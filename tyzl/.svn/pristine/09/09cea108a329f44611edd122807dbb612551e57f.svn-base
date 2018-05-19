<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.tenwa.kernal.utils.EnumUtil,com.tenwa.kernal.utils.ClassUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>javaBean配置</title>
	<%@include file="/base/mini.jsp"%>
	<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		jQuery(function(){
			mini.parse();			
			seajs.use('js/dragonmagic/freemakerconfig',function(rc){
				rc.init();
			});
		});
		
		var nums = <%=ClassUtil.getAllEnumsAndBase(new String[]{"com.tenwa.kernal","com.tenwa.business","com.tenwa.jbpm","com.tenwa.report","com.tenwa.freemaker"})%>;
		
		var comboboxDatas = {
			'tablecolumntype' : <%=EnumUtil.getEnumConstantsAsJson(com.tenwa.freemaker.enums.EntityColumTypeEnum.class)%>,
			'foreignFieldType' :  <%=ClassUtil.getPackageClassNames(new String[]{"com.tenwa.business.entity","com.tenwa.leasing.entity","com.tenwa.jbpm.entity","com.tenwa.report.entity","com.tenwa.freemaker.entity"})%>,
			'commonFieldType' :  
				[{text:'Integer',value:'Integer'},	
					{text:'Double',value:'Double'},	
					{text:'Float',value:'Float'},	
					{text:'String',value:'String'},
					{text:'BigDecimal',value:'java.math.BigDecimal'},
					{text:'Date',value:'java.util.Date'},
					{text:'Boolean',value:'Boolean'}	
				]
				,
			'numsFieldType' :  
				nums
				,
			"fetchType" :　<%=EnumUtil.getEnumConstantsAsJson(com.tenwa.freemaker.enums.ColumnFetchEnum.class)%>,	
			'sourceFold':[{text:'jbpm',value:'jbpm'},{text:'business',value:'business'},{text:'leasing',value:'leasing'},{text:'report',value:'report'},{text:'freemaker',value:'freemaker'}],
			'blobOrClobFieldType' : [{text:'String',value:'String'}],	
			'yesOrNo' :[{text:'Yes',value:'1'},{text:'No',value:'0'}],
			'yesOrNoIsNull' :[{text:'Yes',value:'0'},{text:'No',value:'1'}],
		};
		function __saveChangeToActivityClose (){
			mini.get('id_freemaker_entity_setting').hide();
		}
	</script>
	<style type="text/css">
		 #freemaker_entity_column_key .mini-textbox {
		width :125px;
		}
	
		#freemaker_entity_column_key .mini-buttonedit {
			width :125px;
		} 
	</style>
</head>
<body>
	<table>
		<tr>
			<td>
				<div id='freemaker_entity_tree_container' class="mini-panel" title="javaSrc" style="float:left;width:325px;overflow:hidden;">
					<div style="position: fixed;margin-top: -28px;margin-left: 70px;">
						<input id="id_queryentity_tree"  class="mini-textbox" style="width: 80px; margin-left: 5px;">
						<a class="mini-button" id="id_queryentity_tree_button"  iconCls="icon-search">搜索</a>
						<a class="mini-button" id="id_createalldata_button"  iconCls="icon-db">全局生成</a>
					</div>
					<div id="freemaker_entity_tree" ></div>
				</div>
				
				<div id="freemaker_entity_tree_contextmenu" class="mini-contextmenu" style="display:none;width:120px;">
					<div id="freemaker_entity_contextmenu_add_package" iconCls="icon-addnew" >新增包</div>
					<div id="freemaker_entity_contextmenu_add_entity" iconCls="icon-addnew" >新增类</div>
					<div id="freemaker_entity_contextmenu_edit" iconCls="icon-edit" >修改</div>		
					<div id="freemaker_entity_contextmenu_modify" iconCls="icon-remove" >删除</div>
				</div>
				<jsp:include page="form_freemaker_entity_config.jsp" />
			</td>
			<td>
				<input name="entityId" id= "entityId" class="mini-hidden">
				<div id="freemaker_entity_column_container" style="float: left;width: 100%;height: 100%;"></div>
			</td>
		</tr>
	</table>
	
	<jsp:include page="form_freemaker_entity_column_config.jsp" />
	
</body>
</html>