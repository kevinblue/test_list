<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>集团客户信息管理</title>
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
	<div id="form1" style="padding: 5px 40px 5px 40px;" title="集团客户信息">
		<table id="id_table_cust_info_edit">
		    <tr>
		        <td style="width:120px;">客户名称：</td>
		        <td>
  		        	<input id="custid" name="custid" class="mini-textbox" style="display: none;"/>
  		        	<input id="cliqueid" name="cliqueid" class="mini-textbox" style="display: none;"/>
		            <input id="custname" name="custname" label="客户名称" class="mini-textbox miniext-form-fit" vtype="maxLength:100" required="true"/>
		        </td>
		        <td style="width:120px;">组织机构代码：</td>
		        <td>
		            <input id="orgcode" name="orgcode" label="组织机构代码" class="mini-textbox miniext-form-fit" onvalidation="checkORGRight"/>
		        </td>
		    </tr> 
		    <tr>
		        <td class="miniext-form-td">客户规模：</td>
		        <td><mini:dict  name="custscale" otherproperties=" label='客户规模' " parentid="cust_scale"/> </td>
		        <td class="miniext-form-td">客户来源：</td>
		        <td><mini:dict  name="custsource" otherproperties=" label='客户来源' " parentid="cust_source"/> </td>
		    </tr>
		    <tr>
		    	<td>客户类别：</td>
		        <td>
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
		        <td >客户内部行业：</td>
		        <td><mini:dict  name="custkind" otherproperties=" label='客户内部行业' " parentid="cust_kind"/> </td>
		     </tr>
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
		        <td class="miniext-form-td">行业门类大类：</td>
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
		        <td class="miniext-form-td">行业门类中类：</td>
		        <td >
					<input id="industrylevelmiddle" name="industrylevelmiddle" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelmiddle','industrylevelbig','行业门类大类')" onvaluechanged="onindustrylevelmiddlechanged"
						   showNullItem="true" nullItemText="" emptyText="" label='行业门类中类'/>
		        </td>
		        <td class="miniext-form-td">行业门类小类：</td>
		        <td >
					<input id="industrylevelsmall" name="industrylevelsmall" class="mini-combobox miniext-form-fit" textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_common/get_industry_all.xml'}" 
						   onbeforeshowpopup="changecommon('industrylevelsmall','industrylevelmiddle','行业门类中类')"
						   showNullItem="true" nullItemText="" emptyText="" label='行业门类小类'/>
		        </td>
		    </tr>
		    <tr>
		    	 <td class="miniext-form-td">纳税人类别：</td>
		        <td><mini:dict  name="taxregtype"  parentid="tax_level_name" otherproperties="label='纳税人类别'"/></td>
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
					<input id="country" name="country" class="mini-combobox miniext-form-fit" label='国家' textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   url="${urlPrefix}/eleasing/jsp/sysmgr/sysdatamgr/pro_city_list.xml"
						   text = "中华人民共和国"
						   value = "CHN" 
						   onvaluechanged="oncountrychanged"
						   showNullItem="true" nullItemText=""
						   emptyText=""/>
		        </td>
		        <td class="miniext-form-td">省份：</td>
		        <td>
					<input id="province" name="province" class="mini-combobox miniext-form-fit" label='省份' textField="name" 
	                  	   valueField="id"  
						   dataField="datas"
						   allowInput="false" 
						   onvaluechanged="onprovincechanged"
						   showNullItem="true" nullItemText=""
						   emptyText=""/>
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
		    	<td class="miniext-form-td">法人代表：</td>
		        <td ><input name="personrep" class="mini-textbox miniext-form-fit" label='法人代表' vtype="maxLength:100"/></td>
		    	<td class="miniext-form-td">法人身份证号：</td>
		        <td><input name="personidcard" class="mini-textbox miniext-form-fit" label='法人身份证号' onvalidation="onIDCardsCompanyValidation"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">手机：</td>
		        <td><input name="moblie" class="mini-textbox miniext-form-fit" label='手机' onvalidation = "onMoblieValidation" /> </td>
		        <td class="miniext-form-td">电话：</td>
		        <td><input name="phone" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/>
		        </td>
		    <tr>
		    	<td class="miniext-form-td">邮编：</td>
		        <td><input name="postcode" class="mini-textbox miniext-form-fit" onvalidation = "onPostcodeValidation" /></td>
		        <td class="miniext-form-td">传真：</td>
		        <td><input name="fax" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">归属人：</td>
		        <td><input name="belongingpeople" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>		             
		        <td class="miniext-form-td">客户实际负责人：</td>
		        <td ><input name="custactualpeople" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">联系人：</td>
		        <td><input name="contact" class="mini-textbox miniext-form-fit" vtype="maxLength:50" label='联系人' required="true"/> </td>		             
		        <td class="miniext-form-td">联系人手机：</td>
		        <td ><input name="contactmoblie" class="mini-textbox miniext-form-fit" vtype="maxLength:50" label='联系人手机' required="true"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">联系人邮箱：</td>
		        <td><input name="contactemail" class="mini-textbox miniext-form-fit" vtype="maxLength:50" label='联系人邮箱' required="true"/> </td>		             
		        <td class="miniext-form-td">联系人电话：</td>
		        <td ><input name="custactphone" class="mini-textbox miniext-form-fit" vtype="maxLength:50" /> </td>
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
		        <td><mini:dict  name="ownership"  parentid="ownership" otherproperties="label='企业性质'"/>  </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">营业执照号：</td>
		        <td><input name="buslicense" class="mini-textbox miniext-form-fit" vtype="maxLength:50"/> </td>
		        <td class="miniext-form-td">注册资本：</td>
		        <td><input name="regcapital" class="mini-textbox miniext-form-fit"/> </td>
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
		    <tr>
		        <td class="miniext-form-td">经营方式：</td>
		        <td colspan="3"><input name="operate" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">经营范围<br/>(主营)：</td>
		        <td colspan="3"><input name="operatemaster" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">经营范围<br/>(兼营)：</td>
		        <td colspan="3"><input name="operateminor" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">客户概况：</td>
		        <td colspan="3"><input name="custprobably" class="mini-textarea miniext-form-fit2" vtype="maxLength:1000"/> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">组织机构代码证上传：</td>
		        <td align="center">
		        <input id="custfile1id" name="custfile1id" class="mini-textbox" style="display: none;"/>
		        <input id="custfile1name" name="custfile1name" class="mini-textbox" style="display: none;"/>
		        <a href="javascript:void(0)" onclick="showAddfile('custfile1')">上传</a>&nbsp; &nbsp;&nbsp;&nbsp;
		        <a href="javascript:void(0)" onclick="showFile('custfile1')">预览</a>
		        </td>
		        <td id="custfile1" colspan="2" title="组织机构代码证"></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">营业执照上传：</td>
		        <td align="center">
		        <input id="custfile2id" name="custfile2id" class="mini-textbox" style="display: none;"/>
		        <input id="custfile2name" name="custfile2name" class="mini-textbox" style="display: none;"/>
		        <a href="javascript:void(0)" onclick="showAddfile('custfile2')">上传</a> &nbsp; &nbsp;&nbsp;&nbsp;
		        <a href="javascript:void(0)" onclick="showFile('custfile2')">预览</a>
		        </td>
		        <td id="custfile2" colspan="2" title="营业执照"></td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">税务登记证上传：</td>
		        <td align="center">
		        <input id="custfile3id" name="custfile3id" class="mini-textbox" style="display: none;"/>
		        <input id="custfile3name" name="custfile3name" class="mini-textbox" style="display: none;"/>
		        <a href="javascript:void(0)" onclick="showAddfile('custfile3')">上传</a>&nbsp; &nbsp;&nbsp;&nbsp;
		        <a href="javascript:void(0)" onclick="showFile('custfile3')">预览</a>
		        </td>
		        <td id="custfile3" colspan="2" title="税务登记证"> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">法定代表人身份证：</td>
		        <td align="center">
		        <input id="custfile4id" name="custfile4id" class="mini-textbox" style="display: none;"/>
		        <input id="custfile4name" name="custfile4name" class="mini-textbox" style="display: none;"/>
		        <a href="javascript:void(0)" onclick="showAddfile('custfile4')">上传</a>&nbsp; &nbsp;&nbsp;&nbsp; 
		        <a href="javascript:void(0)" onclick="showFile('custfile4')">预览</a>
		        </td>
		        <td id="custfile4" colspan="2" title="法定代表人身份证"> </td>
		    </tr>
		    <tr>
		        <td class="miniext-form-td">是否草稿：</td>
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
		    <tr>
		    	<td class="miniext-form-td">登记人：</td>
		        <td><input name="creatorname" class="mini-textbox miniext-form-fit asLabel" readonly value="<mini:user/>"/> </td>
		        <td class="miniext-form-td">登记时间：</td>
		        <td><input name="createdate" class="mini-datepicker miniext-form-fit asLabel" readonly value="new Date()"/> </td>
		    </tr>
		    <tr>
		    	<td class="miniext-form-td">登记部门</td>
		        <td>
		        <input id="id_department" name="department" label="登记部门" class="mini-buttonedit mini-queryinput" allowInput="false" readonly
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
<div id="addfile" class="mini-window" title="上传"
		style="width: 400px; height: 100px;" showModal="true" showMaxButton="true"
		allowResize="true" allowDrag="true">
		<iframe style="display:none;" name="attachment_file_upload_real_submit_frame"></iframe>
	<form action="" id="id_uploadCustFileForm" enctype="multipart/form-data"  target="attachment_file_upload_real_submit_frame" method="post">
		<input type="hidden" name="cust_id" id="cust_id" class='mini-hidden'>
		<input type="hidden" name="module" id="id_attachmentFileModule" class='mini-hidden' value="custfile">
		<input type="hidden" name="attachmentFileDictId" id="attachmentFileDictId" class='mini-hidden'>
		<input type="hidden" name="attachmentFileUploadInfoId" id="id_attachmentFileUploadInfoId" class='mini-hidden'>
		<input type="hidden" name="attachmentFileUnionKey" id="id_attachmentFileUnionKey" value="" class='mini-hidden'>
		<table align="center" style="width:90%">
		<tr><td class="input_label_desc"></td><td class="input_value" id="id_upload_location"><input type="file" name="name_upload_file_name" id="id_uploadAttachmentFile" /><span class="content-required">*</span></td></tr>
		<tr class="content-separator">
			<td colspan="2" style="text-align:center;">
			<a class="mini-button" onclick="__uploadActionSubmit"><span>确定</span></a>
			<a class="mini-button" onclick="javascript:{mini.get('addfile').hide();}"><span>取消</span></a>
			</td>
		</tr>
		</table>
	</form>
