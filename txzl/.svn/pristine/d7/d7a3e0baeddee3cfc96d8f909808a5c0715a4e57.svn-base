<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
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
  					id: "payment_condition",
  					renderTo: "id_table_payment_condition",
  					width: currentClientWidth,
  					height: currentClientHeight,
  					//editRemoteOperUrl:getRootPath()+ "/eleasing/removePurchaseFundPlan.acl",
  					lazyLoad: false,
  					title: "付款信息维护",
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
  								if(!row.contractmoney){
  									mini.alert("维护失败！请先维护采购合同总额！");
  									return false;
  								}
  								
  								var multiform = new mini.Form("multiform");
  								var multieditWindow = mini.get("multieditWindow");
  								multiform.clear();
								mini.get("id_projname").setValue(row.projectname); 
								mini.get("contract_number").setValue(row.contractnum);
								mini.get("id_contract_set").setValue(row.contractnumbersetnum);
								mini.get("id_contractmoney").setValue(tenwa.money2Thousand(row.contractmoney));
								mini.get("id_overcontractmoney").setValue(tenwa.money2Thousand(row.overcontractmoney));
								var contractid = row.contractids;
								tenwa.createQueryInput({
									id:'id_payment_planid',
									label:'投放计划',
									windowWidth: 450,
									windowHeight: 700,
									textField: 'name',
									valueField: 'value',
									params: {
										contractid: contractid,
										    xmlFileName:'/eleasing/workflow/fund/fundcomm/purchase_contract_plan_list.xml'
									}
								});
  								multieditWindow.show();
  							}else{
  								mini.alert("请选要维护的数据！");
  							}
  						}
  					},'-',{
  					  html:'删除',
					    plain:true,
					  iconCls:'icon-edit', 
					  handler:function(miniTable, buttonText){ 
						var row = miniTable.getSelected();						
						if(!row){
							mini.alert("请选择要删除的数据！");
							return false;
						}
						if(!row.purfundplanid.length){
							mini.alert("请选择已经维护的数据！");
							return false;
						}
						var url=getRootPath()+ "/eleasing/removePurchaseFundPlan.acl";
						mini.mask({
							el : document.body,
							cls : 'mini-mask-loading',
							html : '数据操作中 请稍等...'
						});
						jQuery.ajax({
							url : url,
							type : 'POST',
							timeout : 30 * 1000,
							data :{purfundplanid:row.purfundplanid},
							dataType : 'json',
							error : function(res, errInfo, e) {
								mini.unmask(document.body);
							},
							success : function(resultJson) {
								var returnState = resultJson.returnType;
								switch (returnState) {
								case "SUCCESS": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert("删除成功！");
									break;
								}
								case "FAILURE": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert("删除失败！"+resultJson.content);
									break;
								}
								}
							}
						});
					}
  					}
  					],
  					queryButtonColSpan: 6,
  					queryButtonNewLine:true,
  					xmlFileName: '/eleasing/workflow/fund/fundcomm/open_payment_fund_condition_list.xml',   
  					columns:[ 
  					       {type : 'indexcolumn'},	
  							{type: 'checkcolumn'},
  							{field: 'ids', header: 'id', visible: false},//采购合同的ID
  							{field: 'fundplanid', header: 'fundplanid', visible: false},//中间表维护的fundplanID
  							{field: 'contractids', header: '合同id',visible: false},//主合同ID contract_info
  							{field: 'purfundplanid', header: '采购关联计划中间表ID',visible: false},//中间表主键ID
  							{field: 'createdate', header: '时间',dateFormat:'yyyy-MM-dd',visible: false},							
  							{field: 'projectname', header: '项目名称',queryConfig : {},formEditorConfig:{visible:false,readOnly : true}},
  							{field: 'contractnum', header: '合同编号',queryConfig : {},formEditorConfig:{visible:false,readOnly : true}},
  							{field: 'contractnumbersetnum', header: '采购合同编号',queryConfig : {},formEditorConfig:{visible:false,readOnly : true}},
  							{field: 'contractnumbersetname', header: '采购合同名称',queryConfig : {}},  							
  							{field: 'contractmoney', header: '采购合同总额',dataType : "currency",formEditorConfig:{visible:false,readOnly : true}},
  							{field: 'overcontractmoney', header: '采购合同余额',dataType : "currency"},
  							//采购/计划余额是指采购合同总额减去已经关联的计划金额的总和
  							{field: 'plancontractmoney', header: '采购/计划余额',dataType : "currency"}, 
  							{field: 'paymentid', header: '付款编号',formEditorConfig:{readOnly : true,required: true}},
  							{field: 'feetype', header: '费用类型',queryConfig : {},formEditorConfig:{newLine:true,readOnly : true}, visible: false},
  							{field: 'planmoney', header: '计划金额',summary : true,dataType : "currency",formEditorConfig:{readOnly : true}},
  							{field: 'overmoney', header: '未付款金额',summary : true,dataType : "currency",formEditorConfig:{newLine:true,readOnly : true}},
  							{field: 'factmoney', header: '已付款金额',summary : true,dataType : "currency",formEditorConfig:{readOnly : true}}, 
  							{field: 'plandate', header: '计划日期', visible: false,formEditorConfig:{readOnly : true}},
  							{field: 'paycustname', header: '支付对象',formEditorConfig:{newLine:true,readOnly : true}},
  							{field: 'checkid', header: '验证id',formEditorConfig:{newLine:true,readOnly : true},visible: false}, 
  							{field: 'paycust', header: '支付对象',visible:false},
  							{field:'fpnote',header:'备注',headerAlign:'center',width:100,allowSort:true,
  					              formEditorConfig:{
  						                   newLine:true,
  						                      type:'textarea',
  						              fieldVisible:true}}
  					]					
  				});
  			});
  		});			
	</script>
