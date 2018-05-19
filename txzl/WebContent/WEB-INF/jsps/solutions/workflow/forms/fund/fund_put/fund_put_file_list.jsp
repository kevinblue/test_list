<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input name="json_fund_put_file_str" id="json_fund_put_file_str" type="hidden" value='${empty json_fund_put_file_str ? "[]" : json_fund_put_file_str}'></input>
<input name="fund_put_base_file_id" id="fund_put_base_file_id" type="hidden" value="${requestScope['fund_put_base_file_id'] }"/>
<input name="fund_put_base_file_name" id="fund_put_base_file_name" type="hidden" value="${requestScope['fund_put_base_file_name'] }"/>
<div id="id_div_fund_put_file_container" style="padding: 5px 5px;"></div>
<script type="text/javascript">
$(function(){
	var industrytype = "${requestScope['contract_info.industrytype']}";
	var importType = "";
	if("medical" == industrytype){
		importType = "放款资料清单-医疗";
	}else{
		importType = "放款资料清单-非医";
	}
	var ButtonText="导入放款资料清单";
	new importExcelUtil({
		id : "fund_put_file",
		showBtn : !('${param.isView}' == 'true'),
		renderTo : "id_div_fund_put_file_container",
		uploadButtonText : ButtonText,
		importType : importType,
		fileKey : "${requestScope['contract_info.id']}",
		data : mini.decode('${empty json_fund_put_file_str ? "[]" : json_fund_put_file_str}'),
		baseFileId : "${requestScope['fund_put_base_file_id'] }",
		baseFileName : "${requestScope['fund_put_base_file_name'] }",
		importCompleteCallback : "uploadFundPutExcelFileCallBack"
	});
});

//上传文件回调函数
function uploadFundPutExcelFileCallBack(result){
	mini.unmask(document.body);
	result = result.replace(/(\n)|(\r)/g,"<br/>")
	var res = mini.decode(result);
	if(res.result == "success"){
		mini.alert(res.message);
		
		$("#json_fund_put_file_str").val(mini.encode(res.exceldata));
		$("#fund_put_base_file_id").val(res.basefileid);
		$("#fund_put_base_file_name").val(res.basefilename);
		
		var fund_put_file = getMiniuiExtObject("fund_put_file");
		fund_put_file.showDownloadHref(res.basefileid, res.basefilename);
		fund_put_file.loadExcelData(res.exceldata);
		fund_put_file.hideUploadWindow();
	}else{
		mini.alert(res.message);
	}
	mini.get("id_win_uploadfile").hide();
}
</script>