<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<title>合同归档</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath}/js/apcomponent/apqueryinput/apqueryinput.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
<script type="text/javascript">
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id2',
				width:globalClientWidth,
				height:globalClientHeight-100,
				//title:'合同归档',
				iconCls:'icon-node',
				multiSelect:true,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				allowCellEdit:true,
				allowCellSelect:true,
				params:{
					contract_id :"${param.id}"
				},
				//已经归档的合同
				xmlFileName:'/docarchives/contract_archives.xml',
				tools: [{
					html:'归档',
					plain : true,
					iconCls : 'icon-edit',
					handler : function(miniTable, buttonText) {
						//归档前判断必填项
						var containerNumber=mini.get("containerNumber").getValue();
						if(containerNumber==""){
							alert("柜号不能为空，请输入柜号！");
							return;
						}
						var filing_date = mini.get("id_filing_date").getText();
						var userName=mini.get("id_realName").getValue()||'${sessionScope.loginUser.id}';
						if(userName==""){
							alert("归档人不能为空，请选择归档人");
							return;
						}
						var url=getRootPath()+"/acl/saveallcontractarchives.acl";
						//遮罩
						mini.mask({
							el : document.body,
							cls : 'mini-mask-loading',
							html : '数据操作中 请稍等...'
						});
						jQuery.ajax({
							url : url,
							type : 'POST',
							timeout : 30 * 1000,
							data : {containerNumber:containerNumber,filing_date:filing_date,userName:userName,conid:"${param.id}"},
							dataType : 'json',
							error : function(res, errInfo, e) {
								mini.unmask(document.body);
							},
							success : function(resultJson) {
								var returnState = resultJson.returnType;
								switch (returnState) {
								case "SUCCESS": {
									miniTable.reload();
									mini.unmask(document.body);
									mini.alert("归档成功");
									break;
								}
								case "FAILURE": {
									miniTable.reload();
									mini.unmask(document.body);
									//mini.alert(operType+operTitle + " 失败！");
									mini.alert("生成失败！"+resultJson.content);
									break;
								}
								}
							}
						});
					}
				}],
				columns:[ 
				    {type:'indexcolumn',width:'10'},
/* 				    {field:'',header:'操作',
				  		   formEditorConfig:{
	                               fieldVisible:false},
			               renderer:function(e){
				                   var id = e.record.id;
				                   var custid = "${param.id}";
			                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"" + custid + "\",\"one\") '>查看 /上传附件</a>";
					}},
				    {
						field : 'id',
						header : 'id',
						visible : false
				    }, */
				   	{field:'docname',header:'合同档案名称'}
				]
			});
		});
	});
/*上传资料内容*/

function showanduploadfile(id,custid,type)
{
	var urlFlag = getRootPath()+"/leasing/cust_info/cust_contact/cust_contact_file_list.bi?id="+id+"&cust_id="+custid+"&type="+type;
	mini.open({
       url: urlFlag,
       title: "上传档案资料", width: 800, height: 455,
       onload: function () {},
       ondestroy: function (action) {
       	if("savesuccess" == action){
       		window.location.reload();
       	}
       }
   });

}
</script>
</head>
<body>
	<div id="form1" class="mini-panel" style="width: 100%;">
		<table class="fillTable">
		    <tr>
		        <td colspan="6" style="border-bottom: 1px solid #99CCFF;">
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
	        	</td>
		    </tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">客户名称:</td>
				<td class="td-content" width="35%">
				<input class="mini-textbox" name="custname"
					allowInput="false" ></input></td>
				<td class="td-content-title" width="15%">合同编号:</td>
				<td class="td-content" width="35%">
					<input class="mini-textbox" name="conid"
					allowInput="false"></input></td>
			</tr>
			<tr class="tr-odd ">
				<td class="td-content-title" width="15%">合同起租日:</td>
				<td class="td-content" width="35%">
					<input class="mini-textbox" name="start_date_" 
						allowInput="false"></input></td>
				<td class="td-content-title" width="15%">柜号</td>
				<td class="td-content" width="35%">
					<input class="mini-textbox" id="containerNumber" 
						name="containernumber" required="true"/>
					 <font class="required-tag"> * </font>
					 </td>
					 
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">归档人:</td>
				<td class="td-content" width="35%">
		         	 <input id="id_realName" name="fillinguser" label="归档人" 
		         	 	class="mini-buttonedit mini-queryinput" 
		         	 	allowInput="false"
		             	required="true"
						text="${empty requestScope['fillingusername'] ? sessionScope.loginUser.realname : requestScope['fillingusername']}"
						value="${empty requestScope['fillinguser'] ? sessionScope.loginUser.id : requestScope['fillinguser']}"
						/>
					 <font class="required-tag">*</font>
					 <input id="rawValueRealname_" name="fillingusername" value="${empty requestScope['fillingusername'] ? sessionScope.loginUser.realname : requestScope['fillingusername']}" 
					 class="mini-textbox" style="display:none"/>
					 </td>
				<td class="td-content-title" width="15%">上次归档时间:</td>
	            <td class="td-content" width="38%"> 
	             	<input id="id_filing_date" name="filing_date" class="mini-datepicker" style="width:200px"  format="yyyy-MM-dd HH:mm:ss" 
	             	label="归档时间"  required="true" allowInput="false" showTime="true"/></td>
			</tr>
		</table>
	</div>
	<div id="table_id2"></div>
</body>
<script type="text/javascript">
jQuery(function() {
	//初始化数据
	var data = miniui_ext.initPageData("/docarchives/contract_archives_list.xml","form1","${param.id}");
	mini.get("id_realName").setText(data.fillingusername);
	tenwa.createQueryInput({ 
		id:'id_realName',
		label:'归档人',
		textField:"name",
  		valueField:"id",
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValueRealname_").setValue(rowData.name);
		},
		params: {
			xmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'
		}
	});
});

</script>  
</html>