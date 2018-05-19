<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>法务日志信息维护</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
//var localEnabled = [ {id:'',text:''},{id : '否',text : '否'}, {id : '是',text : '是'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'推送任务管理',
				iconCls:'icon-node',
				multiSelect:false,	//是否可以多选
				hiddenQueryArea:false,//是否将查询区域折叠起来
				
				queryButtonColSpan:2,	//查询区的按钮占的列数
				queryButtonNewLine:false,
				editFormPopupWindowHeight:380,
				queryButtonNewLine:false,	//查询区的按钮是不是换行显示
				showPager:true,			//分页显示
				remoteOper:true,		//是否每次操作都提交都提交后台
				addRemoteOperUrl:getRootPath()+"/acl/addRemindTaskInfo.acl",
				editRemoteOperUrl:getRootPath()+"/acl/updateRemindTaskInfo.acl",
				entityClassName:'com.tenwa.business.entity.RemindTask',
				xmlFileName: '/eleasing/jsp/law_mng/remind_things.xml',
				tools:["add",'-','edit','-','remove'],
				columns:[		
						
				         	{type:'indexcolumn',width:50},
						   	{type:'checkcolumn',width:50},  
						   	{field:'id',header:'id',visible:false},
						   	{field:'deployproppdid',header:'流程发起参数1'}, 
						   	{field:'workflowparams',header:'流程发起参数2'},
						   	{field:'workflowname',header:'流程名称',
						   		formEditorConfig:{
						   			newLine:true,
						   		 width: '100%'
						   		},queryConfig:{}
						   	},
						   	{field:'reminduser',header:'发起人',
						   		formEditorConfig:{
						   			type:'combobox',
						   			allowInput:false,
						   			valueField:'id',
						   			textField:'reminduser',						   			
						   			params:{
						   				xmlFileName: '/eleasing/jsp/law_mng/remind_users.xml'
						   			},
						   		}
						   	},
						   	{field:'contractid',header:'合同编号',
						   		formEditorConfig:{
						   			newLine:true,
						   			type:'combobox',
						   			valueField:'contractid',
						   			textField:'contractid',						   			
						   			params:{
						   				xmlFileName: '/eleasing/jsp/law_mng/remind_contract_info.xml'
						   			},
						   			 width: '100%'
						   		},queryConfig:{}
						   		},
						   
						   	{field:'projectname',header:'项目名称',queryConfig:{}},
						   	{field:'docid',header:'流程实例ID',
						   		formEditorConfig:{
						   			newLine:true,
						   		 width: '100%'
						   		}
						   	},
						   	{field:'startdate',header:'开始执行日期',
						   		formEditorConfig:{
							   		 type:'date',
					                 vtype:'date',
					                format:'yyyy-MM-dd',
					            allowInput:false,
										maxLength:100}},
						   	{field:'statu',header:'状态',
								formEditorConfig:{
									type:'combobox',
									defaultValue:'待发起',									
									data:[{id:"待发起",text:"待发起"},{id:"已发起",text:"已发起"}],
									newLine:true,
								}
							},
						   	{field:'keyone',header:'备用主键1',
						   		formEditorConfig:{
						   		 width: '100%'
						   		}
						   	},
						   	{field:'keytwo',header:'备用主键2',
						   		formEditorConfig:{
						   			newLine:true,
						   		 width: '100%'
						   		}
						   	},
						   	{field:'keythree',header:'备用主键3'},
							{field:'cid',header:'合同id',visible:false,
						   		formEditorConfig:{
						   			fillFromFieldName :'contractid',
						   			fillProperty :'cid'
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