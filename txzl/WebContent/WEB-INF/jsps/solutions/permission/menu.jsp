<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix='c'   uri='/WEB-INF/tlds/c.tld' %>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<c:set var="constantFlag" value="Menu"/>
<c:set var="currentLabel" value="菜单"/>
<c:if test="${'Resource' eq param.currentFlag}">
    <c:set var="constantFlag" value="Resource" />
    <c:set var="currentLabel" value="资源"/>
</c:if>
<c:if test="${'Action' eq param.currentFlag}">
    <c:set var="constantFlag" value="Action"/>
    <c:set var="currentLabel" value="操作"/>
</c:if>
<c:if test="${'WorkflowStart' eq param.currentFlag}">
    <c:set var="constantFlag" value="WorkflowStart"/>
    <c:set var="currentLabel" value="可发起流程"/>
</c:if>
<c:if test="${'WorkflowView' eq param.currentFlag}">
    <c:set var="constantFlag" value="WorkflowView"/>
    <c:set var="currentLabel" value="可查看流程"/>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${currentLabel}信息管理</title>
	<%@include file="/base/mini.jsp"%>
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
	<script type='text/javascript'>
	  var isWorkflowOper = false;
	</script>
	<c:if test="${('WorkflowStart' eq param.currentFlag)}">
		<script type='text/javascript'>
		  var isWorkflowOper = true;
		</script>
	</c:if>
	<c:if test="${('WorkflowView' eq param.currentFlag)}">
		<script type='text/javascript'>
		  var isWorkflowOper = true;
		</script>
	</c:if>
	<script type="text/javascript">
	   var picJson = <%=FileUtil.getChildrenFileNameJson(WebUtil.getWebContextRealPath()+"\\menuIcons\\")%>;
	   var bigPicJson = <%=FileUtil.getChildrenFileNameJson(WebUtil.getWebContextRealPath()+"\\menuIcons_b\\")%>;
	   var currentPicInputId = null;
	   jQuery(function(){
	        for(var pic in picJson)
	        {
	            jQuery("#id_pictureContainer").append("<div class='picItem'><a href='javascript:void(0)' ondblclick='getSelectedPicture(\""+pic+"\",\"id_pictureContainer\")'><img width='22px' height='22px' src='${pageContext.request.contextPath}/menuIcons/"+pic+"'/></a><div>");
	        }
	        for(var pic in bigPicJson){
	        	jQuery("#id_bigPictureContainer").append("<div class='picItem'><a href='javascript:void(0)' ondblclick='getSelectedPicture(\""+pic+"\",\"id_bigPictureContainer\")'><img width='22px' height='22px' src='${pageContext.request.contextPath}/menuIcons_b/"+pic+"'/></a><div>");
	        }
	   });
	   function getSelectedPicture(picSrc,formId)
	   {
		   if(currentPicInputId)
		   {
			   jQuery("#"+currentPicInputId).val(picSrc);
		   }

		   //jQuery("#id_pictureContainer").dialog("close");
		   jQuery("#" + formId).dialog("close");
	   }
	    function showIconPictureWindow(e,formId)
	    {
	        jQuery("#" + formId).show();
	    	jQuery("#" + formId).dialog({
	        	left:(e.pageX||e.clientX),
	        	top:20,//(e.pageY||e.clientY+5),
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
	    	jQuery("#" + fromId).dialog("open");
	    }
	</script>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
    var constantFlag = "${constantFlag}";
    var operFlag = "";
    var operPromit = "";
    var loadMask_add = null;
    var loadMask_update = null;
    var loadMask_remove = null;
    var loadMask_reset = null;
    var loadMask_enabled = null;
    var windowContainerId = "id_"+constantFlag.toLowerCase()+"DetailInfoWindowContianer";
    var formId  =  "id_"+constantFlag.toLowerCase()+"DetailInfoForm";
	var constantLabelId ="id_"+constantFlag.toLowerCase()+"ConstantLabel";
    function resetForm(){
    	 clearForm($("#"+formId)[0]);
    	 $("#"+formId+" input[name='isRelativedPath'][value='true']:radio ").attr("checked", true);
    	 $("#"+formId+" input[name='isShowOnApp'][value='false']:radio ").attr("checked", true);
    	 //$("#"+formId+" input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
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
        $("#"+formId+" input[name='name']").val(currentMenuNode.attributes['name']);
        $("#"+formId+" input[name='icon']").val(currentMenuNode.attributes['icon']);
        $("#"+formId+" input[name='url']").val(currentMenuNode.attributes['url']);
        if($("#"+formId+" input[name='iconOpen']").length > 0 ){
        	$("#"+formId+" input[name='iconOpen']").val(currentMenuNode.attributes['iconOpen']);
    	}
        
        $("#"+formId+" textarea[name='description']").val(currentMenuNode.attributes['description']);
        var isRelativedPath = currentMenuNode.attributes['isRelativedPath']+"";
        if(!isRelativedPath){
        	isRelativedPath = "true";
        }
	  $("#"+formId+" input[name='isRelativedPath'][value='"+isRelativedPath+"']:radio").attr("checked",true);
        $("#"+formId+" input[name='isShowOnApp'][value='"+isShowOnApp+"']:radio").attr("checked",true);;
        var isShowOnApp = currentMenuNode.attributes['isShowOnApp']+"";
        
        $("#"+formId+" input[name='isShowOnApp'][value='"+isShowOnApp+"']:radio").attr("checked",true);
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
        return Validator.Validate(jQuery("#"+formId)[0],1,false);
    }
    function addMenu(){
    	resetForm();
		 var comboMenuPosition = getTracywindyObject("id_comboMenuPosition");
		 comboMenuPosition.rawValue="最后";
		 comboMenuPosition.value="";
		 jQuery("#"+constantLabelId).hide();
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
    	$("#"+constantLabelId).hide();
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
		$window.dialog({
              top:20,
              closable:false,
              draggable:false
        });
		$window.dialog('open');
    }
    function userOperation(){
    	if(!validateForm(formId))return;
        if(!window.confirm("确认{0}当前${currentLabel}么？".replace("{0}",operPromit)))return;
    	//用户增删改查操作
        {
            var url ="${pageContext.request.contextPath}/acl/{0}.acl".replace("{0}",operFlag+constantFlag);
            var loadMaskMsg = "正在{0}${currentLabel}   请稍后... ".replace("{0}",operPromit);
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
           params['isRelativedPath']=$("#"+formId+" input[name='isRelativedPath']:radio:checked").val();
           params['position'] = 0;
           ajaxRequest({
                url:url,
                params:params,
                timeout:30*1000,
                success:function(res){
                    alert("{0}${currentLabel}成功".replace("{0}",operPromit));
                    try{$("#"+windowContainerId).dialog('close');}catch(e){}
                    currentLoadMask.hide();
                    submitCallBack();
                }
           });
        }
    }
	var loadMask = null;
	
    var isMenuLoaded = false;
    var isUserLoaded = false;
    var isGroupLoaded = false;
    
	jQuery(function(){
		if(!isWorkflowOper)
		{
		    document.getElementById('id_'+constantFlag.toLowerCase()+'Icon').onclick=function(e){
		    	currentPicInputId = this.id;
		    	showIconPictureWindow(getEvent(e),'id_pictureContainer');
			};
		    document.getElementById('id_'+constantFlag.toLowerCase()+'Open').onclick=function(e){
		    	currentPicInputId = this.id;
		    	showIconPictureWindow(getEvent(e),'id_bigPictureContainer');
			};
		    document.getElementById('id_'+constantFlag.toLowerCase()+'Close').onclick=function(e){
		    	currentPicInputId = this.id;
		    	showIconPictureWindow(getEvent(e),'id_pictureContainer');
			};
			//下拉框
			var comboMenuPosition = new tracywindyComboBox({
				lazyLoad:true,
			       dropListHeight:200,
			       id:'id_comboMenuPosition',
			       width:190,
			       name:'currentPosition',
			       renderTo:'id_'+constantFlag.toLowerCase()+'PositionContainer',
			       loadMode:'ajax',
			       readOnly:true,
			       //xmlFileName:'\\combos\\combo'+constantFlag+'Position.xml',
			       xmlFileName:'\\combos\\comboPosition.xml',
			       isCheck:false,
			       leftAdd:1,
			       params:{
			          tablename:("t_"+constantFlag+"s").toUpperCase(),
	                  pid:'-1'
			       },
			       onSelect:function(combo){
			          var currentRawValue = combo.getRawValue();
			          
			          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
			          {
				          jQuery("#"+constantLabelId).hide();
				      }
			          else
			          {
			        	  jQuery("#"+constantLabelId).show();
				      }
			       }
			    });
		}
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
	    isMenuLoaded = false;
	    loadMask.show();
	    //加载树形
	    var curUrl = isWorkflowOper ? ('${pageContext.request.contextPath}/jbpm/getDeployedWorkflowsTreeData.action') : ('${pageContext.request.contextPath}/table/get'+constantFlag+'TreeData.action');
	   
		$('#id_menu_tabs_menu').tree({
			url:curUrl,
			dnd:isWorkflowOper ? false :true ,
			onDrop:function(a,b,c){
			   var toNodeId    = $(a).attr("node-id");
			   var toNode = $('#id_menu_tabs_menu').tree('find',toNodeId);
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
			    	  var childrenNodes = $('#id_menu_tabs_menu').tree('getChildren',toNode.target);
					  var  childrenLen = childrenNodes.length;
					  if(0 == childrenLen){
						  currentPosition = -1;
				      }else{
				    	  currentPosition = childrenNodes[childrenNodes.length-1].attributes['position'];
					  }
				      break;
				  }
			      case  'top':{
			    	  pid = parentNode.id;
			    	  currentPosition = -1;
				      var childrenNodes = $('#id_menu_tabs_menu').tree('getChildren',parentNode.target);
				      for(var i = 0;i<childrenNodes.length;i++){
				    	  var childNode = childrenNodes[i];
				    	  if(toNodeId == childNode.id){
					    	  if(i > 0){
					    		  currentPosition = childrenNodes[i-1].attributes['position'];
						      }
				    		  break;
					      } 
					  }
			    	  break;
				  }
			      case  'bottom':{
			    	  pid = parentNode.id;
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
				if(loadMask){
					loadMask.show();
				}
			    if(isWorkflowOper){
					if(!node){
						params['init'] = true; 
					}
					else{
						params['typeId'] = node.id;
				    }
				}else{
					params['pid']  = node ? node.id : "0";
					if(!node){
						params['init'] = true; 
					}
			    }
			},
			onLoadSuccess:function(a,b){
				isMenuLoaded = true;
				if(isUserLoaded && isGroupLoaded){
					loadMask.hide();
			    };
		    },
		    onContextMenu:function(event,node){
				node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
				currentMenuSelectNode = node;
				$(".flagUserSelectContainer").html(node.attributes.name);
		    	//$(".flagUserSelectContainer").html("");
		    	//reloadInitTree();
		    	stopDefault(event);
		    	if(isWorkflowOper){
			    	return;
			    }
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
			    var isType = false;
		    	if(!node.attributes)isType=true;
		    	if(isWorkflowOper){
			    	if(node.attributes){
				    	if(!node.attributes["typeId"])isType=true;
				    }
			    }
		    	if(!isType)
			    {
					currentMenuSelectNode = node;
					$(".flagUserSelectContainer").html(node.attributes.name);
					reloadOperTree();
			    }

			}
		});
   });
</script>
<script type="text/javascript">
	var pageWidth  = document.documentElement.clientWidth;
	var pageHeight = document.documentElement.clientHeight-10;
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
		loadMask.show();
		reloadInitTree();
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
	    //加载树形
	    isUserLoaded = false;
		$('#id_menu_main_content_div').tree({
			url:'${pageContext.request.contextPath}/table/get'+constantFlag+'DeptAllTreeData.action',
			checkbox:false,
			onLoadSuccess:function(a,b){
				isUserLoaded = true;
				if(isMenuLoaded && isGroupLoaded){
					loadMask.hide();
			    };
		    },
			onBeforeLoad:function(node,params){
				if(loadMask){
					loadMask.show();
				}
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
		    }
		});
	    //加载树形
	    isGroupLoaded = false;
		$('#id_menu_main_content_div_right').tree({
			url:'${pageContext.request.contextPath}/table/get'+constantFlag+'GroupTreeData.action',
			checkbox:false,
			onLoadSuccess:function(a,b){
				isGroupLoaded = true;
				if(isMenuLoaded && isUserLoaded){
					loadMask.hide();
			    };
		    },
			onBeforeLoad:function(node,params){
				if(loadMask){
					loadMask.show();
				}
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
			}
		});
   }
   var $tree = $('#id_menu_tabs_menu').tree;
   function recursionChecked(rootNode,checked){
	   var childrenNodes =  $tree('getChildren',rootNode.target);
       for(var i=0;i<childrenNodes.length;i++){
           var childrenNode = childrenNodes[i];
           var type = childrenNode.attributes["type"];
           if("dept"==type){
               var $checkbox = $(childrenNode.target).find("span.tree-checkbox");
               if(checked){
            	   //$tree("check"  ,childrenNode.target);
            	   $checkbox.removeClass("tree-checkbox0");
            	   $checkbox.addClass("tree-checkbox1");
               }else{
            	   //$tree("uncheck",childrenNode.target);
            	   $checkbox.removeClass("tree-checkbox1");
            	   $checkbox.addClass("tree-checkbox0");
               }
               recursionChecked(childrenNode,checked);
           }
       }
   }
   function reloadOperTree(){
	    //加载树形
	    isUserLoaded = false; 
	    loadMask.show();
		$('#id_menu_main_content_div').tree({
			url:'${pageContext.request.contextPath}/table/get'+constantFlag+'DeptAllTreeData.action',
			checkbox:true,
			cascadeCheck:false,
			onBeforeLoad:function(node,params){
				if(loadMask){
					loadMask.show();
				}
				params['pid']  = node ? node.id : "0";
				params['menuId']  = currentMenuSelectNode.id;
				if(!node){
					params['init'] = true; 
				}
		    },
            onLoadSuccess:function(a,b){
				/*$("#id_menu_main_content_div span.tree-checkbox").click(function(e){
					distribute(currentCheckedNode);
					var checked = false;
					var currentTarget = e.currentTarget||e.target;
					if(currentTarget.className.indexOf("tree-checkbox1")>-1){
						checked = true;
					}
					recursionChecked(currentCheckedNode,checked);
				});*/
				$("#id_menu_main_content_div span.tree-checkbox").each(function(i){
					this.onclick=function(e){
						var currentTarget = getTarget(e);
						var nodeId = (currentTarget.parentNode.getAttribute("node-id"));
						currentCheckedNode = $('#id_menu_main_content_div').tree('find',nodeId);
						distribute(currentCheckedNode);
						var checked = false;
						if(currentTarget.className.indexOf("tree-checkbox1")>-1){
							checked = true;
						}
						recursionChecked(currentCheckedNode,checked);
					};
				});
				isUserLoaded = true;
				if(isMenuLoaded && isGroupLoaded){
					loadMask.hide();
			    };
		    },
			onCheck:function(node,checked){
		    	currentCheckedNode = node;
		    }
		});
	    //加载树形
	    isGroupLoaded = false; 
	    loadMask.show();
		$('#id_menu_main_content_div_right').tree({
			url:'${pageContext.request.contextPath}/table/get'+constantFlag+'GroupTreeData.action',
			checkbox:true,
			cascadeCheck:false,
			onlyLeafCheck:true,
			onBeforeLoad:function(node,params){
				if(loadMask){
					loadMask.show();
				}
				params['pid']  = node ? node.id : "0";
				params['menuId']  = currentMenuSelectNode.id;
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
				isGroupLoaded = true;
				if(isMenuLoaded && isUserLoaded){
					loadMask.hide();
				};
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
		var firstLetter = constantFlag.substring(0,1);
		var dealField = constantFlag.replace(firstLetter,firstLetter.toLowerCase());
        var entityClassName = node.attributes[dealField+"EntityClassName"];
        var leftFieldName = dealField;
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
</script>
</head>
<body>
	<div id='menu_main_content' class="mini-panel" title="${currentLabel}列表" style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border-top:0px;border-right:0px;padding:5px;">
       <div id="id_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;" oncontextmenu='cancelBubble(event);' ></div>
	</div>
	<div id='menu_main_oper' class="mini-panel" title="部门-角色-人员&nbsp;分配" style="overflow:hidden;float:left;border-bottom:1px solid #DDD;padding:5px;">
	   <div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<div id='menu_main_oper_right' class="mini-panel" title="群组&nbsp;分配" style="overflow:hidden;float:left;border:1px solid #DDD;border-left:0px;border-top:0px;padding:5px;">
	   <div id='id_menu_main_content_div_right' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<script type='text/javascript'>
	    var heightAdd = -2;
	    var widthAdd  = -358;
		jQuery("#menu_main_content").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div").css("height",(pageHeight+heightAdd-40)+"px");
		jQuery("#id_menu_main_content_div_right").css("height",(pageHeight+heightAdd-40)+"px");
		
		jQuery("#menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper_right").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper").css("width",((pageWidth + widthAdd)/2 )+"px");
		jQuery("#menu_main_oper_right").css("width",((pageWidth + widthAdd)/2 )+"px");
	</script>
	<div id="id_menuDetailInfoWindowContianer"  buttons="#id-dlg-buttons-menu" closed="true" modal="true" title="菜单信息管理" style="display:none;width:420px;padding-top:10px;height:360px;">
	        <center>
			  <form id="id_menuDetailInfoForm">
			        <table style="width:90%;">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentMenu" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">菜单名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="菜单名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">菜单编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="菜单编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">菜单url:      </td><td  colspan=2 class="input_value"><input  type="text" name="url" /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">&nbsp;&nbsp;</td><td colspan=2 >
			             <input type="radio" name="isRelativedPath" value="true"/>相对路径&nbsp;&nbsp;
			             <input type="radio" name="isRelativedPath" value="false" />绝对路径</td>
			            </tr>
			             <tr><td class="input_label_desc">是否在app显示：</td><td colspan=2 >
			             <input type="radio" name="isShowOnApp" value="true"/>是&nbsp;&nbsp;
			             <input type="radio" name="isShowOnApp" value="false" />否</td>
			            </tr>
			            <!-- 5.0 -->
			            <tr><td class="input_label_desc">菜单图标:     </td><td  colspan=2 class="input_value"><input  type="text" name="icon" id="id_menuIcon" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">菜单地图图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconOpen" id="id_menuOpen" readOnly /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">菜单关闭图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconClose" id="id_menuClose" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">菜单描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			             <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr  ><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_menuConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <!--  
			            <tr class="content-separator">
				            <td colspan=3>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_menuDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
				            </td>
			            </tr>
			            -->
			        </table>
		        </form>
	        </center>
	</div>
	<div style="text-align:center;width:400px;display:none;height:40px;line-height:40px;" id="id-dlg-buttons-menu">
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='userOperation();'><span>确定</span></a>
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_menuDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
	</div>
		<div id="id_resourceDetailInfoWindowContianer"  buttons="#id-dlg-buttons-resource"  closed="true" modal="true" title="资源信息管理" style="display:none;width:400px;padding-top:10px;height:340px;">
	        <center>
			  <form id="id_resourceDetailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentResource" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">资源名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="资源名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">资源编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="资源编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">资源url:      </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="资源 URL"  name="url" /><span class="content-required">*</span></td></tr>
			            <tr style="display:none"><td class="input_label_desc">&nbsp;&nbsp;</td><td colspan=2 >
			             <input type="radio" name="isRelativedPath" value="true"/>相对路径&nbsp;&nbsp;
			             <input type="radio" name="isRelativedPath" value="false" />绝对路径</td>
			            </tr>
			            <tr><td class="input_label_desc">资源图标:     </td><td  colspan=2 class="input_value"><input  type="text" name="icon" id="id_resourceIcon" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">资源打开图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconClose" id="id_resourceOpen" readOnly /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">资源关闭图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconOpen" id="id_resourceClose" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">资源描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			             <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr  ><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_resourcePositionContainer"></span><span style="float:left;" id="id_resourceConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <!--  
			            <tr class="content-separator">
				            <td colspan=3>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_resourceDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
				            </td>
			            </tr>
			            -->
			        </table>
		        </form>
	        </center>
	</div>
	<div style="text-align:center;width:400px;display:none;height:40px;line-height:40px;" id="id-dlg-buttons-resource">
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='userOperation();'><span>确定</span></a>
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_resourceDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
	</div>
			<div id="id_actionDetailInfoWindowContianer"  buttons="#id-dlg-buttons-action"  closed="true" modal="true" title="操作信息管理" style="display:none;width:400px;padding-top:10px;height:340px;">
	        <center>
			  <form id="id_actionDetailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentAction" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">操作名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="操作名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">操作编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="操作编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr style="display:none"><td class="input_label_desc">操作url:      </td><td  colspan=2 class="input_value"><input  type="text" name="url" /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none"><td class="input_label_desc">&nbsp;&nbsp;</td><td colspan=2 >
			             <input type="radio" name="isRelativedPath" value="true"/>相对路径&nbsp;&nbsp;
			             <input type="radio" name="isRelativedPath" value="false" />绝对路径</td>
			            </tr>
			            <tr><td class="input_label_desc">操作图标:     </td><td  colspan=2 class="input_value"><input  type="text" name="icon" id="id_actionIcon" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">操作打开图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconClose" id="id_actionOpen" readOnly /><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr style="display:none;"><td class="input_label_desc">操作关闭图标: </td><td  colspan=2 class="input_value"><input  type="text" name="iconOpen" id="id_actionClose" readOnly/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">操作描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			             <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr  ><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_actionPositionContainer"></span><span style="float:left;" id="id_actionConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <!--  
			            <tr class="content-separator">
				            <td colspan=3>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_actionDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
				            </td>
			            </tr>
			            -->
			        </table>
		        </form>
	        </center>
	</div>
	<div style="text-align:center;width:400px;display:none;height:40px;line-height:40px;" id="id-dlg-buttons-action">
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='userOperation();'><span>确定</span></a>
		  <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_actionDetailInfoWindowContianer").dialog("close");'><span>取消</span></a>
	</div>
	<div id="id_contextmenu" class="easyui-menu" style="display:none;width:120px;">
		<div iconCls="icon-plus-w" onclick='addMenu();'>添加</div>	
		<div iconCls="icon-cogs-w" onclick='updateMenu();'>修改</div>
		<div iconCls="icon-minus-w" onclick='removeMenu();'>删除</div>
	</div>
	<div id="id_pictureContainer" style="display:none;text-align:center;width:300px;height:300px;overflow:auto;"  closed="true" modal="true" title="图片选择器"></div>
	<div id="id_bigPictureContainer" style="display:none;text-align:center;width:300px;height:300px;overflow:auto;"  closed="true" modal="true" title="图片选择器"></div>
</body>
</html>
