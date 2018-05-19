<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同审批</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id:"contract_approval",
		renderTo:"id_table_container_contract_approval",
		width:'100%',
		height:'100%',
		iconCls :'icon-node',
		lazyLoad:false,
		title:"合同审批",
		remoteOper :false,
		showPager:true,
		pageSize:20,
		tools:[{
			html:'合同审批',
			plain:true,
			iconCls:'icon-addfolder',
			handler:function(miniTable, buttonText){
				var row = miniTable.getSelected();
				if (row) {
				//	var multiform = new mini.Form("multiform");
				//	var multieditWindow = mini.get("multieditWindow");
				//	multieditWindow.show();
					var attachmentParams = "proj_id="+row.id;
					startProcess("合同出具流程-1",attachmentParams); 
				} else {
					mini.alert('请选中需要操作的数据！！！');
				}
			}
		  }],
		queryButtonColSpan:4,
		xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_approval_list.xml',
		columns:[
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
			{field:'proj_id', header:'项目编号'},
			{field:'cust_name', header:'客户名称',width:135,queryConfig:{width:160}},
			{field:'project_name', header:'项目名称',queryConfig:{width:160}},
			
			{field:'cleanleasemoney',header:'合同金额'},
			{field:'projtypename',header:'项目类型',visible:false},
			{field:'leasformname',header:'项目类型'},
			{field:'industry_type', header:'内部行业',
			    queryConfig:{
				      width:160,
				    newLine:false,
				    colspan:1,
				       type:'combobox',
				 valueField:'value',
				  textField:'name',
	   			     params:{xmlFileName:'combos/comboDict.xml',pid:'cust_kind'},
			   showNullItem:true}},
			{field:'projmanagename', header:'项目经理',queryConfig:{newLine:true,width:160}},			
			{field:'proj_status', header:'项目状态'},
			{field:'department', header:'出单部门'},
			{field:'card_no', header:'身份证/组织机构代码'},
			
		   	{field:'provincename',header:'所在省份'},
		   	{field:'projsourcename',header:'项目来源'},
			{field:'linkman',header:'联系人'}
			
		]
	});
});
</script>
</head>
<!-- <body>
<div id="multieditWindow" class="mini-window" title="请选择" style="width:30%;height:130px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="multiform" >
		<table class="fillTable" cellspacing="0" cellpadding="10px" style="width:100%;" >
		 	<tr class="tr-even">
				<td class="td-content-title" style="width:80px;">是否拆包：</td>
				<td class="td-content">
					<div name="proj_info.businesstype" id="proj_info.businesstype" class="mini-combobox"
						data="[{text:'是',id:'business_type.yes'},{text:'否',id:'business_type.no'}]"
						textField="text" valueField="id"
						value="business_type.yes" required="true"
					></div>
				</td>
				</tr> 
				<tr>
				<td align="center" colspan="2">
					<a class="mini-button " iconCls="icon-save"  onclick="startPro">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
					<span class="separator"></span>
					<a class="mini-button " iconCls="icon-close"  onclick='cancel'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
				</td>
			</tr>   
		</table>
	</div>
</div> -->
<div id="id_table_container_contract_approval" style="height:100%;"></div>
</body>
</html>
<!-- <script type="text/javascript">
function cancel(){
	mini.get("multieditWindow").hide();
}
function startPro(){
	var businesstyp = mini.get("proj_info.businesstype").getValue()
	var row = mini.get("contract_approval").getSelected();;
	if(row){
		var attachmentParams = "proj_id="+row.id+"&businesstype="+businesstyp;
		startProcess("合同出具流程-1",attachmentParams); 
		mini.get("multieditWindow").hide();
	}else{
		mini.alert("请选中要操作的数据！");
	}
}
function creditValidate(row){
	if(!row.companyid){
		return true;
	} 
	var msg="请检查该客户以下字段是否填写: </br>";
	var flag = true;
	/* if(!row.person_idcard){
		msg += "<font color='#FF0000'>证件号码</font></br>";
		flag=false;
	} 
	 if(!row.maila_ddress){
		msg += "<font color='#FF0000'>邮寄地址</br>";
		flag=false;
	} */
	if(row.tax_reg_type == 'tax_level_name.1' && !row.tax_reg_code){
		msg += "<font color='#FF0000'>纳税人识别号</br>"
		flag=false;
	}
	if(row.tax_reg_type == 'tax_level_name.1' && !row.tax_bank){
		msg += "<font color='#FF0000'>开户行</br>";
		flag=false;
	}
	if(row.tax_reg_type == 'tax_level_name.1' && !row.tax_acc){
		msg += "<font color='#FF0000'>开户账号</br>";
		flag=false;
	}
	if(row.tax_reg_type == 'tax_level_name.1' && !row.invoice_add){
		msg += "<font color='#FF0000'>开票地址</br>";
		flag=false;
	}
	if(row.tax_reg_type == 'tax_level_name.1' && !row.invoice_phone){
		msg += "<font color='#FF0000'>开票电话</br>";
		flag=false;
	}
	if(!flag){
		mini.alert(msg);
	}
	return flag;
}
</script> -->