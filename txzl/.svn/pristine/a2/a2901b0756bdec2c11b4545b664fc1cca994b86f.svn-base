<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WORD模板 - 管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAttachmentFileUpload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
	<style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
    function importExcelFormCallBack(){
    	window.location.reload();
    }
    function downloadWordConfigUploadFile(wordId)
       {
        if(null==window.attachmentFileUploadloadMask)
       {
          attachmentFileUploadloadMask = new tracywindyLoadMask(document.body,"操作进行中 请稍等...");
          createAttachmentFileContainers();
      }
         attachmentFileUploadloadMask.show();
       var newAction = getRootPath()+"/leasing/word/downLoadAttach.action?browserType="+SysBrowser.getBrowser().toLowerCase();
        newAction+="&Id="+wordId;
         uploadAttachmentFileFormSubmit(newAction);
        attachmentFileUploadloadMask.hide();
    } 
    function deleteFileAttache(id,url){
        var params={};
        params["currentId"]=id;
    	if(!window.currentDeleteFileLoadMask)
    	{
    	window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"文件正在删除 请稍等...");
    	} 
    	window.currentDeleteFileLoadMask.show();
    	ajaxRequest({
    		url:getRootPath()+url,
    		success:function(res){
    		    window.location.reload();
    		 },
    		failure:function(res){},
    		params:params 
    	});
    }
    function showfile(value,tableObj,columnName,columnIndex,rowData){
        var base = "<a href='#' onclick='downloadWordConfigUploadFile(\""+rowData.id+"\")'>{1}</a>{2}";
        var separator = "&nbsp;&nbsp;";
        var updateFlag=value;
        var updateClickFunc="other";
        var field=base.replace("{1}",updateFlag).replace("{2}",separator);
        if(rowData.templatepath.length<10){
        	field=value;
         }
           return field;
      }
    var constantFlagTable = "User";
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
   	 var table = new tracywindyOperationTable({
   		 resetFormCallback:function(table,$form,operFlag){
   	   		 if("add" == operFlag){
   	   	   		 
   	   	     }
   	   	     if("update" == operFlag){
   	   	  	 }
   	     },
   		 loadFormDataCallBack:function(table,$form,rowIndex){
   	   		 var rowData = table.getRowDataAt(rowIndex);
   	   	     var wordindustry = getTracywindyObject("id_combo_wordtempletypefirst");
   	       	 wordindustry.setRawValue(rowData["oneclassify"]);
   	         var wordindustry = getTracywindyObject("id_combo_wordtempletypetwo");
	       	 wordindustry.setRawValue(rowData["twoclassify"]);
	         var wordindustry = getTracywindyObject("id_combo_wordtempletypethree");
   	       	 wordindustry.setRawValue(rowData["threeclassify"]);
   	         var wordindustry = getTracywindyObject("id_combo_wordtempletypefour");
	       	 wordindustry.setRawValue(rowData["fourclassify"]);
	         var wordindustry = getTracywindyObject("id_combo_wordtempletypefive");
   	       	 wordindustry.setRawValue(rowData["fiveclassify"]);
    	 },
   		 tableComment:'[模板清单]',
   		 constantFlagTable:'basewordconfig',
   		 tools:[
   		        {html:'<font color="red">上传flt</font>',handler:function(table){loadfit(table)},iconCls:'icon-update'},
   		       // {html:'<font color="red">删除flt</font>',handler:function(table){deletefit(table)},iconCls:'icon-update'},
   		        {html:'<font color="red">上传word</font>',handler:function(table){loadword(table)},iconCls:'icon-update'},
   		        //{html:'<font color="red">删除word</font>',handler:function(table){deleteword(table)},iconCls:'icon-update'},
   		        {html:'<font color="red">配置</font>',handler:function(table){configWord(table)},iconCls:'icon-update'}
      	   		 ],
   		 windowTop:20,
   	     border:true,
         renderTo:'id_tableContainer',
         prefixMVC:'leasing/word',
         suffixMVC:'.action',
         title:'模板清单',
         width:pageWidth,
         height:pageHeight,
         id:'id_table',
         xmlFileName:'/eleasing/jsp/template_word/templateword_info.xml',
         loadMode:'ajax',
         isPage:true,
         isFit:true,
         columns:[
		            {header:'id',name:'id',hidden:true},
		            {header:'一级分类',name:'oneclassify'},
		            {header:'二级分类',name:'twoclassify'},
		            {header:'三级分类',name:'threeclassify'},
		            {header:'四级分类',name:'fourclassify'},
		            {header:'五级分类',name:'fiveclassify'},
		            {header:'显示名称',name:'wordname'},
		            {header:'模板名称',name:'wordtemplatename',renderer:showfile}
	        ]
   	 });
 });
  jQuery(function(){
		dict('id_one_classify','oneclassify','wordtempletypefirst','${requestScope["oneclassify"]}',null,'');
		dict('id_two_classify','twoclassify','wordtempletypetwo','${requestScope["twoclassify"]}',null,'');
		dict('id_three_classify','threeclassify','wordtempletypethree','${requestScope["threeclassify"]}',null,'');
		dict('id_four_classify','fourclassify','wordtempletypefour','${requestScope["fourclassify"]}',null,'');
		dict('id_five_classify','fiveclassify','wordtempletypefive','${requestScope["fiveclassify"]}',null,'');
	});
