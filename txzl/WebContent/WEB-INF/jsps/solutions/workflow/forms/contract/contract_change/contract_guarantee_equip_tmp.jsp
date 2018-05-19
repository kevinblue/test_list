<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var conid="${requestScope['contract_info.id']}";
	var showTools = true;
	var isView='${param.isView}';
	if(isView == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		params:{
			docid:flowUnid,
			 conid:conid
				
			},
		id: "contract_guarantee_equip",
		renderTo: "id_table_contract_guarantee_equip",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		remoteOper : true,
		entityClassName : "com.tenwa.leasing.entity.contract.ContractGuaranteeEquipTmp",
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', {html:'删除',
			  plain:true,
			  iconCls:'icon-add',
			  handler:function(miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					markDeletion3(row.id);
				}else{
						alert("未选中行");			 
				}
	         } 
         }],
		//data: JsonUtil.decode('${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'),
		xmlFileName : '/eleasing/workflow/contract/contract_change/contract_guarantee_equip_tmp.xml', 
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{
				field:'contractid',
				header:'合同id',
				visible: false,
				formEditorConfig:{
				     value:conid,
				     fieldVisible:false
				         }
			},
			
			{
				field:'docid',
				header:'流程id',
				visible: false,
				formEditorConfig:{
				     value:flowUnid,
				     fieldVisible:false
				         }
			},
			{field:'',header:'操作',
		  		   formEditorConfig:{
              fieldVisible:false},
			     renderer:function(e){
				          var id = e.record.id;
				          var oldid = e.record.oldid
			              return "<a href='javascript:void(0);' class='action' onclick='showcontractloadfile(\"" + (oldid==""?id:oldid) + "\",\"one\",\""+isView+"\")'>查看/上传资料 </a>";
			               }},
			{field: 'equipname', header: '抵押物名称',
				     formEditorConfig:{
				             required:true,
				              colspan:3,
				           labelWidth:100,
				                width:'100%',
				            maxLength:120}},
			{field:'guarantorname', header:'担保人',
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'guarantor',
				         fillProperty:'name'},
				 	  	renderer:function(e){
							var row=e.record;
							return "<a style='text-decoration:underline;color:blue;' href='javascript:showcustinfo(\""+row.guarantor+"\")'>"+row.guarantorname+"</a>";
								}		         
			
			},
			{field:'guarantor', header:'担保人',visible:false,
				 	 formEditorConfig:{
				              newLine:true,
				                 type:'queryinput',
				             required:true,
				            textField:'name',
				           valueField:'value',
				         fieldVisible:true,
				              colspan:3,
				                width:"100%",
				               params:{cust_type:'cust_type.guarantor',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
			{field:'equipinvoice', header:'发票号',
				     formEditorConfig:{
							  newLine:true,
							maxLength:50}},
			{field:'guarantyvalue', header:'抵押物原值',
				     formEditorConfig:{
							    vtype:'float',
						   labelWidth:100,
						    maxLength:20}},
			{field:'equipguaranteetypename', header:'抵押方式', 
					 formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'equipguaranteetype',
				         fillProperty:'name'}},
			{field:'equipguaranteetype', header:'抵押方式', visible:false,
				 	 formEditorConfig:{
				              newLine:true,
				                 type:'combobox',
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				         fieldVisible:true,
				              colspan:3,
				                width:"100%",
				               params:{pid:'guarantee_type',xmlFileName:'combos/comboDict.xml'}}},
			{field:'presentvalue', header:'现值',
				     formEditorConfig:{
							  newLine:true,
							    vtype:'float',
							maxLength:20}},
			/* {field:'notaryflagname', header:'是否抵质押公证', 
								 formEditorConfig:{
							         fieldVisible:false,
							    fillFromFieldName:'notaryflag',
							         fillProperty:'name'}}, */
			/* {field:'notaryflag', header:'是否抵质押公证', visible:false,
							 	 formEditorConfig:{
							             
							                 type:'combobox',
							             required:true,
							            textField:'name',
							           valueField:'value',
							           allowInput:false,
							         fieldVisible:true,
							              colspan:3,
							                width:"100%",
							               params:{pid:'notary_flag',xmlFileName:'combos/comboDict.xml'}}}, */
			 {field:'notaryflag', header:'是否抵质押公证', visible:true,
					 formEditorConfig:{
				         showNullItem:"true", 
				         nullItemText:"",
				            emptyText:"",
				                 type:'combobox',
				           valueField:'text',
				            textField:'text',
				         fieldVisible:true,
				                 data:[{text:'是'},{text:'否'}]}},
			/* {field:'recordmech', header:'登记机关',
				     formEditorConfig:{
							  newLine:true,
							maxLength:120}}, */
			{field:'purchaselife', header:'购买年限',
				     formEditorConfig:{
				    	 newLine:true,
							}},
			{field:'cgenote', header:'备注',
					  formEditorConfig:{
				               newLine:true,
				                  type:'textarea',
			 	               colspan:3,
		                        height:70,
				                 width:"100%",
				             maxLength:300}}
		]
	});
});
function showcontractloadfile(id,type,isView){
	var urlFlag = getRootPath()+"/workflow/forms/Proj/proj_develop_list/proj_wind_common/contract_approval_file_list.bi?id="+id+"&type="+type+"&isView="+isView;
	mini.open({
        url: urlFlag,
        title: "抵押物文件信息", width: 800, height: 455,
        onload: function () {},
        ondestroy: function (action) {
        	if("savesuccess" == action){
        		window.location.reload();
        	}
        }
    });

	}
function showcustinfo(custid){
	var params = "id="+custid+"&isView=true";
		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
		var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
}
//标记删除
function markDeletion3(id){
	mini.confirm("确认删除吗？", "删除？", function(action){
	if(action=="ok"){
	   $.ajax({
		cache : false,
		async : false,
        type: "post",
        dataType: "JSON",
        url : getRootPath()+ "/acl/ContractMarkDelect.acl",
        data: {id:id,mark:"3"},
        success: function (data) {
        	if(data==1){
        		mini.get("contract_guarantee_equip").reload();
        		return;
        	}else{
        		mini.alert("删除失败！");
        		return;
        	}
        	
        	    
        }
    });
	}else{return;
	}
  })
}


</script>
<div id="id_table_contract_guarantee_equip" style="width:100%;height:100%;"></div>