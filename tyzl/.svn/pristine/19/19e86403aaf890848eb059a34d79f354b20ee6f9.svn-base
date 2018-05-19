<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    

<script type="text/javascript">
var changeEventArray = [
	{'id':'equipamt',
	  'onvaluechanged':changeequipamtvalue,
	}, 
	{
		'id':'equipendvalue',
		'onkeyup':changeInputToThoud,
	}, 
	{
		'id':'firstpaymentratio',
		'onkeyup':changefirstpayment,
		'onvaluechanged':changefirstpayment
	},
	{
		'id':'assetsratio',
		'onkeyup':changeassetsratio,
		'onvaluechanged':changeassetsratio
	},
	{
		'id':'adviserratio',
		'onkeyup':changeadviserratio,
		'onvaluechanged':changeadviserratio
	},
	{
		'id':'rentorratevalue',
		'onkeyup':rentorratevaluekeyup
	},
	{
		'id':'settlemethod',
		'onvaluechanged':changeRentPlanTableButton
	}	
	,{
		'id':'rentorrate',
		'onvaluechanged':rentorratevaluechange,
		'onitemclick':rentorrateitemchange
	}, 
	{
		'id':'ratefloattype',
		'onvaluechanged':ratefloattypevaluechange
	}, 
	{
		'id':'baserate',
		'onvaluechanged':yearratekeyup
	},
	{
		'id':'ratefloatamt',
		'onvaluechanged':yearratekeyup
	},
	/* {
		'id':'periodtype',
		'onvaluechanged':periodtypevaluechange
	}, */
	/* {
		'id':'incomenumberyear',
		'onvaluechanged':incomenumberyearvaluechange
	},
	{
		'id':'incomenumber',
		'onvaluechanged':incomenumberyearvaluechange
	}, */
/* 	{
		'id':'grace',
		'onvaluechanged':incomenumberyearvaluechange
	}, */
	{
		'id':'leaseamtdate',
		'onvaluechanged':leaseamtdatevaluechange
	},
	{
		'id':'startdate',
		'onvaluechanged':startdatevaluechange
	},
	{
		'id':'firstplandate',
		'onvaluechanged':firstplandatevaluechange
	},
	{
		'id':'secondplandate',
		'onvaluechanged':secondplandatevaluechange
	},
	{
		'id':'lastplandate',
		'onvaluechanged':lastplandatevaluechange
	},
	{
		'id':'handlingchargemoneyratio',
		'onkeyup':changehandlingchargemoneyratio,
		'onvaluechanged':changehandlingchargemoneyratio
	},
	{
		'id':'managementmoneyratio',
		'onkeyup':changemanagementmoneyratio,
		'onvaluechanged':changemanagementmoneyratio
	},
	{
		'id':'cautiondeductionratio',
		'onkeyup':changecautiondeductionratio,
		'onvaluechanged':changecautiondeductionratio
	},
	{
		'id':'insurerate',
		'onkeyup':changeinsurerateratio,
		'onvaluechanged':changeinsurerateratio
	},
	{
		'id':'insurecostrate',
		'onkeyup':changeinsurecostrateratio,
		'onvaluechanged':changeinsurecostrateratio
	},
	{
		'id':'cautiondeductionmoney',
		'onkeyup':cautionmoneyremainvaluechange,
		'onvaluechanged':cautionmoneyremainvaluechange
	},
	{
		'id':'faccautionmoney',
		'onkeyup':changecautiondeductionratio,
		'onvaluechanged':changecautiondeductionratio
	},
	{
		'id':'faccautiondeductionmoney',
		'onkeyup':cautionmoneyremainvaluechange,
		'onvaluechanged':cautionmoneyremainvaluechange
	},
	{
		'id':'insuremoneytype',
		'onvaluechanged':insuremoneytypevaluechange
	},
	{
		'id':'insurancelessee_show',
		'onkeyup':insuremoneyvaluechange,
		'onvaluechanged':insuremoneyvaluechange
	},
	{
		'id':'insurancelessor_show',
		'onkeyup':insuremoneyvaluechange,
		'onvaluechanged':insuremoneyvaluechange
	},
	{
		'id':'nominalprice',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'beforeinterest',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'otherincome',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'returnpointincome',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'interestsupport',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'otherexpenditure',
		'onkeyup':changeInputToThoud,
		'onvaluechanged':changeInputToThoud
	},
	{
		'id':'penarate',
		'onvaluechanged':checkInputIntCompareToZero
	},
	{
		'id':'freedefainterday',
		'onvaluechanged':checkInputIntCompareToZero
	},
	{
		'id':'otherleasemoney',
		'onkeyup':cleanleasemoney,
		'onvaluechanged':cleanleasemoney
	}
];
if('${param.process}' && '${param.process}' == 'onhire'){
	changeEventArray = [
		{
			'id':'leaseamtdate',
			'onvaluechanged':leaseamtdatevaluechange
		},
		{
			'id':'startdate',
			'onvaluechanged':calculateBeforeInterest
		},
		{
			'id':'firstplandate',
			'onvaluechanged':firstplandatevaluechange
		},
		{
			'id':'secondplandate',
			'onvaluechanged':secondplandatevaluechange
		},
		{
			'id':'lastplandate',
			'onvaluechanged':lastplandatevaluechange
		}              
	];
}
jQuery(function(){
	for(var  i = 0  ; i < changeEventArray.length ; i++){
		var changeEvent = changeEventArray[i];
		var id = changeEvent.id;
		delete  changeEvent['id'];  
		$mini(id).set(changeEvent);
	}
});
//第一期支付日改变
function firstplandatevaluechange(e){
	$mini("secondplandate").setMinDate(e.value);
}

function secondplandatevaluechange(e){
	var incomeNumber = mini.get('incomenumber').getInputText();
	var incomenumberyear = $minigetvalue("incomenumberyear");//租金支付类型
	var tempValue = parseInt(incomenumberyear.replace("income_",""));
	var grace = mini.get('grace').getValue();
	incomeNumber = Number(incomeNumber) + Number(grace);
	if(incomeNumber && Number(incomeNumber) >=  2){
		var maxValue =  dateAdd('m',Number(tempValue) * (Number(incomeNumber)-2),mini.get('secondplandate').getValue());
		var minValue  = mini.get('secondplandate').getValue();
		if(Number(incomeNumber) > 2){
			minValue =  dateAdd('m',Number(tempValue) * (Number(incomeNumber)-3),mini.get('secondplandate').getValue());
		}
		$mini("lastplandate").setValue(maxValue);
		$mini("lastplandate").setMaxDate(maxValue);
		$mini("lastplandate").setMinDate(minValue); 
	}
}

