<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金支付通知书</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				addRemoteOperUrl:getRootPath()+"/acl/addTaxRentHcInfo.acl",
				remoteOper:true,
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '租金支付通知书',
				iconCls : 'icon-node',
				multiSelect : false,
				queryButtonColSpan : 6,
				queryButtonNewLine: true,
				showPager:true,
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/workflow/rent/rent_payment_notice.xml',
				tools : [{
					html : '导出租金支付通知书',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var row = miniTable.getSelecteds();	
						var params = {};
						if(row.length==0){
							 mini.alert('请选择要导出租金支付通知书的记录！！');
							 
						 }else if(row.length==1){
							 var rows=miniTable.getSelected();
								
								/*params['label.custname'] = mini.get("cust_name").getValue();
								params['label.invoicecode'] = rows.invoicecode;
								params['label.factoringtype'] =mini.get("factoringtype").getText();
								params['label.contractnumber'] =mini.get("contract_number").getValue();
								*/
								var transferdate =rows.plan_date;	
								var startdate =rows.start_date;
								params['date.iyear']=startdate.substring(0,4);//3.年
								params['date.imonth']=startdate.substring(5,7);//4.月
								params['date.iday']=startdate.substring(8);//5.日
								
								var nowdate=new Date().format("yyyy-mm-dd"); 
								params['date.tyear']=nowdate.substring(0,4);//3.年
								params['date.tmonth']=parseInt((new Date).getMonth().toString()) + 1;
								params['date.tday']=nowdate.substring(8);//5.日
								params['lebal.custname'] = rows.cust_name;
								params['lebal.number'] = rows.contract_number;
								params['lebal.rentlist'] = rows.rent_list;
								params['lebal.rent'] = rows.rent;
								params['lebal.plandate'] = rows.plan_date;
								
								
									var fileTeplate=new fileTemplateConfig({						
										templateno : 'F-201701006',
										tableid:miniTable.config.id,
										modelname:miniTable.config.title,
										returntype:'listtonewpage',
										parames :params
									  });
									   fileTeplate.createFile(); 
							 
						 }
					}
						
				}],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'contractnumber',header : '业务合同号',queryConfig : {},width: 200,renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.id+"\")'>" + e.record.contract_number + "</a>";
					}},
				   	{field : 'cust_name',header : '客户名称',width: 130,queryConfig : {}},
				   	{field : 'rent_list',header : '期次',width: 70,align:'center'},
					{field : 'start_date',header : '合同开始日期',width: 80,visible:false},
				   	{field : 'plan_date',header : '计划日期',width: 80,queryConfig:{type:'date',range:true}},
				   	{field : 'rent',header : '计划金额',width: 80,dataType: 'currency',summary:true},
				   	{field : 'proj_manage',header : '项目经理',width: 80,align:'center',queryConfig : {newLine: true}},
				   	{field : 'proj_dept',header : '组别',width: 100,queryConfig:{}},
				   	{field : 'company_address',header : '经营地址',width: 160},
				   	{field : 'main_person',header : '主联系人',width: 100},
				   	{field : 'mobile_number',header : '手机号',width: 140},
				   	{field : 'export_state',header : '导出状态',width: 80,queryConfig:{
				   		type:'combobox',
				   		showNullItem: true,
				   		data: '[{name:"已导出",value:" RPNR.ID IS NOT NULL"},{name:"未导出",value:" RPNR.ID IS NULL"}]',
				   		textField:"name",
				   		valueField:"value"
				   	}},
				   	{field : 'export_times',header : '导出次数',width: 80}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>

<script>
function showContractDetail(cid){
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}
</script>

