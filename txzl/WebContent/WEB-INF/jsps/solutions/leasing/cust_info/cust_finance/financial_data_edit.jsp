<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
	var custid = "${param.custid}";
</script>
</head>
<body>
<div style="width:100%;height:8%;line-height:40px;" >
	&nbsp;<span>请选择年份:</span>
	<select id="selectedyear" style="width: 160px;" onchange="changeview()">
		<c:forEach var="yearsObject" items="${list}" varStatus="status">
			<option value="${yearsObject.financial_date}">${yearsObject.subject_data}</option>
		</c:forEach>
	</select>
	<a style="float:right;margin-top:6px;margin-right:20px" class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a> 
</div>
	<div id="id_table_financial_data_edit" class="mini-datagrid" style="width:100%;height:92%;" 
        url="${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/cust_info/cust_financial/select_project_year.xml&custid=${param.custid}" idField="id" 
        allowResize="true" pageSize="20" dataField="datas"
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true"
    >
        <div property="columns">
            <div type="indexcolumn"></div>
            <div type="checkcolumn"></div>
            <div name="subjectname"  field="subjectname" headerAlign="center" allowSort="true" width="150" >资金类型
            </div>
            <div name="subjectdata"  field="subjectdata" headerAlign="center" allowSort="true" width="150" >金额
                <input property="editor" class="mini-textbox" style="width:100%;" />
            </div>
            <div name="projectyear"  field="projectyear" headerAlign="center" allowSort="true" width="150" >年度
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	mini.parse();
	var grid = mini.get("id_table_financial_data_edit");
    function saveData() {
        var data = grid.getChanges();
        var json = mini.encode(data);
        grid.loading("保存中，请稍后......");
        $.ajax({
            url: getRootPath()+"/leasing/modifyFinanceData.acl",
            data: { data: json },
            type: "post",
            success: function (text) {
                mini.alert("修改成功");
                grid.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }
    grid.on("celleditenter", function (e) {
        var index = grid.indexOf(e.record);
        if (index == grid.getData().length - 1) {
            var row = {};
            grid.addRow(row);
        }
    });
	grid.load();
	function changeview(){
		var param={};
		var selectedyear = $("#selectedyear").val();
		grid.load({financialdate:selectedyear});
	}
</script>
</html>
