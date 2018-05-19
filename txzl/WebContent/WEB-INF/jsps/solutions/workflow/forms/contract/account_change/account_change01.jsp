<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
//是否保存   
function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	fillHiddenVal();
	return true;
}
//保存成功提交后，后台返回
function saveCallBack() {
	return true;
}
//是否提交    
function workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
{
	if (miniui_ext.submitFormValidation(["contract_signatory_form"]) == false) return false;
	fillHiddenVal();
	return true;
}



</script>
<!--多行控件  -->
<input style="display:none;"	class="mini-textarea" id="id_json_contract_word_str" name="json_contract_word_str" value='${empty json_contract_word_str ? "[]" : json_contract_word_str }'></input>
<input  style="display:none;"   class="mini-textarea" id="id_json_contract_supplier_str" name="json_contract_supplier_str" value='${empty json_contract_supplier_str ? "[]" : json_contract_supplier_str }'></input>
<input style="display:none;"  name="contract_info.id" id="contract_info.id" class="mini-textbox" value="${requestScope['contract_info.id']}" />
<div id="account_change_form" title="回笼账号变更">

	<div class="fillTableContainer">
		<div class="mini-panel" title="租金回笼账号变更" showCollapseButton="true" style="width:100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%" >
				<tr class="tr-even">
					<td class="td-content-title" width="12%">项目编号：</td>
					<td class="td-content" width="38%"><input  class="mini-textbox" label="项目编号" readOnly type="text" value="${requestScope['contract_info.proj_id']}"></td>
					<td class="td-content-title">承租单位：</td>
					<td class="td-content" >
						<input style="width: 38%" class="mini-textbox" readOnly require="true" label="承租单位" type="text"
							value="${requestScope['contract_info.custname'] }">
						&nbsp;
						<a href="javascript:void(0);"><img alt="客户信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
							onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')" style="vertical-align: middle;" /></a>&nbsp;
					</td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">合同编号：</td>
					<td class="td-content">
					   <input style="width: 38%"  class="mini-textbox" label="合同编号" value="${requestScope['contract_info.contractid'] }" allowInput="false" />
					   &nbsp;
						<a href="javascript:void(0);"><img alt="账号信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
							onclick="showContractInfo('${requestScope['contract_info.id']}')" style="vertical-align: middle;" /></a>&nbsp;
					</td>
					<td class="td-content-title">业务合同编号：</td>
					<td class="td-content"><input  class="mini-textbox" label="业务合同编号" readOnly  value="${requestScope['contract_info.contractnumber'] }" /></td>
				</tr>
	          </table>
          </div>
           <div class="mini-panel" title="变更前账号信息"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
					<tr class="tr-lessor-info tr-odd">
						<td class="td-content-title">开户账号：</td>
						<td class="td-content">
							<input  label="开户账号" id="beforeleaseaccnumber" name="beforeleaseaccnumber"  class="mini-combobox" textField="accnumber"  readonly
							   value="${requestScope['contract_signatory.leaseaccnumber'] }" text="${requestScope['contract_signatory.leaseaccnumber'] }"
							/>
						</td>
					 </tr>
					<tr class="tr-lessor-info tr-even" >
						<td class="td-content-title">开户银行：</td>
						<td class="td-content"><input id="beforeleaseaccbank" name="beforeleaseaccbank" label="开户银行" readonly  class="mini-textbox" value="${requestScope['contract_signatory.leaseaccbank']}"></td>
					</tr>
					 <tr class="tr-lessor-info tr-odd" >
						<td class="td-content-title" width="12%">开户户名：</td>
						<td class="td-content" width="38%"><input id="beforeleaseaccname" name="beforeleaseaccname" label="开户户名" readonly  class="mini-textbox"  value="${requestScope['contract_signatory.leaseaccname']}"></td>
					</tr>	
				</table>
          </div>
          <div class="mini-panel" title="变更后账号信息"  style="width:50%;float:left">
			  <table cellspacing="0" cellpadding="0" width="100%" class="fillTable" >
					<tr class="tr-lessor-info tr-odd">
						<td class="td-content-title">开户账号：</td>
						<td class="td-content">
							<input  label="开户账号" id="afterleaseaccnumber" name="afterleaseaccnumber" class="mini-combobox" textField="accnumber" valueField="accnumber" dataField="datas" allowInput="false"
							data-options="{_xmlFileName:'/eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml'}"  value="${requestScope['afterleaseaccnumber'] }" 
							text="${requestScope['afterleaseaccnumber'] }"
							onvaluechanged="afterleaseaccnumbervaluechange" onbeforeshowpopup="miniextonbeforeshowpopup"/>
						</td>
					 </tr>	
					<tr class="tr-lessor-info tr-even" >
						<td class="td-content-title">开户银行：</td>
						<td class="td-content"><input require="true" id="afterleaseaccbank" name="afterleaseaccbank" label="开户银行" readonly  class="mini-textbox" value="${requestScope['afterleaseaccbank']}"></td>
					</tr>
					 <tr class="tr-lessor-info tr-odd" >
						<td class="td-content-title" width="12%">开户户名：</td>
						<td class="td-content" width="38%"><input require="true" id="afterleaseaccname" name="afterleaseaccname" label="开户户名" readonly  class="mini-textbox"  value="${requestScope['afterleaseaccname']}"></td>
					</tr>
				</table>
          </div>
	           
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;display:none" onactivechanged="changTab">
	<div title="账号信息" name="contract_supplier" iconCls="icon-node"  >
			<jsp:include page="../contract_comm/contract_signatory.jsp">
			     <jsp:param value="" name="isView"/>
			</jsp:include>
		</div>
	</div>
	<div id="tabDetails" class="mini-tabs" activeIndex="0" style="width: 100%;" onactivechanged="changTab">
		<div title="银行账户变更通知"  name="contract_word" iconCls="icon-cut">
			<jsp:include page="account_change_notice.jsp" ></jsp:include>
		</div> 
	</div>
