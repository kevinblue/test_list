<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/init.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
seajs.use(["${pageContext.request.contextPath}/js/tenwa/init.js"]);
var allview = true;
if('${param.isView}' == 'true' || isViewHistoryTask == true){allview = false};
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true' || isViewHistoryTask == true){showTools = false};
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id: "premise_condition",
			renderTo: "id_premise_condition",
			width: globalClientWidth - 10,
			height: 360,
			lazyLoad: false,
			isClickLoad:false,
			remoteOper : false,
			showToolbar: showTools,
			showPager: false,
			multiSelect: true,
			data: mini.decode('${empty json_premise_condition_str ? "[]" : json_premise_condition_str}'),
			tools : [ 
						{
						  html:'批量修改',
						  plain:true,
						  iconCls:'icon-edit', 
						  handler:function(miniTable, buttonText) { 
							var row = miniTable.getSelecteds();
							if(row.length>0){
								var multiform = new mini.Form("multiform");
								var multieditWindow = mini.get("multieditWindow");
								multieditWindow.show();
								multiform.clear();
							}else{
								mini.alert("请至少选中一行！");
							}
						}
					}
              ],
			addOperCallBack : function (miniTable,rowData){
				$('#id_json_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			removeOperCallBack:function(miniTable){
				$('#id_json_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			copyOperCallBack:function(miniTable){
				$('#id_json_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			updateOperCallBack:function(miniTable){
				$('#id_json_premise_condition_str').val(mini.encode(miniTable.getData()));
			},
			columns:[
							{type: 'indexcolumn'},
							{type: 'checkcolumn'},
							{field: 'id', header: 'id', visible: false},
							{field: 'projid', header: 'projid', visible: false},
							{field: 'projnumber', header: '项目编号',width: 120,
								formEditorConfig:
								{
									readOnly:true,
									required: true,
									labelWidth:100,
									width: 200
								}
							},
							{field: 'devicename', header: '设备名称ID',visible: false,formEditorConfig:{fieldVisible: false}},
							{field: 'devicenamename', header: '设备名称',formEditorConfig:{fieldVisible: false}},
							{field: 'paymentid', header: '付款节点/费用类型', visible: true,width: 118,
								formEditorConfig:
									{   
										readOnly:true,
										required: true,
										labelWidth:100,
										width: 200
									},
									renderer: function(e){
										return e.record.paymentid+"-"+"设备款";
									}	
							},
 							{field: 'paymentnode', header: '付款条件',width: 250,
 											formEditorConfig:
 											{   
 												newLine : true,
 												readOnly:true,
 												required: true,
 												labelWidth:200,
 												width: 200
 											},
 											renderer: function(e){
 												return e.record.paymentnode+"."+e.record.conditionname;
 											}
 										},
 										
 							{field: 'feetype', header: '款项节点ID',visible: false},//此处实际显示的是fundtype
 							{field: 'feetypename', header: '款项节点',
 											formEditorConfig:
 											{   readOnly:true,
 												required: true,
 												labelWidth:100,
 												width: 200
 											}
 										},
 							   {field:'conditionname',header:'前提条件', visible: false,							
 			  								headerAlign:'center',
 			  								 width:150,
 			  								 allowSort:true,
 			  							    formEditorConfig:{
 			  							    	readOnly:true,
 			  							    	newLine:true,
 										         type:'text',								
 										     	width:200,
 										     
 			 							           }},
 			 			    {field: 'remark', header: '备注',
 			 											formEditorConfig:
 			 											{
 			 												type:'textarea',
 			 												readOnly:true,
 			 												newLine:true,
 			 												labelWidth:100,
 			 												height:70,
 			 												colspan:2,
 			 												width: 278
 			 											}
 			 										},
 			 			  {field:'ismeet',header:'是否满足',  								
 			 			  								headerAlign:'center',
 			 			  								 width:70,
 			 			  								 allowSort:true,
 			 			  							    formEditorConfig:{
 			 			  							    	newLine:true,
 			 										         type:'text',								
 			 										     	width:200,
 			 										     
 			 			 							           }},	
 			 			 {field:'conditionstub',header:'条件说明', headerAlign:'center',
 			 			 			  								 width:150,
 			 			 			  								 allowSort:true,
 			 			 			  							    formEditorConfig:{
 			 			 			  							    	newLine:true,
 			 			 										         type:'text',								
 			 			 										     	width:200,
 			 			 			 							           }} ,

 							       {
 										field: 'createdual', 
 										header: '操作',
 										formEditorConfig:{ fieldVisible:false},
 										 renderer:function(e){
 									    	var createdual = e.record.createdual;
 											return "<a href='javascript:void(0);' onclick='upPaymentFile(\"" + createdual + "\")'>上传附件</a>";    	
 									  } 
 									},
					    {field: 'filename', header: '文件信息',width: 400,
 			 			    renderer: function(e){
 			 			 	return reloadUpload(e);
 			 			 	} 			 							        	   
					    
					    }

						    
					]
		
		});
	});
});
function reloadUpload(e){
		var jsonBaseFileObj = e.record.filename;
		if(!jsonBaseFileObj){
			return workFlowlocale[''];
		}else{
			 var filenameHtml = '';
 			 var renderHtml1 = "<table style='border:0px solid #FFF;'>";
 			 var renderHtml2 = '';
 			 var renderHtml3 = "</table>";
			 for(var y = 0 ;y<jsonBaseFileObj.length;y++){
 			 var deletebyid = jsonBaseFileObj[y]["id"];
 			 var filename = jsonBaseFileObj[y]["file_name"];
 			 var realname = jsonBaseFileObj[y]["realname_"];
 			 var creatdateStr = jsonBaseFileObj[y]["create_date"];
 			 var creatdate = mini.formatDate(creatdateStr, "yyyy-MM-dd HH:mm:ss");
 			 var a = (allview) ? "<td style='border:0px solid #FFF;'>"+
			          "<a href='#' onclick='removeUploadFilebyId(&quot;"+ deletebyid+ "&quot;);'style='color:red;'>"+"[删除]"+"</a>"+
			              "</td>":"";
 			 renderHtml2 +="<tr style='border:0px solid #FFF;'>"+a+
 											 	 "<td style='border:0px solid #FFF;' >"+"<a href='javascript:void(0)' onclick='PreviewOffice(\""+deletebyid+"\",\""+ filename+"\")'>"+"预览</a> &nbsp;|&nbsp;"+
 														  "<a href='javascript:void(0);' onclick='downloadFile(&quot;"+ deletebyid + "&quot;)'>"+filename+"</a>"+
 												 "</td>"+
 												 "<td style='border:0px solid #FFF;'>"+
 														  "【"+ realname+"】"+
 												 "</td>"+
 												 "<td style='border:0px solid #FFF;'>"+
 														  "【"+ creatdate+"】"+
 												 "</td>"+ 
 	 			   	                "</tr>"; 
 			  };
 			  	filenameHtml = renderHtml1+renderHtml2+renderHtml3; 
			return  filenameHtml;
		}
}
//上传文件
function upPaymentFile(id){
	var minitables=mini.get("premise_condition");
	var datarows = minitables.getSelecteds();
    if(datarows.length>1){
    	mini.alert("请选中一条文件上传!");
    	return false;
    }
 	var filekey = id;
	var uploadutil = new uploadUtil({
			url : '/leasing/file/uploadFile.action',
			tableid : "contract_word",
			appImage:true,
		    modelname : '付款前提条件',
		    parames : {
					flowUnid : flowUnid,
					filekey : filekey,
					memo : 'app'　　　
				},
				jscallback : 'reloadcustcontactfile2'//上传完成回掉刷新页面
		});
	uploadutil.createFileAndShow(); 
};
function reloadcustcontactfile2(message){
	var minitables=mini.get("premise_condition");
	var row = minitables.getSelected();
	var rows = minitables.getSelecteds();
	var showfilebyid = row.createdual;
	$.ajax({
		cache : false,
		async : false,
        type: "post",
        url : getRootPath()+ "/acl/getTermsofpaymentFileInformation.acl",
        data: {showfilebyid:showfilebyid,flowUnid:flowUnid},
        success: function (datas) {
		        	var jsonBaseFiledata = mini.decode(datas);
		        	row.filename=jsonBaseFiledata;
		        	minitables.updateRow(rows, row);
		        	minitables.saveDataToInput();
        	        $("#id_json_premise_condition_str").val(mini.encode(mini.get("premise_condition").getData()));
        }
    });
	mini.get("id_uploadfile").hide();
	mini.unmask(document.body);
};

