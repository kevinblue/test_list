<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同信息查询</title>
<%@include file="/base/mini.jsp"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
</head>

<body style="overflow:hidden;"> 
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
   
   var tools = [];
	<permission:action code="businessManager">
		tools.push({
			html : '维护签约日期',
			plain : true,//背景透明
			iconCls : 'icon-addfolder',//按钮图标类
			handler : function (miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					if (row.contractstatus==0) {
						mini.alert("该项目出具流程尚未走完，不允许维护签约日期！！！");
						return false;
					}
					mini.get("id_signdate").setValue(row.signdate==null?"":row.signdate);
					mini.get("id_win_add_signdate").show();
				}
			}
		});
		</permission:action>
		
		
 
		<permission:action code="assetManager">
		/* tools.push({
			html : '维护是否提前开票',
			plain : true,//背景透明
			iconCls : 'icon-addfolder',//按钮图标类
			handler : function (miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					if (row.contractstatus==0) {
					mini.alert("该项目出具流程尚未走完，不允许维护是否提前开票！！！");
					return false;
					}
					mini.get("id_advancebilling").setValue(row.advancebilling==null?"":row.advancebilling);
					mini.get("id_win_add_advancebilling").show();
				}
			}
		}); */
		</permission:action>
		<permission:action code="zdnet">
		tools.push({
			html : '是否登记中登网',
			plain : true,//背景透明
			iconCls : 'icon-addfolder',//按钮图标类
			handler : function (miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					if (row.contractstatus==0) {
					mini.alert("该项目出具流程尚未走完，不允许维护是否登记中登网！！！");
					return false;
					}
					mini.get("id_zdnet").setValue(row.zdnet==null ? "" : row.zdnet);
					mini.get("id_registerdate").setValue(row.registerdate== null ? "":row.registerdate);
					mini.get("id_win_add_zdnet").show();
				}
			}
		});
		</permission:action>
	/*var selectStr = "";
	<permission:action code="SALES">
		selectStr = " and pro.proj_manage='${sessionScope.login_userid}'";
	</permission:action>
	<permission:action code="BLM">
		selectStr = " and pro.proj_dept in (select dept_id_ from t_users_depts where user_id_='${sessionScope.login_userid}' )";
	</permission:action>
	<permission:action code="BA">
		selectStr = " and pro.proj_dept in (select dbo.getDeptChildren1('${sessionScope.login_userid}') )";
	</permission:action>*/
	
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
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable){ 
					
			var contractstatusnamedata=[{id:'合同出具',text:'合同出具'},{id:'合同签约',text:'合同签约'},{id:'合同起租 ',text:'合同起租 '},{id:'合同撤销',text:'合同撤销'},{id:'合同结束 ',text:'合同结束 '}];
			new ApTable({
				id:"id_tasks_table",
				renderTo:"id_tasksContainer",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				title:'合同信息查询',
				queryButtonColSpan :8,
				queryButtonNewLine:false,
				multiSelect:false,
				isExcel:true,
				tools: tools,//添加对象
				remoteOper :true,
				xmlFileName:"/eleasing/workflow/contract/contract_search/contract_info_query.xml",
				columns:[
						    {type:'indexcolumn'},//让下面的数据可以勾选，从而进行编辑
						   	{type:'checkcolumn'},//检查下面的数据，是否有已经选中的，有选中的才可以进行更改
					  {field:'cid', header:'项目信息查询',visible:false},
					  {     field : '',
							header : '操作',
							formEditorConfig : {fieldVisible : false},
							renderer : function(e) {
								var id = e.record.pid;
								return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
										+ id
										+ "\",\"one\")'>查看/上传资料 </a>";
							}
					  },
					  {field:'projid', header:'项目编号'},	 
			       	  {field:'custname', header:'客户名称',width:135,queryConfig:{newLine:false},
			       			renderer:function(e){
			         			 var row=e.record;
			           		return "<a href='javascript:showcustinfo(\""+row.custid+"\")'>"+row.custname+"</a>";
			           		}
			           					
			          },
			          {field:'projectname', header:'项目名称',queryConfig:{newLine:false},
	            	         renderer:function(e){
         		               var rowData = e.record; 
	                               return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projectname"]+"</a>";}},
			          {field:'contractnumber', header:'业务合同编号',width:110,queryConfig:{newLine:false}},						
			         {field:'contractid', header:'合同编号',width:120,renderer:function(e){
			          	            		 var rowData = e.record; 
			          	            		 var str="";
			          	            		 if(rowData['contractstatus']==0){
												return rowData['contractid'];
			          	            		 }
			          	            		 str="<a href='javascript:void(0);' onclick='zijinshoufu(\""+rowData['cid']+"\",\""+rowData['pid']+"\")'>"+rowData['contractid']+"</a>";
			          		                return str;}},			     
			         {field:'cleanleasemoney',dataType:"currency",header:'融资金额',align:"right"},
			         {field:'leasformname',header:'租赁形式',width:80,
			    	        queryConfig:{
			    	               type:'combobox',
			    	               showNullItem:true,
			    	             params:{pid:'leas_form',xmlFileName:'combos/comboDict.xml'},
			                   readOnly:false,
			                  textField:'name',
			                 valueField:'value'}},
			         //净融资额
			        {field:'equipamt',dataType:"currency",header:'合同金额',align:"right"},
					
					{field:'province',header:'省份',allowSort:true,width:80,
                   	 queryConfig:{newLine:true}},
					{field:'districtname',header:'区域',allowSort:true,width:80,
					         /*    queryConfig:{
					            	newLine:false,
					           	 colspan:1,
						              type:'combobox', 
								        valueField:'value',
								         textField:'name',
								            params:{xmlFileName:'combos/comboDict.xml',pid:'district'},
								        allowInput:true, 
								      showNullItem:true}, */
							     formEditorConfig:{
								     fieldVisible:false }},
					{field:'industrytypename',header:'内部行业',width:80, 
						       queryConfig:{//可以当做一个对象，里面有属性可以进行配置
				    	              type:'combobox',	
				    	              newLine:false,
				    	            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
				                  readOnly:false,
				                 textField:'name',
				                valueField:'value',
				              showNullItem:true}},
				              
	          /*       {field:'signdate',header:'签约日期' //如何在字段中添加数据库中相关的数据
				            	  ,width:100},
				            	  
				    {field:'advancebilling',header:'是否提前开票', visible:true,
				            		  queryConfig:{
				            			  newLine:true,
							              type:'combobox', 
							       multiSelect:false, 
							       showNullItem:true,
							        valueField:'id', 
							         textField:'text', 
							      fieldVisible:true,
							              data:[{id:'是',text:'是'},{id:'否',text:'否'}]}  
				            	  },
				   {field:'zdnet',header:'是否登记中登网', visible:true,
				            		  queryConfig:{
				            			  newLine:false,
							              type:'combobox', 
							       multiSelect:false, 
							       showNullItem:true,
							        valueField:'id', 
							         textField:'text', 
							      fieldVisible:true,
							              data:[{id:'是',text:'是'},{id:'否',text:'否'}]}  
				            	  },
				    {field:'registerdate',header:'中登网登记时间'},    */    	  
                  	{field:'actualstartdate',header:'实际起租日',width:100},
                  	{field:'contractstatusname',header:'合同状态',width:80,
                      	       queryConfig:{
						              type:'combobox', 
						              newLine:false,
						       multiSelect:false, 
						       showNullItem:true,
						        valueField:'id', 
						         textField:'text', 
						      fieldVisible:true,
						              data:contractstatusnamedata}},
                  	{field:'projdate',header:'项目启动日期',allowSort:true},                  	
 		            {field:'projmanagename',header:'项目经理',queryConfig:{newLine:false}},
 		            {field:'projassistname',header:'项目助理'},
 		     /*       {field:'projimpotername',header:'项目导入人'},
		            {field:'projregistrarname',header:'信审经办'}, */
 		            {field:'projdeptname',header:'业务部'},
 		          
				   {field:'seller',header:'供应商(用","分隔)',queryConfig:{ newLine:true},
				                	  renderer:function(e){
			 					   	      var row=e.record;
			 					   		return "<a href='javascript:showcustinfo(\""+row.vndr+"\")'>"+row.seller+"</a>";
			 					   		}	}
			 		
                 ],
		        params:{
		        	extendsql:extendsql
		        	 //condition:getFuncRightManage("${sessionScope['login_userid']}","pro.proj_dept","pro.proj_manage")
			  	}
				
			});
		});
	});
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
	//显示流程图
	function toProcessActivePicture(deployId,processInstanceId,planActorId)
	{
		var url = "${pageContext.request.contextPath}/workflow/jbpm/getActivedRects.action?deployId="+deployId+"&processInstanceId="+escape(encodeURIComponent(processInstanceId))+"&jbpmWorkflowHistoryInfoUserId="+planActorId+"&randomNumber="+Math.random();
		openFullScreenWindow(url);
	}
	//查看详情
	function zijinshoufu(cid,pid)
	{
		var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
		openFullScreenWindow(url);
	}
