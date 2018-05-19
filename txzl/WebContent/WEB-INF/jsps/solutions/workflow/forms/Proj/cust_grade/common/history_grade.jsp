<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var custid = "${requestScope['custInfoTojsp']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "history_grade",
		renderTo: "id_table_history_grade",
		width: globalClientWidth - 10,
		height: 365,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		params:{"custid":custid},
		xmlFileName : '/eleasing/jsp/cust_info/cust_grade/grade_history_info_list.xml',
		params:{"custid":custid},
		columns: [
			{type : 'indexcolumn'}, 
			{field:'custid', header:'custid',visible:false,headerAlign:'center',align:'center'},
			{field:'allscore', header:'总得分',visible:true,headerAlign:'center',align:'center'},
			{field:'graderesult',header:'评分等级',visible:true,headerAlign:'center',align:'center'},
			{field:'gradetime',header:'评级时间',visible:true,headerAlign:'center',align:'center'}
		         ]
	   });
	});
});
</script>
<div id="id_table_history_grade"></div>
