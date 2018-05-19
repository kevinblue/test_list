<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
var projid="${requestScope['proj_info.id']}";
var showTools = true;
if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'change_reason_type',
				renderTo : "id_table_factoring_change_explain",
				width: '100%',
				height:365,
				lazyLoad: false,
				title:'方案变更事由',
				isClickLoad:false,
				showPager: false, 
				remoteOper : false,
				//entityClassName : "com.tenwa.leasing.entity.factoring.ChangeReasonType",
				multiSelect: true,
				showToolbar: showTools,
				params : {
					projid : projid
				}, 
				tools: ['add', '-', 'edit', '-','remove','-','exportExcel'],
				data: JsonUtil.decode('${empty json_change_reason_type_str ? "[]" : json_change_reason_type_str }'),
				columns:[ 
				    {
				    	type:'indexcolumn',width:10
				    },
				    {
				    	type:'checkcolumn',width:10
				    },
				   {field:'id',header:'id',visible:false},
				   {field:'projid',visible:false,formEditorConfig:{defaultValue:projid}},
	               {field:'changereasonname', header:'方案变更事由', formEditorConfig:{fieldVisible:false,fillFromFieldName:'changereason',fillProperty:'name'}},
				   {field:'changereason',visible:false,header:'方案变更事由',width:190,
				   		         formEditorConfig:{
				   			     newLine:true,
				   			     type:'combobox',
							     required:false,
							     labelWidth:90,
							     width:'100%',
							     multiSelect:false,
							     textField:'name',
							     valueField:'value',
							     showNullItem:"true",
							     nullItemText:"",
							     fieldVisible:true,
							     params:{pid:'projChangeReasonType',xmlFileName:'combos/comboDict.xml'}}},
													               
	               {
						field : 'memo',
						header : '备注',
						visible : true,
						formEditorConfig : {
							newLine : true,
							colspan : 5,
							width : "100%",
							type : 'textarea'
						}
					} 
				]
			});
		});
	});
</script>

	<div id="mini_test_form">
		<div id="factoring_change_explain" title="项目变更说明">
			<table cellspacing="0" cellpadding="0" style="width:100%;border: 1px solid #99CCFF;" class="fillTable">
				<tr class="tr-even">
					<td class="td-content-title" width="12%">原保理业务方案批复号：</td>
					<td class="td-content" width="38%">
						<input name="factoring_change_explain.oldapprovalno" id ="old_approvalno" label="原保理业务方案批复号" onvaluechanged="changeNext" class="mini-textbox"  
						value="${requestScope['factoring_change_explain.oldapprovalno'] }" allowInput="true" required="true"/>
	               </td>
					<td class="td-content-title">变更后业务方案批复号：</td>
					<td class="td-content">
						<input name="factoring_change_explain.newapprovalno" id ="new_approvalno" label="变更后业务方案批复号" class="mini-textbox"  
						value="${requestScope['factoring_change_explain.newapprovalno'] }" allowInput="true"  required="true"/></td>
				</tr>
				<tr id="id_medical_tr" class="tr-odd">
					<td class="td-content-title" width="12%">原业务方案审批机构：</td>
					<td class="td-content" width="38%">
						<input name="factoring_change_explain.oldexamineagency" id ="old_examineagency" label="原业务方案审批机构" class="mini-textbox" 
						value="${requestScope['factoring_change_explain.oldexamineagency'] }" allowInput="true" required="true"  />
	               </td>
	               <td class="td-content-title" width="12%">变更后方案审批机构：</td>
				   <td  class="td-content" width="38%">
				   	<input name="factoring_change_explain.newexamineagency" id ="new_examineagency" label="变更后方案审批机构" class="mini-textbox" 
				   	value="${requestScope['factoring_change_explain.newexamineagency'] }" allowInput="true" required="true" /></td>
				</tr>
		</table>
		</div>
		
	</div>
	    <div>
	    <div id="id_table_factoring_change_explain"></div>
	    </div>
<script language="javascript">
if('${param.isView}' == 'true'||isViewHistoryTask==true){miniui_ext.disableFormFields("factoring_change_explain");}
function changeNext(){
	var old_approvalno=mini.get("old_approvalno").getValue();
	var new_approvalno=mini.get("new_approvalno");
	/* var reg="^(.)+(-BG[0-9]{2})$"; */
	if(old_approvalno!=null&&old_approvalno!=""){
		if(old_approvalno.match("^(.)+(-BG[0-9]{1,})$"))
		{
			var topstr=old_approvalno.substring(0,old_approvalno.lastIndexOf("G")+1);
			var bottomstr=old_approvalno.substring(old_approvalno.lastIndexOf("G")+1);
			//导入格式化数字js
			/* var format=new DecimalFormat(); 
			format.applyPattern("00");
			var newnum=Number(bottomstr)+1;
			new_approvalno.setValue(topstr+format.format(newnum)); */
			var newnum=Number(bottomstr)+1;
			var bottom=(Array(2).join(0) + newnum).slice(-2);
			new_approvalno.setValue(topstr+bottom);
		}
		else{
			new_approvalno.setValue(old_approvalno+"-BG01");
		}
		
	}
}
</script>