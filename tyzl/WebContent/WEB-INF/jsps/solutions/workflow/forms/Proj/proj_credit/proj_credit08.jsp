<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabDetailsBase");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
	   if (miniui_ext.submitFormValidation(["proj_base_info_form","id_proj_credit_isapproval"]) == false) return false;
		 
        miniui_ext.saveIncludeData("tabDetailsBase");
        if(mini.get("proj_info.creditisapproval").getValue()=="credit_type_@11"&&(mini.get("approvedate").getValue()==null||mini.get("approvedate").getValue()==""))
        {
        	mini.alert("审批通过，信审通过时间必填。")
        	return false;
        }
		return true;
	}
	function workflowNextRouteCallBack(buttonText,requestNextRoute) 
	{
		if(mini.get("proj_info.creditisapproval").getValue()=="credit_type_@104")
			requestNextRoute.value="审批否决"
		else
			requestNextRoute.value="审批通过"
	}
	
</script>
	<jsp:include page="common/proj_credit_publish.jsp"></jsp:include>
	<jsp:include page="common/proj_credit_hidden_info.jsp"></jsp:include>
	<div id="contract_approval_form">
		<div class="fillTableContainer">
			<table class="fillTable" cellspacing="0" cellpadding="0">
					<tr>
						<!-- 插入项目基本信息 -->
						<td> 
						     <jsp:include page="../proj_common/proj_base_info.jsp">
						           <jsp:param value="true" name="isView"/>
						     </jsp:include></td>
					</tr>
			</table>
		</div>
		<div class="mini-tabs" activeIndex="0"   style="width: 100%;" id="tabDetailsBase"  onactivechanged="changTab">
			<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_equip_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="商务条件" name="conditionDetail" iconCls="icon-cut" >
				<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" > 
				        <jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="其他商务条件" name="other_business_condition" iconCls="icon-cut">
	    		 <jsp:include page="../proj_common/proj_other_condition.jsp" >
	    		        <jsp:param value="true" name="isView"/>
	    		 </jsp:include>
			</div>
			<div title="担保单位信息" name="proj_guarantee_detail" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_guarantee_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="抵押物信息" name="proj_guaranty_detail" iconCls="icon-cut">
				<jsp:include page="../proj_common/proj_guaranty_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		        <jsp:include page="../proj_common/proj_union_cust.jsp" >
		        	<jsp:param value="true" name="isView"/>
		        </jsp:include>
	        </div>
		      <div title="信审结论" name="proj_file_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_credit_info.jsp"></jsp:include>
		    </div>
		   <permission:action code="votepermission">
	   		<div title="意见汇总" name="proj_file_detail"   iconCls="icon-cut" >
				    	<jsp:include page="common/proj_credit_committee_final.jsp">
				    		   <jsp:param value="true" name="isView"/>
				    	</jsp:include>
		   </div> 	
		   </permission:action>
	   </div>
	</div>
<script type="text/javascript">
	jQuery(function(){
	    $("#tabDetailsBase").css("width",globalClientWidth - 26);
	});
</script>
