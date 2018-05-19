<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="contract_info.developid" id="contract_info.developid" type="hidden" value="${requestScope['contract_info.developid'] }"/>
    <input name="contract_info.custInfo" id="contract_info.custInfo" type="hidden"  value="${requestScope['contract_info.custInfo'] }"/>
    <input name="contract_info.custclass" id="contract_info.custclass" type="hidden"  value="${requestScope['contract_info.custclass'] }"/>
    <input name="contract_info.id" id="contract_info.id" type="hidden"  value="${requestScope['contract_info.id'] }"/>
    <div id="contract_base_info_form" title="合同基本信息">
    <div class="mini-panel" title="合同基本信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="contract_base_info" >
	          <tr class="tr-projectsss-info tr-even">
	             <td class="td-content-title" width="12%">项目编号：</td>
	             <td class="td-content" width="38%"><input name="contract_info.proj_id" readOnly id ="project_id"  class="mini-textbox" label="项目编号"  type="text" value="${requestScope['contract_info.proj_id']}"></td>
	             <td class="td-content-title" width="12%">项目日期：</td>
	             <td class="td-content" width="38%"> <input id="id_projdate" readOnly name="contract_info.projdate" class="mini-datepicker" value="${requestScope['contract_info.projdate'] }" label="项目日期"  required="true" allowInput="false"/></td>
	          </tr>
	          <tr class="tr-odd">
				<td class="td-content-title">业务合同编号：</td>
				<td class="td-content"><input id="contract_number"  onvaluechanged="checkcontractnumber"required="true" name="contract_info.contractnumber" class="mini-textbox" label="业务合同编号"  value="${requestScope['contract_info.contractnumber'] }" /></td>
			  	<td class="td-content-title">合同编号：</td>
				<td class="td-content"><input id="contract_id"  readOnly name="contract_info.contractid" class="mini-textbox" label="合同编号" required="true" value="${requestScope['contract_info.contractid'] }" allowInput="true" /></td>
				
			  </tr>
	          <tr class="tr-contract-info tr-odd">
	             <td class="td-content-title">项目名称：</td>
	             <td class="td-content"><input readOnly name="contract_info.projectname" id="project_name"  required="true" label="项目名称" class="mini-textbox"  type="text" value="${requestScope['contract_info.projectname']}">
	              <a href="javascript:void(0);">
	                     <img alt="项目信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="showDetail('${requestScope['contract_info.developid'] }')"/></a></td>
	             <td class="td-content-title">融资金额：</td>
	             <td class="td-content"><input name="contract_info.equipamt" id="contract_info.equipamt"  class="mini-textbox"  required="true" type="text" label="融资金额" value="${requestScope['contract_info.equipamt'] }"/></td>
	          </tr>	 
	          <tr class="tr-contract-info tr-even">  
	             <td class="td-content-title">客户名称：</td>  
	             <td class="td-content" >
	             		<input id="cust_id" required="true" name="contract_info.custid" class="mini-buttonedit mini-queryinput"  require="true" label="客户名称"   				
						value="${requestScope['contract_info.custid'] }"	             
	            		 text="${requestScope['rawValue_contract_info.custname'] }" >
	          			<input id="rawValue_contract_info.custname"
						name="rawValue_contract_info.custname"
						value="${requestScope['rawValue_contract_info.custname']}"
						class="mini-textbox"  style="display:none"/>    
	                     <a href="javascript:void(0);">
	                     <img alt="客户信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="opencustdetail('${requestScope['contract_info.custid'] }','${requestScope['contract_info.custclass'] }')"/>
	                     </a>
	              </td>
                      
                 <td class="td-content-title">客户编号：</td>
	             <td class="td-content"><input name="contract_info.custnumber" id="custnumber"  class="mini-textbox" readOnly  type="text" label="客户编号" value="${requestScope['contract_info.custnumber'] }"/></td>
	         </tr>	 
	      <tr class="tr-contract-info tr-odd">
		           <td class="td-content-title">项目类型：</td>
	             <td class="td-content">
				 <input name="contract_info.projtype" id="projtype" label="项目类型" class="mini-combobox" textField="name"  required="true"
				                  	 readOnly
				                  	   valueField="value"    dataField="datas" allowInput="false"  readOnly
				                  	   data-options="{_params:{pid:'projtype'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" 
									   value="${requestScope['contract_info.projtype'] }"
									   text="${requestScope['rawValue_contract_info.projtype'] }" 
									   onvaluechanged="comboboxChanged"/><font class="required-tag"></font>
						 <input id="rawValue_contract_info.projtype" name="rawValue_contract_info.projtype" value="${requestScope['rawValue_contract_info.projtype']}" class="mini-textbox" style="display:none"/></td> 
		          <td class="td-content-title">项目来源：</td>
	             <td class="td-content">
			             <input name="contract_info.projsource" class="mini-combobox" label="项目来源" textField="name"   
				                  	   readOnly
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
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">内部行业：</td>
	             <td class="td-content">
		              <input name="contract_info.industrytype" id="contract_info.industrytype" class="mini-combobox" label="内部行业" textField="name"  required="true"
			                  	  readOnly
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'cust_kind'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['contract_info.industrytype'] }" 
								   text="${requestScope['rawValue_contract_info.industrytype'] }" 
								   onvaluechanged="comboboxChanged" 
								   /> 
					 <input id="rawValue_contract_info.industrytype" name="rawValue_contract_info.industrytype"  value="${requestScope['rawValue_contract_info.industrytype']}" class="mini-textbox" style="display:none"/>
		           </td>
		     <td class="td-content-title">项目规模：</td>
	             <td class="td-content">
			             <input name="contract_info.projscale" id="projscale" label="项目规模" class="mini-combobox" textField="name"  required="true"
				                  	  readOnly
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_scale'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   value="${requestScope['contract_info.projscale'] }" 
									   text="${requestScope['rawValue_contract_info.projscale'] }" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag"></font> 
					     <input id="rawValue_contract_info.projscale" name="rawValue_contract_info.projscale" value="${requestScope['rawValue_contract_info.projscale']}" class="mini-textbox" style="display:none"/> 
	             </td>
	          </tr>	
	          <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">资金用途：</td>
	             <td class="td-content">
		             <input name="contract_info.funduse" id="funduse" label="资金用途" class="mini-combobox" textField="name"  required="true"
			                  	   readOnly
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'fund_use'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['contract_info.funduse'] }" 
								   text="${requestScope['rawValue_contract_info.funduse'] }" 
								   onvaluechanged="comboboxChanged"/>
					 
					 <input id="rawValue_contract_info.funduse" name="rawValue_contract_info.funduse" value="${requestScope['rawValue_contract_info.funduse']}" class="mini-textbox" style="display:none"/>
	             </td>
	             
	             <td class="td-content-title">固定资产：</td>
	             <td class="td-content">
		             <input name="contract_info.leastype" id="leastype" label="固定资产" class="mini-combobox" textField="name"  required="true"
			                  	   readOnly
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'leas_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['contract_info.leastype'] }" 
								   text="${requestScope['rawValue_contract_info.leastype'] }" 
								   onvaluechanged="comboboxChanged"/>
					 
					 <input id="rawValue_contract_info.leastype" name="rawValue_contract_info.leastype" value="${requestScope['rawValue_contract_info.leastype']}" class="mini-textbox" style="display:none"/>
	             </td>
	              
	         </tr>             
	        <tr class="tr-project-info tr-even">
	             <td class="td-content-title">项目出单部门：</td>
	             <td class="td-content">
	            <input id="contract_info.department" name="contract_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		readOnly
	             		required="true"
	             		
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
		             <input id="contract_info.projregistrar" name="contract_info.projregistrar" label="经办人(录入人员)" class="mini-buttonedit mini-queryinput" allowInput="false"
							readOnly
							text="${empty requestScope['rawValue_contract_info.projregistrar'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projregistrar']}"
							value="${empty requestScope['contract_info.projregistrar'] ? sessionScope.loginUser.id : requestScope['contract_info.projregistrar']}"
							/>
					  <font class="required-tag">*</font>
					 <input id="rawValue_contract_info.projregistrar" name="rawValue_contract_info.projregistrar" value="${empty requestScope['rawValue_contract_info.projregistrar'] ? sessionScope.loginUser.realname : requestScope['rawValue_contract_info.projregistrar']}" class="mini-textbox" style="display:none"/>
		         </td>
	          </tr>
	          
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
		          <tr style="display: none;">
		            <td class="td-content-title">项目开发类型：</td>
	           	  <td class="td-content"><input id="contract_info.projdeveloptype" name="contract_info.projdeveloptype" class="mini-textbox" readOnly  type="text" label="项目开发类型" value="${requestScope['contract_info.projdeveloptype'] }" style="display:none"/></td>
		          </tr>
	</table>

	</div>
	
	   <div id="base_showfile" class="mini-window" title="图片预览" showMaxButton="true"
			style="width: 60%; height: 60%;" showCloseButton="true"  showModal="true"
			allowResize="true" allowDrag="true" >
			<div id="imag">
			</div>
	    </div>
