<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">

jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_borrow",
			renderTo: "id_table_contract_borrow",
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
			oncellbeginedit:"OnCellBeginEdit",
			ondrawcell:'OnDrawCell',
			data: JsonUtil.decode('${empty json_contract_borrow_str ? "[]" : json_contract_borrow_str }'),
			columns:[ 
			    {type:'indexcolumn'},
			   	{field:'id',header:'id',visible:false},
			   	{field:'docname',header:'合同档案名称'},
			   	{field:'filestatus',header:'文档状态'},
				{field:'ischeck',header:'是否借出',type:'checkboxcolumn',trueValue:"1", falseValue:"0"},
			   	{field:'memoborrow',header:'备注(说明原因及替代方式)',
					editor:{ 
						width:'100%', 
						type:'textarea', 
						onenter:function(){
							mini.get("table_id1").commitEdit();
                    	}
				    }	
			   	},
			   	{field:'prebackdate',header:'预计归还日期',renderer:"onDateFormatRenderer",
			   		editor:{ 
						width:'100%', 
						type:'datepicker',
						//format : 'yyyy-MM-dd',
						//showTime : true,
						allowInput:false,
						onenter:function(){
							mini.get("table_id1").commitEdit();
                    	}
				    }
			   	}
			]
		});
	});
});

//格式化日期
function onDateFormatRenderer(e){
	var value = e.value;
    if (value) return mini.formatDate(mini.parseDate(value), 'yyyy-MM-dd');
    return "";
}

function OnCellBeginEdit(e) {
    var record = e.record, field = e.field;
    if (field == "ischeck" && record.filestatus == "已借出") {
        e.cancel = true;    //如果已借出，则不允许编辑
    }
    if (field == "memoborrow" && record.filestatus == "已借出") {
       e.cancel = true;    //如果已借出，则不允许编辑
    }
    if (field == "prebackdate" && record.filestatus == "已借出") {
        e.cancel = true;    //如果已借出，则不允许编辑
    }
    //如果没有勾选是否借出 不允许填写备注
    if (field == "memoborrow" && record.ischeck == "") {
       e.cancel = true;   
    }
    if (field == "prebackdate" && record.ischeck == "") {
        e.cancel = true;   
     }
}
function OnDrawCell(e){
    var record = e.record,
    column = e.column,
    field = e.field,
    value = e.value;
    
    if (field == "ischeck"  && record.filestatus == "已借出"){
    	e.cellStyle = "background-color:#efefef";
    }
    
    if (field == "memoborrow"  && record.filestatus == "已借出"){
    	e.cellStyle = "background-color:#efefef";
    }
    
    if (field == "prebackdate"  && record.filestatus == "已借出"){
    	e.cellStyle = "background-color:#efefef";
    }
}
</script>
<div id="id_table_contract_borrow"></div>