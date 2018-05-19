<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<div id="fileadd" title="资料补充">
	<div  title="资料补充" showCollapseButton="true" class="mini-panel" style="width:100%">
		<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">资料名称：</td>
				<td class="td-content" width="38%">
					<input name="proj_info.fileaddname" id ="proj_info.fileaddname" label="资料名称" class="mini-textbox"  
					value="${requestScope['proj_info.fileaddname'] }" allowInput="true" required="true"/>
               </td>
				<td class="td-content-title">补充日期：</td>
				<td class="td-content" width="38%">
					<input name="proj_info.fileadddate" id ="proj_info.fileadddate" label="补充日期" class="mini-datepicker" 
					value="${requestScope['proj_info.fileadddate'] }" allowInput="false"  required="true"/></td>
			</tr>
			<tr class="tr-project-info tr-odd">
	             <td class="td-content-title">补充资料说明：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:100px" name="proj_info.fileaddexplain" id='proj_info.fileaddexplain' label="补充资料说明" 
		              value="${requestScope['proj_info.fileaddexplain'] }"  allowInput="true"   class="mini-textarea" maxLength="300"  > 
	             </td>
	          </tr>
	</table>
	</div>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("fileadd");}
</script>