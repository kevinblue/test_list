<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var leaseForm = [{value: '1',name: '直租'}, {value: '2',name: '回租'}, {value: '0',name: '其它'}];
var comboParams = {
	oneclassifyCode:"wordtempletypefirst7",
	xmlFileName:"/eleasing/jsp/template_word/templatewordSelect.xml"
}
var projid = "${requestScope['contract_info.projid']}";
var contractid = "${requestScope['contract_info.id']}";
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
		    seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				//新增修改点击确认时调用该方法返回
				validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
					var answer = '';
					var ans = '';
					var oldConNum=document.getElementById('id_tasks_table1_editFormPopupWindow_form_oldcontnum$text').value;
					var nowConNum=document.getElementById('id_tasks_table1_editFormPopupWindow_form_contractnumber$text').value;
					if (editFormItemOperFlag == 'add') {
						var ans = sendAjax(nowConNum,answer);
					   if(ans=='0'){
						   mini.alert("合同号重复！");
						   answer = '';
						   ans = '';
						   return false;
					   }else if(ans=='1'){
						   answer = '';
						   ans = '';
						   return true;
					   }else{
						   mini.alert("请求异常！");
						   answer = '';
						   ans = '';
						   return false;
					   }
					};
					if (editFormItemOperFlag == 'edit') {
						if(oldConNum==nowConNum){
							return true;
						}else{
							var ans = sendAjax(nowConNum,answer);
							   if(ans=='0'){
								   mini.alert("合同号重复！");
								   answer = '';
								   ans = '';
								   return false;
							   }else if(ans=='1'){
								   answer = '';
								   ans = '';
								   return true;
							   }else{
								   mini.alert("请求异常！");
								   answer = '';
								   ans = '';
								   return false;
							   }
						}
						
					} 
				},
				showToolbar: showTools,
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				height: 260,
				showPager : false,
				lazyLoad : false,
				title:'合同编号设置',
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.proj.ContractNumberSettingTmp",
				
				params:{
					
					contractid:contractid,
					docid:flowUnid
					
				},
				
				multiSelect: false,
				xmlFileName : '/eleasing/workflow/proj/proj_contract/contract_change_number_setting_tmp.xml',
				tools : [ 'add', '-', 'edit', '-', 'remove'],
				queryButtonColSpan : 6,
				queryButtonNewLine : true,
				columns: [
                 { type : 'checkcolumn'},
					{ header:'序号',type : 'indexcolumn'},
					{field:'id',
						header:'主键',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
							
						}
					},
					
					{field:'contractnumber',
						header:'合同编号',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
						}
					},
					
					{field:'oldcontnum',
						header:'修改前合同编号',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
						}
					},
					
					{field:'docid',
						header:'流程ID',
						visible: false,
						formEditorConfig:{
							value:flowUnid,
							fieldVisible: false,
						}
					},
					{field:'projinfo',
						header:'项目ID',
						visible: false,
						formEditorConfig:{
							value:projid,
							fieldVisible: false,
						}
					},
					
					{field:'contractsubject',
						header:'合同主体',
						visible: true,
						formEditorConfig:{
							fieldVisible: true,
							valueField:'value'
						}
					},
					
					{
						field:'contracttypename',
						visible:true,
						header:'合同类型',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             }
					},
					{
						field:'contracttype',
						visible:false,
						header:'合同类型',
		   		         formEditorConfig:{
		   			              newLine:true,
		   			                 type:'queryinput',
					             required:true,
					          multiSelect:false, 
					            textField:'name',
					           valueField:'value',
					           showNullItem:"true",
					           nullItemText:"",
					           onSelect : setRegistcode,
					         fieldVisible:true,
					             params:{pid:'CONTRACT_TYPE',xmlFileName:'combos/comboDict.xml'}}},
					    
					{field:'custid',
						header:'客户信息',
						visible: false,
						formEditorConfig:{
							onSelect : setContractSubject,
							type:'queryinput',
							fieldVisible: true,
							 textField:'name',
					          valueField:'value',
							params:{cust_id:'xxx',
									docidfilter1:'xxx',
									projid:'xxx',
									docid:'xxx',
									projid1:'xxx',
									docid1:'xxx',
									projid2:'xxx',
									docid2:'xxx',
									limited:'xxx',
								    xmlFileName:'/eleasing/workflow/proj/proj_contract/custinfo_judge_change_set_number.xml'}
						}
					}
				] 
			});
		});
	});
//发送ajax请求验证
function sendAjax(nowConNum,answer){
	$.ajax({
		cache : false,
		async : false,
        type: "post",
        dataType: "JSON",
        url : getRootPath()+ "/acl/ContractApprovalStartActionCheck.acl",
        data: {nowConNum:nowConNum},
        success: function (data) {
        	if(data==0){
        		answer='0';
        	}else if(data==1){
        		answer='1';
        	}else{
        		answer='2';
        	}
        }
    });
	return answer;
}


//自动补充合同主体

