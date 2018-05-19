<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<title>机组选型资料</title>
<%
String id=request.getParameter("id");
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
    var type="<%=type %>";
	var custcontactData;
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 				
  				var showxml="";
  				var showTools=true;
  				var vxmlFileName="";
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
  					xmlFileName:'/eleasing/jsp/finacial/loan_account/loan_account_file_list.xml',
  					showToolbar:showTools,
  					params:{filekey:id,modelname:'机组选型资料'},
  					tools:[
                           {
                             html:'上传资料',plain:true,iconCls:'icon-edit',
                             handler:function(miniTable, buttonText) {
	                            	  var uploadutil=new uploadUtil({
	                      			  url:'/leasing/file/uploadFile.action',modelname:'机组选型资料',
	                      			  title:'机组选型资料',
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
						{field:'',header:'操作',align:'center',
  						    formEditorConfig:{
							    fieldVisible:false},
						            renderer:function(e){
							              var id = e.record.id;
							              var filename = e.record.filename;
						                 return "<a href='javascript:void(0);' onclick='downloadFileById(\"" + id + "\")'>下载 </a>"+"&nbsp&nbsp"+"<a href='javascript:void(0);' onclick='showFile2(\"" + id +"\",\""+filename+"\")'>预览</a>";
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

	   };
      
      
    //根据单个图片数据获取一个document
      function createAttachmentImageDiv2(appImage,filedict){
      	var $div = jQuery('<div/>');
      	if(appImage){
      		$div.addClass("img-box");
      		$div.attr("imageid", appImage.id);
      		var $image = jQuery("<img/>");
      		$image.attr("height", appImage.height);
      		$image.attr("width", appImage.width);
      		$image.attr("realUrl", appImage.imagepath);
      		$image.attr("alt", appImage.title);
      		$image.attr("src", getRootPath()+"/acl/qeryCustfile.acl?filedict="+filedict+"&previewtype=second");
      		$image.appendTo($div);
      	}
      	return $div;
      }
    //预览
      function showFile2(filedict,filename){
    	var filenameStr = filename;
      	mini.mask({
      	    el: document.body,
      	    cls: 'mini-mask-loading'
      	});
      	var filename=filename.substring(filename.lastIndexOf('.') + 1);
      	var fileExtension = filename.toLowerCase();
      	if(fileExtension=="jpg"||fileExtension=="jpeg"||fileExtension=="png"||fileExtension=="bmp"||fileExtension=="gif"||fileExtension=="pdf"){
      		var url = getRootPath() + '/table/getTableData.action';
      		var params = { xmlFileName:'/common/queryCustFile.xml',"filedict":filedict};
      		//params.custid=custid;
      		$.ajax({
      	        url:url,
      	      data:params,
      	      type:'post',
      	        success:function(res){
      	            var result = JsonUtil.decode(res);
      			    var datas = result['datas'];
      			    if(datas && datas.length > 0){
      			    	jQuery("#imag").html('');
      			    	jQuery.each(datas, function(index, appImage){
      			    		var img = createAttachmentImageDiv2(appImage,filedict);
      			    		if(img){
      				    		jQuery("#imag").append(img);
      			    		}
      			    	});
      			    } else {
      			    	jQuery("#imag").html('');
      			    }
      			    mini.unmask(document.body);
      	        },
      	        failure:function(res){
      	        	mini.unmask(document.body);
      	        }
      	    });
      		mini.get("showfile2").show();
      	}else{
      		$.ajax({
      	        url: getRootPath()+"/acl/qeryCustfile.acl",
      	        type: "post",
      	        cache: false, 
      	        data :{"filedict":filedict,"previewtype":"second"},
      	        async : false,
      	        success: function (text) {
      			     var str=text;
      			   	 testwin = open("", "testwin","status=no,menubar=yes,toolbar=no");
      				 testwin.document.open();
      				 testwin.document.write(str);
      				 testwin.document.title = filenameStr;
      				 testwin.document.close();
      				
      	        }
      	    });
      		 mini.unmask(document.body);
      	}
      	

      }
	</script>

<div id='content_table_id3'></div>
<div id="showfile2" class="mini-window" title="预览" showMaxButton="true"
			style="width: 100%; height: 100%;" showCloseButton="true"  showModal="true"
			allowResize="true" allowDrag="true" >
			<div id="imag">
			</div>
	        </div>
</body>
</html>