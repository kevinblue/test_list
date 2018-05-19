<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="contract_info_agent_detail" title="代理商"  showCollapseButton="true" class="mini-panel" style="width:100%">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">项目来源：</td>
					<td class="td-content" width="38%">
					<input  name="contract_info.agentsource" label="项目来源" class="mini-combobox" textField="name"  required="false"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'agent_source'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['contract_info.agentsource'] }" 
							   text="${requestScope['rawValue_contract_info.agentsource']}" 
							   onvaluechanged="comboboxChanged(e)"
				    />
				    <input id="rawValue_contract_info.agentsource" name="rawValue_contract_info.agentsource" value="${requestScope['rawValue_contract_info.agentsource']}"  class="mini-textbox" style="display:none"/>
	               </td>
	               <td class="td-content-title" width="12%">公司：</td>
				   <td class="td-content" width="38%"><input name="contract_info.agentcompany" label="公司" class="mini-textbox" value="${requestScope['contract_info.agentcompany'] }" allowInput="true" /></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">姓名：</td>
					<td class="td-content">
						<input name="contract_info.agentname" label="姓名" class="mini-textbox" value="${requestScope['contract_info.agentname'] }" allowInput="true" /></td>
					<td class="td-content-title">职位：</td>
					<td class="td-content">
						<input name="contract_info.agentjob" label="职位" class="mini-textbox" value="${requestScope['contract_info.agentjob'] }" allowInput="true" /></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">手机：</td>
					<td colspan="3" class="td-content">
						<input name="contract_info.agentphone" label="手机" class="mini-textbox" value="${requestScope['contract_info.agentphone'] }" allowInput="true" /></td>
				</tr>
	</table>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("contract_info_agent_detail");}
</script>


 --%>