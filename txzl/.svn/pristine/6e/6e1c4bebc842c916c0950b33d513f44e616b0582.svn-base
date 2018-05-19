<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<%   
//操作类型、ID、操作URL
String opertype=request.getParameter("opertype");
String id=request.getParameter("id");
String saveurl=request.getParameter("saveurl");
%>
<title>法人客户信息管理</title>
<%@include file="/base/mini.jsp"%>
<%@include file="../cust_comm/cust_comm.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<style type="text/css">
#id_table_cust_info_edit {
	background: #FAFAFA;
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_cust_info_edit td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
	border-bottom: 1px dotted #CCCCCC;
}
.mini-textbox-readOnly .mini-textbox-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-buttons{
	display: none;
}
</style>
</head>
<body>
<!-- 撑满布局 -->
<div class="mini-fit">
	<div id="form1" style="padding: 5px 40px 5px 40px;" title="法人客户信息">
		<table id="id_table_cust_info_edit">
		    <tr>
		        <td style="width:120px;">客户名称：</td>
		        <td>
  		        	<input id="custid" name="custid" class="mini-textbox" style="display: none;"/>
  		        	<input id="companyid" name="companyid" class="mini-textbox" style="display: none;"/>
		            <input id="custname" name="custname" label="客户名称" class="mini-textbox miniext-form-fit" vtype="maxLength:100"/> 
		        </td>
		       <td class="miniext-form-td">客户简称：</td>
		       <td>
		           <input id="custshorthand" name="custshorthand" label="客户简称" class="mini-textbox miniext-form-fit" vtype="maxLength:100"/>
               </td>
		    </tr> 
		    <tr>
		        <td style="width:120px;">注册号/统一社会信用代码：</td>
		        <td>
		            <input id="orgcode" name="orgcode" label="注册号/统一社会信用代码" class="mini-textbox miniext-form-fit" required="true" onvalidation="checkORGRight"/>
		        </td>
		        <td class="miniext-form-td">客户规模：</td>
		        <td><mini:dict  name="custscale" otherproperties=" label='客户规模' " parentid="cust_scale"/> </td>
		<!--         <td class="miniext-form-td">总资产(万元)：</td>
		        <td>
		       	 <input id="custscale" name="custscale" label="总资产(万元)" class="mini-textbox miniext-form-fit" vtype="int"/>
		        </td> -->
		    </tr>
		    <tr>
		       
		        <td class="miniext-form-td">客户来源：</td>
		        <td><mini:dict  name="custsource" otherproperties=" label='客户来源' " parentid="cust_source"/> </td>
		        <td>客户类别：</td>
		        <td  colspan="1">
					<div class="mini-combobox miniext-form-fit" id="custtype"
						popupWidth="100%" textField="name" valueField="value" name="custtype"
					    data-options="{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'cust_type'}}"   
					    multiSelect="true" onbeforeshowpopup="miniextonbeforeshowpopup" allowInput="false" dataField="datas"
					    showClose="true" oncloseclick="onCloseClick" 
					    emptyText="" required="true" label='客户类别'
					    text = '承租人'
					    value = 'cust_type.cust'
					    onvaluechanged="custtypeonvaluechanged">     
					    <div property="columns"> <div header="客户类别" field="name"></div></div>
					</div>
		        </td>
		    </tr>
		    
		   
		 
