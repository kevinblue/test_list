<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已知租金法信息</title>
<%
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
</head>
<body>
<!-- 按钮部分 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="add">增加</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="remove">删除</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-save" plain="true" onclick="save">保存并关闭</a>
	            </td>
            </tr>
        </table>
    </div>
    <!-- 列表部分 -->
    <div class="mini-fit">
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url=""  idField="id"
            sizeList="[5,10,20,50]" pageSize="10"
            dataField="datas" showColumnsMenu="true"
            allowCellEdit="true" allowCellSelect="true"
            editNextOnEnterKey="true"  editNextRowCell="true" allowCellValid="true" 
        >
            <div property="columns">
                <div type="indexcolumn" ></div>                                                    
                <div field="startrentlist" width="150" headerAlign="center" >开始期次
                	<input property="editor" class="mini-textbox" style="width:100%;"/>
                </div>
                <div field="endrentlist"  headerAlign="center" >截止期次
                	<input property="editor" class="mini-textbox" style="width:100%;"/>
                </div>
                <div field="rent"  headerAlign="center" >租金
                	<input property="editor" class="mini-textbox" style="width:100%;"/>
                </div>
            </div>
        </div> 
    </div>
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        function SetData(data) {
        	grid.setData(mini.decode(data));
        }
        function add(e){
        	var data = grid.getData();
        	var newRow={}
			if(data.length > 0){
				newRow = {startrentlist:parseInt(data[0].endrentlist)+1};
	        }
			grid.addRow(newRow);
		    grid.beginEditCell(newRow);
        }
//         function OnCellBeginEdit(e) {
//             var record = e.record, field = e.field;
//             if (field == "startrentlist" && record.married == "1") {
//                 e.cancel = true;    //如果已婚，则不允许编辑性别
//             }
//             var data = grid.getData();
//             if (e.field == "startrentlist" || e.field == "endrentlist") {
//             	if(data.length > 0){
//     				if(e.value < parseInt(data[1].endrentlist)+1){
//     					e.isValid = false;
//                         e.errorText = "开始期次或者截止期次不能为空，都需要大于0，且开始期次不能大于截止期次！";
//     				}
//     	        }
//             }
//         }
       	
        function remove(e){
        	var row = grid.getSelected();
		    if (null != row) {
		        if (confirm("确定删除选中记录？")) {
		            grid.removeRow(row);
		        }
		    } else {
		        mini.alert("请选中一条记录");
		    }
        }
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
		function save() {
			CloseWindow("savesuccess");
        }
		function CloseWindow(action) {            
		    if (action == "close") {
		        if (confirm("数据被修改了，是否先保存？")) {
		            return false;
		        }
		    }
		    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
		    else window.close();            
		}
		function onCancel(e) {
		    CloseWindow("cancel");
		}
		//调用返回到主页面
		function returnTab(){
			return grid;
		}
    </script>
</body>
</html>