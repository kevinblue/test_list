<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理 - 发布</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/js/kindeditor-4.1.7/themes/default/default.css" rel="stylesheet" type="text/css"/>
	<!--javascript libray-->
		<%@include file="/base/mini.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor-4.1.7/kindeditor-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor-4.1.7/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyKindEditorPluginInsertFile.js"></script>
	
	

	<style type="text/css">
	   html,body{overflow:hidden;}
	   td.td-content-title{
	     width:100px;
	   }
	   .window-body div{
	     text-align:center;
	   }
	</style>
<%
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>  
</head>
<body>
<%---Table展示 --%>
 <div id="id_tableContainer"></div>
 <%---发布的增加内容 --%>
<div id="id_detailInfoWindowContainer" title="发布" style="display: none; width: 800px; ">
<center>
<form id="id_detailInfoForm">
<table style="width: 100%;border: 1px #DDD solid;" class="fillTable" >
		<tr>
			<td>
				<input name="id" type="hidden" value=""/>
				<input type="hidden" name="readstatus" id="readstatus" value=""/>
				<input type="hidden" name="fromuser" id="fromuser" value=""/>
				<input type="hidden" name="msgtype" id="msgtype" value=""/>
           	</td>
           	<td></td><td></td><td></td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">主题:</td>
			<td class="td-content" colspan=3>
           		<input type="text" name="msgtitle" class="td-content-input" require="true" label="主题"  maxB="80" style="width:400px;"/><font class="required-content">*</font>
           	</td>
		</tr>
	    <tr class="tr-odd">
		    <td class="td-content-title" align="left">接收人类型:  </td>  
	        <td class="td-content" align="left">
	           	<div id="id_message_tousertype" style="float:left;"></div><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>
	    </tr>
	    <tr class="tr-even" id="users" style="display: none;">
			<td class="td-content-title" align="left">接收人员:</td>
			<td class="td-content" align="left" >
			 
           		<div id="id_message_tousers" style="float:left;"></div><font class="required-content">*</font>
           		
           		<input id="lookup2" name="look" class="mini-lookup" style="width:200px;" 
			        textField="name" valueField="id" popupWidth="auto" 
			        popup="#gridPanel" grid="#datagrid1" multiSelect="true" value="5e9d1625-d14e-49f2-b618-efcaadeeca71" text="abc"
			    />
			
			    <div id="gridPanel" class="mini-panel" title="header" iconCls="icon-add" style="width:450px;height:250px;" 
			        showToolbar="true" showCloseButton="true" showHeader="false" bodyStyle="padding:0" borderStyle="border:0" 
			    >
			        <div property="toolbar" style="padding:5px;padding-left:8px;text-align:center;">   
			            <div style="float:left;padding-bottom:2px;">
			                <span>姓名：</span>                
			                <input id="keyText" class="mini-textbox" style="width:160px;" onenter="onSearchClick"/>
			                <a class="mini-button" onclick="onSearchClick">查询</a>
			                <a class="mini-button" onclick="onClearClick">清除</a>
			            </div>
			            <div style="float:right;padding-bottom:2px;">
			                <a class="mini-button" onclick="onCloseClick">关闭</a>
			            </div>
			            <div style="clear:both;"></div>
			        </div>
			        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
			            borderStyle="border:0" showPageSize="false" showPageIndex="false"
			            url="<%=prefix %>/eleasing/jsp/base/t_user_info.xml" 
			        >
			            <div property="columns">
			                <div type="checkcolumn" ></div>
			                <div field="name" width="120" headerAlign="center" allowSort="true">姓名</div>
			                <div field="value" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">id</div>                
			            </div>
			        </div>  
			    </div>
           		
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>		
		</tr>
		<tr class="tr-odd" id="groups" style="display: none;" >
			<td class="td-content-title" align="left">接收群组:</td>
			<td class="td-content" align="left">
           		<div id="id_message_togroups" style="float:left;"></div><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>		
		</tr>
		<tr class="tr-even" id="depts" style="display: none;" >
			<td class="td-content-title" align="left">接收部门:</td>
			<td class="td-content" align="left">
           		<div id="id_message_todepts" style="float:left;"></div><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>		
		</tr>
		<tr class="tr-odd" id="roles" style="display: none;" >
			<td class="td-content-title" align="left">接收角色:</td>
			<td class="td-content" align="left">
           		<div id="id_message_toroles" style="float:left;"></div><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>		
		</tr>
		<tr class="tr-even" id="areas" style="display: none;" >
			<td class="td-content-title" align="left">接收区域:</td>
			<td class="td-content" align="left">
           		<div id="id_message_toareas" style="float:left;"></div><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title"></td>  
	        <td class="td-content"></td>		
		</tr>
	    <tr class="tr-odd">
           	<td class="td-content-title" align="left">新闻作者:</td>  
           	<td class="td-content">
           		<input name="fromusername" id="fromusername" label="登记人 " class="td-content-readonly" readonly="readonly" type="text" style="float:left;width:198px; height: 18px;"/><font class="required-content">*</font>
           	</td>
           	<td class="td-content-title" align="left">新闻时间:  </td>  
           	<td class="td-content" align="left">
           		<input name="senddate" id="senddate" dataType='date' class="Wdate td-content-input"  onClick="WdatePicker(this,{readOnly:true})" readOnly require="true"  label="新闻时间" />
           		<font class="required-content">*</font>
           	</td>
	    </tr>
		<tr class="tr-even">
			<td class="td-content-title" align="left" colspan=4>正文内容:</td>
		</tr>
		<tr>
		   	<td class="td-content" align="left" colspan=4>
           		<textarea name="msgtext" style="width:99.8%;height:300px;" label="正文内容"  class="td-content-input" require="true" type="text" ></textarea>
           	</td>
		</tr>
		<tr class="content-separator">
			<td colspan='4'>
				<a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='submitNotice();'><span>确定</span></a>
				<a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
			</td>
		</tr>
		
