<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>待补文件管理</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'待补文件管理',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				allowCellEdit:true,
				xmlFileName:'/docadd/limitmagere_list.xml',
				columns:[ 
						{type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false},
						{field:'contract_id',header:'合同编号',
							renderer : function(e){
								var id = e.record.contract_id;
								return '<a href="javascript:void(0);" onclick="showdetail(\'' + e.record.id + '\')">' + id + '</a>';                     
							}	
						},
						{field:'cust_name',header:'客户名称',queryConfig:{}},
						{field:'projmanagename',header:'项目经理',queryConfig:{}},
						{field:'department',header:'部门',queryConfig:{newLine : false}},
						{field:'docname',header:'待补待办事项'},
						{field:'docstatus',header:'待办事项状态',queryConfig:{
							newLine : true,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							defaultValue:'有',
							showNullItem : false,
							data : [{name : '有',value : '有'},{name : '无',value : '无'},{name : '全部',value : ''}]
						}},
						{field:'start_date_',header:'起租日'},
						{field:'firstdate',header:'拨款时间'}
				]
			});
		});
	});
	/*
	function showdetail(){
		var urlFlag = getRootPath()+"/leasing/doc_manager/beAdded_doc/contract_file_added.bi";
		mini.open({
		        url: urlFlag,
		        title: "待补文件管理", width: 800, height: 455,
		        onload: function () {},
		        ondestroy: function (action) {
		        	if("savesuccess" == action){
		        		window.location.reload();
		        	}
		        }
		    });
	}*/
	
	function showdetail(id){
		//var params = "id="+id;
		var URL = "${pageContext.request.contextPath}/leasing/doc_manager/beAdded_doc/contract_file_added.bi";
   
	 	//var url=getRootPath()+"/leasing/doc_manager/beAdded_doc/contract_file_added.bi";
	 	//alert(url);
	 	var params = {id:id};
	 	openFullScreenWindow(URL,params);
	}
</script>
</head>
<body></body>
</html>