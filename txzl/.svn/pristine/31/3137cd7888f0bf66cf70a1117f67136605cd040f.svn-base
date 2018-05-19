define(function(require,exports,module){
	var tenwa = require('js/report/ajax.js');
	module.exports={
		TableCombobox:[
			/* Table Combobox*/
			{id:'table_isScale',datas:'yesOrNo',defaultIndex:1},
			{id:'table_datasource',datas:'dataSource'},
			{id:'table_sqlType',datas:'sqlType'},
			{id:'table_showRowNumber',datas:'yesOrNo',defaultIndex:2},
			{id:'table_showTotalTitle',datas:'yesOrNo',defaultIndex:2},
			{id:'table_isCache', datas:'yesOrNo',defaultIndex:2},
			{id:'table_isExcel', datas:'yesOrNo',onvaluechanged:function(data){onIsExcelSelect(data,'TABLE')}},
			/*Table Column Combobox*/
			{id:'table_columnType',datas:'columnType',onvaluechanged:function(data){ onColumnTypeChanged(data,'TABLE');}},
			{id:'table_columnVisible' ,datas:'yesOrNo',defaultIndex:1},
			{id:'table_columnAlign',datas:'alignType',defaultIndex:1},
			{id:'table_columnMerge',datas:'yesOrNo',defaultIndex:1},
			{id:'table_columnGroupby',datas:'yesOrNo',defaultIndex:2},
			{id:'table_columnSubTotal',datas:'yesOrNo',defaultIndex:2},
			{id:'table_columnTotal',datas:'yesOrNo',defaultIndex:2},
			{id:'table_columnActionType',datas: 'contentType',onvaluechanged:function(data){onActionTypeSelect(data,'TABLE')}},			
			{id:'table_columnActionUrl2',datas: 'pageCondition'},	
			{id:'table_page',datas: 'contentType'},			
			//table Control
			{id:'table_controlGroup',datas: 'groupType'},
			{id:'table_controlMatch',datas: 'matchRelatition'},
			/*Table Filter Comboboxs*/			
			{id:'table_filterDbType',datas: 'filterDataDbType'},
			{id:'table_filterHtmlType', datas:'htmlType',onvaluechanged:function(data){onHtmlTypeChange(data,'TABLE');}},
			{id:'table_filterFilterDataRequestType', datas:'filterRequestDataType'}
		],

		ChartCombobox:[
			/* Chart Combobox*/
			{id:'chart_chartType',datas:'chartType',onvaluechanged:function(data){ onChartTypeChange(data);	}},
			{id:'chart_datasource',datas:'dataSource'},
			{id:'chart_sqlType',datas:'queryType'},
			/*Chart Column Combobox*/
			{id:'chart_columnType',datas:'columnType',onvaluechanged:function(data){ onColumnTypeChanged(data,'CHART');}},
			{id:'chart_columnUsageType',datas:'usageType',defaultIndex:2},
			{id:'chart_columnShowValue',datas:'yesOrNo',defaultIndex:2},
			{id:'chart_columnRightY',datas:'yesOrNo',defaultIndex:2},
			{id:'chart_columnCombi', datas:'subChartType'},
			{id:'chart_columnActionType',datas:'contentType',onvaluechanged:function(data){onActionTypeSelect(data,"CHART")}},
			//char Control
			{id:'chart_controlGroup',datas: 'groupType'},
			{id:'chart_controlMatch',datas: 'matchRelatition'},
			/*Chart Filter Combobox*/
			{id:'chart_filterDbType',datas: 'filterDataDbType'},
			{id:'chart_filterHtmlType',datas:'htmlType',onvaluechanged:function(data){onHtmlTypeChange(data,'CHART');}},
			{id:'chart_filterFilterDataRequestType',datas:'filterRequestDataType'}
		]
	};

	function onColumnTypeChanged(data,contentType){
		var lc = contentType.toLowerCase();
		var prefix = '#tr_' + lc;
		if(contentType == 'TABLE'){
			if(data != "NUMBER"){
				jQuery(prefix + '_subTotal').css('display','none');
				jQuery(prefix + '_total').css('display','none');
			}else{
				jQuery(prefix + '_subTotal').css('display','');
				jQuery(prefix + '_total').css('display','');
			}
		}
		var formaterList = ['NUMBER','DATE','DICT'];
		var found = false;
		for(var i =0;i<formaterList.length;i++){
			if(formaterList[i] == data){
				jQuery(prefix + '_columnformater').css('display','');						
				return ;
			}
		}
		if(!found){
			jQuery(prefix + '_columnformater').css('display','none');

		}
	}

	function onHtmlTypeChange(data,contentType){
		var lc = contentType.toLowerCase();
		var trPrefix = '#tr_' + lc + '_combobox_datasource';
		if(data == "COMBOBOX"){
			for(var i = 1 ; i < 5;i++){
				jQuery(trPrefix + i).css('display','');	
			}
		}else{
			for(var i = 1 ; i < 5;i++){
				jQuery(trPrefix + i).css('display','none');	
			}
		}
	}

	function onChartTypeChange(chartType){

		var c = mini.get('chart_chartType2');	
		c.set({
			textField: 'text',
			valueField: 'value',
			dataField: 'datas',
			onvaluechanged:function(e){
				var chartType = mini.get('chart_chartType').getValue();
				var chart = mini.get('chart_chartType2').getValue();
				if(chartType == 'Combi'){
					var dualYChart = new Array('CombiDY2D');
					var isDualY = false;
					$('#tr_chart_combichart').css('display','');
					for(var c = 0 ; c < dualYChart.length; c++){
						if(dualYChart[c] == chart){
							isDualY = true;
							$('#tr_chart_dualy').css('display','');
							$('#tr_chart_dualy2').css('display','');		
							break;
						}	
					}
					if(!isDualY){
						$('#tr_chart_dualy').css('display','none');
						$('#tr_chart_dualy2').css('display','none');
					}
				}else{
					$('#tr_chart_combichart').css('display','none');
					$('#tr_chart_dualy').css('display','none');
					$('#tr_chart_dualy2').css('display','none');
				}
			}
		});	
		c.load(tenwa.getWebRoot() + "/report/config/listChartsByType.action?chartType=" + chartType);
		c.doValueChanged();
	}

	function onActionTypeSelect(data,contentType){
		var lc = contentType.toLowerCase();
		var prefix = "#tr_" + lc;


		if(data == ""){
			$(prefix + '_actionurl').css('display','none');
			$(prefix + '_actionurl2').css('display','none');
			$(prefix + '_actionparameter').css('display','none');
			$(prefix + '_actioncondition').css('display','none');
			$(prefix + '_actionmemo').css('display','none');
		}else if(data == 'TABLE' || data == 'CHART'){			
			var treeNode = mini.get(lc + "_column_tree").getSelectedNode();
			if(!treeNode){
				return;
			}
			var c = mini.get(lc + "_columnActionUrl");
			c.set({
				textField: 'name',
				valueField: 'id',
				dataField: 'datas'
			});
			c.load(tenwa.getWebRoot() + "/report/config/queryContentNodes.action?type=" + data);

			if(treeNode.attributes[lc + '_columnActionUrl']){
				c.setValue(treeNode.attributes[lc + '_columnActionUrl']);
				c.doValueChanged();
			}			

			$(prefix + '_actionurl').css('display','');
			$(prefix + '_actionurl2').css('display','none');
			$(prefix + '_actionparameter').css('display','');
			$(prefix + '_actioncondition').css('display','');
			$(prefix + '_actionmemo').css('display','');

		}else if(data=='PAGE'){
			var treeNode = mini.get(lc + "_column_tree").getSelectedNode();
			if(!treeNode){
				return;
			}
			if(treeNode.attributes[lc + '_columnActionUrl']){
				mini.get(lc  + '_columnActionUrl2').setValue(treeNode.attributes[lc + '_columnActionUrl']);
			}
			$(prefix + '_actionurl').css('display','none');
			$(prefix + '_actionurl2').css('display','');
			$(prefix + '_actionparameter').css('display','');
			$(prefix + '_actioncondition').css('display','');
			$(prefix + '_actionmemo').css('display','');
		}
	}
	
	function onIsExcelSelect(data,contentType){
		var lc = contentType.toLowerCase();
		var prefix = "#tr_" + lc;
		if(!data || data=='0'){
			$(prefix + '_controlPerson').css('display','none');
			mini.get(lc+'_controlPerson').setValue('');
		}else{
			$(prefix + '_controlPerson').css('display','');
		}
	}
	
});