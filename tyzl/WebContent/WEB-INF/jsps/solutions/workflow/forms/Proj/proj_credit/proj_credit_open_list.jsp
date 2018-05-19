<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>项目信审</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'项目信审',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,				
                xmlFileName:'/eleasing/workflow/proj/proj_approval/proj_approval_open_list.xml', 
				params:{checkcust:"and cic.creator_='"+"${sessionScope['login_userid']}"+"'"},
				tools:[
					{html:'项目信审',
					 plain:true,
					 iconCls:'icon-addfolder',
					 handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row){
								mini.get("id_table_projtype").show();
							}else{
								mini.alert('请你选择需要发起项目信审的客户！！！');
							}						
						}
					}         
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'}, 
				   	{field:'id',header:'id',visible:false},
				   	{field:'custname',header:'客户名称',allowSort:true,queryConfig:{}},
				   	{field:'custclass',header:'客户性质'},
				   	{field:'custkind',header:'内部行业',
				   		           queryConfig:{
                                       colspan:1,
							              type:'combobox', 
							        valueField:'value',
							         textField:'name',
							            params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
							        allowInput:true, 
							      showNullItem:true}},
				   	{field:'orgcodecardno',header:'身份证号/组织机构代码',queryConfig:{newLine:true}},
				   	{field:'creator',header:'登记人'},
				   	{field:'createdate',header:'登记时间',headerAlign:'center',renderer:"miniextonDateRenderer"}
				]
			});
		});
	});
	function changeprojtype()
	{
		var row=mini.get("table_id1").getSelected();
		if (miniui_ext.submitFormValidation(["id_table_projtype"]) == false) return false;
		var leasform=mini.get("proj_info.leasform").getValue();
		var leasformText=mini.get("proj_info.leasform").getText();

		var attachmentParams = "cust_id="+row.custid+"&leasform="+leasform+"&leasformText="+leasformText;
		mini.get("id_table_projtype").hide();
		startProcess("项目信审流程-1",attachmentParams);
	}
	
</script>
</head>
<body>
    <div id="id_table_projtype" class="mini-window" title="租赁形式"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<table style="width: 100%">
			<tr style="width: 100%;">
			    <td style="width: 20%;">租赁形式：</td>
				<td style="width: 70%;"><input name="proj_info.leasform" id='proj_info.leasform'
					class="mini-combobox" label="租赁形式" textField="name" required="true"
					valueField="value" dataField="datas" allowInput="false"
					data-options="{_xmlFileName:'combos/comboDict.xml',_params:{pid:'leas_form'}}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="comboboxChanged" style="width: 90%;"/> <font class="required-tag">*</font>
					<input id="rawValue_changemanage"
					name="rawValue_proj_info.leasform"
					class="mini-textbox" style="display: none" /></td>
			</tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="changeprojtype">确定</a>
			    &nbsp;&nbsp;
			    <a class="mini-button" iconCls="icon-save" plain="true" onclick="javascript:{mini.get('id_table_projtype').hide()}">取消</a>
			    </td>
			</tr>
		</table>
	</div>
</body>
</html>