<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: 'vat_invoice_contract',
		renderTo: 'id_table_container_vat_invoice_contract',
		width: '100%',
		height: globalClientHeight - 345,
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.invoice.VatInvoiceContract',
		//editFormPopupWindowWidth:600,
		//editFormPopupWindowHeight:200,
		remoteOper: buttonFlag == 'edit' ? true:false ,
		showPage: true,
		hiddenQueryArea : true,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		tools: ['add', '-', 'edit', '-', 'remove'],
		params:{invoiceid: invoiceid},
		xmlFileName: '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_contract.xml',
		afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
			if(operFlag == "edit"){
				var unregisteredamt = mini.getbyName("unregisteredamt").getValue();
				var registeredamt = mini.getbyName("registeredamt").getValue();
				mini.getbyName("contractnumber").disable();//.setReadOnly(true);
				mini.getbyName("unregisteredamt").setValue(decimal(Number(unregisteredamt) + Number(registeredamt), 0));
			}
		},
		//输入金额校验
		validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
			if('add'== editFormItemOperFlag || 'edit' == editFormItemOperFlag){
				var flag = true;
				var registeredAmt = parseFloat(mini.getbyName('registeredamt').getValue());
				var equipAmt = parseFloat(mini.getbyName('equipamt').getValue());
				var unregisteredamt = parseFloat(mini.getbyName('unregisteredamt').getValue());
				var invoicemoney = parseFloat(mini.getbyName('invoicemoney').getValue());
				if(isNaN(invoicemoney)){
					mini.alert('请首先登记发票信息');
					flag = false;
					return flag;
				}
				if(editFormItemOperFlag == "add"){
					var datas = miniTable.getData();
					var contractid = mini.getbyName('contractid').getValue();
					if(datas.length > 0){
						for(var i = 0; i< datas.length; i++){
							if(contractid == datas[i].contractid){
								mini.alert("该合同发票信息已登记，不可以重复登记，<br>请修改已登记的合同信息！");
								return false;
							}
						}
					}
				}
				if(registeredAmt > unregisteredamt){
					mini.alert('本次登记金额不能大于合同未登记金额，请重新输入！！');
					flag = false;
					return flag;
				}
				if(registeredAmt > invoicemoney){
					mini.alert('本次登记金额不能大于发票金额，请重新输入！！');
					flag = false;
					return flag;
				}
				miniForm.setData({unregisteredamt: decimal(Number(equipAmt) - Number(registeredAmt),2)},false);
				if('add'== editFormItemOperFlag ){
					var minitable = mini.get("vat_invoice_contract");
					var moneydatas = minitable.getData();
					var summary = 0.0;
					for(var i=0;i<moneydatas.length;i++){
						summary += parseFloat(moneydatas[i].registeredamt);
					}
					if((registeredAmt+summary)>invoicemoney){
						mini.alert("已登记金额大于发票金额");
						flag = false;
						return flag;
					}
					return true;
				} else {
					var param = {};
					var contractid = mini.getbyName('contractid').getValue();
					param.invoiceid = invoiceid;
					param.contractid = contractid;
					param['xmlFileName'] = "/eleasing/jsp/invoice_manage/vat_invoice/vat_invoiceamt_check.xml";
					ajaxRequest({
						params:param,
						url:'${pageContext.request.contextPath}/table/getTableData.action',
						method : 'POST',
						async : false,
						success:function(response){
							var jsondata = eval('(' + response.responseText + ')').datas;
							var totalamt = parseFloat(jsondata[0].totalamt);
							if(invoicemoney < (totalamt+registeredAmt) ){
								mini.alert('累计登记金额超过发票金额，请重新输入！！');
								flag = false;
							}else{
								flag = true;
							}
						},
						failure:function(response){
							mini.alert("校验失败！");
							flag = false;
						}
					});
					return flag;
				}
			}
		},
		columns: [
			{type: 'checkcolumn'},
			{field: 'id',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'invoiceid',visible: false,formEditorConfig: {value: invoiceid}},
			{
				field: 'contractidname',
				header : '合同号',
				formEditorConfig:{
					readOnly:true
				}
			},
			{
				field: 'contractid',
				header : '合同号',visible:false
			},
			{field: 'contractnumbername', header: '业务合同编号',visible: false,
				formEditorConfig:{
				fieldVisible: false
			}},
			{field: 'contractnumber', header: '业务合同编号',
				formEditorConfig : {
				type : 'queryinput',
				labelWidth:100,
				multiSelect : false,
				required: true,
				valueField : 'contractnumber',
				textField : 'contractnumber',
				onSelect: setContractInfo,
				params : {
					xmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_contractId.xml'
				}
				}
			},
			{field: 'projectname', header: '项目名称',
				formEditorConfig : {
					newLine: true,
					readonly : true
				}
			},
			{field: 'custname',header: '客户名称',
				formEditorConfig : {
					readonly : true
				}
			},
			{field: 'equipamt', header: '设备款',dataType:'currency',align: 'right',
				formEditorConfig : {
				fillFromFieldName : 'contractid',
				fillProperty : 'equipamt',
				newLine:true,
				readOnly : true
				}
			},
			{field: 'registeredamt', header: '本次登记金额',dataType:'currency',align: 'right',summary : true,
				formEditorConfig : {
				required: true,
				vtype: 'float'
				}
			},
			{field: 'unregisteredamt', header: '未登记金额',dataType:'currency',summary : true,align: 'right',
				formEditorConfig : {
					fieldVisible:true,
					readOnly : true,
					newLine : true
				}
			}
		]
	});
});

function setContractInfo($me, inputObj, rowData){
	mini.getbyName("projectname").setValue(rowData.projectname);
	mini.getbyName("custname").setValue(rowData.custname);
	mini.getbyName("equipamt").setValue(rowData.equipamt);
	mini.getbyName("registeredamt").setValue(rowData.equipamt);
	mini.getbyName("contractidname").setValue(rowData.contractid);
	mini.getbyName("contractid").setValue(rowData.cid);
	mini.getbyName("unregisteredamt").setValue(rowData.unregisteredamt);
}


</script>
<div id="id_table_container_vat_invoice_contract" style="height: 100%;"></div>
