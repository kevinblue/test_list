<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>总经理办公会议案</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
   jQuery(function(){
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id: "id_tasks_table",
				renderTo: "id_tasksContainer",
				width: globalClientWidth,
				height: globalClientHeight,
				lazyLoad: false,
				showPager : true,//分页按钮是否显示
				title:'总经理办公会议案',
				remoteOper : true,
				entityClassName : 'com.tenwa.leasing.entity.conference.ConferenceDecision',			
				multiSelect: false,
				loadMode : 'ajax',
				queryButtonNewLine:true,
				queryButtonColSpan:6,
				//tools:['globalQuery','_','exportExcel'],
				xmlFileName:"/eleasing/jsp/conference_decision/conference_decision.xml",
				tools : [{
					html: '总经理办公会议案',
					plain: true,
					iconCls: 'icon-addfolder',
					handler: function(miniTable, buttonText){
							var multiform = new mini.Form("multiform");
							var multieditWindow = mini.get("multieditWindow");
							multiform.clear();
							tenwa.createQueryInput({
								id:'id_projname_list',
								label:'项目名称',
								windowWidth: 450,
								windowHeight: 700,
								textField: 'name',
								valueField: 'value',
								params: {
									//contractid:row.id,
								    xmlFileName:'/eleasing/jsp/conference_decision/conference_projname_list.xml'
									}
								});
							multieditWindow.show();
							//var attachmentParams = "letter_id="+row.id;
							/* var log_user='${sessionScope.login_userid}';
							mini.alert(log_user);
							var attachmentParams ="letter_user="+log_user; */
							//mini.alert(loginUserId);
					}
				}],			
				columns: [
					{type:'indexcolumn',width:15},
					{field: 'id', header: 'id', visible: false},
					{field: 'projname', header: '项目名称',headerAlign:'center',
						queryConfig:{newLine:true,type:'text'}
					},
					{field: 'confername', header: '议案标题',headerAlign:'center',
						queryConfig:{type:'text'},
					},
					//{field: 'decisionpoint', header: '决策点',headerAlign:'center'},
					{field: 'confernumber', header: '会议编号',headerAlign:'center',visible: false},
					{field: 'originator', header: '提案人',headerAlign:'center',
						queryConfig:{newLine:false,type:'text'}
					},
					{field: 'originatime', header: '发起时间',headerAlign:'center',
						/* queryConfig:{type:'date',	  		  									      
		  		  			  		range:true,
									defaultValue:mini.formatDate(new Date(),"yyyy-MM-dd"),
								   	format:'yyyy-MM-dd'} */
					},
					
					/*
					{field: 'conferabstract', header: '议案内容',headerAlign:'center',
						queryConfig:{type:'text'},
					},
				    {field: 'conferdescription', header: '会议内容',headerAlign:'center',
						formEditorConfig:{
						newLine:true,
						colspan:'4',
						width:'100%',
						type:'textarea'
					}}, */
						{field : '',
							header : '操作',
							renderer : function(e) {
								var id = e.record.confernumber;
								return "<a href='javascript:void(0);' onclick='showanduploadfile(\""
										+ id
										+ "\",\"one\")'>查看文件 </a>";
							}
						}
				]
				
			});
		});
	});
</script>
</head>
<body>
	<div id="id_tasksContainer" style="height: 100%"></div>
	<div id="multieditWindow" class="mini-window" title="项目名称" style="width:350px;height:120px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr>
                    <td style="width:80px;">项目名称：</td>
                    <td >
                       <input id="id_projname_list" name="dunninginfo" class="mini-buttonedit mini-queryinput" allowInput = "false" />
                    </td>
                </tr>  
                <tr >
                    <td >
                        <a class="mini-button" onclick="submitMultiDataWindReport">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                    <td >
                        <a class="mini-button" onclick='__userOperationClose'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
</html>
<script type="text/javascript">
  function showanduploadfile(id, type) {
	var urlFlag = getRootPath()
			+ "/leasing/conference/conference_decision_file_list.bi?id="
			+ id + "&type=" + type;
	mini.open({
		url : urlFlag,
		title : "查看会议文件",
		width : 800,
		height : 455,
		onload : function() {
		},
		ondestroy : function(action) {
			if ("savesuccess" == action) {
				window.location.reload();
			}
		}
	});
}
function submitMultiDataWindReport(e){
	var projid = mini.get("id_projname_list").getValue();
	if(projid == null || projid == ""){
		mini.alert("请先选择项目名称！");
	}else{
	var loginUserId = "${empty sessionScope.loginUser.id ? '' : sessionScope.loginUser.id }";
	var attachmentParams = "proj_id="+projid+"&login_userid="+loginUserId;
	startProcess("总经理办公会议案-1",attachmentParams);
	__userOperationClose();
	}
};
function __userOperationClose(){
	mini.get(multieditWindow).hide();
}
</script>