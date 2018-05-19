<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix='permission' uri='/WEB-INF/tlds/permission.tld' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>法人客户信息管理</title>
<%@include file="/base/mini.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tenwa/tenwa.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
    	<div class="mini-panel" title="法人客户" showCollapseButton="false" style="width: 100%;">
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table" style="width:95%">
	                <tr>
	                    <td style="width:100px;">客户名称：</td>
	                    <td> <input name="custname" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                   <!--  <td style="width:120px;" >注册号/统一社会信用代码：</td>
	                    <td><input name="orgcode" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                     -->
	                     <td class="miniext-form-td">客户类别：</td>
	                    <td><mini:dict  name="custtype"  parentid="cust_type"/></td>
	                    <td style="width:100px;" >内部行业：</td>
	                    <td><mini:dict  name="custkind"  parentid="cust_kind"/>
	                    </td>
	                </tr>   
	                <tr>
	                   <!--  <td class="miniext-form-td">纳税人识别号：</td>
	                    <td><input name="taxregcode" class="mini-textbox miniext-form-fit" onEnter="search"/></td> -->
	                   
	                   <!--  <td class="miniext-form-td">是否潜在客户：</td>
	                    <td><input name="draft" class="mini-combobox miniext-form-fit" textField="text" valueField="value"   data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]" allowInput="false" />
	                    </td> -->
	                    <td style="width:100px;">实际控制人：</td>
	                    <td> <input name="custactualpeople" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                	<td style="width:100px;">省份：</td>
	                <td> <input name="province" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	               	                    <td colspan="2">
	                    	<a class='mini-button query-button' iconCls='icon-search' onclick="search">搜索</a>
	                    	<a  class='mini-button query-button' onclick="clear" iconCls='icon-remove' style='color:#FFA5A5;border: 1px solid #FFA5A5;'>清空</a>
	                    </td>
	                </tr> 
	                <tr>
	                    <!-- <td class="miniext-form-td">是否废弃：</td>
	                    <td > <input name="invalid" class="mini-combobox miniext-form-fit" textField="text"   valueField="value"    data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]"   allowInput="false"    value="否"	  text="否"/> </td> -->
	                    
	                     
	                       <!--   <td style="width:100px;">区域：</td>
	                    <td> <input name="districtname" 
	                    class="mini-combobox miniext-form-fit" 
							   dataField="datas"
								textField="name"
								valueField="name"
								showNullItem="true"
							   allowInput="true" ,
							   data-options="{_params:{pid:'district',_xmlFileName:'combos/comboDict.xml'}}" 
							   onbeforeshowpopup="miniextonbeforeshowpopup"
	                    onEnter="search"/>
	                    </td> -->

	                </tr>   
	            </table>
	        </div>
	    </div>
    </div>
    <!-- 按钮部分 -->
    <div id="miniuiextbuttons"></div>
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">      
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="${urlPrefix}/eleasing/jsp/cust_info/cust_company/cust_company_list.xml"  idField="id"
            sizeList="[5,10,20,50]"  allowAlternating="true" pageSize="20"
            dataField="datas" showColumnsMenu="true" >
            <div property="columns">
                <div type="indexcolumn" width="35"></div>
                <div type="checkcolumn" width="35"></div>
                <div field="custname" width="200" headerAlign="center" allowSort="true" renderer="onActionRenderer">客户名称</div> 
                 <div field="custshorthand" width="100" headerAlign="center" allowSort="true" >客户简称</div>                           
                <!-- <div field="custnumber" width="100" align="center" allowSort="true" headerAlign="center">客户编号</div> -->
                <div field="orgcode" width="145" headerAlign="center" allowSort="true" >注册号/统一社会信用代码</div>                                    
                <div field="rawValue_custtype" width="150" headerAlign="center" allowSort="true" >客户类别</div>
                <div field="rawValue_custkind"  headerAlign="center" allowSort="true" >内部行业</div>
                <div field="rawValue_draft"  headerAlign="center" allowSort="true">是否潜在客户</div>
                <div field="invalid"  headerAlign="center" allowSort="true">是否废弃</div> 
                <div field="areaname"  headerAlign="center" allowSort="true" >区域</div>
                <div field="rawValue_province"  headerAlign="center" allowSort="true" >省份</div> 
                <div field="rawValue_ownership"  headerAlign="center" allowSort="true" >企业性质</div>
                <div field="rawValue_custsource"  headerAlign="center" allowSort="true" >客户来源</div>
                <div field="rawValue_grouprelationship"  headerAlign="center" allowSort="true" >与集团关系</div>
                <div field="rawValue_taxregtype"  headerAlign="center" allowSort="true" >纳税人类别</div>
                <div field="taxregcode" width="145" headerAlign="center" allowSort="true" >纳税人识别号/国税登记号</div>
                <div field="personrep"  headerAlign="center" allowSort="true" >法定代表人</div>
              <!--   <div field="belongingpeoplename"  headerAlign="center" allowSort="true" >归属人</div> -->
                <div field="custactualpeople"  headerAlign="center" allowSort="true" >实际控制人</div>
             <!--    <div field="rawValue_adminlevel"  headerAlign="center" allowSort="true" >行政级别</div> -->
                <div field="companyaddress"  headerAlign="center" allowSort="true" >经营地址</div>
                <div field="creatorname"  headerAlign="center" allowSort="true" >登记人</div>
                <div field="deptmentname"  headerAlign="center" allowSort="true" >登记部门</div>
                <div field="createdate" width="150" headerAlign="center" allowSort="true" renderer="miniextonDateRenderer">登记时间</div>
                
            </div>
        </div> 
    </div>
     <div id="editWindow" class="mini-window" title="分配归属人" style="width:500px;height:100px;" 
	 showModal="true" allowResize="true" allowDrag="true"
	 >
	    <div id="userform">
	        <table>
	            <tr>
	                <td style="width:100px;">归属人：</td>
	                <td >
	                    <input id="belongingPeople" name="belongingPeople" 
	                    label="归属人" 
	                    class="mini-buttonedit mini-queryinput" allowInput="false"
		             		required="true"
						/>
	                </td>
	                <td >
	                    <a class="mini-button" onclick="saveBelongingPeople" style="width:80px">确定</a>
	                </td>
	            </tr>   
	        </table>
	    </div>
    </div>
    <script type="text/javascript">
        mini.parse();
        var loginUserId = "${empty sessionScope.loginUser.id ? '' : sessionScope.loginUser.id }";
        var grid = mini.get("datagrid1");
        var form = new mini.Form("form1");
        var custData = form.getData(true,true);
        miniui_ext.addOnEnter("form1");
        //客户权限
		var extendsql="";
		<permission:action code="permission_notapproval">
		if(extendsql.length>0){extendsql += "or "}
		extendsql += "nvl(pi.isLix,0)=0"
	    </permission:action>
		<permission:action code="permission_isapproval">
		if(extendsql.length>0){extendsql += "or "}
		extendsql += "nvl(pi.isLix,0)>0"
	    </permission:action>
	/* 	<permission:action code="permission_manager">
	       	if(extendsql.length>0){extendsql += "or "}
			extendsql += "instr(company.belongingpeople,'${sessionScope.login_userid}')>0"
		</permission:action>
		<permission:action code="permission_qyzj">
			if(extendsql.length>0){extendsql += "or "}
			extendsql += "instr((select wm_concat(id_) from vi_user_leader where qyzj = '${sessionScope.login_userid}'),company.belongingpeople)>0  "
		</permission:action>
		<permission:action code="permission_fdqzj">
			if(extendsql.length>0){extendsql += "or "}
			extendsql += "instr((select wm_concat(id_) from vi_user_leader where fdqzj = '${sessionScope.login_userid}'),company.belongingpeople)>0  "
		</permission:action>
		<permission:action code="permission_fzj">
			if(extendsql.length>0){extendsql += "or "}
			extendsql += "instr((select id_ from vi_user_leader where fzj = '${sessionScope.login_userid}'),company.belongingpeople)>0 "
		</permission:action>
		<permission:action code="permission_zj">
			if(extendsql.length>0){extendsql += "or "}
			extendsql += "instr((select id_ from vi_user_leader where zj = '${sessionScope.login_userid}'),company.belongingpeople)>0 "
		</permission:action>
		<permission:action code="permission_all">
			if(extendsql.length>0){extendsql += "or "}
			extendsql += " 1=1 "
		</permission:action> */
		if(extendsql && extendsql.length>0){
			custData["extendsql"] = "and ("+extendsql+")"
		}
        grid.load(custData);
        grid.frozenColumns(0, 2);
        function saveBelongingPeople(e){
        	var row = grid.getSelected();
        	grid.loading("操作中，请稍后......");
        	var belongingPeople  = mini.get("belongingPeople").getValue();
        	var data = {"custid":row.id,"belongingPeople":belongingPeople};
            $.ajax({
                url: "<%=request.getContextPath()%>/acl/saveBelongingPeople.acl",
                success: function (text) {
                	var editWindow = mini.get("editWindow");
            		editWindow.hide();
                    grid.reload();
                },
                data: data,
                error: function () {
                }
            });
        	
        }
        var loginUserIdTemp = loginUserId;
        var relationUserArr = [];
        var param1 = {};
        param1.loginUserId = loginUserIdTemp;
        param1.xmlFileName = '/eleasing/jsp/cust_info/cust_company/get_relation_user_list.xml';
        ajaxRequest({
        	url : '${pageContext.request.contextPath}/table/getTableData.action',
        	method : 'POST',
        	success : function(res) {
        		var scustinfo = res.responseText;
        		scustinfo = scustinfo.replace(/(^\s+)|(\s+$)/g, "");
        		var cust = eval("(" + scustinfo + ")");
        		var datas = cust.datas;
        		if(datas.length > 0){
        			for(var i = 0; i < datas.length; i++){
        				relationUserArr.push(datas[i].userid);
        			}
        		}else{
        			relationUserArr.push(loginUserId);
        		}
        	},
        	async : false,
        	failure : function(res) {
        		currentLoadMask.hide();
        	},
        	params : param1
        });
	    //表格加载之前发生，还未向后台发送参数，此时可修改往后台传的参数
	    function onBeforeCustGridLoad(e){
	    	e.data.TableRemoteSortField = e.data.sortField;
	    	e.data.TableRemoteSortDir = e.data.sortOrder;
	    }
        
        function search(e) {
        	custData = form.getData(true,true);
        	if(extendsql && extendsql.length>0){
    			custData["extendsql"] = "and ("+extendsql+")"
    		}
            grid.load(custData);
        }
        function clear(e) {
        	form.clear();
        	//mini.getbyName("invalid").setValue("否");
        	//mini.getbyName("invalid").setText("否");
        	custData = form.getData(true,true);
 		    grid.load(custData);
        }
        
        function opencustdetail(id){
        	var params = "id="+id;
      		var url = getRootPath()+"/leasing/cust_info/cust_company/cust_company_detail.bi?"+params;
      		var sheight = window.screen.availHeight - 30;
    		var swidth = window.screen.availWidth - 10;
    		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
      		window.open(url, '_blank', winoption);
        }
        function onActionRenderer(e) {
        	var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;
            var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id + '\')">' + e.value + '</a>';
            return s;
        }
        var button = new miniuiExtButtons({
    		url:'/leasing/cust_info/cust_company/input_cust_company_list.bi',
    		title : '法人客户信息', 
    		width :  800, 
    		height : globalClientHeight - 50,  
    	    datagridId : "datagrid1",
    	    renderTo : "miniuiextbuttons",
    /* 	    operValidate:function(miniTable, selectedRowDatas, operFlag){
    	    	 var fat=false;
    	    	if('修改'==operFlag){
    	    		var belongingpeople = selectedRowDatas[0].belongingpeople;
    	    	    var str=belongingpeople.split(",");
    	    	   for(var i=0;str.length>i;i++){
    	    	     	if(str[i].indexOf(loginUserId)>-1){
    	    	     		fat= true;
        	    		}
    	    	    } 
    	    	   if(fat==false){
    	    		   mini.alert("请选择归属人为自己的客户进行操作!");
    	    		   return false;
    	    	   }
    	    	  
    	    	}
    	    	return true;
    	    }, */
    	    constantFlagTable : "CustLaw",
    	    tools :[
				<permission:action code="cust_saveorupdate_permission">
				'新增', '修改', {
    	    	html : '废弃',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-remove',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					var row = miniTable.getSelected();
					/* if(row.belongingpeople != loginUserId){
						mini.alert("请选择归属人为自己的客户进行操作!");
						return ;
					} */
					if (null != row) {
				    	mini.confirm("确定废弃选中记录？","提示：",function(data){
				        	if("ok" == data){
				        		var custidvalue = row.id;
				        		var str = "";
				        		$.ajax({
				        	        url: getRootPath()+"/acl/checkCustByProjInfo.acl",
				        	        type: "post",
				        	        cache: false, 
				        	        data :{ "custId": custidvalue},
				        	        async : false,
				        	        success: function (text) {
				        	        	if("repeat" == text){
				        	        		mini.alert("该客户已有项目立项记录，不能修改为是否潜在客户，并且不能废弃！");
				        	        	}
				        	        	str = text;
				        	        }
				        	    });
				        		if("" == str){
					        		grid.loading("操作中，请稍后......");
						            $.ajax({
						                url: "<%=request.getContextPath() %>/acl/removeCustLaw.acl?id="+row.id,
						                data: {}  ,
						                success: function (text) {
						                	miniTable.reload();
						                },
						                error: function () {
						                }
						            });
				        		}
				        	}
				        });
				    } else {
				        mini.alert("请选中一条记录");
				    }
				}
    	    }</permission:action>
		/* 	<permission:action code="distri_belonging_people">
    	    ,{
    	    	html : '分配归属人',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-remove',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					//mini.get("datagrid1").reload();
			 		var row = miniTable.getSelected();
					if (null != row) {
						var editWindow = mini.get("editWindow");
						mini.get("belongingPeople").setValue(row.belongingpeople);
						mini.get("belongingPeople").setText(row.belongingpeoplename);
						editWindow.show();
				    } else {
				        mini.alert("请选中一条记录");
				    } 
				}
    	    }
    	    </permission:action> */
    	    ]
    	});
    </script>
</body>
</html>
<script>
jQuery(function(){
	tenwa.createQueryInput({
		id:'belongingPeople',
		label:'归属人',
		windowWidth: 600,
		textField: 'name',
		valueField:'id',
		onSelect: function($me, inputObj, rowData){
		},
		params: {
			extendsql:extendsql,
			xmlFileName: '/eleasing/workflow/proj/proj_common/proj_manage_combox.xml'
		}
	});
});
</script>