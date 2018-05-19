<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false;}
	tenwa.createTable({
		id: "rent_income_plan",
		renderTo: "id_table_rent_income_plan",
		width : globalClientWidth-30,
		height : 400,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: [{
			html: '生成罚息减免',
			plain: true,
			iconCls: 'icon-ok',
			handler: function(miniTable, buttonText){
				var currTable = mini.get("rent_penalty_adjust_detail");
				var currRowDatas = mini.clone(currTable.getData());
				var selectedDatas = miniTable.getSelecteds();
				if(selectedDatas.length == 0){
					mini.alert("请先选择要减免罚息的租金计划！");
					return false;			
				}else{
					
					generateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,10000000);
				}
		  }}],
		data: JsonUtil.decode('<mini:param  name="json_rent_income_plan_str" defaultValue="[]"/>'),
		columns: [
			{type: 'checkcolumn',visible: showTools},
			{field: 'id', header: 'id', visible: false},
			{field: 'rentlist', header: '期次'},
			{field: 'status', header: '回笼状态', renderer: showRentStatus},
			{field: 'plandate', header: '计划日期'},
			{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : "currency"},
			{field: 'curpenaltyincome', header: '罚息实收',type:'double',summary : true,dataType : "currency"},
			{field: 'curpenaltyadjustincome', header: '罚息已减免',type:'double',summary : true,dataType : "currency"},
			{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : "currency"},
			{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : "currency"},
			{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : "currency"},
			{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : "currency"},	
			{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : "currency"},
			{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : "currency"}
			
		]
	});
});
function generateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,ebankmoney){
	var rowObj = {};
	var message = "";//已添加的期次
	var cmessage="";//已核销的期次
	var dmessage="";//没有网银核销的期次
	var rent = 0.00;
	var corpus = 0.00;
	var interest = 0.00;
	var penalty = 0.00;
	allMoney=ebankmoney;
	if(parseFloat(ebankmoney)>0){
		for(var i = 0; i < selectedDatas.length; i ++){
			if(parseFloat(allMoney)<=0){
				dmessage+= '【' + selectedDatas[i].rentlist + '】';
				continue;
			}
			if(isExisted(currTable,selectedDatas[i].rentlist)){
				message += '【' + selectedDatas[i].rentlist + '】';
				continue;
			}
			if(!checkRentMayIncome(selectedDatas[i])){
				cmessage+= '【' + selectedDatas[i].rentlist + '】';
				continue;
		    }
			rowObj.planid=selectedDatas[i].id;
			rowObj.planlist = selectedDatas[i].rentlist;
			rowObj.hirelist = getHireList(selectedDatas[i].rentlist);
			rowObj.balancemode = "payfund6";
			rowObj.balancemodename = "电汇";
			rowObj.hiredate = (new Date()).format('yyyy-MM-dd');
			rowObj.accountingdate = (new Date()).format('yyyy-MM-dd');
			rowObj.rent =0;
			rowObj.corpus = 0;
			rowObj.interest = 0;
			rowObj.penalty=0;
			rowObj.planpenalty = selectedDatas[i].penalty;
			rowObj.curpenaltyincome = selectedDatas[i].curpenaltyincome;
			rowObj.curpenaltyadjustincome = selectedDatas[i].curpenaltyadjustincome;
			rowObj.curcorpusoverage = 0;
			rowObj.curinterestoverage = 0;
			rowObj.penaltyoverage = selectedDatas[i].penaltyoverage;				
			rowObj.rentadjust = 0;
			rowObj.corpusadjust = 0;
			rowObj.interestadjust = 0;
			rowObj.penaltyadjust =  selectedDatas[i].penaltyoverage;
			currRowDatas.push(mini.clone(rowObj));
		}
		currRowDatas.sort(function(a,b){return a.planlist - b.planlist;});
	    currTable.setData(currRowDatas);
	   
		if(message != ""||cmessage!=""||dmessage){
			var tempMessage="";
			if(message != ""){tempMessage+="操作成功！其中计划期项为<br/>" + message + "<br/>的数据已添加到实收计划中！"};
			if(cmessage != ""){tempMessage+="计划期项为<br/>" + cmessage + "<br/>的罚息余额为零或租金没有收完,不允许生成！"};
			mini.alert(tempMessage);
		}else{
			mini.alert("生成罚息减免回笼明细成功！");
		}
		miniTable.deselectAll(false);
	}else{
		mini.alert("可核销的金额为0不能再核销了");
   }
}
function checkRentMayIncome(curRowData){
	if(parseFloat(curRowData.penaltyoverage)>0){
        return true;
    }else{
       return false;
    }
}
</script>
<div id="id_table_rent_income_plan" style="width: 100%;height: 100%;"></div>