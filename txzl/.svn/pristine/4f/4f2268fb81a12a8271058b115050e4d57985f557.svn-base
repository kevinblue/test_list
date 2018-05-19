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
			<td colspan="4">
				<jsp:include page="../rent_common/condition_cust_num.jsp"></jsp:include>
			</td>
		<tr>
		<tr>
			<td colspan=4>
				<jsp:include page="condition_frame_5_1.jsp"></jsp:include>
		</td>
		</tr>
		 <tr>
			 <td colspan=4>
				<div class="mini-panel" title="租金计划" showCollapseButton="true" style="width: 100%;">
				  	 <div id="conditionDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab"  style="width: 100%;" bodyStyle="padding:0;border:0;">
					  	 <div title="租金计划" name="fund_rent_plan" iconCls="icon-cut" >
							 <jsp:include page="fund_rent_plan_frame.jsp" ></jsp:include>
						</div> 
						 <div title="现金流" name="fund_cash_flow" iconCls="icon-cut">
							<jsp:include page="fund_cash_plan_frame.jsp" ></jsp:include>
						</div>
						<div title="资金收付计划" name="fund_fund_charge" iconCls="icon-cut">
							<jsp:include page="fund_fund_plan.jsp" ></jsp:include>
						</div> 
					</div>
				</div>
			</td> 
		</tr> 
	</table>
</div>
<script type="text/javascript">
var form = new mini.Form("businessconditionForm");
//获取对象的数值

jQuery(function(){
	//加载客户报价参数
	mini.get('conditionCustId').setValue(custid);
	mini.get('custname').setValue();
	mini.get('reckonProcess').setValue('quoted_price');
	$miniDisable('cust_doc_id');
	//同时将所有readOnly 的表单域，背景变成灰色
	var submitForm = document.getElementById("conditionForm");
	var formElements = submitForm.elements;
	
	for (var i = 0; i < formElements.length; i++) {
         var formElement = formElements[i];
       	 var flag = formElement.readOnly;
         if (flag) {
        	 var currentElementType = formElement.type;
             if (formElement.className.indexOf("Wdate") > -1) {
                 formElement.onclick = null;
	             removeClass(formElement, "Wdate");
             }
             if(formElement.type != 'button' && formElement.className.indexOf('mini-textbox-input') > -1){
	            addClass(formElement.parentNode, "element-readonly");
             }
         }
     }
	
});



</script>

