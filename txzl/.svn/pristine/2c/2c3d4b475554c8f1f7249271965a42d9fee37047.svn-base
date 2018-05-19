<%@page import="java.net.URLEncoder"%>
<%@page import="com.tenwa.business.entity.User"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<%
			String id = request.getParameter("id");
		    
		%>
		<title>风电项目开发详情</title> <%@include file="/base/mini.jsp"%>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/js/comm/arrayUtils.js"></script>        
	    <script type="text/javascript"
			src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script> 
	    <script type="text/javascript"
	      src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
		<script type="text/javascript"
	       src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.0.min.js?${currentTimestamp}"
			type="text/javascript"></script>
		<style>
#id_table_proj_detail {
	width: 100%;
	border: 1px solid #99CCFF;
}

#id_table_proj_detail td {
	padding-left: 10px;
	padding-top: 3px;
	padding-bottom: 3px;
}

#id_table_proj_detail td .mini-textbox-border {
	background: #EEEEEE;
}

#id_table_proj_detail td .mini-buttonedit-border {
	background: #EEEEEE;
}

#id_table_proj_detail .mini-buttonedit-buttons {
	display: none;
}

#id_table_proj_detail .mini-textbox {
	width: 100%;
}

#id_table_proj_detail .mini-buttonedit {
	width: 100%;
}
</style>

		<script type="text/javascript">
var projid ="<%=id%>";

