<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>保险覆盖融资期限</title>
		<%@include file="/base/mini.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
  		<script type="text/javascript">
			
  		jQuery(function() {
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {  
  				new ApTable({
  					id:'table_insurancecover_id',
  					width:globalClientWidth,
  					height:globalClientHeight,					  					
  					editFormPopupWindowWidth:700,
  					editFormPopupWindowHeight:450,
  					iconCls:'icon-node',
  					title:'保险覆盖融资期限',
  					queryButtonColSpan:4,
  					queryButtonNewLine:false,
  					remoteOper:true,
  					entityClassName:'com.tenwa.leasing.entity.insurance.InsuranceCover',  					 					
  					entityBeanCallBackClassName:'',//'com.tenwa.business.callback.InsuranceCoverCallBack',  					
  					frozenStartColumn:0,
  					frozenEndColumn:5,  	 					
  					showPager:true,
  					pageSize:15,
  					xmlFileName:'/eleasing/jsp/insure_manage/insurance_cover_list.xml', 
  					columns:[ 
  					    {type:'indexcolumn'},
  					   	{type:'checkcolumn'}, 
  					    {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false ,
  							 formEditorConfig:{
  								     readOnly:true,
  								 fieldVisible:false}},
  						{field:'custname',header:'客户名',headerAlign:'center',visible:true,width:100,
	  					     formEditorConfig:{
						   		         type:'text',  
						   		     readOnly:true,
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200},
							         renderer:function(e){
												var custname = e.record.custname;
												var contractid = e.record.contractid;
												var contractnumber = e.record.contractnumber;
												return "<a href='javascript:void(0);' onclick='opencustdetail(\"" + contractid + "\",\"" + contractnumber + "\")'>" + custname + "</a>";},
							      queryConfig:{		
								        width:200}} ,	
  						{field: 'contractid_',header:'合同编号',headerAlign:'center',visible:true,width:100,
  					   		 formEditorConfig:{
  					   			         type:'text',  
  					   			     readOnly:true,
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200},
							      queryConfig:{	
								        width:200}},  	
  						{field:'contractnumber', header: '业务合同号', visible: true,
					  			  queryConfig:{  
 							       labelWidth:100,
				   			            width:200},
  						     formEditorConfig:{
 								 fieldVisible:false }},
					   	{field:'startdate',header:'租赁起始日',headerAlign:'center',visible:true,width:100, dateFormat:"yyyy-MM-dd",
  					     	 formEditorConfig:{
  					     		         type:'date',
  					     		      newLine:true,
  					     		     readOnly:true,
						   		       format:'yyyy-MM-dd',
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200}},
					   	{field:'enddate',header:'租赁截至日',headerAlign:'center',visible:true,width:100,dateFormat:"yyyy-MM-dd ",
					   	 	 formEditorConfig:{
					   			         type:'date',  
					   			     readOnly:true,
						   		       format:'yyyy-MM-dd',
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200}},					   	
					   	{field:'insurerstartdate',header:'保险生效日',headerAlign:'center',visible:true,width:100, dateFormat:"yyyy-MM-dd ",
					   		 formEditorConfig:{
					   			      newLine:true,
					   			         type:'date', 
					   			     readOnly:true,
						   		       format:'yyyy-MM-dd',						   		
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200}},
					   	{field:'insurerenddate',header:'保险截止日',headerAlign:'center',visible:true,width:100, dateFormat:"yyyy-MM-dd " ,
					   		 formEditorConfig:{
					   			         type:'date',  
					   			     readOnly:true,
						   		       format:'yyyy-MM-dd',
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200 }},
					   	{field:'leaseterm',header:'融资期限(月)',headerAlign:'center',visible:true,width:100 ,
	  					   	 formEditorConfig:{
						   		         type:'text',  
						   		     readOnly:true,
								 fieldVisible:true ,
								   labelWidth:100,
								        width:200,
								      newLine:true,
								      colspan:3},
							      queryConfig:{	
								   labelWidth:100,
								        width:200,
								      newLine:true}},
					    {field:'handledate',header:'最后处理日期',headerAlign:'center',visible:true,width:100,dateFormat:"yyyy-MM-dd " ,
  					  		 formEditorConfig:{
  					  			         type:'date',  
					   			       format:'yyyy-MM-dd',  								
								 fieldVisible:true ,
								   allowInput:'false',
								   labelWidth:100,
								        width:200}}
					    ]
  				});
  			});
  			
  		});
  		function opencustdetail(contractid,contractnumber){
  			var params = "contractid="+contractid+"&contractnumber="+contractnumber;
  			var url = getRootPath()+"/leasing/insure_manage/insurance_cover_details.bi?"+params;
  			var sheight = window.screen.availHeight - 30;
  			var swidth = window.screen.availWidth - 10;
  			var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
  				window.open(url, '_blank', winoption);
  		}
  		</script>
	</head>

<body></body>
</html>  	       