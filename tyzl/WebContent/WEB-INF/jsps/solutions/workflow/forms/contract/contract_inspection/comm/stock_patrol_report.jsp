<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script>
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){showTools = false;}
	tenwa.createTable({
		id: "stock_patrol_report",
		renderTo: "id_table_stock_patrol_report",
		width: '100%',
		height: 300,
		lazyLoad: false,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: [{
			html : '生成存量资产检查报告',
			plain : true,
			iconCls : 'icon-ok',
			handler : function(miniTable, buttonText) {	
			var tempids = "F-201501017";
			var params={};
			params["flowunid"] = flowUnid;
			getPageData(params);
			var fileTeplate=new fileTemplateConfig({
				templateno : 'F-201501014',
				tableid : 'stock_patrol_report',
				modelname : '存量资产检查报告',
				returntype : 'listtocurpage',
				parames : params
			});
			fileTeplate.createFile();
			saveCallBack();
			}
		}, '-','remove'],
		removeOperCallBack: function(miniTable){
		},
		data: JsonUtil.decode('<mini:param  name="json_stock_patrol_report_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'filename', header: '文件名称'},
			{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
			var temp=e;
			return showOperator(temp,cfalg);}}
		]
	});
});

function  showOperator(temp,cfalg){
	var id = temp.record.id;   
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
		if(cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
		}else{
			return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
		}
}

function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
 	
}
function  printCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"3"});
 	
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function getPageData(params) {

}
</script>
<div id = "id_table_stock_patrol_report"></div>