jQuery(function(){
	miniui_ext.setOddEvenRowsStyle("id_table_proj_detail");
$("#change2").show();
$("#change1").hide();
<permission:action code="cust_button_edit111">
$("#change1").show();
$("#change2").hide();
//document.getElementById("buttonedit").style.display='none';
</permission:action>
});
</script>
</head>
<body>
	<div style="width: 100%; height: 100%; overflow: auto;">
		<div id="form1" style="padding: 5px;">
			<table id="id_table_proj_detail" cellpadding="0" cellspacing="0"
				align="center">
				<c:if test="${param.isView ne true}">
					<tr>
						<td colspan="6" style="border-bottom: 1px solid #99CCFF;">
							<div id="change1">
								<!-- <a class="mini-button" iconCls="icon-edit" onclick="upd">编辑</a>&nbsp; -->
								<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
							</div>
							<div id="change2">
								<a class="mini-button" iconCls="icon-close" onclick="onCancel">关闭</a>
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
					<td width="12%">客户名称：</td>
					<td width="38%">
						<input name="custid" class="mini-textbox" style="display: none;" /> 
						<input name="custname" class="mini-textbox  asLabel" />
					</td>
					<td></td>
					<td>注册号/统一社会信用代码：</td>
					<td><input name="registcode" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td width="12%">项目名称：</td>
					<td width="38%"><input name="projname" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>业务类型：</td>
					<td>
						<input name="leasform" class="mini-textbox" style="display: none;" />
						<input class="mini-textbox asLabel" name="leasformname" />
					</td>
				</tr>
				<tr>
					<td>项目类型：</td>
					<td>
						<input name="projkind" class="mini-textbox" style="display: none;" />
						<input class="mini-textbox asLabel" name="projkindname" />
					</td>
					<td></td>
					<td width="12%">项目阶段：</td>
					<td width="38%"><input name="projectphasename" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>所处省份：</td>
					<td>
						<input name="province" class="mini-textbox" style="display: none;" /> 
						<input class="mini-textbox asLabel" name="provincename" />
					</td>
					<td></td>
					<td>地级市：</td>
					<td>
						<input name="city" class="mini-textbox" style="display: none;" /> 
						<input class="mini-textbox asLabel" name="cityname" />
					</td>
				</tr>
				<tr>
					<td>风电场地形特征：</td>
					<td><input name="windtopographyname" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td width="12%">预计限电比例(%)：</td>
					<td width="38%"><input name="expectscale" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>机组选型：</td>
					<td><input name="fantype" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>机组数量（台）：</td>
					<td><input name="fansum" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>项目容量（万千瓦）：</td>
					<td><input name="projinstalledcapacity" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>上网电价（元/度）：</td>
					<td><input name="internetprice" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>标杆电价（元/度）：</td>
					<td colspan="4"><input name="benchmarkprice" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>平均风速（米/秒）：</td>
					<td><input name="avewindspeed" class="mini-textbox  asLabel" />
					</td>
					<td></td>
					<td>年等效满负荷小时数（小时）：</td>
					<td><input name="equhours" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>年理论发电量（万度）：</td>
					<td><input name="theoryproduction" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>年上网电量（万度）：</td>
					<td><input name="internetpower" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>项目静态投资（万元）：</td>
					<td><input name="projinvestment" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>千瓦造价（元）:</td>
					<td><input name="kwcost" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>项目资本金（万元）：</td>
					<td><input name="projcapital" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>融资金额（万元）：</td>
					<td><input name="rzzlamount" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>（预计）投产时间：</td>
					<td><input name="productiontime" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>项目来源:</td>
					<td><input name="projsourcename" class="mini-textbox  asLabel" /></td>
				</tr>
				<tr>
					<td>项目审批情况：</td>
					<td colspan="4"><input name="projappstatus" class="mini-textarea  asLabel" /></td>
				</tr>	
				<tr>
					<td>项目补充信息：</td>
					<td colspan="4"><input name="note" class="mini-textarea  asLabel" /></td>
				</tr>
				<tr>
					<td>项目联系人：</td>
					<td><input name="linkman" class="mini-textbox asLabel"/></td>
					<td></td>
					<td>联系方式：</td>
					<td><input name="contactway" class="mini-textbox asLabel"/></td>
				</tr>
				<tr>
					<td width="12%">天信对接人：</td>
					<td width="38%">
						<input name="txpeople" class="mini-textbox" style="display: none;" /> 
						<input name="txpeoplename" class="mini-textbox  asLabel" />
					</td>
					<td></td>
					<td>登记人：</td>
					<td><input name="creatorname" class="mini-textbox  asLabel" /></td>
				</tr>

				<tr>
					<td>登记部门</td>
					<td><input name="registdeptname" class="mini-textbox asLabel" /></td>
					<td></td>
					<td>登记时间：</td>
					<td><input name="createdate" class="mini-datepicker  asLabel" /></td>
				</tr>
				<%-- 
				<tr>
					<td>修改人：</td>
					<td><input name="modificatorname" class="mini-textbox  asLabel" /></td>
					<td></td>
					<td>修改时间：</td>
					<td><input name="modifydate" class="mini-datepicker  asLabel" /></td>
					<td></td>
				</tr>
				--%>
			</table>
		</div>
	</div>


	<script>

mini.parse();
var form = new mini.Form("form1");

$.ajax({              
    url: urlPrefix+"/eleasing/workflow/proj/proj_search/get_projwind_byid.xml&id=<%=id%>",
    cache: false,
    success: function (text) {   
    	var result = mini.decode(text);
    	miniui_ext.restoreFromDataSpecialChar(result.datas[0]);
        form.setData(result.datas[0]);
      //  miniui_ext.inittextboxloaddata("form1",result.datas[0]);
        miniui_ext.initformreadOnly("form1");//设置表单为只读
        form.setChanged(false);
        
    }
});

<%-- function upd(){
	var urlFlag = getRootPath()+"/leasing/cust_info/cust_company/input_cust_company_list.bi?id=<%=id%>
		";
			var saveUrl = getRootPath() + "/acl/updateCustLaw.acl";
			mini.open({
				url : urlFlag + "&opertype=update&saveurl=" + saveUrl,
				title : "编辑法人信息",
				width : 800,
				height : 500,
				onload : function() {

				},
				ondestroy : function(action) {
					if ("savesuccess" == action) {
						window.location.reload();
					}
				}
			});
		}
		function custChangTab(e) {
			if (e.name == "relatedPersonHis") {
				mini.get("table_hisd").load();
			}
		} --%>
	</script>
</body>
</html>