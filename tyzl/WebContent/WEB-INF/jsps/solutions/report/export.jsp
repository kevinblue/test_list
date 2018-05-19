<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="div_excelExport" style="display:none;height:200px">	
	<input type="hidden" name="export_reportName" id="export_reportName" vlaue="${(empty reportName)?'':reportName}" />
	<center>
	<table id="table_excelExport" style="width:90%;margin-top:5px">
		
	</table>
	<div id="id_export_message" style="display:none;text-align:center;width:90%;">导出过程中请稍后.....</div>
	<div id="table_export_btn" style="float:right;position:absolute; bottom:1px;right:10px;height:20px;margin-bottom:10px;margin-right:10px;">
		<a href="javascript:void(0);" id="id_export_button" class="btn btn-primary" onclick="doExport()"><span>导出</span></a>
		<a href="javascript:void(0);" class="btn btn-primary" onclick="javascript:closeExportTable()"><span>关闭</span></a>
	</div>
	<div style="display:none">
		<iframe id="iframe_export" src="about:blank" style="display:none">		
		</iframe>
	</div>
	
	</center>
</div>
<script type="text/javascript">
	function doExport (){		
		$("#table_excelExport>input[type='checkbox']").checked=true;
		var d = new Date();
		//var d = new Date ('2014/7/17 23:44:19');
		var day = d.getHours();
		//得到当前报表的数据量
		var infos = jQuery('.mini-pager-right').html()||"";
		if(infos==""){alert("导出之前请查询一下。");return false;}
		var pageReg = /\d+/gi;
		var nums = infos.match(pageReg);
		var num = nums[1];
		//如果数据量小于10000,随时可以下载
		if(Number(num) <= 50000){
			$("#id_export_message").show();
			$("#id_export_button").hide();
			exportExcel('${pageContext.request.contextPath}/report/exportExcel.action');
		}else if(Number(num) > 50000 && Number(num) <=100000){
			//在上班期间不能下载
			if((day>=8 && day<12)||(day>=14&&day<18)){
				alert('抱歉，数据量过大，请在非工作期间下载！');
				return;
			}else{
				$("#id_export_message").show();
				$("#id_export_button").hide();
				exportExcel('${pageContext.request.contextPath}/report/exportExcel.action');
			}
		}else{
			alert('数据量过大，请过滤后下下载！');
			return;
		}
	}
	
	function closeExportTable(){
		$("#id_export_message").hide();
		$("#id_export_button").show();
		$('#div_excelExport').window('close');
	}
	function rebackInfo(){
		alert('系统繁忙中,请不要导报表');
		$("#id_export_message").hide();
		$("#id_export_button").show();
	}
</script>