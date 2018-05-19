<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="credit_result_form" title="信审信息">
<div  title="信审信息" showCollapseButton="true" class="mini-panel" style="width:100%">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">信审结论类型：</td>
			 		 <td class="td-content" width="38%">
                	 <input name="proj_info.projstatustype" id="proj_info.projstatustype" label="信审结论类型" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"  
				                  	   data-options="{_params:{pid:'CreditType'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" 
									   value="${requestScope['proj_info.projstatustype'] }"
									   text="${requestScope['rawValue_proj_info.projstatustype'] }" 
									   onvaluechanged="comboboxChanged"/>
					<input id="rawValue_proj_info.projstatustype" name="rawValue_proj_info.projstatustype" value="${requestScope['rawValue_proj_info.projstatustype']}"  class="mini-textbox" style="display:none"/>
                	</td>
					<td class="td-content-title">信审通过日期：</td>
					<td class="td-content">
						<input id="proj_info.approvedate" name="proj_info.approvedate" class="mini-datepicker" value="${requestScope['proj_info.approvedate'] }" label="信审通过时间"  required="true" allowInput="false"/></td>		
				</td>
				</tr>
	          <tr class="tr-credit-info tr-odd">
	             <td class="td-content-title">信审结论：</td>
	             <td class="td-content" colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		     	 <input id="approveconclusion" name="proj_info.approveconclusion" style="width:72%;height:150px" label="信审结论：" required="true" name="proj_info.approveconclusion" 
		     			value="${requestScope['proj_info.approveconclusion'] }" class="mini-textarea"  type="text" /> 
		         </td>       
	          </tr>
	</table>
</div>
</div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("credit_result_form");}
</script>