<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
	if('${param.isOnhire}' == 'true' && isViewHistoryTask != true){showTools = true;};
	if('${param.isContractApp}' == 'true' && isViewHistoryTask != true){showTools = true;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_cash_plan_frame",
			renderTo: "id_fund_cash_plan_frame",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showToolbar: showTools,
			showPager: false,
			multiSelect: true,
			data: mini.decode('${empty json_fund_cash_flow_str ? "[]" : json_fund_cash_flow_str	 }'),
			tools : [{
				html : '更新现金流',
				plain : true,
				iconCls : 'icon-redo',
				handler : function(miniTable, buttonText) {
					var rentPlan=mini.get("fund_rent_plan_frame").getData();
					var fundPlan=mini.get("fund_fund_plan").getData();
					if(rentPlan.length <= 0||fundPlan.length <= 0){
						mini.alert('请先生成租金计划、资金计划！！！！');
						return;
					}
					//判断设备款和首付款和商务报价中的设备款和首付款是否相同
					var firstFundRent = 0;
					var equipAmp = 0;
					for(var i = 0 ;i<fundPlan.length;i++){
						var fund = fundPlan[i];
						if(fund.feetypename == '设备款'){
							equipAmp += Number(fund.planmoney);
						}
						if(fund.feetypename == '首付款'){
							firstFundRent += Number(fund.planmoney);
						}
					}
					var conditionequipAmp = getNumber('equipamt');
					var conditioneFirstPay = getNumber('firstpayment');
					if(conditionequipAmp != equipAmp){
						mini.alert('请保证资金计划中的设备款之和与商务报价中的设备款保持一致！！！！');
						return;
					}
					if(conditioneFirstPay != firstFundRent){
						mini.alert('请保证资金计划中的首付款之和与商务报价中的首付款保持一致！！！！');
						return;
					}
					mini.mask({
						el: document.body,
						cls: 'mini-mask-loading',
						html: '正在生成现金流，请稍后...'
					});
					var o = businessForm.getData(true,true);
					var fields = businessForm.getFields();
					var resultData = [];
					$.extend(resultData,fields);
					for(var index =0;index<fields.length;index++){
						 //判断是否是下拉框控件，是则同时把text属性设置combobox
						if(fields[index].uiCls == "mini-textbox"){
							if(fields[index].getInputText().indexOf(',')!=-1){
								//所有textbox去掉逗号,
								resultData[index].setValue(fields[index].getInputText().replace(/,/g,""));
							}else if(!fields[index].getInputText() && (fields[index].vtype == 'double' || fields[index].vtype == 'int')){
								resultData[index].setValue('0');
							}
						}
					}
				    o = businessForm.getData(true,true);//把textbox去掉,逗号之后，再次获取form
					o.docId = flowUnid;
					o.enddate = mini.get('enddate').getValue();
					o.startdate = mini.get('startdate').getFormValue();
					o.projId = mini.get('conditionProjId').getValue();
					o.contractId = mini.get('conditionContractId').getValue();
					o.process = mini.get('reckonProcess').getValue();
					o.json_fund_rent_plan_str=mini.encode(mini.get("fund_rent_plan_frame").getData());
					o.json_fund_fund_charge_str=mini.encode(mini.get("fund_fund_plan").getData());
					o.json_special_regular_str=mini.encode(mini.get("special_regular").getData());
					 var url="<%=request.getContextPath() %>/leasing/updateCashFlow.action";
					 $.ajax({
				        url: url,
				        type: "post",
				        data:  o ,
				        success: function (text) {
				        	var result = mini.decode(text);
				        	if(result.updateinfo == '成功'){
				        		mini.alert('现金流生成成功！！！');
				        		mini.get('irrshow').setValue(result.irr);
				        		$('#irr').val(result.irr);
				        		mini.get("grossprofit").setValue(result.grossprofit);
				        		var cashDetailList =mini.decode(result.cashdetaillist) ;
				        		mini.get("fund_cash_plan_frame").setData(cashDetailList);
				        		$("#id_json_fund_cash_flow_str").val(mini.encode(cashDetailList));
				        		mini.unmask(document.body);
				        	}else{
				        		mini.alert('现金流生成失败！！！');
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
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'plandate', header: '计划日期'},
				{field: 'fundin', header: '流入量',dataType : "currency",summary : true,align:'right',},
				{field: 'fundindetails', header: '流入量清单',width:350},
				{field: 'fundout', header: '流出量',dataType : "currency",summary : true,align:'right'},
				{field: 'fundoutdetails', header: '流出量清单',width:350},
				{field: 'netflow', header: '净流量',dataType : "currency",summary : true,align:'right'}
			]
		});
	});
});
</script>
<div id="id_fund_cash_plan_frame"></div>