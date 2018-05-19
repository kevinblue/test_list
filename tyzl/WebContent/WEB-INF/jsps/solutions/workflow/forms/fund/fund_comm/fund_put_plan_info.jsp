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
		id: "put_plan",
		renderTo: "id_table_put_plan",
		width : globalClientWidth-10,
		height : 300,
		editFormPopupWindowWidth : 400,
		lazyLoad: false,
		isClickLoad:false,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools:  toolsArray,
		data: JsonUtil.decode('<mini:param  name="json_put_plan_str" defaultValue="[]"/>'),
		columns: [
		    {type : 'indexcolumn'},	
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paymentid', header: '付款编号',formEditorConfig:{newLine:true,readOnly : true,required: true}},
			{field: 'feetype', header: '费用类型',formEditorConfig:{newLine:true}, visible: false},
			{field: 'feetypename', header: '费用类型',formEditorConfig:{newLine:true}},
			{field: 'planmoney', header: '计划金额',summary : true,dataType : "currency",formEditorConfig:{}},
			{field: 'overmoney', header: '未投放金额',summary : true,dataType : "currency",formEditorConfig:{}},
			{field: 'factmoney', header: '已投放金额',summary : true,dataType : "currency"}, 
			{field: 'plandate', header: '计划日期', visible: false,formEditorConfig:{}},
			{field: 'paycustname', header: '支付对象'},
			{field: 'paycust', header: '支付对象',visible:false},
			{field: 'fpnote', header: '备注',formEditorConfig:{}}
		]
	});
	});
	
});
var toolsArray=
[
{
	html : '生成资金投放',
	plain : true,
	iconCls : 'icon-ok',
	handler : function(miniTable, buttonText) 
	{
		var put_current = mini.get("put_current");//本次资金投放grid
		var curGrids =put_current.getData();
		var rows = miniTable.getSelected();
        if (!rows)
		{
			mini.alert("请勾选数据在操作!");
			return false;
		}
		if (curGrids.length > 0) //第二次操作才判断
 		{
 			for ( var n = 0; n < curGrids.length; n++)
 			{
 				if ( rows.paymentid == curGrids[n].paymentid )
 				{
 					mini.alert("该记录已在本次投放明细中,请不要重复生成!");
 					miniTable.deselectAll(false);
 					return false;
 				}
 			}
 		}

		mini.showMessageBox({
            title: "是否要进行货扣",
            iconCls: "mini-messagebox-question",
            buttons: ["yes", "no"],
            message: "是否要进行货扣",
            callback: function (text) {
            	if(text=="yes"){
                	mini.get("id_window_fund_offset").show();
                	
                 }else if(text=="no"){
                        var cdate=miniTable.getSelected();
                        cdate.settlemethod='payfund6';
                		cdate.settlemethodname='电汇';
                		cdate.pid="";
                        var rdate=[];
                        rdate.push(cdate);
             			getFundPutPlan(rdate);
             			mini.alert("操作成功,请到本次投放明细查看!");
                 }else{
                	 
                 } 
            }
        });
		/*
		 mini.confirm("是否要进行货扣", "提示", function (text) {
                if(text=="ok"){
                	mini.get("id_window_fund_offset").show();
                	
                 }else{
                        var cdate=miniTable.getSelected();
                        cdate.settlemethod='payfund6';
                		cdate.settlemethodname='电汇';
                		cdate.pid="";
                        var rdate=[];
                        rdate.push(cdate);
             			getFundPutPlan(rdate);
             			mini.alert("操作成功,请到本次投放明细查看!");
                 } 
	       });
		*/
	}
}];
function  getFundPutPlan(payplan)
{
	var miniTable=mini.get("put_plan");
	var newRow = {};
	var planGrid = payplan;//获取资金计划grid
	var currentGrid = mini.get("put_current");//获取本次计划grid
	var rowDatas =currentGrid.getData();
	for (var i = 0 ;i <planGrid.length; i++ )
	{
		newRow.fundfundchargeplan=planGrid[i].id;
		newRow.paymentid=planGrid[i].paymentid;
		newRow.pid=planGrid[i].pid;
		newRow.feetype=planGrid[i].feetype;
		newRow.feetypename=planGrid[i].feetypename;
		newRow.settlemethod=planGrid[i].settlemethod;
		newRow.settlemethodname=planGrid[i].settlemethodname;
		newRow.paytype='pay_type_out';
		newRow.paytypename='付款';
		newRow.factdate=planGrid[i].plandate;
		newRow.factmoney=planGrid[i].overmoney;//本次投放金额=计划金额-已投放金额
		newRow.factobject=planGrid[i].paycust;
		newRow.factobjectname=planGrid[i].paycustname;
		newRow.comparemoney=planGrid[i].overmoney;//把可投放的金额传到本次投放列表方便比较	
		newRow.feeadjust='0';
		newRow.ffcmemo=planGrid[i].fpnote;
		rowDatas.push(mini.clone(newRow));
	}
	currentGrid.setData(rowDatas);
	miniTable.deselectAll(false);
}

</script>

<div id="id_table_put_plan"></div>
