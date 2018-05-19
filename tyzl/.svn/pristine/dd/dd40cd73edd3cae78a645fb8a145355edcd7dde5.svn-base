<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>短信发送信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">

jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				operValidate:function(miniTable,rowData,oper){
					if("edit"==oper){
						var checkRowDatas = miniTable.getSelecteds();
						if(checkRowDatas && checkRowDatas.length > 0){
							var rowData = checkRowDatas[0];
							if(rowData.sendflag != '0'){
								alert("只能修改未发送的邮件。");
								return false;
							}
						}
					}
					return true;
				},
				validateForm:function(miniTable, miniForm, editFormItemOperFlag){
					var fdata=miniForm.getData();
					var pattern =  /\d{11}/;
			        if (pattern.test(fdata.phonenumber) == false) {
			            mini.alert("电话号码格式错误");
					    return false;
			        }
			        return true;
				},
				addRemoteOperUrl:getRootPath()+"/acl/addSmsTask.acl",
				editRemoteOperUrl:getRootPath()+"/acl/updateSmsTask.acl",
				removeRemoteOperUrl:getRootPath()+"/acl/removeSmsTask.acl",
				remoteOper:true,
				id : 'table_id1',
				width : globalClientWidth,
				height : globalClientHeight,
				title : '短信发送信息',
				iconCls : 'icon-node',
				multiSelect : true,
				hiddenQueryArea : true,//是否将查询区域折叠起来
				queryButtonColSpan : 2,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName : '/interface/sms/sms_task_list.xml',
				tools : ['add', '-', 'edit', '-','remove','-',
				    {
					html : '重新发送',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
						var operTitle = "重新发送";
						var operAction = "sendSmsAgain";
						
						if(0 == checkedRowDatas.length){
							alert("请选择要"+operTitle+"的记录！！");
							return;
						}
					    var params = {};
					    var tempIdArr = [];
					    for(var i = 0;i<checkedRowDatas.length;i++){
							var checkedRowdata = checkedRowDatas[i];
							var id = checkedRowdata.id;
							tempIdArr.push(id);
						}
						params["ids"] = tempIdArr.join(",");
							
						if(!window.confirm("选中的记录将一律被重新发送，继续请点击确定！")) return;
						var url=getRootPath()+"/acl/"+operAction+".acl";
						//遮罩
						mini.mask({
							el : document.body,
							cls : 'mini-mask-loading',
							html : '数据操作中 请稍等...'
						});
						jQuery.ajax({
							url : url,
							type : 'POST',
							timeout : 30 * 1000,
							data : params,
							dataType : 'json',
							error : function(res, errInfo, e) {
								mini.unmask(document.body);
							},
							success : function(resultJson) {
								var returnState = resultJson.returnType;
								switch (returnState) {
								case "SUCCESS": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert(operTitle + " 成功！");
									break;
								}
								case "FAILURE": {
									mini.unmask(document.body);
									mini.alert(operTitle + " 失败！");
									break;
								}
								}
							}
						});
					}
				  },'-',
				  {
						html : ' 立即发送',
						plain : true,
						iconCls : 'icon-addfolder',
						handler : function(miniTable, buttonText) {
							var checkedRowDatas = miniTable.getSelecteds();
							var operTitle = "立即发送";
							var operAction = "sendSmsNow";
							
							if(0 == checkedRowDatas.length){
								alert("请选择要"+operTitle+"的记录！！");
								return;
							}
						    var params = {};
						    var tempIdArr = [];
						    for(var i = 0;i<checkedRowDatas.length;i++){
								var checkedRowdata = checkedRowDatas[i];
								if("待发送"!=checkedRowdata.sendflagname){
									alert("电话号码："+checkedRowdata.phonenumber+"不是待发送状态,不能再发送!!!" );
									return false;
								}
								var id = checkedRowdata.id;
								tempIdArr.push(id);
							}
							params["ids"] = tempIdArr.join(",");
								
							if(!window.confirm("选中的记录将一律被重新发送，继续请点击确定！")) return;
							var url=getRootPath()+"/acl/"+operAction+".acl";
							//遮罩
							mini.mask({
								el : document.body,
								cls : 'mini-mask-loading',
								html : '数据操作中 请稍等...'
							});
							jQuery.ajax({
								url : url,
								type : 'POST',
								timeout : 30 * 1000,
								data : params,
								dataType : 'json',
								error : function(res, errInfo, e) {
									mini.unmask(document.body);
								},
								success : function(resultJson) {
									var returnState = resultJson.returnType;
									switch (returnState) {
									case "SUCCESS": {
										miniTable.reload();
										mini.unmask(document.body);
										mini.alert(operTitle + " 成功！");
										break;
									}
									case "FAILURE": {
										mini.unmask(document.body);
										mini.alert(operTitle + " 失败！");
										break;
									}
									}
								}
							});
						}
					  }
				  
				],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible : false},
				   	{field : 'phonenumber',header : '电话号码',
				   		formEditorConfig : 
				   		{
				   			colspan: 3,
							width: 505,
							vtype: 'Mobile',
				   			required: true,
				   			
				   			onvalidation :function(e){
						   		if("" == e.value)return;
						   	    if (e.isValid) {
						   	        var pattern =  /\d{11}/;
						   	        if (pattern.test(e.value) == false) {
						   	            e.errorText = "手机号码必须11位数字";
						   	            e.isValid = false;
						   	        }else{
						   	        	e.sender.setIsValid(true);
						   	        }
						   	    }
				   	        }
				   		}
				   	},
				   	{field : 'noticetime',header : '预计发送时间',dateFormat : "yyyy-MM-dd HH:mm:ss",queryConfig : {},
				   		formEditorConfig : {
				   			labelWidth:90,
				   			newLine:true,
				   			type: 'date',
							required: true,
							vtype: 'date',
							allowInput: false,
							defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
							format : 'yyyy-MM-dd HH:mm:ss'
				   		}
				   	},
				   	{field: 'smstypename', header: '发送时机', formEditorConfig:{
				   		fieldVisible: false,
						fillFromFieldName : 'emailtype',
						fillProperty : 'name'
					}},
				   	{field : 'smstype',header : '发送时机',visible: false,queryConfig : {},
				   		formEditorConfig : {
				   			type : 'combobox',
							textField: 'name',
							required: true,
							valueField: 'value',
							fieldVisible: true,
							data :[{name : '延时',value:'DELAYED'},{name : '立即',value:'IMMEDIATELY'}]
				   		}
				   	},
				   	{field: 'smscontent', header: '短信内容', 
				   		formEditorConfig:{
				   			type:'textarea',
				   			colspan: 3,
							width: 505,
							height:200,
				   			required: true,
				   			newLine:true
					    }
				   	},
                    {field: 'sendtime', header: '实际发送时间',dateFormat : "yyyy-MM-dd HH:mm:ss", formEditorConfig:{fieldVisible: false}},
                    {field: 'sendflagname', header: '发送状态', formEditorConfig:{fieldVisible: false}},
                    {field: 'sendresult', header: '反馈消息', formEditorConfig:{fieldVisible: false}},
                    {field: 'creator', header: '创建人', formEditorConfig:{fieldVisible: false}},
                    {field: 'createdate', header: '创建时间',dateFormat : "yyyy-MM-dd HH:mm:ss", formEditorConfig:{fieldVisible: false}}
				]
			});
		});
	});
</script>
</head>
<body></body>
</html>