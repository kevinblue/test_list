<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div id="businessconditionForm">
		    <div class="mini-panel" title="授信条件" showCollapseButton="true" style="width: 100%;">
		    	<table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info_compare" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >设备款：</td>
			              <td class="td-content">
			             	<input name="json_fund_rent_plan_new_str" 			id="id_json_fund_rent_plan_new_str" 		type="hidden" value='${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str}'/><%-- 租金计划 --%>
							<input name="json_fund_cash_flow_new_str" 			id="id_json_fund_cash_flow_new_str" 		type="hidden"	 value='${empty json_fund_cash_flow_new_str ? "[]" : json_fund_cash_flow_new_str }'/><%-- 现金流 --%>
							<input name="json_fund_fund_charge_new_str" 		id="id_json_fund_fund_charge_new_str" 		type="hidden"	 value='${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'/><%-- 资金首付计划 --%>
							<input name="json_grace_plan_new_str" 				id="id_json_grace_plan_new_str" 			type="hidden"	 value='${empty json_grace_plan_new_str ? "[]" : json_grace_plan_new_str }'/><%-- 资金首付计划 --%>
		              	  <input name="framework_condition.equipamt" id ="framework_condition.framework_condition.equipamt" vtype="double"   class="mini-textbox"  onkeyup="changeequipamtvalue" value="${empty requestScope['framework_condition.equipamt'] ? 0 : requestScope['framework_condition.equipamt'] }"></td>
			              <td class="td-content-title">首付款：</td>
			              <td class="td-content"><input name="framework_condition.firstpayment" label ="首付款" vtype="double"  id ="framework_condition.firstpayment"  class="mini-textbox"  value="${empty requestScope['framework_condition.firstpayment'] ? 0 : requestScope['framework_condition.firstpayment'] }" ></td>
			              <td class="td-content-title">首付比例：</td>
			              <td class="td-content"><input name="framework_condition.firstpaymentratio" id ="framework_condition.firstpaymentratio"  class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymentratio'] ? 0 : requestScope['framework_condition.firstpaymentratio'] }" readonly><font class="required-tag">%</font></td>
			             
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			             
			          </tr>
			               
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">租金测算本金：</td>
			              <td class="td-content"><input name="framework_condition.cleanleasemoney" readonly  vtype="double"   id ="framework_condition.cleanleasemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleanleasemoney'] ? 0 : requestScope['framework_condition.cleanleasemoney'] }"></td>
			              <td class="td-content-title">净融资额：</td>
			              <td class="td-content"><input name="framework_condition.cleancreditmoney" vtype="double"  id ="framework_condition.cleancreditmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditmoney'] ? 0 : requestScope['framework_condition.cleancreditmoney'] }" readonly ></td>
			              <td class="td-content-title">净融资额比例：</td>
			              <td class="td-content"><input name="framework_condition.cleancreditratio" id ="framework_condition.cleancreditratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditratio'] ? 0 : requestScope['framework_condition.cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"></td>
			              <td class="td-content"></td>	
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="租金推算方式" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition.proj_base_info" >
			     <tr class="tr-project-info tr-even">
			              <td class="td-content-title">租金计算方式：</td>
			              <td class="td-content">
			               	<input id="framework_condition.settlemethod" required name="framework_condition.settlemethod" class="mini-combobox" textField="name"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'settle_method'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${empty requestScope['framework_condition.settlemethod'] ? 'special_regular' : requestScope['framework_condition.settlemethod'] }" 
								   text="${empty requestScope['rawValue_framework_condition.settlemethod'] ? '规则测算' : requestScope['rawValue_framework_condition.settlemethod'] }"
							   />
							   <input id="rawValue_framework_condition.settlemethod" name="rawValue_framework_condition.settlemethod" value="${requestScope['rawValue_framework_condition.settlemethod']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">还租时点：</td>
				          <td class="td-content">
					              <input id="framework_condition.periodtype" name="framework_condition.periodtype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   allowInput="false" 
										   dataField="datas"
										   data-options="{_params:{pid:'period_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['framework_condition.periodtype'] ? 'period_type_0' : requestScope['framework_condition.periodtype'] }" 
										   text="${empty requestScope['rawValue_framework_condition.periodtype'] ? '期末' : requestScope['rawValue_framework_condition.periodtype'] }"
								  />
								  <input id="rawValue_framework_condition.periodtype" name="rawValue_framework_condition.periodtype" value="${requestScope['rawValue_framework_condition.periodtype']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">还租次数：</td>  
			              <td class="td-content"><input name="framework_condition.incomenumber" required id ="framework_condition.incomenumber" vtype="int" class="mini-textbox"  value="${empty requestScope['framework_condition.incomenumber'] ? 0 : requestScope['framework_condition.incomenumber'] }"   ></td>
			              <td class="td-content-title">租赁期限(月)：</td>
			              <td class="td-content"><input name="framework_condition.leaseterm" id ="framework_condition.leaseterm"  class="mini-textbox"  value="${empty requestScope['framework_condition.leaseterm'] ? 0 : requestScope['framework_condition.leaseterm'] }"></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">还租频率：</td>
			              <td class="td-content">
				              <input id="framework_condition.incomenumberyear" name="framework_condition.incomenumberyear" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'income_number_year'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
									   	   value="${empty requestScope['framework_condition.incomenumberyear'] ? 'income_1' : requestScope['framework_condition.incomenumberyear'] }" 
								   		   text="${empty requestScope['rawValue_framework_condition.incomenumberyear'] ? '月  付' : requestScope['rawValue_framework_condition.incomenumberyear'] }"
							   />
							   <input id="rawValue_framework_condition.incomenumberyear" name="rawValue_framework_condition.incomenumberyear" value="${requestScope['rawValue_framework_condition.incomenumberyear']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">利率计算方式：</td>
			              <td class="td-content">
				              <input id="framework_condition.ratefloattype" required name="framework_condition.ratefloattype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'rate_float_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['framework_condition.ratefloattype'] ? 'fixed' : requestScope['framework_condition.ratefloattype'] }"  
									   	   text="${empty requestScope['rawValue_framework_condition.ratefloattype'] ? '固定利率' : requestScope['rawValue_framework_condition.ratefloattype'] }"
							  />
							  <input id="rawValue_framework_condition.ratefloattype" name="rawValue_framework_condition.ratefloattype" value="${requestScope['rawValue_framework_condition.ratefloattype']}" class="mini-textbox" style="display:none"/>
						   </td>
			              <td class="td-content-title">宽限期(期)：</td>
			              <td class="td-content"><input name="framework_condition.grace" id ="framework_condition.grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['framework_condition.grace'] ? 0 : requestScope['framework_condition.grace'] }"  ></td>
			             <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			             </tr>
			            <tr class="tr-project-info tr-even">
			              <td class="td-content-title">基准利率：</td>
			              <td class="td-content"><input name="framework_condition.baserate" vtype="rate" id ="framework_condition.baserate"  class="mini-textbox" value="${empty requestScope['framework_condition.baserate'] ? 0 : requestScope['framework_condition.baserate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">宽限期利率调整值：</td>
			              <td class="td-content"><input name="framework_condition.graceadjust" id ="framework_condition.graceadjust"  class="mini-textbox" value="${empty requestScope['framework_condition.graceadjust'] ? 0 : requestScope['framework_condition.graceadjust'] }"   vtype="rate"></td>
			              <td class="td-content-title">宽限期年利率：</td>
						  <td class="td-content"><input name="framework_condition.gracerate" id ="framework_condition.gracerate"  vtype="double"   class="mini-textbox" value="${empty requestScope['framework_condition.gracerate'] ? 0 : requestScope['framework_condition.gracerate'] }"  ><font class="required-tag">%</font></td>
						   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			            </tr> 
			           <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">首笔付款日：</td>
			              <td class="td-content"><input name="framework_condition.leaseamtdate" required onvaluechanged="changeDate" id ="framework_condition.leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.leaseamtdate']}"></td>
			              <td class="td-content-title" >起租日：</td>   
			              <td class="td-content"><input name="framework_condition.startdate" id ="framework_condition.startdate" required  class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.startdate']}" ></td>
			              <td class="td-content-title">第一期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition.firstplandate" required id ="framework_condition.firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.firstplandate']}" ></td>
			          	   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			          </tr>
			          
				</table>
			</div>
			<div id="framework_condition.condition_fund_info" class="mini-panel" title="资金信息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition.proj_base_info" >
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">手续费收取间隔：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition.handhz"  name="framework_condition.handhz" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   showNullItem="true"
								   nullItemText=""
								   data-options="{_params:{pid:'hand_hz'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${requestScope['framework_condition.handhz']}"
								   text="${requestScope['rawValue_framework_condition.handhz']}"
							   />
							   <input id="rawValue_framework_condition.handhz" name="rawValue_framework_condition.handhz" value="${requestScope['rawValue_framework_condition.handhz']}" class="mini-textbox" style="display:none"/>
			          	  	</td>
			          	  <td class="td-content-title">手续费收取方式：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition.handtype"  name="framework_condition.handtype" label="手续费收取方式"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['framework_condition.handtype'] ? '' : requestScope['framework_condition.handtype'] }"
								   text="${empty requestScope['rawValue_framework_condition.handtype'] ? '' : requestScope['rawValue_framework_condition.handtype'] }"
								   data-options="{_params:{pid:'period_type'}}" 
							   />
							   <input id="rawValue_framework_condition.handtype" name="rawValue_framework_condition.handtype" value="${requestScope['rawValue_framework_condition.handtype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费计算基数：</td>
			          	  <td class="td-content">
			          	  	<input id="framework_condition.handmoney"  name="framework_condition.handmoney" label="手续费比例计算基数"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['framework_condition.handmoney'] ? '' : requestScope['framework_condition.handmoney'] }"
								   text="${empty requestScope['rawValue_framework_condition.handmoney'] ? '' : requestScope['rawValue_framework_condition.handmoney'] }"
								   data-options="{_params:{pid:'hand_ratio'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_framework_condition.handmoney" name="rawValue_framework_condition.handmoney" value="${requestScope['rawValue_framework_condition.handmoney']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费比例：</td>
			          	  <td class="td-content"><input name="framework_condition.handratio" vtype="double"  label="手续费比例" id ="framework_condition.handratio"  class="mini-textbox" value="${empty requestScope['framework_condition.handratio'] ? 0 : requestScope['framework_condition.handratio'] }" ><font class="required-tag">%</font></td>
			          </tr>    
			          <tr class="tr-project-info tr-even">
			          <td class="td-content-title">咨询服务费：</td>
			              <td class="td-content"><input name="framework_condition.managementmoney" vtype="double" label="管理费"   id ="framework_condition.managementmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoney'] ? 0 : requestScope['framework_condition.managementmoney'] }" ></td>
			              <td class="td-content-title">咨询服务费比例：</td>
			              <td class="td-content"><input name="framework_condition.managementmoneyratio" id ="framework_condition.managementmoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoneyratio'] ? 0 : requestScope['framework_condition.managementmoneyratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">提前还款补偿金：</td>
			              <td class="td-content"><input name="framework_condition.advancerepaymoney" vtype="double" label="提前还款补偿金"   id ="framework_condition.advancerepaymoney"  class="mini-textbox" value="${empty requestScope['framework_condition.advancerepaymoney'] ? 0 : requestScope['framework_condition.advancerepaymoney'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">承租人保证金：</td>   
			              <td class="td-content"><input name="framework_condition.cautionmoney" vtype="double" label="保证金" id ="framework_condition.cautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.cautionmoney'] ? 0 : requestScope['framework_condition.cautionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金比例：</td>
			              <td class="td-content"><input name="framework_condition.cautiondeductionratio" id ="framework_condition.cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionratio'] ? 0 : requestScope['framework_condition.cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">承租人保证金抵扣金额：</td> 
			              <td class="td-content"><input name="framework_condition.cautiondeductionmoney" label="保证金抵扣金额" vtype="double"  id ="framework_condition.cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionmoney'] ? 0 : requestScope['framework_condition.cautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金退还金额：</td>
			              <td class="td-content"><input name="framework_condition.cautionmoneyremain" label="保证金退还金额" vtype="double"  id ="framework_condition.cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['framework_condition.cautionmoneyremain'] ? 0 : requestScope['framework_condition.cautionmoneyremain'] }" readonly></td>
			          </tr>
			         	
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">名义留购价：</td>   
			              <td class="td-content"><input name="framework_condition.nominalprice" label="留购价款" vtype="double"  id ="framework_condition.nominalprice"  class="mini-textbox" value="${empty requestScope['framework_condition.nominalprice'] ? 10000 : requestScope['framework_condition.nominalprice'] }" ></td>
			              <td class="td-content-title">厂商返利（含税）：</td><td class="td-content"><input name="framework_condition.returnamt" id ="framework_condition.returnamt"  class="mini-textbox" value="${empty requestScope['framework_condition.returnamt'] ? 0 : requestScope['framework_condition.returnamt'] }"></td> 
			              
			              
			              <td class="td-content-title">其他收入：</td>
			              <td class="td-content"><input name="framework_condition.otherincome" label="其他收入" vtype="double"  id ="framework_condition.otherincome"  class="mini-textbox" value="${empty requestScope['framework_condition.otherincome'] ? 0 : requestScope['framework_condition.otherincome'] }" ></td>
			              <td class="td-content-title">其他支出：</td>
			              <td class="td-content"><input name="framework_condition.otherexpenditure" label="其他支出"  vtype="double"  id ="framework_condition.otherexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition.otherexpenditure'] ? 0 : requestScope['framework_condition.otherexpenditure'] }" ></td>
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="调息/罚息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="framework_condition.proj_base_info" >
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息方式：</td>
			              <td class="td-content">
			              	  <input id="framework_condition.adjuststyle" name="framework_condition.adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['framework_condition.adjuststyle'] ? 'next_list' : requestScope['framework_condition.adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.adjuststyle'] ? '次期' : requestScope['rawValue_framework_condition.adjuststyle'] }"
							  />	
							  <input id="rawValue_framework_condition.adjuststyle" name="rawValue_framework_condition.adjuststyle" value="${requestScope['rawValue_framework_condition.adjuststyle']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">罚息利率（万分比）：</td>
			              <td class="td-content">
			              		<input id="framework_condition.penarate" name="framework_condition.penarate" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'pena_rate'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['framework_condition.penarate'] ? '5' : requestScope['framework_condition.penarate'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.penarate'] ? '5' : requestScope['rawValue_framework_condition.penarate'] }"
							  />	
							  <input id="rawValue_framework_condition.penarate" name="rawValue_framework_condition.penarate" value="${requestScope['rawValue_framework_condition.penarate']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title" >IRR：</td>  
			              <td class="td-content">
			              		<input name="framework_condition.irrshow" id ="framework_condition.irrshow" readonly class="mini-textbox" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }"><font class="required-tag">%</font>
			              		<input name="framework_condition.irr" id ="framework_condition.irr" type="hidden" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }">
			              </td>
			          	  <td class="td-content-title" >XIRR：</td>  
			              <td class="td-content">
			              		<input name="framework_condition.xirrshow" id ="framework_condition.xirrshow" readonly class="mini-textbox" value="${empty requestScope['framework_condition.xirr'] ? 0 : requestScope['framework_condition.xirr'] }"><font class="required-tag">%</font>
			              		<input name="framework_condition.xirr" id ="framework_condition.xirr" type="hidden" value="${empty requestScope['framework_condition.xirr'] ? 0 : requestScope['framework_condition.xirr'] }">
			              </td>
						  <td class="td-content-title">项目粗利：</td>
			              <td class="td-content"><input name="framework_condition.grossprofit" vtype="double"  id ="framework_condition.grossprofit" readonly class="mini-textbox" value="${empty requestScope['framework_condition.grossprofit'] ? 0 : requestScope['framework_condition.grossprofit'] }"></td>
						  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="text-indent: 0px;" id="framework_condition.calculateButton">
			              	<!-- <a class="mini-button" iconCls="icon-user" plain="true" onclick="save" style="color: red;">租金测算</a> -->
			              </td>
			          </tr>
				</table>
			</div>
			<%--隐藏域 --%>
			<table style="display: none;"  cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
						  <td class="td-content-title">租前期(月)：</td>
			              <td class="td-content"><input name="framework_condition.prelease" id ="framework_condition.prelease" vtype="int" readOnly class="mini-textbox"   value="${empty requestScope['framework_condition.prelease'] ? 0 : requestScope['framework_condition.prelease'] }"   ></td>
			          	  <td class="td-content-title">厂商保证金：</td>   
			              <td class="td-content"><input name="framework_condition.faccautionmoney" vtype="double" label="厂商保证金" id ="framework_condition.faccautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoney'] ? 0 : requestScope['framework_condition.faccautionmoney'] }"></td>
			              <td class="td-content-title">厂商保证金比例：</td>
			              <td class="td-content"><input name="framework_condition.faccautiondeductionratio" id ="framework_condition.faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.faccautiondeductionratio'] ? 0 : requestScope['framework_condition.faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">厂商保证金抵扣：</td>   
			              <td class="td-content"><input name="framework_condition.faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="framework_condition.faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautiondeductionmoney'] ? 0 : requestScope['framework_condition.faccautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">厂商保证金退还：</td>   
			              <td class="td-content"><input name="framework_condition.faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="framework_condition.faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoneyremain'] ? 0 : requestScope['framework_condition.faccautionmoneyremain'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">保险计算方式：</td>
			              <td class="td-content">
			              	  <input id="framework_condition.insuremoneytype" name="framework_condition.insuremoneytype" class="mini-combobox" textField="name" style="width: 200PX;"
				                  	   valueField="value"     
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'insure_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
								   	   value="${empty requestScope['framework_condition.insuremoneytype'] ? 'insure_type5' : requestScope['framework_condition.insuremoneytype'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.insuremoneytype'] ? '客户自保(不提供保单)' : requestScope['rawValue_framework_condition.insuremoneytype'] }"
									   
							  />
							  <input id="rawValue_framework_condition.insuremoneytype" name="rawValue_framework_condition.insuremoneytype" value="${requestScope['rawValue_framework_condition.insuremoneytype']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">保险费率（万分比）：</td>   
			              <td class="td-content">
			              	<input id="framework_condition.insureratio" name="framework_condition.insureratio" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'insureratio'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['framework_condition.insureratio'] ? '0.0' : requestScope['framework_condition.insureratio'] }" 
								   	   text="${empty requestScope['framework_condition.insureratio'] ? '0' : requestScope['framework_condition.insureratio'] }"
							  />	
			              </td>
			              <td class="td-content-title"> 保险费收入：</td>
			              <td class="td-content">
			              	<input name="framework_condition.insurancelessee_show" vtype="double"  label ="保险费收入" id ="framework_condition.insurancelessee_show"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessee'] ? 0 : requestScope['framework_condition.insurancelessee'] }" >
			              	<input name="framework_condition.insurancelessee" type="hidden"  id ="framework_condition.insurancelessee"  value="${empty requestScope['framework_condition.insurancelessee'] ? 0 : requestScope['framework_condition.insurancelessee'] }" >
			              </td>
			              <td class="td-content-title"> 保险费支出：</td>
			              <td class="td-content">
			              	<input name="framework_condition.insurancelessor_show" vtype="double"  label ="保险费支出"  id ="framework_condition.insurancelessor_show"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessor'] ? 0 : requestScope['framework_condition.insurancelessor'] }" >
			              	<input name="framework_condition.insurancelessor" type="hidden"  id ="framework_condition.insurancelessor"  value="${empty requestScope['framework_condition.insurancelessor'] ? 0 : requestScope['framework_condition.insurancelessor'] }" >
			              </td>
			              
			          </tr>
			   <tr class="tr-project-info tr-odd">
			    <td class="td-content-title">第二期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition.secondplandate" id ="framework_condition.secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.secondplandate']}" ></td>
			          	<td class="td-content-title">前期款坐扣：</td>
			          	  <td class="td-content" colspan="7">
			          	  	<input id="framework_condition.deductiontype" name="framework_condition.deductiontype" class="mini-combobox" textField="name" style="width: 200PX;"
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   multiSelect="true"
								   value="${requestScope['framework_condition.deductiontype'] }"
								   text="${requestScope['rawValue_framework_condition.deductiontype'] }"
								   data="[{name:'首付款',value:'feetype5'},{name:'保证金',value:'feetype2'},{name:'手续费',value:'feetype1'},{name:'咨询费',value:'feetype3'}]" 
							   />
							   <input id="rawValue_framework_condition.deductiontype" name="rawValue_framework_condition.deductiontype" value="${requestScope['rawValue_framework_condition.deductiontype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          </tr>
			 <td class="td-content-title">租金推算方法：</td>
			              <td class="td-content">
							   <input id="framework_condition.rentorrate" required name="framework_condition.rentorrate" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   value="${empty requestScope['framework_condition.rentorrate'] ? 'rate' : requestScope['framework_condition.rentorrate'] }"
								   text="${empty requestScope['rawValue_framework_condition.rentorrate'] ? '按年利率计算租金' : requestScope['rawValue_framework_condition.rentorrate'] }"
								   data-options="{_params:{pid:'rent_or_rate'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_framework_condition.rentorrate" name="rawValue_framework_condition.rentorrate" value="${requestScope['rawValue_framework_condition.rentorrate']}" class="mini-textbox" style="display:none"/>
						  </td>
						  
			<tr class="tr-project-info tr-even">
			              <td class="td-content-title">利率调整值：</td>
			              <td class="td-content"><input name="framework_condition.ratefloatamt" id ="framework_condition.ratefloatamt"  class="mini-textbox" value="${empty requestScope['framework_condition.ratefloatamt'] ? 0 : requestScope['framework_condition.ratefloatamt'] }"   vtype="rate"></td>
			              <td class="td-content-title" id="framework_condition.itemTd"><span id="framework_condition.testrent">每期租金:</span><span id="framework_condition.testrate">租赁年利率：</span></td>
			              <td class="td-content"><input name="framework_condition.rentorratevalue" id ="framework_condition.rentorratevalue"  vtype="double"   class="mini-textbox" value="${empty requestScope['framework_condition.rentorratevalue'] ? 0 : requestScope['framework_condition.rentorratevalue'] }"  ></td>
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
             <td class="td-content"><input name="framework_condition.yearrate" id ="framework_condition.yearrate"  class="mini-textbox" value="${empty requestScope['framework_condition.yearrate'] ? 0 : requestScope['framework_condition.yearrate'] }" readonly><font class="required-tag">%</font></td>
			
			 <td class="td-content-title">手续费：</td>
			              <td class="td-content"><input name="framework_condition.handlingchargemoney" vtype="double"  label="手续费" id ="framework_condition.handlingchargemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoney'] ? 0 : requestScope['framework_condition.handlingchargemoney'] }" ></td>
			              <td class="td-content-title">手续费比例：</td>
			              <td class="td-content"><input name="framework_condition.handlingchargemoneyratio" id ="framework_condition.handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoneyratio'] ? 0 : requestScope['framework_condition.handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
			<td class="td-content-title">免罚息天数：</td>
			              <td class="td-content"><input name="framework_condition.freedefainterday"  id ="framework_condition.freedefainterday"  class="mini-textbox"  value="${empty requestScope['framework_condition.freedefainterday'] ? requestScope['framework_condition.proj_info.industrytype'] eq 'cust_source.intenal' || empty requestScope['framework_condition.proj_info.industrytype'] ? 0 : 7  : requestScope['framework_condition.freedefainterday'] }"></td> 
			
			<td class="td-content-title">其他融资费：</td>  
			              <td class="td-content"><input name="framework_condition.otherleasemoney" vtype="double"  id ="framework_condition.otherleasemoney" class="mini-textbox" value="${empty requestScope['framework_condition.otherleasemoney'] ? 0 : requestScope['framework_condition.otherleasemoney'] }" ></td>
		<%-- 	<td class="td-content-title">宽限期：</td>
			              <td class="td-content"><input name="framework_condition.grace" id ="framework_condition.grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['framework_condition.grace'] ? 0 : requestScope['framework_condition.grace'] }"   ></td> --%>
			 <td class="td-content-title">期末余值：</td>
			              <td class="td-content"><input name="framework_condition.equipendvalue" id ="framework_condition.equipendvalue"  vtype="double"  class="mini-textbox"  value="${empty requestScope['framework_condition.equipendvalue'] ? 0 : requestScope['framework_condition.equipendvalue'] }" ></td>
			    <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title">返点收入：</td>   
			              <td class="td-content"><input name="framework_condition.returnpointincome" label="返点收入" vtype="double"  id ="framework_condition.returnpointincome"  class="mini-textbox" value="${empty requestScope['framework_condition.returnpointincome'] ? 0 : requestScope['framework_condition.returnpointincome'] }" ></td>
			          	  <td class="td-content-title">利息补贴：</td>   
			              <td class="td-content"><input name="framework_condition.interestsupport" label="利息补贴" vtype="double"  id ="framework_condition.interestsupport"  class="mini-textbox" value="${empty requestScope['framework_condition.interestsupport'] ? 0 : requestScope['framework_condition.interestsupport'] }" ></td>
			          	  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
				<tr class="tr-project-info tr-even">
				     <td class="td-content-title">财务收益率：</td><td class="td-content"><input name="framework_condition.planirr" id ="framework_condition.planirr" readonly class="mini-textbox" value="${empty requestScope['framework_condition.planirr'] ? 0 : requestScope['framework_condition.planirr']}"></td> 
				     <td class="td-content-title">结束日期：</td><td class="td-content"><input name="framework_condition.enddate" id ="framework_condition.enddate" readonly class="mini-textbox" value="${empty requestScope['framework_condition.enddate'] ? 0 : requestScope['framework_condition.enddate']}"></td> 
					 <td class="td-content-title">租前息金额：</td>
			                <c:choose>
			              	<c:when test="${currentDeployId eq '5557'}">
			              		 <td class="td-content"><input name="framework_condition.beforeinterest" id ="framework_condition.beforeinterest"  vtype="double" class="mini-buttonedit" onbuttonclick = "beforeInterestButtonClick"  text="${empty requestScope['framework_condition.beforeinterest'] ? 0 : requestScope['framework_condition.beforeinterest'] }" value="${empty requestScope['framework_condition.beforeinterest'] ? 0 : requestScope['framework_condition.beforeinterest'] }"></td>
			              	</c:when>
			              	<c:otherwise> 	
			              		<td class="td-content"><input name="framework_condition.beforeinterest" label="租前息" vtype="double"  id ="framework_condition.beforeinterest"  class="mini-textbox" value="${empty requestScope['framework_condition.beforeinterest'] ? 0 : requestScope['framework_condition.beforeinterest'] }" ></td>
			              	</c:otherwise>
			              </c:choose> 
					 <td class="td-content-title">期初付款总计：</td><td class="td-content"><input name="framework_condition.firstpaymenttotal" vtype="double"  id ="framework_condition.firstpaymenttotal" class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymenttotal'] ? 0 : requestScope['framework_condition.firstpaymenttotal'] }" readonly></td>
					<td class="td-content-title">保险费：</td>
	                <td class="td-content"><input name="framework_condition.insuremoney" label="保险费"  vtype="double"  id ="framework_condition.insuremoney"  readonly class="mini-textbox" value="0" readonly></td>
				</tr>
				<tr class="tr-project-info tr-odd">
			              <td class="td-content-title">末期租金支付日：</td>
			              <td class="td-content"><input name="framework_condition.lastplandate" id ="framework_condition.lastplandate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.lastplandate']}" ></td>
			        	   <td class="td-content-title">等额本金计息方式</td>
			        	   <td class="td-content">
			        	   	 <input id="framework_condition.evencorpusratetype" name="framework_condition.evencorpusratetype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'even_corpus_rate_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${requestScope['framework_condition.evencorpusratetype']}" 
								   	   text="${requestScope['rawValue_framework_condition.evencorpusratetype']}"
							  />	
							  <input id="rawValue_framework_condition.evencorpusratetype" name="rawValue_framework_condition.evencorpusratetype" value="${requestScope['rawValue_framework_condition.evencorpusratetype']}" class="mini-textbox" style="display:none"/>
			        	   </td>
			        	   <td class="td-content-title">利率调整</td>
			        	   <td class="td-content">
			        	   	 <input id="framework_condition.rateadjusttype" name="framework_condition.rateadjusttype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'rate_adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['framework_condition.rateadjusttype'] ? 'by_issue' : requestScope['framework_condition.rateadjusttype'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.rateadjusttype'] ? '按期' : requestScope['rawValue_framework_condition.rateadjusttype'] }"
							  />	
							  <input id="rawValue_framework_condition.rateadjusttype" name="rawValue_framework_condition.rateadjusttype" value="${requestScope['rawValue_framework_condition.rateadjusttype']}" class="mini-textbox" style="display:none"/>
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