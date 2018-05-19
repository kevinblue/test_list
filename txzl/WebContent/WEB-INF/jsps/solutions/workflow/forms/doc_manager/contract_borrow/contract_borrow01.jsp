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
	function workflowSubmitCallBack(buttonText){
		if (miniui_ext.submitFormValidation(["div_limit_info_form","div_object_info_form","id_table_contract_borrow_present"]) == false) return false;
	   //if (miniui_ext.submitFormValidation(["id_table_contract_borrow_present"]) == false) return false;
		if("" ==mini.get("contract_borrow").getData()){
			mini.alert("请添加借阅文件再提交！");
			return false;
			} 
	   miniui_ext.saveIncludeData("tabDetails"); 
		return true;
	}
</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_contract_borrow_str" 	name="json_contract_borrow_str" value='${empty json_contract_borrow_str ? "[]" : json_contract_borrow_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_fundput_file_borrow_str" 	name="json_fundput_file_borrow_str" value='${empty json_fundput_file_borrow_str ? "[]" : json_fundput_file_borrow_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	   <tr>
			<td >
				<!-- 借阅类型 --> 
				<jsp:include page="comm/contract_borrow_borrowingtype.jsp"></jsp:include>
			</td>
			<td >
				<!-- 借阅对象 --> 
				<jsp:include page="comm/contract_borrow_borrowingobject.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</div>
<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%" bodyStyle="border:0px;">
	<div title="当前借阅" id="contract_borrow" name="contract_borrow" iconCls="icon-cut" >
		 <jsp:include page="comm/contract_borrow_present.jsp" ></jsp:include>
	</div>
	<div title="历史借阅" name="contract_borrow" iconCls="icon-cut" >
		 <jsp:include page="../contract_back/contract_back_his.jsp" ></jsp:include>
	</div>
</div>