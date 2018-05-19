<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模块管理  - jsp模块管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	
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
    var constantFlagTable = "User";
	var pageWidth  = document.documentElement.clientWidth-2;
	var pageHeight = document.documentElement.clientHeight-2;
	jQuery(function(){
   	 var table = new tracywindyOperationTable({

   		resetFormCallback:function(table,$form,operFlag,rowindex){
   		    if("add" == operFlag){
   			   var rowData = table.getRowDataAt(0);
   	   		   //document.getElementById("custname").value=rowData["custname"];
   			  //$("#custname").val(rowData["custname"]);
   			$("#id_fielddatas option").remove();  
   	        }
   	        if("update"==operFlag){
   	        	var rowData = table.getRowDataAt(rowindex);
   	        	configFieldData(rowData["id"]);
   	   	   	}
     	 },

  		
   		 loadFormDataCallBack:function(table,$form,rowIndex){
        },
   		 tableComment:'[模块管理]',
   		 constantFlagTable:'modelManger',
   		 windowTop:20,
   	     border:true,
         renderTo:'id_tableContainer',
         title:'模块管理',
         width:pageWidth,
         height:pageHeight,
         id:'id_table',
         xmlFileName:'/eleasing/jsp/model_manager/modelmanager_info.xml',
         loadMode:'ajax',
         prefixMVC:'leasing/fieldmodel',
         suffixMVC:'.action',
         isPage:true,
         isFit:true,
         columns:[
		            {header:'模块名称',name:'modelname'},
		            {header:'jsp文件名',name:'jspname'},
		            {header:'路径',name:'jsppath'},
	        ]
   	 });
   // 	commonSelectOverall('id_select_contract_id',"(select ci.contract_id,ci.id||'/'||cust.cust_name value from contract_info ci left join cust_info cust on (ci.cust_id=cust.id)) rs",'value',"contract_id",'contractid','','','',selectContract,false);
  //   	dictYesOrNoByCode('id_select_is_insured','isinsured',1,'','','require="true" label="是否投保"');
   });
</script>

 <script type="text/javascript">
    
     </script>
</head>
<body>
    <div id="id_tableContainer"></div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="jsp模块管理" style="display:none;width:800px;padding-top:20px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr>
			            	
			            	<td class="input_label_desc">模块名称:  </td>  
			            	<td class="td-content" >
			            	   <input name="modelname" require="true" label="模块名称"  maxB="150" type="text" /><span class="content-required">*</span>			            	
			            	 </td>
			            	 <td class="input_label_desc">java类名:  </td>  
			            	<td class="td-content">
               			        <input name="javaclassname" require="true" label="java类名"  maxB="150" type="text" /><span class="content-required">*</span>
			             	</td>
			            </tr>
			            <tr>
			            	<td class="input_label_desc">jsp文件名:  </td>  
			            	<td class="td-content">
			            		<input name="jspname" require="true" label="jsp文件名"  maxB="150" type="text" /><span class="content-required">*</span>
			            	</td>
			            	<td class="input_label_desc">路径:  </td>  
			            	<td class="td-content">
               			        <input name="jsppath" require="true" label="路径"  maxB="150" type="text" /><span class="content-required">*</span>
			             	</td>
			            <tr style="display:none">
			            	<td class="input_label_desc">新增权限:  </td>  
			            	<td class="td-content" colspan="3">
			            		 <textarea name="addright" require="true"  style="width:70%" label="新增权限" maxB="50"   type="text" ></textarea><span class="content-required">*</span>
			            	</td>
			            </tr>
			            <tr style="display:none">
			            	<td class="input_label_desc">删除权限:  </td>  
			            	<td class="td-content" colspan="3">
			            		 <textarea name="deleteright" require="true"  style="width:70%" label="删除权限" maxB="50"   type="text" ></textarea><span class="content-required">*</span>
			            	</td>
			            </tr>
			            <tr style="display:none">
			            	<td class="input_label_desc">修改权限:  </td>  
			            	<td class="td-content" colspan="3">
			            		 <textarea name="moderight" require="true"  style="width:70%" label="修改权限" maxB="50"   type="text" ></textarea><span class="content-required">*</span>
			            	</td>
			            </tr>
			            <tr style="display:none">
			            	<td class="input_label_desc">其它操作权限:  </td>  
			            	<td class="td-content" colspan="3">
			            		 <textarea name="otherright" require="true"  style="width:70%" label="其它操作权限" maxB="50"   type="text" ></textarea><span class="content-required">*</span>
			            	</td>
			            </tr >
			            <tr>
			            
			                 <td class="td-content" colspan="4">
			                 <div style="display:none">
			                            <input name="fielddatasjson" id="id_fielddatasjson" value="">
			                      </div>
			                    <fieldset>
                              <legend>列配置</legend>
			                 <table style="width:100%">
			                 <tr>
			                 <td style="width:30%" style="padding:5px">
			                      <SELECT NAME="fielddatas" id="id_fielddatas"  SIZE="16" style="width:100%;OVERFLOW: auto">
			                      </SELECT>
			                      </br>
			                 </td>
			                 <td rowspan="2" style="border:1px #000000 solid; border-top:0;border-bottom:0; border-right:0;padding:5px" > 
			                      <table style="width:100%;" id="id_fieldtable">
			                          <tr><td>标题</td><td>
			                          <input style="width:100%" name="fieldid" id="id_fieldid"  type="hidden" value="" >
			                          <input type="text" style="width:100%" name="title" id="id_title" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                          <tr><td>字段名</td><td>
			                          <input type="text" style="width:100%" name="fieldname" id="id_fieldname" class="td-content-input"  type="text" value="" >
			                          </td></tr>
			                          <tr><td>数据类型</td><td id="id_datatype">
			                          <input type="radio" name="datatype" value="string" checked="checked" />字符串 
			                          <input type="radio" name="datatype" value="list" />下拉列表
			                          <input type="radio" name="datatype" value="decimal" />日期
			                          <input type="radio" name="datatype" value="other" />其他类型
			                          </td></tr>
			                          <tr><td>数据校验</td><td id="id_datacheck">
			                          <input type="checkbox" name="datacheck" value="require" checked="checked" />必填 
			                          <input type="checkbox" name="datacheck" value="page" />日期
			                           <input type="checkbox" name="datacheck" value="page1" />日期
			                          </td></tr>
			                           <tr><td>是否换行</td><td id="id_changerow">
			                              <input type="radio" name="changerow" value="0" checked="checked" />是
			                              <input type="radio" name="changerow" value="1" />否
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
				            <td colspan='4'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='OP_SubmitWordDataConfig();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
