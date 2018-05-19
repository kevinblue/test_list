<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据字典信息管理</title>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
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
    var constantFlag = "Dictionary";
    var operFlag = "";
    var operPromit = "";
    var loadMask_add = null;
    var loadMask_update = null;
    var loadMask_remove = null;
    var loadMask_reset = null;
    var loadMask_enabled = null;
    var windowContainerId = "id_menuDetailInfoWindowContainer";
    var formId  =  "id_menuDetailInfoForm";
    function resetForm(){
    	 //$("#"+formId)[0].reset();
    	 clearForm($("#"+formId)[0]);
    	 $("#"+formId+" input[name='isRelativedPath'][value='true']:radio ").attr("checked", true);
    	 $("#"+formId+" input[name='enabled'][value='true']:radio").attr("checked",true);
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
        $("#"+formId+" input[name='name']").val(currentMenuNode.attributes['name']);
        $("#"+formId+" input[name='icon']").val(currentMenuNode.attributes['icon']);
        $("#"+formId+" input[name='url']").val(currentMenuNode.attributes['url']);
        $("#"+formId+" textarea[name='description']").val(currentMenuNode.attributes['description']);
        $("#"+formId+" input[name='enabled'][value='"+currentMenuNode.attributes['enabled']+"']:radio").attr("checked",true);
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
        if(!window.confirm("确认{0}当前数据字典么？".replace("{0}",operPromit)))return;
    	//用户增删改查操作
        {
            var url ="${pageContext.request.contextPath}/acl/{0}.acl".replace("{0}",operFlag+constantFlag);
            var loadMaskMsg = "正在{0}数据字典   请稍后... ".replace("{0}",operPromit);
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
                    alert("{0}数据字典成功".replace("{0}",operPromit));
                    try{$("#"+windowContainerId).window('close');}catch(e){}
                    currentLoadMask.hide();
                    submitCallBack();
                }
           });
        }
    }
	var currentOperTree = "";
	var currentMenuNode = null;
	function loadTreeMenu(loadMask){
	    loadMask.show();
	    //加载树形
		$('#id_menu_tabs_menu').tree({
			url:'${pageContext.request.contextPath}/table/get'+constantFlag+'TreeData.action',
			dnd:true ,
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
					    	  var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',toNode.target);
							  var  childrenLen = chidrenNodes.length;
							  if(0 == childrenLen){
								  currentPosition = -1;
						      }else{
						    	  currentPosition = chidrenNodes[chidrenNodes.length-1].attributes['position'];
							  }
							  text = "第一位";
						      break;
						  }
					      case  'top':{
					    	  pid = parentNode.id;
					    	  currentPosition = -1;
						      var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',parentNode.target);
						      for(var i = 0;i<chidrenNodes.length;i++){
						    	  var childNode = chidrenNodes[i];
						    	  if(toNodeId == childNode.id){
							    	  if(i > 0){
							    		  currentPosition = chidrenNodes[i-1].attributes['position'];
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
				params['pid']  = node ? node.id : "0";
				if(!node){
					params['init'] = true;
					var queryText = document.getElementById("id_queryWorkflowsTableInput").value;
					if(queryText+""){
						params['queryText'] = queryText.toUpperCase(); 
					} 
				}
			},
			onLoadSuccess:function(a,b){
				if(b)loadMask.hide();
		    },
		    onContextMenu:function(event,node){
				node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
				currentMenuSelectNode = node;
				$(".flagUserSelectContainer").html(node.text);
				
				currentOperTree = "menuTree";
				currentMenuNode = node;
				jQuery("#id_queryDictDataTableInput").val('');
				var title ="（<b><font color='#961433'>"+node.text.substring(0,node.text.indexOf("["))+"</font>）</b>"+"目录数据";
				jQuery("#id_titleDictData").html(title);
				var table = getTracywindyObject('id_table');
				table.enabledToolsByText("新增,修改,删除"); 
				table.setParams({pid:node.id,queryText:''});
				table.reload();
				
		    	stopDefault(event);
				var x = event.clientX;
				var y = event.clientY;
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
				currentMenuNode = node;
				var title ="（<b><font color='#961433'>"+node.text.substring(0,node.text.indexOf("["))+"</font>）</b>"+"目录数据";
				jQuery("#id_titleDictData").html(title);
				var table = getTracywindyObject('id_table');
				jQuery("#id_queryDictDataTableInput").val('');
				table.enabledToolsByText("新增,修改,删除"); 
				table.setParams({pid:node.id,queryText:''});
				table.reload();
			}
		});
	}
	var loadMask = null;
	jQuery(function(){
		if(null == loadMask){
			loadMask = new tracywindyLoadMask(document.body,"数据加载中 请稍后...");
	    }
		loadMask.show();
		loadTreeMenu(loadMask);
		
		//目录下拉框
		var comboMenuPosition = new tracywindyComboBox({
			lazyLoad:true,
		       dropListHeight:200,
		       id:'id_comboMenuPosition',
		       width:190,
		       name:'currentPosition',
		       isContainEmpty:false,
		       renderTo:'id_menuPositionContainer',
		       loadMode:'ajax',
		       readOnly:true,
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
		//数据下拉框
		var comboDictionaryDataPosition = new tracywindyComboBox({
		       dropListHeight:200,
		       id:'id_comboMenuDataPosition',
		       width:190,
		       renderTo:'id_menuDataPositionContainer',
		       loadMode:'ajax',
		       readOnly:true,
		       isContainEmpty:false,
		       name:'currentPosition',
		       xmlFileName:'/combos/comboDictionaryDataPosition.xml',
		       isCheck:false,
		       onSelect:function(combo){
		          var currentRawValue = combo.getRawValue();
		          
		          if(("第一位" == currentRawValue)||("最后" == currentRawValue))
		          {
			          jQuery("#id_menuDataConstantLabel").hide();
			      }
		          else
		          {
		        	  jQuery("#id_menuDataConstantLabel").show();
			      }
		       }
		    });
   });
	jQuery(function(){
		var table= new tracywindyOperationTable({
	   		tableComment:'字典数据',
	   		isShortPage:true,
	   		constantFlagTable:'DictionaryData',
	   		addValidate : function(table,$form){
		   		 if(!currentMenuNode){
			   		  alert("请先选中左侧所属数据目录!");
			   		  return false;
			     }
	   		   return true;
	   		},
	   		resetFormCallback:function(table,$form,operFlag){
		   		if("add" == operFlag){
					 var comboMenuPosition = getTracywindyObject("id_comboMenuDataPosition");
					 comboMenuPosition.rawValue="最后";
					 comboMenuPosition.value="";
					 jQuery("#id_menuDataConstantLabel").hide();
					 var pid = currentMenuNode.id;
					 comboMenuPosition.setParams({
				            pid:pid
					     });
					 comboMenuPosition.reload();
			   	}
		    	 $form.find("input[name='belongDictionary']").val(currentMenuNode.id);
		    	 $form.find("input[name='isMust'][value='false']:radio").attr("checked",true);
		    	 $form.find("input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
		    	 $("#id_dictName").html(currentMenuNode.attributes.name);
	   		},
	   		validateFrom:function(table,$form){
		   		 if(!currentMenuNode){
			   		  alert("请先选中左侧所属数据目录!");
			     }
			     return  true;
		    },
		    loadFormDataCallBack:function(table,$form,rowIndex){
		    	 $form.find("input[name='position']").val("0");

		    	 var currentRowData = table.getRowDataAt(rowIndex);
				 var comboMenuDataPosition = getTracywindyObject("id_comboMenuDataPosition");
				 comboMenuDataPosition.value=currentRowData['position'];
				 jQuery("#id_menuDataConstantLabel").show();
				 var pid = currentRowData['belongdictionary'];
				 comboMenuDataPosition.setParams({
			            pid:pid
				 });
				 comboMenuDataPosition.reload();
			},
			isNeedEnabled:true,
            renderTo:'id_menu_main_content_div',
            width:pageWidth-433+100,
            height:pageHeight+7,
            title:'<font id="id_titleDictData">目录数据</font><input type="text" style="margin-right:4px;border:1px solid #DDD;" id="id_queryDictDataTableInput" value="${param.queryDictDataText}" />',
            isRank:true,
            rankSize:30,
            id:'id_table',
            checkOnly:true,
            isAutoBreakContent:true,
            toolsAdd:1.4,
            isPage:true,
            isFit:false,
            border:true,
            columns:[{
                header:'记录编号',
                name:'id',
                hidden:true
	        },{
                header:'数据位置',
                name:'position',
                hidden:true
	        },{
                header:'目录编号',
                name:'belongdictionary',
                hidden:true
	        },{
                header:'所属目录',
                name:'dictname'
	        },{
                header:'数据名称',
                name:'name'
	        }
	        ,{
                header:'英文名称',
                name:'enname'
	        },
	        {
                header:'数据编号',
                name:'code'
	        },{
                header:'数据描述',
                name:'description'
	        },{
                header:'数据性质',
                name:'character'
	        },{
                header:'数据等级',
                name:'gradelevel'
	        },{
                header:'是否必需',
                name:'ismustdesc'
	        },{
                header:'是否必需',
                name:'ismust',
                hidden:true
	        },{
                header:'属性一',
                name:'propone'
	        },{
                header:'属性二',
                name:'proptwo'
	        },{
                header:'属性三',
                name:'propthree'
	        },{
                header:'属性四',
                name:'propfour'
	        },{
                header:'属性五',
                name:'propfive'
	        }],
            xmlFileName:'\\acl\\queryDictDatas.xml',
            loadMode:'ajax',
            isPage:true,
            isExcel:true,
            params:{
                pid:'-1'
	        }
         });
		 document.getElementById("id_queryDictDataTableInput").onkeypress = function(evt){
			 var e  = getEvent(evt);
		     var code = e.keyCode||e.charCode;
		     if(13 == code){
		         var workflowsTable = getTracywindyTable("id_table");
		         jQuery("#id_titleDictData").html("目录数据");
		         workflowsTable.disabledToolsByText("新增,修改,删除"); 
		         workflowsTable.params={queryText:this.value.toUpperCase()};
		         workflowsTable.reload();
		     }
		 };
	});
</script>
</head>
<body>
	<div id='menu_main_content' style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border:0px;">
	   <div class="panel-title" style="margin-top:0px;border-top:0px;">数据字典管理<input type="text" style="margin-left: 4px; margin-top: 5px;border:1px solid #DDD;" id="id_queryWorkflowsTableInput" value="${param.queryText}" /></div>
       <div id="id_menu_tabs_menu" style="border:1px solid #DDD;border-top:0px;overflow:auto;padding:5px;" oncontextmenu='cancelBubble(event);' ></div>
	</div>
	<div id='menu_main_oper' style="overflow:hidden;float:left;border:1px solid #DDD;border:0px;margin-left:5px;">
	   <div id='id_menu_main_content_div' ></div>
	</div>
	<script type='text/javascript'>
	    var heightAdd = 0;
	    var widthAdd  = -448;
		//jQuery("#menu_main_content").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight+heightAdd-34)+"px");
		//jQuery("#id_menu_main_content_div").css("height",(pageHeight+heightAdd-40)+"px");
		
		/*jQuery("#menu_main_oper").css("height",(pageHeight+heightAdd)+"px");
		jQuery("#menu_main_oper").css("width",(pageWidth + widthAdd)+"px");*/
		 document.getElementById("id_queryWorkflowsTableInput").onkeypress = function(evt){
			 var e  = getEvent(evt);
		     var code = e.keyCode||e.charCode;
		     if(13 == code){
		    	 loadTreeMenu(loadMask);
		     }
		 };
	</script>
	<div id="id_menuDetailInfoWindowContainer"  closed="true" modal="true" title="数据字典信息管理" style="display:none;width:400px;height:320px;padding-top:20px;">
	        <center>
			  <form id="id_menuDetailInfoForm">
			        <table style="width:98%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentDictionary" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">数据字典名称:     </td><td  colspan=1 class="input_value"><input  type="text" require="true" label="数据字典名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据字典编号:     </td><td  colspan=1 class="input_value"><input  type="text" require="true" label="数据字典编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据字典描述:     </td><td  colspan=1 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td colspan=2><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td class="input_label_desc">是否启用:         </td><td  colspan=1 style="text-align:center;"><input name="enabled" type="radio" value="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="enabled" type="radio" value="false"/>否</td></tr>
			            <tr><td colspan=2><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=2><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td class="input_label_desc"><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span></td><td><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_constantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr class="content-separator">
				            <td colspan=2>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_menuDetailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="字典数据管理" style="display:none;width:400px;height:380px;padding-top:5px;">
	        <center>
		        <form id="id_detailInfoForm">
		        <table>
		        		<tr style="display:none"><td><input name="id" type="hidden" value="" /></td></tr>
			            <tr style="display:none"><td><input name="belongDictionary" type="hidden" value="" require="true" label="数据目录"/></td></tr>
			            <tr style="display:none"><td><input name="position" type="hidden" value="0"/></td></tr>
			            <tr><td class="input_label_desc">数据目录:     </td><td><label id="id_dictName" class="content-label"></label></td></tr>
			            <tr><td class="input_label_desc">数据名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">英文名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="英文名称"  name="enname"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据编号"  name="code"/><span class="content-required">*</span></td></tr>
		        </table> 
		        <div id='id_project_info_tabs_content' class="easyui-tabs" >
		           <div title="基本属性" style="padding-left:50px;">
			        <table style="width:94%">
			        <!--  
			            <tr style="display:none"><td><input name="id" type="hidden" value="" /></td></tr>
			            <tr style="display:none"><td><input name="belongDictionary" type="hidden" value="" require="true" label="数据目录"/></td></tr>
			            <tr style="display:none"><td><input name="position" type="hidden" value="0"/></td></tr>
			            <tr><td class="input_label_desc">数据目录:     </td><td><label id="id_dictName" class="content-label"></label></td></tr>
			            <tr><td class="input_label_desc">数据名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据编号"  name="code"/><span class="content-required">*</span></td></tr>
			        -->    
			            <tr><td class="input_label_desc">数据描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">数据性质:     </td><td  colspan=2 class="input_value"><input  type="text" name="character"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">数据等级:     </td><td  colspan=2 class="input_value"><input  type="text" name="gradeLevel"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td class="input_label_desc">是否必需:     </td><td  colspan=2 style="text-align:center;"><input name="isMust" type="radio" value="true"/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="isMust" type="radio" value="false"/>否</td></tr>
			            <!-- 
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td  class="input_label_desc" ><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span></td><td colspan=3><span style="float:left;" id="id_menuDataPositionContainer"></span><span style="float:left;" id="id_menuDataConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='getTracywindyObject("id_table").operationTable();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			             -->
			        </table>
			        </div>
					<div title="附加属性" style="padding-left:50px;">
				        <table style="width:94%">
				            <tr><td class="input_label_desc">属性一:     </td><td  class="input_value"><input  type="text" require="false" label="属性一"  name="propOne"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				            <tr><td class="input_label_desc">属性二:     </td><td  class="input_value"><input  type="text" require="false" label="属性二"  name="propTwo"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				            <tr><td class="input_label_desc">属性三:     </td><td  class="input_value"><input  type="text" require="false" label="属性三"  name="propThree"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				            <tr><td class="input_label_desc">属性四:     </td><td  class="input_value"><input  type="text" require="false" label="属性四"  name="propFour"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				            <tr><td class="input_label_desc">属性五:     </td><td  class="input_value"><input  type="text" require="false" label="属性五"  name="propFive"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				        </table>
			        </div>
			        </div>
			        <table>
			           	<tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td  class="input_label_desc" ><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span></td><td colspan=3><span style="float:left;" id="id_menuDataPositionContainer"></span><span style="float:left;" id="id_menuDataConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan='2'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='getTracywindyObject("id_table").operationTable();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_detailInfoWindowContainer").window("close");'><span>取消</span></a>
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
	<div id="id_pictureContainer" style="display:none;text-align:center;width:300px;height:300px;overflow:auto;"  closed="true" modal="true" title="图片选择器"></div>
</body>
</html>