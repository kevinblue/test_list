<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<div class="mini-panel" title="申请人自然情况" showCollapseButton="true" style="width: 100%;">
	<div id="personForm">
	    <table class="fillTable" cellspacing="0" cellpadding="0">
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">申请人姓名：</td>
	             <td class="td-content" width="38%"> <input name="proj_report.person.custname" id =""  class="mini-textbox" value="${requestScope['proj_report.person.custname'] }"> </td>
	             <td class="td-content-title" width="12%">性别：</td><td class="td-content" width="38%">
	             		<div name="proj_report.person.sex" class="mini-radiobuttonlist"
							data="[{id:'男',text:'男'},{id:'女',text:'女'}]"
							textField="text" valueField="id" value="${requestScope['proj_report.person.sex'] }"></div>
	             </td>
	         </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">证件类型：</td>
	             <td class="td-content">
	             	<input name="proj_report.person.idcardtype" label="证件类型" class="mini-combobox" textField="name"  required="true"
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'document_type'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_report.person.idcardtype'] }" 
							   text="${requestScope['rawValue_proj_report.person.idcardtype'] }" 
							   onvaluechanged="comboboxChanged" />	 
				    <input id="rawValue_proj_report.person.idcardtype" name="rawValue_proj_report.person.idcardtype" value="${requestScope['rawValue_proj_report.person.idcardtype']}" class="mini-textbox" style="display:none"/>	             
	             </td>
	             <td class="td-content-title">证件号码：</td>
	             <td class="td-content"><input  class="mini-textbox"  name="proj_report.person.idcardno" allowInput="false" value="${requestScope['proj_report.person.idcardno'] }"/></td>
	          </tr>
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">出生日期：</td>
	             <td class="td-content"> <input name="proj_report.person.brithdate" class="mini-datepicker" value="${requestScope['proj_report.person.brithdate'] }"/> </td>
	             <td class="td-content-title">婚烟状态：</td>
	             <td class="td-content">
		             <input name="proj_report.person.maritalstatus" class="mini-combobox" textField="name" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'marital_status'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_report.person.maritalstatus'] }" 
							   text="${requestScope['rawValue_proj_info.person.maritalstatus'] }" />
	             </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">学历：</td>
	             <td class="td-content"  colspan="3">
		             <input name="proj_report.person.school" class="mini-combobox" textField="name" 
		                  	   valueField="value"  
							   dataField="datas"
							   allowInput="false" 
							   data-options="{_params:{pid:'school'}}" 
							   onbeforeshowpopup="onbeforeshowpopup"
							   value="${requestScope['proj_report.person.school'] }" 
							   text="${requestScope['rawValue_proj_info.person.school'] }" />
	             </td>	             
	          </tr>
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">电话：</td>
	             <td class="td-content"><input id="" name="proj_report.person.phone" class="mini-textbox" value="${requestScope['proj_report.person.phone'] }"/></td>
	             <td class="td-content-title">手机号码：</td>
	             <td class="td-content"><input id="" name="proj_report.person.mobilenumber" class="mini-textbox" value="${requestScope['proj_report.person.mobilenumber'] }"/></td>
	          </tr>
	            <tr class="tr-project-info tr-odd">  
	            <td class="td-content-title">户口所在地：</td>
	            <td class="td-content" colspan="3"><input id="" name="proj_report.person.domicileplace" class="mini-textbox"  style="width:911px;" value="${requestScope['proj_report.person.domicileplace'] }"/></td>
	          </tr>
	           <tr class="tr-project-info tr-even">
	             <td class="td-content-title">邮寄地址：</td>
	             <td class="td-content" colspan=3><input id="" name="proj_report.person.mailadd"  require="true" class="mini-textarea"  style="height:70px;width:911px;" value="${requestScope['proj_report.person.mailadd'] }"></td>
	          </tr>
		</table>
	</div>
</div>