function setContractSubject($me,queryInput,rowData){
	  var conType=document.getElementById('id_tasks_table1_editFormPopupWindow_form_contracttype$text').value;
	  /* if(conType==''||inputValueCon==undefined){
		  alert(conType);
		  return;
	  } */
	  var inputValueCon=document.getElementById('id_tasks_table1_editFormPopupWindow_form_contractsubject$text').value;
	  var inputValueCust=document.getElementById('id_tasks_table1_editFormPopupWindow_form_custid$text').value;
	  if(inputValueCon==''||inputValueCon==undefined){
		mini.getbyName("contractsubject").setValue(inputValueCust);
		
	  }
}


//选择合同类型后自动带出客户信息
function setRegistcode($me,queryInput,rowData) {
	var cust_id = "${requestScope['contract_info.custid']}";
	var rowDatas = queryInput.value;               
   var clientBankQueryInput = getMiniuiExtObject("id_tasks_table1_editFormPopupWindow_form_custid");
   var conidparams=clientBankQueryInput.params;
   //租赁合同
   if("CONTRACT_TYPE.01" == rowDatas){
	   conidparams.cust_id =  cust_id;
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
	    conidparams.projid='xxx';
	   	conidparams.docid='xxx';
	    conidparams.projid1='xxx';
	   	conidparams.docid1='xxx';
	   	conidparams.projid2='xxx';
	   	conidparams.docid2='xxx';
	   	conidparams.limited='xxx';
  }else  if("CONTRACT_TYPE.02" == rowDatas){ //采购合同，供应商    
	  conidparams.docidfilter1 =flowUnid;
	  conidparams.docidfilter2 = rowDatas;
	   	conidparams.contractid =  contractid;
	   	conidparams.docid  =  flowUnid;
	   	conidparams.contractid1='xxx';
	   	conidparams.docid1='xxx';
	   	conidparams.contractid2='xxx';
	   	conidparams.docid2='xxx';
	   	conidparams.cust_id='xxx';
	   	conidparams.limited='xxx';
   }else if("CONTRACT_TYPE.03" == rowDatas){ //担保合同，担保人
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
       conidparams.contractid2 =  contractid;
   	conidparams.docid2  = flowUnid;
   	conidparams.contractid='xxx';
   	conidparams.docid='xxx';
   	conidparams.contractid1='xxx';
   	conidparams.docid1='xxx';
   	conidparams.cust_id='xxx';
   	conidparams.limited='xxx';
   }else if("CONTRACT_TYPE.04" == rowDatas){//抵押合同，抵押人
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
   	conidparams.contractid1 = contractid;
   	conidparams.docid1  = flowUnid;
    conidparams.contractid='xxx';
   	conidparams.docid='xxx';
   	conidparams.contractid2='xxx';
   	conidparams.docid2='xxx';
   	conidparams.cust_id='xxx';
   	conidparams.limited='xxx';
   }else if("CONTRACT_TYPE.05" == rowDatas){//其他，所有客户
	   conidparams.docidfilter1 =flowUnid;
	   conidparams.docidfilter2 = rowDatas;
   	 conidparams.contractid='xxx';
   	 conidparams.docid='xxx';
   	 conidparams.contractid1='xxx';
   	 conidparams.docid1='xxx';
   	 conidparams.contractid2='xxx';
   	 conidparams.docid2='xxx';
   	 conidparams.cust_id='xxx';
   	conidparams.limited='';
   }else{
   }
}




jQuery(function(){
	tenwa.lazyLoadCombo("twoclassify",{oneclassifyCode:"wordtempletypefirst7",twoclassify:true,xmlFileName:"/eleasing/jsp/template_word/templatewordSelect.xml"});
});
//加载下一层级分类
function reloadNextClassify(e,comboid,nextcomboid){
	comboboxChanged(e)
	if(nextcomboid.length>2){
		var combo = mini.get(comboid);
		var nextCombo = mini.get(nextcomboid);
		var nextComboParams = mini.clone(comboParams);
		var comboUrl = window.globalWebRoot + '/table/getTableData.action';
		if(comboid == "twoclassify"){
			nextComboParams.threeclassify = true;
			nextComboParams.twoclassifycode = combo.getValue();
/*  			mini.get("threeclassify").setValue();
			mini.get("fourclassify").setValue();
			mini.get("fiveclassify").setValue(); 
			mini.get("sixclassify").setValue();  */
		}else if(comboid == "threeclassify"){
			nextComboParams.fourclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
			nextComboParams.threeclassifycode = combo.getValue();
	   /*   	mini.get("fourclassify").setValue();
			mini.get("fiveclassify").setValue(); 
			mini.get("sixclassify").setValue(); */
		}else if(comboid == "fourclassify"){
			nextComboParams.fiveclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
			nextComboParams.threeclassifycode = mini.get("threeclassify").getValue();
			nextComboParams.fourclassifycode = combo.getValue();
/* 		 	mini.get("fiveclassify").setValue(); 
			mini.get("sixclassify").setValue(); */
		}else{
			nextComboParams.sixclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
	/* 		nextComboParams.threeclassifycode = mini.get("threeclassify").getValue();
			nextComboParams.fourclassifycode = mini.get("fourclassify").getValue();  */
			nextComboParams.fiveclassifycode = combo.getValue();
		/* 	mini.get("sixclassify").setValue(); */
		}
		var nextComboParamsUrl = aputil.getParamsUrl(comboUrl, nextComboParams);
		//mini.get(nextcomboid).setUrl(nextComboParamsUrl);
	}
	chargeTemplate();
}

