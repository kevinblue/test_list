<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var contractid = '${param.contractid}';
	var docid = '${param.docid}';
	var cid = '${param.cid}';
	//alert(docid+"doc" +contractid);
	jQuery("#contractid").html(contractid);
	 
	
		var availWidth = parseInt(document.documentElement.clientWidth);
		var availHeight = parseInt(document.documentElement.clientHeight);
		$("#mianbody").css("height", availHeight - 30 + "px");
		$("#mianbody").css("width", availWidth - 20 + "px");
		
		var read_only =  '${empty param.read_only ? true : param.read_only}' ;
		
		var custInfoParam = {};
		custInfoParam.contractid = contractid;
		custInfoParam.docid = docid;
		$.post('${pageContext.request.contextPath}/leasing/acl/getInterestTempBaseInfo.acl?radom=' + Math.random(), custInfoParam, function(value){
			var src = null;
			if(value.indexOf("查询失败") != -1){
				alert(value);
				return;
			}
			value = eval('(' + value + ')'); 
			
			var $title = $('#title');
			jQuery("#contractnum").html(value.contractnum);
			jQuery("#custname").html(value.custname);
			jQuery("#dealername").html(value.dealername);
			jQuery("#rateoriginal").html(value.rateoriginal + " %");
			jQuery("#rateadjust").html(value.rateadjust + " %");
			jQuery("#oldirr").html(value.oldirr);
			jQuery("#newirr").html(value.newirr);
			if(src != null){
				$("#iframe").attr("src", src);
			}
		});

	var formWidth  = document.documentElement.clientWidth-70;
	var height=document.documentElement.clientHeigth-2;
    
    var showTools = true;
	//if('${param.isView}' == 'true'){showTools = false;}
    
    seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
    	title:'租金计划对比',
    	renderTo:'id_table_leasing_compare_temp_list',
    	width:globalClientWidth-20,
    	height:600,
    	id:'id_table_leasing_current_regulating_of_breathing_container',
    	xmlFileName:'/eleasing/workflow/rent/regulating_breathing/interestComparetmpBefore.xml',

        lazyLoad: false,
		isClickLoad:false,
		remoteOper : false,
		showPager: true,
		//allowPage: false,
		pageSize : 80,
		//showFooter: false,
		showToolbar: showTools,
		multiSelect: false,
       params:{
	     docid:docid,
	     contractid:contractid,
	     cid:cid
	     },
	     tools : ['exportExcel'],
    columns:[
		
		{header:'id',field:'id', visible: false},
		{header:'期项',field:'rentlist1',width:35},
		{header:'计划日期',field:'plandate1',width:65},
		{header:'租金',field:'rent1',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'财务本金',field:'corpus1',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'财务利息',field:'interest1',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'业务本金',field:'corpusbusiness1',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'业务利息',field:'interestbusiness1',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'年利率',field:'yearrate1',width:50},
		{header:'分隔',field:'flag',width:40},
		{header:'期项',field:'rentlist2',width:35},
		{header:'计划日期',field:'plandate2',width:65},
		{header:'租金',field:'rent2',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'财务本金',field:'corpus2',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'财务利息',field:'interest2',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'业务本金',field:'corpusbusiness2',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'业务利息',field:'interestbusiness2',width:80,dataType : "currency",summary : true,align:'right'},
		{header:'年利率',field:'yearrate2',width:50}
		]
		 });
    });
});

</script>
<div   style="width:100%" id="id_table_leasing_compare_temp_list"  style="overflow:auto"></div>