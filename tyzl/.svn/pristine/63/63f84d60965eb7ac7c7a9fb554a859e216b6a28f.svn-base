/*
var  renderToContainer = "id_attachment_table_container";//渲染到哪个div的id
var  attachmentType="测试";//附件分类
var  serialNo ="aaa";//流水号唯一标识
var  isReadOnly = false;//是否只能下载，不能上传和删除附件
var  width =1000;//上传列表宽度
var  height = 500;//上传列表高度
var  title  ="测试附件上传";//列表标题
var  border  = false;//是否产生边框
new commonAttachmentFileUpload(renderToContainer,//渲染到哪个div的id
	attachmentType,//附件分类
	identifierOne,//流水号唯一标识
	identifierTwo,//流水号唯一标识
	identifierThree,//流水号唯一标识
	identifierFour,//流水号唯一标识
	isReadOnly,//是否只能下载，不能上传和删除附件
	width,//上传列表宽度
	height,//上传列表高度
	title,//列表标题,
	border//是否产生边框
 );*/
//上传表单组件
var tenArr = [ "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten" ];
function createAttachmentFileContainers() {
	var uploadWindow = mini.get('id_uploadAttachmentFileWindow');
	if (uploadWindow == undefined) {
		var uploadAttachmentFileWindow_html = "";
		uploadAttachmentFileWindow_html += '<div id="id_uploadAttachmentFileWindow" title="附件上传" class="mini-window" style="display:none;width:600px;height:500px;background-color:#FDF9F8;">';
		uploadAttachmentFileWindow_html += '	<div style="width:100%;">';
		uploadAttachmentFileWindow_html += '		<iframe style="display:none;" name="attachment_file_upload_real_submit_frame"></iframe>';
		uploadAttachmentFileWindow_html += '		<form id="id_uploadAttachmentFileForm" enctype="multipart/form-data" target="attachment_file_upload_real_submit_frame"  method="post" >';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="attachmentFileProcessKey" id="id_attachmentFileProcessKey">';
	//	uploadAttachmentFileWindow_html += '			<input type="hidden" name="docid" value="' + __getDocId() + '">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="module" id="id_attachmentFileModule">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="attachmentFileDictId" id="id_attachmentFileDictId">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="attachmentFileUploadInfoId" id="id_attachmentFileUploadInfoId">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="attachmentFileUnionKey" id="id_attachmentFileUnionKey" value="">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="batchDownloadAttachmentFileTitle" id="id_batchDownloadAttachmentFileTitle" value="">';
		uploadAttachmentFileWindow_html += '			<input type="hidden" name="jbpmWorkflowHistoryInfoId" value=' + (window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '') + '>';
		/*
		 * uploadAttachmentFileWindow_html += ' <input type="hidden"
		 * name="identifierOne" id="id_identifierOne">';
		 * uploadAttachmentFileWindow_html += ' <input type="hidden"
		 * name="identifierTwo" id="id_identifierTwo">';
		 * uploadAttachmentFileWindow_html += ' <input type="hidden"
		 * name="identifierThree" id="id_identifierThree">';
		 * uploadAttachmentFileWindow_html += ' <input type="hidden"
		 * name="identifierFour" id="id_identifierFour">';
		 */
		for ( var j = 0; j < tenArr.length; j++) {
			var identifierItem = ("identifier" + tenArr[j]);
			uploadAttachmentFileWindow_html += '		<input type="hidden" name="' + identifierItem + '" id="id_' + identifierItem + '">';
		}
		uploadAttachmentFileWindow_html += '			<table align="center" style="width:90%">';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc">上传类别:</td><td class="input_value"><label id="id_uploadAttachmentFileWhich"/></label></td></tr>';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc">当前时间:</td><td class="input_value"><label id="id_uploadAttachmentFileTime"/></label></td></tr>';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc">文件选择:</td><td class="input_value"><input type="button" value="新增" onclick="addUploadLocation(this)" locationindex="0" /></td></tr>';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc"></td><td class="input_value" id="id_upload_location"><input type="file" name="name_upload_file_name" id="id_uploadAttachmentFile" onchange="onUploadImgChange(this)"/><span class="content-required">*</span></td></tr>';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc">文件说明:</td><td class="input_value"><textarea name="remark" id="id_uploadAttachmentFileRemark"/></textarea></td></tr>';
		uploadAttachmentFileWindow_html += '				<tr><td class="input_label_desc" colspan="2" style="color:red;">请按照顺序上传附件</td></tr>';
		uploadAttachmentFileWindow_html += '				<tr class="content-separator">';
		uploadAttachmentFileWindow_html += '					<td colspan="2" style="text-align:center;">';
		uploadAttachmentFileWindow_html += '						<a class="mini-button" onclick="__uploadActionSubmit"><span>确定</span></a>';
		uploadAttachmentFileWindow_html += '						<a class="mini-button" onclick="__uploadActionCancel"><span>取消</span></a>';
		uploadAttachmentFileWindow_html += '					</td>';
		uploadAttachmentFileWindow_html += '				</tr>';
		uploadAttachmentFileWindow_html += '			</table>';
		uploadAttachmentFileWindow_html += '		</form>';
		uploadAttachmentFileWindow_html += '	</div>';

		uploadAttachmentFileWindow_html += '	<div id="preview_wrapper" style="display:none;">';
		uploadAttachmentFileWindow_html += '		<div id="preview_fake">';
		uploadAttachmentFileWindow_html += '			<img id="preview"  onload="onPreviewLoad(this)"/>';
		uploadAttachmentFileWindow_html += '		</div>';
		uploadAttachmentFileWindow_html += '		<img id="preview_size_fake"/>';
		uploadAttachmentFileWindow_html += '	</div>';
		uploadAttachmentFileWindow_html += '</div>';

		uploadAttachmentFileWindow_html += '<div id="id_uploadAttachmentProcessFileWindow" class="mini-window" title="" style="display:none;width:400px;height:160px;background-color:#FDF9F8;">';
		uploadAttachmentFileWindow_html += '	<table align="center" style="width:100%">';
		uploadAttachmentFileWindow_html += '		<tr><td class="input_label_desc" style="padding:10px 5px;">上传进度:</td><td class="input_value" style="padding-top:15px;"><div id="id_uploadAttachmentProcessFilePercent" style="width:100px;"></div></td></tr>';
		uploadAttachmentFileWindow_html += '		<tr><td class="input_label_desc" style="padding:10px 5px;">存储进度:</td><td class="input_value" style="padding-top:15px;"><div id="id_uploadAttachmentProcessSaveFilePercent" style="width:100px;"></div></td></tr>';
		uploadAttachmentFileWindow_html += '	</table>';
		uploadAttachmentFileWindow_html += '</div>';

		jQuery(document.body).append(jQuery(uploadAttachmentFileWindow_html));
		mini.parse(document.getElementById('id_uploadAttachmentFileWindow'));
		mini.parse(document.getElementById('id_uploadAttachmentProcessFileWindow'));
	}
}
//添加一行文件上传的位置
function addUploadLocation(obj){
	var index = Number($(obj).attr("locationindex")) + Number(1);
	document.getElementById("id_upload_location").innerHTML+='<div id="div_'+ index +'"><input type="file" name="name_upload_file_name' + index + '" /><input type="button" value="删除"  onclick="deleteUploadLocation(' + index + ')"/></div>';
	$(obj).attr("locationIndex",index);
}
//删除当前行文件上传的位置
function deleteUploadLocation(index){
	document.getElementById("id_upload_location").removeChild(document.getElementById("div_" + index));
}
//批量上传文件
function __uploadActionSubmit() {
	uploadAttachmentFileFormUploadSubmit(getRootPath() + '/attachmentfile/uploadAttachmentFiles.action');
}

