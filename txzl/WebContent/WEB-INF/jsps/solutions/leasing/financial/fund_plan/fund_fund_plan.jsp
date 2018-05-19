<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>资金安排表</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>

<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script> 
<script type="text/javascript">
    var didate="${requestScope['didate']}";
    var tuid="${requestScope['tuid']}";
    var diid="${requestScope['id']}";
    var plantimestart=didate+"-01";
    var plantimeend=didate+"-"+plantimeend(didate);
    var accountbalance="${requestScope['accountbalance']}";
    var balancedeposit="${requestScope['balancedeposit']}";
//获取月份总天数
    function plantimeend(didatetime){
    	var a=didatetime.substr(0,4);
    	var year=parseInt(didatetime.substr(0,4));
    	var month=parseInt(didatetime.substr(5,7));
    	var day = new Date(year,month,0);
    	var daycount = day.getDate();
        return daycount;  
    }
	var currentClientWidth = document.documentElement.clientWidth-1;
	var showTools=true;
	if("${requestScope['opertype']}"=='view'){showTools=false};
	jQuery(function() {		
		//var a=getRootPath();
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
	//	tenwa.createTable({
					id : "fund_fund_plan",
					renderTo : "id_table_fund_fund_plan",
					//width : currentClientWidth,
					width:globalClientWidth - 30,
					height :currentClientWidth-900,
					lazyLoad : false,
					title : "",
					showToolbar:showTools,
					remoteOper:true,
					tools : ['add','-','edit','-','remove','-','exportExcel'],
					queryButtonColSpan : 2,
					allowCellWrap:true,
					showPager : true,
					pageSize : 10,
				    entityClassName:'com.tenwa.leasing.entity.finacial.DepositInterestDetail',
				    xmlFileName : '/eleasing/jsp/finacial/fund_plan/fund_fund_plan.xml',
					params:{didate:"${requestScope['didate']}",tuid:"${requestScope['tuid']}",diid:"${requestScope['id']}",plantimeStart:plantimestart,
					accountbalance:accountbalance,balancedeposit:balancedeposit,plantimeEnd:plantimeend},
					validateForm : function(miniTable, miniForm, editFormItemOperFlag, addIndex){
						if (editFormItemOperFlag == "edit") {
							row = miniTable.getSelected();
							beforebalancedeposit = row.beforebalancedeposit;
							fundallocated = mini.getbyName("fundallocated").getValue();
						  //资金下拨校验
							if(parseFloat(fundallocated)>parseFloat(beforebalancedeposit)){
								mini.alert("资金下拨金额不能大于上一日存款余额");
								return false;
							} 
							//资金下拨校验accoutamount
							accoutamount =mini.getbyName("accoutamount").getValue();
							inneraccount =mini.getbyName("inneraccount").getValue();
returnamount =mini.getbyName("returnamount").getValue();
innerborrow =mini.getbyName("innerborrow").getValue();
outterborrow =mini.getbyName("outterborrow").getValue();
payment =mini.getbyName("payment").getValue();
funddeposit =mini.getbyName("funddeposit").getValue();
innertran =mini.getbyName("innertran").getValue();
outterreturn =mini.getbyName("outterreturn").getValue();
groupreturn =mini.getbyName("groupreturn").getValue();
var sum=parseFloat(accoutamount)+parseFloat(inneraccount)+parseFloat(returnamount)+parseFloat(fundallocated)+parseFloat(innerborrow)+parseFloat(outterborrow);
							if(parseFloat(funddeposit)>sum){
								mini.alert("资金上存金额不能大于当日账户金额和收款总和");
								return false;
							} 
							if(parseFloat(innertran)>sum){
								mini.alert("内部账户转款(转出)金额不能大于当日账户金额和收款总和");
								return false;
							} 
							if(parseFloat(outterreturn)>sum){
								mini.alert("外部还款金额不能大于当日账户金额和收款总和");
								return false;
							} 
							if(parseFloat(groupreturn)>sum){
								mini.alert("还集团借款金额不能大于当日账户金额和收款总和");
								return false;
							} 
						};
						return true;
				    },
					columns : [
							
							{type : 'checkcolumn'},		
							{field : 'id',header : 'id',visible: false,
								formEditorConfig:{
									fieldVisible: false,
								}	
							}, 
							{
								type : 'indexcolumn'
							},
							{field : 'fundplanno',header : 'fundplanno',visible: false,
								formEditorConfig:{
									fieldVisible: false,
								}	
							}, 
							{
								field : 'plantime',
								header : '时间',
								headerAlign : 'center',
								visible : true,
								width : 100,
								dateFormat : "yyyy-MM-dd",
								formEditorConfig : {
									type : 'date',
									colspan : 3,
									defaultValue :"${requestScope['didate']}",
									labelWidth : 100,
									width : '100%',
									format : 'yyyy-MM-dd',
									required : true
								}
							},
						 {field : 'accoutamount',header : '账户金额',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									fieldVisible: false,
								vtype:'float',
								type:'text'
								}
							}, 
							
							{field : 'inneraccount',header : '内部账户转款(转入)',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									newLine:true,
									type:'text'
								}
							},
							{field : 'returnamount',header : '回款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							{field : 'fundallocated',header : '资金下拨',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									newLine:true,
									type:'text'
								}
							},
							{field : 'innerborrow',header : '内部借款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							{field : 'outterborrow',header : '外部借款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									newLine:true,
									type:'text'
								}
							},
							//付款款项
							{field : 'payment',header : '付款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							{field : 'funddeposit',header : '资金上存',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									newLine:true,
									type:'text'
								}
							},
							{field : 'innertran',header : '内部账户转款(转出)',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							{field : 'outterreturn',header : '外部还款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									newLine:true,
									type:'text'
								}
							},
							{field : 'groupreturn',header : '还集团借款',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							} ,
							{field : 'accountbalance',header : '账户余额',align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
								 	fieldVisible: false
								}
							},
							{field : 'beforebalancedeposit',header : '上一日存款余额',align:'right',dataType :"currency",currencyUnit:"￥",
								visible: false,
								summary:true,
								formEditorConfig:{
									fieldVisible: false
								}
							} , 
							{field : 'balancedeposit',header : '存款余额',align:'center',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									fieldVisible: false
									
								}
							} , 
							{field : 'diid',header : '资金预实表id',visible: false,
								formEditorConfig:{
								   
									readOnly:true,
									fieldVisible: false,
									defaultValue:diid
								}	
							}
							]
				});
	});	});
	
</script>
</head>
<body>
	<div id="id_table_fund_fund_plan"></div>
</div>
</body>
</html>