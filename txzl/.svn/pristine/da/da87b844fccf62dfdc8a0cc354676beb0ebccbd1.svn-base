<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>APP版本管理</title>   
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		var phonetype=[{id:'android'},{id:'ios'}];
		var isnew=[{id:'yes',name:'是'},{id:'no',name:'否'}];
		var isneed=[{id:'yes',name:'Y'},{id:'no',name:'N'}];
		new ApTable({
			id : 'table_app_version',
			width : globalClientWidth,
			height : globalClientHeight,
			title : 'APP版本管理',
			iconCls : 'icon-node',
			multiSelect : false,
			hiddenQueryArea : true, 
			queryButtonColSpan : 2,
			queryButtonNewLine:false,
			showPager:true,
			remoteOper : true,
			entityClassName : 'com.tenwa.business.entity.AppVersion',
			xmlFileName : '/eleasing/jsp/template_word/appversion_info.xml',
			tools : [ 'add', '-', 'edit', '-', 'remove', '-',
                   {
				     html : '上传安装包',plain : true,iconCls : 'icon-edit',
				     handler : function(miniTable, buttonText) {
					       var row = miniTable.getSelected();
					       if(row){
					    	   uploadFile(row.id,row.versionid);
					        }else{
					         mini.alert('请选择版本！！！');
					    }}
			        }
			      ],
			columns : [ 
					    {type : 'indexcolumn'},
						{type : 'checkcolumn'},  
						{field : 'id',header : 'id',visible : false,formEditorConfig : {readOnly : true,fieldVisible : false}},
						{field:'versionid',header:'版本号',
							queryConfig:{newLine: true},
							formEditorConfig : {
								type : 'text',
								required :true
								}
						},
						{field:'versionname',header:'版本名称',
							queryConfig:{},
							formEditorConfig : {
								type : 'text',
								newLine: true,
								required :true
								}
						},
						{field:'versioncode',header:'版本下载码',
							queryConfig:{newLine: true},
							formEditorConfig : {
								newLine: true,
								type : 'text',
								required :true
								}
						},
						{field:'updates',header:'发布日期',
							queryConfig:{},
							formEditorConfig : {
								type : 'date',
								newLine : true }
						},	
						{field:'phonetype',header:'手机类型',
							formEditorConfig : {
								newLine:true,
	   			                 type:'combobox',
				             required:false,
				          multiSelect:false,
				            textField:'id',
				           valueField:'id',
				           showNullItem:"true",
				              data:phonetype
								}
						},
						{field:'isnew',header:'是否最新版本',
							formEditorConfig : {
								newLine:true,
	   			                 type:'combobox',
				             required:false,
				          multiSelect:false,
				            textField:'name',
				           valueField:'name',
				           showNullItem:"true" ,
				                  data:isnew
							}
						},
						{field:'mustupdate',header:'是否强制更新',
							formEditorConfig : {
								newLine:true,
	   			                 type:'combobox',
				             required:false,
				          multiSelect:false,
				            textField:'name',
				           valueField:'name',
				           showNullItem:"true" ,
				                  data:isneed
							}
						},
						{field:'skipcelld',header:'安装包',formEditorConfig : {
							fieldVisible : false
							},
							renderer : function(e){
								var id = e.record.basefileid;
								 if(e.record.imagepath.length<2){
							        	return "无安装包";
							         }else{
							        	 return "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载 </a>";
							        }
								
							}
							}
						]
			});
	});
});
</script>
</head>
<body  >
<script type="text/javascript">
   	//上传模板
	function uploadFile(id,versionid){
		var uploadutil=new uploadUtil({
			url:'/leasing/file/uploadFile.action',modelname:'app_version',
			title:'上传安装包',
	        parames:{appId:id,versionid:versionid}}
		);
	   uploadutil.createFileAndShow();
	   	}
	function downloadFile(Id)
	{
		mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'下载中...'});
		var uploadutil=new uploadUtil({url:'/leasing/template/downLoadAttachById.action',parames:{id:Id}});
		uploadutil.createFormAndDown();
	    mini.unmask(document.body);
	}	
	function reloadTable(message){
		mini.alert(message);
		mini.get("table_app_version").reload();
	}

</script>
</body>
</html>