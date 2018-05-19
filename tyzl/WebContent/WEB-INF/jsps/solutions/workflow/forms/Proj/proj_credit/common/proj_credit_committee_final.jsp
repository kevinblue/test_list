<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
	<div class="mini-panel" title="投票信息汇总" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	           <tr class="tr-project-info tr-odd" >  
	             <td class="td-content-title" style="width:13.5%;">同意票数：</td>
	             <td class="td-content">
	                  <input name="proj_info.agreenum" id ="id_agreenum"  class="mini-textbox" label="同意票数" readOnly type="text" value="<mini:param  name="proj_info.agreenum"/>">
				 </td>
				  <td class="td-content-title" style="width:13.5%;">否决票数：</td>
				  <td class="td-content"> 
				     <input name="proj_info.cagreenum" id ="id_cagreenum"  class="mini-textbox" label="同意票数" readOnly type="text" value="<mini:param  name="proj_info.cagreenum"/>">
				  </td>
	              <td class="td-content">
				  </td>
				  <td class="td-content-title" style="width:13.5%;">再议票数：</td>
	              <td class="td-content" >
	              <input name="proj_info.noagreenum" id ="id_noagreenum"  class="mini-textbox" label="同意票数" readOnly type="text" value="<mini:param  name="proj_info.noagreenum"/>">				
				  </td>
				 </tr>
				 <tr class="tr-project-info tr-even">
				  <td class="td-content-title" style="width:13.5%;">意见汇总</td>
	              <td class="td-content" colspan="3" >
	              <div id="votes" style="width:100%">
	              
	              </div>
	              </td>
				 </tr>
		</table>
	</div>
	<script type="text/javascript">
	    var needjudge=true;
		var param = {};
		var projNumber = "<mini:param  name="proj_info.projid"/>";
		var docid  = "<mini:param  name="currentProcessInstanceDBID"/>";
		param.projNumber=  projNumber;
		param.docid = docid;
		ajaxRequest({
			params:param,
			url:'${pageContext.request.contextPath}/acl/getVotes.acl',
			timeout:30*60*1000,
			success:function(response){
				var votes = eval('(' + response.responseText + ')').results;
				var agreeNum=0;
				var noagreeNum=0;
				var cagreeNum=0;
				if(votes.length > 0 ){
					for(var i = 0 ; i < votes.length;i++){
						var vote = votes[i];
						if(vote.isview=="同意"){
							agreeNum=agreeNum+1;
					    }else if(vote.isview=="否决"){
					    	cagreeNum=cagreeNum+1;
					    }else{
					    	noagreeNum=noagreeNum+1;
						}    
						$('#votes').append("<table width='100%'><tr>");	
						$('#votes').append("<td width='100px'>评审委员：</td>");	
						$('#votes').append("<td>"+vote.name+"</td></tr>");	
						$('#votes').append("<tr>");	
						$('#votes').append("<td>投票意见：</td>");	
						$('#votes').append("<td>"+vote.isview+"</td>");	
						$('#votes').append("<tr>");	
						$('#votes').append("<td>意见：</td>");	
						$('#votes').append("<td style='word-wrap:break-word;word-break:break-all;'>"+vote.view+"</td>");	
						$('#votes').append("</tr></table>");
					 	if(i<votes.length-1){
						$('#votes').append("<hr>");}
					}
					mini.get("id_agreenum").setValue(agreeNum);
					mini.get("id_cagreenum").setValue(cagreeNum);
					mini.get("id_noagreenum").setValue(noagreeNum);
					if('<mini:param name="autoJudge"/>' == 'true'){
						//评委秘书节点，信审结论类型需要根据投票结果初始化数据，如果同意票数大于反对票数，默认选中审批通过，反之默认选中审批否决
						if(agreeNum+cagreeNum>noagreeNum){
							mini.get("proj_info.creditisapproval").setValue("credit_type_@11");
							mini.get("proj_info.creditisapproval").setText("审批通过");
							mini.get("rawValue_proj_info.creditisapproval").setValue("审批通过");
						}else{
							mini.get("proj_info.creditisapproval").setValue("credit_type_@103");
							mini.get("proj_info.creditisapproval").setText("审批否决");
							mini.get("rawValue_proj_info.creditisapproval").setValue("审批否决");
						}
					}
					
					
				}
			},
			failure:function(response){
			}
		});
	</script>


