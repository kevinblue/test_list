<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input name="contract_info.custid" id="contract_info.custid" type="hidden" value="<mini:param  name="contract_info.custid"/>"/>
<input name="contract_info.contractstatus" id="contract_info.contractstatus" type="hidden" value="<mini:param  name="contract_info.contractstatus"/>"/>
<input name="contract_info.projid" id="contract_info.projid" type="hidden" value="<mini:param  name="contract_info.projid"/>"/>
<input name="contract_info.id" id="contract_info.id" type="hidden" value="<mini:param  name="contract_info.id"/>"/>
<input name="contract_info.contract_id" id="contract_info.contract_id" type="hidden" value="<mini:param  name="contract_info.contract_id"/>"/>
<div id="contract_base_info_form" title="合同基本信息">
	<div class="mini-panel" title="合同基本信息" showCollapseButton="true" style="width:100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%" id="contract_base_info">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">项目编号：</td>
				<td class="td-content" width="38%"><input name="contract_info.proj_id" id="project_id" class="mini-textbox" label="项目编号" readOnly type="text" value="<mini:param  name="contract_info.proj_id"/>"></td>
				<td class="td-content-title" width="12%">项目日期：</td>
				<td class="td-content" width="38%"><input name="contract_info.proj_date" id="proj_date" class="mini-textbox" label="项目日期" readOnly type="text" value="<mini:param  name="contract_info.projdate"/>"></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">系统合同编号：</td>
				<td class="td-content"><input id="contract_id" name="contract_info.contractid" class="mini-textbox" label="系统合同编号" allowInput="false" read only value="<mini:param  name="contract_info.contractid"/>"></td>
				<td class="td-content-title">业务合同编号：</td>
				<td class="td-content"><input id="contract_number"   name="contract_info.contractnumber" required="true" class="mini-textbox" label="业务合同编号"  allowInput="true" value="<mini:param  name="contract_info.contractnumber"/>"></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">租赁形式：</td>
				<td class="td-content">
					<input name="contract_info.leasform" class="mini-combobox" textField="name" required="true" label="租赁形式 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'leas_form'}}" onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="contract_info.leasform"/>" text="<mini:param  name="rawValue_contract_info.leasform"/>"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.leasform" name="rawValue_contract_info.leasform" value="<mini:param  name="rawValue_contract_info.leasform"/>"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">项目名称：</td>
				<td class="td-content" >
					<input style="width: 60%" name="contract_info.projectname" id="project_name" require="true" label="项目名称" class="mini-textbox" readOnly type="text"
						value="<mini:param  name="contract_info.projectname"/>" />
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">承租单位：</td>
				<td class="td-content">
					<input id="cust_name" name="contract_info.custname" class="mini-textbox" readOnly require="true" label="承租单位" type="text"
						value="<mini:param  name="contract_info.custname"/>">
					&nbsp;
					<a href="javascript:void(0);"><img alt="客户信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="opencustdetail('<mini:param  name="contract_info.custid"/>','<mini:param  name="contract_info.custclass"/>')" style="vertical-align: middle;" /></a>&nbsp;
				</td>
				<td class="td-content-title">客户编号：</td>
				<td class="td-content"><input name="contract_info.custnumber" class="mini-textbox" label="客户编号 " readOnly type="text" value="<mini:param  name="contract_info.custnumber"/>"/></td>
			</tr>
			 <tr class="tr-project-info tr-even">   
	            <td class="td-content-title">联合承租人：</td>
		        <td class="td-content">
					    <input class="mini-textbox" id="custname2"
						 type="text" name="custname2"	readOnly					    
						   />  
						   <input class="mini-hidden" id="custid2"
						 type="text" name="custid2"	readOnly					    
						   />  
				 </td>
				 <td class="td-content-title">设备类型：</td>
	             <td class="td-content-title">
		             <input name="contract_info.leastype" id="leastype" label="设备类型" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'leas_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="contract_info.leastype"/>" 
								   text="<mini:param  name="rawValue_contract_info.leastype"/>" 
								   onvaluechanged="comboboxChanged"/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_contract_info.leastype" name="rawValue_contract_info.leastype" value="<mini:param  name="rawValue_contract_info.leastype"/>" class="mini-textbox" style="display:none"/>
	             </td>
	         </tr>	  
			<tr class="tr-odd">
				<td class="td-content-title">项目类型：</td>
				<td class="td-content">
					<input name="contract_info.projtype" class="mini-combobox" textField="name" readOnly required="true" label="项目类型 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'proj_type'}}" onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="contract_info.projtype"/>" text="<mini:param  name="rawValue_contract_info.projtype"/>"
						onvaluechanged="comboboxChanged(e)"/>
					<input id="rawValue_contract_info.projtype" name="rawValue_contract_info.projtype" value="<mini:param  name="rawValue_contract_info.projtype"/>"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">项目规模：</td>
				<td class="td-content">
					<input name="contract_info.projscale" class="mini-combobox" textField="name" required="true" label="项目规模 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'proj_scale'}}" onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="contract_info.projscale"/>" text="<mini:param  name="rawValue_contract_info.projscale"/>"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.projscale" name="rawValue_contract_info.projscale" value="<mini:param  name="rawValue_contract_info.projscale"/>"
						class="mini-textbox" style="display: none" /></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">项目来源：</td>
				<td class="td-content">
					<input name="contract_info.projsource" class="mini-combobox" textField="name" required="true" label="项目来源 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'proj_source'}}" onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="contract_info.projsource"/>" text="<mini:param  name="rawValue_contract_info.projsource"/>"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.projsource" name="rawValue_contract_info.projsource" value="<mini:param  name="rawValue_contract_info.projsource"/>"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">内部行业：</td>
				<td class="td-content">
					<input name="contract_info.industrytype" class="mini-combobox" textField="name" required="true" label="内部行业 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'cust_kind'}}" onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="contract_info.industrytype"/>" text="<mini:param  name="rawValue_contract_info.industrytype"/>"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.industrytype" name="rawValue_contract_info.industrytype" value="<mini:param  name="rawValue_contract_info.industrytype"/>"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">项目出单部门：</td>
				<td class="td-content">
					<input id="contract_info.department" name="contract_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
	             		value="<mini:param  name="contract_info.department"/>"
						text="<mini:param  name="rawValue_contract_info.department"/>"
						/>
					<input id="rawValue_contract_info.department"
						name="rawValue_contract_info.department"
						value="<mini:param  name="rawValue_contract_info.department"/>"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">经办人：</td>
				<td class="td-content">
					<input id="contract_info.projregistrar" name="contract_info.projregistrar" label="经办人" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
						text="<mini:param  name="rawValue_contract_info.projregistrar"/>"
						value="<mini:param  name="contract_info.projregistrar"/>"
						/>
					<input id="rawValue_contract_info.projregistrar" name="rawValue_contract_info.projregistrar"
						value="<mini:param  name="rawValue_contract_info.projregistrar"/>"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">项目经理：</td>
				<td class="td-content">
					<input id="contract_info.projmanage" name="contract_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
						text="<mini:param  name="rawValue_contract_info.projmanage"/>"
						value="<mini:param  name="contract_info.projmanage"/>"
						/>
					<input id="rawValue_contract_info.projmanage" name="rawValue_contract_info.projmanage"
						value="<mini:param  name="rawValue_contract_info.projmanage"/>" class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">项目协办：</td>
				<td class="td-content">
					<input id="contract_info.projassist" name="contract_info.projassist" label="项目协办" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
						text="<mini:param  name="rawValue_contract_info.projassist"/>"
						value="<mini:param  name="contract_info.projassist"/>"
						/>
					<input id="rawValue_contract_info.projassist" name="rawValue_contract_info.projassist"
						value="<mini:param  name="rawValue_contract_info.projassist"/>"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param name="isView"/>' == 'true' || isViewHistoryTask==true){
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
 //校验业务合同编号是否重复
 function checkcontractnumber(){
		var contractnumber=$minigetvalue("contract_number");
		if(contractnumber){
			contractnumber=contractnumber.trim();
		}
		var returnresult=false;
		 $.ajax({
			 	url: urlPrefix+"/eleasing/jsp/contract_info/check_contract_number.xml",
			    cache: false,
			    async: false,
			    data :{"contractnumber":contractnumber},
			    success: function (text) {   
			    	var result = mini.decode(text);
			    	var res=result.datas[0].result;

			    	if(res>0){
			    		mini.alert("业务合同编号【"+contractnumber+"】编号已存在，请修改！");
			    		returnresult=true;
			    	}
			    }
			});
		 return returnresult;
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
		var strs = [{key:"contract_info.projassist",value:"项目协办"},{key:"contract_info.projmanage",value:"项目经理"},{key:"contract_info.projregistrar",value:"经办人"}];
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
		tenwa.createQueryInput({
			id:'contract_info.department',
			label:'项目出单部门',
			textField:"name",
	      	valueField:"id",
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
				mini.get("rawValue_contract_info.department").setValue(rowData.name);
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/department_combox.xml'
			}
		});
	}); 
	
</script>