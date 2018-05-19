<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开票数据管理</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var exportstatus = [{name : '已开票',value : '已开票'},{name : '未开票',value : '未开票'},{name : '全部',value : ''}];
var hcstatus = [{name : '已红冲',value : '已红冲'},{name : '未红冲',value : '未红冲'},{name : '全部',value : ''}];
var cancelstatus = [{name : '已作废',value : '已作废'},{name : '未作废',value : '未作废'},{name : '全部',value : ''}];

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
			//	title : '<spring:message code="Non-RentalPaymentInvoicingPlanConfirmation" text="资金计划开票确认"/>',
				title:'发票管理',
				iconCls : 'icon-node',
				multiSelect : true,
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/invoice_interface/invoice_interface_export.xml',
				params:{
					//exportstatus:'已开票'
					backstatus:'已回导'
					
				},
				tools : [
					{
						html : '红冲发票',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							var rowdate= mini.encode(checkedRowDatas);
							if(1 != checkedRowDatas.length){
								mini.alert("请选中单条数据操作！");
								return;
							}
							var tempIdArr = [];
							for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("未开票"==checkedRowdata.exportstatus){
									mini.alert(checkedRowdata.contractid+"未开票不能红冲!!!" );
									return false;
								}
								if("已红冲"==checkedRowdata.hcstatus){
									mini.alert(checkedRowdata.contractid+"的"+checkedRowdata.outno+"已红冲，不能再次红冲!!!" );
									return false;
								}
								if("已作废"==checkedRowdata.cancelstatus){
									mini.alert(checkedRowdata.contractid+"已作废，不能红冲!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								//tempIdArr.push("'"+id+"'");
							}
							mini.confirm("确认要红冲？", "确认？", function(action){
								mini.mask({
									el: document.body,
									cls: 'mini-mask-loading',
									html: '<spring:message code="DataLoading" text="数据加载中，请稍后..."/>'
								});
								if (action == "ok") {
									var rednoticenr="";
								/* 	if(checkedRowDatas[0].rentinvoicetype=='增值税专用发票'){
										mini.get("ids").setValue(tempIdArr.join(","));
										mini.get("id_table_invoice").show();
										mini.unmask(document.body);
									}else{ */
										jQuery.ajax({
					                    	url : getRootPath()+"/eleasing/hcFundRentInvoice.acl",
											type : 'POST',
											timeout : 30 * 1000,
											//data : {ids:tempIdArr.join(","),doctype:'2',rednoticenr:rednoticenr},
											data : {"rowdate":rowdate},
											dataType : 'json',
											error : function(res, errInfo, e) {
												mini.unmask(document.body);
											},
											success : function(resultJson) {
												var returnState = resultJson.returnType;
												switch (returnState) {
												case "SUCCESS": {
													miniTable.reload();
													mini.alert(resultJson.content);//提示红冲发票成功
													mini.unmask(document.body);
													break;
												}
												case "FAILURE": {
													miniTable.reload();
													mini.alert("红冲失败！"+resultJson.content);
													mini.unmask(document.body);
													break;
												}
												}
											}
					                     });
							//		}
								}else{
									mini.unmask(document.body);
								}
							});
						}
					}/* ,'-',{
						html : '作废发票',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							if(0 == checkedRowDatas.length){
								mini.alert("请选中要操作的数据！");
								return;
							}
							var tempIdArr = [];
							for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("已作废"==checkedRowdata.cancelstatus||"已红冲"==checkedRowdata.hcstatus){
									alert(checkedRowdata.contractid+"作废和红冲发票不能作废!!!" );
									return false;
								}else if("未开票"==checkedRowdata.exportstatus){
									alert(checkedRowdata.contractid+"未开票不能作废!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								tempIdArr.push("'"+id+"'");
							}
							mini.confirm("确认要作废？", "确认？", function(action){
								mini.mask({
									el: document.body,
									cls: 'mini-mask-loading',
									html: '<spring:message code="DataLoading" text="数据加载中，请稍后..."/>'
								});
								if (action == "ok") {
				                     jQuery.ajax({
				                    	url : getRootPath()+"/acl/voidedInvoiceToSajt.acl",
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
												mini.alert("作废成功！");
												mini.unmask(document.body);
												break;
											}
											case "FAILURE": {
												miniTable.reload();
												mini.alert("作废失败！"+resultJson.content);
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
					},'-',{
						html : '打印发票',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							if(0 == checkedRowDatas.length){
								mini.alert("请选中要操作的数据！");
								return;
							}
							var tempIdArr = [];
							for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("未开票"==checkedRowdata.exportstatus){
									alert("单据号："+checkedRowdata.outno+"未开票,不能打印!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								tempIdArr.push("'"+id+"'");
							}
							mini.confirm("确认要打印？", "确认？", function(action){
								mini.mask({
									el: document.body,
									cls: 'mini-mask-loading',
									html: '<spring:message code="DataLoading" text="数据加载中，请稍后..."/>'
								});
								if (action == "ok") {
				                     jQuery.ajax({
				                    	url : getRootPath()+"/acl/printInvoiceToSajt.acl",
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
												mini.alert("打印成功！");
												miniTable.reload();
												mini.unmask(document.body);
												break;
											}
											case "FAILURE": {
												miniTable.reload();
												mini.alert("打印失败！"+resultJson.content);
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
					} */
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'outno',header : '单据号',queryConfig : {}},
				   	{field : 'contractnumber',header : '<spring:message code="ContractLine" text="业务合同编号"/>',queryConfig : {},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}},
				   	{field : 'custname',header : '<spring:message code="CustomerName" text="客户名称"/>',allowSort:true,queryConfig : {}},
				   	{field : 'rentlist',header : '期次',allowSort:true},
				   	{field : 'plandate',header : '<spring:message code="ScheduledPaymentDate" text="计划收款日期"/>',
				   		queryConfig : {
				   			colspan : 1,
				   			newLine: true,
							width : 100,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						}
				   	},
				   	{field : 'planmoney',header : '<spring:message code="PlanMoney" text="计划收款金额"/>',dataType:"currency",align:'right',summary: true},
				   	{field : 'invoicemoney',header : '本次开票金额',dataType:"currency",align:'right',summary: true},
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
				   		colspan : 1,
						type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						allowInput : false,
					//	defaultValue:'增值税专用发票',
						data : [{'name':'增值税普通发票',value:'增值税普通发票'},{'name':'增值税专用发票',value:'增值税专用发票'},{'name':'全部',value:''}]
				   	}},
				   	{field : 'invoiceno',header : '发票号码'},
				   	{field : 'invoicecode',header : '发票代码'},
				   	{field : 'taxrate',header : '税率'},
				   	{field : 'taxmoney',header : '税额'},
				   	{field : 'taxregcode',header : '<spring:message code="TaxRegCode" text="纳税人识别号"/>'},
				   	{field : 'taxbank',header : '<spring:message code="TaxBank" text="开户行"/>'},
				   	{field : 'taxacc',header : '<spring:message code="TaxAccountNumber" text="开户账号"/>'},
				   	{field : 'phone',header : '<spring:message code="BillingPhone" text="开票电话"/>'},
				   	{field : 'regaddress',header : '<spring:message code="BillingAddress" text="开票地址"/>'},
				   	{field : 'exportstatus',header : '开票状态'},
				   	{field : 'hcstatus',header : '红冲状态',queryConfig:{
				   		colspan : 1,
				   		newLine:true,
						type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						allowInput : false,
						defaultValue:'未红冲',
						data : hcstatus
				   	}},
				   	{field : 'cancelstatus',header : '作废状态',queryConfig:{
				   		colspan : 1,
						type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						allowInput : false,
						defaultValue:'未作废',
						data : cancelstatus
				   	}}
				]
			});
		});
	});
