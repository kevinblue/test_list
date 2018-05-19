<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %>
<%@ taglib uri="/minidict" prefix="mini"%>

<div id="__nextRouteChoseWindow" class="mini-window" title="提交下一步详情" style="display:none;width:600px;padding:10px;">
	<div id="id_nextRouteChoseWindow" class="route-choose" style="width:100%;position:relative;">
		<center>
			<c:if test="${(0 == fn:length(lastBack_next_activityTaskUsersList)) && (0 == fn:length(next_activityTaskUsersList))}">
				<script type="text/javascript">
					isShowSubmitChosePersonWindow = false;
				</script>
			</c:if>
			<script type="text/javascript">
				nextChosePersonArr = [];
				var requestNextRoute = document.getElementById("id_requestInitiatorRoute");
				requestNextRoute.value = "${dynamicRequestInitiatorRoute}";
			</script>
			
			
			<div style="width:96%;text-align:left;">
				<c:if test="${not empty lastBack_next_activityTaskUsersList}">
					<c:if test="${fn:length(lastBack_next_activityTaskUsersList) > 0}">
						<table>
							<c:set var="pageSize" value="4"></c:set>
							<c:forEach items="${lastBack_next_activityTaskUsersList}" var="activityTaskUsers" varStatus="status">
								<c:set var="routePath" value="dynamicLastBackRoutePath"></c:set>
								<c:set var="activityName" value="${activityTaskUsers.currentTaskUsers.activityDetail.activityName}"></c:set>
								<c:set var="parentActivityName" value="${activityTaskUsers.routeInfo.parentActivityName}"></c:set>
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.dealUsers)}" ></c:set>
								<c:set var="dealMethod" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethod}" ></c:set>
								<c:set var="dealMethodChineseName" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethodChineseName}" ></c:set>
								<c:set var="passedCount" value="${activityTaskUsers.currentTaskUsers.activityDetail.passedCount}" ></c:set>
								<c:set var="checkType" value="checkbox"></c:set>
								<c:if test="${'Single' eq dealMethod}">
									<c:set var="checkType" value="radio"></c:set>
								</c:if>
								<script type="text/javascript">
									nextChosePersonArr.push('next_chose_${routePath}_${activityName}');
								</script>
								<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
									<td colspan="2">
										<div><mini:param  name="activityName" /></div>
									</td>
								</tr>
								<c:if test="${0 == len}">
									<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
										<td colspan="2">
											<input type="radio" style="position:relative;top:3px;" 
												name="next_chose_${routePath}_${activityName}" 
												login_name="NULL" routePath="${routePath}" 
												node_name="${activityName}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','actor');"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod="${dealMethod}"
												passedCount="${passedCount}"
												routePath="${routePath}"
											/> <mini:param  name="activityName" />tivityName}
										</td>
									</tr> 
								</c:if>
								
								
								
								<!--执行人开始 -->
								<c:forEach items="${activityTaskUsers.currentTaskUsers.dealUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}" submitType='lastBack'><td>
										<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;" >
											<legend>
												<font color="red">【上一步退回节点】</font>任务处理人【<mini:param  name="dealMethodChineseName" /><c:if test="${'NPassed' eq dealMethod}">,处理人数&nbsp;<mini:param  name="passedCount" />&nbsp;</c:if>】
												<c:if test="${'Single' ne dealMethod}">
													&nbsp;&nbsp;<input 
													id="next_chose_${routePath}_${parentActivityName}_checkedAll_actor" 
													style="position:relative;top:3px;" 
													type="checkbox" 
													onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','actor');"/>
													&nbsp;全选
												</c:if>
											</legend>
											<table>
												<tr identity="next_chose_tr_${routePath}" type="actor" submitType='lastBack'>
									</c:if>
										<td style="padding-left:10px;">
											<input 
												style="position:relative;top:3px;" 
												type="${checkType}" name="next_chose_${routePath}_${activityName}" 
												node_name="${activityName}" real_name="${dealUser.realname}" 
												delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','actor');"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod="${dealMethod}"
												passedCount="${passedCount}"
												routePath="${routePath}"
												dealMethodName="${dealMethodChineseName}"
											/> 
											
                                            <c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
											<mini:param  name="c_curentrealname" />
										</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}">
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								
								
								<!-- 会签人开始 -->
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.signatureUsers)}" ></c:set>
								<c:forEach items="${activityTaskUsers.currentTaskUsers.signatureUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}" submitType='lastBack'><td>
											<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;">
												<legend>会签对象	
													<input 
														id="next_chose_${routePath}_${parentActivityName}_checkedAll_signature" 
														style="position:relative;top:3px;" 
														type="checkbox" 
														onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','signature');"/>
													全选 
												</legend>
											<table>
												<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
									</c:if>
										<td style="padding-left:10px;">
											<input 
												style="position:relative;top:3px;" 
												type="checkbox" name="next_chose_${routePath}_${activityName}" 
												node_name="${activityName}" real_name="${dealUser.realname}" 
												delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','signature');"
												flag="signature"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												routePath="${routePath}"
											/>  
											<c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
											<mini:param  name="c_curentrealname" />
										</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 会签人结束 -->
								
								
								
								<!-- 传阅人开始 -->
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.readDealUsers)}" ></c:set>
								<c:forEach items="${activityTaskUsers.currentTaskUsers.readDealUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}" submitType='lastBack'><td>
											<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;">
												<legend>传阅对象
													<input 
													id="next_chose_${routePath}_${parentActivityName}_checkedAll_read" 
													style="position:relative;top:3px;" 
													type="checkbox" 
													onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','read');"/>
													全选 
												</legend>
												<table>
													<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
									</c:if>
									<td style="padding-left:10px;">
										<input 
											style="position:relative;top:3px;" 
											type="checkbox" name="next_chose_${routePath}_${activityName}" 
											node_name="${activityName}" real_name="${dealUser.realname}" 
											delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
											onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','read');"
											parent_node_name="${parentActivityName}"
											parent_name="next_chose_${routePath}_${parentActivityName}" 
											flag="read"
											routePath="${routePath}"
										/> 
										 <c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
										<mini:param  name="c_curentrealname" />
									</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}" submitType='lastBack'>
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 传阅人结束 -->
							</c:forEach>
							<!-- 执行人结束 -->
							
							
							<!--
							<tr>
								<td bgcolor=#d9d9d9 colspan="2">
								<div id="workflow_message" >
								<fieldset>
										<legend>【信息】</legend>
										<div title="发送短信" >
											<table width="98%">
												<tr>
													<td colspan="4">是否发送短信：<input id="isSendMsgFlag" type="checkbox" > 是否发送站内信：<input id="isSendStationMsgFlag" type="checkbox" ></td>
												</tr>
												<tr>
													<td style="width: 100px;">短息内容：</td>
													<td colspan="3">
														<textarea id="sendMsgContent" rows="2" cols="50"></textarea>
													</td>
												</tr>
											</table>
										</div>
										<div title="发送站内信" >
											<table width="100%">
												<tr>
													<td style="width: 100px;">是否发送站内信：</td>
													<td><input id="isSendStationMsgFlag" type="checkbox" ></td>
												</tr>
												<tr>
													<td style="width: 100px;">站内信内容：</td>
													<td ><textarea id="sendStationMsgContent" rows="2" cols="50"></textarea></td>
												</tr>
											</table>
										</div> 
										</fieldset>
									</div>
								</td>
							</tr>
							-->
						</table>
					</c:if>
				</c:if>
				
				
				
				<c:if test="${not empty next_activityTaskUsersList}">
					<c:if test="${fn:length(next_activityTaskUsersList) > 0}">
						<table>
							<c:set var="pageSize" value="4"></c:set>
							<c:forEach items="${next_activityTaskUsersList}" var="activityTaskUsers" varStatus="status">
								<c:set var="routePath" value="${activityTaskUsers.routeInfo.currentTransition.name}"></c:set>
								<c:set var="activityName" value="${activityTaskUsers.currentTaskUsers.activityDetail.activityName}"></c:set>
								<c:set var="parentActivityName" value="${activityTaskUsers.routeInfo.parentActivityName}"></c:set>
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.dealUsers)}" ></c:set>
								<c:set var="dealMethod" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethod}" ></c:set>
								<c:set var="dealMethodChineseName" value="${activityTaskUsers.currentTaskUsers.activityDetail.dealMethodChineseName}" ></c:set>
								<c:set var="passedCount" value="${activityTaskUsers.currentTaskUsers.activityDetail.passedCount}" ></c:set>
								<c:set var="checkType" value="checkbox"></c:set>
								<c:if test="${'Single' eq dealMethod}">
									<c:set var="checkType" value="radio"></c:set>
								</c:if>
								<script type="text/javascript">
									nextChosePersonArr.push('next_chose_${routePath}_${activityName}');
								</script>
								<tr identity="next_chose_tr_${routePath}">
									<td colspan="2">
										<div> ${activityName} </div>
									</td>
								</tr>
								<c:if test="${0 == len}">
									<tr identity="next_chose_tr_${routePath}">
										<td colspan="2">
											<input type="radio" style="position:relative;top:3px;" 
												name="next_chose_${routePath}_${activityName}" 
												login_name="NULL" routePath="${routePath}" 
												node_name="${activityName}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','actor');"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod="${dealMethod}"
												passedCount="${passedCount}"
												routePath="${routePath}"
											/> <mini:param  name="activityName" />
										</td>
									</tr> 
								</c:if>
								
								
								
								<!--执行人开始 -->
								<c:forEach items="${activityTaskUsers.currentTaskUsers.dealUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}"><td>
										<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;" >
											<legend>
												任务处理人【${dealMethodChineseName}<c:if test="${'NPassed' eq dealMethod}">,处理人数&nbsp;${passedCount}&nbsp;</c:if>】
												<c:if test="${'Single' ne dealMethod}">
													&nbsp;&nbsp;<input 
														id="next_chose_${routePath}_${parentActivityName}_checkedAll_actor" 
														style="position:relative;top:3px;" 
														type="checkbox" 
														onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','actor');"/>
													&nbsp;全选
												</c:if>
											</legend>
											<table>
												<tr identity="next_chose_tr_${routePath}" type="actor">
									</c:if>
										<td style="padding-left:10px;">
											<input 
												style="position:relative;top:3px;" 
												type="${checkType}" name="next_chose_${routePath}_${activityName}" 
												node_name="${activityName}" real_name="${dealUser.realname}" 
												delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','actor');"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												flag="actor"
												dealMethod="${dealMethod}"
												passedCount="${passedCount}"
												routePath="${routePath}"
											/> 
									    <c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
										<mini:param  name="c_curentrealname" />
										</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}">
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
															</tr>
														</table>
													</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 会签人开始 -->
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.signatureUsers)}" ></c:set>
								<c:forEach items="${activityTaskUsers.currentTaskUsers.signatureUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}"><td>
										<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;">
											<legend>会签对象	
												<input 
													id="next_chose_${routePath}_${parentActivityName}_checkedAll_signature" 
													style="position:relative;top:3px;" 
													type="checkbox" 
													onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','signature');"/>
												全选 
											</legend>
											<table>
												<tr identity="next_chose_tr_${routePath}">
									</c:if>
									<td style="padding-left:10px;">
										<input 
											style="position:relative;top:3px;" 
											type="checkbox" name="next_chose_${routePath}_${activityName}" 
											node_name="${activityName}" real_name="${dealUser.realname}" 
											delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
											onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','signature');"
											flag="signature"
											parent_node_name="${parentActivityName}"
											parent_name="next_chose_${routePath}_${parentActivityName}" 
											routePath="${routePath}"
										/>
										 <c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
										<mini:param  name="c_curentrealname" />
									</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}">
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 会签人结束 -->
								<!-- 传阅人开始 -->
								<c:set var="len" value="${fn:length(activityTaskUsers.currentTaskUsers.readDealUsers)}" ></c:set>
								<c:forEach items="${activityTaskUsers.currentTaskUsers.readDealUsers}" var="dealUser" varStatus="userStatus">
									<c:if test="${0 == userStatus.index}">
										<tr identity="next_chose_tr_${routePath}"><td>
										<fieldset style="margin-top:5px;padding-left:20px;border:1px solid #DDD;">
											<legend>传阅对象
												<input 
													id="next_chose_${routePath}_${parentActivityName}_checkedAll_read" 
													style="position:relative;top:3px;" 
													type="checkbox" 
													onclick="doCheckedAll(this.checked,'next_chose_${routePath}_${activityName}','next_chose_${routePath}_${parentActivityName}','read');"/>
												全选 
											</legend>
											<table>
												<tr identity="next_chose_tr_${routePath}">
									</c:if>
										<td style="padding-left:10px;">
											<input 
												style="position:relative;top:3px;" 
												type="checkbox" name="next_chose_${routePath}_${activityName}" 
												node_name="${activityName}" real_name="${dealUser.realname}" 
												delegate_real_name="" login_name="${dealUser.username}" routePath="${routePath}"
												onclick="nextRejectChecked('next_chose_${routePath}_${parentActivityName}','read');"
												parent_node_name="${parentActivityName}"
												parent_name="next_chose_${routePath}_${parentActivityName}" 
												flag="read"
												routePath="${routePath}"
											/>  
											<c:set var="c_curentrealname" value="${dealUser.realname}"></c:set>
										    <mini:param  name="c_curentrealname" />
										</td>
									<c:choose>
										<c:when test="${((len-1) != userStatus.index) && (0 == ((userStatus.index+1) mod 2))}">
											</tr>
											<tr identity="next_chose_tr_${routePath}">
										</c:when>
										<c:otherwise>
											<c:if test="${(len-1) == userStatus.index}">
																</tr>
															</table>
														</fieldset>
													</td>
												</tr>
											</c:if>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 传阅人结束 -->
							</c:forEach>
							<!-- 执行人结束 -->
							
							
							<!--
							<tr>
								<td bgcolor=#d9d9d9 colspan="2">
									<div id="workflow_message" >
										<fieldset>
											<legend>【信息】</legend>
											<div title="发送短信" >
												<table width="98%">
													<tr>
														<td colspan="4">是否发送短信：<input id="isSendMsgFlag" type="checkbox" > 是否发送站内信：<input id="isSendStationMsgFlag" type="checkbox" ></td>
													</tr>
													<tr>
														<td style="width: 100px;">短息内容：</td>
														<td colspan="3">
															<textarea id="sendMsgContent" rows="2" cols="50"></textarea>
														</td>
													</tr>
												</table>
											</div>
											<div title="发送站内信" >
												<table width="100%">
													<tr>
														<td style="width: 100px;">是否发送站内信：</td>
														<td><input id="isSendStationMsgFlag" type="checkbox" ></td>
													</tr>
													<tr>
														<td style="width: 100px;">站内信内容：</td>
														<td ><textarea id="sendStationMsgContent" rows="2" cols="50"></textarea></td>
													</tr>
												</table>
											</div> 
										</fieldset>
									</div>
								</td>
							</tr>
							-->
						</table>
					</c:if>
				</c:if>
			</div>
		</center>
	</div>
	<div style="text-align: center;width: 100%;position: relative;padding: 10px 0px 5px 0px;">
		<a class="mini-button" onclick='__doChoseRouteOperNext'>确定</a>
		<a class="mini-button" onclick='__doChoseRouteOperNextClose'>取消</a>
	</div>
</div>

<script type="text/javascript">
function __doChoseRouteOperNext(){
	doChoseRouteOper("next");
}

function __doChoseRouteOperNextClose(){
	mini.get('__nextRouteChoseWindow').destroy();
}
if(mini.get('__nextRouteChoseWindow')){
	mini.get('__nextRouteChoseWindow').destroy();
}
mini.parse(document.getElementById('__nextRouteChoseWindow'));

</script>
<!-- 弹出提交列表结束 -->