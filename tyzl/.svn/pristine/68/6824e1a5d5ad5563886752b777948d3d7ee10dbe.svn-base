<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var custaccountData;
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 
  				new ApTable({
  				    id:'table_id2',  				  	
  				  	renderTo:'content_table_id2',  				    
					width:globalClientWidth,
  					height:globalClientHeight,   
  					editFormPopupWindowWidth:700,
  					editFormPopupWindowHeight:350,					
					iconCls:'icon-node',
					hiddenQueryArea:true,
					showToolbar: showTools,
					validateForm:function(miniTable, miniForm, editFormItemOperFlag){
						if(checkIsMain()=="repeat") return;
						if(checkCustBankRepeat()=="repeat") return;
						var accnumber = mini.getbyName("accnumber");
						var accnumbervalue = accnumber.getValue().replace(/[ ]/g,"");
						if (isNaN(accnumbervalue)) { 
							mini.alert("银行账号请输入数字！");
							return false;
						}
						accnumber.setValue(accnumbervalue);
						return true;
					},
					afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
						if("add" == operFlag){
							if(custaccountData){
								miniForm.setData(custaccountData);
							}else{
								$.ajax({
							        url: urlPrefix+"/eleasing/jsp/cust_info/cust_account/get_custinfo_byid.xml",
							        type: "post",
							        cache: false, 
							        async:false,
							        data :{"custid":custid},
							        success: function (text) {							            
							            var result = mini.decode(text);
							            var data = result.datas[0];
							            var formData = miniForm.getData();
							            $.extend(formData,mini.decode(data));
							            miniForm.setData(formData);
							            custaccountData = formData;
							        }
							    });
							}
						}
						if("edit" == operFlag){
							changeaccnumber();
						}
					},					
					remoteOper:true,
					entityClassName:'com.tenwa.leasing.entity.cust.CustInfoAccount',
					pageSize:15,
					showPager:true,
					lazyLoad:false,					
					xmlFileName:'/eleasing/jsp/cust_info/cust_account/cust_account_info.xml',					
					params:{custid:custid},
					tools:[ 'add', '-', 'edit', '-', 'remove'],
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
								         fieldVisible:false }},
	  					    {field: 'custname',header:'客户名',headerAlign:'center',width:100,allowSort:true,							
							         formEditorConfig:{
								              colspan: 3,
								                 type:'text', 								
								         fieldVisible:true,
								                width:"100%",
								           labelWidth:120 ,
								             readonly:true }},	
						   {field: 'account',header:'帐户名称',headerAlign:'center', width:100,allowSort:true,							
							        formEditorConfig:{	
								            required:true,
								             newLine:true,
								                type:'text',  							
								          labelWidth:100,
	  							               width:200}},				
						   {field: 'ismain',header:'是否主账户',headerAlign:'center',width:100,allowSort:true,
							        formEditorConfig:{									
								                type:'combobox',							
								          labelWidth:100,
								               width:200,							
								          valueField:'text',
								           textField:'text',
								                data:[{text:'是'},{text:'否'}]}},					
						   {field: 'bankname',header:'开户银行',	headerAlign:'center', width:100,allowSort:true,
									formEditorConfig:{		
								             newLine:true,
								                type:'text',	
								                required:true,
								          labelWidth:100,
								               width:200}},				
						   {field: 'accnumber',header:'银行账号',headerAlign:'center', width:100,allowSort:true,
							                renderer:function(e){
								                 var accnumbervalue = e.record.accnumber;
								                 var formatvalue = accnumbervalue.replace(/(\d{4})(?=\d)/g,"$1 ");
								                 return formatvalue;},
							         formEditorConfig:{
								                 type:'text',
								             required:true,
							 	           labelWidth:100,								
								                width:200,
								              onkeyup:"changeaccnumber"}},
						   {field: 'accinfo',header:'帐户使用情况',headerAlign:'center',width:100,allowSort:true,
							         formEditorConfig:{ 	
								              colspan:3,
								              newLine:true,
								                 type:'textarea',  
								           labelWidth:100,								
			                                    width:"100%"}},										
						   {field: 'creatorname',header:'登记人',headerAlign:'center' ,width:100,allowSort:true,							
							         formEditorConfig:{
								              newLine:true,
								                 type:'text',
								             readOnly:true,
								                value:'<mini:user/>',
								           labelWidth:100,								
								                width:200}},						
						   {field: 'createdate',header:'登记时间',headerAlign:'center',dateFormat:"yyyy-MM-dd ",width:100,allowSort:true, 							
							         formEditorConfig:{								
								                 type:'text',
								             readOnly:true,		
								         defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
								           labelWidth:100,								
								                width:200}},
						   {field: 'modificatorname',header:'修改人',headerAlign:'center' ,width:100,visible:false,allowSort:true,
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
								                width:200 								
	  						} 				   		
						}					
					]
  				
  			});
			});
			
		});
  	function changeaccnumber(e){
  		var accnumber = mini.getbyName("accnumber");
  		var formatvalue = accnumber.getInputText().replace(/[^0-9.]/gi,"");
  		var accnumbervalue = formatvalue.replace(/(\d{4})(?=\d)/g,"$1 ");
  		accnumber.setValue(accnumbervalue);
  	}		
  	function checkIsMain(){
  		var isMainvalue = getMiniEditFormField("table_id2.ismain").getValue();//mini.getbyName("ismain").getValue();
  		if("" == isMainvalue || "否" == isMainvalue)return "";
  		var idvalue = getMiniEditFormField("table_id2.id").getValue();//mini.getbyName("id").getValue();
  		var str = "";
  		$.ajax({
  	        url: getRootPath()+"/acl/checkIsMain.acl?Random=" + Math.random(),
  	        type: "post",
  	        cache: false, 
  	        data :{"id":idvalue,"custId":custid},
  	        async:false,
  	        success: function (text) {
  	        	var obj=mini.decode(text);
  	        	if("repeat" == obj.message){
  	        		mini.alert("主账户只能有一个！");
  	        	}
  	        	str = obj.message;
  	        }
  	    });
  		return str;
  	}
  	function checkCustBankRepeat(){
  		var accNumber = getMiniEditFormField("table_id2.accnumber").getValue();//mini.getbyName("mainpersonflag").getValue();
  		if("" == accNumber)return "";
  		var idvalue = getMiniEditFormField("table_id2.id").getValue();
  		accNumber=accNumber.replace(/[ ]/g,"")
  		var str = "";
  		$.ajax({
  	        url: getRootPath()+"/acl/checkCustAccountRepeat.acl",
  	        type: "post",
  	        cache: false, 
  	        data :{"id":idvalue,"custId":custid,"accNumber":accNumber},
  	        async:false,
  	        success: function (text) {
  	        	var obj=mini.decode(text);
  	        	str ="repeat";
  	        	if(obj.message.length>1){
  	        		mini.alert(obj.message);
  	        	}else{
  	        		str ="";
  	        	}
  	        	
  	        }
  	    });
  		return str;
  	}
	</script>
<div id='content_table_id2'></div>