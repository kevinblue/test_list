<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id6',
			width:globalClientWidth-20,
			height:420,
			iconCls:'icon-node',
			pageSize:15,
			showPager:true,
			renderTo:'content_table_id6',
			lazyLoad:false,		
			xmlFileName:'/eleasing/jsp/cust_info/cust_history/cust_history_list.xml',
			params:{custid:custid},
			columns:[ 
				    {type:'indexcolumn'},
				    {type:'checkcolumn'},
				    {field:'projid',header:'项目编号',headerAlign:'center'},
				    {field:'contractnumber',header:'业务合同号',headerAlign:'center'},
				    {field:'contractstatusname',header:'项目状态',headerAlign:'center'},
				    {field:'allcorpus',header:'融资额',align:'right',dataType :"currency",currencyUnit:"￥",headerAlign:'center'},
				    {field:'custname',header:'承租人',headerAlign:'center'},
				    {field:'projdept',header:'出单部门',headerAlign:'center'},
				    {field:'projmanager',header:'项目经理',headerAlign:'center'},
				    {field:'lastrent',header:'剩余租金',align:'right',dataType :"currency",	currencyUnit:"￥",headerAlign:'center'},
                    {field:'overlist',header:'逾期期数',align:'right',headerAlign:'center'},
                    {field:'overrent',header:'逾期租金',align:'right',dataType :"currency",currencyUnit:"￥",headerAlign:'center'},
                    {field:'overpenalty',header:'剩余罚息',align:'right',dataType :"currency",currencyUnit:"￥",headerAlign:'center'}]
		});
	});
});
</script>
<div id='content_table_id6'></div>