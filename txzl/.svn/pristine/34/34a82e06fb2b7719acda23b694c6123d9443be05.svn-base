<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mini-panel" title="承租企业基本信息" showCollapseButton="true" style="width: 100%;"> 
    <table class="fillTable" cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
				<td class="td-content-title" width="12%">客户名称： </td>
		        <td class="td-content" width="38%">  <input name="proj_report.company.custname" id="proj_report.company.custname" class="mini-textbox" required="true" value="${requestScope['proj_report.company.custname'] }"/></td>
		        <td class="td-content-title" width="12%">组织代码： </td>
		        <td class="td-content" width="38%"><input name="proj_report.company.orgcode" id="proj_report.company.orgcode" class="mini-textbox" value="${requestScope['proj_report.company.orgcode'] }" required="true" requiredErrorText="客户名称不能为空"/> </td>		        
		    </tr>
		    <tr class="tr-project-info tr-odd">
		    	<td class="td-content-title" >法人代表： </td>
		        <td class="td-content"> <input name="proj_report.company.personrep" id="proj_report.company.personrep" class="mini-textbox" required="true" value="${requestScope['proj_report.company.personrep'] }"/> </td>
                <td class="td-content-title" >法人身份证号： </td>
		        <td class="td-content"><input name="proj_report.company.personidcard" id="proj_report.company.personidcard" class="mini-textbox" required="true" value="${requestScope['proj_report.company.personidcard'] }"/> </td>
		    </tr>
		    <tr class="tr-project-info tr-even">
		    	<td class="td-content-title" >员工人数： </td>
		        <td class="td-content"><input name="proj_report.company.empnumber" class="mini-textbox" value="${requestScope['proj_report.company.empnumber'] }" /></td>
		        <td class="td-content-title">企业性质：</td>
		        <td class="td-content">
		        	<input name="proj_report.company.ownership" id="proj_report_company_ownership" class="mini-combobox" 
		        			   textField="name" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'ownership'}}"
							   onbeforeshowpopup="miniextonbeforeshowpopup"
							   value="${requestScope['proj_report.company.ownership']}" 
							   text="${requestScope['rawValue_proj_report.company.ownership']}" 
							   onvaluechanged="comboboxChanged"/>	
				   <input id="rawValue_proj_report.company.ownership" name="rawValue_proj_report.company.ownership" value="${requestScope['rawValue_proj_report.company.ownership']}" class="mini-textbox" style="display:none"/>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title" >营业执照号： </td>
		        <td class="td-content"> <input  class="mini-textbox" name="proj_report.company.buslicense" value="${requestScope['proj_report.company.buslicense'] }" /> </td>
		        <td class="td-content-title" >注册资本： </td>
		        <td class="td-content" >  <input name="proj_report.company.regcapital" class="mini-textbox" value="${requestScope['proj_report.company.regcapital'] }"/></td>			
		    </tr>
			<tr class="tr-project-info tr-even">
		        <td class="td-content-title" >经营方式： </td>
		        <td class="td-content" colspan="3">  <input name="proj_report.company.operate" class="mini-textarea" style="width:73.5%;height:150px" value="${requestScope['proj_report.company.operate'] }"/> </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title" >经营范围（主营）：</td>
		        <td class="td-content" colspan="3">  <input name="proj_report.company.operatemaster" class="mini-textarea" style="width:73.5%;height:150px"  value="${requestScope['proj_report.company.operatemaster'] }"/></td>
		    </tr>
		    <tr class="tr-project-info tr-even">
		        <td class="td-content-title" >经营范围（兼营）：</td>
		        <td class="td-content" colspan="3"><input name="proj_report.company.operateminor" class="mini-textarea" style="width:73.5%;height:150px" value="${requestScope['proj_report.company.operateminor'] }"/></td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">客户概况：</td>
		        <td colspan="3" class="td-content"><input name="proj_report.company.custprobably" class="mini-textarea" style="width:73.5%;height:150px" value="${requestScope['proj_report.company.custprobably'] }"  /> </td>
		    </tr>
			<tr class="tr-project-info tr-even">
		        <td class="td-content-title">开户银行：</td>
		        <td class="td-content"> <input name="proj_report.company.taxbank" class="mini-textbox"  value="${requestScope['proj_report.company.taxbank'] }"/> </td>
		        <td class="td-content-title" >银行账号：</td>
		        <td class="td-content"> <input name="proj_report.company.taxacc" class="mini-textbox" value="${requestScope['proj_report.company.taxacc'] }"/> </td>
		    </tr>		
		</table>
	</div>
 
    


