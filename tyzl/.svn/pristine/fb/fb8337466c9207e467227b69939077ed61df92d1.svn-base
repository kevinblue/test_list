<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404</title>
	 <style type='text/css'>
		 html,body{
			    border:0px solid #DDD;
			    border-top:0px;
			    padding:0px;
			    margin:0px; 
			    overflow:hidden;
			    font-size:12px;
		    }
	 </style>
</head>
<body >
      <!-- 
       404页面是WWW网站访问比较经常出现的错误。
                   最常见的出错提示：404 NOT FOUND。404页面就是当用户输入了错误的链接时，返回的页面。 
                   错误原因原因类型　HTTP 404 错误意味着链接指向的网页不存在，即原始网页的URL失效，这种情况经常会发生，很难避免，
                   比如说：网页URL生成规则改变、网页文件更名或移动位置、导入链接拼写错误等，导致原来的URL地址无法访问；
                   当Web 服务器接到类似请求时，会返回一个404 状态码，告诉浏览器要请求的资源并不存在。 
       404错误页面导致这个错误的原因一般来说，有三种：  　　
       1、无法在所请求的端口上访问Web站点。 　　
       2、Web服务扩展锁定策略阻止本请求。 　　
       3、MIME映射策略阻止本请求。 解决方法　　
                   把缺少的网页文件恢复到正确的位置，重新设置网络服务。
                   对于用户来讲，需要检查你所输入的网络地址是否正确。
    -->
    <iframe frameborder="0" style="width:100%;border:1px solid #DDD;border-top:0px;overflow:hidden;" id="id_frame" src="${pageContext.request.contextPath}/errorPages/error404Page.bi"></iframe>
    <script type="text/javascript">
	    document.getElementById("id_frame").style.width = (document.documentElement.clientWidth-2)+"px";
	    document.getElementById("id_frame").style.height = (document.documentElement.clientHeight-2)+"px";
	    if(window.currentJbpmWorkflowHistoryInfoId){
	    	document.getElementById("id_frame").style.width = (document.documentElement.clientWidth-42)+"px";
	    	document.getElementById("id_frame").style.height = (document.documentElement.clientHeight-122)+"px";
	    	document.getElementById("id_frame").style.borderTop="1px solid #DDD";
		}
	    
	</script>
</body>
</html>
