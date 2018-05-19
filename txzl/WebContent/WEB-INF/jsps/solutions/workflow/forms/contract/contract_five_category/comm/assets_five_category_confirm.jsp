<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
var contractid = "${requestScope.contract_id}";
var generatededitWindow='';
var financedate='';
var custid='';
var guaranteename='';
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "asset_classify",
			renderTo: "id_table_asset_classify",
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
						html : '生成资产分类认定表',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {	
							var params = {};
							params.id = contractid;
							var value;
							var name;
							var generatedform = new mini.Form("generatedform");
							generatededitWindow = mini.get("generatededitWindow");
							
							tenwa.createQueryInput({
								id:'id_windfarmreportYear_list',
								label:'主要担保人',
								windowWidth: 450,
								windowHeight: 700,
								textField: 'name',
								valueField: 'value',
								params: {
										contractid:contractid,
									    xmlFileName:'/eleasing/workflow/proj/proj_credit/guarantee_list.xml'
								}
							});
							$.ajax({
								
								url : '${pageContext.request.contextPath}/acl/getGuarantor.acl',
								method : 'POST',
								async : false,
								data : params,
								success:function(res){
									var arr = res.split("+");
									value = arr[0];
									name = arr[1];
									mini.get("id_windfarmreportYear_list").setValue(name);
									mini.get("id_windfarmreportYear_list").setText(value);
								},
								failure : function(res) {
									
								}
								
							});
							
							generatededitWindow.show();
							
							 
						    
						/* 	var params=getParams();
						     var cdates=miniTable.getData();
					       for(var i=0;i<cdates.length;i++){ 
				                   		mini.alert("您已生成一份，无需重复生成");            	 
							    }
							var fileTeplate=new fileTemplateConfig({
								 isAttachment : true,
								attachmentParams: {
									module:'WorkflowAttchmentFiles',
									jbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',
									attachmentFileDictId : 'ProjectQuotationScheme.01',//项目报价方案
									identifierOne:window.assignAttachmentKeyOne||"${currentProcessInstanceDBID}",
									identifierTwo:window.assignAttachmentKeyTwo||jQuery("#id_currentHistoryTaskInfo_keyOne").val(),
									identifierThree:window.assignAttachmentKeyThree||jQuery("#id_currentHistoryTaskInfo_keyTwo").val(),
									identifierFour:window.assignAttachmentKeyFour||jQuery("#id_currentHistoryTaskInfo_keyThree").val(),
									identifierFive:window.assignAttachmentKeyFive||jQuery("#id_currentHistoryTaskInfo_keyFour").val(),
									identifierSix:window.assignAttachmentKeySix||jQuery("#id_currentHistoryTaskInfo_keyFive").val(),
									identifierSeven:window.assignAttachmentKeySeven||jQuery("#id_currentHistoryTaskInfo_keySix").val(),
									identifierEight:window.assignAttachmentKeyEight||jQuery("#id_currentHistoryTaskInfo_keySeven").val(),
									identifierNine:window.assignAttachmentKeyNine||jQuery("#id_currentHistoryTaskInfo_keyEight").val(),
									identifierTen:window.assignAttachmentKeyTen||jQuery("#id_currentHistoryTaskInfo_keyNine").val()
								},  
								templateno : 'F-201703007',
								tableid : 'asset_classify',
								modelname : '资产分类认定表',
								returntype : 'listtocurpage',
								parames : params
							});
							if(cdates.length==0){
							fileTeplate.createFile();
							mini.alert("您已成功生成资产分类认定表");
							}
							saveCallBack();
						*/
						} 
			        },'-',{
			    		html : '上传',
			    		plain : true,
			    		iconCls : 'icon-add',
			    		handler : function(miniTable, buttonText) {
			    			uploadFile();}
			    	}
			        ],
			//data: JsonUtil.decode('${empty json_asset_classify_str ? "[]" : json_asset_classify_str }'),
			xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
		 	params : {
				flowUnid:flowUnid,
				modelname : '资产分类认定表'
			},
			updateOperCallBack: function(miniTable,formData){
			},
			removeOperCallBack: function(miniTable,rowDatas){
				dropCreateFile(rowDatas);
				return true;
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},
				{field: '', header: '操作',renderer:function(e){
				var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function getParams(){
	var params={};
	
	params["flowunid"]=flowUnid;
	//1.11承租人名称
	//custname,projectname,contractid
	//'{label.projectname}'
	params['label.custname'] = mini.getbyName("contract_info.custid").getText();
	params['label.custid'] = mini.getbyName("contract_info.custid").getValue();
	//1.2项目名称
	params['label.projectname'] = mini.getbyName("contract_info.projectname").getValue();
	params['project.projectname'] = mini.getbyName("contract_info.projectname").getValue();
	//1.3租赁合同编号
	params['label.contractid'] = mini.getbyName("contract_info.contractnumber").getValue();
	//获取融资金额的参数
	params['cmoney.contractid'] = "${param.contract_id}";
	//合同id--作为查询抵押物信息的参数
	params['guaranty.id'] = "${param.contract_id}";
	//财务日期--作为查财务资料的条件
	 date = mini.formatDate(financedate, "yyyy/MM/dd HH:mm:ss"),
	 lastyear=mini.formatDate(financedate, "yyyy"),
	 params['quarter.year'] = lastyear ;
	 //year,quarter
	 lastdate= mini.formatDate(financedate, "M"),
	 num=Number(lastdate );
	// mini.alert(jQuery.type( Number(lastdate )) );
	 if(num <= 12 && num >=10){
		num=lastyear+'-12-31';
		params['lastdate.num'] =num;
		params['lastdate.numstart'] =lastyear+'-10-01';
		numt=lastyear-1+'-12-31';
		params['date.numt']=numt;
		params['date.numtstart']=lastyear-1+'-10-01';
		numy=lastyear-1+'-12-31';
		params['ydate.numy']=numy;
		params['ydate.numystart']=lastyear-1+'-10-01';
		params['quarter.quarter'] = '4' ;
		
	}
	 if(num>=7 && num <=9){
		num=lastyear+'-09-30';
		params['lastdate.num'] =num;
		params['lastdate.numstart'] =lastyear+'-07-01';
		numt=lastyear-1+'-09-30';
		params['date.numt']=numt;
		params['date.numtstart']=lastyear-1+'-07-01';
		numy=lastyear-1+'-12-31';
		params['ydate.numy']=numy;
		params['ydate.numystart']=lastyear-1+'-10-01';
		params['quarter.quarter'] = '3' ;
	 }
	 if(num<=6 && num >=4){
	   num=lastyear+'-06-30';
	   params['lastdate.num'] =num;
	   params['lastdate.numstart'] =lastyear+'-04-01';
	   numt=lastyear-1+'-06-30';
	   params['date.numt']=numt;
	   params['date.numtstart']=lastyear-1+'-04-01';
	   numy=lastyear-1+'-12-31';
	   params['ydate.numy']=numy;
	   params['ydate.numystart']=lastyear-1+'-10-01';
	   params['quarter.quarter'] = '2' ;
	 }
	 if(num<=3 && num >=1){
	   num=lastyear+'-03-31';
	   params['lastdate.num'] =num;
	   params['lastdate.numstart'] =lastyear+'-01-01';
	   numt=lastyear-1+'-03-31';
	   params['date.numt']=numt;
	   params['date.numtstart']=lastyear-1+'-01-01';
	   numy=lastyear-1+'-12-31';
	   params['ydate.numy']=numy;
	   params['ydate.numystart']=lastyear-1+'-10-01';
	   params['quarter.quarter'] = '1' ;
	 }
	 params['finance.financedate']=date;
	// mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
	// mini.alert(date);
    //获取客户number
    params['finance.custnumber']=mini.getbyName("contract_info.custnumber").getValue();
    // mini.alert(mini.getbyName("contract_info.custnumber").getValue());
    //客户id--作为参数过滤得到担保合同
    params['guarantee.custid']=custid;
    params['guarantee.financedate']=date;
    //担保人
     params['guaranteename.guaranteename']=guaranteename;
    //作为查询已收回金额,融资余额的条件
     params['corpus.contractid']="${param.contract_id}";
    //对资产有重大不利影响的因素、同类资产处置情况,租赁资产分类结果,租赁资产分类理由,租赁资产分类展望及理由,建议租赁资产处置措施 
   //badinfluenceofasset,sameasset,contractfive_business,classfyreason,expectreason,assetmeasurement
    //对资产有重大不利影响的因素
    params['classfy.badinfluenceofasset']=mini.get("five_category.badinfluenceofasset").getValue();
	//同类资产处置情况
     params['classfy.sameasset']=mini.get("five_category.treatmentofsameasset").getValue();
    //租赁资产分类结果
    params['classfy.contractfive_business']=mini.get("five_category.contractfive_business").getText();
    //租赁资产分类理由
    params['classfy.classfyreason']=mini.get("five_category.classfyreason").getValue();
    //租赁资产分类展望及理由
    params['classfy.expectreason']=mini.get("five_category.expectreason").getValue();
   //建议租赁资产处置措施 
    params['classfy.assetmeasurement']=mini.get("five_category.assetmeasurement").getValue();

    return params;
}
/* function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
}  */

function showOperator(temp,cfalg){
	if('${param.isView}' == 'true'||isViewHistoryTask==true){
		var id = temp.record.id;
		var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
		var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
		
		var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
		var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
		var tempright="2"
		var filename=temp.record.filename;
		if(filename=="资产分类认定表.docx"&&cfalg){
		    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base3+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}else{
			return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
		}
	}
	var id = temp.record.id;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	var filename=temp.record.filename;
	if(filename=="资产分类认定表.docx"&&cfalg){
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
	param["isAttachment"]=true;//连带级联操作删除自定义方法
	ajaxRequest({
		url:getRootPath()+url,
		method:'POST',
		success:function(rs){
		var message= rs.responseText;
			message=message.replace(/(^\s+)|(\s+$)/g, ""); 
			mini.unmask(document.body);
			if (mini.get("id_customworkflowattachment")) {
				mini.get("id_customworkflowattachment").reload();
				mini.get("id_workflowhisAttachment").reload();
			}
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
			modelname : '资产分类认定表',
			isAttachment:true,
			attachmentParams:getAttachmentParams('root.FileType11.01','${currentProcessInstanceDBID}'),
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
	mini.get("asset_classify").reload();
	loadcustomworkflowattachment();
}
function printassetdata(){
	
	financedate= mini.get("financedate").getValue();
	custid=mini.get("id_windfarmreportYear_list").getValue();
	guaranteename=mini.get("id_windfarmreportYear_list").getText();	
	var params=getParams();
   
     
  /*  var cdates=miniTable.getData();
   for(var i=0;i<cdates.length;i++){ 
       mini.alert("您已生成一份，无需重复生成");            	 
	    } */
	var fileTeplate=new fileTemplateConfig({
	    isAttachment : true,
	    attachmentParams:getAttachmentParams("root.FileType11.01","${currentProcessInstanceDBID}"),
		templateno : 'F-201703007',
		tableid : 'asset_classify',
		modelname : '资产分类认定表',
		returntype : 'listtocurpage',
		parames : params
	});
	    fileTeplate.createFile();
	    generatededitWindow.hide();
	/* if(cdates.length==0){
	fileTeplate.createFile();
	mini.alert("您已成功生成资产分类认定表");
	} */
	saveCallBack();
	loadcustomworkflowattachment();
}

</script>
<div id="generatededitWindow" class="mini-window" title="选择财务数据日期" style="width:323px;height:150px;"   showModal="true" allowResize="true" allowDrag="true">
        <div id="generatedform">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:80px;">发送日期：</td>
                    <td >
                        <input id="financedate" name="financedate"  class="mini-datepicker miniext-form-fit" allowInput="true"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:80px;">主要担保人：</td>
                    <td >
                       <input id="id_windfarmreportYear_list" name="dunninginfo"  class="mini-buttonedit mini-queryinput"  />
                    </td> 
                </tr>
                <tr></tr> 
                <tr>                
                 <td></td>                 
                    <td style='margin:auto;'> 
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="mini-button "  onclick="printassetdata">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                   </td> 
                </tr>   
            </table>
        </div>
	</div>
<div id="id_table_asset_classify"></div>