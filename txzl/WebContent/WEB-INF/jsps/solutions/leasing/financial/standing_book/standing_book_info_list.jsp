<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保理台账</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<body style="overflow:hidden;"> 
   <script type="text/javascript" defer>
   jQuery(function(){
		    seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				showPager : false,
				lazyLoad : false,
				title:'保理台账',
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.finacial.ContractFactoring",
				multiSelect: false,
				xmlFileName:"/eleasing/jsp/finacial/standing_book/standing_book_list.xml",
				tools : [ 'add', '-', 'edit', '-', 'remove'],
				queryButtonColSpan : 6,
				queryButtonNewLine : true,
				columns: [
					{
						type : 'indexcolumn',width:40
						
					},
					{
						type: 'checkcolumn',width:40
						
					},
				    {
						field: 'id',
						header: '主键',
						visible:false,
						formEditorConfig : {visible:false}
					}, 
					 
				    {
						field : 'contractidname',
						width:110,
						header : '业务合同编号',
						visible : true,
						queryConfig : {width:200},
						formEditorConfig : {
							fieldVisible : false
						}
					},
					{
						field : 'contractid',
						header : '业务合同编号',
						queryConfig : {width:200},
						visible : false,
						formEditorConfig : {
							width : 200,
							type : 'queryinput',
							required : true,
							textField : 'name',
							valueField : 'value',
							allowInput : true,
							fieldVisible : true,
							onSelect : setRegistcode,
							params : {      
								xmlFileName : '/eleasing/jsp/finacial/standing_book/standing_book_msg.xml'
							}
						}
					},
					
				    {
						field: 'entryname',
						header: '项目名称',
						queryConfig : {width:210},
						formEditorConfig : {
							readOnly : true,
							labelWidth:100
							}
					},
					
					{
				    	field: 'custname',
				    	header: '客户名称',
				    	queryConfig : {width:210},
				    	formEditorConfig : {
				    		readOnly : true,
				    		newLine:true,
				    		labelWidth:100
				    		}
				    },
					
					{
				    	field: 'financingamount', 
				    	header: '融资额',
				    	dataType:"currency",
				    	visible : true,
				    	formEditorConfig : {
				    		readOnly : true,
							fieldVisible : true
						}
					},
					
					{ 
						field: 'leaseterm', 
						header: '租赁期限',
						formEditorConfig : {
							    readOnly : true,
								newLine : true,
								labelWidth : 100,
								width : '100%',		
						}
					},
				
					{         
						field : 'iffactoring',
						header : '是否保理',
						visible : true,
						formEditorConfig : {
							type : 'combobox',
							textField : 'name',
							required : true,
							valueField : 'value',
							fieldVisible : true,
					        data : [{value:'否',name:'否'},{value:'是',name:'是'}]
						}
					},
					
					{
				    	field: 'factoringbank',
				    	queryConfig : {
				    		newLine:true,
				    		width:200
				    	},
				    	newLine:true,
				    	header: '保理银行',
				    	formEditorConfig : {
				    		required : true,
				    		newLine:true,
				    		labelWidth:100
				    		}
				    },
					
					{
				    	field: 'factoringmoney',
				    	header: '保理金额',
				    	dataType:"currency",
				    		formEditorConfig : {
				    			required : true,
								vtype : 'float',
					    		labelWidth:100
					    		}
				    },
						
			
						
					{
						field : 'factoringbegindate',
						header : '保理开始日期',
						headerAlign : 'center',
						visible : true,
						width : 100,
						dateFormat : "yyyy-MM-dd",
						formEditorConfig : {
							newLine : true,
							type : 'date',
							labelWidth : 100,
							width : '100%',
							format : 'yyyy-MM-dd',
							required : true
						},
						queryConfig : {
							type : 'date',
							range : true,
							format : 'yyyy-MM-dd',
							newLine : false
						}
					},
					
					
						
						
						{
							field : 'factoringenddate',
							header : '保理结束日期',
							headerAlign : 'center',
							visible : true,
							width : 100,
							dateFormat : "yyyy-MM-dd",
							formEditorConfig : {
								newLine : false,
								type : 'date',
								labelWidth : 100,
								width : '100%',
								format : 'yyyy-MM-dd',
								required : true
							},
							queryConfig : {
								type : 'date',
								range : true,
								format : 'yyyy-MM-dd',
								newLine : false
							}
						},
							
				    {
							field: 'remark', 
							header: '备注',
							formEditorConfig : {
								type:'textarea',
								newLine : true,
								colspan:3,
								labelWidth : 100,
								width : '100%',
						}
				    }	
				]
				
			});
		});
	});
   
     //选择合同编号后自动带出注册号
	function setRegistcode($me, inputObj, rowData) {
		mini.getbyName("entryname").setValue(rowData.entryname);
		mini.getbyName("custname").setValue(rowData.custname);
		mini.getbyName("financingamount").setValue(rowData.financingamount);
		mini.getbyName("leaseterm").setValue(rowData.leaseterm);
	} 
   
</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
</body>
</html>