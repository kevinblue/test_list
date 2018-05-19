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
<title>抵押物文件信息</title>
<%
	String id = request.getParameter("id");
	String type = request.getParameter("type");
	String isView = request.getParameter("isView");
%>
<%@include file="/base/mini.jsp"%>
</head>
<body>
	<script type="text/javascript">	
   function reloadcustcontactfile(message){
	   mini.alert(message);
	   mini.get("id_uploadfile").hide();
	   mini.unmask(document.body);
	   mini.get("table_id4").reload();
    }
    var id ="<%=id%>";
    //var cust_id='${sessionScope.loginUser.id}';
    var type="<%=type%>";
    var isView="<%=isView%>";
    var tools1 = [];
    if(!isView){
    	 tools1.push( {
    		    html:'上传资料',plain:true,iconCls:'icon-edit',
    		    handler:function(miniTable, buttonText) {
    		       	  var uploadutil=new uploadUtil({
    		 			  url:'/leasing/file/uploadFile.action',modelname:'抵押物文件信息',
    		 			  title:'抵押物文件信息',
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
    		    );
    }
	var custcontactData;
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 				
  				var showxml="";
  				var showTools=true;
  				new ApTable({  					
  					id:'table_id4',  					
  					renderTo:'content_table_id4',
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
  					xmlFileName:"/eleasing/jsp/cust_info/cust_content/contract_guarantee_equip_file.xml",
  					showToolbar:showTools,
  					params:{filekey:id,modelname:'抵押物文件信息'},
  					tools:tools1,
  				
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
						{field: 'createdate',header:'上传时间',	headerAlign:'center', width:100,dateFormat:"yyyy-MM-dd",
							formEditorConfig:{  								
								        type:'text',
								    readOnly:true,		
								defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd "),
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
    			    mini.get("table_id4").reload();
    			},
    			async:false,
    			failure:function(res) { 				
    			},
    			params :param
    		});

	   }
	</script>

	<div id='content_table_id4'></div>
</body>
</html>