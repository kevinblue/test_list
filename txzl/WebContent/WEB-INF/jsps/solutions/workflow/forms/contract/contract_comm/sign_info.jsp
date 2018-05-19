<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){

	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_sign_info_form");
	miniui_ext.disableFormFields("contrat_sign_info_form1");};

});
//打开页面是赋值千分位
window.onload = function(){
	var contractmoney= mini.get("contract_info.contractmoney").getValue();
	var contrctjine=formatNumberThousand(contractmoney);
	mini.get("contract_info.contractmoney").setValue(contrctjine);
}
/* jQuery(function(){
	tenwa.createQueryInput({
		id:'contract_info.custsignpeople',
		label:'签约人员',
		textField:"name",
      	valueField:"id",
		windowWidth: 600,
		multiSelect : true,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_contract_info.custsignpeople").setValue(rowData.name);
		},
		params: {
			cust_id:"${requestScope['contract_info.custid']}", 
			xmlFileName: '/eleasing/workflow/contract/contract_assets_report/contract_assets_sign_cust.xml'
		}
	});
}); */
function onpurchasetypetwobeforeshowpopup(e){
	var cust_id ='${requestScope["framework_condition.custid"]}';
	//cust_id.validate();
	var cb=e.sender;
	var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';
	var xmlFileName = cb._xmlFileName ||'/eleasing/workflow/contract/contract_assets_report/contract_assets_sign_cust.xml';
	url = url+"&xmlFileName="+xmlFileName+"&cust_id="+cust_id;
	cb.setUrl(url);
}
</script>
<input name="contract_invoice_type.id" id="contract_invoice_type.id" type="hidden" value="${requestScope['contract_invoice_type.id']}" />
	<div id="contrat_sign_info_form1">
			<table cellspacing="0" cellpadding="0" style="width:100%" height="60px" class="fillTable">
				<tr class="tr-odd">
					<td class="td-content-title" width="5%">结论意见：</td>
					<td class="td-content" width="38%" >
						<input  id="contract_info.conclusionopinion" style="width:95%;"  height="50px" name="contract_info.conclusionopinion" class="mini-textarea" required="true" label="结论意见" value="${requestScope['contract_info.conclusionopinion']}"></input>
					</td>
				</tr>
			</table>
	</div>
<div title="基本信息" showCollapseButton="true" class="mini-panel" style="width:100%" onload="signinfo()">
	<div  id="contract_sign_info_form" title="基本信息" >
		<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
			
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">签约日期：</td>
				<td class="td-content" width="38%">
					<input id="contract_info.signdatestart" name="contract_info.signdatestart" class="mini-datepicker"  class="mini-datepicker" required="true" label="开始签约日期" allowInput="false" value="${requestScope['contract_info.signdatestart']}">到</input>
					
					<input id="contract_info.signdateend" name="contract_info.signdateend" class="mini-datepicker"  required="true" label="结束签约日期" allowInput="false" value="${requestScope['contract_info.signdateend']}"/>
					
				</td>
				<td class="td-content-title" width="12%">是否提前开票：</td>
				<td class="td-content" width="38%"><input style="width:80%" name="contract_info.advancebilling" id ="contract_info.advancebilling" required="true" class="mini-combobox" label="是否提前开票" 
					textField="text" valueField="text"
					data="[{text:'是'},{text:'否'}]" 
					type="text" value="${requestScope['contract_info.advancebilling']}"></td>
				<%-- <td class="td-content-title" width="12%">项目金额：</td>
				<td class="td-content" width="38%">      
					<input style="width:80%" vtype="double" id="contract_info.contractmoney"  name="contract_info.contractmoney" allowInput="false" class="mini-textbox" required label="项目金额" value="${requestScope['equipamt']}"/>
				</td> --%>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">签约地点：</td>
				<td class="td-content" width="38%" colspan="4">
					<input style="width:91%" id="contract_info.signplace" name="contract_info.signplace" class="mini-textbox" required="true" label="签约地点" allowInput="true" value="${requestScope['contract_info.signplace']}"/>
				</td>
			</tr>
			<%-- <td class="td-content-title">业务子部：</td>
	             <td class="td-content">
	            <input id="proj_info.deptroute" name="proj_info.deptroute" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
	             		width="60%"
	             		value="${requestScope['proj_info.deptroute'] }"
						text="${requestScope['rawValue_proj_info.deptroute'] }"
						
						/>
					<input id="rawValue_proj_info.deptroute"
						name="rawValue_proj_info.deptroute"
						value="${requestScope['rawValue_proj_info.deptroute']}"
						class="mini-textbox"  style="display:none"/>
				 	</td> --%>
			<tr class="tr-even">
				<%-- <td class="td-content-title" width="12%">签约人员：客户</td>
				<td class="td-content" width="38%">
					<input style="width:92%" id="contract_info.custsignpeople" name="contract_info.custsignpeople" class="mini-combobox" 
					dataField="datas"
					textField="name"
					valueField="value"
					onbeforeshowpopup="onbeforeshowpopup"
					multiSelect="true"
					data-options="{_params:{cust_id:'${requestScope["framework_condition.custid"]}'},_xmlFileName:'/eleasing/workflow/contract/contract_assets_report/contract_assets_sign_cust.xml'}" 
					
					 
					 darequired="true" label="签约人员：客户" 
					value="${requestScope['contract_info.custsignpeople']}"
					text="${requestScope['rawValue_contract_info.custsignpeople']}"
					onvaluechanged="comboboxChanged"
					/>
					
					<input id="rawValue_contract_info.custsignpeople"
					name="rawValue_contract_info.custsignpeople"
					value="${requestScope['rawValue_contract_info.custsignpeople']}"
					class="mini-textbox"  />
				</td> --%>
			<td class="td-content-title" width='12%'>签约人员：客户</td>
			<td class="td-content" width='38%'>
				<input id="contract_info.custsignpeople"  style="width:92%"
					name="contract_info.custsignpeople" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    multiSelect="true"
				    onEnter="search"
				    onbeforeshowpopup="onpurchasetypetwobeforeshowpopup"
				    data-options="{_params:{cust_id:'${requestScope["framework_condition.custid"]}'},_xmlFileName:'/eleasing/workflow/contract/contract_assets_report/contract_assets_sign_cust.xml'}" 
					value="${requestScope['contract_info.custsignpeople'] }"
					text="${requestScope['rawValue_contract_info.custsignpeople']}"
					onvaluechanged="comboboxChanged"
					/>
				
				<input id="rawValue_contract_info.custsignpeople" 
				name="rawValue_contract_info.custsignpeople" 
				value="${requestScope['rawValue_contract_info.custsignpeople']}" 
				class="mini-textbox" style="display:none"/>
				
			</td>
				<td class="td-content-title" width="12%">签约人员：我司</td>
				<td class="td-content" width="38%">
					<input style="width:80%" id="contract_info.signpeople" name="contract_info.signpeople" class="mini-textbox" required="true" label="签约人员：我司" value="${requestScope['contract_info.signpeople']}"/>
				</td>
			</tr>
		</table>
	</div>
</div>
