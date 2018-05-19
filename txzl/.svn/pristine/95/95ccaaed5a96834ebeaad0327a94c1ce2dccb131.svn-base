<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div id="businessconditionForm" title="保理测算商务条件">
	 		 <div class="mini-panel" title="测算条件" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info0" >
			    	 <%-- <tr class="tr-project-info tr-even">
			    	 	  <td class="td-content-title"  ></td>
			             <td class="td-content">
			             <input name="json_fund_rent_plan_new_str" 			id="id_json_fund_rent_plan_new_str" 		type="hidden" value='${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str}'/>租金计划
						 <input name="json_fund_cash_flow_new_str" 			id="id_json_fund_cash_flow_new_str" 		type="hidden"	 value='${empty json_fund_cash_flow_new_str ? "[]" : json_fund_cash_flow_new_str }'/>现金流
						 <input name="json_fund_fund_charge_new_str" 		id="id_json_fund_fund_charge_new_str" 		type="hidden"	 value='${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'/>资金首付计划
			             </td>
			    	 </tr> --%>
			    	 <tr class="tr-project-info tr-even">
			    	 	 <td class="td-content-title"  >应收账款转让日：</td>
			             <td class="td-content">
			              	<input name="leaseamtdate" required id ="leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['leaseamtdate']}" readonly>
			              </td>
			             <td class="td-content-title"  >期限(天)：</td>
			             <td class="td-content">
			              	<input name="leasetermday" required id ="leasetermday" vtype="int" class="mini-textbox"  value="${empty requestScope['leasetermday'] ? 0 : requestScope['leasetermday'] }" readonly  >
			             </td>
			        <%--  </tr>
			         <tr class="tr-project-info tr-even">
			          <td class="td-content-title">基准利率</td>
			              <td class="td-content"><input name="baserate" vtype="rate" id ="baserate"  class="mini-textbox" value="${empty requestScope['baserate'] ? 0 : requestScope['baserate'] }" readonly><font class="required-tag">%</font></td> --%>
			             <td class="td-content-title">利率浮动比率</td>   
			              <td class="td-content"><input name="ratefloat" vtype="rate" id ="ratefloat"  class="mini-textbox" value="${empty requestScope['ratefloat'] ? 0 : requestScope['ratefloat'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">合同利率(年)：</td>
			              <td class="td-content"><input name="factoringflatrateyear1" vtype="rate" id ="factoringflatrateyear1"  class="mini-textbox" value="${empty requestScope['factoringflatrateyear'] ? 0 : requestScope['factoringflatrateyear'] }" readonly><font class="required-tag">%</font></td>	          	  
			    	 </tr>
			 	</table>
			</div>   
		    <div class="mini-panel" title="授信条件" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id=" proj_base_info1" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >应收账款金额：</td>
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
								 -->
								<input name="json_fund_rent_plan_new_str" 	 	id="id_json_fund_rent_plan_str" 		type="hidden" value='${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str}'/><%-- 租金计划 --%>
								<input name="json_fund_cash_flow_new_str" 		id="id_json_fund_cash_flow_str" 		type="hidden" value='${empty json_fund_cash_flow_new_str ? "[]" : json_fund_cash_flow_new_str }'/><%-- 现金流 --%>
								<input name="json_finance_rent_plan_str" 		id="id_json_finance_rent_plan_str" 		type="hidden" value='${empty json_finance_rent_plan_str ? "[]" : json_finance_rent_plan_str }'/><%-- 会计租金计划 --%>
								<input name="json_finance_cash_flow_str" 		id="id_json_finance_cash_flow_str" 		type="hidden" value='${empty json_finance_cash_flow_str ? "[]" : json_finance_cash_flow_str }'/><%-- 会计现金流 --%>
								<input name="json_fund_fund_charge_new_str" 	id="id_json_fund_fund_charge_str" 		type="hidden" value='${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'/><%-- 资金首付计划 --%>
								<input name="json_knowing_rent_plan_str" 		id="id_json_knowing_rent_plan_str"  	type="hidden" value='${empty json_knowing_rent_plan_str ? "[]" : json_knowing_rent_plan_str }'/><!-- 已知租金法的租金列表 -->
								<input name="json_knowing_corpus_plan_str" 		id="id_json_knowing_corpus_plan_str"  	type="hidden" value='${empty json_knowing_corpus_plan_str ? "[]" : json_knowing_corpus_plan_str }'/><!-- 已知本金法的租金列表 -->	
			             	    <input name="equipamt" id ="equipamt" required vtype="double" label ="应收账款金额" vtype="double"  class="mini-textbox"   value="${empty requestScope['equipamt'] ? 0 : requestScope['equipamt'] }" readonly></td>
			              <td class="td-content-title">应收账款比例：</td>
			              <td class="td-content"><input name="equipamtratio" vtype="rate" id ="equipamtratio"  class="mini-textbox" value="${empty requestScope['equipamtratio'] ? 100 : requestScope['equipamtratio'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">保证金：</td>   
			              <td class="td-content"><input name="cautionmoney" datachangefield vtype="double" label="保证金" id ="cautionmoney"  class="mini-textbox"  value="${empty requestScope['cautionmoney'] ? 0 : requestScope['cautionmoney'] }" readonly ></td>
			              <td class="td-content-title">保证金比例：</td>
			              <td class="td-content"><input name="cautiondeductionratio" vtype="rate" id ="cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['cautiondeductionratio'] ? 0 : requestScope['cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			          </tr>
			               
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >应收账款受让款：</td> 
			              <td class="td-content"><input name="factoringpayout" readonly datachangefield vtype="double"  id ="factoringpayout" class="mini-textbox" value="${empty requestScope['factoringpayout'] ? 0 : requestScope['factoringpayout'] }" readonly></td>
			              <td class="td-content-title">应收账款受让款比例：</td>
			              <td class="td-content"><input name="factoringpayoutratio" vtype="rate"  id ="factoringpayoutratio"  class="mini-textbox" value="${empty requestScope['factoringpayoutratio'] ? 0 : requestScope['factoringpayoutratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >咨询服务费收入：</td> 
			              <td class="td-content"><input name="counselingmoney" datachangefield vtype="double"  id ="counselingmoney" class="mini-textbox" value="${empty requestScope['counselingmoney'] ? 0 : requestScope['counselingmoney'] }" readonly></td>
			              <td class="td-content-title">咨询服务费收入比例：</td>
			              <td class="td-content"><input name="counselingratio" vtype="rate" id ="counselingratio"  class="mini-textbox" value="${empty requestScope['counselingratio'] ? 0 : requestScope['counselingratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >保理费收入：</td> 
			              <td class="td-content"><input name="factoringincome" readonly datachangefield vtype="double"  id ="factoringincome" class="mini-textbox" value="${empty requestScope['factoringincome'] ? 0 : requestScope['factoringincome'] }" readonly></td>
			              <td class="td-content-title">保理费收入比例：</td>
			              <td class="td-content"><input name="factoringincomeratio" vtype="rate" id ="factoringincomeratio"  class="mini-textbox" value="${empty requestScope['factoringincomeratio'] ? 0 : requestScope['factoringincomeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >手续费收入：</td> 
			              <td class="td-content"><input name="handlingchargemoney" datachangefield vtype="double"  id ="handlingchargemoney" class="mini-textbox" value="${empty requestScope['handlingchargemoney'] ? 0 : requestScope['handlingchargemoney'] }" readonly></td>
			              <td class="td-content-title">手续费收入比例：</td>
			              <td class="td-content"><input name="handlingchargemoneyratio" vtype="rate" id ="handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['handlingchargemoneyratio'] ? 0 : requestScope['handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >保险费收入：</td> 
			              <td class="td-content"><input name="insuremoney" vtype="double" datachangefield id ="insuremoney" class="mini-textbox" value="${empty requestScope['insuremoney'] ? 0 : requestScope['insuremoney'] }" readonly></td>
			              <td class="td-content-title">保险费收入比例：</td>
			              <td class="td-content"><input name="insuremoneyratio" vtype="rate" id ="insuremoneyratio"  class="mini-textbox" value="${empty requestScope['insuremoneyratio'] ? 0 : requestScope['insuremoneyratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >其他收入：</td> 
			              <td class="td-content"><input name="otherfeerec" vtype="double" datachangefield  id ="otherfeerec" class="mini-textbox" value="${empty requestScope['otherfeerec'] ? 0 : requestScope['otherfeerec'] }" readonly></td>
			              <td class="td-content-title">其他收入比例：</td>
			              <td class="td-content"><input name="otherfeerecratio" vtype="rate" id ="otherfeerecratio"  class="mini-textbox" value="${empty requestScope['otherfeerecratio'] ? 0 : requestScope['otherfeerecratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >担保费收入：</td> 
			              <td class="td-content"><input name="factoringguaranteefee" datachangefield vtype="double"  id ="factoringguaranteefee" class="mini-textbox" value="${empty requestScope['insuremoney'] ? 0 : requestScope['insuremoney'] }" readonly></td>
			              <td class="td-content-title">担保费收入比例：</td>
			              <td class="td-content"><input name="factoringguaranteefeeratio" vtype="rate" id ="factoringguaranteefeeratio"  class="mini-textbox" value="${empty requestScope['factoringguaranteefeeratio'] ? 0 : requestScope['factoringguaranteefeeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >其他成本：</td> 
			              <td class="td-content"><input name="otherfee" vtype="double" datachangefield  id ="otherfee" class="mini-textbox" value="${empty requestScope['otherfee'] ? 0 : requestScope['otherfee'] }" readonly></td>
			              <td class="td-content-title">其他成本比例：</td>
			              <td class="td-content"><input name="otherfeeratio" vtype="rate" id ="otherfeeratio"  class="mini-textbox" value="${empty requestScope['otherfeeratio'] ? 0 : requestScope['otherfeeratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >登记费收入：</td> 
			              <td class="td-content"><input name="factoringregisterfee" datachangefield vtype="double"  id ="factoringregisterfee" class="mini-textbox" value="${empty requestScope['factoringregisterfee'] ? 0 : requestScope['factoringregisterfee'] }" readonly></td>
			              <td class="td-content-title">登记费收入比例：</td>
			              <td class="td-content"><input name="factoringregisterfeeratio" vtype="rate" id ="factoringregisterfeeratio"  class="mini-textbox" value="${empty requestScope['factoringregisterfeeratio'] ? 0 : requestScope['factoringregisterfeeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  ></td> 
			              <td class="td-content"></td>
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			          </tr>
			           <tr class="tr-project-info tr-odd" hidden="hidden">
			              <td class="td-content-title"  >销售额：</td> 
			              <td class="td-content"><input name="salesvolume" readonly vtype="double" datachangefield id ="salesvolume" class="mini-textbox" value="${empty requestScope['salesvolume'] ? 0 : requestScope['salesvolume'] }" readonly></td>
			              <td class="td-content-title">销售额比例：</td>
			              <td class="td-content"><input name="salesvolumeratio" vtype="rate" id ="salesvolumeratio"  class="mini-textbox" value="${empty requestScope['salesvolumeratio'] ? 0 : requestScope['salesvolumeratio'] }" readonly><font class="required-tag">%</font></td>
			           </tr>
			          <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title"  >净融资额：</td> 
			              <td class="td-content"><input name="actualfund" readonly vtype="double" datachangefield  id ="actualfund" class="mini-textbox" value="${empty requestScope['actualfund'] ? 0 : requestScope['actualfund'] }" readonly></td>
			              <td class="td-content-title">净融资额比例：</td>
			              <td class="td-content"><input name="actualfundradio" vtype="rate" id ="actualfundradio"  class="mini-textbox" value="${empty requestScope['actualfundradio'] ? 0 : requestScope['actualfundradio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
				</table>
			</div>
			
			<div class="mini-panel" title="保理收益" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id=" proj_base_info2" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >平面利率（月）：</td> 
			              <td class="td-content"><input name="factoringflatratemonth" vtype="rate"  id ="factoringflatratemonth" class="mini-textbox" value="${empty requestScope['factoringflatratemonth'] ? 0 : requestScope['factoringflatratemonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">名义利率(月)：</td>
			              <td class="td-content"><input name="factoringyearratemonth" vtype="rate" id ="factoringyearratemonth"  class="mini-textbox" value="${empty requestScope['factoringyearratemonth'] ? 0 : requestScope['factoringyearratemonth'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >资金成本(月)：</td> 
			              <td class="td-content"><input name="factoringfundcostmonth" vtype="rate"  id ="factoringfundcostmonth" class="mini-textbox" value="${empty requestScope['factoringfundcostmonth'] ? 0 : requestScope['factoringfundcostmonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title" hidden="hidden">IRR(月)：</td>
			              <td class="td-content" hidden="hidden"><input name="factoringirrmonth" vtype="rate" id ="factoringirrmonth"  class="mini-textbox" value="${empty requestScope['factoringirrmonth'] ? 0 : requestScope['factoringirrmonth'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">平面利率(年)：</td>
			              <td class="td-content"><input name="factoringflatrateyear" vtype="rate" id ="factoringflatrateyear"  class="mini-textbox" value="${empty requestScope['factoringflatrateyear'] ? 0 : requestScope['factoringflatrateyear'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title" >名义利率(年)：</td> 
			              <td class="td-content"><input name="factoringyearrateyear" vtype="rate"  id ="factoringyearrateyear" class="mini-textbox" value="${empty requestScope['factoringyearrateyear'] ? 0 : requestScope['factoringyearrateyear'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">资金成本(年)：</td>
			              <td class="td-content"><input name="factoringfundcostyear" vtype="rate" id ="factoringfundcostyear"  class="mini-textbox" value="${empty requestScope['factoringfundcostyear'] ? 0 : requestScope['factoringfundcostyear'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title" hidden="hidden">IRR(年)：</td> 
			              <td class="td-content" hidden="hidden"><input name="factoringirryear" vtype="rate"  id ="factoringirryear" class="mini-textbox" value="${empty requestScope['factoringirryear'] ? 0 : requestScope['factoringirryear'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          <td class="td-content-title" ></td>
			          <td class="td-content"></td>
			          <td class="td-content-title" ></td>
			          <td class="td-content"></td>
			          <td class="td-content-title" ></td>
			          <td class="td-content"></td>
			           <td class="td-content" colspan="2" style="text-indent: 0px;" id="calculateButton">
			              <a class="mini-button"  iconCls="icon-user"  plain="true" onclick="save" style="color: red;margin-left: 200px;">收益测算</a>
			              </td>
			              </tr>
			          
			          <tr class="tr-project-info tr-even" hidden="hidden">
			              <td class="td-content-title" >Spread(月)：</td> 
			              <td class="td-content"><input name="factoringspreadmonth" id="factoringspreadmonth" vtype="rate"  id ="factoringspread" class="mini-textbox" value="${empty requestScope['factoringspreadmonth'] ? 0 : requestScope['factoringspreadmonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">Spread(年)：</td>
			              <td class="td-content"><input name="factoringspreadyear" id="factoringspreadyear" vtype="rate" id ="factoringspreadyear"  class="mini-textbox" value="${empty requestScope['factoringspreadyear'] ? 0 : requestScope['factoringspreadyear'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title" >GP：</td> 
			              <td class="td-content"><input name="gp" vtype="double"  id ="gp" class="mini-textbox" value="${empty requestScope['gp'] ? 0 : requestScope['gp'] }" readonly></td>
			              <!-- <td class="td-content" colspan="2" style="text-indent: 0px;" id="calculateButton"> -->
			              <!-- 	<a class="mini-button"  iconCls="icon-user"  plain="true" onclick="save" style="color: red;margin-left: 200px;">租金测算</a> -->
			              </td>
			          </tr>
				</table>
			</div>
		</div>
	
	