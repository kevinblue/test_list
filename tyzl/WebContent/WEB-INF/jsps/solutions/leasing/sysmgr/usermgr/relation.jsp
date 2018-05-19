<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>对应关系</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<style type="text/css">
#id_table_cust_info_edit {
	background: #FAFAFA;
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_cust_info_edit td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
	border-bottom: 1px dotted #CCCCCC;
}
.mini-textbox-readOnly .mini-textbox-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-buttons{
	display: none;
}
</style>
</head>
<body>
	<div class="mini-fit">
		<div class="mini-splitter" style="width:100%;height:100%;">
		    <div size="30%" showCollapseButton="true">
		        <div class="mini-panel" title="关系列表" showCollapseButton="false" style="width: 100%;height: 100%;">
		        	<div>
			            <table class="miniext-form-table" style="width:95%">
			                <tr>
			                    <td style="width:100px;">关系列表：搜索</td>
			                    <td >
			                        <input id="key" class="mini-textbox miniext-form-fit" onenter="search('relationtree','key')"/>
			                    </td>
			                </tr>  
		                 </table>
	                 </div>
	                 <div>
		                 <table>
					        <tr>
					            <td valign="top" style="width:300px;">
								    <ul id="relationtree" class="mini-tree"
								        showTreeIcon="true" textField="text" idField="id"
								        resultAsTree="false"
								        contextMenu="#treeMenu" expandOnLoad="true"
								        onnodeclick = "onNodeClick"
								         >        
								     </ul>
					                <ul id="treeMenu" class="mini-contextmenu"  onbeforeopen="onBeforeOpen">        
									    <li name="add" iconCls="icon-add" onclick="onAddNode">添加</li>
										<li name="edit" iconCls="icon-edit" onclick="onEditNode">修改</li>
										<li name="remove" iconCls="icon-remove" onclick="onRemoveNode">删除</li>        
									</ul>
					            </td>
	            			</tr>
	            		</table>
            		</div>
	           </div>
		    </div>
		    <div showCollapseButton="true">
		    	<div class="mini-panel" title="映射配置" showCollapseButton="false" style="width: 100%;height: 100%;">
	                 <table style="width:100%;height:100%">
	                 	<tr>
	                 		<td valign="top">
	                 			<a class="mini-button" onclick="saveMapItem" style="width:80px;margin:10px;">保存</a>
	                 		</td>
	                 		<td valign="top">
	                 		</td>
	                 	</tr>
				        <tr>
				            <td valign="top" style="width:50%;height:100%">
					            <div class="mini-panel" title="映射输入" showCollapseButton="false" style="width: 100%;height: 100%;">
					            	<div>
							            <table class="miniext-form-table" style="width:95%">
							                <tr>
							                    <td style="width:100px;">映射输入：搜索</td>
							                    <td >
							                        <input id="inputkey" class="mini-textbox miniext-form-fit" onenter="search('mappinginputtree','inputkey')"/>
							                    </td>
							                </tr>  
						                 </table>
						                 <ul id="mappinginputtree" class="mini-tree"
									        showTreeIcon="true" textField="text" idField="id"
									        resultAsTree="false" showCheckBox="true"
									         >        
									     </ul>
					                 </div>
								 </div>
				            </td>
				            <td valign="top" style="width:50%;height:100%">
				            	<div class="mini-panel" title="映射输出" showCollapseButton="false" style="width: 100%;height: 100%;">
				            		<div>
					            		<table class="miniext-form-table" style="width:95%">
							                <tr>
							                    <td style="width:100px;">映射输出：搜索</td>
							                    <td >
							                        <input id="outkey" class="mini-textbox miniext-form-fit" onenter="search('mappingouttree','outkey')"/>
							                    </td>
							                </tr>  
						                 </table>
									    <ul id="mappingouttree" class="mini-tree"
									        showTreeIcon="true" textField="text" idField="id"
									        resultAsTree="false" showCheckBox="true"
									         >        
									    </ul>
								    </div>
							    </div>
				            </td>
	           			</tr>
	           		</table>
           		</div>
           </div>
		</div>
	</div>
	<div id="relationWindow" class="mini-window" title="关系管理" style="width:500px;height:200px;" 
	 showModal="true" allowResize="true" allowDrag="true"
	 >
	 	<div class="mini-fit">
		    <div id="relationform">
		    	<table id="id_table_cust_info_edit">
				    <tr>
				        <td style="width:100px;">关系名称：</td>
		                <td >
		                	<input name="relationid" class="mini-textbox" style="display:none"/>
		                    <input name="relationname" class="mini-textbox miniext-form-fit"/>
		                </td>
				    </tr> 
				    <tr>
		                <td style="width:100px;">关系描述：</td>
		                <td >
		                    <input name="description" class="mini-textarea miniext-form-fit"/>
		                </td>
				    </tr>
		        </table>
		    </div>
	    </div>
	    <div class="mini-toolbar miniext-toolbar-bottom">
		    <table class="miniext-form-fit">
		        <tr>
		            <td>
		                <a class="mini-button" onclick="saveRelation" style="width:80px">确定</a>&nbsp;&nbsp;
		                <a class="mini-button" onclick="onCancelWindow('relationWindow')" style="width:80px">取消</a>
		            </td>
		        </tr>
		    </table>           
		</div>
	</div>
	<div id="mapWindow" class="mini-window" title="映射配置" style="width:500px;height:200px;" 
	 showModal="true" allowResize="true" allowDrag="true"
	 >
	 	<div class="mini-fit">
		    <div id="mapform">
		    	<table id="id_table_cust_info_edit">
				    <tr>
				        <td style="width:100px;">映射名称：</td>
		                <td >
		                	<input name="map_id" class="mini-textbox miniext-form-fit" style="display:none"/>
		                	<input name="map_relation_id" class="mini-textbox" style="display:none"/>
				            <input name="leftSelected" class="mini-textbox" style="display:none"/>
				            <input name="rightSelected" class="mini-textbox" style="display:none"/>
		                    <input name="map_name" class="mini-textbox miniext-form-fit"/>
		                </td>
				    </tr> 
		        </table>
		    </div>
	    </div>
	    <div class="mini-toolbar miniext-toolbar-bottom">
		    <table class="miniext-form-fit">
		        <tr>
		            <td>
		                <a class="mini-button" onclick="saveMap" style="width:80px">确定</a>&nbsp;&nbsp;
		                <a class="mini-button" onclick="onCancelWindow('mapWindow')" style="width:80px">取消</a>
		            </td>
		        </tr>
		    </table>           
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
		function search(treeid,searchid) {
			var tree = mini.get(treeid);
		    var key = mini.get(searchid).getValue();
		    if (key == "") {
		        tree.clearFilter();
		        tree.collapseAll();
		        tree.expandLevel(0);
		    } else {
		        key = key.toLowerCase();                
		        tree.filter(function (node) {
		            var text = node.text ? node.text.toLowerCase() : "";
		            if (text.indexOf(key) != -1) {
		                return true;
		            }
		        });
		        tree.expandAll(); 
		    }
		}
		function onCancelWindow(windowid){
			var relationWindow = mini.get(windowid);
			relationWindow.hide();
		}
        function onAddNode(e) {
            var tree = mini.get("relationtree");
            var node = tree.getSelectedNode();
            if(node._level <1){
            	var relationform = new mini.Form("relationform");
        		relationform.clear();
        		var relationWindow = mini.get("relationWindow");
        		relationWindow.show();
            }else{
            	var mapform = new mini.Form("mapform");
        		mapform.clear();
        		var mapWindow = mini.get("mapWindow");
        		mapWindow.show();
            }
        }
        function loadRelationTree(){
        	var relationtree = mini.get("relationtree");//关系树
        	$.ajax({
                url:'${pageContext.request.contextPath}/acl/relation/queryAllRelation.action',
                async:false,
                dataType:'json',
                success:function(data){
                	var arrayData = mini.decode(data);
                	var arrays = new Array();
                	arrays.push(arrayData);
                	//加载树
                	relationtree.loadData(arrays[0]);
                }
             });
        }
        jQuery(function(){
        	loadRelationTree();
        });
        var deptTreeData;
        function onNodeClick(e){
        	var relationtree = mini.get("relationtree");//关系树
        	var relationtreenode = relationtree.getSelectedNode();
        	if(relationtreenode._level<2) return;
        	var mappinginputtree = mini.get("mappinginputtree");//映射输入树
        	var mappingouttree = mini.get("mappingouttree");//映射输出树
        	
        	if(!deptTreeData){
	        	$.ajax({
	                url:'${pageContext.request.contextPath}/acl/relation/getDeptTree.action',
	                async:false,
	                dataType:'json',
	                success:function(data){
	                   deptTreeData = data;
	                }
	             });
        	}
        	//注：这里必须使用两个变量，后人如果改这里千万注意：如果不使用则会导致两个映射树无法展开
        	var arraysInput = new Array();
        	var arrayInputData = mini.decode(deptTreeData);
        	arraysInput.push(arrayInputData);
        	var arraysOut = new Array();
        	var arrayOutData = mini.decode(deptTreeData);
        	arraysOut.push(arrayOutData);
        	
        	var items = {};
            $.ajax({
               url:'${pageContext.request.contextPath}/acl/relation/getMapItems.action',
               async: false,
               data:{'mapId':relationtreenode.id},
               dataType:'json',
               success:function(data,status){            
                  items = data;
               }
            });
        	//加载树
        	mappinginputtree.loadData(arraysInput[0]);
        	mappingouttree.loadData(arraysOut[0]);
        	if("" != items['left']){
	            var leftSelectedNodes = items['left'];
	            for(var i =0;i < leftSelectedNodes.length;i++){
	            	var selectNode = mappinginputtree.getNode(leftSelectedNodes[i]['addressId']);
	            	mappinginputtree.checkNode(selectNode);
	            	mappinginputtree.expandPath(selectNode);
	            }            
	        }else{
	        	 mappinginputtree.expandLevel(0);
	        }
        	if("" != items['right']){
	            var rightSelectedNodes = items['right'];
	            for(var i =0;i < rightSelectedNodes.length;i++){
	            	var selectNode = mappingouttree.getNode(rightSelectedNodes[i]['addressId']);
	            	mappingouttree.checkNode(selectNode);
	            	mappingouttree.expandPath(selectNode);
	            }            
	        }else{
	        	mappingouttree.expandLevel(0);
	        }
        }
        
        function onEditNode(e) {
        	var tree = mini.get("relationtree");
            var node = tree.getSelectedNode();
            if(node._level <2){
            	var relationform = new mini.Form("relationform");
        		relationform.clear();
        		relationform.setData(mini.decode(node));
        		var relationWindow = mini.get("relationWindow");
        		relationWindow.show();
            }else{
	            var mapform = new mini.Form("mapform");
        		mapform.clear();
        		relationform.setData(mini.decode(node));
        		var mapWindow = mini.get("mapWindow");
        		mapWindow.show();
            }           
        }
        function onRemoveNode(e) {
            var tree = mini.get("relationtree");
            var node = tree.getSelectedNode();

            if (node) {
            	mini.confirm("确定删除选中节点？","提示：",function(data){
		        	if("ok" == data){
		        		$.ajax({
			                url:'${pageContext.request.contextPath}/acl/relation/removeRelation.action',
			                data :{'id':node.id,'type':node.attributes['type']},
			                success:function(data){
	            				if(data == ""){
	            					//mini.alert("删除成功!");   	
	            					window.location.reload();
	            				}else{
	            					mini.alert(data);
	            				}
			                }
			             });
		        	}
            	});
            }
        }
		function onBeforeOpen(e) {
	    var menu = e.sender;
	    var tree = mini.get("relationtree");
	
	    var node = tree.getSelectedNode();
	    if (!node) {
	        e.cancel = true;
	        return;
	    }
	    if (node && node.text == "Base") {
	        e.cancel = true;
	        //阻止浏览器默认右键菜单
	        e.htmlEvent.preventDefault();
	        return;
	    }
	
	    ////////////////////////////////
	    var addItem = mini.getbyName("add", menu);
	    var editItem = mini.getbyName("edit", menu);
	    addItem.show();
	    editItem.show();
		if(node._level == 2){
			addItem.hide();
	        editItem.hide();
	    }
	}
	function saveRelation(e){
		var relationform = new mini.Form("relationform");
		var data = relationform.getData();
		encodeParams(data);
		$.ajax({
			data: data,
            url: '${pageContext.request.contextPath}/acl/relation/saveRelation.action',
            success: function (text) {
            	onCancelWindow('relationWindow');
            	loadRelationTree();
            },
            error: function () {
            	onCancelWindow('relationWindow');
            }
        });
	}
	function saveMap(e){
		var mapform = new mini.Form("mapform");
		var tree = mini.get("relationtree");
		var node = tree.getSelectedNode();
		mini.getbyName("map_relation_id").setValue(node.id);
		var data = mapform.getData();
		encodeParams(data);
		$.ajax({
			data: data,
            url: '${pageContext.request.contextPath}/acl/relation/saveMap.action',
            success: function (text) {
            	onCancelWindow('mapWindow');
            	loadRelationTree();
            },
            error: function () {
            	onCancelWindow('mapWindow');
            }
        });
	}
	function saveMapItem(e){
		//获取左侧的选中关系列表		
		var tree = mini.get("relationtree");
		var node = tree.getSelectedNode();
		if(!node){
			mini.alert("请任意选中一个对应关系！");return;
		}
		var parentNode = tree.getParentNode(node);
		var map_relation_id = parentNode.id;//选中的对应关系ID
		var map_id = node.id;
		var map_name = node.text;
		mini.getbyName('map_relation_id').setValue(map_relation_id);
		mini.getbyName('map_id').setValue(map_id);
		mini.getbyName('map_name').setValue(map_name);
		map_name
		var mapform = new mini.Form("mapform");
		var mappinginputtree = mini.get("mappinginputtree");//映射输入树
    	var mappingouttree = mini.get("mappingouttree");//映射输出树
    	var leftSelected = mappinginputtree.getCheckedNodes();
    	var rightSelected = mappingouttree.getCheckedNodes();
    	var leftArray = new Array();
    	for(var i = 0; i < leftSelected.length;i++){
            leftArray.push(leftSelected[i].attributes['type'] + "#" + leftSelected[i].id);
         }
         mini.getbyName('leftSelected').setValue(leftArray.join(","));

         var rightArray = new Array();
         for(var i = 0; i < rightSelected.length; i++){
            rightArray.push(rightSelected[i].attributes['type'] + "#" + rightSelected[i].id);
         }
         mini.getbyName('rightSelected').setValue(rightArray.join(","));
         var data = mapform.getData();
         encodeParams(data);
		 $.ajax({
			data: data,
            url: '${pageContext.request.contextPath}/acl/relation/saveMapItem.action',
            success: function (text) {
            	mini.alert("保存成功！");
            	onCancelWindow('mapWindow');
            	//loadRelationTree();
            },
            error: function () {
            	onCancelWindow('mapWindow');
            }
         });
	}
	//编码方法
	function encodeParams(params){
		for (var param in params) {
			params[param] = encodeURIComponent(params[param]);
		}
	}
</script>