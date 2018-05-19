<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
	//是否保存
	function workflowSaveCallBack() {
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交
	function workflowSubmitCallBack(buttonText) {
		return true;
	}
	function workflowNextRouteCallBack(buttonText, requestNextRoute) {
	}
</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_products_offer_info_str" name="json_products_offer_info_str" value='${empty json_products_offer_info_str ? "[]" : json_products_offer_info_str }'></input>
<div id="products_offer_form">
	<div id="products_base_info_form" title="现有产品报价">
		<div class="mini-panel" title="现有产品报价" showCollapseButton="true"
			style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0"
				style="width: 100%" id="products_base_info">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">流程编号：</td>
					<td class="td-content" width="38%"><input
						name="products_offer.process_number"
						id="products_offer.process_number" class="mini-textbox"
						label="流程编号：" readOnly type="text"
						value="${requestScope['products_offer.process_number']}"></td>
					<td class="td-content-title">工厂：</td>
					<td class="td-content"><input name="products_offer.factory"
						id="products_offer.factory" class="mini-combobox" label="工厂："
						textField="text" valueField="text"
						data="[{text:'LMC'},{text:'LIC'}]"
						value="${requestScope['products_offer.factory'] }" required="true"></td>
				</tr>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">流转方向：</td>
					<td class="td-content"><input name="products_offer.direction"
						id="products_offer.direction" class="mini-combobox" label="流转方向："
						textField="text" valueField="text"
						data="[{text:'流转财务'},{text:'流转物流经理'}]"
						value="[{text:'流转财务'},{text:'流转物流经理'}]" required="true"></td>
					<td class="td-content-title">生产线：</td>
					<td class="td-content"><input
						name="products_offer.productsline"
						id="products_offer.productsline" class="mini-combobox"
						label="生产线：" textField="text" valueField="text"
						data="[{text:'LMC'},{text:'LIC'}]" value="" required="true"></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">Applicationdate：</td>
					<td class="td-content"><input
						name="products_offer.applicationdate"
						id="products_offer.applicationdate" require="true"
						label="Applicationdate：" class="mini-textbox" readOnly type="text"
						value="" /><font style="color: red;">（系统自动生成）* </font></td>
					<td class="td-content-title">Unit：</td>
					<td class="td-content"><input name="products_offer.unit"
						id="products_offer.unit" class="mini-textbox" label="Unit： "
						type="text" required="true" value="" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">ConditionType：</td>
					<td class="td-content"><input
						name="products_offer.conditiontype"
						id="products_offer.conditiontype" class="mini-textbox"
						label="ConditionType： " type="text" required="true" value="" /></td>
					<td class="td-content-title">SalesOrganization：</td>
					<td class="td-content"><input
						name="products_offer.salesorganization"
						id="products_offer.salesorganization" class="mini-textbox"
						type="text" required="true" value="" /></td>
				</tr>

				<tr class="tr-even">
					<td class="td-content-title">ReferenceNo.</td>
					<td class="td-content"><input
						name="products_offer.referenceno" id="products_offer.referenceno"
						class="mini-textbox" type="text" value="" /></td>
					<td class="td-content-title">Currency：</td>
					<td class="td-content"><input
						name="products_offer.productstype" id="productstype"
						class="mini-combobox" textField="text" valueField="text"
						data="[{text:'USD'},{text:'EUR'},{text:'CNY'}" value=""
						required="true"></td>
				</tr>


				<tr class="tr-even">
					<td class="td-content-title">CustomsTariffNo.：</td>
					<td class="td-content"><input
						name="products_offer.customsTariffNo"
						id="products_offer.customsTariffNo" class="mini-textbox"
						type="text" value="" /></td>
					<td class="td-content-title">Loading Grp：</td>
					<td class="td-content"><input name="products_offer.loading"
						id="products_offer.loading" class="mini-textbox" type="text"
						required="true" value="" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">是否生产：</td>
					<td class="td-content"><input name="products_offer.isproducts"
						id="products_offer.isproducts" class="mini-combobox" label="是否生产："
						textField="text" valueField="text" data="[{text:'是'},{text:'否'}]"
						required="true"></td>
					<td class="td-content-title">LeadTime：</td>
					<td class="td-content"><input name="products_offer.leadtime"
						id="products_offer.leadtime" class="mini-textbox" type="text"
						required="true" value="" /><font style="color: red;">天</font></td>

				</tr>

			</table>
		</div>
	</div>

</div>
<div id="tabDetails" class="mini-tabs" activeIndex="0"
	style="width: 100%;" bodyStyle="border:0px;">
	<div title="以下为多行控件：" name="products_offer_info" iconCls="icon-node">
		<jsp:include page="../products_offer/products_offer_info_detail01.jsp">
			<jsp:param value="true" name="isView" />
		</jsp:include>
	</div>
</div>
<div id="products_offer_people">
	<table cellspacing="0" cellpadding="0"
		style="width: 100%; border: 1px solid #99CCFF;" class="fillTable">

		<tr class="tr-even">
			<td class="td-content-title">Applied by</td>
			<td class="td-content" colspan="3"><input
				id="products_offer.applied" name="products_offer.applied"
				class="mini-textbox" label="Applied by" textField="name"
				valueField="value" dataField="datas" allowInput="true"
				value="${requestScope['products_offer.applied']}"
				text="${requestScope['products_offer.applied']}" required="false">
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title">Confirmation Logistics Dept:</td>
			<td id="logistics" class="td-content"><input
				id="products_offer.logistics" name="products_offer.logistics"
				class="mini-textbox" label="Confirmation Logistics Dept:"
				textField="name" valueField="value" dataField="datas"
				allowInput="false"
				value="${requestScope['products_offer.logistics']}"
				class="mini-textbox" /></td>
			<td class="td-content-title">Confirmation Financial Dept:</td>
			<td id="financial" class="financial"><input
				id="products_offer.financial" name="products_offer.financial"
				class="mini-textbox" style="width: 200px;"
				label="Confirmation Financial Dept:" allowInput="false"
				value="${requestScope['products_offer.financial'] }" readonly>
			</td>
		</tr>
	</table>
</div>

