<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>维护合同风机号</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "contract_sign",
		renderTo: "id_table_container_contract_sign",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "维护合同风机号",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools: [{
			html : '维护合同风机信息号',
			plain : true,//背景透明
			iconCls : 'icon-addfolder',//按钮图标类
			handler : function (miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					mini.get("id_machine").setValue(row.wind_machine==null?"":row.wind_machine);
					mini.get("id_main_wind_machine").show();
				}else{
					mini.alert('请先选择合同！！！');
				}	
			}
		}],
		queryButtonColSpan: 4,
		queryButtonNewLine:false,
		xmlFileName: '/eleasing/workflow/contract/SM_interface/wind_machine.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'projid', header:'项目编号'},	 
			{field: 'cust_name', header: '客户名称',width:135,queryConfig:{width:165,newLine: false}},
			{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
			{field: 'contract_number', header: '业务合同编号',width:110,queryConfig:{width:165,newLine: false}},			
			{field: 'contract_id', header: '合同编号',width:110},
			{field:'cleanleasemoney',dataType:"currency",header:'合同金额'},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'contractstatus', header: '项目状态'},
			{field: 'card_no', header: '身份证/注册号/统一社会信用代码',width:175},		
			{field: 'department', header: '出单部门'},
			{field: 'wind_machine', header: '风机合同号',queryConfig:{newLine:true}}
			
		]
	});
});
function addwindmachine(){
	var id_tasks_table1 = mini.get("contract_sign");
	var miniTable=mini.get("contract_sign");
	var row = id_tasks_table1.getSelected();
	var cid = row.id;
	var windmachine=mini.get("id_machine").getValue();
	if (''==windmachine){
		mini.alert("风机合同号不能为空！")
	}else{
		var params = {};
		params.cid = cid;
		params.windmachine=windmachine;
		$.ajax({
			url : '${pageContext.request.contextPath}/acl/saveWindMachine.acl',
			method : 'POST',
			async : false,
			data : params,
			success:function(res){
				var scustinfo = res.responseText;
				mini.get("id_main_wind_machine").hide();
				miniTable.reload();
			},
			failure : function(res) {
				currentLoadMask.hide();
			}
			
		});
	}

}
</script>
</head>
<body>
<div id="id_table_container_contract_sign"></div>
 <div id="id_main_wind_machine" class="mini-window" title="风机合同维护" style="width:400px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_wind_machine">
		<table>
             	<tr>
                    <td style="width:100px;">风机合同维护：</td>
                    <td >
                    <input style="width:200px;" id="id_machine" name="id_machine" class="mini-textarea" />
                    </td>
                    <td>
                     <a class="mini-button" iconCls="icon-edit" onclick="addwindmachine">确定</a>
                     </td>
                </tr>
          </table>
	</div>
</body>
</html>