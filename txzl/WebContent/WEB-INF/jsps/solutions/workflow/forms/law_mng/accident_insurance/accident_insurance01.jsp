<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
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
		miniui_ext.saveIncludeData("tabDetails");
		if (miniui_ext.submitFormValidation(["contract_accident_form"]) == false) return false;
		return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_accident_info_str" name="json_accident_info_str" value='${empty json_accident_info_str ? "[]" : json_accident_info_str }'></input>
<div id="contract_accident_form">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td>
				 <jsp:include page="../../contract/contract_inspection/contract_patrol_info.jsp"><jsp:param value="true" name="isView" /></jsp:include></td>
			</tr>
		</table>
	</div>
		
<div class="fillTableContainer" style="display:none">
		<div title="项目概述" name="accident_insurrance_detail" iconCls="icon-node">
			<jsp:include page="accident_insurrance_info.jsp"></jsp:include>
		</div>
	</div>
	<div id="tabDetails" class="mini-tabs"  activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="存续项目" name="accident_subsist_project" iconCls="icon-node">
			<jsp:include page="accident_subsist_project.jsp"></jsp:include>
		</div>
		<div title="预警出险原因及风险分析" name="contract_equip" iconCls="icon-node">
			<jsp:include page="accident_occus_info.jsp" ></jsp:include>
		</div>
		<div title="风险对抗措施" name="business_condition" iconCls="icon-node">
			<jsp:include page="accident_confrontation_info.jsp" ></jsp:include>
		</div>
		<div title="处理预案建议" name="otherBusinessCondition" iconCls="icon-node">
			<jsp:include page="accident_planadvice_info.jsp" ></jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
				<jsp:include page="../../contract/contract_comm/contract_guarantee_method.jsp" >
				<jsp:param value="true" name="isView"/>
				</jsp:include>
		</div>
		<div title="生成报告" name="accident_info"   iconCls="icon-cut" >
		    	 <jsp:include page="accident_info_reportxls.jsp" ></jsp:include>
		</div> 	
	</div>
</div>
