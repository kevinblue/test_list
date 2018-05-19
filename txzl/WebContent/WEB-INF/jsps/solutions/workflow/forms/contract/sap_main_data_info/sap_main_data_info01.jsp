<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	var form = new mini.Form("#sap_main_data_form");
	form.validate();
	if (form.isValid() == false){
		mini.alert("请填写必填选项");
		return false;
	}else{
		return true;
	}
}

</script>
<div id="sap_main_data_form">
    <div class="mini-panel" title="SAP主数据" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="sap_main_data_info" >
		     <tr class="tr-even" >
		         <td class="td-content-title">流程编号 ：</td>
		         <td class="td-content"><input name="Sap_Main_Data.sapnumber"
		          class="mini-textbox"  type="text" readonly value="${requestScope['Sap_Main_Data.sapnumber']}"></td>
	               <td class="td-content-title">申请人：</td>
	             <td class="td-content"><input name="Sap_Main_Data.creator" readonly class="mini-textbox"  value="${requestScope['Sap_Main_Data.creator'] }" /></td>
	         </tr>
	          <tr class="tr-odd">
	             <td class="td-content-title"  width="12%" >申请人部门 ：</td>
	             <td class="td-content"  width="38%"><input name="Sap_Main_Data.applicantdept"  class="mini-combobox"  required="true"
	             textField="text" valueField="text" data="[{text:'LBS产品线'},{text:'A产品线'},{text:'B产品线'}]"  value="${requestScope['Sap_Main_Data.applicantdept']}"></td>
	             <td class="td-content-title"  width="12%">申请原因：</td>
	             <td class="td-content"  width="38%"><input name="Sap_Main_Data.applicantreason" class="mini-combobox"  required="true"
	             textField="text" valueField="text" data="[{text:'新增'},{text:'修改'}]"  onvaluechanged="onApplicantReasonChanged"
	             value="${requestScope['Sap_Main_Data.applicantreason'] }"/> </td> 
	         </tr>
	         <tr class="tr-even">   
	            <td class="td-content-title">是否已经Release BMC：</td>
	             <td class="td-content"> <input name="Sap_Main_Data.alreadybmc" class="mini-combobox"  required="true"
	             textField="text" valueField="text" data="[{text:'是'},{text:'否'}]" value="${requestScope['Sap_Main_Data.alreadybmc'] }"/> </td>
	             <td class="td-content-title">是否申请Release BMC：</td>
	             <td class="td-content"><input name="Sap_Main_Data.isreleasebmc" class="mini-combobox"   required="true"
	             textField="text" valueField="text" data="[{text:'是'},{text:'否'}]" value="${requestScope['Sap_Main_Data.isreleasebmc'] }"/></td>
	         </tr>  
	         <tr class="tr-odd">   
	             <td class="td-content-title">申请时间：</td>
	             <td class="td-content"><input name="Sap_Main_Data.createdate" readonly class="mini-textbox"   value="${requestScope['Sap_Main_Data.createdate'] }"/> </td>	             
	             <td class="td-content-title">其它：</td>
	             <td class="td-content"><input  name="Sap_Main_Data.others" class="mini-textbox"  value="${requestScope['Sap_Main_Data.others'] }"/></td>	             
	         </tr>
	         <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">修改内容：</td>
	             <td class="td-content"><input name="Sap_Main_Data.edittext" class="mini-textbox"   value="${requestScope['Sap_Main_Data.edittext'] }"/></td>
	             <td class="td-content-title">设计主数据：</td>
	             <td class="td-content">
	             <div class="mini-combobox" id="designmasterdata"
						popupWidth="100%" textField="name" valueField="value" name="designmasterdata"
					    data-options="{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'SAPData'}}"   
					    multiSelect="true" onbeforeshowpopup="miniextonbeforeshowpopup" allowInput="false" dataField="datas"
					    showClose="true" oncloseclick="onCloseClick" 
					    emptyText="" required="true" label='设计主数据'>     
					</div>
	             </td>      
	         </tr>
	          <tr class="tr-odd">   
	             <td class="td-content-title">是否需要Release BMC：</td>
	             <td class="td-content"><input id="cust_name" name="Sap_Main_Data.needbmc" class="mini-combobox" required="true"
	             textField="text" valueField="text" data="[{text:'是'},{text:'否'}]" value="${requestScope['Sap_Main_Data.needbmc'] }" ></td>
	             <td class="td-content-title">下一步处理：</td>
	             <td class="td-content"><input name="Sap_Main_Data.nextstep" id="projscale" class="mini-combobox"  required="true"
	             textField="text" valueField="text" data="[{text:'IT批处理'},{text:'系统自动选择'}]" value="${requestScope['Sap_Main_Data.nextstep'] }"/> </td>
	         </tr>
	         <tr class="tr-even" id = "projnumber" style="display:none">  
	             <td class="td-content-title">项目号：</td>
	             <td class="td-content"><input name="Sap_Main_Data.projnumber" class="mini-textbox"  value="${requestScope['Sap_Main_Data.projnumber'] }"/></td>
	         </tr> 
	          <tr class="tr-odd"  id="ecrnumber" style="display:none"> 
	             <td class="td-content-title">ERC号：</td>
	             <td class="td-content"><input name="Sap_Main_Data.ecrnumber" required="true" class="mini-textbox"  value="${requestScope['Sap_Main_Data.ecrnumber'] }"/></td>
	          </tr> 
	</table>
	</div>
</div>
<script type="text/javascript"> 
function onApplicantReasonChanged(e){
	if(mini.getbyName("Sap_Main_Data.applicantreason").getValue()=="新增"){
		$("#projnumber").show();
		$("#ecrnumber").hide();
	}else{
		$("#projnumber").hide();
		$("#ecrnumber").show();
	}
}
</script>
