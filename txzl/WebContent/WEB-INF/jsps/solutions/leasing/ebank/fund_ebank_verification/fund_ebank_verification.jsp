<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>网银批量核销</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
var attachmentParams ="";	
function contractMatching(table) {
	var factdate = $("input[name='fundebank_id_queryArea_factdate']").val();
	ajaxRequest({
		url : getRootPath() + "/acl/checkContractMatching.acl",
		method : 'POST',
		success : function(res) {
			mini.alert("操作成功");
			mini.get('fundebank_id').reload();
		},
		async : false,
		failure : function(res) {
		},
		params : {
			factdate : factdate
		}
	});
}

function checkedRecors(table) {
	ajaxRequest({
		url : getRootPath() + "/acl/deleteCheckedRecors.acl",
		method : 'POST',
		success : function(res) {
			mini.alert("操作成功");
			mini.get('fundebank_id').reload();
		},
		async : false,
		failure : function(res) {
		},
		params : {}
	});
}

var toolsArray = [];
toolsArray.push(
		
		{
			html : '自动匹配',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				contractMatching(miniTable);
			}
		}, 
		'-',{
			html : '自动核销',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				checkedRecors(miniTable);
			}
		},'-',
		{
			html : '人工匹配',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				manualMatching(miniTable);
			}
		},'-',{
			html : '罚息回笼',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) {
				showPenaltyIncomeWindow(miniTable);
			}
		}
		);

/* <permission:action code="ebankback">
toolsArray.push(
		'-',
		{
	    	html:'网银退还',
			plain:false,
			iconCls:'icon-addfolder',
			handler:function(miniTable, buttonText){
				var RowData = miniTable.getSelected();
				if(RowData){	
					attachmentParams =attachmentParams+"&ebank_id="+RowData.id;
					 startProcess("网银退还流程-1",attachmentParams);  
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
	    }
		);
</permission:action> */

