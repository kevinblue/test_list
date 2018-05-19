<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金开票信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var statusdata = [ {name : '未申请',value : '未申请'},{name : '已退回',value : '已退回'},{name : '已申请',value : "已申请"},{name : '全部',value : ''},{name : '不开票',value : '不开票'}];
var trendsxml =  [{name : '提前',value : 'renttiqian'},{name : '实收',value : 'rentshishou'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'fund_invoice',
				renderTo: "id_fund_invoice",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'租金开票信息',
				iconCls:'icon-node',
				multiSelect:true,
				queryButtonColSpan:8,
				width:"100%",
				heigth:"100%",
				showPager:true,
				params:{
					status:"未申请','已退回"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/invoicePlan/rent_invoice_plan.xml',
				tools:[
					{
						html:'租金开票流程',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var rows = miniTable.getSelecteds();
							var tsdxml=mini.get("fund_invoice_queryArea_trendsxml").getValue();
							var params =[];
							if(rows.length>0){
								var ids="";
								var cid="";
								 for(var i=0;i<rows.length;i++){
									 var checkedRowdata = rows[i];
									   if(checkedRowdata.planmoney<=0){
										   mini.alert("计划金额小于0的不允许开票!!!" );
										   return false;
									    }
									   if("增值税专用发票"==checkedRowdata.rentinvoicetype){
											if(checkedRowdata.taxregcode==null || checkedRowdata.taxregcode==''){
												mini.alert("请维护纳税人信息后再生成开票清单!!!" );
												return false;
											}
										}
									   if(checkedRowdata.taxregcode!=checkedRowdata.taxregcode2){
											mini.alert("该纳税人识别号与法人客户信息里面不一致，请核对！！！" );
											return false;
										}
									  if("已申请"==checkedRowdata.status){
											mini.alert("合同号："+checkedRowdata.contractid+"已申请,不能再生成!!!" );
											return false;
										}
									  var data={};
									  cid=rows[0].cid;
									  data.id=checkedRowdata.id;
									  data.pid=checkedRowdata.pid;
									  data.type=checkedRowdata.plantypeid;
									  data.planmoney=checkedRowdata.planmoney;
									  data.incomemoney=checkedRowdata.incomemoney;
									  params.push(data);
									 if(i==rows.length-1){
										 ids+=rows[i].id;
									 }else{
										 ids+=rows[i].id+",";
									 }
								  }
								var datas=mini.encode(params);//租金实收的参数封装
								var attachmentParams = "id="+ids+"&tsdxml="+tsdxml+"&datas="+datas+"&cid="+cid;
				        	    startProcess("租金开票流程-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起租金开票流程！！！');
							}
						}
					}         
				],
				columns : [ 
						    {type : 'indexcolumn'},
							{type : 'checkcolumn'},  
							{field : 'id',header : 'id',visible : false},
							{field : 'cid',header : 'cid',visible :false},
							{field : 'contractid',header : '合同号',queryConfig : {}},
							{field : 'contractnumber',header : '业务合同号',queryConfig : {},renderer:function(e){
								return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
							}},
							{field : 'custname',header : '客户名称',allowSort:true,queryConfig : {}},
							{field : 'rentlist',header : '期数'},
							{field : 'plandate',header : '计划日期'},
							{field : 'planmoney',header : '计划金额',dataType:"currency",align:'right',summary: true},
							{field : 'incomedate',header : '实收日期',
								queryConfig : {
								newLine: true,
								type : 'date',
								vtype : 'date',//
								range : true,//是否是范围查询
								format : 'yyyy-MM-dd',
								showTime : true
							  }
							},
							{field : 'incomemoney',header : '实收金额',align:'right',summary: true},
							{field : 'plantype',header : '金额类型',queryConfig : {}},
							{field : 'rentinvoicetype',header : '发票类型'},
							{field : 'rentrate',header : '税率'},
							{field : 'taxregcode',header : '纳税人识别号'},
							{field : 'taxbank',header : '开户行及银行账号',width:250},
							{field : 'regaddress',header : '开票地址及电话',width:250},
							{field : 'status',header : '状态',
								queryConfig : 
						   	    {
									type : 'combobox',//表单域类型
									valueField : 'value',
									textField : 'name',
									allowInput : false,
									data : statusdata
							   }	
							},
							{field: 'leaseform', header:'租赁形式',
								queryConfig:{
									newLine: true,
									type:'combobox',
									params : {
										pid:'leas_form',
										xmlFileName : 'combos/comboDict.xml'
									},
									readOnly:false,
									textField:'name',
									valueField:'name'
							}},
							{field : 'trendsxml',visible:false,header : '提前/实收开票',
								queryConfig : 
						   	    {
									fieldVisible : true,
									colspan : 1,
									type : 'combobox',//表单域类型
									valueField : 'value',
									textField : 'name',
									value:'rentshishou',
									allowInput : false,
									data : trendsxml
							   }	
							}
						]
			})
		});
	});

</script>
</head>
<body>
	<div id="id_fund_invoice" style="height: 100%"></div>
</body>
</html>