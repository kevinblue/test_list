<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>本方账户信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<body style="overflow:hidden;"> 
   <script type="text/javascript" defer>
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table1",
				renderTo: "id_tasksContainer2",
				width: '100%',
				height: '100%',
				showPager : false,
				title:'本方账户信息',
				remoteOper : true,
				addRemoteOperUrl:getRootPath()+"/acl/addOwnAccount.acl",
				editRemoteOperUrl:getRootPath()+"/acl/updateOwnAccount.acl",
				removeRemoteOperUrl:getRootPath()+"/acl/removeOwnAccount.acl",
				multiSelect: false,
				xmlFileName:"/eleasing/jsp/sysmgr/sysdatamgr/ownaccountinfo.xml",
				tools : [ 'add', '-', 'edit', '-', 'remove'],
				columns: [
					{type : 'indexcolumn'},
					{type: 'checkcolumn'},
					{field: 'id', header: '主键',visible:false,formEditorConfig : {visible:false}},
					{field: 'ownid', header: '本方出租人',visible:true,renderer:function(e){
						return e.record.raw_oid;
					},formEditorConfig : {
						type : 'combobox', 
						required : true, 
						multiSelect : false, 
						valueField : 'oid', 
						textField : 'raw_oid', 
						labelWidth : 100,
						fieldVisible : true,
						params : {
							xmlFileName : '/eleasing/jsp/sysmgr/sysdatamgr/owninfo_account.xml',//对应的数据xml
							pageSize:20
						}
					}},
					{field: 'accnumber', header: '本方账号',queryConfig:{},formEditorConfig : {labelWidth:100}},
					{field: 'accbank', header: '本方银行',queryConfig:{},formEditorConfig : {newLine:true}},
					{field: 'accname', header: '本方银行账户',formEditorConfig : {}},
					{field: 'state', header: '是否启用',renderer:function(e){
						return e.record.raw_state;
					},formEditorConfig : {
						newLine:true,
						type : 'combobox', 
						required : true, 
						multiSelect : false, 
						valueField : 'id', 
						textField : 'text', 
						width : 200,
						labelWidth : 100,
						fieldVisible : true,
						data:[{id:'0',text:'是'},{id:'1',text:'否'},{id:'2',text:'未知'}]
					}},
					{field: 'accountpurpose', header: '账户用途' ,visible:false,
						formEditorConfig : {
							
							type : 'combobox', 
							required : true, 
							multiSelect : false, 
							valueField : 'id', 
							textField : 'text', 
							colspan:3,
							width : 200,
							labelWidth : 100,
							fieldVisible : true,
							data:[{id:'合同默认帐号',text:'合同默认帐号'},{id:'其他',text:'其他'}]
					}
					}
				]
				
			});
		});
	});
</script>
<div id="id_tasksContainer2" style="width: 100%;height: 100%;"></div>
</body>
</html>