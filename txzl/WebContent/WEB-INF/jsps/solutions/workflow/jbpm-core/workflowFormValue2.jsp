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
	var datatype=[{id:"文本",text:"文本"},{id:"数值",text:"数值"},{id:"日期",text:"日期"},{id:"下拉框",text:"下拉框"}];
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
			entityClassName:'com.tenwa.jbpm.entity.JbpmFormValues',
			xmlFileName:'/jbpm/flowFormValues.xml',
			addRemoteOperUrl:getRootPath()+"/acl/addFormValues.acl",
			editRemoteOperUrl:getRootPath()+"/acl/updateFormValues.acl",
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
						{field:'subjectname',header:'列表名称'},
						{field:'subjectcode',header:'列表域名',
							   formEditorConfig:{
							            newLine:true, 
							           required:true,
							               type:'text'}},
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
							      params:{phonegroupid : "<%=id%>",xmlFileName: '/combos/comboFormValues.xml'}
							               }},
		               {field:'peizhi',header:'显示配置',
							 formEditorConfig:{
						         fieldVisible:false},
						             renderer:function(e){
											var id = e.record.id;
											return "<a href='javascript:void(0);' onclick='showListValue(\"" + id + "\")'>显示配置表 </a>";}}
						]
			});
	});
});
function showListValue(id){
	var url=getRootPath()+"/workflow/jbpm-core/workflowListValue.bi"; 
	mini.open({
        url: url+"?id="+id,
        title: "数据表配置", width: 800, height: 500,
        showModal: true,
        showMaxButton: true
    });
}
</script>
</head>
<body></body>
</html>