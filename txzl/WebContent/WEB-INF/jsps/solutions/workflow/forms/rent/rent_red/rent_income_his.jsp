<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "rent_income_his",
		renderTo: "id_table_rent_income_his",
		width: '100%',
		height: '100%',
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		lazyLoad: false,
		showToolbar: showTools,
		data: JsonUtil.decode('${empty json_rent_income_his_str ? "[]" : json_rent_income_his_str }'),
		tools: [{
			html: '红冲',
			plain: true,
			iconCls: 'icon-ok',
			handler: function(miniTable, buttonText){
			    var currentGrid = mini.get("rent_red_detail");//获取本次红冲grid
			    var currentData=currentGrid.getData();
			    var rows = miniTable.getSelecteds();
				var selectedDatas = miniTable.getSelecteds();
				if(selectedDatas.length == 0){
					mini.alert("请先选择要操作的数据！");
				    return false;
				}
				else if(currentData.length > 0){
					for ( var n = 0; n < currentData.length; n++)
					{
						for(var i=0;i<rows.length;i++){
	                      if(rows[i].id==currentData[n].pid){
	                    	 mini.alert("该笔数据已在本次红冲明细中请不要重复生成!");return false; }
					   }
					}
					FundCollectRedOutInfo(miniTable,selectedDatas,currentData,currentGrid);
				}
				else{
					FundCollectRedOutInfo(miniTable,selectedDatas,currentData,currentGrid);
			   }				
			}
		}],
		columns: [
		      	{type: 'indexcolumn'},
			    {type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field:'planid',header:'planid', visible: false},
				{field:'ebanknumber',header:'ebanknumber', visible: false},
				{field:'roll_back',header:'是否已被红冲',renderer : function(e){var rollback = e.record.roll_back;if(rollback=="0"){return "否";}else{return "是";}}},	
				{field: 'planlist', header: '计划期项'},
				{field: 'ebdataid', header: '网银编号'},
				{field: 'hirelist', header: '回笼期项'},
				{field: 'balancemodename', header: '结算方式'},
				{field: 'hiredate', header: '回笼日期'},
				{field: 'rent', header: '回笼租金',summary : true,dataType : "currency"},
				{field: 'corpus', header: '回笼本金',summary : true,dataType : "currency"},
				{field: 'interest', header: '回笼租息',summary : true,dataType : "currency"},
				{field: 'penalty', header: '回笼罚息',summary : true,dataType : "currency"},
				{field: 'rentadjust', header: '租金调整',summary : true,dataType : "currency"},
				{field: 'corpusadjust', header: '本金调整',summary : true,visible:false,dataType : "currency"},
				{field: 'interestadjust', header: '租息调整',summary : true,dataType : "currency"},
				{field: 'penaltyadjust', header: '罚息调整',summary : true,dataType : "currency"},
				{field: 'invoiceno', header: '单据号'},
				{field: 'accountingdate', header: '会计处理日'},
				{field: 'ownbank', header: '本方银行',width:150},
				{field: 'ownaccount', header: '本方银行账户',width:150},
				{field: 'ownnumber', header: '本方银行账号',width:150},
				{field: 'hireobject', header: '付款人',width:150},
				{field: 'hirebank', header: '对方银行',width:150},
				{field: 'hireaccout', header: '对方银行账户',width:150},
				{field: 'hirenumbr', header: '对方银行账号',width:150},
				{field: 'memo', header: '备注'}		
		]
	});
});

//是否已存在
function isExisted(currTable, id){
	var currRowDatas = currTable.getData();
	for(var i = 0;i < currRowDatas.length; i++){
		if(id == currRowDatas[i].id){
			return true;
		}
	}
	return false;
}
function FundCollectRedOutInfo(miniTable,selectedDatas,currentData,currentGrid){
	var clist=[];
	var rowDatas = [];
	for (var i = 0 ;i <selectedDatas.length; i++ )
 	{  	
	 	if(selectedDatas[i].roll_back==0){		
 		var newRow = {}; 
 		newRow=mini.clone(selectedDatas[i]);
 		newRow.id="";
 		newRow.pid=selectedDatas[i].id;
 		newRow.balancemode='payfund9';
 		newRow.balancemodename='红冲';
 		var fields=['rent','corpus','interest','penalty','rentadjust','corpusadjust','interestadjust','penaltyadjust'];
 		for(var j=0;j<fields.length;j++){
 			newRow[fields[j]]=parseFloat(newRow[fields[j]])*-1;
	 	}
 		newRow.roll_back='-1';
 		currentData.push(newRow); 
	 	}else{
	 		clist.push(selectedDatas[i].planlist);
		}	
 	}
	currentData.sort(function(a,b){return a.planlist - b.planlist;});
 	currentGrid.setData(currentData);
 	miniTable.deselectAll(false);
 	var message="生成租金红冲明细成功！";
 	if(clist.length>0){message+="<br>其中["+clist+"]期已经被红冲不能再红冲";}
 	mini.alert(message);
}
</script>
<div id="id_table_rent_income_his" style="width: 100%;height: 100%;"></div>