//末期支付日改变
function lastplandatevaluechange(e){
	var firstplandate = $mini("firstplandate").getValue();
	if(!firstplandate){
		mini.alert('第一期租金支付日期为空，请先选择第一期租金支付日期！！！');
		$mini("lastplandate").setValue();
		return;
	}
	var incomeNumber = getNumber('incomenumber');
	var grace = getNumber('grace');
	incomeNumber += grace;
	if(!incomeNumber || Number(incomeNumber)<=0){
		mini.alert('还款期次需大于0！！！');
		$mini("lastplandate").setValue();
		return;
	}
	var incomenumberyear = $minigetvalue("incomenumberyear");//租金支付类型
	var tempValue = parseInt(incomenumberyear.replace("income_",""));
	var maxValue =  dateAdd('m',Number(tempValue) * (incomeNumber-1),firstplandate);
	if(mini.get('secondplandate').getValue() && incomeNumber > 1){
		var maxValue =  dateAdd('m',Number(tempValue) * (incomeNumber-2),mini.get('secondplandate').getValue());
	}
	var minValue = mini.get('startdate').getValue();
	if(incomeNumber > 1){
		minValue =  dateAdd('m',Number(tempValue) * (incomeNumber-2),firstplandate);
	}
    if(e.sender.getValue() &&(e.value <= minValue || e.value > maxValue)){
    	mini.alert('末期租金支付日期不能大于正常测算的末期租金支付日'+dateToStr('yyyy-MM-dd',maxValue)+'，<br/>且不能小于倒数第二期的租金支付日期'+dateToStr('yyyy-MM-dd',minValue)+'！！');
    	$mini("lastplandate").setValue(maxValue);
    	$mini("lastplandate").setMaxDate(maxValue);
		$mini("lastplandate").setMinDate(minValue); 
    	return;
    }
}

//起租日改变
function startdatevaluechange(e){
	$mini("firstplandate").setMinDate(e.value);
	calChangTab();//租前息改变方法
}
//根据利率计算方式改变年租息率
function yearratekeyup(e){
	if(typeof(e) != 'string')setformatvalue(e.sender);
	var ratefloattypevalue = $minigetvalue("ratefloattype");
	var ratefloatamt = getNumber("ratefloatamt"); //利率调整值
	var baserate = getNumber("baserate");//基准利率
	if("proportion" == ratefloattypevalue){
		$mini("yearrate").setValue(decimal((ratefloatamt+1) * baserate,6));
	}else if("add" == ratefloattypevalue){
		$mini("yearrate").setValue(ratefloatamt + baserate);
	}
}
/*
	利率计算方式：
		1 选择：固定利率 则利率调整值只读，去掉必填校验
		2 选择非‘固定利率’则利率调整值可输入，并且有必填校验
*/
function ratefloattypevaluechange(e){
	//var sender = e.sender;
	var ratefloattypevalue;
	if(typeof(e) != 'string'){
		ratefloattypevalue = $minigetvalue("ratefloattype");
	}else{
		ratefloattypevalue = "${requestScope['ratefloattype']}";
	}
	var ratefloatamt = getNumber("ratefloatamt"); //利率调整值
	var baserate = getNumber("baserate");//基准利率
	if(ratefloattypevalue == '' ? "proportion" : ratefloattypevalue == 'proportion'){
		$miniEnable("adjuststyle");
	}else{
		$miniSetCombValue("adjuststyle","",'');
		$miniDisable("adjuststyle");
		//$miniSetCombValue("adjuststyle","",'');
	}
	if("fixed" == ratefloattypevalue){//固定利率
		if(typeof(e) != 'string'){
			$mini("yearrate").setValue('0');
		}
		$mini("ratefloatamt").setValue("0");
		$mini("baserate").setValue("0");
		$mini("ratefloatamt").disable();//利率调整值
		$mini("baserate").disable();//基准利率
		$mini("ratefloatamt").setRequired(false);
		$miniEnable("yearrate");
	}else if("proportion" == ratefloattypevalue){//换央行浮动比率
		mini.get("yearrate").disable();
		$miniEnable("ratefloatamt");
		$mini("ratefloatamt").setRequired(true);
		$miniEnable("baserate");
		if(typeof(e) != 'string'){
			$mini("yearrate").setValue(decimal((ratefloatamt+1) * baserate,6));
			$mini("rentorratevalue").setValue("0");
		}
	}else if("add" == ratefloattypevalue){//换央行利率加点
		mini.get("yearrate").disable();
		$miniEnable("ratefloatamt");
		$mini("ratefloatamt").setRequired(true);
		$miniEnable("baserate");
		if(typeof(e) != 'string'){
			$mini("rentorratevalue").setValue("0");
			$mini("yearrate").setValue(ratefloatamt + baserate);
		}
	}else{//说明为空
		$mini("ratefloatamt").enable();//利率调整值
		$mini("baserate").enable();//基准利率
	}
	if(typeof(e) != 'string'){
		setRawValue(e);
	}else{
		if(!'${requestScope["ratefloattype"]}'){
			$miniSetCombValue("ratefloattype","proportion",'按央行浮动比率');
		}else{
			$miniSetCombValue("ratefloattype",ratefloattypevalue,"${requestScope['rawValue_ratefloattype']}");
		}
	}
}
/*
  保险计算方式 改为：本司付款、客户自保，不投保
     1.选择‘本司付款’   保险费(我司)必填 保险费(承租人)取消必填  保险费(我司)、保险费赋空 、
     	保险费(承租人)只读  保险费(我司)允许输入、保险费(承租人)赋为0
     2.客户自保  保险费(我司)取消必填 保险费(承租人)必填  
                      保险费(我司)、保险费赋空 、保险费(我司)只读  保险费(承租人)允许输入、保险费(我司)赋为0
     3.不投保  保险费(我司)取消必填 保险费(承租人)取消必填 保险费(我司)、保险费(承租人)、保险费赋为0保险费(我司)、保险费(承租人)只读
  参数 type说明:
	 1.change:代表的是保险计算公式的下拉触发事件
	 3.start:代表的页面初始化的控件选择按钮
 */
 function $miniDisableSetValue(id,value){
	  mini.get(id+'_show').setValue(value);
	  $('#'+id).val(value);
 }
 function insuremoneytypevaluechange(e){
		var sender = mini.get("insuremoneytype");
		//本司付款
		if("insure_type1" == sender.value){
			$miniEnable('insurancelessor_show');// 保险费(我司)
			$miniDisable("insurancelessee_show");//保险费(承租人)
			if(typeof(e) != 'string'){
				$miniDisableSetValue('insurancelessee','0');
				$miniDisableSetValue('insurancelessor','0');
				$mini("insuremoney").setValue(0);//保险费
			}
		//客户自保
		}else if("insure_type3" == sender.value){
			$miniEnable('insurancelessee_show');//保险费(承租人) 
			$('#insurancelessee_show').find('span').attr('class','mini-textbox-border');
			$miniDisable('insurancelessor_show');// 保险费(我司)
			if(typeof(e) != 'string'){
				$miniDisableSetValue('insurancelessee','0');
				$miniDisableSetValue('insurancelessor','0');
				$mini("insuremoney").setValue(0);//保险费
			}
		//不投保
		}else if("insure_type5" == sender.value){
			$miniDisable('insurancelessee_show');// 保险费(我司)
			$miniDisable('insurancelessor_show');// 保险费(承租人)
			if(typeof(e) != 'string'){
				$miniDisableSetValue('insurancelessee','0');
				$miniDisableSetValue('insurancelessor','0');
				$mini("insuremoney").setValue("0");
			}
		}
		if(typeof(e) != 'string'){
			setRawValue(e);
		}
	}
