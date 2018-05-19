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
		<%-- <c:if test="${currentDeployId eq '1'}">
			<tr>
				<td colspan="4">
					<jsp:include page="../rent_common/condition_cust_num.jsp"></jsp:include>
				</td>
			<tr>
		</c:if> --%>
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1.jsp"></jsp:include>
		</td>
		</tr>
		 <tr>
			 <td colspan=4>
			 	<div id="segment_config" class="mini-panel" title="分段配置" showCollapseButton="true" style="width: 100%;">
				  	 <div id="configDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="配置信息" name="special_regular" iconCls="icon-cut">
							<jsp:include page="../rent_common/special_regular.jsp" ></jsp:include>
						</div> 
					</div>
				</div>
				<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
				  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	 <div title="投放计划" name="fund_put_plan" iconCls="icon-cut" >
							 <jsp:include page="../rent_common/fund_put_plan.jsp" ></jsp:include>
						</div> 
					  	 <div title="租金计划" name="fund_rent_plan" iconCls="icon-cut" >
							 <jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
						</div> 
						 <div title="现金流" name="fund_cash_flow" iconCls="icon-cut">
							<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
						</div>
						<div title="资金收付计划" name="fund_fund_charge" iconCls="icon-cut">
							<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
						</div> 
						<div title="宽限期收款计划" name="fund_fund_charge" iconCls="icon-cut">
							<jsp:include page="grace_plan.jsp" ></jsp:include>
						</div> 
						<div title="付款前提条件" name="payment_premise_condition" iconCls="icon-cut">
							<jsp:include page="payment_premise_condition.jsp" ></jsp:include>
						</div>  
					</div>
				</div>
			</td> 
		</tr> 
	</table>
</div>
<script type="text/javascript">
function checkCalIsSame(){
	var fields = businessForm.getFields();
	var fundFundPlans = mini.decode($('#id_json_fund_fund_charge_str').val());
	if(!fundFundPlans || fundFundPlans.length==0){
		mini.alert('请先生成租金计划！');
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
    /* 	for(var i = 0 ; i < o.length ; i++){
    		if(o[i].uiCls == "mini-textbox" && fields[i].vtype == 'double' && fields[i].label){
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
    		}
    	} */
    	
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

var form = new mini.Form("businessconditionForm");


</script>
</script>

