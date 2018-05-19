<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>

<script type="text/javascript">
	//提交按钮时候执行回调函数
	function btn_callBack(buttonText, assignmentIsSilentModel) {
		mini.unmask(document.body);
		mini.mask({el: document.body,cls: 'mini-mask-loading',html: '请稍后...'});
		if(true == assignmentIsSilentModel){
			document.getElementById("id_assignmentIsSilentModel").value ="true"; 
		} else {
			document.getElementById("id_assignmentIsSilentModel").value ="false"; 
		}
		globalCurrentPressButtonText = buttonText;
		document.getElementById('id_currentTaskSubmitButtonText').value = globalCurrentPressButtonText;
		if(resetRouteButtonDisplayText == globalCurrentPressButtonText){
			var infoFlag = "all";
			if(getLazyLoadedObj(infoFlag,jQuery(document.body),function(){
				mini.get('__allRouteChoseWindow').show();
			})){
				mini.get('__allRouteChoseWindow').show();
			}
			mini.unmask(document.body);
			return;
		}
		if(saveButtonDisplayText == globalCurrentPressButtonText) {
			//是否保存	
			if(window.workflowSaveCallBack) { //返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
				if(!workflowSaveCallBack()) {
					mini.unmask(document.body);
					return;
				}
			}
			submitFormWithoutWorkflowNextCallBack();
		} else if((submitButtonDisplayText == globalCurrentPressButtonText)) {
			if(!checkAttachmentInfoFunc()){
				mini.unmask(document.body);
				return;
			}
			<c:if test="${'1' eq currentTaskIsNeedAdvise}">
				var hidden_text_choseAdvise = document.getElementById('id_hidden_choseResult');
				if(!hidden_text_choseAdvise.value){
					mini.unmask(document.body);
					mini.alert("请填写审批结论");
					var tab = mini.get('__jbpmProcessFormTabs');
					tab.setActiveIndex(1);
					return;
				};
			</c:if>
			//是否提交	
			if(window.workflowSubmitCallBack &&　submitButtonDisplayText == globalCurrentPressButtonText) { //返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
				if(!workflowSubmitCallBack(buttonText)) {
					mini.unmask(document.body);
					return;
				}
			}
			/*
			//通过哪条路由路线进行提交
			var isNeedChoseOper = false;
			var requestNextRoute = document.getElementById("id_requestInitiatorRoute");
			mini.alert(requestNextRoute.value);
			//modify by tracywindy 20140211
			if(!requestNextRoute.value){
				if(window.workflowNextRouteCallBack){ //该回调函数可用于设置提交提交的路由线路通过(requestNextRoute.value=路由线路值)进行设置;
					//该回调函数先于endAction中的appointRequestRoute方法的执行
					isNeedChoseOper = workflowNextRouteCallBack(buttonText,requestNextRoute);
				} else {
					requestNextRoute.value="";
				}
			}*/
			
			if((submitButtonDisplayText == globalCurrentPressButtonText)) {
				var infoFlag="next";
				if(getLazyLoadedObj(infoFlag,jQuery(document.body),function(){
					dealNextRouteChoseWindow();
					if(!isShowSubmitChosePersonWindow) {
						submitFormWithoutWorkflowNextCallBack();
					} else {
						mini.get('__nextRouteChoseWindow').show();
					}
				})){
					dealNextRouteChoseWindow();
					if(!isShowSubmitChosePersonWindow) {
						submitFormWithoutWorkflowNextCallBack();
					} else {
						mini.get('__nextRouteChoseWindow').show();
					}
				}
			} 
		}else if(backButtonDisplayText == globalCurrentPressButtonText){
			if(window.workflowSaveCallBack) { //返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
				if(!workflowSaveCallBack()) {
					mini.unmask(document.body);
					return;
				}
			}
		  <c:if test="${'1' eq currentTaskIsNeedAdvise}">
			var hidden_text_choseAdvise = document.getElementById('id_hidden_choseResult');
			if(!hidden_text_choseAdvise.value){
				mini.unmask(document.body);
				mini.alert("请填写审批结论");
				var tab = mini.get('__jbpmProcessFormTabs');
				tab.setActiveIndex(1);
				return;
			};
		   </c:if>
			var infoFlag="back";
			if(getLazyLoadedObj(infoFlag,jQuery(document.body),function(){
				//modify by tracywindy for default chose first person
				dealNextRouteChoseWindow();
				if(!isShowBackChosePersonWindow) {
					mini.unmask(document.body);
					mini.alert("退回选择列表为空，不允许退回");
					return;
				} else {
					mini.get('__backRouteChoseWindow').show();
				}
			})) { 
				//modify by tracywindy for default chose first person
				dealNextRouteChoseWindow();
				if(!isShowBackChosePersonWindow) {
					mini.unmask(document.body);
					mini.alert("退回选择列表为空，不允许退回");
					return;
				} else {
					mini.get('__backRouteChoseWindow').show();
				}
			}
		} 
		else if(deleteButtonDisplayText == buttonText) {
			mini.confirm("确认删除该流程实例么？", "确定？",
	            function (action) {
	                if (action == "ok") {
						mini.unmask(document.body);
						mini.mask({el: document.body,cls: 'mini-mask-loading',html: '数据处理中，请稍后...'});
						submitFormWithoutWorkflowNextCallBack();
	                }
	            }
	        );
		} else if(abondonButtonDisplayText == buttonText) {
			mini.confirm("确认废弃该流程实例么？", "确定？",
	            function (action) {
	                if (action == "ok") {
						mini.unmask(document.body);
						mini.mask({el: document.body,cls: 'mini-mask-loading',html: '数据处理中，请稍后...'});
						submitFormWithoutWorkflowNextCallBack();
	                }
	            }
	        );
		} else if(readButtonDisplayText == buttonText) {
			new tracywindyCommonUserSelection({
				hiddenInput:'id_readUserIds_hidden',
				displayInput:'id_readUserRealNames_display',
				displayPromit:'参与传阅的人员',
				isMultiSelect:true,
				choseCallBack:function(){
					submitFormWithoutWorkflowNextCallBack();
					return true;
				}
			}).show();
		} else if(signatureButtonDisplayText == buttonText) {
			new tracywindyCommonUserSelection({
				hiddenInput:'id_signatureUserIds_hidden',
				displayInput:'id_signatureUserRealNames_display',
				displayPromit:'参与会签的人员',
				isMultiSelect:true,
				choseCallBack:function(){
					submitFormWithoutWorkflowNextCallBack();
					return true;
				}
			}).show();
		} else if(assignmentButtonDisplayText == buttonText) {
			
			new tracywindyCommonUserSelection({
				hiddenInput:'id_assignmentedUserId_hidden',
				displayInput:'id_assignmentUserRealName_display',
				displayPromit:'被指派人员',
				isMultiSelect:true,
				choseCallBack:function(){
					submitFormWithoutWorkflowNextCallBack();
					return true;
				}
			}).show();
			
			/* new tracywindyCommonUserSelection({
				hiddenInput:'id_assignmentedUserId_hidden',
				displayInput:'id_assignmentUserRealName_display',
				isMultiSelect:false,
				displayPromit:'被指派人员',
				choseCallBack:function(){
					submitFormWithoutWorkflowNextCallBack();
					return true;
				}
			}).show(); */
		}
		mini.unmask(document.body);
	}
 
	function submitFormWithoutWorkflowNextCallBack() {
		//条件通过提交表单
		jQuery("#id_workflowAdviseContainer").css("overflow","hidden");
		jQuery("#id_workflowFormContainer").css("overflow","hidden");
		mini.unmask(document.body);
		mini.mask({el: document.body,cls: 'mini-mask-loading',html: '数据加载中，请稍后...'});
		window.top.document.body.style.overflow="hidden";
		var submitProcessedForm = document.getElementById("id_submitProcessedForm");
		submitProcessedForm.submit();
		return false;
	}
	/*function saveCallBack(returnResult){}*///保存回调函数
	function ajaxCallBack(returnResult,assignmentIsSilentModel) {
		jQuery("#id_workflowAdviseContainer").css("overflow","auto");
		jQuery("#id_workflowFormContainer").css("overflow","auto");

		var isReturn = false;// 防止mini的alert起不到阻塞线程的作用
		if(saveButtonDisplayText == globalCurrentPressButtonText) {
			if(window.saveCallBack) { //保存成功时候的回调函数
				saveCallBack(returnResult);
			}
			if(!assignmentIsSilentModel){
				var promitInfo = '<spring:message code="Save" text="保存"/><spring:message code="Success" text="成功"/>';
				mini.unmask(document.body);
				mini.alert(promitInfo);
			}
			isReturn = true;
		} else if(readButtonDisplayText == globalCurrentPressButtonText) {
			var promitInfo = '<spring:message code="WorkflowRead" text="传阅"/><spring:message code="Success" text="成功"/>';
			mini.unmask(document.body);
			mini.alert(promitInfo);
			isReturn = true;
		} else if(signatureButtonDisplayText == globalCurrentPressButtonText) {
			var promitInfo = '<spring:message code="Signature" text="会签"/><spring:message code="Success" text="成功"/>';
			mini.unmask(document.body);
			mini.alert(promitInfo);
			isReturn = true;
		} else if(assignmentButtonDisplayText == globalCurrentPressButtonText) {
			returnResult = '<spring:message code="Assignment" text="工作指派"/><spring:message code="Success" text="成功"/>';
			isReturn = false;
		} else if(deleteButtonDisplayText == globalCurrentPressButtonText) {
			returnResult = "该流程已被成功删除";
		} else if(abondonButtonDisplayText == globalCurrentPressButtonText) {
			returnResult = "该流程已被成功废弃";
		} else if(assignmentButtonDisplayText == globalCurrentPressButtonText) {
			
		} else if(submitButtonDisplayText == globalCurrentPressButtonText) {
			
		} else if(backButtonDisplayText == globalCurrentPressButtonText) {
			
		}
		
		if(isReturn == false){
			try {
				if(window.opener) {
					window.opener.location.reload();
				}
			} catch(e) {}
			
			try {
				if(deleteButtonNoSavedDisplayText != globalCurrentPressButtonText){
					mini.unmask(document.body);
					mini.alert(returnResult, '提示', function(){
						closeWindow();
					});
				} else {
					closeWindow();
				}
			} catch(e) {}
		}
	}
	//提交下一步
	function submitNextTask(){
		jQuery("#id_currentTaskSubmitButtonText").val(submitButtonDisplayText);
		jQuery("#id_submitProcessedForm")[0].submit();
	}
	function doChoseRouteOper(operFlag) {
		//短信
		/*if(true){
			if(jQuery('#isSendMsgFlag').attr('checked')){
				//短信内容
				var value = jQuery('#sendMsgContent');
			}
		}*/
		var currentChosePersonArr = [];
		var displayPromit = "";
		if('next' == operFlag) {
			displayPromit = "请选择要提交到的节点";
		} else if('back' == operFlag) {
			displayPromit = "请选择要退回到的节点";
		} else if('all' == operFlag) {
			displayPromit = "请选择要重置路由的节点";
		}
		var firstAlllement = jQuery("input[name^='"+operFlag+"_chose_']:checked:first")[0];
		if(!firstAlllement){
			mini.alert(displayPromit);
			return false;
		}
		var parent_name = firstAlllement.getAttribute("parent_name");
		jQuery("input[parent_name='"+parent_name+"']").each(function(){
			var currentName = this.getAttribute("name");
			if(!currentChosePersonArr.contains(currentName)){
				currentChosePersonArr.push(currentName);
			}
		});
		var allActivityRequestInitiators = {};
		var signatureAllActivityRequestInitiators = {};
		var readAllActivityRequestInitiators = {};
		var chosedRoutePath = null;
		if(currentChosePersonArr) {
			for(var i=0;i<currentChosePersonArr.length;i++) {
				var currentChosePerson = currentChosePersonArr[i];
				currentChoses = jQuery("input[name='"+currentChosePerson+"']:checked");
				//modify by tracywindy 2013-08-26 
				actorCurrentChoses = jQuery("input[name='"+currentChosePerson+"'][flag='actor']");
				actorCurrentChosesChecked = jQuery("input[name='"+currentChosePerson+"'][flag='actor']:checked");
				readCurrentChoses = jQuery("input[name='"+currentChosePerson+"'][flag='read']");
				readCurrentChosesChecked = jQuery("input[name='"+currentChosePerson+"'][flag='read']:checked");
				signatureCurrentChoses = jQuery("input[name='"+currentChosePerson+"'][flag='signature']");
				signatureCurrentChosesChecked = jQuery("input[name='"+currentChosePerson+"'][flag='signature']:checked");
				if(operFlag == 'back'){
					var backmodel =  jQuery("input[name='backmodel']:checked").val();
					document.getElementById("id_workflowbackmodel").value = backmodel;
				}
				var isNeedSignature = (signatureCurrentChoses.length>0)&&(0 == signatureCurrentChosesChecked.length);
				var isNeedRead = (readCurrentChoses.length>0)&&(0 == readCurrentChosesChecked.length);
				var msg = "";
				if((actorCurrentChoses.length>0)&&(0 == actorCurrentChosesChecked.length)){
					msg = "任务处理人";
				} else if(isNeedSignature){
					msg = "会签对象";
				} else if(isNeedRead){
					msg = "传阅对象";
				}
				if(msg/*!currentChoses[0]*/) {
					mini.alert("请选择【"+currentChosePerson.substring(currentChosePerson.lastIndexOf("_")+1)+"】"+msg);
					return;
				} else {
					var dealMethod = null;
					var passedCount = null;
					var requestInitiators = new Array();
					var readRequestInitiators = new Array();
					var signatureRequestInitiators = new Array();
					currentChoses.each(function(i){
						if(0 == i) {
							chosedRoutePath = this.getAttribute("routePath");
						}
						var flag = this.getAttribute("flag");
						switch(flag){
							case "actor":{
								if(0 == i){
										dealMethod = this.getAttribute("dealMethod");
										passedCount = this.getAttribute("passedCount");
								}
								requestInitiators.push({username:this.getAttribute("login_name")});break;}
							case "read":{readRequestInitiators.push({username:this.getAttribute("login_name")});break;}
							case "signature":{signatureRequestInitiators.push({username:this.getAttribute("login_name")});break;}
							default:{requestInitiators.push({username:this.getAttribute("login_name")});break;}
						}
					});
					/*
					*	changeRequestInitiator={
					* 		"02-分支任务1":[{"username":"Admin"}],
					* 		"03-分支任务2":[{"username":"Admin"}],
					* 		"04-分支任务3":[{"username":"Admin"}]
					* 	}
					*/
					var node_name = currentChoses[0].getAttribute("node_name");
					if("NPassed" == dealMethod){
						if(parseInt(passedCount) > requestInitiators.length){
							mini.alert("节点【"+node_name+"】所勾选的处理人员数量必须大于等于需要通过的"+passedCount+"人!");
							return;
						}
					}
					allActivityRequestInitiators[node_name] =requestInitiators; 
					readAllActivityRequestInitiators[node_name] =readRequestInitiators; 
					signatureAllActivityRequestInitiators[node_name] =signatureRequestInitiators; 
					//设置任意路由的节点名称
					document.getElementById("id_choseRequestNextRouteNodeName").value =currentChoses[0].getAttribute("parent_node_name");
				}
			}
		}
		if('next' == operFlag) {
			mini.get('__nextRouteChoseWindow').hide();
		} else if('back' == operFlag) {
			mini.get('__backRouteChoseWindow').hide();
		} else if('all' == operFlag) {
			mini.get('__allRouteChoseWindow').hide();
		}
		var requestNextRoute = document.getElementById("id_requestInitiatorRoute");
		var changeRequestInitiator = document.getElementById("id_changeRequestInitiator");
		var signatureChangeRequestInitiator = document.getElementById("id_signatureChangeRequestInitiator");
		var readChangeRequestInitiator = document.getElementById("id_readChangeRequestInitiator");
		requestNextRoute.value = chosedRoutePath;
		changeRequestInitiator.value = JsonUtil.encode(allActivityRequestInitiators);
		signatureChangeRequestInitiator.value = JsonUtil.encode(signatureAllActivityRequestInitiators);
		readChangeRequestInitiator.value = JsonUtil.encode(readAllActivityRequestInitiators);
		submitFormWithoutWorkflowNextCallBack();
	}
	
	function dealNextRouteChoseWindow() {
		//通过哪条路由路线进行提交
		var requestNextRoute = document.getElementById("id_requestInitiatorRoute");
		var conditionRouteType = document.getElementById("conditionRouteType").value;
		//modify by tracywindy 20140211
		if(!requestNextRoute.value){
			if(conditionRouteType == 'pageCallBack' && window.workflowNextRouteCallBack)//该回调函数可用于设置提交提交的路由线路通过(requestNextRoute.value=路由线路值)进行设置;
			{//该回调函数先于endAction中的appointRequestRoute方法的执行
				workflowNextRouteCallBack(globalCurrentPressButtonText,requestNextRoute);
			} else {
				requestNextRoute.value="";
			}
		}
		var currentChosePersonArr = null;
		//var requestNextRoute = document.getElementById("id_requestInitiatorRoute");
		var requestNextRouteValue = (!requestNextRoute.value?"":requestNextRoute.value);
		
		jQuery("tr[identity]").show();
		//jQuery("input[name^='back_chose_']:first").attr("checked",true);
		if((backButtonDisplayText == globalCurrentPressButtonText)) {//modify by tracywindy for default chose first person
			currentChosePersonArr = backChosePersonArr;
			/*
			if(window.workflowNextRouteCallBack) {
				jQuery("tr[identity][identity!='back_chose_tr_"+requestNextRouteValue+"']").hide();
				// jQuery("tr[identity][identity^='back_chose_tr_'][routePath!='"+requestNextRouteValue+"']").hide();
			}
			*/
			//jQuery("input[name^='back_chose_']:first").attr("checked",true);
			//return;
		} else if((submitButtonDisplayText == globalCurrentPressButtonText)) {	
			currentChosePersonArr = nextChosePersonArr;
			if(requestNextRouteValue || (conditionRouteType == 'pageCallBack' && window.workflowNextRouteCallBack)) {
				//历史
				jQuery("tr[identity][submitType!='lastBack'][identity!='next_chose_tr_"+requestNextRouteValue+"']").hide();
			}
		}
		//modify by tracywindy for default chose first person
		var $firstCheckedInput = null;
		//modify by tracywindy 2014-03-18
		var $submitBackedElement = jQuery("tr[submitType='lastBack'] input[parent_node_name][parent_name]:first");
		//默认勾选取消
		if(currentChosePersonArr) {
			for(var i=0;i<currentChosePersonArr.length;i++) {
				var currentChoses = null;
				jQuery("input[name='"+currentChosePersonArr[i]+"']").attr("checked",false);
				if(requestNextRouteValue || (conditionRouteType == 'pageCallBack' && window.workflowNextRouteCallBack)) {
					/*
					//var nameFlag = "";
					if((submitButtonDisplayText == globalCurrentPressButtonText)) {
						//nameFlag ="next_chose_";
						currentChoses = jQuery("input[routePath='"+requestNextRouteValue+"'][name='"+currentChosePersonArr[i]+"']:first"); 
					} else if((backButtonDisplayText == globalCurrentPressButtonText)) {
						//nameFlag ="back_chose_";
						currentChoses = jQuery("input[name='"+currentChosePersonArr[i]+"']:first"); 
					}*/
					//预留退回选择
					//modify by tracywindy 2014-03-18
					if($submitBackedElement[0]){
						currentChoses = $submitBackedElement;
					} else {
						currentChoses = jQuery("input[routePath='"+requestNextRouteValue+"'][name='"+currentChosePersonArr[i]+"']:first"); 
					}
					if(null == $firstCheckedInput){
						$firstCheckedInput = currentChoses;
					}
				} else {
					currentChoses = jQuery("input[name='"+currentChosePersonArr[i]+"']:first");
					if(null == $firstCheckedInput){
						$firstCheckedInput = currentChoses;
					}
				}
				//如果是多人任一 或 多人全部时选中所有人 
				var dealMethod = jQuery("input[name='"+currentChosePersonArr[i]+"']").attr("dealMethod");
				if(dealMethod == "AllPassed" || dealMethod == "OnePassed"){
					/* alert("next_chose"+requestNextRouteValue+"_checkedAll_actor");
					alert(currentChosePersonArr[i]);
					jQuery("#next_chose_"+requestNextRouteValue+"_checkedAll_actor").click(); */
					if(jQuery("input[name='"+currentChosePersonArr[i]+"']").length > 0){
						var firstNode = jQuery(jQuery("input[name='"+currentChosePersonArr[i]+"']")[0]);
						var parent_name = firstNode.attr('parent_name');
						var allChecked = jQuery("input[id='"+parent_name+"_checkedAll_actor']");
						if($submitBackedElement.length <= 0 ){
							var isOnly = true;
							if(currentChosePersonArr.length >1 ){
								isOnly = false;
							}
							if(isOnly){
							  //allChecked.attr('disabled','true');
							  allChecked.click();
							}
							jQuery("input[name='"+currentChosePersonArr[i]+"']").each(function(){
								jQuery(this).attr('disabled','true');
							});
						}
					} 
				}else{
					//currentChoses.attr("checked",true);
					currentChoses.click();
				}	
			}
		}
		//added by tracywindy
		if(null != $firstCheckedInput){
			var first_parent_name = $firstCheckedInput.attr("parent_name");
			if(first_parent_name){
				jQuery("input[parent_node_name][parent_name][parent_name!='"+first_parent_name+"']").attr("checked",false); 
			}
		}
		//add by zhangc 传阅自动选中
		jQuery("#id_nextRouteChoseWindow input[id$='_checkedAll_read']").click();
		jQuery("#id_nextRouteChoseWindow input[flag='read']").attr("disabled",true);
	}
	//选择会签人或者传阅人
	function readOrSignatureOper(flag) {
		var msg = "确认提交么？";
		if(!window.confirm(msg)) {
			return;
		}
		var hidden_text_choseAdvise = document.getElementById('id_hidden_text_choseAdvise');
		var url = "${pageContext.request.contextPath}/jbpm/removeReadPersonToTask.action";
		if('read'!=flag) {
			if(!hidden_text_choseAdvise.value){mini.alert("请填写处理意见");return;};
			url = "${pageContext.request.contextPath}/jbpm/removeSignaturePersonToTask.action";
		}
		mini.unmask(document.body);
		mini.mask({el: document.body,cls: 'mini-mask-loading',html: '数据加载中，请稍后...'});
		ajaxRequest({
			url:url,
			success:function(res){mini.alert("操作成功");if(window.opener){window.opener.location.reload();}closeWindow();},
			failure:function(res){mini.alert("操作失败");if(window.opener){window.opener.location.reload();}closeWindow();},
			params:{
				jbpmWorkflowHistoryInfoId:currentJbpmWorkflowHistoryInfoId,
				processedAdvise:hidden_text_choseAdvise.value
			}
		});
	}
</script>