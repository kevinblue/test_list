<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="id_proj_metting" title="项目评审会议记录" >
<div class="mini-panel" title="项目评审会议记录" showCollapseButton="true" style="width: 100%;"> 
    <table class="fillTable" cellspacing="0" cellpadding="0">
			<tr class="tr-project-info tr-even">
		        <td class="td-content-title">上会时间
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.meetingdate" allowInput="false"  class="mini-datepicker" label="上会时间" required="true" value="<mini:param  name="proj_meeting.meetingdate"/>"/>
		        </td>
		        <td class="td-content-title">上会地点
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.meetingplace" class="mini-textbox" label="上会地点"  value="<mini:param  name="proj_meeting.meetingplace"/>" />
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">主持人
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.chairman" required="true"  class="mini-textbox" label="主持人" value="<mini:param  name="proj_meeting.chairman"/>"  required="true" requiredErrorText="客户名称不能为空"/>
		        </td>
		        <td class="td-content-title">会议方式
		        </td>
		        <td class="td-content">
		            <input name="proj_meeting.meetingtype" class="mini-textbox" label="会议方式" value="<mini:param  name="proj_meeting.meetingtype"/>" required="true" requiredErrorText="客户名称不能为空"/>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-even">
		        
		        <td class="td-content-title">审批内容
		        </td>
		        <td class="td-content" colspan="3">
		            <textarea  class="mini-textarea" required="true" name="proj_meeting.approvalcontent" label="审批内容" value="<mini:param  name="proj_meeting.approvalcontent"/>"style="width:79%;height:100px"  /></textarea>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">参加审批人员
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_meeting.approvalperson" required="true" label="参加审批人员" class="mini-textbox" style="width:79%;"  required="true" value="<mini:param  name="proj_meeting.approvalperson"/>"  />
		        </td>
		    </tr>
		    <tr class="tr-project-info tr-even">
		        <td class="td-content-title">列席会议人员
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_meeting.attendance" required="true" class="mini-textbox" label="列席会议人员" style="width:79%;" value="<mini:param  name="proj_meeting.attendance"/>"/>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">缺席会议人员
		        </td>
		        <td class="td-content" colspan="3">
		            <input name="proj_meeting.absent" class="mini-textbox" required="true" label="缺席会议人员" style="width:79%;" required="true" value="<mini:param  name="proj_meeting.absent"/>"  requiredErrorText="客户名称不能为空"/>
		        </td>
		    </tr>
		    <tr class="tr-project-info tr-even">
		        <td class="td-content-title">会议记录
		        </td>
		        <td class="td-content" colspan="3">
		            <textarea name="proj_meeting.record" class="mini-textarea" label="会议记录" required="true"  style="width:79%;height:100px"   value="<mini:param  name="proj_meeting.record"/>" /></textarea>
		        </td>
		    </tr>
			<tr class="tr-project-info tr-odd">
		        <td class="td-content-title">会议结论
		        </td>
		        <td colspan="3" class="td-content">
		            <textarea name="proj_meeting.conclusion" class="mini-textarea" label="会议结论" required="true" style="width:79%;height:100px"  value="<mini:param  name="proj_meeting.conclusion"/>"   /></textarea>
		        </td>
		    </tr>
		
		</table>
	</div>
 </div>
 <script language="javascript">
		jQuery(function(){
			if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_proj_metting");};
	     });
</script>


