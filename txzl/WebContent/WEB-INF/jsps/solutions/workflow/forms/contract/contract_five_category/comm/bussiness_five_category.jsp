<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="bussiness_five_category_form" title="五级分类">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width='12%'>五级分类结果：</td>
			<td class="td-content" width='38%'>
				<input id="five_category.contractfive_business" 
				label="五级分类结果："
					name="five_category.contractfive_business" 
					class="mini-combobox" 
					required="true"
					textField="name"
					valueField="value"  
				    dataField="datas"
				    allowInput="false" 
				    data-options="{_params:{pid:'five_class'}}" 
				    onbeforeshowpopup="onbeforeshowpopup"
					value="${requestScope['five_category.contractfive_business'] }"
					text="${requestScope['rawValue_five_category.contractfive_business']}"
					onvaluechanged="comboboxChanged"
				/>
				<input label="合同五级分类："  id="rawValue_five_category.contractfive_business" name="rawValue_five_category.contractfive_business" value="${requestScope['rawValue_five_category.contractfive_business']}" class="mini-textbox" style="display:none"/>
				
			</td>
			<td class="td-content-title" width='12%'>五级分类日期：</td>
			<td class="td-content" width='38%'>
				<input label="五级分类日期：" id="five_category.contractfivedate_business" name="five_category.contractfivedate_business" class="mini-datepicker" required="true" allowInput="false" value="${requestScope['five_category.contractfivedate_business'] }"/>
			</td>
		</tr>
		<!-- 质押股权总价，质押股权比例，股权质押评估值，电费质押评估值，设备抵押评估值，其他抵质押物评估值
		five_category.pledgesumprice,
		five_category.pledgeratio,
		five_category.pledgeratingvalue,
		five_category.electricratingvalue,
		five_category.equipratingvalue,
		five_category.otherpledgeratingvalue
		 -->
		<tr class="tr-project-info tr-even">
             <td class="td-content-title" width="12%">融资余额：</td>
             <td class="td-content" width="38%">
             	<input name="five_category.financingbalance" id ="financing_balance"  class="mini-textbox" label="融资余额"  type="text" 
             			allowInput="false" value="${requestScope['five_category.financingbalance']}">
             </td>
	    </tr>
		<tr class="tr-even">
		<td class="td-content-title" width="12%">质押股权总价：</td>
	             <td class="td-content" width="38%"><input name="five_category.pledgesumprice" id ="five_category.pledgesumprice" vtype="float"  class="mini-textbox" label="质押股权总价" required=true type="text" value="${requestScope['five_category.pledgesumprice']}"></td>
		<td class="td-content-title" width="12%">质押股权比例(%)：</td>
	             <td class="td-content" width="38%"><input name="five_category.pledgeratio" id ="risk_name"  vtype="float" class="mini-textbox" label="质押股权比例" required=true type="text" value="${requestScope['five_category.pledgeratio']}"></td>
		</tr >
				
		<tr class="tr-even">
		
		<td class="td-content-title" width="12%">股权质押评估值：</td>
	             <td class="td-content" width="38%"><input name="five_category.pledgeratingvalue" id ="five_category.pledgeratingvalue" vtype="float" class="mini-textbox" label="股权质押评估值" required=true type="text" value="${requestScope['five_category.pledgeratingvalue']}"></td>
		<td class="td-content-title" width="12%">电费质押评估值：</td>
	             <td class="td-content" width="38%"><input name="five_category.electricratingvalue" id ="five_category.electricratingvalue" vtype="float" class="mini-textbox" label="股权质押评估值" required=true type="text" value="${requestScope['five_category.electricratingvalue']}"></td>
		</tr >
		
		<tr class="tr-even">
		
		<td class="td-content-title" width="12%">设备抵押评估值：</td>
	             <td class="td-content" width="38%"><input name="five_category.equipratingvalue" id ="five_category.equipratingvalue" vtype="float"  class="mini-textbox" label="设备抵押评估值" required=true type="text" value="${requestScope['five_category.equipratingvalue']}"></td>
		<td class="td-content-title" width="12%">其他抵质押物评估值：</td>
	             <td class="td-content" width="38%"><input name="five_category.otherpledgeratingvalue" id ="five_category.otherpledgeratingvalue" vtype="float"  class="mini-textbox" label="其他抵质押物评估值" required=true type="text" value="${requestScope['five_category.otherpledgeratingvalue']}"></td>
		</tr >
		<%-- <tr class="tr-even">
		  <td class="td-content-title" width="12%" >流程编号：</td>
	             <td class="td-content" width="38%"><input name="five_category.docid" id ="five_category.docid" style="display:none" class="mini-textbox" label="其他抵质押物评估值" required=true type="text" value="${requestScope['five_category.docid']}"></td>
		</tr> --%>
		<tr class="tr-even">
			<td class="td-content-title">五级分类说明：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input id="five_category.explain_business" style="width: 73.5%;height: 100px;" name="five_category.explain_business" required="true" class="mini-textarea" label="五级分类说明：" value="${requestScope['five_category.explain_business'] }"/>
			   <td class="td-content" width="38%"><input name="five_category.docid" id ="five_category.docid" style="display:none" class="mini-textbox" label="流程编号"  type="text" value="${requestScope['five_category.docid']}"></td>
			</td>
		</tr>
		<!-- badinfluenceofasset,treatmentofsameasset,classfyreason,expectreason,assetmeasurement -->
		<tr class="tr-even">
			<td class="td-content-title">对资产有不利影响的因素 </td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input label="对资产有不利影响的因素" id="five_category.badinfluenceofasset" style="width: 73.5%;height: 100px;" name="five_category.badinfluenceofasset" required="true" class="mini-textarea" value="${requestScope['five_category.badinfluenceofasset'] }"/>
			  
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">同类资产处置情况 ：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input label="同类资产处置情况 ：" id="five_category.treatmentofsameasset" style="width: 73.5%;height: 100px;" name="five_category.treatmentofsameasset" required="true" class="mini-textarea" value="${requestScope['five_category.treatmentofsameasset'] }"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">租赁资产分类理由  ：</td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input label="租赁资产分类理由  ：" id="five_category.classfyreason" style="width: 73.5%;height: 100px;" name="five_category.classfyreason" required="true" class="mini-textarea" value="${requestScope['five_category.classfyreason'] }"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">租赁资产分类展望及理由 </td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input label="租赁资产分类展望及理由" id="five_category.expectreason" style="width: 73.5%;height: 100px;" name="five_category.expectreason" required="true" class="mini-textarea" value="${requestScope['five_category.expectreason'] }"/>
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">建议租赁资产处置措施 </td>
			<td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
				<input label="建议租赁资产处置措施 " id="five_category.assetmeasurement" style="width: 73.5%;height: 100px;" name="five_category.assetmeasurement" required="true" class="mini-textarea" value="${requestScope['five_category.assetmeasurement'] }"/>
			</td>
		</tr>		
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("bussiness_five_category_form");};
});
jQuery(function(){
	var flowid=flowUnid;
	mini.get("five_category.docid").setValue(flowid);
});
jQuery(function(){
	mini.get("five_category.pledgesumprice").setValue(formatNumberThousand(mini.get("five_category.pledgesumprice").getValue()));
	mini.get("five_category.pledgeratingvalue").setValue(formatNumberThousand(mini.get("five_category.pledgeratingvalue").getValue()));
	mini.get("five_category.electricratingvalue").setValue(formatNumberThousand(mini.get("five_category.electricratingvalue").getValue()));
	mini.get("five_category.equipratingvalue").setValue(formatNumberThousand(mini.get("five_category.equipratingvalue").getValue()));
	mini.get("five_category.otherpledgeratingvalue").setValue(formatNumberThousand(mini.get("five_category.otherpledgeratingvalue").getValue()));
	mini.get("financing_balance").setValue(formatNumberThousand(mini.get("financing_balance").getValue()));
}); 
function formatNumberThousand(s) {  
	   s += "";
	   s = s.replace(/,/g,"");
	   if(isNaN(s)){
		   return s;
	   }
	   //if(s==0){s="0.00";}
	   s += "";
	   //s = s.replace(/,/g,"");
	   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g;
	   var n1=s.replace(re,"$1,");
	   return n1;
}

</script>