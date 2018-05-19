<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目信息查询</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>	
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
   <div id="id_proj_credit_search"></div>
   <script type="text/javascript" defer>
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
   	<permission:action code="permission_credit_serach">
   	extendsql = " and (select t.credit_dept  from t_business_credit_config t "
          +" where t.industry_id=pro.industry_type and t.business_dept=dept.id_)= "
          +" (select tdr.dept_id_ from t_users_deptroles tud "
          +" left join t_depts_roles tdr on tdr.id_=tud.deptrole_id_ "
          +" where tud.user_id_='${sessionScope.login_userid}')"
   </permission:action>
   	tools=[];
   	<permission:action code="proj_serach_excel">
		tools.push('exportExcel');
	</permission:action>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			var statusnamedate=[{id:'项目立项',text:'项目立项'},{id:'项目信审(审批通过)',text:'项目信审(审批通过)'},{id:'项目复议',text:'项目复议'},{id:'项目否决',text:'项目否决'},{id:'项目撤销',text:'项目撤销'}];
			var status=[{text:'立项登记[草稿]'},{text:'立项登记[进行中]'},{text:'立项登记[已结束]'},
			            {text:'立项变更[草稿]'},{text:'立项变更[进行中]'},{text:'立项变更[已结束]'},
			            {text:'项目撤销[草稿]'},{text:'项目撤销[进行中]'},{text:'立项登记[已结束]'},
			            {text:'项目审批[草稿]'},{text:'项目审批[进行中]'},{text:'项目审批[已结束]'}]
			new ApTable({
				id:"proj_credit_search",
				renderTo:"id_proj_credit_search",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				title:'项目信息查询',
				queryButtonColSpan:8,
				multiSelect:false,
				isExcel:true,
				remoteOper :true,
				queryButtonNewLine:true,
				loadMode:'ajax',
				xmlFileName:"/eleasing/workflow/proj/proj_credit/proj_credit_serach.xml",
				tools :tools,
				columns:[
					{field:'projid', header:'项目编号'},	
					{field:'custname', header:'客户名称',queryConfig:{}},
			        {field:'proname', header:'项目名称',queryConfig:{},
            	        renderer:function(e){
         		           var rowData = e.record; 
	                           return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["proname"]+"</a>";}},			        
					{field:'industrytypename',header:'内部行业（项目）',queryConfig:{
						newLine:false,
						type:'combobox',
						  valueField:'name',
					         textField:'name',
					         params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
					}},
					/* {field:'subcustkind',header:'内部子行业（项目）'}, */
					{field:'province',header:'省份',allowSort:true,
                   	 queryConfig:{
                   		newLine:true,
                   		 type:'combobox',
                   		allowInput:true,
	                   	 valueField:'value',
				         textField:'value',
				         params:{xmlFileName:'eleasing/jsp/base/t_district.xml',PID:'CHN'},
                   	 }},
                   	{field:'city',header:'城市',allowSort:true},
					{field:'districtname',header:'区域',allowSort:true,
						             queryConfig:{
						            	 colspan:1,
							              type:'combobox', 
									        valueField:'value',
									         textField:'name',
									            params:{xmlFileName:'combos/comboDict.xml',pid:'district'},
									        allowInput:true, 
									      showNullItem:true},
								     formEditorConfig:{
									     fieldVisible:false }},
					{field:'flowstatus',header:'当前流程状态',width:115,allowSort:true,queryConfig:{
						newLine:false,
						type:'combobox',
						valueField:'text',
			               textField:'text',
			               data:status
					}},
					{field:'taskname',header:'最新步骤名称',width:170,allowSort:true},
					{field:'planusers',header:'最新步骤处理人',allowSort:true},
                  	{field:'statusname',header:'项目状态',
	                  	             queryConfig:{
	                  	            	newLine:true,
						                    type:'combobox',
						              valueField:'id',
						               textField:'text',
						             fieldVisible:true,
						                     data:statusnamedate}},
                  	{field:'projdate',header:'立项日期',allowSort:true},
 		            {field:'leasformname',header:'租赁形式',
				    	             queryConfig:{
				    	                    type:'combobox',
				    	                    newLine:false,
				    	                  params:{pid:'leas_form',xmlFileName:'combos/comboDict.xml'},
				                        readOnly:false,
				                       textField:'name',
				                      valueField:'value'}},
				    {field:'equipamt',header:'设备款'},
 		            {field:'projdeptname',header:'业务部'},
 		            {field:'deptroute',header:'业务子部门'},
 		        /*     {field:'projimpotername',header:'项目导入人'},
 		            {field:'projmanagename',header:'项目经理',queryConfig:{}}, */
 		            {field:'projassistname',header:'项目助理'},
				    {field:'seller',header:'供应商(用","分隔)',width:200,queryConfig:{
     	            	 newLine:false}}
                 ],
		        params:{
		        	extendsql:extendsql
		        	 //condition:getFuncRightManage("${sessionScope['login_userid']}","pro.proj_dept","pro.proj_manage")
			  	}
				
			});
		});
	});
	//流程历史信息
	function viewProjSummary(keyOne){
	    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
	    openFullScreenWindow(URL);
	}
</script>
</body>
</html>