</script>
</head>
<body>
<div style="display: none;">
<input name="ids" id='ids' class="mini-textbox" label="红票通知单号" style="width: 90%;"/>
</div>
<div id="id_table_invoice" class="mini-window" title="输入专用发票红票通知单号"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<table style="width: 100%">
			<tr style="width: 100%;">
			    <td style="width: 20%;">红票通知单号：</td>
				<td style="width: 70%;">
					<input name="rednoticenr" id='rednoticenr' class="mini-textbox" label="红票通知单号" required="true" style="width: 90%;"/> <font class="required-tag">*</font>
				</td>
			</tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="hcaction">确定</a></td>
			</tr>
		</table>
</div>
</body>
</html>
<script>
function showContractDetail(cid){
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}
function hcaction(){
	mini.get('id_table_invoice').hide();
	mini.mask({
		el: document.body,
		cls: 'mini-mask-loading',
		html: '<spring:message code="DataLoading" text="数据加载中，请稍后..."/>'
	});
	jQuery.ajax({
    	url : getRootPath()+"/acl/generateInvoiceToSajt.acl",
		type : 'POST',
		timeout : 30 * 1000,
		data : {ids:mini.get("ids").getValue(),doctype:'2',rednoticenr:mini.get("rednoticenr").getValue()},
		dataType : 'json',
		error : function(res, errInfo, e) {
			mini.unmask(document.body);
		},
		success : function(resultJson) {
			var returnState = resultJson.returnType;
			switch (returnState) {
			case "SUCCESS": {
				mini.get("table_id1").reload();
				mini.alert("红冲成功！");
				mini.unmask(document.body);
				break;
			}
			case "FAILURE": {
				mini.get("table_id1").reload();
				mini.alert("红冲失败！"+resultJson.content);
				mini.unmask(document.body);
				break;
			}
			}
		}
     });
}
</script>