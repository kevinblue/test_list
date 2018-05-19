<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'通过',value:'通过'},{text:'不通过',value:'不通过'}];
			mini.get('preisagree').set({
				textField : "text",
				valueField : "value",
				data:creditStatus	
			});
		});
/* 		function savePreVote(){
				var preisagree = mini.get('preisagree').getValue();
				var  prevoteview = mini.get('prevoteview').getValue();
				if(!prevoteview || !preisagree){
					mini.unmask(document.body);
					mini.alert("请先填写是否同意和投票意见！", '提示');
					return false;
				}
				var param = {};
				var projId = '${requestScope["proj_id"]}';
				param.projId=  projId;
				param.prevoteview = prevoteview;
				param.preisagree = preisagree;
				param.docid = flowUnid;
				param.flowname=flowName;
				var flag = true;
				ajaxRequest({
					params:param,
					async : false,
					url:'${pageContext.request.contextPath}/acl/addPreVote.acl',
					timeout:30*60*1000,
					success:function(response){
						var msg = eval("("+response.responseText+")");
						if("投票失败"==msg.msg){
							mini.alert("投票失败!");
							flag=false;
						}
						mini.unmask(document.body);
					},
					failure:function(response){
						mini.unmask(document.body);
						mini.alert("投票失败！");
						flag =  false;
					}
				});
				return flag;
		} */
/* 		function getPreVote(){
			var param = {};
			var projId = '${requestScope["proj_id"]}';
			param.projId=  projId;
			param.docid = flowUnid;
			param.flowname=flowName;
			var flag = true;
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/acl/getPreVotesByUser.acl',
				timeout:30*60*1000,
				success:function(res){
					var votes= eval('('+res.responseText+')');
					if(votes.results.length>0){
						mini.getbyName("proj_info.prevoteview").setValue(votes.results[0].view);
						mini.get("preisagree").setValue(votes.results[0].isview);   
				    }else{
						mini.getbyName("proj_info.prevoteview").setValue("");
						mini.get("preisagree").setValue(""); 
				    }
					
				},
				failure:function(response){
					mini.unmask(document.body);
					flag =  false;
				}
			});
			return flag;
	} */
	</script>
	<div  id="id_project_pre_vote" title="是否通过" > 
	<div class="mini-panel" title="是否通过" showCollapseButton="true" style="width: 100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0"  >
			<tr class="tr-project-info tr-odd">  
				<td class="td-content-title" style="width:12%;">是否通过：</td>
				<td class="td-content" style="width:476px;">
					<input name="proj_info.preisagree" id="preisagree" text="ddd" class="mini-combobox" required="true"  label="投票意见" showNullItem="false"  value="${requestScope['proj_info.preisagree']}"/>
				 </td>
				 </tr>
				 <tr class="tr-project-info tr-even">
				 <td class="td-content-title" style="width:12%">意见：</td>
				<td class="td-content" colspan="3">
					<input name="proj_info.prevoteview" id="prevoteview" class="mini-textarea"  required="true"  label="意见" style="width:80%;height:200px"  value="${requestScope['proj_info.prevoteview']}"/>
				 </td>
	          </tr>
		</table>
	</div>
	</div>
<script language="javascript">
jQuery(function(){
/* 	mini.getbyName("proj_info.prevoteview").setValue("");
	mini.get("preisagree").setValue(""); */
	//getPreVote();
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_project_pre_vote");};
	
});
</script>

