<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "terminate_protocol",
		renderTo: "id_terminate_protocol",
		width: '100%',
		height: 300,
		lazyLoad: false,
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['remove','-',
		        {
			html : '生成提前还款申请书',
			plain : true,
			iconCls : 'icon-save',
			handler : createContractWord
		        },'-',{
		    		html : '上传',
		    		plain : true,
		    		iconCls : 'icon-add',
		    		handler : function(miniTable, buttonText) {
		    			uploadFileZhongZhi();}
		    	}
			],
	//data: JsonUtil.decode('${empty json_terminate_protocol_str ? "[]" : json_terminate_protocol_str }'),
	xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
	params : {
		flowUnid:flowUnid,
		modelname : '提前还款申请书'
	},
	removeOperCallBack: function(miniTable,rowDatas){
		dropCreateFile(rowDatas);
		return true;
	},
	columns: [
			{type: 'indexcolumn'},
			{type:'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: '', header: '操作',renderer:function(e){
				var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
		]
	});
});

function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
		var filename=temp.record.filename;
		if(filename=="提前还款申请书.docx"&&cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
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
	if(filename=="提前还款申请书.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
	}
	//权限控制
	/* var authenticationstr = base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4
		authenticationstr += "&nbsp;&nbsp;|&nbsp;&nbsp;"+base3; 
	return authenticationstr; */
}

