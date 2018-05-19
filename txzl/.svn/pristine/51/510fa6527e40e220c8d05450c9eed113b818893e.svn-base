<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <%-- <input name="proj_info.custInfo" id="proj_info.custInfo" type="hidden"  value="${requestScope['proj_info.custInfo'] }"/>
    <input name="proj_info.projstatus" id="proj_info.projstatus" type="hidden"  value="${requestScope['proj_info.projstatus'] }"/>
    <input name="proj_info.custclass" id="proj_info.custclass" type="hidden"  value="${requestScope['proj_info.custclass'] }"/> --%>
    <div id="add_file_change_info_form" title="补充说明">

	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          	         	     
	           <tr class="tr-odd">
	             <td class="td-content-title" width="10%">资料名称：</td>
	             <td class="td-content" width="38%"><input name="proj_info.fileaddname" id ="risk_name"  class="mini-textbox" label="资料名称"  type="text" value="${requestScope['proj_info.fileaddname']}"></td>	             
	           </tr> 	          	         
	           <tr class="tr-odd">
	             <td class="td-content-title">补充资料说明：</td>
	             <td colspan="2" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:50%;height:70px" label="补充资料说明" name="proj_info.fileaddexplain" value="${requestScope['proj_info.fileaddexplain'] }" class="mini-textarea"  type="text" >  </td>
	           </tr>    
	</table>
	
</div>

<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("add_file_change_info_form");};
});
</script>
