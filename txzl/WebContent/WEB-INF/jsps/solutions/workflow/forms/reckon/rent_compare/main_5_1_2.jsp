<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	
	 #reckonContainerCompare .mini-textbox {
		width :125px;
	}
	
	#reckonContainerCompare .mini-buttonedit {
		width :125px;
	} 
</style>
<div class="fillTableContainer" id="reckonContainerCompare">
	<table class="fillTable" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1_2.jsp"></jsp:include>
		</td>
		</tr>
		<tr>
			 <td colspan=4>
			 	<div id="segment_config" class="mini-panel" title="分段配置" showCollapseButton="true" style="width: 100%;">
				  	 <div id="configDeatils" class="mini-tabs" activeIndex="0"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="配置信息" name="special_regular_new" iconCls="icon-cut">
							<jsp:include page="../rent_common/special_regular_new.jsp" ></jsp:include>
						</div> 
					</div>
				</div>			 
				<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
				  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0"  onactivechanged="changTab"   style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	<div title="投放计划" name="fund_put_plan_new" iconCls="icon-cut" >
							 <jsp:include page="../rent_common/fund_put_plan_new.jsp" ></jsp:include>
						</div> 
					  	 <div title="租金计划" name="fund_rent_plan_new" iconCls="icon-cut" >
							 <jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
						</div> 
						 <div title="现金流" name="fund_cash_flow_new" iconCls="icon-cut">
							<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
						</div>
						 <div title="资金收付计划" name="fund_fund_charge_new" iconCls="icon-cut">
							<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
						</div>
						<div title="宽限期收款计划" name="grace_plan_new" iconCls="icon-cut">
							<jsp:include page="../rent_common/grace_plan_new.jsp" ></jsp:include>
						</div>    
						 <div title="付款前提条件" name="payment_premise_condition_new" iconCls="icon-cut">
							<jsp:include page="payment_premise_condition.jsp" ></jsp:include>
						</div>   
					</div>
				</div>
			</td> 
		</tr>
	</table>
</div>


