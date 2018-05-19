<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="/WEB-INF/tlds/c.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		miniui_ext.saveIncludeData("tabDetailsBase");
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{ 
		if(mini.get("limittype") && "limit_type.zh" != mini.get("limittype").getValue()){
			//综合授信不校验租赁物
	       if(checkEquipIsNull()==false) return;
			if(mini.getbyName('rawValue_proj_info.leasform').getValue()=="直租"){
	        	var data=mini.get('proj_equip').getData();
	        	for(var i=0;i<data.length;i++){
	        		if(data[i].vndrtype==""){
	        			mini.alert("直租时，请在租赁物明细中，填写贸易类型！");
	        			return false;
	        		}
	        		return true;
	        	}
	        };
		}
		if(!checkCalIsSame()){return ;}
		if (miniui_ext.submitFormValidation(["proj_base_info_form","div_limit_info"]) == false) return false;
        miniui_ext.saveIncludeData("tabDetailsBase");
		return true;
	}
</script>
	<div id="contract_approval_form">
		<div class="fillTableContainer" >
			<table class="fillTable" cellspacing="0" cellpadding="0">
					<tr><td> <jsp:include page="../proj_common/proj_base_info.jsp"></jsp:include></td></tr>
					<tr>
						<td colspan=4> <jsp:include page="../proj_credit/common/proj_limit_choose.jsp"></jsp:include></td>
					</tr>
			</table>
		</div>
		<div class="mini-tabs" activeIndex="0"   style="width: 100%" id="tabDetailsBase"  onactivechanged="changTab">
			<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_equip_detail.jsp"></jsp:include>
			</div>
			<div title="商务条件" name="conditionDetail" iconCls="icon-cut" >
				<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ></jsp:include>
			</div>
			<div title="其他商务条件" name="other_business_condition" iconCls="icon-cut">
	    		 <jsp:include page="../proj_common/proj_other_condition.jsp" ></jsp:include>
			</div>
			<div title="担保单位信息" name="proj_guarantee_detail" iconCls="icon-cut" >
				<jsp:include page="../proj_common/proj_guarantee_detail.jsp" ></jsp:include>
			</div>
			<div title="抵质押物信息" name="proj_guaranty_detail" iconCls="icon-cut">
				<jsp:include page="../proj_common/proj_guaranty_detail.jsp" ></jsp:include>
			</div>
			<div title="租金发票类型" name="credit_report"   iconCls="icon-cut" >
		    	 <jsp:include page="../proj_common/proj_rent_invoice_type.jsp" ></jsp:include>
		    </div> 
			<div title="尽调报告" name="credit_report_list" iconCls="icon-cut">
				<jsp:include page="../proj_credit/credit_report/credit_report_list.jsp"></jsp:include>
			</div>
	   </div>
	</div>
	<script type="text/javascript">
</script>