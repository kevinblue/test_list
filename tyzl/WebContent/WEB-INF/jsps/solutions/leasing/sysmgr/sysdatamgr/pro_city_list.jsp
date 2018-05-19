<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>省市信息管理</title>
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
<table class="miniext-form-table" style="padding: 0px;">
   <tr>
       <td>
	       	<div class="mini-toolbar miniext-toolbar-top">
		        <table class="miniext-form-fit">
		            <tr>
			            <td>
			            	<span class="miniext-toolbar-title">国家</span>
			                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="add('countrygrid')">增加</a>
			                <span class="separator"></span>
			                <a class="mini-button" iconCls="icon-save" plain="true" onclick="save('countrygrid')">保存</a>
			            </td>
		            </tr>
		        </table>
		    </div>
		    <!--撑满页面-->
		    <div class="mini-fit">
		        <div id="countrygrid" class="mini-datagrid" style="width:100%;height:100%;" 
		            url="<%=prefix %>/eleasing/jsp/sysmgr/sysdatamgr/pro_city_list.xml"  idField="id"
		            sizeList="[5,10,20,50]" pageSize="10" onrowclick="countrygridrowclick"
		            dataField="datas" contextMenu="#countrygridmenu"
		            allowCellEdit="true" allowCellSelect="true" allowCellValid="true"
		        >
		            <div property="columns">
		                <div field="name" vtype="required;" width="150" headerAlign="center" align="center">名称
		                	<input property="editor" class="mini-textbox" style="width:100%;"/>
		                </div>                            
		                <div field="id" vtype="required" headerAlign="center">编号
		                	<input property="editor" class="mini-textbox" style="width:100%;" />
		                </div>
		            </div>
		        </div> 
		        <ul id="countrygridmenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
			        <li name="add" iconCls="icon-add" onclick="add('countrygrid')">增加</li>
				    <li name="save" iconCls="icon-save" onclick="save('countrygrid')">保存</li> 
			    </ul>
		    </div>
       </td>
       <td>
       		<div class="mini-toolbar miniext-toolbar-top">
		        <table class="miniext-form-fit">
		            <tr>
			            <td>
			            	<span class="miniext-toolbar-title">省份</span>
			                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="add('provincegrid')">增加</a>
			                <span class="separator"></span>
			                <a class="mini-button" iconCls="icon-save" plain="true" onclick="save('provincegrid')">保存</a>
			            </td>
		            </tr>
		        </table>
		    </div>
		    <!--撑满页面-->
		    <div class="mini-fit">
		        <div id="provincegrid" class="mini-datagrid" style="width:100%;height:100%;" 
		            url="<%=prefix %>/eleasing/jsp/sysmgr/sysdatamgr/get_procity_byid.xml"  idField="id"
		            sizeList="[5,10,20,50]" pageSize="10" onrowclick="provincerowclick"
		            dataField="datas" contextMenu="#provincegridmenu"
		            allowCellEdit="true" allowCellSelect="true" allowCellValid="true"
		        >
		            <div property="columns">
		                <div field="name" vtype="required" width="150"  headerAlign="center">名称
		                	<input property="editor" class="mini-textbox" style="width:100%;"/>
		                </div>                            
		                <div field="id" vtype="required" headerAlign="center">编号
		                	<input property="editor" class="mini-textbox" style="width:100%;"/>
		                </div>
		            </div>
		        </div> 
		        <ul id="provincegridmenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
			        <li name="add" iconCls="icon-add" onclick="add('provincegrid')">增加</li>
				    <li name="save" iconCls="icon-save" onclick="save('provincegrid')">保存</li>     
			    </ul>
		    </div>
       </td>
       <td>
       		<div class="mini-toolbar miniext-toolbar-top">
		        <table class="miniext-form-fit">
		            <tr>
			            <td>
			            	<span class="miniext-toolbar-title">城市</span>
			                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="add('citygrid')">增加</a>
			                <span class="separator"></span>
			                <a class="mini-button" iconCls="icon-save" plain="true" onclick="save('citygrid')">保存</a>
			            </td>
		            </tr>
		        </table>
		    </div>
		    <!--撑满页面-->
		    <div class="mini-fit">
		        <div id="citygrid" class="mini-datagrid" style="width:100%;height:100%;" 
		            url="<%=prefix %>/eleasing/jsp/sysmgr/sysdatamgr/get_procity_byid.xml"  idField="id"
		            sizeList="[5,10,20,50]" pageSize="10"
		            dataField="datas" contextMenu="#citygridmenu"
		            allowCellEdit="true" allowCellSelect="true" allowCellValid="true"
		        >
		            <div property="columns">
		                <div field="name" vtype="required" width="150"  headerAlign="center" renderer="onActionRenderer">名称
		                	<input property="editor" class="mini-textbox" style="width:100%;"/>
		                </div>                            
		                <div field="id" vtype="required" headerAlign="center">编号
		                	<input property="editor" class="mini-textbox" style="width:100%;"/>
		                </div>
		            </div>
		        </div> 
		        <ul id="citygridmenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">              
			        <li name="add" iconCls="icon-add" onclick="add('citygrid')">增加</li>
				    <li name="save" iconCls="icon-save" onclick="save('citygrid')">保存</li>    
			    </ul>
		    </div>
       </td>
    </tr>
