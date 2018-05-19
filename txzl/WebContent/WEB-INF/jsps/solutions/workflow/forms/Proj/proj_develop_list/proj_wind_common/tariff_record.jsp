<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script>
var projname = mini.getbyName("projname");
jQuery(function(){
	var showTools = true;
	tenwa.createTable({
		id: "proj_tariff_record",
		renderTo: "id_table_tariff_record",
		width: '100%',
		height: 200,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: true,
		multiSelect: true,
		showToolbar: showTools,
		removeOperCallBack: function(miniTable,rowDatas){
			dropCreateFile(rowDatas);
			return true;
		}, 
		xmlFileName : '/eleasing/workflow/proj/proj_wind/proj_tariff_record.xml',
		params : {
			projname:projname,
			projid:projid
		},
		columns: [
			{type: 'indexcolumn'},
			{type:'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
/* 			{field: 'filename', header: '项目类别'},
			{field: 'filename', header: '区域类型'}, */
			{field: 'projkindname', header: '项目类型'},
	/* 		{field: 'filename', header: '资源区'}, */
			{field: 'filename', header: '省市' ,width: 150,
				renderer: function(e){
			   		 var resStr = e.record.provincename+"-"+e.record.cityname;
			   		return resStr;
				}
			
			},
			{field: 'createdate', header: '核准时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
			{field: 'benchmarkprice', header: '标杆电价（元/度）'},
			{field: 'internetprice', header: '上网电价（元/度）'}
		]
	});
});
</script>
<div id="id_table_tariff_record"></div>