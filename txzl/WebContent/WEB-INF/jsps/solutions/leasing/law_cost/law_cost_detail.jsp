<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>本方信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
var lawid = '${param.lawid}';
jQuery(function(){
	tenwa.createTable({
		id: "lawcost_detail",
		renderTo: "id_table_container_lawcost_detail",
		width: '100%',
		height: '100%',
		entityClassName : 'com.tenwa.leasing.entity.lawmng.LawCost',
		title: "费用详情信息",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		lazyLoad:false,
		editFormPopupWindowWidth:500,
		tools: ['add','-','edit','-','remove'],
		xmlFileName: '/eleasing/jsp/law_mng/law_cost_detail_list.xml',
		params:{lawid:lawid},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'lawid', header: 'lawid',
				formEditorConfig:{
					lableWidth:20,
					value:lawid
				}
			,visible:false
			},
			{field:'feetypename', header:'费用类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'feetype',
			          fillProperty:'name',
			          entityClassName:'com.tenwa.business.entity.DictionaryData',	
			 entityHeaderFieldName:'name'}},
			       {field:'feetype', header:'费用类型', visible:false,align:'left',
				  formEditorConfig:{
					           type:'combobox',
					       required:true,
					      textField:'name',
					     valueField:'value',
					   fieldVisible:true,
					params:{pid: 'lawtype', xmlFileName:'combos/comboDict.xml'}}},
			{field: 'lawdate', header:'支付日期',dateFormat:"yyyy-MM-dd",
					formEditorConfig:{
					type:'date',
					vtype:'date',
					format:'yyyy-MM-dd',
					allowInput:false}},
			{field: 'lawmoney', header: '金额',formEditorConfig:{newLine:true}},
			{field: 'istrasfer', header: '是否票据移交',formEditorConfig:{
				type:'combobox',
				data:[{text:'是'},{text:'否'}],
				valueField:'text',
				textField:'text'
			}},
			{field: 'memo', header: '通讯地址',formEditorConfig:{fieldVisible:false,newLine:true}},
			{field: 'transferdate', header: '票据移交日期',
				formEditorConfig:{
	                  type:'date',
	                 vtype:'date',
	                format:'yyyy-MM-dd',
	            allowInput:false}
			},
			{field: 'mineafford', header: '我司承担'},
			{field: 'oppositeafford', header: '对方承担',formEditorConfig:{newLine:true}},
			{field: 'oppositepayed', header: '对方实付'},
			{field: 'courtbackdate', header: '法院退费日',
			formEditorConfig:{
				newLine:true,
				type:'date',
				vtype:'date',
				format:'yyyy-MM-dd',
				allowInput:false}
			},
			{field: 'courtbackamt', header: '退费金额'},
			{field: 'courtbackplan', header: '法院应退',formEditorConfig:{newLine:true}},
			{field:'paytypename', header:'支付类型', 
			      formEditorConfig:{
			          fieldVisible:false,
			     fillFromFieldName:'paytype',
			          fillProperty:'name',
			          entityClassName:'com.tenwa.business.entity.DictionaryData',	
			 entityHeaderFieldName:'name'}},
			       {field:'paytype', header:'支付类型', visible:false,align:'left',
				  formEditorConfig:{
					           type:'combobox',
					       required:true,
					      textField:'name',
					     valueField:'value',
					   fieldVisible:true,
					params:{pid: 'PayFund', xmlFileName:'combos/comboDict.xml'}}}
		]
	})
});
</script>
</head>
<body>
<div id="id_table_container_lawcost_detail" style="height: 100%;"></div>
</body>
</html>