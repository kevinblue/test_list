<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	miniui_ext.saveIncludeData("tabDetails");
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)
{
    if(checkEquipIsNull()==false) return;
    miniui_ext.saveIncludeData("tabDetails");
    if(checkPercent() == false) return false;
    return true;
}
function checkPercent(){
	 //检验分配比例四项之和必须为100%
	 var referee_Proportion = parseFloat(mini.get("referee_Proportion_id").getValue());
	 var hoster_Proportion = parseFloat(mini.get("hoster_Proportion_id").getValue());
	 var assistant_Proportion = parseFloat(mini.get("assistant_Proportion_id").getValue());
	 var third_Party_Proportion = parseFloat(mini.get("third_Party_Proportion_id").getValue());
	 var referee_idValue=mini.get("referee").getValue();
	 var hoster_idValue=mini.get("rawValue_contract_info.projmanage").getValue();
	 var assistant_idValue=mini.get("assistant").getValue();
	 var thirdParty_idValue=mini.get("thirdParty").getValue();
	 var sum=referee_Proportion+hoster_Proportion+assistant_Proportion+third_Party_Proportion;
	 if (sum != 100) {
			mini.alert("分配比例之和必须为100%，请修改");
			return false;
	 }  if (referee_idValue !="") {
			if (referee_Proportion == 0 || referee_Proportion == "") {
				mini.alert("推荐人的分配比例不能为空或者为0");
				return false;
			} 
	       }
	    if (hoster_idValue =="") {
				mini.alert("主办方不能为空");
				return false;
			}  if (hoster_idValue != "") {
				if (hoster_Proportion == 0 && hoster_Proportion == "") {
					mini.alert("主办方的分配比例不能为空或者为0");
					return false;
				}
			}  if (assistant_idValue != "") {
				if (assistant_Proportion == 0 && assistant_Proportion == "") {
					mini.alert("协办方的分配比例不能为空或者为0");
					return false;
				}
			} if (thirdParty_idValue != "") {
				if (third_Party_Proportion == 0 && third_Party_Proportion == "") {
					mini.alert("第三方的分配比例不能为空或者为0");
					return false;
				}
			}
		return true;
	}




</script>

<!--start 多行控件  -->
<jsp:include page="comm/contract_onhire_mutli_info.jsp" ></jsp:include>
<!--end 多行控件  -->

<div class="contract_onhire_form">
	<jsp:include page="../contract_comm/contract_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="投放明细" name="fund_put" iconCls="icon-expand">
	     <jsp:include page="../../fund/fund_comm/fund_put_cur_money.jsp" ></jsp:include>
		</div>
		<div title="租赁物明细" name="contract_equip" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_equip_detail.jsp" >
			     <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_onhire/main_5_1.jsp" >
			    <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="租金开票类型" name="rentInvoiceType" iconCls="icon-node">
			<jsp:include page="../contract_comm/contract_rent_invoice_type.jsp" >
			    <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<!-- 项目考核 -->
		 <div title="项目考核" name="proj_Check" iconCls="icon-node">
		   <jsp:include page="../../fund/fund_comm/proj_Check.jsp"></jsp:include>
		 </div> 
				<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../contract_comm/contract_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
    </div> 
		
	</div>
</div>
