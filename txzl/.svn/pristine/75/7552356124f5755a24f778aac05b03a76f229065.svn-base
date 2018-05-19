<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "premise_report",
			renderTo: "id_table_premise_report",
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
					html : '生成付款流程报告',
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
					attachmentParams:getAttachmentParams("PremiseReport.01","${currentProcessInstanceDBID}"),
					templateno : 'F-201707009',
					tableid : 'premise_report',
					modelname : '付款流程报告',
					returntype : 'listtocurpage',
					parames : params
					});
					if(cdates.length==0){
						fileTeplate.createFile();
						mini.alert("您已成功生成付款流程报告");
						}
					saveCallBack();
					loadcustomworkflowattachment();
					}
			        }
			        ],
			xmlFileName : '/eleasing/workflow/fund/fund_put/fund_put_premise_report.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '付款流程报告'
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
	params["flowname"]=flowName;
	params["keyone"] = $("#id_currentHistoryTaskInfo_keyOne").val();
	//params["payment.projmanage"]=mini.get('rawValue_contract_info.projmanage').getValue();
	params["payment.department"]=mini.get('rawValue_contract_info.department').getValue();
	params["payment.progressnote"]=mini.get('progressnote').getValue();
	params["payment.projectname"]=mini.get('project_name').getValue();
	var jsoncurrent=mini.get("put_current").getData();
	var jsonpremise=mini.get("premise_condition").getData();
	var currentarr = new Array();
	for(var i=0;i<jsoncurrent.length;i++){
		var current={};
		current["contractname"] =jsoncurrent[i]["contractname"];
		current["connum"] =jsoncurrent[i]["connum"];
		current["paycontent"] =jsoncurrent[i]["devicename1"]+"的"+jsoncurrent[i]["fundtypename"]; 
		current["factobjectname"] =jsoncurrent[i]["factobjectname"];
		current["clientbank"] =jsoncurrent[i]["clientbank"];
		current["clientaccnumber"] =jsoncurrent[i]["clientaccnumber"];
		current["oldplandate"] =jsoncurrent[i]["oldplandate"];
		current["contractmoney"] =jsoncurrent[i]["contractmoney"];
		current["oldplanmoney"] =jsoncurrent[i]["oldplanmoney"];
		current["alreadymoney"] =jsoncurrent[i]["alreadymoney"];
		current["factmoney"] =jsoncurrent[i]["factmoney"];
		var payment =jsoncurrent[i]["paymentid"];
		var device =jsoncurrent[i]["devicename"];
		var fundtype =jsoncurrent[i]["fundtype"];
		var paymentnode="";
		var filename="";
		for(var m=0;m<jsonpremise.length;m++){
			var ment = jsonpremise[m]["paymentid"];
			var deviceid = jsonpremise[m]["devicename"];
			var feetype = jsonpremise[m]["feetype"];
			if(payment==ment.replace(/[^0-9]/g,'')&&device==deviceid&&fundtype==feetype){
				paymentnode += jsonpremise[m]["conditionname"]+"，";
				var jsonfile = jsonpremise[m]["filename"];
				for(var y = 0 ;y<jsonfile.length;y++){
					filename += jsonfile[y]["file_name"]+"，";
				}
			}
		}
		if (paymentnode.length > 0) {
			paymentnode = paymentnode.substr(0, paymentnode.length - 1);
	    }
		if (filename.length > 0) {
			filename = filename.substr(0, filename.length - 1);
	    }
		current["remark"] =paymentnode;
		current["attachment"] =filename;
		currentarr.push(current);
	}
	params['MC_payment_list']=JsonUtil.encode(currentarr);
	return params;
}
function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var filename=temp.record.filename;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ filename+"\")'>"+"预览</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
		if(filename=="租赁业务合同付款流程.xlsx"&&cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}else{
			return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}
	}
	var id = temp.record.id;
	var filename=temp.record.filename;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ filename+"\")'>"+"预览</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	if(filename=="租赁业务合同付款流程.xlsx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1;
	}

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
    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除付款流程报告 请稍等...'});
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
<div id="id_table_premise_report"></div>