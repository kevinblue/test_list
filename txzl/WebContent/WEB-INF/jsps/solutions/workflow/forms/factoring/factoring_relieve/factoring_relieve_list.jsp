<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理争议解除</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.0.min.js?${currentTimestamp}" type="text/javascript"></script>	
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_factoring_relieve',
										renderTo : "id_table_factoring_relieve_list",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : false,
										queryButtonColSpan : 4,
										queryButtonNewLine : false,
										editFormPopupWindowWidth : 750,
										editFormPopupWindowHeight :380,
										title : '保理争议解除',
										pageSize : 20,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										remoteOper : true,
										entityClassName : 'com.tenwa.leasing.entity.factoring.FactoringControversyRelieve',
										xmlFileName : '/eleasing/workflow/factoring/factoring_relieve/factoring_relieve_list.xml',
										tools: ['add', '-', 'edit', '-',{
											html:'删除',
											plain:true,
											iconCls:'icon-remove',
											handler:function(miniTable,buttonText){
												var row=miniTable.getSelected();
												if(row){
													remove(row.id,"1");
												}else{
													alert('未选中行');
												}
											}
										},'-','exportExcel'],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
												{
													//有2个的原因是在editorConfig中queryInput、combobox有默认值
													//contractidname在页面不显示,对应着页面查询的XML
													field : 'contractidname',
													header : '合同编号',
													width : 120,
													formEditorConfig:{
														type : 'text',
														fieldVisible :false
													}
												}, 
												{
													//contractid在页面显示,对应着页面查询的XML
													field : 'contractid',
													header : '合同编号',
													visible:false,
													formEditorConfig:{
														type : 'queryinput',
														textField:'contractidname',
														valueField:'contractid',
														params:{
															xmlFileName:'eleasing/workflow/factoring/factoring_relieve/factoring_relieve_controversyid1.xml'
														},
														onSelect : function($me,queryInput,rowData) {
															//alert(queryInput.value+":"+rowData.contractnumber);
															mini.getbyName("contractidname").setValue(rowData.contractidname); 
															mini.getbyName("contractnumber").setValue(rowData.contractnumber);
															mini.getbyName("custname").setValue(rowData.custname);
															mini.getbyName("projectname").setValue(rowData.projectname);
															
															var contractid_value = queryInput.value;//该控件的值，合同ID
											                var appNumber = getMiniuiExtObject("table_factoring_relieve_editFormPopupWindow_form_applicationnumber");//editorConfig:根据前台前台控件ID获取控件									
											                appNumber.params.contractid= contractid_value;//争议申请编号查询出来的值//选择与合同ID相同的一些行
															//alert(contractid_value);
											                mini.getbyName("applicationnumber").setText("");
										                    mini.getbyName("createdate").setValue("");
										                    mini.getbyName("explaination").setValue("");
										                    
										                    mini.getbyName("status").setValue("");
										                    mini.getbyName("statusname").setValue("");
										                    mini.getbyName("relievedate").setValue("");
										                    mini.getbyName("relieveexplaination").setValue("");
														},
														allowInput:false,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'contractnumber',
													header : '业务合同编号',
													headerAlign : 'center',
													queryConfig : {},
													formEditorConfig : {
														type : 'text',
														readonly : true,
														fieldVisible : true
													}
												},
												{
													field : 'custname',
													header : '客户名称',
													headerAlign : 'center',
													queryConfig : {},
													formEditorConfig : {
														type : 'text',
														readonly : true,
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'projectname',
													header : '项目名称',
													queryConfig : {width:200},
													formEditorConfig : {
														readonly : true,
														fieldVisible : true
													}
												},
												{
													field : 'applicationnumbername',
													header : '争议申请编号',
													visible:true,
													queryConfig:{newLine : true},
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{
													field : 'applicationnumber',
													header : '争议申请编号',
													visible:false,
													formEditorConfig : {
														labelWidth : 120,
														type : 'queryinput',
														textField:'applicationnumbername',
														valueField:'applicationnumber',
														params:{
															xmlFileName:'eleasing/workflow/factoring/factoring_relieve/factoring_relieve_controversyid2.xml'
														},
														onSelect : function($me,queryInput,rowData) {
															mini.getbyName("createdate").setValue(rowData.createdate);
															mini.getbyName("explaination").setValue(rowData.explaination); 
															mini.getbyName("factoringcontroversyid").setValue(rowData.fcid); 
															mini.getbyName("status").setValue("2"); 
															mini.getbyName("statusname").setValue("已解除"); 
														},
														allowInput:false,
														required : true,
														newLine:true,
														fieldVisible : true
													}
												},
												{
													field : 'factoringcontroversyid',
													header : '争议申请ID',
													visible:false,
													formEditorConfig : {
														fieldVisible:false
													}
												},
												{
													field : 'createdate',
													header : '争议申请日期',
													dateFormat : 'yyyy-MM-dd',
													formEditorConfig : {
														type : 'date',
														format : 'yyyy-MM-dd',
														readOnly : true,
														fieldVisible : true
													}
												},
												{
													field : 'explaination',
													header : '争议申请说明',
													queryConfig : { },
													formEditorConfig : {
														type : 'textarea',
														width : "100%",
														readOnly:true,
														newLine : true,
														required : true
													}
												},
												{
													field : 'statusname',
													header : '争议状态',
													visible:false,
													formEditorConfig:{
														fieldVisible:false
													}
												},
												{
													field : 'status',
													header : '争议状态',
													visible:false,
													formEditorConfig : {
														type : 'combobox',
														textField:'name',
														valueField:'value',
														data : [{value :1,name : '争议中'}, {value :2,name : '已解除'}],
														newLine : true,
														fieldVisible:false,
														allowInput:false
													}
												},
												{
													field : 'relievedate',
													header : '争议解除日期',
													dateFormat : 'yyyy-MM-dd',
													queryConfig : {
														type : 'date',
														newLine:false,
														range : true,
														format : 'yyyy-MM-dd'
														
													},
													formEditorConfig : {
														type : 'date',
														defaultValue : mini.formatDate(new Date(),"yyyy-MM-dd"),
														allowInput : false,
													}
												},
												{
													field : 'relieveexplaination',
													header : '争议解除说明',
													queryConfig : {newLine : true},
													formEditorConfig : {
														type : 'textarea',
														width : "100%",
														newLine : true,
														required : true
													}
												} ]
									});
						});
	});

</script>
<script type="text/javascript">
	function remove (id,status){
		 $.ajax({
				url: getRootPath()+"/acl/checkFactoringControversyRelieve.acl",
				type:'post',
				cache:false,
				async:false,
				data:{id:id,status:status},
				success:function (text){
					if('删除成功！'==text){
						mini.alert("删除成功");
						if (mini.get("table_factoring_relieve")) {
							mini.get("table_factoring_relieve").reload();
						}else{
			        		location.reload(); 
						}
					}else{
						mini.alert("删除失败");
					}
				} 
		}); 
	}
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_factoring_relieve_list"></div>
	</div>
</body>
</html>