/*
 	租金支付类型 
	 	1 月付情况下‘租赁期限(月)’ = 1 * ‘还租次数(年)’
	  	2 季付情况下‘租赁期限(月)’ = 3 * ‘还租次数(年)’
	  	3 半年付情况下‘租赁期限(月)’ = 6 * ‘还租次数(年)’
	  	4 年付情况下‘租赁期限(月)’ = 12 * ‘还租次数(年)’
	  	5 双月付情况下‘租赁期限(月)’ = 2 * ‘还租次数(年)’
 */
function incomenumberyearvaluechange(e){
	var grace = $minigetinputtext("grace");//宽限期
	if(typeof(e) == 'object'){
		if(e.sender.id = 'grace'){
			setformatvalue($mini('grace'));
			if(isNaN($minigetinputtext("grace")) || Number($minigetinputtext("grace")) < 0){
				$mini('grace').setValue(0);
			}
			if(Number($minigetinputtext("grace")) > 0){
				$miniSetCombValue('periodtype',"period_type_0","期末");
			}
			grace = $minigetinputtext("grace");
		}else if(e.sender.id = 'incomenumber' && Number(grace) > 0){
			setformatvalue($mini('incomenumber'));
			if(isNaN($minigetinputtext("incomenumber")) || Number($minigetinputtext("incomenumber")) < 0){
				$mini('incomenumber').setValue(0);
			}
		}
	}
	$miniSetCombValue('incomenumberyear',$mini('incomenumberyear').getValue(),$mini('incomenumberyear').getText());	
	var sender = mini.get("incomenumberyear");//租金支付类型
	var incomenumber = $minigetinputtext("incomenumber");//还租次数
	grace = isNaN(grace) ? 0 : grace;
	incomenumber = isNaN(incomenumber) ? 0 : incomenumber;
	var resultnumber = incomenumber + grace;
	//leaseamtdatevaluechange(); 改变测算条件
	if("" == sender.value || "" == incomenumber.value) return;
	//  income_1	income_1	income_number_year	月  付
	// 	income_3	income_3	income_number_year	季  付
	// 	income_6	income_6	income_number_year	半年付
	// 	income_12	income_12	income_number_year	年  付
	// 	income_2	income_2	income_number_year	双月付
	if("income_1" == sender.value){
		$mini("leaseterm").setValue(resultnumber);
	}else if("income_3" == sender.value){
		$mini("leaseterm").setValue(resultnumber *3);
	}else if("income_6" == sender.value){
		$mini("leaseterm").setValue(resultnumber *6);
	}else if("income_12" == sender.value){
		$mini("leaseterm").setValue(resultnumber *12);
	}else if("income_2" == sender.value){
		$mini("leaseterm").setValue(resultnumber *2);
	}
	//setRawValue(e);
	
	leaseamtdatevaluechange('1');
}
function periodtypevaluechange(e){
	$miniSetCombValue(e.sender.id,e.sender.value,e.sender.text);
	if(e.sender.value == 'period_type_1'){
		var grace = mini.get('grace').getValue();
		if(Number(grace) > 0 ){
			mini.get('grace').setValue(0);
			incomenumberyearvaluechange('e');
		}
		$miniDisable('grace');
	}else{
		if(mini.get('rentorrate').getValue() != 'knowing_corpus'){
			$miniEnable('grace');
		}
	}
	leaseamtdatevaluechange('1');
}
function setRawValue(e){
	if(null == e )return;
	var cbb=e.sender;
    mini.get("rawValue_"+cbb.name).setValue(cbb.text);
}
//租金推算方法下拉改变事件
function rentorratevaluechange(e){
	if(typeof(e) != "string"){
		$miniSetCombValue(e.sender.id,e.sender.value,e.sender.text);
	}
	var rentorratevalue = $minigetvalue("rentorrate");
	if("rate" == rentorratevalue){
		$("#testrent").hide();
		$("#testrate").show();
	}else{
		$("#testrate").hide();
		$("#testrent").show();
	} 
	
	 //如果是按租金计算年租息率或者已知租金规则测算情况下
	if("knowing_corpus" == rentorratevalue || "knowing_rent" == rentorratevalue){
		var grace = $minigetinputtext('grace');
		if(Number(grace) > 0){
			mini.get('grace').setValue(0);
			incomenumberyearvaluechange('e');
		}
		$miniDisable('grace');
	}else{
		$miniEnable('grace');
	}
	 if(typeof(e) != "string"){
		$mini("yearrate").setValue('0');
		$mini("rentorratevalue").setValue('0');
		if("rent" == rentorratevalue || "knowing_rent" == rentorratevalue){
			$miniSetCombValue("ratefloattype","fixed","固定利率");
			$miniDisable("ratefloattype");
			$mini("ratefloatamt").disable();//利率调整值
			$mini("baserate").disable();//基准利率
			//年租息率清空
			if("knowing_rent" == rentorratevalue){
			}else{
			}
		}else{
			$miniEnable("ratefloattype");//利率计算方式
			if(mini.get('ratefloattype').getValue() != 'fixed'){
				$miniEnable("ratefloatamt");//利率调整值
				$miniEnable("baserate") ;//基准利率
			}else{
				$miniDisable("ratefloatamt");//利率调整值
				$miniDisable("baserate") ;//基准利率
			}
		} 
	 }
	//动态改变租金计算方式
	if(rentorratevalue != "knowing_rent"){
		if($minigetvalue('settlemethod') == "irregular_rent"){
			//$miniSetCombValue("settlemethod","even_rent","等额租金");
		}
	}else{
		$miniSetCombValue("settlemethod","irregular_rent","不规则");
	}
	if(!'${requestScope["ratefloattype"]}'){
		$miniSetCombValue("ratefloattype","fixed","固定利率");
	}
	
	if(typeof(e) != "string"){
		changeRentPlanTableButton('1');
	}
	else
	{
		$miniDisable("yearrate");
	}
}
//租金推算方法下拉改变事件
function rentorrateitemchange(e){
	var rentorratevalue = $minigetvalue("rentorrate");//租金支付类型
	if("knowing_rent" == rentorratevalue){
		$miniSetCombValue("settlemethod","irregular_rent","不规则");
		if ($minigetvalue("equipamt") && getNumber('equipamt') > 0) {
			$mini("rentorratevalue").setValue("0");
			var editWindow = mini.get("editWindow");
			editWindow.show();
	    }else{
	   	 	mini.alert("请填写设备款！");
		}
	}else if("knowing_corpus" == rentorratevalue){
		
		$miniSetCombValue("settlemethod","even_corpus","等额本金");
		if (getNumber("cleanleasemoney") <= 0 ) {
	   	 	mini.alert("请填写设备款！");
	    }
		else{
			$mini("rentorratevalue").setValue("0");
			$('#testrent').hide();
			$('#testrate').show();
			var editWindow = mini.get("editWindow_knowing_corpus");
			editWindow.show();
		}
	}
}

