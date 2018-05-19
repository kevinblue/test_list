<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<title>公章相关附件</title>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
%>
<%@include file="/base/mini.jsp"%>
</head>
<body>
	<script type="text/javascript">	
   function reloadcustcontactfile(message){
	   mini.alert(message);
	   mini.get("id_uploadfile").hide();
	   mini.unmask(document.body);
	   mini.get("table_id3").reload();
    }
    var id ="<%=id%>";
    var type="<%=type%>";
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 				
  				new ApTable({  					
  					id:'table_id3',  					
  					renderTo:'content_table_id3',
  					width:globalClientWidth,
  					height:420,					 					
  					editFormPopupWindowWidth:600,
  					editFormPopupWindowHeight:400,  										
  					iconCls:'icon-node',
  					hiddenQueryArea:true,
  					remoteOper:true,
  					entityClassName:'com.tenwa.leasing.entity.officialsealregistration.OfficialSealRegistration',
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false,
  					showToolbar:false,
  					xmlFileName: '/eleasing/workflow/contract/seal_registration/seal_accessories_report_list.xml',
  					params:{filekey:id,modelname:'公章使用相关附件'},
  					columns:[ 
  					    {type:'indexcolumn'},
  					    {type:'checkcolumn'}, 
  						{field:'id',header:'标识',headerAlign:'center',width:100,allowSort:true,visible:false},
  						{field: 'filename',header:'附件名称',headerAlign:'center' ,width:100,allowSort:true},
						{field: 'realname',header:'上传人',headerAlign:'center' ,width:100,allowSort:true},
						{field: 'createdate',header:'上传时间',headerAlign:'center', width:100,dateFormat:"yyyy-MM-dd HH:mm:ss"},
						{field:'',header:'操作',
  						    formEditorConfig:{
							    fieldVisible:false},
						            renderer:function(e){
							              var id = e.record.id;
							              var filename = e.record.filename;
						                 return "<a href='javascript:void(0);' onclick='downloadFileById(\"" + id + "\")'>下载 </a>";
						}
						}
  					]
  					
  				});
  			});  					
  			
  		});
  		function downloadFileById(Id) {
  			attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
  		} 
	</script>
	<div id='content_table_id3'></div>
</body>
</html>