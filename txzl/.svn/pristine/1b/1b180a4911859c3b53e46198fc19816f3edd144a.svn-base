<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var conid = "${requestScope['contract_info.id']}";
jQuery(function(){
			var tools= [{
				html : '上传',
				plain : true,
				iconCls : 'icon-add',
				handler : function(miniTable, buttonText) {
						var row = miniTable.getSelected();
						var filekey = row.id;
						var uploadutil = new uploadUtil({
								url : '/leasing/file/uploadFile.action',
								tableid : "contract_word",
							    modelname : '合同附件',
							    parames : {
										flowUnid : flowUnid,
										filekey : filekey　　　//项目ＩＤ
									},
									jscallback : 'reloadcustcontactfile'
							});
						uploadutil.createFileAndShow();
				}
			},'-','remove'];
		    seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				height: 360,
				showPager : false,
				lazyLoad : false,
				
				title:'合同编号设置',
				remoteOper : true,
				entityClassName : "com.tenwa.leasing.entity.proj.ContractNumberSetting",
				
				params:{
					flowunid:flowUnid,
					conid:conid
					
				},
				showToolbar: false,
				tools: tools,
				multiSelect: false,
				xmlFileName : '/eleasing/workflow/contract/contract_sign/contract_sign_number_setting.xml',
				
				queryButtonColSpan : 6,
				queryButtonNewLine : true,
				columns: [
                 { type : 'checkcolumn'},
					{ header:'序号',type : 'indexcolumn'},
					{field:'id',
						header:'主键',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
							
						}
					},
					{field:'contractname',
						header:'合同名称',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
						}
					},
					{field:'contractnumber',
						header:'合同编号',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: true,
						}
					},
					
					{field:'oldcontnum',
						header:'修改前合同编号',
						visible: false,
						formEditorConfig:{
							fieldVisible: false,
						}
					},
					
					{field:'docid',
						header:'流程ID',
						visible: false,
						formEditorConfig:{
							value:flowUnid,
							fieldVisible: false,
						}
					},
					
					
					{field:'contractsubject',
						header:'合同主体',
						visible: true,
						formEditorConfig:{
							fieldVisible: true,
							valueField:'value'
						}
					},
					
					{
						field:'contracttypename',
						visible:true,
						header:'合同类型',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             }
					},
					{
						field:'contractpersonname',
						visible:true,
						header:'签约人',
						 formEditorConfig:{
					     fieldVisible:false,
					        }},
					{
						field:'contractperson',
						visible:false,
						header:'签约人',
						 formEditorConfig:{
					          newLine:true,
					            width:"100%",
					             type:'queryinput',
					         required:true,
					        textField:'realname',
					       valueField:'id',
					       allowInput:false,
					     fieldVisible:true,
					            width:'100%',
					          colspan:3,
					           params:{xmlFileName:'/test/user.xml'}}
					},
					

					{
						field:'signingtime',
						visible:true,
						header:'签约时间',
						formEditorConfig:{
			                  type:'date',
			                 vtype:'date',
			                format:'yyyy-MM-dd',
			                newLine:true,
			            allowInput:false}
					},
					{
						field:'fileaddress',
						visible:false,
						header:'文件地址',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             },
					    renderer:showUploadFilesList
					},
					{
						field:'fileid',
						visible:false,
						header:'文件id',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             }
					},
					
					
					{
						field:'contracttype',
						visible:false,
						header:'合同类型',
		   		         formEditorConfig:{
		   			              newLine:true,
					             required:true,
					          multiSelect:false, 
					            textField:'name',
					           valueField:'value',
					           showNullItem:"true",
					           nullItemText:"",
					         fieldVisible:true}}, {
									field : 'mark',
									header : '备注',
									headerAlign : 'center',width: 150,
									formEditorConfig : {
										newLine : true,
										width : 495,
										colspan : 6,
										height : 55,
										type : 'textarea'
									}
								} ,
		         {field: 'filename', header: '上传文件信息',width: '25%',
					     	renderer: function(e){
								return getPaydownload(e);
							}
		            }
				] 
			});
		});
	});
	
function getPaydownload(e){
	 var fileHtmlTd = '';
	 //文件id
	 var idStr = e.record.fileid;
	 var idArray = idStr.split(",");
	//文件名
     var filenameStr = e.record.filename;
      var filenameArray = filenameStr.split(",");
    //上传时间
    var createdateStr = e.record.createdate;
    var createdateArray;
    if(typeof(createdateStr)== 'object'){
    	//var date=tenwa.toDate(createdateStr+"","yyyy-MM-dd hh:mm:ss");
    	var ss=tenwa.toChar(createdateStr,"yyyy-MM-dd hh:mm:ss");
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
	var con= confirm("确定删除吗？");
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
    });
}

//上传附件
function upFile(id) {
	var filekey = id;
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
		    modelname : '合同附件',
		    parames : {
					flowUnid : flowUnid,
					filekey : filekey　　　//项目ＩＤ
				},
				jscallback : 'reloadcustcontactfile'
		});
	uploadutil.createFileAndShow();
}

	function downloadFile(Id) {
		attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
	} 
	
	function reloadcustcontactfile(message){
		mini.alert(message);
		mini.get("id_uploadfile").hide();
		mini.unmask(document.body);
		mini.get("id_tasks_table1").reload();
	}
</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
<table class="fillTable" cellspacing="0" cellpadding="0" id="id_table_generate_contract">
	<tr class="tr-contractect-word-list">
		<td class="td-content" colspan="2">
			<jsp:include page="contract_list_info.jsp"></jsp:include><%--合同清单 --%>
		</td>
	</tr>
</table>