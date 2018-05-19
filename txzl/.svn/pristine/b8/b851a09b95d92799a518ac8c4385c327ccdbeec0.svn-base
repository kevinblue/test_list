<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
 <script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
		
		var obj = mini.get("cbl11").getValue();
		if(obj==''){
			mini.alert("用章种类至少选择一种！！！");
			return;
		}
		return true;
	}
	//保存成功提交后，后台返回回调
	function saveCallBack() {
		
		return true;
	}
	//流程提交之前回调
	function workflowSubmitCallBack(buttonText)
	{
		
		if (miniui_ext.submitFormValidation(["proj_base_info_form"]) == false) return false;
		var obj = mini.get("cbl11").getValue();
		if(obj==''){
			mini.alert("用章种类至少选择一种！！！");
			return;
		}
		return true;
	}
	//提交路由，选择下一步的节点
	function workflowNextRouteCallBack(buttonText,requestNextRoute){
		if(buttonText == "Submit"){
			var approval = mini.get("approval").getValue();
			if(approval=='是'){
			requestNextRoute.value = "是";
			}else{
			requestNextRoute.value = "否";
			}
		}
	}
	//校验关联的流程信息---关联的流程数量理论上为1
	function queryWorkFlow(){
		 var jsonStr=mini.get("id_json_seal_workflowlist_str").getValue();
		 var jsonArr=JSON.parse(jsonStr);
		 if(jsonArr.length==0){
			 mini.alert("本次盖章流程为空，请选择相关流程！");
			 return false;
		 }
		return true;
	}
</script>
<!--多行控件  -->

<input style="display:none;"    class="mini-textarea" id="id_json_seal_workflowlist_str" name="json_seal_workflowlist_str" value='${empty json_seal_workflowlist_str ? "[]" : json_seal_workflowlist_str }'></input>

<input style="display:none;"	class="mini-textarea" id="id_route_str_many" name="route_str_many" value="${route_str_many}"></input>
<input style="display:none;"	class="mini-textarea" id="id_approvallevellast" name="approvallevellast" value="${approvallevellast}"></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='${empty json_proj_guarantee_detail_str ? "[]" : json_proj_guarantee_detail_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='${empty json_proj_guaranty_detail_str ? "[]" : json_proj_guaranty_detail_str }'></input>
<div  class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 公章使用登记信息 --> 
			 <jsp:include page="comm/seal_registration_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	   
	</table>
</div>
<div id="tabDeatils" class="mini-tabs" activeIndex="0"
			style="width: 100%;" bodyStyle="padding:0;border:0;">
		<div title="公章使用相关附件" name="proj_accessories_report_list" iconCls="icon-node">
				<jsp:include page="comm/seal_accessories_report_list.jsp"></jsp:include>
		</div>
		<div title="流程实例查询" name="seal_workflowlist_r" iconCls="icon-cut">
				<jsp:include page="comm/seal_workflowlist_r.jsp"></jsp:include>
		</div> 
		<div title="本次盖章流程明细" name="seal_workflowlist"
				iconCls="icon-cut">
				<jsp:include page="comm/seal_workflowlist.jsp">
				<jsp:param value="false" name="isView" />
				</jsp:include>
		</div>  
</div>
<script type="text/javascript">
</script>	
