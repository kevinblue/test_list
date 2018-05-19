<%@ page language="java" import="java.net.URLDecoder" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>租金催收</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<%String contractid=request.getParameter("contractid");
String contractnumber=request.getParameter("contractnumber");
String custname=URLDecoder.decode(request.getParameter("custname"),"UTF-8");
String province=URLDecoder.decode(request.getParameter("province"),"UTF-8"); 
String rentlist=URLDecoder.decode(request.getParameter("rentlist"),"UTF-8");
String plandate=request.getParameter("plandate");
String rent=request.getParameter("rent");
String corpus=request.getParameter("corpus");
String interest=request.getParameter("interest");
String currentincome=request.getParameter("currentincome");
String curcorpusincome=request.getParameter("curcorpusincome");
String curinterestincome=request.getParameter("curinterestincome");
String currentoverage=request.getParameter("currentoverage");
String curcorpusoverage=request.getParameter("curcorpusoverage");
String curinterestoverage=request.getParameter("curinterestoverage");
String curpenaltyincome=request.getParameter("curpenaltyincome");
String custclass=request.getParameter("custclass");
  String custid=request.getParameter("custid");
  String planid=request.getParameter("planid");
  String id=request.getParameter("id");
  String specialrequirement=URLDecoder.decode(request.getParameter("specialrequirement"),"UTF-8");%>
<script type="text/javascript">
var contractid = "<%= contractid %>";
var planid = "<%= planid %>";
var custid = "<%= custid %>";
var screenHeight = window.screen.height;
var fixedHeight = 300;
var topHeight = 460;
//合同信息
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : 'table_id1',
			width : "100%",
			height : fixedHeight,
			iconCls : 'icon-node',
			renderTo:'content_table_id1',
			remoteOper : true,
			pageSize : 10,
			showPager : true,
			lazyLoad : false,
			xmlFileName : '/eleasing/jsp/fund/fund_overdue/contract_list_remind.xml',
			entityClassName:'com.tenwa.leasing.entity.fund.overdue.OverdueDunningRecord',
			params : {planid :planid},
			tools:['edit','-','remove'],			
			columns : [ 
			         {type:'indexcolumn',width:10},
			         {type:'checkcolumn',width:10},
			         {field:'id',header:'编号',headerAlign:'center',width:100,allowSort:true,visible:false},
					 {field:'personname',header:'联系人',headerAlign:'center',width:30,
			        	 formEditorConfig:{
			        		 readOnly : true,
			        		 newLine:true,
			        	 }
			         },
					 {field:'contractdate',header:'催收时间',headerAlign:'center',width:20,
						 formEditorConfig:{
							 readOnly : true,
							 newLine:true,
			        	 }
			         },
					 {field:'contactwayname',header:'联系方式',headerAlign:'center',width:20,visible:true,
						 formEditorConfig:{
							 fieldVisible:false,
							 vosible:false,
							 fillFromFieldName:'contactway',
							 fillProperty:'name',
							 newLine:true,
						 }
					 },
					 {field:'contactway',header:'联系方式',headerAlign:'center',width:100,visible:false,
						 formEditorConfig : {
								type:'combobox',
								newLine:true,
								params : {
									pid:'visit_mode',
									xmlFileName : 'combos/comboDict.xml'
								},
								readOnly:false,
								fieldVisible:true,
								textField:'name',
								valueField:'value'
							}
					 },
					 {field:'commitmentinfo',header:'联系情况',headerAlign:'center',width:100,
						 formEditorConfig : {
								newLine:true,
								colspan : 4,
								height : 70,
								width : '100%'
								
							}	 
					 }
					 ]
		});
	});
});

function onhireTypeComboboxChanged(e){
	document.getElementById('contactWay').value = e.sender.text;
}
//窗体关闭退出时刷新父窗口
window.onunload = function(){ 
	window.opener.location.reload(); 
} 
function opencustdetail(id,custclass){
 	var params = "id="+id+"&isView=true";
 	var url="";
	if(custclass=="CUST_INFO_COMPANY"){
		url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
	}else{
		url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
	}
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winoption);
}

function zijinshoufu(cid)
{
	var url = getRootPath()+"/acl/queryContractInfoDetail.acl?contractid="+cid;
	openFullScreenWindow(url);
}

function addt(specialrequirement) {
	//mini.get("id_specialrequirement").setValue(row.specialrequirement==null ? "" : row.specialrequirement);
	//mini.get("id_win_add_specialrequirement").show();
	var multiform = new mini.Form("id_div_distri_manager");
	var multieditWindow = mini.get("id_win_add_specialrequirement");
	multieditWindow.show();
	multiform.clear();
	isMulti = true;
	mini.get("id_specialrequirement").setValue(mini.get("specialrequirement").getValue());
}

