<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "create_report_list",
			renderTo: "id_table_create_report_list",
			width: globalClientWidth - 20,
			height: 250,
			lazyLoad: false,
			title: "生成客户巡视报告",
			isClickLoad:false,
			remoteOper : false,
			dataFormat:"yyyy-MM-dd",
			showPager: false,
			multiSelect: false,
			showToolbar: showTools,
			tools: ['remove','-',
			        {
						html : '生成客户巡视报告',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							if(industryType=="风力发电"||industryType=="光伏发电"){
								var params=getParams();
							     var cdates=miniTable.getData();
							     for(var i=0;i<cdates.length;i++){
					                    if(cdates[i].filename=="生成客户巡视报告.docx"){
					                   	 mini.alert("您已经生成客户巡视报告！");
					                    }
								    }	
								var fileTeplate=new fileTemplateConfig({
									
									templateno : 'F-201703009',
									tableid : 'create_report_list',
									modelname : '生成客户巡视报告',
									returntype : 'listtonewpage',
									parames : params
								});
								fileTeplate.createFile();
							}else{
								var params=getParams();
							     var cdates=miniTable.getData();
							     for(var i=0;i<cdates.length;i++){
					                    if(cdates[i].filename=="生成客户巡视报告.docx"){
					                   	 mini.alert("您已经生成客户巡视报告！");
					                    }
								    }	
								var fileTeplate=new fileTemplateConfig({
									
									templateno : 'F-201703010',
									tableid : 'create_report_list',
									modelname : '生成客户巡视报告',
									returntype : 'listtonewpage',
									parames : params
								});
								fileTeplate.createFile();
							}
						}
			        }
			        ],
			removeOperCallBack: function(miniTable,rowDatas){
						dropCreateFile(rowDatas);
						return true;
					},  
			xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
			params :{
					flowUnid:flowUnid,
					modelname:'生成客户巡视报告'
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'filename', header: '文件名称'},
				{field: 'createdate', header: '文件生成时间'},
				{field: 'createdate', header: '操作',renderer:function(e){ var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function getParams(){
	var params={};
	//基本信息部分
	params["baseinfo.cust_name"] =mini.get("cust_name").getValue();//承租人名称
	params["baseinfo.project_name"] =mini.get("project_name").getValue();//项目名称
	params["baseinfo.contract_number"] =mini.get("contract_number").getValue();//租赁合同编号
	params["baseinfo.lease_type"] =mini.get("lease_type").getValue();//租赁种类
	//params["project_name"] =$("#project_name").val();//租赁物
	params["baseinfo.lease_money"] =mini.get("lease_money").getValue();//融资金额（万元）
	params["baseinfo.lease_term"] =$("#leaseterm").val();//租赁期限
	//params["baseinfo.financing_balance"] =$("#financing_balance").val();//融资余额（万元）
	params["baseinfo.rent_num"] =mini.get("rent_num").getValue();//租金总期数
	params["baseinfo.surplus"] =mini.get("surplus").getValue();//剩余期数
	params["baseinfo.avgrent"] =mini.get("avgrent").getValue();//每期平均租金
	params["baseinfo.paytype"] =$("#periodtype").val();//支付方式
	params["baseinfo.income_time"] =mini.get("income_time").getValue();//租金支付周期
	//params["project_name"] =$("#project_name").val();//首次建立租赁关系时间
	params["baseinfo.ouot_list"] =mini.get("ouot_list").getValue();//逾期期数
	params["baseinfo.out_money"] =mini.get("out_money").getValue();//逾期金额
	params["baseinfo.current_overage"] =mini.get("current_overage").getValue();//应收租赁款余额
	//巡视基本信息
	var dateobject=mini.get("contract_patrol_info.checkTime").getValue();//巡视时间
	date = mini.formatDate(dateobject, "yyyy-MM-dd"),	//日期格式装换
	params["contract_patrol_info.checkTime"] =date;
	params["contract_patrol_info.visitPensonnel"] =mini.get("contract_patrol_info.visitPensonnel").getValue();//
	params["contract_patrol_info.custPersonnel"] =mini.get("contract_patrol_info.custPersonnel").getValue();//
	params["contract_patrol_info.projectname"] =mini.get("project_name").getValue();//项目名称
	params["contract_patrol_info.thisyear"] =thisyear;
	params["contract_patrol_info.quarter"] =quarter;//季度
	//近期发电量统计
	params['power_generation_statistics']=JsonUtil.encode(mini.get('power_generation_statistics').getData());
    //发电情况统计
    params['electric_info_list']=JsonUtil.encode(mini.get('electric_info_list').getData());
	//承租人/担保人财务报表sql参数
	var quarterStart;
	var quarterEnd;
	var quarterStr;
    if(quarter=="1"){
	    quarterStart="-01-01";
		quarterEnd= "-03-31";
		quarterStr="一季度";
	}else if(quarter=="2"){
		quarterStart="-04-01";
		quarterEnd="-06-30";
		quarterStr="二季度";
	}else if(quarter=="3"){
		quarterStart="-07-01";
		quarterEnd="-09-30";
		quarterStr="三季度";
	}else{
		quarterStart="-10-01";
		quarterEnd="-12-31";
		quarterStr="四季度";
	}
	params['lesseefinancial.threeagostart']=threeyearago+"-10-01";
    params['lesseefinancial.threeagoend']=threeyearago+"-12-31";
    params['lesseefinancial.twoagostart']=twoyearago+"-10-01";
    params['lesseefinancial.twoagoend']=twoyearago+"-12-31";
    params['lesseefinancial.laststart']=lastyear+"-10-01";
    params['lesseefinancial.lastend']=lastyear+"-12-31";
    
    params['lesseefinancial.laststart2']=""+lastyear+quarterStart;
    params['lesseefinancial.lastend2']=""+lastyear+quarterEnd;
    params['lesseefinancial.thisstart']=""+thisyear+quarterStart;
    params['lesseefinancial.thisend']=""+thisyear+quarterEnd;
    params['lesseefinancial.custid']=custid;//承租人id
    params['lesseefinancial.guarantee']=guarantee;//担保人id
    
    params['quarter.quarter1']=threeyearago+"年四季度";
    params['quarter.quarter2']=twoyearago+"年四季度";
    params['quarter.quarter3']=lastyear+"年四季度";
    params['quarter.quarter4']=lastyear+"年"+quarterStr;
    params['quarter.quarter5']=thisyear+"年"+quarterStr;
    
    params['lessee.quarter1']=threeyearago+"年四季度";
    params['lessee.quarter2']=twoyearago+"年四季度";
    params['lessee.quarter3']=lastyear+"年四季度";
    params['lessee.quarter4']=lastyear+"年"+quarterStr;
    params['lessee.quarter5']=thisyear+"年"+quarterStr;
    
    
    
    params['guarantee.quarter1']=threeyearago+"年四季度";
    params['guarantee.quarter2']=twoyearago+"年四季度";
    params['guarantee.quarter3']=lastyear+"年四季度";
    params['guarantee.quarter4']=lastyear+"年"+quarterStr;
    params['guarantee.quarter5']=thisyear+"年"+quarterStr;
    
    params['guarantee2.quarter1']=threeyearago+"年四季度";
    params['guarantee2.quarter2']=twoyearago+"年四季度";
    params['guarantee2.quarter3']=lastyear+"年四季度";
    params['guarantee2.quarter4']=lastyear+"年"+quarterStr;
    params['guarantee2.quarter5']=thisyear+"年"+quarterStr;
    
    params['powerGenerationStatistics_2.thisyear']=thisyear;
    params['powerGenerationStatistics_2.lastyear']=lastyear;
    params['powerGenerationStatistics_2.contractidselect']=contractidselect;
    
    //租赁物
    params['leasing_insurance_list']=JsonUtil.encode(mini.get('leasing_insurance_list').getData());
	
    //抵押物
     params['mortgage_info_list']=JsonUtil.encode(mini.get('mortgage_info_list').getData());
    
    //风险政策清单及关注点跟踪反馈
   params['contract_patrol_info2.lastMajorInfo']=mini.get("contract_patrol_info.lastMajorInfo").getValue();
   params['contract_patrol_info2.lastPolicyPoint']=mini.get("contract_patrol_info.lastPolicyPoint").getValue();
   params['contract_patrol_info2.feedback']=mini.get("contract_patrol_info.feedback").getValue();
   params['contract_patrol_info2.nextPolicyPoint']=mini.get("contract_patrol_info.nextPolicyPoint").getValue();
   params['contract_patrol_info2.overdueRent']=mini.get("contract_patrol_info.overdueRent").getValue();
    //本次检查评估结论
    params['contract_patrol_info2.resadvice']=mini.get("contract_patrol_info.resadvice").getValue();
    params['contract_patrol_info2.curassettype']=mini.get("contract_patrol_info.curassettype").getValue();
    params['contract_patrol_info2.qassettype']=mini.get("contract_patrol_info.qassettype").getValue();
    return params;
}
function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 

function getQuarter(time){
	if(time!=null && time!=""){
		time = mini.formatDate(time,"MM");
		return Math.floor((time%3==0)?(time/3):(time/3+1));
	}else{
		return "";
	}
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
<div id="id_table_create_report_list"></div>