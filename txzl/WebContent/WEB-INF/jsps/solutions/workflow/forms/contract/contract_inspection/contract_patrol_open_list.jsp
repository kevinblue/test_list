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
				title:'客户巡视',
				iconCls:'icon-node',
				multiSelect:false,
				queryButtonColSpan:8,
				width:"100%",
				heigth:"100%",
				showPager:true,
			    params:{condtion:'CI.CONTRACT_STATUS>=15 and CI.CONTRACT_STATUS<=100'},
			    xmlFileName: '/eleasing/workflow/contract/contract_finish/contract_finish_list1.xml',
				
				tools:[{
						html:'客户巡视报告',
						plain:true,
						iconCls:'icon-addfolder',
						handler:function(miniTable, buttonText) {
							var row = miniTable.getSelected();
							var value;
							var name;
							if(row && row.id){
								var params = {};
								params.id = row.id;
								$.ajax({
									url : '${pageContext.request.contextPath}/acl/getGuarantor.acl',
									method : 'POST',
									async : false,
									data : params,
									success:function(res){
										var arr = res.split("+");
										value = arr[0];
										name = arr[1];
									},
									failure : function(res) {
										
									}
									
								});
								
								
	  								var multiform = new mini.Form("multiform");
	  								var multieditWindow = mini.get("multieditWindow");
	  								multiform.clear();
	  								tenwa.createQueryInput({
										id:'id_windfarmreportGarantee_list',
										label:'主要担保人',
										windowWidth: 450,
										windowHeight: 700,
										textField: 'name',
										valueField: 'value',
										params: {
												contractid:row.id,
											    xmlFileName:'/eleasing/workflow/proj/proj_credit/guarantee_list.xml'
										}
									});
									var selectedprojid = row.id;
									mini.get("projid").setValue(row.contract_id);
									mini.get("projectname").setValue(row.project_name);
									mini.get("id_windfarmreportGarantee_list").setValue(name);
									mini.get("id_windfarmreportGarantee_list").setText(value);
	  								multieditWindow.show();
							}else{
								mini.alert('请你选择需要发起的项目！！！');
							}
						}
					}         
				],
				columns: [
							{type: 'indexcolumn'},
							{type: 'checkcolumn'},
							{field: 'id', header: '合同id',visible: false},
							{field: 'projid', header: '项目id',visible: false},
							{field: 'custid', header: '用户id',visible: false},
							{field: 'contract_id', header: '合同编号'},
							{field: 'contract_number', header: '业务合同编号',queryConfig:{}},
							{field: 'project_name', header: '项目名称',queryConfig:{}},
							{field: 'cust_name', header: '客户名称',queryConfig:{newLine: true}},
							{field: 'card_no', header: '身份证/组织机构代码'},
							{field: 'industry_type', header: '内部行业'},
							{field: 'projmanagename', header: '项目经理'},
							{field: 'department', header: '出单部门'},
							{field: 'contractstatus', header: '合同状态'},
							
							/* {field: '', header: '----------',visible: false}, */
							
							{field: 'leasetype', header: '租赁种类',visible: false},
							{field: 'custname', header: '租赁物',visible: false},
							{field: 'leasemoney', header: '融资金额（万元）',visible: false},
							{field: 'leaseterm', header: '租赁期限(yue)',visible: false},
							{field: 'financing_balance', header: '融资余额（万元）',visible: false},
							{field: 'rentnum', header: '租金总期数',visible: false},
							{field: 'surplus', header: '剩余期数',visible: false},
							{field: 'avgrent', header: '每期平均租金',visible: false},
							{field: 'periodtype', header: '支付方式',visible: false},
							{field: 'incometime', header: '租金支付周期',visible: false},
							{field: 'department', header: '首次建立租赁关系时间',visible: false},
							{field: 'ouotlist', header: '逾期期数',visible: false},
							{field: 'outmoney', header: '逾期金额',visible: false},
							{field: 'currentoverage', header: '应收租赁款余额',visible: false},
							{field: 'custid', header: '客戶id',visible: false}
							
						]
			})
		});
	});

	
	