<script language="javascript">

function configFieldData(id){
	  if(!window.currentDeleteFileLoadMask)
		 {
		 window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在加载数据 请稍等...");
	  } 
	  window.currentDeleteFileLoadMask.show();
	  var url="/leasing/fieldmodel/loadbasefieldconfig.action";
	  var params={};
	      params["id"]=id;
		  ajaxRequest({
		  	  		url:getRootPath()+url,
		  	  		success:function(res){
			           window.currentDeleteFileLoadMask.hide();
		  	           var json=res.responseText;
		  	        	 $("#id_fielddatasjson").val(json);   
		  	   	         initWordDataConfig();
		  	  		 },
		  	  		failure:function(res){},
		  	  		params:params 
		  	  	});
		
	}
//初始化模板数据配置
function initWordDataConfig(){
	var configDataStr=$("#id_fielddatasjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
	$("#id_fielddatas option").remove();  
	if(configData.length>0){
		for(var i=0;i<configData.length;i++){
		  $("#id_fielddatas").append("<option value='"+(i+1)+"' >"+configData[i].title+"</option>");
		}
	}
}
//新增操作
function OP_AddWordDataConfig(){
	var configDataStr=$("#id_fielddatasjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
    var field={};
    field.id="";
    field.title="标题"
    field.fieldname="";
    field.datatype="string";
    field.datacheck="require";
    field.changerow="1"
    field.opertype="new";
    configData.push(field);
    OP_initOptinsData(field);
    $("#id_fielddatas").append("<option value='"+configData.length+"' selected='true'>标题</option>");
    $("#id_fielddatasjson").val(JsonUtil.encode(configData));
    OP_AddFunction(configData.length);
}
//复制操作
function OP_CopyWordDataConfig(){
	var configDataStr=$("#id_fielddatasjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
	 var cindex=$("#id_fielddatas").get(0).selectedIndex;
	 if(cindex<0){
		 jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请选择要复制的数据！</div>",'error')
         return false;
	}
	 var cvalue= $("#id_fielddatas").get(0).options[cindex].value;
	 var cdata=configData[cvalue-1];
	    var word={};
	    var field={};
	    field.id="";
	    field.title=cdata["title"]+"复制"
	    field.fieldname=cdata["title"]
	    field.datatype=cdata["datatype"]
	    field.datacheck=cdata["datacheck"]
	    field.changerow=cdata["changerow"]
	    field.opertype="new";
	    configData.push(field);
	    OP_initOptinsData(field);
	    $("#id_fielddatas").append("<option value='"+configData.length+"' selected='true'>"+field.title+"</option>");
	    $("#id_fielddatasjson").val(JsonUtil.encode(configData));
	    OP_AddFunction(configData.length);
}
//删除操作
function OP_DeleteWordDataConfig(){
	var configDataStr=$("#id_fielddatasjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
    var cindex=$("#id_fielddatas").get(0).selectedIndex;
	if(cindex<0){
		jQuery.messager.alert('错误提示',"<div style='font-size:15px;line-height:30px;width:200px;'>请先选择数据再删除！</div>",'error');
        return false;
	    };
    var cvalue= $("#id_fielddatas").get(0).options[cindex].value;
    $("#id_fielddatas").get(0).options.remove(cindex);
    configData[cvalue-1].opertype="delete";
    $("#id_fielddatasjson").val(JsonUtil.encode(configData));
}
//提交保存到数据库中
function OP_SubmitWordDataConfig(){
	var configDataStr=$("#id_fielddatasjson").val();
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
      $("#id_fielddatasjson").val(JsonUtil.encode(cdata));
      if(!window.currentDeleteFileLoadMask)
  	{
  	window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body,"正在保存数据 请稍等...");
  	} 
  	window.currentDeleteFileLoadMask.show();
  	var url="/leasing/fieldmodel/addbasefieldconfig.action"
   
    var params = $("#id_detailInfoWindowContainer form").tracywindySerializeFormToJsonObject(true);
  	ajaxRequest({
  		url:getRootPath()+url,
  		success:function(res){
  		window.currentDeleteFileLoadMask.hide();
  		jQuery.messager.alert('提示',"<div style='font-size:15px;line-height:30px;width:200px;'>保存成功！</div>",'success');
  		     jQuery("#id_detailInfoWindowContainer").window("close");
  		   window.location.reload();
  		 },
  		failure:function(res){},
  		params:params 
  	});
    }
}
//增加域操作的函数
function OP_AddFunction(index){
	$("#id_fieldtable input:text").unbind("keyup");
	$("#id_fieldtable input:radio").unbind("click");
	$("#id_fieldtable input:checkbox").unbind("click");  
 
	$("#id_fieldtable input:text").keyup(function(event) {
           var cindex=index;
         setConfigData(cindex,event.target.name,this.value);		  
    }); 
    $("#id_fieldtable input:radio").bind("click",function(event) {
    	 var cindex=index;		  
 	    setConfigData(cindex,event.target.name,this.value);		 
	 });
    $("#id_fieldtable input:checkbox").click(function(event) {
   	    var cindex=index;	
   	    var pcheckbox=event.target.parentNode;
   	    var checkboxs=pcheckbox.childNodes;
   	    var temp="";
   	    for(var i=0;i<checkboxs.length;i++){
            if(checkboxs[i].checked){if(temp==""){temp=checkboxs[i].value;}else{temp=temp+","+checkboxs[i].value;}}
   	   	}
	    setConfigData(cindex,event.target.name,temp);		 
	 });  
}
//初始化选中右边的数据
function OP_initOptinsData(cdata){
	$("#id_fieldid").val(cdata["id"]);
	$("#id_title").val(cdata["title"]);
	$("#id_fieldname").val(cdata["fieldname"]);
	
	var input = $("#id_datatype").find("input:radio");
	 input.each(function(){
	 if($(this).val()==cdata["datatype"]){
	      $(this).attr("checked",true);
	 }
	 });
	 input = $("#id_changerow").find("input:radio");
	 input.each(function(){
	 if($(this).val()==cdata["changerow"]){
	 $(this).attr("checked",true);
	 }
	 });
	 input = $("#id_datacheck").find("input:checkbox");
	 input.each(function(){
     var tempvalue=$(this).val();
	 if(cdata["datacheck"].indexOf(tempvalue)>=0){
	 $(this).attr("checked",true);
	 }
	 });
    //keytype
}
//同步更新数据
function setConfigData(index,field,value){
  var configDataStr=$("#id_fielddatasjson").val();
	  configData=eval("("+configDataStr+")");
  var cdata=configData[index-1];
      if(field=="fieldid"){field="id";}
	  cdata[field]=value;
	  $("#id_fielddatasjson").val(JsonUtil.encode(configData));
     if(field=="title"){
         var options=$("#id_fielddatas option");
         if(value==""){value="标题"}
         for(var i=0;i<options.length;i++){
           if(options[i].value==index){options[i].text=value;}}
      }
  }
$("#id_fielddatas").click(function(){
	var configDataStr=$("#id_fielddatasjson").val();
	var configData;
	if(configDataStr==""){
		configData=[];
    }else{
      configData=eval("("+configDataStr+")");
    }
OP_initOptinsData( configData[this.value-1]);
OP_AddFunction(this.value);} );

</script>
</body>

</html>