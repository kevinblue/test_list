<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//是否保存   
	//12委员会上会
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveData("tabDetailsBase");
		//saveReportTable();
		var flag = saveVote();
        return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{   
		var cflag=miniui_ext.submitFormValidation(["id_project_vote"]);
		if (cflag== false) return false;	 
        miniui_ext.submitData("tabDetailsBase");
        //saveReportTable();
		var flag = saveVote();
        return true;
	}
</script>
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
					<tr>
						<td colspan=4> <jsp:include page="../proj_credit/common/proj_limit_choose.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
					</tr>
					<tr>
						<td colspan=4> <jsp:include page="../proj_credit/common/proj_choose_fkjb.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
					</tr>
					<tr>
						<!-- 插入项目信审信息 -->
						<td colspan=4> <jsp:include page="../proj_credit/common/proj_credit_committee.jsp"></jsp:include></td>
					</tr>
			</table>
		</div>
		<div class="mini-tabs" activeIndex="0"   style="width: 100%" id="tabDetailsBase"  onactivechanged="changTab">
			   <div title="项目变更说明" name="proj_change_explain" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_change_explain.jsp">
				<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			
			<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_equip_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="商务条件" name="conditionDetail" iconCls="icon-cut" >
				<jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" ></jsp:include>
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
			<div title="抵质押物信息" name="proj_guaranty_detail" iconCls="icon-cut">
				<jsp:include page="../proj_common/proj_guaranty_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			 <div title="租金发票类型" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_common/proj_rent_invoice_type.jsp" >
		    	      <jsp:param value="true" name="isView"/>
		    	 </jsp:include>
		    </div> 
			<div title="尽调报告" name="credit_report_list" iconCls="icon-cut">
				<jsp:include page="../proj_credit/credit_report/credit_report_list.jsp"><jsp:param value="true" name="isView"/></jsp:include>
			</div>
			<div title="评估报告" name="proj_credit_report" iconCls="icon-cut">
				<jsp:include page="../proj_credit/credit_report/proj_credit_report.jsp"><jsp:param value="true" name="isView"/></jsp:include>
			</div>
			<div title="生成风控清单" name="proj_risk_list" iconCls="icon-cut" >
		    	<jsp:include page="../proj_credit/credit_report/common/proj_risk_list.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		    </div>
	   </div>
	</div>