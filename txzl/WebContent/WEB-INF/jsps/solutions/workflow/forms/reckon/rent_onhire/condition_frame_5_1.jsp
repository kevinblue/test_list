<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<jsp:include page="../rent_common/cal_js_5_1.jsp">
	<jsp:param value="onhire" name="process"/>
</jsp:include>
<style>
	#businessconditionFormOnhireOnhire .td-content-title{
		width:160px;
	}
	#businessconditionFormOnhireOnhire .td-content{
		width:160px;
	}
	
</style>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div id="businessconditionFormOnhireOnhire">
		<jsp:include page="../rent_common/condition_content_5_1.jsp"></jsp:include>
	</div>
	<div id="beforeInterestContainer" class="mini-window" title="租前息计算" style="width:700px;height:400px;" 
	    showModal="true" allowResize="true" allowDrag="true"
	    >
	    <div id='content_table_beforeInterest_id'></div>
	</div>
<script type="text/javascript">
var businessForm = new mini.Form("businessconditionFormOnhireOnhire");
jQuery(function(){
	//inputs[d].className=inputs[d].className+" element-readonly";
	if('${param.isView}' == 'true' || isViewHistoryTask==true){
		miniui_ext.disableFormFields("businessconditionFormOnhireOnhire");
		$('#calculateButton').hide();
	}else{
		//var inputs = jQuery('#businessconditionFormOnhireOnhire').find('input').css('background','#f5f8fa');
		var form = new mini.Form("businessconditionFormOnhireOnhire");
		var fields = form.getFields();
		for(var index =0;index<fields.length;index++){
			 //判断是否是下拉框控件，是则同时把text属性传入后台
			if(fields[index].uiCls != "mini-datepicker" && fields[index].name != 'beforeinterest'){
				fields[index].disable();
			}
		}
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "condition_beforeInterest",
				renderTo: "content_table_beforeInterest_id",
				width: 675,
				height: 330,
				lazyLoad: false,
				title: "",
				isClickLoad:false,
				remoteOper : false,
				showPager: false,
				multiSelect: true,
				sortMode : "client",
				data: mini.decode('${empty json_fund_plan_equipamt_str ? "[]" : json_fund_plan_equipamt_str }'),
				tools:[{html:'保存',plain:true,iconCls:'icon-save',
                	handler:function(miniTable,buttonText){
                		//起租日
                		calculateBeforeInterest(miniTable);
                	}	
				}], 
				columns: [
					{type: 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: 'id', visible: false},
					{field: 'factmoney', header: '投放金额',
						formEditorConfig:
						{
							newLine :true,
							required: true,
							labelWidth:100,
							maxLength:120,
							colspan: 3,
							width: 345,
							readonly :true
						}
					},
					{field: 'plandate', header: '投放日期',
						formEditorConfig:
						{
							newLine :true,
							required: true,
							labelWidth:100,
							maxLength:120,
							colspan: 3,
							width: 345
						}
					},
					{field: 'datediff', header: '计息天数'},
					{field: 'beforeinterestbranch', header: '计息金额',dataType : "currency",summary : true}
				]
			});
		});
	}
	var rentorrate = mini.get('rentorrate').getValue();
	if(rentorrate == 'rate'||rentorrate == 'handle_method'){
		$('#testrent').hide();
		$('#testrate').show();
	}else{
		$('#testrent').show();
		$('#testrate').hide();
	}
	updateInputThousand();
	//calculateBeforeInterest('start');
	//$('#calculateButton').show();这句会导致在只读页面还会显示测算按钮
});


function beforeInterestButtonClick(e){
	var miniTable = mini.get('condition_beforeInterest');
	var yearRate = mini.get('yearrate').getValue();
	var startDate = mini.get('startdate').getFormValue();
	var fundChargeList =  miniTable.getData();
	for(var i = 0 ; i < fundChargeList.length ; i++){
		var fundCharge = fundChargeList[i];
		var betweenDays = Number(startDate.toDate('yyyy-MM-dd') - fundCharge.plandate.toDate('yyyy-MM-dd'))/(24 * 60 *60 *1000);
		var beforeInterestBranch = decimal(betweenDays/365*Number(fundCharge.factmoney)*Number(yearRate)/100,2); 
		fundCharge.datediff = betweenDays;
		fundCharge.beforeinterestbranch = beforeInterestBranch
	}
	miniTable.setData(fundChargeList);
	mini.get('beforeInterestContainer').show();
}

//刷新页面当中的所有的输入框的钱数为千分位
function updateInputThousand(){
	var fields = form.getFields();
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		if(fields[index].vtype == 'double'){
			fields[index].setValue(formatNumberThousand(fields[index].getValue()));
		}
	}
}


</script>

