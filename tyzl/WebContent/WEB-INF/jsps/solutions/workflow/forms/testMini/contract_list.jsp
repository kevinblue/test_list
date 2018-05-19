<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金测算</title>
<link href="${pageContext.request.contextPath}/css/dtree/dtree.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/tracywindy.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/button.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/tracywindy/workFlowUtil.css" rel="stylesheet" type="text/css">

<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<c:set var="code" value="married"></c:set>
<c:set var="name" value="已婚"></c:set>
<c:set var="email" value="648020894@qq.com"></c:set>
<c:set var="telephone" value="18701435234"></c:set>

<script type="text/javascript">
var userEnabled = [ {
	id : '1',
	text : '在职'
}, {
	id : '0',
	text : '离职'
}, {
	id : '-1',
	text : '未知'
} ];

var getUserEnabled = function(e) {
	var code = e.record.enabled;
	for (var i = 0; i < userEnabled.length; i++) {
		if (userEnabled[i]['id'] == code) {
			return userEnabled[i]['text'];
		}
	}
	return '';
};

function loadData(comboId, params, start, pageSize){
	params = params || {};
	params.start = start ? start : 0;
	params.pageSize = pageSize ? pageSize : 9999;
	tenwa.ajaxRequest({
		method:"post",
		url:tenwa.getRootPath() + "/table/getTableData.action",
		async:false,
		params:params,
		success:function(data){
			mini.get(comboId).setData(JsonUtil.decode(data.responseText).datas);
			mini.get(comboId).isLoadData = true;
		},failture:function(data){
			
		}
	});
}

jQuery(function(){
	mini.parse(document.getElementById("mini_test_form"));
	jQuery('#comboxTest1').find('span.mini-buttonedit-button').on('click', function(){
		if(!mini.get("comboxTest1").isLoadData){
			loadData("comboxTest1", {
				pid:"marital_status",
				xmlFileName:"/combos/comboDict.xml"
			});
		}
	});
	
	
	
	mini.get("comboxTest22").setData([{id:"${code}",name:"${name}",code:"${code}",pid:"${pid}"}]);
	jQuery('#comboxTest22').find('span.mini-buttonedit-button').on('click', function(){
		if(!mini.get("comboxTest22").isLoadData){
			loadData("comboxTest22", {
				pid:"000000",
				xmlFileName:"/test/district_province.xml"
			});
		}
	});
	mini.get("comboxTest22").on("valuechanged", function(e){
		var pid = mini.get("comboxTest22").getValue();
		loadData("comboxTest33", {
			pid:pid,
			xmlFileName:"/test/district_city.xml"
		});
		mini.get("comboxTest44").setData([]);
	});
	
	
	
	
	mini.get("comboxTest33").setData([{id:"${code}",name:"${name}",code:"${code}",pid:"${pid}"}]);
	jQuery('#comboxTest33').find('span.mini-buttonedit-button').on('click', function(){
		var pid = mini.get("comboxTest22").getValue();
		if(!mini.get("comboxTest33").isLoadData){
			loadData("comboxTest33", {
				pid:pid,
				xmlFileName:"/test/district_city.xml"
			});
		}
	});
	mini.get("comboxTest33").on("valuechanged", function(e){
		var pid = mini.get("comboxTest33").getValue();
		loadData("comboxTest44", {
			pid:pid,
			xmlFileName:"/test/district_county.xml"
		});
	});
	
	
	
	
	mini.get("comboxTest44").setData([{id:"${code}",name:"${name}",code:"${code}",pid:"${pid}"}]);
	jQuery('#comboxTest44').find('span.mini-buttonedit-button').on('click', function(){
		var pid = mini.get("comboxTest44").getValue();
		if(!mini.get("comboxTest44").isLoadData){
			loadData("comboxTest44", {
				pid:pid,
				xmlFileName:"/test/district_county.xml"
			});
		}
	});
	
	
	
	
	
	mini.get("comboxTest4").setData([{id:"${id}", realname:"${realname}", email:"${email}", telephone:"${phone}"}]);
	mini.get("comboxTest4").isLoadData = false;
	mini.get("comboxTest4").on("valuechanged", function(e){
		var selecteds = this.getSelecteds();
        var selected = selecteds[0];//单选只有一个
        mini.get('textboxTest5').setValue(selected.email);
        mini.get('textboxTest6').setValue(selected.telephone);
	});
	jQuery('#comboxTest4').find('span.mini-buttonedit-button').on('click', function(){
		if(mini.get("comboxTest4").isLoadData == false){
			loadData("comboxTest4", {
				enabled:"1",
				xmlFileName:"/test/table.xml"
			});
		}
	});
	
	loadTabInfos("table1");
});

var tabIsLoad = {};

