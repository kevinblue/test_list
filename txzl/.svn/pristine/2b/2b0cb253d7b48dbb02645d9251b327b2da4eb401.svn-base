<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "quotation_scheme",
			renderTo: "id_table_quotation_scheme",
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
						html : '生成融资租赁方案',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							 var params=getParams();
						     var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
				                   		mini.alert("您已生成一份，无需重复生成");            	 
							    }
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams: {
									module:'WorkflowAttchmentFiles',
									jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
									attachmentFileDictId : 'ProjectQuotationScheme.01',//项目报价方案
									identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
									identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
									identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
									identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
									identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
									identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
									identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
									identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
									identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
									identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
								}, 
								templateno : 'F-201611001',
								tableid : 'quotation_scheme',
								modelname : '融资租赁方案',
								returntype : 'listtocurpage',
								parames : params
							});
							if(cdates.length==0){
							fileTeplate.createFile();
							mini.alert("您已成功生成融资租赁方案");
							if (mini.get("id_customworkflowattachment")) {
								mini.get("id_customworkflowattachment").reload();
								mini.get("id_workflowhisAttachment").reload();
							  }
							}
							saveCallBack();
						}
			        },
			        ],
			//data: JsonUtil.decode('${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'),
			xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list2.xml',
			params : {
				projectname:mini.get("project_name").getValue(),
				flowUnid:flowUnid,
				modelname : '融资租赁方案'
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
				{field: '', header: '操作',renderer:function(e){ var cfalg=showTools;
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
	params['label.custname'] = mini.get("cust_name").getValue();
	//租赁形式
	params['label.type'] = mini.get("proj_info.leasform").getText();
	//项目名称label11
	params['label.projname'] = mini.get("project_name").getValue();
	//业务模式
	params['label.projtype']= mini.get("projtype").getValue();
	//租赁期限
	params['label.leaseterm']= Math.ceil((mini.get("leaseterm").getValue())/12);
	//宽限期
	params['label.grace'] = mini.get("grace").getValue();
	//还款方式
	mini.get("incomenumberyear").getValue();
	params['label.incomenumberyear'];
	if(mini.get("incomenumberyear").getValue() == "income_1"){
		params['label.incomenumberyear']="每月偿还一次租金";
	}else if(mini.get("incomenumberyear").getValue() == "income_2"){
		params['label.incomenumberyear']="每两个月偿还一次租金";
	}else if(mini.get("incomenumberyear").getValue() == "income_3"){
		params['label.incomenumberyear']="每季偿还一次租金";
	}else if(mini.get("incomenumberyear").getValue() == "income_6"){
		params['label.incomenumberyear']="每半年偿还一次租金";
	}else if(mini.get("incomenumberyear").getValue() == "income_12"){
		params['label.incomenumberyear']="每年偿还一次租金";
	}
	params['label.settlemethod'] = mini.get("settlemethod").getText();
	//还款方式
	params['label.equipamt'] = mini.get("equipamt").getValue();
	//承租人保证金
	params['label.cautionmoney'] = mini.get("cautionmoney").getValue();
	//承租人保证金比率
	params['label.cautiondeductionratio'] = mini.get("cautiondeductionratio").getValue();
	//名义留购价
	params['label.nominalprice'] = mini.get("nominalprice").getValue();
	var jsonrent = mini.get('fund_fund_charge').getData();
 	var marginarr = new Array();
 	var poundagearr = new Array();
	for(var i=0;i<jsonrent.length;i++){
		var margin={};
		var poundage={};
		var moneyname=jsonrent[i]["feetypename"];
		if(moneyname=='保证金'){
			margin["plandate"] =jsonrent[i]["plandate"]; 
			margin["planmoney"] =jsonrent[i]["planmoney"];
			marginarr.push(margin);
		}else if(moneyname=='手续费'){
			poundage["plandate"] = jsonrent[i]["plandate"];
			poundage["planmoney"] = jsonrent[i]["planmoney"];
			poundagearr.push(poundage);
		}
	}
	marginarr.sort(function (a, b) {
        return parseInt(a.plandate.replace(/-/g, ''), 10) - parseInt(b.plandate.replace(/-/g, ''), 10);//升序
	});
	poundagearr.sort(function (a, b) {
        return parseInt(a.plandate.replace(/-/g, ''), 10) - parseInt(b.plandate.replace(/-/g, ''), 10);//升序
	});
		params['rent_list_1']=JsonUtil.encode(marginarr);
		params['rent_list_2']=JsonUtil.encode(poundagearr);
	params['label.createdate'] = new Date().format("yyyy年MM月dd日");
	params['rentpay_list'] = JsonUtil.encode(mini.get("fund_rent_plan").getData());
	params['money_list'] = JsonUtil.encode(mini.get("fund_put_config").getData());
	return params;
}
function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="融资租赁方案.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
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


</script>
<div id="id_table_quotation_scheme"></div>






