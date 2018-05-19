<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "rent_income_plan",
		renderTo: "id_table_rent_income_plan",
		width : globalClientWidth - 30,
		height :400,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		lazyLoad: true,
		showToolbar: showTools,
		tools: [{
			html: '生成租金核销',
			plain: true,
			iconCls: 'icon-ok',
			handler: function(miniTable, buttonText){
				var currTable = mini.get("rent_income_detail");
				
				var currRowDatas = mini.clone(currTable.getData());
				var selectedDatas = miniTable.getSelecteds();
				if(selectedDatas.length == 0){
					mini.alert("请先选择要核销的租金数据！");
					return false;			
				}else{
					var maymoney=getfundEbankOverMoney();//获得网银余额（网银余额-已增加到每次核销的租金）
					generateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,maymoney);
				}
		  }
		}, '-' ,{
			html: '<div readOnly="' + !showTools + '" checked="<mini:param name='isseparaterentlist' defaultValue='false'/>" onclick="saveCheckboxValue(this);" class="mini-checkbox" style="vertical-align: middle;" text="允许隔期回笼"></div>',
			isHtml: true
		}],
		data: JsonUtil.decode('<mini:param  name="json_rent_income_plan_str" defaultValue="[]"/>'),
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
//保存 允许隔期回笼 的value
function saveCheckboxValue(obj){
	var flag = obj.value;
	$("#isseparaterentlist").val(flag);
}

function checkRentMayIncome(curRowData){
	if(parseFloat(curRowData.currentoverage)>0||parseFloat(curRowData.penaltyoverage)>0){
        return true;
    }else{
       return false;
    }
}
function getfundEbankOverMoney(){
	var mayMoney=mini.get("fund_ebank_data.mayopemoney").getValue();
	mayMoney=parseFloat(mayMoney).toFixed(2);
	//var rentData = mini.get("rent_income_detail").getData();
	var rentData =mini.get("rent_income_detail").getData();
	if(rentData.length>0){
          for(var i=0;i<rentData.length;i++){
        	  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].corpus);
        	  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].interest);
        	  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].penalty);
        	  mayMoney=parseFloat(mayMoney).toFixed(2);	  
          }
          return mayMoney;      
	}else{
		return mayMoney;
	}
}
//生成回笼明细
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
			rowObj.ebanknumber ="<mini:param name='fund_ebank_data.id'/>"; 
			rowObj.ebdataid ="<mini:param name='fund_ebank_data.ebdataid'/>"; 
			rowObj.hirelist = getHireList(selectedDatas[i].rentlist);
			rowObj.balancemode = "payfund6";
			rowObj.balancemodename = "电汇";
			rowObj.hiredate = "<mini:param name='fund_ebank_data.factdate'/>";
			corpus = parseFloat(selectedDatas[i].curcorpusoverage);
			interest = parseFloat(selectedDatas[i].curinterestoverage);
			penalty = parseFloat(selectedDatas[i].penaltyoverage);
			var calculateresult = calculatePlanRent(allMoney, corpus, interest, penalty);
			allMoney = parseFloat(calculateresult.lastMoney).toFixed(2);
			rowObj.rent = calculateresult.rent;;
			rowObj.corpus = calculateresult.corpus;
			rowObj.interest = calculateresult.interest;
			rowObj.penalty = calculateresult.penalty;
			rowObj.curcorpusoverage = selectedDatas[i].curcorpusoverage;
			rowObj.curinterestoverage = selectedDatas[i].curinterestoverage;
			rowObj.penaltyoverage = selectedDatas[i].penaltyoverage;				
			rowObj.rentadjust = 0;
			rowObj.corpusadjust = 0;
			rowObj.interestadjust = 0;
			rowObj.penaltyadjust = 0;
			rowObj.accountingdate ="<mini:param name='fund_ebank_data.factdate'/>";
			rowObj.ownbank ="<mini:param name='fund_ebank_data.ownbank'/>";
			rowObj.ownaccount = "<mini:param name='fund_ebank_data.ownaccount'/>";
			rowObj.ownnumber ="<mini:param name='fund_ebank_data.ownaccnumber'/>";
			rowObj.hireobject = "<mini:param name='fund_ebank_data.clientname'/>"; 
			rowObj.hirebank ="<mini:param name='fund_ebank_data.clientbank'/>";  
			rowObj.hireaccount ="<mini:param name='fund_ebank_data.clientaccount'/>";    
			rowObj.hirenumber = "<mini:param name='fund_ebank_data.clientaccnumber'/>";
			currRowDatas.push(mini.clone(rowObj));
		}  
		currRowDatas.sort(function(a,b){return a.planlist - b.planlist;});
	    currTable.setData(currRowDatas);
		if(message != ""||cmessage!=""||dmessage){
			var tempMessage="操作成功，";
			if(message != ""){tempMessage+="其中计划期项为<br/>" + message + "<br/>的数据已添加到实收计划中！"};
			if(cmessage != ""){tempMessage+="其中计划期项为<br/>" + cmessage + "<br/>的数据已回笼不能再核销！"};
			if(dmessage != ""){tempMessage+="其中计划期项为<br/>" + dmessage + "<br/>的数据已没有网银余额进行核销！"};
			mini.alert(tempMessage);
		}else{
			mini.alert("生成租金回笼明细成功！");
		}
		miniTable.deselectAll(false);
	}else{
		mini.alert("可核销的金额为0不能再核销了");
   }
}
</script>
<input type="hidden" id="isseparaterentlist" name="isseparaterentlist" value="${empty isseparaterentlist ? false : isseparaterentlist}"/>
<div id="id_table_rent_income_plan" style="width: 100%;height: 100%;"></div>