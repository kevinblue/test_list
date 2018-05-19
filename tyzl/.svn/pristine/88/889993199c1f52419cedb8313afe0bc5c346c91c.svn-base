var tracywindyMultiTable = function(config){
	//必要属性
	this.objectType = "multiTable"; 
	this.renderTo   = config.renderTo;
	tracywindyObject["multiTable_"+(config.id||this.renderTo)]=this;
	this.width      = config.width;
	this.height     = config.height;
	this.columns    = config.columns;
	this.datas      = config.datas||[];
	this.isShowCopyCount = config.isShowCopyCount;
	//可选
	this.id         = config.id||("table_"+this.renderTo);
	this.border     = config.border||false;
	this.isCheck    = (false == config.isCheck ) ? false : true;
	this.isRank     = (false == config.isRank) ? false : true;
	this.showHeader = true;
	this.checkOnly  = true;
	this.isFit      = config.isFit||false;
	this.loadMode   = config.loadMode||'local';
	this.callBack   = config.callBack||function(){};
	this.params     = config.params||{};
	this.msg        = config.msg||"请勾选要操作的数据";
	this.tools      = ('undefined'==typeof(config.tools)) ? "新增|复制|修改|删除|导出模板|导入模板" : config.tools;
	this.otherTools = config.otherTools||[];
	this.tools      = this.tools.replace(/\s/,"");
	if(config.isNeedTools ==false)this.tools = "";
	this.idField    = config.idField||"id";
	this.windowId = this.id+"Window";
	this.windowTop        = config.windowTop||20;
	this.windowWidth      = config.windowWidth||400;
	this.windowHeight     = config.windowHeight;
	this.labelWidth       = config.labelWidth||100;
	//回调
	//operType:"add","update"
	this.resetFromOperCallBack = config.resetFromOperCallBack||function(operType,thisForm,multiTable){};
	this.updateFromOperCallBack = config.updateFromOperCallBack||function(operType,thisForm,multiTable,rowData){};
	this.operValidate          = config.operValidate||function(operType,rowDatas,thisForm,multiTable){return true;};
	this.beforeCreateTableWindowCallback = config.beforeCreateTableWindowCallback||function($me){};
	//自定义修改更新combobox的数值
	//columnConfig["initValue"](combo,rowDatas[0],multiTable,multiTable)
	//合计
	this.isStatistic          = (config.isStatistic == false)?false:true;
	this.isExportTitle = config.isExportTitle||false;
	//this.inputWidth     = config.inputWidth||165;
	var $me = this;
	var $toolsMapping = {
			"新增":"add",
			"复制":"copy",
			"修改":"update",
			"删除":"remove",
			"导出模板":"export",
			"导入模板":"import"
	};
	this.commonValidateTableDataPassed = config.commonValidateTableDataPassed||function(rowDatas,$me){
		   if(this.operValidate){
			   if(!this.operValidate($me.flag,rowDatas,jQuery("#"+this.windowId+" form")[0],$me)){
				   return false;
			   }
		   }
		   if("add" !=$me.flag){
			   if(0 == rowDatas.length)
			   {
				   alert(this.msg);
				   return false;
			   }
		   }
		   return true;
	};
	this.validateForm  = config.validateForm||function($me,currentTable){
		return Validator.Validate(jQuery("#"+$me.windowId+" form")[0],1,false);
	};
	this.findColumnConfigByName = function($me,currentTable,columnName){
		var columns = currentTable.columns;
		for(var i = 0;i<columns.length;i++){
			var column = columns[i];
			if(columnName == column["name"]){
				return column;
			}
		}
		return {};
	};
	//dataType:'text|number|combobox|date',dateformat:'yyyy-MM-dd',required:false
	var commonCreateTableWindow = config.commonCreateTableWindow || function(){
		   this.statisticColumnNames = [];
		   if(jQuery("#"+this.windowId)[0]){
			   if('BODY'!=jQuery("#"+this.windowId).parent()[0].tagName){
				   jQuery("#"+this.windowId).parent().remove();
			   }else{
				   jQuery("#"+this.windowId).remove();
			   }
			   jQuery("#"+this.windowId+"dlg-buttons").remove();
		   }
		   {
			   this.comboboxs = this.comboboxs||[];
			   for(var i=0;i<this.comboboxs.length;i++)
			   {
				   var curCombo = this.comboboxs[i];
				   try{document.body.removeChild(curCombo.dropDiv);}catch(e){}
				   
			   }
			   this.configRemains   = {};
		 	   var tableWindow_html = "";
		 	   tableWindow_html+='<div id="'+this.windowId+'"  buttons="#'+this.windowId+'dlg-buttons" closed="true" modal="true" title="（新增/修改）数据" style="display:none;width:'+this.windowWidth+'px;'+(this.windowHeight?('height:'+this.windowHeight+'px;'):'')+'text-align:center;padding:0px;">';
		 	   tableWindow_html+= '  <center>';
		 	   tableWindow_html+='       <div style="width:95%;">';
		 	   tableWindow_html+='          <iframe style="display:none;" name="'+this.windowId+'_real_submit_frame"></iframe>';
		 	   tableWindow_html+='          <form id="'+this.windowId+'Form" enctype="multipart/form-data" target="'+this.windowId+'_real_submit_frame"  method="post" >';
		 	   tableWindow_html+='	        <table align="center" style="width:90%">';
		 	   tableWindow_html+='		            <tr style="height:5px;"><td></td></tr>';
		 	   
		 	   this.comboboxArray = [];
		 	   //动态填充开始
		 	   for(var i=0;i<this.columns.length;i++)
		 	   {
			 	   var trClassName    = (i%2)==0 ? "tr-even" : "tr-odd";//
			 	   var columnConfig = this.columns[i];
			 	   var requireContent = "";
			 	   var isRequired = (columnConfig['require']==true)||(columnConfig['nullable']==false);
			 	   if(isRequired){
			 		  requireContent="<font color='red'>*</font>";
			 	   }
			 	   var labelContent   = (columnConfig['header']||columnConfig['name'])+"：";
			 	   var inputName      = columnConfig['inputName']||columnConfig['name'];
			 	   
			 	   this.configRemains[inputName] = columnConfig;
			 		   
			 	   var defaultValue   = columnConfig['defaultValue']||'';
			 	   var dataType       = columnConfig['type']||'text';
			 	   var isHidden        = columnConfig['hidden']||false;
			 	   var readOnly        = columnConfig['readOnly']||false;
			 	   var readOnlyString  = readOnly?"readOnly":'';
			 	   if(this.idField == columnConfig['name']){isHidden=true;};
			 	   var styleDisplay  = "style=\"display:"+(isHidden?"none":'block')+";\"";
			 	   var valueContent   = "";
			 	   var otherAttributes = columnConfig['otherAttributes'];
			 	   if(!otherAttributes){
			 		  otherAttributes = "";
			 	   }
			 	   var otherClasses      ="";
			 	   if(readOnly)
			 	   {
			 		  otherClasses+=" td-content-readonly ";
			 		  otherAttributes+=' '+readOnlyString+' ';
			 	   }
			 	   if(isRequired)
			 	   {
			 		  otherAttributes+=' require="true" label="'+columnConfig['header']+'"';
			 	   }
	 	   		   //显示值
	 	   		   var relativeDisplayInputId = $me.windowId+'_'+inputName;
	 	   		   var oldOtherAttributes = otherAttributes;
	 	   		   otherAttributes+=" id='"+relativeDisplayInputId+"' ";
			 	   switch(dataType.toLowerCase())
			 	   {
			 	   	   case "text":{
			 	   		  valueContent = '<input type="text"  name="'+inputName+'" class="td-content-input'+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' />';
			 	   		  break;
			 	   	   }
			 	   	   case "textarea":{
			 	   		   valueContent = '<textarea  name="'+inputName+'" class="td-content-input '+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' ></textarea>';
			 	   		   break;
			 	   	   }
			 	   	   case "number":{
			 	   		   this.statisticColumnNames.push(inputName);
			 	   		   otherAttributes+=' dataType="Number" ';
			 	   		   valueContent = '<input type="text" name="'+inputName+'" class="td-content-input'+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' />';
			 	   		   break;
			 	   	   }
			 	   	   case "double":{
			 	   		   this.statisticColumnNames.push(inputName);
			 	   		   otherAttributes+=' dataType="Double" ';
			 	   		   valueContent = '<input type="text" name="'+inputName+'" class="td-content-input'+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' />';
			 	   		   break;
			 	   	   }
			 	   	   case "date":{
			 	   		   if(columnConfig['readOnly']){
			 	   			   valueContent = '<input readonly type="text"  name="'+inputName+'" class="td-content-input'+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' />';
			 	   		   }
				 	   	   else{
				 	   		  var dateFormat = columnConfig['dateFormat']||'yyyy-MM-dd';
					 	   	  valueContent = '<input readonly type="text" name="'+inputName+'" dateFormat="'+dateFormat+'" id="'+this.id+"id_date_"+inputName+'" class="Wdate td-content-input'+otherClasses+'" value="'+defaultValue+'" '+otherAttributes+' />';
				 	   	   }
				 	   	   break;
				 	   }
			 	   	   case "combobox":{
			 	   	       var config = columnConfig['config'];
			 	   	       //新增根据id来查询combobox
			 	   	       config["id"] = $me.windowId+"_queryMultiComboBox_"+inputName;
			 	   		   config['relativeDisplayInputId'] = relativeDisplayInputId;
			 	   		   valueContent  =  '<input type="hidden"  name="'+inputName+'" id="'+relativeDisplayInputId+'"/>';
			 	   		   var columnHiddenName = columnConfig['hiddenName'];
			 	   		   
			 	   		   if(columnHiddenName){
			 	   			 var relativeHiddenInputId = $me.windowId+'_'+columnHiddenName;
			 	   			 //valueContent +=  '<input type="hidden"  name="'+columnHiddenName+'" id="'+relativeHiddenInputId+'"/>';
			 	   			 config['relativeHiddenInputId'] = relativeHiddenInputId;
			 	   		   }
			 	   		   //模拟下拉框
			 	   		   valueContent += '<div id="'+this.id+'id_combo_'+inputName+'_container"></div>';
			 	   		   var comboboxDivContianerId = this.id+'id_combo_'+inputName+'_container';
			 	   		   var defaultRawValue = columnConfig['defaultRawValue']||'';
			 	   		   if('undefined' == typeof(columnConfig['isViewHistoryTask'])){
				 	   		   if(window.isViewHistoryTask){
					 	   			 columnConfig['isViewHistoryTask'] = window.isViewHistoryTask;
					 	   	   }
			 	   		   }else{
			 	   			  config['isViewHistoryTask'] = columnConfig['isViewHistoryTask'];
			 	   		   }
			 	   		   config['hiddenName'] = columnHiddenName;
			 	   		   config['renderTo'] = comboboxDivContianerId;
			 	   		   config['otherAttributes'] = oldOtherAttributes;
			 	   		   config['value'] = defaultValue;
			 	   		   config['rawValue'] = defaultRawValue;
			 	   		   config['readOnlyData'] = columnConfig['readOnly'] ? true : false;
			 	   		   var topAdd = 0;
			 	   		   config['topAdd'] = topAdd;
			 	   		   config['leftAdd'] = 1;
			 	   		   var oldParams = config["params"]||{};
		 	   			   //modify by tracywindy 20130109 16:45 添加combobox级联 start
		 	   			   var cascadeConfig = columnConfig["cascade"];
		 	   			   var multiTable    = $me;
		 	   			   var currentTable  = $me.operationTable;
		 	   			   
		 	   			   var tempValue    = defaultValue||config['value'];
		 	   			   var tempRawValue = defaultRawValue||config['rawValue'];
		 	   			   var rowDatas =[];
		 	   			   if("update" == multiTable.flag){
		 	   				  rowDatas = currentTable.getCheckedRowDatas();
	   						  var hiddenMappingName = multiTable.findColumnMappingByName(multiTable,currentTable,columnHiddenName);
	   						  tempValue = rowDatas[0][hiddenMappingName]||"";
	   						  if(!tempValue){
				 	   			 var columnName = columnConfig['name'];
				 	   			 var mappingName = multiTable.findColumnMappingByName(multiTable,currentTable,columnName);
				 	   			 tempRawValue = rowDatas[0][mappingName]||"";
	   						  }
		 	   			   }
			 	   		   config['value']    = tempValue;
			 	   		   config['rawValue'] = tempRawValue;
			 	   		   
		 	   			   if(cascadeConfig && currentTable){
		 	   				   var parentColumnNamesArr   = cascadeConfig["parentColumnNames"]||[];
		 	   				   for(var ii =0;ii<parentColumnNamesArr.length;ii++){
		 	   					   var  parentColumnName  =  parentColumnNamesArr[ii];
		 	   					   var  innerColumnConfig  = multiTable.findColumnConfigByName(multiTable,currentTable,parentColumnName);
		 	   					   var  parentColumnHiddenName = innerColumnConfig["hiddenName"];
		 	   					   var  parentColumnDisplayValue = "";
		 	   					   var  parentColumnHiddenValue  = "";
		 	   					   if("add" == multiTable.flag){
		 	   						   parentColumnDisplayValue = innerColumnConfig["defaultRawValue"]||innerColumnConfig["config"]["rawValue"]||"";
		 	   						   parentColumnHiddenValue  = innerColumnConfig["defaultValue"]||innerColumnConfig["config"]["value"]||"";
		 	   					   }else if("update" == multiTable.flag){
		   							   var hiddenMappingName = multiTable.findColumnMappingByName(multiTable,currentTable,parentColumnHiddenName);
		   							   parentColumnHiddenValue = rowDatas[0][hiddenMappingName]||"";
		   							   if(!parentColumnHiddenValue){
					 	   				  var mappingName = multiTable.findColumnMappingByName(multiTable,currentTable,parentColumnName);
					 	   				  parentColumnDisplayValue = rowDatas[0][mappingName]||"";
		   							   }
		 	   					   }
		 	   					   
		 	   					   oldParams["displayValue_"+parentColumnName]       = parentColumnDisplayValue;
		 	   					   oldParams[parentColumnName]                       = parentColumnHiddenValue;
		 	   					   oldParams[parentColumnHiddenName]                 = parentColumnHiddenValue;
		 	   				   }
		 	   			   }
		 	   			   config["params"] = oldParams;
		 	   			   //modify by tracywindy 20130109 16:45 添加combobox级联 finish
		 	   			   
			 	   		   /*config['init'] = config['init']||function(combo,currentTable,multiTable,columnConfig){
			 	   			   var rowDatas  = currentTable.getCheckedRowDatas();
			 	   			   if(rowDatas.length > 0)
			 	   			   {
			 	   				  if("update" == multiTable.flag)
			 	   				  {
			 	   					 var hiddenName = columnConfig['hiddenName'];
			 	   					 if('local'==columnConfig['config']['loadMode']){
				 	   						if(columnConfig["initValue"]){
				 	   							columnConfig["initValue"](combo,rowDatas[0],multiTable,multiTable);
				 	   						} 
				 	   						else{
				 	   							//if(hiddenName)
				 	   							{
				 	   							   var hiddenMappingName = multiTable.findColumnMappingByName(multiTable,currentTable,hiddenName);
				 	   							   var hiddenValue = rowDatas[0][hiddenMappingName];
				 	   							   if(hiddenValue){
				 	   								 combo.setValue(hiddenValue);
				 	   							   }
				 	   							   else{
				 				 	   				  var columnName = columnConfig['name'];
				 				 	   				  var mappingName = multiTable.findColumnMappingByName(multiTable,currentTable,columnName);
				 	   								  combo.setRawValue(rowDatas[0][mappingName]);
				 	   							   }
				 	   							}
				 	   						}
			 	   					 }else{
					 	   					combo.loadComplete = function(){
					 	   						if(columnConfig["initValue"]){
					 	   							columnConfig["initValue"](combo,rowDatas[0],multiTable,multiTable);
					 	   						}
					 	   						else{
					 	   							//if(hiddenName)
					 	   							{
						 	   							   var hiddenMappingName = multiTable.findColumnMappingByName(multiTable,currentTable,hiddenName);
						 	   							   var hiddenValue = rowDatas[0][hiddenMappingName];
						 	   							   if(hiddenValue){
						 	   								 combo.setValue(hiddenValue);
						 	   							   }
						 	   							   else{
						 				 	   				  var columnName = columnConfig['name'];
						 				 	   				  var mappingName = multiTable.findColumnMappingByName(multiTable,currentTable,columnName);
						 	   								  combo.setRawValue(rowDatas[0][mappingName]);
						 	   							   }
						 	   						}
					 	   						}
					 	   						
					 	   					};
			 	   					 }
			 	   				  }
			 	   			   }
			 	   			   
			 	   		   };
			 	   		   
			 	   		   var initLoadCompteteFunc = (function(multiTable,columnConfig,config){
			 	   			   return function(){var combo = this;config['init'](combo,$me.operationTable,multiTable,columnConfig);};
			 	   		   })($me,columnConfig,config);
			 	   		   config['comboLoadFunc'] = initLoadCompteteFunc;*/
			 	   		   
			 	   		   var $$me = this;
			 	   		   var selectFunc  = (function($$me,columnConfig,config){
			 	   			   return function(combo,rowData){
				 	   			   if(columnConfig['hiddenName'])
				 	   			   {
				 	   				  jQuery("#"+$$me.windowId+" form input[name='"+columnConfig['hiddenName']+"']").val(this.getValue());
				 	   			   }
				 	   			   jQuery("#"+$$me.windowId+" form input[name='"+columnConfig['name']+"']").val(this.getRawValue());
					 	   		   /*cascade:{
					 	   			  parentColumnNames:[],
					 	   			  childrenColumnNames:[]
					 	   		   }*/
				 	   			   //modify by tracywindy 20130109 16:45 添加combobox级联 start
				 	   			   var cascadeConfig = columnConfig["cascade"];
				 	   			   if(cascadeConfig){
				 	   				   //var parentColumnNamesArr   = cascadeConfig["parentColumnNames"]||[];
				 	   				   var childrenColumnNamesArr = cascadeConfig["childrenColumnNames"]||[];
				 	   				   for(var ii =0;ii<childrenColumnNamesArr.length;ii++){
				 	   					   var childrenColumnName = childrenColumnNamesArr[ii];
				 	   					   var childrenComboId = ($$me.windowId+"_queryMultiComboBox_"+childrenColumnName);
				 	   					   var childrenCombo       = getTracywindyObject(childrenComboId);
				 	   					   var cascadeParams = {};
				 	   					   cascadeParams[ "displayValue_"+columnConfig["name"] ]  = combo.getRawValue();
				 	   					   cascadeParams[ columnConfig["name"] ] = combo.getValue();
				 	   					   cascadeParams[ columnConfig["hiddenName"] ] = combo.getValue();
				 	   					   childrenCombo.setParams(cascadeParams);
				 	   					   childrenCombo.reload();
				 	   				   }
				 	   			   }
				 	   			   //modify by tracywindy 20130109 16:45 添加combobox级联 finish
				 	   			   if(config['onSelected']){
				 	   				   config['onSelected'](combo,rowData,$$me,columnConfig);
				 	   			   };
			 	   			   };
			 	   		   })($$me,columnConfig,config);
			 	   		   config['onSelect'] = selectFunc;
			 	   		   this.comboboxArray.push(config);
			 	   		   break;
			 	   	   }
			 	   }
			 	   
			 	   tableWindow_html+='		            <tr class="'+trClassName+'" '+styleDisplay+' >';
			 	   tableWindow_html+='		 	            <td class="td-content-title" style="width:'+this.labelWidth+'px;">'+requireContent+labelContent+'</td>';
			 	   tableWindow_html+='	                    <td class="td-content">'+valueContent+'</td>';
				   tableWindow_html+='	                </tr>';
		 	   }
			   //动态填充结束
		 	   tableWindow_html+='		        </table>';
		 	   tableWindow_html+='	        </form>';
		 	   tableWindow_html+='        </div>';
		 	   tableWindow_html+='    </center>';
		 	   tableWindow_html+='</div>';
		 	   
			   //jQuery(document.body).append(tableWindow_html);
		 	   tableWindow_html+='<div style="text-align:center;width='+this.windowWidth+'px;display:none;" id="'+this.windowId+'dlg-buttons">';
		 	   tableWindow_html+='	<a href="javascript:void(0);" style="margin-top:5px;" id="'+this.id+"SubmitButton"+'" class="btn btn-primary"  >确定</a>';
		 	   tableWindow_html+='	<a href="javascript:void(0);" style="margin-top:5px;margin-left:20px;" id="'+this.id+"CancelButton"+'" class="btn btn-primary"  >取消</a>';
		 	   //tableWindow_html+='	<a href="javascript:void(0);" style="margin-top:5px;margin-left:20px;" class="btn btn-primary"  >取消<a>';
		 	   tableWindow_html+='</div>';
		 	   if(this.isShowCopyCount){
		 		   if(!jQuery("#"+this.windowId+"_copyCountWindow")[0]){
		 			   tableWindow_html+='<div title="复制的条数" buttons="#'+this.windowId+'-copyCount-dlg-buttons" id="'+this.windowId+'_copyCountWindow" modal="true" style="padding:10px;display:none;width:200px;">复制的条数：<input id="'+this.windowId+'_inputCopyCount" style="border:1px solid #DDD;width:80px;" value="1"/></div>';
			 		   tableWindow_html+='<div style="text-align:center;width=200px;display:none;" id="'+this.windowId+'-copyCount-dlg-buttons">';
				 	   tableWindow_html+='	<a href="javascript:void(0);" style="margin-top:5px;" id="'+this.id+"CopyCountSubmitButton"+'" class="btn btn-primary"  >确定</a>';
				 	   tableWindow_html+='	<a href="javascript:void(0);" style="margin-top:5px;margin-left:20px;" id="'+this.id+"CopyCountCancelButton"+'" class="btn btn-primary"  >取消</a>';
				 	   tableWindow_html+='</div>';
		 		   }
		 	   }
		 	   jQuery(document.body).append(tableWindow_html);
		 	   if(document.getElementById(this.windowId+"dlg-buttons"))
			   var $$this = this;
			   jQuery(function(){
				   jQuery("#"+$$this.windowId +" form input.Wdate").click(function(e){
					   WdatePicker(this,{readOnly:true,dateFmt:this.getAttribute('dateFormat')});
				   });		
				   $$this.comboboxs = [];
				   for(var i=0;i<$$this.comboboxArray.length;i++)
				   {
						  var combo_select = new tracywindyComboBox($$this.comboboxArray[i]);
						  $$this.comboboxs.push(combo_select);
				   }
			   });
	    }
	};
	/*迭代配置
	column配置:{
	cascade:{
		  parentColumnNames:["contactname"],
		  childrenColumnNames:["custname"]
	   }
	}
	*/
	this.setColumnConfig = function(config,nameOrIndex){
		tracywindyTable.prototype.setColumnConfig.call(this,config,nameOrIndex);
	};
	this.setReadOnly   = function(isReadOnly,nameOrIndex){
		this.setColumnConfig({readOnly:isReadOnly,isViewHistoryTask:isReadOnly},nameOrIndex); 
		
	};
	this.commonCreateTableWindow = commonCreateTableWindow;
	this.commonCreateTableWindow();
	//初始化window
	var commonTableWindowReset = config.commonTableWindowReset || function(){
		if(this.beforeCreateTableWindowCallback){
			this.beforeCreateTableWindowCallback(this);
		}
		this.commonCreateTableWindow();
		var thisForm = jQuery("#"+this.windowId+" form")[0];
		var formElements = thisForm.elements;
		for(var a=0;a<formElements.length;a++){
			var formElement = formElements[a];
			var inputName = formElement.name;
			if(inputName){
				var config = this.configRemains[inputName];
				if(config){
					var defaultValue = config['defaultValue']||'';
					formElement.value = defaultValue;
				}
			} 
		}
		for(var i= 0;i<this.comboboxs.length;i++)
		{
			var combo = this.comboboxs[i];
			if("update"==this.flag)
			{
				combo.comboLoadFunc();
			}
			else if("add" == this.flag){
				/*if(!combo.value){
					combo.setValue(combo.value);
				}else if(!combo.rawValue){
					combo.setRawValue(combo.rawValue);
				}*/
				//combo.setValue(combo.getValue());
			}
		}
		if(this.resetFromOperCallBack){
			this.resetFromOperCallBack(this.flag,thisForm,this);
	    }
	};
	this.commonTableWindowReset = commonTableWindowReset;
	var showCommonTableWindow = config.showCommonTableWindow||function(){
	       jQuery("#"+this.windowId).show();
		   this.top+= Math.max(document.documentElement.scrollTop,document.body.scrollTop);
		   var config = {
					zIndex:999999,
		        	draggable:true,
		        	top:this.windowTop,
		        	resizable:true,
		        	shadow:true,
		        	modal:true,
		        	inline:false,
		        	collapsible:false,
		        	minimizable:false,
		        	maximizable:false,
		        	closable:true
		   };
		   jQuery("#"+this.windowId).dialog(config);
		   jQuery("#"+this.windowId).dialog("open");
	};
	this.showCommonTableWindow = showCommonTableWindow;
	//定义操作方法
	this.addOperCallBack = function($me,currentTable){
		   var rowData = jQuery("#"+$me.windowId+" form").tracywindySerializeFormToJsonObject(true);
	 	   var realRowData = {};
		   for(var columnName in rowData){
			  var mappingName = $me.findColumnMappingByName($me,currentTable,columnName);
			  if(null == mappingName)continue;
			  realRowData[mappingName] = rowData[columnName];
		   }
		currentTable.addRowsAt(0,realRowData);
		if(config.addOperCallBack)config.addOperCallBack($me,currentTable, realRowData);
		jQuery("#"+$me.windowId).window("close");
	};
	this.copyOperCallBack = function($me,currentTable){
		if(this.isShowCopyCount){
			jQuery("#"+this.windowId+"_inputCopyCount").val("1");
			var clickFunc = (function($me,currentTable){
				return function(e){
					var copyCountVal = jQuery("#"+$me.windowId +"_inputCopyCount").val();
					var copyCount = -1;
					if(copyCountVal){
						copyCountVal = copyCountVal.trim();
						copyCount = parseInt(copyCountVal);
						if(isNaN(copyCount) ){
							alert("复制条数必须是整数");
							return;
						}else{
							if(copyCount<=0){
								alert("复制条数必须大于等于1");
								return;
							}
						}
					}
					var newRowDatas = new Array();
					var rowDatas  = currentTable.getCheckedRowDatas();
					var fromRowDatasLength = rowDatas.length;
					for(var ci=0;ci<copyCount;ci++){
						for(var i=0;i<fromRowDatasLength;i++) {
							var newRowData = {};
							var fromRowData = rowDatas[i];
							for(var p in fromRowData) {
								newRowData[p] = fromRowData[p];
							}
							newRowData[$me.idField] = '';
							newRowDatas.push(newRowData);
							if(config.copyOperChangeDataCallBack){
								config.copyOperChangeDataCallBack($me,currentTable,newRowData, fromRowData);
							}
						}
					}
					currentTable.addRowsAt(0,newRowDatas);
					if(config.copyOperCallBack)config.copyOperCallBack($me,currentTable);
					jQuery("#"+$me.windowId+"_copyCountWindow").dialog("close");
				};
			})($me,currentTable);
			jQuery("#"+$me.id+"CopyCountSubmitButton")[0].onclick = clickFunc;
			var cancelClickFunc = (function($me,currentTable){
				return function(e){
					jQuery("#"+$me.windowId+"_copyCountWindow").dialog("close");
				};
			})($me,currentTable);
			jQuery("#"+$me.id+"CopyCountCancelButton")[0].onclick = cancelClickFunc;
			jQuery("#"+$me.windowId+"_copyCountWindow").show();
			jQuery("#"+$me.windowId+"_copyCountWindow").dialog({top:200});
			jQuery("#"+$me.windowId+"_copyCountWindow").dialog("open");
		} else {
			var rowDatas  = currentTable.getCheckedRowDatas();
			var newRowDatas = new Array();
			for(var i=0;i<rowDatas.length;i++) {
				var newRowData = {};
				var fromRowData = rowDatas[i];
				for(var p in fromRowData) {
					newRowData[p] = fromRowData[p];
				}
				newRowData[$me.idField] = '';
				newRowDatas.push(newRowData);
			}
			currentTable.addRowsAt(0,newRowDatas);
			if(config.copyOperCallBack) {
				config.copyOperCallBack($me,currentTable);
			}
		}
	};
	this.findColumnMappingByName = function($me,currentTable,columnName){
		var columns = currentTable.columns;
		for(var i = 0;i<columns.length;i++){
			var column = columns[i];
			if(columnName == column["name"]){
				return column["mapping"];
			}
		}
		return null;
	};
	this.findColumnNameByMapping = function($me,currentTable,mappingName){
		var columns = currentTable.columns;
		for(var i = 0;i<columns.length;i++){
			var column = columns[i];
			if(mappingName == column["mapping"]){
				return column["name"];
			}
		}
		return null;
	};
	this.updateOperCallBack = function($me,currentTable){
		  var rowData = jQuery("#"+$me.windowId+" form").tracywindySerializeFormToJsonObject(true);
		  var currentOperRowData  = currentTable.getCheckedRowDatas()[0];
		  for(var p in rowData)
		  {
			 var cdata = rowData[p];
			// currentOperRowData[p] = cdata;
			 var mapping = this.findColumnMappingByName($me,currentTable,p);
			 if(null == mapping)continue;
			 currentOperRowData[mapping] = cdata;
		  }
		  createDataTable(currentTable);
		if(config.updateOperCallBack)config.updateOperCallBack($me,currentTable,currentOperRowData);//updatedRowData
		jQuery("#"+$me.windowId).window("close");
	};
	this.removeOperCallBack = function($me,currentTable){
		   var rowDatas  = currentTable.getCheckedRowDatas();
		   if(0 == rowDatas.length)
		   {
			   alert("请选择要删除的数据");
			   return;
		   }
		   if (window.confirm('确认删除选中数据么？')){
			   var confirmRemove=true;
			   if(config.confirmRemoveCallBack){
				   confirmRemove=config.confirmRemoveCallBack($me,currentTable);
			   }
			  if(confirmRemove){
			     for(var i=0;i<rowDatas.length;i++)
		         {
				   var rowData = rowDatas[i];
				   var removeIndex = rowData.rowIndex ;
				   currentTable.removeRowAt(removeIndex);
		         }
			  }
		  }
		if(config.removeOperCallBack)config.removeOperCallBack($me,currentTable,rowDatas);
	};
	var $toolsMapping = {
			"新增":{
				html:'<font style="color:red;font-weight:BOLD;">'+"新增"+'</font>',
				iconCls:'icon-'+"add",
				handler:function(currentTable){
			         $me.flag = "add";
			         if(!$me.commonValidateTableDataPassed(null,$me))return;
			         $me.commonTableWindowReset();
			         var clickFunc = (function($me,currentTable){
			        	 return function(e){
			        		 if($me.validateForm($me,currentTable)){
				        		$me.addOperCallBack($me,currentTable);
			        		 }
			        	 };
			         })($me,currentTable);
			         jQuery("#"+$me.id+"SubmitButton")[0].onclick = clickFunc;
			         var cancelClickFunc = (function($me,currentTable){
			        	 return function(e){
			        		 jQuery("#"+$me.windowId).dialog("close");
			        	 };
			         })($me,currentTable);
			         jQuery("#"+$me.id+"CancelButton")[0].onclick = cancelClickFunc;
			         $me.showCommonTableWindow();
				}
	        },
			"复制":{
				html:'<font style="color:red;font-weight:BOLD;">'+"复制"+'</font>',
				iconCls:'icon-'+"copy",
				handler:function(currentTable){
				$me.flag = "copy";
				   var rowDatas  = currentTable.getCheckedRowDatas();
	               if($me.commonValidateTableDataPassed(rowDatas,$me)){
	            	   $me.copyOperCallBack($me,currentTable);
	               }
				}
	        },
			"修改":{
				html:'<font style="color:red;font-weight:BOLD;">'+"修改"+'</font>',
				iconCls:'icon-'+"update",
				handler:function(currentTable){
				   $me.flag = "update";
				   var rowDatas  = currentTable.getCheckedRowDatas();
		           if($me.commonValidateTableDataPassed(rowDatas,$me)){
		        	   if(rowDatas.length>1){
		        		  alert("只能选择单条记录进行修改");  
		        		  return;
		        	   };
		        	   $me.commonTableWindowReset();
		        	   var realRowData = {};
		        	   for(var mappingName in rowDatas[0]){
		        		  var columnName = $me.findColumnNameByMapping($me,currentTable,mappingName);
		        		  if(null == columnName)continue;
		        		  realRowData[columnName] = rowDatas[0][mappingName]||rowDatas[0][columnName];
		        	   }
			   		   jQuery('#'+$me.windowId+" form").form('load',realRowData);
					   if($me.updateFromOperCallBack){
					   	  $me.updateFromOperCallBack("update",jQuery('#'+$me.windowId+" form")[0],$me,realRowData);
					   }
				       var clickFunc = (function($me,currentTable){
				        	 return function(e){
				        		 if($me.validateForm($me,currentTable)){
					        		$me.updateOperCallBack($me,currentTable);
				        		 }
				        	 };
				         })($me,currentTable);
				       jQuery("#"+$me.id+"SubmitButton")[0].onclick = clickFunc;
				         var cancelClickFunc = (function($me,currentTable){
				        	 return function(e){
				        		 jQuery("#"+$me.windowId).dialog("close");
				        	 };
				         })($me,currentTable);
				         jQuery("#"+$me.id+"CancelButton")[0].onclick = cancelClickFunc;
				       $me.showCommonTableWindow();
		           }
				}
	        },
			"删除":{
				html:'<font style="color:red;font-weight:BOLD;">'+"删除"+'</font>',
				iconCls:'icon-'+"remove",
				handler:function(currentTable){
				$me.flag = "remove";
				   var rowDatas  = currentTable.getCheckedRowDatas();
			       if($me.commonValidateTableDataPassed(rowDatas,$me)){
		        		$me.removeOperCallBack($me,currentTable);
			       }
				}
	        },
			"导出模板":{
				html:'<font style="color:red;font-weight:BOLD;">'+"导出模板"+'</font>',
				iconCls:'icon-'+"export",
				handler:function(currentTable){
					exportExcelData(currentTable.id);
			    }
	        },
			"导入模板":{
				html:'<font style="color:red;font-weight:BOLD;">'+"导入模板"+'</font>',
				iconCls:'icon-'+"import",
				handler:function(currentTable){
					importExcelData(currentTable.id);
				}
	        }
	};
	var $tools    = this.otherTools;
	var toolsArr  = this.tools.split("|");
	for(var i = 0;i<toolsArr.length;i++)
	{
		var toolsItem         = toolsArr[i];
		if(toolsItem){
			var toolsItemObj = $toolsMapping[toolsItem.replace(/\s/g,"")];
			if(toolsItemObj){
				$tools.push(toolsItemObj);
				$tools.push('-');
			}
		}
	}
	var $config = {
	        renderTo:this.renderTo,
	        id:this.id,
	        width:this.width,
	        height:this.height,
	        isAutoHeight:this.isAutoHeight,
	        isCheck:this.isCheck,
	        isRank:this.isRank,
	        checkOnly:this.checkOnly,
	        border:this.border,
	        showHeader:this.showHeader,
	        loadMode:this.loadMode,
	        isStatistic:this.statisticColumnNames.length > 0,
	        datas:this.datas,
	        isFit:this.isFit,
	        //isPage:false,
	        isExportTitle:this.isExportTitle,
	        statisticColumnNames:this.statisticColumnNames,
	        columns:this.columns,
	    	tools:$tools,
	        callBack:this.callBack,
	        params:this.params
	     };
	for(var p in $config){
		config[p] = $config[p];
	}
    var table = new tracywindyTable(config);
    this.operationTable = table;
    return table;
};