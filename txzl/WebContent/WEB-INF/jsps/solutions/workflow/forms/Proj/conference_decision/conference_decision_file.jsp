<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
var confernumber = "${requestScope['conference_decision.confernumber']}";
jQuery(function(){
	var showTools = true;
	//if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "conference_decision_file",
			renderTo: "id_table_conference_decision_file",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
	    		html : '上传',
	    		plain : true,
	    		iconCls : 'icon-add',
	    		handler : function(miniTable, buttonText) {
	    			uploadFile();}
	    	}
			        ],
			//data: JsonUtil.decode('${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'),
			xmlFileName : '/eleasing/jsp/conference_decision/conference_decision_file_list.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '决策会议文件'
			},
			updateOperCallBack: function(miniTable,formData){
			},
			removeOperCallBack: function(miniTable,rowDatas){
				dropCreateFile(rowDatas);
				return true;
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
				{field: '', header: '操作',renderer:function(e){
				var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});

function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ basefilename+"\")'>"+"预览</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
	  return base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base2;
	}
	var id = temp.record.id;
	var basefilename = temp.record.filename;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ basefilename+"\")'>"+"预览</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
  return base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base2;
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
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
			if (mini.get("id_customworkflowattachment")) {
				mini.get("id_customworkflowattachment").reload();
				mini.get("id_workflowhisAttachment").reload();
			}
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
function uploadFile() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '决策会议文件',
			isAttachment:true,
			attachmentParams:getAttachmentParams('root.FileType.Conference.01','${currentProcessInstanceDBID}'),
			parames : {
				flowUnid : flowUnid,
				filekey : confernumber,　　　//会议编号
			},
			jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("conference_decision_file").reload();
	loadcustomworkflowattachment();
}
</script>
<div id="id_table_conference_decision_file"></div>