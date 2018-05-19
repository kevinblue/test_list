<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='c'	uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>短信配置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '短信配置',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:true,
				editFormPopupWindowHeight:500,
				remoteOper:true,
				entityClassName:'com.tenwa.business.entity.message.MsgConfig',
				showPager:true,
				xmlFileName : '/interface/queryMsgConfig.xml',
				operValidate:function(miniTable, rowdata, operFlag){
					if('add' == operFlag){
						return true;
					}
					if('edit' == operFlag){
						var selectedRowData = miniTable.getSelected();
						if(selectedRowData){
							if(selectedRowData.msgstatus == 'ACQUIRED'){
								mini.alert("该定时调度任务不能被编辑");
								return false;
							}
							return true;
						}
						mini.alert("请选择一行数据!");
						return false;
					}
				},
				afterShowWindowCallBack:function(miniTable,miniForm, operFlag){
					var selectedRowData = miniTable.getSelected();
					if(selectedRowData){
						checkMsgType(selectedRowData.msgtype);
						msgObjectChange(selectedRowData.msgobject);
					}
				},
				validateForm:function(miniTable,miniForm,editFormItemOperFlag,addIndex){
					var data = miniTable.getEditFormData();
					var selectedRowData = miniTable.getSelected();
					var id;
					var msgtype;
					if("add" == editFormItemOperFlag){
						id = "";
						msgtype = data.msgtype;
					}else if("edit" == editFormItemOperFlag){
						id = selectedRowData.id;
						msgtype = selectedRowData.msgtype;
					}
					var flag = false;
					tenwa.ajaxRequest({
						url:'${pageContext.request.contextPath}/quartz/checkMsgType.acl',
						method:'POST',
						async:false,
						success:function(res){
							var result = JsonUtil.decode(res.responseText).returnType;
							if(result == "SUCCESS"){
								flag = true;
							}else{
								mini.alert("此短信类型已存在！");
							}
						},
						params:{"msgtype":msgtype,"id":id,"type":editFormItemOperFlag}
			  		});
					if(!flag){
						return false;
					}
					if(data.msgsql.indexOf("extract") != -1 || data.msgsql.indexOf("EXTRACT") != -1){
						alert("不支持extract函数！");
						return false; 
					}
					var params = [];
					var num = data.msgcontent.length-data.msgcontent.replace(/}/g,"").length;
					if(num > 0)
						var index = 0;
						for(var p=0;p<num;p++){
							var start = data.msgcontent.indexOf("{", index);
							var end = data.msgcontent.indexOf("}", index);
							params.push(data.msgcontent.substring(start+1, end));
							index = end+1;
						}
					var sqlparam = data.msgsqlparam.split(",");
					if(sqlparam.length > 0){
						for(var s=0;s<sqlparam.length;s++){
							params.push(sqlparam[s]);
						}
					}
					var ale = "SQL语句中缺少必须的返回字段：";
					var fl = false;
					var sq = data.msgsql.substring(data.msgsql.indexOf("select")+6,data.msgsql.indexOf("from"));
					for(var i=0;i<params.length;i++){
						if(sq.indexOf("."+params[i].toLowerCase()+" ") == -1 
								&& sq.indexOf(" "+params[i].toLowerCase()+" ") == -1
									&& sq.indexOf(" "+params[i].toLowerCase()+",") == -1
										&& sq.indexOf("."+params[i].toLowerCase()+",") == -1
											&& sq.indexOf(","+params[i].toLowerCase()+",") == -1){
							ale += params[i]+",";
							fl = true;
						}
					}
					if(fl){
						mini.alert(ale.substring(0,ale.length-1)+"！");
						return false;
					}
					var reg = /^\d{1,2}:\d{1,2}:\d{1,2}$/;
					if(!data.msgsendtime.match(reg)){
						mini.alert("短信发送时间格式错误,请参照00:00:00或0:0:0！");
						return false;
					}
					if(data.msgsqlparam.toLowerCase().indexOf("phonenumber")==-1){
						mini.alert("sql关联关键字必须含有phonenumber!");
						return false;
					}
					if(data.msgsqlparam.toLowerCase().indexOf("cust_id")==-1){
						mini.alert("sql关联关键字必须含有cust_id!");
						return false;
					}
					if(data.msgtype.indexOf("holiday") != -1 || data.msgtype.indexOf("birthday") != -1){
						return true;
					}
					if(data.msgsql.toLowerCase().indexOf("proj_manage")==-1){
						mini.alert("sql必须返回proj_manage信息!");
						return false;
					}
					if(data.msgsql.toLowerCase().indexOf("contract_id")==-1){
						mini.alert("sql必须返回contract_id信息!");
						return false;
					}
					return true;
				},
				submitReturnCallBack:function(miniTable,a,b,resultJson){
					var data = miniTable.getEditFormData();   //获取表单多个控件的数据   
					doQuartzJobAndTriggerOperation(data);
			 	},
				tools : ['add', '-' ,'edit', '-' ,{
					html:'删除',
					plain : true,//按钮是否有立体感
					iconCls : 'icon-remove',//按钮的图标
					handler:function(miniTable, buttonText){
						var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
						if(selectedRowDatas.length > 0){
							for(var i=0;i<selectedRowDatas.length;i++){
								var rowData = selectedRowDatas[i];
								if(rowData.msgstatus == 'PAUSED' || rowData.msgstatus == 'WAITING'){
									triggerOper(rowData.triggername, 'remove' ,rowData.id);
								} else {
									mini.alert("该定时调度任务不能被删除！");
								}
							}
						}else{
							mini.alert("请选中一行数据！");
						}
					}
				},'-', {
					html:'立即执行',
					plain : true,//按钮是否有立体感
					iconCls : 'icon-edit',//按钮的图标
					handler:function(miniTable, buttonText){
						var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
						if(selectedRowDatas.length > 0){
							var rowData = selectedRowDatas[0];
							if(rowData.msgstatus == 'ACQUIRED' || rowData.msgstatus == 'PAUSED'){
								triggerOper(rowData.triggername, 'run');
							} else {
								mini.alert("该定时调度任务不能被执行！");
							}
						}else{
							mini.alert("请选中一行数据！");
						}
					}
				}/* ,'-', {
					html:'删除短信任务',
					plain : true,//按钮是否有立体感
					iconCls : 'icon-edit',//按钮的图标
					handler:function(miniTable, buttonText){
						$.ajax({
							url:'${pageContext.request.contextPath}/quartz//showMission.action'
						})
					}
				} */],
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},  
					{header:'记录编号',field:'id',visible : false},
					{header:'定时任务编号',field:'jobname',visible : false},
					{header:'定时任务时间编号',field:'triggername',visible : false},
					{field:'msgtypename',header:'短信类型', 
					    formEditorConfig:{
					        fieldVisible:false,
					   fillFromFieldName:'msgtype',
					        fillProperty:'name'}},
					{header:'短信类型',field:'msgtype',visible:false,
						formEditorConfig:{
							type:'combobox',
							width:200,
		            		required : true,
					        textField:'name',
		            		valueField:'value',
					        fieldVisible:true,
					        params:{xmlFileName:'eleasing/jsp/base/msg_config.xml',PID:'MsgType'},
					        onvaluechanged:'checkMsgType'
						}
		            },
		            {field:'isoverdateexecutename',header:'是否过期执行', 
					    formEditorConfig:{
					        fieldVisible:false,
					   fillFromFieldName:'isoverdateexecute',
					        fillProperty:'name'}},
		            {header:'是否过期执行',field:'isoverdateexecute',visible:false,
						formEditorConfig:{
							type:'combobox',
							width:200,
		            		required : true,
					        textField:'name',
		            		valueField:'value',
					        fieldVisible:true,
					        params:{xmlFileName:'eleasing/jsp/base/msg_config.xml',PID:'IsOverdateExecute'},
						}
		            },
		            {header:'调度状态',field:'msgstatus',renderer:function(e){
				        var triggername = e.record.triggername;
						var trigger_state = e.record.msgstatus;
						var id_ = e.record.id;
						var display = "";
						var resumeOper = "<a href='javascript:void(0);' onclick='triggerOper(\""+triggername+"\",\"resume\",\""+id_+"\")'>恢复</a>&nbsp;&nbsp;";
						var pauseOper = "<a href='javascript:void(0);' onclick='triggerOper(\""+triggername+"\",\"pause\",\""+id_+"\")'>暂停</a>&nbsp;&nbsp;";
						switch(trigger_state) {
							case "ACQUIRED":{display = "运行";resumeOper="";break;}
							case "PAUSED":{display = "暂停";pauseOper="";break;}
							case "WAITING":{display = "等待";resumeOper="";break;}
						}
						return display + " / " + resumeOper + pauseOper;
						},
						formEditorConfig:{
							type:'combobox',
							required : true,
							newLine : true,
							data:[{id : 'ACQUIRED', text: '运行'},{id : 'PAUSED', text: '暂停'},{id : 'WAITING', text: '等待'}]
						}
					},
		            {header:'定时执行时间表达式',field:'cronexpression',width:150,renderer:function(e){
		                var cron_expression = e.record.cronexpression; 
		                var id_ = e.record.id;
		                var status = e.record.msgstatus;
		                var msgtype = e.record.msgtype;
		                return "<a href='javascript:void(0);' onclick='showCronWindow(\""+cron_expression+"\",\""+id_+"\",\""+status+"\",\""+msgtype+"\")'>"+cron_expression+"</a>&nbsp;&nbsp;";},
		                formEditorConfig:{
		                	type:'text',
							width:200,
		            		required : true,
		            		click:'showCronWindow'
						}},
		            {header:'调度开始时间',field:'startdate',width:130,dateFormat:'yyyy-MM-dd HH:mm:ss',
		            	formEditorConfig:{
		            		fieldVisible:false
		            	}},
		            {header:'调度结束时间',field:'enddate',width:130,dateFormat:'yyyy-MM-dd HH:mm:ss',
		            	formEditorConfig:{
		            		fieldVisible:false
		            	}},
		            {header:'调度上次执行时间',field:'previousfiretime',width:130,dateFormat:'yyyy-MM-dd HH:mm:ss',
		            	formEditorConfig:{
		            		fieldVisible:false
		            	}},
		            {header:'调度下次执行时间',field:'nextfiretime',width:130,dateFormat:'yyyy-MM-dd HH:mm:ss',
		            	formEditorConfig:{
		            		fieldVisible:false
		            	}},
		            
		            {field:'triggermodename',header:'触发方式', 
					    formEditorConfig:{
					        fieldVisible:false,
					   fillFromFieldName:'triggermode',
					        fillProperty:'name'}},
		            {header:'触发方式',field:'triggermode',visible:false,
						formEditorConfig:{
							type:'combobox',
							width:200,
							newLine:true,
		            		required : true,
		            		valueField:'value',
					        textField:'name',
					        fieldVisible:true,
					        params:{xmlFileName:'eleasing/jsp/base/msg_config.xml',PID:'TriggerMode'},
						}
		            },
		            {field:'msgobjectname',header:'发送时间类型', 
					    formEditorConfig:{
					        fieldVisible:false,
					   fillFromFieldName:'msgobject',
					        fillProperty:'name'}},
		            {header:'发送时间类型',field:'msgobject',visible:false,
						formEditorConfig:{
							type:'combobox',
							width:200,
							newLine: true,
		            		required : true,
		            		valueField:'value',
					        textField:'name',
					        fieldVisible:true,
					        params:{xmlFileName:'eleasing/jsp/base/msg_config.xml',PID:'MsgObject'},
					        onvaluechanged:'msgObjectChange'
						}
		            },
		            {header:'延迟发送天数',field:'delayday',
						formEditorConfig:{
							type:'int',
							width:200
						}
		            },
		            {header:'短信发送时间',field:'msgsendtime',
						formEditorConfig:{
							width:200,
							newLine: true,
							defaultValue:mini.formatDate(new Date(),"HH:mm:ss")
						}
		            },
		            {header:'调度持续时间(天)',field:'durabledays',visible:false,
						formEditorConfig:{
							type:'number',
							fieldVisible:true,
		            		required : true,
		            		maxValue : '95000',
		            		minValue : '1',
		            		defaultValue:95000
						}
		            },
		            {header:'发送记录关联关键字',field:'msgsqlparam',visible:false,
						formEditorConfig:{
							type:'combobox',
							width:'100%',
							newLine: true,
							required : true,
							multiSelect : true,
							valueField:'value',
					        textField:'name',
							colspan:3,
							fieldVisible:true,
							params:{xmlFileName:'eleasing/jsp/base/msg_send_relative.xml'}
						}
		            },
		            {header:'SQL',field:'msgsql',visible:false,
		            	formEditorConfig:{
		            		fieldVisible:true,
		            		type:'textarea',
		            		width:'100%',
		            		height:100,
		            		colspan:3,
		            		required : true,
		            		newLine: true//是否必填
						}
		            },
		            {header:'短信内容',field:'msgcontent',visible:false,
						formEditorConfig:{
							type:'textarea',
							width:'100%',
							height:100,
							colspan:3,
							fieldVisible:true,
		            		required : true,
		            		newLine: true
						}
		            },
		            {field:'creatorname',header:'创建人',
					    formEditorConfig:{
					        fieldVisible:true,
					        readonly:true,
					        newLine: true,
					   fillFromFieldName:'creator',
					        fillProperty:'name'}},
		            {header:'创建人',field:'creator',visible:false,
						formEditorConfig:{
							readonly:true,
							width:200,
		            		required : true,
		            		fieldVisible:false,
						}
		            },
		            {header:'创建时间',field:'createdate',dateFormat:'yyyy-MM-dd',
						formEditorConfig:{
							readonly:true,
							width:200,
							type:'date',
		            		required : true,
		            		format:'yyyy-MM-dd'
						}
		            },
		            {field:'modificatorname',header:'修改人',
					    formEditorConfig:{
					        fieldVisible:true,
					        readonly:true,
					        newLine: true,
					   fillFromFieldName:'modificator',
					        fillProperty:'name'}},
		            {header:'修改人',field:'modificator',visible:false,
						formEditorConfig:{
							fieldVisible:false,
							readonly:true,
							width:200,
		            		required : true
						}
		            },
		            {header:'修改时间',field:'modifydate',dateFormat:'yyyy-MM-dd',
						formEditorConfig:{
							readonly:true,
							type:'date',
							width:200,
		            		required : true,
		            		format:'yyyy-MM-dd'
						}
		            }
				]
			});
		});
	});
