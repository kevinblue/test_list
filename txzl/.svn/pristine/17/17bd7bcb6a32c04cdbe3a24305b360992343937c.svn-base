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
			id: "cumulative_operating_report_year2",
			renderTo: "id_table_cumulative_operating2",
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
				reportdate:reportdate,
				selectedprojid:selectedprojid,
			},
			virtualScroll:true,
			xmlFileName : '/eleasing/workflow/proj/proj_credit/cumulative_operating_list_year1_2.xml',
			columns: [
				{type:'indexcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'projectname',align:'center',
					header:'项目名称',
					visible: true,
					formEditorConfig:{
					}
				},
				{field:'fansumandtype',align:'center',
					header:'机型-数量',
					visible: false,
					formEditorConfig:{
						fieldVisible: false,
					}
				},
				
				{field:'faultnums',header:'故障次数',align:'center',
					  formEditorConfig:{}},
				{field:'faultdate', header:'故障停机总时长（h）',align:'center',
					  formEditorConfig:{}},
					  {field:'mtbf', header:'平均无故障时间（h）',align:'center',
						  formEditorConfig:{}},
					  
				{field:'mttr', header:'故障排除时间（h）',align:'center',summary:true,
					  formEditorConfig:{  }}
			]
		});
	});
});

</script>

<div id="id_table_cumulative_operating2" style="width:100%;height:100%;"></div>