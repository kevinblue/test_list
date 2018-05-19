<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>央行基准利率</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var adjustFlag = [ {id : '是',text : '是'}, {id : '否',text : '否'}];
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id : 'table1',
		renderTo : "id_table_render_table1",
		width : globalClientWidth,
		height : globalClientHeight,
		title : '央行基准利率调整', 
		editFormPopupWindowWidth : 800, 
		queryButtonColSpan : 2,
		remoteOper : true, 
		entityClassName : 'com.reckon.entity.interest.FundStandardInterest',
		showPager : true, 
		lazyLoad : false, 
		xmlFileName : '/eleasing/jsp/base_rate/base_rate_manager_info.xml', 
		params : {},
		tools : [ 'add', '-', 'edit', '-', 'remove' ],
		columns : [ 
		    {type:'indexcolumn'}, 
		    {type:'checkcolumn'},
		    {field:'id', header:'记录编号', visible:false, 
			        formEditorConfig:{
		                    readOnly:true, 
		                fieldVisible:false}} ,
			{field:'startdate',header:'利率开始执行 时间',dateFormat:"yyyy-MM-dd",
			        formEditorConfig:{
					         newLine:false, 
					            type:'date',
                             equired:true, 
                                width:200,
                           labelWidth:100,
				         defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
				               format:'yyyy-MM-dd', 
				           timeFormat:'HH:mm:ss', 
				              showTime:true }},
			{field:'isclose',header:'调息是否完成',
					  formEditorConfig:{
				             textField:'text',
				              required:true, 
				                 width:200,
				            labelWidth:100,
				          fieldVisible:true,
								  type:'combobox', 
								  data:adjustFlag}},
		   {field:'ratehalf',header:'利息调整幅度_六月',  
					  formEditorConfig:{
				                 vtype:'double',
				               newLine:true,
				                  type:'text', 
				          fieldVisible:true,
				              required:true,
				                 width:200,
				            labelWidth:100}},				    
		  {field:'baseratehalf', header:'利息央行基准_六月', 
			          formEditorConfig:{
				                 vtype:'double',
				                  type:'text', 
				          fieldVisible:true, 
				              required:true, 
				                 width:200,
				            labelWidth:100}}, 
           {field:'rateone',header:'利息调整幅度_1年',newLine:true,
			          formEditorConfig:{
				                 vtype:'double',
				               newLine:true,
				                  type:'text', 
				          fieldVisible:true, 
				              required:true, 
				                 width:200,
				            labelWidth:100}}, 
	       {field:'baserateone',header:'利息央行基准_1年', newLine:true,
			          formEditorConfig:{
				                 vtype:'double',
				                  type:'text',
				          fieldVisible:true, 
				             required:true, 
				                width:200,
				           labelWidth:100}},
			{field:'ratethree',header:'利息调整幅度_3年',newLine:true,
			         formEditorConfig:{
				                vtype:'double',
				              newLine:true,
								 type:'text', 
					     fieldVisible:true, 
						     required:true, 
								width:200,
						   labelWidth:100}},   
		     {field:'baseratethree',header:'利息央行基准_3年', newLine:true,
			         formEditorConfig:{
				                vtype:'double',
				                 type:'text', 
				         fieldVisible:true, 
				             required:true, 
				                width:200,
				           labelWidth:100}},
			 {field:'ratefive', header:'利息调整幅度_5年', newLine:true,
			         formEditorConfig:{
				                vtype:'float',
				              newLine:true,
				                 type:'text',
				         fieldVisible:true, 
				             required:true, 
				                width:200,
				           labelWidth:100}}, 
			  {field:'baseratefive', header:'利息央行基准_5年', 
			         formEditorConfig:{
				                vtype:'float',
				                 type:'text', 
				         fillProperty:'id', 
				         fieldVisible:true, 
				             required:true, 
				                width:200,
				           labelWidth:100}}, 
			  {field:'rateabovefive', header:'利息调整幅度_5年以上', newLine:true,
			         formEditorConfig:{
				                vtype:'float',
				              newLine:true,
				                 type:'text', 
				         fieldVisible:true, 
				             required:true, 
				                width:200,
				           labelWidth:100}}, 
			  {field:'baserateabovefive',header:'利息央行基准_5年以上', 		
			         formEditorConfig:{
				                 type:'text',
				         fieldVisible:true,
				             required:true, 
				                width:200,
				           labelWidth:100
			}
		}]
	});
});
});




</script>
</head>
<body>
	<div id="mini_test_form">
	        <div id="id_table_render_table1"></div>
	</div>
</body>
</html>