<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script> 

 <div  id="letter_approval_form"  title="公函审批"> 
    <div   class="mini-panel" title="公函审批" showCollapseButton="true" style="width: 100%;">
	<table class="fillTable" cellspacing="0" cellpadding="0" id="letter_approval" >
	          <tr class="tr-project-info tr-even">
	          	<td class="td-content-title" width="10%" >
	          		 函件编号:
	             </td>	             
	             <td class="td-content" width="40%"  >	            	             
	             	<input  name="letter_approval.letternumber" id ="letter_approval.letternumber"  type="text" 	    
	               class="mini-textbox"  allowinput="false" value="${requestScope['letter_approval.letternumber']}">
	               </td> 
	             <td class="td-content-title" width="10%" >
	          		  发起人:
	             </td>	             
	             <td class="td-content" width="40%"  >	            	             
	             	<input  name="letter_approval.originator" id ="letter_approval.originator"  type="text" 	    
	               class="mini-textbox"  allowinput="false"  value="${requestScope['letter_approval.originator']}">
	               </td>  
	                  
	          </tr>	  
	          <tr class="tr-project-info tr-even">
 
	            <td class="td-content-title" width="10%" >
	          		  发起时间:
	             </td>	             
	             <td class="td-content" width="40%" >	            	             
	             	<input  name="letter_approval.originatime" id ="letter_approval.originatime"  type="date" format="yyyy-MM-dd"
	               allowinput="false"   class="mini-textbox"    value="${requestScope['letter_approval.originatime']}">
	               </td>
	               <td class="td-content-title">项目名称：</td>
	             <td class="td-content" >
		             <input id="letter_approval.projname" name="letter_approval.projname" label="项目名称" class="mini-buttonedit mini-queryinput" allowInput="false"
							text="${empty requestScope['rawValue_letter_approval.projname'] ? '' : requestScope['rawValue_letter_approval.projname'] }"
							value="${empty requestScope['letter_approval.projname'] ? '' : requestScope['letter_approval.projname'] }"
							/>
					 <input id="rawValue_letter_approval.projname" name="rawValue_letter_approval.projname" value="${empty requestScope['rawValue_letter_approval.projname'] ? '' : requestScope['rawValue_letter_approval.projname'] }" class="mini-textbox" style="display:none"/>
		         </td>         
	          </tr>	 
	           <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="10%" >
	          		函件名称 :
	             </td>	             
	             <td class="td-content" width="40%" colspan="2" >	            	             
	             	<input  width="70%" name="letter_approval.lettername" id ="letter_approval.lettername" required="true" type="text" 
	               label="函件名称" class="mini-textbox"   value="${requestScope['letter_approval.lettername']}">
	               </td>         
	          </tr>
	                 
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="10%" >
	          		收件人:
	             </td>	             
	             <td class="td-content" width="40%" colspan="2" >	            	             
	             	<input  width="70%" name="letter_approval.recipient" id ="letter_approval.recipient"  type="text" 
					 class="mini-textbox"   value="${requestScope['letter_approval.recipient']}">
	               </td>         
	          </tr>
	            
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" > 
	             	函件内容描述:
	             </td>
	             <td  colspan="2" width="40%" style="padding-top: 5px;padding-bottom: 5px;" > 
	             <input  width="70%" height="80px" class="mini-textarea"  name="letter_approval.letterdescription" id ="letter_approval.letterdescription"  label="函件内容描述:" 
	              
	             value="${requestScope['letter_approval.letterdescription']}"  type="text"  >  </td>
	        </tr>  
		</table>
	</div>
</div> 


<script language="javascript">

jQuery(function(){
	if('${param.isView}' == 'true' /* || isViewHistoryTask==true */)
	{
		miniui_ext.disableFormFields("letter_approval_form");
	};
});
jQuery(function(){
	tenwa.createQueryInput({
		id:'letter_approval.projname',
		label:'项目名称',
		textField:"name",
      	valueField:"value",  
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_letter_approval.projname").setValue(rowData.name);
		},
		params: {
			xmlFileName: '/eleasing/jsp/letterapproval/letterapproval_projinfo.xml'
		}
	});
});
function changeDate(e){
	letter_approval.originatime=mini.formatDate(mini.getbyName("letter_approval.originatime").getValue(),'yyyy-MM-dd');
}
</script> 
