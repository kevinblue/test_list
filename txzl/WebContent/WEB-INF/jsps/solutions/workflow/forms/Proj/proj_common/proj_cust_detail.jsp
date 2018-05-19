<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%-- 
<div id="proj_info_cust_detail" title="客户描述" showCollapseButton="true" class="mini-panel" style="width:100%">
 
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">收入(万元)：</td>
					<td class="td-content" width="38%">
						<input name="proj_info.custincome" label="收入(万元)" class="mini-textbox" vType="float" value="${requestScope['proj_info.custincome'] }" allowInput="true" />
	               </td>
					<td class="td-content-title">资产负债率：</td>
					<td class="td-content">
						<input name="proj_info.debtratio" label="资产负债率" class="mini-textbox" vType="float" value="${requestScope['proj_info.debtratio'] }" allowInput="true" /></td>
				</tr>
				<tr id="id_medical_tr" class="tr-even" style="display:none">
					<td class="td-content-title" width="12%">等级：</td>
					<td class="td-content" width="38%">
						<input name="proj_info.custlevel" label="等级" class="mini-textbox" value="${requestScope['proj_info.custlevel'] }" allowInput="true" />
	               </td>
	               <td class="td-content-title" width="12%">实际开放床位：</td>
				   <td  class="td-content" width="38%">
				   	<input name="proj_info.custbednum" label="实际开放床位" class="mini-textbox" value="${requestScope['proj_info.custbednum'] }" allowInput="true" /></td>
				</tr>
				<tr id="id_public_tr"  class="tr-odd" style="display:none">
	               <td class="td-content-title" width="12%">利润(万元)：</td>
				   <td class="td-content" width="38%">
				   	<input name="proj_info.custprofit" label="利润(万元)" class="mini-textbox" vType="float" value="${requestScope['proj_info.custprofit'] }" allowInput="true" /></td>
	               <td class="td-content-title" width="12%" >刚性负债(万元)：</td>
				   <td class="td-content" width="38%" >
				   	<input name="proj_info.custdebt" label="刚性负债(万元)" class="mini-textbox" vType="float" value="${requestScope['proj_info.custdebt'] }" allowInput="true" /></td>
				</tr> --%>
<div id="proj_info_cust_detail" title="客户描述" showCollapseButton="true" class="mini-panel" style="width:100%">
 
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">	          
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">备注：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:150px" name="proj_info.custmemo" label="备注" value="${requestScope['proj_info.custmemo'] }"  label="备注" class="mini-textarea" maxLength="500" type="text" > 
	             </td>
	          </tr>
	          
 	</table>
</div>
<script language="javascript">
jQuery(function(){
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("proj_info_cust_detail");}
});

</script>