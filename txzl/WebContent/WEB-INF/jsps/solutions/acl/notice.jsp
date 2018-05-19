<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>公告管理</title>
		<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.7/themes/default/default.css" rel="stylesheet" type="text/css"/>
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor-4.1.7/kindeditor-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyKindEditorPluginInsertFile.js"></script>
    <style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	   .window-body div{
	     text-align:center;
	   }
	</style>
<script type="text/javascript">
</script>
<script type="text/javascript">
    var constantFlagTable = "User";
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	var editor = null;
	jQuery(function(){
	 jQuery("#id_previewDetailInfoWindowContainer").css("height",(jQuery(window).height()-40)+"px");
	 jQuery("#id_detailInfoWindowContainer textarea[name='content']").css("height",(jQuery(window).height()-230)+"px");
   	 var table = new tracywindyOperationTable({
   		 tableComment:'公告',
   		 limit:20,
   		 constantFlagTable:'Notice',
   	     isNeedEnabled:false,
   	     isNeedEnabledQuery:false,
   	     operButtons:'新增|修改|删除|全局搜索',
   	     border:true,
         renderTo:'id_tableContainer',
         title:'公告信息列表',
         width:pageWidth,
         height:pageHeight,
         id:'id_table',
         xmlFileName:'/acl/queryAllNotice.xml',
         loadMode:'ajax',
         isPage:true,
         isFit:true,
         border:true,
         windowTop:10,
         afterShowWindowCallBack:function($form){
            if(!editor){
    			editor = KindEditor.create('textarea[name="content"]', {
    				allowFileManager : false,
    				allowImageRemote:false,
    				items:[
    				        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
    				        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
    				        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
    				        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
    				        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
    				        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
    				        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
    				        'anchor', 'link', 'unlink'/*, '|', 'about'*/
    				],
    				uploadJson       :'${pageContext.request.contextPath}/acl/uploadNoticeFile.acl'
    			});
            }
			editor.html($form.find('textarea[name="content"]').val());
         },
         beforeSubmitData:function($form,params){
             try{
	        	 var content = editor.html().replace(/(\r|\n)/gim,"");
	        	 params["content"]= content;
             }catch(e){}
         },
         isNewLineQueryButton:true,
         queryButtonColSpan:4,
         columns:[
		            {header:'记录编号',name:'id',hidden:true},
		            {header:'公告主题',name:'title'},
		            {header:'公告内容',align:'center',name:'content',renderer:function(value,tableObj,columnName,columnIndex,rowData){
			              /*value = value.replace(/(\r|\n)/gim,"");
			              value = value.replace(/\"/gim,'&quot;');
			              value = value.replace(/\'/gim,"&#039;");*/
			              return "<a href='javascript:void(0);' onclick=\"preview("+rowData.rowIndex+")\">查看详细内容</a>";       
			        }},
		            {header:'创建人'  ,name:'creator'},
		            {header:'创建日期',name:'createdate'},
		            {header:'发布时间',name:'publishdate'},
		            {header:'截止时间',name:'deadlinedate'}
	        ]
   	 });
   });
   function preview(rowIndex){
	   var rowData = getTracywindyObject("id_table").getRowDataAt(rowIndex);
	   var content = rowData["content"];
	   var $previewDetailInfoWindowContainer = jQuery("#id_previewDetailInfoWindowContainer");
	   $previewDetailInfoWindowContainer.show();
	   var $previewDetailInfo = jQuery("#id_previewDetailInfo");
	   $previewDetailInfo.html(content);
	   addNoticeAttachmentDownloadFunction();
	   $previewDetailInfoWindowContainer.window({top:20});
	   $previewDetailInfoWindowContainer.window('open');
   }
   function addNoticeAttachmentDownloadFunction()
   {
	   jQuery("#id_previewDetailInfo a.tracywindyFileDownLoadSpan").each(function(i){
		   this.style.cursor = "pointer";
		   this.title = "点击下载文件";
		   this.style.color = "blue";
		   this.href="javascript:void(0)";
		   this.style.fontWeight = "BOLD";
		   var oldClassStr = (this.className||"").replace(/\s{1,}/gm," ");
		   var oldClassArr = oldClassStr.split(" ");
		   for(var i=0;i<oldClassArr.length;i++){
			   var className = oldClassArr[i]||"";
			   if(0 == className.indexOf("uuid-")){
				   var uuid = className.substring("uuid-".length,className.length);
				   var clickFunc = (function(uuid){
					   return function(e){
						   jQuery("#id_nameForm input[name='uuid']").val(uuid);
						   jQuery("#id_nameForm")[0].submit();
						   return false;
					   };
				   })(uuid);
				   this.onclick = clickFunc;
			   }
		   }
	   });
  }
</script>
</head>
<body>
    <div id='id_transferDiv'></div>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer" buttons="#id-dlg-buttons" closable="false" closed="true" modal="true" title="公告信息管理" style="display:none;width:1000px;padding-top:10px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:98%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">公告主题:</td>  <td class="input_value" style="text-align:left;"><input style="width:95%;" name="title"   require="true" label="公告主题"/>  <span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">发布时间:</td>  <td class="input_value" style="text-align:left;"><input readonly class="Wdate td-content-input" onclick="WdatePicker(this,{readOnly:true,dateFmt:'yyyy-MM-dd hh:mm:ss'});" name="publishDate"  require="true" label="发布时间" type="text"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">截止时间:</td>  <td class="input_value" style="text-align:left;"><input readonly class="Wdate td-content-input" onclick="WdatePicker(this,{readOnly:true,dateFmt:'yyyy-MM-dd hh:mm:ss'});" name="deadlineDate" require="true" label="截止时间" type="text"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">公告内容:</td>  <td ><textarea   style="width:95%;height:320px;" name="content" ></textarea></td></tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div style="text-align:center;display:none;height:40px;line-height:30px;" id="id-dlg-buttons">
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='getTracywindyObject("id_table").operationTable();'><span>保存修改</span></a>
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>忽略变动</span></a>
	</div>
	<div id="id_previewDetailInfoWindowContainer"  closable="true" closed="true" modal="true" title="公告内容" style="display:none;width:1000px;">
	     <div id="id_previewDetailInfo" style="text-align:left;padding:5px"></div>
	</div>
	<form method="post" target="fileDownloadIframe" action="${pageContext.request.contextPath}/acl/downloadNoticeUploadFile.acl" id="id_nameForm">
	  <input name="type" type="hidden" value="file" />
	  <input name="uuid"  type="hidden" value=""/>
	</form>
	<iframe name="fileDownloadIframe" style="display:none;"></iframe>
</body>
</html>