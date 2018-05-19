<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>催款员负责合同页面列表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var isMulti = false;
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id9',
			width:globalClientWidth,
			height:globalClientHeight,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			multiSelect:true,
			editFormPopupWindowWidth:700,
			title:'催款员负责合同页面列表' ,
			remoteOper:true,
			pageSize:50,
			showPager:true,
			lazyLoad:false,
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueDunningInfo',
			xmlFileName:'/eleasing/jsp/fund/fund_overdue/overdue_dunning_table.xml',
			queryButtonColSpan:2,
			beforeShowWindowCallBack:function(miniTable,miniForm, operType){
				isMulti = false;
				return true;
			},
			params:{},
		/* 	tools:[ 'edit','-',
					{
				     html:'批量修改',
				    plain:true,
				  iconCls:'icon-edit', 
				  handler:function(miniTable, buttonText) { 
					var row = miniTable.getSelecteds();
					if(row.length>0){
						var multiform = new mini.Form("multiform");
						var multieditWindow = mini.get("multieditWindow");
						multieditWindow.show();
						multiform.clear();
						isMulti = true;
					}else{
						mini.alert("请至少选中一行！");
					}
				}
			}], */
		
			columns:[ 
				{type:'indexcolumn'},
			    {field:'id',header:'部门编号',headerAlign:'center',width:100,allowSort:true,visible:false},
			    {field:'deptid',header:'部门id',headerAlign:'center',width:100,allowSort:true,visible:false},
				{field: 'name', header: '业务部门', visible: true,queryConfig:{},
							     formEditorConfig:{
								     fieldVisible:false }},	 	        
				{field: 'operation', header: '资产操作',renderer: function(e){
						var pid=e.record.id;
						return "<a href='"+getRootPath()+"/leasing/fund/fund_overdue/overdue_dunning_list.bi?pid="+pid+"' target='_blank'>"+ "维护资产主管权限" + "</a>";;
					}
				},
				{field: 'operation', header: '信审操作',renderer: function(e){
					var pid=e.record.id;
					return "<a href='"+getRootPath()+"/leasing/fund/fund_overdue/xinshen_maintenance_list.bi?pid="+pid+"' target='_blank'>"+ "维护信审部门权限" + "</a>"; 
				}
			}
				]
		});
	});
	
});

</script>
</head>
<body>
	
</body>
</html>
<script>

</script>