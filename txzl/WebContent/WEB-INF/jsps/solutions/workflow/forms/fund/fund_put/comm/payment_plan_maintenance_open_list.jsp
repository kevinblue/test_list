<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
<%@include file="/base/mini.jsp"%>
 <script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">		
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
  		jQuery(function() {	
  			seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) { 								
  				new ApTable({ 					
  					id: "payment_maintenance",
  					renderTo: "id_table_payment_maintenance",
  					width: currentClientWidth,
  					height: currentClientHeight,
  				    afterShowWindowCallBack:getQueryInputValue,
  					validateForm:sendSaveAjax,
  					lazyLoad: false,
  					title: "付款计划维护",
  					showPager: true,
  					remoteOper : true,
  					pageSize: 20,
  					tools:[ 
  							{
  						     html:'付款信息维护',
  						    plain:true,
  						  iconCls:'icon-edit', 
  						  handler:function(miniTable, buttonText) { 
  							var row = miniTable.getSelected();
  							if(row){
  								var multiform = new mini.Form("multiform");
  								var multieditWindow = mini.get("multieditWindow");
  								multiform.clear();
								mini.get("contract_number").setValue(row.contractnumber);
								mini.get("payment_id").setValue(row.paymentid);
								mini.get("planmoney").setValue(row.planmoney);
								mini.get("plantime").setValue(row.plandate);
								var contractid = row.contractid;
								tenwa.createQueryInput({
									id:'id_contractlist',
									label:'采购合同',
									windowWidth: 450,
									windowHeight: 700,
									textField: 'name',
									valueField: 'value',
									params: {
										contractid: contractid,
										    xmlFileName:'/eleasing/workflow/fund/fundcomm/purchase_contract_list.xml'
									}
								});
  								multieditWindow.show();
  							}else{
  								mini.alert("请选要维护的数据！");
  							}
  						}
  					}],
  					queryButtonColSpan: 6,
  					queryButtonNewLine:true,
  					xmlFileName: '/eleasing/workflow/fund/fundcomm/open_payment_fund_plan_list.xml',   
  					columns:[ 
  					       {type : 'indexcolumn'},	
  							{type: 'checkcolumn'},
  							{field: 'id', header: 'id', visible: false},
  							{field: 'contractid', header: '合同id',queryConfig : {},formEditorConfig:{visible:false,readOnly : true},visible: false},
  							{field: 'projname', header: '项目名称',queryConfig : {},formEditorConfig:{visible:false,readOnly : true},visible: true},
  							{field: 'contractnumber', header: '合同编号',queryConfig : {},formEditorConfig:{visible:false,readOnly : true},visible: true},
  							{field: 'paymentid', header: '付款编号',formEditorConfig:{readOnly : true,required: true}},
  							{field: 'feetype', header: '费用类型',queryConfig : {},formEditorConfig:{newLine:true,readOnly : true}, visible: false},
  							{field: 'planmoney', header: '计划金额',summary : true,dataType : "currency",formEditorConfig:{readOnly : true}},
  							{field: 'overmoney', header: '未付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly : true}},
  							{field: 'factmoney', header: '已付款金额',summary : true,dataType : "currency",formEditorConfig:{readOnly : true}}, 
  							{field: 'plandate', header: '计划日期', visible: false,formEditorConfig:{readOnly : true}},
  							{field: 'paycustname', header: '支付对象',formEditorConfig:{newLine:true,readOnly : true}},
  							{field: 'checkid', header: '验证id',formEditorConfig:{newLine:true,readOnly : true},visible: false},
  							{field: 'purchasecontractname', header: '采购合同',visible:true,
  				   		         formEditorConfig:{
  							         fieldVisible:false,
  							             }
  							}, 
  							{field: 'paycust', header: '支付对象',visible:false},
  							{field:'fpnote',header:'备注',headerAlign:'center',width:100,allowSort:true,
  					              formEditorConfig:{
  						                     width:"100%",
  						                   newLine:true,
  						                   colspan: 3,
  						                      type:'textarea',
  						              fieldVisible:true}}
  					]					
  				});
  			});
  		});
  		function getQueryInputValue(miniTable,miniForm, operFlag){
  			var contractidi=miniTable.getSelected().contractid;	
  			 var clientBankQueryInput = getMiniuiExtObject("payment_maintenance_editFormPopupWindow_form_purchasecontract");
  			 var conidparams=clientBankQueryInput.params;
  			 conidparams.contractid =  contractidi;
  		};
  		function sendSaveAjax(miniTable, miniForm, editFormItemOperFlag, addIndex){
  			var rowid = miniTable.getSelected().id;
  			var purchasecontractid = eval(document.getElementById('payment_maintenance_editFormPopupWindow_form_purchasecontract$value')).value; 
  		 	tenwa.ajaxRequest({
  		        url: getRootPath()+ "/acl/savePurchaseContractFundFundPlan.acl",
  		        params: {"rowvalue":rowid,"purchasecontractid":purchasecontractid},
  		        timeout: 30 * 1000,
  		        success: function(res){
  		        	mini.get("payment_maintenance").reload();
  		        },
  		    });
  		  return true;
  		}
  		
  		
  		
  		
  		
	</script>
