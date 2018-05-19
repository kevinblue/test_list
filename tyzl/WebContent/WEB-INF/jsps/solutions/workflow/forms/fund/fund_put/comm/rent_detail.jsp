<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	tenwa.createTable({
		id: "rent_detail",
		renderTo: "id_table_rent_detail",
		width : globalClientWidth-30,
		editFormPopupWindowWidth : 600,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: true,
		multiSelect: true,
		data: JsonUtil.decode('<mini:param  name="json_rent_detail_str" defaultValue="[]"/>'),
		tools: ['exportExcel'],
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'rentlist', header: '期次'},
			{field: 'plandate', header: '计划日期'},
			{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
			{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
			{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"}
		]
	});
});
</script>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_detail_str" name="json_rent_detail_str" value='<mini:param  name="json_rent_detail_str" defaultValue="[]"/>'></input>
<div id="id_table_rent_detail" style="width: 100%;height: 100%;"></div>