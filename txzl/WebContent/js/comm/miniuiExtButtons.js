var miniuiExtButtons = function(config) 
{
	var isReadonly = ('undefined' == typeof(config.isReadonly) || "null" == config.isReadonly ) ? false : true;
	if(isReadonly) return;
	var strFullPath = window.location.pathname;
	var flag = strFullPath.substring(strFullPath.substr(1).lastIndexOf('/')+2,strFullPath.length);
	strFullPath = strFullPath.replace(flag,"input_"+flag);
	strFullPath = strFullPath.replace("/tenwa","");
	var url = config.url || strFullPath;
	var title = config.title; 
	var width = config.width || 800; 
	var height = config.height || 500;
    var datagridId = config.datagridId;
    var renderTo = config.renderTo;
    var showModal = config.showModal || true;
    var showMaxButton = config.showMaxButton || true;
    var isRowEditing = config.isRowEditing ||false;
    var rowEditParams = config.rowEditParams||{};
    var toolbarTextAalign = config.toolbarTextAalign || "left";
    var tools = ('undefined' == typeof(config.tools)) ? ["新增","修改","删除","导出"]: config.tools;
    //添加，修改，删除时请求的controller名称
    var constantFlagTable = config.constantFlagTable;
    var prefixMVC = config.prefixMVC || "acl";
    var suffixMVC = config.suffixMVC || ".acl";
    var addOperParamscolumn = config.addOperParamscolumn || "id";
    var updOperParamscolumn = config.updOperParamscolumn || "id";
    var delOperParamscolumn = config.delOperParamscolumn || "id";
    
 // 初始化columns
	var columnsConfig = config.columns ||initColumns(datagridId);
    //var grid = mini.get(datagridId);
    // toolbar
    var $panelContainer = jQuery("#" + config.renderTo);
    var $toolbarContainer = null;
	var toolbarDom = config.toolbarEl;
	if (toolbarDom || tools) {
		toolbarDom = mini.byId(toolbarDom);
		$toolbarContainer = (!toolbarDom ? jQuery("<div></div>") : jQuery(toolbarDom)).addClass("mini-toolbar").attr({}).css({
			display : 'block',
			borderWidth : "0px",
			borderBottomWidth : "1px",
			textAlign : toolbarTextAalign
		});
		$panelContainer.append($toolbarContainer);
	}
//	// 创建tools应用
	var createToolItemByConfig = function(me, tool) {
		var html = tool.html;
		var $toolItemParent = jQuery("<span></span>");
		$toolbarContainer.append($toolItemParent);
		var handler = tool.handler || function() {
		};
		var clickHandler = (function(me, html, handler) {
			return function(e) {
				handler(mini.get(datagridId), html);
				e.stopPropagation();
			};
		})(me, html, handler);
		$toolItemParent.bind("click", clickHandler);
		var toolItemConfig = {};

		// if(tool.id) {toolItemConfig["id"] = tool.id;}
		if (tool.iconCls) {
			toolItemConfig["iconCls"] = tool.iconCls;
		}
		if (tool.plain) {
			toolItemConfig["plain"] = tool.plain;
		}

		$toolItem = jQuery("<a href='javascript:void(0)'></a>").attr(toolItemConfig);
		$toolItemParent.append($toolItem);
		$toolItem.addClass("mini-button mini-button-plain").html(html);
		var toolItemText = $toolItem.text();
		var toolItemIdentifier = (config.id + "_" + toolItemText);
		$toolItem.attr({
			id : toolItemIdentifier
		});
		$toolItemParent[0].setAttribute("remainClickFunction", clickHandler);
		$toolItemParent.attr({
			toolItemIdentifier : toolItemIdentifier
		});
	};
	var defaultTools ={
		"新增":{"html" : "新增","iconCls" : "icon-addfolder", "plain" :true},
		"修改":{"html" : "修改","iconCls" : "icon-edit", "plain" :true},
		"删除":{"html" : "删除","iconCls" : "icon-remove", "plain" :true},
		"导出":{"html" : "导出","iconCls" : "icon-exportExcel", "plain" :true},
		"复制":{"html" : "复制","iconCls" : "icon-collapse", "plain" :true},
		"保存":{"html" : "保存","iconCls" : "icon-save", "plain" :true},
		"关闭":{"html" : "关闭","iconCls" : "icon-close", "plain" :true}
	};
	for ( var index = 0; index < tools.length; index++) {
		var tool = tools[index];
		var $toolItem = null;
		if (typeof (tool) == 'object') {
			var html = tool.html;
			var isHtml = tool.isHtml || false;
			if (isHtml) {
				$toolItem = jQuery(html);
				$toolbarContainer.append($toolItem);
				continue;
			} else {
				createToolItemByConfig(this, tool);
				if(index!=tools.length-1){
					$toolbarContainer.append(jQuery("<span class='separator'></span>"));
				}			
			}
		}else if (typeof (tool) == 'string') {
			var operbeforehander = tool.operbeforehander || function() {
			};
			var toolContent = tool.trim();
			var operMethod ="";
			switch (toolContent) {
				case '新增': {
					defaultTools[toolContent].handler = function(miniTable, html) {
						if (config.operbeforehander) {
				            config.operbeforehander(miniTable,"新增");
				        }
						var selectedRowDatas = miniTable.getSelecteds();
						if(config.operValidate){
							if(!config.operValidate(miniTable, selectedRowDatas, '新增')){return;}
						}
						if (config.isRowEditing) {
							var rowData = rowEditParams;
							miniTable.addRow(rowData, 0);
							miniTable.commitEdit();
							miniTable.beginEditRow(rowData);
						} else {
							var urlFlag="";
							var temp="";
							if (typeof (addOperParamscolumn) == 'object') {
								
								for(var p in addOperParamscolumn)
							    {
								    temp+=("&"+p+"="+escape(encodeURIComponent(addOperParamscolumn[p]))); 
							    }
							}
							urlFlag+=getRootPath()+url+"?1=1"+temp;
							operMethod = "add" + constantFlagTable;
							var saveUrl = getRootPath() + "/" + prefixMVC + "/{0}".replace("{0}", operMethod) + suffixMVC;
							mini.open({
				                url: urlFlag+"&opertype=add&saveurl="+saveUrl,
				                title: title, width: width, height: height,
				                showModal: showModal,
				                showMaxButton: showMaxButton,
				                onload: function () {
				                },
				                ondestroy: function (action) {
				                	if("savesuccess" == action){
				                		mini.get(datagridId).reload();
			                    	}
				                }
				            });
						}
					};
					break;
				}
				case '修改': {
					//获取选中行、并判断
					defaultTools[toolContent].handler = function(miniTable, html) {
						var row = miniTable.getSelected();
			            if (row) {
			            	if (config.operbeforehander) {
					            config.operbeforehander(miniTable,"修改");
					        }
							var selectedRowDatas = miniTable.getSelecteds();
							if(config.operValidate){
								if(!config.operValidate(miniTable, selectedRowDatas, '修改')){return;}
							}
			            	var temp="";
							var params = updOperParamscolumn;
							var urlFlag="";
			            	if("all" == updOperParamscolumn){
			            		urlFlag=getRootPath()+url+"?1=1";
			            	}else if("id" == updOperParamscolumn){
			            		urlFlag=getRootPath()+url+"?id="+row.id;
			            	}
			            	else{
			            		if (typeof (params) == 'object') {
									for(var p in params)
								    {
									    temp+=("&"+p+"="+escape(encodeURIComponent(params[p]))); 
									    
								    }
								}else{
								   for(var i=0;i<params.length;i++)
								   {
									   temp+=("&"+params[i]+"="+row[params[i]]); 
								   }
								}
			            		urlFlag+=getRootPath()+url+"?1=1"+temp;
			            	}
			            	operMethod = "update" + constantFlagTable;
							var saveUrl = getRootPath() + "/" + prefixMVC + "/{0}".replace("{0}", operMethod) + suffixMVC;
			            	mini.open({
				                url: urlFlag+"&opertype=update&saveurl="+saveUrl,
				                title: title, width: width, height: height,
				                showModal: showModal,
				                showMaxButton: showMaxButton,
				                onload: function () {
				                	if("all" == updOperParamscolumn){
					                	var iframe = this.getIFrameEl();
				                        iframe.contentWindow.SetData(row);
				                	}
				                },
				                ondestroy: function (action) {
				                	if("savesuccess" == action){
				                		mini.get(datagridId).reload();
			                    	}
				                }
				            });
			            } else {
			                mini.alert("请选中一条记录");
			            }
					 };
					break;
				}
				case '删除': {
					defaultTools[toolContent].handler = function(miniTable, html) {
						var rows = miniTable.getSelecteds();
			            if (rows.length > 0) {
			            	mini.confirm("确定删除选中记录？","提示：",function(data){
					        	if("ok" == data){
					        		if (config.operbeforehander) {
							            config.operbeforehander(miniTable,"删除");
							        }
					        		if(isRowEditing){
					        			miniTable.removeRows(rows);return;
					        		}
					        		var temp="";
									var params = delOperParamscolumn;
									operMethod = "remove" + constantFlagTable;
									var url = getRootPath() + "/" + prefixMVC + "/{0}".replace("{0}", operMethod) + suffixMVC;
									var urlFlag="";
									if("id" == delOperParamscolumn){
					            		urlFlag=url+"?id="+rows[0].id;
					            	}
									else if('object' == typeof(params))
									{
									   for(var i=0;i<params.length;i++)
									   {
										   temp+=("&"+params[i]+"="+escape(encodeURIComponent(rows[0][params[i]]))); 
									   }
									   urlFlag=url+"?1=1"+temp;
									}
					        		grid.loading("操作中，请稍后......");
						            $.ajax({
						                url: urlFlag,
						                success: function (text) {
						                    grid.reload();
						                },
						                error: function () {
						                }
						            });
					        	}
					        });
			            } else {
			                mini.alert("请选中一条记录");
			            }
					 };
					break;
				}
				case '保存': {
					defaultTools[toolContent].handler = function(miniTable, html) {
						var data = miniTable.getChanges(null);
			            miniTable.loading("保存中，请稍后......");
			            var json = mini.encode(data);
			            operMethod = "save" + constantFlagTable;
						var url = getRootPath() + "/" + prefixMVC + "/{0}".replace("{0}", operMethod) + suffixMVC;
			            $.ajax({
			                url: url,
			                data: {data : json},
			                type: "post",
			                success: function (text) {
			                    miniTable.reload();
			                }, 
			                error: function (jqXHR, textStatus, errorThrown) {
			                    alert(jqXHR.responseText);
			                }
			            });
					}
					break;
				}
				case '复制': {
					defaultTools[toolContent].handler = function(miniTable, html) {
						var rows = miniTable.getSelecteds();
						if(rows.length == 0){
							mini.alert("请选择需要复制的数据！");
							return;
						}
			            if (1 < rows.length) {
			            	mini.alert("只能选择单条数据进行复制操作！");
			            	return;
			            } 
			            var uploadAttachmentFileWindow_html = "";
			        	uploadAttachmentFileWindow_html += '<div id="id_copyFormWindow" class="mini-window"  closed="true" modal="true" title=\"'+title+'\" style="display:none;width:300px;text-align:center;background-color:#FDF9F8;">';
			        	uploadAttachmentFileWindow_html += '	<center>';
			        	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
			        	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%">';
			        	uploadAttachmentFileWindow_html += '					<tr><td class="miniext-form-td">复制行数：</td>';
			        	uploadAttachmentFileWindow_html += '					<td><input id="copyrow" name="copyrow" class="mini-textbox miniext-form-fit" onEnter="search"/></td></tr>';
			        	uploadAttachmentFileWindow_html += '					<tr>';
			        	uploadAttachmentFileWindow_html += '						<td colspan="2">';
			        	uploadAttachmentFileWindow_html += '							<a  class="mini-button" iconCls="icon-add" onclick="javascript:fnCopyRow('+datagridId+');"><span>确定</span></a>';
			        	uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;"  class="mini-button" iconCls="icon-close"  onclick=\'javascript:mini.get("id_copyFormWindow").hide();\'><span>取消</span></a>';
			        	uploadAttachmentFileWindow_html += '						</td>';
			        	uploadAttachmentFileWindow_html += '					</tr>';
			        	uploadAttachmentFileWindow_html += '				</table>';
			        	uploadAttachmentFileWindow_html += '		</div>';
			        	uploadAttachmentFileWindow_html += '	</center>';
			        	uploadAttachmentFileWindow_html += '</div>';
			        	$(document.body).append(uploadAttachmentFileWindow_html);
			        	mini.parse(document.getElementById("id_copyFormWindow"))
			        	var win = mini.get("id_copyFormWindow");
			        	win.showAtPos("center","middle");
					}
					break;
				}
				case '导出': {
					defaultTools[toolContent].handler = function(miniTable, html) {
						var columnsEditFormMapping = getColumnsEditFormMapping(columnsConfig, config);
						var exportExcelFormId = "id_exportApTableExcelForm";
						var $form = jQuery("#" + exportExcelFormId);
						if (0 < $form.length) {
							document.body.removeChild($form[0]);
						}

						var exportExcelUrl = config.exportExcelUrl || (window.globalWebRoot + '/table/getExcelExportData.action');
						var tempInnerHtml = "<form action='" + exportExcelUrl + "' id='" + exportExcelFormId + "' method='POST'>";

						var complexHeadersStr = mini.encode(config.exportComplexHeaders || columnsEditFormMapping["exportComplexHeaders"]).replace(/'/gi, '&#39;');
						tempInnerHtml += "<input type='hidden' name='complexHeadersStr' value='" + complexHeadersStr + "'/>";

						var columnsStr = mini.encode(columnsEditFormMapping["exportColumns"]).replace(/'/gi, '&#39;');
						tempInnerHtml += "<input type='hidden' name='columnsStr' value='" + columnsStr + "'/>";
						var datasStr = mini.encode(miniTable.getData()).replace(/'/gi, '&#39;');
						tempInnerHtml += "<input type='hidden' name='datasStr' value='" + datasStr + "'/>";
						var params = miniTable._dataSource.loadParams;
						params['excelTitleName'] = config.title || "export";

						for ( var p in params) {
							var tempStr = params[p];
							if (Object.prototype.toString.call(params[p]) == '[object String]') {
								tempStr = params[p].replace(/'/gi, '&#39;');
							}
							tempInnerHtml += "<input type='hidden' name='" + p + "' value='" + tempStr + "'/>";
						}
						var excelversion = config.exportExcelVersion || "2007";
						tempInnerHtml += "<input type='hidden' name='exportExcelVersion' value='" + excelversion + "'/>";
						tempInnerHtml += "<input type='hidden' name='loadMode' value='local'/>";
						tempInnerHtml += "<input type='hidden' name='isExportTitle' value='" + ((false == config.isExportTitle) ? false : true) + "'/>";
						tempInnerHtml += "<input type='hidden' name='isTableExportExcel' value='true'/>";
						tempInnerHtml += "<input type='hidden' name='BrowserType' value='" + getBrowser().toLowerCase() + "'/>";
						tempInnerHtml += "<input type='hidden' name='forceExportExcelIndexs' value='" + "" + "'/>";
						tempInnerHtml += "</form>";

						$form = jQuery(tempInnerHtml);
						jQuery(document.body).append($form);
						$form.submit();

						// 为了防止普通浏览器进行表单提交和产生页面导航（防止默认提交）返回false
						return false;
					}
					break;
				}
				case '关闭': {
					defaultTools[toolContent].handler = function() {
						onCancel();
					}
					break;
				}
			}
			createToolItemByConfig(this, defaultTools[toolContent]);
			$toolItem = jQuery("<span class='separator'></span>");
			if(index!=tools.length-1){
				$toolbarContainer.append($toolItem);
			}
		}
	}
	//$panelContainer.append($tableContainer);
	//var str='<input class="mini-textbox" />';
	//currentObj.append(str);
	mini.parse($panelContainer[0]);
};
function fnCopyRow(gridid){
	var grid =  mini.get(gridid);
	var selectedRowData = grid.getSelected();
	var copyCount = mini.get("copyrow").getValue();
	var newRowDatas = [];
	for ( var i = 0; i < copyCount; i++) {
		var newRowData = {};
		mini.copyTo(newRowData, selectedRowData);
		newRowDatas.push(newRowData);
	}
	grid.addRows(newRowDatas, 0);
	mini.get("id_copyFormWindow").hide();
	mini.get("copyrow").setValue("");
}
function getColumnsEditFormMapping(columnsConfig, config) {
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
	recursionColumnsEditFormMapping(columnsEditFormMapping, fillEditFormMapping, parentsEditFormMapping, childrenEditFormMapping, queryAreaFillEditFormMapping, queryAreaParentsEditFormMapping, queryAreaChildrenEditFormMapping, summaryColumnNames, summaryColumnNameFieldMappings, exportColumns, exportComplexHeaders, columnsKey, columns, config);
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
}
function initColumns(gridid){
if(('undefined' == typeof(gridid))) return;
	var grid =  mini.get(gridid);
	var columns = grid.getBottomColumns();
    function getColumns(columns) {
        columns = columns.clone();
        for (var i = columns.length - 1; i >= 0; i--) {
            var column = columns[i];
            if (!column.field) {
                columns.removeAt(i);
            } else {
                var c = { header: column.header, field: column.field };
                columns[i] = c;
            }
        }
        return columns;
    }
    return columns;
}
function recursionColumnsEditFormMapping(columnsEditFormMapping, fillEditFormMapping, parentsEditFormMapping, childrenEditFormMapping, queryAreaFillEditFormMapping, queryAreaParentsEditFormMapping, queryAreaChildrenEditFormMapping, summaryColumnNames, summaryColumnNameFieldMappings, exportColumns, exportComplexHeaders, columnsKey, columns, config) {
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
			if (visible) {
				exportColumns.push({
					header : header,
					name : key,
					mapping : key,
					hidden : !visible
				});
			}
			columnsKey.push(key);
			var valueObj = {};
			valueObj["header"] = header;
			valueObj["visible"] = visible;
			valueObj["name"] = name;
			valueObj["field"] = key;
			/** *form editor start** */
			var formEditorConfig = column["formEditorConfig"] || {};
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
}
function getBrowser(){  
    var browserName=navigator.userAgent.toLowerCase();  
    if(/msie/i.test(browserName) && !/opera/.test(browserName)){  
        return "IE";  
    }else if(/firefox/i.test(browserName)){  
        return "Firefox";  
    }else if(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)){  
        return "Chrome";  
    }else if(/opera/i.test(browserName)){  
        return "Opera";  
    }else if(/webkit/i.test(browserName) &&!(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName))){  
        return "Safari"	;  
    }
    return "";
}  
