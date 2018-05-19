<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>

<div id="id_workflowhisAttachmentContainer" style="width: 100%;"></div>

 <script type="text/javascript">
 jQuery(function(){
	    var pageHeightV = jQuery(window).height() - 100;
		jQuery('#id_workflowhisAttachmentContainer').css({height:pageHeightV});	
 	
		var instanceIds = '';
		ajaxRequest({
			url:"${pageContext.request.contextPath}/table/getTableData.action",
			async:false,
			success:function(response) { 
				var rt = (response.responseText);
				var jsonData = mini.decode(rt);
				if(jsonData.datas.length > 0){
					instanceIds = jsonData.datas[0].instance_ids_;
				}
			},
			failure:function(response){ },
			params:{
				xmlFileName:'/jbpm/flowHisAttachemntRelated.xml',
				currentTaskId:"${currentHistoryTaskInfo.id}"
			}
		});
		
 		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'id_workflowhisAttachment',
				renderTo: "id_workflowhisAttachmentContainer",
				width:"100%",
				height:"100%",
				title:'其他流程附件',
				//iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:false,				
				xmlFileName:'/jbpm/flowHisAttachemnt.xml',
				params:{
					instanceIds:instanceIds,
					key : jQuery("#id_currentHistoryTaskInfo_keyOne").val()
				},
				//<a style='text-decoration:none;' onclick='downloadUploadFile(\"" + fileUUIDListArr[i] + "\");' title='"+workFlowlocale['DownloadFile']+"' href='javascript:void(0);'>" + fileNameListArr[i] + "</a>
				columns:[ 
				   	{field:'display_name_',header:'流程名称'},
				   	{field:'activity_name_',header:'上传步骤'},
				   	{field:'filetypename',header:'文件名称'},
					{field:'username',header:'上传人'},
				   	{field:'uploadtime',header:'上传时间',dateFormat:'yyyy-MM-dd HH:mm:ss'},
					{field:'filenames',header:'文件清单',width:'25%',	
				   		renderer : function(e){
							var id = e.record.id;
							var filenames = e.record.filenames;
							return "<a style='text-decoration:none;' onclick='downloadUploadFile(\"" +id+ "\");' title='"+workFlowlocale['DownloadFile']+"' href='javascript:void(0);'>" + filenames + "</a>";
						}
					}
				]
			});
		});
		
 	});
 </script>
 
 