<%-- 		    <tr id="hospitaltype01" >
		        <td>医院类型：</td>
		        <td><mini:dict  name="hospitaltype" otherproperties=" label='医院类型' " parentid="hospital_type"/> </td>
		        <td>行政级别：</td>
		        <td><mini:dict  name="adminlevel" otherproperties=" label='行政级别' " parentid="admin_level"/> </td>
		     </tr> --%>
		     <tr>
		        <td class="miniext-form-td">客户所属行业门类：</td>
		        <td >
					<input id="industry" name="industry" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_bypid.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"  onvaluechanged="onindustrychanged"
						   showNullItem="true" nullItemText="" emptyText="" label='客户所属行业门类'/>
		        </td>
		        <td class="miniext-form-td" style="width:100px;">行业门类大类：</td>
		        <td >
					<input id="industrylevelbig" name="industrylevelbig" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelbig','industry','客户所属行业门类')" onvaluechanged="onindustrylevelbigchanged"
						   showNullItem="true" nullItemText="" emptyText="" label='行业门类大类'/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td" style="width:100px;">行业门类中类：</td>
		        <td >
					<input id="industrylevelmiddle" name="industrylevelmiddle" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelmiddle','industrylevelbig','行业门类大类')" onvaluechanged="onindustrylevelmiddlechanged"
						   showNullItem="true" nullItemText="" emptyText="" label='行业门类中类'/>
		        </td>
		        <td class="miniext-form-td" style="width:100px;">行业门类小类：</td>
		        <td >
					<input id="industrylevelsmall" name="industrylevelsmall" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   showNullItem="false"
						   onbeforeshowpopup="changecommon('industrylevelsmall','industrylevelmiddle','行业门类中类')"
						   showNullItem="true" nullItemText="" emptyText="" label='行业门类小类'/>
		        </td>
		    </tr>
		    <tr>
		        <td >内部行业：</td>
		        <td><input name="custkind" id="custkind" class="mini-combobox" textField="name" required="true" label="内部行业 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'cust_kind'}}" onbeforeshowpopup="onbeforeshowpopup" 
						onvaluechanged="comboboxChanged(e)" /> </td>
						
					        <td class="miniext-form-td" style="width:80px;">与金风关系：</td>
		        <td><mini:dict  name="grouprelationship" otherproperties=" label='与集团关系' " parentid="group_relationship"/> </td> 		
	<!-- 	        <td >子行业：</td>
		        <td><input name="subcustkind" id="subcustkind" class="mini-combobox" textField="name" required="true" label="子行业" valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:''}}" 
								   onbeforeshowpopup="onpurchasetypetwobeforeshowpopup"
								   onEnter="search" />	 </td> -->
		     </tr>
		    <tr>
		    	 <td class="miniext-form-td">纳税人类别：</td>
		        <td><mini:dict  name="taxregtype"  parentid="tax_level_name" otherproperties="onvaluechanged='taxregtypeonvaluechanged' label='纳税人类别'"/></td>
		        <td class="miniext-form-td">纳税人识别号：</td>
		        <td><input name="taxregcode" class="mini-textbox miniext-form-fit" vtype="maxLength:50" label='纳税人识别号'/></td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">开户行：</td>
		        <td><input name="taxbank" class="mini-textbox miniext-form-fit" label='开启行' vtype="maxLength:100"/></td>
		    	<td class="miniext-form-td">开户账号：</td>
		        <td><input name="taxacc" class="mini-textbox miniext-form-fit" label='开启账号' vtype="maxLength:50"/></td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">开票地址：</td>
		        <td><input name="invoiceadd" class="mini-textbox miniext-form-fit" label='开票地址' vtype="maxLength:100"/>  </td>
		    	<td class="miniext-form-td">开票电话：</td>
		        <td><input name="invoicephone" class="mini-textbox miniext-form-fit" label='开票电话' vtype="maxLength:100"/></td>
		    </tr>
		     <tr>
		        <td class="miniext-form-td">国家：</td>
		        <td>
					<input id="country" name="country" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="国家"
						   dataField="datas"
						   allowInput="false" 
						   url="${urlPrefix}/eleasing/jsp/sysmgr/sysdatamgr/pro_city_list.xml"
						   text = "中华人民共和国"
						   value = "CHN" 
						   onvaluechanged="oncountrychanged"
						   showNullItem="true" nullItemText=""
						   emptyText=""
					/>
		        </td>
		        <td class="miniext-form-td">省份：</td>
		        <td>
					<input id="province" name="province" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="省份"
						   dataField="datas"
						   allowInput="false" 
						   onvaluechanged="onprovincechanged"
						   showNullItem="true" nullItemText=""
						   emptyText=""
					/>
		        </td>
       
		    </tr>
		    <tr>
		        <td class="miniext-form-td">城市：</td>
		        <td>
					<input id="city" name="city" class="mini-combobox miniext-form-fit" label='城市' textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""/>
		        </td>
		        <td class="miniext-form-td">区县：</td>
		        <td ><input name="county" class="mini-textbox miniext-form-fit" label='区县' vtype="maxLength:200"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">邮寄地址：</td>
		        <td colspan="3"><input name="mailaddress" class="mini-textbox miniext-form-fit2" label='邮寄地址' vtype="maxLength:500"/> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">法定代表人：</td>
		        <td><input name="personrep" class="mini-textbox miniext-form-fit" label='法人代表' vtype="maxLength:100"/></td>
		        <td class="miniext-form-td" style="width:120px;">主营业务收入(万元):</td>
		    	<td>
		    	<input id="mainrevenue" name="mainrevenue" class="mini-textbox miniext-form-fit" label='主营业务收入(万元)' vtype="int"/>
		    	</td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">法人代表证件类型：</td>
		        <td><mini:dict  name="documenttype" otherproperties=" label='法人代表证件类型' onvaluechanged='documenttypechange()'" parentid="document_type"/> </td>
		    	<td class="miniext-form-td">证件号码：</td>
		        <td><input name="personidcard" class="mini-textbox miniext-form-fit" label='法人身份证号' onvalidation="onIDCardsCompanyValidation"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">手机：</td>
		        <td><input name="moblie" class="mini-textbox miniext-form-fit" label='手机' onvalidation = "onMoblieValidation"/> </td><!--  onvalidation = "onMoblieValidation" -->
		        <td class="miniext-form-td">电话：</td> 
		        <td><input name="phone" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/>
		        </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">邮编：</td>
		        <td><input name="postcode" class="mini-textbox miniext-form-fit" onvalidation = "onPostcodeValidation" /></td>
		        <td class="miniext-form-td">传真：</td>
		        <td><input name="fax" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/></td>
		    </tr>
		    <tr>
		  <%--       <td class="miniext-form-td">归属人：</td>
		        <td><input name="belongingpeoplename" class="mini-textbox miniext-form-fit asLabel" readonly value="${empty requestScope['belongingpeoplename'] ? sessionScope.login_realname:requestScope['belongingpeoplename']}" }" vtype="maxLength:50"/>
		        	<input name="belongingpeople" class="mini-textbox miniext-form-fit asLabel" value="${empty requestScope['belongingpeoplename'] ? sessionScope.login_userid:requestScope['belongingpeoplename']}"] }" visible="false" />
		        </td> --%>	             
		        <td class="miniext-form-td">实际控制人：</td>
		        <td ><input name="custactualpeople" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>
		        
		        
		         <td class="miniext-form-td">成立时间：</td>
		        <td ><input name="establishdate" class="mini-datepicker miniext-form-fit" vtype="maxLength:50" /> </td>
		        
		    </tr>
		    <tr>
		        <td class="miniext-form-td">是否有贷款卡：</td>
		        <td>
					<input name="creditcardyesorno" class="mini-combobox miniext-form-fit" textField="text" 
	                  	   valueField="text"  
						   data="[{text:'是'},{text:'否'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   onvaluechanged="oncreditcardyesorno"/>
		        </td>
		        <td class="miniext-form-td">贷款卡号：</td>
		        <td ><input id="creditcard" name="creditcard" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">员工人数：</td>
		        <td><input name="empnumber" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/></td>
		        <td class="miniext-form-td">企业性质：</td>
		        <td><mini:dict  name="ownership"  parentid="ownership"/>  </td>
		    </tr>
		    <tr>
		       <!--  <td class="miniext-form-td">营业执照号：</td>
		        <td><input name="buslicense" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td> -->
		 
		        <td class="miniext-form-td">注册资本：</td>
		        <td colspan="3"><input name="regcapital" width=540 class="mini-textbox miniext-form-fit" /> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">注册地址：</td>
		        <td colspan="3"><input name="regaddress" class="mini-textbox miniext-form-fit2" vtype="maxLength:1000"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">经营地址：</td>
		        <td colspan="3"><input name="companyaddress" class="mini-textbox miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">公司网址：</td>
		        <td colspan="3">	<input name="companyurl" class="mini-textbox miniext-form-fit2" vtype="maxLength:1000"/> </td>		        
		    </tr>
	<!-- 	    <tr>
		        <td class="miniext-form-td">经营方式：</td>
		        <td colspan="3"><input name="operate" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr> -->
		    <tr>
		        <td class="miniext-form-td">经营范围<br/>(主营)：</td>
		        <td colspan="3"><input name="operatemaster" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
	<!-- 	    <tr>
		        <td class="miniext-form-td">经营范围<br/>(兼营)：</td>
		        <td colspan="3"><input name="operateminor" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr> -->
		    <tr>
		        <td class="miniext-form-td">客户概况：</td>
		        <td colspan="3"><input name="custprobably" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">是否潜在客户：</td>
		        <td>
					<div id="draft" name="draft" class="mini-radiobuttonlist"
						data="[{text:'是'},{text:'否'}]"
						textField="text" valueField="text"
					    value="否"  onvaluechanged="draftchanged">
					</div>
		        </td>
		        <td class="miniext-form-td">是否废弃：</td>
		        <td>
					<div id="invalid" name="invalid" class="mini-radiobuttonlist"
						data="[{text:'是'},{text:'否'}]"
						textField="text" valueField="text"
					    value="否">
					</div>
		        </td>
		    </tr>
		     <td class="miniext-form-td">是否验证：</td>
		        <td>
					<div id="validate1" name="validate1" class="mini-radiobuttonlist"
						data="[{text:'是'},{text:'否'}]"
						textField="text" valueField="text"
					    value="是">
					</div>
		        </td>
		        <td class="miniext-form-td"></td>
		        <td></td>
		    <tr>
		    	<td class="miniext-form-td">登记人：</td>
		        <td><input name="creatorname" class="mini-textbox miniext-form-fit asLabel" readonly value="${sessionScope.loginUser.realname}"/> </td>
		        <td class="miniext-form-td">登记时间：</td>
		        <td><input name="createdate" class="mini-datepicker miniext-form-fit asLabel" readonly value="new Date()"/> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">登记部门</td>
		        <td>
		        <input id="id_department" name="department" label="登记部门" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"/>
		        </td>
		        <td class="miniext-form-td"></td>
		        <td></td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">修改人：</td>
		        <td><input name="modificatorname" class="mini-textbox miniext-form-fit asLabel" readonly/> </td>
		        <td class="miniext-form-td">修改时间：</td>
		        <td><input name="modifydate" class="mini-datepicker miniext-form-fit asLabel" readonly/></td>
		    </tr>
		    
		</table>
	</div>
