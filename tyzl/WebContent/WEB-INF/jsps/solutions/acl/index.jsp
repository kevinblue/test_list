<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>用户主页</title>
	<%@include file="/base/minibase.jsp"%>
	<script src="${pageContext.request.contextPath}/js/base/tenwa.js?<mini:param  name='currentTimestamp' scope="page"/>" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		var pendingLoaded	= false;
		var noticeLoaded	= false;
		var quickMenuLoaded = false;
		//加载待办
		function loadPendingInfo(){
			pendingLoaded = false;
			tenwaAjax.showLoadMask();
			tenwaAjax.ajaxRequest({
				url:"${pageContext.request.contextPath}/table/getTableData.action",
				async:true,
				success:function(response) { 
					var rt = (response.responseText);
					var jsonData = mini.decode(rt);
					var $topLeftContentDiv = jQuery("#id_top_left_content_div");
					$topLeftContentDiv.html("");
					if((typeof(jsonData.norecord)!='undefined')&&(jsonData.norecord=='true')) {
						$topLeftContentDiv.html("暂无需要处理的待办任务!");
					} else {
						var datas = jsonData.datas;
						var pendingTable = document.createElement("table");
						var $pendingTable = jQuery(pendingTable);
						var tableWidth = "99.9%";
						$pendingTable.css({width:tableWidth});
						$topLeftContentDiv[0].appendChild(pendingTable);
						var pendingTbody = document.createElement("tbody");
						pendingTable.appendChild(pendingTbody);
						for(var i=0;i<datas.length;i++){
							var pendingTr=document.createElement("tr");
							var bgClass ="x-panel-table-data-row-";
							if((i+1)%2==0) {bgClass+="even";} else {bgClass+="odd";}
							pendingTr.className = bgClass;
							pendingTbody.appendChild(pendingTr);
							var data			= datas[i];
							var projectname	    = data["projectname"]||"";
							var workflowname	= data["workflowname"];
							var taskname		= data["taskname"];
							var taskstart		= data["taskstart"];
							var tasktype		= data["tasktype"];
							var taskid			= data["taskid"];
							var actorid		    = data["actorid"];
							var contractid      = data["contractid"];
							var taskbutton      =data["taskbutton"];
							var showtitle="";
							var checktype="";
							if(taskbutton=="Back"){
								taskbutton='<img  src="${pageContext.request.contextPath}/css/miniui/icons/undo.gif"  alt="待办退回"></img>';
								showtitle="退回待办"
							}else{
								taskbutton='';
								showtitle="";
							}
							var taskTypeChineseName = getTaskTypeChineseName(tasktype);
							taskstart=mini.formatDate ( taskstart,"yyyy-MM-dd HH:mm:ss")
							var content		="[ "+taskTypeChineseName+" ( "+taskstart+" ) "+"]&nbsp;";
							if(contractid != null && contractid != ""){
									content+=workflowname+" -> "+taskname+" -> "+contractid+" -> "+projectname ;		
							}else{
									content+=workflowname+" -> "+taskname+" -> "+projectname ;
							}
							var tempHtml ='</input><a href="javascript:void(0);" onclick="toProcessForm('+taskid+',\''+actorid+'\',\''+tasktype+'\')"  title="'+showtitle+'">'+content+'</a>'+taskbutton;
							var pendingTd		=  document.createElement("td");
							pendingTd.innerHTML	= tempHtml;
							pendingTr.appendChild(pendingTd);
						}
					}
					pendingLoaded	= true;
					if(noticeLoaded && quickMenuLoaded){
						tenwaAjax.hideLoadMask();
					}
				},
				failure:function(response){ },
				params:{
					xmlFileName:'/jbpm/queryPendingTasks.xml',
					USERID:"${sessionScope['login_userid']}",
					NOTPROCESSINSTANCESTATE:'Draft',
					globalQueryText:mini.get("id_queryPendingsTableInput").getValue(),
					PENDING:true
				}
			});
		}
		function toProcessForm(currentTaskId,planActorId,tasktype) {
			var url = "${pageContext.request.contextPath}/jbpm/requestProcessTaskForm.action?currentTaskId="+currentTaskId+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&currentRequestTaskType="+tasktype;
			tenwaAjax.openFullScreenWindow(url);
		}
		var newsNoticeContentPrefix = "id_newsNoticeContent_";
		function loadNoticeInfo(){
			noticeLoaded = false;
			//公告通知
			tenwaAjax.ajaxRequest({
				url:"${pageContext.request.contextPath}/table/getTableData.action",
				async:true,
				success:function(response) {	
					var rt = (response.responseText);
					var jsonData = mini.decode(rt);
					var $topMiddleContentDiv = jQuery("#id_top_middle_content_div");
					if((typeof(jsonData.norecord)!='undefined')&&(jsonData.norecord=='true')) {
						$topMiddleContentDiv.html("暂未发布新公告!");
					} else {
						var datas = jsonData.datas;
						var noticeTable = document.createElement("table");
						var $noticeTable = jQuery(noticeTable);
						$noticeTable.css({width:'99.8%'});
						$topMiddleContentDiv[0].appendChild(noticeTable);
						var noticeTbody = document.createElement("tbody");
						noticeTable.appendChild(noticeTbody);
						var currentDate = mini.formatDate (new Date(),"yyyy-MM-DD"); 
						for(var i=0;i<datas.length;i++){
							var noticeTr		=  document.createElement("tr");
							var bgClass ="x-panel-table-data-row-";
							if((i+1)%2==0) {
								bgClass+="even";
							} else {
								bgClass+="odd";
							}
							noticeTr.className = bgClass;
							noticeTbody.appendChild(noticeTr);
							var data		= datas[i];
							var title		= data["title"]||"";
							var content	= data["content"];
							var publishDate = data["publishdate"];
							publishDate=mini.formatDate ( publishDate,"yyyy-MM-dd HH:mm:ss")
							var noticeTitleTd		=  document.createElement("td");
							/*content = content.replace(/(\r|\n)/gim,"");
							content = content.replace(/\"/gim,'&quot;');
							content = content.replace(/\'/gim,"&acute");*/
							var tempHtml	= "<a title='查看详细内容' href='javascript:void(0);' onclick=\"preview("+i+")\">"+title+"</a>";
							if( currentDate == publishDate.substring(0,10)) {
								tempHtml+="&nbsp;<span class='newsNotice'></span>";
							}
							noticeTitleTd.innerHTML	= tempHtml;
							var newsNoticeContent= document.createElement("div");
							with(newsNoticeContent){
								id=newsNoticeContentPrefix+i;
								innerHTML = content;
								style.display="none";
							}
							noticeTitleTd.appendChild(newsNoticeContent);
							noticeTr.appendChild(noticeTitleTd);
							var noticePublishDateTd		=  document.createElement("td");
							noticePublishDateTd.style.textAlign="right";
							noticePublishDateTd.innerHTML	= publishDate;
							noticeTr.appendChild(noticePublishDateTd);
						}
					}
					noticeLoaded = true;
					if(pendingLoaded && quickMenuLoaded){
						tenwaAjax.hideLoadMask();
					}
				},
				failure:function(response){ },
				params:{
					xmlFileName:'/acl/queryAllNotice.xml',
					deadline:true
				}
			});
		}
		function preview(rowIndex){
			var content = jQuery("#"+newsNoticeContentPrefix+rowIndex).html();
			var previewDetailInfoWindowContainer = mini.get("id_previewDetailInfoWindowContainer");
			var $previewDetailInfo = jQuery("#id_previewDetailInfo");
			$previewDetailInfo.html(content);
			previewDetailInfoWindowContainer.show('center','middle');
		}
		//快捷操作
		function recursionFindFirstLevelIndex(menuJson,newDataArr,compareDataArr){
			var children = menuJson.children;
			var childrenLen = children.length;
			if(0 < childrenLen){
				for(var i=0;i<childrenLen;i++){
					recursionFindFirstLevelIndex(children[i],newDataArr,compareDataArr);
				}
			}else{
				var checked = menuJson.checked;
				var currentId = menuJson.id;
				if("true" == (checked+"")){
					for(var ii=0;ii<compareDataArr.length;ii++){
						var compareData = compareDataArr[ii];
						var compareId	= compareData.id_;
						if(currentId == compareId){
							var newObj = {
								id_  :compareData.id_,
								name_:compareData.name_,
								icon_:compareData.icon_,
								firstLevelMenuId:menuJson.firstLevelMenuId
							};
							newDataArr.push(newObj);
							break;
						}
					}
				}
			}
		}
		function loadQuickMenuInfo(){
			quickMenuLoaded = false;		
			tenwaAjax.ajaxRequest({
				url:"${pageContext.request.contextPath}/table/getTableData.action",
				async:true,
				success:function(response){
					var rt = (response.responseText);
					if(!rt)$topRightContentDiv.html("您的用户信息发生变动，请联系管理员同步用户权限!");
					var jsonData = mini.decode(rt);
					var $topRightContentDiv = jQuery("#id_top_right_content_div");
					if((typeof(jsonData.norecord)!='undefined')&&(jsonData.norecord=='true')) {
						$topRightContentDiv.html("暂未定义快捷操作!");
					} else {
						var compareDataArr = jsonData.datas;
						var newDataArr		= [];
						tenwaAjax.ajaxRequest({
							url:'${pageContext.request.contextPath}/acl/getUserQuickMenuTreeData.acl',
							success:function(res){
								var jsonChildren = mini.decode(res.responseText);
								for(var k = 0;k < jsonChildren.length;k++){
									recursionFindFirstLevelIndex(jsonChildren[k],newDataArr,compareDataArr);
								}
							    var $mtable=$("<table></table>");
							    $topRightContentDiv.append($mtable);
							    var skipindex=3;
							    var $tr=null;
								for(var i=0;i<newDataArr.length;i++) {
									if(i%skipindex==0){
									   $tr=$("<tr></tr>");
									   $mtable.append($tr);
									}
									var $td=$("<td></td>");
									var data			= newDataArr[i];
									var menuId			= data["id_"];
									var text			= data["name_"];
									var icon 			= data["icon_"];
									var firstLevelMenuId = data["firstLevelMenuId"];
									var $divContainer = $("<div></div>");
									$divContainer.css("cursor","pointer");
									$divContainer.css("height","22px");
									$divContainer.css("margin-right","10px");
									$divContainer.addClass("btn-primary");
									var clickFunc = (function(firstLevelMenuId,menuId){
										return function(e){
											window.top.doCheckTreeByMenuId(firstLevelMenuId,menuId);
										};
									})(firstLevelMenuId,menuId);
									$divContainer.click(clickFunc);
									var $img = $("<img src='${pageContext.request.contextPath}/menuIcons/"+icon+"'/>");
									$img.css("verticalAlign","middle");
									var temp="";
								     if(text.length>5){
								    	 temp=text.substring(0,5)+"...";
								     }else{ temp=text};
									var $text = $("<a name='"+menuId+"' title='"+text+"'>"+$img[0].outerHTML+"&nbsp;"+temp+"</a>");
									$divContainer.append($text);
									$td.append($divContainer);
									$tr.append($td);
								}
							}
						});
					}
					quickMenuLoaded = true;
					if(pendingLoaded && noticeLoaded){
						tenwaAjax.hideLoadMask();
					}
				},
				params:{
					userId:"${sessionScope['login_userid']}",
					xmlFileName:'acl/queryUserQuickMenu.xml'
				}
			});
		}
		jQuery(function(){
			mini.parse();
			loadPendingInfo();
			loadNoticeInfo();
			loadQuickMenuInfo();
		});
	</script>
