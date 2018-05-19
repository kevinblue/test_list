<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<style>
#id_table_cust_detail {
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_cust_detail td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
}
#id_table_cust_detail td .mini-textbox-border{
	background: #EEEEEE;
}
#id_table_cust_detail td .mini-buttonedit-border{
	background: #EEEEEE;
}
#id_table_cust_detail .mini-buttonedit-buttons{
	display: none;
}
#id_table_cust_detail .mini-textbox{
	width: 100%;
}
#id_table_cust_detail .mini-buttonedit{
	width: 100%;
}
</style>
    <input name="petition_approval.custInfo" id="petition_approval.custInfo" type="hidden"  value="${requestScope['petition_approval.custInfo'] }"/>
    <input name="petition_approval.projstatus" id="petition_approval.projstatus" type="hidden"  value="${requestScope['petition_approval.projstatus'] }"/>
    <input name="petition_approval.custclass" id="petition_approval.custclass" type="hidden"  value="${requestScope['petition_approval.custclass'] }"/>
    <div id="proj_base_info_form" title="公章使用登记信息">
    <div class="mini-panel" title="公章使用登记信息 " showCollapseButton="true" style="width: 100%;">
	    <table class="fillTable" cellspacing="0" cellpadding="0" id="proj_base_info" >
	    	<tr class="tr-project-info tr-odd">
             	<td class="td-content-title" width="12%">项目名称：</td>
             	<td class="td-content" width="38%"> <input id="seal_registration.projectname" name="seal_registration.projectname" label="项目名称" class="mini-textbox" value="${requestScope['seal_registration.projectname'] }"/> </td>
             	<td class="td-content-title" width="12%">登记日期：</td>
             	<td class="td-content" width="38%"> <input name="seal_registration.registrationdate" class="mini-datepicker" value="${requestScope['seal_registration.registrationdate'] }" label="登记日期" required="true" allowInput="false"/> </td>
             </tr>
              <tr class="tr-project-info tr-odd">
             	<td class="td-content-title" width="12%">流程编号/名称：</td>
             	<td class="td-content" width="38%"> <input  name="seal_registration.flownumber" class="mini-textbox" value="${requestScope['seal_registration.flownumber']}" label="流程编号/名称"  required="true"  allowInput="false" /> </td>
             	<td class="td-content-title" width="12%">盖章文件名称：</td>
             	<td class="td-content" width="38%"> <input id="seal_registration.sealfile" name="seal_registration.sealfile" label="盖章文件名称" class="mini-textbox" value="${requestScope['seal_registration.sealfile'] }" required="true" /> </td>
             </tr>
		     <tr class="tr-project-info tr-odd">
             	<td class="td-content-title">申请人：</td>
	             <td class="td-content" >
		             <input id="seal_registration.projmanage" name="seal_registration.projmanage" label="申请人" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_seal_registration.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_seal_registration.projmanage'] }"
							value="${empty requestScope['seal_registration.projmanage'] ? sessionScope.loginUser.id : requestScope['seal_registration.projmanage'] }"
							/>
					 <input id="rawValue_seal_registration.projmanage" name="rawValue_seal_registration.projmanage" value="${empty requestScope['rawValue_seal_registration.projmanage'] ? sessionScope.loginUser.realname : requestScope['rawValue_seal_registration.projmanage'] }" class="mini-textbox" style="display:none"/>
		         </td>
		         <td class="td-content-title">申请部门：</td>
	             <td class="td-content" >
		             <input id="seal_registration.projdept" name="seal_registration.projdept" label="申请部门" class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
							text="${empty requestScope['rawValue_seal_registration.projdept'] ? '' : requestScope['rawValue_seal_registration.projdept'] }"
							value="${empty requestScope['seal_registration.projdept'] ? '' : requestScope['seal_registration.projdept'] }"
							/>
					 <input id="rawValue_seal_registration.projdept" name="rawValue_seal_registration.projdept" value="${empty requestScope['rawValue_seal_registration.projdept'] ? '' : requestScope['rawValue_seal_registration.projdept'] }" class="mini-textbox" style="display:none"/>
		         </td>
             </tr>
             <tr class="tr-project-info tr-odd">
             	<td class="td-content-title" width="12%">用章种类：</td>
             	<td class="td-content">
		             <div id="cbl11" class="mini-checkboxlist"  name="seal_registration.sealtype" value="${requestScope['seal_registration.sealtype'] }" 
					     repeatItems="3" repeatLayout="table" textField="text" valueField="id"  
		        		data="[{id: '公章', text: '公章'}, {id: '法人章', text: '法人章'}, {id: '财务章', text: '财务章'}]" >
   					</div>          
		         </td>
             <td class="td-content-title" width="12%">文件是否已批准：</td>
			      <td class="td-content" width="38%">
                       <input id= "approval" 
                       name="approval" 
                       class="mini-combobox asLabel"
                       required=true 
                       label="文件是否已批准"
                       textField="text"   
                       valueField="value"
                       data="[{text:'是',value:'是'},{text:'否',value:'否'}]"   
                       allowInput="false"      
                       value="${requestScope['approval'] }"/>
                    </td>
             </tr>       
             <tr class="tr-project-info tr-odd">
             	<td class="td-content-title" width="12%">报送部门：</td>
             	<td class="td-content" width="38%"> <input id="seal_registration.submitdepartment" name="seal_registration.submitdepartment" label="报送部门" class="mini-textbox" value="${requestScope['seal_registration.submitdepartment'] }"/> </td>
             	<td class="td-content-title" width="12%">文件份数：</td>
             	<td class="td-content" width="38%"> <input id="seal_registration.documentnumber" name="seal_registration.documentnumber" label="文件份数" class="mini-textbox" value="${requestScope['seal_registration.documentnumber'] }" /> </td>
             </tr>
	       <tr class="tr-project-info tr-even">
	             <td class="td-content-title">备注：</td>
	             <td colspan="3" style="padding-top: 5px;padding-bottom: 5px;">
	             <textarea style="width:73.5%;height:120px"class="mini-textarea" id="seal_registration.remarks" name="seal_registration.remarks" value="${requestScope['seal_registration.remarks'] }"> </textarea>  
	             </td>
	       </tr>
	</table>
	</div>
