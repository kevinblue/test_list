<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
//var filekey = "${param.id}";
jQuery(function(){
	var showTools = true;
	if('${param.isShow}' == 'true'){showTools = false;}
	var tools= [{
		html : '上传',
		plain : true,
		iconCls : 'icon-add',
		handler : function(miniTable, buttonText) {uploadFileBill();}
	},'-','remove'];
	<%-- if('${param.isView}' == 'true'||isViewHistoryTask==true){
		tools.pop();
		tools.pop();
		showTools = false;
		//如果流程发起人 显示 上传按钮
		$.ajax({
			url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/workflow/proj/proj_credit/queryWorkflowStartUser.xml&docid=" + flowUnid,
			cache: false,
			type:'post',
			async:false,
			success: function (text) {
				var result = mini.decode(text);
				var actoruserid=result.datas[0].actoruser;
				if(actoruserid=="${sessionScope['login_userid']}"){
					showTools = true;
				}
			}
		});
	} --%>

	tenwa.createTable({
		id: "proj_application_form_report_list",
		renderTo: "id_table_proj_application_form_report_list",
		width: '100%',
		height: 200,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: true,
		multiSelect: true,
		showToolbar: showTools,
		tools: tools,
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		}, 
		xmlFileName : '/eleasing/workflow/proj/proj_wind/selection_accessories_report_list.xml',
		params : {
		   flowUnid : flowUnid,
		   //filekey:filekey,
		   modelname : '议案申请表相关附件'
		},
		columns: [
			{type: 'indexcolumn',width:9},
			{type: 'checkcolumn',width:12},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'createdate', header: '上传时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
			{field: 'realname', header: '上传人'},
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
	var resStr =base2;
	return resStr;
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
    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除机组选型报告相关附件, 请稍等...'});
	var url="/leasing/template/dropCreateFile.action";
/* 	var param=[];
	param["ids"]=ids+"";
	param["isAttachment"]=false;//连带级联操作删除自定义方法 */
	$.ajax({
		url:getRootPath()+url,
	      type: "post",
	      cache: false, 
	      data :{"ids":ids+"","isAttachment":false},
	      async : false,
		success:function(rs){
			mini.unmask(document.body);
		/* var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body); */
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
	});
}
/*处理上传的模板*/
function uploadFileBill() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '议案申请表相关附件',
			parames : {
				flowUnid : flowUnid,   //流程id
				//filekey : filekey　　　//项目id
			},
			jscallback : 'reloadcustcontactfileBill'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfileBill(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("proj_application_form_report_list").reload();
}
</script>
<div id="id_table_proj_application_form_report_list"></div>