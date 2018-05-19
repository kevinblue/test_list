<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开票数据导出</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '已申请',value : '已申请'},{name : '已确认提交',value : '已确认提交'},{name : '全部',value : ''}];

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '<spring:message code="Non-RentalPaymentInvoicingPlanConfirmation" text="资金计划开票确认"/>',
				iconCls : 'icon-node',
				multiSelect : true,
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					isexport:'0',
					rentinvoicetype:'增值税专用发票'
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/fund_rent_invoice_export.xml',
				tools : [
					{
						html : '<spring:message code="invoiceDataExport" text="开票数据导出"/>',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							if(0 == checkedRowDatas.length){
								mini.alert("<spring:message code="5CAB6C00-12EC-4F1E-8F41-A43D6E7A3527" text="请选中要操作的数据！  "/>");
								return;
							}
							var tempIdArr = [];
							for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("已退回"==checkedRowdata.status){
									alert("<spring:message code='ContractLine' text='合同号'/>:"+checkedRowdata.contractid+"已退回,不能导出!!!" );
									return false;
								}
								if(Number(checkedRowdata.isexport)==1){
									alert("<spring:message code='ContractLine' text='合同号'/>:"+checkedRowdata.contractid+"已导出,不能导出!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								tempIdArr.push("'"+id+"'");
								//tempIdArr.push(id);
							}
							mini.confirm("确认要导出税控开票？", "确认？", function(action){
								mini.mask({
									el: document.body,
									cls: 'mini-mask-loading',
									html: '<spring:message code="DataLoading" text="数据加载中，请稍后..."/>'
								});
								if (action == "ok") {
									var pm={};
									pm['ids']=tempIdArr.join(",");
									pm['templateid']='8ad69b144f154493014f1592c0f30010,8ad69b144f154493014f1588128a000c';
									var fileTeplate=new fileTemplateConfig({
				                    	 tableid:miniTable.config.id,
				                         modelname:miniTable.config.title,
				                         url : '/leasing/template/CreateFileByTemplate.action',
				                         returntype:'listtonewpage',
				                         jscallbak :function(tableid){
				                        	 mini.get("table_id1").reload();
				                         },
				                         parames:pm
				                         });
				                     fileTeplate.createFile();
				                     jQuery.ajax({
				                    	url : getRootPath()+"/acl/modifyInvoiceStatus.acl",
										type : 'POST',
										timeout : 30 * 1000,
										data : {ids:tempIdArr.join(",")},
										dataType : 'json',
										error : function(res, errInfo, e) {
											mini.unmask(document.body);
										},
										success : function(resultJson) {
											var returnState = resultJson.returnType;
											switch (returnState) {
											case "SUCCESS": {
												miniTable.reload();
												mini.unmask(document.body);
												break;
											}
											}
										}
				                     });
								}else{
									mini.unmask(document.body);
								}
							});
						}
					}
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'contractid',header : '<spring:message code="ContractId" text="合同号"/>',width : 120},
				   	{field : 'contractnumber',header : '<spring:message code="ContractLine" text="业务合同编号"/>',width : 120,queryConfig : {width:200},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '<spring:message code="CustomerName" text="客户名称"/>',width : 120,allowSort:true,queryConfig : {}},
				   	{field : 'plandate',header : '<spring:message code="ScheduledPaymentDate" text="计划收款日期"/>',
				   		queryConfig : {
							width : 100,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true,
							newLine: false
						}
				   	},
				   	{field : 'planmoney',header : '<spring:message code="PlanMoney" text="计划收款金额"/>',width : 120,dataType:"currency",align:'right',summary: true},
				   	{field : 'balanceexportmoney',header : '<spring:message code="TheRemainingAmountOfTheExport" text="剩余导出金额"/>',dataType:"currency",align:'right',summary: true,
				   		queryConfig : 
				   		{
				   			newLine: true,
				   			width:95,
				   			type:'float',
				   			range:true
				   		}
				   	},
				   	{field : 'feetype',header : '<spring:message code="FeeType" text="费用类型"/>',queryConfig : {
						type:'combobox',
						
						params : {
							pid:'FeeType',
							xmlFileName : 'combos/comboDict.xml'
						},
						readOnly:false,
						textField:'name',
						valueField:'value'
					}},
				   	{field : 'rentinvoicetype',header : '<spring:message code="InvoiceType" text="发票类型"/>',queryConfig:{
				   		
						type : 'combobox',//表单域类型
						width:200,
						valueField : 'value',
						textField : 'name',
						newLine:false,
						allowInput : false,
						defaultValue:'增值税专用发票',
						data : [{'name':'增值税普通发票',value:'增值税普通发票'},{'name':'增值税专用发票',value:'增值税专用发票'}]
				   	}},
				   	{field : 'taxregcode',width : 120,header : '<spring:message code="TaxRegCode" text="纳税人识别号"/>'},
				   	{field : 'taxbank',width : 120,header : '<spring:message code="TaxBank" text="开户行"/>'},
				   	{field : 'taxacc',width : 120,header : '<spring:message code="TaxAccountNumber" text="开户账号"/>'},
				   	{field : 'phone',header : '<spring:message code="BillingPhone" text="开票电话"/>'},
				   	{field : 'regaddress',width : 120,header : '<spring:message code="BillingAddress" text="开票地址"/>'},
				   	{field : 'status',header : '<spring:message code="Status" text="状态"/>'},
				   	{field: 'leaseform', header:'<spring:message code="LeaseForm" text="租赁形式"/>',
						queryConfig:{
							newLine: true,
							width:200,
							type:'combobox',
							params : {
								pid:'leas_form',
								xmlFileName : 'combos/comboDict.xml'
							},
							readOnly:false,
							textField:'name',
							valueField:'name'

					}},
					{field : 'isexport',header : '<spring:message code="ExportStatus" text="导出状态"/>',
				   		queryConfig : 
				   	    {
							//width : 200,
							colspan : 1,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							defaultValue:'0',
							data : [{'name':'已导出',value:'1'},{'name':'未导出',value:'0'}]
					   },
					   renderer:function(e){
        		           var rowData = e.value; 
        		           if(rowData=='1'){
        		        	   return '已导出';
        		           }else{
        		        	   return '未导出';
        		           }
        		       }
				   	},
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>
<script>
function showContractDetail(cid){
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}
</script>