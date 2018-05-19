<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
jQuery(function(){
	tenwa.createTable({
		id: "accident_subsist_list",
		renderTo: "id_table_accident_subsist_list",
		lazyLoad: false,
		isClickLoad:false,
		remoteOper : false,
		showPager: false,
		multiSelect: true,
		virtualScroll:true,
		width: currentClientWidth,
		height: 260,
		xmlFileName: '/eleasing/jsp/law_mng/get_accidentinfo.xml',
		params:{contractid:"${requestScope['contract_id']}"},
		columns:[
			{type:'indexcolumn'},    
			{field:'id',header:'id',visible:false},
			{field:'projid', header:'项目编号',
   	         renderer:function(e){
   		               var rowData = e.record; 
                          return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},	
			{field:'contractid', header:'合同编号', visible: true},
		   	{field:'projectname',header:'项目名称',visible: true},
		   	{field:'projmanage',header:'项目经理id',visible: false},
		   	{field:'manager',header:'项目经理',visible: true},
		   	{field:'projimpoter',header:'项目导入人id',visible: false},
		   	{field:'impoter',header:'项目导入人',visible: true},
		   	{field:'projdept',header:'业务部id',visible: false},
		   	{field:'deptname',header:'业务部',visible: true},
		   	{field:'leasform',header:'业务类型编号',visible: false},
		   	{field:'leasformname',header:'业务类型',visible: true},
		   	{field:'assuror',header:'担保人',visible: true},
		   	{field:'guarantor',header:'其他担保',visible: true},
		   	{field:'startdate',header:'起租日',visible: true},
		   	{field:'rentmoney',header:'租金金额',visible: true},
		   	{field:'plandate',header:'首次逾期时间',visible: true},
		   	{field:'incrent',header:'逾期金额',visible: true},
		   	{field:'sendtime',header:'催收函/律师函发送时间',visible: true}
		]
	});
});

//流程历史信息
function viewProjSummary(keyOne){
    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
    openFullScreenWindow(URL);
}
</script>
<div id="id_table_accident_subsist_list"></div>
