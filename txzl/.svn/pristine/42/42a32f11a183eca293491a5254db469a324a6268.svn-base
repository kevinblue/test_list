<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script>
var showTools = true;
jQuery(function(){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "contract_word",
		renderTo: "id_table_contract_word",
		width: '100%',
		height: 300,
		lazyLoad: false,
		title: "合同清单",
		isClickLoad:false,
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['remove','-',{
			html:'打包下载',
			iconCls : 'icon-download',
			handler:function(miniTable,buttonText){
				var zipFileName = "${requestScope['contract_info.custname'] }";
				batchDownloadBaseFileByIds(miniTable,zipFileName);
			}
			
		},'-',{
    		html : '上传',
    		plain : true,
    		iconCls : 'icon-add',
    		handler : function(miniTable, buttonText) {
    			contractlistuploadFile();}
    	}],
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		},
		//data: JsonUtil.decode('${empty json_contract_word_str ? "[]" : json_contract_word_str }'),
		xmlFileName : '/eleasing/workflow/proj/proj_credit/contract_detail_list.xml',
			params : {
				flowUnid : flowUnid,
				filekey : mini.get("contract_id").getValue(),
				modelname : '合同管理'
			},
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
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	//权限控制
	var authenticationstr = base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4
	if(showTools){
		authenticationstr += "&nbsp;&nbsp;|&nbsp;&nbsp;"+base3; 
	}
	return authenticationstr;
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
	param["isAttachment"]=true;//连带级联操作删除自定义方法
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			loadcustomworkflowattachment();
			mini.alert(message);
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
		params:param
	});
}
/*处理上传的模板*/
function contractlistuploadFile() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '合同管理',
			isAttachment:true,
			attachmentParams:getAttachmentParams('root.FileType.ContractFile.01','${currentProcessInstanceDBID}'),
		 	parames : {
				flowUnid : flowUnid,
				filekey : mini.get("contract_id").getValue()	
			},
			jscallback : 'listcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function listcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("contract_word").reload();
	loadcustomworkflowattachment();
}
</script>
<div id="id_table_contract_word"></div>