<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>租金催收</title>
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
	    <fieldset id="fd2">
	        <legend><label><input type="checkbox" checked id="checkbox1" onclick="toggleFieldSet(this, 'fd2')" hideFocus/>查询条件</label></legend>
	        <div class="fieldset-body" id="form1">
	            <table class="miniext-form-table">
	                <tr>
	                    <td style="width:100px;">客户名称：</td>
	                    <td >
	                        <input name="" class="mini-textbox" onEnter="search"/>
	                    </td>
	                    <td style="width:100px;">逾期租金：</td>
	                    <td >
	                        <input name="" class="mini-textbox" onEnter="search"/>到
	                        <input name="" class="mini-textbox" onEnter="search"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="width:100px;">起租日：</td>
	                    <td >
	                        <input name="" class="mini-datepicker"/>到
	                        <input name="" class="mini-datepicker"/>
	                    </td>
	                    <td style="width:100px;">承诺还款日：</td>
	                    <td >
	                        <input name="" class="mini-datepicker"/>到
	                        <input name="" class="mini-datepicker"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="width:100px;">逾期天数：</td>
	                    <td >
	                        <input id=""  class="mini-spinner"/>到
	                        <input name="" class="mini-spinner"/>
	                    </td>
	                    <td style="width:100px;">台数：</td>
	                    <td >
	                        <input id=""  class="mini-spinner"/>到
	                        <input name="" class="mini-spinner"/>
	                    </td>
	                </tr>
	                <tr>
	                    <td style="width:100px;">风险级别：</td>
	                    <td >
	                        <input class="mini-combobox" style="width:150px;"/>    
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
    <!-- 列表部分 -->
    <!--撑满页面-->
    <div class="mini-fit" id="_fit">
        
        <div id="datagrid1" class="mini-datagrid" style="width:100%;height:100%;" 
            url="<%=prefix %>/eleasing/jsp/ebackmgr/rent_collection/rent_collection_info.xml"  idfield=""
            sizeList="[5,10,20,50]" pageSize="10" showColumnsMenu="true"
            datafield="datas" contextMenu="#gridMenu"
        >
            <div property="columns">
                <div type="checkcolumn"></div>
                <div field="id" width=""  headerAlign="center" renderer="onActionRenderer">客户名称</div>                            
                <div field="" headerAlign="center">合同号</div>
                <div field="" width="" headerAlign="center" >合同状态</div>
                <div field=""  headerAlign="center" >催款员</div>                                    
                <div field="" width="" headerAlign="center" align="center">事业部</div>
                <div field=""  headerAlign="center" align="center">省份</div>
                <div field=""  headerAlign="center" >身份证/组织机构代码</div>
                <div field=""  headerAlign="center" align="center">逾期期数</div>
                <div field=""  headerAlign="center" align="center">逾期租金</div>
                <div field=""  headerAlign="center" align="center">逾期利息</div>
                <div field=""  headerAlign="center" align="center">逾期罚息</div>
                <div field=""  headerAlign="center" align="center">逾期天数</div>
                <div field=""  headerAlign="center" align="center">留购价款</div>
                <div field=""  headerAlign="center" align="center">总期数</div>
                <div field=""  headerAlign="center" align="center">联系方式</div>
                <div field=""  headerAlign="center" align="center">起租日</div>
                <div field=""  headerAlign="center" align="center">本金余额/剩余还款</div>
                <div field=""  headerAlign="center" align="center">下一次联系日</div>
                <div field=""  headerAlign="center" align="center">最近一次付款金额/时间</div>
                <div field=""  headerAlign="center" align="center">催收日</div>
                <div field=""  headerAlign="center" align="center">承诺还款日</div>
                <div field=""  headerAlign="center" align="center">承诺还款金额</div>
                <div field=""  headerAlign="center" align="center">担保人</div>
                <div field=""  headerAlign="center" align="center">担保人联系方式</div>
                <div field=""  headerAlign="center" align="center">承租人邮寄地址</div>
                <div field=""  headerAlign="center" align="center">逾期天数</div>
                <div field=""  headerAlign="center" align="center">台数</div>
                <div field=""  headerAlign="center" align="center">风险级别</div>
            </div>
        </div> 
        <ul id="gridMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
	        <li name="add" iconCls="icon-add" onclick="add">增加</li>
		    <li name="edit" iconCls="icon-edit" onclick="upd">修改</li>
		    <li name="remove" iconCls="icon-remove" onclick="remove">删除</li>        
	    </ul>
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
        //grid.load();//加载数据
        $(function(){
        	var data=[{id:"小李飞刀",text:""},{id:"西门吹冰",text:""}];
        	grid.setData(mini.decode(data));
        });
      //格式化客户名称这一列
        function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;
            var s = '<a class="Edit_Button" href="javascript:opencustdetail(\'' + e.record.id + '\',\'' + e.value + '\')">' + e.value + '</a>';
            return s;
        }
        //点击客户打开详情
        function opencustdetail(id,name){
      		/*
      			title:'标题'
      			showModal:'是否遮罩'
      			showMaxButton:'是否显示最大化按钮'
      			onload:页面打开时加载
      			ondestroy:页面关闭时加载
      		*/
        	var win = mini.open({
                url: getRootPath()+"/leasing/ebackmgr/rent_collection/rent_collection_info_details.bi",
                title: "编辑", width: 800, height: 500,
                showModal: false,
                showMaxButton: true,
                onload: function () {
                    
                },
                ondestroy: function (action) {
                }
            });
        	win.max();
        }
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
                url: getRootPath()+"/leasing/sysmgr/sysdatamgr/add_ownaccountinfo.bi?opertype=add",
                title: "增加", width: 800, height: 300,
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
                    url: getRootPath()+"/leasing/sysmgr/sysdatamgr/add_ownaccountinfo.bi?opertype=upd",
                    title: "编辑", width: 800, height: 300,
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
     	//右键菜单
        function onBeforeOpen(e) {
            var menu = e.sender;
                    
            var row = grid.getSelected();
            var rowIndex = grid.indexOf(row);            
            if (!row) {
                e.cancel = true;
                //阻止浏览器默认右键菜单
                e.htmlEvent.preventDefault();
                return;
            }
            ////////////////////////////////
//             var editItem = mini.getbyName("edit", menu);
//             var removeItem = mini.getbyName("remove", menu);
//             editItem.show();
//             removeItem.enable();

//             if (rowIndex == 1) {
//                 editItem.hide();
//             }
//             if (rowIndex == 1) {
//                 removeItem.disable();
//             }

        }
     	//删除
		function remove(e) {
		    var row = grid.getSelected();
		    if (null != row) {
		    	mini.confirm("确定删除选中记录？","提示：",function(data){
		        	if("ok" == data){
		        		grid.loading("操作中，请稍后......");
			            $.ajax({
			                url: "<%=request.getContextPath() %>/acl/removeOwnAccount.acl?id="+row.id,
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