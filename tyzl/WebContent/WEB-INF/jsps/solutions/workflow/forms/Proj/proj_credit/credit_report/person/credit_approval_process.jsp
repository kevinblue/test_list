<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/minidict" prefix="mini"%>
	
	    
    <div class="mini-panel" title="评审进程" showCollapseButton="true" style="width: 99%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">上会时间：</td>
	             <td class="td-content">
	             <input name="proj_report.person.begindate" id="voteresult" class="mini-datepicker"   value="<mini:param  name="proj_report.person.begindate" />"/>
				 <font class="required-tag">*</font>
				 </td>
				 <td class="td-content-title">通过时间：</td>
	             <td class="td-content">
	             <input name="proj_report.person.approvaldate" class="mini-datepicker"   value="<mini:param  name="proj_report.person.approvaldate" />"/>
				 <font class="required-tag">*</font>
				 </td>
	          </tr>
		</table>
	</div>


