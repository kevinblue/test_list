<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'	uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>列表组件测试</title>
	<%@include file="/base/mini.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<style type="text/css">
	html,body{
		overflow:hidden;
	}
	.twoNumInput{
		width:20px;
		border:1px solid silver;
		height:13px;
		font-size:11px;
	}
	.fourNumInput{
		width:60px;
		border:1px solid silver;
		height:14px;
		font-size:11px;
	}
	.content-container{
		padding:5px;
		border:1px solid silver;
		margin-top:5px;
	}
	.commonCheck{
		position:relative;
		top:2px;
	}
	</style>
	
	<script type="text/javascript">
	//上传函数结束
	jQuery(function(){
		mini.parse(document.body);
		
		//触发器
		tenwa.createTable({
			renderTo : "id_triggerContainer",
			id : 'id_triggersTable',
			title : '定时调度维护',
			width : globalClientWidth,//表格高度，整型数值
			height : globalClientHeight,//表格宽度，整型数值
			showPager : true,//分页按钮是否显示
			pageSize : 15,
			xmlFileName : '/quartz/queryAllTriggers.xml',
			lazyLoad : false,
			columns:[ 
				    {type : 'indexcolumn'}, 
				    {type : 'checkcolumn'}, 
				    {header:'调度名称',field:'trigger_name',visible : false},
				    {header:'调度描述',field:'description'},
				    {header:'任务名称',field:'job_name',visible : false},
				    {header:'任务名称',field:'job_description'},
				    {header:'调度开始时间',field:'start_time',renderer:function(e){
					       var start_time = e.record.start_time;
					       return __getCurDateTime(new Date(parseInt(start_time)));}},
					{header:'调度结束时间',field:'end_time',renderer:function(e){
					                         var end_time = e.record.end_time; 
					                         return __getCurDateTime(new Date(parseInt(end_time)));}},
					{header:'时间表达式',field:'cron_expression',renderer:function(e){
					                var cron_expression = e.record.cron_expression; 
					                return "<a href='javascript:void(0);' onclick='showCronWindow(\""+cron_expression+"\",\"resume\")'>"+cron_expression+"</a>&nbsp;&nbsp;";}},
				    {header:'调度状态',field:'trigger_state',renderer:function(e){
					          var trigger_name = e.record.trigger_name;
					var trigger_state = e.record.trigger_state;
					var display = "";
					var resumeOper = "<a href='javascript:void(0);' onclick='triggerOper(\""+trigger_name+"\",\"resume\")'>恢复</a>&nbsp;&nbsp;";
					var pauseOper = "<a href='javascript:void(0);' onclick='triggerOper(\""+trigger_name+"\",\"pause\")'>暂停</a>&nbsp;&nbsp;";
					switch(trigger_state) {
						case "ACQUIRED":{display = "运行";resumeOper="";break;}
						case "PAUSED":{display = "暂停";pauseOper="";break;}
						case "WAITING":{display = "等待";resumeOper="";break;}
					}
					return display + " / " + resumeOper + pauseOper;
				}
			},{
				header:'调度上次执行时间',field:'prev_fire_time',renderer:function(e){
					var prev_fire_time = e.record.prev_fire_time; 
					return __getCurDateTime(new Date(parseInt(prev_fire_time)));
				}
			},{
				header:'调度下次执行时间',field:'next_fire_time',renderer:function(e){
					var next_fire_time = e.record.next_fire_time; 
					return __getCurDateTime(new Date(parseInt(next_fire_time)));
				}
			} ],
			tools:[{
				html:'新增',
				plain : true,//按钮是否有立体感
				iconCls : 'icon-add',//按钮的图标
				handler:function(miniTable, buttonText){
					addTrigger();
				}
			}, '-', {
				html:'修改',
				plain : true,//按钮是否有立体感
				iconCls : 'icon-edit',//按钮的图标
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
					if(selectedRowDatas.length > 0){
						var rowData = selectedRowDatas[0];
						if(rowData.trigger_state == 'PAUSED'){
							modifyTrigger(rowData);
						} else {
							mini.alert("请先暂停该定时调度任务！");
						}
					}
				}
			}, '-', {
				html:'删除',
				plain : true,//按钮是否有立体感
				iconCls : 'icon-remove',//按钮的图标
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
					if(selectedRowDatas.length > 0){
						var rowData = selectedRowDatas[0];
						if(rowData.trigger_state == 'PAUSED' || rowData.trigger_state == 'WAITING'){
							triggerOper(rowData.trigger_name, 'remove');
						} else {
							mini.alert("该定时调度任务不能被删除！");
						}
					}
				}
			}, '-', {
				html:'立即执行',
				plain : true,//按钮是否有立体感
				iconCls : 'icon-edit',//按钮的图标
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
					if(selectedRowDatas.length > 0){
						var rowData = selectedRowDatas[0];
						if(rowData.trigger_state == 'PAUSED' || rowData.trigger_state == 'WAITING'){
							triggerOper(rowData.trigger_name, 'run');
						} else {
							mini.alert("该定时调度任务不能被执行！");
						}
					}
				}
			}]
		});
		
		new tracywindyComboBox({
			lazyLoad:true,
			dropListHeight:200,
			id:'id_comboJobName',
			width:350,
			renderTo:'id_comboJobContainer',
			loadMode:'ajax',
			readOnly:false,
			xmlFileName:'/quartz/queryAllJobs.xml',
			isCheck:false,
			valueField:'job_name',
			displayField:'description',
			onSelect:function(combo){
				
			}
		});
	});
	
	function resetTrigger() {
		mini.get("id_triggerName").setValue("");
		mini.get("id_description").setValue("");
		mini.get("id_cronExpression").setValue("");
		mini.get("id_startDate").setValue("");
		mini.get("id_durableDays").setValue("");
		getTracywindyObject("id_comboJobName").setValue("");
	}
	
	function addTrigger() {
		resetTrigger();
		mini.get('__quartzTriggerDetailInfoWindow').show();
	}
	
	function modifyTrigger(rowData) {
		if(rowData){
			resetTrigger();
			mini.get("id_triggerName").setValue(rowData["trigger_name"]);
			mini.get("id_description").setValue(rowData["description"]);
			mini.get("id_cronExpression").setValue(rowData["cron_expression"]);
			getTracywindyObject("id_comboJobName").setValue(rowData["job_name"]);
			
			var start_time = rowData["start_time"];
			var d1 = new Date(parseInt(start_time));
			
			mini.get("id_startDate").setValue(__getCurDateTime(d1));
			var end_time = rowData["end_time"];
			/*var s1 = start_time.substring(0,10).replace(/-/g, "/");
			var s2 = end_time.substring(0,10).replace(/-/g, "/");*/
			
			var days = parseInt((parseInt(end_time)-parseInt(start_time)) / (1000 * 60 * 60 * 24));
			mini.get("id_durableDays").setValue(days);
			
			mini.get('__quartzTriggerDetailInfoWindow').show();
		}
	}
	
	function __getCurDateTime(date){
		return tenwa.toChar(date, 'yyyy-MM-dd hh:mm:ss');
	}
	
	function doQuartzTriggerOperationClose(){
		mini.get('__quartzTriggerDetailInfoWindow').hide();
	}
	
	function doQuartzCronDetailInfoWindowClose(){
		mini.get('__quartzCronDetailInfoWindow').hide();
	}
	
	function doQuartzTriggerOperation() {
		var triggerName = mini.get("id_triggerName").getValue();
		var jobName = getTracywindyObject("id_comboJobName").getValue();
		var description = mini.get("id_description").getValue();
		var cronExpression = mini.get("id_cronExpression").getValue();
		var startDate = mini.get("id_startDate").getFormValue();
		var durableDays = mini.get("id_durableDays").getValue();
		
		// if(!triggerName){mini.alert("调度名称不能为空");return;}
		if(!description){mini.alert("调度描述不能为空");return;}
		if(!cronExpression){mini.alert("cron表达式不能为空");return;}
		if(!startDate){mini.alert("调度开始时间不能为空");return;}
		if(!durableDays){mini.alert("调度持续时间");return;}
		if(!jobName){mini.alert("任务名称不能为空");return;}
		
		var params = {};
		params['triggerName'] = triggerName;
		params['jobName'] = jobName;
		params['description'] = description;
		params['cronExpression'] = cronExpression;
		params['startDate'] = startDate;
		params['durableDays'] = durableDays;
		
		mini.get('__quartzTriggerDetailInfoWindow').hide();
		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/saveOrUpdateTrigger.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("id_triggersTable").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:params
 		});
	}
	
	
	function triggerOper(triggerName,flag) {
		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/'+flag+'Trigger.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("id_triggersTable").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:{
				triggerName:triggerName
			}
 		});
	}
	</script>
