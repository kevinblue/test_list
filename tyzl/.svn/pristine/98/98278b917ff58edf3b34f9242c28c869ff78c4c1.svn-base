<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveData("tabDetailsBase");
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
		var cflag=miniui_ext.submitFormValidation(["id_proj_board_metting","id_proj_credit_isapproval"]);
		if (cflag== false) return false;	
        miniui_ext.submitData("tabDetailsBase");
        saveReportTable();
		return true;
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
						     </jsp:include>
						</td>
					</tr>
			</table>
		</div>

		<div class="mini-tabs" activeIndex="0"   style="width: 99%" id="tabDetailsBase"  onactivechanged="changTab">
			<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_equip_detail.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="商务条件" name="conditionDetail" iconCls="icon-cut" >
				<jsp:include page="../../reckon/rent_readonly/main_5_1.jsp" >
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
			 <div title="租金发票类型" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_common/proj_rent_invoice_type.jsp" >
		    	      <jsp:param value="true" name="isView"/>
		    	 </jsp:include>
		    </div> 
			 <c:choose>
	   			<c:when test="${requestScope['rawValue_proj_info.dictionarydatabyindustrytype'] =='医疗'}">
	   				<div title="尽职调查报告（医疗）" name="medicalDetail"    iconCls="icon-cut" >
						  <jsp:include page="common/proj_company_report.jsp" >
								<jsp:param value="true" name="isView"/>
						 </jsp:include>
	   				</div>
	   			</c:when>
	   			<c:otherwise>
	   				<c:choose>
	   					<c:when test="${requestScope['proj_info.custclass'] =='CUST_INFO_PERSON'}">
	   						<div title="尽职调查报告（自然人）" id="frm1" name="personDetail"   iconCls="icon-cut"  >
				  				<jsp:include page="common/proj_person_report.jsp" >
									<jsp:param value="true" name="isView"/>
				 				</jsp:include>
		   					</div>
	   					</c:when>
	   					<c:otherwise>
	   						<div title="尽职调查报告（法人）" name="companyDetail"    iconCls="icon-cut" >
		   		 				<jsp:include page="common/proj_company_report.jsp" >
									<jsp:param value="true" name="isView"/>
						 		</jsp:include>
		   		 			</div>
	   					</c:otherwise>
	   				</c:choose>
	   			</c:otherwise>
	   		</c:choose> 
		   <div title="文件清单" name="proj_file_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_person_report_fileinfo.jsp" >
		    	     <jsp:param value="true" name="isView"/>
		    	</jsp:include>
		    </div>
		    
		    <div title="生成客户信审报告" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="common/proj_credit_reportxls.jsp" >
						<jsp:param value="true" name="isView"/>
				 </jsp:include>
		    </div>
		    <div title="评审会议记录" name="proj_file_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_meeting_record.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		    </div>
		    <div title="董事会会议记录" name="proj_board_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_meeting_board_record.jsp" ></jsp:include>
		    </div>
		    <div title="信审结论" name="proj_file_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_credit_info.jsp"></jsp:include>
		    </div>
	   </div>
	</div>
<script type="text/javascript">
	jQuery(function(){
	    $("#tabDetailsBase").css("width",globalClientWidth - 26);
	});
</script>	