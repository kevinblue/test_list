<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var leaseForm = [{value: '1',name: '直租'}, {value: '2',name: '回租'}, {value: '0',name: '其它'}];
var comboParams = {
	oneclassifyCode:"wordtempletypefirst7",
	xmlFileName:"/eleasing/jsp/template_word/templatewordSelect.xml"
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
			mini.get("threeclassify").setValue();
			mini.get("fourclassify").setValue();
			mini.get("fiveclassify").setValue();
			mini.get("sixclassify").setValue();
		}else if(comboid == "threeclassify"){
			nextComboParams.fourclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
			nextComboParams.threeclassifycode = combo.getValue();
			mini.get("fourclassify").setValue();
			mini.get("fiveclassify").setValue();
			mini.get("sixclassify").setValue();
		}else if(comboid == "fourclassify"){
			nextComboParams.fiveclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
			nextComboParams.threeclassifycode = mini.get("threeclassify").getValue();
			nextComboParams.fourclassifycode = combo.getValue();
			mini.get("fiveclassify").setValue();
			mini.get("sixclassify").setValue();
		}else{
			nextComboParams.sixclassify = true;
			nextComboParams.twoclassifycode = mini.get("twoclassify").getValue();
			nextComboParams.threeclassifycode = mini.get("threeclassify").getValue();
			nextComboParams.fourclassifycode = mini.get("fourclassify").getValue();
			nextComboParams.fiveclassifycode = combo.getValue();
			mini.get("sixclassify").setValue();
		}
		var nextComboParamsUrl = aputil.getParamsUrl(comboUrl, nextComboParams);
		mini.get(nextcomboid).setUrl(nextComboParamsUrl);
	}
	chargeTemplate();
}

//获取合同模板
function chargeTemplate() {
	var twoClassify = mini.get("twoclassify").getValue();
	var threeClassify = mini.get("threeclassify").getValue();
	var fourClassify = mini.get("fourclassify").getValue();
	var fiveClassify = mini.get("fiveclassify").getValue();
	var sixClassify = mini.get("sixclassify").getValue();
	if (twoClassify != "") {
		var param = {};
		param["twoClassify"] = twoClassify;
		param["threeClassify"] = threeClassify;
		param["fourClassify"] = fourClassify;
		param["fiveClassify"] = fiveClassify;
		param["sixClassify"] = sixClassify;
		//window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body, "正在加载合同模板 请稍等...");
		//currentDeleteFileLoadMask.show();
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
    var cmessage=mini.decode(svote);
    document.getElementById("contract_word_check_list").innerHTML=cmessage.message;
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
	//追加参数
}
</script>
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-odd">
		<td class="td-content-title" width="12%">一级分类：</td>
		<td class="td-content" width="38%" style="text-indent: 0px;">
			<input name="twoclassify" id="twoclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'twoclassify','threeclassify')"
				value="<mini:param  name="twoclassify"/>"  
				text="<mini:param  name="rawValue_twoclassify"/>">
			<input id="rawValue_twoclassify" name="rawValue_twoclassify" value="<mini:param name='rawValue_twoclassify'/>" class="mini-textbox" style="display:none"/>
		</td>
		<td class="td-content-title" width="12%">二级分类：</td>
		<td class="td-content" width="38%" style="text-indent: 0px;">
			<input name="threeclassify" id="threeclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'threeclassify','fourclassify')"
				value="<mini:param  name="threeclassify"/>"  
				text="<mini:param  name="rawValue_threeclassify"/>">
			<input id="rawValue_threeclassify" name="rawValue_threeclassify" value="<mini:param  name="rawValue_threeclassify"/>" class="mini-textbox" style="display:none"/>
		</td>
	</tr>
	<tr class="tr-even">
		<td class="td-content-title">三级分类：</td>
		<td class="td-content" style="text-indent: 0px;">
			<input name="fourclassify" id="fourclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'fourclassify','fiveclassify')"
				value="<mini:param  name="fourclassify"/>"  
				text="<mini:param  name="rawValue_fourclassify"/>">
			<input id="rawValue_fourclassify" name="rawValue_fourclassify" value="<mini:param  name="rawValue_fourclassify"/>" class="mini-textbox" style="display:none"/>
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
				value="<mini:param  name="fiveclassify"/>"  
				text="<mini:param  name="rawValue_fiveclassify"/>">
			<input id="rawValue_fiveclassify" name="rawValue_fiveclassify" value="<mini:param  name="rawValue_fiveclassify"/>" class="mini-textbox" style="display:none"/>
		</td>
	</tr>
	<tr class="tr-odd">
		<td class="td-content-title">五级分类：</td>
		<td class="td-content" style="text-indent: 0px;">
			<input name="sixclassify" id="sixclassify" 
				class="mini-combobox"
				dataField="datas"
				textField="name"
				valueField="code"
				showNullItem="true"
				onvaluechanged="reloadNextClassify(e,'sixclassify','')"
				value="<mini:param  name="sixclassify"/>"  
				text="<mini:param  name="rawValue_sixclassify"/>">
			<input id="rawValue_sixclassify" name="rawValue_sixclassify" value="<mini:param  name="rawValue_sixclassify"/>" class="mini-textbox" style="display:none"/>
		</td>
		<td class="td-content-title" colspan="2" style="text-indent: 0px;">
			<a id="create_contract_word" class="mini-button" iconCls="icon-new" onclick="createContractWord()" style="color: red;font-weight: bold;">生成合同</a>
		</td>
	</tr>
	<tr class="tr-even">
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
</script>