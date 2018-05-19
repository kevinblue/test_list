<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金发票实收开票确认</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '已退回',value : '已退回'},{name : '已申请',value : '已申请'},{name : '已导出',value : '已导出'},{name : '全部',value : ''}];

function cretaefileByTempateCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
     mini.get(tableid).reload();
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '资金发票实收开票确认',
				iconCls : 'icon-node',
				multiSelect : true,
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 4,
				queryButtonNewLine:false,
				showPager:true,
				params:{
					status:"已申请"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/fund_invoice/invoiceCharge/fund_invoice_charge_confirm.xml',
				tools : [
					{
						html : '退回',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							
							var operType = "退回";
							var operTitle = "资金实收开票";
							var operAction = "backFundChargeInvoice";
							
							if(0 == checkedRowDatas.length){
								alert("请选择要"+operType+"的记录！！");
								return;
							}
						    var params = {};
						    var tempIdArr = [];
						    for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("已退回"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contractid+"已退回,不能再退回!!!" );
									return false;
								}
								if("已导出"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contractid+"已导出,不能退回!!!" );
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
					},'-',
					{
						html : '导出资金开票数据',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							if(0 == checkedRowDatas.length){
								alert("请选择要导出开票的记录！！");
								return;
							}
							var tempIdArr = [];
							for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("已退回"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contractid+"已退回,不能导出!!!" );
									return false;
								}
								if(Number(checkedRowdata.balanceexportmoney)==0){
									alert("合同号："+checkedRowdata.contractid+"已导出,不能导出!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								tempIdArr.push("'"+id+"'");
								//tempIdArr.push(id);
							}
							//var isput=window.confirm(("是否要导入税控开票？点击【确定】将导入税控机并下载excel，【取消】将直接下载excel!")) ;
							var isput=0;
							var fileTeplate=new fileTemplateConfig({
		                    	 templateno:'F-201411004',
		                    	 tableid:miniTable.config.id,
		                         modelname:miniTable.config.title,
		                         returntype:'listtonewpage',
		                         jscallbak :function(tableid){
		                        	 mini.get("table_id1").reload();
		                         },
		                         parames:{
		                        	importorexportcallback:'exportFundChargeInvoiceBefore',
		                        	importorexportcallbackafter:'exportFundInvoiceAfter',
		                        	isput:isput,
		                        	ids:tempIdArr.join(",")  
		                            //splitfield:'planmoney',
			                        //rangevalue:'100'
		                         }
		                         });
		                     fileTeplate.createFile();
						}
					}
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   //	{field : 'contractid',header : '合同号',queryConfig : {}},
				   	//{field : 'contractnumber',header : '业务合同号',queryConfig : {},renderer:function(e){
					//	return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					//}},
					{field : 'contractputnumber',header : '投放编号',queryConfig : {}},
					{field : 'taxrate',header : '税率'},
				   	{field : 'custname',header : '客户名称',allowSort:true,queryConfig : {}},
				   	{field : 'feetype',header : '费用类型',queryConfig : {
						type:'combobox',
						newLine: true,
						params : {
							pid:'FeeType',
							xmlFileName : 'combos/comboDict.xml'
						},
						readOnly:false,
						textField:'name',
						valueField:'value'
					}},
					{field : 'plandate',header : '计划收款日期'},
					{field : 'planmoney',header : '计划收款金额',dataType:"currency",align:'right',summary: true},
					{field : 'plist',header : '笔数',
						renderer : function(e){
							var paymentid = e.record.plist;
							return "第"+paymentid+"笔";
						},
					},
					{field : 'factdate',header : '实际收款日期',
						queryConfig : {
						type : 'date',
						vtype : 'date',//
						range : true,//是否是范围查询
						format : 'yyyy-MM-dd',
						showTime : true
					}
					},
					{field : 'factmoney',header : '实际收款金额',dataType:"currency",align:'right',summary: true},
					{field : 'balanceexportmoney',header : '剩余导出金额',dataType:"currency",align:'right',summary: true,
						queryConfig : 
				   		{
				   			width : 74,
				   			//newLine : true,
				   			type:'float',
				   			range:true
				   		}
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
							newLine: true,
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