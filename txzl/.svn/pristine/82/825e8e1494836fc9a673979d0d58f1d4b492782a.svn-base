<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>



<script type="text/javascript">
jQuery(function(){
	var conid="${requestScope['contract_info.id']}";
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		 params:{
			 docid:flowUnid,
			 conid:conid
				
			},
		id: "contract_guarantee_method",
		renderTo: "id_table_guarantee_method",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		remoteOper : true,
		entityClassName : "com.tenwa.leasing.entity.contract.ContractGuaranteeMethodTmp",
		showPager: false,
		showToolbar: showTools,
		tools: ['add', '-', 'edit', '-', {html:'删除',
			  plain:true,
			  iconCls:'icon-add',
			  handler:function(miniTable, buttonText) {
				var row = miniTable.getSelected();
				if(row){
					var a=2;
					markDeletion2(row.id,a);
				}else{
						alert("未选中行");			 
				}
	         } 
         }],
		//data: JsonUtil.decode('${empty json_contract_guarantee_method_str ? "[]" : json_contract_guarantee_method_str }'),
		xmlFileName : '/eleasing/workflow/contract/contract_change/contract_guarantee_method_tmp.xml', 
		columns: [
			{type:'indexcolumn'},
			{type:'checkcolumn'},
			{field:'id', header:'id', visible:false},
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
			
			{field:'assurorname', header:'担保单位', visible:true,
				     formEditorConfig:{
					     fieldVisible:false},
					     renderer : function(e){
					    	 var assurorname = e.record.assurorname;
					    	 var assurorId = e.record.assuror;
					    	 var assurorcustclass=e.record.assurorcustclass;
					    	 return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + assurorId +"\",\""+assurorcustclass+ "\")'>" + assurorname + "</a>";}	     
			
			},
			{field:'assuror', header:'担保单位', visible:false,
					 formEditorConfig:{
					          newLine:true,
					            width:"100%",
					             type:'queryinput',
					         required:true,
					        textField:'name',
					       valueField:'value',
					       allowInput:false,
					     fieldVisible:true,
					            width:'100%',
					          colspan:3,
					           params:{cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},
			{field:'assurorcustclass', header:'担保单位客户类型',visible:false,
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuror',
				         fillProperty:'custclass'}},
			{field:'assuremethodname', header:'担保类型',
				 	 formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assuremethod',
				         fillProperty:'name'}},
			{field:'assuremethod', header:'担保类型', visible:false,
				 	 formEditorConfig:{
				                width:"100%",
				              newLine:true,
				                 type:'combobox',
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				         fieldVisible:true,
				               params:{pid:'assure_method',xmlFileName:'combos/comboDict.xml'}}},
			{field:'assurerelationname', header:'关系', 
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'assurerelation',
				         fillProperty:'name'}},
			{field:'assurerelation', header:'关系', visible:false,
				 	 formEditorConfig:{
				              newLine:false,
				                width:"100%",
				                 type:'combobox',
				             required:true,
				            textField:'name',
				           valueField:'value',
				           allowInput:false,
				          fieldVisible:true,
				            labelWidth:100,
				                params:{pid:'relation',xmlFileName:'combos/comboDict.xml'}}},
			{field:'cgmnote', header:'备注',formEditorConfig:{newLine:true,width:'100%',colspan:3,type:'textarea'}}
		]
	});
});

//标记删除
function markDeletion2(id){
	mini.confirm("确认删除吗？", "删除？", function(action){
	if(action=="ok"){
	   $.ajax({
	    cache : false,
		async : false, 
        type: "post",
        dataType: "JSON", 
        data: {id:id,mark:"2"},
        url : getRootPath()+ "/acl/ContractMarkDelect.acl",
        success: function (data) {
        	if(data==1){
        		mini.get("contract_guarantee_method").reload();
        		return;
        	}else{
        		mini.alert("删除失败！");
        		return;
        	}
        },
    });
	}else{
		return;
	}
  })
}



</script>
<div id="id_table_guarantee_method" style="width:100%;height:100%;"></div>