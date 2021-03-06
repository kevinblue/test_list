<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div id="businessconditionForm">
		    <div id="condition_condition_info" class="mini-panel" title="商务条件" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >设备款：</td>
			              <td class="td-content">
			             		<!-- 下边的值是一些关建行信息值，包跨项目阶段的项目编号，合同阶段的合同编号，客户报价时客户编号  //实际授信月数(申请书需用到)//保证金抵扣期数-->
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
								
								<!-- 下边的是租金计划的值，就是测算结果 -->
								<!-- 
									json_fund_rent_plan_str 租金计划
									json_fund_cash_flow_str 现金流
									json_finance_rent_plan_str 会计租金计划
									json_finance_cash_flow_str 会计现金流
									json_fund_fund_charge_str 资金收付计划
									json_knowing_rent_plan_str 已知租金法完整json
									json_payment_premise_condition_str  付款前提条件
								 -->  
						        <input name="json_payment_premise_condition_str"id="id_json_payment_premise_condition_str" type="hidden"  value='${empty json_payment_premise_condition_str ? "[]" : json_payment_premise_condition_str}'/><%-- 付款前提条件 --%>
								<input name="json_fund_rent_plan_str" 	 		id="id_json_fund_rent_plan_str" 		type="hidden" value='${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}'/><%-- 租金计划 --%>
								<input name="json_fund_cash_flow_str" 			id="id_json_fund_cash_flow_str" 		type="hidden" value='${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str }'/><%-- 现金流 --%>
								<input name="json_finance_rent_plan_str" 		id="id_json_finance_rent_plan_str" 		type="hidden" value='${empty json_finance_rent_plan_str ? "[]" : json_finance_rent_plan_str }'/><%-- 会计租金计划 --%>
								<input name="json_finance_cash_flow_str" 		id="id_json_finance_cash_flow_str" 		type="hidden" value='${empty json_finance_cash_flow_str ? "[]" : json_finance_cash_flow_str }'/><%-- 会计现金流 --%>
								<input name="json_fund_fund_charge_str" 		id="id_json_fund_fund_charge_str" 		type="hidden" value='${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'/><%-- 资金首付计划 --%>
								<input name="json_knowing_rent_plan_str" 		id="id_json_knowing_rent_plan_str"  	type="hidden" value='${empty json_knowing_rent_plan_str ? "[]" : json_knowing_rent_plan_str }'/><!-- 已知租金法的租金列表 -->
								<input name="json_knowing_corpus_plan_str" 		id="id_json_knowing_corpus_plan_str"  	type="hidden" value='${empty json_knowing_corpus_plan_str ? "[]" : json_knowing_corpus_plan_str }'/><!-- 已知本金法的租金列表 -->	
								<input name="json_fund_put_config_str" 			id="id_json_fund_put_config_str"  		type="hidden" value='${empty json_fund_put_config_str ? "[]" : json_fund_put_config_str }'/><!-- 投放计划列表 -->	
								<input name="json_special_regular_str" 			id="id_json_special_regular_str"  		type="hidden" value='${empty json_special_regular_str ? "[]" : json_special_regular_str }'/><!-- 分段配置列表 -->	
								<input name="json_grace_plan_str" 			id="id_json_grace_plan_str"  		type="hidden" value='${empty json_grace_plan_str ? "[]" : json_grace_plan_str }'/><!-- 宽限期收款计划 -->	
								<input name="json_income_discount_str" 			id="id_json_income_discount_str"  		type="hidden" value='${empty json_income_discount_str ? "[]" : json_income_discount_str }'/><!-- 收入折现计划 -->
			             	    <input name="equipamt" id ="equipamt" required vtype="double" label ="设备款" vtype="double"  class="mini-textbox"   value="${empty requestScope['equipamt'] ? 0 : requestScope['equipamt'] }"></td>
			              <td class="td-content-title">首付款：</td>
			              <td class="td-content"><input name="firstpayment" label ="首付款" vtype="double"  id ="firstpayment"  class="mini-textbox"  value="${empty requestScope['firstpayment'] ? 0 : requestScope['firstpayment'] }" ></td>
			              <td class="td-content-title">首付比例：</td>
			              <td class="td-content"><input name="firstpaymentratio" id ="firstpaymentratio"  class="mini-textbox" value="${empty requestScope['firstpaymentratio'] ? 0 : requestScope['firstpaymentratio'] }" readonly><font class="required-tag">%</font></td>
			             
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			             
			          </tr>
			               
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">租金测算本金：</td>
			              <td class="td-content"><input name="cleanleasemoney" readonly  vtype="double"   id ="cleanleasemoney"  class="mini-textbox" value="${empty requestScope['cleanleasemoney'] ? 0 : requestScope['cleanleasemoney'] }"></td>
			              <td class="td-content-title">净融资额：</td>
			              <td class="td-content"><input name="cleancreditmoney" vtype="double"  id ="cleancreditmoney"  class="mini-textbox" value="${empty requestScope['cleancreditmoney'] ? 0 : requestScope['cleancreditmoney'] }" readonly ></td>
			              <td class="td-content-title">净融资额比例：</td>
			              <td class="td-content"><input name="cleancreditratio" id ="cleancreditratio"  class="mini-textbox" value="${empty requestScope['cleancreditratio'] ? 0 : requestScope['cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"></td>
			              <td class="td-content"></td>	
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="租金推算方式" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			     <tr class="tr-project-info tr-even">
			              <td class="td-content-title">租金计算方式：</td>
			              <td class="td-content">
			               	<input id="settlemethod" required name="settlemethod" class="mini-combobox" textField="name"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'settle_method'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${empty requestScope['settlemethod'] ? 'special_regular' : requestScope['settlemethod'] }" 
								   text="${empty requestScope['rawValue_settlemethod'] ? '规则测算' : requestScope['rawValue_settlemethod'] }"
							   />
							   <input id="rawValue_settlemethod" name="rawValue_settlemethod" value="${requestScope['rawValue_settlemethod']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">还租时点：</td>
				          <td class="td-content">
					              <input id="periodtype" name="periodtype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   allowInput="false" 
										   dataField="datas"
										   data-options="{_params:{pid:'period_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['periodtype'] ? 'period_type_0' : requestScope['periodtype'] }" 
										   text="${empty requestScope['rawValue_periodtype'] ? '期末' : requestScope['rawValue_periodtype'] }"
								  />
								  <input id="rawValue_periodtype" name="rawValue_periodtype" value="${requestScope['rawValue_periodtype']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">还租次数：</td>  
			              <td class="td-content"><input name="incomenumber" required id ="incomenumber" vtype="int" class="mini-textbox"  value="${empty requestScope['incomenumber'] ? 0 : requestScope['incomenumber'] }"   ></td>
			              <td class="td-content-title">租赁期限(月)：</td>
			              <td class="td-content"><input name="leaseterm" id ="leaseterm"  class="mini-textbox"  value="${empty requestScope['leaseterm'] ? 0 : requestScope['leaseterm'] }"></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">还租频率：</td>
			              <td class="td-content">
				              <input id="incomenumberyear" name="incomenumberyear" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'income_number_year'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
									   	   value="${empty requestScope['incomenumberyear'] ? 'income_1' : requestScope['incomenumberyear'] }" 
								   		   text="${empty requestScope['rawValue_incomenumberyear'] ? '月  付' : requestScope['rawValue_incomenumberyear'] }"
							   />
							   <input id="rawValue_incomenumberyear" name="rawValue_incomenumberyear" value="${requestScope['rawValue_incomenumberyear']}" class="mini-textbox" style="display:none"/>
						  </td>
			              <td class="td-content-title">利率计算方式：</td>
			              <td class="td-content">
				              <input id="ratefloattype" required name="ratefloattype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'rate_float_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['ratefloattype'] ? 'fixed' : requestScope['ratefloattype'] }"  
									   	   text="${empty requestScope['rawValue_ratefloattype'] ? '固定利率' : requestScope['rawValue_ratefloattype'] }"
							  />
							  <input id="rawValue_ratefloattype" name="rawValue_ratefloattype" value="${requestScope['rawValue_ratefloattype']}" class="mini-textbox" style="display:none"/>
						   </td>
			              <td class="td-content-title">宽限期(期)：</td>
			              <td class="td-content"><input name="grace" id ="grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['grace'] ? 0 : requestScope['grace'] }"  ></td>
			             <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			             </tr>
			            <tr class="tr-project-info tr-even">
			              <td class="td-content-title">基准利率：</td>
			              <td class="td-content"><input name="baserate" vtype="rate" id ="baserate"  class="mini-textbox" value="${empty requestScope['baserate'] ? 0 : requestScope['baserate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">宽限期利率调整值：</td>
			              <td class="td-content"><input name="graceadjust" id ="graceadjust"  class="mini-textbox" value="${empty requestScope['graceadjust'] ? 0 : requestScope['graceadjust'] }"   vtype="rate"></td>
			              <td class="td-content-title">宽限期年利率：</td>
						  <td class="td-content"><input name="gracerate" id ="gracerate"  vtype="double"   class="mini-textbox" value="${empty requestScope['gracerate'] ? 0 : requestScope['gracerate'] }"  ><font class="required-tag">%</font></td>
						   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			            </tr> 
			           <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">首笔付款日：</td>
			              <td class="td-content"><input name="leaseamtdate" required onvaluechanged="changeDate" id ="leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['leaseamtdate']}"></td>
			              <td class="td-content-title" >起租日：</td>   
			              <td class="td-content"><input name="startdate" id ="startdate" required  class="mini-datepicker"  allowInput="false" value="${requestScope['startdate']}" ></td>
			              <td class="td-content-title">第一期租金支付日：</td>
			              <td class="td-content"><input name="firstplandate" required id ="firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['firstplandate']}" ></td>
			          	   <td class="td-content-title"></td>
			          	   <td class="td-content"></td>
			          </tr>
			          
				</table>
			</div>
			<div id="condition_fund_info" class="mini-panel" title="资金信息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">手续费收取间隔：</td>
			          	  <td class="td-content">
			          	  	<input id="handhz"  name="handhz" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   showNullItem="true"
								   nullItemText=""
								   data-options="{_params:{pid:'hand_hz'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${requestScope['handhz']}"
								   text="${requestScope['rawValue_handhz']}"
							   />
							   <input id="rawValue_handhz" name="rawValue_handhz" value="${requestScope['rawValue_handhz']}" class="mini-textbox" style="display:none"/>
			          	  	</td>
			          	  <td class="td-content-title">手续费收取方式：</td>
			          	  <td class="td-content">
			          	  	<input id="handtype"  name="handtype" label="手续费收取方式"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['handtype'] ? '' : requestScope['handtype'] }"
								   text="${empty requestScope['rawValue_handtype'] ? '' : requestScope['rawValue_handtype'] }"
								   data-options="{_params:{pid:'period_type'}}" 
							   />
							   <input id="rawValue_handtype" name="rawValue_handtype" value="${requestScope['rawValue_handtype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费计算基数：</td>
			          	  <td class="td-content">
			          	  	<input id="handmoney"  name="handmoney" label="手续费比例计算基数"  class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   onvaluechanged="comboboxChanged"
								   value="${empty requestScope['handmoney'] ? '' : requestScope['handmoney'] }"
								   text="${empty requestScope['rawValue_handmoney'] ? '' : requestScope['rawValue_handmoney'] }"
								   data-options="{_params:{pid:'hand_ratio'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_handmoney" name="rawValue_handmoney" value="${requestScope['rawValue_handmoney']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          	  <td class="td-content-title">手续费比例：</td>
			          	  <td class="td-content"><input name="handratio" vtype="double"  label="手续费比例" id ="handratio"  class="mini-textbox" value="${empty requestScope['handratio'] ? 0 : requestScope['handratio'] }" ><font class="required-tag">%</font></td>
			          </tr>    
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">咨询服务费：</td>
			              <td class="td-content"><input name="managementmoney" vtype="double" label="管理费"   id ="managementmoney"  class="mini-textbox" value="${empty requestScope['managementmoney'] ? 0 : requestScope['managementmoney'] }" ></td>
			              <td class="td-content-title">咨询服务费比例：</td>
			              <td class="td-content"><input name="managementmoneyratio" id ="managementmoneyratio"  class="mini-textbox" value="${empty requestScope['managementmoneyratio'] ? 0 : requestScope['managementmoneyratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">提前还款补偿金：</td>
			              <td class="td-content"><input name="advancerepaymoney" vtype="double" label="提前还款补偿金"   id ="advancerepaymoney"  class="mini-textbox" value="${empty requestScope['advancerepaymoney'] ? 0 : requestScope['advancerepaymoney'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">承租人保证金：</td>   
			              <td class="td-content"><input name="cautionmoney" vtype="double" label="保证金" id ="cautionmoney"  class="mini-textbox"  value="${empty requestScope['cautionmoney'] ? 0 : requestScope['cautionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金比例：</td>
			              <td class="td-content"><input name="cautiondeductionratio" id ="cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['cautiondeductionratio'] ? 0 : requestScope['cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">承租人保证金抵扣金额：</td> 
			              <td class="td-content"><input name="cautiondeductionmoney" label="保证金抵扣金额" vtype="double"  id ="cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['cautiondeductionmoney'] ? 0 : requestScope['cautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">承租人保证金退还金额：</td>
			              <td class="td-content"><input name="cautionmoneyremain" label="保证金退还金额" vtype="double"  id ="cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['cautionmoneyremain'] ? 0 : requestScope['cautionmoneyremain'] }" readonly></td>
			          </tr>
			         	
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">名义留购价：</td>   
			              <td class="td-content"><input name="nominalprice" label="留购价款" vtype="double"  id ="nominalprice"  class="mini-textbox" value="${empty requestScope['nominalprice'] ? 10000 : requestScope['nominalprice'] }" ></td>
			              <td class="td-content-title">厂商返利（含税）：</td><td class="td-content"><input name="returnamt" id ="returnamt"  class="mini-textbox" value="${empty requestScope['returnamt'] ? 0 : requestScope['returnamt'] }"></td> 
			              
			              
			              <td class="td-content-title">其他收入：</td>
			              <td class="td-content"><input name="otherincome" label="其他收入" vtype="double"  id ="otherincome"  class="mini-textbox" value="${empty requestScope['otherincome'] ? 0 : requestScope['otherincome'] }" ></td>
			              <td class="td-content-title">其他支出：</td>
			              <td class="td-content"><input name="otherexpenditure" label="其他支出"  vtype="double"  id ="otherexpenditure"  class="mini-textbox" value="${empty requestScope['otherexpenditure'] ? 0 : requestScope['otherexpenditure'] }" ></td>
			          </tr>
				</table>
			</div>
			<div class="mini-panel" title="调息/罚息" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息方式：</td>
			              <td class="td-content">
			              	  <input id="adjuststyle" name="adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['adjuststyle'] ? 'next_list' : requestScope['adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_adjuststyle'] ? '次期' : requestScope['rawValue_adjuststyle'] }"
							  />	
							  <input id="rawValue_adjuststyle" name="rawValue_adjuststyle" value="${requestScope['rawValue_adjuststyle']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">罚息利率（万分比）：</td>
			              <td class="td-content">
			              		<input id="penarate" name="penarate" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'pena_rate'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['penarate'] ? '5' : requestScope['penarate'] }" 
								   	   text="${empty requestScope['rawValue_penarate'] ? '5' : requestScope['rawValue_penarate'] }"
							  />	
							  <input id="rawValue_penarate" name="rawValue_penarate" value="${requestScope['rawValue_penarate']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title" >IRR：</td>  
			              <td class="td-content">
			              		<input name="irrshow" id ="irrshow" readonly class="mini-textbox" value="${empty requestScope['irr'] ? 0 : requestScope['irr'] }"><font class="required-tag">%</font>
			              		<input name="irr" id ="irr" type="hidden" value="${empty requestScope['irr'] ? 0 : requestScope['irr'] }">
			              </td>
			          	  <td class="td-content-title" >XIRR：</td>  
			              <td class="td-content">
			              		<input name="xirr" id ="xirr" readonly class="mini-textbox" value="${empty requestScope['xirr'] ? 0 : requestScope['xirr'] }"><font class="required-tag">%</font>
			              </td>
						  <td class="td-content-title">项目粗利：</td>
			              <td class="td-content"><input name="grossprofit" vtype="double"  id ="grossprofit" readonly class="mini-textbox" value="${empty requestScope['grossprofit'] ? 0 : requestScope['grossprofit'] }"></td>
						  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="text-indent: 0px;" id="calculateButton">
			              	<a class="mini-button" iconCls="icon-user" plain="true" onclick="save" style="color: red;">租金测算</a>
			              </td>
			          </tr>
				</table>
			</div>
			<%--隐藏域 --%>
			<table style="display: none;"  cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
						  <td class="td-content-title">租前期(月)：</td>
			              <td class="td-content"><input name="prelease" id ="prelease" vtype="int" readOnly class="mini-textbox"   value="${empty requestScope['prelease'] ? 0 : requestScope['prelease'] }"   ></td>
			          	  <td class="td-content-title">厂商保证金：</td>   
			              <td class="td-content"><input name="faccautionmoney" vtype="double" label="厂商保证金" id ="faccautionmoney"  class="mini-textbox"  value="${empty requestScope['faccautionmoney'] ? 0 : requestScope['faccautionmoney'] }"></td>
			              <td class="td-content-title">厂商保证金比例：</td>
			              <td class="td-content"><input name="faccautiondeductionratio" id ="faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['faccautiondeductionratio'] ? 0 : requestScope['faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">厂商保证金抵扣：</td>   
			              <td class="td-content"><input name="faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['faccautiondeductionmoney'] ? 0 : requestScope['faccautiondeductionmoney'] }" ></td>
			              <td class="td-content-title">厂商保证金退还：</td>   
			              <td class="td-content"><input name="faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['faccautionmoneyremain'] ? 0 : requestScope['faccautionmoneyremain'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              
			              <td class="td-content-title">保险计算方式：</td>
			              <td class="td-content">
			              	  <input id="insuremoneytype" name="insuremoneytype" class="mini-combobox" textField="name" style="width: 200PX;"
				                  	   valueField="value"     
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'insure_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
								   	   value="${empty requestScope['insuremoneytype'] ? 'insure_type5' : requestScope['insuremoneytype'] }" 
								   	   text="${empty requestScope['rawValue_insuremoneytype'] ? '客户自保(不提供保单)' : requestScope['rawValue_insuremoneytype'] }"
									   
							  />
							  <input id="rawValue_insuremoneytype" name="rawValue_insuremoneytype" value="${requestScope['rawValue_insuremoneytype']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">保险费率（万分比）：</td>   
			              <td class="td-content">
			              	<input id="insureratio" name="insureratio" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'insureratio'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['insureratio'] ? '0.0' : requestScope['insureratio'] }" 
								   	   text="${empty requestScope['insureratio'] ? '0' : requestScope['insureratio'] }"
							  />	
			              </td>
			              <td class="td-content-title"> 保险费收入：</td>
			              <td class="td-content">
			              	<input name="insurancelessee_show" vtype="double"  label ="保险费收入" id ="insurancelessee_show"  class="mini-textbox" value="${empty requestScope['insurancelessee'] ? 0 : requestScope['insurancelessee'] }" >
			              	<input name="insurancelessee" type="hidden"  id ="insurancelessee"  value="${empty requestScope['insurancelessee'] ? 0 : requestScope['insurancelessee'] }" >
			              </td>
			              <td class="td-content-title"> 保险费支出：</td>
			              <td class="td-content">
			              	<input name="insurancelessor_show" vtype="double"  label ="保险费支出"  id ="insurancelessor_show"  class="mini-textbox" value="${empty requestScope['insurancelessor'] ? 0 : requestScope['insurancelessor'] }" >
			              	<input name="insurancelessor" type="hidden"  id ="insurancelessor"  value="${empty requestScope['insurancelessor'] ? 0 : requestScope['insurancelessor'] }" >
			              </td>
			              
			          </tr>
			   <tr class="tr-project-info tr-odd">
			    <td class="td-content-title">第二期租金支付日：</td>
			              <td class="td-content"><input name="secondplandate" id ="secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['secondplandate']}" ></td>
			          	<td class="td-content-title">前期款坐扣：</td>
			          	  <td class="td-content" colspan="7">
			          	  	<input id="deductiontype" name="deductiontype" class="mini-combobox" textField="name" style="width: 200PX;"
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   multiSelect="true"
								   value="${requestScope['deductiontype'] }"
								   text="${requestScope['rawValue_deductiontype'] }"
								   data="[{name:'首付款',value:'feetype5'},{name:'保证金',value:'feetype2'},{name:'手续费',value:'feetype1'},{name:'咨询费',value:'feetype3'}]" 
							   />
							   <input id="rawValue_deductiontype" name="rawValue_deductiontype" value="${requestScope['rawValue_deductiontype']}" class="mini-textbox" style="display:none"/>
			          	  </td>
			          </tr>
			 <td class="td-content-title">租金推算方法：</td>
			              <td class="td-content">
							   <input id="rentorrate" required name="rentorrate" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"    
								   allowInput="false" 
								   value="${empty requestScope['rentorrate'] ? 'rate' : requestScope['rentorrate'] }"
								   text="${empty requestScope['rawValue_rentorrate'] ? '按年利率计算租金' : requestScope['rawValue_rentorrate'] }"
								   data-options="{_params:{pid:'rent_or_rate'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
							   />
							   <input id="rawValue_rentorrate" name="rawValue_rentorrate" value="${requestScope['rawValue_rentorrate']}" class="mini-textbox" style="display:none"/>
						  </td>
						  
			<tr class="tr-project-info tr-even">
			              <td class="td-content-title">利率调整值：</td>
			              <td class="td-content"><input name="ratefloatamt" id ="ratefloatamt"  class="mini-textbox" value="${empty requestScope['ratefloatamt'] ? 0 : requestScope['ratefloatamt'] }"   vtype="rate"></td>
			              <td class="td-content-title" id="itemTd"><span id="testrent">每期租金:</span><span id="testrate">租赁年利率：</span></td>
			              <td class="td-content"><input name="rentorratevalue" id ="rentorratevalue"  vtype="double"   class="mini-textbox" value="${empty requestScope['rentorratevalue'] ? 0 : requestScope['rentorratevalue'] }"  ></td>
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
             <td class="td-content"><input name="yearrate" id ="yearrate"  class="mini-textbox" value="${empty requestScope['yearrate'] ? 0 : requestScope['yearrate'] }" readonly><font class="required-tag">%</font></td>
			
			 <td class="td-content-title">手续费：</td>
			              <td class="td-content"><input name="handlingchargemoney" vtype="double"  label="手续费" id ="handlingchargemoney"  class="mini-textbox" value="${empty requestScope['handlingchargemoney'] ? 0 : requestScope['handlingchargemoney'] }" ></td>
			              <td class="td-content-title">手续费比例：</td>
			              <td class="td-content"><input name="handlingchargemoneyratio" id ="handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['handlingchargemoneyratio'] ? 0 : requestScope['handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
			<td class="td-content-title">免罚息天数：</td>
			              <td class="td-content"><input name="freedefainterday"  id ="freedefainterday"  class="mini-textbox"  value="${empty requestScope['freedefainterday'] ? requestScope['proj_info.industrytype'] eq 'cust_source.intenal' || empty requestScope['proj_info.industrytype'] ? 0 : 0  : requestScope['freedefainterday'] }"></td> 
			
			<td class="td-content-title">其他融资费：</td>  
			              <td class="td-content"><input name="otherleasemoney" vtype="double"  id ="otherleasemoney" class="mini-textbox" value="${empty requestScope['otherleasemoney'] ? 0 : requestScope['otherleasemoney'] }" ></td>
		<%-- 	<td class="td-content-title">宽限期：</td>
			              <td class="td-content"><input name="grace" id ="grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['grace'] ? 0 : requestScope['grace'] }"   ></td> --%>
			 <td class="td-content-title">期末余值：</td>
			              <td class="td-content"><input name="equipendvalue" id ="equipendvalue"  vtype="double"  class="mini-textbox"  value="${empty requestScope['equipendvalue'] ? 0 : requestScope['equipendvalue'] }" ></td>
			    <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title">返点收入：</td>   
			              <td class="td-content"><input name="returnpointincome" label="返点收入" vtype="double"  id ="returnpointincome"  class="mini-textbox" value="${empty requestScope['returnpointincome'] ? 0 : requestScope['returnpointincome'] }" ></td>
			          	  <td class="td-content-title">利息补贴：</td>   
			              <td class="td-content"><input name="interestsupport" label="利息补贴" vtype="double"  id ="interestsupport"  class="mini-textbox" value="${empty requestScope['interestsupport'] ? 0 : requestScope['interestsupport'] }" ></td>
			          	  <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			              <td class="td-content-title">&nbsp;</td>
			              <td class="td-content" style="width:160px;"></td>
			          </tr>
				<tr class="tr-project-info tr-even">
				     <td class="td-content-title">财务收益率：</td><td class="td-content"><input name="planirr" id ="planirr" readonly class="mini-textbox" value="${empty requestScope['planirr'] ? 0 : requestScope['planirr']}"></td> 
				     <td class="td-content-title">结束日期：</td><td class="td-content"><input name="enddate" id ="enddate" readonly class="mini-textbox" value="${empty requestScope['enddate'] ? 0 : requestScope['enddate']}"></td> 
					 <td class="td-content-title">租前息金额：</td>
			                <c:choose>
			              	<c:when test="${currentDeployId eq '5557'}">
			              		 <td class="td-content"><input name="beforeinterest" id ="beforeinterest"  vtype="double" class="mini-buttonedit" onbuttonclick = "beforeInterestButtonClick"  text="${empty requestScope['beforeinterest'] ? 0 : requestScope['beforeinterest'] }" value="${empty requestScope['beforeinterest'] ? 0 : requestScope['beforeinterest'] }"></td>
			              	</c:when>
			              	<c:otherwise> 	
			              		<td class="td-content"><input name="beforeinterest" label="租前息" vtype="double"  id ="beforeinterest"  class="mini-textbox" value="${empty requestScope['beforeinterest'] ? 0 : requestScope['beforeinterest'] }" ></td>
			              	</c:otherwise>
			              </c:choose> 
					 <td class="td-content-title">期初付款总计：</td><td class="td-content"><input name="firstpaymenttotal" vtype="double"  id ="firstpaymenttotal" class="mini-textbox" value="${empty requestScope['firstpaymenttotal'] ? 0 : requestScope['firstpaymenttotal'] }" readonly></td>
					<td class="td-content-title">保险费：</td>
	                <td class="td-content"><input name="insuremoney" label="保险费"  vtype="double"  id ="insuremoney"  readonly class="mini-textbox" value="0" readonly></td>
				</tr>
				<tr class="tr-project-info tr-odd">
			              <td class="td-content-title">末期租金支付日：</td>
			              <td class="td-content"><input name="lastplandate" id ="lastplandate"   class="mini-datepicker" allowInput="false" value="${requestScope['lastplandate']}" ></td>
			        	   <td class="td-content-title">等额本金计息方式</td>
			        	   <td class="td-content">
			        	   	 <input id="evencorpusratetype" name="evencorpusratetype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'even_corpus_rate_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${requestScope['evencorpusratetype']}" 
								   	   text="${requestScope['rawvalue_evencorpusratetype']}"
							  />	
							  <input id="rawValue_evencorpusratetype" name="rawValue_evencorpusratetype" value="${requestScope['rawValue_evencorpusratetype']}" class="mini-textbox" style="display:none"/>
			        	   </td>
			        	   <td class="td-content-title">利率调整</td>
			        	   <td class="td-content">
			        	   	 <input id="rateadjusttype" name="rateadjusttype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'rate_adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
								   	   value="${empty requestScope['rateadjusttype'] ? 'by_issue' : requestScope['rateadjusttype'] }" 
								   	   text="${empty requestScope['rawValue_rateadjusttype'] ? '按期' : requestScope['rawValue_rateadjusttype'] }"
							  />	
							  <input id="rawValue_rateadjusttype" name="rawValue_rateadjusttype" value="${requestScope['rawValue_rateadjusttype']}" class="mini-textbox" style="display:none"/>
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