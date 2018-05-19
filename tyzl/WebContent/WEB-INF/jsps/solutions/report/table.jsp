<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="r" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="fn"  uri="/WEB-INF/tlds/fn.tld" %>
<r:if test="${empty needjs || (needjs eq '1')}">
<!DOCTYPE html PUBLIC"-//W3C//DTDXHTML1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<head>
	<%@include file="/base/mini.jsp" %>
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/report/report.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript">
		var browserVersion = jQuery.browser.version;
		var pageWidth  = document.documentElement.clientWidth-2;
		var pageHeight = document.documentElement.clientHeight-2;
	</script>
	<style type="text/css">
		body{
			text-align:center;
		}

		.singleReport{
			width:99% !important;
			height:99% !important;
			margin-left:1% !important;
			margin-top:5px;
			border-left:1px solid #DDD;

		}
		.queryButton{
			float:right;			
			padding-top:1px;
			margin-left:10px;
		}

	</style>
</r:if>

<script type="text/javascript">

	jQuery(function(){
		mini.parse();
		window["func_table_${id}"]();		
	});

	var queryObject_${id} = new Object();
	window["func_table_${id}"] = function(){		
		queryObject_${id}.pageSize = 20;
		queryObject_${id}.id = '${id}';
		queryObject_${id}.name = '${title}';
		queryObject_${id}.type = 'TABLE';
		queryObject_${id}.url = '${pageContext.request.contextPath}/report/loadTableData2.action';
		
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/getHeader.action',
			data:{'id':'${id}','pageWidth':pageWidth-100},
			dataType:'json',
			async:false,
			success:function(data){
				queryObject_${id}.headers=data;
			}
		});

		// 预查询
		<r:if test="${empty searchs}">
			//mini.get('#search_${id}').hide();
			jQuery('#filter_${id}').css('display','block');
		</r:if>
		<r:if test="${not empty searchs}">
			mini.get('#search_${id}').show('center','middle');
			//jQuery('#search_${id}').css('display','block');
			jQuery('#filter_${id}').css('display','none');
		</r:if>
		
		// 查询区
		jQuery('#div_showquery_button_${id}').css('display','none');// 折叠区隐藏
		

		var divHeight = '${width > 0}' ? '${width}' : (pageHeight - 47);
		var divWidth = '${width > 0}' ? '${width}' : (pageWidth - 3);
		var divCount = '${divCount}' || 1;
		
	
		queryObject_${id}.width = (divWidth - 5);
		queryObject_${id}.height = (divHeight - 5);		
		queryObject_${id}.hasFilter = false;
		var filter_fields_${id} = new Array();
		<r:if test="${not empty filters}">
			var filter_comboboxs_${id} = new Array();
			<r:forEach items="${filters}" var="filter">
				var filter_field_${filter.id} = new Object();
				filter_field_${filter.id}.id = "${filter.id}";
				filter_field_${filter.id}.name =  "${filter.name}";
				filter_field_${filter.id}.value = "";
				filter_field_${filter.id}.type = "${filter.htmlType}";
				filter_field_${filter.id}.label = isEnglish() ?　"${filter.enname}" :  "${filter.label}";
				<r:if test="${filter.htmlType eq 'COMBOBOX'}" >
					filter_field_${filter.id}.requestType = "${filter.filterDataRequestType}";
					filter_field_${filter.id}.datasource="${(empty filter.comboBoxDataSource)?'':filter.comboBoxDataSource}";
					filter_field_${filter.id}.nameField = "${(empty filter.comboBoxNameField)?'text':filter.comboBoxNameField}";
					filter_field_${filter.id}.valueField = "${(empty filter.comboBoxValueField)?'value':filter.comboBoxValueField}";
					filter_field_${filter.id}.defaultValue="${(empty filter.defaultValue)?"":filter.defaultValue}";
					filter_comboboxs_${id}.push(filter_field_${filter.id});

				</r:if>
				filter_fields_${id}.push(filter_field_${filter.id});

			</r:forEach>
			createComboboxs(filter_comboboxs_${id},'filter','${id}'); 
			queryObject_${id}.filters = filter_fields_${id};
			queryObject_${id}.hasFilter = true;
		</r:if>

		
		var search_fields_${id} = new Array();
		<r:choose>
			<r:when test="${not empty searchs}">
				var search_comboboxs_${id} = new Array();
				<r:forEach items="${searchs}" var="search">
					var search_field_${search.id} = new Object();
					search_field_${search.id}.id = "${search.id}";
					search_field_${search.id}.name = "${search.name}";
					search_field_${search.id}.value = "";
					search_field_${search.id}.type = "${search.htmlType}";
					search_field_${search.id}.label = isEnglish() ?　"${search.enname}" : "${search.label}";
					<r:if test="${search.htmlType eq 'COMBOBOX'}">				
						search_field_${search.id}.requestType="${search.filterDataRequestType}";
						search_field_${search.id}.datasource="${(empty search.comboBoxDataSource)?'':search.comboBoxDataSource}";
						search_field_${search.id}.nameField = "${(empty search.comboBoxNameField)?'text':search.comboBoxNameField}";
						search_field_${search.id}.valueField = "${(empty search.comboBoxValueField)?'value':search.comboBoxValueField}";
						search_field_${search.id}.defaultValue="${(empty search.defaultValue)?"":search.defaultValue}";
						search_comboboxs_${id}.push(search_field_${search.id});
					</r:if>
					search_fields_${id}.push(search_field_${search.id});
				</r:forEach>
				queryObject_${id}.searchs = search_fields_${id};				
				createComboboxs(search_comboboxs_${id},'search','${id}');
				jQuery('#search_${id}').css('display','');
			</r:when>
			<r:otherwise>
				loadReportTableData(queryObject_${id});
			</r:otherwise>
		</r:choose>
		
		if(allQueryObjects != 'undefined'){
			allQueryObjects.push(queryObject_${id});
		}

	}

