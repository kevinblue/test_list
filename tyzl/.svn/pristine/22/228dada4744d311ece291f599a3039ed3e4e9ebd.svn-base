<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
jQuery(function(){
	tenwa.createTable({
		id: "old_contract_guarantee_equip",
		renderTo: "id_table_old_contract_guarantee_equip",
		width: globalClientWidth-30,
		height: 250,
		lazyLoad: true,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: false,
		data: JsonUtil.decode('<mini:param  name="json_old_contract_guarantee_equip_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'equipname', header: '抵押物名称',formEditorConfig:{newLine: true}},
			{field: 'guarantorname', header: '抵押人'},
			{field: 'equipinvoice', header: '发票号'},
			{field: 'guarantyvalue', header: '抵质押物价值'},
			{field: 'presentvalue', header: '现值'},
			{field: 'equipguaranteetypename', header: '抵质押方式'},
			{field: 'notaryflagname', header: '是否抵质押公证'},
			{field: 'recordmech', header: '登记机关'},
			{field: 'purchaselife', header: '购买年限'},
			{field: 'cgenote', header: '备注'}
		]
	});
});
</script>
<div id="id_table_contract_guarantee_equip">
	<table cellspacing="0" cellpadding="0">
	    <tr>
	       <td class="x-panel-table-div-title">原抵押物信息</td>
	    </tr>
	    <tr>
	       <td> <div id="id_table_old_contract_guarantee_equip"></div></td>
	    </tr>
	</table>
   
    <table cellspacing="0" cellpadding="0" width="100%" >
	    <tr>
	       <td class="x-panel-table-div-title">现抵押物信息</td>
	    </tr>
	    <tr>
	       <td> <jsp:include page="../../contract_comm/contract_guarantee_equip.jsp" ></jsp:include></td>
	    </tr>
	 </table>
</div>