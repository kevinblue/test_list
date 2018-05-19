<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逾期利息计算器</title>   
    <!--css sheet-->
	<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindySerializeFormToJsonObject.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/my97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyOperationTable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyQueryInput.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/MustFillIn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/custType.js"></script>
	<style type="text/css">
	   html,body{
	     overflow:hidden;
	   }
	   td.td-content{
	     text-align:left;
	   }
	   td.td-content input{
	      width:165px;
	      float:left;
	      border:1px solid #DDD;
	   }
	   td.td-content textarea{
	      width:90%;
	      overflow:auto;
	      float:left;
	      border:1px solid #DDD;
	   }
	</style>
<script type="text/javascript">
	var type = -1; 
	var pageWidth = document.documentElement.clientWidth - 2;
	var pageHeight = document.documentElement.clientHeight - 2;
	jQuery(function() {
		new tracywindyOperationTable({
			resetFormCallback : function(table, $form, operFlag) {
				if ("add" == operFlag) {

				}
				if ("update" == operFlag) {
					var rowData = table.getCheckedRowDatas();
				}
			},
			loadFormDataCallBack : function(table, $form, rowIndex) {
				
			},
			//tableComment : '逾期利息计算器',
			constantFlagTable : '',
			windowTop : 80,
			border : true,
			renderTo : 'table_container',
			//title : '逾期利息计算器',
			width : pageWidth,
			height : pageHeight-90,
			id : 'id_table',
			xmlFileName : '/acl/queryEquipPenalty.xml',
			loadMode : 'ajax',
			isPage : true,
			pageSize: 100,
			isFit : false,
			operButtons : '',
			enabledPromit : '有效',
			disabledPromit : '无效',
			enabledDefaultValue : '1',
			isNeedEnabled : false,
			isAutoBreakContent : true,
			checkType:'checkbox',
			lazyLoad:true,
			isCheck:false,
			isRank:true,
			tools:[{
				html : '<font color="red" style="font-weight:bold;">查询</font>',
				handler : function(table) {
					queryPenalty(table);
				},
				iconCls :'icon-zoom'
			}],
			isStatistic:true,
			statisticColumnNames:['plan_pena','income_pena','for_pena'],
			lockedNames:['cust_name','contract_id'],
			columns:[
				{header:'客户名称',name:'cust_name',width:190},
				{header:'合同编号',name:'contract_id',width:150},
				{header:'设备识别号',name:'equip_no',width:170},
				{header:'计划期次',name:'rent_list',width:80},
				{header:'计划还款日期',name:'plan_date',width:90},
				{header:'到账日期',name:'hire_date',width:90},
				{header:'计划还款租金',name:'rent',width:90,type:'double'},
				{header:'计划还款本金',name:'business_corpus',width:90,type:'double'},
				{header:'计划还款利息',name:'business_interest',width:90,type:'double'},
				{header:'实际还款租金',name:'in_rent',width:90,type:'double'},
				{header:'实际还款本金',name:'in_corpus',width:90,type:'double'},
				{header:'实际还款利息',name:'in_interest',width:90,type:'double'},
				{header:'逾期天数',name:'overdue_day',width:80,align:'center'},
				{header:'逾期利息',name:'plan_pena',width:90,type:'double'},
				{header:'已核销',name:'income_pena',width:90,type:'double'},
				{header:'未核销',name:'for_pena',width:90,type:'double'}
			],
			params : {}
		});
		
		//选择合同编号
		new tracywindyQueryInput({
			id:'id_combo_id_contractid',
			xmlFileName:'/acl/queryContractInfo.xml',
			label:'合同编号',
			otherAttributes:"",
			displayField : 'contract_id',
			valueField : 'contract_no',
			assignInputHiddenInputId: 'selectOfValue_contractid',
			assignInputDisplayInputId: 'selectOfRawValue_contractid',
			onSelect:function(combo,rowData){
				var rowDatas = combo.getSelectedOptionData();
				$("#custname_contract").val(rowDatas.cust_name);
				$("#agents_contract").val(rowDatas.cust_name);
				$("#agentsno_contract").val(rowDatas.card_no);
			},
			params:{
				
			}
		});
		
		var nowdate = new Date();
		$("#due_date").val(nowdate.format("yyyy-MM-dd"));
	});
	

	
	//查询逾期利息
	function queryPenalty(table){
		var contract_id = $("#selectOfValue_contractid").val();
		var equip_no = $("#equip_no").val();
		var rent_list = $("#rent_list").val();
		var due_date = $("#due_date").val();
		var params = {};
		if(contract_id == '' && equip_no == ''){
			alert("请填写合同编号或设备识别号！");
			return;
		}
		if(due_date == ''){
			alet("截止日期不可以为空");
			return;
		}
		params.contract_id = contract_id;
		params.equip_no = equip_no;
		params.rent_list = rent_list;
		params.due_date = due_date;
		//var table = getTracywindyObject("id_table");
		table.setParams(params);
		table.reload();
	}
</script>
</head>
<body>
	<table style="width:100%" class="fillTable">
		<tr>
			<td class="x-panel-table-div-title" colspan=6>&nbsp;逾期利息计算器</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title">合同编号</td>
			<td class="td-content">
				<input type="hidden" id="selectOfValue_contractid" name="contract_id" require='true' label="合同编号"/>
				<input type="text" id="selectOfRawValue_contractid"/>
				<!-- <input name="contract_id" id="contract_id" label="合同编号" class="td-content-input" type="text" /> -->
			</td>
			<td class="td-content-title">设备识别号</td>
			<td class="td-content">
				<input name="equip_no" id="equip_no" label="设备识别号" class="td-content-input" type="text" />
			</td>
		<tr class="tr-odd">
			<td class="td-content-title">期次</td>
			<td class="td-content">
				<input name="rent_list" id="rent_list" label="期次" class="td-content-input" type="text" />
			</td>
			<td class="td-content-title">截止日期</td>
			<td class="td-content">
				<input name="due_date" id="due_date" label="截止日期" class="Wdate td-content-input" onClick="WdatePicker(this,{readOnly:true})" type="text" />
			</td>
		</tr>
	</table>
<div id="table_container"></div>
</body>
</html>