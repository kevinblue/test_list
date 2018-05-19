<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>保险清单</title>
		<%@include file="/base/mini.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
  		<script type="text/javascript">
  		jQuery(function() {
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
  				  var userEnabled = [{id:'0',text:'是'}, {id:'1',text:'否'}];
  				  var getUserEnabled = function(e) {
  					    var code = e.record.isinsure;
                        for (var i = 0; i < userEnabled.length; i++) {  						
  						   if (userEnabled[i]['id'] == code) {
  							  return userEnabled[i]['text'];
  						 }
  					 }
  					 return '';};
  					 
	  				new ApTable({
	  					id:'table_insuranceinfo_id',
	  					renderTo:"id_table_render_table1",
	  					width:globalClientWidth,
	  					height:globalClientHeight,
	  					iconCls:'icon-node',
	  					hiddenQueryArea:false,
	  					multiSelect:true,
	  					queryButtonColSpan:4,
	  					queryButtonNewLine:false,
	  					editFormPopupWindowWidth:800,
	  					editFormPopupWindowHeight:450,
	  					title:'保险清单' ,
	  					remoteOper:true,
	  					pageSize:15,
	  					showPager:true,
	  					lazyLoad:false,
	  					entityClassName:'com.tenwa.leasing.entity.insurance.InsuranceInfo',
	  					frozenStartColumn:0,
	  					frozenEndColumn:5,  					
	  					xmlFileName:'/eleasing/jsp/insure_manage/insurance_info_list.xml',
	  					tools:[ 'add', '-', 'edit', '-', 'remove'],
	  					columns:[ 
	  					    {type:'indexcolumn'},
	  					   	{type:'checkcolumn'}, 
	  					    {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
	  							     formEditorConfig:{
	  								         readOnly:true,
	  								     fieldVisible:false}},
	  						{field: 'contractidname_', header: '合同号', visible: true,width:150,
	  							     formEditorConfig:{
	  								     fieldVisible:false},
	  					   		          queryConfig:{  	
	  					   			           width :200}},
	  					   	{field: 'contractnumbername', header: '业务合同号',visible: false,formEditorConfig:{fieldVisible: false}},
	  						{field: 'contractnumber', header: '业务合同号', visible: true,
	  						  			  queryConfig:{  
	  			                           labelWidth:100,
						   			            width:200},
		  						     formEditorConfig:{
		  						    	newLine:true,
  						                width: 200,
  						                 type:'queryinput',
  						             required: true,
  						            textField: 'contractnumber',
  						           valueField: 'contractid',
  						           allowInput: false,
  						         fieldVisible: true,
  						             onSelect:function($me, queryInput, rowData){
  							                   mini.getbyName("custname").setValue(rowData.custname);
  							                   mini.getbyName("contractid").setValue(rowData.contractid);
  							                   mini.getbyName("insurancemoney").setValue(rowData.equipamt);},
  						               params: {xmlFileName: '/eleasing/jsp/insure_manage/insurance_contractId.xml'}
	  								 }},
		  					{field: 'contractid', header: '合同号', visible: false,
	  					  			 formEditorConfig:{
	  					  				fieldVisible:false 
		  						              },
						   		          queryConfig:{  	
						   			            width:200}},
	  					    {field:'custname',header:'客户名',headerAlign:'center',visible: true,width:150, 
	  					   		     formEditorConfig:{	
	  					   		                 type:'text',
									       labelWidth:100,
									            width:200,
									         required:true,	
									         readonly:true},
	  					   		          queryConfig:{  	
	  					   			            width:200}},  					   	
						   	{field:'isinsure',header:'是否投保',headerAlign:'center',visible: true,width:100, renderer:getUserEnabled,  					    	
	  					    	     formEditorConfig:{
	  								          newLine:true,
	  								       labelWidth:100,
	  								            width:200,
	  								         required:true,
	  								             type:'combobox',
	  								             data:userEnabled}},				 	   	
						   	{field:'insurancename',header:'保险公司',headerAlign:'center',visible: true,width:100,					   		
						   		     formEditorConfig:{								
						   		 	             type:'text',
									       labelWidth:100,
									            width:200,
									         required:true},
								          queryConfig:{  
                                              newLine:true,
									            width:200}},
						   	{field:'insuranceid',header:'保险单号',headerAlign:'center',visible: true,width:100,					   		
						   		     formEditorConfig:{
		  								      newLine:true,
		  								         type:'text',
		 								   labelWidth:100,
		 								        width:200,
		  								     required:true}},
						   	{field:'isurancetype',header:'险种',	headerAlign:'center',visible: true,width:100,
						   		     formEditorConfig:{
									         required:true,							
									             type:'text',
									       labelWidth:100,
									            width:200}},					   	
						   	{field:'insurerstartdate',header:'保险生效日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd",
							         formEditorConfig:{
									          newLine:true,
									             type:'date',
									         required:true,								
									       labelWidth:100,
									            width:200,
									           format:'yyyy-MM-dd',
									       allowInput:"false",
									  otherAttributes:'onvaluechanged="judgedate"'}},
						   	{field:'insurerenddate',header:'保险终止日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd",
						   		     formEditorConfig:{
							             type:'date',
							         required:true,								
							       labelWidth:100,
							            width:200,
							           format:'yyyy-MM-dd',
							       allowInput:"false"}},
							{field:'firstbeneficiary',header:'第一受益人',headerAlign:'center',visible: true,width:100,
								     formEditorConfig:{
	  								          newLine:true,
	  								             type:'text',
	  								         required:true,
	 								       labelWidth:100,
	 								            width:200 }},
						    {field:'insurancepremium',header:'保险费',headerAlign:'center',visible: true,width:100,
		  						     formEditorConfig:{
		  							         required:true,
		  						                 type:'text',
									       labelWidth:100,
									            width:200,
										         vtype:"float"}},					   
						    {field:'insurancepremiumrate',header:'保险费率',headerAlign:'center',visible: true,width:100,					    	
						    	     formEditorConfig:{
	  								          newLine:true,
	  								             type:'text',
	 								       labelWidth:100,
	 								            width:200,
										         vtype:"float"}},
						    {field:'insurancemoney',header:'保险金额',headerAlign:'center',visible: true,width:100,					   		
						   		     formEditorConfig:{	
	  					   		                 type:'text',
									       labelWidth:100,
									            width:200,	
									         readonly:true
					   		     	}},					   
						   	{field:'insurergetdate',header:'收保单日期',dateFormat:"yyyy-MM-dd",
						   		     formEditorConfig:{
									          newLine:true,
									             type:'date',
									       allowInput:"false",
									          equired:true, 
									            width:200,
									       labelWidth:100,								
									           format:'yyyy-MM-dd'}},
						    {field:'surrenderobject',header:'退保对象',headerAlign:'center',visible: true,width:100 ,
		  						     formEditorConfig:{		  						
			  						             type:'text',
									       labelWidth:100,
									            width:200}},
						    {field:'surrendermoney',header:'退保金额',headerAlign:'center',visible: true,width:100,
						    	     formEditorConfig:{
			  						          newLine:true,
			  						             type:'text',
									       labelWidth:100,
									          colspan: 3,
									            width:200,
										         vtype:"float" }},
						    {field:'leasedproperty',header:'租赁物件',headerAlign:'center',visible: true,width:100,
						    	      formEditorConfig:{
		  							           newLine:true,
		  							           colspan: 3,							
									             width:"100%",
		  							              type:'textarea'} },
						    {field:'surrenderlog',header:'退保日志',headerAlign:'center',visible: true,width:100,
						    	      formEditorConfig:{
		  							           newLine:true,
		  							           colspan: 3,							
									             width:"100%",
		  							              type:'textarea'}},
						    {field:'memo',header:'备注',headerAlign:'center',visible: true,width:100,formEditorConfig:{
		  							           newLine:true,
		  							           colspan: 3,							
									             width:"100%",
		  							              type:'textarea'}}					    
						    ]
	  				});
	  			});
	  			
	  		});	
  		function judgedate(e){ 
  			var start=mini.getbyName("insurerstartdate").getValue();
  			mini.getbyName("insurerenddate").setMinDate(start);  			
  		}
  		</script>
	</head>
<body>
<div id="mini_test_form">
	<div id="id_table_render_table1"></div>
</div>
</body>
</html>  	       