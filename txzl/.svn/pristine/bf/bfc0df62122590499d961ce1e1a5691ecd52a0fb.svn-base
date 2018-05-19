<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input  style="display:none;"	class="mini-textarea" id="id_json_csut_apply_list_str" name="json_csut_apply_list_str" value='${empty json_csut_apply_list_str ? "[]" : json_csut_apply_list_str }'></input>
<script type="text/javascript">
var stp ="";
if("${requestScope['currentWorkFlowName']}" !=""){
	stp = "${requestScope['currentWorkFlowName']}";
}
var localEnabled = [ {id : '',text : ''}, 
                     {id : '承租人',text : '承租人'},
                     {id : '担保人',text : '担保人'} ];
jQuery(function(){
	var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}
	tenwa.createTable({
		id: 'csut_apply_list',
		renderTo: 'id_table_csut_apply_list',
		width: '100%',
		height: 355,
		allowCellWrap: true,
		lazyLoad: false,
		showToolbar: showTools,
		hiddenQueryArea : false,
		loadMode : 'ajax',
		pageSize: 50,
		showPager : true,
		tools: ['edit','-','exportExcel' ],
		remoteOper:true,
		editRemoteOperUrl:getRootPath()+"/acl/updateAssetNetMonitorApplyDetail.acl",
	//	entityClassName:'com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail',
	 	 params:{
				applyid:  '${requestScope["applyid"]}'
			},  
		xmlFileName : '/eleasing/jsp/other/get_network_cust_info1.xml', 
			
		
	
		beforeShowWindowCallBack:function(miniTable,miniForm, operType){
			mini.getbyName("applyid").setValue('${requestScope["applyid"]}');
			return true;
		},
		/* validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
			 $.ajax({
				url:getRootPath()+"/acl/updateAssetNetMonitorApplyDetail.acl",
				type:'post',
				success: function (e){
						 
				}
			}) 
			return true;
		},  */
		 afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
			 //mini.getbyName("applyid").setValue('${requestScope["applyid"]}');
			 /*var queryinput=getMiniuiExtObject("csut_apply_list_editFormPopupWindow_form_custinfo");
			  console.info(queryinput);
			  queryinput.params['applydate']=applydate;
			  if(queryinput.initwindow==true){
				  queryinput.loadQueryInputAjaxData();
			  }*/
			  if(stp!=""){
				  mini.getbyName("baidu").disable();
				  mini.getbyName("executionnet").disable();
				  mini.getbyName("judgmentnet").disable();
				  mini.getbyName("negativecontrol").enable();
			  }
			 return true;
		 },
		columns: [
            {type : 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id',header: 'ID',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'custinfo',header: '客户名称id',visible: false,formEditorConfig: {
					type : 'queryinput',
					labelWidth:100,
					multiSelect : false,
					fieldVisible: true,
					required: true,
					readOnly:true,
					valueField : 'id',
					textField : 'name',
					onSelect:function($me, inputObj, rowData){
						mini.getbyName("custinfoname").setValue(rowData.name);
						mini.getbyName("code").setValue(rowData.code);
					},
					params : {
						xmlFileName : '/eleasing/jsp/other/get_cust_info_open_list.xml',
						userid:'${sessionScope.loginUser.id}',
						//applydate:applydate
					}
			}},
			{field: 'custinfoname',header : '客户名称',visible: true,width:70,formEditorConfig: {
				fillFromFieldName : 'cust',
				fillProperty : 'name',
				fieldVisible: false,
				readOnly:true
			},queryConfig:{}},
			{field: 'code',
				header : '身份证/组织机构代码',visible:true,width:30,
				formEditorConfig: {
					labelWidth:130,
					fillFromFieldName : 'cust',
					fillProperty : 'code',
					readOnly:true
				},queryConfig:{}
			},

			{field: 'applyid',header: 'applyid',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'custtype',
				header : '客户类型',visible:true,width:30,
				formEditorConfig: {
					data : localEnabled,
					type : 'combobox',
					newLine:true,
					readOnly:true
				}
			},
			{field: 'baidu', header: '百度',visible: true,
				formEditorConfig:{
					type:'textarea',
					colspan: 3,
					width:'100%',
					newLine:true,
				 fieldVisible: true
			}},
			{field: 'executionnet', header: '被执行人网',
				formEditorConfig : {
					type:'textarea',
					width:'100%',
					colspan: 3,
					newLine:true,
				 fieldVisible: true
				}
			},
			{field: 'judgmentnet', header: '裁判文书网',
				formEditorConfig : {
					type:'textarea',
					newLine:true,
					colspan: 3,
					width:'100%',
				 fieldVisible: true
				}
			},
			{field: 'negativecontrol',header: '负面事件管控措施',
				formEditorConfig : {
					labelWidth:110,
					type:'textarea',
					readOnly:true,
					colspan: 3,
					width:'100%',
					
					newLine:true,
				 fieldVisible: true
				}
			}
		]
	});
});

</script>
<div id="id_table_csut_apply_list" style="height: 100%;"></div>
