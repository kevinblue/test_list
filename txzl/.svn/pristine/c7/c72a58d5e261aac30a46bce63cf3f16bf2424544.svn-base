<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发票开票回导</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<body>
<script type="text/javascript">
var statusdata = [{name : '未回导',value : '未回导'},{name : '已回导',value : '已回导'},{name : '全部',value : ''}];
var hcstatusdata = [{name : '未红冲',value : '未红冲'},{name : '已红冲',value : '已红冲'},{name : '全部',value : ''}];

function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 mini.alert(info.message);
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
     mini.get(tableid).reload();
}

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				//导入Excel配置
				//importTargetClass:'com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo',
				//importOrExPortCallBack:'checkRentInvoiceDownloadInfoImport',
				importDataIndex:'2',
				importHeaderIndex:'1',
				editRemoteOperUrl:getRootPath()+ "/eleasing/updateFundRentInvoice.acl",
				id : 'invoice_import',
				width : globalClientWidth,
				height : globalClientHeight,
				//title : '<spring:message code="RentalInvoiceResultImportBack" text="发票开票回导"/>',
				title : "发票数据导出",
				iconCls : 'icon-node',
				remoteOper : true,
				multiSelect : true,
				lazyLoad:false,
				validateForm: function(miniTable, miniForm){
					var rate =getMiniEditFormField("invoice_import.taxratename").getValue();
					if(rate!="6%"&&rate!="17%"){
						mini.alert("请填写正确的税率！");
						return false;	
					}
					return true;
				},
				//hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 6,
				queryButtonNewLine: false,
				showPager:true,
				params:{
					backstatus:"未回导"
				},
				isRemoteStatistic: true,
			//	validateForm:editInvoiceStatus,
				operValidate:beforeEdit,
				//xmlFileName : '/eleasing/jsp/invoice_manage/rent_invoice/fund_rent_invoice_import.xml',
				xmlFileName:'eleasing/jsp/invoice_manage/invoice_interface/invoice_interface_import.xml',
				tools : [{
					html : '导入Excle',
					plain : true,
					iconCls : 'icon-importExcel',
					handler : function(miniTable, buttonText) {
						//mini.alert(window.globalWebRoot);
						//mini.alert(getRootPath()+"根目录");
						var config = {};
					//	config.importExcelUrl = window.globalWebRoot + '/eleasing/updateInvoiceImportExcel.action';					
						config.importExcelUrl = window.globalWebRoot + '/eleasing/updateInvoiceImportExcel.action';
						config.title = '发票回导';
						config.id = 'invoice_import';
						seajs.use("js/apcomponent/aptablebase/aptablebase.js",
								function(ApTableBase) {									
									ApTableBase.importExcel(miniTable, [], config);
								});					
						miniTable.reload();
					}
				},'-','edit','-',
				    {
						html : '发票数据导出',
						plain : true,
						iconCls : 'icon-save',
						handler : exportInvoiceData
			        },'-',
				         {
					html : '<spring:message code="D5700C4B-60BB-45AC-AA30-FE1E56034E01" text="发票回导模板下载"/>',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var fileTeplate=new fileTemplateConfig({
	                    	 templateno:'F-201706009',
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
				//indexcolumn,outno,projname,invoicedate,invoiceno,invoicecode,feetype,invoicemoney,rentlist,rentinvoicetype,taxrate,taxmoney
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'cid',header : 'cid',visible : false},					
				   	{field : 'outno',header : '单据号',queryConfig : {},formEditorConfig:{fieldVisible:true,readOnly:true}	},
				   	{field : 'projname',header : '项目名称',queryConfig : {}},
				   	{field : 'contractnumber',header : '<spring:message code="ContractLine" text="业务合同编号"/>',queryConfig : {},renderer:function(e){
						return "<a href='javascript:void(0);' onclick='showContractDetail(\""+e.record.cid+"\")'>" + e.record.contractnumber + "</a>";
					},formEditorConfig:{fieldVisible:false}},
				   	{field : 'custname',header : '<spring:message code="CustomerName" text="客户名称"/>',allowSort:true,queryConfig : {newLine:true},formEditorConfig:{fieldVisible:false}	},
				   	{field : 'rentlist',header : '期次',allowSort:true,formEditorConfig:{fieldVisible:false}	},
				   	{field : 'plandate',header : '<spring:message code="ScheduledPaymentDate" text="计划收款日期"/>',
				   		queryConfig : {
				   			colspan : 1,
				   			newLine: false,
							width : 100,
							type : 'date',
							vtype : 'date',//
							range : true,//是否是范围查询
							format : 'yyyy-MM-dd',
							showTime : true
						},formEditorConfig:{fieldVisible:false}	
				   	},
				   	{field : 'planmoney',header : '<spring:message code="PlanMoney" text="计划收款金额"/>',dataType:"currency",align:'right',summary: true,formEditorConfig:{fieldVisible:false}	},
				   	{field : 'invoicemoney',header : '本次开票金额',dataType:"currency",align:'right',summary: true,formEditorConfig:{fieldVisible:true,readOnly:true,newLine:true}	},
				   	{field : 'feetype',header : '<spring:message code="FeeType" text="费用类型"/>',formEditorConfig:{fieldVisible:false}/* ,queryConfig : {
						type:'combobox',
						
						params : {
							pid:'FeeType',
							xmlFileName : 'combos/comboDict.xml'
						},
						readOnly:false,
						textField:'name',
						valueField:'value'
					} */},
				   	{field : 'rentinvoicetype',header : '<spring:message code="InvoiceType" text="发票类型"/>',formEditorConfig:{fieldVisible:false}
				   	,queryConfig:{
				   		colspan : 1,
						type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						allowInput : false,
						//defaultValue:'增值税专用发票',
						data : [{'name':'增值税普通发票',value:'增值税普通发票'},{'name':'增值税专用发票',value:'增值税专用发票'},,{name : '全部',value : ''}]
				   	}},
				   	{field : 'invoiceno',header : '发票号码',formEditorConfig:{newLine:false,fieldVisible:true,required:true}},
				   	{field : 'invoicecode',header : '发票代码',formEditorConfig:{newLine:true,fieldVisible:true,required:true}},
				   	{field : 'taxrate',header : '税率',visible:false},
					{field : 'taxratename',header : '税率',formEditorConfig:{newLine:false,fieldVisible:true,required:true}},
				   	{field : 'taxmoney',header : '税额',formEditorConfig:{newLine:true,fieldVisible:true,required:true}},
				   	{field : 'taxregcode',header : '<spring:message code="TaxRegCode" text="纳税人识别号"/>',formEditorConfig:{fieldVisible:false}},
				   	{field : 'taxbank',header : '<spring:message code="TaxBank" text="开户行"/>',formEditorConfig:{fieldVisible:false}},
				   	{field : 'taxacc',header : '<spring:message code="TaxAccountNumber" text="开户账号"/>',formEditorConfig:{fieldVisible:false}},
				   	{field : 'phone',header : '<spring:message code="BillingPhone" text="开票电话"/>',formEditorConfig:{fieldVisible:false}},
				   	{field : 'regaddress',header : '<spring:message code="BillingAddress" text="开票地址"/>',formEditorConfig:{fieldVisible:false}},
				   	{field : 'invoicedate',header : '开票日期',formEditorConfig:{
				   		newLine:false,
				   		fieldVisible:true,
				   		required:true,
				   		format : 'yyyy-MM-dd',
				   		type : 'date',}},
				   	{field : 'hcstatus',header : '红冲状态',formEditorConfig:{
					   		fieldVisible:false,
					   		//type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							data:hcstatusdata
					  }},
				   	{field : 'cancelstatus',header : '是否作废',formEditorConfig:{fieldVisible:false}	},
				   	{field : 'backstatus',header : '发票回导状态',formEditorConfig:{
				   		fieldVisible:true,
				   		newLine:true,
				   		//type : 'combobox',//表单域类型
						valueField : 'value',
						textField : 'name',
						readOnly:true,
						data:[{name : '未回导',value : '未回导'},{name : '已回导',value : '已回导'}]
				   		},
				   		queryConfig:{
					   		colspan : 1,
							type : 'combobox',//表单域类型
							newLine:true,
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							//defaultValue:'未回导',
							data :statusdata
					   	}}
				   		
				]
			});
		});
	});
	function  beforeEdit(miniTable,miniForm, operFlag){
		var rows = miniTable.getSelecteds();
		var  row=rows[0];
		if(rows.length>1){
			mini.alert("只能选择单条数据进行修改！");
			return false;
		} 
		if("已回导"==row.backstatus){
			mini.alert("已回导，不能修改!!!" );
			return false;
		}
		return true;
	}
	function exportInvoiceData(miniTable, buttonText) {							
		 var params={};
		// initCreateWordData(params)
	     var invoicedata=miniTable.getSelecteds();	
	 	params["templateid"] = '8a8980895ca5729a015ca599d3fb0092';							
		params['custcreatetemplateno'] = 'F-201706010';
	     params["invoice_im_list"]=mini.encode(invoicedata);

		var fileTeplate=new fileTemplateConfig({
			templateno:'F-201706010',
			tableid:miniTable.config.id,
			modelname:miniTable.config.title,
			url : '/leasing/template/CreateFileByTemplate.action',
			returntype:'listtonewpage',
			jscallbak :function(tableid){
				mini.get("invoice_import").reload();
			},
			parames: params
		});
		fileTeplate.createFile();
	}
	
	function cancelRedInvoice(miniTable, buttonText) {
		var checkedRowDatas = miniTable.getSelecteds();
		var operType = "作废红冲发票";
		var operAction = "cancelRedInvoice";
		
		if(0 == checkedRowDatas.length){
			mini.alert("<spring:message code="5CAB6C00-12EC-4F1E-8F41-A43D6E7A3527" text="请选中要操作的数据！  "/>");
			return;
		}
	    var params = {};
	    var tempIdArr = [];
	    for(var i = 0;i<checkedRowDatas.length;i++){
			var checkedRowdata = checkedRowDatas[i];
			if("已红冲"!=checkedRowdata.hcstatus){
				mini.alert("只能作废红冲发票！" );
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
					mini.alert(resultJson.content);
					break;
				}
				}
			}
		});
	 }
/* 	function initCreateWordData(params) {
		var contract_equip=mini.get("contract_equip").getData();
		params["contract_equip_list"]=mini.encode(contract_equip);
	} */
</script>
</head>


</body>
</html>
<script>
function showContractDetail(cid){
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}
</script>