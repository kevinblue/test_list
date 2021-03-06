	<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同收款流程信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<jsp:include page="../fund_comm/fund_comm_js_function.jsp"></jsp:include>
<script type="text/javascript">
var attachmentParams="";
var localMayMoney = [ {id:'',text:'全部'},{id :'and  cp.overmoney>0',text :'大于零'}, {id :' and cp.overmoney=0',text :'等于零'}];
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id_index',
			width:globalClientWidth,
			height:globalClientHeight,
			title:'收款流程信息',
			multiSelect:false, 
			editFormPopupWindowWidth:650, 
			hiddenQueryArea:false, 
			queryButtonColSpan:2,
			showPager:true,
			xmlFileName:'eleasing/workflow/fund/fundcomm/fund_fund_plan_all_list.xml',
			tools:toolsArray,
			remoteOper:false,
			params:{paytype:'pay_type_in',ismaymoney:'and cp.overmoney>0'},
			afterClickClearBtnCallBack: function(miniTable,queryAreaParams){
				mini.get("table_id_index_queryArea_ismaymoney").setText("大于零");
				$("input[name='table_id_index_queryArea_ismaymoney']").val("and  cp.overmoney>0");
				return true;
			},
			columns:[ 
			    {type:'indexcolumn'}, 
			   	{type:'checkcolumn'}, 
			   	
			   	{field:'id',header:'id',visible:false},
				{field:'projid',header:'项目编号',visible:false,allowSort:true,queryConfig:{},formEditorConfig:{ }},
				/* {field:'contractid',header:'合同编号',visible:true,allowSort:true,formEditorConfig:{ }}, */
				/* {field:'contractnumber',header:'业务合同编号',headerAlign:'center',queryConfig:{},formEditorConfig:{ }}, */
				{field:'contract_put_number',header:'投放编号',headerAlign:'center',queryConfig:{},formEditorConfig:{ } },
				{field:'planmoney',header:'应收资金合计',summary:true,dataType:"currency",headerAlign:'center',
					      formEditorConfig:{newLine:true },
					           queryConfig:{
					                  type:'float',
					                 range:true,
					               colspan:4}},
				{field:'factmoney',header:'已收资金合计',summary:true,dataType:"currency",headerAlign:'center',formEditorConfig:{ }},
				{field:'feeadjust',header:'调整资金合计',summary:true,dataType:"currency",headerAlign:'center',formEditorConfig:{ }},
				{field:'overmoney',header:'未收资金合计',summary:true,dataType:"currency",headerAlign:'center',formEditorConfig:{newLine:true }},
				{field:'collectstatus',header:'收款状态',headerAlign:'center',formEditorConfig:{newLine:true },
					             renderer:function(e){ return setfundIncomeState(e.record);}},
				{field:'projectname',header:'项目名称',headerAlign:'center',queryConfig:{colspan:4},formEditorConfig:{newLine:true }},
				{field:'custid',header:'承租人id',headerAlign:'center',visible:false},
				{field:'custname',header:'承租人',headerAlign:'center',queryConfig:{newLine:true},formEditorConfig:{ }},
				{field:'projmanagename',header:'项目经理',headerAlign:'center',queryConfig:{},formEditorConfig:{newLine:true }},
				{field:'ismaymoney',header:'可收款金额',visible:false,headerAlign:'center',
							    queryConfig:{ 
				   			       readOnly:false,
				   			           data:localMayMoney,
				   			           type:'combobox',
				   			   fieldVisible:true,
				   			      textField:"text",
				   			     valueField:"id",
				   			          value:'and  cp.overmoney>0',
				   			           text:'大于零'
		   		}}
			]
		});
	});
var toolsArray =
	[
		{
			html:'发起收款流程',
			plain:true,
			iconCls:'icon-addfolder',
			handler:function(miniTable, buttonText) 
			{
				var row = miniTable.getSelected();
				var overmoney=row.overmoney;
			    if (parseFloat(overmoney)==0)
			      {mini.alert("资金收款完毕不允许发起收款流程,有特殊需要请考虑走红冲!");return false;}
			    else   
			      {attachmentParams="contract_id="+row.id;showEbankInfo(row);}
			       miniTable.load();
			}
		}
		 			
	];
	tenwa.createTable({
		id:"fund_ebank_data",
		renderTo:"id_table_container_fund_ebank_data",
		width:'100%',
		height:'100%',
		lazyLoad:false,
		remoteOper:false,
		showPager:true,
		pageSize:20,
		tools:[{
			html:'选中网银发起收款流程',
			plain:false,
			iconCls:'icon-add',
			handler:function(miniTable, buttonText){
				var ebankRowData = miniTable.getSelected();
				if(ebankRowData){
					var mayopemoney=ebankRowData.mayopemoney;
					if(parseFloat(mayopemoney)==0){mini.alert("可核销金额为零不能发起收款流程！");return false;}
					attachmentParams =  attachmentParams+"&ebank_id="+ebankRowData.id;
					startProcess("收款流程-1",attachmentParams); 
				}else{
					mini.alert("请选中要操作的数据！");
				}
			}
		}],
		params:{invalid:'否',ismayopemoney:true},
		xmlFileName:'/eleasing/jsp/ebank/fundebank/fund_ebank_data_list.xml',
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'ebdataid', header:'网银编号',queryConfig:{}},
			{field:'sn', header:'流水号',queryConfig:{}},
			{field:'custidname',header:'承租人名称',visible:true,queryConfig:{colspan:3}},
			{field:'clientname', header:'付款人'},
			{field:'factmoney', header:'到账金额',summary:true,dataType:"currency",queryConfig:{newLine:true,type:'float',range:true,colspan:4}},
			{field:'nowithmoney', header:'非业务金额',summary:true,dataType:"currency"},
			{field:'hadmoney',header:'已核销金额',summary:true,dataType:"currency",headerAlign:'center'},
			{field:'mayopemoney', header:'可核销金额',summary:true,dataType:"currency"},
			{field:'factdate', header:'到账时间'},
			{field:'ownbank', header:'本方银行'},
			{field:'ownaccnumber', header:'本方账号'},
			{field:'ownaccount', header:'本方账户'},
			{field:'clientbank', header:'对方银行',queryConfig:{}},
			{field:'clientaccnumber', header:'对方账号',queryConfig:{}},
			{field:'clientaccount', header:'对方账户'},
			{field:'uploaddate', header:'上传时间',type:'date',dateFormat:"yyyy-MM-dd hh:mm:ss"}
		]
	});
	});
</script>
 </head>
 <body>
 
<div id="id_table_window_fund_ebank_data" class="mini-window" title="选择网银" style="width:1000px;height:500px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_table_container_fund_ebank_data" style="width:100%;height:100%;"></div>
</div>
 </body>
<script type="text/javascript">
function showEbankInfo(row)
{
	var editWindow = mini.get("id_table_window_fund_ebank_data");
	editWindow.show();
	var usergrid = mini.get("fund_ebank_data");
	usergrid.setParams({custid:row.custid});
	usergrid.reload();
}
</script>
</html>