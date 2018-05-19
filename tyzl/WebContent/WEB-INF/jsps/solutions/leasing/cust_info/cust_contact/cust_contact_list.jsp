<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">		
	var custcontactData;
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 								
  				new ApTable({ 					
  					id:'table_id3',					
  					renderTo:'content_table_id3',
  					width:globalClientWidth,
  					height:450,					 					
  					editFormPopupWindowWidth:600,
  					editFormPopupWindowHeight:400,  										
  					iconCls:'icon-node',
  					hiddenQueryArea:true,
  					showToolbar: showTools,
  					afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
						if("add" == operFlag){
							if(custcontactData){
								miniForm.setData(custcontactData);
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
							            custcontactData = formData;						          
							        }
							    });
							}
						}
					}, 		            
  					remoteOper:true,
  					entityClassName:'com.tenwa.leasing.entity.cust.CustInfoContact',
  					pageSize:15,
  					showPager:true,
  					lazyLoad:false, 					
  					xmlFileName:'/eleasing/jsp/cust_info/cust_contact/cust_contact_list.xml', 					
  					params:{custid:custid},
  					tools:[ 'add', '-', 'edit', '-', 'remove','-',
                              {
                               html:'查看所有走访资料',plain:true,iconCls:'icon-search',
                               handler:function(miniTable, buttonText) {
  						          showanduploadfile("xx","all");
                        }}],
  					columns:[ 
  					    {type:'indexcolumn'},
  					    {type:'checkcolumn'}, 
  						{field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
	  						    formEditorConfig:{
  								        readOnly:true,
  								    fieldVisible:false}},  	
  						{field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
	  						    formEditorConfig:{
  								        readOnly:true,
  								    fieldVisible:false ,
  								           value:custid}},    						
	  					{field:'custname',header:'客户名称',headerAlign:'center',width:100,allowSort:true ,							
							    formEditorConfig:{								
								            type:'text',								
								      labelWidth:100,
	  							           width:200,
	  							        readonly:true}},							
						{field: 'contactdate',header:'走访时间',	headerAlign:'center', width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
							    formEditorConfig:{								
								            type:'date',  
								      labelWidth:100,
	  							           width:200,			
	  							        required:true,
								    defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
								      allowInput:"false"}},					
						{field: 'contactperson',header:'联系人',headerAlign:'center',width:100,allowSort:true,
							    formEditorConfig:{  	
								        required:true,
								            type:'text',
								         newLine:true,
  								      labelWidth:100,
								           width:200}},					
						{field: 'contactphone',header:'联系人电话',headerAlign:'center', width:100,allowSort:true,
							    formEditorConfig:{								
								            type:'text', 								
								      labelWidth:100,
  								           width:200}},
						{field: 'contactadd',header:'联系地点',headerAlign:'center',width:100,allowSort:true,
							    formEditorConfig:{
								            type:'text',
								         newLine:true,
  								      labelWidth:100,								
  								           width:200}},
						{field: 'raw_contacttype',header:'拜访类型',	headerAlign:'center',width:100,visible :true ,
							   formEditorConfig:{  								
								   fieldVisible: false,
  							  fillFromFieldName:'name', 						
								   fillProperty:'name'}},						
						{field: 'contacttype',header:'拜访类型',	headerAlign:'center',width:100,visible:false ,
							   formEditorConfig:{								
									       type:'combobox',
								   fieldVisible: true,
									 labelWidth:100,								
	  								      width:200,
	  								     params:{pid: 'contact_type',xmlFileName: '/combos/comboDict.xml'},
	  								  textField:'name',
	  								 valueField:'value'}},						
						{field: 'ccmemo',header:'记录内容',	headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{  								
								          type:'textarea', 
								       newLine:true,
								       colspan:4,
								    labelWidth:100,								
	  							         width:"100%"}},						
						{field: 'creatorname',header:'登记人',headerAlign:'center' ,width:100,allowSort:true,
							  formEditorConfig:{
								       newLine:true,
								          type:'text',
								         value:'<mini:user/>',
								      readOnly:true,
								    labelWidth:100,								
  								         width:200}},						
						{field: 'createdate',header:'登记时间',	headerAlign:'center', width:100,dateFormat:"yyyy-MM-dd",
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
  								         width:200 }},						
						{field: 'modifydate',header:'修改时间',	headerAlign:'center', visible:false,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
							  formEditorConfig:{  								
								          type:'text',
								      readOnly:true,
								  fieldVisible:true,
								    labelWidth:100,								
  								         width:200}},
  					     {field:'',header:'操作',
  					  		   formEditorConfig:{
                                   fieldVisible:false},
						               renderer:function(e){
							                   var id = e.record.id;
						                       return "<a style='text-decoration:underline;color:blue;' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
						               }}
  					]					
  				});
  			});  					
  			
  		});
  		function showanduploadfile(id,type){
  			var urlFlag = getRootPath()+"/leasing/cust_info/cust_contact/cust_contact_file_list.bi?id="+id+"&cust_id="+custid+"&type="+type;
  			mini.open({
  		        url: urlFlag,
  		        title: "走访资料", width: 800, height: 455,
  		        onload: function () {},
  		        ondestroy: function (action) {
  		        	if("savesuccess" == action){
  		        		window.location.reload();
  		        	}
  		        }
  		    });

  	  	}
	</script>
<div id='content_table_id3'></div>