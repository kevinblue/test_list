<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<script type="text/javascript">
	var invoiceid = "<mini:param  name='invoiceId'/>";
	var toolsFlag = "<mini:param  name='toolsFlag'/>";
	var buttonFlag = "<mini:param  name='buttonFlag'/>";
</script>
<title>新增进项发票详细信息</title>
<%@include file="/base/mini.jsp"%>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/tracywindy/workflow.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<style type="text/css">

</style>
</head>
<body style="overflow: auto;">
<div id="id_table_vat_invocie_detail">
	<div id="mini-panel" title="发票基本信息" showCollapseButton="true" style="width: 100%;" >
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" id="contract_base_info">
			<tr>
		        <td style="padding:5px">
		        	<a class="mini-button" iconCls="icon-save" onclick="saveInvoice">保存</a>
		        	<span class="separator"></span>
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		        </td>
		    </tr>
		</table>
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%;" >   
		    <tr style="background:#468CC8;height:32px" ><td style="padding-left:10px" colspan='4'><font color="white">发票基本信息</font></td></tr>
		    <tr class="tr-odd">
		        <td class="td-content-title" width="12%">发票号：</td>
		        <td class="td-content" width="38%">
 		        	 <input width="55%" name="invoiceno" class="mini-textbox " type='text' required="true" requiredErrorText="发票号不能为空"/>
		        </td>
		        <td class="td-content-title" width="12%" >发票金额：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="invoicemoney" class="mini-textbox" required="true" onblur="checkAmt()" requiredErrorText="发票金额不能为空"/>
		        </td>
		    </tr>
		    <tr class="tr-even">
		        <td class="td-content-title" width="12%">购货单位：</td>
		        <td class="td-content" width="38%">
		            <input width="55%" name="purchasenits" class="mini-textbox" required="true" requiredErrorText="购货单位不能为空"/>
		        </td>
		        <td class="td-content-title" width="12%">供应商：</td>
		        <td class="td-content" width="38%">
					<input width="55%" id="supplier" name="supplier" class="mini-buttonedit mini-queryinput" allowInput="false" required="true" requiredErrorText="供应商不能为空"/>
		        </td>
		    </tr>
		    <tr class="tr-odd">
		    	<td class="td-content-title" width="12%">登记日期：</td>
		        <td class="td-content" width="38%">
					<input width="55%" class="mini-datepicker" name="recorddate" allowinput="false" required="true" requiredErrorText="发票号不能为空">
					</input>
		        </td>
		        <td class="td-content-title" width="12%">货物名称：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="goodsname" class="mini-textbox" textField="name" required="true" requiredErrorText="货物名称不能为空"/>
		        </td>
		     </tr>
		     <tr class="tr-even" style="height:40px">
		        <td class="td-content-title" width="12%" height="70px">备注：</td>
		        <td class="td-content" colspan="3">
					<input style="width: 81%;height: 90%" name="memo" class="mini-textarea"/	>
		        </td>
		    </tr>
		    <tr class="tr-odd">
		        <td class="td-content-title" width="12%">创建人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="creatorname" class="mini-textbox" readonly/>
		        </td>
		        <td class="td-content-title" width="12%">创建时间：</td>
		        <td class="td-content" width="38%">
		        	<input width="55%" name="createdate" class="mini-textbox"  readonly/>
		        </td>
		    </tr>
		    <tr class="tr-even">
		    	<td class="td-content-title" width="12%">修改人：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modificator" class="mini-textbox" readonly/>
		        </td>
		    	<td class="td-content-title" width="12%">修改时间：</td>
		        <td class="td-content" width="38%">
					<input width="55%" name="modifydate" class="mini-textbox" readonly/>
		        </td>
		    </tr>
		</table>
	</div>
	</div>
<!-- 页签 -->
	<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="合同选择" name="relatedInvoice" >
	    	<jsp:include page="vat_invoice_contract.jsp" ></jsp:include>
	    </div>	 
</div>


<script>
//初始化
mini.parse("id_table_vat_invocie_detail");
var form = new mini.Form("id_table_vat_invocie_detail");
$.ajax({
    url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record.xml&id=<mini:param  name='invoiceId'/>",
    cache: false,
    success: function (text) {
    	var result = mini.decode(text);//把字符串反序列化为JS对象
    	var data = result.datas[0];
    	if('edit' ==  buttonFlag){
        	if(data.createdate.length != 0){
        		data.createdate = (data.createdate).format("yyyy-MM-dd");
            }
        	if(data.modifydate.length != 0){
        		data.modifydate = (data.modifydate).format("yyyy-MM-dd");
        	}
        } else {
            data = {};
        	data.createdate = new Date().format("yyyy-MM-dd");
        	data.creatorname = "${sessionScope.login_realname}";
        }
        form.setData(data);//设置数据
        if('edit' ==  buttonFlag){
        	mini.get("supplier").setText(data.suppliername);
        }
        //miniui_ext.initcomboboxloaddata("id_table_vat_invocie_detail",result.datas[0]);//设置combobobox text值
        //miniui_ext.initformenabled("form1");//设置表单为只读
        form.setChanged(false);
    }
});
jQuery(function(){
	tenwa.createQueryInput({
		id:'supplier',
		label:'供应商',
		windowWidth: 600,
		params: {
			cust_type: 'cust_type.vndr',
			xmlFileName: '/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'
		}
	});
});

