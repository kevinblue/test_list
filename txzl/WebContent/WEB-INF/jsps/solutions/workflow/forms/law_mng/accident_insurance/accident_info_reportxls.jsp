<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "accident_info",
			renderTo: "id_accident_info",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: true,
			title: "生成出险报告",
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成出险报告',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							var params={};
						     params["flowunid"]=flowUnid;	
						     params["condition.department"]="${requestScope['rawValue_contract_info.department'] }";	
						     params["condition.projmanage"]="${requestScope['rawValue_contract_info.projmanage'] }";	
						     params["condition.projassist"]="${requestScope['rawValue_contract_info.projassist'] }";	
						     params["condition.projectname"]="${requestScope['contract_info.projectname']}";	
						     
						     params["condition.currentoverage"]=mini.getbyName("contract_info.currentoverage").getValue();	
						     params["condition.curcorpusoverage"]=mini.getbyName("contract_info.curcorpusoverage").getValue();	
						     params["condition.businesstype"]="${requestScope['rawValue_contract_info.businesstype']}";	
						     params["condition.guarantee"]=mini.getbyName("contract_info.guarantee").getValue();
						     
						     params["condition.otherguarantee"]=mini.getbyName("contract_info.otherguarantee").getValue();
						     params["condition.startdate"]=mini.getbyName("contract_info.startdate").getValue();	
						     params["condition.allrent"]=mini.getbyName("contract_info.allrent").getValue();	
						     params["condition.handate"]=mini.getbyName("contract_info.handate").getValue();
						     
						     params["condition.overduedate"]=mini.getbyName("contract_info.overduedate").getValue();	
						     params["condition.overduemoney"]=mini.getbyName("contract_info.overduemoney").getValue();	
						     params["condition.accidentres"]=mini.getbyName("accident_info.accidentres").getValue();	
						     params["condition.factor"]=mini.getbyName("accident_info.factor").getValue();	
						     
						     params["condition.guarantee"]=mini.getbyName("accident_info.guarantee").getValue();	
						     params["condition.equipinfo"]=mini.getbyName("accident_info.equipinfo").getValue();	
						     params["condition.dyinfo"]=mini.getbyName("accident_info.dyinfo").getValue();	
						     params["condition.otherinfo"]=mini.getbyName("accident_info.otherinfo").getValue();
						     
						     params["condition.planadvice"]=mini.getbyName("accident_info.planadvice").getValue();	
						     params["condition.thinghiscontent"]=mini.getbyName("accident_info.thinghiscontent").getValue();	
						     params["condition.thinghisdate"]=mini.getbyName("accident_info.thinghisdate").getValue();	
						     /* params["condition.thingcontent"]=mini.getbyName("accident_info.thingcontent").getValue(); */
						     
						     params["condition.thingother"]=mini.getbyName("accident_info.thingother").getValue();	
						     params["condition.assetproj"]=mini.getbyName("accident_info.assetproj").getValue();	
						     params["condition.reportdate"]=mini.getbyName("accident_info.reportdate").getValue();	
						     var cdates=miniTable.getData();
						     for(var i=0;i<cdates.length;i++){
				                    if(cdates[i].filename=="客户尽调报告.docx"){
				                   	 mini.alert("您已经生成客户尽调报告！");
				                         //return false;
				                    }
							    }	
							var fileTeplate=new fileTemplateConfig({
								templateno : 'F-201603002',
								tableid : 'accident_info',
								modelname : '出险报告',
								returntype : 'listtocurpage',
								parames : params
							});
							fileTeplate.createFile();
							saveCallBack();
						}
			        }
			        ],
			data: JsonUtil.decode('${empty json_accident_info_str ? "[]" : json_accident_info_str }'),
			updateOperCallBack: function(miniTable,formData){
			},
			operValidate:function(miniTable, rowDatas,flag){
				  if(flag=="remove"){
				   }
			       return true;
		     },
			columns: [
				{type: 'indexcolumn',width:9},
				{type: 'checkcolumn',width:9},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间',dateFormat:"yyyy-MM-dd"},
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
	if(filename=="客户尽调报告.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3;
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
<div id="id_accident_info"></div>