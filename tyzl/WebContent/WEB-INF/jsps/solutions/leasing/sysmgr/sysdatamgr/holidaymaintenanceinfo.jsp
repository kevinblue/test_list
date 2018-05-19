<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>节假日维护信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<script type="text/javascript">
jQuery(function(){
	tenwa.createTable({
		id: "holiday_maintenance_info",
		renderTo: "id_table_container_holidayinfo",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.base.HolidayInfo',
		title: "节假日维护信息",
		remoteOper : true,
		showPager: true,
		pageSize: 120,
		multiSelect:true,
		editFormPopupWindowWidth:500,
		tools: ['add','-','edit','-','remove','-',
		        {
				html:'初始化周六周末',
				plain:true,
			       iconCls:'icon-save',
			       handler:function(miniTable,buttonText){
			    	   mini.get("id_table_change_manage").show();
			    	   
			       }
		        }
		       ],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/holidaymaintenance_info.xml',
		validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
			  var holholiday=mini.formatDate(mini.getbyName("holiday").getValue(), "yyyy-MM-dd");
			  var flag=true;
		     if(editFormItemOperFlag=='add'){
		    	 $.ajax({//校验放假日是否有重复的数据
		    	        url: getRootPath()+"/acl/checkHolholiday.acl",
		    	        type: "post",
		    	        cache: false, 
		    	        data :{ 'holiday': holholiday},
		    	        async : false,
		    	        success: function (data) {
		    	           if(data!="0"){
		    	        	   mini.alert("不允许添加重复放假日,请重新添加！");
		    	        	   flag= false;
		    	           }
		    	          
		    	        }
		    	    });
		     }
		     return flag;
		},
	
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'holiday', header: '放假日',dateFormat : "yyyy-MM-dd",queryConfig : {
				type:'date',vtype:'date',format:'yyyy'},
		   		formEditorConfig : {
		   			labelWidth:90,
		   			newLine:true,
		   			type: 'date',
					required: true,
					vtype: 'date',
					allowInput: true,
					defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd"),
					format : 'yyyy-MM-dd'
		   		}},
			{field: 'workday', header: '对应的工作日',dateFormat : "yyyy-MM-dd", queryConfig :{type:'date',vtype:'date',format:'yyyy'},
		   			formEditorConfig:{
		   				labelWidth:90,
			   			newLine:true,
			   			type: 'date',
						required: true,
						vtype: 'date',
						allowInput: true,
						defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd"),
						format : 'yyyy-MM-dd'}}
			
		]
		
	});
});
function changeyear(){
	
	 var year=mini.formatDate(mini.getbyName("year_date").getValue(), "yyyy");
	 if(year==null||year==''){
		  mini.alert("请选择年份！");  

		  return false;
		}
	 $.ajax({//初始化周六周末
	        url: getRootPath()+"/acl/updatequartzJobHoliday.acl",
	        type: "post",
	        cache: false, 
	        data :{ year: year},
	        async : false,
	        success: function (data) {
	      
	          mini.alert(data);
	          mini.get("id_table_change_manage").hide();
	          mini.get("holiday_maintenance_info").reload();
	        }
	    });
	
}
</script>
</head>
<body>
    <div id="id_table_change_manage" class="mini-window" title="初始化周六周末"
		style="width: 400px; height: 100px;" showModal="true"
		allowResize="true" allowDrag="true">
		<table style="width: 100%">
			<tr style="width: 100%;">
			    <td style="width: 20%;">选择年：</td>
				<td style="width: 70%;">
				<input id="year_date" name="year_date" format="yyyy" class="mini-datepicker"  required="true" />
				</td>
			</tr>
			<tr style="width: 100%;">
			    <td colspan="2" style='text-align: center;'> <a class="mini-button" iconCls="icon-save" plain="true" onclick="changeyear">确定</a>
			    &nbsp;&nbsp;
			    <a class="mini-button" iconCls="icon-save" plain="true" onclick="javascript:{mini.get('id_table_change_manage').hide()}">取消</a>
			    </td>
			</tr>
		</table>
	</div>
<div id="id_table_container_holidayinfo" style="height: 100%;"></div>
</body>
</html>