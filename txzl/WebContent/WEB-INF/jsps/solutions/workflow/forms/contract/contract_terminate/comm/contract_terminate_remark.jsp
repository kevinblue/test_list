<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="proj_other_condition1" title="原因及备注">
	<table class="fillTable" cellspacing="0" cellpadding="0">
          <tr class="tr-project-info tr-odd">
             <td class="td-content-title" >终止原因：</td>
             <td class="td-content">
             	<input style="width:80%;height:100px" name="contract_terminate.endreason"  required="true" label="终止原因"  value="${requestScope['contract_terminate.endreason'] }" class="mini-textarea" > 
             </td>
          </tr> 
          <tr class="tr-project-info tr-odd">
             <td class="td-content-title" >备注：</td>
             <td class="td-content">
             	<input style="width:80%;height:100px" name="contract_terminate.endmemo"  value="${requestScope['contract_terminate.endmemo'] }"  class="mini-textarea" > 
             </td>
          </tr>     
	 </table>
</div>