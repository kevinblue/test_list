<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id:"collection_his",
		renderTo:"id_table_collection_his",
		width :globalClientWidth-30,
		height :400,
		lazyLoad:false,
		isClickLoad:false,
		multiSelect :true,
		title:"",
		remoteOper :false,
		showPager:false,
		showToolbar:showTools,
		tools:toolsArr,
		data:JsonUtil.decode('${empty json_collection_his_str ? "[]" :json_collection_his_str }'),
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'feetypename', header:'费用类型',formEditorConfig:{}
				},
			{field:'paymentid', header:'收款编号'},//计划表主键id
			{field:'planmoney', header:'计划金额（元）',summary : true,dataType : "currency"},
			{field:'factdate', header:'实收日期',type:"date",dateFormat : "yyyy-MM-dd",renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.factdate);
				return valueStr;
					}},
			{field:'factmoney', header:'实收金额（元）',summary : true,dataType :"currency"},
			{field:'overmoney', header:'剩余金额（元）',summary :true,dataType :"currency"},		
			{field:'penalty', header:'实收罚息（元）',summary : true,dataType :"currency"},
			{field:'feeadjust', header:'调整金额（元）',summary :true,dataType :"currency"},
			{field:'roll_back',header:'是否已被红冲',
							renderer:function(e){
								var row=e.record;
								var valueStr = getValueStr2(row.roll_back);
								return valueStr;
									}
			},	
			{field:'fundfundchargeplan', header:'收款编号',visible:false},//计划表主键id
			{field:'ebanknumber', header:'网银编号',renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.ebanknumber);
				return valueStr;
					}},
			{field:'feetype', header:'费用类型',formEditorConfig:{}, visible:false},
			/* {field:'fundtype', header:'类型',formEditorConfig:{}, visible:false}, */
			
			
			/* {field:'fundtypename', header:'类型',formEditorConfig:{}}, */
			{field:'settlemethod', header:'结算方式', visible:false,formEditorConfig:{newLine:true}},
			{field:'settlemethodname', header:'结算方式',
				formEditorConfig:{readOnly :true},renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.settlemethodname);
				return valueStr;
					}},
		
		/* 	{field:'otherbank', header:'对方银行',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.otherbank);
				return valueStr;
					}},
			{field:'clientname', header:'对方账户',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.clientname);
				return valueStr;
					}},
			{field:'clientaccnumber', header:'对方账号',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.clientaccnumber);
				return valueStr;
					}},
			{field:'accountbank', header:'本方银行',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.accountbank);
				return valueStr;
					}},     
			{field:'account', header:'本方账户',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.account);
				return valueStr;
					}},
			{field:'accnumber', header:'本方账号',width:150,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.accnumber);
				return valueStr;
					}},
			{field:'factobject', header:'付款对象',width:180,renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.factobject);
				return valueStr;
					}}, */
			{field:'accountingdate', header:'会计处理日',type:"date",dateFormat : "yyyy-MM-dd",renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.accountingdate);
				return valueStr;
					}},
			{field:'ffcmemo', header:'备注',renderer:function(e){
				var row=e.record;
				var valueStr = getValueStr2(row.ffcmemo);
				return valueStr;
					}}
		]
	});
	});
});

 var toolsArr=[{
		html :'生成实收红冲明细',
		plain :true,
		iconCls :'icon-ok',
		handler :function(miniTable, buttonText)
		{
			var curGrid = mini.get("redout_current");//本次资金收款grid
			var curGrids =curGrid.getData();
			var rows = miniTable.getSelecteds();   
			if (rows.length==0){mini.alert("请勾选数据在操作!");return false;}
			else if (curGrids.length > 0)
			{ // 重复生成判断
				for ( var n = 0; n < curGrids.length; n++)
				{
					for(var i=0;i<rows.length;i++){
                      if(rows[i].id==curGrids[n].pid){
                    	 mini.alert(rows[i].feetypename+rows[i].factmoney+"已在本次红冲明细中请不要重复生成!");return false; }
                      if(parseFloat(rows[i].roll_back)!=0){ mini.alert(rows[i].feetypename+rows[i].factmoney+"已红冲 过不能再红冲!");return false;}	
				   }
				}
				FundCollectRedOutInfo(miniTable);
				mini.alert("红冲操作成功,请到本次红冲明细中复核!");
			}
			else
			{
				for(var i=0;i<rows.length;i++){
					  if(parseFloat(rows[i].roll_back)!=0){ mini.alert(rows[i].feetypename+rows[i].factmoney+"已红冲 过不能再红冲!");return false;}
				}  
				FundCollectRedOutInfo(miniTable);
				mini.alert("红冲操作成功,请到本次红冲明细中复核!");
			}
		}
	}];

 function FundCollectRedOutInfo(miniTable)
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
 		newRow.roll_back='-1';
 		curData.push(newRow); 
 	}
 	currentGrid.setData(curData);
 	miniTable.deselectAll(false);
 }
 function getValueStr2(valueStr){
	 var renderHtml1 = "<table style='border:0px solid #FFF;'>";
	 var fileHtmlTd = '';	
     var renderHtml2 = "</table>";
	 var valuearray = valueStr.split(",");
	 for(var i=0;i<valuearray.length;i++){		 
	 		fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
        				 "<td style='border:0px solid #FFF;'>"+valuearray[i]+
         				"</td>"+
        				 "</tr>";
	 }	 
       return renderHtml1+fileHtmlTd+renderHtml2;
}
 

	
</script>

<div id="id_table_collection_his" style="width:100%;height:100%;"></div>