function addspecialrequirement(contractid){
	/* if (miniui_ext.submitFormValidation(["id_signdate"]) == false){
		alert("请选中项目！");
		return false}; */
	var specialrequirement=mini.get("id_specialrequirement").getValue();
	var contractid = contractid;
	var params = {};
	params.contractid = contractid;
	params.specialrequirement=specialrequirement;
	$.ajax({
		url : '${pageContext.request.contextPath}/acl/saveSpecialrequirement.acl',
		method : 'POST',
		success:function(res){
			//console.info(res);//控制台显示info信息
			var scustinfo = res.responseText;
			mini.get("specialrequirement").setValue(mini.get("id_specialrequirement").getValue());
			mini.get("id_win_add_specialrequirement").hide();
			miniTable.reload();
		},
		async : false,
		failure : function(res) {
			currentLoadMask.hide();
		},
		data : params
	});
}
</script>
</head>
<body style="overflow: auto">
<div class="mini-panel" title="催收信息" style="width: 100%;">
	<div style="margin:auto;padding:auto;">
		<table style="width:100%;">
			<tr>
				<td>合同编号：</td>
				<td>
					 <input name="contractid" id="contractid" class="mini-textbox" readOnly value="<%=contractid %>"/>
					 <a href="javascript:void(0);"><img alt="合同信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="zijinshoufu('<%=id %>')"/></a>
				</td>
				<td>业务合同号：</td>
				<td>
					 <input name="contractnumber" id="contractnumber" class="mini-textbox" readOnly value="<%=contractnumber %>"/>
				</td>
				<td>客户名称：</td>
				<td><input name="custclass" id="custclass" class="mini-textbox" value=<%=custclass %> style="display:none"/>
					 <input name="custname" id="custname" class="mini-textbox" readOnly value="<%=custname %>"/>
					 <a href="javascript:void(0);"><img alt="客户信息" style="vertical-align:middle;" src="${pageContext.request.contextPath}/menuIcons/search.png" 
	                     onclick="opencustdetail('<%=custid %>','<%=custclass %>')"/></a>
				</td>
				<td>省份：</td>
				<td>
					 <input name="province" id="province" class="mini-textbox" readOnly value="<%=province %>"/>
				</td>
			</tr>
			<tr>
				<td>计划期项：</td>
				<td>
					 <input name="rentlist" id="rentlist" class="mini-textbox" readOnly value="<%=rentlist %>"/>
				</td>
				<td>计划日期：</td>
				<td>
					 <input name="plandate" id="plandate" class="mini-textbox" readOnly value="<%=plandate %>"/>
				</td>
				<td>租金：</td>
				<td>
					 <input name="rent" id="rent" class="mini-textbox" readOnly value="<%=rent %>"/>
				</td>
				<td>本金：</td>
				<td>
					 <input name="corpus" id="corpus" class="mini-textbox" readOnly value="<%=corpus %>"/>
				</td>
			</tr>
			<tr>
				<td>利息：</td>
				<td>
					 <input name="interest" id="interest" class="mini-textbox" readOnly value="<%=interest %>"/>
				</td>
				<td>实收租金：</td>
				<td>
					 <input name="currentincome" id="currentincome" class="mini-textbox" readOnly value="<%=currentincome %>"/>
				</td>
				<td>实收本金：</td>
				<td>
					 <input name="curcorpusincome" id="curcorpusincome" class="mini-textbox" readOnly value="<%=curcorpusincome %>"/>
				</td>
				<td>实收利息：</td>
				<td>
					 <input name="curinterestincome" id="curinterestincome" class="mini-textbox" readOnly value="<%=curinterestincome %>"/>
				</td>
			</tr>
			<tr>
				<td>剩余租金：</td>
				<td>
					 <input name="currentoverage" id="currentoverage" class="mini-textbox" readOnly value="<%=currentoverage %>"/>
				</td>
				<td>剩余本金：</td>
				<td>
					 <input name="curcorpusoverage" id="curcorpusoverage" class="mini-textbox" readOnly value="<%=curcorpusoverage %>"/>
				</td>
				<td>剩余利息：</td>
				<td>
					 <input name="curinterestoverage" id="curinterestoverage" class="mini-textbox" readOnly value="<%=curinterestoverage %>"/>
				</td>
				<td>实收罚息：</td>
				<td>
					 <input name="curpenaltyincome" id="curpenaltyincome" class="mini-textbox" readOnly value="<%=curpenaltyincome %>"/>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<tr style="width:30%;">
			<td style="width:200px;">特殊要求：</td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<td style="width:300px;">
			 	<input style="width:500px;" name="specialrequirement" id="specialrequirement" class="mini-textbox" readOnly value="<%=specialrequirement %>"/>
			</td>
			<td>
                     <a class="mini-button" iconCls="icon-edit" onclick="addt('<%=specialrequirement %>')">编辑</a>
            </td>
		</tr>
	</div>
</div>
  <div title="联系人信息显示" name="relatedPerson">
	<jsp:include page="cust_relatedperson_list01.jsp"></jsp:include>
  </div>
<div class="mini-panel" title="催收记录" showCollapseButton="true" style="width: 100%;">
	<div id='content_table_id1'></div>
</div>
<div id="id_win_add_specialrequirement" class="mini-window" title="维护特殊要求" style="width:500px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_div_distri_manager">
		<table>
             	<tr>
                    <td style="width:100px;">特殊要求：</td>
                    <td >
                    <input style="width:300px;" id="id_specialrequirement" name="id_specialrequirement" class="mini-textbox"  maxlength="30"/>
                    </td>
                    <td>
                     <a class="mini-button" iconCls="icon-edit" onclick="addspecialrequirement('<%=id %>')">确定</a>
                     </td>
                </tr>
          </table>
	</div>
</div>
</body>
</html>
