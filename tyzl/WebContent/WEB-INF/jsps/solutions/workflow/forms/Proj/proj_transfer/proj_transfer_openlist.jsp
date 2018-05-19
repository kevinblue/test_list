<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目移交</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body style="overflow:hidden;"> 
    <div id="id_table_change_manage" class="mini-window" title="客户经理分配"
		style="width: 60%; height: 30%;" showModal="true"
		allowResize="true" allowDrag="true">
		<div class="mini-fit">
		<div id="form1" style="padding: 5px 40px 5px 40px;" title="法人客户信息">
		<table style="width: 100%">
	         <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title">项目经理：</td>
	             <td class="td-content">
	             	<input name="attachmentParams" id="attachmentParams" class="mini-hidden" />
		             <input id="cust_info.newcreator" name="cust_info.newcreator" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"/>
				 </td>
	             <td class="td-content-title">出单部门：</td>
	             <td class="td-content">
		             <input id="cust_info.newdepartment" name="cust_info.newdepartment" readOnly label="出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"/>
		         </td>
	         </tr>
	         <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">操作人：</td>
	             <input name="creatorid" id="creatorid" class="mini-hidden" value="${sessionScope.loginUser.id}"/>
	             <td class="td-content" width="38%"><input name="creator" id ="creator"  class="mini-textbox" label="操作人" readOnly type="text" value="${sessionScope.loginUser.realname}"></td>
	             <td class="td-content-title">操作时间：</td>
         		<td class="td-content" width="38%"><input id="createDate" name="createDate" class="mini-datepicker  asLabel" readOnly value="new Date()"/></td>
	         </tr>
	          <tr class="tr-project-info tr-odd">
	         	<td class="td-content-title">备注：</td>
	         	<td class="td-content" colspan="3">
	         		<input style="width:90%;height:70px" name="cust_info.note" id="cust_info.note"  maxLength=500, label="移交说明" class="mini-textarea"  type="text"  >
					
	         	</td>
	         </tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="projtransfer()">确定</a>
			    &nbsp;&nbsp;
			    <a class="mini-button" iconCls="icon-save" plain="true" onclick="javascript:{mini.get('id_table_change_manage').hide()}">取消</a>
			    </td>
			</tr>
		</table>
	</div>
	</div>
	</div>
   <div id="id_tasksContainer"></div>
   <script type="text/javascript" defer>
   var deployedIds = ("${applicationScope['userOwnedWorkflowStartSqlIds'][sessionScope['login_username']]}");
   jQuery(function(){ 					
			tenwa.createTable({
				id:"id_tasks_table",
				renderTo:"id_tasksContainer",
				width:globalClientWidth,
				height:globalClientHeight,
				showPager :true,
				title:'项目移交',
				queryButtonColSpan :8,
				queryButtonNewLine:false,
				multiSelect:true,
				isExcel:true,
				remoteOper :false,
				xmlFileName:"/eleasing/workflow/proj/proj_transfer/proj_info_transfer.xml",
				showToolbar: true,
				tools: [{
						html : '项目移交',
						plain : true,
						iconCls : 'icon-save',
						handler : function(miniTable, buttonText) {
							var selectedRowDatas = miniTable.getSelecteds();
							var transferprojid="";
		
							if(selectedRowDatas.length==0){
								mini.alert("请选择数据！");
								return;
							}
							for(var i=0;i<selectedRowDatas.length;i++){
								if(i==0)
									transferprojid="'"+selectedRowDatas[i].id+"'";
								else
									transferprojid=transferprojid+",'"+selectedRowDatas[i].id+"'";
							}
							var attachmentParams =transferprojid;
							showprojtransfer(attachmentParams);
						}
			        }
			    ],
				columns:[
			          {type : 'indexcolumn'}, 
			          {type : 'checkcolumn'},
					  {field:'id', header:'项目id',visible:false},
					{field:'custname', header:'承租客户',queryConfig:{}},
					 {field:'projid', header:'项目编号',queryConfig:{}},	
					{field:'projname', header:'项目名称',queryConfig:{}},
					{field:'projmananger', header:'项目经理',queryConfig:{}},
					{field:'industrytypename',header:'内部行业',   
						       queryConfig:{
				    	              type:'combobox',
				    	           newLine:true,
				    	            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
				                  readOnly:false,
				                 textField:'name',
				                valueField:'name',
				              showNullItem:true}},
                  	{field:'projdate',header:'项目时间',allowSort:true},
 		            {field:'projregistrarname',header:'录入人员'},
 		            {field:'projdeptname',header:'出单部门',queryConfig:{
	 		            	type:'combobox',
			    	            params:{xmlFileName:'/eleasing/workflow/proj/proj_common/department_combox.xml'},
			                  readOnly:false,
			                 textField:'name',
			                valueField:'name',
			              showNullItem:true}
 		            },
 		            {field:'leasformname',header:'租赁形式',
				    	        queryConfig:{
				    	               type:'combobox',
				    	            newLine:false,
				    	             params:{pid:'leas_form',xmlFileName:'combos/comboDict.xml'},
				                   readOnly:false,
				                  textField:'name',
				                 valueField:'value'}},
				   {field:'oper',header:'操作',renderer:function(e){
	            		 var rowData = e.record; 
	            		 var str="";
	            		 str="<a href='javascript:void(0);' onclick='loadcontracttransferinfo(\""+rowData['id']+"\")'>查看移交历史记录</a>";
		                return str;}}
                 ]
				
			});
		});
	
	//查看详情
	function loadcontracttransferinfo(id)
	{
		var url = getRootPath()+"/leasing/common/templateManager/contracttransferhis.bi?id="+id;
		mini.open({
			 url: url,
		        title: "移交历史记录", width: 800, height: 500,
		        onload: function () {
		        	
		        },
		        ondestroy: function (action) {
		        	if("savesuccess" == action){
		        		window.location.reload();
		        	}
		        }
		})
	}
	function showprojtransfer(attachmentParams){
		mini.get("attachmentParams").setValue(attachmentParams);
		var strs = [{key:"cust_info.newcreator",value:"项目经理"}];
		for(var i = 0 ;i<strs.length;i++){
			tenwa.createQueryInput({ 
				id:strs[i].key,
				label:strs[i].value,
				textField:"name",
		      	valueField:"id",
				windowWidth: 600,
				onSelect: function($me, inputObj, rowData){
					mini.get("cust_info.newdepartment").setText(rowData.deptname);
					mini.get("cust_info.newdepartment").setValue(rowData.deptid);
				},
				params: {
					role:'r_business_manager',
					xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'
				}
			});
		}
		tenwa.createQueryInput({
			id:'cust_info.newdepartment',
			label:'接收部门',
			textField:"name",
	      	valueField:"id",  
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/department_combox.xml'
			}
		});
		mini.get("id_table_change_manage").show(); 
		mini.get("cust_info.newdepartment").setEnabled(false);
		mini.get("creator").setEnabled(false);
		mini.get("createDate").setEnabled(false);
	}
	function projtransfer()
	{	
		var attachmentParams=mini.get("attachmentParams").getValue();
		var newcreator=mini.get("cust_info.newcreator").getValue();
		var newdepartment=mini.get("cust_info.newdepartment").getValue();
    	var creatorid=mini.get("creatorid").getValue();
    	var createDate=mini.get("createDate").getValue();
    	createDate=mini.formatDate ( createDate,"yyyy-MM-dd HH:mm:ss");
    	var note=mini.get("cust_info.note").getValue();
    	
    	$.ajax({
	        url: getRootPath()+"/acl/projTransfer.acl",
	        data :{ "transferprojid": attachmentParams,"newcreator":newcreator,"newdepartment":newdepartment,"creatorid":creatorid,"createDate":createDate,"note":note},
	        async : false,
	        success: function (text) {
	        	mini.alert("移交成功！");
	        	mini.get('id_table_change_manage').hide();
	        }
	    });
    	mini.get("id_tasks_table").reload();
	}
	
</script>
</body>
</html>