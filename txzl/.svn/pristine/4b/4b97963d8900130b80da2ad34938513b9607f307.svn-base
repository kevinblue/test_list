<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据源管理</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<%@include file="/base/mini.jsp"%>

<script type="text/javascript">
	jQuery(function(){
		mini.parse();
		setTable();
		loadTable();
		loadDataSourceType();
		loadDialect();
		hideOrShowJdbc(true);
		jQuery('#tr_jndi').hide();

	});

	function setTable(){
		var table = mini.get("datasource_table");
		table.set({
			url:'${pageContext.request.contextPath}/report/config/loadDataSourceTable.action',
			height: document.documentElement.clientHeight-34,
			multiSelect: false,
			allowUnselect: true,
			//onlyCheckSelection: true,
			columns:[		
				{header:'id',name:'id',field:'id',visible:false},
				{type:'checkcolumn',width:15},
				{header:'名称',name:'dataSourceName',field:'dataSourceName',headerAlign:'center'},
				{header:'类型',name:'dataSourceType',field:'dataSourceType',headerAlign:'center'},
				{header:'方言',name:'dialect',field:'dialect',displayField:'dialectName',headerAlign:'center'},
				{header:'JNDI',name:'jndi',field:'jndi',headerAlign:'center'},
				{header:'驱动',name:'driverName',field:'driverName',headerAlign:'center'},
				{header:'连接串',name:'url',field:'url',headerAlign:'center'},
				{header:'连接用户',name:'username',field:'username',headerAlign:'center'},
				{header:'password',name:'password',field:'password',visible:false}
			],
			onrowdblclick:function(e){

			}
		});
	}

	function loadDataSourceType(){
		mini.get("dataSourceType").set({
			url:'${pageContext.request.contextPath}/report/config/getAllDatasourcetypeAsJson.action',
			valueField:'value',
			onvaluechanged:function(e){
				switch(e.value){
				case "JNDI":
					jQuery('#tr_jndi').show();
					jQuery('#id_dstype_label').html('JNDI:');
					hideOrShowJdbc(true);
					break;
				case "SPRING":
					jQuery('#tr_jndi').show();
					jQuery('#id_dstype_label').html('Bean:');
					hideOrShowJdbc(true);
					break;
				case "JDBC":
					jQuery('#tr_jndi').hide();
					hideOrShowJdbc(false);
					break;
				default:
					jQuery('#tr_jndi').hide();
					hideOrShowJdbc(true);
				}
			}
		});
	}

	function hideOrShowJdbc(isHidden){
		var display = "none";
		if(!isHidden){
			display = "";
		}
		
		for(var i = 1; i <= 4; i++){
			jQuery('#jdbc_' + i).css('display',display);
		}
		
	}

	function loadDialect(){
		mini.get("dialect").set({
			url:'${pageContext.request.contextPath}/report/config/getAllDialectAsJson.action',
			textField:'name',
			valueField:'value',
			onvaluechanged:function(e){				
				mini.get('dialectName').setValue(e.selected.name);				
			}
		});
	}

	function loadTable(){
		mini.get("datasource_table").load();
	}

	function add(){
		var form = new mini.Form('datasourceForm');
		form.reset();
		mini.get('dataSourceType').doValueChanged();
		mini.get('datasource').show();
	}

	function check(){
		jQuery.ajax({
			url:'${pageContext.request.contextPath}/report/config/checkDataSourceValidate.action',
			data:(new mini.Form('datasourceForm').getData()),
			dataType:'text',

			success:function(data){			
				if(data=="TRUE")
					mini.alert("数据源连接测试成功"); 
				else
					mini.alert("数据源连接测试失败，原因：" + data);
				
			},

			error:function(){				
				mini.alert('数据源连接测试失败！原因：服务器内部错误或无法访问。');
			}
		});
	}

	function cancel(){
		mini.get('datasource').hide();
	}

	function save(){
		var data = (new mini.Form('datasourceForm').getData());		
		seajs.use('js/report/ajax',function(ajax){
			ajax.ajax({
				url:'${pageContext.request.contextPath}/report/config/addReportDataSource.action',
				data: {formData: mini.encode(data)},
				hasContext: true,
				encode: false,
				method: 'POST', 
				dataType:'text',
				success:function(){
					mini.get('datasource').hide();
					loadTable();
				}
			});

		})
	}

	function remove(){
		var row = mini.get('datasource_table').getSelected();
		if(row){
			jQuery.ajax({
				url:'${pageContext.request.contextPath}/report/config/removeReportDataSource.action',
				data:{id:row.id},
				success: function(){
					loadTable();
				}
			});
		}
	}

	function edit(){
		var row = mini.get('datasource_table').getSelected();
		if(row){
			var form = new mini.Form('datasourceForm');
			form.clear();
			form.reset();
			form.setData(row);
			mini.get('dataSourceType').doValueChanged();
			mini.get('datasource').show();
		}
	}
</script>
</head>
<body>
<div style="width:100%">
	<div class="mini-toolbar">
		<a class="mini-button" iconCls="icon-add" onclick="add()">添加</a>
		<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
		<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>		
	</div>
	<div id="datasource_table" class="mini-datagrid">
	</div>
</div>

<div id="datasource" class="mini-window" modal="true" title="数据源配置"  style="height: 240px;width: 400px">
	<div id="datasourceForm" class="mini-form" style="margin:20px;margin-top:5px;margin-bottom:-5px">
		<table>
			<tr>
				<td class="input_label_desc">名称:</td>
				<td>
				<input id="id" name="id"  class="mini-hidden">
				<input id="dataSourceName" name="dataSourceName" class="mini-textbox" value="" required="true"/>
				</td>
			</tr>
			<tr>
				<td class="input_label_desc">类型:</td>
				<td><input id="dataSourceType" name="dataSourceType" class="mini-combobox" required="true"/></td>
			</tr>
			<tr>
				<td class="input_label_desc">方言:</td>
				<td><input id="dialect" name="dialect" class="mini-combobox" required="true"/><input id="dialectName" name="dialectName" class="mini-hidden" /></td>
			</tr>

			<tr id="tr_jndi">
				<td class="input_label_desc" id="id_dstype_label">JNDI:</td>
				<td><input id="jndi" name="jndi" class="mini-textbox"/></td>
			</tr>
			<tr  id="jdbc_1">
				<td class="input_label_desc">驱动:</td>
				<td><input id="driverName" name="driverName" class="mini-textbox"/></td>
			</tr>
			<tr id="jdbc_2">
				<td class="input_label_desc" >连接串:</td>
				<td><input id="url" name="url" class="mini-textbox"/></td>
			</tr>
			<tr id="jdbc_3">
				<td class="input_label_desc">连接用户:</td>
				<td><input id="username" name="username" class="mini-textbox"/></td>
			</tr>
			<tr id="jdbc_4">
				<td class="input_label_desc">连接密码:</td>
				<td><input id="password" name="password" class="mini-textbox"/></td>
			</tr>
			<tr style="line-height:50px">
				<td>
					<a class="mini-button" width="78" onclick="check()">验证数据源</a>
				</td>
				<td style="text-align:right">
					<a class="mini-button" onclick="save()">保存</a>
					<a class="mini-button" onclick="cancel()">取消</a>
				</td>
			</tr>			
		</table>
	</div>
</div>


</body>
</html>