<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<style>
	#reckonContainer .mini-panel-header{
		background :rgb(74,165,219);
	}
	 #reckonContainer .mini-textbox {
		width :125px;
	}
	
	#reckonContainer .mini-buttonedit {
		width :125px;
	} 
</style>
<div class="fillTableContainer" id="reckonContainer">
	<table class="fillTable" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1.jsp"></jsp:include>
				<jsp:include page="../rent_common/rent_custom_info.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask == true){showTools = false;};
});
var form = new mini.Form("businessconditionForm");
function checkCalIsSame(){
	var fields = businessForm.getFields();
	var fundFundPlans = mini.decode($('#id_json_fund_fund_charge_str').val());
	var cashflow = mini.decode($('#id_json_fund_cash_flow_str').val());
	if(!cashflow || cashflow.length==0){
		mini.alert('请更新现金流！');
		return false;
	}else if(!fundFundPlans || fundFundPlans.length==0){
		mini.alert('请先生成费用表！');
		return false;
	}
	if(fundFundPlans && fundFundPlans.length > 0 ){
		var merageFundFundPlans = new Array();
		merageFundFundPlans[0] =  fundFundPlans[0];
		for(var i = 1 ; i < fundFundPlans.length ; i++){
			for(var j=0;j<merageFundFundPlans.length;j++){
				if(fundFundPlans[i].feetypename == merageFundFundPlans[j].feetypename){
					merageFundFundPlans[j].planmoney
					= decimal(Number(merageFundFundPlans[j].planmoney)
					+Number(fundFundPlans[i].planmoney),2);
					break;
				}else if(j==merageFundFundPlans.length-1){
					merageFundFundPlans.push(fundFundPlans[i]);
					break;
				}
			}
		}
		var htmlArr = [];
		var checkResult = true;
		htmlArr.push("<div id='calculateCheckTable'  class='mini-window' title='测算资金计划不一致提示' style='width:700px;' showModal='true' allowResize='true' allowDrag='true'><div class='mini-fit' style='padding:10px 30px;overflow-y:hidden;'>");
    	htmlArr.push("<table class='fillTable' align='center' cellspacing='0' cellpadding='0' style='border:1px solid #cccccc;'>");
    	htmlArr.push("<tr class='tr-project-info'><td colspan=2 style='font-size:15px;font-weight:bold;border-bottom:1px dotted #cccccc;width:150px;text-align:center;'>租金测算提示</td></tr>");
    	for(var i = 0 ; i < merageFundFundPlans.length ; i++){
			var isCheckflag = false;
			var fundFundPlan = merageFundFundPlans[i];
			if(typeof(fundFundPlan.fieldname) != undefined){
				var funPlanMoney ;
				try{
					funPlanMoney =  $minigetinputtext(fundFundPlan.fieldname);
				}catch(e){
					funPlanMoney =  $('#'+fundFundPlan.fieldname).val();
				}
				if(fundFundPlan.planmoney != funPlanMoney){
					isCheckflag = true;
				}
			}else{
				return true;
			}	
			if(isCheckflag){
				htmlArr.push("<tr class='tr-project-info'>");
				htmlArr.push("<td style='border-left:1px dotted #cccccc;border-bottom:1px dotted #cccccc;color:red;height:25px;line-height:25px;text-indent: 5px;'>" + fundFundPlan.feetypename + "</td>");
				htmlArr.push("<td style='border-left:1px dotted #cccccc;border-bottom:1px dotted #cccccc;color:red;height:25px;line-height:25px;text-indent: 5px;'>" + fundFundPlan.feetypename + "商务报价和资金计划不一致，请核对后修改！</td>");
				htmlArr.push("<tr/>");
				checkResult = false;
			}
		}
    	//同时检测商务报价中的资金与资金计划中的计划还款金额是否相同
    	
    	var o = businessForm.getFields();;
    	for(var i = 0 ; i < o.length ; i++){
    		/* if(o[i].uiCls == "mini-textbox" && fields[i].vtype == 'double' && fields[i].label){
    			if(o[i].getValue() && Number(o[i].getValue().replace(/,/g,"")) > 0){
    				var conditionMoney = Number(o[i].getValue().replace(/,/g,""));
    				var isCheckflag = false;
    				var isContainFlag = false;
    				for(var j = 0 ; j < merageFundFundPlans.length ;j++){
    					if(o[i].name == merageFundFundPlans[j].fieldname){
    						isContainFlag = true;
    					}
    					if(o[i].name == merageFundFundPlans[j].fieldname && conditionMoney != Number(merageFundFundPlans[j].planmoney)){
    						isCheckflag = true;
    						break;
    					}
    				}
    				
    				if(!isContainFlag ||  isCheckflag){
	    				htmlArr.push("<tr class='tr-project-info'>");
	    				htmlArr.push("<td style='border-left:1px dotted #cccccc;border-bottom:1px dotted #cccccc;color:red;height:25px;line-height:25px;text-indent: 5px;'>" + fields[i].label + "</td>");
	    				htmlArr.push("<td style='border-left:1px dotted #cccccc;border-bottom:1px dotted #cccccc;color:red;height:25px;line-height:25px;text-indent: 5px;'>" + fields[i].label + "商务报价和资金计划不一致，请核对后修改！</td>");
	    				htmlArr.push("<tr/>");
	    				checkResult = false;
    				}
    			}
    		} */
    	}
    	
		if(!checkResult){
			htmlArr.push("<tr class='tr-project-info'><td colspan=2 style='font-size:15px;font-weight:bold;border-bottom:1px dotted #cccccc;width:150px;text-align:center;'><a class='mini-button' onclick=closeErrorWindow('calculateCheckTable') style='width:80px'>确定</a></td></tr>");
			htmlArr.push( "</table>");
			htmlArr.push("</div>");
	    	htmlArr.push("</div>");
	    	var $errorPopupWindow = jQuery(htmlArr.join(""));
			jQuery(document.body).append($errorPopupWindow);
			jQuery('#calculateCheckTable').append();
	    	mini.parse($errorPopupWindow);
	    	var editWindow = mini.get("calculateCheckTable");
			editWindow.show();
		}
		return checkResult;
	}
	
}

function calChangTab(e){
		try{
		//如果租前息为空或者是0  则计算租前息：(起租日期-投放日期)*日利率*融资额/100
		var beforeInterest=mini.get("beforeinterest").getValue();
		//if(beforeInterest==0||beforeInterest==""){
			var startdate=mini.get("startdate").getValue();
			var leaseamtdate=mini.get("leaseamtdate").getValue();
			var dayrate=mini.get("yearrate").getValue();
			var cleanleasemoney=mini.get("cleanleasemoney").getValue().replace(/,/g,"");
			var days=GetDateDiff(startdate,leaseamtdate);
			mini.get("beforeinterest").setValue(decimal(days*dayrate/360*cleanleasemoney/100,2));
		//}
		}catch(e){}
	
}

function GetDateDiff(startDate,endDate)  
{
	if(startDate==""||endDate==""){
		return 0;
	}else{
	    var startTime = startDate.getTime();     
	    var endTime = endDate.getTime();     
	    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
	    return  dates;    
	}
}
</script>

