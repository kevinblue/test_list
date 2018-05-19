<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>生成租金还款通知书</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id1',
			width:globalClientWidth,
			height:globalClientHeight,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			editFormPopupWindowWidth:700,
			queryButtonColSpan:4,
			queryButtonNewLine:true,
			remoteOper:true,
			multiSelect:true,
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			title:'生成租金还款通知书',
			tools:[{
				html:'生成租金还款通知书', 
				plain:true, 
				iconCls:'icon-addfolder', 
				handler:function(miniTable, buttonText) { 
					var row = miniTable.getSelecteds();
					if(row.length>0){
						var generatedform = new mini.Form("generatedform");
						var generatededitWindow = mini.get("generatededitWindow");
						generatededitWindow.show();
						generatedform.clear();
					}else{
						mini.alert("请至少选中一行！");
					}
				}
			}],
			xmlFileName:'/eleasing/jsp/fund/fund_reminder/generated_reminder_letter_list.xml',
			params:{},
			columns:[
				   {type:'indexcolumn'},
				   {type:'checkcolumn'},
				   {field:'contract_id',header:'合同号',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					               width:200}},
				   {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					          labelWidth:100,
					               width:200}},
				   {field:'senddate',header:'最后一次发送日期',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					          labelWidth:120,
					             newLine:true,
					               width:200,
					                type:'date',
					              format:'yyyy-MM-dd',
					          allowInput:"false",
					               range:true}},
					{field:'custname',header:'客户名',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					               width:200}},
					{field:'phone',header:'电话',headerAlign:'center',width:100},
				    {field:'address',header:'地址',headerAlign:'center',width:100},
				    {field:'guaranteecustname',header:'担保人',headerAlign:'center',width:100,
				             queryConfig:{
					            
					               width:200}},
					{field:'startdate',header:'起租日',headerAlign:'center',width:100,allowSort:true},
				    {field:'rentlist',header:'期次',headerAlign:'center',width:100,allowSort:true}]
		});
	});
});
</script>
</head>
</html>