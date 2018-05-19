<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>快递单打印</title>
		<%@include file="/base/mini.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
  		<script type="text/javascript">
  		jQuery(function() {
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
  				  new ApTable({
	  					id:'table_expressdeliver_id',
	  					renderTo:"id_table_render_table1",
	  					width:globalClientWidth,
	  					height:globalClientHeight,
	  					iconCls:'icon-node',
	  					hiddenQueryArea:false,
	  					multiSelect:true,
	  					queryButtonColSpan:4,
	  					queryButtonNewLine:false,
	  					title:'快递单打印' ,
	  					remoteOper:true,
	  					pageSize:30,
	  					showPager:true,
	  					lazyLoad:false,
	  					//entityClassName:'com.tenwa.leasing.entity.other.ExpressdeliverInfo',
	  					xmlFileName:'/eleasing/jsp/onhiredocexpress/onhire_express_list.xml',
	  					tools:[ 'exportExcel'],
	  					columns:[ 
	  					 {type:'indexcolumn'},
	  					 {type:'checkcolumn'}, 
	  					 {field:'id',header:'id',headerAlign:'center',visible: false},
	  					 {field:'projectname',header:'项目名称'},
		   			     {field:'mailadd',header:'邮寄地址'},
		   			  	 {field:'mobilenumber',header:'手机'},
		   				 {field:'personname',header:'收件人'},
		   				 {field:'postcode',header:'邮编'}
		   			
	  					 
								                    
							]
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