<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
isViewHistoryTask = true;
function formPageReadOnlyCallBack(){
	mini.get("auto_hire").disable();
}
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	var form = new mini.Form("#rent_income_form");
	form.validate();
	if (form.isValid() == false) return;
	if(submitCheck() == false) return;
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_detail_str" name="json_rent_income_detail_str" value='${empty json_rent_income_detail_str ? "[]" : json_rent_income_detail_str }'></input>
<div id="rent_income_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入合同基本信息 --> <jsp:include page="../factoring_comm/contract_factoring_base_info.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%">
		<div title="本次应收账款回笼明细" name="rent_income_detail" iconCls="icon-node">
			<jsp:include page="comm/factoring_rent_income_detail.jsp" ></jsp:include>
		</div>
		<div title="网银导入" name="ebank_import" iconCls="icon-node">
			<jsp:include page="comm/factoring_ebank_import.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>				
		<div title="保理债权回笼计划" name="rent_income_plan" iconCls="icon-node">
			<jsp:include page="comm/factoring_rent_income_plan.jsp" ></jsp:include>
		</div>
		<div title="保理债权回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="comm/factoring_rent_income_his.jsp" ></jsp:include>
		</div>
		
	</div>
</div>
<script>
jQuery(function(){
	miniui_ext.initformenabled("ebank_import_form");
	miniui_ext.initformenabled("contract_base_info_form");
});
//提交检查
function submitCheck(){
	var currData = mini.get("rent_income_detail").getData();
	var totalHireRent = 0;
	for(var i = 0;i < currData.length;i ++){
		totalHireRent += Number(currData[i].rent);
	}
	totalHireRent = decimal(totalHireRent,2);
	var mayopenmoney = mini.get('fund_ebank_data.mayopemoney').getValue();
	if(totalHireRent > mayopenmoney){
		alert("回笼债权不能大于网银可核销金额!");
		return false;
	}else{
		return true;
	}
}
</script>