</table>
</form>
</center>
</div>
<!-- 增加富文本编辑框 -->
	<div id="id_previewDetailInfoWindowContainer"  closable="true" closed="true" modal="true" title="公告内容" style="display:none;width:1000px;">
	     <div id="id_previewDetailInfo" style="text-align:left;padding:5px"></div>
	</div>
	<form method="post" target="fileDownloadIframe" action="${pageContext.request.contextPath}/acl/downloadNoticeUploadFile.acl" id="id_nameForm">
	  <input name="type" type="hidden" value="file" />
	  <input name="uuid"  type="hidden" value=""/>
	</form>
	<iframe name="fileDownloadIframe" style="display:none;"></iframe>
	<script type="text/javascript">
	   function preview(rowIndex){
		   var rowData = getTracywindyObject("id_table").getRowDataAt(rowIndex);
		   var content = rowData["msgtext"];
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
	  var editor = null;
	</script>
<!--增加富文本编辑框结束 -->
<script type="text/javascript">
	var constantFlagTable = "User";
	var login_name = "${sessionScope['login_realname']}";
	var tools = '新增|修改';
	if(login_name == "超级管理员" || login_name == "系统管理员"){
		tools = '新增|修改|删除';
	}
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
		var combo_tousertype = new tracywindyComboBox({//接收类型
	    	lazyLoad:true,
	   		id:'id_combo_message_tousertype',
	   	    width:200,
	   	    renderTo:'id_message_tousertype',
	   	 	xmlFileName:'\\combos\\comboDict.xml',
	   	 	loadMode:'ajax',
	   	 	readOnly:false,
	   	 	isAjaxLenovo:true,
	   	 	otherAttributes:'require="true" label="接收类型"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'tousertype',
		   	displayField:'name',
		   	valueField:'value',
		   	leftAdd:0,
		    isCheck:true,
		   	params:{pid:'msggroup'},
		   	onSelect:showUserType
		 });
	   	var combo_toUser = new tracywindyComboBox({//接收人员
	    	lazyLoad:true,
	   		id:'id_combo_message_tousers',
	   	    width:200,
	   	    renderTo:'id_message_tousers',
	   	 	xmlFileName:'/eleasing/jsp/base/t_user_info.xml',
		   	loadMode:'ajax',
		    readOnly:false,
		    isAjaxLenovo:true,
		    isContainEmpty:false,
	   	 	otherAttributes:'label="接收人员"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'users',
		   	displayField:'value',
		   	valueField:'name',
		   	leftAdd:0,
		   	isCheck:true,
		   	params:{},
		   	onSelect:function(combo){}
		 });
	   	var combo_toUser = new tracywindyComboBox({//接收群组
	    	lazyLoad:true,
	   		id:'id_combo_message_togroups',
	   	    width:200,
	   	    renderTo:'id_message_togroups',
	   	 	xmlFileName:'/eleasing/jsp/base/t_group_info.xml',
	   	 	loadMode:'ajax',
	   	 	readOnly:false,
	   	 	isAjaxLenovo:true,
	   	 	otherAttributes:'label="接收群组"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'groups',
		   	displayField:'value',
		   	valueField:'name',
		   	leftAdd:0,
		    isCheck:true,
		   	params:{},
		   	onSelect:function(combo){}
		 });
	   	var combo_toUser = new tracywindyComboBox({//接收部门
	    	lazyLoad:true,
	   		id:'id_combo_message_todepts',
	   	    width:200,
	   	    renderTo:'id_message_todepts',
	   	 	xmlFileName:'/eleasing/jsp/base/t_dept_info.xml',
	   	 	loadMode:'ajax',
	   	 	readOnly:false,
	   	 	isAjaxLenovo:true,
	   	 	otherAttributes:'label="接收部门"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'depts',
		   	displayField:'value',
		   	valueField:'name',
		   	leftAdd:0,
		    isCheck:true,
		   	params:{},
		   	onSelect:function(combo){}
		 });
	   	var combo_toUser = new tracywindyComboBox({//接收角色
	    	lazyLoad:true,
	   		id:'id_combo_message_toroles',
	   	    width:200,
	   	    renderTo:'id_message_toroles',
	   	 	xmlFileName:'/eleasing/jsp/base/t_role_info.xml',
	   	 	loadMode:'ajax',
	   	 	readOnly:false,
	   	 	isAjaxLenovo:true,
	   	 	otherAttributes:'label="接收角色"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'roles',
		   	displayField:'value',
		   	valueField:'name',
		   	leftAdd:0,
		    isCheck:true,
		   	params:{},
		   	onSelect:function(combo){}
		 });
	   	var combo_toUser = new tracywindyComboBox({//接收区域
	    	lazyLoad:true,
	   		id:'id_combo_message_toareas',
	   	    width:200,
	   	    renderTo:'id_message_toareas',
	   	 	xmlFileName:'\\combos\\comboDict.xml',
	   	 	loadMode:'ajax',
	   	 	readOnly:false,
	   	 	isAjaxLenovo:true,
	   	 	otherAttributes:'label="接收区域"',
		   	dropListHeight:200,
		   	topAdd:0,
		   	leftAdd:0,
		   	positionDropIconLeftAdd:-1,
		   	name:'areas',
		   	displayField:'name',
		   	valueField:'value',
		   	leftAdd:0,
		    isCheck:true,
		   	params:{pid:'dealer_district'},
		   	onSelect:function(combo){}
		 });
	    var table = new tracywindyOperationTable({
	   		resetFormCallback:function(table,$form,operFlag){
				if("add" == operFlag){
					$("#users").hide();
					$("#groups").hide();
					$("#depts").hide();
					$("#roles").hide();
					$("#areas").hide();
					jQuery("#readstatus").val('1');
				    jQuery("#msgtype").val('msgtype.2');
				    jQuery("#fromuser").val('${sessionScope.loginUser.id}');
				    jQuery("#fromusername").val('${sessionScope.loginUser.realname}');
				}
	     	},
	    	  loadFormDataCallBack:function(table,$form,rowIndex){
	     		jQuery("#readstatus").val('1');
	     		jQuery("#msgtype").val('msgtype.2');
			    jQuery("#fromuser").val('${sessionScope.loginUser.id}');
			    jQuery("#fromusername").val('${sessionScope.loginUser.realname}');
	 			var rowData = table.getRowDataAt(rowIndex);
	 			var rowDataValue = rowData['usertype'];
	 			var currentCombo = getTracywindyObject("id_combo_message_tousertype");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
			    //判断显示与隐藏
			    $("#users").hide();
				$("#groups").hide();
				$("#depts").hide();
				$("#roles").hide();
				$("#areas").hide();
			    if(rowDataValue!=""){
					var checkLis=rowDataValue.split(",");
					for(var i=0;i<checkLis.length;i++){
						var temp=checkLis[i];
						if(temp=="msggroup_user"){
							$("#users").show();
						}
						if(temp=="msggroup_group"){
							$("#groups").show();
						}
						if(temp=="msggroup_dept"){
							$("#depts").show();
						}
						if(temp=="msggroup_roles"){
							$("#roles").show();
						}
						if(temp=="msggroup_district"){
							$("#areas").show();
						}
					}
				}
			    //接收人员
	 			rowDataValue = rowData['users'];
	 			currentCombo = getTracywindyObject("id_combo_message_tousers");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
			    //接收群组
	 			rowDataValue = rowData['groupss'];
	 			currentCombo = getTracywindyObject("id_combo_message_togroups");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
			    //接收部门
	 			rowDataValue = rowData['depts'];
	 			currentCombo = getTracywindyObject("id_combo_message_todepts");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
			    //接收角色
	 			rowDataValue = rowData['roless'];
	 			currentCombo = getTracywindyObject("id_combo_message_toroles");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
			    //接收区域
	 			rowDataValue = rowData['districts'];
	 			currentCombo = getTracywindyObject("id_combo_message_toareas");
	 			currentCombo.setValue(rowDataValue);
			    currentCombo.reload();
 			 },
 	         afterShowWindowCallBack:function($form){
 	            if(!editor){
 	    			editor = KindEditor.create('textarea[name="msgtext"]', {
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
 				editor.html($form.find('textarea[name="msgtext"]').val());
 	         },
 	         beforeSubmitData:function($form,params){
 	 	         try{
	 	        	 var content = editor.html().replace(/(\r|\n)/gim,"");
	 	        	 params["msgtext"]= content;
 	        	 }catch(e){}
 	         },
 	         validateFrom:function(table, $form){
 	        	 var content = editor.html().replace(/(\r|\n)/gim,"");
 	        	 $form.find("textarea[name='msgtext']").val(content);
 	        	 return true;
 	         },
	   		 tableComment:'[发布]',
	   		 constantFlagTable:'MessageInfo',
	   		 windowTop:20,
	   	     border:true,
	         renderTo:'id_tableContainer',
	         title:'发布',
	         width:parseInt("${param.tableWidth}")||pageWidth,
	         height:parseInt("${param.tableHeight}")||pageHeight,
	         id:'id_table',
	         xmlFileName:'/eleasing/jsp/base/publishMessageInfo.xml',
	         loadMode:'ajax',
	         isPage:true,
	         isFit:true,
	         operButtons:tools,
	         columns:[
		            {header:'id',name:'id',hidden:true},
		            {header:'users',name:'users',hidden:true},
		            {header:'groups',name:'groupss',hidden:true},
		            {header:'depts',name:'depts',hidden:true},
		            {header:'roless',name:'roless',hidden:true},
		            {header:'districts',name:'districts',hidden:true},
		            {header:'接收人类型',name:'usertype',hidden:true},
		            {header:'主题',name:'msgtitle',renderer:function(value,tableObj,columnName,columnIndex,rowData){
			           return "<a href='javascript:void(0);' onclick=\"preview("+rowData.rowIndex+")\">"+value+"</a>";       
			        }},
		            {header:'发布时间',name:'senddate'},
		            {header:'接收人',name:'realname'}
		        ],
		        params:{
	 			     	MSG_TYPE:"msgtype.2",FROM_USER:'${sessionScope["login_userid"]}'    
				}
	   	 });
	 });
	function showUserType(){
		var tempStr=getTracywindyObject("id_combo_message_tousertype").getValue();
		$("#users").hide();
		$("#groups").hide();
		$("#depts").hide();
		$("#roles").hide();
		$("#areas").hide();
		if(tempStr!=""){
			var checkLis=tempStr.split(",");
			for(var i=0;i<checkLis.length;i++){
				var temp=checkLis[i];
				if(temp=="msggroup_user"){
					$("#users").show();
				}
				if(temp=="msggroup_group"){
					$("#groups").show();
				}
				if(temp=="msggroup_dept"){
					$("#depts").show();
				}
				if(temp=="msggroup_roles"){
					$("#roles").show();
				}
				if(temp=="msggroup_district"){
					$("#areas").show();
				}
			}
		}
	}
	function submitNotice(){//提交表单
		var tempStr=getTracywindyObject("id_combo_message_tousertype").getValue();
		if(tempStr!=""){
			var checkLis=tempStr.split(",");
			for(var i=0;i<checkLis.length;i++){
				var temp=checkLis[i];
				if(temp=="msggroup_user"){
					if($("input[name='users']").val()==""){
						alert("请输入接收人员！");
						return false;
					}
				 }
				 if(temp=="msggroup_group"){
					if($("input[name='groups']").val()==""){
						alert("请输入接收群组！");
						return false;
					}
				 }
				 if(temp=="msggroup_dept"){
					if($("input[name='depts']").val()==""){
						alert("请输入接受部门！");
						return false;
					}
				 }
				 if(temp=="msggroup_roles"){
					if($("input[name='roles']").val()==""){
						alert("请输入接收角色！");
						return false;
					}
				 }
				 if(temp=="msggroup_district"){
					if($("input[name='areas']").val()==""){
						alert("请输入接收区域！");
						return false;
					}
				 }
			}
		}
		getTracywindyObject("id_table").operationTable();
	}
</script>	
	
</body>
</html>