</div>
<script language="javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("proj_base_info_form","");
		//ie 下textarea  disable之后不显示滚动条
		//mini.get("proj_base_info_form").setReadOnly(true);
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

jQuery(function(){
	tenwa.createQueryInput({
		id:'seal_registration.projdept',
		label:'申请部门',
		textField:"name",
      	valueField:"id",  
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_seal_registration.projdept").setValue(rowData.name);
		},
		params: {
			xmlFileName: '/eleasing/workflow/proj/proj_common/department_combox.xml'
		}
	});
});

jQuery(function(){
	tenwa.createQueryInput({
		id:'seal_registration.projmanage',
		label:'申请人',
		textField:"name",
      	valueField:"id",  
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_seal_registration.projmanage").setValue(rowData.name);
		},
		params: {
			xmlFileName: '/eleasing/workflow/proj/proj_common/officialseal_tuser.xml'
		}
	});
});
function isManager(){
	var param = {};
	var ismanager;
	param['loginuser'] = "${sessionScope['login_userid']}";
	param['xmlFileName'] = "/eleasing/workflow/admin_manager/petition_flow/petition_is_manager.xml";
	ajaxRequest({
		params:param,
		url:'${pageContext.request.contextPath}/table/getTableData.action',
		method : 'POST',
		async : false,
		success:function(response){
			var jsondata = eval('(' + response.responseText + ')').datas;
			ismanager = jsondata[0].ismanager;
			return ismanager;
		}
	})
	//return 是:否
	return ismanager;
}
</script>

