<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
   <div id="proj_cancel_stop_forml" title="中止说明">
	    <table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;">
	             <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title" width="12%">经办人所属部门：</td>
	             <td class="td-content" width='38%'>
		             <input id="presentdept" name="proj_info.presentdept" label="经办人所属部门" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.presentdept'] ? sessionScope['loginUserDeptObj'].name : requestScope['rawValue_proj_info.presentdept'] }"
							value="${empty requestScope['proj_info.presentdept'] ? sessionScope['loginUserDeptObj'].id : requestScope['proj_info.presentdept'] }"
							/>
					 <input id="rawValue_proj_info.presentdept" name="rawValue_proj_info.presentdept" value="${empty requestScope['rawValue_proj_info.presentdept'] ? sessionScope['loginUserDeptObj'].name : requestScope['rawValue_proj_info.presentdept'] }" class="mini-textbox" style="display:none"/>
		         </td>
	             <td class="td-content-title" width="12%">经办人：</td>
	             <td class="td-content" width='38%'>
		             <input id="presentname" name="proj_info.presentname" readonly label="经办人" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.presentname']}"
							value="${empty requestScope['proj_info.presentname'] ? sessionScope.loginUser.id : requestScope['proj_info.presentname']}"
							/>

					 <input id="rawValue_proj_info.presentname" name="rawValue_proj_info.presentname" value="${empty requestScope['rawValue_proj_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.presentname']}" class="mini-textbox" style="display:none"/>
		         </td>
	         </tr>     
	         
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width='12%'>日期：</td>
	             <td class="td-content" width='38%'> <input id="repealdate" name="proj_info.repealdate" id=""  label="中止日期" value="${requestScope['proj_info.repealdate'] }" class="mini-datepicker" required="true"  allowInput="false"/></td>
	          </tr>          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">原因：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
	             <input style="width:72%;height:150px" id="repealreason" label="原因" name="proj_info.repealreason" value="${requestScope['proj_info.repealreason'] }" required="true" label="变更原因" class="mini-textarea"  type="text" >  </td>
	          </tr>
	            
	</table>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_cancel_stop_forml");};
	tenwa.createQueryInput({
		id:'presentdept',
		label:'经办人所属部门',
		textField:"name",
      	valueField:"id",  
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_proj_info.presentdept").setValue(rowData.name);
		},
		params: {
			xmlFileName: '/eleasing/workflow/proj/proj_common/thisdepartment_combox.xml'
	
		}
	});

	var myDate =new Date();
	mini.get("repealdate").setValue(myDate);
});

</script>


