//获得table
var tracywindy_tables = {};

if (!window.JsonUtil) {
    alert("请导入json的js类库");
}

function getTracywindyTable(tableId) {
    return getTracywindyObject(tableId);
}
var tracywindyDynamicLoadJs = function(jsLocation) {
    ajaxRequest({
        url: jsLocation,
        async: false,
        success: function(response) {
            var source = (response.responseText);
            var oHead = document.getElementsByTagName("HEAD").item(0);
            var oScript = document.createElement("script");
            oScript.language = "javascript";
            oScript.type = "text/javascript";
            oScript.defer = true;
            oScript.text = source;
            oHead.appendChild(oScript);
        },
        failure: function(response) {},
        params: {}
    });
};

function expandOrCollapseQueryArea(tableId,isInit) {
	var currentOperTable = getTracywindyObject(tableId);
	if( isInit && currentOperTable){
		if(!currentOperTable.isQueryAreaInit ){
			currentOperTable.isQueryAreaInit = true;
			if(currentOperTable.queryAreaDiv){
				var $me = currentOperTable;
			    var oldDisplay = "";
			    if ($me.displayToggleContainerObj) {
			        oldDisplay = $me.displayToggleContainerObj.style.display;
			        $me.displayToggleContainerObj.style.display = "block";
			    }
			    currentOperTable.queryAreaDivHeight = currentOperTable.queryAreaDiv.offsetHeight;
			    currentOperTable.tableDiv.style.height = (parseInt(currentOperTable.tableDiv.style.height) - currentOperTable.queryAreaDivHeight) + "px";
			    if ($me.displayToggleContainerObj) {
			        $me.displayToggleContainerObj.style.display = oldDisplay;
			    }
			}
		}
		return;
	}
    if ("none" == currentOperTable.queryAreaDiv.style.display) {
        document.getElementById(tableId + "_expandOrCollapseButton").value = "折叠";
        currentOperTable.queryAreaDiv.style.display = "";
        currentOperTable.queryAreaDivHeight = currentOperTable.queryAreaDiv.offsetHeight;
        if (!currentOperTable.isPagePrint && !currentOperTable.isAutoHeight) {
            currentOperTable.tableDiv.style.height = (parseInt(currentOperTable.tableDiv.style.height) - currentOperTable.queryAreaDivHeight) + "px";
            try {
                currentOperTable.lockedTableDiv.style.height = (parseInt(currentOperTable.lockedTableDiv.style.height) - currentOperTable.queryAreaDivHeight) + "px";
                currentOperTable.lockedTableDiv.oldHeight = parseInt(currentOperTable.lockedTableDiv.style.height);
                currentOperTable.tableDiv.oldHeight = (parseInt(currentOperTable.tableDiv.oldHeight) - currentOperTable.queryAreaDivHeight);
            } catch(e) {}
        }
    } else {
        document.getElementById(tableId + "_expandOrCollapseButton").value = "显示";
        currentOperTable.queryAreaDiv.style.display = "none";
        if (!currentOperTable.isPagePrint && !currentOperTable.isAutoHeight) {
            currentOperTable.tableDiv.style.height = (parseInt(currentOperTable.tableDiv.style.height) + currentOperTable.queryAreaDivHeight) + "px";
            try {
//              currentOperTable.lockedTableDiv.style.height = (parseInt(currentOperTable.lockedTableDiv.style.height) + currentOperTable.queryAreaDivHeight) + "px";
                currentOperTable.lockedTableDiv.style.height = (parseInt(currentOperTable.lockedTableDiv.style.height) + currentOperTable.queryAreaDivHeight) + "px";
                currentOperTable.lockedTableDiv.oldHeight = parseInt(currentOperTable.lockedTableDiv.style.height);
                currentOperTable.tableDiv.oldHeight = (parseInt(currentOperTable.tableDiv.oldHeight) + currentOperTable.queryAreaDivHeight);
            } catch(e) {}
        }
    }

}
function popupReadOnlyWindow(table,rowData){
	var columns = table.columns;
	var tableId = table.id;
	var itemLen = 0;
	var itemHeaders = [];
	var itemValues  = [];

	for(var i = 0;i < columns.length;i++){
		var column = columns[i];
		if(!column.hidden){
			var itemValue = rowData[column.mapping||column.name];
			if("undefined" != typeof(itemValue)){
				itemLen++;
				itemHeaders.push(column.header);
				itemValues.push(column['renderer'](itemValue, table, "", -1, rowData));
			}
		}
	}
	var itemWindowHtmlArr = [];
	var windowId = tableId+"popupReadOnlyWindow";
	var windowDom = document.getElementById(windowId);
	if(windowDom){
		if('BODY'!=jQuery("#"+windowId).parent()[0].tagName){
		    jQuery("#"+windowId).parent().remove();
		}else{
			jQuery("#"+windowId).remove();
		}
	    jQuery("#"+windowId+"dlg-buttons").remove();
	}
	var panelWidth = 500;
	itemWindowHtmlArr.push("<div id='"+windowId+"'class='popupReadOnlyWindow' closed='false' modal='true' style='display:none;'>");
	itemWindowHtmlArr.push("<div id='"+windowId+"Title' style='padding-bottom:10px;width:100%;text-align:right;'><div style='float:left;margin-left:20px;'>数据明细</div><a style='width:60px;cursor:pointer;'  onclick='document.getElementById(\""+windowId+"\").style.display=\"none\"'>×</a></div>"); 
	itemWindowHtmlArr.push("<div style='width:"+panelWidth+"px;height:200px;overflow:auto;'><table id='"+windowId+"Table' class='popupReadOnlyWindowTable' ><tbody>");
	for(var i = 1;i <= itemHeaders.length;i++){
		var itemHeader = itemHeaders[i-1];
		var itemValue  = itemValues[i-1];
		itemWindowHtmlArr.push("<tr>");
		itemWindowHtmlArr.push("<td class='input-label'>"+itemHeader+"：</td>");
		itemWindowHtmlArr.push("<td class='input-value'>"+itemValue+"</td>");
		itemWindowHtmlArr.push("</tr>");
	}
	itemWindowHtmlArr.push("</tbody></table></div></div>");
	jQuery(document.body).append(jQuery(itemWindowHtmlArr.join("")));

	var clientWidth  = ((0 == document.documentElement.clientWidth)  ? document.body.clientWidth : document.documentElement.clientWidth);
	var clientHeight = ((0 == document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight);
    jQuery("#"+windowId).show();
    var offsetHeight = document.getElementById(windowId+"Table").offsetHeight;
    var adjustWidth   =  ((clientWidth - 26) > panelWidth   ) ? panelWidth : (clientWidth  - 26);
    var adjustHeight  =  ((offsetHeight + 80) > clientHeight ) ? (clientHeight - 80) : offsetHeight;
	 jQuery("#"+windowId).css({
		 position:"absolute",
		 zIndex:999999,
		 background:"#FFFFFF",
		 top : 20 + Math.max(document.documentElement.scrollTop,document.body.scrollTop)+"px",
		 left: (( clientWidth - adjustWidth - 24 )/2 + Math.max(document.documentElement.scrollLeft,document.body.scrollLeft)) +"px"
	 });
    jQuery("#"+windowId+"Table").parent().css({
    	width:adjustWidth+"px",
    	height:adjustHeight+"px"
    });
    jQuery("#"+windowId).draggable({
    	handle:'#'+windowId+"Title"
    });
	/*var config = {
				zIndex:999999,
	        	draggable:true,
	        	title:'数据预览',
	        	resizable:true,
	        	shadow:true,
	        	modal:true,
	        	inline:false,
	        	collapsible:false,
	        	minimizable:false,
	        	maximizable:false,
	        	closable:true
	   };
	jQuery("#"+windowId).dialog(config);
	jQuery("#"+windowId).dialog("open");*/
}
//导出excel function
function exportExcelData(tableId) {
	var pageNum = jQuery('.x-panel-table-div-page-number');
	pageNum.each(function(i,e){
		if(i == 1){
			var d = new Date();
			var day = d.getHours();
			var pageNum = Number(jQuery(e).html());
			if(pageNum <= 50000){
				exportExcelDataFormSubmit(tableId);
			}else if(pageNum > 50000 && pageNum <=100000){
				//在上班期间不能下载
				if((day>=8 && day<12)||(day>=14&&day<18)){
					alert('抱歉，数据量过大，请在非工作期间下载！');
					return;
				}else{
					exportExcelDataFormSubmit(tableId);
				}
			}else{
				alert('数据量过大，请过滤后下下载！');
				return;
			}
		}
	});
	
    
}
function exportExcelDataFormSubmit(tableId){
	var table = getTracywindyTable(tableId);
    var params = table.params;
    var titleSpan = document.getElementById(table.id + '_titleSpan');
    var excelTitleName = "sheet";
    if (titleSpan) {
        excelTitleName = titleSpan.innerText || titleSpan.textContent; // encodeURI(titleSpan.innerText||titleSpan.textContent);
    }
    var form = document.getElementById("id_exportExcelForm");
    if (form) {
        document.body.removeChild(form);
    }
    form = document.createElement("form");
    form.id = "id_exportExcelForm";
    form.target = ""; //tableId+"_exportExcelFormIFrame";
    var tempInnerHtml = "";
    var complexHeaders = [];
    /*for(var i=0;i<table.lockedComplexHeaders.length;i++){
		complexHeaders.push(table.lockedComplexHeaders[i]);
	}
	for(var i=0;i<table.complexHeaders.length;i++){
		complexHeaders.push(table.complexHeaders[i]);
	}*/
    complexHeaders = table.complexHeaders;
    if (0 < table.exportComplexHeaders.length) {
        complexHeaders = table.exportComplexHeaders;
    }
    var complexHeadersStr = JsonUtil.encode(complexHeaders).replace(/'/gi, '&#39;');
    tempInnerHtml += "<input type='hidden' name='complexHeadersStr' value='" + complexHeadersStr + "'/>";
    var columnsStr = JsonUtil.encode(table.columns).replace(/'/gi, '&#39;');
    tempInnerHtml += "<input type='hidden' name='columnsStr' value='" + columnsStr + "'/>";
    if ('local' == table.loadMode) {
        var datasStr = JsonUtil.encode(table.tableData).replace(/'/gi, '&#39;');
        tempInnerHtml += "<input type='hidden' name='datasStr' value='" + datasStr + "'/>";
    }
    params['excelTitleName'] = excelTitleName;
    for (var p in params) {
        var tempStr = params[p];
        if (Object.prototype.toString.call(params[p]) == '[object String]') {
            tempStr = params[p].replace(/'/gi, '&#39;');
            //.replace(/"/gi,'&#34');
        }
        tempInnerHtml += "<input type='hidden' name='" + p + "' value='" + tempStr + "'/>";
    }
    tempInnerHtml += "<input type='hidden' name='exportExcelVersion' value='" + table.exportExcelVersion + "'/>";
    tempInnerHtml += "<input type='hidden' name='loadMode' value='" + table.loadMode + "'/>";
    tempInnerHtml += "<input type='hidden' name='isExportTitle' value='" + table.isExportTitle + "'/>";
    tempInnerHtml += "<input type='hidden' name='isTableExportExcel' value='true'/>";
    tempInnerHtml += "<input type='hidden' name='BrowserType' value='" + SysBrowser.getBrowser().toLowerCase() + "'/>";
    tempInnerHtml += "<input type='hidden' name='forceExportExcelIndexs' value='" + table.forceExportExcelIndexs + "'/>";
    with(form) {
        var exportExcelUrl = "";
        if (table.exportExcelUrl.indexOf("?") > -1) {
            exportExcelUrl = table.exportExcelUrl + "&decorate=no&randomNumber=" + Math.random();
        } else {
            exportExcelUrl = table.exportExcelUrl + "?decorate=no&randomNumber=" + Math.random();
        }
        action = exportExcelUrl;
        method = "post";
        style.display = "none";
        innerHTML = tempInnerHtml;
    }
    document.body.appendChild(form);
    form.submit();
}
//导入excel function
function importExcelData(tableId, url, columns) {

    var table = getTracywindyObject(tableId);
    var tempurl = url || '/table/importExcel.action';
    var tempcolumns = columns || table.columns;
    if (!window.currentImportExcelLoadMask) {
        window.currentImportExcelLoadMask = new tracywindyLoadMask(document.body, "文件上传中  请稍等...");
    }
    if (!document.getElementById("id_importExcelFormWindow")) {
        var uploadAttachmentFileWindow_html = "";
        uploadAttachmentFileWindow_html += '<div id="id_importExcelFormWindow"  closed="true" modal="true" title="模板数据上传" style="display:none;width:300px;text-align:center;background-color:#FDF9F8;">';
        uploadAttachmentFileWindow_html += '  <center>';
        uploadAttachmentFileWindow_html += '       <div style="width:95%;">';
        uploadAttachmentFileWindow_html += '          <iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
        uploadAttachmentFileWindow_html += '          <form id="id_importExcelForm" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
        uploadAttachmentFileWindow_html += '	        <table align="center" style="width:90%">';
        uploadAttachmentFileWindow_html += '		            <tr><td colspan=2><input type="hidden" name="currentTableId" value="' + tableId + '"></td></tr>';
        uploadAttachmentFileWindow_html += '		            <tr><td colspan=2><input type="hidden" name="skipRowCount" value="' + table.skipRowCount + '"></td></tr>';
        uploadAttachmentFileWindow_html += '		            <tr><td colspan=2><input type="hidden" id="id_importExcelColumnStr" name="importExcelColumnStr" /></td></tr>';
        uploadAttachmentFileWindow_html += '		            <tr><td colspan=2><input type="file" name="tableImportExcel" style="border:1px solid #DDD;cursor:pointer;"></td></tr>';
        uploadAttachmentFileWindow_html += '		            <tr class="content-separator">';
        uploadAttachmentFileWindow_html += '			            <td colspan="2">';
        uploadAttachmentFileWindow_html += '					        <a href="javascript:void(0);" class="btn btn-primary" id="' + tableId + 'ImportExcelFormSubmitButton" ><span>确定</span></a>';
        uploadAttachmentFileWindow_html += '					        <a href="javascript:void(0);" class="btn btn-primary" id="' + tableId + 'ImportExcelFormCancelButton" style="margin-left:20px;"  ><span>取消</span></a>';
        uploadAttachmentFileWindow_html += '			            </td>';
        uploadAttachmentFileWindow_html += '		            </tr>';
        uploadAttachmentFileWindow_html += '		        </table>';
        uploadAttachmentFileWindow_html += '	        </form>';
        uploadAttachmentFileWindow_html += '        </div>';
        uploadAttachmentFileWindow_html += '    </center>';
        uploadAttachmentFileWindow_html += '</div>';

        jQuery(document.body).append(uploadAttachmentFileWindow_html);
        jQuery("#" + tableId + "ImportExcelFormSubmitButton").click(function(e) {
            //alert(tempurl);
            importExcelFormSubmit(getRootPath() + tempurl);
        });
        jQuery("#" + tableId + "ImportExcelFormCancelButton").click(function(e) {
            jQuery("#id_importExcelFormWindow").window("close");
        });
    }

    var columnsStr = JsonUtil.encode(tempcolumns);
    //.replace(/'/gi,'&#39;');
    document.getElementById("id_importExcelColumnStr").value = columnsStr;
    jQuery("#id_importExcelFormWindow").show();
    jQuery("#id_importExcelFormWindow").window({
        top: 200
    });
    jQuery("#id_importExcelFormWindow").window("open");
}

function importExcelFormSubmit(url) {
    window.currentImportExcelLoadMask.show();
    var importExcelForm = document.getElementById("id_importExcelForm");
    with(importExcelForm) {
        action = url;
        submit();
    }
    try {
        jQuery("#id_importExcelFormWindow").window("close");
    } catch(e) {}
    return false;
}

function importExcelFormCallBack(result, currentTableId) {
    var table = getTracywindyObject(currentTableId);
    table.tableData = JsonUtil.decode(result);
    createDataTable(table);
    window.currentImportExcelLoadMask.hide();
}

//showRank Check 
var tracywindyTable = function(config) {
    //判断是够创建过
    if(config.id){
    	if(getTracywindyObject(config.id)){
    		return;
    	}
    }
    //js中本table对象的id
    this.id = config.id || GenerateGuid();
    //附件上传联合主键
	this.unionKey      = config.unionKey;
	this.checkedKey    = config.checkedKey;
	this.checkAttachmentInfoFuncCallback = config.checkAttachmentInfoFuncCallback;
    //表配置
    this.tableConfig = config;

    //根据行号或者列数据实现对行颜色的调控，回掉方法
    this.rowColorCallback = config.rowColorCallback ||
    function(index, rowData) {
        return "";
    };

    
    //select数据是否在页面初始化时即加载数据
    this.lazyLoad = config.lazyLoad || false;

    //
    if (window.isPagePrint) {
        this.isPagePrint = true;
    }

    //判断客户端类型是否是mobile
    this.isMobile = (SysBrowser.android || SysBrowser.iPhone /*|| SysBrowser.iPad*/);
    this.isPad = SysBrowser.iPad;
    //对象类型
    this.objectType = 'table';

    //表格数据来源sql语句所在的xml文件名称
    this.xmlFileName = config.xmlFileName || '';

    //需要导出excel数据的行索引
    this.forceExportExcelIndexs = config.forceExportExcelIndexs || "";

    //列模式
    this.cm = config.columnModel || {};

    //参数列表，通过sql查询数据时需要的sql参数
    this.params = config.params || {};
    this.params['pageLocationHref'] = window.location.href;
    if(config.beforeTableParamsLoadServiceName){
    	this.params['beforeTableParamsLoadServiceName'] = config.beforeTableParamsLoadServiceName;
    }
    //列是否自动填满窗口，均分窗口宽度，默认不均分，超过后显示横项滚动条
    this.isFit = config.isFit || false;
    this.isShortPage = config.isShortPage || false;
    if(this.isMobile||this.isPad){
    	this.isShortPage = true;
    }
    this.columns = config.columns || [];
    //列模型
    this.remainColumns = config.remainColumns || false;
    if (this.columns.length > 0) {
        this.remainColumns = true;
    }
    //是否显示边框
    this.border = config.border || false;
    this.isExportTitle = config.isExportTitle;

    //值为空时显示的默认值
    this.emptyChar = ("undefined" == typeof(config.emptyChar)) ? ".&nbsp;&nbsp;": config.emptyChar;

    //影响标题栏
    this.complexHeadersCallBack =config.complexHeadersCallBack||function(){}; 
    this.complexHeaders = config.complexHeaders || [];
    this.columnAddWidth = config.columnAddWidth || (this.complexHeaders.length > 0 ? -3 : 0);
    this.lockedComplexHeaders = config.lockedComplexHeaders || [];
    this.exportComplexHeaders = config.exportComplexHeaders || [];
    this.fitColumnSizeAdd = config.fitColumnSizeAdd || -10;
    this.complexHeadersAddHeight = config.complexHeadersAddHeight || 0;
    this.toolsLeftMargin = config.toolsLeftMargin || 20;
    this.toolsAdd = config.toolsAdd || 1;
    this.forceUseColumn = config.forceUseColumn || false;
    this.isCustomTools = config.isCustomTools || false;
    this.queryArea = config.queryArea;

    //是否隐藏字段值当字段长度超过列长度是true:不隐藏，false:隐藏
    this.isAutoBreakContent = config.isAutoBreakContent || false;
    this.statisticColumnNames = config.statisticColumnNames || [];
    this.params['summaryFields'] = this.statisticColumnNames.join(",");
    this.precise = config.precise || 2;
    this.skipRowCount = config.skipRowCount || 0;
    //导出excel时的版本
    this.exportExcelVersion = config.exportExcelVersion || "2007";
    //锁定列数据，滚动时列保持不动
    this.lockedNames = config.lockedNames || [];
    this.isReport = config.isReport || false;

    this.needLoadMask = ((config.needLoadMask == false) ? false: true);

    if (this.id) {
        tracywindy_tables[this.id] = this;
        tracywindyObject[this.id] = this;
    }

    //表格显示在哪个页面对象中
    this.renderTo = config.renderTo;
    this.displayToggleContainer = config.displayToggleContainer;
    var clientTableCalWidth = (Math.max(document.documentElement.clientWidth, (document.body || document.documentElement).clientWidth));
    this.width = (config.width) || (clientTableCalWidth - 2);
    this.width-=2;
    if ((this.width + "").indexOf("%") > -1) {
        this.width = clientTableCalWidth * (parseFloat((this.width + "").replace(/\s/gi, "")) / 100 - 2);
    }
    this.height  = config.height ;

    //是否高度自适应
    this.isAutoHeight = config.isAutoHeight || false;
    this.isAutoHeight = (!this.height) || this.isAutoHeight;

    this.showHeader = ((config.showHeader == false) ? false: true);

    this.datas = config.datas || [];
    this.tools = config.tools || [];
    if ((window.isViewHistoryTask && !config.isForceTools)  || this.isPagePrint ) {
        this.tools = [];
    }

    this.tableData = this.datas;

    this.title = (config.title || "");
    //this.isExcel = (config.isExcel == false )?false:true;
    this.isExcel = config.isExcel || false;
    this.callBack = config.callBack;

    //分页
    this.basicInfo = config.basicInfo || {
        datas: 'datas',
        total: 'total'
    };
    this.isPage = config.isPage || false;
    this.isRank = config.isRank || false;
    this.isCheck = config.isCheck || false;
    if (this.isPagePrint) {
        this.isCheck = false;
    }
    this.onCheckedCallback = config.onCheckedCallback;
    
    this.isStatistic = config.isStatistic || false;
    this.checkOnly = config.checkOnly || false;
    this.checkType = (("radio" != config.checkType) ? "checkbox": "radio"); //"radio","checkbox"
    this.pageSize = config.pageSize || 20;
    if (!this.isPage) {
        this.pageSize = 99999999;
    }
    //本地分页loadmode='local'
    this.total = config.total || config.total;
    this.start = config.start || 0;

    this.rankSize = config.rankSize || 20;
    this.checkSize = config.checkSize || 24;
    this.url = config.url || (getRootPath() + '/table/getTableData.action');
    this.exportExcelUrl = config.exportExcelUrl || (getRootPath() + '/table/getExcelExportData.action');
    this.completeCallBack = config.completeCallBack;
    this.jsonData = {};
    //加载数据方式
    this.loadMode = config.loadMode || 'ajax';
    this.isColumnResizable = (false == config.isColumnResizable) ? false: true;
    this.resizableAddSize = config.resizableAddSize || 0;
    this.isRemoteSortable = ((false == config.isRemoteSortable) || ('ajax' != this.loadMode)) ? false: true;
    //判断是否是锁定
    this.isNeedLocked = (this.lockedNames.length > 0) ;//|| this.isRank || this.isCheck;
    
    this.showQueryAreaOnDocumentReady = (config.showQueryAreaOnDocumentReady==false)?false:true;
    //创建table
    this.createTableDetail();

};
/******创建查询区域（开始）*************************************/
//同步高度
tracywindyTable.prototype.synchronizedHeight = function(){
	var $me = this;
    var oldDisplay = "";
    if ($me.displayToggleContainerObj) {
        oldDisplay = $me.displayToggleContainerObj.style.display;
        $me.displayToggleContainerObj.style.display = "block";
    }
    if($me.lockedTable){
    	var lockedWidth = $me.lockedTableDiv.clientWidth;
		if(this.showHeader){
			lockedWidth = $me.lockedTableHeaderDiv.clientWidth;
		}
    	if($me.span){
    		$me.tableHeaderDiv.style.width = ($me.width-lockedWidth-16)+"px";
    	}else{
    		$me.tableHeaderDiv.style.width = ($me.width-lockedWidth+1)+"px";
    	}
    	//$me.tableHeaderDiv.style.width = ($me.width-lockedWidth+1)+"px";
    	$me.tableDiv.style.width = ($me.width-lockedWidth+1)+"px";
    }else{
    	if($me.span){
    		$me.tableHeaderDiv.style.width = ($me.width-15)+"px";
    	}else{
    		$me.tableHeaderDiv.style.width = ($me.width+1)+"px";
    	}
    	$me.tableDiv.style.width = ($me.width+2)+"px";
    }
    var clientWidth = $me.tableDiv.clientWidth;
    if($me.showHeader){
    	clientWidth = $me.tableHeaderDiv.clientWidth;
    }
    var scrollWidth = $me.tableDiv.scrollWidth;
    if($me.showHeader){
    	scrollWidth = $me.tableHeaderDiv.scrollWidth;
    }
    // modify by tracywindy revise the last row was covered by the container's scollbar
    if($me.isAutoHeight && (clientWidth < scrollWidth) && ("IE" == SysBrowser.getBrowser()) ){
        var realHeight = Math.max($me.tempDatas.length * 24, $me.table.offsetHeight);
        $me.tableDiv.style.height = (realHeight+18)+"px";
    }
    var clientHeight = $me.tableDiv.clientHeight;
    if ($me.displayToggleContainerObj) {
        $me.displayToggleContainerObj.style.display = oldDisplay;
    }
    if($me.lockedTable){
  		    $me.lockedTableDiv.style.height = (clientHeight)+"px";
		  	/*if(scrollWidth == clientWidth){
      		  	try{
                    if(!$me.lockedTableDiv.oldHeight){
                    	$me.lockedTableDiv.oldHeight = parseInt($me.lockedTableDiv.style.height);
                    }
                    $me.lockedTableDiv.style.height = (parseInt($me.lockedTableDiv.style.height)+16)+"px";
    			}catch(e){}	
    			
          }*/
    }
};
tracywindyTable.prototype.initQueryAreaContainer = function() {
    this.queryAreaDiv = document.createElement("div");
    this.queryAreaDiv.className = "x-panel-table-div-query-area";
    this.queryAreaDiv.id = this.id + "_queryAreaDivContainer";
    this.mainDiv.appendChild(this.queryAreaDiv);
    this.queryAreaDiv.innerHTML = this.queryArea || "";
};
tracywindyTable.prototype.createQueryArea = function() {
    this.queryAreacomboboxConfigArray = [];
    var tableConfig = this.tableConfig;
    var columns = this.columns;
    this.queryArea = this.queryArea || "";
    var queryAreaTableTbody   = null;
    var queryAreaTableTbodyTr = null;
    if (this.queryArea) {
        this.initQueryAreaContainer();
    }
    var isInitQueryArea = false;
    for (var i = 0; i < columns.length; i++) {
        var columnConfig = columns[i];

        var isHidden = columnConfig.hidden || false;
        if (typeof isHidden == "string") {
            if (isHidden.toLowerCase == "true") {
                isHidden = false;
            } else if (isHidden.toLowerCase == "false") {
                isHidden = false;
            }
        }
        isHidden = false;
        var columnQueryConfig = columnConfig.queryConfig;
        if (!isHidden && columnQueryConfig) {
            if (!isInitQueryArea) {
                if (!this.queryAreaDiv) {
                    this.initQueryAreaContainer();
                }
                this.queryAreaTableDivContainer = document.createElement("div");
                this.queryAreaTableDivContainer.className = "x-panel-table-div-query-area-auto-config";
                this.queryAreaDiv.appendChild(this.queryAreaTableDivContainer);
                var queryAreaTable = document.createElement("table");
                this.queryAreaTable = queryAreaTable;
                this.queryAreaTableDivContainer.appendChild(this.queryAreaTable);
                queryAreaTableTbody = document.createElement("tbody");
                queryAreaTable.appendChild(queryAreaTableTbody);
                queryAreaTableTbodyTr = document.createElement("tr");
                queryAreaTableTbody.appendChild(queryAreaTableTbodyTr);
                isInitQueryArea = true;
            }
            if (columnQueryConfig.isNewLine) {
                queryAreaTableTbodyTr = document.createElement("tr");
                queryAreaTableTbody.appendChild(queryAreaTableTbodyTr);
            }
            var rowSpan = columnQueryConfig.rowSpan || 1;
            var colSpan = columnQueryConfig.colSpan || 1;
            var queryAreaTableTbodyTdLabel = document.createElement("td");
            queryAreaTableTbodyTdLabel.style.borderRight = "1px solid transparent";
            queryAreaTableTbodyTr.appendChild(queryAreaTableTbodyTdLabel);
            queryAreaTableTbodyTdLabel.rowSpan = rowSpan;
            var queryAreaTableTbodyTdValue = document.createElement("td");
            queryAreaTableTbodyTdValue.rowSpan = rowSpan;
            queryAreaTableTbodyTdValue.colSpan = colSpan;
            queryAreaTableTbodyTr.appendChild(queryAreaTableTbodyTdValue);
            var labelContent = (columnConfig['header'] || columnConfig['name']) + "：";
            queryAreaTableTbodyTdLabel.innerHTML = labelContent;
            var valueContent = '';
            var dataType = columnConfig['type'] || 'text';
            var inputName = this.id + "_queryArea_" + (columnConfig['mapping'] || columnConfig['name']);
            var otherAttributes = columnConfig['otherAttributes'];
            if (!otherAttributes) {
                otherAttributes = "";
            }
            var otherClasses = " td-content-readonly ";
            //this.queryAreacomboboxConfigArray = [];
            switch (dataType.toLowerCase()) {
            case "text":
                {
                    valueContent = '<input type="text"  name="' + inputName + '" onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\' style=\'width:100px\'  class="td-content-input"/>';
                    break;
                }
            case "textarea":
                {
                    valueContent = '<textarea  name="' + inputName + '" class="td-content-input "></textarea>';
                    break;
                }
            case "number":
                {
                    otherAttributes += ' dataType="Number" ';
                    valueContent = '<input type="text" style=\'width:100px\' name="' + inputName + '" onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\' class="td-content-input" ' + otherAttributes + ' />';
                    if (columnConfig['isRange']) {
                    	valueContent = '<span style="float:left;"><input type="text" name="' + inputName + '_start" style=\'width:50px\' onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\'  ' + otherAttributes + ' />';
                        valueContent += '&nbsp;到&nbsp;<input type="text" name="' + inputName + '_end" style=\'width:50px\' onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\'  ' + otherAttributes + ' /></span>';
                    }
                    break;
                }
            case "double":
                {
                    otherAttributes += ' dataType="Double" ';
                    valueContent = '<input style=\'width:100px\' type="text" name="' + inputName + '" onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\' class="td-content-input" ' + otherAttributes + ' />';
                    if (columnConfig['isRange']) {
                    	valueContent = '<span style="float:left;"><input type="text" name="' + inputName + '_start" style=\'width:50px\' onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\'  ' + otherAttributes + ' />';
                        valueContent += '&nbsp;到&nbsp;<input type="text" name="' + inputName + '_end" style=\'width:50px\' onkeydown=\'doQuickQuery(event,getTracywindyObject("' + this.id + '"))\'  ' + otherAttributes + ' /></span>';
                    }
                    break;
                }
            case "date":
                {
                    var dateFormat = columnConfig['dateFormat'] || 'yyyy-MM-dd';
                    valueContent = '<input readonly type="text" name="' + inputName + '" class="Wdate td-content-input' + otherClasses + '" onClick="WdatePicker(this,{readOnly:true,dateFmt:\'' + dateFormat + '\'})" ' + otherAttributes + ' />';
                    if (columnConfig['isRange']) {
                        valueContent = '<div style="width:200px;float:left;"><span style="float:left;"></span><input readonly type="text" name="' + inputName + '_start" class="Wdate td-content-input' + otherClasses + '" style=\'width:80px\' onClick="WdatePicker(this,{readOnly:true,dateFmt:\'' + dateFormat + '\'})" ' + otherAttributes + ' />';
                        valueContent += '<span style="float:left;">&nbsp;-&nbsp;</span><input readonly type="text" name="' + inputName + '_end" class="Wdate td-content-input' + otherClasses + '" style=\'width:80px\' onClick="WdatePicker(this,{readOnly:true,dateFmt:\'' + dateFormat + '\'})" ' + otherAttributes + ' />';
                    }
                    break;
                }
            case "combobox":
                {
                    var comboboxDivContianerId = this.id + '_queryArea_id_combo_' + inputName + '_container';
                    columnQueryConfig['renderTo'] = comboboxDivContianerId;
                    columnQueryConfig['name'] = inputName;
                    columnQueryConfig['width'] = 100;
                    //模拟下拉框
                    valueContent += '<div style=\'float:left;border-bottom:1px solid silver;border-top:1px solid silver;width:80px;height:19px\' id="' + comboboxDivContianerId + '"></div>';
                    this.queryAreacomboboxConfigArray.push(columnQueryConfig);
                }
            }
            queryAreaTableTbodyTdValue.innerHTML = valueContent;
        }
    }
    this.queryAreaComboboxs = [];
    for (var i = 0; i < this.queryAreacomboboxConfigArray.length; i++) {
        var combo_select = new tracywindyComboBox(this.queryAreacomboboxConfigArray[i]);
        this.queryAreaComboboxs.push(combo_select);
    }
    if (this.queryAreaDiv) {
        //加载comobo
        this.queryAreaDivHeight = this.queryAreaDiv.offsetHeight;
        this.queryAreaDiv.style.display = "none";
        if (!this.titleDiv) {
            alert("请先指定" + this.id + '的Title标题');
            return;
        }
        if(this.showQueryAreaOnDocumentReady == true){
        	$(this.queryAreaDiv).show();
        }
        this.titleDiv.innerHTML += "<div style='float:right;padding-top:7px;margin-right:10px;'><input type='button' class='btn btn-primary' id='" + this.id + "_expandOrCollapseButton' value='" + (this.showQueryAreaOnDocumentReady?"折叠":"显示") + "' onclick='javascript:expandOrCollapseQueryArea(\"" + this.id + "\")'/><div>";
    }
    if (isInitQueryArea) {
        if (tableConfig.isNewLineQueryButton) {
            queryAreaTableTbodyTr = document.createElement("tr");
            queryAreaTableTbody.appendChild(queryAreaTableTbodyTr);
        }
        var queryButtonTd = document.createElement("td");
        queryButtonTd.colSpan = tableConfig.queryButtonColSpan || 2;
        queryAreaTableTbodyTr.appendChild(queryButtonTd);
        var tempHtml = "";
        tempHtml += '<a href="javascript:void(0);" style="margin-top:0px;margin:0 5px;" class="btn btn-primary"  onclick=\'doQueryAreaAutoQuery(getTracywindyObject("' + this.id + '"),false,true)\'>查询</a>';
        tempHtml += '<a href="javascript:void(0);" style="margin-top:0px;margin:0 5px;" class="btn btn-primary"  onclick=\'doQueryAreaAutoQuery(getTracywindyObject("' + this.id + '"),true,true)\'>清空<a>';
        queryButtonTd.innerHTML = tempHtml;
        var queryAreaTableRows = this.queryAreaTable.rows;
        for (var i = 0; i < queryAreaTableRows.length; i++) {
            var bgClass = "x-panel-table-data-row-";
            if ((i + 1) % 2 == 0) {
                bgClass += "even";
            } else {
                bgClass += "odd";
            }
            queryAreaTableRows[i].className = bgClass;
        }
    }
    
};
/*****创建查询区域（结束）*************************************/
/*****数据加载方法（开始）*************************************/
tracywindyTable.prototype.setTools = function(newTools) {
    this.tools = newTools || [];
    this.createTableDetail();
};
//设置锁定lie
tracywindyTable.prototype.setLockedNames = function(lockedNames) {
    this.lockedNames = lockedNames;
    this.isNeedLocked = (this.lockedNames.length > 0) ;//|| this.isRank || this.isCheck;
    this.height = this.tableConfig.height;
    if (this.tableConfig.height && (this.isNeedLocked) && !this.isFit) {
        this.height = (this.tableConfig.height - 16);
    }
    //this.mainDiv.style.height = this.height + "px";
    try {
        this.mainDiv.removeChild(this.lockedTableHeaderDivContainer);
    } catch(e) {};
    try {
        this.mainDiv.removeChild(this.lockedTableDivContainer);
    } catch(e) {};
    this.mainDiv.removeChild(this.tableHeaderDivContainer);
    this.mainDiv.removeChild(this.tableDivContainer);
    this.createLockedTableContainer();
    this.createNonLockedTableContainer();
    this.loadMask.show();
    this.reload();
};
tracywindyTable.prototype.createTableDetail = function() {
    this.isNeedLocked = (this.lockedNames.length > 0) ;//|| this.isRank || this.isCheck;
    if (this.tableConfig.height && (this.isNeedLocked) && !this.isFit) {
        this.height = (this.tableConfig.height - 16);
    }
    //创建div
    this.createTableObj();
    if (this.loadMode == 'ajax') {
        //加载ajax table数据
        this.loadAjaxTableData();
    } else {
        if (this.isPage) {
            if (!this.total) {
                alert("本地页面分页需要指定total");
            }
        }
        //加载本地数据
        this.loadLocalTableData();
    }
};
//判断是否有数据
tracywindyTable.prototype.isExistData = function() {
    return ! isNoTableData;
};
//加载本地数据
tracywindyTable.prototype.loadLocalTableData = function() {
    this.loadMask.show();
    //创建分页
    if (this.isPage) {
        createPage(this);
    }
    createDataTable(this);
};
//重新设置table大小
tracywindyTable.prototype.setWidth = function(newWidth) {
    this.renderObj.removeChild(this.dom);
    this.span = null;
    this.width = newWidth - 2;
    //创建div
    this.createTableDetail();
};
tracywindyTable.prototype.setHeight = function(newHeight) {
    this.renderObj.removeChild(this.dom);
    this.span = null;
    this.height = newHeight;
    //创建div
    this.createTableDetail();
};
//加载ajax数据
tracywindyTable.prototype.loadAjaxTableData = function() {
    var $me = this;
    if ($me.lazyLoad) {
        $me.lazyLoad = false;
        $me.jsonData = {
            total: 0
        };
        //创建分页
        if ($me.isPage) {
            createPage($me);
        }
        createDataTable($me);
        this.loadMask.hide();
        return false;
    }
    if (this.needLoadMask) {
        this.loadMask.show();
    }
    var params = this.params;
    var xmlFileName = this.xmlFileName;
    params['xmlFileName'] = xmlFileName;
    params['start'] = params['start'] || $me.start || 0;
    params['pageSize'] = this.pageSize || 20;

    if (this.isReport && jQuery.ajax) {

        jQuery.ajax({
            url: getTracywindyRandomUrl(this.url),
            data: params,
            type: 'POST',
            dataType: 'JSON',
            success: function(data) {

                if (data.code == "") {
                    $me.jsonData = data;
                    $me.parseTableData();
                } else {
                    alert(data.code);

                }
            },
            error: function(res) {
                alert("Server Unavailable");

            }
        });
    } else {
        ajaxRequest({
            url: this.url,
            success: function(res) {
        	    try{
                $me.jsonData = eval('(' + res.responseText + ')');
        	    }catch(e){
        	    	document.body.innerText= e+">>:"+res.responseText ;
        	    }
                $me.parseTableData();
            },
            failure: function(res) {},
            params: params
        });
    }
};

tracywindyTable.prototype.parseTableData = function() {
    var $me = this;
    $me.tableData = $me.jsonData[$me.basicInfo.datas];

    //替换换行符   
    for (var k = 0; k < $me.tableData.length; k++) {
        for (var key in $me.tableData[k]) {
            if (typeof $me.tableData[k][key] == 'string') {
                //替换换行符
                $me.tableData[k][key] = $me.tableData[k][key].replace(/\\\\n/gi, '\n');
            }
        }
    }
    //创建数据区
    if ($me.forceUseColumn) {
        var tempTableDataArr = [];
        for (var i = 0; i < $me.tableData.length; i++) {
            var tempRowData = {};
            for (var ii = 0; ii < $me.columns.length; ii++) {
                if ($me.columns[ii]['col_mapping']) {
                    tempRowData[$me.columns[ii].name] = $me.tableData[i][$me.columns[ii]['col_mapping'].toLowerCase()];
                } else {
                    tempRowData[$me.columns[ii].name] = '';
                }
            }
            tempTableDataArr.push(tempRowData);
        }
        $me.tableData = tempTableDataArr;
    }
    //创建分页
    if ($me.isPage) {
        createPage($me);
    }
    createDataTable($me);
};
//创建数据区
tracywindyTable.prototype.AddRow = function(rowData) {
    this.tableData.push(rowData);
    createDataTable(this);
    return rowData;
};
tracywindyTable.prototype.AddRows = function(rowDatas) {
    if (rowDatas instanceof Array) {
        for (var index = 0; index < rowDatas.length; index++) {
            this.tableData.push(rowDatas[index]);
        }
    } else {
        this.tableData.push(rowDatas);
    }
    createDataTable(this);
    return rowDatas;
};
tracywindyTable.prototype.addRow = function(rowData) {
    this.tableData.push(rowData);
    createDataTable(this);
    return rowData;
};
tracywindyTable.prototype.addRows = function(rowDatas) {
    if (rowDatas instanceof Array) {
        for (var index = 0; index < rowDatas.length; index++) {
            this.tableData.push(rowDatas[index]);
        }
    } else {
        this.tableData.push(rowDatas);
    }
    createDataTable(this);
    return rowDatas;
};
tracywindyTable.prototype.setTitle = function(html) {
    document.getElementById(this.id + "_titleSpan").innerHTML = html;
};
//返回所有行json data 形如[{'value':'1'},{'value','2'}]
tracywindyTable.prototype.getRowsData = function() {
    return this.tableData;
};
tracywindyTable.prototype.getRowsJsonData = function() {
    var rowDatas = this.getRowsData();
    return rowDatas;
};
tracywindyTable.prototype.getRowDataAt = function(rowIndex) {
    return this.getRowsData()[rowIndex];
};
tracywindyTable.prototype.removeRowAt = function(index) {
    var rowData = this.tableData.splice(index, 1);
    createDataTable(this);
    return rowData;
};
tracywindyTable.prototype.addRowAt = function(index, rowData) {
    this.tableData.splice(index, 0, rowData);
    createDataTable(this);
    return rowData;
};
tracywindyTable.prototype.addRowsAt = function(index, rowDatas) {
    if (rowDatas instanceof Array) {
        for (var rindex = 0; rindex < rowDatas.length; rindex++) {
            this.tableData.splice(index++, 0, rowDatas[rindex]);
        }
    } else {
        this.tableData.splice(index, 0, rowDatas);
    }
    createDataTable(this);
    return rowDatas;
};
tracywindyTable.prototype.setColumnConfig = function(config, columnIndexOrName) {
    var currentCofig = {};
    if (typeof(columnIndexOrName) == 'string') {
        for (var index = 0; index < this.columns.length; index++) {
            if (this.columns[index].name == columnIndexOrName) {
                currentCofig = this.columns[index];
            }
        }
    } else {
        currentCofig = this.columns[columnIndexOrName];
    }
    if (currentCofig == null) {
        return;
    }
    for (var p in config) {
        currentCofig[p] = config[p];
    }
};
tracywindyTable.prototype.findColumnIndex = function(columnName) {
    var columnIndex = -1;
    for (var index = 0; index < this.columns.length; index++) {
        if (this.columns[index].name == columnName) {
            columnIndex = index;
        }
    }
    return columnIndex;
};
tracywindyTable.prototype.setColumnHeader = function(newHeader, columnIndexOrName) {
    this.setColumnConfig({
        header: newHeader
    },
    columnIndexOrName);
};
tracywindyTable.prototype.setColumnWidth = function(columnWidth, columnIndexOrName) {
    this.setColumnConfig({
        width: columnWidth
    },
    columnIndexOrName);
};
tracywindyTable.prototype.setColumnHidden = function(newHidden, columnIndexOrName) {
    this.setColumnConfig({
        hidden: newHidden
    },
    columnIndexOrName);
};
tracywindyTable.prototype.setColumnRenderer = function(newRenderer, columnIndexOrName) {
    this.setColumnConfig({
        renderer: newRenderer
    },
    columnIndexOrName);
};
tracywindyTable.prototype.setColumnAlign = function(newAlign, columnIndexOrName) {
    this.setColumnConfig({
        align: newAlign
    },
    columnIndexOrName);
};
tracywindyTable.prototype.setColumnHeaderAlign = function(newHeaderAlign, columnIndexOrName) {
    this.setColumnConfig({
        headerAlign: newHeaderAlign
    },
    columnIndexOrName);
};
tracywindyTable.prototype.getCheckedRowDatas = function() {
    var rowDatas = new Array();
    var allItems = document.getElementsByName(this.checkName);
    for (var i = 0; i < allItems.length; i++) {
        if (allItems[i].checked) {
            var rowData = allItems[i].rowData;
            rowData.rowIndex = i;
            rowDatas.push(rowData);
        }
    }
    return rowDatas;
};
tracywindyTable.prototype.getSingleCheckedReferenceRowDataToModify = function() {
    var rowIndex = -1;
    var allItems = document.getElementsByName(this.checkName);
    for (var i = 0; i < allItems.length; i++) {
        if (allItems[i].checked) {
            rowIndex = i;
        }
    }
    if ( - 1 != rowIndex) {
        return this.tableData[rowIndex];
    } else {
        return null;
    }
};
tracywindyTable.prototype.deleteCheckedRows = function() {
    var rowsData = [];
    var allItems = document.getElementsByName(this.checkName);
    for (var i = 0; i < allItems.length; i++) {
        if (allItems[i].checked) {
            var rowData = this.tableData.splice(index, 1);
            rowsData.push(rowData);
        }
    }
    createDataTable(this);
    return rowsData;
};
tracywindyTable.prototype.setParam = function(name, value) {
    this.params[name] = value;
};
tracywindyTable.prototype.setParams = function(params) {
    for (var param in params) {
        this.params[param] = params[param];
    }
};
tracywindyTable.prototype.reconfigure = function(columns, datas) {
    this.columns = columns;
    if (datas) {
        this.datas = datas;
    }
    if (this.loadMode == 'ajax') {
        //加载ajax table数据
        this.loadAjaxTableData();
    } else {
        //加载本地数据
        this.jsonData = {};
        this.tableData = this.datas;
        this.loadLocalTableData();
    }
};
tracywindyTable.prototype.reload = function() {
    /*if(this.loadMode == 'ajax')
	{
		//加载ajax table数据
		this.loadAjaxTableData();
	}
	else 
	{
		//加载本地数据
		this.loadLocalTableData();
	}*/
    this.load();
};
tracywindyTable.prototype.load=function(loadSummary){
	//this.params['start'] = '0';  2014-07-16 徐工电话催收、财务到帐核销需要在功能结束后仍保留之前页数 by ToyBaby
	this.params['pageSize'] = this.pageSize||20;
	//modify by tracywindy 2013-10-23
	//this.createTableDetail();
	/**/
	if(this.loadMode == 'ajax')
	{
		//加载ajax table数据
		this.loadAjaxTableData();
	}
	else 
	{
		//加载本地数据
		this.loadLocalTableData();
	}
};
/*****数据加载方法（结束）*************************************/
//-------------------------------------------------------------
/*****创建容器对象（开始）*************************************/
tracywindyTable.prototype.createTableObj = function() {
    var currentObj = this.renderTo;
    //创建主div
    if (! (typeof(this.renderTo) == 'object')) {
        currentObj = document.getElementById(this.renderTo);
    }
    if (this.displayToggleContainer) {
        this.displayToggleContainerObj = this.displayToggleContainer;
        if (! (typeof(this.displayToggleContainer) == 'object')) {
            this.displayToggleContainerObj = document.getElementById(this.displayToggleContainer);
        }
    }
    this.renderObj = currentObj;
    if (!currentObj) {
        alert("tracywindyTable[id='" + this.id + "'],没有对应的html元素<" + this.renderTo + ">");
    }
    currentObj.style.margin = "0px";
    currentObj.style.padding = "0px";
    currentObj.style.textIndent = "0px";
    if (this.dom) {
        this.renderObj.removeChild(this.dom);
    }
    var name_exportExcelFormIFrame = this.id + "_exportExcelFormIFrame";
    this.name_exportExcelFormIFrame = name_exportExcelFormIFrame;
    currentObj.innerHTML = "<iframe style='display:none;' name='" + name_exportExcelFormIFrame + "'></iframe>";
    this.mainDiv = document.createElement("div"); //document.getElementById(this.renderTo);
    this.mainDiv.style.width = (this.width+2) + "px";
    this.mainDivId = this.id + "_mainDivTableContainer";
    this.mainDiv.id = this.mainDivId;
    if (!this.isPagePrint && !this.isAutoHeight) {
        //this.mainDiv.style.height = this.height + "px";
        //modify by tracywindy 2013-08-16 17:06
        this.mainDiv.style.height = this.tableConfig.height + "px";
    }
    this.mainDiv.className = "x-panel-table-div";
    if (!currentObj) alert(this.renderTo);
    currentObj.appendChild(this.mainDiv);
    //创建loadMask
    if (!this.loadMask) {
        var $me = this;
        var oldDisplay = "";
        if ($me.displayToggleContainerObj) {
            oldDisplay = $me.displayToggleContainerObj.style.display;
            $me.displayToggleContainerObj.style.display = "block";
        }
        this.loadMask = new tracywindyLoadMask(this.mainDiv, '数据加载中，请稍等...');
        if ($me.displayToggleContainerObj) {
            $me.displayToggleContainerObj.style.display = oldDisplay;
        }
    }
    this.loadMask.show();

    this.dom = this.mainDiv;
    if (this.border) {
        addClass(this.mainDiv, "x-panel-table-div-border");
        if (!this.title) {
            this.mainDiv.style.borderTop = "1px solid #DDD";
        }
    }
    //创建主标题
    if (this.title) {
        this.titleDiv = document.createElement("div");
        this.titleDiv.className = "x-panel-table-div-title";
        var titleInnerHTML = "<span id='" + this.id + "_titleIcon' class='x-panel-table-div-title-iconSpan'>&nbsp;</span>";
        titleInnerHTML += "<span id='" + this.id + "_titleSpan' class='x-panel-table-div-title-titleSpan'>" + this.title + "</span>";
        this.mainDiv.appendChild(this.titleDiv);
        if (this.isExcel) {
            //titleInnerHTML += "<span id='" + this.id + "_operSpan' class='x-panel-table-div-title-operSpan' title='导出excel' onclick='exportExcelData(\"" + this.id + "\")'>&nbsp;导出</span>";
            titleInnerHTML += "<div style='float:right;padding:5px 10px 0 0;text-indent: 5px;line-height:20px;'><input type='button' class='btn btn-primary' id='" + this.id + "_operSpan' value='导出' onclick='exportExcelData(\"" + this.id + "\")' /> </div>";
        }
        this.titleDiv.innerHTML = titleInnerHTML;
        this.titleDiv.style.width = (this.width ) + "px";
        var isNoTitleBorder = null;
        if (window.parent) {
            isTitleBorder = !window.parent.document.getElementById("id_goPage");
        }
        if (this.border) {
            this.titleDiv.style.borderTop = "0px solid #DDD";
            this.titleDiv.style.borderLeft = "1px solid transparent";
            this.titleDiv.style.borderRight = "1px solid transparent";
            if (isTitleBorder) {
                addClass(this.mainDiv, "x-panel-table-div-border-single");
            }
        }
    }
    this.createQueryArea();
    this.toolbarDiv = document.createElement("div");
    this.toolbarDiv.className = "x-panel-table-toolbar-div-container";
    this.mainDiv.appendChild(this.toolbarDiv);
    if (!this.isPage && (0 == this.tools.length)) {
        this.toolbarDiv.style.display = "none";
    }
    //创建tools 工具条div
    if (this.tools.length > 0) {
        this.toolsDiv = document.createElement("div");
        this.toolsDiv.id = this.id + "_toolsDiv";
        this.toolsDiv.className = "x-panel-table-tools-div";
        this.toolsDiv.style.borderWidth = "0px";
        this.toolsDiv.style.borderStyle = "solid";
        this.toolsDiv.style.borderColor = "#FFFFFF";
        if (!this.isPage) {
            try {
                this.toolsDiv.style.cssFloat = "none";
            } catch(e) {}
            try {
                this.toolsDiv.style.styleFloat = "none";
            } catch(e) {}
        }
        var attachmentToolDiv = document.createElement("div");
        attachmentToolDiv.className = "x-panel-table-tools-attachment-div";
        attachmentToolDiv.style.width = this.toolsLeftMargin + "px";
        this.toolsDiv.appendChild(attachmentToolDiv);
        this.toolbarDiv.appendChild(this.toolsDiv);

        var toolsDivInnerTable = document.createElement("table");
        toolsDivInnerTable.className  = "x-panel-table-tools-inner-table";
        this.toolsDiv.appendChild(toolsDivInnerTable);
        this.toolsDivInnerTable = toolsDivInnerTable;
        try {
            toolsDivInnerTable.style.cssFloat = "left";
            toolsDivInnerTable.style.borderCollapse = "collapse";
            toolsDivInnerTable.style.height = "30px";
        } catch(e) {}
        try {
            toolsDivInnerTable.style.styleFloat = "left";
            toolsDivInnerTable.style.borderCollapse = "collapse";
            toolsDivInnerTable.style.height = "30px";
        } catch(e) {}
        var toolsDivInnerTableBody = document.createElement("tbody");
        toolsDivInnerTable.appendChild(toolsDivInnerTableBody);
        var toolsDivInnerTableBodyTR = document.createElement("tr");
        toolsDivInnerTableBody.appendChild(toolsDivInnerTableBodyTR);
        //创建tools应用
        for (var index = 0; index < this.tools.length; index++) {
            var toolsDivInnerTableBodyTR_TD = document.createElement("td");
            toolsDivInnerTableBodyTR.appendChild(toolsDivInnerTableBodyTR_TD);

            var tool = this.tools[index];
            if (typeof(tool) == 'object') {
                if (this.isCustomTools) {
                    this.toolsDiv.innerHTML = tool.html;
                } else {
                    var html = tool.html;
                    var isHtml = tool.isHtml || false;
                    if (isHtml) {
                        var toolItem = document.createElement("span");
                        toolItem.innerHTML = html;
                        toolsDivInnerTableBodyTR_TD.appendChild(toolItem);
                        continue;
                    }
                    var toolItem = document.createElement("a");

                    toolItem.setAttribute("iconCls", tool.iconCls);
                    toolItem.setAttribute("plain", 'true');
                    toolItem.setAttribute("remainToolsIndex", index);
                    var handler = tool.handler ||
                    function() {};
                    var clickHandler = (function(html, handler, table) {
                        return function(e) {
                            handler(table);
                            cancelBubble(e);
                        };
                    })(html, handler, this);
                    with(toolItem) {
                        href = "javascript:void(0)";
                        innerHTML = html;
                        onclick = clickHandler;
                    }
                    toolsDivInnerTableBodyTR_TD.appendChild(toolItem);
                    var toolItemText = jQuery(toolItem).text();
                    toolItem.setAttribute("toolItemIdentifier", this.id + "_" + toolItemText);
                    jQuery(toolItem).linkbutton();
                }
            } else if (typeof(tool) == 'string') {
                //添加分隔符
                var menuItemSeparator = document.createElement("a");
                menuItemSeparator.className = "x-panel-table-tool-div-separator-image";
                menuItemSeparator.innerHTML = "&nbsp;";
                toolsDivInnerTableBodyTR_TD.appendChild(menuItemSeparator);
            }
        }
    }
    //创建分页
    if (this.isPage) {
        this.pageDiv = document.createElement("div");
        this.pageDiv.className = "x-panel-table-div-page";
        if (this.tools.length == 0) {
            this.pageDiv.style.width = (this.width - 2) + "px";
            this.pageDiv.style.textAlign = "right";
            if (this.border) {
                this.pageDiv.style.width = (this.width - 1) + "px";
                this.pageDiv.style.borderLeft = "0px";
                try {
                    this.pageDiv.style.cssFloat = "left";
                } catch(e) {}
                try {
                    this.pageDiv.style.styleFloat = "left";
                } catch(e) {}
            } else {
                try {
                    this.pageDiv.style.cssFloat = "none";
                } catch(e) {}
                try {
                    this.pageDiv.style.styleFloat = "none";
                } catch(e) {}
            }
        }
        this.toolbarDiv.appendChild(this.pageDiv);
    }
    if (this.isPage && (this.tools.length > 0)) {
        this.toolsDiv.style.borderRight = "0px";
        this.pageDiv.style.borderLeft = "0px";
        if (this.title == "信用等级") {
            this.toolsDiv.style.display = "block";
        }
        //this.pageDiv.style.width  = (this.width-this.toolsAdd-this.toolsDiv.offsetWidth+1)+"px";
        this.pageDiv.style.marginLeft = "-2px";
        this.pageDiv.style.textAlign = "right";
    }
    if (this.isPage && this.border) {
        this.pageDiv.style.borderRight = "1px solid transparent";
    } else if ((this.tools.length > 0) && this.border) {
        this.toolsDiv.style.borderRight = "1px solid transparent";
    }

    this.subHeight = 0;
    if (this.title) {
        this.subHeight += 40;
    }
    if (this.showHeader) {
        if (this.complexHeaders.length > 0) {
            /*this.subHeight+=30*this.complexHeaders.length;*/
            var rowspan = this.complexHeaders.length;
            this.subHeight += (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2));
            this.subHeight += this.complexHeadersAddHeight;
        } else {
            this.subHeight += 30;
        }
    }
    if (this.isPage || (this.tools.length > 0)) {
        this.subHeight += 40;
    }
    this.mainDataHeight = (this.height - this.subHeight);
    if (this.isNeedLocked) {
        this.createLockedTableContainer();
    }
    this.createNonLockedTableContainer();
    
    //modify by tracywindy 2014-07-13 start ,添加后台合计功能
    if(this.isRemoteStatistic){
    	 this.summaryDiv = document.createElement("div");
         this.summaryDiv.className = "x-panel-table-div-summary";
         this.mainDiv.appendChild(this.summaryDiv);
    }
    //modify by tracywindy 2014-07-13 end 
};
tracywindyTable.prototype.createLockedTableContainer = function() {
    //table 标题(复杂表头)区域容器,当出现滚动条的时候添加空白样式
    this.lockedTableHeaderDivContainer = document.createElement("div");
    this.lockedTableHeaderDivContainer.id = this.id + "_lockedHeaderDivContainer";
    this.lockedTableHeaderDivContainer.className = 'x-panel-table-header-div-container';
    if (this.complexHeaders.length > 0) {
        var rowspan = this.complexHeaders.length;
        this.lockedTableHeaderDivContainer.style.height = (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
    }
    this.mainDiv.appendChild(this.lockedTableHeaderDivContainer);
    //table 标题(复杂表头)区域容器,当出现滚动条的时候添加空白样式
    this.tableHeaderDivContainer = document.createElement("div");
    this.tableHeaderDivContainer.id = this.id + "_headerDivContainer";
    this.tableHeaderDivContainer.className = 'x-panel-table-header-div-container';
    if (this.complexHeaders.length > 0) {
        var rowspan = this.complexHeaders.length;
        this.tableHeaderDivContainer.style.height = (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
        //this.tableHeaderDivContainer.style.height = (30+30*this.complexHeaders.length)+"px";
    }
    this.mainDiv.appendChild(this.tableHeaderDivContainer);
    var brDiv = document.createElement("div");
    brDiv.className = "br-div";
    this.mainDiv.appendChild(brDiv);
    //添加table 标题区域
    this.lockedTableHeaderDiv = document.createElement("div");
    this.lockedTableHeaderDiv.id = this.id + "_lockedHeaderDiv";
    this.lockedTableHeaderDiv.className = "x-panel-table-header-div";
    if (this.complexHeaders.length > 0) {
        var rowspan = this.complexHeaders.length;
        this.lockedTableHeaderDiv.style.height = (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
        //this.lockedTableHeaderDiv.style.height = (30+30*this.complexHeaders.length)+"px";
    }
    this.lockedTableHeaderDivContainer.appendChild(this.lockedTableHeaderDiv);
    //创建table 数据div容器
    this.lockedTableDivContainer = document.createElement("div");
    this.lockedTableDivContainer.id = this.id + "_lockedTableDivCotainer";
    this.lockedTableDivContainer.className = "x-panel-table-content-div-container";
    this.mainDiv.appendChild(this.lockedTableDivContainer);

    this.lockedTableDiv = document.createElement("div");
    this.lockedTableDiv.id = this.id + "_lockedTableDiv";
    //this.lockedTableDiv.style.borderBottom="1px dotted #DDD";
    this.lockedTableDiv.className = "x-panel-table-content-div";
    if (this.border) {
        //this.tableDiv.style.width = (this.width-2) + "px";
        //this.tableHeaderDivContainer.style.width =(this.width-2) + "px";
    }
    this.lockedTableDivContainer.appendChild(this.lockedTableDiv);
    //创建table数据区域
    this.lockedTable = document.createElement("table");
    this.lockedTable.className = "x-panel-table";
    this.lockedTableDiv.appendChild(this.lockedTable);
    //table 区域实际高度
    this.lockedTableHeaderDivContainer.style.display = "none";
    this.lockedTableDiv.style.display = "none";
    this.lockedTable.style.display = "none";
    if (!this.isPagePrint && !this.isAutoHeight) {
        this.lockedTableDiv.style.height = (this.height - this.subHeight -1) + "px";
    }
};
tracywindyTable.prototype.createNonLockedTableContainer = function() {
    //table 标题(复杂表头)区域容器,当出现滚动条的时候添加空白样式
    if (!this.isNeedLocked) {
        this.tableHeaderDivContainer = document.createElement("div");
        this.tableHeaderDivContainer.id = this.id + "_headerDivContainer";
        this.tableHeaderDivContainer.className = 'x-panel-table-header-div-container';
        if (this.complexHeaders.length > 0) {
            //this.tableHeaderDivContainer.style.height = (30+30*this.complexHeaders.length)+"px";
            var rowspan = this.complexHeaders.length;
            this.tableHeaderDivContainer.style.height = (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
        }
        this.mainDiv.appendChild(this.tableHeaderDivContainer);
    }
    //添加table 标题区域
    this.tableHeaderDiv = document.createElement("div");
    this.tableHeaderDiv.id = this.id + "_headerDiv";
    this.tableHeaderDiv.className = "x-panel-table-header-div";
    if (this.complexHeaders.length > 0) {
        var rowspan = this.complexHeaders.length;
        this.tableHeaderDiv.style.height = (34 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
        //this.tableHeaderDiv.style.height = (30+30*this.complexHeaders.length)+"px";
    }
    this.tableHeaderDivContainer.appendChild(this.tableHeaderDiv);
    //创建table 数据div容器
    this.tableDivContainer = document.createElement("div");
    this.tableDivContainer.id = this.id + "_tableDivCotainer";
    this.tableDivContainer.className = "x-panel-table-content-div-container";
    this.mainDiv.appendChild(this.tableDivContainer);
    this.tableDiv = document.createElement("div");
    this.tableDiv.id = this.id + "_tableDiv";
    this.tableDiv.className = "x-panel-table-content-div";
    if (this.border) {
        //this.tableDiv.style.width = (this.width-2) + "px";
        //this.tableHeaderDivContainer.style.width =(this.width-2) + "px";
    }
    this.tableDivContainer.appendChild(this.tableDiv);
    //创建table数据区域
    this.table = document.createElement("table");
    this.table.className = "x-panel-table";
    this.tableDiv.appendChild(this.table);
    //table 区域实际高度
    if (!this.isPagePrint && !this.isAutoHeight) {
        var curHeight = this.height - this.subHeight;
        if (this.isNeedLocked && !this.isFit) {
            curHeight += 16;
        }
        this.tableDiv.style.height = (curHeight) + "px";
    }
};
tracywindyTable.prototype.enabledToolsByText = function(toolsText, splitChar) {
    var toolsTextArr = toolsText.split(splitChar || ",");
    for (var i = 0; i < toolsTextArr.length; i++) {
        var $meItem = jQuery(this.toolsDivInnerTable).find(" tbody tr td a.l-btn[toolItemIdentifier=" + this.id + "_" + toolsTextArr[i] + "]");
        if (!$meItem[0]) return;
        var $displayItem = $meItem.find("span.l-btn-left span.l-btn-text");
        var $meItemText = $meItem.text();
        var $meItemIconCls = $meItem.attr("iconCls");
        var $meClickHandler = $meItem.attr("remainClickFunc");
        var remainToolsIndex = $meItem.attr("remainToolsIndex");
        var toolItem = this.tools[remainToolsIndex];
        $meItem.removeClass("l-btn-plain-disabled");
        $displayItem.removeClass($meItemIconCls);
        $displayItem.removeClass($meItemIconCls + "-disable");
        $displayItem.addClass($meItemIconCls);
        var handler = toolItem.handler ||
        function(table) {};
        var html = toolItem.html;
        var clickHandler = (function(html, handler, table) {
            return function(e) {
                handler(table);
                cancelBubble(e);
            };
        })(html, handler, this);
        $meItem[0].onclick = clickHandler;
        $displayItem.html(html);
        //$meItem.linkbutton('enable');
    }
};
tracywindyTable.prototype.disabledToolsByText = function(toolsText, splitChar) {
    var toolsTextArr = toolsText.split(splitChar || ",");
    for (var i = 0; i < toolsTextArr.length; i++) {
        var $meItem = jQuery(this.toolsDivInnerTable).find(" tbody tr td a.l-btn[toolItemIdentifier=" + this.id + "_" + toolsTextArr[i] + "]");
        if (!$meItem[0]) return;
        var $displayItem = $meItem.find("span.l-btn-left span.l-btn-text");
        var $meItemText = $meItem.text();
        var $meItemIconCls = $meItem.attr("iconCls");
        var $meClickHandler = $meItem.attr("remainClickFunc");
        $meItem.addClass("l-btn-plain-disabled");
        $displayItem.removeClass($meItemIconCls);
        $displayItem.removeClass($meItemIconCls + "-disable");
        $displayItem.addClass($meItemIconCls + "-disable");
        $meItem[0].onclick = null;
        $displayItem.html($meItemText);
        //$meItem.linkbutton('disable');
    }
};
tracywindyTable.prototype.getLastClickedRow = function() {
    if ((typeof(this.currentSelectedIndex) == 'undefined')) {
        alert("无选择项");
        return;
    }
    return this.table.rows[this.currentSelectedIndex].dataObj;
};
function doClickTableRow(e,$me, tr, rowIndex) {
    if ($me.onRowClicked) {
        $me.onRowClicked(e,$me, tr, rowIndex);
    }
    if ($me.isCheck && (!$me.checkOnly)) {
        var headerCheckBox = document.getElementById($me.checkName + '_header');
        var target = getTarget(e);
        var checks = document.getElementsByName($me.checkName);
        for (var cIndex = 0; cIndex < checks.length; cIndex++) {
            if (checks[cIndex].rowIndex == rowIndex) {
            	if($me.checkName != target.name){
            		checks[cIndex].checked = !checks[cIndex].checked;
            	}
	            /* if (headerCheckBox && checks[cIndex].checked) {
	                headerCheckBox.checked = true;
	            }*/
	        	if (headerCheckBox) {
	                var allCheckedCount = 0;
	                var allItems = document.getElementsByName($me.checkName);
	                for (var i = 0; i < allItems.length; i++) {
	                    if (allItems[i].checked) {
	                        allCheckedCount++;
	                        break;
	                    }
	                }
	                
	                if (allCheckedCount > 0) {
	                    headerCheckBox.checked = true;
	                }else{
	                	headerCheckBox.checked = false;
	                }
	            }
	            break;
            }
        }
    }
    $me.currentSelectedIndex = rowIndex;
    var rejectClassName = "x-panel-table-data-row-click";
    changeTrCssStyle($me.table, $me.table.rows[rowIndex], rejectClassName);
    if ($me.lockedTable) {
        changeTrCssStyle($me.lockedTable, $me.lockedTable.rows[rowIndex], rejectClassName);
    }
}
function changeTrCssStyle(table, currentRow, rejectClassName) {
    for (var rowIndex = 0; rowIndex < table.rows.length; rowIndex++) {
        var row = table.rows[rowIndex];
        removeClass(row, rejectClassName);
    }
    addClass(currentRow, rejectClassName);
}
function setDefaultTableColumnConfig(currentColumnConfig,$me) {
    var currentColumnToConfig = currentColumnConfig;
    var currentDataTypeToConfig = currentColumnToConfig['type'];
    if (("number" == currentDataTypeToConfig) || ("double" == currentDataTypeToConfig)) {
        currentColumnToConfig['align'] = 'right';
    }
    if(currentColumnToConfig['width']){
    	 if((currentColumnToConfig['width']+"").indexOf("%")>-1){
    		 currentColumnToConfig['width'] = decimal(($me.width+2)*parseFloat(currentColumnToConfig['width'])/100,2);
    	 }else{
    	    currentColumnToConfig['width'] = parseFloat(currentColumnToConfig['width']);
    	 }
    }
    return currentColumnToConfig;
}
function initDataTable($me) {
    //初始化数据开始
    var jsonData = $me.jsonData;
    var datas = $me.tempDatas = [];
    for (var i = 0; i < $me.tableData.length; i++) {
        var newRowData = {};
        for (var p in $me.tableData[i]) {
            //2013-4-18 孙传良  修改  如果是double类型的配置则格式化为2位小数
            //原代码
            //newRowData[p] = $me.tableData[i][p];
            //修改开始
            try {
                newRowData[p] = $me.tableData[i][p];
                var val = parseFloat($me.tableData[i][p]);
                var type;
                for (var iik = 0; iik < $me.columns.length; iik++) {
                    if (($me.columns[iik]['mapping'] || $me.columns[iik]['name']) == p) {

                        if ("double" == $me.columns[iik]['type']) {
                            if (String(val) == 'NaN') {
                                newRowData[p] = $me.tableData[i][p];
                            } else {
                                //newRowData[p] = (val.toFixed(2));
                                // modify by tracywindy 2013-10-14
                                newRowData[p] = forcePreciseDecimal(val, 2);
                            }
                        } else {
                            newRowData[p] = $me.tableData[i][p];
                        }
                        break;
                    }
                }
            } catch(e) {
                newRowData[p] = $me.tableData[i][p];
            }
            //修改结束
        }
        datas.push(newRowData);
    }
    if ($me.isStatistic) {
        //初始化合计
        var statisticRowData = {};
        var statisticRowDataType = {};
        for (var iik = 0; iik < $me.columns.length; iik++) {
            var currentColumnConfig = $me.columns[iik];
            var name = currentColumnConfig['mapping'] || currentColumnConfig['name'];
            statisticRowData[name] = ' - ';
            if (jQuery.inArray(name, $me.statisticColumnNames) > -1) {
                statisticRowData[name] = '0';
                statisticRowDataType[name] = currentColumnConfig['type'];
            }
        }
        datas.push(statisticRowData);
        var statisticColumnNames = $me.statisticColumnNames;
        var sumRowData = {};
        for (var i = 0; i < $me.statisticColumnNames.length; i++) {
            sumRowData[$me.statisticColumnNames[i]] = 0;
        }
        if("local" == $me.loadMode){
        	 //计算本地合计
            for (var iir = 0; iir < (datas.length - 1); iir++) {
                var rowData = datas[iir];
                for (var p in sumRowData) {
                    sumRowData[p] += parseFloat(empty2Other(rowData[p], 0));
                }
            }
        }else{ //后台远程合计
        	sumRowData = jsonData.sumRowData;
        }
        for (var p in sumRowData) {
            if ("double" == statisticRowDataType[p]) {
                statisticRowData[p] = (sumRowData[p].toFixed(2));
            } else {
                statisticRowData[p] = decimal(sumRowData[p], $me.precise);
            }
        }
    }
    /*else
	{
		datas = $me.tableData;
	}*/
    $me.tempDatas = datas;
    //header 
    var columnIndex = 0;

    if (((!$me.forceUseColumn) && ($me.loadMode == 'ajax')) && !$me.remainColumns && (datas.length > 0)) {
        $me.columns = [];
    }
    var tableItemColumnLen = $me.columns.length;

    if ((tableItemColumnLen == 0)) {
        var headers = datas[0];
        for (var p in headers) {
            if (("rowIndex" == p) || ("rn_column" == p)) {
                continue;
            }
            var currentColumnConfig = {};
            currentColumnConfig['name'] = p;
            currentColumnConfig['mapping'] = p;
            currentColumnConfig['hidden'] = false;
            currentColumnConfig['header'] = p;
            currentColumnConfig['width'] = 100;
            currentColumnConfig['align'] = "left";
            currentColumnConfig['headerAlign'] = "center";
            currentColumnConfig['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                return value;
            };
            if (!$me.columns[columnIndex]) {
                $me.columns[columnIndex] = {};
            }
            var currentColumnToConfig = setDefaultTableColumnConfig($me.columns[columnIndex],$me);
            tracywindyApplyTo(currentColumnConfig, currentColumnToConfig);
            columnIndex++;
        }
        tableItemColumnLen = columnIndex;
    } else {
        if ($me.loadMode == 'ajax') {
            if ($me.forceUseColumn) {
                for (var iiii = 0; iiii < $me.columns.length; iiii++) {
                    var currentColumnConfig = {};
                    currentColumnConfig['hidden'] = false;
                    currentColumnConfig['mapping'] = $me.columns[iiii].name;
                    currentColumnConfig['width'] = 100;
                    currentColumnConfig['align'] = "left";
                    currentColumnConfig['headerAlign'] = "center";
                    currentColumnConfig['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                        return value;
                    };
                    var currentColumnToConfig = setDefaultTableColumnConfig($me.columns[iiii],$me);
                    tracywindyApplyTo(currentColumnConfig, currentColumnToConfig);
                }
            } else {
                if ($me.remainColumns) {
                    for (var iik = 0; iik < $me.columns.length; iik++) {
                        var currentColumnConfig = {};
                        var currentConfig = $me.columns[iik];
                        currentColumnConfig['name'] = currentConfig['name'];
                        currentColumnConfig['mapping'] = currentConfig['name'];
                        currentColumnConfig['hidden'] = false;
                        currentColumnConfig['header'] = currentConfig['name'];
                        currentColumnConfig['width'] = 100;
                        currentColumnConfig['align'] = "left";
                        currentColumnConfig['headerAlign'] = "center";
                        currentColumnConfig['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                            return value;
                        };
                        var currentColumnToConfig = setDefaultTableColumnConfig($me.columns[iik],$me);
                        tracywindyApplyTo(currentColumnConfig, currentColumnToConfig);
                    }
                } else {
                    var headers = datas[0];
                    for (var p in headers) {
                        if (("rowIndex" == p) || ("rn_column" == p)) {
                            continue;
                        }
                        var currentColumnConfig = {};
                        currentColumnConfig['name'] = p;
                        currentColumnConfig['mapping'] = p;
                        currentColumnConfig['hidden'] = false;
                        currentColumnConfig['header'] = p;
                        currentColumnConfig['width'] = 100;
                        currentColumnConfig['align'] = "left";
                        currentColumnConfig['headerAlign'] = "center";
                        currentColumnConfig['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                            return value;
                        };
                        if (!$me.columns[columnIndex]) {
                            $me.columns[columnIndex] = {};
                        }
                        var currentColumnToConfig = setDefaultTableColumnConfig($me.columns[columnIndex],$me);
                        tracywindyApplyTo(currentColumnConfig, currentColumnToConfig);
                        columnIndex++;
                    }
                }
            }
        } else {
            for (var cIndex = 0; cIndex < tableItemColumnLen; cIndex++) {
                var obj = {};
                obj['name'] = $me.columns[cIndex].name;
                obj['mapping'] = $me.columns[cIndex].name;
                obj['hidden'] = false;
                obj['header'] = $me.columns[cIndex].name;
                obj['width'] = 100;
                obj['align'] = "left";
                obj['headerAlign'] = "center";
                obj['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                    return value;
                };
                var currentColumnToConfig = setDefaultTableColumnConfig($me.columns[cIndex],$me);
                tracywindyApplyTo(obj, currentColumnToConfig);
            }
        }
    }
    columnIndex = 0;
    var isInvokeCallBack = true;
    if (($me.loadMode == 'ajax') && (typeof($me.jsonData.norecord) != 'undefined') && ($me.jsonData.norecord == 'true')) {
        isInvokeCallBack = false;
    }
    if ($me.callBack) {
        if (!isInvokeCallBack) {
            for (var cIndex = 0; cIndex < tableItemColumnLen; cIndex++) {
                if (!$me.columns[cIndex]['renderer']) {
                    $me.columns[cIndex]['renderer'] = function(value, tableObj, columnName, columnIndex, rowData) {
                        return value;
                    };
                }
            }
        }
        $me.callBack(); //回调函数
    }
    var attachmentWidth = 0;
    if ($me.isRank) {
        attachmentWidth += $me.rankSize;
    }
    if ($me.isCheck) {
        attachmentWidth += ($me.checkSize+2);
    }
    $me.attachmentWidth = attachmentWidth;
    //查找特殊列
    var firstDisplayColumnIndex = 0;
    var lastDisplayColumnIndex = 0;
    var isFindFirstDisplayColumn = false;
    for (var cc_Index = 0; cc_Index < tableItemColumnLen; cc_Index++) {
        var currentFoundColumn = $me.columns[cc_Index];
        if (!currentFoundColumn.hidden) {
            if (!isFindFirstDisplayColumn) {
                firstDisplayColumnIndex = cc_Index;
                isFindFirstDisplayColumn = true;
            }
            lastDisplayColumnIndex = cc_Index;
        }
    }
    $me.firstDisplayColumnIndex = firstDisplayColumnIndex;
    $me.lastDisplayColumnIndex = lastDisplayColumnIndex;

    //是否自动填充,获取table的offsetHeight，此处为相似数值
    if ($me.isFit) {
        var realSize = 0;
        var firstNotHiddenColumnIndex = -1;
        for (var a = 0; a < tableItemColumnLen; a++) {
            if (!$me.columns[a].hidden) {++realSize;
                if ( - 1 == firstNotHiddenColumnIndex) {
                    firstNotHiddenColumnIndex = a;
                }

            }

        }
        realSize = ((realSize == 0) ? 1 : realSize);
        var realColumnSize = parseInt(($me.width - attachmentWidth) / realSize);
        var compareWidth = ($me.width - attachmentWidth) % realSize;
        for (var a = 0; a < tableItemColumnLen; a++) {
            $me.columns[a].width = realColumnSize - 3; //(realColumnSize/($me.width-attachmentWidth))*($me.width-attachmentWidth)-3;
        }
        //$me.columns[tableItemColumnLen-1].width = realColumnSize+compareWidth;
        //var remainWidth = ($me.width-attachmentWidth-30-(realColumnSize-4)*realSize );
        var remainWidth = $me.fitColumnSizeAdd; //(realSize-2)*3;
        if (SysBrowser.getBrowser().toLowerCase().indexOf("safari") > -1) {
            remainWidth += 15;
        }
        $me.columns[firstNotHiddenColumnIndex].width = ($me.columns[firstNotHiddenColumnIndex].width + remainWidth);
    }

    var lockedColumns = [];
    var nonLockedColumns = [];

    $me.lockedColumns = lockedColumns;
    $me.nonLockedColumns = nonLockedColumns;
    if (0 == $me.lockedNames.length) {
        $me.lockedColumns = $me.nonLockedColumns = $me.columns;
        return;
    }
    for (var cIndex = 0; cIndex < tableItemColumnLen; cIndex++) {
        var isLocked = false;
        var currentColumn = $me.columns[cIndex];
        for (var lockedIndex = 0; lockedIndex < $me.lockedNames.length; lockedIndex++) {
            if (currentColumn["name"] == $me.lockedNames[lockedIndex]) {
                lockedColumns.push(currentColumn);
                isLocked = true;
                break;
            }
        }
        if (!isLocked) {
            nonLockedColumns.push(currentColumn);
        }
    }
    //初始化数据完成
}
function createAllDataTable($me, complexHeaders, tableHeaderDivContainer, tableHeaderDiv, tableDiv, table, flag) {
    //清除数据开始
    tableHeaderDiv.innerHTML = '';
    var tableHeader = document.createElement("table");
    if ($me.isFit) {
        tableDiv.style.overflowX = "hidden";
    }
    table.deleteTHead();
    table.deleteTFoot();
    for (var rowIndex = 0; rowIndex < table.rows.length; rowIndex++) {
        table.deleteRow(rowIndex);
    }
    for (var iNode = 0; iNode < table.childNodes.length; iNode++) {
        table.removeChild(table.childNodes[iNode]);
    }
    ///清除数据完成
    var currentOperColumns = [];
    if ("locked" == flag) {
        currentOperColumns = $me.lockedColumns;
    } else if ("nonLocked" == flag) {
        currentOperColumns = $me.nonLockedColumns;
    }
    var tableItemColumnLen = currentOperColumns.length;
    //开始创建表格数据
    {
        //创建复杂表头col
        var complexLen = complexHeaders.length;
        var isNeedAddStyle = false;
        var isNoLockednames = true;
        if ("locked" == flag) {
            isNoLockednames = (0 == $me.lockedNames.length);
            isNeedAddStyle = (complexLen == 0) && ($me.complexHeaders.length > 0);
            if (isNeedAddStyle) {
                if (!isNoLockednames) {
                    alert("未指定锁定列的复杂表头配置项lockedComplexHeaders:['name']，会导致数据和样式错误");
                }
            }
        }
        if (complexLen > 0) {
            //创建头部
            /*for(var ccIndex = 0;ccIndex<tableItemColumnLen;ccIndex++)
		         {
		        	  if(0 == ccIndex){
		        	     //创建col 用于合并
					     if($me.isRank && ("locked"==flag)){
					    	 var col = document.createElement("col");
					    	 col.className = "x-panel-table-col-rank";
					    	 col.style.width =  ($me.rankSize+2) + "px";
				        	 tableHeader.appendChild(col);
					     }
					     if($me.isCheck && ("locked"==flag)){
					    	 var col = document.createElement("col");
				        	 col.className = "x-panel-table-col-check";
				        	 tableHeader.appendChild(col);
					     }
					     if(("locked" == flag)&&(0 == $me.lockedNames.length))break;
		        	   }
		        	   var currentFoundColumn = currentOperColumns[ccIndex];
		        	   var col = document.createElement("col");
		        	   col.style.width = (currentFoundColumn.width+3)+"px";
		        	   col.id    = "table_header_col_"+"_"+$me.id+"_"+ccIndex;
		        	   col.style.display = currentFoundColumn.hidden ? "none":"";
		        	   col.className = "x-panel-table-col";
		        	   tableHeader.appendChild(col);
		         }*/
        }
        //width变量
        var isLockedInitWidthAdd = false;
        var isNonLockedInitWidthAdd = false;
        //创建thead body
        var tbody_header = document.createElement("tbody");
        tableHeader.className = "x-panel-table-header";
        $me.checkName = $me.id + "_check";
        //创建复杂表头
        //var complexLen = complexHeaders.length;
        if (complexLen > 0) {
            for (var complexIndex = 0; complexIndex < complexLen; complexIndex++) {
                isLockedInitWidthAdd = false;
                isNonLockedInitWidthAdd = false;
                /*if(complexHeaders[complexIndex].length==0){
		        		 continue;
		        	 }*/
                var table_header_complex_row = document.createElement("tr");
                tbody_header.appendChild(table_header_complex_row);
                if (0 == complexIndex) {
                    var fillBackgroundColor = "transparent";
                    if ($me.isRank && (("locked" == flag)||!$me.isNeedLocked)) {
                        var th_rank = document.createElement("td");
                        th_rank.rowSpan = (complexLen + 1);
                        //th_rank.style.width =  ($me.rankSize) + "px" ;
                        th_rank.className = "x-panel-table-rank";
                        th_rank.innerHTML = '<div style="width:' + ($me.rankSize+5) + 'px">&nbsp;</div>';
                        table_header_complex_row.appendChild(th_rank);
                    }
                    if ($me.isCheck && (("locked" == flag)||!$me.isNeedLocked)) {
                        var th_check = document.createElement("td");
                        th_check.rowSpan = (complexLen + 1);
                        th_check.className = "x-panel-table-header-check";
                        th_check.style.textIndent = "0px";
                        var input_check = document.createElement('input');
                        input_check.type = $me.checkType;
                        input_check.id = $me.checkName + "_header";
                        input_check.onclick = function(e) {
                            var allItems = document.getElementsByName($me.checkName);
                            for (var i = 0; i < allItems.length; i++) {
                                allItems[i].checked = this.checked;
                            }
                            cancelBubble(e);
                        };
                        //th_check.appendChild(input_check);
                        if ($me.checkType == "radio") {
                            th_check.innerHTML = '<div style="width:'+($me.checkSize-1)+'px;text-indent:0px;">&nbsp;</div>';
                        }else{
                        	var checkDivContainer = document.createElement("div");
                        	checkDivContainer.style.width = ($me.checkSize-1)+"px";
                        	checkDivContainer.appendChild(input_check);
                        	th_check.appendChild(checkDivContainer);
                        }
                        table_header_complex_row.appendChild(th_check);
                    }
                    if (("locked" == flag) && (0 == $me.lockedNames.length)) break;
                }
                var complexHeaderRowData = complexHeaders[complexIndex];
                var currentColumnRealIndex = 0;
                for (var complexColIndex = 0; complexColIndex < complexHeaderRowData.length; complexColIndex++) {
                    var columnHeaderAlign = "center";
                    var rowspan = 1;
                    var colspan = 1;

                    var complexHeaderRowDataColumn = complexHeaderRowData[complexColIndex];
                    var columnHeader = nullToString(complexHeaderRowDataColumn.header);
                    /*if(complexHeaderRowDataColumn.isFillEmpty){
		        		    	 columnHeader = "&nbsp;";
					     }*/
                    var tempColumnHeaderAlign = nullToString(complexHeaderRowDataColumn.headerAlign);
                    var rowspanStr = nullToString(complexHeaderRowDataColumn.rowspan);
                    var colspanStr = nullToString(complexHeaderRowDataColumn.colspan);

                    var beforeCurrentColumnRealIndex = currentColumnRealIndex;
                    if ("" != tempColumnHeaderAlign) {
                        columnHeaderAlign = tempColumnHeaderAlign;
                    }
                    if ("" != rowspanStr) {
                        rowspan = parseInt(rowspanStr);
                    }
                    if ("" != colspanStr) {
                        colspan = parseInt(colspanStr);
                    }
                    currentColumnRealIndex += colspan;
                    var realColumnThWidth = 0;
                    for (var colConfigIndex = beforeCurrentColumnRealIndex; colConfigIndex < currentColumnRealIndex; colConfigIndex++) {
                        var currentFoundColumn = currentOperColumns[colConfigIndex];
                        if (currentFoundColumn.hidden) {
                            colspan--;
                            continue;
                        }
                        realColumnThWidth += currentFoundColumn.width;
                        if (beforeCurrentColumnRealIndex != colConfigIndex) {
                            realColumnThWidth += 3;
                        }
                    }
                    var th = document.createElement("td");
                    th.style.display = "";
                    th.rowSpan = rowspan;
                    th.colSpan = colspan;
                    th.style.borderLeft ="0px";
                    //th.style.width = realColumnThWidth +"px";
                    th.style.textAlign = columnHeaderAlign;
                    var widthAdd = 0;
                    if (!currentFoundColumn.hidden) {
                        if ("locked" == flag) {
                            if (!isLockedInitWidthAdd) {
                                widthAdd = 0;
                                isLockedInitWidthAdd = true;
                            }
                        } else if ("nonLocked" == flag) {
                            if (!isNonLockedInitWidthAdd) {
                                widthAdd = 0;
                                isNonLockedInitWidthAdd = true;
                            }
                        }
                    }
                    th.innerHTML = '<div style="text-align:' + columnHeaderAlign + '">' + columnHeader + '</div>';
                    if (complexColIndex == (complexHeaderRowData.length - 1)) {
                        if ("locked" == flag) {
                            th.style.borderRightColor = "transparent";
                        }
                    }
                    table_header_complex_row.appendChild(th);
                    /*if(complexHeaderRowDataColumn.isFillEmpty){
				        	   th.style.borderColor="transparent";
				           }*/
                    if (rowspan > 1) {
                        th.style.height = (rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px";
                    }
                }
            }
        }
        //创建表格头部
        isLockedInitWidthAdd = false;
        isNonLockedInitWidthAdd = false;
        var table_header_row = document.createElement("tr");
        for (var ccIndex = 0; ccIndex < tableItemColumnLen; ccIndex++) {
            if ((0 == ccIndex) && (complexHeaders.length <= 0)) {
                if ($me.isRank && (("locked" == flag)||!$me.isNeedLocked)) {
                    var th_rank = document.createElement("td");
                    th_rank.className = "x-panel-table-rank";
                    //th_rank.style.width =  ($me.rankSize) + "px";
                    th_rank.innerHTML = '<div style="width:' + ($me.rankSize+5) + 'px">&nbsp;</div>';
                    table_header_row.appendChild(th_rank);
                    if (isNeedAddStyle && isNoLockednames) {
                        rowspan = $me.complexHeaders.length;
                        th_rank.style.lineHeight = (29 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px"; //(30+28*$me.complexHeaders.length)+"px";
                    }
                    if (!$me.isCheck && isNoLockednames) {
                        th_rank.style.borderRightColor = "transparent";
                    }
                }
                if ($me.isCheck && (("locked" == flag)||!$me.isNeedLocked)) {
                    var th_check = document.createElement("td");
                    th_check.className = "x-panel-table-check";
                    var input_check = document.createElement('input');
                    input_check.type = $me.checkType;
                    input_check.id = $me.checkName + "_header";
                    input_check.onclick = function(e) {
                        var allItems = document.getElementsByName($me.checkName);
                        for (var i = 0; i < allItems.length; i++) {
                            allItems[i].checked = this.checked;
                        }
                        cancelBubble(e);
                    };
                    if (isNeedAddStyle && isNoLockednames) {
                        rowspan = $me.complexHeaders.length;
                        th_check.style.lineHeight = (29 + rowspan * 26 + (rowspan * 2 - 1) + (rowspan - 2)) + "px"; //(30+28*$me.complexHeaders.length)+"px";
                        //th_check.style.lineHeight = (30+26*$me.complexHeaders.length)+"px";
                    }
                    if (isNoLockednames && $me.isNeedLocked) {
                        th_check.style.borderRightColor = "transparent";
                    }
                    if ($me.checkType == "radio") {
                        th_check.innerHTML ='<div style="width:'+($me.checkSize-1)+'px;">&nbsp;</div>';
                    }else{
                    	var checkDivContainer = document.createElement("div");
                    	checkDivContainer.style.width = ($me.checkSize-1)+"px";
                    	checkDivContainer.appendChild(input_check);
                    	th_check.appendChild(checkDivContainer);
                    }
                    table_header_row.appendChild(th_check);
                }
                if (("locked" == flag) && (0 == $me.lockedNames.length)) break;
            }
            var currentFoundColumn = currentOperColumns[ccIndex];
            var th = document.createElement("td");
            th.style.borderRight = '#b6cad2 1px dotted';
            
            th.style.display = currentFoundColumn.hidden ? "none": "";
            th.setAttribute("columnName",currentFoundColumn.name);
            //th.style.width = currentFoundColumn.width +"px";
            
            th.style.textAlign = currentFoundColumn.headerAlign;
            th.columnConfig = currentFoundColumn;
            var widthAdd = 0;
            if (!currentFoundColumn.hidden) {
                if ("locked" == flag) {
                    if (!isLockedInitWidthAdd) {
                        widthAdd = 0;
                        isLockedInitWidthAdd = true;
                    }
                } else if ("nonLocked" == flag) {
                    if (!isNonLockedInitWidthAdd) {
                        widthAdd = 0;
                        isNonLockedInitWidthAdd = true;
                    }
                }
            }
            var sortDivHtml = "";
            if ($me.SortField && ($me.SortField == currentFoundColumn.mapping)) {
                th.SortDir = $me.SortDir;
                sortDivHtml = "<img class='table-sort-img' src='" + getRootPath() + "/images/sort_" + $me.SortDir + ".gif'/>";
            }
            th.innerHTML = "<div style='width:" + (currentFoundColumn.width + widthAdd) + "px;text-align:" + currentFoundColumn.headerAlign + "'>" + currentFoundColumn.header + sortDivHtml + "</div>";
            table_header_row.appendChild(th);
            if (ccIndex == $me.lastDisplayColumnIndex) {
                //th.style.borderRight = "transparent";
            }
            if (ccIndex == (tableItemColumnLen - 1)) {
                if ("locked" == flag) {
                    th.style.borderRightColor = "transparent";
                }
            }
        }
        tbody_header.appendChild(table_header_row);
        tableHeader.appendChild(tbody_header);
        tableHeaderDiv.appendChild(tableHeader);
    }
    var hScroll = false;
    var vScroll = false;
    var totalTableWidth = 0;
    if (($me.lockedNames.length > 0) || ("nonLocked" == flag)) {
        for (var a = 0; a < tableItemColumnLen; a++) {
            var currentFoundColumn = currentOperColumns[a];
            if (!currentFoundColumn.hidden) {
                totalTableWidth += parseInt(currentFoundColumn.width)+3;
            }
        }
    }
    if ("locked" == flag) {
        if ($me.isNeedLocked) {
            tableDiv.style.borderRight = "1px solid #DDD";
            // tableDiv.style.background="1px solid #DDD";
            tableHeaderDiv.style.borderRight = "1px solid #DDD";
        }
        if (0 == totalTableWidth) {
            curWidth += 8;
            $me.attachmentWidth += 8;
        }
        var curWidth = (totalTableWidth + $me.attachmentWidth);
        tableHeaderDiv.style.width = curWidth + "px";
        table.style.width = (curWidth - 1) + "px";
        tableDiv.style.width = (curWidth) + "px";
        tableDiv.style.background = "#F0F3F7";
        tableDiv.style.overflow = "hidden";
        $me.lockedTotalTableWidth = curWidth;
    } else if ("nonLocked" == flag) {
        if (!$me.lockedTotalTableWidth) {
            $me.lockedTotalTableWidth = 0;
        }
        var relativeWidth = Math.min(totalTableWidth, $me.width - $me.lockedTotalTableWidth - 2);
        relativeWidth = Math.min(relativeWidth + 2, $me.width  - $me.lockedTotalTableWidth - 2);
        if ($me.isFit
        /*&& (!$me.isMobile)*/
        ) {
            relativeWidth = ($me.width - $me.lockedTotalTableWidth - 2);
        }
        table.style.width = totalTableWidth + "px";
        tableDiv.style.width =( relativeWidth+3 )+ "px";
        tableHeader.style.width = totalTableWidth + "px";
        tableHeaderDiv.style.width = ( relativeWidth + 2) + "px";
        tableHeaderDiv.style.overflow = "hidden";
        tableDiv.style.overflow = "auto";
        hScroll = true;
        if ($me.isFit) {
            tableDiv.style.overflowX = "hidden";
            hScroll = false;
        }
        $me.nonlockedTotalTableWidth = relativeWidth;
    }

    $me.isInitHeight = false;
    //滚动条控制
    if ("nonLocked" == flag) {
        tableHeaderDiv.scrollLeft = tableDiv.scrollLeft = 0;
        try {
            $me.lockedTableDiv.scrollTop = tableDiv.scrollTop = 0;
        } catch(e) {}
        tableDiv.onscroll = function() {
            tableHeaderDiv.scrollLeft = tableDiv.scrollLeft;
            try {
                $me.lockedTableDiv.scrollTop = tableDiv.scrollTop;
            } catch(e) {}
        };
    }
    if ($me.showHeader) {
        tableHeaderDivContainer.style.display = "";
    } else {
        tableHeaderDivContainer.style.display = "none";
    }
    //tbody
    var table_body = document.createElement("tbody");
    table.appendChild(table_body);
    for (var iNode = 0; iNode < table.childNodes.length; iNode++) {
        if (table.childNodes[iNode].tagName == 'TBODY') {
            table_body = table.childNodes[iNode];
        }
    }
    $me.isNoTableData = false;
    if (!$me.isPagePrint && !$me.isAutoHeight && ("nonLocked" == flag)) {
        tableDiv.style.overflowY = "auto";
    } else {
        tableDiv.style.overflowY = "hidden";
    }
    /*
    //记载数据成功后回调函数
    if ($me.completeCallBack && ("nonLocked" == flag)) {
        $me.completeCallBack();
    }*/
    if (($me.tableData.length == 0) || (($me.loadMode == 'ajax') && (typeof($me.jsonData.norecord) != 'undefined') && ($me.jsonData.norecord == 'true'))) {
        if (("nonLocked" == flag) && $me.span) {
            try {
                tableHeaderDivContainer.removeChild($me.span);
            } catch(e) {}
            $me.span = null;
        }

        var table_body_row = document.createElement("tr");
        var td = document.createElement("td");
        td.style.borderRight = "0px";
        table_body_row.style.border = "0px";
        table_body_row.style.textAlign = 'center';
        td.colSpan = tableItemColumnLen;
        td.style.border = "0px";
        if ($me.isPagePrint || $me.isAutoHeight) {
            td.style.height = "35px";
        } else {
            td.style.height = ($me.mainDataHeight - 4) + "px";
        }
        var oldDisplay = "";
        if ($me.displayToggleContainerObj) {
            oldDisplay = $me.displayToggleContainerObj.style.display;
            $me.displayToggleContainerObj.style.display = "block";
        }
        td.style.width = (tableHeader.offsetWidth) + "px";
        if ($me.displayToggleContainerObj) {
            $me.displayToggleContainerObj.style.display = oldDisplay;
        }
        tableDiv.style.overflowY = "hidden";
        if ("locked" == flag) {
            tableDiv.style.borderRight = "0px solid #DDD";
            tableHeaderDiv.style.borderRight = "0px solid #DDD";
            td.innerHTML = "<div></div>";
        } else {
            td.innerHTML = "<center>无数据</center>";
        }
        tableDiv.style.background = "#FFFFFF";
        td.className = "no-data-td";
        td.style.fontSize = "30px";
        table_body_row.appendChild(td);
        table_body.appendChild(table_body_row);
        table.appendChild(table_body);
        $me.isNoTableData = true;
        $me.loadMask.hide();
        $me.jsonData.norecord = 'false';
        $me.tableData = this.datas = [];
        /*if ($me.completeCallBack) {
            $me.completeCallBack();
        }*/
        if ($me.isPagePrint) {
            jQuery("table.x-panel-table-header tbody tr td>div").css("color", "#000000");
            jQuery("table.x-panel-table-header").css("borderColor", "transparent");
            jQuery("table.x-panel-table-header tbody tr>td").css("background", "#DDD");
            jQuery("table.x-panel-table-header tbody tr>td").css("borderColor", "silver");
        }
        $me.synchronizedHeight();
        return true;
    }
    /*else
	 {
		 if($me.completeCallBack)
		 {
			$me.completeCallBack();
		 }
	 }*/
    var tempDatasLen = $me.tempDatas.length;
    for (var index = 0; index < tempDatasLen; index++) {
        var data = $me.tempDatas[index];
        var realTableRowData = null;
        if (index < $me.tableData.length) {
            realTableRowData = $me.tableData[index];
        } else {
            realTableRowData = data;
        }
        realTableRowData.rowIndex = index;
        var table_body_row = document.createElement("tr");
        table_body.appendChild(table_body_row);
        table_body_row.dataObj = data;
        table_body_row.dataObj['rowIndex'] = index;

        var bgClass = "x-panel-table-data-row-";
        if ((index + 1) % 2 == 0) {
            bgClass += "even";
        } else {
            bgClass += "odd";
        }
        table_body_row.className = bgClass;
        var rowColor = $me.rowColorCallback(index, data);
        if (rowColor) {
            table_body_row.style.backgroundColor = rowColor;
        }
        //注册行事件
        var clickHandler = (function($me, tr, rowIndex) {
            return function(e) {
                doClickTableRow(e,$me, tr, rowIndex);
            };
        })($me, table_body_row, index);
        var mouseOverFunc = (function(row, rowIndex) {
            return function() {
                var attachClass = "x-panel-table-data-row-mouseover";
                addClass($me.table.rows[rowIndex], attachClass);
                if ($me.lockedTable) {
                    addClass($me.lockedTable.rows[rowIndex], attachClass);
                }
            };
        })(table_body_row, index);
        var mouseOutFunc = (function(row, rowIndex) {
            return function() {
                var attachClass = "x-panel-table-data-row-mouseover";
                removeClass($me.table.rows[rowIndex], attachClass);
                if ($me.lockedTable) {
                    removeClass($me.lockedTable.rows[rowIndex], attachClass);
                }
            };
        })(table_body_row, index);
        table_body_row.onclick = clickHandler;
        var dblclickHandler  = (function(table,rowData){
        	return function(e){
        		cancelBubble(e);
            	popupReadOnlyWindow($me,rowData);
        	};
        })($me,data);
        table_body_row.ondblclick = dblclickHandler;
        table_body_row.onmouseover = mouseOverFunc;
        table_body_row.onmouseout = mouseOutFunc;

        columnIndex = 0;

        for (var ccIndex = 0; ccIndex < tableItemColumnLen; ccIndex++) {
            if (0 == ccIndex) {
                if ($me.isRank && (("locked" == flag)||!$me.isNeedLocked)) {
                    var td_rank = document.createElement("td");
                    //	td_rank.style.width =  ($me.rankSize) + "px";
                    td_rank.className = "x-panel-table-rank";
                    td_rank.innerHTML = "<div style='width:" + ($me.rankSize+5) + "px'>" + (index + 1) + '</div>';
                    if (index == 0) {
                        //td_rank.style.borderTop="1px dotted #CAD9EA";
                        td_rank.style.borderTop = "1px dotted transparent";
                    }
                    table_body_row.appendChild(td_rank);
                    if ($me.isStatistic && ($me.tempDatas.length == (index + 1))) {
                        td_rank.innerHTML = "<div style='width:40px;'>合计</div>";
                        if ($me.isCheck) td_rank.colSpan = 2;
                    }
                }
                if ($me.isCheck && (("locked" == flag)||!$me.isNeedLocked)) {
                    if ($me.isStatistic && ($me.tempDatas.length == (index + 1))) {
                    	if(!$me.isRank){
                    		var td_check = document.createElement("td");
                        	td_check.className = "x-panel-table-check";
                        	td_check.innerHTML = "-";
                        	table_body_row.appendChild(td_check);
                    	}
                    	
                    } else {
                        var td_check = document.createElement("td");
                        table_body_row.appendChild(td_check);
                        td_check.style.textIndent = "0px";
                        td_check.className = "x-panel-table-check";
                        var tempId = GenerateGuid();
                        td_check.innerHTML = ('<div style="width:'+($me.checkSize-1)+'px;"><input  id="' + tempId + '" type="' + $me.checkType + '" name="' + $me.checkName + '"/></div>');
                        var input_check = document.getElementById(tempId);
                        //input_check.type = $me.checkType;
                        input_check.rowData = realTableRowData;
                        input_check.rowIndex = index;
                        if (index == 0) {
                            //td_check.style.borderTop="1px dotted #CAD9EA";
                            td_check.style.borderTop = "1px dotted transparent";
                        }
                        //input_check.name=$me.checkName;ie下getElementsByName不能成功  ,ie不能动态修改name ;
                        input_check.onclick = function(e) {
                            if ($me.checkType == "radio") {
                                return true;
                            }
                            var allCheckedCount = 0;
                            var allItems = document.getElementsByName($me.checkName);
                            for (var i = 0; i < allItems.length; i++) {
                                if (allItems[i].checked) {
                                    allCheckedCount++;
                                    break;
                                }
                            }
                            var headerCheckBox = document.getElementById($me.checkName + '_header');
                            if (headerCheckBox) {
                                headerCheckBox.checked = false;
                            }
                            if (allCheckedCount > 0) {
                                if (headerCheckBox) {
                                    headerCheckBox.checked = true;
                                }
                            }
                            cancelBubble(e);
                            if($me.onCheckedCallback){
                            	$me.onCheckedCallback($me, table, this.rowData);
                            }
                        };
                    }
                }
                if (("locked" == flag) && (0 == $me.lockedNames.length)) break;
            }
            var currentFoundColumn = currentOperColumns[columnIndex];
            var td = document.createElement("td");
            td.setAttribute("columnName",currentFoundColumn.name);
            //td.style.width = (currentFoundColumn.width+$me.columnAddWidth) +"px";	         
            if (0 == index) {
                //td.style.borderTop="1px dotted #CAD9EA";
                td.style.borderTop = "1px dotted transparent";
            }
            td.style.display = currentFoundColumn.hidden ? "none": "";
            td.style.textAlign = currentFoundColumn.align;
           
            //(value,tableObj,columnName,columnIndex,rowData){return value;};
            


            var currentColumnValue = data[currentFoundColumn.mapping] + "";



            if (
            /*(!currentColumnValue)||*/
            ("undefined" == currentColumnValue)) {
                currentColumnValue = $me.emptyChar;
            }
            var tempInnerHTML = "";
            if (((tempDatasLen - 1) == index) && ($me.isStatistic) &&($me.statisticColumnNames.length > 0)) {
                tempInnerHTML = currentColumnValue;
            } else {
                tempInnerHTML = currentFoundColumn.renderer(currentColumnValue, $me, currentFoundColumn.name, columnIndex, data);
            }

            //待renderer执行后设定的backgroupdColor可生效            
            if(currentFoundColumn.backgroundColor){
                td.style.backgroupColor = currentFoundColumn.backgroupColor;
            }

            var currentDataType = currentFoundColumn['type'];
            if (("number" == currentDataType) || ("double" == currentDataType)) {
                if (currentColumnValue == tempInnerHTML) {
                    if (false == currentFoundColumn['format']) {
                        tempInnerHTML = tempInnerHTML;
                    } else {
                        tempInnerHTML = formatNumberThousand(tempInnerHTML);
                    }
                }
            }
            var otherContentDivClass = "";
            if ($me.isAutoBreakContent) {
                otherContentDivClass = "autoBreakContent";
            }

            var forecolor = "black";
            if(currentFoundColumn.color){
                forecolor = currentFoundColumn.foreColor; 
            }

            td.innerHTML = "<div class='" + otherContentDivClass + "' style='color:" + forecolor + ";width:" + (currentFoundColumn.width) + "px;text-align:" + currentFoundColumn.align + "'>" + tempInnerHTML + "</div>";
            table_body_row.appendChild(td);
            if ("nonLocked" == flag) {
                if (columnIndex == $me.lastDisplayColumnIndex) {
                    var tempClass = "x-panel-table-td-border";
                    if ((index + 1) % 2 == 0) {
                        td.style.borderRightColor = "#FAFAFA";
                    } else {
                        td.style.borderRightColor = "#F7F3F7";
                    }
                    td.className = tempClass;
                }
            }
            columnIndex++;
        }
    }

    if ($me.span && ("nonLocked" == flag)) {
        try {
            tableHeaderDivContainer.removeChild($me.span);
        } catch(e) {}
        $me.span = null;
    }
    var oldDisplay = "";
    if ($me.displayToggleContainerObj) {
        oldDisplay = $me.displayToggleContainerObj.style.display;
        $me.displayToggleContainerObj.style.display = "block";
    }
    var realHeight = Math.max($me.tempDatas.length * 24, table.offsetHeight);
    if ($me.displayToggleContainerObj) {
        $me.displayToggleContainerObj.style.display = oldDisplay;
    }
    if ($me.isMobile) {
        tableDiv.style.overflow = "hidden";
    }
    if (((realHeight + 5) > $me.mainDataHeight) && ("nonLocked" == flag)) {
        vScroll = true;
        if (!$me.isMobile && !$me.isPad) {
            $me.isInitHeight = true;
            if(!tableHeaderDiv.oldWidth){
            	tableHeaderDiv.oldWidth = parseInt(tableHeaderDiv.style.width);
            }
            tableHeaderDiv.style.width = (parseInt(tableHeaderDiv.oldWidth) - 16) + "px";
            var span = document.createElement('div');
            $me.span = span;
            span.innerHTML = "&nbsp;&nbsp;";
            span.className = "attachElement";
            if ($me.isPagePrint) {
                span.style.display = "none";
                tableHeaderDiv.style.width = (relativeWidth) + "px";
            }
            if ($me.showHeader) {
                span.style.height = (30 + 30 * complexHeaders.length) + "px";
                span.style.lineHeight = (30 + 30 * complexHeaders.length) + "px";
            }
            tableHeaderDivContainer.appendChild(span);
        }
    }
    if ($me.isMobile) {
        tracywindyDynamicLoadJs(getRootPath() + "/js/tracywindy/iscroll.js");
        new iScroll(tableDiv, {
            hScroll: hScroll,
            vScroll: vScroll,
            hScrollbar: true,
            vScrollbar: true,
            onScrollMove: function(e) {
                try {
                    tableHeaderDiv.scrollLeft = -1 * this.x;
                    $me.lockedTableDiv.scrollTop = -1 * this.y;
                } catch(e) {}
            }
        });
    }
    if ($me.isPagePrint) {
        jQuery("table.x-panel-table-header tbody tr td>div").css("color", "#000000");
        jQuery("table.x-panel-table-header").css("borderColor", "transparent");
        jQuery("table.x-panel-table-header tbody tr>td").css("background", "#DDD");
        jQuery("table.x-panel-table-header tbody tr>td").css("borderColor", "silver");
    }
    if ("nonLocked" == flag) {
        if ($me.lockedTable) {
            var tableRows = table.rows;
            var lockedTableRows = $me.lockedTable.rows;
            var rowLen = tableRows.length;
            var oldDisplay = "";
            var $hideTabs = jQuery("div.tabs-panels>div.panel:hidden");
            jQuery("div.tabs-panels>div.panel:hidden").each(function() {
                var hideTabsDisplayToggleContainerObj = jQuery(this).find("div[id='" + $me.mainDivId + "']")[0];
                if (hideTabsDisplayToggleContainerObj) {
                    $me.displayToggleContainerObj = this;
                }
            });
            if ($me.displayToggleContainerObj) {
                oldDisplay = $me.displayToggleContainerObj.style.display;
                $me.displayToggleContainerObj.style.display = "block";
            }
            for (var i = 0; i < rowLen; i++) {
                var tableRow = tableRows[i];
                var lockedTableRow = lockedTableRows[i];
                if (tableRow.offsetHeight != lockedTableRow.offsetHeight) {
                    tableRow.style.height = lockedTableRow.style.height = Math.max(tableRow.offsetHeight, lockedTableRow.offsetHeight) + "px";
                }
            }
            if ($me.displayToggleContainerObj) {
                $me.displayToggleContainerObj.style.display = oldDisplay;
            }
        }
        $me.loadMask.hide();
        //新增拖动改变列的宽度
        if ($me.showHeader && $me.isColumnResizable /*&& !$me.isFit*/) {
            if (!window.jQuery) {
                tracywindyDynamicLoadJs(getRootPath() + "/js/jquery/jquery.min.js");
            }
            if (!jQuery.resizable) {
                tracywindyDynamicLoadJs(getRootPath() + "/js/jquery/jquery.easyui.min.js");
            }
            var $ = jQuery;
            var $mainDiv = jQuery($me.mainDiv);
            $mainDiv.append("<div class=\"datagrid-resize-proxy\"></div>");
            var tableHeaderRows = tableHeader.rows;
            var tableHeaderRow = tableHeaderRows[tableHeaderRows.length - 1];
            var tableHeaderCells = tableHeaderRow.cells;
            var resizableAddSize = $me.resizableAddSize || 0;
            for (var i = 0; i < tableHeaderCells.length; i++) {
                var tableHeaderCell = tableHeaderCells[i];
                if(!$me.isNeedLocked){
                	if($me.isRank && $me.isCheck){
                		if(i < 2){
                			continue;
                		}
                	}else if($me.isRank || $me.isCheck) {
                		if(i < 1){
                			continue;
                		}
                	}
                }
                var startResizeFunc = (function(tableHeaderCell, index, $mainDiv, $me) {
                    return function(e) {
                    	$me.tableDiv.style.overflowX = "auto";
                        tableHeaderCell.beforeResizeLeft = (e.pageX - $mainDiv.offset().left - 1);
                        tableHeaderCell.beforeResizeWidth = jQuery(tableHeaderCell).width();
                        tableHeaderCell.beforeResizeHeight = jQuery(tableHeaderCell).height();
                        //$mainDiv.children("div.datagrid-resize-proxy").css({left:tableHeaderCell.beforeResizeLeft,display:"block"});
                        $mainDiv.children("div.datagrid-resize-proxy").css({
                            left: e.pageX + resizableAddSize,
                            display: "block"
                        });
                    };
                })(tableHeaderCell, i, $mainDiv, $me);
                var stopResizeFunc = (function(tableHeaderCell, index, $mainDiv, $me) {
                    return function(e) {
                        //$mainDiv.children("div.datagrid-resize-proxy").css({left:e.pageX-$mainDiv.offset().left-1,display:"none"});
                        $mainDiv.children("div.datagrid-resize-proxy").css({
                            left: e.pageX - 1 + resizableAddSize,
                            display: "none"
                        });
                        var afterResizeLeft = (e.pageX - $mainDiv.offset().left - 1);
                        var changedWidth = (afterResizeLeft - tableHeaderCell.beforeResizeLeft);
                        var columnName = tableHeaderCell.getAttribute("columnName");
                        var currentColumn = $me.columns[$me.findColumnIndex(columnName)];
                        currentColumn.width = (currentColumn.width+changedWidth);
                        /*if(!$me.resizeLoadMask){
                        	$me.resizeLoadMask = new tracywindyLoadMask(document.body,"<font color='red'>正在调整数据列宽，请稍等...</font>");
                        }
                        $me.resizeLoadMask.show();
                        createDataTable($me);
                        $me.resizeLoadMask.hide();*/
                        /*modify by traycywindy revise the resizable bug*/
                        jQuery(tableHeaderCell).find(">div:first").css("width", (currentColumn.width) + "px");
                        jQuery($me.table).find("td[columnName='"+columnName+"']").each(function(ii) {
                            var currentWidthAdd = 0;
                            if ((tableHeaderCell.beforeResizeWidth - 1) > jQuery(this).width()) {
                                //currentWidthAdd = 1;
                            }
                            var $div = jQuery(this).find(">div:first");
                            $div.css("width", (currentColumn.width) + "px");
                        });
                        $(tableHeaderCell).css("height", tableHeaderCell.beforeResizeHeight + "px");
                    };
                })(tableHeaderCell, i, $mainDiv, $me);
                jQuery(tableHeaderCell).resizable({
                    handles: "e",
                    onStartResize: startResizeFunc,
                    onResize: function(e) {
                        //$mainDiv.children("div.datagrid-resize-proxy").css({left:e.pageX-$mainDiv.offset().left-1,display:"block"});
                        $mainDiv.children("div.datagrid-resize-proxy").css({
                            left: e.pageX + resizableAddSize,
                            display: "block"
                        });
                        return false;
                    },
                    onStopResize: stopResizeFunc
                });
            }
        }
        //同步高度
        $me.synchronizedHeight();
    } else {
        var tableRows = table.rows;
        var rowLen = tableRows.length;
        for (var i = 0; i < rowLen; i++) {
            var tableRow = tableRows[i];
            var cells = tableRow.cells;
            var cellLen = tableRow.cells.length;
            var tdCell = cells[cellLen - 1];
            tdCell.style.borderRight = "0px solid transparent";
            try {
                tdCell.style.width = (parseInt(tdCell.style.width) + 1) + "px";
            } catch(e) {}
        }
    }

    //新增排序操作
    if ($me.showHeader && $me.isRemoteSortable) {
        if (!window.jQuery) {
            tracywindyDynamicLoadJs(getRootPath() + "/js/jquery/jquery.min.js");
        }
        var $ = jQuery;
        var tableHeaderRows = tableHeader.rows;
        var tableHeaderRow = tableHeaderRows[tableHeaderRows.length - 1];
        var tableHeaderCells = tableHeaderRow.cells;
        for (var i = 0; i < tableHeaderCells.length; i++) {
            var tableHeaderCell = tableHeaderCells[i];
            var sortClickFunc = (function(index) {
                return function(e) {
                    var currentColumnConfig = this.columnConfig;
                    if (currentColumnConfig) {
                        var isContainColumn = false;
                        if ($me.tableData.length > 0) {
                            isContainColumn = ("undefined" != typeof($me.tableData[0][currentColumnConfig["mapping"]]));
                        }
                        if (!isContainColumn) return;
                        var SortDir = !this.SortDir ? "asc": (("asc" == this.SortDir) ? "desc": "asc");
                        this.SortDir = SortDir;
                        var SortField = currentColumnConfig["mapping"];
                        $me.SortField = SortField;
                        $me.SortDir = SortDir;
                        jQuery($me.mainDiv).find("img.table-sort-img").remove();
                        var $sortDiv = jQuery("<img/>");
                        $sortDiv.attr("src", getRootPath() + "/images/sort_" + SortDir + ".gif");
                        $sortDiv.addClass("table-sort-img");
                        jQuery(this).find(">div:first").append($sortDiv);
                        $me.setParams({
                            "TableRemoteSortField":  SortField ,
                            "TableRemoteSortDir": SortDir
                        });
                        $me.reload();
                    }
                };
            })(i, $me);
            jQuery(tableHeaderCell).click(sortClickFunc);
        }
    }
    //modify by tracywindy 2014-05-12
    expandOrCollapseQueryArea($me.id,true);
    return true;
}
function createLockedDataTable($me) {
    return createAllDataTable($me, $me.lockedComplexHeaders, $me.lockedTableHeaderDivContainer, $me.lockedTableHeaderDiv, $me.lockedTableDiv, $me.lockedTable, "locked");
}
function createNonLockedDataTable($me) {
    return createAllDataTable($me, $me.complexHeaders, $me.tableHeaderDivContainer, $me.tableHeaderDiv, $me.tableDiv, $me.table, "nonLocked");
}
function createDataTable($me) {
	if($me.complexHeadersCallBack){
		$me.complexHeadersCallBack();
	}
    //记载数据成功后回调函数
    if ($me.completeCallBack) {
        $me.completeCallBack();
    }
    initDataTable($me);
    if (!$me.isNeedLocked) {
        createNonLockedDataTable($me);
        return;
    }
    $me.lockedTableHeaderDivContainer.style.display = "";
    $me.lockedTableDiv.style.display = "";
    $me.lockedTable.style.display = "";
    if (createLockedDataTable($me)) {
        createNonLockedDataTable($me);
    }
}
//page分页
function createPage($me) {
    var jsonData = $me.jsonData;
    var totalCountAttibute = $me.basicInfo.total;
    var total = $me.total || jsonData[totalCountAttibute];
    var page = $me.pageDiv;
    var start = parseInt($me.params['start'] || $me.start);
    var pageSize = parseInt($me.params['pageSize'] || $me.pageSize);
    var realTotalCount = parseInt(total);
    var currentPage = start / pageSize + 1;
    var totalPages = parseInt((realTotalCount - 1) / pageSize) + 1;
    var realSize = start + pageSize;
    $me.start = start;
    $me.pageSize = pageSize;
    $me.realTotalCount = realTotalCount;
    $me.currentPage = currentPage;
    $me.totalPages = totalPages;
    $me.realSize = realSize;
    //最后一页
    if (currentPage == totalPages) {
        realSize = realTotalCount;
    }
    if (!document.getElementById($me.id + "_pageForm")) {
        var pageForm = document.createElement("form");
        $me.pageForm = pageForm;

        var pageFormInnerHtml = "<input name='table_load_local_page_start' id='" + $me.id + "_table_load_local_page_start' type='hidden' value=''>" + "<input name='table_load_local_page_limit' id='" + $me.id + "_table_load_local_page_limit' type='hidden' value=''>";
        with(pageForm) {
            id = $me.id + "_pageForm";
            action = window.location.href;
            method = 'POST';
            target = "_self";
            innerHTML = pageFormInnerHtml;
        }
        document.body.appendChild(pageForm);
    }
    var contentPage = "";
    contentPage += "<span>每页 <input type='text' style='width:20px;' title='按回车更换条数' id='id_change_page_size_" + $me.id + "' value='" + pageSize + "'> 条，当前显示 <font class='x-panel-table-div-page-number'> " + Math.min(start + 1, realSize) + "-" + (realSize) + "</font> 条记录/共<font class='x-panel-table-div-page-number'> " + realTotalCount + "</font> 条记录，";
    contentPage += "</span>";

    if ($me.isShortPage) {
        contentPage = "<span><input type='text' style='width:20px;' title='按回车更换条数' id='id_change_page_size_" + $me.id + "' value='" + pageSize + "'>条/页,共<font class='x-panel-table-div-page-number'> " + totalPages + "</font> 页 /<font class='x-panel-table-div-page-number'> " + realTotalCount + "</font> 条，";
        contentPage += "</span>";
    }

    var toInfo = "<span style='margin-right:20px;'>";
    if (currentPage > 1) {
        toInfo += '&nbsp;<img align="absmiddle" style="cursor:pointer; " onClick="goPage(\'' + $me.id + '\',\'first\')" src="' + getRootPath() + '/images/ico_first.gif" title="第一页" border="0">';
        toInfo += '&nbsp;<img align="absmiddle" style="cursor:pointer; " onClick="goPage(\'' + $me.id + '\',\'prev\')" src="' + getRootPath() + '/images/ico_prev.gif" title="上一页"    border="0">';
    } else {
        toInfo += '&nbsp;<img align="absmiddle" style="filter:Gray;"  src="' + getRootPath() + '/images/ico_first.gif" title="第一页"  border="0">';
        toInfo += '&nbsp;<img align="absmiddle" style="filter:Gray;" src="' + getRootPath() + '/images/ico_prev.gif" title="上一页" border="0">';
    }
    toInfo += "&nbsp;第 " + "<input type='input' title='按回车换页' id='id_skip_page_" + $me.id + "' value='" + currentPage + "' style='line-height:15px;width:20px;'>" + " 页";
    if (currentPage < totalPages) {
        toInfo += '&nbsp;<img align="absmiddle" style="cursor:pointer; " onClick="goPage(\'' + $me.id + '\',\'next\')" src="' + getRootPath() + '/images/ico_next.gif" title="下一页" border="0">';
        toInfo += '&nbsp;<img align="absmiddle" style="cursor:pointer; " onClick="goPage(\'' + $me.id + '\',\'last\')" src="' + getRootPath() + '/images/ico_last.gif" title="最后页" border="0">';
    } else {
        toInfo += '&nbsp;<img align="absmiddle" style="filter:Gray;" src="' + getRootPath() + '/images/ico_next.gif" title="下一页" border="0">';
        toInfo += '&nbsp;<img align="absmiddle" style="filter:Gray;" src="' + getRootPath() + '/images/ico_last.gif" title="最后页" border="0">';
    }
    toInfo += '</span>';
    page.innerHTML = (contentPage + toInfo);

    document.getElementById('id_skip_page_' + $me.id).onkeypress = function(e) {
        var keyCode = getEvent(e).keyCode || getEvent(e).charCode;
        if (keyCode == 13) {
            goPage($me.id, parseInt("" == this.value ? 0 : this.value));
        }
    };
    document.getElementById('id_change_page_size_' + $me.id).onkeypress = function(e) {
        var keyCode = getEvent(e).keyCode || getEvent(e).charCode;
        if (keyCode == 13) {

            var currentTable = getTracywindyTable($me.id);
            currentTable.pageSize = parseInt(parseInt("" == this.value ? 0 : this.value));
            if (0 == currentTable.pageSize) {
                alert("显示条数必须大于0");
                return;
            }
            if ('local' == currentTable.loadMode) {
                currentTable.loadMask.show();
                doLocalTablePageReload(currentTable.params, 0, currentTable.pageSize);
            } else {
                currentTable.load(false);
            }
        }
    };
}
function doChangePageSize(tableId, currentPageSize) {
    var keyCode = getEvent(e).keyCode || charCode;
    if (keyCode == 13) {
        var currentTable = getTracywindyTable(tableId);
        currentTable.pageSize = parseInt(currentPageSize);
        if (0 == currentTable.pageSize) {
            alert("显示条数必须大于0");
            return;
        }
        currentTable.load(false);
    }
}
function goPage(tableId, toWhere) {
    var currentTable = getTracywindyTable(tableId);
    var pageSize = currentTable.pageSize;
    var start = 0;
    var currentPage = currentTable.currentPage;
    var totalPages = currentTable.totalPages;
    var total = currentTable.realTotalCount;
    if (typeof(toWhere) == 'string') {
        switch (toWhere) {
        case 'first':
            {
                start = 0;
                break;
            }
        case 'prev':
            {
                start = (currentPage - 2) * pageSize;
                break;
            }
        case 'next':
            {
                start = currentPage * pageSize;
                break;
            }
        case 'last':
            {
                start = (totalPages - 1) * pageSize;
                break;
            }
        }
    } else {
        if (toWhere < 1) {
            alert("跳转页数应该大于等于1");
            return;
        } else if (toWhere > totalPages) {
            alert("跳转页数应该小于等于" + totalPages);
            return;
        }
        start = (toWhere - 1) * pageSize;
    }
    currentTable.params['start'] = start;
    currentTable.start = start;
    if ('local' == currentTable.loadMode) {
        currentTable.loadMask.show();
        doLocalTablePageReload(currentTable.params, start, pageSize);
    } else {
        currentTable.params['start'] = start;
        currentTable.params['pageSize'] = pageSize;
        currentTable.params['total'] = total;
        currentTable.loadAjaxTableData();
    }
}
function doLocalTablePageReload(params, start, pageSize) {
    var oldHref = window.location.href;
    var prefixHref = "";
    var locationIndex = oldHref.indexOf("?");
    var jsonObj = {};
    if (locationIndex > -1) {
        prefixHref = oldHref.substring(0, locationIndex + 1);
        suffixHref = trim(oldHref.substring(locationIndex + 1, oldHref.length));
    } else {
        prefixHref = oldHref + "?";
    }
    jsonObj['table_load_local_page_start'] = start;
    jsonObj['table_load_local_page_limit'] = pageSize;
    var url = prefixHref + jsonToAttachmentParams(jsonObj);
    var constantFormId = "table_local_reload_form_id";
    var form = document.getElementById(constantFormId);
    if (form) {
        document.body.removeChild(form);
    }
    form = document.createElement("form");
    var tempInnerHtml = "";
    for (var p in params) {
        var tempStr = params[p];
        if (Object.prototype.toString.call(params[p]) == '[object String]') {
            tempStr = params[p].replace(/'/gi, '&#39;');
            //.replace(/"/gi,'&#34');
        }
        tempInnerHtml += "<input type='hidden' name='" + p + "' value='" + tempStr + "'/>";
    }
    with(form) {
        style.display = "none";
        id = constantFormId;
        target = "_self";
        action = url;
        method = "post";
        innerHTML = tempInnerHtml;
    }
    document.body.appendChild(form);
    form.submit();
    return false;
}
function attachmentParamsToJson(attchmentParams) {
    var jsonObj = {};
    var keysets = attchmentParams.split("&");
    for (var i = 0; i < keysets.length; i++) {
        var keyset = keysets[i].split("=");
        var key = trim(keyset[0]);
        var value = (keyset.length > 1) ? nullToString(keyset[0]) : "";
        jsonObj[key] = value;
    }
    return jsonObj;
}
function jsonToAttachmentParams(jsonObj) {
    var tempArr = new Array();
    for (var p in jsonObj) {
        var key = trim(p);
        var value = nullToString(jsonObj[p]);
        tempArr.push(key + "=" + value);
    }
    return tempArr.join("&");
}
function replaceParams(paramStr, paramName, paramValue) {
    var prefixRegExp = new RegExp("\s*&\s*" + paramName + "\s*=[^&]*&", "g");
    var suffixRegExp = new RegExp("\s*&\s*" + paramName + "\s*=[^&]*&$", "g");
    paramStr = paramStr.replace(prefixRegExp, "&" + paramName + "=" + paramValue + "&");
    paramStr = paramStr.replace(suffixRegExp, "&" + paramName + "=" + paramValue + "&");
    paramStr += paramName + "=" + paramValue + "&";
    return paramStr;
}
function doQueryAreaAutoQuery(table, isClear, isReload) {
    if (isClear) {
        for (var i = 0; i < table.queryAreaComboboxs.length; i++) {
            table.queryAreaComboboxs[i].setValue("");
        }
    }
    var params = getQueryAreaParams(table, isClear);
    table.setParams(params);
    if (isReload) {
        table.load();
    }
}
function getQueryAreaParams(table, isClear) {
    var queryAreaDivDom = table.queryAreaDiv;
    var params = {};
    recusionAllChildrenNodesParams(queryAreaDivDom, params, isClear);
    for (var p in params) {
        if (!p) continue;
        if (new RegExp("^" + table.id + "_queryArea_").test(p)) {
            var key = p.substring((table.id + "_queryArea_").length, p.length);
            var value = params[p];
            params[key] = value;
            delete params[p];
        }
    }
    return params;
}
function doQuickQuery(e,table){
	if(e && e.keyCode==13){
		doQueryAreaAutoQuery(table,false,true);
	}
}
/*function clearQueryAreaParams(table,isReload){
    var params = getQueryAreaParams(table,true);
    table.setParams(params);
    if(isReload){
    	table.load();
    }
}*/
function recusionAllChildrenNodesParams(parentDom, params, isClear) {
    var allChildrenNodes = parentDom.childNodes;
    var allChildrenNodesLen = allChildrenNodes.length;
    if (allChildrenNodesLen > 0) {
        for (var i = 0; i < allChildrenNodesLen; i++) {
            var childNode = allChildrenNodes[i];
            var nodeTagName = childNode.tagName;
            if (!nodeTagName) {
                continue;
            }
            switch (nodeTagName.toUpperCase()) {
            case "SELECT":
            case "TEXTAREA":
                {
                    setParamByDom(childNode, params, isClear);
                    break;
                }
            default:
                {
                    recusionAllChildrenNodesParams(childNode, params, isClear);
                }
            }
        }
    } else {
        setParamByDom(parentDom, params, isClear);
    }
}
function setParamByDom(domNode, params, isClear) {
    if (isClear) {
        domNode.value = "";
    }
    var paramName = domNode.name;
    var paramValue = domNode.value || "";
    if (paramName) {
        params[paramName] = paramValue;
    }
}