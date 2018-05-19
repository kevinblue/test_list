<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<title>合同归档</title>
<%@include file="/base/mini.jsp"%>
<%
String id=request.getParameter("id");
String custname=request.getParameter("custname");
String contractnumber=request.getParameter("contractnumber");
String signdate=request.getParameter("signdate");

%>
<script type="text/javascript">
	var custname = '${param.custname}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
 

	

</script>
</head>
<body>
	<div class="mini-toolbar" style="text-align:center;line-height:30px;" borderStyle="border:0;">
          <label >姓名：</label>
          <input id="key" class="mini-textbox" style="width:150px;" onenter="search"/>
          <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
    </div>
    <div class="mini-fit">

        <div id="usergrid_as" class="mini-datagrid" style="width:100%;height:100%;" 
        url="${urlPrefix}/contract_back/contract_back_man.xml"  idField="id"
        dataField="datas" 
           sizeList="[5,10,20,50]" pageSize="10" onrowdblclick = "onRowDblClick"
            showColumnsMenu="true" >
            <div property="columns">
                <div field="id" width="120" headerAlign="center"  visible="false">id</div>    
                <div field="usersname" width="100%" headerAlign="center" >姓名</div>                                            
            </div>
        </div>
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>


</body>
<script type="text/javascript">
mini.parse();

var grid = mini.get("usergrid_as");
//var urlFlag = getRootPath()+"/contract_back/contract_back_man.xml";
//动态设置URL
//alert(urlFlag)
//grid.setUrl(urlFlag);
//也可以动态设置列 grid.setColumns([]);

grid.load();

function search() {
	//var grid = mini.get("usergrid_as");
    var key = mini.get("key").getValue();
    grid.load({ key: key });
}

function GetData() {
    var row = grid.getSelected();
    //alert(row.id);
    return row;
}
    
function onRowDblClick(e) {
    onOk();
}
//////////////////////////////////
function CloseWindow(action) {
    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
    else window.close();
}

function onOk() {
	//alert(1);
    CloseWindow("ok");
}
function onCancel() {
    CloseWindow("cancel");
}




</script>
</html>