<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_info.custInfo" id="contract_info.custInfo" type="hidden" value="${requestScope['contract_info.custInfo'] }" />
<input name="contract_info.contractstatus" id="contract_info.contractstatus" type="hidden" value="${requestScope['contract_info.contractstatus'] }" />
<input name="contract_info.projid" id="contract_info.projid" type="hidden" value="${requestScope['contract_info.projid']}" />
<input name="contract_info.id" id="contract_info.id" type="hidden" value="${requestScope['contract_info.id']}" />
<input name="contractid" id="contractid" type="hidden" value="${requestScope['contractId']}" />
<input name="contract_info.contract_id" id="contract_info.contract_id" type="hidden" value="${requestScope['contract_info.contract_id']}" />
<div id="contract_base_info_form" title="基本信息">
	<div class="mini-panel" title="基本信息" showCollapseButton="true" style="width:100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%" id="contract_base_info">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">项目编号：</td>
				<td class="td-content" width="38%"><input name="contract_info.proj_id" id="project_id" class="mini-textbox" label="项目编号" readOnly type="text" value="${requestScope['contract_info.proj_id']}"></td>
				<td class="td-content-title">业务合同编号：</td>
				<td class="td-content"><input id="contract_number"  required="true" name="contract_info.contractnumber" class="mini-textbox" label="业务合同编号" value="${requestScope['contract_info.contractnumber'] }" /></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">承租单位：</td>
				<td class="td-content">
					<input id="cust_name" name="contract_info.custname" class="mini-textbox" readOnly require="true" label="承租单位" type="text"
						value="${requestScope['contract_info.custname'] }">
					&nbsp;
					<a href="javascript:void(0);"><img alt="客户信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')" style="vertical-align: middle;" /></a>&nbsp;
				</td>
				
				<td class="td-content-title">项目名称：</td>
				<td class="td-content" colspan="3" >
					<input style="width: 60%" name="contract_info.projectname" id="project_name" require="true" label="项目名称" class="mini-textbox" readOnly type="text"
						value="${requestScope['contract_info.projectname']}" /><font style="color: red;">（系统自动生成）* </font>
				</td>
			</tr>
			<tr class="tr-even">
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">剩余租金：</td>
				<td class="td-content"><input name="contract_info.currentoverage" class="mini-textbox"  readOnly type="text" value="${requestScope['contract_info.currentoverage'] }" /></td>
				<td class="td-content-title">剩余本金：</td>
				<td class="td-content"><input name="contract_info.curcorpusoverage" class="mini-textbox"   readOnly type="text" value="${requestScope['contract_info.curcorpusoverage'] }" /></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">重大事项状态：</td>
				<td class="td-content">
					<input id="contract_info.thingstatus" name="contract_info.thingstatus" value="${requestScope['contract_info.thingstatus']}"
						class="mini-textbox"/>
				</td>
				
				<td class="td-content-title">项目经理：</td>
				<td class="td-content">
					<input id="contract_info.projmanage" name="contract_info.projmanage"
						value="${requestScope['contract_info.projmanage']}" class="mini-textbox"  />
				</td>
			</tr>
			 
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_base_info_form");
	};
});
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
 function checkContractNumberUnique(){
	   // if(mini.get("contract_number").getValue() == ""){mini.alert("请填写业务合同编号！");return false;}
		var flag = false;
		var param = {};
		param.xmlFileName = '/eleasing/workflow/contract/contract_comm/get_contract_by_contractnumber.xml';
		param.contractnumer = mini.get("contract_number").getValue();
		ajaxRequest({
			url : '${pageContext.request.contextPath}/table/getTableData.action',
			method : 'POST',
			success : function(res) {
				var scustinfo = res.responseText;
				scustinfo = scustinfo.replace(/(^\s+)|(\s+$)/g, "");
				var obj =mini.decode(scustinfo);
				if("" != obj.datas){
					flag = true;
				}
			},
			async : false,
			failure : function(res) {
			},
			params : param
		});
		if(flag==true){mini.alert("该业务合同编号已存在！");}
		return flag;
	}
 jQuery(function(){
		var strs = [{key:"contract_info.projmanage",value:"项目经理"}];
		for(var i = 0 ;i<strs.length;i++){
			tenwa.createQueryInput({ 
				id:strs[i].key,
				label:strs[i].value,
				windowWidth: 600,
				textField:"name",
		      	valueField:"id",
				onSelect: function($me, inputObj, rowData){
					mini.get("rawValue_"+inputObj.id).setValue(rowData.name);
				},
				params: {
					xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'
				}
			});
		}
	}); 
</script>