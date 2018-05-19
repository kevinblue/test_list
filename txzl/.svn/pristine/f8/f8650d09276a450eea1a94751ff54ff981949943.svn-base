<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 var tabledate=info.tabledate;
	 mini.alert(info.message);
	 if(""!=tabledate){
	    var grid=mini.get(tableid);
       grid.set({data:mini.decode(tabledate)});
	 }
    mini.get("id_minitableimport").hide();
    mini.unmask(document.body);
}
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_rent_plan_modify",
			renderTo: "id_fund_rent_plan_frame_rw",
			width: globalClientWidth - 30,
			height: 400,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方法
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			data: mini.decode('${empty json_fund_rent_plan_modify_str ? "[]" : json_fund_rent_plan_modify_str	 }'),
			tools : [  'add','-','edit','-','remove','-','exportExcel','-','importExcel', '-',  {
				html : '租金测算',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-save',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					var formCondition = new mini.Form("businessconditionFormModify");
					if(mini.get('fund_rent_plan_modify').getData().length < 0){
						mini.alert('请先上传租金计划！！！');
						return;
					}
					//再调整之前先将租金计划隐藏域重新赋值
					var fundRentsData = mini.get('fund_rent_plan_modify').getData()
					var fundRents = mini.encode(fundRentsData);
					//根据租金计划
					var corpusbusinessTotal = 0;
					var corpusTotal = 0;
					var cleanLeasingMoney = mini.get('framework_condition_modify.cleanleasemoney').getValue().replace(/,/gim,'');
					var equipendvalue = mini.get('framework_condition_modify.equipendvalue').getValue().replace(/,/gim,'');
					cleanLeasingMoney = Number(cleanLeasingMoney) - Number(equipendvalue);
					for(var i = 0  ; i < fundRentsData.length ;i++){
						corpusbusinessTotal = corpusbusinessTotal + Number(fundRentsData[i].corpusbusiness.replace(/,/gim,''));
						corpusTotal = corpusTotal + Number(fundRentsData[i].corpus.replace(/,/gim,''));
					}
					corpusbusinessTotal = decimal(corpusbusinessTotal,2);
					corpusTotal = decimal(corpusTotal,2);
					if(corpusbusinessTotal != corpusTotal){
						mini.alert('财务本金之和需等于业务本金之和，请检查后，在进行租金测算！');
						return ;
					}
					if(corpusbusinessTotal != cleanLeasingMoney){
						mini.alert('本金之和需等于业务本金之和，请检查后，在进行租金测算！');
						return;
					}
					//mini.get("json_fund_rent_plan_modify_str").setValue(fundRents);
					var o = formCondition.getData(true,false);
					o.json_fund_rent_plan_str = fundRents;
					o.docid = flowUnid;
					o.contractid = "${requestScope['contract_info.contractid'] }";
					mini.mask({	
						el: document.body,
						cls: 'mini-mask-loading',
						html: '正在进行租金计划修改，请稍后...'
					}); 
				    var url="<%=request.getContextPath() %>/leasing/updateCalculateOld.action";
					$.ajax({
				        url: url,
				        type: "post",
				        data:  o ,
				        success: function (text) {
				        	var result = mini.decode(text);
				        	if(result.rentcalculaters == 'yes'){
				        		mini.alert('租金计划修改成功');
					        	var finacashdetail = result.cashdetaillist;
					        	var fundchargeplan = result.fundchargeplan;
					        	fundrentplan = result.rentplanlist;
					        	mini.get("fund_rent_plan_modify").setData(mini.decode(fundRents));
					        	mini.get("fund_cash_flow_modify").setData(finacashdetail);
					        	mini.get("fund_fund_charge_modify").setData(fundchargeplan);
					        	//mini.get("json_fund_rent_plan_modify_str").setValue(mini.encode(fundrentplan));
					        	jQuery("#id_json_fund_rent_plan_modify_str").val(mini.encode(fundrentplan));
					        	jQuery("#id_json_fund_fund_charge_modify_str").val(mini.encode(fundchargeplan));
					        	jQuery("#id_json_fund_cash_flow_modify_str").val(mini.encode(finacashdetail));
					        	mini.get("framework_condition_modify.irrshow").setValue(result.irr);
					        	mini.get("framework_condition_modify.xirr").setValue(result.xirr);
					        	document.getElementById('framework_condition_modify.irr').value = result.irr;
					        	mini.unmask(document.body);
				        	}else{
				        		mini.alert('服务器繁忙，请与管理员联系！！！');
				        		mini.unmask(document.body);
				        	}
				        	
				        },
				        error: function (jqXHR, textStatus, errorThrown) {
				        	mini.unmask(document.body);
				            alert(jqXHR.responseText);
				        }
				    });
				}
			} ],
			beforeShowWindowCallBack : function (miniTable,miniForm,operFlag){
				if('edit' == operFlag){
					var selectedRowDatas = miniTable.getSelecteds();
					if(selectedRowDatas[0].planstatusname  != '未回笼'){
						mini.alert('该租金计划已核销，不能进行修改！！！');
						return;
					}else{
						return true;
					}
				}
			},
			afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
				var datas = miniTable.getData();
				if("add" == operFlag){
					var qixiang=datas[datas.length-1].rentlist;
					mini.getbyName("rentlist").setValue(parseInt(qixiang)+1);
				}
			},
			addOperCallBack : function (miniTable,rowData){
				fundrentplan = miniTable.getData();
				for(var k = 0 ; k < fundrentplan.length;k++){
	        		var temprentlist ;
	        		var date1 = Number(fundrentplan[k].rentlist);
	        		for(var m = k + 1 ; m < fundrentplan.length ;m++){
	        			var date2 = Number(fundrentplan[m].rentlist);
	        			if(date1 > date2){
	        				date1 = date2;
	        				temprentlist = fundrentplan[k] ;
	        				fundrentplan[k] = fundrentplan[m];
	        				fundrentplan[m] = temprentlist;
	        				temprentlist = fundrentplan[k];
	        			}
	        		}
	        		
	        	}
				miniTable.setData(fundrentplan);
				jQuery('#id_json_fund_rent_plan_modify_str').val(JsonUtil.encode(mini.get("fund_rent_plan_modify").getData()));
			},
			updateOperCallBack : function (miniTable,rowData){
				jQuery('#id_json_fund_rent_plan_modify_str').val(JsonUtil.encode(mini.get("fund_rent_plan_modify").getData()));
			},
			removeOperCallBack : function (miniTable,rowData){
				jQuery('#id_json_fund_rent_plan_modify_str').val(JsonUtil.encode(mini.get("fund_rent_plan_modify").getData()));
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
					alert("租金计划应该从最后一项开始删除，请重新选择！");
					return false;
				}if(parseInt(strmax)-parseInt(strmin)+1!=selectedRowDatas.length){
					alert("租金计划应该从最后一项开始删除，期项必须是连续的，请重新选择！");
					return false;
				}
				return true;
			},
			validateForm: function(miniTable, miniForm){
				var cwbj=Number(getinputtext(mini.getbyName("corpus")));
				var cwlx=Number(getinputtext(mini.getbyName("interest")));
				var ywbj=Number(getinputtext(mini.getbyName("corpusbusiness")));
				var ywlx=Number(getinputtext(mini.getbyName("interestbusiness")));
				if(ywbj+ywlx != cwbj+cwlx){
					alert("业务本金与业务利息之和不等，请核对!");
					return false;
				}
				return true;
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'rentlist', header: '期项',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '计划日期',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						type:'date',
						format:'yyyy-MM-dd',
						width: 200
					}
				},
				{field: 'rent', header: '租金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						readOnly:true,
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200,
						otherAttributes:'onkeyup= "calCaiwu()"'
					}
				},
				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						required: true,
						labelWidth:100,
						width: 200,
						otherAttributes:'onkeyup="calCaiwu()"'
					}
				},
				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						width: 200,
						otherAttributes:'onkeyup="calRent()"'
					}
				},
				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						required: true,
						labelWidth:100,
						width: 200,
						otherAttributes:'onkeyup="calRent()"'
					}
				},
				{field: 'planstatusname', header: '核销状态',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						readOnly:true,
						value:'未回笼',
						width: 200
					}
				}
			]
		});
	});
});

function calRent(){
	var corpusbusiness = mini.getbyName('corpusbusiness');
	var interestbusiness = mini.getbyName('interestbusiness');
	var rent = mini.getbyName('rent');
	setformatvalue(corpusbusiness);
	setformatvalue(interestbusiness);
	var corpusbusinessValue =  getinputtext(corpusbusiness);
	var interestbusiness =   getinputtext(interestbusiness);
	rent.setValue(decimal(Number(corpusbusinessValue) + Number(interestbusiness),2));
	setformatvalue(rent);
}

function getinputtext(e){
	return e.getInputText().replace(/,/g,'');
}

function  calCaiwu(){
	var corpus = mini.getbyName('corpus');
	var interest = mini.getbyName('interest');
	setformatvalue(corpus);
	setformatvalue(interest);
}
</script>
<div id="id_fund_rent_plan_frame_rw"></div>