<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	tenwa.createTable({
		id: "rent_income_plan",
		renderTo: "id_table_rent_income_plan",
		width : '999',
		height : 400,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		xmlFileName: 'eleasing/workflow/rent/rent_comm/rent_plan.xml',
		params : {
		    contract_id:"${requestScope['contract_info.id']}",
		    ismaymoney:' and currentoverage>0'      
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'rentlist', header: '期次'},
			{field: 'status', header: '回笼状态', renderer: showRentStatus},
			{field: 'plandate', header: '计划日期'},
			{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
			{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
			{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},
			{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : "currency"},
			{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"},
			{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : "currency"}
		]
	});
});

//生成回笼明细
function generateDeduceRentDetail(rents){
	    var selectedDatas=rents;
	    var currTable=mini.get("rent_income_detail");
	    var miniTable=mini.get("rent_income_plan");
	    var currRowDatas=currTable.getData();
		for(var i = 0; i < selectedDatas.length; i ++){
			if(parseFloat(selectedDatas[i].currentoverage)>0){
				var rowObj={};
				rowObj.planid=selectedDatas[i].id;
				rowObj.planlist = selectedDatas[i].rentlist;
				rowObj.hirelist ='1';
				rowObj.balancemode = "payfund11";
				rowObj.balancemodename = "坐扣";
				rowObj.hiredate=selectedDatas[i].plandate;
				rowObj.accountingdate=(new Date()).format('yyyy-MM-dd');
				rowObj.rent = selectedDatas[i].currentoverage;
				rowObj.corpus = selectedDatas[i].curcorpusoverage;
				rowObj.interest = selectedDatas[i].curinterestoverage;
				rowObj.penalty = selectedDatas[i].penaltyoverage;
				rowObj.curcorpusoverage = selectedDatas[i].curcorpusoverage;
				rowObj.curinterestoverage = selectedDatas[i].curinterestoverage;
				rowObj.penaltyoverage = selectedDatas[i].penaltyoverage;				
				rowObj.rentadjust = 0;
				rowObj.corpusadjust = 0;
				rowObj.interestadjust = 0;
				rowObj.penaltyadjust = 0;
				rowObj.memo=selectedDatas[i].memo;
				currRowDatas.push(mini.clone(rowObj));
			}
		}
		currRowDatas.sort(function(a,b){return a.planlist - b.planlist;});
	    currTable.setData(currRowDatas);
		miniTable.deselectAll(false);
	
}
</script>
<input type="hidden" id="isseparaterentlist" name="isseparaterentlist" value="${empty isseparaterentlist ? false : isseparaterentlist}"/>
<div id="id_table_rent_income_plan" style="width: 100%;height: 100%;"></div>