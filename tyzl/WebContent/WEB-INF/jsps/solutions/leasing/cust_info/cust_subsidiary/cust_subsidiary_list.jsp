<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var custid ="<mini:param  name='id'/>";
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'cust_subsidiary',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			pageSize:15,
			showPager:true,
			renderTo:'id_cust_subsidiary',
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_subsidiary/cust_subsidiary_list.xml',
			params:{'custid':custid},
			columns:[ 
				    {type:'indexcolumn'},
				    {type:'checkcolumn'},
				    {field:'id',header:'id',visible : false},
				    {field:'custname',header:'客户名称',headerAlign:'center'},
				    {field:'custnumber',header:'客户编号',headerAlign:'center'},
				    {field:'orgcode',header:'组织机构代码',headerAlign:'center'},
				    {field:'custtype',header:'客户类别',headerAlign:'center'},
				    {field:'custkind',header:'客户内部行业',headerAlign:'center'}
				    ]
		});
	});
});
function addsubsidiary(){

	var companyid=mini.get("subsidiary").getValue();
	$.ajax({
        url: getRootPath()+"/acl/addSubsidiary.acl",
        data :{ "custid": custid,"companyid":companyid},
        async : false,
        success: function (text) {
        	mini.alert("添加成功！");
        	mini.get('id_table_subsidiary').hide();
        	mini.get("cust_subsidiary").reload();
        }
    });
}
function deletesubsidiary(){
	if(null!=mini.get("cust_subsidiary").getSelected())
	{
		var companyid=mini.get("cust_subsidiary").getSelected().id;
	
		$.ajax({
	        url: getRootPath()+"/acl/deleteSubsidiary.acl",
	        data :{"companyid":companyid},
	        async : false,
	        success: function (text) {
	        	mini.alert("删除成功！");
	        	mini.get("cust_subsidiary").reload();
	        }
	    });
	}else
	{
		mini.alert("请选择数据！");
	}
}

</script>
<div class="mini-toolbar" id="id_finance_tool">
		<a class="mini-button" iconCls="icon-upload" onclick="javascript:{mini.get('id_table_subsidiary').show()}">新增</a><span class="separator"></span>
	<a class="mini-button" iconCls="icon-upload" onclick="deletesubsidiary()">删除</a>
</div>
<div class="mini-table"style="width: 100%; height: 100%; padding: 5px auto auto 5px;" id="id_cust_subsidiary">

</div>
    <div id="id_table_subsidiary" class="mini-window" title="添加下属子公司"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<table style="width: 100%">
			<tr style="width: 100%;">
			    <td style="width: 20%;">选择子公司：</td>
				<td style="width: 70%;"><input name="subsidiary" id='subsidiary'
					class="mini-combobox" label="客户经理" textField="custname" required="true"
					valueField="id" dataField="datas" allowInput="false"
					data-options="{_xmlFileName:'/eleasing/jsp/cust_info/cust_company/cust_company_list.xml'}"
					onbeforeshowpopup="onbeforeshowpopup"
					onvaluechanged="comboboxChanged" style="width: 90%;"/> <font class="required-tag">*</font>
					<input id="rawValue_changemanage"
					name="rawValue_changemanage"
					class="mini-textbox" style="display: none" /></td>
			</tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="addsubsidiary">确定</a>
			    &nbsp;&nbsp;
			    <a class="mini-button" iconCls="icon-save" plain="true" onclick="javascript:{mini.get('id_table_subsidiary').hide()}">取消</a>
			    </td>
			</tr>
		</table>
	</div>