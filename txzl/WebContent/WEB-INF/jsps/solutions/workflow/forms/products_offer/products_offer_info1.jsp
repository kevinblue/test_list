<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<input name="contract_info.custInfo" id="contract_info.custInfo" type="hidden" value="${requestScope['contract_info.custInfo'] }" />
<input name="contract_info.businessmanager" id="contract_info.businessmanager" type="hidden" value="${requestScope['contract_info.businessmanager'] }" />
<input name="contract_info.contractstatus" id="contract_info.contractstatus" type="hidden" value="${requestScope['contract_info.contractstatus'] }" />
<input name="contract_info.projid" id="contract_info.projid" type="hidden" value="${requestScope['contract_info.projid']}" />
<input name="contract_info.id" id="contract_info.id" class="mini-textbox" style="display: none;" value="${requestScope['contract_info.id']}" />
<input name="contract_info.contract_id" id="contract_info.contract_id" type="hidden" value="${requestScope['contract_info.contract_id']}" />
<div id="contract_base_info_form" title="合同基本信息">
	<div class="mini-panel" title="合同基本信息" showCollapseButton="true" style="width:100%;">
		<table class="fillTable" cellspacing="0" cellpadding="0" style="width:100%" id="contract_base_info">
			<tr class="tr-even">
				<td class="td-content-title" width="12%">项目编号：</td>
				<td class="td-content" width="38%"><input name="contract_info.proj_id" id="project_id" class="mini-textbox" label="项目编号" readOnly type="text" value="${requestScope['contract_info.proj_id']}"></td>
				<td class="td-content-title" width="12%">项目启动时间：</td>
				<td class="td-content" width="38%"><input name="contract_info.proj_date" id="proj_date" class="mini-textbox" label="项目启动时间" readOnly type="text" value="${requestScope['contract_info.projdate']}"></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">合同编号：</td>
				<td class="td-content"><input id="contract_id" name="contract_info.contractid" class="mini-textbox" label="合同编号" value="${requestScope['contract_info.contractid'] }" allowInput="false" /></td>
				<td class="td-content-title">业务合同编号：</td>
				<td class="td-content"><input id="contract_number"  required="true" name="contract_info.contractnumber" class="mini-textbox" label="业务合同编号"  value="${requestScope['contract_info.contractnumber'] }" /></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">项目名称：</td>
				<td class="td-content" colspan="3" >
					<input style="width: 60%" name="contract_info.projectname" id="project_name" require="true" label="项目名称" class="mini-textbox" readOnly type="text"
						value="${requestScope['contract_info.projectname']}" /><font style="color: red;">（系统自动生成）* </font>
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">承租单位：</td>
				<td class="td-content" >
					<input style="width: 38%" id="cust_name" name="contract_info.custname" class="mini-textbox" readOnly require="true" label="承租单位" type="text"
						value="${requestScope['contract_info.custname'] }">
					&nbsp;
					<a href="javascript:void(0);"><img alt="客户信息" src="${pageContext.request.contextPath}/menuIcons/search.png"
						onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')" style="vertical-align: middle;" /></a>&nbsp;
				</td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">客户编号：</td>
				<td class="td-content"><input name="contract_info.custnumber" class="mini-textbox" label="客户编号 " readOnly type="text" value="${requestScope['contract_info.custnumber'] }" /></td>				
				<td class="td-content-title">项目来源：</td>
				<td class="td-content">
					<input name="contract_info.projsource" class="mini-combobox" textField="name" required="true" label="项目来源 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'proj_source'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.projsource'] }" text="${requestScope['rawValue_contract_info.projsource'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.projsource" name="rawValue_contract_info.projsource" value="${requestScope['rawValue_contract_info.projsource']}"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">内部行业：</td>
				<td class="td-content">
					<input name="contract_info.industrytype" id="contract_info.industrytype" class="mini-combobox" textField="name" required="true" label="内部行业 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'cust_kind'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.industrytype'] }" text="${requestScope['rawValue_contract_info.industrytype'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.industrytype" name="rawValue_contract_info.industrytype" value="${requestScope['rawValue_contract_info.industrytype']}"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">子行业：</td>
				<td class="td-content">
					<input name="contract_info.subcustkind" id="contract_info.subcustkind" class="mini-combobox" textField="name" required="true" label="项目规模 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:''}}" 
								   onbeforeshowpopup="onpurchasetypetwobeforeshowpopup"
								   onEnter="search" value="${requestScope['contract_info.subcustkind'] }" text="${requestScope['rawValue_contract_info.subcustkind'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.subcustkind" name="rawValue_contract_info.subcustkind" value="${requestScope['rawValue_contract_info.subcustkind']}"
						class="mini-textbox" style="display: none" /></td> 
			</tr>
			<tr class="tr-even">
				<td class="td-content-title">租赁形式：</td>
				<td class="td-content">
					<input name="contract_info.leasform" class="mini-combobox" textField="name" required="true" label="租赁形式 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'leas_form'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.leasform'] }" text="${requestScope['rawValue_contract_info.leasform'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.leasform" name="rawValue_contract_info.leasform" value="${requestScope['rawValue_contract_info.leasform']}"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">资金用途：</td>
				<td class="td-content">
					<input name="contract_info.funduse" class="mini-combobox" textField="name" required="true" label="固定资产 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'fund_use'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.funduse'] }" text="${requestScope['rawValue_contract_info.funduse'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.funduse" name="rawValue_contract_info.funduse" value="${requestScope['rawValue_contract_info.funduse']}"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">业务类型：</td>
				<td class="td-content">
					<input name="contract_info.businesstype" class="mini-combobox" textField="name" required="true" label="租赁形式 " valueField="value" dataField="datas" allowInput="false" readonly
						data-options="{_params:{pid:'business_type'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.businesstype'] }" text="${requestScope['rawValue_contract_info.businesstype'] }"
						onvaluechanged="onbusinesstypeChanged" />
					<input id="rawValue_contract_info.businesstype" name="rawValue_contract_info.businesstype" value="${requestScope['rawValue_contract_info.businesstype']}"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title" >主要竞争对手：</td>
				<td class="td-content" > <input id="competitors" name="contract_info.competitors" class="mini-textbox" value="${requestScope['contract_info.competitors'] }" label="主要竞争对手"  /> </td>
			</tr>
			<tr class="tr-even" id="id_tr_factoring">
				<td class="td-content-title">保理类型：</td>
				<td class="td-content">
					<input name="contract_info.factoringtype" id="factoringtype" class="mini-combobox" textField="name" required="true" label="保理类型 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'factoring_type'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.factoringtype'] }" text="${requestScope['rawValue_contract_info.factoringtype'] }"
						onvaluechanged="comboboxChanged" />
					<input id="rawValue_contract_info.factoringtype" name="rawValue_contract_info.factoringtype" value="${requestScope['rawValue_contract_info.factoringtype']}"
						class="mini-textbox" style="display: none" />
				</td>
				<td class="td-content-title">追索权：</td>
				<td class="td-content"><input name="contract_info.recourseright" id ="recourseright"  class="mini-combobox" label="追索权" 
					textField="text" valueField="text"
					data="[{text:'有'},{text:'无'}]"
					type="text" value="${requestScope['contract_info.recourseright']}" required="true" ></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">起租方式：</td>
				<td class="td-content">
					<input id="onhiretype" name="contract_info.onhiretype" class="mini-combobox" textField="name" required="true" label="起租方式 " valueField="value" dataField="datas" allowInput="false"
						data-options="{_params:{pid:'onhire_type'}}" onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.onhiretype'] }" text="${requestScope['rawValue_contract_info.onhiretype'] }"
						onitemclick="onhireTypeComboboxChanged" />
					<input id="rawValue_contract_info.onhiretype" name="rawValue_contract_info.onhiretype" value="${requestScope['rawValue_contract_info.onhiretype']}"
						type="hidden" /></td>			
			<td class="td-content-title">合同文本类型：</td>
			<td class="td-content"><input name="contract_info.contracttype" id ="contracttype"  class="mini-combobox" label="合同文本类型" 
				textField="text" valueField="text" data="[{text:'标准'},{text:'非标准'}]" 
				value="${requestScope['contract_info.contracttype'] }" required="true" onvaluechanged="contracttypelawmanagechanged"></td>
			</tr>
			<tr class="tr-even">
	             <td class="td-content-title">设备来源：</td>
	             <td class="td-content">
		             <input name="contract_info.equipsource" id="equipsource" label="设备来源" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'equip_source'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['contract_info.equipsource'] }" 
								   text="${requestScope['rawValue_contract_info.equipsource'] }" 
								   onvaluechanged="comboboxChanged"/>
					 <input id="rawValue_contract_info.equipsource" name="rawValue_contract_info.equipsource" value="${requestScope['rawValue_contract_info.equipsource']}" class="mini-textbox" style="display:none"/>
	             </td>
				<td class="td-content-title">项目出单部门：</td>
				<td class="td-content">
					<input id="contract_info.department" name="contract_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
	             		value="${requestScope['contract_info.department'] }"
						text="${requestScope['rawValue_contract_info.department'] }"
						
						/>
					<input id="rawValue_contract_info.department"
						name="rawValue_contract_info.department"
						value="${requestScope['rawValue_contract_info.department']}"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			 <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">部门路径：</td>
	             <td class="td-content">
	            <input id="contract_info.deptroute" name="contract_info.deptroute" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
	             		width="60%"
	             		value="${requestScope['contract_info.deptroute'] }"
						text="${requestScope['rawValue_contract_info.deptroute'] }"
						
						/>
					<input id="rawValue_contract_info.deptroute"
						name="rawValue_contract_info.deptroute"
						value="${requestScope['rawValue_contract_info.deptroute']}"
						class="mini-textbox"  style="display:none"/>
				 	</td>
		         <td class="td-content-title">项目导入人：</td><td class="td-content">
		             <input id="contract_info.projimpoter" name="contract_info.projimpoter" label="项目导入人" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_contract_info.projimpoter'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projimpoter']}"
							value="${empty requestScope['contract_info.projimpoter'] ? sessionScope.loginUser.id : requestScope['contract_info.projimpoter']}"
							/>
					 
					 <input id="rawValue_contract_info.projimpoter" name="rawValue_contract_info.projimpoter" value="${empty requestScope['rawValue_contract_info.projimpoter'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projimpoter']}" class="mini-textbox" style="display:none"/>
	             </td>    
	        </tr>
			<tr class="tr-odd">
				<td class="td-content-title">项目经理：</td>
				<td class="td-content">
					<input id="contract_info.projmanage" name="contract_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
						text="${requestScope['rawValue_contract_info.projmanage'] }"
						value="${requestScope['contract_info.projmanage'] }"
						/>
					<input id="rawValue_contract_info.projmanage" name="rawValue_contract_info.projmanage"
						value="${requestScope['rawValue_contract_info.projmanage']}" class="mini-textbox" style="display:none"/>
				</td>
				<td class="td-content-title">项目协办：</td>
				<td class="td-content">
					<input id="contract_info.projassist" name="contract_info.projassist" label="项目协办" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
						text="${requestScope['rawValue_contract_info.projassist'] }"
						value="${requestScope['contract_info.projassist'] }"
						/>
					<input id="rawValue_contract_info.projassist" name="rawValue_contract_info.projassist"
						value="${requestScope['rawValue_contract_info.projassist']}"
						class="mini-textbox" style="display: none" />
				</td>
			</tr>
			<tr class="tr-even">
					         <td class="td-content-title">商务经办：</td><td class="td-content">
		             <input id="contract_info.projcommisionera" name="contract_info.projcommisionera" label="商务经办" class="mini-buttonedit mini-textbox" allowInput="false" readOnly
							text="${requestScope['rawValue_contract_info.projcommisionera']}"
							value="${requestScope['contract_info.projcommisionera']}"
							/>
					 
					 <input id="rawValue_contract_info.projcommisionera" name="rawValue_contract_info.projcommisionera" value="${requestScope['rawValue_contract_info.projcommisionera']}" class="mini-textbox" style="display:none"/>
	             </td>
	             <td class="td-content-title">法务经办：</td><td class="td-content">
		             <input id="contract_info.lawmanage" name="contract_info.lawmanage" label="法务经办" class="mini-buttonedit mini-queryinput" allowInput="false" 
							text="${requestScope['rawValue_contract_info.lawmanage']}"
							value="${requestScope['contract_info.lawmanage']}" 
							/>
					 <input id="rawValue_contract_info.lawmanage" name="rawValue_contract_info.lawmanage" value="${requestScope['rawValue_contract_info.lawmanage']}" class="mini-textbox" style="display:none"/>
	             </td>   
			</tr>
	       <tr class="tr-odd">
	             <td class="td-content-title">租赁物概况描述：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;"><input style="width:72%;height:50px" label="租赁物概况描述" name="contract_info.equipinfo" value="${requestScope['contract_info.equipinfo'] }" class="mini-textarea"  type="text" >  </td>
	       </tr>
		</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_base_info_form");
	};
	onbusinesstypeChanged();
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
		var strs = [{key:"contract_info.projassist",value:"项目协办"},{key:"contract_info.projmanage",value:"项目经理"}];
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
			id:'contract_info.lawmanage',
			label:'法务经办',
			windowWidth: 600,
			textField:"name",
	      	valueField:"id",
			onSelect: function($me, inputObj, rowData){
				mini.get("rawValue_"+inputObj.id).setValue(rowData.name);
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/choose_group_user.xml',
				group_code:'g_legal_manager'
			}
		});
		
		tenwa.createQueryInput({
			id:'contract_info.deptroute',
			label:'部门路径',
			textField:"name",
	      	valueField:"id",
			windowWidth: 600,
			onSelect: function($me, inputObj, rowData){
				mini.get("rawValue_contract_info.deptroute").setValue(rowData.name);
			},
			params: {
				xmlFileName: '/eleasing/workflow/proj/proj_common/department_lujing.xml'
			}
		});
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
				xmlFileName: '/eleasing/workflow/proj/proj_common/department_deptlujing.xml'
			}
		});
	}); 
 function onbusinesstypeChanged(e){
		if(mini.getbyName("contract_info.businesstype").getValue()=="business_type.factoring"){
			$("#id_tr_factoring").show();
		}else{
			mini.get("factoringtype").setValue("");
			mini.get("recourseright").setValue("");
			$("#id_tr_factoring").hide();
		}
		if(typeof(e)!='undefined'){
			comboboxChanged(e);
		}
	}
	function oncustkindchange(e){
		if(mini.getbyName("contract_info.industrytype").getValue()=="medical"){
			$("#id_medical_tr").show();
			$("#id_public_tr").hide();
			mini.getbyName("contract_info.custprofit").setValue("");
			mini.getbyName("contract_info.custdebt").setValue("");
		}else{
			mini.getbyName("contract_info.custlevel").setValue("");
			mini.getbyName("contract_info.custbednum").setValue("");
			$("#id_medical_tr").hide();
			$("#id_public_tr").show();
		}
		if(typeof(e)!='undefined'){
			comboboxChanged(e);
		}
		
	
		
	}
	
	function onpurchasetypetwobeforeshowpopup(e){
		var purchasetypeone = mini.get("contract_info.industrytype");
		purchasetypeone.validate();
		var cb=e.sender;
		var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';
		var xmlFileName = cb._xmlFileName ||'/combos/comboDic.xml';
		url = url+"&xmlFileName="+xmlFileName+"&pid="+purchasetypeone.getValue();
		cb.setUrl(url);
		
	}
	
	function onhireTypeComboboxChanged(e){
		document.getElementById('rawValue_contract_info.onhiretype').value = e.sender.text;
	}
	function onhireTypeQueryChanged(e){
		document.getElementById('rawValue_contract_info.lawmanage').value = e.sender.text;
	}
	function contracttypelawmanagechanged(){
		var sender = mini.get("contracttype").getValue();
		if(sender=="标准"){
			mini.get("contract_info.lawmanage").setRequired(false);
		}else{
			mini.get("contract_info.lawmanage").setRequired(true);
		}
	}
</script>
 