</head>
<body>
	<div id='id_top_left' class="mini-panel" title="待办事宜" style="float:left;">
		<div style="position: fixed;margin-top: -28px;margin-left: 70px;">
			<input id="id_queryPendingsTableInput" onenter="loadPendingInfo" class="mini-textbox" style="width: 150px; margin-left: 5px;">
			<a class="mini-button" onclick="loadPendingInfo" iconCls="icon-search">搜索</a>
		</div>
		<div id="id_top_left_content_div" style="overflow:auto;width:100%;"></div>
	</div>

	<div id='id_top_middle' class="mini-panel" title="公告通知" style="float:right;overflow:hidden;">
		<div id='id_top_middle_content_div' style="overflow:auto;"></div>
	</div>
	
	<div id='id_top_right' class="mini-panel" title="快捷操作" style="float:right;overflow:hidden;margin-top: 3px;">
		<div id='id_top_right_content_div' style="overflow:auto;"></div>
	</div>
	<!--显示公告内容 -->	
	<div id="id_previewDetailInfoWindowContainer" class="mini-window" width="800px" ,heigth="600px" showCloseButton="true" showModal="true" allowDrag="true" title="公告内容" style="display:none;">
		<div id="id_previewDetailInfo" style="text-align:left;padding:5px"></div>
	</div>
	<script type='text/javascript'>
		var pageWidth = document.documentElement.clientWidth;
		var pageHeight = document.documentElement.clientHeight;
		
		var topRightWidth = 450;
		var topRightHeight = (pageHeight - 3)/2;//减去3是右边2个模块的间距
		
		var topLeftHeight = pageHeight;
		var topLeftWidth = pageWidth - topRightWidth - 3;//再减去3是减去左右两块的间距	
		/* 
		var bottomHeight = pageHeight/3;
		var bottomLeftWidth = topLeftWidth  ;
		var bottomRightWidth = pageWidth - bottomLeftWidth; 
		*/
		jQuery("#id_top_left").css("height",topLeftHeight+"px");
		jQuery("#id_top_middle").css("height",topRightHeight + "px");
		jQuery("#id_top_right").css("height",topRightHeight + "px");
		
		jQuery("#id_top_left").css("width",topLeftWidth+"px");
		jQuery("#id_top_middle").css("width",topRightWidth + "px");
		jQuery("#id_top_right").css("width",topRightWidth + "px");
		
		jQuery("#id_top_left_content_div").css("height",(topLeftHeight - 40)+"px");//减去标题等高度
		//jQuery("#id_top_middle_content_div").css("height",(topRightHeight - 30) + "px");//减去标题等高度
		jQuery("#id_top_right_content_div").css("height",(topRightHeight - 40) + "px");//减去标题等高度
		
		//jQuery("#id_top_left_content_div").css("width",(topLeftWidth - 20)+"px");//减去标题等高度
		//jQuery("#id_top_middle_content_div").css("width",(topRightWidth-5) + "px");//减去标题等高度
		jQuery("#id_top_right_content_div").css("width",(topRightWidth-5) + "px");//减去标题等高度
		/* 
		jQuery("#id_bottom_left").css("height",bottomHeight+"px");
		jQuery("#id_bottom_left").css("width",bottomLeftWidth+"px");
		
		jQuery("#id_bottom_right").css("height",bottomHeight+"px");
		jQuery("#id_bottom_right").css("width",bottomRightWidth+"px");
		*/
	</script>
</body>
</html>