function checkKnowingRentList(rentList){
	var length = rentList?rentList.length:0;
	if(length > 0){
		var total = 0;
		var maxNum = 0;
		var minNum = 2;
		for (var i = 0; i < length; i++) {
			var rowData = rentList[i];
			var startNum = rowData["startrentlist"] * 1;
			var endNum = rowData["endrentlist"] * 1;
			for (var j = i + 1; j < length; j++) {
				var rowData2 = rentList[j];
				var startNum2 = rowData2["startrentlist"] * 1;
				var endNum2 = rowData2["endrentlist"] * 1;
				if(!(startNum2 > endNum || endNum2 < startNum)){//结束期项大于等于开始期项，且和已有期项无交集
					mini.alert("租金计划有期项重复或者残缺!");
					return 0;
				}
			}
			total += (endNum - startNum + 1);
			minNum = minNum > startNum ? startNum : minNum;
			maxNum = maxNum > endNum ? maxNum : endNum;
		}
		if(!maxNum || !minNum || minNum != 1 || maxNum != total){
			mini.alert("租金计划有期项重复或者残缺!");
			return 0;
		} else {
			return total;
		}
	} else {
		mini.alert("在已知租金法情况下，必须按要求填写对应租金及期项值，请选择[已知租金法]重新设置租金计划列表!");
		return 0;
	}
}
function checkKnowingCorpusList(rentList){
	var length = rentList?rentList.length:0;
	if(length > 0){
		var total = 0;
		var maxNum = 0;
		var minNum = 2;
		for (var i = 0; i < length; i++) {
			var rowData = rentList[i];
			var startNum = rowData["startcorpuslist"] * 1;
			var endNum = rowData["endcorpuslist"] * 1;
			for (var j = i + 1; j < length; j++) {
				var rowData2 = rentList[j];
				var startNum2 = rowData2["startcorpuslist"] * 1;
				var endNum2 = rowData2["endcorpuslist"] * 1;
				if(!(startNum2 > endNum || endNum2 < startNum)){//结束期项大于等于开始期项，且和已有期项无交集
					mini.alert("租金计划有期项重复或者残缺!");
					return 0;
				}
			}
			total += (endNum - startNum + 1);
			minNum = minNum > startNum ? startNum : minNum;
			maxNum = maxNum > endNum ? maxNum : endNum;
		}
		if(!maxNum || !minNum || minNum != 1 || maxNum != total){
			mini.alert("租金计划有期项重复或者残缺!");
			return 0;
		} else {
			return total;
		}
	} else {
		mini.alert("在已知租金法情况下，必须按要求填写对应租金及期项值，请选择[已知租金法]重新设置租金计划列表!");
		return 0;
	}
}
/*
 *	付款日：
 	付款日放在起租日前面
 	付款日选择后触发事件计算：起租日，第一期租金支付日，第二期租金支付日 三个值
 	计算逻辑：第一期租金支付日不能小于起租日期，第二期租金支付日不能小于第一期租金支付日!
	1.起租日，第一期租金支付日，第二期租金支付日 三个值都不存在值则被重新计算
	2.三个值都为空情况下：
		起租日 = 付款日
		第一期租金支付日：if '期初/期末' = q期初，则=起租日，else = 起租日 + 租金支付类型（同上方3的数字:1/3/6/12/2）月份
		第二期租金支付日：= 第一期租金支付日 +  + 租金支付类型（同上方3的数字:1/3/6/12/2）的数字:1/3/6/12)月份 
		
 */