var toolsFlag = '0';
jQuery(function(){
	tenwa.createTable({
		id: 'fundebank_id',
		renderTo: 'id_table_fundebank_id',
		width: '100%',
		height: '100%',
		lazyLoad: false,
		title: "网银批量核销",
		editFormPopupWindowWidth:700,
		editFormPopupWindowHeight:250,
		multiSelect : true,
		remoteOper: false,
		showPage: true,
		hiddenQueryArea : false,
		pageSize: 20,
		queryButtonColSpan: 6,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/jsp/ebank/fundebank/fund_ebank_data_list_hire.xml',
		params: {invalid:'否',ismayopemoney:true},
		showPager : true,//分页按钮是否显示
		tools: toolsArray,
			columns: [
			  		{type: 'indexcolumn'},
			  		{type: 'checkcolumn'},
			  		{field: 'id', header: 'id', visible: false},
			  		{field: 'ebdataid', header: '网银编号',queryConfig:{width:212}},
			  		{field: 'sn', header: '流水号',width:160,queryConfig:{width:212}},
			  		{field : 'custidname',header : '承租人名称',width:180,queryConfig : {width:212}},
			  		{field: 'clientname', header: '付款人',width:180,},
			  		{field: 'matchinfo', header: '成功匹配信息'},
			  		{field: 'factmoney', header: '到账金额',summary : true,dataType : "currency",queryConfig:{newLine:true,type:'float',range:true}},
			  		{field: 'nowithmoney', header: '非业务金额',summary : true,dataType : "currency"},
			  		{field : 'hadmoney',header : '已核销金额',summary : true,dataType : "currency",headerAlign : 'center'},
			  		{field: 'mayopemoney', header: '可核销金额',summary : true,dataType : "currency"},
			  		{field: 'factdate', header: '到账时间',type:'date',dateFormat:"yyyy-MM-dd hh:mm:ss",width:150},
			  		{field: 'ownbank', header: '本方银行',width:150},
			  		{field: 'ownaccnumber', header: '本方账号',width:150},
			  		{field: 'ownaccount', header: '本方账户',width:150},
			  		{field: 'clientbank', header: '对方银行',width:150,queryConfig:{width:212}},
			  		{field: 'clientaccnumber', header: '对方账号',width:150,queryConfig:{width:212}},
			  		{field: 'clientaccount', header: '对方账户',width:150},
			  		{field: 'uploaddate', header: '上传时间',type:'date',dateFormat:"yyyy-MM-dd hh:mm:ss",width:150}
			  	]
	});
});
//单笔租金回笼
tenwa.createTable({
	id: "singel_rent_income",
	renderTo: "id_table_container_single_income",
	width: '100%',
	height: '100%',
	lazyLoad: false,
	remoteOper: false,
	showPager: true,
	pageSize: 20,
	multiSelect : true,
	queryButtonColSpan: 2,
	tools: [{
		html: '确定核销',
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
	xmlFileName: '/eleasing/workflow/rent/rent_income/rent_plan_singleorpenalty.xml',
	columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'contractnumber', header: '业务合同编号',queryConfig:{}},
				{field: 'custname', header: '承租人名称',queryConfig:{}},
				{field: 'rentlist', header: '期次',queryConfig:{}},
				{field: 'status', header: '回笼状态', renderer: showRentStatus},
				{field: 'plandate', header: '计划日期',queryConfig:{
					newLine : true,
					type : 'date',
					range : true
				}},
				{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
				{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
				{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},
				{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : "currency"},
				{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
				{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"},
				{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : "currency"}
	]
});
//罚息回笼
tenwa.createTable({
	id: "penalty_income",
	renderTo: "id_table_container_penalty_income",
	width: '100%',
	height: '100%',
	lazyLoad: false,
	remoteOper: false,
	showPager: true,
	multiSelect : true,
	pageSize: 20,
	queryButtonColSpan: 2,
	tools: [{
		html: '确定核销',
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
	xmlFileName: '/eleasing/workflow/rent/rent_income/rent_plan_singleorpenalty.xml',
	columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'contractnumber', header: '业务合同编号',queryConfig:{}},
				{field: 'custname', header: '承租人名称',queryConfig:{}},
				{field: 'rentlist', header: '期次',queryConfig:{}},
				{field: 'status', header: '回笼状态', renderer: showRentStatus},
				{field: 'plandate', header: '计划日期',queryConfig:{
					newLine : true,
					type : 'date',
					range : true
				}},
				{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : "currency"},
				{field: 'penaltypayed', header: '已收罚息',type:'double',summary : true,dataType : "currency"},
				{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : "currency"}
	]
});
//租金回笼状态
function showRentStatus(e){
    var curRowData=e.record;
	if(curRowData.currentoverage == 0){//&& curRowData.penaltyoverage==0
		return "已回笼";
	}else if(curRowData.currentoverage == curRowData.rent){
		return "未回笼";
	}else{
		return "部分回笼";
	}
}
function refresh(){
	mini.get('fundebank_id').reload();
}
function showSingleIncomeWindow(miniTable){
	mini.get("id_table_window_single_income").show();
}
function showPenaltyIncomeWindow(miniTable){
	var selectedRows = miniTable.getSelecteds();
	if(selectedRows && selectedRows.length>1){
		mini.alert("请选择单笔网银进行罚息回笼!");
		return false;
	}
	var ebankid =selectedRows[0].id;
	var url = getRootPath()+"/leasing/ebank/fund_ebank_verification/fund_ebank_match_penalty.bi?ebid="+ebankid;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
function manualMatching(miniTable){
	var selectedRows = miniTable.getSelecteds();
	if(selectedRows && selectedRows.length>1){
		mini.alert("请选择单笔网银进行人工匹配!");
		return false;
	}
	var ebankid =selectedRows[0].id;
	var url = getRootPath()+"/leasing/ebank/fund_ebank_verification/fund_ebank_match.bi?ebid="+ebankid;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
</script>
</head>	
<body>
<div id="id_table_fundebank_id" style="height: 100%;"></div>
<div id="id_table_window_single_income" class="mini-window" title="选择网银" 
	style="width:1000px;height:600px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_table_container_single_income" style="width: 100%;height: 100%;"></div>
</div>
<div id="id_table_window_penalty_income" class="mini-window" title="选择网银" 
	style="width:1000px;height:600px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_table_container_penalty_income" style="width: 100%;height: 100%;"></div>
</div>
</body>
</html>

