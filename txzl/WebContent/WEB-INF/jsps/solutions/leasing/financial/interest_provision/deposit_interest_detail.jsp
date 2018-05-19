<%@page import="java.net.URLEncoder"%>
<%@page import="com.tenwa.business.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%String id=request.getParameter("id");%>
<title>资金预实明细详情</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<style>
#id_table_deposit_interest_detail {
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_deposit_interest_detail td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
}
#id_table_deposit_interest_detail td .mini-textbox-border{
	background: #EEEEEE;
}
#id_table_deposit_interest_detail td .mini-buttonedit-border{
	background: #EEEEEE;
}
#id_table_deposit_interest_detail .mini-buttonedit-buttons{
	display: none;
}
#id_table_deposit_interest_detail .mini-textbox{
	width: 100%;
}
#id_table_deposit_interest_detail .mini-buttonedit{
	width: 100%;
}
</style>
<script type="text/javascript">
var custid ="<%=id %>";
jQuery(function(){
	miniui_ext.setOddEvenRowsStyle("id_table_deposit_interest_detail");
});
</script>
</head>
<body>
<div style="width: 100%;height: 100%;overflow: auto;">
	<div id="form1" style="padding: 5px;">
		<table id="id_table_deposit_interest_detail" cellpadding="0" cellspacing="0" align="center">
			<tr>
		        <td width="12%">跟单意向：</td>
		        <td width="28%">
 		        	<input name="orderintent" class="mini-textbox"/>
		        </td>
		        <td width="10%"></td>
		        <td width="12%">客户名称：</td>
		        <td width="28%">
		        	<input name="lendername" class="mini-textbox"/> 
		        </td>
		        <td width="10%"></td>
		    </tr>
		       <tr>
		        <td>联系人：</td>
		        <td ><input  name="contactperson"  class="mini-textbox"/> </td>
		        <td></td>
		        <td>手机：</td>
		        <td><input  name="phone"  class="mini-textbox"/></td>
		        <td></td>	    
		    </tr>
		    <tr>
		        <td>单位电话：</td>
		        <td ><input  name="utilphone"  class="mini-textbox"/> </td>
		        <td></td>
		        <td>跟单阶段：</td>
		        <td><input  name="orderphase"  class="mini-textbox"/></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>职位：</td>
		        <td><input  name="position" class="mini-textbox"/>
		        </td>
		        <td></td>
		        <td >预计成交金额：</td>
		        <td><input  name="expectedturnover" class="mini-textbox" /></td>
		        <td></td>
		     </tr>
		     <tr>
		        <td>跟单人：</td>
		        <td >
					<input name="orderusername" class="mini-textbox" />
		        </td>
		        <td></td>
		        <td>预计成交日期：</td>
		        <td >
					<input name="expecteddate" class="mini-textbox" />
		        </td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>跟单状态：</td>
		        <td >
					<input name="orderstate" class="mini-textbox"/>
		        </td>
		        <td></td>
		        <td>跟单部门：</td>
		        <td >
					<input name="orderdepartment" class="mini-textbox" />
		        </td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>详情：</td>
		        <td colspan="4"><input  name="details" class="mini-textarea"/>
		        </td>
		        	        
		    </tr>	
		    
		    <tr>
		    	<td>下次提醒：</td>
		        <td><input  name="orderdate" class="mini-textbox"/>
		        </td>
		        <td></td>		        
		    </tr>		   
		</table>
	</div>
	<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="跟单产品" name="financingOrderProduct" iconCls="icon-cut">
	    	<jsp:include page="../deposit_interest/deposit_interest_product_before.jsp" ></jsp:include>
	    </div>	   
	    <div title="进展记录" name="financingOrderProcess" iconCls="icon-cut"> 
 	    	<jsp:include page="../deposit_interest/deposit_interest_process_before.jsp" ></jsp:include> 
 	    </div>    	
	</div>
<script>
mini.parse();
var form = new mini.Form("form1");
//跨页面传递的数据对象，克隆后才可以安全使用
$.ajax({
    url: urlPrefix+"/eleasing/jsp/finance/financeOrder.xml&id=<%=id%>",
    cache: false,
    success: function (text) {
    	var result = mini.decode(text);
    	miniui_ext.restoreFromDataSpecialChar(result.datas[0]);
        form.setData(result.datas[0]);
        miniui_ext.initcomboboxloaddata("form1",result.datas[0]);
        miniui_ext.initformreadOnly("form1");//设置表单为只读
        form.setChanged(false);
    }
});
</script>
</body>
</html>