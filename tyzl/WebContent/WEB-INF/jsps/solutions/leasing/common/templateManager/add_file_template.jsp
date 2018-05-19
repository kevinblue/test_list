<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tenwa.business.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
//前缀
String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<title>模板页面修改</title>

<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>

</head>
<body>
<div class="mini-fit">
	<div id="form1" >
		<table class="miniext-form-table" >
		    <tr>
		        <td class="miniext-form-td">模板名称：</td>
		        <td>
		            <input name="id" class="mini-textbox" style="display: none;"/>
					<input name="templatename" class="mini-textbox" style="width:154px" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">显示名称：</td>
		        <td>
		            <input name="id" class="mini-textbox" style="display: none;"/>
					<input name="templateshowname" class="mini-textbox" style="width:154px" required="true"/>
		        </td>
		    </tr>
		    <tr>   
		        <td class="miniext-form-td">一级分类：</td>
		        <td>
					<input id="id_oneclassify" name="oneclassify"  class="mini-combobox" style="width:154px" textField="name" 
	                  	       valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'wordtempletypefirst'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
						  
					/>
		        </td>
		    </tr>
		    
		    <tr>
		        <td class="miniext-form-td">二级分类：</td>
		        <td>
					<input id="id_twoclassify" name="twoclassify" class="mini-combobox" style="width:154px" textField="name" 
	                  	 valueField="value"  
							   dataField="datas"
							   allowInput="true" ,
							   data-options="{_params:{pid:'wordtempletypetwo'},_pageSize:2000}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
					/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">三级分类：</td>
		        <td>
					<input id="id_threeclassify" name="threeclassify" class="mini-combobox" style="width:154px" textField="name" 
	                  	  valueField="value"  
							   dataField="datas"
							   allowInput="true" 
							   data-options="{_params:{pid:'wordtempletypethree'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
					/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">四级分类：</td>
		        <td>
					<input id="id_fourclassify" name="fourclassify" class="mini-combobox" style="width:154px" textField="name" 
	                  	 valueField="value"  
							   dataField="datas"
							   allowInput="true" 
							   data-options="{_params:{pid:'wordtempletypefour'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
					/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">五级分类：</td>
		        <td>
					<input id="id_fiveclassify" name="fiveclassify" class="mini-combobox" style="width:154px" textField="name" 
	                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'wordtempletypefive'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
					/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">六级分类：</td>
		        <td>
					<input id="id_sixclassify" name="sixclassify" class="mini-combobox" style="width:154px" textField="name" 
	                  	  valueField="value"  
							   dataField="datas"
							   allowInput="true" 
							   data-options="{_params:{pid:'wrodtemplatetypesix'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
					/>
		        </td>
		    </tr>
		</table>
	</div>
</div>
<div class="mini-toolbar miniext-toolbar-bottom">
    <table class="miniext-form-fit">
        <tr>
            <td>
                <a class="mini-button" iconCls="icon-add" onclick="save">保存</a>
                <a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
            </td>
        </tr>
    </table>           
</div>
<script>
mini.parse();
var form = new mini.Form("form1");


var opertype="<mini:param  name='opertype'/>";
function onbeforeshowpopup(e) {
	var val=mini.get("test").getText();
	var cb=e.sender;
	var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';//url 
    var xmlFileName = cb._xmlFileName ||'/combos/comboDict.xml';//xmlFileName
    var attachmentParams = cb._params || '' ;//params参数
    var params = {};
	if (attachmentParams) {
		if ("object" == typeof (attachmentParams)) {
			for ( var p in attachmentParams) {
				params[p] = escape(encodeURIComponent(attachmentParams[p]));
			}
		} else if ("string" == typeof (attachmentParams)) {
			var paramsArr = attachmentParams.split("&");
			for (var i = 0; i < paramsArr.length; i++) {
				var param = paramsArr[i];
				if (param) {
					var keyValueArr = param.split("=");
					var key = "";
					var value = "";
					var keyValueArrLen = keyValueArr.length;
					if (keyValueArrLen >= 1) {
						key = keyValueArr[0];
						if (key) {
							key = key.trim();
						}
					}
					if (keyValueArrLen >= 2) {
						value = keyValueArr[1];
						if (value) {
							if (processDefinitionId.indexOf("%") > -1) {
								value = value.trim();
							} else {
								value = escape(encodeURIComponent(value)).trim();
							}
						}
					}
					if (key) {
						params[key] = value;
					}
				}
			}
		}
	}
	
	
	
    //需要把key的""去掉
    var pageSize = cb._pageSize ||  10;//显示行数
	if(url.indexOf("&") == -1){
		url=url+"?xmlFileName="+xmlFileName+"&pageSize="+pageSize;
	}else{
		url=url+"&xmlFileName="+xmlFileName+"&pageSize="+pageSize;
	}
    
	if(params)
	{
		if('string' == typeof(params))
		{
			url += ("&"+params.replace(/(^&)|(^\?)/,""));
		}
		else if('object' == typeof(params))
		{
		   for(var p in params)
		   {
			  url+=("&"+p+"="+params[p]); 
		   }
		}
	}
	alert(url+"&query="+val);
	cb.setUrl(url+"&query="+val);
}

function SetData(data) {
	var form = new mini.Form("form1");
    //跨页面传递的数据对象，克隆后才可以安全使用
    data = mini.clone(data);
    $.ajax({
        url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/template_word/filetemplate_info.xml&id=" + data.id,
        cache: false,
        success: function (text) {
        	var result = mini.decode(text);
            form.setData(result.datas[0]);
            initcomboboxloaddata("form1",result.datas[0]);
            form.setChanged(false);
        }
    });
}
function save(e){
	var o = form.getData(true,true); 
    form.validate();
    if (form.isValid() == false) return;
    var url="";
    if(opertype=="add"){
		url="<%=request.getContextPath() %>/leasing/template/addFileTemplate.action";
	}else{
		url="<%=request.getContextPath() %>/leasing/template/updateFileTemplate.action";
	}
    var json = mini.encode(o);
    mini.mask({
        el: document.body,
        cls: 'mini-mask-loading',
        html: '提交中...'
    });
    
	$.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	alert("保存成功...");
        	CloseWindow("savesuccess");
        	mini.unmask(document.body);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
            mini.unmask(document.body);
        }
    });
}
function CloseWindow(action) {            
    if (action == "close" && grid.isChanged()) {
        if (confirm("数据被修改了，是否先保存？")) {
            return false;
        }
    }
    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
    else window.close();            
}
function onCancel(e) {
    CloseWindow("cancel");
}
function onCloseClick(e) {
    var obj = e.sender;
    obj.setText("");
    obj.setValue("");
}

function initcomboboxloaddata(formid,data){
	 var form = new mini.Form(formid);
	 var o = form.getData(); 
	 var fields = form.getFields();
	 for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		 if(fields[index].uiCls == "mini-combobox"){
			//var temp = fields[index].name;
			//fields[index].setText(data[temp]);
			var tempvalue ="raw_"+fields[index].name;
			fields[index].setValue(data[tempvalue]);
		 }
	 }
}
</script>
</body>
</html>