function leaseamtdatevaluechange(e){
	//if("" == e.value) return;
	var sender = mini.get("leaseamtdate");
	if(!sender.value){
		return;
	}
	//  income_1	income_1	income_number_year	月  付
	// 	income_3	income_3	income_number_year	季  付
	// 	income_6	income_6	income_number_year	半年付
	// 	income_12	income_12	income_number_year	年  付
	// 	income_2	income_2	income_number_year	双月付
	var incomeNumber = mini.get('incomenumber').getValue();
	var startdate = $minigetvalue("startdate");//起租日
	var firstplandate = $minigetvalue("firstplandate");//第一期租金支付日
	var secondplandate = $minigetvalue("secondplandate");//第二期租金支付日
	var lastplandate = $minigetvalue("lastplandate");//第二期租金支付日
	var periodtype = $minigetvalue("periodtype");//期初/期末
	var incomenumberyear = $minigetvalue("incomenumberyear");//租金支付类型
	if(typeof(e) == 'string' || ("" == startdate && "" == firstplandate && "" == secondplandate && "" == lastplandate)){
		$mini("startdate").setValue(sender.value);
		var changedate;
		var tempValue = parseInt(incomenumberyear.replace("income_",""));
		if("period_type_1" == periodtype){
			$mini("firstplandate").setValue(sender.value);
			changedate=sender.value;
		}else{
			changedate = DateUtil.dateAdd('m',tempValue,sender.value);
			$mini("firstplandate").setValue(changedate);
		}
		$mini("firstplandate").setMinDate($minigetvalue("startdate"));
		$mini("secondplandate").setMinDate($minigetvalue("firstplandate"));
		$mini("secondplandate").setValue(dateAdd('m',tempValue,changedate));
		if(incomeNumber > 0 ){
			var leaseterm =  $minigetvalue('leaseterm');
			var tempValue = parseInt(incomenumberyear.replace("income_",""));
			$mini("lastplandate").setValue(dateAdd('m',Number(leaseterm) - Number(tempValue),changedate));
		}else{
			$mini("lastplandate").setValue($mini("secondplandate").getValue());
		}
		$mini("lastplandate").setMaxDate($mini("lastplandate").getValue());
		$mini("lastplandate").setMinDate(dateAdd('m',-1,$mini("lastplandate").getValue())); 
	}
	calChangTab();//租前息改变方法
}
//点击租金测算
function save(e){
	if(mini.get("settlemethod").getValue()=='special_regular'){
		var special_regular=mini.get("special_regular").getData();
		if(special_regular.length==0){
			mini.alert('请先填写分段规则！');
			return ;
		}else{
			//给还租次数 租赁期限赋值
			var month=0;
			var count=0;
			for(var i=0;i<special_regular.length;i++){
				month=parseInt(month)+(parseInt(special_regular[i].endlist)-parseInt(special_regular[i].startlist)+1)*parseInt(special_regular[i].regular_months);
				count=(parseInt(count)<parseInt(special_regular[i].endlist))?parseInt(special_regular[i].endlist):parseInt(count);
			}
			mini.get("leaseterm").setValue(month);
			mini.get("incomenumber").setValue(count);
		}
	}
	//判断设备款是否大于0
	var equipamt =  $minigetvalue("equipamt");
	if(equipamt){
		if(Number(equipamt) <= 0 ){
			mini.alert('设备款需大于0！！！');
			return;
		}
	}else{
		mini.alert('设备款不能为空！！！');
		return;
	}
	//判断还租次数,年租息率 ，起租日是否为空，以及还租次数、年租息率格式是否正确
	var incomenumber = $minigetvalue("incomenumber");
	var numRegex  = /^[-\+]?\d+$/;
	var rateRegex = /^[-\+]?\d+(\.[0-9]{1,6})?$/;
	var yearrate = $minigetvalue("yearrate");

	
	var leaseamtdate = $minigetvalue("leaseamtdate");
	if(!leaseamtdate){
		mini.alert('请先输入付款日！！！');
		return;
	}
	
	var startdate = $minigetvalue("startdate");
	if(!startdate){
		mini.alert('请先输入起租日！！！');
		return;
	}
	
	mini.mask({
		el: document.body,
		cls: 'mini-mask-loading',
		html: '正在测算中，请稍后...'
	});
	var o = businessForm.getData(true,true);
	var fields = businessForm.getFields();
	var resultData = [];
	$.extend(resultData,fields);
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].uiCls == "mini-textbox"){
			if(fields[index].getInputText().indexOf(',')!=-1){
				//所有textbox去掉逗号,
				resultData[index].setValue(fields[index].getInputText().replace(/,/g,""));
			}else if(!fields[index].getInputText() && (fields[index].vtype == 'double' || fields[index].vtype == 'int')){
				resultData[index].setValue('0');
			}
		}
	}
    o = businessForm.getData(true,true);//把textbox去掉,逗号之后，再次获取form
    if(typeof(e) == 'string'){
    	//说明是租金调整
    	o.reckontype = e;
    }
    
    o.json_knowing_corpus_plan_str = $('#id_json_knowing_corpus_plan_str').val();
    var docid = mini.get('conditionDocId').getValue();
    if(!docid){
	    try{
		    o.docid = mini.get('cust_doc_id').getValue();
	    }catch(e){
	    	
	    }
    }
    //将特殊规则添加到隐藏域
    jQuery("#id_json_special_regular_str").val(mini.encode(mini.get("special_regular").getData()));
    o.json_special_regular_str = $('#id_json_special_regular_str').val();
    o.json_fund_rent_plan_str  = $('#id_json_fund_rent_plan_str').val();
    var url="<%=request.getContextPath() %>/leasing/rentCalculate.action";
	$.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	var result = mini.decode(text);
        	if(result.rentcalculaters == 'yes'){
            	$mini("enddate").setValue(result.enddate);
            	$mini("cleancreditmoney").setValue(result.cleancreditmoney);//授信额度
            	$mini("firstpaymenttotal").setValue(result.firstpaymenttotal);//计算期初付款总计
            	var settlemethod =  mini.get('settlemethod').getValue();
            	$mini("yearrate").setValue(decimal(result.yearrate, 6));//年租息率
            	$mini("yearrate_helper").setValue(decimal(result.yearrate, 6));//年租息率辅助字段
            	$mini("cleancreditratio").setValue(decimal(result.cleancreditratio, 6));//授信比例
            	var fundrentplan =result.rentplanlist ;
            	
            	mini.get("fund_rent_plan_frame").setData(fundrentplan);
            	//分别把租金计划  、现金流 、资金收付计划保存至隐藏域
            	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
            	if(!docid){//说明不在流程中 的客户报价和租金计算器
	            	try{
		            	$mini("cust_doc_id").setValue(result.docid)
	            	}catch(e){
	            		
	            	}
            	}
            	updateInputThousand();
            	mini.unmask(document.body);
            	mini.alert('测算成功！');
            	updateInputThousand();
        	}else if(result.rentcalculaters.toLowerCase() == 'rentsmall'){
        		mini.alert('已知租金太小，请调整租金后再测算！');
        		updateInputThousand();
        		mini.unmask(document.body);
        	}
        	else {
            	mini.unmask(document.body);
        		mini.alert(result.rentcalculatemsg);
            	updateInputThousand();
        	}
        	
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	mini.unmask(document.body);
            alert(jqXHR.responseText);
        }
    });
}

//刷新页面当中的所有的输入框的钱数为千分位
function updateInputThousand(){
	var fields = businessForm.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].vtype == 'double'){
			fields[index].setValue(formatNumberThousand(fields[index].getValue()));
		}
	}
}
//测算利率改变事件
function rentorratevaluekeyup(e){
	var id = $('#itemTd').find('span:visible')[0].id;
	if(id == "testrate"){
		e.sender.vtype = 'rate';
	}
	setformatvalue(e.sender);
	var rentorrateinputvalue = $minigetinputtext("rentorratevalue");
	var ratefloattypevalue = $minigetvalue("ratefloattype");
	var rentorratevalue = $minigetvalue("rentorrate");
	if("fixed" == ratefloattypevalue && ("rate" == rentorratevalue || "knowing_corpus" == rentorratevalue)){
		$mini("yearrate").setValue(rentorrateinputvalue);
	}
}

