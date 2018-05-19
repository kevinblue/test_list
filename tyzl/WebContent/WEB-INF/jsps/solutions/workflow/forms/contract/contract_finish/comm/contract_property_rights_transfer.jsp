<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="contract_property_rights_transfer" title="产权转移单">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-odd">
			<td class="td-content-title" width="11%">转移价格：</td>
			<td class="td-content" width="37%" style="text-indent: 0px;">
				<input id="contract_end_info.endprice" vtype="float" label="转移价格" name="contract_end_info.endprice" class="mini-textbox"   value="<mini:param  name="contract_end_info.endprice"/>"  required="true" allowInput="true" />
			</td>
			<td class="td-content-title"  width="13%">接收方名称：</td>
			<td class="td-content"  width="39%" style="text-indent: 0px;">
				<input id="contract_end_info.endcust" label="接收方名称" name="contract_end_info.endcust" class="mini-textbox"   value="<mini:param  name="contract_end_info.endcust"/>"   required="true" allowInput="true" />
			</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title"  width="13%">接收时间：</td>
			<td class="td-content" style="text-indent: 0px;"  width="38%">
				<input id="contract_end_info.enddate" label="接收时间" name="contract_end_info.enddate" class="mini-datepicker"  value="<mini:param  name="contract_end_info.enddate"/>"   required="true" allowInput="false" />
			</td>
			<td class="td-content-title" colspan="2" style="text-indent: 0px;"><a class="mini-button" id="id_btn_create_transfer_word" iconCls="icon-ok" onclick="createTransferWord()">生成产权转移清单</a></td>
		</tr>
	</table>
	<span class="module-content-title">清单列表</span>
	<div id="id_table_contract_property_rights_transfer"></div>
	<jsp:include page="contract_property_rights_transfer_word.jsp"></jsp:include>
</div>
<script type="text/javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_property_rights_transfer");
           mini.get("id_btn_create_transfer_word").hide();
		};
});
//生成产权转移清单
function createTransferWord(){
	var params={};
	params["flowunid"] = flowUnid;
	getPageData(params);
	var fileTeplate=new fileTemplateConfig({
		templateno : 'F-201412003',
		tableid : 'contract_property_rights_transfer',
		modelname : '产权转移清单',
		returntype : 'listtocurpage',
		parames : params
	});
	fileTeplate.createFile();
	saveCallBack();
}
function getPageData(params) {

}
</script>