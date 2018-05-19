<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">

var tools = [];
<permission:action code="cust_share_company_add">
tools.push('add');
</permission:action>
<permission:action code="cust_share_company_edit">
tools.length>0?tools.push('-','edit'):tools.push('edit');
</permission:action>
<permission:action code="cust_share_company_remove">
tools.length>0?tools.push('-',"remove"):tool.push('remove');
</permission:action>  

jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id4',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'content_table_id4',
			hiddenQueryArea:true,
			frozenStartColumn:1,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			showToolbar: showTools,
			remoteOper:true,
			//validateForm:judgeCustCompany,
		 	 validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(checkCardNoShareCompany()=="repeat") return;
				return true;}, 
			entityClassName:'com.tenwa.leasing.entity.cust.CustShareCompany',
			pageSize:15,
			showPager:true,
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_share_company/cust_share_company.xml',
			params:{custid:custid},
			//tools:[ 'add', '-', 'edit', '-', 'remove'],
			tools:tools,	
		 	beforeShowWindowCallBack:function(miniTable, miniForm, operFlag){
				if("edit"==operFlag){
					getMiniEditFormField("table_id4.stockholdernameid").setEnabled(false);
					//getMiniEditFormField("table_id4.enterprisetypename").setText(mini.getbyName("enterprisetype").getValue());
					//mini.getbyName("enterprisetypename").setValue(mini.getbyName("enterprisetype").getValue());
				//	mini.alert(mini.getbyName("enterprisetype").getValue());
				//	mini.getbyName("enterprisetypename").setText(mini.getbyName("enterprisetypename").getValue());
				
				}
				return true;
			}, 
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
				 
	              {field:'stockholdernameid',header:'选择公司名称',headerAlign:'center',width:100,visible:false,
		              formEditorConfig:{
			                      type:'queryinput',
			                      width:'100%',
			                      colspan:4,
			              fieldVisible:true,				              
			                  required:false,
			              	textField : 'name',
					     	valueField : 'value',
						onSelect : function(
								$me,queryInput,rowData) {
							
							getMiniEditFormField("table_id4.stockholdername").setValue(rowData.name);
							getMiniEditFormField("table_id4.orgcode").setValue(rowData.orgcode);
							getMiniEditFormField("table_id4.regcapital").setValue(rowData.regcapital);
							//getMiniEditFormField("table_id4.enterprisetypename").setValue(rowData.rawValue_enterprisetype);
							//mini.getbyName("enterprisetypename").setValue(rowData.rawValue_enterprisetype);
							//getMiniEditFormField("table_id4.enterprisetypename").setValue(rowData.value);
							getMiniEditFormField("table_id4.enterprisetype").setValue(rowData.value);
							getMiniEditFormField("table_id4.enterprisetype").setText(rowData.rawValue_enterprisetype);
							
							//getMiniEditFormField("table_id4.enterprisetype").setValue(rowData.value);
							//mini.alert(getMiniEditFormField("table_id4.enterprisetype").getValue());
							
							getMiniEditFormField("table_id4.legalrepresentative").setValue(rowData.legalrepresentative);
							getMiniEditFormField("table_id4.founddate").setValue(rowData.founddate);
							getMiniEditFormField("table_id4.domicile").setValue(rowData.domicile);
							getMiniEditFormField("table_id4.addr").setValue(rowData.addr);
							getMiniEditFormField("table_id4.bizscopeprimary").setValue(rowData.bizscopeprimary);
							getMiniEditFormField("table_id4.relation_shipname").setValue("");//重置关联关系内容
											
						},
						params : {
						xmlFileName : '/eleasing/jsp/cust_info/cust_share_company/cust_share_company_query.xml'
						}
			                  
		              }}, 
		              {field:'stockholdername',header:'公司名称',headerAlign:'center',width:100,allowSort:true,
					     	formEditorConfig:{
					                    type:'text',
					                    newLine:true,
					            fieldVisible:true,
					            fillFromFieldName:'stockholdernameid',
					                   width:200,
					              labelWidth:100}},
				   {field:'orgcode',header:'注册号/统一社会信用代码',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                    type:'text',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}}, 
				  {field:'regcapital',header:'注册资金(万元)',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                 newLine:true,
					                    type:'text',
					                   // vtype:'float',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}},
				 {
	            	field: 'rawValue_enterprisetype',
	            	header: '企业类型', 
	            	visible:true,
	                formEditorConfig:{
	       				fillFromFieldName:'enterprisetype',
	            		fillProperty:'name',
	            		fieldVisible: false
	            	}
	              },
				  {
	            	  field:'enterprisetype',
	            	  header:'企业类型',
	            	  headerAlign:'center',
	            	  visible:false,
	            	  width:100,
	            	  formEditorConfig:{
				            type:'combobox',
				            textField: 'name',
				            valueField: 'value',
				            width:200,
				            fieldVisible: true,
				            showNullItem:"true", 
				            nullItemText:"",
				            emptyText:"",
				            params: {pid: 'cust_scale',xmlFileName: '/combos/comboDict.xml'},
				       }
	              },
	             /*  {field: 'rawValue_relationship', header: '关系', 
		              formEditorConfig:{
			              fieldVisible: false,
			         fillFromFieldName:'relationship',
			              fillProperty:'name'}},
			 {field:'relationship',header:'关系',visible:false,headerAlign:'center',width:100,allowSort:true,
                      formEditorConfig:{
			                     width:200,
			                   newLine:true,
			              fieldVisible: true,
			              showNullItem:"true", 
			              nullItemText:"",
			                 emptyText:"",
			                      type:'combobox',
			                    params: {pid: 'relationship',xmlFileName: '/combos/comboDict.xml'},
			                 textField:'name',
			                valueField:'value'}}, */
			   {field:'domicile',header:'住所',headerAlign:'center',width:100,allowSort:true,
							            formEditorConfig:{
								                 newLine:true,
								                    type:'text',
								            fieldVisible:true,
								                   width:200,
								              labelWidth:100}},					              
	             {field:'legalrepresentative',header:'法定代表人或负责人',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                    type:'text',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}},
				  {field: 'relation_shipname', header: '关联关系', 
		                    formEditorConfig:{
					            fieldVisible: false,
					       fillFromFieldName:'relationship',
					            fillProperty:'name'}},
				  {field:'relationship',header:'关联关系',visible:false,headerAlign:'center',width:100,allowSort:true,			
				            formEditorConfig:{
					                 newLine:true,
					              labelWidth:100,
					                   width:200,
					            fieldVisible: true,
					            showNullItem:"true", 
					            nullItemText:"",
					               emptyText:"",					
					                    type:'combobox',
					                  params:{pid: 'relation_ship',xmlFileName: '/combos/comboDict.xml'},
					               textField:'name',
					              valueField:'value'}},
			 {field:'founddate',header:'成立日期',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
							            formEditorConfig:{
								                    type:'date',
								            fieldVisible:true,
								                   width:200,
								              labelWidth:100}}, 	              
				   {field:'addr',header:'地址',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                 newLine:true,
				 	                 colspan: 3,
					                    type:'textarea',
					                 newLine:true,
					            fieldVisible:true,
					              labelWidth:100,
					                   width:"100%"}}, 
				    {field:'bizscopeprimary',header:'主营业务',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                 newLine:true,
					                 colspan: 3,
					                    type:'textarea',
					                 newLine:true,
					            fieldVisible:true,
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
			}]
		});
	});
});
function checkCardNoShareCompany(){
	var cardNo = getMiniEditFormField("table_id4.orgcode").getValue();//mini.getbyName("mainpersonflag").getValue();
	//mini.alert(cardNo);
	if("" == cardNo)return "";
	//if(!checkORGRight(cardNo)){return "repeat";}
	var idvalue = getMiniEditFormField("table_id4.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkCardNoShareCompany.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid,"cardNo":cardNo},
        async:false,
        success: function (text) {
        	if(text.length>1){
        		mini.alert("组织机构代码不能重复！");
        		str="repeat"
        	}else{
        		str = "";
        	}
        	
        }
    });
	return str;
}
//组织机构代码校验
function checkORGRight(code){//校验组织机构代码
	   if(code.length>0){
       var temp=code.substring(0,1);
       if(temp=="X"){return true;
        }
	   }
	   var financecode=code;
	   var fir_value, sec_value;
	   var w_i = new Array(8);
	   var c_i = new Array(8);
	   var j, s = 0;
	   var c, i;

	   var code = financecode;

	   if (code.length<1) {
	     //alert("请输入组织机构代码!");
	       return false;
	   }

	   if (code == "00000000-0") {
		   mini.alert("组织机构代码错误!");
	       return false;
	   }

	   re = /[A-Z0-9]{8}-[A-Z0-9]/;    
	   r = code.match(re);   
	   if (r == null) {
		   mini.alert("组织机构代码错误!");
	     return false;
	   }        

		   w_i[0] = 3;
		   w_i[1] = 7;
		   w_i[2] = 9;
		   w_i[3] = 10;
		   w_i[4] = 5;
		   w_i[5] = 8;
		   w_i[6] = 4;
		   w_i[7] = 2;

		   if (financecode.charAt(8) != '-') {
			   mini.alert("组织机构代码错误!");
			   return false;
		   }

		   for (i = 0; i < 10; i++) {
			   c = financecode.charAt(i);
			   if ((c <= 'z') && (c >= 'a')) 
			  {
				   mini.alert("组织机构代码错误!");
				   return false;
			   }
		   }


		   fir_value = financecode.charCodeAt(0);
		   sec_value = financecode.charCodeAt(1);

		   if (fir_value >= 'A'.charCodeAt(0) && fir_value <= 'Z'.charCodeAt(0)) {
			   c_i[0] = fir_value + 32 - 87;
		   } else {
				if (fir_value >= '0'.charCodeAt(0) && fir_value <= '9'.charCodeAt(0)) {
				c_i[0] = fir_value - '0'.charCodeAt(0);
				} else {
					mini.alert("组织机构代码错误!");
				return false;
				}
		   }

		   s = s + w_i[0] * c_i[0];

		   if (sec_value >= 'A'.charCodeAt(0) && sec_value <= 'Z'.charCodeAt(0)) {
			   c_i[1] = sec_value + 32 - 87;
		   } else if (sec_value >= '0'.charCodeAt(0) && sec_value <= '9'.charCodeAt(0)) {
			   c_i[1] = sec_value - '0'.charCodeAt(0);
		   } else {
			   mini.alert("组织机构代码错误!");
			   return false;
		   }

		   s = s + w_i[1] * c_i[1];
		   for (j = 2; j < 8; j++) {
			   if (financecode.charAt(j) < '0' || financecode.charAt(j) > '9') {
			   alert("组织机构代码错误!");
				   return false;
			   }
			   c_i[j] = financecode.charCodeAt(j) - '0'.charCodeAt(0);
			   s = s + w_i[j] * c_i[j];
		   }

		   c = 11 - (s % 11);

		   if (!((financecode.charAt(9) == 'X' && c == 10) ||
				 (c == 11 && financecode.charAt(9) == '0') || (c == financecode.charCodeAt(9) - '0'.charCodeAt(0)))) {

				  mini.alert("组织机构代码错误!");
					return false;
		   }
	   return true;
}


</script>
<div id='content_table_id4'></div>