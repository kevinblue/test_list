<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金催收函</title>
<%
	//前缀
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
	    <fieldset id="fd2">
	        <legend><label><input type="checkbox" checked id="checkbox1" onclick="toggleFieldSet(this, 'fd2')" hideFocus/>查询条件</label></legend>
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table">
	                <tr>
	                    <td style="width:100px;">合同编号：</td>
	                    <td >
	                        <input name="accnumber" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td style="width:100px;">业务合同号：</td>
	                    <td >
	                        <input name="accbank" class="mini-textbox miniext-form-fit" onEnter="search"/>
	                    </td>
	                    <td class="querybtn">
	                    	<input class="mini-button" text="&nbsp;&nbsp;&nbsp;" tooltip="查询"
	                    	iconCls="icon-query" onclick="search"
	                        />
	                    </td>
	                </tr>
	            </table>
	        </div>
	    </fieldset>
    </div>
<!-- 按钮部分 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="senddate">维护发送日期</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-edit" plain="true" onclick="openletter">生成催款函</a>
	            </td>
            </tr>
        </table>
    </div>
    <!-- 列表部分 -->
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="<%=prefix %>/eleasing/jsp/ebackmgr/rent_collection/rent_collection_letter_info.xml"  idfield=""
            sizeList="[5,10,20,50]" pageSize="10" showColumnsMenu="true"
            datafield="datas" contextMenu="#gridMenu"
        >
            <div property="columns">
                <div type="checkcolumn"></div>
                <div field="" width=""  headerAlign="center">合同编号</div>                            
                <div field="" headerAlign="center">业务合同号</div>
                <div field="" width="" headerAlign="center" >最近一次发送日期</div>
                <div field=""  headerAlign="center" >催款员</div>                                    
                <div field="" width="" headerAlign="center" align="center">客户名称</div>
                <div field=""  headerAlign="center" align="center">电话</div>
                <div field=""  headerAlign="center" >地址</div>
                <div field=""  headerAlign="center" align="center">担保人</div>
            </div>
        </div> 
        <ul id="gridMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
	        <li name="add" iconCls="icon-add" onclick="add">增加</li>
		    <li name="edit" iconCls="icon-edit" onclick="upd">修改</li>
		    <li name="remove" iconCls="icon-remove" onclick="remove">删除</li>        
	    </ul>
    </div>
    <div id="editWindow" class="mini-window" title="租金催收函" style="width:300px;" 
	    showModal="true" allowResize="true" allowDrag="true"
	    >
	    <div id="editform" class="form" >
	        <input class="mini-hidden" name="id"/>
	        <table style="width:100%;">
	            <tr>
	                <td style="width:80px;">发送时间：</td>
	                <td style="width:150px;">
	                	<input name="" class="mini-datepicker miniext-form-fit"/>
	                </td>
	            </tr>
	            <tr>
	                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
	                    <a class="mini-button" iconCls="icon-add" onclick="save">保存</a>
                		<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
	                </td>                
	            </tr>
	       
	        </table>
	    </div>
	</div>
    <script type="text/javascript">
        function toggleFieldSet(ck, id) {
            var dom = document.getElementById(id);
            dom.className = !ck.checked ? "hideFieldset" : "";
            var _fit = mini.get("_fit");
            _fit.setHeight(window.document.body.clientHeight);
        }
    </script>
    <script type="text/javascript">
    	//初始化
        mini.parse();
        miniui_ext.addOnEnter("form1");
        var editWindow = mini.get("editWindow");
        var grid = mini.get("datagrid1");
        var form = new mini.Form("form1");
        //grid.load({"xmlFileName":"/eleasing/jsp/cust_info/cust_person/cust_person_list.xml"});
        grid.load();//加载数据
        //combobox懒加载
        function onbeforeshowpopup(e) {
        	miniui_ext.onbeforeshowpopup(e);
        }
        //查询
        function search(e) {
            var data = form.getData(true,true);
 		    grid.load(data);
        }
      	//格式化日期
        function onDateRenderer(e) {
            return miniui_ext.onDateRenderer(e);
        }
      	//维护发送日期
        function senddate(e){
        	//获取选中行、并判断
        	var row = grid.getSelected();
            if (!row) {
            	editWindow.show();
                //var editform = new mini.Form("#editform");
            } else {
                mini.alert("请选中一条记录");
            }
        }
     	//生成催收函
        function openletter(e){
        	var row = grid.getSelected();
            if (!row) {
            	/*
	  			title:'标题'
	  			showModal:'是否遮罩'
	  			showMaxButton:'是否显示最大化按钮'
	  			onload:页面打开时加载
	  			ondestroy:页面关闭时加载
		  		*/
	            mini.open({
	                url: getRootPath()+"/leasing/sysmgr/sysdatamgr/add_ownaccountinfo.bi?opertype=upd",
	                title: "编辑", width: 800, height: 300,
	                onload: function () {
	                    
	                },
	                ondestroy: function (action) {
	                	
	                }
	            });
            } else {
                mini.alert("请选中一条记录");
            }
        }
     	//右键菜单
        function onBeforeOpen(e) {
            var menu = e.sender;
                    
            var row = grid.getSelected();
            var rowIndex = grid.indexOf(row);            
            if (!row) {
                e.cancel = true;
                //阻止浏览器默认右键菜单
                e.htmlEvent.preventDefault();
                return;
            }
            ////////////////////////////////
//             var editItem = mini.getbyName("edit", menu);
//             var removeItem = mini.getbyName("remove", menu);
//             editItem.show();
//             removeItem.enable();

//             if (rowIndex == 1) {
//                 editItem.hide();
//             }
//             if (rowIndex == 1) {
//                 removeItem.disable();
//             }

        }

    </script>
</body>
</html>