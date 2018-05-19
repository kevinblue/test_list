﻿﻿var pageWidth  = document.documentElement.clientWidth;
var pageHeight = document.documentElement.clientHeight-50;

var allQueryObjects = new Array();
var isFirstLoad = true;

function showExportWindow(){	
	if(jQuery('#div_excelExport').length == 0)
		return;
	var sHtml = "";
	for(var i = 0; i < allQueryObjects.length; i++){
		var queryObject = allQueryObjects[i];
		sHtml = sHtml + "<tr><td><input type='checkbox' id='checkbox_"+ queryObject.id 
				+ "' name='checkbox_" + queryObject.id 
				+ "' value='" + queryObject.id + "|" + queryObject.type + "' />" 
				+ queryObject.name 
				+ "</td></tr>"; 
		
	}
	//sHtml += "</table>";
	jQuery('#table_excelExport').html(sHtml);
	var dialog = jQuery('#div_excelExport');
	dialog.window({
				title: '导出',				
				width:400,		
				height:200,		
				closed:true		
	});

	dialog.show();
	dialog.window('open');
}

function exportExcel(actionurl){
	var exportObjects = new Array();
	jQuery('#table_excelExport').find('tr').each(function(i){
		var exportObject = new Object();
		var shouldAdd = false;
		jQuery(this).find('input').each(function(j){
			
			if(this.type == "checkbox"){			
				if(jQuery(this).attr('checked')){
					shouldAdd = true;
					var temp = jQuery(this).val().split('|');
					exportObject.id = temp[0];
					exportObject.type = temp[1];
					for(var i = 0; i < allQueryObjects.length; i++){
						if(allQueryObjects[i].id == exportObject.id){
							exportObject.queryParams = allQueryObjects[i].paramValues;
							break;
						}
					}
					exportObject.ready = true;
				}
			}
			if(this.type == "text"){
				if(this.id.indexOf("export_start") == 0){
					exportObject.start = jQuery(this).val();
				}else if(this.id.indexOf("export_end") == 0){
					exportObject.end = jQuery(this).val();
				}
			}

		});
		if(exportObject.ready){
			exportObjects.push(exportObject);
		}
	});
	if(exportObjects.length == 0){
		$('#div_excelExport').dialog('close');
		return false;
	}
	var browser = SysBrowser.getBrowser();
	var exportParams = JsonUtil.encode(exportObjects);	
	var reportName = jQuery('#export_reportName').val();




	var html = "<form id='form_export' method='post' target='_self' action='"+actionurl+"'>";
	html += "<input type='hidden' name='browser' value='"+browser+"'>";
	html += "<input type='hidden' name='reportName' value='" + reportName + "'>";
	html += "<input type='hidden' name='exportParams' value='" + exportParams + "'>";
	html += "</form>";
	
	var exportFrame = jQuery('#iframe_export').contents();

	//var exportFrame = jQuery(window.frames["iframe_export"].document);
	exportFrame.find('body').html(html);
	//iframe.find('body').append(html);
	
	
	exportFrame.find('#form_export').submit();

}

function createComboboxs(comboboxs,type,tableId){
	if(!comboboxs)
		return false;

	//comboboxs = buildDependecyList(comboboxs);
	//console.debug(comboboxs);
	for(var i = 0; i < comboboxs.length;i++){

		var url = "";
		var tempObj = comboboxs[i];
		if(tempObj.requestType == "JSON"){
			if(tempObj.datasource.toUpperCase().indexOf("HTTP://") >=0){
				url = tempObj.datasource;				
			}else{
				if(url.indexOf("/") == 0)
					url = globalWebRoot + tempObj.datasource;
				else
					url = globalWebRoot + "/" + tempObj.datasource;
			}
		}else if(tempObj.requestType == "SQL" || tempObj.requestType == "LOCAL" || tempObj.requestType == "DICT"){
			url = globalWebRoot + '/report/getCombobox.action?tableId=' + tableId + "&filterId=" + tempObj.id ;				
		}else if(tempObj.requestType == "XML"){
			url = globalWebRoot + "/report/getXmlCombo.action?xmlFileName=" + tempObj.datasource ;
		}
		
		
		jQuery.ajax({
			url: url,
			async:false,
			success:function(data){
				
				var tempData;
				if(typeof(data) == 'string'){
					tempData = JsonUtil.decode(data);
				}else{
					tempData = data;
				}
				var comboData;
				if(tempData['datas']){	//TracywindyCombobox Json Data Format
					if(!tempObj.nameField){
						tempObj.nameField = "name";
					}
					comboData = tempData['datas'];
				}else{
					comboData = tempData;
				}

				if(!tempObj.nameField){
					tempObj.nameField = "text";
				}
				if(!tempObj.valueField){
					tempObj.valueField = "value";
				}

				createCombobox('#' + type + '_' + tempObj.id,comboData,tempObj.nameField,tempObj.valueField,tempObj.defaultValue);
			}

		});
		

		//createCombobox('#' + type + '_' + tempObj.id,url,tempObj.nameField,tempObj.valueField);
		
	}
}

