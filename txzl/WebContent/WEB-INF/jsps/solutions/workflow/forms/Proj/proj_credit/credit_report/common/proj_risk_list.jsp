<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<div id="proj_risk_base" title="风控清单补充信息">
<div class="mini-panel" title="风控清单补充信息" showCollapseButton="true" style="width: 100%;">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="proj_risk_detail"  title="风控清单补充信息">
		<tr class="tr-project-info tr-even">
			<td class="td-content-title" width="12%">我司评估等级：</td>
			<td class="td-content" width="38%"><input id ="proj_info.adjustlevel" name="proj_info.adjustlevel" class="mini-textbox" type="text" value="${requestScope['proj_info.adjustlevel']}" label="我司评估等级" ></td>
			<td class="td-content-title" width="12%">合同生效前提：</td>
			<td class="td-content" width="38%"> <input id="proj_info.effectivecondition" name="proj_info.effectivecondition" class="mini-textbox" value="${requestScope['proj_info.effectivecondition'] }" label="合同生效前提" /></td>
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title" width="12%">租金支付类型：</td>
			<td class="td-content" width="38%">
			<input id="proj_info.paymenttype" name="proj_info.paymenttype" class="mini-combobox" textField="name" 
				       	   valueField="value"  
						allowInput="false" 
						   data="[{name :'月付',value:'月付'},{name :'季付',value:'季付'},{name :'半年付',value:'半年付'}
						   ,{name :'年付',value:'年付'}
						   ,{name :'双月付',value:'双月付'}
						   ,{name :'不规则',value:'不规则'}]"
						   value="${requestScope['proj_info.paymenttype']}"/>
			</td>
			<td class="td-content-title" width="12%">每期租金：</td>
			<td class="td-content" width="38%">
				<input id="proj_info.paymoney" name="proj_info.paymoney" label="每期租金" class="mini-combobox" textField="name" 
				       valueField="value"   
				       allowInput="false"
				        data="[{name :'等额租金',value:'等额租金'},{name :'不等额租金',value:'不等额租金'}]"
					   value="${requestScope['proj_info.paymoney']}" />	
			</td>
			
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title" width="12%">信审有效期：</td>
			<td class="td-content" width="38%"><input id ="proj_info.expirationdate" format="yyyy-MM-dd" showTime="true" required="true" name="proj_info.expirationdate"  class="mini-datepicker asLabel" allowInput="false" type="text" value="${requestScope['proj_info.expirationdate']}"  label="信审有效期" ></td>
			<td class="td-content-title" width="12%">是否涉及多家设备商提供保证金：</td>
			<td class="td-content" width="38%"><input id="proj_info.ismanybond" name="proj_info.ismanybond" label="是否涉及多家设备商提供保证金" class="mini-combobox" textField="name" 
				       valueField="value"   
				       allowInput="false"
				        data="[{name :'是',value:'是'},{name :'否',value:'否'}]"
						   value="${requestScope['proj_info.ismanybond']}" />	</td>
		</tr>
		<tr class="tr-project-info tr-even">
			<td class="td-content-title" width="12%">资产管理建议：</td>
			<td class="td-content" width="38%"><input id ="proj_info.assetopinion" name="proj_info.assetopinion" class="mini-textbox"  type="text" value="${requestScope['proj_info.assetopinion']}"  label="资产管理建议" ></td>
			 <td class="td-content-title">项目类型：</td>
	         <td class="td-content">
				<input name="proj_info.projtype" label="项目类型" class="mini-combobox" textField="name"  required="true"
				       valueField="value"    dataField="datas" allowInput="false"  
				       data-options="{_params:{pid:'proj_type'}}" 
					   onbeforeshowpopup="onbeforeshowpopup"
					   onvaluechanged="comboboxChanged" value="${requestScope['proj_info.projtype']}" text="${requestScope['rawValue_proj_info.projtype'] }" 
					   onitemclick="projTypeComboboxChanged"/>	 
				<input id="rawValue_proj_info.projtype" name="rawValue_proj_info.projtype" value="${requestScope['rawValue_proj_info.projtype']}" class="mini-textbox" style="display:none"/></td>
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title" width="12%">承租人性质：</td>
			<td class="td-content" width="38%"><input id ="proj_info.lesseenature" required="true" name="proj_info.lesseenature" class="mini-textbox" type="text" value="${requestScope['proj_info.lesseenature']}" label="承租人性质" ></td>
			<td class="td-content-title">上会时间：</td>
		    <td class="td-content"><input name="proj_meeting.meetingdate" allowInput="false"  class="mini-datepicker" label="上会时间" required="true" value="${requestScope['proj_meeting.meetingdate'] }"/></td>
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title" width="12%">支付方式：</td>
			<td class="td-content" width="38%"><input required="true" name="proj_info.paymentmode" class="mini-textbox" type="text" value="${requestScope['proj_info.paymentmode']}" label="支付方式" ></td>
			<td class="td-content-title">付款节奏：</td>
		    <td class="td-content"><input name="proj_info.paymentrhythm" id="proj_info.paymentrhythm"  class="mini-textbox" label="付款节奏" required="true" value="${requestScope['proj_info.paymentrhythm']}"/></td>
		</tr>
		<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">付款前提：
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_info.paymentpremise" id="proj_info.paymentpremise" required="true" label="付款前提" class="mini-textbox" style="width:72%;"  required="true" value="${requestScope['proj_info.paymentpremise'] }" />
		        </td>
		    </tr>
	<%-- 	<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">参加审批人员：
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_meeting.approvalperson" required="true" label="参加审批人员" class="mini-textbox" style="width:72%;"  required="true" value="${requestScope['proj_meeting.approvalperson'] }" />
		        </td>
		    </tr> --%>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title">合同审批或签约前提：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="合同审批或签约前提" id="proj_info.approvalcondition" name="proj_info.approvalcondition" value="${requestScope['proj_info.approvalcondition'] }" class="mini-textarea"  type="text" >  </td>
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title">是否有特殊事项需要在信审会后流程确认：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="其他" id="proj_info.special" name="proj_info.special" value="${requestScope['proj_info.special'] }" class="mini-textarea"  type="text" >  </td>
		</tr>
		<tr class="tr-project-info tr-even">
			<td class="td-content-title">其他：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="其他" id="proj_info.othermemo" name="proj_info.othermemo" value="${requestScope['proj_info.othermemo'] }" class="mini-textarea"  type="text" >  </td>
		</tr>
		
	</table>
