<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<title>文件下载信息</title>
<%@include file="/base/mini.jsp"%>
</head>
<body>
<script type="text/javascript">	
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
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false,
  					xmlFileName:vxmlFileName,
  					showToolbar:showTools,
  					tools:[{
                             html:'下载',plain:true,iconCls:'icon-edit',
                           }],
  					columns:[ 
  					    {type:'indexcolumn'},
  					    {type:'checkcolumn'}, 
  						{field:'id',header:'标识',headerAlign:'center',width:100,allowSort:true,visible:false,
	  						  formEditorConfig:{
  								      readOnly:true,
  								  fieldVisible:false}},
						{field:'modelname',header:'模块名称'},
  						{field: 'filename',header:'文件名',headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{  								
								          type:'textarea', 
								       newLine:true,
								       colspan:6,
								    labelWidth:100,								
	  							         width:500,
	  							        height:100}},
						{field: 'creator',header:'创建人',headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{
								       newLine:true,
								          type:'text',
								         value:'${sessionScope.loginUser.realname}',
								      readOnly:true,
								    labelWidth:100,								
  								         width:200 }},						
						{field: 'createdate',header:'创建时间',	headerAlign:'center', width:100,dateFormat:"yyyy-MM-dd HH:mm:ss",
							formEditorConfig:{  								
								        type:'text',
								    readOnly:true,		
								defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss "),
								  labelWidth:100,								
  								       width:200}},
  						
						{field:'',header:'操作',
  						    formEditorConfig:{
							    fieldVisible:false},
						            renderer:function(e){
							              var id = e.record.id;
						                 return "<a href='javascript:void(0);' onclick='downloadFileById(\"" + id + "\")'>下载 </a>";
						}
						}
  					]
  					
  				});
  			});  					
  			
  		});
	</script>

<div id='content_table_id3'></div>
</body>
</html>