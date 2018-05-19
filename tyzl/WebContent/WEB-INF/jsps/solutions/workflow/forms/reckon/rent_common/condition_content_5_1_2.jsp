 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 		<div class="mini-panel" title="授信条件" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info_compare" >
		          <tr class="tr-project-info tr-even">
		              <td class="td-content-title"  >设备款：</td>
		              <td class="td-content">
							<input name="json_fund_rent_plan_new_str" 			id="id_json_fund_rent_plan_new_str" 		type="hidden" value='${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str}'/><%-- 租金计划 --%>
							<input name="json_fund_cash_flow_new_str" 			id="id_json_fund_cash_flow_new_str" 		type="hidden"	 value='${empty json_fund_cash_flow_new_str ? "[]" : json_fund_cash_flow_new_str }'/><%-- 现金流 --%>
							<input name="json_fund_fund_charge_new_str" 		id="id_json_fund_fund_charge_new_str" 		type="hidden"	 value='${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'/><%-- 资金首付计划 --%>
		              <input name="framework_condition.equipamt" id ="framework_condition.equipamt" vtype="double"   class="mini-textbox"  onkeyup="changeequipamtvalue" value="${empty requestScope['equipamt'] ? 0 : requestScope['equipamt'] }"></td>
		              <td class="td-content-title">首付款：</td>
		              <td class="td-content"><input name="framework_condition.firstpayment"  vtype="double"  id ="framework_condition.firstpayment"  class="mini-textbox"  value="${empty requestScope['framework_condition.firstpayment'] ? 0 : requestScope['framework_condition.firstpayment'] }" ></td>
		              <td class="td-content-title">首付比例：</td>
		              <td class="td-content"><input name="framework_condition.firstpaymentratio" vtype="double"  id ="framework_condition.firstpaymentratio"  class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymentratio'] ? 0 : requestScope['framework_condition.firstpaymentratio'] }" readonly><font class="required-tag">%</font></td>
		              <td class="td-content-title">期末余值：</td>
		              <td class="td-content"><input name="framework_condition.equipendvalue" vtype="double"  id ="framework_condition.equipendvalue"  class="mini-textbox"  value="${empty requestScope['framework_condition.equipendvalue'] ? 0 : requestScope['framework_condition.equipendvalue'] }"></td>
		          </tr>
		               
		          <tr class="tr-project-info tr-odd">
		              <td class="td-content-title">其他融资费：</td>  
		              <td class="td-content"><input name="framework_condition.otherleasemoney" vtype="double"  id ="framework_condition.otherleasemoney" class="mini-textbox" value="${empty requestScope['framework_condition.otherleasemoney'] ? 0 : requestScope['framework_condition.otherleasemoney'] }" readonly></td>
		              <td class="td-content-title">融资额：</td>
		              <td class="td-content"><input name="framework_condition.cleanleasemoney" vtype="double"    id ="framework_condition.cleanleasemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleanleasemoney'] ? 0 : requestScope['framework_condition.cleanleasemoney'] }"></td>
		              <td class="td-content-title">风险敞口：</td>
		              <td class="td-content"><input name="framework_condition.cleancreditmoney" vtype="double"  id ="framework_condition.cleancreditmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditmoney'] ? 0 : requestScope['framework_condition.cleancreditmoney'] }" readonly ></td>
		              <td class="td-content-title">净授信比例：</td>
		              <td class="td-content"><input name="framework_condition.cleancreditratio" vtype="double"  id ="framework_condition.cleancreditratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditratio'] ? 0 : requestScope['framework_condition.cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
		          </tr>
			</table>
		</div>
		
		<div class="mini-panel" title="租金推算方式" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
		          <tr class="tr-project-info tr-even">
		              <td class="td-content-title">租金计算方式：</td>
		              <td class="td-content">
		              	   <input id="framework_condition.settlemethod" name="framework_condition.settlemethod" class="mini-combobox" textField="name" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'settle_method'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${empty requestScope['framework_condition.settlemethod'] ? 'even_rent' : requestScope['framework_condition.settlemethod'] }" 
							   text="${empty requestScope['rawValue_framework_condition.settlemethod'] ? '等额租金' : requestScope['rawValue_framework_condition.settlemethod'] }"
							   onvaluechanged="comboboxChanged" 
							   emptyText=""
							   showNullItem="true" 
							   nullItemText=""
						   />
						   <input id="rawValue_framework_condition.settlemethod" name="rawValue_framework_condition.settlemethod" value="${requestScope['rawValue_framework_condition.settlemethod']}" class="mini-textbox" style="display:none"/>
		              </td>
		              <td class="td-content-title">租金推算方法：</td>
		              <td class="td-content">
						   <input id="framework_condition.rentorrate" name="framework_condition.rentorrate" class="mini-combobox" textField="name" 
		                  	   valueField="value"  
							   dataField="datas"    
							   allowInput="false" 
							   value="${empty requestScope['framework_condition.rentorrate'] ? 'rate' : requestScope['framework_condition.rentorrate'] }"
							   text="${empty requestScope['rawValue_framework_condition.rentorrate'] ? '按年利率计算租金' : requestScope['rawValue_framework_condition.rentorrate'] }"
							   data-options="{_params:{pid:'rent_or_rate'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   onvaluechanged="rentorratevaluechange"
							   onitemclick="rentorrateitemchange"
						   />
						   <input id="rawValue_framework_condition.rentorrate" name="rawValue_framework_condition.rentorrate" value="${requestScope['rawValue_framework_condition.rentorrate']}" class="mini-textbox" style="display:none"/>
					  </td>
		              <td class="td-content-title">利率计算方式：</td>
		              <td class="td-content">
			              <input id="framework_condition.ratefloattype" name="framework_condition.ratefloattype" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'rate_float_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="ratefloattypevaluechange"
									   value="${empty requestScope['framework_condition.ratefloattype'] ? 'fixed' : requestScope['framework_condition.ratefloattype'] }"  
								   	   text="${empty requestScope['rawValue_framework_condition.ratefloattype'] ? '固定利率' : requestScope['rawValue_framework_condition.ratefloattype'] }"
						  />
						  <input id="rawValue_framework_condition.ratefloattype" name="rawValue_framework_condition.ratefloattype" value="${requestScope['rawValue_framework_condition.ratefloattype']}" class="mini-textbox" style="display:none"/>
					   </td>
		              <td class="td-content-title"><span id="framework_condition.testrent">测算租金:</span><span id="framework_condition.testrate">年租息率：</span></td>
		              <td class="td-content"><input name="framework_condition.rentorratevalue" id ="framework_condition.rentorratevalue"   class="mini-textbox" value="${empty requestScope['framework_condition.rentorratevalue'] ? 0 : requestScope['framework_condition.rentorratevalue'] }" onkeyup="rentorratevaluekeyup"></td>
		          </tr>
		          
		          <tr class="tr-project-info tr-odd">
		               <td class="td-content-title">期初/期末：</td>
		              <td class="td-content">
			              <input id="framework_condition.periodtype" name="framework_condition.periodtype" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   allowInput="false" 
								   dataField="datas"
								   data-options="{_params:{pid:'period_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${empty requestScope['framework_condition.periodtype'] ? 'period_type_0' : requestScope['framework_condition.periodtype'] }" 
								   text="${empty requestScope['rawValue_framework_condition.periodtype'] ? '期末' : requestScope['rawValue_framework_condition.periodtype'] }"
								   onvaluechanged="comboboxChanged"
						  />
						  <input id="rawValue_framework_condition.periodtype" name="rawValue_framework_condition.periodtype" value="${requestScope['rawValue_framework_condition.periodtype']}" class="mini-textbox" style="display:none"/>
					  </td>
		              <td class="td-content-title">租金支付类型：</td>
		              <td class="td-content">
			              <input id="framework_condition.incomenumberyear" name="framework_condition.incomenumberyear" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'income_number_year'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="incomenumberyearvaluechange"
								   	   value="${empty requestScope['framework_condition.incomenumberyear'] ? 'income_1' : requestScope['framework_condition.incomenumberyear'] }" 
							   		   text="${empty requestScope['rawValue_framework_condition.incomenumberyear'] ? '月  付' : requestScope['rawValue_framework_condition.incomenumberyear'] }"
									   emptyText=""
									   showNullItem="true" 
									   nullItemText=""
						   />
						   <input id="rawValue_framework_condition.incomenumberyear" name="rawValue_framework_condition.incomenumberyear" value="${requestScope['rawValue_framework_condition.incomenumberyear']}" class="mini-textbox" style="display:none"/>
					  </td>
		              <td class="td-content-title">还租次数：</td>  
		              <td class="td-content">
		              	<input name="framework_condition.incomenumbershow" id ="framework_condition.incomenumbershow"  class="mini-textbox"  value="${empty requestScope['framework_condition.incomenumber'] ? 0 : requestScope['framework_condition.incomenumber'] }" >
		              	<input name="framework_condition.incomenumber" id ="framework_condition.incomenumber"  type="hidden"  value="${empty requestScope['framework_condition.incomenumber'] ? 0 : requestScope['framework_condition.incomenumber'] }" >
		              </td>
		              <td class="td-content-title">宽限期：</td>
		              <td class="td-content"><input name="framework_condition.grace" vtype="double"  id ="framework_condition.grace"  class="mini-textbox"   value="${empty requestScope['framework_condition.grace'] ? 0 : requestScope['framework_condition.grace'] }" ></td>
		          </tr>
		          <tr class="tr-project-info tr-even">
		              <td class="td-content-title">租赁期限(月)：</td>
		              <td class="td-content">
		              	 <input name="framework_condition.leasetermshow" id ="framework_condition.leasetermshow"  class="mini-textbox"  value="${empty requestScope['framework_condition.leaseterm'] ? 0 : requestScope['framework_condition.leaseterm'] }">
		              	<input name="framework_condition.leaseterm" id ="framework_condition.leaseterm"  type="hidden"  value="${empty requestScope['framework_condition.leaseterm'] ? 0 : requestScope['framework_condition.leaseterm'] }">
		              </td>
		            <td></td>
		          	<td></td>
		          	<td></td>
		          	<td></td>
		          	<td></td>
		          	<td></td>
		          </tr>
			</table>
		</div>
		
		<div class="mini-panel" title="测算条件" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
		          <tr class="tr-project-info tr-even">
		          <td class="td-content-title">付款日：</td>
		              <td class="td-content"><input name="framework_condition.leaseamtdate" id ="framework_condition.leaseamtdate"   class="mini-datepicker" allowInput="false"  value="${requestScope['framework_condition.leaseamtdate']}"></td>
		              <td class="td-content-title" >起租日：</td>   
		              <td class="td-content"><input name="framework_condition.startdate" id ="framework_condition.startdate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.startdate']}" ></td>
		              <td class="td-content-title">第一期租金支付日：</td>
		              <td class="td-content"><input name="framework_condition.firstplandate" id ="framework_condition.firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.firstplandate']}" ></td>
		              <td class="td-content-title">第二期租金支付日：</td>
		              <td class="td-content"><input name="framework_condition.secondplandate" id ="framework_condition.secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.secondplandate']}" ></td>
		          </tr>
		            
			</table>
		</div>
		<div class="mini-panel" title="资金信息" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
		          <tr class="tr-project-info tr-even">
		              <td class="td-content-title">服务费：</td>
		              <td class="td-content"><input name="framework_condition.handlingchargemoney" vtype="double"    id ="framework_condition.handlingchargemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoney'] ? 0 : requestScope['framework_condition.handlingchargemoney'] }" ></td>
		              <td class="td-content-title">服务费比例：</td>
		              <td class="td-content"><input name="framework_condition.handlingchargemoneyratio" vtype="double"  id ="framework_condition.handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoneyratio'] ? 0 : requestScope['framework_condition.handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
		              <td class="td-content-title">咨询费：</td>
		              <td class="td-content"><input name="framework_condition.managementmoney" vtype="double"    id ="framework_condition.managementmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoney'] ? 0 : requestScope['framework_condition.managementmoney'] }" ></td>
		              <td class="td-content-title">咨询费比例：</td>
		              <td class="td-content"><input name="framework_condition.managementmoneyratio" vtype="double"  id ="framework_condition.managementmoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoneyratio'] ? 0 : requestScope['framework_condition.managementmoneyratio'] }"  readonly><font class="required-tag">%</font></td>
		          </tr>    
		          <tr class="tr-project-info tr-odd">
		          	  
		              <td class="td-content-title">保证金：</td>   
		              <td class="td-content"><input name="framework_condition.cautionmoney" vtype="double"   id ="framework_condition.cautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.cautionmoney'] ? 0 : requestScope['framework_condition.cautionmoney'] }" ></td>
		              <td class="td-content-title">保证金比例：</td>
		              <td class="td-content"><input name="framework_condition.cautiondeductionratio" vtype="double"  id ="framework_condition.cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionratio'] ? 0 : requestScope['framework_condition.cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
		              <td class="td-content-title">保证金抵扣金额：</td> 
		              <td class="td-content"><input name="framework_condition.cautiondeductionmoney"   vtype="double"  id ="framework_condition.cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionmoney'] ? 0 : requestScope['framework_condition.cautiondeductionmoney'] }" ></td>
		              <td class="td-content-title">保证金退还金额：</td>
		              <td class="td-content"><input name="framework_condition.cautionmoneyremain" vtype="double"   id ="framework_condition.cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['framework_condition.cautionmoneyremain'] ? 0 : requestScope['framework_condition.cautionmoneyremain'] }" readonly></td>
		          </tr>
		          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">待投保年费率：</td>
			              <td class="td-content"><input name="framework_condition.insurerate" id ="framework_condition.insurerate" vtype="rate" label="待投保年费率"  class="mini-textbox" value="${empty requestScope['framework_condition.insurerate'] ? 0 : requestScope['framework_condition.insurerate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保收入：</td>   
			              <td class="td-content"><input name="framework_condition.insureincome" vtype="double" label="投保收入" id ="framework_condition.insureincome"  class="mini-textbox"  value="${empty requestScope['framework_condition.insureincome'] ? 0 : requestScope['insureincome'] }" readonly></td>
			              <td class="td-content-title">投保成本年费率：</td> 
			              <td class="td-content"><input name="framework_condition.insurecostrate" label="投保成本年费率" vtype="rate"  id ="framework_condition.insurecostrate"  class="mini-textbox" value="${empty requestScope['framework_condition.insurecostrate'] ? 0 : requestScope['framework_condition.insurecostrate'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保支出：</td>
			              <td class="td-content"><input name="framework_condition.insureexpenditure" label="投保支出" vtype="double"  id ="framework_condition.insureexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition.insureexpenditure'] ? 0 : requestScope['framework_condition.insureexpenditure'] }" readonly></td>
			          </tr>
		          <tr class="tr-project-info tr-odd">
		              
		              <td class="td-content-title">保险计算方式：</td>
		              <td class="td-content">
		              	  <input id="framework_condition.insuremoneytype" name="framework_condition.insuremoneytype" class="mini-combobox" textField="name" 
			                  	   valueField="value"     
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'insure_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="insuremoneytypevaluechange"
							   	   value="${empty requestScope['framework_condition.insuremoneytype'] ? 'insure_type1' : requestScope['framework_condition.insuremoneytype'] }" 
							   	   text="${empty requestScope['rawValue_framework_condition.insuremoneytype'] ? '本司付款' : requestScope['rawValue_framework_condition.insuremoneytype'] }"
								   emptyText=""
								   showNullItem="true" 
								   nullItemText=""
						  />
						  <input id="rawValue_framework_condition.insuremoneytype" name="rawValue_framework_condition.insuremoneytype" value="${requestScope['rawValue_framework_condition.insuremoneytype']}" class="mini-textbox" style="display:none"/>
		              </td>
		              <td class="td-content-title"> 保险费(承租人)：</td>
		              <td class="td-content"><input name="framework_condition.insurancelessee" vtype="double"  id ="framework_condition.insurancelessee"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessee'] ? 0 : requestScope['framework_condition.insurancelessee'] }" ></td>
		              <td class="td-content-title"> 保险费(我司)：</td>
		              <td class="td-content"><input name="framework_condition.insurancelessor" vtype="double"  id ="framework_condition.insurancelessor"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessor'] ? 0 : requestScope['framework_condition.insurancelessor'] }" ></td>
		              <td class="td-content-title">保险费：</td>
		              <td class="td-content"><input name="framework_condition.insuremoney" vtype="double"  id ="framework_condition.insuremoney"  readonly class="mini-textbox" value="${empty requestScope['framework_condition.insuremoney'] ? 0 : requestScope['framework_condition.insuremoney'] }" readonly></td>
		          </tr>
		          <tr class="tr-project-info tr-even">
		          	  <td class="td-content-title">留购价款：</td>   
		              <td class="td-content"><input name="framework_condition.nominalprice" vtype="double"  id ="framework_condition.nominalprice"  class="mini-textbox" value="${empty requestScope['framework_condition.nominalprice'] ? 0 : requestScope['framework_condition.nominalprice'] }"></td>
		              <td class="td-content-title">租前息金额：</td>
		              <td class="td-content"><input name="framework_condition.beforeinterest" vtype="double"  id ="framework_condition.beforeinterest"  class="mini-textbox" value="${empty requestScope['framework_condition.beforeinterest'] ? 0 : requestScope['framework_condition.beforeinterest'] }"></td>
		              <td class="td-content-title">其他收入：</td>
		              <td class="td-content"><input name="framework_condition.otherincome" vtype="double"  id ="framework_condition.otherincome"  class="mini-textbox" value="${empty requestScope['framework_condition.otherincome'] ? 0 : requestScope['framework_condition.otherincome'] }"></td>
		              <td class="td-content-title">其他支出：</td>
		              <td class="td-content"><input name="framework_condition.otherexpenditure" vtype="double"  id ="framework_condition.otherexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition.otherexpenditure'] ? 0 : requestScope['framework_condition.otherexpenditure'] }"></td>
		          </tr>
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
			</table>
		</div>
		<div class="mini-panel" title="调息/罚息" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
		          <tr class="tr-project-info tr-even">
		              <td class="td-content-title" >调息生效节点：</td>
		              <td class="td-content">
		              	  <input id="framework_condition.adjuststyle" name="framework_condition.adjuststyle" class="mini-combobox" textField="name" 
			                  	   valueField="value"  
								   dataField="datas"  
								   allowInput="false" 
								   data-options="{_params:{pid:'adjust_style'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   onvaluechanged="comboboxChanged"
								   emptyText=""
								   showNullItem="true" 
								   nullItemText=""
							   	   value="${empty requestScope['framework_condition.adjuststyle'] ? 'next_list' : requestScope['framework_condition.adjuststyle'] }" 
							   	   text="${empty requestScope['rawValue_framework_condition.adjuststyle'] ? '次期' : requestScope['rawValue_framework_condition.adjuststyle'] }"
						  />	
						  <input id="rawValue_framework_condition.adjuststyle" name="rawValue_framework_condition.adjuststyle" value="${requestScope['rawValue_framework_condition.adjuststyle']}" class="mini-textbox" style="display:none"/>
		              </td>
		              <td class="td-content-title">罚息利率(万分之)：</td>
		              <td class="td-content"><input name="framework_condition.penarate" id ="framework_condition.penarate"  class="mini-textbox"  value="${empty requestScope['framework_condition.penarate'] ? 0 : requestScope['framework_condition.penarate'] }">&#8241;</td>
		              <td class="td-content-title">免罚息天数：</td>
		              <td class="td-content"><input name="framework_condition.freedefainterday" vtype="double"  id ="framework_condition.freedefainterday"  class="mini-textbox"  value="${empty requestScope['framework_condition.freedefainterday'] ? 0 : requestScope['framework_condition.freedefainterday'] }"></td>
		              <td class="td-content-title">&nbsp;</td>
		              <td class="td-content" style="width:160px;"></td>
		          </tr>
			</table>
		</div>
		<div class="mini-panel" title="收益" showCollapseButton="true" style="width: 100%;">
		    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
		          <tr class="tr-project-info tr-even">
		          	  <td class="td-content-title" >内部收益率：</td>  
		              <td class="td-content">
		              <input name="framework_condition.irrshow" id ="framework_condition.irrshow" readonly class="mini-textbox" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }">
		              	<input name="framework_condition.irr" id ="framework_condition.irr" type="hidden" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }">
					  <td class="td-content-title" >剔除保证金IRR：</td>  
		              <td class="td-content">
		              		<input name="framework_condition.killirr" id ="framework_condition.killirr" readonly class="mini-textbox" value="${empty requestScope['framework_condition.killirr'] ? 0 : requestScope['framework_condition.killirr'] }"><font class="required-tag">%</font>
		              </td>
		              <td class="td-content-title" >平息年利率：</td>  
		              <td class="td-content">
		              		<input name="framework_condition.subsiderate" id ="framework_condition.subsiderate" readonly class="mini-textbox" value="${empty requestScope['framework_condition.subsiderate'] ? 0 : requestScope['framework_condition.subsiderate'] }"><font class="required-tag">%</font>
		              </td>
					  <td class="td-content-title">项目粗利：</td>
		              <td class="td-content"><input name="framework_condition.grossprofit" vtype="double"  id ="framework_condition.grossprofit" readonly class="mini-textbox" value="${empty requestScope['framework_condition.grossprofit'] ? 0 : requestScope['framework_condition.grossprofit'] }"></td>
		          </tr>
			</table>
		</div>
		
		<table style="display: none;"  cellspacing="0" cellpadding="0">
				<tr class="tr-project-info tr-even">
				     <td class="td-content-title">财务收益率：</td><td class="td-content"><input name="framework_condition.planirr" id ="framework_condition.planirr" readonly class="mini-textbox" value="${empty requestScope['framework_condition.planirr'] ? 0 : requestScope['framework_condition.planirr']}"></td> 
				     <td class="td-content-title">结束日期：</td><td class="td-content"><input name="framework_condition.enddate" id ="framework_condition.enddate" readonly class="mini-textbox" value="${empty requestScope['framework_condition.enddate'] ? 0 : requestScope['framework_condition.enddate']}"></td> 
					 <td class="td-content-title">厂商返利：</td><td class="td-content"><input name="framework_condition.returnamt" id ="framework_condition.returnamt"  class="mini-textbox" value="${empty requestScope['framework_condition.returnamt'] ? 0 : requestScope['framework_condition.returnamt'] }"></td> 
					 <td class="td-content-title">期初付款总计：</td>  <td class="td-content"><input name="framework_condition.firstpaymenttotal" vtype="double"  id ="framework_condition.firstpaymenttotal" class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymenttotal'] ? 0 : requestScope['framework_condition.firstpaymenttotal'] }" readonly></td>
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
								   	   value="${empty requestScope['framework_condition.evencorpusratetype'] ? 'issue' : requestScope['framework_condition.evencorpusratetype'] }" 
								   	   text="${empty requestScope['rawvalue_framework_condition.evencorpusratetype'] ? '按期' : requestScope['rawvalue_framework_condition.evencorpusratetype'] }"
							  />	
							  <input id="rawValue_framework_condition.evencorpusratetype" name="rawValue_framework_condition.evencorpusratetype" value="${requestScope['rawvalue_framework_condition.evencorpusratetype']}" class="mini-textbox" style="display:none"/>
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
			          
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">厂商保证金：</td>   
			              <td class="td-content"><input name="framework_condition.faccautionmoney" vtype="double" label="厂商保证金" id ="framework_condition.faccautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoney'] ? 0 : requestScope['framework_condition.faccautionmoney'] }" ></td>
			              <td class="td-content-title">厂商保证金比例：</td>
			              <td class="td-content"><input name="framework_condition.faccautiondeductionratio" id ="framework_condition.faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.faccautiondeductionratio'] ? 0 : requestScope['framework_condition.faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">厂商保证金抵扣：</td>   
			              <td class="td-content"><input name="framework_condition.faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="framework_condition.faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautiondeductionmoney'] ? 0 : requestScope['framework_condition.faccautiondeductionmoney'] }"></td>
			              <td class="td-content-title">厂商保证金退还：</td>   
			              <td class="td-content"><input name="framework_condition.faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="framework_condition.faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoneyremain'] ? 0 : requestScope['framework_condition.faccautionmoneyremain'] }" ></td>
			      </tr> 
			      <tr class="tr-project-info tr-odd">
			     	 <td class="td-content-title">利率调整值：</td>
		              <td class="td-content"><input name="framework_condition.ratefloatamt" vtype="double"  id ="framework_condition.ratefloatamt"  class="mini-textbox" value="${empty requestScope['framework_condition.ratefloatamt'] ? 0 : requestScope['framework_condition.ratefloatamt'] }" ></td>
		              <td class="td-content-title">基准利率：</td>
		              <td class="td-content"><input name="framework_condition.baserate" vtype="double"  id ="framework_condition.baserate"  class="mini-textbox" value="${empty requestScope['framework_condition.baserate'] ? 0 : requestScope['framework_condition.baserate'] }" ></td>
		              <td class="td-content-title">年利率：</td>
		              <td class="td-content"><input name="framework_condition.yearrate" vtype="double"  id ="framework_condition.yearrate"  class="mini-textbox" value="${empty requestScope['framework_condition.yearrate'] ? 0 : requestScope['framework_condition.yearrate'] }" readonly><font class="required-tag">%</font></td>
			      </tr>
		</table>
		