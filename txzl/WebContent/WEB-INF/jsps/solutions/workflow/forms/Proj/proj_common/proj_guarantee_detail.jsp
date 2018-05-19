<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "proj_guarantee_detail",
		renderTo: "id_table_proj_guarantee_detail",
		width: globalClientWidth - 10,
		height: 365,
		lazyLoad: true,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_proj_guarantee_detail_str ? "[]" : json_proj_guarantee_detail_str }'),
		columns: [
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'assurorname', header:'担保单位',
				     renderer : function(e){
					              var assurorname = e.record.assurorname;
					              var assurorId = e.record.assuror;
					              var assurorcustclass=e.record.assurorcustclass;
					               return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + assurorId +"\",\""+assurorcustclass+ "\")'>" + assurorname + "</a>";},
				     formEditorConfig:{
				 	     fieldVisible:false,
				    fillFromFieldName:'assuror',
					     fillProperty:'name'}},
			{field:'assurorcustclass', header:'担保单位客户类型',visible: false,
					 formEditorConfig:{
					     fieldVisible:false,
					fillFromFieldName:'assuror',
					     fillProperty:'custclass'}},
			{field:'assuror', header:'担保单位', visible:false,
				    formEditorConfig:{
				             newLine:true,
				                type:'queryinput',
				            required:true,
				           textField:'name',
				          valueField:'value',
				          allowInput:false,
				             colspan:3,
				               width:'100%',
				        fieldVisible:true,
				              params:{cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'},
				            onSelect:function($me,inputObj, rowdata){mini.getbyName("assurorcustclass").setValue(rowdata.custclass);}}},
			{field:'assuremethodname',header:'担保类型', 
				    formEditorConfig:{
				        fieldVisible:false,
				   fillFromFieldName:'assuremethod',
				        fillProperty:'name'}},
			{field:'assuremethod',header:'担保类型',visible:false,
					formEditorConfig:{
				             newLine:true,
				                type:'combobox',
				            required:true,
				           textField:'name',
				          valueField:'value',
				        fieldVisible:true,
				              params:{dictid:'assure_method',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'}}},
			{field:'assurerelationname',header:'关系', 
				    formEditorConfig:{
				        fieldVisible:false,
				   fillFromFieldName:'assurerelation',
				        fillProperty:'name'}},
			{field:'assurerelation',header:'关系',visible:false,
					formEditorConfig:{
				             newLine:false,
				                type:'combobox',
				            required:true,
				           textField:'name',
				          valueField:'value',
				        fieldVisible:true,
				              params:{dictid:'relation',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'}}},
           {field:'mainguaranteeflag',header:'是否主担保人',visible:true,headerAlign:'center',width:100,allowSort:true,
            formEditorConfig:{
            	newLine:true,
                    width:200,
             showNullItem:"true", 
             nullItemText:"",
                emptyText:"",
                     type:'combobox',
               valueField:'text',
                textField:'text',
                     data:[{text:'是'},{text:'否'}]}},
			{field:'cgmnote',header:'备注',
				    formEditorConfig:{
					         newLine:true,
					       maxLength:500,
					         colspan:3,
					           width:'100%',
					          height:70,
					            type:'textarea'
				}
			}
		]
	});
	});
});
</script>
<div id="id_table_proj_guarantee_detail"></div>
