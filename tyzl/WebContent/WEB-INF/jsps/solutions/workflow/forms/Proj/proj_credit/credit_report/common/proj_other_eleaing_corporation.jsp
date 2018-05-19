<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div class="mini-panel" title="与其他融资企业的相关交易情况" showCollapseButton="true" style="width: 100%;">
	<div id="personForm">
	    <table class="fillTable" cellspacing="0" cellpadding="0">
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title">承租人：</td><td class="td-content">
	             <input name="proj_report.otherelease.custname" id =""  class="mini-textbox" value="<mini:param  name="proj_report.otherelease.custname"/>">
	             </td>
	             <td class="td-content-title">借贷机构：</td><td class="td-content">
	             <input name="proj_report.otherelease.org" id =""  class="mini-textbox" value="<mini:param  name="proj_report.otherelease.org"/>" >
	             </td>
	         </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">模式/金额/月还款：</td>
	             <td class="td-content">
	             	<input id="" name="proj_report.otherelease.pattern" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.pattern"/>" />
	             </td>
	             <td class="td-content-title">余额：</td>
	             <td class="td-content">
		             <input  class="mini-textbox"  name="proj_report.otherelease.remainmoney"  value="<mini:param  name="proj_report.otherelease.remainmoney"/>" />
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">最后还款时间：</td>
	             <td class="td-content">
		             <input name="proj_report.otherelease.lastreturndate" class="mini-datepicker" value="<mini:param  name="proj_report.otherelease.lastreturndate"/>" />
	             </td>
	             <td class="td-content-title">担保人：</td>
	             <td class="td-content">
		             <input name="proj_report.otherelease.guarantor" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.guarantor"/>" />
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">融资总期数：</td>
	            <td class="td-content">
		             <input name="proj_report.otherelease.totaltime" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.totaltime"/>"/>
	             </td>
	             <td class="td-content-title">已付期数：</td>
	             <td class="td-content">
		             <input id="" name="proj_report.otherelease.alreadytime" class="mini-textbox"  value="<mini:param  name="proj_report.otherelease.alreadytime"/>" />
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title">到期期数：</td>
	             <td class="td-content" colspan=3>
	             	<input id="" name="proj_report.otherelease.duetime"  require="true" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.duetime"/>"  >
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">逾期期数：</td>
	             <td class="td-content">
		             <input id="" name="proj_report.otherelease.overduetime" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.overduetime"/>"/>
	             </td>
	             <td class="td-content-title">手机号码：</td>
	             <td class="td-content">
		             <input id="" name="proj_report.otherelease.phone" class="mini-textbox" value="<mini:param  name="proj_report.otherelease.phone"/>" />
	             </td>
	          </tr>
	          
		</table>
	</div>
</div>

