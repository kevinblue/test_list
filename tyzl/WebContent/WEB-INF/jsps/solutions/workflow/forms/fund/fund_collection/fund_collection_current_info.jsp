<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "collection_current",
		renderTo: "id_table_collection_current",
		width : globalClientWidth-30,
		height : 400,
		editFormPopupWindowWidth : 800,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper:false,
		showPager: false,
		showToolbar: showTools,
		tools: ['edit', '-', 'remove'],
		validateForm: function(miniTable, miniForm){
			var planmoney =getMiniEditFormField("collection_current.planmoney").getValue();
			var hadmoney=getMiniEditFormField("collection_current.hadmoney").getValue();
			var factmoney =getMiniEditFormField("collection_current.factmoney").getValue();
			var feeadjust =getMiniEditFormField("collection_current.feeadjust").getValue();  
            var sum=parseFloat(factmoney)+parseFloat(feeadjust);
            sum=sum.toFixed(2);
            planmoney=parseFloat(planmoney).toFixed(2);
            var overmoney=parseFloat(planmoney)-parseFloat(hadmoney);
            overmoney=parseFloat(overmoney).toFixed(2);
			if (parseFloat(overmoney) < parseFloat(sum)){mini.alert("本次收款金额与调整金额之和不能大于余额("+overmoney+")!");return false;} 
		    return true;
		},
		data: JsonUtil.decode('<mini:param  name="json_collection_current_str" defaultValue="[]"/>'),
		columns: [
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'contractid', header:'contractid', visible:false},
			{field:'ebid', header:'ebid', visible:false},
			{field:'fundfundchargeplan',header:'收款计划',visible:false},
			{field:'ebanknumber', header:'网银编号',formEditorConfig:{readOnly:true,required:true}},
			{field:'settlemethod', header:'结算方式', visible:false,formEditorConfig:{}},
			{field:'settlemethodname', header:'结算方式',formEditorConfig:{readOnly:true,required:true}},
			{field:'feetype', header:'费用类型',formEditorConfig:{}, visible:false},
			{field:'feetypename', header:'费用类型',formEditorConfig:{newLine:true,readOnly:true}},
			{field:'paymentid', header:'期次',formEditorConfig:{readOnly:true}},
			{field:'planmoney', header:'计划金额',summary:true,dataType:"currency",formEditorConfig:{newLine:true,readOnly:true}},
			{field:'hadmoney', header:'已收金额',summary:true,dataType:"currency",formEditorConfig:{readOnly:true,required:true}},
			{field:'factmoney', header:'本次收款',summary:true,dataType:"currency",formEditorConfig:{newLine:true,required:true}},
			{field:'feeadjust', header:'调整金额',summary:true,dataType:"currency",formEditorConfig:{}},			
			{field:'paytype', header:'支付类型',formEditorConfig:{}, visible:false},
			{field:'paytypename', header:'支付类型', visible:false,formEditorConfig:{readOnly:true}},
			{field:'factdate', header:'到账时间',dateFormat:"yyyy-MM-dd",
				         formEditorConfig:{
		                          newLine:true,
		                             type:'date',
		                         required:true,
		                         readOnly:true,
		                           format:'yyyy-MM-dd'}},
			{field:'accountingdate', header:'会计处理日',dateFormat:"yyyy-MM-dd",
		                 formEditorConfig:{
		      		                 type:'date',
		      		             required:true,
		      		               format:'yyyy-MM-dd'}},
			{field:'accountbank', header:'本方银行',formEditorConfig:{readOnly:true,newLine:true,required:true}},   
			{field:'account', header:'本方账户',formEditorConfig:{readOnly:true,required:true}},
			{field:'accnumber', header:'本方账号',formEditorConfig:{readOnly:true,newLine:true,required:true}},
			{field:'factobject', header:'付款客户',formEditorConfig:{ readOnly:true}},
			{field:'clientbank', header:'客户银行',formEditorConfig:{ newLine:true,readOnly:true}},
			{field:'clientaccount', header:'客户账户',formEditorConfig:{ readOnly:true}},
			{field:'clientaccnumber', header:'客户账号',formEditorConfig:{colspan:3,newLine:true, readOnly:true}},
			{field:'ffcmemo', header:'备注',width:280,
				          formEditorConfig:{
		                         colspan:3,
		                           width:400,
		                          height:70, 
		                            type:'textarea',
		                         newLine:true}}
		]
	});
	});
});
</script>

<div id="id_table_collection_current" style="width:100%;height:100%;"></div>
