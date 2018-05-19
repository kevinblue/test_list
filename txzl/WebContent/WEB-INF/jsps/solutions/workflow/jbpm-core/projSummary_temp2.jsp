<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>项目总表</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAttachmentFileUpload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type='text/javascript'>
	var all_width  =  document.documentElement.clientWidth;
	var all_height =  document.documentElement.clientHeight-4;
    jQuery(function(){
        jQuery("#id_tabs_content").tabs();
        jQuery(".tabs-panels").css("padding","5px");
        jQuery(".tabs-panels").css("height",(all_height-60)+"px");
        jQuery(".x-panel-table-div-title").css("width",(all_width-35)+"px");
        //jQuery(".x-panel-table-div-title").css("borderLeftWidth","0px");
        //jQuery(".x-panel-table-div-title").css("borderRightWidth","0px");
		//########公共JS事件##########
		jQuery(".toggle-icon-expanded").click(toggleLeasingOperation);
    	var columns_workflow=[
        {   
            header:'操作',
            name:'taskid',
            renderer:function(value,tableObj,columnName,columnIndex,rowData){
            return "<a href='javascript:void(0);' onclick='toProcessForm(\""+value+"\")'>查看详情</a>";
	       }
        },{
    	    header:'流程名称',
    	    name:'workflowname'
    	},{
    	    header:'流程状态',
    	    name:'processinstancestate',
    	    renderer:function(value,tableObj,columnName,columnIndex,rowData){
    	    	var valueText=value;
    			switch(value){
    			case 'Draft' : valueText+= "[草稿]"; break;
    			case 'Pending' : valueText+= "[进行中]"; break;
    			case 'Finish' : valueText+= "[结束]"; break;
    			case 'Delete' : valueText+= "[删除]"; break;
    			case 'Abondon' : valueText+= "[废弃流程]"; break;
    			}
    			return valueText;
    		}
    	},{
    		 header:'关键字一',
     	    name:'keyone'
        },{
        	 header:'关键字二',
     	    name:'keytwo'
        },{
        	 header:'关键字三',
     	    name:'keythree',hidden:true
        },{
    	    header:'开始时间',
    	    name:'starttime'
    	},{
    	    header:'结束时间',
    	    name:'endtime'
    	}];
    	var datas_workflow = [];
        <c:forEach var="projSummaryHistoryInfo" items="${projSummaryHistoryInfos}">
           datas_workflow.push({
               "taskid"      :"${projSummaryHistoryInfo.historyTaskInstanceImpl.historyTask.id}",
               "workflowname":"${projSummaryHistoryInfo.workflowName}",
               "processinstancestate":"${projSummaryHistoryInfo.processInstanceState}",
               "keyone":"${projSummaryHistoryInfo.keyOne}",
               "keytwo":"${projSummaryHistoryInfo.keyTwo}",
               "keythree":"${projSummaryHistoryInfo.keyThree}",
               "starttime":"${projSummaryHistoryInfo.historyProcessInstanceImpl.startTime}",
               "endtime":"${projSummaryHistoryInfo.historyProcessInstanceImpl.endTime}"
           });
        </c:forEach>
    	new tracywindyTable({
            renderTo:'id_workflowHistoryTableContainer',
            width:all_width-35,
            isRank:true,
            rankSize:30,
            border:true,
            columns:columns_workflow,
            datas:datas_workflow,
            toolsLeftMargin:200,
            id:'workflowHistoryTable',
            loadMode:'local',
            isPage:false,
            isFit:true
    	});
    });
    function toProcessForm(currentTaskId,planActorId){
    	window.open("${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId,"_blank");
    }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="overflow:auto;padding:10px;">
			<!-- 标签栏 -->
			<div id='id_tabs_content' class="fillTable">
					<div title="基本信息" >
					    <span class="print-tabs-title-content">基本信息</span>
					    <!--  
					    <div class="x-panel-table-div-title">
								<div class="toggle-icon-expanded" title="折叠"
									toggleClass="basic-project-info"></div>
						项目基本信息</div>
						<div class="basic-project-info" >项目基本信息内容</div>
						-->
					    <div class="x-panel-table-div-title">
								<div class="toggle-icon-expanded" title="折叠"
									toggleClass="workflow-processes"></div>
						流程参与情况</div>
					    <div class="workflow-processes" id="id_workflowHistoryTableContainer" style="overflow:hidden;"></div>
					</div>
					<div title="附件一览">
					 	<span class="print-tabs-title-content">附件一览</span>
					 	    <c:forEach var="projSummaryHistoryInfo" items="${projSummaryHistoryInfos}">
					 	    
						        <div class="x-panel-table-div-title">
								<div class="toggle-icon-expanded" title="折叠"
									toggleClass="workflow-attachment-${projSummaryHistoryInfo.historyProcessInstanceImpl.dbid}"></div>
									${projSummaryHistoryInfo.workflowName}
								</div>
								<div class="workflow-attachment-${projSummaryHistoryInfo.historyProcessInstanceImpl.dbid}" style="line-height:20px;">
							             <c:forEach var="attachmentDetailInfo" items="${projSummaryHistoryInfo.historyProcessInstanceImpl.attachmentFileUploadInfoDetails}">
							                 <div>&nbsp;<a><img align='absmiddle' width=14  src='${pageContext.request.contextPath}/images/attach.gif'/></a><a href="javascript:void(0);" onclick="downloadUploadFile('${attachmentDetailInfo.id}')">
									                 ${attachmentDetailInfo.chineseFileName}【${attachmentDetailInfo.fileType}】
									                 <c:choose> 
									                    <c:when test="${ 1024*1024 > attachmentDetailInfo.fileSize }">
									                    <fmt:formatNumber value="${attachmentDetailInfo.fileSize/1024}" pattern="0.00"/>
									                    K</c:when> 
									                    <c:otherwise>
									                     <fmt:formatNumber value="${attachmentDetailInfo.fileSize/1024/1024}" pattern="0.00"/>
									                    M</c:otherwise>
									                 </c:choose>
									                </a>
									                                    【上传时间】${attachmentDetailInfo.uploadTime}【上传人】${attachmentDetailInfo.uploadUser.realname}【下载】${fn:length(attachmentDetailInfo.attachmentFileUploadInfoDetailDownloads)}次<a onclick='downloadUploadFile("${attachmentDetailInfo.id}");' title='下载' href='javascript:void(0);'><img border=0 align='absmiddle' width=14  src='${pageContext.request.contextPath}/images/toolbar_export.gif'/></a>
							               </div>
							             </c:forEach>
						        </div>
						    </c:forEach>
					</div>
			</div>
</body>