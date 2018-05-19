<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "penalty_notice",
			renderTo: "id_table_penalty_notice",
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
						html : '生成罚息支付通知书',
						plain : false,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							selectedDatas=mini.get("rent_income_plan").getSelecteds();	
							if(selectedDatas.length==0){
								mini.alert("请先从租金回笼计划中选择已回笼期次");
							}else{ 
							 var params=getParams();
						     var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
				                   		mini.alert("您已生成一份，无需重复生成");            	 
							    }
					   	$
						.ajax({
							url : getRootPath()
									+ "/acl/getPenaltyRunningWater.acl",
							type : "post",
							cache : false,
							data : {},
							async : false,
							success : function(	date) {
								params['number.num'] = date;
								var fileTeplate=new fileTemplateConfig({
									isAttachment : false,
									attachmentParams: {
										module:'WorkflowAttchmentFiles',
										jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
										attachmentFileDictId : 'penaltynotice.01',//项目报价方案
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
									templateno : 'F-201704005',
									tableid : 'penalty_notice',
									modelname : '罚息支付通知书',
									returntype : 'listtocurpage',
									parames : params
								});
								if(cdates.length==0){
									fileTeplate.createFile();
									mini.alert("您已成功生成罚息支付通知书");
									}
									saveCallBack();
									loadcustomworkflowattachment();
								
							},
							failure : function(date) {
								mini.alert(date);
								
							}
						});
							
							
						}
						}
			        },
			        ],
			//data: JsonUtil.decode('${empty json_penalty_notice_str ? "[]" : json_penalty_notice_str }'),
			xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '罚息支付通知书'
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
	//前面页面拿过来的值
	//console.info(selectedDatas);
	var params={};
	params["flowunid"]=flowUnid;
	params["contractid"]= document.getElementById("contract_info.id").value;
	params["rentlist"]=selectedDatas[0].rentlist;
	//params["label.custname"] = mini.getbyName("contract_info.custname").getValue();
	params["label.custname"] = mini.getbyName("contract_info.custid").getText();
	params["label.contractnum"] = mini.getbyName("contract_info.contractnumber").getValue();
	params["label.rentlist"]= selectedDatas[0].rentlist;
	params["label.penalty"]=selectedDatas[0].penalty;
	params["label.penaltyDX"]=tenwa.money2DX(params["label.penalty"]);
	params["label.rent"]=selectedDatas[0].rent;

	return params;
}

function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
		var filename=temp.record.filename;
		if(filename=="罚息支付通知书.docx"&&cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}else{
			return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}
	}
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="罚息支付通知书.docx"&&cfalg){
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
<div id="id_table_penalty_notice"></div>