function createCombobox(targetId,data,nameField,valueField,defaultValue){
	var combobox = mini.get(targetId);
	combobox.set({
		'data':data,
		'textField':nameField,
		'valueField':valueField,
		'text' : defaultValue,
		'value' : defaultValue
	});
	
}


function buildDependecyList(comboboxs){
	var paramPattern = new RegExp('\\{.*?\\}','g');
	for(var i = 0; i < comboboxs.length; i++){
		var tempObj = comboboxs[i];
		var url = tempObj.datasource;			
		var paramDependecies = new Array();
		var result;
		while((result = paramPattern.exec(url))){
			var t = new Object();
			t.paramName = trim(result[0].substring(1,result[0].length-1));
			t.ready=false;
			paramDependecies.push(t);
		}
		tempObj.paramDependency = paramDependecies;
	}
	return comboboxs;
}

function doSearch(queryObject){	
	if(isFirstLoad){
		var filters =  queryObject['filters'];
		var searchs =  queryObject['searchs'];
		if(searchs){
			for(var i = 0 ; i < searchs.length ; i++){
				var search  = searchs[i];
				var id = search.id;
				for(var j = 0 ; j < filters.length ; j++){
					var filter = filters[j];
					var filterId = filter.id;
					if(filter.name == search.name){
						switch(filter.type){
						case "DATE":{
							if(mini.get('filter_'+filterId) && !mini.get('filter_'+filterId).getValue){
								mini.get('filter_'+filterId).setFormValue(mini.get('search_'+id).getFormValue());
							}
							break;
						}
						case "DATERANGE":{
							if(mini.get('filter_start_'+filterId) && !mini.get('filter_start_'+filterId).getValue()){
								mini.get('filter_start_'+filterId).setFormValue(mini.get('search_start_'+id).getFormValue());
							}
							if(mini.get('filter_end_'+filterId) && !mini.get('filter_end_'+filterId).getValue()){
								mini.get('filter_end_'+filterId).setFormValue(mini.get('search_end_'+id).getFormValue());
							}
							break;
						}
						case "NUMBERRANGE":{
							if(mini.get('filter_start_'+filterId) && !mini.get('filter_start_'+filterId).getValue()){
								mini.get('filter_start_'+filterId).setValue(mini.get('search_start_'+id).getValue());
							}
							if(mini.get('filter_end_'+filterId) && !mini.get('filter_end_'+filterId).getValue()){
								mini.get('filter_end_'+filterId).setValue(mini.get('search_end_'+id).getValue());
							}
							break;
						}
						default:{
							if(mini.get('filter_'+filterId) && !mini.get('filter_'+filterId).getValue()){
								mini.get('filter_'+filterId).setValue(mini.get('search_'+id).getValue());
							}				  
							break;
						}
						} 
					}
				}
			}
		}
	}
	
	var paramValues = new Array();
	var types = ["search","filter"];
	for(var k = 0; k < types.length; k++){
		var allFields = queryObject[types[k] + "s"];
		if(!allFields)
			continue;

		for(var i = 0; i <allFields.length;i++ ){
			var field = allFields[i];
			var paramValue = new Object();
			paramValue.id=field.id;
			paramValue.name=field.name;
			paramValue.label = field.label;
			paramValue.filtertype = types[k];
			paramValue.datetype = field.type;
			switch(field.type){
			case "TEXT":
				paramValue.value = mini.get('#' + types[k] + '_' + field.id).getValue();
			case "DATE":				
				//paramValue.value = jQuery('#'+ types[k] + '_' + field.id).val();				
				paramValue.value = mini.get('#' + types[k] + '_' + field.id).getFormValue();
				break;
			case "COMBOBOX":				
				//paramValue.value = $('#'+ types[k] + '_' + field.id).combobox('getValue');
				paramValue.value = mini.get('#' + types[k] + '_' + field.id).getValue();
				break;
			
			case "DATERANGE":
			case 'NUMBERRANGE':
				paramValue.value = mini.get('#'+ types[k] + '_start_' + field.id).getFormValue() + "|" + mini.get('#'+ types[k] + '_end_' + field.id).getFormValue();
				break;
			default:
				paramValue.value = mini.get('#' + types[k] + '_' + field.id).getValue();
				break;
			}
			allFields[i].value = paramValue.value;
			paramValues.push(paramValue);
		}
	}
	
	
	queryObject.paramValues = JSON.stringify(paramValues);
	loadReportTableData(queryObject);
	isFirstLoad = false;
}

