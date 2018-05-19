<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var leaseForm = [{value: '1',name: '直租'}, {value: '2',name: '回租'}, {value: '0',name: '其它'}];
//获取合同模板
function chargeTemplate() {
		var param = {};
		param["twoClassify"] = 'wordtempletypetwo.97';
		param["oneClassify"] = 'wordtempletypefirst8';
		//window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body, "正在加载合同模板 请稍等...");
		//currentDeleteFileLoadMask.show();
	    param["changeRowNum"]=8;
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
		tableid : "contract_word",
		modelname : "合同管理",
		url : '/leasing/template/CreateFileByTemplate.action',
		parames : params
	});
	fileTeplate.createFile();
}
function initCreateWordData(params) {
	//合同基本信息
	 var contract_base_info_form=new mini.Form("contract_base_info_form");
	 var contract_base_info_data=contract_base_info_form.getData().contract_info;
	for(var cbid in contract_base_info_data){
		params["contract_info."+cbid]=contract_base_info_data[cbid];
	}
	params['contract_info.leasformname']=mini.getbyName("contract_info.leasform").getText();
	params['contractnumbre.maincontract']=generateContractNo('L',0);
	params['contractnumbre.contractno']=generateContractNo('C',0);
	

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
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-odd">
		<td class="td-content-title"  style="text-indent: 0px;">
			<a id="create_contract_word" class="mini-button" iconCls="icon-new" onclick="createContractWord()" style="color: red;font-weight: bold;">生成文件</a>
		</td>
</table>
<table class="fillTable" cellspacing="0" cellpadding="0" >
	<tr class="tr-even">
		<td class="td-content-title">协议模板：</td>
		<td class="td-content" colspan="3"><div style="overflow:hidden;width:100%;" id="contract_word_check_list">请先选择!</div></td>
	</tr>
	<tr class="tr-contractect-word-list">
		<td class="td-content" colspan="4">
			<jsp:include page="../contract_comm/contract_list_info.jsp"></jsp:include><%--合同清单 --%>
		</td>
	</tr>
</table>
<script language="javascript">
jQuery(function(){chargeTemplate()});
</script>