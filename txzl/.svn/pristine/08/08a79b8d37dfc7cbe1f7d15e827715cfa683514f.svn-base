<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>财务关账日维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var statusValue = [ {id : '是',text : '是'}, {id : '否',text : '否'}];
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id : 'id_closeday',
		renderTo : "id_table_render_table1",
		width : globalClientWidth,
		height : globalClientHeight,
		title : '财务关账日维护', 
		editFormPopupWindowWidth : 800, 
		queryButtonColSpan : 2,
		remoteOper : true, 
		entityClassName : 'com.tenwa.leasing.entity.voucher.InterFinanceCloseDay',
		showPager : true, 
		lazyLoad : false, 
		xmlFileName : '/eleasing/jsp/fund/finance_closeday/finance_closeday_list.xml', 
		params : {},
		beforeShowWindowCallBack:function(miniTable,miniForm, operType){
			if("add"==operType){
				loadDateData();
			}
			return true;
		}, 
		tools : ['add', '-', 'edit'],
		columns : [ 
		    {type:'indexcolumn'}, 
		    {type:'checkcolumn'},
		    {field:'id', header:'id', visible:false, 
			        formEditorConfig:{
		                    readOnly:true, 
		                fieldVisible:false}} ,
			{field : 'closeyear',
							header : '年份',
							headerAlign : 'center',
							formEditorConfig : {
								readOnly : true}},
			{field : 'closemonth',
							header : '月份',
							headerAlign : 'center',
							formEditorConfig : {
								readOnly : true}},
			{field:'closeday', header:'关账日', visible:false, 
						        formEditorConfig:{
			                    readOnly:true, 
				                fieldVisible:false}} ,
			{field:'status',header:'是否有效',
							  formEditorConfig:{
								  newLine:true, 
				             textField:'text',
				              required:true, 
				                width:200,
				            labelWidth:100,
				            fieldVisible:true,
							  type:'combobox', 
							  data:statusValue,
							  defaultValue:"是"}},
			{field:'staffname', header:'关帐人', visible:true, 
		        formEditorConfig:{
	                    readOnly:true, 
	                fieldVisible:false}}, 
	        {field:'closestaff', header:'关帐人id', visible:false, 
	    	       formEditorConfig:{
	    	              readOnly:true, 
	    	              fieldVisible:false}}, 
			{field:'createdate', header:'操作日期', visible:true,
	    	            	dateFormat:"yyyy-MM-dd",
					        formEditorConfig:{
			                   readOnly:true, 
				                fieldVisible:false}} 
		]
	});
});
});


function loadDateData(){
	var params = {};
	params["xmlFileName"] = "/eleasing/jsp/fund/finance_closeday/finance_closeday.xml";
	
    $.ajax({
        url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none" ,
        cache: false,
        async : false,
        data : params,
        success: function (text) {
        	var result = mini.decode(text);
            var data = result.datas[0];
            mini.getbyName("closeyear").setValue(data["closeyear"]);
            mini.getbyName("closemonth").setValue(data["closemonth"]);
            mini.getbyName("closeday").setValue(data["closeday"]);
            mini.getbyName("closestaff").setValue('${sessionScope.loginUser.id}');
			
        },
        error:function(e){
        	mini.alert("初始化信息失败！");
        }
    });
}

</script>
</head>
<body>
	<div id="mini_test_form">
	        <div id="id_table_render_table1"></div>
	</div>
</body>
</html>