function doChartSearch(queryObject){		
	var paramValues = new Array();
	var types = ["search","filter"];
	for(var k = 0; k < types.length; k++){
		var allFields = queryObject[types[k] + "s"];
		if(!allFields)
			continue;

		for(var i = 0; i <allFields.length;i++ ){
			var field = allFields[i];
			var paramValue = new Object();
			paramValue.id=field.id;
			paramValue.name=field.name;
			paramValue.label = field.label;
			switch(field.type){
			case "TEXT":
			case "DATE":				
				paramValue.value = mini.get('#chart_'+ types[k] + '_' + field.id).getFormValue();				
				break;
			case "COMBOBOX":				
				paramValue.value = mini.get('#chart_'+ types[k] + '_' + field.id).getValue();
				break;
			
			case "DATERANGE":
			case "NUMBERRANGE":
				paramValue.value = mini.get('#chart_'+ types[k] + '_start_' + field.id).getFormValue() + "|" + mini.get('#chart_'+ types[k] + '_end_' + field.id).getFormValue();
				break;
			default:
				paramValue.value = "";
				break;
			}
			allFields[i].value = paramValue.value;
			paramValues.push(paramValue);
		}
	}
	
	//console.debug(paramValues);
	//queryObject.paramValues = JSON.stringify(paramValues);
	queryObject.paramValues = JSON.stringify(paramValues);
	loadReportChartData(queryObject);
}

function loadReportTableData(queryObject){	
	
	
	var isVirtualScroll = false;
	
	if(queryObject.pageSize > 200){
		isVirtualScroll = true;
	}
	
	var sizeList = [20,50,100];
	var sizeFind = false;
	for(var i =0; i < sizeList.length; i++){
		if(sizeList[i] == queryObject.pageSize){
			sizeFind = true;
			break;
		}
	}
	if(!sizeFind){
		sizeList.push(queryObject.pageSize);
		sizeList.sort(function(a,b){
			return a-b;
		});
	}	
	//var dateMini = jQuery('#table_data_'+queryObject.id);
	var filterMini = jQuery('#filter_'+queryObject.id);
	var divTitle = jQuery('#table_title_'+queryObject.id).height();
	var width = document.documentElement.clientWidth;
	var height =  decimal(document.documentElement.clientHeight - filterMini.height() - divTitle - 12,0);
	var table = mini.get(queryObject.id);
	if(!table){
		table = new mini.DataGrid();
		table.set({
			id: queryObject.id,
			virtualScroll: isVirtualScroll,
			dataField:'datas',
			totalField:'totalCount',
			url:queryObject.url,
			
			pageSize: queryObject.pageSize,
			sizeList:sizeList,
			showPager:true,
			allowSortColumn:true,
			allowAlternating: true,
			showEmptyText: true,
			allowResize: true,
			pageIndexField:'start',
			pageSizeField:'pageSize',
			width: width,
			height: height,
			emptyText: "无数据",
			columns:queryObject.headers
	
		});
	}
	table.load({
		'id':queryObject.id,
		'paramValue':queryObject.paramValues
	},function(data){
		//console.debug(data);
	},function(data){
		//console.debug(data);
	});
	table.render($('#table_data_' + queryObject.id)[0]);
	
	
	jQuery('#table_data_' + queryObject.id).css('display','');
	//jQuery('#table_data_' + queryObject.id).css('width',(pageWidth-5) + "px");
	if(typeof(mini.get('search_'+ queryObject.id)) != 'undefined'){
		mini.get('#search_'+ queryObject.id).hide();
	}
    jQuery('#filter_' + queryObject.id).css('display','block');
    jQuery('#div_search_button_' + queryObject.id).css('display','none');
    jQuery('#div_common_button_' + queryObject.id).css('display','');
    if(queryObject.hasFilter){
    	jQuery('#div_showquery_button_' + queryObject.id).css('display','');
    }
	
	
}

