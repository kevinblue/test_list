<%@ page language="java" import="java.net.URLDecoder" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<%String planid=request.getParameter("planid");%>
<script type="text/javascript">
var planid = "<%= planid %>";
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
			iconCls : 'icon-node',
			renderTo:'content_table_id1',
			remoteOper : true,
			pageSize : 10,
			showPager : true,
			lazyLoad : false,
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/contract_list_remind.xml',
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord',
			params : {planid :planid},		
			columns : [ 
			         {type:'indexcolumn'},
			         {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'personname',header:'联系人',headerAlign:'center',width:100,
			        	 formEditorConfig:{
			        		 readOnly : true,
			        		 newLine:true,
			        	 }
			         },
					 {field:'contractdate',header:'催收时间',headerAlign:'center',width:100,
						 formEditorConfig:{
							 readOnly : true,
							 newLine:true,
			        	 }
			         },
					 {field:'contactwayname',header:'联系方式',headerAlign:'center',width:100,visible:true,
						 formEditorConfig:{
							 fieldVisible:false,
							 vosible:false,
							 fillFromFieldName:'contactway',
							 fillProperty:'name',
							 newLine:true,
						 }
					 },
					 {field:'contactway',header:'联系方式',headerAlign:'center',width:100,visible:false,
						 formEditorConfig : {
								type:'combobox',
								newLine:true,
								params : {
									pid:'visit_mode',
									xmlFileName : 'combos/comboDict.xml'
								},
								readOnly:false,
								fieldVisible:true,
								textField:'name',
								valueField:'value'
							}
					 },
					 {field:'commitmentinfo',header:'联系情况',headerAlign:'center',width:100,
						 formEditorConfig : {
								newLine:true,
								
							}	 
					 }
					 ]
		});
	});
});

function onhireTypeComboboxChanged(e){
	document.getElementById('contactWay').value = e.sender.text;
}

</script>

</head>
<body style="overflow: auto">
<div>
	<div id='content_table_id1'></div>
</div>
</body>
</html>