</div>
<div id="showfile" class="mini-window" title="预览"
		style="width: 60%; height: 60%;" showCloseButton="true"  showModal="true" showMaxButton="true"
		allowResize="true" allowDrag="true" >
	<div id="imag">
	</div>
</div>
<script>
<!--初始化-->
mini.parse();
var form = new mini.Form("form1");
var opertype="<mini:param  name='opertype'/>";
function $mini(id){
	return mini.get(id);
}
//组织机构代码校验
function checkORGRight(obj){//校验组织机构代码
	if(obj.sender.required==false) return;
	   var code=obj.value;
	   var cflag=true;
	   if(code.length>0){
       code=code.substring(0,1);
       if(code=="X"){return true;
        }
	   }
	    if (obj.value.length == 10)
	    {
	    	
	    	cflag= isValidEntpCode(obj.value);
	    }else{
	    	cflag=isValidSocialCreditIdentifier(obj.value);
	    }
	   if(!cflag){mini.alert("组织机构代码无效");}
	   return cflag;
}
//验证组织机构代码

function isValidEntpCode(code)
{
    var ws = [3,7,9,10,5,8,4,2];
    var  str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var  rx = /^([0-9A-Z]){8}-[0-9|X]$/g;
    if (!rx.test(code)){return false;}
     var  sum = 0;
    for (var i = 0; i < 8; i++)
    {sum += str.indexOf((code.substr(i,1))+"") * ws[i];}
    var  c9 = 11 - (sum % 11);
    var  sc9 =c9+"";
    if (11 == c9)
    {sc9 = "0";}
    else if (10 == c9)
    {   sc9 = "X";}
    return sc9==(code.substr(9,1));
}

