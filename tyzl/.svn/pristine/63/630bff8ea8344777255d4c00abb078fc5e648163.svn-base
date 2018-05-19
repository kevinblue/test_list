<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/minidict" prefix="mini"%>
    <input name="proj_info.custInfo" id="proj_info.custInfo" type="hidden"  value="<mini:param  name="proj_info.custInfo" />"/>
    <input name="proj_info.projstatus" id="proj_info.projstatus" type="hidden"  value="<mini:param  name="proj_info.projstatus" />"/>
    <input name="proj_info.custclass" id="proj_info.custclass" type="hidden"  value="<mini:param  name="proj_info.custclass" />"/>
    <div id="proj_base_info_form" title="项目基本信息">
    <div class="mini-panel" title="项目基本信息" showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	          <tr class="tr-project-info tr-even">
	             <td class="td-content-title" width="12%">项目编号：</td>
	             <td class="td-content" width="38%"><input name="proj_info.projid" id ="project_id"  class="mini-textbox" label="项目编号" readOnly type="text" value="<mini:param  name="proj_info.projid"/>"></td>
	             <td class="td-content-title" width="12%">项目日期：</td>
	             <td class="td-content" width="38%"> <input id="id_projdate" name="proj_info.projdate" class="mini-datepicker" value="<mini:param  name="proj_info.projdate"/>" label="项目日期"  required="true" allowInput="false"/> <font class="required-tag">*</font> </td>
	          </tr>
	          <tr class="tr-project-info tr-odd">
	          <td class="td-content-title">租赁形式：</td>
	             <td class="td-content">
		             <input name="proj_info.leasform" id="id_leasform" class="mini-combobox" label="租赁形式" textField="name"  required="true"
			                  	   valueField="value"  readOnly
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'leas_form'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="proj_info.leasform"/>" 
								   text="<mini:param  name="rawValue_proj_info.leasform"/>" 
								   onvaluechanged="comboboxChanged"/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.leasform" name="rawValue_proj_info.leasform" value="<mini:param  name="rawValue_proj_info.leasform"/>" class="mini-textbox" style="display:none"/>
		          </td>
	             <td class="td-content-title">项目名称：</td>
	             <td class="td-content"><input style="width:70%" name="proj_info.projectname" id="project_name"  required="true" label="项目名称" class="mini-textbox"  type="text" value="<mini:param  name="proj_info.projectname"/>"  onclick="createProjectName();" > </td>
	          </tr>
	          <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">承租单位：</td>
	             <td class="td-content"><input id="cust_name" name="proj_info.custname" class="mini-textbox" readOnly require="true" label="承租单位"  type="text" value="<mini:param  name="proj_info.custname"/>" >
	                     <font class="required-tag">*</font><a href="javascript:void(0);">
	                     <img alt="客户信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="opencustdetail('<mini:param  name="proj_info.custInfo"/>','<mini:param  name="proj_info.custclass"/>')"/></a></td>
	             <td class="td-content-title">客户编号：</td>
	             <td class="td-content"><input name="proj_info.custnumber" class="mini-textbox" readOnly  type="text" label="客户编号" value="<mini:param  name="proj_info.custnumber"/>"/> <font class="required-tag">*</font> </td>
	         </tr>	   
	         
	           <tr class="tr-project-info tr-odd">   
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
	             <td class="td-content">
		             <input name="proj_info.leastype" id="leastype" label="设备类型" class="mini-combobox" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'leas_type'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="proj_info.leastype"/>" 
								   text="<mini:param  name="rawValue_proj_info.leastype"/>" 
								   onvaluechanged="comboboxChanged"/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.leastype" name="rawValue_proj_info.leastype" value="<mini:param  name="rawValue_proj_info.leastype"/>" class="mini-textbox" style="display:none"/>
	             </td>
	         </tr>	  
	         
	              
	         <tr class="tr-project-info tr-even">
	             <td class="td-content-title">项目类型：</td>
	             <td class="td-content">
						 <input name="proj_info.projtype" label="项目类型" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"    dataField="datas" allowInput="false"   
				                  	   data-options="{_params:{pid:'proj_type'}}" 
									   onbeforeshowpopup="onbeforeshowpopup" value="<mini:param  name="proj_info.projtype"/>"
									   text="<mini:param  name="rawValue_proj_info.projtype"/>" 
									   onvaluechanged="comboboxChanged"/>	 <font class="required-tag">*</font>
						 <input id="rawValue_proj_info.projtype" name="rawValue_proj_info.projtype" value="<mini:param  name="rawValue_proj_info.projtype"/>" class="mini-textbox" style="display:none"/></td>
	             <td class="td-content-title">项目规模：</td>
	             <td class="td-content">
			             <input name="proj_info.projscale" id="projscale" label="项目规模" class="mini-combobox" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_scale'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
									   value="<mini:param  name="proj_info.projscale"/>" 
									   text="<mini:param  name="rawValue_proj_info.projscale"/>" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag">*</font>
					     <input id="rawValue_proj_info.projscale" name="rawValue_proj_info.projscale" value="<mini:param  name="rawValue_proj_info.projscale"/>" class="mini-textbox" style="display:none"/>
	             </td>      
	          </tr>
	          
	          <tr class="tr-project-info tr-odd">  
	             <td class="td-content-title">项目来源：</td>
	             <td class="td-content">
			             <input name="proj_info.projsource" class="mini-combobox" label="项目来源" textField="name"  required="true"
				                  	   valueField="value"  
									   dataField="datas"
									   allowInput="false" 
									   data-options="{_params:{pid:'proj_source'}}" 
									   onbeforeshowpopup="onbeforeshowpopup"
						               value="<mini:param  name="proj_info.projsource"/>" 
									   text="<mini:param  name="rawValue_proj_info.projsource"/>" 
									   onvaluechanged="comboboxChanged" />
						 <font class="required-tag">*</font>
						 <input id="rawValue_proj_info.projsource" name="rawValue_proj_info.projsource" value="<mini:param  name="rawValue_proj_info.projsource"/>" class="mini-textbox" style="display:none"/>
			      </td>
	             <td class="td-content-title">内部行业：</td>
	             <td class="td-content">
		             <input name="proj_info.industrytype" class="mini-combobox" label="内部行业" textField="name"  required="true"
			                  	   valueField="value"  
								   dataField="datas"
								   allowInput="false" 
								   data-options="{_params:{pid:'cust_kind'}}" 
								   onbeforeshowpopup="onbeforeshowpopup"
								   value="<mini:param  name="proj_info.industrytype"/>" 
								   text="<mini:param  name="rawValue_proj_info.industrytype"/>" 
								   onvaluechanged="comboboxChanged" />
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.industrytype" name="rawValue_proj_info.industrytype"  value="<mini:param  name="rawValue_proj_info.industrytype"/>" class="mini-textbox" style="display:none"/>
		           </td>
	          </tr>	              
	         <tr class="tr-project-info tr-even">   
	             <td class="td-content-title">项目出单部门：</td>
	             <td class="td-content">
		             <input id="proj_info.department" name="proj_info.department" label="项目出单部门" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="<mini:dept name="rawValue_proj_info.department"/>"
							value="<mini:dept name="proj_info.department" valuetype="id"/>"
							/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.department" name="rawValue_proj_info.department" value="<mini:dept name="rawValue_proj_info.department"/>" class="mini-textbox" style="display:none"/>
		         </td>
		         <td class="td-content-title">项目经理：</td><td class="td-content">
		             <input id="proj_info.projmanage" name="proj_info.projmanage" label="项目经理" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="<mini:user name="rawValue_proj_info.projmanage"/>"
							value="<mini:user name="proj_info.projmanage" valuetype="id"/>"
							/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.projmanage" name="rawValue_proj_info.projmanage" value="<mini:user name="rawValue_proj_info"/>" class="mini-textbox" style="display:none"/>
				 </td>
	         </tr>
	          <tr class="tr-project-info tr-odd" style="display: none;">   
	              <td class="td-content-title">经办人：</td>
	             <td class="td-content">
		             <input id="proj_info.projregistrar" name="proj_info.projregistrar" label="经办人(录入人员)" class="mini-buttonedit mini-queryinput" allowInput="false"
							text="<mini:user name="rawValue_proj_info.projregistrar"/>"
							value="<mini:user name="proj_info.projregistrar" valuetype="id"/>"/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.projregistrar" name="rawValue_proj_info.projregistrar" value="<mini:user name="rawValue_proj_info.projregistrar"/>" class="mini-textbox" style="display:none"/>
		         </td>
	             <td class="td-content-title">项目协办：</td><td class="td-content">
		             <input id="proj_info.projassist" name="proj_info.projassist" label="项目协办" class="mini-buttonedit mini-queryinput" allowInput="false"
							text="<mini:user name="rawValue_proj_info.projassist"/>"
							value="<mini:user name="proj_info.projassist" valuetype="id"/>"
							/>
					 <font class="required-tag">*</font>
					 <input id="rawValue_proj_info.projassist" name="rawValue_proj_info.projassist" value="<mini:user name="rawValue_proj_info.projassist"/>" class="mini-textbox" style="display:none"/>
	             </td>
	          </tr>       
	</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('<mini:param  name="isView"/>' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields("proj_base_info_form");};
});
//生成项目名称
function createProjectName() {
	var project_name =mini.get("project_name").getValue();
	var proj_id=mini.get("project_id").getValue();
	var cust_name=mini.get("cust_name").getValue();
	var leasform=mini.get("rawValue_proj_info.leasform").getValue();
	if (project_name == '') {
		project_name = proj_id+"-"+cust_name +leasform+ '项目';
	}
	mini.get("project_name").setValue(project_name);
}
function opencustdetail(id,custclass){
 	var params = "id="+id+"&isView=true";
 	var url="";
	if(custclass=="CUST_INFO_COMPANY"){
		url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	}else if(custclass=="CUST_INFO_CLIQUE")
	{
		url = getRootPath()+"/leasing/cust_info/cust_clique/cust_clique_detail.bi?"+params
	}else
	{
		url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	}
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}
jQuery(function(){
	var strs = [{key:"proj_info.projassist",value:"项目协办"},{key:"proj_info.projmanage",value:"项目经理"},{key:"proj_info.projregistrar",value:"经办人"}];
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
			xmlFileName: '/eleasing/workflow/proj/proj_common/department_combox.xml'
		}
	});
});


</script>

