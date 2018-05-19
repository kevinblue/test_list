<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>借款计划还款</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var bid = "${param.bid}";
var billid = "${param.billid}";

jQuery(function() {
	 var loanid ='${param.loanid}';
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'loan_rent_plan',
				renderTo:'id_table_loan_rent_plan',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'',
				iconCls:'icon-node',
				importTargetClass : 'com.tenwa.leasing.entity.finacial.LoanRentPlan',
				importDataIndex : '2',
				importHeaderIndex : '1',
				importLoanid :loanid,
				importOrExPortCallBack:'SetRentloanId',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,	
  				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.finacial.LoanRentPlan',
				xmlFileName:'/eleasing/jsp/finacial/loan_rent_plan/loan_rent_plan_list.xml',
  				params:{loanid:loanid},				
				tools:['add','-','edit','-','remove','-','exportExcel','-','importExcel'],
				afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
					//alert(mini.getbyName("feename").getValue());
					mini.getbyName("corpus").getValue();
				},
				 confirmRemoveCallBack:function(miniTable, selectedRowDatas){						
					  
				 
				return true;
			},
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'loancontractid',header:'借款合同号',visible:true,allowSort:true,formEditorConfig:{
              			type:'text',
              			defaultValue:'${param.loancontractid}',
              			fieldVisible:true,
              			readOnly:true
              		}},
				   	{field:'loanid',header:'借款合同号',visible:false,allowSort:true,formEditorConfig:{
				   		required:true,
				   		fieldVisible:false,
				   		defaultValue:'${param.loanid}',
              			type:'text'
              		}},
              	
              		 {field:'planlist',header:'摘要',formEditorConfig:{
				   		required:false,
				   		newLine:false,
				   		type:'text'
				   	}}, 
				   	{field:'plandate',header:'计划还款日期',formEditorConfig:{
				   		required:true,
				   		newLine:true,
				   		type:'date'
				   	}}, 
				   	
				   	{field:'corpus',header:'计划还款金额（元）',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,vtype:'float',
				   		newLine:false,
				   		fieldVisible:true
				   	}},
				   /* 	{field:'corpus',header:'应支付本金',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,vtype:'float',
				   		dataType:"currency",
				   		newLine:true,
				   		fieldVisible:true
				   	}}, */
				   /* 	{field:'interest',header:'应支付利息',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,		vtype:'float',
				   		fieldVisible:true
				   	}}, */
				   	{field:'currencyname',header:'币种',visible:true,formEditorConfig:{
				   		fieldVisible:false,
			            fillProperty:'name',
			            defaultValue:'人民币',
			            entityClassName:'com.tenwa.business.entity.DictionaryData',
			            fillFromFieldName : 'currency',//关联的列
			            entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段
				   	}},
				   	{field:'currency',header:'币种',visible:false,formEditorConfig:{
				   		type:'combobox',
				   		fieldVisible:true,
				   		newLine:true,
				   		required:true,
				   		defaultValue:'currency_type1',
				   		textField: 'name',
						valueField: 'value',
						params:{pid: 'currency_type',xmlFileName: '/combos/comboDict.xml'}
				   	}},
				 	
				   	{field:'meno',header:'备注',formEditorConfig:{
				   		required:false,
				   		newLine:true,
				   		colspan:'3',
				   		type:'textarea',
				   		width:500
				   	}}
				   	
				]
			});
		});
	});
</script>
</head>
<body>
<div id="id_table_loan_rent_plan"></div>
</body>
</html>