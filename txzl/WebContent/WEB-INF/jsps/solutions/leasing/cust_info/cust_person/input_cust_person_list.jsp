<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%
String opertype=request.getParameter("opertype");
String id=request.getParameter("id");
String saveurl=request.getParameter("saveurl");
%>
<title>自然人客户信息管理</title>
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
<div class="mini-fit" >
	<div id="form1" title="自然人客户信息" style="padding: 5px 40px 5px 40px;">
		<table id="id_table_cust_info_edit" cellpadding="0" cellspacing="0" align="center" >
		    <tr>
		        <td >客户名称：</td>
		        <td colspan="1">
 		        	<input id ="custid" name="custid" class="mini-textbox" style="display: none;"/>
 		        	<input id="personid" name="personid" class="mini-textbox" style="display: none;"/>
		            <input id="custname" name="custname" label="客户名称" class="mini-textbox miniext-form-fit" vtype="maxLength:100" required="true"/>
		        </td>
		        	<td width="16%" class="miniext-form-td">证件类型：</td>
		        <td width="34%"><mini:dict  name="documenttype" otherproperties=" label='法人代表证件类型' onvaluechanged='documenttypechange()'" parentid="document_type"/> </td>
		    </tr>
		    <tr>		    
		    	<td width="16%">证件号码：</td>
		        <td width="34%"> <input id="idcardno" name="idcardno" label="身份证号码" class="mini-textbox miniext-form-fit"  onvalidation="validationIdCard"/></td>
		        <td >性别：</td>
		        <td >
					<div id="sex" name="sex" class="mini-radiobuttonlist"
						data="[{text:'男'},{text:'女'}]" textField="text" valueField="text" 
						showNullItem="true" nullItemText="" emptyText="" label="性别">
					</div>
		        </td>
		    </tr>
		    <tr>		        
		        <td class="miniext-form-td">出生日期：</td>
		        <td><input id="brithdate" name="brithdate" label="出生日期" class="mini-datepicker miniext-form-fit" allowInput="false"/></td>
		         
		        <td >客户内部行业：</td>
		        <td><input name="custkind" id="custkind" class="mini-combobox" textField="name" required="true" label="客户行业 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'cust_kind'}}" onbeforeshowpopup="onbeforeshowpopup" 
						onvaluechanged="comboboxChanged" style="width:95%"/></td>
		
		  <%--       <td class="miniext-form-td">客户规模：</td>
		        <td><mini:dict  name="custscale" otherproperties=" label='客户规模' " parentid="cust_scale"/></td> --%>
		    
		     </tr>
		     <tr>		        
		        <td class="miniext-form-td">客户来源：</td>
		        <td><mini:dict  name="custsource" otherproperties=" label='客户来源' " parentid="cust_source"/></td>
		        <td>客户类别：</td>
		        <td>
					<div class="mini-combobox miniext-form-fit"  id="custtype"
						popupWidth="100%" textField="name" valueField="value" name="custtype"
					    data-options="{_xmlFileName:'/combos/comboDict.xml',_params:{pid:'cust_type'}}"   
					    multiSelect="true" onbeforeshowpopup="miniextonbeforeshowpopup" allowInput="false" dataField="datas"
					    showClose="true" oncloseclick="onCloseClick" 
					    emptyText="" required="true" label="客户类别"
					    text = '承租人'
					    value = 'cust_type.cust'
					    onvaluechanged="custtypeonvaluechanged"
					    >     
					    <div property="columns">
					        <div header="客户类别" field="name"></div>
					    </div>
					</div>
		        </td>
		    </tr>
		   <!--    <tr>
		        
		        <!-- <td >客户内部行业：</td>
		        <td><input name="custkind" id="custkind" class="mini-combobox" textField="name" required="true" label="客户行业 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'cust_kind'}}" onbeforeshowpopup="onbeforeshowpopup" 
				<td>子行业</td>
				<td><input name="subcustkind" id="subcustkind" class="mini-combobox" textField="name" required="true" label="子行业" valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:''}}" 
								   onbeforeshowpopup="onpurchasetypetwobeforeshowpopup"
								   onEnter="search" style="width:95%"/></td>
		    </tr> -->
		    <tr>
		        <td class="miniext-form-td">客户所属行业门类：</td>
		        <td >
					<input id="industry" name="industry" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="客户所属行业门类"
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_bypid.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"  onvaluechanged="onindustrychanged"
						   showNullItem="true" nullItemText="" emptyText=""
					/>
		        </td>
		        <td class="miniext-form-td">行业门类大类：</td>
		        <td >
					<input id="industrylevelbig" name="industrylevelbig" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="行业门类大类"
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelbig','industry','客户所属行业门类')" onvaluechanged="onindustrylevelbigchanged"
						   showNullItem="true" nullItemText="" emptyText=""
					/>
		        </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">行业门类中类：</td>
		        <td >
					<input id="industrylevelmiddle" name="industrylevelmiddle" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="行业门类中类"
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelmiddle','industrylevelbig','行业门类大类')" onvaluechanged="onindustrylevelmiddlechanged"
						   showNullItem="true" nullItemText="" emptyText=""
					/>
		        </td>
		        <td class="miniext-form-td">行业门类小类：</td>
		        <td >
					<input id="industrylevelsmall" name="industrylevelsmall" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="行业门类小类"
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelsmall','industrylevelmiddle','行业门类中类')"
						   showNullItem="true" nullItemText="" emptyText=""
					/>
		        </td>
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
					<input id="city" name="city" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  label="城市"
						   dataField="datas"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""/>
		        </td>
		        <td class="miniext-form-td">区县：</td>
		        <td ><input name="county"  label="区县" class="mini-textbox miniext-form-fit" vtype="maxLength:200"/></td>
		    </tr>		    
		    <tr>
		        <td class="miniext-form-td">邮寄地址：</td>
		        <td colspan="3"><input name="mailadd" label="邮寄地址" class="mini-textbox miniext-form-fit2" vtype="maxLength:200"/></td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">邮编：</td>
		        <td><input name="postcode" label="邮编" class="mini-textbox miniext-form-fit" vtype="maxLength:50" onvalidation = "onPostcodeValidation"/> </td>
		        <td class="miniext-form-td">手机：</td>
		        <td><input name="mobilenumber" label="手机" class="mini-textbox miniext-form-fit" vtype="maxLength:200" onvalidation = "onMoblieValidation"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">电话：</td>
		        <td ><input name="phone" label="电话" class="mini-textbox miniext-form-fit" vtype="maxLength:200"/></td>
		        <td class="miniext-form-td">Email：</td>
		        <td><input name="email" class="mini-textbox miniext-form-fit" vtype="email;maxLength:50"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">户口所在地：</td>
		        <td colspan="3"><input name="domicileplace" class="mini-textbox miniext-form-fit2" vtype="maxLength:100"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">常住地址：</td>
		        <td colspan="3"><input name="oftenaddr" class="mini-textbox miniext-form-fit2" vtype="maxLength:200"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">婚烟状态：</td>
		        <td><mini:dict  name="maritalstatus"  parentid="marital_status"/> </td>
		        <td class="miniext-form-td">学历：</td>
		        <td><mini:dict  name="school"  parentid="school"/></td>
		    </tr>
		    <tr>		        
		        <td class="miniext-form-td">传真：</td>
		        <td><input name="faxnumber" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>
		        <td class="miniext-form-td">职务：</td>
		        <td><mini:dict  name="unitposition"  parentid="cust_jobposition"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">工作单位：</td>
		        <td colspan="3"><input name="unitname" class="mini-textbox miniext-form-fit2" vtype="maxLength:200"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">客户概况：</td>
		        <td colspan="3"><textarea id="id_ceimemo" name="ceimemo" class="mini-textarea miniext-form-fit2" vtype="maxLength:2000"></textarea></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">是否潜在客户：</td>
		        <td><div id="draft" name="draft" class="mini-radiobuttonlist" data="[{text:'是'},{text:'否'}]" textField="text" valueField="text" value="否"  onvaluechanged="draftchanged"> </div> </td>
		        <td class="miniext-form-td">是否废弃：</td>
		        <td><div id="invalid" name="invalid" class="mini-radiobuttonlist" data="[{text:'是'},{text:'否'}]" 	textField="text" valueField="text" value="否"></div> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">登记人：</td>
		        <td><input name="creatorname" class="mini-textbox miniext-form-fit asLabel" readonly value="${sessionScope.loginUser.realname}"/></td>
		        <td class="miniext-form-td">登记时间：</td>
		        <td><input name="createdate" class="mini-datepicker miniext-form-fit asLabel" readonly value="new Date()"/></td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">登记部门</td>
		        <td>
		        <input id="id_department" name="department" width="280px" label="登记部门" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"/>
		        </td>
		        <%-- <td class="miniext-form-td">归属人：</td>
		        <td><input name="belongingpeoplename" class="mini-textbox miniext-form-fit asLabel" readonly value="${empty requestScope['belongingpeoplename'] ? sessionScope.login_realname:requestScope['belongingpeoplename']}" }" vtype="maxLength:50"/>
		        	<input name="belongingpeople" class="mini-textbox miniext-form-fit asLabel" value="${empty requestScope['belongingpeoplename'] ? sessionScope.login_userid:requestScope['belongingpeoplename']}"] }" visible="false" />
		        </td> --%>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">修改人：</td>
		        <td><input name="modificatorname" class="mini-textbox miniext-form-fit asLabel" readonly/></td>
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
mini.parse();
var form = new mini.Form("form1");
var opertype="<%=opertype %>";
function $mini(id){
	return mini.get(id);
}
//客户行业联动子行业
function comboboxChanged(e){
	mini.get("subcustkind").setValue("");
	mini.get("subcustkind").setText("");
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
			//客户名称、身份证号、性别、出生年月、客户内部行业、客户所属行业门类、大、中小、(需增加) 省份、城市、国家、手机、区县、电话、邮编、邮寄地址
			str += "custname,idcardno,sex,brithdate,custkind"+
			  ",country,province,city,county"+
			  ",mobilenumber,custscale,custsource,";
		}else if("担保人"== texts[text]){
			str += "custname,idcardno,sex";
		}
	}
	miniui_ext.initrequired("form1",str,true);
	miniui_ext.initrequired("form1","custname,custtype",true);
	return str;
}
//是否草稿改变事件
function draftchanged(e){
	var sender = mini.get("draft");
	/* if("是" == sender.value){
		miniui_ext.initrequired("form1","all",false);
		miniui_ext.initrequired("form1","custname",true);
	}else{ */
		miniui_ext.initrequired("form1","custname,custtype",true);
		custtypeonvaluechanged();
	/* } */
}

