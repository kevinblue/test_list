<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_invoice_type.id" id="contract_invoice_type.id" type="hidden" value="${requestScope['contract_invoice_type.id']}" />
<div id="commerce_clause_change" title="商务条款变更">

	<div class="fillTableContainer">
           <div class="mini-panel" title="变更前商务条件"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
			    		<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更前IRR：</td>  
			              <td class="td-content">
			              		<input name="beforeirr" id ="beforeirr" readonly class="mini-textbox" value="${empty requestScope['beforeirr'] ? 0 : requestScope['beforeirr'] }"><font class="required-tag">%</font>
		              	  </td>
			    		  <td class="td-content-title" >变更前还租次数：</td>  
			              <td class="td-content">
			              		<input name="beforeincomenumber" id ="beforeincomenumber" readonly class="mini-textbox" value="${empty requestScope['beforeincomenumber'] ? 0 : requestScope['beforeincomenumber'] }"><font class="required-tag"></font>
		              	  </td>
			            </tr>
			    		<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更前租赁期限(月)：</td>  
			              <td class="td-content">
			              		<input name="beforeleaseterm" id ="beforeleaseterm" readonly class="mini-textbox" value="${empty requestScope['beforeleaseterm'] ? 0 : requestScope['beforeleaseterm'] }"><font class="required-tag"></font>
		              	  </td>
			    		  <td class="td-content-title" >变更前净融资额：</td>  
			              <td class="td-content">
			              		<input name="beforecleancreditmoney" id ="beforecleancreditmoney" readonly class="mini-textbox" value="${empty requestScope['beforecleancreditmoney'] ? 0 : requestScope['beforecleancreditmoney'] }"><font class="required-tag"></font>
		              	  </td>
			            </tr>
			    		<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更前项目粗利：</td>  
			              <td class="td-content">
			              		<input name="beforegrossprofit" id ="beforegrossprofit" readonly class="mini-textbox" value="${empty requestScope['beforegrossprofit'] ? 0 : requestScope['beforegrossprofit'] }"><font class="required-tag"></font>
		              	  </td>
		              	   <td></td>
		              	   <td></td>
			            </tr>
				</table>
          </div>
          <div class="mini-panel" title="变更后商务条件"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
			  			<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更后IRR：</td>  
			              <td class="td-content">
			              		<input name="irr" id ="irr" readonly class="mini-textbox" value="${empty requestScope['irr'] ? 0 : requestScope['irr'] }"><font class="required-tag">%</font>
		              	  </td>
			    		  <td class="td-content-title" >变更后还租次数：</td>  
			              <td class="td-content">
			              		<input name="incomenumber" id ="incomenumber" readonly class="mini-textbox" value="${empty requestScope['incomenumber'] ? 0 : requestScope['incomenumber'] }"><font class="required-tag"></font>
		              	  </td>
			            </tr>
			    		<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更后租赁期限(月)：</td>  
			              <td class="td-content">
			              		<input name="leaseterm" id ="leaseterm"  class="mini-textbox" value="${empty requestScope['leaseterm'] ? 0 : requestScope['leaseterm'] }"><font class="required-tag"></font>
		              	  </td>
			    		  <td class="td-content-title" >变更后净融资额：</td>  
			              <td class="td-content">
			              		<input name="cleancreditmoney" id ="cleancreditmoney" readonly class="mini-textbox" value="${empty requestScope['cleancreditmoney'] ? 0 : requestScope['cleancreditmoney'] }"><font class="required-tag"></font>
		              	  </td>
			            </tr>
			    		<tr class="tr-project-info tr-odd">
			    		  <td class="td-content-title" >变更后项目粗利：</td>  
			              <td class="td-content">
			              		<input name="grossprofit" id ="grossprofit" readonly class="mini-textbox" value="${empty requestScope['grossprofit'] ? 0 : requestScope['grossprofit'] }"><font class="required-tag"></font>
		              	  </td>
		              	  <td></td>
		              	  <td></td>
			            </tr>
				</table>
          </div>
	</div>
	<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
	  	<div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
			 <div title="变更前租金计划" name="rent_plan_before" iconCls="icon-cut">
				<jsp:include page="rent_plan_before.jsp" ></jsp:include>
			</div>
			<div title="变更后租金计划" name="rent_plan_after" iconCls="icon-cut">
				<jsp:include page="rent_plan_after.jsp" ></jsp:include>
			</div> 
		</div>
	</div>
	<!-- 隐藏域 -->
	<div style="display: none;">
	    <input name="cleancreditratio" id ="cleancreditratio"  class="mini-textbox" value="${empty requestScope['cleancreditratio'] ? 0 : requestScope['cleancreditratio'] }" ><%-- 净融资额比例 --%>
		<input name="enddate" id ="enddate"  class="mini-textbox" value="${empty requestScope['enddate'] ? 0 : requestScope['enddate']}"><%-- 结束日期 --%>
		<input name="firstpaymenttotal" id ="firstpaymenttotal"  class="mini-textbox" value="${empty requestScope['firstpaymenttotal'] ? 0 : requestScope['firstpaymenttotal']}"><%-- 期初付款总计 --%>
		<input name="json_fund_rent_plan_str" 	id="id_json_fund_rent_plan_str" 	type="hidden" value='${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str}'/><%-- 租金计划 --%>
		<input name="json_fund_cash_flow_str" 	id="id_json_fund_cash_flow_str" 	type="hidden" value='${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str }'/><%-- 现金流 --%>
	</div>
</div>
<script type="text/javascript">

/* jQuery(function(){	
	 var showTools = true;
	 if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	 if(showTools==false){
	 	miniui_ext.disableFormFields("contract_rent_invoice_type_form");
	 } 
	 if(mini.get("taxregtype").getValue()=="tax_level_name.1"){
		 mini.get("taxregcode").setRequired(true);
	 }else{
		 mini.get("taxregcode").setRequired(false);
	 }
	 miniui_ext.disableFormFields("rentinvoicetype");
	 miniui_ext.disableFormFields("invoicetype");
	 taxregtypeonvaluechanged();
});
 */

</script>