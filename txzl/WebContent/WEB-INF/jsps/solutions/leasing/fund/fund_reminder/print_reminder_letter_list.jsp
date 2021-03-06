<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>打印催款函列表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">

jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id1',
			width:globalClientWidth,
			height:globalClientHeight,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			editFormPopupWindowWidth:700,
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			remoteOper:true,
			title:'打印催款函列表',
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			tools:[
					{html:'取消生成',
				     plain:true,
				     iconCls:'icon-undo',
				     handler:function(miniTable, buttonText) {
					 var row = miniTable.getSelecteds();
					 if(row.length>0){
						 if(0 == row[0].status){
				    		mini.alert("催款函状态为已打印,不允许取消生成！");
				    		return;
				    	}
						mini.confirm("确定取消生成？","提示：",function(data){if("ok" == data){backGenerated();}});
					   }else{
						mini.alert("请至少选中一行！");}
				    }},'-',
				    {
				    html:'打印',
				    plain:true,
				    iconCls:'icon-redo',
				    handler:function(miniTable, buttonText) {
					    var row = miniTable.getSelected();
					    if(null != row){
					    	if(0 == row.status){
					    		mini.alert("催款函状态为已打印,不允许重复打印！");
					    		return;
					    	}
					    	var params={};
					     	$
							.ajax({
								url : getRootPath()
										+ "/acl/getReminderLetterRunningWater.acl",
								type : "post",
								cache : false,
								data : {},
								async : false,
								success : function(	date) {
									params['number.num'] = date;
								},
								failure : function(date) {
									mini.alert(date);
								}
							});
					    	//客户名称
					    	params['overdue.custname'] = row.custname;
					    	var sdate = row.startdate;
					    	params['overdue.startdate'] = sdate.substring(0,4)+"年"+sdate.substring(5,7)+"月"+sdate.substring(8)+"日";
					    	//合同号
					    	params['overdue.contract_id'] = row.contractnumber;
					    	var pdate = row.plandate;
					    	params['overdue.plandate'] = pdate.substring(0,4)+"年"+pdate.substring(5,7)+"月"+pdate.substring(8)+"日";
					    	//逾期期数
					    	params['overdue.rentlist'] = row.rent_list;
					    	params['overdue.overduerent'] = row.overduerent;
					    	var sumoverduerent = parseFloat(row.overduerent)+parseFloat(row.penalty);
					    	params['overdue.sumoverduerent'] = sumoverduerent;
					    	var thisdate=mini.formatDate(new Date(),"yyyy-MM-dd");
					    	params['overdue.systemdate']=thisdate.substring(0,4)+"年"+thisdate.substring(5,7)+"月"+thisdate.substring(8)+"日";
						    var fileTeplate=new fileTemplateConfig({
								templateno:'F-201612011',
								tableid:'table_id1',
								modelname:'租金催收通知函',
								returntype:'file',
								parames:params
						    });
						    fileTeplate.createFile();
							printgeneratedData();
					    }else{
					    	mini.alert("请至少选中一行！");
					    }
				}
			}],
			xmlFileName:'/eleasing/jsp/fund/fund_reminder/print_reminder_letter_list.xml',
			afterClickClearBtnCallBack: function(miniTable,queryAreaParams){
				mini.get("table_id1_queryArea_status").setText("已申请");
				$("input[name='table_id1_queryArea_status']").val("1");
				return true;
			},
			params:{},
			columns:[ 
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',visible:false},
				{field:'contract_id',header:'合同编号',headerAlign:'center',width:100,allowSort:true},
		        {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				        queryConfig:{
					     labelWidth:100,
					          width:200}},
	          {field:'projname',header:'项目名称',headerAlign:'center',width:100,allowSort:true,
			        queryConfig:{
				     labelWidth:100,
				          width:200}},
			    {field:'rawValue_partdept',header:'催款员',headerAlign:'center',
				        queryConfig:{
				        	newLine:false,
					          width:200}},
			    {field:'custname',header:'客户名称',headerAlign:'center',width:100,allowSort:true,
				        queryConfig:{
					        newLine:true,
					          width:200}},
			    {field:'startdate',header:'签约日期',headerAlign:'center',width:100,visible:false},
			    {field:'plandate',header:'计划日期',headerAlign:'center',width:100,visible:false},
			    {field:'rent_list',header:'期项',headerAlign:'center',width:100,allowSort:true},
			    {field:'overduenum',header:'逾期期数',headerAlign:'center',width:100,allowSort:true},
			    {field:'overduerent',header:'逾期租金',headerAlign:'center',width:100,allowSort:true},
			    {field:'penalty',header:'逾期罚息',headerAlign:'center',width:100,allowSort:true},
			    {field:'',header:'合计欠款',headerAlign:'center',width:100,allowSort:true,
				            renderer:function(e){
								 var incrent = e.record.overduerent;
								 var penalty = e.record.penalty;
								 return incrent>0||penalty>0?parseFloat(incrent) + parseFloat(penalty):0;
				}
			},{field:'status',header:'状态',headerAlign:'center',width:100,allowSort:true,
				            renderer:function(e){
								 var status = e.record.status;
								 return status=="1"?"已申请":"已打印";
				},
				queryConfig:{
			           width:200,
				       type:'combobox',
					   valueField:'value',
					   textField:'text',
					   value:'1',
					   text:'已申请',
		   			   data:[{text:'已打印',value:'0'},{text:'已申请',value:'1'},{text:'全部',value:''}]
			    }
			}]
		});
	});
});
</script>
</head>
<body>
</body>
</html>
<script type="text/javascript">
	function backGenerated(e){
		var table_id1 = mini.get("table_id1");
		var rows = table_id1.getSelecteds();
		var noticeids = "";
		for(var i=0;i<rows.length;i++){
			noticeids+=rows[i].id+",";
		}
		$.ajax({
	        url: getRootPath()+"/acl/removeGeneratedReminderLetter.acl",
	        type: "post",
	        cache: false, 
	        data:{"noticeids":noticeids},
	        async:false,
	        success: function (text) {
	        	mini.alert("取消成功！");
	        }
	    });
		mini.get("table_id1").load();
	}
	function printgeneratedData(e){
		var table_id1 = mini.get("table_id1");
		var row = table_id1.getSelected();
		$.ajax({
	        url: getRootPath()+"/acl/updateGeneratedReminderLetter.acl",
	        type: "post",
	        cache: false, 
	        data:{"id":row.id,"status":0},
	        async:false,
	        success: function (text) {
	        	mini.alert("打印成功！");
	        }
	    });
		mini.get("table_id1").load();
	}
</script>