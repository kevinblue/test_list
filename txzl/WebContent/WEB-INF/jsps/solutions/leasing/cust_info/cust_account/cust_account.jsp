<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">

  

jQuery(function() {
	var custaccountData;
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;}
	if('${param.accNumView}' == 'false'){showTools = true;}
	 var tools = [];
		<permission:action code="cust_bankAccount_add">
		tools.push('add');
		</permission:action>
		<permission:action code="cust_bankAccount_edit">
		tools.length>0?tools.push('-','edit'):tools.push('edit');
		</permission:action>
		<permission:action code="cust_bankAccount_remove">
		tools.length>0?tools.push('-',"remove"):tool.push('remove');
		</permission:action>
	var shortname=[{id:'CCB',text:'CCB'},{id:'简称2',text:'简称2'}];//银行简称
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
						/* if(checkCustBankRepeat()=="repeat") return; */
						var accnumber = mini.getbyName("accnumber");
						var accnumbervalue = accnumber.getValue().replace(/[ ]/g,"");
						if (isNaN(accnumbervalue)) { 
							mini.alert("银行账号请输入数字！");
							return false;
						}
						accnumber.setValue(accnumbervalue);
						
						var date=miniTable.getEditFormData();
					 	var number=date.bankno;
						if(getLength(number)>10){
							mini.alert("银行行号长度不能超过10个字符！");
							return false;
						} 
						var unionbankno=date.unionbankno;
						if(getLength(unionbankno)>30){
							mini.alert("银行联行号长度不能超过30个字符！");
							return false;
						} 
						
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
								             required:true,
								                type:'text',							
								          labelWidth:100,
								               width:200}},		
						 //银行简称
				 	{field: 'depositbanktypename',header:'银行简称',	headerAlign:'center', visible:true,
									formEditorConfig:{										
										type : 'text',	/* 		
										fillFromFieldName : 'depositbanktype',										
							              fillProperty:'name', */
										fieldVisible : false
										
		            			 }
						 }, 
		                 {field: 'depositbanktype',header:'银行简称',	headerAlign:'center', visible:false,
									formEditorConfig:{		
										showNullItem:true,
										type : 'combobox',
										textField : 'name',
										valueField : 'value',
										required : true,
										fieldVisible : true,
										params : {
											pid : 'bank_short_name',
											xmlFileName : '/combos/comboDict.xml'
										}
		            			 }
						 },
						   {field: 'bankno',header:'行号',headerAlign:'center', width:100,allowSort:true,
									formEditorConfig:{	
										 newLine:true,
										 required : true,
								                type:'text',	
								          labelWidth:100,
								               width:200}},			
						 //收款方联行号
		                  {field: 'unionbankno',header:'联行号',	headerAlign:'center', width:100,allowSort:true,
									formEditorConfig:{
											  newLine:false,
											  required : true,
		               							 type:'text',							
		       							   labelWidth:100,
		               							width:200}},	
						   {field: 'accnumber',header:'银行账号',headerAlign:'center', width:100,allowSort:true,
							                renderer:function(e){
								                 var accnumbervalue = e.record.accnumber;
								                 var formatvalue = accnumbervalue.replace(/(\d{4})(?=\d)/g,"$1 ");
								                 return formatvalue;},
							         formEditorConfig:{
							        	 colspan: 3,
							        	 newLine:true,
							        	 required : true,
								                 vtype:"float",
							 	           labelWidth:100,								
								                width:520,
								              onkeyup:"changeaccnumber"}},
						   //开户行省
			        /*       {field: 'depositprovince',header:'开户行省',	headerAlign:'center', width:100,allowSort:true,
									formEditorConfig:{		
	          						   		newLine:true,
	              							   type:'text',							
	        					    	 labelWidth:100,
	            						      width:200}},	
	           				//开户行市
   						   {field: 'depositcity',header:'开户行市',	headerAlign:'center', width:100,allowSort:true,
									formEditorConfig:{		
          						  			 newLine:false,
              								    type:'text',							
        					    		  labelWidth:100,
            								   width:200}}, */