function __uploadActionCancel() {
	mini.get("id_uploadAttachmentFileWindow").hide();
}

// 批量文件下载
function batchUploadFiles(table, batchDownloadAttachmentFileTitle) {
	var attchmentDetailIdStr = "";
	var include_file_ids = "";
	var checkedRowsData = table.getCheckedRowDatas();
	var len = checkedRowsData.length;
	if (len < 1) {
		mini.alert("请选择要批量下载的资料！");
		return;
	} else {
		for ( var i = 0; i < len; i++) {
			include_file_ids = checkedRowsData[i]["attachmentFileUploadInfoDetailIds"].split("@@_@@").join(",");
			if (include_file_ids) {
				attchmentDetailIdStr += include_file_ids;
			}
			if ((i != (len - 1)) && (checkedRowsData[i + 1]["attachmentFileUploadInfoDetailIds"])) {
				attchmentDetailIdStr += ",";
			}
		}
	}
	if ("" == attchmentDetailIdStr) {
		mini.alert("请选择要批量下载的资料！");
		return;
	}
	var tableBatchDownloadFormId = "id_table_batchDownloadForm";
	var batchForm = document.getElementById(tableBatchDownloadFormId);
	if (batchForm) {
		document.body.removeChild(batchForm);
	}
	batchForm = document.createElement("form");
	with (batchForm) {
		action = getRootPath() + "/attachmentfile/downloadBatchAttachmentFiles.action?browserType=" + SysBrowser.getBrowser().toLowerCase();
		target = table.name_exportExcelFormIFrame;
		style.display = "none";
		method = "post";
		innerHTML = "<input type='hidden' name='attchmentDetailBatchDownloadIdStr' value='" + attchmentDetailIdStr + "'/>" + "<input type='hidden' name='batchDownloadAttachmentFileTitle' value='" + batchDownloadAttachmentFileTitle + "'/>";
	}
	document.body.appendChild(batchForm);
	batchForm.submit();
	return false;
}
// attachmentFileDictListImplBeanName自定义实现 id_attachmentFileUnionKey
var globalAttachmentUploadOperCallBack = function(columnName, $me, isReadOnly) {
	$me.setColumnRenderer(function(value, tableObj, columnName, columnIndex, rowData) {
		var fileListUUIDStr = rowData["attachmentFileUploadInfoDetailIds"];
		if (!fileListUUIDStr) {
			return "暂无文档";
		} else {
			var splitStr = "@@_@@";
			var fileUUIDListArr = fileListUUIDStr.split(splitStr);
			var fileAdListArr = (rowData.attachmentFileUploadInfoDetailActivityDetailIds).split(splitStr);
			var fileNameListArr = (rowData.attachmentFileUploadInfoDetailChineseFileNames).split(splitStr);
			var fileTypeListArr = (rowData.attachmentFileUploadInfoDetailUploadTimes).split(splitStr);
			var fileUploadUserListArr = (rowData.attachmentFileUploadInfoDetailUploadUsers).split(splitStr);
			var fileSizeListArr = (rowData.attachmentFileUploadInfoDetailFileSizes).split(splitStr);

			var renderHtml = "<table style='border:0px solid #FFF;'><tr style='border:0px solid #FFF;'>";
			var tempValueArr = [];
			for ( var i = 0; i < fileUUIDListArr.length; i++) {
				var fileSizeFlag = "Kb";
				var fileSize = parseFloat(fileSizeListArr[i]);
				if (1024 * 1024 > fileSize) {
					fileSize = decimal(parseFloat(fileSizeListArr[i]) / 1024, 2);
				} else {
					fileSize = decimal(parseFloat(fileSizeListArr[i]) / 1024 / 1024, 2);
					fileSizeFlag = "Mb";
				}
				var tempValue = "<td style='border:0px solid #FFF;'><img style='position:relative;top:-2px' align='absmiddle' width=14  src='" + getRootPath() + "/images/attach.gif'/>";
				tempValue += "<a style='text-decoration:none;' onclick='downloadUploadFile(\"" + fileUUIDListArr[i] + "\");' title='下载文件' href='javascript:void(0);'>" + fileNameListArr[i] + "</a></td>";
				tempValue += "<td style='border:0px solid #FFF;'>【上传日期：" + fileTypeListArr[i] + "】</td>";
				tempValue += "<td style='border:0px solid #FFF;'>【上传人：" + fileUploadUserListArr[i] + "】</td>";
				tempValue += "<td style='border:0px solid #FFF;'>【大小：" + fileSize + "&nbsp;" + fileSizeFlag + "】</td>";
				// tempValue += "&nbsp;&nbsp;<a onclick='downloadUploadFile(\"" + fileUUIDListArr[i] + "\");' title='下载文件' href='javascript:void(0);'><img style='position:relative;top:-2px' border=0 align='absmiddle' width=14  src='" + getRootPath() + "/images/toolbar_export.gif'/></a>";
				// tempValue+="&nbsp;&nbsp;<a
				// style='text-decoration:none;font-size:24px;width:20px;background:url(${pageContext.request.contextPath}/images/icon_moreinfo.gif)
				// no-repeat ;'
				// onclick='editUploadFile("+i+",\"id_table_leasing_materials\");'
				// title='编辑' href='javascript:void(0);'>&nbsp</a>";
				if (!isReadOnly) {
					if (window.currentTaskActivityDetailId && (window.currentTaskActivityDetailId == fileAdListArr[i])) {
						tempValue += "<td style='border:0px solid #FFF;'><a onclick='removeUploadFile(\"" + tableObj.id + "\",\"" + fileUUIDListArr[i] + "\");' style='position:relative;top:-2px' title='删除' href='javascript:void(0);'><img border=0 align='absmiddle' width=14  src='" + getRootPath() + "/images/icon_delete.gif'/></a></td>";
					}
				}
				tempValueArr.push(tempValue);
			}
			renderHtml += tempValueArr.join("</tr><tr style='border:0px solid #FFF;'>");
			renderHtml += "</tr></table>";
			return renderHtml;
		}
	}, columnName);
};
// unionkey
var tracywindyAttachmentFileUpload = function(config) {
	var module = config.module;
	if (!module) {
		mini.alert("文件上传的模块名称不能为空!");
		return;
	}
	var batchDownloadAttachmentFileTitle = config.batchDownloadAttachmentFileTitle || "";
	var attachmentType = config.attachmentType, // 附件分类
	identifierOne = config.identifierOne || "", // 流水号唯一标识
	identifierTwo = config.identifierTwo || "", // 流水号唯一标识
	identifierThree = config.identifierThree || "", // 流水号唯一标识
	identifierFour = config.identifierFour || "", // 流水号唯一标识
	identifierFive = config.identifierFive || "", // 
	identifierSix = config.identifierSix || "", // 
	identifierSeven = config.identifierSeven || "", // 
	identifierEight = config.identifierEight || "", // 
	identifierNine = config.identifierNine || "", // 
	identifierTen = config.identifierTen || "", // 
	isReadOnly = config.isReadOnly || window.isCompletedTask;// 是否只能下载，不能上传和删除附件
	createAttachmentFileContainers();
	var tableConfig = {
		isCheck : (false == config.isCheck) ? false : true,
		isRank : (false == config.isRank) ? false : true,
		tools : [ {
			html : '<font style="color:red;font-weight:BOLD;">' + "批量下载文件" + '</font>',
			iconCls : 'icon-layers',
			handler : function(table) {
				batchUploadFiles(table, batchDownloadAttachmentFileTitle);
			}
		} ],
		isAutoBreakContent : true,
		isForceTools : true,
		columns : [// { header:'检查附件标示',name:'isAttachmentChecked'},
		{
			name : 'attachmentFileDictId',
			header : '文件编号',
			hidden : true
		}, {
			name : 'attachmentFileDictFileName',
			header : '文件名称',
			width : 300
		}, {
			name : 'attachmentFileUploadInfoId',
			header : '文件上传编号',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailIds',
			header : '上传明细文件编号',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailChineseFileNames',
			header : '上传明细文件显示名称',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailEncodeFileNames',
			header : '上传明细文件加密名称',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailFileSizes',
			header : '上传明细文件文件大小',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailUploadTimes',
			header : '上传明细文件上传日期',
			hidden : true
		}, {
			name : 'attachmentFileUploadInfoDetailRemarks',
			header : '上传明细文件文件说明',
			hidden : true
		}, {
			name : 'operOne',
			header : '上载',
			align : 'center',
			width : 80,
			renderer : function(value, tableObj, columnName, columnIndex, rowData) {
				if (!identifierOne) {
					return "<font color='red'>关键字为空</br>不能上载文件</font>";
				} else {
					return "<a href='javascript:void(0);' onclick='addUploadFile(\"" + tableObj.id + "\"," + rowData.rowIndex + ",\"add\");'>附件上传</a>";
				}
			}
		}, {
			name : 'operTwo',
			header : '上载文件清单'
		} ],
		loadMode : 'ajax',
		url : getRootPath() + '/attachmentfile/getAttachmentFileTableData.action',
		callBack : function() {
			var fileDetailWidth = this.width - this.rankSize - this.checkSize - 450;
			if (!config.isRank) {
				fileDetailWidth += this.rankSize;
			}
			if (!config.isCheck) {
				fileDetailWidth += this.checkSize;
			}
			this.tableDiv.style.overflowX = "hidden";
			if (config.isReadOnly) {
				fileDetailWidth += 80;
				this.setColumnHidden(true, 'operOne');
			}
			this.setColumnWidth(fileDetailWidth, 'operTwo');
			globalAttachmentUploadOperCallBack('operTwo', this, config.isReadOnly);
		},
		checkedKey : 'isAttachmentChecked',
		completeCallBack : function() {
			if (window.lazyLoadedObj) {
				lazyLoadedObj["attachment"] = true;
			}
		},
		params : {
			attachmentFileDictListImplBeanName : config.attachmentFileDictListImplBeanName || "",
			attachmentFileUnionKeyFieldMapping : config.unionKey || "",
			module : module,
			attachmentType : attachmentType,
			identifierOne : identifierOne,
			identifierTwo : identifierTwo,
			identifierThree : identifierThree,
			identifierFour : identifierFour,
			identifierFive : identifierFive,
			identifierSix : identifierSix,
			identifierSeven : identifierSeven,
			identifierEight : identifierEight,
			identifierNine : identifierNine,
			identifierTen : identifierTen
		}
	};
	for ( var p in config) {
		if ("params" == p) {
			continue;
		}
		tableConfig[p] = config[p];
	}
	if (config.params) {
		for ( var p in config.params) {
			tableConfig["params"][p] = config.params[p];
		}
	}
	var attachmentTable = new tracywindyTable(tableConfig);
	return attachmentTable;
};
// 上传列表所需函数变量
var currentUploadAttachmentFileTableId = null;
// 重置上传表单
function resetFileUploadAttachmentFileForm() {
	$("#id_attachmentFileDictId").val("");
	$("#id_attachmentFileUploadInfoId").val("");
	for ( var i = 0; i < tenArr.length; i++) {
		var item = tenArr[i];
		var identifierItem = ("identifier" + item);
		$("#id_" + identifierItem).val("");
	}
	$("#id_uploadAttachmentFileWhich").html("");
	$("#id_uploadAttachmentFileTime").html(getCurDateTime());
	$("#id_uploadAttachmentFileRemark").val("");
}
// 提交上传表单
function uploadAttachmentFileFormUploadSubmit(newAction) {
	isSuccessFlag = false;
	$("#id_attachmentFileProcessKey").val(new Date().getTime() + "_" + GenerateGuid());
	var uploadAttachmentFileForm = $("#id_uploadAttachmentFileForm")[0];
	with (uploadAttachmentFileForm) {
		action = newAction;
		submit();
	}
	try {
		mini.get("id_uploadAttachmentFileWindow").hide();
		mini.get("id_uploadAttachmentProcessFileWindow").show();
		$('#id_uploadAttachmentProcessFilePercent').progressbar({
			width : 300,
			value : 0
		});
		$('#id_uploadAttachmentProcessSaveFilePercent').progressbar({
			width : 300,
			value : 0
		});
		window.intervalAjaxFileProcess = setInterval("ajaxFileProcess()", window.constantLoopSeconds);
	} catch (e) {
	}
	return false;
}
var isSuccessFlag = false;
function uploadAttachmentFileFormSubmit(newAction) {
	isSuccessFlag = false;
	mini.mask({
		el : document.body,
		cls : 'mini-mask-loading',
		html : '数据加载中，请稍后...'
	});
	$("#id_attachmentFileProcessKey").val(new Date().getTime() + "_" + GenerateGuid());
	var uploadAttachmentFileForm = $("#id_uploadAttachmentFileForm")[0];
	with (uploadAttachmentFileForm) {
		action = newAction;
		submit();
	}
	mini.get("id_uploadAttachmentFileWindow").hide();
	return false;
}

