<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
		if (miniui_ext.submitFormValidation(["contract_rent_invoice_type_form"]) == false) return false;
		//json域 保存
		miniui_ext.saveIncludeData("tabApprovalDeatils");
		if(checkFundPutInfo() == false) return false ;
		//判断资金计划明细和本次收款明细总金额
        var  cfalg=checkFundFundCharge("put_current");
        if(!cfalg){return false;}
        //判断货扣资金
        if(mini.get("caution_money_refund_detail").getData().length>0){
            var  cfalg=checkFundFundCharge("caution_money_refund_detail");
            if(!cfalg){return false;}
        }
        //判断货扣租金
/*          if(mini.get("rent_income_detail").getData().length>0){
            var cflag=checkFundRentIncome("rent_income_detail");//合数据库进行比较
    	    if(!cflag){return false;}
         } */
    	//设备款中的抵扣金额和资金和租金中是不是一样
        var cflag=CheckDecdueMoney();
        if(!cflag){return false;}
        if(!checkClientBank())return false;
        if(!checkAccBank())return false;
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
	//本次投放明细 本方银行信息不能为空
	function checkAccBank(){
		var flag = true;
		var t1 = mini.get("put_current").getData();
		for(var i=0;i<t1.length;i++){
			if(!t1[i].accountbank|| !t1[i].account|| !t1[i].accnumber){
				flag = false;
			}
		}
		if(flag == false){
			mini.alert("请在本次投放明细中填写本方银行信息！");
		}
		return flag;
	}
</script>
<!--多行控件  -->
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_word_fundput_str" name="json_contract_word_fundput_str" value='${empty json_contract_word_fundput_str ? "[]" : json_contract_word_fundput_str }'>
<input  style="display:none;"	class="mini-textarea" id="id_json_premise_condition_str" name="json_premise_condition_str" value='${empty json_premise_condition_str ? "[]" : json_premise_condition_str }'>
<input  style="display:none;"	class="mini-textarea" id="id_json_contract_equip_str" name="json_contract_equip_str" value='${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_plan_str" name="json_put_plan_str" value='${empty json_put_plan_str ? "[]" : json_put_plan_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_his_str" 	name="json_put_his_str" value='${empty json_put_his_str ? "[]" : json_put_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_collection_his_str" 	name="json_collection_his_str" value='${empty json_collection_his_str ? "[]" : json_collection_his_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_put_current_str" 	name="json_put_current_str" value='${empty json_put_current_str ? "[]" : json_put_current_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_rent_income_detail_str" name="json_rent_income_detail_str" value='${empty json_rent_income_detail_str ? "[]" : json_rent_income_detail_str }'></input>
<input  style="display:none;"	class="mini-textarea" id="id_json_caution_money_refund_detail_str" name="json_caution_money_refund_detail_str" value='${empty json_caution_money_refund_detail_str ? "[]" : json_caution_money_refund_detail_str }'></input>

<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 合同基本信息 --> 
			 <jsp:include page="../../contract/contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	   <tr>
			<td >
				<!-- 流程备注 --> 
				<jsp:include page="comm/fund_put_process_note.jsp"></jsp:include>
			</td>
		</tr>
	 </table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="padding:0;border:0;">
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="comm/contract_equip_detail1.jsp" ></jsp:include>
		</div>
	
	<div title="投放计划明细" name="put_plan" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_plan_info.jsp" >
	     <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>
	<div title="已投放明细" name="put_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_his_info.jsp" ></jsp:include>
	</div>
	<div title="资金收款历史明细" name="collection_his" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_collection_his_info.jsp" >
	     <jsp:param value="true" name="isView"/>
	     </jsp:include>
	</div>
	<div title="本次投放明细" name="put_current" iconCls="icon-expand">
	     <jsp:include page="fund_put_current_info03.jsp" >
	     <jsp:param value="false" name="isView"/>
	     </jsp:include>
	</div>
	<div title="坐扣资金明细" name="caution_money_refund_detail" iconCls="icon-node">
		  <jsp:include page="comm/caution_money_refund_detail.jsp" ></jsp:include>
	</div>	
 	<div title="坐扣租金明细" name="rent_income_detail" iconCls="icon-node">
		<jsp:include page="comm/rent_income_detail_fund.jsp" ></jsp:include>
	</div> 
	 <div title="付款前提条件" name="premise_condition" iconCls="icon-node">
		<jsp:include page="../../fund/fund_comm/premise_condition.jsp" >
		<jsp:param value="false" name="isView"/>
		</jsp:include>
	</div>
    <div title="面签合同" name="contract_word_fundput" iconCls="icon-node">
		<jsp:include page="comm/contract_word_fundput.jsp" >
			<jsp:param value="true" name="isView"/>
		</jsp:include>
	 </div> 
	<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
		<jsp:include page="../fund_comm/contract_received_invoice_type.jsp" ></jsp:include>
	</div>
	<div title="放款资料清单" name="fund_put_file_list" iconCls="icon-node">
		<jsp:include page="fund_put_file_list.jsp" ></jsp:include>
	</div>
</div>
<jsp:include page="comm/fund_deduction.jsp"></jsp:include>