<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script> 
 <div  id="conference_decision_form" > 
    <div   class="mini-panel" showCollapseButton="true" style="width: 100%;">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="conference_decision" >
	          <tr class="tr-project-info tr-even">
	          	<td class="td-content-title" width="10%" style="display:none">
	          		 会议编号:
	             </td>	             
	             <td class="td-content" width="40%" style="display:none">	            	             
	             	<input  name="conference_decision.confernumber" id ="conference_decision.confernumber"  type="text" 	    
	               class="mini-textbox"  allowinput="false" value="${requestScope['conference_decision.confernumber']}">
	               </td>
	             <td class="td-content-title" width="10%" >
	          		  提案人:
	             </td>	             
	             <td class="td-content" width="40%"  >	            	             
	             	<input  name="conference_decision.originator" id ="conference_decision.originator"  type="text" 	    
	               class="mini-textbox"  allowinput="false"  value="${requestScope['conference_decision.originator']}">
	               </td>  
	            <td class="td-content-title" width="10%" >
	          		  发起时间:
	             </td>	             
	             <td class="td-content" width="40%" >	            	             
	             	<input  name="conference_decision.originatime" id ="conference_decision.originatime"  type="date" format="yyyy-MM-dd"
	               allowinput="false"   class="mini-textbox"    value="${requestScope['conference_decision.originatime']}">
	               </td>         
	          </tr>	  
	           <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="10%" >
	          		议案标题:
	             </td>	             
	             <td class="td-content" width="40%" colspan="3" >	            	             
	             	<input  width="70%" name="conference_decision.confername" id ="conference_decision.confername" required="true" type="text" 
	               label="议案标题" class="mini-textbox"   value="${requestScope['conference_decision.confername']}">
	               </td>         
	          </tr>
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" > 
	             	议案内容:
	             </td>
	             <td  colspan="3" width="40%" style="padding-top: 5px;padding-bottom: 5px;" > 
	             <input  width="70%" height="250px" class="mini-textarea"  name="conference_decision.conferabstract" id ="conference_decision.conferabstract"  label="议案内容:" 
	              
	             value="${requestScope['conference_decision.conferabstract']}"  type="textarea"  >  </td>
	        </tr>
	        <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="10%" >
	          		决策点:
	             </td>	             
	             <td class="td-content" width="40%" colspan="3" >	            	             
	             	<input  width="70%" height="100px" name="conference_decision.decisionpoint" id ="conference_decision.decisionpoint" required="true" type="text" 
	               label="决策点" class="mini-textarea"   value="${requestScope['conference_decision.decisionpoint']}">
	               </td>         
	          </tr>  
		</table>
	</div>
</div> 


<script language="javascript">

jQuery(function(){
	if('${param.isView}' == 'true' /* || isViewHistoryTask==true */)
	{
		miniui_ext.disableFormFields("conference_decision_form");
	};
}); 
/* function changeDate(e){
	letter_approval.originatime=mini.formatDate(mini.getbyName("letter_approval.originatime").getValue(),'yyyy-MM-dd');
} */
</script> 
