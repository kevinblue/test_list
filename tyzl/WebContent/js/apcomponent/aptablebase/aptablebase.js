define(function(require, exports, module) {
	// add prototype method named trim for String
	var apUtil = require("js/apcomponent/aputil/aputil.js");
	var apQueryInput = require("js/apcomponent/apqueryinput/apqueryinput.js");
	var locale = require("js/apcomponent/aptable/locale/{currentLocale}.js");
	var ApTableBase = {
		tableAddOper : function(miniTable, columnsConfig, rowData, config) {
			miniTable.editFormItemOperFlag = "add";
			if (true == config.isRowEditing) {
				/*
				 * editor:{ width:'100%', type:'TextBox', onenter:function(){
				 * mini.get("test").commitEdit(); } }
				 */
				miniTable.addRow(rowData, 0);
				miniTable.commitEdit();
				miniTable.beginEditRow(rowData);
			} else {
				var columnsEditFormWindow = this.initColumnsEditFormWindowPanel(miniTable, columnsConfig, config);
				var editFormPopupWindowFormId = config.editFormId;
				var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
				jQuery("#" + editFormItemOperFlagId).val("add");
				var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
				if(config.beforeShowWindowCallBack)config.beforeShowWindowCallBack(miniTable,miniForm, "add");
				columnsEditFormWindow.showAtPos('center', config.top || 150);
				this.lazyRenderCombobox(config.comboRenders);
				this.lazyRenderQueryInput(config.queryInputRenders);
				if(config.afterShowWindowCallBack)config.afterShowWindowCallBack(miniTable,miniForm, "add");
			}
		},
		tableEditOper : function(miniTable, columnsConfig, selectedRowData, config) {
			var selectedDataSize = this.tableSelectedDataSize(miniTable);
			
			miniTable.editFormItemOperFlag = "edit";
			if (0 == selectedDataSize) {
				mini.alert(locale['editAlert']);
				return;
			}
			if (1 < selectedDataSize) {
				mini.alert(locale['editAlert2']);
				return;
			}
			
			for(var i=0;i<columnsConfig.length;i++){
				if(columnsConfig[i].dateFormat){
					var dateFormat=columnsConfig[i].dateFormat||"";
					var temp=selectedRowData[columnsConfig[i].field]||"";
					if(dateFormat!=""&&temp!=""){
						if(typeof temp=="object"){
           					  selectedRowData[columnsConfig[i].field]=mini.formatDate(selectedRowData[columnsConfig[i].field],dateFormat)
					     }
                      }
				}
			}
			if (true == config.isRowEditing) {
				miniTable.commitEdit();
				miniTable.beginEditRow(selectedRowData);
			} else {
				var columnsEditFormWindow = this.initColumnsEditFormWindowPanel(miniTable, columnsConfig, config);
				var editFormPopupWindowFormId = config.editFormId;
				var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
				jQuery("#" + editFormItemOperFlagId).val("edit");
				var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
				for(var p in selectedRowData){
					if(typeof(selectedRowData[p]) == 'string'){
						selectedRowData[p] = selectedRowData[p].replace(/<br\s*\/>/gim,'\n').replace(/&#39;/gim,'\'').replace(/&quot;/gim,'\"');
						/*if(/^[-\+]?\d+[\.]?([0-9]{1,2})?$/.test(selectedRowData[p].replace(/,/gim,''))){
							selectedRowData[p] = formatNumberThousand(selectedRowData[p].replace(/,/gim,''));
						}*/
					}
				}
				/*********/
				//编辑的将要千分位的数据，回显为千分位
				miniForm.setData(selectedRowData);
				var fields = miniForm.getFields();
				for(var index =0;index<fields.length;index++){
					if(fields[index].vtype == 'thousand'){
						fields[index].setValue(formatNumberThousand(fields[index].getValue().replace(/,/gim,'')));
					}
				}
				var flag = true;
				if(config.beforeShowWindowCallBack){
					flag = config.beforeShowWindowCallBack(miniTable,miniForm, "edit") ;
				}
				if(flag){
					columnsEditFormWindow.showAtPos('center', config.top || 150);
					this.lazyRenderCombobox(config.comboRenders);
					this.lazyRenderQueryInput(config.queryInputRenders);
					if(config.afterShowWindowCallBack)config.afterShowWindowCallBack(miniTable,miniForm, "edit");
				}
				
			}
		},
		lazyRenderCombobox : function(comboRenders) {
			// 渲染combobox
			var timeOutFunc = (function(comboRenders) {
				return function() {
					for ( var renderTo in comboRenders) {
						// var url = comboRenders[renderTo];
						// mini.get(renderTo).load(url);

						var comboConfig = comboRenders[renderTo];
						var combo = mini.get(renderTo);
						if(comboConfig.lazyLoad == true){
							var clickFunc = (function(combo,comboConfig){
								return function(e){
									if(!combo.isLoadData){
										combo.set(comboConfig);
										combo.isLoadData = true;
									}
								}
							})(combo,comboConfig);
							combo.on("click",clickFunc);
						}else{
							combo.set(comboConfig);
						}
						if(mini.get(renderTo + "name")){
							combo.setText(mini.get(renderTo + "name").getValue());
						}
						
					}
				};
			})(comboRenders);
			setTimeout(timeOutFunc, 100);
		},
		lazyRenderQueryInput: function(queryInputRenders){
			//渲染QueryInput
			var timeOutFunc = (function(queryInputRenders) {
				return function() {
					for ( var id in queryInputRenders) {
						var queryInputConfig = queryInputRenders[id];
						queryInputConfig.id = id;
						if(mini.get(id + "name")){
							mini.get(id).setText(mini.get(id + "name").getValue());
						}
						new apQueryInput(queryInputConfig);
					}
				};
			})(queryInputRenders);
			setTimeout(timeOutFunc, 200);
		},
		tableRemoveOper : function(miniTable, columnsConfig, selectedRowDatas, config) {
			var selectedDataSize = this.tableSelectedDataSize(miniTable);
			var me = this;
			if (0 == selectedDataSize) {
				mini.alert(locale['removeAlert'] );
				return;
			}
			if(config.confirmRemoveCallBack){
				if(!config.confirmRemoveCallBack(miniTable, selectedRowDatas)){return;}
			}
			mini.confirm(locale['removeInfo'], locale['confirmOperation'], function(buttonText) {
				if ("ok" == buttonText) {
					if ((true != config.isRowEditing) && ("true" == (config.remoteOper + ""))) {
						var removedRows = selectedRowDatas;
						var removedIds = [];
						for ( var a = 0; a < removedRows.length; a++) {
							var removedRow = removedRows[a];
							removedIds.push(removedRow["id"]);
						}
						var ajaxData = {};
						ajaxData["id"] = removedIds.join(",");
						me.tableRemoteOper("remove", ajaxData, miniTable, config);
					} else {
						miniTable.removeRows(selectedRowDatas, false);
						if(config.removeOperCallBack)config.removeOperCallBack(miniTable,selectedRowDatas);
					}
				}
			});
		},
		tableCopyOper : function(miniTable, columnsConfig, selectedRowDatas, config) {
			if ((true != config.isRowEditing) && ("true" == (config.remoteOper + ""))) {
				var selectedDataSize = this.tableSelectedDataSize(miniTable);
				miniTable.editFormItemOperFlag = "copy";
				if (0 == selectedDataSize) {
					mini.alert(locale['copyAlert']);
					return;
				}
				if (1 < selectedDataSize) {
					mini.alert(locale['copyAlert2']);
					return;
				}
				var columnsEditFormWindow = this.initColumnsEditFormWindowPanel(miniTable, columnsConfig, config);
				var editFormPopupWindowFormId = config.editFormId;
				var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
				jQuery("#" + editFormItemOperFlagId).val("copy");
				var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
				var currentCopyData = mini.clone(selectedRowDatas[0]);
				delete currentCopyData["id"];
				miniForm.setData(currentCopyData);
				columnsEditFormWindow.showAtPos('center', config.top || 150);
				this.lazyRenderCombobox(config.comboRenders);
			} else {
				this.initCopyCountPopupWindow(miniTable, config);
			}
		},
		initCopyCountPopupWindow : function(miniTable, config) {
			var selectedDataSize = this.tableSelectedDataSize(miniTable);
			if (0 == selectedDataSize) {
				mini.alert(locale['copyAlert']);
				return;
			}
			var copyCountPopupWindowId = config.id + "_copyCountPopupWindow";
			var copyCountInputId = copyCountPopupWindowId + "_input";
			var copyCountButtonOperConfirmId = copyCountPopupWindowId + "_buttonConfirm";
			var copyCountButtonOperCancelId = copyCountPopupWindowId + "_buttonCancel";
			if (0 == jQuery("#" + copyCountPopupWindowId).length) {
				var copyCountPopupWindowWidth = 300;
				var copyCountPopupWindowHeight = 120;
				var htmlArr = [];
				htmlArr.push("<div  title='"+locale['copy']+"' width='" + copyCountPopupWindowWidth + "' height='" + copyCountPopupWindowHeight + "' iconCls='icon-node' class='mini-window' id='" + copyCountPopupWindowId + "'>");
				htmlArr.push("<div class='aptable-copy-popup-window'><label for='" + copyCountInputId + "$text'>"+locale['copetime']+"：</label>");
				htmlArr.push("<input required='true' vtype='int' id='" + copyCountInputId + "' class='mini-textbox' value='1'/></div>");
				htmlArr.push("<div style='text-align:right;padding-top:5px;padding-right:10px;'><span id='" + copyCountButtonOperConfirmId + "'><a class='mini-button' iconCls='icon-ok'>"+locale['confirm']+"</a></span>&nbsp;&nbsp;<span id='" + copyCountButtonOperCancelId + "'><a class='mini-button' iconCls='icon-cancel'>"+locale['cancel']+"</a></span></div>");
				htmlArr.push("</div>");
				var $copyCountPopupWindow = jQuery(htmlArr.join(""));
				jQuery(document.body).append($copyCountPopupWindow);
				jQuery("#" + copyCountButtonOperConfirmId).css({
					'display' : 'inline-block'
				});
				jQuery("#" + copyCountButtonOperCancelId).bind("click", function(e) {
					e.stopPropagation();
					mini.get(copyCountPopupWindowId).hide();
				});
				jQuery("#" + copyCountButtonOperConfirmId).bind("click", function(e) {
					e.stopPropagation();
					var miniCopyInput = mini.get(copyCountInputId);
					if (!miniCopyInput.validate()) {
						return false;
					}
					var copyCount = parseInt(miniCopyInput.getValue());
					var idFieldName = "id";

					var exceptionFieldNames = [];
					exceptionFieldNames.push(idFieldName);
					exceptionFieldNames.push("_id");
					exceptionFieldNames.push("_uid");
					exceptionFieldNames.push("_state");
					if (config.idField) {
						exceptionFieldNames.push(config.idField);
					}
					var selectedRowDatas = miniTable.getSelecteds();
					var newRowDatas = [];

					//miniTable.addRows(newRowDatas);
					for ( var i = 0; i < copyCount; i++) {
						for ( var j = 0; j < selectedRowDatas.length; j++) {
							var newRowData = {};
							var selectedRowData = selectedRowDatas[j];
							mini.copyTo(newRowData, selectedRowData);

							for ( var k = 0; k < exceptionFieldNames.length; k++) {
								try {
									delete newRowData[exceptionFieldNames[k]];
								} catch (e) {
								}
							}
							newRowDatas.push(newRowData);
						}
					}
					if(config.confirmCopyCallBack){
						config.confirmCopyCallBack(miniTable, newRowDatas);
					}else{
						miniTable.addRows(newRowDatas, 0);
					}
					mini.get(copyCountPopupWindowId).hide();
					if(config.copyOperCallBack)config.copyOperCallBack(miniTable, newRowDatas);
					miniTable.saveDataToInput();
				});
				mini.parse($copyCountPopupWindow[0]);
			}
			var miniCopyInput = mini.get(copyCountInputId);
			miniCopyInput.setValue("1");
			mini.get(copyCountPopupWindowId).show();
		},
		initColumnsElements : function(renderType, columnsConfig, config, miniTable, isDestroy) {
			var isQueryArea = ("query" == renderType);
			// initInfo
			var columnsMapping = this.getColumnsEditFormMapping(columnsConfig, config);
			// try{miniTable.columnsMapping = columnsMapping;}catch(e){}
			// miniTable.columnsMapping = columnsMapping;
			var columnsEditFormMapping = columnsMapping["columnsEditFormMapping"];
			var fillEditFormMapping = isQueryArea ? columnsMapping["queryAreaFillEditFormMapping"] : columnsMapping["fillEditFormMapping"];
			var columnsKey = columnsMapping["columnsKey"];

			var htmlArray = [];
			var otherAttributes = "";

			var labelMaxWidth = 0;
			var editorMaxWidth = 0;
			var cascaseParamSuffix = config.cascaseParamSuffix || "Cascade";
			var comboRenders = {};
			var queryInputRenders = {};
			if (!isQueryArea) {
				config.comboRenders = comboRenders;
				config.queryInputRenders = queryInputRenders;
			}
			var columnsLen = columnsKey.length;
			var i = -1;
			// var globalTrSuffix = "";
			for ( var iii = 0; iii < columnsLen; iii++) {
				var columnKey = columnsKey[iii];
				var columnConfig = columnsEditFormMapping[columnKey];
				var header = columnConfig["header"];
				var formEditorConfig = isQueryArea ? columnConfig["queryConfig"] : columnConfig["formEditorConfig"];
				if (!formEditorConfig)
					continue;
				i++;
				var editFormItemId = formEditorConfig["id"];
				var labelWidth = formEditorConfig["labelWidth"] || 80;
				labelMaxWidth = Math.max(labelMaxWidth, labelWidth);
				var editorWidth = formEditorConfig["width"] || 150;
				editorMaxWidth = Math.max(editorMaxWidth, editorWidth);
				var formEditType = formEditorConfig["type"] || "text";
				var isDataRange = (isQueryArea && (formEditorConfig["range"]));
				otherAttributes = " label=" + header + " ";
				otherAttributes += (formEditorConfig["otherAttributes"] || "").replace(/"/g, "'");
				for ( var p in formEditorConfig) {
					var currentValueObj = formEditorConfig[p];
					var attrName = p;
					var attrVal = currentValueObj;// || ""
					
					if (("type" == p) || ("url" == p) || ('object' == typeof (currentValueObj)) || ('function' == typeof (currentValueObj))) {
						continue;
					}
					
					attrName = ("defaultValue" == attrName) ? "value" : attrName;
					attrName = ("readOnly" == attrName) ? "readonly" : attrName;
					if ("readonly" == attrName) {
						attrName = "enabled";
						attrVal = !attrVal;
					}
					if (isDataRange && (("id" == p) || ("name" == p) || ("value" == p) || ("defaultValue" == p))) {
						// attrVal += "End";
						continue;
					}
					otherAttributes += (" " + attrName + "='" + attrVal + "' ");
				}

				if(formEditType == 'date' || formEditType == 'month' || formEditType == 'combobox'){
					if(otherAttributes.indexOf("allowInput") == -1){
						otherAttributes += " allowInput='false' ";
					}
				}
				
				if (isDestroy) {
					try {
						mini.get(editFormItemId).destroy();
					} catch (e) {
					}
				}
				var miniClassName = "item-hidden";

				if (formEditType == 'text') {
					miniClassName = "mini-textbox";
 
				}else if(formEditType == 'thousand'){
					//生成输入框时输入值千分位
					miniClassName = "mini-textbox";
					var oldonkeyup = formEditorConfig.onkeyup;
					if(!oldonkeyup){
						otherAttributes += " onkeyup='setapcolumnformatvalue' onvalidation='thousandvalidation'";
			      } 
				}else if(formEditType == 'textarea'){
					miniClassName = "mini-textarea";
				} else if(formEditType == "queryinput"){
					var itemConfig = formEditorConfig;
					itemConfig["lazyLoad"] = formEditorConfig["lazyLoad"] || false;
					itemConfig["label"] = header;
					queryInputRenders[editFormItemId] = itemConfig;
					miniClassName = "mini-buttonedit mini-queryinput";
					
				} else if(formEditType == 'buttonedit'){
					miniClassName = "mini-buttonedit";
				} else if (formEditType == 'number' || formEditType == 'int') {
					formEditorConfig["vtype"] = formEditorConfig["vtype"] || "int";
					miniClassName = "mini-spinner";

				} else if (formEditType == 'float' || formEditType == 'double') {
					formEditorConfig["vtype"] = formEditorConfig["vtype"] || "float";
					miniClassName = "mini-textbox";
					
				} else if (formEditType == 'date') {
					formEditorConfig["vtype"] = formEditorConfig["vtype"] || "date";
					miniClassName = "mini-datepicker";
				} else if (formEditType == 'month') {
					formEditorConfig["vtype"] = formEditorConfig["vtype"] || "month";
					miniClassName = "mini-monthpicker";
				}
				else if(formEditType == 'radiobuttonlist'){
					var data = formEditorConfig["data"];
					var encodeData = mini.encode(data);
					otherAttributes += (" data='" + encodeData + "' ");
					miniClassName = "mini-radiobuttonlist";
				} else if(formEditType == 'checkboxlist'){
					var data = formEditorConfig["data"];
					var encodeData = mini.encode(data);
					otherAttributes += (" data='" + encodeData + "' ");
					miniClassName = "mini-checkboxlist";
				}else if (formEditType == 'combobox') {
					var data = formEditorConfig["data"];
					var cascadeConfig = formEditorConfig["cascade"];
					if (!data) {
						/*
						 * var defaultText =
						 * formEditorConfig["defaultText"]||formEditorConfig["text"]||"";
						 * if(defaultText){ var valueField =
						 * formEditorConfig["valueField"]||"id"; var textField =
						 * formEditorConfig["textField"]||"name"; var tempData =
						 * {}; tempData[valueField] =
						 * formEditorConfig["defaultValue"]||formEditorConfig["value"];
						 * tempData[textField] = defaultText;
						 * otherAttributes+=(" data='"+mini.encode(tempData)+"'
						 * "); }
						 */
						var url = formEditorConfig["url"];
						var params = formEditorConfig["params"] || {};
						if (cascadeConfig) {
							var parentFieldNamesArr = cascadeConfig["parentFieldNames"] || [];
							for ( var ii = 0; ii < parentFieldNamesArr.length; ii++) {
								var parentFieldName = parentFieldNamesArr[ii];
								switch (miniTable.editFormItemOperFlag) {
								case "add": {
									var parentFormEditorConfig = columnsEditFormMapping[parentFieldName][isQueryArea ? "queryConfig" : "formEditorConfig"];
									params[parentFieldName + cascaseParamSuffix] = parentFormEditorConfig["defaultValue"] || "";
									break;
								}
								case "edit": {
									var selectedData = miniTable.getSelected();
									params[parentFieldName + cascaseParamSuffix] = selectedData[parentFieldName];
									break;
								}
								default: {
									var parentFormEditorConfig = columnsEditFormMapping[parentFieldName][isQueryArea ? "queryConfig" : "formEditorConfig"];
									params[parentFieldName + cascaseParamSuffix] = parentFormEditorConfig["defaultValue"] || "";
									break;
								}
								}
							}
						}
						url = apUtil.getParamsUrl(url, params);
						if (!isQueryArea) {
							// comboRenders[editFormItemId] = url;
							var itemConfig = {};
							itemConfig["url"] = url;
							itemConfig["onbeforeshowpopup"] = formEditorConfig["onbeforeshowpopup"] || function(ex) {
							};
							itemConfig["lazyLoad"] = formEditorConfig["lazyLoad"] || false;
							comboRenders[editFormItemId] = itemConfig;
						} else {
							otherAttributes += (" url='" + url + "' ");
						}
						// 级联选择
						var comboSelectFuncName = editFormItemId + "_combo_select_func";
						window[comboSelectFuncName] = (function(columnKey, fillEditFormMapping, columnsEditFormMapping, cascadeConfig, me) {
							return function(miniComboBox) {
								var delimiter = miniComboBox.delimiter;
								var textFieldName = miniComboBox.getTextField();
								var selectedText = miniComboBox.getText();
								var selectedValue = miniComboBox.getFormValue();
								var filledColumnFieldNames = fillEditFormMapping[columnKey];
								if (filledColumnFieldNames) {
									var selectedComboDatas = miniComboBox.getSelecteds();

									for ( var k = 0; k < filledColumnFieldNames.length; k++) {
										var filledColumnFieldNameConfig = filledColumnFieldNames[k];
										var filledFormFieldId = filledColumnFieldNameConfig["id"];
										var fillProperty = filledColumnFieldNameConfig["fillProperty"].replace("{textField}", textFieldName);
										var filledValues = new Array();

										for ( var kk = 0; kk < selectedComboDatas.length; kk++) {
											var selectedComboData = selectedComboDatas[kk];
											filledValues.push(selectedComboData[fillProperty] || "");
										}
										mini.get(filledFormFieldId).setValue(filledValues.join(delimiter));
									}

								}
								// 级联处理
								if (cascadeConfig) {
									var childrenFieldNamesArr = cascadeConfig["childrenFieldNames"] || [];
									for ( var ii = 0; ii < childrenFieldNamesArr.length; ii++) {
										var childrenFieldName = childrenFieldNamesArr[ii];
										var childrenFormEditorConfig = columnsEditFormMapping[childrenFieldName][isQueryArea ? "queryConfig" : "formEditorConfig"];
										var childrenComboId = childrenFormEditorConfig["id"];
										var childrenCombo = mini.get(childrenComboId);
										childrenCombo.setValue("");
										var newUrl = childrenFormEditorConfig["url"];
										var oldUrl = childrenCombo.getUrl();
										if (!oldUrl) {
											oldUrl = comboRenders[childrenComboId];
										}
										var cascadeParams = apUtil.getJsonParamsByUrl(oldUrl);
										cascadeParams["displayValue_" + columnKey + cascaseParamSuffix] = selectedText;
										cascadeParams["rawValue_" + columnKey + cascaseParamSuffix] = selectedText;
										cascadeParams[columnKey + cascaseParamSuffix] = selectedValue;
										var url = apUtil.getParamsUrl(newUrl, cascadeParams);
										childrenCombo.load(url);
									}
									var clearFieldNamesArr = cascadeConfig["clearFieldNames"] || [];
									for ( var ii = 0; ii < clearFieldNamesArr.length; ii++) {
										var clearFieldName = clearFieldNamesArr[ii];
										var clearFormEditorConfig = columnsEditFormMapping[clearFieldName][isQueryArea ? "queryConfig" : "formEditorConfig"];
										var clearComboId = clearFormEditorConfig["id"];
										var clearCombo = mini.get(clearComboId);
										clearCombo.setValue("");
									}
								}
							};
						})(columnKey, fillEditFormMapping, columnsEditFormMapping, cascadeConfig, this);
						// 级联操作
						var onvaluechanged = formEditorConfig["onvaluechanged"] || ("window." + comboSelectFuncName + "(this)");
						otherAttributes += ("  onvaluechanged='" + onvaluechanged + "' ");
						// otherAttributes+=("
						// onbeforeshowpopup='if(!this.getUrl()){this.load(\""+url+"\");this.showPopup();}'
						// ");
					} else {
						var encodeData = mini.encode(data);
						otherAttributes += (" data='" + encodeData + "' ");
					}
					miniClassName = "mini-combobox";// "aptable-lazy-combobox";
				} else {
					miniClassName = "item-hidden";
				}
				
				var labelOtherAttributes = "";
				var fieldVisible = ('undefined' != typeof (formEditorConfig["fieldVisible"])) ? formEditorConfig["fieldVisible"] : columnConfig["visible"];
				if (!fieldVisible) {
					labelOtherAttributes = " style='display:none;'  ";
					otherAttributes += " type='hidden'  ";
					miniClassName += " item-hidden ";
				}
				var newLine = formEditorConfig["newLine"] || false;
				var tdColspan = formEditorConfig["colspan"] || "1";
				var separator = formEditorConfig["separator"];
				var itemHtmlPrefix = "";
				var itemHtmlSuffix = "";
				var separatorHtml = "";
				if (newLine) {
					if (separator) {
						separatorHtml = "</tr>";
						separatorHtml += "<tr class='form-table-title-item'><td class='td-label' colspan='" + (separator["colspan"] || 1) + "'>" + (separator["html"] || "<hr/>") + "</td></tr>";
					}
					if (0 < i) {
						itemHtmlPrefix += "</tr>";
						itemHtmlPrefix += "{separatorHtml}";
						if(i % 2 == 1){
							itemHtmlPrefix += "<tr class='form-table-tr-odd'>";
						} else {
							itemHtmlPrefix += "<tr class='form-table-tr-even'>";
						}
					} else {
						itemHtmlPrefix += "{separatorHtml}";
					}
				}
				itemHtmlPrefix = itemHtmlPrefix.replace("{separatorHtml}", separatorHtml);
				if (0 == i) {
					itemHtmlPrefix += "<tr class='form-table-tr-even'>";
				}
				if ((columnsLen - 1) == i) {
					globalTrSuffix = "";
					if (!isQueryArea) {
						itemHtmlSuffix += "</tr>";
					}
				}
				var tdDisplayClass = "";
				if (!fieldVisible) {
					tdDisplayClass = " item-hidden";
				}
				var editFormItemLabelHtml = "";
				if(isQueryArea){
					editFormItemLabelHtml = itemHtmlPrefix + "<td class='td-label" + tdDisplayClass + "'><label  {labelOtherAttributes} width='{labelWidth}' >{labelHeader}：</label></td>";
				}else{
					editFormItemLabelHtml = itemHtmlPrefix + "<td class='td-label" + tdDisplayClass + "' width='{labelWidth}'><label  {labelOtherAttributes} >{labelHeader}：</label></td>";
				}
				htmlArray.push(editFormItemLabelHtml.replace("{labelWidth}", labelWidth).replace("{labelHeader}", header).replace("{labelOtherAttributes}", labelOtherAttributes));
				var tdPrefixContent = "";
				
				var editFormItemInnerHtml = "<input class='{miniClassName}' {otherAttributes} />";
				if(formEditType == "radiobuttonlist"){//单选框时初始化为div
					editFormItemInnerHtml = "<div class='{miniClassName}' {otherAttributes}></div>";
				}
				
				if(isQueryArea){//调整查询框宽度
					if(otherAttributes.indexOf("width") == -1){
						otherAttributes = otherAttributes + " width='160' ";
					}
				}
				if (isDataRange) {
					otherAttributes = otherAttributes.replace("width='160'", "width='100'");
					var startOtherAttributes = otherAttributes;
					var dateConfigId = formEditorConfig["id"];
					var dateConfigName = formEditorConfig["name"];
					var startDefaultValue = formEditorConfig["startDefaultValue"] || formEditorConfig["startValue"] || "";
					var endDefaultValue = formEditorConfig["endDefaultValue"] || formEditorConfig["endValue"] || "";
					
					if(formEditorConfig["type"] == 'date'){
						startOtherAttributes += " id='" + dateConfigId + "Start" + "' ";
						startOtherAttributes += " name='" + dateConfigName + "Start" + "' ";
						startOtherAttributes += " value='" + startDefaultValue + "' ";
						otherAttributes += " id='" + dateConfigId + "End" + "' ";
						otherAttributes += " name='" + dateConfigName + "End" + "' ";
						otherAttributes += " value='" + endDefaultValue + "' ";
						/*var startEditFormItemContentHtml = "<td class='td-input" + tdDisplayClass + "' colspan='1'><div><input class='{miniClassName}' {otherAttributes} /></div>";
						var endEditFormItemLabelHtml = "<label  width='{labelWidth}'  {labelOtherAttributes} >{labelHeader}</label></td>";
						htmlArray.push(startEditFormItemContentHtml.replace("{miniClassName}", miniClassName).replace("{otherAttributes}", startOtherAttributes));
						tdColspan = (0 == (parseInt(tdColspan) - 1)) ? 1 : (parseInt(tdColspan) - 1);
						htmlArray.push(endEditFormItemLabelHtml.replace("{labelWidth}", labelWidth).replace("{labelHeader}", "&nbsp;到&nbsp;").replace("{labelOtherAttributes}", labelOtherAttributes));*/
					}
					if(formEditorConfig["type"]  == 'float' || formEditorConfig["type"]  == 'double'){
						startOtherAttributes += " id='" + dateConfigId + "Min" + "' ";
						startOtherAttributes += " name='" + dateConfigName + "Min" + "' ";
						startOtherAttributes += " value='" + startDefaultValue + "' ";
						otherAttributes += " id='" + dateConfigId + "Max" + "' ";
						otherAttributes += " name='" + dateConfigName + "Max" + "' ";
						otherAttributes += " value='" + endDefaultValue + "' ";
					}
					var editFormItemContentHtml = "<td class='td-input" + tdDisplayClass + "' colspan='1'><span width='85%'><input class='{miniClassName}' {startOtherAttributes} />";
					editFormItemContentHtml = editFormItemContentHtml + "&nbsp;-&nbsp;<input class='{miniClassName}' {otherAttributes} /></span></td>" + itemHtmlSuffix;
					htmlArray.push(editFormItemContentHtml.replace(new RegExp("{miniClassName}", "gm"), miniClassName).replace("{startOtherAttributes}", startOtherAttributes).replace("{otherAttributes}", otherAttributes));
				}else{
					var editFormItemContentHtml = "<td class='td-input" + tdDisplayClass + "' colspan='" + tdColspan + "'><div>" + editFormItemInnerHtml + "</div></td>" + itemHtmlSuffix;
					htmlArray.push(editFormItemContentHtml.replace("{miniClassName}", miniClassName).replace("{otherAttributes}", otherAttributes));
				}
			}
			// htmlArray.push(globalTrSuffix);
			return {
				htmlArr : htmlArray,
				labelMaxWidth : labelMaxWidth,
				editorMaxWidth : editorMaxWidth
			};
		},
		getQueryAreaParams : function(miniTableId, isClear) {
			var queryAreaContainerId = "id_queryAreaContainerId_" + miniTableId;
			var queryAreaDivDom = mini.byId(queryAreaContainerId);
			var itemIdPrefix = (miniTableId + "_queryArea_");
			var params = {};
			apUtil.recusionAllChildrenNodesParams(queryAreaDivDom, params, isClear);
			var rp = new RegExp("^" + itemIdPrefix);
			for ( var p in params) {
				if (!p)
					continue;
				if (rp.test(p)) {
					var key = p.substring(itemIdPrefix.length, p.length);
					var value = params[p].trim();
					params[key] = value;
					delete params[p];
				}
			}
			return params;
		},
		getColumnsQueryAreaHtmlObj : function(columnsConfig, config, miniTable, isDestroy) {
			var htmlObj = this.initColumnsElements('query', columnsConfig, config, miniTable, isDestroy);
			return htmlObj;
		},
		initColumnsEditFormWindowPanel : function(miniTable, columnsConfig, config) {
			mini.mask({
				el : document.body,
				cls : 'mini-mask-loading',
				html : locale['shadeInfo']
			});
			if (!miniTable.columnsConfig) {
				miniTable.columnsConfig = columnsConfig;
			}
			// assign window's width
			var editFormPopupWindowWidth = config.editFormPopupWindowWidth || 0;
			var editFormPopupWindowHeight = config.editFormPopupWindowHeight;
			var editFormPopupWindowId = config.editWindowId;
			var editFormPopupWindowFormId = config.editFormId;
			var $editFormPopupWindow = jQuery("#" + editFormPopupWindowId);
			var isDestroy = (0 < $editFormPopupWindow.length);
			var editFormWindowPanelHtmlArr = [];
			var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
			var editRemoteOperFlagId = editFormPopupWindowFormId + "_editRemoteOperFlag";
			editFormWindowPanelHtmlArr.push("<div title='"+locale['divTitle']+"'  allowResize='true' showMaxButton='true' showFooter='true'  iconCls='icon-node' id='" + editFormPopupWindowId + "' class='mini-window aptable-edit-form-popup-window' showModal='true' >");
			editFormWindowPanelHtmlArr.push("<form id='" + editFormPopupWindowFormId + "' class='mini-form' >" + "<input type='hidden' id='" + editFormItemOperFlagId + "'/>" + "<input type='hidden' id='" + editRemoteOperFlagId + "'/>" + "<div style='border:1px solid #CCCCCC;'><table><tbody>");
			var htmlObj = this.initColumnsElements('form', columnsConfig, config, miniTable, isDestroy);

			var labelMaxWidth = htmlObj["labelMaxWidth"];
			var editorMaxWidth = htmlObj["editorMaxWidth"];
			var htmlArr = htmlObj["htmlArr"];

			for ( var bb = 0; bb < htmlArr.length; bb++) {
				editFormWindowPanelHtmlArr.push(htmlArr[bb]);
			}
			var editFormPopupWindowFormConfirmButtonId = editFormPopupWindowFormId + "_confirmButton";
			var editFormPopupWindowFormCancelButtonId = editFormPopupWindowFormId + "_cancelButton";
			editFormWindowPanelHtmlArr.push("</tbody></table></div></form>");
			editFormWindowPanelHtmlArr.push("<div property='footer' class='oper-footer'>");
			editFormWindowPanelHtmlArr.push("<span id='" + editFormPopupWindowFormConfirmButtonId + "'><a  class='mini-button'>"+locale['confirm']+"</a></span>");
			editFormWindowPanelHtmlArr.push("&nbsp;&nbsp;<span id='" + editFormPopupWindowFormCancelButtonId + "'><a  class='mini-button'>"+locale['cancel']+"</a></span>");
			editFormWindowPanelHtmlArr.push("</div></div>");
			if (isDestroy) {
				try {
					mini.get(editFormPopupWindowId).destroy();
				} catch (e) {
				}
			}
			jQuery(document.body).append(jQuery(editFormWindowPanelHtmlArr.join("")));
			jQuery("#" + editFormPopupWindowId).css({
				width : 'auto',//Math.max(editFormPopupWindowWidth, (labelMaxWidth + editorMaxWidth + 200)) + "px",
				height : editFormPopupWindowHeight ? (editFormPopupWindowHeight + "px") : "auto"
			});
			jQuery("#" + editFormPopupWindowFormCancelButtonId).bind("click", function(e) {
				e.stopPropagation();
				if(config.cancelOperCallBack){
					var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
					config.cancelOperCallBack(miniTable, miniForm);
				}
				mini.get(editFormPopupWindowId).hide();
			});
			var editFormItemOperFlagId = editFormPopupWindowFormId + "_editFormItemOperFlag";
			jQuery("#" + editRemoteOperFlagId).val((config.remoteOper || false) + "");

			var me = this;
			jQuery("#" + editFormPopupWindowFormConfirmButtonId).bind("click", function(e) {
				e.stopPropagation();
				if(config.confirmOperCallBack){
					var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
					config.confirmOperCallBack(miniTable, miniForm);
				}
				var editFormItemOperFlag = jQuery("#" + editFormItemOperFlagId).val();
				var operText = ("add" == editFormItemOperFlag) ? locale['add'] : (("edit" == editFormItemOperFlag) ? locale['edit'] : locale['copy']);
				var miniForm = new mini.Form("#" + editFormPopupWindowFormId);
				var addIndex = miniTable.getData().length;
				if(config.validateForm){
					if(!config.validateForm(miniTable, miniForm, editFormItemOperFlag, addIndex)){return;}
				}
				//再校验之前，需要将float、doule类型的金额，千分位去掉
				var fields = miniForm.getFields();
				for(var i = 0 ; i < fields.length ; i++){
					if(fields[i].vtype == 'float' || fields[i].vtype == 'double'||fields[i].vtype == 'thousand'){
						fields[i].setValue(fields[i].getValue().replace(/,/g,''));
					}
				}
				if (me.formValidate(miniForm)) {
					var confirmInfo = "确认数据的" + operText + "操作么？";
					if(isEnglish()){
						confirmInfo = "Do you confirm to "+ operText +" the selected data line?";
					}
					mini.confirm(confirmInfo, locale['confirmOperation'], function(buttonText) {
						if ("ok" == buttonText) {
							switch (editFormItemOperFlag) {
							case "add":
							case "edit":
							case "copy": {
								var rowData = miniForm.getData(true);
								if ("true" == (config.remoteOper + "")) {
									me.tableRemoteOper(editFormItemOperFlag, rowData, miniTable, config);
								} else {
									switch (editFormItemOperFlag) {
									case "add": {
										miniTable.addRow(rowData, addIndex);
										if(config.addOperCallBack)config.addOperCallBack(miniTable,rowData);
										break;
									}
									case "edit": {
										var row = miniTable.getSelected();
										miniTable.updateRow(row, rowData);
										if(config.updateOperCallBack)config.updateOperCallBack(miniTable,rowData);
										break;
									}
									}
								}
								mini.get(editFormPopupWindowId).hide();
								break;
							}
							}
							//将多行的数据保存到input中
							miniTable.saveDataToInput();
						}
					});
				}
			});
			mini.parse(editFormPopupWindowId);
			setTimeout(function() {
				mini.unmask(document.body);
			}, 200);
			return mini.get(editFormPopupWindowId);
		},
		tableRemoteOper : function(editFormItemOperFlag, ajaxData, miniTable, config) {
			mini.mask({
				el : document.body,
				cls : 'mini-mask-loading',
				html : locale['shadeInfo']
			});

			ajaxData["entityClassName"] = config["entityClassName"] || "";
			ajaxData["entityBeanCallBackClassName"] = config["entityBeanCallBackClassName"] || "";

			var operText = ("add" == editFormItemOperFlag) ? locale['add'] : (("edit" == editFormItemOperFlag) ? locale['edit'] : (("copy" == editFormItemOperFlag) ? locale['copy'] : locale['remove']));
			jQuery.ajax({
				url : config[editFormItemOperFlag + "RemoteOperUrl"] || (window.globalWebRoot + '/table/' + editFormItemOperFlag + 'RemoteOper.action'),
				async : true,
				type : 'POST',
				contentType : 'application/x-www-form-urlencoded',
				timeout : 30 * 1000,
				data : ajaxData,
				dataType : 'json',
				error : function(res, errInfo, e) {
					mini.unmask(document.body);
				},
				success : function(resultJson) {
					var returnState = resultJson.returnType;
					switch (returnState) {
					case "SUCCESS": {
						miniTable.reload();
						mini.unmask(document.body);
						mini.alert(operText + " "+locale['sucess']+"！");
						break;
					}
					case "FAILURE": {
						mini.unmask(document.body);
						mini.alert(operText + " "+locale['failure']+"！");
						break;
					}
					}
				}
			});
		},
		tableSelectedDataSize : function(miniTable) {
			return miniTable.getSelecteds().length;
		},
		updateEnabledTable : function(){
			alert(1);
			//TODO
		},
		formValidate: function(form){
			form.validate();
			if(form.isValid() == false){
				var errorFields = form.getErrors();
				var errorText = "";
				for(var i = 0;i < errorFields.length;i ++){
					errorText += (errorFields[i].label + " " + errorFields[i].errorText + "<br/>");
				}
				mini.alert(errorText);
				return false;
			}
			return true
		},
		getColumnsEditFormMapping : function(columnsConfig, config) {
			/**
			 * 是否启用状态编辑
			 */
			var isNeedEnabled = config.isNeedEnabled;
			var isFound = false;
			if (isNeedEnabled) {
	            for (var index = 0; index < columnsConfig.length; index++) {
	                if (columnsConfig[index].name == "enabled") {
	                    isFound = true;
	                    break;	
	                }
	            }
	            if (!isFound) {
	            	columnsConfig.push({
	                    header: config.title + '状态',
	                    name: 'enabled',
	                    formEditorConfig : {
							fieldVisible : false 
						},
	                    renderer: function(e) {
	                    	var value = e.record.enabled;
	                        var content = "<font color='red'>{0}</font>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='updateEnabledTable(\"" + config.id + "\",\"" + config.entityClassName + "\",\"" + e.record.id + "\",\"" + value + "\");'>标记为{3}</a>";
	                        var enabledPromit = config.enabledPromit || locale['enable'];
	                        var disabledPromit = config.disabledPromit || locale['disable'];
	                        var re = '';
	                        if ("1" == value) {
	                            re = (content.replace("{0}", enabledPromit)).replace("{3}", disabledPromit);
	                        } else {
	                            re = (content.replace("{0}", disabledPromit)).replace("{3}", enabledPromit);
	                        }
	                        return re;
	                    }
	                });
	            }
	        }
			
			var columnsEditFormMapping = {};
			var fillEditFormMapping = {};
			var parentsEditFormMapping = {};
			var childrenEditFormMapping = {};
			// queryarea config
			var queryAreaFillEditFormMapping = {};
			var queryAreaParentsEditFormMapping = {};
			var queryAreaChildrenEditFormMapping = {};
			// summary
			var summaryColumnNames = [];
			var summaryColumnNameFieldMappings = {};
			// export
			var exportColumns = [];
			var exportComplexHeaders = [];

			var columnsKey = [];
			var columns = columnsConfig;
			this.recursionColumnsEditFormMapping(columnsEditFormMapping, fillEditFormMapping, parentsEditFormMapping, childrenEditFormMapping, queryAreaFillEditFormMapping, queryAreaParentsEditFormMapping, queryAreaChildrenEditFormMapping, summaryColumnNames, summaryColumnNameFieldMappings, exportColumns, exportComplexHeaders, columnsKey, columns, config);
			return {
				columnsEditFormMapping : columnsEditFormMapping,
				fillEditFormMapping : fillEditFormMapping,
				parentsEditFormMapping : parentsEditFormMapping,
				childrenEditFormMapping : childrenEditFormMapping,
				queryAreaFillEditFormMapping : queryAreaFillEditFormMapping,
				queryAreaParentsEditFormMapping : queryAreaParentsEditFormMapping,
				queryAreaChildrenEditFormMapping : queryAreaChildrenEditFormMapping,
				summaryColumnNames : summaryColumnNames,
				summaryColumnNameFieldMappings : summaryColumnNameFieldMappings,
				exportColumns : exportColumns,
				exportComplexHeaders : exportComplexHeaders,
				columnsKey : columnsKey
			};
		},
		exportExcel : function(miniTable, columnsConfig, config) {
			var columnsEditFormMapping = this.getColumnsEditFormMapping(columnsConfig, config);
			var summaryColumn=columnsEditFormMapping["summaryColumnNames"];
			var exportExcelFormId = "id_exportApTableExcelForm";
			var $form = jQuery("#" + exportExcelFormId);
			if (0 < $form.length) {
				document.body.removeChild($form[0]);
			}
           var loadmode=config.loadMode||"local";
			var exportExcelUrl = config.exportExcelUrl || (window.globalWebRoot + '/table/getExcelExportData.action');
			var tempInnerHtml = "<form action='" + exportExcelUrl + "' id='" + exportExcelFormId + "' method='POST'>";

			var complexHeadersStr = mini.encode(config.exportComplexHeaders || columnsEditFormMapping["exportComplexHeaders"]).replace(/'/gi, '&#39;');
			tempInnerHtml += "<input type='hidden' name='complexHeadersStr' value='" + complexHeadersStr + "'/>";

			var columnsStr = mini.encode(columnsEditFormMapping["exportColumns"]).replace(/'/gi, '&#39;');
			tempInnerHtml += "<input type='hidden' name='columnsStr' value='" + columnsStr + "'/>";
			if (loadmode=="local") {
				var datasStr = JsonUtil.encode(miniTable.getData()).replace(/'/gi, '&#39;');
				tempInnerHtml += "<input type='hidden' name='datasStr' value='" + datasStr + "'/>";
			}
			var params = miniTable.getParams();
			params['excelTitleName'] = config.title || "export";
            params['xmlFileName']=config.xmlFileName||"";
			for ( var p in params) {
				var tempStr = params[p];
				if (Object.prototype.toString.call(params[p]) == '[object String]') {
					tempStr = params[p].replace(/'/gi, '&#39;');
				}
				tempInnerHtml += "<input type='hidden' name='" + p + "' value='" + tempStr + "'/>";
			}
			
			tempInnerHtml += "<input type='hidden' name='exportExcelVersion' value='" + (config.exportExcelVersion || "2007") + "'/>";
			tempInnerHtml += "<input type='hidden' name='loadMode' value='" + (config.loadMode||"local") + "'/>";
			
			tempInnerHtml += "<input type='hidden' name='summaryFields' value='" + summaryColumn + "'/>";
			tempInnerHtml += "<input type='hidden' name='isExportTitle' value='" + ((false == config.isExportTitle) ? false : true) + "'/>";
			tempInnerHtml += "<input type='hidden' name='isTableExportExcel' value='true'/>";
			tempInnerHtml += "<input type='hidden' name='BrowserType' value='" + apUtil.getBrowser().toLowerCase() + "'/>";
			tempInnerHtml += "<input type='hidden' name='forceExportExcelIndexs' value='" + "" + "'/>";
			tempInnerHtml += "</form>";
			$form = jQuery(tempInnerHtml);
			jQuery(document.body).append($form);
			$form.submit();

			// 为了防止普通浏览器进行表单提交和产生页面导航（防止默认提交）返回false
			return false;

		},
		importExcel: function(miniTable, columnsConfig, config) {
			var exportExcelFormId = "id_minitableimport";
			var $form = jQuery("#" + exportExcelFormId);
			if (0 < $form.length) {
				document.body.removeChild($form[0]);
			}
			var params=config.importOrExPortParam||{};
			var columnsEditFormMapping = this.getColumnsEditFormMapping(columnsConfig, config);
			var importExcelUrl = config.importExcelUrl || (window.globalWebRoot + '/table/importExcel.action');
			var uploadAttachmentFileWindow_html = "";
			uploadAttachmentFileWindow_html += '<div id="id_minitableimport" class="mini-window"  closed="true" showModal="true" title="导入Excel('+config.title+')" style="display:none;width:400px;text-align:center;background-color:#FDF9F8;">';
			uploadAttachmentFileWindow_html += '	<center>';
			uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
			uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
			uploadAttachmentFileWindow_html += '			<form id="id_minitableimportForm" action="'+importExcelUrl+'" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
			uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%"><tr><td colspan=2>';
			var columnsStr = mini.encode(columnsEditFormMapping["exportColumns"]).replace(/'/gi, '&#39;');
			uploadAttachmentFileWindow_html += "<input type='hidden' name='importExcelColumnStr' value='" + columnsStr + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='currentTableId' value='" + config.id + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='importTargetClass' value='" + (config.importTargetClass||"") + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='importOrExPortCallBack' value='" + (config.importOrExPortCallBack||"") + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='modelname' value='" + (config.title||"") + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='importHeaderIndex' value='" + (config.importHeaderIndex||"1") + "'/>";
			uploadAttachmentFileWindow_html += "<input type='hidden' name='importDataIndex' value='" + (config.importDataIndex||"2") + "'/>";
			if(params!={}){
			for ( var p in params) {
				var tempStr = params[p];
				if (Object.prototype.toString.call(params[p]) == '[object String]') {
					tempStr = params[p].replace(/'/gi, '&#39;');
				}
				uploadAttachmentFileWindow_html += "<input type='hidden' name='" + p + "' value='" + tempStr + "'/>";
			}}
			uploadAttachmentFileWindow_html += "</td></tr>";
			uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate" name="tableImportExcel" style="width:350px;border:1px solid #DDD;cursor:pointer;"></td></tr>';
			uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
			uploadAttachmentFileWindow_html += '						<td colspan="2"  align="center">';
			uploadAttachmentFileWindow_html += '							<a href="javascript:void(0);" id="id_minitableimport_save" class="mini-button" iconCls="icon-add" ><span>'+locale['confirm']+'</span></a>';
			uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;" id="id_minitableimport_cancle" href="javascript:void(0);" class="mini-button" iconCls="icon-close"><span>'+locale['cancel']+'</span></a>';
			uploadAttachmentFileWindow_html += '						</td>';
			uploadAttachmentFileWindow_html += '					</tr>';
			uploadAttachmentFileWindow_html += '				</table>';
			uploadAttachmentFileWindow_html += '			</form>';
			uploadAttachmentFileWindow_html += '		</div>';
			uploadAttachmentFileWindow_html += '	</center>';
			uploadAttachmentFileWindow_html += '</div>';
			$(document.body).append(uploadAttachmentFileWindow_html);
			mini.parse();
			$("#id_minitableimport_save").bind('click',function(){
				var filename=$("#id_tableImportTemplate").val();
				if(filename==""){alert("请选择上传的文件");return false;}
				mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'上传中...'});
				$("#id_minitableimportForm").submit();});
			$("#id_minitableimport_cancle").bind('click',function(){mini.get("id_minitableimport").hide()});
			mini.get("id_minitableimport").show();
			 
		},
		recursionColumnsEditFormMapping : function(columnsEditFormMapping, fillEditFormMapping, parentsEditFormMapping, childrenEditFormMapping, queryAreaFillEditFormMapping, queryAreaParentsEditFormMapping, queryAreaChildrenEditFormMapping, summaryColumnNames, summaryColumnNameFieldMappings, exportColumns, exportComplexHeaders, columnsKey, columns, config) {
			var editFormPopupWindowFormId = config.editFormId;
			for ( var i = 0; i < columns.length; i++) {
				var column = columns[i];
				var type = column.type;
				var header = column.header;
				if (("indexcolumn" == type) || ("checkcolumn" == type)) {
					continue;
				}
				var recursionColumns = column.columns;
				if (recursionColumns && (0 < recursionColumns.length)) {
					var rowIndex = parseInt(column["_level"]);
					var rowComplexHeaders = exportComplexHeaders[rowIndex];
					if ("undefined" == typeof (rowComplexHeaders)) {
						rowComplexHeaders = [];
						exportComplexHeaders.push(rowComplexHeaders);
					}
					var rowComplexHeadConfig = {
						header : header,
						headerAlign : column.headerAlign
					};
					var exportConfig = column.exportConfig || {};
					var startColNum = exportConfig["startColNum"];
					if (startColNum) {
						rowComplexHeadConfig["startColNum"] = startColNum;
					}
					rowComplexHeadConfig["colspan"] = exportConfig["colspan"] || "1";
					rowComplexHeadConfig["rowspan"] = exportConfig["rowspan"] || "1";

					rowComplexHeaders.push(rowComplexHeadConfig);
					this.recursionColumnsEditFormMapping(columnsEditFormMapping, fillEditFormMapping, parentsEditFormMapping, childrenEditFormMapping, queryAreaFillEditFormMapping, queryAreaParentsEditFormMapping, queryAreaChildrenEditFormMapping, summaryColumnNames, summaryColumnNameFieldMappings, exportColumns, exportComplexHeaders, columnsKey, recursionColumns, config);
				} else {
					var key = column["field"];
					var columnName = column["name"] || key;
					column["name"] = columnName;
					var name = key;// column["name"]||key;
					var visible = (("false" == column["visible"]) || (false == column["visible"])) ? false : true;
					var formEditorConfig = column["formEditorConfig"] || {};
					if (visible) {
						//导出或导入列配置
						exportColumns.push({
							header : header,
							name : key,
							mapping : key,
							hidden : !visible,
							required:formEditorConfig["required"]||false,//是否必填
							type:formEditorConfig["vtype"]||"String",//数据类型
							hiddenName:formEditorConfig["fillFromFieldName"]||"",//数据类型
							entityClassName:formEditorConfig["entityClassName"]||"",//导入列对应的类
							entityHeaderFieldName:formEditorConfig["entityHeaderFieldName"]||"",//导入对应的类的属性
							findCallBack:formEditorConfig["findCallBack"]||""//导入查找之后的回调，如果找不到则就增加一行
						});
					}
					columnsKey.push(key);
					var valueObj = {};
					valueObj["header"] = header;
					valueObj["visible"] = visible;
					valueObj["name"] = name;
					valueObj["field"] = key;
					/** *form editor start** */
					
					if (formEditorConfig) {
						// 元素唯一标示
						var itemId = /* formEditorConfig["id"]|| */(editFormPopupWindowFormId + "_" + key);
						// 只针对combobox 级联反选赋值
						var fillFromFieldName = formEditorConfig["fillFromFieldName"];
						var fillProperty = formEditorConfig["fillProperty"] || "{textField}";
						var fieldVisible = formEditorConfig["fieldVisible"];
						if (fillFromFieldName) {
							var fillFormFieldNames = fillEditFormMapping[fillFromFieldName];
							if (!fillFormFieldNames) {
								fillFormFieldNames = [];
								fillEditFormMapping[fillFromFieldName] = fillFormFieldNames;
							}
							fillFormFieldNames.push({
								id : itemId,
								fillProperty : fillProperty,
								fieldVisible : fieldVisible
							});
						}
						// editor config
						formEditorConfig["id"] = itemId;
						formEditorConfig["name"] = name;
						formEditorConfig["dataField"] = "datas";
						formEditorConfig["url"] = formEditorConfig["url"] || (window.globalWebRoot + '/table/getTableData.action');
						valueObj["formEditorConfig"] = formEditorConfig;
					}
					/** *form editor finish** */
					/** *query area editor start** */
					var queryConfig = column["queryConfig"];
					if (queryConfig) {
						// 元素唯一标示
						var itemId = (config.id + "_queryArea_" + key);
						// 只针对combobox 级联反选赋值
						var queryAreaFillFromFieldName = queryConfig["fillFromFieldName"];
						var queryAreaFillProperty = queryConfig["fillProperty"] || "{textField}";
						var queryAreaFieldVisible = true;// queryConfig["fieldVisible"];
						if (queryAreaFillFromFieldName) {
							var queryAreaFillFormFieldNames = queryAreaFillEditFormMapping[queryAreaFillFromFieldName];
							if (!queryAreaFillFormFieldNames) {
								queryAreaFillFormFieldNames = [];
								queryAreaFillEditFormMapping[queryAreaFillFromFieldName] = queryAreaFillFormFieldNames;
							}
							queryAreaFillFormFieldNames.push({
								id : itemId,
								fillProperty : queryAreaFillProperty,
								fieldVisible : queryAreaFieldVisible
							});
						}
						// query config
						queryConfig["id"] = itemId;
						queryConfig["name"] = itemId;
						queryConfig["newLine"] = queryConfig["newLine"] || false;
						queryConfig["dataField"] = "datas";
						queryConfig["url"] = queryConfig["url"] || (window.globalWebRoot + '/table/getTableData.action');
						valueObj["queryConfig"] = queryConfig;
					}
					/** *query area editor finish** */
					// summary
					if (true == column["summary"]) {
						summaryColumnNames.push(columnName);
						summaryColumnNameFieldMappings[key] = columnName;
					}
					columnsEditFormMapping[key] = valueObj;
				}
			}
		},
		// 折叠显示区域
		fieldsetToggleClick : function(e, callBack) {
			apUtil.fieldsetToggle(e.target, callBack);
		},
		// 格式化千分位
		formatNumberThousand : function(s) {
			return apUtil.formatNumberThousand(s);
		}/*,
		changeColumnToI18n : function(config,ischange){
			return apUtil.changeColumnToI18n(config,ischange);
		}*/
	};
	return ApTableBase;
});