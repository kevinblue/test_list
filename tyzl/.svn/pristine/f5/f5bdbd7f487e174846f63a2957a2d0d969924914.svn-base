<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>保险覆盖融资期限</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var contractid = "<mini:param  name='contractid'/>";
var contractnumber = "<mini:param  name='contractnumber'/>";
var screenHeight = window.screen.height;
//合同信息
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
        var userEnabled = [ {id : '0',text : '是'}, {id : '1',text : '否'}];
  	    var getUserEnabled = function(e) {
  					var code = e.record.isinsure;
  					for (var i = 0; i < userEnabled.length; i++) {  						
  						if (userEnabled[i]['id'] == code) {
  							return userEnabled[i]['text'];
  						}
  					}
  					return '';
  				};
		
		new ApTable({
			id:'table_insurance_info_list_id',
			renderTo:"insurance_info_list",
			width:"100%",
			height:300,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			queryButtonColSpan:4,
			queryButtonNewLine:false,
			editFormPopupWindowWidth:800,
			editFormPopupWindowHeight:450,
			remoteOper:true,
			pageSize:15,
			showPager:true,
			lazyLoad:false,			
			xmlFileName:'/eleasing/jsp/insure_manage/insurance_info_list.xml',
			params:{contractnumber :contractnumber},			
			columns:[ 
			    {type:'indexcolumn'},
			   	{type:'checkcolumn'}, 
			    {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false},
				{field: 'contractidname_', header: '合同号', visible: true,width:150},
				{field: 'contractnumber', header: '业务合同号', visible: true},
				{field: 'contractid', header: '合同号', visible: false},
			    {field:'custname',header:'客户名',headerAlign:'center',visible: true,width:150},  					   	
		   	    {field:'isinsure',header:'是否投保',headerAlign:'center',visible: true,width:100, renderer:getUserEnabled},					   	
		   	    {field:'insurancename',header:'保险公司',headerAlign:'center',visible: true,width:100},
		   	    {field:'insuranceid',header:'保险单号',headerAlign:'center',visible: true,width:100},
		   	    {field:'isurancetype',header:'险种',headerAlign:'center',visible: true,width:100},					   	
		      	{field:'insurerstartdate',header:'保险生效日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd"},
		     	{field:'insurerenddate',header:'保险终止日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd"},
			    {field:'firstbeneficiary',header:'第一受益人',headerAlign:'center',visible: true,width:100},
		        {field:'insurancepremium',header:'保险费',headerAlign:'center',visible: true,width:100},					   
		        {field:'insurancepremiumrate',header:'保险费率',headerAlign:'center',visible: true,width:100},
		        {field:'insurancemoney',header:'保险金额',headerAlign:'center',visible: true,width:100},					   
		     	{field:'insurergetdate',header:'收保单日期',dateFormat:"yyyy-MM-dd"},
		        {field:'surrenderobject',header:'退保对象',headerAlign:'center',visible: true,width:100},
		        {field:'surrendermoney',header:'退保金额',headerAlign:'center',visible: true,width:100},
		        {field:'leasedproperty',header:'租赁物件',headerAlign:'center',visible: true,width:100},
		        {field:'surrenderlog',header:'退保日志',headerAlign:'center',visible: true,width:100},
		        {field:'memo',header:'备注',headerAlign:'center',visible: true,width:100}					    
		    ]
		});
		
		
		//处理记录
		new ApTable({
			id:'table_insurance_cover_list_id',
			width:"100%",
			height:300,
			iconCls:'icon-node',
			renderTo:'insurance_cover_list',
			editFormPopupWindowWidth:700,
			remoteOper:true,
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			params:{contractid :contractid},
			entityClassName:'com.tenwa.leasing.entity.insurance.InsuranceCover',
			xmlFileName:'/eleasing/jsp/insure_manage/get_insurance_cover_by_contractid.xml',
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[ 
				    {type:'indexcolumn'},
				    {type:'checkcolumn'}, 
				    {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false}},
				   {field:'contractid',header:'合同主键ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				              formEditorConfig:{
					                  readOnly:true,
					              fieldVisible:false,
					                     value:contractid}},
			       {field:'rawValue_handlestatus', header: '处理类型', 
				              formEditorConfig:{
					              fieldVisible:false,
					         fillFromFieldName:'handlestatus',
					              fillProperty:'name'}},
				   {field:'handlestatus',header:'处理类型',visible:false,headerAlign:'center',width:100,allowSort:true,			
				              formEditorConfig:{
					                     width:200,
					                   newLine:true,
					              fieldVisible:true,
					              showNullItem:"true", 
					              nullItemText:"",
					                 emptyText:"",
					                      type:'combobox',
					                    params:{pid: 'hander_type',xmlFileName: '/combos/comboDict.xml'},
					                 textField: 'name',
					                valueField: 'value'}},
					{field:'handledate',header:'处理日期',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
				              formEditorConfig:{
					                   newLine:true,
					                      type:'date',
					                     width:200,
					                labelWidth:100,
					                allowInput:"false",
					                    format:'yyyy-MM-dd',
					              defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd")}},
			        {field:'handleresult',header:'处理结果',headerAlign:'center',width:100,allowSort:true,
				              formEditorConfig:{
                                         width:"100%",
					                   newLine:true,
					                   colspan:3,
					                      type:'textarea',
					              fieldVisible:true
				}
			}]
		});
	});
});

</script>
</head>
<body style="overflow: auto">
<div class="mini-panel" title="保险清单" showCollapseButton="true" style="width: 100%;">
	<div id="insurance_info_list"></div>
</div>
<div class="mini-panel" title="处理记录" showCollapseButton="true" style="width: 100%;">
	<div id="insurance_cover_list"></div>
</div>
</body>
</html>
