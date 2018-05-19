<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_pledge_info_list_str" name="json_pledge_info_list_str" value='${empty json_pledge_info_list_str ? "[]" : json_pledge_info_list_str }'></input>

<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "pledge_info_list",
		renderTo: "id_table_pledge_info_list",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: true,
		title: "",
		allowCellWrap: true,
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_pledge_info_list_str ? "[]" : json_pledge_info_list_str }'),
		columns: [
			{field:'id', header:'id', visible:false},
			{field:'pledge_name1', header:'质押物一名称', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'pledge_old_val1', header:'原质押物价值',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_new_val1', header:'最新评估价值',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_proportion1', header:'质押率',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_insurance1', header:'质押物一是否保险',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_safeNum1', header:'保险单号码',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_safeTerm1', header:'保险期限',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_firstPeople1', header:'第一受益人',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_signDay1', header:'质押合同签署日',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_startDate1', header:'担保时效起始日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'pledge_endDate1', header:'担保时效截至日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_name2', header:'质押物二名称',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'pledge_old_val2', header:'原质押物价值',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'pledge_new_val2', header:'最新评估价值',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'pledge_proportion2', header:'质押率',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
			,
			{field:'pledge_insurance2', header:'质押物二是否保险',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_safeNum2', header:'保险单号码',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_safeTerm2', header:'保险期限',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_firstPeople2', header:'第一受益人',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_signDay2', header:'质押合同签署日',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_startDate2', header:'担保时效起始日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			},
			{field:'pledge_endDate2', header:'担保时效截至日期',formEditorConfig:{
				newLine:true,required:true,
				labelWidth:110,
		    	 required:true,
			     fieldVisible: true}
			}
		]
	});
});
</script>
<div id="id_table_pledge_info_list" style="width:100%;height:100%;"></div>