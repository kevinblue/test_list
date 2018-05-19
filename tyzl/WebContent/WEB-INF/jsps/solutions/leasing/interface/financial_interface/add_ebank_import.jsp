<%@page import="com.tenwa.business.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>网银信息</title>

<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>

</head>
<body>

<div class="mini-fit">
	<div id="form1">
		<table class="miniext-form-table">
		    <tr>
		        <td style="width:120px;">流水号：</td>
		        <td>
		            <input name="sn" class="mini-textbox miniext-form-fit" required="true"/>
		        	<input name="id" class="mini-textbox" style="display: none;"/>
		        </td>
		        <td style="width:100px;">是否有效：</td>
		        <td>
		            <input name="enabledname" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		     <tr>
		        <td class="miniext-form-td">客户：</td>
		        <td>
					<input name="custname" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		        <td class="miniext-form-td">付款人：</td>
		        <td>
					<input name="clientname" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		     <tr>
		        <td class="miniext-form-td">本方银行：</td>
		        <td>
					<input name="ownbank" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		        <td class="miniext-form-td">对方银行：</td>
		        <td>
					<input name="clientbank" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">本方帐号：</td>
		        <td>
					<input name="ownaccnumber" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		        <td class="miniext-form-td">对方账号：</td>
		        <td >
					<input name="clientaccnumber" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td >到账金额类型：</td>
		        <td>
					<input name="moneytype" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		        <td >到账金额：</td>
		        <td>
					<input name="factmoney" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td >网银余额：</td>
		        <td>
					<input name="owmoney" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		        <td >已核销金额：</td>
		        <td>
					<input name="hiremoney" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td >到账时间：</td>
		        <td>
					<input name="factdate" class="mini-datepicker miniext-form-fit" required="true"/>
		        </td>
		        <td >与系统无关金额：</td>
		        <td>
					<input name="nowithmoney" class="mini-textbox miniext-form-fit" required="true"/>
		        </td>
		    </tr>
		    <tr>
		        <td >备注：</td>
		        <td colspan="3">
					<input name="summary" class="mini-textarea miniext-form-fit2"/>
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
function onbeforeshowpopup(e) {
	miniui_ext.onbeforeshowpopup(e);
}
function $mini(id){
	return mini.get(id);
}
function SetData(data) {
	var form = new mini.Form("form1");
    //跨页面传递的数据对象，克隆后才可以安全使用
    data = mini.clone(data);
    form.setData(data);
}
function save(e){
	var o = form.getData(true,true); 
    if (miniui_ext.fromvalidation('form1') == false) return;
    var url = "<%=request.getContextPath() %>/acl/updatefundebank.acl";
    var json = mini.encode(o);
	$.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	CloseWindow("savesuccess");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseText);
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
</script>
</body>
</html>