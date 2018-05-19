<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//资金收款状态
function setfundIncomeState(row){
    var overmoney=row.overmoney;
    var planmoney=row.planmoney;
    overmoney=parseFloat(overmoney);
    overmoney=overmoney.toFixed(2);
    planmoney=parseFloat(planmoney);
    planmoney=planmoney.toFixed(2);
    var csate="";
    if(parseFloat(overmoney)<0){
   	 csate= "多核销";
    }else if(parseFloat(overmoney)==0){
   	 csate= "已核销"; 
    }else if(parseFloat(overmoney)<parseFloat(planmoney)){
   	 csate= "部份核销";
    }else{
   	 csate= "未核销";
    }
    return csate;   
}
//资金付款状态
function setfundPayState(row){
     var overmoney=row.overmoney;
     var planmoney=row.planmoney;
     overmoney=parseFloat(overmoney);
     overmoney=overmoney.toFixed(2);
     planmoney=parseFloat(planmoney);
     planmoney=planmoney.toFixed(2);
     var csate="";
     if(parseFloat(overmoney)<0){
    	 csate= "多核销";
     }else if(parseFloat(overmoney)==0){
    	 csate= "已核销"; 
     }else if(parseFloat(overmoney)<parseFloat(planmoney)){
    	 csate= "部份核销";
     }else{
    	 csate= "未核销";
     }
     return csate;  
}
//租金回笼状态
function showRentStatus(e){
    var curRowData=e.record;
	if(curRowData.currentoverage == 0 && curRowData.penaltyoverage==0){
		return "已回笼";
	}else if(curRowData.currentoverage ==curRowData.rent){
		return "未回笼";
	}else{
		return "部分回笼";
	}
}
//检查资金实收或付款是否大于资金的余额，如果有网银则再检查网银
function checkFundFundCharge(tableid){
	 
	   var ebankid="";
	   if(mini.get("ebdataid")){
		   ebankid=mini.get("ebdataid").getValue();
	   }
	   var flowunid=flowUnid;
	   var chargeTable=mini.get(tableid);
	   var chargeData=chargeTable.getData();
	   var ids=[]; 
	   for(var i=0;i<chargeData.length;i++){
	     ids.push("'"+chargeData[i].fundfundchargeplan+"'");
	  }
	  var param={};
	  var cflag=true;
	  if(ebankid!=""){param["ebankid"]=ebankid;}
	  param["fundplandids"]=ids.join(",");
	  param["flowunid"]=flowunid;
	  var temptableid=tableid;
	  if(ids.length>0){
		  mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'检查资金...'});
		  ajaxRequest({
				url : getRootPath() + "/eleasing/getFundCheckInfo.acl",
				method : 'POST',
				success : function(rs) {
					var returnMessage = rs.responseText;
	                var checkInfo=eval("("+returnMessage+")");
	                if(checkInfo.result=="0"){
	                       mini.alert(checkInfo.message);
	                       cflag= false;
	                }else{
	                     var allsum=0;
	                     for(var i=0;i<chargeData.length;i++){
	                          allsum=parseFloat(allsum)+parseFloat(chargeData[i].factmoney);
	                          allsum=allsum.toFixed(2);
	                          for(var j=0;j<checkInfo.fund.length;j++){
	                               if(chargeData[i].fundfundchargeplan==checkInfo.fund[j].id){
	                                   var tempvalue=parseFloat(checkInfo.fund[j].overmoney)-parseFloat(chargeData[i].factmoney);
	                                   tempvalue=tempvalue.toFixed(2); 	                                   
	                            	   if(parseFloat(tempvalue)<0){
	                                       mini.alert(chargeData[i].feetypename+"第"+chargeData[i].paymentid+"笔("+chargeData[i].factmoney+")大于计划余额("+ checkInfo.fund[j].overmoney+")");
	                                       checkInfo.fund[j].overmoney=tempvalue;
	                                       cflag= false;
	                                   }else{
	                                	   checkInfo.fund[j].overmoney=tempvalue;
		                                }
	                               }
	                          }
	                     }
	                     if(checkInfo.ebank.length>0){
	                           var mayopemoney=checkInfo.ebank[0].mayopemoney;
	                           mayopemoney=parseFloat(mayopemoney);
	                           mayopemoney=mayopemoney.toFixed(2);
	                           if(allsum>parseFloat(mayopemoney)){
	                        	   mini.alert("本次核销金额"+allsum+"大于网银中的可核销金额"+mayopemoney);
	                        	   cflag= false;
	                           }
	                     }
	                }
					mini.unmask(document.body);
		  },
			async : false,
			failure : function(res) {
			  mini.unmask(document.body);
			},
			params :param
		});
	  }else{
	     mini.alert("没有要校验的资金实收或实付");
	     cflag= false;
	  }	
	  return cflag;
} 

