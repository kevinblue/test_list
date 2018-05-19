<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ taglib uri="/minidict" prefix="mini"%>	
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<!-- 弹出意见框开始 -->
<div id="id_adviseContainerWindow" class="mini-window" title="<spring:message code="WriteOpinion" text="填写意见"/>" style="display:none;width:550px;">
	<div style="padding: 5px 0px;">
		<table style="width:530px" style="padding:1px;">
			<tr class="tr-odd">
				<td style="width:80px;text-align:right;"><spring:message code="CurrentTime" text="当前时间"/>：</td>
				<td style="text-align:left;"><div id="id_currentSystemTime"></div></td>
			</tr>
			<tr class="tr-even" style="width:300px;">
				<td style="width:80px;text-align:right;">审批结论：</td>
				<td style="text-align:left;">
				   <mini:dict name="selectAdvise_combo" otherproperties='width="200px"' parentid="JbpmCommonAdvise"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td style="width:80px;text-align:right;">审批意见：</td>
				<td style="text-align:left;">
					<textarea id="text_selectedAdvise" style="width:400px;height:200px;border:1px solid #DDD;font-size: 12px;"></textarea>
				</td>
			</tr>
		</table>
	</div>
	<div style="padding:5px 0px;text-align:center;border-top: 1px solid #AAA; "> 
		<a class="mini-button" onclick='__adviseContainerWindowOk'><spring:message code="Confirm" text="确定"/></a>
		<a class="mini-button" onclick='__adviseContainerWindowNo'><spring:message code="Cancel" text="取消"/></a>
	</div>
</div>

<script type="text/javascript">
	function __adviseContainerWindowOk(){
		fillCurrentCommonAdvise();
		mini.get("id_adviseContainerWindow").hide();
	}
	
	function __adviseContainerWindowNo(){
		mini.get("id_adviseContainerWindow").hide();
	}
	
	mini.parse(document.getElementById('id_adviseContainerWindow'));
</script>

