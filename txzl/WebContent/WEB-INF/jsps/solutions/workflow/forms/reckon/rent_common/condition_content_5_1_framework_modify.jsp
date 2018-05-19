<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div id="businessconditionForm">
		    <div class="mini-panel" title="授信条件" showCollapseButton="true" style="width: 100%;">
		    	<table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info_compare" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >设备款：</td>
			              <td class="td-content">
			               	  <input name="id" 			id="conditionId"  		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.id']}"/>
							  <input name="custid" 		id="conditionCustId" 	style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.custid']}"/>
							  <input name="contractid" 	id="conditionContractId" 	style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.contractid']}"/>
							  <input name="projid" 		id="conditionProjId" 		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.projid']}"/>
							  <input name="docid" 		id="conditionDocId" 		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.docid']}"/> 
							  <input name="creditmonths"id="creditmonths" 	        style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.creditmonths']}"/> 
							  <input name="dcnum" 		id="dcnum" 	        		style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.dcnum']?'0':requestScope['framework_condition.dcnum'] }"/>
							  <input name="custname" 		id="custname" 	        		style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.custname']?'':requestScope['framework_condition.custname'] }"/>
							  
							  <!-- 下面是关于测算的关键值 -->
							    <input name="process" 		id="reckonProcess" 			style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.process']}"/>
								<input name="reckontype" 	id="reckonType"  			style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.reckontype']?'condition':requestScope['framework_condition.reckontype'] }"/>
								
			             	<input name="json_fund_rent_plan_modify_str" 			id="id_json_fund_rent_plan_modify_str" 		type="hidden" value='${empty json_fund_rent_plan_modify_str ? "[]" : json_fund_rent_plan_modify_str}'/><%-- 租金计划 --%>
							<input name="json_fund_cash_flow_modify_str" 			id="id_json_fund_cash_flow_modify_str" 		type="hidden"	 value='${empty json_fund_cash_flow_modify_str ? "[]" : json_fund_cash_flow_modify_str }'/><%-- 现金流 --%>
							<input name="json_fund_fund_charge_modify_str" 		id="id_json_fund_fund_charge_modify_str" 		type="hidden"	 value='${empty json_fund_fund_charge_modify_str ? "[]" : json_fund_fund_charge_modify_str }'/><%-- 资金首付计划 --%>
							<input name="json_grace_plan_modify_str" 				id="id_json_grace_plan_modify_str" 			type="hidden"	 value='${empty json_grace_plan_modify_str ? "[]" : json_grace_plan_modify_str }'/><%-- 资金首付计划 --%>
		              	  <input name="framework_condition_modify.equipamt" id ="framework_condition_modify.framework_condition_modify.equipamt" vtype="double"   class="mini-textbox"  onkeyup="changeequipamtvalue" value="${empty requestScope['framework_condition_modify.equipamt'] ? 0 : requestScope['framework_condition_modify.equipamt'] }"></td>
			              <td class="td-content-title">首付款：</td>
			              <td class="td-content"><input name="framework_condition_modify.firstpayment" label ="首付款" vtype="double"  id ="framework_condition_modify.firstpayment"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.firstpayment'] ? 0 : requestScope['framework_condition_modify.firstpayment'] }" ></td>
			              <td class="td-content-title">首付比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.firstpaymentratio" id ="framework_condition_modify.firstpaymentratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.firstpaymentratio'] ? 0 : requestScope['framework_condition_modify.firstpaymentratio'] }" readonly><font class="required-tag">%</font></td>
			             
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			             
			          </tr>
			               
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">租金测算本金：</td>
			              <td class="td-content"><input name="framework_condition_modify.cleanleasemoney" readonly  vtype="double"   id ="framework_condition_modify.cleanleasemoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cleanleasemoney'] ? 0 : requestScope['framework_condition_modify.cleanleasemoney'] }"></td>
			              <td class="td-content-title">净融资额：</td>
			              <td class="td-content"><input name="framework_condition_modify.cleancreditmoney" vtype="double"  id ="framework_condition_modify.cleancreditmoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cleancreditmoney'] ? 0 : requestScope['framework_condition_modify.cleancreditmoney'] }" readonly ></td>
			              <td class="td-content-title">净融资额比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.cleancreditratio" id ="framework_condition_modify.cleancreditratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cleancreditratio'] ? 0 : requestScope['framework_condition_modify.cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"></td>
			              <td class="td-content"></td>	
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="租金推算方式" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition_modify.proj_base_info" >
			     <tr class="tr-project-info tr-even">
			              <td class="td-content-title">租金计算方式：</td>
			              <td class="td-content">
			               	<input id="framework_condition_modify.settlemethod" required name="framework_condition_modify.settlemethod" class="mini-combobox" textField="name"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'settle_method'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${empty requestScope['framework_condition_modify.settlemethod'] ? 'special_regular' : requestScope['framework_condition_modify.settlemethod'] }" 
								   text="${empty requestScope['rawValue_framework_condition_modify.settlemethod'] ? '规则测算' : requestScope['rawValue_framework_condition_modify.settlemethod'] }"
							   />
							   <input id="rawValue_framework_condition_modify.settlemethod" name="rawValue_framework_condition_modify.settlemethod" value="${requestScope['rawValue_framework_condition_modify.settlemethod']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">还租时点：</td>
				          <td class="td-content">
					              <input id="framework_condition_modify.periodtype" name="framework_condition_modify.periodtype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   allowInput="false" 
										   dataField="datas"
										   data-options="{_params:{pid:'period_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['framework_condition_modify.periodtype'] ? 'period_type_0' : requestScope['framework_condition_modify.periodtype'] }" 
										   text="${empty requestScope['rawValue_framework_condition_modify.periodtype'] ? '期末' : requestScope['rawValue_framework_condition_modify.periodtype'] }"
								  />
								  <input id="rawValue_framework_condition_modify.periodtype" name="rawValue_framework_condition_modify.periodtype" value="${requestScope['rawValue_framework_condition_modify.periodtype']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">还租次数：</td>  
			              <td class="td-content"><input name="framework_condition_modify.incomenumber" required id ="framework_condition_modify.incomenumber" vtype="int" class="mini-textbox"  value="${empty requestScope['framework_condition_modify.incomenumber'] ? 0 : requestScope['framework_condition_modify.incomenumber'] }"   ></td>
			              <td class="td-content-title">租赁期限(月)：</td>
			              <td class="td-content"><input name="framework_condition_modify.leaseterm" id ="framework_condition_modify.leaseterm"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.leaseterm'] ? 0 : requestScope['framework_condition_modify.leaseterm'] }"></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">还租频率：</td>
			              <td class="td-content">
				              <input id="framework_condition_modify.incomenumberyear" name="framework_condition_modify.incomenumberyear" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'income_number_year'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
									   	   value="${empty requestScope['framework_condition_modify.incomenumberyear'] ? 'income_1' : requestScope['framework_condition_modify.incomenumberyear'] }" 
								   		   text="${empty requestScope['rawValue_framework_condition_modify.incomenumberyear'] ? '月  付' : requestScope['rawValue_framework_condition_modify.incomenumberyear'] }"
							   />
							   <input id="rawValue_framework_condition_modify.incomenumberyear" name="rawValue_framework_condition_modify.incomenumberyear" value="${requestScope['rawValue_framework_condition_modify.incomenumberyear']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">利率计算方式：</td>
			              <td class="td-content">
				              <input id="framework_condition_modify.ratefloattype" required name="framework_condition_modify.ratefloattype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'rate_float_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['framework_condition_modify.ratefloattype'] ? 'fixed' : requestScope['framework_condition_modify.ratefloattype'] }"  
									   	   text="${empty requestScope['rawValue_framework_condition_modify.ratefloattype'] ? '固定利率' : requestScope['rawValue_framework_condition_modify.ratefloattype'] }"
							  />
							  <input id="rawValue_framework_condition_modify.ratefloattype" name="rawValue_framework_condition_modify.ratefloattype" value="${requestScope['rawValue_framework_condition_modify.ratefloattype']}" class="mini-textbox" style="display:none"/>
						   </td>
			              <td class="td-content-title">宽限期(期)：</td>
			              <td class="td-content"><input name="framework_condition_modify.grace" id ="framework_condition_modify.grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['framework_condition_modify.grace'] ? 0 : requestScope['framework_condition_modify.grace'] }"  ></td>
			             <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			             </tr>
			            <tr class="tr-project-info tr-even">
			              <td class="td-content-title">基准利率：</td>
			              <td class="td-content"><input name="framework_condition_modify.baserate" vtype="rate" id ="framework_condition_modify.baserate"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.baserate'] ? 0 : requestScope['framework_condition_modify.baserate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">宽限期利率调整值：</td>
			              <td class="td-content"><input name="framework_condition_modify.graceadjust" id ="framework_condition_modify.graceadjust"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.graceadjust'] ? 0 : requestScope['framework_condition_modify.graceadjust'] }"   vtype="rate"></td>
			              <td class="td-content-title">宽限期年利率：</td>
						  <td class="td-content"><input name="framework_condition_modify.gracerate" id ="framework_condition_modify.gracerate"  vtype="double"   class="mini-textbox" value="${empty requestScope['framework_condition_modify.gracerate'] ? 0 : requestScope['framework_condition_modify.gracerate'] }"  ><font class="required-tag">%</font></td>
						   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			            </tr> 
			           <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">首笔付款日：</td>
			              <td class="td-content"><input name="framework_condition_modify.leaseamtdate" required onvaluechanged="changeDate" id ="framework_condition_modify.leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition_modify.leaseamtdate']}"></td>
			              <td class="td-content-title" >起租日：</td>   
			              <td class="td-content"><input name="framework_condition_modify.startdate" id ="framework_condition_modify.startdate" required  class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition_modify.startdate']}" ></td>
			              <td class="td-content-title">第一期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition_modify.firstplandate" required id ="framework_condition_modify.firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition_modify.firstplandate']}" ></td>
			          	   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			          </tr>
			          
				</table>
			</div>
			<div id="framework_condition_modify.condition_fund_info" class="mini-panel" title="资金信息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition_modify.proj_base_info" >
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">手续费收取间隔：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition_modify.handhz"  name="framework_condition_modify.handhz" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   showNullItem="true"
								   nullItemText=""
								   data-options="{_params:{pid:'hand_hz'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${requestScope['framework_condition_modify.handhz']}"
								   text="${requestScope['rawValue_framework_condition_modify.handhz']}"
							   />
							   <input id="rawValue_framework_condition_modify.handhz" name="rawValue_framework_condition_modify.handhz" value="${requestScope['rawValue_framework_condition_modify.handhz']}" class="mini-textbox" style="display:none"/>
			          	  	</td>
			          	  <td class="td-content-title">手续费收取方式：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition_modify.handtype"  name="framework_condition_modify.handtype" label="手续费收取方式"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['framework_condition_modify.handtype'] ? '' : requestScope['framework_condition_modify.handtype'] }"
								   text="${empty requestScope['rawValue_framework_condition_modify.handtype'] ? '' : requestScope['rawValue_framework_condition_modify.handtype'] }"
								   data-options="{_params:{pid:'period_type'}}" 
							   />
							   <input id="rawValue_framework_condition_modify.handtype" name="rawValue_framework_condition_modify.handtype" value="${requestScope['rawValue_framework_condition_modify.handtype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费计算基数：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition_modify.handmoney"  name="framework_condition_modify.handmoney" label="手续费比例计算基数"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['framework_condition_modify.handmoney'] ? '' : requestScope['framework_condition_modify.handmoney'] }"
								   text="${empty requestScope['rawValue_framework_condition_modify.handmoney'] ? '' : requestScope['rawValue_framework_condition_modify.handmoney'] }"
								   data-options="{_params:{pid:'hand_ratio'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_framework_condition_modify.handmoney" name="rawValue_framework_condition_modify.handmoney" value="${requestScope['rawValue_framework_condition_modify.handmoney']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费比例：</td>
			          	  <td class="td-content"><input name="framework_condition_modify.handratio" vtype="double"  label="手续费比例" id ="framework_condition_modify.handratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.handratio'] ? 0 : requestScope['framework_condition_modify.handratio'] }" ><font class="required-tag">%</font></td>
			          </tr>    
			          <tr class="tr-project-info tr-even">
			          <td class="td-content-title">咨询服务费：</td>
			              <td class="td-content"><input name="framework_condition_modify.managementmoney" vtype="double" label="管理费"   id ="framework_condition_modify.managementmoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.managementmoney'] ? 0 : requestScope['framework_condition_modify.managementmoney'] }" ></td>
			              <td class="td-content-title">咨询服务费比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.managementmoneyratio" id ="framework_condition_modify.managementmoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.managementmoneyratio'] ? 0 : requestScope['framework_condition_modify.managementmoneyratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">提前还款补偿金：</td>
			              <td class="td-content"><input name="framework_condition_modify.advancerepaymoney" vtype="double" label="提前还款补偿金"   id ="framework_condition_modify.advancerepaymoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.advancerepaymoney'] ? 0 : requestScope['framework_condition_modify.advancerepaymoney'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">承租人保证金：</td>   
			              <td class="td-content"><input name="framework_condition_modify.cautionmoney" vtype="double" label="保证金" id ="framework_condition_modify.cautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.cautionmoney'] ? 0 : requestScope['framework_condition_modify.cautionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.cautiondeductionratio" id ="framework_condition_modify.cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cautiondeductionratio'] ? 0 : requestScope['framework_condition_modify.cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">承租人保证金抵扣金额：</td> 
			              <td class="td-content"><input name="framework_condition_modify.cautiondeductionmoney" label="保证金抵扣金额" vtype="double"  id ="framework_condition_modify.cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cautiondeductionmoney'] ? 0 : requestScope['framework_condition_modify.cautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金退还金额：</td>
			              <td class="td-content"><input name="framework_condition_modify.cautionmoneyremain" label="保证金退还金额" vtype="double"  id ="framework_condition_modify.cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.cautionmoneyremain'] ? 0 : requestScope['framework_condition_modify.cautionmoneyremain'] }" readonly></td>
			          </tr>
			         	
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">名义留购价：</td>   
			              <td class="td-content"><input name="framework_condition_modify.nominalprice" label="留购价款" vtype="double"  id ="framework_condition_modify.nominalprice"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.nominalprice'] ? 10000 : requestScope['framework_condition_modify.nominalprice'] }" ></td>
			              <td class="td-content-title">厂商返利（含税）：</td><td class="td-content"><input name="framework_condition_modify.returnamt" id ="framework_condition_modify.returnamt"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.returnamt'] ? 0 : requestScope['framework_condition_modify.returnamt'] }"></td> 
			              
			              
			              <td class="td-content-title">其他收入：</td>
			              <td class="td-content"><input name="framework_condition_modify.otherincome" label="其他收入" vtype="double"  id ="framework_condition_modify.otherincome"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.otherincome'] ? 0 : requestScope['framework_condition_modify.otherincome'] }" ></td>
			              <td class="td-content-title">其他支出：</td>
			              <td class="td-content"><input name="framework_condition_modify.otherexpenditure" label="其他支出"  vtype="double"  id ="framework_condition_modify.otherexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.otherexpenditure'] ? 0 : requestScope['framework_condition_modify.otherexpenditure'] }" ></td>
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="调息/罚息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition_modify.proj_base_info" >
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息方式：</td>
			              <td class="td-content">
			              	  <input id="framework_condition_modify.adjuststyle" name="framework_condition_modify.adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['framework_condition_modify.adjuststyle'] ? 'next_list' : requestScope['framework_condition_modify.adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition_modify.adjuststyle'] ? '次期' : requestScope['rawValue_framework_condition_modify.adjuststyle'] }"
							  />	
							  <input id="rawValue_framework_condition_modify.adjuststyle" name="rawValue_framework_condition_modify.adjuststyle" value="${requestScope['rawValue_framework_condition_modify.adjuststyle']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">罚息利率（万分比）：</td>
			              <td class="td-content">
			              		<input id="framework_condition_modify.penarate" name="framework_condition_modify.penarate" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'pena_rate'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['framework_condition_modify.penarate'] ? '5' : requestScope['framework_condition_modify.penarate'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition_modify.penarate'] ? '5' : requestScope['rawValue_framework_condition_modify.penarate'] }"
							  />	
							  <input id="rawValue_framework_condition_modify.penarate" name="rawValue_framework_condition_modify.penarate" value="${requestScope['rawValue_framework_condition_modify.penarate']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title" >IRR：</td>  
			              <td class="td-content">
			              		<input name="framework_condition_modify.irrshow" id ="framework_condition_modify.irrshow" readonly class="mini-textbox" value="${empty requestScope['framework_condition_modify.irr'] ? 0 : requestScope['framework_condition_modify.irr'] }"><font class="required-tag">%</font>
			              		<input name="framework_condition_modify.irr" id ="framework_condition_modify.irr" type="hidden" value="${empty requestScope['framework_condition_modify.irr'] ? 0 : requestScope['framework_condition_modify.irr'] }">
			              </td>
			          	  <td class="td-content-title" >XIRR：</td>  
			              <td class="td-content">
			              		<input name="framework_condition_modify.xirr" id ="framework_condition_modify.xirr" readonly class="mini-textbox" value="${empty requestScope['framework_condition_modify.xirr'] ? 0 : requestScope['framework_condition_modify.xirr'] }"><font class="required-tag">%</font>
			              </td>
						  <td class="td-content-title">项目粗利：</td>
			              <td class="td-content"><input name="framework_condition_modify.grossprofit" vtype="double"  id ="framework_condition_modify.grossprofit" readonly class="mini-textbox" value="${empty requestScope['framework_condition_modify.grossprofit'] ? 0 : requestScope['framework_condition_modify.grossprofit'] }"></td>
						  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="text-indent: 0px;" id="framework_condition_modify.calculateButton">
			              	<!-- <a class="mini-button" iconCls="icon-user" plain="true" onclick="save" style="color: red;">租金测算</a> -->
			              </td>
			          </tr>
				</table>
			</div>
			<%--隐藏域 --%>
			<table style="display: none;"  cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
						  <td class="td-content-title">租前期(月)：</td>
			              <td class="td-content"><input name="framework_condition_modify.prelease" id ="framework_condition_modify.prelease" vtype="int" readOnly class="mini-textbox"   value="${empty requestScope['framework_condition_modify.prelease'] ? 0 : requestScope['framework_condition_modify.prelease'] }"   ></td>
			          	  <td class="td-content-title">厂商保证金：</td>   
			              <td class="td-content"><input name="framework_condition_modify.faccautionmoney" vtype="double" label="厂商保证金" id ="framework_condition_modify.faccautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.faccautionmoney'] ? 0 : requestScope['framework_condition_modify.faccautionmoney'] }"></td>
			              <td class="td-content-title">厂商保证金比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.faccautiondeductionratio" id ="framework_condition_modify.faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.faccautiondeductionratio'] ? 0 : requestScope['framework_condition_modify.faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">厂商保证金抵扣：</td>   
			              <td class="td-content"><input name="framework_condition_modify.faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="framework_condition_modify.faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.faccautiondeductionmoney'] ? 0 : requestScope['framework_condition_modify.faccautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">厂商保证金退还：</td>   
			              <td class="td-content"><input name="framework_condition_modify.faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="framework_condition_modify.faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.faccautionmoneyremain'] ? 0 : requestScope['framework_condition_modify.faccautionmoneyremain'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">保险计算方式：</td>
			              <td class="td-content">
			              	  <input id="framework_condition_modify.insuremoneytype" name="framework_condition_modify.insuremoneytype" class="mini-combobox" textField="name" style="width: 200PX;"
				                  	   valueField="value"     
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'insure_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
								   	   value="${empty requestScope['framework_condition_modify.insuremoneytype'] ? 'insure_type5' : requestScope['framework_condition_modify.insuremoneytype'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition_modify.insuremoneytype'] ? '客户自保(不提供保单)' : requestScope['rawValue_framework_condition_modify.insuremoneytype'] }"
									   
							  />
							  <input id="rawValue_framework_condition_modify.insuremoneytype" name="rawValue_framework_condition_modify.insuremoneytype" value="${requestScope['rawValue_framework_condition_modify.insuremoneytype']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">保险费率（万分比）：</td>   
			              <td class="td-content">
			              	<input id="framework_condition_modify.insureratio" name="framework_condition_modify.insureratio" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'insureratio'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['framework_condition_modify.insureratio'] ? '0.0' : requestScope['framework_condition_modify.insureratio'] }" 
								   	   text="${empty requestScope['framework_condition_modify.insureratio'] ? '0' : requestScope['framework_condition_modify.insureratio'] }"
							  />	
			              </td>
			              <td class="td-content-title"> 保险费收入：</td>
			              <td class="td-content">
			              	<input name="framework_condition_modify.insurancelessee_show" vtype="double"  label ="保险费收入" id ="framework_condition_modify.insurancelessee_show"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.insurancelessee'] ? 0 : requestScope['framework_condition_modify.insurancelessee'] }" >
			              	<input name="framework_condition_modify.insurancelessee" type="hidden"  id ="framework_condition_modify.insurancelessee"  value="${empty requestScope['framework_condition_modify.insurancelessee'] ? 0 : requestScope['framework_condition_modify.insurancelessee'] }" >
			              </td>
			              <td class="td-content-title"> 保险费支出：</td>
			              <td class="td-content">
			              	<input name="framework_condition_modify.insurancelessor_show" vtype="double"  label ="保险费支出"  id ="framework_condition_modify.insurancelessor_show"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.insurancelessor'] ? 0 : requestScope['framework_condition_modify.insurancelessor'] }" >
			              	<input name="framework_condition_modify.insurancelessor" type="hidden"  id ="framework_condition_modify.insurancelessor"  value="${empty requestScope['framework_condition_modify.insurancelessor'] ? 0 : requestScope['framework_condition_modify.insurancelessor'] }" >
			              </td>
			              
			          </tr>
			   <tr class="tr-project-info tr-odd">
			    <td class="td-content-title">第二期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition_modify.secondplandate" id ="framework_condition_modify.secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition_modify.secondplandate']}" ></td>
			          	<td class="td-content-title">前期款坐扣：</td>
			          	  <td class="td-content" colspan="7">
			          	  	<input id="framework_condition_modify.deductiontype" name="framework_condition_modify.deductiontype" class="mini-combobox" textField="name" style="width: 200PX;"
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   multiSelect="true"
								   value="${requestScope['framework_condition_modify.deductiontype'] }"
								   text="${requestScope['rawValue_framework_condition_modify.deductiontype'] }"
								   data="[{name:'首付款',value:'feetype5'},{name:'保证金',value:'feetype2'},{name:'手续费',value:'feetype1'},{name:'咨询费',value:'feetype3'}]" 
							   />
							   <input id="rawValue_framework_condition_modify.deductiontype" name="rawValue_framework_condition_modify.deductiontype" value="${requestScope['rawValue_framework_condition_modify.deductiontype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          </tr>
			 <td class="td-content-title">租金推算方法：</td>
			              <td class="td-content">
							   <input id="framework_condition_modify.rentorrate" required name="framework_condition_modify.rentorrate" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   value="${empty requestScope['framework_condition_modify.rentorrate'] ? 'rate' : requestScope['framework_condition_modify.rentorrate'] }"
								   text="${empty requestScope['rawValue_framework_condition_modify.rentorrate'] ? '按年利率计算租金' : requestScope['rawValue_framework_condition_modify.rentorrate'] }"
								   data-options="{_params:{pid:'rent_or_rate'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_framework_condition_modify.rentorrate" name="rawValue_framework_condition_modify.rentorrate" value="${requestScope['rawValue_framework_condition_modify.rentorrate']}" class="mini-textbox" style="display:none"/>
						  </td>
						  
			<tr class="tr-project-info tr-even">
			              <td class="td-content-title">利率调整值：</td>
			              <td class="td-content"><input name="framework_condition_modify.ratefloatamt" id ="framework_condition_modify.ratefloatamt"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.ratefloatamt'] ? 0 : requestScope['framework_condition_modify.ratefloatamt'] }"   vtype="rate"></td>
			              <td class="td-content-title" id="framework_condition_modify.itemTd"><span id="framework_condition_modify.testrent">每期租金:</span><span id="framework_condition_modify.testrate">租赁年利率：</span></td>
			              <td class="td-content"><input name="framework_condition_modify.rentorratevalue" id ="framework_condition_modify.rentorratevalue"  vtype="double"   class="mini-textbox" value="${empty requestScope['framework_condition_modify.rentorratevalue'] ? 0 : requestScope['framework_condition_modify.rentorratevalue'] }"  ></td>
			             </tr>
			             <%-- <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			             </tr> --%>
				
             <td class="td-content-title">年租息率：</td>
             <td class="td-content"><input name="framework_condition_modify.yearrate" id ="framework_condition_modify.yearrate"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.yearrate'] ? 0 : requestScope['framework_condition_modify.yearrate'] }" readonly><font class="required-tag">%</font></td>
			
			 <td class="td-content-title">手续费：</td>
			              <td class="td-content"><input name="framework_condition_modify.handlingchargemoney" vtype="double"  label="手续费" id ="framework_condition_modify.handlingchargemoney"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.handlingchargemoney'] ? 0 : requestScope['framework_condition_modify.handlingchargemoney'] }" ></td>
			              <td class="td-content-title">手续费比例：</td>
			              <td class="td-content"><input name="framework_condition_modify.handlingchargemoneyratio" id ="framework_condition_modify.handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.handlingchargemoneyratio'] ? 0 : requestScope['framework_condition_modify.handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
			<td class="td-content-title">免罚息天数：</td>
			              <td class="td-content"><input name="framework_condition_modify.freedefainterday"  id ="framework_condition_modify.freedefainterday"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.freedefainterday'] ? requestScope['framework_condition_modify.proj_info.industrytype'] eq 'cust_source.intenal' || empty requestScope['framework_condition_modify.proj_info.industrytype'] ? 0 : 7  : requestScope['framework_condition_modify.freedefainterday'] }"></td> 
			
			<td class="td-content-title">其他融资费：</td>  
			              <td class="td-content"><input name="framework_condition_modify.otherleasemoney" vtype="double"  id ="framework_condition_modify.otherleasemoney" class="mini-textbox" value="${empty requestScope['framework_condition_modify.otherleasemoney'] ? 0 : requestScope['framework_condition_modify.otherleasemoney'] }" ></td>
		<%-- 	<td class="td-content-title">宽限期：</td>
			              <td class="td-content"><input name="framework_condition_modify.grace" id ="framework_condition_modify.grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['framework_condition_modify.grace'] ? 0 : requestScope['framework_condition_modify.grace'] }"   ></td> --%>
			 <td class="td-content-title">期末余值：</td>
			              <td class="td-content"><input name="framework_condition_modify.equipendvalue" id ="framework_condition_modify.equipendvalue"  vtype="double"  class="mini-textbox"  value="${empty requestScope['framework_condition_modify.equipendvalue'] ? 0 : requestScope['framework_condition_modify.equipendvalue'] }" ></td>
			    <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title">返点收入：</td>   
			              <td class="td-content"><input name="framework_condition_modify.returnpointincome" label="返点收入" vtype="double"  id ="framework_condition_modify.returnpointincome"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.returnpointincome'] ? 0 : requestScope['framework_condition_modify.returnpointincome'] }" ></td>
			          	  <td class="td-content-title">利息补贴：</td>   
			              <td class="td-content"><input name="framework_condition_modify.interestsupport" label="利息补贴" vtype="double"  id ="framework_condition_modify.interestsupport"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.interestsupport'] ? 0 : requestScope['framework_condition_modify.interestsupport'] }" ></td>
			          	  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
				<tr class="tr-project-info tr-even">
				     <td class="td-content-title">财务收益率：</td><td class="td-content"><input name="framework_condition_modify.planirr" id ="framework_condition_modify.planirr" readonly class="mini-textbox" value="${empty requestScope['framework_condition_modify.planirr'] ? 0 : requestScope['framework_condition_modify.planirr']}"></td> 
				     <td class="td-content-title">结束日期：</td><td class="td-content"><input name="framework_condition_modify.enddate" id ="framework_condition_modify.enddate" readonly class="mini-textbox" value="${empty requestScope['framework_condition_modify.enddate'] ? 0 : requestScope['framework_condition_modify.enddate']}"></td> 
					 <td class="td-content-title">租前息金额：</td>
			                <c:choose>
			              	<c:when test="${currentDeployId eq '5557'}">
			              		 <td class="td-content"><input name="framework_condition_modify.beforeinterest" id ="framework_condition_modify.beforeinterest"  vtype="double" class="mini-buttonedit" onbuttonclick = "beforeInterestButtonClick"  text="${empty requestScope['framework_condition_modify.beforeinterest'] ? 0 : requestScope['framework_condition_modify.beforeinterest'] }" value="${empty requestScope['framework_condition_modify.beforeinterest'] ? 0 : requestScope['framework_condition_modify.beforeinterest'] }"></td>
			              	</c:when>
			              	<c:otherwise> 	
			              		<td class="td-content"><input name="framework_condition_modify.beforeinterest" label="租前息" vtype="double"  id ="framework_condition_modify.beforeinterest"  class="mini-textbox" value="${empty requestScope['framework_condition_modify.beforeinterest'] ? 0 : requestScope['framework_condition_modify.beforeinterest'] }" ></td>
			              	</c:otherwise>
			              </c:choose> 
					 <td class="td-content-title">期初付款总计：</td><td class="td-content"><input name="framework_condition_modify.firstpaymenttotal" vtype="double"  id ="framework_condition_modify.firstpaymenttotal" class="mini-textbox" value="${empty requestScope['framework_condition_modify.firstpaymenttotal'] ? 0 : requestScope['framework_condition_modify.firstpaymenttotal'] }" readonly></td>
					<td class="td-content-title">保险费：</td>
	                <td class="td-content"><input name="framework_condition_modify.insuremoney" label="保险费"  vtype="double"  id ="framework_condition_modify.insuremoney"  readonly class="mini-textbox" value="0" readonly></td>
				</tr>
				<tr class="tr-project-info tr-odd">
			              <td class="td-content-title">末期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition_modify.lastplandate" id ="framework_condition_modify.lastplandate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition_modify.lastplandate']}" ></td>
			        	   <td class="td-content-title">等额本金计息方式</td>
			        	   <td class="td-content">
			        	   	 <input id="framework_condition_modify.evencorpusratetype" name="framework_condition_modify.evencorpusratetype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'even_corpus_rate_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${requestScope['framework_condition_modify.evencorpusratetype']}" 
								   	   text="${requestScope['rawValue_framework_condition_modify.evencorpusratetype']}"
							  />	
							  <input id="rawValue_framework_condition_modify.evencorpusratetype" name="rawValue_framework_condition_modify.evencorpusratetype" value="${requestScope['rawValue_framework_condition_modify.evencorpusratetype']}" class="mini-textbox" style="display:none"/>
			        	   </td>
			        	   <td class="td-content-title">利率调整</td>
			        	   <td class="td-content">
			        	   	 <input id="framework_condition_modify.rateadjusttype" name="framework_condition_modify.rateadjusttype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'rate_adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['framework_condition_modify.rateadjusttype'] ? 'by_issue' : requestScope['framework_condition_modify.rateadjusttype'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition_modify.rateadjusttype'] ? '按期' : requestScope['rawValue_framework_condition_modify.rateadjusttype'] }"
							  />	
							  <input id="rawValue_framework_condition_modify.rateadjusttype" name="rawValue_framework_condition_modify.rateadjusttype" value="${requestScope['rawValue_framework_condition_modify.rateadjusttype']}" class="mini-textbox" style="display:none"/>
			        	   </td>
			        	   <td class="td-content-title"></td>
			        	   <td class="td-content"></td>
			          </tr>

			</table>
		</div>
	<div id="editWindow" class="mini-window" title="已知租金测算" style="width:700px;height:400px;" 
	    showModal="true" allowResize="true" allowDrag="true"
	    >
	     <jsp:include page="knowing_rent_plan_frame.jsp" ></jsp:include>
	</div>
	<div id="editWindow_knowing_corpus" class="mini-window" title="已知本金测算" style="width:700px;height:400px;" 
	    showModal="true" allowResize="true" allowDrag="true"
	    >
	    <jsp:include page="knowing_copus_plan_frame.jsp" ></jsp:include>
	</div>
	<style>
	
	#reckonContainer .mini-panel-header {
    background:#468CC8;
    background-color:#468CC9;
}
	
	</style>
	 <script type="text/javascript"> 
 jQuery(function(){
     if(mini.get("approval_distingguish") && mini.get("approval_distingguish").getValue()=="show_rent_cash"){
    	 //mini.get("fund_information").setReadOnly(true);
    	 //mini.get("test00001").setReadOnly(true);
    	 miniui_ext.disableFormFields("condition_condition_info",'');
    	 miniui_ext.disableFormFields("condition_fund_info",'');
     }	
    
});

 function changeDate(e){	
	 		var periodtype=$minigetvalue("periodtype");//还租时点，期初期末
	 		var temp;//计算第一期租金支付日的参数
	 		if(periodtype=="period_type_1"){
	 			temp=0;
	 		}else{
	 			temp=1;
	 		}
	 		//宽限期期次
	 		var graceterm= $minigetvalue("grace")==""?"0":$minigetvalue("grace")//如果为空，宽限期默认为0
	 		var incomenumberyear=$minigetvalue("incomenumberyear");//还租频率
	 		var incomenumber=incomenumberyear.substr(incomenumberyear.indexOf('_')+1);//还租频率转化为数字
	 		//宽限期时间(月)
	 		var grace=(Number)(graceterm)*(Number)(incomenumber);
			var leaseamtdate = $minigetvalue("leaseamtdate");
	 		var startdate=mini.get("startdate");
	 		var firstplandate=$mini("firstplandate");
	 		//特殊情况，例如付款日是1月31日，宽限期1月，起租日是2月31日即3月2或3日，实际情况应该是2月28/29日
	 			if(leaseamtdate!=null&&leaseamtdate!=""){
	 			changemonth(Number(grace),"leaseamtdate","startdate");//自定义调整月份方法，分别是：调整数值；基准日期；要调整的日期 
	 		
	 			//该情况是起租日需要调整日期，如果是期初，则按照起租日调整第一期租金支付日，否则按照付款日调整第一期租金
	 			if(temp==1){//期末
	 				changemonth(Number(grace)+Number(incomenumber),"leaseamtdate","firstplandate");
	 			}else{
	 				changemonth(0,"startdate","firstplandate");
	 			} 
	 	  }
 }

 function changemonth(num,basedate,adjustdate){
	 mini.get(adjustdate).setValue(DateUtil.dateAdd('m',num,$minigetvalue(basedate)));
	 var adjustday=$minigetvalue(adjustdate).getDate();//获取调整之后的日期和basedate对比，如果不等，调整后的日期月份没有那一日
	     try{
	    	 var baseday=$minigetvalue(basedate).getDate();
			 if(adjustday!=baseday){
				 mini.get(adjustdate).setValue(DateUtil.dateAdd('m',-1,$minigetvalue(adjustdate)));//先把月份-1
				 var month=$minigetvalue(adjustdate).getMonth();//获取调整后的月份
				 var year=$minigetvalue(adjustdate).getFullYear();//获取调整后的年份
				 var day=$minigetvalue(adjustdate).getDate();//获取调整获得日期
				 var temp = new Date(year,month+1,0);
				 var maxday=temp.getDate();//获得本月最大天数
				 mini.get(adjustdate).setValue(DateUtil.dateAdd('d',maxday-day,$minigetvalue(adjustdate)));//调整日期
			 }
	     }catch(e){
	     }
 }
 </script>