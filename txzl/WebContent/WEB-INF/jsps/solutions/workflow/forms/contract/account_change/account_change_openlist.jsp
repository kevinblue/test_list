<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>回笼账号变更</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function(){
	tenwa.createTable({
		id: "account_change",
		renderTo: "id_table_container_account_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "回笼账号变更",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		tools:[{
			html: '回笼账号变更',
			plain: false,
			iconCls: 'icon-addfolder',
			handler: function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if(row){
					var attachmentParams = "contract_id="+row.id;
					startProcess("回笼账号变更流程-1",attachmentParams); 
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		}],
		queryButtonColSpan: 2,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/contract/account_change/account_change_list.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'projid', header: '项目编号',queryConfig:{width:165},
			 renderer:function(e){
		           var rowData = e.record; 
                 return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projid"]+"</a>";}},
                 {field: 'cust_name', header: '客户名称',
         			renderer:function(e){
         	            var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.custid + '\')">' + e.value + '</a>';
         	            return s;}}, 
                 {field:'projectname', header:'项目名称',queryConfig:{newLine:false},
        	         renderer:function(e){
 		               var rowData = e.record; 
                           return "<a href='javascript:void(0);' onclick='viewProjSummary(\""+rowData["projid"]+"\")'>"+rowData["projectname"]+"</a>";}},
                 {field: 'contract_number', header: '业务合同编号',queryConfig:{width:165},
			renderer:function(e){
         		 var rowData = e.record; 
         		 var str="";
         		 str="<a href='javascript:void(0);' onclick='showProjDetail(\""+rowData['id']+"\",\""+rowData['pid']+"\")'>"+rowData['contractnumber']+"</a>";
	                return str;}},
			
			{field: 'lease_acc_number', header: '账号'},
			{field: 'lease_acc_bank', header: '开户行'},
			{field: 'lease_acc_name', header: '收款人'},
			{field: 'department', header: '出单部门'},
			{field: 'contractstatus', header: '合同状态'}
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
 function opencustdetail(id,name){
        	var params = "id="+id+"&isView=true";
      		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
      		var sheight = window.screen.availHeight - 30;
    		var swidth = window.screen.availWidth - 10;
    		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
      		window.open(url, '_blank', winoption);
        }
</script>
</head>
<body>
<div id="id_table_container_account_change"></div>
</body>
</html>