</div>
<div class="mini-toolbar miniext-toolbar-bottom">
    <table class="miniext-form-fit">
        <tr>
            <td>
                <a class="mini-button" onclick="save" style="width:80px">确定</a>&nbsp;&nbsp;
                <a class="mini-button" onclick="onCancel" style="width:80px">取消</a>
            </td>
        </tr>
    </table>           
</div>
<script>
<!--初始化-->
mini.parse();
var form = new mini.Form("form1");
var opertype="<%=opertype%>";
function $mini(id){
	return mini.get(id);
}
//客户行业联动子行业
function comboboxChanged(e){
	mini.get("subcustkind").setValue("");
	mini.get("subcustkind").setText("");
	
	var custkind=e.selected;
	if(custkind.name!='医疗'){
		document.getElementById("hospitaltype01").style.display="none";
	}else{
		document.getElementById("hospitaltype01").style.display="";
	}
}
function onpurchasetypetwobeforeshowpopup(e){
	var purchasetypeone = mini.get("custkind");
	purchasetypeone.validate();
	var cb=e.sender;
	var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';
	var xmlFileName = cb._xmlFileName ||'/combos/comboDic.xml';
	url = url+"&xmlFileName="+xmlFileName+"&pid="+purchasetypeone.getValue();
	cb.setUrl(url);
	
}
//组织机构代码校验  8位时校验规则
function checkORGRightOld(obj){//校验组织机构代码
	   var code=obj.value;
       if(code.indexOf("X")>-1){
    	   return true;
        }
	   var financecode=obj.value;
	   var fir_value, sec_value;
	   var w_i = new Array(8);
	   var c_i = new Array(8);
	   var j, s = 0;
	   var c, i;

	   var code = financecode;

	   if (code.length<1) {
	     //alert("请输入组织机构代码!");
	       return false;
	   }

	   if (code == "00000000-0") {
		   //mini.alert("注册号/统一社会信用代码错误!");
	       return false;
	   }

	   re = /[A-Z0-9]{8}-[A-Z0-9]/;    
	   r = code.match(re);   
	   if (r == null) {
		  // mini.alert("注册号/统一社会信用代码错误!");
	     return false;
	   }        

		   w_i[0] = 3;
		   w_i[1] = 7;
		   w_i[2] = 9;
		   w_i[3] = 10;
		   w_i[4] = 5;
		   w_i[5] = 8;
		   w_i[6] = 4;
		   w_i[7] = 2;

		   if (financecode.charAt(8) != '-') {
			  // mini.alert("注册号/统一社会信用代码错误!");
			   return false;
		   }

		   for (i = 0; i < 10; i++) {
			   c = financecode.charAt(i);
			   if ((c <= 'z') && (c >= 'a')) 
			  {
				  // mini.alert("注册号/统一社会信用代码错误!");
				   return false;
			   }
		   }


		   fir_value = financecode.charCodeAt(0);
		   sec_value = financecode.charCodeAt(1);

		   if (fir_value >= 'A'.charCodeAt(0) && fir_value <= 'Z'.charCodeAt(0)) {
			   c_i[0] = fir_value + 32 - 87;
		   } else {
				if (fir_value >= '0'.charCodeAt(0) && fir_value <= '9'.charCodeAt(0)) {
				c_i[0] = fir_value - '0'.charCodeAt(0);
				} else {
				//	mini.alert("注册号/统一社会信用代码错误!");
				return false;
				}
		   }

		   s = s + w_i[0] * c_i[0];

		   if (sec_value >= 'A'.charCodeAt(0) && sec_value <= 'Z'.charCodeAt(0)) {
			   c_i[1] = sec_value + 32 - 87;
		   } else if (sec_value >= '0'.charCodeAt(0) && sec_value <= '9'.charCodeAt(0)) {
			   c_i[1] = sec_value - '0'.charCodeAt(0);
		   } else {
			   //mini.alert("注册号/统一社会信用代码错误!");
			   return false;
		   }

		   s = s + w_i[1] * c_i[1];
		   for (j = 2; j < 8; j++) {
			   if (financecode.charAt(j) < '0' || financecode.charAt(j) > '9') {
			  // alert("注册号/统一社会信用代码错误!");
				   return false;
			   }
			   c_i[j] = financecode.charCodeAt(j) - '0'.charCodeAt(0);
			   s = s + w_i[j] * c_i[j];
		   }

		   c = 11 - (s % 11);

		   if (!((financecode.charAt(9) == 'X' && c == 10) ||
				 (c == 11 && financecode.charAt(9) == '0') || (c == financecode.charCodeAt(9) - '0'.charCodeAt(0)))) {

				  //mini.alert("注册号/统一社会信用代码错误!");
					return false;
		   }

	   return true;
}
function checkORGRight(e) {
	//var flagt=false;
	var obj = mini.get("orgcode");
	var code = obj.value;
	
/* 	if(code!=""||code.length>0){
		$.ajax({
	        url: getRootPath()+"/acl/checkBelongingPeople.acl",
	        type: "post",
	        cache: false, 
	        data :{ "code": code},
	        async : true,//异步
	        success: function (data) {
	             if(data==""||data.length<0){
	            	 flagt =true;
	             }else{
	            	 mini.confirm("该客户在系统中已存在,是否获取该客户详细质料？","提交",function(action){
	            		 if(action=='ok'){
	            			 onCancel();//mini自带关闭按钮
	            		var url = getRootPath()
								+ "/acl/windowOpenCustApplication.acl?custid="+data;
						var sheight = window.screen.availHeight - 30;
						var swidth = window.screen.availWidth - 10;
						var winoption = "left=0px,top=0px,height="
								+ sheight
								+ "px,width="
								+ swidth
								+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
						
						window.open(url, '_blank', winoption);
						mini.get("datagrid1").reload();
	            		 }
	            	 });
	            	 
	            	   flagt = true;  
	            	 
	             }
	             
	        }
	    }); 	
	} */
	//如果为香港或台湾，不校验组织机构代码
	var province = mini.get("province");
	if (province.getValue() == "810000" || province.getValue() == "710000") {
		e.sender.setIsValid(true);
	}
	var custtype = mini.get("custtype").getValue();
	if (!custtype.match("cust_type.cust") && !custtype.match("cust_type.assuror") && !custtype.match("cust_type.othertype") && !custtype.match("cust_type.guarantor")) {
		e.sender.setIsValid(true);
	}
	if (code.length == 10) {
		if(checkORGRightOld(obj)){
			e.errorText = "错误!";
            e.isValid = false;
		}else{
			e.sender.setIsValid(true);
		}
		
		
	} else {
		var  flag = isValidSocialCreditIdentifier(code);
		if (!flag) {
			//mini.alert("<spring:message code='EBEC9DBC-72C3-45C4-AB7A-B1205D59BEF8' text='注册号/统一社会信用代码错误!'/>");
			if(code==''){
				e.errorText = "不能为空!";
	            e.isValid = false;
			}else{
				e.errorText = "不符合官方标准!";
	            e.isValid = false;
			}
			 var ifcheck = mini.get('validate1').getValue();
				if(ifcheck != '是'){
					e.isValid = true;
				};
		}else{
			e.sender.setIsValid(true);
		}
	}
	
	if(checkOrgCode()=="repeat"){
		e.errorText = "重复!";
        e.isValid = false;
	}else{
		e.sender.setIsValid(true);
	}
/* 	   if(checkBelongingPeople(code)==false){
		   
		   return false;
	   }
	   return flagt; */
}

