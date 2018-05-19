<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabDetailsBase");
		saveReportTable();
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
	   if (miniui_ext.submitFormValidation(["proj_base_info_form","id_proj_credit_isapproval","proj_rent_invoice_type"]) == false) return false;
		var nextValue=mini.get('creditisapproval').getValue();
		var creditManager = mini.get('proj_info.creditmanager').getValue();
		if(nextValue=="是"){
		    if(!creditManager){mini.alert('请选择风控经办！');return false;
		}}
        miniui_ext.saveIncludeData("tabDetailsBase");
        saveReportTable();
		return true;
	}
	
	//提交路由，选择下一步的节点
	function workflowNextRouteCallBack(buttonText,requestNextRoute){
		if(buttonText == "Submit"){
			var nextValue=mini.get('creditisapproval').getValue();
			requestNextRoute.value = nextValue;
		}
	}
</script>
	<jsp:include page="../proj_credit/common/proj_credit_publish.jsp"></jsp:include>
	<jsp:include page="../proj_credit/common/proj_credit_hidden_info.jsp"></jsp:include>
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
						<!-- 插入项目信审信息 -->
						<td colspan=4> <jsp:include page="../proj_credit/common/proj_credit_isapproval.jsp"></jsp:include></td>
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
			<div title="信审评分" name="proj_guaranty_detail" iconCls="icon-cut">
				<span class="print-tabs-title-content">信审评分表</span>
				<jsp:include page="../proj_credit/common/credit_grade.jsp">
				       <jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			 <c:choose>
	   			<c:when test="${requestScope['rawValue_proj_info.dictionarydatabyindustrytype'] =='医疗'}">
	   				<div title="尽调报告（医疗）" name="medicalDetail"    iconCls="icon-cut" >
						  <jsp:include page="../proj_credit/common/proj_company_report.jsp" >
								<jsp:param value="true" name="isView"/>
						 </jsp:include>
	   				</div>
	   			</c:when>
	   			<c:otherwise>
	   				<c:choose>
	   					<c:when test="${requestScope['proj_info.custclass'] =='CUST_INFO_PERSON'}">
	   						<div title="尽调报告（自然人）" id="frm1" name="personDetail"   iconCls="icon-cut"  >
				  				<jsp:include page="../proj_credit/common/proj_person_report.jsp" >
									<jsp:param value="true" name="isView"/>
				 				</jsp:include>
		   					</div>
	   					</c:when>
	   					<c:otherwise>
	   						<div title="尽调报告（法人）" name="companyDetail"    iconCls="icon-cut" >
		   		 				<jsp:include page="../proj_credit/common/proj_company_report.jsp" >
									<jsp:param value="true" name="isView"/>
						 		</jsp:include>
		   		 			</div>
	   					</c:otherwise>
	   				</c:choose>
	   			</c:otherwise>
	   		</c:choose> 
		   <div title="文件清单" name="proj_file_detail"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_credit/common/proj_person_report_fileinfo.jsp" > 
		    	        <jsp:param value="true" name="isView"/>
		    	 </jsp:include>
		    </div>
		    <div title="生成客户信审报告" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_credit/common/proj_due_reportxls.jsp" >
						<jsp:param value="true" name="isView"/>
				 </jsp:include>
		    </div> 
	   </div>
	</div>

