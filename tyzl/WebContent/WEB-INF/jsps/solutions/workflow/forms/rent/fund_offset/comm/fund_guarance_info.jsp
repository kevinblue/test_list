<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "fund_guarance_plan",
		renderTo: "id_table_fund_guarance_plan",
		width : '100%',
		height :'100%',
		editFormPopupWindowWidth : 400,
		lazyLoad: false,
		isClickLoad:false,
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools:[{
			html: '抵扣',
			plain: false,
			iconCls: 'icon-folder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					if(parseFloat(row.overmoney)>0){
					   var selectedDatas=mini.get("rent_income_plan").getSelecteds();	
					   var currTable=mini.get("rent_income_detail");
					   var currRowDatas=currTable.getData();	
					   var renttable=mini.get("rent_income_plan");
					   var info="使用编号为"+row.paymentid+"的保证金抵扣";
					   mini.mask({el: document.body,cls: 'mini-mask-loading',html: '生成抵扣信息中..'});
					   var rinfo= generateRentDetail(renttable,currTable,currRowDatas,selectedDatas,row.overmoney,info);
                       var fundtable=mini.get("caution_money_refund_detail");
                       var fundRowDatas=fundtable.getData();
                       if(rinfo.length>0){
					     getFundPutPlan(fundtable,fundRowDatas,row,rinfo);
                       }
					   mini.unmask(document.body);
					   miniTable.deselectAll(false);
					   mini.get("id_window_fund_offset").hide();
					}else{
                         mini.alert("这一笔保证金没有余额不能进行保证金抵扣");
                         return false;
					}
				}else{
					mini.alert("请选中要抵扣的保证金计划！");
					return false;
				}
			}
		}],
		queryButtonColSpan: 4,
		xmlFileName: 'eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml',
		params : {
		    paytype:'pay_type_out',
		    feetypes:"'feetype2','feetype17','feetype16'",
		    contractid:'<mini:param  name="contract_info.id" />'  
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{
				
				fieldVisible: false,
				fillFromFieldName : 'feetype',
				fillProperty : 'name'
			}},
			{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{
				type : 'combobox',
				required: true,
				labelWidth:100,
				textField: 'name',
				valueField: 'value',
				fieldVisible: true,
				params: {
				    pid: 'FeeType',
					xmlFileName: 'combos/comboDict.xml'
				}
			}},
			{field: 'paymentid', header: '收/付款编号',formEditorConfig:{labelWidth:100,required: true}},
			{field: 'paycustname', header: '支付对象',formEditorConfig:{fieldVisible: false}},
			{field: 'paycust', header: '支付对象',visible: false,formEditorConfig:{
				newLine: true,
				type : 'queryinput',
				required: true,
				textField: 'name',
				valueField: 'name',
				allowInput: false,
				fieldVisible: true,
				width: 300,
				colspan:3,
				params: {
					xmlFileName: 'common/custInfo.xml'
				}
			}},
			{field: 'planmoney', header: '计划付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,required: true}},
			{field: 'factmoney', header: '实付款金额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',readOnly:true}},
			{field: 'overmoney', header: '计划余额',summary : true,dataType : "currency",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},
			{field: 'plandate', header: '付款日期',formEditorConfig:{
				required: true,
				type:'date'
			}},
			{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',
				width: 300,
				height:70}}
		]
	});
	});
	 
});
function  getFundPutPlan(currTable,currRowDatas,row,rinfo)
{
	var newRow = {};
	var rowDatas = [];
    newRow.fundfundchargeplan=row.id;
	newRow.paymentid=row.paymentid;
	newRow.feetype=row.feetype;
	newRow.feetypename=row.feetypename;
	newRow.settlemethod='payfund7';
	newRow.settlemethodname='保证金抵扣';
	newRow.paytype='pay_type_out';
	newRow.paytypename='付款';
	newRow.factdate=(new Date()).format('yyyy-MM-dd');
	newRow.accountingdate=(new Date()).format('yyyy-MM-dd');
	newRow.factadjust=0;
	newRow.factobject=row.paycust;
	newRow.factobjectname=row.paycustname;

	newRow.feeadjust='0';
	for(var i=0;i<rinfo.length;i++){
		var temp=mini.clone(newRow);
		temp.pid=rinfo[i].pid;
		var tempmoney=parseFloat(rinfo[i].rent)+parseFloat(rinfo[i].penalty);
		tempmoney=parseFloat(tempmoney).toFixed(2);
		temp.factmoney=tempmoney;
		temp.comparemoney=tempmoney;
		var info="抵扣第"+rinfo[i].planlist+"期";
		if(parseFloat(rinfo[i].rent)>0){info+="租金("+rinfo[i].rent+")";}
		if(parseFloat(rinfo[i].penalty)>0){info+="罚息("+rinfo[i].penalty+")";}
		temp.ffcmemo=info;
		currRowDatas.push(temp);
	}
	currTable.setData(currRowDatas);
}
</script>

<div id="id_table_fund_guarance_plan" style="width:100%;height:400px"></div>
