<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>法务信息维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var localEnabled = [ {id:'',text:''},{id : '否',text : '否'}, {id : '是',text : '是'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
			id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'法务信息维护',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				editFormPopupWindowHeight:516,
				queryButtonNewLine:false,
				showPager:true,			
				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.lawmng.LawApproval',
				xmlFileName: '/eleasing/workflow/contract/contract_other/law_info_list.xml',
				tools: [{
							html:'诉讼详情记录',
							plain:true,
							iconCls:'icon-edit',
							handler:function(miniTable, buttonText) {
								var miniTable = mini.get("table_id1");
								var columnsConfig = miniTable.columns;
								var selectedRowData = miniTable.getSelected();
								var config = miniTable.config;
								seajs.use([ "js/apcomponent/aptablebase/aptablebase.js" ], function(apTableBase) {
									apTableBase.tableEditOper(miniTable, columnsConfig, selectedRowData, config);
								});
							}
						},'-','exportExcel'
				       ],
				columns:[ 
				         	{type:'indexcolumn'},
						   	{type:'checkcolumn'},  
						   	{field:'id',header:'id',visible:false},
						   	{field:'',header:'操作',
						  		   formEditorConfig:{
			                               fieldVisible:false},
					               renderer:function(e){
						                   var id = e.record.id;
						                   var custid = e.record.custid;
					                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"" + custid + "\",\"one\") '>查看/上传资料 </a>";
							  }},	
						   	{field:'lawnum',header:'法务编号',formEditorConfig:{readOnly:true}},
						   	{field:'contractnumber',header:'合同号',
						   		formEditorConfig:{
						   	    labelWidth:110,
				              	required:true,
				              	readOnly:true,
									maxLength:100},queryConfig:{}},
						   	{field:'custname',header:'客户名',
						   		formEditorConfig:{
						   		labelWidth:110,
				                required:true,
				                colspan:5,
				                width:'100%',
				                newLine:true,
				                readOnly:true,
									maxLength:100},queryConfig:{}},
							/* {field:'lawtypename',header:'法务处理类型',
								formEditorConfig:{
									fieldVisible:false,
									required:true,
									newLine:true,
									fillFromFieldName:'lawtype',
									fillProperty:'name',
									}},
							{field:'lawtype',header:'法务处理类型',visible:false,
						      formEditorConfig:{
					                  type:'combobox',
					              required:true,
					              readOnly:true,
					             textField:'name',
					            valueField:'value',
					          fieldVisible:true,
					                params:{pid: 'fwclx', xmlFileName:'combos/comboDict.xml'}}}, */
							{field:'lawmemo',header:'法务申请处理说明',formEditorConfig:{newLine:true,colspan:3,
								width:'100%',type:'textarea',readOnly:true}}, 
						   	{field:'lawdate',header:'法务申请时间',dateFormat:'yyyy-MM-dd HH:mm:ss',
								   		formEditorConfig:{
									   		 type:'date',
									   		newLine:true,
							                 vtype:'date',
							                 readOnly:true,
							                 format:'yyyy-MM-dd HH:mm:ss',
							              required:true,
							            allowInput:false,
												maxLength:100}},
							/* {field:'jalawtypename',header:'结案类型',
								formEditorConfig:{
									fieldVisible:false,
									required:true,
									newLine:true,
									fillFromFieldName:'jalawtype',
									fillProperty:'name',
									}},
							{field:'jalawtype',header:'结案类型',visible:false,
						      formEditorConfig:{
					                  type:'combobox',
					              required:true,
					              readOnly:true,
					             textField:'name',
					            valueField:'value',
					          fieldVisible:true,
					                params:{pid: 'jieanlx', xmlFileName:'combos/comboDict.xml'}}},
							{field:'closeinfo',header:'结案信息',formEditorConfig:{readOnly:true}},
							{field:'closemoney',header:'结案金额',formEditorConfig:{readOnly:true,newLine:true}},
							//{field:'closedate',header:'结案时间',formEditorConfig:{readOnly:true}},
							{field:'closememo',header:'结案处理说明',formEditorConfig:{newLine:true,colspan:3,
									width:'100%',type:'textarea',readOnly:true}}, */
						   	{field:'indictdate',header:'起诉日期',
						   		formEditorConfig:{
							   		 type:'date',
					                 vtype:'date',					                 
					                format:'yyyy-MM-dd',
					            allowInput:false,
										maxLength:100},queryConfig:{
											type:'date',newLine:true,
											  range:true}},
						   	{field:'ispreserve',header:'是否保全',
						   		formEditorConfig:{
				                data:localEnabled,
				                newLine:true,
	   			                type:'combobox',
									maxLength:100}},
						   	{field:'preservedate',header:'保全日期',
										   		formEditorConfig:{
											   		 type:'date',
									                 vtype:'date',									                 
									                format:'yyyy-MM-dd',
									            allowInput:false,
														maxLength:100},queryConfig:{
															type:'date',
															  range:true}},
						   	{field:'detailmemo',header:'情况说明',formEditorConfig:{newLine:true,colspan:3,
						   		height:200,width:'100%',type:'textarea',maxLength:1000}} 
				]
		});
		});
	});
	
	
/*上传资料内容*/

function showanduploadfile(id,custid,type)
{
	var urlFlag = getRootPath()+"/leasing/cust_info/cust_contact/cust_contact_file_list.bi?id="+id+"&cust_id="+custid+"&type="+type;
	mini.open({
       url: urlFlag,
       title: "上传诉讼资料", width: 800, height: 455,
       onload: function () {},
       ondestroy: function (action) {
       	if("savesuccess" == action){
       		window.location.reload();
       	}
       }
   });

}
</script>
</head>
<body></body>
</html>