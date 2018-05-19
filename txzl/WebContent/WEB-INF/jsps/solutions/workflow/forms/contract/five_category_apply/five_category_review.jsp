<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>资产分类审核与上报</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;
	var statusdata = [ {name : '',value : ''},{name : '审核中',value : "审核中"},{name : '审核通过',value : '审核通过'},{name : '已退回',value : '已退回'}];

	jQuery(function() {
		tenwa.createTable({
					id : "five_category_review",
					renderTo : "id_table_container_ive_category_review",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "资产分类审核与上报",
					remoteOper : false,
					showPager : true,
					pageSize : 20,
					multiSelect : false,
					tools : [					         							
							{
								html : '发起资产分类流程',
								plain : true,
								iconCls : 'icon-ok',
								handler : function(miniTable, buttonText) 
								{
									
									var generatedform = new mini.Form("generatedform");
									generatededitWindow = mini.get("generatededitWindow");
									generatededitWindow.show();
									generatedform.clear();  																		
									/* var attachmentParams = "fiveids="+fiveids;
									startProcess("资产分类流程-1",attachmentParams);  */
								}
							}],
					queryButtonColSpan : 8,
					queryButtonNewLine:true,
					xmlFileName : '/eleasing/jsp/other/five_category_apply_review.xml',
					//params :{apply_status :'审核中'},
					columns : [
								{type : 'indexcolumn'},
								{type : 'checkcolumn'},
								{field : 'id',header : 'id',visible : false},
								/* {field : 'viewdetails',header : '查看明细',renderer : function(e) {
									var id = e.record.id;
									return "<a href='javascript:showDetail(\""
											+ id + "\")'>"
											+ "查看明细" + "</a>";}}, */
								{field : 'apply_number',header : '申请编号',queryConfig:{}}, 
								{field : 'applyusername',header : '申请人',queryConfig:{}}, 								
								/* {field : 'apply_date',header : '申请日期',queryConfig:{}},  */
								{field : 'apply_memo',header : '备注'}, 
								{field : 'start_date',header : '开始日期',queryConfig:{}},
								{field : 'end_date',header : '结束日期'}
								]
				});
	});

function showDetail(id) {
	var url = getRootPath()
			+ "/acl/showFiveCategoryApplication.acl?applyid="
			+ id + "&opertype=view"
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winosption = "left=0px,top=0px,height="
			+ sheight
			+ "px,width="
			+ swidth
			+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winosption);
}
//发起资产分类审核和报告流程
function classifyaudit(){
	//获取点击的日期作为参数传到流程里面
	var startdate=mini.get("startdate").getValue();
	var enddate=mini.get("enddate").getValue();
	 if(startdate =='' || enddate==''){
		mini.alert("请填写日期 "); return false;
	} 
	startdate=mini.formatDate(startdate, "yyyy-MM-dd");
	enddate=mini.formatDate(enddate, "yyyy-MM-dd");	
	
	var attachmentParams = "startdate="+startdate;
	    attachmentParams=attachmentParams+"&enddate="+enddate;
		startProcess("资产分类审核上报-1",attachmentParams);
}
</script>
<div id="generatededitWindow" class="mini-window" title="选择五级分类日期" style="width:323px;height:150px;"   showModal="true" allowResize="true" allowDrag="true">
        <div id="generatedform">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:80px;">开始日期：</td>
                    <td >
                        <input id="startdate" name="startdate" class="mini-datepicker miniext-form-fit" allowInput="false"/>
                    </td>
                </tr>
                <tr>
                     <td style="width:80px;">结束日期：</td>
                    <td >
                        <input id="enddate" name="enddate" class="mini-datepicker miniext-form-fit" allowInput="false"/>
                    </td>
                </tr>
                <tr></tr> 
                <tr>                
                 <td></td>                 
                    <td style='margin:auto;'> 
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="mini-button "  onclick="classifyaudit">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                   </td> 
                </tr>   
            </table>
        </div>
	</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
</head>
<body>
	<div id="id_table_container_ive_category_review"></div>
</body>
</html>