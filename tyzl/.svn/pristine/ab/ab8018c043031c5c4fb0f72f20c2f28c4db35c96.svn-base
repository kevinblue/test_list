<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="comparebusinessconditionForm" >
	<div class="mini-tabs" activeIndex="0"   style="width: 100%" id="calTabDetailsBase" onactivechanged="calChangTab">
		<div title="租金计划表" iconCls="icon-cut">
		 <table class="fillTable" cellspacing="0" cellpadding="0" id="rent_cal_info" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title"  >设备款：</td>
			              <td class="td-content">
								<input name="json_fund_rent_plan_new_str" 	 		id="id_json_fund_rent_plan_new_str" 		type="hidden" value='${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str}'/><%-- 租金计划 --%>
								<input name="json_fund_cash_flow_new_str" 			id="id_json_fund_cash_flow_new_str" 		type="hidden" value='${empty json_fund_cash_flow_new_str ? "[]" : json_fund_cash_flow_new_str }'/><%-- 现金流 --%>
								<input name="json_fund_fund_charge_new_str" 		id="id_json_fund_fund_charge_new_str" 		type="hidden" value='${empty json_fund_fund_charge_new_str ? "[]" : json_fund_fund_charge_new_str }'/><%-- 资金首付计划 --%>
			             	    <input name="json_special_regular_new_str" 		    id="id_json_special_regular_new_str"  	    type="hidden" value='${empty json_special_regular_new_str ? "[]" : json_special_regular_new_str }'/><!-- 特殊规则列表 -->
			             	    <input name="framework_condition.equipamt" id ="framework_condition.equipamt" required vtype="double" label ="设备款" vtype="double"  class="mini-textbox"   value="${empty requestScope['framework_condition.equipamt'] ? 0 : requestScope['framework_condition.equipamt'] }"><font class="required-tag">*</font></td>
			              <td class="td-content-title">首付比例：</td>
			              <td class="td-content"><input name="framework_condition.firstpaymentratio" id ="framework_condition.firstpaymentratio"  class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymentratio'] ? 0 : requestScope['framework_condition.firstpaymentratio'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">首付款：</td>
			              <td class="td-content"><input name="framework_condition.firstpayment" label ="首付款" vtype="double"  id ="framework_condition.firstpayment"  class="mini-textbox"  value="${empty requestScope['framework_condition.firstpayment'] ? 0 : requestScope['framework_condition.firstpayment'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">融资额：</td>
			              <td class="td-content"><input name="framework_condition.cleanleasemoney" readonly  vtype="double"   id ="framework_condition.cleanleasemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleanleasemoney'] ? 0 : requestScope['framework_condition.cleanleasemoney'] }"></td>
			              <td class="td-content-title">风险敞口：</td>
			              <td class="td-content"><input name="framework_condition.cleancreditmoney" vtype="double"  id ="framework_condition.cleancreditmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditmoney'] ? 0 : requestScope['framework_condition.cleancreditmoney'] }" readonly ></td>
			              <td class="td-content-title">净授信比例：</td>
			              <td class="td-content"><input name="framework_condition.cleancreditratio" id ="framework_condition.cleancreditratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cleancreditratio'] ? 0 : requestScope['framework_condition.cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
			             
			          </tr>
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
							   	   text="${empty requestScope['rawValue_framework_condition.settlemethod'] ? '分段测算' : requestScope['rawValue_framework_condition.settlemethod'] }"
							   /><font class="required-tag">*</font>
							   <input id="rawValue_framework_condition.settlemethod" name="rawValue_framework_condition.settlemethod" value="${requestScope['rawValue_framework_condition.settlemethod']}" class="mini-textbox" style="display:none"/>
			              </td>
			               <td class="td-content-title">期末余值：</td>
			              <td class="td-content"><input name="framework_condition.equipendvalue" id ="framework_condition.equipendvalue"  vtype="double"  class="mini-textbox"  value="${empty requestScope['framework_condition.equipendvalue'] ? 0 : requestScope['framework_condition.equipendvalue'] }" ></td>
						   <td class="td-content-title">利率计算方式：</td>
			              <td class="td-content">
				              <input id="framework_condition.ratefloattype" required name="framework_condition.ratefloattype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'rate_float_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['framework_condition.ratefloattype'] ? 'proportion' : requestScope['framework_condition.ratefloattype'] }"  
									   	   text="${empty requestScope['rawValue_framework_condition.ratefloattype'] ? '按央行浮动比率' : requestScope['rawValue_framework_condition.ratefloattype'] }"
							  /><font class="required-tag">*</font>
							  <input id="rawValue_framework_condition.ratefloattype" name="rawValue_framework_condition.ratefloattype" value="${requestScope['rawValue_framework_condition.ratefloattype']}" class="mini-textbox" style="display:none"/>
						   </td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title">基准利率：</td>
			              <td class="td-content"><input name="framework_condition.baserate" vtype="double" id ="framework_condition.baserate"  class="mini-textbox" value="${empty requestScope['framework_condition.baserate'] ? 0 : requestScope['framework_condition.baserate'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">利率调整值：</td>
			              <td class="td-content"><input name="framework_condition.ratefloatamt" id ="framework_condition.ratefloatamt"  class="mini-textbox" value="${empty requestScope['framework_condition.ratefloatamt'] ? 0 : requestScope['framework_condition.ratefloatamt'] }"  vtype="rate"></td>
			              <td class="td-content-title">年租息率：</td>
			              <td class="td-content"><input name="framework_condition.yearrate" id ="framework_condition.yearrate"  class="mini-textbox" value="${empty requestScope['framework_condition.yearrate'] ? 0 : requestScope['framework_condition.yearrate'] }" ><font class="required-tag">%</font></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">付款日：</td>
			              <td class="td-content"><input name="framework_condition.leaseamtdate" required id ="framework_condition.leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.leaseamtdate']}"><font class="required-tag">*</font></td>
			              <td class="td-content-title" >起租日：</td>   
			              <td class="td-content"><input name="framework_condition.startdate" required id ="framework_condition.startdate" class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.startdate']}" ><font class="required-tag">*</font></td>
			              <td></td>
			              <td></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息生效节点：</td>
			              <td class="td-content">
			              	  <input id="framework_condition.adjuststyle" name="framework_condition.adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['framework_condition.adjuststyle'] ? 'next_list' : requestScope['framework_condition.adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.adjuststyle'] ? '次期' : requestScope['rawValue_framework_condition.adjuststyle'] }"
							  />	
							  <input id="rawValue_framework_condition.adjuststyle" name="rawValue_framework_condition.adjuststyle" value="${requestScope['rawValue_framework_condition.adjuststyle']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">罚息利率：</td>
			              <td class="td-content"><input name="framework_condition.penarate" id ="framework_condition.penarate"  class="mini-textbox"  value="${empty requestScope['framework_condition.penarate'] ?  5 : requestScope['framework_condition.penarate'] }"><font class="required-tag">%%</font></td>
			              <td class="td-content-title">免罚息天数：</td>
			              <td class="td-content"><input name="framework_condition.freedefainterday"  id ="framework_condition.freedefainterday"  class="mini-textbox"  value="${empty requestScope['framework_condition.freedefainterday'] ? requestScope['proj_info.industrytype'] eq 'cust_source.intenal' || empty requestScope['proj_info.industrytype'] ? 0 : 7  : requestScope['framework_condition.freedefainterday'] }"></td>
			          </tr>
				</table>
				<div title="分段测算" id="div_special_regular_new" class="mini-panel" style="width: 100%;height: 100%;" showCollapseButton="true">
					<jsp:include page="special_regular.jsp" ></jsp:include>
				</div> 
				 <div title="租金计划" name="fund_rent_plan_new" iconCls="icon-cut" >
					<jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
				 </div> 
		</div>
		<div title="费用表" iconCls="icon-cut">
			<table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">咨询服务费比例：</td>
			              <td class="td-content"><input name="framework_condition.handlingchargemoneyratio" id ="framework_condition.handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoneyratio'] ? 0 : requestScope['framework_condition.handlingchargemoneyratio'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">咨询服务费：</td>
			              <td class="td-content"><input name="framework_condition.handlingchargemoney" vtype="double"  label="咨询服务费" id ="framework_condition.handlingchargemoney"  class="mini-textbox" value="${empty requestScope['framework_condition.handlingchargemoney'] ? 0 : requestScope['framework_condition.handlingchargemoney'] }" readonly></td>
			          </tr>    
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">手续费比例：</td>
			              <td class="td-content"><input name="framework_condition.managementmoneyratio" id ="framework_condition.managementmoneyratio"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoneyratio'] ? 0 : requestScope['framework_condition.managementmoneyratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">手续费：</td>
			              <td class="td-content"><input name="framework_condition.managementmoney" vtype="double" label="手续费"   id ="framework_condition.managementmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.managementmoney'] ? 0 : requestScope['framework_condition.managementmoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">资产管理费比例：</td>
			              <td class="td-content"><input name="framework_condition.assetsratio" id ="framework_condition.assetsratio"  class="mini-textbox" value="${empty requestScope['framework_condition.assetsratio'] ? 0 : requestScope['framework_condition.assetsratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">资产管理费：</td>
			              <td class="td-content"><input name="framework_condition.assetsmoney" vtype="double" label="资产管理费"   id ="framework_condition.assetsmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.assetsmoney'] ? 0 : requestScope['framework_condition.assetsmoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">财务顾问费比例：</td>
			              <td class="td-content"><input name="framework_condition.adviserratio" id ="framework_condition.adviserratio"  class="mini-textbox" value="${empty requestScope['framework_condition.adviserratio'] ? 0 : requestScope['framework_condition.adviserratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">财务顾问费：</td>
			              <td class="td-content"><input name="framework_condition.advisermoney" vtype="double" label="财务顾问费"   id ="framework_condition.advisermoney"  class="mini-textbox" value="${empty requestScope['framework_condition.advisermoney'] ? 0 : requestScope['framework_condition.advisermoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">风险金比例：</td>
			              <td class="td-content"><input name="framework_condition.cautiondeductionratio" id ="framework_condition.cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionratio'] ? 0 : requestScope['framework_condition.cautiondeductionratio'] }"  ><font class="required-tag">%</font></td>
			             <td class="td-content-title">风险金：</td>   
			              <td class="td-content"><input name="framework_condition.cautionmoney" vtype="double" label="风险金" id ="framework_condition.cautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.cautionmoney'] ? 0 : requestScope['framework_condition.cautionmoney'] }" readonly></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">风险金抵扣金额：</td> 
			              <td class="td-content"><input name="framework_condition.cautiondeductionmoney" label="风险金抵扣金额" vtype="double"  id ="framework_condition.cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['framework_condition.cautiondeductionmoney'] ? 0 : requestScope['framework_condition.cautiondeductionmoney'] }" readonly></td>
			          	  <td class="td-content-title">风险金退还金额：</td>
			              <td class="td-content"><input name="framework_condition.cautionmoneyremain" label="风险金退还金额" vtype="double"  id ="framework_condition.cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['framework_condition.cautionmoneyremain'] ? 0 : requestScope['framework_condition.cautionmoneyremain'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">租前息金额：</td>
	              		  <td class="td-content"><input name="framework_condition.beforeinterest" label="租前息" vtype="double"  id ="framework_condition.beforeinterest"  class="mini-textbox" value="${empty requestScope['framework_condition.beforeinterest'] ? 0 : requestScope['framework_condition.beforeinterest'] }" ></td>
			              <td class="td-content-title">留购价款：</td>   
			              <td class="td-content"><input name="framework_condition.nominalprice" label="留购价款" vtype="double"  id ="framework_condition.nominalprice"  class="mini-textbox" value="${empty requestScope['framework_condition.nominalprice'] ? 100 : requestScope['framework_condition.nominalprice'] }" ></td>
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
								   	   value="${empty requestScope['framework_condition.insuremoneytype'] ? 'insure_type1' : requestScope['framework_condition.insuremoneytype'] }" 
								   	   text="${empty requestScope['rawValue_framework_condition.insuremoneytype'] ? '本司付款' : requestScope['rawValue_framework_condition.insuremoneytype'] }"
									   
							  />
							  <input id="rawValue_framework_condition.insuremoneytype" name="rawValue_framework_condition.insuremoneytype" value="${requestScope['rawValue_framework_condition.insuremoneytype']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">保险费：</td>
			              <td class="td-content"><input name="framework_condition.insuremoney" label="保险费"  vtype="double"  id ="framework_condition.insuremoney"  readonly class="mini-textbox" value="${empty requestScope['framework_condition.insuremoney'] ? 0 : requestScope['framework_condition.insuremoney'] }" readonly></td>
			            
			          </tr>
			          <tr class="tr-project-info tr-even">
			            <td class="td-content-title"> 保险费(承租人)：</td>
			              <td class="td-content">
			              	<input name="framework_condition.insurancelessee_show" vtype="double"  id ="framework_condition.insurancelessee_show"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessee'] ? 0 : requestScope['framework_condition.insurancelessee'] }" >
			              	<input name="framework_condition.insurancelessee" type="hidden"  id ="framework_condition.insurancelessee"  value="${empty requestScope['framework_condition.insurancelessee'] ? 0 : requestScope['framework_condition.insurancelessee'] }" >
			              </td>
			              <td class="td-content-title"> 保险费(我司)：</td>
			              <td class="td-content">
			              	<input name="framework_condition.insurancelessor_show" vtype="double"  id ="framework_condition.insurancelessor_show"  class="mini-textbox" value="${empty requestScope['framework_condition.insurancelessor'] ? 0 : requestScope['framework_condition.insurancelessor'] }" >
			              	<input name="framework_condition.insurancelessor" type="hidden"  id ="framework_condition.insurancelessor"  value="${empty requestScope['framework_condition.insurancelessor'] ? 0 : requestScope['framework_condition.insurancelessor'] }" >
			              </td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			          	<td class="td-content-title">其他收入：</td>
			              <td class="td-content"><input name="framework_condition.otherincome" label="其他收入" vtype="double"  id ="framework_condition.otherincome"  class="mini-textbox" value="${empty requestScope['framework_condition.otherincome'] ? 0 : requestScope['framework_condition.otherincome'] }" ></td>
			              <td class="td-content-title">其他支出：</td>
			              <td class="td-content"><input name="framework_condition.otherexpenditure" label="其他支出"  vtype="double"  id ="framework_condition.otherexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition.otherexpenditure'] ? 0 : requestScope['framework_condition.otherexpenditure'] }" ></td>
			          </tr>
				</table>
				<div title="资金收付计划" name="fund_fund_charge_new" iconCls="icon-cut">
					<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
				</div> 
		</div>
		<div title="现金流表" iconCls="icon-cut">
			 <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title" >内部收益率：</td>  
			              <td class="td-content">
			              		<input name="framework_condition.irrshow" id ="framework_condition.irrshow" readonly class="mini-textbox" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }"><font class="required-tag">%</font>
			              		<input name="framework_condition.irr" id ="framework_condition.irr" type="hidden" value="${empty requestScope['framework_condition.irr'] ? 0 : requestScope['framework_condition.irr'] }">
			              </td>
						  <td class="td-content-title">项目粗利：</td>
			              <td class="td-content"><input name="framework_condition.grossprofit" vtype="double"  id ="framework_condition.grossprofit" readonly class="mini-textbox" value="${empty requestScope['framework_condition.grossprofit'] ? 0 : requestScope['framework_condition.grossprofit'] }"></td>
			          </tr>
				</table>
				 <div title="现金流" name="fund_cash_flow_new" iconCls="icon-cut">
					<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
				 </div>
			<%--隐藏域 --%>
			<table style="display: none;"  cellspacing="0" cellpadding="0">
			<input name="framework_condition.yearrate_helper" id ="framework_condition.yearrate_helper"  class="mini-textbox" value="${empty requestScope['framework_condition.yearrate_helper'] ? 0 : requestScope['framework_condition.yearrate_helper'] }" style="display:none">
			 <td class="td-content-title">租赁期限(月)：</td>
             <td class="td-content"><input name="framework_condition.leaseterm" id ="framework_condition.leaseterm"  class="mini-textbox" readonly value="${empty requestScope['framework_condition.leaseterm'] ? 0 : requestScope['framework_condition.leaseterm'] }"></td>
			<td class="td-content-title">厂商返利：</td><td class="td-content"><input name="framework_condition.returnamt" id ="framework_condition.returnamt"  class="mini-textbox" value="${empty requestScope['framework_condition.returnamt'] ? 0 : requestScope['framework_condition.returnamt'] }"></td>
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
					   	   text="${requestScope['rawvalue_framework_condition.evencorpusratetype']}"
				  />	
				  <input id="rawValue_framework_condition.evencorpusratetype" name="rawValue_framework_condition.evencorpusratetype" value="${requestScope['rawValue_framework_condition.evencorpusratetype']}" class="mini-textbox" style="display:none"/>
        	   </td>
			  <tr class="tr-project-info tr-even">
	          	  <td class="td-content-title">厂商保证金：</td>   
	              <td class="td-content"><input name="framework_condition.faccautionmoney" vtype="double" label="厂商保证金" id ="framework_condition.faccautionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoney'] ? 0 : requestScope['framework_condition.faccautionmoney'] }"></td>
	              <td class="td-content-title">厂商保证金比例：</td>
	              <td class="td-content"><input name="framework_condition.faccautiondeductionratio" id ="framework_condition.faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['framework_condition.faccautiondeductionratio'] ? 0 : requestScope['framework_condition.faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
	              <td class="td-content-title">厂商保证金抵扣：</td>   
	              <td class="td-content"><input name="framework_condition.faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="framework_condition.faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautiondeductionmoney'] ? 0 : requestScope['framework_condition.faccautiondeductionmoney'] }" ></td>
	              <td class="td-content-title">厂商保证金退还：</td>   
	              <td class="td-content"><input name="framework_condition.faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="framework_condition.faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['framework_condition.faccautionmoneyremain'] ? 0 : requestScope['framework_condition.faccautionmoneyremain'] }" ></td>
	          </tr>
			 <td class="td-content-title">末期租金支付日：</td>
              <td class="td-content"><input name="framework_condition.lastplandate" id ="framework_condition.lastplandate"   class="mini-datepicker" allowInput="false" value="${requestScope['framework_condition.lastplandate']}" ></td>
			  <td class="td-content-title">宽限期：</td>
              <td class="td-content"><input name="framework_condition.grace" id ="framework_condition.grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['framework_condition.grace'] ? 0 : requestScope['framework_condition.grace'] }"   ></td>
			  <td class="td-content-title">租金支付类型：</td>
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
	              <td class="td-content-title">还租次数：</td>  
	              <td class="td-content"><input name="framework_condition.incomenumber" required id ="framework_condition.incomenumber" vtype="int" class="mini-textbox"  value="${empty requestScope['framework_condition.incomenumber'] ? 0 : requestScope['framework_condition.incomenumber'] }"   ><font class="required-tag">*</font></td>
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
				  />
				  <input id="rawValue_framework_condition.periodtype" name="rawValue_framework_condition.periodtype" value="${requestScope['rawValue_framework_condition.periodtype']}" class="mini-textbox" style="display:none"/>
			     </td>
				 <td class="td-content-title">其他融资费：</td>  
		         <td class="td-content"><input name="framework_condition.otherleasemoney" vtype="double"  id ="framework_condition.otherleasemoney" class="mini-textbox" value="${empty requestScope['framework_condition.otherleasemoney'] ? 0 : requestScope['framework_condition.otherleasemoney'] }" ></td>
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
				   /><font class="required-tag">*</font>
				   <input id="rawValue_framework_condition.rentorrate" name="rawValue_framework_condition.rentorrate" value="${requestScope['rawValue_framework_condition.rentorrate']}" class="mini-textbox" style="display:none"/>
			  	</td>
			 <td class="td-content-title">期初付款总计：</td><td class="td-content"><input name="framework_condition.firstpaymenttotal" vtype="double"  id ="framework_condition.firstpaymenttotal" class="mini-textbox" value="${empty requestScope['framework_condition.firstpaymenttotal'] ? 0 : requestScope['framework_condition.firstpaymenttotal'] }" readonly></td>
			  <td class="td-content-title" id="framework_condition.itemTd"><span id="framework_condition.testrent">测算租金:</span><span id="framework_condition.testrate">年租息率：</span></td>
	          <td class="td-content"><input name="framework_condition.rentorratevalue" id ="framework_condition.rentorratevalue"  vtype="double"   class="mini-textbox" value="${empty requestScope['framework_condition.rentorratevalue'] ? 0 : requestScope['framework_condition.rentorratevalue'] }"  ></td>
			   <td class="td-content-title">结束日期：</td><td class="td-content"><input name="framework_condition.enddate" id ="framework_condition.enddate" readonly class="mini-textbox" value="${empty requestScope['framework_condition.enddate'] ? 0 : requestScope['framework_condition.enddate']}"></td>
			  <td class="td-content-title">第一期租金支付日：</td>
	          <td class="td-content"><input name="framework_condition.firstplandate" required id ="framework_condition.firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.firstplandate']}" ></td>
	          <td class="td-content-title">第二期租金支付日：</td>
	          <td class="td-content"><input name="framework_condition.secondplandate" id ="framework_condition.secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['framework_condition.secondplandate']}" ></td>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">待投保年费率：</td>
			              <td class="td-content"><input name="framework_condition.insurerate" id ="framework_condition.insurerate" vtype="rate" label="待投保年费率"  class="mini-textbox" value="${empty requestScope['framework_condition.insurerate'] ? 0 : requestScope['framework_condition.insurerate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保收入：</td>   
			              <td class="td-content"><input name="framework_condition.insureincome" vtype="double" label="投保收入" id ="framework_condition.insureincome"  class="mini-textbox"  value="${empty requestScope['framework_condition.insureincome'] ? 0 : requestScope['framework_condition.insureincome'] }" readonly></td>
			              <td class="td-content-title">投保成本年费率：</td> 
			              <td class="td-content"><input name="framework_condition.insurecostrate" label="投保成本年费率" vtype="rate"  id ="framework_condition.insurecostrate"  class="mini-textbox" value="${empty requestScope['framework_condition.insurecostrate'] ? 0 : requestScope['framework_condition.insurecostrate'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保支出：</td>
			              <td class="td-content"><input name="framework_condition.insureexpenditure" label="投保支出" vtype="double"  id ="framework_condition.insureexpenditure"  class="mini-textbox" value="${empty requestScope['framework_condition.insureexpenditure'] ? 0 : requestScope['framework_condition.insureexpenditure'] }" readonly></td>
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
	</div>
</div>
	