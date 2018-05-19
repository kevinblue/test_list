<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %>

<div id="__allRouteChoseWindow" class="mini-window" title="任意下一步详情" style="display:none;width:600px;padding:10px;">
	<div id="id_allRouteChoseWindow" class="route-choose" style="width: 100%;position: relative;">
		<center>
			<script type="text/javascript">
				allChosePersonArr = [];
			</script>
			<div style="width:96%;text-align:left;">
				<table width="100%">
					<c:set var="pageSize" value="2"></c:set>
					<tr style='height:20px;'><td></td></tr>
					<c:forEach items="${all_activityTaskUsersList}" var="activityTaskUsers" varStatus="status">
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
							allChosePersonArr.push('all_chose_${routePath}_${activityName}');
						</script>
						<tr identity="all_chose_tr_${routePath}">
							<td colspan=2>
								<div> 
									${activityName}
									<c:if test="${0 != len}">
																【
											${dealMethodChineseName}
											<c:if test="${'NPassed' eq dealMethod}">
												,处理人数&nbsp;${passedCount}&nbsp;
											</c:if>
											】
										<c:if test="${'Single' ne dealMethod}">
										&nbsp;&nbsp;<input id="all_chose_${routePath}_${parentActivityName}_checkedAll_actor" style="position:relative;top:3px;" type="checkbox" onClick="javascript:doCheckedAll(this.checked,'all_chose_${routePath}_${activityName}','all_chose_${routePath}_${parentActivityName}','actor');"/>&nbsp;全选
										</c:if>
									</c:if>
								</div>
							</td>
						</tr>
						<c:if test="${0 == len}">
						<tr identity="all_chose_tr_${routePath}">
							<td style="padding-left:10px;">
								<input 
									style="position:relative;top:3px;" 
									type="radio" name="all_chose_${routePath}_${activityName}" 
									node_name="radio" 
									routePath="${routePath}"
									onclick="allRejectChecked('all_chose_${routePath}_${parentActivityName}');"
									parent_node_name="${parentActivityName}"
									parent_name	= "all_chose_${routePath}_${parentActivityName}" 
									flag="actor"
									dealMethod = "${dealMethod}"
									passedCount = "${passedCount}"
								/>
								${activityName}
							</td>
						</tr> 
						</c:if>
						<c:forEach items="${activityTaskUsers.currentTaskUsers.dealUsers}" var="dealUser" varStatus="userStatus">
							<c:if test="${0 == userStatus.index}">
								<tr identity="all_chose_tr_${routePath}">
							</c:if>
							<td style="padding-left:10px;">
								<input 
									style="position:relative;top:3px;" 
									type="${checkType}" name="all_chose_${routePath}_${activityName}" 
									node_name="${activityName}" real_name="${dealUser.realname}" 
									delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
									onclick="allRejectChecked('all_chose_${routePath}_${parentActivityName}');"
									parent_node_name="${parentActivityName}"
									parent_name	= "all_chose_${routePath}_${parentActivityName}" 
									flag="actor"
									dealMethod = "${dealMethod}"
									passedCount = "${passedCount}"
								/>
								${dealUser.realname}
							</td>
							<c:choose>
								<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
									</tr>
									<tr identity="all_chose_tr_${routePath}">
								</c:when>
								<c:otherwise>
									<c:if test="${(len-1) == userStatus.index}">
										</tr>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:forEach>
				</table>
			</div>
		</center>
	</div>
	<div style="text-align: center;width: 100%;position: relative;padding: 10px 0px 5px 0px;">
		<a class="mini-button" onclick='__doChoseRouteOperAll'>确定</a>
		<a class="mini-button" onclick='__doChoseRouteOperAllClose'>取消</a>
	</div>
</div>

<script type="text/javascript">
function __doChoseRouteOperAll(){
	doChoseRouteOper("all");
}

function __doChoseRouteOperAllClose(){
	mini.get('__allRouteChoseWindow').destroy();
}
if(mini.get('__allRouteChoseWindow')){
	mini.get('__allRouteChoseWindow').destroy();
}
mini.parse(document.getElementById('__allRouteChoseWindow'));

</script>
<!--任意路由结束-->