</div>
<script language="javascript">
jQuery(function(){
	/* miniui_ext.disableFormFields("contract_base_info_form");
	mini.get("contract_number").enable(); */
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("contract_base_info_form");};
	//onbusinesstypeChanged();
});
jQuery(function(){
	mini.get("contract_info.equipamt").setValue(formatNumberThousand(mini.get("contract_info.equipamt").getValue()));
}); 
function formatNumberThousand(s) {  
	   s += "";
	   s = s.replace(/,/g,"");
	   if(isNaN(s)){
		   return s;
	   }
	   //if(s==0){s="0.00";}
	   s += "";
	   //s = s.replace(/,/g,"");
	   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g;
	   var n1=s.replace(re,"$1,");
	   return n1;
}
/* window.onload=function(){
	createProjectName();
} */
//打开页面生成项目名称
/* function createProjectName() {
	var project_name =mini.get("project_name").getValue();
	//var proj_id=mini.get("project_id").getValue();
	var cust_name=mini.get("cust_name").getValue();
	if (project_name == '') {
		project_name = cust_name + '项目';
	}
	mini.get("project_name").setValue(project_name);
} */
//校验合同编号是否重复
function checkcontractnumber(){
	var contractnumber=$minigetvalue("contract_number");
	if(contractnumber){
		contractnumber=contractnumber.trim();
	}else{
		return false;
	}
	var returnresult=false;
	 $.ajax({
		 	url: urlPrefix+"/eleasing/jsp/cust_info/contract_info/check_contract_number.xml",
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
function showDetail(id) {
		var params = "id=" + id;
		var url;
		var developid=mini.get("contract_info.projdeveloptype").getValue();
		if(developid==1){//风电项目
			url= getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_wind_power_detail_see.bi?"
			+ params;
		}else if(developid==2){//供应链
			url= getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_supply_chain_detail_see.bi?"
			+ params;
		}else{//其他
			url= getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_others_detail_see.bi?"
			+ params;
		}
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winosption = "left=0px,top=0px,height="
			+ sheight
			+ "px,width="
			+ swidth
			+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winosption);
	} 
 /* jQuery(function(){
	var strs = [{key:"contract_info.projassist",value:"项目协办"},{key:"contract_info.projmanage",value:"项目经理"},{key:"contract_info.projregistrar",value:"经办人(录入人员)"}/* ,{key:"contract_info.projimpoter",value:"项目导入人"} ];
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
			xmlFileName: '/eleasing/workflow/proj/proj_common/department_lujingchange.xml'//department_dept.xml
		}
	}); 
});  */
/* function onbusinesstypeChanged(e){
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
} */
/*  function oncustkindchange(e){
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

}  */

 function onpurchasetypetwobeforeshowpopup(e){
	var purchasetypeone = mini.get("contract_info.industrytype");
	purchasetypeone.validate();
	var cb=e.sender;
	var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';
	var xmlFileName = cb._xmlFileName ||'/combos/comboDic.xml';
	url = url+"&xmlFileName="+xmlFileName+"&pid="+purchasetypeone.getValue();
	cb.setUrl(url);
	
}
jQuery(function(){
	tenwa.createQueryInput({
		id:'cust_id',
		label:'客户名称',
		textField:"name",
      	valueField:"id",
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_contract_info.custname").setValue(rowData.name);
			mini.get("cust_id").setValue(rowData.id);
			mini.get("custnumber").setValue(rowData.custnumber);
			
		}, 
		params: {
			xmlFileName: '/eleasing/workflow/contract/contract_comm/contract_custname.xml'   
		}
	}); 
}); 
</script>

