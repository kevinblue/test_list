<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix="permission" uri="/WEB-INF/tlds/permission.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!--提交按钮-->
<div id="__jbpmProcessButtonsToolbar" class="mini-panel" maskOnLoad="false" title="<spring:message code="Workflow"  text="流程"/>：<mini:param  name="currentWorkFlowDisplayName" />[<mini:param  name="currentTaskName" />]" style="width: 100%;" bodyStyle="padding:2px 6px 2px 6px;">
	<div style="float:left;">
	<a class="mini-button" onclick="__removeProcessInstance" id="__removeProcessInstance" iconCls="icon-cancel"><spring:message code="Close" text="关闭"/></a>
	
	<c:if test="${('true' ne isViewHistoryTask)}">
		<c:set var="operationButtons" value="${currentTaskOperationButtons}"></c:set>
		<c:if test="${('read' eq currentRequestTaskType)}">
			<a class="mini-button" onclick="__readOrSignatureOper_read" id="__readOrSignatureOper_read" iconCls="icon-save"><spring:message code="ConfirmWorkflowRead" text="已阅"/></a>
			<a class="mini-button" onclick="__readButtonDisplayText" id="__readButtonDisplayText" iconCls="icon-save"><spring:message code="ContinueWorkflowRead" text="继续传阅"/></a>
		</c:if>
		<c:if test="${('signature' eq currentRequestTaskType)}">
			<a class="mini-button" onclick="__readOrSignatureOper_signature" id="__readOrSignatureOper_signature" iconCls="icon-save"><spring:message code="ConfirmSignature" text="会签确认"/></a>
			<a class="mini-button" onclick="__signatureButtonDisplayText" id="__signatureButtonDisplayText" iconCls="icon-save"><spring:message code="ContinueSignature" text="继续会签"/></a>
		</c:if>
		<c:if test="${('assignmentPending' eq currentRequestTaskType)}">
			<c:if test="${fn:indexOf(operationButtons, 'save')>-1}">
			<a class="mini-button" onclick="__saveButtonDisplayText" id="__saveButtonDisplayText" iconCls="icon-save"><spring:message code="Save" text="保存"/></a>
			</c:if>
			<a class="mini-button" onclick="__submitButtonDisplayText" id="__submitButtonDisplayText" iconCls="icon-ok"><spring:message code="Submit" text="提交"/></a>
		</c:if>
		<c:if test="${('assignmentCompleted' eq currentRequestTaskType)}">
			<c:if test="${fn:indexOf(operationButtons, 'save')>-1}">
			<a class="mini-button" onclick="__saveButtonDisplayText" id="__saveButtonDisplayText" iconCls="icon-save"><spring:message code="Save" text="保存"/></a>
			</c:if>
			<c:if test="${fn:indexOf(operationButtons, 'read')>-1}">
			<a class="mini-button" onclick="__readButtonDisplayText" id="__readButtonDisplayText" iconCls="icon-save"><spring:message code="WorkflowRead" text="传阅"/></a>
			</c:if>
			<c:if test="${fn:indexOf(operationButtons, 'signature')>-1}">
			<a class="mini-button" onclick="__signatureButtonDisplayText" id="__signatureButtonDisplayText" iconCls="icon-sum"><spring:message code="Signature" text="会签"/></a>
			</c:if>
			<!--提交-->
			<a class="mini-button" onclick="__submitButtonDisplayText" id="__submitButtonDisplayText" iconCls="icon-ok"><spring:message code="Submit" text="提交"/></a>
			<c:if test="${fn:indexOf(operationButtons, 'back')>-1}">
			<a class="mini-button" onclick="__backButtonDisplayText" id="__backButtonDisplayText" iconCls="icon-undo"><spring:message code="Back" text="退回"/></a>
			</c:if>
			<c:if test="${('true' eq isFirstTask )}">
			<a class="mini-button" onclick="__deleteButtonDisplayText" id="__deleteButtonDisplayText" iconCls="icon-cancel"><spring:message code="Delete" text="删除"/></a>
			</c:if>
			<!-- 
			<c:if test="${('true' eq isFirstTask ) && !('Draft' eq processInstanceState) }">
			<a class="mini-button" onclick="__abondonButtonDisplayText" id="__abondonButtonDisplayText" iconCls="icon-cancel"><spring:message code="Abondon" text="废弃"/></a>
			</c:if>
			-->
		</c:if>
		<c:if test="${('pending' eq currentRequestTaskType)||('delegate' eq currentRequestTaskType)}">
			<c:if test="${fn:indexOf(operationButtons, 'save')>-1}">
			<a class="mini-button" onclick="__saveButtonDisplayText" id="__saveButtonDisplayText" iconCls="icon-save"><spring:message code="Save" text="保存"/></a>
			</c:if>
			<c:if test="${('pending' eq currentRequestTaskType)}">
			<c:if test="${fn:indexOf(operationButtons, 'assignment')>-1}">
			<a class="mini-button" onclick="__assignmentButtonDisplayText" id="__assignmentButtonDisplayText" iconCls="icon-save"><spring:message code="Assignment" text="工作指派"/></a>
			</c:if>
			</c:if>
			<c:if test="${fn:indexOf(operationButtons, 'read')>-1}">
			<a class="mini-button" onclick="__readButtonDisplayText" id="__readButtonDisplayText" iconCls="icon-save"><spring:message code="WorkflowRead" text="传阅"/></a>
			</c:if>
			<c:if test="${fn:indexOf(operationButtons, 'signature')>-1}">
			<a class="mini-button" onclick="__signatureButtonDisplayText" id="__signatureButtonDisplayText" iconCls="icon-sum"><spring:message code="Signature" text="会签"/></a>
			</c:if>
			<!--提交-->
			<a class="mini-button" onclick="__submitButtonDisplayText" id="__submitButtonDisplayText" iconCls="icon-ok"><spring:message code="Submit" text="Submit"/></a>
			<c:if test="${fn:indexOf(operationButtons, 'back')>-1}">
			<a class="mini-button" onclick="__backButtonDisplayText" id="__backButtonDisplayText" iconCls="icon-undo"><spring:message code="Back" text="Back"/></a>
			</c:if>
			<c:if test="${('true' eq isFirstTask )}">
			<a class="mini-button" onclick="__deleteButtonDisplayText" id="__deleteButtonDisplayText" iconCls="icon-cancel"><spring:message code="Delete" text="删除"/></a>
			</c:if>
			<!-- 
			<c:if test="${('true' eq isFirstTask ) && !('Draft' eq processInstanceState) }">
			<a class="mini-button" onclick="__abondonButtonDisplayText" iconCls="icon-cancel"><spring:message code="Abondon" text="废弃"/></a>
			</c:if>
			-->
		</c:if>
		<c:if test="${(empty currentRequestTaskType)}">
			<c:if test="${fn:indexOf(operationButtons, 'save')>-1}">
			<a class="mini-button" onclick="__saveButtonDisplayText" id="__saveButtonDisplayText" iconCls="icon-save"><spring:message code="Save" text="保存"/></a>
			</c:if>
			<!--提交-->
			<a class="mini-button" onclick="__submitButtonDisplayText" id="__submitButtonDisplayText" iconCls="icon-ok"><spring:message code="Submit" text="提交"/></a>
			<c:if test="${('true' eq isFirstTask )}">
			<a class="mini-button" onclick="__deleteButtonDisplayText" id="__deleteButtonDisplayText" iconCls="icon-cancel"><spring:message code="Delete" text="删除"/></a>
			</c:if>
			<!-- 
			<c:if test="${('true' eq isFirstTask ) && !('Draft' eq processInstanceState) }">
			<a class="mini-button" onclick="__abondonButtonDisplayText" id="__abondonButtonDisplayText" iconCls="icon-cancel"><spring:message code="Abondon" text="废弃"/></a>
			</c:if>
			-->
		</c:if>
		<c:if test="${('pending' eq currentRequestTaskType)}">
			<permission:action code="admin_action">
			<a class="mini-button" onclick='__resetRouteButtonDisplayText' id="__resetRouteButtonDisplayText" iconCls="icon-department"><spring:message code="ResetRoute" text="任意路由"/></a>
			</permission:action>
		</c:if>
	</c:if>
	</div>
	<div style="float:right;">
		<a class="mini-button" onclick='toProcessActivePicture' id="id_toProcessActivePicture" iconCls="icon-expand"><spring:message code="WorkflowDiagram" text="流程图"/></a>
		<a class="mini-button" onclick='viewWorkflowHistoryDetail' id="id_viewWorkflowHistoryDetail" iconCls="icon-tip"><spring:message code="HistoryInfo" text="历史信息"/></a>
		<a class="mini-button" onclick='viewProjSummary' id="id_viewProjSummary" iconCls="icon-node"><spring:message code="ProjectSummary" text="项目总表"/></a>
			<!--
		<a class="mini-button" onclick='flowDataContrast' flag="compare" id="id_viewFlowDataContrast" iconCls="icon-node"><spring:message code="FlowDataContrast" text="数据对比"/></a>
	
		<a class="mini-button" onclick='previewPrint' id="id_previewPrint" iconCls="icon-print"><spring:message code="PrintPreview" text="打印预览"/></a>
		-->
		<a class="mini-button" onclick="__goHome" id="__goHome" iconCls="icon-home"><spring:message code="ReturnHome" text="返回首页"/></a>
	</div>