//combobx懒加载
function onbeforeshowpopup(e) {	
	miniui_ext.onbeforeshowpopup(e);
}
//计算期初付款总计
function firstpaymenttotal(e){
	//首付款 +保证金 +手续费 +管理费 +其他收入 + 期初第1期租金 + 厂商返利 + 管理费
	//无期初第1期租金 管理费重复
	var firstpayment = $minigetinputtext("firstpayment");//首付款
	var cautionmoney = $minigetinputtext("cautionmoney");//保证金 
	var managementmoney = $minigetinputtext("managementmoney");//管理费
	var handlingchargemoney = $minigetinputtext("handlingchargemoney");//手续费
	var otherincome = $minigetinputtext("otherincome");//其他收入 
	var returnamt = $minigetinputtext("returnamt");//厂商返利
	$mini("firstpaymenttotal").setValue(firstpayment + cautionmoney + managementmoney 
				+ handlingchargemoney + otherincome +otherincome);
}
//计算融资额
function cleanleasemoney(e){
	var equipamt = $minigetinputtext("equipamt");//设备款
	var firstpayment = $minigetinputtext("firstpayment");//首付款
	var otherleasemoney = $minigetinputtext("otherleasemoney");//其他融资费用
	var cleanleasemoney = mini.get("cleanleasemoney");
	var cleanleasemoneyvalue = decimal(equipamt - firstpayment+otherleasemoney,2);
	cleanleasemoney.setValue(cleanleasemoneyvalue);
	$setThouValue('cleanleasemoney');
}

//计算风险敞口 
function cleancreditmoney(e){
	//设备款－首付款－保证金-其他收入+其他支出-前收第1期租金+保险费-厂商返利-手续费 - 管理费
	//无前收第1期租金
	debugger;
	var equipamt = $minigetinputtext("equipamt");//设备款
	var firstpayment = $minigetinputtext("firstpayment");//首付款
	var cautionmoney = $minigetinputtext("cautionmoney");//保证金 
	var otherincome = $minigetinputtext("otherincome");//其他收入 
	var otherexpenditure = $minigetinputtext("otherexpenditure");//其他支出
	var insuremoney = $minigetinputtext("insuremoney");//保险费
	var returnamt = $minigetinputtext("returnamt");//厂商返利
	var handlingchargemoney = $minigetinputtext("handlingchargemoney");//手续费
	var managementmoney = $minigetinputtext("managementmoney");//管理费
	$mini("cleancreditmoney").setValue(equipamt - firstpayment - cautionmoney - 
			otherincome + otherexpenditure + insuremoney - returnamt - handlingchargemoney - managementmoney);
	setformatvalue($mini("cleancreditmoney"));
}
//计算手续费
function changehandlingchargemoneyratio(e){
	var equipamt = getNumber("equipamt");//设备款
	setformatvalue($mini("handlingchargemoneyratio"));
	var handlingchargemoney = mini.get("handlingchargemoney");//手续费
	var handlingchargemoneyratio = mini.get("handlingchargemoneyratio").getInputText().replace(/,/g,"");//手续费比例
	var handlingchargemoneyvalue =!equipamt || Number(equipamt)<=0 ?  0 : decimal(handlingchargemoneyratio*equipamt/100, 6);
	handlingchargemoney.setValue(handlingchargemoneyvalue);
}
//计算管理费比例
function changemanagementmoneyratio(e){
	setformatvalue($mini("managementmoney"));
	var equipamt = getNumber("equipamt");//设备款
	var managementmoney = mini.get("managementmoney");//管理费
	var managementmoneyratio = mini.get("managementmoneyratio").getInputText().replace(/,/g,"");//管理费比例
	
	var managementmoneyvalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(managementmoneyratio*equipamt/100, 6);
	managementmoney.setValue(managementmoneyvalue);
}
function changeassetsratio(e){
	setformatvalue($mini("assetsmoney"));
	var equipamt = getNumber("equipamt");//设备款
	var assetsmoney = mini.get("assetsmoney");//管理费
	var assetsratio = mini.get("assetsratio").getInputText().replace(/,/g,"");//管理费比例
	
	var managementmoneyvalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(assetsratio*equipamt/100, 6);
	assetsmoney.setValue(managementmoneyvalue);
}
function changeadviserratio(e){
	setformatvalue($mini("assetsmoney"));
	var equipamt = getNumber("equipamt");//设备款
	var advisermoney = mini.get("advisermoney");//管理费
	var adviserratio = mini.get("adviserratio").getInputText().replace(/,/g,"");//管理费比例
	
	var managementmoneyvalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(adviserratio*equipamt/100, 6);
	advisermoney.setValue(managementmoneyvalue);
}
//计算保证金
function changecautiondeductionratio(e){
	var cautiondeductionratio = "cautiondeductionratio";//风险金比例
	var cautionmoney = "cautionmoney";//风险金
	var cautiondeductionmoney = "cautiondeductionmoney";//风险金抵扣金额
	var cautionmoneyremain = "cautionmoneyremain";//风险金退还
	setformatvalue($mini(cautiondeductionratio));
	var equipamt = getNumber("equipamt");//设备款
	var cautiondeductionratio = mini.get(cautiondeductionratio).getInputText().replace(/,/g,"");
	var cautionmoney = mini.get(cautionmoney);//保证金
	var cautiondeductionvalue =!equipamt || Number(equipamt)<=0 ?  0 : decimal(cautiondeductionratio*equipamt/100, 6);
	cautionmoney.setValue(cautiondeductionvalue);
	//如果保证金发生改变的话，默认是将保证金抵扣金额赋值为和保证金金额相等
	mini.get(cautiondeductionmoney).setValue(!cautionmoney.getInputText().replace(/,/g,"") ? 0 : cautionmoney.getInputText().replace(/,/g,""));
	mini.get(cautionmoneyremain).setValue(0);
}
//根据待投保年费率计算投保收入
function changeinsurerateratio(e){
	setformatvalue(mini.get("insurerate"));
	var equipamt = getNumber("equipamt");//设备款
	var insurerate = mini.get("insurerate").getValue();
	var insureincome=!equipamt || Number(equipamt)<=0 ?  0 : decimal(insurerate*equipamt/100, 2);
	mini.get("insureincome").setValue(!insurerate?0:insureincome);
	setformatvalue(mini.get("insureincome"));
}
//根据投保成本年费率计算投保支出
function changeinsurecostrateratio(e){
	setformatvalue(mini.get("insurecostrate"));
	var equipamt = getNumber("equipamt");//设备款
	var insurecostrate = mini.get("insurecostrate").getValue();
	var insureexpenditure=!equipamt || Number(equipamt)<=0 ?  0 : decimal(insurecostrate*equipamt/100, 2);
	mini.get("insureexpenditure").setValue(!insureexpenditure?0:insureexpenditure);
	setformatvalue(mini.get("insureexpenditure"));
}
function checkcode(e){
	//IE 中 Event 对象使用 keyCode 获得键入字符的 Unicode 编码  
    //DOM 中 Event 对象 使用 charCode 获得键入字符的 Unicode 编码  
    var char_code = e.htmlEvent.charCode ? e.htmlEvent.charCode : e.htmlEvent.keyCode;  
    //Unicode 编码中， 0~9 的编码的十进制 是 48~57 之间 ， 0为 48， 9 为57  
    if(char_code<48 || char_code >57){  
        alert("只允许输入数字");     
        return false;  
    }  
    else{  
        return true;      
    }
}
//计算设备款
function changeequipamtvalue(e){
	
	//var code = checkcode(e);
	setformatvalue($mini("equipamt"));
	changefirstpayment();//计算首付款
	changecautiondeductionratio('');//计算保证金比例
	changecautiondeductionratio('fac');//计算保证金比例
	changemanagementmoneyratio();//计算管理费比例
	changehandlingchargemoneyratio();//计算手续费比例
	changeassetsratio();
	changeadviserratio();
	//cleancreditmoney();//风险敞口 生成资金计划计算
	cleanleasemoney();//融资额
	//changecleancreditratio();//计算净授信比例  生成资金计划计算
}

