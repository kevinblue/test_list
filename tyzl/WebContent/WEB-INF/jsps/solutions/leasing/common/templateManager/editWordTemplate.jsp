<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>文档在线编辑</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<SCRIPT LANGUAGE="JavaScript" src="${pageContext.request.contextPath}/ntko/OfficeContorlFunctions.js"></SCRIPT>
<script type="text/javascript" language="javascript" src="${pageContext.request.contextPath}/print/LodopFuncs.js"></script>
</head>
  <script type="text/javascript">
  var id = "<mini:param  name='id'/>"
  var status="<mini:param  name='otype'/>";
  var curusername="<mini:user/>"
  if(status==""||status=="null"){
      status="1";
  }
  jQuery(function() {
	   if(status!=2){
		   $("#id_tosave").css("display","none");
	   }
  });	  
  var newAction="leasing/template/downLoadAttachByIdtoEdit.action?browserType="+SysBrowser.getBrowser().toLowerCase()+"&id="+id;
  </script>
 <body style="overflow:hidden" onload='intializePage(newAction)' onbeforeunload ="onPageClose()">
		        <div>
			        <a class="mini-button" iconCls="icon-close" plain="true"  onClick="if(window.confirm('确认关闭？'))closeWindow();"  >关闭</a>
			        <a class="mini-button" iconCls="icon-save" plain="true" id="id_tosave" onClick="if(window.confirm('确认保存WORD？'))saveFileToUrl();">保存</a>
			        <a class="mini-button" iconCls="icon-print" plain="true" id="" onClick="OFFICE_CONTROL_OBJ.printout(true);" >打印</a>
			       <!--  <a class="mini-button" iconCls="icon-user" plain="true"  onClick="SetReviewMode(true);">修改留痕</a>  !--> 
			        <a class="mini-button" iconCls="icon-user" plain="true"  onClick="OFFICE_CONTROL_OBJ.ActiveDocument.AcceptAllRevisions();" >接受修改</a>
			   </div>
		 </div>  
         <div>
			<jsp:include page="editword.jsp"></jsp:include>	 
		</div>					
</body>
<script type="text/javascript">
function closeWindow()
{

	if (browser=="IE"||OFFICE_CONTROL_OBJ.activeDocument.saved){
		window.opener=null;
		window.open("","_self");
		window.close();
	}else{ 
		status=-1;
	   onPageClose();	
	}
}
</script>
</html>