jQuery(function(){
	//页面加载数据
	var data = miniui_ext.initPageData("/eleasing/jsp/cust_info/cust_person/cust_person_list.xml","form1","<%=id %>");
	draftchanged();
	oncountrychanged();
	var dept="${sessionScope['loginUserDeptObj'].id}";
	var deptname="${sessionScope['loginUserDeptObj'].name}";
	if("update" == opertype){
		$mini("country").setValue(data.country);
		$mini("province").setValue(data.province);
		onprovincechanged();
		$mini("city").setValue(data.city);
		 dept=data.department||"${sessionScope['loginUserDeptObj'].id}";
		   deptname=data.deptmentname||"${sessionScope['loginUserDeptObj'].name}";
		//$mini("id_ceimemo").setValue($mini("id_ceimemo").getValue().replace(/<br>/g, "\\r\\n"));
	    //document.getElementById("id_ceimemo$text").innerText="xxxx";
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
//页面保存
function save(e){
	mini.parse();
	var  o = form.getData(true,true);
	var custname = mini.get("custname").getValue();
	var draft = mini.get("draft").getValue();
	var invalid = mini.get("invalid").getValue();;
	if("update" == opertype && ("是"==draft || "是" == invalid) ){
		if(checkDraftByProjInfo()=="repeat") return;
	}
	miniui_ext.replaceFromDataSpecialChar(o);
	if("是"==draft){
		if(""!=custname){
			if (miniui_ext.submitFormValidation(['form1']) == false) return;
			if(checkIdCard()=="repeat") return;
			miniui_ext.saveDataAjax("<%=saveurl%>",o);
		}else{
			mini.alert("请填写客户名称！");
		}
	}else{
		if (miniui_ext.submitFormValidation(['form1']) == false) return;
		if(checkIdCard()=="repeat") return;
		
		miniui_ext.saveDataAjax("<%=saveurl%>",o);
	}
}
function checkIdCard(){
	var idcardnovalue = $mini("idcardno").getValue();
	if("" == idcardnovalue)return "";
	var personidvalue = $mini("personid").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkIdCard.acl",
        type: "post",
        cache: false,
        data :{ "idcardno": idcardnovalue,"id":personidvalue},
        async : false,
        success: function (text) {
        	if("repeat" == text){
        		mini.alert("身份证号重复！");
        	}
        	str =  text;
        }
    });
	return str;
}


function validationIdCard(e){
	var flag=false;
	var idcardno=mini.get("idcardno").getValue();
/*  	if(idcardno!=""||idcardno.length>0){
		$.ajax({
	        url: getRootPath()+"/acl/checkPersonBelongingPeople.acl",
	        type: "post",
	        cache: false, 
	        data :{ "idcardno": idcardno},
	        async : true,//异步
	        success: function (data) {
	             if(data==""||data.length<0){
	            	 flag=true;
	             }else{
	            	 mini.confirm("该客户在系统中已存在,是否获取该客户详细质料？","提交",function(action){
	            		 if(action=='ok'){
	            			 onCancel();//mini自带关闭按钮
	            		var url = getRootPath()
								+ "/acl/windowOpenPersonApplication.acl?custid="+data;
						var sheight = window.screen.availHeight - 30;
						var swidth = window.screen.availWidth - 10;
						var winoption = "left=0px,top=0px,height="
								+ sheight
								+ "px,width="
								+ swidth
								+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
						window.open(url, '_blank', winoption);
	            		 }
	            	 });
	          	   flag = true;  
	             }
	        }
	    }); 	
	} */
	
	if("" != mini.getbyName("documenttype").getValue() && "document_type1" != mini.getbyName("documenttype").getValue()){
		return;
	}
	 var sId = e.value;
	 var isValid = cidInfo(sId);
	 if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
	 $mini("brithdate").setValue(idcardStr.brithdate);
	 $mini("sex").setValue(idcardStr.sex);
	 $mini("province").setValue(idcardStr.province);
	 onprovincechanged();
	 $mini("city").setValue(idcardStr.city);
}
function documenttypechange(){
	mini.getbyName("idcardno").validate();
}
</script>
</body>
</html>