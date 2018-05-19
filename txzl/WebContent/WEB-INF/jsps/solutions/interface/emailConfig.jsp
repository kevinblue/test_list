<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>邮件设置</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '邮件设置',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				remoteOper:true,
				entityClassName:'com.tenwa.business.entity.email.EmailConfig',
				showPager:true,
				xmlFileName : '/interface/queryEmailConfig.xml',
				tools : ['add', '-', 'edit'],
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},  
					{header:'记录编号',field:'id',visible : false},
		            {header:'邮箱服务器主机',field:'host',
						formEditorConfig:{
							width:200,
							labelWidth:300,
		            		required : true
						}
		            },
		            {header:'邮箱服务器端口',field:'port',
						formEditorConfig:{
							width:200,
							labelWidth:300,
		            		required : true
						}
		            },
		            {header:'邮箱发送者',field:'fromuser',
		            	formEditorConfig:{
		            		width:200,
		            		required : true,//是否必填
							newLine: true
						}
		            },
		            {header:'邮箱发送者密码',field:'fromuserpassword',
						formEditorConfig:{
							width:200,
		            		required : true
						}
		            },
		            {header:'邮箱发送者邮件地址',field:'fromuseremailaddress',
		            	formEditorConfig:{
		            		width:200,
		            		required : true,//是否必填
							newLine: true
						}
		            },
		            {header:'邮箱发送者真实身份',field:'fromuserrealname',
						formEditorConfig:{
							width:200,
		            		required : true
						}
		            },
					{header:'是否发送',field:'issend',
						formEditorConfig:{
							newLine: true,
							type : 'combobox',
							required : true,
							multiSelect : false, 
							valueField : 'value',
							textField : 'name',
							value: "否",
							data: '[{"name":"是","value":"是"},{"name":"否","value":"否"}]'
						}
					}, {
						header : '允许发送短信的主机IP',
						field : 'allowsendips',
						formEditorConfig : {
							newLine : true,
							required : true,
							colspan : 4,
							width : '100%',
							height : '200',
							type : 'textarea'
						}
					}
						
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>