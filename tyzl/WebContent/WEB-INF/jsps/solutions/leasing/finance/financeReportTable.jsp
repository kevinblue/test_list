<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>财务报表配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var userEnabled = [ {id:'是',text:'是'}, {id:'否',text:'否'} ];
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_idfinal',
			width:globalClientWidth,
			height:globalClientHeight,
			title:'财务报表配置',
			iconCls:'icon-node',
			multiSelect:true,
			hiddenQueryArea:true, 
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			editFormPopupWindowWidth:500,
			editFormPopupWindowHeight:300,		
			showPager:true,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.finance.FinancialTable',
			xmlFileName:'/eleasing/jsp/finance/financeTable.xml',
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[ 
					    {type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false,
							   formEditorConfig:{
						 	           readOnly:true,
							       fieldVisible:false}},
						{field:'sheetname',header:'sheet名'},
						{field:'titlename',header:'表名',
							   formEditorConfig:{
							            newLine:true, 
							               type:'text'}},
						{field:'titleindex',header:'排序位置',
								formEditorConfig:{
								         newLine:true, 
								            type:'text'}},
						{field:'keyrowindex',header:'年所在行',
								formEditorConfig:{
							             newLine:true}},
						{field:'keycellindex',header:'数据开始列',
							    formEditorConfig:{
							                type:'text'}},
						{field:'skipcell',header:'跳过列数',
								formEditorConfig:{
							             newLine:true}},	
						{field:'issaveskipdata',header:'是否保存跳过的列',
							    formEditorConfig:{
								            type:'combobox', 
								            data:userEnabled}},
						{field:'skipcelld',header:'配置',
								 formEditorConfig:{
							         fieldVisible:false},
							             renderer:function(e){
												var id = e.record.id;
												return "<a href='javascript:void(0);' onclick='showfinance(\"" + id + "\")'>科目配置表 </a>";}}
						]
			});
	});
});
function showfinance(id){
	var url=getRootPath()+"/leasing/finance/financeReportConfig.bi"; 
	mini.open({
        url: url+"?id="+id,
        title: "科目表配置", width: 800, height: 500,
        showModal: true,
        showMaxButton: true
    });
}
</script>
</head>
<body></body>
</html>