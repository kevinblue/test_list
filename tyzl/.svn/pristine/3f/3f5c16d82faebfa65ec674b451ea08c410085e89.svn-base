<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld" %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	window.moveTo(0,0);
	//window.resizeTo(screen.availWidth,screen.availHeight); 
	//如果想和最大化效果看起来没有一丝差距，还需要调整一下（0,0）、screen.availWidth和screen.availHeight;
	//（0,0）可以改成（-4，-4）等；screen.availWidth和screen.availHeight可以都加上一个比较小的合适数值;
	//具体加减多少得慢慢试，可能不同浏览器和机器不一样，如果要求不严格，可以无视这点差别。

  var isPagePrint = true;
  var currentJbpmWorkflowHistoryInfoId = '<mini:param  name='currentJbpmWorkflowHistoryInfoId'/>';
  var isViewHistoryTask  = true;
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><mini:param  name='currentTaskFormTitle'/>打印预览</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<STYLE type=text/css media=print>
  .no-print{
    display:none
  }
  html,body{
    -webkit-print-color-adjust: exact;
  }
  .page-next{PAGE-BREAK-AFTER:always;}
</STYLE>  
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
	<script type="text/javascript">
	  jQuery.fn.tabs = function(){};
	   /* window.onresize = function(){
	    	var currentPageClientWidth  =  document.documentElement.clientWidth;
	    	var currentPageClientHeight =  document.documentElement.clientHeight;
	    	currentPageClientWidth  = currentPageClientWidth  > 0 ? currentPageClientWidth  : document.body.clientWidth;
	    	currentPageClientHeight = currentPageClientHeight > 0 ? currentPageClientHeight : document.body.clientHeight;
            if(
                     (Math.abs(currentPageClientWidth-oldCurrentPageClientWidth)>10)
                   ||(Math.abs(currentPageClientHeight-oldCurrentPageClientHeight)>10)
              ){
            	   window.location.href = window.location.href;
              }
	    	oldCurrentPageClientWidth  =  currentPageClientWidth;
	    	oldCurrentPageClientHeight =  currentPageClientHeight;
		};*/
		
		var all_width  =  1000;//document.documentElement.clientWidth;
		var isNormal = false;//(SysBrowser.getBrowser().indexOf("IE")>-1);//||(SysBrowser.getBrowser().indexOf("Firefox")>-1);
		if(isNormal){
			all_width = document.documentElement.clientWidth-16;
		}
		var all_height =  document.documentElement.clientHeight;
		var oldCurrentPageClientWidth = document.documentElement.clientWidth;
		var oldCurrentPageClientHeight = all_height;
		var formHeight = 0;
		var formWidth = 0;
		jQuery(function(){
			   var containerWidthAdd = 0;
			   var containerHeightAdd = 115;
			   formHeight = all_height-containerHeightAdd-10;
			   formWidth = all_width-20;
			   if(isNormal){
				   formWidth = all_width-20;
				}
			   jQuery("#id_workflowFormContainer_inner").css("width", (all_width-containerWidthAdd)+"px");
		       //jQuery("#id_workflowFormContainer_inner").css("height",(all_height-containerHeightAdd-1)+"px");
		       jQuery("#id_workflowAdviseContainer").css("width",(all_width-containerWidthAdd-5)+"px");
		       //jQuery("#id_workflowAdviseContainer").css("height",(all_height-containerWidthAdd-124)+"px");
		       jQuery(".x-panel-table-toolbar-div").css("width",(all_width-containerWidthAdd)+"px");
		       jQuery(".x-panel-table-div-title").css("width",(all_width-containerWidthAdd-3)+"px");
		       jQuery("#id_toggle_tabs_advise .x-panel-table-div-title").css("width",(all_width-containerWidthAdd-25)+"px");
		       jQuery("#id_toggle_tabs_attachment .x-panel-table-div-title").css("width",(all_width-containerWidthAdd-25)+"px");
		       jQuery("#id_content_tabs .tabs-header").css("width",(all_width-containerWidthAdd+1)+"px");
				  //加载附件
				 /*
				    renderToContainer,//渲染到哪个div的id
				    attachmentType,//附件分类
				    serialNo,//流水号唯一标识
				    isReadOnly,//是否只能下载，不能上传和删除附件
				    width,//上传列表宽度
				    height,//上传列表高度
				    title//列表标题
				    border//边框
				    */
			     new tracywindyAttachmentFileUpload(
		                  'id_workflowAttachmentContainer',
		                  '<mini:param  name="currentTaskActivityDetailInfo.attachmentType"/>',
		                  '<mini:param  name="currentHistoryTaskInfo.keyOne" />',
		                  true,
		                  all_width-12,
		                  all_height-containerWidthAdd-124,
		                  '',
		                  false
					 );
		});
	</script>
