<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script type="text/javascript">
jQuery(function(){
	//获取父页面中保存在hidden域的值
	var showTools = true;
	if('${param.isView}'== 'true')
	{
		showTools = false;
	}
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
		id: "pledge_contract",
		renderTo: "id_table_cancled",
		width : globalClientWidth-30,
		height : 400,
		lazyLoad: true,
		isClickLoad:false,
		multiSelect : true,
		title: "",
		remoteOper : false,
		showPager: false,
		data: JsonUtil.decode('${empty json_pledge_contract_str ? "[]" : json_pledge_contract_str }'),
		columns: [
			{type : 'indexcolumn',header : '序号'},
			{type : 'checkcolumn'},
            {field : 'id',header : '编号',headerAlign : 'center',width : 100,allowSort : true,visible : false
			},
			{field : 'registypeidname',header : '登记类型',headerAlign : 'center',visible : true
			},
			{field : 'contractid',header : '抵质押登记合同编号',headerAlign : 'center',visible : true,width : 150
			},
			{field : 'regisnum',header : '登记证明编号',headerAlign : 'center',visible : true,width : 150
			},
			
			{field : 'pledgetime',header : '抵质押注销时间',headerAlign : 'center',visible : true,width : 150
			},
			{field : 'pledgereason',header : '抵质押注销原因',headerAlign : 'center',visible : true,width : 150
			},
			
			{field : 'regisunit',header : '登记单位',headerAlign : 'center',visible : true,width : 150
			},
			{field : 'assurorname',header : '担保单位',headerAlign : 'center',visible : true
			},
			{field : 'pledgeownner',header : '抵质押物所在企业/自然人',headerAlign : 'center',width : 200,visible : true	},
			{
				field : 'pledgevalue',header : '抵质押物数额/原值',width : 150,headerAlign : 'center',visible : true,width : 150
			},
			{
				field : 'pledgevaluenow',header : '抵质押物现值',width : 150,headerAlign : 'center',	visible : true,	width : 150
			},
			{
				field : 'assurortotalval',header : '被担保债权总额/主合同金额',headerAlign : 'center',visible : true,	width : 200
			},
			 {
				field : 'descriptionone',header : '抵质押物描述',headerAlign : 'center',	visible : true,	width : 150
			}, 
			{
				field : 'debtstart',header : '债务履行期起始日',headerAlign : 'center',visible : true,width : 150,dateFormat : "yyyy-MM-dd"
			}, {
				field : 'debtend',
				header : '债务履行期终止日',
				headerAlign : 'center',
				visible : true,
				width : 150,
				dateFormat : "yyyy-MM-dd"
			}, 
			{
				field : 'pledgestart',
				header : '抵质押登记起始日',
				headerAlign : 'center',
				visible : true,
				width : 150,
				dateFormat : "yyyy-MM-dd"
			}, {
				field : 'pledgeend',
				header : '抵质押登记到期日',
				headerAlign : 'center',
				visible : true,
				width : 150,
				dateFormat : "yyyy-MM-dd"
			}, 
			{
				field : 'registime',
				header : '登记时间',
				headerAlign : 'center',
				visible : true,
				width : 100,
				dateFormat : "yyyy-MM-dd"
			}, {
				field : 'preparer',
				header : '填表人',
				headerAlign : 'center',
				visible : true,
				width : 100
			},
			 {field:'',header:'上传设备信息',visible: false,
				  		   formEditorConfig:{
                                fieldVisible:false},
			               renderer:function(e){
				                   var id = e.record.id;
			                       return "<a href='javascript:void(0);' onclick='showanduploadfile(\"" + id + "\",\"one\")'>查看/上传资料 </a>";
			               }}
				]
	});
	});
});

</script>

<div id="id_table_cancled"style="width:100%;height:100%;"></div>