<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<title>走访调资料</title>
<%
String id=request.getParameter("id");
String cust_id=request.getParameter("cust_id");
String type=request.getParameter("type");
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
    var id ="<%=id %>";
    var cust_id="<%=cust_id %>";
    var type="<%=type %>";
	var custcontactData;
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 				
  				var showxml="";
  				var showTools=true;
  				var vxmlFileName="";
  				if(type=="one"){
  					 vxmlFileName="/eleasing/jsp/cust_info/cust_contact/cust_contact_file_list.xml";
  	  			}else{
  	  			     showTools=false;
  	  			     vxmlFileName="/eleasing/jsp/cust_info/cust_contact/cust_contact_file_all_list.xml";
  	  	  		}
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
  					entityClassName:'com.tenwa.leasing.entity.cust.CustInfoContact',
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false,
  					xmlFileName:vxmlFileName,
  					showToolbar:showTools,
  					params:{filekey:id,modelname:'走访调研',custid:cust_id},
  					tools:[
                           {
                             html:'上传资料',plain:true,iconCls:'icon-edit',
                             handler:function(miniTable, buttonText) {
	                            	  var uploadutil=new uploadUtil({
	                      			  url:'/leasing/file/uploadFile.action',modelname:'走访调研',
	                      			  title:'走访调研',
	                      		      parames:{id:'custcontactfile',filekey:id},
	                      		      jscallback:'reloadcustcontactfile'}
	                      			);
	                      		   uploadutil.createFileAndShow();
	                            }
                           },'-',
                           {
                               html:'废弃',plain:true,iconCls:'icon-cancel',
                               handler:function(miniTable, buttonText) {
  	                              var row = miniTable.getSelected();
  	                              if(row){
                                   if(row.invalid=="否"){mini.alert("已经废弃的文件不能再废弃");return false;}
  	  	                              
  	                            	setFileState(row.id,"否")
  	                              }else{mini.alert("请选择文件");}
  	                              }
                             },'-',
                             {
                                 html:'恢复',plain:true,iconCls:'icon-ok',
                                 handler:function(miniTable, buttonText) {
    	                              var row = miniTable.getSelected();
    	                              if(row){
    	                            	  if(row.invalid=="是"){mini.alert("该文件已经是有效的");return false;}
    	                            	  setFileState(row.id,"是")
    	  	                              }else{mini.alert("请选择文件");}}
                               }
   		  					],
  					columns:[ 
  					    {type:'indexcolumn'},
  					    {type:'checkcolumn'}, 
  						{field:'id',header:'标识',headerAlign:'center',width:100,allowSort:true,visible:false,
	  						  formEditorConfig:{
  								      readOnly:true,
  								  fieldVisible:false}},
  						{field: 'filename',header:'附件名称',headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{  								
								          type:'textarea', 
								       newLine:true,
								       colspan:6,
								    labelWidth:100,								
	  							         width:500,
	  							        height:100}},
						{field: 'creator',header:'上传人',headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{
								       newLine:true,
								          type:'text',
								         value:'${sessionScope.loginUser.realname}',
								      readOnly:true,
								    labelWidth:100,								
  								         width:200 }},						
						{field: 'createdate',header:'上传时间',	headerAlign:'center', width:100,dateFormat:"yyyy-MM-dd HH:mm:ss",
							formEditorConfig:{  								
								        type:'text',
								    readOnly:true,		
								defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss "),
								  labelWidth:100,								
  								       width:200}},
  						{field:'invalid',header:'是否有效'},
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
  		function downloadFileById(Id) {
  			attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
  		} 
      function setFileState(id,state){
          var param={};
          param["id"]=id;
          param["state"]=state;
    	  ajaxRequest({
    			url:getRootPath() + "/leasing/file/setFileState.action",
    			method:'POST',
    			success:function(rs) {
    				mini.alert(rs.responseText);
    			    mini.get("table_id3").reload();
    			},
    			async:false,
    			failure:function(res) { 				
    			},
    			params :param
    		});

	   }
	</script>

<div id='content_table_id3'></div>
</body>
</html>