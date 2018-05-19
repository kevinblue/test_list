<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input name="lawnum" id="lawnum" type="hidden"  value="${requestScope['lawnum'] }"/>
<script type="text/javascript">
 
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "law_cost_detail",
			renderTo: "id_table_proj_equip_detail",
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
			tools: ['add', '-', 'edit', '-','remove'],
			data: JsonUtil.decode('${empty json_law_cost_detail_str ? "[]" : json_law_cost_detail_str }'),
			afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					var lawnum='${param.lawnum}';
					mini.getbyName("lawnum").setValue(lawnum);
				}
			},
			columns: [
				{type:'indexcolumn',width:'9'},
				{type:'checkcolumn',width:'9'},
				{field:'id',header:'id',visible: false},
				{field:'lawnum',header:'lawnum',visible: false},
				{field:'costtypename', header:'费用类型', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'costtype',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
		         {field:'costtype', header:'费用类型', visible:false,dataType:"currency",align:'left',summary:true,
					  formEditorConfig:{
				                  type:'combobox',
				              required:true,
				             textField:'name',
				            valueField:'value',
						     vtype: 'float',
				          fieldVisible:true,
				                params:{pid: 'lawtype', xmlFileName:'combos/comboDict.xml'}}},
				{field:'lawdate', header:'日期',
					  formEditorConfig:{
						      required: true,
						    labelWidth:100,
						     maxLength:120,
						     type:'date',
			                 vtype:'date',
			                format:'yyyy-MM-dd',
						         width: '100%'}},
		         {field:'lawmoney', header:'金额',dataType:"currency",align:'left',summary:true,
					  formEditorConfig:{
						      required: true,
						    labelWidth:100,
						    newLine:true,
						     maxLength:50,
						     vtype: 'float',
						         width: '100%'}},
		         {field:'memo', header:'备注',
					  formEditorConfig:{
						       newLine: true,
						      required: true,
						     maxLength:120,
						     colspan:3,
						     type: 'textarea',
						         width: '100%'}}
			]
		});
	});
});
 
</script>
<div id="id_table_proj_equip_detail"></div>