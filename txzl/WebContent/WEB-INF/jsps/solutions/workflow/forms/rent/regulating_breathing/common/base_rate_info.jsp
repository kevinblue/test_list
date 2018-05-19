<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!-- 隐藏域 -->
<input type="hidden" name="fund_standard_interest.id" value="${requestScope['fund_standard_interest.id'] }"/>
  <div id="form_interest">
    <div class="mini-panel" title="调息基本信息" showCollapseButton="true" style="width: 100%;">
		<table class="fillTable"  id="fund_standard_interest" cellspacing="0" cellpadding="0">
		  <tr class="tr-even">
			    <td class="td-content-title" width="12%">调息发起时间：</td>
			    <td class="td-content"  width="38%">
			    	<input name="fund_standard_interest.startdate" id="fund_standard_interest.startdate" value="${empty requestScope['fund_standard_interest.startdate'] ? '':requestScope['fund_standard_interest.startdate']}"  class="mini-datepicker"  readonly/>
		    	</td>    
		  		<td class="td-content-title" width="12%">调息发起人：</td>
		  		<td class="td-content"  width="38%">
		  			<input name="fund_standard_interest.creator" id="fund_standard_interest.creator"  value="${empty requestScope['fund_standard_interest.creator'] ? sessionScope['sessionScope.login_realname']: requestScope['rawValue_fund_standard_interest.creator']}"   class="mini-textbox"  readonly/>
		  		</td>
		  </tr>
		</table>
	</div>
</div>
