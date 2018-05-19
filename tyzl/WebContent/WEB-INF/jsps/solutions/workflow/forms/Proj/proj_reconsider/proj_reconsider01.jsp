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
	//原始提交按钮
	/* function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{ 
		if(!checkCalIsSame()){return ;}
		if (miniui_ext.submitFormValidation(["proj_base_info_form"]) == false) return false;
       if(checkEquipIsNull()==false) return;
     //文件清单校验
	 //var checkFlag =  getTracywindyObject("document_list_data") ? getTracywindyObject("document_list_data").checkRequire() : getTracywindyObject("document_list_nonmedical_data").checkRequire();
		 if(checkInfo.checkResult){
			mini.alert(checkInfo.msgs);
			return ;
		} 
		//if(checkFlag){return};
        miniui_ext.saveIncludeData("tabDetailsBase");
		return true;
	} */

 function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{ 
		if(!checkCalIsSame()){return ;}
		if (miniui_ext.submitFormValidation(["proj_base_info_form"]) == false) return false;
        if(checkEquipIsNull()==false) return ;
     //文件清单校验
	 //var checkFlag =  getTracywindyObject("document_list_data") ? getTracywindyObject("document_list_data").checkRequire() : getTracywindyObject("document_list_nonmedical_data").checkRequire();
		/* if(checkInfo.checkResult){
			mini.alert(checkInfo.msgs);
			return ;
		} */
		//if(checkFlag){return};
        miniui_ext.saveIncludeData("tabDetailsBase");
		return true;
	}
</script>
    <jsp:include page="../proj_credit/common/proj_credit_publish.jsp"></jsp:include>
    <jsp:include page="../proj_credit/common/proj_credit_hidden_info.jsp"></jsp:include>
	<div id="contract_approval_form">
		<div class="fillTableContainer" >
			<table class="fillTable" cellspacing="0" cellpadding="0">
					<tr><td> <jsp:include page="../proj_common/proj_base_info.jsp"></jsp:include></td></tr>
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
			<div title="抵押物信息" name="proj_guaranty_detail" iconCls="icon-cut">
				<jsp:include page="../proj_common/proj_guaranty_detail.jsp" ></jsp:include>
			</div>
			<div title="联合承租人" name="union_cust" iconCls="icon-cut" >
		        <jsp:include page="../proj_common/proj_union_cust.jsp" ></jsp:include>
	        </div>
	   </div>
	</div>