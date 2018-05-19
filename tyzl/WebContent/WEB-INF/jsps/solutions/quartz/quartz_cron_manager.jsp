<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表组件测试</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css"/>
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
	  input.commonCheck{
	     position:relative;
	     top:2px;
	  }
	</style>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <c:set var="sixty" value="0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59" ></c:set>
    <c:set var="twentyFour" value="0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23" ></c:set>
    <c:set var="thirtyOne" value="1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31" ></c:set>
    <c:set var="twelve" value="1|2|3|4|5|6|7|8|9|10|11|12" ></c:set>
    <c:set var="week" value="星期日|星期一|星期二|星期三|星期四|星期五|星期六" ></c:set>
   	<div id="tt" class="easyui-tabs"  >
		<div title="秒"  style="padding:20px;">
		    <input name="cronSecond" value="per" type="radio" checked/>每秒都执行
			<br/>
		    <input name="cronSecond" value="cycle" type="radio"/>循环：
		        <div class="content-container">从第&nbsp;<input id="id_cronSecondStart" type="text" class="twoNumInput" value="0"/>&nbsp;秒开始，每隔&nbsp;<input id="id_cronSecondPer" type="text" class="twoNumInput"  value="1"/>&nbsp;秒执行。</div>
		    <input name="cronSecond" value="assigned" type="radio" />指定：
			    <div class="content-container">
				    <c:forTokens var="str" items="${sixty}" delims="|" varStatus="status">  
				         <input  name="checkbox_cronSecond" type="checkbox" value="${str}"/>${str}
				         <c:if test="${1 == fn:length(str)}">
				             &nbsp;
				         </c:if>
				    </c:forTokens>
			    </div>
		</div>
		<div title="分钟"  style="padding:20px;">
		    <input name="cronMinute" value="per" type="radio" checked/>每分钟都执行
			<br/>
		    <input name="cronMinute" value="cycle" type="radio" />循环：
		    	<div class="content-container">从第&nbsp;<input value="0" type="text" id="id_cronMinuteStart"  class="twoNumInput"/>&nbsp;分钟开始，每隔&nbsp;<input type="text" id="id_cronMinutePer"  class="twoNumInput" value="1" />&nbsp;分钟执行。</div>
		    <input name="cronMinute" value="assigned" type="radio" />指定：
			    <div class="content-container">
				    <c:forTokens var="str" items="${sixty}" delims="|" varStatus="status">  
				         <input  name="checkbox_cronMinute" type="checkbox" value="${str}"/>${str}
				         <c:if test="${1 == fn:length(str)}">
				             &nbsp;
				         </c:if>
				    </c:forTokens>
			    </div>
		</div>
		<div title="小时" style="padding:20px;">
		    <input name="cronHour" value="per" type="radio" checked/>每小时都执行
		    <br/>
		    <input name="cronHour" value="cycle" type="radio" />循环：
		    	<div class="content-container">从&nbsp;<input value="0" type="text" id="id_cronHourStart"  class="twoNumInput"/>&nbsp;时开始，每隔&nbsp;<input type="text" id="id_cronHourPer"  class="twoNumInput" value="1" />&nbsp;小时执行。</div>
		  	<input name="cronHour" value="assigned" type="radio" />指定：
			    <div class="content-container">
				    上午：<c:forTokens var="str" items="${twentyFour}" delims="|" varStatus="status"> 
				         <c:if test="${status.index == 12}">
				             <br/>下午：
				         </c:if> 
				         <input  name="checkbox_cronHour"  type="checkbox" value="${str}"/>${str}
				         <c:if test="${1 == fn:length(str)}">
				             &nbsp;
				         </c:if>
				    </c:forTokens>
			    </div>
		</div>
		<div title="天" style="padding:20px;">
		   <input name="cronPriority" value="day" type="radio" checked/>天优先（优先级高于&nbsp;<font color="red">星期</font>&nbsp;）
		   <div>
		        <input name="cronDay" value="per" type="radio" checked/>每天都执行
			   	<br/>
		        <input name="cronDay" value="cycle" type="radio" />循环：
		    	<div class="content-container">从第&nbsp;<input value="1" type="text" id="id_cronDayStart"  class="twoNumInput"/>&nbsp;天开始，每隔&nbsp;<input type="text" id="id_cronDayPer"  class="twoNumInput" value="1" />&nbsp;天执行。</div>  
			   	<input name="cronDay" value="assigned" type="radio" />指定：
				    <div class="content-container">
					    <c:forTokens var="str" items="${thirtyOne}" delims="|" varStatus="status">  
					         <input  name="checkbox_cronDay"   type="checkbox" value="${str}"/>${str}
					         <c:if test="${1 == fn:length(str)}">
					             &nbsp;
					         </c:if>
					    </c:forTokens>
				    </div>
		   </div>
		</div>
		<div title="月" style="padding:20px;">
		   	<input name="cronMonth" value="per" type="radio" checked/>每月都执行
			<br/>
		    <input name="cronMonth" value="cycle" type="radio" />循环：
		    	<div class="content-container">从&nbsp;<input value="1" type="text" id="id_cronMonthStart"  class="twoNumInput"/>&nbsp;月开始，每隔&nbsp;<input type="text" id="id_cronMonthPer"  class="twoNumInput" value="1" />&nbsp;月执行。</div>  
		   	<input name="cronMonth" value="assigned" type="radio" />指定：
			    <div class="content-container">
				    <c:forTokens var="str" items="${twelve}" delims="|" varStatus="status">  
				         <input  name="checkbox_cronMonth"  type="checkbox" value="${str}"/>${str}
				         <c:if test="${1 == fn:length(str)}">
				             &nbsp;
				         </c:if>
				    </c:forTokens>
			    </div>
		</div>
		<div title="星期" style="padding:20px;">
		    <input name="cronPriority" value="week" type="radio" />星期优先（优先级高于&nbsp;<font color="red">天</font>&nbsp;）
		    <div>
			    <input name="cronWeek" value="per" type="radio" checked/>每星期都执行
			   	<br/>
		        <input name="cronWeek" value="cycle" type="radio" />循环：
		    	<div class="content-container">从&nbsp;<select id="id_cronWeekStart">
		    	    <c:forTokens var="str" items="${week}" delims="|" varStatus="status">  
					         <option value="${status.index+1}" <c:if test="${0 == status.index}"></c:if>/>${str}</option>
					</c:forTokens>
		    	</select>&nbsp;开始，每隔&nbsp;<input type="text" id="id_cronWeekPer"  class="twoNumInput" value="1" />&nbsp;天执行。</div>  
			   	<input name="cronWeek" value="assigned" type="radio" />指定：
				    <div class="content-container">
					    <c:forTokens var="str" items="${week}" delims="|" varStatus="status">  
					         <input name="checkbox_cronWeek"  type="checkbox" value="${status.index+1}"/>${str}
					    </c:forTokens>
				    </div>
			 </div>
		</div>
		<div title="年" style="padding:20px;">
		   <input name="cronYear" value="per" type="radio" checked/>每年都执行
		   <br/>
		   <input name="cronYear" value="cycle" type="radio" />循环：
		       <div class="content-container">从&nbsp;<input value="*" type="text" id="id_cronYearStart"  class="fourNumInput"/>&nbsp;年开始，每隔&nbsp;<input type="text" id="id_cronYearPer"  class="twoNumInput" value="1" />&nbsp;年执行。</div>  
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
	<textarea id="id_cron"></textarea>
	<input type="button" onclick="resolveUIToCronExpression(jQuery);" value="生成cron表达式"/>
	<input type="button" onclick="resolveCronExpressionToUI(jQuery);" value="解析cron表达式"/>
