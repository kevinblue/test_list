<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门信息管理</title>
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<!--javascript libray-->
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
	   .picItem{
	      float:left;
	      padding:5px 5px 5px 5px;
	      border:1px solid silver;
	   }
	</style>
	<script type="text/javascript">
	   var picJson = <%=FileUtil.getChildrenFileNameJson(WebUtil.getWebContextRealPath()+"\\menuIcons\\")%>;
	   var currentPicInputId = null;
	   jQuery(function(){
	        for(var pic in picJson)
	        {
	            jQuery("#id_pictureContainer").append("<div class='picItem'><a href='javascript:void(0)' ondblclick='getSelectedPicture(\""+pic+"\")'><img width='22px' height='22px' src='${pageContext.request.contextPath}/menuIcons/"+pic+"'/></a><div>");
	        }
	   });
	   function getSelectedPicture(picSrc)
	   {
		   if(currentPicInputId)
		   {
			   jQuery("#"+currentPicInputId).val(picSrc);
		   }
		   jQuery("#id_pictureContainer").window("close");
	   }
	    function showIconPictureWindow(e)
	    {
	        jQuery("#id_pictureContainer").show();
	    	jQuery("#id_pictureContainer").window({
	        	left:(e.pageX||e.clientX),
	        	top:(e.pageY||e.clientY+5),
	        	zIndex:9000,
	        	draggable:true,
	        	resizable:true,
	        	shadow:true,
	        	modal:false,
	        	inline:false,
	        	collapsible:false,
	        	minimizable:false,
	        	maximizable:false,
	        	closable:true,
	        	closed:false
	        });
	    	jQuery("#id_pictureContainer").window("open");
	    }
	</script>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
    var constantFlag = "Dept";
    var operFlag = "";
    var operPromit = "";
    var loadMask_add = null;
    var loadMask_update = null;
    var loadMask_remove = null;
    var loadMask_reset = null;
    var loadMask_enabled = null;
    var windowContainerId = "id_detailInfoWindowContianer";
    var formId  =  "id_detailInfoForm";
    function resetForm(){
    	 clearForm($("#"+formId)[0]);
    	 $("#"+formId+" input[name='isRelativedPath'][value='true']:radio ").attr("checked", true);
    	// $("#"+formId+" input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
    }
    function loadFormData(){
    	resetForm();
        var currentRowData = currentMenuNode;
        var arr=jQuery("#"+formId)[0].elements;  
        jQuery.each(arr,function(k,v){  
	           var name = v.name;
	           if(name && ("undefined"!=typeof(currentRowData[name]))){
		            v.value = currentRowData[name];
		       }
        });
        $("#"+formId+" input[name='parent"+constantFlag+"']").val(currentMenuNode.attributes['pid']);
        $("#"+formId+" input[name='code']").val(currentMenuNode.attributes['code']);
        $("#"+formId+" input[name='name']").val(currentMenuNode.text.substring(0,currentMenuNode.text.lastIndexOf("（")));
        $("#"+formId+" textarea[name='description']").val(currentMenuNode.attributes['description']);
        $("#"+formId+" input[name='propOne']").val(currentMenuNode.attributes['propOne']);
        $("#"+formId+" input[name='propTwo']").val(currentMenuNode.attributes['propTwo']);
        $("#"+formId+" input[name='propThree']").val(currentMenuNode.attributes['propThree']);
        $("#"+formId+" input[name='propFour']").val(currentMenuNode.attributes['propFour']);
        $("#"+formId+" input[name='propFive']").val(currentMenuNode.attributes['propFive']);
        $("#"+formId+" input[name='isRelativedPath'][value='"+currentMenuNode.attributes['isRelativedPath']+"']:radio").attr("checked",true);;
    }
    function submitCallBack(){
    	//loadTreeMenu(loadMask); 
    	var currentReloadId = "";
    	if("add" == operFlag){
    		currentReloadId = currentMenuNode.id;
    		if(( "0" != currentReloadId) && $('#id_menu_tabs_menu').tree('isLeaf',currentMenuNode.target)){
    			currentReloadId = currentMenuNode.attributes["pid"];
        	}
        }else{
        	currentReloadId = currentMenuNode.attributes["pid"];
        }
        if(!currentReloadId)
        {
        	currentReloadId = currentMenuNode.id;
        }
        var currentNode = $('#id_menu_tabs_menu').tree('find',currentReloadId);
    	$('#id_menu_tabs_menu').tree('reload',currentNode.target);
    	$('#id_menu_tabs_menu').tree('expand',currentNode.target);
    }
    function validateForm(){
    	if("remove" != operFlag){
    		return Validator.Validate(jQuery("#"+formId)[0],1,false);
        }else{
            return true;
        }
    }
    function addMenu(){
    	resetForm();
		 var comboMenuPosition = getTracywindyObject("id_comboMenuPosition");
		 comboMenuPosition.rawValue="最后";
		 comboMenuPosition.value="";
		 jQuery("#id_constantLabel").hide();
		 var pid = currentMenuNode.id;
		 comboMenuPosition.setParams({
	            pid:pid
		     });
		 comboMenuPosition.reload();
    	$("#"+formId+" input[name='parent"+constantFlag+"']").val(currentMenuNode.id);
    	showWindow();
    	operFlag = "add";
    	operPromit = "新增";
    }
    function updateMenu(){
    	resetForm();
    	loadFormData();
    	updatePosition();
    	showWindow();
    	operFlag = "update";
    	operPromit = "修改";
    }
    function updatePosition(){
    	var position  = currentMenuNode.attributes.position;
    	var pid  = currentMenuNode.attributes.pid;
    	$("#id_constantLabel").hide();
    	var comboMenuPosition = getTracywindyObject("id_comboMenuPosition");
	   	comboMenuPosition.setParams({
	         pid:pid
	      });
	 	comboMenuPosition.value = position;
	 	comboMenuPosition.reload();
    }
    function removeMenu(){
    	jQuery("#id_contextmenu").menu('hide');
    	loadFormData();
    	operFlag = "remove";
    	operPromit = "删除";
    	userOperation();
    }
    function showWindow(){
		var $window = $("#"+windowContainerId);
		$window.show();
		$window.window({
              top:50
        });
		$window.window('open');
    }
    function userOperation(){
    	if(!validateForm(formId))return;
        if(!window.confirm("确认{0}当前部门么？".replace("{0}",operPromit)))return;
    	//用户增删改查操作
        {
            var url ="${pageContext.request.contextPath}/acl/{0}.acl".replace("{0}",operFlag+constantFlag);
            var loadMaskMsg = "正在{0}部门   请稍后... ".replace("{0}",operPromit);
            var currentLoadMask = null;
            switch(operFlag)
            {
	            case "add"   :{
		            if(null == loadMask_add){
		            	loadMask_add = new tracywindyLoadMask(document.body,loadMaskMsg);
			        }
		            currentLoadMask = loadMask_add;
		            break;
		        }
	            case "update":{
		            if(null == loadMask_update){
		            	loadMask_update = new tracywindyLoadMask(document.body,loadMaskMsg);
			        }
		            currentLoadMask = loadMask_update;
		            break;
		        }
	            case "remove":{
		            if(null == loadMask_remove){
		            	loadMask_remove = new tracywindyLoadMask(document.body,loadMaskMsg);
			        }
		            currentLoadMask = loadMask_remove;
		            break;
		        }
	            default:{		            
			        if(null == loadMask_update){
		            	loadMask_update = new tracywindyLoadMask(document.body,loadMaskMsg);
			        }
		            currentLoadMask = loadMask_update;
		            break;
	            }
           }
           currentLoadMask.show();
           var params = $('#'+formId).tracywindySerializeFormToJsonObject(true);
           params['position'] = 0;
           ajaxRequest({
                url:url,
                params:params,
                timeout:30*1000,
                success:function(res){
                    alert("{0}部门成功".replace("{0}",operPromit));
                    try{$("#"+windowContainerId).window('close');}catch(e){}
                    currentLoadMask.hide();
                    submitCallBack();
                }
           });
        }
    }
    var isDeptLoaded = false;
    var isUserLoaded = false;
    var isRoleLoaded = false;
    
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
		//下拉框
		var comboMenuPosition = new tracywindyComboBox({
			lazyLoad:true,
		       dropListHeight:200,
		       id:'id_comboMenuPosition',
		       width:190,
		       otherAttributes:" require='true' label='排在' ",
		       //isAjaxLenovo:true,
		       name:'currentPosition',
		       renderTo:'id_menuPositionContainer',
		       loadMode:'ajax',
		       readOnly:false,
		       isContainEmpty:false,
		       xmlFileName:'\\combos\\combo'+constantFlag+'Position.xml',
		       isCheck:false,
		       leftAdd:1,
		       params:{
                  pid:'-1'
		       },
		       onSelect:function(combo){
		          var currentRawValue = combo.getRawValue();
		          
		          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
		          {
			          jQuery("#id_constantLabel").hide();
			      }
		          else
		          {
		        	  jQuery("#id_constantLabel").show();
			      }
		       }
		    });
		initDeptTree();
   });
	   var currentCheckedNode = null;
	   function reloadInitTree(){
		    var isReload = false;
		    try{
		    	isReload = $('#id_menu_main_content_div').tree('options').checkbox;
			}catch(e){
				isReload = true;
				loadMask.show();
			}
			if(!isReload)return;
		    var queryText = jQuery("#id_queryWorkflowsTableInput").val().toUpperCase();
		    //加载树形
		    isUserLoaded = false;
			$('#id_menu_main_content_div').tree({
				url:'${pageContext.request.contextPath}/table/getDeptUserTreeData.action',
				checkbox:false,
				onLoadSuccess:function(a,b){
					isUserLoaded = true;
				    if(isDeptLoaded && isRoleLoaded){
					    loadMask.hide();
					}
			    },
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					if(queryText){
						params['queryText'] = queryText;
					}
					if(!node){
						params['init'] = true; 
					}
			    }
			});
		    //加载树形
		    isRoleLoaded = false;
			$('#id_menu_main_content_div_right').tree({
				url:'${pageContext.request.contextPath}/table/getDeptRoleTreeData.action',
				checkbox:false,
				onLoadSuccess:function(a,b){
					isRoleLoaded = true;
				    if(isDeptLoaded && isUserLoaded){
					    loadMask.hide();
					}
			    },
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					if(!node){
						params['init'] = true; 
					}
				}
			});
	   }
	   function initDeptTree(){
		 //加载树形
		    isDeptLoaded = false;
		    loadMask.show();
			$('#id_menu_tabs_menu').tree({
				url:'${pageContext.request.contextPath}/table/getBusinessDeptTreeData.action',
				/*checkbox:true,
				cascadeCheck:false,
				onlyLeafCheck:true,*/
				dnd:true,
				onDrop:function(a,b,c){
				   var fromNode = b;
				   var toNodeId    = $(a).attr("node-id");
				   var toNode = $('#id_menu_tabs_menu').tree('find',toNodeId);
				   if(("user" == fromNode.attributes["type"] && ("user" == toNode.attributes["type"]))){
					   return false;
				   }
				   else if(("user" == fromNode.attributes["type"])){
					   return false;
				   }
	               else if(("dept" == fromNode.attributes["type"])&&("user" == toNode.attributes["type"]) && ("top"!=c)){
					   return false;
				   }
				   var fromNodeId = b.id;
				   var text = toNode.text;
				   var currentPosition = toNode.attributes['position'];
				   var pid             = toNode.id;
				   var parentNode = $('#id_menu_tabs_menu').tree('getParent',toNode.target);
				   if(("0" == toNodeId)&&("append"!=c)){
					   return false;
				   }
				   switch(c){
				      case  'append':{
				    	  var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',toNode.target);
						  var  childrenLen = chidrenNodes.length;
						  if(0 == childrenLen){
							  currentPosition = -1;
					      }else{
					    	   for(var i = 0;i<chidrenNodes.length;i++){
							    	  var childNode = chidrenNodes[i];
								      if('dept' == childNode.attributes["type"]){
								    		  currentPosition = chidrenNodes[i].attributes['position'];
								    		  text = childNode.text;
									   }
							  }
						  }
					      break;
					  }
				      case  'top':{
				    	  pid = parentNode.id;
				    	  currentPosition = -1;
					      var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',parentNode.target);
					      if("dept" == toNode.attributes["type"]){
							  for(var i = 0;i<chidrenNodes.length;i++){
								    	  var childNode = chidrenNodes[i];
								    	  if(toNodeId == childNode.id){
									    	  if(i > 0){
									    		  currentPosition = chidrenNodes[i-1].attributes['position'];
										      }
								    		  break;
								 } 
							  }
					      }
						  else if('user' == toNode.attributes["type"]){
						    	   for(var i = 0;i<chidrenNodes.length;i++){
								    	  var childNode = chidrenNodes[i];
									      if('dept' == childNode.attributes["type"]){
									    		  currentPosition = chidrenNodes[i].attributes['position'];
										   }
								  }
						  }
				    	  break;
					  }
				      case  'bottom':{
				    	  pid = parentNode.id;
				    	  if('user' == toNode.attributes["type"]){
				    		   var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',parentNode.target);
				    		   currentPosition = -1;
					    	   for(var i = 0;i<chidrenNodes.length;i++){
							    	  var childNode = chidrenNodes[i];
								      if('dept' == childNode.attributes["type"]){
								    		  currentPosition = chidrenNodes[i].attributes['position'];
									   }
							  }
					      }
				    	  break;
					  }
				   }
				   loadMask.show();
				    var params = {id:fromNodeId,entityClassSimpleName:constantFlag,parentId:pid,currentPosition:currentPosition};
					ajaxRequest({
				        url:"${pageContext.request.contextPath}/acl/updatePosition.acl",
				        success:function(res){
						       //modify by tracywindy 2013-09-28 revise the position is null's bug
					           b.attributes.pid = pid;
						       currentPosition = (-1 == parseInt(currentPosition)) ? 1 : (parseInt(currentPosition)+1);
						       b.attributes.position = ""+currentPosition;
					           loadMask.hide();
				        },
				        failure:function(res){alert("操作失败");loadMask.hide();},
				        params:params
				    });
				   return true;
				},
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					if(!node){
						params['init'] = true; 
					}
				    var queryDeptText = jQuery("#id_queryDeptsTableInput").val().toUpperCase();
					if(queryDeptText && isQueryDeptFlag){
						params['queryDeptText'] = queryDeptText;
					}
				},
				onLoadSuccess:function(a,b){
				    isDeptLoaded = true;
				    if(isUserLoaded && isRoleLoaded){
					    loadMask.hide();
					}
				    isQueryDeptFlag = false;
			    },
			    onContextMenu:function(event,node){
					node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
					currentMenuSelectNode = node;
					$(".flagUserSelectContainer").html(node.text);
			    	//$(".flagUserSelectContainer").html("");
			    	//reloadInitTree();
			    	stopDefault(event);
					var x = event.clientX;
					var y = event.clientY;
				    if("user" == node.attributes["type"]){
				    	currentMenuRemoveUserNode = node;
						jQuery("#id_contextmenu_remove").menu('show',{
							left:event.pageX||x,
							top: event.pageY||y
					    });
				    	return;
					}
					currentOperTree = "menuTree";
					currentMenuNode = node;
					var menuItems = jQuery("#id_contextmenu div.menu-item");
					menuItems.each(function(i){
						if(("0" == node.id)&&(2 == i) ){
							this.style.display="none";
						}
						else{
							if(!$('#id_menu_tabs_menu').tree('isLeaf',currentMenuNode.target) && (2 == i)){
								this.style.display="none";
						    }
							else{
								this.style.display="block";
							}
							this.style.display="block";
						}
						 // this.disabled = false;
					});
					jQuery("#id_contextmenu").menu('show',{
						left:event.pageX||x,
						top: event.pageY||y
				    });
					$('#id_menu_tabs_menu').tree('select',node.target);
				},
			    onClick:function(node){
					node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
					if("user" == node.attributes["type"]){
						currentMenuSelectNode = null;
						$(".flagUserSelectContainer").html("");
						reloadInitTree();
				    }else{
						currentMenuSelectNode = node;
						$(".flagUserSelectContainer").html(node.text);
						reloadOperTree();
					}
				}
			});
			reloadInitTree();
	   }
	   function reloadOperTree(){
		   var queryText = jQuery("#id_queryWorkflowsTableInput").val().toUpperCase();
		    //加载树形
		    isUserLoaded = false;
		    loadMask.show();
			$('#id_menu_main_content_div').tree({
				url:'${pageContext.request.contextPath}/table/getDeptUserTreeData.action',
				checkbox:true,
				cascadeCheck:false,
				onlyLeafCheck:true,
				onLoadSuccess:function(a,b){
					isUserLoaded = true;
				    if(isDeptLoaded && isRoleLoaded){
					    loadMask.hide();
					}
			    },
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					params['deptId']  = currentMenuSelectNode.id;
					if(queryText){
						params['queryText'] = queryText;
					}
					if(!node){
						params['init'] = true; 
					}
			    }
			});
		    //加载树形
		    isRoleLoaded = false;
		    loadMask.show();
			$('#id_menu_main_content_div_right').tree({
				url:'${pageContext.request.contextPath}/table/getDeptRoleTreeData.action',
				checkbox:true,
				cascadeCheck:false,
				onlyLeafCheck:true,
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					params['deptId']  = currentMenuSelectNode.id;
					if(!node){
						params['init'] = true; 
					}
				},
				onLoadSuccess:function(a,b){
					/*$("#id_menu_main_content_div_right span.tree-checkbox").click(function(e){
						distribute(currentCheckedNode);
					});*/
					$("#id_menu_main_content_div_right span.tree-checkbox").each(function(i){
						this.onclick=function(e){
							var currentTarget = getTarget(e);
							var nodeId = (currentTarget.parentNode.getAttribute("node-id"));
							currentCheckedNode = $('#id_menu_main_content_div_right').tree('find',nodeId);
							distribute(currentCheckedNode);
						};
					});
					isRoleLoaded = true;
				    if(isDeptLoaded && isUserLoaded){
					    loadMask.hide();
					}
			    },
				onCheck:function(node,checked){
			    	currentCheckedNode = node;
			    }
			});
	   }
		   
		//加载人员选择
		var currentMenuSelectNode = null;
		var firstReloaded = false;
		function distribute(node){
			loadMask.show();
	        var entityClassName = node.attributes["deptEntityClassName"];
	        var leftFieldName = "dept";
	        var leftId = currentMenuSelectNode.id;
	        var rightFieldName = node.attributes["type"];;
	        var rightId = node.id;
		    var params = {
		             entityClassName:entityClassName,
		       		 leftFieldName  :leftFieldName,
		       		 leftId         :leftId,
		       		 rightFieldName :rightFieldName,
		       		 rightId        :rightId
		        };
			 var url = "${pageContext.request.contextPath}/acl/addOrRemoveDistribute.acl";
	         ajaxRequest({
	              url:url,
	              params:params,
	              timeout:30*1000,
	              success:function(res){
	                  loadMask.hide();
	              }
	         });
	    }
	   
	//加载人员选择
	var currentMenuSelectNode = null;
	var currentMenuRemoveUserNode = null;
	var currentSelectedUserIds = [];
	function reloadSelectUsersTable(includeUserIdsArr){
		var notIncludeUserIdsArr = [];
	    if(null != includeUserIdsArr){
		    for(var j=0;j<includeUserIdsArr.length;j++){
		    	notIncludeUserIdsArr.push(includeUserIdsArr[j]);
		    }
		}
		var len = notIncludeUserIdsArr.length;
		var notIncludeUserIds = "";
		for(var i=0;i<len;i++)
	    {
		     var userId = notIncludeUserIdsArr[i];
		     if(i>0){
		    	 notIncludeUserIds+=',';
			 }
		     notIncludeUserIds+=("'"+userId+"'");
	    }
		{
		    var table = getTracywindyObject("id_table");
			table.setParams({deptId:currentMenuSelectNode.id,notIncludeUserIds:notIncludeUserIds});
			table.reload();
		}
	}
	function doSelectUsers(){
		
		if(null == currentMenuSelectNode){
			alert("请先选中左侧需要添加人员的部门");
			return ;
		}
		 var checkedNodes = $('#id_menu_main_content_div').tree('getChecked');
		 var len = checkedNodes.length;
		 if(0 == len){
				alert("请先勾选需要添加人员的记录");
				return ;  
	     }
		 var includeUserIdsArr   = [];
		 for(var i=0;i<len;i++){
			 includeUserIdsArr.push(checkedNodes[i].id);
	     }
         var url = "${pageContext.request.contextPath}/acl/addOrRemoveDistribute.acl";
         var entityClassName = "UserDepartment";
         var leftFieldName = "dept";
         var leftId  = currentMenuSelectNode.id;
         var rightFieldName = "user";
         var rightId = includeUserIdsArr.join(",");
         var params = {
                 entityClassName:entityClassName,
        		 leftFieldName  :leftFieldName,
        		 leftId         :leftId,
        		 rightFieldName :rightFieldName,
        		 rightId        :rightId
         };
         loadMask.show();
         ajaxRequest({
              url:url,
              params:params,
              timeout:30*1000,
              success:function(res){
        	 	  alert("已成功添加选中人员");
        	 	  reloadUserTree(includeUserIdsArr.join(","));
        	 	  $('#id_menu_tabs_menu').tree('reload',currentMenuSelectNode.target);
        	 	  $('#id_menu_tabs_menu').tree('expand',currentMenuSelectNode.target);
                  loadMask.hide();
              }
         });
	}
	function reloadUserTree(notIncludeUserIds){
		var queryText = jQuery("#id_queryWorkflowsTableInput").val().toUpperCase();
		isUserLoaded = false;
		loadMask.show();
		if($('#id_menu_main_content_div').tree('options').checked){
		    //加载树形
			$('#id_menu_main_content_div').tree({
				url:'${pageContext.request.contextPath}/table/getDeptUserTreeData.action',
				checkbox:true,
				cascadeCheck:false,
				onlyLeafCheck:true,
				onLoadSuccess:function(a,b){
					isUserLoaded = true;
				    if(isDeptLoaded && isRoleLoaded){
					    loadMask.hide();
					}
			    },
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					params['deptId']  = currentMenuSelectNode.id;
					params['notIncludeUserIds']  = notIncludeUserIds;
					if(queryText){
						params['queryText']  = queryText;
					}
					if(!node){
						params['init'] = true; 
					}
			    }
			});
		}else
		{
		    //加载树形
			$('#id_menu_main_content_div').tree({
				url:'${pageContext.request.contextPath}/table/getDeptUserTreeData.action',
				checkbox:false,
				onLoadSuccess:function(a,b){
					isUserLoaded = true;
				    if(isDeptLoaded && isRoleLoaded){
					    loadMask.hide();
					}
			    },
				onBeforeLoad:function(node,params){
					params['pid']  = node ? node.id : "0";
					//params['deptId']  = currentMenuSelectNode.id;
					params['notIncludeUserIds']  = notIncludeUserIds;
					if(queryText){
						params['queryText']  = queryText;
					}
					if(!node){
						params['init'] = true; 
					}
			    }
			});
		}

	}
	function removeUserDepartment(){
		loadMask.show();
        var url = "${pageContext.request.contextPath}/acl/addOrRemoveDistribute.acl";
        var entityClassName = "UserDepartment";
        var leftFieldName = "dept";
        var leftId  = currentMenuRemoveUserNode.attributes["deptId"];
        var rightFieldName = "user";
        var rightId = currentMenuRemoveUserNode.id;
        var params = {
             entityClassName:entityClassName,
       		 leftFieldName  :leftFieldName,
       		 leftId         :leftId,
       		 rightFieldName :rightFieldName,
       		 rightId        :rightId
        };
        loadMask.show();
        ajaxRequest({
             url:url,
             params:params,
             timeout:30*1000,
             success:function(res){
	    	 	  alert("已从该部门成功移除选中人员");
				  reloadUserTree('');
	      	 	  var parentNode =  $('#id_menu_tabs_menu').tree("getParent",currentMenuRemoveUserNode.target);
	      	 	  $('#id_menu_tabs_menu').tree('reload',parentNode.target);
	      	 	  $('#id_menu_tabs_menu').tree('expand',parentNode.target);
	              loadMask.hide();
             }
        });
	}
	var isQueryDeptFlag = false;
    jQuery(function(){
	     jQuery("#id_queryDeptsTableInput").keypress(function(e){
	         var code = e.keyCode||e.charCode;
	         if(13 == code){
	        	 isQueryDeptFlag = true;
	             initDeptTree();
	         }
	     });
	   });
    jQuery(function(){
	     jQuery("#id_queryWorkflowsTableInput").keypress(function(e){
	         var code = e.keyCode||e.charCode;
	         if(13 == code){
	             reloadUserTree();
	         }
	     });
	   });
