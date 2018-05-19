<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<script type="text/javascript">
var isDraft = ("Draft" == "${processInstanceState}");

var __getDocId = function(){// JBPM流程ID
	return '${currentProcessInstanceDBID}';
};
var __getProcessId = function(){// 流程实例ID
	return '${currentProcessInstanceId}';
};
var __getProcessName = function(){// 流程名称
	return '${currentWorkFlowDisplayName}';
};
var __getProcessState = function(){// 流程状态
	return '${processInstanceState}';
};
var __getCurrentTaskId = function(){// 当前任务ID
	return '${currentTaskId}';
};
var __getCurrentTaskName = function(){// 当前任务名称
	return '${currentTaskName}';
};
var __getCurrentUserId = function(){// 当前登陆用户ID
	return '${CurrentUserId}';
};
var __getCurrentUserName = function(){// 当前登陆用户名称
	return '${CurrentUserName}';
};
</script>
<script type="text/javascript">
	//显示流程图
	function toProcessActivePicture(){
		/*var processInstanceId =encodeURIComponent('${currentProcessInstanceId}');
		?deployId=&processInstanceId="+processInstanceId+"&randomNumber="+Math.random()*/
		var attachmentParams = {
			deployId:'${currentDeployId}',
			processInstanceId:escape(encodeURIComponent('${currentProcessInstanceId}')),
			randomNumber:Math.random(),
			jbpmWorkflowHistoryInfoUserId:"${jbpmWorkflowHistoryInfoUserId}"
	    };
		openFullScreenWindow("${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action",attachmentParams);
	}
	//流程历史信息
	function viewWorkflowHistoryDetail(){
		var infoFlag="history";
		if(getLazyLoadedObj(infoFlag,jQuery(document.body),function(){
			mini.get("id_workflowHistoryDetailInfoWindow").show('center','middle');
		})){
			mini.get("id_workflowHistoryDetailInfoWindow").show('center','middle');
		}
	}
	//流程历史信息
	function viewProjSummary(){
		<c:if test="${empty currentHistoryTaskInfo.keyOne}">
		    mini.alert("流程关键字为空,请先设定流程《${currentHistoryTaskInfo.workflowName}》的第一个流程关键字");
		    return;
		</c:if>
	    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne=${currentHistoryTaskInfo.keyOne}&proj_id=${requestScope['project_info.projId']}";
	    openFullScreenWindow(URL);
	}
	//打开历史表单
	function toProcessForm(currentTaskId){
		window.open("${pageContext.request.contextPath}/jbpm/viewHistoryProcessForm.action?currentTaskId="+currentTaskId,"_blank");
	}
	//打印预览
	function previewPrint(){
		var url=getRootPath()+"/jbpm/getPrint.acl";
    	url+="?DocNo="+"${currentProcessInstanceDBID}";
    	url+="&jhiCompletedTaskImplId="+"${currentJbpmWorkflowHistoryInfoId}";
    	window.open(url,"_blank");
	}
	//弹出流程对比选择流程框
	function flowDataContrast(){
		var flag = event.srcElement.parentNode.getAttribute("flag")||"compare";
		if(flag=="compare"){
			var keyone="${currentHistoryTaskInfo.keyOne}";
			var curstepid=currentJbpmWorkflowHistoryInfoId;
			if(keyone==""){alert("没有设计关键字one请设置");return false;}
			mini.open({
				id:'id_flowselectwindow',
	            url: getRootPath() + '/workflow/jbpm-core/selectflowDataContrast.bi?keyone='+keyone+"&stepid="+curstepid,
	            title: "选择对比流程", width: 800, height: 500,
	            showModal: true,
	            showMaxButton: true,
	            onload: function () {
	            },
	            ondestroy: function (action) {
	            }
	        });
		}else{
			cancelFlowDifInputRed();
		}
	}
	//标记流程中的不同数据
	function setFlowDifInputRed(difDate){
		  mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在比较数据中   请稍等...'});
		   mini.get("#id_flowselectwindow").hide();
		   var mobj=difDate;
		   for(var key in mobj){  
			   if(key.indexOf("rawValue_")<0){
				   if(key.indexOf("json_")==0){
				   //处理多行控件
				   var gridid=key.substring(key.indexOf("json_")+5);
				   gridid=gridid.substring(0,gridid.length-4);
				   var rendto="id_panelContainer_"+gridid;
				   var windowdiv=$("#"+rendto);
				   var tooldiv=windowdiv.find("div[class='mini-toolbar']");
					   if(tooldiv.length>0){			
						   var span='<span class="separator"></span><span id="id_compare_grid_'+gridid+'"';					 
						   span=span+'    toolitemidentifier="'+gridid+'历史数据"><a class="mini-button mini-button-plain" href="javascript:void(0)" id="'+gridid+'_历史数据">';
						   span=span+' <span class="mini-button-text  mini-button-icon icon-update" style="">历史数据</span></a></span>';
						   tooldiv.append(span);	
						   var tempkey=key;
						   $("#id_compare_grid_"+gridid).bind("click",function(){
							   var tableContent=mobj[tempkey];
							   showTabelDifData(gridid,tableContent)
						   });
					   }
				   }else{
				   //处理input	   
				   var inputs=$("input[name='"+key+"'],textarea[name='"+key+"']");
				   for(var i=0;i<inputs.length;i++){
					   var oneinput=inputs[i];
					   var sp=oneinput.parentNode;
				       var tempsp=sp.parentNode;
				       if(tempsp.nodeName=="SPAN"){
				    	   if(tempsp.style.display=="none"){
				    		   continue;
				    	   }
				       }
					   if(sp.nodeName=="SPAN"){
						sp.isFlowDateCompare=true;
					    sp.style.border="3px solid red";
					    var img=document.createElement("img")
					    img.src="${pageContext.request.contextPath}/images/d02.png";
					    var tclass=sp.getAttribute("class")||"";
					    if(tclass.indexOf("mini-combobox")>0){
					    	img.mess=mobj["rawValue_"+key];
					    }else{
					    	 img.mess=mobj[key];
					    }
					    img.height=20;
					    img.isFlowDateCompare=true;
					    img.onclick=function(){mini.alert("原值:"+this.mess);}	
					    while(sp.nodeName!="TD"){
					    	sp=sp.parentNode;
					    }
					    sp.appendChild(img);
					   }
				   }
			   }}
		   }
		   //改变按钮的文字
		   $("#id_viewFlowDataContrast>span").text("取消流程对比");
		   $("#id_viewFlowDataContrast").attr("flag","cancel");  
		   mini.unmask(document.body);
	}
	function showTabelDifData(id,cdate){
		mini.parse();
		var grid=mini.get(id);
		var twin=mini.open({
			id:'id_flowselectwindow',
            title: "历史数据", width: globalClientWidth - 30, height: 300,
            showModal: false,
            showMaxButton: true,
            onload: function () {
            },
            ondestroy: function (action) {
            }
        });
		var twinbody=twin.getBodyEl();
		var temp=' <div id="id_flowselectwindow_datagrid" class="mini-datagrid" style="width:'+(globalClientWidth - 30)+';height:300px;"';
		temp=temp+ ' allowCellSelect="true",showPager="false">';
		twinbody.innerHTML=temp;
		mini.parse();
		var cdata=mini.get("id_flowselectwindow_datagrid");
		cdata.set({columns:mini.decode(mini.encode(grid.columns))});
		cdata.set({data:mini.decode(cdate)});
	}
	//取消流程数据对比
	function cancelFlowDifInputRed(){
		$("#id_viewFlowDataContrast>span").text("流程数据对比");
		$("#id_viewFlowDataContrast").attr("flag","compare");
		var imgs=$("img[src$='d02.png']")//删除提示信息
		for(var i=0;i<imgs.length;i++){
			imgs[i].parentNode.removeChild(imgs[i]);
		}
		var spans=$("span[isFlowDateCompare='true']");//删除红框
		for(var i=0;i<spans.length;i++){
		    spans[i].style.border='';
		}
		var spans=$("span[style*='border: 3px solid red']");//删除红框
		for(var i=0;i<spans.length;i++){
		    spans[i].style.border='';
		}
		var spans=$("span[id*='id_compare_grid_']");//删除
		for(var i=0;i<spans.length;i++){
			var before=spans[i].previousSibling;
			if(before!=null){
				if(before.getAttribute("class")=="separator"){
					spans[i].parentNode.removeChild(before);
				}
			}
		    spans[i].parentNode.removeChild(spans[i]);
		}
	}
	//删除流程实例
	function removeProcessInstance(){
	    try{
	 	   closeWindow();
	 	}catch(e){}
		/*if(isDraft && !window.isCompletedTask){
		   if(!window.loadMask)
		   {
		    	window.loadMask = new tracywindyLoadMask(document.body,"数据处理中，请稍等...");
		   }
		   window.loadMask.show();
		   globalCurrentPressButtonText = deleteButtonNoSavedDisplayText;
		   document.getElementById('id_currentTaskSubmitButtonText').value = globalCurrentPressButtonText;
		   submitFormWithoutWorkflowNextCallBack();
		}else{
			try{
			   closeWindow();
			}catch(e){}
		}*/
		/*try{
		    if(window.top.opener)
		    {
			    window.top.opener.location.reload();
			}
		}catch(e){}
		closeWindow();*/
	}
</script>
<script type="text/javascript"> 
	/*window.onbeforeunload = onbeforeunload_handler; 
	window.onunload = onunload_handler; 
	function onbeforeunload_handler(){ 
	    var warning="确认退出?";    
	    return window.confirm(warning); 
	} 
	    
	function onunload_handler(){ 
	    var warning="谢谢光临"; 
	    mini.alert(warning); 
	} */
	/*if(isDraft && !window.isCompletedTask){
		window.onbeforeunload = function(e){    
		   var ev = getEvent(e);
	       var n = ev.screenX - window.screenLeft;    
	       var b = n >= (document.documentElement.clientWidth-30);    
	       if(b && ev.clientY < 0 || ev.altKey)    
	       { 
	         removeProcessInstance();
	         try{
	 		    if(window.opener)
	 		    {
	 			    window.opener.location.reload();
	 			}
	 		}catch(e){}
	         try{
	        	closeWindow();
	         }catch(e){}
	         cancelBubble(ev);
	       } 
	    }; 
	}*/

</script> 
