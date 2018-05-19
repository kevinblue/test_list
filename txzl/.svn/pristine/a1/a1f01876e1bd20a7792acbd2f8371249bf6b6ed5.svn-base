<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>凭证信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var statusdata = [{name : '有效',value : '0'},{name : '无效',value : '1'}];
var evidence_number="";
var contractid="";
var modlename = "";
var rollback=0;
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'凭证信息',
				iconCls:'icon-node',
				multiSelect:true,
				hiddenQueryArea:false,
				queryButtonColSpan:6,
				queryButtonNewLine:true,
				showPager:true,
				xmlFileName:'/voucher/voucher_list.xml',
				tools:[{
					html:'导出凭证',
					plain:true,
					iconCls:'icon-addfolder',
					handler:function(miniTable, buttonText) {						
						var checkedRowDatas = miniTable.getSelecteds();
						if(0 == checkedRowDatas.length){
							alert("请选择要导出开票的记录！！");
							return;
						}
						var tempIdArr = [];
						for(var i = 0;i<checkedRowDatas.length;i++){
							var checkedRowdata = checkedRowDatas[i];
							if("1"==checkedRowdata.exp_flag){
								alert("凭证号："+checkedRowdata.evidence_number+"已导出,不能导出!!!" );
								return false;
							}
							var id = checkedRowdata.evidence_number;
							tempIdArr.push("'"+id+"'");
						}
						var fileTeplate=new fileTemplateConfig({
			            	 templateno:'F-201412008',
			            	 tableid:"datagrid1",
			                 modelname:"凭证导出",
			                 returntype:'listtonewpage',
	                         jscallbak :function(tableid){
	                        	 mini.get("table_id1").reload();
	                         },
			                 parames:{
			                	//importorexportcallback:'exportRentIncomeInvoiceBefore',
			                	importorexportcallbackafter:'exportVoucherAfter',
			                    ids:tempIdArr.join(",")  
			                    //splitfield:'planmoney',
			                    //rangevalue:'100'
			                 }
			            });
			            fileTeplate.createFile();
					}
				 },'-',
				 {
					    html: '红冲凭证',
						plain: true,
						iconCls: 'icon-addfolder',
						handler: function(miniTable, buttonText){
							var rowDatas = miniTable.getSelecteds();
							var rowdatastring = JSON.stringify(rowDatas);
							if(rowDatas.length == 0){
								mini.alert("请选中要操作的数据");
								return;
							}
							for(var i=0;i<rowDatas.length;i++){
								if(rowDatas[i].rollback != "正常凭证"){
									mini.alert("已红冲过,请选正常凭证，进行红冲");
								    return;
								}
							}
							if(rowDatas.length>0){
									ajaxRequest({
										url : getRootPath() + "/acl/redVoucher.acl",
										method : 'POST',
										success : function(res) {
											mini.get("table_id1").reload();
											mini.alert("操作成功");
										},
										async : false,
										failure : function(res) {
											mini.alert("操作失败");
										},
										params : {
											rowdatastring:rowdatastring,
											loginuserid:"${sessionScope['login_userid']}"
										}
									});
							}
						}
				 },'-',
				 {
					    html: '导入EBS',
						plain: true,
						iconCls: 'icon-addfolder',
						handler: function(miniTable, buttonText){
							var rowDatas = miniTable.getSelecteds();
							var rowdatastring = JSON.stringify(rowDatas);
							
							if(rowDatas.length == 0){
								mini.alert("请选中要操作的数据");
								return;
							}
							for(var i=0;i<rowDatas.length;i++){
								if(rowDatas[i].rollback != "正常凭证"){
									mini.alert("已红冲过,请选正常凭证，进行红冲");
								    return;
								}
							}
							if(rowDatas.length>0){
							$.ajax({
						        url: getRootPath()+"/acl/importVoucherEbs.acl",
						        type: "post",
						        cache: false, 
						        data :{rowdatastring:rowdatastring},
						        async : false,
						        success: function (data) {
						        mini.alert(data);
						        }
						    });
							
						
						   }
						}
				 }
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'client_id',header:'合同id',visible:false},
				   	{field:'rollback',header:'凭证状态',visible:true},
				   	{field:'evidence_number',header:'凭证号',queryConfig:{}},
				   	{field:'modlename',header:'系统模块',queryConfig:{}},				   	
				   	{field:'happen_date',header:'记账日期'},
				   	{field:'ebank_fact_date',header:'业务日期',visible:false},				   	
				   	{field:'recording_voucher',header:'凭证字',visible:false},				   
				   	{field:'voucher_status',header:'凭证状态',visible:false},
				   	{field:'exp_flag',header:'导出状态'},
				   	{field:'ebank_fact_date',header:'查看明细',
				   		renderer:function(e){
				   			 evidence_number = e.record.evidence_number;
							 contractid = e.record.f18;
							 modlename = e.record.modlename;
							 rollbackname = e.record.rollback;
							 if(rollbackname == '被红冲'){
								 rollback = 1;
							 }else if(rollbackname == '红冲凭证'){
								 rollback = -1;
							 }else if(rollbackname == '冲抵凭证'){
								 rollback = 2;
							 }
							return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + evidence_number +"\",\""+rollback+ "\")'> 查看明细 </a>";
						}
				   	},
				   	{field:'contract_number',header:'合同号',queryConfig:{}},
				   	{field:'evidence_summary',header:'摘要'},
				   	{field:'exp_date',header:'传输日期'},
				   	{field:'gennerate_date',header:'生成日期'}	
				]
			});
		});
	});
    function opencustdetail(evidence_number,rollback){
        var url=getRootPath()+"/voucher/voucher_detail/voucherass_info_detail.bi?evidence_number="+evidence_number+"&rollback="+rollback;
        var sheight = window.screen.availHeight - 30;
	    var swidth = window.screen.availWidth - 10;
	    var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	    window.open(url, '_blank', winoption);
    }
</script>
</head>
<body></body>
</html>