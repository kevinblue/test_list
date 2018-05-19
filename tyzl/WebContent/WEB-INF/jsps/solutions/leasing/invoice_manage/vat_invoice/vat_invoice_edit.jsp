<%@page import="com.tenwa.business.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>进项发票登记</title>

<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>

</head>
<body>

<div class="mini-fit">
	<div id="form1">
	<input name="id" class="mini-textbox" style="display: none;"/>
		<table class="miniext-form-table">
		    <tr>
		        <td style="width:100px;">客户名称：</td>
		        <td>
		            <input id="custid" name="custid" class="mini-combobox miniext-form-fit" textField="custname" 
	                  	   valueField="custid"  
						   dataField="datas"
						   allowInput="true" 
						   data-options="{_xmlFileName:'/eleasing/jsp/invoice_manage/vat_invoice/cust_select.xml'}" 
						   onbeforeshowpopup="onbeforeshowpopup"
						   onvaluechanged="onCustChanged"/>
		        </td>
		        <td style="width:120px;">合同号：</td>
		        <td>
		            <input id="contractid" name="contractid" class="mini-combobox miniext-form-fit" textField="contractnumber" 
	                  	   valueField="contractid"  
						   dataField="datas"
						   allowInput="true" 
						   data-options="{_xmlFileName:'/eleasing/jsp/invoice_manage/vat_invoice/contract_select.xml'}" 
						   onvaluechanged="onContractChanged"
						   onbeforeshowpopup="onbeforeshowpopup"/>
		        </td>
		    </tr>
		    <tr>
		    	<td >费用类型：</td>
		        <td>
					<input name="feetype" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="value"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'FeeType'}}" 
						   onbeforeshowpopup="onbeforeshowpopup"
					/>
		        </td>
		        <td >发票种类：</td>
		        <td>
					<input name="invoicetype" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="value"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'invoicetype'}}" 
						   onbeforeshowpopup="onbeforeshowpopup"
					/>
		        </td>
		     </tr>
		     <tr>
		        <td class="miniext-form-td">发票号码：</td>
		        <td>
					<input name="invoiceno" class="mini-textbox miniext-form-fit"/>
		        </td>
		        <td class="miniext-form-td">发票金额：</td>
		        <td>
					<input name="invoicemoney" class="mini-textbox miniext-form-fit" />
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">登记时间：</td>
		        <td>
					<input name="recorddate" class="mini-datepicker miniext-form-fit asLabel" readonly value="new Date()"/>
		        </td>
		    	<td class="miniext-form-td">登记人：</td>
		        <td>
					<input name="creator" class="mini-textbox miniext-form-fit asLabel" readonly value="<mini:user/>"/>
		        </td>
		    </tr>
		    
		</table>
	</div>
</div>
<div class="mini-toolbar miniext-toolbar-bottom">
    <table class="miniext-form-fit">
        <tr>
            <td>
                <a class="mini-button" iconCls="icon-add" onclick="save">保存</a>
                <a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
            </td>
        </tr>
    </table>           
</div>
<script>
mini.parse();
var form = new mini.Form("form1");
var opertype="<mini:param  name='opertype'/>";
function onbeforeshowpopup(e) {
	miniui_ext.onbeforeshowpopup(e);
}
function $mini(id){
	return mini.get(id);
}

function onCustChanged(e) {
	var custCombo = mini.get("custid");	
	var contractCombo = mini.get("contractid");
    var custid = e.selected.custid;
    contractCombo["data-options"]="{_xmlFileName:'/eleasing/jsp/invoice_manage/vat_invoice/contract_select.xml',_params:{custid:'"+custid+"'}}";
    alert(contractCombo["data-options"]);
    contractCombo.setValue("");
    contractCombo.select(0);
    
    //alert(mini.decode(custCombo));
    //{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'invoicetype'}}
    /*
    var tmp = "";
    for(var p in custCombo){
        tmp += p + "===>" + custCombo[p] + "\n";
        }
    */
    //console.log(tmp);
    //alert(custCombo);
    //contractCombo.setValue("123");
    
    //var url = "../data/AjaxService.aspx?method=GetPositionsByDepartmenId&id=" + id
    //contractCombo.setUrl(url);
    //contractCombo.select(0);
}

function onContractChanged(e){
	//选择合同号后将对应的客户带入到客户名称域
	var custCombo = mini.get("custid");	//获得客户选择控件
	custCombo.setValue(e.selected.custid);
	custCombo.setText(e.selected.custname);
}

function SetData(data) {
	var form = new mini.Form("form1");
    //跨页面传递的数据对象，克隆后才可以安全使用
    data = mini.clone(data);
    $.ajax({
        url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_list.xml&id=" + data.id,
        cache: false,
        success: function (text) {
        	var result = mini.decode(text);
            form.setData(result.datas[0]);
            miniui_ext.initcomboboxloaddata("form1",result.datas[0]);
            form.setChanged(false);
        }
    });
}
function save(e){
	var o = form.getData(true,true); 
    if (miniui_ext.fromvalidation('form1') == false) return;
    var url="";
    if(opertype=="add"){
		url="<%=request.getContextPath() %>/acl/addTaxVatInfo.acl";
	}else{
		url="<%=request.getContextPath() %>/acl/updateTaxVatInfo.acl";
	}
    var json = mini.encode(o);
    
	$.ajax({
        url: url,
        type: "post",
        data:  o ,
        success: function (text) {
        	CloseWindow("savesuccess");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            mini.alert(jqXHR.responseText);
        }
    });
}
function CloseWindow(action) {            
    if (action == "close" && grid.isChanged()) {
        if (confirm("数据被修改了，是否先保存？")) {
            return false;
        }
    }
    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
    else window.close();            
}
function onCancel(e) {
    CloseWindow("cancel");
}
function onCloseClick(e) {
    var obj = e.sender;
    obj.setText("");
    obj.setValue("");
}
</script>
</body>
</html>