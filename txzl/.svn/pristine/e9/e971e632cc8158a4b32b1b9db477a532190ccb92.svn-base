<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">
<title>合同归档</title>
<%@include file="/base/mini.jsp"%>
<%
String id=request.getParameter("id");
String custname=request.getParameter("custname");
String contractnumber=request.getParameter("contractnumber");
String signdate=request.getParameter("signdate");

%>
<script type="text/javascript">
	var custname = '${param.custname}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
mini.parse();
jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			new ApTable({
				id:'table_id1',
				width:globalClientWidth,
				height:globalClientHeight-150,
				//title:'合同归档',
				iconCls:'icon-node',
				multiSelect:true,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:2,
				queryButtonNewLine:false,
				showPager:true,
				allowCellEdit:true,
				allowCellSelect:true,
				oncellbeginedit:"OnCellBeginEdit",
				ondrawcell:'OnDrawCell',
				params:{
					contract_id:"${param.id}"
				},
				xmlFileName:'/contract_back/contract_back_detail.xml',
				tools: [{
					html:'保存',
					plain : true,
					iconCls : 'icon-edit',
					handler : function(miniTable, buttonText) {
						var alldata=miniTable.getData();
						
						var url=getRootPath()+"/acl/saveallcontractdoclistborrow.acl";
						
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
							data : {datas:mini.encode(alldata),cid:"${param.id}"},
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
									mini.alert("归还成功！");
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
				    {type:'indexcolumn'},
				   	{field:'id',header:'id',visible:false},
				   	{field:'docname',header:'合同档案名称'},
					{field:'isborrow',header:'是否借出'},
					{field:'filestatus',header:'filestatus',visible:false},
				   	{field:'memoborrow',header:'借出备注(说明原因及替代方式)'},
				   	{field:'borrowman',header:'借阅人'},
				   	{field:'borrowdate',header:'借阅时间'},
				   	{field:'prebackdate',header:'预计归还日期',dateFormat : 'yyyy-MM-dd'},
				   	{field:'isback',header:'是否归还',type:'checkboxcolumn',trueValue:"1", falseValue:"0"},
				   	{field:'memoback',header:'归还备注(说明原因及替代方式)',
				   		editor:{ 
							width:'100%', 
							type:'textarea', 
							onenter:function(){
								mini.get("table_id1").commitEdit();
	                    	}
					    }
				   	},
				   	{field:'backdate',header:'归还日期',dateFormat : 'yyyy-MM-dd',
				   		editor:{ 
							width:'100%', 
							type:'datepicker',
							format : 'yyyy-MM-dd',
							showTime : true,
							onenter:function(){
								mini.get("table_id1").commitEdit();
	                    	}
					    }
				   	},
				   	{field:'backman',header:'归还人',
				   		editor:{ 
							width:'100%', 
							type:'buttonedit',
							onbuttonclick:'onButtonEdit'
					    }
				   	}
				   	/* formEditorConfig:{
			               newLine:true,
			               colspan: 3,
			                 width: '100%',
			                  type: 'queryinput',
			              required: true,
			             textField: 'name',
			            valueField: 'value',
			            allowInput: false,
			          fieldVisible: true,
		                 	params: {cust_type: 'cust_type.vndr',
              	xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}} */
				  
				]
			});
		});
	});
	
function OnCellBeginEdit(e) {
    var record = e.record, field = e.field;
    if (field == "isback" && record.filestatus == "未借出") {
        e.cancel = true;    //如果已婚，则不允许编辑性别
    }
    if (field == "memoback" && record.filestatus == "未借出") {
        e.cancel = true;    //如果已婚，则不允许编辑性别
    }
    if (field == "backdate" && record.filestatus == "未借出") {
        e.cancel = true;    //如果已婚，则不允许编辑性别
    }
    if (field == "backman" && record.filestatus == "未借出") {
        e.cancel = true;    //如果已婚，则不允许编辑性别
    }
}
function OnDrawCell(e){
    var record = e.record,
    column = e.column,
    field = e.field,
    value = e.value;
    
    if (field == "isback"  && record.filestatus == "未借出"){
    	e.cellStyle = "background-color:#efefef";
    }
    
    if (field == "memoback"  && record.filestatus == "未借出"){
    	e.cellStyle = "background-color:#efefef";
    }
    
    if (field == "backdate"  && record.filestatus == "未借出"){
    	e.cellStyle = "background-color:#efefef";
    }
    if (field == "backman"  && record.filestatus == "未借出"){
    	e.cellStyle = "background-color:#efefef";
    }
}
function onButtonEdit() {
    var btnEdit = this;
    var urlFlag = getRootPath()+"/leasing/doc_manager/contract_back/contract_back_man.bi";
    mini.open({
        url: urlFlag,
        title: "选择列表",
        width: 650,
        height: 430,
        ondestroy: function (action) {
            //if (action == "close") return false;
            if (action == "ok") {
                var iframe = this.getIFrameEl();
                var data = iframe.contentWindow.GetData();
                data = mini.clone(data);    //必须
                //alert(1);
                if (data) {
                	//alert(2);
                    btnEdit.setValue(data.usersname);
                    btnEdit.setText(data.usersname);
                    
                    
                }
            }

        }
    });            
     
}
</script>
</head>
<body>
	<div id="form1" class="mini-panel" style="width: 100%;">
		<input class="mini-textbox"  visible="false" name="contractidkey">
		<table class="fillTable">
		    <tr>
		        <td colspan="6" style="border-bottom: 1px solid #99CCFF;">
		        	<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
	        	</td>
		    </tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">客户名称:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="cust_name" value="${param.custname}" allowInput="false"></input></td>
				<td class="td-content-title" width="15%">合同编号:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="contract_id" value="${param.contractnumber}" allowInput="false"></input></td>
			</tr>
			<tr class="tr-even">
				<td class="td-content-title" width="15%">合同签订日期:</td>
				<td class="td-content" width="35%"><input class="mini-textbox" name="start_date_" value="${param.signdate}"allowInput="false"></input></td>
			</tr>
		</table>
	</div>
	<div id="table_id1"></div>
</body>
<script type="text/javascript">
   jQuery(function() {
	//初始化数据
	var data = miniui_ext.initPageData("contract_back/contract_back_init_info.xml","form1","${param.id}");
	
});   




</script>
</html>