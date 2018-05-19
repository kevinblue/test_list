<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <%-- <input name="contract_info.developid" id="contract_info.developid" type="hidden" value="${requestScope['contract_info.developid'] }"/>
    <input name="contract_info.custInfo" id="contract_info.custInfo" type="hidden"  value="${requestScope['contract_info.custInfo'] }"/>
    <input name="contract_info.custclass" id="contract_info.custclass" type="hidden"  value="${requestScope['contract_info.custclass'] }"/>
    <input name="contract_info.id" id="contract_info.id" type="hidden"  value="${requestScope['contract_info.id'] }"/> --%>
    <div id="contract_base_info_form" title="风电场基本项目信息表">
    <div class="mini-panel" title="风电场基本项目信息表" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="contract_base_info" >
	          <tr class="tr-projectsss-info tr-even">
	             <td class="td-content-title" width="12%">报表类型：</td>
	             <td class="td-content" width="38%"><input name="projectname" readOnly id ="projectname"  class="mini-textbox" label="风电项目年度运行报告"  type="text" value="风电项目年度运行报告"></td>
	             <td class="td-content-title" width="12%">发起人：</td>
	             <td class="td-content" width="38%"> <input id="sponsor" readOnly name="sponsor" class="mini-textbox" value="${requestScope['login_username'] }" label="发起人" type="text"/></td>
	          </tr>
	</table>

	</div>
</div>
<script language="javascript">
</script>

