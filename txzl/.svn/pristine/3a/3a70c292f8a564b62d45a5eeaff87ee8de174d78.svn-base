<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input style="display:none;"	class="mini-textarea" id="id_contractidselect_str" name="json_leasing_info_list_str" value='${contractidselect}'></input>
<input style="display:none;"	class="mini-textarea" id="id_custid_str" name="json_leasing_info_list_str" value='${custid}'></input>
<input style="display:none;"	class="mini-textarea" id="id_projid_str" name="json_projid_str" value='${projid}'></input>
<input style="display:none;"	class="mini-textarea" id="id_lastyear_str" name="json_leasing_info_list_str" value='${lastyear}'></input>
<input style="display:none;"	class="mini-textarea" id="id_twoyearago_str" name="json_leasing_info_list_str" value='${twoyearago}'></input>
<input style="display:none;"	class="mini-textarea" id="id_threeyearago_str" name="json_leasing_info_list_str" value='${threeyearago}'></input>
<input style="display:none;"	class="mini-textarea" id="id_custid_str" name="json_custid_str" value='${custid}'></input>
<input style="display:none;"	class="mini-textarea" id="id_guarantee_str" name="json_guarantee_str" value='${guarantee}'></input>
<input style="display:none;"	class="mini-textarea" id="id_industryType_str" name="json_industryType_str" value='${industryType}'></input>
<script type="text/javascript">
	var quarter="${requestScope['quarter']}";
    var contractidselect = $("#id_contractidselect_str").val();
    var custid = $("#id_custid_str").val();
    var thisyear = ${requestScope['thisyear']};
    var lastyear = $("#id_lastyear_str").val();
    var twoyearago =$("#id_twoyearago_str").val();
    var threeyearago = $("#id_threeyearago_str").val();
   // var quarter = ${requestScope['quarter']};
    var custid = $("#id_custid_str").val();
    var projid=$("#id_projid_str").val();
   // mini.alert(projid);
    var guarantee = $("#id_guarantee_str").val();
    var industryType = $("#id_industryType_str").val();
    
    var quarterStart1;
	var quarterEnd1;
	var quarterStr1;
	var thisstart1;
	var thissend1;
    if(quarter=="1"){
	    quarterStart1="-01-01";
		quarterEnd1= "-03-31";
		quarterStr1="一季度";
	}else if(quarter=="2"){
		quarterStart1="-04-01";
		quarterEnd1="-06-30";
		quarterStr1="二季度";
	}else if(quarter=="3"){
		quarterStart1="-07-01";
		quarterEnd1="-09-30";
		quarterStr1="三季度";
	}else{
		quarterStart1="-10-01";
		quarterEnd1="-12-31";
		quarterStr1="四季度";
	}
    thisstart1=""+thisyear+quarterStart1;
    thissend1=""+thisyear+quarterEnd1;
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
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		//if (miniui_ext.submitFormValidation(["contract_patrol_plan_form"]) == false) return;
		miniui_ext.submitData("tabDetails");
		return true;
	}
	
	function fillDebtJsonData(){
		mini.parse();
		var grid =mini.get("loan_task");
		mini.get("id_json_loan_task_str").setValue(mini.encode(grid.getData()));
		
		var grid1 =mini.get("channel_task");
		mini.get("id_json_channel_task_str").setValue(mini.encode(grid1.getData()));
		
		var grid2 =mini.get("channel_type_task");
		mini.get("id_json_channel_type_task_str").setValue(mini.encode(grid2.getData()));
		
		var grid3 =mini.get("channel_type_other_task");
		mini.get("id_json_channel_type_other_task_str").setValue(mini.encode(grid3.getData()));
		
	}
	
</script>
<div id="contract_patrol_form" title="租后巡视">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td ><jsp:include page="contract_patrol_info.jsp"><jsp:param value="true" name="isView"/></jsp:include></td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;">
		<div title="巡视基本信息" name="contract_patrol_plan_form" iconCls="icon-node">
			<jsp:include page="comm/contract_patrol_plan_form.jsp" ></jsp:include>
		</div>
		
		<%-- <div title="项目运营情况" name="operate_info_list" iconCls="icon-node">
			<jsp:include page="comm/medical_operate_info_list.jsp" ></jsp:include>
		</div>
		 --%>
		 <c:if  test="${requestScope['industryType']=='风力发电'||requestScope['industryType']=='光伏发电'}">
				 <div title="近期发电量统计" name="leasing_info_list" iconCls="icon-node">
				 <jsp:include page="comm/power_generation_statistics.jsp" ></jsp:include>
				 <%-- <jsp:include page="comm/power_generation_statistics_lastyear.jsp" ></jsp:include> --%>
				 </div>
				 <div title="发电情况统计" name="produce_electric_info_list" iconCls="icon-node">
					<jsp:include page="comm/produce_electric_info_list.jsp" ></jsp:include>
				 </div>
		</c:if> 
		<%-- <div title="承租人重大投资事项" name="other_zdtz_info_list" iconCls="icon-node">
			<jsp:include page="comm/other_zdtz_info_list.jsp" ></jsp:include>
		</div> --%>
		
		<%-- <div title="承租人负债情况" name="debt_info_list" iconCls="icon-node">
			<jsp:include page="comm/debt_info_list.jsp" ></jsp:include>
		</div> --%>
		
		<div title="租赁物情况" name="leasing_info_list" iconCls="icon-node">
			<jsp:include page="comm/leasing_insurance_list.jsp" ></jsp:include>
		</div> 
		
		<div title="抵质押情况" name="mortgage_info_list" iconCls="icon-node">
			<jsp:include page="comm/mortgage_info_list.jsp" ></jsp:include>
		</div>
		
		
		<%-- <div title="八、担保人情况" name="guaratee_info_list" iconCls="icon-node">
			<jsp:include page="comm/guaratee_info_list.jsp" ></jsp:include>
		</div> --%>
		
		<div title="重点关注事项" name="risk_info_list" iconCls="icon-node">
			<jsp:include page="comm/risk_info_list.jsp" ></jsp:include>
		</div>
		
		<div title="本次检查评估结论" name="curpatrol_info_list" iconCls="icon-node">
			<jsp:include page="comm/curpatrol_info_list.jsp" ></jsp:include>
		</div>
			<div title="其他文件" name="proj_accessories_report_list"
					iconCls="icon-cut">
					<jsp:include
						page="comm/proj_other_report_list.jsp"></jsp:include>
				</div>
		
		<div title="生成租后检查报告" name="create_report_list" iconCls="icon-node">
			<%-- <jsp:include page="comm/create_report_list.jsp" ></jsp:include> --%>
			<jsp:include page="../../../../report/windfarmreport/generate_wind_farm_upfile.jsp" ></jsp:include>
		</div>
		<div title="财务报表" name="custFinancialReport" iconCls="icon-cut">
				<jsp:include page="comm/cust_finance_report_newnew.jsp"></jsp:include>
		</div>
	</div>
</div>