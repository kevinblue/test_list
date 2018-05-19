<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="businessconditionForm" >
	<div class="mini-tabs" activeIndex="0"   style="width: 100%" id="calTabDetailsBase" >
		<div title="租金计划表" iconCls="icon-cut">
		 <table class="fillTable" cellspacing="0" cellpadding="0" id="rent_cal_info" >
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
								 -->
								<input name="json_fund_rent_plan_str" 	 		id="id_json_fund_rent_plan_str" 		type="hidden" value='${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}'/><%-- 租金计划 --%>
								<input name="json_fund_cash_flow_str" 			id="id_json_fund_cash_flow_str" 		type="hidden" value='${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str }'/><%-- 现金流 --%>
								<input name="json_fund_fund_charge_str" 		id="id_json_fund_fund_charge_str" 		type="hidden" value='${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'/><%-- 资金首付计划 --%>
			             	   <input name="json_special_regular_str" 		    id="id_json_special_regular_str"  	    type="hidden" value='${empty json_special_regular_str ? "[]" : json_special_regular_str }'/><!-- 特殊规则列表 -->
			             <input name="equipamt" id ="equipamt" required vtype="double" label ="设备款" vtype="double"  onvaluechanged="checkequipment"  class="mini-textbox"   value="${empty requestScope['equipamt'] ? 0 : requestScope['equipamt'] }"><font class="required-tag">*</font></td>
			              <td class="td-content-title">首付比例：</td>
			              <td class="td-content"><input name="firstpaymentratio" id ="firstpaymentratio"  class="mini-textbox" value="${empty requestScope['firstpaymentratio'] ? 0 : requestScope['firstpaymentratio'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">首付款：</td>
			              <td class="td-content"><input name="firstpayment" label ="首付款" vtype="double"  id ="firstpayment"  class="mini-textbox"  value="${empty requestScope['firstpayment'] ? 0 : requestScope['firstpayment'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">融资额：</td>
			              <td class="td-content"><input name="cleanleasemoney" readonly  vtype="double"   id ="cleanleasemoney"  class="mini-textbox" value="${empty requestScope['cleanleasemoney'] ? 0 : requestScope['cleanleasemoney'] }"></td>
			              <td class="td-content-title">风险敞口：</td>
			              <td class="td-content"><input name="cleancreditmoney" vtype="double"  id ="cleancreditmoney"  class="mini-textbox" value="${empty requestScope['cleancreditmoney'] ? 0 : requestScope['cleancreditmoney'] }" readonly ></td>
			              <td class="td-content-title">净授信比例：</td>
			              <td class="td-content"><input name="cleancreditratio" id ="cleancreditratio"  class="mini-textbox" value="${empty requestScope['cleancreditratio'] ? 0 : requestScope['cleancreditratio'] }" readonly><font class="required-tag">%</font></td>
			             
			          </tr>
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
							   	   text="${empty requestScope['rawValue_settlemethod'] ? '分段测算' : requestScope['rawValue_settlemethod'] }"
							   /><font class="required-tag">*</font>
							   <input id="rawValue_settlemethod" name="rawValue_settlemethod" value="${requestScope['rawValue_settlemethod']}" class="mini-textbox" style="display:none"/>
			              </td>
			               <td class="td-content-title">期末余值：</td>
			              <td class="td-content"><input name="equipendvalue" id ="equipendvalue"  vtype="double"  class="mini-textbox"  value="${empty requestScope['equipendvalue'] ? 0 : requestScope['equipendvalue'] }" ></td>
						   <td class="td-content-title">利率计算方式：</td>
			              <td class="td-content">
				              <input id="ratefloattype" required name="ratefloattype" class="mini-combobox" textField="name" 
					                  	   valueField="value"  
										   dataField="datas"
										   allowInput="false" 
										   data-options="{_params:{pid:'rate_float_type'}}" 
										   onbeforeshowpopup="onbeforeshowpopup"
										   value="${empty requestScope['ratefloattype'] ? 'proportion' : requestScope['ratefloattype'] }"  
									   	   text="${empty requestScope['rawValue_ratefloattype'] ? '按央行浮动比率' : requestScope['rawValue_ratefloattype'] }"
							  /><font class="required-tag">*</font>
							  <input id="rawValue_ratefloattype" name="rawValue_ratefloattype" value="${requestScope['rawValue_ratefloattype']}" class="mini-textbox" style="display:none"/>
						   </td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			          	  <td class="td-content-title">基准利率：</td>
			              <td class="td-content"><input name="baserate" vtype="double" id ="baserate"   class="mini-textbox" value="${empty requestScope['baserate'] ? 0 : requestScope['baserate'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">利率调整值：</td>
			              <td class="td-content"><input name="ratefloatamt" id ="ratefloatamt"  class="mini-textbox" value="${empty requestScope['ratefloatamt'] ? 0 : requestScope['ratefloatamt'] }"  vtype="rate"></td>
			              <td class="td-content-title">年租息率：</td>
			              <td class="td-content"><input name="yearrate" id ="yearrate"  class="mini-textbox" value="${empty requestScope['yearrate'] ? 0 : requestScope['yearrate'] }" ><font class="required-tag">%</font></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title">付款日：</td>
			              <td class="td-content"><input name="leaseamtdate" required id ="leaseamtdate"   class="mini-datepicker" allowInput="false" value="${requestScope['leaseamtdate']}"><font class="required-tag">*</font></td>
			              <td class="td-content-title" >起租日：</td>   
			              <td class="td-content"><input name="startdate" required id ="startdate" class="mini-datepicker" allowInput="false" value="${requestScope['startdate']}" ><font class="required-tag">*</font></td>
			              <td></td>
			              <td></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title" >调息生效节点：</td>
			              <td class="td-content">
			              	  <input id="adjuststyle" name="adjuststyle" class="mini-combobox" textField="name" 
				                  	   valueField="value"  
									   dataField="datas"  
									   allowInput="false" 
									   data-options="{_params:{pid:'adjust_style'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   onvaluechanged="comboboxChanged"
									   showNullItem="true" 
								   	   nullItemText=""
								   	   value="${empty requestScope['adjuststyle'] ? 'next_list' : requestScope['adjuststyle'] }" 
								   	   text="${empty requestScope['rawValue_adjuststyle'] ? '次期' : requestScope['rawValue_adjuststyle'] }"
							  />	
							  <input id="rawValue_adjuststyle" name="rawValue_adjuststyle" value="${requestScope['rawValue_adjuststyle']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">罚息利率：</td>
			              <td class="td-content"><input name="penarate" id ="penarate"  class="mini-textbox"  value="${empty requestScope['penarate'] ?  5 : requestScope['penarate'] }"><font class="required-tag">%%</font></td>
			              <td class="td-content-title">免罚息天数：</td>
			              <td class="td-content"><input name="freedefainterday"  id ="freedefainterday"  class="mini-textbox"  value="${empty requestScope['freedefainterday'] ? requestScope['proj_info.industrytype'] eq 'cust_source.intenal' || empty requestScope['proj_info.industrytype'] ? 0 : 7  : requestScope['freedefainterday'] }"></td>
			          </tr>
				</table>
				<div title="分段测算" id="div_special_regular" class="mini-panel" style="width: 100%;height: 100%;" showCollapseButton="true">
					<jsp:include page="special_regular.jsp" ></jsp:include>
				</div> 
				 <div title="租金计划" name="fund_rent_plan" iconCls="icon-cut" >
					<jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
				 </div> 
		</div>
		<div title="费用表" iconCls="icon-cut" >
			<table class="fillTable" cellspacing="0" cellpadding="0"  id="fund_table">
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">咨询服务费比例：</td>
			              <td class="td-content"><input name="handlingchargemoneyratio" id ="handlingchargemoneyratio"  class="mini-textbox" value="${empty requestScope['handlingchargemoneyratio'] ? 0 : requestScope['handlingchargemoneyratio'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">咨询服务费：</td>
			              <td class="td-content"><input name="handlingchargemoney" vtype="double"  label="咨询服务费" id ="handlingchargemoney"  class="mini-textbox" value="${empty requestScope['handlingchargemoney'] ? 0 : requestScope['handlingchargemoney'] }" readonly></td>
			          </tr>    
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">手续费比例：</td>
			              <td class="td-content"><input name="managementmoneyratio" id ="managementmoneyratio"  class="mini-textbox" value="${empty requestScope['managementmoneyratio'] ? 0 : requestScope['managementmoneyratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">手续费：</td>
			              <td class="td-content"><input name="managementmoney" vtype="double" label="手续费"   id ="managementmoney"  class="mini-textbox" value="${empty requestScope['managementmoney'] ? 0 : requestScope['managementmoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">资产管理费比例：</td>
			              <td class="td-content"><input name="assetsratio" id ="assetsratio"  class="mini-textbox" value="${empty requestScope['assetsratio'] ? 0 : requestScope['assetsratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">资产管理费：</td>
			              <td class="td-content"><input name="assetsmoney" vtype="double" label="资产管理费"   id ="assetsmoney"  class="mini-textbox" value="${empty requestScope['assetsmoney'] ? 0 : requestScope['assetsmoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">财务顾问费比例：</td>
			              <td class="td-content"><input name="adviserratio" id ="adviserratio"  class="mini-textbox" value="${empty requestScope['adviserratio'] ? 0 : requestScope['adviserratio'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">财务顾问费：</td>
			              <td class="td-content"><input name="advisermoney" vtype="double" label="财务顾问费"   id ="advisermoney"  class="mini-textbox" value="${empty requestScope['advisermoney'] ? 0 : requestScope['advisermoney'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">风险金比例：</td>
			              <td class="td-content"><input name="cautiondeductionratio" id ="cautiondeductionratio"  class="mini-textbox" value="${empty requestScope['cautiondeductionratio'] ? 0 : requestScope['cautiondeductionratio'] }"  ><font class="required-tag">%</font></td>
			             <td class="td-content-title">风险金：</td>   
			              <td class="td-content"><input name="cautionmoney" vtype="double" label="风险金" id ="cautionmoney"  class="mini-textbox"  value="${empty requestScope['cautionmoney'] ? 0 : requestScope['cautionmoney'] }" readonly></td>
			          </tr>
			           <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">风险金抵扣金额：</td> 
			              <td class="td-content"><input name="cautiondeductionmoney" label="风险金抵扣金额" vtype="double"  id ="cautiondeductionmoney"  class="mini-textbox" value="${empty requestScope['cautiondeductionmoney'] ? 0 : requestScope['cautiondeductionmoney'] }" readonly></td>
			          	  <td class="td-content-title">风险金退还金额：</td>
			              <td class="td-content"><input name="cautionmoneyremain" label="风险金退还金额" vtype="double"  id ="cautionmoneyremain"  class="mini-textbox" value="${empty requestScope['cautionmoneyremain'] ? 0 : requestScope['cautionmoneyremain'] }" readonly></td>
			          </tr>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">租前息金额：</td>
	              		  <td class="td-content"><input name="beforeinterest" label="租前息" vtype="double"  id ="beforeinterest"  class="mini-textbox" value="${empty requestScope['beforeinterest'] ? 0 : requestScope['beforeinterest'] }" ></td>
			              <td class="td-content-title">留购价款：</td>   
			              <td class="td-content"><input name="nominalprice" label="留购价款" vtype="double"  id ="nominalprice"  class="mini-textbox" value="${empty requestScope['nominalprice'] ? 100 : requestScope['nominalprice'] }" ></td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			              <td class="td-content-title">保险计算方式：</td>
			              <td class="td-content">
			              	  <input id="insuremoneytype" name="insuremoneytype" class="mini-combobox" textField="name" 
				                  	   valueField="value"     
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'insure_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
								   	   value="${empty requestScope['insuremoneytype'] ? 'insure_type1' : requestScope['insuremoneytype'] }" 
								   	   text="${empty requestScope['rawValue_insuremoneytype'] ? '本司付款' : requestScope['rawValue_insuremoneytype'] }"
									   
							  />
							  <input id="rawValue_insuremoneytype" name="rawValue_insuremoneytype" value="${requestScope['rawValue_insuremoneytype']}" class="mini-textbox" style="display:none"/>
			              </td>
			              <td class="td-content-title">保险费：</td>
			              <td class="td-content"><input name="insuremoney" label="保险费"  vtype="double"  id ="insuremoney"  readonly class="mini-textbox" value="${empty requestScope['insuremoney'] ? 0 : requestScope['insuremoney'] }" readonly></td>
			            
			          </tr>
			          <tr class="tr-project-info tr-even">
			            <td class="td-content-title"> 保险费(承租人)：</td>
			              <td class="td-content">
			              	<input name="insurancelessee_show" vtype="double"  id ="insurancelessee_show"  class="mini-textbox" value="${empty requestScope['insurancelessee'] ? 0 : requestScope['insurancelessee'] }" >
			              	<input name="insurancelessee" type="hidden"  id ="insurancelessee"  value="${empty requestScope['insurancelessee'] ? 0 : requestScope['insurancelessee'] }" >
			              </td>
			              <td class="td-content-title"> 保险费(我司)：</td>
			              <td class="td-content">
			              	<input name="insurancelessor_show" vtype="double"  id ="insurancelessor_show"  class="mini-textbox" value="${empty requestScope['insurancelessor'] ? 0 : requestScope['insurancelessor'] }" >
			              	<input name="insurancelessor" type="hidden"  id ="insurancelessor"  value="${empty requestScope['insurancelessor'] ? 0 : requestScope['insurancelessor'] }" >
			              </td>
			          </tr>
			          <tr class="tr-project-info tr-odd">
			          	<td class="td-content-title">其他收入：</td>
			              <td class="td-content"><input name="otherincome" label="其他收入" vtype="double"  id ="otherincome"  class="mini-textbox" value="${empty requestScope['otherincome'] ? 0 : requestScope['otherincome'] }" ></td>
			              <td class="td-content-title">其他支出：</td>
			              <td class="td-content"><input name="otherexpenditure" label="其他支出"  vtype="double"  id ="otherexpenditure"  class="mini-textbox" value="${empty requestScope['otherexpenditure'] ? 0 : requestScope['otherexpenditure'] }" ></td>
			          </tr>
				</table>
				<div title="资金收付计划" name="fund_fund_charge" iconCls="icon-cut">
					<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
				</div> 
		</div>
		<div title="现金流表" iconCls="icon-cut">
			 <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
			          <tr class="tr-project-info tr-even">
			          	  <td class="td-content-title" >内部收益率：</td>  
			              <td class="td-content">
			              		<input name="irrshow" id ="irrshow" readonly class="mini-textbox" value="${empty requestScope['irr'] ? 0 : requestScope['irr'] }"><font class="required-tag">%</font>
			              		<input name="irr" id ="irr" type="hidden" value="${empty requestScope['irr'] ? 0 : requestScope['irr'] }">
			              </td>
						  <td class="td-content-title">项目粗利：</td>
			              <td class="td-content"><input name="grossprofit" vtype="double"  id ="grossprofit" readonly class="mini-textbox" value="${empty requestScope['grossprofit'] ? 0 : requestScope['grossprofit'] }"></td>
			          </tr>
				</table>
				 <div title="现金流" name="fund_cash_flow" iconCls="icon-cut">
					<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
				 </div>
			<%--隐藏域 --%>
			<table style="display: none;"  cellspacing="0" cellpadding="0">
			<input name="yearrate_helper" id ="yearrate_helper"  class="mini-textbox" value="${empty requestScope['yearrate_helper'] ? 0 : requestScope['yearrate_helper'] }" style="display: none;">
			 <td class="td-content-title">租赁期限(月)：</td>
             <td class="td-content"><input name="leaseterm" id ="leaseterm"  class="mini-textbox" readonly value="${empty requestScope['leaseterm'] ? 0 : requestScope['leaseterm'] }"></td>
			<td class="td-content-title">厂商返利：</td><td class="td-content"><input name="returnamt" id ="returnamt"  class="mini-textbox" value="${empty requestScope['returnamt'] ? 0 : requestScope['returnamt'] }"></td>
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
			  <tr class="tr-project-info tr-even">
	          	  <td class="td-content-title">厂商保证金：</td>   
	              <td class="td-content"><input name="faccautionmoney" vtype="double" label="厂商保证金" id ="faccautionmoney"  class="mini-textbox"  value="${empty requestScope['faccautionmoney'] ? 0 : requestScope['faccautionmoney'] }"></td>
	              <td class="td-content-title">厂商保证金比例：</td>
	              <td class="td-content"><input name="faccautiondeductionratio" id ="faccautiondeductionratio"  class="mini-textbox" value="${empty requestScope['faccautiondeductionratio'] ? 0 : requestScope['faccautiondeductionratio'] }"  readonly><font class="required-tag">%</font></td>
	              <td class="td-content-title">厂商保证金抵扣：</td>   
	              <td class="td-content"><input name="faccautiondeductionmoney" vtype="double" label="厂商保证金抵扣"  id ="faccautiondeductionmoney"  class="mini-textbox"  value="${empty requestScope['faccautiondeductionmoney'] ? 0 : requestScope['faccautiondeductionmoney'] }" ></td>
	              <td class="td-content-title">厂商保证金退还：</td>   
	              <td class="td-content"><input name="faccautionmoneyremain" vtype="double" readonly label="厂商保证金退还"  id ="faccautionmoneyremain"  class="mini-textbox"  value="${empty requestScope['faccautionmoneyremain'] ? 0 : requestScope['faccautionmoneyremain'] }" ></td>
	          </tr>
			 <td class="td-content-title">末期租金支付日：</td>
              <td class="td-content"><input name="lastplandate" id ="lastplandate"   class="mini-datepicker" allowInput="false" value="${requestScope['lastplandate']}" ></td>
			  <td class="td-content-title">宽限期：</td>
              <td class="td-content"><input name="grace" id ="grace" vtype="int"  class="mini-textbox"   value="${empty requestScope['grace'] ? 0 : requestScope['grace'] }"   ></td>
			  <td class="td-content-title">租金支付类型：</td>
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
	              <td class="td-content-title">还租次数：</td>  
	              <td class="td-content"><input name="incomenumber" required id ="incomenumber" vtype="int" class="mini-textbox"  value="${empty requestScope['incomenumber'] ? 0 : requestScope['incomenumber'] }"   ><font class="required-tag">*</font></td>
				 <td class="td-content-title">期初/期末：</td>
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
				 <td class="td-content-title">其他融资费：</td>  
		         <td class="td-content"><input name="otherleasemoney" vtype="double"  id ="otherleasemoney" class="mini-textbox" value="${empty requestScope['otherleasemoney'] ? 0 : requestScope['otherleasemoney'] }" ></td>
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
				   /><font class="required-tag">*</font>
				   <input id="rawValue_rentorrate" name="rawValue_rentorrate" value="${requestScope['rawValue_rentorrate']}" class="mini-textbox" style="display:none"/>
			  	</td>
			 <td class="td-content-title">期初付款总计：</td><td class="td-content"><input name="firstpaymenttotal" vtype="double"  id ="firstpaymenttotal" class="mini-textbox" value="${empty requestScope['firstpaymenttotal'] ? 0 : requestScope['firstpaymenttotal'] }" readonly></td>
			  <td class="td-content-title" id="itemTd"><span id="testrent">测算租金:</span><span id="testrate">年租息率：</span></td>
	          <td class="td-content"><input name="rentorratevalue" id ="rentorratevalue"  vtype="double"   class="mini-textbox" value="${empty requestScope['rentorratevalue'] ? 0 : requestScope['rentorratevalue'] }"  ></td>
			   <td class="td-content-title">结束日期：</td><td class="td-content"><input name="enddate" id ="enddate" readonly class="mini-textbox" value="${empty requestScope['enddate'] ? 0 : requestScope['enddate']}"></td>
			  <td class="td-content-title">第一期租金支付日：</td>
	          <td class="td-content"><input name="firstplandate" required id ="firstplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['firstplandate']}" ></td>
	          <td class="td-content-title">第二期租金支付日：</td>
	          <td class="td-content"><input name="secondplandate" id ="secondplandate"   class="mini-datepicker"  allowInput="false" value="${requestScope['secondplandate']}" ></td>
			          <tr class="tr-project-info tr-even">
			              <td class="td-content-title">待投保年费率：</td>
			              <td class="td-content"><input name="insurerate" id ="insurerate" vtype="rate" label="待投保年费率"  class="mini-textbox" value="${empty requestScope['insurerate'] ? 0 : requestScope['insurerate'] }"  ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保收入：</td>   
			              <td class="td-content"><input name="insureincome" vtype="double" label="投保收入" id ="insureincome"  class="mini-textbox"  value="${empty requestScope['insureincome'] ? 0 : requestScope['insureincome'] }" readonly></td>
			              <td class="td-content-title">投保成本年费率：</td> 
			              <td class="td-content"><input name="insurecostrate" label="投保成本年费率" vtype="rate"  id ="insurecostrate"  class="mini-textbox" value="${empty requestScope['insurecostrate'] ? 0 : requestScope['insurecostrate'] }" ><font class="required-tag">%</font></td>
			              <td class="td-content-title">投保支出：</td>
			              <td class="td-content"><input name="insureexpenditure" label="投保支出" vtype="double"  id ="insureexpenditure"  class="mini-textbox" value="${empty requestScope['insureexpenditure'] ? 0 : requestScope['insureexpenditure'] }" readonly></td>
			          </tr>
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
			</table>
		</div>
	</div>
</div>
<script type="text/javascript"> 
   function  checkequipment(){		  
	   var equipamt=Number(mini.get('equipamt').getValue());
	   var pqs = mini.get('proj_equip');
	   if(pqs==null||pqs==""){
		   pqs = mini.get('contract_equip');
	   }
	   var pq=pqs.getData();
	   var sumnowtotal=0;
	   for(var i=0;i<pq.length;i++){
		  var nowtotal= (Number)(pq[i]["nowtotal"]);
		sumnowtotal=nowtotal+sumnowtotal;
		  
	   }
	  if(equipamt<sumnowtotal){
	
		  mini.alert("设备款应大于等于租赁物价格");
		  mini.get('equipamt').setValue("");
	  } 
	   
   }
 </script>
	