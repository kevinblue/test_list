<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>列表组件测试</title>
	<!--css sheet-->
	<%@include file="/base/mini.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js?version=1,1,1,1"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyComboBox.js"></script>
	<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	//上传函数结束
	jQuery(function(){
		
		mini.parse(document.body);
		
		//任务
		tenwa.createTable({
			id:'id_jobsTable',
			title:'调度任务',
			renderTo:"id_jobContainer",
			width : globalClientWidth,//表格高度，整型数值
			height : globalClientHeight,//表格宽度，整型数值
			showPager:true,
			pageSize:15,
			xmlFileName:'/quartz/queryAllJobs.xml',
			lazyLoad : false,
			columns:[
			   {type : 'indexcolumn'}, 
			   {type : 'checkcolumn'},
			   {header:'任务名称',field:'job_name',visible : false},
			   {header:'任务描述',field:'description'},
			   {header:'任务CLASS',field:'job_class_name'}],
			tools:[{
				html:'新增',
				plain : true,//按钮是否有立体感
				iconCls:'icon-add',
				handler:function(miniTable, buttonText){
					addJob();
				}
			},{
				html:'修改',
				iconCls:'icon-edit',
				plain : true,//按钮是否有立体感
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
					if(selectedRowDatas.length > 0){
						var rowData = selectedRowDatas[0];
						modifyJob(rowData);
					} else {
						mini.alert("请先选择数据");
					}
				}
			},{
				html:'删除',
				iconCls:'icon-remove',
				plain : false,//按钮是否有立体感
				handler:function(miniTable, buttonText){
					var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
					if(selectedRowDatas.length > 0){
						var rowData = selectedRowDatas[0];
						removeJob(rowData);
					} else {
						mini.alert("请先选择数据");
					}
				}
			}],
			callBack:function(){
				
			}
		});
		
		new tracywindyComboBox({
			id:'combo_quartzJob',
			renderTo:'id_quartzJobContainer',
			loadModel:'ajax',
			lazyLoad:true,
			dropListHeight:200,
			width:197,
			url:'${pageContext.request.contextPath}/quartz/getQuartzJobs.action',
			readOnly:true,
			value:'',
			isCheck:false,
			displayField:'name',
			valueField:'value',
			onSelect:function(combo){
				
			}
		});
	});
	
	function resetJob(resetValue) {
		var comboQuartzJob = getTracywindyObject("combo_quartzJob");
		comboQuartzJob.value = resetValue;
		comboQuartzJob.reload();
	}
	
	function addJob() {
		mini.get("id_jobName").setValue("");
		mini.get("id_description").setValue("");
		resetJob("");
		mini.get("id_quartzJobDetailInfoForm").show();
	}
	
	function modifyJob(rowData) {
		mini.get("id_jobName").setValue(rowData['job_name']);
		mini.get("id_description").setValue(rowData['description']);
		//var comboQuartzJob = getTracywindyObject("combo_quartzJob");
		resetJob(rowData['job_class_name']);
		//comboQuartzJob.setValue(rowData['job_class_name']);
		//mini.get("#id_jobClassName").setValue(rowData['job_class_name']); 
 		mini.get("id_quartzJobDetailInfoForm").show();
	}
	
	function doQuartzJobOperationClose() {
		mini.get("id_quartzJobDetailInfoForm").hide();
	}
	
	function doQuartzJobOperation() {
		var jobName = mini.get("id_jobName").getValue();
		var description = mini.get("id_description").getValue();
		//var jobClassName = mini.get("id_jobClassName").getValue();
		var comboQuartzJob = getTracywindyObject("combo_quartzJob");
		jobClassName =  comboQuartzJob.getValue();
		//if(!jobName){mini.alert("任务名称不能为空");return;}
		if(!description){mini.alert("任务描述不能为空");return;}
		if(!jobClassName){mini.alert("任务执行CLASS不能为空");return;}

		var params = {};
		params['jobName'] = jobName;
		params['description'] = description;
		params['jobClassName'] = jobClassName;

		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		
		mini.get("id_quartzJobDetailInfoForm").hide();
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/saveOrUpdateJob.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("id_jobsTable").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:params
  		});
	}
	
	function removeJob(rowDate) {
		mini.mask({
            el: document.body,
            cls: 'mini-mask-loading',
            html: '操作进行中，请稍等...'
        });
		tenwa.ajaxRequest({
			url:'${pageContext.request.contextPath}/quartz/removeJob.action',
			method:'POST',
			success:function(res){
				mini.unmask(document.body);
				mini.alert("操作成功",'提示',function(){
					mini.get("id_jobsTable").reload();
				});
			},
			failure:function(res){
				mini.unmask(document.body);
				mini.alert("操作失败");
			},
			params:rowDate
  		});
	}
	</script>
</head>
<body>
	<div id="id_jobContainer"></div>
	
	<div id="id_quartzJobDetailInfoForm" title="调度任务" class="mini-window" style="display:none;width:350px;height:180px;">
		<input type="text" id="id_jobName" class="mini-hidden"/>
		<table align="center" style="width:100%">
			<tr>
				<td style="width: 75px;">任务CLASS:</td>
				<td style="width: 205px;"><div id="id_quartzJobContainer"></div></td>
				<td style="width: 70px;"><span class="content-required">*</span></td>
			</tr>
			<tr>
				<td>任务描述:</td>
				<td><textarea id="id_description" class="mini-textarea" style="width: 200px;"></textarea></td>
				<td><span class="content-required">*</span></td>
			</tr>
			<tr class="content-separator">
				<td colspan="3" align="center">
					<a class="mini-button" onclick='doQuartzJobOperation'><span>确定</span></a>
					<a class="mini-button" onclick='doQuartzJobOperationClose'><span>取消</span></a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>