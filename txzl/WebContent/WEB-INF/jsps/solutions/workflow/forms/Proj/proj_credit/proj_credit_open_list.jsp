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
				renderTo: "id_table_id1",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'项目信审流程发起',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:8,
				width:"100%",
				heigth:"100%",
				showPager:true,
				params:{projmanage:'${sessionScope.login_userid}'},
				xmlFileName:'/eleasing/workflow/proj/proj_credit/proj_credit_info_list.xml',
				tools:[
					{
						html:'项目信审',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row && row.id){
								//if(!creditValidate(row)){
								//	return ;
								//}
								/* if("是"==mini.decode(row).draft){
										mini.alert('该项目承租人仍为潜在客户状态，请在客户信息中修改为非潜在客户状态后再发起流程！');
										return;
								} */
								var attachmentParams = "proj_id="+row.id;
				        		startProcess("项目信审流程-1",attachmentParams); 
							}else{
								mini.alert('请你选择需要发起信审流程的项目！！！');
							}
						}
					}         
				],
				columns:[ 
					{type:'indexcolumn'},
					{type:'checkcolumn'},  
					{field:'id',header:'id',visible:false},
					{field:'projid',header:'项目编号'},
					{field:'custname',header:'客户名称',width : 100,queryConfig:{}},
					{field:'projectname',header:'项目名称',width : 100,queryConfig:{}},
					{
						field : 'rzzlamount',
						dataType:"currency",
						header : '融资金额（万元）'
					},
					{
						field : 'leasformname',
						header : '项目类型'
					/* 	queryConfig : {
							type : 'combobox',
							textField : 'name',
							valueField : 'value',
							fillFromFieldName : 'value',
							showNullItem : "true",
							nullItemText : "", //显示空值
							params : {
								pid : 'proj_kind',
								xmlFileName : '/combos/comboDict.xml'
							}
						}, */
						
					},
					{field:'projsource',header:'内部行业'},
					{field:'projdate',header:'立项时间',queryConfig:{type:'date',newLine:false,range:true,format:'yyyy-MM-dd'}},					
					{field:'projmanager',header:'项目经理',queryConfig:{newLine:true}},
					{field:'dept',header:'出单部门'},
					
					{field:'projstatus',header:'项目状态',headerAlign:'center',align:'center'},
					{field:'draft',header:'是否草稿',visible:false,headerAlign:'center',align:'center'}
					,{field:'companyid',header:'',visible:false}
					,{field:'person_idcard',header:'',visible:false}
					,{field:'maila_ddress',header:'',visible:false}
					,{field:'tax_reg_type',header:'',visible:false}
					,{field:'tax_reg_code',header:'',visible:false}
					,{field:'tax_bank',header:'',visible:false}
					,{field:'tax_acc',header:'',visible:false}
					,{field:'invoice_add',header:'',visible:false}
					,{field:'invoice_phone',header:'',visible:false}
				]	
			})
		});
	});

</script>
</head>
<body>
	<div id="id_table_id1" style="height: 100%"></div>
</body>
</html>
<script type="text/javascript">
function creditValidate(row){
	if(!row.companyid){
		return true;
	} 
	var msg="请检查该客户以下字段是否填写: </br>";
	var flag = true;
	/* if(!row.person_idcard){
		msg += "<font color='#FF0000'>证件号码</font></br>";
		flag=false;
	} */
	if(!row.maila_ddress){
		msg += "<font color='#FF0000'>邮寄地址</br>";
		flag=false;
	}
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
</script>