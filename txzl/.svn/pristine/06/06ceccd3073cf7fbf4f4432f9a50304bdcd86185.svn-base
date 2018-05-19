<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>信审权限维护页面列表</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">

	var pid='${param.pid}';
	
var isMulti = false;
jQuery(function() {
	seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id:'table_id8',
			width:globalClientWidth,
			height:globalClientHeight,
			iconCls:'icon-node',
			hiddenQueryArea:false,
			multiSelect:true,
			editFormPopupWindowWidth:700,
			title:'信审权限维护页面列表' ,
			remoteOper:true,
			pageSize:50,
			showPager:true,
			lazyLoad:false,
			xmlFileName:'/eleasing/jsp/fund/fund_overdue/xinshen_maintenance_list.xml',
			queryButtonColSpan:2,
			beforeShowWindowCallBack:function(miniTable,miniForm, operType){
				isMulti = false;
				return true;
			},
			params:{
				pid:pid
			},
			tools:[
					{
				     html:'批量修改',
				    plain:true,
				  iconCls:'icon-edit', 
				  handler:function(miniTable, buttonText) { 
					var row = miniTable.getSelecteds();
					if(row.length>0){
						var multiform = new mini.Form("multiform");
						var multieditWindow = mini.get("multieditWindow");
						multieditWindow.show();
						multiform.clear();
						isMulti = true;
					}else{
						mini.alert("请至少选中一行！");
					}
				}
			}],
		
			columns:[ 
				{type:'indexcolumn'},
			    {type:'checkcolumn'},
			    {field:'id',header:'中间表ID',headerAlign:'center',width:100,allowSort:true,visible:false,
				        formEditorConfig:{
					            readOnly:true,
				 	        fieldVisible:false}}, 	        
				{field: 'businessdeptname', header: '业务部门', visible: true},	 
				{field: 'businessdeptid', header: '业务部门编号', visible:false},	 
				{field: 'industryid', header: '行业ID', visible:false},	 
				{field: 'industryname', header: '行业', visible: true,queryConfig:{},
							     formEditorConfig:{
								     fieldVisible:false }},
				{field: 'creditdeptid', header: 'creditdeptid', visible: false},
				{field: 'creditdeptname', header: '信审部门', visible: true}
		
				]
		});
	});

});

</script>
</head>
<body>
	<div id="multieditWindow" class="mini-window" title="选择信审部门" style="width:400px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
        <div id="multiform">
            <table>
                <tr>
                    <td style="width:80px;">信审部门:</td>
                    <td >  
                        <input id="id_dunninginfo" name="dunninginfo" class="mini-combobox" allowInput = "false" textField="name" required="true" valueField="id" dataField="datas" allowInput="false"
						  data-options="{_xmlFileName:'/eleasing/jsp/fund/fund_overdue/get_credit_dept.xml'}" 
						   onbeforeshowpopup="miniextonbeforeshowpopup"/>
                    </td>
                    <td >
                        <a class="mini-button " onclick="submitMultiData">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>
                    </td>
                </tr>   
            </table>
        </div>
	</div>
</body>
</html>
<script>

 
	function submitMultiData(e){
		
		var dunninginfo = mini.get("id_dunninginfo").getValue();
		if("" == dunninginfo){mini.alert("请选择信审部门！");return false;}
		var table_id8 = mini.get("table_id8");
		var rows = table_id8.getSelecteds();
	
		var arr=new Array();
		for(var i=0;i<rows.length;i++){
		
			arr.push((rows[i].id=="0"?"null":rows[i].id)+"@"+rows[i].businessdeptid+"@"+rows[i].industryid);
	

		}   
		
	$.ajax({			
	        url: getRootPath()+"/acl/saveMultiXinshenDunningInfo.acl",
	        type: "post",
	        cache: false, 
	        data :{"arr":arr.join(","),"partdept":dunninginfo},
	        async:false,
	        success: function (text) {mini.alert("保存成功！");}
	    });  
		mini.get("multieditWindow").hide();
		mini.get("table_id8").load();
	}
</script>