<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<div class="mini-panel" title="报价编号" showCollapseButton="true" style="width: 100%;">
	<table class="fillTable" cellspacing="0" cellpadding="0"   align="center" id="cust_condition_info" >
		<tr class="tr-project-info tr-odd" >
			<td class="td-content-title" width="12.5%">客户报价编号：</td>
			<td class="td-content" colspan="7">	
				<input id="cust_doc_id"
				name="cust_doc_id" class="mini-textbox"
				readonly label="报价编号" type="text" style="width:200px" value = "${requestScope['cust_doc_id']}"/> 
				<a class="mini-button" iconCls="icon-search" plain="true" onclick="openConditonNumber" style="color: red;">查找</a>	
			</td>
		</tr>
	</table>
</div> 

<script type="text/javascript">
var custid = "${requestScope['proj_info.custInfo']}" || custid;
jQuery(function(){
seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
	new ApTable({
		id: "condition_number",
		renderTo: "id_condition_number_containter",
		width: 700,
		height: 400,
		//title: "客户报价编号查询",
		remoteOper : false,
		showPager: false,
		multiSelect: false,
		sortMode : "client",
		showPager : true,
		params:{'custid':custid},
		xmlFileName : '/eleasing/workflow/rent/rent_cal/cust_condition_docid.xml',
		tools:[{
			html : '确定',
			plain : true,
			iconCls : 'icon-addfolder',
			handler : function(miniTable,
					buttonText) {
				mini.mask({
					el: document.body,
					cls: 'mini-mask-loading',
					html: '正在生成测算数据，请稍后...'
				});
				var row = miniTable
						.getSelected();
				if (row) {
					mini.get('editWindow_condition_number').hide();
					mini.get('cust_doc_id').setValue(row.doc_id);
					//ajax 后台查询对应的租金计划，资金计划以及现金流
					$.ajax({
        		        url: "${pageContext.request.contextPath}/leasing/getCustConditionInfo.action",
        		        type: "post",
        		        data:  {
        					custid : custid,
        					docid : row.doc_id
        				} ,
        		        success: function (res) {
        		        	var result = mini.decode(res);
        		        	if(result.flag =='ok'){
        		        		var fundrentplan =result.rentplanlist ;
        		            	var finacashdetail =result.cashdetaillist ;
        		            	var fundFundPlanList =  result.fundfundplanlist;
        		            	mini.get("fund_rent_plan_frame").setData(fundrentplan);
        		            	mini.get("fund_cash_plan_frame").setData(finacashdetail);
        		            	mini.get("fund_fund_plan").setData(mini.decode(fundFundPlanList));
        		            	$("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
        		            	$("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
        		            	$("#id_json_fund_fund_charge_str").val(fundFundPlanList);
        		            	var form = new mini.Form('businessconditionForm');
        		            	for(var p in result){
        		            		if(mini.get(p)){
        		            			var miniObj = mini.get(p)
        		            			miniObj.setValue(result[p]);
        		            			if(miniObj.type == 'combobox'){
        		            				miniObj.setText(result['rawvalue_'+p]);
        		            			} 
        		            		}else{
										if(mini.get(p+"_show")){
											var miniObj = mini.get(p+"_show");
	        		            			miniObj.setValue(result[p]);
	        		            			jQuery('#'+p).val(result[p]);
        		            			}else if(mini.get(p+"show")){
        		            				var miniObj = mini.get(p+"show");
	        		            			miniObj.setValue(result[p]);
	        		            			jQuery('#'+p).val(result[p]);
        		            			}
        		            		}
        		            		
        		            	}
        		            	startReloadConditionContent();
        		            	mini.alert('读取成功！！！');
        		        	}else{
        		        		mini.alert('读取失败！！！');
        		        	}
        		        	mini.unmask(document.body);
        		        },
        		        error: function (jqXHR, textStatus, errorThrown) {
        		            alert(jqXHR.responseText);
        		            mini.unmask(document.body);
        		        }
        		    }); 
				} else {
					mini.alert('请你选择报价编号！！！');
					mini.unmask(document.body);
				}
			}
		}],
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'doc_id', header: '报价编号',width:150},
			{field: 'equip_amt', header: '设备款',dataType : "currency",summary : true},
			{field: 'first_payment', header: '首付款',dataType : "currency",summary : true},
			{field: 'lease_amt_date', header: '付款日'},
			{field: 'start_date', header: '起租日'},
			{field: 'income_number', header: '租赁期限'},
			{field: 'settle_method', header: '租金计算方法'},
			{field: 'handling_charge_money', header: '手续费',dataType : "currency",summary : true},
			{field: 'caution_money', header: '保证金',dataType : "currency",summary : true},
			{field: 'irr', header: '内部收益率'},
			{field: 'year_rate', header: '年利率'}
		]
	});
});
})

function openConditonNumber(){
	var miniTable =  mini.get('condition_number');
	miniTable.load();
	var editWindow = mini.get("editWindow_condition_number");
	editWindow.show();
}
</script>
<div id="editWindow_condition_number" class="mini-window" title="客户报价编号" style="width:700px;height:455px;" 
	    showModal="true" allowResize="false" allowDrag="true">
 	<div id='id_condition_number_containter'></div>
 </div>
 
 