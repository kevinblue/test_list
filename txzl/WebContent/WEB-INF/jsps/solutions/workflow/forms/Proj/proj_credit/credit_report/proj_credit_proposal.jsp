<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var modelnameParmas=null;
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "quotation_scheme",
			renderTo: "id_table_quotation_scheme",
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
					html : '生成项目建议书',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {						
						//获得01界面上租赁形式，来判断生成模板类型：直接租赁和售后回租
						var lease_form=mini.getbyName("proj_info.leasform").getText();
						var projdeveloptype=mini.get("proj_info.projdeveloptype").getValue();
					    var cdates=miniTable.getData();
				       for(var i=0;i<cdates.length;i++){ 
	                   		mini.alert("您已生成一份，无需重复生成");            	 
						    }
						if(projdeveloptype=="1"){
							var params1=getParams1();
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams:getAttachmentParams("root.FileType.Proposal01","${currentProcessInstanceDBID}"),
								templateno : 'F-201612012',
								tableid : 'quotation_scheme',
								modelname : '项目建议书-直租',
								returntype : 'listtocurpage',
								parames : params1
							});
							if(cdates.length==0){
								fileTeplate.createFile();
								mini.alert("您已成功生成直接租赁项目建议书");
								}
						}else if(projdeveloptype=="2"){
							var params2=getParams2();
							var fileTeplate=new fileTemplateConfig({
								isAttachment : true,
								attachmentParams:getAttachmentParams("root.FileType.Proposal01","${currentProcessInstanceDBID}"),
								templateno : 'F-201612005',
								tableid : 'quotation_scheme',
								modelname : '项目建议书-回租',
								returntype : 'listtocurpage',
								parames : params2
							});
							if(cdates.length==0){
								fileTeplate.createFile();
								mini.alert("您已成功生成售后回租项目建议书");
								}
						}else{
							mini.alert("非直接租赁和售后回租项目无须生成项目建议书！");
							return;
						}							
						if (mini.get("id_customworkflowattachment")) {
							mini.get("id_customworkflowattachment").reload();
							mini.get("id_workflowhisAttachment").reload();
						  }
						saveCallBack();
						}
		        },'-',{
		    		html : '上传',
		    		plain : true,
		    		iconCls : 'icon-add',
		    		handler : function(miniTable, buttonText) {
		    			creditUploadFile(modelnameParmas);}
		    	}
		        ],
			//data: JsonUtil.decode('${empty json_quotation_scheme_str ? "[]" : json_quotation_scheme_str }'),
			xmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',
			params : {
				flowUnid:flowUnid,
				modelname : modelnameParmas,
				filekey:flowUnid+mini.get("project_id").getValue()
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
				{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;
				var temp=e;
				return showOperator(temp,cfalg);}}
			]	
		});
	});
});
function getParams1(){
	var params={};
	params["flowunid"]=flowUnid;
	params["filekey"]=flowUnid+mini.get("project_id").getValue();
    //投资总额
     var totalInvestment=mini.get("id_total_investment_str").getValue();
     params["totalInvestment.totalInvestment1"]= parseFloat(totalInvestment)*1.05;
     params["totalInvestment.totalInvestment2"]= parseFloat(totalInvestment)*1.1;
     params["totalInvestment.totalInvestment3"]= totalInvestment;
     
     params["totalInvestment.totalInvestment4"]= parseFloat(totalInvestment)*1.05;
     params["totalInvestment.totalInvestment5"]= parseFloat(totalInvestment)*1.1;
     params["totalInvestment.totalInvestment6"]= totalInvestment;
     
     params["totalInvestment.totalInvestment7"]= parseFloat(totalInvestment)*1.05;
     params["totalInvestment.totalInvestment8"]= parseFloat(totalInvestment)*1.1;
     params["totalInvestment.totalInvestment9"]= totalInvestment;
    
    
    
     
     
	//项目名称label11
	params['label.projname'] = mini.get("project_name").getValue();
	//建议书生成日期
 	//params['label.projdate'] = mini.get("cust_name").getValue();
	params['label.projdate'] =new Date().format("yyyy年MM月dd日");	
	//项目所在地
	var proj_id=mini.get("project_id").getValue();
	
    params['projaddress'] =proj_id;
    //项目id
    params['projectid'] =projectid;
    //mini.alert(params['projectid']);
	//项目规模projscale
	params['label.projscale'] = mini.get("projscale").getText();	
	//融资租赁金额
	params['label.cleanleasemoney']=(getNumberNew(mini.get("cleanleasemoney").getValue())/100000000).toFixed(2);
	//客户融资净额
	params['label.cleancreditmoney']=(getNumberNew(mini.get("cleancreditmoney").getValue())/100000000).toFixed(2);
	//静态投资额和融资金额一样    cleanleasemoney
	//动态投资额和客户融资净额一样
	
	//期限    leaseterm
	//租赁期限(不含宽限期)
	params['year.lease']= Math.ceil((mini.get("leaseterm").getValue())/12);
	//宽限期(年)
	params['year.grace'];
	if(mini.get("incomenumberyear").getValue() == "income_1"){
		params['year.grace']=Math.ceil((mini.get("grace").getValue())/12);
	}else if(mini.get("incomenumberyear").getValue() == "income_3"){
		params['year.grace']=Math.ceil((mini.get("grace").getValue())/4);
	}else if(mini.get("incomenumberyear").getValue() == "income_6"){
		params['year.grace']=Math.ceil((mini.get("grace").getValue())/2);
	}else if(mini.get("incomenumberyear").getValue() == "income_12"){
		params['year.grace']=Math.ceil(mini.get("grace").getValue());
	}
	//租赁期限(含宽限期)
	params['year.leaseterm']= params['year.lease']+params['year.grace'];
	//利率  	
	var special = mini.get("special_regular").getData();
	var settlemethod = "";
	for(var i=0;i<special.length;i++){
		settlemethod += special[i].regularsettlemethodname + "，";
	}
	if (settlemethod.length > 0) {
		settlemethod = settlemethod.substr(0, settlemethod.length - 1);
    }
	params['rate.settlemethod'] = settlemethod;
	params['rate.interestrate'];
	if(special.length == 0){
		params['rate.interestrate']="";
	}else if(special.length == 1){
		var ratefloatamt = special[0].ratefloatamt;
		var yearrate = special[0].yearrate;
		var sumrate = parseFloat(ratefloatamt)+parseFloat(yearrate);
		params['rate.interestrate']="利率：在中国人民银行公布的同期贷款基准利率基础上上加"+ratefloatamt+"%，目前利率为"+yearrate+"%+"+ratefloatamt+"%="+sumrate+"%。";
	}else{
		var ratefloatamt = special[0].ratefloatamt;
		var yearrate = special[0].yearrate;
		var sumrate = parseFloat(ratefloatamt)+parseFloat(yearrate);
		var ratefloatamt1 = special[1].ratefloatamt;
		var yearrate1 = special[1].yearrate;
		var sumrate1 = parseFloat(ratefloatamt1)+parseFloat(yearrate1);
		params['rate.interestrate']="利率：获得首笔补贴电价前利率为在中国人民银行公布的同期贷款基准利率基础上上加"+ratefloatamt+"%，即目前获得首笔补贴电价前利率为"+yearrate+"%+"+ratefloatamt+"%="+sumrate+"%；获得首笔补贴电价后利率为在中国人民银行公布的同期贷款基准利率基础上上加"+ratefloatamt1+"%，即"+yearrate1+"%+"+ratefloatamt1+"%="+sumrate1+"%。";
	}
	//保证金
	var cautionmoney = mini.get("cautionmoney").getValue();
	var cautionmoneynum = getNumber("cautionmoney");
	params['rate.cautionmoney'] = parseFloat(cautionmoneynum/10000).toFixed(2);
	//保证金比率
	params['rate.cautionmoneyratio'] = mini.get("cautiondeductionratio").getValue();
	//手续费
	var handratio = mini.get("handratio").getValue();
	params['rate.counterfee'];
	if(mini.get("handhz").getValue() == "hand_hz.12"){
		params['rate.counterfee']="每年收取一次，每次为租赁金额的"+handratio+"%";
	}else{
		params['rate.counterfee']="";
	}
	//还款方式
	params['rate.incomenumberyear'];
	if(mini.get("incomenumberyear").getValue() == "income_1"){
		params['rate.incomenumberyear']="月付";
	}else if(mini.get("incomenumberyear").getValue() == "income_2"){
		params['rate.incomenumberyear']="双月付";
	}else if(mini.get("incomenumberyear").getValue() == "income_3"){
		params['rate.incomenumberyear']="季付";
	}else if(mini.get("incomenumberyear").getValue() == "income_6"){
		params['rate.incomenumberyear']="半年付";
	}else if(mini.get("incomenumberyear").getValue() == "income_12"){
		params['rate.incomenumberyear']="年付";
	}
	var jsonrentplan=mini.get('fund_rent_plan_frame').getData(); 
	if(jsonrentplan.length!=0){
		params['rate.rent']=parseFloat(parseFloat(jsonrentplan[0]["rent"])/10000).toFixed(2);
	}
	//信用结构
	var guaranteearr = mini.get("proj_guarantee_detail").getData();
	if(guaranteearr.length!=0){
	var legalguarantee = "";
	var naturalguarantee = "";
	for(var i=0;i<guaranteearr.length;i++){
		if((guaranteearr[i].assurorname).length>3){
			legalguarantee += guaranteearr[i].assurorname + "，";
		}else{
			naturalguarantee += guaranteearr[i].assurorname + "，";
		}
	  }
	if (legalguarantee.length > 0) {
		legalguarantee = legalguarantee.substr(0, legalguarantee.length - 1);
    }
	if (naturalguarantee.length > 0) {
		naturalguarantee = naturalguarantee.substr(0, naturalguarantee.length - 1);
    }
	params['credit.legalguarantee'] = legalguarantee;
	params['credit.naturalguarantee'] = naturalguarantee;
	}
	//项目收益率
	params['yieldrate.irr']=parseFloat(mini.get("irrshow").getValue()).toFixed(2);
	params['yieldrate.xirr']=parseFloat(mini.get("xirr").getValue()).toFixed(2);
	//承租人背景
	//客户名称
	var custname=mini.get("cust_name").getValue();
	params['lessee_frame_list']=custname;	
	//客户编号
	var custnumber = mini.getbyName("proj_info.custnumber").getValue();
	params['custbase.custname']=custname;
	params['custbase.custnumber']=custnumber;	
	//租赁方案
	//租赁期限(含宽限期)
	params['lease_scheme.leaseterm']=params['year.leaseterm'];
	//宽限期
	params['lease_scheme.grace']=params['year.grace'];
	var equipamt = mini.get("equipamt").getValue();
	var equipamtnum = getNumber("equipamt");
	params['lease_scheme.equipamt'] = parseFloat(equipamtnum/100000000).toFixed(2);
	params['lease_scheme.interestrate'] = params['rate.interestrate'];
	params['lease_scheme.incomenumberyear'] = params['rate.incomenumberyear'];
	params['lease_scheme.settlemethod'] = params['rate.settlemethod'];
	params['lease_scheme.rent'] = params['rate.rent'];
	params['lease_scheme.cautionmoneyratio'] = params['rate.cautionmoneyratio'];
	params['lease_scheme.cautionmoney'] = params['rate.cautionmoney'];
	params['lease_scheme.counterfee'] = params['rate.counterfee'];
	if(special.length>=2){
		var putconfig = mini.get('fund_put_config').getData();
		var plandate = putconfig[0].plandate;
		var formatdate = plandate.replace('-','年').replace('-','月')+'日';
		var newplandate = DateUtil.strFormatToDate('yyyy-MM-dd',plandate);
		var finaldate = DateUtil.dateAdd('y',3,newplandate);
		var newfinaldate = DateUtil.dateToStr('yyyy年MM月dd日',finaldate);
		params['lease_scheme.subsidytariff'] = "注：假定首笔放款日为"+formatdate+"，"+newfinaldate+"收到首笔补贴电价。"
	}
	//放款计划表
	var jsonconfig = mini.get('fund_put_config').getData();
 	var configarr = new Array();
	for(var i=0;i<jsonconfig.length;i++){
		var config={};
		config["plandate"] = jsonconfig[i]["plandate"];
		var planmoney = jsonconfig[i]["planmoney"];
		config["planmoney"] = parseFloat(planmoney/10000).toFixed(2);
		configarr.push(config);
	} 
	params['money_list']=JsonUtil.encode(configarr);
	
	//保证金和手续费收取计划表
	var jsonrent = mini.get('fund_fund_plan').getData();
 	var marginarr = new Array();
 	var poundagearr = new Array();
	for(var i=0;i<jsonrent.length;i++){
		var margin={};
		var poundage={};
		var moneyname=jsonrent[i]["feetypename"];
		if(moneyname=='保证金'){
			margin["plandate"] =jsonrent[i]["plandate"]; 
			var planmoney = jsonrent[i]["planmoney"];
			margin["planmoney"] =parseFloat(planmoney/10000).toFixed(2);
			marginarr.push(margin);
		}else if(moneyname=='手续费'){
			poundage["paymentid"] = jsonrent[i]["paymentid"];
			poundage["plandate"] = jsonrent[i]["plandate"];
			var money = jsonrent[i]["planmoney"];
			poundage["planmoney"] = parseFloat(money/10000).toFixed(2);
			poundage["planmoneynofree"] = parseFloat(parseFloat(jsonrent[i]["planmoney"])/1.17/10000).toFixed(2);
			poundage["planmoneytax"] = parseFloat(parseFloat(jsonrent[i]["planmoney"])/1.17*0.17/10000).toFixed(2);
			poundagearr.push(poundage);
		}
	}
	marginarr.sort(function (a, b) {
        return parseInt(a.plandate.replace(/-/g, ''), 10) - parseInt(b.plandate.replace(/-/g, ''), 10);//升序
	});
	poundagearr.sort(function (a, b) {
        return parseInt(a.plandate.replace(/-/g, ''), 10) - parseInt(b.plandate.replace(/-/g, ''), 10);//升序
	});
	params['rent_list_1']=JsonUtil.encode(marginarr);
	params['rent_list_2']=JsonUtil.encode(poundagearr);
	//租金收取计划表
	var rentplanarr = new Array();
	for(var i=0;i<jsonrentplan.length;i++){
		var rentplan={};
		rentplan["rentlist"] = jsonrentplan[i]["rentlist"];
		rentplan["plandate"] = jsonrentplan[i]["plandate"];
		rentplan["rent"] = parseFloat(parseFloat(jsonrentplan[i]["rent"])/10000).toFixed(2);
		rentplan["corpus"] = parseFloat(parseFloat(jsonrentplan[i]["corpus"])/10000).toFixed(2);
		rentplan["interestnofree"] = parseFloat(parseFloat(jsonrentplan[i]["interest"])/1.17/10000).toFixed(2);
		rentplan["interesttax"] = parseFloat(parseFloat(jsonrentplan[i]["interest"])/1.17*0.17/10000).toFixed(2);
		rentplanarr.push(rentplan);
	}
	params['rentpay_list']=JsonUtil.encode(rentplanarr);
	//业务模式
	params['credit.legalguarantee1'] = params['credit.legalguarantee'];
	params['credit.naturalguarantee1'] = params['credit.naturalguarantee'];
	//信用结构
	params['credit.legalguarantee2'] = params['credit.legalguarantee'];
	params['credit.naturalguarantee2'] = params['credit.naturalguarantee'];
	//风险分析
	params['credit.legalguarantee3'] = params['credit.legalguarantee'];
	params['credit.naturalguarantee3'] = params['credit.naturalguarantee'];
	//融资租赁方案与营销协同收益测算
	params['yieldrate.xirr2'] = params['yieldrate.xirr'];
	//特殊情况风险
	var unitreport = mini.get('table_id9').getData();
	if(unitreport.length!=0){
		params['book.fantype'] = unitreport[0]["fanmodel"]
	}
	return params;
}

