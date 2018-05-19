<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 var tabledate=info.tabledate;
	 if(""!=tabledate){
	    var grid=mini.get(tableid);
        grid.set({data:mini.decode(tabledate)});
	 }
     mini.get("id_minitableimport").hide();
     mini.unmask(document.body);
}
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	var buttons=[ 'add', '-', 'edit', '-','copy','-', 'remove','-','exportExcel','-','importExcel'];
	if('${param.buttons}' != ''){
		 var s_buttons='${param.buttons}';
		 var v_buttons=s_buttons.split(",");
		 buttons=[];
		 for(var i=0;i<v_buttons.length;i++){
			 buttons.push(v_buttons[i]);
		 }
	}
	
	var conid = mini.getbyName("contract_info.contractid").getValue();
	var projid = "${requestScope['contract_info.projid']}";
	//alert(flowUnid);
	//alert(conid);
	//alert(projid);
	//获取父页面中保存在hidden域的值
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		 new ApTable({
			id: "contract_equip",
			renderTo: "id_table_contract_equip_detail",
			width: globalClientWidth - 20,
			height: 360,
			lazyLoad: false,
			title: "",
			isClickLoad:false,
			remoteOper : true,
			entityClassName : "com.tenwa.leasing.entity.proj.ProjEquipTmp",
			showPager: false,
			multiSelect: true,
			title:'',
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方法
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			showToolbar: showTools,
			params:{
				
				projid:projid,
				docid:flowUnid
				
			},
			virtualScroll:true,
			tools : buttons,
			//data: JsonUtil.decode('${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'),
			
			xmlFileName : '/eleasing/workflow/proj/proj_contract/proj_equip_tmp.xml',
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id',header:'id',visible: false},
				{field:'contractid',
					header:'合同编号',
					visible: false,
					formEditorConfig:{
						fieldVisible: false,
						valueField:conid,
						value:conid
					}
				},
				
				{field:'projinfo',
					header:'项目id',
					visible: false,
					formEditorConfig:{
						fieldVisible: false,
						value:projid
					}
				},
				
				
				{field:'docid',
					header:'流程id',
					visible: false,
					formEditorConfig:{
						fieldVisible: false,
						value:flowUnid
					}
				},
				
				{field:'equipname',header:'设备/租赁物名称',
					  formEditorConfig:{
						      required:true,
						    labelWidth:100,
						     maxLength:120,
						       colspan:3,
						         width:'100%'}},
				{field:'model', header:'型号/规格',
					  formEditorConfig:{
						       newLine: true,
						      required: true,
						    labelWidth:100,
						     maxLength:120,
						       colspan: 3,
						         width: '100%'}},
				{field:'equipnum', header:'数量',dataType:"currency",align:'right',summary:true,
					  formEditorConfig:{
					           newLine:true,
					              type:'text',
					             vtype:'float',
					      defaultValue:1,
					           onkeyup:'adjustTotalPrice'}},
				{field:'price', header:'单价(RMB)',dataType:"currency",align:'right',summary:true,
					  formEditorConfig:{
					              type:'text',
					             vtype:'float',
					           onkeyup:'adjustTotalPrice'}},
					           
		           {
						field:'originaltotal', 
						dataType:"currency",
						header:'设备原值(RMB)',
						align:'right',
						summary:true,				  
						formEditorConfig:{
				        	  type:'text',
					         vtype:'float',
					         newLine:true
						},		  
					},
					{
						field:'nowtotal', 
						header:'设备现值(RMB)',
						dataType:"currency",
						align:'right',
						summary:true,				  
						formEditorConfig:{
				        	  type:'text',
					         vtype:'float',
					         labelWidth:100,
						},		  
					},
				{field:'equipprice', header:'总价(RMB)',dataType:"currency",align:'right',summary:true,
						dataType:"currency",
					  formEditorConfig:{
					        	  type:'text',
						         vtype:'float',
						         newLine:true,
							  readOnly: true}},
				{field: 'brand', header: '品牌',
					      formEditorConfig:{
							       colspan:3,      
						         maxLength:120}},
						         
		        /* {field:'vndrtypename', header:'贸易类型', formEditorConfig:{fieldVisible:false,fillFromFieldName:'vndrtype',fillProperty:'name'}},
			   	{field:'vndrtype',visible:false,header:'贸易类型',
			   		         formEditorConfig:{
			   			              newLine:true,
			   			                 type:'combobox',
						             required:false,
						          multiSelect:false, 
						            textField:'name',
						           valueField:'value',
						           showNullItem:"true",
						           nullItemText:"",
						         fieldVisible:true,
						               params:{pid:'vndr_type',xmlFileName:'combos/comboDict.xml'}}}, */
						         
					{field: 'vndrname', header: '供应商', 
					  formEditorConfig:{
				          fieldVisible: false,
				     fillFromFieldName:'vndr',
				          fillProperty:'name',
				       entityClassName:'com.tenwa.leasing.entity.cust.CustInfo',	
				 entityHeaderFieldName:'custName'},
					renderer:function(e){
						var row=e.record;
						return "<a style='text-decoration:underline;color:blue;' href='javascript:showcustinfo(\""+row.vndr+"\")'>"+row.vndrname+"</a>";
							}	
					
					},
			{field: 'vndr', header: '供应商', visible: false,
					  formEditorConfig:{
				               newLine:true,
				               colspan: 3,
				                 width: '100%',
				                  type: 'queryinput',
				             textField: 'name',
				            valueField: 'value',
				            allowInput: false,
				          fieldVisible: true,
			                 	params: {cust_type: 'cust_type.vndr',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
				{field: 'manufacturername', header:'生产商', 
				          formEditorConfig:{
					          fieldVisible:false,
					     fillFromFieldName:'manufacturer',
					          fillProperty:'name',
					          findCallBack:'createmanufacturer',	
					       entityClassName:'com.tenwa.leasing.entity.cust.CustInfo',
					 entityHeaderFieldName:'custName'}},
				{field: 'manufacturer', header:'生产商', visible:false,
						  formEditorConfig:{
					               newLine:true,
					               colspan:3,
					                 width:'100%',
					                  type:'queryinput',
					              
					             textField:'name',
					            valueField:'value',
					            allowInput:false,
					          fieldVisible:true,
					          params:{cust_type:'cust_type.manufacturer',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
			
					          {
									field:'devicetypename', 
									header:'设备类型',
									formEditorConfig:{
										fieldVisible:false,
										fillFromFieldName:'devicetype',
										fillProperty:'name'}
								},
							   {
									field:'devicetype',
									header:'设备类型',
									visible:false,
									width:190,
					   		         formEditorConfig:{
						   			     type:'combobox',
									     textField:'name',
									     valueField:'value',
									     params:{pid:'device_type',xmlFileName:'combos/comboDict.xml'},
						   			     newLine:true,
									     required:false,
									     fieldVisible:true,
									     allowinput:false
									}
								},
								{
									field:'equipid', 
									header:'设备编号',
									formEditorConfig:{
							        	  type:'text'
									}		  
								},        
					          
					          
					          {field: 'equipdeliverydate', header:'交付时间',dateFormat:"yyyy-MM-dd",
						  formEditorConfig:{
					                  type:'date',
					                 vtype:'date',
					                format:'yyyy-MM-dd',
					                newLine:true,
					            allowInput:false}},
				{field:'equipperiod', header:'质量保证期',
					      formEditorConfig:{   
							     maxLength:120}},
				{field:'equipdeliveryplace', header:'设备交付地址',
						  formEditorConfig:{
								   newLine:true,
								   maxLength:120,
								   colspan:3,
								   width:'100%',
								 maxLength:120}},
				{field:'equipplace',header:'设备设置地址',
					      formEditorConfig:{
					             maxLength:120,
					               newLine:true,
					               colspan:3,
					                height:70,
					                 width:'100%'}},
				{field:'cenote',header:'备注',
					      formEditorConfig:{
					             maxLength:500,
					               newLine:true,
					                  type:'textarea',
					               colspan:3,
					                height:70,
					                 width:'100%'
				}}
			]
		});
	});
});
//加载客户信息
function showcustinfo(custid){
	var params = "id="+custid+"&isView=true";
		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
		var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
}

//计算设备总价
function adjustTotalPrice(){
	var equipnum =$("input[name=equipnum]").val();
	var price = $("input[name=price]").val();
	equipnum = equipnum || 0;
	price = price || 0;
	var temp = Number(equipnum) * Number(price);
	mini.getbyName("equipprice").setValue(decimal(temp,2));
	mini.getbyName("originaltotal").setValue(decimal(temp,2));
}
//检查租赁物件必填和交易价和测算中的价格是否一样
 function checkEquipIsNull(){
	if(mini.getbyName("rawValue_contract_info.leasform").getValue()!="直租"){
		return;
	}
	var grid1 = mini.get("contract_equip");
	var jsondata=grid1.getData();
	if(jsondata.length<=0){mini.alert("请填写租赁物！！");return false;} 
	//租赁物总价
	 var totalPrice = 0;
	for ( var i = 0; i < jsondata.length; i++) {
		totalPrice += parseFloat(jsondata[i]['equipprice']);
	} 
	//计算商务条件的设备款
	 var tempequipAmt = mini.get("equipamt").getValue();
	tempequipAmt=tempequipAmt.replace(/,/g,"");
	var equipAmt=parseFloat(tempequipAmt).toFixed(2);
	totalPrice=parseFloat(totalPrice).toFixed(2);
	equipAmt=parseFloat(equipAmt).toFixed(2);
	if (parseFloat(totalPrice) < parseFloat(equipAmt)) {
		mini.alert("【租赁物明细】标签的合计总价[" + totalPrice + "]不能小于【拟商务条件】标签中的设备款[" + equipAmt + "]!");
		return false;
	} else {
		return true;
	} 
}
</script>

<div id="id_table_contract_equip_detail" style="width:100%;height:100%;"></div>