<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var projid = "${requestScope['contract_info.projid']}";
var contractid = "${requestScope['contract_info.id']}";
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				height: 260,
				showPager : false,
				lazyLoad : false,
				title:'合同编号设置',
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.proj.ContractNumberSettingTmp",
				params:{
					contractid:contractid,
					docid:flowUnid
				},
				multiSelect: false,
				xmlFileName : '/eleasing/workflow/proj/proj_contract/contract_change_number_setting_tmp.xml',
				
				queryButtonColSpan : 6,
				queryButtonNewLine : true,
				columns: [
                 { type : 'checkcolumn'},
					{ header:'序号',type : 'indexcolumn'},
					{field:'id',
						header:'主键',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
							
						}
					},
					{field:'contractname',
						header:'合同名称',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
						}
					},
					{field:'contractnumber',
						header:'合同编号',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
						}
					},
					
					{field:'oldcontnum',
						header:'修改前合同编号',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
						}
					},
					
					{field:'docid',
						header:'流程ID',
						visible: false,
						formEditorConfig:{
							value:flowUnid,
							fieldVisible: false,
						}
					},
					{field:'projinfo',
						header:'项目ID',
						visible: false,
						formEditorConfig:{
							value:projid,
							fieldVisible: false,
						}
					},
					
					{field:'contractsubject',
						header:'合同主体',
						visible: true,
						formEditorConfig:{
							 newLine:true,
							fieldVisible: true,
							valueField:'value'
						}
					},
					
					{
						field:'contracttypename',
						visible:true,
						header:'合同类型',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             }
					},
					{
						field:'contracttype',
						visible:false,
						header:'合同类型',
		   		         formEditorConfig:{
		   			             
					             required:true,
					          multiSelect:false, 
					            textField:'name',
					           valueField:'value',
					           showNullItem:"true",
					           nullItemText:"",
					         fieldVisible:true,
					     }},
					    
					{field:'custid',
						header:'客户信息',
						visible: false,
						formEditorConfig:{
							 newLine:true,
							fieldVisible: true,
							 textField:'name',
					          valueField:'value',
							
						}
					}, {
						field : 'mark',
						header : '备注',
						headerAlign : 'center',
						formEditorConfig : {
							newLine : true,
							width : 495,
							colspan : 6,
							height : 55,
							type : 'textarea'
						}
					} 
				] 
			});
		});
	});
</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-contractect-word-list">
		<td class="td-content" colspan="4">
			<jsp:include page="contract_list_info.jsp"></jsp:include><%--合同清单 --%>
		</td>
	</tr>
</table>