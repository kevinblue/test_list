<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function() {
	 var loanid ='${param.loanid}';
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'loan_fee_income',
				renderTo:'id_table_loan_fee_income',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				importTargetClass : 'com.tenwa.leasing.entity.finacial.LoanFeeIncome',
				importDataIndex : '2',
				importHeaderIndex : '1',
				importLoanaccountid :loanid,
				importOrExPortCallBack:'SetloanIdIncome',
  				remoteOper:true,
  				params:{loanid:loanid},
				entityClassName:'com.tenwa.leasing.entity.finacial.LoanFeeIncome',
				xmlFileName:'/eleasing/jsp/finacial/loan_fee_income/loan_fee_income_list.xml',
				tools:['add','-','edit','-','remove','-','exportExcel','-','importExcel'],
				afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
					//alert(mini.getbyName("feename").getValue());
					mini.getbyName("planid").setText(mini.getbyName("feename").getValue());
				},
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field: 'feename', header: '支付计划', 
						  formEditorConfig:{
					          fieldVisible: false,
						      fillProperty:'name',
						      entityClassName:'com.tenwa.leasing.entity.finacial.LoanFeePlan',
							  fillFromFieldName : 'planid',//关联的列
							  entityHeaderFieldName:'feeName'//显示值是对应实体类的哪个字段
						      }
						},
					{
						field : 'planid',
						header : '支付计划',
						visible : false,
						formEditorConfig : {
							width : 200,
							type : 'queryinput',
							required : true,
							textField : 'feename',
							valueField : 'id',
				            allowInput:false,
					         fieldVisible:true,
				             onSelect:function($me, queryInput, rowData){
				                   mini.getbyName("currencyname").setValue(rowData.currencyname);
				                   mini.getbyName("currency").setValue(rowData.currency);},
							params : {
								xmlFileName : '/eleasing/jsp/finacial/loan_fee_plan/loan_fee_plan_income.xml',
								loanid:'${param.loanid}'
							}
						}
					},
              		{field:'loanid',header:'借款合同号',visible:false,allowSort:true,formEditorConfig:{
				   		required:false,
				   		fieldVisible:false,
				   		defaultValue:'${param.loanid}',
              			type:'text'
              		}}, 
				   	{field:'currencyname',header:'币种',visible:true,formEditorConfig:{
				   		fieldVisible:false,
					     fillFromFieldName:'currency',
				         fillProperty:'name',
				         defaultValue:'人民币',
				         entityClassName:'com.tenwa.business.entity.DictionaryData',
					     fillFromFieldName : 'currency',//关联的列
					     entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段
				   	}},
				   	{field:'currency',header:'币种',visible:false,formEditorConfig:{
				   		type:'combobox',
				   		fieldVisible:true,
				   		required:true,
				   		readOnly:false,
				   		defaultValue:'currency_type1',
				   		textField: 'name',
						valueField: 'value',						
						params:{pid: 'currency_type',xmlFileName: '/combos/comboDict.xml'}
				   	}},
				   	{field:'factmoney',header:'实付金额',visible:true,dataType:"currency",formEditorConfig:{
				   		required:true,
              			type:'text',vtype:'float',
				   		newLine:true,
				   		fieldVisible:true
				   	}},
				   	{field:'factdate',header:'实付日期',visible:true,formEditorConfig:{
				   		required:true,
				   		type:'date'
				   	}},
				   	{field:'adjustmoney',header:'调整金额',visible:true,align:'center',
						headerAlign : 'center',dataType:"currency",formEditorConfig:{
				   		required:true,
              			type:'text',vtype:'float',
				   		newLine:true,
				   		fieldVisible:true
				   	}},
				]
			});
		});
	});
</script>
</head>
<body>
	<div id="id_table_loan_fee_income"></div>
</body>
</html>