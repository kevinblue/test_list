<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>节假日维护信息</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
var holiday_number=null;
jQuery(function(){
	$.ajax({//自动生成节假日编号
	    url: getRootPath()+"/acl/createHolidayNumber.acl",
	    type: "post",
	    cache: false, 
	    async : false,
	    success: function (data) {
	    	holiday_number=data;
	    }
	});
}); 
var whether=[{id:'是',text:'是'},{id:'否',text:'否'}];
jQuery(function(){
	tenwa.createTable({
		id: "holiday_calssify",
		renderTo: "id_table_holiday_calssify",
		width: '100%',
		height: '100%',
		lazyLoad: false,
		entityClassName : 'com.tenwa.leasing.entity.base.HolidayClassify',
		title: "节假日信息维护",
		remoteOper : true,
		showPager: true,
		pageSize: 20,
		multiSelect:false,
		tools: ['add','-','edit','-','remove'],
		xmlFileName: '/eleasing/jsp/sysmgr/sysdatamgr/holidayclassify.xml',
		beforeShowWindowCallBack:function(miniTable,miniForm, operType){
			if("add"==operType){
				if(Number(holiday_number)<10){
					var holidaynum=parseInt(holiday_number)+parseInt("1");
					mini.getbyName("holidaynumber").setValue("CAL00"+holidaynum);
				}else{
					var holidaynum=parseInt(holiday_number)+parseInt("1");
					mini.getbyName("holidaynumber").setValue("CAL0"+holidaynum);
				}
			}
			return true;
		},
		submitReturnCallBack:function(miniTable, editFormItemOperFlag, operText, resultJson){
			mini.unmask(document.body);
			window.location.reload();
		},
		validateForm:function(miniTable, miniForm, editFormItemOperFlag, addIndex){
			var state=mini.getbyName("state").getValue();
			var id=getMiniEditFormField("holiday_calssify.id").getValue();
			var flag=0;
			if(editFormItemOperFlag=='add'||editFormItemOperFlag=='edit'){
				if(state=='是'){
					var stats=miniTable.getData()
					for(var i = 0;i<stats.length;i++){
						var stat = stats[i];
						if(state==stat.state&&id!=stat.id){
							flag+=1;
						}					
					}					
				}
			}
			if(flag>0){
				mini.alert("只能有一个首选节日方案！");
				return false;
			}else{
				return true;
			}
		},
		columns: [
			{type: 'indexcolumn'},
			{type: 'checkcolumn'},
			{field: 'id', header: 'id', visible: false},
			{field: 'holidaynumber', header: '方案编号',align:'center',
				formEditorConfig:{
					newLine:true,
					required:true,
					readonly:true
				}
			},
			{field: 'holidaytype', header: '节假日方案',align:'center',
				formEditorConfig:{
					newLine:true,
					required:true
				}
			},
			{field: 'holidayyear', header: '年份',align:'center',
				formEditorConfig:{
					newLine:true,
					required:true,
					vtype : 'int'
				}
			},
			{field: 'state', header: '是否首选',align:'center',
				formEditorConfig:{
					newLine:true,
					required:true,
					type: 'combobox',
					data: whether,
					value : '否'
				}
			},
			{field: 'coun', header: '节假日数量',align:'center',
				formEditorConfig:{
					fieldVisible:false
				}
			},
			{field : '查看详情',header : '查看详情',align:'center',
				formEditorConfig:{
					fieldVisible:false
				},
				renderer:function(e){
					 id = e.record.id;
					 type=e.record.holidaytype;
					 year=e.record.holidayyear;
					return "<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\"" + id +"\",\""+type+ "\",\""+year+ "\")'> 查看明细 </a>";
				}
			}
		]
		
	});
});
function opencustdetail(id,type,year){
    var url=getRootPath()+"/leasing/sysmgr/sysdatamgr/holidayinfo.bi?id="+id+"&type="+escape(encodeURIComponent(type))+"&year="+year;
    var sheight = window.screen.availHeight - 30;
    var swidth = window.screen.availWidth - 10;
    var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
    window.open(url, '_blank', winoption); 
}
</script>
</head>
<body>
<div id="id_table_holiday_calssify" style="height: 100%;"></div>
</body>
</html>