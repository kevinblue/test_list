define(function(require,exports,module){
	module.exports = {
		tableColumnMapping:[
			{name:'columnId',type:'text'},
			{name:'columnName',type:'text'},
			{name:'columnLabel',type:'text'},
			{name:'columnType',type:'combobox','field':'id_combo_columnType'},
			{name:'columnFormater',type:'text'},
			{name:'columnGroupby',type:'combobox','field':'id_combo_columnGroupby'},
			{name:'columnColor',type:'text'},
			{name:'columnVisible',type:'combobox','field':'id_combo_columnVisible'},
			{name:'columnAlign',type:'combobox','field':'id_combo_columnAlign'},
			{name:'columnTotal',type:'combobox','field':'id_combo_columnTotal'},
			{name:'columnSubTotal',type:'combobox','field':'id_combo_columnSubTotal'},
			{name:'columnWidth',type:'text'},
			{name:'columnActionType',type:'combobox','field':'id_combo_columnActionType'},						
			{name:'columnMerge',type:'combobox','field':'id_combo_columnMerge'},
			{name:'columnActionParameter',type:'text'},
			{name:'columnActionCondition',type:'text'}],

		chartColumnMapping:[
			{name:'columnId',type:'text'},
			{name:'columnName',type:'text'},
			{name:'columnLabel',type:'text'},
			{name:'columnType',type:'combobox','field':'id_combo_chart_columnType'},								
			{name:'columnColor',type:'text'},
			{name:'columnActionType',type:'combobox','field':'id_combo_chart_columnActionType'},
			{name:'columnActionParameter',type:'text'},
			{name:'columnActionCondition',type:'text'},	
			{name:'columnUsageType',type:'combobox','field':'id_combo_chart_usageType'},	
			{name:'columnShowValue',type:'combobox','field':'id_combo_chart_showValue'},
			{name:'columnRightY',type:'combobox','field':'id_combo_chart_rightY'},		
			{name:'columnCombi',type:'combobox','field':'id_combo_chart_combichart'},
			{name:'columnFormater',type:'text'}],

		columnMappingJson:{TABLE:'tableColumnMapping',CHART:'chartColumnMapping'},

		tableFilterMapping:[
			{name:'filter_id',type:'text'},
			{name:'filter_name',type:'text'},
			{name:'filter_label',type:'text'},
			{name:'filter_dbType',type:'combobox','field':'id_combo_filter_dbType'},
			{name:'filter_filterType',type:'text'},
			{name:'filter_express',type:'text'},
			{name:'filter_defaultValue',type:'text'},
			{name:'filter_htmlType',type:'combobox','field':'id_combo_filter_htmlType'},
			{name:'filter_filterDataRequestType',type:'combobox','field':'id_combo_filter_filterDataRequestType'},								
			{name:'filter_comboboxDataSource',type:'text'},
			{name:'filter_comboboxNameField',type:'text'},
			{name:'filter_comboboxValueField',type:'text'}],

		chartFilterMapping:[
			{name:'filter_id',type:'text'},
			{name:'filter_name',type:'text'},
			{name:'filter_label',type:'text'},
			{name:'filter_dbType',type:'combobox','field':'id_combo_chart_filter_dbType'},
			{name:'filter_filterType',type:'text'},
			{name:'filter_express',type:'text'},
			{name:'filter_defaultValue',type:'text'},
			{name:'filter_htmlType',type:'combobox','field':'id_combo_chart_filter_htmlType'},
			{name:'filter_filterDataRequestType',type:'combobox','field':'id_combo_chart_filter_filterDataRequestType'},
			{name:'filter_comboboxDataSource',type:'text'},
			{name:'filter_comboboxNameField',type:'text'},
			{name:'filter_comboboxValueField',type:'text'}],

		filterMappingJson:{TABLE:'tableFilterMapping',CHART:'chartFilterMapping'}
	}
});