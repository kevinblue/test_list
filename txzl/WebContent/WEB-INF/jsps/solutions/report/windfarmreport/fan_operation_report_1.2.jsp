<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 var tabledate=info.tabledate;
	 if(""!=tabledate){
	    var grid=mini.get(tableid);
        grid.set({data:mini.decode(tabledate)});
	 }
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
}
jQuery(function(){
	//机组故障数据
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		 new ApTable({
				id: "unit_failure_data",
				renderTo: "id_table_unit_failure_data",
				width: globalClientWidth - 30,
				height: 450,
				lazyLoad: false,
				isClickLoad:false,
				showPager: true,
				multiSelect: true,
				importTargetClass:'',//导入EXCEL对应的类
				importOrExPortCallBack:'',//导入回调方法
				importHeaderIndex:'3',//标题行
				importDataIndex:'4',//数据行
				showToolbar: false,
				params:{
					selectedprojid:selectedprojid,
					reportdate:reportdate
					
				},
				virtualScroll:true,
				xmlFileName : '/eleasing/workflow/proj/proj_credit/typhoon_operation_list1_2.xml',
				columns: [
					{type:'indexcolumn'},
					{field:'id',header:'id',visible: false},
					{field:'num',align:'center',
						header:'编号',
						visible: true,
						formEditorConfig:{
							
						}
					},
					
					{field:'type',align:'center',
						header:'机型',
						visible: true,
						formEditorConfig:{
							
						}
					},
					
					
					{field:'faultnums',align:'center',summary:true,
						header:'故障次数',
						visible: true,
						formEditorConfig:{
							fieldVisible: false,
						}
					},
					
					{field:'faultdate',header:'故障停机总时长（h）',align:'center',summary:true,
						  formEditorConfig:{
							      required:true,
							    labelWidth:100,
							     maxLength:120,
							       colspan:3,
							         width:'100%'}},
					{field:'mtbf', header:'平均无故障时间（h）',align:'center',summary:true,
						  formEditorConfig:{
							       newLine: true,
							      required: true,
							    labelWidth:100,
							     maxLength:120,
							       colspan: 3,
							         width: '100%'}},
					{field:'mttr', header:'故障排除时间（h）',align:'center',summary:true,
						  formEditorConfig:{
						           newLine:true,
						              type:'text',
						             vtype:'float',
						      defaultValue:1,
						           }}
				]
			});
	});
	
});

</script>
<div id="id_table_unit_failure_data" style="width:100%;height:100%;"></div>