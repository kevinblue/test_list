<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<title>保险理赔</title>
		<%@include file="/base/mini.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
  		<script>
  		jQuery(function() {
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
  				new ApTable({
  					id : 'table_insuranceclaim_id',
  					width : globalClientWidth,
  					height : globalClientHeight,					
  					editFormPopupWindowWidth : 700,
  					editFormPopupWindowHeight:450,
  					iconCls : 'icon-node',
  					title:'保险理赔',
  					multiSelect : true,
  					queryButtonColSpan : 4,
  					queryButtonNewLine:false,
  					remoteOper : true,
  					entityClassName : 'com.tenwa.leasing.entity.insurance.InsuranceClaim',
  					//entityBeanCallBackClassName : 'com.tenwa.business.callback.InsuranceClaimCallBack',
  					frozenStartColumn:0,
  					frozenEndColumn:4,
  					showPager:true,
  					pageSize : 15,
  					xmlFileName : '/eleasing/jsp/insure_manage/insurance_claim_list.xml',  					
  					tools : [ 'add', '-', 'edit', '-', 'remove'],					
  					columns : [ 
  						{type:'indexcolumn'},
  						{type:'checkcolumn'}, 
  						{field:'id',header:'id',headerAlign:'center',allowSort:true,visible:false, width:100,
  							    formEditorConfig:{
  								        readOnly:true,
  								    fieldVisible:false }},
  						{field:'contractidname', header:'合同号', visible:true,width:150,
  							    formEditorConfig:{
  								    fieldVisible:false},
  					   		         queryConfig:{  	
  					   			           width:200}},
  						{field:'contractnumber', header:'业务合同号', visible:true,width:150,
  							    formEditorConfig:{
  								        readOnly:true},
  					   		         queryConfig:{  	
  					   		          labelWidth:100,
  					   			           width:200}},
  						{field:'custname',header:'客户名称',headerAlign:'center',visible:true,width:100,
  							    formEditorConfig:{
								        readOnly:true,
								      labelWidth:100,
								           width:200,								
	  							            type:'text',
								        required:true},
							         queryConfig:{
								           width:200}},
						{field:'insuranceidname', header:'保险单号', visible:true,
									 queryConfig:{  
  							          labelWidth:100,
  							             newLine:true,
					   			           width:200},
	  						    formEditorConfig:{
  								    fieldVisible:false }},
	  					{field:'insuranceid', header:'保险单号', visible:false,
  					  			formEditorConfig:{
	  						             newLine:true,
	  						               width:200,
	  						                type:'queryinput',
	  						            required:true,
	  						           textField:'insuranceid',
	  						          valueField:'insid',
	  						          allowInput:false,
	  						        fieldVisible:true,
	  						            onSelect:function($me, queryInput, rowData){
						  							mini.getbyName("insurancename").setValue(rowData.insurancename);
						  							mini.getbyName("custname").setValue(rowData.custname);
						  							mini.getbyName("contractnumber").setValue(rowData.contractnumber);},
	  						              params:{xmlFileName:'/eleasing/jsp/insure_manage/insurance_claim_insuranceId.xml'}}},
						{field:'insurancename',header:'保险公司',headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{
								         required:true,
								       labelWidth:100,
								            width:200,
								         readOnly:true},
							          queryConfig:{
								            width:200}},
						{field:'recordnumber',header:'赔案号',headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{
 								          newLine:true,
 								             type:'text',
 								       labelWidth:100,
 								            width:200,	
 								         required:true},
							          queryConfig:{
								            width:200}},
						{field:'reporter',header:'报案人',headerAlign:'center',visible:true,width:100,							
							     formEditorConfig:{
								             type:'text',
 								       labelWidth:100,
 								            width:200}},
						{field:'claimsbegindate',header:'出险时间',dateFormat:"yyyy-MM-dd ",headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								      allowInput:"false",
								         newLine:true,
								            type:'date',
 								      labelWidth:100,
 								           width:200},
							         queryConfig:{
								         newLine:true,
								           range:true,
								            type:'date',
								          format:'yyyy-MM-dd',
								      allowInput:"false"}},
						{field:'claimsaddress',header:'出险地点',headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								            type:'text',
 								      labelWidth:100,
 								           width:200 }},		
						{field:'claimsmanager',header:'经办人',headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								         newLine:true,
								            type:'text',
 								      labelWidth:100,
 								           width:200}},
						{field:'danger',header:'赔付险种',headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								            type:'text',
 								      labelWidth:100,
 								           width:200 }},		
						{field:'claimstime',header:'赔偿日期',dateFormat:"yyyy-MM-dd ",headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{
								       allowInput:"false",
								          newLine:true,
								             type:'date',
								           format:'yyyy-MM-dd ',
								       labelWidth:100,
	 							            width:200}},
						{field:'claimsmoney',header:'赔款金额(元)',headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{								
								             type:'text',
 								       labelWidth:100,
 								            width:200}},
						{field:'claimsmoneydeal',header:'处理方式',headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{
								          newLine:true,
								          colspan:3,
								             type:'text',
 								       labelWidth:100,
 							 	            width:200 }},
						{field:'claimsinfo',header:'出险原因',headerAlign:'center',visible:true,width:100,
							     formEditorConfig:{
								          newLine:true,
	  							             type:'textarea',	
	  							          colspan:3,
	  							            width:"100%"}},
						{field:'claimsafter',header:'出险经过及损失情况',	headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								         newLine:true,
								            type:'textarea',
								         colspan:3,
	  							           width:"100%"}},
						{field:'remark',header:'备注',headerAlign:'center',visible:true,width:100,
							    formEditorConfig:{
								         newLine:true,
								            type:'textarea',
								         colspan:3,
	  							           width:"100%"}}
					]
				});
			});
			
		});
		</script>
	</head>
<body></body>
</html>