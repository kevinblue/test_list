<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		</td>
		</tr>
		<tr>
			 <td colspan=4>
			     <div id="segment_config" class="mini-panel" title="分段配置" showCollapseButton="true" style="width: 100%;">
				  	 <div id="configDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="配置信息" name="special_regular" iconCls="icon-cut">
							<jsp:include page="../rent_common/special_regular.jsp" >
							<jsp:param value="true" name="isView"/>
							</jsp:include>
						</div> 
					</div>
				</div>
			 
				<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
				  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0"  onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	 <div title="投放计划" name="fund_put_plan" iconCls="icon-cut" >
							 <jsp:include page="../rent_common/fund_put_plan.jsp" >
							 <jsp:param value="true" name="isView"/></jsp:include>
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
							<jsp:include page="payment_premise_condition.jsp" >
							<jsp:param value="true" name="isView"/>
							</jsp:include>
						</div> 
					</div>
				</div>
			</td> 
		</tr>
	</table>
</div>
<script type="text/javascript">
var form = new mini.Form("businessconditionFormOnhireOnhire");
//获取对象的数值
function getNumber(id){
    var value = mini.get(id).getValue();
    try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}
function decimalReal(num,v)
{
   var dight  =(num*Math.pow(10,v)/Math.pow(10,v)).toFixed(v);  
   return parseFloat(dight);
} 
//四舍五入
function decimal(num,v)
{	//num表示要四舍五入的数,v表示要保留的小数位数。
	if(0 == v)
	{
		return decimalReal(decimalReal(num,2),0);
	}
    return decimalReal(num,v);		
}
function $minigetvalue(id){
	return mini.get(id).getValue();
}
function $mini(id){
	return mini.get(id);
}
function $minigetinputtext(id){
	var value = mini.get(id).getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0; 
    }
    return value;
}
</script>

