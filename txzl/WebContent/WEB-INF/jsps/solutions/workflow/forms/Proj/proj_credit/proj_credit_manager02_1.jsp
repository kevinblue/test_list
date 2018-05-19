<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//项目经理修改项目信息
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveData("tabDetailsBase");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		var flag = true;
		
		//保存投票信息
        var cflag=miniui_ext.submitFormValidation(["id_project_vote"]);
		if (cflag== false) return false;	 
        //获取审批结论下拉框是否填写信息，如果填写，则进行保存。
        if(mini.get('id_selectAdvise_combo').getValue()!=""){
	        var vflag = saveVote();
        }
       /*  
		 if(checkAttachmentFilesIsUpload() == false){
		if(confirm("您还未上传尽调报告，确定不上传吗？")){
			return true;
			}else{
			return false;
			}
		   }else{
			   flag=true; 
		   } */
		
     	miniui_ext.saveIncludeData("tabDetailsBase"); 
     	return flag&&vflag;
	}
 	function checkAttachmentFilesIsUpload(){
		var dictIdArr = ['FinancialAdjustment.01'];
		return checkAttachmentIsNull("'" + dictIdArr +　"'");
	}  

	//起租文件校验
 	 function checkAttachmentIsNull(fileDictIds){
		var flag = false;
		var param = {};
		param.xmlFileName = '/jbpm/queryWorkflowFilesFinancialIsExists.xml';
		param.dbid = "${currentProcessInstanceDBID}";
		param.dictids = fileDictIds;
		param.is_must_="1";
		ajaxRequest({
			url : '${pageContext.request.contextPath}/table/getTableData.action',
			method : 'POST',
			success : function(res){
				var scustinfo = res.responseText;
				scustinfo = scustinfo.replace(/(^\s+)|(\s+$)/g, "");
				//console.info(scustinfo);
				var obj =mini.decode(scustinfo);
				if(obj.datas.length==0){
					flag=false;
				}else{
					flag=true;
				}
				
			},
			async : false,
			failure : function(res) {
			},
			params : param
		});
		return flag; 

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
					<tr>
						<!-- 插入项目信审信息 -->
						<td colspan=4> <jsp:include page="../../Proj/proj_credit/common/proj_credit_committee2.jsp"></jsp:include></td>
					</tr>
			</table>
			<!-- 隐藏域，判断是否为风电项目 -->
			<table style="display: none;"  cellspacing="0" cellpadding="0">
						<td class="td-content-title">：</td>
						<td class="td-content"><input name="type" id ="type"  class="mini-textbox" value="${empty requestScope['type'] ? 0 : requestScope['type'] }" readonly></td>
			</table>
		</div>

		<div class="mini-tabs" activeIndex="0"   style="width: 99%" id="tabDetailsBase"  onactivechanged="changTab">
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
			<div title="抵质押物信息" name="proj_guaranty_detail" iconCls="icon-cut">
				<jsp:include page="../proj_common/proj_guaranty_detail.jsp" >
				<jsp:param value="true" name="isView"/>
				</jsp:include>
			</div>
			<div title="租金开票类型" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_common/proj_rent_invoice_type.jsp" >
		    	 <jsp:param value="true" name="isView"/>
		    	 </jsp:include>
		    </div>  
		     <c:if test="${requestScope['rawValue_proj_info.industrytype']=='风力发电'}">
		    
		     <div title="经评模型" name="credit_report"   iconCls="icon-cut" >
		       <table class="fillTable" cellspacing="0" cellpadding="0">
		            <tr>
			            <td colspan=4>
				           <jsp:include page="../proj_common/create_report_list.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		                </td>
		            </tr>
		            <tr>
						 <td colspan=4>
							<div class="mini-panel" title="输出结果" showCollapseButton="true" style="width: 100%;">
							  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
								  	 <div title="敏感性分析" name="fund_put_plan" iconCls="icon-cut" >
										 <jsp:include page="../proj_common/evaluation_model_statistics_1.jsp" ></jsp:include>
									</div> 
								  	 <div title="偿债覆盖率（DSCR）" name="fund_rent_plan" iconCls="icon-cut" >
										 <jsp:include page="../proj_common/evaluation_model_statistics_2.jsp" ></jsp:include>
									</div> 
								</div>
							</div>
						</td> 
		             </tr> 
	           </table>
		    </div> 
		     </c:if>
		    <div title="尽调报告" name="proj_guarantee_detai2" iconCls="icon-cut">
	     <jsp:include page="../proj_common/upload_due_diligence_report.jsp" >
	        <jsp:param value="尽调报告" name="tab_name"/>
	        <jsp:param value="DueDiligenceReport01" name="tab_key"/>
	     </jsp:include>
	     
	       </div>
		    
		    
		    
		    
			<%-- <div title="尽调报告" name="credit_report_list" iconCls="icon-cut">
				<jsp:include page="credit_report/credit_report_page2.jsp"></jsp:include>
			</div> --%>
			
			<%-- <div title="评估报告" name="proj_credit_report" iconCls="icon-cut">
				<jsp:include page="credit_report/proj_credit_report.jsp"><jsp:param value="true" name="isView"/></jsp:include>
			</div> --%>
			<!-- 
		    <div title="评审会议记录" name="proj_file_detail"   iconCls="icon-cut" >
		    	<jsp:include page="common/proj_meeting_record.jsp" ></jsp:include>
		    </div>
			 -->
			<c:choose>
				<c:when  test="${requestScope['type'] =='1'}">
					<div title="项目核准文件" iconCls="icon-cut" name="power_info"> 
						<jsp:include page="credit_report/wind_power_info.jsp">
							<jsp:param value="true" name="isView"/>
						</jsp:include>
					</div>  
				</c:when>  
			</c:choose>
			<div title="项目建议书" name="proj_credit_proposal" iconCls="icon-cut" >
				<jsp:include page="credit_report/proj_credit_proposal.jsp" >
					<jsp:param value="true" name="isView"/>
				</jsp:include>
				
			</div>
	   </div>
	</div>
<script type="text/javascript">
	jQuery(function(){
	    $("#tabDetailsBase").css("width",globalClientWidth - 26);
	});
</script>	