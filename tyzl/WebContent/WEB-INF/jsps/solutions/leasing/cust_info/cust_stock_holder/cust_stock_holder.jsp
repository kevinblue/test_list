<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id5',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'content_table_id5',
			hiddenQueryArea:true,
			frozenStartColumn:1,
			frozenEndColumn:3,
			editFormPopupWindowWidth:700,
			remoteOper:true,
			validateForm:function(miniTable, miniForm, editFormItemOperFlag){
				if(checkCardNoCustStockHolder()=="repeat") return;
				return true;},
			entityClassName:'com.tenwa.leasing.entity.cust.CustStockHolder',
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			showToolbar: showTools,
			xmlFileName:'/eleasing/jsp/cust_info/cust_stockholder/cust_stockholder.xml',
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
					{field:'stockholdername',header:'股东名称',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					               required:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
				    {field: 'cust_info_typename', header: '法人/个人', 
				           formEditorConfig:{
					           fieldVisible: false,
					      fillFromFieldName:'stockholdertype',
					           fillProperty:'name'}},
					{field:'stockholdertype',header:'法人/个人',visible:false,headerAlign:'center',width:100,allowSort:true,	
				           formEditorConfig:{
					             labelWidth:100,
					                  width:200,
					           fieldVisible: true,
					           showNullItem:"true", 
					           nullItemText:"",
					              emptyText:"",
					                   type:'combobox',
					                 params:{pid: 'cust_info_type',xmlFileName: '/combos/comboDict.xml'},
					              textField:'name',
					             valueField:'value'}},
					{field:'orgcode',header:'身份证/组织机构代码',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                newLine:true,
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
					{field:'regcapital',header:'注册资本',headerAlign:'center',width:100,allowSort:true,
				           formEditorConfig:{
					                   type:'text',
					           fieldVisible:true,
					                  width:200,
					             labelWidth:100}},
					{field:'capitaltype',header:'出资方式',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					               newLine:true,
					                  type:'text',
					          fieldVisible:true,
					                 width:200,
					            labelWidth:100}},
				    {field:'capitalmoney',header:'出资金额',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					                  type:'text',
					          fieldVisible:true,
					                 width:200,
					            labelWidth:100}},
					{field:'shareholding',header:'持股比例(%)',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					               newLine:true,
					                  type:'text',
					          fieldVisible:true,
					                 vtype:"float",
					                 width:200,
					            labelWidth:100}},
					 {field:'legalrepresentative',header:'法人代表',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					                  type:'text',
					          fieldVisible:true,
					                 width:200,
					            labelWidth:100}},
					 {field:'addr',header:'地址',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					               newLine:true,
					                  type:'text',
					          fieldVisible:true,
					               colspan: 3,
					                 width:"100%",
					            labelWidth:100}},
					  {field:'bizscopeprimary',header:'主营业务',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					               newLine:true,
					               colspan: 3,
					                  type:'textarea',
					               newLine:true,
					          fieldVisible:true,
					            labelWidth:100,
					                 width:"100%"}},
					  {field:'cshmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
				          formEditorConfig:{
					               newLine:true,
					               colspan: 3,
					                  type:'textarea',
					          fieldVisible:true,
					            labelWidth:100,
					                 width:"100%"
				}
			}]
		});
	});
});
function checkCardNoCustStockHolder(){
	var cardNo = getMiniEditFormField("table_id5.orgcode").getValue();//mini.getbyName("mainpersonflag").getValue();
	if("" == cardNo)return "";
	var idvalue = getMiniEditFormField("table_id5.id").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkCardNoCustStockHolder.acl",
        type: "post",
        cache: false, 
        data :{"id":idvalue,"custId":custid,"cardNo":cardNo},
        async:false,
        success: function (text) {
        	var obj=mini.decode(text);
        	if(obj.message.length>1){
        		mini.alert(text+"身份证/组织机构代码不能重复！");
        		str="repeat"
        	}else{
        		str = "";
        	}
        	
        }
    });
	return str;
}
</script>
<div id='content_table_id5'></div>