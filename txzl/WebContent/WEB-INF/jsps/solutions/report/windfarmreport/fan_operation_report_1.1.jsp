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
	//机组运行数据
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		 new ApTable({
				id: "unit_operation_data",
				renderTo: "id_table_unit_operation_data",
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
					reportdate:reportdate,
					selectedprojid:selectedprojid
					
				},
				virtualScroll:true,
				xmlFileName : '/eleasing/workflow/proj/proj_credit/typhoon_operation_list1_1.xml',
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
					
					
					{field:'windspeed',align:'center',
						header:'平均风速（n/s）',
						visible: true,
						formEditorConfig:{
							fieldVisible: true,
						}
					},
					
					{field:'power',header:'发电量（统计时段内）（kVh）',align:'center',summary:true,
						  formEditorConfig:{
							      required:true,
							    labelWidth:100,
							     maxLength:120,
							       colspan:3,
							         width:'100%'}},
					{field:'electricdate', header:'等效利用小时数（h）',align:'center',summary:true,
						  formEditorConfig:{
							       newLine: true,
							      required: true,
							    labelWidth:100,
							     maxLength:120,
							       colspan: 3,
							         width: '100%'}},
					{field:'dateavailability', header:'机组可利用率（%）',align:'center'}
				]
			});
	});
	
});

</script>
<div id="id_table_unit_operation_data" style="width:100%;height:100%;"></div>