function ajaxFileProcess() {
	var attachmentFileProcessKey = jQuery("#id_attachmentFileProcessKey").val();
	ajaxRequest({
		url : getRootPath() + "/attachmentfile/getAttachmentFileUploadedPercent.action?browserType=" + SysBrowser.getBrowser().toLowerCase(),
		success : function(res) {
			var fileProcess = JsonUtil.decode(res.responseText);
			var filePercent = fileProcess.uploadedPercent;
			var uploadedFinish = fileProcess.uploadedFinish;
			// jQuery("#id_uploadAttachmentProcessFilePercent").val(filePercent);
			if (!uploadedFinish) {
				$('#id_uploadAttachmentProcessFilePercent').progressbar('setValue', parseFloat(filePercent));
				if ((100 == parseFloat(filePercent))) {
					// alert(uploadedFinish);
					// jQuery("#id_uploadAttachmentProcessFileWindow").window("close");
					// window.clearInterval(intervalAjaxFileProcess);
				}
			} else {
				$('#id_uploadAttachmentProcessFilePercent').progressbar('setValue', parseFloat(100));
				$('#id_uploadAttachmentProcessSaveFilePercent').progressbar('setValue', parseFloat(filePercent));
				if ((100 == parseFloat(filePercent))) {
					mini.get("id_uploadAttachmentProcessFileWindow").hide();
					window.clearInterval(window.intervalAjaxFileProcess);
					if (!isSuccessFlag) {
						mini.mask({
							el : document.body,
							cls : 'mini-mask-loading',
							html : '数据加载中，请稍后...'
						});
					}
				}
			}
		},
		params : {
			attachmentFileProcessKey : attachmentFileProcessKey
		}
	});
}

