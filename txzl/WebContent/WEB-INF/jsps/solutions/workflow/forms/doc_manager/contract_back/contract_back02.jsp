<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{
		//miniui_ext.saveIncludeData("tabDetails");
		return true;
	}
</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_contract_back_str" 	name="id_json_contract_back_str" value='${empty id_json_contract_back_str ? "[]" : id_json_contract_back_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_fundput_file_borrow_str" 	name="json_fundput_file_borrow_str" value='${empty json_fundput_file_borrow_str ? "[]" : json_fundput_file_borrow_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>
<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="本次归还" name="contract_back" iconCls="icon-cut" >
		 <jsp:include page="contract_back_present.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div title="历史借阅" name="contract_back" iconCls="icon-cut" >
		 <jsp:include page="contract_back_his.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
	</div>
</div>