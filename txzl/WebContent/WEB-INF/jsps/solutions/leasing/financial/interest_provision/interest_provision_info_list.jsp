<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>利息计提明细表</title>
<%
	String dateid = request.getParameter("id");
%>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
function importMiniTableCallBack(message, tableid) {
	var info = eval("(" + message + ")");
	alert(info.message);
	var tabledate = info.tabledate;
	if ("" != tabledate) {
		var grid = mini.get(tableid);
		grid.set({
			data : mini.decode(tabledate)
		});
	}
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
}
var dateid ="<%=dateid%>";
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ],function(ApTable) {
			new ApTable(
			{   
				id : 'table_interest_provision_info_id',
				renderTo : "id_table_render_table1",
				width : globalClientWidth,
				height : globalClientHeight,
				iconCls : 'icon-node',
				hiddenQueryArea : true,
				multiSelect : true,
				editFormPopupWindowWidth : 800,
				editFormPopupWindowHeight : 250,
				title : '利息计提明细表',
				remoteOper : true,
				pageSize : 500,
				showPager : false,
				lazyLoad : false,
				loadMode : 'ajax',
				importTargetClass : 'com.tenwa.leasing.entity.finacial.InterestProvisionInfo',
				importDataIndex : '2',
				importHeaderIndex : '1',
				importDateid:dateid,
				importOrExPortCallBack:'SetDateidInterest',
				entityClassName : 'com.tenwa.leasing.entity.finacial.InterestProvisionInfo',
				frozenStartColumn : 0,
				frozenEndColumn : 0,
				xmlFileName : '/eleasing/jsp/finacial/interest_provision/interest_provision_info_list.xml',
				params : {
					dateid : dateid
				},
				tools : [ 'add', '-', 'edit', '-',
						'remove', '-', 'importExcel',
						'-', 'exportExcel' ,'-',{
					html : '更新预提信息',
					plain : true,
					iconCls : 'icon-remove',
					handler : function(miniTable, buttonText) {
						var userid='${sessionScope['login_userid']}';
						var url = getRootPath()
								+ "/leasing/updateInterest.acl";
						var params = {};
						params["dateid"] = dateid;
						$.ajax({
									url : url,
									data : params,
									type : 'post',
									async : false,
									success : function(e) {
										miniTable.reload();
									}
																		 
						});
						
					}
				}],
				beforeShowWindowCallBack:function(miniTable,miniForm, operType){	
					 if(operType=='edit' ){
						 var row = miniTable.getSelected();						 
						 var newstr=row.interestrate.replace(/%/, "");
						 mini.getbyName("interestrate").setValue((parseFloat(newstr)/100).toFixed(4)+"");
						 return true;
					 }
					
				 
				},
				validateForm(miniTable, miniForm, editFormItemOperFlag, addIndex){
					 var interestrate=mini.getbyName("interestrate").getValue();
						  if(isNaN(interestrate)){
							  mini.alert("利率请输入数字！");
							  return false;
						  }
				      	 return true;
				},
				columns : [ {
					type : 'indexcolumn'
				}, {
					type : 'checkcolumn'
				}, {
					field : 'id',
					header : '编号',
					headerAlign : 'center',
					width : 100,
					allowSort : true,
					visible : false,
					formEditorConfig : {
						fieldVisible : false
					}
				},
				{
					field : 'dateid',
					header : '月份',
					visible : false,
					formEditorConfig : {
						width : 200,
						fieldVisible : false,
						value : dateid
					}
				},
				{
					field : 'servicenumber',
					header : '业务编号',
					headerAlign : 'center',
					visible : true,
					width : 100,
					formEditorConfig : {
						newLine : true,
						labelWidth : 100,					
						width : 200,
						required : true,
						type : 'text'
					}
				}, {
					field : 'contractno',
					header : '合同号',
					headerAlign : 'center',
					visible : true,
					width : 100,
					formEditorConfig : {
						newLine : false,
						labelWidth : 100,
						width : 200,
						required : true,
						type : 'text'
					}
				}, {
					field : 'enterprisecustomer',
					header : '企业客户',
					headerAlign : 'center',
					visible : true,
					width : 100,
					formEditorConfig : {
						newLine : true,
						labelWidth : 100,
						required : true,
						width : 200,
						required : false,
						type : 'text'
					}
				}, {
					field : 'enterprisecustomername',
					header : '企业客户名称',
					headerAlign : 'center',
					visible : true,
					width : 100,
					formEditorConfig : {
						newLine : false,
						labelWidth : 100,
						width : 200,
						required : false,
						type : 'text'
					}
				}, {
					field : 'currency',
					header : '币种',
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
				},{
					field : 'interestrate',
					header : '利率',
					headerAlign : 'center',
					visible : true,
					width : 100,
					formEditorConfig : {
						newLine : false,
						labelWidth : 100,
						//vtype : "float",
						onkeyup:'adjustInterest',
						fieldVisible:true,
						width : 200,
						required : true,
						type : 'text'
					}
				}, {
					field : 'balance',
					header : '余额',
					headerAlign : 'center',
					visible : true,
					width : 100,
					summary:true,
					align:'right',
					dataType:"currency",
					formEditorConfig : {
						onkeyup:'adjustInterest',
						newLine : true,
						vtype : "float",
						required : true,
						width : "100%",
						type : 'text'
					}
				}, {
					field : 'interest',
					header : '利息',
					headerAlign : 'center',
					visible : true,
					width : 100,
					summary:true,
					dataType:"currency",
					formEditorConfig : {
						newLine : false,
						width : "100%",
						readOnly:true,
						required : true,
						type : 'text'
					}
				}, {
					field : 'dateofinterest',
					header : '起息日',
					headerAlign : 'center',
					visible : true,
					width : 100,
					dateFormat : 'yyyy-MM-dd',
					formEditorConfig : {
						otherAttributes:'onvaluechanged="adjustInterest"',
						newLine : true,
						width : "100%",
						required : true,
						type : 'date'
					}
				}, {
					field : 'ceasedate',
					header : '止息日',
					headerAlign : 'center',
					visible : true,
					width : 100,
					dateFormat : 'yyyy-MM-dd',
					formEditorConfig : {
						otherAttributes:'onvaluechanged="adjustInterest"',
						newLine : false,
						width : "100%",
						required : true,
						type : 'date'
					}
				}

				]
			});
		});
	});
	
	 function adjustInterest(){
			var balance=$("input[name=balance]").val();
			var interestrate =$("input[name=interestrate]").val();
			var dateofinterest=mini.getbyName("dateofinterest").getValue();
			var ceasedate=mini.getbyName("ceasedate").getValue();
			var interest = $("input[name=interest]").val();
			//借款余额
			balance=balance||0;
			//利率
			interestrate = interestrate || 0;
			//利息
			dateofinterest=dateofinterest||0;
			ceasedate=ceasedate||0;
			var temp = Number(balance)*Number(interestrate)*((ceasedate-dateofinterest)/(3600*1000*24))/360;
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
