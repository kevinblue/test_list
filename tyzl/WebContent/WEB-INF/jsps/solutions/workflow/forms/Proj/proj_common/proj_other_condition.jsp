<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="proj_other_condition">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;">
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title" width="12%">其他商务条件：</td>
	             <td width="64%" colspan="4" style="padding-top: 4px;padding-bottom: 4px;">
	             	<input style="width:100%;height:200px" name="proj_info.othercondition"  value="<mini:param  name="proj_info.othercondition"/>"  maxLength=500, label="其他商务条件" class="mini-textarea"  type="text" > 
	             </td>
	             <td class="td-content"></td>
	          </tr>    
	 </table>
</div>
<script language="javascript">
if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("proj_other_condition");}
</script>