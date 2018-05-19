<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if('${param.isShow}' == 'true'){showTools = false;}
		seajs.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_supplier_information',
										renderTo : "id_table_supplier_information",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										queryButtonColSpan : 4,
										queryButtonNewLine : false,
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformation',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										//新增弹出窗口调用该方法返回AJAX
										afterShowWindowCallBack : function(
												miniTable, miniForm, operType) {
											if (operType == 'add') {//如果是新增就获取流水号
												$.ajax({
															url : getRootPath()+ "/acl/getRunningWater.acl",
															type : "post",
															cache : false,
															data : {},
															async : false,
															success : function(date) {
																mini.getbyName("supplierid").setValue(date);
															}
														});
											}
										},
										xmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_information.xml',
										params:{projid:projid},
										showToolbar: showTools,
										tools : [ 'add', '-', 'edit', '-','remove', '-', 'exportExcel' ],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '标识符',
													headerAlign : 'center',
													width : 200,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
											   {field : 'projid',
												header : '项目id号',
												headerAlign : 'center',
												width : 100,
												allowSort : true,
												visible : false,
												formEditorConfig : {
													readOnly : true,
													fieldVisible : false,
													value : projid
												}
											},
												{field:'supplierid',header:'供应商编号',formEditorConfig : {newLine:true,width : 200,labelWidth:100,readOnly:true}},
												{field:'suppliername',header:'供应商名称',formEditorConfig : {width : 200,labelWidth:100}},
												{field:'icregistcode',header:'企业工商注册编码',formEditorConfig : {width : 200,labelWidth:120,newLine:true}},
												//{field:'supplierId',header:'供应商编号',formEditorConfig:{width : 200,labelWidth:100}}, 
												{
								field : 'supplierbcategoriesname',
								header : '供应商大类',
								formEditorConfig : {
									labelWidth : 100,
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									fillFromFieldName : 'supplierbcategories',
									fillProperty : 'name',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'supplierbcategories',
								header : '供应商大类',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									newLine : false,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										pid1 : 'A',
										pid2 : 'B',
										xmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_big.xml'
									},
									cascade : {
										childrenFieldNames : [ 'supplierscategories' ],
									    clearFieldNames : [ 'supplierscategories','nameofgoodsorservices' ] 
									}
								}

							},
							{
								field : 'supplierscategoriesname',
								header : '供应商小类',
								formEditorConfig : {
									fillFromFieldName : 'supplierscategories',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									width : 200,
									fieldVisible : false,
									
									
								}
							},
							{
								field : 'supplierscategories',
								header : '供应商小类',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine :true,
									showNullItem : true,
									nullItemText : "",    //显示空值
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									otherAttributes:'onClick="AreYouOk"',
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_small.xml'
									},
									cascade : {
										parentFieldNames : [ 'supplierbcategories' ],
										childrenFieldNames : [ 'nameofgoodsorservices' ],
									    clearFieldNames : [ 'nameofgoodsorservices' ] 
									}
								}

							},
							{
								field : 'nameofgoodsorservicesname',
								header : '货物或劳务名称',
								formEditorConfig : {
									fillFromFieldName : 'nameofgoodsorservices',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'nameofgoodsorservices',
								header : '货物或劳务名称',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine :false,
									showNullItem : true,
									nullItemText : "",    //显示空值
									entityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									otherAttributes:'onClick="AreYouOk1"',
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_type.xml'
									},
									cascade : {
										parentFieldNames : [ 'supplierscategories' ]
									  /*  clearFieldNames : [ 'nameofgoodsorservices' ]  */
									}
								}
							},
												{field:'isqualifiedsupplier',header:'是否合格供应商',formEditorConfig:{newLine:true,width : 200,labelWidth:100,type : 'combobox',
													textField : 'name',
													valueField : 'value',
													allowInput : false,
													showNullItem : false,
													defaultValue : '是',
													data : [ {
														name : "是",
														value : "是"
													}, {
														name : "否",
														value : "否"
													} ]}}, 
													
												{field:'relevantqualification',header:'相关资质',formEditorConfig : {newLine:false,width : 200,labelWidth:100}},
												{field:'projectexperience',header:'项目经验',formEditorConfig : {colspan : 3,	width : "100%",newLine:true}},
												{field:'contacts',header:'联系人',formEditorConfig :{newLine:true,width : 200,labelWidth:100}},
												{field:'contactway',header:'联系方式',formEditorConfig : {newLine:false,width : 200,labelWidth:100}},
												{field:'remarks',header:'备注',formEditorConfig : {type : 'textarea',colspan : 3,width : "100%",newLine:true}}
												]


									});
						});
	});
	
	function AreYouOk(e){		
	var big=mini.getbyName("supplierbcategories").getValue();
	   if(big==""){
		   setTimeout(function(){mini.alert("请先选择供应商大类！")},100);
	   }
		
	}
	function AreYouOk1(e){	
	var small=mini.getbyName("supplierscategories").getValue();
	var big=mini.getbyName("supplierbcategories").getValue();
	   if(small=="" || big==""){
		   setTimeout(function(){mini.alert("请先选择供应商小类！")},100);
		   
	   }
		
	}
</script>
<div id="id_table_supplier_information"></div>
