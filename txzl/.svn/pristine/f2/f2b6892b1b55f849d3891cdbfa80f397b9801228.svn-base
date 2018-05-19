<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="id_proj_metting" title="项目评审会议记录" >
<div class="mini-panel" title="项目评审会议记录" showCollapseButton="true" style="width: 100%;"> 
    <table class="fillTable" cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
		        <td class="td-content-title">上会时间
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.meetingdate" allowInput="false"  class="mini-datepicker" label="上会时间" required="true" value="${requestScope['proj_meeting.meetingdate'] }"/>
		        </td>
		        <td class="td-content-title">上会地点
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.meetingplace" class="mini-textbox" label="上会地点"  value="${requestScope['proj_meeting.meetingplace'] }"/>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">参加审批人员
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_meeting.approvalperson" required="true" label="参加审批人员" class="mini-textbox" style="width:79%;"  required="true" value="${requestScope['proj_meeting.approvalperson'] }" />
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">上会结论
		        </td>
		        <td colspan="3" class="td-content">
		            <textarea name="proj_meeting.conclusion" class="mini-textarea" label="会议结论" required="true" style="width:79%;height:100px"   value="${requestScope['proj_meeting.conclusion'] } " /></textarea>
		        </td>
		    </tr>
		
		</table>
	</div>
 </div>
 <script language="javascript">
		jQuery(function(){
			if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_proj_metting");};
	     });
</script>


