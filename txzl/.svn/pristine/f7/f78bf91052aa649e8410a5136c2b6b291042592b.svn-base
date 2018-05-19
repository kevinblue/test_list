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
    var saleweekid="${requestScope['saleweekid']}";
	var currentClientWidth = document.documentElement.clientWidth-1;
	var userEnable=[{id:'本周',text:'本周'},{id:'下周',text:'下周'}];
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		tenwa.createTable({
					id : "work_week_report2",
					renderTo : "id_table_work_week_report2",
					width : currentClientWidth,
					height :'100%',
					lazyLoad : false,
					title : "3、过会项目",
					showToolbar:showTools,
					remoteOper : false,
					tools : ['add','-','edit','-','remove'],
					queryButtonColSpan : 2,
					remoteOper:true,
					/* sumCellAlign:'left', */
					/* showPager : true,
					pageSize : 5, */
					entityClassName:'com.tenwa.leasing.entity.cust.SaleWeekReportDetail',
					xmlFileName : '/eleasing/jsp/other/get_sale_team_week_report_detail_list2.xml',
					params:{projstatus:'过会',startdate:"${requestScope['startdate']}",registerid:"${requestScope['registerid']}",saleweekid:"${requestScope['saleweekid']}"},
					columns : [
							{type : 'indexcolumn'},
							/* {type : 'checkcolumn'}, */							
					       	/* {field : 'projstatus',header : '阶段',
								formEditorConfig:{	}
							}, */
							{field : 'projstatus',header : '阶段',align:'center',visible: false,
								formEditorConfig:{
									newLine:true,
									value:'过会',
									readOnly:true
								}	
							},
							/* {field : 'projstatus',header :'' ,formEditorConfig:{}}, */
							{field : 'projdate',header : '时间',align:'center',width:'30',
								formEditorConfig:{									
									type: 'combobox',
									data: userEnable,
									required:true,
								}
							},
							
							{field:'id',header:'id',visible: false},			
							{field: 'projname', header:'项目名称', visible: true,align:'center',width:'150',
						          formEditorConfig:{
						        	  fieldVisible:false
						        	 }},
							{field: 'proj',header: '项目名称',visible: false,formEditorConfig: {
								type : 'queryinput',
								labelWidth:100,
								multiSelect : false,
								fieldVisible: true,
								required: true,
								readOnly: false,
								newLine:true,
								valueField : 'value',
								textField : 'pname',
								onSelect:function($me, inputObj, rowData){
									getMiniEditFormField("work_week_report2.projnumber").setValue(rowData.projid);
									getMiniEditFormField("work_week_report2.projmoney").setValue(rowData.equipamt);
									getMiniEditFormField("work_week_report2.area").setValue(rowData.areaname);
									getMiniEditFormField("work_week_report2.projname").setValue(rowData.projname);
								},
								params : {
									cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_saleinfo2.xml'
								}
						}},
							{field : 'projnumber',header : '项目编号',align:'center',
								formEditorConfig:{
									readOnly:true,
								}
							},  
							{field : 'projmoney',header : '项目金额',summary:true,align:'right',width:'80',
								formEditorConfig:{
									newLine:true,
									readOnly:true,
								}
							}, 
							{field : 'area',header : '区域',align:'center',
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
							],
							onupdate:function(){
								var data = mini.get("work_week_report2").getData();
								var len = data.length;
								$("#id_table_work_week_report2").height(150+len*20);									
								mini.get("id_panelContainer_work_week_report2").doLayout();
							}
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_work_week_report2"></div>

</body>
</html>