<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" import="com.tenwa.kernal.utils.EnumUtil" %>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>报表管理</title>
    <!--css sheet-->
    <%@include file="/base/mini.jsp" %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.fileDownload.js"></script>
   	<style type="text/css">
		html,body{
			overflow:hidden;
		    height:100%;
		}
		.input_label_desc{
			 width:100px;
			 font-weight:BOLD;
		}		
		.container{
			float:left;
			height: 100%;
			border: 1px solid #DDD;
			border-top:0px;
			border-right:0px;
			margin-right: 5px;
	   	}	

	   	.panel-button{
	   		width:54px;	   		
	   		margin-left:20px;
	   		margin-top:-3px;
	   		border:1px solid #A9ACB5;
	   		background: #ebedf2;
	   		font-size:9pt;
	   		line-height: 22px;
	   		text-decoration: none;
	   		text-align: center;
	   		display:inline-block;
	   		cursor:pointer;
	   		vertical-align: middle;
	   		outline:none;	   		
	   		
	   	}

	   	.panel-button span{
	   		padding-left:25px;
	   		background-position:6px,50%;
	   		background-repeat:no-repeat;
	   		width:25px;
	   		color:#201F35;
	   	}

	   	.form_tab{
	   		border:1px solid #ddd;
	   	}
	  
		#report_tree_container{			
			overflow:hidden;	
			padding-left:0px;		
		}
		span.content-required 
		{
		  color:red;
		  padding-left:5px;
		}
	</style>
	<script type="text/javascript">
		var dataSourceJson;		
		var groupJson;
		var matchJson;
		var pageCondition;
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/queryDataSource.action',
			async: false,			
			dataType:'json',
			success:function(data){
				dataSourceJson = data;
			}
		});
		
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/queryGroup.action',
			async: false,			
			dataType:'json',
			success:function(data){
				groupJson = data;
			}
		});
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/queryMath.action',
			async: false,			
			dataType:'json',
			success:function(data){
				matchJson = data;
			}
		});
		
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/queryPageCondition.action',
			async: false,			
			dataType:'json',
			success:function(data){
				pageCondition = data;
			}
		});
		
		/* jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/queryTablePower.action',
			async: false,			
			dataType:'json',
			success:function(data){
				controlPersons = data;
			}
		}); */
		var comboboxDatas = {
			'dataSource': dataSourceJson,
			'sqlType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.QueryType.class)%>,
			'queryType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.QueryType.class)%>,
			'chartType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.ChartType.class)%>,
			'subChartType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.ChartType.Area,com.tenwa.report.enums.ChartType.Column,com.tenwa.report.enums.ChartType.Line)%>,
			'columnType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.ColumnDataType.class) %>,
			'usageType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.UsageType.class) %>,
			'alignType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.AlignType.class) %>,
			'contentType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.ContentType.class) %>,
			'filterRequestDataType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.FilterDataRequestType.class) %>,
			'filterDataDbType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.ColumnDataType.STRING,com.tenwa.report.enums.ColumnDataType.NUMBER,com.tenwa.report.enums.ColumnDataType.DATE)%>,
			'htmlType':<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.report.enums.HtmlType.class) %>,
			'groupType' : groupJson ,
			'matchRelatition' : matchJson,
			'pageCondition' : pageCondition,
			'yesOrNo' :[{text:'Yes',value:'1'},{text:'No',value:'0'}]
		};

		jQuery(function(){
			mini.parse();			
			seajs.use('js/report/reportconfig',function(rc){
				rc.initPage(comboboxDatas,'<mini:param name="reportMenuRoot"/>');

			})
		})
		
	</script>
		
