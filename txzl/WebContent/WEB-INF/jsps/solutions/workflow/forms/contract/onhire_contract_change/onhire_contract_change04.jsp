<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	var oldcontractequip=mini.get("old_contract_equip").getData();
	var oldtotalPrice = 0;
	for ( var i = 0; i < oldcontractequip.length; i++) {
		oldtotalPrice += parseFloat(oldcontractequip[i]['equipprice']);
	}
	var newtotalPrice=0;
	var newcontractequip=mini.get("contract_equip").getData();
	for ( var i = 0; i < newcontractequip.length; i++) {
		newtotalPrice += parseFloat(newcontractequip[i]['equipprice']);
	}
	if(newtotalPrice<oldtotalPrice){
		mini.alert("新设备价值和应大于等于旧设备价值和");
		return false;
	}
	return true;
}

function workflowNextRouteCallBack(buttonText,requestNextRoute){
	/* var changetype=mini.get("changetype").getValue();
	alert(mini.get("changetype").getValue());
	//console.info(changetype);
       if(changetype=='onhire_contract_change_type01'){
    	   requestNextRoute.value="租赁物变更";
       }else if(changetype=='onhire_contract_change_type02'){
    	   requestNextRoute.value="账户变更";
       }else{
    	   requestNextRoute.value="其他类型变更";
       } */
}

