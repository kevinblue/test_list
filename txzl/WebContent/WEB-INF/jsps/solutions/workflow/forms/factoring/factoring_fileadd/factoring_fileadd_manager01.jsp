<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
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
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{	
		if (miniui_ext.submitFormValidation(["proj_factoring_base_info_form"]) == false||miniui_ext.submitFormValidation(["fileadd"]) == false) return false;
        miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
</script>
<!--多行控件  -->
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入保理业务基本信息 --> 
			 <jsp:include page="../factoring_comm/factoring_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>  
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="资料补充" name="factoring_fileadd" iconCls="icon-cut" >
		 <jsp:include page="fileadd.jsp" ></jsp:include>
	</div>
	
</div>