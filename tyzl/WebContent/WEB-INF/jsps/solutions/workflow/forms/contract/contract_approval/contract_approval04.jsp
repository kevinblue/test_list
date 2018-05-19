<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
function formPageReadOnlyCallBack(){
	mini.get("contract_number").setRequired();
}
//保存成功提交后，后台返回
function saveCallBack() {
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	//if(checkContractNumberUnique()){return false;}
	if(checkcontractnumber()){
  		return false;
  	}
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
</script>

<!--start 多行控件  -->
<jsp:include page="comm/contract_approval_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->

<div id="contract_approval_form">
	<div class="fillTableContainer"> 
		<jsp:include page="../contract_comm/contract_base_info.jsp">
		   <jsp:param value="true" name="isView"/></jsp:include>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
		<div title="租赁物明细" name="contract_equip_detail" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" >
					<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ><jsp:param value="true" name="isView"/></jsp:include>
		</div>
		<div title="其它商务条件" name="otherBusinessCondition" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_other_business_condition.jsp" >
			    <jsp:param value="true" name="isView"/>
		    </jsp:include>
		</div>
		<div title="担保单位信息" name="contract_guarantee_method" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_method.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="抵押物信息" name="contract_guarantee_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_guarantee_equip.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="签约信息" name="signInfo" iconCls="icon-node">
			<jsp:include page="../contract_comm/sign_info.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		
		<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../contract_comm/contract_union_cust.jsp" >
		     	<jsp:param value="true" name="isView"/>
		     </jsp:include>
	     </div> 
	</div>
</div>