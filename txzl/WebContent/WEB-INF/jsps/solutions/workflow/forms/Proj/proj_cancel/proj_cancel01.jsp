<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		 if (miniui_ext.submitFormValidation(["proj_change_form"]) == false) return;
	       // if(checkEquipIsNull()==false) return;
	        miniui_ext.saveIncludeData("tabApprovalDeatils");
			return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='${empty json_proj_guarantee_detail_str ? "[]" : json_proj_guarantee_detail_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='${empty json_proj_guaranty_detail_str ? "[]" : json_proj_guaranty_detail_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../proj_common/proj_base_info.jsp"> <jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
	<div title="撤销说明" name="change_detail" iconCls="icon-cut">
		 <jsp:include page="proj_cancel_detail.jsp" ></jsp:include>
	</div>
	<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
		 <jsp:include page="../proj_common/proj_equip_detail.jsp" >
		    <jsp:param value="true" name="isView"/>
		 </jsp:include>
	</div>
	<div title="商务条件" name="business_condition" iconCls="icon-cut">
	     <jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" > <jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div title="其他商务条件" name="other_business_condition" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_other_condition.jsp" > <jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div title="担保单位信息" name="proj_guarantee_detail" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_guarantee_detail.jsp" >
	       <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="抵质押物信息" name="proj_guaranty_detail" iconCls="icon-cut" >
	     <jsp:include page="../proj_common/proj_guaranty_detail.jsp" >
	       <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="项目报价方案" name="quotation_scheme" iconCls="icon-node" >
			<jsp:include page="../proj_common/proj_quotation_scheme.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
	</div> 
</div>
