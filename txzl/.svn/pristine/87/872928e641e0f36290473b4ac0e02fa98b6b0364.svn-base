<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>流程信息分组配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_idfinal',
			width:globalClientWidth,
			height:globalClientHeight,
			title:'流程信息分组配置',
			iconCls:'icon-node',
			multiSelect:true,
			hiddenQueryArea:true, 
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			showPager:true,
			remoteOper:true,
			addRemoteOperUrl:getRootPath()+"/acl/addPhoneGroup.acl",
			editRemoteOperUrl:getRootPath()+"/acl/updatePhoneGroup.acl",
			entityClassName:'com.tenwa.jbpm.entity.JbpmPhoneGroup',
			xmlFileName:'/jbpm/flowPhoneGroup.xml',
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[ 
					    {type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false,
							   formEditorConfig:{
						 	           readOnly:true,
							       fieldVisible:false}},
						{field:'groupname',header:'分组名称',
					    	   formEditorConfig:{
					    		      required :true,
						                   type:'text'}},
						{field:'memo',header:'分组描述',
							   formEditorConfig:{
								        newLine:true,
							               type:'text'}},
			           	{field:'islist',header:'是否为列表',
							   formEditorConfig:{
								        newLine:true,
							               type:'combobox',
							               text : "否",
							               textField: 'text',
									        valueField: 'value',
									        value:0,
									        defaultValue:0,
									        data:[{text:"否",value:0},{text:"是",value:1}]
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
						      params:{xmlFileName: '/combos/comboPhoneGroup.xml'}
						               }},
						{field:'peizhi',header:'配置',
								 formEditorConfig:{
							         fieldVisible:false},
							             renderer:function(e){
												var id = e.record.id;
												var islist=e.record.islist;
												return "<a href='javascript:void(0);' onclick='showFormValue(\"" + id+"\",\""+islist + "\")'>数据配置表 </a>";}}
						]
			});
	});
});
function showFormValue(id,islist){
	var url=getRootPath()+"/workflow/jbpm-core/workflowFormValue.bi"; 
	if(islist==1){
		url=getRootPath()+"/workflow/jbpm-core/workflowListValue.bi"; 
	}
	mini.open({
        url: url+"?id="+id+"&islist="+islist,
        title: "数据表配置", width: 800, height: 500,
        showModal: true,
        showMaxButton: true
    });
}
</script>
</head>
<body></body>
</html>