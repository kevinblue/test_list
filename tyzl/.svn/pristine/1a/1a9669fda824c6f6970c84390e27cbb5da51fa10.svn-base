<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户群组信息管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
    <style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	</style>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
	    //加载树形
		$('#id_menu_tabs_menu').tree({
			url:'${pageContext.request.contextPath}/table/getDeptWithUserTreeData.action',
			onBeforeLoad:function(node,params){
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
			},
			onLoadSuccess:function(a,b){
		    },
		    onClick:function(node){
		    	node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
				if("dept" == node.attributes["type"])return;
				currentMenuSelectNode = node;
				$("#id_userSelectContainer").html(node.text);
				reloadSelectUsersTable(null);
			}
		});
   });

	   
	//加载人员选择
	var currentMenuSelectNode = null;
	var currentMenuRemoveUserNode = null;
	var currentSelectedUserIds = [];
	
	function reloadSelectUsersTable(){
		    var table = getTracywindyObject("id_table");
		    if(!firstReloaded){
		    	table.columns.push({
			    	 header:'群组分配',
			    	 name:'isgrant',
			    	 renderer:function(value,tableObj,columnName,columnIndex,rowData){
			    	 	var checked = "";
		            	if('1' == value )
		            	{
		            		checked ="checked";
			            }
			            var html ="<input style='cursor:pointer;' type='checkbox' "+checked +"  onclick=\"distribute('"+rowData.rowIndex+"',this.checked);\"/>";
			            return html;
			    	 }
			    });
			}
		    firstReloaded = true;
		    table.xmlFileName = "/acl/queryUserGroup.xml";
			table.setParams({userId:currentMenuSelectNode.id});
			table.reload();
	}
	var firstReloaded = false;
	function distribute(rowIndex,checked){
		 loadMask.show();
         var rowData = getTracywindyObject("id_table").getRowDataAt(rowIndex);
         var entityClassName = "UserGroup";
         var leftFieldName = "user";
         var leftId  = rowData["userid"];
         var rightFieldName = "group";
         var rightId = rowData["groupid"];
         var url = "${pageContext.request.contextPath}/acl/addOrRemoveDistribute.acl";
         var params = {
                 entityClassName:entityClassName,
        		 leftFieldName  :leftFieldName,
        		 leftId         :leftId,
        		 rightFieldName :rightFieldName,
        		 rightId        :rightId
         };
         ajaxRequest({
              url:url,
              params:params,
              timeout:30*1000,
              success:function(res){
                  loadMask.hide();
              }
         });
         
    }
    jQuery(function(){
	   	 var table = new tracywindyTable({
	   	     border:true,
	         renderTo:'id_menu_main_content_div',
	         width:(pageWidth + widthAdd-15),
	         height:(pageHeight+heightAdd-40),
	         tools:[{
	        	  isHtml:true,
	           	  html:'全局搜索：<input type="text" style="margin-right:4px;border:1px solid #DDD;width:80px;" id="id_queryWorkflowsTableInput" />'
	           }],
	         id:'id_table',
	         xmlFileName:'/acl/queryAllGroup.xml',
	         loadMode:'ajax',
	         isPage:false,
	         isFit:true,
	         border:true,
	         isRank:true,
	         columns:[
			            {header:'记录编号',name:'id',hidden:true},
			            {header:'群组名称',name:'name'},
			            {header:'群组编号',name:'code'},
			            {header:'群组描述',name:'description'}
		     ],
		     params:{
	   		    enabled : 1
		     }
	   	 });
	     jQuery("#id_queryWorkflowsTableInput").keypress(function(e){
	         var code = e.keyCode||e.charCode;
	         var table = getTracywindyObject("id_table");
	         if(13 == code){
	             table.setParams({
	                    queryText:this.value.toUpperCase()
	             });
	             table.reload();
	         }
	     });
	   });
</script>
</head>
<body>
	<div id='menu_main_content' style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border-top:0px;border-right:0px;padding:5px;">
	   <div class="panel-title" >用户列表</div>
       <div id="id_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"  ></div>
	</div>
	<div id='menu_main_oper' style="overflow:hidden;float:left;border:1px solid #DDD;border-left:0px;border-top:0px;padding:5px;">
	   <div class="panel-title" ><font color="red" id="id_userSelectContainer"></font>&nbsp;&nbsp;群组分配</div>
	   <div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:hidden;padding:5px;"></div>
	</div>
	<script type='text/javascript'>
	    var heightAdd = -2;
	    var widthAdd  = -348;
		jQuery("#menu_main_content").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div").css("height",(pageHeight+heightAdd-40)+"px");
		
		jQuery("#menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper").css("width",(pageWidth + widthAdd)+"px");
	</script>
</body>
</html>
