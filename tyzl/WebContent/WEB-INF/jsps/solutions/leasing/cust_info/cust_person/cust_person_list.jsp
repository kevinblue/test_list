<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>自然人客户信息管理</title>
<%@include file="/base/mini.jsp"%>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
    	<div class="mini-panel" title="自然人客户"  showCollapseButton="false" style="width: 100%;">
	        <div class="fieldset-body" id="form2">
	            <table class="miniext-form-table" style="width:95%">
	                <tr>
	                    <td style="width:100px;">客户名称：</td>
	                    <td><input name="custname" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                    <td style="width:100px;">客户编号：</td>
	                    <td><input name="custnumber" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                    <td style="width:100px;">身份证号码：</td>
	                    <td><input name="idcardno" class="mini-textbox miniext-form-fit" onEnter="search"/></td>
	                </tr>
	                <tr>
	                    <td class="miniext-form-td">客户内部行业：</td>
	                    <td><mini:dict  name="custkind"  parentid="cust_kind"/></td>
	                    <td class="miniext-form-td">客户类别：</td>
	                    <td><mini:dict  name="custtype"  parentid="cust_type"/></td>
	                    <td class="miniext-form-td">是否草稿：</td>
	                    <td><input name="draft" class="mini-combobox miniext-form-fit" textField="text"  valueField="value"  allowInput="false" data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]"/> </td>
	                </tr>  
	                <tr>
	                    <td class="miniext-form-td">是否废弃：</td>
	                    <td> <input name="invalid" class="mini-combobox miniext-form-fit" textField="text" valueField="value"   allowInput="false"  value="否"	text="否" data="[{text:'是',value:'是'},{text:'否',value:'否'},{text:'全部',value:''}]"/></td>
	                    <td colspan="4">
	                    	<a class='mini-button query-button' iconCls='icon-search' onclick="search">搜索</a>
	                    	<a class='mini-button query-button' onclick="clear" iconCls='icon-remove' style='color:#FFA5A5;border: 1px solid #FFA5A5;'>清空</a>  
	                    </td>
	                </tr>    
	            </table>
	        </div>
	    </div>
    </div>
	<!-- 按钮部分 -->
    <div id="miniuiextbuttons"></div>
    <!-- 列表部分 -->
    <div class="mini-fit" id="_fit">   
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" url="${urlPrefix}/eleasing/jsp/cust_info/cust_person/cust_person_list.xml" onbeforeload="onBeforeCustGridLoad" idField="id"  sizeList="[5,10,20,50]" pageSize="20" allowAlternating="true" dataField="datas">
            <div property="columns">
                <div type="indexcolumn" width="35"></div>
                <div type="checkcolumn" width="35"></div>
                <div field="custname" width="100"  headerAlign="center" allowSort="true" renderer="onActionRenderer">客户名称</div>                            
                <div field="custnumber" headerARlign="center" allowSort="true">客户编号</div>
                <div field="rawValue_custtype" width="150" headerAlign="center" allowSort="true" >客户类别</div>
                <div field="idcardno"  headerAlign="center" width="150" allowSort="true">身份证件号码</div> 
                <div field="rawValue_custkind"  headerAlign="center" allowSort="true" >客户内部行业</div>
                <div field="rawValue_draft"  headerAlign="center" allowSort="true">是否草稿</div>
                <div field="invalid"  headerAlign="center" allowSort="true">是否废弃</div>                                   
                <div field="sex" width="30" headerAlign="center" align="center" allowSort="true">性别</div>
                <div field="brithdate"  headerAlign="center" align="center" allowSort="true">出生日期</div>
                <div field="mobilenumber"  headerAlign="center" allowSort="true" >手机</div>
                <div field="phone"  headerAlign="center" allowSort="true" >电话</div>
                <div field="creatorname"  headerAlign="center" allowSort="true" >登记人</div>
                <div field="deptmentname"  headerAlign="center" allowSort="true" >登记部门</div>
                <div field="createdate"  headerAlign="center" allowSort="true" renderer="miniextonDateRenderer">登记时间</div>
            </div>
        </div> 
    </div>
    <script type="text/javascript">
	    mini.parse();
	    var grid = mini.get("datagrid1");
	    var form = new mini.Form("form2");
	    miniui_ext.addOnEnter("form2");
	    var custData = form.getData(true,true);
        grid.load(custData);
	    grid.frozenColumns(0, 2);
	    
	    //表格加载之前发生，未向后台发送参数
	    function onBeforeCustGridLoad(e){
	    	e.data.TableRemoteSortField = e.data.sortField;
	    	e.data.TableRemoteSortDir = e.data.sortOrder;
	    }
	    
	    function search(e) {
	        var data = form.getData(true,true);
		    grid.load(data);
	    }
	    function clear(e) {
        	form.clear();
        	mini.getbyName("invalid").setValue("否");
        	mini.getbyName("invalid").setText("否");
        	custData = form.getData(true,true);
 		    grid.load(custData);
        }
      	//点击客户打开详情
        function opencustdetail(id){
      		var params = "id="+id;
      		var url = getRootPath()+"/leasing/cust_info/cust_person/cust_person_detail.bi?"+params;
      		var sheight = window.screen.availHeight - 30;
    		var swidth = window.screen.availWidth - 10;
    		var winoption = "left=0px,top=0px,height=" + sheight + "px,width=" + swidth + "px,toolbar=yes,menubar=yes,location=yes,status=yes,scrollbars=yes,resizable=yes";
      		window.open(url, '_blank', winoption);
        }
      	//格式化客户名称这一列
        function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;
            var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id + '\')">' + e.value + '</a>';                     
            return s;
        }
        var button = new miniuiExtButtons({
    		url:'/leasing/cust_info/cust_person/input_cust_person_list.bi',
    		title : '自然人客户信息', 
    		width :  900, 
    		height : globalClientHeight - 50,  
    	    datagridId : "datagrid1",
    	    renderTo : "miniuiextbuttons",
    	    constantFlagTable : "CustEwlp",
    	    tools :['新增', '修改', {
    	    	html : '废弃',//自定义按钮的名字
				plain : true,//按钮是否有立体感
				iconCls : 'icon-remove',//按钮的图标
				handler : function(miniTable, buttonText) {//按钮响应函数
					var row = miniTable.getSelected();
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
				        	        	var obj=mini.decode(text);
				        	        	if("repeat" == obj.message){
				        	        		mini.alert("该客户已有项目立项记录，不能修改为草稿，并且不能废弃！");
				        	        	}
				        	        	str = text;
				        	        }
				        	    });
				        		if("" == str){
					        		grid.loading("操作中，请稍后......");
						            $.ajax({
						                url: "<%=request.getContextPath() %>/acl/removeCustEwlp.acl?id="+row.id,
						                data: {},
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
    	    }]
    	});
    </script>
</body>
</html>