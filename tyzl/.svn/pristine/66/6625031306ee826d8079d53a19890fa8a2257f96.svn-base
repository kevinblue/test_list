<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "old_contract_guarantee_method",
		renderTo: "id_table_old_contract_guarantee_method",
		width: globalClientWidth-30,
		height: 250,
		lazyLoad: true,
		title: "",
		remoteOper : false,
		showPager: false,
		showToolbar: false,
		data: JsonUtil.decode('<mini:param  name="json_old_contract_guarantee_method_str" defaultValue="[]"/>'),
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'assurorname', header: '担保单位',
				renderer : function(e){
					var assurorname = e.record.assurorname;
					var assurorId = e.record.assuror;
					var assurorcustclass=e.record.assurorcustclass;
					return "<a style='text-decoration:underline;color:blue;cursor: pointer;' onclick='opencustdetail(\"" + assurorId +"\",\""+assurorcustclass+ "\")'>" + assurorname + "</a>";
			}},
			{field: 'assurorcustclass', header: '担保单位客户类型',visible: false},
			{field: 'assuremethodname', header: '担保类型'},
			{field: 'assurerelationname', header: '关系'},
			{field: 'cgmnote', header: '备注'}
		]
	});
});
</script>
<div id="id_table_contract_guarantee_method">
	   <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	        <tr>
	          <td class="x-panel-table-div-title" colspan=4>原担保信息</td>
	        </tr>
	    </table>
	    <div id="id_table_old_contract_guarantee_method" class="fieldset-body"></div>
	    <table cellspacing="0" cellpadding="0" width="100%" class="fillTable">
	         <tr>
	           <td class="x-panel-table-div-title" colspan=4>现担保信息</td>
	         </tr>
	     </table>
	     <div id="id_table_new_contract_guarantee_method" class="fieldset-body">
	       <jsp:include page="../../contract_comm/contract_guarantee_method.jsp" ></jsp:include>
	     </div>
</div>