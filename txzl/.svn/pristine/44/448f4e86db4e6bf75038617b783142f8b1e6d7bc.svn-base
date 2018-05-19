<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
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
		return true;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_ebank_info_str" name="json_ebank_info_str" value='${empty json_ebank_info_str ? "[]" : json_ebank_info_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_plan_str" 	name="json_collection_plan_str" value='${empty json_collection_plan_str ? "[]" : json_collection_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_current_str" 	name="json_collection_current_str" value='${empty json_collection_current_str ? "[]" : json_collection_current_str }'></input>
<div id="contract_approval_form">
	<div class="fillTableContainer"> 
		<jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
	</div>
 <div  id="tabDetails"  class="mini-tabs"  activeIndex="0" style="width: 100%;" bodyStyle="border:0px;">  
 <div title="变更信息" id="changeDeatils" name="changeinfo" iconCls="icon-node">
  		<c:choose>
				<c:when  test="${requestScope['contract_info.leasform'] == 'lease_form1'||requestScope['contract_info.leasform'] == 'leas_form7'}">
						<jsp:include page="../../fund/fund_comm/message_todirect.jsp"></jsp:include>
				</c:when>
				<c:when  test="${requestScope['contract_info.leasform'] == 'lease_form2'||requestScope['contract_info.leasform'] == 'lease_form6'}">
						<jsp:include page="../../fund/fund_comm/message_toleaseback.jsp"></jsp:include>
				</c:when>   
	  </c:choose> 
 </div> 
  <div title="历史信息" id="changeDeatils" name="changeinfo" iconCls="icon-node">
 <c:choose>
				<c:when  test="${requestScope['contract_info.leasform'] == 'lease_form1'||requestScope['contract_info.leasform'] == 'leas_form7'}">
						<jsp:include page="history_todirect.jsp"><jsp:param value="true" name="isView"/></jsp:include>
				</c:when>
				<c:when  test="${requestScope['contract_info.leasform'] == 'lease_form2'||requestScope['contract_info.leasform'] == 'lease_form6'}">
						<jsp:include page="history_toleaseback.jsp"><jsp:param value="true" name="isView"/></jsp:include>
				</c:when>   
	  </c:choose>  
	  </div>
 </div>
</div>		

