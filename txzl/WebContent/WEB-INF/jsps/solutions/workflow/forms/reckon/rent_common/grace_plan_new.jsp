<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "grace_plan_new",
			renderTo: "id_grace_plan_new",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_grace_plan_new_str ? "[]" : json_grace_plan_new_str }'),
			tools : [ 'edit','-',{
				html : '更新现金流',
				plain : true,
				iconCls : 'icon-redo',
				handler : function(miniTable, buttonText) {
					if(miniTable.getData().length <= 0){
						mini.alert('请先进行租金测算，生成资金计划！！！！');
						return;
					}
					//判断设备款和首付款和商务报价中的设备款和首付款是否相同
					var firstFundRent = 0;
					var equipAmp = 0;
					var  fundData = mini.get("fund_fund_plan").getData();
					for(var i = 0 ;i<fundData.length;i++){
						var fund = fundData[i];
						if(fund.feetypename == '首付款'){
							firstFundRent += Number(fund.planmoney);
						}
					}
					var conditionequipAmp = getNumber('equipamt');
					var conditioneFirstPay = getNumber('firstpayment');
					if(conditioneFirstPay != firstFundRent){
						mini.alert('请保证资金计划中的首付款之和与商务报价中的首付款保持一致！！！！');
						return;
					}
					mini.mask({
						el: document.body,
						cls: 'mini-mask-loading',
						html: '正在更新资金计划中，请稍后...'
					});
					var o = {};
					o.docId = flowUnid;
					o.enddate = mini.get('enddate').getValue();
					o.startdate = mini.get('startdate').getFormValue();
					o.leaseamtdate=mini.get('leaseamtdate').getFormValue();
					o.projId = mini.get('conditionProjId').getValue();
					o.contractId = mini.get('conditionContractId').getValue();
					o.process = mini.get('reckonProcess').getValue();
					//资金收付计划由资金计划+宽限期收款计划合并得到
					//成功后要更改表单控件的值（资金计划+宽限期）
					var fundplan = mini.get("fund_fund_plan").getData();
					var graceplan = mini.get("grace_plan_new").getData();
					var newplan =[];
					for(var i=0;i<fundplan.length;i++){
						newplan.push(fundplan[i]);
					}
					for(var i=0;i<graceplan.length;i++){
						newplan.push(graceplan[i]);
					}
					o.json_fund_fund_charge_str = mini.encode(newplan);
					o.json_fund_rent_plan_str = mini.encode(mini.get('fund_rent_plan_frame').getData());
					o.json_fund_put_config_str = mini.encode(mini.get('fund_put_config').getData());
					 var url="<%=request.getContextPath() %>/leasing/updateFundFundPlan.action";
					 $.ajax({
				        url: url,
				        type: "post",
				        data:  o ,
				        success: function (text) {
				        	var result = mini.decode(text);
				        	if(result.updateinfo == '成功'){
				        		mini.alert('资金计划更新成功！！！');
				        		mini.get('irrshow').setValue(result.irr);
				        		mini.get('xirr').setValue(result.xirr);
				        		$('#irr').val(result.irr);
				        		var finacashdetail =mini.decode(result.fundcashdetail) ;
				        		mini.get("fund_cash_plan_frame").setData(finacashdetail);
				        		$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
				        		$("#id_json_fund_fund_charge_str").val(mini.encode(fundplan));
				        		$("#id_json_grace_plan_new_str").val(mini.encode(graceplan));
				        		mini.unmask(document.body);
				        	}else{
				        		mini.alert('资金计划更新失败！！！');
				        		mini.unmask(document.body);
				        	}
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	mini.unmask(document.body);
				            alert(jqXHR.responseText);
				        }
					  });
				}
			}],
			confirmCopyCallBack:function(miniTable,rows){
				var names = [];
				for(var i = 0 ; i<rows.length;i++){
					names.push(rows[i].feetypename);
				}
				names = unique(names);
				var newRows = [];
				var datas = miniTable.getData();
				for(var i = 0 ; i< names.length;i++){
					var temp = 0;
					var feetypename = names[i];
					for(var j = 0 ; j< datas.length;j++){
						var data = datas[j];
						if(data.feetypename == feetypename){
							if(Number(data.paymentid) > temp){
								temp = Number(data.paymentid);
							}
						}
					}
					for(var k = 0 ;k<rows.length;k++){
						var row = mini.clone(rows[k]);
						if(row.feetypename == feetypename){
							row.paymentid = ++temp;
							newRows.push(row);
						}
					} 
				}
			   miniTable.addRows(newRows, 0); 
			   miniTable.saveDataToInput();
			},
			removeOperCallBack:function(miniTable){
				$('#id_json_grace_plan_new_str').val(mini.encode(miniTable.getData()));
			},
			
			copyOperCallBack:function(miniTable){
				$('#id_json_grace_plan_new_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_grace_plan_new_str').val(mini.encode(miniTable.getData()));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},	
				{field: 'contractid', header: '合同号',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paymentid', header: '编号',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '计划收付日期',type:"date",format:"yyyy-MM-dd",
					formEditorConfig:
					{
						type:"date",format:"yyyy-MM-dd",
						newLine : true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'planmoney', header: '金额',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						vtype:'float',
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'feetype', header: '款项名称ID',visible: false},
				{field: 'feetypename', header: '款项名称',
					formEditorConfig:
					{
						readOnly:true,
						newLine : true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paytype', header: '收付方向id', visible: false},
				{field: 'paytypename', header: '收付方向',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'payconditionname', header: '支付条件',formEditorConfig:{
					readOnly:true,
					fieldVisible:false,
					allowInput:false,
					fillFromFieldName:'paycondition',
					fillProperty:'name'
				}},
				{field: 'paycondition', header: '支付条件', visible: false,
					formEditorConfig:
					{
						type:'combobox',
						newLine:true,
						readOnly:true,
						textField:'name',
						valueField:'value',
						allowInput:false,
						fieldVisible:true,
						params:{pid: 'pay_condition', xmlFileName:'combos/comboDict.xml'}
					}
				},
				{field: 'whetherdeduct', header: '是否抵扣', visible: false,
					formEditorConfig:
					{
						type:'combobox',
						labelWidth:100,
						readOnly:true,
						width: 200,
						textField:'name',
						valueField:'value',
						data:[{name:'是',value:'是'},{name:'否',value:'否'}]
					}
				},
				{field: 'paycust', header: '收付对象',
					visible: false,
			       formEditorConfig:{
				    	newLine:true,
				    	readOnly:true,
		                width: 400,
		                colspan:3,
		                type:'queryinput',
		             	required: false,
		            	textField: 'paycustname',
		           		valueField: 'paycust',
		           		allowInput: false,
		         		fieldVisible: true,
		             	/* onSelect:function($me, queryInput, rowData){
			                   mini.getbyName("paycustname").setValue(rowData.paycustname);
			                   mini.getbyName("paycust").setValue(rowData.paycust);
			            }, */
		               	params: {xmlFileName: '/eleasing/workflow/rent/rent_cal/fund_fund_pay_obj.xml'}
				  }
				},
				{field: 'paycustname', header: '收付对象',visible: true,
						formEditorConfig:{
			  			fieldVisible:false 
		             }
				},
				{field: 'fpnote', header: '备注',
					formEditorConfig:
					{
						type:'textarea',
						newLine:true,
						readOnly:true,
						labelWidth:100,
						height:70,
						colspan:3,
						width: 478
					}
				}
			]
		});
	});
});
</script>
<div id="id_grace_plan_new"></div>