<div id='id_table_payment_condition' style="width:300px;height:200px;" ></div>
<body>
<div id="multieditWindow" class="mini-window" title="付款信息维护" style="width:550px;height:200px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr class="tr-projectsss-info tr-even">
               	 <td class="td-content-title" width="12%">项目名称：</td>
	             <td class="td-content" width="38%"><input name="id_projname" readOnly id ="id_projname"  class="mini-textbox" label="合同编号"  type="text"></td>
	  
	             <td class="td-content-title" width="12%">合同编号：</td>
	             <td class="td-content" width="38%"><input name="contract_number" readOnly id ="contract_number"  class="mini-textbox" label="合同编号"  type="text"></td>
	      
	            </tr>
	          <tr class="tr-odd">
	             <td class="td-content-title" width="12%">采购合同编号：</td>
	             <td class="td-content" width="38%"><input name="id_contract_set" readOnly id ="id_contract_set"  class="mini-textbox" label="采购合同编号"  type="text"></td>
	            
				<td class="td-content-title">采购合同总额金额：</td>
				<td class="td-content"><input id="id_contractmoney"  readOnly name="id_contractmoney" class="mini-textbox" label="计划金额" /></td>			 	   
			  </tr> 
                 <tr>
                    <td style="width:80px;">投放计划：</td>
                    <td >
                        <input id="id_payment_planid"  name="id_payment_planid" class="mini-buttonedit mini-queryinput" allowInput = "false" />
                    </td>
                    <td style="width:80px;">采购合同剩余总金额：</td>
                    <td >
                        <input id="id_overcontractmoney"  name="id_overcontractmoney" c class="mini-textbox" label="采购合同剩余总金额" />
                    </td>
                </tr>   
                <tr>
                    <td >
                        <a class="mini-button" onclick="submitMultiDatas">&nbsp;&nbsp;新增&nbsp;&nbsp;</a>
                    </td>
                    <td >
                        <a class="mini-button"  id = "edit" onclick="editbuttens">&nbsp;&nbsp;修改&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
