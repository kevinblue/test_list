<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金发票计划开票</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
//状态下拉查询
var statusdata = [ {name : '未申请',value : '未申请'},{name : '已退回',value : '已退回'},{name : '已申请',value : "已申请"},{name : '全部',value : ''}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '资金发票计划开票',
				iconCls : 'icon-node',
				multiSelect : true,
				
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 4,
				queryButtonNewLine: false,
				showPager:true,
				params:{
					status:"未申请','已退回"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/fund_invoice/invoicePlan/fund_invoice_plan.xml',
				tools : [
					{
						html : '生成开票清单',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							var operType = "生成";
							var operTitle = "资金计划开票";
							var operAction = "submitFundPlanInvoice";
							
							if(0 == checkedRowDatas.length){
								alert("请选择要"+operType+"的记录！！");
								return;
							}
						    var params = {};
						    var tempIdArr = [];
						    for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								 if("已申请"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contractid+"已申请,不能再生成!!!" );
									return false;
								} 
								var id = checkedRowdata.id;
								tempIdArr.push(id);
							}
							params["ids"] = tempIdArr.join(",");
								
							if(!window.confirm(("确定"+operType+"当前"+checkedRowDatas.length+"条记录么？"))) return;
							var url=getRootPath()+"/acl/"+operAction+".acl";
							//遮罩
							mini.mask({
								el : document.body,
								cls : 'mini-mask-loading',
								html : '数据操作中 请稍等...'
							});
							jQuery.ajax({
								url : url,
								type : 'POST',
								timeout : 30 * 1000,
								data : params,
								dataType : 'json',
								error : function(res, errInfo, e) {
									mini.unmask(document.body);
								},
								success : function(resultJson) {
									var returnState = resultJson.returnType;
									switch (returnState) {
									case "SUCCESS": {
										miniTable.reload();
										mini.unmask(document.body);
										alert(operType+operTitle + " 成功！");
										break;
									}
									case "FAILURE": {
										miniTable.reload();
										mini.unmask(document.body);
										mini.alert("生成失败！"+resultJson.content);
										break;
									}
									}
								}
							});
						}
					},'-',
					{
						html : '删除开票清单',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
				
							var operType = "删除";
							var operTitle = "资金开票清单";
							var operAction = "deleteFundInvoiceOrReceipt";
							
							if(0 == checkedRowDatas.length){
								alert("请选择要"+operType+"的记录！！");
								return;
							}
						    var params = {};
						    var tempIdArr = [];
						    for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
						     if("已申请"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contractid+"已申请,不能删除!!!");
									return false;
								} 
								if(checkedRowdata.fundinvoiceid){
								  var id = checkedRowdata.fundinvoiceid;
								  tempIdArr.push(id);
								}else{
									alert("合同号："+checkedRowdata.contractid+"没有开票记录，不需要删除！！！");
									return false;
								}
							}
							params["ids"] = tempIdArr.join(",");
								
							if(!window.confirm(("确定"+operType+"当前"+checkedRowDatas.length+"条记录么？"))) return;
							var url=getRootPath()+"/acl/"+operAction+".acl";
							//遮罩
							mini.mask({
								el : document.body,
								cls : 'mini-mask-loading',
								html : '数据操作中 请稍等...'
							});
							jQuery.ajax({
								url : url,
								type : 'POST',
								timeout : 30 * 1000,
								data : params,
								dataType : 'json',
								error : function(res, errInfo, e) {
									mini.unmask(document.body);
								},
								success : function(resultJson) {
									var returnState = resultJson.returnType;
									switch (returnState) {
									case "SUCCESS": {
										miniTable.reload();
										mini.unmask(document.body);
										mini.alert(operType+operTitle + " 成功！");
										break;
									}
									case "FAILURE": {
										miniTable.reload();
										mini.unmask(document.body);
										mini.alert("生成失败！"+resultJson.content);
										break;
									}
									}
								}
							});
						}
					}
				],
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},  
					{field : 'id',header : 'id',visible : false},
					//除去合同号和业务合同号
					//{field : 'contractid',header : '合同号',queryConfig : {}},
					//{field : 'contractnumber',header : '业务合同号',queryConfig : {},renderer:function(e){
						//return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\",\""+e.record.pid+"\")'>" + e.record.contractnumber + "</a>";
					//}},
					{field : 'contractputnumber',header : '投放编号',queryConfig : {}},
					{field : 'custname',header : '客户名称',queryConfig : {}},
					{field : 'plandate',header : '计划收款日期',
						queryConfig : {
						width : 125,
						newLine: true,
						type : 'date',
						vtype : 'date',//
						range : true,//是否是范围查询
						format : 'yyyy-MM-dd',
						showTime : true
					}
					},
					{field : 'planmoney',header : '计划收款金额',dataType:"currency",align:'right',summary: true},
					{field : 'feetype',header : '费用类型',queryConfig : {
						type:'combobox',
						params : {
							pid:'FeeType',
							xmlFileName : 'combos/comboDict.xml'
						},
						readOnly:false,
						textField:'name',
						valueField:'value'
					}},
					{field : 'paymentid',header : '笔数',
						renderer : function(e){
							var paymentid = e.record.paymentid;
							return "第"+paymentid+"笔";
						},
					},
					{field : 'invoicetype',header : '发票类型'},
					{field : 'taxregcode',header : '纳税人识别号'},
					{field : 'taxbank',header : '开户行'},
					{field : 'taxacc',header : '开户账号'},
					{field : 'phone',header : '开票电话'},
					{field : 'regaddress',header : '开票地址'},
					{field : 'status',header : '状态',
						queryConfig : 
				   	    {
							//width : 200,
							colspan : 1,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							//defaultValue:'1',
							data : statusdata
					   }	
					},
					{field: 'leaseform', header:'租赁形式',
						queryConfig:{
							type:'combobox',
							newLine:true,
							params : {
								pid:'leas_form',
								xmlFileName : 'combos/comboDict.xml'
							},
							readOnly:false,
							textField:'name',
							valueField:'value'

					}}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>