</script>
</head>
<body>
	<div id="id_table_id1" style="height: 100%"></div>
	<div id="multieditWindow" class="mini-window" title="风场月报" style="width:600px;height:190px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr class="tr-projectsss-info tr-even">
	             <td class="td-content-title" width="12%">合同编号：</td>
	             <td class="td-content" width="38%"><input name="contract_info.proj_id" readOnly id ="projid"  class="mini-textbox" label="项目编号"  type="text"></td>
	             <td class="td-content-title" width="12%">项目名称：</td>
	             <td class="td-content" width="38%"><input name="contract_info.project_name" readOnly id ="projectname"  class="mini-textbox" label="项目名称"  type="text"></td>
	            </tr>
                <tr>
                     <td style="width:80px;">报表年度：</td>
                    <td >
                    <input id="report_year_list" name="windfarmreportyear" class="mini-combobox miniext-form-fit" textField="text"  
	                     valueField="value"    data=""  
	                      allowInput="false"  onclick="getYearData"  />
                    
                    </td>
                    <td style="width:80px;">报表季度：</td>
                    <td >
                       <input id= "id_windfarmreportMonth_list" name="windfarmreportMonth" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'第一季度',value:'1'},{text:'第二季度',value:'2'},{text:'第三季度',value:'3'},{text:'第四季度',value:'4'}]"   allowInput="false"    value="第一季度"	  text="1"/>
                    </td>
                </tr>  
                <tr>
                   
                    <td style="width:80px;">主要担保人：</td>
                    <td >
                       <input id="id_windfarmreportGarantee_list" name="dunninginfo" class="mini-buttonedit mini-queryinput" allowInput = "false" />
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
</body>
</html>
<script type="text/javascript">


function submitMultiDataWindReport(e){
	var selectyear = mini.get("report_year_list").getValue();
	var quarter = mini.get("id_windfarmreportMonth_list").getValue();
	var guarantee = mini.get("id_windfarmreportGarantee_list").getValue();
	if("" == quarter){mini.alert("请选择报表季度！");return false;}
	if("" == selectyear){mini.alert("请选择报表年份！");return false;}
	var tablevalue = mini.get("table_id1");
	var row = tablevalue.getSelected();
	var rowStr=JSON.stringify(row);
	var attachmentParams = "id="+row.id+"&projid="+row.projid+"&custid="+row.custid+"&industryType="+row.industry_type+"&contractid="+row.id+"&quarter="+quarter+"&guarantee="+guarantee+"&selectyear="+selectyear+
	
	"&leasetype="+row.leasetype+
	"&custname="+row.custname+
	"&leasemoney="+row.leasemoney+
	"&leaseterm="+row.leaseterm+
	"&financing_balance="+row.financing_balance+
	"&rentnum="+row.rentnum+
	"&surplus="+row.surplus+
	"&avgrent="+row.avgrent+
	"&periodtype="+row.periodtype+
	"&incometime="+row.incometime+
	"&department="+row.department+
	"&ouotlist="+row.ouotlist+
	"&outmoney="+row.outmoney+
	"&currentoverage="+row.currentoverage+
	"&project_name="+row.project_name+
	"&contractnumber="+row.contract_number+
	"&cust_name="+row.cust_name;
	
	console.info(attachmentParams);

	/* var attachmentParams ={};
	attachmentParams["custid"] = row.custid;
	attachmentParams["industryType"] = row.industry_type;
	attachmentParams["contractid"] = row.id;
	attachmentParams["quarter"] = quarter;
	attachmentParams["guarantee"] = guarantee;
	attachmentParams["rowvalue"] = rowStr;
	attachmentParams["selectyear"] = selectyear; */
	startProcess("租后巡视-1",attachmentParams); 
	__userOperationClose();
};
function __userOperationClose(){
	mini.get(multieditWindow).hide();
}

function getYearData(){
	var myDate = new Date();
	var thisyear= myDate.getFullYear();
	var lastyear = thisyear-1;
	var yeardata = "[{text:'"+thisyear+"',value:'"+thisyear+"'},{text:'"+lastyear+"',value:'"+lastyear+"'}]";
	//console.info(yeardata);
	 mini.getbyName("windfarmreportyear").setData(yeardata);
}
</script>