</body>
    <script type="text/javascript">
    function resolveUIToCronExpression($)
    {
       
        	var cronExpression =	
            	resolveUIToCronExpression_items($,"Second")
                +" "+resolveUIToCronExpression_items($,"Minute")
                +" "+resolveUIToCronExpression_items($,"Hour")
                +" "+resolveUIToCronExpression_items($,"Day")
                +" "+resolveUIToCronExpression_items($,"Month")
                +" "+resolveUIToCronExpression_items($,"Week")
                +" "+resolveUIToCronExpression_items($,"Year");
         $("#id_cron").val(cronExpression);
    }
    //解析秒开始
    function resolveCronExpressionToUI($/*,cronExpression*/)
    {
    	var cronExpression = $("#id_cron").val();
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
    	if(splitFields.length > 6)
    	{
    	  var yearField = splitFields[6];
    	  resolveCronExpressionToUI_items($,"Year",yearField);
        }
        //解析毫秒字段
    }
    function resolveUIToCronExpression_items($,flag)
    {
        if(("Day"==flag)||("Week"==flag))
        {
            var cronPriorityVal = $("#tt input[name='cronPriority'][type='radio']:checked").val();
            if(flag.toLowerCase()!=cronPriorityVal)
            {
                return "?";
            }
        }
    	var selectedValues = "";
        var selectedItemVal = $("#tt input[name='cron"+flag+"'][type='radio']:checked").val();
        if("cycle"==selectedItemVal) {
            var cronItemStartVal = $("#id_cron"+flag+"Start").val();
            var cronItemPerVal = $("#id_cron"+flag+"Per").val();
            if(""==cronItemStartVal)
            {
                if(""==cronItemPerVal)
                {
                	selectedValues = "*";
                }
                else
                {
                	selectedValues = "*/"+cronItemPerVal;
                }
            }
            else
            {
                if(""==cronItemPerVal)
                {
                	selectedValues = cronItemStartVal;
                }
                else
                {
                	selectedValues = cronItemStartVal+"/"+cronItemPerVal;
                }
            }
        }
        else if("per"==selectedItemVal){
        	selectedValues = "*";
        }
        else if("assigned"==selectedItemVal){
            if("Year" == flag)
            {
            	var cronYearAssignedVal = $("#id_cronYear_assigned").val();
            	if(cronYearAssignedVal)
            	{
            		selectedValues = cronYearAssignedVal;
                }
            }
        	var isFirst = true;
            var selectedItemsCheckBox = $("#tt input[name='checkbox_cron"+flag+"'][type='checkbox']:checked");
            selectedItemsCheckBox.each(function(i){
                  if(!isFirst)
                  {
                	  selectedValues+=","; 
                  }
                  selectedValues+=this.value;
                  isFirst = false;
            });
        }
        else if("period"==selectedItemVal){
            var cronItemStartVal = $("#id_cron"+flag+"PeriodStart").val();
            var cronItemEndVal = $("#id_cron"+flag+"PeriodEnd").val();
            if(cronItemStartVal&&cronItemEndVal)
            {
            	selectedValues = cronItemStartVal+"-"+cronItemEndVal;
            }
            else if(!cronItemStartVal)
            {
            	alert("开始年份不能为空");
            	return null;
            }
            else if(!cronItemEndVal)
            {
            	alert("结束年份不能为空");
            	return null;
            }
        }
        var flagDisplay = "";
        switch(flag)
        {
           case "Second":{flagDisplay="秒";break;}
           case "Minute":{flagDisplay="分钟";break;}
           case "Hour":{flagDisplay="小时";break;}
           case "Day":{flagDisplay="天";break;}
           case "Month":{flagDisplay="月";break;}
           case "Week":{flagDisplay="星期";break;}
           case "Year":{flagDisplay="年";break;}
        }
        if("" == selectedValues)
        {
            alert("没有指定"+flagDisplay+"!");
            return null;
        }
        return selectedValues;
    }
    function resolveCronExpressionToUI_items($,flag,fieldValue)
    {
        if("*"==fieldValue)   
        {
        	$("#tt input[name='cron"+flag+"'][type='radio'][value='per']").attr("checked",true);
        }
        else if("?"==fieldValue)   
        {
        	$("#tt input[name='cronPriority'][type='radio'][value!='"+flag.toLowerCase()+"']").attr("checked",true);
        }
        else if(fieldValue.indexOf("/")>-1)
        {
        	$("#tt input[name='cron"+flag+"'][type='radio'][value='cycle']").attr("checked",true);
            var cronItemStartVal = fieldValue.split("/")[0];
            var cronItemPerVal   = fieldValue.split("/")[1];
            $("#id_cron"+flag+"Start").val(cronItemStartVal);
            $("#id_cron"+flag+"Per").val(cronItemPerVal);
        }
        else if(fieldValue.indexOf("-")>-1)
        {
        	$("#tt input[name='cron"+flag+"'][type='radio'][value='period']").attr("checked",true);
            var cronItemStartVal = fieldValue.split("-")[0];
            var cronItemEndVal   = fieldValue.split("-")[1];
            $("#id_cron"+flag+"PeriodStart").val(cronItemStartVal);
            $("#id_cron"+flag+"PeriodEnd").val(cronItemEndVal);
        }
        else
        {
        	$("#tt input[name='cron"+flag+"'][type='radio'][value='assigned']").attr("checked",true);
            if("Year" == flag)
            {
            	$("#id_cronYear_assigned").val(fieldValue);
                return;
            }
        	var selectedValues = fieldValue.split(",");
            var selectedItemsCheckBox = $("#tt input[name='checkbox_cron"+flag+"'][type='checkbox']");
            selectedItemsCheckBox.each(function(i){
                var isChecked = false;
                for(var i=0;i<selectedValues.length;i++)
                {
                   if( this.value == selectedValues[i])  
                   {
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