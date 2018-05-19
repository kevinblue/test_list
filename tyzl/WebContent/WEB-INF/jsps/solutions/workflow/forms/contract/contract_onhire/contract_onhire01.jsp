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
 	return true;
}

	//本次投放明细 设备序列号不能为空
function checkEquipID(){
	var t1 = mini.get("contract_equip").getData();
	for(var i=0;i<t1.length;i++){
		if((!t1[i].equipid)){
			mini.alert("请填写设备序列号！");
			return false;
		}
	}
	return true;
}



	function isRepeat() {
		var t1 = mini.get("contract_equip").getData();
		var hash = [];
		var idArrays = [];
		for (var i = 0; i < t1.length; i++) {
			hash.push("'" + t1[i].equipid + "'");
			idArrays.push("'" + t1[i].id + "'");
		}
		var equipids = hash.join(",")
		var s = hash.join(",") + ",";
		for (var i = 0; i < hash.length; i++) {
			if (s.replace(hash[i] + ",", "").indexOf(hash[i] + ",") > -1) { //匹配字符串S中当前i的数组值，并替换为空；在当前循环里查看S中是否有重复
				mini.alert("设备序列号重复，请重新填写！");
				return "repeat";
			}
		}
		var ids = idArrays.join(",");
		var str = "";
		$.ajax({
			url : getRootPath() + "/acl/checkEquipSerial.acl",
			type : "post",
			cache : false,
			data : {
				"ids" : ids, "equipids" : equipids
			},
			async : false,
			success : function(text) {
				if ("repeat" == text) {
					mini.alert("设备序列号重复，请重新填写！");
				}
				str = text;
			}
		});
		return str;
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
			<jsp:include page="../contract_comm/contract_equip_detail.jsp">
			   <jsp:param value="true" name="isView"/>
			</jsp:include>
			<%--  <jsp:param value="edit" name="buttons"/> --%>
		</div>
		<div title="商务条件" name="business_condition" iconCls="icon-node">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" >
			<jsp:param value="true" name="isView"/>
			<jsp:param value="true" name="isOnhire"/></jsp:include>
		</div>
		<div title="资金收付款情况" name="fund_plan_old" iconCls="icon-node">
			<jsp:include page="../../fund/fund_comm/fund_fund_plan_info.jsp" ></jsp:include>
		</div>
	<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		     <jsp:include page="../contract_comm/contract_union_cust.jsp" >
		      <jsp:param value="true" name="isView"/>
		     </jsp:include>
	</div>
	<div title="起租通知书" name="fund_plan_old" iconCls="icon-node">
			<jsp:include page="start_rent_notice.jsp" ></jsp:include>
		</div>
</div>
