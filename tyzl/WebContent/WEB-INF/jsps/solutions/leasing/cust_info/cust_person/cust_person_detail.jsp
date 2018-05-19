<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>自然人客户信息管理</title>
<script>
var custid ="<mini:param  name='id'/>";
</script>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<style>
#id_table_cust_detail {
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_cust_detail td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
}
#id_table_cust_detail td .mini-textbox-border{
	background: #EEEEEE;
}
#id_table_cust_detail td .mini-buttonedit-border{
	background: #EEEEEE;
}
#id_table_cust_detail .mini-buttonedit-buttons{
	display: none;
}
#id_table_cust_detail .mini-textbox{
	width: 100%;
}
#id_table_cust_detail .mini-buttonedit{
	width: 100%;
}
</style>
<script type="text/javascript">
jQuery(function(){
	miniui_ext.setOddEvenRowsStyle("id_table_cust_detail");
});
</script>
</head>
<body>
<div style="width: 100%;height: 100%;overflow: auto;">
<!-- 撑满布局 -->
	<div id="form1" style="padding: 5px;">
		<table id="id_table_cust_detail" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		    	<td colspan="6" style="border-bottom: 1px solid #99CCFF;">
		    		<a class="mini-button" iconCls="icon-edit" onclick="upd">编辑</a>&nbsp;&nbsp;<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
		    	</td>
		    </tr>
		    <tr>
		        <td width="12%">客户名称：</td>
		        <td width="28%">
 		        	<input name="custid" class="mini-textbox" style="display: none;"/>
 		        	<input name="personid" class="mini-textbox" style="display: none;"/>
		            <input name="custname" class="mini-textbox  asLabel" requiredErrorText="客户名称不能为空"/>
		        </td>
		        <td width="10%"></td>
		        <td width="12%">性别：</td>
		        <td width="28%"><input name="sex" class="mini-combobox asLabel"/></td>
		        <td width="10%"></td>
		    </tr>
		    <tr>
		        <td>身份证号码：</td>
		        <td><input name="idcardno" class="mini-textbox asLabel"/></td>
		        <td></td>
		        <td>出生日期：</td>
		        <td><input name="brithdate" class="mini-datepicker asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>客户规模（根据1104报表要求）：</td>
		        <td ><mini:dict  name="custscale"  parentid="cust_scale" otherClass="asLabel"/></td>
		        <td></td>
		        <td>客户来源：</td>
		        <td><mini:dict  name="custsource"  parentid="cust_source" otherClass="asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>客户类别：</td>
		        <td><div class="mini-combobox asLabel" name="custtype" /></div></td>
		        <td></td>
		        <td>客户内部行业：</td>
		        <td><input name="custkind" class="mini-combobox asLabel" /></td>
		        <td></td>
		     </tr>
		     <tr>
		        <td>客户所属行业门类：</td>
		        <td>
					<input id="industry" name="industry" class="mini-combobox asLabel" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_bypid.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"
						   showNullItem="true" nullItemText="" emptyText=""/>
		        </td>
		        <td></td>
		        <td>行业门类大类：</td>
		        <td>
					<input id="industrylevelbig" name="industrylevelbig" class="mini-combobox asLabel" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"
						   showNullItem="true" nullItemText="" emptyText=""/>
		        </td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>行业门类中类：</td>
		        <td >
					<input id="industrylevelmiddle" name="industrylevelmiddle" class="mini-combobox asLabel" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"
						   showNullItem="true" nullItemText="" emptyText=""/>
		        </td>
		        <td></td>
		        <td>行业门类小类：</td>
		        <td >
					<input id="industrylevelsmall" name="industrylevelsmall" class="mini-combobox asLabel" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"
						   showNullItem="true" nullItemText="" emptyText=""/>
		        </td>
		        <td></td>
		    </tr>
		     <tr>
		        <td>国家：</td>
		        <td><input name="country" class="mini-combobox asLabel"/></td>
		        <td></td>
		        <td>省份：</td>
		        <td><input name="province" class="mini-combobox asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>城市：</td>
		        <td><input name="city" class="mini-combobox asLabel" /></td>
		        <td></td>
		        <td>区县：</td>
		        <td><input name="county" class="mini-textbox asLabel"/>
		        </td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>邮寄地址：</td>
		        <td colspan="4"><input name="mailadd" class="mini-textbox  asLabel" width="100%"/></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>邮编：</td>
		        <td><input name="postcode" class="mini-textbox asLabel"/></td>
		        <td></td>
		    	<td>手机：</td>
		        <td><input name="mobilenumber" class="mini-textbox asLabel"/> </td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>电话：</td>
		        <td ><input name="phone" class="mini-textbox asLabel"/></td>
		        <td></td>
		        <td>Email：</td>
		        <td><input name="email" class="mini-textbox asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>户口所在地：</td>
		        <td colspan="4"><input name="domicileplace" class="mini-textbox asLabel" width="100%"/> </td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>常住地址：</td>
		        <td colspan="4"><input name="oftenaddr" class="mini-textbox  asLabel" width="100%"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>婚烟状态：</td>
		        <td><input name="maritalstatus" class="mini-combobox asLabel"/></td>
		        <td></td>
		        <td>学历：</td>
		        <td ><input name="school" class="mini-combobox asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>传真：</td>
		        <td><input name="faxnumber" class="mini-textbox asLabel"/></td>
		        <td></td>
		        <td>职务：</td>
		        <td><input name="unitposition" class="mini-combobox asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>工作单位：</td>
		        <td colspan="4"><input name="unitname" class="mini-textbox asLabel"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>客户概况：</td>
		        <td colspan="4"><input name="ceimemo" class="mini-textarea  asLabel" width="100%"/></td>
		        <td></td>
		    </tr>
		    <tr>
		        <td>是否草稿：</td>
		        <td><input name="draft" class="mini-combobox asLabel" /></td>
		        <td></td>
		        <td>是否废弃：</td>
		        <td><div name="invalid" class="mini-combobox asLabel"></div></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>登记人：</td>
		        <td><input name="creatorname" class="mini-textbox asLabel" /></td>
		        <td></td>
		        <td>登记时间：</td>
		        <td><input name="createdate" class="mini-datepicker asLabel" value="new Date()"/></td>
		        <td></td>
		    </tr>
		     <tr>
		    	<td>登记部门</td>
		        <td><input name="deptmentname" class="mini-textbox asLabel"/></td>
		        <td></td>
		        <td></td>
		        <td></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td>修改人：</td>
		        <td><input name="modificatorname" class="mini-textbox asLabel"/></td>
		        <td></td>
		        <td>修改时间：</td>
		        <td><input name="modifydate" class="mini-datepicker asLabel"/></td>
		        <td></td>
		    </tr>
		    
		</table>
	</div>
	<!-- 页签-->
	<div id="tabDeatils" class="mini-tabs" activeIndex="0" style="width: 100%;" bodyStyle="padding:0;border:0;">
	    <div title="重要个人" name="relatedPerson" iconCls="icon-cut">
	    	<jsp:include page="../cust_relatedperson/cust_relatedperson_list.jsp" ></jsp:include>
	    </div>	 
	  
	   <div title="银行账号" name="bankAccount" iconCls="icon-cut"> 
	        <jsp:include page="../cust_account/cust_account.jsp" ></jsp:include>
	    </div> 
	      
	    <div title="走访调研" iconCls="icon-cut">
 	    	<jsp:include page="../cust_contact/cust_contact_list.jsp" ></jsp:include> 
 	    </div> 
 	 
	    <div title="关联企业" name="custSharecompany" iconCls="icon-cut" >
	    	<jsp:include page="../cust_share_company/cust_share_company.jsp" ></jsp:include>
	    </div>
	    <div title="历史交易记录" name="custHistory" iconCls="icon-cut" >
	    	<jsp:include page="../cust_history/cust_history_list.jsp" ></jsp:include>
	    </div>
	</div>
