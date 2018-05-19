<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>风电项目一览</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;
	//关于电价记录的变量
	var benchmarkpriceAbTr = null;
	var internetpriceAbTr = null;
	var idAbTr = null;
	var benchmarkpriceeNewAbTr = null;
	var internetpriceNewAbTr = null;
	jQuery(function() {
		tenwa
				.createTable({
					id : "proj_wind_power_list",
					renderTo : "id_proj_wind_power_list",
					width : currentClientWidth,
					height : currentClientHeight,
					editFormPopupWindowWidth : 800,
					editFormPopupWindowHeight : 500,
					lazyLoad : false,
					title : "风电项目一览",
					remoteOper : true,
					entityClassName : "com.tenwa.leasing.entity.proj.ProjDevelopInfo",
					showPager : true,
					pageSize : 20,
					tools : [ "add", "-", "edit", "-", "remove",
					          '-',
					  		{
					  			html: '查看',
					  			plain: true,
					  			iconCls: 'icon-addfolder',
					  			handler: function(miniTable, buttonText){
					  				var row = miniTable.getSelected();
					  				if(row){
					  					var id = row.id;
					  			    	showDetail(id);
					  					
					  				}else{
					  					mini.alert("请选择要查看的数据！");
					  				}
					  			}
					  		}],
					queryButtonColSpan : 6,
					queryButtonNewLine : false,
					xmlFileName : '/eleasing/workflow/proj/proj_search/proj_develop_list.xml',
					afterShowWindowCallBack : function(miniTable, miniForm,
							operFlag) {
						if (operFlag == "edit") {
							var custname = mini.getbyName("custname").getValue();
							mini.getbyName("custid").setText(custname);
						}
						if (operFlag == "add") {
							dept = "${sessionScope['loginUserDeptObj'].id}";
							deptname = "${sessionScope['loginUserDeptObj'].name}";
							creator = "${sessionScope['loginUser'].id}";
							creatorname = "${sessionScope['loginUser'].realname}";							
							mini.getbyName("creator").setText(creatorname);
							mini.getbyName("registdept").setText(deptname);
						}
					},
					
					validateForm : function(miniTable, miniForm, editFormItemOperFlag, addIndex){
						if (editFormItemOperFlag == "edit") {
							row = miniTable.getSelected();
							benchmarkpriceAbTr = row.benchmarkprice;
							internetpriceAbTr = row.internetprice;
							idAbTr = row.id;
							benchmarkpriceeNewAbTr = mini.getbyName("benchmarkprice").getValue();
							internetpriceNewAbTr = mini.getbyName("internetprice").getValue();
						};
						return true;
				    },
					
				    submitReturnCallBack:function(miniTable, editFormItemOperFlag, operText, resultJson){
				    	if (editFormItemOperFlag == "edit") {
							if(benchmarkpriceAbTr!=benchmarkpriceeNewAbTr||internetpriceAbTr!=internetpriceNewAbTr){
								 $.ajax({
										cache : false,
										async : false,
								        type: "post",
								        dataType: "JSON",
								        url : getRootPath()+ "/acl/CheckTariffRecord.acl",
								        data: {benchmarkprice:benchmarkpriceAbTr,internetprice:internetpriceAbTr,id:idAbTr},
								        success: function (data) {}
								    });
							};
							mini.alert("操作成功");
			        		miniTable.reload();//重新加载表格
					    	mini.unmask(document.body);//隐藏遮罩
						}else{
							mini.alert("操作成功");
			        		miniTable.reload();//重新加载表格
					    	mini.unmask(document.body);//隐藏遮罩
						};
				    },
				    
				    
					params : {
						type : 1
					},
					columns : [
							{
								type : 'indexcolumn'
							},
							{
								type : 'checkcolumn'
							},
							{
								field : 'id',
								header : 'id',
								visible : false,
								formEditorConfig : {
									readOnly : true,
									fieldVisible : false
								}
							},
							/* {
								field : 'oper',
								header : '操作',
								allowSort : false,
								readOnly : true,
								renderer : onActionRenderer,
								formEditorConfig : {
									readOnly : true,
									fieldVisible : false
								}
							}, */
							{
								field : 'custname',
								header : '客户名称',
								visible : true,
								width:130,
								queryConfig : {},
								formEditorConfig : {
									fieldVisible : false
								},
								renderer:function(e){
									var row=e.record;
									return "<a style='text-decoration:underline;color:blue;' href='javascript:showcustinfo(\""+row.custid+'"'+","+'"'+row.custclass+"\")'>"+row.custname+"</a>";
								}
							},
							{
								field : 'custid',
								header : '客户名称',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									required : true,
									textField : 'name',
									valueField : 'value',
									allowInput : false,
									fieldVisible : true,
									onSelect : setRegistcode,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_search/registcode.xml'
									}
								}
							},
							{
								field : 'registcode',
								header : '注册号/统一社会信用代码',
								visible : true,
								width : 150,
								formEditorConfig : {
									allowInput : false,
									required : true,
									readOnly:true,
									fieldVisible : true,
								}
							},
							{
								field : 'registcodeid',
								header : '注册号/统一社会信用代码',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									required : false,
									textField : 'name',
									valueField : 'value',
									allowInput : false,
									readOnly:true,
									newLine : false,
									fieldVisible : false
								}
							},
							{
								field : 'projname',
								header : '项目名称',
								queryConfig : {},
								renderer:onActionRenderer1,
								width : 130,
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									newLine : true,
									required : true,
									fieldVisible : true
								}
							},
							{
								field : 'rzzlamount',
								dataType:"currency",
								header : '融资金额（万元）',
								summary:true,
								formEditorConfig : {
									type : 'float',
									vtype : "float",
									required : true,
									labelWidth : 100,
									width : 200,
									newLine : false
								}
							},
							{
								field : 'leasformname',
								header : '业务类型',
								queryConfig : {
									type : 'combobox',
									textField : 'name',
									newLine : false,
									valueField : 'value',
									fillFromFieldName : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'leas_form',
										xmlFileName : '/combos/comboDict.xml'
									}
								},
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									readOnly : true,
									newLine : true,
									fieldVisible : false
								}
							},
							{
								field : 'leasform',
								header : '业务类型',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
									required : true,
									fieldVisible : true,
									params : {
										pid : 'leas_form',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'projsourcename',
								header : '项目来源',
								queryConfig : {
									newLine:true,
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'proj_source',
										xmlFileName : '/combos/comboDict.xml'
									}
								},
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'projsource',
								header : '项目来源',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine : false,
									required : true,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										pid : 'proj_source',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'projkindname',
								header : '项目类型',
								queryConfig : {
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
									fillFromFieldName : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'proj_kind',
										xmlFileName : '/combos/comboDict.xml'
									}
								},
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									readOnly : true,
									newLine : true,
									fieldVisible : false
								}
							},
							{
								field : 'projkind',
								header : '项目类型',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									required : true,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										pid : 'proj_kind',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'projectphasename',
								header : '项目阶段',
								queryConfig : {
									newLine:false,
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
									fillFromFieldName : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'project_phase',
										xmlFileName : '/combos/comboDict.xml'
									}
								},
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									readOnly : true,
									fieldVisible : false
								}
							},
							{
								field : 'projectphase',
								header : '项目阶段',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									required : true,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										pid : 'project_phase',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},	
							{
								field : 'provincename',
								header : '所处省份',
								queryConfig : {
									type : 'combobox',
									newLine : true,
									textField : 'name',
									valueField : 'value',
									showNullItem : "true",
									nullItemText : "", //显示空值
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									}
								},
								formEditorConfig : {
									labelWidth : 100,					
									entityClassName : 'com.tenwa.business.entity.base.District',
									fillFromFieldName : 'province',
									fillProperty : 'name',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'province',
								header : '所处省份',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									entityClassName : 'com.tenwa.business.entity.base.District',
									newLine : true,
									textField : 'name',
									valueField : 'value',
									showNullItem : "true",
									required : true,
									fieldVisible : true,
									params : {
										pid : 'CHN',
										xmlFileName : '/eleasing/workflow/proj/proj_search/province.xml'
									},
									cascade : {
										childrenFieldNames : [ 'city' ],
										clearFieldNames : [ 'city' ]
									}
								}
							},
							{
								field : 'cityname',
								header : '地级市',
								formEditorConfig : {
									fillFromFieldName : 'city',
									fillProperty : 'name',
									entityClassName : 'com.tenwa.business.entity.base.District',
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'city',
								header : '地级市',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine : false,
									required : true,
									entityClassName : 'com.tenwa.business.entity.base.District',
									showNullItem : "true",
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_search/city.xml'
									},
									cascade : {
										parentFieldNames : [ 'province' ]
									}
								}
							},
							{
								field : 'windtopographyname',
								header : '风电场地形特征',
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									fieldVisible : false
								}
							},
							{
								field : 'windtopography',
								header : '风电场地形特征',
								visible : false,
								formEditorConfig : {
									type : 'combobox',
									newLine : true,
									required : true,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									params : {
										pid : 'windtopography',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'expectscale',
								header : '限电比例(%)',
								formEditorConfig : {
									type : 'text',
									vtype : "float",
									required : true,
									onkeyup:'adjustInternetpower',
									fieldVisible:true
								}
							},
							
							{
								field : 'benchmarkprice',
								header : '标杆电价（元/度）',
								width : 120,
								formEditorConfig : {
									type : 'text',
									vtype : "float",
									newLine : true,
									required : true,								
									width :200,
									labelWidth : 100
								}
							},
							{
								field : 'internetprice',
								header : '上网电价（元/度）',
								width : 120,
								formEditorConfig : {
									type : 'text',
									vtype : "float",
									required : true,
									newLine : false,
									labelWidth : 100,
									width : 200
								}
							},
							
							{
								field : 'fantype',
								header : '机组选型',
								formEditorConfig : {
									type : 'text',
									required : true,
									newLine : true,
									labelWidth : 100,
									colspan:3,
									width : "100%"
								}
							},
							{
								field : 'fansum',
								header : '机组数量（台）',
								summary:true,
								formEditorConfig : {
									newLine : true,
									type : 'text',
									vtype : "float",
									required : true,
									newLine : true,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'projinstalledcapacity',
								header : '项目容量（万千瓦）',
								summary:true,
								width : 120,
								formEditorConfig : {
									type : 'text',
									required : true,
									vtype:'float',
									
									onkeyup:'adjustInternetpower',
									labelWidth : 100,
									width : 200
								}
							},
						
						
							{
								field : 'avewindspeed',
								header : '平均风速（米/秒）',
								width : 120,
								formEditorConfig : {
									type : 'text',
									required : true,
									vtype : "float",
									newLine : true,
									labelWidth : 200,
									width : 200
								}
							},
							{
								field : 'equhours',
								header : '年等效满负荷小时数（小时）',
								width : 120,
								formEditorConfig : {
									type : 'text',
									required : true,
									vtype:'float',
									newLine : false,
									labelWidth : 200,
									 onkeyup:'adjustInternetpower',
									width : 200
								}
							},
							{
								field : 'theoryproduction',
								header : '年理论发电量（万度）',
								summary:true,
								width : 120,
								formEditorConfig : {
									type : 'text',
									vtype : "float",
									newLine : true,
									readOnly:true,
									required : true,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'internetpower',
								header : '年上网电量（万度）',
								summary:true,
								width : 120,
								formEditorConfig : {
									type : 'text',
									vtype : "float",
									required : true,
									readOnly:true,
									newLine : false,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'projinvestment',
								header : '项目静态投资（万元）',
								summary:true,
								width : 120,
								formEditorConfig : {
									type : 'text',
									newLine : true,
									 onkeyup:'adjustInternetpower',
									vtype : "float",
									required : true,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'kwcostint',
								header : '千瓦造价（元）',
								//dataType : "currency",
								formEditorConfig : {
									type:'text', //客户要求四舍五入
								//	vtype : "int",
									readOnly:true,
									newLine : false,
									required : true,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'projcapital',
								header : '项目资本金（万元）',
								summary:true,
								width : 120,
								formEditorConfig : {
									type : 'float',
									vtype : "float",
									required : true,
									labelWidth : 100,
									width : 200,
									newLine : true
								}
							},
							
							{
								field : 'productiontime',
								header : '（预计）投产时间',
								headerAlign : 'center',
								visible : true,
								width : 120,
								dateFormat : "yyyy-MM-dd",
								formEditorConfig : {
									newLine : false,
									type : 'date',
									required : true,
									labelWidth : 100,
									width : '100%',
									format : 'yyyy-MM-dd',
								
								}
							},
							
							{
								field : 'projappstatus',
								header : '项目审批情况',
								formEditorConfig : {
									newLine : true,
									required : true,
									colspan : 3,
									width : "100%",
									type : 'textarea'
								}
							},
							{
								field : 'note',
								header : '项目补充信息',
								formEditorConfig : {
									newLine : true,
									colspan : 3,
									width : "100%",
									type : 'textarea'
								}
							},
							{
								field : 'linkman',
								header : '项目联系人',
								formEditorConfig : {
									required : true,
									type : 'text',
									labelWidth : 100,
									width : 200,
									newLine : true
								}
							},
							{
								field : 'contactway',
								header : '联系方式',
								formEditorConfig : {
									type : 'text',
									required : true,
									labelWidth : 100,
									width : 200
								}
							},
							{
								field : 'txpeoplename',
								header : '天信对接人',
								visible : true,
								formEditorConfig : {
									fieldVisible : false
								}
							},
							{
								field : 'txpeople',
								header : '天信对接人',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									required : false,
									textField : 'name',
									valueField : 'id',
									allowInput : false,
									newLine : true,
									fieldVisible : true,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_common/creator_combox.xml'
									}
								}
							},
						/* 	{
								field : 'creatorname',
								header : '登记人',
								visible : true,
								formEditorConfig : {
									fieldVisible : false
								}
							},
							{
								field : 'creator',
								header : '登记人',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									textField : 'name',
									valueField : 'id',
									readOnly : false,
									allowInput : false,
									newLine : false,
									fieldVisible : true,
									onSelect : function($me, inputObj, rowData) {
									},
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_common/creator_combox.xml'
									}
								}
							}, */				
							{
								field : 'creatorname',
								header : '登记人',
								visible : true,
								formEditorConfig : {
									defaultValue :'${sessionScope.loginUser.realname}',
									newLine : false,
									type : 'text',
									labelWidth : 110,
									width : '100%',
									required : false,
									readonly : false
								}
							},
							{
								field : 'registdeptname',
								header : '登记部门',
								visible : true,
								formEditorConfig : {
									fieldVisible : false,
								}
							},
							{
								field : 'registdept',
								header : '登记部门',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									textField : 'name',
									valueField : 'id',
									allowInput : false,
									readOnly : false,
									newLine : true,
									fieldVisible : true,
									onSelect : function($me, inputObj, rowData) {
									},
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_common/department_combox.xml'
									}
								}
							},
							{
								field : 'createdate',
								header : '登记时间',
								headerAlign : 'center',
								visible : true,
								width : 100,
								dateFormat : "yyyy-MM-dd",
								formEditorConfig : {
									newLine : false,
									type : 'date',
									readOnly : true,
									defaultValue : mini.formatDate(new Date(),
											"yyyy-MM-dd"),
									labelWidth : 100,
									width : '100%',
									format : 'yyyy-MM-dd',
									required : false
								}
							},
							{
								field : 'modificatorname',
								header : '修改人',
								visible : true,
								formEditorConfig : {
									fieldVisible : false
								}
							},
							{
								field : 'modificator',
								header : '修改人',
								visible : false,
								formEditorConfig : {
									width : 200,
									type : 'queryinput',
									textField : 'name',
									valueField : 'id',
									readOnly : true,
									newLine : true,
									fieldVisible : true,
								 	params : {
										xmlFileName : '/eleasing/workflow/proj/proj_common/creator_combox.xml'
									} 
								}
							}, {
								field : 'modifydate',
								header : '修改时间',
								headerAlign : 'center',
								visible : true,
								width : 100,
								dateFormat : "yyyy-MM-dd",
								formEditorConfig : {
									newLine : false,
									type : 'date',
									readOnly : true,
									labelWidth : 100,
									width : '100%',
									format : 'yyyy-MM-dd',
									required : false
								}
							}, {
								field : 'type',
								header : '类型',
								visible : false,
								formEditorConfig : {
									readOnly : true,
									fieldVisible : false,
									value : 1
								}
							} ]
				});
	});
	function onActionRenderer(e) {
		var id = e.record.projid;
		var name = e.record.projname;
		var s = '<a class="Edit_Button" href="javascript:opencustdetail(\''
				+ id + '\')">' + '查看详情' + '</a>';
		return s;
	}
	
	//加载客户信息
	function showcustinfo(custid,custclass){
		var params = "id="+custid+"&isView=true";
		if(custclass == "CUST_INFO_PERSON"){
			var url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
			var sheight = window.screen.availHeight - 30;
			var swidth = window.screen.availWidth - 10;
			var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
				window.open(url, '_blank', winoption);
		}else{
			var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
			var sheight = window.screen.availHeight - 30;
		    var swidth = window.screen.availWidth - 10;
		    var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
			window.open(url, '_blank', winoption);
		}
	}
	
	function onActionRenderer1(e) {
		var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;
        var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id + '\')">' + e.value + '</a>';
        return s;
	}

	function opencustdetail(id) {
		var params = "id=" + id;
		var url = getRootPath()
				+ "/workflow/forms/Proj/proj_develop_list/proj_wind_power_detail.bi?"
				+ params;
		var sheight = window.screen.availHeight - 30;
		var swidth = window.screen.availWidth - 10;
		var winoption = "left=0px,top=0px,height="
				+ sheight
				+ "px,width="
				+ swidth
				+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
		window.open(url, '_blank', winoption);
	}
	//选择客户后自动带出注册号
	function setRegistcode($me, inputObj, rowData) {
		mini.getbyName("registcode").setValue(rowData.registcode);
		mini.getbyName("registcodeid").setValue(rowData.registcodeid);
	}
	
	//计算年上网电量
	 function adjustInternetpower(){
		var expectscale=$("input[name=expectscale]").val();
		var projinstalledcapacity =$("input[name=projinstalledcapacity]").val();
		var equhours = $("input[name=equhours]").val();
		var projinvestment = $("input[name=projinvestment]").val();
		//获取预计限电比例
		expectscale=expectscale||0;
		//项目容量
		projinstalledcapacity = projinstalledcapacity || 0;
		//年等效满负荷小时数
		equhours = equhours || 0;
		//项目静态投资
		projinvestment=projinvestment|| 0;
		//年理论发电量
		var temp = Number(projinstalledcapacity) * Number(equhours);
		mini.getbyName("theoryproduction").setValue(decimal(temp,2));
		//年上网电量
		var temp1 = temp* (100-Number(expectscale))/100;
		mini.getbyName("internetpower").setValue(decimal(temp1,2));
		//千瓦造价
		if(Number(projinstalledcapacity)!=0){
		var temp2=Number(projinvestment)/Number(projinstalledcapacity);
		mini.getbyName("kwcost").setValue(decimal(temp2,0));
		}else{
			mini.getbyName("kwcost").setValue(null);
		}
		
		
	}
function showDetail(id) {
	var params = "id=" + id;
	var url = getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_wind_power_detail_see.bi?"
			+ params;
	/*
    var url = getRootPath()
		+ "/acl/showPledgeInformation.acl?contract_id="
		+ id + "&opertype=view";
	*/
	var sheight = window.screen.availHeight - 30;
	var swidth = window.screen.availWidth - 10;
	var winosption = "left=0px,top=0px,height="
		+ sheight
		+ "px,width="
		+ swidth
		+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
	window.open(url, '_blank', winosption);
}  	
</script>
</head>
<body>
	<div id="id_proj_wind_power_list"></div>
</body>
</html>


