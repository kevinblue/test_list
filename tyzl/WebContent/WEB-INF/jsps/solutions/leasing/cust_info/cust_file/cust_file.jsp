<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//显示添加文件窗口
function showAddfile(custfilename)
{
	$("#id_uploadAttachmentFile").val("");
	mini.get("cust_id").setValue(mini.get("custid").getValue());
	mini.get("attachmentFileDictId").setValue(custfilename)
	mini.get("addfile").show();
}
//保存上传
function __uploadActionSubmit()
{
	var form=$("#id_uploadCustFileForm")[0];
	with (form) {
		action = getRootPath() + '/attachmentfile/uploadCustFile.action';
		submit();
	}
	mini.get("addfile").hide();
}
//上传结果
function uploadAttachmentFileCallBack(msg){
	
	var s=msg.split(",");
	mini.alert(s[0]);
	creatdownloadUpload(s[1],s[2],s[3]);
	mini.unmask(document.body);
}
function showFile(filedict){
	var url = getRootPath() + '/table/getTableData.action';
	var params = { xmlFileName:'/common/queryCustImages.xml',"filedict":filedict};
	var custid=mini.get("custid").getValue();
	if(custid==null||custid==""){
		params.nullcustid=" is null";
	}
	else{
		params.custid=custid;
	}
	mini.mask({
	    el: document.body,
	    cls: 'mini-mask-loading'
	});
	tenwa.ajaxRequest({
        url:url,
        params:params,
        method:'post',
        success:function(res){
            var result = JsonUtil.decode(res.responseText);
		    var datas = result['datas'];
		    if(datas && datas.length > 0){
		    	jQuery("#imag").html('');
		    	jQuery.each(datas, function(index, appImage){
		    		var img = createAttachmentImageDiv(appImage);
		    		if(img){
			    		jQuery("#imag").append(img);
		    		}
		    	});
		    } else {
		    	jQuery("#imag").html('');
		    }
		    mini.unmask(document.body);
        },
        failure:function(res){
        	mini.unmask(document.body);
        }
    });
	mini.get("showfile").show();
}

// 根据单个图片数据获取一个document
function createAttachmentImageDiv(appImage){
	var $div = jQuery('<div/>');
	if(appImage){
		$div.addClass("img-box");
		$div.attr("imageid", appImage.idd);
		var $image = jQuery("<img/>");
		$image.attr("height", appImage.height);
		$image.attr("width", appImage.widths);
		$image.attr("realUrl", appImage.imagepath);
		$image.attr("alt", appImage.title);
		$image.attr("src", appImage.imagepath);
		$image.appendTo($div);
	}
	return $div;
}
//下载附件
function downloadUploadCustFile(uploadAttachmentFileDetailId) {
	var newAction = getRootPath() + "/attachmentfile/downloadAttachmentFile.action?";
	newAction += "uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId;
	var form=$("#id_uploadCustFileForm")[0];
	with (form) {
		action = newAction;
		submit();
	}
}
//删除附件
function removeUploadCustFile(tableId, uploadAttachmentFileDetailId) {
	mini.confirm("确定删除该附件么？", "删除？", function(action) {
		if (action == 'ok') {
			$("#"+tableId).html();
			mini.mask({
				el : document.body,
				cls : 'mini-mask-loading',
				html : '数据加载中，请稍后...'
			});
			
			var newAction = getRootPath() + "/attachmentfile/removeAttachmentCustFile.action";
			newAction += "?uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId+"&td="+tableId;
			var form=$("#id_uploadCustFileForm")[0];
			with (form) {
				action = newAction;
				submit();
			}
			
		}
	});
}
function creatdownloadUpload(custfilename,custfileid,td)
{
	if(custfileid=="")
	{
		$("#"+td).html("");
	}
	else
	{
		var renderHtml = "<table style='border:0px solid #FFF;'><tr style='border:0px solid #FFF;'>";
		renderHtml+="<td style='border:0px solid #FFF;'><a href='#' onclick='downloadUploadCustFile(\"" + custfileid + "\");'>"+custfilename+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"
		renderHtml+="<a onclick='removeUploadCustFile(\"" +td+ "\",\"" + custfileid + "\");' style='position:relative;top:-2px' title='删除' href='javascript:void(0);'><img border=0 align='absmiddle' width=14  src='" + getRootPath() + "/images/icon_delete.gif'/></a>";
		renderHtml += "</td></tr></table>";
		$("#"+td).html(renderHtml);
	}
}

</script>
<div id="addfile" class="mini-window" title="上传"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<iframe style="display:none;" name="attachment_file_upload_real_submit_frame"></iframe>
	<form action="" id="id_uploadCustFileForm" enctype="multipart/form-data"  target="attachment_file_upload_real_submit_frame" method="post">
		<input type="hidden" name="cust_id" id="cust_id" class='mini-hidden'>
		<input type="hidden" name="module" id="id_attachmentFileModule" class='mini-hidden' value="custfile">
		<input type="hidden" name="attachmentFileDictId" id="attachmentFileDictId" class='mini-hidden'>
		<input type="hidden" name="attachmentFileUploadInfoId" id="id_attachmentFileUploadInfoId" class='mini-hidden'>
		<input type="hidden" name="attachmentFileUnionKey" id="id_attachmentFileUnionKey" value="" class='mini-hidden'>
		<table align="center" style="width:90%">
		<tr><td class="input_label_desc"></td><td class="input_value" id="id_upload_location"><input type="file" name="name_upload_file_name" id="id_uploadAttachmentFile" /><span class="content-required">*</span></td></tr>
		<tr class="content-separator">
			<td colspan="2" style="text-align:center;">
			<a class="mini-button" onclick="__uploadActionSubmit"><span>确定</span></a>
			<a class="mini-button" onclick="javascript:{mini.get('addfile').hide();}"><span>取消</span></a>
			</td>
		</tr>
		</table>
	</form>
</div>
<div id="showfile" class="mini-window" title="预览"
		style="width: 60%; height: 60%;" showCloseButton="true"  showModal="true"
		allowResize="true" allowDrag="true" >
	<div id="imag">
	</div>
</div>



