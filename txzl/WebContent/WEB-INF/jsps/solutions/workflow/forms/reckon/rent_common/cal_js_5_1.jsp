<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<jsp:include page="cal_js_5_1_common.jsp"></jsp:include>
<script type="text/javascript">
var changeEventArray = [
	{'id':'equipamt',
	  'onkeyup':changeequipamtvalue,
	}, 
	{
		'id':'equipendvalue',
		'onkeyup':changeInputToThoud,
	}, 
	{
		'id':'firstpayment',
		'onkeyup':changefirstpaymentratio,
		'onvaluechanged':changefirstpaymentratio
	},
	{
		'id':'handhz',
		'onvaluechanged':changeHandhz
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
	{
		'id':'graceadjust',
		'onvaluechanged':yearratekeyup
	},
	{
		'id':'periodtype',
		'onvaluechanged':periodtypevaluechange
	},
	{
		'id':'insureratio',
		'onvaluechanged':insureratiovaluechange
	},
	{
		'id':'incomenumberyear',
		'onvaluechanged':incomenumberyearvaluechange
	},
	{
		'id':'incomenumber',
		'onvaluechanged':incomenumberyearvaluechange
	},
	{
		'id':'grace',
		'onvaluechanged':incomenumberyearvaluechange
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
		'id':'handlingchargemoney',
		'onkeyup':changehandlingchargemoneyratio,
		'onvaluechanged':changehandlingchargemoneyratio
	},
	{
		'id':'managementmoney',
		'onkeyup':changemanagementmoneyratio,
		'onvaluechanged':changemanagementmoneyratio
	},
	{
		'id':'advancerepaymoney',
		'onkeyup':advancerepaymoney,
		'onvaluechanged':advancerepaymoney
	},
	{
		'id':'cautionmoney',
		'onkeyup':changecautiondeductionratio,
		'onvaluechanged':changecautiondeductionratio
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
	var settlemethod = mini.get('settlemethod').getValue();
	if(settlemethod == 'excel_import'){
		$('#calculateButton').hide();
	}else{
		$('#calculateButton').show();
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
}
//根据利率计算方式改变年租息率
function yearratekeyup(e){
	setformatvalue(e.sender)	
	var ratefloattypevalue = $minigetvalue("ratefloattype");
	var ratefloatamtbefore = getNumber("graceadjust"); //利率调整值
	var baserate = getNumber("baserate");//基准利率
	if("proportion" == ratefloattypevalue){
		$mini("gracerate").setValue(decimal((ratefloatamtbefore+1) * baserate,6));
	}else if("add" == ratefloattypevalue){
		$mini("gracerate").setValue(decimal((ratefloatamtbefore + baserate),6));
	}
}
/*
	利率计算方式：
		1 选择：固定利率 则利率调整值只读，去掉必填校验
		2 选择非‘固定利率’则利率调整值可输入，并且有必填校验
*/
function ratefloattypevaluechange(e){
	var ratefloattypevalue;
	if(typeof(e) != 'string'){
		ratefloattypevalue = $minigetvalue("ratefloattype");
	}else{
		ratefloattypevalue = "${requestScope['ratefloattype']}";
	}
	var ratefloatamtbefore = getNumber("graceadjust"); //利率调整值
	var baserate = getNumber("baserate");//基准利率
	if("fixed" == ratefloattypevalue){//固定利率
		$mini("graceadjust").disable();//利率调整值
		$mini("baserate").disable();//基准利率
		$mini("ratefloatamt").setRequired(false);
		$mini("graceadjust").setRequired(false);
		mini.get("gracerate").setReadOnly(false);
		$miniEnable("rentorratevalue");
		if(typeof(e) != 'string'){
			$mini("graceadjust").setValue("0");
			$mini("baserate").setValue("0");
			if($('#testrate').is(':visible')){
				$mini("gracerate").setValue($mini("graceadjust").getValue());
			}else{
				$mini("yearrate").setValue('0');
			}
		}
	}else if("proportion" == ratefloattypevalue){//换央行浮动比率
		$miniEnable("graceadjust");
		$miniEnable("baserate");
		$mini("graceadjust").setRequired(true);
		$mini("baserate").setRequired(true);
		$mini("rentorratevalue").disable();
		mini.get("gracerate").setReadOnly(true);
		if(typeof(e) != 'string'){
			$mini("gracerate").setValue(decimal((ratefloatamtbefore+1) * baserate,6));
			$mini("rentorratevalue").setValue("0");
		}
		$miniEnable("adjuststyle");
	}else if("add" == ratefloattypevalue){//换央行利率加点
		$miniEnable("graceadjust");
		$mini("graceadjust").setRequired(true);
		$mini("rentorratevalue").disable();
		mini.get("gracerate").setReadOnly(true);
		$miniEnable("baserate");
		if(typeof(e) != 'string'){
			$mini("rentorratevalue").setValue("0");
			$mini("gracerate").setValue(ratefloatamtbefore + baserate);
		}
		$miniEnable("adjuststyle");
	}else{//说明为空
		$mini("graceadjust").disable();//利率调整值
		$mini("baserate").disable();//基准利率
	}
	if(typeof(e) != 'string'){
		setRawValue(e);
	}else{
		if(!'${requestScope["ratefloattype"]}'){
			$miniSetCombValue("ratefloattype","fixed",'固定利率');
		}else{
			$miniSetCombValue("ratefloattype",ratefloattypevalue,"${requestScope['rawValue_ratefloattype']}");
		}
	}
}

function insureratiovaluechange(e){
	//保险费 设备款*保险费率*租赁期限/12
	if(typeof(e)=="undefined"){
		var insureratio =  mini.get("insureratio").getValue();
	}else{
		var insureratio =  e.sender.value;
	}
	var insuremoneytype = mini.get("insuremoneytype").getValue();
	var leaseterm = ('0'==mini.get("leaseterm").getValue())?'12':mini.get("leaseterm").getValue();;
	if("insure_type1" == insuremoneytype || "insure_type2" == insuremoneytype){
		var insurancelessor = decimal(getNumber('equipamt') * Number(insureratio)*Number(leaseterm)/12/10000,2);
		var insurancelessee = getNumber('insurancelessee_show');
		$miniDisableSetValue('insurancelessor',insurancelessor);
		//$mini("insuremoney").setValue(insurancelessor + insurancelessee);//保险费
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
				//$miniDisableSetValue('insurancelessor','0');
				var insurancelessor = decimal(getNumber('equipamt') * Number(mini.get('insureratio').getValue())
						*Number(mini.get("leaseterm").getValue())/12/100,2);
				$miniDisableSetValue('insurancelessor',insurancelessor);
				//$mini("insuremoney").setValue(insurancelessor);//保险费
			}else{
				if(getNumber('insurancelessor_show') == 0 ){
					var insurancelessor = decimal(getNumber('equipamt') * Number(mini.get('insureratio').getValue())
							*Number(mini.get("leaseterm").getValue())/12/100,2);
					$miniDisableSetValue('insurancelessor',insurancelessor);
					var insurancelessee = getNumber('insurancelessee_show');
					//$mini("insuremoney").setValue(insurancelessor+ insurancelessee);//保险费
				}
			}
		//代收代付
		}else if("insure_type2" == sender.value){
			$miniEnable('insurancelessor_show');// 保险费(我司)
			$miniEnable("insurancelessee_show");//保险费(承租人)
			if(typeof(e) != 'string'){
				var insurancelessor = decimal(getNumber('equipamt') * Number(mini.get('insureratio').getValue())
						*Number(mini.get("leaseterm").getValue())/12/100,2);
				var insurancelessee = getNumber('insurancelessee_show');
				$miniDisableSetValue('insurancelessor',insurancelessor);
				//$mini("insuremoney").setValue(insurancelessor + insurancelessee);//保险费
			}else{
				if(getNumber('insurancelessor_show') == 0 ){
					var insurancelessor = decimal(getNumber('equipamt') * Number(mini.get('insureratio').getValue())
							*Number(mini.get("leaseterm").getValue())/12/100,2);
					$miniDisableSetValue('insurancelessor',insurancelessor);
					var insurancelessee = getNumber('insurancelessee_show');
					//$mini("insuremoney").setValue(insurancelessor+ insurancelessee);//保险费
				}
			}
		}else if("insure_type3" == sender.value){
			$miniEnable('insurancelessee_show');//保险费(承租人) 
			$('#insurancelessee_show').find('span').attr('class','mini-textbox-border');
			$miniDisable('insurancelessor_show');// 保险费(我司)
			if(typeof(e) != 'string'){
				$miniDisableSetValue('insurancelessee','0');
				$miniDisableSetValue('insurancelessor','0');
				//$mini("insuremoney").setValue(0);//保险费
			}
		//不投保
		}else if("insure_type5" == sender.value){
			$miniDisable('insurancelessee_show');// 保险费(我司)
			$miniDisable('insurancelessor_show');// 保险费(承租人)
			if(typeof(e) != 'string'){
				$miniDisableSetValue('insurancelessee','0');
				$miniDisableSetValue('insurancelessor','0');
				//$mini("insuremoney").setValue("0");
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
				//$miniSetCombValue('periodtype',"period_type_0","期末");
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
		//$mini("leaseterm").setValue(resultnumber);
	}else if("income_3" == sender.value){
		//$mini("leaseterm").setValue(resultnumber *3);
	}else if("income_6" == sender.value){
		//$mini("leaseterm").setValue(resultnumber *6);
	}else if("income_12" == sender.value){
		//$mini("leaseterm").setValue(resultnumber *12);
	}else if("income_2" == sender.value){
		//$mini("leaseterm").setValue(resultnumber *2);
	}
	//setRawValue(e);
	
	leaseamtdatevaluechange('1');
	insureratiovaluechange();
}
function periodtypevaluechange(e){
	$miniSetCombValue(e.sender.id,e.sender.value,e.sender.text);
	/* if(e.sender.value == 'period_type_1'){
		var grace = mini.get('grace').getValue();
		if(Number(grace) > 0 ){
			mini.get('grace').setValue(0);
			incomenumberyearvaluechange('e');
		}
		//$miniDisable('grace');
	}else{
		if(mini.get('rentorrate').getValue() != 'knowing_corpus'){
			$miniEnable('grace');
		}
	} */
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
	if("rate" == rentorratevalue || "handle_method"==rentorratevalue || "r_special_regular"==rentorratevalue){
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
			$mini("graceadjust").disable();//利率调整值
			$mini("baserate").disable();//基准利率
			//年租息率清空
			if("knowing_rent" == rentorratevalue){
				$miniDisable("rentorratevalue");
			}else{
				$miniEnable("rentorratevalue");
				mini.get('rentorratevalue').setReadOnly(false);
			}
		}else{
			$miniEnable("ratefloattype");//利率计算方式
			$miniEnable("rentorratevalue");
			mini.get('rentorratevalue').setReadOnly(false);
			if(mini.get('ratefloattype').getValue() != 'fixed'){
				$miniEnable("ratefloatamt");//利率调整值
				$miniEnable("graceadjust");//利率调整值
				$miniEnable("baserate") ;//基准利率
			}else{
				$miniDisable("ratefloatamt");//利率调整值
				$miniDisable("graceadjust");//利率调整值
				$miniDisable("baserate") ;//基准利率
			}
		} 
	 }else{
		 var settlemethod =  mini.get('settlemethod').getValue();
		 var rentorrate =  mini.get('rentorrate').getValue();
		 if(settlemethod != 'excel_import'){
			$("#calculateButton").show();
			tempColume.tools =nomalTool ;
			tempColume.columns = columnsNomal;
			$miniSetCombValue("rentorrate","r_special_regular","分段推算");
			$miniSetCombValue("settlemethod","special_regular","规则测算");
			//$miniDisable("grace");
			mini.get("segment_config").show();
			$miniSetCombValue('ratefloattype','fixed','固定利率');
			
		}else{
			mini.get("segment_config").hide();
		}
	 }
	//动态改变租金计算方式
	if(rentorratevalue != "knowing_rent"){
		if($minigetvalue('settlemethod') == "irregular_rent"){
		}else if(rentorratevalue == "handle_method"){
			$miniSetCombValue("settlemethod","excel_import","不规则导入");
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
	
}
//租金推算方法下拉改变事件
function rentorrateitemchange(e){
	var rentorratevalue = $minigetvalue("rentorrate");//租金支付类型
	if("knowing_rent" == rentorratevalue){
		$miniSetCombValue("settlemethod","irregular_rent","不规则");
		if ($minigetvalue("equipamt") && getNumber('equipamt') > 0) {
			$mini("rentorratevalue").setValue("0");
			$mini("rentorratevalue").setReadOnly(true);
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
			$mini("rentorratevalue").setReadOnly(false);
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
	//  income_1	income_1	income_number_year	月  付
	// 	income_3	income_3	income_number_year	季  付
	// 	income_6	income_6	income_number_year	半年付
	// 	income_12	income_12	income_number_year	年  付
	// 	income_2	income_2	income_number_year	双月付
	changeDate();//租金支付日改变调用此方法
	var incomeNumber = mini.get('incomenumber').getValue();
	var startdate = $minigetvalue("startdate");//起租日
	var firstplandate = $minigetvalue("firstplandate");//第一期租金支付日
	var lastplandate = $minigetvalue("lastplandate");//第二期租金支付日
	var periodtype = $minigetvalue("periodtype");//期初/期末
	var incomenumberyear = $minigetvalue("incomenumberyear");//租金支付类型
	if(typeof(e) == 'string' || ("" == startdate && "" == firstplandate  && "" == lastplandate)){
/* 		var changedate;
		var sender=mini.get("leaseamtdate"); */
		//var tempValue = parseInt(incomenumberyear.replace("income_",""));
/* 		if("period_type_1" == periodtype){
			$mini("firstplandate").setValue(sender.value);
			changedate=sender.value;
		}else{
			changedate = DateUtil.dateAdd('m',tempValue,sender.value);
			$mini("firstplandate").setValue(changedate);
		} */
		$mini("firstplandate").setMinDate($minigetvalue("startdate"));
/* 		if(incomeNumber > 0 ){
			var leaseterm =  $minigetvalue('leaseterm');
			var tempValue = parseInt(incomenumberyear.replace("income_",""));
			$mini("lastplandate").setValue(dateAdd('m',Number(leaseterm) - Number(tempValue),changedate));
		} */
		$mini("lastplandate").setMaxDate($mini("lastplandate").getValue());
		$mini("lastplandate").setMinDate(dateAdd('m',-1,$mini("lastplandate").getValue())); 
	}
}
//点击租金测算
function save(e){
	formatSpecialConfig();
	debugger;
	var fund_put_config=mini.get("fund_put_config").getData();
	//如果是是特殊规则
	var special_regular="";
	if(mini.get("settlemethod").getValue()=='special_regular'){
		special_regular=mini.get("special_regular").getData();
		if(special_regular.length==0){
			mini.alert('请先填写特殊规则！');
			return ;
		}else{
			//给还租次数 租赁期限赋值
			var month=0;
			var count=0;
			for(var i=0;i<special_regular.length;i++){
				month=parseInt(month)+(parseInt(special_regular[i].endlist)-parseInt(special_regular[i].startlist)+1)*parseInt(special_regular[i].regularmonths);
				count=(parseInt(count)<parseInt(special_regular[i].endlist))?parseInt(special_regular[i].endlist):parseInt(count);
			}
			mini.get("leaseterm").setValue(month);
			mini.get("incomenumber").setValue(count);
		}
		//设备款和投放计划校验
		if(fund_put_config == null || fund_put_config.length == 0){
			mini.alert('请先填写投放计划！');
			return ;
		}else{
			var equipMoney=mini.get("equipamt").getValue().replace(/,/g,'');
			var fundputmoney=mini.get("fund_put_config").getSummaryCellEl("planmoney").innerHTML.replace(/,/g,'');
			if(parseFloat(equipMoney)!=parseFloat(fundputmoney)){
				//mini.alert("投放计划的投放总金额和设备款不一致！");
				//return ;
			}
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
	var leaseterm=mini.get("leaseterm").getValue();
	if(!leaseterm||Number(leaseterm)<=0){
		mini.alert("租赁期限(月)必须大于0！");
		return;
	}
	//判断租金支付类型，规则测算不能为不等期
	var incomenumber = $minigetvalue("incomenumber");
	
	//判断还租次数,年租息率 ，起租日是否为空，以及还租次数、年租息率格式是否正确
	var incomenumberyear = $minigetvalue("incomenumberyear");
	if(incomenumberyear == 'income_0' && '${param.process}' != 'onhire'){
		mini.alert('请选择规则的租金支付类型！！！！')
		return;
	}
	var numRegex  = /^[-\+]?\d+$/;
	if(!numRegex.test(incomenumber)){
		mini.alert('请你输入正确格式的还租次数！')
		return;
	}else{
		if(Number(incomenumber) <= 0 ){
			mini.alert('还租次数需大于0！');
			return ;
		}
	}
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
	var firstplandate = $minigetvalue("firstplandate");
	if(!firstplandate){
		mini.alert('请先输入第一期租金支付日！！！');
		return; 
	}
	//如果手续费收取间隔是随还租频率收取，并且手续费计算基数是剩余本金，则起租日和第一期租金支付日的日期保持一致
	var handhz=$minigetvalue("handhz");//手续费收取间隔
	var handmoney=$minigetvalue("handmoney");//手续费计算基数
	var leaseamtday=leaseamtdate.getDate();//付款日
	var firstplanday = firstplandate.getDate();//第一期租金支付日
	if(handhz=="hand_hz.01"&&(handmoney=="hand_ratio.02"||handmoney=="hand_ratio.03")){
		if(leaseamtday!=firstplanday){
			mini.alert('手续费收取间隔为规则收取时，第一期租金支付日期【'+firstplanday+'】要和付款日期【'+leaseamtday+'】保持一致');
			return;
		}
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
    
    $('#id_json_fund_put_config_str').val(mini.encode(fund_put_config));
    o.json_fund_rent_plan_str  = $('#id_json_fund_rent_plan_str').val();
    o.json_fund_cash_flow_str  = $('#id_json_fund_cash_flow_str').val();
    o.json_fund_fund_charge_str  = $('#id_json_fund_fund_charge_str').val();
    o.json_knowing_rent_plan_str  = $('#id_json_knowing_rent_plan_str').val();
    o.json_knowing_corpus_plan_st  = $('#id_json_knowing_corpus_plan_str').val();
    o.json_fund_put_config_str=$('#id_json_fund_put_config_str').val();
    o.json_special_regular_str=$('#id_json_special_regular_str').val();
    var url="<%=request.getContextPath() %>/leasing/rentCalculate.action";
    $.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	var result = mini.decode(text);
        	if(result.rentcalculaters == 'yes'){
        		$mini("irrshow").setValue(result.irr);//内部收益率
        		$mini("xirr").setValue(result.xirr);//内部收益率
        		$('#irr').val(result.irr);
            	$mini("enddate").setValue(result.enddate);//内部收益率
            	$mini("grace").setValue(result.grace);//宽限期期数
            	$mini("grossprofit").setValue(result.grossprofit);//项目粗利
            	$mini("cleancreditmoney").setValue(result.cleancreditmoney);//授信额度
            	$mini("firstpaymenttotal").setValue(result.firstpaymenttotal);//计算期初付款总计
            	var settlemethod =  mini.get('settlemethod').getValue();
            	$mini("cleancreditratio").setValue(decimal(result.cleancreditratio, 6));//授信比例
            	var fundrentplan =result.rentplanlist ;
            	var graceplan =result.graceplan ;
            	var finacashdetail =result.cashdetaillist ;
            	var fundchargeplan =  result.fundchargeplan;
            	var fundfundplan =  result.fundputplan;
            	//写的什么鸡巴玩意儿
                if(mini.get("approval_distingguish") && mini.get("approval_distingguish").getValue()=="show_fund_cash"){
            		mini.get("fund_cash_plan_frame").setData(finacashdetail);
                	mini.get("fund_fund_plan").setData(fundchargeplan);
                	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
                	$("#id_json_fund_fund_charge_str").val(mini.encode(fundchargeplan));
            	}else if(mini.get("approval_distingguish") && mini.get("approval_distingguish").getValue()=="show_rent_cash"){
            		mini.get("fund_rent_plan_frame").setData(fundrentplan);
            		mini.get("fund_cash_plan_frame").setData(finacashdetail);
                	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
                	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
                	
            	}else{
            		mini.get("fund_rent_plan_frame").setData(fundrentplan);
                	mini.get("fund_cash_plan_frame").setData(finacashdetail);
                   	mini.get("fund_fund_plan").setData(fundchargeplan);
                   	mini.get("fund_put_config").setData(fundfundplan);
                   	mini.get("grace_plan").setData(graceplan);
                   	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
                   	$("#id_json_fund_fund_charge_str").val(mini.encode(fundchargeplan));
                   	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
                   	$("#id_json_fund_put_config_str").val(mini.encode(fundfundplan));
                   	$("#id_json_grace_plan_str").val(mini.encode(graceplan));
            	} 
            	//alert(mini.get("approval_distingguish").getValue());
            	//分别把租金计划  、现金流 、资金收付计划保存至隐藏域
            	/*mini.get("fund_rent_plan_frame").setData(fundrentplan);
            	mini.get("fund_cash_plan_frame").setData(finacashdetail);
               	mini.get("fund_fund_plan").setData(fundchargeplan);
               	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
               	$("#id_json_fund_fund_charge_str").val(mini.encode(fundchargeplan));
               	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));  
            	*/
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
        	//测算完成之后，页面计算租前息的数据，并添加到资金收付计划页签中
			calculatebeforeinterest_new();
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	mini.unmask(document.body);
            alert(jqXHR.responseText);
        }
    });
}
//测算前格式化分段规则 初始化还款间隔 计算分段年利率
function formatSpecialConfig(){
	var special_regular=mini.get("special_regular").getData();
	var incomenumberyear = $minigetvalue("incomenumberyear");
	var tempValue = parseInt(incomenumberyear.replace("income_",""));//还款间隔
	var ratefloattypevalue = $minigetvalue("ratefloattype");
	var baserate = getNumber("baserate");//基准利率
	var temprate=0;
	for(var i=0;i<special_regular.length;i++){
		if("proportion" == ratefloattypevalue){
			temprate=decimal((parseFloat(special_regular[i].ratefloatamt)+1) * baserate,6);
			special_regular[i].yearrate=temprate;
		}else if("add" == ratefloattypevalue){
			temprate=decimal((parseFloat(special_regular[i].ratefloatamt) + baserate),6);
			special_regular[i].yearrate=temprate;
		} 
		special_regular[i].regularmonths=tempValue;
	}
	mini.get("special_regular").setData(special_regular);
    $('#id_json_special_regular_str').val(mini.encode(special_regular));
}

//测算利率改变事件
function rentorratevaluekeyup(e){
	if($('#testrate').is(':visible')){
		e.sender.vtype = 'rate';
	}
	setformatvalue(e.sender);
	var rentorrateinputvalue = $minigetinputtext("rentorratevalue");
	var ratefloattypevalue = $minigetvalue("ratefloattype");
	var rentorratevalue = $minigetvalue("rentorrate");
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
function cleancreditmoney(){
	//净授信额 = 设备款－首付款－保证金-厂商保证金-利息补贴-返点收入-其他收入-期初第1期租金-厂商返利-手续费 - 咨询费+其他支出+保险费
	var equipamt = $minigetinputtext("equipamt");//设备款
	var firstpayment = $minigetinputtext("firstpayment");//首付款
	var cautionmoney = $minigetinputtext("cautionmoney");//保证金 
	var faccautionmoney = $minigetinputtext("faccautionmoney");//厂商保证金 
	var interestsupport = $minigetinputtext("interestsupport");//利息补贴
	var returnpointincome = $minigetinputtext("returnpointincome");//返点收入
	var otherincome = $minigetinputtext("otherincome");//其他收入 
	var firstRent=0;
	if($mini("fund_rent_plan_frame").getData().lenght>0){
		firstRent=$mini("fund_rent_plan_frame").getData()[0].rent;//期初第1期租金
	}
	var returnamt = $minigetinputtext("returnamt");//厂商返利
	var handlingchargemoney = $minigetinputtext("handlingchargemoney");//手续费
	var managementmoney = $minigetinputtext("managementmoney");//咨询费
	var otherexpenditure = $minigetinputtext("otherexpenditure");//其他支出
	var insuremoney = $minigetinputtext("insuremoney");//保险费
	$mini("cleancreditmoney").setValue(equipamt - firstpayment - cautionmoney - faccautionmoney - interestsupport - returnpointincome-
			otherincome-firstRent-returnamt-handlingchargemoney-managementmoney+otherexpenditure+insuremoney);
	setformatvalue($mini("cleancreditmoney"));
}
//计算手续费比例
function changehandlingchargemoneyratio(e){
	var equipamt = getNumber("equipamt");//设备款
	if('${currentDeployId}' == '41093'){//代表是伙伴计划信审流程，手续费取值按照融资额
		equipamt = getNumber("cleanleasemoney");//融资额
	}
	setformatvalue($mini("handlingchargemoney"));
	var handlingchargemoney = mini.get("handlingchargemoney").getInputText().replace(/,/g,"");//手续费
	var handlingchargemoneyratio = mini.get("handlingchargemoneyratio");//手续费比例
	var handlingchargemoneyratiovalue =!equipamt || Number(equipamt)<=0 ?  0 : decimal(handlingchargemoney/equipamt*100, 6);
	handlingchargemoneyratio.setValue(handlingchargemoneyratiovalue);
}
//计算管理费比例
function changemanagementmoneyratio(e){
	setformatvalue($mini("managementmoney"));
	var equipamt = getNumber("equipamt");//设备款
	var managementmoney = mini.get("managementmoney").getInputText().replace(/,/g,"");//管理费
	var managementmoneyratio = mini.get("managementmoneyratio");//管理费比例
	
	var managementmoneyratiovalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(managementmoney/equipamt*100, 6);
	managementmoneyratio.setValue(managementmoneyratiovalue);
}
//提前还款补偿金格式
function advancerepaymoney(e){
	setformatvalue($mini("advancerepaymoney"));
}
//计算保证金比例
function changecautiondeductionratio(e){
	var cautiondeductionratio = "cautiondeductionratio";
	var cautionmoney = "cautionmoney";
	var cautiondeductionmoney = "cautiondeductionmoney";
	var cautionmoneyremain = "cautionmoneyremain";
	if((typeof(e) != 'string' && e.sender.id != 'cautionmoney') || e == 'fac'){
		cautiondeductionratio = "faccautiondeductionratio";
		cautionmoney = "faccautionmoney";
		cautiondeductionmoney = "faccautiondeductionmoney";
		cautionmoneyremain = "faccautionmoneyremain";
	}
	setformatvalue($mini(cautionmoney));
	var equipamt = getNumber("equipamt");//设备款
	var cautionmoney = mini.get(cautionmoney).getInputText().replace(/,/g,"");//保证金
	var cautiondeductionratio = mini.get(cautiondeductionratio);//保证金比例
	var cautiondeductionratiovalue =!equipamt || Number(equipamt)<=0 ?  0 : decimal(cautionmoney/equipamt*100, 6);
	cautiondeductionratio.setValue(cautiondeductionratiovalue);
	//如果保证金发生改变的话，默认是将保证金抵扣金额赋值为和保证金金额相等
	mini.get(cautiondeductionmoney).setValue(!cautionmoney ? 0 : cautionmoney);
	mini.get(cautionmoneyremain).setValue(0);
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
	setformatvalue(e.sender);
	changefirstpaymentratio();//计算首付比例
	changecautiondeductionratio('');//计算保证金比例
	changecautiondeductionratio('fac');//计算保证金比例
	changemanagementmoneyratio();//计算管理费比例
	changehandlingchargemoneyratio();//计算手续费比例
	//cleancreditmoney();//净融资额
	cleanleasemoney();//融资额
	changecleancreditratio();//计算净授信比例 
}

//计算首付比例
function changefirstpaymentratio(e){
	setformatvalue($mini("firstpayment"));
	var equipamt = getNumber("equipamt");//设备款
	var firstpayment = $minigetinputtext("firstpayment");//首付款
	var firstpaymentratio = mini.get("firstpaymentratio");//首付比例
	var firstpaymentratiovalue = !equipamt || Number(equipamt)<=0 ?  0 : decimal(firstpayment/equipamt*100, 6);
	firstpaymentratio.setValue(firstpaymentratiovalue);
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
		//insuremoney.setValue(insuremoneyvalue);
	}
}

function isNumber(moneyText){
	var rateRegex = /^[-\+]?\d+(\.\d+)?$/;
	return rateRegex.test(moneyText);
}
function changeEvencorpusRateType(settlemethod){
	/* var settlemethod = settlemethod == '' ? "special_regular" : settlemethod ;
	if(settlemethod == 'even_corpus'){
		if(!mini.get('evencorpusratetype').getValue()){
			$miniSetCombValue("evencorpusratetype","issue",'按期');
		}		
		$miniEnable("evencorpusratetype");
		//将计息方式只读
		$miniSetCombValue("rateadjusttype","by_issue",'按期');
		$miniDisable("rateadjusttype");
	}else{
		//$miniSetCombValue("adjuststyle","",'');
		$miniSetCombValue("evencorpusratetype","",'');
		//$miniDisable("evencorpusratetype");
		if(settlemethod == 'even_interest'){
			$miniSetCombValue("rateadjusttype","by_issue",'按期');
			$miniDisable("rateadjusttype");
		}else{
			$miniSetCombValue("adjuststyle","next_year",'年调');
			$miniEnable("adjuststyle");
		}
	} */
}
function changeRentPlanTableButton(e){
	var table = mini.get('fund_rent_plan_frame');
	var data = mini.get('fund_rent_plan_frame').getData();
	$('#id_fund_rent_plan_frame').html('');
	mini.parse();
	var settlemethod = mini.get('settlemethod').getValue();
	changeEvencorpusRateType(settlemethod);
	var conditionDeatils = mini.get("conditionDeatils");
	if(settlemethod == 'irregular_rent'){
		$('#calculateButton').show();
		tempColume.tools =nomalTool ;
		tempColume.columns = columnsIrr;
		$miniSetCombValue("rentorrate","knowing_rent","已知租金规则测算");
		mini.get('rentorratevalue').disable();
		$miniSetCombValue('ratefloattype','fixed','固定利率');
		$miniSetCombValue("incomenumberyear","","");
		$miniDisable('incomenumberyear');
		$miniDisable("ratefloattype");
		$miniDisable("ratefloatamt");
		$miniDisable("graceadjust");
		$miniDisable("baserate");
		$mini("ratefloatamt").setValue("0");
		$mini("graceadjust").setValue("0");
		$mini("baserate").setValue("0");
		$mini("yearrate").setValue("0");
		$mini("rentorratevalue").setValue("0");
		mini.get("segment_config").hide();
		$('#testrent').hide();
		$('#testrate').show();
		if(typeof(e) != "string"){
			rentorrateitemchange();
		}
	}else if(settlemethod == 'special_regular'){
		$("#calculateButton").show();
		tempColume.tools =nomalTool ;
		tempColume.columns = columnsNomal;
		$miniSetCombValue("rentorrate","r_special_regular","分段推算");
		$miniSetCombValue("incomenumberyear","income_1","月  付");
		$miniEnable('incomenumberyear');
		$mini("incomenumber").setValue("0");
		$mini("leaseterm").setValue("0");
		mini.get("segment_config").show();
		mini.get('rentorratevalue').enable();  
		mini.get('grace').enable();
		mini.get('gracerate').enable();
		$miniEnable("ratefloattype");
	}else if(settlemethod == 'excel_import'){
		$('#calculateButton').hide();
		tempColume.tools =impExcelTool ;
		tempColume.columns = columnsIrr;
		$miniSetCombValue("rentorrate","handle_method","手工EXCEL");
		$miniSetCombValue('ratefloattype','fixed','固定利率');
		$miniSetCombValue("incomenumberyear","","");
		$miniDisable('incomenumberyear');
		$mini("rentorratevalue").setReadOnly(false);
		$mini("grace").setValue("0");
		$mini("gracerate").setValue("0");
		$miniDisable("grace");
		$miniDisable("gracerate");
		$miniDisable("incomenumber");
		$miniDisable("leaseterm");
		$miniDisable("ratefloattype");
		$miniDisable("ratefloatamt");
		$miniDisable("graceadjust");
		$miniDisable("baserate");
		$mini("ratefloatamt").setValue("0");
		$mini("graceadjust").setValue("0");
		$mini("baserate").setValue("0");
		$mini("yearrate").setValue("0");
		$miniEnable("rentorratevalue");
		$mini("rentorratevalue").setValue("0");
		$('#testrent').hide();
		$('#testrate').show();
		if(typeof(e) != "string"){
			rentorrateitemchange();
		}
		mini.get("segment_config").hide();
	}else{
		mini.get("segment_config").hide();
		$('#calculateButton').show();
		tempColume.tools =[];
		tempColume.columns = columnsNomal;
		$miniSetCombValue("incomenumberyear","income_1","月  付");
		$miniEnable('incomenumberyear');
		$('#calculateButton').show();
		$miniEnable('rentorratevalue');
		mini.get('rentorratevalue').setReadOnly(false);
		//$('input[name="rentorratevalue"]').removeAttr("readonly");
		if(mini.get('rentorrate').getValue() == 'knowing_rent'){
			mini.get('yearrate').setValue('0');
			mini.get('rentorratevalue').setValue('0');
			$miniEnable("rentorratevalue");
			$miniSetCombValue('rentorrate','rate','按年利率计算租金');
		}
		/* if(settlemethod == 'even_corpus' && (mini.get('rentorrate').getValue() != 'rate' || mini.get('rentorrate').getValue() == 'knowing_corpus')){
			$miniSetCombValue('rentorrate','rate','按年利率计算租金');
		} */
		if(mini.get('settlemethod').getValue() == 'even_interest'){
			$miniSetCombValue('rentorrate','rate','按年利率计算租金');
		}else if(mini.get('settlemethod').getValue() == 'even_corpus'){
			if(mini.get('rentorrate').getValue() == 'knowing_rent' || mini.get('rentorrate').getValue() == 'rent'){
				$miniSetCombValue('rentorrate','rate','按年利率计算租金');
			}
		}
		//再去判断是否是按租金推算年租息率
		if(mini.get('rentorrate').getValue() == 'rent'){
			$miniSetCombValue('ratefloattype','add','按央行利率加点');
			$miniEnable("ratefloattype");
			$miniEnable("ratefloatamt");
			$miniEnable("graceadjust");
			$miniEnable("baserate");
			$mini("ratefloatamt").setValue("0");
			$mini("graceadjust").setValue("0");
			$mini("baserate").setValue("0");
			mini.get('rentorratevalue').setValue('0');
			$miniEnable("rentorratevalue");
			$miniEnable("adjuststyle");
			$mini("yearrate").setValue("0");
			$('#testrent').show();
			$('#testrate').hide();
		}else{
			$('#testrent').hide();
			$('#testrate').show();
			$miniEnable("ratefloattype");
			$miniEnable("rentorratevalue");
			if($minigetvalue("ratefloattype") != "fixed"){
				$miniEnable("ratefloatamt");
				$miniEnable("graceadjust");
				$miniEnable("baserate");
			}else{
				$miniDisable("ratefloatamt");
				$miniDisable("graceadjust");
				$miniDisable("baserate");
				$mini("ratefloatamt").setValue("0");
				$mini("graceadjust").setValue("0");
				$mini("baserate").setValue("0");
			}
		}
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable(tempColume);
	});
	tempColume = mini.clone(tempColume2);
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
    var value = 0;
    try {
    	value=mini.get(id).getValue();
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
	var conditionDeatils = mini.get("conditionDeatils");
	var settlemethod = "${empty requestScope['settlemethod'] ? 'special_regular' : requestScope['settlemethod'] }";
	updateInputThousand();//刷新页面当中的所有金额为千分位
	changeEvencorpusRateType(settlemethod);
	rentorratevaluechange('start');//年租息率只读
	ratefloattypevaluechange('start');//固定利率设置只读
	insuremoneytypevaluechange('start');//保险计算方式
	mini.get('incomenumber').setReadOnly(true);
	mini.get('leaseterm').setReadOnly(true);
}
function calculateBeforeInterest(e){
	//旧版租前息测算
	/* var fundChargeList ;
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
	mini.get('beforeInterestContainer').hide(); */
}
//测算完成之后，页面计算租前息的数据，并添加到资金收付计划页签中
function calculatebeforeinterest_new(){
	var special_regular=mini.get("special_regular");
	var datas=special_regular.data;
	for(var i=0;i<datas.length;i++){
		if(datas[i].regularsettlemethod=="special_regular.beforeinterest"){
			var startlist=datas[i].startlist;//开始期次
			var endlist=datas[i].endlist;//结束期次
			var yearrate=datas[i].yearrate;//租前息年利率
			var regularmonths=datas[i].regularmonths;//还款间隔
			
		}
	}
};
function changeHandhz(e){
	//hand_hz.01	随还租频率收取
	//hand_hz.02	不规则收取
	
	//hand_ratio.01	融资总额
	//hand_ratio.02	上期剩余本金
	//hand_ratio.03	当期剩余本金
	
	//period_type_1	期初
	//period_type_0	期末
	if(e.sender.value=='hand_hz.01'){
		mini.get("handmoney").enable();
	}else if(e.sender.value=='hand_hz.02'){
		mini.get("handmoney").disable();
		$miniSetCombValue("handmoney","hand_ratio.01","融资总额");
	}
	if(e.sender.value==""){
		mini.get("handtype").setRequired(false);
		mini.get("handmoney").setRequired(false);
		mini.get("handratio").setRequired(false);
	}else{
		mini.get("handtype").setRequired(true);
		mini.get("handmoney").setRequired(true);
		mini.get("handratio").setRequired(true);
	}
}
</script>