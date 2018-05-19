<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText) //返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if (miniui_ext.submitFormValidation(["riskmanager_five_category_form"]) == false) return false;

	return true;
}
</script>
<!--多行控件  -->
<div id="contract_change_form">
	<div class="fillTableContainer"> 
		<table class="fillTable" cellspacing="0" cellpadding="0">
			<tr>
				<td >
				  <jsp:include page="../contract_comm/contract_base_info.jsp">
				     <jsp:param value="true" name="isView"/>
				  </jsp:include>
			    </td>
			</tr>
		</table>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
		<div title="业务五级" name="bussinessfivecategory" iconCls="icon-node">
			<jsp:include page="comm/bussiness_five_category.jsp" >
			 <jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="资产五级" name="assetsfivecategory" iconCls="icon-node">
			<jsp:include page="comm/assets_five_category.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="财务五级" name="financefivecategory" iconCls="icon-node">
			<jsp:include page="comm/finance_five_category.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="风险五级" name="riskfivecategory" iconCls="icon-node">
			<jsp:include page="comm/risk_five_category.jsp" >
			<jsp:param value="true" name="isView"/>
			</jsp:include>
		</div>
		<div title="风控五级" name="riskmanagerfivecategory" iconCls="icon-node">
			<jsp:include page="comm/riskmanager_five_category.jsp" ></jsp:include>
		</div>
	</div>
</div>