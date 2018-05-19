<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link
	href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css"
	rel="stylesheet" type="text/css">
<title>合同档案归档</title>
<%@include file="/base/mini.jsp"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAttachmentFileUpload.js"></script>
<script type="text/javascript">
var a={
		html:'归档',
		plain : true,
		iconCls : 'icon-edit',
		handler : function(miniTable, buttonText) {
			//归档前判断必填项
			var rows = miniTable.getSelecteds();
			if(rows.length==0){
				mini.alert("请选择需要归档的文件");
				return false;
			}
			var str="";
			var params={};
			for(var i=0;i<rows.length;i++){
				if(rows[i].filingstatus=='已归档'){
					mini.alert("已归档文件不允许再归档");
					return false;
				}
				if(rows[i].fillingtype==''){
					mini.alert("归档类别未填写不允许归档");
					return false;
				}
				if(rows[i].fillingnumber==''){
					mini.alert("档案编号未填写不允许归档");
					return false;
				}
				
				if(rows[i].filingname==''){
					mini.alert("档案名称未填写不允许归档");
					return false;
				}
				if(rows[i].comeindate==''){
					mini.alert("生效日期未填写不允许归档");
					return false;
				}
				if(rows[i].filingdate==''){
					mini.alert("归档日期未填写不允许归档");
					return false;
				}
				if(i==rows.length-1){
					str+=rows[i].id;
				}else{
					str+=rows[i].id+",";
				}
			}
		    params["arryid"] = str;
			//params['params']=JSON.stringify(rows); 
			/* for(var i=0;i<rows.length;i++){
				params[i]=rows.[i].id;
			}  */
	var url=getRootPath()+"/acl/saveContractArchives.acl";
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
				mini.alert("归档成功");
				break;
			}
			case "FAILURE": {
				miniTable.reload();
				mini.unmask(document.body);
				//mini.alert(operType+operTitle + " 失败！");
				mini.alert("归档失败！"+resultJson.content);
				break;
			}
			}
		}
	});
	}
	};
	var tool = [];
	/* tools.push('add'); */
	
	<permission:action code="archives_add">
	tool.push('add');
	</permission:action>
	<permission:action code="archives_edit">
	tool.length > 0 ? tool.push('-', 'edit') : tool.push('edit');
	</permission:action>
	<permission:action code="archives_copy">
	tool.length>0?tool.push('-','copy'):tool.push('copy');
	</permission:action>
	<permission:action code="archives_remove">
	tool.length > 0 ? tool.push('-', "remove") : tool.push('remove');
	</permission:action>
	<permission:action code="archives_filing">
	tool.length > 0 ? tool.push('-', a) : tool.push(a);
	</permission:action>
	//如果是财务部只能归档保险柜档案,资产管理部归档保险柜外的档案
	var extendsql="";
	if("${sessionScope['loginUserDeptObj'].id}"=='402880ad58f1f5ad0158f213f947003a'){
	extendsql += "AND c.container_number like '保险柜%' "}
	else if("${sessionScope['loginUserDeptObj'].id}"=='402880ad58f1f5ad0158f21641ce003c'){
		extendsql += "AND (c.container_number not like '保险柜%'   or c.container_number is null)"
	}else {
		extendsql += "AND c.container_number ='123' "
	}
	
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_id3',
										width : globalClientWidth,
										height : globalClientHeight - 100,
										//title:'合同归档',
										iconCls : 'icon-node',
										multiSelect : true,
										hiddenQueryArea : false,//是否将查询区域折叠起来
										queryButtonColSpan : 2,
										queryButtonNewLine : false,
										showPager : true,
										remoteOper : true,
										entityClassName : "com.tenwa.leasing.entity.contract.ContractFiling",
										pageSize : 20,
										allowCellEdit : true,
										allowCellSelect : true,
										params : {
											extendsql:extendsql,
											//user_id :"${sessionScope.loginUser.id}",
											conid : "${param.id}"
										},
										//已经归档的合同
										xmlFileName : '/docarchives/contract_archives_detail.xml',
										operValidate : function(miniTable,
												rowData, oper) {
											var rowss = miniTable
													.getSelecteds();
											if ("edit" == oper) {
												for (var i = 0; i < rowss.length; i++) {
													if (rowss[i].filingstatus == '已归档') {
														mini
																.alert("已归档文件不允许修改");
														return false;
													}
												}
											}
											return true;
										},
										validateForm : function(miniTable,
												miniForm, editFormItemOperFlag,
												addIndex) {
											var rows = miniTable.getSelecteds();
											if (editFormItemOperFlag == "remove") {
												var str = "";
												$
														.ajax({
															url : getRootPath()
																	+ "/acl/checkArchives.acl",
															type : "post",
															cache : false,
															data : {
																"id" : rows[0].id
															},
															async : false,
															dataType : 'json',
															success : function(
																	resultJson) {
																var returnState = resultJson.returnType;
																switch (returnState) {
																case "SUCCESS": {
																	miniTable
																			.reload();
																	mini
																			.unmask(document.body);
																	mini
																			.alert("删除成功");
																	break;
																}
																case "FAILURE": {
																	miniTable
																			.reload();
																	mini
																			.unmask(document.body);
																	//mini.alert(operType+operTitle + " 失败！");
																	mini
																			.alert("删除失败！"
																					+ resultJson.content);
																	break;
																}
																}
															}

														});
											}
											if (editFormItemOperFlag == "copy"
													|| editFormItemOperFlag == "edit") {
												for (var i = 0; i < rows.length; i++) {
													if (rows[i].filingstatus == '已归档') {
														mini
																.alert("已归档文件不允许修改、复制");
														return false;
													}
												}
											}
											return true;
										},
										afterShowWindowCallBack : function(
												miniTable, miniForm, operFlag) {
											if (operFlag == "edit") {
												//生效日期添加默认值
												var comeindate = mini
														.getbyName("comeindate")
														.getValue();
												mini
														.getbyName("comeindate")
														.setValue(
																comeindate == "" ? mini
																		.formatDate(
																				new Date(),
																				"yyyy-MM-dd")
																		: comeindate);
												//归档日期添加默认值
												var filingdate = mini
														.getbyName("filingdate")
														.getValue();
												mini
														.getbyName("filingdate")
														.setValue(
																filingdate == "" ? mini
																		.formatDate(
																				new Date(),
																				"yyyy-MM-dd")
																		: filingdate);
												//备注默认值添加
												var notea = mini.getbyName(
														"note").getValue();
												mini
														.getbyName("note")
														.setValue(
																notea == "" ? "原件"
																		: notea);
												//柜号添加默认值
												var containernumbera = mini
														.getbyName(
																"containernumber")
														.getValue();
												mini
														.getbyName(
																"containernumber")
														.setValue(
																containernumbera == "" ? "档案柜"
																		: containernumbera);
												//归档人添加默认值
												var fillingusername = mini
														.getbyName(
																"fillingusername")
														.getValue();
												var fillinguser = mini
														.getbyName(
																"fillinguser")
														.getValue();
												mini
														.getbyName(
																"fillinguser")
														.setValue(
																fillingusername == "" ? '${sessionScope.loginUser.id}'
																		: fillinguser);
												mini
														.getbyName(
																"fillingusername")
														.setValue(
																fillingusername == "" ? '${sessionScope.loginUser.realname}'
																		: fillingusername);
											}
										},
										tools : tool,
								/* 	tools: [ 'add','-','edit','-','copy','-','remove','-', ],  */
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : 'id',
													visible : false
												},
												/* 				    {field:'',header:'操作',
												 formEditorConfig:{
												 fieldVisible:false},
												 renderer:function(e){
												 var id = e.record.id;
												 var custid = "${param.id}";
												 return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"" + custid + "\",\"one\") '>查看 /上传附件</a>";
												 }},
												 {
												 field : 'id',
												 header : 'id',
												 visible : false
												 }, */
												{
													field : 'filingsn',
													header : '唯一识别号',
													visible : false
												},
												{
													field : 'fillingtypename',
													header : '类别',
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														fieldVisible : false
													}
												},
												{
													field : 'fillingtype',
													header : '类别',
													visible : false,
													formEditorConfig : {
														type : 'combobox',
														newLine : true,
														required : true,
														textField : 'name',
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'fillingtype',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'fillingnumber',
													header : '档案编号',
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												/* {field:'filingsn',header:'文件名称id',visible:false,formEditorConfig:{
												    fieldVisible:false}}, */
												{
													field : 'filingname',
													header : '文件名称',
													formEditorConfig : {
														required : true,
														newLine : true,
														colspan : 3,
														width : "100%",
														fieldVisible : true
													}
												},
												{
													field : '',
													header : '文件下载',
													align : "center",
													formEditorConfig : {
														fieldVisible : false
													},
													renderer : function(e) {
														var fileid = e.record.fileid;
														var flagtable = e.record.flagtable;
														var filename = e.record.downfilename;
														if (flagtable == 'basefile') {
															return "<a href='javascript:void(0);' onclick='downloadFile(\""
																	+ fileid
																	+ "\")'>"
																	+ filename
																	+ "</a>";
														} else if (flagtable == 'attachmentinfodetail') {
															return "<a href='javascript:void(0);' onclick='downloadUploadFile(\""
																	+ fileid
																	+ "\")'>"
																	+ filename
																	+ "</a>";
														} else {
															return "";
														}
													}
												},
												{
													field : 'comeindate',
													header : '生效日期',
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														required : true,
														defaultValue : mini
																.formatDate(
																		new Date(),
																		"yyyy-MM-dd"),
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',

													}
												},
												{
													field : 'filingdate',
													header : '归档日期',
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														defaultValue : mini
																.formatDate(
																		new Date(),
																		"yyyy-MM-dd"),
														type : 'date',
														required : true,
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',

													}
												},

												{
													field : 'page',
													header : '页数',
													formEditorConfig : {
														newLine : true,
														fieldVisible : true
													}
												},
												{
													field : 'note',
													header : '备注',
													formEditorConfig : {
														width : "100%",
														defaultValue : '原件',
														fieldVisible : true
													}
												},
												{
													field : 'fillingusername',
													header : '归档人',
													formEditorConfig : {
														readOnly : true,
														newLine : true,
														fieldVisible : true,
														defaultValue : '${sessionScope.loginUser.realname}'
													}
												},
												{
													field : 'fillinguser',
													header : '归档人',
													visible : false,
													formEditorConfig : {
														defaultValue : '${sessionScope.loginUser.id}',
														fieldVisible : false
													}
												},
												{
													field : 'filingstatus',
													header : '归档状态',
													formEditorConfig : {
														fieldVisible : false,
														type : 'combobox',//表单域类型
														valueField : 'value',
														textField : 'name',
														allowInput : false,
														showNullItem : true,
														nullItemText : '',
														data : [ {
															name : '已归档',
															value : '已归档'
														}, {
															name : '未归档',
															value : '未归档'
														} ]
													/* 	text:'已归档',
														value:"已归档" */
													}
												},

												{
													field : 'contractid',
													header : '合同编号',
													visible : false,
													formEditorConfig : {
														defaultValue : "${param.id}",
														fieldVisible : false
													}
												}, {
													field : 'containernumber',
													header : '柜号',
													formEditorConfig : {
														defaultValue : '1',
														fieldVisible : true
													}
												} ]
									});
						});
	});
	/*上传资料内容*/

	function showanduploadfile(id, custid, type) {
		var urlFlag = getRootPath()
				+ "/leasing/cust_info/cust_contact/cust_contact_file_list.bi?id="
				+ id + "&cust_id=" + custid + "&type=" + type;
		mini.open({
			url : urlFlag,
			title : "上传档案资料",
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
</head>
<body>
	<div id="form1" class="mini-panel" style="width: 100%;">
		<table class="fillTable">
			<tr>
				<td colspan="6" style="border-bottom: 1px solid #99CCFF;"><a
					class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="10%">客户名称:</td>
				<td class="td-content" width="45%"><input class="mini-textbox"
					name="custname" width="80%" readonly allowInput="false"></input></td>
				<td class="td-content-title" width="10%">合同编号:</td>
				<td class="td-content" width="35%"><input class="mini-textbox"
					name="conid" readonly allowInput="false"></input></td>
			</tr>
			<!-- <tr class="tr-odd ">
				<td class="td-content-title" width="15%">合同起租日:</td>
				<td class="td-content" width="35%">
					<input class="mini-textbox" name="start_date_" 
						allowInput="false"></input></td>
				<td class="td-content-title" width="15%">柜号</td>
				<td class="td-content" width="35%">
					<input class="mini-textbox" id="containerNumber" 
						name="containernumber" required="true"/>
					 <font class="required-tag"> * </font>
					 </td>
					 
			</tr> -->
			<%-- <tr class="tr-even">
				<td class="td-content-title" width="15%">归档人:</td>
				<td class="td-content" width="35%">
		         	 <input id="id_realName" name="fillinguser" label="归档人" 
		         	 	class="mini-buttonedit mini-queryinput" 
		         	 	allowInput="false"
		             	required="true"
						text="${empty requestScope['fillingusername'] ? sessionScope.loginUser.realname : requestScope['fillingusername']}"
						value="${empty requestScope['fillinguser'] ? sessionScope.loginUser.id : requestScope['fillinguser']}"
						/>
					 <font class="required-tag">*</font>
					 <input id="rawValueRealname_" name="fillingusername" value="${empty requestScope['fillingusername'] ? sessionScope.loginUser.realname : requestScope['fillingusername']}" 
					 class="mini-textbox" style="display:none"/>
					 </td>
				<td class="td-content-title" width="15%">上次归档时间:</td>
	            <td class="td-content" width="38%"> 
	             	<input id="id_filing_date" name="filing_date" class="mini-datepicker" style="width:200px"  format="yyyy-MM-dd HH:mm:ss" 
	             	label="归档时间"  required="true" allowInput="false" showTime="true"/></td>
			</tr> --%>
		</table>
	</div>
	<div id="table_id3"></div>
</body>
<script type="text/javascript">
	jQuery(function() {
		//初始化数据
		var data = miniui_ext.initPageData(
				"/docarchives/contract_archives_list.xml", "form1",
				"${param.id}");
		miniui_ext.disableFormFields("form1");
	});
	function downloadFile(Id) {
		attachmentDown({
			returnType : 'file',
			url : "${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="
					+ Id
		});
	}
</script>
</html>