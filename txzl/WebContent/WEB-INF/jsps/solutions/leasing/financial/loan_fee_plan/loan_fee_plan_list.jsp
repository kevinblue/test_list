<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">

function importMiniTableCallBack(message, tableid) {
	var info = eval("(" + message + ")");
	alert(info.message);
	var tabledate = info.tabledate;
	if ("" != tabledate) {
		var grid = mini.get(tableid);
		grid.set({
			data : mini.decode(tabledate)
		});
	}
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
}
jQuery(function() {
    var loanid ='${param.loanid}';
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'loan_fee_plan',
				renderTo:'id_table_loan_fee_plan',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				importTargetClass : 'com.tenwa.leasing.entity.finacial.LoanFeePlan',
				importDataIndex : '2',
				importHeaderIndex : '1',
				importLoanid :loanid,
				importOrExPortCallBack:'SetloanId',
  				remoteOper:true,
  				params:{loanid:loanid},
				entityClassName:'com.tenwa.leasing.entity.finacial.LoanFeePlan',
				xmlFileName:'/eleasing/jsp/finacial/loan_fee_plan/loan_fee_plan_list.xml',
				afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
					//alert(mini.getbyName("feename").getValue());
					if (operFlag == "add") {
					mini.getbyName("payaccount").setText("3901181209000008343");
					mini.getbyName("payaccount").setValue("3901181209000008343");
					}
				/* 	mini.getbyName("payaccount").setValue("3901181209000008343");
					mini.getbyName("payaccountname").setText("3901181209000008343");
					mini.getbyName("payaccountname").setValue("3901181209000008343"); */
				},
				operValidate:function(miniTable,miniForm, operType){
					if("remove"==operType){
						var checkedRowDatas = miniTable.getSelected();
						
						if("未核销"!=checkedRowDatas.hirestatus){
							mini.alert("核销过的记录不能删除！");
							return false;
						}
					}
					return true;
				},
				tools:['add','-','edit','-','remove','-','exportExcel','-','importExcel'],
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
				   		value : loanid,
              			type:'text'
              		}}, 
              		{field:'feetypename',header:'融资费类型',visible:true,formEditorConfig:{
              			fieldVisible:false,
              			entityClassName:'com.tenwa.business.entity.DictionaryData',
				         fillFromFieldName : 'feetype',//关联的列
				         entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段
              		}},
              		{field:'feetype',header:'融资费类型',visible:false,formEditorConfig:{
				   		type:'combobox',
				   		fieldVisible:true,
				   		required:true,
				   		textField: 'name',
						valueField: 'value',
						params:{pid:'fee_type',xmlFileName: '/combos/comboDict.xml'}
				   	}},
				   	{field:'feename',header:'摘要',visible:true,formEditorConfig:{
				   		required:true,
				   		newLine:true,
				   		fillFromFieldName:'feetype',
				   		fillProperty:'name',
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
				           /*  entityClassName:'com.tenwa.leasing.entity.proj.ProjInfo',//对应实体类
								fillProperty:'name',//显示projid的name
								fillFromFieldName : 'projid',//关联的列
								entityHeaderFieldName:'projectName'//显示值是对应实体类的哪个字段 */
				   	}},
				   	{field:'currency',header:'币种',visible:false,formEditorConfig:{
				   		type:'combobox',
				   		fieldVisible:true,
				   		required:true,
				   		defaultValue:'currency_type1',
				   		textField: 'name',
						valueField: 'value',
						params:{pid:'currency_type',xmlFileName: '/combos/comboDict.xml'}
				   	}},
				   	{field:'plandate',header:'应付日期',visible:true,formEditorConfig:{
				   		required:true,
				   		newLine:true,
				   		type:'date'
				   	}},
				   	{field:'planmoney',header:'应支付金额',visible:true,dataType:'currency',formEditorConfig:{
				   		required:true,
				   		vtype:'float',
				   		fieldVisible:true
				   	}},
				   	{field:'payaccountname',header:'支付帐号',visible:false,formEditorConfig:{
				   		fieldVisible:false
				   	}},
				   	{field:'payaccount', header:'支付帐号',visible:false,formEditorConfig:{
						type : 'queryinput', 
						multiSelect : false, 
				   		required:false,
						allowInput : false,
						fieldVisible:true,
				   		newLine:true,
						valueField : 'accnumber', 
						textField : 'accnumber', 
						labelWidth : 100, 
						width : 200, 
						params : {xmlFileName : 'eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml'}
					}
				  }, 
				  {field:'hirestatus',header:'核销状态',formEditorConfig:{
				   		fieldVisible:false
				   	}},
				  {field:'memo',header:'备注',visible:true,formEditorConfig:{
				   		type:'textarea',
				   		newLine:true,
				   		colspan:'3',
				   		width:500
				   }}
				]
			});
		});
	});
</script>
</head>
<body>
	<div id="id_table_loan_fee_plan"></div>
</body>
</html>