<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var tools=[ 'edit','-','copy','-','remove','-'];
	var toolsnew={
		html : '生成费用表',
		plain : true,
		iconCls : 'icon-redo',
		handler : function(miniTable, buttonText) {
			var rentPlan=mini.get("fund_rent_plan_frame").getData();
			if(rentPlan.length <= 0){
				mini.alert('请先进行租金测算，生成资金计划！！！！');
				return;
			}
			var lastRent=rentPlan[rentPlan.length-1].rent;
			mini.mask({
				el: document.body,
				cls: 'mini-mask-loading',
				html: '正在更新资金计划中，请稍后...'
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
			o.lastRent = lastRent;
			o.enddate = mini.get('enddate').getValue();
			o.startdate = mini.get('startdate').getFormValue();
			o.projId = mini.get('conditionProjId').getValue();
			o.contractId = mini.get('conditionContractId').getValue();
			o.process = mini.get('reckonProcess').getValue();
			o.json_fund_fund_charge_str = mini.encode(miniTable.getData());
			o.json_fund_rent_plan_str = mini.encode(mini.get('fund_rent_plan_frame').getData());
			 var url="<%=request.getContextPath() %>/leasing/updateFundFundPlan.action";
			 $.ajax({
		        url: url,
		        type: "post",
		        data:  o ,
		        success: function (text) {
		        	var result = mini.decode(text);
		        	if(result.updateinfo == '成功'){
		        		mini.alert('资金计划更新成功！！！');
		        		var fundchargeplan =  result.fundchargeplan;
		        		mini.get("cautionmoneyremain").setValue(result.cautionmoneyremain);
		        		mini.get("cautiondeductionmoney").setValue(result.cautiondeductionmoney);
		        		mini.get("cleancreditmoney").setValue(result.cleancreditmoney);//风险敞口
		        		mini.get("cleancreditratio").setValue(decimal(result.cleancreditmoney/mini.get("equipamt").getValue()*100,6));//净授信比例
		        		mini.get("fund_fund_plan").setData(fundchargeplan);
		        		$("#id_json_fund_fund_charge_str").val(mini.encode(fundchargeplan));
						//更新租金计划表中最后一期的实际还款金额  没有涉及到数据库级别的操作
		        		var fundrentplan=mini.get("fund_rent_plan_frame").getData();
		        		fundrentplan[fundrentplan.length-1].actualrent=decimal(fundrentplan[fundrentplan.length-1].rent-result.cautiondeductionmoney,2);
		        		mini.get("fund_rent_plan_frame").setData(fundrentplan);
		        		$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
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
	};
	tools.push(toolsnew);
	var put={
			html : '抵扣',
			plain : true,
			iconCls : 'icon-redo',
			handler : function(miniTable, buttonText) {
				debugger;
				   var pays = miniTable.getSelecteds();
				   for (var j = 0 ;j <pays.length; j++ ){
					   //选择非设备款的收款进行抵扣
						if(pays[j].paytype=='pay_type_out'){
							mini.alert("请正确选择货扣的资金！！！");
							return false;
						}
					}
				   setDeduceFundPutPlanPut(pays);
				   setDeduceFundPutPlanFund(pays);
				   mini.alert("抵扣成功，请到本次投放明细查看！！！");
				}
		};
	if('${param.isput}' == 'true'){
		tools.push(put);
	};
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	if('${param.isOnhire}' == 'true' && isViewHistoryTask != true){showTools = true;};
	if('${param.isContractApp}' == 'true' && isViewHistoryTask != true){showTools = true;};
	if('${param.isRentChange}' == 'true' || isViewHistoryTask ==true){
		//租金计划变更里面只有 生成费用表按钮
		var toolschange=[];
		toolschange.push(tools[6]);
		tools=toolschange;
	};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_fund_plan",
			renderTo: "id_fund_fund_plan",
			width: globalClientWidth - 30,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_fund_fund_charge_str ? "[]" : json_fund_fund_charge_str }'),
			tools : tools,
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
				miniTable.addRows(newRows);
			},
			removeOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
			},
			
			copyOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));
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
						required: true,
						labelWidth:100,
						width: 200
					}
				},
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
				{field: 'paycust', header: '收付对象',
					visible: false,
			       formEditorConfig:{
				    	newLine:true,
		                width: 200,
		                type:'queryinput',
		             	required: true,
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

function unique(arr){
	var ret = [];
	var have = {};
	for(var i = 0 ; i< arr.length; i++){
		var item = arr[i];
		var key = typeof(item)+item;
		if(have[key] != 1){
			ret.push(item);
			have[key] = 1;
		}
	}
	return ret;
}

function  setDeduceFundPutPlanPut(pays)
{
	debugger;
	var miniTable=mini.get("fund_guarance_plan");
	var newRow = {};
	var planGrid = pays;//获取资金计划grid
	var currentGrid = mini.get("put_current");//获取本次计划grid
	var rowDatas =currentGrid.getData();
	var alldemoney=0;
	for (var i = 0 ;i <planGrid.length; i++ )
	{
		if(parseFloat(planGrid[i].planmoney)>0){
			
			newRow.paymentid=planGrid[i].paymentid;
// 			newRow.feetype=planGrid[i].feetype;
// 			newRow.feetypename=planGrid[i].feetypename;
// 			newRow.paytype='pay_type_in';
// 			newRow.paytypename='收款';
			newRow.feetype="feetype10";
			newRow.feetypename="设备款";
			newRow.settlemethod='payfund11';
			newRow.settlemethodname='货扣';
			newRow.paytype='pay_type_out';
			newRow.paytypename='付款';
			
			newRow.factdate=mini.formatDate(new Date(),"yyyy-MM-dd");
			newRow.feeadjust='0';
			newRow.ffcmemo=planGrid[i].feetypename+"抵扣";
			newRow.fundfundchargeplan=planGrid[i].feetype;//保存抵扣费用类型
			newRow.factmoney=planGrid[i].planmoney;
			
			rowDatas.push(mini.clone(newRow));
			alldemoney=parseFloat(alldemoney)+parseFloat(planGrid[i].planmoney);
		}
	}
	for (var j = 0 ;j <rowDatas.length; j++ ){
		if(rowDatas[j].settlemethod=="payfund6"){
			rowDatas[j].factmoney=parseFloat(rowDatas[j].factmoney)-parseFloat(alldemoney);
		}
	}
	currentGrid.setData(rowDatas);
	//currentGrid.addRows ( rowDatas, 0 );
	currentGrid.saveDataToInput();
}

function  setDeduceFundPutPlanFund(pays)
{
	debugger;
	var newRow = {};
	var planGrid = pays;//获取资金计划grid
	var currentGrid = mini.get("caution_money_refund_detail");//获取本资金抵扣的grid
	var rowDatas =[];
	for (var i = 0 ;i <planGrid.length; i++ )
	{
		if(parseFloat(planGrid[i].planmoney)>0){
			
			newRow.paymentid=planGrid[i].paymentid;
			newRow.feetype=planGrid[i].feetype;
			newRow.feetypename=planGrid[i].feetypename;
			newRow.paytype='pay_type_in';
			newRow.paytypename='收款';
			newRow.settlemethod='payfund11';
			newRow.settlemethodname='货扣';
			
			newRow.factdate=mini.formatDate(new Date(),"yyyy-MM-dd");
			newRow.feeadjust='0';
			newRow.ffcmemo=planGrid[i].feetypename+"抵扣";
			newRow.fundfundchargeplan=planGrid[i].feetype;//保存抵扣费用类型
			newRow.factmoney=planGrid[i].planmoney;
			rowDatas.push(mini.clone(newRow));
		}
	}
	currentGrid.setData(rowDatas);
	//currentGrid.addRows ( rowDatas, 0 );
	currentGrid.saveDataToInput();
}
</script>
<div id="id_fund_fund_plan"></div>