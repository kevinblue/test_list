<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
		<table class="fillTable" cellspacing="0" cellpadding="0" id="div_limit_info" >
		<%-- 	<tr class="tr-project-info tr-even">
	             <td class="td-content-title" >是否额度提取：</td>
	             <td class="td-content" width="38%">
		             <input name="proj_info.isuselimit" class="mini-combobox" label="是否额度提取" textField="name"  required="true"
		             				id="isuselimit"
			                  	   valueField="value"  
								   dataField="datas"
								   showNullItem="true"
								   allowInput="false" 
								   data-options="{_params:{pid:'use_limit'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['proj_info.isuselimit'] }" 
								   text="${requestScope['rawValue_proj_info.isuselimit'] }" 
								   onvaluechanged="useLimitChange"/>
					 
					 <input id="rawValue_proj_info.isuselimit" name="rawValue_proj_info.isuselimit" value="${requestScope['rawValue_proj_info.isuselimit']}" class="mini-textbox" style="display:none"/>
		          </td>
	             <td class="td-content-title" width="12%">授信类型：</td>
	             <td class="td-content" width="38%">
		             <input name="proj_info.limittype" id="limittype" label="授信用途" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   showNullItem="true"
								   allowInput="false" 
								   data-options="{_params:{pid:'limit_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['proj_info.limittype'] }" 
								   text="${requestScope['rawValue_proj_info.limittype'] }" 
								   onvaluechanged="comboboxChanged"/>
					 <input id="rawValue_proj_info.limittype" name="rawValue_proj_info.limittype" value="${requestScope['rawValue_proj_info.limittype']}" class="mini-textbox" style="display:none"/>
	             </td>
		</tr> --%>
			<tr class="tr-project-info tr-odd" id="tr_limit_id" style="display:none">
			   <td class="td-content-title" width="12%">综合授信项目编号：</td>
				<td class="td-content" width="38%">
					<input id="proj_info.limitid" name="proj_info.limitid" label="综合授信项目编号" class="mini-buttonedit mini-queryinput" allowInput="false"
						required="true"
						text="${requestScope['rawValue_proj_info.limitid'] }"
						value="${requestScope['proj_info.limitid'] }"
						/>
					<input id="rawValue_proj_info.limitid" name="rawValue_proj_info.limitid"
						value="${requestScope['rawValue_proj_info.limitid']}"
						class="mini-textbox" style="display: none" />
				</td>
		</tr>
	</table>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
    miniui_ext.disableFormFields("div_limit_info");
}
/* jQuery(function(){
	useLimitChange();
	if(mini.get("isuselimit").getValue()=='use_limit.use' && stepName.substring(0,2) >='06'){
		$("#tr_limit_id").show();
		if(stepName.substring(0,2) =='06'){
			//第六步信审经办选择额度编号
			mini.get("proj_info.limitid").enable();
		}
	}
}); */
function useLimitChange(e){
	if(mini.get("isuselimit").getValue()=='use_limit.limit' && stepName.substring(0,2) =='01'){
		//项目经理可选授信类型
		mini.get("limittype").enable();
	}
	if(mini.get("isuselimit").getValue()=='use_limit.use'){
		//额度提取
		mini.get("limittype").setValue("");
		mini.get("limittype").setText("");
		mini.get("rawValue_proj_info.limittype").setValue("");
	    mini.get("limittype").disable();
	}
	if(typeof(e)!='undefined'){
		comboboxChanged(e);
	}
}
jQuery(function(){
	var strs = [{key:"proj_info.limitid",value:"项目编号"}];
	for(var i = 0 ;i<strs.length;i++){
		tenwa.createQueryInput({ 
			id:strs[i].key,
			label:strs[i].value,
			textField:"name",
	      	valueField:"id",
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
				if(parseFloat(mini.get("equipamt").getValue().replace(/,/g,"")) > parseFloat(rowData.ava)){
					$("#avaFlag").val("true");
					mini.alert("本次提取额度金额大于剩余金额,请检查后提交!");
					return false;
				}
				$("#avaFlag").val("false");
				mini.get("rawValue_"+inputObj.id).setValue(rowData.name);
			},
			params: {
				custid:"${requestScope['custid']}",
				xmlFileName: '/eleasing/workflow/proj/proj_common/choose_projid.xml'
			}
		});
	}
});
</script>

