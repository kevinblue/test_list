<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>凭证信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var statusdata = [{name : '有效',value : '0'},{name : '无效',value : '1'}];
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'凭证信息',
				iconCls:'icon-node',
				multiSelect:true,
				hiddenQueryArea:true,
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				xmlFileName:'/voucher/voucher_list.xml',
				tools:[{
					html:'导出凭证',
					plain:true,
					iconCls:'icon-addfolder',
					handler:function(miniTable, buttonText) {
						var checkedRowDatas = miniTable.getSelecteds();
						if(0 == checkedRowDatas.length){
							alert("请选择要导出开票的记录！！");
							return;
						}
						var tempIdArr = [];
						for(var i = 0;i<checkedRowDatas.length;i++){
							var checkedRowdata = checkedRowDatas[i];
							if("1"==checkedRowdata.v8flag){
								alert("凭证号："+checkedRowdata.vouchernumber+"已导出,不能导出!!!" );
								return false;
							}
							var id = checkedRowdata.vouchernumber;
							tempIdArr.push("'"+id+"'");
						}
						var fileTeplate=new fileTemplateConfig({
			            	 templateno:'F-201412008',
			            	 tableid:"datagrid1",
			                 modelname:"凭证导出",
			                 returntype:'listtonewpage',
	                         jscallbak :function(tableid){
	                        	 mini.get("table_id1").reload();
	                         },
			                 parames:{
			                	//importorexportcallback:'exportRentIncomeInvoiceBefore',
			                	importorexportcallbackafter:'exportVoucherAfter',
			                    ids:tempIdArr.join(",")  
			                    //splitfield:'planmoney',
			                    //rangevalue:'100'
			                 }
			            });
			            fileTeplate.createFile();
					}
				 }
				],
				columns:[ 
				    {type:'indexcolumn'},
				   	{type:'checkcolumn'},  
				   	{field:'id',header:'id',visible:false},
				   	{field:'vouchernumber',header:'凭证号',queryConfig:{}},
				   	{field:'modlename',header:'系统模块',queryConfig:{}},
				   	{field:'dept_name',header:'所属公司'},
				   	{field:'f1',header:'记账日期'},
				   	{field:'f15',header:'业务日期'},				   	
				   	{field:'f2',header:'凭证字'},
				   	{field:'evidencetype',header:'基本类别'},
				   	{field:'status_',header:'凭证状态'},
				   	{field:'v8flag',header:'导出状态'},
				   	{field:'f15',header:'明细',
				   		renderer:function(e){
							var vouchernumber = e.record.vouchernumber;
							return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + vouchernumber +"\")'> 明细 </a>";
						}
				   	},
				   	{field:'contract_number',header:'合同号',queryConfig:{}},
				   	{field:'v8memo',header:'导出备注'},
				   	{field:'f5',header:'摘要'},
				   	{field:'expdate',header:'导出日期'},
				   	{field:'generatedate',header:'生成日期'}
				]
			});
		});
	});
    function opencustdetail(vouchernumber){
        var url=getRootPath()+"/voucher/voucher_detail/voucherass_info_detail.bi?vouchernumber="+vouchernumber;
        var sheight = window.screen.availHeight - 30;
	    var swidth = window.screen.availWidth - 10;
	    var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	    window.open(url, '_blank', winoption);
    }
</script>
</head>
<body></body>
</html>