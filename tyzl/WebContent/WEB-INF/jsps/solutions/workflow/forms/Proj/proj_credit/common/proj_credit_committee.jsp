<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
	<script type="text/javascript">
		jQuery(function(){
			var creditStatus = [{text:'同意',value:'同意'},{text:'否决',value:'否决'},{text:'再议',value:'再议'}];
			mini.get('isagree').set({
				textField : "text",
				valueField : "value",
				data:creditStatus	
			});
		});
		function saveVote(){
				var isagree = mini.get('isagree').getValue();
				var  voteview = mini.get('voteview').getValue();
				if(!voteview || !isagree){
					mini.unmask(document.body);
					mini.alert("请先填写是否同意和投票意见！", '提示');
					return false;
				}
				var param = {};
				var projNumber = '<mini:param name="proj_info.projid"/>';
				
				param.projNumber=  projNumber;
				param.voteview = voteview;
				param.isagree = isagree;
				param.docid = flowUnid;
				param.flowname=flowName;
				var flag = true;
				ajaxRequest({
					params:param,
					url:'${pageContext.request.contextPath}/acl/addVote.acl',
					timeout:30*60*1000,
					success:function(response){
						mini.unmask(document.body);
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
			var projNumber = '<mini:param name="proj_info.projid"/>';
			param.projNumber=  projNumber;
			param.docid = flowUnid;
			param.flowname=flowName;
			var flag = true;
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/acl/getVotesByUser.acl',
				timeout:30*60*1000,
				success:function(res){
					var votes= eval('('+res.responseText+')');
					if(votes.results.length>0){
						mini.getbyName("proj_info.voteview").setValue(votes.results[0].view.replace(/<br>/ig, "\n"));
						mini.get("isagree").setValue(votes.results[0].isview);   
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
	<div  id="id_project_vote" title="上会投票信息" > 
    <div class="mini-panel" title="上会投票信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0"  >
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title" style="width:12%;">投票意见：</td>
	             <td class="td-content" style="width:476px;">
		             <input name="proj_info.isagree" id="isagree" class="mini-combobox" required="true"  label="投票意见" showNullItem="false"  value="<mini:param  name="proj_info.isagree"/>"/>
					 <font class="required-tag">*</font>
				 </td>
				 </tr>
				 <tr class="tr-project-info tr-even">
				 <td class="td-content-title" style="width:12%">意见：</td>
	             <td class="td-content" colspan="3">
		             <textarea name="proj_info.voteview" id="voteview" class="mini-textarea"  required="true"  label="意见" style="width:80%;height:200px" maxLength="990" maxLengthErrorText="不能超过990个字符" value="<mini:param  name="proj_info.voteview"/>"></textarea>
					 <font class="required-tag">*</font>
				 </td>
	          </tr>
		</table>
	</div>
	</div>    
<script language="javascript">
jQuery(function(){
	mini.getbyName("proj_info.voteview").setValue("");
	mini.get("isagree").setValue("");
	getVote();
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("id_project_vote");};
	
});
</script>

