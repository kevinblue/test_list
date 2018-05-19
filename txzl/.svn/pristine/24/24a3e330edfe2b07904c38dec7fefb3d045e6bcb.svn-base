<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">

	var tools = [];
	<permission:action code="cust_manufacturersInfo_add">
	tools.push('add');
	</permission:action>
	<permission:action code="cust_manufacturersInfo_edit">
	tools.length>0?tools.push('-','edit'):tools.push('edit');
	</permission:action>
	<permission:action code="cust_manufacturersInfo_remove">
	tools.length>0?tools.push('-',"remove"):tool.push('remove');
	</permission:action> 
	
jQuery(function() {
	
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id7',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'content_table_id7',
			hiddenQueryArea:true,
			frozenStartColumn:1,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			showToolbar: showTools,
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.cust.other.CustManufacturersInfo',
			pageSize:15,
			showPager:true,
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_manufacturersInfo/cust_manufacturersInfo_list.xml',
			params:{custid:custid},
			 beforeShowWindowCallBack:function(miniTable, miniForm, operFlag){
					if("edit"==operFlag){
						/* getMiniEditFormField("contractid").setText(mini.getbyName("connameidname").getValue()); */
						mini.getbyName("companynameid").setText(mini.getbyName("companyname").getValue());
						mini.getbyName("companynameid").setValue(mini.getbyName("companyname").getValue());
						//mini.alert("1"+mini.getbyName("companynameid").getValue());
						//mini.alert(mini.getbyName("companynameid").getText());
					}
					return true;
				}, 
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
					 				
					{field:'companyname',header:'公司名称',headerAlign:'center',width:100,visible:true,
				           formEditorConfig:{
					     fillFromFieldName : 'companynameid',
					           fieldVisible:false}},
				{field:'companynameid',header:'公司名称',headerAlign:'center',width:100,visible:false,
		           formEditorConfig:{
			                   type:'queryinput',
			                 required:true,
			          	 fieldVisible:true,
							required : true,
							textField : 'name',
					     	valueField : 'value',
						onSelect : function(
								$me,queryInput,rowData) {
							mini.getbyName("companyname").setValue(rowData.name);
							mini.getbyName("companynumber").setValue(rowData.value);
							},
						params : {
						xmlFileName : '/eleasing/jsp/cust_info/cust_manufacturersInfo/custcompany_query.xml'
						}
					}},
		             {field:'companynumber',header:'社会信用代码',headerAlign:'center',width:100,visible:true,
				           formEditorConfig:{
					               readonly : true,
					                newLine:false,
					                   type:'text'}},
		             {field:'companynumberid',header:'社会信用代码',headerAlign:'center',width:100,visible:false,
				           formEditorConfig:{}},
					/* {field:'projectname',header:'项目名称',headerAlign:'center',width:100,allowSort:true,
							formEditorConfig:{
										newLine:true,
								        type:'text',
										fieldVisible:true,
								        width:200,
										labelWidth:100}},
					{field:'monthavgmoney',header:'合同金额',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                    type:'text',
					            fieldVisible:true,
					                   vtype:"float",
					                   width:200,
					              labelWidth:100}}, */
				  //项目名称下拉框，辉哥说只需要文本，不用下拉
		             /*  {field:'projectname',header:'项目名称',headerAlign:'center',width:100,allowSort:true,
				             formEditorConfig:{
				            	 newLine:true,
					                     type:'combobox',
					                     params: {pid: custid,xmlFileName: '/eleasing/jsp/cust_info/cust_manufacturersInfo/cust_manufacturersInfo_project.xml'},
						                 textField: 'name',
						                valueField: 'value',
					             fieldVisible:true,
					                    width:200,
					               labelWidth:100}},  */    
	               {field:'projectname',header:'项目名称',headerAlign:'center',width:100,allowSort:true,
			             formEditorConfig:{
			            	 newLine:true,
				                     type:'text',				                     
				             fieldVisible:true,
				                    width:200,
				               labelWidth:100}}, 
					 {field:'paymentway',header:'付款方式',headerAlign:'center',width:100,allowSort:true,
				             formEditorConfig:{
				            	 newLine:false,
					                     type:'text',
					             fieldVisible:true,
					                    width:200,
					               labelWidth:100}},
	               
					  {field:'purchaseproject',header:'货物名称',headerAlign:'center',width:100,allowSort:true,
				             formEditorConfig:{
					                     type:'text',
					                     newLine:true,
					             fieldVisible:true,
					                    width:200,
					               labelWidth:100}},
					   {field: 'qualityratename', header: '金风质量评级' ,
						            	 formEditorConfig:{
						            		 fieldVisible: false, 
						            	 }},									             
                       {field: 'qualityrate', header: '金风质量评级',headerAlign:'center',visible:false,width:100,allowSort:true,	 
										   formEditorConfig:{
											   newLine:false,
									             labelWidth:100,
									                  width:200,
									           fieldVisible: true,
									           showNullItem:"true", 
									           nullItemText:"",
									           emptyText:"",
									           type:'combobox',
									           params:{pid: 'cust_qualityrate',xmlFileName: '/combos/comboDict.xml'},
									           textField:'name',
									           valueField:'value'}},
				       
						{field: 'creditratename', header: '天信客户信用评级' ,
									       formEditorConfig:{
									            fieldVisible: false, 
									        }},									             
			             {field: 'creditrate', header: '天信客户信用评级',headerAlign:'center',visible:false,width:100,allowSort:true,	 
											formEditorConfig:{
												newLine:true,
												labelWidth:120,
												width:200,
												fieldVisible: true,
												showNullItem:"true", 
												nullItemText:"",
												emptyText:"",
											    type:'combobox',
												params:{pid: 'credit_rating',xmlFileName: '/combos/comboDict.xml'},
												textField:'name',
												valueField:'value'}},
												
			           {field: 'creatorname',header:'登记人',headerAlign:'center' ,width:100,allowSort:true,	
				             formEditorConfig:{
					                  newLine:false,
					                     type:'text',
					                 readOnly:true,
					                    value:'${sessionScope.loginUser.realname}',
					               labelWidth:100,								
					                    width:200}},						
			           {field: 'createdate',header:'登记时间',headerAlign:'center',dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
					 		 formEditorConfig:{	
					 					newLine:true,
					                     type:'text',
					                 readOnly:true,		
					             defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
					               labelWidth:100,								
					                   width:200 }},
			           {field: 'modificatorname',header:'修改人',headerAlign:'center' ,width:100,visible:false,allowSort:true,
				            formEditorConfig:{
					                 newLine:false,
					                    type:'text',
					                readOnly:true, 
					            fieldVisible:true,
					              labelWidth:100,								
					                   width:200}},
			           {field: 'modifydate',header:'修改时间',headerAlign:'center',visible:false,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
				            formEditorConfig:{	
				            	newLine:true,
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
<div id='content_table_id7'></div>