</head>
   <body style="padding:5px;">
   <div class="no-print">
    			  <a style="color:red;margin-left:460px;margin-bottom:5px;" href="javascript:void(0);" class="easyui-linkbutton" onClick='previewPrint();' plain="true" iconCls="icon-print">打印</a></div>
          		  <div id="id_loadMaskContainer" style="display:block;">
					  <div id="loading">
					    <div class="loading-indicator">正在加载   请稍等...</div>
					</div>
			  </div>
			  <script type="text/javascript">
			  if(isNormal){
				  jQuery("#id_loadMaskContainer").hide();		
				  }
			  else{
				   jQuery("#id_loadMaskContainer").css("height",all_height);		
				  }
			  </script>
    <div>
       <div class="x-panel-table-div-title" id="id_focusComponent">所用流程：<mini:param  name='currentWorkFlowName'/>&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<mini:param  name='currentTaskName'/>&nbsp;&nbsp;]</div>
		        <!-- 弹出意见框结束-->
		       <div id="id_content_tabs"><!--页签开始-->
		           <div  style="display:block;" id="id_toggle_tabs_form"><!--页签1开始-->
				        <!--表单处理-->
				        <div  class="workflow-title"><span style="height:100%;padding-top:5px;font-size:11px;color:#FFFFFF;font-weight:BOLD;">流程表单</span></div>
					     <div style="" id="id_workflowFormContainer">
						     <div  id="id_workflowFormContainer_inner">
						         <form id="id_submitProcessedForm">
								   <!-- 当前任务节点表单路径相对于jbpm-core/forms下的jsp页面 -->
								   <jsp:include page="/${empty requestFormPath ? 'errorPages/error404.bi' : requestFormPath }"  flush="true"></jsp:include>
							     </form>
							 </div>
						</div>
				    </div><!--页签1结束-->
				    <!--审批意见-->
		            <div id="id_toggle_tabs_advise"><!--页签2开始-->
						       <div class="x-panel-table-div-title" >
							                    意见一览
						       </div>
							       <div id="id_workflowAdviseContainer">
									       <c:forEach items="${processedHistoryLogInfoList}" var="jbpmWorkflowHistoryInfo" varStatus="status">
									           <c:set var="historyTask" value="${jbpmWorkflowHistoryInfo.historyTaskInstanceImpl}"></c:set>
									           <c:set var="fromButtonTextToCome" value="${jbpmWorkflowHistoryInfo.fromButtonTextToCome}"></c:set>
									           <c:set var="taskName" value="${historyTask.activityName}"></c:set>
									           <c:set var="taskStartTime" value="${historyTask.startTime}"></c:set>
									           <c:set var="taskEndTime" value="${historyTask.endTime}"></c:set>
									           <c:set var="durationMinutes" value="${historyTask.duration/1000/60}"></c:set>
										       <div class="taskname">
                                                   <table><tr>
										           <td><strong>【<font color="red"><mini:param  name="fromButtonTextToCome" /></font>】<mini:param  name="taskName" /></strong></td>
                                                   <td>【开始时间】<fmt:formatDate value="${taskStartTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                                   <td>【结束时间】<fmt:formatDate value="${taskEndTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                                                   <td>【耗时】<fmt:formatNumber value="${durationMinutes}" pattern="#.##" type="number"></fmt:formatNumber>&nbsp;分钟</td>
                                                   </tr></table>
										       </div>
										       <c:forEach items="${jbpmWorkflowHistoryInfo.jbpmWorkflowHistoryInfoUsers}" var="jbpmWorkflowHistoryInfoUser" varStatus="userStatus">
										        <c:set var="taskPlanActor" value="${jbpmWorkflowHistoryInfoUser.planActor}"></c:set>
									            <c:set var="taskActualActor" value="${jbpmWorkflowHistoryInfoUser.actualActor}"></c:set>
									            <c:set var="taskPlanActorRealName" value="${taskPlanActor.realname}"></c:set>
									            <c:set var="taskActualActorRealName" value="${taskActualActor.realname}"></c:set>
									            <c:set var="processedAdvise" value="${jbpmWorkflowHistoryInfoUser.processedAdvise}"></c:set>
										        <div class="taskactor">
										        <c:set var="t_userStatus" value="${userStatus.index+1}"></c:set>	           
											           【处理人】&nbsp;&nbsp;&nbsp;<mini:param  name="t_userStatus" />、<mini:param  name="taskPlanActorRealName" />&nbsp;&lt;&nbsp;<mini:param  name="taskActualActorRealName" />&nbsp;&gt;&nbsp;<br>
												【处理意见】 &nbsp;&nbsp;<mini:param  name="processedAdvise" />
											    </div>
										       </c:forEach>
									       </c:forEach>
						        </div>
		              </div><!--页签2结束-->
		             <div id="id_toggle_tabs_attachment"><!--页签2开始-->
						       <div class="x-panel-table-div-title" > 
						                         附件上传
						       </div>
						       <div id="id_workflowAttachmentContainer">
						       </div>
				    </div>
				</div><!--页签结束-->
			</div>
			<!-- 
			<object id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
				<embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0 ></embed>
			</object> 
			
			<OBJECT id="WebBrowser" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name=wb></OBJECT>  
			 -->
</body>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/print/LodopFuncs.js"></script>
 <script type="text/javascript">
    function previewPrint(){
		/*LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));  

		LODOP.PRINT_INIT("打印控件功能演示_Lodop功能_完整全页");
		//LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Full-Page");
		//alert(document.getElementsByTagName("html")[0].innerHTML);
		//jQuery(document.body).text(document.getElementsByTagName("html")[0].outerHTML);
		LODOP.ADD_PRINT_HTM(10,10,"100%","100%",'<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">'+document.getElementsByTagName("html")[0].outerHTML);
		LODOP.PREVIEW();	*/
		if((SysBrowser.getBrowser().indexOf("IE")>-1)){
			window.print();
			//document.getElementById("WebBrowser").execWB(7,1);  
		}
		else{
			window.print();
		}
    }
    var defaultInputWidth = 100;
    $.fn.extend({
        textareaAutoHeight: function (options) {
            this._options = {
                minHeight: 0,
                maxHeight: 1000
            };

            this.init = function () {
                for (var p in options) {
                    this._options[p] = options[p];
                }
                if (this._options.minHeight == 0) {
                    this._options.minHeight=parseFloat($(this).height());
                }
                for (var p in this._options) {
                    if ($(this).attr(p) == null) {
                        $(this).attr(p, this._options[p]);
                    }
                }
                $(this).keyup(this.resetHeight).change(this.resetHeight).focus(this.resetHeight);
            };
            this.resetHeight = function(){
            	var _minHeight = parseFloat($(this).attr("minHeight"));
                var _maxHeight = parseFloat($(this).attr("maxHeight"));
                if (!$.browser.msie) {
                    $(this).height(0);
                }
                var h = parseFloat(this.scrollHeight);
                h = (h < _minHeight ) ? _minHeight : (h > _maxHeight ? _maxHeight : h );
                $(this).height(h).scrollTop(h);
                if (h >= _maxHeight) {
                    $(this).css("overflow-y", "scroll");
                }
                else {
                    $(this).css("overflow-y", "hidden");
                }
                    
            }
            this.init();
            this.resetHeight();
        }
    });
   jQuery(function(){
			   var jqPrintObjs = jQuery("span.print-tabs-title-content");
			   jqPrintObjs.each(function(){
				    jQuery(this).html("<div class='x-panel-table-div-title' style='width:"+(formWidth-10)+"px;padding-left:20px;'>&gt;&gt;&gt;&gt;&nbsp;&nbsp;"+jQuery(this).text()+"</div>") ;
			   });
			   jQuery("span.print-tabs-title-content").show();
				
	           if(isViewHistoryTask){
		           var submitForm = document.getElementById("id_submitProcessedForm");
		           var formElements = submitForm.elements;
		           for(var i=0;i<formElements.length;i++)
		           {
			           var formElement = formElements[i];
			           formElement.readOnly = true;
			           jQuery("#id_submitProcessedForm input[type='text'],#id_submitProcessedForm textarea").addClass("element-readonly");
			           if(formElement.className.indexOf("Wdate")>-1){
			        	   formElement.onclick = null;
				       }
			           removeClass(formElement,"Wdate");
			       }
			       if(window.formPageReadOnlyCallBack){
			    	   window.formPageReadOnlyCallBack(submitForm);
				   }
				   jQuery("a.btn-primary,input.btn-primary").hide();
		       }
	           document.documentElement.style.overflowY = "auto";
	           document.body.style.overflowY = "auto";
	           //打印特殊设置开始
		   		jQuery(".td-content-input,textarea.td-content-input").each(function(){
		            if(!this.style.width || (this.className.indexOf("x-panel-combo-droplist-input")>-1) ){
		                this.style.width = "100px";
		            }
		  	    });
		  		//Query("textarea.td-content-input").css("width","100px");
		  		jQuery(".required-content").hide();
		  		jQuery(".required-percent-content").hide();
		  		jQuery("#id_toggle_tabs_advise").show();
		  		jQuery("#id_toggle_tabs_attachment").show();
		  		jQuery("textarea").each(function(){
                         this.style.height = Math.max(this.offsetHeight,this.scrollHeight)+"px";
			     });
		  	     //打印特殊设置结束
	           if(!isNormal){
				   var iframes = window.top.frames;
				   var iframesLen = iframes.length;
				   var index = 0;
				   {
					  setTimeout('jQuery("#id_loadMaskContainer").hide();',iframesLen*250);
				   }
		      }

	});
   </script>
</html>