/* 				 	{
								field : 'depositprovincename',
								header : '所处省份',
								queryConfig : {
									type : 'combobox',
									newLine : true,
									textField : 'name',
									valueField : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									}
								},
								formEditorConfig : {
									labelWidth : 100,					
									entityClassName : 'com.tenwa.business.entity.base.District',
									fillFromFieldName : 'depositprovince',
									fillProperty : 'name',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'depositprovince',
								header : '所处省份',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									entityClassName : 'com.tenwa.business.entity.base.District',
									newLine : true,
									textField : 'name',
									valueField : 'value',
									showNullItem : "true",
									required : true,
									fieldVisible : true,
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									},
									cascade : {
										childrenFieldNames : [ 'depositcity' ],
										clearFieldNames : [ 'depositcity' ]
									}
								}
							}, 
						{
								field : 'depositcityname',
								header : '地级市',
								formEditorConfig : {									
									fillFromFieldName : 'depositcity',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.business.entity.base.District',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'depositcity',
								header : '地级市',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine : false,
									entityClassName : 'com.tenwa.business.entity.base.District',
									showNullItem : "true",
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_search/city.xml'
									},
									cascade : {
										parentFieldNames : [ 'depositprovince' ]
									}
								}
							},  
							{
								field : 'provincename',
								header : '所处省份',
								queryConfig : {
									type : 'combobox',
									showNullItem : "true",
									textField : 'name',
									newLine : false,
									valueField : 'value',
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									}
								},
								formEditorConfig : {
									labelWidth : 100,
									entityClassName : 'com.tenwa.business.entity.base.District',
									fillFromFieldName : 'province',
									fillProperty : 'name',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'province',
								header : '所处省份',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									entityClassName : 'com.tenwa.business.entity.base.District',
									newLine : true,
									showNullItem : "true",
									textField : 'name',
									valueField : 'value',
									required : true,
									fieldVisible : true,
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									},
									cascade : {
										childrenFieldNames : [ 'city' ],
										clearFieldNames : [ 'city' ]
									}
								}
								
							},
							{
								field : 'cityname',
								header : '地级市',
								formEditorConfig : {
									fillFromFieldName : 'city',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.business.entity.base.District',
									width : 200,
									fieldVisible : false
								}
							}, */
							{
								field : 'provincename',
								header : '所处省份',
								queryConfig : {
									type : 'combobox',
									showNullItem : "true",
									textField : 'name',
									newLine : false,
									valueField : 'value',
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									}
								},
								formEditorConfig : {
									labelWidth : 100,
									entityClassName : 'com.tenwa.business.entity.base.District',
									fillFromFieldName : 'province',
									fillProperty : 'name',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'province',
								header : '所处省份',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									entityClassName : 'com.tenwa.business.entity.base.District',
									newLine : true,
									showNullItem : "true",
									textField : 'name',
									valueField : 'value',
									required : true,
									fieldVisible : true,
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									},
									cascade : {
										childrenFieldNames : [ 'city' ],
										clearFieldNames : [ 'city' ]
									}
								}
								
							},
							{
								field : 'cityname',
								header : '地级市',
								formEditorConfig : {
									fillFromFieldName : 'city',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.business.entity.base.District',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'city',
								header : '地级市',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine : false,
									showNullItem : "true",
									entityClassName : 'com.tenwa.business.entity.base.District',
									textField : 'name',
									valueField : 'value',
									required : true,
									fieldVisible : true,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_search/city.xml'
									},
									cascade : {
										parentFieldNames : [ 'province' ]
									}
								}
								
							},		 
				{field: 'accounttypenewname', header:'账户类型', visible: true,
	  			           									   formEditorConfig:{
	  			           											fieldVisible: false,
	  			           											fillFromFieldName : 'accounttypenew',
	  			           										    newLine:true,
	  			           											fillProperty : 'name'								 
	  			           					}},
	  	{field : 'accounttypenew',header : '账户类型',visible: false,
	  			           									  formEditorConfig : {
	  			           										  
	  			           											type : 'combobox',
	  			           											textField: 'name',
	  			           											required: true,
	  			           										    valueField: 'value',
	  			           											fieldVisible: true,
	  			           											params:{pid: 'account_type_new',xmlFileName: '/combos/comboDict.xml'}			                 
	  			           					}},     
	  											           
	  											           
			            
	  						{field: 'accounttype',header:'是否监管',	headerAlign:'center', width:100,allowSort:true,
								formEditorConfig:{	
									colspan:3,
									 type:'combobox',				 
									 required:true,
									 newLine:false,
							          labelWidth:100,
							               width:200,							
							          valueField:'text',
							           textField:'text',
							           data:[{text:'是'},{text:'否'}]}},		
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
								                value:'${sessionScope.loginUser.realname}',
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
  	        	if("repeat" == text){
  	        		mini.alert("主账户只能有一个！");
  	        	}
  	        	str = text;
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
  	        	str ="repeat";
  	        	if(text.length>1){
  	        		mini.alert(text);
  	        	}else{
  	        		str ="";
  	        	}
  	        	
  	        }
  	    });
  		return str;
  	}
  	function getLength(str)   
  	{  
  	    var realLength = 0;  
  	    for (var i = 0; i < str.length; i++)   
  	    {  
  	        charCode = str.charCodeAt(i);  
  	        if (charCode >= 0 && charCode <= 128)   
  	        realLength += 1;  
  	        else   
  	        realLength += 3;  
  	    }  
  	    return realLength;  
  	}
	</script>
<div id='content_table_id2'></div>