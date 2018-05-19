<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
	
	<%String id = request.getParameter("custid");%>
<script type="text/javascript">
var projid ="<%=id%>";

	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		 try{
			 mini.get('json_cust_grade_str').setValue(JsonUtil.encode(getTracywindyObject("cust_grade_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{
		 try{
			 mini.get('json_cust_grade_str').setValue(JsonUtil.encode(getTracywindyObject("cust_grade_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
		 if (miniui_ext.submitFormValidation(["cust_grade_form"]) == false) return false;
		return true;
	}

	
</script>
<div id="contract_approval_form">
	<div class="fillTableContainer">
		 <table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td><jsp:include page="cust_grade_info.jsp"></jsp:include></td>
			</tr>
		</table> 
		 <input id="all_Score"  class="mini-texbox"  name="all_Score" style="display:none;"
		value='${empty all_Score ? "":all_Score}'>
	
	 </div>
 	 <div class="mini-tabs" activeIndex="0" style="width: 100%"
		id="tabDetailsBase" onactivechanged="changTab">	
			<div title="客户信用评级" iconCls="icon-cut"> 
						<jsp:include page="common/cust_grade_new.jsp"></jsp:include>
			</div> 
			<div title="客户评价" iconCls="icon-cut"> 
						<jsp:include page="common/cust_evaluate.jsp"></jsp:include>
			</div>
			<div title="历史评级记录" id="id_table_history_grade" iconCls="icon-cut"> 
					<jsp:include page="common/history_grade.jsp"></jsp:include>
			</div> 
			<div title="生成客户评价" iconCls="icon-cut"> 
						<jsp:include page="common/cust_grade_word.jsp"></jsp:include>
			</div>
	</div> 
</div>
<script type="text/javascript">
</script>

