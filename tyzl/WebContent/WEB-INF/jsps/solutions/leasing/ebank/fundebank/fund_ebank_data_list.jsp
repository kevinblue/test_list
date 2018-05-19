<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>网银接口信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
	 //是否废弃
	 var localEnabled = [ {id:'',text:''},{id : '否',text : '否'}, {id : '是',text : '是'}];
	 var localMayMoney = [ {id:'',text:'全部'},{id : ' and e.fact_money-e.no_with_money-nvl(cf.fundmoney,0)- nvl(cr.rent,0)-nvl(pro.process_amount,0)>0',text : '大于零'}, {id : ' and  e.fact_money-e.no_with_money-nvl(cf.fundmoney,0)- nvl(cr.rent,0)-nvl(pro.process_amount,0)=0',text : '等于零'}];
	 function importMiniTableCallBack(message,tableid){
		 var info=eval("("+message+")");
		 mini.alert(info.message);
	     mini.get("id_minitableimport").hide();
	     mini.unmask(document.body);
	     mini.get(tableid).reload();
	}
    jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				//导入Excel配置
				importTargetClass:'com.tenwa.leasing.entity.base.FundEbankData',
				importOrExPortCallBack:'checkFundEbankInfoImport',
				importDataIndex:'2',
				importHeaderIndex:'1',
				id:'table_id_index',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'网银信息',				
				multiSelect:false, 
				editFormPopupWindowWidth:700, 
				hiddenQueryArea:false, 
				queryButtonColSpan:6,
				queryButtonNewLine:true,
				showPager:true,
				xmlFileName:'eleasing/jsp/ebank/fundebank/fund_ebank_data_list.xml',
				tools: toolsArray, 
				remoteOper:true,
				entityClassName:'com.tenwa.leasing.entity.base.FundEbankData',
				entityBeanCallBackClassName:'com.tenwa.leasing.serviceImpl.ebank.FundEbankBeanCallBack',
				validateForm: function(miniTable, miniForm){
				    var invalid=getMiniEditFormField("table_id_index.invalid").getText();
					var hadmoney=getMiniEditFormField("table_id_index.hadmoney").getValue();
					var factmoney= getMiniEditFormField("table_id_index.factmoney").getValue();
					var nowithmoney=getMiniEditFormField("table_id_index.nowithmoney").getValue();					
					hadmoney=parseFloat(hadmoney).toFixed(2);
					factmoney=parseFloat(factmoney).toFixed(2);
					nowithmoney=parseFloat(nowithmoney).toFixed(2);
					var sum=parseFloat(hadmoney)+parseFloat(nowithmoney);
					sum=sum.toFixed(2);
					if(parseFloat(hadmoney)>0){
                       if(invalid=="是"){mini.alert("已核销过的网银不能废弃");return false;}
					}
					if(parseFloat(sum)>parseFloat(factmoney)){mini.alert("非业务金额大于可核销金额");return false;};
					return true;
			    },
			    params:{invalid:'否'},
				columns:[ 
				    {type:'indexcolumn'},//有序列
				   	{type:'checkcolumn'},//单选行
				   	{field:'id',header:'id',visible:false},
				   	{field:'ebdataid',header:'网银编号',allowSort:true,
					   	           queryConfig:{
			   	                    lableWidth:100},
					   	      formEditorConfig:{
								   	  readOnly:true,
								   	labelWidth:100}},
				   	{field:'sn',header:'流水号',allowSort:true,
								   queryConfig:{},
						      formEditorConfig:{
									labelWidth:100,
								      readOnly:true}},
				 	{field:'invalid',header:'是否废弃',headerAlign:'center',
						           queryConfig:{ 
			   			              readOnly:false,
			   			                  data:localEnabled,
			   			                  type:'combobox',
			   			                 value:"否"},
				   		       formEditorConfig:{
				   			            newLine:true,
				   			           readOnly:false,
				   			               data:localEnabled,
				   			               type:'combobox',
				   			            colspan:3}},
				   	{field:'custidname',header:'承租人名称',visible:true,
				   		            queryConfig:{
						   		        newLine:true},
						   	   formEditorConfig:{
							       fieldVisible:false}},
				   	{field:'custid',header:'承租人名称',allowSort:true,visible:false,
				   		               renderer:function(e){return e.record.custname;},
				   		       formEditorConfig:{
				   			            newLine:true,
							               type:'queryinput',
							          textField:'name',
							         valueField:'value',
							         allowInput:false,
							       fieldVisible:true,
							              width:300,
							            colspan:3,
							             params:{xmlFileName:'common/custInfo.xml',custtype:'cust_type.cust'}}},
				   	{field:'clientname',header:'付款人',headerAlign:'center',
									queryConfig:{},
							   formEditorConfig:{
										colspan:3,
									    newLine:true,
									   readOnly:true}},
					{field:'factmoney',header:'到账金额',summary:true,dataType:"currency",headerAlign:'center',
									queryConfig:{
										   type:"double",
										  range:true},
							   formEditorConfig:{
										  vtype:'double',
										newLine:true,
									   readOnly:true}},
					{field:'factdate',header:'到账时间',headerAlign:'center',
								    queryConfig:{
										newLine:true,
										   type:'date',
										  range:true},
			                   formEditorConfig:{
							              vtype:'date',
							           readOnly:true}},   
					{field:'nowithmoney',header:'非业务金额',summary:true,dataType:"currency",headerAlign:'center',
							   formEditorConfig:{
										newLine:true}},
				   	{field:'hadmoney',header:'已核销金额',summary:true,dataType:"currency",headerAlign:'center',
							   formEditorConfig:{
									   readOnly:true}},
				   	{field:'mayopemoney',header:'可核销金额',summary:true,dataType:"currency",headerAlign:'center',
							   formEditorConfig:{
										newLine:true,
									   readOnly:true}},
					{field:'uploaddate',header:'上传时间',dateFormat:"yyyy-MM-dd HH:mm:ss",headerAlign:'center',
                                    queryConfig:{
		                                   type:'date',
		                                  range:true},
		                       formEditorConfig:{
				   		                   type:'date',
				   		               readOnly:true,
				   		                newLine:false,
				   		                 format:'yyyy-MM-dd HH:mm:ss'}},
				   	{field:'ownbank',header:'本方银行',headerAlign:'center',
				   			   formEditorConfig:{
								   	    newLine:true,
								   	   readOnly:true}},
				   	{field:'clientbank',header:'对方银行',headerAlign:'center',
									queryConfig:{},
							   formEditorConfig:{
									   readOnly:true}},
				   	{field:'ownaccount',header:'本方账户',headerAlign:'center',
							   formEditorConfig:{
										newLine:true,
									   readOnly:true}},
					{field:'clientaccnumber',header:'对方账号',headerAlign:'center',
									queryConfig:{
									    newLine:true},
							   formEditorConfig:{
									   readOnly:true}},
				   	{field:'ownaccnumber',header:'本方账号',headerAlign:'center',
					           formEditorConfig:{
								        newLine:true,
								       readOnly:true}},
				   	{field:'clientaccount',header:'对方账户',headerAlign:'center',
								    queryConfig:{},
							   formEditorConfig:{
									   readOnly:true}},
				   	{field:'ismaymoney',header:'可核销金额',visible:false,headerAlign:'center',
								    queryConfig:{ 
			   			              readOnly:false,
			   			                  data:localMayMoney,
			   			                  type:'combobox',
			   			          fieldVisible:true,
			   			             textField:"text",
			   			            valueField:"id",
			   			                 value:' and e.fact_money-e.no_with_money-nvl(cf.fundmoney,0)- nvl(cr.rent,0)-nvl(pro.process_amount,0)>0',
			   			                  text:'大于零'}},
				   	{field:'summary',header:'备注',headerAlign:'center',
			   				  formEditorConfig:{
				   		               newLine:true,
				   		                 width:350,
				   		               colspan:3,
				   		                height:55,
				   		                  type:'textarea'
				   	}}
				]
			});
		     
		});
});
var toolsArray =
  ['edit','-','importExcel','-'
          ,{
				html:'网银模板下载',
				plain:true,
				iconCls:'icon-addfolder',
				handler:function(miniTable, buttonText) {
					var fileTeplate=new fileTemplateConfig({
                    	 templateno:'F-201411002',
                    	 tableid:miniTable.config.id,
                         modelname:miniTable.config.title,
                         returntype:'file',
                         parames:{}
                         });
	               fileTeplate.createFile();
				}
			}
            ,'-'
            ,{
			html:'废弃',
			plain:true,
			iconCls:'icon-remove',
			handler:function(miniTable, buttonText) {
				mini.confirm("确认废弃选中数据行么？", "确认操作", function(buttonText) {
					var row = miniTable.getSelected();
					var param={};
					param.id=row.id;
					param.isdiuse=row.isdiuse;
					param.nowithmoney=row.nowithmoney;
					if ("ok" == buttonText) 
					{
						if (row.isdiuse !="0")//判断重复点击废弃按钮
						{
							/* 处理非废弃的   ajax start  */
							$.ajax({
								url:window.globalWebRoot + '/acl/delEbankInfo.acl',
								method:'post',
								data :param,
								failure:function(res) {

								},
								success:function(res) {
									if(res=="yes")
										mini.alert("操作成功!");
									else if (res=="process")
										mini.alert("不允许废弃,网银正在使用中...");
									else
										mini.alert("操作失败!");
								}
						  });
						/*ajax end */
					   }
						else{
								alert("该数据已是废弃状态不能重复点击废弃,请操作其他数据!");
								return false;
							}
						miniTable.load();//操作完刷新
						miniTable.deselectAll(false);
					}
				});
			}
		}
  ];
 function ShowIsdiuse(e)
 {
	var id_isdiuse=mini.getbyName("isdiuse").getValue();
	var id_procmoney= mini.getbyName("procmoney").getValue();  
	if (id_isdiuse =="0" && id_procmoney >0 )
	{
		mini.alert("网银正在使用中... 不允许废弃!");
		mini.getbyName("isdiuse").setValue("1");
	}
 }
</script>
</head>
<body>
</body>
</html>