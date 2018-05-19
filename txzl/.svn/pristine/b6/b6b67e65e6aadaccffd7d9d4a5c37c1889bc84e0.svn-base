<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
function updateIrrExcel(config){
	config.id=config.id ||"id_uploadfile";
	config.title=config.title||"上传文件"; //标题 
	config.url=config.url||"";//url
	config.jscallback=config.jscallback||"";//回调
	config.parames=config.parames||{};
	config.modelname=config.modelname||"财务报表";
	config.browserType=SysBrowser.getBrowser().toLowerCase();
	createFileAndShow(config);
}

function createFileAndShow(config){
	var $form = jQuery("#" + config.id);
	if (0 < $form.length) {
		document.body.removeChild($form[0]);
	}
	var importExcelUrl = getRootPath()+config.url;
	var uploadAttachmentFileWindow_html = "";
	uploadAttachmentFileWindow_html += '<div id="'+config.id+'" class="mini-window"  closed="true" modal="true" title="'+config.title+'" style="display:none;width:400px;text-align:center;background-color:#FDF9F8;">';
	uploadAttachmentFileWindow_html += '	<center>';
	uploadAttachmentFileWindow_html += '		<div style="width:95%;">';
	uploadAttachmentFileWindow_html += '			<iframe style="display:none;" name="import_excel_real_submit_frame"></iframe>';
	uploadAttachmentFileWindow_html += '			<form id="'+config.id+'Form" action="'+importExcelUrl+'" enctype="multipart/form-data" target="import_excel_real_submit_frame"  method="post" >';
	uploadAttachmentFileWindow_html += '				<table align="center" style="width:90%"><tr><td colspan=2>';
	uploadAttachmentFileWindow_html +=getParametetoStr(config);
	uploadAttachmentFileWindow_html += "</td></tr>";
	uploadAttachmentFileWindow_html += '					<tr><td colspan=2><input type="file" id="id_tableImportTemplate_irr_calculate" name="tableImportExcel" style="width:350px;border:1px solid #DDD;cursor:pointer;"></td></tr>';
	uploadAttachmentFileWindow_html += '					<tr class="content-separator">';
	uploadAttachmentFileWindow_html += '						<td colspan="2" align="center">';
	uploadAttachmentFileWindow_html += '							<a href="javascript:void(0);" id="'+config.id+'_save" class="mini-button" iconCls="icon-add"  onclick=\'javascript:document.getElementById("'+config.id+'Form").submit();\'><span class="mini-button-text">确定</span></a>';
	uploadAttachmentFileWindow_html += '							<a style="margin-left:20px;" id="'+config.id+'_cancle" href="javascript:void(0);" class="mini-button" iconCls="icon-close"  onclick=\'javascript:mini.get("'+config.id+'").hide();\'><span class="mini-button-text" >取消</span></a>';
	uploadAttachmentFileWindow_html += '						</td>';
	uploadAttachmentFileWindow_html += '					</tr>';
	uploadAttachmentFileWindow_html += '				</table>';    
	uploadAttachmentFileWindow_html += '			</form>';
	uploadAttachmentFileWindow_html += '		</div>';
	uploadAttachmentFileWindow_html += '	</center>';
	uploadAttachmentFileWindow_html += '</div>';
	$(document.body).append(uploadAttachmentFileWindow_html);
	mini.parse();
	var formid=config.id+"Form";
	var id=config.id;
	$("#"+config.id+"_save").bind('click',function(){
		var filename=$("#id_tableImportTemplate_irr_calculate").val();
		if(filename==""){alert("请选择上传的文件");return false;}
		mini.mask({ el: document.body,cls: 'mini-mask-loading',html:'上传中...'});
		$("#"+formid).submit();});
	$("#"+config.id+"_cancle").bind('click',function(){mini.get(id).hide()});
	mini.get(config.id).show();
}
function getParametetoStr(config){
	var tempInnerHtml=""; 
	tempInnerHtml+= "<input type='hidden' name='browserType' value='" + config.browserType+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='currentTableId' value='" + config.id+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='jscallback' value='" + config.jscallback+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='modelname' value='" + config.modelname+ "'/>";
	tempInnerHtml+= "<input type='hidden' name='_success' value='window.parent.irrCalculateSuccess'/>";
	tempInnerHtml+= "<input type='hidden' name='_failture' value='window.parent.irrCalculateFailture'/>";
    for(var param in config.parames){
      if (typeof (config.parames[param]) == 'object'){
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value='" + mini.decode(config.parames[param])+ "'/>";
    	}else{
    	tempInnerHtml+= "<input type='hidden' name='"+param+"' value='" + config.parames[param]+ "'/>";	
      }
    }
    return tempInnerHtml;
}
function irrCalculateSuccess( message){
	mini.unmask(document.body);
	var result = mini.decode(message);
	if(result.issucess == 'true'){
		mini.get("irr").setValue(result.irr);//内部收益率
		mini.get("leaseterm").setValue(result.leaseterm);//租赁期限
		mini.get("incomenumber").setValue(result.incomenumber);//还租次数
    	mini.get("enddate").setValue(result.enddate);//结束日期
    	mini.get("grossprofit").setValue(result.grossprofit);//项目粗利
    	mini.get("cleancreditmoney").setValue(result.cleancreditmoney);//授信额度
    	mini.get("firstpaymenttotal").setValue(result.firstpaymenttotal);//计算期初付款总计
    	$mini("cleancreditratio").setValue(decimal(result.cleancreditratio, 6));//授信比例
    	mini.get("fund_rent_plan_frame").isinitData=1;
    	
    	var fundrentplan =mini.decode(result.rentplanlist) ;
    	var finacashdetail =mini.decode(result.cashdetaillist) ;
    	
    	mini.get("fund_rent_plan_frame").setData(fundrentplan);
    	jQuery("#id_json_fund_rent_plan_str").val(mini.encode(fundrentplan));
    	jQuery("#id_json_fund_cash_flow_str").val(mini.encode(finacashdetail));
    	mini.alert("测算成功！", "提示", function(){
			mini.get('irrExcelImportDiv_irr_cal').hide();
		});
	}else{
		mini.alert(result.message);
	}
}
function irrCalculateFailture( message){
	mini.unmask(document.body);
	mini.alert(message);
}
function loadIrrExcelParams(){
	
	var resultData = {};
	resultData.contractId="${requestScope['contract_info.id']}";
	resultData.calType="onhire_change";//标记本次测算属于资产变更
	resultData.process="cont_process"
	var resultDataStr = mini.encode(resultData);
	loadIrrExcel(resultDataStr);
}
//下载租金计划导入模板
function loadIrrExcel(params){
	updateIrrExcel(
	   	 {
			url:'/leasing/IrrRentCalculate.action',
			modelname:'模板管理',
			title:'不规则租金计划Excel导入',
	       	id : 'irrExcelImportDiv_irr_cal',
	        parames:{
	        	conditionitem:params
	       	}
	     }
	);
}
var tools = [];
var columns = [];
var toolsIr =  ['add', '-', 'edit', '-','remove','-','exportExcel','-','importExcel','-',{
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
		$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));
		save('plan');
	 }
	}
	];
var impExcelTool=[{
	html : '导入租金计划',
	plain : true,
	iconCls : 'icon-importExcel',
	handler : function(miniTable, buttonText) {
		//上传模板
		loadIrrExcelParams();
	}
},'-',
{
	html : '模板下载',
	plain : true,
	iconCls : 'icon-importExcel',
	handler : function(miniTable, buttonText) {
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
				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,visible:false,
					formEditorConfig:
					{
						required: true,
						labelWidth:100,
						vtype:"float",
						width: 200,
						otherAttributes:'onblur="adjustPlanPrive1()"'
					}
				},
				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,visible:false,
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
  				{field: 'corpusbusiness', header: '业务本金',dataType : "currency",summary : true,visible:false,
  					formEditorConfig:
  					{
  						required: true,
  						labelWidth:100,
  						vtype:"float",
  						width: 200,
  						otherAttributes:'onblur="adjustPlanPrive1()"'
  					}
  				},
  				{field: 'interestbusiness', header: '业务利息',dataType : "currency",summary : true,visible:false,
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
var showTools = true;
if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
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
	tools: impExcelTool, 
	data: mini.decode('${empty json_fund_rent_plan_str ? "[]" : json_fund_rent_plan_str	 }'),
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