</head>
<body>
	<!-- left -->
	<div style="width:100%;height:100%">
		<div id="report_tree_container" class="container mini-panel" title="<spring:message code='report.config.form.report' text='报表'/>">		
			<div id="report_tree"></div>
		</div>
		<div id="content_tree_container" class='container mini-panel' title="<spring:message code='report.config.form.content' text='图表'/>">		
			<div id="table_chart_tree"></div>
		</div>
		<!--表格表单-->
		<div id="table_container" style="display:none" class="container mini-panel" width="800px" title="<spring:message code='report.config.form.table' text='表格' />">
			<div id="table_toolbar" style="display:none">
				<a class="panel-button" id="btn_save_table" href="javascript:void(0);"><span class="mini-button-text mini-button-icon icon-save"><spring:message code='Save' text='保存' /></span></a>	
			</div>
			<jsp:include page="Form_Table_Config.jsp" flush="true"></jsp:include>
		</div>
		<!--静态页面表单-->		
		<div id="page_container" style="display:none" class="container mini-panel" width="800px" title="<spring:message code='report.config.form.page' text='静态页' />">
			<div id="page_toolbar" style="display:none">
				<a class="panel-button" id="btn_save_page" href="javascript:void(0);"><span class="mini-button-text mini-button-icon icon-save"><spring:message code='Save' text='保存' /></span></a>	
			</div>
			<jsp:include page="Form_Page_Config.jsp" flush="true"></jsp:include>
		</div>
		<!--图形表单-->
		<div id="chart_container" style="display:none" class="container mini-panel" width="800px" title="<spring:message code='report.config.form.chart' text='图表' />">
			<div id="chart_toolbar" style="display:none">
				<a class="panel-button" id="btn_save_chart" href="javascript:void(0)"><span class="mini-button-text mini-button-icon icon-save"><spring:message code='Save' text='保存' /></span></a>
			</div>
			<jsp:include page="Form_Chart_Config.jsp" flush="true"></jsp:include>
		</div>
	</div>
	<!--报表表单-->
	<jsp:include page="Form_Report_Config.jsp" />
	

	<!-- Context Menu Operation -->
	<div id="report_contextmenu" class="mini-contextmenu" style="display:none;width:120px;">
		<div id="report_contextmenu_add_folder" iconCls="icon-plus-w"><spring:message code='report.config.form.addFold' text='添加文件夹' /></div>
		<div id="report_contextmenu_add_report" iconCls="icon-plus-w"><spring:message code='report.config.form.addReport' text='添加报表' /></div>		
		<div id="report_contextmenu_modify" iconCls="icon-cogs-w" ><spring:message code='edit' text='修改' /></div>
		<div id="report_contextmenu_export" iconCls="icon-cogs-w"><spring:message code='report.config.form.export' text='导出' /></div>
		<div id="report_contextmenu_import" iconCls="icon-cogs-w"><spring:message code='report.config.form.import' text='导入' /></div>
		<div id="report_contextmenu_delete" iconCls="icon-minus-w"><spring:message code='remove' text='删除' /></div>
	</div>

	<div id="content_contextmenu" class="mini-contextmenu" style="display:none;width:120px;">
		<div id="content_contexmenu_add_table" iconCls="icon-plus-w"><spring:message code='report.config.form.addTable' text='添加表格报表' /></div>
		<div id="content_contextmenu_add_chart" iconCls='icon-plus-w'><spring:message code='report.config.form.addChart' text='添加图形报表' /></div>
		<div id="content_contextmenu_add_page" iconCls="icon-plus-w"><spring:message code='report.config.form.addPage' text='添加静态页面' /></div>	
		<div id="content_contextmenu_delete" iconCls="icon-plus-w"><spring:message code='remove' text='删除' /></div>
	</div>

	<div id="report_download" style="display:none">
		<form id="download_form" method="post" action="${ctx}/report/config/export.action">
			<input id="browser" name="browser" value="" />
			<input id="id" name="id" value="" />
		</form>
	</div>

	<div id="div_upload" class="mini-window" modal="true" title="<spring:message code='report.config.form.importReport' text='报表导入' />" style="width:450px">
		
		<table style="width:98%;padding-left:20px;padding-top:15px;">
			<tr id="import_fileupload">
				<td>
					<input type="file" id="input_file_upload" name="input_file_upload">
				</td>
			</tr>
			<tr>
				<div id="import_progress">
				</div>
			</tr>
			<tr>
				<td style="text-align:right;padding-right:10px">
					<a id="import_ok" class="mini-button"><span><spring:message code='Confirm' text='确定' /></span></a>
					<a id="import_cancel" class="mini-button"><span><spring:message code='Close' text='关闭' /></span></a>
				</td>
			</tr>
		</table>
		
	</div>
</body>
</html>