// 上传附件
function addUploadFile(tableId, rowIndex, flag) {
	currentUploadAttachmentFileTableId = tableId;
	var attachmentTable = getTracywindyObject(tableId);
	var rowData = attachmentTable.getRowDataAt(rowIndex);
	resetFileUploadAttachmentFileForm();
	if ("add" == flag) {
		$("#id_attachmentFileDictId").val(rowData["attachmentFileDictId"]);
		$("#id_attachmentFileUploadInfoId").val(rowData["attachmentFileUploadInfoId"]);
		if (attachmentTable.unionKey) {
			$("#id_attachmentFileUnionKey").val(rowData[attachmentTable.unionKey] || "");
		}
		for ( var i = 0; i < tenArr.length; i++) {
			var item = tenArr[i];
			var identifierItem = ("identifier" + item);
			if (attachmentTable.params[identifierItem]) {
				$("#id_" + identifierItem).val(attachmentTable.params[identifierItem]);
			}
		}
		$("#id_attachmentFileModule").val(attachmentTable.params["module"]);
		$("#id_uploadAttachmentFileWhich").html(rowData["attachmentFileDictFileName"]);
		// $("#id_uploadAttachmentFileRemark").val("");
		//$('#id_uploadAttachmentFileForm')[0].reset()
		mini.get("id_uploadAttachmentFileWindow").show();
	}
}
var constantLoopSeconds = 1000;
// 上传附件回调函数
function uploadAttachmentFileCallBack(msg) {
	isSuccessFlag = true;
	mini.alert(msg);
	if (window.currentUploadAttachmentFileTableId) {
		getTracywindyObject(window.currentUploadAttachmentFileTableId).reload();
	}

	try {
		mini.get("id_uploadAttachmentProcessFileWindow").hide();
	} catch (e) {
	}
	if (window.intervalAjaxFileProcess) {
		window.clearInterval(window.intervalAjaxFileProcess);
	}
	setTimeout("if(window.intervalAjaxFileProcess){window.clearInterval(window.intervalAjaxFileProcess);}mini.unmask(document.body);", window.constantLoopSeconds);
	mini.unmask(document.body);
}

