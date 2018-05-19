<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var contract_id="${requestScope['contract_id']}";
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "contract_borrow",
			renderTo: "id_table_contract_borrow_present",
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
			data: JsonUtil.decode('${empty json_contract_borrow_str ? "[]" : json_contract_borrow_str }'),
			tools : [ 'add', '-', 'edit', '-','remove'],
			columns:[ 
			    {type:'indexcolumn'},
			    {type : 'checkcolumn'},
			   	{field:'id',header:'id',visible:false},
			    {field : 'contractnumberid',
					header : '合同编号',
					visible : false,
					formEditorConfig : {
						fieldVisible : false,
						valueField:contract_id,
						value:contract_id
					}},
			   	{
					field : 'contractfilingidname',
					header : '档案名称',
					visible : true,
					width:130,
					formEditorConfig : {
						fillProperty:'value',
						fieldVisible : false
					}
				},
				{
					field : 'contractfilingid',
					header : '档案名称',
					visible : false,
					formEditorConfig : {
						width : 200,
						type : 'queryinput',
						required : true,
						textField : 'name',
						valueField : 'value',
						allowInput : false,
						fieldVisible : true,
						onSelect : setFilingNumber,
						params : {
							conid : contract_id,
							xmlFileName : '/docadd/contract_filing_search.xml'
						}
					}
				},
			   	
			   	{field:'docsubname',header:'子合同编号'},
				{field:'docnumber',header:'档案编号',formEditorConfig : {readOnly:true,newLine:true}},
			   	{
					field : 'borrow',
					header : '借阅人',
					visible : true,
					formEditorConfig : {
						defaultValue :'${sessionScope.loginUser.realname}',
						newLine : false,
						type : 'text',
						labelWidth : 110,
						width : '100%',
						required : false,
						readonly : false
					}
				},
			 	/* {field:'filestatus',header:'借阅状态',formEditorConfig : {newLine:true,required:true}}, */
			   	{field : 'borrowdate',
					header : '借阅时间',
					headerAlign : 'center',
					visible : true,
					width : 100,
					dateFormat : "yyyy-MM-dd",
					formEditorConfig : {
						newLine : true,
						type : 'date',
						defaultValue : mini.formatDate(new Date(),
								"yyyy-MM-dd"),
						labelWidth : 100,
						width : '100%',
						format : 'yyyy-MM-dd',
					}
				},
				{
					field : 'memoborrow',
					header : '借阅备注',
					formEditorConfig : {
						newLine : true,
						colspan : 3,
						width : "100%",
						type : 'textarea'
					}
				}
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
//选择档案后自动带出档案编号
function setFilingNumber($me, inputObj, rowData) {
	mini.getbyName("contractfilingidname").setValue(rowData.fillingname);
	mini.getbyName("docnumber").setValue(rowData.fillingnumber);
}

</script>
<div id="id_table_contract_borrow_present"></div>