</head>
<body>
	<div id="id_triggerContainer"></div>
	
	<div id="__quartzTriggerDetailInfoWindow" class="mini-window" title="调度任务" style="display:none;width:580px;height:280px;">
		<div id="id_quartzTriggerDetailInfoForm" style="padding: 15px 25px">
			<input id="id_triggerName" type="hidden" class="mini-hidden"/>
			<table align="center" style="width:100%;position: relative;">
				<tr><td>任务名称:</td><td id="id_comboJobContainer"></td><td><span class="content-required">*</span></td></tr>
				<tr><td>调度开始时间:</td><td><input id="id_startDate" allowInput="false" class="mini-datepicker" style="width: 350px;" format="yyyy-MM-dd HH:mm:ss" timeFormat="HH:mm:ss" showTime="true" showOkButton="true" showClearButton="false"/></td><td><span class="content-required">*</span></td></tr>
				<tr><td>调度持续时间（天）:</td><td><input id="id_durableDays" class="mini-textbox" style="width: 350px;"/></td><td><span class="content-required">*</span></td></tr>
				<tr><td>CRON表达式:</td><td><input id="id_cronExpression" class="mini-textbox" style="width: 350px;" onclick="showCronWindow();"/></td><td><span class="content-required">*</span></td></tr>
				<tr><td>调度描述:</td><td><textarea id="id_description" class="mini-textarea" style="width: 350px;"></textarea></td><td><span class="content-required">*</span></td></tr>
				<tr class="content-separator">
					<td colspan="3">
						<a class="mini-button" onclick='doQuartzTriggerOperation'><span>确定</span></a>
						<a class="mini-button" onclick='doQuartzTriggerOperationClose'><span>取消</span></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	
	<c:set var="sixty" value="0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59" ></c:set>
	<c:set var="twentyFour" value="0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23" ></c:set>
	<c:set var="thirtyOne" value="1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31" ></c:set>
	<c:set var="twelve" value="1|2|3|4|5|6|7|8|9|10|11|12" ></c:set>
	<c:set var="week" value="星期日|星期一|星期二|星期三|星期四|星期五|星期六" ></c:set>
	
	<div id="__quartzCronDetailInfoWindow" class="mini-window" title="CRON转换器" style="display:none;width:700px;height:350px;">
		<div id="id_quartzCronDetailInfoForm">
			<div id="tt" class="mini-tabs" style="position: relative;">
				<div title="秒" style="padding:0px 10px;">
					<input name="cronSecond" value="per" type="radio" checked/>每秒都执行
					<br/>
					<input name="cronSecond" value="cycle" type="radio"/>循环：
					<div class="content-container">从第&nbsp;<input id="id_cronSecondStart" type="text" class="twoNumInput" value="0"/>&nbsp;秒开始，每隔&nbsp;<input id="id_cronSecondPer" type="text" class="twoNumInput" value="1"/>&nbsp;秒执行。</div>
					<input name="cronSecond" value="assigned" type="radio" />指定：
					<div class="content-container">
						<c:forTokens var="str" items="${sixty}" delims="|" varStatus="status"> 
							<c:if test="${1 == fn:length(str)}"><input name="checkbox_cronSecond" type="checkbox" value="${str}"/>0${str}&nbsp;</c:if>
							<c:if test="${2 == fn:length(str)}"><input name="checkbox_cronSecond" type="checkbox" value="${str}"/>${str}&nbsp;</c:if>
							<c:if test="${(str + 1)%15 == 0}"><br/></c:if>
						</c:forTokens>
					</div>
				</div>
				<div title="分钟" style="padding:0px 10px;">
					<input name="cronMinute" value="per" type="radio" checked/>每分钟都执行
					<br/>
					<input name="cronMinute" value="cycle" type="radio" />循环：
					<div class="content-container">从第&nbsp;<input value="0" type="text" id="id_cronMinuteStart" class="twoNumInput"/>&nbsp;分钟开始，每隔&nbsp;<input type="text" id="id_cronMinutePer" class="twoNumInput" value="1" />&nbsp;分钟执行。</div>
					<input name="cronMinute" value="assigned" type="radio" />指定：
					<div class="content-container">
						<c:forTokens var="str" items="${sixty}" delims="|" varStatus="status"> 
							<c:if test="${1 == fn:length(str)}"><input name="checkbox_cronMinute" type="checkbox" value="${str}"/>0${str}&nbsp;</c:if>
							<c:if test="${2 == fn:length(str)}"><input name="checkbox_cronMinute" type="checkbox" value="${str}"/>${str}&nbsp;</c:if>
							<c:if test="${(str + 1)%15 == 0}"><br/></c:if>
						</c:forTokens>
					</div>
				</div>
				<div title="小时" style="padding:0px 10px;">
					<input name="cronHour" value="per" type="radio" checked/>每小时都执行
					<br/>
					<input name="cronHour" value="cycle" type="radio" />循环：
					<div class="content-container">从&nbsp;<input value="0" type="text" id="id_cronHourStart" class="twoNumInput"/>&nbsp;时开始，每隔&nbsp;<input type="text" id="id_cronHourPer" class="twoNumInput" value="1" />&nbsp;小时执行。</div>
					<input name="cronHour" value="assigned" type="radio" />指定：
					<div class="content-container">
						上午：
						<c:forTokens var="str" items="${twentyFour}" delims="|" varStatus="status"> 
							<c:if test="${status.index == 12}">
								<br/>下午：
							</c:if> 
							<c:if test="${1 == fn:length(str)}"><input name="checkbox_cronHour" type="checkbox" value="${str}"/>0${str}</c:if>
							<c:if test="${2 == fn:length(str)}"><input name="checkbox_cronHour" type="checkbox" value="${str}"/>${str}</c:if>
						</c:forTokens>
					</div>
				</div>
				<div title="天" style="padding:0px 10px;">
					<input name="cronPriority" value="day" type="radio" checked/>天优先（优先级高于&nbsp;<font color="red">星期</font>&nbsp;）
					<br/>
					<input name="cronDay" value="per" type="radio" checked/>每天都执行
					<br/>
					<input name="cronDay" value="cycle" type="radio" />循环：
					<div class="content-container">从第&nbsp;<input value="1" type="text" id="id_cronDayStart" class="twoNumInput"/>&nbsp;天开始，每隔&nbsp;<input type="text" id="id_cronDayPer" class="twoNumInput" value="1" />&nbsp;天执行。</div> 
					<input name="cronDay" value="assigned" type="radio" />指定：
					<div class="content-container">
						<c:forTokens var="str" items="${thirtyOne}" delims="|" varStatus="status"> 
							<c:if test="${1 == fn:length(str)}"><input name="checkbox_cronDay" type="checkbox" value="${str}"/>0${str}</c:if>
							<c:if test="${2 == fn:length(str)}"><input name="checkbox_cronDay" type="checkbox" value="${str}"/>${str}</c:if>
							<c:if test="${str%16 == 0}"><br/></c:if>
						</c:forTokens>
					</div>
				</div>
				<div title="月" style="padding:0px 10px;">
					<input name="cronMonth" value="per" type="radio" checked/>每月都执行
					<br/>
					<input name="cronMonth" value="cycle" type="radio" />循环：
					<div class="content-container">从&nbsp;<input value="1" type="text" id="id_cronMonthStart" class="twoNumInput"/>&nbsp;月开始，每隔&nbsp;<input type="text" id="id_cronMonthPer" class="twoNumInput" value="1" />&nbsp;月执行。</div> 
					<input name="cronMonth" value="assigned" type="radio" />指定：
					<div class="content-container">
						<c:forTokens var="str" items="${twelve}" delims="|" varStatus="status"> 
							<c:if test="${1 == fn:length(str)}"><input name="checkbox_cronMonth" type="checkbox" value="${str}"/>0${str}</c:if>
							<c:if test="${2 == fn:length(str)}"><input name="checkbox_cronMonth" type="checkbox" value="${str}"/>${str}</c:if>
						</c:forTokens>
					</div>
				</div>
				<div title="星期" style="padding:0px 10px;">
					<input name="cronPriority" value="week" type="radio" />星期优先（优先级高于&nbsp;<font color="red">天</font>&nbsp;）
					<div>
						<input name="cronWeek" value="per" type="radio" checked/>每星期都执行
						<br/>
						<input name="cronWeek" value="cycle" type="radio" />循环：
						<div class="content-container">从&nbsp;<select id="id_cronWeekStart">
							<c:forTokens var="str" items="${week}" delims="|" varStatus="status"> 
								<option value="${status.index+1}" <c:if test="${0 == status.index}"></c:if>/>${str}</option>
							</c:forTokens>
						</select>&nbsp;开始，每隔&nbsp;<input type="text" id="id_cronWeekPer" class="twoNumInput" value="1" />&nbsp;天执行。
						</div> 
						<input name="cronWeek" value="assigned" type="radio" />指定：
						<div class="content-container">
							<c:forTokens var="str" items="${week}" delims="|" varStatus="status"> 
								<input name="checkbox_cronWeek" type="checkbox" value="${status.index+1}"/>${str}
							</c:forTokens>
						</div>
					</div>
				</div>
				<div title="年" style="padding:0px 10px;">
					<input name="cronYear" value="per" type="radio" checked/>每年都执行
					<br/>
					<input name="cronYear" value="cycle" type="radio" />循环：
					<div class="content-container">从&nbsp;<input value="*" type="text" id="id_cronYearStart" class="fourNumInput"/>&nbsp;年开始，每隔&nbsp;<input type="text" id="id_cronYearPer" class="twoNumInput" value="1" />&nbsp;年执行。</div> 
					<input name="cronYear" value="assigned" type="radio" />指定年份（多个年份以<font color="red">英文逗号</font>隔开,形如：2012<font color="red">,</font>2013）：
					<div class="content-container">
						<textarea id="id_cronYear_assigned" style="overflow:hidden;border:transparent;width:100%;height:16px;"></textarea>
					</div>
					<input name="cronYear" value="period" type="radio" />时间段：
					<div class="content-container">
						选择：&nbsp;<input type="text" id="id_cronYearPeriodStart" class="fourNumInput"/>&nbsp;----&nbsp;<input type="text" id="id_cronYearPeriodEnd" class="fourNumInput"/>&nbsp;年 
					</div>
				</div>
			</div>
			<div style="text-align: center;padding: 15px;">
				<a class="mini-button" onclick='generateCron'><span>确定</span></a>
				<a class="mini-button" onclick='doQuartzCronDetailInfoWindowClose'><span>取消</span></a>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript">
	function showCronWindow(tempCron) {
		var cronExpression = mini.get("id_cronExpression").getValue();
		if(tempCron) {
			cronExpression = tempCron;
		}
		if(!cronExpression) {
			cronExpression = "* * * * * ? *";
		}
		resolveCronExpressionToUI(jQuery,cronExpression);
		mini.get("__quartzCronDetailInfoWindow").show();
		jQuery("#id_cronSecondStart").focus();
	}
	
	function generateCron() {
		var cronExpression = resolveUIToCronExpression(jQuery);
		if(cronExpression) {
			mini.get("id_cronExpression").setValue(cronExpression);
			mini.get("__quartzCronDetailInfoWindow").hide();
		}
	}
	
	function resolveUIToCronExpression($) {
		var cronExpression = "";
		var secondField = resolveUIToCronExpression_items($,"Second");
		var minuteField = resolveUIToCronExpression_items($,"Minute");
		var hourField	= resolveUIToCronExpression_items($,"Hour");
		var dayField	= resolveUIToCronExpression_items($,"Day");
		var monthField = resolveUIToCronExpression_items($,"Month");
		var weekField	= resolveUIToCronExpression_items($,"Week");	
		var yearField	= resolveUIToCronExpression_items($,"Year");	
		if(!secondField || !minuteField || !hourField || !dayField|| !monthField || !weekField || !yearField) {
			return null;
		}		
		cronExpression = secondField
		+" "+minuteField
		+" "+hourField
		+" "+dayField
		+" "+monthField
		+" "+weekField
		+" "+yearField;
		return cronExpression;
	}
	
	//解析秒开始
	function resolveCronExpressionToUI($,cronExpression) {
		cronExpression = cronExpression.replace(/\s+/," ");
		var splitFields = cronExpression.split(" ");
		var secondField = splitFields[0];
		var minuteField = splitFields[1];
		var hourField = splitFields[2];
		var dayField = splitFields[3];
		var monthField = splitFields[4];
		var weekField = splitFields[5];
		resolveCronExpressionToUI_items($,"Second",secondField);
		resolveCronExpressionToUI_items($,"Minute",minuteField);
		resolveCronExpressionToUI_items($,"Hour",hourField);
		resolveCronExpressionToUI_items($,"Day",dayField);
		resolveCronExpressionToUI_items($,"Month",monthField);
		resolveCronExpressionToUI_items($,"Week",weekField);
		if(splitFields.length > 6) {
			var yearField = splitFields[6];
			resolveCronExpressionToUI_items($,"Year",yearField);
		}
	}
	
	function resolveUIToCronExpression_items($,flag) {
		if(("Day"==flag)||("Week"==flag)) {
			var cronPriorityVal = $("#tt input[name='cronPriority'][type='radio']:checked").val();
			if(flag.toLowerCase()!=cronPriorityVal) {
				return "?";
			}
		}
		var selectedValues = "";
		var selectedItemVal = $("#tt input[name='cron"+flag+"'][type='radio']:checked").val();
		if("cycle"==selectedItemVal) {
			var cronItemStartVal = $("#id_cron"+flag+"Start").val();
			var cronItemPerVal = $("#id_cron"+flag+"Per").val();
			if(""==cronItemStartVal) {
				if(""==cronItemPerVal) {
					selectedValues = "*";
				} else {
					selectedValues = "*/"+cronItemPerVal;
				}
			} else {
				if(""==cronItemPerVal) {
					selectedValues = cronItemStartVal;
				} else {
					selectedValues = cronItemStartVal+"/"+cronItemPerVal;
				}
			}
		} else if("per"==selectedItemVal){
			selectedValues = "*";
		} else if("assigned"==selectedItemVal){
			if("Year" == flag) {
				var cronYearAssignedVal = $("#id_cronYear_assigned").val();
				if(cronYearAssignedVal) {
					selectedValues = cronYearAssignedVal;
				}
			}
			var isFirst = true;
			var selectedItemsCheckBox = $("#tt input[name='checkbox_cron"+flag+"'][type='checkbox']:checked");
			selectedItemsCheckBox.each(function(i){
				if(!isFirst) {
					selectedValues+=","; 
				}
				selectedValues+=this.value;
				isFirst = false;
			});
		} else if("period"==selectedItemVal){
			var cronItemStartVal = $("#id_cron"+flag+"PeriodStart").val();
			var cronItemEndVal = $("#id_cron"+flag+"PeriodEnd").val();
			if(cronItemStartVal&&cronItemEndVal) {
				selectedValues = cronItemStartVal+"-"+cronItemEndVal;
			} else if(!cronItemStartVal) {
				mini.alert("开始年份不能为空");
				return null;
			} else if(!cronItemEndVal) {
				mini.alert("结束年份不能为空");
				return null;
			}
		}
		
		var flagDisplay = "";
		switch(flag) {
			case "Second":{flagDisplay="秒";break;}
			case "Minute":{flagDisplay="分钟";break;}
			case "Hour":{flagDisplay="小时";break;}
			case "Day":{flagDisplay="天";break;}
			case "Month":{flagDisplay="月";break;}
			case "Week":{flagDisplay="星期";break;}
			case "Year":{flagDisplay="年";break;}
		}
		
		if("" == selectedValues) {
			mini.alert("没有指定"+flagDisplay+"!");
			return null;
		}
		return selectedValues;
	}
	
	function resolveCronExpressionToUI_items($,flag,fieldValue) {
		if("*"==fieldValue)	 {
			$("#tt input[name='cron"+flag+"'][type='radio'][value='per']").attr("checked",true);
		} else if("?"==fieldValue) {
			$("#tt input[name='cronPriority'][type='radio'][value!='"+flag.toLowerCase()+"']").attr("checked",true);
		} else if(fieldValue.indexOf("/")>-1) {
			$("#tt input[name='cron"+flag+"'][type='radio'][value='cycle']").attr("checked",true);
			var cronItemStartVal = fieldValue.split("/")[0];
			var cronItemPerVal	= fieldValue.split("/")[1];
			$("#id_cron"+flag+"Start").val(cronItemStartVal);
			$("#id_cron"+flag+"Per").val(cronItemPerVal);
		} else if(fieldValue.indexOf("-")>-1) {
			$("#tt input[name='cron"+flag+"'][type='radio'][value='period']").attr("checked",true);
			var cronItemStartVal = fieldValue.split("-")[0];
			var cronItemEndVal	= fieldValue.split("-")[1];
			$("#id_cron"+flag+"PeriodStart").val(cronItemStartVal);
			$("#id_cron"+flag+"PeriodEnd").val(cronItemEndVal);
		} else {
			$("#tt input[name='cron"+flag+"'][type='radio'][value='assigned']").attr("checked",true);
			if("Year" == flag) {
				$("#id_cronYear_assigned").val(fieldValue);
				return;
			}
			var selectedValues = fieldValue.split(",");
			var selectedItemsCheckBox = $("#tt input[name='checkbox_cron"+flag+"'][type='checkbox']");
			selectedItemsCheckBox.each(function(i){
				var isChecked = false;
				for(var i=0;i<selectedValues.length;i++) {
					if( this.value == selectedValues[i]) {
						isChecked = true;
						break;
					} 
				}
				this.checked = isChecked;
			});
		}
	}
	//上传函数结束
	jQuery(function(){
		jQuery("#tt input[type='radio']").addClass("commonCheck");
		jQuery("#tt input[type='checkbox']").addClass("commonCheck");
		jQuery("#id_cronYearStart").val(new Date().getFullYear());
	});
	</script>
</html>