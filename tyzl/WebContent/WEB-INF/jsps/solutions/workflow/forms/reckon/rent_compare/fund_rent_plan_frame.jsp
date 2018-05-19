<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
var columnsNomalNew = [
  				{type: 'indexcolumn'},
  				{type: 'checkcolumn'},
  				{field: 'id', header: 'id', visible: false},
  				{field: 'rentlist', header: '期项',
  					formEditorConfig:{
  						fieldVisible : false
  					}
  				},
  				{field: 'calplandate', header: '计划日期',
  					formEditorConfig:
  					{
  						required: true,
  						readOnly:true,
  						labelWidth:100,
  						type:'date',
  						format:'yyyy-MM-dd',
  						width: 200
  					}
  				},
  				{field: 'plandate', header: '实际计划日期',dateFormat : "yyyy-MM-dd",
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						type:'month',
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
  				{field: 'actualrent', header: '实际还款额',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						
  						width: 200
  					}
  				},
  				{field: 'corpus', header: '本金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						readOnly:true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin1()"'
  					}
  				},
  				{field: 'interest', header: '利息',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						required: true,
  						readOnly:true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin2()"'
  					}
  				},
  				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,visible: false,
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive1()"'
  					}
  				},
  				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,visible: false,
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
  				{field: 'rentadjust', header: '租金调整',visible: false,
          			formEditorConfig:
          			{
          				required: false,
          				labelWidth:100,
          				readOnly:true,
          				width: 200
          			}
          		}
  			];		
var confignew =  {
	id: "fund_rent_plan_frame_new",
	renderTo: "id_fund_rent_plan_frame_new",
	width: globalClientWidth - 30,
	height: 700,
	lazyLoad: false,
	isClickLoad:false,
	remoteOper : false,
	showPager: false,
	multiSelect: true,
	showToolbar: false,
	editFormPopupWindowWidth : 700,
	allowCellEdit: true,
	allowCellSelect: true,
	data: mini.decode('${empty json_fund_rent_plan_new_str ? "[]" : json_fund_rent_plan_new_str	 }'),
	columns:columnsNomalNew
};
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable(confignew);
	});
	
});
</script>
<div id="id_fund_rent_plan_frame_new"></div>