</div>
<script>
//初始化
mini.parse();
var form = new mini.Form("form1");
$.ajax({
    url: "<%=request.getContextPath() %>/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=/eleasing/jsp/cust_info/cust_person/get_custinfoperson_byid.xml&id=<mini:param  name='id'/>",
    cache: false,
    success: function (text) {
    	var result = mini.decode(text);//把字符串反序列化为JS对象
    	var data = result.datas[0];
    	miniui_ext.restoreFromDataSpecialChar(data);
        form.setData(data);//设置数据
        miniui_ext.initcomboboxloaddata("form1",result.datas[0]);//设置combobobox text值
        miniui_ext.initformreadOnly("form1");//设置表单为只读
        form.setChanged(false);
    }
});
function upd(){
	var urlFlag = getRootPath()+"/leasing/cust_info/cust_person/input_cust_person_list.bi?id=<mini:param  name='id'/>";
	var saveUrl = getRootPath() + "/acl/updateCustEwlp.acl";
	mini.open({
        url: urlFlag+"&opertype=update&saveurl="+saveUrl,
        title: "编辑自然人信息", width: 800, height: 500,
        onload: function () {
        	
        },
        ondestroy: function (action) {
        	if("savesuccess" == action){
        		window.location.reload();
        	}
        }
    });
}
</script>
</body>
</html>