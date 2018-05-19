<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
var tools = [];

var deployedIds = ("${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}");
var extendsql="";
<permission:action code="permission_manager">
	extendsql = "and leader.id_ = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_fzj">
	extendsql = "and leader.fzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_fdqzj">
	extendsql = "and leader.fdqzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_qyzj">
	extendsql = "and leader.qyzj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_zj">
	extendsql = "and leader.zj = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_all">
	extendsql = " or 1=1 "
</permission:action>
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_contract_detail_div",
			renderTo: "id_proj_contract_detail_div",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			showToolbar: showTools,
			multiSelect: true,
			xmlFileName:"/eleasing/workflow/proj/proj_cancel_stop/proj_contract_detail.xml",
			params:{ projid:"${requestScope['proj_id']}"},
			tools : [
							{
								html : '撤销',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
									var rowDatas = miniTable.getSelecteds();
					        		 if(rowDatas.length == 0){
					        			 jQuery.messager.alert('错误提示',"<div style='font-size:18px;line-height:30px;'>请选择需要调息的合同</div>",'error');
					        		 }else {
					        			 revocationTX(rowDatas);
					        		 }
								
								}
							}         
						],
						columns:[
								    {type:'indexcolumn'},//让下面的数据可以勾选，从而进行编辑
				
							  {field:'cid', header:'项目信息查询',visible:false},
							  {field:'projid', header:'项目编号',
				            	         renderer:function(e){
				            		               var rowData = e.record; 
					                               return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},	
							{field:'contractid', header:'合同编号'},
							{field:'contractnumber', header:'业务合同编号'},
							{field:'custname', header:'承租客户'},
							{field:'province',header:'省份',allowSort:true},
							{field:'districtname',header:'区域',allowSort:true,
							    
									     formEditorConfig:{
										     fieldVisible:false }},
							{field:'industrytypename',header:'内部行业'},
						              
			                {field:'signdate',header:'签约日期' //如何在字段中添加数据库中相关的数据
						            	  },
						            	  
						    {field:'advancebilling',header:'是否提前开票', visible:true
						            		  
						            	  },
						            	  
		                  	{field:'actualstartdate',header:'实际起租日'},
		                  	{field:'contractstatusname',header:'合同状态' },
		                  	{field:'projdate',header:'项目时间',allowSort:true},
		                  	{field:'cleanleasemoney',header:'净融资额'},
		 		            {field:'projmanagename',header:'客户经理'},
		 		            {field:'projassistname',header:'客户助理'},
		 		            {field:'projimpotername',header:'项目导入人'},
		 		            {field:'projregistrarname',header:'信审经办'},
		 		            {field:'projdeptname',header:'出单部门'},
		 		            {field:'leasformname',header:'租赁形式'},
						   {field:'seller',header:'供应商(用","分隔)'},
						   {field:'oper',header:'操作',allowSort: false,renderer:function(e){
			            		 var rowData = e.record; 
			            		 var str="";
			            		 str="<a href='javascript:void(0);' onclick='zijinshoufu(\""+rowData['cid']+"\",\""+rowData['pid']+"\")'>查看详情</a>";
				                return str;}}
		                 ],
		});});	});
$("id_queryWorkflowsTableInput").onkeypress = function(evt){
	 var e  = getEvent(evt);
    var code = e.keyCode||e.charCode;
    if(13 == code){
        var workflowsTable = getTracywindyTable("id_tasks_table");
        workflowsTable.setParams({
               queryText:this.value.toUpperCase()
        });
        workflowsTable.reload();
    }
};

//查看详情
function zijinshoufu(cid,pid)
{
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}

function doQueryByText_pending()
{
 var contentText = document.all['id_contextText_pending'].value;
 var tableContact = getTracywindyTable("pendingRequestTable");
 tableContact.params['proj_id'] = contentText.toUpperCase();
 tableContact.reload();
}
//流程历史信息
function viewProjSummary(keyOne){
   var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
   openFullScreenWindow(URL);
}
</script>
<div id="id_proj_contract_detail_div"></div>
</body>
</html>

