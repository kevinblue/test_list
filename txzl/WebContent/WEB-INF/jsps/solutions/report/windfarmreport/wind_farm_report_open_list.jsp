<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>风电场报告</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				renderTo: "id_table_id1",
				width:globalClientWidth,
				height:globalClientHeight,
				title:'风电场报告流程',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:8,
				queryButtonNewLine:true,
				width:"100%",
				heigth:"100%",
				showPager:true,
				params:{projmanage:'${sessionScope.login_userid}'},
				xmlFileName:'/eleasing/workflow/proj/proj_credit/wind_farm_report_list.xml',
				
				tools:[
					{
						html:'年度风电场报告流程',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
	  							    var multiform = new mini.Form("multiform2");
	  								var multieditWindow = mini.get("multieditWindow2");
	  								multiform.clear();
									tenwa.createQueryInput({
										id:'id_windfarmreportYear_list',
										label:'年度风电场报告',
										windowWidth: 450,
										windowHeight: 700,
										textField: 'name',
										valueField: 'value',
										params: {
											    xmlFileName:'/eleasing/workflow/proj/proj_credit/wind_farm_report_year.xml'
										}
									});
	  								multieditWindow.show();
								//mini.alert('请你选择需要发起的项目！！！');
								
							/* mini.confirm("确定发起本年度风场报表?",'删除？',function(action){
								if(action == 'ok'){
									var attachmentParams = "proj_id=风电场年报";
									startProcess("风电场报告-1",attachmentParams); 
								}
							} );*/
							}
					}, "-",{
						html:'月度风电场报告流程',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							if(row && row.id){
	  								var multiform = new mini.Form("multiform");
	  								var multieditWindow = mini.get("multieditWindow");
	  								multiform.clear();
									var selectedprojid = row.id;
									mini.get("projid").setValue(row.projid);
									mini.get("projectname").setValue(row.projectname);
									tenwa.createQueryInput({
										id:'id_windfarmreportMonth_list',
										label:'月度风电场报告',
										windowWidth: 450,
										windowHeight: 700,
										textField: 'name',
										valueField: 'value',
										params: {
											selectedprojid:selectedprojid,
											    xmlFileName:'/eleasing/workflow/proj/proj_credit/wind_farm_report_month.xml'
										}
									});
	  								multieditWindow.show();
							}else{
								mini.alert('请你选择需要发起的项目！！！');
							}
						}
					}         
				],
				columns:[ 
					{type:'indexcolumn'},
					{type:'checkcolumn',visible:false},  
					{field:'id',header:'id',visible:false},
					{field:'globalcontractmessageid',header:'globalcontractmessageid',visible:false},
					{field:'projid',header:'项目编号',queryConfig:{}},
					{field:'custname',header:'客户名称',width : 100,queryConfig:{newLine:false,colspan:3}},
					{field:'projectname',header:'项目名称',width : 100,queryConfig:{}},
					{
						field : 'rzzlamount',
						dataType:"currency",
						header : '融资金额（万元）'
					},
					{
						field : 'leasformname',
						header : '项目类型'
					},
					{field:'projsource',header:'内部行业'},
					{field:'projdate',header:'立项时间'},					
					{field:'projmanager',header:'项目经理'},
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
	<div id="multieditWindow" class="mini-window" title="风场月报" style="width:550px;height:150px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr class="tr-projectsss-info tr-even">
	             <td class="td-content-title" width="12%">项目编号：</td>
	             <td class="td-content" width="38%"><input name="contract_info.proj_id" readOnly id ="projid"  class="mini-textbox" label="项目编号"  type="text"></td>
	             <td class="td-content-title" width="12%">项目名称：</td>
	             <td class="td-content" width="38%"><input name="contract_info.project_name" readOnly id ="projectname"  class="mini-textbox" label="项目名称"  type="text"></td>
	            </tr>
                <tr>
                    <td style="width:80px;">报表月份：</td>
                    <td >
                        <input id="id_windfarmreportMonth_list" name="dunninginfo" class="mini-buttonedit mini-queryinput" allowInput = "false" />
                    </td>
                </tr>   
                <tr >
                    <td >
                        <a class="mini-button" onclick="submitMultiDataWindReport">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                    <td >
                        <a class="mini-button" onclick='__userOperationClose'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
	<div id="id_table_id2" style="height: 100%"></div>
	<div id="multieditWindow2" class="mini-window" title="风场年报" style="width:450px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform2">
            <table>
                <tr>
                    <td style="width:80px;">报表年份：</td>
                    <td >
                        <input id="id_windfarmreportYear_list" name="dunninginfo" class="mini-buttonedit mini-queryinput" allowInput = "false" />
                    </td>
                </tr>   
                <tr >
                    <td >
                        <a class="mini-button" onclick="submitMultiDataWindReport2">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                    <td >
                        <a class="mini-button" onclick='__userOperationClose2'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
</html>
<script type="text/javascript">
function submitMultiDataWindReport(e){
	var windfarmreportmonth = mini.get("id_windfarmreportMonth_list").getValue();
	if("" == windfarmreportmonth){mini.alert("请选择报表月份！");return false;}
	var tablevalue = mini.get("table_id1");
	var row = tablevalue.getSelected();
	var rowid = row.id;
	var globalcontractmessageid = row.globalcontractmessageid;
	var attachmentParams = "proj_id="+rowid+";"+windfarmreportmonth+";风电场月报"+";"+globalcontractmessageid;
	startProcess("风电场报告-1",attachmentParams); 
	
};
function submitMultiDataWindReport2(e){
	var windfarmreportyear = mini.get("id_windfarmreportYear_list").getValue();
	if("" == windfarmreportyear){mini.alert("请选择报表年份！");return false;}
	var attachmentParams = "proj_id=风电场年报;"+windfarmreportyear;
	startProcess("风电场报告-1",attachmentParams); 
	
};
function __userOperationClose(){
	mini.get(multieditWindow).hide();
}
function __userOperationClose2(){
	mini.get(multieditWindow2).hide();
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