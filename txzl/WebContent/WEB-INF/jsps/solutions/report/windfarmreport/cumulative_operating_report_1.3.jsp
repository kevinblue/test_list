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
			id: "cumulative_operating_report3",
			renderTo: "id_table_cumulative_operating3",
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
			xmlFileName : '/eleasing/workflow/proj/proj_credit/cumulative_operating_list1_3.xml',
			columns: [
				{type:'indexcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'reportdate',align:'center',
					header:'月份',
					visible: true,
					formEditorConfig:{
						
					}
				},
				{field:'maintainloss',align:'center',summary:true,
					header:'维护损失电量（kVh）',
					visible: true,
					formEditorConfig:{
						fieldVisible: false,
					}
				},
				
				{field:'limitloss',header:'限功率损失电量（kVh）',align:'center',summary:true,
					  formEditorConfig:{}},
				{field:'faultloss', header:'故障损失电量（kVh）',align:'center',summary:true,
					  formEditorConfig:{}},
				{field:'totalloss', header:'损失电量合计（kVh）',align:'center',summary:true,
					  formEditorConfig:{  }}
			]
		});
	});
});

</script>

<div id="id_table_cumulative_operating3" style="width:100%;height:100%;"></div>