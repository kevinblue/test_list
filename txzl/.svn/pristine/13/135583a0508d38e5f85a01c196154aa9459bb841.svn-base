<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsps/solutions/leasing/cust_info/cust_comm/cust_comm.jsp"%>
<style>
	#assets_sign_report_table tr td{border:1px dotted #888888;}
</style>
<div title="联系人" class="mini-panel" name="contentperson" style="width:100%;">
			<jsp:include page="assets_sign_cust_relateperson.jsp" >
			   <jsp:param value="false" name="isView"/>
			</jsp:include>
		</div>
<div id="assets_sign_report_info" class="mini-panel" title="现场信息反馈" showCollapseButton="true" style="width:100%;">
	<table id="assets_sign_report_table" cellspacing="0" cellpadding="0" style="width:100%" class="fillTable">
		<tr class="tr-even" style="font-weight:bold">
				<td class="td-content-title" width="12%" >重点关注信息</td>
				<td class="td-content" width="28%" align="center" >内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容</td>
				<td class="td-content" width="60%" align="center">情况说明</td>
		</tr>
		<tr class="tr-even">
				<td class="td-content-title" width="12%">客户配合度：</td>
				<td class="td-content" width="28%"><input style="width:90%" name="cooperationattitude" id ="cooperationattitude"  class="mini-combobox" label="客户配合度" 
					textField="text" valueField="text"
					data="[{text:'良好'},{text:'一般'},{text:'较差'}]"
					type="text" value="${requestScope['cooperationattitude']}"></td>
				<td class="td-content" width="60%">
					<input style="width:95%;height:50px" label="客户配合度情况说明" id="cooperationattitudememo" name="cooperationattitudememo" value="${requestScope['cooperationattitudememo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">客户付款流程：</td>
			<td class="td-content" width="28%">
				<input style="width:90%" label="客户付款流程内容" id="custpayment" name="custpayment" value="${requestScope['custpayment'] }" class="mini-textbox"  type="text" >  
			</td>
				<td class="td-content" width="60%">
					<input style="width:95%;height:50px" label="客户付款流程情况说明" id="custpaymentmemo" name="custpaymentmemo" value="${requestScope['custpaymentmemo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" width="12%">调研问卷完成情况：</td>
			<td class="td-content" width="28%">
				<input style="width:90%" label="调研问卷内容" id="studyallreal" name="studyallreal" value="${requestScope['studyallreal'] }" class="mini-textbox"  type="text" >  
			</td>
				<td class="td-content" width="60%">
					<input style="width:95%;height:50px" label="调查问卷情况说明" id="studyallrealmemo" name="studyallrealmemo" value="${requestScope['studyallrealmemo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>	
		<tr class="tr-odd">
				<td class="td-content-title" width="12%">经营状况：</td>
				<td class="td-content" width="28%"><input style="width:90%" name="hospitalstatus" id ="hospitalstatus"  class="mini-combobox" label="医院经营状况" 
					textField="text" valueField="text"
					data="[{text:'正常'},{text:'异常'}]"
					type="text" value="${requestScope['hospitalstatus']}" ></td>
				<td class="td-content" width="60%">
					<input style="width:95%;height:50px" label="异常情况说明" id="hospitalmemo" name="hospitalmemo" value="${requestScope['hospitalmemo'] }" class="mini-textarea"  type="text" >  
				</td>
			<td></td>
		</tr>
		 <tr class="tr-even">
			<td class="td-content-title">其他：</td>
			<td colspan="2" style="padding-top: 5px;padding-bottom: 5px;">
				<input style="width:97%;height:100px" label="其他" id="another" name="another" value="${requestScope['another'] }" class="mini-textarea"  type="text"  readOnly>  
			</td>
		</tr>
	</table>
</div>
<div id="other_table" style="width: 100%;" ></div>
<input id="id_json_other_str" name="json_other_str"  value='${empty json_other_str ? "[]" : json_other_str }' style="display: none;"/>
<script>
jQuery(function() {
	var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
		miniui_ext.disableFormFields("assets_sign_report_info");
		$('#assetssignbtn').html("");
	}
	tenwa
			.createTable({
				id : 'other',
				width : globalClientWidth-40,
				height :500,
			//	iconCls : 'icon-node',
				renderTo : 'other_table',
				frozenStartColumn : 0,
				remoteOper : false,
				pageSize : 15,
				showToolbar: showTools,
				title : "其他",
				allowCellWrap:true,
				showPager : true,
				lazyLoad : false,
				tools: ['add', '-', 'edit'],
				data: JsonUtil.decode('${empty json_other_str ? "[]" : json_other_str }'),
				columns : [  {type:'indexcolumn',width:6},
							 {type:'checkcolumn',width:6}, 
							 {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
		                              formEditorConfig:{
							                  readOnly:true,
							              fieldVisible:false }},
							 {field:'matters' , header:'事项',width:20,
								 formEditorConfig:{
									 width:500
								 }},
							 {field:'explain' , header:'说明',
									 formEditorConfig:{
										 width:500,
										 height:100,
										 newLine:true,
										 type:"textarea"
									 } }
							 ]
			});
});
</script>
<script>
	jQuery(function() {
		var showTools = true;
		if ('${param.isView}' == 'true') {
			showTools = false;
			miniui_ext.disableFormFields("assets_sign_report_info");
			$('#assetssignbtn').html("");
		}
	});
	
</script>

