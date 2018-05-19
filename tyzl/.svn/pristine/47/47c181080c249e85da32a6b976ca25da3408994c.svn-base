<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/minidict" prefix="mini"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'是',value:'0'},{text:'否',value:'1'}];
			mini.get('chairmanagree').set({
				textField : "text",
				valueField : "value",
				data:creditStatus	
			});
		});
	</script>
	    
    <div class="mini-panel" title="信审信息" showCollapseButton="true" style="width: 99%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">董事长裁决：</td>
	             <td class="td-content">
	             <input name="proj_info.chairmanagree" id="chairmanagree" class="mini-combobox" showNullItem="true" value="<mini:param  name="proj_info.chairmanagree"/>"/>
				 <font class="required-tag">*</font>
				 </td>
	          </tr>
		</table>
	</div>


