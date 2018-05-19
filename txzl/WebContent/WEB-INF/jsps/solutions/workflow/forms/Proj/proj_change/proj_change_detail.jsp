<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
   <div id="proj_change_form" title="变更说明">
        <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title" width="12%">变更日期：</td>
	             <td class="td-content" width="88%"> <input name="proj_info.changedate" value="${requestScope['proj_info.changedate'] }" label="变更日期" class="mini-datepicker" required="true"  allowInput="false"/></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">变更说明：</td>
	             <td style="padding-top: 4px;padding-bottom: 4px;"><input style="width:72%;height:150px" name="proj_info.changememo" label="变更说明" value="${requestScope['proj_info.changememo'] }" required="true"  class="mini-textarea"  type="text" >  </td>
	          </tr>	            
	</table>
</div>
<script language="javascript">
//控制只读页面
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){	miniui_ext.disableFormFields("proj_change_form");	};
});
</script>


