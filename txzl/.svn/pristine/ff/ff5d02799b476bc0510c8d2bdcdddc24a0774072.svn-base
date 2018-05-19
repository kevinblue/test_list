<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "rent_penalty_adjust_detail",
		renderTo: "id_table_rent_penalty_adjust_detail",
		width : globalClientWidth-30,
		height : 400,
		title: "",
		remoteOper : false,
		lazyLoad:true,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['edit', '-', 'remove'],
		validateForm : function(miniTable, miniForm, editFormItemOperFlag){
			var penaltyadjust = mini.getbyName("penaltyadjust").getValue();//减免罚息 
			var penaltyoverage = mini.getbyName("penaltyoverage").getValue();//罚息余额
			if(parseFloat(penaltyadjust) > parseFloat(penaltyoverage)){
				mini.alert("减免罚息不能大于罚息余额！");
				return;
			}
			return true;
		},
		data: JsonUtil.decode('${empty json_rent_penalty_adjust_detail_str ? "[]" : json_rent_penalty_adjust_detail_str }'),
		columns: [
			{type: 'checkcolumn',visible: showTools},
			{field: 'id', header: 'id', visible: false},
			{field: 'planid', header: 'planid', visible: false},
			{field: 'planlist', header: '计划期项',type:'number',formEditorConfig:{labelWidth:100,readOnly:true}},
			{field: 'hirelist', header: '回笼期项',formEditorConfig:{readOnly:true}},	
			{field: 'hiredate', header: '减免日期',type:"date",format:"yyyy-MM-dd",formEditorConfig:{
				newLine:true,
				type: 'date',
				vtype: 'date',
				format: 'yyyy-MM-dd',
				allowInput : "false"
			}},
			{field: 'accountingdate', header: '会计处理日',type:"date",format:"yyyy-MM-dd",formEditorConfig:{
				
				type: 'date',
				vtype: 'date',
				format: 'yyyy-MM-dd',
				allowInput : "false"
			}},
			{field: 'balancemodename', header: '结算方式',visible:false},
			{field: 'balancemode', header: '结算方式',visible:false},
			{field: 'rent', header: '回笼租金',visible:false},
			{field: 'corpus', header: '回笼本金',visible:false},
			{field: 'interest', header: '回笼租息',visible:false},
			{field: 'penalty', header: '回笼罚息',visible:false},
			{field: 'rentadjust', header: '租金调整',visible:false},
			{field: 'corpusadjust', header: '本金调整',visible:false},
			{field: 'interestadjust', header: '租息调整',visible:false},			
			{field: 'planpenalty', header: '应收罚息',summary : true,dataType : "currency",formEditorConfig:{newLine:true,vtype:'float',readOnly:true}},
			{field: 'curpenaltyincome', header: '已收罚息',summary : true,dataType : "currency",formEditorConfig:{vtype:'float',readOnly:true}},
			{field: 'curpenaltyadjustincome', header: '已收减免罚息',summary : true,dataType : "currency",formEditorConfig:{newLine:true,vtype:'float',readOnly:true}},
			{field: 'penaltyoverage', header: '罚息余额',summary : true,dataType : "currency",formEditorConfig:{vtype:'float',readOnly:true}},
			{field: 'penaltyadjust', header: '本次减免罚息',summary : true,dataType : "currency",formEditorConfig:{newLine:true,vtype:'float',colspan : 3}},			
			{field: 'memo', header: '备注',formEditorConfig:{colspan : 3,
				width: 400,
				height:70, type: 'textarea',newLine:true}}
		]
	});
});
</script>
<div id="id_table_rent_penalty_adjust_detail" style="width: 100%;height: 100%;"></div>