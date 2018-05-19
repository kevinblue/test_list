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
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成保理放款审批表',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							
							var params=getParams();
							var b = mini.get("payment_current").getData();
							if(b.length==0 || b==null){
								mini.alert("你还未生成付款明细!");
							}else{
						    var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
				                   		mini.alert("您已生成一份，无需重复生成");            	 
							    }
					       	  		
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
								
								templateno : 'F-201611003',
								tableid : 'quotation_scheme',
								modelname : '保理放款审批表',
								returntype : 'listtocurpage',
								parames : params
							});
							
							if(cdates.length==0){
						
							fileTeplate.createFile();
							mini.alert("您已成功生成保理放款审批表");
							if (mini.get("id_customworkflowattachment")) {
								mini.get("id_customworkflowattachment").reload();
								mini.get("id_workflowhisAttachment").reload();
							}
							}
							saveCallBack();
						}
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
    //1客户名称-申请人
  //  custname,contractnumber,planmoney,factdate,recourseright,transferdate,paymentway
	params['label.custname'] = mini.get("cust_name").getValue();
	
//2保理业务操作方案批复号
  params['label.contractnumber'] = mini.get("contract_number").getValue();
  params['invoice.contract_id']=mini.get("contract_id").getValue();
// alert(mini.get("fund_payment_plan_info"));
//增值税发票/其他发票份数

//3申请融资币种及金额
 var a = mini.get("payment_current").getData();
 //console.info(a);
 var sum =0.00;
 for(var i = 0;i<a.length;i++ ){
	 var factmoney = a[i]["factmoney"];
	 sum= accAdd(sum,parseFloat(factmoney));	
 }
 function accAdd(arg1,arg2){ 
	 var r1,r2,m; 
	 try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
	 try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
	 m=Math.pow(10,Math.max(r1,r2)) 
	 return (arg1*m+arg2*m)/m 
	 } 
 params['label.planmoney'] =sum; //由于之前标签名定位错误，所以没有改标签名，所以这里的planmoney指所有factmoney的和
//4付款期限
//var b = mini.get("payment_current").getData();
var factdate;
if(a==""){
	params['label.factdate']=="";
}else{
	 factdate = a[0]["factdate"];
	 params['label.factdate']=factdate
}
//手续费费率
//5保理业务种类
var factoringtype=mini.getbyName("contract_info.factoringtype").getText();
var recourse= mini.get("recourseright").getText();
var recourseright=factoringtype+recourse;
 params['label.recourseright'] = recourseright;

//6应收账款转让通知
 params['label.transferdate'] = mini.get("transferdate").getText();
 
//7买方付款方式

 params['label.paymentway'] = mini.get("contract_info.paymentway").getText();
 
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

</script>
<div id="id_table_quotation_scheme"></div>






