<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金支付通知书</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
     mini.get("id_minitableimport").hide();
     mini.get(tableid).reload();
}
function showcustinfo(custid){
	var params = "id="+custid+"&isView=true";
	var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				addRemoteOperUrl:getRootPath()+"/acl/addTaxRentHcInfo.acl",
				remoteOper:true,
				id : 'table_id2',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '租金支付通知书',
				iconCls : 'icon-node',
				multiSelect : false,
				queryButtonColSpan : 6,
				queryButtonNewLine: true,
				showPager:true,
				isRemoteStatistic: true,
				frozenStartColumn:0,
				frozenEndColumn:5,
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/rent_payment_notice.xml',
				tools : [{
					html : '生成租金支付通知书',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
						if(0 == checkedRowDatas.length){
							mini.alert("请选择要导出租金支付通知书的记录！！");
							return;
						}
						
						var row = checkedRowDatas[0];
						var params = {};
						
						//label:cust_name,contract_number,plandate
						params['label.cust_name'] = row.cust_name;//1.承租人
						params['label.contract_number'] = row.contract_number;//2合同编号
						var dateStr=row.plandate;
						var date=tenwa.toDate(dateStr,'yyyy年MM月dd日');
						var plandate=tenwa.toChar(date,'yyyy年MM月dd日');
						params['label.plandate']=plandate;
						
						//list:projectname,rent_list,rent,comment_rent,total,totalRMB
						params['list.projectname']=row.projectname;//6项目projectName
						params['list.rent_list']=row.rent_list;//7.期次
						var rent=row.rent;
						params['list.rent']=rent;//8.金额
						params['list.comment_rent']=0;//9.备注金额
						var total=rent;
						params['list.total']=total;//10.总计
						var totalRMB=tenwa.money2DX(total);
						params['list.totalRMB']=totalRMB;////11.人民币
						
						//comment:lessor,leaseaccbank,leaseaccnumber
						params['comment.lessor']=row.lessor;//12.户名
						params['comment.leaseaccbank']=row.leaseaccbank;//13.开户行
						params['comment.leaseaccnumber']=row.leaseaccnumber;//14.账号
						
						//date
						var sysTime=new Date();
						var nowTime=tenwa.toChar(sysTime,'yyyy年MM月dd日');
						params['date.nowTime']=nowTime;
						
					  	var fileTeplate=new fileTemplateConfig({						
							templateno : 'F-201612009',
							tableid:miniTable.config.id,
							modelname:miniTable.config.title,
							returntype:'listtonewpage',
							parames :params
						});
						fileTeplate.createFile();
				  	}
				}],
				columns : [ 
				    {
				    	type : 'indexcolumn'
				    },
				   	{
				    	type : 'checkcolumn'
				    },  
				   	{
				    	field : 'id',
				    	header : 'id',
				    	visible : false
				    },
				   	{
				    	field : 'contract_number',
				    	header : '业务合同编号',
				    	queryConfig : {},
				    	width: 80,
				    	renderer:function(e){
							return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.id+"\")'>" + e.record.contract_number + "</a>";
						}
				    },
					{
				    	field : 'custid',
				    	header : '客户id',
				    	visible : false
				    },
				   	{
				    	field : 'cust_name',
				    	header : '客户名称',
				    	width: 130,
				    	queryConfig : {},
						renderer:function(e){
					   	    var row=e.record;
					   		return "<a href='javascript: showcustinfo(\""+row.custid+"\")'>"+row.cust_name+"</a>";
					   	}
				    },
				    {
				    	field : 'projectname',
				    	header : '项目名称',
				    	visible : false
				    },
				   	{
				    	field : 'rent_list',
				    	header : '期次',
				    	width: 70,
				    	align:'center'
				    },
				   	{
				    	field : 'plandate',
				    	header : '计划日期',
				    	width: 80,
				    	queryConfig:{
				    		type:'date',
				    		range:true
				    	}
				    },
				   	{
				    	field : 'rent',
				    	header : '计划金额',
				    	width: 80,
				    	dataType: 'currency',summary:true
				    },
				   	{
				    	field : 'penalty',
				    	header : '罚息金额',
				    	width: 80,
				    	dataType: 'currency',summary:true
				    },
				   	{
				    	field : 'lessor',
				    	header : '出租人',
				    	align:'center',
				    	visible : false
				    },
				   	{
				    	field : 'leaseaccbank',
				    	header : '租金回笼银行',
				    	width: 100,
				    	align:'center'
				    },
				   	{
				    	field : 'leaseaccnumber',
				    	header : '租金回笼账号',
				    	width: 150
				    },
				   	{
				    	field : 'export_state',
				    	header : '导出状态',
				    	width: 80,
				    	align:'center',
				    	queryConfig:{
					   		type:'combobox',
					   		showNullItem: true,
					   		newLine: true,
					   		data: '[{name:"已导出",value:" RPNR.ID IS NOT NULL"},{name:"未导出",value:" RPNR.ID IS NULL"}]',
					   		textField:"name",
					   		valueField:"value"
				   		}
				    },
				   	{
				    	field : 'export_times',
				    	header : '导出次数',
				    	width: 80,align:'center'
				    },
				   	{
				    	field : 'proj_manage',
				    	header : '项目经理',
				    	width: 80,
				    	align:'center',
				    	queryConfig : {}
				    },
				   	{
				    	field : 'proj_dept',
				    	header : '业务部',
				    	width: 100,
				    	queryConfig:{}
				    },
				   	{
				    	field : 'offsetflag',
				    	header : '是否抵扣',
				    	visible:false
				    }
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>
