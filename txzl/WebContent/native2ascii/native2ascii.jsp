<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="keywords" content="native2ascii,ascii,在线转换">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>在线native2ascii</title>
<style type="text/css">
<!--
body {
	font-size:12px;
	overflow-y:auto;
}
textarea{
	width:99%;
	height:400px; 
	overflow:auto;
}
.submit{
   width:50px;
   height:30px;
   margin-bottom:20px;
}
td{
  color:#FFFFFF;
}
-->
</style></head>

<body>
<p><script language="javascript" src="native2ascii3.js"></script></p>
<form name="characterCode">
<center><div style="text-align:left;width:85%;"><input type="checkbox" name="ignoreLetter" id="ignoreLetter" checked>不转换字母和数字。</div></center>
<table align="center" width="85%" height="70%" cellspacing="0" cellpadding="5" style="border:#00486A dotted 1; background-color:#41566B;">
<tr height="20">
<td>Native</td>
<td width="10">
</td>
<td>Ascii</td></tr>
<tr>
<td><textarea id="characterTa" name="characterTa"></textarea></td>
<td width="10">
  <input type="button" value="&gt;&gt;" onclick="native2ascii();" class="submit"/><br>
  <input type="button" value="&lt;&lt;" onclick="ascii2native();" class="submit"/>
</td>
<td><textarea id="asciiTa" name="asciiTa"></textarea></td>
</tr>
</table>
</form>
</body>
</html>
