<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>匹配关系</title>
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<%@include file="/base/mini.jsp"%>

<script type="text/javascript">
	jQuery(function(){
		mini.parse();
		setTable();
		loadTable();
	});

	function setTable(){
		var table = mini.get("match_table");
		table.set({
			url:'${pageContext.request.contextPath}/report/config/loadMatchTable.action',
			height: document.documentElement.clientHeight-74,
			multiSelect: false,
			allowUnselect: true,
			//onlyCheckSelection: true,
			columns:[		
				{header:'id',name:'id',field:'id',visible:false},
				{type:'checkcolumn',width:15},
				{header:'名称',name:'matchName',field:'matchName',headerAlign:'center'},
				{header:'匹配sql',name:'matchSql',field:'matchSql',headerAlign:'center'},
			],
			onrowdblclick:function(e){

			}
		});
	}

	

	
	function loadTable(){
		mini.get("match_table").load();
	}

	function add(){
		var form = new mini.Form('matchForm');
		form.reset();
		mini.get('match').show();
	}


	function cancel(){
		mini.get('match').hide();
	}

	function save(){
		var data = (new mini.Form('matchForm').getData());
		if(!data.matchName){
			mini.alert('匹配名称不能为空!');
			return;
		}
		if(!data.matchSql){
			mini.alert('匹配SQL不能为空！');
			return;
		}
 		seajs.use('js/report/ajax',function(ajax){
			ajax.ajax({
				url:'${pageContext.request.contextPath}/report/config/addReportMatch.action',
				data: {formData: mini.encode(data)},
				hasContext: true,
				encode: false,
				method: 'POST', 
				dataType:'text',
				success:function(result){
					mini.alert('操作成功');
					mini.get('match').hide();
					loadTable();
				}
			});

		});
	}

	function remove(){
		var row = mini.get('match_table').getSelected();
		if(row){
			jQuery.ajax({
				url:'${pageContext.request.contextPath}/report/config/removeReportMatch.action',
				data:{id:row.id},
				success: function(){
					mini.alert('删除成功！');
					loadTable();
				}
			});
		}
	}

	function edit(){
		var row = mini.get('match_table').getSelected();
		if(row){
			var form = new mini.Form('matchForm');
			form.clear();
			form.reset();
			form.setData(row);
			mini.get('id').set({value:row.id});;
			mini.get('match').show();
		}
	}
</script>
</head>
<body>
	
		<div class="mini-panel" title="匹配关系" style="width: 100%;" showCollapseButton="false">
			<div class="mini-toolbar">
				<a class="mini-button" iconCls="icon-add" onclick="add()">添加</a>
				<a class="mini-button" iconCls="icon-edit" onclick="edit()">修改</a>
				<a class="mini-button" iconCls="icon-remove" onclick="remove()">删除</a>		
			</div>
			<div id="match_table" class="mini-datagrid">
			</div>
		</div>
<div id="match" class="mini-window" modal="true" title="匹配关系" style="width: 500px">
	<div id="matchForm" class="mini-form" style="margin:20px;margin-top:5px;margin-bottom:-5px;">
		<table>
			<tr>
				<td class="input_label_desc">名称:</td>
				<td>
					<input id="id" name="id"  class="mini-hidden">
					<input id="matchName" name="matchName" class="mini-textbox" value="" required="true" style="width: 350px"/>
				</td>
			</tr>
			<tr>
				<td class="input_label_desc">匹配sql:</td>
				<td><input id="matchSql" name="matchSql" class="mini-textarea" required="true" emptyText="请输入匹配sql" style="width: 350px"/></td>
			</tr>
			
			<tr style="line-height:50px;">
				<td style="text-align:center" colspan="2">
					<a class="mini-button" onclick="save()">保存</a>
					<a class="mini-button" onclick="cancel()">取消</a>
				</td>
			</tr>			
		</table>
	</div>
</div>


</body>
</html>