<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_mortgage_info_list_str" name="json_mortgage_info_list_str" value='${empty json_mortgage_info_list_str ? "[]" : json_mortgage_info_list_str }'></input>

<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "mortgage_info_list",
		renderTo: "id_table_mortgage_info_list",
		width: globalClientWidth - 25,
		height: 365,
		lazyLoad: false,
		title: "",
		allowCellWrap: true,
		remoteOper : false,
		showPager: true,
		showToolbar: showTools,
		/* tools: ['add', '-', 'edit', '-', 'remove'], */
		 params:{
			 contractidselect:contractidselect},
		    xmlFileName: '/eleasing/workflow/contract/contract_finish/contract_collateral_list3.xml',
			columns: [
             {type:'indexcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'equipname', header:'抵押物一名称', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'registypename', header:'登记类型', visible: true},
			
			{field:'guarantyvalue', header:'原抵押物价值（万元）',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'presentvalue', header:'最新评估价值（万元）',dataType:"currency",summary:true,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'mortgage_proportion1', header:'抵押率',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: false}
			},
			{field:'whetherinsurance', header:'抵押物一是否保险',visible:false,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'insuranceid', header:'保险单号码',visible:false,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'purchaselife', header:'保险期限',visible:false/* ,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true},
			     renderer:function(e){
			    	 if(e.record.startdata==null||e.record.startdata==""){
	                		return null;
	                	}
			    	 return e.record.startdata+"至"+e.record.enddata;
			     } */
			},
			{field:'firstbeneficiary', header:'第一受益人',visible:false,formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'mortgage_signday1', header:'抵押合同签署日',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'mortgage_startdate1', header:'担保时效起始日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'mortgage_enddate1', header:'担保时效截至日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'mortgage_currentstatus1', header:'抵押物目前状态',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
		]
	});
});
</script>
<div id="id_table_mortgage_info_list" style="width:100%;height:100%;"></div>