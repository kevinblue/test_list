<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %>

<!-- 弹出退回列表开始 -->
<div id="__backRouteChoseWindow" class="mini-window" title="退回详情" style="display:none;width:600px;padding:10px;">
	<div id="id_backRouteChoseWindow" class="route-choose" style="width: 100%;position: relative;">
		<center>
			<c:if test="${0 == fn:length(back_activityTaskUsersList)}">
				<script type="text/javascript">
					isShowBackChosePersonWindow = false;
				</script>
			</c:if>
			<script type="text/javascript">
				backChosePersonArr = [];
			</script>
			<div style="width:98%;text-align:left;overflow-x:hidden">
				<c:if test="${not empty back_activityTaskUsersList}">
					<c:if test="${fn:length(back_activityTaskUsersList) > 0}">
						<table width="100%">
							<c:set var="pageSize" value="4"></c:set>
							<tr>
								<td>
									<label style="color: red">退回后提交模式</label>
								</td>
							</tr>
							<tr>  
								<td colspan="2" style="padding-left:10px;">
								    <input type="radio" checked="checked" name="backmodel"  style="position:relative;top:3px;"  value="DIRECTPUSH"></input>直接推送
									<input  type="radio"  name="backmodel" style="position:relative;top:3px;" value="STEPPUSH" ></input>逐级推送
									
								</td>
							</tr>
							<tr>
								<td style="color: red;">**********************************************************************************</td>
							</tr>
							<c:forEach items="${back_activityTaskUsersList}" var="activityTaskUsers" varStatus="status">
								<c:set var="routePath" value="${activityTaskUsers.routeInfo.currentTransition.name}"></c:set>
								<c:set var="activityName" value="${activityTaskUsers.currentTaskUsers.activityDetail.activityName}"></c:set>
								<c:set var="parentActivityName" value="${activityTaskUsers.routeInfo.parentActivityName}"></c:set>
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.dealUsers)}" ></c:set>
								<c:set var="checkType" value="checkbox"></c:set>
								<c:set var="dealMethod" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethod}" ></c:set>
								<c:set var="dealMethodChineseName" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethodChineseName}" ></c:set>
								<c:set var="passedCount" value="${activityTaskUsers.currentTaskUsers.activityDetail.passedCount}" ></c:set>
								
								<c:if test="${'Single' eq dealMethod}">
									<c:set var="checkType" value="radio"></c:set>
								</c:if>
								<script type="text/javascript">
									backChosePersonArr.push('back_chose_${routePath}_${activityName}');
								</script>
								<tr identity="back_chose_tr_${routePath}">
									<td colspan=2>
										<div> 
											${activityName}
											<c:if test="${0 != len}">
												【${dealMethodChineseName}
												<c:if test="${'NPassed' eq dealMethod}">
													,处理人数&nbsp;${passedCount}&nbsp;
												</c:if>
												】
												<c:if test="${'Single' ne dealMethod}">
													&nbsp;&nbsp;<input id="back_chose_${routePath}_${parentActivityName}_checkedAll_actor" style="position:relative;top:3px;" type="checkbox" onClick="javascript:doCheckedAll(this.checked,'back_chose_${routePath}_${activityName}','back_chose_${routePath}_${parentActivityName}','actor');"/>&nbsp;全选
												</c:if>
											</c:if>
										</div>
									</td>
								</tr>
								<c:if test="${0 == len}">
									<tr identity="back_chose_tr_${routePath}">
										<td colspan=2 >
											<input type="radio" style="position:relative;top:3px;" 
												name="back_chose_${routePath}_${activityName}" 
												login_name="NULL" routePath="${routePath}" 
												node_name="${activityName}"
												onclick="backRejectChecked('back_chose_${routePath}_${parentActivityName}');"
												parent_node_name="${parentActivityName}"
												parent_name	= "back_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod = "${dealMethod}"
												passedCount = "${passedCount}"
												routePath="${routePath}"
											/> ${activityName}
										</td>
									</tr> 
								</c:if>
								<c:forEach items="${activityTaskUsers.currentTaskUsers.dealUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<table>
										<tr identity="back_chose_tr_${routePath}">
									</c:if>
										<td style="padding-left:10px;">
											<input 
												style="position:relative;top:3px;" 
												type="${checkType}" name="back_chose_${routePath}_${activityName}" 
												node_name="${activityName}" real_name="${dealUser.realname}" 
												delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
												onclick="backRejectChecked('back_chose_${routePath}_${parentActivityName}');"
												parent_node_name="${parentActivityName}"
												parent_name	= "back_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod = "${dealMethod}"
												passedCount = "${passedCount}"
												routePath="${routePath}"
											/> ${dealUser.realname}
										</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="back_chose_tr_${routePath}">
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
												</tr>
												</table>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:forEach>
						</table>
					</c:if>
				</c:if>
			</div>
		</center>
	</div>
	<div style="text-align: center;width: 100%;position: relative;padding: 10px 0px 5px 0px;">
		<a class="mini-button" onclick='__doChoseRouteOperBack'>确定</a>
		<a class="mini-button" onclick='__doChoseRouteOperBackClose'>取消</a>
	</div>
</div>

<script type="text/javascript">
function __doChoseRouteOperBack(){
	doChoseRouteOper("back");
}

function __doChoseRouteOperBackClose(){
	mini.get('__backRouteChoseWindow').destroy();
}
if(mini.get('__backRouteChoseWindow')){
	mini.get('__backRouteChoseWindow').destroy();
}
mini.parse(document.getElementById('__backRouteChoseWindow'));

</script>
<!-- 弹出退回列表结束 -->