//上传结果
function uploadAttachmentFileResultCallBack(msg){
	isSuccessFlag = true;
	mini.alert(msg);
	if (window.currentUploadAttachmentFileTableId) {
		getTracywindyObject(window.currentUploadAttachmentFileTableId).reload();
	}
	try {
		mini.get("id_uploadAttachmentProcessFileWindow").hide();
	} catch (e) {
	}
	mini.unmask(document.body);
}
// 下载附件
function downloadUploadFile(uploadAttachmentFileDetailId) {
	mini.mask({
		el : document.body,
		cls : 'mini-mask-loading',
		html : '数据加载中，请稍后...'
	});
	createAttachmentFileContainers();
	var newAction = getRootPath() + "/attachmentfile/downloadAttachmentFile.action?browserType=" + SysBrowser.getBrowser().toLowerCase();
	newAction += "&uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId;
	uploadAttachmentFileFormSubmit(newAction);
	mini.unmask(document.body);
}
// 删除附件
function removeUploadFile(tableId, uploadAttachmentFileDetailId) {
	mini.confirm("确定删除该附件么？", "删除？", function(action) {
		if (action == 'ok') {
			mini.mask({
				el : document.body,
				cls : 'mini-mask-loading',
				html : '数据加载中，请稍后...'
			});
			currentUploadAttachmentFileTableId = tableId;
			var newAction = getRootPath() + "/attachmentfile/removeAttachmentFile.action";
			newAction += "?uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId;
			uploadAttachmentFileFormSubmit(newAction);
		}
	});
}
// 上传文件
function onUploadImgChange(sender) {
	// 单纯只为了文件名
	var filePath = sender.value;
	var fileExt = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();

	if (!checkFileExt(fileExt)) {
		mini.alert("不允许上传扩展名为《" + fileExt + "》的文件,请重新选择！");
		return;
	}
	/*
	 * var objPreview = document.getElementById( 'preview' ); objPreviewFake =
	 * document.getElementById( 'preview_fake' ), objPreviewSizeFake =
	 * document.getElementById( 'preview_size_fake' ); var file = null; var
	 * fileSize = 0; if(!checkLocalFileType(fileExt)){
	 * mini.unmask(document.body); return; } try{ //先采用HTML5方法 if(typeof
	 * FileReader !== 'undefined'){ file = sender.files[0]; fileSize =
	 * file.fileSize || file.size; if(checkFileSize(fileSize)){ var reader = new
	 * FileReader(); reader.readAsDataURL(file); reader.onload = function(e){
	 * objPreview.src = this.result; }; } }else if( sender.files){
	 * //非IE，不支持HTML5方法 file = sender.files[0]; fileSize = file.fileSize;
	 * if(checkFileSize(fileSize)){ filePath = file.getAsDataURL();
	 * objPreview.src = filePath; }
	 * 
	 * }else if( objPreviewFake.filters ){ if(!checkPicture(fileExt)){
	 * mini.unmask(document.body); return; } // IE7,IE8 在设置本地图片地址为 img.src
	 * 时出现莫名其妙的后果 //（相同环境有时能显示，有时不显示），因此只能用滤镜来解决 // IE7, IE8因安全性问题已无法直接通过
	 * input[file].value 获取完整的文件路径 sender.select(); var imgSrc =
	 * document.selection.createRange().text;
	 * //objPreviewFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src =
	 * imgSrc;
	 * //objPreviewSizeFake.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src =
	 * imgSrc; //autoSizePreview( objPreviewFake,objPreviewSizeFake.offsetWidth,
	 * objPreviewSizeFake.offsetHeight ); //objPreview.style.display = 'none';
	 * //读取图片文件大小 var sh = setInterval(function(){ var img =
	 * document.createElement("img"); img.src = imgSrc; fileSize = img.fileSize;
	 * if (fileSize > 0){ checkFileSize(fileSize); clearInterval(sh); } img =
	 * null; } ,1000); } } catch(e) { mini.unmask(document.body); //
	 * alert("调用后台方法"); }
	 */

}
// 本地检测附件大小
function checkLocalFileType(ext) {
	if (!ext.match(/.jpg|.jpeg|.gif|.png|.bmp|.txt|.doc|.docx|.xls|xlsx/i)) {
		return false;
	}
	return true;
}
// 检测是否是图片
function checkPicture(ext) {
	if (!ext.match(/.jpg|.jpeg|.gif|.png|.bmp/i)) {
		return false;
	}
	return true;
}
// 检测文件类型
function checkFileExt(ext) {
	/*
	 * if (!ext.match(/.jpg|.jpeg|.gif|.png|.bmp|.doc|.txt/i)) { return false; }
	 */
	return true;
}
// 检测文件大小
function checkFileSize(fileSize) {
	mini.unmask(document.body);
	/*
	 * if(fileSize > 1024*1024){ alert("您上传的文件大于1M,请重新选择！"); return false; }
	 */
	return true;
}
// 预览
function onPreviewLoad(sender) {
	autoSizePreview(sender, sender.offsetWidth, sender.offsetHeight);
}
// 居中显示
function autoSizePreview(objPre, originalWidth, originalHeight) {
	var zoomParam = clacImgZoomParam(300, 300, originalWidth, originalHeight);
	objPre.style.width = zoomParam.width + 'px';
	objPre.style.height = zoomParam.height + 'px';
	objPre.style.marginTop = zoomParam.top + 'px';
	objPre.style.marginLeft = zoomParam.left + 'px';
}
// 图像缩放
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		width : width,
		height : height,
		top : 0,
		left : 0
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;

		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = height / rateWidth;
		} else {
			param.width = width / rateHeight;
			param.height = maxHeight;
		}
	}
	param.left = (maxWidth - param.width) / 2;
	param.top = (maxHeight - param.height) / 2;
	return param;
}
