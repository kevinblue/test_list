<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="CustomerCodingMaintenance" text="客户编码维护"/></title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id : 'table_paymentlog',
				renderTo : "table_paymentlog_table1",
				width : globalClientWidth,
				height : globalClientHeight,
				title : 'CBS付款日志信息',
				iconCls : 'icon-node',
				//editRemoteOperUrl:getRootPath()+"/acl/editVoucherCust.acl",
				multiSelect : false,
				hiddenQueryArea : false,//是否将查询区域折叠起来
				queryButtonNewLine:true,
				queryButtonColSpan:6,
				showPager:true,
				remoteOper: true,
				lazyLoad:false,
				loadMode:'ajax',
				validateForm:updateFundCharge,
				params:{},
				entityClassName : 'com.tenwa.leasing.entity.fund.ContractFundPaymentInterfaceLog',
				xmlFileName : '/eleasing/jsp/fund/fund_payment/payment_interface_log.xml',
				tools : ['edit','-',						
				         {
  		   				html:'传输数据',
  		 					plain : true,
  		 					handler : function(miniTable, buttonText) {
  		 						var row = miniTable.getSelected();
  		 						var  logid=row.id;
  		 						var message='';
  		 						if(row.paymentstatus=="S"){
  		 							mini.alert("付款已成功，不允许再次发送！");//sendPaymentDate.acl
  		 							return false;
  		 						}
  		 						if(row.paymentstatus=="A"){
  		 							mini.alert("传输已成功，不允许再次发送！");//sendPaymentDate.acl
  		 							return false;
  		 						}
  		 						if(row.paymentstatus=="F"){
  		 							mini.alert("付款失败，不允许再次发送！请线下联系。");//sendPaymentDate.acl
  		 							return false;
  		 						}
  		 					  var paymentaccounts=row.paymentaccounts;
    		 				   if(getLength(paymentaccounts)>35){
    		 					  mini.alert("付款银行帐号不能超过35个字符或11个汉字");
    		 					 	 return false;
    		 				  	} 
  		 					  var depositbankname=row.depositbankname;
   		 					   if(getLength(depositbankname)>90){
   		 							  mini.alert("开户行不能超过90个字符或30个汉字");
   		 					 	 return false;
   		 				  		} 
   		 					  var depositaccountsname=row.depositaccountsname;
		 					   if(getLength(depositaccountsname)>90){
		 							  mini.alert("开户行账户名不能超过90个字符或30个汉字");
		 					 	 return false;
		 				  		} 
		 					  var depositaccounts=row.depositaccounts;
			 				   if(getLength(depositaccounts)>35){
			 						 mini.alert("收款帐号不能超过35个字符或11个汉字");
			 					 	 return false;
			 				  	} 
  		 					  var depositbanktype=row.depositbanktype;
  		 					   if(getLength(depositbanktype)>5){
  		 							 mini.alert("银行简称不能超过5个字符或2个汉字");
  		 					 	 return false;
  		 				  		}   		 					   
  		 					  var depositprovince=row.depositprovince;
		 					   if(getLength(depositprovince)>20){
		 							 mini.alert("省份不能超过20个字符或6个汉字");
		 					 	 return false;
		 				  		} 
		 					  var depositcity=row.depositcity;
 		 					   if(getLength(depositcity)>30){
 		 							mini.alert("市不能超过30个字符或10个汉字");
 		 					 	 return false;
 		 				  		}  		 					   
  		 					   var banknumber=row.banknumber;
  		 				       if(getLength(banknumber)>10){
	 							  mini.alert("收款方银行号不能超过10个字符");
	 					 	     return false;
	 				  		   } 
  		 				    var unionbanknumber=row.unionbanknumber;
		 				       if(getLength(unionbanknumber)>30){
	 							   mini.alert("收款方银行联行号不能超过30个字符");
	 					 	     return false;
	 				  		   }  		 						  		 				
  		 						$.ajax({
  									url : getRootPath()+ "/eleasing/sendPaymentDate.acl",
  									type : "post",
  									cache : false,
  									data : {logid:logid},
  									async : false,
  									success : function(data) {
  										message=data;
  									}
  									});
  		 						mini.alert(message);
  		 						miniTable.reload();
  		 						
  		 					}
  		 					
  		 				},'-',
  		 				 {
  	  		   				html:'更新付款状态',
  	  		 					plain : true,
  	  		 					handler : function(miniTable, buttonText) {
  	  		 					var row = miniTable.getSelected();//updatePaymentStatus
  	  		 					var message='';
  	  		 				$.ajax({
									url : getRootPath()+ "/eleasing/updatePaymentStatus.acl",
									type : "post",
									cache : false,
									data : {},
									async : false,
									success : function(data) {
										message=data;
									}
									});
  	  		 					mini.alert(message);
  	  		 					miniTable.reload();
  	  		 				}
  		 				
  		 				}
				         ],
				columns : [ 
				    {type : 'indexcolumn'},
				   	{type : 'checkcolumn'},  
				   	{field : 'id',header : 'id',visible:false},
				   	{field : 'erppaymentid',header : 'erppaymentid',visible:false},
				   	{field : 'numuuid',header : 'numuuid',visible:false},				   
				   	{field : 'projname',header : '项目名称',formEditorConfig:{readOnly:true},queryConfig:{}},
				   	{field : 'paymentcontractid',header : '合同编号',formEditorConfig:{readOnly:true},queryConfig:{}},
				   	{field : 'paymentprocessid',header : '流程编号',formEditorConfig:{readOnly:true,fieldVisible:false}},
				   	{field : 'paymentmethodtypeid',header : '结算方式',formEditorConfig:{readOnly:true,fieldVisible:false}},					
				   	{field : 'amount',header : '交易金额',formEditorConfig:{readOnly:true,newLine:true}},
				   	{field : 'paymenttypeid',header : '付款合同类型',visible:false},
				   	{field : 'paymenttypeidname',header : '合同类型',formEditorConfig:{fieldVisible:false},
				   	queryConfig:{
				   		showNullItem:true,
						visible:true,
						type:'combobox',
						textField:'text',
						valueField:'id',
						data:[{id:'保理',text:'保理'},{id:'租赁',text:'租赁'}]
				   	}				   	
				   	},
				   	{field : 'paymentstatus',header : '付款状态标识符',visible:false,formEditorConfig:{readOnly:true,newLine:false,fieldVisible:false}},
				   	{field : 'paymentstatusname',header : '付款状态',formEditorConfig:{readOnly:true,newLine:false}},
				   	{field : 'errorreason',header : '错误原因',formEditorConfig:{readOnly:true,newLine:true}},
	
				   	{field : 'feetypename',header : '费用类型',formEditorConfig:{readOnly:true,newLine:false}},
				   	{field : 'lastupdate',header : '实际付款日',visible:true,dateFormat : "yyyy-MM-dd",
				   		formEditorConfig:{
				   			newLine : true,
							type : 'date',
							labelWidth : 100,
							format : 'yyyy-MM-dd'
							//timeFormat : 'HH:mm:ss',//弹出的日历控件上，时间的格式化形式
							//showTime : true 
				   			}
				   	},
				   	{field : 'paymentaccounts',header : '付款方银行账号',formEditorConfig:{newLine:true,required: true}},
				   	
				  	{field : 'depositbankname',header : '收款方开户行',formEditorConfig:{newLine:false,required: true},queryConfig:{newLine:true}},
				   	{field : 'depositaccountsname',header : '收款方银行账户名称',formEditorConfig:{newLine:true,required: true},queryConfig:{newLine:false}},
				   	{field : 'depositaccounts',header : '收款方银行账号',formEditorConfig:{newLine:false,required: true}},
				   	{field : 'depositbanktype',header : '收款方银行简称',formEditorConfig:{newLine:true,required: true}},
				   	{field : 'depositprovince',header : '收款方开户行省',formEditorConfig:{newLine:false,required: true}},
				   	{field : 'depositcity',header : '收款方开户行市',formEditorConfig:{newLine:true,required: true}},
				   	
				  	{field : 'priorityflag',header : '加急标识',
				   		formEditorConfig:{	
							type:'combobox',
							required: true,
							textField:'text',
							valueField:'id',
							data:[{id:'Y',text:'Y'},{id:'N',text:'N'}]
						},
						queryConfig:{
							showNullItem:true,
							visible:true,
							type:'combobox',
							textField:'text',
							valueField:'id',
							data:[{id:'Y',text:'Y'},{id:'N',text:'N'}]
						}
				   	},
				   	{field : 'banknumber',header : '收款方银行号',formEditorConfig:{newLine:true,required: true}},
				   	{field : 'unionbanknumber',header : '收款方联行号',formEditorConfig:{newLine:false,required: true}},
			
				   	{field : 'creator',header : '发起人ID',visible:false},
				   	{field : 'creatorid',header : '付款发起人',formEditorConfig:{readOnly:true,newLine:true}},
				   	{field : 'createdate',header : '发起时间',
				   		dateFormat : "yyyy-MM-dd HH:mm:ss",
						formEditorConfig : {
							newLine : false,
							readOnly:true,
							type : 'date',
							readOnly : true,
							labelWidth : 100,
							width : '100%',
							format : 'yyyy-MM-dd HH:mm:ss',
							required : false
						}},					
				   	{field : 'modificator',header : '修改人',visible:false},
				   	{field : 'modificatorid',header : '修改人',formEditorConfig:{readOnly:true,newLine:true}},
				   	{field : 'modifydate',header : '修改时间',
				   		dateFormat : "yyyy-MM-dd HH:mm:ss",
						formEditorConfig : {
							newLine : false,
							readOnly:true,
							type : 'date',
							readOnly : true,
							labelWidth : 100,
							width : '100%',
							format : 'yyyy-MM-dd HH:mm:ss',
							required : false
						}},
						{field:'bfrealname',header:'上传文件信息', width:'250px',
							formEditorConfig:{fieldVisible:false},
					   		renderer: function(e){
					   		  var fileHtmlTd = '';
					   		  //文件id
					   		 var idStr = e.record.bffileid;
					   		if(idStr == null || idStr == undefined || idStr == ''){
					   			return "还未上传文件！";
					   		}
					   		var idArray = idStr.split(",");
					   		//文件名
					   	     var filenameStr = e.record.bffilename;
					   	      var filenameArray = filenameStr.split(",");
					   	    //上传时间
					   	    var createdateStr = e.record.bfcreatedate;
					   	    var createdateArray;
					   	    if(typeof(createdateStr)== 'object'){
					   	    	var date=tenwa.toDate(createdateStr+"","yyyy-MM-dd hh:mm:ss");
					   	    	var ss=tenwa.toChar(date,"yyyy-MM-dd hh:mm:ss");
					   	    	createdateArray = ss.split(",");
					   	    }else{
					   		    createdateArray = createdateStr.split(",");
					   	    }
					   	    //上传人
					   	    var realnameStr = e.record.bfrealname;
					   	    var realnameArray = realnameStr.split(",");
					   	     //拼table
					   	     var renderHtml1 = "<table style='border:0px solid #FFF;'>";
					   	     var renderHtml2 = "</table>";
					   	     for(var i=0;i<filenameArray.length;i++){
					   	   	 var fnStr = filenameArray[i];
						   	   	fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
			   	                     "<td style='border:0px solid #FFF;'>"+
			   	                             "<a href='javascript:void(0);' onclick='removeUploadDdrFilebyId(\""+ idArray[i] + "\");'style='color:red;'>"+"[删除]"+"</a>"+
			   	                     "</td>"+
			   	                     "<td style='border:0px solid #FFF;'>"+
			                                  "<a href='javascript:void(0);' onclick='downloadDdrFile(\""+ idArray[i] + "\")'>"+"["+fnStr+"]"+"</a>"+
			                         "</td>"+
			                         "<td style='border:0px solid #FFF;'>"+
			                                  "【"+   realnameArray[i]+"】"+
			                         "</td>"+
			                         "<td style='border:0px solid #FFF;'>"+
			                                  "【"+ createdateArray[i]+"】"+
			                         "</td>"+
			   	                "</tr>";
					   	   	
					   	             } 
					   	     return renderHtml1+fileHtmlTd+renderHtml2; 
					   		}},					   		
					   		{
								field: 'create', 
								header: '操作',
								formEditorConfig:{fieldVisible:false},
							    renderer:function(e){
							    		var id = e.record.id;
										return "<a href='javascript:void(0);' onclick='upDdrFileForPayMentLog(\"" + id+"\",\""+ e.record.paymentprocessid+ "\")'>上传文件</a>";    	
							}}
				   	
				]
			});
		});
	});