//删除附件
function removeUploadFilebyId(uploadAttachmentFileDetailId) {
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
		        		reloadcustcontactfile2();
		        		mini.unmask(document.body);
		        	}else{
		        		mini.alert("删除失败！");
		        		mini.unmask(document.body);
		        	}
		        	
		        }
		    })
}
//批量修改付款前提条件
function updateCondition(){
	var idismeet=mini.get("idismeet").getValue();
	var idconditionstub=mini.get("idconditionstub").getValue();
   if(idismeet==''||idismeet==null){
	   mini.alert("请选择是否满足！");
	   return false;
   }
   var alltable = mini.get("premise_condition");
   var rows = alltable.getSelecteds();
   var premisearray=[];
   for(var i=0;i<rows.length;i++){
	   rows[i].ismeet=idismeet;
	   rows[i].conditionstub=idconditionstub;
	   alltable.updateRow(rows, rows[i]);
   }
    //将多行的数据保存到input中
    alltable.saveDataToInput();
 	var multieditWindow = mini.get("multieditWindow");
	    multieditWindow.hide(); 
	    mini.alert("修改成功！");
}
</script>
<div id="id_premise_condition"></div>
	<div id="multieditWindow" class="mini-window" title="编辑页面" style="width:300px;height:176px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr>
                    <td class="td-content-title">是否满足:</td>
                    <td >  
                        <input name="ismeet" id="idismeet"   textField="name" valueField="id"
                               class="mini-combobox"
						       data="[{id:'是',name:'是'},{id:'否',name:'否'}]"
						       showNullItem="true"
						       required="true"
						       />
                    </td>
                     </tr>
                     <tr> 
                      <td class="td-content-title">条件说明:</td>
	             <td  style="padding-top: 5px;padding-bottom: 5px;">
	             <input style="width:100%;height:50px" label="条件说明" name="conditionstub"  id="idconditionstub"
	                   class="mini-textarea"  type="text" >  </td>
                </tr>
                <tr>   
                        <td class="td-content-title"></td>
                   <td >
                        <a class="mini-button " onclick="updateCondition()">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td></tr>  
            </table>
        </div>
	</div>