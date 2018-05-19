<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'是',value:'是'},{text:'否',value:'否'}];
			mini.get('creditisapproval').set({
				textField : "text",
				valueField : "value",			
				data:creditStatus	
			});
			
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
					xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'
				}
			});
			
		});
		function comboboxChanged(e){
			 var cbb=e.sender;
			 mini.get("rawValue_"+cbb.name).setValue(cbb.text);
		}
		function isShow(){
		    if("是"==mini.getbyName("proj_info.nocreditisapproval").getValue()){
		         document.getElementById("choosepeople1").style.display = "";
		         document.getElementById("choosepeople2").style.display = "";
				 mini.get("proj_info.creditmanager").setValue("");
		         miniui_ext.setFieldsRequired("proj_info.creditmanager",true);
			}else{
				document.getElementById("choosepeople1").style.display = "none";
				document.getElementById("choosepeople2").style.display = "none";
				mini.get("proj_info.creditmanager").setValue("Administrator");
			}
		}
	</script>
	<div  id="id_proj_credit_isapproval"   title="信审信息">  
    <div class="mini-panel"   title="信审信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title" width="12%">是否继续调查：</td>
	             <td class="td-content" width="38%">
	             <input name="proj_info.nocreditisapproval" id="creditisapproval" label="是否继续调查" required="true" class="mini-combobox" showNullItem="false"  value="${requestScope['proj_info.nocreditisapproval']}" onvaluechanged="isShow"/>
				 <font class="required-tag">*</font>
				 </td>
				 <td class="td-content-title" width="12%" id="choosepeople1">风控经办：</td>
	             <td class="td-content" width="38%" id="choosepeople2">
	             	<input id="proj_info.creditmanager" name="proj_info.creditmanager" label="风控经办" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		
							text="${requestScope['rawValue_proj_info.creditmanager']}"
							value="${requestScope['proj_info.creditmanager']}"
							/>
				 <font class="required-tag">*</font>
				 <input id="rawValue_proj_info.creditmanager" name="rawValue_proj_info.creditmanager" value="${requestScope['rawValue_proj_info.creditmanager']}" class="mini-textbox" style="display:none"/>
				 </td>
	          </tr>
		</table>
	</div>
</div>    

