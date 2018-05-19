<%@page import="com.tenwa.leasing.utils.WorkflowUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理争议申请</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_factoring_controversy',
				renderTo : "id_table_factoring_controversy_list",
				width : globalClientWidth,
				height : globalClientHeight,
				iconCls : 'icon-node',
				hiddenQueryArea : false,
				multiSelect : true,
				queryButtonColSpan : 6,
				queryButtonNewLine : false,
				editFormPopupWindowWidth : 800,
				editFormPopupWindowHeight : 350,
				title : '保理争议申请',
				remoteOper : true,
				pageSize : 20,
				showPager : true,
				lazyLoad : false,
				loadMode : 'ajax',
				frozenStartColumn : 0,
				frozenEndColumn : 2,
				//新增修改弹出窗口后点击确认时调用该方法返回AJAX
				validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
					//如果是新增就获取流水号 
					if (editFormItemOperFlag == 'add') {
						var num=0;
						$.ajax({
							url : getRootPath()+ "/acl/getApplicationRunningWater.acl",
							type : "post",
							cache : false,
							data : {},
							async : false,
							success : function(date) {
								mini.getbyName("applicationnumber").setValue(date);
								num++;
							}
						});
						if(num==1){
							return true;
						}
						return false;
					} 
					if (editFormItemOperFlag == 'edit') {
						return true;
					} 
				},
				entityClassName : 'com.tenwa.leasing.entity.factoring.FactoringControversy',
				xmlFileName:'/eleasing/workflow/factoring/factoring_controversy/factoring_controversy_list.xml',
				tools: ['add', '-', 'edit', '-','remove','-','exportExcel','-',
				       {	html : '生成保理争议通知单',
							plain : true,
							iconCls : 'icon-addfolder',
							handler : function(miniTable, buttonText) {
								var row = miniTable.getSelected();
								if(row){
									var params = {};
									params['label.applicationnumber'] = row.applicationnumber;
									params['label.purchasername'] = row.purchasername;
									params['label.invoicecode'] = row.invoicecodename;
								  	var fileTeplate=new fileTemplateConfig({						
										templateno : 'F-201612007',
										tableid:miniTable.config.id,
										modelname:miniTable.config.title,
										returntype:'listtonewpage',
										parames :params
									});
									fileTeplate.createFile();
								}else{
									mini.alert('请选择需要生成保理争议的选项！！！');
								}
						  	}
						}
				],
				columns:[ 
				    {
				    	type:'indexcolumn'
				    },
				    {
				    	type:'checkcolumn'
				    },
				    {	
				    	field:'id',
				    	visible:false,
				    },
				    {
				    	field:'applicationnumber',
				    	header:'争议申请编号',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
							width : 200
	                   	},
				    	formEditorConfig : {
				    		readOnly:true,
				    		labelWidth:120,
				    		newLine:true,
							type:'text',
							required:true,
							fieldVisible:true
						}
				    },
				    {
				    	//contractidname在页面显示,对应着页面查询的XML
				    	field:'contractidname',
				    	header:'合同编号',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	formEditorConfig : {
				    		//弹出框不显示
				    		fieldVisible : false
						}
				    },
				    {
				    	//contractid在页面不显示,对应着页面查询的XML
				    	field:'contractid',
				    	header:'合同编号',
				    	headerAlign : 'center',
				    	visible : false,
				    	formEditorConfig : {
							type : 'queryinput',
							required : true,
							//弹出框显示值为contractid,实际存储为id值r
							textField : 'contractid',
							valueField : 'idvalue',
							allowInput : false,
							fieldVisible : true,
							onSelect : function($me,queryInput,rowData) {
								mini.getbyName("contractnumber").setValue(rowData.contractnumber);
								mini.getbyName("custname").setValue(rowData.custname);
								mini.getbyName("projectname").setValue(rowData.projectname);
								var rowDatas = queryInput.value;
				                var clientBankQueryInput = getMiniuiExtObject("table_factoring_controversy_editFormPopupWindow_form_invoicecode");
				                var conidparams=clientBankQueryInput.params ;
			                    conidparams.contract_id= rowDatas;
			                    mini.getbyName("invoicecode").setText("");
			                    mini.getbyName("invoicecode").setValue("");
			                    mini.getbyName("invoicecodename").setValue("");
			                    mini.getbyName("purchasername").setValue("");
							},
							params : {
								xmlFileName : '/eleasing/workflow/factoring/factoring_controversy/factoring_controversy_contractId.xml'
							}
						}
				    },
				    {
				    	field:'contractnumber',
				    	header:'业务合同编号',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	visible : true,
				    	queryConfig:{
							width : 200
	                   	},
				    	formEditorConfig : {
				    		labelWidth:120,
							type : 'text',
							readonly:true,
							newLine : true,
							required : true,
							fieldVisible : true
						}
				    },
				    {
				    	field:'custname',
				    	header:'客户名称',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
							width : 200,
							newLine : false
	                   	},
				    	formEditorConfig : {
							type : 'text',
							readonly:true,
							required : true,
							fieldVisible : true
						}
				    },
				    {
				    	field:'projectname',
				    	header:'项目名称',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
				    		newLine : true,
							width : 200
	                   	},
				    	formEditorConfig : {
							type : 'text',
							readonly:true,
							newLine : true,
							required : true,
							fieldVisible : true
						}
				    },
				    {
				    	field:'invoicecodename',
				    	header:'发票号',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
							width : 200
	                   	},
				    	formEditorConfig : {
				    		fieldVisible : false
						}
				    },
				     {
				    	field:'invoicecode',
				    	header:'发票号',
				    	headerAlign : 'center',
				    	allowSort:false,
				    	visible : false,
				    	formEditorConfig : {
							type : 'queryinput',
							required : true,
							onvaluechanged:'clearSonInputData',
							textField : 'invoicecode',
							valueField : '',
							allowInput : false,
							fieldVisible : true,
							onSelect : function($me,queryInput,rowData) {
								mini.getbyName("purchasername").setValue(rowData.purchasername);
								var clientBankQueryInput = getMiniuiExtObject("table_factoring_controversy_editFormPopupWindow_form_invoicecode");
							},
							params : {
								xmlFileName : '/eleasing/workflow/factoring/factoring_controversy/factoring_controversy_invoicecode.xml',
								contract_id: 'xxx'
							}
						}
				    }, 
				    {
				    	field:'purchasername',
				    	header:'买方名称',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
							width : 200,
							newLine : false
	                   	},
				    	formEditorConfig : {
							type : 'text',
							readonly:true,
							required : true,
							newLine : true,
							fieldVisible : true
						}
				    },
				    {
				    	field:'createdate',
				    	header:'争议申请时间',
				    	headerAlign : 'center',
				    	allowSort:true,
				    	dateFormat:'yyyy-MM-dd',
				    	queryConfig : {
				    		newLine : true,
							width : 100,
							type : 'date',
							range : true,
							format : 'yyyy-MM-dd'
						},
				    	formEditorConfig : {
				    		labelWidth:120,
							type : 'date',
							readonly:true,
							defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd"),
							format:'yyyy-MM-dd',
							required : true,
							fieldVisible : true
						}
				    },
				    {
				    	field:'explaination',
				    	header:'争议申请说明',
				    	width:200,
				    	headerAlign : 'center',
				    	allowSort:true,
				    	queryConfig:{
							width : 200
	                   	},
				    	formEditorConfig : {
				    		labelWidth:120,
							type:'textarea',
							colspan: 4,	
				            width:"100%",
							height:70,
							newLine : true,
							required : true,
							allowInput : true,
							required : true,
							fieldVisible : true
						}
				    }
				]
			});
		});
	});
</script>
<script type="text/javascript">
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_factoring_controversy_list"></div>
	</div>
</body>
</html> 