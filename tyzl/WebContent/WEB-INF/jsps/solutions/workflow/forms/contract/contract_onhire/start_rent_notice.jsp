<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
//	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
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
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '起租通知书',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							
							var params=getParams();							
						  /*   var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
				                   		mini.alert("您已生成一份，无需重复生成");            	 
							    } */
					       	  		
							var fileTeplate=new fileTemplateConfig({
					/* 			isAttachment : true,
								attachmentParams: {
									module:'WorkflowAttchmentFiles',
									jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
									attachmentFileDictId : 'root.FileType.Type3.TEST01',
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
								}, */	
								
								templateno : 'F-201701004',
								tableid : 'quotation_scheme',
								modelname : '起租通知书',
								returntype : 'listtocurpage',
								parames : params
							});
							
							//if(cdates.length==0){
						
							fileTeplate.createFile();
							mini.alert("您已成功生成起租通知书");
							/* if (mini.get("id_customworkflowattachment")) {
								mini.get("id_customworkflowattachment").reload();
								mini.get("id_workflowhisAttachment").reload();
							} */
						//	}
							saveCallBack();
						
						}
			        },
			        ],
			data: JsonUtil.decode('${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'),
			
			updateOperCallBack: function(miniTable,formData){
			},
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
	debugger;
	//custname,contractnumber,contractnumber1,startdate
	//客户名称
	params['label.custname'] = mini.getbyName("contract_info.custname").getValue();
	//合同号
	params['label.contractnumber'] = mini.get("contract_number").getValue();
	//合同号
	params['label.contractnumber1'] = mini.get("contract_number").getValue();
	//项目日期
	params['date.date'] = mini.get("proj_date").getValue();	
	
	//实际起租日			
	params['label.startdate'] = mini.get("startdate").getText();
	
	var rent_plan_grid = mini.get('fund_rent_plan_frame').getData();		
	var rent_plan_cn=[];
	//rentlist,plandate,rent,corpus,interest
	for(var i=0;i<rent_plan_grid.length;i++){
		var temp_rent_plan={};		
		temp_rent_plan["rentlist"]=rent_plan_grid[i].rentlist;//租赁期数			
		temp_rent_plan["plandate"]=rent_plan_grid[i].plandate;//租金到期日		
		temp_rent_plan["rent"]=rent_plan_grid[i].rent;//每期租金
		temp_rent_plan["corpus"]=rent_plan_grid[i].corpus;//每期本金
		temp_rent_plan["interest"]=rent_plan_grid[i].interest; //每期利息
		rent_plan_cn.push(temp_rent_plan);	
		console.info(temp_rent_plan);
	}
	params['table']=JsonUtil.encode(rent_plan_cn);
	var nowdate=new Date().format("yyyy-mm-dd"); 
	params['date.tyear']=nowdate.substring(0,4);//3.年
	params['date.tmonth']=parseInt((new Date).getMonth().toString()) + 1;
	params['date.tday']=nowdate.substring(8);//5.日
      
	//date,tyear,tmonth,tday

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
	if(filename=="起租通知书.docx"&&cfalg){
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

</script>
<div id="id_table_quotation_scheme"></div>