function getParams2(){
	var params={};
	params["flowunid"]=flowUnid;
	params["filekey"]=flowUnid+mini.get("project_id").getValue();

	//客户名称
	params['label.custname']=mini.get("custname").getValue();
	//项目名称label11
	params['label.projname'] = mini.get("project_name").getValue();
	//建议书生成日期
 	//params['label.projdate'] = mini.get("cust_name").getValue();
	params['label.projdate'] =new Date().format("yyyy年MM月dd日");	
	//客户名称+项目名称
	params['label.custprojname']=mini.get("custname").getValue()+mini.get("project_name").getValue();
    
	//固定资产净值
	params['label.equipamt']=(getNumberNew(mini.get("equipamt").getValue())/10000).toFixed(2);
	//融资租赁金额
	params['label.cleanleasemoney']=getNumberNew(mini.get("cleanleasemoney").getValue())/10000;
	//客户融资净额
	params['label.cleancreditmoney']=getNumberNew(mini.get("cleancreditmoney").getValue())/10000;
	
	//融资租赁方案
	//融资金额
	params['lease_plan.cleanleasemoney']=params['label.cleanleasemoney'];
	//期限
	var leasetermmonth=mini.get("leaseterm").getValue();//租赁期限（月）	
	var leasetermyear=Math.ceil(leasetermmonth/12);
	var leasetermother=mini.get("grace").getValue();//宽限期
	var leaseterm=parseFloat(leasetermyear)+getRentReceiveNum(leasetermother,mini.get("rawValue_incomenumberyear").getValue());
	//alert("总期限"+leaseterm+"宽限期"+leasetermother);
	params['lease_plan.leaseterm']=leaseterm;
	//基准利率  	
	var ratebase=mini.get("baserate").getValue();
	params['lease_plan.baserate']=ratebase;
	//保证金
	var cautionmoney=getNumberNew(mini.get("cautionmoney").getValue())/10000
 	var margin=(cautionmoney*10/100).toFixed(2);
 	params['lease_plan.margin']=margin; 
 	//首笔放款日
 	var jsonlend=mini.get("fund_put_config").getData();
 	if(jsonlend.length!=0){
 		params['lease_plan.plandate']=jsonlend[0]["plandate"];//放款日期
 	}else{
 		params['lease_plan.plandate']="";//放款日期
 	}
	//手续费
	var yearpoundage=(cautionmoney*(1.8/100));
	params['lease_plan.poundage']=yearpoundage.toFixed(2)+"万元";	
	
	//获取租金
	var jsonrent=mini.get('fund_rent_plan_frame').getData(); 
	if(jsonrent.length!=0){
		var  returnmoney=jsonrent[0]["rent"];
		params['lease_plan.returnmoney']=(returnmoney/10000).toFixed(2);
	}else{
		params['lease_plan.returnmoney']=0;
	}	
/* 	//租赁期
	params['lease_plan.leaseterm']=leaseterm; */
	
	//承租人背景
	//客户名称
	var custname=mini.get("custname").getValue();
	//客户编号
	var custnumber = mini.getbyName("proj_info.custnumber").getValue();
	params['cust_base.custname']=custname;
	params['lessee_base.custname']=custname;
	params['lessee_base.custnumber']=custnumber;	
	//实缴出资
	params['lessee_base_money']=custname;
	//客户股本结构历史-认缴
	params['lessee_history_list']=custname;
	//合计
	params['lessee_base_sum']=custname;
	
	//租赁方案
	//期限
	params['lease_scheme.leaseterm']=leaseterm;
	//租赁金额
	params['lease_scheme.cleanleasemoney']=params['label.cleanleasemoney'];
	//起租日
//	params['lease_scheme.startdate']=mini.formatDate(mini.get("startdate").getValue(),"yyyy 年  MM 月 dd 日 ");
	//基准利率
	params['lease_scheme.ratebase']=ratebase;
	//宽限期调整值
	params['lease_scheme.ratebaseadd']=mini.get("graceadjust").getValue();
	//宽限期利率
	params['lease_scheme.ratebasesum']=mini.get("gracerate").getValue();
	//还款方式--还租频率
	params['lease_scheme.incomenum']=mini.get("rawValue_incomenumberyear").getValue();
	//获取分段配置信息里计算方式	
	var specialregular=mini.get("special_regular").getData();
	var regularsettcclemethodname="";
	for(var i=0;i<specialregular.length;i++){
		//var asd=specialregular[i].regularsettlemethodname;
		regularsettcclemethodname+=specialregular[i].regularsettlemethodname;
		if(i<specialregular.length-1){
			regularsettcclemethodname+="/";
		}
	}
	params['lease_scheme.incomename']=regularsettcclemethodname;
	//每期租金
	var rentplan=mini.get("fund_rent_plan_frame").getData();
	params['lease_scheme.rent']=rentplan[0].rent;	
	

	
	//保证金
	params['lease_scheme.margin']=cautionmoney;
	//保证金比例
	params['lease_scheme.marginrate']=mini.get("cautiondeductionratio").getValue();
	//租赁手续费--租赁金额的1.5%
	var handhz=mini.get("rawValue_handhz").getValue();
	var poundagemoney=params['label.cleanleasemoney']*(1.5/100);
	if(handhz=='年付'){
		params['lease_scheme.poundage']=poundagemoney*10000;
	}else{
		params['lease_scheme.poundage']=' ';
	}
	//信用结构
	var guaranteeinfo =mini.get("proj_guarantee_detail").getData();
	var guaranteecompany="";
	var guaranteeperson="";
	var mothercompany="";
	for(var i=0;i<guaranteeinfo.length;i++){
		if("CUST_INFO_COMPANY"==guaranteeinfo[i].assurorcustclass){
			guaranteecompany+=guaranteeinfo[i].assurorname;
			if(i<guaranteeinfo.length-1){
				guaranteecompany+="、";
			}
		}
		if("CUST_INFO_PERSON"==guaranteeinfo[i].assurorcustclass){
			guaranteeperson+=guaranteeinfo[i].assurorname;
			if(i<guaranteeinfo.length-1){
				guaranteeperson+="、";
			}
		}
		if("CUST_INFO_COMPANY"==guaranteeinfo[i].assurorcustclass){
			if("股东"==guaranteeinfo[i].assurerelationname){
				mothercompany+=guaranteeinfo[i].assurorname;
				if(i<guaranteeinfo.length-1){
					mothercompany+="、";
				}
			}

		}
	}
	params['credit_str.guaranteecompany']=guaranteecompany;
	params['credit_str.guaranteeperson']=guaranteeperson;
	params['credit_str.mothercompany']=mothercompany;
	//项目优势
	var irr=mini.get("irrshow").getValue();
	var xirr=mini.get("xirr").getValue();
	params['pro_advant.irr']=irr;
	params['pro_advant.xirr']=xirr;
	//承租人基本情况--看上面代码
	//财务状况
	var custid = "${requestScope['proj_info.custid']}";	
	params['custid']=custid;
	
	//保证人 
	params['guaranteinfo.guaranteecompany']=guaranteecompany;
	params['guaranteinfo.guaranteeperson']=guaranteeperson;
	
	//租赁方案单独模块
	params['lease_func.leaseterm']=leaseterm;
	params['lease_func.cleanleasemoney']=params['label.cleanleasemoney'];
	params['lease_func.ratebase']=ratebase;
	params['lease_func.ratebaseadd']=mini.get("graceadjust").getValue();
	params['lease_func.ratebasesum']=mini.get("gracerate").getValue();	
	params['lease_func.incomenum']=mini.get("rawValue_incomenumberyear").getValue();	
	params['lease_func.incomename']=regularsettcclemethodname;
	params['lease_func.rent']=rentplan[0].rent;
	params['lease_func.marginrate']=mini.get("cautiondeductionratio").getValue();
	params['lease_func.margin']=cautionmoney;
	params['lease_func.poundage']=params['lease_scheme.poundage'];
	params['lease_func.nominalprice']=mini.get("nominalprice").getValue();
	params['lease_func.firstputdate']=params['lease_plan.plandate'];
	
	//放款  jsonlend
	params['fund_pay_list']=JsonUtil.encode(jsonlend);
	
	
	//手续费收取计划表--资金收付计划
	var jsondate= mini.get('fund_fund_plan').getData();
 	var poundagearr = new Array();
 	var guarantearr = new Array();
	for(var i=0;i<jsondate.length;i++){
		var count=0;
		var poundage={};
		var guarante={};
		var moneyname=jsondate[i]["feetypename"];		
		if(moneyname=='手续费'){
			count+=1;
			poundage["number"] = count;
			poundage["paymentid"]=jsondate[i]["paymentid"];		
			poundage["plandate"] =jsondate[i]["plandate"];
			poundage["planmoney"] =(jsondate[i]["planmoney"]/10000).toFixed(2);
			poundage["taxmoney"]=(poundage["planmoney"]/1.06).toFixed(2);		
			poundage["taxmoney0"] =(poundage["taxmoney"]*0.06).toFixed(2);
			poundagearr.push(poundage);
		}
		if(moneyname=='保证金'){
			guarante["plandate"] =jsondate[i]["plandate"];
			guarante["planmoney"] =(jsondate[i]["planmoney"]/10000).toFixed(2);
			guarantearr.push(guarante);
		}
	}
	params['lease_poundage_list']=JsonUtil.encode(poundagearr);	
	params['lease_guarante_list']=JsonUtil.encode(guarantearr);
	
	//租金偿还表
	//租赁金额
	params['rent_pay.cleanleasemoney']=params['label.cleanleasemoney']*10000;
	//期限
	params['rent_pay.leaseterm']=leaseterm;
	//预付租金
	params['rent_pay.firstpayment']=getNumberNew(mini.get("firstpayment").getValue());
	
	//还款次数
	params['rent_pay.incomenumber']=mini.get("incomenumber").getValue();//年还款数
	//手续费
	params['rent_pay.poundage']=params['lease_scheme.poundage']*10000;
	//租赁金额放款日
	var startdate=mini.get("startdate").getValue();
	params['rent_pay.startdate']=mini.formatDate(startdate, "yyyy 年  MM 月 dd 日 ");//放款日期
	//租赁款发放计划表
	var lease_schemearr = new Array();
	for(var i=0;i<jsonrent.length;i++){
		var equipment={};
		    equipment["rentlist"] = jsonrent[i]["rentlist"];
		    equipment["plandate"] = jsonrent[i]["plandate"];
		    equipment["rent"] = jsonrent[i]["rent"];		    
		    equipment["corpus"] = jsonrent[i]["corpus"];
		    equipment["interest"] = jsonrent[i]["interest"];
		    equipment["taxmoney"] = (equipment["interest"]/1.17).toFixed(2);
		    equipment["taxmoney0"] = (equipment["interest"]*0.17).toFixed(2);
			lease_schemearr.push(equipment);		
	} 
	//params['lease_plan_list']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());
	params['rent_pay_list']=JsonUtil.encode(lease_schemearr);
	
	//七，业务模式  Business Model
	params['businessmodel.guaranteecompany']=guaranteecompany;//法人担保
	params['businessmodel.guaranteeperson']=guaranteeperson;//自然人担保
	params['businessmodel.mothercompany']=mothercompany;//担保人中的关联企业及母公司
	//guaranteecompany,guaranteeperson,mothercompany,mothercompany,mothercompany,guaranteeperson,mothercompany,mothercompany
	
	
	//console.info(jsonrent);
	//保证金收取计划表
	var jsonpromise = mini.get('fund_fund_plan').getData();
 	var marginarr = new Array();
	for(var i=0;i<jsonpromise.length;i++){
		var count=0;
		var margin={};
		var moneyname=jsonpromise[i]["feetypename"];
		if(moneyname=='保证金'){
			count+=1;
			margin["number"] = count;
			margin["plandate"] =jsonpromise[i]["plandate"]; 
			margin["planmoney"] =(jsonpromise[i]["planmoney"]/10000).toFixed(2);
			marginarr.push(margin);
		}
	}
	params['lease_margin_list']=JsonUtil.encode(marginarr);

	//租金偿还表
	params['rent_repay.cleanleasemoney']=(getNumberNew(mini.get("cleanleasemoney").getValue())/10000).toFixed(2);//租赁金额
	params['rent_repay.leaseterm']=leaseterm;//期限
	params['rent_repay.firstpayment']=(getNumberNew(mini.get("firstpayment").getValue())/10000).toFixed(2);//预付金
	params['rent_repay.incomenumber']=mini.get("incomenumber").getValue();//年还款数
	params['rent_repay.count']=yearpoundage.toFixed(2);//手续费
	//mini.alert(yearpoundage);
	
	return params;
}
function getRentReceiveNum(lerstem,renthz){
	if("月付"==renthz.replace(/\s+/g, "")){
		return lerstem/12;
	}
	if("季付"==renthz.replace(/\s+/g, "")){
		return lerstem*3/12;
	}
	if("半年付"==renthz.replace(/\s+/g, "")){
		return lerstem*6/12;
	}
	if("年付"==renthz.replace(/\s+/g, "")){
		return lerstem*12/12;
	}
	
}
function getNumberNew(money){
	var num = money.split(",");
	var b = "";
	for(var a=0;a<num.length;a++ ){
		b=b+num[a];
	}
	return b;
}