//获取合同模板
function chargeTemplate() {
	var twoClassify = mini.get("twoclassify").getValue();
/* 	var threeClassify = mini.get("threeclassify").getValue();
	var fourClassify = mini.get("fourclassify").getValue();
	var fiveClassify = mini.get("fiveclassify").getValue(); 
	var sixClassify = mini.get("sixclassify").getValue(); */
	if (twoClassify != "") {
		var param = {};
		param["twoClassify"] = twoClassify;
/* 		param["threeClassify"] = threeClassify;
 		param["fourClassify"] = fourClassify;
		param["fiveClassify"] = fiveClassify; 
		param["sixClassify"] = sixClassify; */
		//window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body, "正在加载合同模板 请稍等...");
		//currentDeleteFileLoadMask.show();
	    param["changeRowNum"]=4;
		ajaxRequest({
			url : '${pageContext.request.contextPath}/leasing/template/loadTemplateByClassify.action',
			method : 'POST',
			success : ajaxChargeTemplateCallBack,
			async : false,
			failure : function(res) {
				//currentDeleteFileLoadMask.hide();
				alert("抓合同模板失败!");
			},
			params : param
		});
	} else {
		document.getElementById("contract_word_check_list").innerHTML = "请选择筛选项!";
	}
}

function ajaxChargeTemplateCallBack(rs){
	var svote=rs.responseText;
    svote=svote.replace(/(^\s+)|(\s+$)/g, ""); 
    document.getElementById("contract_word_check_list").innerHTML=svote;
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
function createContractWord() {
	var tempids = getTempateIdInfo();
	if ("" == tempids){
		alert("请选择合同模板");return;
	}
	var params = {};
	params["flowunid"] = flowUnid;
	params["templateid"] = tempids;
	params["custcreatetemplateno"]="";
	params["custcreatetemplatemethod"]="";
	initCreateWordData(params);
	var fileTeplate = new fileTemplateConfig({
		isAttachment : true,
		attachmentParams: {
			module:'WorkflowAttchmentFiles',
			jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
			attachmentFileDictId : 'root.FileType.ContractFile.01',//合同文件ID
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
		tableid : "contract_word",
		modelname : "合同管理",
		url : '/leasing/template/CreateFileByTemplate.action',
		parames : params
	});
	fileTeplate.createFile();
	if (mini.get("id_customworkflowattachment")) {
		mini.get("id_customworkflowattachment").reload();
		mini.get("id_workflowhisAttachment").reload();
	}
}
function initCreateWordData(params) {
	var regx=new RegExp(',','g');
	var temp_obj={};
	var temp_arr=[];
	//追加参数
	var signatory_form=new mini.Form("contract_signatory_form");
	var signatory_data=signatory_form.getData().contract_signatory;
	for(var sfd in signatory_data){
		params["contract_signatory."+sfd]=signatory_data[sfd];
	}
	//租金开票信息
	var contract_rent_invoice_type_form=new mini.Form("contract_rent_invoice_type_form");
	var contract_rent_invoice_type_data=contract_rent_invoice_type_form.getData().contract_invoice_type;
	for(var critf in contract_rent_invoice_type_data){
		params["contract_invoice_type."+critf]=contract_rent_invoice_type_data[critf];
	}
	
	//合同基本信息
	 var contract_base_info_form=new mini.Form("contract_base_info_form");
	 var contract_base_info_data=contract_base_info_form.getData().contract_info;
	for(var cbid in contract_base_info_data){
		params["contract_info."+cbid]=contract_base_info_data[cbid];
	}
	params['contract_info.leasformname']=mini.getbyName("contract_info.leasform").getText();
	if(mini.getbyName("contract_info.leasform").getText() == "售后回租"){
		params['leas.leasback'] = "融资租赁合同(售后回租)";
	}else{
		params['leas.leasback'] = "融资租赁合同";
	}
	
	params['con.info']=(params["contract_info.contractnumber"]).replace(/P/,'PL')+params["contract_info.custname"];
	//购买类合同编号
	params['buytype_contract.number']=(params["contract_info.contractnumber"]).replace(/P/,'PS');
	//咨询服务协议
	params['askservice_contract.number']=(params["contract_info.contractnumber"]).replace(/P/,'PC');
	//其他类合同编号
	params['othertype_contract.number']=(params["contract_info.contractnumber"]).replace(/P/,'PL');
	//租赁物明显
	var contract_equip=mini.get("contract_equip").getData();
	params["contract_equip_list"]=mini.encode(contract_equip);
	
	var jsondata=mini.get("contract_equip").getData();
	var totalPrice = 0;
	for ( var i = 0; i < jsondata.length; i++) {
		totalPrice += parseFloat(jsondata[i]['equipprice']);
	}
	params["contract_equip.totalprice"]=totalPrice;
	params["contract_equip.dxtotalprice"]=DX(totalPrice);
	 
	var rent_plan_grid=mini.get("fund_rent_plan_frame").getData();
	
	
	var totalrent=0;
	var rent_plan_cn=[];
	for(var i=0;i<rent_plan_grid.length;i++){
		var temp_rent_plan={};
		temp_rent_plan["rentlist"]="第"+rent_plan_grid[i].rentlist+"期";
		temp_rent_plan["rent"]="人民币"+rent_plan_grid[i].rent+"元";
		totalrent+=parseFloat(rent_plan_grid[i].rent.replace(regx,''));
		rent_plan_cn.push(temp_rent_plan);
	}
	params["rent_plan"]=mini.encode(rent_plan_cn);
	
	//商务条件
	params["contract_condition.equipamt"]=mini.get("equipamt").getValue().replace(regx,'');
	params["contract_condition.dxequipamt"]=DX(mini.get("equipamt").getValue().replace(regx,''));
	params["contract_condition.cautionmoney"]=mini.get("cautionmoney").getValue().replace(regx,'');
	params["contract_condition.dxcautionmoney"]=DX(params["contract_condition.cautionmoney"]);
	params["contract_condition.cleanleasemoney"]=mini.get("cleanleasemoney").getValue().replace(regx,'');
	params["contract_condition.handlingchargemoney"]=mini.get("handlingchargemoney").getValue().replace(regx,'');
	params["contract_condition.dxhandlingchargemoney"]=DX(params["contract_condition.handlingchargemoney"]);
	params["contract_condition.firstpayment"]=mini.get("firstpayment").getValue().replace(regx,'');
	params["contract_condition.insuremoney"]=mini.get("insuremoney").getValue().replace(regx,'');
	params["contract_condition.incomenumber"]=mini.get("incomenumber").getValue();
	params["contract_condition.leaseterm"]=mini.get("leaseterm").getValue();//租赁期限（月）
	params["contract_condition.leasetermyear"]=decimal(parseFloat(mini.get("leaseterm").getValue())/12,0);//租赁期限（月）
	params["contract_condition.totalrent"]=decimal(totalrent,2);
	params["contract_condition.dxtotalrent"]=DX(decimal(totalrent,2));
	params["contract_condition.periodtype"]=mini.get("periodtype").getText();//租金支付类型
	if(mini.get("incomenumberyear").getValue()=='income_1'){
		params["contract_condition.incomenumberyear"]='1';
		params["contract_condition.dxincomenumberyear"]='每壹个月支付一期';
	}else if(mini.get("incomenumberyear").getValue()=='income_3'){
		params["contract_condition.incomenumberyear"]='3';
		params["contract_condition.dxincomenumberyear"]='每叁个月支付一期';
	}else if(mini.get("incomenumberyear").getValue()=='income_6'){
		params["contract_condition.incomenumberyear"]='6';
		params["contract_condition.dxincomenumberyear"]='每陆个月支付一期';
	}else if(mini.get("incomenumberyear").getValue()=='income_12'){
		params["contract_condition.incomenumberyear"]='壹拾贰';
		params["contract_condition.dxincomenumberyear"]='每壹拾贰个月支付一期';
	}else if(mini.get("incomenumberyear").getValue()=='income_2'){
		params["contract_condition.incomenumberyear"]='贰';
		params["contract_condition.dxincomenumberyear"]='每贰个月支付一期';
	}else if(mini.get("incomenumberyear").getValue()=='income_0'){
		params["contract_condition.dxincomenumberyear"]='不规则期间还款，具体支付日见《起租通知书》';
	}
	var totalincome=0;
	var managementmoney=mini.get("managementmoney").getValue().replace(regx,'');
	var nominalprice=mini.get("nominalprice").getValue().replace(regx,'');
	var beforeinterest=mini.get("beforeinterest").getValue().replace(regx,'');
	var otherincome=mini.get("otherincome").getValue().replace(regx,'');
	totalincome=decimal(parseFloat(totalrent)+parseFloat(params["contract_condition.handlingchargemoney"])+parseFloat(managementmoney)
			              +parseFloat(params["contract_condition.firstpayment"])+parseFloat(nominalprice)+parseFloat(otherincome)+parseFloat(beforeinterest),2);
	params["contract_condition.totalincome"]=totalincome;
	if(mini.get("adjuststyle").getValue()=='固定利率'){
		params["contract_rule.ratefloattype"]='本合同实际起租后，在本合同履行期间同期中国人民银行贷款基准利率发生任何调整和变更的，本合同项下承租人应付租金的金额不做调整和变更，承租人仍应按照出租人发出的《起租通知书》与《租金支付表》向出租人支付租金。';
	}else if(mini.get("adjuststyle").getValue() == "next_year" || mini.get("adjuststyle").getValue() == "next_year_next_list"){
		params["contract_rule.ratefloattype"]='本合同实际起租后，如遇中国人民银行同期贷款基准利率调整的，出租人均有权参考调整后的中国人民银行同期贷款基准利率对租金金额进行同方向的变更，利率调整日之前各期租金和利率调整日后的第一期租金不变，之后各期租金开始按调整后的租金金额收取。出租人根据上述约定变更租金后，将向承租人发出《租金变更通知书》，承租人在此同意无条件接受本条所述租金变更并按照《租金变更通知书》向出租人支付租金。';
	}else{
		params["contract_rule.ratefloattype"]='本合同实际起租后，以后每年与起租日相同的日期为起租对应日。起租日下一年度的起租对应日与起租日相比或任何一年起租对应日与上一年度起租对应日相比，中国人民银行贷款基准利率发生变更的，出租人均有权参考变更后的中国人民银行同期贷款基准利率对租金金额进行同方向的变更。若出租人根据上述约定调整租金金额的，租金调整日之前各期租金不变，承租人应于该起租对应日支付的租金（若有）和该起租对应日之后应支付的第一期租金金额不变，以后各期租金开始按调整后的租金金额收取。出租人根据上述约定变更租金后，将向承租人发出《租金变更通知书》，承租人在此同意无条件接受本条所述租金变更并按照《租金变更通知书》向出租人支付租金。';
	}
	
	var insuremoneytype=mini.get("rawValue_insuremoneytype").getValue();
	if(insuremoneytype=='本司付款'){
		params["contract_rule.insuremoneytype"]='出租人自行购买租赁物保险';
	}else if(insuremoneytype=='客户自保'){
		params["contract_rule.insuremoneytype"]='承租人自行购买租赁物保险';
	}else if(insuremoneytype=='代收代付'){
		params["contract_rule.insuremoneytype"]='出租人代办租赁物保险';
	}else{
		params["contract_rule.insuremoneytype"]='无';
	}
	
	if(mini.get('contract_info.industrytype').getValue()=='medical'){
		params['contract_rule.owncell']='我院';
	}else{
		params['contract_rule.owncell']='我公司';
	}
	
	var cautionmoneyremain=mini.get("cautionmoneyremain").getValue().replace(regx,'');
	if(parseFloat(cautionmoneyremain)>0){
		params['contract_rule.cautionmoneyremain']=',但起租后至租赁期满时若承租人无任何违约情况或虽存在违约但已全部得以救济，则出租人应于租赁期满后无息退还承租人租赁保证金。';
		params["contract_rule.cautiondeductionmoney"]='';
	}else{
		params['contract_rule.cautionmoneyremain']='';
		params["contract_rule.cautiondeductionmoney"]='在承租人无任何违约或虽存在违约但已全部得以救济的情况下，租赁保证金将按其剩余金额倒序冲抵最后一期或多期租金的部分或全部，若剩余租赁保证金不足冲抵最后某期租金的全部，则承租人必须先行支付完毕不足部分，之后方能进行租赁保证金冲抵，承租人同意前述冲抵';
	}
	var contract_guarantee_equip=mini.get('contract_guarantee_equip').getData();
	
   if(params["templateid"].match('40288dfd53a112710153a2b23570004f')){
	   params['F-201603013']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
   }
	
   if(params["templateid"].match('40288dfd53a112710153a15efb7c0029')){
	   params['F-201603012']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
   }
   params['contractnumber.maincontract']=generateContractNo('L',0);
   
    if(params["templateid"].match('40288dfd53a639780153a725861c0054')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;
		   	  temp_arr.push(temp_obj);
			}
		}
		params['F-201603014']=mini.encode(temp_arr);
	}
	
	if(params["templateid"].match('40288dfd53a639780153a7cc09da0092')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			if('guarantee_type8'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_arr.push(temp_obj);
			}
		}
		params['F-201603015']=mini.encode(temp_arr);
	}
	
	var contract_guarantee_method=mini.get("contract_guarantee_method").getData();
	
	if(params["templateid"].match('40288dfd53a639780153a7edd4a7009a')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_method.length;i++){
			if('CUST_INFO_COMPANY'!=contract_guarantee_method[i].assurorcustclass){
			  temp_obj={};
			   temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			   temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			   count++;
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
			}
		}
		params['F-201603016']=mini.encode(temp_arr);
	}
	
	if(params["templateid"].match('40288dfd53a639780153a811be6b00b3')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_method.length;i++){
			if('CUST_INFO_COMPANY'==contract_guarantee_method[i].assurorcustclass){
			  temp_obj={};
			   temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			   temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			   count++;
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
			}
		}
		params['F-201603017']=mini.encode(temp_arr);
	}
	
	var contract_supplier = mini.get('contract_supplier').getData();
	var fund_plan=mini.get("fund_fund_plan").getData();
	if (params["templateid"].match('40288dfd53ab59430153aba739540014')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
			temp_obj = {};
			if (contract_supplier[i].vndrtype == 'vndr_type.import') {
				var totalequipprice = 0;
				temp_obj["contractnumber.contractno"] = generateContractNo('I',i);
				temp_obj["seller"] = contract_supplier[i].seller;
				var contract_equip_temp_arr = [];
				for (var j = 0; j < contract_equip.length; j++) {
					if (contract_equip[j].vndr == contract_supplier[i].seller) {
						var contract_equip_temp_obj = {};
						contract_equip_temp_obj.equipname = contract_equip[i].equipname;
						contract_equip_temp_obj.model = contract_equip[i].model;
						contract_equip_temp_obj.equipnum = contract_equip[i].equipnum;
						contract_equip_temp_obj.price = contract_equip[i].price;
						contract_equip_temp_obj.equipdeliveryplace = contract_equip[i].equipdeliveryplace;
						contract_equip_temp_obj.vndrname = contract_equip[i].vndrname;
						contract_equip_temp_obj.equipdeliverydate = contract_equip[i].equipdeliverydate;
						contract_equip_temp_obj.equipplace = contract_equip[i].equipplace;
						contract_equip_temp_arr.push(contract_equip_temp_obj);
						totalequipprice+=parseFloat(contract_equip[i].price.replace(regx,''));
					}
				}
				var collection_count=0;
				for(var k=0;k<fund_plan.length;k++){
					if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
						collection_count++;
						if(collection_count==1){
							temp_obj['equip_plan.first']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxfirst']=DX(fund_plan[k].planmoney);
						}else if(collection_count==2){
							temp_obj['equip_plan.second']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxsecond']=DX(fund_plan[k].planmoney);
						}else if(collection_count==3){
							temp_obj['equip_plan.third']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxthird']=DX(fund_plan[k].planmoney);
						}
					}
				}
		        temp_obj['contract_equip_list']=mini.encode(contract_equip_temp_arr);
		        temp_obj['contract_equip_list_info.totalequipprice']=DX(totalequipprice);
		        temp_arr.push(temp_obj);
			}
		}
		params['F-201603018']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288dfd53ab59430153acc20db70047')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
			temp_obj = {};
			if(mini.get('contract_info.industrytype').getValue()!='medical'){
			if (contract_supplier[i].vndrtype != 'vndr_type.import') {
				var totalequipprice = 0;
				temp_obj["contractnumber.contractno"] = generateContractNo('P',i);
				temp_obj["seller"] = contract_supplier[i].seller;
				var contract_equip_temp_arr = [];
				for (var j = 0; j < contract_equip.length; j++) {
					if (contract_equip[j].vndr == contract_supplier[i].seller) {
						var contract_equip_temp_obj = {};
						contract_equip_temp_obj.equipname = contract_equip[i].equipname;
						contract_equip_temp_obj.model = contract_equip[i].model;
						contract_equip_temp_obj.equipnum = contract_equip[i].equipnum;
						contract_equip_temp_obj.price = contract_equip[i].price;
						contract_equip_temp_obj.equipdeliveryplace = contract_equip[i].equipdeliveryplace;
						contract_equip_temp_obj.vndrname = contract_equip[i].vndrname;
						contract_equip_temp_obj.equipdeliverydate = contract_equip[i].equipdeliverydate;
						contract_equip_temp_obj.equipplace = contract_equip[i].equipplace;
						contract_equip_temp_arr.push(contract_equip_temp_obj);
						totalequipprice+=parseFloat(contract_equip[i].price.replace(regx,''));
					}
				}
				
				var collection_count=0;
				for(var k=0;k<fund_plan.length;k++){
					if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
						collection_count++;
						if(collection_count==1){
							temp_obj['equip_plan.first']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxfirst']=DX(fund_plan[k].planmoney);
						}else if(collection_count==2){
							temp_obj['equip_plan.second']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxsecond']=DX(fund_plan[k].planmoney);
						}else if(collection_count==3){
							temp_obj['equip_plan.third']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxthird']=DX(fund_plan[k].planmoney);
						}
					}
				}
		        temp_obj['contract_equip_list']=mini.encode(contract_equip_temp_arr);
		        temp_obj['contract_equip_list_info.totalequipprice']=DX(totalequipprice);
		        temp_arr.push(temp_obj);
			}
		}
		}
		params['F-201603014']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288dfd53ab59430153acfa480b005f')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
			temp_obj = {};
			if(mini.get('contract_info.industrytype').getValue()=='medical'){
			if (contract_supplier[i].vndrtype != 'vndr_type.import') {
				var totalequipprice = 0;
				temp_obj["contractnumber.contractno"] = generateContractNo('P',i);
				temp_obj["seller"] = contract_supplier[i].seller;
				var contract_equip_temp_arr = [];
				for (var j = 0; j < contract_equip.length; j++) {
					if (contract_equip[j].vndr == contract_supplier[i].seller) {
						var contract_equip_temp_obj = {};
						contract_equip_temp_obj.equipname = contract_equip[i].equipname;
						contract_equip_temp_obj.model = contract_equip[i].model;
						contract_equip_temp_obj.equipnum = contract_equip[i].equipnum;
						contract_equip_temp_obj.price = contract_equip[i].price;
						contract_equip_temp_obj.equipdeliveryplace = contract_equip[i].equipdeliveryplace;
						contract_equip_temp_obj.vndrname = contract_equip[i].vndrname;
						contract_equip_temp_obj.equipdeliverydate = contract_equip[i].equipdeliverydate;
						contract_equip_temp_obj.equipplace = contract_equip[i].equipplace;
						contract_equip_temp_arr.push(contract_equip_temp_obj);
						totalequipprice+=parseFloat(contract_equip[i].price.replace(regx,''));
					}
				}
				
				var collection_count=0;
				for(var k=0;k<fund_plan.length;k++){
					if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
						collection_count++;
						if(collection_count==1){
							temp_obj['equip_plan.first']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxfirst']=DX(fund_plan[k].planmoney);
						}else if(collection_count==2){
							temp_obj['equip_plan.second']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxsecond']=DX(fund_plan[k].planmoney);
						}else if(collection_count==3){
							temp_obj['equip_plan.third']=fund_plan[k].planmoney;
							temp_obj['equip_plan.dxthird']=DX(fund_plan[k].planmoney);
						}
					}
				}
		        temp_obj['contract_equip_list']=mini.encode(contract_equip_temp_arr);
		        temp_obj['contract_equip_list_info.totalequipprice']=DX(totalequipprice);
		        temp_arr.push(temp_obj);
			}
		}
		}
		params['F-201603020']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bae8afba0011')) {
		params['F-201603023']=mini.encode([{'contractnumber.contractno':generateContractNo('C',0)}]);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bb073b130019')) {
		params['F-201603024']=mini.encode([{'contractnumber.contractno':generateContractNo('C',0)}]);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bbdaebe9005f')) {
		temp_arr=[];
		for(var i=0;i<contract_guarantee_method.length;i++){
			  temp_obj={};
			  temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
		}
		params['F-201603026']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bbf28490006d')) {
		temp_arr=[];
		for(var i=0;i<contract_guarantee_method.length;i++){
			  temp_obj={};
			  temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
		}
		params['F-201603028']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bbf3c640006f')) {
		temp_arr=[];
		for(var i=0;i<contract_guarantee_method.length;i++){
			  temp_obj={};
			  temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
		}
		params['F-201603030']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bc42af720096')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
			   temp_obj = {};
			   var no=0;
			   var totalequipprice=0;
			   var contract_equip_temp_arr = [];
				for (var j = 0; j < contract_equip.length; j++) {
					temp_obj["seller"] = contract_supplier[i].seller;
					if (contract_equip[j].vndr == contract_supplier[i].seller) {
						var contract_equip_temp_obj = {};
						no++;
						contract_equip_temp_obj.no=no;
						contract_equip_temp_obj.equipname = contract_equip[i].equipname;
						contract_equip_temp_obj.model = contract_equip[i].model;
						contract_equip_temp_obj.equipnum = contract_equip[i].equipnum;
						contract_equip_temp_obj.manufacturername = contract_equip[i].manufacturername;
						contract_equip_temp_obj.equipid = contract_equip[i].equipid;
						contract_equip_temp_arr.push(contract_equip_temp_obj);
						totalequipprice+=parseFloat(contract_equip[i].price.replace(regx,''));
					}
				 }
			      temp_obj['contract_equip_list']=mini.encode(contract_equip_temp_arr);
			      temp_obj['contract_equip_list_info.totalequipprice']=DX(totalequipprice);
			      temp_arr.push(temp_obj);
		}
		params['F-201603032']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bc67e3cb00a3')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
			var equipamt_count=0;
				for(var k=0;k<fund_plan.length;k++){
					if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
						temp_obj = {};
						equipamt_count++;
						temp_obj["seller"] = contract_supplier[i].seller;
						collection_count++;
						temp_obj['equip_amt.planmoney']=fund_plan[k].planmoney;
						temp_obj['equip_amt.term']='请贵司待购买合同中第'+equipamt_count+'笔购买款 ';
						temp_arr.push(temp_obj);
					}
				}
		}
		params['F-201603034']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bc8d7b1300b5')) {
		params['lastequipamt.lastequipamt']=decimal(parseFloat(params["contract_condition.equipamt"])-parseFloat(params["contract_condition.cautionmoney"])-parseFloat(params["contract_condition.handlingchargemoney"]),2);
	}
	
	if (params["templateid"].match('40288d0653bad5490153bcd4b72300d9')) {
		temp_arr = [];
		for (var i = 0; i < contract_supplier.length; i++) {
				for(var k=0;k<fund_plan.length;k++){
					if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
						temp_obj = {};
						temp_obj["seller"] = contract_supplier[i].seller;
						temp_obj['first_equip.planmoney']=fund_plan[k].planmoney;
						temp_arr.push(temp_obj);
						break;
					}
				}
		}
		params['F-201603037']=mini.encode(temp_arr);
	}
	
	if (params["templateid"].match('40288ded53cb135f0153cb32fd1d000f')) {
		temp_arr = [];
		for(var i=0;i<contract_supplier.length;i++){
			temp_obj={};
			temp_obj['seller_info.sellername']=contract_supplier[i].sellername;
			temp_obj['contractnumber.contractno']=generateContractNo('F',i);
			temp_obj['contractnumber.advno']=generateContractNo('C',i);
			for(var k=0;k<fund_plan.length;k++){
				var pay_count=0;
				if(fund_plan[k].feetypename=='设备款'&&fund_plan[k].paycust==contract_supplier[i].seller){
					pay_count++;
					if(pay_count==1){
						temp_obj['pay_condition.firstpay']=fund_plan[k].planmoney;
						temp_obj['pay_condition.dxfirstpay']=DX(fund_plan[k].planmoney);
					}else if(pay_count==2){
						temp_obj['pay_condition.secondpay']=fund_plan[k].planmoney;
						temp_obj['pay_condition.dxsecondpay']=DX(fund_plan[k].planmoney);
					}
				}
			}
			temp_arr.push(temp_obj);
		}
	  params['F-201603046']=mini.encode(temp_arr);
	}
	if (params["templateid"].match('40288ded53cb135f0153cc05bd00002e')) {
		params['F-201603047']=mini.encode([{'contractnumber.contractno':generateContractNo('F',0)}]);
	}
	//console.info(params);
	
}
function generateContractNo(mark,count){
	var level1="";
	count++;
	var level2=mark;
	var contract_number=mini.getbyName('contract_info.contractid').getValue().replace('P','');
	var level3=contract_number.split('-')[0];
	if(mini.getbyName('contract_info.businesstype').getValue()=='business_type.factoring'){
		level1='F';
	}else{
		var industrytype=mini.get("contract_info.industrytype").getValue();
		if(industrytype=='medical'){
			level1='H';
		}else if(industrytype=='Pharma'){
			level1='P';
		}else{
			level1='S';
		}
	}
	return level1+level2+level3+"-"+count;
}


