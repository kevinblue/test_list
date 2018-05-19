<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>付息</title>
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
				id:'loan_rent_hire_interest',
				renderTo:'id_table_loan_rent_hire_interest',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'',
				iconCls:'icon-node',
				multiSelect:false,
				importTargetClass : 'com.tenwa.leasing.entity.finacial.LoanRentIncomeToInterest',
				importDataIndex : '2',
				importHeaderIndex : '1',
				importLoanid :loanid,
				multiSelect:true,
				importOrExPortCallBack:'SetIIloanId',
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,	
  				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.finacial.LoanRentIncomeToInterest',
				xmlFileName:'/eleasing/jsp/finacial/loan_rent_plan/loan_rent_income_interest_list.xml',
  				params:{loanid:'${param.loanid}'},
				tools:['edit','-','remove','-','importExcel','-','exportExcel'],
				afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
					//alert(mini.getbyName("feename").getValue());
					//mini.getbyName("planid").setText(mini.getbyName("planlist").getValue());
					/* mini.getbyName("loanid").setText('${param.loanid}');
					mini.getbyName("loancontractid").setText('${param.loancontractid}'); */
					 if(operFlag=='edit' ){
						 var row = miniTable.getSelected();	
						 var newstr=row.sjinterestrate.replace(/%/, ""); 
						 //(parseFloat(newstr)/100).toFixed(4)+""
						 mini.getbyName("sjinterestrate").setValue((Number(newstr)/100).toFixed(4)+"");
					      	 return true;
					 }else{
						 
						 return true;
					 }
				},
				/* submitReturnCallBack(miniTable, editFormItemOperFlag, operText, resultJson){
					
					 mini.get("loan_rent_plan_interest").reload();
					 mini.get("loan_rent_hire_interest").reload();
					 mini.unmask(document.body);									
				},  */
				validateForm(miniTable, miniForm, editFormItemOperFlag, addIndex){
					 var interestrate=mini.getbyName("sjinterestrate").getValue();
						  if(isNaN(interestrate)){
							  mini.alert("利率请输入数字！");
							  return false;
						  }
				      	 return true;
				},
				/* validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
					mini.get("loan_rent_plan_interest").reload();
					 mini.get("loan_rent_hire_interest").reload();
					 mini.unmask(document.body);		
					}, */
				columns:[ 
				    {type:'indexcolumn'},
				    {type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'loancontractid',header:'借款合同号1',visible:true,allowSort:true,formEditorConfig:{
              			type:'text',
              			defaultValue:'${param.loancontractid}',
              			fieldVisible:true,
              			readOnly:true
              		}},
				   	{field:'loanid',header:'借款合同号2',visible:false,allowSort:true,formEditorConfig:{
				   		fieldVisible:false,
				   		//defaultValue:'${param.loancontractid}',
				   		defaultValue:'${param.loanid}',
              			type:'text'
              		}}, 
              	  	{field:'currency',header:'币种',visible:false,formEditorConfig:{
				   		type:'combobox',
				   		fieldVisible:true,
				   		newLine:false,
				   		readOnly:false,
				   		required:true,
				   		defaultValue:'currency_type1',
				   		textField: 'name',
						valueField: 'value',
						params:{pid: 'currency_type',xmlFileName: '/combos/comboDict.xml'}
				   	}},
				   /* 	{field:'planlist',header:'摘要',formEditorConfig:{
				   		required:false,
				   		newLine:false,
				        fieldVisible:true,
				   		type:'text'
				   		 ,
				   		 fillProperty:'name',
			            entityClassName:'com.tenwa.leasing.entity.finacial.LoanRentPlanToInterest',
			            fillFromFieldName : 'planid',//关联的列
			            entityHeaderFieldName:'planList'//显示值是对应实体类的哪个字段  
				   	}}, */
				   	/* {field:'planid',header:'摘要',visible : false,
						formEditorConfig : {
							width : 200,
							type : 'queryinput',
							required : true,
							textField : 'planlist',
							valueField : 'id',
				            allowInput:false,
					         fieldVisible:true,
				             onSelect:function($me, queryInput, rowData){
				                   mini.getbyName("currencyname").setValue(rowData.currencyname);
				                   mini.getbyName("currency").setValue(rowData.currency);},
							params : {
								xmlFileName : '/eleasing/jsp/finacial/loan_rent_plan/loan_rent_plan_income_to_interest.xml',
								loanid:'${param.loanid}'
							}
						}}, */
						{field:'sjdateofinterest',header:'起息日',formEditorConfig:{
					   		required:true,
					   		otherAttributes:'onvaluechanged="adjustSJInterest"',
					   		newLine:true,
					   		type:'date'
					   	}}, 
						{field:'sjceasedate',header:'止息日',formEditorConfig:{
					   		required:true,
					   		otherAttributes:'onvaluechanged="adjustSJInterest"',
					   		newLine:false,
					   		type:'date'
					   	}}, 
						{field:'hiredate',header:'核销日期',formEditorConfig:{
					   		required:true,
					   		newLine:true,
					   		type:'date'
					   	}}, 
				   /* 	{field:'rent',header:'租金',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,
				   		vtype:'float',
				   		fieldVisible:true
				   	}}, */
				   	{field:'sjbalance',header:'剩余本金',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,
				   		vtype:'float',
				   		onkeyup:'adjustSJInterest',
				   		newLine:false,
				   		fieldVisible:true
				   	}},
				   	{field:'sjinterestrate',header:'利率',visible:true,formEditorConfig:{
				   		required:true,
				   		newLine:true,
				   		onkeyup:'adjustSJInterest',
				   		fieldVisible:true
				   	}},
				   	
				   	{field:'sjinterest',header:'利息',visible:true,dataType:"currency",summary:true,formEditorConfig:{
				   		required:true,
				   		newLine:false,
				   		readOnly:true,
				   		vtype:'float',
				   		fieldVisible:true
				   	}},
				   	{field:'currencyname',header:'币种',visible:true,formEditorConfig:{
				   		fieldVisible:false,
				   		fillProperty:'name',
				   		defaultValue:'人民币',
			            entityClassName:'com.tenwa.business.entity.DictionaryData',
			            fillFromFieldName : 'currency',//关联的列
			            entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段 
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
function adjustSJInterest(){
	var balance=$("input[name=sjbalance]").val();
	var interestrate =$("input[name=sjinterestrate]").val();
	var dateofinterest=mini.getbyName("sjdateofinterest").getValue();
	var ceasedate=mini.getbyName("sjceasedate").getValue();
	var interest = $("input[name=sjinterest]").val();
	//借款余额
	balance=balance||0;
	//利率
	interestrate = interestrate || 0;
	//利息
	dateofinterest=dateofinterest||0;
	ceasedate=ceasedate||0;
	var temp = Number(balance)*Number(interestrate)*((ceasedate-dateofinterest)/(3600*1000*24))/360;
	mini.getbyName("sjinterest").setValue(decimal(Number(temp),2));
}
</script>
</head>
<body>
<div id="id_table_loan_rent_hire_interest"></div>
</body>
</html>