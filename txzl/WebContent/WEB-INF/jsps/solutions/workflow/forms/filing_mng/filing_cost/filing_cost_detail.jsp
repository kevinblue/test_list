<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input name="lawnum" id="lawnum" type="hidden"  value="${requestScope['lawnum'] }"/>
<script type="text/javascript">
 
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "filing_cost_detail",
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
			data: JsonUtil.decode('${empty json_filing_cost_detail_str ? "[]" : json_filing_cost_detail_str }'),
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
				{field:'filinggact', header:'受理法院',
							  formEditorConfig:{
						                  type:'text',
						              required:true,
						          fieldVisible:true,
						          newLine:true,
						          width: '100%'}},
				{field:'filingjudge', header:'主办法官', 
							  formEditorConfig:{
						                  type:'text',
						          fieldVisible:true,
						          width: '100%'}},
		         {field:'filingno', header:'案号',
					  formEditorConfig:{
				                  type:'text',
				                  newLine:true,
				          fieldVisible:true,
				          width: '100%'}},
				{field:'filingdate', header:'立案日期',
					  formEditorConfig:{
						      required: true,
						    labelWidth:100,
						     maxLength:120,
						     type:'date',
			                 vtype:'date',
			                format:'yyyy-MM-dd',
						         width: '100%'}},
		         {field:'filingtel', header:'联系方式',
					  formEditorConfig:{
						      required: true,
						    labelWidth:100,
						    newLine:true,
						     maxLength:50,
						     type: 'text',
						         width: '100%'}},
		         {field:'filingreq', header:'诉讼请求',
					  formEditorConfig:{
						       newLine: true,
						      required: true,
						     maxLength:120,
						     colspan:3,
						     type: 'textarea',
						         width: '100%'}},
				{field:'filingreason', header:'延迟立案原因',
					  formEditorConfig:{
							newLine: true,
							maxLength:120,
							colspan:3,
							type: 'textarea',
							width: '100%',
							fieldVisible:false}}
			]
		});
	});
});
 
</script>
<div id="id_table_proj_equip_detail"></div>