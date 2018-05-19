<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	var conid = mini.getbyName("contract_info.contractid").getValue();
	var projid = "${requestScope['contract_info.projid']}";
	var showTools = true;
	var isView='${param.isView}';
	if(isView == 'true' || isViewHistoryTask == true){showTools = false;};
	tenwa.createTable({
		id: "contract_guarantee_equip",
		renderTo: "id_table_contract_guarantee_equip",
		width: globalClientWidth - 30,
		height: 365,
		lazyLoad: false,
		title: "",
		remoteOper : true,
		entityClassName : "com.tenwa.leasing.entity.proj.ProjGuaranteeEquipTmp",
		showPager: false,
		showToolbar: showTools,
		params:{
			
			projid:projid,
			docid:flowUnid
		},
		tools: ['add', '-', 'edit', '-', 'remove'],
		//data: JsonUtil.decode('${empty json_contract_guarantee_equip_str ? "[]" : json_contract_guarantee_equip_str }'),
		xmlFileName : '/eleasing/workflow/proj/proj_contract/proj_guarantee_equip_tmp.xml',
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			
			{field:'contractid',
				header:'合同编号',
				visible: false,
				formEditorConfig:{
					fieldVisible: false,
					value:conid
				}
			},
			
			{field:'projinfo',
				header:'项目id',
				visible: false,
				formEditorConfig:{
					fieldVisible:false,
					value:projid
				}
			},
			
			{field:'docid',
				header:'流程id',
				visible: false,
				formEditorConfig:{
					fieldVisible: false,
					value:flowUnid
				}
			},
			{field:'',header:'操作',
		  		   formEditorConfig:{
                 fieldVisible:false},
			     renderer:function(e){
				          var id = e.record.id;
			              return "<a href='javascript:void(0);' class='action' onclick='showcontractloadfile(\"" + id + "\",\"one\",\""+isView+"\")'>查看/上传资料 </a>";
			               }},
			{field:'guarantorname', header:'抵押人',
				     formEditorConfig:{
				         fieldVisible:false,
				    fillFromFieldName:'guarantor',
				         fillProperty:'name'},
				 	  	renderer:function(e){
							var row=e.record;
							return "<a style='text-decoration:underline;color:blue;' href='javascript:showcustinfo(\""+row.guarantor+"\")'>"+row.guarantorname+"</a>";
								}		         
			
			},
			{field:'guarantor', header:'抵押人',visible:false,
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
			{field: 'equipname', header: '抵押物名称',
				     formEditorConfig:{
				    	      newLine:true,
				             required:true,
				              colspan:3,
				           labelWidth:100,
				                width:'100%',
				            maxLength:120}},
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
			{field:'guarantyvalue', header:'抵押物原值',dataType:"currency",
				     formEditorConfig:{
				    	 	  newLine:true,
							    vtype:'float',
						   labelWidth:100,
						    maxLength:20}},
			{field:'presentvalue', header:'抵押物现值',dataType:"currency",
				     formEditorConfig:{
							  newLine:false,
							    vtype:'float',
							maxLength:20}},
			{field:'notaryflag', header:'是否抵质押公证', visible:true,
					 formEditorConfig:{
						      newLine:true,
				         showNullItem:"true", 
				         nullItemText:"",
				            emptyText:"",
				                 type:'combobox',
				           valueField:'text',
				            textField:'text',
				         fieldVisible:true,
				                 data:[{text:'是'},{text:'否'}]}},
			{field:'equipinvoice', header:'发票号',
				     formEditorConfig:{
							  newLine:false,
							maxLength:50}},
			{field:'recordmech', header:'登记机关',
				     formEditorConfig:{
							  newLine:true,
							maxLength:120}},
			{field:'purchaselife', header:'购买年限',
				     formEditorConfig:{
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

</script>
<div id="id_table_contract_guarantee_equip" style="width:100%;height:100%;"></div>