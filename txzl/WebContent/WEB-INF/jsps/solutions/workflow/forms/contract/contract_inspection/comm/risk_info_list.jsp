<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="risk_info_list" title="重点关注事项">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
		<tr class="tr-odd">
				<td class="td-content-title">内容</td>
				<td class="td-content-title">情况说明/备注</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title"  width="220px">与前一次检查有重大出入的信息：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.lastMajorInfo" 
					name="contract_patrol_info.lastMajorInfo" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.lastMajorInfo']}"/>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" >前次风险政策清单及关注点：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.lastPolicyPoint" 
					name="contract_patrol_info.lastPolicyPoint" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.lastPolicyPoint']}"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >本次跟踪反馈说明：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.feedback" 
					name="contract_patrol_info.feedback" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.feedback']}"/>
				</td>
			</tr>
			<tr  class="tr-even">
				<td class="td-content-title" >后期风险政策清单及关注点：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.nextPolicyPoint" 
					name="contract_patrol_info.nextPolicyPoint" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.nextPolicyPoint']}"/>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" >逾期租金说明：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.overdueRent" 
					name="contract_patrol_info.overdueRent" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.overdueRent']}"/>
				</td>
			</tr>
			
			<tr class="tr-odd">
			      <td class="td-content-title" width="12%">注册信息是否变更：</td>
			      <td width="70px" >
                       <input id= "id_ifchange_list" name="proj_info.ifchange" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'是',value:'1'},{text:'否',value:'2'}]"   allowInput="false"      value="${requestScope['proj_info.ifchange'] }"/>
                    </td>
				<td class="td-content-title" width="12%">注册信息变更情况：</td>
				<td  style="padding-top: 4px; padding-bottom: 4px;" colspan="3">
					<input style="width: 65%; height: 100px"
					id="contract_patrol_info.change"
					name="contract_patrol_info.changeinfomation" class="mini-textarea"
					allowInput="true" label="注册信息变更情况"
					value="${requestScope['contract_patrol_info.changeinfomation']}" />
				</td>
				
			</tr>
			
			
			<tr class="tr-odd">
			      <td class="td-content-title" width="12%">担保人信息是否变更：</td>
			      <td width="70px" >
                       <input id= "id_ifchange_guarantee_list" name="guarantee.ifchange" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'是',value:'1'},{text:'否',value:'2'}]"   allowInput="false"      value="${requestScope['guarantee.ifchange'] }"/>
                    </td>
				<td class="td-content-title" width="12%">担保人信息变更描述：</td>
				<td  style="padding-top: 4px; padding-bottom: 4px;" colspan="3">
					<input style="width: 65%; height: 100px"
					id="contract_patrol_info.content"
					name="contract_patrol_info.content" class="mini-textarea"
					allowInput="true" label="担保人信息变更描述"
					value="${requestScope['contract_patrol_info.content']}" />
				</td>
				
			</tr>
			
			<tr class="tr-odd">
			      <td class="td-ifhave-title" width="12%">是否取得不动产权证：</td>
			      <td width="70px" >
                       <input id= "id_ifhave_list" name="proj_info.ifhave" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'是',value:'1'},{text:'否',value:'2'}]"   allowInput="false"      value="${requestScope['proj_info.ifhave'] }"/>
                    </td>
                     <td class="td-content-title" width="12%">竣工时间：</td>
	             <td class="td-content" width="65%"> <input id="id_projdate" name="completion_time" class="mini-datepicker" value="${requestScope['completion_time'] }" label="竣工时间"   allowInput="false"/></td>
			</tr>
			
			
			<tr class="tr-odd">
				<td class="td-content-title" >备注：</td>
				<td style="padding-top: 4px;padding-bottom: 4px;" colspan="3">
					<input style="width:73%;height:80px;" id="contract_patrol_info.mark" 
					name="contract_patrol_info.mark" class="mini-textarea" 
					value="${requestScope['contract_patrol_info.mark']}"/>
				</td>
			</tr>
			
			
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("risk_info_list");};
});
</script>