</script>
</head>
<body>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="WORD模板息管理" style="display:none;width:500px;padding-top:20px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">模板名称:  </td>  <td class="input_value"><input name="wordname" require="true" label="模板名称"   type="text" /><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">一分类:</td>  <td  class="td-content"><div class="leftComboContainer"     id="id_one_classify" style="display:inline-block"  ></div><span   class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">二级分类:</td>  <td class="td-content" ><div class="leftComboContainer"  id="id_two_classify" style="display:inline-block"  ></div><span   class="content-required">*<span></tr>
			            <tr><td class="input_label_desc">三级分类:</td>  <td  class="td-content"><div class="leftComboContainer"   id="id_three_classify" style="display:inline-block"  ></div><span   class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">四级分类:</td>  <td class="td-content" ><div class="leftComboContainer"   id="id_four_classify" style="display:inline-block"  ></div><span   class="content-required">*<span></tr>
			            <tr><td class="input_label_desc">五级分类:</td>  <td class="td-content" ><div class="leftComboContainer"   id="id_five_classify" style="display:inline-block"  ></div><span   class="content-required">*<span></tr>
			            <tr><td class="input_label_desc">显示名称:</td>  <td class="input_value"><input name="wordtemplatename" require="true" label="显示名称"   type="text" /><span class="content-required">*</span></td></tr>     
			            <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='getTracywindyObject("id_table").operationTable();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div id="id_wordConfigdetailInfoWindowContainer"  closed="true" modal="true" title="模板数据配置" style="display:none;width:600px;padding-top:20px;">
	        <center>
		        <form id="d_wordConfigdetailForm">
			        <table style="width:100%" >
			              <tr>
			                 <td>
			                 <fieldset>
                              <legend>数据配置</legend>
			                 <table style="width:100%">
			                 <tr>
			                 <td style="width:30%" style="padding:5px">
			                      <SELECT NAME="WordData" id="id_WordData"  SIZE="16" style="width:100%;OVERFLOW: auto">
			                      
			                      </SELECT>
			                      </br>
			                 </td>
			                 <td rowspan="2" style="border:1px #000000 solid; border-top:0;border-bottom:0; border-right:0;padding:5px" > 
			                      <div style="display:none">
			                            <input name="wordConfig" id="id_wordConfig" value="">
			                            <input name="wordConfigjson" id="id_wordConfigjson" value="">
			                      </div>
			                      <table style="width:100%;" id="formDataConfig">
			                          <tr><td>关键字</td><td>
			                          <input style="width:100%" name="wordid" id="id_wordid"  type="hidden" value="" >
			                          <input style="width:100%" name="wordkey" id="id_wordkey" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                          <tr><td>数据类型</td><td id="id_keytype">
			                          <input type="radio" name="keytype" value="STRING" checked="checked" />字段 
			                          <input type="radio" name="keytype" value="LIST" />列表
			                          <input type="radio" name="keytype" value="TABLE" />表格
			                          </td></tr>
			                          <tr><td>数据来源</td><td id="id_datasource">
			                          <input type="radio" name="datasource" value="database" checked="checked" />数据库 
			                          <input type="radio" name="datasource" value="page" />页面
			                          </td></tr>
			                          <tr><td>多行控件名</td><td>
			                            <input style="width:100%" name="multiwordfield" id="id_multiwordfield" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                          <tr><td>数据列名</td><td>
			                          <input style="width:100%" name="wordfield" id="id_wordfield" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                          <tr><td>数据SQL</td><td>
			                          <textarea rows="5"  
			                                    style="width: 98%" name="datasql" id="id_datasql"></textarea>
			                          </td></tr>
			                          <tr><td>说明</td><td>
			                          <input style="width:100%" name="wordmemo" id="id_wordmemo" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                      </table>
			                 </td>
			                 </tr>
			                 <tr>
			                 <td style="heigth:100px">
			                      <a  style="margin-top:10px " href="javascript:void(0);" class="btn btn-primary"onclick='OP_AddWordDataConfig();'><span>新增</span></a>
						          <a  style="margin-top:10px " href="javascript:void(0);" class="btn btn-primary"onclick='OP_CopyWordDataConfig();'><span>复制</span></a>
						          <a  style="margin-top:10px " href="javascript:void(0);" class="btn btn-primary" onclick='OP_DeleteWordDataConfig()'><span>删除</span></a>
			                 </td>
			                 </tr>
			                 </table>
			                  </fieldset>
			                 </td>
			              </tr>
			              <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='OP_SubmitWordDataConfig();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_wordConfigdetailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