function createContractWord(miniTable, buttonText) {
	//var tempids = getTempateIdInfo();
	/* if ("" == tempids){
		alert("请选择合同模板");return;
	} */
	  var cdates=miniTable.getData();
      for(var i=0;i<cdates.length;i++){ 
              		mini.alert("您已生成一份，无需重复生成");            	 
		    }
	var tempids = "8a9fb3105c2e3688015c2fa9bc9602e7";
	var params = {};
	params["flowunid"] = flowUnid;
	//params["templateid"] = tempids;
	//params["custcreatetemplateno"]="";
	//params["custcreatetemplatemethod"]="";
	initCreateWordData(params);
	var fileTeplate = new fileTemplateConfig({
		tableid : "terminate_protocol",
		isAttachment : true,
		attachmentParams:getAttachmentParams("prepayment_agreement.01","${currentProcessInstanceDBID}"),
		modelname : "提前还款申请书",
		templateno : 'F-201705007',
		returntype : 'listtocurpage',
		parames : params
		//url : '/leasing/template/CreateFileByTemplate.action',
	});
	if(cdates.length==0){
	fileTeplate.createFile();
	mini.alert("您已成功生成提前还款申请书");
	if (mini.get("id_customworkflowattachment")) {
		mini.get("id_customworkflowattachment").reload();
		mini.get("id_workflowhisAttachment").reload();
	  }
	}
	saveCallBack();
}
function initCreateWordData(params){
	 var reg=new RegExp(',','g');
     var cid= $("input[name='contract_info.id']").val();
     params['cid']=cid;
     params['nowdate.year']=mini.formatDate(new Date(),'yyyy');
     params['nowdate.month']=mini.formatDate(new Date(),'MM');
     params['nowdate.day']=mini.formatDate(new Date(),'dd');
     params['contract_condition.year']=mini.formatDate(mini.get("paydayadjust").getValue(),'yyyy');
     params['contract_condition.month']=mini.formatDate(mini.get("paydayadjust").getValue(),'MM');
     params['contract_condition.day']=mini.formatDate(mini.get("paydayadjust").getValue(),'dd');
     
     params['contract_info.contractnumber']=mini.get("contract_number").getValue();//融资租赁合同     
     params['contract_condition.equipamt']=mini.get("equipamt").getValue().replace(reg,'');//融资金额
     params['contract_condition.dxequipamt']=DX(mini.get("equipamt").getValue().replace(reg,''));//融资金额
     params["time_condition.leaseterm"]=mini.get("leaseterm").getValue();//租赁期限（月）
   //获得起租日日期
 	var  startdateformat=formatDate(mini.get("startdate").getValue());
 	params["time_condition.startdateyy"]=startdateformat.substring(0,4);
 	params["time_condition.startdatemm"]=startdateformat.substring(5,7);
 	params["time_condition.startdatedd"]=startdateformat.substring(8,10);
	//起租日加上租赁期限后的日期	
	var  gracenum=mini.get("grace").getValue();////计算宽限期时间---宽限期一般是按期次数
	var  num=mini.get("leaseterm").getValue();//租赁期次
	var enddate=addmulMonth(startdateformat,Number(num)+Number(gracenum));//租赁期需要加上宽限期
	params["time_condition.enddateyy"]=enddate.substring(0,4);
	params["time_condition.enddatemm"]=enddate.substring(5,7);
	params["time_condition.enddatedd"]=enddate.substring(8,10);
	 //约定终止日
	 var  agreementdate=formatDate(mini.get("paydayadjust").getValue());
	params["agreedate.agreementdateyy"]=agreementdate.substring(0,4);
	params["agreedate.agreementdatemm"]=agreementdate.substring(5,7);
	params["agreedate.agreementdatedd"]=agreementdate.substring(8,10);
	//变更原因	
	var changereason=mini.get("contract_change_info.changeinstruction").getValue();
	params["agreedate.changereason"]=changereason;
	var custname=mini.get("custname").getValue();
	params["contract_info.custname"]=custname;
	 //var cautionmoney=mini.get("cautionmoney").getValue();
	 //params['contract_condition.cautionmoney']=cautionmoney;//保证金
	 //params['contract_condition.dxcautionmoney']=DX(cautionmoney);
	 var dungrace = mini.get("dungrace").getValue();//已到期租前息
     var dunrent = mini.get("dunrent").getValue();//已到期租金 
     var penalty = mini.get("delaypenalty").getValue();//迟延违约金 
     var interest = mini.get("overcorpusinterest").getValue();//未到期租金中租赁本金至提前还款日的利息 
     var handmoney = mini.get("overcorpushandmoney").getValue();//未到期手续费
     var advmoney = mini.get("advancerepaymoney").getValue();//提前还款补偿金 
     var corpusage = mini.get("corpusoverage").getValue();//未到期租金中的租赁本金 
     var nominalprice = mini.get("nominalprice").getValue();//期末认购价款 
     var otherout = mini.get("otherout").getValue();//其他应付款项 
     var contracttotal = mini.get("contracttotal").getValue();//合计
     params['label.gracerent'] = dungrace+"/"+dunrent;
     params['label.penalty'] = penalty;
     params['label.interest'] = interest;
     params['label.handmoney'] = handmoney;
     params['label.advmoney'] = advmoney;
     params['label.corpusage'] = corpusage;
     params['label.nominalprice'] = nominalprice;
     params['label.otherout'] = otherout;
     params['label.contracttotal'] = contracttotal;
     //params['contract_condition.dxdunrent']=DX(mini.get("dunrent").getValue());//到期未付租金     
     //params['contract_condition.corpusoverage']=mini.get("corpusoverage").getValue();//未到期本金
     //params['contract_condition.dxcorpusoverage']=DX(mini.get("corpusoverage").getValue());//未到期本金
   
   
     
     /* var finalamount=parseFloat(params['contract_condition.dunrent'])+parseFloat(params['contract_condition.corpusoverage'])+100;
     params['contract_condition.finalamount']= finalamount;
     params['contract_condition.dxfinalamount']=DX(finalamount);
     
     var finalamountafter=parseFloat(finalamount)-parseFloat(params['contract_condition.cautionmoney']);
     params['contract_condition.finalamountafter']= finalamountafter;
     params['contract_condition.dxfinalamountafter']=DX(finalamountafter);
     
     var finalrentllist=parseFloat(mini.get("fund_rent_plan_new").getData().length)+1;
     var maxrentlist=mini.getbyName("incomenumber").getValue();
     params["contract_condition.finalrentllist"]=finalrentllist;
     params["contract_condition.maxrentlist"]=maxrentlist; */
}
var formatDate = function (date) {
	var date= new Date(Date.parse(date));
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
}
function  addmulMonth(dtstr,n){   // n个月后 
	   var yy=dtstr.substring(0,4);
var mm=dtstr.substring(5,7);
var dd=dtstr.substring(8,10);	   
var  m1=mm.substring(0,1);
var  m2=mm.substring(1,2);
var mmsum=0;
if(m1==0){
	mmsum=parseInt(m2)+parseInt(n);
}else{
	mmsum=parseInt(mm)+parseInt(n);
}
//给月和年设置值
mm=parseInt(mmsum%12); 		  
yy=parseInt(yy)+parseInt(mmsum/12);
if(mm<10){
	 mm="0"+mm;
 }

var dt = yy+"-"+mm+"-"+dd;
return dt;
}
function getTempateIdInfo() {
	var tempid = "";
	$('input[name="contract_word_list_check_box"]:checked').each(
			function() {
				//chk_value.push($(this).val());
				if (tempid.length > 0) {
					tempid = tempid + ",";
				}
				tempid = tempid + $(this).val();
			});
	$('input[name="contract_word_list_check_box"]:checked').each(
			function() {
				//chk_value.push($(this).attr("checked", false));
			});
	return tempid;
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
/* function chargeTemplate() {
	var param = {};
	param["oneClassify"] = 'wordtempletypefirst6';
	param["twoClassify"] = 'wordtempletypetwo.contract_terminate_protocol';
	ajaxRequest({
		url : '${pageContext.request.contextPath}/leasing/template/loadTemplateByClassify.action',
		method : 'POST',
		success : ajaxChargeTemplateCallBack,
		async : false,
		failure : function(res) {
			alert("抓协议模板失败!");
		},
		params : param
	});
}
function ajaxChargeTemplateCallBack(rs){
	var svote=rs.responseText;
    svote=svote.replace(/(^\s+)|(\s+$)/g, ""); 
    document.getElementById("contract_word_check_list").innerHTML=svote;
} */
/*处理上传的模板*/
function uploadFileZhongZhi() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '提前还款申请书',
			isAttachment:true,
			attachmentParams:getAttachmentParams('prepayment_agreement.01','${currentProcessInstanceDBID}'),
			parames : {
				flowUnid : flowUnid,
				//filekey : filekey　　　//项目ＩＤ
			} ,
			jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
 	mini.get("terminate_protocol").reload();
	loadcustomworkflowattachment(); 
}

</script>
<div id="id_terminate_protocol"></div>