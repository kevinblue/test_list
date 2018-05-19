<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="proj_info.developid" id="proj_info.developid" type="hidden" value="${requestScope['proj_info.developid'] }"/>
    <input name="proj_info.custInfo" id="proj_info.custInfo" type="hidden"  value="${requestScope['proj_info.custInfo'] }"/>
    <input name="proj_info.custclass" id="proj_info.custclass" type="hidden"  value="${requestScope['proj_info.custclass'] }"/>
    <div id="proj_base_info_form" title="项目基本信息">
    <div class="mini-panel" title="项目基本信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">项目编号：</td>
	             <td class="td-content" width="38%"><input name="proj_info.projid" id ="project_id"  class="mini-textbox" label="项目编号" readonly type="text" value="${requestScope['proj_info.projid']}"></td>
	             <td class="td-content-title" width="12%">项目日期：</td>
	             <td class="td-content" width="38%"> <input id="id_projdate" name="proj_info.projdate" class="mini-datepicker" value="${requestScope['proj_info.projdate'] }" label="项目日期"  required="true" allowInput="false"/></td>
	          </tr>
	             <tr class="tr-project-info tr-odd">
	             <td class="td-content-title">项目名称：</td>
	             <td class="td-content" colspan=3><input style="width:50%" name="proj_info.projectname" id="project_name"  required="true" label="项目名称" class="mini-textbox"  type="text" value="${requestScope['proj_info.projectname']}">
	                  <a href="javascript:void(0);">
	                     <img alt="项目信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="showDetail('${requestScope['proj_info.developid'] }')"/></a></td>
	          </tr>	 
	          <tr class="tr-project-info tr-even">  
	             <td class="td-content-title">客户名称：</td>  
	             <td class="td-content"><input id="cust_name" name="proj_info.custname" class="mini-textbox" readOnly require="true" label="客户名称"  type="text" value="${requestScope['proj_info.custname'] }" >
	                     <a href="javascript:void(0);">
	                     <img alt="客户信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="opencustdetail('${requestScope['proj_info.custInfo'] }','${requestScope['proj_info.custclass'] }')"/></a></td>
                 <td class="td-content-title">融资金额：</td>
	             <td class="td-content"><input name="proj_info.equipamt" id="proj_info.equipamt"  class="mini-textbox"  required="true" type="text" label="融资金额" value="${requestScope['proj_info.equipamt'] }"/></td>
	         </tr>	 
	         <tr class="tr-project-info tr-odd">
	           <td class="td-content-title">客户编号：</td>
	           <td class="td-content"><input name="proj_info.custnumber" class="mini-textbox" readOnly  type="text" label="客户编号" value="${requestScope['proj_info.custnumber'] }"/></td>
	             <td class="td-content-title" >租赁形式：</td>
		          <td class="td-content">	          
		          <input name="proj_info.leasform" id="proj_info.leasform" readonly label="租赁形式" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"  
				                  	   data-options="{_params:{pid:'leas_form'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" 
									   value="${requestScope['proj_info.leasform'] }"
									   text="${requestScope['rawValue_proj_info.leasform'] }" 
									   onvaluechanged="comboboxChanged"/><font class="required-tag"></font>
						 <input id="rawValue_proj_info.leasform" name="rawValue_proj_info.leasform" value="${requestScope['rawValue_proj_info.leasform']}" class="mini-textbox" style="display:none"/></td> 
		      </td> 
	         </tr>
	      <tr class="tr-project-info tr-even">
		           <td class="td-content-title">项目类型：</td>
	             <td class="td-content">
				 <input name="proj_info.projtype" id="projtype" label="项目类型" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"  
				                  	   data-options="{_params:{pid:'projtype'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" 
									   value="${requestScope['proj_info.projtype'] }"
									   text="${requestScope['rawValue_proj_info.projtype'] }" 
									   onvaluechanged="comboboxChanged"/><font class="required-tag"></font>
						 <input id="rawValue_proj_info.projtype" name="rawValue_proj_info.projtype" value="${requestScope['rawValue_proj_info.projtype']}" class="mini-textbox" style="display:none"/></td> 
		          <td class="td-content-title">项目来源：</td>
	             <td class="td-content">
			             <input name="proj_info.projsource" class="mini-combobox" label="项目来源" textField="name"   readOnly
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_source',description_:'proj_source0'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="${requestScope['proj_info.projsource'] }" 
									   text="${requestScope['rawValue_proj_info.projsource'] }" 
									   onvaluechanged="comboboxChanged" />
						 
						 <input id="rawValue_proj_info.projsource" name="rawValue_proj_info.projsource" value="${requestScope['rawValue_proj_info.projsource']}" class="mini-textbox" style="display:none"/>
			      </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">内部行业：</td>
	             <td class="td-content">
		              <input name="proj_info.industrytype" id="proj_info.industrytype" readonly class="mini-combobox" label="内部行业" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'cust_kind'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['proj_info.industrytype'] }" 
								   text="${requestScope['rawValue_proj_info.industrytype'] }" 
								   onvaluechanged="comboboxChanged" 
								   /> 
					 <input id="rawValue_proj_info.industrytype" name="rawValue_proj_info.industrytype"  value="${requestScope['rawValue_proj_info.industrytype']}" class="mini-textbox" style="display:none"/>
		           </td>
		     <td class="td-content-title">项目规模：</td>
	             <td class="td-content">
			             <input name="proj_info.projscale" id="projscale" label="项目规模" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_scale'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   value="${requestScope['proj_info.projscale'] }" 
									   text="${requestScope['rawValue_proj_info.projscale'] }" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag"></font> 
					     <input id="rawValue_proj_info.projscale" name="rawValue_proj_info.projscale" value="${requestScope['rawValue_proj_info.projscale']}" class="mini-textbox" style="display:none"/> 
	             </td>
	          </tr>	
	          <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">资金用途：</td>
	             <td class="td-content">
		             <input name="proj_info.funduse" id="funduse" label="资金用途" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'fund_use'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['proj_info.funduse'] }" 
								   text="${requestScope['rawValue_proj_info.funduse'] }" 
								   onvaluechanged="comboboxChanged"/>
					 
					 <input id="rawValue_proj_info.funduse" name="rawValue_proj_info.funduse" value="${requestScope['rawValue_proj_info.funduse']}" class="mini-textbox" style="display:none"/>
	             </td>
	             
	              <td class="td-content-title">固定资产：</td>
	             <td class="td-content">
		             <input name="proj_info.leastype" id="leastype" label="固定资产" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'leas_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								    value="${requestScope['proj_info.leastype'] }" 
								   text="${requestScope['rawValue_proj_info.leastype'] }" 
								   onvaluechanged="comboboxChanged"/>
					 
					 <input id="rawValue_proj_info.leastype" name="rawValue_proj_info.leastype" value="${requestScope['rawValue_proj_info.leastype']}" class="mini-textbox" style="display:none"/>
	             </td>  
	              
	         </tr>             
	        <tr class="tr-project-info tr-even">
	             <td class="td-content-title">项目出单部门：</td>
	             <td class="td-content">
	            <input id="proj_info.department" name="proj_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
	             		required="true"
	             		value="${requestScope['proj_info.department'] }"
						text="${requestScope['rawValue_proj_info.department'] }"
						/>
					<input id="rawValue_proj_info.department"
						name="rawValue_proj_info.department"
						value="${requestScope['rawValue_proj_info.department']}"
						class="mini-textbox"  style="display:none"/>
				 	</td>
		          <td class="td-content-title">项目协办：</td><td class="td-content">
		             <input id="proj_info.projassist" name="proj_info.projassist" label="项目协办" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.projassist'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projassist']}"
							value="${empty requestScope['proj_info.projassist'] ? sessionScope.loginUser.id : requestScope['proj_info.projassist']}"
							/>
					 <input id="rawValue_proj_info.projassist" name="rawValue_proj_info.projassist" value="${empty requestScope['rawValue_proj_info.projassist'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projassist']}" class="mini-textbox" style="display:none"/>
	             </td>
	        </tr>
	          <tr class="tr-project-info tr-odd">   
	             <td class="td-content-title">项目经理：</td><td class="td-content">
		             <input id="proj_info.projmanage" name="proj_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projmanage']}"
							value="${empty requestScope['proj_info.projmanage'] ? sessionScope.loginUser.id : requestScope['proj_info.projmanage']}"
							/>
					 
					 <input id="rawValue_proj_info.projmanage" name="rawValue_proj_info.projmanage" value="${empty requestScope['rawValue_proj_info.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.projmanage']}" class="mini-textbox" style="display:none"/>
				 </td>
	             <td class="td-content-title">经办人：</td>
	             <td class="td-content">
		             <input id="proj_info.presentname" name="proj_info.presentname" label="经办人(录入人员)" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_proj_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.presentname']}"
							value="${empty requestScope['proj_info.presentname'] ? sessionScope.loginUser.id : requestScope['proj_info.presentname']}"
							/>
					  <!-- <font class="required-tag">*</font> -->
					 <input id="rawValue_proj_info.presentname" name="rawValue_proj_info.presentname" value="${empty requestScope['rawValue_proj_info.presentname'] ? sessionScope.loginUser.realname : requestScope['rawValue_proj_info.presentname']}" class="mini-textbox" style="display:none"/>
		         </td>
	          </tr>
	          
	             <tr class="tr-project-info tr-even"   style="display: none;" >
	          <td class="td-content-title" >业务类型：</td>
	             <td class="td-content">
                   <input name="proj_info.businesstype" class="mini-combobox" label="业务类型" textField="name"  required="true" readonly
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'business_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="${requestScope['proj_info.businesstype'] }" 
								   text="${empty requestScope['rawValue_proj_info.businesstype'] ? (requestScope['proj_info.businesstype'] eq 'business_type.lease'?'租赁':'保理') :requestScope['rawValue_proj_info.businesstype']}" 
								   onvaluechanged="onbusinesstypeChanged"/>
					 
					 <input id="rawValue_proj_info.businesstype" name="rawValue_proj_info.businesstype" value="${empty requestScope['rawValue_proj_info.businesstype'] ? (requestScope['proj_info.businesstype'] eq 'business_type.lease'?'租赁':'保理') :requestScope['rawValue_proj_info.businesstype']}" class="mini-textbox" style="display:none"/>
		          </td>
		          <td class="td-content-title" >项目开发类型：</td>
	           	  <td class="td-content"><input id="proj_info.projdeveloptype" name="proj_info.projdeveloptype" class="mini-textbox" readOnly  type="text" label="项目开发类型" value="${requestScope['proj_info.projdeveloptype'] }" style="display:none"/></td>
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
	if('${param.isView}' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_base_info_form");};
	//onbusinesstypeChanged();
});	
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
 jQuery(function(){
	var strs = [{key:"proj_info.projassist",value:"项目协办"},{key:"proj_info.projmanage",value:"项目经理"},{key:"proj_info.presentname",value:"经办人"}/* ,{key:"proj_info.projimpoter",value:"项目导入人"} */];
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
		id:'proj_info.department',
		label:'项目出单部门',
		textField:"name",
      	valueField:"id",
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_proj_info.department").setValue(rowData.name);
		}, 
		params: {
			user_id:"${sessionScope['login_userid']}",
			xmlFileName: '/eleasing/workflow/proj/proj_common/department_dept.xml'   
			//department_lujingchange.xml
		}
	}); 
}); 
 function showDetail(id) {
		var params = "id=" + id;
		var url;
		var developid=mini.get("proj_info.projdeveloptype").getValue();
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
 
/* function onbusinesstypeChanged(e){
	if(mini.getbyName("proj_info.businesstype").getValue()=="business_type.factoring"){
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
	if(mini.getbyName("proj_info.industrytype").getValue()=="medical"){
		$("#id_medical_tr").show();
		$("#id_public_tr").hide();
		mini.getbyName("proj_info.custprofit").setValue("");
		mini.getbyName("proj_info.custdebt").setValue("");
	}else{
		mini.getbyName("proj_info.custlevel").setValue("");
		mini.getbyName("proj_info.custbednum").setValue("");
		$("#id_medical_tr").hide();
		$("#id_public_tr").show();
	}
	if(typeof(e)!='undefined'){
		comboboxChanged(e);
	}

}  */

/* function onpurchasetypetwobeforeshowpopup(e){
	var purchasetypeone = mini.get("proj_info.industrytype");
	purchasetypeone.validate();
	var cb=e.sender;
	var url = cb._url ||getRootPath()+'/table/getTableData.action?tracywindyRandom=1&decorate=none';
	var xmlFileName = cb._xmlFileName ||'/combos/comboDic.xml';
	url = url+"&xmlFileName="+xmlFileName+"&pid="+purchasetypeone.getValue();
	cb.setUrl(url);
	
} */
</script>

