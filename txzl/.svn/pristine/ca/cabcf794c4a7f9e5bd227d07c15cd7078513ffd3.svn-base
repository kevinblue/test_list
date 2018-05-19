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
var conid = "${requestScope['contract_info.id']}";
jQuery(function(){
	var showTools = true;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){showTools = false;}
		    seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "contract_word_fundput",
				renderTo: "id_contract_word_fundput",
				width: '100%',
				height: '100%',
				width: globalClientWidth - 10,
				height: 360,
				lazyLoad: false,
				isClickLoad:false,
				remoteOper : false,
				showToolbar: showTools,
				showPager: false,
				multiSelect: true,
				data: mini.decode('${empty json_contract_word_fundput_str ? "[]" : json_contract_word_fundput_str}'),
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
							fieldVisible: false,
						}
					},
					{field:'contractnumber',
						header:'合同编号',
						visible: true,
						formEditorConfig:{
							 required:true,
							fieldVisible: false,
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
							fieldVisible: false,
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
						field:'fileaddress',
						visible:false,
						header:'文件地址',
		   		         formEditorConfig:{
					         fieldVisible:false,
					             },
					    renderer:showUploadFilesList
					},
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
						field:'contractpersonname',
						visible:true,
						header:'签约人',
						 formEditorConfig:{
					     fieldVisible:false,
					        }},

					{
						field:'signingtime',
						visible:true,
						header:'签约时间',
						formEditorConfig:{
							required:true,
			                  type:'date',
			                 vtype:'date',
			                format:'yyyy-MM-dd',
			                newLine:true,
			            allowInput:false}
					},
					{
						field:'contractingplace',
						visible:true,
						header:'签约地点',
		   		         formEditorConfig:{
		   		        	required:true,
		   		        	newLine:true,
					         fieldVisible:true,
					         type:'textarea'
					             }
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
					         fieldVisible:false}},
					         {
									field : 'mark',
									header : '备注',
									headerAlign : 'center',width: 150,
									formEditorConfig : {
										newLine : true,
										width : 495,
										colspan : 6,
										height : 55,
										type : 'textarea',
										fieldVisible:false
									}
								} ,
		         {field: 'filename', header: '上传文件信息',width: '30%',
									formEditorConfig : {
										fieldVisible:false
									},
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
 	                    "<td style='border:0px solid #FFF;' >"+"<a href='javascript:void(0)' onclick='PreviewOffice(\""+idArray[i]+"\",\""+ fnStr+"\")'>"+"预览</a> &nbsp;|&nbsp;"+
                        "<a href='javascript:void(0);' onclick='downloadFile123(\""+ idArray[i] + "\")'>"+fnStr+"</a>"+
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
	function downloadFile123(Id) {
		attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
	} 
</script>
<div id="id_contract_word_fundput" style="width: 100%;height: 100%;"></div>
