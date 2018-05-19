<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
	
	 	   <table class="fillTable" cellspacing="0" cellpadding="0" id="contract_base_info" >
	          <tr class="tr-project-info tr-odd">  
	          		<td class="td-content-title" width="12%" >流程下节点处理人：</td><td class="td-content-title">
		             	<input id="proj_info.projmanage" name="proj_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projmanage']}"
							value="${empty requestScope['proj_info.projmanage'] ? sessionScope.loginUser.id : requestScope['proj_info.projmanage']}"
							/>
					
					 	<input id="rawValue_proj_info.projmanage" name="rawValue_proj_info.projmanage" value="${empty requestScope['rawValue_proj_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projmanage']}" class="mini-textbox" style="display:none"/>
					</td>
				</tr>
		    </table>
	
<script language="javascript">
	jQuery(function(){
	var strs = [{key:"proj_info.projmanage",value:"项目经理"}];
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
				xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox2.xml'
			}
		});
	}
	});
	</script>