<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use(["js/apcomponent/aptable/aptable.js"], function(ApTable) {
		new ApTable({
			id : 'share_company',
			width : globalClientWidth-20,
			height : 200, 
			frozenStartColumn:0,
			title:"关联企业及分支机构情况",
			frozenEndColumn:1,
			renderTo:'table_id_share_company',
			showToolbar  :showTools,
			hiddenQueryArea : true, 
			frozenStartColumn : 1, 
			frozenEndColumn : 3,
			editFormPopupWindowWidth : 700,
			data: JsonUtil.decode('<mini:param  name="proj_report_share_company" defaultValue="[]"/>'),
			remoteOper : false,
			pageSize : 15, 
			tools : [ 'add', '-', 'edit', '-', 'remove'],
			columns : [
						{type:'indexcolumn'},
						{type:'checkcolumn'}, 
                        {field:'id', header:'记录编号',headerAlign:'center', width:100,allowSort:true,visible:false, 
				               formEditorConfig:{
					                   readOnly:true,
					               fieldVisible:false }},
					    {field:'custid',header:'客户ID', headerAlign:'center',width:100, allowSort:true, visible:false, 
				              formEditorConfig:{
					                  readOnly:true, 
					              fieldVisible:false }},
					    {field:'stockholdername',header:'关联企业名称',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
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
					                 labelWidth:100,
					                      vtype:"float"}},
					    {field:'legalrepresentative',header:'法人代表',headerAlign:'center',width:100,allowSort:true,
				                formEditorConfig:{
					                        type:'text', 
					                fieldVisible:true, 
					                       width:200,
					                  labelWidth:100}},
					    {field: 'relation_shipname', header: '关联关系', 
				                formEditorConfig:{
					                fieldVisible:false,
					           fillFromFieldName:'relationship',
					                fillProperty:'name'}},
					    {field:'relationship',header:'关联关系',visible:false,headerAlign:'center',width:100,allowSort:true,				
				                formEditorConfig:{
					                     newLine:true,
					                  labelWidth:100,
					                     colspan:3,
					                     width:200,
					                fieldVisible:true,
					                showNullItem:"true", 
					                nullItemText:"",
					                   emptyText:"",
					                        type:'combobox',
					                      params:{pid: 'relationship',xmlFileName: '/combos/comboDict.xml'},
					                   textField:'name',
					                  valueField:'value'}},
					    {field:'addr',header:'地址',headerAlign:'center',width:100,allowSort:true,
				                formEditorConfig:{
					                     newLine:true, 
					                     colspan:3,
					                        type:'textarea',
					                     newLine:true, 
					                fieldVisible:true, 
					                  labelWidth:100,
					                      height:70,
					                       width:517}},
					   {field:'bizscopeprimary',header:'主营业务',headerAlign:'center',width:100,allowSort:true,
				                formEditorConfig:{
					                     newLine:true, 
					                     colspan: 3,
					                        type:'textarea',
					                     newLine:true, 
					                fieldVisible:true, 
					                  labelWidth:100,
					                      height:70,
					                       width:517
				}
			}]
		});
	});
});
</script>
<div id='table_id_share_company'></div>