<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!--css sheet-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/my97DatePicker/skin/WdatePicker.css"/>
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
	  html,body{
	    overflow:hidden;
	  }
	</style>
    <script type="text/javascript">
    var loadMask = null;
    var operFlag = "";
     jQuery(function(){
         if(!jQuery("#id_tableContainer")[0]){return;}
		 if(null == loadMask){
			 loadMask = new tracywindyLoadMask(document.body,"操作进行中   请稍后...");
	     }
         var all_width  =  800;
         var all_height =  500;
         var widthAdd   = 0;
         if(SysBrowser.getBrowser().toLowerCase().indexOf("ie")>-1){
        	widthAdd+=10;
		 }
  	     var delegateTable = new tracywindyTable({
		   renderTo:"id_tableContainer",
		   id:'id_delegateTable',
		   width:792+widthAdd,
		   height:400,
		   isShortPage:true,
		   //title:'流程授权<font color="#FF9238">&nbsp;&lt;&nbsp;&nbsp;${login_realname}（${login_username}）&gt;&nbsp;</font>',
		   xmlFileName:'/jbpm/workflowDelegate.xml',
		   loadMode:'ajax',
		   columns:[{
			   header:'流程部署编号',name:'deployment_id_',hidden:true
		   },{
			   header:'流程定义',name:'workflow_definition_',hidden:true
		   },{
			   header:'流程名称',name:'workflow_name_'
		   },{
			   header:'授权编号',name:'grant_delegate_id_',hidden:true
		   },{
			   header:'授权对象编号',name:'delegate_user_id_',hidden:true
		   },{
			   header:'授权对象登录名',name:'delegate_user_name_',hidden:true
		   },{
			   header:'授权对象',name:'delegate_user_realname_',
			   renderer:function(value,tableObj,columnName,columnIndex,rowData){
			      if(rowData['delegate_user_name_'])
			      {
			    	  return value+"（"+rowData['delegate_user_name_']+"）";
				  }
			      return "未授权";
			   }
		   },{
			   header:'授权开始时间',name:'start_date_'
		   },{
			   header:'授权结束时间',name:'end_date_'
		   },{
			   header:'操作',name:'oper',
			   renderer:function(value,tableObj,columnName,columnIndex,rowData){
			      if(!rowData['grant_delegate_id_'])
			      {
			    	  return '<a href="javascript:void(0);" onclick="delegateSingleWorkflow('+rowData.rowIndex+');">添加授权</a>';
				  }
				  var oper = '<a href="javascript:void(0);" onclick="delegateSingleWorkflow('+rowData.rowIndex+');">修改授权</a>';
				  oper+="&nbsp;&nbsp;";
				  oper+='<a href="javascript:void(0);"  onclick="removeSingleWorkflow('+rowData.rowIndex+');">取消授权</a>';
			      return oper;
			   }
		   }],
		   params:{userid:'${sessionScope.login_userid}'},
		   isFit:true,
		   border:true,
		   isCheck:true,
		   pageSize:20,
		   isShortPage:true,
		   border:true,
		   checkType:'checkbox',//radio,
		   isRank:true,
		   isPage:false,
		   displayToggleContainer:"id_setDelegateWindowContainer",
		   tools:[{
	              html:'全部授权',
	              iconCls:'icon-calendar',
	              handler:function(table){operFlag = "delegateAllWorkflows";showDelegateDataWindow();}
		   },'-',{
	              html:'批量授权',
	              iconCls:'icon-calendar',
	              handler:function(table){			
		                var checkedRowDatas = table.getCheckedRowDatas();
			  			var checkedRowDataslen = checkedRowDatas.length;
						if(checkedRowDataslen < 1)
						{
							alert("请选择需要批量授权的流程");
							return ;
						}
						operFlag = "delegateCheckedWorkflows";showDelegateDataWindow();
				}
		   },'-',{
              html:'批量取消',
              iconCls:'icon-calendar',
              handler:function(table){removeCheckedDelegatedWorkflows(table);}
		   },'-',{
	           html:'全部取消',
	           iconCls:'icon-calendar',
	           handler:function(table){removeAllDelegatedWorkflows();}
		   }],
		   callBack:function(){}
		});
     }); 
         //显示授权窗口  
	     function showDelegateDataWindow()
	     {
	    	   var table = getTracywindyObject('id_delegateTable');
	    	   var checkedRowDatas = table.getCheckedRowDatas();
	    	   var workflowNameArr  = [];
		       if("delegateAllWorkflows" == operFlag)
		       {
		    	   checkedRowDatas = table.tableData;
			   }
		       var checkedRowDataslen = checkedRowDatas.length;
			   if(0 == checkedRowDataslen){
				   alert("流程列表不能为空！");
				   return;
			   }
		       var rowData  = checkedRowDatas[0];
		       var isFindRowData = false;
				for(var i=0;i<checkedRowDataslen;i++)
				{
					var currentRowData = checkedRowDatas[i];
					if((currentRowData['delegate_user_name_'])&&!isFindRowData)
					{
						rowData = currentRowData;
						isFindRowData = true;
					}
					workflowNameArr.push(currentRowData["workflow_name_"]);
				}
	     	    var delegateUserId  = rowData["delegate_user_id_"];
		     	var delegateRealName = "";
	    	    var startDate  = rowData["start_date_"];
	    	    var endDate  = rowData["end_date_"];
			    if(rowData['delegate_user_name_'])
			    {
			    	delegateRealName = rowData['delegate_user_realname_']+"（"+rowData['delegate_user_name_']+"）";
				}
	    	    jQuery("#id_delegate_workflow_index").val(rowData.rowIndex);
	    	    jQuery("#id_delegateUser").val(delegateUserId);
	    	    jQuery("#id_delegateRealName").val(delegateRealName);
	    	    jQuery("#id_startDate").val(startDate);
	    	    jQuery("#id_endDate").val(endDate);
		       jQuery("#id_listAllWorflows").html(workflowNameArr.join(","));
		       jQuery("#id_setDelegateForm").show();
		       jQuery("#id_setDelegateForm").window({
	                  top:100
			   });
		       jQuery("#id_setDelegateForm").window("open");
	     }
	     //关闭授权窗口   
	     function closeDelegateWindow(msg)
	     {
		     try{
		    	 jQuery("#id_setDelegateForm").window("close");
			 }catch(e){
			 }
	    	 alert("操作"+msg);
	    	 loadMask.hide();
	    	 getTracywindyObject('id_delegateTable').reload();
	    	 jQuery("#id_setDelegateWindowContainer").window("open");
	     }  
        //选择授权人
	    function choseDelegateUser()
		{
			var strURL = "${pageContext.request.contextPath}/acl/commonUserSelection.bi?isUseRowId=true";
			var parentOrgId   = '0';
			var parentOrgName = '组织结构';
			var selectModel   = 'S';
			var isQueryModel  = null;
			var argumentArr = [];
			argumentArr.push(document.getElementById('id_delegateUser'));//realValue
			argumentArr.push(document.getElementById('id_delegateRealName'));//displayValue
			showModalUserSelectionWindow_(strURL,argumentArr,parentOrgId,parentOrgName,selectModel,isQueryModel);
	    }
	    //兼容批量操作
	    function singleWorkflowOper(rowIndex)
	    {
	    	var table = getTracywindyObject('id_delegateTable');
     		var allItems = document.getElementsByName(table.checkName);
     		for(var i= 0;i<allItems.length;i++)
     		{
     			if(allItems[i]['rowIndex'] == rowIndex)
     			{
     				allItems[i].checked = true; 
         		}
     			else
     			{
     				allItems[i].checked = false; 
         		}
     		}
		}
	    //单个流程授权
		function delegateSingleWorkflow(rowIndex)
		{
			operFlag = "singleDelegateWorkflow";
			singleWorkflowOper(rowIndex);
			showDelegateDataWindow();
	    }
	    //授权选中流程
	    function delegateCheckedWorkflows()
	    {
	    	var rowIndex = jQuery('#id_delegate_workflow_index').val();
		    var table = getTracywindyObject('id_delegateTable');
		    singleWorkflowOper(rowIndex);
			var checkedIds = "";
			var checkedIdsArray = [];
			var checkedDeploymentIdsArray = [];
			var checkedRowDatas = table.getCheckedRowDatas();
			var checkedRowDataslen = checkedRowDatas.length;
			if(checkedRowDataslen < 1)
			{
				alert("请选择需要批量授权的流程");
				return ;
			}
			for(var i=0;i<checkedRowDataslen;i++)
			{
				var rowData = checkedRowDatas[i];
				var id = rowData['grant_delegate_id_'];
				if(!id)
				{
					id = "null";
				}
				checkedIdsArray.push(id);
				checkedDeploymentIdsArray.push(rowData['deployment_id_']);
			}
     	    var delegateUserId  = jQuery("#id_delegateUser").val();
    	    var startDate  = jQuery("#id_startDate").val();
    	    var endDate  = jQuery("#id_endDate").val();
    	    var grantMethod  = "批量授权";
	    	loadMask.show();
	    	var url = "${pageContext.request.contextPath}/jbpm/delegateCheckedWorkflows.action";
		    ajaxRequest({
                url:url,
                success:function(res){closeDelegateWindow("成功");},
                failure:function(res){closeDelegateWindow("失败");},
                params:{
                	delegateUserId:delegateUserId,
                	startDate:startDate,
                	endDate:endDate,
                	grantMethod:grantMethod,
                	checkedIds:checkedIdsArray.join(","),
                	checkedDeploymentIds:checkedDeploymentIdsArray.join(",")
                }
			});
		}
	    //授权所有流程
	    function delegateAllWorkflows()
	    {
     	    var delegateUserId  = jQuery("#id_delegateUser").val();
    	    var startDate  = jQuery("#id_startDate").val();
    	    var endDate  = jQuery("#id_endDate").val();
    	    var grantMethod  = "全部授权";
	    	loadMask.show();
	    	var url = "${pageContext.request.contextPath}/jbpm/delegateAllWorkflows.action";
		    ajaxRequest({
                url:url,
                success:function(res){closeDelegateWindow("成功");},
                failure:function(res){closeDelegateWindow("失败");},
                params:{
                	delegateUserId:delegateUserId,
                	startDate:startDate,
                	endDate:endDate,
                	grantMethod:grantMethod
                }
			});
		}
		function setDelegate()
		{
     	    var delegateUserId  = jQuery("#id_delegateUser").val();
     	    if(!delegateUserId){alert("请选择代理对象");return;}
    	    var startDate  = jQuery("#id_startDate").val();
    	    if(!startDate){alert("请选择代理开始日期");return;}
    	    var endDate  = jQuery("#id_endDate").val();
    	    if(!endDate){alert("请选择代理结束日期");return;}
			if("delegateAllWorkflows" == operFlag){delegateAllWorkflows();}
			else{delegateCheckedWorkflows();}
		}
		
		//取消单个授权
		function removeSingleWorkflow(rowIndex)
		{
			operFlag = "removeDelegateWorkflow";
			singleWorkflowOper(rowIndex);
			removeCheckedDelegatedWorkflows();
	    }
		//取消选中授权
		function removeCheckedDelegatedWorkflows()
		{
			var table = getTracywindyObject('id_delegateTable');
			var checkedIds = "";
			var checkedIdsArray = [];
			var checkedRowDatas = table.getCheckedRowDatas();
			var checkedRowDataslen = checkedRowDatas.length;
			if(checkedRowDataslen < 1)
			{
				alert("请选择需要取消授权的流程");
				return ;
			}
			for(var i=0;i<checkedRowDataslen;i++)
			{
				var rowData = checkedRowDatas[i];
				var id = rowData['grant_delegate_id_'];
				if(id)
				{
					checkedIdsArray.push(id);
				}
			}
			if(checkedIdsArray.length == 0)
			{
				alert("选择项中至少要包含一个已经授权的流程");
				return;
			}
	    	loadMask.show();
	    	var url = "${pageContext.request.contextPath}/jbpm/removeCheckedDelegatedWorkflows.action";
		    ajaxRequest({
                url:url,
                success:function(res){closeDelegateWindow("成功");},
                failure:function(res){closeDelegateWindow("失败");},
                params:{
                	checkedIds:checkedIdsArray.join(",")
                }
			});
		}
		//取消所有授权
		function removeAllDelegatedWorkflows()
		{
	    	loadMask.show();
	    	var url = "${pageContext.request.contextPath}/jbpm/removeAllDelegatedWorkflows.action";
		    ajaxRequest({
                url:url,
                success:function(res){closeDelegateWindow("成功");},
                failure:function(res){closeDelegateWindow("失败");},
                params:{}
			});
		}
   </script>
