<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	var showTools = true;
	if(isViewHistoryTask){showTools = false;}
	tenwa.createTable({
		id: "rent_income_detail",
		renderTo: "id_table_rent_income_detail",
		width : globalClientWidth-30,
		height : 400,
		title: "",
		remoteOper : false,
		showPager: false,
		lazyLoad: true,
		multiSelect: true,
		showToolbar: showTools,
		tools: ['edit', '-', 'remove'],
		data: JsonUtil.decode('<mini:param  name="json_rent_income_detail_str" defaultValue="[]"/>'),
		columns: [
			{type: 'checkcolumn',visible: showTools},
			{field: 'id', header: 'id', visible: false},
			{field:'planid',header:'planid',visible: false},
			{field: 'planlist', header: '计划期项',type:'number',formEditorConfig:{labelWidth:100,readOnly:true}},
			{field: 'ebdataid', header: '网银编号',formEditorConfig:{labelWidth:100,readOnly:true}},
			{field: 'ebanknumber', header: '网银编号',visible: false},
			{field: 'hirelist', header: '回笼期项',formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'balancemodename', header: '结算方式',visible:false,formEditorConfig: {fillFromFieldName : 'balancemode',fillProperty : 'name'}},
			{field: 'balancemode', header: '结算方式',
				renderer: function(e){
					return e.record.balancemodename;
				},
				formEditorConfig:{
					type:'combobox',
					required: true,
					textField: 'name',
					valueField: 'value',
					fieldVisible: true,
					params: {
					pid: 'PayFund',
					xmlFileName: 'combos/comboDict.xml'
				}
			}},
			{field: 'hiredate', header: '回笼日期',type:"date",format:"yyyy-MM-dd",formEditorConfig:{
				newLine:true,
				type: 'date',
				vtype: 'date',
				format: 'yyyy-MM-dd'
			}},
			{field: 'accountingdate', header: '会计处理日',type:"date",format:"yyyy-MM-dd",formEditorConfig:{
				type: 'date',
				vtype: 'date',
				format: 'yyyy-MM-dd'
			}},
			{field: 'rent', header: '回笼租金',type:'double',summary : true,dataType : "currency",formEditorConfig:{newLine:true,vtype:'float',readOnly:true}},
			{field: 'corpus', header: '回笼本金',type:'double',summary : true,dataType : "currency",formEditorConfig:{vtype:'float',onkeyup: 'checkHire(e,"corpus")'}},
			{field: 'interest', header: '回笼租息',type:'double',summary : true,dataType : "currency",formEditorConfig:{newLine:true,vtype:'float',onkeyup: 'checkHire(e,"interest")'}},
			{field: 'penalty', header: '回笼罚息',type:'double',summary : true,dataType : "currency",formEditorConfig:{vtype:'float',onkeyup: 'checkHire(e,"penalty")'}},
			{field: 'rentadjust', header: '租金调整',type:'double',summary : true,dataType : "currency",formEditorConfig:{readOnly:true,newLine:true,vtype:'float'}},
			{field: 'corpusadjust', header: '本金调整',type:'double',summary : true,dataType : "currency",visible: false,formEditorConfig:{vtype:'float'}},
			{field: 'interestadjust', header: '租息调整',type:'double',summary : true,dataType : "currency",formEditorConfig:{vtype:'float',onkeyup:'checkHire(e,"interestadjust")'}},
			{field: 'penaltyadjust', header: '罚息调整',type:'double',summary : true,dataType : "currency",visible: false,formEditorConfig:{vtype:'float'}},
			{field: 'curcorpusoverage', header: '本金余额',visible:false,formEditorConfig:{vtype:'float'}},
			{field: 'curinterestoverage', header: '租息余额',visible:false,formEditorConfig:{vtype:'float'}},
			{field: 'penaltyoverage', header: '罚息余额',visible:false,formEditorConfig:{vtype:'float'}},
			{field: 'hireobject', header: '付款人',formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'invoiceno', header: '单据号',formEditorConfig:{}},
			{field: 'ownbank', header: '本方银行',formEditorConfig:{colspan:3,newLine:true,readOnly:true}},
			{field: 'ownaccount', header: '本方银行账户',formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'ownnumber', header: '本方银行账号',formEditorConfig:{readOnly:true}},
			{field: 'hirebank', header: '对方银行',formEditorConfig:{colspan:3,newLine:true,readOnly:true}},
			{field: 'hireaccount', header: '对方银行账户',formEditorConfig:{newLine:true,readOnly:true}},
			{field: 'hirenumber', header: '对银行账号',formEditorConfig:{readOnly:true}},
			{field: 'memo', header: '备注',formEditorConfig:{colspan : 3,
				width: 400,
				height:70, type: 'textarea',newLine:true}}
		]
	});
});
//检查核销值
function checkHire(e,type){
    var corpus = getMiniEditFormField("rent_income_detail.corpus");
	var interest =getMiniEditFormField("rent_income_detail.interest");
	var penalty =getMiniEditFormField("rent_income_detail.penalty"); 
	var curcorpusoverage =getMiniEditFormField("rent_income_detail.curcorpusoverage"); 
	var curinterestoverage = getMiniEditFormField("rent_income_detail.curinterestoverage");  
	var penaltyoverage =getMiniEditFormField("rent_income_detail.penaltyoverage");  
	var interestadjust = getMiniEditFormField("rent_income_detail.interestadjust"); 
	var rent= getMiniEditFormField("rent_income_detail.rent"); 
	if(type == "corpus"){
		if(compareTwoField( $("input[name=corpus]").val(),curcorpusoverage.getValue())){
            mini.alert("本次回笼本金金额大于本金余额"+curcorpusoverage.getValue());
            corpus.setValue(curcorpusoverage.getValue());
		}
	}
	if(type == "interest"||type=="interestadjust"){
		 var sum=parseFloat( $("input[name=interest]").val())+parseFloat( $("input[name=interestadjust]").val());
		 sum=parseFloat(sum).toFixed(2);
		 if(compareTwoField(sum,curinterestoverage.getValue())){
	            mini.alert("本次回笼租息加上租息调整的金额大于租息余额"+curinterestoverage.getValue());
	            if(type == "interest"){interest.setValue(curinterestoverage.getValue());}
	            if(type=="interestadjust"){interestadjust.setValue(0);}
		}
	}
	if(type = "penalty"){
		if(compareTwoField( $("input[name=penalty]").val(),penaltyoverage.getValue())){
            mini.alert("本次回笼罚息金额大于罚息余额"+penaltyoverage.getValue());
            penalty.setValue(penaltyoverage.getValue());
		}
	}
	sum=parseFloat($("input[name=corpus]").val()||0)+parseFloat($("input[name=interest]").val()||0)+parseFloat($("input[name=interestadjust]").val()||0);
	sum=parseFloat(sum).toFixed(2);
	rent.setValue(sum);
}
</script>
<div id="id_table_rent_income_detail" style="width: 100%;height: 100%;"></div>