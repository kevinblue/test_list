<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<div class="mini-panel" title="提前终止变更项" showCollapseButton="true" style="width: 100%;" > 
	<div id="contractterminatechangeitem" title="提前终止变更项" style="border: 1px solid #99CCFF;">
	    <table class="fillTable" cellspacing="0" cellpadding="0">
				<tr class="tr-project-info tr-odd">
			        <td class="td-content-title">约定终止日
			        </td>
			        <td class="td-content">
			        	<input name="fund_rent_adjust.finacorpusoverage" id="finacorpusoverage" class="mini-hidden" value="${empty requestScope['fund_rent_adjust.finacorpusoverage'] ? '0' : requestScope['fund_rent_adjust.finacorpusoverage']}"/>
			            <input name="fund_rent_adjust.paydayadjust" id="paydayadjust" class="mini-datepicker" required="true" label="约定终止日" minDate="${requestScope['minplandate'] }" maxDate="${requestScope['maxplandate'] }" allowInput="false" value="${ requestScope['fund_rent_adjust.paydayadjust']}" onvaluechanged="changePayDayAdjust">
			        </td>
			        <td class="td-content-title">迟延违约金 </td>
			        <td class="td-content"><input name="fund_rent_adjust.delaypenalty" id="delaypenalty" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.delaypenalty'] ? '0' : requestScope['fund_rent_adjust.delaypenalty']}"/></td>  
			    </tr>
			    <tr class="tr-project-info tr-odd">
			    	<td class="td-content-title">未到期租金中租赁本金至提前还款日的利息</td>
			        <td class="td-content"><input name="fund_rent_adjust.overcorpusinterest" id="overcorpusinterest" class="mini-textbox" readonly value="${empty  requestScope['fund_rent_adjust.overcorpusinterest'] ?  '0' : requestScope['fund_rent_adjust.overcorpusinterest'] }"/></td>
					<td class="td-content-title">未到期手续费</td>
			        <td class="td-content"><input name="fund_rent_adjust.overcorpushandmoney" id="overcorpushandmoney" class="mini-textbox" readonly value="${empty  requestScope['fund_rent_adjust.overcorpushandmoney'] ?  '0' : requestScope['fund_rent_adjust.overcorpushandmoney'] }"/></td>
			    </tr>
			    <tr class="tr-project-info tr-odd">
			        <td class="td-content-title">已到期租金 </td>
			        <td class="td-content"><input name="fund_rent_adjust.dunrent" id="dunrent" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.dunrent'] ? '0' : requestScope['fund_rent_adjust.dunrent']}"/></td>
			        <td class="td-content-title">已到期租前息 </td>
			        <td class="td-content"><input name="fund_rent_adjust.dungrace" id="dungrace" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.dungrace'] ? '0' : requestScope['fund_rent_adjust.dungrace']}"/></td>
			         
			    </tr>
			    <tr class="tr-project-info tr-even">
			        
			        <td class="td-content-title"><!-- 其他应退 -->其他应付款项</td>
			        <td class="td-content"> <input name="fund_rent_adjust.otherout" id="otherout" class="mini-textbox"  value="${empty requestScope['fund_rent_adjust.otherout'] ? '0' :  requestScope['fund_rent_adjust.otherout']}"/></td>
			        <td class="td-content-title">提前还款补偿金</td>
			        <td class="td-content"> <input name="fund_rent_adjust.advancerepaymoney" id="advancerepaymoney" class="mini-textbox" readonly value="${empty requestScope['advancerepaymoney'] ? '0' :  requestScope['advancerepaymoney']}"/></td>
			    </tr>
				<tr class="tr-project-info tr-even">
			        <td class="td-content-title"><!-- 未到期本金 -->未到期租金中的租赁本金</td>
			        <td class="td-content"> <input name="fund_rent_adjust.corpusoverage" id="corpusoverage" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.corpusoverage'] ? '0' : requestScope['fund_rent_adjust.corpusoverage']}"/></td>
			        <td class="td-content-title">期末认购价款</td>
			        <td class="td-content"> <input name="fund_rent_adjust.nominalprice" id="nominalprice" class="mini-textbox" readonly value="${empty requestScope['nominalprice'] ? '0' : requestScope['nominalprice']}"/></td>
			        
			    </tr>
			    <tr class="tr-project-info tr-odd">
			        
			    </tr>
				<tr class="tr-project-info tr-even">
			        <td class="td-content-title"><!-- 合同债权总计 -->合计</td>
			        <td class="td-content"><input name="fund_rent_adjust.contracttotal" id="contracttotal" class="mini-textbox" readonly value="${empty  requestScope['fund_rent_adjust.contracttotal'] ?  '0' : requestScope['fund_rent_adjust.contracttotal'] }"/></td>
			         
			    </tr>
			    <tr class="tr-project-info tr-odd" id="id_contract_terminate_button">
	                <td class="td-content" colspan="4" style="text-align: center;">
	                	<a class="mini-button" iconCls="icon-user" plain="true" onclick="submitChange" style="color: red;">提前结清</a>
	                	<a class="mini-button" iconCls="icon-user" plain="true" onclick="resetChange" style="color: red;">撤销结清</a>
	                </td>
			    </tr>
			    <tr class="tr-project-info tr-even" style ="display:none">
			    	<td class="td-content-title">违约手续费</td>
			        <td class="td-content"><input name="fund_rent_adjust.handlingcharge" class="mini-textbox" value="${empty requestScope['fund_rent_adjust.handlingcharge'] ? '0' : requestScope['fund_rent_adjust.handlingcharge']}"/></td>
			        <td class="td-content-title">其他应收 </td>
			        <td class="td-content"><input name="fund_rent_adjust.otherin" id="otherin" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.otherin'] ? '0' : requestScope['fund_rent_adjust.otherin']}"/></td>
			        <td class="td-content-title">逾期罚息</td>
			        <td class="td-content"> <input name="fund_rent_adjust.dunpenalty" id="dunpenalty" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.dunpenalty'] ? '0' : requestScope['fund_rent_adjust.dunpenalty'] }"/></td>
			        <td class="td-content-title">商定罚息</td>
			        <td class="td-content"> <input name="fund_rent_adjust.agreepenalty" id="agreepenalty" class="mini-textbox" value="${empty requestScope['fund_rent_adjust.agreepenalty'] ? '0' : requestScope['fund_rent_adjust.agreepenalty']}" onkeyup="changeagree"/></td>
			        <td class="td-content-title">商定利息</td>
			        <td class="td-content"> <input name="fund_rent_adjust.agreedinterest" id="agreedinterest" class="mini-textbox" value="${empty  requestScope['fund_rent_adjust.agreedinterest'] ? '0' : requestScope['fund_rent_adjust.agreedinterest']}" onkeyup="changeagree" required="true"/></td>
			        <td class="td-content-title">未到期利息</td>
			        <td class="td-content"><input name="fund_rent_adjust.corpusinterest" id="corpusinterest" class="mini-textbox" readonly value="${empty requestScope['fund_rent_adjust.corpusinterest'] ? '0' : requestScope['fund_rent_adjust.corpusinterest']}"/></td>
			        <td class="td-content-title">老版未到期手续费</td>
			        <td class="td-content"><input name="fund_rent_adjust.prematurityhandmoney" id="prematurityhandmoney" class="mini-textbox" readonly value="${empty  requestScope['fund_rent_adjust.prematurityhandmoney'] ?  '0' : requestScope['fund_rent_adjust.prematurityhandmoney'] }"/></td>
			    </tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	var plans =  mini.decode('${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}');
	/* var minPlanDate = '';
	var maxPlanDate = ''; */
	jQuery(function(){
		if('${param.isView}' == 'true' || isViewHistoryTask==true)
			{
			miniui_ext.disableFormFields("contractterminatechangeitem");
			$("#id_contract_terminate_button").hide();
			};
	/* 	//查找对应的租金计划中的未回笼状态的最小期次的还款日期和最大期次的还款日期
			for(var i = 0 ; i< plans.length;i++){
				var plan = plans[i];
				if(plan.planstatusname == '未回笼'){
					minPlanDate = plan.plandate;
					break;
				}
			}
			maxPlanDate = plans[plans.length-1].plandate; */
	});	
		function changePayDayAdjust(e){
			var adjustDate =e.sender.text;								
		    var a=	mini.get("fund_fund_charge").getData();		
		    var plandate=a[0]["plandate"];			    		  
		    var days=DateDiff(plandate,  adjustDate);//天数差
		 
		       //计算天数差的函数，通用  
		     function  DateDiff(sDate1,  sDate2){    //sDate1和sDate2是2002-12-18格式  
		           var  aDate,  oDate1,  oDate2,  iDays  
		           aDate  =  sDate1.split("-")  
		           oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2002格式  
		           aDate  =  sDate2.split("-")  
		           oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
		           iDays  =  parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
		           return  iDays  
		    }
		    var rentorratevalue=  mini.get("rentorratevalue").getValue();//年利率	
		  
			/* if(adjustDate.toDate('yyyy-MM-dd') - minPlanDate.toDate('yyyy-MM-dd') < 0 || adjustDate.toDate('yyyy-MM-dd') - maxPlanDate.toDate('yyyy-MM-dd') > 0){
				mini.alert('预定终止日需大于未回笼状态最小期次的还款日期，需小于最后一期的还款日期！');
				e.sender.text = '';
				return;
			} */
			var param = {};
			param.xmlFileName = '/eleasing/workflow/contract/contract_terminate/contract_terminate_info.xml';
			param.contractid = "${requestScope['contract_info.id']}";
			param.adjustdate = adjustDate;
			ajaxRequest({
				url : '${pageContext.request.contextPath}/table/getTableData.action',
				method : 'POST',
				success : function(res) {
					var scustinfo = res.responseText;
					scustinfo = scustinfo.replace(/(^\s+)|(\s+$)/g, "");
					var obj =mini.decode(scustinfo);
					var remaincorpus =  obj.datas[0].remaincorpus;
					var remaininterest =  obj.datas[0].remaininterest;
					var overduerent =  obj.datas[0].overduerent;
					var remainfincorpus =  obj.datas[0].remainfincorpus;
					var penalty = obj.datas[0].penalty;	
					//---------------------
					var overcorpushandmoney =  obj.datas[0].overcorpushandmoney;//未到期手续费
					var delaypenalty = obj.datas[0].delaypenalty;//迟延违约金
					var overcorpusinterest = obj.datas[0].overcorpusinterest;//所有剩余本金至提前还款日的利息
					var sumcorpus = obj.datas[0].sumcorpus;//剩余本金
					var dungrace = obj.datas[0].dungrace;//已到期租前息
					mini.get("delaypenalty").setValue(delaypenalty);
					mini.get("overcorpusinterest").setValue(overcorpusinterest);
					mini.get("overcorpushandmoney").setValue(overcorpushandmoney);
					mini.get("dungrace").setValue(dungrace);
					//mini.get("corpusoverage").setValue(sumcorpus);
					var  corpusoverage=  mini.get('corpusoverage').setValue(sumcorpus);
					//---------------------
					mini.get('dunrent').setValue(overduerent);
					mini.get('prematurityhandmoney').setValue(handmoney);
				 
				    if(corpusoverage==null){
				    	corpusoverage=0;				    	
				    }						  
					mini.get('corpusinterest').setValue(remaininterest);									
					var rentorratevaluelast = Number(rentorratevalue)/100;
					var interest = corpusoverage*days*rentorratevaluelast/360;		
				    mini.get('agreedinterest').setValue(decimal(interest, 2));										
					mini.get('agreepenalty').setValue(penalty);
					mini.get('dunpenalty').setValue(penalty);
					mini.get('finacorpusoverage').setValue(remainfincorpus);
					var totolBond = getContractBondTotal();
					mini.get('contracttotal').setValue(totolBond);
				},
				async : false,
				failure : function(res) {
				},
				params : param
			});
		}
		
		function changeagree(e){
			var currentvalue =  e.sender.getInputText();
			var id = e.sender.id;
			var value = e.sender.value;
			var rateRegex = /^\d+(\.[0-9]{1,6})?$/;
			var total = getContractBondTotal();
			mini.get('contracttotal').setValue(total);
			currentvalue=currentvalue+"";
			var lastValue="";
			if(currentvalue.length>0){
			    lastValue=currentvalue.substr(currentvalue.length-1,1);
				if(lastValue=="."){
					currentvalue=currentvalue.substr(0,currentvalue.length-1);
				}
			}
			if(!rateRegex.test(currentvalue)){
				mini.alert('请你输入正确格式的金额！');
				mini.get(id).setValue(value);
				var total = getContractBondTotal();
				mini.get('contracttotal').setValue(total);
				return;
			}else{
				var total = getContractBondTotal();
				mini.get('contracttotal').setValue(total);
			}
		}
		
		function  getContractBondTotal(){
			var  dunrent = decimal(mini.get('dunrent').getInputText(),2);
			var  corpusoverage = decimal(mini.get('corpusoverage').getInputText(),2);
			var  corpusinterest = 0;//mini.get('corpusinterest').getInputText();
			var  agreedinterest = decimal(mini.get('agreedinterest').getInputText(),2);
			var  agreepenalty = decimal(mini.get('agreepenalty').getInputText(),2);
			var  dunpenalty = decimal(mini.get('dunpenalty').getInputText(),2);
			var  otherin = decimal(mini.get('otherin').getInputText(),2);
			var  otherout = decimal(mini.get('otherout').getInputText(),2);
			return decimal(Number(otherin ?  otherin : 0 ) - Number(otherout ?  otherout : 0 ) + Number(dunrent ?  dunrent : 0 )
			+ Number(corpusoverage ?  corpusoverage : 0 ) + Number(corpusinterest ?  corpusinterest : 0 ) + Number(agreedinterest ?  agreedinterest : 0 )
			+ Number(agreepenalty ?  agreepenalty : 0 ) +  Number(dunpenalty ?  dunpenalty : 0 ),2) ;
		}
		
		function resetChange(e){
			//判断是否变更过
			if(!checkContractIsAdust()){
				mini.alert('未进行合同终止操作，无法进行撤销！！');
				return;
			}
			
			var param ={};
			param['fund_rent_adjust.docid'] = "${currentProcessInstanceDBID}";
			param['fund_rent_adjust.contractid'] = "${requestScope['contract_info.id']}";
			mini.mask({	
				el: document.body,
				cls: 'mini-mask-loading',
				html: '正在进行撤销合同终止操作，请稍后...'
			}); 
		    var url="<%=request.getContextPath() %>/leasing/resetAdjust.action";
			$.ajax({
		        url: url,
		        type: "post",
		        data:  param,
		        success: function (text) {
		        	var  fundrentplan = mini.get('fund_rent_plan').getData();
		        	var finacashdetail = mini.get('fund_cash_flow').getData();
		        	var fundfundplan = mini.get('fund_fund_charge').getData();
		        	mini.get("fund_rent_plan_new").setData(fundrentplan);
		        	mini.get("fund_cash_flow_new").setData(finacashdetail);
		        	mini.get("fund_fund_charge_new").setData(fundfundplan);
		        	$("#id_json_fund_rent_plan_new_str").val(mini.encode(fundrentplan));
		        	$("#id_json_fund_cash_flow_new_str").val(mini.encode(finacashdetail));
		        	$("#id_json_fund_fund_charge_new_str").val(mini.encode(fundfundplan));
		        	document.getElementById('framework_condition.irr').value = $('#irr').val();
		        	mini.get("framework_condition.irrshow").setValue(mini.get("irrshow").getValue());
		        	document.getElementById('framework_condition.xirr').value = mini.get("xirr").getValue();
		        	mini.get("framework_condition.xirrshow").setValue(mini.get("xirr").getValue());
		        	mini.get("framework_condition.enddate").setValue(mini.get("enddate").getValue());
		        	mini.unmask(document.body);
		        	mini.unmask(document.body);
		        	mini.alert('撤销成功！');
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	mini.unmask(document.body);
		            alert(jqXHR.responseText);
		        }
		    });
		}
		function submitChange(e){
			//判断预定终止日是否为空
			var paydayadjust =   mini.get('paydayadjust').getFormValue();
			if(!paydayadjust ||　paydayadjust.length <= 0 ){
				mini.alert('请输入约定终止日后在进行中途终止操作！！！');
				return;
			}
			//判断是否变更过
			if(checkContractIsAdust()){
				mini.alert('已经进行过合同终止，请撤销后在进行合同终止！');
				return;
			}
			
			 if (miniui_ext.submitFormValidation(["contractterminatechangeitem"]) == false) return false;	
			var form = new mini.Form("contractterminatechangeitem");
			var formCondition = new mini.Form("businessconditionFormReadonly");
			var o = form.getData(true,false);
			var o1 = formCondition.getData(true,false);
			for(var p in o){
				o1[p] = o[p];
			}	
			o1['fund_rent_adjust.agreedinterest'] = mini.get('overcorpusinterest').getValue();
			o1['fund_rent_adjust.adjustsavetype'] = 'terminate';
			o1['fund_rent_adjust.adjusttype'] = 'his_contract_end';
			o1['fund_rent_adjust.docid'] = "${currentProcessInstanceDBID}";
			o1['fund_rent_adjust.contract'] = mini.get('contract_id').getValue();
			o1['fund_rent_adjust.contractid'] = "${requestScope['contract_info.id']}";
			o1['fund_rent_adjust.contractinfoid'] = "${requestScope['contract_info.id']}";
			o1['fund_rent_adjust.jsonfundrentplanstr'] = '${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}';
			o1['fund_rent_adjust.fundputstr'] = '${empty json_fund_put_config_str ? "[]" : json_fund_put_config_str}';
		    var url="<%=request.getContextPath() %>/leasing/adjustCalculateOld.action";
		    mini.mask({	
				el: document.body,
				cls: 'mini-mask-loading',
				html: '正在进行中途终止，请稍后...'
			});
			$.ajax({
		        url: url,
		        type: "post",
		        data:  o1 ,
		        success: function (text) {
		        	var result = mini.decode(text);
		        	if(result.rentcalculaters == 'yes'){
			        	mini.alert('操作成功请到现商务条件中查看租金计划');
			        	var fundrentplan = result.rentplanlist;
			        	//租金计划按照期次进行排序
			        	for(var k = 0 ; k < fundrentplan.length;k++){
			        		var temprentlist ;
			        		var date1 = Number(fundrentplan[k].rentlist);
			        		for(var m = k + 1 ; m < fundrentplan.length ;m++){
			        			var date2 = Number(fundrentplan[m].rentlist);
			        			if(date1 > date2){
			        				date1 = date2;
			        				temprentlist = fundrentplan[k] ;
			        				fundrentplan[k] = fundrentplan[m];
			        				fundrentplan[m] = temprentlist;
			        				temprentlist = fundrentplan[k];
			        			}
			        		}
			        		
			        	}
			        	var finacashdetail = result.cashdetaillist;
			        	//现金流根据计划日期进行排序
			        	for(var i = 0 ; i < finacashdetail.length;i++){
			        		var tempCashdetail ;
			        		var plandate1 = new Date(finacashdetail[i].plandate.replace(/-/g,'/'));
			        		for(var j = i + 1 ; j < finacashdetail.length ;j++){
			        			var plandate2 = new Date(finacashdetail[j].plandate.replace(/-/g,'/'));
			        			if( plandate2 < plandate1){
			        				tempCashdetail = finacashdetail[i] ;
			        				finacashdetail[i] = finacashdetail[j];
			        				finacashdetail[j] = tempCashdetail;
			        			}
			        		}
			        	}
			        	
			        	var fundchargeplan = result.fundchargeplan;
			        	
			        	mini.get("fund_rent_plan_new").setData(fundrentplan);
			        	mini.get("fund_cash_flow_new").setData(finacashdetail);
			        	mini.get("fund_fund_charge_new").setData(fundchargeplan);
			        	
			        	$("#id_json_fund_rent_plan_new_str").val(mini.encode(fundrentplan));
			        	$("#id_json_fund_cash_flow_new_str").val(mini.encode(finacashdetail));
			        	$("#id_json_fund_fund_charge_new_str").val(mini.encode(fundchargeplan));
			        	
			        	mini.get("framework_condition.irrshow").setValue(result.irr);
			        	document.getElementById('framework_condition.irr').value = result.irr;
			        	document.getElementById('framework_condition.xirr').value = result.xirr;
			        	mini.get("framework_condition.xirrshow").setValue(result.xirr);
			        	mini.get("framework_condition.enddate").setValue(result.enddate);
			        	mini.unmask(document.body);
		        	}else{
		        		mini.alert('操作失败');
		        		mini.unmask(document.body);
		        	}
		        	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	mini.unmask(document.body);
		            alert(jqXHR.responseText);
		        }
		    });
		}
		
		function checkContractIsAdust(contractId,docId){
			var isAdjust = true;
			var param = {};
			var contractId = "${requestScope['contract_info.id']}";
			var docid  = "${currentProcessInstanceDBID}";
			param.contractId=  contractId;
			param.docid = docid;
			param['xmlFileName'] = "/eleasing/workflow/contract/contract_planchange/contract_rent_adjust_check.xml";
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/table/getTableData.action',
				timeout:30*60*1000,
				async : false,
				success:function(response){
					var jsondata = eval('(' + response.responseText + ')').datas;
					if(Number(jsondata[0].num) > 0 ){
						isAdjust = true;
					}else{
						isAdjust = false;
					}
				},
				failure:function(response){
					isAdjust =  false;
				}
			});
			return isAdjust;
		}
	</script>
 
    


