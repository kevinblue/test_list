<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
   <div id="proj_change_form" title="借阅人信息">
	    <table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;">
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width='12%'>借阅人：</td>
	             <td class="td-content" width='38%'>
		             <input id="proj_info.borrower" name="proj_info.borrower" label="借阅人" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.borrower'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.borrower']}"
							value="${empty requestScope['proj_info.borrower'] ? sessionScope.loginUser.id : requestScope['proj_info.borrower']}"
							/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.borrower" name="rawValue_proj_info.borrower" value="${requestScope['rawValue_proj_info.borrower']}" class="mini-textbox" style="display:none"/>
		         </td>
	             <td class="td-content-title" width='12%'>借阅日期：</td>
	             <td class="td-content" width='38%'> <input name="proj_info.borrowdate"  label="借阅日期" value="${requestScope['proj_info.borrowdate'] }" class="mini-datepicker" required="true"  allowInput="false"/></td>
	             
	          </tr> 
	          <tr class="tr-project-info tr-even">
	          	 <td class="td-content-title" width="12%">借阅类型：</td>
	             <td class="td-content" width="38%"><input name="proj_info.borrowtype" class="mini-combobox asLabel" required=true textField="text" 
	                  	   valueField="value"  
						   data="[{text:'外部借阅',value:'外部借阅'},{text:'非外部借阅',value:'非外部借阅'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   value="${requestScope['proj_info.borrowtype'] }" 
						   text="${requestScope['rawValue_proj_info.borrowtype'] }" />
				<input id="rawValue_proj_info.borrowtype" name="rawValue_proj_info.borrowtype" value="${requestScope['rawValue_proj_info.borrowtype']}" class="mini-textbox" style="display:none"/>
                 </td>        
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">借阅说明：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:74%;height:150px" label="借阅说明" id="proj_info.borrwomemo" name="proj_info.borrwomemo" value="${requestScope['proj_info.borrwomemo'] }" required="true"  class="mini-textarea"  type="text" >  </td>
	          </tr>
	            
	</table>
</div>
<script language="javascript">
function aaa(){
	alert(111);
	var a=mini.get("rawValue_proj_info.borrowtype").getValue();
	mini.get("proj_info.borrwomemo").setValue(a);
	mini.get("proj_info.borrwomemo").setText(a);
	alert(a);
	
	
}
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_change_form");};
	var strs = [{key:"proj_info.borrower",value:"借阅人"}];
	for(var i = 0 ;i<strs.length;i++){
		tenwa.createQueryInput({ 
			id:strs[i].key,
			label:strs[i].value,
			textField:"name",
	      	valueField:"id",
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
				mini.get("rawValue_"+inputObj.id).setValue(rowData.name);
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'
			}
		});
	}
});
</script>


