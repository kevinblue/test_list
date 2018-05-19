<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理合同催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id9',
			width : globalClientWidth,
			height : globalClientHeight,
			iconCls : 'icon-node',
			hiddenQueryArea : false,
			editFormPopupWindowWidth : 700,
			queryButtonColSpan : 4,
			queryButtonNewLine:true,
			title : '保理合同催收' ,
			remoteOper : false,
			pageSize : 20,
			showPager : true,
			lazyLoad : false,			
			xmlFileName : '/eleasing/workflow/factoring/fund_overdue/factoring_overdue_menu_list.xml',
			//params : {},
			 columns : [ 
			       {type:'indexcolumn'},
			       {type:'checkcolumn'} ,
			       {field:'contractid',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				         formEditorConfig:{
					             readOnly:true,
					         fieldVisible:false}},
				   {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				         formEditorConfig:{
					         fieldVisible:true}},
				   {field:'custname',header:'客户名',headerAlign:'center',width:100,allowSort:true,
				                 renderer:function(e){
					                  var custname = e.record.custname;
					                  var contractid = e.record.contractid;
					                  var custid = e.record.custid;
					                 return "<a href='javascript:void(0);' onclick='opencustdetail(\"" + contractid + "\",\"" + custid + "\")'>" + custname + "</a>";},
				              queryConfig:{
					           labelWidth:100,
					                width:200}},
				   {field:'contractid1',header:'合同编号',headerAlign:'center',width:100,allowSort:true,width:160},
				   {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					           labelWidth:100,
					                width:200}},
				   {field:'statusname',header:'合同状态',headerAlign:'center',visible:false,width:100,allowSort:true,
				              queryConfig:{
					                width:200}},
				   {field:'rawValue_partdept',header:'催款员',headerAlign:'center',
				              queryConfig:{
					                width:200,
					              newLine:false}},
				   {field:'projmanagename',header:'项目经理',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
				            	  newLine:true,
					                width:200}},
				   {field:'outlist',header:'逾期期数',headerAlign:'center',width:100,allowSort:true},
				   {field:'incrent',header:'逾期租金',headerAlign:'center',width:100,allowSort:true},
				   {field:'penalty',header:'逾期罚息',headerAlign:'center',width:100,allowSort:true},
				   {field:'next_commitment_date',header:'计划下次联系日',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					                width:200,
					                 type:'date',
					               format:'yyyy-MM-dd',
					           allowInput:"false"}},
				    {field:'commitment_date',header:'承诺还款日期',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					              newLine:false,
					                width:200,
					                 type:'date',
					           allowInput:"false"}
			} ] 
		});
	});
});
function opencustdetail(contractid,custid){
	 var params = "contractid="+contractid+"&custid="+custid;
	 var url = getRootPath()+"/workflow/forms/factoring/factoring_fund/fund_overdue/factoring_overdue_menu_list_details.bi?"+params;
	 var sheight = window.screen.availHeight - 30;
	 var swidth = window.screen.availWidth - 10;
	 var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
</script>
</head>
<body>
</body>
</html>
