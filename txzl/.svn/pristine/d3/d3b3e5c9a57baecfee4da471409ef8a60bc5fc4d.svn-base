<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>合同归档</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/seajs/sea.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/workFlowUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript">
jQuery(function() {
	tenwa
			.createTable({

				id:'table_id2',
				width:globalClientWidth,
				height:globalClientHeight,
				title:'合同归档',
				iconCls:'icon-node',
				multiSelect:false,
				hiddenQueryArea:false,//是否将查询区域折叠起来
				queryButtonColSpan:10,
				queryButtonNewLine:false,
				showPager:true,				
				xmlFileName:'/docarchives/contract_archives_list.xml',
				tools: [{
					html : '档案归档',
					plain : true,
					iconCls : 'icon-addfolder',
					handler : function(miniTable, buttonText) {
						var rows = miniTable.getSelecteds();
						var userid='${sessionScope['login_userid']}';
						if (rows.length == 0) {
							mini.alert("请选择要归档的合同！");
							return false;
						}
						var url=getRootPath()+"/acl/showContractAchives.acl?id="+rows[0].id+"&conid="+ rows[0].conid+"&custname="+rows[0].custname;
									var sheight = window.screen.availHeight - 30;
									var swidth = window.screen.availWidth - 10;
									var winoption = "left=0px,top=0px,height="
										+ sheight
										+ "px,width="
										+ swidth
										+ "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
					window.open(url, '_blank', winoption);
						} 
				}],
				columns:[ 
						{type:'indexcolumn'},
						{type:'checkcolumn'},  
						{field:'id',header:'id',visible:false},
						{field:'conid',header:'合同编号'/* ,
							renderer: function(e){
								var conid = e.record.conid; 
								return '<a href="javascript:void(0);" onclick="showdetail(\''+ e.record.id+'\')">'+conid+'</a>';
								
							}	 */
						},
						{field:'contractnumber',header:'业务合同编号'},
						{field:'projectname',header:'项目名称'},
						{field:'custname',header:'客户名称',queryConfig:{}},
						{field:'start_date_',header:'起租日'},
						{field:'containernumber',header:'柜号',queryConfig:{}},
					    {field : 'fillingtypename',header : '类别',visible:false,
							queryConfig:{
					    	type : 'combobox',
							textField : 'name',
							valueField : 'value',
							fieldVisible : false,
							params : {
								pid : 'fillingtype',
								xmlFileName : '/combos/comboDict.xml'
							}}},
						{field:'filingname',header:'文件名称',visible:false,queryConfig:{newLine:true}},
						{field:'filing_status',header:'归档状态',queryConfig:{
							colspan : 1,
							type : 'combobox',//表单域类型
							valueField : 'value',
							textField : 'name',
							allowInput : false,
							showNullItem : true,
							nullItemText:'',
							data : [{name : '已归档',value : '已归档'},{name : '部分归档',value : '部分归档'},{name : '未归档',value : '未归档'}],
							text:'',
							value:""
						}}
				]
			
		});
	});
	function showdetail(id){
		//alert(id);
		//需要打开窗口的url
		var URL = "${pageContext.request.contextPath}/leasing/doc_manager/contract_archives/contract_archives.bi";
		//参数形式为json
	 	var params = {id:id};
	 	//打开一个全屏的新窗口，带上该条记录的id参数
	 	openFullScreenWindow(URL,params);
	}
</script>
</head>
<body>
</body>
</html>