function loadReportChartData(queryObject){
	var filterMini = jQuery('#filter_'+queryObject.id);
	var width = jQuery('#chart_data_' + queryObject.id).width()-40;
	var height = jQuery('#chart_data_' + queryObject.id).parent().height() - filterMini.height()-40;

	var url = queryObject.url + "?id=" + queryObject.id;
	if(queryObject.paramValues){
		url += "&paramValue=" + queryObject.paramValues;
	}
	
	jQuery('#chart_data_' + queryObject.id).insertFusionCharts({
		swfUrl:queryObject.swfUrl,
		dataSource: url,
		dataFormat: 'jsonurl',
		width: queryObject.width,
		height: queryObject.height
		
	});
	jQuery('#chart_data_' + queryObject.id).css('display','');
	jQuery('#chart_search_' + queryObject.id).css('display','none');
	jQuery('#div_common_button_' + queryObject.id).css('display','');
	if(queryObject.hasFilter){
    	jQuery('#div_showquery_button_' + queryObject.id).css('display','');
    }
}

function showOrHiddenQueryArea(aLink,tableId,isChart){
	var pageHeight = document.documentElement.clientHeight;
	var prefix = "#";
	if(isChart){
		prefix = "#chart_";
	}
	var status = jQuery(prefix + 'filter_' + tableId).css('display');
	if(status=='none'){
		jQuery(prefix + 'filter_' + tableId).css('display','');
		jQuery(prefix + 'div_filter_button_' + tableId).css('display','');
		jQuery(aLink).children("span").each(function(i){				
			jQuery(this).text('折叠');
		});
		mini.parse();
		var tableMini = mini.get(tableId);
		var dateMini = jQuery('#table_data_'+tableId);
		var filterMini = jQuery('#filter_'+tableId);
		dateMini.height(dateMini.height() - filterMini.height()-10);
		tableMini.setHeight(tableMini.getHeight()-  filterMini.height()-10);  
		tableMini.doLayout();
	}else{
		mini.parse();
		var tableMini = mini.get(tableId);
		var dateMini = jQuery('#table_data_'+tableId);
		var filterMini = jQuery('#filter_'+tableId);
		dateMini.height(dateMini.height() + filterMini.height()+10);
		tableMini.setHeight(pageHeight-30);
		tableMini.doLayout();
		jQuery(prefix + 'filter_' + tableId).css('display','none');
		jQuery(prefix + 'div_filter_button_' + tableId).css('display','none');
		jQuery(aLink).children("span").each(function(i){				
			jQuery(this).text('显示');
		});
	}
}

function report_openLink(contextPath,type,url,param,parameters,actionParametersName,actionParameters){
	var openUrl = "";
	if(type=="TABLE"){
		openUrl = contextPath + "/report/showTable.action?needjs=1&id=" + url + "&" + parameters;
	}else if(type=="CHART"){
		openUrl = contextPath + "/report/showChart.action?needjs=1&id=" + url + "&" + parameters;
	}else if(type=="PAGE"){
		openUrl = contextPath + "/report/showPage.action?needjs=1&id=" + url + "&param=" + param+"&paramvalue="+parameters+"&actionParametersName="+actionParametersName+"&actionParameters="+actionParameters;
	}else{
		if(url.toLowerCase().indexOf("http://") >= 0){
			openUrl = url + "?" + parameters;
		}else{
			if(url.indexOf("/") == 0){
				openUrl = contextPath + url + "?" + parameters;
			}else{
				openUrl = contextPath + "/" + url + "?" + parameters;
			}
		}
	}

	window.open(openUrl);
}

