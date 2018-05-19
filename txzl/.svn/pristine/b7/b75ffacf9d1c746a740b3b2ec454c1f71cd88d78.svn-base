<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.tenwa.kernal.utils.FileUtil,com.tenwa.kernal.utils.WebUtil,com.tenwa.kernal.utils.EnumUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>评分规则管理</title>
	
	<!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<%@include file="/base/mini.jsp"%>
	
	<!--javascript libray-->
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
		var currentPicInputId = null;
		
		jQuery(function(){
			
			mini.get('rolecaltype').set({
				valueField:'value',
				textField:'text',
				data: <%=EnumUtil.getEnumConstantsAsJson(com.tenwa.business.model.RoleCalEnum.class)%>
			});
		});
		var pageWidth = document.documentElement.clientWidth;
		var pageHeight = document.documentElement.clientHeight;
		var constantFlag = "BaseRole";
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
			$("#"+formId+" input[name='maxvalue']").val(currentMenuNode.attributes['maxvalue']);
			$("#"+formId+" input[name='minvalue']").val(currentMenuNode.attributes['minvalue']);
			$("#"+formId+" input[name='valuescale']").val(currentMenuNode.attributes['valuescale']);
			mini.get('rolecaltype').setValue(currentMenuNode.attributes['rolecaltype']);
		
			var enabled = currentMenuNode.attributes['enabled'] ;
			var isStatistic = currentMenuNode.attributes['isStatistic'] ;
			$("#enabled"+enabled).attr("checked", true);
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
			if(!currentMenuNode || currentMenuNode.state){
				mini.alert("请先选择左侧目录！");
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
			
			document.getElementById("id_detailInfoForm").reset();
			$("#id_dictName").html(currentMenuNode.attributes.name);
			$("#id_detailInfoForm input[name='pid']").val(currentMenuNode.id);
			$("#id_detailInfoForm input[name='id']").val("");
			mini.get('maxopenorclose').setValue('false');
			mini.get('minopenorclose').setValue('true');
			$("#enabled1").attr("checked", true);
			mini.get('id_detailInfoWindowContainer').show();
		}
		
		
		function editMenuIterm(miniTable){
			var selectedRowDatas = miniTable.getSelecteds();
			if(selectedRowDatas.length > 0){
				var currentRowData = selectedRowDatas[0];
				var comboMenuDataPosition = getTracywindyObject("id_comboMenuDataPosition");
				var pid = currentRowData.pid;
				comboMenuDataPosition.value = currentRowData['position'];
				comboMenuDataPosition.setParams({
					pid:pid
				});
				comboMenuDataPosition.reload();
				for(var p in currentRowData){
					$("#id_detailInfoForm input[name='"+p+"']").val(currentRowData[p]);
				}
				$("#id_detailInfoForm textarea[name='description']").val(currentRowData.description);
				$("#enabled"+currentRowData.enabled).attr("checked", true);
				mini.get('minopenorclose').setValue(currentRowData.minopenorclose == '0' ? 'false' : 'true');
				mini.get('maxopenorclose').setValue(currentRowData.maxopenorclose == '0' ? 'false' : 'true');
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
							url:"${pageContext.request.contextPath}/acl/removeBaseRoleBlock.acl",
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
			var position = getTracywindyObject("id_comboMenuDataPosition");
			var params = $('#id_detailInfoForm').tracywindySerializeFormToJsonObject(true);
			params.position = position.getValue();
			params.currentPosition = position.getValue();
			params.description = $("#id_detailInfoForm textarea[name='description']").val();
			params.enabled = $("#id_detailInfoForm input[name='enabled']:radio:checked").val() == 0 ? false : true;
			params.minopenorclose = mini.get('minopenorclose').getValue();
			params.maxopenorclose = mini.get('maxopenorclose').getValue();
			
			var action = params.id ? 'update' : 'add';
			tenwa.ajaxRequest({
				url:"${pageContext.request.contextPath}/acl/" + action + "BaseRoleBlock.acl",
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
				mini.alert("请先选定左侧评分规则模块。");
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
			mini.confirm("确认{0}当前评分规则么？".replace("{0}",operPromit),operPromit + '？',function(action){
				if(action == 'ok'){
					var url ="${pageContext.request.contextPath}/acl/{0}.acl".replace("{0}",operFlag+constantFlag);
					var loadMaskMsg = "正在{0}评分规则	请稍后... ".replace("{0}",operPromit);
					mini.mask({
			            el: document.body,
			            cls: 'mini-mask-loading',
			            html: loadMaskMsg
			        });
					var params = $('#'+formId).tracywindySerializeFormToJsonObject(true);
					params['enabled'] = $("#"+formId+" input[name='enabled']:radio:checked").val();
					params['enabled'] = params['enabled'] ? true : false;
					params['position'] = 0;
					params['rolecaltype'] = mini.get('rolecaltype').getValue();
					tenwa.ajaxRequest({
						url:url,
						params:params,
						timeout:30*1000,
						success:function(res){
							mini.unmask(document.body);
							mini.alert("{0}评分规则成功".replace("{0}",operPromit),'提示',function(){
								mini.get(windowContainerId).hide();
								submitCallBack();
							});
						},
						failure:function(res){
							mini.unmask(document.body);
							mini.alert("{0}评分规则失败".replace("{0}",operPromit));
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
					tablename:("BASE_ROLE").toUpperCase(),
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
					tablename:("BASE_ROLE_BLOCK").toUpperCase(),
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
				entityClassName : "com.tenwa.business.entity.base.BaseRoleBlock",
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
				  {header:'目录编号',field:'pid',visible:false},
				  {header:'所属目录',field:'rolename'},
				  {header:'数据名称',field:'name'},
				  {header:'数据编号',field:'code'},
				  {header:'最大区间值',field:'maxfieldvalue'},
				  {header:'最大区间开闭', field:'maxopenorclosename'},
				  {header:'maxopenorclose',field:'maxopenorclose',visible:false},
				  {header:'最小区间值',field:'minfieldvalue'},
				  {header:'最小区间开闭', field:'minopenorclosename'},
				  {header:'minopenorclose',field:'minopenorclose',visible:false},
				  {header:'基准区间值',field:'basefieldvalue'},
				  {header:'基准分值',field:'basevalue'},
				  {header:'区间增量',field:'fieldincrement'},
				  {header:'说明',field:'description'},
				  {header:'分值增量',field:'valueincrement'}],
				xmlFileName:'\\acl\\queryBaseRoleBlocks.xml',
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
	<div id='menu_main_content' class="mini-panel" title="评分规则管理" style="float:left;width:325px;overflow:hidden;">
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
	
	
	<div id="id_menuDetailInfoWindowContainer" class="mini-window" title="评分规则信息管理" style="display:none;width:380px;height:355px;">
		<form id="id_menuDetailInfoForm">
			<table style="width:100%">
				<tr style="display:none"><td><input name="id" type="hidden" value=""/></td></tr>
				<tr style="display:none"><td><input name="parentBaseRole" type="hidden" value=""/></td></tr>
				<tr><td class="input_label_desc">规则名称:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="评分规则名称" name="name"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">规则编号:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="评分规则编号" name="code"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">规则描述:	</td><td colspan="2" class="input_value"><textarea name="description"></textarea><span class="content-required">&nbsp;&nbsp;</span></td></tr>
				<tr><td class="input_label_desc">最大分值:		</td><td colspan="2" class="input_value"><input name="maxvalue"/></td></tr>
				<tr><td class="input_label_desc">最小分值:		</td><td colspan="2" class="input_value"><input name="minvalue"/></td></tr>
				<tr><td class="input_label_desc">分值精度:		</td><td colspan="2" class="input_value"><input name="valuescale"/></td></tr>
				<tr><td class="input_label_desc">计算规则:		</td><td colspan="2" class="input_value"><input name="rolecaltype" class="mini-combobox"    id ="rolecaltype" ></td></tr>
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
	
	
	<div id="id_detailInfoWindowContainer" class="mini-window" title="评分规则块管理" style="display:none;width:380px;height:520px;">
		<form id="id_detailInfoForm">
			<table style="width:100%">
				<tr style="display:none"><td><input name="id" type="hidden" value="" /></td></tr>
				<tr style="display:none"><td><input name="pid" type="hidden" value="" require="true" label="数据目录"/></td></tr>
				<tr style="display:none"><td><input name="position" type="hidden" value="0"/></td></tr>
				<tr><td class="input_label_desc">数据目录:	</td><td style="text-indent:5px;text-align:left;"><label id="id_dictName" class="content-label"></label></td></tr>
				<tr><td class="input_label_desc">数据名称:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="数据名称" name="name"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">数据编号:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="数据编号" name="code"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">最大区间值:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="最大区间值" name="maxfieldvalue"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">最大区间值开闭:	</td>
					<td >
						<input id="maxopenorclose" class="mini-combobox" valueField="value"
				textField="name"   id ="rolecaltype" data = "[{value:'false',name:'开'},{value:'true',name:'闭'}]">
					</td>
				</tr>
				<tr><td class="input_label_desc">最小区间值:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="最小区间值" name="minfieldvalue"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">最小区间值开闭:	</td>
					<td >
						<input id="minopenorclose" class="mini-combobox" valueField="value"
				textField="name"   id ="rolecaltype" data = "[{value:'false',name:'开'},{value:'true',name:'闭'}]">
					</td>
				</tr>
				<tr><td class="input_label_desc">基准区间值:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="基准区间值" name="basefieldvalue"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">基准分值:	</td><td colspan="2" class="input_value"><input type="text" require="true" label="基准分值" name="basevalue"/><span class="content-required">*</span></td></tr>
				<tr><td class="input_label_desc">区间增量:	</td><td colspan="2" class="input_value"><input type="text"  label="区间增量" name="fieldincrement"/></td></tr>
				<tr><td class="input_label_desc">分值增量:	</td><td colspan="2" class="input_value"><input type="text"  label="分值增量" name="valueincrement"/></td></tr>
				<tr><td class="input_label_desc">数据描述:	</td><td colspan="2" class="input_value"><textarea name="description"></textarea></td></tr>
				<tr><td class="input_label_desc">是否启用:	</td>
					<td colspan="2">
						<input type="radio" name="enabled" id="enabled1" value="1"/>启用&nbsp;&nbsp;
						<input type="radio" name="enabled" id="item_enabled0" value="0" />禁用
					</td>
				</tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td colspan="3"><div style="width:100%;border-top:1px solid #DDD;"></div></td></tr>
				<tr><td colspan="3"><div style="width:100%;height:5px;">&nbsp;</div></td></tr>
				<tr><td class="input_label_desc" colspan="3"><span style="float:left;margin-left:50px;">排在&nbsp;&nbsp;</span><span style="float:left;" id="id_menuDataPositionContainer"></span><span style="float:left;" id="id_menuDataConstantLabel">&nbsp;&nbsp;之后</span></td></tr>
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