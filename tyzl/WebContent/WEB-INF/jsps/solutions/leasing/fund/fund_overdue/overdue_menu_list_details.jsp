<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var contractid = "<mini:param  name='contractid'/>";
var custid = "<mini:param  name='custid'/>";
var screenHeight = window.screen.height;
var fixedHeight = 300;
var topHeight = 460;
//合同信息
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id1',
			width : "100%",
			height : fixedHeight,
			frozenStartColumn : 0,
			frozenEndColumn : 2,
			iconCls : 'icon-node',
			renderTo:'content_table_id1',
			remoteOper : true,
			pageSize : 10,
			showPager : true,
			lazyLoad : false,
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/contract_list.xml',
			params : {custid :custid},
			columns : [ 
				     {type:'indexcolumn'},
			         {type:'checkcolumn'} , 
			         {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				             formEditorConfig:{
					                 readOnly:true,
					             fieldVisible:false }},
					 {field:'custname',header:'客户名',headerAlign:'center',width:100,allowSort:true},
					 {field:'contractid1',header:'合同号',headerAlign:'center',width:100,allowSort:true,width: 200},
					 {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,width: 200},
					 {field:'statusname',header:'合同状态',headerAlign:'center',width:100,allowSort:true},
					 {field:'rawValue_partdept',header:'催款员',headerAlign:'center',width:100,allowSort:true},
					 {field:'department',header:'项目出单部门',headerAlign:'center',width:100,allowSort:true},
					 {field:'projmanage',header:'项目经理',headerAlign:'center',width:100,allowSort:true},
					 {field:'outlist',header:'逾期期数',headerAlign:'center',width:100,allowSort:true},
					 {field:'incrent',header:'逾期租金',headerAlign:'center',width:100,allowSort:true},
					 {field:'penalty',header:'逾期罚息',headerAlign:'center',width:100,allowSort:true},
					 {field:'incomenumber',header:'总期数',headerAlign:'center',width:100,allowSort:true},
					 {field:'custdetails',header:'联系方式',headerAlign:'center',width:100,allowSort:true,width: 200},
					 {field:'startdate',header:'起租日',headerAlign:'center',width:100,allowSort:true},
					 {field:'guaranteecustname',header:'担保人',headerAlign:'center',width:100,allowSort:true},
					 {field:'clientaddress',header:'承租人邮寄地址',headerAlign:'center',width:100,allowSort:true}
					]
		});
	});
});

</script>
</head>
<body style="overflow: auto">
<div class="mini-panel" title="合同信息" showCollapseButton="true" style="width: 100%;">
	<div id='content_table_id1'></div>
</div>
<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
  <div title="催收记录" name="relatedPerson" >
  	<jsp:include page="overdue_dunning_record.jsp" ></jsp:include>
  </div>
  <div title="主管领导指示" name="relatedPerson" >
  	<jsp:include page="overdue_dunning_directives.jsp" ></jsp:include>
  </div>
  <div title="出险项目情况表指示" name="relatedPerson" >
  	<jsp:include page="overdue_project.jsp" ></jsp:include>
  </div>
</div>
</body>
</html>