//计算首付款
function changefirstpayment(e){
	setformatvalue($mini("firstpaymentratio"));
	var equipamt = getNumber("equipamt");//设备款
	var firstpaymentratio = $minigetinputtext("firstpaymentratio");//首付比例
	
	var firstpayment = mini.get("firstpayment");//首付款
	var firstpaymentvalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(firstpaymentratio*equipamt/100, 6);
	firstpayment.setValue(firstpaymentvalue);
	cleanleasemoney();//融资额
}
//计算净授信比例 
function changecleancreditratio(e){
	setformatvalue($mini("cleancreditmoney"));
	var equipamt = getNumber("equipamt");//设备款
	var cleancreditmoney = $minigetinputtext("cleancreditmoney");//风险敞口
	var cleancreditratio = mini.get("cleancreditratio");//风险敞口比例
	var cleancreditratiovalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(cleancreditmoney/equipamt*100, 6);
	cleancreditratio.setValue(cleancreditratiovalue);
}

//计算保证金退还金额
function cautionmoneyremainvaluechange(e){
	var cautiondeductionratio = "cautiondeductionratio";
	var cautionmoney = "cautionmoney";
	var cautiondeductionmoney = "cautiondeductionmoney";
	var cautionmoneyremain = "cautionmoneyremain";
	var fac = "";
	if(e.sender.id != 'cautiondeductionmoney'){
		cautiondeductionratio = "faccautiondeductionratio";
		cautionmoney = "faccautionmoney";
		cautiondeductionmoney = "faccautiondeductionmoney";
		cautionmoneyremain = "faccautionmoneyremain";
		fac = "厂商";
	}
	
	var cautionmoneyM = $minigetinputtext(cautionmoney);//保证金
	var cautiondeductionmoneyM = $minigetinputtext(cautiondeductionmoney);//保证金抵扣金额
	var cautionmoneyremainField = mini.get(cautionmoneyremain);//保证金退还金额
// 	if("" == cautionmoney || "" == cautiondeductionmoney){
// 		cautionmoneyremain.setValue("");
// 		return;
// 	}
	if(!isNumber(cautiondeductionmoneyM)){
		mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
		cautionmoneyremainField.setValue('0');
		return;
	}else{
		if(Number(cautiondeductionmoneyM) < 0 ){
			//mini.alert(fac+'保证金退还金额需大于0！');
			mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
			cautionmoneyremainField.setValue('0');
			return;
		}
		if(Number(cautiondeductionmoneyM) > Number(cautionmoneyM) ){
			//mini.alert(fac+'保证金退还金额需小于等于'+fac+'保证金金额');
			mini.get(cautiondeductionmoney).setValue(cautionmoneyM);
			setformatvalue($mini(cautiondeductionmoney));
			cautionmoneyremainField.setValue('0');
			return;
		}
	}
	var cautionmoneyremainvalue = decimal(cautionmoneyM - cautiondeductionmoneyM,2);
	cautionmoneyremainField.setValue(cautionmoneyremainvalue);
	setformatvalue($mini(cautionmoney));
	setformatvalue($mini(cautiondeductionmoney));
	setformatvalue($mini(cautionmoneyremain));
}
//计算保险费
function insuremoneyvaluechange(e){
	setformatvalue($mini("insurancelessee_show"));
	setformatvalue($mini("insurancelessor_show"));
	setformatvalue($mini("insuremoney"));
	var insurancelessee = getNumber("insurancelessee_show");//承租人
	var insurancelessor = getNumber("insurancelessor_show");//我司
	$('#insurancelessee').val(insurancelessee);
	$('#insurancelessor').val(insurancelessor); 
	if(e.sender.id == 'insurancelessor_show'){
		var insuremoney = mini.get("insuremoney");//保证金退还金额
		var insuremoneyvalue = insurancelessee + insurancelessor;
		insuremoney.setValue(insuremoneyvalue);
	}
}

