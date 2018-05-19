<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "cust_report",
			renderTo: "id_table_cust_report",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "生成客户巡视报告",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成客户巡视报告',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							var params=getParams();
						     var cdates=miniTable.getData();
						     for(var i=0;i<cdates.length;i++){
				                    if(cdates[i].filename=="生成客户巡视报告.docx"){
				                   	 mini.alert("您已经生成客户巡视报告！");
				                    }
							    }	
							var fileTeplate=new fileTemplateConfig({
								
								
								templateno : 'F-201610005',
								tableid : 'cust_report',
								modelname : '生成客户巡视报告',
								returntype : 'listtocurpage',
								parames : params
							});
							fileTeplate.createFile();
							//saveCallBack();
						}
			        }
			        ],
			 removeOperCallBack: function(miniTable,rowDatas){
						dropCreateFile(rowDatas);
						return true;
					},        
		    xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
			params : {
						flowUnid:flowUnid,
						modelname : '生成客户巡视报告'
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
function getParams(){
	var params={};
	params["flowunid"]=flowUnid;
	params["cust_info.name1"]="111111";
	params["cust_info.name2"]="生成客户巡视报告";
	//多行
	//params['json_fund_rent_plan_str']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());
	return params;
}
function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="生成客户巡视报告.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
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
//删除生成的文件。把文件注为无效
function dropCreateFile(rowDatas){
	var plandata = rowDatas;
	var ids=[];
	for(var i=0;i<plandata.length;i++){
		ids.push(plandata[i].id);
	}
    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除合同 请稍等...'});
	var url="/leasing/template/dropCreateFile.action";
	var param=[];
	param["ids"]=ids+"";
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			mini.alert(message);
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
		params:param
	});
}
</script>
<div id="id_table_cust_report"></div>






