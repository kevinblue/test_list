<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false};
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
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(checkCardNoShareCompany()=="repeat") return;
				return true;},
			entityClassName:'com.tenwa.leasing.entity.cust.CustShareCompany',
			pageSize:15,
			showPager:true,
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_share_company/cust_share_company.xml',
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
					            fieldVisible:false, 
					                   value:custid}},
				  {field:'stockholdername',header:'关联企业名称',headerAlign:'center',width:100,allowSort:true,
					     	formEditorConfig:{
								    required:true,
					                    type:'text',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}},
				  {field:'orgcode',header:'身份证/组织机构代码',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                    type:'text',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}},
				  {field:'regcapital',header:'注册资本',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
					                 newLine:true,
					                    type:'text',
					            fieldVisible:true,
					                   width:200,
					              labelWidth:100}},
	             {field:'legalrepresentative',header:'法人代表',headerAlign:'center',width:100,allowSort:true,
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
					                 colspan:3,
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
			}]
		});
	});
});
function checkCardNoShareCompany(){
	var cardNo = getMiniEditFormField("table_id4.orgcode").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == cardNo)return "";
	if(!checkORGRight(cardNo)){return "repeat";}
	var idvalue = getMiniEditFormField("table_id4.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkCardNoShareCompany.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid,"cardNo":cardNo},
        async:false,
        success: function (text) {
        	var obj=mini.decode(text);
        	if(obj.message.length>1){
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