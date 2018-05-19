<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id:"collection_plan",
		renderTo:"id_table_collection_plan",
		width:globalClientWidth-10,
		height:400,
		editFormPopupWindowWidth:400,
		lazyLoad:false,
		isClickLoad:false,
		title:"",
		remoteOper:false,
		showPager:false,
		showToolbar:showTools,
		multiSelect:true,
		tools:[
				{
					html:'生成资金收款',
					plain:true,
					iconCls:'icon-ok',
					handler:function(miniTable, buttonText) 
					{
						var curGrid = mini.get("collection_current");//本次资金收款grid
						var curGrids =curGrid.getData();
						var rows = miniTable.getSelecteds();
						if (rows.length == 0){mini.alert("请勾选数据在操作!");return false;}
						/*判断全部结清状态不允许重复生成*/
						for ( var k = 0 ; k < rows.length; k++ )
						{
							if (parseFloat(rows[k].overmoney)==0)
							{
								//mini.alert(rows[k].feetypename+"全部结清状态不允许重复生成资金收款!");
								mini.alert("存在已核销状态收款！请只点击未核销状态下资金收款！");
								miniTable.deselectAll();
								return false;
							}
						}
						
						/* 不在本次明细中重复生成判断 */
						var gobalFlag=false;
						for(var i = 0 ; i< rows.length;i++){
							var row = rows[i];
							for(var j = 0 ; j < curGrids.length;j++){								
								if(row.id == curGrids[j].fundfundchargeplan){gobalFlag=true;break;}
							}
						} 
						if(gobalFlag){
							mini.alert("您选中行的数据已在本次明细中了,请不要重复生成！"); return false;
						}else {
							if(fundCurrentPlanFunc(miniTable)){
							  mini.alert("操作成功,请到本次明细查看!");	
							}
						}
					}}
		],
		data:JsonUtil.decode('${empty json_collection_plan_str ? "[]":json_collection_plan_str }'),
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'paymentid', header:'收款编号'},
			{field:'feetypename', header:'费用类型',formEditorConfig:{}},
			{field:'feetype', header:'费用类型', visible:false,formEditorConfig:{ }},
			{field:'planmoney', header:'计划金额',summary:true,dataType:"currency"},
			{field:'factmoney', header:'已收付金额',summary:true,dataType:"currency"},
			{field:'overmoney', header:'未收金额',summary:true,dataType:"currency"},
			{field:'penaltyoverage', header:'罚息余额',summary:true,dataType:"currency"},
			{field:'lesspenalty', header:'剩余罚息',summary:true,dataType:"currency"},
			{field:'plandate', header:'计划日期'},
			{field:'payobj', header:'付款客户', visible:false},
			{field:'planmoneystatusname', header:'收款状态',renderer:function(e){return setfundIncomeState(e.record);}},
			{field:'fpnote', header:'备注'}
		]
	});
	});
});

</script>

<div id="id_table_collection_plan" style="width:100%;height:100%;"></div>
<script type="text/javascript">
function fundCurrentPlanFunc(miniTable)
{
	var newRow = {};
	var planGrid = miniTable.getSelecteds();//获取资金计划grid
	var currentGrid = mini.get("collection_current");//获取本次计划grid
	var rowDatas = [];
	var maymoney="${requestScope['fund_ebank_data.mayopemoney']}";//网银可核销金额
	maymoney=parseFloat(maymoney).toFixed(2);
    if(maymoney<0){mini.alert("网银余额为负数不能在进行核销"); return false;}
    
	for (var i = 0 ;i <planGrid.length; i++ )
	{
		newRow.contractid="${requestScope['contract_info.id']}";
		newRow.fundfundchargeplan=planGrid[i].id;
		newRow.paymentid=planGrid[i].paymentid;
		newRow.feetype=planGrid[i].feetype;
		newRow.feetypename=planGrid[i].feetypename;
		newRow.settlemethod='payfund6';
		newRow.settlemethodname='电汇';
		newRow.paytype='pay_type_in';
		newRow.paytypename='收款';
		newRow.factdate="${requestScope['fund_ebank_data.factdate'] }";
		newRow.factmoney=planGrid[i].overmoney;
		//回笼罚息
		newRow.penalty=planGrid[i].lesspenalty;
		newRow.planmoney=planGrid[i].planmoney;
		newRow.hadmoney=planGrid[i].factmoney;
		newRow.ffcmemo=planGrid[i].fpnote;	
		newRow.ebid="${requestScope['fund_ebank_data.id']}";
		newRow.ebanknumber="${requestScope['fund_ebank_data.ebdataid']}";
		newRow.factobject=planGrid[i].payobj;
		newRow.accountbank="${requestScope['fund_ebank_data.ownbank'] }";
		newRow.account="${requestScope['fund_ebank_data.ownaccount'] }";
		newRow.accnumber="${requestScope['fund_ebank_data.ownaccnumber'] }";
		newRow.otherbank="${requestScope['fund_ebank_data.otherbank'] }";
		newRow.clientname="${requestScope['fund_ebank_data.clientname'] }";
		newRow.clientaccnumber="${requestScope['fund_ebank_data.clientaccnumber'] }";
		newRow.accountingdate="${requestScope['fund_ebank_data.factdate'] }";
		newRow.feeadjust='0';
		rowDatas.push(mini.clone(newRow));	
	}
	currentGrid.addRows ( rowDatas, 0 );
	currentGrid.saveDataToInput();
	miniTable.deselectAll(false);
	return true;
	
}


</script>