<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="projectCheckTable" title="项目考核">
	<table id="proj_Check" cellspacing="0" cellpadding="0" align="center"
		style="width: 70%; border: 2px solid #99CCFF;" class="fillTable">
		<th bgcolor="#468cc8"></th>
		<th bgcolor="#468cc8">&nbsp&nbsp&nbsp姓&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp名</th>
		<th bgcolor="#468cc8">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp分配比例</th>
		<tr>	<td class="td-content-title">推荐人:</td>
				<td class="td-content">
					<input id="referee" name="referee" label="推荐人" class="mini-combobox" allowInput="true"
	             		required="false"
						text="<mini:param  name="rawValue_referee"/>"
						value="<mini:param  name="referee"/>"
						/>
					<input id="rawValue_referee" name="rawValue_referee"
						value="<mini:param  name="rawValue_referee"/>"
						class="mini-textbox" style="display: none" />
				</td>
			<td class="td-content"><input id="referee_Proportion_id"
				name="referee_Proportion" class="mini-textbox" vtype="float"
				allowInput="true" Value=0 /> <font class="required-tag">%</font></td>
		</tr>
		<tr>
			 <td class="td-content-title">主&nbsp&nbsp办:</td>
			   <td class="td-content">
			   <input id="hoster" name="hoster" label="主办"
				class="mini-combobox"  required="true"  allowInput="true"  value="<mini:param  name="contract_info.projmanage" />"
				text="<mini:param  name="rawValue_contract_info.projmanage"/>" 
				/>
				<input id="rawValue_hoster" name="rawValue_hoster"
						value="<mini:param  name="rawValue_hoster"/>"
						class="mini-textbox" style="display: none" />
				</td> 
			<td class="td-content"><input id="hoster_Proportion_id"
				name="hoster_Proportion" class="mini-textbox" vtype="float"
				allowInput="true" Value=0 /> <font class="required-tag">%</font></td>
		</tr>
		<tr>
			<td class="td-content-title">协&nbsp&nbsp办:</td>
			<td class="td-content"><input id="assistant" name="assistant"
				class="mini-combobox" textField="name" label="协办" allowInput="true"
				value="<mini:param name="rawValue_assistant" />"  
				text="<mini:param  name="assistant"/>" />
				<input id="rawValue_assistant" name="rawValue_assistant"
						value="<mini:param  name="rawValue_assistant"/>"
						class="mini-textbox" style="display: none" />
				</td>
			<td class="td-content"><input id="assistant_Proportion_id"
				name="assistant_Proportion" class="mini-textbox" vtype="float"
				allowInput="true" Value=0 /> <font class="required-tag">%</font></td>
		</tr>
		<tr>
			<td class="td-content-title">第三方:</td>
			<td class="td-content"><input id="thirdParty"
				name="thirdParty" class="mini-combobox" textField="name" label="第三方" allowInput="true"
				value="<mini:param name="rawValue_thirdParty"/>"  
				text="<mini:param  name="thirdParty"/>" />
				<input id="rawValue_thirdParty" name="rawValue_thirdParty"
						value="<mini:param  name="rawValue_thirdParty"/>"
						class="mini-textbox" style="display: none" />
				</td>
			<td class="td-content"><input id="third_Party_Proportion_id"
				name="third_Party_Proportion" class="mini-textbox" vtype="float"
				allowInput="true" Value=0 request="ture" /> <font
				class="required-tag">%</font></td>
		</tr>
		<tr>
			<td>
		</tr>
	</table>
</div>

<script type="text/javascript">

      jQuery(function(){
		var strs = [{key:"referee",value:"推荐人"},{key:"hoster",value:"主办"},{key:"assistant",value:"协办"},{key:"thirdParty",value:"第三方"}];
		for(var i = 0 ;i<strs.length;i++){
			tenwa.createQueryInput({ 
				id:strs[i].key,
				label:strs[i].value,
				windowWidth: 600,
				textField:"name",
		      	valueField:"id",
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














<!--  <script type="text/javascript">
 $(function(){
	// 设置属性值
	$("#submit").click(function() {
		 var allocationProportion1 = parseFloat(mini.get("allocationProportion_id1").getValue());
		 var allocationProportion2 = parseFloat(mini.get("allocationProportion_id2").getValue());
		 var allocationProportion3 = parseFloat(mini.get("allocationProportion_id3").getValue());
		 var allocationProportion4 = parseFloat(mini.get("allocationProportion_id4").getValue());
		 var sum=allocationProportion1+allocationProportion2+allocationProportion3+allocationProportion4;
		  mini.alert(sum);
		 if(sum!=100){
			 mini.alert("分配比例之和必须为100%，请修改");
			 return false;
		 }
		 return true;
	});
}); -->

