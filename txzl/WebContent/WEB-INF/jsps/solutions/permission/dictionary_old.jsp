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
    	 $("#"+formId+" input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
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
        $("#"+formId+" input[name='icon']").val(currentMenuNode.icon.substring(currentMenuNode.icon.lastIndexOf("/")+1,currentMenuNode.icon.length));
        $("#"+formId+" input[name='iconClose']").val(currentMenuNode.icon.substring(currentMenuNode.iconClose.lastIndexOf("/")+1,currentMenuNode.icon.length));
        $("#"+formId+" input[name='iconOpen']").val(currentMenuNode.icon.substring(currentMenuNode.iconOpen.lastIndexOf("/")+1,currentMenuNode.icon.length));
        $("#"+formId+" input[name='parent"+constantFlag+"']").val(currentMenuNode.pid);
        $("#"+formId+" input[name='code']").val(currentMenuNode.attributes['code']);
        $("#"+formId+" input[name='isRelativedPath'][value='"+currentMenuNode.attributes['isRelativedPath']+"']:radio").attr("checked",true);;
    }
    function submitCallBack(){
    	loadTreeMenu(loadMask); 
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
		 var pid = ("0" == (""+currentMenuNode.id)) ? "DICT" : (""+currentMenuNode.id) ;
		 comboMenuPosition.setParams({
	            pid:pid
		     });
		 comboMenuPosition.reload();
    	$("#"+formId+" input[name='parent"+constantFlag+"']").val(pid);
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
    	 var position  = currentMenuNode.position;
    	 var nodeIndex = currentMenuNode._ai;
    	 var pid = currentMenuNode.pid;
    	 var _ls = currentMenuNode._ls;//false; //是否是父节点的最后一个子节点
    	 var _hc = currentMenuNode._hc;//false; //是否存在子节点
    	 var _ai = currentMenuNode._ai;//被添加的索引
    	 var _p  = currentMenuNode._p; //父节点
    	 
    	 var comboMenuPosition = getTracywindyObject("id_comboMenuPosition");
    	 if((999999!=position)&&(-1!=position)&&!_ls)
    	 {
    		 var tempChildrenNodes = [];
    		 var childrenIndex = 0;
    		 for(var i=0;i<menuTree.aNodes.length;i++)
    		 {
    			 if(pid == menuTree.aNodes[i].pid)
    			 {
    				 if(position == menuTree.aNodes[i].position)
    				 {
    					 break;
    			     }
    				 tempChildrenNodes.push(menuTree.aNodes[i]);
    			 }
    		 }
    		 if(tempChildrenNodes.length==0)
    		 {
    			 comboMenuPosition.rawValue = "第一位";
    			 jQuery("#id_constantLabel").hide();
    		 }
    		 else
    		 {
    			var previousNode = tempChildrenNodes.pop();
    			comboMenuPosition.rawValue = previousNode.name;
    			jQuery("#id_constantLabel").show();
    		 }
    		 tempChildrenNodes=[];
    	 } 
    	 else
    	 {
    		 if(0==position){comboMenuPosition.rawValue = "第一位";}
    		 if(_ls){comboMenuPosition.rawValue = "最后";}
    		 jQuery("#id_constantLabel").hide();
    	 }
    	 comboMenuPosition.setParams({
            pid:pid
         });
    	 comboMenuPosition.value = "";
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
	menuTree = new dTree('menuTree');
    menuTree.config.useCookies=false;
	// Toggle Open or close
	menuTree.onclick = function(nodeIndex) {
		currentOperTree = "menuTree";
		var cn = this.aNodes[nodeIndex];
		currentMenuNode = cn;
		/*this.nodeStatus(!cn._io, id, cn._ls);
		cn._io = !cn._io;*/
		if (this.config.closeSameLevel) this.closeLevel(cn);
		if (this.config.useCookies) this.updateCookie();
		this.s(nodeIndex);
		var title ="（<b><font color='#961433'>"+cn.name+"</font>）</b>"+"目录数据";
		var table = getTracywindyObject('id_table');
		table.setTitle(title);
		table.setParams({pid:cn.id});
		table.reload();
	};
	menuTree.onrootcontextmenu = function(event,nodeIndex){
		var cn = this.aNodes[nodeIndex];
		currentOperTree = "menuTree";
		currentMenuNode = cn;
		var x = event.clientX;
		var y = event.clientY;
		var menuItems = jQuery("#id_contextmenu div.menu-item");
		menuItems.each(function(i){
			  this.disabled = true;
		});
		menuItems[0].disabled = false;
		jQuery("#id_contextmenu").menu('show',{
			left: event.pageX||x,
			top: event.pageY||y
	    });
	};
	menuTree.oncontextmenu = function(event,nodeIndex){
		var cn = this.aNodes[nodeIndex];
		currentOperTree = "menuTree";
		currentMenuNode = cn;
		var x = event.clientX;
		var y = event.clientY;
		var menuItems = jQuery("#id_contextmenu div.menu-item");
		menuItems.each(function(i){
			  this.disabled = false;
		});
		jQuery("#id_contextmenu").menu('show',{
			left:event.pageX||x,
			top: event.pageY||y
	    });
	};
	//主页管理树结束
	var isFirstLoad = true;
	function loadTreeMenu(loadMask)
	{
		//刷新主页树
		var reloadMenuTree = true;
		if(reloadMenuTree)
		{
			//刷新数据字典树
			menuTree.aNodes=[];
			menuTree.add(0,-1,'系统数据字典');
			var params={};
			params["rootParent"+constantFlag+"Id"] = "DICT";
			ajaxRequest({
			    url:'${pageContext.request.contextPath}/acl/query'+constantFlag+'s.acl',
			    method:'POST',
			    success:function(res){
				        var menuJson = eval("("+res.responseText+")");
				        recursionPageTreeMenu(menuJson,menuTree,true);
				    	jQuery("#id_menu_tabs_menu").html(menuTree.toString());

				    	//加载dtree右键弹出
				    	dTreeContextMenuEvent(menuTree);
				    	menuTree.config.useCookies=true;
				    	if(isFirstLoad)
				    	{
					    	//去掉伸缩按钮
					    	jQuery("#menu_main_content .panel-header .panel-tool").html("");
				    	}
				    	isFirstLoad = false;
				    	try{
				    		getTracywindyObject("id_table").reload();
					    }catch(e){}
				    	
				    	loadMask.hide();
			    },
			    failure:function(res){},
			    async:true,
			    timeout:30*1000,
			    params:params
			});
		}
	}
	//迭代普通数据字典
	function recursionPageTreeMenu(menuJson,tree,isInit)
	{
		var menus = menuJson.children;
		for(var mIndex = 0;mIndex<menus.length;mIndex++)
		{
		   var tbMenu = menus[mIndex];
		   tbMenu.icon      = "home.png";
		   tbMenu.iconClose = "home.png";
		   tbMenu.iconOpen  = "home.png";
		   if(isInit)
		   {
			   tree.add(tbMenu.id,0,tbMenu.name,tbMenu.url,tbMenu.icon,tbMenu.iconClose,tbMenu.iconOpen,tbMenu.description,tbMenu.position,null,tbMenu.attributes);
		   }
		   else
		   {
			   tree.add(tbMenu.id,tbMenu.pid,tbMenu.name,tbMenu.url,tbMenu.icon,tbMenu.iconClose,tbMenu.iconOpen,tbMenu.description,tbMenu.position,null,tbMenu.attributes);
		   }
		   recursionPageTreeMenu(tbMenu,tree,false);
		}
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
			lazyLoad:true,
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
		    	 $form.find("input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
		    	 $("#id_dictName").html(currentMenuNode.name);
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
            width:pageWidth-432+100,
            height:pageHeight+7,
            title:'目录数据',
            isCheck:false,
            isRank:false,
            rankSize:30,
            id:'id_table',
            checkOnly:true,
            checkType:'checkbox',
            toolsAdd:1.4,
            isFit:true,
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
	        },{
                header:'数据编号',
                name:'code'
	        },{
                header:'数据描述',
                name:'description'
	        }],
            xmlFileName:'\\acl\\queryDictDatas.xml',
            loadMode:'ajax',
            isPage:true,
            isExcel:true,
            params:{
                pid:'-1'
	        }
         });
	});
