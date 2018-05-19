<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>付款统计表</title>
<script type="text/javascript">
    var didate="${requestScope['didate']}";
    var tuid="${requestScope['tuid']}";
    var diid="${requestScope['id']}";
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
	var currentClientWidth = document.documentElement.clientWidth-1;
	jQuery(function() {		
		var paramid = "${param.id}";
		tenwa.createTable({
					id : "payment_count_ap",
					renderTo : "id_table_payment_count_ap",
					width : currentClientWidth,
					height :305,
					lazyLoad : false,
					title : "付款统计",
					remoteOper : false,
					tools : ['add','-','edit','-','remove','-','importExcel','-','exportExcel'],
					queryButtonColSpan : 2,
					remoteOper:true,
					allowCellWrap:true,
					lazyLoad : false,
					loadMode:'ajax',
					showPager : true,
					pageSize : 10,
					entityClassName:'com.tenwa.leasing.entity.finacial.PaymentCountDetailAP',
					xmlFileName : '/eleasing/jsp/finacial/fund_plan/fund_plan_payment_count_ap.xml',
					params:{reportid:paramid},
					columns : [
                              {field : 'id',header : 'id',visible: false,
	                           formEditorConfig:{
		                           fieldVisible: false,
	                             }	
                              }, 
                              {
									type : 'indexcolumn'
								},
							{type : 'checkcolumn'},
							{field : 'fundplanno',header : '序列',visible: false,
								formEditorConfig:{
									 fieldVisible:false,
									type:'text'
								}
							},
							{field : 'paymentcount',header : '付款统计',
								formEditorConfig:{
									type:'text'
								}
							},
							
							{field : 'amount',header : '金额',width : 60,	summary:true,align:'right',dataType :"currency",currencyUnit:"￥",
								formEditorConfig:{
									 vtype:'float',
									type:'text'
								}
							},
							{
								field : 'paymenttime',
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
							{field : 'supportunit',header : '供应商名称',
								formEditorConfig:{
									type:'text'
								}
							},
							{
								field : 'planname',
								header : '付款类型',
								visible : true,
								formEditorConfig : {
									fillFromFieldName:'plannameid',
						            fillProperty:'name',
									fieldVisible:false
								}
							},
							{
								field : 'plannameid',
								header : '付款类型',
								visible : false,
								formEditorConfig : {
									newLine : true,
									 fieldVisible:true,
									type : 'queryinput',
									textField : 'name',
									showNullItem : "true",
									valueField : 'value',
									params : {
										pid : 'fund_plan_out',
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
									data:[{text:'付款'}, {text:'资金上存'},{text:'内部账户转款(转出)'}, {text:'外部还款'}, {text:'还集团借款'}]
								}
							},
							
							{field : 'silver',header : '银承',
								formEditorConfig:{
									newLine:true,
									type:'text'
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
	<div id="id_table_payment_count_ap"></div>
	
</body>
</html>