// 验证社会信用代码 18位

function isValidSocialCreditIdentifier( code)
{
    if (code.length != 18) return false;
    var ws = [1,3,9,27,19,26,16,17,20,29,25,13,8,24,10,30,28];
    var str = "0123456789ABCDEFGHJKLMNPQRTUWXY";
    //验证9-17位 的组织机构代码
    var tCode = code.substr(8, 8) + "-" + code.substr(16, 1);
    if (isValidEntpCode(tCode) == false) return false;
    var sum = 0;
    for (var i = 0; i < 17; i++)
    {
        sum += str.indexOf((code.substr(i, 1))+"") * ws[i];
    }
    var c18 = 31 - (sum % 31);
    return (str.substr(c18, 1)+"") == (code.substr(17, 1));
}

//是否有贷款卡改变事件
function oncreditcardyesorno(e){
	if("是" == e.value){
		$mini("creditcard").setRequired(true);
	}else{
		$mini("creditcard").setRequired(false);
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
	var str = "custname,custsource,custtype,custkind,taxregtype,country,province,city,county,contact,contactmoblie,contactemail,ownership,custscale";
	var texts = textstr.split(",");
	for(var text in texts){
		if("承租人" == texts[text]){
			//组织机构代码、客户类别、客户规模、客户来源、纳税人相关信息、内部行业、行业门类（大、中、小）、国家、省份、城市、区县、法人代表、法人身份证号、手机
			str += ",orgcode,industry,industrylevelbig,industrylevelmiddle,industrylevelsmall";
		}else if("担保人"== texts[text]){//moblie,phone,postcode,
			str += ",orgcode";
		}
	}
	miniui_ext.initrequired("form1",str,true);
	return str;
}
//纳税人改变事件
function taxregtypeonvaluechanged(){
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
	if("是" == sender.value){
		miniui_ext.initrequired("form1","all",false);
		miniui_ext.initrequired("form1","custname",true);
	}else{
		miniui_ext.initrequired("form1","custname,custtype",true);
		custtypeonvaluechanged();
	}
}
jQuery(function(){
	//页面加载数据
	var data = miniui_ext.initPageData("/eleasing/jsp/cust_info/cust_clique/get_custinfoclique_byid.xml","form1","<mini:param  name='id'/>");
	//如果是修改操作、则把组织机构代码属性设为只读
	draftchanged();
	oncountrychanged();

	var dept="${sessionScope['loginUserDeptObj'].id}";
	var deptname="${sessionScope['loginUserDeptObj'].name}";
	if(opertype=="update"){
		$mini("orgcode").setEnabled(false);
		$mini("country").setValue(data.country);
		$mini("province").setValue(data.province);
		onprovincechanged();
		$mini("city").setValue(data.city);
		 dept=data.deptment||dept;
		 deptname=data.deptmentname||deptname;
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
	for(var i=1;i<=4;i++)
	{
		var fileid=mini.get("custfile"+i+"id").getValue();
		var filename=mini.get("custfile"+i+"name").getValue();
		creatdownloadUpload(filename,fileid,"custfile"+i);
	}
});

function save(e){
	//验证客户附件上传
	var textstr=$mini("custtype").text;
	var texts = textstr.split(",");
	for(var text in texts){
		if("承租人" == texts[text]){
			var a=false;
			for(var i=1;i<4;i++)
			{
				if($("#custfile"+i).html()!="")
				{
					a=true;
				}
			}
			if(a==false)
			{
				mini.alert("组织机构代码证、营业执照、税务登记证必须上传一个！");
				return;
			}
		}
	}
	
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
			if(miniui_ext.submitFormValidation(['form1']) == false) return;
			if(checkOrgCode()=="repeat") return;
			miniui_ext.saveDataAjax("<mini:param  name='saveurl'/>",o);
		}else{
			mini.alert("请填写客户名称！");
		}
	}else{
		if (miniui_ext.submitFormValidation(['form1']) == false) return;
		if(checkOrgCode()=="repeat") return;
		miniui_ext.saveDataAjax("<mini:param  name='saveurl'/>",o);
	}
}	
//组织机构代码
function checkOrgCode(){
	var orgcodevalue = $mini("orgcode").getValue();
	if("" == orgcodevalue)return "";
	var cliqueidvalue = $mini("cliqueid").getValue();
	var str = "";
	$.ajax({
        url: getRootPath()+"/acl/checkOrgCode.acl",
        type: "post",
        cache: false, 
        data :{ "orgcode": orgcodevalue,"id":cliqueidvalue},
        async : false,
        success: function (text) {
        	var obj=mini.decode(text);
        	if("repeat" ==  obj.message){
        		mini.alert("组织机构代码重复！");
        	}
        	str =  obj.message;
        }
    });
	return str;
}
function onIDCardsCompanyValidation(e) {
	if("未知" == e.value) return;
	var isValid = cidInfo(e.value);
	if(!(true == isValid)){
		e.errorText = isValid;
		e.isValid = false;
	}
}
//显示添加文件窗口
function showAddfile(custfilename)
{
	$("#id_uploadAttachmentFile").val("");
	mini.get("cust_id").setValue(mini.get("custid").getValue());
	mini.get("attachmentFileDictId").setValue(custfilename)
	mini.get("addfile").show();
}
//保存上传
function __uploadActionSubmit()
{
	var form=$("#id_uploadCustFileForm")[0];
	with (form) {
		action = getRootPath() + '/attachmentfile/uploadCustFile.action';
		submit();
	}
	mini.get("addfile").hide();
}
//上传结果
function uploadAttachmentFileCallBack(msg){
	
	var s=msg.split(",");
	mini.alert(s[0]);
	creatdownloadUpload(s[1],s[2],s[3]);
	mini.unmask(document.body);
}
function showFile(filedict){
	var url = getRootPath() + '/table/getTableData.action';
	var params = { xmlFileName:'/common/queryCustImages.xml',"filedict":filedict};
	var custid=mini.get("custid").getValue();
	if(custid==null||custid==""){
		params.nullcustid=" is null";
		params.userid="<mini:param name='loginUser.id'/>";
	}
	else{
		params.custid=custid;
	}
	mini.mask({
	    el: document.body,
	    cls: 'mini-mask-loading'
	});
	tenwa.ajaxRequest({
        url:url,
        params:params,
        method:'post',
        success:function(res){
            var result = JsonUtil.decode(res.responseText);
		    var datas = result['datas'];
		    if(datas && datas.length > 0){
		    	jQuery("#imag").html('');
		    	jQuery.each(datas, function(index, appImage){
		    		var img = createAttachmentImageDiv(appImage,filedict);
		    		if(img){
			    		jQuery("#imag").append(img);
		    		}
		    	});
		    } else {
		    	jQuery("#imag").html('');
		    }
		    mini.unmask(document.body);
        },
        failure:function(res){
        	mini.unmask(document.body);
        }
    });
	mini.get("showfile").show();
}

