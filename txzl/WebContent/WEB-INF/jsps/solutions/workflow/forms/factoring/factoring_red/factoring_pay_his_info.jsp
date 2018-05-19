<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true')
	{
		showTools = false;
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "pay_his",
		renderTo: "id_table_pay_his",
		width : globalClientWidth-30,
		height : 400,
		lazyLoad: true,
		isClickLoad:false,
		multiSelect : true,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: toolsArray2,
		data: JsonUtil.decode('${empty json_pay_his_str ? "[]" : json_pay_his_str }'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'roll_back',header:'是否已被红冲',renderer :function(e){var rollback = e.record.roll_back;
			if(rollback=="0"){return "否";}else{return "是";}}},	
			{field: 'ebanknumber', header: '网银编号', visible: false},
			{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},
			{field: 'feetypename', header: '费用类型',formEditorConfig:{}},
			{field: 'paymentid', header: '付款编号'},//计划表主键id
			{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{newLine:true}},
			{field: 'settlemethodname', header: '结算方式',formEditorConfig:{readOnly : true}},
			{field: 'factmoney', header: '付款金额',summary : true,dataType : "currency"},
			{field: 'factdate', header: '付款日期'},
			{field: 'feeadjust', header: '调整金额',summary : true,dataType : "currency"},
			{field: 'accountbank', header: '本方银行'},     
			{field: 'account', header: '本方账户'},
			{field: 'accnumber', header: '本方账号'},
			{field: 'factobject', header: '付款客户'},
			{field: 'clientbank', header: '对方银行'},
			{field: 'clientaccount', header: '对方账户'},
			{field: 'clientaccnumber', header: '对方账号'},
			{field: 'accountingdate', header: '会计处理日'},
			{field: 'ffcmemo', header: '备注'}
		]
	});
	});
});

 var toolsArray2=
	 [{
			html : '生成付款红冲明细',
			plain : true,
			iconCls : 'icon-ok',
			handler : function(miniTable, buttonText)
			{
				var curGrid = mini.get("redout_current");//本次资金收款grid
				var curGrids =curGrid.getData();
				var rows = miniTable.getSelecteds();
	            
				if (!rows)
				{
					alert("请勾选数据在操作!");
					return false;
				}
				// 重复生成判断
				else if (curGrids.length > 0) //第二次操作才判断
				{
					for ( var n = 0; n < curGrids.length; n++)
					{
						for(var i=0;i<rows.length;i++){
	                      if(rows[i].id==curGrids[n].pid){
	                    	  mini.alert(rows[i].feetypename+rows[i].factmoney+"已在本次红冲明细中请不要重复生成!");								
	  						return false;
	                      }
					   }
					}
					FundPayRedOutInfo(miniTable);
					mini.alert("红冲操作成功,请到本次红冲明细中复核!");
				}
				else
				{
					for(var i=0;i<rows.length;i++){
						if(parseFloat(rows[i].roll_back)!=0){ mini.alert(rows[i].feetypename+rows[i].factmoney+"已红红冲 过不能再红冲!");return false;}
					}
					FundPayRedOutInfo(miniTable);
					mini.alert("红冲操作成功,请到本次红冲明细中复核!");
				}
			}
		}];
 function FundPayRedOutInfo(miniTable)
 {
 	var hisGrid = miniTable.getSelecteds();//获取资金计划grid
 	var currentGrid = mini.get("redout_current");//获取本次红冲grid
 	var curData=currentGrid.getData();
 	var rowDatas = [];
 	for (var i = 0 ;i <hisGrid.length; i++ )
 	{
 		var newRow = {}; 
 		newRow=mini.clone(mini.clone(hisGrid[i]));
 		newRow.id="";
 		newRow.pid=hisGrid[i].id;
 		newRow.settlemethod='payfund9';
 		newRow.settlemethodname='红冲';
 		newRow.factmoney=parseFloat(hisGrid[i].factmoney)*(-1);
 		newRow.feeadjust=parseFloat(hisGrid[i].feeadjust)*(-1);
 		newRow.feeadjust='0';
 		newRow.roll_back='-1';
 		curData.push(newRow);
 	}
 	currentGrid.setData(curData);
 	miniTable.deselectAll(false);
 }
</script>

<div id="id_table_pay_his"style="width:100%;height:100%;"></div>
