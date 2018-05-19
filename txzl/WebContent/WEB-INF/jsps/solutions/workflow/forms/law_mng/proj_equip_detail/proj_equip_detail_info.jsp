<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租赁物清单明细</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">

function importMiniTableCallBack(message, tableid) {
	var info = eval("(" + message + ")");
	mini.alert(info.message);
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
}

seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
	jQuery(function() {seajs.use([ "js/apcomponent/aptable/aptable.js" ],
		function(ApTable) {
			new ApTable({
				//导入Excel配置
				importTargetClass:'com.tenwa.leasing.entity.proj.ProjEquipmentInfo',
				//importOrExPortCallBack : 'checkFundEbankDataTX',//回调方法用来校验对象属性
				importDataIndex : '2',
				importHeaderIndex : '1',
				id : 'table_id_index',
				renderTo : "id_table_render_table1",
				width : globalClientWidth,
				height : globalClientHeight,
				iconCls : 'icon-node',
				hiddenQueryArea : false,
				isClickLoad : false,
				multiSelect : true,
				queryButtonColSpan : 4,
				queryButtonNewLine : false,
				editFormPopupWindowWidth : 800,
				editFormPopupWindowHeight : 300,
				title : '租赁物清单',
				remoteOper : true,
				pageSize : 15,
				showPager : true,
				lazyLoad : false,
				loadMode : 'ajax',
				queryButtonColSpan : 6,
				queryButtonNewLine : false,
				entityClassName : 'com.tenwa.leasing.entity.proj.ProjEquipmentInfo',
				xmlFileName : '/eleasing/jsp/proj_equipment_info/proj_equipment_info_list.xml',
				tools : [ 'add', '-', 'edit', '-',
						'remove', '-','importExcel','-', 'exportExcel' ],
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
							width : 100,
							allowSort : true,
							visible : false,
							formEditorConfig : {
								fieldVisible : false
							}
						},
						 {
							field : 'projname',
							header : '项目名称',
							headerAlign : 'center',
							width : 100,
							visible : true,
							queryConfig : {},
							formEditorConfig : {
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : true,
								type : 'text'
							}
						},
						/* {
							field : 'projid',
							header : '项目名称',
							visible : false,
							formEditorConfig : {
								newLine : false,
								type : 'queryinput',
								required : true,
								textField : 'name',
								valueField : 'id',
								fieldVisible : true,
								params : {
									xmlFileName : '/eleasing/jsp/proj_equipment_info/proj_info_list.xml'
								}
							}
						}, */ 
						{
							field : 'contractlife',
							header : '合同年限',
							visible : true,
							width:130,
							formEditorConfig : {
								newLine : false,
								type : 'text',
								labelWidth : 100,
								width : '100%',
								required : false
							}
						},
						{
							field : 'obtaintime',
							header : '取得时间',
							visible : true,
							width:130,
							formEditorConfig : {
								newLine : true,
								type : 'text',
								labelWidth : 100,
								width : '100%',
								required : false
							}
						},
						{
							field : 'manufacturer',
							header : '生产厂家',
							visible : true,							
							formEditorConfig : {
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						},
						{
							field : 'devicename',
							header : '设备名称',
							headerAlign : 'center',
							visible : true,
							formEditorConfig : {
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						{
							field : 'specimodel',
							header : '规格型号',
							visible : true,
							formEditorConfig : {
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						{
							field : 'quantum',
							header : '数量',
							visible:true,
							formEditorConfig : {
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						}, 
						{
							field : 'originalvalue',
							header : '原值',
							headerAlign : 'center',
							dataType:"currency",
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						{
							field : 'networth',
							header : '净值',
							dataType:"currency",
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						},
						{
							field : 'leaseassetsnumber',
							header : '租赁资产编号',
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						{
							field : 'invoicenumber',
							header : '发票号码',
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						{
							field : 'storagelocation',
							header : '存放位置',
							width : 120,
							visible : true,
							formEditorConfig : {
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
								}
						},
						]
					});
			});
	});
	//发送ajax获取合同名
	/*
	function loadContractNameAjax($me,queryInput,rowData){
		var conid = queryInput.value;  
		$.ajax({
			cache : false,
			async : false,
	        type: "post",
	        url : getRootPath()+ "/acl/loadContractName.acl",
	        data: {conid:conid},
	        success: function (data){
	        	mini.getbyName("contractname").setValue(data);
	        }
	    })
	}
	*/

</script>
</head>
<body>
	<div id="id_table_render_table1"></div>
</body>
</html>
