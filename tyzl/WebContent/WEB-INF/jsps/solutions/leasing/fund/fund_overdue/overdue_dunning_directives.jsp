<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
//主管领导指示
jQuery(function() {
	var directivesData;
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id3',
			width:globalClientWidth,
			height:screenHeight - topHeight,
			iconCls:'icon-node',
			renderTo:'content_table_id3',
			editFormPopupWindowWidth:700,
			afterShowWindowCallBack: function(miniTable,miniForm,operFlag){
				if("add" == operFlag){
					if(directivesData){
						miniForm.setData(directivesData);
					}else{
						$.ajax({
					        url: urlPrefix+"/eleasing/jsp/fund/fund_overdue/get_cust_by_contractid.xml",
					        type: "post",
					        cache: false, 
					        async:false,
					        data :{"contractid":contractid},
					        success: function (text) {
					        	var result = mini.decode(text);
					            var data = result.datas[0];
					            var formData = miniForm.getData();
					            $.extend(formData,mini.decode(data));
					            miniForm.setData(formData);
					            directivesData = formData;
					        }
					    });
					}
				}
			},
			remoteOper:true,
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueDunningDirectives',
			pageSize:10,
			showPager:true,
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/fund/fund_overdue/overdue_dunning_directives.xml',
			params:{contractid :contractid},
			tools:[ 'add', '-', 'edit', '-', 'remove'],
			columns:[
					 {type:'indexcolumn'},
			         {type:'checkcolumn'} , 
			         {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,
				               formEditorConfig:{
					                   readOnly:true,
					               fieldVisible:false}},
					  {field:'custid',header:'ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				               formEditorConfig:{
					                   readOnly:true,
					               fieldVisible:false}}, 
					  {field:'contractid',header:'合同主键ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				               formEditorConfig:{
					                   readOnly:true,
					               fieldVisible:false,
					                      value:contractid}},
			          {field:'custname',header:'客户名称',headerAlign:'center',width:100,allowSort:true,
				               formEditorConfig:{
					                   readonly:true,
					                       type:'text',
					               fieldVisible:true,
					                      width:200,
					                 labelWidth:100}},
					   {field:'directiveperson',header:'ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				               formEditorConfig:{
					                   readOnly:true,
					               fieldVisible:false,
					                      value:"<mini:user valuetype='id'/>"}},
					    {field:'directivepersonname',header:'指示人',headerAlign:'center',width:100,allowSort:true,
				               formEditorConfig:{
					                   readonly:true,
					                       type:'text',
					               fieldVisible:true,
				 	                      width:200,
					                 labelWidth:100,
					                     value:"<mini:user/>"}},
					    {field:'directivedate',header:'指示日期',headerAlign:'center',width:100,dateFormat:"yyyy-MM-dd",
			                	formEditorConfig:{
					                     colspan:3,
					                     newLine:true,
					                        type:'date',
					                       width:200,
					                  labelWidth:100,
					                  allowInput:"true",
					                      format:'yyyy-MM-dd',
					                       value:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
					                defaultValue:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
					                    readonly:true}},
					     {field:'directiveinfo',header:'指示内容',headerAlign:'center',width:100,allowSort:true,
				                formEditorConfig:{
					                     colspan:3,
				 	                     newLine:true,
					                        type:'textarea',
					                fieldVisible:true,
					                       width:"100%",
					                       height:300,
					                  labelWidth:100}
			}]
		});
	});
});
</script>
<div id='content_table_id3'></div>