</script>
</head>
<body>
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
	function __getCurDateTime(date){
		return tenwa.toChar(date, 'yyyy-MM-dd hh:mm:ss');
	}
	
	function msgObjectChange(e){
		var value = e.value;
		if(!value){
			value = e;
		}
		if("msg_object01" == value){//当天立即
			getMiniEditFormField("table_id1.delayday").disable();
			getMiniEditFormField("table_id1.msgsendtime").disable();
		}else if("msg_object02" == value){//当天指定时间
			getMiniEditFormField("table_id1.delayday").disable();
			getMiniEditFormField("table_id1.msgsendtime").enable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","msgsendtime",true);
		}else if("msg_object03" == value){//本周最后一天
			getMiniEditFormField("table_id1.delayday").disable();
			getMiniEditFormField("table_id1.msgsendtime").enable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","msgsendtime",true);
		}else if("msg_object04" == value){//本月最后一天
			getMiniEditFormField("table_id1.delayday").disable();
			getMiniEditFormField("table_id1.msgsendtime").enable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","msgsendtime",true);
		}else if("msg_object05" == value){//延迟天
			getMiniEditFormField("table_id1.delayday").enable();
			getMiniEditFormField("table_id1.msgsendtime").enable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","delayday",true);
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","msgsendtime",true);
		}
	}
	
	function doQuartzJobAndTriggerOperation(data) {
		var params = data;
		params['startdate'] = __getCurDateTime(new Date());
		
		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/saveOrUpdateJobAndTrigger.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("table_id1").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:params
  		});
	}
	
	function doQuartzTriggerOperationClose(){
		mini.get('__quartzTriggerDetailInfoWindow').hide();
	}
	
	function doQuartzCronDetailInfoWindowClose(){
		mini.get('__quartzCronDetailInfoWindow').hide();
	}
	
	function triggerOper(triggerName,flag,id) {
		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/'+flag+'MsgTrigger.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("table_id1").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:{
				triggerName:triggerName,
				id:id
			}
 		});
	}
	
	function checkMsgType(e){
		var msgtype = e.value;
		if(!msgtype){
			msgtype = e;
		}
		if(msgtype.indexOf("holiday") != -1 || msgtype.indexOf("birthday") != -1){
			getMiniEditFormField("table_id1.cronexpression").disable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","cronexpression",false);
		}else{
			getMiniEditFormField("table_id1.cronexpression").enable();
			miniui_ext.initrequired("table_id1_editFormPopupWindow_form","cronexpression",true);
		}
	}
	
	function showCronWindow(tempCron,id,msgstatus,msgtype) {
		if(msgstatus == "ACQUIRED"){
			mini.alert("运行中的任务不能修改时间表达式!");
			return;
		}
		if(msgtype){
			if(msgtype.indexOf("holiday") != -1 || msgtype.indexOf("birthday") != -1){
				mini.alert("节假日祝福和生日祝福不能修改时间表达式！");
				return;
			}
		}
		var cronExpression = "";
		var id_ = "";
		if(tempCron.sender){
			cronExpression = tempCron.sender.value;
			id_ = tempCron.sender.id;
		}else{
			cronExpression = tempCron;
			id_ = id;
		}
		if(!cronExpression) {
			cronExpression = "* * * * * ? *";
		}
		resolveCronExpressionToUI(jQuery,cronExpression);
		mini.get("__quartzCronDetailInfoWindow").show();
		mini.get("__quartzCronDetailInfoWindow").setValue(id_);
		jQuery("#id_cronSecondStart").focus();
	}
	
	function generateCron() {
		var cronExpression = resolveUIToCronExpression(jQuery);
		if(cronExpression) {
			var id_ = mini.get("__quartzCronDetailInfoWindow").getValue();
			if(mini.get(id_)){
				mini.get(id_).setValue(cronExpression);
			}else{
			tenwa.ajaxRequest({
		 			type:'post',
		 			url:'${pageContext.request.contextPath}/quartz/updateCronExpression.acl',
		 			params : {"cronexpression":cronExpression,"id":id_},
					dataType : 'json',
					success:function(result){
						alert("时间表达式修改成功！");
						mini.get("table_id1").reload();
					}
		 		}); 
			}
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
</body>
</html>