</script>
<r:if test="${empty needjs || (needjs eq '1')}">
</head>
<body style="overflow:hidden;">
</r:if>
<div id='table_${id}' class="singleReport x-panel-table-div x-panel-table-div-border" style="overflow:hidden;height: 100%">
	<div id="table_title_${id}" class="x-panel-table-div-title">
		<span class="x-panel-table-div-title-titleSpan">&nbsp;&nbsp;${title}</span>
		<div style="float:rigth;text-indent: 0px;">
			<div style="float:right;margin-right:10px;">&nbsp;</div>
			<c:if test="${requestScope['isExcel'] =='true' }">
				<div id="div_common_button_${id}" class="queryButton">
					<a href="javascript:void(0);" class="btn btn-primary" onclick="javascript:showExportWindow();"><span><spring:message code="Export" text="导出"/></span></a>
				</div>
			</c:if>
			<div id="div_showquery_button_${id}" class="queryButton">
				<a href="javascript:void(0)" class="btn btn-primary" onclick="javascript:showOrHiddenQueryArea(this,'${id}');"><span><spring:message code="Fold" text="折叠"/></span></a>
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>
	
	<r:set var="columnCount" value="2" />

	<r:if test="${fn:length(searchs) > 0}">
		<div id='search_${id}' style="display:none;overflow: hidden;" class="mini-window"  showCloseButton="false" showModal="true" allowDrag="true" width="820px" heigth="500px" title="预查询">
				<center style="margin-bottom:20px;width: 810px;overflow: hidden;">
					<table style="width:100%;border:1px solid #fdfdfd;margin-top:20px;margin-bottom:20px;overflow: hidden;">
							<r:forEach items="${searchs}" var="search" varStatus="status">
								<r:if test="${status.index%columnCount eq 0}"><tr></r:if>	
							<r:if test="${sessionScope['login_userlanguage'].language eq 'en'}">
								<td style="width:${50/(2*columnCount)}%">${search.enname}</td>
							</r:if>
							<r:if test="${sessionScope['login_userlanguage'].language eq 'zh'}">
								<td style="width:${50/(2*columnCount)}%">${search.label}</td>
							</r:if>							
								
								<td style="width:${150/(2*columnCount)}%">
								<r:choose>
									<r:when test="${search.htmlType eq 'TEXT' || empty search.htmlType}">
										<input id="search_${search.id}" name="search_${search.id}" class="mini-textbox" value="${(empty search.defaultValue)?'':search.defaultValue}" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:when test="${search.htmlType eq 'COMBOBOX'}">							
										<input id="search_${search.id}" name="search_${search.id}" class="mini-combobox" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:when test="${search.htmlType eq 'DATE'}">
										<input id="search_${search.id}" name="search_${search.id}" class="mini-datepicker" label="${search.label}" allowInput="false" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:when test="${search.htmlType eq 'DATERANGE'}">
										<input id="search_start_${search.id}" name="search_start_${search.id}" label="${search.label}" style="width:100px;" class="mini-datepicker" allowInput="false" onkeyup='doQuickQuery(event)'/>
										 -> 
										<input id="search_end_${search.id}" name="search_end_${search.id}" label="${search.label}"  style="width:100px;" class="mini-datepicker" allowInput="false" onkeyup='doQuickQuery(event)' />
									</r:when>
									<r:when test="${search.htmlType eq 'NUMBERRANGE'}">
										<input id="search_start_${search.id}" name="search_start_${search.id}" label="${search.label}" style="width:84px;" class="mini-textbox" onkeyup='doQuickQuery(event)'/>
										 -> 
										<input id="search_end_${search.id}" name="search_end_${search.id}" label="${search.label}"  style="width:84px;" class="mini-textbox" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:otherwise>
										<input id="search_${search.id}" name="search_${search.id}" value="${(empty search.defaultValue)?'':search.defaultValue}" />
									</r:otherwise>
								</r:choose>
							</td>
							
							<r:if test="${(status.index+1)%columnCount eq 0}">
								</tr>
							</r:if>
							
							<!-- 最后补空行和按钮 -->
							<r:if test="${status.last}">
								<r:if test="${!((status.index+1)%columnCount eq 0)}">
									</tr>
								</r:if>
								
							</r:if>
						</r:forEach>
					</table>
					<div id="div_filter_button_${id}" style="padding-top:1px;;text-align: center;overflow: hidden;">
						<a href="javascript:void(0);" class="btn btn-primary" id="queryOnlick" onclick="doSearch(queryObject_${id});"><span>查询</span></a>
						<a href="javascript:void(0);" class="btn btn-primary" onclick="doClear(queryObject_${id},'filter');"><span>清空</span></a>
					</div>
				</center>
		</div>
	</r:if>
	<r:set var="columnCount" value="3" />
	<r:if test="${fn:length(filters) > 0}">
		<div id="filter_${id}" style="display:none" class="x-panel-table-div-query-area">
			<div id="filtercontainer_${id}" class="x-panel-table-div-query-area-auto-config">
				<table style="width:100%;border:1px solid #fdfdfd;text-align: left;">				
					<r:forEach items="${filters}" var="filter" varStatus="status">
						<r:if test="${status.index%columnCount eq 0}">
							<tr>
						</r:if>
							<r:if test="${sessionScope['login_userlanguage'].language eq 'en'}">
								<td style="">${filter.enname}</td>
							</r:if>
							<r:if test="${sessionScope['login_userlanguage'].language eq 'zh'}">
								<td style="">${filter.label}</td>
							</r:if>
							<td style="">						
								<r:choose>
									<r:when test="${filter.htmlType eq 'TEXT' || empty filter.htmlType}">
										<input class="mini-textbox" id="filter_${filter.id}" name="filter_${filter.id}" value="${(empty filter.defaultValue)?'':filter.defaultValue}" onkeyup='doQuickQuery(this,event)' />						
									</r:when>
									<r:when test="${filter.htmlType eq 'COMBOBOX'}">
										<input type="text" class="mini-combobox" id="filter_${filter.id}" name="filter_${filter.id}" />
									</r:when>
									<r:when test="${filter.htmlType eq 'DATE'}">
										<input id="filter_${filter.id}" name="filter_${filter.id}" label="${filter.label}" class="mini-datepicker" allowInput="false" type="text" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:when test="${filter.htmlType eq 'DATERANGE'}">
										<input name="filter_start_${filter.id}" id="filter_start_${filter.id}"  label="${filter.label}"  class="mini-datepicker" allowInput="false" type="text" onkeyup='doQuickQuery(this,event)' style="width: 100px;"/>
										 -> 
										<input name="filter_end_${filter.id}" id="filter_end_${filter.id}"  label="${filter.label}"  class="mini-datepicker"  allowInput="false" type="text" onkeyup='doQuickQuery(this,event)' style="width: 100px;"/>		
									</r:when>
									<r:when test="${filter.htmlType eq 'NUMBERRANGE'}">
										<input name="filter_start_${filter.id}" id="filter_start_${filter.id}"  label="${filter.label}" style="width:84px;" class="mini-textbox" type="text" onkeyup='doQuickQuery(event)'/>
										 -> 
										<input name="filter_end_${filter.id}" id="filter_end_${filter.id}"  label="${filter.label}"  style="width:84px;" class="mini-textbox" type="text" onkeyup='doQuickQuery(event)'/>
									</r:when>
									<r:otherwise>
										<input class="mini-textbox" id="search_${filter.id}" name="filter_${filter.id}" value="${(empty filter.defaultValue)?'':filter.defaultValue}" onkeyup='doQuickQuery(event)'/>
									</r:otherwise>
								</r:choose>
							</td>
							<r:if test="${(status.index+1)%columnCount eq 0}">
								</tr>
							</r:if>
							
							<!-- 最后补空行和按钮 -->
							<r:if test="${status.last}">
								
								<r:if test="${((status.index+1)%columnCount eq 0) }">
									<tr>
								</r:if>
									<td colspan="${(columnCount - (status.index+1)%columnCount) * 2}">
										<div id="div_filter_button_${id}" style="float:left;padding-top:1px;margin-left:10px;">
											<a href="javascript:void(0);" class="btn btn-primary" id="queryOnlick" onclick="doSearch(queryObject_${id});"><span><spring:message code="Search" text="查询"/></span></a>
											<a href="javascript:void(0);" class="btn btn-primary" onclick="doClear(queryObject_${id},'filter');"><span><spring:message code="Clear" text="清空"/></span></a>
										</div>
									</td>
								</tr>
							</r:if>
					</r:forEach>
				</table>
			</div>
		</div>
	</r:if>
	
	<div id="table_data_${id}" style="display:none;overflow:hidden;height: 100%"></div>
</div>
<r:if test="${empty needjs || (needjs eq '1')}">
<jsp:include page="export.jsp?reportName=${(empty reportName)?'':reportName}" flush="true" />
</body>
</html>
</r:if>
<script type="text/javascript">
		jQuery(function(){
			var divHeight = jQuery('#filter_${id}').height();
			var divTitle = jQuery('#table_title_${id}').height();
			$("#table_data_${id}").height(pageHeight - divHeight-divTitle-10);
			$("#table_data_${id}").width(pageWidth);
		});
		
		function doQuickQuery(obj,e){
			var e = window.event || e;
			if(e && e.keyCode==13){
				doSearch(queryObject_${id}); 
			}
		}
</script>
