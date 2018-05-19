<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "payment_plan",
		renderTo: "id_table_payment_plan",
		width : globalClientWidth-30,
		height : 400,
		editFormPopupWindowWidth : 400,
		lazyLoad:false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect : true,
		showToolbar: showTools,
		tools: toolsArray,
		data: JsonUtil.decode('<mini:param  name="json_payment_plan_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paymentid', header: '付款编号'},
			{field: 'feetype', header: '费用类型',formEditorConfig:{newLine:true}, visible: false},
			{field: 'feetypename', header: '费用类型',formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'planmoney', header: '计划金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly : true,otherAttributes: '  '}},
			{field: 'factmoney', header: '已付金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
			{field: 'overmoney', header: '未付金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'plandate', header: '计划日期',dateFormat : "yyyy-MM-dd",formEditorConfig:{
					type: 'date',
					required: true,
					newLine:true,
					readOnly : true,
					format: 'yyyy-MM-dd'
			}},
			{field: 'paycust', header: '支付对象',visible:false},
			{field: 'paycustname', header: '支付对象',formEditorConfig:{newLine:true,readOnly : true}},
			{field: 'planmoneystatus', header: '付款状态', renderer : function(e){
				  return setfundPayState(e.record);
	     	}},
			{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,readOnly : true}}
		]
	});
	});
});
var toolsArray=
	[
	{
		html : '生成付款明细',
		plain : true,
		iconCls : 'icon-ok',
		handler : function(miniTable, buttonText) 
		{
			var curGrid = mini.get("payment_current");//本次资金投放grid
			var curGrids =curGrid.getData();
			var rows = miniTable.getSelecteds();
	        
			if (rows.length == 0)
			{
				alert("请勾选数据在操作!");
				return false;
			}
			/* 不在本次明细中重复生成判断 */
			var gobalFlag=false;
			for(var i = 0 ; i< rows.length;i++){
				var row = rows[i];
				var flag=false;//标识重复
				for(var j = 0 ; j < curGrids.length;j++){										
					if(row.id == curGrids[j].fundfundchargeplan){			
						gobalFlag=true;											 
					}
				}
			} 
			if(gobalFlag){
				mini.alert("您选中行的数据已在本次付款明细中了,请不要重复生成！");
				miniTable.deselectAll();return false;
			}else{
				getFundPayPlan(miniTable);
				mini.alert("操作成功,请到本次付款明细查看!");	
			}
			/* 不在本次明细中重复生成判断  结束*/
			
		}
	}];
	function  getFundPayPlan(miniTable)
	{
		var newRow = {};
		var planGrid = miniTable.getSelecteds();//获取资金计划grid
		var currentGrid = mini.get("payment_current");//获取本次计划grid
		var rowDatas = [];
		for (var i = 0 ;i <planGrid.length; i++ )
		{
			newRow.fundfundchargeplan=planGrid[i].id;
			newRow.paymentid=planGrid[i].paymentid;
			newRow.feetype=planGrid[i].feetype;
			newRow.feetypename=planGrid[i].feetypename;
			newRow.settlemethod='payfund6';
			newRow.settlemethodname='电汇';
			newRow.paytype='pay_type_out';
			newRow.paytypename='付款';
			newRow.factdate=planGrid[i].plandate;
			newRow.factmoney=planGrid[i].overmoney;//本次投放金额=计划金额-已投放金额
			newRow.factobject=planGrid[i].paycust;
			newRow.factobjectname=planGrid[i].paycustname;
			newRow.comparemoney=planGrid[i].overmoney;//把可投放的金额传到本次投放列表方便比较	
			newRow.feeadjust='0';
			rowDatas.push(mini.clone(newRow));
		}
		currentGrid.addRows ( rowDatas, 0 );
		currentGrid.saveDataToInput();
		miniTable.deselectAll(false);
	}
</script>

<div id="id_table_payment_plan" style="width:100%;height:100%;"></div>