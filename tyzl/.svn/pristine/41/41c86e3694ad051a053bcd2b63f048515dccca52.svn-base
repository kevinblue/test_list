<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>国际化数据字典</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				renderTo: "id_table_id1",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'国际化数据字典',
				iconCls:'icon-db',
				multiSelect:false,
				queryButtonColSpan:2,
				width:"100%",
			    isNeedEnabled:true,
				heigth:"100%",
				showPager:true,
				entityClassName : 'com.tenwa.business.entity.I18nDictionary',
				entityBeanCallBackClassName : 'com.tenwa.business.callback.I18NCallBack',
				remoteOper : true,
				xmlFileName:'/acl/queryI18nDict.xml',
				tools:[
					'add', '-', 'edit',
					 {
						html : '同步到propertes',
						plain : true,
						iconCls : 'icon-folder',
						handler : function(miniTable, buttonText) {
							mini.mask({
                				el: document.body,
                				cls: 'mini-mask-loading',
                				html: '正在同步，请稍后...'
                			});
							jQuery.ajax({
								url : getRootPath() + "/table/synI18nProperties.action",
								method : 'POST',
								success : function(rs) {
									var returnState = rs.returnType;
									var message = rs.content;
									mini.unmask(document.body);
									mini.alert(message);
									switch (returnState) {
										case "SUCCESS": {
											break;
										}
										case "FAILURE": {
											break;
										}
									}
								},
								async : false,
								failure : function(res) {
									mini.unmask(document.body);
								},
								dataType : 'json'
							});
						}	
					} ,
					 {
						html : '同步到DB',
						plain : true,
						iconCls : 'icon-db',
						handler : function(miniTable, buttonText) {
							mini.mask({
                				el: document.body,
                				cls: 'mini-mask-loading',
                				html: '正在同步，请稍后...'
                			});
							jQuery.ajax({
								url : getRootPath() + "/table/synI18nDB.action",
								method : 'POST',
								success : function(rs) {
									var returnState = rs.returnType;
									var message = rs.content;
									mini.unmask(document.body);
									mini.alert(message);
									switch (returnState) {
										case "SUCCESS": {
											miniTable.reload();
											break;
										}
										case "FAILURE": {
											break;
										}
									}
								},
								async : false,
								failure : function(res) {
									mini.unmask(document.body);
								},
								dataType : 'json'
							});
						}	
					} 
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'name',header:'名称',formEditorConfig : {required : true,otherAttributes:'onblur="appendNameToZhname"'},queryConfig : {}},
				   	{field:'code',header:'数据编号',formEditorConfig : {required : true,otherAttributes:'onblur="appendNameToEnname"'},queryConfig : {}},
				   	{field:'zhname',header:'中文',formEditorConfig : {newLine:true}},
				   	{field:'enname',header:'英文',formEditorConfig : {required : true}},
				   	{field:'twname',header:'中国台湾',formEditorConfig : {newLine:true}}
				],
				validateForm: function(miniTable, miniForm){
					var params = miniForm.getData(true,true);
					var flag = true;
					jQuery.ajax({
						url : getRootPath() + "/table/checkI18nData.action",
						method : 'POST',
						success : function(rs) {
							var returnState = rs.returnType;
							var message = rs.content;
							switch (returnState) {
								case "SUCCESS": {
									flag = true;
									break;
								}
								case "FAILURE": {
									mini.alert(message);
									flag = false;
									break;
								}
							}
						},
						async : false,
						failure : function(res) {
							mini.unmask(document.body);
						},
						dataType : 'json',
						data :params
					});
					
					return flag;
				}
			});
		});
	});
	function appendNameToZhname(e){
		mini.getbyName('zhname').setValue(e.sender.value) ;
	}
	
	function appendNameToEnname(e){
		mini.getbyName('enname').setValue(e.sender.value) ;
	}
	
</script>
</head>
<body>
	<div id="id_table_id1" style="height: 100%"></div>
</body>
</html>