function loadTabInfos(id){
	tenwa.createTable({
		id : id,
		renderTo : "id_table_render_" + id,
		width : globalClientWidth - 30,//表格高度，整型数值
		height : globalClientHeight,//表格宽度，整型数值
		title : '多行表格测试信息',//表格标题，生成在表格最上边

		editFormPopupWindowWidth : 700,//修改时的window高度
		queryButtonColSpan : 2,//查询区的按钮占的列数
		
		remoteOper : true,//是否每次操作都提交后台，后台提交开关
		entityClassName : 'com.tenwa.business.entity.User',//本table对应的实体类，存储数据时按该类型存储
		entityBeanCallBackClassName : 'com.tenwa.business.callback.UserBeanCallBack',//实体类回调，用于单表操作外的其他操作

		showPager : true,//分页按钮是否显示
		lazyLoad : false,//lazyLoad=true时需要调用miniTable.reload()或者miniTable.load()方法才能显示数据，只对xml数据有效

		xmlFileName : 'test/table.xml',//数据xml来源
		params : {
			//email : '163.com'//传给XML数据来源的参数
		},

		tools : [ 'add', '-', 'edit', '-', 'remove', '-', 'copy', '-', 'exportExcel', '-', {
			html : '启动流程',//自定义按钮的名字
			plain : true,//按钮是否有立体感
			iconCls : 'icon-save',//按钮的图标
			handler : function(miniTable, buttonText) {//按钮响应函数
				//var selectedRowData = miniTable.getSelected();//单选数据，不推荐使用使用，可能返回null
				var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
				if(selectedRowDatas.length > 0){
					tenwa.startProcess('MINIUI测试流程-1',{});
				}
				//alert("您选取的数据是:" + mini.encode(selectedRowData));//被选择的列的数据，数组对象
				//alert("您选取的数据是:" + mini.encode(selectedRowDatas));//被选择的列的数据，数组对象
				//alert(buttonText + ":" + mini.encode(miniTable.getData()));//表格的全部数据，数组对象
			}
		}, '-', 'globalQuery' ],

		columns : [ { /*数字列*/
			type : 'indexcolumn'
		}, { /*选择列*/
			type : 'checkcolumn'
		}, {
			field : 'id',//table数据中显示的js对象属性
			header : '记录编号',//table列标题
			visible : false,//《1修改时隐藏该列》是否在新增修改时显示该列
			formEditorConfig : {
				readOnly : true,//该列在新增修改时只读
				fieldVisible : false //《2修改时隐藏该列》，优先于上边的《1修改时隐藏该列》
			}
		}, {
			field : 'province',//table数据中显示的js对象属性
			header : '所在省份',//table列标题
			visible : false,
			formEditorConfig : {
				type : 'combobox',//修改时显示的表单域类型
				required : true,//是否必填
				multiSelect : false,//combo是否可以多选
				readOnly : false,//新增修改时是否只读
				valueField : 'id',//table显示值
				textField : 'name',//combox显示值
				labelWidth : 100,//该域标签宽度
				width : 200,//该域总宽度
				newLine : true,//新起一行来画该表单域，用于修改时页面的表单域换行
				separator : {
					html : '<div class="aptable-editform-separator-info">基本信息：</div>',//form上下分割内容
					colspan : 4 //新增或者修改时，1个表单域占两列，每行显示2个的话就占4列
				},
				params : {
					xmlFileName : 'test/district_province.xml',//对应的数据xml
					pid : '000000'//参数传递
				},
				fieldVisible : true,//修改时是否显示此表单域
				cascade : {
					childrenFieldNames : [ 'city', 'county' ],//该表单域关联的子表单域
					clearFieldNames : [ 'city', 'county' ] //该表单域修改时需要清空的子表单域
				}
			}
		}, {
			field : 'city',//table数据中显示的js对象属性
			header : '所在市区',//table列标题
			visible : false,
			formEditorConfig : {
				type : 'combobox',//表单域类型
				required : true,//是否必填
				multiSelect : false,//combo是否可以多选
				readOnly : false,//新增修改时是否只读
				valueField : 'id',//table显示值
				textField : 'name',//combox显示值
				labelWidth : 100,//该域标签宽度
				width : 200,//该域总宽度
				fillFromFieldName : 'province',//有哪一个combox决定该表单域的值
				fillProperty : 'id',//该表单域的值由combox查询的table.xml中的哪一列值决定
				params : {
					xmlFileName : 'test/district_city.xml'
				},
				fieldVisible : true,
				cascade : {
					parentFieldNames : [ 'province' ],//该表单域关联的父级表单域
					childrenFieldNames : [ 'county' ],
					clearFieldNames : [ 'county' ]
				}
			}
		}, {
			field : 'county',//table数据中显示的js对象属性
			header : '所在县城',//table列标题
			visible : false,
			formEditorConfig : {
				newLine : true,//新起一行来画该表单域，用于修改时页面的表单域换行
				type : 'combobox',//表单域类型
				required : true,//是否必填
				multiSelect : false,//combo是否可以多选
				readOnly : false,//新增修改时是否只读
				valueField : 'id',//table显示值
				textField : 'name',//combox显示值
				labelWidth : 100,//该域标签宽度
				width : 200,//该域总宽度
				fillFromFieldName : 'city',//有哪一个combox决定该表单域的值
				fillProperty : 'id',//该表单域的值由combox查询的table.xml中的哪一列值决定
				params : {
					xmlFileName : 'test/district_county.xml'
				},
				fieldVisible : true,
				cascade : {
					parentFieldNames : [ 'province', 'city' ]
				}
			}
		}, {
			field : 'realname',
			header : '真实姓名',
			formEditorConfig : {
				type : 'combobox',//表单域类型
				required : true,//是否必填
				multiSelect : false,//combo是否可以多选
				valueField : 'realname',//table显示值
				textField : 'realname',//combox显示值
				labelWidth : 100,//该域标签宽度
				width : 200,//该域总宽度
				params : {
					xmlFileName : 'test/table.xml'
				}
			}
		}, {
			header : '母亲',//标题
			field : 'motherid',//对应数据的key
			renderer : function(e){
				var motherName = e.record.mothername;
				var motherId = e.record.motherid;
				return "<a href='javascript:void(0);' onclick='alert(\"" + motherId + "\")'>" + motherName + "</a>";
			},
			formEditorConfig : {//修改或者新增时该列在表单中的配置信息
				newLine : true,//新起一行来画该表单域，用于修改时页面的表单域换行
				type : 'combobox',//类型
				required : true,//是否必填
				multiSelect : true,//多选select
				valueField : 'id',//combox隐藏值
				textField : 'realname',//combox显示值
				width : 200,
				labelWidth : 100,
				fieldVisible : true,
				params : {
					xmlFileName : 'test/table.xml'
				}
			}
		}, {
			header : '父亲',//标题
			field : 'fatherid',//对应数据的key
			renderer : function(e){
				var fatherName = e.record.fathername;
				var fatherId = e.record.fatherid;
				return "<a href='javascript:void(0);' onclick='alert(\"" + fatherId + "\")'>" + fatherName + "</a>";
			},
			formEditorConfig : {
				type : 'combobox',//表单域类型
				equired : true,//是否必填
				multiSelect : false,//单选select
				valueField : 'id',//combox隐藏值
				textField : 'username',//combox显示值
				width : 200,
				labelWidth : 100,
				fieldVisible : true,
				params : {
					xmlFileName : 'test/table.xml'
				}
			}
		}, {
			field : 'email',
			header : '邮件地址',
			vtype : 'email',//验证值的类型
			formEditorConfig : {
				newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
				type : 'text',//表单域类型
				fillFromFieldName : 'motherid',//有哪一个combox决定该表单域的值
				fillProperty : 'id',//该表单域的值由combox查询的table.xml中的哪一列值决定
				fieldVisible : true,//修改时是否显示该字段
				required : true,//是否必填
				width : 200,
				labelWidth : 100
			},
			queryConfig : {
				width : 200
			}
		}, {
			field : 'telephone',
			header : '电话号码',
			formEditorConfig : {
				type : 'text',//表单域类型
				fillFromFieldName : 'fatherid',//有哪一个combox决定该表单域的值
				fillProperty : 'id',//该表单域的值由combox查询的table.xml中的哪一列值决定
				fieldVisible : true,//修改时是否显示该字段
				equired : true,//是否必填
				width : 200,
				labelWidth : 100
			}
		}, {
			field : 'enabled',
			header : '是否在职',
			renderer : getUserEnabled,//表格数据填充器
			formEditorConfig : {
				newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
				labelWidth : 100,
				width : 200,
				separator : {
					html : '<div class="aptable-editform-separator-info">联系方式：</div>',
					colspan : 4
				},
				type : 'combobox',//表单域类型
				data : userEnabled
			},
			queryConfig : {
				width : 200,
				colspan : 1,
				type : 'combobox',//表单域类型
				allowInput : false,
				showNullItem : true,
				data : userEnabled
			}
		}, {
			field : 'username',
			header : '用户名',
			visible : true,
			headerAlign : 'center',
			allowSort : true,
			formEditorConfig : {
				type : 'combobox',//表单域类型
				equired : true,//是否必填
				readOnly : true,//表单域只读
				multiSelect : false,//单选select
				valueField : 'username',//combox隐藏值
				textField : 'username',//combox显示值
				separator : {
					html : '<div class="aptable-editform-separator-info">用户状态：</div>',
					colspan : 4
				},
				width : 200,
				labelWidth : 100,
				params : {
					xmlFileName : 'test/table.xml'
				}
			},
			queryConfig : {
				width : 200,
				colspan : 1,
				type : 'combobox',//表单域类型
				valueField : 'username',
				textField : 'realname',
				params : {
					xmlFileName : 'test/table.xml'
				},
				allowInput : true,//是否允许手动输入
				showNullItem : true//是否显示空行
			}
		}, {
			field : 'createdate',
			header : '创建时间',
			dateFormat : "yyyy-MM-dd HH:mm:ss",
			formEditorConfig : {
				newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
				type : 'date',
				equired : true,//是否必填
				width : 200,
				labelWidth : 100,
				defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
				format : 'yyyy-MM-dd HH:mm:ss',//若showTime=false将会出现2104-02-03 00:00:00的情况
				timeFormat : 'HH:mm:ss',//弹出的日历控件上，时间的格式化形式
				showTime : true //是否显示时间，false时，将会出现2104-02-03 00:00:00的情况，等于时不再加载时间值，只用00占位。出现占位和column的dateFormat配置也有关系
			},
			queryConfig : {
				width : 200,
				newLine : true,
				type : 'date',
				vtype : 'date',//
				range : true,//是否是范围查询
				format : 'yyyy-MM-dd HH:mm:ss',
				timeFormat : 'HH:mm:ss',
				showTime : true
			}
		}, {
			field : 'money',
			header : '工资',
			dataType : "currency",//货币类型数据
			currencyUnit : "￥",//货币符号
			summary : true,//是否合计该列的值
			formEditorConfig : {
				type : 'text',
				vtype : 'money',//目前不起效
				equired : true,//是否必填
				width : 200,
				labelWidth : 100
			}
		} ]
	});
}

