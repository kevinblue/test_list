<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>短信设置</title>
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
				title : '短信设置',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				remoteOper:true,
				entityClassName:'com.tenwa.business.entity.message.MessageConfig',
				showPager:true,
				xmlFileName : '/interface/queryMessageConfig.xml',
				tools : ['add', '-', 'edit'],
				columns : [ 
				    {type : 'indexcolumn'},
					{type : 'checkcolumn'},  
					{header:'记录编号',field:'id',visible : false},
		            {header:'短信服务器主机',field:'host',
						formEditorConfig:{
							width:200,
							labelWidth:300,
		            		required : true
						}
		            },
		            {header:'短信服务器端口',field:'port',
						formEditorConfig:{
							width:200,
							labelWidth:300,
		            		required : true
						}
		            },
		            {header:'短信发送者',field:'sender',
		            	formEditorConfig:{
		            		width:200,
		            		required : true,//是否必填
							newLine: true
						}
		            },
		            {header:'短信发送者密码',field:'senderpassword',
						formEditorConfig:{
							width:200,
		            		required : true
						}
		            },
		            {header:'短信发送的URL',field:'sendmessageurl',
		            	formEditorConfig:{
		            		width:200,
		            		required : true,//是否必填
							newLine: true
						}
		            },
		            {header:'短信发送时参数信息',field:'sendparamsurl',
						formEditorConfig:{
							width:200,
		            		required : true
						}
		            },
		            {header:'短信子码',field:'subcode',
						formEditorConfig:{
							width:200,
							newLine: true,
		            		required : true
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