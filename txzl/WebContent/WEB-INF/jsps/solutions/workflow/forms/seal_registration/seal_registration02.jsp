<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{
		return true;
	}
	function workflowNextRouteCallBack(buttonText,requestNextRoute){
		if(buttonText == "Submit"){
			var isUnpacking = mini.get('cbl11').getValue();
	        if(isUnpacking.indexOf('公章')> -1){
	        	requestNextRoute.value = "是";
	        }else{
	        	requestNextRoute.value = "否";
	        }		
		}
	}
</script>
<input style="display:none;"	class="mini-textarea" id="id_route_str_many" name="route_str_many" value="${route_str_many}"></input>

<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_seal_workflowlist_str" name="json_seal_workflowlist_str" value='${empty json_seal_workflowlist_str ? "[]" : json_seal_workflowlist_str }'></input>

<input style="display:none;"	class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='${empty json_proj_guarantee_detail_str ? "[]" : json_proj_guarantee_detail_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='${empty json_proj_guaranty_detail_str ? "[]" : json_proj_guaranty_detail_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="comm/seal_registration_base_info.jsp"><jsp:param value="true" name="isView"/></jsp:include>
		 </td>
	   </tr>
	   
	</table>
</div>
<div id="tabDeatils" class="mini-tabs" activeIndex="0"
			style="width: 100%;" bodyStyle="padding:0;border:0;">
		<div title="公章使用相关附件" name="proj_accessories_report_list"
				iconCls="icon-cut">
				<jsp:include page="comm/seal_accessories_report_list.jsp">
					<jsp:param value="true" name="isView" />
					</jsp:include>
		</div>
		<div title="本次盖章流程明细" name="seal_workflowlist"
				iconCls="icon-node">
				<jsp:include page="comm/seal_workflowlist.jsp">
				<jsp:param value="true" name="isView" />
				</jsp:include>
		</div>  
</div>		