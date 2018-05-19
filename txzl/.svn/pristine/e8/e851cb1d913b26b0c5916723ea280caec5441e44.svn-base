<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="contract_property_rights_transfer" title="产权转移单">
	<table class="fillTable" cellspacing="0" cellpadding="0" style="border: 1px solid #99CCFF;">
		<tr class="tr-even">
			<td class="td-content-title" colspan="2" style="text-indent: 0px;"><a class="mini-button" id="id_btn_create_transfer_word" iconCls="icon-ok" onclick="createTransferWord()">生成产权转移清单</a></td>
		</tr>
	</table>
	<span class="module-content-title">清单列表</span>
	<div id="id_table_contract_property_rights_transfer"></div>
	<jsp:include page="contract_property_rights_transfer_word.jsp"></jsp:include>
</div>
<script type="text/javascript">
jQuery(function(){
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("contract_property_rights_transfer");
           mini.get("id_btn_create_transfer_word").hide();
		};
});
//生成产权转移清单
function createTransferWord(){
	var params={};
	params["flowunid"] = flowUnid;
	getPageData(params);
	var fileTeplate=new fileTemplateConfig({
		templateno : 'F-201412003',
		tableid : 'contract_property_rights_transfer',
		modelname : '产权转移清单',
		returntype : 'listtocurpage',
		parames : params
	});
	fileTeplate.createFile();
	saveCallBack();
}
function getPageData(params) {
   var  industrytype=  mini.get("rawValue_contract_info.industrytype").getValue();
   var  contract_id=  mini.get("contract_id").getValue();
   var  custname=  mini.get("cust_name").getValue();
   var  year=mini.formatDate(new Date(),'yyyy');
   var  month=mini.formatDate(new Date(),'MM');
   var  day=mini.formatDate(new Date(),'dd');
   var lease_from=mini.getbyName("rawValue_contract_info.leasform").getValue();
   
   params["contract_info.leasefrom"]=lease_from;
   params["contract_info.contractid"]=contract_id;
   params["contract_info.custname"]=custname;
   params["contract_info.year"]=year;
   params["contract_info.month"]=month;
   params["contract_info.day"]=day;
   if(industrytype=='医疗'){
	   params["contract_info.name"]="贵院"; 
   }else{
	   params["contract_info.name"]="贵公司"; 
   }
}
</script>