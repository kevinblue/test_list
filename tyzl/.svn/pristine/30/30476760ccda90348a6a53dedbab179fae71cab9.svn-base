<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script>
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'||isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "contract_word",
		renderTo: "id_table_contract_word",
		width: '100%',
		height: 300,
		lazyLoad: true,
		title: "合同清单",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['remove'],
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		},
		data: JsonUtil.decode('<mini:param  name="json_contract_word_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type:'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'create', header: '操作',renderer: showOperator}
		]
	});
});

function downloadFile(Id) {
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function showOperator(e){
	var id = e.record.id;
	var base = "<a style='text-decoration:underline;color:blue;' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a style='text-decoration:underline;color:blue;' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a style='text-decoration:underline;color:blue;' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a style='text-decoration:underline;color:blue;' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a style='text-decoration:underline;color:blue;' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3 ;
}

function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
 	
}
function  printCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"3"});
 	
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
	//window.open (getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi?id='+id,'newwindow',"resizable=true,top=0,left=0") ;
}
function showContractFile(value,tableObj,columnName,columnIndex,rowData){
	var base = "<a href='#' onclick='downloadContractWordUploadFile(\""+$("#projid").val()+"/"+rowData.wordaddress+"\",\""+rowData.wordtemplatename+"\")'>{1}</a>{2}";
	var separator = "&nbsp;&nbsp;";
	var updateFlag=value;
	var updateClickFunc="other";
	var field=base.replace("{1}",updateFlag).replace("{2}",separator);
	return field;
}
function downloadContractWordUploadFile(fileAddress,wordName) {
	  mini.mask({el: document.body,cls: 'mini-mask-loading',html: '操作进行中 请稍等...'});
	var newAction = getRootPath()+"/leasing/word/downLoadContractAttach.action?browserType="+SysBrowser.getBrowser().toLowerCase();
	newAction+="&fileAddress="+fileAddress+"&wordName="+wordName;
	uploadAttachmentFileFormSubmit(newAction);
	mini.unmask(document.body);
}
//删除生成的文件。把文件注为无效
function dropCreateFile(rowDatas){
	var plandata = rowDatas;
	var ids=[];
	for(var i=0;i<plandata.length;i++){
		ids.push(plandata[i].id);
	}
    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除合同 请稍等...'});
	var url="/leasing/template/dropCreateFile.action";
	var param=[];
	param["ids"]=ids+"";
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			alert(message);
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
		params:param
	});
}
</script>
<div id="id_table_contract_word"></div>