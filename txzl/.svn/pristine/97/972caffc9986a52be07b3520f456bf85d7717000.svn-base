<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>凭证辅助账类型分类</title>
<%
	//前缀
	String prefix = "${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=";
%>
<%@include file="/base/mini.jsp"%>
<!-- miniui扩展样式 -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>
<!-- miniui扩展JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
</head>
<body>
<!-- 高级查询部分 -->
    <div>
	    <fieldset id="fd2" class="hideFieldset">
	        <legend><label><input type="checkbox"  id="checkbox1" onclick="toggleFieldSet(this, 'fd2')" hideFocus/>查询条件</label></legend>
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table">
	                <tr>
	                    <td style="width:100px;">客户名称：</td>
	                    <td >
	                        <input name="custname" class="mini-textbox miniext-form-fit" />
	                    </td>
	                  
	                    <td style="width:100px;">身份证号码：</td>
	                    <td >
	                        <input name="orgcodecardno" class="mini-textbox miniext-form-fit"/>
	                    </td>
	                    
	                    <td class="querybtn">
	                    	<input class="mini-button" text="&nbsp;&nbsp;&nbsp;" tooltip="查询"
	                    	iconCls="icon-query" onclick="search"
	                        />
	                    </td>
	                </tr>      
	            </table>
	        </div>
	    </fieldset>
    </div>
<!-- 按钮部分 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="add">增加</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-edit" plain="true" onclick="upd">修改</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="remove">删除</a>
	            </td>
            </tr>
        </table>
    </div>
    <!-- 列表部分 -->
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="<%=prefix %>/voucher/voucherass_connection.xml"  idField="id"
            sizeList="[5,10,20,50]" pageSize="10" showColumnsMenu="true"
            dataField="datas" contextMenu="#gridMenu"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="raw_subjectsowner"  headerAlign="center" >所属区域</div>
                <div field="raw_configid"  headerAlign="center" >科目名称</div>                                    
                <div field="subjectscode"  headerAlign="center" align="center">科目编码</div>
                <div field="raw_asstacttype"  headerAlign="center" align="center">辅助账类型</div>
               <div field="raw_status"  headerAlign="center" >是否有效</div>
            </div>
        </div> 
       
    </div>
    <script type="text/javascript">
        function toggleFieldSet(ck, id) {
            var dom = document.getElementById(id);
            dom.className = !ck.checked ? "hideFieldset" : "";
            var _fit = mini.get("_fit");
            _fit.setHeight(window.document.body.clientHeight);
        }
    </script>
    <script type="text/javascript">
    	//初始化
        mini.parse();
        miniui_ext.addOnEnter("form1");
        var grid = mini.get("datagrid1");
        var form = new mini.Form("form1");
        //grid.load({"xmlFileName":"/eleasing/jsp/cust_info/cust_person/cust_person_list.xml"});
        grid.load();//加载数据
        //combobox懒加载
        function onbeforeshowpopup(e) {
        	miniui_ext.onbeforeshowpopup(e);
        }
        //查询
        function search(e) {
            var data = form.getData(true,true);
 		    grid.load(data);
        }
      	//格式化日期
        function onDateRenderer(e) {
            return miniui_ext.onDateRenderer(e);
        }
      	
      	//添加
        function add(e){
        	/*
	  			title:'标题'
	  			showModal:'是否遮罩'
	  			showMaxButton:'是否显示最大化按钮'
	  			onload:页面打开时加载
	  			ondestroy:页面关闭时加载
	  		*/
        	mini.open({
        		ShowModal:false,
        		showCloseButton:true,
        		showMaxButton:true,
                url: getRootPath()+"/voucher/voucherass_connection/add_voucherass_connection.bi?opertype=add",
                title: "增加", width: 600, height: 400,
                onload: function () {
                	
                },
                ondestroy: function (action) {
                	if("savesuccess" == action){
                		grid.reload();
                	}
                }
            });
        }
     	//修改
        function upd(e){
     		//获取选中行、并判断
        	var row = grid.getSelected();
            if (row) {
            	/*
		  			title:'标题'
		  			showModal:'是否遮罩'
		  			showMaxButton:'是否显示最大化按钮'
		  			onload:页面打开时加载
		  			ondestroy:页面关闭时加载
		  		*/
                mini.open({
                    url: getRootPath()+"/voucher/voucherass_connection/add_voucherass_connection.bi?opertype=upd",
                    title: "编辑", width: 600, height: 400,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        var data = { action: "edit", id: row.id };
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                    	if("savesuccess" == action){
                    		grid.reload();
                    	}
                    }
                });
                
            } else {
                alert("请选中一条记录");
            }
        }
     	
     	//删除
		function remove(e) {
		    var row = grid.getSelected();
		    if (null != row) {
		    	mini.confirm("确定删除选中记录？","提示：",function(data){
		        	if("ok" == data){
		        		grid.loading("操作中，请稍后......");
			            $.ajax({
			                url: "<%=request.getContextPath() %>/acl/removeVoucherConnection.acl?id="+row.id,
			                data: {}  ,
			                success: function (text) {
			                    grid.reload();
			                },
			                error: function () {
			                }
			            });
		        	}
		        });
		    } else {
		        mini.alert("请选中一条记录");
		    }
		}
        

    </script>
</body>
</html>