<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同资金计划变更流程信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
	     //画清单list table
		new ApTable({
			id : 'table_id_index',
			width : globalClientWidth,
			height : globalClientHeight,
			title : '资金计划变更信息',
			multiSelect : false, 
			editFormPopupWindowWidth : 650, 
			hiddenQueryArea : false, 
			queryButtonColSpan :6,
			showPager:true,
			xmlFileName : 'eleasing/workflow/fund/fund_plancharg/fund_plancharg_open_list.xml',
			tools : toolsArray, 
			remoteOper : false, 
			columns : [ 
			    {type : 'indexcolumn'}, 
			   	{type : 'checkcolumn'},
			   	{field : 'id',header : 'id',visible : false},
			   	{field : 'contractid',header : '合同编号',allowSort:true,formEditorConfig : { }},
			 	{field : 'contractnumber',header : '业务合同编号',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
			   	{field : 'projectname',header : '项目名称',headerAlign : 'center',queryConfig : {},formEditorConfig : {newLine:true }},
			   	{field : 'custname',header : '客户名称',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
			   	{field : 'projmanagename',header : '项目经理',headerAlign : 'center',queryConfig : {newLine:true},formEditorConfig : {newLine:true }}
			  
				
			]
		});
	});
});

var toolsArray =
	[
		{
			html : '发起资金计划变更流程',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) 
			{
				var row = miniTable.getSelected();
				var attachmentParams = "contract_id="+row.id;// row.id 去contract_info 表主键id
				startProcess("资金计划变更-1",attachmentParams); 
			}
		}
	 			
	];
/* toolsArray end */
</script>
 </head>
 <body>
 </body>
 
</html>