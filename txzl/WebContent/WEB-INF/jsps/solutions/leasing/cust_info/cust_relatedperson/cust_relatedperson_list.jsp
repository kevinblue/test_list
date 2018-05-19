<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
 <%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
 <%@include file="/base/mini.jsp"%>
<%@include file="../cust_comm/cust_comm.jsp"%>


<script type="text/javascript">

	var tool = [];
	/* tools.push('add'); */
	 <permission:action code="cust_relatedPerson_add">
  	tool.push('add');
  	</permission:action>
  	<permission:action code="cust_relatedPerson_edit">
  	tool.length>0?tool.push('-','edit'):tool.push('edit');
  	</permission:action>
  	<permission:action code="cust_relatedPerson_remove">
  	tool.length>0?tool.push('-',"remove"):tool.push('remove');
  	//tools.push('remove');
  	</permission:action>
  			

jQuery(function() {

	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false;}
	if('${param.isShow}' == 'true'){showTools = true;}
		tenwa.createTable({
			id:'table_id1',
			width:globalClientWidth,
			height:420,
			iconCls:'icon-node',
			renderTo:'content_table_id1',
			frozenStartColumn:0,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			remoteOper:true,
			editRemoteOperUrl:getRootPath()+"/acl/updateCustRelatedPersonHis.acl",
			entityClassName:'com.tenwa.leasing.entity.cust.CustRelatedPerson',			
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			showToolbar: showTools,
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(checkIsPersonMain()=="repeat") return;
				if(checkCardNoPersonMain()=="repeat") return;
				return true;
			},
			updateOperCallBack:function(miniTable,rowData){
				mini.get("table_id1").deselectAll(false);
			},
			xmlFileName:'/eleasing/jsp/cust_info/cust_relatedperson/cust_relatedperson_list.xml',
			<%-- operValidate:function(miniTable,rowData,oper){
				var deptobj = "<%=session.getAttribute("userDeptListStr")%>";
				if("edit"==oper||"remove"==oper){
					var checkRowDatas = miniTable.getSelecteds();
					if(checkRowDatas && checkRowDatas.length > 0){
						var rowData = checkRowDatas[0];
						//4028805e5261d9e001526206b4630066资产管理部
						if(rowData.onhirenum > '0' && deptobj.indexOf('4028805e5261d9e001526206b4630066')<0){
							mini.alert("该承租人有在起租的合同，请联系资产管理部进行修改");
							return false;
						}
					}
				}
				return true;
			}, --%>
			params:{custid :custid},
			//tools:[ 'add', '-', 'edit', '-', 'remove'],
			tools:tool,
			 beforeShowWindowCallBack:function(miniTable, miniForm, operFlag){
					if("edit"==operFlag){
						getMiniEditFormField("table_id1.personnameid").setEnabled(false);
						//mini.getbyName("personnameid").setText(mini.getbyName("personname").getValue());
						//mini.getbyName("personnameid").setValue(mini.getbyName("personname").getValue());
						
					}
					return true;
				},
			columns:[
					 {type:'indexcolumn'},
					 {type:'checkcolumn'}, 
					 {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
                              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false }},
					 {field:'onhirenum',header:'起租合同数',headerAlign:'center',width:100,allowSort:true,visible:false,
                              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false }},
					 {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false, 
					                     value:custid}},
	                {field:'personnameid',header:'选择重要个人',headerAlign:'center',width:100,visible:false,
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
								getMiniEditFormField("table_id1.personname").setValue(rowData.name);
								getMiniEditFormField("table_id1.sex").setValue(rowData.sex);
								getMiniEditFormField("table_id1.idcardno").setValue(rowData.idcardno);
								getMiniEditFormField("table_id1.birthdate").setValue(rowData.birthdate);
								getMiniEditFormField("table_id1.mailadd").setValue(rowData.mailadd);
								getMiniEditFormField("table_id1.postcode").setValue(rowData.postcode);
								getMiniEditFormField("table_id1.mobilenumber").setValue(rowData.mobilenumber);
								getMiniEditFormField("table_id1.phone").setValue(rowData.phone);
								getMiniEditFormField("table_id1.email").setValue(rowData.email);
								getMiniEditFormField("table_id1.unitposition").setValue(rowData.unitposition);
								getMiniEditFormField("table_id1.domicileplace").setValue(rowData.domicileplace);

								
								},
							params : {
							xmlFileName : '/eleasing/jsp/cust_info/cust_relatedperson/custperson_query.xml'
							}
				                  
			              }},
						{field:'personname',header:'重要个人名称',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                      type:'text',
					         fillFromFieldName:'personnameid',
					         newLine:true,
					              fieldVisible:true,
					                  required:true}},
					 {field:'sex',header:'性别',visible:true,headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'radiobuttonlist',
					               repeatItems: "0",
					              repeatLayout: "table",
					           repeatDirection: "vertical",
					                valueField:'text',
					                 textField:'text',
					                      data:[{text:'男'},{text:'女'}]}},
					 {field:'idcardno',header:'身份证号码',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					              fieldVisible:true,
					              onvalidation:"onIDCardsValidation",
					                     width:200,
					                labelWidth:100}},
					 {field:'birthdate',header:'出生年月',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
				              formEditorConfig:{
					                      type:'date',
					                    format:'yyyy-MM-dd',
					                     width:200}},
					                     
			         {field: 'rawValue_unitposition', header: '职务', visible:true,
				              formEditorConfig:{
					              fieldVisible: false,
					         fillFromFieldName:'unitposition',
					              fillProperty:'name'}},
		              {field:'unitposition',header:'职务',visible:false,headerAlign:'center',width:100,allowSort:true,
			           	  formEditorConfig:{
			           		fieldVisible: false,
				                     width:200,
				                   newLine:true,
				              fieldVisible: true,
				              showNullItem:"true", 
				              nullItemText:"",
				                 emptyText:"",
				                      type:'combobox',
				                    params: {pid: 'cust_jobposition',xmlFileName: '/combos/comboDict.xml'},
				                 textField: 'name',
				                valueField: 'value'}},
					 {field:'mainpersonflag',header:'是否主联系人',visible:true,headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					              showNullItem:"true", 
					              nullItemText:"",
					                 emptyText:"",
					                      type:'combobox',
					                valueField:'text',
					                 textField:'text',
					                      data:[{text:'是'},{text:'否'}]}},
                     /*  {field:'sendperson',header:'是否寄件人',visible:true,headerAlign:'center',width:100,allowSort:true,
			              formEditorConfig:{
			            	  newLine:true,
	            	  		  required:true,
				                     width:200,
				              showNullItem:"true", 
				              nullItemText:"",
				                 emptyText:"",
				                      type:'combobox',
				                valueField:'text',
				                 textField:'text',
				                      data:[{text:'是'},{text:'否'}]}}, */
					  {field:'mailadd',header:'邮寄地址',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                     width:"100%",
					                   colspan:3,
					                      type:'text',
					                   newLine:true,
					              fieldVisible:true}},
					  {field:'postcode',header:'邮编',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                     width:200,
					                      type:'text',
					              fieldVisible:true,
					              onvalidation:"onPostcodeValidation"}},
					  {field:'mobilenumber',header:'手机',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'text',
					              fieldVisible:true,
					              onvalidation:"onMoblieValidation"}},
					  {field:'phone',header:'电话',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                   newLine:true,
					                      type:'text',
					              fieldVisible:true}},
					 {field:'email',header:'email',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'text',
					              fieldVisible:true,
					                     vtype:"email"}},					 
					              
	              {field: 'rawValue_relationship', header: '关系', 
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
				                valueField:'value'}},
					
					 {field:'servicelife',header:'任职年限(年)',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:200,
					                      type:'text',
					              fieldVisible:true}},
					 {field:'domicileplace',header:'户口所在地',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   colspan: 3,
				 	                   newLine:true,
					                     width:"100%",
				 	                      type:'text',
				 	              fieldVisible:true}},
				 	 {field:'homeadd',header:'常住地址',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                   colspan: 3,
					                   newLine:true,
					                     width:"100%",
					                      type:'text',
					              fieldVisible:true}},
					 {field:'cpmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
					                     width:"100%",
					                   newLine:true,
					                   colspan: 3,
					                      type:'textarea',
					              fieldVisible:true}},										
			         {field: 'creatorname',header:'登记人',	headerAlign:'center',width:100,allowSort:true,				
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					                  readOnly:true,
					                     value:'${sessionScope.loginUser.realname}',
					                labelWidth:100,								
					                     width:200}},						
			         {field: 'createdate',header:'登记时间',	headerAlign:'center', dateFormat:"yyyy-MM-dd ",width:100,allowSort:true, 							
				              formEditorConfig:{								
					                      type:'text',
					                  readOnly:true,		
					              defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
					                labelWidth:100,								
					                     width:200 }},
			        {field: 'modificatorname',header:'修改人',	headerAlign:'center' ,width:100,visible:false,allowSort:true,
				              formEditorConfig:{
					                   newLine:true,
					                      type:'text',
					                  readOnly:true,
					              fieldVisible:true,
					                labelWidth:100,								
					                     width:200}},
			       {field: 'modifydate',header:'修改时间',	headerAlign:'center', visible:false,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
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
//身份证校验
function onIDCardsValidation(e) {
	var sId = e.value||"";
	 var isValid = cidInfo(sId);
	 if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
	if(sId!=""){
	mini.get("table_id1_editFormPopupWindow_form_birthdate").setValue(idcardStr.brithdate);
	mini.get("table_id1_editFormPopupWindow_form_sex").setValue(idcardStr.sex);
	}
	return true;
}
function checkIsPersonMain(){
	var mainpersonflagvalue = getMiniEditFormField("table_id1.mainpersonflag").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == mainpersonflagvalue || "否" == mainpersonflagvalue)return "";
	var idvalue = getMiniEditFormField("table_id1.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkIsPersonMain.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid},
        async:false,
        success: function (text) {
        	if("repeat" == text){
        		mini.alert("主联系人只能有一个！");
        	}
        	str = text;
        }
    });
	return str;
}
function checkCardNoPersonMain(){
	var cardNo = getMiniEditFormField("table_id1.idcardno").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == cardNo)return "";
	var idvalue = getMiniEditFormField("table_id1.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkCardNoPersonMain.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid,"cardNo":cardNo},
        async:false,
        success: function (text) {
        	if("repeat" == text){
        		mini.alert("身份证号码不能重复！");
        	}
        	str = text;
        }
    });
	return str;
}
</script>
<div id='content_table_id1'></div>