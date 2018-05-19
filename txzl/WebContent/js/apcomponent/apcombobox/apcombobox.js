define(function(require, exports, module) {
	require("css/apcombobox/{currentSkin}/apcombobox.css");
	var locale = require("js/apcombobox/locale/{currentLocale}.js");
	var exportExcel = locale.exportExcel;
	var ApComboBox = function(config) {
		var params = config.params || {};
		params['xmlFileName'] = config.xmlFileName;
		config.dataField = "datas";
		config.totalField = "total";
		config.pageIndexField = "pageIndex";
		config.pageSizeField = "pageSize";
		config.sortFieldField = "TableRemoteSortField";
		config.sortOrderField = "TableRemoteSortDir";
		var currentObj = mini.byId(config.renderTo || document.body);
		var isLocal = false;
		if (config.data) {
			isLocal = true;
			config.showPager = false;
		} else {
			config.url = config.url || (window.globalWebRoot + '/table/getTableData.action');
		}
		jQuery.extend(mini.ComboBox.prototype, {
			setParam : function(name, value) {
				this.setParams({
					name : value
				});
			},
			setParams : function(newParams) {
				var currentParams = this._dataSource.loadParams || {};
				for ( var param in newParams) {
					currentParams[param] = newParams[param];
				}
			},
			reload : function(success, error, complete) {
				load(success, error, complete);
			},
			load : function(success, error, complete) {
				this.accept();
				miniTable._dataSource.loadParams["pageIndex"] = 0;
				this._dataSource.reload(success, error, complete);
			}
		});
		var $renderToContainer = jQuery(currentObj).attr({}).css({});
		var panelTitle = config.title;
		var panelContainerId = "id_panelContainer_" + config.id;
		var $panelContainer = jQuery("<div class='aptable'></div>").addClass("mini-panel").attr({
			id : panelContainerId,
			title : panelTitle,
			iconCls : config.iconCls,
			showToolbar : config.showToolbar || false,
			showCloseButton : config.showCloseButton || false,
			showFooter : config.showFooter || false,
			showHeader : panelTitle ? true : false,
			bodyStyle : config.bodyStyle || "padding:0px;"
		}).css({
			width : parseInt(config.width) + "px",
			height : parseInt(config.height) + "px"
		});
		$renderToContainer.append($panelContainer);
		var $toolbarContainer = null;
		var toolbarDom = config.toolbarEl;
		if (toolbarDom || config.tools) {
			toolbarDom = mini.byId(toolbarDom);
			$toolbarContainer = (!toolbarDom ? jQuery("<div></div>") : jQuery(toolbarDom)).addClass("mini-toolbar").attr({}).css({
				display : 'block',
				borderWidth : "0px",
				borderBottomWidth : "1px"
			});
			$panelContainer.append($toolbarContainer);
		}
		//创建tools应用
		var tools = config.tools || [];
		var me = this;
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
					var $toolItemParent = jQuery("<span></span>");
					$toolbarContainer.append($toolItemParent);
					var handler = tool.handler || function() {
					};
					var clickHandler = (function(html, handler) {
						return function(e) {
							handler(me.miniTable, html);
							e.stopPropagation();
						};
					})(html, handler);
					$toolItemParent.bind("click", clickHandler);
					var toolItemConfig = {};
					if (tool.iconCls) {
						toolItemConfig["iconCls"] = tool.iconCls;
					}
					if (tool.plain) {
						toolItemConfig["plain"] = tool.plain;
					}
					if (tool.id) {
						toolItemConfig["id"] = tool.id;
					}
					if (tool.plain) {
						toolItemConfig["plain"] = tool.plain;
					}

					$toolItem = jQuery("<a href='javascript:void(0)'></a>").attr(toolItemConfig);
					$toolItemParent.append($toolItem);
					$toolItem.addClass("mini-button mini-button-plain").html(html);
					var toolItemText = $toolItem.text();

					$toolItemParent.attr({
						toolItemIdentifier : config.id + "_" + toolItemText
					});
				}
			} else if (typeof (tool) == 'string') {
				//添加分隔符
				$toolItem = jQuery("<span class='separator'></span>");
				$toolbarContainer.append($toolItem);
			}
		}
		var tableContainerId = "id_tableContainer_" + config.id;
		var $tableContainer = jQuery("<div></div>").addClass("mini-fit").attr({
			id : tableContainerId
		}).css({});
		$panelContainer.append($tableContainer);
		mini.parse($panelContainer[0]);
		var $excelTool = jQuery("<span></span>").addClass("tools-export-excel").attr({
			title : exportExcel
		}).css({});
		jQuery(mini.get(panelContainerId).getHeaderEl()).find("div.mini-panel-header-inner div.mini-tools").append($excelTool);
		
		var miniTable = new mini.DataGrid();
		this.miniTable = miniTable;
		config["width"] = '100%';
		config["height"] = '100%';
		miniTable.set(config);
		miniTable._dataSource.loadParams = params;
		miniTable.render(tableContainerId);
		if ((true != config.lazyLoad) && !isLocal) {
			miniTable.load();
		}
	};
	return ApComboBox;
});