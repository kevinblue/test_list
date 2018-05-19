<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
function importMiniTableCallBack(message,tableid){
	 var info=eval("("+message+")");
	 var tabledate=info.tabledate;
	 mini.alert(info.message);
	 if(""!=tabledate){
	 if(tableid=="proj_equip"){
		var grid=mini.get(tableid);
		var oldData = grid.getData();
		var tabledate = tabledate.concat(oldData);
		grid.set({data:mini.decode(tabledate)});
	 }else{
	 	var grid=mini.get(tableid);
		grid.set({data:mini.decode(tabledate)});
	 }

	 }
    mini.get("id_minitableimport").hide();
    mini.unmask(document.body);
}
var tools = [];
var columns = [];
/* var toolsIr =  ['add', '-', 'edit', '-','remove','-','exportExcel','-','importExcel','-',{
	html : '租金测算',//自定义按钮的名字
	plain : true,//按钮是否有立体感
	iconCls : 'icon-save',//按钮的图标
	handler : function(miniTable, buttonText) {//按钮响应函数
		var fundPlans = miniTable.getData();
		if(fundPlans.length <= 0 ){
			mini.alert('请先进行租金测算！！！！');
			return;
		}
		//将租金计划重新赋给隐藏域
		mini.get('id_json_fund_rent_plan_str').setValue(mini.encode(miniTable.getData()));
		save('plan');
	 }
	}
	
	];
 */   			
var nomalTool = [{
	html : '租金调整',
	plain : true,
	iconCls : 'icon-redo',
	handler : function(miniTable, buttonText) {
		var fundPlans = mini.get('fund_cash_plan_frame').getData();
		if(fundPlans.length <= 0 ){
			mini.alert('请先进行租金测算！！！！');
			return;
		}
		//将租金计划重新赋给隐藏域
		$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
		save('adjust');
	}
}]; 

var columnsIrr = [
				{type: 'indexcolumn'},
				{type: 'checkcolumn'},
				{field: 'id', header: 'id', visible: false},
				{field: 'rentlist', header: '期项',
					formEditorConfig:
					{
						readOnly:true,
						required: true,
						labelWidth:100,
						width: 200
					}
				},
				{field: 'plandate', header: '计划日期',
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						type:'date',
						format:'yyyy-MM-dd',
						width: 200
					}
				},
				{field: 'rent', header: '租金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						readOnly:true,
						required: true,
						labelWidth:100,
						
						width: 200
					}
				},
				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						
						required: true,
						vtype:"float",
						labelWidth:100,
						width: 200,
						otherAttributes:'onblur="caiwubenjin1()"'
					}
				},
				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						required: true,
						vtype:"float",
						labelWidth:100,
						width: 200,
						otherAttributes:'onblur="caiwubenjin2()"'
					}
				},
				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:"float",
						width: 200,
						otherAttributes:'onblur="adjustPlanPrive1()"'
					}
				},
				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,
					formEditorConfig:
					{
						newLine:true,
						required: true,
						labelWidth:100,
						vtype:"float",
						width: 200,
						otherAttributes:'onblur="adjustPlanPrive2()"'
					}
				},
				{field: 'rentadjust', header: '租金调整',
        			formEditorConfig:
        			{
        				required: false,
        				labelWidth:100,
        				readOnly:true,
        				width: 200
        			},
        			editor:{width:'100%',type:'textbox'}
        		}
			];
var columnsNomal = [
  				{type: 'indexcolumn'},
  				{type: 'checkcolumn'},
  				{field: 'id', header: 'id', visible: false},
  				{field: 'rentlist', header: '期项',
  					formEditorConfig:
  					{
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						width: 200
  					}
  				},
  				{field: 'plandate', header: '计划日期',
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						type:'date',
  						format:'yyyy-MM-dd',
  						width: 200
  					}
  				},
  				{field: 'rent', header: '租金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						
  						width: 200
  					}
  				},
  				{field: 'corpus', header: '财务本金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						
  						required: true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin1()"'
  					}
  				},
  				{field: 'interest', header: '财务利息',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin2()"'
  					}
  				},
  				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive1()"'
  					}
  				},
  				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive2()"'
  					}
  				},
  				{field: 'rentadjust', header: '租金调整',
          			formEditorConfig:
          			{
          				required: false,
          				labelWidth:100,
          				readOnly:true,
          				width: 200
          			}
          		}
  			];		
