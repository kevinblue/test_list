<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
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
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}

</script>
<!--多行控件  -->
<input type="hidden"  id="id_json_curent_roll_back_the_record_str" name="json_curent_roll_back_the_record_str" value='${empty json_curent_roll_back_the_record_str ? "[]" : json_curent_roll_back_the_record_str }'/>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 基准利率 --> 
			 <jsp:include page="../regulating_breathing/common/base_rate_info.jsp"></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0"  style="width: 99%;" bodyStyle="padding:0;border:0;">
	<div title="已调息可回滚记录列表" name="already_regulating_of_breathing" iconCls="icon-cut" >
		 <jsp:include page="common/already_regulating_of_breathing_list.jsp" >
		 	<jsp:param value="true" name="isView"/>
		 </jsp:include>
	</div>
	<div title="本次回滚列表" name="curent_roll_back_the_record" iconCls="icon-cut">
		<jsp:include page="common/curent_roll_back_the_record_list.jsp" >
			<jsp:param value="true" name="isView"/>
		</jsp:include>
	</div>
</div>
