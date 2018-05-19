<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "put_plan",
		renderTo: "id_table_put_plan",
		width : globalClientWidth-10,
		height : 300,
		editFormPopupWindowWidth : 400,
		lazyLoad: false,
		isClickLoad:false,
		multiSelect : true,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: showTools,
		tools:  toolsArray,
		data: JsonUtil.decode('${empty json_put_plan_str ? "[]" : json_put_plan_str }'),
		columns: [
		    {type : 'indexcolumn'},	
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'paymentid', header: '付款编号',formEditorConfig:{newLine:true,readOnly : true,required: true}},
			{field: 'contractid', header: '合同id',visible: false,formEditorConfig:{fieldVisible: false,newLine:true,readOnly : true,required: true}},
			{field: 'feetype', header: '费用类型',formEditorConfig:{newLine:true}, visible: false},
			{field: 'feetypename', header: '费用类型',formEditorConfig:{newLine:true}},
			{field: 'devicename', header: '设备名称ID',visible: false,formEditorConfig:{fieldVisible: false}},
			{field: 'devicename1', header: '设备名称',formEditorConfig:{fieldVisible: false}},
			{field: 'fundtype', header: '款项节点',formEditorConfig:{newLine:true}, visible: false},
			{field: 'fundtypename', header: '款项节点',formEditorConfig:{newLine:true}},
			{field: 'planmoney', header: '计划金额',summary : true,dataType : "currency",formEditorConfig:{}},
			{field: 'overmoney', header: '未付款金额',summary : true,dataType : "currency",formEditorConfig:{}},
			{field: 'factmoney', header: '已付款金额',summary : true,dataType : "currency"}, 
			{field: 'plandate', header: '计划日期', visible: false,formEditorConfig:{}},
			{field: 'paycustname', header: '支付对象'},
			{field: 'connum', header: '采购合同号'},
			{field: 'contractname', header: '采购合同名称'},
			{field: 'contractmoney', header: '采购合同总额',summary : true,dataType : "currency"},
			{field: 'sumplanmoney', header: '采购合同计划总额',summary : true,dataType : "currency"},
			{field: 'overcontractmoney', header: '采购合同余额',summary : true,dataType : "currency"},
			{field: 'paycust', header: '支付对象',visible:false},
			{field: 'fileinformation', header: '文件信息',width: '160',
				formEditorConfig:{fieldVisible: false},
				visible:true,
				renderer: function(e){
					return getPaydownloadforfundput(e);
				}
				
			},
			{field: 'fpnote', header: '备注',formEditorConfig:{}}
		]
	});
	});
	
});
var toolsArray=
[
{
	html : '生成付款',
	plain : true,
	iconCls : 'icon-ok',
	handler : function(miniTable, buttonText) 
	{
		var put_current = mini.get("put_current");//本次资金付款grid
		var curGrids =put_current.getData();
		var rows = miniTable.getSelected();
        if (!rows)
		{
			mini.alert("请勾选数据在操作!");
			return false;
		}
        var put_plan=mini.get("put_plan").getData();
        var rowss = miniTable.getSelecteds();
        if(rowss.length>0){
        	for(var m=0;m<rowss.length;m++){
        		var overmoneycopy=rowss[m].overmoney;
        		if(overmoneycopy==0){
        			mini.alert("未付款金额等于0时无需在进行付款！");
        		return false;
        		}
        	}
        }
		if (curGrids.length > 0) //第二次操作才判断
 		{
			
			for(var s=0;s<rowss.length;s++){
				var rowssid=rowss[s].id;
				for ( var n = 0; n < curGrids.length; n++){
	 				if ( rowssid == curGrids[n].fundfundchargeplan ){
	 					mini.alert("被选中的记录已在本次付款明细中存在,请不要重复生成!");
	 					//miniTable.deselectAll(false);
	 					return false;
	 				}
	 			}
			}
 		}
            	var selected = mini.getbyName("proj_info.fund_delivery").getText();
            	var contractid="${requestScope['contract_info.id']}";
            	if(selected=="是"){
            		  if(rowss.length>1){
            			   mini.alert("请选择一笔设备款进行坐扣!");
            			   return false; 
            		  }
            		for(var w=0;w<rowss.length;w++){
            			if(rowss[w].feetype!="feetype10"){
            				mini.alert("请选中费用类型为设备款的数据进行抵扣!");
            				return false;
            			  }
            		     }
            			mini.get("id_window_fund_offset").show();
            			getFundPutPlan(rowss);
                 		getPremiseCondition(contractid,rowss);
                  }else{
           			    getFundPutPlan(rowss);
           			 	getPremiseCondition(contractid,rowss);
           				mini.alert("操作成功,请到本次付款明细查看!");
                  }
               }
	}];
	
	function getPremiseCondition(contractid,rowss){
		var contractid = contractid;
		var premise_condition=mini.get("premise_condition");
		for(var k=0;k<rowss.length;k++){
			if(rowss[k].feetype=='feetype10'){
				var paymentid=rowss[k].paymentid;
				var feetype=rowss[k].fundtype;
				var devicename=rowss[k].devicename;
				$.ajax({
			        url: getRootPath()+"/acl/getPremiseCondition.acl",
			        type: "post",
			        cache: false, 
			        data :{contractid: contractid,paymentid:paymentid,feetype:feetype,devicename:devicename},
			        async : false,
			        success: function (data){
			        var result = mini.decode(data);
			        premise_condition.addRows(result, 0);
				    $("#id_json_premise_condition_str").val(mini.encode(mini.get("premise_condition").getData()));
			        }
			    });
			}
		}
	}