<textarea id='id_processedAdviseTxt' style="display:none;">${currentJBPMWorkflowHistoryInfoUser.processedAdvise}</textarea>
<textarea id='id_assignedAdviseTxt' style="display:none;">${currentJBPMWorkflowHistoryInfoUser.assignedAdvise}</textarea>
<textarea id='id_processedResultTxt' style="display:none;">${currentJBPMWorkflowHistoryInfoUser.processedResult.id}</textarea>
<textarea id='id_processedResultShowTxt' style="display:none;">${currentJBPMWorkflowHistoryInfoUser.processedResult.name}</textarea>
<script type="text/javascript">
	var currentSavedAdvise = (jQuery("#id_processedAdviseTxt").val()||jQuery("#id_assignedAdviseTxt").val());
	var currentSavedResult = jQuery("#id_processedResultTxt").val();
	var currentShowSavedResult = jQuery("#id_processedResultShowTxt").val();
	
	// 加载意见列表意见
	function addCommonAdivse(hidden_text_choseAdviseValue,hidden_choseResult,show_choseResult) {
		var hidden_text_choseAdvise = document.getElementById('id_hidden_text_choseAdvise');
		hidden_text_choseAdvise.value = hidden_text_choseAdviseValue;
		$("#id_hidden_choseResult").val(hidden_choseResult);
		$("#id_show_choseResult").val(show_choseResult);
		var workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');
		
		var insertHtml ='<div id="id_currentTaskFilledAdvise_1" style="border:0px solid silver;padding-left:5px;height:25px;line-height:25px;background-color:#EFEFEF;">';
		insertHtml+='	<span style="width:200px;height:100%;padding-top:5px;font-size:12px;">【'+workFlowlocale['step']+'】<font color="red" style="font-weight:BOLD;">'+workFlowlocale['currentHandle']+'</font></span>';
		insertHtml+='	<span style="width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;">【'+workFlowlocale['WriteDate']+'】'+ getCurDateTime()+'</span>';
		insertHtml +='	<span style="width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;">【审批结论】<font style="color:red;">'+ show_choseResult+'</font></span>';
		insertHtml+='	<span style="width:150px;margin-left:20%;height:100%;padding-top:4px;font-size:12px;">';
		if(!isCompletedTask){
			insertHtml+='		<a href="javascript:void(0);" style="text-decoration:none;" onclick="showModalCommonAdivseWindow();"><img align="absmiddle" src="${pageContext.request.contextPath}/menuIcons/pencil.png" border="0"/>&nbsp;'+workFlowlocale['Edit']+'</a>';
			insertHtml+='		<a href="javascript:void(0);" style="text-decoration:none;margin-left:20px;" onclick="delCommonAdivse();"><img align="absmiddle" src="${pageContext.request.contextPath}/menuIcons/no.png" border="0"/>&nbsp;'+workFlowlocale['Remove']+'</a></span>';
		}
		insertHtml+='</div>';
		insertHtml+='<div id="id_currentTaskFilledAdvise_2" style="border:0px solid silver;border-top:0px solid #FFFFFF;border-bottom:0px;padding-left:20px;height:auto;line-height:25px;background-color:#FFFFFF;">';
		insertHtml+='	<span style="height:100%;padding-top:5px;font-size:12px;">'+hidden_text_choseAdvise.value+'</span>';
		insertHtml+='</div>';
		
		workflowAdviseContainer.innerHTML = (insertHtml+workflowAdviseContainer.innerHTML);
	}
	
	//通用意见弹出窗口
	function showModalCommonAdivseWindow() {
		var hidden_text_choseAdvise=$("#id_hidden_text_choseAdvise").val();
		$("#text_selectedAdvise").val(hidden_text_choseAdvise);
		$("#id_currentSystemTime").html(getCurDateTime());
		var hidden_choseResult=$("#id_processedResultTxt").val() ;
		var show_choseResult=$("#id_processedResultShowTxt").val();
		mini.get('id_selectAdvise_combo').setValue(hidden_choseResult);
		mini.get('id_selectAdvise_combo').setText(show_choseResult);
		var win = mini.get("id_adviseContainerWindow").show('center','middle');
	}
	
	//添加或者修改当前流程意见
	function fillCurrentCommonAdvise(){
		var hidden_text_choseAdvise = document.getElementById('id_hidden_text_choseAdvise');
		hidden_text_choseAdvise.value = document.getElementById('text_selectedAdvise').value;
		$("#id_hidden_choseResult").val(mini.get('id_selectAdvise_combo').getValue());
		$("#id_show_choseResult").val(mini.get('id_selectAdvise_combo').getText());
		$("#id_processedResultTxt").val(mini.get('id_selectAdvise_combo').getValue());
		$("#id_processedResultShowTxt").val(mini.get('id_selectAdvise_combo').getText());
		var workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');
		var currentTaskFilledAdvise_1 = document.getElementById('id_currentTaskFilledAdvise_1');
		if(currentTaskFilledAdvise_1) {
			workflowAdviseContainer.removeChild(currentTaskFilledAdvise_1);
			var currentTaskFilledAdvise_2 = document.getElementById('id_currentTaskFilledAdvise_2');
			workflowAdviseContainer.removeChild(currentTaskFilledAdvise_2);
		}
		var insertHtml = '';
		insertHtml +='<div id="id_currentTaskFilledAdvise_1" style="border:0px solid silver;padding:5px;height:auto;line-height:25px;background-color:#EFEFEF;">';
		insertHtml +='	<span style="width:200px;height:100%;padding-top:5px;font-size:12px;">【步骤】<font color="red" style="font-weight:BOLD;">当前处理</font></span>';
		insertHtml +='	<span style="width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;">【填写时间】'+ getCurDateTime()+'</span>';
		insertHtml +='	<span style="width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;">【审批结论】<font style="color:red;">'+ mini.get('id_selectAdvise_combo').getText()+'</font></span>';
		insertHtml +='	<span style="width:150px;margin-left:20%;height:100%;padding-top:4px;font-size:12px;">';
		insertHtml +='		<a href="javascript:void(0);" style="text-decoration:none;" onclick="showModalCommonAdivseWindow();"><img align="absmiddle" src="${pageContext.request.contextPath}/menuIcons/pencil.png" border="0"/>&nbsp;'+workFlowlocale['Edit']+'</a>';
		insertHtml +='		<a href="javascript:void(0);" style="text-decoration:none;margin-left:20px;" onclick="delCommonAdivse();"><img align="absmiddle" src="${pageContext.request.contextPath}/menuIcons/no.png" border="0"/>&nbsp;'+workFlowlocale['Remove']+'</a>';
		insertHtml +='   </span>';
		insertHtml +='</div>';
		insertHtml +='<div id="id_currentTaskFilledAdvise_2" style="border:0px solid silver;border-top:0px solid #FFFFFF;border-bottom:0px;padding-left:20px;height:auto;line-height:25px;background-color:#FFFFFF;">';
		insertHtml +='	<span style="height:100%;padding-top:5px;font-size:12px;">'+hidden_text_choseAdvise.value+'</span>';
		insertHtml +='</div>';
		insertHtml +='</div>';
		workflowAdviseContainer.innerHTML = (insertHtml+workflowAdviseContainer.innerHTML);
	}
	
	//删除当前流程意见
	function delCommonAdivse() {
		var workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');
		var currentTaskFilledAdvise_1 = document.getElementById('id_currentTaskFilledAdvise_1');
		if(currentTaskFilledAdvise_1) {
			workflowAdviseContainer.removeChild(currentTaskFilledAdvise_1);
			var currentTaskFilledAdvise_2 = document.getElementById('id_currentTaskFilledAdvise_2');
			workflowAdviseContainer.removeChild(currentTaskFilledAdvise_2);
		}
		$("#id_hidden_text_choseAdvise").val("");
		$("#id_hidden_choseResult").val("");
		$("#id_show_choseResult").val("");
		$("#id_processedResultTxt").val("");
		$("#id_processedResultShowTxt").val("");
	}
	//判断已经保存过了意见
	if(currentSavedAdvise && !isCompletedTask) {
		$("#id_hidden_text_choseAdvise").val(currentSavedAdvise);
	}
	function setWorkflowChoseAdvise(e){
		 var combox=e.sender;
		 selectedAdviseValue=combox.text
		 $('#text_selectedAdvise').val(selectedAdviseValue);
		 $('#id_hidden_text_choseAdvise').val(selectedAdviseValue);
    }
</script>		
<!-- 弹出意见框结束-->