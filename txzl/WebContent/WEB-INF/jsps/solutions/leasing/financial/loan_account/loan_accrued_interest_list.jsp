<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%String startdate=request.getParameter("startdate");%>
<%String enddate=request.getParameter("enddate");%>
<title>计提利息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'loan_atm_plan',
				renderTo:'id_table_loan_atm_plan',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'计提利息',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
  				remoteOper:true,
  				params:{startdate:'<%=startdate%>',enddate:'<%=enddate%>'},
				xmlFileName:'/eleasing/jsp/finacial/loan_account/loan_accrued_interest_list.xml',
				tools:['exportExcel'],
				columns:[ 
				    {type:'indexcolumn'},
				   	{field:'id',header:'id',visible:false},
				   	{field:'loancontractid',header:'合同号',visible:true,allowSort:true,
						queryConfig : {}}, 
				   	{field:'loanunit',header:'企业客户名称',visible:true,
							queryConfig : {},},
				   	{field:'currency',header:'币种',visible:true},
				   	{field:'nowrate',header:'利率%',visible:true},
				   	{field:'surpluscorpus',header:'余额',dataType:'currency',visible:true,
						 queryConfig:{
			                  type:'float',
			                 range:true,
			               colspan:4}},
				   	{field:'interest',header:'利息',dataType:'currency',visible:true},
				  	{field:'startdate',header:'起息日',visible:true,dateFormat:"yyyy-MM-dd"},
				  	{field:'enddate',header:'止息日',visible:true,dateFormat:"yyyy-MM-dd"}
				]
			});
		});
	});
</script>
</head>
<body>
	<div id="id_table_loan_atm_plan"></div>
</body>
</html>