</div>
</div>
<div id="id_table_proj_risk_list"></div>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools=false}
	tenwa.createTable({
		id: "proj_risk_list",
		renderTo: "id_table_proj_risk_list",
		width: '100%',
		height: 400,
		lazyLoad: false,
		title: "生成风控清单",
		remoteOper : false,
		showPager: true,
		multiSelect: true,
		showToolbar: showTools,
		tools: [{
			html : '生成风控清单',
			plain : true,
			iconCls : 'icon-add',
			handler : function(miniTable, buttonText) {
				if(miniui_ext.submitFormValidation(["proj_info.lesseenature"])==false||miniui_ext.submitFormValidation(["proj_info.expirationdate"])==false){
					return false;
				} 
				var params = getParams();
				var fileTeplate=new fileTemplateConfig({
					isAttachment : true,
					attachmentParams: {
						module:'WorkflowAttchmentFiles',
						jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
						attachmentFileDictId : 'root.FileType.Type5.01',//风控清单Id
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
					templateno:'F-201603011',
					tableid:miniTable.config.id,
					modelname:miniTable.config.title,
					returntype:'listtocurpage',
					jscallbak :function(tableid){
						mini.get("table_id1").reload();
					},
					parames:params,
					});
					fileTeplate.createFile();
					if (mini.get("id_customworkflowattachment")) {
						mini.get("id_customworkflowattachment").reload();
						mini.get("id_workflowhisAttachment").reload();
					}
			
			}
		},'-','remove'],
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		}, 
		data: JsonUtil.decode('${empty json_proj_risk_list_str ? "[]" : json_proj_risk_list_str }'),
		columns: [
			{type: 'indexcolumn',width:9},
			{type:'checkcolumn',width:12},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'createdate', header: '文件生成时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
			{field: 'create', header: '操作',renderer: showOperatorRisk}
		]
	});
});
function getParams(){
	var params={};
	params["flowunid"]=flowUnid;
	params["create.date"]= new Date().format("yyyy-MM-dd")+"";
	params["c.lesseenature"]=mini.get("proj_info.lesseenature").getValue();
	params["c.paymenttype"]=mini.get("proj_info.paymenttype").getValue();
	params["c.paymoney"]=mini.get("proj_info.paymoney").getValue();
	params["c.projregistrar"]=mini.get("proj_info.projregistrar").getValue();
	params["c.paymentrhythm"]=mini.get("proj_info.paymentrhythm").getValue();
	params["c.paymentpremise"]=mini.get("proj_info.paymentpremise").getValue();
	var myDate = new Date();  
	var time=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
	params["c.time"]=time;

	//商务条件 
	var clist=['equipamt','firstpayment','firstpaymentratio','cleancreditmoney','cautionmoney','cautiondeductionratio','insurancelessee_show','irrshow'
	           ,'rawValue_incomenumberyear','leaseterm','handlingchargemoney','rawValue_settlemethod','faccautionmoney','managementmoney'];
	for(var i=0;i<clist.length;i++){
		if(clist[i]=="equipamt"||clist[i]=="firstpayment"||clist[i]=="cautionmoney"||clist[i]=="cleancreditmoney"||clist[i]=="handlingchargemoney"||clist[i]=="insurancelessee_show"||clist[i]=="managementmoney"){
			var value=((mini.get(clist[i]).getValue()).replace(/,/g,""))/10000;	
			params["c."+clist[i]] = typeof(mini.get(clist[i]).getValue())=='undefined'?"":value;
			
		}else if(clist[i]=="cautiondeductionratio"||clist[i]=="firstpaymentratio"){
			var caut=mini.get(clist[i]).getValue()+"%";
			var cau=parseFloat(caut).toFixed(2)+"%";
			params["c."+clist[i]] = typeof(mini.get(clist[i]).getValue())=='undefined'?"":cau;
		}else if(clist[i]=="irrshow"){
			var irr=mini.get(clist[i]).getValue();
			var ir=parseFloat(irr).toFixed(2)+"%";
			params["c."+clist[i]] = typeof(mini.get(clist[i]).getValue())=='undefined'?"":ir;
		}else{
			params["c."+clist[i]] = typeof(mini.get(clist[i]).getValue())=='undefined'?"":mini.get(clist[i]).getValue();			
		}
	}
	params["c.cleanleasemoneyratio"]=(Number(mini.get("cleancreditmoney").getValue().replace(/,/g,''))/Number(mini.get("equipamt").getValue().replace(/,/g,''))*100).toFixed(2)+"%";
	var isdeduction=mini.get("deductiontype").getValue();//是否做扣
	if(isdeduction.indexOf("feetype2")<0){
		params["c.isdeduction"]="否"
	}else{
		params["c.isdeduction"]="是"
	};
	var rentplan = mini.get("fund_rent_plan_frame").getData();
	var periodamt=0;
	var periodtype=mini.get("rawValue_periodtype").getValue();
	for(var i=0;i<rentplan.length;i++){
		if(rentplan[i].rentlist=='1'&&periodtype=="期初"){
			periodamt=rentplan[i].rent/10000;
		}		
	}
	params["c.rentqian"] = parseFloat(periodamt).toFixed(2);
	//客户类型
	params["proj_info.custtype"] = "${requestScope['proj_info.custclass']}" == "CUST_INFO_PERSON"?'自然人':'法人';
	var projlist = ['proj_info.effectivecondition','proj_info.expirationdate','proj_info.adjustlevel','proj_info.othermemo',
					'proj_info.approvalcondition','proj_info.assetopinion','proj_info.special'];
	for(var i=0;i<projlist.length;i++){
		if(projlist[i]=="proj_info.expirationdate"){
			params[projlist[i]] = typeof(mini.get(projlist[i]).getValue())=='undefined'?"":mini.get(projlist[i]).getText();
		}else{
			params[projlist[i]] = typeof(mini.get(projlist[i]).getValue())=='undefined'?"":mini.get(projlist[i]).getValue();		
		}
	}
	var guaranteedetail= mini.get("proj_guarantee_detail").getData();
	var gdtail= [];
	for(var i=0;i<guaranteedetail.length;i++){	
	 	if(gdtail.indexOf(guaranteedetail[i].assurorname)==-1){
	 		gdtail.push(guaranteedetail[i].assurorname);			
		} 
	}

	params["c.guaranteedetail"]=gdtail;
	params["c.projregistrar"]="${requestScope['rawValue_proj_info.projregistrar']}";
	//租赁物供应商
	var leaseform=mini.getbyName("rawValue_proj_info.leasform").getValue();
	var cautionmoney=mini.getbyName("cautionmoney").getValue();
	var projequip=mini.get("proj_equip").getData();
	var vndrname=[];
	for(var i=0;i<projequip.length;i++){
		vndrname.push(projequip[i].vndrname);		
	}
	//供应商名称 --临时解决方案 回租 直接为空
	if(leaseform.indexOf("回租")<0){
		params["c.vndrname"]=vndrname;
	}else{
		params["c.vndrname"]="";
	}
	params["c.ensure"]=mini.get("proj_info.ismanybond").getValue();
	/* if(leaseform=="直租"&&cautionmoney!=0&&vndrname.length>0){
		params["c.ensure"]="是";
	}else{		
		params["c.ensure"]="否";		
	} */
	
	
	params["c.paymentmode"]=mini.getbyName("proj_info.paymentmode").getValue();

	params["c.meetingdate"]=mini.getbyName("proj_meeting.meetingdate").getText(); 
	return params;
}
function downloadFile(Id) {
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function showOperatorRisk(e){
	var id = e.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2" 
	var resStr =base2;+"&nbsp;&nbsp;\&nbsp;&nbsp;"+base3;
	return resStr;
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
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("proj_risk_detail");
}
function projTypeComboboxChanged(e){
	document.getElementById('rawValue_proj_info.projtype').value = e.sender.text;
}
</script>
