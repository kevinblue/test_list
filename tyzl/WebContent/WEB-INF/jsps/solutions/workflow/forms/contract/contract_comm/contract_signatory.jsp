<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--合同各方信息 -->
<div id="contract_signatory_form" style="overflow: auto;" title="合同各方">
	<input require="true" label="出租人" id="contract_signatory_id" name="contract_signatory.id" class="mini-textbox"  style="display:none" value="<mini:param  name="contract_signatory.id"/>" >
	<table cellspacing="0" cellpadding="0" width="100%" class="fillTable" id="contract_signatory_form">
		<tr>
			<td colspan="4">
				<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
					<tr>
						<td class="x-panel-table-div-title" colspan=4>出租方基本信息(甲方)</td>
					</tr>
					<tr class="tr-lessor-info tr-even">
						<td class="td-content-title" width="12%">出租人：</td>
						<td class="td-content" width="38%"><input require="true" label="出租人" id="contract_signatory_lessor" name="contract_signatory.lessor" class="mini-textbox" value="<mini:param  name="contract_signatory.lessor"/>" ></td>
						<td class="td-content-title" width="12%">委托代理人：</td>
						<td class="td-content" width="38%"><input require="true" label="委托代理人" name="contract_signatory.leaseconsigner" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseconsigner"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-odd">
						<td class="td-content-title">注册地址：</td>
						<td class="td-content"><input require="true" label="注册地址" name="contract_signatory.leaseregisteraddr" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseregisteraddr"/>" ></td>
						<td class="td-content-title">通讯地址：</td>
						<td class="td-content"><input require="true" label="通讯地址" name="contract_signatory.leaseaddr" class="mini-textbox"  value="<mini:param  name="contract_signatory.leaseaddr"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-even">
						<td class="td-content-title">法定代表人：</td>
						<td class="td-content"><input require="true" label="法定代表人" id="contract_signatory_leaseperson" name="contract_signatory.leaseperson" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseperson"/>" ></td>
						<td class="td-content-title">邮编：</td>
						<td class="td-content"><input require="true" label="邮编" id="contract_signatory_leasepostcode" name="contract_signatory.leasepostcode" class="mini-textbox" value="<mini:param  name="contract_signatory.leasepostcode"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-odd">
						<td class="td-content-title">联系人：</td>
						<td class="td-content"><input require="true" label="联系人" name="contract_signatory.leaselinkman" class="mini-textbox"  value="<mini:param  name="contract_signatory.leaselinkman"/>"  ></td>
						<td class="td-content-title">电话：</td>
						<td class="td-content"><input require="true" label="电话" id="contract_signatory_leasetel" name="contract_signatory.leasetel" class="mini-textbox" value="<mini:param  name="contract_signatory.leasetel"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-even">
						<td class="td-content-title">传真：</td>
						<td class="td-content"><input label="传真" id="contract_signatory_leasefax" name="contract_signatory.leasefax" class="mini-textbox" value="<mini:param  name="contract_signatory.leasefax"/>"  ></td>
						<td class="td-content-title">电子邮件：</td>
						<td class="td-content"><input label="电子邮件" name="contract_signatory.leaseemail" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseemail"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-odd">
						<td class="td-content-title">开户账号：</td>
						<td class="td-content">
							<input name="contract_signatory.leaseaccnumber" label="开户账号"  class="mini-combobox" textField="accnumber" valueField="accnumber" dataField="datas" allowInput="false"
							data-options="{_xmlFileName:'/eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml'}"  
							value="<mini:param  name="contract_signatory.leaseaccnumber"/>"  
							text="<mini:param  name="contract_signatory.leaseaccnumber"/>"
							onvaluechanged="leaseaccnumbervaluechange" onbeforeshowpopup="miniextonbeforeshowpopup"/>
						</td>
						<td class="td-content-title">开户银行：</td>
						<td class="td-content"><input require="true" label="开户银行" readonly name="contract_signatory.leaseaccbank" id="contract_signatory_leaseaccbank" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseaccbank"/>" ></td>
					</tr>
					<tr class="tr-lessor-info tr-even">
						<td class="td-content-title">开户户名：</td>
						<td class="td-content"><input require="true" label="开户户名" readonly name="contract_signatory.leaseaccname" id="contract_signatory_leaseaccname" class="mini-textbox" value="<mini:param  name="contract_signatory.leaseaccname"/>" ></td>
						<td class="td-content-title"></td>
						<td class="td-content"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
					<tr>
						<td class="x-panel-table-div-title" colspan=4>承租方基本信息(乙方)</td>
					</tr>
					<tr>
						<td colspan=4>
							<div id="id_table_contract_signatory"></div>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
		<tr>
			<td class="x-panel-table-div-title" colspan=4>卖方/供应商</td>
		</tr>
	</table>
	<div id="id_table_leasing_contract_supplier_container"></div>
