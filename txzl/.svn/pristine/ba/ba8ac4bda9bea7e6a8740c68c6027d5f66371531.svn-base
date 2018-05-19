<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>上传授权信息</title>
	<style type="text/css">
	   table {
	     border-collapse:collapse;
	   }
	   td{
	     border-collapse:collapse;
	     border:1px solid #DDD;
	     line-height:30px;
	     padding:4px;
	   }
	   input{
	     border:1px solid #DDD;
	   }
	   input[type='submit']{
	     border:1px solid #DDD;
	     padding:5px;
	     cursor:pointer;
	   }
	</style>
</head>
<body>
   <center>
	   <form style="padding-top:10%;" id="id_formUpload" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/acl/uploadLicenseInfo.acl">
		   <table>
		     <tbody>
		        <tr style="text-align:center;color:red;"><td colspan="2"><font>授权信息不正确或者该授权已到期</font></td></tr>
		       <tr><td>授权信息文件（<font color="red">authorize.info</font>）：</td><td><input type="file" name="authorizeInfo" id="id_authorizeInfo"/></td></tr>
		       <tr><td>私钥文件（<font color="red">private.key</font>）：</td><td><input type="file" name="privateKey" id="id_privateKey"/></td></tr>
		       <tr style="text-align:center"><td colspan="2"><input type="submit" value="提交认证信息" style="width:120px;" onclick="" /></td></tr>
		     </tbody>
		   </table>
	   </form>
   </center>
</body>
</html>