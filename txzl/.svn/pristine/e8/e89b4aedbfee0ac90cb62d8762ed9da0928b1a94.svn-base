<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript">
    //流程保存之前回调
	function workflowSaveCallBack()
	{
	 	//createProjectName();//保存生成项目名称
		miniui_ext.saveIncludeData("tabApprovalDeatils");
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
        if(checkEquipIsNull()==false) return;
        miniui_ext.saveIncludeData("tabApprovalDeatils");
        if(mini.get("id_customworkflowattachment")){
	 		var attachment=mini.get("id_customworkflowattachment").getData();
	 		for(var i = 0;i < attachment.length; i++){
	 			var fileid = attachment[i].attachmentFileDictId;
	 			var filename = attachment[i].attachmentFileUploadInfoDetailChineseFileNames;
	 			if(fileid=="root.FileType.Surface.01"&&filename==""){
	 				mini.alert("请上传租赁方案测算表！");
	 				return false;
	 			}
	 		}
	 	}else{
	 		mini.alert("请到附件一览查看是否已上传租赁方案测算表！");
	 		return false;
	 	}
/*         
        if(mini.getbyName('rawValue_proj_info.leasform').getValue()=="直租"){
        	var data=mini.get('proj_equip').getData();
        	if(mini.get('proj_equip').getData().length==0){
        		mini.alert("直租时，租赁物明细不能为空！");
        		return false;
        		};
        		
        	for(var i=0;i<data.length;i++){
        		if(data[i].vndrtype==""){
        			mini.alert("直租时，请在租赁物明细中，填写贸易类型！");
        			return false;
        		}
        	}
        	
        };
         */
		return true;
	}
</script>
<!--多行控件  -->
<input style="display:none;"    class="mini-textarea" id="id_json_proj_equip_str" name="json_proj_equip_str" value='${empty json_proj_equip_str ? "[]" : json_proj_equip_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guarantee_detail_str" 	name="json_proj_guarantee_detail_str" value='${empty json_proj_guarantee_detail_str ? "[]" : json_proj_guarantee_detail_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_proj_guaranty_detail_str" 	name="json_proj_guaranty_detail_str" value='${empty json_proj_guaranty_detail_str ? "[]" : json_proj_guaranty_detail_str }'></input>
<input style="display:none;"	class="mini-textarea" id="id_json_quotation_scheme_str" 	name="json_quotation_scheme_str" value='${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'></input>
<div class="fillTableContainer">
   <table class="fillTable" cellspacing="0" cellpadding="0">
	  <tr>
	     <td colspan=4>
			 <!-- 插入项目基本信息 --> 
			 <jsp:include page="../proj_common/proj_base_info.jsp"></jsp:include>
		 </td>
	   </tr>
	</table>
</div>		
<div id="tabApprovalDeatils" class="mini-tabs" activeIndex="0" onactivechanged="changTab" style="width: 100%;" bodyStyle="border:0px;">
	<div title="租赁物明细" name="proj_equip" iconCls="icon-cut" >
		 <jsp:include page="../proj_common/proj_equip_detail.jsp" ></jsp:include>
	</div>
	<div title="商务条件" name="business_condition" iconCls="icon-cut">
			<jsp:include page="../../reckon/rent_reckon/main_5_1.jsp" ></jsp:include>
	</div>
	<div title="其他商务条件" name="other_business_condition" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_other_condition.jsp" ></jsp:include>
	</div>
	<div title="担保单位信息" name="proj_guarantee_detail" iconCls="icon-cut">
	     <jsp:include page="../proj_common/proj_guarantee_detail.jsp" ></jsp:include>
	</div>
	<div title="抵质押物信息" name="proj_guaranty_detail" iconCls="icon-cut" >
	     <jsp:include page="../proj_common/proj_guaranty_detail.jsp" ></jsp:include>
	</div>
	<div title="项目报价方案" name="quotation_scheme" iconCls="icon-node" >
			<jsp:include page="../proj_common/proj_quotation_scheme.jsp" ></jsp:include>
	</div> 
</div>