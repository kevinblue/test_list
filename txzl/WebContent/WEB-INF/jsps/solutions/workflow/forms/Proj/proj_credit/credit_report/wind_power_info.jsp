<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
var projid="${requestScope['proj_info.developid']}";

</script>

<div class="mini-tabs" activeIndex="0"   style="width: 100%" id="tabDetailsBase"  onactivechanged="changTab">
		 	<div title="项目批复文件" name="proj_approvaldoc" iconCls="icon-cut">
				<jsp:include
					page="../../proj_develop_list/proj_wind_common/Proj_App_Doc.jsp"></jsp:include>
			</div> 
			 <div title="风资源分析" name="windResourceReport" iconCls="icon-cut">
				<jsp:include
					page="../../proj_develop_list/proj_wind_common/proj_wind_resource.jsp"></jsp:include>
			</div>
<%-- 			<div title="项目建设进度" name="projProgress" iconCls="icon-cut">
				<jsp:include
					page="../../proj_develop_list/proj_wind_common/proj_progress.jsp"></jsp:include>
			</div> --%>
			<div title="机组选型报告" name="selectionReport" iconCls="icon-cut">
				<jsp:include
					page="../../proj_develop_list/proj_wind_common/selection_report.jsp"></jsp:include>
			</div> 
			<div title="机组选型报告相关附件" name="selection_accessories_report_list" iconCls="icon-cut">
					<jsp:include
						page="../../proj_develop_list/proj_wind_common/selection_accessories_report_list.jsp"></jsp:include>
			</div>
			</div>
<script type="text/javascript">
</script>