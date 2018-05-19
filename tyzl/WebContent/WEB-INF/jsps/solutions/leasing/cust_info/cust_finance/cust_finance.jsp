<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var custid ="<mini:param  name='id'/>";
jQuery(function() {
	if('<mini:param  name="isView"/>' == 'true'){
        //控制新增是否显示
		jQuery("#id_finance_tool1").hide();
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'cust_finance',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			pageSize:15,
			showPager:true,
			renderTo:'id_cust_finance',
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_financial/cust_financial_history.xml',
			params:{'custid':custid},
			columns:[ 
				    {type:'indexcolumn'},
				    {type:'checkcolumn'},
				    {field:'id',header:'id',visible : false},
				    {field:'filename',header:'附件名称',headerAlign:'center'},
				    {field:'filesize',header:'文件大小',headerAlign:'center',renderer : function(e){
						var id = e.record.id;
						var fileSizeFlag = "Kb";
						var fileSize = e.record.filesize;
						if (1024 * 1024 > fileSize) {
							fileSize = decimal(fileSize / 1024, 2);
						} else {
							fileSize = decimal(fileSize / 1024 / 1024, 2);
							fileSizeFlag = "Mb";
						}
					    return fileSize+fileSizeFlag; 
					   }},
				    {field:'realname',header:'上传人',headerAlign:'center'},
				    {field:'createdate',header:'上传时间',dateFormat : "yyyy-MM-dd HH:mm:ss"},
				    {field:'contractstatusname',header:'操作',headerAlign:'center',
				    	renderer : function(e){
							var id = e.record.id;
						    var base = "<a href='#' onclick='fileOperator.downFile(\"" + id + "\")'>下载</a>";
						    var base2 = "<a href='#' onclick='dropFinanceReport(\"" + id + "\")'>删除</a>";
						    return base+"&nbsp;&nbsp;"+base2; 
						   }}
				    ]
		});
	});
});
function importFinanceReport(){
	var uputil=new uploadUtil({
		url:'/leasing/finance/addFinance.action',
    	title:'财务报表',
    	jscallback:'importFinanceCall',
    	parames:{custid:custid}
	});
	uputil.createFileAndShow();
}
function importFinanceCall(message){
    mini.alert(message);
	mini.unmask(document.body);
	mini.get("id_uploadfile").hide();
	window.location.reload(); 
}
function tempdownload(){
	var templateno='F-201412013';
    downLoadFileTemplate(templateno);
}
function dropFinanceReport(id){
	var url="/leasing/template/dropCreateFile.action";
	var param=[];
	param["ids"]=id+"";
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			mini.alert(message);
			window.location.reload(); 
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
		params:param
	});
	
}
</script>

<div class="mini-toolbar" id="id_finance_tool1">
		<a class="mini-button" iconCls="icon-upload" onclick="importFinanceReport">新增</a><span class="separator"></span>
		<!-- <a text-aligh="right"class="mini-button" iconCls="icon-download" onclick="tempdownload()">模板下载</a> -->
</div>
<div class="mini-table"style="width: 100%; height: 100%; padding: 5px auto auto 5px;" id="id_cust_finance">

</div>