function  getFundPutPlan(planGrid){   
	var newRow = {};
	var currentGrid = mini.get("put_current");//获取本次计划grid
	var rowDatas = [];
	var oDate = new Date();
	for (var i = 0 ;i <planGrid.length; i++ ){
		    newRow.numuuid=(oDate.getMinutes()*60+oDate.getSeconds())+Math.random()*100000000000000000;
			newRow.connum=planGrid[i].connum;
			newRow.contractname=planGrid[i].contractname;
			newRow.contractmoney=planGrid[i].contractmoney;
			newRow.overcontractmoney=planGrid[i].overcontractmoney;
			newRow.fundfundchargeplan=planGrid[i].id;
			newRow.paymentid=planGrid[i].paymentid;
			newRow.pid=planGrid[i].pid;
			newRow.feetype=planGrid[i].feetype;
			newRow.feetypename=planGrid[i].feetypename;
			newRow.fundtype=planGrid[i].fundtype;
			newRow.fundtypename=planGrid[i].fundtypename;
			newRow.alreadymoney=planGrid[i].factmoney;
			newRow.settlemethod='payfund6';
			newRow.settlemethodname='电汇';
			newRow.devicename = planGrid[i].devicename;
			newRow.devicename1 = planGrid[i].devicename1;
			newRow.paytype='pay_type_out';
			newRow.paytypename='付款';
			newRow.oldplanmoney=planGrid[i].planmoney;
			newRow.factdate=planGrid[i].plandate;
			newRow.oldplandate=planGrid[i].plandate;
			newRow.factmoney=planGrid[i].overmoney;//本次付款金额=计划金额-已付款金额
			newRow.factobject=planGrid[i].paycust;
			newRow.factobjectname=planGrid[i].paycustname;
			newRow.comparemoney=planGrid[i].overmoney;//把可付款的金额传到本次付款列表方便比较	
			newRow.feeadjust='0';
			newRow.priorityflag='N',
			newRow.ffcmemo=planGrid[i].fpnote;
			rowDatas.push(mini.clone(newRow));
		}
		currentGrid.addRows (rowDatas, 0);
		currentGrid.saveDataToInput();
		//miniTable.deselectAll(false);
		return true; 
}


function downloadFileforfundput(Id) {
	attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
}

function getPaydownloadforfundput(e){
	  var fileHtmlTd = '';
	  //文件id
	 var idStr = e.record.fileid;
	if(idStr == null || idStr == undefined || idStr == ''){
		return " ";
	}
	 var idArray = idStr.split(",");
	//文件名
     var filenameStr = e.record.filename;
      var filenameArray = filenameStr.split(",");
     //拼table
     var renderHtml1 = "<table style='border:0px solid #FFF;'>";
     var renderHtml2 = "</table>";
     for(var i=0;i<filenameArray.length;i++){
   	 var fnStr = filenameArray[i];
   	fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
   	                    
   	                     "<td style='border:0px solid #FFF;'>"+
                                  "<a href='javascript:void(0);' onclick='downloadFileforfundput(\""+ idArray[i] + "\")'>"+"["+fnStr+"]"+"</a>"+
                         "</td>"+
   	                "</tr>";
             } 
     return renderHtml1+fileHtmlTd+renderHtml2;  
}
</script>

<div id="id_table_put_plan"></div>