function saveInvoice(){
	var invoicemoney = parseFloat(mini.getbyName('invoicemoney').getValue());
	form.validate();
    if (form.isValid() == false) return false;
    
	if("add" == buttonFlag){
		var minitable = mini.get("vat_invoice_contract");
		var moneydatas = minitable.getData();
		var summary = 0.0;
		for(var i=0;i<moneydatas.length;i++){
			summary += parseFloat(moneydatas[i].registeredamt);
		}
		if(summary>invoicemoney){
			mini.alert("已登记金额大于发票金额");
			return false;
		}
		//发票号不能重复
		var param = {};
		var invoiceno = mini.getbyName('invoiceno').getValue();
		param.invoiceNo = invoiceno;
		param['xmlFileName'] = "/eleasing/jsp/invoice_manage/vat_invoice/vat_invoiceid_check.xml";
		var flag = true;
		ajaxRequest({
			params:param,
			url:'${pageContext.request.contextPath}/table/getTableData.action',
			method : 'POST',
			async : false,
			success:function(response){
				var jsondata = eval('(' + response.responseText + ')').datas;
				if(jsondata.length > 0 ){
					mini.alert('已存在该进项发票号，请重新输入！！');
					flag = false;
					}else{
						flag = true;
					}
			},
			failure:function(response){
						mini.alert("校验失败！");
						flag = false;
					}
			});
		if(!flag) return flag; 
		var datas = form.getData();
		datas.recorddate = (datas.recorddate).format("yyyy-MM-dd");
	    var operType = "保存";
		var operTitle = "发票信息";
		var operAction = "saveVatInvoiceContract";
		var contractData=mini.get("vat_invoice_contract").getData();
		datas.contractData = mini.encode(contractData);
		var url=getRootPath()+"/acl/"+operAction+".acl";
			jQuery.ajax({
				url : url,
				type : 'POST',
				timeout : 30 * 1000,
				data : datas,
				dataType : 'json',
				error : function(res, errInfo, e) {
					mini.unmask(document.body);
				},
				success : function(resultJson) {
					var returnState = resultJson.returnType;
					switch (returnState) {
					case "SUCCESS": {
						mini.unmask(document.body);
						mini.alert(operType+operTitle + " 成功！",'保存',function(){
							window.opener.refresh();
							window.close();
						});
						break;
					};
					case "FAILURE": {
						mini.unmask(document.body);
						mini.alert(operType+operTitle + " 失败！");
					}
					}
				}
			});
		} else {
			//发票号不能重复
			var invoiceno = mini.getbyName('invoiceno').getValue();
			var param1 = {};
			param1.invoiceid = invoiceid;
			param1['xmlFileName'] = "/eleasing/jsp/invoice_manage/vat_invoice/vat_invoiceamt_check.xml";
			ajaxRequest({
				params:param1,
				url:'${pageContext.request.contextPath}/table/getTableData.action',
				method : 'POST',
				async : false,
				success:function(response){
					var jsondata = eval('(' + response.responseText + ')').datas;
					var totalamt = parseFloat(jsondata[0].totalamt);
					if(invoicemoney < totalamt ){
						mini.alert('累计登记金额超过发票金额，请重新输入！！');
						flag = false;
					}else{
						flag = true;
					}
				},
				failure:function(response){
					mini.alert("校验失败！");
					flag = false;
				}
			});
			if(!flag){
				return flag;
			}			
			var invoiceno = mini.getbyName('invoiceno').getValue();
			var param = {};
			param.id = invoiceid;
			param.invoiceNo = invoiceno;
			param['xmlFileName'] = "/eleasing/jsp/invoice_manage/vat_invoice/vat_invoiceid_check.xml";
			var flag = true;
			ajaxRequest({
				params:param,
				url:'${pageContext.request.contextPath}/table/getTableData.action',
				method : 'POST',
				async : false,
				success:function(response){
					var jsondata = eval('(' + response.responseText + ')').datas;
					if(jsondata.length > 0 ){
						mini.alert('已存在该进项发票号，请重新输入！！');
						flag = false;
						}else{
							flag = true;
						}
				},
				failure:function(response){
							mini.alert("校验失败！");
							flag = false;
						}
				});
			if(!flag) return flag; 
			var datas = form.getData();
			datas.invoiceid = invoiceid;
			datas.recorddate = (datas.recorddate).format("yyyy-MM-dd");
		    var operType = "保存";
			var operTitle = "发票信息";
			var operAction = "updateVatInvoiceContract";
			var contractData=mini.get("vat_invoice_contract").getData();
			datas.contractData = mini.encode(contractData);
			var url=getRootPath()+"/acl/"+operAction+".acl";
				jQuery.ajax({
					url : url,
					type : 'POST',
					timeout : 30 * 1000,
					data : datas,
					dataType : 'json',
					error : function(res, errInfo, e) {
						mini.unmask(document.body);
					},
					success : function(resultJson) {
						var returnState = resultJson.returnType;
						switch (returnState) {
						case "SUCCESS": {
							mini.unmask(document.body);
							mini.alert(operType+operTitle + " 成功！",'保存',function(){
								window.opener.refresh();
								window.close();
							});
							break;
						}
						case "FAILURE": {
							mini.unmask(document.body);
							mini.alert(operType+operTitle + " 失败！");
							break;
						}
						}
					}
				});
		}
};
function checkAmt(){
	var invoicemoney = Number(mini.getbyName("invoicemoney").getValue());
		var minitable = mini.get("vat_invoice_contract");
		var moneydatas = minitable.getData();
		var summary = 0.0;
		for(var i=0;i<moneydatas.length;i++){
			summary += parseFloat(moneydatas[i].registeredamt);
		}
		if(summary>invoicemoney){
			mini.alert("已登记金额大于发票金额");
			return false;
		}	
}
jQuery(function(){
	$(".mini-textbox-readOnly .mini-textbox-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-border").css("background", "#F0F0F0");
	$(".mini-buttonedit-readOnly .mini-buttonedit-button").css("display", "none");
});
</script>
</body>
</html>