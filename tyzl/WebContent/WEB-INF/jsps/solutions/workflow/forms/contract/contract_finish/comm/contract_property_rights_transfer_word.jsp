<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script>
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false};
	tenwa.createTable({
		id: "contract_property_rights_transfer",
		renderTo: "id_table_contract_property_rights_transfer",
		width: '100%',
		height: 300,
		lazyLoad: true,
		title: "",
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['remove'],
		removeOperCallBack: function(miniTable){
		},
		data: JsonUtil.decode('<mini:param  name="json_contract_property_rights_transfer_str" defaultValue="[]"/>'),
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
	//window.open (getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi?id='+id,'newwindow',"resizable=true,top=0,left=0") ;
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
</script>