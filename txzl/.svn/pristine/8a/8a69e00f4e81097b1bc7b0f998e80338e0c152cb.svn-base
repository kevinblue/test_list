<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

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
  		var tools = [];
  			/* tools.push('add'); */
  			<permission:action code="insurance_add">
  			tools.push('add');
  			</permission:action>
  			<permission:action code="insurance_edit">
  			tools.length>0?tools.push('-','edit'):tools.push('edit');
  			</permission:action>
  			<permission:action code="insurance_remove">
  			tools.length>0?tools.push('-',"remove"):tool.push('remove');
  			//tools.push('remove');
  			</permission:action>
  			<permission:action code="insurance_exportexcel">
  			tools.length>0?tools.push('-','exportExcel'):tool.push('exportExcel');
  			</permission:action>
  		jQuery(function() {
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
  				  var userEnabled = [{id:'是',text:'是'}, {id:'否',text:'否'}];
  				  var ispayment = [{id:'是',text:'是'}, {id:'否',text:'否'}];
  				  var getUserEnabled = function(e) {
  					    var code = e.record.ispaymentpremium;
                        for (var i = 0; i < ispayment.length; i++) {  						
  						   if (ispayment[i]['id'] == code) {
  							  return ispayment[i]['text'];
  						 }
  					 }
  					 return '';};
  					var getIsPayment = function(e) {
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
	  					loadMode:'ajax',
	  					entityClassName:'com.tenwa.leasing.entity.insurance.InsuranceInfo',
	  					entityBeanCallBackClassName : 'com.tenwa.business.callback.InsuranceInfoBeanCallBack',
	  					frozenStartColumn:0,
	  					frozenEndColumn:5,  					
	  					xmlFileName:'/eleasing/jsp/insure_manage/insurance_info_list.xml',
	  					tools:tools,
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
						   	{field:'isinsure',header:'是否我司投保',headerAlign:'center',visible: true,width:100, 				    	
	  					    	     formEditorConfig:{
	  								          newLine:true,
	  								       labelWidth:100,
	  								            width:200,
	  								         required:true,
	  								       defaultValue:'0',
	  										showNullItem : false,
	  								             type:'combobox',
	  								             data:userEnabled}},
	  						{field:'ispaymentpremium',header:'是否已支付保费',headerAlign:'center',visible: true,width:100,	    	
	  				  				formEditorConfig:{
	  				  						labelWidth:100,
	  				  							 width:200,
	  				  						//	textField: 'text',
												required: true,
											//    valueField: 'id',
	  				  						type:'combobox',
	  				  						data:ispayment}},
						   	{field:'insurancename',header:'保险公司',headerAlign:'center',visible: true,width:100,					   		
						   		     formEditorConfig:{		
						   		    	       colspan:3,
						   		    	       newLine:true,
						   		 	             type:'text',
									       labelWidth:100,
									            width:'100%',
									         required:true},
								          queryConfig:{  
                                              newLine:true}},
                            {field: 'isurancetypename', header:'险种', visible: true,
           									   formEditorConfig:{
           											fieldVisible: false,
           											fillFromFieldName : 'isurancetype',
           											fillProperty : 'name'								 
           					}},
           					{field : 'isurancetype',header : '险种',visible: false,
           									  formEditorConfig : {
           										    newLine:true,
           											type : 'combobox',
           											textField: 'name',
           											required: true,
           										    valueField: 'value',
           											fieldVisible: true,
           											params:{pid: 'insurancetype',xmlFileName: '/combos/comboDict.xml'}			                 
           					}},
						   	{field:'insuranceid',header:'保险单号',headerAlign:'center',visible: true,width:100,					   		
						   		     formEditorConfig:{
		  								         type:'text',
		 								   labelWidth:100,
		 								        width:200,
		  								     required:true}},
							
						   	{field:'insurerstartdate',header:'保险生效日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd",
							         formEditorConfig:{
									          newLine:true,
									             type:'date',
									         required:true,								
									       labelWidth:100,
									            width:200,
									           format:'yyyy-MM-dd',
									       allowInput:"false"}},
							{field:'insurerenddate',header:'保险终止日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd",
										formEditorConfig:{
												newLine:true,
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
	 						{field:'insurancemoney',header:'保额(设备款)',headerAlign:'center',visible: true,width:100,					   		
	 									   		     formEditorConfig:{	
	 				  					   		                 type:'text',
	 												       labelWidth:100,
	 												            width:200,	
	 												         readonly:true
	 						}},	
						    {field:'insurancepremium',header:'保费',headerAlign:'center',visible: true,width:100,
		  						     formEditorConfig:{
		  						          	 newLine:true,
		  							         required:true,
		  						                 type:'float',
									       labelWidth:100,
									            width:200,
										         otherAttributes:"onvaluechanged='insurancepremiumchanged(e)'"}},					   
						    {field:'insurancepremiumrate',header:'保险费率(万分之)',headerAlign:'center',visible: true,width:100,					    	
						    	     formEditorConfig:{
	  								             type:'text',
	 								       labelWidth:100,
	 								            width:150,
										         vtype:"float",
												  otherAttributes:'onvaluechanged="judgedate"'}},				   
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
  		 
  		function insurancepremiumchanged(e){
  			var data=e.sender;
  			//console.info(data);
  			//alert(data.value);
  			//alert(data.value);
  			//var insurancepremium=getMiniEditFormField("table_insuranceinfo_id.insurancepremium").getValue();
  	  		//var insurancepremium = mini.getbyName("insurancepremium").getValue();//保费
  	  		var insurancemoney = mini.getbyName("insurancemoney").getValue();//保额(设备款)
  	  		var insurancepremiumrate = (Number(data.value,2)*10000/Number(insurancemoney,2)).toFixed(2);
  	  		mini.getbyName("insurancepremiumrate").setValue(insurancepremiumrate);       
  	  		
  	  	} 
  		</script>
	</head>
<body>
<div id="mini_test_form">
	<div id="id_table_render_table1"></div>
</div>
</body>
</html>  	       