<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/minidict" prefix="mini"%>
	<script type="text/javascript">
		jQuery(function(){
	
			tenwa.createQueryInput({
				id:'proj_info.creditmanager',
				label:'风控经办',
				textField:"name",
		      	valueField:"id",  
				windowWidth: 600,
				onSelect: function($me, inputObj, rowData){
					mini.get("rawValue_proj_info.creditmanager").setValue(rowData.name);
				},
				params: {
					xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml',
					rolecode:'r_proj_first'
				}
			});
			
		});
		function comboboxChanged(e){
			 var cbb=e.sender;
			 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
		}

	</script>
	<div  id="id_proj_credit_isapproval"   title="信审信息">  
    <div class="mini-panel"   title="信审信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd"> 
				 <td class="td-content-title" width="12%" id="choosepeople1">风控审查：</td>
	             <td class="td-content" width="38%" id="choosepeople2">
	             	<input id="proj_info.creditmanager" name="proj_info.creditmanager" label="风控审查" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="<mini:param  name="rawValue_proj_info.creditmanager"/>"
							value="<mini:param  name="proj_info.creditmanager"/>"
							/>
				 <font class="required-tag">*</font>
				 <input id="rawValue_proj_info.creditmanager" name="rawValue_proj_info.creditmanager" value="<mini:param  name="rawValue_proj_info.creditmanager"/>" class="mini-textbox" style="display:none"/>
				 </td>
	          </tr>
		</table>
	</div>
</div>    

