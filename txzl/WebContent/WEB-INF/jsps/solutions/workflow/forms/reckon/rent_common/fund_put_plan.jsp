<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask ==true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "fund_put_config",
			renderTo: "id_fund_put_config",
			width: globalClientWidth - 30,
			height: 360,
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			showToolbar: showTools,
			editFormPopupWindowWidth : 700,
			data: mini.decode('${empty json_fund_put_config_str ? "[]" : json_fund_put_config_str }'),
			tools : [ 'add','-','edit','-','remove','-','copy','-',
				     {
						    html : '维护付款前提',
							plain : true,
							iconCls : 'icon-redo',
							handler : function(miniTable, buttonText) {
								var rows = miniTable.getSelecteds();
								var arrayojb=[];
								var fundtype=[];
								if(rows.length>0){
									  for(var i=0;i<rows.length;i++){
										  fundtype.push(rows[i].fundtype);
										  var paymentpremise={};
										    if(rows[i].feetype.length<=0){
					                            mini.alert("请先测算租金,才可以维护付款前提！");	 
				                            	return false;	
				                              } 
										     if(rows[i].paytypename.indexOf('收款')>-1){
					                            mini.alert("只有收付方向为付款的数据才能发起付款前提按钮！");	 
				                            	return false;	
				                              } 
										     paymentpremise=rows[i];
										     arrayojb.push(paymentpremise);
									  }
									var paymentjson=mini.encode(arrayojb);
									var fundtypejson=mini.encode(fundtype);
							        var urlFlag = getRootPath()+"/workflow/forms/reckon/rent_reckon/payment_premise_openlist.bi?paymentjson="+paymentjson+"&fundtypejson="+fundtypejson;
							        urlFlag = encodeURI(urlFlag);
							        mini.open({
			                   		        url: urlFlag,
			                   		        id:'table_id3',
			                   		        title: "维护付款前提", width: 800, height: 455,
			                   		        onload: function () {},
			                   		        ondestroy: function (action) {
			                   		        	if("savesuccess" == action){
			                   		        		window.location.reload();
			                   		        	}
			                   		        }
			                   		    });
								}else{
								   mini.alert("请选中数据！");
								}
						      }
					 }
			         ],
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				var paymentid = mini.getbyName("paymentid");
				if(operFlag=="add"){
					paymentid.setValue(miniTable.getData().length+1);
				}
				var feetype = mini.getbyName("feetype");
				feetype.setValue("feetype10");
				var paytype = mini.getbyName("paytype");
				paytype.setValue("pay_type_out");
			},
			confirmCopyCallBack:function(miniTable,rows){
				var newRows = [];
				var datas = miniTable.getData();
				var temp=0;
				for(var j = 0 ; j< datas.length;j++){
					var data = datas[j];
						if(Number(data.paymentid) > temp){
							temp = Number(data.paymentid);
						}
				}
				for(var i = 0 ; i< rows.length;i++){
					var row = mini.clone(rows[i]);
					row.paymentid = ++temp;
					newRows.push(row);
				}
			   miniTable.addRows(newRows, 0); 
			   miniTable.saveDataToInput();
			},
			validateForm:function (miniTable, miniForm, editFormItemOperFlag, addIndex){
				return true; 
			},
			addOperCallBack : function (miniTable,rowData){
				$('#id_json_fund_put_config_str').val(mini.encode(miniTable.getData()));
			},
			removeOperCallBack:function(miniTable,rowDatas){
		         var guarantable=mini.get("payment_premise_condition");
		           var guarantableData=guarantable.getData();
				   for(j=0;j<rowDatas.length;j++){
		               var paymentid=rowDatas[j].paymentid;
		               var fundtype=rowDatas[j].fundtype;
		               var devicename=rowDatas[j].devicename;
		               for(var i=0;i<guarantableData.length;i++){
		            	   var paymentidTmp=guarantableData[i].paymentid.replace(/[^0-9]/ig,"");//去除所有字符串只取数字
		                    if(paymentid==paymentidTmp&&fundtype==guarantableData[i].feetype&&devicename==guarantableData[i].devicename){
		                    	guarantable.removeRow(guarantableData[i]);
		                    }
		               }                
				   }
				$('#id_json_fund_put_config_str').val(mini.encode(miniTable.getData()));
			},
			copyOperCallBack:function(miniTable){
				$('#id_json_fund_put_config_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_fund_put_config_str').val(mini.encode(miniTable.getData()));
			},
			columns: [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'docid', header: 'docid', visible: false},
				{field: 'contractid', header: '合同号',visible: true,
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						fieldVisible:false,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paymentid', header: '期次',
					formEditorConfig:
					{
						type:'int',
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '投放日期',dateFormat :"yyyy-MM-dd",
					formEditorConfig:
					{
						type:'date',
						required: true,
						labelWidth:100,
						width: 200,
						format:'yyyy-MM-dd'
					}
				},
				{field: 'planmoney', header: '投放金额',dataType : "currency",summary : true,align:'right',
					formEditorConfig:
					{
						type:'float',
						newLine:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'feetype', header: '款项名称ID',visible: false},
				{field: 'feetypename', header: '费用类型',visible: true,
					formEditorConfig:
					{
						readOnly:true,
						fieldVisible:false,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paytype', header: '收付方向id', visible: false},
				{field: 'paytypename', header: '收付方向', visible: false,
					formEditorConfig:
					{
						readOnly:true,
						fieldVisible:false,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'paycust', header: '收付对象',  visible: false,
			       formEditorConfig:{
		                width: 400,
		                fieldVisible:false,
		                colspan:3,
		                type:'queryinput',
		             	required: false,
		            	textField: 'paycustname',
		           		valueField: 'paycust',
		           		allowInput: false,
		               	params: {xmlFileName: '/eleasing/workflow/rent/rent_cal/fund_fund_pay_obj.xml'}
				  }
				},
				{field: 'paycustname', header: '收付对象',visible: false
				},
				{field:'fundtypename', header:'款项节点', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'fundtype',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
				{field: 'fundtype', header: '款项节点', visible:false,
					  formEditorConfig:{
				                  type:'combobox',
				              required:true,
				              newLine:false,
				             textField:'name',
				            valueField:'value',
				          fieldVisible:true,
				    params:{pid: 'fund_type', xmlFileName:'combos/comboDict.xml'}}
				},
				{field:'devicenamename', header:'设备名称', 
				      formEditorConfig:{
				          fieldVisible:false,
				     fillFromFieldName:'devicename',
				          fillProperty:'name',
			           entityClassName:'com.tenwa.business.entity.DictionaryData',	
				 entityHeaderFieldName:'name'}},
				{field: 'devicename', header: '设备名称', visible:false,
					  formEditorConfig:{
				                  type:'combobox',
				              required:true,
				              newLine:true,
				             textField:'name',
				            valueField:'value',
				          fieldVisible:true,
				    params:{pid: 'device_name', xmlFileName:'combos/comboDict.xml'}}
				},
				{field: 'fpnote', header: '备注',
					formEditorConfig:
					{
						type:'textarea',
						newLine:true,
						labelWidth:100,
						height:70,
						colspan:3,
						width: 510
					}
				}
			]
		});
	});
});




</script>
<div id="id_fund_put_config"></div>