function updateFundCharge(miniTable, miniForm, editFormItemOperFlag, addIndex){
	debugger;
	var message="";
	var row = miniTable.getSelected();	
	console.info(row);
	var  lastDate=getMiniEditFormField("table_paymentlog.lastupdate").getText();
	var  numuuid=row.numuuid;
	$.ajax({
		url : getRootPath()+ "/eleasing/updateFundCharge.acl",
		type : "post",
		cache : false,
		data : {lastDate:lastDate,numuuid:numuuid},
		async : false,
		success : function(date) {
			message=date;
		}
		});
	if(message=="成功"){
		miniTable.reload();
		return true;
	}else{
		mini.alert(message);	
		miniTable.reload();
		return false;
	}
			
}	
	
//删除附件
function removeUploadDdrFilebyId(uploadAttachmentFileDetailId) {
	mini.confirm("确认删除？", "删除？", function(action){
		if(action=="ok"){
			var ids=[];
				ids.push(uploadAttachmentFileDetailId);
		    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除合同 请稍等...'});
			var url="/leasing/template/dropCreateFile.action";
			var param=[];
			param["ids"]=ids+"";
			param["isAttachment"]=true;//连带级联操作删除自定义方法
			ajaxRequest({
				url:getRootPath()+url,
				method:'POST',
				success:function(rs){
				var message= rs.responseText;
					message=message.replace(/(^\s+)|(\s+$)/g, ""); 
					mini.unmask(document.body);
					mini.get("table_paymentlog").reload();
	        		loadcustomworkflowattachment();
					mini.alert(message);
				},
				async:false,
				failure:function(res){
					mini.unmask(document.body);
				},
				params:param
			});
		}else{
			return;
		}
	});
}
function getLength(str)   
{  
    var realLength = 0;  
    for (var i = 0; i < str.length; i++)   
    {  
        charCode = str.charCodeAt(i);  
        if (charCode >= 0 && charCode <= 128)   
        realLength += 1;  
        else   
        realLength += 2;  
    }  
    return realLength;  
};

//上传附件
function upDdrFileForPayMentLog(id,flowUnid){
	var filekey = id;
	var attachmentParams=getAttachmentParams("BusinessAdjustment.01","${currentProcessInstanceDBID}");
	var uploadutil = new uploadUtil({
			attachmentParams:attachmentParams,
			url : '/leasing/file/uploadFile.action',
			tableid : "due_diligence_report",
		    modelname : "PayMentLog",
		    parames : {
					flowUnid : flowUnid,
					filekey : filekey　　　
				},
				jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}
function reloadcustcontactfile(message){
	mini.alert(message);
	loadcustomworkflowattachment();
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
	mini.get("table_paymentlog").reload();
	
}



</script>
</head>
<body>
	<div id="mini_test_form">
	        <div id="table_paymentlog_table1"></div>
	</div>

</body>
</html>