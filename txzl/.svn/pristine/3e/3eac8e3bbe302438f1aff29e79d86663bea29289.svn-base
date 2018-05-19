<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
var selectStr = "";
<permission:action code="assetmanager">
	selectStr = " and  dunningid='${sessionScope.login_userid}'";
</permission:action>
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id8',
			width : globalClientWidth,
			height : globalClientHeight,
			iconCls : 'icon-node',
			hiddenQueryArea : false,
			editFormPopupWindowWidth : 700,
			queryButtonColSpan : 4,
			title : '租金催收' ,
			remoteOper : true,
			pageSize : 20,
			showPager : true,
			lazyLoad : false,			
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/overdue_menu_list.xml',
			params : {
				selectStr:selectStr
			},
			columns : [ 
			       {type:'indexcolumn'},
			       {type:'checkcolumn'} ,
			       {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
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
				   {field:'contractid1',header:'合同号',headerAlign:'center',width:100,allowSort:true,width:160,
				              queryConfig:{
					                width:200}},
				   {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					           labelWidth:100,
					                width:200}},
				   {field:'status_name',header:'合同状态',headerAlign:'center',visible:false,width:100,allowSort:true,
				              queryConfig:{
					                width:200}},
				   {field:'dunningname',header:'催款员',headerAlign:'center',visible:false,
				              queryConfig:{
					                width:200,
					              newLine:true}},
				   {field:'projmanagename',header:'项目经理',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					                width:200}},
				   {field:'outlist',header:'逾期期数',headerAlign:'center',width:100,allowSort:true},
				   {field:'outdate',header:'逾期天数',headerAlign:'center',width:100,allowSort:true},
				   {field:'incrent',header:'逾期租金',headerAlign:'center',width:100,allowSort:true},
				   {field:'penalty',header:'剩余罚息',headerAlign:'center',width:100,allowSort:true},
				   {field:'next_commitment_date',header:'计划下次联系日',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					                width:200,
					                 type:'date',
					               format:'yyyy-MM-dd',
					           allowInput:"false"}},
				    {field:'commitment_date',header:'承诺还款日期',headerAlign:'center',width:100,allowSort:true,
				              queryConfig:{
					              newLine:true,
					                width:200,
					                 type:'date',
					           allowInput:"false"}
			}]
		});
	});
});
function opencustdetail(contractid,custid){
	 var params = "contractid="+contractid+"&custid="+custid;
	 var url = getRootPath()+"/leasing/fund/fund_overdue/overdue_menu_list_details.bi?"+params;
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
