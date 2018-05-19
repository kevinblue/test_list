<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "eleasing_company",
			renderTo: "id_table_eleasing_company",
			width: globalClientWidth - 20,
			height: 200,
			lazyLoad: false,
			title: "金融机构往来—租赁公司",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['add', '-', 'edit', '-', 'remove'],
			data: JsonUtil.decode('<mini:param  name="proj_report_eleasing_company" defaultValue="[]"/>'),
	    	columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header: 'id', visible: false},
				{field:'contractid', header: '合同号',formEditorConfig:{labelwidth:150}},
				{field:'custname', header: '承租人',visible:true,formEditorConfig:{labelwidth:150,required: true}},
				{field:'debtorg', header: '借贷机构',formEditorConfig:{newLine : true}},
				{field:'remaindebt', header: '设备金额',formEditorConfig:{vtype:'float'}},
				{field:'monthreturn', header: '月还款',formEditorConfig:{vtype:'float',newLine : true}},
				{field:'moneyremain', header: '租金余额',formEditorConfig:{vtype:'float'}},
				{field:'guarantee', header:'担保人',formEditorConfig:{newLine:true}},
				{field:'lastdate', header: '最后还款时间',formEditorConfig:{type: 'date',required: true,format: 'yyyy-MM-dd'}},
				{field:'alreadynum', header: '已付期数',formEditorConfig:{newLine:true,vtype:'int'}},
				{field:'endlist', header: '到期期数',formEditorConfig:{vtype:'int'}},
				{field:'ovenum', header: '逾期期数',formEditorConfig:{newLine:true,vtype:'int'}},
				{field:'eleasenum', header: '融资总期数',formEditorConfig:{vtype:'int'}},
				{field:'remark', header: '备注',formEditorConfig:{type:'textarea',newLine:true,width:477,height:70,colspan:3}}
			]	
		});
	});
});

</script>

<div id="id_table_eleasing_company"></div>