/* 	function shangwutiaojian(cid)
	{
		 $.ajax({
			 method:"post",
             url:this.getRootPath() + "/table/getTableData.action",
 			 async:false,
             data:{username:$("#username").val(), content:$("#content").val()},
             dataType:"json",
             success:function(data){
             	
             }
         });
		
		var url = getRootPath()+"/workflow/forms/contract/contract_search/fund_fund_plan_base.bi?contractid="+cid;
		openFullScreenWindow(url);
	} */
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
<div id="id_win_add_signdate" class="mini-window" title="维护签约日期" style="width:400px;height:100px;"   showModal="true" allowResize="true" allowDrag="true">
        <div id="id_div_distri_manager">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:100px;">签约日期：</td>
                    <td >
                        <input id="id_signdate" name="signdate" class="mini-datepicker miniext-form-fit" allowInput="false"/>
                    </td>
                    <td>
                        <a class="mini-button " onclick="addsigndate"><spring:message code="Confirm" text="确定"/></a>
                    </td>
                </tr>
            </table>
        </div>
	</div>
	
	<div id="id_win_add_advancebilling" class="mini-window" title="维护是否提前开票" style="width:550px;height:100px;"   showModal="true" allowResize="true" allowDrag="true">
        <div id="id_div_distri_manager">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:100px;">是否提前开票：</td>
                    <td >
                        <input id="id_advancebilling" name="advancebilling" class="mini-combobox" allowInput="false" textField="text" valueField="text"
					data="[{text:'是'},{text:'否'}]" 
					type="text"/>
                    </td>
                    <td>
                        <a class="mini-button " onclick="addadvancebilling"><spring:message code="Confirm" text="确定"/></a>
                    </td>
                </tr>
            </table>
        </div>
     </div>
     
     <div id="id_win_add_zdnet" class="mini-window" title="维护是否登记中登网" style="width:30%;height:25%;" showModal="true" allowResize="true" allowDrag="true">
        <div id="id_div_distri_manager">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:100px;">是否登记中登网：</td>
                    <td >
                    <input style="width:200px;" id="id_zdnet" name="zdnet" class="mini-combobox" allowInput="false" textField="text" valueField="text"
					data="[{text:'是'},{text:'否'}]" 
					type="text"/>
                    </td>
                </tr>
                <tr>
                    <td style="width:100px;">登记日期：</td>
                    <td >
                        <input style="width:200px;" id="id_registerdate" name="registerdate" class="mini-datepicker miniext-form-fit" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                	<td style="width:100px;"></td>
                	<td style="width:100px;"></td>
                	<td>
                        <a class="mini-button " onclick="addzdnet"><spring:message code="Confirm" text="确定"/></a> &nbsp;&nbsp;
               		</td>
                </tr>
            </table>
        </div>
     </div>
