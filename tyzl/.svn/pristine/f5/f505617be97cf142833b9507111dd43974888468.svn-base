<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>模板管理</title>   
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_idfinal',
			width : globalClientWidth,
			height : globalClientHeight,
			title : '模板管理',
			iconCls : 'icon-node',
			multiSelect : false,
			hiddenQueryArea : true, 
			queryButtonColSpan : 2,
			queryButtonNewLine:false,
			editFormPopupWindowWidth : 500,
			editFormPopupWindowHeight : 300,		
			showPager:true,
			remoteOper : true,
			entityClassName : 'com.tenwa.leasing.entity.file.BaseFileTemplate',
			entityBeanCallBackClassName : 'com.tenwa.leasing.serviceImpl.fileTemplate.FileTemplateCreateCallBack',
			xmlFileName : '/eleasing/jsp/template_word/filetemplate_info.xml',
			tools : [ 'add', '-', 'edit', '-', 'remove', '-',
                   {
				     html : '上传模板',plain : true,iconCls : 'icon-edit',
				     handler : function(miniTable, buttonText) {
					       var row = miniTable.getSelected();
					       if(row){
					    	   loadftl(row.id);
					        }else{
					         mini.alert('请选择模板！！！');
					    }}
			        },  '-',
                   {
			        html : '配置',plain : true,iconCls : 'icon-edit',
			        handler : function(miniTable, buttonText) {
				          var row = miniTable.getSelected();
				          if(row){
				    	   configWordData(row.id);}else{mini.alert('请选择模板！！！');
				      }}
		            }       
			      ],
			columns : [ 
					    {type : 'indexcolumn'},
						{type : 'checkcolumn'},  
						{field : 'id',header : 'id',visible : false,formEditorConfig : {readOnly : true,fieldVisible : false}},
						{field:'oneclassifyname',header:'一级分类',queryConfig:{}, formEditorConfig:{fieldVisible: false,fillFromFieldName : 'oneclassify',fillProperty : 'name'}},
						{field:'oneclassify',header:'一级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypefirst',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'twoclassifyname',header:'二级分类',queryConfig:{}, formEditorConfig:{fieldVisible: false,fillFromFieldName : 'twoclassify',fillProperty : 'name'}},
						{field:'twoclassify',header:'二级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypetwo',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'threeclassifyname',header:'三级分类',queryConfig:{}, formEditorConfig:{fieldVisible: false,fillFromFieldName : 'threeclassify',fillProperty : 'name'}},
						{field:'threeclassify',header:'三级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							newLine : true, 
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypethree',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'fourclassifyname',header:'四级分类', formEditorConfig:{fieldVisible: false,fillFromFieldName : 'fourclassify',fillProperty : 'name'}},
						{field:'fourclassify',header:'四级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypefour',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'fiveclassifyname',header:'五级分类', formEditorConfig:{fieldVisible: false,fillFromFieldName : 'fiveclassify',fillProperty : 'name'}},
						{field:'fiveclassify',header:'五级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							newLine : true, 
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypefive',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'sixclassifyname',header:'六级分类', formEditorConfig:{fieldVisible: false,fillFromFieldName : 'fiveclassify',fillProperty : 'name'}},
						{field:'sixclassify',header:'六级分类', visible: false,formEditorConfig:{
							type : 'combobox',
							textField: 'name',
							valueField: 'value',
							fieldVisible: true,
							params: {
								dictid: 'wordtempletypesix',
								xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'
							}
						}},
						{field:'templateno',header:'模板编号',queryConfig:{newLine: true},formEditorConfig : {type : 'text',readOnly : true,fieldVisible : false}},
						{field:'templatename',header:'模板名称',queryConfig:{},formEditorConfig : {newLine : true }},	
						{field:'templateshowname',header:'显示名称'},
						{field:'skipcelld',header:'原文件',formEditorConfig : {
							fieldVisible : false
							},
							renderer : function(e){
								var id = e.record.id;
								 if(e.record.templatepath.length<2){
							        	return "没有模板";
							         }else{
							        	 return "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>模板 </a>";
							        }
								
							}
							}
						]
			});
	});
});
</script>
</head>
<body  >
    <div id="id_wordConfigdetailInfoWindowContainer" class="mini-window"  closed="true" modal="true" title="模板数据配置" style="display:none;width:600px;padding-top:20px;">
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
			                          <tr><td>千分位字段</td><td>
			                            <input style="width:100%" name="formatfield" id="id_formatfield" class="td-content-input"  type="text" value="" >
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
				            <td colspan='2' align="center">
				                 <a  style="margin-left:20px;"  class="mini-button" iconCls="icon-add" onclick='OP_SubmitWordDataConfig();'><span>确定</span></a>
						         <a  style="margin-left:20px;"  class="mini-button" iconCls="icon-close" onclick='mini.get("id_wordConfigdetailInfoWindowContainer").hide();'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
    
    <script type="text/javascript">
     	//上传模板
		function loadftl(id){
        	var uploadutil=new uploadUtil({
				url:'/leasing/template/uploadingFileTemplateFtl.action',modelname:'模板管理',
				title:'上传模板',
		        parames:{id:id}}
			);
		   uploadutil.createFileAndShow();
     	}
		function downloadFile(Id)
		{
			mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'下载中...'});
			var uploadutil=new uploadUtil({url:'/leasing/template/downLoadTemplateAttach.action',parames:{id:Id}});
			uploadutil.createFormAndDown();
		    mini.unmask(document.body);
		}		
