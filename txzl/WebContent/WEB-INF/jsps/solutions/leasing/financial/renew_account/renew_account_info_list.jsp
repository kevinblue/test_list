<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>续借台帐</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ],function(ApTable) {
							new ApTable(
									{
										id : 'table_renew_account_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 350,
										title : '续借台帐',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										entityClassName : 'com.tenwa.leasing.entity.finacial.RenewAccount',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/finacial/renew_account/renew_account_list.xml',
										tools : [ 'add', '-', 'edit', '-','remove'],
										/* afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
											mini.getbyName("contractid").setText(mini.getbyName("projectname").getValue());
										}, */
										columns : [
												{
													type : 'indexcolumn',width:40
												},
												{
													type : 'checkcolumn',width:40
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
												{
													field : 'loanaccountidname',
													header : '借款合同编号',
													visible : true,
													width:100,
													queryConfig:{},
													formEditorConfig : {
														newLine:false,
														required : false,
														fieldVisible : false,
														fillFromFieldName:'loanaccountid',
												        fillProperty:'name'
													}
												},
												{	field : 'loanaccountid',
													header : '借款合同编号',
													visible : false,
													formEditorConfig : {
														width : 200,
														type : 'queryinput',
														required : false,
														textField : 'name',
														valueField : 'value',
											            allowInput:false,
												        fieldVisible:true,
														params : {
															xmlFileName : '/eleasing/jsp/finacial/renew_account/renew_account_info.xml'
														}
													}
												},
												/* {
													field : 'corpus',
													header : '借款金额(元)',
													visible : true,
													dataType:"currency",
													 queryConfig:{
										                  type:'float',
										                 range:true,
										                 newLine:false,
										               },
													formEditorConfig : {
														vtype : 'float',
														newLine:true,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'loantermnew',
													header : '期限',
													visible : true,
													formEditorConfig : {
														required : false,
														fieldVisible : false
													}
												}, 
												{
													field : 'loandate',
													header : '借款日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														type : 'date',
														labelWidth : 100,
														width : '100%',
														newLine:true,
														format : 'yyyy-MM-dd',
														otherAttributes:'onvaluechanged="loandatejudge"',
														required : true
													}
												},
												{
													field : 'expiredate',
													header : '原预计还款日',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														otherAttributes:'onvaluechanged="loandatejudge"',
														required : true
													},
													queryConfig : {
														type : 'date',
														range : true,														
														format : 'yyyy-MM-dd',
														newLine :false
													}
												}, */
												{
													field : 'renewdate',
													header : '续借日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													queryConfig : {
														type : 'date',
														format : 'yyyy-MM-dd',
														newLine :false
													},
													formEditorConfig : {
														type : 'date',
														labelWidth : 100,
														width : '100%',
														newLine:true,
														format : 'yyyy-MM-dd',
														//otherAttributes:'onvaluechanged="loandatejudge"',
														required : true
													}
												},
												 {
													field : 'expirationdate',
													header : '续借到期日',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														type : 'date',
														labelWidth : 100,
														width : '100%',
														fieldVisible : true,
														//otherAttributes:'onvaluechanged="loandatejudge"',
														newLine:true,
														format : 'yyyy-MM-dd',
														required : false
													}
												},
												{
													field : 'nowrate',
													header : '执行利率%',
													visible : true,
													formEditorConfig : {
														readOnly:false,
														vtype : 'float',
														newLine:true,
														required : false,
														fieldVisible : true
													}
												}
												/* {field : 'alreadycorpus',header : '已偿还本金(元)',dataType:"currency",visible : true,formEditorConfig:{fieldVisible: false}},
												{field : 'surpluscorpus',header : '剩余本金(元)',queryConfig : {
													newLine:false,colspan:3},dataType:"currency",summary:true,visible : true,formEditorConfig:{fieldVisible: false}},
												
												{field: 'projectname', header: '借款项目', width:160,
													queryConfig : {
														newLine:true,},
													  formEditorConfig:{
												          fieldVisible: false,
												     fillFromFieldName:'contractid',
												          fillProperty:'name',
												       entityClassName:'com.tenwa.leasing.entity.contract.ContractInfo',	
												 entityHeaderFieldName:'projectName'}
													},
												{
													field : 'contractid',
													header : '借款项目',
													visible : false,
													formEditorConfig : {
														width : 200,
														type : 'queryinput',
														required : false,
														textField : 'name',
														valueField : 'value',
											            allowInput:false,
												         fieldVisible:true,
														params : {
															xmlFileName : '/eleasing/jsp/finacial/loan_account/loan_contract.xml'
														}
													}
												}, */
										]
									});
						});
	});	
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
	

</body>
<script type="text/javascript">
function loandatejudge(e){ 
	var start=mini.getbyName("loandate").getValue();
	mini.getbyName("expiredate").setMinDate(start);  
	mini.getbyName("paydate").setMinDate(start); 
	var end =mini.getbyName("expiredate").getValue();
	var yearstart=start.getFullYear();
	var yearend=end.getFullYear();
	var monthstart=start.getMonth();
	var monthend=end.getMonth();
	if(yearend==yearstart){
		mini.getbyName("loantermnew").setValue(monthend-monthstart+"月"); 
	}else{
		if(monthstart==monthend){
			mini.getbyName("loantermnew").setValue(yearend-yearstart+"年"); 
		}else
		{
			mini.getbyName("loantermnew").setValue(yearend-yearstart+"年"+(monthend-monthstart)+"月"); 
		}
		
	}
			
	
	}
function judgedate(e){ 
	var start=mini.getbyName("start_date").getValue();
	mini.getbyName("end_date").setMinDate(start);  			
}
function cancel(){
	mini.get("multieditWindow").hide();
}
function selectAccrued(){
	if (miniui_ext.submitFormValidation(["multiform"]) == false) return false;
	var startdate=mini.get("start_date").getValue();
	var enddate=mini.get("end_date").getValue();
	startdate=mini.formatDate (startdate,"yyyy-MM-dd");
	enddate=mini.formatDate (enddate,"yyyy-MM-dd");
	var params = "startdate="+startdate+"&enddate="+enddate;
	var url = getRootPath()+"/leasing/financial/loan_account/loan_accrued_interest_list.bi?"+params;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}

</script>
</html>
