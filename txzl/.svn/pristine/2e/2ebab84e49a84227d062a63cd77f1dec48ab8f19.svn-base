<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div id="proj_change_explain" title="项目变更说明">
	    <table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
	
				<tr class="tr-even">
					<td class="td-content-title" width="12%">项目变更类型：</td>
					<td class="td-content" width="38%">
					<input  name="proj_info.projchangetype" label="项目变更类型" class="mini-combobox"  textField="name"  required="true"
		                  	   valueField="value"  
							   dataField="datas"
							   width="60%"
							   allowInput="false" 
							   data-options="{_params:{pid:'projChangeType'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_info.projchangetype'] }" 
							   text="${requestScope['rawValue_proj_info.projchangetype']}" 
							   onvaluechanged="comboboxChanged" />
				    <input id="rawValue_proj_info.projchangetype" name="rawValue_proj_info.projchangetype" value="${requestScope['rawValue_proj_info.projchangetype']}"  class="mini-textbox" style="display:none"/>
	               </td>
	               <td class="td-content-title" width="12%"></td>
				   <td class="td-content" width="38%"></td>
				</tr>
		
		          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">项目变更说明：</td>
	             <td colspan=3 style="padding-top: 4px;padding-bottom: 4px;">
		             <input style="width:72%;height:120px" name="proj_info.projchangeexplain" label="变更说明"  value="${requestScope['proj_info.projchangeexplain'] }"  label="开票说明" class="mini-textarea" maxLength="500" type="text" > 
	             </td>
	           <td>  </td>
	          </tr>

		
   	</table>
</div>
<!-- 	<div style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0"
			style="width: 100%" id="contract_base_info">
		   <tr height=5 style='height:5.0pt'>          
				<td class="td-content-title" width="30%" align="center"><h4 >实质性风险变化</h4>
			
				</td>
				<td class="td-content" width="30%" align="center"><h4>实质性风险未发生变化(方案劣化)</h4>
			
				</td>
				<td class="td-content-title" width="30%" align="center"><h4>实质性风险未发生变化(方案优化)</h4>
				
				</td>
			
			</tr>

             <tr height=216 style='height:162.0pt'>
  <td height=216 class=xl66 width=241 style='height:162.0pt;width:181pt' align="left">
  	<i>发生以下任一情况时视为实质性风险发生变化：</i><br>
  1.
  担保方式减少<br>
    2. 承租人变更<br>
    3. 其他（信审认为存在影响项目安全的变化因素，需要启动资评变更的情况）<br><br><br><br><br><br><br></td>
  <td class=xl66 width=241 style='width:181pt' align="left">
  	<i>实质性风险未发生变化时，发生如下任一情况时视为方案劣化：</i><br>
  	1. IRR降低<br>
    2. 期初支付改为期末支付<br>
    3. 净融资额增加<br>
    4. 付款周期变长<br>
    5. 租赁期限变化<br>
    6. 信审有效期过期<br>
    7. 直租项目付款节奏劣于原先审批<br>
    8. 供应商、厂商更改（直租）<br>
    9.<span style='mso-spacerun:yes'>&nbsp; </span>租赁物发生实质性变更<br>
    10. 其它情况（总体来说条件劣于原先审批）</td>
  <td class=xl66 width=241 style='width:181pt' align="left">
  <i>实质性风险未发生变化且不属于方案劣化时，发生如下任一情况时视为方案优化：</i><br>
  1. IRR升高<br>
    2. 期末付变为期初付款<br>
    3. 净融资额减少<br>
    4. 付款周期变短<br>
    5. 担保方式增多<br>
    6. 直租项目付款节奏优于原先审批<br>
    7. 风控清单中的承租人名称、担保人名称书写错误<br>
    8.<span style='mso-spacerun:yes'>&nbsp; </span>租赁物件名称，或型号发生变化<br>
    9. 其它情况（总体来说条件优于原先审批）<br><br></td>
 </tr>
			</table>
</div> -->
<script language="javascript">
	 
	if('${param.isView}' == 'true'||isViewHistoryTask==true){ 
         miniui_ext.disableFormFields("proj_change_explain");
    }

</script>
