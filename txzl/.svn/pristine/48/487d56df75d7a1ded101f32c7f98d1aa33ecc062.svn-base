<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>银承台帐</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>

<script type="text/javascript">

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
										id : 'table_silver_account_info_id',
										renderTo : "id_table_render_table1",
										width : globalClientWidth,
										height : globalClientHeight,
										iconCls : 'icon-node',
										hiddenQueryArea : false,
										multiSelect : true,
										importTargetClass:'com.tenwa.leasing.entity.finacial.SilverAccount',
										importDataIndex : '2',
										importHeaderIndex : '1',
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 450,
										title : '银承台帐',
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										queryButtonColSpan : 6,
										queryButtonNewLine : true,
										entityClassName : 'com.tenwa.leasing.entity.finacial.SilverAccount',
										frozenStartColumn : 0,
										frozenEndColumn : 2,
										xmlFileName : '/eleasing/jsp/finacial/silver_account/silver_account_list.xml',
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'exportExcel','-', 'importExcel' ],
										columns : [
												{
													type : 'indexcolumn',width:40
												},
												{
													type : 'checkcolumn',width:40
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
													readOnly:true,
														required : true,
													fieldVisible : false
																	}
																},
												{
													field : 'drafitendorser',
													header : '付款人（上手背书人）',
													visible : true,
													width:120,
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'ticket',
													header : '票号',
													visible : true,
													queryConfig : {width:200},
													formEditorConfig : {
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'ticketamount',
													header : '票据金额',
													visible : true,
													dataType:'currency',
													formEditorConfig : {
														required : true,
														onkeyup:'adjustInterest',
														vtype : 'float',
														newLine:true,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'outdate',
													header : '出票日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true
													},
													queryConfig : {
														type : 'date',
														range : true,
														format : 'yyyy-MM-dd',
														newLine : false
													}
												},
												{
													field : 'duedate',
													header : '到期日期',
													headerAlign : 'center',
													visible : true,
													width : 100,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : true,
														type : 'date',
														otherAttributes:'onvaluechanged="adjustInterest"',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true
													},
													queryConfig : {
														type : 'date',
														range : true,
														format : 'yyyy-MM-dd',
														newLine : false
													}
												},
											
												{
													field : 'drawerbank',
													header : '出票银行',
													visible : true,
													
													formEditorConfig : {
														required : true,
														newLine:false,
														fieldVisible : true
													}
												},
												{
													field : 'draweraccount',
													header : '出票人账号',
													visible : true,
													formEditorConfig : {
														required : true,
														newLine:true,
														fieldVisible : true
													},
												queryConfig : {
													newLine:true,
													width:200
												}
												},
												{
													field : 'drawer',
													header : '出票人',
													visible : true,
													formEditorConfig : {
														required : true,
														fieldVisible : true
													},
													queryConfig : {
														width:210
													}
												},
												{
													field : 'endpaydisname',
													header : '背书转让、收款、贴现',
													visible : true,
													width:140,
													formEditorConfig : {
														fieldVisible : false,
														entityClassName:'com.tenwa.business.entity.DictionaryData',//对应实体类
														fillProperty:'name',//显示endpaydis的name
														fillFromFieldName : 'endpaydis',//关联的列
														entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段
													}
												},
												 {         
													field : 'endpaydis',
													header : '背书转让、收款、贴现',
													visible : false,
													formEditorConfig : {
														newLine : true,
														type : 'combobox',
														textField : 'name',
														required : true,
														valueField : 'value',
														fieldVisible : true,
														params : {
															pid : 'endpaydis',
															xmlFileName : '/combos/comboDict.xml'
														}
													}
												}, 
												{
													field : 'endpaydisdate',
													header : '背书转让、收款、贴现日期',
													headerAlign : 'center',
													visible : true,
													width : 150,
													dateFormat : "yyyy-MM-dd",
													formEditorConfig : {
														newLine : false,
														otherAttributes:'onvaluechanged="adjustInterest"',
														type : 'date',
														labelWidth : 100,
														width : '100%',
														format : 'yyyy-MM-dd',
														required : true
													}
												},
												{
													field : 'endorsee',
													header : '被背书人',
													visible : true,
													formEditorConfig : {
														newLine:true,
														required : true,
														fieldVisible : true
													}
												},
												{
													field : 'endorsepurpose',
													header : '背书转让用途',
													visible : true,
													formEditorConfig : {
														newLine:false,
														required : true,
														fieldVisible : true
													}
												},
												
												 {
													field : 'projnameidname',
													header : '所属项目',
													visible : true,
													queryConfig : {width:210},
													formEditorConfig : {
														fieldVisible : false,
														entityClassName:'com.tenwa.leasing.entity.contract.ContractInfo',
														fillProperty:'name',
														fillFromFieldName : 'projnameid',
														entityHeaderFieldName:'projectName'
													}
												},
												{
													field : 'projnameid',
													header : '所属项目',
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
															xmlFileName : '/eleasing/jsp/guarantee_manage/guarantee_manage_proj.xml'
														}
													}
												}, 
												{
													field : 'payment',
													header : '款项',
													visible : true,
													formEditorConfig : {
														newLine:false,
														required : false,
														fieldVisible : true
													}
												},
								
												{
													field : 'accessmethod',
													header : '取得方式',
													headerAlign : 'center',
													visible : true,
													width : 100,
													
													formEditorConfig : {
														newLine : true,
														labelWidth : 100,
														width : 200,
														required : false,
														type : 'text'
													}
												},
												{
													field : 'rate',
													header : '利率(%)',
													headerAlign : 'center',
													visible : true,
													width : 100,
													formEditorConfig : {
														vtype : 'float',
														newLine : false,
														onkeyup:'adjustInterest',
														labelWidth : 100,
														width : 200,
														required : false,
														type : 'text'
													}
												},

												{
													field : 'interest',
													header : '利息',
													headerAlign : 'center',
													visible : true,
													width : 100,
													
													formEditorConfig : {
														labelWidth : 100,
														newLine : true,
														width : 200,
														required : false,														                   
														type : 'text'
													}
												}

										]
									});
						});
	});
	function showanduploadfile(id,type){
			var urlFlag = getRootPath()+"/leasing/financial/silver_account/silver_information_file_list.bi?id="+id+"&type="+type;
			mini.open({
		        url: urlFlag,
		        title: "银承台账附件", width: 800, height: 455,
		        onload: function () {
		         mini.get("table_silver_account_info_id").reload();
		   		 mini.unmask(document.body);
		        },
		        ondestroy: function (action) {
		        	if("savesuccess" == action){
		        		window.location.reload();
		        	}
		        }
		    });			
	  	}
	
	function adjustInterest(){
		var ticketamount=$("input[name=ticketamount]").val();
		var rate =$("input[name=rate]").val();
		var outdate=mini.getbyName("outdate").getValue();
		var duedate=mini.getbyName("duedate").getValue();
		var interest = $("input[name=interest]").val();
		//票据金额
		ticketamount=ticketamount||0;
		//利率
		rate = rate || 0;
		//利息
		interest=interest||0;
		outdate=outdate||0;
		duedate=duedate||0;
		var temp = Number(ticketamount)*Number(rate)*((duedate-outdate)/(3600*1000*24))/365;
		mini.getbyName("interest").setValue(decimal(Number(temp),2));
	}
</script>
</head>
<body>
	<div id="mini_test_form">
		<div id="id_table_render_table1"></div>
	</div>
</body>
</html>