</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">合同文本类型：</td>
		<td class="td-content" width="38%" style="text-indent: 0px;" colspan="2">
			<input name="twoclassify" id="twoclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'twoclassify','threeclassify')"
				text="${requestScope['rawValue_twoclassify']}"
				value="${requestScope['twoclassify']}">
			<input id="rawValue_twoclassify" name="rawValue_twoclassify" value="${requestScope['rawValue_twoclassify']}" class="mini-textbox" style="display:none"/>
		</td>
		<td class="td-content-title"  style="text-indent: 0px;">
			<a id="create_contract_word" class="mini-button" iconCls="icon-new" onclick="createContractWord()" style="color: red;font-weight: bold;">相关合同</a>
		</td>
<%-- 		<td class="td-content-title" width="12%">二级分类：</td>
		<td class="td-content" width="38%" style="text-indent: 0px;">
			<input name="threeclassify" id="threeclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'threeclassify','fourclassify')"
				text="${requestScope['rawValue_threeclassify']}"
				value="${requestScope['threeclassify']}">
			<input id="rawValue_threeclassify" name="rawValue_threeclassify" value="${requestScope['rawValue_threeclassify']}" class="mini-textbox" style="display:none"/>
		</td> --%>
	</tr>
	<%-- <tr class="tr-even">
		<td class="td-content-title">三级分类：</td>
		<td class="td-content" style="text-indent: 0px;">
			<input name="fourclassify" id="fourclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'fourclassify','fiveclassify')"
				text="${requestScope['rawValue_fourclassify']}"
				value="${requestScope['fourclassify']}">
			<input id="rawValue_fourclassify" name="rawValue_fourclassify" value="${requestScope['rawValue_fourclassify']}" class="mini-textbox" style="display:none"/>
		</td>
		<td class="td-content-title">四级分类：</td>
		<td class="td-content" style="text-indent: 0px;">
			<input name="fiveclassify" id="fiveclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'fiveclassify','sixclassify')"
				text="${requestScope['rawValue_fiveclassify']}"
				value="${requestScope['fiveclassify']}">
			<input id="rawValue_fiveclassify" name="rawValue_fiveclassify" value="${requestScope['rawValue_fiveclassify']}" class="mini-textbox" style="display:none"/>
		</td>
	</tr> --%>
