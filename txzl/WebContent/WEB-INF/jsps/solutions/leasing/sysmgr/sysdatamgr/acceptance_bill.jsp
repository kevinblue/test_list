<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>承兑汇票信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<body style="overflow:hidden;"> 
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				showPager: true,
				pageSize: 20,
				title:'承兑汇票信息',
				remoteOper : true,
				entityClassName : 'com.tenwa.leasing.entity.base.AcceptanceBill',
				multiSelect: false,
				xmlFileName:"/eleasing/jsp/sysmgr/sysdatamgr/acceptance_bill.xml",
				tools : [ 'add', '-', 'edit', '-', 'remove'],
				columns: [
					{type : 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: '主键',visible:false,formEditorConfig : {visible:false}},
					{field: 'billtype', header: '票据类型',
						visible: true,
						queryConfig:{
							type : 'combobox',
							fieldVisible : true,
							textField : "text",
							valueField : "text",
							showNullItem :true,
							data:[{text:'银行承兑汇票'},{text:'商业承兑汇票'}]
						},
						formEditorConfig:{
							type : 'combobox', 
							required : true, 
							multiSelect : false, 
							valueField : 'text', 
							textField : 'text', 
							width : 200,
							labelWidth : 100,
							fieldVisible : true,
							data:[{text:'银行承兑汇票'},{text:'商业承兑汇票'}]
					    }
					},{field: 'billnum', header: '票据编号',
						visible: true,
						formEditorConfig:{
							type : 'text', 
							required : true, 
							width : 200,
							labelWidth : 100,
							fieldVisible : true
					    }
				    },{field: 'newbillnum', header: '新票据编号',
						visible: true,
						formEditorConfig:{
							newLine:true,
							type : 'text', 
							width : 200,
							labelWidth : 100,
							fieldVisible : true
					    }
				    },{field: 'oldbillnum', header: '旧票据编号',
						visible: true,
						formEditorConfig:{
							type : 'queryinput', 
							width : 200,
							labelWidth : 100,
							fieldVisible : true,
							valueField : 'billnum', 
							textField : 'billnum', 
							allowInput : false,
							params : {
								xmlFileName : '/eleasing/jsp/sysmgr/sysdatamgr/acceptance_bill.xml',
							}
					    }
				    },{field: 'issuedate', header: '出票日期',
						visible: true,
						dateFormat : "yyyy-MM-dd",
						formEditorConfig:{
							newLine:true,
							required : true,
							type : 'date',
							format : 'yyyy-MM-dd'
					    }
				    }, {field: 'duedate', header: '到期日期',
						visible: true,
						dateFormat : "yyyy-MM-dd",
						formEditorConfig:{
							required : true,
							type : 'date',
							format : 'yyyy-MM-dd'
					    }
				    },
				    
				    {field: 'payeename', header: '收款人全称',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							newLine :true,
							type : 'text'
					    }
				    },
				    {field: 'draweename', header: '出款人全称',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },
				   {field: 'acceptorname', header: '承兑人名称',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							newLine :true,
							type : 'text'
					    }
				    },{field: 'acceptornumber', header: '承兑人账号',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'acceptorbank', header: '承兑人开户银行',
						visible: true,
						formEditorConfig:{
							newLine :true,
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'faceamount', header: '金额类型',
						visible: true,
						formEditorConfig:{
							required :true,
							width : 200,
							labelWidth : 100,
							type : 'float',
							vtype : 'float'
					    }
				    },{field: 'billresource', header: '票据来源',
						visible: true,
						queryConfig:{
							type : 'combobox',
							fieldVisible : true,
							textField : "text",
							valueField : "text",
							showNullItem :true,
							data:[{text:'1'},{text:'2'}]
						},
						formEditorConfig:{
							type : 'combobox', 
							required : true, 
							newLine :true,
							valueField : 'text', 
							textField : 'text', 
							width : 200,
							labelWidth : 100,
							fieldVisible : true,
							data:[{text:'1'},{text:'2'}]
					    }
					},{field: 'buydate', header: '买入日期',
						visible: true,
						dateFormat : "yyyy-MM-dd",
						formEditorConfig:{
							required : true,
							type : 'date',
							format : 'yyyy-MM-dd'
					    }
				    },{field: 'currentholder', header: '当前持票人',
						visible: true,
						formEditorConfig:{
							newLine :true,
							required : true,
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'previousholder', header: '上一前手',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'remoteholder', header: '前手',
						visible: true,
						formEditorConfig:{
							newLine :true,
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'buycontractnum', header: '买入时的合同号',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'buynoticenum', header: '买入时的通知单号',
						visible: true,
						formEditorConfig:{
							newLine :true,
							width : 200,
							labelWidth : 100,
							type : 'text'
					    }
				    },{field: 'buytransitdays', header: '买入时的在途天数',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'text',
							vtype : 'int'
					    }
				    },{field: 'buydiscountrate', header: '买入时的贴现率',
						visible: true,
						formEditorConfig:{
							newLine :true,
							width : 200,
							labelWidth : 100,
							type : 'float',
							vtype : 'float'
					    }
				    },{field: 'buydiscountinterest', header: '买入时的贴现利息',
						visible: true,
						formEditorConfig:{
							width : 200,
							labelWidth : 100,
							type : 'float',
							vtype : 'float'
					    }
				    },{field: 'memo', header: '备注',
						visible: true,
						formEditorConfig:{
							newLine : true,
							type : 'textarea',
							width : 400,
							colspan : 3,
							height : 55,
					    }
				    },
				    {field: 'filename', header: '上传文件信息',width: 320,visible: true,
				    	formEditorConfig:{ fieldVisible:false},
				     	renderer: function(e){
							return getPaydownload(e);
						}
	            },
		     {
				field: 'create', 
				header: '操作',
				formEditorConfig:{ fieldVisible:false},
			    renderer:function(e){
			    	var id = e.record.id;
					return "<a href='javascript:void(0);' onclick='upFile(\"" + id + "\")'>上传附件</a>";    	
			}}
				]
				
			});
		});
	});
   function getPaydownload(e){
		  var fileHtmlTd = '';
		  //文件id
		 var idStr = e.record.fileid;
		  //console.info(typeof idStr);
		if(idStr == null || idStr == undefined || idStr == ''){
			return "还未上传附件！";
		}
		var idArray = idStr.split(",");
		//文件名
	     var filenameStr = e.record.filename;
	      var filenameArray = filenameStr.split(",");
	    //上传时间
	    var createdateStr = e.record.createdate;
	    var createdateArray;
	    if(typeof(createdateStr)== 'object'){
	    	var date=tenwa.toDate(createdateStr+"","yyyy-MM-dd hh:mm:ss");
	    	var ss=tenwa.toChar(date,"yyyy-MM-dd hh:mm:ss");
	    	createdateArray = ss.split(",");
	    }else{
		    createdateArray = createdateStr.split(",");
	    }
	    //上传人
	    var realnameStr = e.record.realname;
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
		mini.confirm("确认删除合同 附件？", "删除？", function(action){
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
			        		mini.get("id_tasks_table1").reload();
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
	function upFile(id) {
		var filekey = id;
		var uploadutil = new uploadUtil({
				url : '/leasing/file/uploadFile.action',
				tableid : "contract_word",
			    modelname : '承兑汇票信息附件',
			    parames : {
						filekey : filekey　　　
					},
					jscallback : 'reloadcustcontactfile2'
			});
		uploadutil.createFileAndShow();
	}

		function downloadFile(Id) {
			attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
		} 
		
		function reloadcustcontactfile2(message){
			mini.alert(message);
			mini.get("id_uploadfile").hide();
			mini.unmask(document.body);
			mini.get("id_tasks_table1").reload();
		}
   
</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
</body>
</html>