<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyTree2Table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<input style="display:none;"	class="mini-textarea" id="id_json_union_cust_str" 	name="json_union_cust_str" value='${empty json_union_cust_str ? "[]" : json_union_cust_str }'></input>

<script type="text/javascript">
jQuery(function(){
	var showTools = true;
/* 	var json_union_cust_str='${json_union_cust_str}'
	var jsonunioncuststr='[{"unioncustname":"${requestScope['proj_info.custname'] }","unioncust":"${requestScope['proj_info.custInfo'] }","unioncustclass":"${requestScope['proj_info.custclass'] }","ismainpay":"是"}]';
	if(json_union_cust_str.length>2)
		jsonunioncuststr='${json_union_cust_str}'; */
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({ 
			id: "union_cust",
			renderTo: "id_table_union_cust",
			width : globalClientWidth -30,
			height : 365,
			lazyLoad : true,
			isClickLoad : false,
			title : "",
			remoteOper : false,
			showPager : false,
			showToolbar : showTools,
			tools : ['add', '-', 'edit', '-', 'remove'],
			data : mini.decode('${empty json_union_cust_str ? "[]" : json_union_cust_str }'),
			completeCallBack:function(miniTable){
				var datas = miniTable.getData();
				var unioncust = "";
				var unioncustid = "";
				for(var i=0;i<datas.length;i++){
					unioncust += datas[i].unioncustname;
					unioncustid += datas[i].unioncust;
					if(i<datas.length-1){
						unioncust += ",";
						unioncustid+=",";
					}
					
				}
				mini.get("custname2").setValue(unioncust);
				mini.get("custid2").setValue(unioncustid);
				//mini.get('id_json_contract_unioncust_str').setValue(mini.encode(miniTable.getData()));
			},
			addOperCallBack:function(miniTable,rowData){
				var datas = miniTable.getData();
				var unioncust = "";
				for(var i=0;i<datas.length;i++){
					unioncust += datas[i].unioncustname;
					if(i<datas.length-1){
						unioncust += ",";					
					}
					
				}
				mini.get("custname2").setValue(unioncust);
			},
			updateOperCallBack:function(miniTable,rowData){
				var datas = miniTable.getData();
				var unioncust = "";
				var unioncustid = "";
				for(var i=0;i<datas.length;i++){
					unioncust += datas[i].unioncustname;
					unioncustid += datas[i].unioncust;
					if(i<datas.length-1){
						unioncust += ",";
						unioncustid+=",";
					}
					
				}
				mini.get("custname2").setValue(unioncust);
				mini.get("custid2").setValue(unioncustid);
			},
			removeOperCallBack:function(miniTable,rowData){
				var datas = miniTable.getData();
				var unioncust = "";
				for(var i=0;i<datas.length;i++){
					unioncust += datas[i].unioncustname;
					if(i<datas.length-1){
						unioncust += ",";					
					}
					
				}
				mini.get("custname2").setValue(unioncust);
			},
			columns: [
				{type:'indexcolumn'},
				{type:'checkcolumn'},
				{field:'id', header:'id', visible:false},
				{field:'unioncustname', header:'联合承租人',
					     renderer : function(e){
						              var unioncustname = e.record.unioncustname;
						              var unioncustID = e.record.unioncust;
						              var unioncustclass=e.record.unioncustclass;
						               return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + unioncustID +"\",\""+unioncustclass+ "\")'>" + unioncustname + "</a>";},
					     formEditorConfig:{
					 	     fieldVisible:false,
					    fillFromFieldName:'assuror',
						     fillProperty:'name'}},
				{field:'unioncustclass', header:'联合承租人客户类型',visible: false,
						 formEditorConfig:{
						     fieldVisible:false,
						fillFromFieldName:'unioncust',
						     fillProperty:'unioncustclass'}},
				{field:'unioncust', header:'联合承租人', visible:false,
					    formEditorConfig:{
					             newLine:true,
					                type:'queryinput',
					            required:true,
					           textField:'name',
					          valueField:'value',
					          allowInput:false,
					             colspan:3,
					               width:'100%',
					        fieldVisible:true,
					              params:{cust_type:'cust_type.cust',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'},
					            onSelect:function($me,inputObj, rowdata){
					            	getMiniEditFormField("union_cust.unioncustclass").setValue(rowdata.custclass);
					  }}},
					           
	           {field:'ismainpayname',header:'是否主付款人',visible:false,
	            	formEditorConfig:{
	            		 fieldVisible:false,
	  				fillFromFieldName:'ismainpay',
	  		             fillProperty:'name'
	            	}
			    },	            
				{field:'ismainpay',header:'是否主付款人',visible:false,
	            	formEditorConfig:{ 
		           		newLine:true, 
		           		type:'combobox',
			            
				           textField:'name',
				          valueField:'value',
				          fieldVisible:false,
				          data:[{'name':'是','value':'是'},{'name':'否','value':'否'}]
			    	}
			    },	   
			    {field:'projinfo',header:'项目id',visible:false,formEditorConfig:{
			    	fieldVisible:false,
			    	value:"${requestScope['contract_info.projid']}",
			    	text:"${requestScope['contract_info.projid']}"
			    }},
			    {field:'memo',header:'备注',
					    formEditorConfig:{
						         newLine:true,
						       maxLength:500,
						         colspan:3,
						           width:'100%',
						          height:70,
						            type:'textarea'
					}
				}
			]
		});
	});
});
</script>
<div id="id_table_union_cust"></div>
