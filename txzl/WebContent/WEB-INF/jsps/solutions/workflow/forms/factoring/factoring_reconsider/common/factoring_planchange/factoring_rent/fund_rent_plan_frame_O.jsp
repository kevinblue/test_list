<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:"fund_rent_plan_1",
			renderTo:"id_fund_rent_plan_frame_1",
			width:globalClientWidth - 30,
			height:400,
			lazyLoad:false,
			isClickLoad:false,
			remoteOper :false,
			showPager:false,
			multiSelect:true,
			showToolbar:showTools,
			data:mini.decode('${empty json_fund_rent_plan_str ? "[]" :json_fund_rent_plan_str}'),
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					var leaseamtdate = mini.get('leaseamtdate').getValue();
					var startrentlist = mini.getbyName("rentlist");
					var row = miniTable.getRow(miniTable.getData().length-1);
					if(null != row){
						startrentlist.setValue(parseInt(row.rentlist)+1);
						if(leaseamtdate){
							var plandate = mini.getbyName("plandate");
							plandate.setValue(getLastPlanDate());
						}
					}else{
						startrentlist.setValue(1);
						if(leaseamtdate){
							var plandate = mini.getbyName("plandate");
							plandate.setValue(leaseamtdate);
						}
					}
					startrentlist.setEnabled(false);
				}
			},
			validateForm:function(miniTable, miniForm){
				var datas = miniTable.getData();
				var plandate = mini.getbyName("plandate").getValue();
				var leaseamtdate = mini.get('leaseamtdate').getValue()
				if(datas.length == 0){
					if(!leaseamtdate){
						mini.get('leaseamtdate').setValue(plandate);
					}
				}else{
					if(leaseamtdate && (plandate < leaseamtdate || plandate > getLastPlanDate())){
						mini.alert('日期不能小于应收账款转让日，且不能大于，规定期限日！');
						return false;
					}
				}
				var currentcorpus =  Number(!(mini.getbyName("corpusbusiness").getValue()) ? 0 : mini.getbyName("corpusbusiness").getValue());
				var currentInterest = Number(!(mini.getbyName("interestbusiness").getValue()) ? 0 : mini.getbyName("interestbusiness").getValue());
				//商务条件中保理费收入与保理明细中保理费收入之和相等 商务条件中应收账款受让款等于保理明细中的应收账款受让款相等
				for(var i = 0 ; i < datas.length ;i++){
					currentInterest += Number(!datas[i].currentInterest ? 0 : datas[i].currentInterest);
					currentcorpus += Number(!datas[i].currentcorpus ? 0 : datas[i].currentcorpus);
				}
				mini.get('factoringincome').setValue(currentInterest);
				mini.get('factoringpayout').setValue(currentcorpus);
				$setThouValue("factoringincome");
				$setThouValue("factoringpayout");
				var ratiovalueIncome = getRadio(currentInterest);
				var ratiovaluepayout = getRadio(currentcorpus);
				mini.get('factoringpayoutratio').setValue(ratiovaluepayout);
				mini.get('factoringincomeratio').setValue(ratiovalueIncome);
				mini.getbyName('rent').setValue( Number(!(mini.getbyName("corpusbusiness").getValue()) ? 0 : mini.getbyName("corpusbusiness").getValue()) + Number(!(mini.getbyName("interestbusiness").getValue()) ? 0 : mini.getbyName("interestbusiness").getValue()));
				return true;
			},
			confirmRemoveCallBack:function(miniTable){	
				var datas = miniTable.getData();
				var selectedRowDatas = miniTable.getSelecteds();
				var qixiang=datas[datas.length-1].rentlist;
				var strmax=0;
				var strmin=selectedRowDatas[0].rentlist;
				for(var i=0;i<selectedRowDatas.length;i++){
					if(selectedRowDatas[i].rentlist>=strmax){
						strmax=selectedRowDatas[i].rentlist;
					}
					if(selectedRowDatas[i].rentlist<=strmin){
						strmin=selectedRowDatas[i].rentlist;
					}
					
				}
				if(strmax!=qixiang){
					mini.alert("租金计划应该从最后一项开始删除，请重新选择！");
					return false;
				}if(parseInt(strmax)-parseInt(strmin)+1!=selectedRowDatas.length){
					mini.alert("租金计划应该从最后一项开始删除，期项必须是连续的，请重新选择！");
					return false;
				}
				return true;
			},
			addOperCallBack : function (miniTable,rowData){
				calFacIncome(miniTable);
				$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
			},
			removeOperCallBack:function(miniTable){
				if(!miniTable.getData()){
					mini.get('factoringincome').setValue(0);
					mini.get('factoringpayout').setValue(0);
				}
				calFacIncome(miniTable);
				$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
			},
			copyOperCallBack:function(miniTable){
				calFacIncome(miniTable);
				$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				calFacIncome(miniTable);
				$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
			},
			columns:[
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field:'rentlist', header:'期项',formEditorConfig : 
					{
						readOnly : true,
						required: true,width:180,
						type: 'int',
						vtype: 'int'
					}
				},
				{field:'plandate', header:'日期',
					formEditorConfig : 
					{
						required: true,width:180,
						type: 'date',
						vtype: 'date',
						format: 'yyyy-MM-dd'
					}
				},
				{field: 'rent', header: '租金',visible: false,
					formEditorConfig:
					{
						fieldVisible : false,
					}
				},
				
				{field:'corpusbusiness', header:'收回应收账款',dataType :"currency",summary :true,align:'right',
					formEditorConfig : 
					{
						newLine:true,
						width:180,
						type: 'float',
						vtype: 'float',
						format: 'yyyy-MM-dd'
					}	
				},
				{field:'interestbusiness', header:'保理费收入',dataType :"currency",summary :true,align:'right',
					formEditorConfig : 
					{
						width:180,
						type: 'float',
						vtype: 'float',
					}	
				},
				{field:'cautionmoneyremain', header:'保证金退还',dataType :"currency",summary :true,align:'right',
					formEditorConfig : 
					{
						newLine:true,
						width:180,
						type: 'float',
						vtype: 'float'
					}	
				}
			]
		});
	});
	
});
function calFacIncome(miniTable){
	var datas = miniTable.getData();
	var interestbusiness = 0; 
	var corpusbusiness =0;
	for(var i=0;i<datas.length;i++){
		interestbusiness += Number(datas[i].interestbusiness);
		corpusbusiness += Number(datas[i].corpusbusiness);
	}
	if(interestbusiness > 0 ){
		mini.get('factoringincome').setValue(formatNumberThousand(interestbusiness));
	}
	if(corpusbusiness > 0){
		mini.get('factoringpayout').setValue(formatNumberThousand(corpusbusiness));
	}
	var ratiovalueIncome = getRadio(interestbusiness);
	var ratiovaluepayout = getRadio(corpusbusiness);
	mini.get('factoringpayoutratio').setValue(ratiovaluepayout);
	mini.get('factoringincomeratio').setValue(ratiovalueIncome);
	calSalesvolume();
	calActualfund();
}
</script>
<div id="id_fund_rent_plan_frame_1"></div>