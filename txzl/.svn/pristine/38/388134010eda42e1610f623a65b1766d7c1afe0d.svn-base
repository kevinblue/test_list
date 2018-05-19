<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>抵质押登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	·
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_accountrecple_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										title : '抵质押登记',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										entityClassName : 'com.tenwa.leasing.entity.pledge.Pledge',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										queryButtonColSpan : 8,
										queryButtonNewLine:true,
										xmlFileName : '/eleasing/jsp/pledge/pledge_info_list.xml',
										/* validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
											var pledge=miniTable.getSelected();
											var num=Number(pledge.pledgevalue).toFixed(2);
											getMiniEditFormField("table_accountrecple_info_id.pledgevalue").setValue(Number(num));
											return  true;
										}, */
										 beforeShowWindowCallBack:function(miniTable, miniForm, operFlag){
											if("edit"==operFlag){
												/* getMiniEditFormField("contractid").setText(mini.getbyName("connameidname").getValue()); */
												//mini.getbyName("contractid").setValue(mini.getbyName("connameidname").getValue());
												mini.getbyName("projid").setText(mini.getbyName("projectname").getValue());
												mini.getbyName("pledgeownner").setText(mini.getbyName("pledgeownner").getValue());
												//mini.getbyName("contractid").setReadOnly(false);
												/* $($(".mini-buttonedit-button")[1]).hide();*/
												getMiniEditFormField("table_accountrecple_info_id.contractid").disable();
																						}
											return true;
										}, 
										tools : [
												'add',
												'-',
												'edit',
												'-',
												'remove',
												'-',
												'exportExcel',
												'-',
												{
													html : '查看所有设备信息',
													plain : true,//背景透明
													iconCls : 'icon-search',//按钮图标类
													handler : function(
															miniTable,
															buttonText) {
														showanduploadfile("xx",
																"all");
													}
												} ],
										columns : [
												{
													type : 'indexcolumn',
													header : '序号'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
												{
													field : '',
													header : '上传资料',
													formEditorConfig : {
														fieldVisible : false
													},
													renderer : function(e) {
														var id = e.record.id;
														return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
																+ id
																+ "\",\"one\")'>查看所上传资料 </a>";
													}
												},
												{
													field : 'pledgename',
													header : '抵质押物名称',
													headerAlign : 'center',
													visible : true,
													formEditorConfig : {
														newLine : true,
														width : '100%',
														colspan : 3,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'regisnum',
													header : '登记证明编号',
													headerAlign : 'center',
													visible : true,
													queryConfig : {},
													formEditorConfig : {
														newLine : true,
														width : '100%',
														colspan : 3,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'projectname',
													header : '项目名称',
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'projid'
													},
													queryConfig : {
													labelWidth : 100,
													width : 200
												},
												},
												{
													field : 'projid',
													header : '项目名称',
													visible : false,
													queryConfig : {
														labelWidth : 100,
														width : 200
													},
													formEditorConfig : {
														newLine : true,
														type : 'queryinput',
														required : true,
														textField : 'projname',
														valueField : 'projid',
														fieldVisible : true,
														onSelect : function(
																$me,queryInput,rowData) {
															mini.getbyName("connameidname").setValue(rowData.name);
															mini.getbyName("contractid").setValue(rowData.value);
														},
														params : {
															xmlFileName : '/eleasing/jsp/pledge/pledge_contractid.xml'
														}
													}
												},
												{
													field : 'contractid',
													header : '业务合同号',
													headerAlign : 'center',
													visible : false,
													width : 150,
													formEditorConfig : {}

												},
											 {
													field : 'connameidname',
													header : '业务合同号',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														type : 'text',
														labelWidth : 100,
														width : 200,
														required : false,
														readonly : true
													}
												},
 											
												{
													field : 'registypename',
													header : '登记类型',
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'registypeid',
														fillProperty : 'name'
													}
												},
												{
													field : 'registypeid',
													header : '登记类型',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : true,
														otherAttributes:'onvaluechanged="registypechanged"',
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'regis_type',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												},
												{
													field : 'guaranteeid',
													header : '担保合同编号',
													visible : true,
													formEditorConfig : {
														newLine : false,
														type : 'text'
													}
												},
												
												{
													field : 'pledgestatus',
													header : '登记状态',
													headerAlign : 'center',
													visible : false,
													width : 150,
													queryConfig : {},
													formEditorConfig : {
														newLine : false,
														value : 1,
														fieldVisible : false
													}
												},
												{
													field : 'regisunit',
													header : '登记单位',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														newLine : true,
														width : '100%',
														colspan : 3,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'assurorname',
													header : '出质人',
													queryConfig : {},
													visible : true,
													formEditorConfig : {
														fieldVisible : false,
														fillFromFieldName : 'assuror'
													}
												},

												{
													field : 'assuror',
													header : '出质人',
													visible : false,
													formEditorConfig : {
														newLine : true,
														width : 200,
														type : 'queryinput',
														required : false,
														textField : 'name',
														valueField : 'value',
														allowInput : false,
														fieldVisible : true,
														params : {
															//cust_type : 'cust_type.assuror'+'\''+',\''+'cust_type.cust',
															cust_type : 'cust_type.cust',
															cust_type1 : 'cust_type.assuror',
															xmlFileName : '/eleasing/jsp/pledge/pledge_person_list.xml'
														}

													}
												},
											
												{
													field : 'pledgeownner',
													header : '抵质押物所在企业/自然人',
													headerAlign : 'center',
													width : 200,
													visible : true,
													formEditorConfig : {
														type : 'queryinput',
														windowWidth:450,
														textField : 'name',
														width : 200,
														valueField : 'name',
														allowInput : false,
														newLine : false,
														required : false,
														params : {
															//cust_type : 'cust_type.assuror'+'\''+',\''+'cust_type.cust',
															cust_type : 'cust_type.cust',
															cust_type1 : 'cust_type.assuror',
															xmlFileName : '/eleasing/jsp/pledge/pledge_person_list2.xml'
														}

													}
												},	
											
												{
													field : 'assurorunion',
													header : '联合担保人',
													headerAlign : 'center',
													visible : true,
													formEditorConfig : {
														newLine : true,
														required : false,
														width : '100%',
														colspan:3,
														type : 'text'
													}
												},
												{
													field : 'salesrevenue',
													header : '预期年净现金流量(万元)',
													width : 150,
													headerAlign : 'center',
													visible : true,
													dataType:"currency",
													formEditorConfig : {
														vtype : "float",
														labelWidth : 110,
														newLine : true,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'pledgevalue',
													header : '抵质押物现值(元)',
													width : 150,
													headerAlign : 'center',
													visible : true,
													dataType:"currency",
													formEditorConfig : {
														type : 'text',
														vtype : 'float',
														labelWidth : 110,
														newLine : true,
														width : 200,
														required : false
													}
												},
												{
													field : 'pledgedequity',
													header : '股权质押比例%',
													width : 150,
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														vtype : "float",
														labelWidth : 110,
														newLine : false,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'subjectdata',
													header : '净资产',
													dataType:"currency",
													width : 150,
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														fieldVisible : false
													}
												},
												{
													field : 'pledgenowvalue',
													header : '股权质押物现值',
													width : 150,
													dataType:"currency",
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														labelWidth : 110,
														newLine : true,
														readOnly:true,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'assurortotalval',
													header : '被担保债权总额/主合同金额(元)',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														newLine : false,
														vtype : "float",
														type : 'text',
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'xsze',
													header : '销售总额',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														newLine : true,
														readOnly : true,
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'dfxz',
													header : '应收账款质押现值(万元)',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														readOnly : true,
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'pledgevaluenow',
													header : '抵质押物原值',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														newLine : true,
														vtype : "float",
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'purchasingtime',
													header : '采购时间',
													headerAlign : 'center',
													visible : true,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, 
												{
													field : 'deprecialife',
													header : '折旧年限(年)',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														newLine : true,
														vtype : "float",
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'salvagerate',
													header : '残值率%',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														newLine : false,
														vtype : "float",
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'currentyear',
													header : '当前年度',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, 
												{
													field : 'dzywjz',
													header : '抵质押物净值',
													headerAlign : 'center',
													visible : true,
													width : 200,
													dataType:"currency",
													formEditorConfig : {
														readOnly : true,
														newLine : false,
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'dzywjiazhi',
													header : '抵质押物价值',
													headerAlign : 'center',
													visible : true,
													dataType:"currency",
													width : 200,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false,
														newLine : true,
														labelWidth : 110,
														width : '100%'
													}
												},
												{
													field : 'allrate',
													header : '综合抵质押率%',
													headerAlign : 'center',
													visible : true,
													dataType:"currency",
													width : 200,
													formEditorConfig : {
														readOnly : true,
														newLine : true,
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												{
													field : 'conyearrate',
													header : '合同当前执行利率%',
													headerAlign : 'center',
													visible : true,
													dataType:"currency",
													width : 200,
													formEditorConfig : {
														readOnly : true,
														newLine : false,
														labelWidth : 110,
														width : '100%',
														required : false
													}
												},
												
												{
													field : 'descriptionone',
													header : '抵质押物描述',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														//	textField: 'text',
														required : false,
														//    valueField: 'id',                           
														type : 'textarea',
														width : '100%',
														colspan : 3
													}
												},
												{
													field : 'debtstart',
													header : '债务履行期起始日',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true,
														otherAttributes:'onvaluechanged="judgedate"'
													}
												},
												{
													field : 'debtend',
													header : '债务履行期终止日',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true
													}
												},
												{
													field : 'pledgestart',
													header : '抵质押登记起始日',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false,
														otherAttributes:'onvaluechanged="copydate"'
													}
												},
												{
													field : 'pledgeend',
													header : '抵质押登记到期日',
													headerAlign : 'center',
													visible : true,
													width : 150,
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 110,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												},
												{
													field : 'registime',
													header : '登记时间',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														labelWidth : 110,
														defaultValue : mini
																.formatDate(
																		new Date(),
																		"yyyy-MM-dd"),
														width : '100%',
														format : 'yyyy-MM-dd',
														required : false
													}
												}, {
													field : 'preparer',
													header : '填表人',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														defaultValue :'${sessionScope.loginUser.realname}',
														newLine : false,
														type : 'text',
														labelWidth : 110,
														width : '100%',
														required : false,
														readonly : true
													}
												} ]
									});
						});
	});
	
	function registypechanged(e){ 
	var registypeid=	mini.getbyName("registypeid").getValue();
	//动产抵押，财报数据不抓去，金评模型数据不抓取，抵质押原值到当前年度必填，当前年度取当前时间,交通运输工具算动产抵押
	if(registypeid=="regis_type.06"||registypeid=="regis_type.04"){
		mini.getbyName("pledgevaluenow").setRequired(true); 
		mini.getbyName("purchasingtime").setRequired(true); 
		mini.getbyName("deprecialife").setRequired(true); 
		mini.getbyName("salvagerate").setRequired(true); 
		mini.getbyName("currentyear").setRequired(true);
		mini.getbyName("currentyear").setText(mini.formatDate(new Date(),"yyyy-MM-dd")); 
		mini.getbyName("currentyear").setValue(mini.formatDate(new Date(),"yyyy-MM-dd")); 
	}else{
		mini.getbyName("pledgevaluenow").setRequired(false); 
		mini.getbyName("purchasingtime").setRequired(false); 
		mini.getbyName("deprecialife").setRequired(false); 
		mini.getbyName("salvagerate").setRequired(false); 
		mini.getbyName("currentyear").setRequired(false);
		mini.getbyName("currentyear").setText(""); 
		mini.getbyName("currentyear").setValue(""); 
	}
	//股权质押，质押比例必填
	if(registypeid=="regis_type.07"){	
		mini.getbyName("pledgedequity").setRequired(true); 
	}else{
		mini.getbyName("pledgedequity").setRequired(false);
	}
	//当选择建筑、建设抵押时，抵质押物数额/价值，改为抵质押物现值，现值、原值改为必填
	if(registypeid=="regis_type.02"||registypeid=="regis_type.01"){	
		mini.getbyName("pledgevalue").setRequired(true); 
		mini.getbyName("pledgevaluenow").setRequired(true); 
	}else{
		mini.getbyName("pledgevalue").setRequired(false); 
		mini.getbyName("pledgevaluenow").setRequired(false); 
	}
	}
	function judgedate(e){ 
		var start=mini.getbyName("debtstart").getValue();
		mini.getbyName("debtend").setMinDate(start);  			
	}
	function copydate(){
		var dizhiStart=mini.getbyName("pledgestart").getValue();
		mini.getbyName("debtstart").setValue(dizhiStart);
	}
	function showanduploadfile(id, type) {
		var urlFlag = getRootPath()
				+ "/workflow/forms/law_mng/pledge/pledge_file_list.bi?id=" + id
				+ "&type=" + type;
		mini.open({
			url : urlFlag,
			title : "设备信息",
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
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
</body>
</html>
