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
function loadIrrExcelParams(){
	
	var equipamt =  $minigetvalue("equipamt");
	if(equipamt){
		if(Number(equipamt) <= 0 ){
			mini.alert('设备款需大于0！！！');
			return;
		}
	}else{
		mini.alert('设备款不能为空！！！');
		return;
	}
	var yearrate = $minigetvalue("yearrate");
	var rateRegex = /^[-\+]?\d+(\.[0-9]{1,6})?$/;
	if(!rateRegex.test(yearrate)){
		mini.alert('请你输入正确格式的测算利率！')
		return;
	}else{
		if(Number(yearrate) <= 0 ){
			mini.alert('测算利率需大于0！');
			return ;
		}
	}
	var leaseamtdate = $minigetvalue("leaseamtdate");
	if(!leaseamtdate){
		mini.alert('请先输入付款日！！！');
		return;
	}
	var startdate = $minigetvalue("startdate");
	if(!startdate){
		mini.alert('请先输入起租日！！！');
		return;
	}
	var o = businessForm.getData(true,true);
	var fields = businessForm.getFields();
	var resultData = {};
	for(var index =0;index<fields.length;index++){
		 //判断是否是下拉框控件，是则同时把text属性设置combobox
		 var fieldValue = o[fields[index].name];
		if(fields[index].uiCls == "mini-textbox"){
			if(fields[index].getInputText().indexOf(',')!=-1){
				//所有textbox去掉逗号,
				fieldValue = fields[index].getInputText().replace(/,/g,"");
			}
		}
		resultData[fields[index].name] = fieldValue;
	}
	resultData.json_fund_fund_charge_str = $('#id_json_fund_fund_charge_str').val();
	var resultDataStr = mini.encode(resultData);
	loadIrrExcel(resultDataStr);
}
function loadIrrExcel(params){
	updateIrrExcel(
	   	 {
			url:'/leasing/IrrRentCalculate.action',
			modelname:'不规则测算',
			title:'不规则租金计划Excel导入',
	       	id : 'irrExcelImportDiv_irr_cal',
	        parames:{
	        	conditionitem:params
	       	}
	     }
	);
}
var columns = [];
var tools=[];
var toolsIrregular =  [{
		html : '导入不规则租金计划',//自定义按钮的名字
		plain : true,//按钮是否有立体感
		iconCls : 'icon-save',//按钮的图标
		handler : function(miniTable, buttonText) {//按钮响应函数
			loadIrrExcelParams();
		 }
	},'-','edit','-',{
		html : '模板下载',//自定义按钮的名字
		plain : true,//按钮是否有立体感
		iconCls : 'icon-save',//按钮的图标
		handler : function(miniTable, buttonText) {//按钮响应函数
			var fileTeplate=new fileTemplateConfig({
		       	 templateno:'F-201605001',
		       	 tableid:miniTable.config.id,
		            modelname:miniTable.config.title,
		            returntype:'file',
		            parames:{}
		            });
		      fileTeplate.createFile();
		 }
	}
	];
var toolsSpecial =  [{
	html : '租金测算',//自定义按钮的名字
	plain : true,//按钮是否有立体感
	iconCls : 'icon-save',//按钮的图标
	handler : function(miniTable, buttonText) {
		save();
	 }
	},'-','edit'
	];

var columnsNomal = [
  				{type: 'indexcolumn'},
  				{type: 'checkcolumn'},
  				{field: 'id', header: 'id', visible: false},
  				{field: 'rentlist', header: '期项',
  					formEditorConfig:{
  						fieldVisible : false
  					}
  				},
  				{field: 'calplandate', header: '计划日期',
  					formEditorConfig:
  					{
  						required: true,
  						readOnly:true,
  						labelWidth:100,
  						type:'date',
  						format:'yyyy-MM-dd',
  						width: 200
  					}
  				},
  				{field: 'plandate', header: '实际计划日期',dateFormat : "yyyy-MM-dd",
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						type:'month',
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
  				{field: 'actualrent', header: '实际还款额',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						readOnly:true,
  						required: true,
  						labelWidth:100,
  						
  						width: 200
  					}
  				},
  				{field: 'corpus', header: '本金',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						newLine:true,
  						required: true,
  						readOnly:true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin1()"'
  					}
  				},
  				{field: 'interest', header: '利息',dataType : "currency",summary : true,
  					formEditorConfig:
  					{
  						required: true,
  						readOnly:true,
  						vtype:"float",
  						labelWidth:100,
  						width: 200,
  						otherAttributes:'onblur="caiwubenjin2()"'
  					}
  				},
  				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,visible: false,
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive1()"'
  					}
  				},
  				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,visible: false,
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
  				{field: 'rentadjust', header: '租金调整',visible: false,
          			formEditorConfig:
          			{
          				required: false,
          				labelWidth:100,
          				readOnly:true,
          				width: 200
          			}
          		}
  			];		
if("${empty requestScope['settlemethod'] ? 'special_regular' : requestScope['settlemethod'] }" == 'special_regular'){
	tools = toolsSpecial;
}else{
	tools = toolsIrregular;
}		
var showTools = true;
if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
if('${param.isOnhire}' == 'true' && isViewHistoryTask != true){showTools = true;};
if('${param.isContractApp}' == 'true' && isViewHistoryTask != true){showTools = true;};
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
	columns:columnsNomal,
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
	updateOperCallBack :function(miniTable,rowData){
		var startdate=mini.clone($minigetvalue("startdate")).format('yyyy-MM-dd');
		var datestr=rowData.plandate;
		var date=new Date(datestr).format('yyyy-MM-dd');
		var maxday=getLastDay(date.substr(0,4),Number(date.substr(5,2)));
		if(Number(maxday)>=Number(startdate.substr(8,2))){
			datestr=datestr+"-"+startdate.substr(8,2);
		}else{
			datestr=datestr+"-"+maxday;
		}
		date=new Date(datestr).format('yyyy-MM-dd');
		miniTable.getSelected().plandate=date;
		var rowDatas = miniTable.getData();
		miniTable.setData(rowDatas);
		mini.getbyName("plandate").destroy();
	},
	cancelOperCallBack :function(miniTable,miniForm){
		mini.getbyName("plandate").destroy();
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
function getLastDay(year,month)
{
 var new_year = year;  //取当前的年份
 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）
 if(month>12)      //如果当前大于12月，则年份转到下一年
 {
 new_month -=12;    //月份减
 new_year++;      //年份增
 }
 var new_date = new Date(new_year,new_month,1);//取当年当月中的第一天
 return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期
}
</script>
<div id="id_fund_rent_plan_frame"></div>