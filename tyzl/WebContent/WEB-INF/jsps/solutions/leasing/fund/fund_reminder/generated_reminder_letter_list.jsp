<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>生成催款函列表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript">
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id1',
			width:globalClientWidth,
			height:globalClientHeight,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			editFormPopupWindowWidth:700,
			queryButtonColSpan:4,
			remoteOper:true,
			multiSelect:true,
			pageSize:15,
			showPager:true,
			lazyLoad:false,
			title:'生成催款函列表',
			tools:[{
				html:'生成催款函', 
				plain:true, 
				iconCls:'icon-addfolder', 
				handler:function(miniTable, buttonText) { 
					var row = miniTable.getSelecteds();
					if(row.length>0){
						var generatedform = new mini.Form("generatedform");
						var generatededitWindow = mini.get("generatededitWindow");
						generatededitWindow.show();
						generatedform.clear();
					}else{
						mini.alert("请至少选中一行！");
					}
				}
			}],
			xmlFileName:'/eleasing/jsp/fund/fund_reminder/generated_reminder_letter_list.xml',
			params:{},
			columns:[
				   {type:'indexcolumn'},
				   {type:'checkcolumn'},
				   {field:'contract_id',header:'合同号',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					               width:200}},
				   {field:'contractnumber',header:'业务合同号',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					          labelWidth:100,
					               width:200}},
				   {field:'rawValue_partdept',header:'催款员',headerAlign:'center',
				             queryConfig:{
					               width:200}},
				   {field:'senddate',header:'最后一次发送日期',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					          labelWidth:120,
					             newLine:true,
					               width:200,
					                type:'date',
					              format:'yyyy-MM-dd',
					          allowInput:"false",
					               range:true}},
					{field:'custname',header:'客户名',headerAlign:'center',width:100,allowSort:true,
				             queryConfig:{
					               width:200,
					             colspan:3}},
					{field:'phone',header:'电话',headerAlign:'center',width:100},
				    {field:'address',header:'地址',headerAlign:'center',width:100},
				    {field:'guaranteecustname',header:'担保人',headerAlign:'center',width:100,
				             queryConfig:{
					             newLine:true,
					               width:200}},
					{field:'startdate',header:'起租日',headerAlign:'center',width:100,allowSort:true},
				    {field:'overduenum',header:'逾期期数',headerAlign:'center',width:100,allowSort:true},
				    {field:'overduerent',header:'逾期租金',headerAlign:'center',width:100,allowSort:true},
				    {field:'penalty',header:'逾期罚息',headerAlign:'center',width:100,allowSort:true},
				    {field:'',header:'合计欠款',headerAlign:'center',width:100,allowSort:true,
				               renderer:function(e){
									var incrent = e.record.overduerent;
									var penalty = e.record.penalty;
									return parseFloat(incrent) + parseFloat(penalty);}}]
		});
	});
});
</script>
</head>
<body>
	<div id="generatededitWindow" class="mini-window" title="维护发送日期" style="width:400px;height:100px;"   showModal="true" allowResize="true" allowDrag="true">
        <div id="generatedform">
            <table class="miniext-form-table">
                <tr>
                    <td style="width:100px;">发送日期：</td>
                    <td >
                        <input id="senddate" name="senddate" class="mini-datepicker miniext-form-fit" allowInput="false"/>
                    </td>
                    <td >
                        <a class="mini-button " onclick="submitgeneratedData">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
</html>
<script type="text/javascript">
	function submitgeneratedData(e){
		var senddate = mini.get("senddate").getFormValue();
		if("" == senddate){
			mini.alert("请选择发送日期！");
			return false;
		}
		var table_id1 = mini.get("table_id1");
		var rows = table_id1.getSelecteds();
		for(var i=0;i<rows.length;i++){
			rows[i]["senddate"]=senddate;
			rows[i]["status"]="1";
		}
		var json = mini.encode(rows);
		$.ajax({
	        url: getRootPath()+"/acl/saveGeneratedReminderLetter.acl",
	        type: "post",
	        cache: false, 
	        data:{"datas":json,"senddate":senddate},
	        async:false,
	        success: function (text) {
	        	mini.alert("生成成功！");
	        }
	    });
		mini.get("generatededitWindow").hide();
		mini.get("table_id1").load();
	}
</script>