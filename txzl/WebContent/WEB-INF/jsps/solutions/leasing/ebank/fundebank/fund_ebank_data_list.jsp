<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>网银接口信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript" defer>
	/**
	var attachmentParams = "";
	//是否废弃
	var localEnabled = [ {
		id : '',
		text : ''
	}, {
		id : '否',
		text : '否'
	}, {
		id : '是',
		text : '是'
	} ];
	var localMayMoney = [
			{
				id : '',
				text : '全部'
			},
			{
				id : ' and e.fact_money-nvl(e.no_with_money,0)-nvl(cf.fundmoney,0)- nvl(cr.rent,0)-nvl(pro.process_amount,0)>0',
				text : '大于零'
			},
			{
				id : ' and  e.fact_money-nvl(e.no_with_money,0)-nvl(cf.fundmoney,0)- nvl(cr.rent,0)-nvl(pro.process_amount,0)=0',
				text : '等于零'
			} ];*/
	var toolsArray = [];
	<permission:action code="ports">
	toolsArray.push('edit', '-', 'importExcel','-','exportExcel', '-', {
		html : '模板下载',
		plain : true,
		iconCls : 'icon-addfolder',
		handler : function(miniTable, buttonText) {
			var fileTeplate = new fileTemplateConfig({
				templateno : 'F-201411002',
				tableid : miniTable.config.id,
				modelname : miniTable.config.title,
				returntype : 'file',
				parames : {}
			});
			fileTeplate.createFile();
		}
	}

	, 	
	'-', 'remove'/*{
		html : '废弃',
		plain : true,
		iconCls : 'icon-remove',
		handler : function(miniTable, buttonText) {
			mini.confirm("确认废弃选中数据行么？", "确认操作", function(buttonText) {
				var row = miniTable.getSelected();
				var param = {};
				param.id = row.id;
				param.isdiuse = row.isdiuse;
				//param.nowithmoney = row.nowithmoney;
				if ("ok" == buttonText) {
					if (row.isdiuse != "0")//判断重复点击废弃按钮
					{
						/* 处理非废弃的   ajax start  *//*
						$.ajax({
							url : window.globalWebRoot
									+ '/acl/delEbankInfo.acl',
							method : 'post',
							data : param,
							failure : function(res) {

							},
							success : function(res) {
								if (res == "yes")
									mini.alert("操作成功!");
								else if (res == "process")
									mini.alert("不允许废弃,网银正在使用中...");
								else
									mini.alert("操作失败!");
							}
						});
						/*ajax end *//*
					} else {
						alert("该数据已是废弃状态不能重复点击废弃,请操作其他数据!");
						return false;
					}
					miniTable.load();//操作完刷新
					miniTable.deselectAll(false);
				}
			});
		}
	}, '-'
	/*
	, {
		html : '工行导入',
		plain : true,
		iconCls : 'icon-importExcel',
		handler : function(miniTable, buttonText) {
			var config = {};
			config.importExcelUrl = window.globalWebRoot
					+ '/acl/importExcelIDBC.action';
			config.title = '工行网银';
			config.id = 'table_id_index';
			seajs.use("js/apcomponent/aptablebase/aptablebase.js", function(
					ApTableBase) {
				ApTableBase.importExcel(miniTable, [], config);
			});
		}
	}, '-'*/
	
	);
	</permission:action>
	/*
	<permission:action code="ebankback">
	toolsArray.push(
			{
		    	html:'网银退还',
				plain:false,
				iconCls:'icon-addfolder',
				handler:function(miniTable, buttonText){
					var RowData = miniTable.getSelected();
					if(RowData){	
						attachmentParams =attachmentParams+"&ebank_id="+RowData.id;
						 startProcess("网银退还流程-1",attachmentParams);  
					}else{
						mini.alert("请选中要操作的数据！");
					}
			}
		    }
			);
	</permission:action>
	 */
	function importMiniTableCallBack(message, tableid) {
		var info = eval("(" + message + ")");
		mini.alert(info.message);
		mini.get("id_minitableimport").hide();
		mini.unmask(document.body);
		mini.get(tableid).reload();
	}
	jQuery(function() {
		seajs
				.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										//导入Excel配置
									    //importOrExPortParam:{'vatInvoiceContract':'vatInvoiceContract'},//万瑞用来校验发票登记自定义的方法
		                                importTargetClass:'com.tenwa.leasing.entity.base.FundEbankData',
										importOrExPortCallBack : 'checkFundEbankDataTX',//回调方法用来校验对象属性
										importDataIndex : '2',
										importHeaderIndex : '1',
										id : 'table_id_index',
										width : globalClientWidth,
										height : globalClientHeight,
										title : '网银信息',
										multiSelect : true,
										editFormPopupWindowWidth : 700,
										editFormPopupWindowHeight : 450,
										hiddenQueryArea : false,
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										xmlFileName : 'eleasing/jsp/ebank/fundebank/fund_ebank_data_import.xml',
										tools : toolsArray,
										remoteOper : true,
										lazyLoad : false,
										pageSize:700,
										showPager : false,
										afterShowWindowCallBack : function(miniTable, miniForm,
												operFlag) {
											if (operFlag == "edit") {
												var custname = mini.getbyName("custidname")
														.getValue();
												mini.getbyName("custid").setText(custname);
											}
											/* if (operFlag == "add") {
												dept = "${sessionScope['loginUserDeptObj'].id}";
												deptname = "${sessionScope['loginUserDeptObj'].name}";
												creator = "${sessionScope['loginUser'].id}";
												creatorname = "${sessionScope['loginUser'].realname}";

												mini.getbyName("creator").setText(creatorname);

												mini.getbyName("registdept").setText(deptname);
											} */
										},
										loadMode : 'ajax',
										entityClassName : 'com.tenwa.leasing.entity.base.FundEbankData',
										//entityBeanCallBackClassName : 'com.tenwa.leasing.serviceImpl.ebank.FundEbankBeanCallBack',
										validateForm : function(miniTable,
												miniForm) {
											return true;
										},
										/*
										params : {
											invalid : '否'
										},*/
										columns : [
												{
													type : 'indexcolumn'
												},//有序列
												{
													type : 'checkcolumn'
												},//单选行
												
												{
													field : 'id',
													header : 'id',
													visible : false
												},
												{field:'',header:'操作',width:200,
						  					  		   formEditorConfig:{
						                                   fieldVisible:false},
												               renderer:function(e){
													                   var id = e.record.id;
												                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 /附件个数："+e.record.attachmentnum+"</a>";
												               }},
												 {field : 'attachmentnum',
												    header : '附件个数',
													visible : false,
													queryConfig : {},
													formEditorConfig : {
													required : true,
													fieldVisible : false
																	}
																},
												{
													/* field : 'transactiondate',到账时间  */
													field : 'factdate',
													header : '交易日期时间',
													dateFormat : "yyyy-MM-dd HH:mm:ss",
													headerAlign : 'center',
													width:150,
													queryConfig : {
														type : 'date',
														range : true
													},
													formEditorConfig : {
														type : 'date',
														readOnly : true,
														newLine : false,
														format : 'yyyy-MM-dd HH:mm:ss'
													}
												},
												{
													field : 'payoutdate',
													header : '起息日期',
													dateFormat : "yyyy-MM-dd",
													headerAlign : 'center',
													queryConfig : {
														type : 'date',
														range : true
													},
													formEditorConfig : {
														type : 'date',
														readOnly : true,
														newLine : false,
														format : 'yyyy-MM-dd'
													}
												},
												{
												/* 	field : 'accountname',本方账户 */
													field : 'ownaccount',
													header : '账户名称',
													allowSort : true,
													width:150,
													queryConfig : {
														width:212
													},
													formEditorConfig : {
														readOnly : true,
														newLine : true,
														labelWidth : 100
													}
												},
												{
													/* field : 'banktype',本方银行  */
													field : 'ownbank',
													header : '银行类型',
													allowSort : true,
													queryConfig : {
														newLine : true,
														width:212
													},
													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
											/* 		field : 'account',本方账号 */
													field : 'ownaccnumber',
													header : '账号',
													width:150,
													queryConfig : {width:212},
													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'borrow',
													header : '借',
													//summary : true,
													dataType : "currency",
													allowSort : true,

													formEditorConfig : {

														labelWidth : 100,
														readOnly : true
													}
												},
												{
													field : 'credit',
													header : '贷',
													//summary : true,
													dataType : "currency",
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'accountbalance',
													header : '账户余额',
												//	summary : true,
													dataType : "currency",
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
													field : 'digest',
													header : '摘要',
													width:150,
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'purpose',
													header : '用途',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
													/* field : 'otheraccount', 付款人 */
													field : 'clientname',
													header : '对方户名',
													allowSort : true,
													width:150,
													queryConfig : {width:212},
													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'clientaccnumber',
													header : '对方账号',
													allowSort : true,
													width:150,
													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
													/* field : 'otherbank',对方账户*/
													field : 'otherbank',
													header : '对方开户行',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'transactiontype',
													header : '交易类型',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
													field : 'settlementtype',
													header : '结算类型',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},
												{
													field : 'currency',
													header : '币种',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												},
												{
													field : 'custidname',
													header : '关联客户号',
													visible : true,

													formEditorConfig : {
														
														fieldVisible : false
													}
												},
												{
													field : 'custid',
													header : '关联客户号',
													allowSort : true,
													visible : false,
													renderer : function(e) {
														return e.record.custname;
													},
													formEditorConfig : {
														newLine : true,
														type : 'queryinput',
														textField : 'name',
														valueField : 'value',
														allowInput : false,
														fieldVisible : true,
													
														params : {
															xmlFileName : 'common/custInfo.xml'
														}
													}
												}, {
													field : 'sn',
													header : '银行流水号',
													allowSort : true,
													width:160,
													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												}, {
													field : 'detailedsource',
													header : '明细来源',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												}, {
													field : 'maintenanceuser',
													header : '维护用户',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														readOnly : true
													}
												}, {
													/* field : 'amount', */
													field :'factmoney',
													header : '金额',
													summary : true,
													dataType : "currency",
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												}, {
													field : 'note',
													header : '备注',
													allowSort : true,

													formEditorConfig : {
														
														readOnly : true
													}
												},

												{
													field : 'parsubaccounts',
													header : '母/子公司账号',
													allowSort : true,

													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														readOnly : true
													}
												},

												{
													field : 'parsubaccname',
													header : '母/子公司账户名',
													visible : true,

													formEditorConfig : {
														newLine : false,
														readOnly : true,
														fieldVisible : true
													}
												},
												{
													field : 'uploaddate',
													header : '上传日期',
													dateFormat:'yyyy-MM-dd HH:mm:ss',
													width:30,
													headerAlign : 'center',
													visible : false,
													allowSort:'true',
													formEditorConfig : {
														type : 'date',
														width:200,
														readonly:true,
														/* value:mini.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),  */
														/* defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"),  */
														format:'yyyy-MM-dd HH:mm:ss',
														required : true,
														fieldVisible : false
													}
												}					
												
												
												]
									});

						});
	});
	
function showanduploadfile(id,type){
		var urlFlag = getRootPath()+"/leasing/ebank/fundebank/fundebank_file_list.bi?id="+id+"&type="+type;
		mini.open({
	        url: urlFlag,
	        title: "网银导入资料", width: 800, height: 455,
	        onload: function () {
	         mini.get("table_id_index").reload();
	   		 mini.unmask(document.body);
	        },
	        ondestroy: function (action) {
	        	if("savesuccess" == action){
	        		window.location.reload();
	        	}
	        }
	    });
		

  	}
</script>
</head>
<body>
</body>
</html>