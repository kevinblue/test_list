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
var localMayMoney = [ {id:'',text:'全部'},{id :'and   vp.CURRENTINCOME=0 and vcp.income_penalty=0',text :'未回笼'},{id :'and   (vp.CURRENTINCOME>0 and vp.CURRENTINCOME!=vp.RENT )',text :'部份回笼'},{id :' and  (vp.RENT!=vp.CURRENTINCOME or  vcp.penalty!=0)',text :'未回笼或部份回笼'}, {id :' and ( vp.CURRENTOVERAGE=0 and vcp.penalty=0)',text :'已回笼'},{id :' and (vcp.penalty>0)',text :'罚息未回笼'}];
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
		xmlFileName: '/eleasing/workflow/rent/rent_income/rent_income_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'contract_put_number', header: '投放编号',queryConfig:{width: 165}},
			{field: 'projectname', header: '项目名称',queryConfig:{width: 165}},
			{field: 'rentlist', header: '计划期次'},
			{field: 'plandate', header: '收款日期',queryConfig:{type:'date',vtype:'date',range:true,format:'yyyy-MM-dd',width: 165}},
			{field: 'rent', header: '租金',summary:true,dataType:"currency"},
			{field: 'corpus', header: '本金',summary:true,dataType:"currency"},
			{field: 'interest', header: '利息',summary:true,dataType:"currency"},
			{field: 'planpenalty', header: '罚息',summary:true,dataType:"currency"},
			{field: 'curcorpusoverage', header: '剩余本金',summary:true,dataType:"currency"},
			{field: 'curinterestoverage', header: '剩余利息',summary:true,dataType:"currency"},
			{field: 'penalty', header: '剩余罚息',summary:true,dataType:"currency"},
			{field: 'custname', header: '承租人',queryConfig:{width: 165,newLine: true}},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field:'ismaymoney',header:'状态',visible:false,headerAlign:'center',
			    queryConfig:{ 
   			       readOnly:false,
   			           data:localMayMoney,
   			           type:'combobox',
   			   fieldVisible:true,
   			      textField:"text",
   			     valueField:"id",
   			          value:'and  (vp.RENT!=vp.CURRENTINCOME or  vcp.penalty!=0)',
   			           text:'未回笼或部份回笼'
	}}
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
			{field : 'custidname',header : '承租人名称',visible:true,queryConfig : {colspan:3}},
			{field: 'clientname', header: '付款人'},
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