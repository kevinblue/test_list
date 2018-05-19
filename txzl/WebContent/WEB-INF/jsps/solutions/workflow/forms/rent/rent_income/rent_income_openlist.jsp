<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金回笼</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
var tools = [];
tools.push({
	html: '租金回笼',
	plain: true,
	iconCls: 'icon-addfolder',
	handler: function(miniTable, buttonText){
		var rentRowData = miniTable.getSelected();
		if(rentRowData){
			mini.get("id_table_window_fund_ebank_data").show();
		}else{
			mini.alert("请选中要操作的数据！");
		}
	}
});
jQuery(function(){
	//流程列表
	tenwa.createTable({
		id: "rent_income",
		renderTo: "id_table_container_rent_income",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "租金回笼",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: tools,
		queryButtonColSpan: 6,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/rent/rent_income/rent_income_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'contract_id', header: '合同编号'},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{width: 165}},
			{field: 'project_name', header: '项目名称',queryConfig:{width: 165}},
			{field: 'currentoverage', header: '租金余额',summary : true,dataType : "currency"},
			{field: 'penaltyoverage', header: '罚息余额',summary : true,dataType : "currency"},
			{field: 'cust_name', header: '客户名称',queryConfig:{width: 165,newLine: false}},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '项目状态'}
			
		]
	});
	//网银列表
	tenwa.createTable({
		id: "fund_ebank_data",
		renderTo: "id_table_container_fund_ebank_data",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		remoteOper: false,
		showPager: true,
		pageSize: 20,
		queryButtonColSpan: 2,
		tools: [{
			html: '发起租金回笼流程',
			plain: false,
			iconCls: 'icon-folder',
			handler: function(miniTable, buttonText){
				var fundRowData = miniTable.getSelected();
				var rentRowData = mini.get("rent_income").getSelected();
				if(rentRowData && fundRowData){
					var attachmentParams = "contract_id=" + rentRowData.id + "&ebank_id=" + fundRowData.id;
					startProcess("租金回笼流程-1",attachmentParams);
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		}],
		params: {invalid:'否',ismayopemoney:true},
		xmlFileName: '/eleasing/jsp/ebank/fundebank/fund_ebank_data_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'ebdataid', header: '网银编号',queryConfig:{}},
			{field: 'sn', header: '流水号',queryConfig:{}},
			{field : 'custidname',header : '承租人名称',visible:true},
			{field: 'clientname', header: '付款人',queryConfig : {colspan:3}},
			{field: 'factmoney', header: '到账金额',summary : true,dataType : "currency",queryConfig:{newLine:true,type:'float',range:true,colspan:4}},
			{field: 'nowithmoney', header: '非业务金额',summary : true,dataType : "currency"},
			{field : 'hadmoney',header : '已核销金额',summary : true,dataType : "currency",headerAlign : 'center'},
			{field: 'mayopemoney', header: '可核销金额',summary : true,dataType : "currency"},
			{field: 'factdate', header: '到账时间'},
			{field: 'ownbank', header: '本方银行'},
			{field: 'ownaccnumber', header: '本方账号'},
			{field: 'ownaccount', header: '本方账户'},
			{field: 'clientbank', header: '对方银行',queryConfig:{}},
			{field: 'clientaccnumber', header: '对方账号',queryConfig:{}},
			{field: 'clientaccount', header: '对方账户'},
			{field: 'uploaddate', header: '上传时间',type:'date',dateFormat:"yyyy-MM-dd hh:mm:ss"}
		]
	});
});
</script>
</head>
<body>
<div id="id_table_container_rent_income"></div>
<div id="id_table_window_fund_ebank_data" class="mini-window" title="选择网银" 
	style="width:1000px;height:600px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_table_container_fund_ebank_data" style="width: 100%;height: 100%;"></div>
</div>
</body>
</html>