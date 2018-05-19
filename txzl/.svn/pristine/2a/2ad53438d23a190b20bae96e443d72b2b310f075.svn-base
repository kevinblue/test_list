<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>开票信息变更</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "contract_tax_change",
		renderTo: "id_table_container_account_tax_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "开票信息变更",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools:[{
			html: '开票信息变更',
			plain: false,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					var attachmentParams = "contract_id="+row.id+"&cid="+row.cid;
					startProcess("开票信息变更-1",attachmentParams); 
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		}],
		queryButtonColSpan: 2,
		xmlFileName: '/eleasing/workflow/contract/contract_tax_change/contract_tax_change_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'cid', header: 'cid', visible: false},	
			{field: 'custid', header: '客户id',visible: false},
			{field: 'projid', header: '项目编号',
				 renderer:function(e){
			           var rowData = e.record; 
	                 return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},
			{field: 'custname', header: '客户名称',
				renderer:function(e){
			   	      var row=e.record;
			   		return "<a href='javascript:showcustinfo(\""+row.custid+"\")'>"+row.custname+"</a>";
			   		}},
			   	 {field:'projectname', header:'项目名称',queryConfig:{newLine:false},
	        	         renderer:function(e){
	 		               var rowData = e.record; 
	                           return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projectname"]+"</a>";}}, 		
	                       	{field: 'contractnumber', header: '业务合同编号',queryConfig:{width:165},
	               				renderer:function(e){
	               	         		 var rowData = e.record; 
	               	         		 var str="";
	               	         		 str="<a href='javascript:void(0);' onclick='showProjDetail(\""+rowData['id']+"\",\""+rowData['pid']+"\")'>"+rowData['contractnumber']+"</a>";
	               		                return str;}},
	               			/* {field: 'projectname', header: '项目名称',queryConfig:{width:165}}, */
	               			{field: 'contractid', header: '合同编号'},
	                           {field: 'taxtype', header: '纳税人类别'},
			{field: 'taxcode', header: '纳税人识别号'},
			{field: 'taxbank', header: '开户行'},
			{field: 'tax_acc', header: '开户账号'},
			{field: 'invoiceadd', header: '开票地址'},
			{field : 'invoicephone', header : '开票电话'}
		]
	});
});
//项目编号
function viewProjSummary(keyOne){
    var URL = "${pageContext.request.contextPath}/jbpm/getProjSummaryHistoryInfos.action?keyOne="+keyOne;
    openFullScreenWindow(URL);
}
//业务合同编号
function showProjDetail(cid,pid){
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}

     //客户信息   
 function showcustinfo(custid){
		var params = "id="+custid+"&isView=true";
		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
	}
</script>
</head>
<body>
<div id="id_table_container_account_tax_change"></div>
</body>
</html>