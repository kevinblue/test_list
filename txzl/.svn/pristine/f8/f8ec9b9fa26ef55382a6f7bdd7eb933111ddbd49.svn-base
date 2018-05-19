<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<style>
.action{cursor: pointer;}
</style>
<script type="text/javascript">
	jQuery(function() {
		var showTools = true;
		if('${param.isView}' == 'true'){showTools = false};
		seajs.use(
						[ "js/apcomponent/aptable/aptable.js" ],
						function(ApTable) {
							new ApTable(
									{
										id : 'table_proj_doc',
										renderTo : "id_table_proj_doc",
										width:globalClientWidth,
										height:230,
										iconCls : 'icon-node',
										multiSelect : true,
										exportTitle : '项目批复文件',
										editFormPopupWindowWidth : 800,
										editFormPopupWindowHeight : 200,
										remoteOper : true,
										pageSize : 15,
										showPager : true,
										lazyLoad : false,
										loadMode : 'ajax',
										showToolbar: showTools,
										entityClassName : 'com.tenwa.leasing.entity.proj.ProjApprovalDoc',
										xmlFileName : '/eleasing/workflow/proj/proj_app_doc/proj_app_doc_info_list.xml',
										params:{projid:projid},
										tools : [ 'add', '-', 'edit', '-',
												'remove', '-', 'exportExcel' ],
										columns : [
												{
													type : 'indexcolumn'
												},
												{
													type : 'checkcolumn'
												},
												{
													field : 'id',
													header : '编号',
													headerAlign : 'center',
													width : 200,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false
													}
												},
												{field : 'projid',
													header : '项目ID',
													headerAlign : 'center',
													width : 100,
													allowSort : true,
													visible : false,
													formEditorConfig : {
														readOnly : true,
														fieldVisible : false,
														value : projid
													}
												},												
												{field:'docname',header:'文件名称',formEditorConfig:{width : 200,labelWidth:100,required:true}}, 
												{field:'docnum',header:'文件编号',formEditorConfig:{width : 200,labelWidth:100}},
												{field:'officialreply',header:'获批单位',formEditorConfig : {newLine:true,width : 200,labelWidth:100}},
												{field:'replydept',header:'批复单位',formEditorConfig :{newLine:false,width : 200,labelWidth:100}},
												{field:'replydate',header:'批复日期',formEditorConfig : {newLine:true,type:'date',width : 200,labelWidth:100,
													otherAttributes:'onvaluechanged="judgedate"'}},												
												{field:'expirydate',header:'有效期截止日',
													formEditorConfig : {width : 200,labelWidth:100,type:'date'}},
												{field:'replycontent',header:'批复内容',formEditorConfig : {newLine:true,type:'textarea',colspan:5,width : 520,labelWidth:100}},
												{field:'remarks',header:'备注',formEditorConfig : {newLine:true,type:'textarea',colspan:5,width : 520,labelWidth:100}},
												 {field:'',header:'操作',
											  		   formEditorConfig:{
						                                fieldVisible:false},
												     renderer:function(e){
													          var id = e.record.id;
												              return "<a href='javascript:void(0);' class='action' onclick='showanduploadfilereply(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
												               }}
												]


									});
						});
	});
	
	
	function judgedate(e){ 
			var start=mini.getbyName("replydate").getValue();
			mini.getbyName("expirydate").setMinDate(start);  			
		}
	function showanduploadfilereply(id,type){
			var urlFlag = getRootPath()+"/workflow/forms/Proj/proj_develop_list/proj_wind_common/proj_approval_file_list.bi?id="+id+"&type="+type;
			mini.open({
		        url: urlFlag,
		        title: "项目批复文件", width: 800, height: 455,
		        onload: function () {},
		        ondestroy: function (action) {
		        	if("savesuccess" == action){
		        		window.location.reload();
		        	}
		        }
		    });

	  	} 
</script>
<div >
		<div id="id_table_proj_doc"></div>
</div>

