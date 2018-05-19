<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
<%@include file="/base/mini.jsp"%>
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>

<script type="text/javascript">		

	var custcontactData;
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 								
  				new ApTable({ 					
  					id:'table_id5',					
  					renderTo:'content_table_id5',
  					width:globalClientWidth,
  					height:globalClientHeight,					 															
  					iconCls:'icon-node',
  					hiddenQueryArea:false,
  					multiSelect: false,
  					showToolbar: showTools,//是否显示表格按钮行
  					title:'合同条件维护',//修改显示页面的标题
  					queryButtonColSpan:4,
  					remoteOper:true,//是否每次操作都交给后台，为true 时必须定义  entityClassName
  					entityClassName:'com.tenwa.leasing.entity.contract.ContractConditionMaintenance',
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false, 	
  					xmlFileName:'/eleasing/workflow/contract/contract_condition/contract_condition_query.xml', 	
  					//params:{custid:custid},
  				
  					tools:[ 'add', '-', 'edit', '-', 'remove','-'],     
  					columns:[ 
						{type:'checkcolumn'},
  					    {type:'indexcolumn',header:'序号'},
  						{field:'id',header:'id',headerAlign:'center',visible:false},	
  						{field:'conditionname',header:'条件名称',  								
  								headerAlign:'center',
  								 width:150,
  								 allowSort:true,
  								 queryConfig : {},
  							    formEditorConfig:{								
							         type:'textarea',								
							     	width:200,
							     
 							           }},  	
  						 {field:'contracttype',header:'合同类型',
  							      headerAlign:'center',
  							      width:50,
  							      allowSort:true,
  							      queryConfig : {
  							    	 type:'combobox',
							         width:150,
							         valueField:'id', 
							         textField:'text',
									  data:[{id:'租赁合同',text:'租赁合同'},{id:'采购合同',text:'采购合同'}]
 							           
  							      },
  							    formEditorConfig:{
							         type:'combobox',
							         width:200,
							         valueField:'id', 
							         textField:'text',
									  data:[{id:'租赁合同',text:'租赁合同'},{id:'采购合同',text:'采购合同'}]
 							           } }, 	  			  						  			     
  			  			{field:'paymentcategory',header:'款项类别',
	  			  				    	headerAlign:'center',
	  			  				    	width:150,
	  			  				    	allowSort:true, 
	  			  				       queryConfig : {},
	  			  				    	formEditorConfig:{
	  			  				    		newLine:true,
	  			  				    		type:'textarea',								
								     		width:200
	  		  							           }},  
  			  			 {field:'conditiontype',header:'条件类型',
  			  			  		  			 headerAlign:'center',
  			  			  		  			 width:50,
  			  			  		  			 allowSort:true,
  			  			  		  			 queryConfig : {
  			  			  		  				 newLine:true,
  			  			  		          	 type:'combobox',
  									      	 headerAlign:'center',
  									    		 valueField:'id', 
  								         		textField:'text',
  												data:[{id:'前置',text:'前置'},{id:'后置',text:'后置'}]
  			  			  		  				 
  			  			  		  			 },
  			  			  		  	    formEditorConfig:{		
  									         type:'combobox',
  									       headerAlign:'center',
  									    	 width:200,
  									    	 valueField:'id', 
  								         		textField:'text',
  												data:[{id:'前置',text:'前置'},{id:'后置',text:'后置'}]
  		  							           }} 
  					]					
  				});
  			});
  		});
	</script>
<div id='content_table_id5' style="width:300px;height:200px;" ></div>

</head>
<body></body>
</html>