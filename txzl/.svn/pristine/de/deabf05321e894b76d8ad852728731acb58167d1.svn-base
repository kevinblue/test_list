<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="leasing_info_list" title="租赁物情况">
	<fieldset class="fieldset-title">
		<table cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
				<tr class="tr-odd">
					<td class="td-content-title" colspan="1"  align="center">设备名称</td>
					<td class="td-content-title" colspan="3"  align="center">设备运行状况</td>
				</tr>
				<tr class="tr-even">
					<td class="td-content" colspan="1" ><input id="contract_invest_info.yxmoney" style="width:100%"  name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
					<td class="td-content" colspan="3" ><input id="contract_invest_info.ycurmoney" style="width:98%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content" colspan="1" ><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
					<td class="td-content" colspan="3" ><input id="contract_invest_info.ycurmoney" style="width:98%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
				</tr>
				
		 	 <tr class="tr-odd">
				<td class="td-content-title" align="center">租赁物原值（万元）</td>
				<td class="td-content-title" align="center">租赁物最新评估价值</td>
				<td class="td-content-title" align="center">评估方法</td>
				<td class="td-content-title" align="center">现价与应收租赁款余值的比例</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:100%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:94%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
			</tr> 
			
			<tr class="tr-odd">
				<td class="td-content-title" align="center">租赁物是否保险</td>
				<td class="td-content-title" align="center">保险单号码</td>
				<td class="td-content-title" align="center">保险期限</td>
				<td class="td-content-title" align="center">第一受益人</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:100%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.yxmoney" style="width:100%" name="contract_invest_info.yxmoney" class="mini-textbox" value="${requestScope['contract_invest_info.yxmoney'] }" label="年销售收入（万元）"/></td>
				<td class="td-content" colspan="1"><input id="contract_invest_info.ycurmoney" style="width:94%" name="contract_invest_info.ycurmoney" class="mini-textbox" value="${requestScope['contract_invest_info.ycurmoney'] }" label="年销售收入（万元）"/></td>
			</tr>
		</table>
			<br/>
	</fieldset>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("leasing_info_list");};
});
</script> --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input style="display:none;"	class="mini-textarea" id="id_json_leasing_info_list_str" name="json_leasing_info_list_str" value='${empty json_leasing_info_list_str ? "[]" : json_leasing_info_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "leasing_info_list",
		renderTo: "id_table_leasing_info_list",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: true,
		title: "",
		allowCellWrap: true,
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', 'remove'],
		data: JsonUtil.decode('${empty json_leasing_info_list_str ? "[]" : json_leasing_info_list_str }'),
		columns: [
			{type:'indexcolumn',width:9},
			{type:'checkcolumn',width:9},
			{field:'id', header:'id', visible:false},
			{field:'eqname', header:'设备名称', visible: true,
				     formEditorConfig:{
				    	 labelWidth:110,
				    	 required:true,
					     fieldVisible: true}
			},
			{field:'runthing', header:'设备运行状况',formEditorConfig:{
				newLine:true,required:true,
				width:'500px',height:'100px',
				colspan:3,type:'textarea'}
			}
		]
	});
});
</script>
<div id="id_table_leasing_info_list" style="width:100%;height:100%;"></div>