</script>
</head>
<body>
	<div id='menu_main_content' style="float:left;width:325px;overflow:hidden;border:1px solid #DDD;border:0px;">
	   <div class="panel-title" style="margin-top:0px;">数据字典管理</div>
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
	</script>
	<div id="id_menuDetailInfoWindowContainer"  closed="true" modal="true" title="数据字典信息管理" style="display:none;width:500px;padding-top:20px;">
	        <center>
			  <form id="id_menuDetailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
			            <tr style="display:none"><td><input name="parentDictionary" type="hidden" value=""/></td></tr>
			            <tr><td class="input_label_desc">数据字典名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据字典名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据字典编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据字典编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据字典描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:100px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_constantLabel">&nbsp;&nbsp;之后</span></td></tr>
			            <tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
			            <tr class="content-separator">
				            <td colspan=3>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='userOperation();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_menuDetailInfoWindowContainer").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		        </form>
	        </center>
	</div>
	<div id="id_detailInfoWindowContainer"  closed="true" modal="true" title="字典数据管理" style="display:none;width:500px;padding-top:20px;">
	        <center>
		        <form id="id_detailInfoForm">
			        <table style="width:90%">
			            <tr style="display:none"><td><input name="id" type="hidden" value="" /></td></tr>
			            <tr style="display:none"><td><input name="belongDictionary" type="hidden" value="" require="true" label="数据目录"/></td></tr>
			            <tr style="display:none"><td><input name="position" type="hidden" value="0"/></td></tr>
			            <tr><td class="input_label_desc">数据目录:     </td><td><label id="id_dictName" class="content-label"></label></td></tr>
			            <tr><td class="input_label_desc">数据名称:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据字典名称"  name="name"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据编号:     </td><td  colspan=2 class="input_value"><input  type="text" require="true" label="数据字典编号"  name="code"/><span class="content-required">*</span></td></tr>
			            <tr><td class="input_label_desc">数据描述:     </td><td  colspan=2 class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td colspan=3><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
			            <tr><td colspan=3><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
			            <tr><td  class="input_label_desc" colspan=3><span style="float:left;margin-left:100px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuDataPositionContainer"></span><span style="float:left;" id="id_menuDataConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
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
