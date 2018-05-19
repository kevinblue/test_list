<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "old_contract_equip",
		renderTo: "id_table_old_contract_equip_detail",
		width: globalClientWidth-30,
		height: 250,
		title: "",
		lazyLoad: true,
		remoteOper : false,
		showPager: false,
		multiSelect: false,
		showToolbar: false,
		data: JsonUtil.decode('${empty json_old_contract_equip_str ? "[]" : json_old_contract_equip_str }'),
		columns: [
					{type:'indexcolumn'},
					{type:'checkcolumn'},
					{field:'id',header:'id',visible: false},
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
					{field:'equipprice', header:'总价(RMB)',dataType:"currency",align:'right',summary:true,
						  formEditorConfig:{
						        	  type:'text',
							         vtype:'float',
							         newLine:true,
								  readOnly: true}},
					{field: 'brand', header: '品牌',
						      formEditorConfig:{
								       colspan:3,      
							         maxLength:120}},
							         
			        {field:'vndrtypename', header:'贸易类型', formEditorConfig:{fieldVisible:false,fillFromFieldName:'vndrtype',fillProperty:'name'}},
				   	{field:'vndrtype',visible:false,header:'贸易类型',
				   		         formEditorConfig:{
				   			              newLine:true,
				   			                 type:'combobox',
							             required:true,
							          multiSelect:false, 
							            textField:'name',
							           valueField:'value',
							         fieldVisible:true,
							         params:{pid:'vndr_type',xmlFileName:'combos/comboDict.xml'}}},
							         
					{field: 'vndrname', header: '供应商', 
					  formEditorConfig:{
				          fieldVisible: false,
				     fillFromFieldName:'vndr',
				          fillProperty:'name',
				       entityClassName:'com.tenwa.leasing.entity.cust.CustInfo',	
				 entityHeaderFieldName:'custName'}},
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
						              required:true,
						             textField:'name',
						            valueField:'value',
						            allowInput:false,
						          fieldVisible:true,
						                params:{cust_type:'cust_type.manufacturer',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
					{field: 'equipdeliverydate', header:'交付时间',dateFormat:"yyyy-MM-dd",
							  formEditorConfig:{
						                  type:'date',
						                 vtype:'date',
						                format:'yyyy-MM-dd',
						                newLine:true,
						              required:true,
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
		
	});});
</script>
	<div id="id_table_contract_equip">
	   <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	       <tr>
	          <td class="x-panel-table-div-title" colspan=4>原租赁物件</td>
	      </tr>
	   </table>
	    <div id="id_table_old_contract_equip_detail" class="fieldset-body"></div>
	   <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	     <tr>
	        <td class="x-panel-table-div-title" colspan=4>现租赁物件</td></tr>
	     </table>
	    <div id="id_table_new_contract_equip_detail" class="fieldset-body">
	        	<jsp:include page="../../contract_comm/contract_equip_detail.jsp" ></jsp:include>
	    </div>
</div>