// 根据单个图片数据获取一个document
function createAttachmentImageDiv(appImage,filedict){
	var $div = jQuery('<div/>');
	var custid=mini.get("custid").getValue();
	if(appImage){
		$div.addClass("img-box");
		$div.attr("imageid", appImage.idd);
		var $image = jQuery("<img/>");
		$image.attr("height", appImage.height);
		$image.attr("width", appImage.widths);
		$image.attr("realUrl", appImage.imagepath);
		$image.attr("alt", appImage.title);
		$image.attr("src", getRootPath()+"/acl/qeryCustfile.acl?filedict="+filedict+"&custid="+custid+"&userid="+"<mini:param name='loginUser.id'/>");
		$image.appendTo($div);
	}
	return $div;
}
//下载附件
function downloadUploadCustFile(uploadAttachmentFileDetailId) {
	var newAction = getRootPath() + "/attachmentfile/downloadAttachmentFile.action?";
	newAction += "uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId;
	var form=$("#id_uploadCustFileForm")[0];
	with (form) {
		action = newAction;
		submit();
	}
}
//删除附件
function removeUploadCustFile(tableId, uploadAttachmentFileDetailId) {
	mini.confirm("确定删除该附件么？", "删除？", function(action) {
		if (action == 'ok') {
			$("#"+tableId).html();
			mini.mask({
				el : document.body,
				cls : 'mini-mask-loading',
				html : '数据加载中，请稍后...'
			});
			
			var newAction = getRootPath() + "/attachmentfile/removeAttachmentCustFile.action";
			newAction += "?uploadAttachmentFileDetailId=" + uploadAttachmentFileDetailId+"&td="+tableId;
			var form=$("#id_uploadCustFileForm")[0];
			with (form) {
				action = newAction;
				submit();
			}
			
		}
	});
}
function creatdownloadUpload(custfilename,custfileid,td)
{
	if(custfileid=="")
	{
		$("#"+td).html("");
	}
	else
	{
		var renderHtml = "<table style='border:0px solid #FFF;'><tr style='border:0px solid #FFF;'>";
		renderHtml+="<td style='border:0px solid #FFF;'><a href='#' onclick='downloadUploadCustFile(\"" + custfileid + "\");'>"+custfilename+"</a>&nbsp;&nbsp;&nbsp;&nbsp;"
		renderHtml+="<a onclick='removeUploadCustFile(\"" +td+ "\",\"" + custfileid + "\");' style='position:relative;top:-2px' title='删除' href='javascript:void(0);'><img border=0 align='absmiddle' width=14  src='" + getRootPath() + "/images/icon_delete.gif'/></a>";
		renderHtml += "</td></tr></table>";
		$("#"+td).html(renderHtml);
	}
}

</script>
</body>
</html>