<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'person_experience',
			showToolbar  :showTools,
			width:globalClientWidth-20,
			height:200,
			title:"从业经验",
			showToolbar  :showTools,
			renderTo:'table_id_person_experience',
			hiddenQueryArea:true,
			editFormPopupWindowWidth:700,
			data: JsonUtil.decode('<mini:param  name="proj_report_person_experience_str" defaultValue="[]"/>'),         
			remoteOper:false,
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[ 
               {type: 'indexcolumn'},
               {type: 'checkcolumn'},
               {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
					   formEditorConfig:{
							  readOnly:true,
						  fieldVisible:false}},
			  {field:'job',header:'从事职业',headerAlign:'center',width:100,
				        formEditorConfig:{
							   newLine:true,
								  type:'text',
						  fieldVisible:true,
							    colspan:3,
								 width:200,
							labelWidth:100}},
			  {field:'startdate',header:'开始时间',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
				       formEditorConfig:{
				                newLine:true,
				                   type: 'date',
					              vtype: 'date',
					             format: 'yyyy-MM-dd',
					      fieldVisible:true,
					             width:200,
					        labelWidth:100}},
			  {field:'enddate',header:'结束时间',headerAlign:'center',width:100,allowSort:true,
				      formEditorConfig:{
				                   type: 'date',
				                  vtype: 'date',
				                 format: 'yyyy-MM-dd',
					      fieldVisible:true,
					             width:200,
					        labelWidth:100}},
			  {field:'instrumentperson',header:'证明人',headerAlign:'center',width:100,allowSort:true,
				      formEditorConfig:{
				               newLine:true, 
					              type:'text',
					      fieldVisible:true,
					             width:200,
					        labelWidth:100}},
			{field:'phone',header:'联系电话',headerAlign:'center',width:100,allowSort:true,
				     formEditorConfig:{
					             type:'text',
					     fieldVisible:true,
					            width:200,
					       labelWidth:100}},
			 {field:'remark',header:'备注',headerAlign:'center',width:100,allowSort:true,
			 	     formEditorConfig:{
					           newLine:true, 
					               type: 'textarea',
					       fieldVisible: true,
					             height:70,
					              width: '100%',
					            colspan:3,
					         labelWidth:100
				}
			}]
		});
	});
});
</script>
<div id='table_id_person_experience'></div>
