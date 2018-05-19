<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'同意',value:'同意'},{text:'不同意',value:'不同意'}];
			mini.get('isagree').set({
				textField : "text",
				valueField : "value",
				data:creditStatus	
			});
		});
		function saveVote(){   
			   
			
				//var isagree = mini.get('isagree').getValue();//
				//var  voteview = mini.get('voteview').getValue();
				var isagree = $("#id_processedResultShowTxt").val();//审批结论
				var  voteview = $("#text_selectedAdvise").val();//审批意见
				if(!voteview || !isagree){
					mini.unmask(document.body);
					//mini.alert("请先填写是否同意和投票意见！", '提示');
					//return false;
				}
				var param = {};
				var projId = '${requestScope["proj_id"]}';
				
				param.projId=  projId;
				param.voteview = voteview;
				param.isagree = isagree;
				param.docid = flowUnid;
				param.flowname=flowName;
				var flag = true;
				ajaxRequest({
					params:param,
					async : false,
					url:'${pageContext.request.contextPath}/acl/addVote.acl',
					timeout:30*60*1000,
					success:function(response){
						mini.unmask(document.body);
						var msg = eval("("+response.responseText+")");
						if("投票失败"==msg.msg){
							mini.alert("投票失败!");
							flag=false;
						}
					},
					failure:function(response){
						mini.unmask(document.body);
						mini.alert("投票失败！");
						flag =  false;
					}
				});
				return flag;
		}
		function getVote(){
			var param = {};
			var projId = '${requestScope["proj_id"]}';
			param.projId=  projId;
			param.docid = flowUnid;
			param.flowname=flowName;
			var flag = true;
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/acl/getVotesByUser.acl',
				timeout:30*60*1000,
				success:function(res){
					//alert(res.responseText);
					var votes= eval('('+res.responseText+')');
					if(votes.results.length>0){
						if("null"==votes.results[0].view){
						mini.getbyName("proj_info.voteview").setValue("");	
						}else{
						mini.getbyName("proj_info.voteview").setValue(votes.results[0].view);
						}
						mini.get("isagree").setValue(votes.results[0].isview);   
				    }else{
						mini.getbyName("proj_info.voteview").setValue("");
						mini.get("isagree").setValue(""); 
				    }
				},
				failure:function(response){
					mini.unmask(document.body);
					flag =  false;
				}
			});
			return flag;
	}
	</script>
	<div style="display: none" id="id_project_vote" title="上会投票信息" > 
    <div class="mini-panel" title="上会投票信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title" style="width:12%;">投票意见：</td>
	             <td class="td-content" style="width:476px;">
		             <input name="proj_info.isagree" id="isagree" class="mini-combobox"   label="投票意见" showNullItem="false"  value="${requestScope['proj_info.isagree']}"/>
				 </td>
				 </tr>
				 <tr class="tr-project-info tr-even">
				 <td class="td-content-title" style="width:12%">意见：</td>
	             <td class="td-content" colspan="3">
		             <input name="proj_info.voteview" id="voteview" class="mini-textarea"  label="意见" style="width:80%;height:100px"  value="${requestScope['proj_info.voteview']}"/>
				 </td>
	          </tr>
		</table>
		
	</div>
		
	</div>    
<script language="javascript">
jQuery(function(){
/* 	mini.getbyName("proj_info.voteview").setValue("");
	mini.get("isagree").setValue(""); */
	getVote();
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_project_vote");};
	
});
</script>

