<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/minidict" prefix="mini"%>
<style>
	#businessconditionFormReadonly .td-content-title{
		width:160px;
		font-size:12px;
	}
	#businessconditionFormReadonly .td-content{
		width:160px;
	}
</style>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<div id="businessconditionFormReadonly">
	<div class="mini-panel" title="授信条件" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">设备款：</td>
				<td class="td-content">
					<!-- 下边的值是一些关建行信息值，包跨项目阶段的项目编号，合同阶段的合同编号，客户报价时客户编号  //实际授信月数(申请书需用到)//保证金抵扣期数-->
						<!-- 下边的值是一些关建行信息值，包跨项目阶段的项目编号，合同阶段的合同编号，客户报价时客户编号  //实际授信月数(申请书需用到)//保证金抵扣期数-->
						   <input name="id" 			id="conditionId"  		style="display:none;"	class="mini-textbox"  value="<mini:param  name="framework_condition.id"/>" />
						  <input name="custid" 		id="conditionCustId" 	style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.custid"/>" />
						  <input name="contractid" 	id="conditionContractId" 	style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.contractid"/>" />
						  <input name="projid" 		id="conditionProjId" 		style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.projid"/>" />
						  <input name="docid" 		id="conditionDocId" 		style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.docid"/>" /> 
						  <input name="creditmonths"id="creditmonths" 	        style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.creditmonths"/>" /> 
						  <input name="dcnum" 		id="dcnum" 	        		style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.dcnum" defaultValue="0"/>" />
						  <input name="custname" 	id="custname" 	        	style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.custname" defaultValue=""/>" />
						  <input name="process" 	id="reckonProcess" 			style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.process"/>" />
						  <input name="reckontype" 	id="reckonType"  			style="display:none;"	class="mini-textbox" value="<mini:param  name="framework_condition.reckontype" defaultValue="condition"/>" />

							<!-- 下边的是租金计划的值，就是测算结果 -->
							<!-- 
								json_fund_rent_plan_str 租金计划
								json_fund_cash_flow_str 现金流
								json_finance_rent_plan_str 会计租金计划
								json_finance_cash_flow_str 会计现金流
								json_fund_fund_charge_str 资金收付计划
								json_knowing_rent_plan_str 已知租金法完整json
							 -->
								<input name="json_fund_rent_plan_str" 	 		id="id_json_fund_rent_plan_str" 		style="display:none;"	class="mini-textarea" value='<mini:param  name="json_fund_rent_plan_str" defaultValue="[]"/>'/><%-- 租金计划 --%>
							<input name="json_fund_cash_flow_str" 			id="id_json_fund_cash_flow_str" 		style="display:none;"	class="mini-textarea" value='<mini:param  name="json_fund_cash_flow_str" defaultValue="[]"/>'/><%-- 现金流 --%>
							<input name="json_finance_rent_plan_str" 		id="id_json_finance_rent_plan_str" 		style="display:none;"	class="mini-textarea" value='<mini:param  name="json_finance_rent_plan_str" defaultValue="[]"/>'/><%-- 会计租金计划 --%>
							<input name="json_finance_cash_flow_str" 		id="id_json_finance_cash_flow_str" 		style="display:none;"	class="mini-textarea" value='<mini:param  name="json_finance_cash_flow_str" defaultValue="[]"/>'/><%-- 会计现金流 --%>
							<input name="json_fund_fund_charge_str" 		id="id_json_fund_fund_charge_str" 		style="display:none;"	class="mini-textarea" value='<mini:param  name="json_fund_fund_charge_str" defaultValue="[]"/>'/><%-- 资金首付计划 --%>
							<input name="json_knowing_rent_plan_str" 		id="id_json_knowing_rent_plan_str"  	style="display:none;"	class="mini-textarea" value='<mini:param  name="json_knowing_rent_plan_str" defaultValue="[]"/>'/><!-- 已知租金法的租金列表 -->
							<input name="json_knowing_corpus_plan_str" 		id="id_json_knowing_corpus_plan_str"  	type="hidden" value='<mini:param  name="json_knowing_corpus_plan_str" defaultValue="[]"/>'/><!-- 已知本金法的租金列表 -->	
		             	    <input name="equipamt" id ="equipamt" vtype="double" vtype="double"  class="mini-textbox"  onkeyup="changeequipamtvalue" value='<mini:param  name="equipamt" defaultValue="0"/>'></td>
				</td>
				<td class="td-content-title">首付款：</td>
				<td class="td-content"><input name="firstpayment"
					vtype="double" id="firstpayment" class="mini-textbox"
					value="<mini:param  name="firstpayment" defaultValue="0"/>"
					onkeyup="changefirstpaymentratio"></td>
				<td class="td-content-title">首付比例：</td>
				<td class="td-content"><input name="firstpaymentratio"
					id="firstpaymentratio" class="mini-textbox"
					value="<mini:param  name="firstpaymentratio" defaultValue="0"/>" 
					readonly><font class="required-tag">%</font></td>
				<td class="td-content-title">期末余值：</td>
				<td class="td-content"><input name="equipendvalue"
					id="equipendvalue" class="mini-textbox"
					value="<mini:param  name="equipendvalue" defaultValue="0"/>"></td>
			</tr>

			<tr class="tr-project-info tr-odd">
				<td class="td-content-title">融资额：</td>
				<td class="td-content"><input name="cleanleasemoney"
					vtype="double" id="cleanleasemoney" class="mini-textbox"
					value="<mini:param  name="cleanleasemoney" defaultValue="0"/>"></td>
				<td class="td-content-title">期初付款总计：</td>
				<td class="td-content"><input name="firstpaymenttotal"
					id="firstpaymenttotal" class="mini-textbox"
					value="<mini:param  name="firstpaymenttotal" defaultValue="0"/>"
					readonly></td>
				<td class="td-content-title">净授信额：</td>
				<td class="td-content"><input name="cleancreditmoney"
					id="cleancreditmoney" class="mini-textbox"
					value="<mini:param  name="cleancreditmoney" defaultValue="0"/>"
					readonly onkeyup="changecleancreditratio"></td>
				<td class="td-content-title">净授信比例：</td>
				<td class="td-content"><input name="cleancreditratio"
					id="cleancreditratio" class="mini-textbox"
					value="<mini:param  name="cleancreditratio" defaultValue="0"/>"
					readonly><font class="required-tag">%</font></td>
			</tr>
		</table>
	</div>

	<div class="mini-panel" title="租金推算方式" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">租金计算方式：</td>
				<td class="td-content">
					<input name="settlemethod" id="settlemethod"  class="mini-combobox" textField="name"  
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'settle_method'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="<mini:param  name="settlemethod"/>"
							   text="<mini:param  name="rawValue_settlemethod"/>"
							   onvaluechanged="comboboxChanged"
				 />
					<input id="rawValue_settlemethod" name="rawValue_settlemethod" value="<mini:param  name="rawValue_settlemethod"/>" class="mini-textbox" style="display:none"/>
				</td>
				<td class="td-content-title">租金推算方法：</td>
				<td class="td-content"><input id="rentorrate" name="rentorrate"
					class="mini-combobox" textField="name" valueField="value"
					dataField="datas" allowInput="false"
					value="<mini:param  name="rentorrate" defaultValue="rate"/>"
					text="<mini:param  name="rawValue_rentorrate" defaultValue="按年利率计算租金"/>"
					data-options="{_params:{pid:'rent_or_rate'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="rentorratevaluechange"
					onitemclick="rentorrateitemchange" /> <input
					id="rawValue_rentorrate" name="rawValue_rentorrate"
					value="<mini:param  name="rawValue_rentorrate"/>" class="mini-textbox" style="display:none"/></td>
				<td class="td-content-title"><span id="testrent">测算租金:</span><span
					id="testrate">测算利率：</span></td>
				<td class="td-content"><input name="rentorratevalue"
					id="rentorratevalue" class="mini-textbox"
					value="<mini:param  name="rentorratevalue" defaultValue="0"/>"
					onkeyup="rentorratevaluekeyup"></td>
				<td class="td-content-title">利率计算方式：</td>
				<td class="td-content"><input id="ratefloattype"
					name="ratefloattype" class="mini-combobox" textField="name"
					valueField="value" dataField="datas" allowInput="false"
					data-options="{_params:{pid:'rate_float_type'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="ratefloattypevaluechange"
					 value="<mini:param  name="ratefloattype" defaultValue="fixed"/>"
					text="<mini:param  name="rawValue_ratefloattype" defaultValue="固定利率"/>">
					<input id="rawValue_ratefloattype" name="rawValue_ratefloattype" value="<mini:param  name="rawValue_ratefloattype" />" class="mini-textbox" style="display:none"/></td>
			</tr>

			<tr class="tr-project-info tr-odd">
				<td class="td-content-title">利率调整值：</td>
				<td class="td-content"><input name="ratefloatamt"
					id="ratefloatamt" class="mini-textbox"
					value="<mini:param  name="ratefloatamt" defaultValue="0"/>"
					onkeyup="yearratekeyup"></td>
				<td class="td-content-title">基准利率：</td>
				<td class="td-content"><input name="baserate" id="baserate"
					class="mini-textbox"
					value="<mini:param  name="baserate" defaultValue="0"/>"
					onkeyup="yearratekeyup"></td>
				<td class="td-content-title">年利率：</td>
				<td class="td-content"><input name="yearrate" id="yearrate"
					class="mini-textbox"
					value="<mini:param  name="yearrate" defaultValue="0"/>" 
					readonly><font class="required-tag">%</font></td>
				<td class="td-content-title">期初/期末：</td>
				<td class="td-content"><input id="periodtype" name="periodtype"
					class="mini-combobox" textField="name" valueField="value"
					allowInput="false" dataField="datas"
					data-options="{_params:{pid:'period_type'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					value="<mini:param  name="periodtype" defaultValue="period_type_0"/>"
					text="<mini:param  name="rawValue_periodtype" defaultValue="期末"/>"
					onvaluechanged="comboboxChanged" /> 
					<input id="rawValue_periodtype" name="rawValue_periodtype" value="<mini:param  name="rawValue_periodtype"/>" class="mini-textbox" style="display:none"/></td>
			</tr>
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">租金支付类型：</td>
				<td class="td-content"><input id="incomenumberyear"
					name="incomenumberyear" class="mini-combobox" textField="name"
					valueField="value" dataField="datas" allowInput="false"
					data-options="{_params:{pid:'income_number_year'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="incomenumberyearvaluechange"
					value="<mini:param  name="incomenumberyear" defaultValue="income_1"/>"
					text="<mini:param  name="rawValue_incomenumberyear" defaultValue="月  付"/>"
					emptyText="" showNullItem="true" nullItemText="" />
					 <input id="rawValue_incomenumberyear" name="rawValue_incomenumberyear" value="<mini:param  name="rawValue_incomenumberyear"/>" class="mini-textbox" style="display:none"/></td>
				<td class="td-content-title">还租次数：</td>
				<td class="td-content"><input name="incomenumber"
					id="incomenumber" class="mini-textbox"
					value="<mini:param  name="incomenumber"  defaultValue="0"/>"
					onkeyup="incomenumberyearvaluechange"></td>
				<td class="td-content-title">宽限期：</td>
				<td class="td-content"><input name="grace" id="grace"
					class="mini-textbox"
					value="<mini:param  name="grace"  defaultValue="0"/>"
					onkeyup="incomenumberyearvaluechange"></td>
				<td class="td-content-title">租赁期限(月)：</td>
				<td class="td-content"><input name="leaseterm" id="leaseterm"
					class="mini-textbox"
					value="<mini:param  name="leaseterm"  defaultValue="0"/>"></td>
			</tr>
		</table>
	</div>

	<div class="mini-panel" title="测算条件" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">付款日：</td>
				<td class="td-content"><input name="leaseamtdate"
					id="leaseamtdate" class="mini-datepicker" allowInput="false"
					onvaluechanged="leaseamtdatevaluechange"
					value="<mini:param  name="leaseamtdate"/>"></td>
				<td class="td-content-title">起租日：</td>
				<td class="td-content"><input name="startdate" id="startdate"
					class="mini-datepicker" allowInput="false"
					value="<mini:param  name="startdate"/>"
					onvaluechanged="startdatevaluechange"></td>
				<td class="td-content-title">第一期租金支付日：</td>
				<td class="td-content"><input name="firstplandate"
					id="firstplandate" class="mini-datepicker" allowInput="false"
					value="<mini:param  name="firstplandate"/>"
					onvaluechanged="firstplandatevaluechange"></td>
				<td class="td-content-title">第二期租金支付日：</td>
				<td class="td-content"><input name="secondplandate"
					id="secondplandate" class="mini-datepicker" allowInput="false"
					value="<mini:param  name="secondplandate"/>"></td>
			</tr>
		</table>
	</div>
	<div class="mini-panel" title="资金信息" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">手续费：</td>
				<td class="td-content"><input name="handlingchargemoney"
					vtype="double" id="handlingchargemoney" class="mini-textbox"
					value="<mini:param  name="secondplandate"/>"
					onkeyup="changehandlingchargemoneyratio"></td>
				<td class="td-content-title">手续费比例：</td>
				<td class="td-content"><input name="handlingchargemoneyratio"
					id="handlingchargemoneyratio" class="mini-textbox"
					value="<mini:param  name="handlingchargemoneyratio"  defaultValue="0"/>"
					readonly><font class="required-tag">%</font></td>
				<td class="td-content-title">管理费：</td>
				<td class="td-content"><input name="managementmoney"
					vtype="double" id="managementmoney" class="mini-textbox"
					value="<mini:param  name="managementmoney"  defaultValue="0"/>"
					onkeyup="changemanagementmoneyratio"></td>
				<td class="td-content-title">管理费比例：</td>
				<td class="td-content"><input name="managementmoneyratio"
					id="managementmoneyratio" class="mini-textbox"
					value="<mini:param  name="managementmoneyratio"  defaultValue="0"/>"
					readonly><font class="required-tag">%</font></td>
			</tr>
			<tr class="tr-project-info tr-odd">

				<td class="td-content-title">保证金：</td>
				<td class="td-content"><input name="cautionmoney"
					vtype="double" id="cautionmoney" class="mini-textbox"
					value="<mini:param  name="cautionmoney"  defaultValue="0"/>"
					onkeyup="changecautiondeductionratio"></td>
				<td class="td-content-title">保证金比例：</td>
				<td class="td-content"><input name="cautiondeductionratio"
					id="cautiondeductionratio" class="mini-textbox"
					value="<mini:param  name="cautiondeductionratio"  defaultValue="0"/>"
					readonly><font class="required-tag">%</font></td>
				<td class="td-content-title">保证金抵扣金额：</td>
				<td class="td-content"><input name="cautiondeductionmoney"
					vtype="double" id="cautiondeductionmoney" class="mini-textbox"
					value="<mini:param  name="cautiondeductionmoney"  defaultValue="0"/>"
					onkeyup="cautionmoneyremainvaluechange"></td>
				<td class="td-content-title">保证金退还金额：</td>
				<td class="td-content"><input name="cautionmoneyremain"
					vtype="double" id="cautionmoneyremain" class="mini-textbox"
					value="<mini:param  name="cautionmoneyremain"  defaultValue="0"/>"
					readonly></td>
			</tr>
			<tr class="tr-project-info tr-even">

				<td class="td-content-title">保险计算方式：</td>
				<td class="td-content"><input id="insuremoneytype"
					name="insuremoneytype" class="mini-combobox" textField="name"
					valueField="value" dataField="datas" allowInput="false"
					data-options="{_params:{pid:'insure_type'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="insuremoneytypevaluechange"
					value="<mini:param  name="insuremoneytype" defaultValue="insure_type1"/>"
					text="<mini:param  name="rawValue_insuremoneytype" defaultValue="本司付款"/>"
					emptyText="" showNullItem="true" nullItemText="" /> <input id="rawValue_insuremoneytype" name="rawValue_insuremoneytype" value="<mini:param  name="rawValue_insuremoneytype"/>" class="mini-textbox" style="display:none"/></td>
				<td class="td-content-title">保险费(承租人)：</td>
				<td class="td-content"><input name="insurancelessee"
					id="insurancelessee" class="mini-textbox"
					value="<mini:param  name="insurancelessee" defaultValue="0"/>"
					onkeyup="insuremoneyvaluechange"></td>
				<td class="td-content-title">保险费(我司)：</td>
				<td class="td-content"><input name="insurancelessor"
					id="insurancelessor" class="mini-textbox"
					value="<mini:param  name="insurancelessor" defaultValue="0"/>"
					onkeyup="insuremoneyvaluechange"></td>
				<td class="td-content-title">保险费：</td>
				<td class="td-content"><input name="insuremoney"
					id="insuremoney" readonly class="mini-textbox"
					value="<mini:param  name="insuremoney" defaultValue="0"/>"
					readonly></td>
			</tr>
			<tr class="tr-project-info tr-odd">
				<td class="td-content-title">名义货价：</td>
				<td class="td-content"><input name="nominalprice"
					id="nominalprice" class="mini-textbox"
					value="<mini:param  name="nominalprice" defaultValue="0"/>"></td>
				<td class="td-content-title">租前息金额：</td>
				<td class="td-content"><input name="beforeinterest"
					id="beforeinterest" class="mini-textbox"
					value="<mini:param  name="beforeinterest" defaultValue="0"/>"></td>
				<td class="td-content-title">其他收入：</td>
				<td class="td-content"><input name="otherincome"
					id="otherincome" class="mini-textbox"
					value="<mini:param  name="otherincome" defaultValue="0"/>"></td>
				<td class="td-content-title">其他支出：</td>
				<td class="td-content"><input name="otherexpenditure"
					id="otherexpenditure" class="mini-textbox"
					value="<mini:param  name="otherexpenditure" defaultValue="0"/>"></td>
			</tr>
			<!-- 		          <tr class="tr-project-info tr-even"> -->
			<!-- 		          	  <td class="td-content-title">厂商返利：</td>    -->
			<%-- 		              <td class="td-content"><input name="returnamt" id ="returnamt"  class="mini-textbox" value="<mini:param name='returnamt' defaultValue='0'/>"></td> --%>
			<!-- 		          </tr> -->
		</table>
	</div>
	<div class="mini-panel" title="调息/罚息" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title">调息生效节点：</td>
				<td class="td-content"><input id="adjuststyle"
					name="adjuststyle" class="mini-combobox" textField="name"
					valueField="value" dataField="datas" allowInput="false"
					data-options="{_params:{pid:'adjust_style'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="comboboxChanged" emptyText="" showNullItem="true"
					nullItemText=""
					value="<mini:param  name="adjuststyle" defaultValue="next_list"/>"
					text="<mini:param  name="rawValue_adjuststyle" defaultValue="次期"/>">
					<input id="rawValue_adjuststyle" name="rawValue_adjuststyle" value="<mini:param  name="rawValue_adjuststyle"/>" 
					class="mini-textbox" style="display:none"/></td>
				<td class="td-content-title">罚息利率(万分之)：</td>
				<td class="td-content"><input name="penarate" id="penarate"
					class="mini-textbox"
					value="<mini:param  name="penarate" defaultValue="0"/>" >&#8241;</td>
				<td class="td-content-title">免罚息天数：</td>
				<td class="td-content"><input name="freedefainterday"
					id="freedefainterday" class="mini-textbox"
					value="<mini:param  name="freedefainterday" defaultValue="0"/>" ></td>
				<td class="td-content-title">&nbsp;</td>
				<td class="td-content" style="width: 160px;"></td>
			</tr>
		</table>
	</div>
	<div class="mini-panel" title="收益" showCollapseButton="true"
		style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"
			id="proj_base_info">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title" style="width:12.5%;">内部收益率：</td>
				<td class="td-content" style="width:12.5%;"><input name="irr" id="irr" readonly
					class="mini-textbox"
					value="<mini:param  name="irr" defaultValue="0"/>" ></td>
				<!-- 		              <td class="td-content-title">财务收益率：</td> -->
				<%-- 		              <td class="td-content"><input name="planirr" id ="planirr" readonly class="mini-textbox" value="<mini:param name='planirr' defaultValue='0'/>"></td> --%>
				<td class="td-content-title" style="width:12.5%;">项目粗利：</td>
				<td class="td-content"><input name="grossprofit"
					id="grossprofit" readonly class="mini-textbox"
					value="<mini:param  name="grossprofit" defaultValue="0"/>" ></td>
				<td class="td-content-title">&nbsp;</td>
				<td class="td-content">&nbsp;</td>

			</tr>
		</table>
	</div>
</div>

<script type="text/javascript">
jQuery(function(){
	//inputs[d].className=inputs[d].className+" element-readonly";
	var inputs = jQuery('#businessconditionFormReadonly').find('input').css('background','#f5f8fa');
	var form = new mini.Form("businessconditionFormReadonly");
	var fields = form.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性传入后台
		if(fields[index].uiCls == "mini-combobox" || fields[index].uiCls =="mini-datepicker"){
			fields[index].disable();
		}else{
			fields[index].allowInput=false;
		}
	}
});

</script>

