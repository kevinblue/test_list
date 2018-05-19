<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 alert(info.message);
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
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask == true){showTools = false;};
	var buttons=[ 'add', '-', 'edit', '-', 'remove'];
	if('<mini:param name="buttons"/>' != ''){
		 var s_buttons='<mini:param name="buttons"/>';
		 var v_buttons=s_buttons.split(",");
		 buttons=[];
		 for(var i=0;i<v_buttons.length;i++){
			 buttons.push(v_buttons[i]);
		 }
	}
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
			remoteOper : false,
			showPager: false,
			multiSelect: true,
			title:'',
			importTargetClass:'',//导入EXCEL对应的类
			importOrExPortCallBack:'',//导入回调方法
			importHeaderIndex:'3',//标题行
			importDataIndex:'4',//数据行
			showToolbar: showTools,
			virtualScroll:true,
			tools : buttons,
			data: JsonUtil.decode('${empty json_contract_equip_str ? "[]" : json_contract_equip_str }'),
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'equipname', header:'设备名称',
					 formEditorConfig:{
						     required:true,
						   labelWidth:100,
						    maxLength:120,
						    readOnly:true,
						      colspan:3,
						        width:506}},
				{field:'model', header:'型号/规格',
					 formEditorConfig:{
						      newLine:true,
						      readOnly:true,
						     required:true,
						   labelWidth:100,
						    maxLength:120,
						      colspan:3,
						        width:506}},
				{field:'equipnum', header:'数量',dataType :"currency",align:'right',
					 formEditorConfig:{
					          newLine:true,
					         required:true,
					         readOnly:true,
					             type:'text',
					            vtype:'float',
					     defaultValue:1,
					          onkeyup:'adjustTotalPrice'}},
				{field:'price', header:'单价',dataType :"currency",align:'right',
					 formEditorConfig:{
					       labelWidth:90,
					       readOnly:true,
					             type:'text',
					            vtype:'float',
					         required:true,
					          onkeyup:'adjustTotalPrice'}},
				{field:'unit', header:'单位',
					 formEditorConfig:{
						 readOnly:true,
					          newLine:true,		 
						    maxLength:10}},	          
				{field:'equipprice', header:'交易价格',dataType :"currency",align:'right',summary :true,
					 formEditorConfig:{
					        	 type:'text',
						        vtype:'float',		 
							
							 readOnly:true}},
				{field:'total', header:'设备原值',dataType :"currency",align:'right',
				     formEditorConfig:{
				    	 readOnly:true,
							  newLine:true, 
							    vtype:'float'}},
				{field:'nowtotal', header:'设备现值',dataType :"currency",align:'right',
				     formEditorConfig:{		
				    	 readOnly:true,
							    vtype:'float'}},
				{field:'brand', header:'品牌',
					 formEditorConfig:{
						 readOnly:true,
							  newLine:true, 
						    maxLength:120}},
				{field: 'buydate', header: '购买时间',
					  formEditorConfig:{
						  readOnly:true,
								         maxLength:120}},
				{field:'vndrname', header:'供应商', 
					 formEditorConfig:{
					     fieldVisible:false,
					     readOnly:true,
				    fillFromFieldName:'vndr',
					     fillProperty:'name',
					  entityClassName:'com.tenwa.leasing.entity.cust.CustInfo',	
					  findCallBack:'createVndr',	
			    entityHeaderFieldName:'custName'}},
				{field:'vndr',header:'供应商',visible:false,
				     formEditorConfig:{
					          newLine:true,
					          colspan:3,
					          readOnly:true,
					            width:506,
					             type:'queryinput',
					         required:true,
					        textField:'name',
					       valueField:'value',
					       allowInput:false,
					     fieldVisible:true,
					            params:{cust_type:'cust_type.vndr',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
				{field:'manufacturername', header:'制造商', 
					  formEditorConfig:{
					      fieldVisible:false,
					 fillFromFieldName:'manufacturer',
					      fillProperty:'name',
					      findCallBack:'createmanufacturer',	
					   entityClassName:'com.tenwa.leasing.entity.cust.CustInfo',	
			     entityHeaderFieldName:'custName'}},
				{field:'manufacturer', header:'制造商', visible:false,
					  formEditorConfig:{
					           newLine:true,
					           colspan:3,
					           readOnly:true,
					             width:506,
					              type:'queryinput',
					          required:true,
					         textField:'name',
					        valueField:'value',
					        allowInput:false,
					      fieldVisible:true,
				 	            params:{cust_type:'cust_type.manufacturer',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
				{field:'devicetypename', header:'设备类型', 
				 	  formEditorConfig:{
					      fieldVisible:false,
					 fillFromFieldName:'devicetype',
					      fillProperty:'name',
				       entityClassName:'com.tenwa.business.entity.DictionaryData',	
			     entityHeaderFieldName:'name'}},
				{field:'devicetype', header:'设备类型', visible:false,
					  formEditorConfig:{
					           newLine:true,
					              type:'combobox',
					          required:true,
					         textField:'name',
					        valueField:'value',
					      fieldVisible:true,
					            params:{pid:'device_type',xmlFileName:'combos/comboDict.xml'}}},			    
				{field:'equipid', header:'设备序列号',
					 formEditorConfig:{ 
							maxLength:120}},
				{field:'equipdeliveryplace', header:'交付地点',
					  formEditorConfig:{
							   newLine:true,
							 maxLength:120}},
				{field:'equipdeliverydate', header:'交付时间',dateFormat :"yyyy-MM-dd",
					  formEditorConfig:{
					              type:'date',
					          required:true,
					             
					        allowInput:false,
					            format:'yyyy-MM-dd'}},
				{field:'equipplace', header:'设备设置地址',
					  formEditorConfig:{
					         maxLength:120,
					           newLine:true,
					           colspan:3,
					            height:70,
					             width:506}},
				{field:'cenote', header:'备注',
					  formEditorConfig:{
					         maxLength:500,
					           newLine:true,
					              type:'textarea',
					           colspan:3,
					            height:70,
					             width:506}}
			]
		});
	});
});
//计算设备总价
function adjustTotalPrice(){
	var equipnum =$("input[name=equipnum]").val();
	var price = $("input[name=price]").val();
	equipnum = equipnum || 0;
	price = price || 0;
	var temp = Number(equipnum) * Number(price);
	mini.getbyName("equipprice").setValue(decimal(temp,2));
}
//检查租赁物件必填和交易价和测算中的价格是否一样
function checkEquipIsNull(){
	var grid1 = mini.get("contract_equip");
	var jsondata=grid1.getData();
	if(jsondata.length<=0){mini.alert("请填写租赁物！！");return false;}
	return true;
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
	if (parseFloat(totalPrice) != parseFloat(equipAmt)) {
		mini.alert("【租赁物明细】标签的合计总价[" + totalPrice + "]和【拟商务条件】标签中的设备款[" + equipAmt + "]不相等,请检查.");
		return false;
	} else {
		return true;
	}
}
</script>

<div id="id_table_contract_equip_detail" style="width:100%;height:100%;"></div>