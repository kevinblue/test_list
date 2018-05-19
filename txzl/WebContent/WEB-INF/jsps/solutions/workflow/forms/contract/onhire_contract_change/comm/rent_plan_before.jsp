<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">

var columns_before = [
  				{type: 'indexcolumn'},
  				{type: 'checkcolumn'},
  				{field: 'id', header: 'id', visible: false},
  				{field: 'rentlist', header: '期项',
  					formEditorConfig:
  					{
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						width: 200
  					}
  				},
  				{field: 'plandate', header: '计划日期',
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						type:'date',
  						format:'yyyy-MM-dd',
  						width: 200
  					}
  				},
  				{field: 'rent', header: '租金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						
  						width: 200
  					}
  				},
  				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						
  						required: true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin1()"'
  					}
  				},
  				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin2()"'
  					}
  				},
  				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,visible:false,
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive1()"'
  					}
  				},
  				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,visible:false,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive2()"'
  					}
  				},
  				{field: 'rentadjust', header: '租金调整',
          			formEditorConfig:
          			{
          				required: false,
          				labelWidth:100,
          				readOnly:true,
          				width: 200
          			}
          		}
  			];		
var config_before =  {
	id: "fund_rent_plan_before",
	renderTo: "id_fund_rent_plan_before",
	width: globalClientWidth - 30,
	height: 700,
	lazyLoad: false,
	isClickLoad:false,
	remoteOper : false,
	showPager: false,
	multiSelect: true,
	showToolbar: false,
	editFormPopupWindowWidth : 700,
	importTargetClass:'',//导入EXCEL对应的类
	importOrExPortCallBack:'',//导入回调方法
	importHeaderIndex:'3',//标题行
	importDataIndex:'4',//数据行
	allowCellEdit: true,
	allowCellSelect: true,
	oncelldblclick: function(e){
		var miniTable = e.sender;
		var rowData   = e.record;
		//miniTable.commitEdit();
		miniTable.beginEditCell();
	},
	data: mini.decode('${empty json_fund_rent_plan_before_str ? "[]" : json_fund_rent_plan_before_str	 }'),
	columns:columns_before,
};
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable(config_before);
	});
});

</script>
<div id="id_fund_rent_plan_before"></div>
