<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<jsp:include page="../../reckon/rent_common/cal_js_5_1_common.jsp"></jsp:include>
<script type="text/javascript">
jQuery(function(){
	var fields = form.getFields();
	for(var  i = 0  ; i < fields.length ; i++){
		var field = fields[i];
		if(field.id == 'equipamt' ){
			mini.get(field.id).set({'onkeyup':equipamtChanged});
			mini.get(field.id).set({'onvaluechanged':equipamtChanged});
		}else if(field.id == 'leasetermday' || field.id == 'ratefloat'){
			mini.get(field.id).set({'onvaluechanged':leasetermdayChange});
		}else if(field.vtype=="double"){
			mini.get(field.id).set({'onkeyup':fieldValueChange}) ;
			mini.get(field.id).set({'onvaluechanged':fieldValueChange}) ;
		}
	}
	confirmConditionRate();
});
function equipamtChanged(e){
	changeInputToThoud(e);
	calSalesvolume();
	calActualfund();
	calRatio();
}
function leasetermdayChange(e){
	changeInputToThoud(e);
	confirmConditionRate();
}

function fieldValueChange(e){
	var obj = e.sender;
	var radioId =  obj.id+"ratio";
	if(obj.id == 'counselingmoney'){
		radioId = "counselingratio";
	}else if(obj.id == 'cautionmoney'){
		radioId = "cautiondeductionratio";
	}
	setformatvalue(e.sender);
	var feeValue = obj.getValue().replace(/,/g,"");
	var ratiovalue = getRadio(feeValue);
	if(mini.get(radioId)){
		mini.get(radioId).setValue(ratiovalue);
	}else{
		//console.info(field.id);	
	}
	calSalesvolume();
	calActualfund();
}

function getRadiotwo(money){
	var equipAmt = getNumber('equipamt');
	return !equipAmt || Number(equipAmt)<=0 ?  0 : decimal(money/equipAmt*100, 2);
}

function getRadio(money){
	var equipAmt = getNumber('factoringpayout');
	return !equipAmt || Number(equipAmt)<=0 ?  0 : decimal(money/equipAmt*100, 2);
}
//销售额=应收账款受让款-保理费收入-保险费收入-担保费收入-登记费收入-咨询服务费收入-手续费收入-其他收入+其他成本
function calSalesvolume(){
	//应收账款受让款
	var factoringpayout = getNumber('factoringpayout');
	//保理费收入
	var factoringincome = getNumber('factoringincome');
	//保险费收入
	var insuremoney = getNumber('insuremoney');
	//担保费收入
	var factoringguaranteefee = getNumber('factoringguaranteefee');
	//登记费收入
	var factoringregisterfee = getNumber('factoringregisterfee');
	//咨询服务费收入
	var counselingmoney = getNumber('counselingmoney');
	//手续费
	var handlingchargemoney = getNumber('handlingchargemoney');
	//其他收入
	var otherfeerec  = getNumber('otherfeerec');
	//其他成本
	var otherfee = getNumber('otherfee');
	var salesvolume = factoringpayout - factoringincome - insuremoney- 
					  factoringguaranteefee - factoringregisterfee -counselingmoney 
					  -handlingchargemoney - otherfeerec + otherfee;
	mini.get('salesvolume').setValue(formatNumberThousand(salesvolume));
	mini.get('salesvolumeratio').setValue(getRadio(salesvolume));
}

//净融资额=应收账款受让款-保证金
function calActualfund(){
	//应收账款受让款
	var factoringpayout = getNumber('factoringpayout');
	//var salesvolume = getNumber('salesvolume');
	var cautionmoney = getNumber('cautionmoney');
	var actualfund = factoringpayout - cautionmoney;
	mini.get('actualfund').setValue(formatNumberThousand(actualfund));
	mini.get('actualfundradio').setValue(getRadio(actualfund));
}

function getLastPlanDate(){
	var leasetermday = getNumber("leasetermday")? getNumber("leasetermday") : 0;
	var leaseamtdate = mini.get('leaseamtdate').getValue();
	return dateAdd('d',leasetermday,leaseamtdate);
}

