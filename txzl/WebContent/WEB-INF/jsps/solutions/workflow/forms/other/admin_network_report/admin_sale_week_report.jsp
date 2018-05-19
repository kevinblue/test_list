<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>周报汇总</title>
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
//权限查看
var extendsql="";
var extendsqller="";
<permission:action code="permission_system">
extendsqller += "or 1=1"
</permission:action>
<permission:action code="permission_manager">
	extendsql += "tw.register_id = '${sessionScope.login_userid}' "
</permission:action>
<permission:action code="permission_qyzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "tw.register_id  in (select id_ from vi_user_leader where qyzj = '${sessionScope.login_userid}')  "
</permission:action>
<permission:action code="permission_fdqzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "tw.register_id  in (select id_ from vi_user_leader where fdqzj = '${sessionScope.login_userid}')  "
</permission:action>
<permission:action code="permission_fzj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "tw.register_id  in (select id_ from vi_user_leader where fzj = '${sessionScope.login_userid}') "
</permission:action>
<permission:action code="permission_zj">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += "tw.register_id  in (select id_ from vi_user_leader where zj = '${sessionScope.login_userid}') "
</permission:action>
<permission:action code="permission_all">
	if(extendsql.length>0){extendsql += "or "}
	extendsql += " 1=1 "
</permission:action>
if(extendsql && extendsql.length>0){
	extendsql = "and ("+extendsql+")"
}
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;

	jQuery(function() {
		tenwa
				.createTable({
					id : "work_week_report",
					renderTo : "id_table_work_week_report",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "超级管理员",
					remoteOper : false,
					showPager : true,
					pageSize : 20,
					/* tools : [
							{
								html : '新增',
								plain : true,
								iconCls : 'icon-addfolder',
								handler : function(miniTable, buttonText) {
									var multiform = new mini.Form("overduedunningcored");
									var multieditWindow = mini.get("editWindow");
									multieditWindow.show();
									multiform.clear();
									isMulti = true;
								}
							},
							"-",
							{
								html : '修改',
								plain : true,
								iconCls : 'icon-edit',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									var userid='${sessionScope['login_userid']}';
									if (rows.length == 0) {
										mini.alert("请选择要修改的条目！");
										return false;
									}
									if(userid!=rows[0].tuid){
										mini.alert("只可修改自己的日程！")
										return false;
									}
									var weekstatus=rows[0].weekstyle;
									if (weekstatus=='已完成') {
										mini.alert("该报表已完成无法修改！");
										return false;
									}
									
									var buttonFlag = 'add';
									var url = getRootPath()
											+ "/acl/showSaleWeekReport.acl?opertype=edit&workid="
											+ rows[0].id;
									var sheight = window.screen.availHeight - 30;
									var swidth = window.screen.availWidth - 10;
									var winoption = "left=0px,top=0px,height="
											+ sheight
											+ "px,width="
											+ swidth
											+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
									window.open(url, '_blank', winoption);
								}
							},
							"-",
							{
								html : '删除',
								plain : true,
								iconCls : 'icon-remove',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									var userid='${sessionScope['login_userid']}';
									if (rows.length == 0) {
										mini.alert("请选择要删除的条目！");
										return false;
									}
									if (rows[0].weekstyle == "已完成") {
										mini.alert("该申请已完成无法删除！");
										return false;
									}
									if(userid!=rows[0].tuid){
										mini.alert("只可删除自己的日程！")
										return false;
									}

									var url = getRootPath()
											+ "/acl/deleteSaleWeekReport.acl";
									var params = {};
									params["workid"] = rows[0].id;
									mini.confirm("确认要删除该工作日程？", "确认？", function(action){
										if (action == "ok"){
											$.ajax({
												url : url,
												data : params,
												type : 'post',
												async : false,
												success : function(e) {
													miniTable.reload();
												}
											}); 											 
										}
									});
								}
							},'-',{
								html : '完成草稿',
								plain : true,
								iconCls : 'icon-edit',
								handler : function(miniTable, buttonText) {
									var rows = miniTable.getSelecteds();
									var userid='${sessionScope['login_userid']}';
									if (rows.length == 0) {
										mini.alert("请选择要完成的条目！");
										return false;
									}
									if (rows[0].weekstyle == "已完成") {
										mini.alert("该申请已完成不需要修改！");
										return false;
									}
									if(userid!=rows[0].tuid){
										mini.alert("只可提交完成自己的日程！")
										return false;
									}

									var url = getRootPath()
											+ "/acl/updateSaleWeekReport.acl";
									var params = {};
									params["workid"] = rows[0].id;
									$.ajax({
										url : url,
										data : params,
										type : 'post',
										async : false,
										success : function(e) {
											miniTable.reload();
										}
									});
								}
							} ], */
					queryButtonColSpan : 2,
					xmlFileName : '/eleasing/jsp/other/get_admin_sale_week_report_list.xml',
					params:{extendsql:extendsql,extendsqller:extendsqller},
					columns : [
							{
								type : 'indexcolumn'
							},
							/* {
								type : 'checkcolumn'
							}, */
							{
								field : 'id',
								header : 'id',
								visible : false
							},
							{
								field : 'serialid',
								header : '编号',
								align:'center',
								renderer : function(e) {
									var id = e.record.id;
									return "<a href='javascript:showDetail(\""
											+ id + "\")'>"
											+ e.record.serialid + "</a>";}
							}, {
								field : 'realname',
								header : '填写人',
								align:'center',
							}, {
								field : 'tuid',
								header : '人员编号',
								align:'center',
								visible : false
								
							}, {
								field : 'deptname',
								header : '业务部',
								align:'center',
							}, {
								field : 'childdeptname',
								header : '业务子部',
								align:'center',
								queryConfig:{},
								
							}, {
								field : 'startdate',
								header : '开始日期',
								align:'center',
								queryConfig:{
									type:'date'
								},
									
							}, {
								field : 'enddate',
								header : '结束日期',
								align:'center',
							},  {
								field : 'weekstyle',
								header : '状态',
								align:'center',
								queryConfig:{
									type : 'combobox',
									data:[{id:'',text:''},{id:'草稿',text:'草稿'},{id:'已完成',text:'已完成'}]
								}
							} ]
				});
	});

	function showDetail(id) {
		var url = getRootPath()
				+ "/acl/showAdminSaleWeekReport.acl?workid="
				+ id + "&opertype=view"
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winosption = "left=0px,top=0px,height="
				+ sheight
				+ "px,width="
				+ swidth
				+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winosption);
	}
	function changeDate(e){
		var starttime=mini.formatDate(mini.getbyName("startdate").getValue(),'yyyy-MM-dd');
		var days=mini.parseDate(starttime);
		var ss=new Date(days.getTime() + 24*6*60*60*1000); 
		var endtime=mini.formatDate(ss,"yyyy-MM-dd");
		mini.getbyName("enddate").setValue(endtime);
	}
	function saveApplication(){
		var params={};
		var starttime=mini.formatDate(mini.getbyName("startdate").getValue(),'yyyy-MM-dd');
		var endtime=mini.getbyName("enddate").getValue();
		var day=new Date(starttime).getDay();
		if(day!="1"){
			mini.alert("周报开始日期必须为星期一！")
			return ;
		}
		params["startdate"]=starttime;
		params["enddate"]=endtime;
		$.ajax({
			url:getRootPath()+"/acl/addSaleWeekReportlication.acl",
			data:params,
			type:'post',
			async:false,
			success:function(e){
				var result=mini.decode(e);
				if(result.flag=='true'){
					var buttonFlag = 'view';
					var url = getRootPath()+ "/acl/showSaleWeekReport.acl?opertype=add&startdate="+starttime;
					var sheight = window.screen.availHeight - 30;
					var swidth = window.screen.availWidth - 10;
					var winoption = "left=0px,top=0px,height="
						+ sheight
						+ "px,width="
						+ swidth
						+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
				    window.open(url, '_blank', winoption);
				    var multieditWindow = mini.get("editWindow");
				    multieditWindow.hide();
				    window.location.reload();
				}else if(result.flag=='tru'){
					mini.alert(result.msg);
				}else{
					mini.alert('发生异常：'+result.msg);
				}
			}
		});	
	}
	function clsoeApplication(){
		var multieditWindow = mini.get("editWindow");
		multieditWindow.hide();
	}