//验证9-17位 的组织机构代码
function isValidSocialCreditIdentifier(code) {
	if (code.length != 18)
		return false;
	var ws = [1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28];
	var str = "0123456789ABCDEFGHJKLMNPQRTUWXY";
	//验证9-17位 的组织机构代码
	var tCode = code.substr(8, 8) + "-" + code.substr(16, 1);
	if (isValidEntpCode(tCode) == false)
		return false;
	var sum = 0;
	for (var i = 0; i < 17; i++) {
		sum += str.indexOf((code.substr(i, 1)) + "") * ws[i];
	}
	var c18 = 31 - (sum%31);
	if(c18 > 30){
		c18 = 0;
	} 
	return (str.substr(c18, 1) + "") == (code.substr(17, 1));
}
function isValidEntpCode(code) {
	var ws = [3, 7, 9, 10, 5, 8, 4, 2];
	var str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var rx = /^([0-9A-Z]){8}-[0-9|X]$/g;
	if (!rx.test(code)) {
		return false;
	}
	var sum = 0;
	for (var i = 0; i < 8; i++) {
		sum += str.indexOf((code.substr(i, 1)) + "") * ws[i];
	}
	var c9 = 11 - (sum % 11);
	var sc9 = c9 + "";
	if (11 == c9) {
		sc9 = "0";
	} else if (10 == c9) {
		sc9 = "X";
	}
	return sc9 == (code.substr(9, 1));
}
//是否有贷款卡改变事件
function oncreditcardyesorno(e){
	if("是" == e.value){
		$mini("creditcard").setRequired(true);
	}else{
		$mini("creditcard").setRequired(false);
	}
}
//内部行业为医疗
function oncustkindchange(e){
	var sender = mini.getbyName("custkind");
	if("medical" == sender.value){
		mini.getbyName("hospitaltype").setRequired(true);
		mini.getbyName("adminlevel").setRequired(true);
	}else{
		mini.getbyName("hospitaltype").setRequired(false);
		mini.getbyName("adminlevel").setRequired(false);
	}
}
function custtypeonvaluechanged(e){
	var str = commoncusttypechange($mini("custtype").text);
}
//客户类别改变事件
function commoncusttypechange(textstr){
	var draft = $mini("draft").getValue();
	if("是" == draft) return;
	miniui_ext.initrequired("form1","all",false);
	var str = "";
	var texts = textstr.split(",");
	for(var text in texts){
		if("承租人" == texts[text]){
			//组织机构代码、客户类别、总资产(万元)、客户来源、纳税人相关信息、内部行业、行业门类（大、中、小）、国家、省份、城市、区县、法人代表、法人身份证号、手机
			str += "custname,orgcode,custtype,custsource,custkind,custshorthand"+
			  ",taxacccusttrade,taxregtype"+
			  ",country,province,city,county,personrep,";
		}else if("担保人"== texts[text]){//moblie,phone,postcode,
			str += "custname,country,province,city,county,mailaddress";
		}
	}
	miniui_ext.initrequired("form1",str,true);
	miniui_ext.initrequired("form1","custname,custtype",true);
	taxregtypeonvaluechanged();
	return str;
}
//纳税人改变事件
function taxregtypeonvaluechanged(){
	/* return; */
	var sender = mini.get("id_taxregtype");
	var str ="taxregcode,taxbank,taxacc,invoiceadd,invoicephone";
	if("一般纳税人" == sender.text){
		miniui_ext.initrequired("form1",str,true);
	}else{
		miniui_ext.initrequired("form1",str,false);
	}
}
//是否草稿改变事件
function draftchanged(e){
	var sender = mini.get("draft");
	/* if("是" == sender.value){
		miniui_ext.initrequired("form1","all",false);
		miniui_ext.initrequired("form1","custname",true);
		miniui_ext.initrequired("form1","province,city",true);
	}else{ */
		miniui_ext.initrequired("form1","custname,custtype",true);
		taxregtypeonvaluechanged();
		custtypeonvaluechanged();
	/* } */
}
jQuery(function(){
	//页面加载数据
	var data = miniui_ext.initPageData("/eleasing/jsp/cust_info/cust_company/get_custinfocompany_byid.xml","form1","<%=id %>");
	//如果是修改操作、则把组织机构代码属性设为只读
	draftchanged();
	 oncountrychanged();
	/*oncustkindchange();*/
	var dept="${sessionScope['loginUserDeptObj'].id}";
	var deptname="${sessionScope['loginUserDeptObj'].name}";
	
	if(opertype=="update"){
		$mini("orgcode").setEnabled(true);
		$mini("country").setValue(data.country);
		$mini("province").setValue(data.province);
		onprovincechanged();
		$mini("city").setValue(data.city);
		 dept=data.department||"${sessionScope['loginUserDeptObj'].id}";
		 deptname=data.deptmentname||"${sessionScope['loginUserDeptObj'].name}";
	}
	$mini("id_department").setValue(dept);
	$mini("id_department").setText(deptname);
	tenwa.createQueryInput({
		id:'id_department',
		label:'项目出单部门',
		textField:"name",
      	valueField:"id",  
		windowWidth: 600,
		params: {
			xmlFileName: '/eleasing/workflow/proj/proj_common/department_combox.xml'
		}
	});
});

