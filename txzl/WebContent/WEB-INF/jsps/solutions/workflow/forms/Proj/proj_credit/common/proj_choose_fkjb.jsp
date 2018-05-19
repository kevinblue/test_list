<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_projregistrar" >
			<tr class="tr-project-info tr-even">
			   <td class="td-content-title" width="12%">信审经办：</td>
				<td class="td-content" width="38%">
					<input id="proj_info.projregistrar" name="proj_info.projregistrar" label="信审经办" class="mini-buttonedit mini-queryinput" allowInput="false"
						required="true"
						text="${requestScope['rawValue_proj_info.projregistrar'] }"
						value="${requestScope['proj_info.projregistrar'] }"
						/>
					<input id="rawValue_proj_info.projregistrar" name="rawValue_proj_info.projregistrar"
						value="${requestScope['rawValue_proj_info.projregistrar']}"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title" width="12%">信审方向负责人：</td>
				<td class="td-content" width="38%">
					<input id="proj_info.projresponsible" name="proj_info.projresponsible" label="信审方向负责人" class="mini-buttonedit mini-queryinput" allowInput="false"
						required="true" readOnly
						text="${empty requestScope['rawValue_proj_info.projresponsible']? sessionScope.loginUser.realname:requestScope['rawValue_proj_info.projresponsible']}"
						value="${empty requestScope['proj_info.projresponsible']?  sessionScope.loginUser.id: requestScope['proj_info.projresponsible']}"
						/>
					<input id="rawValue_proj_info.projresponsible" name="rawValue_proj_info.projresponsible"
						value="${empty requestScope['rawValue_proj_info.projresponsible']?sessionScope.loginUser.realname:requestScope['rawValue_proj_info.projresponsible']}"
						class="mini-textbox" style="display: none" />
				</td>
		</tr>
		<tr class="tr-project-info tr-odd">
			   <td class="td-content-title" width="12%">现有风险敞口：</td>
				<td class="td-content" width="38%" clospan="3">
					<input id="proj_info.exposure" name="proj_info.exposure" label="风险敞口" class="mini-textbox" 
						required="true" readOnly
						value="${requestScope['proj_info.exposure'] }"/>
				</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
jQuery(function(){
	var strs = [{key:"proj_info.projregistrar",value:"信审经办"}];
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
				xmlFileName: '/eleasing/workflow/proj/proj_common/choose_fkjb.xml'
			}
		});
	}
});
jQuery(function(){
	var strs = [{key:"proj_info.projresponsible",value:"信审方向负责人"}];
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
				xmlFileName: '/eleasing/workflow/proj/proj_common/choose_proj_responsible.xml'
			}
		});
	}
});
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_projregistrar");
}
jQuery(function(){
	mini.get("proj_info.projresponsible").disable();
})
jQuery(function(){
	$.ajax({
		url: urlPrefix+"/eleasing/workflow/proj/proj_credit/query_risk_exposure.xml",
		cache: false,
		data :{"custid":"${requestScope['proj_info.custInfo'] }"},
		success: function (text) {   
			var result = mini.decode(text);
			if(mini.get("proj_info.exposure") && mini.get("proj_info.exposure").getValue()==""){
				mini.get("proj_info.exposure").setValue(formatNumberThousand(result.datas[0].exposure));
			}
		}
	})
});
</script>

