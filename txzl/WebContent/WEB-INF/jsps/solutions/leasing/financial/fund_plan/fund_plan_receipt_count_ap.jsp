<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>收款统计表</title>
<script type="text/javascript">
function importMiniTableCallBack(message, tableid) {
	var info = eval("(" + message + ")");
	mini.alert(info.message);
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
}
    var didate="${requestScope['didate']}";
    var tuid="${requestScope['tuid']}";
    var diid="${requestScope['id']}";
	var currentClientWidth = document.documentElement.clientWidth-1;
	var plantimestart=didate+"-01";
	var plantimeend=didate+"-"+plantimeend(didate);
	  //获取月份总天数
	    function plantimeend(didatetime){
	    	var year=parseInt(didatetime.substr(0,4));
	    	var month=parseInt(didatetime.substr(5,7));
	    	var day = new Date(year,month,0);
	    	var daycount = day.getDate();
	        return daycount;  
	    }
	jQuery(function() {		
		var paramid = "${param.id}";
		tenwa.createTable({
					id : "receipt_count_ap",
					renderTo : "id_table_receipt_count_ap",
					width : currentClientWidth,
					height :305,
					lazyLoad : false,
					title : "收款统计",
					tools : ['add','-','edit','-','remove','-','importExcel','-','exportExcel'],
					queryButtonColSpan : 2,
					remoteOper:true,
					allowCellWrap:true,
					showPager : true,
					loadMode:'ajax',
					pageSize : 20,
					entityClassName:'com.tenwa.leasing.entity.finacial.ReceiptCountDetailAP',
					xmlFileName : '/eleasing/jsp/finacial/fund_plan/fund_plan_receipt_count_ap.xml',
					params:{reportid:paramid},
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
							{field : 'fundplanno',header : '序列号',visible: false,
								formEditorConfig:{
									fieldVisible:false,
									type:'text'
								}
							},
							{field : 'receiptcount',header : '收款统计',
								formEditorConfig:{
									type:'text'
								}
							},
							
							{field : 'amount',header : '金额',width : 60,align:'right',dataType :"currency",currencyUnit:"￥",
								summary:true,
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							 {
								field : 'receipttime',
								header : '时间',
								headerAlign : 'center',
								visible : true,
								width : 60,
								dateFormat : "yyyy-MM-dd",
								formEditorConfig : {
									newLine:true,
									type : 'date',
									labelWidth : 100,
									width : '100%',
									format : 'yyyy-MM-dd',
									required : false
								}
							},
							{field : 'paymentunit',header : '付款单位',
								formEditorConfig:{
									type:'text'
								}
							},
							   {
								field : 'plannameidname',
								header : '收款类型',
								visible : true,
								formEditorConfig : {
									fillFromFieldName:'plannameid',
						            fillProperty:'name',
						            fieldVisible:false
								}
							},  
							{
								field : 'plannameid',
								header : '收款类型',
								visible : true,
								formEditorConfig : {
									type : 'queryinput',
									newLine:true,
									showNullItem : "true",
									fieldVisible:true,
									textField : 'name',
									valueField : 'value',
									params : {
										pid : 'fund_plan_in',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'fundtype',
								header : '资金安排类别',
								width : 60,
								visible : true,
								formEditorConfig : {
									type : 'combobox',
									newLine:false,
									fieldVisible:true,
									textField : 'text',
									valueField : 'text',
									data:[{text:'内部账户转款(转入)'},{text:'回款'},{text:'资金下拨'}, {text:'内部借款'},{text:'外部借款'}]
								}
							},
							{
								field : 'note',
								header : '备注',
								formEditorConfig : {
									newLine : true,
									colspan : 3,
									width : "100%",
									type : 'textarea'
								}
							},
							{
								field : 'reportid',
								header : '关联ID',
								visible : false,
								formEditorConfig : {
									fieldVisible:false,
									defaultValue:paramid
								}
							}
							
							]
				});
	});	
	
</script>

</head>
<body>
	<div id="id_table_receipt_count_ap"></div>
	
</body>
</html>