<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use(["js/apcomponent/aptable/aptable.js"], function(ApTable) {
		new ApTable({
			id : 'stock_holder',
			width : globalClientWidth-20,
			height : 200,
			frozenStartColumn:0,
			frozenEndColumn:1,
			renderTo:'table_id_stock_holder',
			title:"股东及经营团队",
			showToolbar  :showTools,
			hiddenQueryArea : true,
			frozenStartColumn : 1,
			frozenEndColumn : 3,
			editFormPopupWindowWidth : 700,
			data: JsonUtil.decode('${empty proj_report_stock_holder ? "[]" : proj_report_stock_holder}'),
			remoteOper : false,
			pageSize : 15,
			showPager : false,
			lazyLoad : false,
			tools : [ 'add', '-', 'edit', '-', 'remove'],
        	columns : [ 
        		      {type:'indexcolumn'},
			          {type:'checkcolumn'}, 
				      {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				            formEditorConfig:{
					                readOnly:true,
					            fieldVisible:false}}, 
					  {field:'custid',header:'客户ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				            formEditorConfig:{
					                readOnly:true,
					            fieldVisible:false}},
					  {field:'stockholdername',header:'股东名称',headerAlign:'center',width:100,allowSort:true,
				            formEditorConfig:{
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
					               valueField: 'value'}},
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
					                labelWidth:100,
					                     vtype:"float"}},
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
					                labelWidth:100,
					                     vtype:"float"}},
					   {field:'shareholding',header:'持股比例',headerAlign:'center',width:100,allowSort:true,
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
					                      type:'textarea',
					              fieldVisible:true,
					                   colspan:3,
					                     width:517,
					                    height:70,
					                labelWidth:100}},
					   {field:'bizscopeprimary',header:'主营业务',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
							            newLine:true, 
					                    colspan:3,
					                       type:'textarea',
					                    newLine:true, 
					               fieldVisible:true, 
					                 labelWidth:100,
					                     height:70,
					                      width:517}},
					    {field:'cshmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
				               formEditorConfig:{
					                    newLine:true, 
					                    colspan:3 ,
					                       type:'textarea',
					               fieldVisible:true, 
					                 labelWidth:100,
					                     height:80,
					                      width:517
				}
			}]
		});
	});
});
</script>
<div id='table_id_stock_holder'></div>