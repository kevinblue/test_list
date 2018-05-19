<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>工作周报表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
    var startdate=${requestScope['startdate']};
    var registerid="${requestScope['registerid']}";
    var saleweekid="${requestScope['workid']}";
	var currentClientWidth = document.documentElement.clientWidth-1;
	var userEnable=[{id:'本周',text:'本周'},{id:'下周',text:'下周'}];
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		tenwa.createTable({
					id : "work_week_report1",
					renderTo : "id_table_work_week_report1",
					width : currentClientWidth,
					height :305,
					lazyLoad : false,
					title : "2、签约项目",
					showToolbar: showTools,
					remoteOper : false,
					tools : ['add','-','edit','-','remove'],
					queryButtonColSpan : 2,
					remoteOper:true,
					showPager : true,
					pageSize : 5,
					entityClassName:'com.tenwa.leasing.entity.cust.SaleWeekReportDetail',
					xmlFileName : '/eleasing/jsp/other/get_sale_week_report_detail_list1.xml',
					params:{projstatus:'签约',startdate:"${requestScope['startdate']}",registerid:"${requestScope['registerid']}",saleweekid:"${requestScope['workid']}"},
					columns : [
							{type : 'indexcolumn'},
							{type : 'checkcolumn'},							
							{field : 'projstatus',header : '阶段',
								formEditorConfig:{
									newLine:true,
									value:'签约',
									readOnly:true
								}	
							},
							{field : 'projdate',header : '时间',
								formEditorConfig:{									
									type: 'combobox',
									data: userEnable,
									required:true,
								}
							},
							{field:'id',header:'id',visible: false},							
							{field: 'contractidname', header:'项目名称', visible: true,
						          formEditorConfig:{
						        	  fieldVisible: false
							          }},
							{field: 'contractid',header: '项目名称',visible: false,formEditorConfig: {
								type : 'queryinput',
								labelWidth:100,
								fieldVisible: true,
								required: true,
								readOnly: false,
								newLine:true,
								valueField : 'contractid',
								textField : 'pname',
								onSelect:function($me, inputObj, rowData){
									getMiniEditFormField("work_week_report1.projnumber").setValue(rowData.contractnumber);
									getMiniEditFormField("work_week_report1.projmoney").setValue(rowData.equipamt);
									getMiniEditFormField("work_week_report1.area").setValue(rowData.areaname);
									getMiniEditFormField("work_week_report1.contractidname").setValue(rowData.contractidname);
								},
								params : {
									cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_saleinfo.xml'
								}
						}},
							{field : 'projnumber',header : '业务合同编号',
								formEditorConfig:{
									readOnly:true,
								}
							},  
							{field : 'projmoney',header : '合同金额',summary:true,align:'right',
								formEditorConfig:{
									newLine:true,
									readOnly:true,
								}
							}, 
							{field : 'area',header : '区域',
								formEditorConfig:{
									readOnly:true,
								}
							},												
							{field : 'saleweekid',header : '周报编号',visible: false,
								formEditorConfig:{
									readOnly:true,
									fieldVisible: false,
									defaultValue:"${requestScope['workid']}"
								}
							}
							]
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_work_week_report1"></div>
</body>
</html>