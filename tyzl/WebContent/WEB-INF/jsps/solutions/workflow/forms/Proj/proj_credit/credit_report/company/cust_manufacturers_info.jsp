<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function() {
	var showTools = true;
	if('<mini:param  name="isView"/>' == 'true'||isViewHistoryTask==true||reportShowFlag==false){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'manufacturers_info',
			showToolbar  :showTools,
			width:globalClientWidth-20,
			height:200,
			title:"进货厂商",
			showToolbar  :showTools,
			renderTo:'table_id_manufacturers_info',
			hiddenQueryArea:true,
			editFormPopupWindowWidth:700,
			data: JsonUtil.decode('<mini:param  name="proj_report_manufacturers_info" defaultValue="[]"/>'),         
			remoteOper:false,
			pageSize:15,
			showPager:true,
			lazyLoad:false,
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
					     fieldVisible:false}},
			  {field:'companynumber',header:'公司统编',headerAlign:'center',width:100,allowSort:true,
				     formEditorConfig:{
					          newLine:true,
					             type:'text',
					     fieldVisible:true,
					            width:200,
					       labelWidth:100}},
			  {field:'companyname',header:'公司名称',headerAlign:'center',width:100,allowSort:true,
			         formEditorConfig:{
					            type:'text',
					    fieldVisible:true,
					           width:200,
					      labelWidth:100}},
			  {field:'monthavgmoney',header:'月平均往来金额',headerAlign:'center',width:100,allowSort:true,
				     formEditorConfig:{
					         newLine:true,
					            type:'text',
					    fieldVisible:true,
					           vtype:"float",
					           width:200,
					      labelWidth:100}},
			  {field:'paymentway',header:'付款方式',headerAlign:'center',width:100,allowSort:true,
				     formEditorConfig:{
					            type:'text',
					    fieldVisible:true,
					           width:200,
					      labelWidth:100}},
			  {field:'purchaseproject',header:'进货项目',headerAlign:'center',width:100,allowSort:true,
				     formEditorConfig:{
					         newLine:true, 
					            type:'textarea',
					           width:400,
					          height:150,
					    fieldVisible:true,
					         colspan:3,
					      labelWidth:100
				}
			}]
		});
	});
});
</script>
<div id='table_id_manufacturers_info'></div>