<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "badassets_report",
			renderTo: "id_table_badassets_report",
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
						html : '生成不良资产报告',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
						   var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
		                   		mini.alert("您已生成一份，无需重复生成");
		                   		return;
						    }
							var params=getParams();
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams:getAttachmentParams("badassets_report.01","${currentProcessInstanceDBID}"),
								templateno : 'F-201709016',
								tableid : 'badassets_report',
								modelname : '不良资产报告',
								returntype : 'listtocurpage',
								parames : params
							});
								fileTeplate.createFile();
								saveCallBack();
								loadcustomworkflowattachment();
						}
			        }
			        ],
			xmlFileName : 'eleasing/workflow/contract/badassets_submitted/badassets_report_list.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '不良资产报告'
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
	//客户名称
	params['label.custname'] = mini.get("rawValue_contract_info.custname").getValue();
	//项目名称
	params['label.projname'] = mini.get("project_name").getValue();
	params['label.classifylevel'] = mini.getbyName("badassets_info.classifylevel").getText();
	params['label.fiamount']= mini.get("financing_amount").getValue();
	params['label.fibalance']= mini.get("financing_balance").getValue();
	params['label.reamount']= mini.get("receive_amount").getValue();
	params['label.paymentamount']= mini.get("payment_amount").getValue();
	params['label.basicsituation']= mini.get("basic_situation").getValue();	
	params['label.collmeasures']= mini.get("coll_measures").getValue();	
	params['label.badassets']= mini.get("badassets").getValue();	
	params['label.treatment']= mini.get("treatment_schema").getValue();	
	params['label.impsuggestion']= mini.get("impsuggestion").getValue();	
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
</script>
<div id="id_table_badassets_report"></div>