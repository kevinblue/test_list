<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var leaseForm = [{value: '1',name: '直租'}, {value: '2',name: '回租'}, {value: '0',name: '其它'}];
//获取合同模板
function chargeTemplate() {
		var param = {};
		param["oneClassify"] = 'wordtempletypefirst8';
		param["twoClassify"] = 'wordtempletypetwo.97';
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
				mini.alert("抓合同模板失败!");
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
		}
	);
	$('input[name="contract_word_list_check_box"]:checked').each(
		function() {
			//chk_value.push($(this).attr("checked", false));
		}
	);
	return tempid;
}
function createContractWord() {
	var tempids = getTempateIdInfo();
	if ("" == tempids){
		mini.alert("请选择合同模板!!!");
		return;
	}
	
	var changeTime=mini.getbyName('contract_change_info.changedate').getValue();
	if(!changeTime){
		mini.alert('请填写变更说明!!!');
		return;
	}
	
	var changeInfo=mini.getbyName('fund_rent_adjust.paydayadjust').getValue();
	if(!changeInfo){
		mini.alert('请填写变更信息!!!');
		return;
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
	params['contract_info.leasformname']=mini.getbyName("contract_info.leasform").getValue();
	params['contractnumbre.maincontract']=generateContractNo('L',0);
	params['contractnumbre.contractno']=generateContractNo('C',0);
	
	//原租金计划
	var fund_rent_plan=mini.get('fund_rent_plan').getData();
	
	//现租金计划
	var fund_rent_plan_new=mini.get('fund_rent_plan_new').getData();
	
    //变更信息:年,月,日,合同编号,开始期次
    //change_year,change_month,change_day,contractid,startlist2
	//变更年,月,日
    var change_year=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'yyyy');
    var change_month=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'MM');
    var change_day=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'dd');
    params['change_info.change_year']=change_year;
    params['change_info.change_month']=change_month;
    params['change_info.change_day']=change_day;
    //合同编号
    params['change_info.contractid']=mini.getbyName('contract_info.contractid').getValue();
    //开始期次
	params['change_info.startlist']='第'+mini.getbyName('fund_rent_adjust.startlist').getValue()+'期';
    params['change_info.startlist2']=mini.getbyName('fund_rent_adjust.startlist').getValue();
    //支付时间
    params['change_info.paydayadjust']=mini.getbyName('fund_rent_adjust.paydayadjust').getValue();
    var adjust_year=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'yyyy');
    var adjust_month=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'MM');
    var adjust_day=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'dd');
    params['change_info.paydayadjust']=adjust_year+'年'+adjust_month+'月'+adjust_day+'日';

	//期数,计划日期,调整前的租金,租赁本金,利息,调整后的租金额,租赁本金,利息
	//rentlist,plandate,rent_old,corpus_old,interest_old,rent_new,corpus_new,interest_new
    var list=0;
	if(fund_rent_plan.length>fund_rent_plan_new.length){
		list=fund_rent_plan.length;
	}else{
		list=fund_rent_plan_new.length;
	}
	var rent_plans=[];
	for(var i=0;i<list;i++){
		var rent_plan={};
		if(mini.getbyName('fund_rent_adjust.startlist').getValue()==fund_rent_plan[i].rentlist){
			params['change_info.firstrent']=fund_rent_plan[i].rent;
		}
		if(fund_rent_plan.length>i){
			//期次
			rent_plan['rentlist']=fund_rent_plan[i].rentlist;
			//支付时间
			var date=tenwa.toDate(fund_rent_plan[i].plandate,'yyyy年MM月dd日');
			rent_plan['plandate']=tenwa.toChar(date,'yyyy年MM月dd日');
			//调整前的租金
			var rent_old=fund_rent_plan[i].rent;
			rent_plan['rent_old']=tenwa.money2Thousand(parseFloat(rent_old).toFixed(2));
		    //调整前的租赁本金
		    var corpus_old=fund_rent_plan[i].corpus;
		    if(corpus_old){
		    	rent_plan['corpus_old']=tenwa.money2Thousand(parseFloat(corpus_old).toFixed(2));
		    }else{
			    rent_plan['corpus_old']='0.00';
		    }
		    //调整前的利息
		    var interest_old=fund_rent_plan[i].interest;
		    if(interest_old){
		    	rent_plan['interest_old']=tenwa.money2Thousand(parseFloat(interest_old).toFixed(2));
		    }else{
		    	rent_plan['interest_old']='0.00';
		    }
		}
		if(fund_rent_plan_new.length>i){
			//期次
			rent_plan['rentlist']=fund_rent_plan_new[i].rentlist;
			//支付时间
			var date=tenwa.toDate(fund_rent_plan_new[i].plandate,'yyyy年MM月dd日');
			rent_plan['plandate']=tenwa.toChar(date,'yyyy年MM月dd日');
			//调整后的租金
			var rent_new=fund_rent_plan_new[i].rent;
			rent_plan['rent_new']=tenwa.money2Thousand(parseFloat(rent_new).toFixed(2));
		    //调整后的租赁本金
		    var corpus_new=fund_rent_plan_new[i].corpus;
		    if(corpus_new){
		    	rent_plan['corpus_new']=tenwa.money2Thousand(parseFloat(corpus_new).toFixed(2));
		    }else{
			    rent_plan['corpus_new']='0.00';
		    }
		    //调整后的利息
		    var interest_new=fund_rent_plan_new[i].interest;
		    if(interest_new){
		    	rent_plan['interest_new']=tenwa.money2Thousand(parseFloat(interest_new).toFixed(2));
		    }else{
		    	rent_plan['interest_new']='0.00';
		    }
		}
		rent_plans.push(rent_plan);
	}
	params['rent_plans']=mini.encode(rent_plans);
	
    //当前系统日期
    params['system_time.nowTime']= new Date().format("yyyy 年 MM 月 dd 日");
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