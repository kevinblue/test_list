<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "payment_premise_condition",
			renderTo: "id_payment_premise_condition",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showToolbar: showTools,
			showPager: false,
			multiSelect: true,
			data: mini.decode('${empty json_payment_premise_condition_str ? "[]" : json_payment_premise_condition_str}'),
			tools : [ 'edit', '-','remove'],
			addOperCallBack : function (miniTable,rowData){
				$('#id_json_payment_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			removeOperCallBack:function(miniTable){
				$('#id_json_payment_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			copyOperCallBack:function(miniTable){
				$('#id_json_payment_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_payment_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			columns:[
							{type: 'indexcolumn'},
							{type: 'checkcolumn'},
							{field: 'id', header: 'id', visible: false},
							{field: 'docid', header: 'docid', visible: false},
							{field: 'projnumber', header: '项目编号',
								formEditorConfig:
								{
									readOnly:true,
									required: true,
									labelWidth:100,
									width: 200
								}
							},
							{field: 'paymentid', header: '付款节点', visible: true,
								formEditorConfig:
									{   
										readOnly:true,
										required: true,
										labelWidth:100,
										width: 200
									}	
							},
 							{field: 'paymentnode', header: '付款节点编号',
 											formEditorConfig:
 											{   
 												newLine : true,
 												readOnly:true,
 												required: true,
 												labelWidth:100,
 												width: 200
 											}
 										},
 							{field: 'feetype', header: '款项名称ID',visible: false},			
 							{field: 'feetypename', header: '款项名称',
 											formEditorConfig:
 											{   readOnly:true,
 												required: true,
 												labelWidth:100,
 												width: 200
 											}
 										},
 							   {field:'conditionname',header:'前提条件',  								
 			  								headerAlign:'center',
 			  								 width:150,
 			  								 allowSort:true,
 			  							    formEditorConfig:{
 			  							    	readOnly:true,
 			  							    	newLine:true,
 										         type:'text',								
 										     	width:200,
 										     
 			 							           }},
 			 							         {field:'devicenamename', header:'设备名称', 
  			 									      formEditorConfig:{
  			 									          fieldVisible:false}
  			 							         },
  			 									{field: 'devicename', header: '设备名称', visible:false,
  			 										  formEditorConfig:{fieldVisible:false}
  			 									},
 			 			    {field: 'remark', header: '备注',
 			 											formEditorConfig:
 			 											{
 			 												type:'textarea',
 			 												newLine:true,
 			 												labelWidth:100,
 			 												height:70,
 			 												colspan:2,
 			 												width: 278
 			 											}
 			 										}				           
					]
		
		});
	});
});
</script>
<div id="id_payment_premise_condition"></div>