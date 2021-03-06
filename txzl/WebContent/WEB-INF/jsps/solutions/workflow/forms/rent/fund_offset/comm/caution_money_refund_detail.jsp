<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	tenwa.createTable({
		id: "caution_money_refund_detail",
		renderTo: "id_table_caution_money_refund_detail",
		width : globalClientWidth-30,
		height : 400,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		lazyLoad: true,
		showToolbar: showTools,
		data: JsonUtil.decode('${empty json_caution_money_refund_detail_str ? "[]" : json_caution_money_refund_detail_str }'),
		tools: ['edit', '-', 'remove'],
		removeOperCallBack:function(miniTable, rowDatas){
		      for(j=0;j<rowDatas.length;j++){
                var pid=rowDatas[j].pid;
                var guarantable=mini.get("rent_income_detail");
                var guarantableData=guarantable.getData();
                for(var i=0;i<guarantableData.length;i++){
                     if(guarantableData[i].planid==pid){
                     	guarantable.removeRow (guarantableData[i]);
                     }
                } 
		      } 
		   return true;
        },
		columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'pid', header: 'pid',visible: false},
					{field:'fundfundchargeplan',header:'付款计划',visible: false},
					{field: 'paymentid', header: '付款编号',formEditorConfig:{readOnly : true}}, 
					{field: 'comparemoney', header: '比较金额', visible: false,formEditorConfig:{readOnly : true}}, 
					{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},
					{field: 'feetypename', header: '费用类型',formEditorConfig:{readOnly : true}},
					{field: 'paytype', header: '支付类型',formEditorConfig:{newLine:true}, visible: false},
					{field: 'paytypename', header: '支付类型', visible: false,formEditorConfig:{newLine:true,readOnly:true}},
					{field: 'factmoney', header: '付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
					{field: 'comparemoney', header: '比较付款金额', visible: false},
					{field: 'factadjust', header: '调整付款金额', visible: false},
					{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{
						type : 'combobox',
						required: true,
						textField: 'name',
						valueField: 'value',
						allowInput: true,
						fieldVisible: true,
						colspan : 3,	
						params: {
								pid: 'PayFund',
								xmlFileName: 'combos/comboDict.xml'
							
						}}},
					{field: 'settlemethodname', header: '结算方式',formEditorConfig:{fieldVisible: false,
						  fillFromFieldName : 'settlemethod',
						fillProperty : 'name'}},
							
					{field: 'factdate', header: '付款日期',formEditorConfig:{newLine:true,required: true,type:'date'}},
					{field: 'accountingdate', header: '会计处理日',formEditorConfig:{type:'date'}},
					{field: 'ffcmemo', header: '备注',formEditorConfig:{
						type: 'textarea',
						newLine:true,
						colspan : 3,
						width: 300,
						height:70
					}}

		]
	});
});
function getCautionMoney(){
	var tableData=mini.get("caution_money_refund_detail").getData();
	var summoney=0;
	for(var i=0;i<tableData.length;i++){
		summoney=parseFloat(summoney)+parseFloat(tableData[i].factmoney);
	}
	summoney=parseFloat(summoney).toFixed(2);
	return summoney;
}
</script>
<div id="id_table_caution_money_refund_detail" style="width: 100%;height: 100%;"></div>
