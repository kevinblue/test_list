<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "old_contract_equip",
		renderTo: "id_table_old_contract_equip_detail",
		width: globalClientWidth-30,
		height: 250,
		title: "",
		lazyLoad: true,
		remoteOper : false,
		showPager: false,
		multiSelect: false,
		showToolbar: false,
		data: JsonUtil.decode('<mini:param  name="json_old_contract_equip_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'equipname', header: '设备名称'},
			{field: 'model', header: '型号/规格'},
			{field: 'equipnum', header: '数量'},
			{field: 'price', header: '单价'},
			{field: 'unit', header: '单位'},
			{field: 'equipprice', header: '交易价格'},
			{field: 'total', header: '设备原值'},
			{field: 'nowtotal', header: '设备现值'},
			{field: 'brand', header: '品牌'},
			{field: 'vndrname', header: '供应商'},
			{field: 'manufacturername', header: '制造商'},
			{field: 'devicetypename', header: '设备类型'},
			{field: 'equipid', header: '设备序列号'},
			{field: 'equipdeliveryplace', header: '交付地点'},
			{field: 'equipdeliverydate', header: '交付时间'},
			{field: 'equipplace', header: '设备设置地址'},
			{field: 'cenote', header: '备注'}
		]
	});});
</script>
	<div id="id_table_contract_equip">
	   <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	       <tr>
	          <td class="x-panel-table-div-title" colspan=4>原租赁物件</td>
	      </tr>
	   </table>
	    <div id="id_table_old_contract_equip_detail" class="fieldset-body"></div>
	   <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	     <tr>
	        <td class="x-panel-table-div-title" colspan=4>现租赁物件</td></tr>
	     </table>
	    <div id="id_table_new_contract_equip_detail" class="fieldset-body">
	        	<jsp:include page="../../contract_comm/contract_equip_detail.jsp" ></jsp:include>
	    </div>
</div>