if("${empty requestScope['settlemethod'] ? 'even_rent' : requestScope['settlemethod'] }" == 'irregular_rent'){
	tools = nomalTool;
	columns =  columnsIrr;
}else{
	columns = columnsNomal;
}		
var showTools = true;
if('${param.isView}' == 'true'){showTools = false};
var config =  {
	id: "fund_rent_plan_frame",
	renderTo: "id_fund_rent_plan_frame",
	width: globalClientWidth - 30,
	height: 700,
	lazyLoad: false,
	isClickLoad:false,
	remoteOper : false,
	showPager: false,
	multiSelect: true,
	showToolbar: showTools,
	editFormPopupWindowWidth : 700,
	importTargetClass:'',//导入EXCEL对应的类
	importOrExPortCallBack:'',//导入回调方法
	importHeaderIndex:'3',//标题行
	importDataIndex:'4',//数据行
	onenter:function(){
		mini.get("fund_rent_plan_frame").commitEdit();//提交编辑
	},
	allowCellEdit: true,
	allowCellSelect: true,
	oncelldblclick: function(e){
		var miniTable = e.sender;
		var rowData   = e.record;
		//miniTable.commitEdit();
		miniTable.beginEditCell();
	},
	tools: tools, 
	data: mini.decode('${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str	 }'),
	completeCallBack:function(miniTable){
		//alert($('#id_fund_rent_plan_frame.mini-toolbar').find('span').length);
	},
	columns:columns,
	afterShowWindowCallBack:function(miniTable,miniForm,operFlag){
		var datas = miniTable.getData();
		if("add" == operFlag){
			var qixiang=datas[datas.length-1].rentlist;
			mini.getbyName("rentlist").setValue(parseInt(qixiang)+1);
		}
	},
	confirmRemoveCallBack:function(miniTable){
		var datas = miniTable.getData();
		var selectedRowDatas = miniTable.getSelecteds();
		var qixiang=datas[datas.length-1].rentlist;
		var strmax=0;
		var strmin=selectedRowDatas[0].rentlist;
		for(var i=0;i<selectedRowDatas.length;i++){
			if(selectedRowDatas[i].rentlist>=strmax){
				strmax=selectedRowDatas[i].rentlist;
			}
			if(selectedRowDatas[i].rentlist<=strmin){
				strmin=selectedRowDatas[i].rentlist;
			}
			
		}
		if(strmax!=qixiang){
			alert("租金计划应该从最后一项开始删除，请重新选择！");
			return false;
		}if(parseInt(strmax)-parseInt(strmin)+1!=selectedRowDatas.length){
			alert("租金计划应该从最后一项开始删除，期项必须是连续的，请重新选择！");
			return false;
		}
		return true;
	},
	validateForm: function(miniTable, miniForm){
		var cwbj=Number(mini.getbyName("corpus").getValue());
		var cwlx=parseFloat(mini.getbyName("interest").getValue());
		var ywbj=parseFloat(mini.getbyName("corpusbusiness").getValue());
		var ywlx=parseFloat(mini.getbyName("interestbusiness").getValue());
		if(ywbj+ywlx>cwbj+cwlx){
			alert("业务本金与业务利息之和不能大于租金!");
			return false;
		}
		mini.getbyName("rent").setValue(decimal(Number(cwbj+cwlx),2));
		return true;
	},
};
var tempColume = mini.clone(config);
var tempColume2 =  mini.clone(config);
jQuery(function(){
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable(config);
	});
	
});

function adjustPlanPrive1(){
	var cwbj=parseFloat(mini.getbyName("corpus").getValue());
	var cwlx=parseFloat(mini.getbyName("interest").getValue());
	var v3=mini.getbyName("corpusbusiness").getValue();
	if(v3==''||! $.isNumeric(v3)){
		v3=0;
		mini.getbyName("corpusbusiness").setValue(0);
	}
	var ywbj=parseFloat(v3);
	var v4=mini.getbyName("interestbusiness").getValue();
	if(v4==''||! $.isNumeric(v4)){
		v4=0;
		//mini.getbyName("interestbusiness").setValue(0);
	}
	var ywlx=parseFloat(v4);
	if(ywbj+ywlx>cwbj+cwlx){
		alert("本次业务本金与业务利息之和不能大于租金!");
		return false;
	}
}
function adjustPlanPrive2(){
	var cwbj=parseFloat(mini.getbyName("corpus").getValue());
	var cwlx=parseFloat(mini.getbyName("interest").getValue());
	var v3=mini.getbyName("corpusbusiness").getValue();
	if(v3==''||! $.isNumeric(v3)){
		v3=0;
		//mini.getbyName("corpusbusiness").setValue(0);
	}
	var ywbj=parseFloat(v3);
	var v4=mini.getbyName("interestbusiness").getValue();
	if(v4==''||! $.isNumeric(v4)){
		v4=0;
		mini.getbyName("interestbusiness").setValue(0);
	}
	var ywlx=parseFloat(v4);
	if(ywbj+ywlx>cwbj+cwlx){
		alert("本次业务本金与业务利息之和不能大于租金!");
		return false;
	}
}
function caiwubenjin1(){
	var v1=mini.getbyName("corpus").getValue();
	if(v1==''||! $.isNumeric(v1)){
		v1=0;
		mini.getbyName("corpus").setValue(0);
	}
	var cwbj=parseFloat(v1);
	var v2=mini.getbyName("interest").getValue();
	if(v2==''||! $.isNumeric(v2)){
		v2=0;
		//mini.getbyName("interest").setValue(0);
	}
	var cwlx=parseFloat(v2);
	mini.getbyName("rent").setValue(cwbj+cwlx);
}
function caiwubenjin2(){
	var v1=mini.getbyName("corpus").getValue();
	if(v1==''||! $.isNumeric(v1)){
		v1=0;
	//	mini.getbyName("corpus").setValue(0);
	}
	var cwbj=parseFloat(v1);
	var v2=mini.getbyName("interest").getValue();
	if(v2==''||! $.isNumeric(v2)){
		v2=0;
		mini.getbyName("interest").setValue(0);
	}
	var cwlx=parseFloat(v2);
	mini.getbyName("rent").setValue(cwbj+cwlx);
}
</script>
<div id="id_fund_rent_plan_frame"></div>