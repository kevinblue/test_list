<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>公函审批</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css?version=3" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
</head>
<%@include file="/base/mini.jsp"%>
<body style="overflow:hidden;"> 
<div id="id_tasksContainer"></div>
<script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_tasksContainer",
				width: globalClientWidth,
				height: globalClientHeight,
				lazyLoad: false,
				showPager : true,//分页按钮是否显示
				title:'公函审批',
				remoteOper : true,
				entityClassName : 'com.tenwa.leasing.entity.letter.LetterApproval',			
				multiSelect: false,
				loadMode : 'ajax',
				queryButtonNewLine:false,
				queryButtonColSpan:2,
				//tools:['globalQuery','_','exportExcel'],
				xmlFileName:"/eleasing/jsp/letterapproval/letterapproval.xml",
				tools : [{
					html: '公函审批',
					plain: true,
					iconCls: 'icon-addfolder',
					handler: function(miniTable, buttonText){
							//var attachmentParams = "letter_id="+row.id;
							/* var log_user='${sessionScope.login_userid}';
							mini.alert(log_user);
							var attachmentParams ="letter_user="+log_user; */
							var loginUserId = "${empty sessionScope.loginUser.id ? '' : sessionScope.loginUser.id }";
							//mini.alert(loginUserId);
							var attachmentParams = "login_userid="+loginUserId;
							startProcess("公函审批流程-1",attachmentParams);				
					}
				}],			
				columns: [
					{type:'indexcolumn',width:15},
					{field: 'id', header: 'id', visible: false},
					{field: 'letternumber', header: '函件编号',headerAlign:'center',
						queryConfig:{
							type:'text'
						}},
					{field: 'lettername', header: '函件名称',headerAlign:'center',
						queryConfig:{
							type:'text'
						},
						formEditorConfig:{
							type:'text',
							required:true
					}},	
					{field: 'recipient', header: '收件人',headerAlign:'center',
						queryConfig:{
							type:'text'
						},
						formEditorConfig:{
							type:'text'
						}
					},
					{field: 'letterdescription', header: '函件内容描述',headerAlign:'center',
						formEditorConfig:{
						newLine:true,
						colspan:'4',
						width:'100%',
						type:'textarea'
					}},
					{field: 'originator', header: '发起人',headerAlign:'center',
						queryConfig:{
							newLine:true,
							type:'text'
						}
					},
					{field: 'originatime', header: '发起时间',headerAlign:'center',
						queryConfig:{
							
							type:'date',	  		  									      
		  		  			  		range:true,
				defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd"),
								   format:'yyyy-MM-dd'
							
						}
					},
					{field: 'letterstate', header: '函件状态',visible:false,
						formEditorConfig:{
							type:'text',
							defaultValue:'0',
							fieldVisible:false
						}},
						{
							field : '',
							header : '操作',
							formEditorConfig : {
								fieldVisible : false
							},
							renderer : function(e) {
								var id = e.record.letternumber;
								return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
										+ id
										+ "\",\"one\")'>查看公函文件 </a>";
							}
						}
				]
				
			});
		});
	});
   function showanduploadfile(id, type) {
		var urlFlag = getRootPath()
				+ "/leasing/other/letterApproval_file_list.bi?id="
				+ id + "&type=" + type;
		mini.open({
			url : urlFlag,
			title : "查看公函文件",
			width : 800,
			height : 455,
			onload : function() {
			},
			ondestroy : function(action) {
				if ("savesuccess" == action) {
					window.location.reload();
				}
			}
		});

	}
</script>

</body>
</html>