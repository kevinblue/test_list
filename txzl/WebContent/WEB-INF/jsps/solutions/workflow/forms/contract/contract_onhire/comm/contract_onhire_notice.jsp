<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "onhire_report",
			renderTo: "id_table_onhire_report",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成租金支付表',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {															
						     var cdates=miniTable.getData();
						     for(var i=0;i<cdates.length;i++){
						    	 mini.alert("您已生成一份，无需重复生成");
						    	 return;
							    }	
							var params=getParams();
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams:getAttachmentParams("root.FileType.LeasFile.01","${currentProcessInstanceDBID}"),
								templateno : 'F-201603005',
								tableid : 'onhire_report',
								modelname : '租金支付表',
								returntype : 'listtocurpage',
								parames : params
							});
							fileTeplate.createFile();
							saveCallBack();
							loadcustomworkflowattachment();
						}
			        },'-',{
			    		html : '上传',
			    		plain : true,
			    		iconCls : 'icon-add',
			    		handler : function(miniTable, buttonText) {
			    			uploadFile();}
			    	}
			        ],
			//data: JsonUtil.decode('${empty json_onhire_report_str ? "[]" : json_onhire_report_str }'),
			xmlFileName : '/eleasing/workflow/contract/contract_onhire/contract_onhire_file_list.xml',
			params : {
				flowUnid:flowUnid,
				modelname : '租金支付表'
			},
			updateOperCallBack: function(miniTable,formData){},
			removeOperCallBack: function(miniTable,rowDatas){
				dropCreateFile(rowDatas);
				return true;
			},
			/* operValidate:function(miniTable, rowDatas,flag){
				  if(flag=="remove"){
				   for(j=0;j<rowDatas.length;j++){
					   if(rowDatas[j].filename!="起租通知书_账户变更版.docx"||rowDatas[j].filename!="起租通知书_常规版.docx"){
	                  	 mini.alert("您只能删除客户起租通知书！");
	                        return false;
	                   }
				   }}
			       return true;
		     }, */
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
				{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function getParams(){
	var  industrytype = mini.get("rawValue_contract_info.industrytype").getValue();
	var params={};
	params["flowunid"]=flowUnid;
	params["contract_id"]="${requestScope['contract_info.id']}";
	params['common_field.custname'] = "${requestScope['rawValue_contract_info.custname']}";
	params['common_field.contractid'] = mini.get("contract_id").getValue();
	//合同编号
	params['common.contractnumber'] = mini.get("contract_number").getValue();
	//附表编号
	var str = mini.get("contract_number").getValue();
	var s = str.substring(0,str.length-6);
	var t = s+"ZLFB";
	params['common.addendum'] = t;
	//融资额，资金测算本金
	params['common.cleanleasemoney'] = mini.get("cleanleasemoney").getValue();
	//租期，就是租赁期限（月）/12
	//params['common.leaseterm']=Math.ceil((mini.get("leaseterm").getValue())/12);
	//承租保证金
	params['common.cautionmoney']=mini.get("cautionmoney").getValue();
	//年还租次数
	var incomenumberyear =mini.get("incomenumberyear").getValue();
	params['common.incomenumberyear'];
	if(incomenumberyear == "income_1"){
		params['common.incomenumberyear']="12";
	}else if(incomenumberyear == "income_2"){
		params['common.incomenumberyear']="6";
	}else if(incomenumberyear == "income_3"){
		params['common.incomenumberyear']="4";
	}else if(incomenumberyear == "income_6"){
		params['common.incomenumberyear']="2";
	}else if(incomenumberyear == "income_12"){
		params['common.incomenumberyear']="1";
	}
	var rentplan = mini.get("fund_rent_plan_frame").getData();
	if(mini.get("leaseterm").getValue()==0){
		params['common.leaseterm']=rentplan.length/parseInt(params['common.incomenumberyear']);
	}else{
		params['common.leaseterm']=Math.ceil((mini.get("leaseterm").getValue())/12);
	}
	//租赁手续费
	params['common.handlingchargemoney']=mini.get("handlingchargemoney").getValue();
	//起租日
	params['common.startdate']=(mini.get("startdate").getValue()).format("yyyy年MM月dd日");
	//起始日期	
	params['common.createdate'] = new Date().format("yyyy年MM月dd日");	
	//租金表
	params['rent_list']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());
//	rentlist,plandate,rent,corpusbusiness,interestbusiness
	params['common_field.contracttype'] = "";
	 if(mini.get("rawValue_contract_info.leasform")=='直租'){
		params['common_field.contracttype'] = "《融资租赁合同》";
		params['common_field.contractkind'] = "《购买合同》（编号：）约定的第一笔购买价款";
	}else{
		params['common_field.contracttype'] = "《融资租赁合同（售后回租）》";
		params['common_field.contractkind'] = "租赁物件转让价格";
	} 
	params['common_field.onhiredate'] = dateSformat(mini.get("startdate").getText());
	//mini.alert(mini.get("startdate").getText()+","+mini.get("leaseterm").getValue());
	params['common_field.onhiredate1'] = mini.get("startdate").getText();
	params['common_field.onhireleaseterm'] = mini.get("leaseterm").getValue();
	//params['common_field.leasetermstart'] = dateSformat(mini.get("firstplandate").getText());
	//params['common_field.leasetermend'] = dateSformat(mini.get("firstplandate").getText());
	params['common_field.incomenumberyear']="见租金支付表";
	//params['common_field.incomenumberyear'] = mini.get("incomenumberyear").getText();
	params['common_field.rentsum'] = mini.get("fund_rent_plan_frame").getSummaryCellEl("rent").innerHTML;
	params['common_field.rentsum2'] = "人民币"+DX(Number(mini.get("fund_rent_plan_frame").getSummaryCellEl("rent").innerHTML.replace(/,/g,'')));
	params['common_field.rentsumChinerse'] = "";
	var rentTable = mini.get("fund_rent_plan_frame").getData();
	for(var i = 0;i<rentTable.length;i++ ){
		var row = rentTable[i];
		if(row.rentlist=='1'){
			params['common_field.frentdate'] = dateSformat(row.plandate);
			params['common_field.frentamount'] = tenwa.money2Thousand(row.rent);
			params['common_field.frentamountdx']="人民币"+DX(row.rent);
			params['common_field.frentlist'] = "第一期";
		}
		if(row.rentlist == (rentTable.length)){
			params['common_field.frentdateend'] = dateSformat(row.plandate);
			params['common_field.frentamountend'] = tenwa.money2Thousand(row.rent);
			params['common_field.frentlistend'] = "最后一期";
		}
	}
	
	params['common_field.nominalprice'] = mini.get("nominalprice").getValue();
	params['common_field.createdate'] = new Date().format("yyyy年MM月dd日");
	
	//多行
	params['json_fund_rent_plan_str']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());
	return params;
}
function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ basefilename+"\")'>"+"预览</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
	  return base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base2;
	}
	var id = temp.record.id;
	var basefilename = temp.record.filename;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ basefilename+"\")'>"+"预览</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
  return base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base2;
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
	param["isAttachment"]=true;//连带级联操作删除自定义方法
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			loadcustomworkflowattachment();
			mini.alert(message);
		},
		async:false,
		failure:function(res){
			mini.unmask(document.body);
		},
		params:param
	});
}
/*处理上传的模板*/
function uploadFile() {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : '租金支付表',
			isAttachment:true,
			attachmentParams:getAttachmentParams('root.FileType.LeasFile.01','${currentProcessInstanceDBID}'),
			parames : {
				flowUnid : flowUnid,
				//filekey : filekey　　　//项目ＩＤ
			},
			jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("onhire_report").reload();
	loadcustomworkflowattachment();
}
</script>
<div id="id_table_onhire_report"></div>