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
	
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		 new ApTable({
			id: "cumulative_operating_report1",
			renderTo: "id_table_cumulative_operating1",
			width: globalClientWidth - 30,
			height: 380,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : true,
			showPager: true,
			multiSelect: true,
			title:'',
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方法
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			showToolbar: false,
			params:{
				//reportdate:reportdate,
				selectedprojid:selectedprojid
				
			},
			virtualScroll:true,
			xmlFileName : '/eleasing/workflow/proj/proj_credit/cumulative_operating_list1_1.xml',
			columns: [
				{type:'indexcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'reportdate',align:'center',
					header:'月份',
					visible: true,
					formEditorConfig:{
						
					}
				},
				{field:'windspeed',align:'center',
					header:'平均风速（n/s）',
					visible: true,
					formEditorConfig:{
						fieldVisible: false,
					}
				},
				
				{field:'power',header:'发电量（统计时段内）（kVh）',align:'center',summary:true,
					  formEditorConfig:{}},
				{field:'electricdate', header:'等效利用小时数（h）',align:'center',summary:true,
					  formEditorConfig:{}},
				{field:'dateavailability', header:'机组可利用率（%）',align:'center',summary:true,
					  formEditorConfig:{  }}
			]
		});
	});
});

</script>

<div id="id_table_cumulative_operating1" style="width:100%;height:100%;"></div>