//确定资金成本
function confirmConditionRate(){
	var leasetermday = getNumber("leasetermday");
	//应收账款转让日
	var leaseamtdate = mini.get('leaseamtdate').getFormValue();
	if(leasetermday && leaseamtdate){
		var data = {};
		data.leaseamtdate = leaseamtdate;
		data.leasetermday = leasetermday;
		var flag = true;
		var baseRate = 0;
		var floatingRadio = 0;
		//求基准利率
		$.ajax({
	        url: "<%=request.getContextPath() %>/leasing/getBaseRate.action",
	        type: "post",
	        data:  data ,
	        async : false,
	        success: function (json) {
	        	var data = mini.decode(json);
	        	baseRate = Number(data.baseRate);
	        	floatingRadio = Number(data.floatingRadio);
	        },
            error: function (jqXHR, textStatus, errorThrown) {
                flag = false;
                alert(jqXHR.responseText);
            }
	    });
		if(!flag){
			return;
		}
		//基准利率
		//mini.get('baserate').setValue(baseRate);
	    //资金成本
	    var fundcostYear =  decimal(baseRate * (1+floatingRadio/100),2);//数据字典维护的上浮利率
		var fundcostMonth = decimal(fundcostYear /12,2) ;
		mini.get('factoringfundcostmonth').setValue(fundcostMonth);
		mini.get('factoringfundcostyear').setValue(fundcostYear);
		//平面利率
		var ratefloat = getNumber("ratefloat");
		var factoringflatrateyear = decimal(baseRate * (1+ratefloat/100),2);
		var factoringflatratemonth = decimal(factoringflatrateyear/12,2);
		mini.get('factoringflatrateyear').setValue(factoringflatrateyear);
		mini.get('factoringflatrateyear1').setValue(factoringflatrateyear);
		mini.get('factoringflatratemonth').setValue(factoringflatratemonth);
	}
}
function calRatio(){
	//保证金cautionmoney	
	var cautionmoney = getNumber("cautionmoney");
	mini.get('cautiondeductionratio').setValue(getRadio(cautionmoney));
	//应收账款受让款factoringpayout	factoringpayoutratio
	var factoringpayout = getNumber("factoringpayout");
	mini.get('factoringpayoutratio').setValue(getRadiotwo(factoringpayout));
	//咨询服务费收入counselingmoney	counselingratio
	var counselingmoney = getNumber("counselingmoney");
	mini.get('counselingratio').setValue(getRadio(counselingmoney));
	//保理费收入factoringincome	factoringincomeratio
	var factoringincome = getNumber("factoringincome");
	mini.get('factoringincomeratio').setValue(getRadio(factoringincome));
	//手续费收入handlingchargemoney	handlingchargemoneyratio
	var handlingchargemoney = getNumber("handlingchargemoney");
	mini.get('handlingchargemoneyratio').setValue(getRadio(handlingchargemoney));
	//保险费收入insuremoney	insuremoneyratio
	var insuremoney = getNumber("insuremoney");
	mini.get('insuremoneyratio').setValue(getRadio(insuremoney));
	//其他收入otherfeerec	otherfeerecratio
	var otherfeerec = getNumber("otherfeerec");
	mini.get('otherfeerecratio').setValue(getRadio(otherfeerec));
	//担保费收入factoringguaranteefee	factoringguaranteefeeratio
	var factoringguaranteefee = getNumber("factoringguaranteefee");
	mini.get('factoringguaranteefeeratio').setValue(getRadio(factoringguaranteefee));
	//其他成本otherfee 	otherfeeratio
	var otherfee = getNumber("otherfee");
	mini.get('otherfeeratio').setValue(getRadio(otherfee));
	//登记费收入factoringregisterfee	factoringregisterfeeratio
	var factoringregisterfee = getNumber("factoringregisterfee");
	mini.get('factoringregisterfeeratio').setValue(getRadio(factoringregisterfee));
}
function save(e){
	var startdate = $minigetvalue("leaseamtdate");
	if(!startdate){
		mini.alert('请先输入应收账款转让日！！！');
		return;
	}
	var equipamt =  $minigetvalue("equipamt");
	if(equipamt){
		if(Number(equipamt) <= 0 ){
			mini.alert('应收账款受让款金额需大于0！！！');
			return;
		}
	}else{
		mini.alert('应收账款受让款金额不能为空！！！');
		return;
	}
	
	var leasetermday = $minigetvalue("leasetermday");
	var numRegex  = /^[-\+]?\d+$/;
	if(!numRegex.test(leasetermday)){
		mini.alert('请你输入正确格式的期限！')
		return;
	}else{
		if(Number(leasetermday) <= 0 ){
			mini.alert('期限需大于0！');
			return ;
		}
	}
	/*
	检查保理明细是否为空
	*/
	var renPlans = mini.get('fund_rent_plan').getData();
	if(renPlans.length <= 0){
		mini.alert('请你先填写保理明细！！！');
		return;
	}
	o = form.getData(true,true);
	for(var p in o){
		o[p] = o[p].replace(/,/gim,'');
	} 
	o.json_fund_rent_plan_str  = $('#id_json_fund_rent_plan_str').val();
	mini.mask({
		el: document.body,
		cls: 'mini-mask-loading',
		html: '正在测算中，请稍后...'
	});
	var url="<%=request.getContextPath() %>/leasing/facRentCalculate.action";
	$.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	var result = mini.decode(text);
        	if(result.rentcalculaters == 'yes'){
        		mini.get('factoringirryear').setValue(decimal(result.irr,2));
        		mini.get('factoringirrmonth').setValue(decimal(result.factoringirrmonth,2));
        		mini.get('factoringyearrateyear').setValue(decimal(result.xirr,2));
        		mini.get('factoringyearratemonth').setValue(decimal(result.factoringyearratemonth,2));
        		 
        		var costmonth = mini.get('factoringfundcostmonth').getValue();
        		mini.get('factoringspreadmonth').setValue(decimal(result.factoringspreadmonth,2));
        		mini.get('factoringspreadyear').setValue(decimal(result.factoringspreadyear,2));
        		mini.get('factoringflatrateyear').setValue(decimal(result.factoringflatrateyear,2));
        		mini.get('factoringflatratemonth').setValue(decimal(result.factoringflatratemonth,2));
        		
        		mini.get('gp').setValue(result.gp);
            	var fundrentplan =result.rentplanlist ;
            	var finacashdetail =result.cashdetaillist ;
            	var fundchargeplan =  result.fundchargeplan;
            	
            	mini.get("fund_rent_plan").setData(fundrentplan);
            	mini.get("fund_cash_plan_frame").setData(finacashdetail);
            	mini.get("fund_fund_plan").setData(fundchargeplan);
            	//分别把租金计划  、现金流 、资金收付计划保存至隐藏域
            	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
            	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
            	$("#id_json_fund_fund_charge_str").val(mini.encode(fundchargeplan));
            	mini.unmask(document.body);
            	mini.alert('测算成功！');
        	}
        	else {
            	mini.unmask(document.body);
        		mini.alert('测算失败，请与管理员联系！');
            	updateInputThousand();
        	}
        	
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	mini.unmask(document.body);
            alert(jqXHR.responseText);
        }
    });
	
}

</script>