</script>
</head>
<body>
	<div id="mini_test_form">
		<div class="mini-panel" title="合同信息" showCollapseButton="true" style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0">
				<tr class="tr-odd">
					<td class="td-content-title">项目名称:</td>
					<td class="td-content"><input id="tb" class="mini-textbox" style="width:165px;" emptyText="请输入项目名称"/></td>
					<td class="td-content-title">出单时间:</td>
					<td class="td-content"><div id="calendar1" class="mini-datepicker" style="width:165px;" value="2011-12-11" ></div></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">项目类型:</td>
					<td class="td-content"><input id="comboxTest1" class="mini-combobox" style="width:165px;" textField="name" valueField="code" value="${code}" showNullItem="true" allowInput="true"/></td>
					<td class="td-content-title">所在省份:</td>
					<td class="td-content"><input id="comboxTest22" class="mini-combobox" style="width:165px;" textField="name" valueField="id" value="${id}" showNullItem="true" allowInput="false"/></td>
				</tr>
				<tr class="tr-odd">
					<td class="td-content-title">所在市:</td>
					<td class="td-content"><input id="comboxTest33" class="mini-combobox" style="width:165px;" textField="name" valueField="id" value="${id}" showNullItem="true" allowInput="false"/></td>
					<td class="td-content-title">所在县:</td>
					<td class="td-content"><input id="comboxTest44" class="mini-combobox" style="width:165px;" textField="name" valueField="id" value="${id}" showNullItem="true" allowInput="false"/></td>
				</tr>
			</table>
		</div>
		<div class="mini-panel" title="承租人信息" showCollapseButton="true" style="width: 100%;">
			<table class="fillTable" cellspacing="0" cellpadding="0">
				<tr class="tr-odd">
					<td class="td-content-title">承租人:</td>
					<td class="td-content">
						<div id="comboxTest4" class="mini-combobox" style="width:165px;" popupWidth="200" textField="realname" valueField="id" value="${id }" multiSelect="false">     
						    <div property="columns">
						        <div header="值" field="id"></div>
						        <div header="名称" field="realname"></div>
						        <!-- 
						        <div header="邮箱" field="email"></div>
						        <div header="电话" field="telephone"></div>
						         -->
						    </div>
						</div>
					</td>
					<td class="td-content-title">设备数量：</td>
					<td class="td-content"><input id="sp1" class="mini-spinner" style="width:165px;" minValue="200" maxValue="250" increment="5"/></td>
				</tr>
				<tr class="tr-even">
					<td class="td-content-title">联系电话:</td>
					<td class="td-content"><input id="textboxTest5" class="mini-textbox" style="width:165px;" emptyText="请输入联系电话" value="${telephone }"/></td>
					<td class="td-content-title">联系地址:</td>
					<td class="td-content"><input id="textboxTest6" class="mini-textbox" style="width:165px;" emptyText="请输入联系地址" value="${email }"/></td>
				</tr>
			</table>
		</div>
		<div class="mini-panel" title="租赁物件" showCollapseButton="true" style="width: 100%;">
	        <div id="id_table_render_table1"></div>
		</div>
	</div>
</body>
</html>