</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_old_contract_equip_str" name="json_old_contract_equip_str" value='${empty json_old_contract_equip_str ? "[]" : json_old_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_old_contract_guarantee_method_str" name="json_old_contract_guarantee_method_str" value='${empty json_old_contract_guarantee_method_str ? "[]" : json_old_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_equip_str" name="json_contract_guarantee_equip_str" value='${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_guarantee_method_str" name="json_contract_guarantee_method_str" value='${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_old_contract_guarantee_equip_str" name="json_old_contract_guarantee_equip_str" value='${empty json_old_contract_guarantee_equip_str ? "[]" : json_old_contract_guarantee_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>

<div id="onhire_contract_change_form" title="合同变更">
	<div class="fillTableContainer">
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="../contract_assets_sign_report/contract_assets_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="comm/onhire_contract_change_item.jsp"><jsp:param value="false" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab" >
		<div title="变更说明" name="contract_change_info" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_change_info01.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="承租人变更" name="contract_cust_change" id="contract_cust_change" iconCls="icon-node">
			<jsp:include page="comm/onhire_contract_cust_change.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="租赁物明细" name="old_contract_equip" id="old_contract_equip" iconCls="icon-node">
			<jsp:include page="comm/contract_equip_detail.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method,old_contract_guarantee_method" id="contract_guarantee_method,old_contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="comm/contract_guarantee_method.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="抵质押物信息" name="contract_guarantee_equip,old_contract_guarantee_equip" id="contract_guarantee_equip,old_contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="comm/contract_guarantee_equip.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="其它实质性变更" name="contract_change_other_info" id="contract_change_other_info" iconCls="icon-node">
			<jsp:include page="comm/contract_change_other_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="商务条款变更" name="business_condition" id="business_condition" iconCls="icon-node">
			<jsp:include page="comm/commerce_clause_change.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="租金计划变更" name="contract_supplier" id="contract_supplier" iconCls="icon-node">
			<jsp:include page="comm/rent_plan_change.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		 <div title="合同变更协议" name="contract_planchange_tab" id="contract_planchange_tab" iconCls="icon-cut">
			<jsp:include page="../contract_comm/contract_change_word1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div> 
	</div>
</div>
<script>
jQuery(function(){
	var tabs=mini.get("tabDetails");
	var tab=tabs.getTab("old_contract_equip");
	tabs.updateTab(tab,{visible:false});
	
	var tab1=tabs.getTab("contract_cust_change");
	tabs.updateTab(tab1,{visible:false});
	
	var tab2=tabs.getTab("contract_guarantee_equip,old_contract_guarantee_equip");
	tabs.updateTab(tab2,{visible:false});
	
	var tab3=tabs.getTab("contract_guarantee_method,old_contract_guarantee_method");
	tabs.updateTab(tab3,{visible:false});
	
	var tab4=tabs.getTab("contract_change_other_info");
	tabs.updateTab(tab4,{visible:false});
	
	var tab5=tabs.getTab("business_condition");
	tabs.updateTab(tab5,{visible:false});
	
	var tab6=tabs.getTab("contract_supplier");
	tabs.updateTab(tab6,{visible:false});
	
	var tab7=tabs.getTab("contract_planchange_tab");
	tabs.updateTab(tab7,{visible:false});
	
	var sub=mini.get("proj_info.subchangecontent").getValue();
	var ary = sub.split(",");
	for(var i=0;i<ary.length;i++){
		if(ary[i]=="租赁物变更"){
			var tabs=mini.get("tabDetails");
			var tab=tabs.getTab("old_contract_equip");
			tabs.updateTab(tab,{visible:true});
		}else if(ary[i]=="承租人变更"){
			var tabs=mini.get("tabDetails");
			var tab=tabs.getTab("contract_cust_change");
			tabs.updateTab(tab,{visible:true});
		}else if(ary[i]=="抵押物变更"){
			var tabs=mini.get("tabDetails");
			var tab=tabs.getTab("contract_guarantee_equip,old_contract_guarantee_equip");
			tabs.updateTab(tab,{visible:true});
		}else if(ary[i]=="担保变更"){
			var tabs=mini.get("tabDetails");
			var tab=tabs.getTab("contract_guarantee_method,old_contract_guarantee_method");
			tabs.updateTab(tab,{visible:true});
		}else if(ary[i]=="其他性质变更"){
			var tabs=mini.get("tabDetails");
			var tab=tabs.getTab("contract_change_other_info");
			tabs.updateTab(tab,{visible:true});
		}
	}
	var subnot=mini.get("proj_info.subnotchangecontent").getValue();
	if(subnot=="商务条款变更"){
		var tabs=mini.get("tabDetails");
		var tab=tabs.getTab("business_condition");
		tabs.updateTab(tab,{visible:true});
	}else if(subnot==""){
		var tab8=tabs.getTab("business_condition");
		tabs.updateTab(tab8,{visible:false});
		
		var tab9=tabs.getTab("contract_supplier");
		tabs.updateTab(tab9,{visible:false});
		
		var tab10=tabs.getTab("contract_planchange_tab");
		tabs.updateTab(tab10,{visible:false});
	}else{
		var tabs=mini.get("tabDetails");
		var tab=tabs.getTab("contract_supplier");
		tabs.updateTab(tab,{visible:true});
		var tabs1=mini.get("tabDetails");
		var tab1=tabs1.getTab("contract_planchange_tab");
		tabs1.updateTab(tab1,{visible:true});
	} 
});
//表格值填入隐藏域
function fillHiddenVal(){
	 
	var grid_new_equip_data = mini.get("contract_equip").getData();
	mini.get("id_json_contract_equip_str").setValue(mini.encode(grid_new_equip_data));
	if(mini.get("contract_guarantee_method").isinitData==1){
		var grid_new_guarantee_method_data = mini.get("contract_guarantee_method").getData();
		mini.get("id_json_contract_guarantee_method_str").setValue(mini.encode(grid_new_guarantee_method_data));
	}	 
	if(mini.get("contract_guarantee_equip").isinitData==1){
		var grid_new_guarantee_equip_data = mini.get("contract_guarantee_equip").getData();
		mini.get("id_json_contract_guarantee_equip_str").setValue(mini.encode(grid_new_guarantee_equip_data));
	}	 
	if(mini.get("contract_supplier").isinitData==1){
		var grid_new_contract_supplier_data= mini.get("contract_supplier").getData();
		mini.get("id_json_contract_supplier_str").setValue(mini.encode(grid_new_contract_supplier_data));
	}
}
function submitCheck(){
	var grid_new_equip_data = mini.get("contract_equip").getData();
	if(grid_new_equip_data.length < 0){
		alert("现租赁物明细不能为空！");
		return false;
	}else{
		return true;
	}
}
</script>