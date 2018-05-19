<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsps/solutions/leasing/cust_info/cust_comm/cust_comm.jsp"%>
<%-- <input id="id_json_relateperson_str" name="json_relateperson_str" class="mini-textarea" value='${empty json_relateperson_str ? "[]" : json_relateperson_str }' style="display: none;"/>
<div id="relatedperson_table" style="width: 100%;"></div> --%>
<style>
	#assets_sign_report_table tr td{border:1px dotted #888888;}
</style>
<div title="联系人" class="mini-panel" name="contentperson"style="width:100%;" iconCls="icon-node">
			<jsp:include page="assets_sign_cust_relateperson.jsp" >
			   <jsp:param value="true" name="isView"/>
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
				<td class="td-content" width="28%">${requestScope['cooperationattitude']}
				<input style="display:none" style="width:90%" name="cooperationattitude" id ="cooperationattitude"  class="mini-textarea" label="客户配合度" 
					textField="text" valueField="text"
					data="[{text:'良好'},{text:'一般'},{text:'较差'}]"
					type="text" value="${requestScope['cooperationattitude']}"></td>
				<td class="td-content" width="60%" > ${requestScope['cooperationattitudememo'] }
					<input style="display:none" style="width:95%;height:50px" label="客户配合度情况说明" id="cooperationattitudememo" name="cooperationattitudememo" value="${requestScope['cooperationattitudememo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>
		<tr class="tr-odd">
			<td class="td-content-title" width="12%">客户付款流程：</td>
			<td class="td-content" width="28%">${requestScope['custpayment'] }
				<input style="display:none" style="width:90%" label="客户付款流程内容" id="custpayment" name="custpayment" value="${requestScope['custpayment'] }" class="mini-textarea"  type="text" >  
			</td>
				<td class="td-content" width="60%">${requestScope['custpaymentmemo'] }
					<input style="display:none" style="width:95%;height:50px" label="客户付款流程情况说明" id="custpaymentmemo" name="custpaymentmemo" value="${requestScope['custpaymentmemo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>
		<tr class="tr-even">
			<td class="td-content-title" width="12%">调研问卷完成情况：</td>
			<td class="td-content" width="28%">${requestScope['studyallreal'] }
				<input style="display:none" style="width:90%" label="调研问卷内容" id="studyallreal" name="studyallreal" value="${requestScope['studyallreal'] }" class="mini-textarea"  type="text" >  
			</td>
				<td class="td-content" width="60%">${requestScope['studyallrealmemo'] }
					<input style="display:none" style="width:95%;height:50px" label="调查问卷情况说明" id="studyallrealmemo" name="studyallrealmemo" value="${requestScope['studyallrealmemo'] }" class="mini-textarea"  type="text" >  
				</td>
		</tr>	
		<tr class="tr-odd">
				<td class="td-content-title" width="12%">经营状况：</td>
				<td class="td-content" width="28%">${requestScope['hospitalstatus']}
				<input style="display:none" style="width:90%" name="hospitalstatus" id ="hospitalstatus"  class="mini-textarea" label="医院经营状况" 
					textField="text" valueField="text"
					data="[{text:'正常'},{text:'异常'}]"
					type="text" value="${requestScope['hospitalstatus']}" ></td>
				<td class="td-content" width="60%">${requestScope['hospitalmemo'] }
					<input style="display:none" style="width:95%;height:50px" label="异常情况说明" id="hospitalmemo" name="hospitalmemo" value="${requestScope['hospitalmemo'] }" class="mini-textarea"  type="text" >  
				</td>
			<td></td>
		</tr>
		 <tr class="tr-even">
			<td class="td-content-title">其他：</td>
			<td colspan="2" style="padding-top: 5px;padding-bottom: 5px;">${requestScope['another'] }
				<input style="display:none" style="width:97%;height:100px" label="其他" id="another" name="another" value="${requestScope['another'] }" class="mini-textarea"  type="text"  readOnly>  
			</td>
		</tr>
	</table>
