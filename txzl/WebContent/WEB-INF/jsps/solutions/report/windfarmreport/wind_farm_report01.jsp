<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<input name="reportDate" id="reportDate"     style="display:none;"      value="${requestScope['reportdate'] }"/>
<input name="reportkeyword" id="reportkeyword"  style="display:none;"   value="${requestScope['reportkeyword'] }"/>
<input name="selectedprojid" id="selectedprojid" style="display:none;"  value="${requestScope['selectedprojid'] }"/> 
<input name="login_username" id="login_username" style="display:none;"  value="${requestScope['login_username'] }"/> 
<!-- 流程关键字 -->
<input name="reportkeywordview" id="reportkeywordview" style="display:none;"  value="${requestScope['reportkeywordview'] }"/> 
<script type="text/javascript">
var reportdate = $("#reportDate").val();
var reportkeyword =$("#reportkeyword").val();
var selectedprojid = $("#selectedprojid").val();
var loginusername = $("#login_username").val();
 //是否保存
function workflowSaveCallBack() {
	 
	  miniui_ext.saveIncludeData("tabDetails");   
	return true;
} 
//保存成功提交后，后台返回
 function saveCallBack() {
	return true;
} 
</script>
	 <div class="fillTableContainer"> 
	        <div title="风电场报告" iconCls="icon-cut"> 
	        <c:choose>
				<c:when  test="${requestScope['reportkeyword']=='年度报告'}">
						<jsp:include page="wind_farm_baseinfo_year.jsp"></jsp:include>
				</c:when>  
				 <c:when  test="${requestScope['reportkeyword']=='月度报告'}"> 
                        <jsp:include page="wind_farm_baseinfo.jsp"></jsp:include>
                 </c:when> 
			</c:choose>  
	        </div>  
	 </div>
	
	
	
	
	
 <div  id="tabDetails"  class="mini-tabs"  activeIndex="0" style="width: 100%;" bodyStyle="border:0px;">  
		<c:if  test="${requestScope['reportkeyword']=='年度报告'}">
		       <div title="项目信息" name=wind_farm_data_list_year iconCls="icon-cut">
						<jsp:include page="wind_farm_data_list.jsp" ></jsp:include>
			   </div> 
		</c:if> 
		<c:if  test="${requestScope['reportkeyword']=='月度报告'}">
		      <div  title="风电场各台风机当月运行报表" iconCls="icon-node">
				<div id="conditionDeatils1" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="机组运行数据" name="unit_operation_data" iconCls="icon-cut">
							<jsp:include page="fan_operation_report_1.1.jsp" ></jsp:include>
						</div> 
					  	 <div title="机组故障数据" name="unit_failure_data" iconCls="icon-cut">
							<jsp:include page="fan_operation_report_1.2.jsp" ></jsp:include>
						</div> 
						<div title="损失电量数据" name="loss_data" iconCls="icon-cut">
							<jsp:include page="fan_operation_report_1.3.jsp" ></jsp:include>
						</div>  
					</div>
			  </div>
				<div  title="全场累计各月运行报表" iconCls="icon-node">
					<div id="conditionDeatils2" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
						  	<div title="机组运行数据" name="cumulative_operating_report1" iconCls="icon-node">
				                <jsp:include page="cumulative_operating_report_1.1.jsp" ></jsp:include>
			                </div>
						  	<div title="机组故障数据" name="cumulative_operating_report2" iconCls="icon-cut">
								<jsp:include page="cumulative_operating_report_1.2.jsp" ></jsp:include>
							</div> 
							<div title="损失电量数据" name="cumulative_operating_report3" iconCls="icon-cut">
								<jsp:include page="cumulative_operating_report_1.3.jsp" ></jsp:include>
				            </div>
				   </div>
			  </div>
		</c:if> 
		<c:if  test="${requestScope['reportkeyword']=='年度报告'}">
		   <div  title="各风电场当年运行报表" iconCls="icon-node">
				<div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="机组运行数据" name="cumulative_operating_report_year1" iconCls="icon-node">
			                 <jsp:include page="cumulative_operating_report_year1.1.jsp" ></jsp:include>
		                 </div>
					  	<div title="机组故障数据" name="cumulative_operating_report_year2" iconCls="icon-cut">
							<jsp:include page="cumulative_operating_report_year1.2.jsp" ></jsp:include>
						</div> 
						<div title="损失电量数据" name="cumulative_operating_report_year3" iconCls="icon-cut">
							<jsp:include page="cumulative_operating_report_year1.3.jsp" ></jsp:include>
		                </div> 
				</div> 
		   </div>
		</c:if> 
		<div title="运行状况分析" name="proj_change_explain" iconCls="icon-cut" >
				<jsp:include page="operation_status_analysis.jsp"></jsp:include>
	    </div>
		<div title="运行报告结论" name="summary" iconCls="icon-cut" >
		        <jsp:include page="summary.jsp"></jsp:include>
	    </div> 
	    <div title="风电场运行报表" name="onhire_report" iconCls="icon-node" >
		    <table class="fillTable" cellspacing="0" cellpadding="0">
		      <tr>
			    <td colspan=4>
				 	<%-- <div id="segment_config" class="mini-panel" title="风电场运行报表" showCollapseButton="true" style="width: 100%;">
					  	 <div id="configDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="padding:0;border:0;">
						  	<div title="风电场运行报表" name="wind_farm_report" iconCls="icon-cut">
								<jsp:include page="generate_wind_farm.jsp" ></jsp:include>
							</div> 
						</div>
					</div> --%>
				<div class="mini-panel" title="上传文件信息" showCollapseButton="true" style="width: 100%;">
					  	 <div title="上传文件信息" name="due_diligence_report2" iconCls="icon-cut" >
							 <jsp:include page="generate_wind_farm_upfile2.jsp" ></jsp:include>
						</div> 
				</div>
			   </td> 
		    </tr> 
	     </table>
	  </div> 
     </div> 