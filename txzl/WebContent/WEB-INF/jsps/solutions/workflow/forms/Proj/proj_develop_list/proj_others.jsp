<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>其他项目开发一览</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;
	jQuery(function() {
		tenwa
				.createTable({
					id : "proj_supply_chain_list",
					renderTo : "id_proj_supply_chain_list",
					width : currentClientWidth,
					height : currentClientHeight,
					lazyLoad : false,
					title : "其他项目开发一览",
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
					afterShowWindowCallBack : function(miniTable,miniForm, operFlag){
						if(operFlag == "edit"){
							var custname = mini.getbyName("custname").getValue();
							mini.getbyName("custid").setText(custname);
						}
					},
					params : {
						type : 3
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
							{
								field : 'custname',
								header : '客户名称',
								visible : true,
								width:135,
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
									//onkeyup : 'adjustCust',
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
									readOnly:true,
									fieldVisible : true,
									labelWidth : 120
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
									newLine : false,
									fieldVisible : false
								}
							},
							{
								field : 'projname',
								header : '项目名称',
								queryConfig : {},
								//renderer:onActionRenderer,
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
								header : '融资金额(万元)',
								summary:true,
								formEditorConfig : {
									type : 'text',
									required : true,
									vtype : "float",
									labelWidth : 100,
									newLine : false,
									width : 200
								}
							},
							{
								field : 'projsourcename',
								header : '项目来源',
								queryConfig : {
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
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
									newLine : true,
									textField : 'name',
									valueField : 'value',
									fieldVisible : true,
									required : true,
									params : {
										pid : 'proj_source',
										xmlFileName : '/combos/comboDict.xml'
									}
								}
							},
							{
								field : 'leasformname',
								header : '项目类型',
								queryConfig : {
									newLine:true,
									type : 'combobox',
									textField : 'name',
									valueField : 'value',
									params : {
										pid : 'leas_form',
										xmlFileName : '/combos/comboDict.xml'
									}
								},
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									width : 200,
									readOnly:true,
									newLine : false,
									fieldVisible : false
								}
							},
							{
								field : 'leasform',
								header : '项目类型',
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
								field : 'provincename',
								header : '所处省份',
								queryConfig : {
									type : 'combobox',
									textField : 'name',
									newLine : false,
									valueField : 'value',
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
									showNullItem : "true",
									entityClassName : 'com.tenwa.business.entity.base.District',
									newLine : true,
									textField : 'name',
									valueField : 'value',
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
								field : 'projsituation',
								header : '项目基本情况',
								formEditorConfig : {
									type : 'textarea',
									newLine : true,
									required : true,
									colspan : 3,
									width : '100%'
								}
							},
							{
								field : 'linkman',
								header : '项目联系人',
								formEditorConfig : {
									type : 'text',
									labelWidth : 100,
									required : true,
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
									textField : 'name',
									valueField : 'id',
									allowInput : false,
									required : true,
									newLine : true,
									fieldVisible : true,
									params : {
										xmlFileName : '/eleasing/workflow/proj/proj_common/creator_combox.xml'
									}
								}
							}, {
								field : 'projschedule',
								header : '项目进度',
								formEditorConfig : {
									type : 'text',
									required : true,
									labelWidth : 100,
									width : 200
								}
							}, {
								field : 'note',
								header : '备注',
								formEditorConfig : {
									type : 'textarea',
									newLine : true,
									colspan : 3,
									width : '100%'
								}
							}, {
								field : 'type',
								header : '类型',
								visible : false,
								formEditorConfig : {
									readOnly : true,
									fieldVisible : false,
									value : 3
								}
							},
							{
								field : 'custclass',
								visible : false,
								header : '客户类型'
							}]
				});
	});
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
	function onActionRenderer(e) {
		var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;
        var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id +'\',\''+e.value+ '\')">' + e.value + '</a>';
        return s;
	}
	//加载项目信息
	function opencustdetail(id, name) {
		var params = "id=" + id+"&name="+name;
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
	function setRegistcode($me, inputObj, rowData){
		mini.getbyName("registcode").setValue(rowData.registcode);
		mini.getbyName("registcodeid").setValue(rowData.registcodeid);
		//mini.getbyName("custname").setValue(rowData.name);
		//mini.getbyName("custid").setValue(rowData.custid);
	
	}
function showDetail(id) {
	var params = "id=" + id;
	var url = getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_others_detail_see.bi?"
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
	<div id="id_proj_supply_chain_list"></div>
</body>
</html>