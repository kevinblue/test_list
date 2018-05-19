<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目发电量电费收入</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
	jQuery(function() {seajs.use([ "js/apcomponent/aptable/aptable.js" ],
		function(ApTable) {
			new ApTable({
				id : 'table_proj_electricity_info_id',
				renderTo : "id_table_render_table1",
				width : globalClientWidth,
				height : globalClientHeight,
				iconCls : 'icon-node',
				hiddenQueryArea : false,
				isClickLoad : false,
				multiSelect : true,
				queryButtonColSpan : 4,
				queryButtonNewLine : false,
				//editFormPopupWindowWidth : 800,
				//editFormPopupWindowHeight : 250,
				title : '项目发电量',
				remoteOper : true,
				pageSize : 15,
				showPager : true,
				lazyLoad : false,
				loadMode : 'ajax',
				queryButtonColSpan : 6,
				queryButtonNewLine : true,
				entityClassName : 'com.tenwa.leasing.entity.projectgeneratingelectricity.ProjElectricityInfo',
				frozenStartColumn : 0,
				frozenEndColumn : 2,
				xmlFileName : '/eleasing/jsp/proj_generatine_electricity/proj_electricity_info_list.xml',
				tools : [ 'add', '-', 'edit', '-',
						'remove', '-', 'exportExcel' ],
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
								//readOnly : true,
								fieldVisible : false
							}
						},
						 {
							field : 'projidname',
							header : '项目名称',
							headerAlign : 'center',
							width : 100,
							visible : true,
							queryConfig : {},
							formEditorConfig : {
								fieldVisible : false,
									/* entityClassName:'com.tenwa.leasing.entity.proj.ProjInfo',//对应实体类
									fillProperty:'name',//显示projid的name
									fillFromFieldName : 'projid',//关联的列
									entityHeaderFieldName:'projectName'//显示值是对应实体类的哪个字段 */
							}
						},{
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
								onSelect : setBenchPrice,
								params : {
									xmlFileName : '/eleasing/jsp/proj_generatine_electricity/proj_info_all_list.xml'
								}
							}
						}, 
					
						{
							field : 'time',
							header : '月度',
							visible : true,
							width:130,
							dateFormat : "yyyy-MM",
							queryConfig : {
								type : 'date',
								format : 'yyyy-MM'
							},
							formEditorConfig : {
								newLine : false,
								type : 'date',
								labelWidth : 100,
								width : '100%',
								format : 'yyyy-MM',
								required : false
							}
						},
						{
							field : 'ongridenergy',
							header : '上网电量(度)',
							visible : true,							
							dataType:"currency",
							formEditorConfig : {
								vtype : "float",
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						},
						{
							field : 'standardhours',
							header : '标准发电小时数',
							headerAlign : 'center',
							visible : true,
							dataType:"currency",
							formEditorConfig : {fieldVisible : false}
						},
						/* {
							field : 'billreceivable',
							header : '应收电费(元)',
							width:120,
							dataType:"currency",
							queryConfig:{},
							formEditorConfig : {
								newLine :true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						},  */
						{
							field : 'benchmarktariff',
							header : '应收电费(元)',
							visible : true,
							dataType:"currency",
							formEditorConfig : {fieldVisible : false}
						},
						{
							field : 'cashreceived',
							header : '实收电费(元)',
							visible:true,
							dataType:"currency",
							formEditorConfig : {
								vtype : "float",
								newLine : false,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						}, 
						{
							field : 'excludingnosubsidy',
							header : '应收补贴(元)',
							headerAlign : 'center',
							dataType:"currency",
							visible : true,
							width : 100,
							formEditorConfig : {fieldVisible : false}
						},
						{
							field : 'electricitysubsidy',
							header : '实收补贴(元)',
							dataType:"currency",
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								vtype : "float",
								newLine : true,
								labelWidth : 100,
								width : 200,
								required : false,
								type : 'text'
							}
						},
						{
							field : 'powerrationing',
							header : '限电比例',
							headerAlign : 'center',
							visible : true,
							dataType:"currency",
							width : 100,
							formEditorConfig : {fieldVisible : false}
						},
						{
							field : 'powerlimit',
							header : '限电量',
							headerAlign : 'center',
							visible : true,
							dataType:"currency",
							width : 100,
							formEditorConfig : {fieldVisible : false}
						},
						{
							field : 'internettariff',
							header : '上网电价（元/度）',
							width : 120,
							dataType:"currency",
							visible : true,
							formEditorConfig : {
								readOnly : true,
								newLine : true,
								fieldVisible : true}
						},
						{
							field : 'thermalprice',
							header : '火电标杆电价(元/度)',
							headerAlign : 'center',
							dataType:"currency",
							visible : true,
							width : 100,
							formEditorConfig : {readOnly : true,fieldVisible : true}
						}, 
						{
							field : 'internetprice',
							header : '上网电价（元/度）',
							width : 120,
							visible : false,
							formEditorConfig : {
								readOnly : true,
								newLine : true,
								fieldVisible : false}
						},
						{
							field : 'benchmarkprice',
							header : '火电标杆电价(元/度)',
							headerAlign : 'center',
							dataType:"currency",
							visible : false,
							width : 100,
							formEditorConfig : {readOnly : true,fieldVisible : false}
						}, 
						{
							field : 'settlementprice',
							header : '结算电价(元)',
							headerAlign : 'center',
							dataType:"currency",
							visible : true,
							width : 100,
							formEditorConfig : {
								vtype : "float",
								newLine : true,
								fieldVisible : true}
						}, 
						{
							field : 'afterbenchmarkprice',
							header : '补贴电价(元)',
							headerAlign : 'center',
							dataType:"currency",
							visible : true,
							width : 100,
							formEditorConfig : {vtype : "float",fieldVisible : true}
						},
						{
							field : 'realname',
							header : '登记人',
							headerAlign : 'center',
							width:100,
							visible : true,
							allowSort:'true',
							formEditorConfig : {
								type : 'text',
								newLine : true,
								width:200,
								readonly:true,
								value:"${sessionScope.loginUser.realname}",
								required : true,
								fieldVisible : true
							}
						},
						{
							field : 'note',
							header : '备注',
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
					             maxLength:500,
					               newLine:true,
					                  type:'textarea',
					               colspan:3,
					                height:70,
					                 width:'100%'}
						}
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
//选择项目后自动带出上网电价和火电标杆电价
function setBenchPrice($me, inputObj, rowData) {
	mini.getbyName("internettariff").setValue(rowData.internetprice);
	mini.getbyName("thermalprice").setValue(rowData.benchmarkprice);
	
}
</script>
</head>
<body>
	<div id="id_table_render_table1"></div>
</body>
</html>
