<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发票接口导出</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '未导出',value : '未导出'},{name : '已导出',value : '已导出'},{name : '全部',value : ''}];

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
					status:"未导出"
				},
				isRemoteStatistic: true,
				xmlFileName : '/eleasing/jsp/invoice_manage/invoice_interface/invoice_interface_export.xml',
				tools : [
					{
						html : '退回',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							var operType = "退回";
							var operTitle = "开票确认";
							var operAction = "deleteInvoiceInfo";
							
							if(0 == checkedRowDatas.length){
								alert("请选择要"+operType+"的记录！！");
								return;
							}
						    var params = {};
						    var tempIdArr = [];
						    for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								
								if("已导出"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contract_num+"已导出,不能退回!!!" );
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
						html : '导出开票数据',
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
								if("已导出"==checkedRowdata.status){
									alert("合同号："+checkedRowdata.contract_num+"已导出,不能导出!!!" );
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
		                        	//importorexportcallback:'',
		                        	importorexportcallbackafter:'exportInvoiceAfter',
		                        	//isput:isput,
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
				   	{field : 'contract_num',header : '合同号',queryConfig : {}},
				   	{field : 'cust_name',header : '客户名称',allowSort:true,queryConfig : {}},
				   	{field : 'out_no',header : '发票编号'},
				   	{field : 'fee_type',header : '费用类型'},
					{field : 'invoice_date',header : '收款日期'},
					{field : 'invoicemoney',header : '开票金额',dataType:"currency",align:'right',summary: true},
					{field : 'invoice_type',header : '发票类型'},
					{field : 'taxregcode',header : '纳税人识别号'},
					{field : 'taxbank',header : '开户行'},
					{field : 'taxacc',header : '开户账号'},
					{field : 'taxtel',header : '开票电话'},
					{field : 'taxaddr',header : '开票地址'},
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
					}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>