</div>
<script>
	function leaseaccnumbervaluechange(e){
		var selected = e.selected;
		mini.getbyName("contract_signatory.leaseaccbank").setValue(selected.accbank);
		mini.getbyName("contract_signatory.leaseaccname").setValue(selected.accname);
	}
	function clientaccnumbervaluechange(e){
		var selected = e.selected; 
		mini.getbyName("contract_signatory.clientaccbank").setValue(selected.bankname); 
		mini.getbyName("contract_signatory.clientaccname").setValue(selected.account);
	}
	var dealerid = "<mini:param name='contract_info.dealerid'/>";
	jQuery(function() {
		var showTools = true;
		if ('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true) {showTools = false;}
		tenwa.createTable({
			id : "contract_signatory",
			renderTo : "id_table_contract_signatory",
			width : '100%',
			height : 250,
			title : "",
			remoteOper : false,
			showPager : false,
			showToolbar : showTools,
			tools : ['add', '-', 'edit', '-', 'remove' ],
			lazyLoad : true,
			data : JsonUtil.decode('${empty json_contract_signatory_str ? "[]" : json_contract_signatory_str }'),
			columns : [
					{type:'indexcolumn'},
					{type: 'checkcolumn'},
					{field:'client', header:'承租人',
						   formEditorConfig:{required:true}},
  				    {field:'clientperson',header:'法定代表人',
						   formEditorConfig:{required:true}},
					{field:'clientlinkman',header:'联系人',
						   formEditorConfig:{
							   required:true,
						        newLine:true,
							     labelWidth:100}},
				    {field:'clientmobilenumber',header:'手机号',
						   formEditorConfig:{required:true}},
					{field:'clienttel',header:'电话',
							 formEditorConfig:{required:true,
							        newLine:true}},		
				    {field:'clientfax',header:'传真',
						formEditorConfig:{}},

				   {field:'clientemail',header:'电子邮件',
						formEditorConfig:{
					        newLine:true}},
					{field:'clientaddress',header:'联系地址',
						formEditorConfig:{required:true}},
					{field:'clientpostcode',header:'邮政编码',
						formEditorConfig:{
					        newLine:true}}
					]
		});
		tenwa.createTable({
					id : "contract_supplier",
					renderTo : "id_table_leasing_contract_supplier_container",
					width : '100%',
					height : 250,
					title : "",
					remoteOper : false,
					showPager : false,
					showToolbar : showTools,
					tools : ['add', '-', 'edit', '-', 'remove' ],
					lazyLoad : true,
					data: JsonUtil.decode('<mini:param  name="json_contract_supplier_str" defaultValue="[]"/>'),
					columns : [
							{type:'indexcolumn'},
							{type: 'checkcolumn'},
							{field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
								   formEditorConfig:{
									       readOnly:true,
									   fieldVisible:false}},
							{field:'sellername', header:'供应商', visible:true,
								   formEditorConfig:{
								       fieldVisible:false}},
		  					{field:'seller',header:'供应商',visible:false,
						  		   formEditorConfig:{
		  						            newLine:true,
		  						              width:200,
		  						               type:'queryinput',
		  						           required:true,
		  						          textField:'sellername',
		  						         valueField:'seller',
		  						         allowInput:false,
		  						       fieldVisible:true,
		  						 onBeforeShowWindow:function($me){$me.params.custidstr = getAddedSellers();return true;},
		  						           onSelect:function($me, queryInput, rowData){
						  							mini.getbyName("sellerlegalperson").setValue(rowData.sellerlegalperson);
						  							mini.getbyName("sellerlinkman").setValue(rowData.sellerlinkman);
						  							mini.getbyName("sellerregisteraddr").setValue(rowData.sellerlink);
						  							mini.getbyName("selleraddr").setValue(rowData.selleraddr);
						  							mini.getbyName("sellerpostcode").setValue(rowData.sellerpostcode);
						  							mini.getbyName("sellertel").setValue(rowData.sellertel);
						  							mini.getbyName("sellerfax").setValue(rowData.sellerfax);
						  							clearCustbank();},
		  						             params:{xmlFileName:'/eleasing/workflow/contract/contract_approval/get_contract_supplier_info.xml'}}},
		  				    {field:'sellerlegalperson',header:'法定代表人',
								   formEditorConfig:{
									       readOnly:true,
								  fillFromFieldName:'seller',
									   fillProperty:'sellerlegalperson'}},
							{field:'sellerlinkman',header:'联系人',
								   formEditorConfig:{
									        newLine:true,
									     labelWidth:100,
									       readOnly:true,
								  fillFromFieldName:'seller',
								       fillProperty:'sellerlinkman'}},
						    {field:'sellerregisteraddr',header:'注册地址',
								   formEditorConfig:{
									       readOnly:true,
								  fillFromFieldName:'seller',
									   fillProperty:'sellerregisteraddr'}},
						    {field:'selleraddr',header:'通讯地址',
								formEditorConfig:{
									     newLine:true,
									    readOnly:true,
							   fillFromFieldName:'seller',
									fillProperty:'selleraddr'}},
							{field:'sellerpostcode',header:'邮政编码',
							    formEditorConfig:{
									    readOnly:true,
							   fillFromFieldName:'seller',
									fillProperty:'sellerpostcode'}},
						   {field:'sellertel',header:'电话',
								formEditorConfig:{
									     newLine:true,
									    readOnly:true,
							   fillFromFieldName:'seller',
									fillProperty:'sellertel'}},
							{field:'sellerfax',header:'传真',
								formEditorConfig:{
									    readOnly:true,
							   fillFromFieldName:'seller',
									fillProperty:'sellerfax'}},
							{field:'selleraccnumbername', header:'开户账号',visible:false,formEditorConfig:{
								fillFromFieldName:'clientbank',
								fillProperty:'clientbank'}},
							{field:'selleraccnumber', header:'开户账号',
								 formEditorConfig:{
										  newLine:true,
										     type:'combobox', 
										 required:true, 
									  multiSelect:false,
										 lazyLoad:true,
									   valueField:'accnumber', 
										textField:'accnumber', 
									   labelWidth:100, 
										    width:200, 
								    onbuttonclick:'initCustbank',
										   params:{xmlFileName:'/eleasing/workflow/contract/contract_approval/get_bankaccount_by_custid.xml'
											   ,custid:'xx'}}},   
							{field:'selleraccbank', header:'开户银行',
								 formEditorConfig:{
								fillFromFieldName:'selleraccnumber',
									 fillProperty:'bankname',  
							   			 readonly:true}},
							{field:'selleraccname', header:'开户户名',
								 formEditorConfig:{
								fillFromFieldName:'selleraccnumber',
									 fillProperty:'account',
							   			 readonly:true,
							   			  newLine:true,
							   			  colspan:3}}
							]
				});
	});
	function initCustbank(){
	    var param={};
	    param["custid"]=getMiniEditFormField("contract_supplier.seller").getValue();
	    var selleraccnumber=getMiniEditFormField("contract_supplier.selleraccnumber");
	    seajs.use([ "js/apcomponent/aputil/aputil.js" ],function(ApUtil){
	    	 ApUtil.setComboxParams(selleraccnumber,param);
	     });
	}
	function clearCustbank(){
		getMiniEditFormField("contract_supplier.selleraccbank").setValue("");
		getMiniEditFormField("contract_supplier.selleraccname").setValue("");
		getMiniEditFormField("contract_supplier.selleraccnumber").setValue("");
		getMiniEditFormField("contract_supplier.selleraccnumber").setText("");
	}
	//获取已经添加过的供应商
	function getAddedSellers(){
		var sellers = "";
		var grid_supplier_data = mini.get("contract_supplier").getData();
		for(var i = 0;i < grid_supplier_data.length;i++){
			sellers = sellers + "'" + grid_supplier_data[i].seller + "',";
		}
		sellers = sellers.substring(0,sellers.length-1);
		return sellers;
	}
	jQuery(function(){
		if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_signatory_form");};
	});
    //当租赁形式为厂商、经营性租赁，承租人(乙方)可选
	jQuery(function(){/* 
			var leasValue = mini.get("rawValue_contract_info.leasform").getValue("contract_info.leasform");
		    if(leasValue == '厂商' || leasValue == '经营性租赁'){
			tenwa.createQueryInput({
						id : 'contract_signatory.client',
						label : '承租人',
						textField : "name",
						valueField : "id",
						windowWidth : 600,
						onSelect : function($me, inputObj, rowData) {
							mini.get("rawValue_contract_signatory.client")
									.setValue(rowData.NAME);
							mini.getbyName("contract_signatory.clientconsigner")
									.setValue(rowData.name);
							mini.getbyName("contract_signatory.clientaddress")
									.setValue(rowData.company_address_value);
							mini.getbyName("contract_signatory.clientpostcode")
									.setValue(rowData.postcodevalue);
							mini.getbyName("contract_signatory.clientperson")
									.setValue(rowData.person_repvalue);
							mini.get("contract_signatory_clientlinkman")
									.setValue(rowData.name);
							mini.get("contract_signatory_clienttel").setValue(
									rowData.mobile_numbervalue);
							mini.getbyName("contract_signatory.clientfax")
									.setValue(rowData.cir_fax_value);
							mini.get("contract_signatory_clientmobilenumber")
									.setValue(rowData.moblie);
							mini.getbyName("contract_signatory.clientemail")
									.setValue(rowData.maila_ddressvalue);
							mini.get("contract_signatory_clientaccnumber")
									.setText(rowData.acc_number_value);
							mini.get("contract_signatory_clientaccbank")
									.setValue(rowData.bank_name_value);
							mini.get("contract_signatory_clientaccname")
									.setValue(rowData.account_value);
						},
						params : {
							xmlFileName : '/eleasing/workflow/contract/contract_approval/get_contract_clienterInfo.xml'
						}
					});
		  }else {
			var ClientText = mini.get("contract_signatory.client").getValue();
			mini.get("rawValue_contract_signatory.client").setValue(ClientText);
			mini.get("contract_signatory.client").setValue(ClientText);
			__saveButtonDisplayText;
		   } */
	});
</script>