<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<%@include file="/base/mini.jsp"%>
<title><spring:message code="userHome" text="用户主页"></spring:message> </title>
<script>
	jQuery(function() {
		seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
			var userEnabled =isEnglish() ?[ {
				id : '1',
				text : 'onJob'
			}, {
				id : '0',
				text : 'dimission'
			}, {
				id : '-1',
				text : 'unknown'
			} ] :  [ {
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

			new ApTable({
				id : 'table_id1',
				width : globalClientWidth,//表格高度，整型数值
				height : globalClientHeight,//表格宽度，整型数值
				title : '<spring:message code="dataGridTest" text="多行表格测试信息"/>',//表格标题，生成在表格最上边
				iconCls : 'icon-node',//title的图标
				multiSelect : true,//是否可以多选，true多选，false为单选,
				frozenStartColumn:0,
				frozenEndColumn:2,

				hiddenQueryArea : true,//是否将查询区域折叠起来
				frozenStartColumn : 1,//固定列起始位置
				frozenEndColumn : 3,//固定列结束位置

				editFormPopupWindowWidth : 700,//修改时的window高度
				queryButtonColSpan : 2,//查询区的按钮占的列数
				//queryButtonNewLine:true,//查询区的按钮是不是换行显示
				
				//virtualScroll : true,
				//allowCellEdit : true,
				//allowCellSelect : true,
				//allowRowSelect : true,
				//editNextOnEnterKey:true,
				//toolbarEl:'testId',//可以指定按钮生成的位置<div><span><body>等分区标签
				
				allowRowEdit : false,
                isRowEditing : false,//行编辑
                onrowdblclick : function(e){
                	var miniTable = e.sender;
                	var rowData   = e.record;
                	miniTable.commitEdit();
                	miniTable.beginEditRow(rowData);
                },
                
				remoteOper : true,//是否每次操作都提交后台，后台提交开关
				entityClassName : 'com.tenwa.business.entity.User',//本table对应的实体类，存储数据时按该类型存储
				entityBeanCallBackClassName : 'com.tenwa.business.callback.UserBeanCallBack',//实体类回调，用于单表操作外的其他操作

				pageSize : 15,//每页显示几条数据
				showPager : true,//分页按钮是否显示
				lazyLoad : false,//lazyLoad=true时需要调用miniTable.reload()或者miniTable.load()方法才能显示数据，只对xml数据有效
				
				xmlFileName : 'test/table.xml',//数据xml来源
				params : {
					//email : '163.com'//传给XML数据来源的参数
				},
				//data:[
				//{id:'1',fatherid:'fatherid1',motherid:'motherid1',email:'email1',telephone:'telephone1',realname:'realname1',createdate:'createdate1'},
				//{id:'2',fatherid:'fatherid2',motherid:'motherid2',email:'email2',telephone:'telephone2',realname:'realname2',createdate:'createdate2'},
				//{id:'3',fatherid:'fatherid3',motherid:'motherid3',email:'email3',telephone:'telephone3',realname:'realname3',createdate:'createdate3'}
				//],//table数据对象，优先于XML数据

				tools : [ 'add', '-', 'edit', '-', 'remove', '-', 'copy', '-', 'exportExcel', '-', 'globalQuery', '-', {
					html : '<spring:message code="getData" text="获取数据"/>',//自定义按钮的名字
					plain : true,//按钮是否有立体感
					iconCls : 'icon-save',//按钮的图标
					handler : function(miniTable, buttonText) {//按钮响应函数
						var selectedRowData = miniTable.getSelected();//单选数据，不推荐使用使用，可能返回null
						var selectedRowDatas = miniTable.getSelecteds();//多选的数据，推荐使用，无选择时返回空数组对象[]
						alert("您选取的数据是:" + mini.encode(selectedRowData));//被选择的列的数据，数组对象
						alert("您选取的数据是:" + mini.encode(selectedRowDatas));//被选择的列的数据，数组对象
						alert(buttonText + ":" + mini.encode(miniTable.getData()));//表格的全部数据，数组对象
					}
				}, '-', {
					html : '<spring:message code="dataGridTest" text="多行表格测试信息"/>',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {
						alert(buttonText + ":" + mini.encode(miniTable.getColumns()));
					}
				}, '-', {
					html : '<spring:message code="refreshData"  text="刷新数据"/>',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {
						miniTable.reload();
					}
				}, '-', {
					html : '<spring:message code="loadData"  text="加载数据"/>',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {
						miniTable.load();
					}
				}, '-', {
					html : '<spring:message code="confirmSubmit"  text="确认提交"/>',
					plain : true,
					iconCls : 'icon-save',
					handler : function(miniTable, buttonText) {
						mini.confirm("确认删除选中数据行么？", "确认操作", function(buttonText) {
							alert(buttonText);
							if ("ok" == buttonText) {
								ajaxRequest({
									url : window.globalWebRoot + '/acl/test.acl',
									method : 'post',
									params : {

									},
									failure : function(res) {

									},
									success : function(res) {

									}
								});
							}
						});
					}
				} ],

				exportComplexHeaders : [ [ {
					header : '&nbsp;',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 2
				}, {
					header : '<spring:message code="detailInfo"  text="详细资料"/>',
					headerAlign : 'center',
					colspan : 8,
					rowspan : 1
				} ], [ {
					header : '&nbsp;',
					headerAlign : 'center',
					colspan : 2,
					rowspan : 1,
					startColNum : 2
				}, {
					header : '<spring:message code="contact"  text="联系方式"/>',
					headerAlign : 'center',
					colspan : 5,
					rowspan : 1
				}, {
					header : '&nbsp;',
					headerAlign : 'center',
					colspan : 1,
					rowspan : 1
				} ] ],

				columns : [ { /*数字列*/
					type : 'indexcolumn'
				}, { /*选择列*/
					type : 'checkcolumn'
				}, {
					field : 'id',//table数据中显示的js对象属性
					header : '<spring:message code="recodeIndex"  text="记录编号"/>',//table列标题
					headerAlign : 'center',//内容居中
					width : 100,//table列宽
					allowSort : true,//是否允许排序
					visible : false,//《1修改时隐藏该列》是否在新增修改时显示该列
					formEditorConfig : {
						readOnly : true,//该列在新增修改时只读
						fieldVisible : false //《2修改时隐藏该列》，优先于上边的《1修改时隐藏该列》
					}
				}, {
					field : 'province',//table数据中显示的js对象属性
					header : '<spring:message code="province"  text="所在身份 "/>',//table列标题
					headerAlign : 'center',//内容居中
					width : 100,//table列宽
					allowSort : true,//是否允许排序
					visible : false,//《1修改时隐藏该列》是否在新增修改时显示该列
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
						allowInput:true,
						
						params : {
							xmlFileName : 'test/district_province.xml',//对应的数据xml
							pid : '000000',//参数传递
							pageSize:20
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
					headerAlign : 'center',//内容居中
					width : 100,//table列宽
					allowSort : true,//是否允许排序
					visible : false,//《1修改时隐藏该列》是否在新增修改时显示该列
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
					headerAlign : 'center',//内容居中
					width : 100,//table列宽
					allowSort : true,//是否允许排序
					visible : false,//《1修改时隐藏该列》是否在新增修改时显示该列
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
					header : '<spring:message code="realname"  text="真实姓名"/>',
					headerAlign : 'center',
					width : 100,
					allowSort : true,
					visible : true,//是否在表格中显示该列数据
					editor:{ 
						width:'100%', 
						type:'combobox', 
						onenter:function(){
							mini.get("table_id1").commitEdit();//提交编辑
                    	},
                    	valueField : 'realname',//table显示值
						textField : 'realname',//combox显示值
						url:'${pageContext.request.contextPath}/table/getTableData.action?xmlFileName=test/table.xml',
						dataField:'datas'
				    },
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
					header : '<spring:message code="detailInfo"  text="详细资料"/>',
					headerAlign : 'center',
					columns : [ {
						header : '<spring:message code="mother"  text="母亲"/>',//标题
						field : 'motherid',//对应数据的key
						visible : true,//是否显示该列
						headerAlign : 'center',//标题居中    左对齐left,右对齐right,居中对齐center
						visible : true,//是否显示该列
						width : 100,//列宽度
						allowSort : true,//是否允许排序
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
						},
						editor : { 
							width:'100%', 
							type:'combobox', 
							onenter:function(){
								mini.get("table_id1").commitEdit();
	                    	},
	                    	valueField : 'id',//table显示值
							textField : 'realname',//combox显示值
							url:'${pageContext.request.contextPath}/table/getTableData.action?xmlFileName=test/table.xml',
							dataField:'datas'
					    }
					}, {
						header : '<spring:message code="father"  text="父亲"/>',//标题
						field : 'fatherid',//对应数据的key
						visible : true,//是否显示该列
						headerAlign : 'center',
						width : 100,
						allowSort : false,
						visible : true,
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
						},
						editor : {
							width:'100%', 
							type:'combobox', 
							onenter:function(){
								mini.get("table_id1").commitEdit();
	                    	},
	                    	valueField : 'id',//table显示值
							textField : 'username',//combox显示值
							url:'${pageContext.request.contextPath}/table/getTableData.action?xmlFileName=test/table.xml',
							dataField:'datas'
					    }
					}, {
						header : '<spring:message code="contact"  text="联系方式"/>',
						headerAlign : 'center',
						columns : [ {
							field : 'email',
							header : '<spring:message code="mailaddr"  text="邮件地址"/>',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
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
								//vtype : 'email',
								width : 200
							},
							editor:{ 
								width:'100%', 
								type:'textarea', 
								onenter:function(){
									mini.get("table_id1").commitEdit();
		                    	}
						    }
						}, {
							field : 'telephone',
							header : '<spring:message code="phonecode"  text="电话号码"/>',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							formEditorConfig : {
								type : 'text',//表单域类型
								fillFromFieldName : 'fatherid',//有哪一个combox决定该表单域的值
								fillProperty : 'id',//该表单域的值由combox查询的table.xml中的哪一列值决定
								fieldVisible : true,//修改时是否显示该字段
								equired : true,//是否必填
								width : 200,
								labelWidth : 100
							},
							editor:{ 
								width:'100%', 
								type:'textbox',
								onenter:function(){
									mini.get("table_id1").commitEdit();
		                    	}
						    }
						}, {
							field : 'enabled',
							header : '<spring:message code="isJob"  text="是否在职"/>',
							visible : true,
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							renderer : getUserEnabled,//表格数据填充器
							formEditorConfig : {
								newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
								labelWidth : 100,
								width : 200,
								separator : {
									html : '<div class="aptable-editform-separator-info">联系方式：</div>',
									colspan : 4
								},
								//colspan : 3,
								//defaultValue : '1',
								type : 'checkboxlist',//表单域类型
								data : userEnabled
							},
							queryConfig : {
								width : 200,
								colspan : 1,
								type : 'combobox',//表单域类型
								allowInput : false,
								showNullItem : true,
								//defaultValue:'1',
								data : userEnabled
							},
							editor:{ 
								width:'100%', 
								type:'combobox', 
								onenter:function(){
									mini.get("table_id1").commitEdit();
		                    	},
		                    	valueField : 'id',//table显示值
								textField : 'text',//combox显示值
								data: userEnabled
						    }
						}, {
							field : 'username',
							header : '<spring:message code="username"  text="用户名"/>',
							visible : true,
							headerAlign : 'center',
							width : 100,
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
							},
							editor:{ 
								width:'100%', 
								type:'textbox', 
								onenter:function(){
									mini.get("table_id1").commitEdit();
		                    	}
						    }
						}, {
							field : 'createdate',
							header : '<spring:message code="createdate"  text="创建时间"/>',
							headerAlign : 'center',
							width : 100,
							allowSort : true,
							dateFormat : "yyyy-MM-dd HH:mm:ss",
							formEditorConfig : {
								newLine : true,//新起一行来画该表单域，用于修改页面的表单域换行
								type : 'date',
								required : true,//是否必填
								width : 200,
								labelWidth : 100,
								defaultValue : mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
								format : 'yyyy-MM-dd HH:mm:ss',//若showTime=false将会出现2104-02-03 00:00:00的情况
								timeFormat : 'HH:mm:ss',//弹出的日历控件上，时间的格式化形式
								showTime : true, //是否显示时间，false时，将会出现2104-02-03 00:00:00的情况，等于时不再加载时间值，只用00占位。出现占位和column的dateFormat配置也有关系
								
						        otherAttributes: 'onvaluechanged="adjustTotalPrice(e)"'
							},
							queryConfig : {
								width : 200,
								newLine : true,
								//required:true,
								type : 'date',
								vtype : 'date',//
								range : true,//是否是范围查询
								format : 'yyyy-MM-dd HH:mm:ss',
								timeFormat : 'HH:mm:ss',
								showTime : true
								//,
								//startDefaultValue:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),
								//endDefaultValue:mini.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
							},
							editor:{ 
								width:'100%', 
								type:'datepicker',
								format : 'yyyy-MM-dd HH:mm:ss',
								timeFormat : 'HH:mm:ss',
								showTime : true,
								onenter:function(){
									mini.get("table_id1").commitEdit();
		                    	}
						    }
						} ]
					}, {
						field : 'money',
						header : '<spring:message code="salary"  text="工资"/>',
						headerAlign : 'center',
						width : 100,
						allowSort : true,
						dataType : "currency",//货币类型数据
						currencyUnit : "￥",//货币符号
						summary : true,//是否合计该列的值
						formEditorConfig : {
							type : 'text',
							vtype : 'money',//目前不起效
							equired : true,//是否必填
							width : 200,
							labelWidth : 100
						},
						editor:{
							width:'100%', 
							type:'spinner',
							maxValue:999999999999,
							onenter:function(){
								mini.get("table_id1").commitEdit();
	                    	}
					    }
					} ]
				} ]
			});
		});
	});
	
	function adjustTotalPrice(e){
		debugger;
		var min=mini.formatDate(e.value, "yyyy-MM-dd HH:mm:ss");
		if(min>'2014-11-22'){
			alert("开始时间不能大于结束时间");
		mini.getbyName("createdate").setValue('');
		}
	}
</script>
</head>
<body></body>
</html>
