<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同红冲流程信息</title>
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
				title : '资金红冲流程信息',
				multiSelect : false,
				editFormPopupWindowWidth : 650,
				hiddenQueryArea : false,
				queryButtonColSpan : 6,
				showPager:true,
				xmlFileName : 'eleasing/workflow/fund/red_out/fund_redout_open_list.xml',
				tools : toolsArray,
				remoteOper : false,
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},
				   	{field : 'id',header : 'id',visible : false},
					/* {field : 'contractnumber',header : '业务合同编号',headerAlign : 'center',queryConfig : {},formEditorConfig : { }}, */
					{field : 'contract_put_number',header:'投放编号',headerAlign:'center',queryConfig:{},formEditorConfig:{ } },
					{field : 'planmoneyin',header : '应收资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
					{field : 'planmoneyout',header : '应付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
					{field : 'factmoneyin',header : '已收资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
					{field : 'factmoneyout',header : '已付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
					{field : 'feeadjustin',header : '调整收资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
					{field : 'feeadjustout',header : '调整付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
					{field : 'wymoneyin',header : '未收资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
					{field : 'wymoneyout',header : '未付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
					{field : 'projectname',header : '项目名称',headerAlign : 'center',queryConfig : {},formEditorConfig : {newLine:true }},
					{field : 'custname',header : '承租人',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
					{field : 'projmanagename',header : '项目经理',headerAlign : 'center',queryConfig : {newLine:true},formEditorConfig : {newLine:true }}
				    
				]
			});
		});
});
/* toolsArray callback start*/
var toolsArray =[
					{
						html : '发起红冲流程',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) 
						{
							var row = miniTable.getSelected();	
							if (null != row) {
								var attachmentParams="contract_id="+row.id;
								startProcess("资金红冲流程-1",attachmentParams); 
							}else {
						        mini.alert("请选中一条记录");
						    }
						}
					}
					 				
				];
/* toolsArray end */
</script>
 </head>
 <body>
 </body>
 
<script type="text/javascript">
 
</script>
</html>