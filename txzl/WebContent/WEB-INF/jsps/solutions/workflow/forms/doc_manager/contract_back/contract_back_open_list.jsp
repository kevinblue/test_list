<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同归还</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "contract_back",
		renderTo: "id_table_container_contract_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "合同归还",
		remoteOper : false,
		showPager: true,
		queryButtonNewLine:true,
		queryButtonColSpan:10,
		pageSize: 20,
		tools:[
				{html:'档案归还',
				 plain:true,
				 iconCls:'icon-addfolder',
				 handler:function(miniTable, buttonText) {
						var row = miniTable.getSelected();
						if(row){
							var attachmentParams = "contract_id="+row.id;
			        		startProcess("合同归还-1",attachmentParams); 
						}else{
							mini.alert('请你选择需要发起合同归还流程的清单！！！');
						}						
					}
				}         
			],
		xmlFileName: 'contract_back/contract_back_list2.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'contract_id', header: '合同编号'},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{width:165}},
			{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
			{field: 'cust_name', header: '客户名称',queryConfig:{width:165}},
			{field: 'card_no', header: '身份证/组织机构代码'},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanage', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '项目状态'},
			{field:'signdatestatus',header:'归还状态'/* ,queryConfig:{
				type : 'combobox',//表单域类型
				valueField : 'value',
				textField : 'name',
				allowInput : false,
				showNullItem : false,
				width:165,
 				data : [{name : '未归还',value : '未归还'},{name : '已归还',value : '已归还'}]
		   	} */}

		]
	});
});
	 

  /*  function showdetail(id){
	//var params = "id="+id;
	var URL = "${pageContext.request.contextPath}/leasing/doc_manager/contract_back/contract_back_detail.bi";

 	//var url=getRootPath()+"/leasing/doc_manager/beAdded_doc/contract_file_added.bi";
 	//alert(url);
 	var params = {id:id};
 	openFullScreenWindow(URL,params);
}   */ 

</script>
</head>
<body>
<div id="id_table_container_contract_change"></div>
</body>
</html>