function isNumber(moneyText){
	var rateRegex = /^[-\+]?\d+(\.\d+)?$/;
	return rateRegex.test(moneyText);
}
function changeRentPlanTableButton(e){
	var table = mini.get('fund_rent_plan_frame');
	var data = mini.get('fund_rent_plan_frame').getData();
	$('#id_fund_rent_plan_frame').html('');
	mini.parse();
	if(typeof(e) != "string"){
		$miniSetCombValue("settlemethod",e.sender.value,e.sender.text);
	}
	var settlemethod = mini.get('settlemethod').getValue();
	if(settlemethod == 'irregular_rent'){
		tempColume.tools =toolsIrregular ;
		$miniSetCombValue('rentorrate','rate','按年利率计算租金');
		$miniSetCombValue('ratefloattype','fixed','固定利率');
		
		$miniDisable("ratefloattype");
		$miniDisable("ratefloatamt");
		$miniDisable("baserate");
		$miniEnable("yearrate");
		$mini("ratefloatamt").setValue("0");
		$mini("baserate").setValue("0");
		$mini("yearrate").setValue("0");
		$mini("rentorratevalue").setValue("0");
		$('#testrent').hide();
		$('#testrate').show();
		if(typeof(e) != "string"){
			rentorrateitemchange();
		}
		mini.get("div_special_regular").hide();
	}else{
		tempColume.tools =toolsSpecial ;
		mini.get("div_special_regular").show();
		$miniSetCombValue('rentorrate','rate','按年利率计算租金');
		$('#calculateButton').show();
		//再去判断是否是按租金推算年租息率
		if(mini.get('rentorrate').getValue() == 'rent'){
			$miniSetCombValue('ratefloattype','fixed','固定利率');
			$miniDisable("ratefloattype");
			$miniDisable("ratefloatamt");
			$miniDisable("baserate");
			$mini("ratefloatamt").setValue("0");
			$mini("baserate").setValue("0");
			mini.get('rentorratevalue').setValue('0');
			$mini("yearrate").setValue("0");
			$('#testrent').show();
			$('#testrate').hide();
		}else{
			$('#testrent').hide();
			$('#testrate').show();
			$miniEnable("ratefloattype");
			if($minigetvalue("ratefloattype") != "fixed"){
				$miniEnable("ratefloatamt");
				$miniEnable("baserate");
			}else{
				$miniDisable("ratefloatamt");
				$miniDisable("baserate");
				$mini("ratefloatamt").setValue("0");
				$mini("baserate").setValue("0");
			}
		}
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable(tempColume);
	});
	tempColume = mini.clone(tempColume2);
}
function changeInputToThoud(e){
	setformatvalue(e.sender);
}
function $setThouValue(id){
	setformatvalue(mini.get(id));
}

function checkInputIntCompareToZero(e){
	setformatvalue(e.sender);
	if(isNaN(e.sender.value) || Number(e.sender.value) < 0){
		e.sender.setValue(0);
	}
}

function $miniEnable(id){
	var miniObj = mini.get(id);
	miniObj.enable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-textbox'){
		jQueryObj.find('.mini-textbox-border').attr("class", "mini-textbox-border");
	}else if(uiCl == 'mini-combobox'){
		jQueryObj.find('.mini-textbox-border').attr("style", "");
		jQueryObj.find('.mini-buttonedit-border').attr("style", "");
		jQueryObj.find(".mini-buttonedit-button").css("display", "block");
	}
}
function $miniDisable(id){
	var miniObj = mini.get(id);
	miniObj.disable();
	var uiCl = miniObj.uiCls;
	var jQueryObj = $('#'+id);
	if(uiCl == 'mini-combobox'){
		jQueryObj.find(".mini-buttonedit-button").css("display", "none");
	}
}
function $miniSetCombValue(id,value,text){
	var miniObj = mini.get(id);
	miniObj.setValue(value);
	miniObj.setText(text);
	var miniHiddenObj = mini.get("rawValue_"+id).setValue(text);
}

function getNumber(id){
    var value = mini.get(id).getValue();
    try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function decimalReal(num,v)
{
   var dight  =(num*Math.pow(10,v)/Math.pow(10,v)).toFixed(v);  
   return parseFloat(dight);
} 
//四舍五入
function decimal(num,v)
{	//num表示要四舍五入的数,v表示要保留的小数位数。
	if(0 == v)
	{
		return decimalReal(decimalReal(num,2),0);
	}
    return decimalReal(num,v);		
}
function $minigetvalue(id){
	return mini.get(id).getValue();
}
function $mini(id){
	return mini.get(id);
}
function $minigetinputtext(id){
	var value = mini.get(id).getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function startReloadConditionContent(){
	var settlemethod = $minigetvalue('settlemethod') || "${empty requestScope['settlemethod'] ? 'even_rent' : requestScope['settlemethod'] }";
	if(settlemethod == 'irregular_rent'){
		jQuery('#calculateType').find('td[displayid="rentorratevaluetd"]').hide();
		$miniSetCombValue("rentorrate","knowing_rent","已知租金规则测算");
		$miniSetCombValue("settlemethod","irregular_rent","不规则");
		$('#testrent').hide();
		$('#testrate').show();
	}
	updateInputThousand();//刷新页面当中的所有金额为千分位
	rentorratevaluechange('start');//年租息率只读
	ratefloattypevaluechange('start');//固定利率设置只读
	insuremoneytypevaluechange('start');//保险计算方式	
}
function calculateBeforeInterest(e){
	var fundChargeList ;
	var startDate = mini.get('startdate').getFormValue();
	var yearRate = mini.get('yearrate').getValue();
	if(typeof(e) == 'object'){
		var miniTable = mini.get('condition_beforeInterest');
		if(!startDate){
			mini.alert('请先选择起租日！！！！');
			//mini.get('beforeInterestContainer').hide();
			return;
		}
		fundChargeList =  miniTable.getData();
	}else{
		fundChargeList = mini.decode('${empty json_fund_plan_equipamt_str ? "[]" : json_fund_plan_equipamt_str }')
	}
	var beforeInterest = 0;
	for(var i = 0 ; i < fundChargeList.length ; i++){
		var fundCharge = fundChargeList[i];
		var betweenDays = Number(startDate.toDate('yyyy-MM-dd') - fundCharge.plandate.toDate('yyyy-MM-dd'))/(24 * 60 *60 *1000);
		var beforeInterestBranch = decimal(betweenDays/365*Number(fundCharge.factmoney)*Number(yearRate)/100,2); 
		beforeInterest += beforeInterestBranch;
	}
	beforeInterest = decimal(beforeInterest,2);
	mini.get('beforeinterest').setValue(beforeInterest);
	mini.get('beforeinterest').setText(beforeInterest);
	mini.get('beforeInterestContainer').hide();
}
function setformatvalue(m){
	var pMoneyOri = m.getInputText().trim();
	var pMoney = pMoneyOri.replace(/,/gi,"");
	var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,6})?$/;
	if((pMoneyOri && pMoneyOri.length>=0) && !pMoneyReg.test(pMoney)){
		pMoney = 0;
		m.setValue();
		m.setValue('0');
	}else{
		//把非数字的字符格式化成空格、并千分位
		m.setValue(formatNumberThousand(pMoney));
	}
}
</script>