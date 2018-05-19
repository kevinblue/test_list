<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		//json域 保存
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		if(checkFundPutInfo() == false) return false ;
		   
		//检查本次手续费、服务费、保证金投放金额不能大于余额
		if(checkFundPut() == false) return false ;
		if(checkFundCashEmpty()==false) return false;

    	//设备款中的抵扣金额和资金和租金中是不是一样
        var cflag=CheckDecdueMoney();
        if(!cflag){return false;}
        if(!checkClientBank())return false;
	    return true;
	}
	//本次投放明细 对方银行信息不能为空
	function checkClientBank(){
		var flag = true;
		var t1 = mini.get("put_current").getData();
		for(var i=0;i<t1.length;i++){
			if(t1[i].settlemethod== "payfund6" && (!t1[i].clientbank|| !t1[i].clientaccount|| !t1[i].clientaccnumber)){
				flag = false;
			}
		}
		if(flag == false){
			mini.alert("请填写对方银行信息！");
		}
		return flag;
	}
	//校验资金计划现金流是否为空
	function checkFundCashEmpty(){
		var flag=true;
		var fundplan=mini.get("fund_fund_plan").getData();
		var cash=mini.get("fund_cash_plan_frame").getData();
		if(fundplan.length<=0){
			flag=false;
			mini.alert("请先生成费用表！");
		}else if(cash.length<=0){
			flag=false;
			mini.alert("请先生成现金流表！");
		}
		return flag;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_put_plan_str" name="json_put_plan_str" value='<mini:param  name="json_put_plan_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_his_str" 	name="json_put_his_str" value='<mini:param  name="json_put_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='<mini:param  name="json_collection_his_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_current_str" 	name="json_put_current_str" value='<mini:param  name="json_put_current_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_detail_str" name="json_rent_income_detail_str" value='<mini:param  name="json_rent_income_detail_str" defaultValue="[]"/>'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_caution_money_refund_detail_str" name="json_caution_money_refund_detail_str" value='<mini:param  name="json_caution_money_refund_detail_str" defaultValue="[]"/>'></input>

<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="padding:0;border:0;">
	<div title="本次投放金额" name="put_money" iconCls="icon-node">
			<jsp:include page="comm/fund_put_cur_money.jsp" ></jsp:include>
	</div>
	<div title="本次投放商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" >
			<jsp:param value="true" name="isput"/>
			</jsp:include>
		</div>	
<!-- 	<div title="投放计划明细" name="put_plan" iconCls="icon-expand"> -->
<%-- 	     <jsp:include page="../../fund/fund_comm/fund_put_plan_info.jsp" > --%>
<%-- 	     <jsp:param value="false" name="isView"/> --%>
<%-- 	     </jsp:include> --%>
<!-- 	</div> -->
	<div title="本次投放明细" name="put_current" iconCls="icon-expand">
	     <jsp:include page="comm/fund_put_current_manage.jsp" >
	     <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>
	<div title="货扣资金明细" name="caution_money_refund_detail" iconCls="icon-node">
		  <jsp:include page="comm/caution_money_refund_detail.jsp" ></jsp:include>
	</div>	
	
	<div title="资金收款历史明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="已投放明细" name="put_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_his_info.jsp" ></jsp:include>
	</div>
	<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../fund_comm/fund_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
    </div> 
</div>
<jsp:include page="comm/fund_deduction.jsp"></jsp:include>