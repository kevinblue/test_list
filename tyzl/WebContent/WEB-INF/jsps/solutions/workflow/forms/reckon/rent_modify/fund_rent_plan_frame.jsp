<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
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
	if('<mini:param name="isView"/>' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_rent_plan_new",
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
			data: JsonUtil.decode('<mini:param  name="json_fund_rent_plan_new_str" defaultValue="[]"/>'),
			tools : [  'add','-','edit','-','remove','-','exportExcel','-','importExcel', '-',  {
				html : '租金测算',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-save',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					var formCondition = new mini.Form("businessconditionFormReadonly");
					if(mini.get('fund_rent_plan_new').getData().length < 0){
						mini.alert('请先上传租金计划！！！');
						return;
					}
					//再调整之前先将租金计划隐藏域重新赋值
					var fundRents = mini.encode(mini.get('fund_rent_plan_new').getData());
					//mini.get("json_fund_rent_plan_new_str").setValue(fundRents);
					var o = formCondition.getData(true,false);
					o.json_fund_rent_plan_str = fundRents;
					o.docid = flowUnid;
					o.contractid = "<mini:param name='contract_info.contractid'/>";
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
					        	mini.get("fund_rent_plan_new").setData(mini.decode(fundRents));
					        	mini.get("fund_cash_flow_new").setData(finacashdetail);
					        	mini.get("fund_fund_charge_new").setData(fundchargeplan);
					        	//mini.get("json_fund_rent_plan_new_str").setValue(mini.encode(fundrentplan));
					        	jQuery("#id_json_fund_rent_plan_new_str").val(mini.encode(fundrentplan));
					        	jQuery("#id_json_fund_fund_charge_new_str").val(mini.encode(fundchargeplan));
					        	jQuery("#id_json_fund_cash_flow_new_str").val(mini.encode(finacashdetail));
					        	mini.get("framework_condition.irr").setValue(result.irr);
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
				jQuery('#id_json_fund_rent_plan_new_str').val(JsonUtil.encode(mini.get("fund_rent_plan_new").getData()));
			},
			updateOperCallBack : function (miniTable,rowData){
				jQuery('#id_json_fund_rent_plan_new_str').val(JsonUtil.encode(mini.get("fund_rent_plan_new").getData()));
			},
			removeOperCallBack : function (miniTable,rowData){
				jQuery('#id_json_fund_rent_plan_new_str').val(JsonUtil.encode(mini.get("fund_rent_plan_new").getData()));
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
	var corpusbusinessValue =   getinputtext(corpusbusiness);
	var interestbusiness =   getinputtext(interestbusiness);
	rent.setValue(decimal(Number(corpusbusinessValue) + Number(interestbusiness),2));
	setformatvalue(rent);
}


function getinputtext(e){
	var value = e.getInputText();
	try {
    	value = (value+'').replace(/,/g,'');
    	value = value == '' ? '0' : value;
    	value = parseFloat(value);
    } catch(e) {
    	value = 0;
    }
    return value;
}

function  calCaiwu(){
	var corpus = mini.getbyName('corpus');
	var interest = mini.getbyName('interest');
	setformatvalue(corpus);
	setformatvalue(interest);
}
</script>
<div id="id_fund_rent_plan_frame_rw"></div>