<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//催收记录
jQuery(function() {
// 	var recordData;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id2',
			width : globalClientWidth,
			height : screenHeight - topHeight,
			iconCls : 'icon-node',
			frozenStartColumn:0,
			frozenEndColumn:1,
			hiddenQueryArea : true,
			frozenStartColumn : 1,
			frozenEndColumn : 3,
			renderTo:'content_table_id2',
			editFormPopupWindowWidth : 700,
// 			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
// 				if("add" == operFlag){
// 					if(recordData){
// 			            miniForm.setData(recordData);
// 					}else{
// 						$.ajax({
// 					        url: urlPrefix+"/eleasing/jsp/fund/fund_overdue/get_cust_by_contractid.xml",
// 					        type: "post",
// 					        cache: false, 
// 					        async : false,
// 					        data :{"contractid":contractid},
// 					        success: function (text) {
// 					        	var result = mini.decode(text);
// 					            var data = result.datas[0];
// 					            var formData = miniForm.getData();
// 					            $.extend(formData,mini.decode(data));
// 					            miniForm.setData(formData);
// 					            recordData = formData;
// 					        }
// 					    });
// 					}
// 				}
// 			},
			remoteOper : true,
			entityClassName : 'com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord',//本table对应的实体类，存储数据时按该类型存储
			pageSize : 10,
			showPager : true,
			lazyLoad : false,
			
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml',
			params : {
				contractid:contractid
			},
			tools : [ 'add', '-', 'edit', '-', 'remove'],
			columns : [ { 
				type : 'indexcolumn'
			},
			{type : 'checkcolumn'} , {
				field : 'id',
				header : '记录编号',
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				visible : false,
				formEditorConfig : {
					readOnly : true,
					fieldVisible : false
				}
			}, {
				field : 'custid',
				header : 'ID',
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				visible : false,
				formEditorConfig : {
					readOnly : true,
					fieldVisible : false
				}
			},{
				field : 'custname',
				header : '客户名',
				headerAlign : 'center',
				width : 100,
				visible : false,
				formEditorConfig : {
					type : 'text',
					fieldVisible : true,
					width : 200,
					labelWidth : 100,
					readonly :true
				}
			},{
				field : 'overduedunninginfoname',
				header : '催款员',
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				formEditorConfig : {
					type : 'text',
					fieldVisible : true,
					width : 200,
					labelWidth : 100,
					readonly :true
				}
			},
			{field: 'contractidname', header: '业务合同号', visible: true,queryConfig : {  
				labelWidth : 100,
   				width :200
			},
			formEditorConfig : {
				fieldVisible : false 
			}},
			{field: 'contractid', header: '合同号', visible: false,formEditorConfig:{
				newLine:true,
				width: 200,
				type : 'queryinput',
				required: true,
				textField: 'contractnumber',
				valueField: 'contractid',
				allowInput: false,
				fieldVisible: true,
				onSelect : function($me, queryInput, rowData){
					mini.getbyName("custname").setValue(rowData.custname);
					mini.getbyName("overduedunninginfoname").setValue(rowData.rawValue_partdept);
				},
				params: {
					custid: custid,
					xmlFileName: '/eleasing/jsp/fund/fund_overdue/get_contract_list_by_custid.xml'
				}
				}
			},{
				field : 'contactdate',
				header : '联系日期',
				headerAlign : 'center',
				width : 100,
				dateFormat : "yyyy-MM-dd",
				formEditorConfig : {
					type : 'date',
					width : 200,
					labelWidth : 100,
					allowInput : "false",
					format : 'yyyy-MM-dd',//若showTime=false将会出现2104-02-03 00:00:00的情况
					defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
				}
			},{field: 'rawValue_contactway', header: '联系方式', 
				formEditorConfig:{
					fieldVisible: false,
					fillFromFieldName : 'contactway',
					fillProperty : 'name'
				}
			},{
				field : 'contactway',
				header : '联系方式',
				visible : false,
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				
				formEditorConfig : {
					newLine :true,
					labelWidth : 100,
					width : 200,
					fieldVisible: true,
					showNullItem:"true", 
					nullItemText:"",
					emptyText:"",
					type : 'combobox',
					params: {
						pid: 'visit_mode',
						xmlFileName: '/combos/comboDict.xml'
					},
					textField: 'name',
					valueField: 'value'
				}
			},{
				field : 'commitmentmoney',
				header : '承诺还款金额',
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				formEditorConfig : {
					type : 'text',
					fieldVisible : true,
					width : 200,
					labelWidth : 100
				}
			},{
				field : 'commitmentdate',
				header : '承诺还款日',
				headerAlign : 'center',
				width : 100,
				dateFormat : "yyyy-MM-dd",
				formEditorConfig : {
					newLine : true,
					type : 'date',
					width : 200,
					labelWidth : 100,
					allowInput : "false",
					format : 'yyyy-MM-dd'//若showTime=false将会出现2104-02-03 00:00:00的情况
				}
			},{
				field : 'nextcommitmentdate',
				header : '下次联系日期',
				headerAlign : 'center',
				width : 100,
				dateFormat : "yyyy-MM-dd",
				formEditorConfig : {
					type : 'date',
					width : 200,
					labelWidth : 100,
					allowInput : "false",
					format : 'yyyy-MM-dd'//若showTime=false将会出现2104-02-03 00:00:00的情况
				}
			},{
				field : 'commitmentinfo',
				header : '联系情况',
				headerAlign : 'center',
				width : 100,
				allowSort : true,
				formEditorConfig : {
					newLine : true,
					colspan:3,
					type : 'textarea',
					fieldVisible : true,
					width : "100%"
				}
			}]
		});
	});
});


</script>
<div id='content_table_id2'></div>