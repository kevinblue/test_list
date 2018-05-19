<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <div id="businessconditionForm0" title="保理测算商务条件">
	 		 <div class="mini-panel" title="测算条件" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="condition_frame_proj_base_info0" >
			    	<tr class="tr-project-info tr-even">
			    	 	 <td class="td-content-title"  >应收账款转让日：</td>
			             <td class="td-content">
			              	<input name="framework_condition.leaseamtdate" required id ="framework_condition.leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.leaseamtdate']}" readonly>
			              </td>
			             <td class="td-content-title"  >期限(天)：</td>
			             <td class="td-content">
			              	<input name="framework_condition.leasetermday" required id ="framework_condition.leasetermday" vtype="int" class="mini-textbox"  value="${empty requestScope['framework_condition.leasetermday'] ? 0 : requestScope['framework_condition.leasetermday'] }" readonly  >
			             </td>
			         <%-- </tr>
			         <tr class="tr-project-info tr-even">
			          <td class="td-content-title">基准利率</td>
			              <td class="td-content"><input name="baserate" vtype="rate" id ="baserate"  class="mini-textbox" value="${empty requestScope['baserate'] ? 0 : requestScope['baserate'] }" readonly><font class="required-tag">%</font></td> --%>
			             <td class="td-content-title">利率浮动比率</td>   
			              <td class="td-content"><input name="framework_condition.ratefloat" vtype="rate" id ="framework_condition.ratefloat"  class="mini-textbox" value="${empty requestScope['framework_condition.ratefloat'] ? 0 : requestScope['framework_condition.ratefloat'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">合同利率(年)：</td>
			              <td class="td-content"><input name="framework_condition.factoringflatrateyear1" vtype="rate" id ="framework_condition.factoringflatrateyear1"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringflatrateyear'] ? 0 : requestScope['framework_condition.factoringflatrateyear'] }" readonly><font class="required-tag">%</font></td>	          	  
			    	 </tr>
			 	</table>
			</div>   
		    <div class="mini-panel" title="授信条件" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="condition_frame_proj_base_info1" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >应收账款金额：</td>
			              <td class="td-content">
			             		<!-- 下边的值是一些关建行信息值，包跨项目阶段的项目编号，合同阶段的合同编号，客户报价时客户编号  //实际授信月数(申请书需用到)//保证金抵扣期数-->
							  <input name="condition_frame_id" 			id="condition_frame_conditionId"  		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.id']}"/>
							  <input name="condition_frame_custid" 		id="condition_frame_conditionCustId" 	style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.custid']}"/>
							  <input name="condition_frame_contractid" 	id="condition_frame_conditionContractId" 	style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.contractid']}"/>
							  <input name="condition_frame_projid" 		id="condition_frame_conditionProjId" 		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.projid']}"/>
							  <input name="condition_frame_docid" 		id="condition_frame_conditionDocId" 		style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.docid']}"/> 
							  <input name="condition_frame_creditmonths"id="condition_frame_creditmonths" 	        style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.creditmonths']}"/> 
							  <input name="condition_frame_dcnum" 		id="condition_frame_dcnum" 	        		style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.dcnum']?'0':requestScope['framework_condition.dcnum'] }"/>
							  <input name="condition_frame_custname" 		id="condition_frame_custname" 	        		style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.custname']?'':requestScope['framework_condition.custname'] }"/>
							  
							  <!-- 下面是关于测算的关键值 -->
							    <input name="condition_frame_process" 		id="condition_frame_reckonProcess" 			style="display:none;"	class="mini-textbox" value="${requestScope['framework_condition.process']}"/>
								<input name="condition_frame_reckontype" 	id="condition_frame_reckonType"  			style="display:none;"	class="mini-textbox" value="${empty requestScope['framework_condition.reckontype']?'condition':requestScope['framework_condition.reckontype'] }"/>
								
								<!-- 下边的是租金计划的值，就是测算结果 -->
								<!-- 
									json_fund_rent_plan_str 租金计划
									json_fund_cash_flow_str 现金流
									json_finance_rent_plan_str 会计租金计划
									json_finance_cash_flow_str 会计现金流
									json_fund_fund_charge_str 资金收付计划
									json_knowing_rent_plan_str 已知租金法完整json
								 -->
								<input name="condition_frame_json_fund_rent_plan_str" 	 		id="condition_frame_id_json_fund_rent_plan_str" 		type="hidden" value='${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}'/><%-- 租金计划 --%>
								<input name="condition_frame_json_fund_cash_flow_str" 			id="condition_frame_id_json_fund_cash_flow_str" 		type="hidden" value='${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str }'/><%-- 现金流 --%>
								<input name="condition_frame_json_finance_rent_plan_str" 		id="condition_frame_id_json_finance_rent_plan_str" 		type="hidden" value='${empty json_finance_rent_plan_str ? "[]" : json_finance_rent_plan_str }'/><%-- 会计租金计划 --%>
								<input name="condition_frame_json_finance_cash_flow_str" 		id="condition_frame_id_json_finance_cash_flow_str" 		type="hidden" value='${empty json_finance_cash_flow_str ? "[]" : json_finance_cash_flow_str }'/><%-- 会计现金流 --%>
								<input name="condition_frame_json_fund_fund_charge_str" 		id="condition_frame_id_json_fund_fund_charge_str" 		type="hidden" value='${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'/><%-- 资金首付计划 --%>
								<input name="condition_frame_json_knowing_rent_plan_str" 		id="condition_frame_id_json_knowing_rent_plan_str"  	type="hidden" value='${empty json_knowing_rent_plan_str ? "[]" : json_knowing_rent_plan_str }'/><!-- 已知租金法的租金列表 -->
								<input name="condition_frame_json_knowing_corpus_plan_str" 		id="condition_frame_id_json_knowing_corpus_plan_str"  	type="hidden" value='${empty json_knowing_corpus_plan_str ? "[]" : json_knowing_corpus_plan_str }'/><!-- 已知本金法的租金列表 -->	
			             	    <input name="condition_frame_equipamt" id ="framework_condition.equipamt" required vtype="double" label ="应收账款金额" vtype="double"  class="mini-textbox"   value="${empty requestScope['framework_condition.equipamt'] ? 0 : requestScope['framework_condition.equipamt'] }" readonly></td>
			              <td class="td-content-title">应收账款比例：</td>
			              <td class="td-content"><input name="condition_frame_equipamtratio" vtype="rate" id ="framework_condition.equipamtratio"  class="mini-textbox" value="${empty requestScope['framework_condition.equipamtratio'] ? 100 : requestScope['framework_condition.equipamtratio'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">保证金：</td>   
			              <td class="td-content"><input name="condition_frame_cautionmoney" datachangefield vtype="double" label="保证金" id ="framework_condition.cautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.cautionmoney'] ? 0 : requestScope['framework_condition.cautionmoney'] }" readonly ></td>
			              <td class="td-content-title">保证金比例：</td>
			              <td class="td-content"><input name="condition_frame_cautiondeductionratio" vtype="rate" id ="framework_condition.cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionratio'] ? 0 : requestScope['framework_condition.cautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
			          </tr>
			               
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >应收账款受让款：</td> 
			              <td class="td-content"><input name="condition_frame_factoringpayout" readonly datachangefield vtype="double"  id ="framework_condition.factoringpayout" class="mini-textbox" value="${empty requestScope['framework_condition.factoringpayout'] ? 0 : requestScope['framework_condition.factoringpayout'] }" readonly></td>
			              <td class="td-content-title">应收账款受让款比例：</td>
			              <td class="td-content"><input name="condition_frame_factoringpayoutratio" vtype="rate"  id ="framework_condition.factoringpayoutratio"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringpayoutratio'] ? 0 : requestScope['framework_condition.factoringpayoutratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >咨询服务费收入：</td> 
			              <td class="td-content"><input name="condition_frame_counselingmoney" datachangefield vtype="double"  id ="framework_condition.counselingmoney" class="mini-textbox" value="${empty requestScope['framework_condition.counselingmoney'] ? 0 : requestScope['framework_condition.counselingmoney'] }" readonly></td>
			              <td class="td-content-title">咨询服务费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_counselingratio" vtype="rate" id ="framework_condition.counselingratio"  class="mini-textbox" value="${empty requestScope['framework_condition.counselingratio'] ? 0 : requestScope['framework_condition.counselingratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >保理费收入：</td> 
			              <td class="td-content"><input name="condition_frame_factoringincome" readonly datachangefield vtype="double"  id ="framework_condition.factoringincome" class="mini-textbox" value="${empty requestScope['framework_condition.factoringincome'] ? 0 : requestScope['framework_condition.factoringincome'] }" readonly></td>
			              <td class="td-content-title">保理费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_factoringincomeratio" vtype="rate" id ="framework_condition.factoringincomeratio"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringincomeratio'] ? 0 : requestScope['framework_condition.factoringincomeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >手续费收入：</td> 
			              <td class="td-content"><input name="condition_frame_handlingchargemoney" datachangefield vtype="double"  id ="framework_condition.handlingchargemoney" class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoney'] ? 0 : requestScope['framework_condition.handlingchargemoney'] }" readonly></td>
			              <td class="td-content-title">手续费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_handlingchargemoneyratio" vtype="rate" id ="framework_condition.handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoneyratio'] ? 0 : requestScope['framework_condition.handlingchargemoneyratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >保险费收入：</td> 
			              <td class="td-content"><input name="condition_frame_insuremoney" vtype="double" datachangefield id ="framework_condition.insuremoney" class="mini-textbox" value="${empty requestScope['framework_condition.insuremoney'] ? 0 : requestScope['framework_condition.insuremoney'] }" readonly></td>
			              <td class="td-content-title">保险费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_insuremoneyratio" vtype="rate" id ="framework_condition.insuremoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.insuremoneyratio'] ? 0 : requestScope['framework_condition.insuremoneyratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >其他收入：</td> 
			              <td class="td-content"><input name="condition_frame_otherfeerec" vtype="double" datachangefield  id ="framework_condition.otherfeerec" class="mini-textbox" value="${empty requestScope['framework_condition.otherfeerec'] ? 0 : requestScope['framework_condition.otherfeerec'] }" readonly></td>
			              <td class="td-content-title">其他收入比例：</td>
			              <td class="td-content"><input name="condition_frame_otherfeerecratio" vtype="rate" id ="framework_condition.otherfeerecratio"  class="mini-textbox" value="${empty requestScope['framework_condition.otherfeerecratio'] ? 0 : requestScope['framework_condition.otherfeerecratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >担保费收入：</td> 
			              <td class="td-content"><input name="condition_frame_factoringguaranteefee" datachangefield vtype="double"  id ="framework_condition.factoringguaranteefee" class="mini-textbox" value="${empty requestScope['framework_condition.insuremoney'] ? 0 : requestScope['framework_condition.insuremoney'] }" readonly></td>
			              <td class="td-content-title">担保费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_factoringguaranteefeeratio" vtype="rate" id ="framework_condition.factoringguaranteefeeratio"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringguaranteefeeratio'] ? 0 : requestScope['framework_condition.factoringguaranteefeeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >其他成本：</td> 
			              <td class="td-content"><input name="condition_frame_otherfee" vtype="double" datachangefield  id ="framework_condition.otherfee" class="mini-textbox" value="${empty requestScope['framework_condition.otherfee'] ? 0 : requestScope['framework_condition.otherfee'] }" readonly></td>
			              <td class="td-content-title">其他成本比例：</td>
			              <td class="td-content"><input name="condition_frame_otherfeeratio" vtype="rate" id ="framework_condition.otherfeeratio"  class="mini-textbox" value="${empty requestScope['framework_condition.otherfeeratio'] ? 0 : requestScope['framework_condition.otherfeeratio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title"  >登记费收入：</td> 
			              <td class="td-content"><input name="condition_frame_factoringregisterfee" datachangefield vtype="double"  id ="framework_condition.factoringregisterfee" class="mini-textbox" value="${empty requestScope['framework_condition.factoringregisterfee'] ? 0 : requestScope['framework_condition.factoringregisterfee'] }" readonly></td>
			              <td class="td-content-title">登记费收入比例：</td>
			              <td class="td-content"><input name="condition_frame_factoringregisterfeeratio" vtype="rate" id ="framework_condition.factoringregisterfeeratio"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringregisterfeeratio'] ? 0 : requestScope['framework_condition.factoringregisterfeeratio'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  ></td> 
			              <td class="td-content"></td>
			              <td class="td-content-title"></td>
			              <td class="td-content"></td>
			          </tr>
			           <tr class="tr-project-info tr-odd" hidden="hidden">
			              <td class="td-content-title"  >销售额：</td> 
			              <td class="td-content"><input name="condition_frame_salesvolume" readonly vtype="double" datachangefield id ="framework_condition.salesvolume" class="mini-textbox" value="${empty requestScope['framework_condition.salesvolume'] ? 0 : requestScope['framework_condition.salesvolume'] }" readonly></td>
			              <td class="td-content-title">销售额比例：</td>
			              <td class="td-content"><input name="condition_frame_salesvolumeratio" vtype="rate" id ="framework_condition.salesvolumeratio"  class="mini-textbox" value="${empty requestScope['framework_condition.salesvolumeratio'] ? 0 : requestScope['framework_condition.salesvolumeratio'] }" readonly><font class="required-tag">%</font></td>
			           </tr>
			          <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title"  >净融资额：</td> 
			              <td class="td-content"><input name="condition_frame_actualfund" readonly vtype="double" datachangefield  id ="framework_condition.actualfund" class="mini-textbox" value="${empty requestScope['framework_condition.actualfund'] ? 0 : requestScope['framework_condition.actualfund'] }" readonly></td>
			              <td class="td-content-title">净融资额比例：</td>
			              <td class="td-content"><input name="condition_frame_actualfundradio" vtype="rate" id ="framework_condition.actualfundradio"  class="mini-textbox" value="${empty requestScope['framework_condition.actualfundradio'] ? 0 : requestScope['framework_condition.actualfundradio'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
				</table>
			</div>
			
			<div class="mini-panel" title="保理收益" showCollapseButton="true" style="width: 100%;">
			    <table class="fillTable" cellspacing="0" cellpadding="0" id="condition_frame_proj_base_info2" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >合同利率（月）：</td> 
			              <td class="td-content"><input name="condition_frame_factoringflatratemonth" vtype="rate"  id ="framework_condition.factoringflatratemonth" class="mini-textbox" value="${empty requestScope['framework_condition.factoringflatratemonth'] ? 0 : requestScope['framework_condition.factoringflatratemonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">名义利率(月)：</td>
			              <td class="td-content"><input name="condition_frame_factoringyearratemonth" vtype="rate" id ="framework_condition.factoringyearratemonth"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringyearratemonth'] ? 0 : requestScope['framework_condition.factoringyearratemonth'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title"  >资金成本(月)：</td> 
			              <td class="td-content"><input name="condition_frame_factoringfundcostmonth" vtype="rate"  id ="framework_condition.factoringfundcostmonth" class="mini-textbox" value="${empty requestScope['framework_condition.factoringfundcostmonth'] ? 0 : requestScope['framework_condition.factoringfundcostmonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title" hidden="hidden">IRR(月)：</td>
			              <td class="td-content" hidden="hidden"><input name="condition_frame_factoringirrmonth" vtype="rate" id ="framework_condition.factoringirrmonth"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringirrmonth'] ? 0 : requestScope['framework_condition.factoringirrmonth'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">合同利率(年)：</td>
			              <td class="td-content"><input name="condition_frame_factoringflatrateyear" vtype="rate" id ="framework_condition.factoringflatrateyear"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringflatrateyear'] ? 0 : requestScope['framework_condition.factoringflatrateyear'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title" >名义利率(年)：</td> 
			              <td class="td-content"><input name="condition_frame_factoringyearrateyear" vtype="rate"  id ="framework_condition.factoringyearrateyear" class="mini-textbox" value="${empty requestScope['framework_condition.factoringyearrateyear'] ? 0 : requestScope['framework_condition.factoringyearrateyear'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">资金成本(年)：</td>
			              <td class="td-content"><input name="condition_frame_factoringfundcostyear" vtype="rate" id ="framework_condition.factoringfundcostyear"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringfundcostyear'] ? 0 : requestScope['framework_condition.factoringfundcostyear'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title" hidden="hidden">IRR(年)：</td> 
			              <td class="td-content" hidden="hidden"><input name="condition_frame_factoringirryear" vtype="rate"  id ="framework_condition.factoringirryear" class="mini-textbox" value="${empty requestScope['framework_condition.factoringirryear'] ? 0 : requestScope['framework_condition.factoringirryear'] }" readonly><font class="required-tag">%</font></td>
			          </tr>
			          <tr class="tr-project-info tr-even"hidden="hidden">
			              <td class="td-content-title" >Spread(月)：</td> 
			              <td class="td-content"><input name="condition_frame_factoringspreadmonth" id="condition_frame_factoringspreadmonth" vtype="rate"  id ="framework_condition.factoringspreadmonth" class="mini-textbox" value="${empty requestScope['framework_condition.factoringspreadmonth'] ? 0 : requestScope['framework_condition.factoringspreadmonth'] }" readonly><font class="required-tag">%</font></td>
			              <td class="td-content-title">Spread(年)：</td>
			              <td class="td-content"><input name="condition_frame_factoringspreadyear" id="condition_frame_factoringspreadyear" vtype="rate" id ="framework_condition.factoringspreadyear"  class="mini-textbox" value="${empty requestScope['framework_condition.factoringspreadyear'] ? 0 : requestScope['framework_condition.factoringspreadyear'] }" readonly><font class="required-tag">%</font></td>
			          	  <td class="td-content-title" >GP：</td> 
			              <td class="td-content"><input name="condition_frame_gp" vtype="double"  id ="framework_condition.gp" class="mini-textbox" value="${empty requestScope['framework_condition.gp'] ? 0 : requestScope['framework_condition.gp'] }" readonly></td>
			             <!--  <td class="td-content" colspan="2" style="text-indent: 0px;" id="condition_frame_calculateButton">
			              	<a class="mini-button"  iconCls="icon-user"  plain="true" onclick="save" style="color: red;margin-left: 200px;">租金测算</a> -->
			              </td>
			          </tr>
				</table>
			</div>
		</div>
	
	