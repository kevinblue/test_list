<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>借款台帐</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">

	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ],function(ApTable) {
							new ApTable(
									{
										id : 'table_loan_account_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										title : '借款台帐',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										entityClassName : 'com.tenwa.leasing.entity.finacial.LoanAccount',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/finacial/loan_account/loan_account_list.xml',
										tools : [ 'add', '-', 'edit', '-','remove','-',{html:'查看计提信息',
											 plain:true,
											 iconCls:'icon-addfolder',
											 handler:function(miniTable, buttonText) {
														var multieditWindow = mini.get("multieditWindow");
														multieditWindow.show();
												}
											},'-','exportExcel'],
										afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
											mini.getbyName("contractid").setText(mini.getbyName("projectname").getValue());
										},
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
													   field:'oper',header:'借款详情',
													   allowSort: false,
													   readOnly : true,
													   renderer:function(e){
													   	      var row=e.record;
													   		return "<a href='javascript:showloaninfo(\""+row.id+"\",\""+row.loancontractid+"\")'>借款详情</a>";
													   },
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{field:'',header:'操作',width:200,
						  					  		   formEditorConfig:{
						                                   fieldVisible:false},
												               renderer:function(e){
													                   var id = e.record.id;
												                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 /附件个数："+e.record.attachmentnum+"</a>";
												               }},
												 {field : 'attachmentnum',
												    header : '附件个数',
													visible : false,
													queryConfig : {},
													formEditorConfig : {
													readOnly:true,
														required : true,
													fieldVisible : true
																	}
																},
												{
													field : 'loanunit',
													header : '借款单位',
													visible : true,
													queryConfig : {
														

													},
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'loantype',
													header : '借款类别',
													visible : true,
													formEditorConfig : {
														required : true,
														newLine:true,
														fieldVisible : true
													}
												},
												{
													field : 'loancontractid',
													header : '借款合同编号',
													visible : true,
													width:120,
													formEditorConfig : {
														newLine:false,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'corpus',
													header : '借款本金(元)',
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
													field : 'loanterm',
													header : '借款期限',
													visible : false,
													formEditorConfig : {
														required : false,
														fieldVisible : false
													}
												},
												{
													field : 'loantermnew',
													header : '借款期限',
													visible : true,
													formEditorConfig : {
														required : false,
														fieldVisible : false
													}
												}, 
												{
													field : 'yearrate',
													header : '合同利率%',
													visible : true,
													formEditorConfig : {
														vtype : 'float',
														required : true,
														newLine:false,
														fieldVisible : true
													}
												},
												{
													field : 'nowrate',
													header : '当前执行利率%',
													visible : true,
													formEditorConfig : {
														readOnly:true,
														vtype : 'float',
														newLine:true,
														required : false,
														fieldVisible : true
													}
												},
												/* {
													field : 'cautionmoney',
													header : '保证金(元)',
													visible : true,
													dataType:"currency",
													formEditorConfig : {
														vtype : 'float',
														required : true,
														newLine:true,
														fieldVisible : true
													}
												}, */
												{
													field : 'paymenttypename',
													header : '还款方式',
													visible : true,
													formEditorConfig : {
														
														fieldVisible : false,
														fillFromFieldName : 'paymenttype',
														fillProperty : 'name'
													}
												},
												{         
													field : 'paymenttype',
													header : '还款方式',
													visible : false,
													formEditorConfig : {
														type : 'combobox',
														textField : 'name',
														required : true,
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'paymenttype',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'loandate',
													header : '借款日',
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
													header : '到期日',
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
												},
												 {
													field : 'paydate',
													header : '付息日',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														type : 'date',
														labelWidth : 100,
														width : '100%',
														fieldVisible : true,
														otherAttributes:'onvaluechanged="loandatejudge"',
														newLine:true,
														format : 'yyyy-MM-dd',
														required : false
													}
												}, 
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
												},
												{field : 'atmmoney',header : '已提款金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'surplusatmmoney',width:160,header : '剩余可提款金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'alreadycorpus',header : '已还本金(元)',dataType:"currency",visible : true,formEditorConfig:{fieldVisible: false}},
												{field : 'alreadyinterest',header : '已付利息(元)',dataType:"currency",visible : true,formEditorConfig:{fieldVisible: false}},
												{field : 'surpluscorpus',header : '未还本金(元)',queryConfig : {
													newLine:false,colspan:3},dataType:"currency",summary:true,visible : true,formEditorConfig:{fieldVisible: false}},
												{field : 'surplusinterest',header : '未付利息(元)',dataType:"currency",visible : true,formEditorConfig:{fieldVisible: false}},
												{field : 'alreadycaution',width:160, header : '保证金已付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'surpluscaution',width:160,header : '保证金未付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'alreadyhandling',width:160,header : '手续费已付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'surplushandling',width:160,header : '手续费未付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'alreadyother',width:160,header : '其他资金已付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{field : 'surplusother',width:160,header : '其他资金未付金额(元)',visible : true,dataType:"currency",formEditorConfig:{fieldVisible: false}},
												{
													field : 'mortgagedetail',
													header : '担保抵押情况',
													visible : true,
													
													formEditorConfig : {
														type:'textarea',
														colspan:3,
														width:'100%',
														required : true,
														newLine:true,
														fieldVisible : true
													}
												}
										]
									});
						});
	});
	
	function showloaninfo(id,loancontractid)
	{
		var params = "id="+id+"&loancontractid="+loancontractid+"&isView=true";
		var url = getRootPath()+"/leasing/financial/loan_account/loan_account_info_detail.bi?"+params;
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
		
	}
	
	function showanduploadfile(id,type){
 			var urlFlag = getRootPath()+"/leasing/financial/loan_account/loan_information_file_list.bi?id="+id+"&type="+type;
 			mini.open({
 		        url: urlFlag,
 		        title: "借款资料", width: 800, height: 455,
 		        onload: function () {
 		         mini.get("table_loan_account_info_id").reload();
 		   		 mini.unmask(document.body);
 		        },
 		        ondestroy: function (action) {
 		        	if("savesuccess" == action){
 		        		window.location.reload();
 		        	}
 		        }
 		    });
 			

 	  	}
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
	
<div id="multieditWindow" class="mini-window" title="请选择" style="width:30%;height:200px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="multiform" title="计提信息">
		<table class="fillTable" cellspacing="0" cellpadding="10px" style="width:100%;" >
				<tr class="tr-even">
				<td class="td-content-title" style="width:80px;">起息日：</td>
				<td class="td-content">
					<input id="start_date" name="start_date" class="mini-datepicker"  onvaluechanged="judgedate" label="起息日"  required="true" allowInput="false"/>
				</td>
				</tr>
				<tr class="tr-even">
				<td class="td-content-title" style="width:80px;">止息日：</td>
				<td class="td-content">
					<input id="end_date" name="end_date" class="mini-datepicker"  label="止息日"  required="true" allowInput="false"/>
				</td>
				</tr>
				<tr>
				<td align="center" colspan="2">
					<a class="mini-button " iconCls="icon-save"  onclick="selectAccrued">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
					<span class="separator"></span>
					<a class="mini-button " iconCls="icon-close"  onclick='cancel'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
				</td>
			</tr>   
		</table>
	</div>
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
