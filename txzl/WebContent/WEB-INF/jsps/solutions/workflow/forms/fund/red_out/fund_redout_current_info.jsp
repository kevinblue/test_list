<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "redout_current",
		renderTo: "id_table_redout_current",
		width : globalClientWidth-30,
		height : 400,
		editFormPopupWindowWidth : 600,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect : true,
		showToolbar: showTools,
		tools: ['edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_redout_current_str ? "[]" : json_redout_current_str }'),
		columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'contractid', header: 'contractid', visible: false},
					{field: 'ebid', header: 'ebid', visible: false},
					{field:'fundfundchargeplan',header:'收款计划',visible: false},
					{field: 'ebanknumber', header: '网银编号',formEditorConfig:{readOnly : true,required: true}},
					{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{}},
					{field: 'settlemethodname', header: '结算方式',formEditorConfig:{readOnly : true,required: true}},
					{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},
					{field: 'feetypename', header: '费用类型',width:130,formEditorConfig:{newLine:true,readOnly:true}},
					{field: 'paymentid', header: '期次',formEditorConfig:{readOnly:true}},
					{field: 'factmoney', header: '收/付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true,readOnly:true}},
					{field: 'feeadjust', header: '调整金额',summary : true,dataType : "currency",formEditorConfig:{readOnly:true}},			
					{field: 'paytype', header: '支付类型',formEditorConfig:{}, visible: false},
					{field: 'paytypename', header: '支付类型', visible: false,formEditorConfig:{readOnly:true}},
					{field: 'factdate', header: '到账时间',dateFormat : "yyyy-MM-dd",formEditorConfig:{newLine:true,type: 'date',required: true,readOnly : true,format: 'yyyy-MM-dd'}},
					{field: 'accountingdate', header: '会计处理日',dateFormat : "yyyy-MM-dd",formEditorConfig:{type: 'date',required: true,format: 'yyyy-MM-dd'}},
					{field: 'accountbank', header: '本方银行',width:150,formEditorConfig:{readOnly : true,newLine:true,required: true}},   
					{field: 'account', header: '本方账户',width:150,formEditorConfig:{readOnly : true,required: true}},
					{field: 'accnumber', header: '本方账号',width:150,formEditorConfig:{readOnly : true,newLine:true,required: true}},
					{field: 'factobject', header: '付款客户',width:150,formEditorConfig:{ readOnly : true}},
					{field: 'clientbank', header: '客户银行',width:150,formEditorConfig:{ newLine:true,readOnly : true}},
					{field: 'clientaccount', header: '客户账户',width:150,formEditorConfig:{ readOnly : true}},
					{field: 'clientaccnumber', header: '客户账号',width:150,formEditorConfig:{colspan:3,newLine:true, readOnly : true}},
					{field: 'ffcmemo', header: '备注',width:280,formEditorConfig:{colspan : 3,
						width: 400,
						height:70, type: 'textarea',newLine:true}}
		]
	});
	});
});

</script>

<div id="id_table_redout_current"style="width:100%;height:100%;"></div>
