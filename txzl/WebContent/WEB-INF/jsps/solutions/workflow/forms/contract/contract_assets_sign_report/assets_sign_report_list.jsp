<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "assets_sign_word",
		renderTo: "id_table_assets_sign_word",
		width: '100%',
		height: 300,
		lazyLoad: true,
		title: "资产签约报告",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: [{
			html : '生成资产签约报告',
			plain : true,
			iconCls : 'icon-add',
			handler : function(miniTable, buttonText) {
				gennerate();
			}
		},'-','remove'],
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		},
		data: JsonUtil.decode('${empty json_assets_sign_word_str ? "[]" : json_assets_sign_word_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type:'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'create', header: '操作',renderer: showOperatorasset}
		]
	});
});

function downloadFile(Id) {
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function showOperatorasset(e){
	var id = e.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	//权限控制
	var authenticationstr = base2 + "&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	if('01资产经理'==stepName){
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
function gennerate(){
	if (miniui_ext.submitFormValidation(["contract_sign_info_form"]) == false) return false;
	var params={};
	var dates=mini.get("contract_info.assetsigndate").getValue().format("yyyy-MM-dd");
	
	var date=dates.split("-");
	params["contract_info.projname"]=mini.get("project_name").getValue();
	var reg = new RegExp(",", "g");
	params["contract_info.equipamt"]=parseFloat(mini.getbyName("equipamt").getValue().replace(reg,""))/10000;
	params["contract_info.year"]=date[0];
	params["contract_info.month"]=date[1];
	params["contract_info.day"]=date[2];
	params["contract_info.signplace"]=mini.get("contract_info.signplace").getValue();
	params["contract_info.custsignpeople"]=mini.get("contract_info.custsignpeople").getValue();
	params["contract_info.signpeople"]=mini.get("contract_info.signpeople").getValue();
	
	var arry = ['cooperationattitude','cooperationattitudememo','studyallreal','studyerrornum','studyerror','hospitalstatus','hospitalmemo','another'];
	for(var i=0;i<arry.length;i++){
		params["report_info."+arry[i]]=mini.get(arry[i]).getValue();
		
	}
/* 	params["report_info.cooperationgood"]=mini.get("cooperationgood").getValue();
	params["report_info.cooperationnormal"]=mini.get("cooperationnormal").getValue();
	params["report_info.cooperationbad"]=mini.get("cooperationbad").getValue();
	params["report_info.studyallreal"]=mini.get("studyallreal").getValue();
	params["report_info.studyerrornum"]=mini.get("studyerrornum").getValue();
	params["report_info.studyerror"]=mini.get("studyerror").getValue();
	params["report_info.opernormal"]=mini.get("opernormal").getValue();
	params["report_info.operabnormal"]=mini.get("operabnormal").getValue();
	params["report_info.another"]=mini.get("another").getValue(); */
	
	var arr_relatedperson=[];
	var datas=mini.get("relateperson").getData();
	
	for(var i=0;i<datas.length;i++){
		var obj={};
		obj['personname']=datas[i].personname;
		obj['mobilenumber']=datas[i].mobilenumber;
		obj['rawValue_unitposition']=datas[i].rawValue_unitposition;
		arr_relatedperson.push(obj);
	}
	
	var reg_json = new RegExp("\"", "g");
	params["relatedperson"]=JsonUtil.encode(arr_relatedperson).replace(reg_json,"'");
		
	var fileTeplate=new fileTemplateConfig({
   	 templateno:'F-201603006',
        modelname:"资产签约报告",
        tableid:"assets_sign_word",
       // returntype:'file',
        parames:params
        });
     fileTeplate.createFile();	
     saveCallBack(); 
}
</script>
<div id="id_table_assets_sign_word"></div>