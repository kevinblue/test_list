<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>覆审汇总表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var localEnabled = [ {id:'未完成覆审',text:'未完成覆审'},{id : '已完成覆审',text : '已完成覆审'}];
var isexist = [ {id:'',text:''},{id : '无',text : '无'}, {id : '有',text : '有'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'覆审汇总表',
				iconCls:'icon-node',
				multiSelect: true,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				editFormPopupWindowHeight:530,
				queryButtonNewLine:false,
				showPager:true,	
				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.contract.ContractPatrolInfo',
				xmlFileName: '/eleasing/workflow/contract/contract_other/law_total_list.xml',
				tools: ['edit', '-',{
					html : '导出覆审汇总数据',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
						if(0 == checkedRowDatas.length){
							alert("请选择要导出的数据！");
							return;
						}
						var tempIdArr = [];
						for(var i = 0;i<checkedRowDatas.length;i++){
							var checkedRowdata = checkedRowDatas[i];
							var id = checkedRowdata.id;
							tempIdArr.push("'"+id+"'");
						}
						var fileTeplate=new fileTemplateConfig({
		                	 templateno:'F-201504004',
		                	 tableid:miniTable.config.id,
		                	 modelname:miniTable.config.title,
		                	 returntype:'file',
		                     parames:{
					                 /* importorexportcallback:'exportFundChargeInvoiceBefore',
			                     	importorexportcallbackafter:'exportFundInvoiceAfter', */
			                     	ids:tempIdArr.join(",") 
		                     }
		                     });
		                 fileTeplate.createFile();
					}}],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
	              {field:'patroltypename',header:'覆审类型',
						formEditorConfig:{
							labelWidth:140,
							fieldVisible:false,
							required:true,
							readOnly:true,
							fillFromFieldName:'patroltype',
							fillProperty:'name',
							}},
				{field:'bnsrealname',header:'承办人员',
			   		formEditorConfig:
			   		{
			   			newLine:true,
			   			readOnly:true,
		                required:true
		             },queryConfig:{}},
					{field:'patroltype',header:'覆审类型',visible:false,
						formEditorConfig:{
							labelWidth:140,
							type:'combobox',
							required:true,
							readOnly:true,
							textField:'name',
							valueField:'value',
							fieldVisible:true,
							params:{pid: 'fushen_type', xmlFileName:'combos/comboDict.xml'}}},
				   	{field:'patroldate',header:'覆审日期',
								   		formEditorConfig:
								   		{
								   		   type:'date',
							               vtype:'date',
							               format:'yyyy-MM-dd',
							               required:true,
							               readOnly:true,
							               newLine:true,
							               allowInput:false
							            },
							            queryConfig:
							            {
							            	type:'date',
											range:true
										}
					 },
					  {field:'custname',header:'客户名称',
					   		formEditorConfig:{
			              required:true,
			              readOnly:true}},
					  {field:'custtype',header:'客户类别',
					   		formEditorConfig:{
					   			newLine:true,
					   			readOnly:true,
			              required:true}},
					  {field:'pmoney',header:'曝险金额',
					   		formEditorConfig:{
			              required:true}},
					  /* {field:'fminfo',header:'负面信息查询',
					   		formEditorConfig:{
			              required:true}}, */
					  {field:'isexist',header:'逾期天数有无超过7天',
					   		formEditorConfig:{
					   		 type:'combobox',
					   		 newLine:true,
					   			data: isexist,
			              required:true}},
			              {field:'bnsresultname',header:'业务单位意见',
								formEditorConfig:{
									fieldVisible:false,
									required:true,
									fillFromFieldName:'bnsresult',
									fillProperty:'name',
									}},
						  {field:'bnsresult',header:'业务单位意见',visible:false,
								formEditorConfig:{
									type:'combobox',
									required:true,
									textField:'name',
									valueField:'value',
									fieldVisible:true,
									params:{pid: 'fushen', xmlFileName:'combos/comboDict.xml'}}},
					  {field:'bnsresultmemo',header:'业务部处理结论',
					   		formEditorConfig:{
					   			type:'textarea',
					            newLine:true,
					            readOnly:true,
								maxLength:500,
			              required:true,colspan:3,
							type:'textarea' ,width:'100%'}},
							{field:'riskresultname',header:'风控意见',
								formEditorConfig:{
									fieldVisible:false,
									required:true,
									newLine:true,
									fillFromFieldName:'riskresult',
									fillProperty:'name',
									}},
							  {field:'riskresult',header:'风控意见',visible:false,
									formEditorConfig:{
										type:'combobox',
										required:true,
										textField:'name',
										valueField:'value',
										fieldVisible:true,
										params:{pid: 'fushen', xmlFileName:'combos/comboDict.xml'}}},
					  {field:'riskresultmemo',header:'风控部处理结论',
					   		formEditorConfig:{
					   			type:'textarea',
					            newLine:true,
					            colspan:3,
					            readOnly:true,
					            maxLength:500,
								type:'textarea',
								required:true,
								width:'100%'}},
					  {field:'endtime',header:'提交覆审时间',dateFormat:'yyyy-MM-dd HH:mm:ss',
					   		formEditorConfig:{
					   			type:'date',
		   			           format:'yyyy-MM-dd HH:mm:ss',
		   			               vtype:'date',
		   			               readOnly:true,
					              newLine:true,
			              required:true}},
					  {field:'fstatus',header:'覆审状态',
						   		       formEditorConfig:{
					   			           readOnly:false,
					   			        fieldVisible:false,
					   			               data:localEnabled,
					   			               type:'combobox'},queryConfig:{
					   			            	   newLine:true,
					   			            	   data:localEnabled,
						   			               type:'combobox'}},
				   	{field:'patrolpoint',header:'情况说明',formEditorConfig:{newLine:true,colspan:3,
							type:'textarea' ,width:'100%'}},
					{field:'deptname',header:'覆审单位',
				   		formEditorConfig:{
				   			labelWidth:130,
			              required:true,
			              newLine:true,
			              readOnly:true,
			            allowInput:false},queryConfig:{}}
		        	
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>