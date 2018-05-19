
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目立项</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var extendsql="";
<permission:action code="permission_manager">  
	extendsql += "instr(aa.belongingpeople,'${sessionScope.login_userid}')>0 "
</permission:action>
<permission:action code="permission_qyzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "instr((select wm_concat(id_) from vi_user_leader where qyzj = '${sessionScope.login_userid}'),aa.belongingpeople)>0  "
</permission:action>
<permission:action code="permission_fdqzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "instr((select wm_concat(id_) from vi_user_leader where fdqzj = '${sessionScope.login_userid}'),aa.belongingpeople)>0  "
</permission:action>
<permission:action code="permission_fzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "instr((select wm_concat(id_) from vi_user_leader where fzj = '${sessionScope.login_userid}'),aa.belongingpeople)>0 "
</permission:action>
<permission:action code="permission_zj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "instr((select wm_concat(id_) from vi_user_leader where zj = '${sessionScope.login_userid}'),aa.belongingpeople)>0 "
</permission:action>
<permission:action code="permission_all">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += " 1=1 "
</permission:action>
if(extendsql && extendsql.length>0){
	extendsql = "and ("+extendsql+")"
}
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'项目立项',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:8,
				queryButtonNewLine:false,
				showPager:true,				
				xmlFileName:'/eleasing/workflow/proj/proj_approval/proj_approval_open_list.xml',
				tools:[
					{html:'项目立项',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();							
							if (row) {
								var attachmentParams = "cust_id="+row.custid+"&proj_id="+row.projid;
								startProcess("项目立项-1",attachmentParams); 
							
							} else {
								mini.alert('请你选择需要发起立项客户！！！');
							}
						}
					}         
				],
				params:{
					extendsql:extendsql
				},
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'projid',header:'id',visible:false},
				   	{field:'custname',header:'客户名称',allowSort:true,width:135,queryConfig:{},
				   	 renderer:function(e){
				   	    var row=e.record;
				   	    var custclassname= row.custclassname;				   	   
				   	    if(custclassname=="法人"){
				   		return "<a href='javascript:opencustdetail(\""+row.custid+"\")'>"+row.custname+"</a>";
				   	    }else{
				   	    	return "<a href='javascript:opencustdetail1(\""+row.custid+"\")'>"+row.custname+"</a>";
				   	    }
				   		}
				   	},
				   	{field:'projname',header:'项目名称',queryConfig:{}},
				   	{
						field : 'rzzlamount',
						dataType:"currency",
						header : '融资金额（万元）'
					},
					{field:'leasformname',header:'项目类型'},
					      {field:'custkindname',header:'内部行业'},
				   	{field:'provincename',header:'所在省份'},
				   	{field:'projsourcename',header:'项目来源'},
				   	{field:'linkman',header:'联系人'},
					{field:'custtypename',header:'客户类型'}
				/* 	{field:'custclassname',header:'客户性质'},  */
					
					
				   	
				   /* 	{field:'registcode',header:'身份证号/组织机构代码',queryConfig:{newLine:true}},
				   	{field:'creator',header:'登记人'},
				   	{field:'createdate',header:'登记时间',headerAlign:'center',renderer:"miniextonDateRenderer"} */
				]
			});
		});
	});
	


//点击客户打开详情
function opencustdetail(id){//法人
	var params = "id="+id+"&isView=true";
		//var params = "id="+id;	
		//var url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
		
		var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
}


function opencustdetail1(id){//自然人
	var params = "id="+id+"&isView=true";
	//var params = "id="+id;	
	var url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	
	
	var sheight = window.screen.availHeight - 30;
var swidth = window.screen.availWidth - 10;
var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
</script>
</head>
<body>

</body>

</html>