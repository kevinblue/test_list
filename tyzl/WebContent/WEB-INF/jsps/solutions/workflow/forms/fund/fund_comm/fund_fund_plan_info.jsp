<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_plan_old",
		renderTo: "id_table_fund_plan_old",
		width : '100%',
		height :'100%',
		editFormPopupWindowWidth : 400,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: [],
		data: JsonUtil.decode('<mini:param  name="json_fund_plan_old_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paytypename', header: '收付方向' },
			{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{
				
				fieldVisible: false,
				fillFromFieldName : 'feetype',
				fillProperty : 'name'
			}},
			{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{
				type : 'combobox',
				required: true,
				labelWidth:100,
				textField: 'name',
				valueField: 'value',
				fieldVisible: true,
				params: {
				    pid: 'FeeType',
					xmlFileName: 'combos/comboDict.xml'
				}
			}},
			{field: 'paymentid', header: '收/付款编号',formEditorConfig:{labelWidth:100,required: true}},
			{field: 'payobjname', header: '支付对象',formEditorConfig:{fieldVisible: false}},
			{field: 'payobj', header: '支付对象',visible: false,formEditorConfig:{
				newLine: true,
				type : 'queryinput',
				required: true,
				textField: 'name',
				valueField: 'name',
				allowInput: false,
				fieldVisible: true,
				width: 300,
				colspan:3,
				params: {
					xmlFileName: 'common/custInfo.xml'
				}
			}},
			{field: 'planmoney', header: '计划收/付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
			{field: 'factmoney', header: '实收/付款金额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',readOnly:true}},
			{field: 'overmoney', header: '计划余额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},
			{field: 'plandate', header: '收/付款日期',formEditorConfig:{
				required: true,
				type:'date'
			}},
			{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',
				width: 300,
				height:70}}
		]
	});
	});
	 
});
</script>

<div id="id_table_fund_plan_old" style="width:100%;height:400px"></div>
