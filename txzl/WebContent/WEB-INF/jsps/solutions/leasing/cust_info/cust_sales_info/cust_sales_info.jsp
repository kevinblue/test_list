<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">

	var tools = [];
	<permission:action code="cust_saleInfo_add">
	tools.push('add');
	</permission:action>
	<permission:action code="cust_saleInfo_edit">
	tools.length>0?tools.push('-','edit'):tools.push('edit');
	</permission:action>
	<permission:action code="cust_saleInfo_remove">
	tools.length>0?tools.push('-',"remove"):tool.push('remove');
	</permission:action> 

jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id8',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'content_table_id8',
			hiddenQueryArea:true,
			frozenStartColumn:1,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			showToolbar: showTools,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.cust.other.CustSalesInfo',
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			xmlFileName:'/eleasing/jsp/cust_info/cust_salesinfo/cust_salesinfo_list.xml',
			params:{custid:custid},
			tools:tools,
			columns:[ 
				{type:'indexcolumn'},
				{type:'checkcolumn'}, 
				{field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				        formEditorConfig:{
					            readOnly:true,
					        fieldVisible:false }}, 
			    {field:'custid',header:'客户ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				        formEditorConfig:{
					            readOnly:true,
					        fieldVisible:false, 
					               value:custid}},
				{field:'companyname',header:'公司名称',headerAlign:'center',width:100,allowSort:true,
								        formEditorConfig:{
									                type:'text',
									                colspan:4,
									                required:true,
									        fieldVisible:true,
									               width:510,
									          labelWidth:100}},
		        {field:'companynumber',header:'公司编号',headerAlign:'center',width:100,allowSort:true,
				        formEditorConfig:{
					            required:true,
					             newLine:true,
					                type:'text',
					        fieldVisible:true,					
					               width:200,
					          labelWidth:100}},
			   
			   {field:'contractmoney',header:'合同金额',headerAlign:'center',width:100,allowSort:true,
							        formEditorConfig:{
								             newLine:false,
								                type:'text',
								        fieldVisible:true,
								               vtype:"float",
								               width:200,
								          labelWidth:100}},	          
			    {field:'monthavgmoney',header:'月平均往来金额',headerAlign:'center',width:100,allowSort:true,
				        formEditorConfig:{
					             newLine:true,
					                type:'text',
					        fieldVisible:true,
					               vtype:"float",
					               width:200,
					          labelWidth:100}},
			    {field:'paymentconditions',header:'收款条件',headerAlign:'center',width:100,allowSort:true,
				        formEditorConfig:{
					                type:'text',
					        fieldVisible:true,
					               width:200,
					          labelWidth:100}},
			    {field:'accountingrevenueradio',header:'占营收比率(%)',headerAlign:'center',width:100,allowSort:true,
				        formEditorConfig:{
					             newLine:true,
					                type:'text',
					                vtype:"float",
					                required:true,
					        fieldVisible:true,
					               width:200,
					          labelWidth:100}},
			    {field:'salesproject',header:'货物名称',headerAlign:'center',width:100,allowSort:true,
				        formEditorConfig:{
					                type:'text',
					        fieldVisible:true,
					               width:200,
					          labelWidth:100}},										
			   {field: 'creatorname',header:'登记人',headerAlign:'center' ,width:100,allowSort:true,	
				        formEditorConfig:{
					             newLine:true,
					                type:'text',
					            readOnly:true,
					               value:'${sessionScope.loginUser.realname}',
					          labelWidth:100,								
					               width:200}},						
			   {field: 'createdate',header:'登记时间',headerAlign:'center', dateFormat:"yyyy-MM-dd ",width:100,allowSort:true, 							
				        formEditorConfig:{								
					                type:'text',
					            readOnly:true,		
					        defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
					          labelWidth:100,								
					               width:200}},
			  {field: 'modificatorname',header:'修改人',	headerAlign:'center' ,width:100,visible:false,allowSort:true,
				        formEditorConfig:{
					             newLine:true,
					                type:'text',
					            readOnly:true, 
					        fieldVisible:true,
					          labelWidth:100,								
					               width:200}},
			  {field: 'modifydate',header:'修改时间',headerAlign:'center', visible:false,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
				        formEditorConfig:{									
					                type:'text',
					            readOnly:true,
				 	        fieldVisible:true,
					          labelWidth:100,								
					               width:200} 				   		
			}]
		});
	});
});
</script>
<div id='content_table_id8'></div>