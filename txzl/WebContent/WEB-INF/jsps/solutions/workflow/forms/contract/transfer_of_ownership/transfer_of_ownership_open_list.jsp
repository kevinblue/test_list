<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>所有权转移</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
var ifonlyread =  window.isCompletedTask||false;
var currentClientWidth = document.documentElement.clientWidth;
var currentClientHeight = document.documentElement.clientHeight;
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "transfer_of_ownership",
		renderTo: "id_table_container_contract_change",
		width: currentClientWidth,
		height: currentClientHeight,
		lazyLoad: false,
		title: "所有权转移确认书",
		remoteOper : false,
		showPager: true,
		pageSize: 20,
		queryButtonColSpan: 6,
		queryButtonNewLine:true,
		xmlFileName: '/eleasing/workflow/contract/contract_change/contract_change_list.xml',
		tools:[
			   {
				html:'生成所有权转移确认书',
				plain:true,
				iconCls:'icon-addfolder',
				handler:function(miniTable, buttonText) {
					var checkedRowDatas = miniTable.getSelecteds();
					if(0 == checkedRowDatas.length){
						mini.alert("请选择要导出资产所有权转移确认书的记录！！");
						return;
					}
					var row = checkedRowDatas[0];
					var leasform = row.leasform;
					var params = {};
					params['contract_id'] = row.contract_id;
					//date:当前时间
					params['date.nowTime']= new Date().format("yyyy 年 MM 月 dd 日");
					if(leasform=='lease_form2'){
						var startdate=tenwa.toDate(row.startdate,'yyyy-MM-dd');
						var year = startdate.getFullYear();
						var month = startdate.getMonth()+ 1;
						var day = startdate.getDate();
						//label_0,label_1,label_2
						params['label.year']= year;
						params['label.month']= month;
						params['label.day']= day;
					  	var fileTeplate=new fileTemplateConfig({						
							templateno : 'F-201703004',
							tableid:miniTable.config.id,
							modelname:miniTable.config.title,
							returntype:'listtonewpage',
							parames :params
						});
						fileTeplate.createFile();
				     }else if(leasform=='lease_form1'){																		
					  	var fileTeplate=new fileTemplateConfig({						
							templateno : 'F-201703003',
							tableid:miniTable.config.id,
							modelname:miniTable.config.title,
							returntype:'listtonewpage',
							parames :params
						});
						fileTeplate.createFile();
					}else{
						mini.alert('没有对应模板！！！');
						return;
					}
				}
			},'-',{
				html:'生成授权抵押确认函',
				plain:true,
				iconCls:'icon-addfolder',
				handler:function(miniTable, buttonText) {
					var checkedRowDatas = miniTable.getSelecteds();
					if(0 == checkedRowDatas.length){
						mini.alert("请先选择1条记录！");
						return;
					}
					var row = checkedRowDatas[0];
					var params = {};
					params['contractid'] = row.contract_id;
					params['label.custname'] = row.cust_name;
					params['label.currentdate']= new Date().format("yyyy年 MM月 dd日");
					params['label.contractnumber']= row.contract_number;
				  	var fileTeplate=new fileTemplateConfig({						
						templateno : 'F-201708010',
						tableid:miniTable.config.id,
						modelname:miniTable.config.title,
						returntype:'listtonewpage',
						parames :params
					});
					fileTeplate.createFile();
				}
			}         
		],
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field:'projid', header:'项目编号'},	
			{field: 'cust_name', header: '客户名称',width:135,queryConfig:{width:165}},
			{field: 'project_name', header: '项目名称',queryConfig:{width:165}},
			{field: 'leasform', header: '租赁形式', visible: false},
			{field: 'contract_number', header: '业务合同编号',width:110,queryConfig:{width:165,newLine:false}},			
			{field: 'contract_id', header: '合同编号',width:110},
			{field:'cleanleasemoney',dataType:"currency",header:'合同金额'},
			{field: 'industry_type', header: '内部行业',width:80},
			{field: 'projmanagename', header: '项目经理',width:80},
			{field: 'contractstatus', header: '项目状态',width:80},
			{field: 'card_no', header: '身份证/注册号/统一社会信用代码',width:175},			
			{field: 'department', header: '出单部门'},
			{field: 'startdate', header: '起租日',visible: false},
			{field: 'filename', header: '上传附件信息',width: 320,visible: true,
		    	formEditorConfig:{ fieldVisible:false},
		     	renderer: function(e){
					return getPaydownloadownership(e);
				}
        	},
     		{
			field: 'create', 
				header: '上传附件',
				formEditorConfig:{ fieldVisible:false},
			    renderer:function(e){
			    	var id = e.record.id;
					return "<a href='javascript:void(0);' onclick='upFileownership(\"" + id + "\")'>上传附件</a>";    	
				}
			}
		 ]
	  });
   });
});
function getPaydownloadownership(e){
	  var fileHtmlTd = '';
	  //文件id
	 var idStr = e.record.fileidv;
	  //console.info(typeof idStr);
	if(idStr == null || idStr == undefined || idStr == ''){
		return "还未上传相关附件！";
	}
	var idArray = idStr.split(",");
	//文件名
   var filenameStr = e.record.filenamev;
    var filenameArray = filenameStr.split(",");
  //上传时间
  var createdateStr = e.record.createdatev;
  var createdateArray;
  if(typeof(createdateStr)== 'object'){
  	var date=tenwa.toDate(createdateStr+"","yyyy-MM-dd hh:mm:ss");
  	var ss=tenwa.toChar(date,"yyyy-MM-dd hh:mm:ss");
  	createdateArray = ss.split(",");
  }else{
	    createdateArray = createdateStr.split(",");
  }
  //上传人
  var realnameStr = e.record.realnamev;
  var realnameArray = realnameStr.split(",");
   //拼table
   var renderHtml1 = "<table style='border:0px solid #FFF;'>";
   var renderHtml2 = "</table>";
   for(var i=0;i<filenameArray.length;i++){
 	 var fnStr = filenameArray[i];
 	fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
 	                     "<td style='border:0px solid #FFF;'>"+
 	                             "<a href='javascript:void(0);' onclick='removeUploadFilebyId(\""+ idArray[i] + "\");'style='color:red;'>"+"[删除]"+"</a>"+
 	                     "</td>"+
 	                     "<td style='border:0px solid #FFF;'>"+
                                "<a href='javascript:void(0);' onclick='downloadFile(\""+ idArray[i] + "\")'>"+"["+fnStr+"]"+"</a>"+
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
}
//删除附件
function removeUploadFilebyId(uploadAttachmentFileDetailId) {
	mini.confirm("确认删除该附件？", "删除？", function(action){
		if(action=="ok"){
			$.ajax({
				cache : false,
				async : false,
		        type: "post",
		        dataType: "JSON",
		        url : getRootPath()+ "/acl/ContractEnclosureMarkDelect.acl",
		        data: {uploadAttachmentFileDetailId:uploadAttachmentFileDetailId},
		        success: function (data) {
		        	if(data){
		        		mini.alert("删除成功！");
		        		mini.get("transfer_of_ownership").reload();
		        		return;
		        	}else{
		        		mini.alert("删除失败！");
		        		return;
		        	}
		        	
		        }
		    })
		}else{
			return;
		}
	});
}

//上传附件
function upFileownership(id) {
	var filekey = id;
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
		    modelname : '所有权转移附件',
		    parames : {
					filekey : filekey　　　
				},
				jscallback : 'reloadcustcontactfileownership'
		});
	uploadutil.createFileAndShow();
}

	function downloadFile(Id) {
		attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
	} 
	
	function reloadcustcontactfileownership(message){
		mini.alert(message);
		mini.get("id_uploadfile").hide();
		mini.unmask(document.body);
		mini.get("transfer_of_ownership").reload();
	}
</script>
</head>
<body>
<div id="id_table_container_contract_change"></div>
</body>
</html>
