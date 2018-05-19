<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同付款流程信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
	     //画清单list table
		new ApTable({
			id : 'table_id_index',
			width : globalClientWidth,
			height : globalClientHeight,
			title : '付款流程信息',
			multiSelect : false, 
			editFormPopupWindowWidth : 650, 
			hiddenQueryArea : false, 
			queryButtonColSpan : 4,
			showPager:true,
			xmlFileName : 'eleasing/workflow/fund/fundcomm/fund_fund_plan_all_list.xml',
			tools : toolsArray,
			remoteOper : false,
			params : {
			    isnoequip:true,
			    paytype:'pay_type_out'
			},
			columns : [ 
			    {type : 'indexcolumn'},
			   	{type : 'checkcolumn'},
			   	{field : 'id',header : 'id',visible : false},
			   	{field : 'projid',header : '项目编号',visible : false,allowSort:true,queryConfig : {},formEditorConfig : { }},
			   	/* {field : 'contractid',header : '合同编号',allowSort:true,formEditorConfig : { }}, */
			   /* 	{field : 'contractnumber',header : '业务合同编号',headerAlign : 'center',queryConfig : {},formEditorConfig : { }}, */
			   	{field : 'contract_put_number',header:'投放编号',headerAlign:'center',queryConfig:{},formEditorConfig:{ } },
			   	{field : 'planmoney',header : '应付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
				{field : 'factmoney',header : '已付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
				{field : 'feeadjust',header : '调整资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : { }},
				{field : 'overmoney',header : '未付资金合计',summary : true,dataType : "currency",headerAlign : 'center',formEditorConfig : {newLine:true }},
				{field : 'collectstatus',header : '付款状态',headerAlign : 'center',formEditorConfig : {newLine:true},
					renderer : function(e){
					  return setfundPayState(e.record);
			     	}
		    	 },
			   	{field : 'projectname',header : '项目名称',headerAlign : 'center',queryConfig : {},formEditorConfig : {newLine:true }},
			   	{field : 'custname',header : '承租人',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
			   	{field : 'projmanagename',header : '项目经理',headerAlign : 'center',queryConfig : {newLine:true},formEditorConfig : {newLine:true }}
			   	
			]
		});
	});
});
var toolsArray =
	[
		{
			html : '发起付款流程',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) 
			{
				var row = miniTable.getSelected();
				var overmoney=row.overmoney;
				var attachmentParams = "contract_id="+row.id;
				if(parseFloat(overmoney)<=0){mini.alert("没有要付款的计划");return false;}
				startProcess("付款流程-1",attachmentParams); 
			}
		}
	 			
	];



</script>
 </head>
 <body>
 </body>
 
</html>