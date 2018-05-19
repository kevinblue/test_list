<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>科目表配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%String id=request.getParameter("id");%>
<script type="text/javascript">
jQuery(function() {
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
			editFormPopupWindowWidth:150,
			editFormPopupWindowHeight:250,		
			showPager:true,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.finance.FinancialSubjects',
			entityBeanCallBackClassName:'com.tenwa.leasing.serviceImpl.Finance.financeConfigCallBack',
			xmlFileName:'/eleasing/jsp/finance/financeConfig.xml',
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			params:{pid:"<%=id%>"},
			columns:[ 
					    {type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false,
							   formEditorConfig:{
							           readOnly:true,
							       fieldVisible:false}},
						{field:'financialtable',header:'financialtable',visible:false,
							   formEditorConfig:{
							           readOnly:true,
							       fieldVisible:false,
							              value:"<%=id%>"}},
						{field:'subjectname',header:'科目名称'},
						{field:'subjectcode',header:'科目编号',
							   formEditorConfig:{
							            newLine:true, 
							               type:'text'}},
					    {field:'tableindex',header:'科目顺序',
							   formEditorConfig:{
									 newLine:true, 
								     type:'text'}},							               
						{field:'subjectindex',header:'EXCEL行',
								formEditorConfig:{
								         newLine:true, 
								            type:'text'}},
						{field:'subjectcell',header:'EXCEL列',
								formEditorConfig:{
									     newLine:true,   
											type:'text'}},
						{field:'percent',header:'是否显示百分比',
								formEditorConfig:{
										 newLine:true, 
											type:'text'}},			
						{field:'formula',header:'科目最小数值',visible : false,
							   formEditorConfig:{
									    newLine:true, 
									    fieldVisible : false,
									       type:'text'}},
						{field:'contrast',header:'年初所在的列',visible : false,
											   formEditorConfig:{
													    newLine:true, 
													    fieldVisible : false,
													       type:'text'}},
						{field:'gongshi',header:'大型对应列',
								formEditorConfig:{
										 newLine:true, 
											type:'text'}},
						{field:'gongshismall',header:'小型对应列',
								formEditorConfig:{
										 newLine:true, 
											type:'text'}},
						{field:'gongshisipp',header:'供应商对应列',
								formEditorConfig:{
										 newLine:true, 
											type:'text'}},
						{field:'gongshity',header:'通用对应列',
								formEditorConfig:{
										newLine:true, 
											type:'text'}},
						]
			});
	});
});

</script>
</head>
<body></body>
</html>