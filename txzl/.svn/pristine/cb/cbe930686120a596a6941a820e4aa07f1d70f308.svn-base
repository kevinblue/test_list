<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var plans =  mini.decode('${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}');
	jQuery(function(){
		var datas = [];
		for(var i = 0 ; i< plans.length;i++){
			var plan = plans[i];
			if(plan.planstatusname == '未回笼'){
				datas.push({text:"第"+plan.rentlist+"期",value:""+plan.rentlist});
			}
		}
		mini.get('startlist').setData(datas);
		if(!mini.get('adjustlist')){
			mini.get('adjustlist').setValue(plans.length);
		}
	});
</script>
<input name="nowRentList" id="nowPlanDate" type="hidden" value="${requestScope['nowListPlanDate']}" /><!-- 选择期次对应的还款时间 -->
<input name="lastRentList" id="lastPlanDate" type="hidden" value="${requestScope['lastListPlanDate']}" /><!-- 选择期次对应的上一期的还款时间 -->
<div  title="变更条件"  id="changeInfoForm"> 
    <table class="fillTable" cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title" width="12%">变更开始期次:</td>
		        <td class="td-content" width="38%"> <input  class="mini-combobox" id="startlist" label="变更开始期次" name="fund_rent_adjust.startlist" onvaluechanged="adjustListchange" textField="text" valueField="value" vtype="int" required="true"  value="${requestScope['fund_rent_adjust.startlist'] }" /></td>
		        <td class="td-content-title" width="12%">变更后总期次:</td>
		        <td class="td-content" width="38%"><input name="fund_rent_adjust.adjustlist" id="adjustlist" vtype="int" label="变更后总期次" required="true"  class="mini-textbox"  required="true" value="${requestScope['fund_rent_adjust.adjustlist'] }" /></td>
		    </tr>
			<tr class="tr-project-info tr-even">
		        <td class="td-content-title" width="12%">付款时间:</td>
		        <td class="td-content" width="38%"> <input name="fund_rent_adjust.paydayadjust" id="paydayadjust" label="付款时间" required="true"  onvaluechanged="paydayAdustchange" class="mini-datepicker" value="${requestScope['fund_rent_adjust.paydayadjust'] }" required="true" /> </td>
		        <td class="td-content-title"  width="12%">变更年利率:</td>
		        <td class="td-content" width="38%"><input  class="mini-textbox" vtype="double" required="true" label="变更年利率" name="fund_rent_adjust.yearrate" id="yearrate" value="${requestScope['fund_rent_adjust.yearrate'] }" /> </td>
		    </tr>
		    <tr class="tr-project-info tr-even" id="id_changebutton">
                <td class="td-content" colspan="4" align="center">
                	<a class="mini-button" iconCls="icon-user" plain="true" onclick="doRentChange" style="color: red;">租金变更</a>
                	<a class="mini-button" iconCls="icon-user" plain="true" onclick="cancelRentChange" style="color: red;">变更撤销</a>
                </td>
		    </tr>
		</table>
	</div>
	<script type="text/javascript">
		jQuery(function(){
			if('${param.isView}' == 'true' || isViewHistoryTask==true){
				  $("#id_changebutton").hide();
				  miniui_ext.disableFormFields("changeInfoForm");
		    };
		});	
		/*
		 *起始期次改变触发函数
		*/
		function adjustListchange(e){
			var sender = e.sender;
			var startList = Number(sender.value);
			for(var i = 0 ; i< plans.length;i++){
				var plan = plans[i];
				var rentlist = plan.rentlist;
				if(startList == Number(rentlist)){
					jQuery('#nowPlanDate').val(plan.plandate);
					mini.get('paydayadjust').setValue(plan.plandate);
				}
				if(startList == Number(rentlist)+1){
					jQuery('#lastPlanDate').val(plan.plandate);
				}
			}
		}
		
		function paydayAdustchange(e){
			var adjustDateStr = mini.get('paydayadjust').getFormValue();
			var lastPlanDateStr = jQuery('#lastPlanDate').val();
			var adjustDate = new Date(adjustDateStr.replace(/-/g,'\/'));
			var lastPlanDate = new Date((lastPlanDateStr+'').replace(/-/g,'\/'));
			if(adjustDate <= lastPlanDate){
				mini.alert('付款日需大于变更其次上一期的还款日期！！！');
				mini.get('paydayadjust').setValue(jQuery('#nowPlanDate').val());
			}			
		}
		
		function doRentChange(){
			//判断是否变更过
			if(checkContractIsAdust()){
				mini.alert('已经进行过租金变更，请撤销后在进行租金变更！');
				return;
			}
			//判断变更期次格式
			var numRegex  = /^[-\+]?\d+$/;
			var rateRegex = /^[-\+]?\d+(\.[0-9]{1,6})?$/;
			if(!numRegex.test(mini.get('adjustlist').getValue())){
				mini.alert('请你输入正确的变更期次！！！');
				return ;
			}
			if(!rateRegex.test(mini.get('yearrate').getValue())){
				mini.alert('请你输入正确的变更年利率！！！');
				return ;
			}
			/* var settlemethod = mini.get("settlemethod").getValue();
			if(settlemethod == "excel_import"){
				mini.alert("不规则导入不能进行租金计划变更");
				return ;
			} */
			//判断对应的合同编号以及流程号是否已租金变更			
			var form = new mini.Form("changeInfoForm");
			var formCondition = new mini.Form("businessconditionFormReadonly");
			var o = form.getData(true,false);
			var o1 = formCondition.getData(true,false);
			for(var p in o){
				o1[p] = o[p];
			}	
			o1['fund_rent_adjust.adjustsavetype'] = 'reckon';
			o1['fund_rent_adjust.adjusttype'] = 'his_plan_change';
			o1['fund_rent_adjust.docid'] = "${currentProcessInstanceDBID}";
			o1['fund_rent_adjust.contract'] = mini.get('contract_id').getValue();
			o1['fund_rent_adjust.contractid'] = "${requestScope['contract_info.id']}";
			o1['fund_rent_adjust.contractinfoid'] = "${requestScope['contract_info.id']}";
			o1['fund_rent_adjust.jsonfundrentplanstr'] = '${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}';
			mini.mask({	
				el: document.body,
				cls: 'mini-mask-loading',
				html: '正在进行租金变更，请稍后...'
			}); 
		    var url="<%=request.getContextPath() %>/leasing/adjustCalculateOld.action";
			$.ajax({
		        url: url,
		        type: "post",
		        data:  o1 ,
		        success: function (text) {
		        	var result = mini.decode(text);
		        	if(result.rentcalculaters == 'yes'){
			        	mini.alert(result.rentcalculatemsg);
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
			        	
			        	jQuery("#id_json_fund_rent_plan_new_str").val(mini.encode(fundrentplan));
			        	jQuery("#id_json_fund_cash_flow_new_str").val(mini.encode(finacashdetail));
			        	jQuery("#id_json_fund_fund_charge_new_str").val(mini.encode(fundchargeplan));
			        	mini.get("framework_condition.irrshow").setValue(result.irr);
			        	mini.get("framework_condition.xirr").setValue(result.xirr);
			        	//document.getElementById('framework_condition.irr').value = result.irr;
			        	mini.get("framework_condition.incomenumber").setValue(mini.get('adjustlist').getValue());
			        	//document.getElementById('framework_condition.incomenumber').value = mini.get('adjustlist').getValue();
			        	var incomenumberyear =  mini.get('framework_condition.incomenumberyear').getValue().replace("income_",'');
			        	mini.get("framework_condition.leaseterm").setValue(Number(mini.get('adjustlist').getValue())*Number(incomenumberyear));
			        	//document.getElementById('framework_condition.leaseterm').value = Number(mini.get('adjustlist').getValue())*Number(incomenumberyear);
			        	mini.unmask(document.body);
		        	}else{
		        		mini.alert('服务器正忙，请与管理员联系！！！！');
		        		mini.unmask(document.body);
		        	}
		        	
		        },
		        error: function (jqXHR, textStatus, errorThrown) {
		        	mini.unmask(document.body);
		            alert(jqXHR.responseText);
		        }
		    });
		}
		function cancelRentChange(){
			
			//判断是否变更过
			if(!checkContractIsAdust()){
				mini.alert('未进行租金变更操作，无法进行撤销！！');
				return;
			}
			var param ={};
			param['fund_rent_adjust.docid'] = "${currentProcessInstanceDBID}";
			param['fund_rent_adjust.contractid'] = "${requestScope['contract_info.id']}";
			mini.mask({	
				el: document.body,
				cls: 'mini-mask-loading',
				html: '正在进行变更撤销，请稍后...'
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
		        	
		        	jQuery("#id_json_fund_rent_plan_new_str").val(mini.encode(fundrentplan));
		        	jQuery("#id_json_fund_cash_flow_new_str").val(mini.encode(finacashdetail));
		        	jQuery("#id_json_fund_fund_charge_new_str").val(mini.encode(fundfundplan));
		        	var irr = $('#irr').val();
		        	mini.get("framework_condition.irrshow").setValue(irr);
		        	//document.getElementById('framework_condition.irr').value = irr;
		        	mini.get("framework_condition.incomenumber").setValue(mini.get('incomenumber').getValue());
		        	//document.getElementById('framework_condition.incomenumber').value = mini.get('incomenumber').getValue();
		        	mini.get("framework_condition.leaseterm").setValue(mini.get('leaseterm').getValue());
		        	//document.getElementById('framework_condition.leaseterm').value = mini.get('leaseterm').getValue();
		        	mini.unmask(document.body);
		        	mini.alert('撤销成功！');
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
	
 
    