</script>
</head>
<body>
	<div id="id_table_work_week_report"></div>
	<div id="editWindow" class="mini-window" title="周报登记" style="width:300px;" showModal="true" allowResize="true" allowDrag="true">
		<div id="overduedunningcored">
			<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="application_base_info">
				<tr class="tr-odd" style="height:50px">
			        <td class="td-content-title" >开始时间：</td>
			        <td class="td-content" >
						<input name="startdate" class="mini-datepicker" type="date" format="yyyy-MM-dd" required="true" allowinput="false" value="${requestScope['startdate']}" onvaluechanged="changeDate(e)"/>
			        </td>
			    </tr>
			    <tr class="tr-odd" style="height:50px">
			        <td class="td-content-title" >结束时间：</td>
			        <td class="td-content">
						<input  name="enddate" class="mini-textbox" readonly="readonly" type="date" format="yyyy-MM-dd" required="true" allowinput="false" value="${requestScope['enddate']}" />
			        </td>
			    </tr>
			    </tr class="tr-odd" style="height:50px;" >
			        <td colspan="2" style="text-align:center"><a class="mini-button" iconCls="icon-save" onclick="saveApplication">确定</a>
			        <a class="mini-button" iconCls="icon-save" onclick="clsoeApplication">取消</a></td>
			    </tr>
			</table>
		</div>
	</div>
</body>
</html>