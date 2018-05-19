<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
 
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "json_law_cost_his_detail",
			renderTo: "id_table_proj_equip_detail2",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			title:'',
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方法
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			showToolbar: showTools,
			virtualScroll:true,
			data: JsonUtil.decode('${empty json_filing_cost_his_detail_str ? "[]" : json_filing_cost_his_detail_str }'),
			columns: [
				{type:'indexcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'lawnum',header:'lawnum',visible: false},
				{field:'filinggact', header:'采理法院'},
		         {field:'filingjudge', header:'主办法官'},
		         {field:'filingno', header:'案号'},
		         {field:'filingtel', header:'联系方式'},
		         {field:'filingreq', header:'诉讼请求'},
		         {field:'filingreason', header:'延迟立案原因'},
				{field:'filingdate', header:'立案日期',format:'yyyy-MM-dd'},
		         {field:'filingreason', header:'延迟立案原因'}
			]
		});
	});
});
 
</script>
<div id="id_table_proj_equip_detail2"></div>