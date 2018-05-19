<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>融资租赁登记</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">

function importMiniTableCallBack(message, tableid) {
	var info = eval("(" + message + ")");
	mini.alert(info.message);
	mini.get("id_minitableimport").hide();
	mini.unmask(document.body);
	mini.get(tableid).reload();
	
	var tabledate = info.tabledate;
	//console.info(info);
	if ("" != tabledate) {
		var grid = mini.get(tableid);
		grid.set({
			data : mini.decode(tabledate)
		});
	}		
}

	jQuery(function() {
		seajs.use(
		[ "js/apcomponent/aptable/aptable.js" ],
		function(ApTable) {
			new ApTable(
					{
						id : 'table_finance_lease_registration_info_id',
						renderTo : "id_table_render_table1",
						width : globalClientWidth,
						height : globalClientHeight,
						iconCls : 'icon-node',
						hiddenQueryArea : false,
						multiSelect : true,
						importTargetClass : 'com.tenwa.leasing.entity.financeleaseregistration.FinanceLeaseRegistration',
						importDataIndex : '2',
						importHeaderIndex : '1',
						isClickLoad : false,
						editFormPopupWindowWidth : 800,
						editFormPopupWindowHeight : 300,
						title : '融资租赁登记',
						remoteOper : true,
						pageSize : 15,
						showPager : true,
						lazyLoad : false,
						loadMode : 'ajax',
						queryButtonColSpan : 6,
						queryButtonNewLine : true,
						xmlFileName : '/eleasing/jsp/finance_lease_registration/finance_lease_registration_list.xml',
						tools : [ 'add', '-', 'edit', '-','remove', '-', 'importExcel','-', 'exportExcel' ],
						entityClassName : 'com.tenwa.leasing.entity.financeleaseregistration.FinanceLeaseRegistration',
					    frozenStartColumn : 0,
						frozenEndColumn : 2,
						columns : [ {
							type : 'indexcolumn'
						}, {
							type : 'checkcolumn'
						}, {
							field : 'id',
							header : '编号',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							visible : false,
							formEditorConfig : {
								readOnly : true,
								fieldVisible : false
							}
						},{
							field : 'contractname',
							header : '租赁合同编号',
							headerAlign : 'center',
							width : 100,
							queryConfig:{width:200},
							visible : true,
							formEditorConfig : {
								/* readOnly : true, */
								fieldVisible : false,
								fillFromFieldName : 'contractid'
							}
						},{
							field : 'contractid',
							header : '租赁合同编号',
							visible : false,
							queryConfig : {
								labelWidth : 100,
								width : 200
							},
							formEditorConfig : {
								newLine : true,
								type : 'queryinput',
								required : true,
								textField : 'contractname',
								valueField : 'contractid',
								fieldVisible : true,
								onSelect : function(
										$me,queryInput,rowData) {
									mini.getbyName("lesseename").setValue(rowData.custname);
									mini.getbyName("lesseeid").setValue(rowData.custid);
									mini.getbyName("projectname").setValue(rowData.projname);
									mini.getbyName("projid").setValue(rowData.value);
								},
								params : {
									xmlFileName : '/eleasing/jsp/finance_lease_registration/finance_contractid.xml'
								}
							}
						},{
							field : 'lesseeid',
							header : '客户名称',
							visible : false,
							width:300,
							formEditorConfig : {}
						},{
							field : 'lesseename',
							header : '承租人名称',
							visible : true,
							queryConfig:{width:200},
							width:300,
							queryConfig : {width:200},
							formEditorConfig : {
								required : false,
								fieldVisible : true,
								readonly : true
							}
						},{
							field : 'projid',
							header : '项目名称',
							visible : false,
							width:300,
							formEditorConfig : {}
						},{
							field : 'projectname',
							header : '项目名称',
							visible : true,
							queryConfig:{width:200},
							width:300,
							queryConfig : {width:200},
							formEditorConfig : {
								newLine : true,
								required : false,
								fieldVisible : true,
								readonly : true
							}
						}, {
							field : 'preparer',
							header : '填表人',
							visible : true,
							formEditorConfig : {
								required : true,
								fieldVisible : true
							}
						}, {
							field : 'serialnumber',
							header : '登记证明编号',
							width:300,
							visible : true,
							formEditorConfig : {
								newLine : true,
								required : true,
								fieldVisible : true
							}
						}, {
							field : 'recorddate',
							header : '登记日期',
							visible : true,
							dateFormat : "yyyy-MM-dd",
							formEditorConfig : {
								type : 'date',
								labelWidth : 110,
								defaultValue : mini.formatDate(new Date(),"yyyy-MM-dd"),
								width : '100%',
								format : 'yyyy-MM-dd',
								onkeyup:'getdateofregistration'
							}
						}, {
							field : 'deadline',
							header : '登记期限(年)',
							visible : true,
							formEditorConfig : {
								type : 'text',
								newLine : true,
								required : true,
								onkeyup:'getdateofregistration',
								fieldVisible : true
							}
						},{
							field : 'dateofregistration',
							header : '登记到期日',
							headerAlign : 'center',
							visible : true,
							width : 100,
							dateFormat : "yyyy-MM-dd",
							formEditorConfig : {
								type : 'text',
								labelWidth : 100,
								width : '100%',
								format : 'yyyy-MM-dd'
								}
						}, {
							field : 'archivenumber',
							header : '归档号',
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : true,
								type : 'text',
								labelWidth : 100,
								width : '100%',
								required : false
							}
						},
						{
							field : 'rentamount',
							header : '租金金额',
							visible : true,
							formEditorConfig : {
								required : true,
								vtype:'float',
								fieldVisible : true
							}
						},
						{
							field : 'propertydescription',
							header : '租赁财产描述',
							headerAlign : 'center',
							visible : true,
							width : 100,
							formEditorConfig : {
								newLine : true,
								colspan : 3,
								width : "100%",
								type : 'textarea'
							}
						}, {field: 'filename', header: '租赁物清单',width: 300,
							formEditorConfig : {
								fieldVisible:false
							},
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
		}} ]
					});
		});
	});