<%-- 	<tr class="tr-odd">
		<td class="td-content-title">五级分类：</td>
		<td class="td-content" style="text-indent: 0px;">
			<input name="sixclassify" id="sixclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'sixclassify','')"
				text="${requestScope['rawValue_sixclassify']}"
				value="${requestScope['sixclassify']}">
			<input id="rawValue_sixclassify" name="rawValue_sixclassify" value="${requestScope['rawValue_sixclassify']}" class="mini-textbox" style="display:none"/>
		</td>
		<td class="td-content-title" colspan="2" style="text-indent: 0px;">
			<a id="create_contract_word" class="mini-button" iconCls="icon-new" onclick="createContractWord()" style="color: red;font-weight: bold;">相关合同</a>
		</td>
	</tr> --%>
</table>
<table class="fillTable" cellspacing="0" cellpadding="0">
	<tr class="tr-even" id="id_word_tips">
		<td class="td-content-title">合同模板：</td>
		<td class="td-content" colspan="3"><div style="overflow:hidden;width:100%;" id="contract_word_check_list">请先选择!</div></td>
	</tr>
	<tr class="tr-contractect-word-list">
		<td class="td-content" colspan="4">
			<jsp:include page="contract_list_info.jsp"></jsp:include><%--合同清单 --%>
		</td>
	</tr>
</table>
<script language="javascript">
jQuery(function(){chargeTemplate()});
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		$("#id_table_generate_contract").hide();
		$("#id_word_tips").hide();
	};
})
</script>