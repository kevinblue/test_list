<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "evaluation_model_statistics2",
			renderTo: "id_evaluation_model_statistics2",
			width: globalClientWidth - 10,
			height: 500,
			lazyLoad: false,
			isClickLoad:false,
			title: "",
			remoteOper : false,
			showPager: false,
			params :{
				projectid:projectid
		            },
			xmlFileName : '/eleasing/workflow/proj/proj_common/proj_evaluation_model2.xml', 
			columns: [
				{type:'indexcolumn'},
				{field:'listid',header:'年',align:'center'},
				{field:'file1',header:'文件一',align:'center'},
				{field:'file2',header:'文件二',align:'center'},
				{field:'file3',header:'文件三',align:'center'},
				{field:'file4',header:'文件四',align:'center'},
				{field:'file5',header:'文件五',align:'center'},
				{field:'file6',header:'文件六',align:'center'},
				{field:'file7',header:'文件七',align:'center'},
				{field:'file8',header:'文件八',align:'center'}
				
				]
	});});
});
</script>
<div id="id_evaluation_model_statistics2"></div>