</script>
</head>
<body>
	<div id='menu_main_content' style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border-top:0px;border-right:0px;padding:5px;">
	   <div class="panel-title" >部门人员列表
	   <SPAN>搜索：<INPUT style="BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; WIDTH: 80px; BORDER-TOP: #ddd 1px solid; MARGIN-RIGHT: 4px; BORDER-RIGHT: #ddd 1px solid" id=id_queryDeptsTableInput type="text" jQuery18205254865307538132="34"></SPAN>
	   </div>
       <div id="id_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;" oncontextmenu='cancelBubble(event);' ></div>
	</div>
	<div id='menu_main_oper' style="overflow:hidden;float:left;border-bottom:1px solid #DDD;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;人员分配</div>
		<div class="panel-title" >
			<SPAN>搜索：<INPUT style="BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; WIDTH: 80px; BORDER-TOP: #ddd 1px solid; MARGIN-RIGHT: 4px; BORDER-RIGHT: #ddd 1px solid" id=id_queryWorkflowsTableInput type="text" jQuery18205254865307538132="34"></SPAN>
			<a class="x-panel-table-tool-div-separator-image">&nbsp;</a>
			<a href="javascript:void(0);" onclick="doSelectUsers();" style="TEXT-INDENT: 0px" class="l-btn l-btn-plain" plain="true" iconCls="icon-plus" jQuery182005250442229279051="3"><SPAN class=l-btn-left><SPAN style="PADDING-LEFT: 20px" class="l-btn-text icon-plus">添加选中人员</SPAN></SPAN></a>
	    </div>
	   <div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<div id='menu_main_oper_right' style="overflow:hidden;float:left;border:1px solid #DDD;border-left:0px;border-top:0px;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;角色分配</div>
	   <div id='id_menu_main_content_div_right' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<script type='text/javascript'>
	    var heightAdd = -2;
	    var widthAdd  = -358;
		jQuery("#menu_main_content").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div").css("height",(pageHeight+heightAdd-40-30)+"px");
		jQuery("#id_menu_main_content_div_right").css("height",(pageHeight+heightAdd-40)+"px");
		
		jQuery("#menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper_right").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper").css("width",((pageWidth + widthAdd)/2 )+"px");
		jQuery("#menu_main_oper_right").css("width",((pageWidth + widthAdd)/2 )+"px");
	</script>
	<div id="id_detailInfoWindowContianer"  closed="true" modal="true" title="部门信息管理" style="display:none;width:400px;padding-top:20px;height:420px;">
	        <center>
			  <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentDept" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">部门名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="部门名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">部门编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="部门编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">部门描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">属性一:     </td><td  colspan=2 class="input_value"><input  type="text" require="false" label="属性一"  name="propOne"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        <tr><td class="input_label_desc">属性二:     </td><td  colspan=2 class="input_value"><input  type="text" require="false" label="属性二"  name="propTwo"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        <tr><td class="input_label_desc">属性三:     </td><td  colspan=2 class="input_value"><input  type="text" require="false" label="属性三"  name="propThree"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        <tr><td class="input_label_desc">属性四:     </td><td  colspan=2 class="input_value"><input  type="text" require="false" label="属性四"  name="propFour"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        <tr><td class="input_label_desc">属性五:     </td><td  colspan=2 class="input_value"><input  type="text" require="false" label="属性五"  name="propFive"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr  ><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_constantLabel">&nbsp;&nbsp;之后</span><span class="content-required">*</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan=3>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContianer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div id="id_contextmenu" class="easyui-menu" style="display:none;width:120px;">
		<div iconCls="icon-plus-w" onclick='addMenu();'>添加</div>	
		<div iconCls="icon-cogs-w" onclick='updateMenu();'>修改</div>
		<div iconCls="icon-minus-w" onclick='removeMenu();'>删除</div>
	</div>
	<div id="id_contextmenu_remove" class="easyui-menu" style="display:none;width:120px;">
		<div iconCls="icon-minus-w" onclick='removeUserDepartment();'>从该部门移除用户</div>
	</div>
	<div id="id_pictureContainer" style="display:none;text-align:center;width:300px;height:300px;overflow:auto;"  closed="true" modal="true" title="图片选择器"></div>
</body>
</html>
