<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>数据表配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%String id=request.getParameter("id");%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id1',
			width:globalClientWidth,
			height:globalClientHeight,
			title:'',
			iconCls:'icon-node',
			multiSelect:true,
			hiddenQueryArea:true,//是否将查询区域折叠起来
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			showPager:true,
			remoteOper:true,
			entityClassName:'com.tenwa.jbpm.entity.JbpmListValues',
			xmlFileName:'/jbpm/flowListValues.xml',
			addRemoteOperUrl:getRootPath()+"/acl/addListValues.acl",
			editRemoteOperUrl:getRootPath()+"/acl/updateListValues.acl",
			showToolbar: showTools,
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			params:{pid:"<%=id%>"},
			columns:[ 
					    {type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false,
							   formEditorConfig:{
							           readOnly:true,
							       fieldVisible:false}},
						{field:'phonegroupid',header:'phonegroupid',visible:false,
							   formEditorConfig:{
							           readOnly:true,
							       fieldVisible:false,
							              value:"<%=id%>"}},
						{field:'subjectname',header:'数据名称'},
						{field:'subjectcode',header:'数据域名',
							   formEditorConfig:{
							            newLine:true, 
							           required:true,
							               type:'text'}},
			           	{field:'listshow',header:'列表页面显示',
							   formEditorConfig:{
								        newLine:true,
							               type:'combobox',
							               text : "是",
							               textField: 'text',
									        valueField: 'value',
									        value:1,
									        defaultValue:1,
									        data:[{text:"是",value:1},{text:"否",value:0}]
							               }},	         	
		               {field:'detailshow',header:'详情页面显示',
						   formEditorConfig:{
						        newLine:true,
					               type:'combobox',
					               text : "是",
					               textField: 'text',
							        valueField: 'value',
							        value:1,
							        defaultValue:1,
							        data:[{text:"是",value:1},{text:"否",value:0}]
					               }},	         
						 {field:'position_',header:'位置',
								        visible:false,     	   
							   formEditorConfig:{
								        newLine:true,
							               type:'combobox',
							      fieldVisible : true,
							          textValue:name,
							          textField: 'name',
							         valueField: 'value',
							             value:999999,
							      params:{phonegroupid : "<%=id%>",xmlFileName: '/combos/comboListValues.xml'}
							               }},			
						]
			});
	});
});

</script>
</head>
<body></body>
</html>