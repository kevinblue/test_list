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
			data: JsonUtil.decode('<mini:param  name="json_contract_equip_str" defaultValue="[]"/>'),
			columns: [
						{type:'indexcolumn'},
						{type:'checkcolumn'},
						{field:'id',header:'id',visible: false},
						{field:'equipname',header:'设备名称',
							  formEditorConfig:{
								      required:true,
								    labelWidth:100,
								     maxLength:120,
								       colspan:3,
								         width:'100%'}},
						{field:'devicetypename', header:'设备分类', 
							      formEditorConfig:{
							          fieldVisible:false,
							     fillFromFieldName:'devicetype',
							          fillProperty:'name',
						           entityClassName:'com.tenwa.business.entity.DictionaryData',	
							 entityHeaderFieldName:'name'}},
						{field:'devicetype', header:'设备分类', visible:false,
								  formEditorConfig:{
							               newLine:true,
							                  type:'combobox',
							 
							             textField:'name',
							            valueField:'value',
							          fieldVisible:true,
							                params:{pid: 'leas_type', xmlFileName:'combos/comboDict.xml'}}},

						{field: 'nowtotal', header: '设备现值',dataType:"currency",align:'right',summary:true,
									formEditorConfig:{
											type:'thousand',
								              required:true,
											vtype:'thousand'}},	                
						{field: 'buildupstop', header:'建造起止期',
								  formEditorConfig:{
						               newLine:true
								  }},
						{field: 'startstopusecycle', header:'起止使用周期',
								formEditorConfig:{
								}},					            
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
//计算设备总价
function adjustTotalPrice(e){
	var equipnum =getMiniEditFormField("contract_equip.equipnum").getInputText().trim();
	var price = getMiniEditFormField("contract_equip.price").getInputText().trim().replace(/,/g,"");
	equipnum = equipnum || 0;
	price = price || 0;
    if (!price.match(/^(:?(:?\d+.\d+)|(:?\d+))$/)){price=0;
    getMiniEditFormField("contract_equip.price").text=0;}else{
    	
    }
	var temp = Number(equipnum) * Number(price);
	if(isNaN(temp)){temp=0;}
	getMiniEditFormField("contract_equip.equipprice").setValue(formatNumberThousand(decimal(temp,2)));
	if(e.sender.name!="equipnum"){setapcolumnformatvalue(e)	};
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