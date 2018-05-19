<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "already_regulating_of_breathing",
			renderTo: "id_already_regulating_of_breathing",
			width: globalClientWidth - 30,
			height: 600,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper : false,
			showPager: true,
			multiSelect: true,
			xmlFileName:'/eleasing/workflow/rent/regulating_breathing/already_regulating_breathing_list.xml',
			params:{adjustid:"${requestScope['fund_standard_interest.id'] }"},
		    columns:[
					{type:'indexcolumn'}, 
					{type:'checkcolumn'},
		            {header:'id',field:'id', visible:false},
		            {header:'proj_id',field:'proj_id', visible:false},
					{header:'custid',field:'custid', visible:false},
					{header:'合同号',field:'contractid'},
					{header:'docid',field:'docid', visible:false},
					{header:'业务合同号',field:'contractnumber'},
					{header:'客户名',field:'custname'},
					{header:'设备款',field:'equipamt'},
					{header:'首付款',field:'firstpayment'},
					{header:'还租次数',field:'incomenumber'},
					{header:'付租类型',field:'incomenumberyear'},
					{header:'付租方式',field:'periodtype'},
					{header:'起租日期',field:'startdate'},
					{header:'利率',field:'yearrate'},
					{header:'内部收益率',field:'irr'},
					{header:'调息前利率',field:'rateoriginal'},
					{header:'调息后利率',field:'rateadjust'},
					{header:'调息前内部收益率',field:'oldirr'},
					{header:'调息后内部收益率',field:'newirr'},
				    {header:'操作',align:'center',width:120,
								renderer:function(e){
								var  table = e.record;
				                return "<a href='javascript:void(0);' onclick='previewtmp("+JsonUtil.encode(table)+")'>对比</a>";}}
	        ]
		});});});	
function previewtmp(data){
   var URL = "${pageContext.request.contextPath}/leasing/query/condition_his/interestCompare.bi";
   var params = {cid:data.id,contractid:data.contractid,docid:data.docid,read_only :false};
   openFullScreenWindow(URL,params);
}

</script>
<div id="id_already_regulating_of_breathing"></div>