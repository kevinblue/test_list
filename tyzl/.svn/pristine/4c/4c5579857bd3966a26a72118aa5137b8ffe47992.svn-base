<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if (miniui_ext.submitFormValidation(["contract_change_info_form","changeInfoForm"]) == false) return;
	if(!checkContractIsAdust()){
		mini.alert('请先进行租金变更！！！');
		return;
	}
    miniui_ext.submitIncludeData("tabDetails");
	return true;
}

</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='<mini:param  name="json_contract_equip_str" defaultValue="[]"/>'></input>
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
		<div title="变更说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_change_info.jsp"></jsp:include>
		</div>
		<div title="(变更前)商务条件" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_compare/main_5_1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<%-- <div title="变更信息" name="business_condition" iconCls="icon-node">
			<jsp:include page="contract_planchange_info.jsp" ></jsp:include>
		</div> --%>
		 <div title="(变更后)商务条件" name="fund_rent_plan_new" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" >
				<jsp:param value="true" name="isRentChange"/>
			</jsp:include>
		</div> 
	</div>
</div>
<script>
$(function(){
	//租金计划变更 不能变更的域控制为只读
	setDomReadOnly();
});
function setDomReadOnly(){
	var str="equipamt,firstpaymentratio,settlemethod,leaseamtdate";
	var ss=str.split(",");
	for(var i=0;i<ss.length;i++){
		$mini(ss[i]).disable();
	}
	//费用表 表单只读
	miniui_ext.disableFormFields("fund_table");
	
}
</script>