function compareTwoField(valueone,valuetwo){
	if(isNaN(valueone)){valueone=0;}
	if(isNaN(valuetwo)){valuetwo=0;}
	valueone=parseFloat(valueone).toFixed(2);
	valuetwo=parseFloat(valuetwo).toFixed(2);
	if(parseFloat(valueone)>parseFloat(valuetwo)){
        return true;
	}else{
        return false;
	}
}
//根据网银的余额进行核销
function calculatePlanRent(allMoney, corpus, interest, penalty) {
	var rentlist = {};
	//核销租息
	if (allMoney > 0) {
		if (allMoney - interest >= 0) {
			allMoney = parseFloat(allMoney) - parseFloat(interest);
			allMoney=allMoney.toFixed(2);
		} else {
			interest = allMoney;
			allMoney = 0;
		}
	} else {
		interest = 0;
	}
	//核销本金
	if (allMoney > 0) {
		if (allMoney - corpus >= 0) {
			allMoney = parseFloat(allMoney) - parseFloat(corpus);
			allMoney=allMoney.toFixed(2);
		} else {
			corpus = allMoney;
			allMoney = 0;
		}
	} else {
		corpus = 0;
	}
	//核销罚息
	if (allMoney > 0) {
		if (allMoney - penalty >= 0) {
			allMoney = parseFloat(allMoney) - parseFloat(penalty);
			allMoney=allMoney.toFixed(2);
		} else {
			penalty = allMoney;
			allMoney = 0;
		}
	} else {
		penalty = 0;
	}
	var rent = parseFloat(interest) + parseFloat(corpus);
	rentlist.rent = parseFloat(rent).toFixed(2);
	rentlist.corpus = parseFloat(corpus).toFixed(2);
	rentlist.interest = parseFloat(interest).toFixed(2);
	rentlist.penalty = parseFloat(penalty).toFixed(2);
	rentlist.lastMoney = parseFloat(allMoney).toFixed(2);
	return rentlist;
}
//检验租金计划是否已添加
function isExisted(currTable, rentlist){
	
	var currRowDatas = currTable.getData();
	if(currRowDatas.length==0){return false;}
	for(var i = 0;i < currRowDatas.length; i++){
		if(parseFloat(rentlist) == parseFloat(currRowDatas[i].planlist)){
			return true;
		}
	}
	return false;
}
//获取核销次数
function getHireList(rentlist){
	var hireList = 0;
	var hisTableData = mini.get("rent_income_his").getData();
	var hisLen = hisTableData.length;
	for(var i = 0;i < hisLen; i ++){
		if(hisTableData[i].plan_list == rentlist){
			if(hireList < hisTableData[i].hire_list){
				hireList = hisTableData[i].hire_list;
			}
		}
	}
	return parseInt(hireList) + 1;
}
//检查租金实收
function checkFundRentIncome(tableid){
	 
	   var ebankid="";
	   var plandate="";
	   var param={};
	   if(mini.get("fund_ebank_data.id")){
		   ebankid=mini.get("fund_ebank_data.id").getValue();
		   plandate=mini.get("fund_ebank_data.factdate").getValue();
		   param["ebankid"]=ebankid;
		   param["plan_date"]=plandate;
	   }
	   var flowunid=flowUnid;
	   var chargeTable=mini.get(tableid);
	   var chargeData=chargeTable.getData();
	   var ids=[]; 
	   for(var i=0;i<chargeData.length;i++){
	     ids.push("'"+chargeData[i].planid+"'");
	  }
	  var cflag=true;
	  if(ebankid!=""){;}
	  param["rentplandids"]=ids.join(",");
	  param["flowunid"]=flowunid;
	  var temptableid=tableid;
	  if(ids.length>0){
		  mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'检查租金...'});
		  ajaxRequest({
				url : getRootPath() + "/eleasing/getRentCheckInfo.acl",
				method : 'POST',
				success : function(rs) {
					var returnMessage = rs.responseText;
	                var checkInfo=eval("("+returnMessage+")");
	                if(checkInfo.result=="0"){
	                       mini.alert(checkInfo.message);
	                       cflag= false;
	                }else{
	                     var allsum=0;
	                     var message=[];
	                     for(var i=0;i<chargeData.length;i++){
	                          allsum=parseFloat(allsum)+parseFloat(chargeData[i].corpus)+parseFloat(chargeData[i].interest)+parseFloat(chargeData[i].penalty);
	                          allsum=allsum.toFixed(2);
	                          for(var j=0;j<checkInfo.rent.length;j++){
	                               if(chargeData[i].planid==checkInfo.rent[j].id){
	                                   var tempvalue=parseFloat(checkInfo.rent[j].curcorpusoverage)-parseFloat(chargeData[i].corpus);
	                                   tempvalue=tempvalue.toFixed(2); 
	                            	   if(parseFloat(tempvalue)<0){
	                            		   message.push("第"+chargeData[i].planlist+"期本金("+chargeData[i].corpus+")大于计划余额("+checkInfo.rent[j].curcorpusoverage+")");
	                                       cflag= false;
	                                   }
	                            	   checkInfo.rent[j].curcorpusoverage=tempvalue;
	                            	   tempvalue=parseFloat(checkInfo.rent[j].curinterestoverage)-parseFloat(chargeData[i].interest);
	                                   tempvalue=tempvalue.toFixed(2); 
	 
	                            	   if(parseFloat(tempvalue)<0){
	                            		   message.push("第"+chargeData[i].planlist+"期租息("+chargeData[i].interest+")大于计划余额("+checkInfo.rent[j].curinterestoverage+")");
	                                       cflag= false;
	                                   }
	                            	   checkInfo.rent[j].curinterestoverage=tempvalue;
	                            	   tempvalue=parseFloat(checkInfo.rent[j].penaltyoverage)-parseFloat(chargeData[i].penalty);
	                                   tempvalue=tempvalue.toFixed(2); 
	                            	   if(parseFloat(tempvalue)<0){
	                            		   message.push("第"+chargeData[i].planlist+"期罚息("+chargeData[i].penalty+")大于计划余额("+checkInfo.rent[j].penaltyoverage+")");
	                                       cflag= false;
	                                   }
	                            	   checkInfo.rent[j].penaltyoverage=tempvalue;
	                
	                            	   tempvalue=parseFloat(checkInfo.rent[j].penaltyoverage)-parseFloat(chargeData[i].penaltyadjust);
	                                   tempvalue=tempvalue.toFixed(2); 
	                            	   if(parseFloat(tempvalue)<0){
	                            		   message.push("第"+chargeData[i].planlist+"期罚息减免("+chargeData[i].penaltyadjust+")大于计划余额("+checkInfo.rent[j].penaltyoverage+")");
	                                       cflag= false;
	                                   }
	                            	   checkInfo.rent[j].penaltyoverage=tempvalue;
	                               }
	                          }
	                     }
	                     if(checkInfo.ebank.length>0){
	                           var mayopemoney=checkInfo.ebank[0].mayopemoney;
	                           mayopemoney=parseFloat(mayopemoney);
	                           mayopemoney=mayopemoney.toFixed(2);
	                           if(allsum>parseFloat(mayopemoney)){
	                        	   message.push("本次核销金额"+allsum+"大于网银余额"+mayopemoney);
	                        	   cflag= false;
	                           }
	                     }
	                     if(message.length>0){
                             mini.alert(message.join("<br>"));
		                 }
	                }
					mini.unmask(document.body);
		  },
			async : false,
			failure : function(res) {
			  mini.unmask(document.body);
			},
			params :param
		});
	  }else{
	     mini.alert("请选择本次回笼租金");
	     cflag= false;
	  }	
	  return cflag;
} 
//验证核销不能跳期核，只能按期次向下
function doCheckRentListContinue(tableid1,tableid2)
{
	var plantable = mini.get(tableid1); //租金计划数据
	var plandate=mini.clone(plantable.getData());
	var incomeTable=mini.get(tableid2); //本次租金回笼明细数据
	var incometable=mini.clone(incomeTable.getData());
	var temp=0;
    for(var i=0;i<plandate.length;i++){
         for( var j=0;j<incometable.length;j++){
             if(plandate[i].rentlist==incometable[j].planlist){
                 temp=0;
                 temp=parseFloat(plandate[i].currentoverage)-parseFloat(incometable[j].rent);
                 temp=temp.toFixed(2);
                 plandate[i].currentoverage=temp;
             }   
         } 
    }
    for(var i=0;i<plandate.length-1;i++){
        var j=i+1;
        if(plandate[i].currentoverage>0&&plandate[j].currentoverage!=plandate[j].rent){
        	 mini.alert("不能跳过第"+(i+1)+"期进行隔期核销，请确认,如需请勾选租金回笼计划中的允许隔期回笼！");
             return false;
        }  
    }
	return true;
}
</script>

