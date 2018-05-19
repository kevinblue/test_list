<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<input  style="display:none;"	class="mini-textarea" id="id_json_csut_apply_list_str" name="json_csut_apply_list_str" value='${empty json_csut_apply_list_str ? "[]" : json_csut_apply_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	//alert(contractid);
	var showTools = true;
	//if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}; 
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "proj_invoice",  
			renderTo: "id_table_proj_invoice_detail",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : true,
			entityClassName : "com.tenwa.leasing.entity.contract.ContractEquip",
			showPager: false,
			multiSelect: true,
			title:'',
			showToolbar: showTools,
			virtualScroll:true,
		 	params:{id:contractid},
			tools: ['edit','-','exportExcel','-',
              {	html : '生成应收账款反转让通知书',
				plain : true,
				iconCls : 'icon-addfolder',
				handler : function(miniTable, buttonText) {
					var row = miniTable.getSelecteds();	
					var params = {};
					
				
					 if(row.length==0){
						
						 mini.alert('请先选择需要生成应收账款反转让选项！');
						 
					 }else if(row.length==1){ 
						var rows=miniTable.getSelected();
						var equipstatusname=rows.equipstatusname;
						if(equipstatusname=='已反转让'){
						params['label.custname'] = mini.get("cust_name").getValue();
						params['label.invoicecode'] = rows.invoicecode;
						params['label.factoringtype'] =mini.get("factoringtype").getText();
						params['label.contractnumber'] =mini.get("contract_number").getValue();
						var transfermattersname =rows.transfermattersname;				
						var b="未清偿应收账款";					
					 if(transfermattersname==b){
							params['label.transfermattersname']="1";
						}else{
							params['label.transfermattersname']="2";
						}
					 params['buyer.purchasername'] =rows.purchasername;
					 params['buyer.purchasername1'] =rows.purchasername;
					 params['buyer.purchasername2'] =rows.purchasername;
					//iyear,imonth,iday,tyear,tmonth,tday
					    var transferdate=rows.transferdate;
					    params['date.iyear']=transferdate.substring(0,4);//3.年
						params['date.imonth']=transferdate.substring(5,7);//4.月
						params['date.iday']=transferdate.substring(8);//5.日
					
					
					    params['date.tyear']=transferdate.substring(0,4);//3.年
						params['date.tmonth']=transferdate.substring(5,7);//4.月
						params['date.tday']=transferdate.substring(8);//5.日

						var fileTeplate=new fileTemplateConfig({						
							templateno : 'F-201611006',
							tableid:miniTable.config.id,
							modelname:miniTable.config.title,
							returntype:'listtonewpage',
							
							parames :params
						  });
						   fileTeplate.createFile(); 
						}else{
							mini.alert('请选择转让状态是已反转让的选项！');
					      
						}					
							
			}else{
				
				var name=row[0].purchasername;
				var transfermattersname = row[0].transfermattersname;
				var equipstatusname = '已反转让';
				for(var i=0;i<row.length;i++){
					 if(equipstatusname==row[i].equipstatusname){
						 equipstatusname=row[i].equipstatusname;
					 }else{
						 mini.alert("选择有已转让");
						 return;
					 }
					 if(name==row[i].purchasername&&transfermattersname==row[i].transfermattersname){
						 name=row[i].purchasername;
						 transfermattersname=row[i].transfermattersname;
						 
					 }else{
						 mini.alert("名称或事项有差异");
						 return;
					 }
				} 
				
				//1客户名称
				params['label.custname'] = mini.get("cust_name").getValue();
				 //2两个日期
				  var transferdate=row[0].transferdate;
					    params['date.iyear']=transferdate.substring(0,4);//3.年
						params['date.imonth']=transferdate.substring(5,7);//4.月
						params['date.iday']=transferdate.substring(8);//5.日
					
					    params['date.tyear']=transferdate.substring(0,4);//3.年
						params['date.tmonth']=transferdate.substring(5,7);//4.月
						params['date.tday']=transferdate.substring(8);//5.日
               //发票号                 
				        var invoicecodes="";
						for(c=0;c<row.length-1;c++){
							var invoicecode=row[c].invoicecode;
							invoicecodes=invoicecodes +invoicecode+",";
						}
						var last =row[length].invoicecode;
						var invoice= invoicecodes+last;
                  params['label.invoicecode'] = invoice;
			    //
				 params['label.factoringtype'] =mini.get("factoringtype").getText();
				 params['label.contractnumber'] =mini.get("contract_number").getValue();
				//反转让事项
				 var transfermattersname =row[0].transfermattersname;				
					var b="未清偿应收账款";					
				 if(transfermattersname==b){
						params['label.transfermattersname']="1";
					}else{
						params['label.transfermattersname']="2";
					}
				//3个买方								
				params['buyer.purchasername'] =row[0].purchasername;
				params['buyer.purchasername1'] =row[0].purchasername;
				params['buyer.purchasername2'] =row[0].purchasername;
				
				var fileTeplate=new fileTemplateConfig({						
					templateno : 'F-201611006',
					tableid:miniTable.config.id,
					modelname:miniTable.config.title,
					returntype:'listtonewpage',
					
					parames :params
				  });
				   fileTeplate.createFile(); 
				
			}
 
			}
              }
			        ],
			xmlFileName:'/eleasing/workflow/proj/proj_account_rece/proj_transfer_receivables_invoice.xml',
			columns: [   
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				
				
				{
					field:'contractid',
					header:'合同id',
					visible: false,
					formEditorConfig:{
						fieldVisible:false,
						value:contractid
					         }
				},
				{field:'purchasername',header:'买方名称',
					  formEditorConfig:{
						      required:true,
						    labelWidth:100,
						     maxLength:120,
						       colspan:3,
						       readOnly : true,
						         width:'100%'}},
				{field:'invoicecode', header:'发票号',
					  formEditorConfig:{
						  readOnly : true,
						       newLine: true,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'equipprice', header:'发票金额',align:'left',summary:true,
					  formEditorConfig:{
						  readOnly : true,
					        	  type:'text',
						         vtype:'float',
						         newLine:true}},
				{field: 'invoicedate', header:'发票日期',dateFormat:"yyyy-MM-dd",
						  formEditorConfig:{
							  readOnly : true,
					                  type:'date',
					                 vtype:'date',
					                format:'yyyy-MM-dd',
					            allowInput:false}},
			   {field: 'invoiceexpiredate', header:'发票到期日',dateFormat:"yyyy-MM-dd",
									  formEditorConfig:{
										  readOnly : true,
								                  type:'date',
								                 vtype:'date',
								                format:'yyyy-MM-dd',
								                newLine:true,
								            allowInput:false}},
			  {field: 'transferdate', header:'转让日期',dateFormat:"yyyy-MM-dd",
												  formEditorConfig:{
													  
											                  type:'date',
											                 vtype:'date',
											                 required:true,
											                format:'yyyy-MM-dd',
											                readOnly : true,
											            allowInput:true}},
											            
			  {field:'equipstatusname',header:'转让状态',visible: true, formEditorConfig : {fieldVisible : false}}	,			            
		 	  {
				   field: 'equipstatus', 
				   header:'转让状态',
				   visible: false,
		            formEditorConfig : {
						type : 'combobox',
						textField : 'name',
						required : true,
						valueField : 'value',
						fieldVisible : true,
						newLine:true,
				        data : [{value:'2',name:'已转让'},{value:'3',name:'已反转让'}]
					}	            
			 		            
			 	} ,        
			 	  
				  {field:'transfermattersname',header:'反转让事项',visible: true, formEditorConfig : {fieldVisible : false}}	,			            
						 	  {
								  field: 'transfermatters', 
								  header:'反转让事项',
								  visible: false,
						          formEditorConfig : {
										type : 'combobox',
										textField : 'name',
										required : true,
										valueField : 'value',
										fieldVisible : true,
								        data : [{value:'1',name:'未清偿应收账款'},{value:'2',name:'买方提出争议'}]
									}	            
							 		            
							 	} ,  
			  {field: 'hadtransferdate', header:'反转让日期',dateFormat:"yyyy-MM-dd",
									  formEditorConfig:{
										  
								                  type:'date',
								                 vtype:'date',
								                 required:true,
								                format:'yyyy-MM-dd',
								                readOnly :false,
								                newLine:true,
								            allowInput:true}
								            
							 	},
			 	{field:'transferreason',header:'转让原因',
					  formEditorConfig:{
						        type:'textarea',
						      required:true,
						    labelWidth:100,
						    newLine:true,
						     maxLength:120,
						       colspan:3,
						       readOnly : false,
						         width:'100%'}}
			]
		});
	});
});


</script>

<div id="id_table_proj_invoice_detail" style="height: 100%;">
</div>