<div id='id_table_payment_maintenance' style="width:300px;height:200px;" ></div>

</head>
<body>
<div id="multieditWindow" class="mini-window" title="付款信息维护" style="width:550px;height:200px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr class="tr-projectsss-info tr-even">
	             <td class="td-content-title" width="12%">合同编号：</td>
	             <td class="td-content" width="38%"><input name="contract_info.proj_id" readOnly id ="contract_number"  class="mini-textbox" label="合同编号"  type="text"></td>
	             <td class="td-content-title" width="12%">付款编号：</td>
	             <td class="td-content" width="38%"><input name="contract_info.proj_id" readOnly id ="payment_id"  class="mini-textbox" label="付款编号"  type="text"></td>
	            
	            </tr>
	          <tr class="tr-odd">
				<td class="td-content-title">计划金额：</td>
				<td class="td-content"><input id="planmoney"  readOnly required="true" name="contract_info.contractnumber" class="mini-textbox" label="计划金额" /></td>
			  	<td class="td-content-title">计划日期：</td>
				<td class="td-content"><input id="plantime"  readOnly name="contract_info.contractid" class="mini-textbox" label="计划日期" required="true"  allowInput="true" /></td>
				
			  </tr> 
                <tr>
                    <td style="width:80px;">采购合同：</td>
                    <td >
                        <input id="id_contractlist" name="dunninginfo" class="mini-buttonedit mini-queryinput" allowInput = "false" />
                    </td>
                </tr>   
                <tr>
                    <td >
                        <a class="mini-button " onclick="submitMultiData">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                    <td >
                        <a class="mini-button " style="display:none;" id = "edit" onclick="editbutten">&nbsp;&nbsp;修改&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
</html>
<script>
	function submitMultiData(e){
		var purchasecontractid = mini.get("id_contractlist").getValue();
		if("" == purchasecontractid){mini.alert("请选择相关采购合同！");return false;}
		var tablevalue = mini.get("payment_maintenance");
		var row = tablevalue.getSelected();
		var rowid = row.id;
		tenwa.ajaxRequest({
		        url: getRootPath()+ "/acl/savePurchaseContractFundFundPlan.acl",
		        params: {"rowvalue":rowid,"purchasecontractid":purchasecontractid},
		        timeout: 30 * 1000,
		        success: function(res){
		        	var text = res.responseText;
		        	if(text=="记录成功"){
						mini.alert("信息维护成功！");
						mini.get("payment_maintenance").load();
						mini.get("multieditWindow").hide();
						return;
		        	}else if(text=="记录失败"){
						mini.alert("信息维护失败！");
						return;
		        	}else{
		        		mini.alert("该信息已维护过,如有需要请点击修改!");
		        		$("#edit").show();
		        		return;
		        	} 
		        }
		    });
		
	};
	function editbutten(e){
		var edit = "yes";
		var purchasecontractid = mini.get("id_contractlist").getValue();
		if("" == purchasecontractid){mini.alert("请选择相关采购合同！");return false;}
		var tablevalue = mini.get("payment_maintenance");
		var row = tablevalue.getSelected();
		var rowid = row.id;
		tenwa.ajaxRequest({
		        url: getRootPath()+ "/acl/savePurchaseContractFundFundPlan.acl",
		        params: {"rowvalue":rowid,"purchasecontractid":purchasecontractid,"edit":edit},
		        timeout: 30 * 1000,
		        success: function(res){
		        	var text = res.responseText;
		        	if(text=="记录成功"){
						mini.alert("信息维护成功！");
						mini.get("payment_maintenance").load();
						mini.get("multieditWindow").hide();
						return;
		        	}else if(text=="记录失败"){
						mini.alert("信息维护失败！");
						return;
		        	}else if(text=="已经添加"){
		        		mini.alert("该信息已维护过,如有需要请点击修改!");
		        		$("#edit").show();
		        		return;
		        	}else{
		        		mini.alert("信息修改成功！");
						mini.get("payment_maintenance").load();
						$("#edit").hide();
						mini.get("multieditWindow").hide();
						return;
		        	}
		        }
		    });
		
	};
</script>