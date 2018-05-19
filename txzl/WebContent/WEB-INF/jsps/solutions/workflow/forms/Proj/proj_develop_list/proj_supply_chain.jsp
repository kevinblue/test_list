<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>供应链项目一览</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyUtils.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyLoadMask.js"></script>
<script type="text/javascript">
	var currentClientWidth = document.documentElement.clientWidth;
	var currentClientHeight = document.documentElement.clientHeight;
	jQuery(function() {seajs.use([ "js/apcomponent/aptable/aptable.js" ], function(ApTable) {
		new ApTable({
			id : "proj_supply_chain_list",
			renderTo : "id_proj_supply_chain_list",
			width : currentClientWidth,
			height : currentClientHeight,
			editFormPopupWindowWidth : 800,
			editFormPopupWindowHeight : 500,
			lazyLoad : false,
			title : "供应链项目一览",
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
			queryButtonColSpan : 2,
			queryButtonNewLine : false,
			xmlFileName : '/eleasing/workflow/proj/proj_search/proj_develop_list.xml',
			afterShowWindowCallBack : function(miniTable, miniForm,
					operFlag) {
				if (operFlag == "edit") {
					var custname = mini.getbyName("custname")
							.getValue();
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
			params : {
				type : 2
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
						visible :true,
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
							onSelect: setRegistcode,
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
							readOnly:true,
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
							type : 'float',
							vtype : "float",
							required : true,
							labelWidth : 100,
							width : 200,
							newLine : false
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
							newLine : false,
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
							required : true,
							fieldVisible : true,
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
							showNullItem : "true",
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
							entityClassName : 'com.tenwa.business.entity.base.District',
							newLine : true,
							showNullItem : "true",
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
							showNullItem : "true",
							entityClassName : 'com.tenwa.business.entity.base.District',
							textField : 'name',
							valueField : 'value',
							required : true,
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
						field : 'custcreditratingname',
						header : '金风质量等级',
						width : 120,
						/* queryConfig : {
							type : 'combobox',
							textField : 'name',
							newLine : false,
							valueField : 'value',
							params : {
								pid : 'custcreditrating',
								xmlFileName : '/combos/comboDict.xml'
							}
						}, */
						formEditorConfig : {
							type : 'text',
							labelWidth : 100,
							width : 200,
							newLine : false,
							fieldVisible : false
						}
					},
					{
						field : 'custcreditrating',
						header : '金风质量等级',
						visible : false,
						formEditorConfig : {
							type : 'combobox',
							newLine : true,
							required : true,
							textField : 'name',
							valueField : 'value',
							fieldVisible : true,
							params : {
								pid : 'custcreditrating',
								xmlFileName : '/combos/comboDict.xml'
							}
						}
					},
					{
						field : 'annualorderamountname',
						header : '与金风的年订单金额',
						width : 120,
						formEditorConfig : {
							type : 'text',
							labelWidth : 100,
							width : 200,
							newLine : false,
							fieldVisible : false
						}
					},
					{
						field : 'annualorderamount',
						header : '与金风的年订单金额',
						visible : false,
						formEditorConfig : {
							type : 'combobox',
							newLine : false,
							textField : 'name',
							required : true,
							valueField : 'value',
							fieldVisible : true,
							params : {
								pid : 'annualorderamount',
								xmlFileName : '/combos/comboDict.xml'
							}
						}
					},
					
					

					{
						field : 'purchasesaccounted',
						header : '金风采购量占比(%)',
						formEditorConfig : {
							type : 'text',
							vtype : "float",
							required : true,
							labelWidth : 100,
							width : 200,
							newLine : true
						}
					},
					{
						field : 'supcategoryname',
						header : '供应商类别',
						/* queryConfig : {
							type : 'combobox',
							textField : 'name',
							newLine : true,
							valueField : 'value',
							params : {
								pid : 'supcategory',
								xmlFileName : '/combos/comboDict.xml'
							}
						}, */
						formEditorConfig : {
							type : 'text',
							labelWidth : 100,
							width : 200,
							newLine : false,
							fieldVisible : false
						}
					},
					{
						field : 'supcategory',
						header : '供应商类别',
						visible : false,
						formEditorConfig : {
							type : 'combobox',
							newLine : false,
							required : true,
							textField : 'name',
							valueField : 'value',
							fieldVisible : true,
							params : {
								pid : 'supcategory',
								xmlFileName : '/combos/comboDict.xml'
							}
						}
					},
					{
						field : 'indirectsup',
						header : '间接供应商',
						formEditorConfig : {
							type : 'text',							
							required :false,
							labelWidth : 100,					
							newLine : true
						}
					},
					{
						field : 'txcreditratingname',
						header : '天信客户信用等级',
						width : 120,
						formEditorConfig : {
							type : 'text',
							colspan : 3,
							width : 200,
							readOnly:true,
							newLine : false,
							fieldVisible : true
						}/* ,
						queryConfig : {
							type : 'combobox',
							textField : 'name',
							valueField : 'value',
							params : {
								pid : 'credit_rating',
								xmlFileName : '/combos/comboDict.xml'
							}
						} */
					},
					{
						field : 'txcreditrating',
						header : '天信客户信用等级',
						visible : false,
						formEditorConfig : {
							newLine : true,
							labelWidth : 120,
							fieldVisible : false
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
							fieldVisible : false,

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
							newLine : true,
							required : true,
							fieldVisible : true,
							params : {
								xmlFileName : '/eleasing/workflow/proj/proj_common/creator_combox.xml'
							}
						}
					},
					/* {
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
							allowInput : true,
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
					}, 
					 {
						field : 'type',
						header : '类型',
						visible : false,
						formEditorConfig : {
							readOnly : true,
							fieldVisible : false,
							value : 2
						}
					},
					{
						field : 'custclass',
						visible : false,
						header : '客户类型'
					},
				    {field: 'filename', header: '上传文件信息',width: 320,visible: true,
				    	formEditorConfig:{ fieldVisible:false},
				     	renderer: function(e){
							return getPaydownload(e);
						}
	            },
		     {
				field: 'create', 
				header: '操作',
				formEditorConfig:{ fieldVisible:false},
			    renderer:function(e){
			    	var id = e.record.id;
					return "<a href='javascript:void(0);' onclick='upFile(\"" + id + "\")'>上传采购订单</a>";    	
			}}]
		});
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
		mini.getbyName("txcreditratingname").setValue(rowData.txcreditratingname);
		mini.getbyName("txcreditrating").setValue(rowData.txcreditrating);
		
		//mini.getbyName("custname").setValue(rowData.name);
		//mini.getbyName("custid").setValue(rowData.custid);
	
	}
	
	 function getPaydownload(e){
		  var fileHtmlTd = '';
		  //文件id
		 var idStr = e.record.fileidv;
		  //console.info(typeof idStr);
		if(idStr == null || idStr == undefined || idStr == ''){
			return "还未上传采购订单！";
		}
		var idArray = idStr.split(",");
		//文件名
	     var filenameStr = e.record.filenamev;
	      var filenameArray = filenameStr.split(",");
	    //上传时间
	    var createdateStr = e.record.createdatev;
	    var createdateArray;
	    if(typeof(createdateStr)== 'object'){
	    	var date=tenwa.toDate(createdateStr+"","yyyy-MM-dd hh:mm:ss");
	    	var ss=tenwa.toChar(date,"yyyy-MM-dd hh:mm:ss");
	    	createdateArray = ss.split(",");
	    }else{
		    createdateArray = createdateStr.split(",");
	    }
	    //上传人
	    var realnameStr = e.record.realnamev;
	    var realnameArray = realnameStr.split(",");
	     //拼table
	     var renderHtml1 = "<table style='border:0px solid #FFF;'>";
	     var renderHtml2 = "</table>";
	     for(var i=0;i<filenameArray.length;i++){
	   	 var fnStr = filenameArray[i];
	   	fileHtmlTd +="<tr style='border:0px solid #FFF;'>"+
	   	                     "<td style='border:0px solid #FFF;'>"+
	   	                             "<a href='javascript:void(0);' onclick='removeUploadFilebyId(\""+ idArray[i] + "\");'style='color:red;'>"+"[删除]"+"</a>"+
	   	                     "</td>"+
	   	                     "<td style='border:0px solid #FFF;'>"+
	                                  "<a href='javascript:void(0);' onclick='downloadFile(\""+ idArray[i] + "\")'>"+"["+fnStr+"]"+"</a>"+
	                         "</td>"+
	                         "<td style='border:0px solid #FFF;'>"+
	                                  "【"+   realnameArray[i]+"】"+
	                         "</td>"+
	                         "<td style='border:0px solid #FFF;'>"+
	                                  "【"+ createdateArray[i]+"】"+
	                         "</td>"+
	   	                "</tr>";
	             } 
	     return renderHtml1+fileHtmlTd+renderHtml2;  
	}

	//删除附件
	function removeUploadFilebyId(uploadAttachmentFileDetailId) {
		mini.confirm("确认删除合同 附件？", "删除？", function(action){
			if(action=="ok"){
				$.ajax({
					cache : false,
					async : false,
			        type: "post",
			        dataType: "JSON",
			        url : getRootPath()+ "/acl/ContractEnclosureMarkDelect.acl",
			        data: {uploadAttachmentFileDetailId:uploadAttachmentFileDetailId},
			        success: function (data) {
			        	if(data){
			        		mini.alert("删除成功！");
			        		mini.get("proj_supply_chain_list").reload();
			        		return;
			        	}else{
			        		mini.alert("删除失败！");
			        		return;
			        	}
			        	
			        }
			    })
			}else{
				return;
			}
		});
	}

	//上传附件
	function upFile(id) {
		var filekey = id;
		var uploadutil = new uploadUtil({
				url : '/leasing/file/uploadFile.action',
				tableid : "contract_word",
			    modelname : '采购订单',
			    parames : {
						filekey : filekey　　　
					},
					jscallback : 'reloadcustcontactfile2'
			});
		uploadutil.createFileAndShow();
	}

		function downloadFile(Id) {
			attachmentDown({returnType:'file',url:"${pageContext.request.contextPath}/leasing/template/downLoadAttachById.action?id="+Id});
		} 
		
		function reloadcustcontactfile2(message){
			mini.alert(message);
			mini.get("id_uploadfile").hide();
			mini.unmask(document.body);
			mini.get("proj_supply_chain_list").reload();
		}
function showDetail(id) {
	var params = "id=" + id;
	var url = getRootPath()
			+ "/workflow/forms/Proj/proj_develop_list/proj_supply_chain_detail_see.bi?"
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