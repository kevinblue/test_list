<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/validator.js"></script>
	<%String id = request.getParameter("custid");%>
<script type="text/javascript">
var projid ="<%=id%>";

	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		 
		 try{
		     mini.get('json_small_business_str').setValue(JsonUtil.encode(getTracywindyObject("small_business_data").getTableAllSavedConfigJson())); 
		 }catch (e){
			 
		 }
		 try{
			 mini.get('json_big_business_str').setValue(JsonUtil.encode(getTracywindyObject("big_business_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
		 try{
			 mini.get('json_supplier_str').setValue(JsonUtil.encode(getTracywindyObject("supplier_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
/* 		miniui_ext.saveIncludeData("tabDetailsBase");
		miniui_ext.saveIncludeData("tabApprovalDeatils"); */
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
		     mini.get('json_small_business_str').setValue(JsonUtil.encode(getTracywindyObject("small_business_data").getTableAllSavedConfigJson())); 
			 
		 }catch (e){
			
		 }
		 try{
			 mini.get('json_big_business_str').setValue(JsonUtil.encode(getTracywindyObject("big_business_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
		 try{
			 mini.get('json_supplier_str').setValue(JsonUtil.encode(getTracywindyObject("supplier_data").getTableAllSavedConfigJson()));
		 }catch (e){
			 
		 }
		//if (miniui_ext.submitFormValidation(["cust_base_info_form"]) == false) return false;
        //miniui_ext.saveIncludeData("tabApprovalDeatils");
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
		 <input id="all_Score"  class="mini-texbox"  name="all_Score" type="text" style="display:none;"
		value='${empty all_Score ? "":all_Score}'>
	 </div>
 	 <div class="mini-tabs" activeIndex="0" style="width: 100%"
		id="tabDetailsBase" onactivechanged="changTab">	
			<c:choose>
				<c:when  test="${requestScope['cust_info.custscale'] =='小型企业'}">
					<div title="小型企业评分" iconCls="icon-cut"> 
						<jsp:include page="common/small_business.jsp">
						<jsp:param value="true" name="isView"/>
						</jsp:include>
					</div>  
				</c:when>  
				<c:when  test="${requestScope['cust_info.custscale'] =='大中型企业'}">
					<div title="大中型企业评分" iconCls="icon-cut"> 
						<jsp:include page="common/big_business.jsp">
						<jsp:param value="true" name="isView"/>
						</jsp:include>
					</div> 
				</c:when>
				<c:when  test="${requestScope['cust_info.custscale'] =='供应商'}">
					<div title="供应商评级" iconCls="icon-cut"> 
						<jsp:include page="common/supplier.jsp">
						<jsp:param value="true" name="isView"/>
						</jsp:include>
					</div> 
				</c:when>
			</c:choose>   
			<div title="历史评级记录" id="id_table_history_grade" iconCls="icon-cut"> 
					<jsp:include page="common/history_grade.jsp"></jsp:include>
			</div> 
	</div> 
</div>
<script type="text/javascript">
</script>