function dateSformat(date){
	return date.replace('-','年').replace('-','月')+'日';
} 
function showOperator(temp,cfalg){
	var id = temp.record.id;
	var filename=temp.record.filename;
	var base = "<a href='javascript:void(0);' onclick='showRecorder(\"" + id + "\")'>记录</a>";
	var base1 = "<a href='javascript:void(0);' onclick='PreviewOffice(\""+id+"\",\""+ filename+"\")'>"+"预览</a>";
	var base2 = "<a href='javascript:void(0);' onclick='downloadFile(\"" + id + "\")'>下载</a>";
	var base3 = "<a href='javascript:void(0);' onclick='editoverdue(\"" + id + "\")'>编辑</a>";
	var base4 = "<a href='javascript:void(0);' onclick='readCreateWord(\"" + id + "\")'>查看</a>";
	var base5 = "<a href='javascript:void(0);' onclick='printCreateWord(\"" + id + "\")'>打印</a>";
	var tempright="2"
	if(filename=="项目建议书-直租.docx"&&cfalg){
	    return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else if(filename=="项目建议书-回租.docx"&&cfalg){
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
	}else{
		return base2+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base1+"&nbsp;&nbsp;|&nbsp;&nbsp;"+base4;
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
function creditUploadFile(modelnameParmas) {
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			modelname : modelnameParmas,
			isAttachment:true,
			attachmentParams:getAttachmentParams('root.FileType.Proposal01','${currentProcessInstanceDBID}'),
			parames : {
				flowUnid : flowUnid,
				filekey:flowUnid+mini.get("project_id").getValue()
				//filekey : filekey　　　//项目ＩＤ
			},
			jscallback : 'creditcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function creditcustcontactfile(message){
	mini.alert(message);
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("quotation_scheme").reload();
	loadcustomworkflowattachment();
}


</script>
<div id="id_table_quotation_scheme"></div>