</div>
<script>

//表格值填入隐藏域
function fillHiddenVal(){
	
		var grid_new_contract_word_data= mini.get("contract_word").getData();
		mini.get("id_json_contract_word_str").setValue(mini.encode(grid_new_contract_word_data));

	if(mini.get("contract_supplier").isinitData==1){
		var grid_new_contract_supplier_data= mini.get("contract_supplier").getData();
		mini.get("id_json_contract_supplier_str").setValue(mini.encode(grid_new_contract_supplier_data));
	}
}

function opencustdetail(id,custclass){
 	var params = "id="+id+"&isView=true";
 	var url="";
	if(custclass=="CUST_INFO_COMPANY"){
		url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	}else{
		url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	}
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}

function afterleaseaccnumbervaluechange(e){
	var selected = e.selected;
	mini.getbyName("afterleaseaccbank").setValue(selected.accbank);
	mini.getbyName("afterleaseaccname").setValue(selected.accname);
	mini.getbyName("contract_signatory.bankno").setValue(selected.bankno);
}
</script>
<script type="text/javascript">
	$(document).ready(function(){
		if("${requestScope['afterleaseaccnumber'] }"==""){
			var beforeleaseaccnumber=mini.get("beforeleaseaccnumber").getValue();
			var beforeleaseaccbank=mini.get("beforeleaseaccbank").getValue();
			var beforeleaseaccname=mini.get("beforeleaseaccname").getValue();
			
			mini.get("afterleaseaccnumber").setValue(beforeleaseaccnumber);
			mini.get("afterleaseaccnumber").setText(beforeleaseaccnumber);
			mini.get("afterleaseaccbank").setValue(beforeleaseaccbank);
			mini.get("afterleaseaccname").setValue(beforeleaseaccname);
		}
		
	});
	
	function showContractInfo(id){
		var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+id;
		openFullScreenWindow(url);
	}
</script>
