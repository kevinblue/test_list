<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_word",
			renderTo: "id_table_contract_word",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成银行账号变更通知函',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							
							var params=getParams();
						     var cdates=miniTable.getData();
						     for(var i=0;i<cdates.length;i++){
				                    if(cdates[i].filename=="银行账号变更通知函.docx"){
				                   	 mini.alert("您已经生成银行账号变更通知函！");
				                    }
							    }	
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams: {
									module:'WorkflowAttchmentFiles',
									jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
									attachmentFileDictId : 'root.FileType.Type7.01',//银行帐号ID
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
								templateno : 'F-201607004',
								tableid : 'contract_word',
								modelname : '银行账号变更通知函',
								returntype : 'listtocurpage',
								parames : params
							});
							fileTeplate.createFile();
							if (mini.get("id_customworkflowattachment")) {
								mini.get("id_customworkflowattachment").reload();
								mini.get("id_workflowhisAttachment").reload();
							}
							saveCallBack();
						}
			        }],
			data: JsonUtil.decode('${empty json_contract_word_str ? "[]" : json_contract_word_str }'),
			updateOperCallBack: function(miniTable,formData){
			},
			/* operValidate:function(miniTable, rowDatas,flag){
				  if(flag=="remove"){
				   for(j=0;j<rowDatas.length;j++){
					   if(rowDatas[j].filename!="起租通知书_账户变更版.docx"||rowDatas[j].filename!="起租通知书_常规版.docx"){
	                  	 mini.alert("您只能删除客户起租通知书！");
	                        return false;
	                   }
				   }}
			       return true;
		     }, */
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间'},
				{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function getParams(){
	var params={};
	params["flowunid"]=flowUnid;
	params["contract_id"]="${requestScope['contract_info.id']}";
	//合同编号
	params['contract_info.contractnumber']="${requestScope['contract_info.contractnumber']}";
	//承租人
	params['contract_info.custname']="${requestScope['contract_info.custname']}";
	//开户户名
	params['contract_signatory.leaseaccname']=mini.get("afterleaseaccname").getValue();
	//开户银行
	params['contract_signatory.leaseaccbank']=mini.get("afterleaseaccbank").getValue();
	//开户帐号
	params['contract_signatory.leaseaccnumber']=mini.get("afterleaseaccnumber").getValue();
	//变更时间
	var myDate = new Date();
    var adjust_year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
    var adjust_month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
    var adjust_day = myDate.getDate(); 
   /*  var adjust_year=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'yyyy');
    var adjust_month=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'MM');
    var adjust_day=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'dd');*/
    params['change_info.changeDay']=adjust_year+'年'+adjust_month+'月'+adjust_day+'日'; 
	
	return params;
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
	if(filename=="客户起租通知书.docx"&&cfalg){
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

function checksigndate(){
	var checkflag;
	var contractid=mini.get("contract_info.id").getValue();
	$.ajax({
        url: getRootPath()+"/acl/checkSigndate.acl",
        type: "post",
        cache: false, 
        data :{"contractid": contractid},
        async : false,
        success: function (text) {
        	  if(text=='1'){//等于1表示签约时间有值
        		  
        		  checkflag = true;
        	  }else{
        		  
        		  checkflag = false;
        	  }
        	
        }
    });
	
   return checkflag;	
}
</script>
<div id="id_table_contract_word"></div>