</body>
</html>
<script type="text/javascript">
function addzdnet(){
	/* if (miniui_ext.submitFormValidation(["id_signdate"]) == false){
		alert("请选中项目！");
		return false}; */
	var miniTable=mini.get("id_tasks_table");
	var row=miniTable.getSelected();
	var zdnet=mini.get("id_zdnet").getText();
	var registerdate=mini.get("id_registerdate").getText();
	var contractid = row.cid;
	var params = {};
	params.contractid = contractid;
	params.zdnet=zdnet;
	params.registerdate=registerdate;
	$.ajax({
		url : '${pageContext.request.contextPath}/acl/saveZdnet.acl',
		method : 'POST',
		success:function(res){
			//console.info(res);//控制台显示info信息
			var scustinfo = res.responseText;
			mini.get("id_win_add_zdnet").hide();
			miniTable.reload();
		},
		async : false,
		failure : function(res) {
			currentLoadMask.hide();
		},
		data : params
	});
}

//填写当前日期
function addsigndate(){
	 if (miniui_ext.submitFormValidation(["id_signdate"]) == false){
		alert("请选中项目！");
		return false};
	var miniTable=mini.get("id_tasks_table");
	var row=miniTable.getSelected();
	var signdate=mini.get("id_signdate").getText();
	var contractid = row.cid;
	var params = {};
	params.contractid = contractid;
	params.signdate=signdate;
	$.ajax({
		url : '${pageContext.request.contextPath}/acl/saveSignDate.acl',
		method : 'POST',
		success:function(res){
			//console.info(res);//控制台显示info信息
			var scustinfo = res.responseText;
			mini.get("id_win_add_signdate").hide();
			miniTable.reload();
		},
		async : false,
		failure : function(res) {
			currentLoadMask.hide();
		},
		data : params
	});
}


function showcustinfo(custid){
	var params = "id="+custid+"&isView=true";
		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
		var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
}
function addadvancebilling(){
	//if(miniui_ext.submitFormValidation(["id_advancebilling"]) == false) return false;
	
	var miniTable = mini.get("id_tasks_table");
	var row = miniTable.getSelected();
	var advancebilling = mini.get("id_advancebilling").getText();
	var contractid = row.cid;
	var params = {};
	params.contractid = contractid;
	params.advancebilling = advancebilling;
	$.ajax({
		url : '${pageContext.request.contextPath}/acl/saveAdvanceBilling.acl',
		method : 'POST',
		success: function(res){
			//console.info(res);//控制台显示info 信息
			var scustinfo = res.responseText;
			mini.get("id_win_add_advancebilling").hide();
			miniTable.reload();
		},
		async : false,
		failure : function(res) {
			currentLoadMask.hide();
		},
		data : params
	});
}
function showanduploadfile(id, type) {
	var urlFlag = getRootPath()
			+ "/workflow/forms/contract/contract_search/contract_info_file_list.bi?id="
			+ id + "&type=" + type;
	mini.open({
		url : urlFlag,
		title : "合同附件信息",
		width : 800,
		height : 455,
		onload : function() {
		},
		ondestroy : function(action) {
			if ("savesuccess" == action) {
				window.location.reload();
			}
		}
	});
}
</script>