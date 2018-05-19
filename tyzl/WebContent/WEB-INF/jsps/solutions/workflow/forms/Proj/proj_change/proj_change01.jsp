<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
	//流程保存之前回调
	function workflowSaveCallBack()
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		 if (miniui_ext.submitFormValidation(["proj_base_info_form","proj_rent_invoice_type","proj_change_form"]) == false) return;
	        if(checkEquipIsNull()==false) return;
	        miniui_ext.saveIncludeData("tabApprovalDeatils");
			return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='<mini:param  name="json_proj_equip_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='<mini:param  name="json_proj_guarantee_detail_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='<mini:param  name="json_proj_guaranty_detail_str" defaultValue="[]"/>'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../proj_common/proj_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width:99.9%;">
	<div title="变更说明" name="change_detail" iconCls="icon-cut">
		<jsp:include page="proj_change_detail.jsp" ></jsp:include>
	</div>
	<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
		 <jsp:include page="../proj_common/proj_equip_detail.jsp" ></jsp:include>
	</div>
	<div title="商务条件" name="business_condition" iconCls="icon-cut">
		<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ></jsp:include>
	</div>
	<div title="其他商务条件" name="other_business_condition" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_other_condition.jsp" ></jsp:include>
	</div>
	<div title="担保单位信息" name="proj_guarantee_detail" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_guarantee_detail.jsp" ></jsp:include>
	</div>
	<div title="抵押物信息" name="proj_guaranty_detail" iconCls="icon-cut" >
	     <jsp:include page="../proj_common/proj_guaranty_detail.jsp" ></jsp:include>
	</div>
	<div title="租金发票类型" name="rent_invoice_type" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_rent_invoice_type.jsp" ></jsp:include>
	</div>
</div>
