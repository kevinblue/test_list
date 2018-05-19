<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资金开票数据回导</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript">
//状态下拉查询
var statusdata = [{name : '未回导',value : '未回导'},{name : '已回导',value : '已回导'},{name : '全部',value : ''}];
var hcstatusdata = [{name : '未红冲',value : '未红冲'},{name : '已红冲',value : '已红冲'},{name : '全部',value : ''}];

function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
     mini.get(tableid).reload();
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				//导入Excel配置
				importTargetClass:'com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo',
				importOrExPortCallBack:'checkFundInvoiceDownloadInfoImport',
				importDataIndex:'2',
				importHeaderIndex:'1',
				
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '资金开票数据回导',
				iconCls : 'icon-node',
				multiSelect : true,
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine:true,
				showPager:true,
				isRemoteStatistic: true,
				params:{
					backstatus:"未回导"
				},
				xmlFileName : '/eleasing/jsp/invoice_manage/fund_invoice/fund_invoice_import.xml',
				tools : ['importExcel',
				         /* '-',
				        
				  {
					html : '同步税控返回结果',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
				
						var operType = "同步税控";
						var operAction = "importFundInvoiceSKBack";
						
						if(0 == checkedRowDatas.length){
							alert("请选择要"+operType+"的记录！！");
							return;
						}
					   
					    var params = {};
					    var tempIdArr = [];
					    for(var i = 0;i<checkedRowDatas.length;i++){
							var checkedRowdata = checkedRowDatas[i];
							if("已回导"==checkedRowdata.backstatus){
								alert("合同号："+checkedRowdata.contractid+"已回导,不能再生成!!!" );
								return false;
							}
							tempIdArr.push(checkedRowdata.outno);
						}
					    params["outnos"] = tempIdArr.join(",");
					    
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
							data :params,
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
									mini.alert(resultJson.content);
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
				},
				*/
				'-',
				{
					html : '资金开票数据回导模板下载',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var fileTeplate=new fileTemplateConfig({
	                    	 templateno:'F-201411005',
	                    	 tableid:miniTable.config.id,
	                         modelname:miniTable.config.title,
	                         returntype:'file',
	                         parames:{
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
				   
				  /*  {field : 'contractid',header : '合同号',queryConfig : {}},
				   	{field : 'contractnumber',header : '业务合同号',queryConfig : {},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					}}, */
					{field : 'outno',header : '发票编号'},
					{field : 'contractputnumber',header : '投放编号',queryConfig : {}},
				   	{field : 'custname',header : '客户名称',allowSort:true,queryConfig : {}},
				   	{field : 'feetype',header : '费用类型',queryConfig : {
						type:'combobox',
						params : {
							pid:'FeeType',
							xmlFileName : 'combos/comboDict.xml'
						},
						readOnly:false,
						textField:'name',
						valueField:'name'
					}},
				   	{field : 'plandate',header : '收款日期',
				   		queryConfig : {
							newLine: true,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						}
				   	},
				   	{field : 'planmoney',header : '收款金额',dataType:"currency",align:'right',summary: true},
					{field : 'invoicetype',header : '发票类型'},
					{field : 'taxrate',header : '税率'},
					
				   	{field : 'invoiceno',header : '发票号码',queryConfig : {}},
				   	{field : 'invoicemoney',header : '含税总金额',dataType:"currency",align:'right',summary: true,
				   		formEditorConfig:{
				              vtype:'currency'
				        }
				   	},
				   	{field : 'invoicedate',header : '开票日期'},
				  	{field : 'taxmoney',header : '合计税额',dataType:"currency",align:'right',summary: true,
				   		formEditorConfig:{
				              vtype:'currency'
				        }
				   	}, 
				   	
			    	{field : 'taxbank',header : '开户行'},
				   	{field : 'taxacc',header : '开户账号'},
				   	{field : 'taxphone',header : '开票电话'},
				   	{field : 'taxregcode',header : '纳税人识别号'},
				   	{field : 'taxregadd',header : '开票地址'},
				   	{field : 'backstatus',header : '回导状态',
						queryConfig : 
				   	    {
							colspan : 1,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							newLine: true,
							//defaultValue:'1',
							data : statusdata
					   }	
					},
					{field : 'hcstatus',header : '红冲状态',
						queryConfig : 
				   	    {
							//width : 200,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							//defaultValue:'1',
							data : hcstatusdata
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