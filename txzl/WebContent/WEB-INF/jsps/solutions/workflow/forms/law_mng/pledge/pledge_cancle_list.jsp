<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>抵质押登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>

<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "pledge_cancle",
		renderTo: "id_table_pledge_cancle",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "抵质押注销",
		remoteOper : false,
		queryButtonColSpan : 6,
		queryButtonNewLine:false,
		showPager: true,
		pageSize: 20,
		tools:[{
			html: '抵质押注销',
			plain: true,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					
					var attachmentParams = "contract_id="+row.id;
					
					var pledge_switch=row.pledge_state;
					if(pledge_switch!='已注销'){
						startProcess("抵质押注销-1",attachmentParams); 
					}else{
						mini.alert('该合同没有可注销抵押物')
					}					
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		},'-',
		{
			html: '查看历史详情',
			plain: true,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					var id = row.id;
			    	showDetail(id); 
					
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		},'-'],
		xmlFileName: '/eleasing/jsp/pledge/pledge_cancled_list.xml',
		//var project_status=[{id:'1',text:'未注销'},{id:'0',text:'已注销'},{id:'-1',text:'部分注销'}],
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			
			/* {field: 'oper', header: '操作',renderer : onActionRenderer,	formEditorConfig : {
				readOnly : true,
				fieldVisible : false
			}}, */
			{field: 'contract_id', header: '合同编号'},
			{field: 'contract_number', header: '业务合同编号',
				queryConfig:{}},
			{field: 'project_name', header: '项目名称',queryConfig:{}},
			{field: 'cust_name', header: '客户名称',queryConfig:{newLine: false}},
			{field: 'card_no', header: '身份证/注册号/统一社会信用代码'},
			{field: 'industry_type', header: '内部行业'},
			{field: 'projmanagename', header: '项目经理'},
			{field: 'department', header: '出单部门'},
			{field: 'pledge_state', header: '抵质押状态',visible:true,
				queryConfig:{
				newLine: true,
				showNullItem:true,
				visible:true,
				type:'combobox',
				textField:'text',
				valueField:'id',
				data:[{id:'未注销',text:'未注销'},{id:'已注销',text:'已注销'},{id:'部分注销',text:'部分注销'}]
			}}
		]
	});
});

/* function onActionRenderer(e) {
	var id = e.record.id;
	var s = '<a class="Edit_Button" href="javascript:opencustdetail(\''
			+ id + '\')">' +'查看历史数据'+ '</a>';
	return s;
}

function opencustdetail(id) {
	var params = "contract_id=" + id + "&isView=true";
	var url = getRootPath()
			+ "/workflow/forms/law_mng/pledge/pledge_cancle_manager03.bi?"
			+ params;
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height="
			+ sheight
			+ "px,width="
			+ swidth
			+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
} */

	function showDetail(id) {
    var url = getRootPath()
		+ "/acl/showPledgeInformation.acl?contract_id="
		+ id + "&opertype=view";
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winosption = "left=0px,top=0px,height="
		+ sheight
		+ "px,width="
		+ swidth
		+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winosption);
}  

</script>
</head>
<body>
		<div id="id_table_pledge_cancle"></div>
</body>
</html>