function openChartLink(action){
	var actionJson = JsonUtil.decode(action);
	report_openLink(actionJson.contextPath,actionJson.type,actionJson.url,actionJson.parameters);
}

function doClear(queryObject,type){
	var paramValues = new Array();
	var types = ["search","filter"];
	for(var k = 0; k < types.length; k++){
		var allFields = queryObject[types[k] + "s"];
		if(!allFields)
			continue;

		for(var i = 0; i <allFields.length;i++ ){
			var field = allFields[i];
			var paramValue = new Object();
			paramValue.id=field.id;
			paramValue.name=field.name;
			paramValue.label = field.label;
			//alert(field.type);
			switch(field.type){
			case "TEXT":
				if(type == types[k]){
					mini.get('#'+ types[k] + '_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#'+ types[k] + '_' + field.id).getFormValue();
				}
				break;
			case "DATE":
				if(type == types[k]){
					mini.get('#'+ types[k] + '_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#'+ types[k] + '_' + field.id).getFormValue();
				}
				break;
			case "COMBOBOX":
				if(type == types[k]){
					 mini.get('#'+ types[k] + '_' + field.id).setValue('');
					 paramValue.value = "";
				}else{
					paramValue.value = mini.get('#'+ types[k] + '_' + field.id).getValue();
				}
				break;
			
			case "DATERANGE":
				if(type == types[k]){
					mini.get('#'+ types[k] + '_start_' + field.id).setValue('');
					mini.get('#'+ types[k] + '_end_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#'+ types[k] + '_start_' + field.id).getFormValue() + "|" + mini.get('#'+ types[k] + '_end_' + field.id).getFormValue();
				}
				break;
			case "NUMBERRANGE":
				if(type == types[k]){
					mini.get('#'+ types[k] + '_start_' + field.id).setValue('');
					mini.get('#'+ types[k] + '_end_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#'+ types[k] + '_start_' + field.id).getFormValue() + "|" + mini.get('#'+ types[k] + '_end_' + field.id).getFormValue();
				}
				break;
			default:
				//mini.get('#'+ types[k] + '_' + field.id).setValue('');
				paramValue.value = "";
				break;
			}
			allFields[i].value = paramValue.value;
			paramValues.push(paramValue);
		}
	}
	
	if(type !='search'){
		queryObject.paramValues = JSON.stringify(paramValues);
		loadReportTableData(queryObject);
	}
}

function doChartClear(queryObject,type){
	var paramValues = new Array();
	var types = ["search","filter"];
	for(var k = 0; k < types.length; k++){
		var allFields = queryObject[types[k] + "s"];
		if(!allFields)
			continue;

		for(var i = 0; i <allFields.length;i++ ){
			var field = allFields[i];
			var paramValue = new Object();
			paramValue.id=field.id;
			paramValue.name=field.name;
			paramValue.label = field.label;
			switch(field.type){
			case "TEXT":
				if(type == types[k]){
					jQuery('#chart_'+ types[k] + '_' + field.id).val('');	
					paramValue.value = "";
				}else{
					paramValue.value = jQuery('#chart_'+ types[k] + '_' + field.id).val();		
				}		
				break;
			case "DATE":	
				if(type == types[k]){
					mini.get('#chart_'+ types[k] + '_' + field.id).setValue('');	
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#chart_'+ types[k] + '_' + field.id).getValue();		
				}		
				break;
			case "COMBOBOX":		
				if(type == types[k]){
					mini.get('#chart_'+ types[k] + '_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#chart_'+ types[k] + '_' + field.id).getValue();
				}
				break;
			
			case "DATERANGE":
				if(type == types[k]){
					mini.get('#chart_'+ types[k] + '_start_' + field.id).setValue('');
					mini.get('#chart_'+ types[k] + '_end_' + field.id).setValue('');
					paramValue.value = "";
				}else{
					paramValue.value = mini.get('#chart_'+ types[k] + '_start_' + field.id).getFormValue() + "|" + mini.get('#chart_'+ types[k] + '_end_' + field.id).getFormValue();
				}
				break;
			default:
				paramValue.value = "";
				break;
			}
			allFields[i].value = paramValue.value;
			paramValues.push(paramValue);
		}
	}
	
	if(type != 'search'){
		queryObject.paramValues = JSON.stringify(paramValues);
		loadReportChartData(queryObject);
	}
}

