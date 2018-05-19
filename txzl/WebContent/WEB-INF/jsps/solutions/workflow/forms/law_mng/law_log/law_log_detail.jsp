<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>法务日志信息维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
			id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				editFormPopupWindowHeight:400,
				queryButtonNewLine:false,
				showPager:true,
				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.lawmng.LawLog',
				xmlFileName: '/eleasing/jsp/law_mng/law_log_list.xml',
				tools: [
				        /*{
							html:'诉讼详情记录',
							plain:true,
							iconCls:'icon-edit',
							handler:function(miniTable, buttonText) {
								var miniTable = mini.get("table_id1");
								var columnsConfig = miniTable.columns;
								var selectedRowData = miniTable.getSelected();
								var config = miniTable.config;
								seajs.use([ "js/apcomponent/aptablebase/aptablebase.js" ], function(apTableBase) {
									apTableBase.tableEditOper(miniTable, columnsConfig, selectedRowData, config);
								});
							}
						}*/
						'add','-','edit','-','remove'
				
				       ],
				       afterShowWindowCallBack:function(miniTable,miniForm, operType){
							  var lawApprovalid ='${param["lawApprovalid"]}'||'${requestScope["lawApproval"]}';
						   	  mini.getbyName("relatekey").setValue(lawApprovalid);
						   	  
						}, 
						params:{relatekey : '${param["lawApprovalid"]}'||'${requestScope["lawApproval"]}'},
				columns:[ 
				         	{type:'indexcolumn',width:9},
						   	{type:'checkcolumn',width:9},  
						   	{field:'id',header:'id',visible:false},
						   	{field:'lawregdate',header:'法务登记日期',
										   		formEditorConfig:{
											   		 type:'date',
									                 vtype:'date',
									                 newLine:true,
									                format:'yyyy-MM-dd',
									            allowInput:false,
														maxLength:100}},
						   	{field:'thingmemo',header:'事项说明',formEditorConfig:{newLine:true,colspan:3,
						   		height:200,width:400,type:'textarea',maxLength:1000}} ,
						   	{field:'relatekey',header:'外键',visible:false}
				]
		});
		});
	});
	
	
/*上传资料内容*/

function showanduploadfile(id,custid,type)
{
	var urlFlag = getRootPath()+"/leasing/cust_info/cust_contact/cust_contact_file_list.bi?id="+id+"&cust_id="+custid+"&type="+type;
	mini.open({
       url: urlFlag,
       title: "上传诉讼资料", width: 800, height: 455,
       onload: function () {},
       ondestroy: function (action) {
       	if("savesuccess" == action){
       		window.location.reload();
       	}
       }
   });

}
</script>
</head>
<body></body>
</html>