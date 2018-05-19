<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
    if(checkrefund()==false) return;
    return true;
}

</script>
<div id="ebank_import_form">
    <div class="mini-panel" title="网银信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="ebank_info" >
		    <tr class="tr-project-info tr-even" style="display: none">
		         <td class="td-content-title">网银id ：</td>
		         <td class="td-content"><input name="fund_ebank_data.id" id ="ebdataid"  class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.id']}"></td>
	         </tr>
	          <tr class="tr-project-info tr-odd">
	             <td class="td-content-title"  width="12%" >网银编号 ：</td>
	             <td class="td-content"  width="38%"><input name="fund_ebank_data.ebdataid" id ="ebdataidnumber"  class="mini-textbox" readOnly type="text" value="${requestScope['fund_ebank_data.ebdataid']}"></td>
	             <td class="td-content-title"  width="12%">流水号：</td>
	             <td class="td-content"  width="38%"><input name="fund_ebank_data.sn" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.sn'] }"/> </td> 
	         </tr>
	         <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">到账金额：</td>
	             <td class="td-content"><input name="fund_ebank_data.factmoney" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.factmoney'] }"/></td>
	             <td class="td-content-title">到账时间：</td>
	             <td class="td-content"><input name="fund_ebank_data.factdate" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.factdate'] }"/></td>
	         </tr>  
	         <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title">非业务金额：</td>
	             <td class="td-content"><input name="fund_ebank_data.nowithmoney" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.nowithmoney'] }"/></td>
	             <td class="td-content-title">已核销金额：</td>
	             <td class="td-content"><input id="fund_ebank_data.hadmoney"  name="fund_ebank_data.hadmoney" class="mini-textbox" readOnly value="${requestScope['fund_ebank_data.hadmoney'] }"/></td>	             
	         </tr>
	         <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">可核销金额：</td>
	             <td class="td-content"><input name="fund_ebank_data.mayopemoney" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.mayopemoney'] }"/></td>
	             <td class="td-content-title">付款人：</td>
	             <td class="td-content"><input   name="fund_ebank_data.clientname" class="mini-textbox" readOnly value="${requestScope['fund_ebank_data.clientname'] }"/></td>      
	         </tr>
	          <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title">本方银行：</td>
	             <td class="td-content"><input id="cust_name" name="fund_ebank_data.ownbank" class="mini-textbox" readOnly   type="text" value="${requestScope['fund_ebank_data.ownbank'] }" ></td>
	             <td class="td-content-title">对方银行：</td>
	             <td class="td-content"><input name="fund_ebank_data.clientbank" id="projscale" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.clientbank'] }"/> </td>
	         </tr>
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">本方账户：</td>
	             <td class="td-content"> <input name="fund_ebank_data.ownaccount" class="mini-textbox" readOnly  type="text"  value="${requestScope['fund_ebank_data.ownaccount'] }"/> </td>
	             <td class="td-content-title">对方账户：</td>
	             <td class="td-content"><input name="fund_ebank_data.clientaccount" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.clientaccount'] }"/> </td>	             
	          </tr>
	         <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">本方账号：</td>
	             <td class="td-content"><input name="fund_ebank_data.ownaccnumber" class="mini-textbox" readOnly value="${requestScope['fund_ebank_data.ownaccnumber'] }" />	 </td>
                 <td class="td-content-title">对方账号：</td>
	             <td class="td-content"><input name="fund_ebank_data.clientaccnumber" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.clientaccnumber'] }"/> </td>
	          </tr>	      
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title">历史退还金额：</td>
                 <td class="td-content"><input  name="fund_ebank_data.alreadyrefundamount" class="mini-textbox"  readOnly value="${requestScope['fund_ebank_data.alreadyrefundamount'] }"/></td>   
	             <td class="td-content-title">到账金额类型：</td>
	             <td class="td-content" colspan="3"><input name="fund_ebank_data.moneytype" class="mini-textbox" readOnly  value="${requestScope['fund_ebank_data.moneytype'] }"/></td>
	          </tr>
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title">本次退还金额：</td>
                 <td class="td-content"><input name="fund_ebank_data.refundamount" class="mini-textbox" required="true" vtype="float" value="${requestScope['fund_ebank_data.refundamount'] }"  /></td>   
	          </tr>
	          <tr class="tr-project-info tr-odd" >
	              <td class="td-content-title">历史退还备注：</td>
			      <td class="td-content" colspan="3"><input style="width:87%;height: 80px;" id="fund_ebank_data.hisrefundsummary" name="fund_ebank_data.hisrefundsummary"   label="历史退还备注" class="mini-textarea"  type="text" readonly value="${requestScope['fund_ebank_data.hisrefundsummary'] }"> </td>
	          </tr> 
	           <tr class="tr-project-info tr-odd" >
	              <td class="td-content-title">本次退还备注：</td>
			      <td class="td-content" colspan="3"><input style="width:87%;height: 80px;" id="fund_ebank_data.refundsummary" name="fund_ebank_data.refundsummary"  required="true" label="退还备注" class="mini-textarea"  type="text" value="${requestScope['fund_ebank_data.refundsummary'] }"> </td>
	          </tr>    
	</table>
	</div>
</div>
<script type="text/javascript">
  function checkrefund(){
	  var refundamount  = mini.getbyName("fund_ebank_data.refundamount").value;
	  var refundsummary = mini.getbyName("fund_ebank_data.refundsummary").value;
	  var mayopemoney   = mini.getbyName("fund_ebank_data.mayopemoney").value;
	  var may =parseInt(mayopemoney);
	  var chechnumber = /^([1-9]\d*|0)(\.\d*[1-9])?$/;
	  if(refundamount==""){
		  mini.alert("请填写退还金额！");
		  return false;
	  }
	  if(!chechnumber.exec(refundamount)){
		  mini.alert("金额必须是数字且不能以0开头!");
		  return false;
	  }
	  
	  if(refundsummary==""){
		  mini.alert("请填写退还备注！");
		  return false;
	  }
	  
      if(refundamount > may){
    	  mini.alert("退还金额不能大于可核销金额！");
    	  return false;
      }
	  return true;
  }
</script>
