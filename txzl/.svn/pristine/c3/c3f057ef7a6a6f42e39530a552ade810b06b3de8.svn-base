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
		lazyLoad: false,
		showToolbar: showTools,
		hiddenQueryArea : false,
		pageSize: 20,
		showPager : true,
		remoteOper : true,
		entityClassName : 'com.tenwa.leasing.entity.asset.AssetMngDetail',
		tools: ['add', '-', 'edit', '-', 'remove'],
		params:{
				applyid: '${param.opertype}'=="edit" ? '${param.applyid}' : "${requestScope['assetMngApply'] }"
			},
		//data:mini.decode('${empty json_csut_apply_list_str ? "[]" :json_csut_apply_list_str }'),
		xmlFileName : '/eleasing/jsp/asset/assets_mng_detail_info.xml',
		validateForm:function(miniTable, miniForm, editFormItemOperFlag){
			return true;
		},
		beforeShowWindowCallBack:function(miniTable,miniForm, oper){
			if('${param.opertype}'=="edit"){
				var applyid = '${param.applyid}';
				mini.getbyName("applyid").setValue(applyid);
			} else {
				var assetmngapply = mini.getbyName("assetMngApply").getValue();
				mini.getbyName("applyid").setValue(assetmngapply);
			}
			return true;
		 },
		columns: [
			{type: 'checkcolumn'},
			{field: 'id',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'applyid',visible: false,formEditorConfig: {fieldVisible:false }},
			{field: 'custinfo',header: '客户名称',visible: false,formEditorConfig: {
					type : 'queryinput',
					labelWidth:100,
					multiSelect : false,
					fieldVisible: true,
					required: true,
					readOnly: false,
					valueField : 'id',
					textField : 'name',
					onSelect:function($me, inputObj, rowData){
						mini.getbyName("custinfoname").setValue(rowData.name);
						mini.getbyName("code").setValue(rowData.code);
					},
					params : {
						xmlFileName : '/eleasing/jsp/other/get_cust_info_open_list.xml',
						userid:'${sessionScope.loginUser.id}',
						//applydate:applydate /*关联省份内容*/
					}
			}},
			{field: 'custinfoname',header : '客户名称',visible: true,formEditorConfig: {
				fillFromFieldName : 'cust',
				fillProperty : 'name',
				fieldVisible: false,
				readOnly: false
			},queryConfig:{}},
			{field: 'code',
				header : '身份证/组织机构代码',visible:true,
				formEditorConfig: {
					labelWidth:130,
					fillFromFieldName : 'cust',
					fillProperty : 'code',
					readOnly:true
				},queryConfig:{}
			},
			{field: 'xundate', header: '巡视时间',visible: true,
				formEditorConfig:{
					type:'date',
					colspan: 3,
					newLine:true,
				 fieldVisible: true
			}},
			{field: 'xunresult', header: '巡视原因',
				formEditorConfig : {
					type:'textarea',
					width:'100%',
					colspan: 3,
					newLine:true,
				 fieldVisible: true
				}
			},
			{field: 'xunthing', header: '巡视重点',
				formEditorConfig : {
					type:'textarea',
					newLine:true,
					colspan: 3,
					width:'100%',
				 fieldVisible: true
				}
			}
		]
	});
});
</script>
<div id="id_table_csut_apply_list" style="height: 100%;"></div>