function save(e){
	var  o = form.getData(true,true); 
	var custname = mini.get("custname").getValue();
	var draft = mini.get("draft").getValue();
	var invalid = mini.get("invalid").getValue();
	if("update" == opertype && ("是"==draft || "是" == invalid) ){
		if(checkDraftByProjInfo()=="repeat") return;
	}
	miniui_ext.replaceFromDataSpecialChar(o);
	if("是"==draft){
		if(""!=custname){
			if(miniui_ext.submitFormValidation(['form1']) == false) return;
			if(checkOrgCode()=="repeat") return;
			miniui_ext.saveDataAjax("<%=saveurl%>",o);
		}else{
			mini.alert("请填写客户名称！");
		}
	}else{
		if (miniui_ext.submitFormValidation(['form1']) == false) return;
		if(checkOrgCode()=="repeat") return;
		miniui_ext.saveDataAjax("<%=saveurl%>",o);
	}
}	
//组织机构代码
function checkOrgCode(){
	var orgcodevalue = $mini("orgcode").getValue();
	if("" == orgcodevalue)return "";
	var companyidvalue = $mini("companyid").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkOrgCode.acl",
        type: "post",
        cache: false, 
        data :{ "orgcode": orgcodevalue,"id":companyidvalue},
        async : false,
        success: function (text) {
        	if("repeat" == text){
        		//mini.alert("注册号/统一社会信用代码重复！");
        	}
        	str = text;
        }
    });
	return str;
}
function onIDCardsCompanyValidation(e) {
	if("未知" == e.value) return;
	if("" != mini.getbyName("documenttype").getValue() && "document_type1" != mini.getbyName("documenttype").getValue()){
		return;
	}
	var isValid = cidInfo(e.value);
	if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
}
function documenttypechange(){
	mini.getbyName("personidcard").validate();
}

</script>
</body>
</html>