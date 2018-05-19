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
		queryButtonNewLine:false,
		pageSize: 20,
		queryButtonColSpan: 10,
		xmlFileName: 'contract_back/contract_back_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'contract_id', header: '合同编号',
				renderer : function(e){
					var id = e.record.contract_id;
					return '<a href="javascript:void(0);" onclick="showdetail(\'' + e.record.id + '\')">' + id + '</a>';
				}},
			{field: 'contract_number', header: '业务合同编号',queryConfig:{width:165}},
			{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
			{field: 'cust_name', header: '客户名称',queryConfig:{width:165,newLine: false}},
			{field: 'card_no', header: '身份证/组织机构代码'},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanage', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '项目状态'},
			{field:'signdatestatus',header:'归还状态',queryConfig:{
				type : 'combobox',//表单域类型
				valueField : 'value',
				textField : 'name',
				allowInput : false,
				showNullItem : false,
				newLine: true,
				//defaultValue:'0',
				data : [{name : '未归还',value : '0'},{name : '已归还',value : '1'},{name : '部分归还',value : '2'}],
				value :'3'
		   	}}

		]
	});
});

  function showdetail(id){
	//var row = grid.getSelected();
	var urlFlag = getRootPath()+"/leasing/doc_manager/contract_back/contract_back_detail.bi?id="+id;
	
   	 mini.open({
     	   url: urlFlag,
     	   title: "合同归档", width: 1000, height: 500,
     	   onload: function () {
        	    /* var iframe = this.getIFrameEl();
        	    var data = { id: id };
      	      iframe.contentWindow.SetData(data); */
            
     	   },
      	  ondestroy: function (action) {
      	      /* grid.reload(); */
            
      	  }
   	 });
	 
}  
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