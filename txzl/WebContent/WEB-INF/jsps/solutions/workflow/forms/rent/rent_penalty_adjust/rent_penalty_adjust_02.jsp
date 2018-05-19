<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<jsp:include page="../../fund/fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
isViewHistoryTask = true;
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
	var form = new mini.Form("#rent_penalty_adjust_form");
	form.validate();
	if (form.isValid() == false) return;
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//提交路由，选择下一步的节点
function workflowNextRouteCallBack(buttonText,requestNextRoute){
	if(buttonText == "Submit"){
		var allpenalty = mini.get("rent_penalty_adjust_detail").getSummaryCellEl("penaltyadjust").innerHTML;
		allpenalty = Number(allpenalty.replace(",",""));
		if(allpenalty>=500){
			requestNextRoute.value= '500及以上';
		}else {
			requestNextRoute.value= '500以下';
		}
	}
}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_plan_str" name="json_rent_income_plan_str" value='${empty json_rent_income_plan_str ? "[]" : json_rent_income_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_his_str" name="json_rent_income_his_str" value='${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_penalty_adjust_detail_str" name="json_rent_penalty_adjust_detail_str" value='${empty json_rent_penalty_adjust_detail_str ? "[]" : json_rent_penalty_adjust_detail_str }'></input>
<div id="rent_penalty_adjust_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><!-- 插入合同基本信息 --> <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%">
		<div title="租金回笼计划" name="rent_income_plan" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_penalty_adjust_plan.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金回笼历史" name="rent_income_his" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_income_his.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="罚息减免明细" name="rent_penalty_adjust_detail" iconCls="icon-node">
			<jsp:include page="../rent_comm/rent_penalty_adjust_detail.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="罚息减免备注" name="rent_penalty_adjust_remark" iconCls="icon-node">
			<jsp:include page="rent_penalty_adjust_remark.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
	</div>
</div>
<script>
jQuery(function(){
	miniui_ext.initformenabled("contract_base_info_form");
	miniui_ext.initformenabled("contract_rent_invoice_type_form");
});
//存储第一步内容
</script>