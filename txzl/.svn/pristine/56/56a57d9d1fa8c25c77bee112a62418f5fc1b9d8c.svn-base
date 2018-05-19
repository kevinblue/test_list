<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="model_parameter_form" title="经评模型参数">
	    <input id="id_special_regular_leasing_value" name="json_special_regular_leasing_value" class="mini-textbox" style="display:none"  allowInput="true"/>
    <div class="mini-panel" title="经评模型参数" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="model_parameter_was_evaluated" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">银行贷款模式利率(%):</td>
	             <td class="td-content" width="38%"><input name="interest_rate_input" id ="id_interest_rate_input"  class="mini-textbox" label="银行贷款模式利率"  type="text" required="true" value="${requestScope['interest_rate_input']}"></td>
	             <td class="td-content-title" width="12%">项目总投资：</td>
	             <td class="td-content" width="38%"> <input id="id_total_investment_str" name="json_total_investment_str" class="mini-textbox" value="${requestScope['json_total_investment_str'] }" label="项目总投资"  required="true" allowInput="true"/></td>
	          </tr>
	            
	    </table>

	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("model_parameter_form");};
	//onbusinesstypeChanged();
});	
</script>