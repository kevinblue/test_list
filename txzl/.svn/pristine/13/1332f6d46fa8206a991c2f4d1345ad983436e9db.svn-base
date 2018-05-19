<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="proj_info.custInfo" id="proj_info.custInfo" type="hidden"  value="${requestScope['proj_info.custInfo'] }"/>
    <input name="proj_info.projstatus" id="proj_info.projstatus" type="hidden"  value="${requestScope['proj_info.projstatus'] }"/>
    <input name="proj_info.custclass" id="proj_info.custclass" type="hidden"  value="${requestScope['proj_info.custclass'] }"/>
    <div id="risk_report_page.jsp" title="风险报送">

	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">风险名称：</td>
	             <td class="td-content" width="38%"><input name=" risk_info.riskname" id ="risk_name"  allowInput="false" class="mini-textbox" label="项目编号"  type="text" value="${requestScope['risk_info.riskname']}"></td>
	             <td class="td-content-title" width="12%">风险类型：</td>
	             <td class="td-content" width="38%"><input name=" risk_info.risktype" readOnly class="mini-combobox asLabel" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'战略风险'},{text:'战略风险'},{text:'操作风险'},{text:'财务风险'},{text:'外部风险'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.risktype'] }" 
						   text="${requestScope['rawValue_risk_info.risktype'] }" 
						   onvaluechanged="oncreditcardyesorno"/>
						   <input id="rawValue_risk_info.risktype" name="rawValue_risk_info.risktype" value="${requestScope['rawValue_risk_info.risktype']}" class="mini-textbox" style="display:none"/>
               </td>
	         
	          </tr>
	           <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">风险等级：</td>
	             <td class="td-content" width="38%"><input name="risk_info.risklevel" id ="risk_level"  allowInput="false" class="mini-textbox" label="项目编号"  type="text" value="${requestScope['risk_info.risklevel']}"></td>
	             <td class="td-content-title" width="12%">风险类别：</td>
	             <td class="td-content" width="38%"><input name="risk_info.riskcategories" readOnly class="mini-combobox asLabel" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'绿色'},{text:'黄色'},{text:'红色'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.riskcategories'] }" 
						   text="${requestScope['rawValue_risk_info.riskcategories'] }" 
						   onvaluechanged="oncreditcardyesorno"/>
						   <input id="rawValue_risk_info.riskcategories" name="rawValue_risk_info.riskcategories" value="${requestScope['rawValue_risk_info.riskcategories']}" class="mini-textbox" style="display:none"/>
                 </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">风险描述：</td>
	             <td class="td-content" colspan=3><input style="width:50%" name="risk_info.riskdescription" id="risk_description"   label="项目名称" allowInput="false" class="mini-textbox"  type="text" value="${requestScope['risk_info.riskdescription']}"></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">风险控制策略：</td>
	             <td class="td-content" colspan=3>
	             <input name=" risk_info.riskcontrolstrategy" readOnly class="mini-combobox asLabel" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'减轻风险'},{text:'风险转移'},{text:'风险承担'},{text:'拒绝风险'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['risk_info.riskcontrolstrategy'] }" 
						   text="${requestScope['rawValue_risk_info.riskcontrolstrategy'] }" 
						   onvaluechanged="oncreditcardyesorno"/>
						   <input id="rawValue_risk_info.riskcontrolstrategy" name="rawValue_risk_info.riskcontrolstrategy" value="${requestScope['rawValue_risk_info.riskcontrolstrategy']}" class="mini-textbox" style="display:none"/>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">风险控制措施：</td>
	             <td class="td-content" colspan=3 ><input style="width:50% " name=" risk_info.riskcontrolmeasures" id="risk_control_measures"   label="项目名称" allowInput="false" class="mini-textbox"  type="text" value="${requestScope['risk_info.riskcontrolmeasures']}"></td>
	          </tr>     
	</table>
	
</div>
<script language="javascript">

</script>

