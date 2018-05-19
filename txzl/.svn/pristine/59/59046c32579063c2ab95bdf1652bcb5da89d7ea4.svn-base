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
		editFormPopupWindowHeight:350,
		showToolbar: showTools,
		hiddenQueryArea : true,
		pageSize: 20,
		showPager : true,//分页按钮是否显示
		remoteOper:true,
		editRemoteOperUrl:getRootPath()+"/acl/updFiveCategory.acl",
		xmlFileName:"eleasing/jsp/other/get_apply_info_open_list.xml",
		entityClassName:'com.tenwa.leasing.entity.fivecategoryapply.FiveCategoryApplyDetail',
		params:{
		//	applyid:'${requestScope["pid"]}',
			userid:'${sessionScope.login_userid}'},
		remoteOper:true,
		tools: [
		        'edit'
		        ],
		//点击按钮之后弹出的修改菜单的确认按钮的操作
		
		validateForm:function(miniTable, miniForm, editFormItemOperFlag){
			var datas=mini.get("csut_apply_list").getData();
			var input=mini.getbyName("contractnumber").getValue();
			for(var i=0;i<datas.length;i++){
				if(input==datas[i].custinfo){
					mini.alert("客户不能重复添加！");
					return false;
				}
			}
			return true;
		},
		
		afterShowWindowCallBack:function(miniTable,miniForm, operType){
			  //alert( '${requestScope["pid"]}' );
			  var applyid ='${param["applyid"]}'||'${requestScope["pid"]}';
		   	  mini.getbyName("applyid").setValue(applyid);
		
		},
		params:{applyno:'${param["applyid"]}'||'${requestScope["pid"]}'},
		 
		columns: [
			{type: 'checkcolumn'},
			{field: 'id',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'contractid',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'custid',visible: false,formEditorConfig: {fieldVisible:false}},
			{field: 'applyid', visible: false, header : 'applyno'},
			
/* 			{field: 'contractnumbername', header: '业务合同编号',headerAlign:'center',visible: false,
				formEditorConfig:{fieldVisible: false}},
 */		   
		 	{field: 'contractno', header: '合同编号',headerAlign:'center', visible: true,
			  			  queryConfig:{  
                           labelWidth:100,
                           
	   			            width:200},
	   			         formEditorConfig:{
	   			        	readOnly:true
	   			         }
				     /* formEditorConfig:{
				    	newLine:true,
		                width: 200,
		                 type:'queryinput',
		             required: true,
		            textField: 'contractid',
		           valueField: 'contractno',
		           allowInput: false,
		         fieldVisible: true,
		             onSelect:function($me, queryInput, rowData){
		            		mini.getbyName("contractid").setValue(rowData.cid);
		            		
		            		mini.getbyName("contractnumber").setValue(rowData.contractnumber);
		            		mini.getbyName("cardno").setValue(rowData.cardno);
		            		mini.getbyName("projectname").setValue(rowData.projectname);
		            		mini.getbyName("projdept").setValue(rowData.projdept);
		            		mini.getbyName("projmanage").setValue(rowData.projmanage);
		            		mini.getbyName("insideindustryname").setValue(rowData.insideindustryname);
		            		mini.getbyName("custname").setValue(rowData.custname);
		            		mini.getbyName("leaseform").setValue(rowData.leaseform);
		            		mini.getbyName("provincename").setValue(rowData.provincename);
		            		mini.getbyName("startdate").setValue(rowData.startdate);
		            		mini.getbyName("incomenumber").setValue(rowData.incomenumber);
		            		mini.getbyName("currentoverage").setValue(rowData.currentoverage);
		            		mini.getbyName("curinterestoverage").setValue(rowData.curinterestoverage);
		            		mini.getbyName("firstrent").setValue(rowData.firstrent);
		            		mini.getbyName("cautionmoney").setValue(rowData.cautionmoney);
		            		mini.getbyName("out_list").setValue(rowData.out_list);
		            		mini.getbyName("out_date").setValue(rowData.out_date);
		            		mini.getbyName("custid").setValue(rowData.custid);
		            		
	                    },
	                    params : {
							xmlFileName : '/eleasing/jsp/other/docfive_category_contractId.xml',
							userid:'${sessionScope.loginUser.id}',
							applydate:applydate
						}
					 } */
	   			},
			{field: 'contractnumber', header: '业务合同编号',headerAlign:'center',visible: true,
				formEditorConfig:{fieldVisible: true,readOnly:true}},
			
			
			{field: 'cardno',header : '身份证/组织机构代码',visible:true,formEditorConfig: {readOnly:true,newLine:true}},
			
			
			{field : 'projectname',header : '项目名称',formEditorConfig:{readOnly:true}}, 
			{field : 'projdept',header : '部门名称',formEditorConfig:{newLine:true,readOnly:true}},
			
			
			{field : 'insideindustryname',header : '内部行业',formEditorConfig:{readOnly:true}}, 
			{field : 'custname',header : '客户名称',formEditorConfig:{newLine:true,readOnly:true}},
			
			{field : 'leaseform',header : '租赁形式',formEditorConfig:{readOnly:true}},
			{field : 'provincename',header : '省份',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'projmanage',header : '项目经理',formEditorConfig:{readOnly:true}},
			{field : 'startdate',header : '起租日',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'incomenumber',header : '还租频次',formEditorConfig:{readOnly:true}},
			{field : 'currentoverage',header : '未偿租金金额',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'curinterestoverage',header : '未偿本金金额',formEditorConfig:{readOnly:true}},
			{field : 'firstrent',header : '每期租金金额',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'cautionmoney',header : '保证金金额',formEditorConfig:{readOnly:true}},
			{field : 'out_list',header : '逾期次数',formEditorConfig:{newLine:true,readOnly:true}},
			{field : 'out_date',header : '逾期天数',formEditorConfig:{readOnly:true}},
			
			{field : 'latecause',header : '逾期原因',visible:true,
				formEditorConfig:{
					newLine:true,
					type:'textarea',
					colspan:3,
					width:'100%',
					fieldVisible: true
				}},
			{field : 'importantmatters',header : '重大事项',visible:true,
				formEditorConfig:{
					type:'textarea',
					newLine:true,
					colspan:3,
					width:'100%',
					fieldVisible: true
				}},
			{field : 'custvisit',header : '客户巡视',visible:true,
					formEditorConfig:{
						type:'textarea',
						newLine:true,
						colspan:3,
						width:'100%',
						fieldVisible: true
					}},
			{field : 'accidentlitigationsituation',header : '出险及涉诉情况',formEditorConfig:{newLine:true}},
			{field : 'lastclassificationresult',header : '前次资产分类结果',visible:true,
				formEditorConfig:{
					readOnly:true
				}},
			{field: 'classificationresultname', header: '本次分类结果', visible: true,
	            	  formEditorConfig:{
				   		fieldVisible: false,
				   		fillFromFieldName : 'classificationresult',
						fillProperty : 'name'
					}},
			{field : 'classificationresult',header : '本次分类结果',visible: false,
			   		formEditorConfig : {
			   			type : 'combobox',
						textField: 'name',
						newLine:true,
						required: true,
						fieldVisible: true,
						params:{pid: 'five_class',xmlFileName: '/combos/comboDict.xml'},
		                 textField: 'name',
		                valueField: 'value'
			   		}},
			   		
			{field : 'adjustmentreason',header : '调整原因',visible:true,
					formEditorConfig:{
						type:'textarea',
						colspan:3,
						width:'100%',
						fieldVisible: true,
						newLine:true
					}}
		]
	});
});
</script>
<div id="id_table_csut_apply_list" style="height: 100%;"></div>
