<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String applyid = request.getParameter("applyid");//ID
	String opertype = request.getParameter("opertype");
%>
<script type="text/javascript">
var applyid='<%=applyid%>'
</script>
<script type="text/javascript">
	//是否保存   
	function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}
	//保存成功提交后，后台返回
	function saveCallBack() {
		return true;
	}
	//是否提交    
	function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
	{
		return true;
	}
</script>
<!-- 撑满布局 -->
<div id="workflow_form">
	<div id="id_table_application_detail">
		<div id="mini-panel" title="申请基本信息" showCollapseButton="true"
			style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0"
				style="width: 100%;">
				<tr style="background: #468CC8; height: 32px">
					<td style="padding-left: 10px" colspan='4'><font color="white">申请基本信息</font></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title" width="12%">资产专员：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applyusername" class="mini-textbox " type='text'
						required="true" readonly="readonly"
						value="${requestScope['applyusername']}" /></td>
					<td class="td-content-title" width="12%">申请年月：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applydate" class="mini-datepicker" type="date"
						format="yyyy-MM" readonly="readonly" required="true"
						allowinput="false" value="${requestScope['applydate']}" /></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title" width="12%">申请状态：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="applystatus" class="mini-textbox" required="true"
						readonly="readonly"
						value="${empty requestScope['applystatus']?'未审核': requestScope['applystatus']}" />
					</td>
					<td class="td-content-title" width="12%">申请编号：</td>
					<td class="td-content" width="38%">
						<input width="55%" name="applyno"  class="mini-textbox" required="true" readonly="readonly" value="${requestScope['applyno']}"/>
						<input width="55%" name="applysid"  style="display:none"  class="mini-textbox" readonly="readonly" value="${requestScope['applyid']}"/>
					</td>
				</tr>
				<tr class="tr-odd" style="height: 40px">
					<td class="td-content-title" width="12%" height="70px">备注：</td>
					<td class="td-content" colspan="3"><textarea
							style="width: 81%; height: 90%" name="applymemo" readonly="readonly"
							class="mini-textarea" value="${requestScope['applymemo']}"></textarea>
					</td>
				</tr>
				<tr class="tr-even">
				
					<td class="td-content-title" width="12%">创建人：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="creatorname" class="mini-textbox" readonly
						value="${requestScope['creator']}" /></td>
					<td class="td-content-title" width="12%">创建时间：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="createdate" class="mini-textbox" readonly
						value="${requestScope['createdate']}" "/>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title" width="12%">修改人：</td>
					<td class="td-content" width="38%"><input width="55%" name="modificatorname" class="mini-textbox" readonly value="${requestScope['modificatorname']}"/></td>
					<td class="td-content-title" width="12%">修改时间：</td>
					<td class="td-content" width="38%"><input width="55%"
						name="modifydate" class="mini-textbox" readonly
						value="${requestScope['modifydate']}" /></td>
				</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">百度：</td>
				<td class="td-content" width="38%"><a href="http://www.baidu.com" target="_blank">www.baidu.com</a></td>
				<td class="td-content-title" width="12%">执行人网：</td>
				<td class="td-content" width="38%"><a href="http://zhixing.court.gov.cn/search/" target="_blank">zhixing.court.gov.cn</a></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title" width="12%">裁判文书网：</td>
				<td class="td-content" width="38%"><a href="http://wenshu.court.gov.cn/ " target="_blank">wenshu.court.gov.cn</a></td>
				<td class="td-content-title" width="12%"></td>
				<td class="td-content" width="38%"></td>
			</tr>
			</table>
		</div>
	</div>
</div>
<!-- 页签 -->
<div id="tabDeatils" class="mini-tabs" activeIndex="0"
	style="width: 100%;" bodyStyle="padding:0;border:0;">
	<div title="申请详细信息" name="applicationcust">
		<jsp:include page="asset_network_moniter_application_detail.jsp"><jsp:param
				value="true" name="isView" /></jsp:include>
	</div> 
</div>
<script>
//初始化
mini.parse("id_table_application_detail");
var form = new mini.Form("id_table_application_detail");
	jQuery(function() {
		$(".mini-textbox-readOnly .mini-textbox-border").css("background",
				"#F0F0F0");
		$(".mini-buttonedit-readOnly .mini-buttonedit-border").css(
				"background", "#F0F0F0");
		$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display",
				"none");
	});
</script>
