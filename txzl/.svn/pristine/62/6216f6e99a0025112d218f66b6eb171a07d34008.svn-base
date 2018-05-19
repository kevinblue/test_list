var tracywindyOperationTable = function(config) {
	
	//表格数据对象名称，删除，修改，添加成功后提示信息子段
    var entityPromitTable = config.tableComment;
    //添加，修改，删除时请求的controller名称
    var constantFlagTable = config.constantFlagTable;
    var enabledPromit = config.enabledPromit || "启用";
    var enabledDefaultValue = config.enabledDefaultValue || "";
    var disabledPromit = config.disabledPromit || "停用";
    var separator = config.separator || "&nbsp;&nbsp;";
    var isNeedEnabled = config.isNeedEnabled || false;
    var isNeedPasswordReset  = (false == config.isNeedPasswordReset) ? false : true;
	//页面加载完成时是否显示查询区域
    var showQueryAreaOnDocumentReady = config.showQueryAreaOnDocumentReady||false;
    var windowContainerIdTable = config.formContainerId || "id_detailInfoWindowContainer";
    var $form = jQuery("#" + windowContainerIdTable + " form");
    var prefixMVC = config.prefixMVC || "acl";
    var suffixMVC = config.suffixMVC || ".acl";
    var otherOper = config.otherOper || function(value, tableObj, columnName, columnIndex, rowData) {
        return "";
    };
    //表格数据默认增删改查等操作按钮
    var operButtons = ('undefined' == typeof(config.operButtons)) ? "新增|修改|删除": config.operButtons; //config.operButtons||"";
    config.isCheck = (false == config.isCheck) ? false: true;
    //行选中模式，单选还是多选，默认单选
    config.checkType = config.checkType || "radio";
    var operButtonsMapping = {
        "新增": {
            iconCls: 'icon-add',
            html: '<font color="red">新增</font>',
            handler: function(table) {
                table.addEntity();
            }
        },
        "修改": {
            iconCls: 'icon-update',
            html: '<font color="red">修改</font>',
            handler: function(table) {
                table.updateEntity();
            }
        },
        "删除": {
            iconCls: 'icon-remove',
            html: '<font color="red">删除</font>',
            handler: function(table) {
                table.removeEntity();
            }
        },
        "全局搜索": {
            isHtml: true,
            html: '全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;width:80px;" id="' + config.id + 'id_queryWorkflowsTableInput" />'
        },
        "状态": {
            isHtml: true,
            html: '<span style="float:left;">' + entityPromitTable + '状态：</span><span  style="height:20px;line-height:20px;margin-top:3px;float:left;position:relative;" id="id_' + config.id + '_enabled_combo_container" ></span>'
        }
        /*,
        "重置密码":{
	        iconCls:'icon-minus',
	        html:'<font color="red">重置密码</font>',
	        handler:function(table){
	      	  table.resetPassword();
	        }
        }*/
    };
    //var formIdTable  =  config.formId||"id_detailInfoForm";
    var windowTop = config.windowTop || 50;
    var scrollWindowTopContainer = config.scrollWindowTopContainer;
    var $tools = [];
    /* var isNeedGlobalQuery = (false == config.isNeedGlobalQuery) ? false : true ;
    if(isNeedGlobalQuery){
    	$tools.push({
       	  isHtml:true,
         	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;width:80px;" id="'+config.id+'id_queryWorkflowsTableInput" />'
         });
    	$tools.push(operButtonsMapping["全局搜索"]);
    	$tools.push('-');
    }
    if(isNeedEnabled){
    	if(isNeedGlobalQuery){
    		$tools.push('-');
    	}
    	$tools.push({
            isHtml:true,
            html:'<span style="float:left;">'+entityPromitTable+'状态：</span><span  style="float:left;top:8px;position:relative;" id="id_'+config.id+'_enabled_combo_container" ></span>'
          });
    	$tools.push(operButtonsMapping["状态"]);
    	config["toolsAdd"] = 65;
    }
    $tools.push({
          iconCls:'icon-plus',
          html:'<font color="red">新增</font>',
          handler:function(table){
        	  table.addEntity();
          }
      });
    $tools.push('-');*/
    var isNeedGlobalQuery = false;
    var isNeedEnabledQuery = false;
    var operButtonsArr = operButtons.split("|");
    for (var i = 0; i < operButtonsArr.length; i++) {
        var toolsItem = operButtonsArr[i];
        if (toolsItem) {
            var toolsItemObj = operButtonsMapping[toolsItem.replace(/\s/g, "")];
            if ("全局搜索" == toolsItem.replace(/\s/g, "")) {
                isNeedGlobalQuery = true;
            }
            if ("状态" == toolsItem.replace(/\s/g, "")) {
                isNeedEnabledQuery = true;
            }
            if (toolsItemObj) {
                $tools.push(toolsItemObj);
                $tools.push('-');
            }
        }

    }
    if (config.tools) {
        for (var i = 0; i < config.tools.length; i++) {
            $tools.push(config.tools[i]);
        }
    }
    config["tools"] = $tools;
    config["fitColumnSizeAdd"] = 8;

    if (config.columns) {
        var isFound = false;
        if (isNeedEnabled) {
            for (var index = 0; index < config.columns.length; index++) {
                if (config.columns[index].name == "enabled") {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                config.columns.push({
                    header: entityPromitTable + '状态',
                    name: 'enabled',
                    renderer: function(value, tableObj, columnName, columnIndex, rowData) {
                        var content = "<font color='red'>{0}</font>{1}<a href='javascript:void(0);' onclick='getTracywindyObject(\"" + table.id + "\").updateEnabledTable({2});'>标记为{3}</a>";
                        var rowIndex = rowData.rowIndex;
                        var re = "";
                        if ("true" == value) {
                            re = (content.replace("{0}", enabledPromit)).replace("{1}", separator).replace("{2}", rowIndex).replace("{3}", disabledPromit);
                        } else {
                            re = (content.replace("{0}", disabledPromit)).replace("{1}", separator).replace("{2}", rowIndex).replace("{3}", enabledPromit);
                        }
                        return re;
                    }
                });
            }
        }
        isFound = false;
        for (var index = 0; index < config.columns.length; index++) {
            if (config.columns[index].name == "oper") {
                isFound = true;
                break;
            }
        }
        if ((!isFound) && ("User" == constantFlagTable) && isNeedPasswordReset) {
            config.columns.splice(0, 0, {
                header: '操作',
                name: 'oper',
                renderer: function(value, tableObj, columnName, columnIndex, rowData) {
                    var base = "<a href='javascript:void(0);' onclick='getTracywindyObject(\"" + table.id + "\").{0}({1})'>{2}</a>{3}";
                    var updateFlag = "修改";
                    var updateClickFunc = "updateEntity";
                    var removeFlag = "删除";
                    var removeClickFunc = "removeEntity";
                    var resetFlag = "重置密码为6个1";
                    var resetClickFunc = "resetPassword";
                    var rowIndex = rowData.rowIndex;
                    var separator = "&nbsp;&nbsp;";
                    var updateOper = base.replace("{0}", updateClickFunc).replace("{1}", rowIndex).replace("{2}", updateFlag).replace("{3}", separator);
                    var removeOper = base.replace("{0}", removeClickFunc).replace("{1}", rowIndex).replace("{2}", removeFlag).replace("{3}", separator);
                    var resetOper = base.replace("{0}", resetClickFunc).replace("{1}", rowIndex).replace("{2}", resetFlag).replace("{3}", separator);
                    //var re = updateOper+removeOper;
                    var re = "";
                    if ("User" == constantFlagTable) {
                        re += resetOper;
                    }
                    re += (separator + otherOper(value, tableObj, columnName, columnIndex, rowData));
                    return re;
                }
            });
        }
    }
    if (("User" == constantFlagTable)&&isNeedPasswordReset) {
        var lockedNames = [];
        if (config.lockedNames) {
            lockedNames = config.lockedNames;
        }
        lockedNames.splice(0, 0, 'oper');
        config.lockedNames = lockedNames;
    }

    var table = new tracywindyTable(config);
    if (isNeedEnabledQuery) {

        var combo = new tracywindyComboBox({
            id: 'id_' + table.id + '_enabled_combo',
            renderTo: 'id_' + table.id + '_enabled_combo_container',
            loadMode: 'local',
            datas: [{
                title: enabledPromit,
                name: '1'
            },
            {
                title: disabledPromit,
                name: '0'
            }],
            readOnly: true,
            width: 60,
            topAdd: 0,
            leftAdd: 0,
            displayField: 'title',
            valueField: 'name',
            leftAdd: 0,
            value: enabledDefaultValue,
            onSelect: function(combo) {
                table.setParams({
                    enabled: combo.getValue()
                });
                table.reload();
            }
        });
    }
    if (isNeedGlobalQuery) {
        jQuery("#" + config.id + "id_queryWorkflowsTableInput").keypress(function(e) {
            var code = e.keyCode || e.charCode;
            if (13 == code) {
                table.setParams({
                    queryText: this.value.toUpperCase()
                });
                table.reload();
            }
        });
    }
    var operFlagTable = "";
    var operPromitTable = "";
    var loadMask_addTable = null;
    var loadMask_updateTable = null;
    var loadMask_removeTable = null;
    var loadMask_resetTable = null;
    var loadMask_enabledTable = null;

    var resetFormTable = function(table) {
        clearForm($form[0]);
        $form.find("input[name='enabled']").val("true");
        for (var p in tracywindyObject) {
            var obj = tracywindyObject[p];
            if ("combobox" == obj.objectType) {
                var renderToInner = $form.find("#" + obj.renderToObj.id)[0];
                if (renderToInner) {
                    obj.setValue('');
                }
            }
        }
    };
    var loadFormDataTable = function(table, rowIndex) {
        //resetFormTable();
        var currentRowData = table.getRowDataAt(rowIndex);
        var arr = $form[0].elements;
        jQuery.each(arr,
        function(k, v) {
            var name = v.name;
            var realValue = currentRowData[name.toLowerCase()];
            if (name && ("undefined" != typeof(realValue))) {
                if (("INPUT" == v.tagName) && (("RADIO" == (v.getAttribute("type") || "").toUpperCase()) || ("CHECKBOX" == (v.getAttribute("type") || "").toUpperCase()))) {
                    var valueArr = realValue.split(",");
                    for (var i = 0; i < valueArr.length; i++) {
                        var value_ = valueArr[i];
                        if (v.value == value_) {
                            v.checked = true;
                        }
                    }

                } else {
                    v.value = currentRowData[name.toLowerCase()];
                }
            }
        });
        if (config.loadFormDataCallBack) {
            config.loadFormDataCallBack(table, $form, rowIndex);
        }
    };

    var defaultCallback = function(table, responseText) { //继承孙传良修改
        responseText = responseText.responseText||"";
        responseText = responseText.replace(/(^\s+)|(\s+$)/g, "");
        if (responseText != '') {
            alert(responseText);
        } else {
            alert(("{0}" + entityPromitTable + "成功").replace("{0}", operPromitTable));
        }
    };

    var successCallBack = function(table, responseText) { //优先用户回调，再默认回调
        if (config.successCallBack) {
            config.successCallBack(table, responseText);
        }
        defaultCallback(table, responseText);
        table.reload();
    };

    var validateForm = function(table) {
        if ("undefined" != typeof(config.validateFrom)) {
            if (!config.validateFrom(table, $form)) {
                return false;
            }
        }
        return Validator.Validate($form[0], 1, false);
    };
    var addValidate = config.addValidate ||
    function(table, $form) {
        return true;
    };
    var addEntity = function() {
        if (!addValidate(table, $form)) {
            return;
        }
        resetFormTable();
        var cur_date = getCurDate(new Date());
        $("form input[isDefaultDate='true']").each(function(i, tinput) {
            tinput.value = cur_date;
        });
        if (config.resetFormCallback) {
            config.resetFormCallback(table, $form, "add");
        }
        operFlagTable = "add";
        operPromitTable = "新增";
        showWindowTable(table,$form,operFlagTable);
    };
    table.addEntity = addEntity;
    var updateEntity = function(rowIndex) {
        var checkedRowDatas = this.getCheckedRowDatas();
        if (0 == checkedRowDatas.length) {
            alert("请选择需要修改的记录");
            return;
        }
        if (1 < checkedRowDatas.length) {
            alert("只能选择单行记录进行修改");
            return;
        }
        rowIndex = checkedRowDatas[0].rowIndex;
        resetFormTable();
        if (config.resetFormCallback) {
            config.resetFormCallback(table, $form, "update");
        }
        loadFormDataTable(table, rowIndex);
        operFlagTable = "update";
        operPromitTable = "修改";
        showWindowTable(table,$form,operFlagTable);
    };
    table.updateEntity = updateEntity;
    var removeEntity = function(rowIndex) {
        var checkedRowDatas = this.getCheckedRowDatas();
        if (0 == checkedRowDatas.length) {
            alert("请选择需要删除的记录");
            return;
        }
        if (1 < checkedRowDatas.length) {
            alert("只能选择单行记录进行删除");
            return;
        }
        rowIndex = checkedRowDatas[0].rowIndex;
        resetFormTable();
        loadFormDataTable(table, rowIndex);
        operFlagTable = "remove";
        operPromitTable = "删除";
        operationTable();
    };
    table.removeEntity = removeEntity;

    var resetPassword = function(rowIndex) {
        /*var checkedRowDatas = this.getCheckedRowDatas();
		if(0 == checkedRowDatas.length){
			alert("请选择需要重置密码的记录");
			return;
		}
		if(1 < checkedRowDatas.length){
			alert("只能选择单行记录进行重置密码");
			return;
		}
		rowIndex = checkedRowDatas[0].rowIndex;*/
        resetFormTable();
        loadFormDataTable(table, rowIndex);
        operFlagTable = "reset";
        operPromitTable = "重置密码";
        operationTable();
    };
    table.resetPassword = resetPassword;
    var updateEnabledTable = function(rowIndex) {
        resetFormTable();
        loadFormDataTable(table, rowIndex);
        operFlagTable = "enabled";
        operPromitTable = "更改";
        operationTable();
    };
    table.updateEnabledTable = updateEnabledTable;
    function showWindowTable(table,$form,operFlagTable) {
    	// showWindowTable(table,$form,operFlagTable);
    	if(config.beforeShowWindowCallBack){
    		if(!config.beforeShowWindowCallBack(table,$form,operFlagTable)){
    			return;
    		}
    	}
    	var currentWindowTop = windowTop;
    	if(scrollWindowTopContainer){
    		 var currentScrollContainer = scrollWindowTopContainer;
    		 if (!(typeof(scrollWindowTopContainer) == 'object')) {
    		     currentScrollContainer = document.getElementById(scrollWindowTopContainer);
    		 }	
    		 currentWindowTop = windowTop+currentScrollContainer.scrollTop;
    	}
    	currentWindowTop+=document.body.scrollTop;
        var $window = jQuery("#" + windowContainerIdTable);
        $window.show();
        $window.dialog({
            top: currentWindowTop,
            autoScroll: true
        });
        $window.dialog('open');
        if (config.afterShowWindowCallBack) {
            config.afterShowWindowCallBack($form);
        }
    }

    var operationTable = function() {
        if (operFlagTable != "remove" && !validateForm(table)) { //孙传良 修改 删除时不做校验
            return;
        }
        if (!window.confirm(("确认{0}当前" + entityPromitTable + "么？").replace("{0}", operPromitTable))) {
        	return;
        } else {
        	//用户增删改查操作
            var operMethod = operFlagTable + constantFlagTable;
            var loadMaskMsg = ("正在{0}" + entityPromitTable + "   请稍后... ").replace("{0}", operPromitTable);
            var currentLoadMask = null;
            switch (operFlagTable) {
            case "add":
                {
                    if (null == loadMask_addTable) {
                        loadMask_addTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_addTable;
                    break;
                }
            case "update":
                {
                    if (null == loadMask_updateTable) {
                        loadMask_updateTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_updateTable;
                    break;
                }
            case "remove":
                {
                    if (null == loadMask_removeTable) {
                        loadMask_removeTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_removeTable;
                    break;
                }
            case "reset":
                {
                    if (null == loadMask_resetTable) {
                        loadMask_resetTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_resetTable;
                    operMethod = operFlagTable + "Password";
                    break;
                }
            case "enabled":
                {
                    if (null == loadMask_enabledTable) {
                        loadMask_enabledTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_enabledTable;
                    operMethod = "update" + constantFlagTable + "Enabled";
                    break;
                }
            default:
                {
                    if (null == loadMask_updateTable) {
                        loadMask_updateTable = new tracywindyLoadMask(document.body, loadMaskMsg);
                    }
                    currentLoadMask = loadMask_updateTable;
                    break;
                }
            }
            currentLoadMask.show();
            var url = getRootPath() + "/" + prefixMVC + "/{0}".replace("{0}", operMethod) + suffixMVC;
            var params = $form.tracywindySerializeFormToJsonObject(true);
            if (config.beforeSubmitData) {
                config.beforeSubmitData($form, params);
            }
            ajaxRequest({
                url: url,
                params: params,
                timeout: 30 * 1000,
                success: function(res) {
	            	if(config.submitReturnCallBack){
	            		config.submitReturnCallBack(table,("{0}" + entityPromitTable + "成功").replace("{0}", operPromitTable),res.responseText,windowContainerIdTable);
	            	}else{
	                    try {
	                        jQuery("#" + windowContainerIdTable).dialog('close');
	                    } catch(e) {}
	                    successCallBack(table, res);
	            	}
	            	currentLoadMask.hide();
                }
            });
        }
    };
    table.operationTable = operationTable;
};