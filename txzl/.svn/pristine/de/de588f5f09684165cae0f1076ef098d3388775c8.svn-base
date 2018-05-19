<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<div class="mini-panel" title="配偶情况" showCollapseButton="true" style="width: 100%;">
	<div id="mateinfoForm">
	    <table class="fillTable" cellspacing="0" cellpadding="0">
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">姓名：</td><td class="td-content" width="38%"><input name="proj.person.mateinfo.name" id =""  class="mini-textbox" value="${requestScope['proj.person.mateinfo.name'] }"> </td>
	             <td class="td-content-title" width="12%">出生年月：</td><td class="td-content" width="38%"><input name="mateinfo.a2" class="mini-datepicker"/></td>
	         </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">证件类型：</td>
	             <td class="td-content">
	                 <input name="proj.person.mateinfo.cardtype" label="证件类型" class="mini-combobox" textField="name"  required="true"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'document_type'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj.person.mateinfo.cardtype'] }" 
							   text="${requestScope['rawValue_proj.person.mateinfo.cardtype'] }" 
							   onvaluechanged="comboboxChanged" />	 
				    <input id="rawValue_proj.person.mateinfo.cardtype" name="rawValue_proj.person.mateinfo.cardtype" value="${requestScope['rawValue_proj.person.mateinfo.cardtype']}" class="mini-textbox" style="display:none"/> </td>
	             <td class="td-content-title">证件号码：</td>
	             <td class="td-content"><input name="proj.person.mateinfo.idcardno" id =""  class="mini-textbox"  value="${requestScope['proj.person.mateinfo.idcardno'] }"></td>
	          </tr>
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">工作单位：</td>
	             <td class="td-content" colspan="3"><input name="proj.person.mateinfo.workplace" id =""  class="mini-textbox" style="width: 888px;" value="${requestScope['proj.person.mateinfo.workplace'] }"></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
          		<td class="td-content-title">单位地址：</td>
           		  <td class="td-content" colspan="3"> <input name="proj.person.mateinfo.workaddr" id =""  class="mini-textbox" style="width: 888px;" value="${requestScope['proj.person.mateinfo.workaddr'] }"></td>
	          </tr>
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">单位电话：</td>
	             <td class="td-content"> <input name="proj.person.mateinfo.workphone" id =""  class="mini-textbox"  value="${requestScope['proj.person.mateinfo.workphone'] }"> </td>
	             <td class="td-content-title">邮政编码：</td>
	             <td class="td-content"> <input id="" name="proj.person.mateinfo.postcode" class="mini-textbox" value="${requestScope['proj.person.mateinfo.postcode'] }"/></td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">其他联系方式：</td>
	             <td class="td-content" colspan=3><input name="proj.person.mateinfo.othercontact" style="width: 888px;" id="" class="mini-textarea" value="${requestScope['proj.person.mateinfo.othercontact'] }"></td>
	          </tr>
		</table>
	</div>
</div>
