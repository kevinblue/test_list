<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var contract_id="${requestScope['contract_id']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_back_his",
			renderTo: "id_table_contract_back_his",
			width: globalClientWidth - 30,
			height:360,
			//title:'合同归档',
			iconCls:'icon-node',
			multiSelect:true,
			hiddenQueryArea:false,//是否将查询区域折叠起来
			queryButtonColSpan:2,
			queryButtonNewLine:false,
			showPager:false,
			showToolbar: showTools,
			allowCellEdit:showTools,
			allowCellSelect:true,
			 params:{
	            	contract_id:contract_id,
				},
			//data: JsonUtil.decode('${empty json_contract_borrow_str ? "[]" : json_contract_borrow_str }'),
			xmlFileName : 'contract_back/contract_back_his.xml',
			//tools : [ 'add', '-', 'edit', '-','remove'],
			columns:[ 
			    {type:'indexcolumn'},
			    {type : 'checkcolumn'},
			   	{field:'id',header:'id',visible:false},
			   	{field:'docname',header:'档案名称'},
			   	{field:'docsubname',header:'子合同编号'},
				{field:'docnumber',header:'档案编号'},
			   	{field:'borrow',header:'借阅人'},
			 	{field:'borrowingstatus',header:'借阅状态'}, 
			 	{field:'borrowingtype',header:'借阅类型'}, 
			   	{field : 'borrowdate',header : '借阅时间'},
				{field : 'memoborrow',header : '借阅备注'},
				{field:'backman',header:'归还人'},
				{field : 'backdate',header : '归还时间'},
				{field : 'memoback',header : '归还备注'}
			]
		});
	});
});

/* //格式化日期
function onDateFormatRenderer(e){
	var value = e.value;
    if (value) return mini.formatDate(mini.parseDate(value), 'yyyy-MM-dd');
    return "";
} */


</script>
<div id="id_table_contract_back_his"></div>