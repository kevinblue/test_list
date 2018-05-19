<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('<mini:param name="isView"/>' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "credit_report",
			renderTo: "id_table_credit_report",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: true,
			title: "生成客户信审报告",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成客户信审报告',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							var params={};
						     params["flowunid"]=flowUnid;
						     var cdates=miniTable.getData();
						     for(var i=0;i<cdates.length;i++){
				                    if(cdates[i].filename=="客户信审报告.docx"){
				                   	 mini.alert("您已经生成信审报告！");
				                         //return false;
				                    }
							    }	
							var fileTeplate=new fileTemplateConfig({
								templateno : 'F-201412161',
								tableid : 'credit_report',
								modelname : '信审报告',
								returntype : 'listtocurpage',
								parames : params
							});
							fileTeplate.createFile();
							saveCallBack();
						}
			        }
			        ],
			data: JsonUtil.decode('<mini:param  name="id_json_credit_report_str" defaultValue="[]"/>'),
			updateOperCallBack: function(miniTable,formData){
			},
			operValidate:function(miniTable, rowDatas,flag){
				  if(flag=="remove"){
				   for(j=0;j<rowDatas.length;j++){
					   if(rowDatas[j].filename!="客户信审报告.docx"){
	                  	 mini.alert("您只能删除客户信审报告！");
	                        return false;
	                   }
				   }}
			       return true;
		     },
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间'},
				{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function showOperator(temp,cfalg){
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="客户信审报告.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}
}
function editoverdue(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
	var currentPageClientHeight =  document.documentElement.clientHeight;
	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"2"});
}
function downloadFile(Id){
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
} 
function  readCreateWord(id){
	var currentPageClientWidth  =  document.documentElement.clientWidth;
 	var currentPageClientHeight =  document.documentElement.clientHeight;
 	openFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:"1"});
}
</script>
<div id="id_table_credit_report"></div>






