<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:"fund_rent_plan_new",
			renderTo:"id_fund_rent_plan_frame_compare",
			width:globalClientWidth - 30,
			height:400,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper :false,
			showPager:false,
			multiSelect:true,
			showToolbar:showTools,
			data:mini.decode('${empty json_fund_rent_plan_new_str ? "[]" :json_fund_rent_plan_new_str	 }'),			
			columns:[
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'rentlist', header:'期项',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200}},
				{field:'plandate', header:'计划日期',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200}},
				{field:'rent', header:'租金',dataType :"currency",summary :true,align:'right',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200}},
				{field:'corpus', header:'财务本金',dataType :"currency",summary :true,align:'right',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200}},
				{field:'interest', header:'财务利息',dataType :"currency",summary :true,align:'right',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,	
						             width:200}},
				{field:'corpusbusiness', header:'业务本金',dataType :"currency",summary :true,align:'right',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200}},
				{field:'interestbusiness', header:'业务利息',dataType :"currency",summary :true,align:'right',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,	
						             width:200}},
				{field:'yearrate', header:'年利率',
					      formEditorConfig:{
						          required:true,
						        labelWidth:100,
						             width:200
					}
				}
			]
		});
	});
});
</script>
<div id="id_fund_rent_plan_frame_compare"></div>