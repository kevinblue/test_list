<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同付款流程信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
var localMayMoney = [ {id:'',text:'全部'},{id : 'and  pln.planmoney-nvl(chag.fact_money,0)>0',text : '大于零'}, {id : ' and pln.planmoney-nvl(chag.fact_money,0)<0',text : '小于零'}];
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id_index',
			width : globalClientWidth,
			height : globalClientHeight,
			title : '付款流程信息',
			multiSelect : false, 
			editFormPopupWindowWidth : 650, 
			hiddenQueryArea : false, 			
			//queryButtonColSpan:3,
			
			showPager:true,
			xmlFileName : 'eleasing/workflow/fund/fund_put/fund_put_open_list.xml',
			tools : toolsArray,
			remoteOper : false,
			queryButtonColSpan:8,
			params : {
			    ismaymoney:'and  pln.planmoney-nvl(chag.fact_money,0)>0'
			},
			afterClickClearBtnCallBack: function(miniTable,queryAreaParams){
				mini.get("table_id_index_queryArea_ismaymoney").setText("大于零");
				$("input[name='table_id_index_queryArea_ismaymoney']").val("and  pln.planmoney-nvl(chag.fact_money,0)>0");
				return true;
			},
			columns : [ 
			    {type : 'indexcolumn'},
			   	{type : 'checkcolumn'},
			   	{field : 'id',header : 'id',visible : false},
			   	{field : 'projid',header : '项目编号',visible : false,allowSort:true,queryConfig : {},formEditorConfig : { }},
			   	{field : 'contractid',header : '合同编号',allowSort:true,formEditorConfig : { }},
			   	{field : 'contractnumber',header : '业务合同编号',width:'100',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
			   	{field : 'planmoney',header : '计划付款金额',summary : true,dataType : "currency",headerAlign : 'center'},
			   	{field : 'factmoney',header : '已付款金额',summary : true,dataType : "currency",headerAlign : 'center'},
			   	{field : 'overmoney',header : '未付款金额',summary : true,dataType: "currency",headerAlign : 'center'},
			   	{field : 'projectname',header : '项目名称',width:'120',headerAlign : 'center',queryConfig : {},formEditorConfig : {newLine:true }},
			   	{field : 'custname',header : '承租人',headerAlign : 'center',queryConfig : {},formEditorConfig : { }},
			   	{field : 'projmanage',header : '项目经理',headerAlign : 'center',width:80,queryConfig : {newLine:true},formEditorConfig : {newLine:true }},
			   	{field : 'ismaymoney',header : '未付款金额',visible : false,headerAlign : 'center',queryConfig : { 
		   			 readOnly : false,
		   			 data : localMayMoney,
		   			 type : 'combobox',
		   			 fieldVisible : true,
		   			 textField: "text",
		   			 valueField: "id",
		   			 value:'and  pln.planmoney-nvl(chag.fact_money,0)>0',
		   			 text: '大于零'
		   		}}/* ,
		   		{field : 'receivedinvoice',header : '收到票据类型',width:190,headerAlign : 'center'} */
			]
		});
	});
});
/* toolsArray callback start*/
var toolsArray =
	[
		{
			html : '发起付款流程',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable, buttonText) 
			{
				var rows = miniTable.getSelected();
			  //  var signdate=rows.signdate;//合同签约时间
			    var leasform=rows.leas_form;
				/*    if(signdate==null||signdate==''){
					  
					   mini.alert("该合同签约日期尚未维护,请联系商务经办！");
					  return ; 
				   } */
			  //  if(leasform=="lease_form1"||leasform=="lease_form3"){
				
						var row = miniTable.getSelected();
						var attachmentParams = "contract_id="+row.id;
						//mini.alert(row.id);
						var overmoney=row.overmoney;
						if(parseFloat(overmoney)<=0){mini.alert("资金已经投放完毕");}
						startProcess("实际投放-1",attachmentParams);	
			//    }
			    /* else{
					mini.confirm("请确认有无查询中登网","请确认有无查询中登网",function(action){
						if(action=='ok'){
							var row = miniTable.getSelected();
							var attachmentParams = "contract_id="+row.id;
							var overmoney=row.overmoney;
							if(parseFloat(overmoney)<=0){mini.alert("资金已经投放完毕");}
							startProcess("实际投放-1",attachmentParams);
						}
					});
			    } */
			}
		}
	 			
	];
/* toolsArray end */
</script>
 </head>
 <body>
 </body>
 
</html>