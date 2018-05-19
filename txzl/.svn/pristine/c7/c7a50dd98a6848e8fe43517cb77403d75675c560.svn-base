<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="contract_info.projid" id="contract_info.projid" type="hidden" value="${requestScope['contract_info.id'] }"/>
    <input name="projidforend" id="projidforend" type="hidden" value="${requestScope['projidforend'] }"/>
    <input name="contract_info.custid" id="contract_info.custid" type="hidden" value="${requestScope['contract_info.custid'] }"/>
    <input name="contract_info.custclass" id="contract_info.custclass" type="hidden"  value="${requestScope['contract_info.custclass'] }"/>
    <div id="contract_base_info_form" title="保理合同基本信息">
    <div class="mini-panel" title="保理合同基本信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="contract_base_info" >
	          <tr class="tr-even">
				<td class="td-content-title" width="12%">项目编号：</td>
				<td class="td-content" width="38%"><input  id="proj_id"  name="contract_info.proj_id" readOnly class="mini-textbox" label="项目编号"  type="text" value="${requestScope['contract_info.proj_id']}"></td>
				<td class="td-content-title" width="12%">项目启动时间：</td>
				<td class="td-content" width="38%"><input  id="projdate" name="contract_info.projdate" readOnly class="mini-textbox" label="项目启动时间"  type="text" value="${requestScope['contract_info.projdate']}"></td>
			</tr>
			<tr class="tr-odd">
				<td class="td-content-title">合同编号：</td>
				<td class="td-content"><input id="contract_id" name="contract_info.contractid" class="mini-textbox" label="合同编号" value="${requestScope['contract_info.contractid'] }" allowInput="false" /></td>
				<td class="td-content-title">业务合同编号：</td>
				<td class="td-content"><input id="contract_number"  required="true" name="contract_info.contractnumber" class="mini-textbox" label="业务合同编号"  value="${requestScope['contract_info.contractnumber'] }" /></td>
			</tr>
	     
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">供应商名称：</td>
	             <td class="td-content" colspan=3><input style="width:50%" id="cust_name" name="contract_info.custname" class="mini-textbox" readOnly require="true" label="供应商名称"  type="text" value="${requestScope['contract_info.custname'] }" >
	                     <a href="javascript:void(0);">
	                     <img alt="供应商名称" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')"/></a></td>
	         </tr>
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title">项目名称：</td>
	             <td class="td-content" colspan=3><input style="width:50%" name="contract_info.projectname" readOnly id="projectname"  required="true" label="项目名称" class="mini-textbox"  type="text" value="${requestScope['contract_info.projectname']}"></td>
	          </tr>	         
	   <tr class="tr-project-info tr-odd">
		           <td class="td-content-title">项目类型：</td>
	             <td class="td-content">
				 <input name="contract_info.projtype" label="项目类型" readOnly class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"  
				                  	   data-options="{_params:{pid:'projtype'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" 
									   value="${requestScope['contract_info.projtype'] }"
									   text="${requestScope['rawValue_contract_info.projtype'] }" 
									   onvaluechanged="comboboxChanged"/><font class="required-tag"></font>
						 <input id="rawValue_contract_info.projtype" name="rawValue_contract_info.projtype" value="${requestScope['rawValue_contract_info.projtype']}" class="mini-textbox" style="display:none"/></td> 
		          <td class="td-content-title">项目来源：</td>
	             <td class="td-content">
			             <input name="contract_info.projsource" readOnly class="mini-combobox" label="项目来源" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_source',description_:'proj_source0'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="${requestScope['contract_info.projsource'] }" 
									   text="${requestScope['rawValue_contract_info.projsource'] }" 
									   onvaluechanged="comboboxChanged" />
						 
						 <input id="rawValue_contract_info.projsource" name="rawValue_contract_info.projsource" value="${requestScope['rawValue_contract_info.projsource']}" class="mini-textbox" style="display:none"/>
			      </td>
	          </tr>
	           <tr class="tr-project-info tr-even">
	          	  <td class="td-content-title">应收账款转让通知时间：</td>
	               <td class="td-content">
				    <input name="contract_info.transferdate" readOnly id="transferdate" label="应收账款转让通知时间" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"  
				                  	   data-options="{_params:{pid:'transfer_date'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" value="${requestScope['contract_info.transferdate'] }"
									   text="${requestScope['rawValue_contract_info.transferdate'] }" 
									   onvaluechanged="comboboxChanged"/><font class="required-tag"></font>
						 <input id="rawValue_contract_info.transferdate" name="rawValue_contract_info.transferdate" value="${requestScope['rawValue_contract_info.transferdate']}" class="mini-textbox" style="display:none"/></td> 
		          <td class="td-content-title">买方付款方式：</td>
	                <td class="td-content">
			             <input name="contract_info.paymentway" readOnly id="contract_info.paymentway" class="mini-combobox" label="买方付款方式" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'payment_way'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="${requestScope['contract_info.paymentway'] }" 
									   text="${requestScope['rawValue_contract_info.paymentway'] }" 
									   onvaluechanged="comboboxChanged" />
						 
					  <input id="rawValue_contract_info.paymentway" name="rawValue_contract_info.paymentway" value="${requestScope['rawValue_contract_info.paymentway']}" class="mini-textbox" style="display:none"/>
			      </td>
	          </tr>
	          <tr class="tr-project-info tr-odd" id="id_tr_factoring" >
	             <td class="td-content-title">保理类型：</td>
	             <td class="td-content">
		             <input name="contract_info.factoringtype" readOnly id="factoringtype" label="保理类型" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'factoring_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['contract_info.factoringtype'] }" 
								   text="${requestScope['rawValue_contract_info.factoringtype'] }" 
								   onvaluechanged="comboboxChanged"/>
					 
					 <input id="rawValue_contract_info.factoringtype" name="rawValue_contract_info.factoringtype" value="${requestScope['rawValue_contract_info.factoringtype']}" class="mini-textbox" style="display:none"/>
	             </td>
	             <td class="td-content-title">追索权：</td>
	             <td class="td-content"><input name="contract_info.recourseright" readOnly id ="recourseright"  class="mini-combobox" label="追索权" 
		             textField="text" valueField="text"
		             data="[{text:'有追索'},{text:'无追索'}]"
		              type="text" value="${requestScope['contract_info.recourseright']}" required="true" ></td>
	          </tr>
	         <tr class="tr-project-info tr-even">
	             <td class="td-content-title">项目出单部门：</td>
	             <td class="td-content">
	            <input id="contract_info.department" readOnly name="contract_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		value="${requestScope['contract_info.department'] }"
						text="${requestScope['rawValue_contract_info.department'] }"
						/>
					<input id="rawValue_contract_info.department" 
						name="rawValue_contract_info.department"
						value="${requestScope['rawValue_contract_info.department']}"
						class="mini-textbox"  style="display:none"/>
				 	</td>
		          <td class="td-content-title">项目协办：</td><td class="td-content">
		             <input id="contract_info.projassist" name="contract_info.projassist" label="项目协办" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		readOnly
		             		required="true"
							text="${empty requestScope['rawValue_contract_info.projassist'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projassist']}"
							value="${empty requestScope['contract_info.projassist'] ? sessionScope.loginUser.id : requestScope['contract_info.projassist']}"
							/>
					 <input id="rawValue_contract_info.projassist" name="rawValue_contract_info.projassist" value="${empty requestScope['rawValue_contract_info.projassist'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projassist']}" class="mini-textbox" style="display:none"/>
	             </td>
	        </tr>
	          <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title">项目经理：</td><td class="td-content">
		             <input id="contract_info.projmanage" name="contract_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		readOnly
		             		required="true"
							text="${empty requestScope['rawValue_contract_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projmanage']}"
							value="${empty requestScope['contract_info.projmanage'] ? sessionScope.loginUser.id : requestScope['contract_info.projmanage']}"
							/>
					 
					 <input id="rawValue_contract_info.projmanage" name="rawValue_contract_info.projmanage" value="${empty requestScope['rawValue_contract_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projmanage']}" class="mini-textbox" style="display:none"/>
				 </td>
	             <td class="td-content-title">经办人：</td>
	             <td class="td-content">
		             <input id="contract_info.presentname" name="contract_info.presentname" label="经办人(录入人员)" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		readOnly
							text="${empty requestScope['rawValue_contract_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.presentname']}"
							value="${empty requestScope['contract_info.presentname'] ? sessionScope.loginUser.id : requestScope['contract_info.presentname']}"
							/>
					  <font class="required-tag">*</font>
					 <input id="rawValue_contract_info.presentname" name="rawValue_contract_info.presentname" value="${empty requestScope['rawValue_contract_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.presentname']}" class="mini-textbox" style="display:none"/>
		         </td>
	          </tr>
	          
	           <tr class="tr-project-info tr-even">   
	         <td class="td-content-title" >供应商在金风融资的</br>总比例(含本次保理融资)：</td>
	             <td class="td-content" style="padding-top: 5px;padding-bottom: 5px;" >
	             <input readOnly vtype="float" label="供应商在金风融资的总比例(含本次保理融资)" name="contract_info.proportion" value="${requestScope['contract_info.proportion'] }" class="mini-textbox"   /> <font color="red">%</font> </td>
	             <td class="td-content-title">其它情况说明：</td>
	             <td  class="td-content"><input readOnly label="其它情况说明" name="contract_info.equipinfo" value="${requestScope['contract_info.equipinfo'] }" class="mini-textarea"  >  </td>
	       </tr>
	         <%--  <tr class="tr-project-info tr-even">   
	         <td class="td-content-title" >供应商在金风融资的</br>总比例(含本次保理融资)：</td>
	             <td style="padding-top: 5px;padding-bottom: 5px;">
	             <input style="width:50%;height:50px" label="其它情况说明" readOnly name="contract_info.proportion" value="${requestScope['contract_info.proportion'] }" class="mini-textarea"  type="text" >  </td>
	        
	        
	             <td class="td-content-title">其它情况说明：</td>
	             <td  style="padding-top: 5px;padding-bottom: 5px;"><input readOnly style="width:50%;height:50px" label="其它情况说明" name="contract_info.equipinfo" value="${requestScope['contract_info.equipinfo'] }" class="mini-textarea"  type="text" >  </td>
	       </tr> --%>
		   <tr class="tr-project-info tr-even"   style="display: none;" >
	          <td class="td-content-title" >业务类型：</td>
	             <td class="td-content">
                   <input name="contract_info.businesstype" class="mini-combobox" label="业务类型" textField="name"  required="true" readonly
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'business_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['contract_info.businesstype'] }" 
								   text="${empty requestScope['rawValue_contract_info.businesstype'] ? (requestScope['contract_info.businesstype'] eq 'business_type.lease'?'租赁':'保理') :requestScope['rawValue_contract_info.businesstype']}" 
								   onvaluechanged="onbusinesstypeChanged"/>
					 
					 <input id="rawValue_contract_info.businesstype" name="rawValue_contract_info.businesstype" value="${empty requestScope['rawValue_contract_info.businesstype'] ? (requestScope['contract_info.businesstype'] eq 'business_type.lease'?'租赁':'保理') :requestScope['rawValue_contract_info.businesstype']}" class="mini-textbox" style="display:none"/>
		          </td>
	          <td class="td-content-title" >租赁形式：</td>
		          <td class="td-content">	          
		          <input name="contract_info.leasform" id ="contract_info.leasform"  class="mini-textbox" label="租赁形式"  type="text" value="${requestScope['contract_info.leasform']}"></td>
		          </td>   
		    </tr>
	</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	/* miniui_ext.disableFormFields("contract_base_info_form");
	mini.get("contract_number").enable(); */
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
	    if(mini.get("contract_number").getValue() == ""){mini.alert("请填写业务合同编号！");return false;}
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
 /* jQuery(function(){
		var strs = [{key:"contract_info.projassist",value:"项目协办"},{key:"contract_info.projmanage",value:"项目经理"},{key:"contract_info.presentname",value:"经办人"}];
		for(var i = 0 ;i<strs.length;i++){
			tenwa.createQueryInput({ 
				id:strs[i].key,
				label:strs[i].value,
				textField:"name",
		      	valueField:"id",
				windowWidth: 600,
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
				user_id:"${sessionScope['login_userid']}",   
				xmlFileName: '/eleasing/workflow/proj/proj_common/department_dept.xml' //department_dept.xml
			}
		}); 
	}); */
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
</script>