//计算登记到期日
 function getdateofregistration(){
	 var date = new Date(Date.parse(mini.getbyName("recorddate").getText(),"yyyy-MM-dd"));
	 var year = $("input[name=deadline]").val();
	 //var year1 = mini.getbyName("deadline").getValue();
	 date.setFullYear((date.getFullYear()+Number(year)));
	 date.setDate((date.getDate()-1));
	 var registration = date.toLocaleDateString();
	 mini.getbyName("dateofregistration").setValue(registration);	
};
function getPaydownload(e){
	  var fileHtmlTd = '';
	  //文件id
	 var idStr = e.record.fileid;
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
						       "<a href='javascript:void(0);' onclick='previewFilebyId(\""+ idArray[i]+"\",\""+fnStr+"\");'style='color:blue;'>"+"[预览]"+"</a>"+
						"</td>"+
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
		        		mini.get("table_finance_lease_registration_info_id").reload();
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
			tableid : "lease_list",
		    modelname : '租赁物清单',
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
		mini.get("table_finance_lease_registration_info_id").reload();
	}
	
	function previewFilebyId(id,filename){
		var filename=filename.substring(filename.lastIndexOf('.') + 1);
		var fileExtension = filename.toLowerCase();
		
		if(fileExtension=="jpg"||fileExtension=="jpeg"||fileExtension=="png"||fileExtension=="bmp"||fileExtension=="gif"||fileExtension=="pdf"){
			showpicuture(id);
		}else{
			$.ajax({
		        url: getRootPath()+"/acl/qeryCustfile.acl",
		        type: "post",
		        cache: false, 
		        data :{"filedict":id,"previewtype":"second"},
		        async : false,
		        success: function (text) {
				     var str=text;
				   	 testwin= open("", "testwin","status=no,menubar=yes,toolbar=no");
					 testwin.document.open();
					 //testwin.document.title = filename;
					 testwin.document.write(str);
					 testwin.document.close();
					
		        }
		    });
			 mini.unmask(document.body);
		}
	};
	function showpicuture(filedict){
		jQuery("#imag").html('');
    		var img = createAttachmentImageDiv(filedict);
    		if(img){
	    		jQuery("#imag").append(img);
    		}
    	mini.get("showfile").show();
	};
	//根据单个图片数据获取一个document
	function createAttachmentImageDiv(filedict){
		var $div = jQuery('<div/>');
			$div.addClass("img-box");
			var $image = jQuery("<img/>");
			$image.attr("src", getRootPath()+"/acl/qeryCustfile.acl?filedict="+filedict+"&previewtype="+"second");
			$image.appendTo($div);
		return $div;
	}
</script>

</head>
<body>
	
		<div id="id_table_render_table1"></div>
   <div id="showfile" class="mini-window" title="预览" showMaxButton="true"
			style="width: 60%; height: 60%;" showCloseButton="true"  showModal="true"
			allowResize="true" allowDrag="true" >
			<div id="imag">
			</div>
	        </div>
</body>
</html>
