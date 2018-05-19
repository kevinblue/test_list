<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "rent_change_notice",
			renderTo: "id_table_rent_change_notice",
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
					html : '生成租后变更通知书',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {
					   var cdates=miniTable.getData();
				       for(var i=0;i<cdates.length;i++){ 
			                mini.alert("您已生成一份，无需重复生成");            	 
					   }
						var params=getParams();
						var fileTeplate=new fileTemplateConfig({
							isAttachment : true,
							attachmentParams:getAttachmentParams("rent_change_notice.01","${currentProcessInstanceDBID}"),
							templateno : 'F-201708011',
							tableid : 'rent_change_notice',
							modelname : '租后变更通知书',
							returntype : 'listtocurpage',
							parames : params
						});
							fileTeplate.createFile();
							saveCallBack();
							loadcustomworkflowattachment();
						}
			        }
			        ],
			//data: JsonUtil.decode('${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'),
			xmlFileName : '/eleasing/workflow/contract/onhire_contract_change/rent_change_notice_file.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '租后变更通知书'
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
function getParams(){
	var params={};
	params["flowunid"]=flowUnid;
	//合同编号
	params['label.contractnumber'] = mini.get("contract_number").getValue();
	params['label.period'] = mini.get("startlist").getText();
	params['label.nowtime'] = new Date().format("yyyy年MM月dd日");
	//租金调整后
	var jsonnewrentplan = mini.get('fund_rent_plan_new').getData();
	//租金调整前
	var jsonrentplan = mini.get('fund_rent_plan').getData();
	for(var i=0;i<jsonnewrentplan.length;i++){
		jsonnewrentplan[i].oldrent = jsonrentplan[i]==undefined ? 0 : jsonrentplan[i].rent;
		jsonnewrentplan[i].oldcorpus = jsonrentplan[i]==undefined ? 0 : jsonrentplan[i].corpus;
		jsonnewrentplan[i].oldinterest = jsonrentplan[i]==undefined ? 0 : jsonrentplan[i].interest;
	}
 	var rentplanarr = new Array();
 	var period = mini.get("startlist").getText();
 	var a = parseInt(period.replace(/[^0-9]+/g, ''))-1;
	for(var i=a;i<jsonnewrentplan.length;i++){
		var rentplan={};
		rentplan["rentlist"] = parseFloat(jsonnewrentplan[i]["rentlist"]);
		rentplan["plandate"] = jsonnewrentplan[i]["plandate"];
		rentplan["oldrent"] = parseFloat(jsonnewrentplan[i]["oldrent"]).toFixed(2);
		rentplan["oldcorpus"] = parseFloat(jsonnewrentplan[i]["oldcorpus"]).toFixed(2);
		rentplan["oldinterest"] = parseFloat(jsonnewrentplan[i]["oldinterest"])/1.17.toFixed(2);
		rentplan["rent"] = parseFloat(jsonnewrentplan[i]["rent"]).toFixed(2);
		rentplan["corpus"] = parseFloat(jsonnewrentplan[i]["corpus"]).toFixed(2);
		rentplan["interest"] = parseFloat(jsonnewrentplan[i]["interest"])/1.17.toFixed(2);
		rentplanarr.push(rentplan);
	}
	params['rent_list']=JsonUtil.encode(rentplanarr);
	return params;
}
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
/*处理上传的模板
function uploadFile() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '融资租赁方案',
			isAttachment:true,
			attachmentParams:getAttachmentParams('ProjectQuotationScheme.01','${currentProcessInstanceDBID}'),
			parames : {
				flowUnid : flowUnid,
				//filekey : filekey　　　//项目ＩＤ
			},
			jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("rent_change_notice").reload();
	loadcustomworkflowattachment();
}
*/
</script>
<div id="id_table_rent_change_notice"></div>