</table>
	
    <script type="text/javascript">
        mini.parse();
        /*
        	国家 countrygrid
        	省份 provincegrid
        	城市 citygrid
        	国家选中行 countrycheckrow
        	城市选中行 provincecheckrow
        */
        var countrygrid = mini.get("countrygrid");
        var provincegrid = mini.get("provincegrid");
        var citygrid = mini.get("citygrid");
        var countrycheckrow;
        var provincecheckrow;
        init();//初始化加载国家列表
        function init(){
        	countrygrid.load({},function(data){
        		if(null == data.data || "" == data.data) return;
        		//选中第一行
        		countrygrid.select(countrygrid.getRow(0));
        		//加载省份
        		loadprovince();
        	});
        }
     	 //国家行点击事件触发
        function countrygridrowclick(e){
     		//判断是否填写编号与名称
        	if(null == e.row.id || null == e.row.name) return;
        	//刷新省份
        	loadprovince();
        }
      	//省份行点击事件触发
        function provincerowclick(e){
        	//判断是否填写编号与名称
        	if(null == e.row.id || null == e.row.name) return;
        	//刷新城市
        	loadcity();
        }
        //加载省份
        function loadprovince(){
        	//判断是否重复点击
        	if(null != countrycheckrow && countrycheckrow.name == countrygrid.getSelected().name) return;
        	//保存选中国家
         	countrycheckrow = countrygrid.getSelected() || countrygrid.getRow(0); 
    		provincegrid.load({pid:countrycheckrow.id},function(data){
    			if(null == data.data || "" == data.data) return;
    			//选中省份列表第一行
    			provincegrid.select(provincegrid.getRow(0));
    			//刷新城市
    			loadcity();
    		});
        }
      	//加载城市
        function loadcity(){
        	//判断是否重复点击
        	if(null != provincecheckrow && provincecheckrow.name == provincegrid.getSelected().name) return;
        	//保存选中省份
        	provincecheckrow = provincegrid.getSelected() || provincegrid.getRow(0); 
			citygrid.load({pid:provincecheckrow.id});
        }
      	/*
      		添加操作
      		param: gridid
      	*/
        function add(gridid) {
      		//根据gridid获取选中行
        	var row = $mini(gridid).getSelected();
      		//获取pid
            var newRow = {parentDistrict:returnpid(gridid)};
      		//多行编辑开始  -- 至第一行
            $mini(gridid).addRow(newRow, 0);
            $mini(gridid).validateRow(newRow);   //加入新行，马上验证新行
        }
        //返回pid
        function returnpid(gridid){
        	pid="";
        	if("provincegrid" == gridid){
        		pid = $miniselected("countrygrid").id;
        	}else if("citygrid" == gridid){
        		pid = $miniselected("provincegrid").id;
        	}
        	return pid;
        }
        //保存操作
        function save(gridid){
        	var grid = mini.get(gridid);
        	//验证
            if (miniui_ext.gridvalidation(gridid) == false) return;
        	//获取当前增删改集合
            var data = grid.getChanges(null);
        	//反序列化json
            var json = mini.encode(data);
        	$.ajax({
                url: "<%=request.getContextPath() %>/acl/addDistrict.acl",
                type: "post",
                data:  {data:json} ,
                success: function (text) {
//                 	if("countrygrid" == gridid){
//                 		init();
//                 	}else if("provincegrid" == gridid){
//                 		loadprovince();
//                 	}else{
//                 		loadcity();
//                 	}
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
        //删除
        function remove(gridid) {
        	var row = $mini(gridid).getSelected();
		    if (null != row) {
		        mini.confirm("确定删除选中记录？","提示：",function(data){
		        	if("ok" == data){
			        	$mini(gridid).loading("操作中，请稍后......");
			            $.ajax({
			                url: "<%=request.getContextPath() %>/acl/removeDistrict.acl?id="+row.id,
			                data: {}  ,
			                success: function (text) {
			                	$mini(gridid).reload();
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
        function $mini(id){
        	return mini.get(id);
        }
        function $miniselected(gridid){
        	return $mini(gridid).getSelected();
        }
        //右键菜单
        function onBeforeOpen(e) {
            var gridid = e.popupEl.id;
            var row = $mini(gridid).getSelected();
            var rowIndex = $mini(gridid).indexOf(row);            
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
    </script>
</body>
</html>