</div>
<div id="other_table" style="width: 100%;"></div>
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
				height : 500,
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
		tenwa
				.createTable({
					id : 'relateperson',
					width : globalClientWidth,
					height : 250,
				//	iconCls : 'icon-node',
					renderTo : 'relatedperson_table',
					frozenStartColumn : 0,
					frozenEndColumn : 3,
					editFormPopupWindowWidth : 700,
					remoteOper : false,
					pageSize : 15,
					showToolbar: showTools,
					title : "联系人",
					showPager : true,
					lazyLoad : false,
					tools: ['add', '-', 'edit'],
					data: JsonUtil.decode('${empty json_relateperson_str ? "[]" : json_relateperson_str }'),
					columns : [  {type:'indexcolumn'},
								 {type:'checkcolumn'}, 
								 {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false,
			                              formEditorConfig:{
								                  readOnly:true,
								              fieldVisible:false }},
								 {field:'custid',header:'客户编号',headerAlign:'center',width:100,allowSort:true,visible:false,
							              formEditorConfig:{
								                  readOnly:true,
								              fieldVisible:false,
								              defaultValue:mini.getbyName("custid").getValue()}},
								 {field:'personname',header:'重要个人名称',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                      type:'text',
								         fillFromFieldName:'fatherid',
								              fillProperty:'id',
								              fieldVisible:true,
								                  required:true,
								                     width:200,
								                labelWidth:100}},
								 {field:'sex',header:'性别',visible:true,headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								                      type:'radiobuttonlist',
								               repeatItems: "0",
								              repeatLayout: "table",
								           repeatDirection: "vertical",
								                valueField:'text',
								                 textField:'text',
								                      data:[{text:'男'},{text:'女'}]}},
								 {field:'idcardno',header:'身份证号码',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                   newLine:true,
								                      type:'text',
								         fillFromFieldName:'fatherid',
								              fillProperty:'id',
								              fieldVisible:true,
								              onvalidation:"onIDCardsValidation",
								                     width:200,
								                labelWidth:100}},
								 {field:'birthdate',header:'出生年月',headerAlign:'center',width:100,allowSort:true,dateFormat:"yyyy-MM-dd",
							              formEditorConfig:{
								                      type:'date',
								                allowInput:"false",
								                    format:'yyyy-MM-dd',
								                     width:200}},
						         {field: 'rawValue_relationship', header: '关系', 
							              formEditorConfig:{
								              fieldVisible: false,
								         fillFromFieldName:'relationship',
								              fillProperty:'name'}},
								 {field:'relationship',header:'关系',visible:false,headerAlign:'center',width:100,allowSort:true,
			                              formEditorConfig:{
								                     width:200,
								                   newLine:true,
								              fieldVisible: true,
								              showNullItem:"true", 
								              nullItemText:"",
								                 emptyText:"",
								                      type:'combobox',
								                    params: {pid: 'relationship',xmlFileName: '/combos/comboDict.xml'},
								                 textField:'name',
								                valueField:'value'}},
								 {field:'mainpersonflag',header:'是否主联系人',visible:true,headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								              showNullItem:"true", 
								              nullItemText:"",
								                 emptyText:"",
								                      type:'combobox',
								                valueField:'text',
								                 textField:'text',
								                      data:[{text:'是'},{text:'否'}]}},
								 {field:'sendperson',header:'是否寄件人',visible:true,headerAlign:'center',width:100,allowSort:true,
											formEditorConfig:{
											      newLine:true,
									             required:true,
												      width:200,
												 showNullItem:"true", 
												   nullItemText:"",
												     emptyText:"",
												    type:'combobox',
												    valueField:'text',
												 textField:'text',
												     data:[{text:'是'},{text:'否'}]}},
								  {field:'mailadd',header:'邮寄地址',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                   newLine:true,
								                     width:"100%",
								                   colspan:3,
								                      type:'text',
								                   newLine:true,
								              fieldVisible:true}},
								  {field:'postcode',header:'邮编',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                   newLine:true,
								                     width:200,
								                      type:'text',
								              fieldVisible:true,
								              onvalidation:"onPostcodeValidation"}},
								  {field:'mobilenumber',header:'手机',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								                      type:'text',
								              fieldVisible:true,
								              onvalidation:"onMoblieValidation"}},
								  {field:'phone',header:'电话',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								                   newLine:true,
								                      type:'text',
								              fieldVisible:true}},
								 {field:'email',header:'email',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								                      type:'text',
								              fieldVisible:true,
								                     vtype:"email"}},
						         {field: 'rawValue_unitposition', header: '职务', 
							              formEditorConfig:{
								              fieldVisible: false,
								         fillFromFieldName:'unitposition',
								              fillProperty:'name'}},
								 {field:'unitposition',header:'职务',visible:false,headerAlign:'center',width:100,allowSort:true,
							           	  formEditorConfig:{
								                     width:200,
								                   newLine:true,
								              fieldVisible: true,
								              showNullItem:"true", 
								              nullItemText:"",
								                 emptyText:"",
								                      type:'combobox',
								                    params: {pid: 'cust_jobposition',xmlFileName: '/combos/comboDict.xml'},
								                 textField: 'name',
								                valueField: 'value'}},
								 {field:'servicelife',header:'任职年限',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:200,
								                      type:'text',
								              fieldVisible:true}},
								 {field:'domicileplace',header:'户口所在地',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                   colspan: 3,
							 	                   newLine:true,
								                     width:"100%",
							 	                      type:'text',
							 	              fieldVisible:true}},
							 	 {field:'homeadd',header:'常住地址',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                   colspan: 3,
								                   newLine:true,
								                     width:"100%",
								                      type:'text',
								              fieldVisible:true}},
								 {field:'cpmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,
							              formEditorConfig:{
								                     width:"100%",
								                   newLine:true,
								                   colspan: 3,
								                      type:'textarea',
								              fieldVisible:true}},										
						         {field: 'creatorname',header:'登记人',	headerAlign:'center',width:100,allowSort:true,				
							              formEditorConfig:{
								                   newLine:true,
								                      type:'text',
								                  readOnly:true,
								                     value:'${sessionScope.loginUser.realname}',
								                labelWidth:100,								
								                     width:200}},						
						         {field: 'createdate',header:'登记时间',	headerAlign:'center', dateFormat:"yyyy-MM-dd ",width:100,allowSort:true, 							
							              formEditorConfig:{								
								                      type:'text',
								                  readOnly:true,		
								              defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd"),
								                labelWidth:100,								
								                     width:200 }},
						        {field: 'modificatorname',header:'修改人',	headerAlign:'center' ,width:100,visible:false,allowSort:true,
							              formEditorConfig:{
								                   newLine:true,
								                      type:'text',
								                  readOnly:true,
								              fieldVisible:true,
								                labelWidth:100,								
								                     width:200}},
						       {field: 'modifydate',header:'修改时间',	headerAlign:'center', visible:false,dateFormat:"yyyy-MM-dd ",width:100,allowSort:true,
							              formEditorConfig:{									
								                      type:'text',
								                  readOnly:true,
								              fieldVisible:true,
								                labelWidth:100,								
								                     width:200 								
								} 				   		}]
				});
	});
	
</script>

