<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>树转表模型管理</title>
	<%@include file="/base/mini.jsp"%>
	
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree/dtree.js"></script>
	
	<style type="text/css">
		html,body {
			overflow:hidden;
		}
		.picItem {
			float:left;
			padding:5px 5px 5px 5px;
			border:1px solid silver;
		}
	</style>
	<script type="text/javascript">
		var configTableId = "id_table";
		var picJson = <%=FileUtil.getChildrenFileNameJson(WebUtil.getWebContextRealPath()+"\\menuIcons\\")%>;
		var currentPicInputId = null;
		
		jQuery(function(){
			for(var pic in picJson) {
				jQuery("#id_pictureContainerBox").append("<div class='picItem'><a href='javascript:void(0)' ondblclick='getSelectedPicture(\""+pic+"\")'><img width='22px' height='22px' src='${pageContext.request.contextPath}/menuIcons/"+pic+"'/></a><div>");
			}
		});
		function getSelectedPicture(picSrc) {
			if(currentPicInputId) {
				jQuery("#"+currentPicInputId).val(picSrc);
			}
			mini.get('id_pictureContainer').hide();
		}
		function showIconPictureWindow(e) {
			mini.get('id_pictureContainer').show();
		}
	
		var pageWidth = document.documentElement.clientWidth;
		var pageHeight = document.documentElement.clientHeight;
		var constantFlag = "BaseDocumentConfig";
		var operFlag = "";
		var operPromit = "";
		var windowContainerId = "id_menuDetailInfoWindowContainer";
		var formId = "id_menuDetailInfoForm";
		function resetForm(){
			document.getElementById(formId).reset();
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
			$("#"+formId+" input[name='width']").val(currentMenuNode.attributes['width']);
			$("#"+formId+" input[name='height']").val(currentMenuNode.attributes['height']);
			var enabled = currentMenuNode.attributes['enabled'] ;
			var isStatistic = currentMenuNode.attributes['isStatistic'] ;
			$("#enabled"+enabled).attr("checked", true);
			$("#isStatistic"+isStatistic).attr("checked", true);
			$("#"+formId+" textarea[name='description']").val(currentMenuNode.attributes['description']);
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
			
			if(!currentReloadId) {
				currentReloadId = currentMenuNode.id;
			}
			var currentNode = $('#id_menu_tabs_menu').tree('find',currentReloadId);
			$('#id_menu_tabs_menu').tree('reload',currentNode.target);
			$('#id_menu_tabs_menu').tree('expand',currentNode.target);
		}
		function validateForm(){
			return Validator.Validate(jQuery("#"+formId)[0],1,false);
		}
		
		function addMenuIterm(miniTable){
			
			if(currentMenuNode == false || currentMenuNode.state){
				mini.alert("请先选择测试项目选择项！");
				return false;
			}
			var comboMenuPosition = getTracywindyObject("id_comboMenuDataPosition");
			comboMenuPosition.rawValue="最后";
			comboMenuPosition.value="";
			jQuery("#id_menuDataConstantLabel").hide();
			var pid = currentMenuNode.id;
			comboMenuPosition.setParams({
				pid:pid
			});
			comboMenuPosition.reload();
			getTracywindyObject("id_comboMenuDataType").setValue("documentColumnType_null");
			
			$("#id_detailInfoForm input[name='belongDocument']").val(currentMenuNode.id);
			$("#id_detailInfoForm input[name='code']").val((currentMenuNode.attributes['code'] ? currentMenuNode.attributes['code'] : "root") +".");
			$("#id_dictName").html(currentMenuNode.attributes.name);
			
			$("#id_detailInfoForm input[name='id']").val("");
			$("#id_detailInfoForm input[name='name']").val("");
			$("#id_detailInfoForm input[name='value']").val("0");
			$("#id_detailInfoForm textarea[name='description']").val("");
			$("#id_detailInfoForm input[name='width']").val("");
			$("#id_detailInfoForm input[name='height']").val("");
			$("#id_detailInfoForm input[name='enabled']").val("true");
			getTracywindyObject("id_autocomputer").setValue("否");
			mini.get('id_detailInfoWindowContainer').show();
		}
		
		
		function editMenuIterm(miniTable){
			var selectedRowDatas = miniTable.getSelecteds();
			if(selectedRowDatas.length > 0){
				var currentRowData = selectedRowDatas[0];
				var comboMenuDataPosition = getTracywindyObject("id_comboMenuDataPosition");
				var pid = currentRowData.belongdocument;
				comboMenuDataPosition.value = currentRowData['position'];
				comboMenuDataPosition.setParams({
					pid:pid
				});
				comboMenuDataPosition.reload();
				getTracywindyObject("id_comboMenuDataType").setValue(currentRowData.type);
				getTracywindyObject("id_autocomputer").setValue(currentRowData.autocomputer);
				$("#id_detailInfoForm input[name='id']").val(currentRowData.id);
				$("#id_detailInfoForm input[name='name']").val(currentRowData.name);
				$("#id_detailInfoForm input[name='code']").val(currentRowData.code);
				$("#id_detailInfoForm input[name='value']").val(currentRowData.value);
				$("#id_detailInfoForm textarea[name='description']").val(currentRowData.description);
				$("#id_detailInfoForm input[name='width']").val(currentRowData.width);
				$("#id_detailInfoForm input[name='height']").val(currentRowData.height);
				$("#id_detailInfoForm input[name='enabled']").val(currentRowData.enabled);
				$("#id_detailInfoForm input[name='belongDocument']").val(currentMenuNode.id);
				var selectedCombo = getTracywindyObject("id_comboMenuDataIsSelected");
				selectedCombo.setValue(miniTable.getSelected()["isselected"]); 
				selectedCombo.reload();
				
				var requireCombo = getTracywindyObject("id_comboMenuDataIsRequire");
				requireCombo.setValue(miniTable.getSelected()["isrequire"]); 
				requireCombo.reload();
				jQuery("#id_menuDataConstantLabel").show();
				
				$("#id_dictName").html(currentMenuNode.attributes.name);
				mini.get('id_detailInfoWindowContainer').show();
			} else {
				mini.alert('请选择要修改的记录数据');
			}
		}
		
		
		function removeMenuIterm(miniTable){
			var selectedRowDatas = miniTable.getSelecteds();
			if(selectedRowDatas.length > 0){
				var currentRowData = selectedRowDatas[0];
				mini.confirm('确定要删除该条记录么？','删除？',function(action){
					if(action == 'ok'){
						tenwa.ajaxRequest({
							url:"${pageContext.request.contextPath}/acl/removeBaseDocumentConfigData.acl",
							params:currentRowData,
							success:function(res){
								mini.unmask(document.body);
								mini.alert("删除操作成功");
								miniTable.reload();
							},
							failure:function(res){
								mini.unmask(document.body);
								mini.alert("删除操作失败");
							}
						});
					}
				});
			} else {
				mini.alert('请选择要删除的记录数据');
			}
		}

		
		function __saveMenuIterm(){
			var params = {};
			var position = getTracywindyObject("id_comboMenuDataPosition");
			var type = getTracywindyObject("id_comboMenuDataType");
			params.id = $("#id_detailInfoForm input[name='id']").val();
			params.code = $("#id_detailInfoForm input[name='code']").val();
			params.name = $("#id_detailInfoForm input[name='name']").val();
			params.position = position.getValue();
			params.currentPosition = position.getValue();
			params.type = type.getValue();
			params.value = $("#id_detailInfoForm input[name='value']").val();
			params.description = $("#id_detailInfoForm textarea[name='description']").val();
			params.belongDocument = $("#id_detailInfoForm input[name='belongDocument']").val();
			params.width = $("#id_detailInfoForm input[name='width']").val();
			params.height = $("#id_detailInfoForm input[name='height']").val();
			params.enabled = $("#id_detailInfoForm input[name='enabled']").val();
			params.isselected = getTracywindyObject("id_comboMenuDataIsSelected").getValue();
			params.isrequire = getTracywindyObject("id_comboMenuDataIsRequire").getValue();
			params.autocomputer=getTracywindyObject("id_autocomputer").getValue();
			var action = params.id ? 'update' : 'add';
			tenwa.ajaxRequest({
				url:"${pageContext.request.contextPath}/acl/" + action + "BaseDocumentConfigData.acl",
				params:params,
				success:function(res){
					mini.unmask(document.body);
					mini.alert("操作成功",'提示',function(){
						mini.get("id_detailInfoWindowContainer").hide();
						mini.get(configTableId).reload();
					});
				},
				failure:function(res){
					mini.unmask(document.body);
					mini.alert("操作失败");
				}
			});
		}
		function __saveMenuItermClose(){
			mini.get("id_detailInfoWindowContainer").hide();
		}
		
		function addMenu(){
			if(currentMenuNode){
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
				$("#enabledtrue").attr("checked", true);
				$("#isStatistic0").attr("checked", true);
				$("#"+formId+" input[name='id']").val('');
				$("#"+formId+" input[name='parent"+constantFlag+"']").val(currentMenuNode.id);
				showWindow();
				operFlag = "add";
				operPromit = "新增";
			} else {
				mini.alert("请先选定左侧树转表模块。");
			}
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
			var position = currentMenuNode.attributes.position;
			var pid = currentMenuNode.attributes.pid;
			$("#id_constantLabel").hide();
			var comboMenuPosition = getTracywindyObject("id_comboMenuPosition");
				comboMenuPosition.setParams({
				pid:pid
			});
			comboMenuPosition.value = position;
			comboMenuPosition.reload();
		}
		function removeMenu(){
			loadFormData();
			operFlag = "remove";
			operPromit = "删除";
			userOperation();
		}
		
		function showWindow(){
			mini.get(windowContainerId).show();
		}
		
		function __userOperationClose(){
			mini.get(windowContainerId).hide();
		}
		
		function userOperation(){
			if(!validateForm(formId)) return;
			mini.confirm("确认{0}当前树转表么？".replace("{0}",operPromit),operPromit + '？',function(action){
				if(action == 'ok'){
					var url ="${pageContext.request.contextPath}/acl/{0}.acl".replace("{0}",operFlag+constantFlag);
					var loadMaskMsg = "正在{0}树转表	请稍后... ".replace("{0}",operPromit);
					mini.mask({
			            el: document.body,
			            cls: 'mini-mask-loading',
			            html: loadMaskMsg
			        });
					var params = $('#'+formId).tracywindySerializeFormToJsonObject(true);
					params['enabled'] = $("#"+formId+" input[name='enabled']:radio:checked").val();
					params['enabled'] = params['enabled'] ? true : false;
					params['isStatistic'] = $("#"+formId+" input[name='isStatistic']:radio:checked").val();
					params['isStatistic'] = params['isStatistic'] ? 1 : 0;
					params['position'] = 0;
					tenwa.ajaxRequest({
						url:url,
						params:params,
						timeout:30*1000,
						success:function(res){
							mini.unmask(document.body);
							mini.alert("{0}树转表成功".replace("{0}",operPromit),'提示',function(){
								mini.get(windowContainerId).hide();
								submitCallBack();
							});
						},
						failure:function(res){
							mini.unmask(document.body);
							mini.alert("{0}树转表失败".replace("{0}",operPromit));
						}
					});
				}
			});
		}
		
		var currentOperTree = "";
		var currentMenuNode = null;
		
		function loadTreeMenu(){
			mini.mask({
	            el: document.body,
	            cls: 'mini-mask-loading',
	            html: "数据加载中，请稍等..."
	        });
			//加载树形
			$('#id_menu_tabs_menu').tree({
				url:'${pageContext.request.contextPath}/table/get'+constantFlag+'TreeData.action',
				dnd:true ,
				onDrop:function(a,b,c){
					var toNodeId	= $(a).attr("node-id");
					var toNode = $('#id_menu_tabs_menu').tree('find',toNodeId);
					var fromNodeId = b.id;
					var currentPosition = toNode.attributes['position'];
					var pid			= toNode.id;
					var parentNode = $('#id_menu_tabs_menu').tree('getParent',toNode.target);
					if(("0" == toNodeId)&&("append"!=c)){
						return false;
					}
					switch(c){
						case 'append':{
							var chidrenNodes = $('#id_menu_tabs_menu').tree('getChildren',toNode.target);
							var childrenLen = chidrenNodes.length;
							if(0 == childrenLen){
								currentPosition = -1;
							}else{
								currentPosition = chidrenNodes[chidrenNodes.length-1].attributes['position'];
							}
							text = "第一位";
							break;
						}
						case 'top':{
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
						case 'bottom':{
							pid = parentNode.id;
							break;
						}
					}
					mini.mask({
			            el: document.body,
			            cls: 'mini-mask-loading',
			            html: "数据加载中，请稍等..."
			        });
					var params = {id:fromNodeId,entityClassSimpleName:constantFlag,parentId:pid,currentPosition:currentPosition};
					tenwa.ajaxRequest({
						url:"${pageContext.request.contextPath}/acl/updatePosition.acl",
						success:function(res){
							mini.unmask(document.body);
							b.attributes.pid = pid;
							currentPosition = (-1 == parseInt(currentPosition)) ? 1 : (parseInt(currentPosition)+1);
							b.attributes.position = ""+currentPosition;
						},
						failure:function(res){
							mini.unmask(document.body);
							mini.alert("操作失败");
						},
						params:params
					});
					return true;
				},
				onBeforeLoad:function(node,params){
					params['pid'] = node ? node.id : "0";
					if(!node){
						params['init'] = true; 
					}
				},
				onLoadSuccess:function(a,b){
					if(b){
						mini.unmask(document.body);
					}
				},
				onContextMenu:function(event,node){
					node.target.style.width = ($('#id_menu_tabs_menu')[0].scrollWidth-5)+"px";
					currentMenuSelectNode = node;
					$(".flagUserSelectContainer").html(node.text);
					
					currentOperTree = "menuTree";
					currentMenuNode = node;
					var title = "目录数据(" + node.text + ")";
					var table = mini.get(configTableId);
					mini.get('id_panelContainer_' + configTableId).setTitle(title);
					table.setParams({pid:node.id});
					table.reload();
					
					stopDefault(event);
					var x = event.clientX;
					var y = event.clientY;
					var menuItems = jQuery("#id_contextmenu div.menu-item");
					menuItems.each(function(i){
						if(("0" == node.id)&&(2 == i) ){
							this.style.display="none";
						} else{
							if(!$('#id_menu_tabs_menu').tree('isLeaf',currentMenuNode.target) && (2 == i)){
								this.style.display="none";
							} else{
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
					var title = "目录数据(" + node.text + ")";
					var table = mini.get(configTableId);
					table.setParams({pid:node.id});
					mini.get('id_panelContainer_' + configTableId).setTitle(title);
					table.reload();
				}
			});
		}
		jQuery(function(){
			mini.mask({
	            el: document.body,
	            cls: 'mini-mask-loading',
	            html: "数据加载中，请稍等..."
	        });
			loadTreeMenu();
			
			//目录下拉框
			new tracywindyComboBox({
				lazyLoad:true,
				dropListHeight:200,
				id:'id_comboMenuPosition',
				width:190,
				name:'currentPosition',
				isContainEmpty:false,
				renderTo:'id_menuPositionContainer',
				loadMode:'ajax',
				readOnly:true,
				xmlFileName:'\\combos\\comboPosition.xml',
				isCheck:false,
				leftAdd:1,
				params:{
					tablename:("BASE_DOCUMENT_CONFIG").toUpperCase(),
					pid:'-1'
				},
				onSelect:function(combo){
					var currentRawValue = combo.getRawValue();
					
					if(("第一位" == currentRawValue)||("最后" == currentRawValue)) {
						jQuery("#id_constantLabel").hide();
					} else {
						jQuery("#id_constantLabel").show();
					}
				}
			});
			//数据下拉框
			new tracywindyComboBox({
				lazyLoad:true,
				dropListHeight:200,
				id:'id_comboMenuDataPosition',
				width:190,
				renderTo:'id_menuDataPositionContainer',
				loadMode:'ajax',
				readOnly:true,
				isContainEmpty:false,
				name:'currentPosition',
				xmlFileName:'\\combos\\comboPosition.xml',
				isCheck:false,
				leftAdd:1,
				params:{
					tablename:("BASE_DOCUMENT_COLUMN_CONFIG").toUpperCase(),
					pid:'-1'
				},
				onSelect:function(combo){
					var currentRawValue = combo.getRawValue();
					
					if(("第一位" == currentRawValue)||("最后" == currentRawValue)) {
						jQuery("#id_menuDataConstantLabel").hide();
					} else {
						jQuery("#id_menuDataConstantLabel").show();
					}
				}
			});
			//数据类型下拉框
			new tracywindyComboBox({
				lazyLoad:true,
				dropListHeight:240,
				id:'id_comboMenuDataType',
				width:190,
				renderTo:'id_menuDataTypeContainer',
				loadMode:'ajax',
				readOnly:true,
				isContainEmpty:false,
				name:'type',
				xmlFileName:'/combos/comboDict.xml',
				isCheck:false,
				leftAdd:1,
				params:{
				pid:'documentColumnType'
				},
				onSelect:function(combo){
				}
			});
			
			var comboMenuDataIsSelected = new tracywindyComboBox({
				   lazyLoad:true,
			       dropListHeight:240,
			       id:'id_comboMenuDataIsSelected',
			       width:190,
			       renderTo:'id_menuDataIsSelectedContainer',
			       readOnly:true,
			       isContainEmpty:false,
			       name:'isSelected',
			       loadMode : 'local',
				   datas : [{title:'是',code:'1'},{title:'否',code:'0'}],
				   otherAttributes : 'require="true" label="默认选中"',
				   displayField : 'title',
				   valueField : 'code',
			       isCheck:false,
			       leftAdd:1,
			       onSelect:function(combo){
			    	   
			       }
			});
		var comboMenuDataIsSelected = new tracywindyComboBox({
			   lazyLoad:true,
		       dropListHeight:240,
		       id:'id_comboMenuDataIsRequire',
		       width:190,
		       renderTo:'id_menuDataIsRequireContainer',
		       readOnly:true,
		       isContainEmpty:false,
		       name:'isrequire',
		       loadMode : 'local',
			   datas : [{title:'是',code:'1'},{title:'否',code:'0'}],
			   otherAttributes : 'require="true" label="默认不必填"',
			   displayField : 'title',
			   valueField : 'code',
		       isCheck:false,
		       leftAdd:1,
		       defaultIndex:1,
		       onSelect:function(combo){
		    	   
		       }
		});
		var comboxAutocomputerSelected = new tracywindyComboBox({
			   lazyLoad:true,
		       dropListHeight:240,
		       id:'id_autocomputer',
		       width:190,
		       renderTo:'id_autocomputer',
		       readOnly:true,
		       isContainEmpty:false,
		       name:'isrequire',
		       loadMode : 'local',
			   datas : [{title:'是',code:'是'},{title:'否',code:'否'}],
			   otherAttributes : 'require="true" label="是否自动计算"',
			   displayField : 'title',
			   valueField : 'code',
		       isCheck:false,
		       leftAdd:1,
		       defaultIndex:1,
		       onSelect:function(combo){
		    	   
		       }
		});
	});
		jQuery(function(){
			mini.parse();
			
			tenwa.createTable({
				title:'目录数据',
				id:configTableId,
				renderTo:'id_menu_main_content_div',
				
				isNeedEnabled:true,
				width : pageWidth - widthLeft,
				height : pageHeight,
				entityClassName : "com.tenwa.business.entity.base.BaseDocumentConfigData",
				tools:[ {
					html : '新增',//自定义按钮的名字
					plain : true,//按钮是否有立体感
					iconCls : 'icon-add',//按钮的图标
					handler : function(miniTable, buttonText) {//按钮响应函数
						addMenuIterm(miniTable);
					}
				},'-',{
					html : '修改',//自定义按钮的名字
					plain : true,//按钮是否有立体感
					iconCls : 'icon-edit',//按钮的图标
					handler : function(miniTable, buttonText) {//按钮响应函数
						editMenuIterm(miniTable);
					}
				},'-',{
					html : '删除',//自定义按钮的名字
					plain : true,//按钮是否有立体感
					iconCls : 'icon-remove',//按钮的图标
					handler : function(miniTable, buttonText) {//按钮响应函数
						removeMenuIterm(miniTable);
					}
				} ],
				columns:[ 
				  {type : 'indexcolumn'}, 
				  {type : 'checkcolumn'}, 
				  {header:'记录编号',field:'id',visible:false},
				  {header:'数据位置',field:'position',visible:false},
				  {header:'目录编号',field:'belongdocument',visible:false},
				  {header:'所属目录',field:'documentname'},
				  {header:'数据名称',field:'name'},
				  {header:'数据编号',field:'code'},
				  {header:'数值',field:'value'},
				  {header:'数据类型',field:'typename'},
				  {header:'默认选中', field:'isselectedname'},
				  {header:'isselected',field:'isselected',visible:false},
				  {header:'是否必填',field:'isrequirename'},
				  {header:'isrequire',field:'isrequire',visible:false},
				  {header:'数据描述',field:'description'},
				  {header:'列宽',field:'width'},
				  {header:'是否自动计算',field:'autocomputer'},
				  {header:'列高',field:'height',visible:false}],
				xmlFileName:'\\acl\\queryBaseDocumentConfigDatas.xml',
				loadMode:'ajax',
				showPager:true,
				params:{
					pid:'-1'
				}
			});
		});
	</script>
</head>
<body>
	<div id='menu_main_content' class="mini-panel" title="树转表管理" style="float:left;width:325px;overflow:hidden;">
		<div id="id_menu_tabs_menu" oncontextmenu='cancelBubble(event);' ></div>
	</div>
	<div id='id_menu_main_content_div' style="float:right;margin-left: 5px;">
	
	</div>
	
	<script type='text/javascript'>
		var widthLeft = 330;
		jQuery("#menu_main_content").css("height",(pageHeight) + "px");
		jQuery("#id_menu_tabs_menu").css("height",(pageHeight - 35) + "px");
		jQuery("#id_menu_main_content_div").css("height",(pageHeight) + "px");
		jQuery("#id_menu_main_content_div").css("width",(pageWidth - widthLeft) + "px");
	</script>
	
	
	<div id="id_menuDetailInfoWindowContainer" class="mini-window" title="树转表信息管理" style="display:none;width:380px;height:355px;">
		<form id="id_menuDetailInfoForm">
			<table style="width:100%">
				<tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
				<tr style="display:none"><td><input name="parentBaseDocumentConfig" type="hidden" value=""/></td></tr>
				<tr><td class="input_label_desc">树转表名称:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="树转表名称" name="name"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">树转表编号:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="树转表编号" name="code"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">树转表描述:	</td><td colspan="2" class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">列宽:		</td><td colspan="2" class="input_value"><input name="width"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">列高:		</td><td colspan="2" class="input_value"><input name="height"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">分数统计:	</td>
					<td colspan="2">
						<input type="radio" name="isStatistic" id="isStatistic1" value="1"/>统计&nbsp;&nbsp;
						<input type="radio" name="isStatistic" id="isStatistic0" value="0" />不统计
					</td>
				</tr>
				<tr><td class="input_label_desc">是否启用:	</td>
					<td colspan="2">
						<input type="radio" name="enabled" id="enabledtrue" value="true"/>启用&nbsp;&nbsp;
						<input type="radio" name="enabled" id="enabledfalse" value="false" />禁用
					</td>
				</tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td colspan="3"><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td class="input_label_desc" colspan="3"><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuPositionContainer"></span><span style="float:left;" id="id_constantLabel">&nbsp;&nbsp;之后</span></td></tr>
				<tr class="content-separator">
					<td colspan="3" style="text-align: center;">
						<a class="mini-button" onclick='userOperation'><span>确定</span></a>
						<a class="mini-button" onclick='__userOperationClose'><span>取消</span></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<div id="id_detailInfoWindowContainer" class="mini-window" title="字典数据管理" style="display:none;width:380px;height:420px;">
		<form id="id_detailInfoForm">
			<table style="width:100%">
				<tr style="display:none"><td><input name="id" type="hidden" value="" /></td></tr>
				<tr style="display:none"><td><input name="belongDocument" type="hidden" value="" require="true" label="数据目录"/></td></tr>
				<tr style="display:none"><td><input name="position" type="hidden" value="0"/></td></tr>
				<tr><td class="input_label_desc">数据目录:	</td><td style="text-indent:5px;text-align:left;"><label id="id_dictName" class="content-label"></label></td></tr>
				<tr><td class="input_label_desc">数据名称:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="数据名称" name="name"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">数据编号:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="数据编号" name="code"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">数值:		</td><td colspan="2" class="input_value"><input type="text" label="数值" name="value"/></td></tr>
				<tr><td class="input_label_desc">数据类型:	</td><td colspan="2" id="id_menuDataTypeContainer" ><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">默认选中:     </td><td  colspan=2  id="id_menuDataIsSelectedContainer" ><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">是否必填:     </td><td  colspan=2  id="id_menuDataIsRequireContainer" ><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">数据描述:	</td><td colspan="2" class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">列宽:	</td><td colspan="2" class="input_value"><input name="width"/><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">是否自动计算:</td><td  colspan=2  id="id_autocomputer" ><span class="content-required">*</span></td></tr>
				
				<tr style="display:none"><td>列高:	<input name="height" type="hidden" value="true"/></td></tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td colspan="3"><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td class="input_label_desc" colspan="3"><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuDataPositionContainer"></span><span style="float:left;" id="id_menuDataConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
				<tr style="display:none"><td><input name="enabled" type="hidden" value="true"/></td></tr>
				<tr class="content-separator">
					<td colspan='2' style="text-align: center;">
						<a class="mini-button" onclick='__saveMenuIterm'><span>确定</span></a>
						<a class="mini-button" onclick='__saveMenuItermClose'><span>取消</span></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="id_contextmenu" class="easyui-menu" style="display:none;width:120px;">
		<div iconCls="icon-plus-w" onclick='addMenu();'>添加</div>	
		<div iconCls="icon-cogs-w" onclick='updateMenu();'>修改</div>
		<div iconCls="icon-minus-w" onclick='removeMenu();'>删除</div>
	</div>
	<div id="id_pictureContainer" title="图片选择器" class="mini-window" style="display:none;text-align:center;width:300px;height:300px;overflow:auto;">
		<div id="id_pictureContainerBox">
		
		</div>
	</div>
</body>
</html>