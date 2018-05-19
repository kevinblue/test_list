<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限分配</title>
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
   });
function initDeptTree(){
	loadMask.show();
    //加载树形
	$('#id_menu_tabs_menu').tree({
		url:'${pageContext.request.contextPath}/table/getDeptWithUserTreeData.action',
		onBeforeLoad:function(node,params){
			params['pid']  = node ? node.id : "0";
			if(!node){
				params['init'] = true; 
			}
		    var queryText = jQuery("#id_queryDeptsTableInput").val().toUpperCase();
			if(queryText && isQueryDeptFlag){
				params['queryText'] = queryText;
			}
		},
		onLoadSuccess:function(a,b){
		    //isDeptLoaded = true;
		    isQueryDeptFlag = false;
	    },
	    onClick:function(node){
			node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
		    if( 'user' != node.attributes['type']){
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
		initDeptTree();
		//loadMask.hide();
   });
   var currentCheckedNode = null;
   var isDeptRoleLoaded = false;
   var isGroupLoaded = false;
   function reloadInitTree(){
	   isDeptRoleLoaded = false;
	    //加载树形
		$('#id_menu_main_content_div').tree({
			url:'${pageContext.request.contextPath}/table/getUserDeptAllTreeData.action',
			checkbox:false,
			onLoadSuccess:function(a,b){
				isDeptRoleLoaded = true;
				if(isGroupLoaded){
					loadMask.hide();
			    };
	       },
			onBeforeLoad:function(node,params){
	    	    loadMask.show();
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
		    }
		});
		isGroupLoaded = false;
	    //加载树形
		$('#id_menu_main_content_div_right').tree({
			url:'${pageContext.request.contextPath}/table/getUserGroupTreeData.action',
			checkbox:false,
			onLoadSuccess:function(a,b){
				isGroupLoaded = true;
				if(isDeptRoleLoaded){
					loadMask.hide();
			    };
       		},
			onBeforeLoad:function(node,params){
       			loadMask.show();
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true; 
				}
			}
		});
   }
   function reloadOperTree(){
	    isDeptRoleLoaded = false; 
	    loadMask.show();
	    //加载树形
		$('#id_menu_main_content_div').tree({
			url:'${pageContext.request.contextPath}/table/getUserDeptAllTreeData.action',
			checkbox:true,
			cascadeCheck:false,
			onlyLeafCheck:true,
			onBeforeLoad:function(node,params){
				loadMask.show();
				params['pid']  = node ? node.id : "0";
				params['userId']  = currentMenuSelectNode.id;
				if(!node){
					params['init'] = true; 
				}
		    },
            onLoadSuccess:function(a,b){
				   /* $("#id_menu_main_content_div span.tree-checkbox").click(function(e){
					distribute(currentCheckedNode);
				});*/
				$("#id_menu_main_content_div span.tree-checkbox").each(function(i){
					var treeNodeDiv = this.parentNode;
					var nodeId = (treeNodeDiv.getAttribute("node-id"));
					var currentType = $('#id_menu_main_content_div').tree('find',nodeId).attributes["type"];
					if("dept" == currentType){
						treeNodeDiv.style.display="none";
				    }else{
						this.onclick=function(e){
							var currentTarget = getTarget(e);
							var nodeId = (currentTarget.parentNode.getAttribute("node-id"));
							currentCheckedNode = $('#id_menu_main_content_div').tree('find',nodeId);
							distribute(currentCheckedNode);
							var checked = false;
							if(currentTarget.className.indexOf("tree-checkbox1")>-1){
								checked = true;
							}
						};
				    }
				});
				isDeptRoleLoaded = true;
				if(isGroupLoaded){
					loadMask.hide();
				}
		    },
			onCheck:function(node,checked){
		    	currentCheckedNode = node;
		    }
		});
	    //加载树形
	    isGroupLoaded = false; 
	    loadMask.show();
		$('#id_menu_main_content_div_right').tree({
			url:'${pageContext.request.contextPath}/table/getUserGroupTreeData.action',
			checkbox:true,
			cascadeCheck:false,
			onlyLeafCheck:true,
			onBeforeLoad:function(node,params){
				loadMask.show();
				params['pid']  = node ? node.id : "0";
				params['userId']  = currentMenuSelectNode.id;
				if(!node){
					params['init'] = true; 
				}
			},
			onLoadSuccess:function(a,b){
				$("#id_menu_main_content_div_right span.tree-checkbox").each(function(i){
					this.onclick=function(e){
						var currentTarget = getTarget(e);
						var nodeId = (currentTarget.parentNode.getAttribute("node-id"));
						currentCheckedNode = $('#id_menu_main_content_div_right').tree('find',nodeId);
						distribute(currentCheckedNode);
					};
				});
				isGroupLoaded = true;
				if(isDeptRoleLoaded){
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
        var entityClassName = node.attributes["userEntityClassName"];
        var leftFieldName = "user";
        var leftId = currentMenuSelectNode.id;
        var rightFieldName = node.attributes["type"];        var rightId = node.id;
        
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
</script>
</head>
<body>
	<div id='menu_main_content' style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border-top:0px;border-right:0px;padding:5px;">
	   <div class="panel-title" >用户列表
	      <SPAN>搜索：<INPUT style="BORDER-BOTTOM: #ddd 1px solid; BORDER-LEFT: #ddd 1px solid; WIDTH: 80px; BORDER-TOP: #ddd 1px solid; MARGIN-RIGHT: 4px; BORDER-RIGHT: #ddd 1px solid" id=id_queryDeptsTableInput type="text" jQuery18205254865307538132="34"></SPAN>
	   </div>
       <div id="id_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;" oncontextmenu='cancelBubble(event);' ></div>
	</div>
	<div id='menu_main_oper' style="overflow:hidden;float:left;border-bottom:1px solid #DDD;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;人员角色分配</div>
	   <div id='id_menu_main_content_div' style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;"></div>
	</div>
	<div id='menu_main_oper_right' style="overflow:hidden;float:left;border:1px solid #DDD;border-left:0px;border-top:0px;padding:5px;">
	   <div class="panel-title" ><font color="red" class="flagUserSelectContainer"></font>&nbsp;&nbsp;人员群组分配</div>
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
</body>
</html>