//加载word模板配置数据
function configWordData(id){
  var url="/leasing/template/loadFileTemplateDataConfig.action";
  var params={};
      params["id"]=id;
      mini.mask({
          el: document.body,
          cls: 'mini-mask-loading',
          html: '加载数据...'
     });
	  ajaxRequest({
	  	  		url:getRootPath()+url,
	  	  		success:function(res){
		            $("#id_wordConfigjson").val("");   
		            $("#id_wordConfig").val("");
	  	             var json=res.responseText;
	  	        	 $("#id_wordConfigjson").val(json);   
	  	   	         initWordDataConfig();
	  	   	         $("#id_wordConfig").val(id);
	  	   	         mini.get("id_wordConfigdetailInfoWindowContainer").show();
	  	   	         mini.unmask(document.body);
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
    word.formatfield="";
    configData.push(word);
    OP_initOptinsData(word);
    $("#id_WordData").append("<option value='"+configData.length+"' selected='true'>关键字</option>");
    $("#id_wordConfigjson").val(mini.encode(configData));
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
	    word.formatfield=cdata["formatfield"];
	    configData.push(word);
	    OP_initOptinsData(word);
	    $("#id_WordData").append("<option value='"+configData.length+"' selected='true'>"+word.wordkey+"</option>");
	    $("#id_wordConfigjson").val(mini.encode(configData));
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
    $("#id_wordConfigjson").val(mini.encode(configData));
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
      $("#id_wordConfigjson").val(mini.encode(cdata));
     mini.mask({
          el: document.body,
          cls: 'mini-mask-loading',
          html: '提交中...'
     });
  	var url="/leasing/template/addFileTemplateDataConfig.action";
    var params={};
    params["jsonData"]=mini.encode(cdata);
    params["id"]=$("#id_wordConfig").val();
  	ajaxRequest({
  		url:getRootPath()+url,
  		success:function(res){
        alert(res.responseText);
        mini.get("id_wordConfigdetailInfoWindowContainer").hide();
        mini.unmask(document.body);
  		 },
  		failure:function(res){
  			mini.unmask(document.body);
  		},
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
	$("#id_formatfield").val(cdata["formatfield"]);
	$("input[name='keytype'][value='"+cdata["keytype"]+"']").attr("checked",true); 
	$("input[name='datasource'][value='"+cdata["datasource"]+"']").attr("checked",true); 
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
	  $("#id_wordConfigjson").val(mini.encode(configData));
     if(field=="wordkey"){
         var t="#id_WordData option[value='"+index+"']";
         var options=$("#id_WordData option");
         for(var i=0;i<options.length;i++){
           if(options[i].value==index){options[i].text=value;}}
      }
}

    </script>
</body>
</html>