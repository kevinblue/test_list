<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>选对比的流程</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<%
String keyone=request.getParameter("keyone");
String stepid=request.getParameter("stepid");
%>
<style type="text/css">
#id_table_cust_info_edit {
	background: #FAFAFA;
	width: 100%;
	border: 1px solid #99CCFF;
}
#id_table_cust_info_edit td{
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
	border-bottom: 1px dotted #CCCCCC;
}
.mini-textbox-readOnly .mini-textbox-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-border {
	background-color: #EEEEEE;
}
.mini-buttonedit-readOnly .mini-buttonedit-buttons{
	display: none;
}
</style>
</head>
<body>
<form method="post" id="id_flowdif" action="${pageContext.request.contextPath}/jbpm/getTwoFlowDifDate.action">
<div class="mini-fit">
	<div id="form1" title="选择对比流程" style="padding: 5px 40px 5px 40px;">
		<table id="id_table_cust_info_edit" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		        <td width="35%">流程名称</td>
		        <td width="30%">流程发起时间</td>
		        <td width="35%">流程步骤</td> 
		    </tr>
		    <tr>
		        <td>
		            <div id="id_flowname" class="mini-listbox" onvaluechanged="loadFlowendTime" style="width:100%;height:350px;" textField="name" valueField="name"></div>
                </td>
		        <td>
		            <div id="id_flowdate" class="mini-listbox" onvaluechanged="loadFlowStep"  style="width:100%;height:350px;" textField="name" valueField="value"></div>
		        </td>
		        <td>
		            <div id="id_flowstep" class="mini-listbox" style="width:100%;height:350px;" textField="name" valueField="value"></div>
		        </td> 
		    </tr>
		</table>
	</div>
	<div>
	   <input id ="id_fromflowid" name="fromflowid" class="mini-textbox" style="display: none;"/>
	   <input id ="id_toflowid" name="toflowid" class="mini-textbox" style="display: none;"/>
	</div>
</div>
<div class="mini-toolbar miniext-toolbar-bottom">
    <table class="miniext-form-fit">
        <tr>
            <td>
                <a class="mini-button" onclick="loadFlowDeffData" style="width:80px">确定</a>&nbsp;&nbsp;
                <a class="mini-button" onclick="onCancel" style="width:80px">取消</a>
            </td>
        </tr>
    </table>           
</div>
</form>
</body>
</html>
<script language="javascript">
   var keyone="<%=keyone%>";
   var mainstepid="<%=stepid%>";
   loadFlowName(keyone);
    function loadFlowName(keyone){
	    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在加载数据   请稍等...'});
		var param=[];
		param["xmlFileName"]="/jbpm/flowContrastName.xml";
		param["keyone"]=keyone;
		ajaxRequest({
			url:getRootPath()+'/table/getTableData.action',
			method:'POST',
			success:function(rs){
			var message= rs.responseText;
				message=message.replace(/(^\s+)|(\s+$)/g, ""); 
				var returnobj=eval("("+message+")")
				mini.parse();
				var flowlist=mini.get("id_flowname");
				flowlist.removeAll();
				flowlist.addItems(returnobj.datas);
				mini.unmask(document.body);
			},
			async:false,
			failure:function(res){
				mini.unmask(document.body);
			},
			params:param
		 });
    }
	function loadFlowendTime(e) {                              
        var listbox = e.sender;
        var flowname = listbox.getValue();
        mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在加载数据   请稍等...'});
		var param=[];
		param["xmlFileName"]="/jbpm/flowContrastStartDate.xml";
		param["flowname"]=flowname;
		param["keyone"]=keyone;
		ajaxRequest({
			url:getRootPath()+'/table/getTableData.action',
			method:'POST',
			success:function(rs){
			var message= rs.responseText;
				message=message.replace(/(^\s+)|(\s+$)/g, ""); 
				var returnobj=eval("("+message+")")
				mini.parse();
				var flowlist=mini.get("id_flowdate");
				flowlist.removeAll();//删除流程日期
				flowlist.addItems(returnobj.datas);
				flowlist=mini.get("id_flowstep");
				flowlist.removeAll();//删除流程步骤数据
				mini.unmask(document.body);
			},
			async:false,
			failure:function(res){
				mini.unmask(document.body);
			},
			params:param
		 });
    }
	function loadFlowStep(e){
		 var listbox = e.sender;
	     var flowunid = listbox.getValue();
	     mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在加载数据   请稍等...'});
			var param=[];
			var flownameobj=mini.get("id_flowname");
			var flowname=flownameobj.getValue();
			param["xmlFileName"]="/jbpm/flowContrastStep.xml";
			param["flowunid"]=flowunid;
			ajaxRequest({
				url:getRootPath()+'/table/getTableData.action',
				method:'POST',
				success:function(rs){
				var message= rs.responseText;
					message=message.replace(/(^\s+)|(\s+$)/g, ""); 
					var returnobj=eval("("+message+")")
					mini.parse();
					var flowlist=mini.get("id_flowstep");
					flowlist.removeAll();
					flowlist.addItems(returnobj.datas);
					mini.unmask(document.body);
				},
				async:false,
				failure:function(res){
					mini.unmask(document.body);
				},
				params:param
			 });
	}
	function loadFlowDeffData(){
		var stepid=mini.get("id_flowstep").getValue()||"";
		if(stepid==""){alert("请选要比较的流程步骤");return false;}
		var mainid=mainstepid;
		var form=$("#id_flowdif");
		mini.get("id_fromflowid").setValue(mainid);
		mini.get("id_toflowid").setValue(stepid)
		mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在比较数据中   请稍等...'});
		form.submit();
		
	}
</script>