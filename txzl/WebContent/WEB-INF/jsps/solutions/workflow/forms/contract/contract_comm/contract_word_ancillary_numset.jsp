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
				/* validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
					var answer = '';
					var ans = '';
					var oldConNum=document.getElementById('contract_change_numset_editFormPopupWindow_form_oldcontnum$text').value;
					var nowConNum=document.getElementById('contract_change_numset_editFormPopupWindow_form_contractnumber$text').value;
					if (editFormItemOperFlag == 'add') {
						var ans = sendAjax(nowConNum,answer);
					   if(ans=='0'){
						   mini.alert("合同编号重复！");
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
				}, */
				showToolbar: showTools,
				id: "contract_change_numset",
				renderTo: "id_contract_change_numset",
				width: '100%',
				height: '100%',
				height: 500,
				showPager : false,
				lazyLoad : false,
				title:'合同编号设置',
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.proj.ContractNumberSettingTmp",
				params:{					
					projid:projid,
					docid:flowUnid
				},
				multiSelect: false,
				xmlFileName : '/eleasing/workflow/proj/proj_contract/contract_number_setting_tmp.xml',
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
					{
						field:'contractid',
						header:'合同id',
						visible: false,
						formEditorConfig:{
						     value:contractid,
						     fieldVisible:false
						         }
					},
					{field:'contractname',
						header:'合同名称',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
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
							newLine:true,
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
		   			                 type:'queryinput',
					             required:false,
					          multiSelect:false, 
					            textField:'name',
					           valueField:'value',
					           showNullItem:"true",
					           nullItemText:"",
					           onSelect : setRegistcode,
					         fieldVisible:true,
					             params:{pid:'CONTRACT_TYPE',xmlFileName:'combos/comboDict.xml'}}},
		             {
							field:'custidname',
							visible:false,
							header:'客户信息',
			   		         formEditorConfig:{
						         fieldVisible:false,
						             }
						},  
					{field:'custid',
						header:'客户信息',
						visible: false,
						formEditorConfig:{
							 newLine:true,
							onSelect : setContractSubject,
							type:'queryinput',
							fieldVisible: true,
							 textField:'name',
					          valueField:'value',
							params:{cust_id:'xxx',
									docidfilter:'xxx',
									projid:'xxx',
									docid:'xxx',
									projid1:'xxx',
									docid1:'xxx',
									projid2:'xxx',
									contractid:'xxx',
									docid2:'xxx',
									limited:'xxx',
								    xmlFileName:'/eleasing/workflow/proj/proj_contract/custinfo_judge_change_set_number.xml'}
						}
					},
					{
						field : 'contractmoney',
						header : '采购总额',
						headerAlign : 'center',
						dataType:"currency",
						formEditorConfig : {
							type : 'text',
							readOnly:true
						}
					},
					{
						field : 'mark',
						header : '备注',
						headerAlign : 'center',
						formEditorConfig : {
							newLine : true,
							width : 495,
							colspan : 6,
							height : 55,
							type : 'textarea'
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
	  var conType=document.getElementById('contract_change_numset_editFormPopupWindow_form_contracttype$text').value;
	  /* if(conType==''||inputValueCon==undefined){
		  alert(conType);
		  return;
	  } */
	  var inputValueCon=document.getElementById('contract_change_numset_editFormPopupWindow_form_contractsubject$text').value;
	  var inputValueCust=document.getElementById('contract_change_numset_editFormPopupWindow_form_custid$text').value;
	  if(inputValueCon==''||inputValueCon==undefined){
		mini.getbyName("contractsubject").setValue(inputValueCust);
		
	  }
}


//选择合同类型后自动带出客户信息
function setRegistcode($me,queryInput,rowData) {	
	mini.get("contract_change_numset_editFormPopupWindow_form_contractmoney").disable(true);
	var cust_id = "${requestScope['contract_info.custid']}";
	var rowDatas = queryInput.value;               
   var clientBankQueryInput = getMiniuiExtObject("contract_change_numset_editFormPopupWindow_form_custid");
   var conidparams=clientBankQueryInput.params;
   //租赁合同
   if("CONTRACT_TYPE.01" == rowDatas){
	   conidparams.cust_id = cust_id;
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
	   conidparams.contractid=contractid;
	    conidparams.projid='xxx';
	   	conidparams.docid='xxx';
	    conidparams.projid1='xxx';
	   	conidparams.docid1='xxx';
	   	conidparams.projid2='xxx';
	   	conidparams.docid2='xxx';
	   	conidparams.limited='xxx';
	   	mini.get("contract_change_numset_editFormPopupWindow_form_contractmoney").enable(true);
  }else  if("CONTRACT_TYPE.02" == rowDatas||"CONTRACT_TYPE.08" == rowDatas){ //采购合同，权利义务转让协议，供应商    
	  conidparams.docidfilter1 =flowUnid;
	  conidparams.docidfilter2 = rowDatas;
	  conidparams.contractid=contractid;
	   	conidparams.projid =  projid;
	   	conidparams.docid  =  flowUnid;
	   	conidparams.projid1='xxx';
	   	conidparams.docid1='xxx';
	   	conidparams.projid2='xxx';
	   	conidparams.docid2='xxx';
	   	conidparams.cust_id='xxx';
	   	conidparams.limited='xxx';
	   	mini.get("contract_change_numset_editFormPopupWindow_form_contractmoney").enable(true);
	   	
   }else if("CONTRACT_TYPE.03" == rowDatas){ //担保合同，担保人
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
	   conidparams.contractid=contractid;
       conidparams.projid2 =  projid;
   	conidparams.docid2  = flowUnid;
   	conidparams.projid='xxx';
   	conidparams.docid='xxx';
   	conidparams.projid1='xxx';
   	conidparams.docid1='xxx';
   	conidparams.cust_id='xxx';
   	conidparams.limited='xxx';
   }else if("CONTRACT_TYPE.04" == rowDatas){//抵押合同，抵押人
	   conidparams.docidfilter1 = flowUnid;
	   conidparams.docidfilter2 = rowDatas;
	   conidparams.contractid=contractid;
   	conidparams.projid1 = projid;
   	conidparams.docid1  = flowUnid;
    conidparams.projid='xxx';
   	conidparams.docid='xxx';
   	conidparams.projid2='xxx';
   	conidparams.docid2='xxx';
   	conidparams.cust_id='xxx';
   	conidparams.limited='xxx';
   }else if("CONTRACT_TYPE.05" == rowDatas||
		   "CONTRACT_TYPE.06" == rowDatas||
		   "CONTRACT_TYPE.07" == rowDatas||
		   "CONTRACT_TYPE.09" == rowDatas||
		   "CONTRACT_TYPE.10" == rowDatas||
		   "CONTRACT_TYPE.11" == rowDatas
   ){//其他，所有客户
	   conidparams.docidfilter1 =flowUnid;
	   conidparams.docidfilter2 = rowDatas;
   	 conidparams.projid='xxx';
   	 conidparams.docid='xxx';
   	 conidparams.projid1='xxx';
   	 conidparams.docid1='xxx';
   	 conidparams.projid2='xxx';
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
function  getContractNumInfo(){
	 var  row=null;
	row=mini.get("contract_change_numset").getSelected();
	return row; 
}

function createContractWord() {
	
	var tempids = getTempateIdInfo();
	if ("" == tempids){
		alert("请选择合同模板");return;
	}
	//获取每个合同的合同编号
	var contarctRow=getContractNumInfo();
	if (null == contarctRow){
		alert("请选择合同编号设置选项");return;
	}	
	var params = {};
	params["flowunid"] = flowUnid;
	params["filekey"] =mini.get("contract_id").getValue();
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
		//returntype : 'listtonewpage',
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
	var  custname=mini.get("cust_id").getText();
	params["contract_info.custname"]=custname;
	
	params['contract_info.leasformname']=mini.get("contract_info.leasform").getValue();
	if(mini.getbyName("contract_info.leasform").getValue() == "售后回租"){
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
	
	var equipplaces="";
	for(var i=0;i<contract_equip.length;i++){
		equipplaces+=contract_equip[i].equipplace;
		if(i<contract_equip.length-1){
			equipplaces+="和";
		}
	}
	params["contract_equip.equipplace"]=equipplaces;
	
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
		temp_rent_plan["rent"]="人民币"+getThousands(rent_plan_grid[i].rent)+"元";
		totalrent+=parseFloat(rent_plan_grid[i].rent.replace(regx,''));
		rent_plan_cn.push(temp_rent_plan);
	}
	params["rent_plan"]=mini.encode(rent_plan_cn);
	
	//租金支付表
	var rent_plan_reciv=[];
	for(var i=0;i<rent_plan_grid.length;i++){
		var temp_rent_plan={};
		temp_rent_plan["rentlist"]="第"+rent_plan_grid[i].rentlist+"期";
		temp_rent_plan["plandate"]=rent_plan_grid[i].plandate;
		temp_rent_plan["rent"]="人民币"+rent_plan_grid[i].rent+"元";
		temp_rent_plan["corpus"]=rent_plan_grid[i].corpus;
		temp_rent_plan["interest"]=rent_plan_grid[i].interest;
		rent_plan_reciv.push(temp_rent_plan);
	}	
	//params["rent_plan_tables"]=JsonUtil.encode(rent_plan_reciv);
	params["rent_plan_tables"]=mini.encode(rent_plan_reciv);
	params["rent_plan_tables1"]=mini.encode(rent_plan_reciv);
	
	
	//资金计划表
	var fund_plan=mini.get("fund_fund_plan").getData();
	var poundage=0;
	var cash=0;
	var fund_plan_pr=[];
	var fund_plan_cr=[];
	for(var i=0;i<fund_plan.length;i++){
		var fund_plan_poundage={};
		var fund_plan_cash={};
		if(fund_plan[i].feetypename=="手续费"){
			poundage=Number(poundage)+Number(fund_plan[i].planmoney);
			fund_plan_poundage=fund_plan[i];
			fund_plan_pr.push(fund_plan_poundage);
		}
		if(fund_plan[i].feetypename=="保证金"){
			cash=Number(cash)+Number(fund_plan[i].planmoney);
			fund_plan_cash=fund_plan[i];
			fund_plan_cr.push(fund_plan_cash);
		}	
	}
	params["fund_plan_pr"]=mini.encode(fund_plan_pr);
	params["fund_plan_cr"]=mini.encode(fund_plan_cr);	
	params["contract_fund.poundage"]=poundage;
	params["contract_fund.dxpoundage"]=DX(poundage);
	params["contract_fund.cash"]=cash;
	params["contract_fund.dxcash"]=DX(cash);
	params["contract_fund.equipamt"]=mini.get("equipamt").getValue().replace(regx,'');
	params["contract_fund.leaseterm"]=mini.get("leaseterm").getValue();
	
	//商务条件
	params["contract_condition.equipamt"]=mini.get("equipamt").getValue().replace(regx,'');
	params["contract_condition.dxequipamt"]=DX(mini.get("equipamt").getValue().replace(regx,''));
	params["contract_condition.cautionmoney"]=mini.get("cautionmoney").getValue().replace(regx,'');
	params["contract_condition.dxcautionmoney"]=DX(params["contract_condition.cautionmoney"]);
	params["contract_condition.cleanleasemoney"]=mini.get("cleanleasemoney").getValue().replace(regx,'');
	params["contract_condition.dxcleanleasemoney"]=DX(params["contract_condition.cleanleasemoney"]);
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
	params["contract_condition.startdate"]=formatDate(mini.get("startdate").getValue());
	
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
	//抵押物信息
	var contract_guarantee_equip=mini.get('contract_guarantee_equip').getData();
	var guaranum=1;
	for(var i=0;i<contract_guarantee_equip.length;i++){
		if('guarantee_type7'==contract_guarantee_equip[i].equipguaranteetype){
			guaranum++;
	   //	  params['guaranum'+guaranum]=contract_guarantee_equip[i].guarantor;		   	

		}
	}
	if(contract_guarantee_equip.length>0){
		params['guaranum1']=contract_guarantee_equip[0].guarantor;
	}
	
	
	//获得合同模板的值
	var contractnum=document.getElementById("contract_word_check_list").outerText;
	var rr = $("input[name=contract_word_list_check_box]");
	var text ="";
	 $("input[name=contract_word_list_check_box]").each(function() {  
         if ($(this).is(':checked')) {  
            text += $(this).val()+",";  
         }  
     });  
	var contractarray=getContractNum(text);
	//获取每个合同的合同编号
	var contarctRow=getContractNumInfo();
	var contractNum=contarctRow.contractnumber;
	var contractName=contarctRow.contractname
	params['contract_setnum.contractnumber']=contractNum;	//合同编号
	params['contract_setnum.contractname']=contractName;	//合同名称
	params['foot.contractnumber']=contractNum;//页眉带值的设置
	params["projid"]=projid;	
	//获得起租日日期
	var  startdateformat=formatDate(mini.get("startdate").getValue());
	params["contract_condition.startdateyy"]=startdateformat.substring(0,4);
	params["contract_condition.startdatemm"]=startdateformat.substring(5,7);
	params["contract_condition.startdatedd"]=startdateformat.substring(8,10);
	//本项目建成日---起租日前一天
	var  projenddate=formatDate(new Date(getProjEndDate(startdateformat)));
	params["projenddate.enddateyy"]=projenddate.substring(0,4);
	params["projenddate.enddatemm"]=projenddate.substring(5,7);
	params["projenddate.enddatedd"]=projenddate.substring(8,10);
	
	//计算宽限期时间---宽限期一般是按期次数
	var  gracenum=mini.get("grace").getValue();//parseInt
	var  gracedate=addmulMonth(startdateformat,gracenum);
	params["gracedate.gracedateyy"]=gracedate.substring(0,4);
	params["gracedate.gracedatemm"]=gracedate.substring(5,7);
	params["gracedate.gracedatedd"]=gracedate.substring(8,10);
	
	//起租日加上租赁期限后的日期
	var  num=mini.get("leaseterm").getValue();//租赁期次
	var enddate=addmulMonth(startdateformat,Number(num)+Number(gracenum));//租赁期需要加上宽限期
	params["contract_condition.enddateyy"]=enddate.substring(0,4);
	params["contract_condition.enddatemm"]=enddate.substring(5,7);
	params["contract_condition.enddatedd"]=enddate.substring(8,10);
	
	//期限
	params["contract_condition.startdateformat"]=startdateformat;
	params["contract_condition.enddate"]=enddate;
	
	params["obligation.startdateyy"]=startdateformat.substring(0,4);
	params["obligation.startdatemm"]=startdateformat.substring(5,7);
	params["obligation.startdatedd"]=startdateformat.substring(8,10);
	params["obligation.leaseterm"]=params["contract_condition.leaseterm"];
	params["obligation.dxequipamt"]=params["contract_condition.dxequipamt"];
	params["obligation.equipamt"]=params["contract_condition.equipamt"];
	//获得客户ID
	var custid = "${requestScope['contract_info.custid']}";
	params["custid"]=custid;
	//签约方
	var signatory_seller=mini.get('contract_supplier').getData();
	//计算还租频率
	 var renthz=mini.get("rawValue_incomenumberyear").getValue().replace(/\s/g,'');
	if(renthz!=null){
		if(renthz=="月付"){
			params['receiverent.renthz']="每月";
		}
		if(renthz=="季付"){
			params['receiverent.renthz']="每季";
		}
		if(renthz=="半年付"){
			params['receiverent.renthz']="每半年";
		}
		if(renthz=="年付"){
			params['receiverent.renthz']="每年";
		}
	}
	//租金计划表
	params["rent_plan_table.equipamt"]=mini.get("equipamt").getValue().replace(regx,'');//融资额
	params["rent_plan_table.leaseterm"]=mini.get("leaseterm").getValue();//租赁期
	params["rent_plan_table.startdate"]=startdateformat;//起租日
	params["rent_plan_table.rentnum"]=getRentReceiveNum(Number(num)+Number(gracenum),renthz);//年还款次数
	params["rent_plan_table.cash"]=cash;

	//资金余额表
	var  rentplan_start=formatDate(mini.get("startdate").getValue());//起租日期
	var  rent_plan_over=[];
	for(var i=0;i<rent_plan_grid.length;i++){
		var qizuDate=new Date(getDateNum(rentplan_start,80));
		var firstDate=new Date(rent_plan_grid[0].plandate);
		if(qizuDate>firstDate){
			break;
		}
		var rentArr=[];
		if(i==0){			
			var startdate=rentplan_start;
			var plandate=rent_plan_grid[0].plandate;
			var rent=rent_plan_grid[0].rent;
			rentArr=getRentOverArr(startdate,plandate,rent);
			for(var j=0;j<rentArr.length;j++){
				var rentObj={};
				rentObj["rentlist"]="第"+rent_plan_grid[i].rentlist+"期";
				rentObj["startdate"]=rentArr[j].startdate;
				rentObj["enddate"]=rentArr[j].enddate;
				rentObj["rent"]=rentArr[j].rent;
				rent_plan_over.push(rentObj);
			}
		}else{
			var startdate=getDateNum(rent_plan_grid[i-1].plandate,1);
			var plandate=rent_plan_grid[i].plandate;
			var rent=rent_plan_grid[i].rent;
			rentArr=getRentOverArr(startdate,plandate,rent);
			for(var j=0;j<rentArr.length;j++){
				var rentObj={};
				rentObj["rentlist"]="第"+rent_plan_grid[i].rentlist+"期";
				rentObj["startdate"]=rentArr[j].startdate;
				rentObj["enddate"]=rentArr[j].enddate;
				rentObj["rent"]=rentArr[j].rent;
				rent_plan_over.push(rentObj);
			}
		}
		
	}
	params["rent_plan_over"]=mini.encode(rent_plan_over);
	var  nowDate=formatDate(new Date());
	params['rent_over.contractnumber']=contractNum+nowDate;	//资金余额编号
	params["rent_over.dateyy"]=nowDate.substring(0,4);
	params["rent_over.datemm"]=nowDate.substring(5,7);
	params["rent_over.datedd"]=nowDate.substring(8,10);
	
	
    //动产抵质押登记书
    if(params["templateid"].match('4028b8815b88f367015b892326bd0036')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;
			  temp_obj['guarantee_equip.dxpresentvalue']= DX(contract_guarantee_equip[i].presentvalue.replace(regx,''));
			  temp_obj['guarantee_equip.presentvalue']=contract_guarantee_equip[i].presentvalue.replace(regx,'');
			  temp_obj['guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;			
		}else{
			params['F-201704038']=mini.encode(temp_arr);
		}
	}
	
    //动产抵质押登记变更书
    if(params["templateid"].match('4028b8815b88f367015b894955a80058')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;
			  temp_obj['guarantee_equip.guarantorname']=contract_guarantee_equip[i].guarantorname;
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;			
		}else{
			params['F-201704039']=mini.encode(temp_arr);
		}
	}
    //动产抵质押登记注销书
    if(params["templateid"].match('4028b8815b895a1a015b896039ba0014')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;
			  temp_obj['guarantee_equip.guarantorname']=contract_guarantee_equip[i].guarantorname;
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;			
		}else{
			params['F-201704040']=mini.encode(temp_arr);
		}
	}
    
    //权利义务转让协议（设备类）
    if(params["templateid"].match('4028b8815b7ea30e015b7fa0465800af')){
    	temp_arr=[];
		var count=0;
		for(var i=0;i<signatory_seller.length;i++){
			if(signatory_seller[i].istotalcontractor=="否"){			
		   	  temp_obj={};
		   	  temp_obj['seller']=signatory_seller[i].seller;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['signatory_seller.sellername']=signatory_seller[i].sellername;
		   	temp_obj['signatory_seller.selleraccname']=signatory_seller[i].selleraccname;
		   	temp_obj['signatory_seller.selleraccbank']=signatory_seller[i].selleraccbank;
		   	temp_obj['signatory_seller.selleraccnumber']=signatory_seller[i].selleraccnumber;
		   	  temp_arr.push(temp_obj);
			}  
		}
		if(temp_arr.length==0){
			mini.alert("请填写卖方信息！");
			return;
			
		}else{
			params['F-201704033']=mini.encode(temp_arr);
		}
	}
    //权利义务转让协议（总包类）
    if(params["templateid"].match('4028b8815b83d0d7015b844071c90041')){
    	temp_arr=[];
		var count=0;
		for(var i=0;i<signatory_seller.length;i++){
			if(signatory_seller[i].istotalcontractor=="是"){	
			   	  temp_obj={};
			   	  temp_obj['seller']=signatory_seller[i].seller;		   	
			      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			      count++;
			   	  temp_obj['signatory_seller.sellername']=signatory_seller[i].sellername;
			   	temp_obj['signatory_seller.selleraccname']=signatory_seller[i].selleraccname;
			   	temp_obj['signatory_seller.selleraccbank']=signatory_seller[i].selleraccbank;
			   	temp_obj['signatory_seller.selleraccnumber']=signatory_seller[i].selleraccnumber;
			   	  temp_arr.push(temp_obj);
			}
		}
		if(temp_arr.length==0){
			mini.alert("请填写卖方信息！");
			return;			
		}else{
			params['F-201704035']=mini.encode(temp_arr);
		}
	}
	
    //权利义务转让协议（总包下设备类）
    if(params["templateid"].match('4028b8815b83d0d7015b856b63440133')){
    	temp_arr=[];
		var count=0;
		for(var i=0;i<signatory_seller.length;i++){
			if(signatory_seller[i].istotalcontractor=="是"){	
			   	  temp_obj={};
			   	  temp_obj['seller']=signatory_seller[i].seller;		   	
			      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			      count++;
			   	  temp_obj['signatory_seller.sellername']=signatory_seller[i].sellername;
			   	temp_obj['signatory_seller.selleraccname']=signatory_seller[i].selleraccname;
			   	temp_obj['signatory_seller.selleraccbank']=signatory_seller[i].selleraccbank;
			   	temp_obj['signatory_seller.selleraccnumber']=signatory_seller[i].selleraccnumber;
			   	  temp_arr.push(temp_obj);
			}
		}
		if(temp_arr.length==0){
			mini.alert("请填写卖方信息！");
			return;			
		}else{
			params['F-201704036']=mini.encode(temp_arr);
		}
	}
    
   if(params["templateid"].match('40288dfd53a112710153a2b23570004f')){
	   params['F-201603013']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
   }
	
   if(params["templateid"].match('40288dfd53a112710153a15efb7c0029')){
	   params['F-201603012']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
   }
   params['contractnumber.maincontract']=generateContractNo('L',0);
   
    if(params["templateid"].match('4028b8815b151da6015b15211e3d0006')){
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
    

    //抵押合同---房产抵押合同
    if(params["templateid"].match('4028b8815b1392c3015b13b8937d0011')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;			
		}else{
			params['F-201703012']=mini.encode(temp_arr);
		}
	}
    
    //抵质押承诺函(直租)
    if(params["templateid"].match('4028b8815b557317015b568127ed0036')){
		params['F-201704015']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
	}
    params["pledge_other.custname"]=custname;
    params["pledge_other.projectname"]= contract_base_info_data["projectname"];
    
    //抵质押承诺函(其他)
    if(params["templateid"].match('4028b8815b664410015b66f8d6f5014c')){
		params['F-201704019']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
	}
    //抵押合同（回租）
    if(params["templateid"].match('4028b8815b557317015b56fe3d24006e')){
    	temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;//原值
		   	 // temp_obj['contract_guarantee_equip.presentvalue']=contract_guarantee_equip[i].presentvalue;//现值
		  	 temp_obj['guarantee_equip.dxpresentvalue']= DX(contract_guarantee_equip[i].presentvalue.replace(regx,''));
		   	temp_obj['guarantee_equip.presentvalue']=contract_guarantee_equip[i].presentvalue.replace(regx,'');
		   	
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;
			
		}else{
			params['F-201704016']=mini.encode(temp_arr);
		}
    	//params['F-201704016']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
	}
    //机器设备抵押合同
    if(params["templateid"].match('4028b8815b6a0b43015b6b242fa00111')){
    	temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_equip.length;i++){
			//if('guarantee_type1'==contract_guarantee_equip[i].equipguaranteetype||'guarantee_type3'==contract_guarantee_equip[i].equipguaranteetype){
		   	  temp_obj={};
		   	  temp_obj['guarantor']=contract_guarantee_equip[i].guarantor;		   	
		      temp_obj['contractnumber.contractno']=generateContractNo('G',count);
		      temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
		      count++;
		   	  temp_obj['contract_guarantee_equip.equipname']=contract_guarantee_equip[i].equipname;
		   	  temp_obj['contract_guarantee_equip.guarantor']=contract_guarantee_equip[i].guarantor;
		   	  temp_obj['contract_guarantee_equip.guarantyvalue']=contract_guarantee_equip[i].guarantyvalue;//原值
		   	 // temp_obj['contract_guarantee_equip.presentvalue']=contract_guarantee_equip[i].presentvalue;//现值
		  	 temp_obj['guarantee_equip.dxpresentvalue']= DX(contract_guarantee_equip[i].presentvalue.replace(regx,''));
		   	temp_obj['guarantee_equip.presentvalue']=contract_guarantee_equip[i].presentvalue.replace(regx,'');
		   	
		   	  temp_arr.push(temp_obj);
			//}
		}
		if(temp_arr.length==0){
			mini.alert("请填写抵押物信息！");
			return;
			
		}else{
			params['F-201704025']=mini.encode(temp_arr);
		}
    	//params['F-201704016']=mini.encode([{'contractnumber.contractno':generateContractNo('L',0)}]);
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
	//法人回租  担保函模板
	if(params["templateid"].match('8a89808d5b6a0ee4015b6a6dc107003f')){
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
			params['F-201704022']=mini.encode(temp_arr);
		
	}
	//自然人回租  担保函模板
	if(params["templateid"].match('8a89808d5b6a0ee4015b6a842bd3004a')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_method.length;i++){
			if('CUST_INFO_PERSON'==contract_guarantee_method[i].assurorcustclass){
			  temp_obj={};
			   temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			   temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			   count++;
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
			}
		}
			params['F-201704023']=mini.encode(temp_arr);
	}
	
	//法人直租   担保函模板
	if(params["templateid"].match('8a89808b5b64ef84015b66987e880083')){
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
			params['F-201704018']=mini.encode(temp_arr);
		
	}
	//自然人直租  担保函模板
	if(params["templateid"].match('8a89808d5b6a0ee4015b6a3416f3000d')){
		temp_arr=[];
		var count=0;
		for(var i=0;i<contract_guarantee_method.length;i++){
			if('CUST_INFO_PERSON'==contract_guarantee_method[i].assurorcustclass){
			  temp_obj={};
			   temp_obj['contractnumber.contractno']=generateContractNo('G',count);
			   temp_obj['contractnumber.maincontract']=generateContractNo('L',0);
			   count++;
		   	  temp_obj['assuror']=contract_guarantee_method[i].assuror;
		   	  temp_arr.push(temp_obj);
			}
		}
			params['F-201704020']=mini.encode(temp_arr);
	}
	
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
//获得日期相隔几天
function getDateNum(str,daynum){
	//var str = "2010-08-01";
	// 转换日期格式
	str = str.replace(/-/g, '/'); // "2010/08/01";
	// 创建日期对象
	var date = new Date(str);
	// 加上一个整数，可为正，可为负数
	date.setDate(date.getDate()+daynum);
	// 没有格式化的功能，只能一个一个取
	str = date.getFullYear() + '-'
	    // 因为js里month从0开始，所以要加1
	    + (parseInt(date.getMonth()) + 1) + '-'
	    + date.getDate();
	return str;
}
//获取一个数组，包括起止时间，结束时间，余额，参数是起止时间和截止时间，以及租金
function getRentOverArr(startdate,plandate,rent){
	var rentArr=[];	
		var rentObj1={};
		var rentObj2={};
		var rentObj3={};		
		rentObj3["startdate"]=getDateNum(plandate,-15);
		rentObj3["enddate"]=plandate;
		rentObj3["rent"]=Number(rent).toFixed(2);
		rentObj2["startdate"]=getDateNum(plandate,-45);
		rentObj2["enddate"]=getDateNum(plandate,-16);
		rentObj2["rent"]=(Number(rent)/2).toFixed(2);
		rentObj1["startdate"]=startdate;
		rentObj1["enddate"]=getDateNum(plandate,-46);
		rentObj1["rent"]=0;
		rentArr.push(rentObj1)
		rentArr.push(rentObj2);
		rentArr.push(rentObj3);
	return rentArr;
}
function getThousands(num) {	
    return (Number(num).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
}
function getContractNum(contractnum){
	var contractarray=[];
	contractarray=contractnum.split(',');
	return contractarray;
}
var formatDate = function (date) {  
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
	//获得日期前一天
function getProjEndDate(str){
	//var str = "2010-08-01";
	// 转换日期格式
	str = str.replace(/-/g, '/'); // "2010/08/01";
	// 创建日期对象
	var date = new Date(str);
	// 减去一天
	date.setDate(date.getDate() - 1);
	// 没有格式化的功能，只能一个一个取
	str = date.getFullYear() + '-'
	    // 因为js里month从0开始，所以要加1
	    + (parseInt(date.getMonth()) + 1) + '-'
	    + date.getDate();
	return str;
}	
function getRentReceiveNum(lerstem,renthz){
	if(renthz=="月付"){
		return lerstem<12 ?  lerstem:12;
	}
	if(renthz=="季付"){
		return lerstem<12 ?  Math.ceil(lerstem/3):4;
	}
	if(renthz=="半年付"){
		return lerstem<12 ?  Math.ceil(lerstem/6):2;
	}
	if(renthz=="年付"){
		return 1;
	}
	
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
<div id="id_contract_change_numset" style="width: 100%;height: 100%;"></div>
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">合同文本类型：</td>
		<td class="td-content" width="38%" style="text-indent: 0px;" colspan="2">
			<input name="twoclassify" id="twoclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="false"
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