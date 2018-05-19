<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="contract_patrol_plan_form" title="巡视基本信息">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width: 100%"
			class="fillTable">
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">检查时间：</td>
				<td class="td-content" width="100px"><input
					id="contract_patrol_info.checkTime"
					name="contract_patrol_info.checkTime" class="mini-datepicker"
					allowInput="false"
					value="${requestScope['contract_patrol_info.checkTime'] }"
					required="false" label="检查时间" /></td>
				<td class="td-content-title" width="12%">当前IRR：</td>
				<td class="td-content" width="100px"><input
					id="contract_patrol_info.CurrentYield"
					name="contract_patrol_info.CurrentYield" class="mini-textbox"
					allowInput="false"
					value="${requestScope['contract_patrol_info.CurrentYield'] }"
					required="false" label="当前IRR" readOnly/>
					<font class="required-tag">%</font>
					<a class="mini-button" iconCls="icon-user" plain="true" onclick="save" style="color: red;">当前IRR测算</a>
					</td>
			</tr>
			 
			
			<tr class="tr-odd">
				<td class="td-content-title" width="11%">参访人员：</td>
				<td class="td-content"width="100px">
					<%-- <input style="width: 50%; "
					class="mini-combobox"
					multiSelect="true"
					data-options="{_xmlFileName:'acl/queryAllUser.xml'}" 
					textfield="realname" valueField="id"
					allowInput="false" dataField="datas"
					emptyText="" onbeforeshowpopup="miniextonbeforeshowpopup"
					onvaluechanged="comboboxChanged"
					id="contract_patrol_info.visitPensonnel"
					name="contract_patrol_info.visitPensonnel" 
					value="${requestScope['contract_patrol_info.visitPensonnel'] }"
					text= "${requestScope['rawValue_contract_patrol_info.visitPensonnel'] }"
					required="false" label="参访人员" /> --%>
					<input name="contract_patrol_info.visitPensonnel" id="contract_patrol_info.visitPensonnel" 
					class="mini-combobox" textField="realname"  
					label="参访人员 " valueField="id" dataField="datas" multiSelect="true"
					allowInput="false"data-options="{_xmlFileName:'acl/queryAllUser.xml'}" 
					onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_patrol_info.visitPensonnel'] }" 
					text="${requestScope['rawValue_contract_patrol_info.visitPensonnel'] }"
					onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_patrol_info.visitPensonnel" name="rawValue_contract_patrol_info.visitPensonnel" value="${requestScope['rawValue_contract_patrol_info.visitPensonnel']}"
						class="mini-textbox" style="display: none" />
				</td>
			<!-- </tr>

			<tr class="tr-even"> -->
				<td class="td-content-title" width="12%">客户接待人员：</td>
				<td class="td-content"width="100px">
					<input 
					id="contract_patrol_info.custPersonnel"
					name="contract_patrol_info.custPersonnel" class="mini-textbox"
					allowInput="true" label="客户接待人员"
					value="${requestScope['contract_patrol_info.custPersonnel']}" />
				</td>
			</tr>
			<tr class="tr-odd">
		      <td class="td-content-title" width="12%">检查方式：</td>
		      <td class="td-content" >
                      <input id= "checkMode" 
                      name="contract_patrol_info.checkMode" 
                      class="mini-combobox" 
                      textField="text"   
                      valueField="value" 
                      allowInput="false" 
                      data="[{text:'现场',value:'1'},{text:'非现场',value:'2'}]"   
                      value="${requestScope['contract_patrol_info.checkMode'] }"/>
                 </td>
			</tr>
			
		</table>
		<br />
	</fieldset>
</div>
<script type="text/javascript">
	jQuery(function() {
		if ('${param.isView}' == 'true' || isViewHistoryTask == true) {
			miniui_ext.disableFormFields("contract_patrol_plan_form");
		};
		/* tenwa.createQueryInput({
			id:'contract_patrol_info.visitPensonnel',
			label:'归属人',
			windowWidth: 600,
			textField: 'name',
			valueField:'id',
			onSelect: function($me, inputObj, rowData){
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/proj_manage_combox.xml'
			}
		}); */
	});
	function save(){
		var contractid = mini.get("id_contractidselect_str").getValue();
		$.ajax({
	       url: "<%=request.getContextPath() %>/leasing/currentIRRCalculate.action",
	       type: "post",
	       data:  {"contractid":contractid },
	       success: function (text) {
	    	var result = mini.decode(text);
	       	mini.get("contract_patrol_info.CurrentYield").setValue(result.irr);
	       },
	       error: function (jqXHR, textStatus, errorThrown) {
	        mini.unmask(document.body);
	           alert(jqXHR.responseText);
	       }
		 });
	}
</script>
