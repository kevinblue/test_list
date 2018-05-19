<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<input  style="display:none;"	class="mini-textarea" id="id_json_csut_apply_list_str" name="json_csut_apply_list_str" value='${empty json_csut_apply_list_str ? "[]" : json_csut_apply_list_str }'></input>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if ('${param.isView}' == 'true') {
		showTools = false;
	}
	tenwa.createTable({
		id: 'csut_apply_list',
		renderTo: 'id_table_csut_apply_list',
		width:globalClientWidth,
		height: 600,
		lazyLoad: false,
		showPage: true,
//		editFormPopupWindowHeight:200,
		showToolbar: showTools,
		hiddenQueryArea : true,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		xmlFileName:"eleasing/jsp/law_implement/law_implemention_detail.xml",
		entityClassName:'com.tenwa.leasing.entity.lawmng.LawImplemention',
		remoteOper:true,
		tools: ['add', '-', 'edit', '-', 'remove'],
		
		
		
		/*beforeShowWindowCallBack:function(miniTable,miniForm, operType){
			  mini.getbyName("applyid").setValue('${requestScope["pid"]}');
 			  var queryinput=getMiniuiExtObject("csut_apply_list_editFormPopupWindow_form_custinfo");
			  queryinput.params['applydate']=applydate;
			  if(queryinput.initwindow==true){
				  queryinput.loadQueryInputAjaxData();
			  } 
			 //return true;
		 },*/
		 afterShowWindowCallBack:function(miniTable,miniForm, operType){
			  var lawApprovalid ='${param["lawApprovalid"]}'||'${requestScope["lawApproval"]}';
		   	  mini.getbyName("relatekey").setValue(lawApprovalid);
		   	  
		}, 
		params:{relatekey : '${param["lawApprovalid"]}'||'${requestScope["lawApproval"]}'},
		 
		columns: [
			{type: 'checkcolumn'},
			
			{field: 'id',visible: false,formEditorConfig: {fieldVisible:false}},
			
			{field: 'applicationexecutiondate',header:'申请执行日',visible:true,dateFormat:"yyyy-MM-dd",
						         formEditorConfig:{
							          newLine:true,
							             type:'date',
							       labelWidth:100,
							            width:200,
							           format:'yyyy-MM-dd'}},
			
			{field: 'executivejudge',header:'执行法官',visible:true,formEditorConfig:{}},
			
			{field: 'contactway',header:'联系方式',visible:true,formEditorConfig:{newLine:true}},
			
 			{field: 'executioncasenumber', header: '执行案号',headerAlign:'center',visible: true,
					formEditorConfig:{fieldVisible: true}},
					
			{field:'registedate',header:'登记日期',headerAlign:'center',visible: true,width:100,dateFormat:"yyyy-MM-dd",
				         formEditorConfig:{
						          newLine:true,
						             type:'date',
						         required:true,								
						       labelWidth:100,
						            width:200,
						           format:'yyyy-MM-dd'}},
			
			{field : 'itemdescription',header : '事项说明',visible:true,
				formEditorConfig:{
					newLine:true,
					type:'textarea',
					colspan:3,
					width:'100%',
					fieldVisible: true
				}},
			
			{field : 'relatekey',visible: false,header : 'relatekey',formEditorConfig:{readOnly:true,newLine:true}}
			
		]
	});
});
</script>
<div id="id_table_csut_apply_list" style="height: 100%;"></div>