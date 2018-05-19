<%@ page language="java" import="java.net.URLDecoder" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>节假日维护信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validator.js"></script>
<% String type=URLDecoder.decode(request.getParameter("type"),"UTF-8");%>
<script type="text/javascript">
var type = "<%= type %>";
var id="${param.id}";
var year="${param.year}";

jQuery(function(){
	tenwa.createTable({
		id: "holiday_info",
		renderTo: "id_table_container_holidayinfo",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.base.HolidayInfo',
		title: year+"-"+type+"-维护信息",
		remoteOper : true,
		showPager: true,
		pageSize: 120,
		multiSelect:false,
		editFormPopupWindowWidth:500,
		tools: ['add','-','edit','-','remove','-',
		        {
				html:'初始化周六周日',
				plain:true,
			       iconCls:'icon-save',
			       handler:function(miniTable,buttonText){
			    	   $.ajax({//初始化周六周末
				   	        url: getRootPath()+"/acl/updatequartzJobHoliday.acl",
				   	        type: "post",
				   	        cache: false, 
				   	        data :{year: year,id:id},
				   	        async : false,
				   	        success: function (data) {
				   	            mini.alert(data);
				   	       		miniTable.reload();
				   	        }
				   	    });
			    	   
			       }
		        }
		       ],
		params:{
			holidayclassifyid:id
		},
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/holiday_info.xml',
		validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
			  var holholiday=mini.formatDate(mini.getbyName("holiday").getValue(), "yyyy-MM-dd");
			  var id=getMiniEditFormField("holiday_info.id").getValue();
			  var flag=true;
		      if(editFormItemOperFlag=='add'||editFormItemOperFlag=='edit'){
		    	 $.ajax({//校验放假日是否有重复的数据
		    	        url: getRootPath()+"/acl/checkHolholiday.acl",
		    	        type: "post",
		    	        cache: false, 
		    	        data :{holiday: holholiday,type:editFormItemOperFlag,id:id},
		    	        async : false,
		    	        success: function (data) {
		    	           if(data!="0"){
		    	        	   mini.alert("放假日重复,请重新选择放假！");
		    	        	   flag= false;
		    	           }
		    	        }
		    	    });
		     } 
		     return flag;
		},
		beforeShowWindowCallBack : function (miniTable,miniForm,operFlag){
			if('add' == operFlag){
				getMiniEditFormField("holiday_info.holidayclassifyid").setValue(id);
			}
			return true;
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'holiday', header: '节假日',align:'center',dateFormat : "yyyy-MM-dd",queryConfig : {
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
		   		}
			},
			{field: 'workday', header: '对应的工作日',align:'center',dateFormat : "yyyy-MM-dd", queryConfig :{type:'date',vtype:'date',format:'yyyy'},
		   			formEditorConfig:{
		   				labelWidth:90,
			   			newLine:true,
			   			type: 'date',
						required: true,
						vtype: 'date',
						allowInput: true,
						defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd"),
						format : 'yyyy-MM-dd'
						}
			},
			{field: 'issameday', header: '是否节日当天', width:50,align:'center',
				renderer:function(e){
					var issameday = e.record.issameday;
					var val = '';
					switch(issameday) {
					case "true":{val = "是";break;}
					case "false":{val = "否";break;}
				}
				return val;
				},
				formEditorConfig : {
					type:'combobox',
		   			newLine:true,
		   			data:[{id:'true',text:'是'},{id:'false',text:'否'}]
		   		}
			},
			{field: 'memo', header: '备注',align:'center',
				formEditorConfig : {
		   			newLine:true,
		   		}
			},
			{field: 'holidayclassifyid', header: 'holidayclassifyid',align:'center',visible: false,
				formEditorConfig : {
		   			newLine:true,
		   		}
			}
			
		]
		
	});
});
</script>
</head>
<body>
<div id="id_table_container_holidayinfo" style="height: 100%;"></div>
</body>
</html>