<script>
	function submitMultiDatas(e){
		if(!checkplanmoney('add')){
			return  false;
		}
		 var contractplanid = mini.get("id_payment_planid").getValue();		 
		if("" == contractplanid){mini.alert("请选择投放计划！");return false;}
		var tablevalue = mini.get("payment_condition");
		var row = tablevalue.getSelected();
		var purchasecontractid = row.ids;		
		var fundplanid = row.fundplanid;
		if(fundplanid==contractplanid){
			mini.alert('该信息已新增过，清不要重复维护！');
			return false;
		}
		tenwa.ajaxRequest({
		        url: getRootPath()+ "/acl/savePurchaseContractFundFundcondition.acl",
		        params: {"contractplanid":contractplanid,"purchasecontractid":purchasecontractid},
		        timeout: 30 * 1000,
		        success: function(res){
		        	var text = res.responseText;
		        	if(text=="记录成功"){
						mini.alert("信息维护成功！");
						mini.get("payment_condition").load();
						mini.get("multieditWindow").hide();
						return;
		        	}else if(text=="记录失败!"){
						mini.alert("信息维护失败！");
						return;
		        	}else{
		        		mini.alert("该信息已维护过,不能重复新增!");
		        		return;
		        	} 
		        }
		    });
		 
	}
	function editbuttens(e){
		if(!checkplanmoney('edit')){
			return  false;
		}
		 var edit = "yes";
		var contractplanid = mini.get("id_payment_planid").getValue();//编辑框内的计划
		if("" == contractplanid){mini.alert("请选择投放计划！");return false;}
		var tablevalue = mini.get("payment_condition");
		var row = tablevalue.getSelected();
		var fundplanid = row.fundplanid;
		if(fundplanid==""){
			mini.alert("该采购合同没有维护，清先新增维护!");
			return;
		} 
		if(fundplanid==contractplanid){
			mini.alert("请选择不同的计划进行修改!");
			return;
		} 
		var purchasecontractid = row.ids;
		tenwa.ajaxRequest({
		         url: getRootPath()+ "/acl/savePurchaseContractFundFundcondition.acl",
		             params: {"contractplanid":contractplanid,"fundplanid":fundplanid,"purchasecontractid":purchasecontractid,"edit":edit},
		              timeout: 30 * 1000,
		                success: function(res){
		                	var text = res.responseText;
		                	if(text=="修改成功"){
		                		mini.alert("信息修改成功");
		                		mini.get("payment_condition").load();
		                		mini.get("multieditWindow").hide();
		                		return;
		                	}else if(text=="已经添加"){
								mini.alert("该修改信息已存在，清不要重复维护！");
								return;
				        	}else{
		                		mini.alert("信息修改失败");
		                	}
		        
		        } 
		    });
		  
	}
	function  checkplanmoney(flag){	
		 var row=mini.get("payment_condition").getSelected();//获取当前选中的行对象
		 var contractmoney=row.contractmoney;//当前采购合同总额
		 var overcontractmoney=row.overcontractmoney;//当前采购合同余额	
		 var plancontractmoney=row.plancontractmoney;//当前采购 计划余额
		 var planDetail=mini.get("id_payment_planid").getText();//获取选中的计划，通过内容可以赛选出计划金额
		 var planDetailArr=planDetail.split(":");
		 var planmoney=planDetailArr[1];
		 var currentplanmoney=row.planmoney;//当前选中对象的计划金额
		 var currentfactmoney=row.factmoney;//当前选中对象的已付金额
		 if(flag=='add'){
			 if((parseFloat(plancontractmoney)-parseFloat(planmoney))<0){
				 mini.alert("所选计划额金额不能大于采购合同计划余额！");
				 return false;
			 }
		 }
		 if(flag=='edit'){
			if((plancontractmoney+parseFloat(currentplanmoney)-parseFloat(planmoney))<0){ 
				 mini.alert("所选计划额金额不能大于采购合同计划余额！");
				 return false;
			 }
		 }
		 return true;
	}
</script>