</div>

<script type="text/javascript">

	function __readOrSignatureOper_read(){
		readOrSignatureOper('read');
	}

	function __readButtonDisplayText(){
		btn_callBack(readButtonDisplayText);
	}

	function __readOrSignatureOper_signature(){
		readOrSignatureOper('signature');
	}

	function __signatureButtonDisplayText(){
		btn_callBack(signatureButtonDisplayText);
	}

	function __saveButtonDisplayText(){
		btn_callBack(saveButtonDisplayText);
	}

	function __submitButtonDisplayText(){
		if("${WorkFlowDealMethod}"=="AllPassed"){
			if(confirm("确定提交流程下一步？")){btn_callBack(submitButtonDisplayText);}
		}else{
		    btn_callBack(submitButtonDisplayText);
		}
	}

	function __backButtonDisplayText(){
		btn_callBack(backButtonDisplayText);
	}

	function __deleteButtonDisplayText(){
		btn_callBack(deleteButtonDisplayText);
	}

	function __assignmentButtonDisplayText(){
		btn_callBack(assignmentButtonDisplayText);
	}

	function __resetRouteButtonDisplayText(){
		btn_callBack(resetRouteButtonDisplayText);
	}

	function __abondonButtonDisplayText(){
		btn_callBack(abondonButtonDisplayText);
	}

	function __removeProcessInstance(){
		if(window.confirm('确认关闭此流程页面么？')){
			removeProcessInstance();
		}
	}

	function __goHome(){
		mini.confirm("确认返回至系统首页么？", "确定？",
            function (action) {
                if (action == "ok") {
					mini.mask({
			            el: document.body,
			            cls: 'mini-mask-loading',
			            html: '正在返回首页 请耐心等待...'
			        });
					window.top.location.href='${pageContext.request.contextPath}/acl/index.acl';
                }
            }
        );
	}
	
	mini.parse(document.getElementById("__jbpmProcessButtonsToolbar"));
</script>


<permission:action code="dealer_action">
	<script type="text/javascript">
		mini.get("id_viewProjSummary").hide();
	</script>
</permission:action>