</body>
</html>
<script type="text/javascript" >
//加载word模板配置数据
function configWordData(id){
  if(!window.currentDeleteFileLoadMask)
	 {
	 window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在加载数据 请稍等...");
  } 
  window.currentDeleteFileLoadMask.show();
  var url="/leasing/word/loadWordDataConfig.action";
  var params={};
      params["id"]=id;
	  ajaxRequest({
	  	  		url:getRootPath()+url,
	  	  		success:function(res){
		           window.currentDeleteFileLoadMask.hide();
	  	           var json=res.responseText;
	  	        	 $("#id_wordConfigjson").val(json);   
	  	             var $window = $("#id_wordConfigdetailInfoWindowContainer");
	  	   	         initWordDataConfig()
	  	   	         $("#id_wordConfig").val(id);
	  	   	         $window.show();
	  	   	         $window.window({
	  	       	               top:20,
	  	       	               autoScroll:true
	  	       	     });
	  	   	         $window.window('open'); 
	  	  		 },
	  	  		failure:function(res){},
	  	  		params:params 
	  	  	});
	
}
//初始化模板数据配置
function initWordDataConfig(){
	var configDataStr=$("#id_wordConfigjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
	$("#id_WordData option").remove();  
	if(configData.length>0){
		for(var i=0;i<configData.length;i++){
		  $("#id_WordData").append("<option value='"+(i+1)+"' >"+configData[i].wordkey+"</option>");
		}
	}
	$("#id_WordData").click(function(){
		var configDataStr=$("#id_wordConfigjson").val();
		var configData;
		if(configDataStr==""){
			configData=[];
	    }else{
	      configData=eval("("+configDataStr+")");
	    }
		OP_initOptinsData( configData[this.value-1]);
		OP_AddFunction(this.value);} ); 
}
//新增操作
function OP_AddWordDataConfig(){
	var configDataStr=$("#id_wordConfigjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
    var word={};
    word.id="";
    word.wordconfig=$("#id_wordConfig").val();
    word.wordkey="关键字";
    word.keytype="STRING";
    word.wordfield="";
    word.datasql="";
    word.wordmemo="";
    word.opertype="new";
    word.datasource="database";
    word.multiwordfield="";
    configData.push(word);
    OP_initOptinsData(word);
    $("#id_WordData").append("<option value='"+configData.length+"' selected='true'>关键字</option>");
    $("#id_wordConfigjson").val(JsonUtil.encode(configData));
    OP_AddFunction(configData.length);
}
//复制操作
function OP_CopyWordDataConfig(){
	var configDataStr=$("#id_wordConfigjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
	 var cindex=$("#id_WordData").get(0).selectedIndex;
	 if(cindex<0){
		 jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请选择要复制的数据！</div>",'error')
         return false;
	}
	 var cvalue= $("#id_WordData").get(0).options[cindex].value;
	 var cdata=configData[cvalue-1];
	    var word={};
	    word.id="";
	    word.wordconfig=$("#id_wordConfig").val();
	    word.wordkey=cdata["wordkey"]+"关键字";
	    word.keytype=cdata["keytype"];
	    word.wordfield=cdata["wordfield"];
	    word.datasql=cdata["datasql"];
	    word.wordmemo=cdata["wordmemo"];
	    word.opertype="new";
	    word.datasource=cdata["datasource"];
	    word.multiwordfield=cdata["multiwordfield"];
	    configData.push(word);
	    OP_initOptinsData(word);
	    $("#id_WordData").append("<option value='"+configData.length+"' selected='true'>"+word.wordkey+"</option>");
	    $("#id_wordConfigjson").val(JsonUtil.encode(configData));
	    OP_AddFunction(configData.length);
}
//删除操作
function OP_DeleteWordDataConfig(){
	var configDataStr=$("#id_wordConfigjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
    var cindex=$("#id_WordData").get(0).selectedIndex;
	if(cindex<0){
		jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请先选择数据再删除！</div>",'error');
        return false;
	    };
    var cvalue= $("#id_WordData").get(0).options[cindex].value;
    $("#id_WordData").get(0).options.remove(cindex);
    configData[cvalue-1].opertype="delete";
    $("#id_wordConfigjson").val(JsonUtil.encode(configData));
}
//提交保存到数据库中
function OP_SubmitWordDataConfig(){
	var configDataStr=$("#id_wordConfigjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
    if(configData.length<=0){
    	jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请配置数据！</div>",'error');
        return false;
     }else{
       var cdata=[];
       //过滤新建又被删除的数据
      for(var i=0;i<configData.length;i++){
         if(configData[i].opertype=="delete"){

         }else{
        	 cdata.push(configData[i]);
           }
       }
      $("#id_wordConfigjson").val(JsonUtil.encode(cdata));
      if(!window.currentDeleteFileLoadMask)
  	{
  	window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在保存数据 请稍等...");
  	} 
  	window.currentDeleteFileLoadMask.show();
  	var url="/leasing/word/operatorWordDataConfig.action"
    var params={};
    params["jsonData"]=JsonUtil.encode(cdata);
    params["wordid"]=$("#id_wordConfig").val();
  	ajaxRequest({
  		url:getRootPath()+url,
  		success:function(res){
  		window.currentDeleteFileLoadMask.hide();
  		jQuery.messager.alert('提示',"<div style='font-size:15px;line-height:30px;width:200px;'>保存成功！</div>",'success');
  		jQuery("#id_wordConfigdetailInfoWindowContainer").window("close");
  		 },
  		failure:function(res){},
  		params:params 
  	});
    }
}
//增加域操作的函数
function OP_AddFunction(index){
	$("#formDataConfig input").unbind("keyup");
	$("#formDataConfig textarea").unbind("keyup"); 
	$("#formDataConfig input:radio").unbind("click");  
	$("#formDataConfig input").keyup(function(event) {
    var cindex=index;
    setConfigData(cindex,event.target.name,this.value);		  
    }); 
	$("#formDataConfig textarea").keyup(function(event) {
	    var cindex=index;		  
	    setConfigData(cindex,event.target.name,this.value);		  
	    }); 
    $("#formDataConfig input:radio").click(function(event) {
    	 var cindex=index;		  
 	    setConfigData(cindex,event.target.name,this.value);		 
	 }); 
}
//初始化选中右边的数据
function OP_initOptinsData(cdata){
	$("#id_wordid").val(cdata["id"]);
	$("#id_wordkey").val(cdata["wordkey"]);
	$("#id_wordfield").val(cdata["wordfield"]);
	$("#id_datasql").val(cdata["datasql"]);
	$("#id_multiwordfield").val(cdata["multiwordfield"]);
	var input = $("#id_keytype").find("input:radio");
	 input.each(function(){
	 if($(this).val()==cdata["keytype"]){
	 $(this).attr("checked",true);
	 }
	 });
	 input = $("#id_datasource").find("input:radio");
	 input.each(function(){
	 if($(this).val()==cdata["datasource"]){
	 $(this).attr("checked",true);
	 }
	 });
    $("#id_wordmemo").val(cdata["wordmemo"]);
    //keytype
}
//同步更新数据
function setConfigData(index,field,value){
  var configDataStr=$("#id_wordConfigjson").val();
	  configData=eval("("+configDataStr+")");
  var cdata=configData[index-1];
      if(field=="wordid"){field="id";}
	  cdata[field]=value;
	  $("#id_wordConfigjson").val(JsonUtil.encode(configData));
     if(field=="wordkey"){
         var t="#id_WordData option[value='"+index+"']";
         var options=$("#id_WordData option");
         for(var i=0;i<options.length;i++){
           if(options[i].value==index){options[i].text=value;}}
      }
}
function loadfit(table){
	var doc_id=getSelectRowId(table);
	if(doc_id==""){return false;}
	var url="";
	url="/leasing/word/fileupload.action";
	attachmentUp(doc_id,url,"上传模板flt");
}
function deletefit(table){
	var doc_id=getSelectRowId(table);
	if(doc_id==""){return false;}
	var url="";
	 url="/leasing/word/deletefile.action";
	 deleteFileAttache(doc_id,url);
}
function loadword(table){
	var doc_id=getSelectRowId(table);
	if(doc_id==""){return false;}
	var url="";
	 url="/leasing/word/sourefileupload.action";
	 attachmentUp(doc_id,url,"上传模板word")
}
function deleteword(table){
	var doc_id=getSelectRowId(table);
	if(doc_id==""){return false;}
	var url="";
	url="/leasing/word/deleteSourcefile.action";
	deleteFileAttache(doc_id,url);
}
function configWord(table){
	var doc_id=getSelectRowId(table);
	if(doc_id==""){return false;}
	configWordData(doc_id)
}
function getSelectRowId(tabled){
	
	var selectDatas = tabled.getCheckedRowDatas();
	if (0 == selectDatas.length) {
		alert("请选择需要操作模板！");
		return "";
	}
    return selectDatas[0].id
}
</script>