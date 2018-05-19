<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保证金折现</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<body style="overflow:hidden;"> 
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "table1",
				renderTo: "id_cash_deposit",
				width: '100%',
				height: '100%',
				showPager : true,
				title:'保证金折现',
				remoteOper : true,
				multiSelect: true,
				entityClassName : "com.tenwa.leasing.entity.finacial.CashDepositBase",
				xmlFileName:"/eleasing/jsp/cash_deposit/cash_deposit_open.xml",
				 afterShowWindowCallBack:function(miniTable, miniForm, operFlag){
						if("edit"==operFlag){
							mini.getbyName("projid").setText(mini.getbyName("projectname").getValue());
							getMiniEditFormField("table1.contractid").disable();
						}
						return true;
					}, 
				tools : [ "add", "-", "edit", "-", "remove","-", {
					html : '清除保证金折算数据',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var rows = miniTable.getSelecteds();
						var ids = [];
						var param = {};
						if(rows.length!=0){
							for(var i = 0;i<rows.length;i++){
									var row = rows[i];
									var id = row.id;
									ids.push(id);
							}
						    param["ids"] = ids.join(",");	
							ajaxRequest({
								params:param,
								url:getRootPath()+"/leasing/removeCashDepositData.acl",
								method : 'POST',
								async : false,
								success:function(response){
								window.location.reload(); 
								}
							});
						}else{
								mini.alert('请先选择保证金！');
						}			
				  	}  
				},
				          '-',{
					html : '保证金折现计算',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var rows = miniTable.getSelecteds();
						if(rows.length!=0){
								mini.get("id_cash_deposit_date").show();
						}else{
								mini.alert('请先选择保证金！');
						}			
				  	}  
				}],
				columns: [
					{type : 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: '主键',visible:false,formEditorConfig : {visible:false}},
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
								mini.getbyName("contractnumber").setValue(rowData.name);
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
						field : 'contractnumber',
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
					{field: 'factdate', header: '保证金收取日',
						dateFormat : "yyyy-MM-dd",
						formEditorConfig : {
							newLine : true,
							type : 'date',
							required : true,
							labelWidth : 100,
							width : '100%',
							format : 'yyyy-MM-dd'}},
					{field: 'maxdate', header: '保证金到期日',formEditorConfig : {
						newLine : false,
						type : 'date',
						required : true,
						labelWidth : 100,
						width : '100%',
						format : 'yyyy-MM-dd'}},
					{field: 'factmoney',dataType:"currency", header: '保证金金额',formEditorConfig : {newLine : true,vtype : "float",
						required : true,}},
					{field: 'startdate', width : 150,header: '贷款基准利率执行开始时间',formEditorConfig : {fieldVisible : false}},
					{field: 'baserateone', header: '贷款基准利率(%)',formEditorConfig : {fieldVisible : false}},
					{field: 'depositstartdate', width : 150, header: '存款款基准利率执行开始时间',formEditorConfig : {fieldVisible : false}},
					{field: 'baseratedepositone', header: '存款基准利率(%)',formEditorConfig : {fieldVisible : false}},
					{field: 'preendcashdepositamount',dataType:"currency", header: '前年底折现金额',formEditorConfig : {fieldVisible : false}},
					{field: 'nowyearcashdepositamount', dataType:"currency",header: '当年折现金额',formEditorConfig : {fieldVisible : false}},
					{field: 'nowendcashdepositamount',dataType:"currency", header: '当年底折现金额',formEditorConfig : {fieldVisible : false}},
					{field: 'preendcashdepositinterest', width : 120,dataType:"currency", header: '前年底保证金利息',formEditorConfig : {fieldVisible : false}},
					{field: 'nowendcashdepositinterest',  width : 120,dataType:"currency",header: '当年底保证金利息',formEditorConfig : {fieldVisible : false}},
					{field: 'yeartxe', header: '年摊销额', dataType:"currency",formEditorConfig : {fieldVisible : false}},
					{field: 'monthtxe', header: '月摊销额', dataType:"currency",formEditorConfig : {fieldVisible : false}}
				]
				
			});
		});
	});
   function cashdeposit(){
		var table1 = mini.get("table1");
		var rows = table1.getSelecteds();
		var param = {};
		param.cashdepositdate=mini.formatDate(mini.get("cashdepositdate").getValue(),"yyyy-MM-dd");
		var tempId = [];
		var tempFactmoney = [];
		var tempMaxdate = [];
		var tempBaserateone = [];
		var tempBaseratedepositone = [];
		
		for(var i = 0;i<rows.length;i++){
				var row = rows[i];
				var id = row.id;
				var factmoney=row.factmoney;
				var maxdate=row.maxdate;
				var baserateone=row.baserateone;
				var baseratedepositone=row.baseratedepositone;
				//校验折现日期不能大于保证金有效期
				if(param.cashdepositdate>row.maxdate||param.cashdepositdate<row.factdate){
					mini.alert(row.projectname+"的保证金折现日期不在计算范围内");
					return false;
				}
				if(param.cashdepositdate.substring(0,7)!=row.factdate.substring(0,7)&&param.cashdepositdate.substring(5,7)!=12){
					mini.alert(row.projectname+"的保证金折现日期不在计算范围内");
					return false;
				}
				tempId.push(id);
				tempFactmoney.push(factmoney);
				tempMaxdate.push(maxdate);
				tempBaserateone.push(baserateone);
				tempBaseratedepositone.push(baseratedepositone);
			}
		param["ids"] = tempId.join(",");
		param["factmoneys"] = tempFactmoney.join(",");
		param["maxdates"] = tempMaxdate.join(",");
		param["baserateones"] = tempBaserateone.join(","); 
		param["baseratedepositones"] = tempBaseratedepositone.join(","); 
		
		ajaxRequest({
			params:param,
			url:getRootPath()+"/leasing/saveCashDepositData.acl",
			method : 'POST',
			async : false,
			success:function(response){
			window.location.reload(); 
			}
		});
	} 
</script>
<div id="id_cash_deposit" style="width: 100%;height: 100%;"></div>
<div id="id_cash_deposit_date" class="mini-window" title="选择保证金折现计算日期" style="width:400px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
	
		<table>
             	<tr>
		      
                    <td style="width:100px;">折现日期：</td>
                    <td ><input id="cashdepositdate" name="cashdepositdate" class="mini-datepicker miniext-form-fit" vtype="maxLength:50" /> </td>
                    <td>
                     <a class="mini-button" iconCls="icon-edit" onclick="cashdeposit">确定</a>
                     </td>
                </tr>
          </table>
	
</div>
</body>
</html>