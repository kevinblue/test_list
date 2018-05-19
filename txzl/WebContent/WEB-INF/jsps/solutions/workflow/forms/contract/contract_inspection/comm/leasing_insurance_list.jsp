<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_leasing_insurance_list_str" name="json_leasing_insurance_list_str" value='${empty json_leasing_insurance_list_str ? "[]" : json_leasing_insurance_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "leasing_insurance_list",
		renderTo: "id_table_leasing_insurance_list",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		allowCellWrap: true,
		remoteOper : true,
		showPager: true,
		showToolbar: showTools,
		params:{
			thisstart1:thisstart1,
			thissend1:thissend1,
			contractidselect:contractidselect
		},
		xmlFileName: '/eleasing/workflow/patrol/patrol_leasehold.xml',
		/* tools: ['add', '-', 'edit', '-', 'remove'], */
		columns: [
			{type:'indexcolumn',width:20},
			{field:'id', header:'id', visible:false},
			{field:'equipname', header:'设备名称', visible: true,
			     formEditorConfig:{
			    	 labelWidth:110,
				     fieldVisible: true}
		},
		{field:'operation_status', header:'设备运行状况',visible: false,formEditorConfig:{
			newLine:true,
			readonly:true,
			labelWidth:110,
		     fieldVisible: true}
		}, 
		{field:'total', header:'租赁物原值（万元）',dataType:"currency",summary:true,formEditorConfig:{
			newLine:true,
			readonly:true,
			labelWidth:110,
		     fieldVisible: true}
		},
			{field:'nowtotal', header:'租赁物最新评估价值（万元）',dataType:"currency",summary:true,visible: false,
				     formEditorConfig:{
				    	 labelWidth:110,
					     fieldVisible: true}
			},
			{field:'hidden1', header:'评估方法',visible: false
			},
			{field:'hidden2', header:'现价与应收租赁款余值之比', visible: false
			},
			
				{field:'is_insure', header:'现价与应收租赁款余值之比', visible: false,
					     formEditorConfig:{
					    	 labelWidth:110,
						     fieldVisible: true}
				},
				{field:'whetherinsurance', header:'租赁物是否保险',formEditorConfig:{
					newLine:true,
					readonly:true,
					labelWidth:110,
				     fieldVisible: true}
				},
			{field:'insuranceid', header:'保险单号码',visible: true,formEditorConfig:{
				newLine:true,
				readonly:true,
				labelWidth:110,
			     fieldVisible: true}
			},
			{field:'date_start_end', header:'保险期限'/* ,formEditorConfig:{
				newLine:true,
				readonly:true,
				labelWidth:110,
			     fieldVisible: true},
                renderer:function(e){
                	if(e.record.startdata==null||e.record.startdata==""){
                		return null;
                	}
			    	 return e.record.startdata+"至"+e.record.enddata;
			     } */
			},
			{field:'firstbeneficiary', header:'第一受益人',formEditorConfig:{
				newLine:true,
				readonly:true,
				labelWidth:110,
			     fieldVisible: true}
			}
		]
	});
});
</script>
<div id="id_table_leasing_insurance_list" style="width:100%;height:100%;"></div>