<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mini-panel" title="走访记录" showCollapseButton="true" style="width: 100%;">
    <!-- 按钮操作 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="addVisirecordRow">增加</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeVisirecordRow">删除</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-save" plain="true" onclick="saveVisirecordRow">保存</a>
	            </td>
            </tr>
        </table>
    </div>
    <!--grid展示--撑满页面-->
    <div id="visirecordgrid" class="mini-datagrid" style="width:100%;height:300px;" dataField="datas" contextMenu="#gridMenu"
        url=""  
        sizeList="[5,10,20,50]" pageSize="10"  idField="id"
     allowResize="true" showColumnsMenu="true"
     allowCellEdit="true" allowCellSelect="true" multiSelect="true"
     allowCellValid="true"
    >
        <div property="columns">
            <div type="checkcolumn"></div>
            <div type="indexcolumn" headerAlign="center">序号</div>
            <div field="custname" width="200" headerAlign="center">客户名称</div>
            <div field="contactdate" align="center" headerAlign="center">交往时间</div>
            <div field="contactperson" align="center" headerAlign="center">联系人</div>
            <div field="contactphone" align="center"  headerAlign="center">联系人电话</div>
            <div field="contactadd" width="200" headerAlign="center">联系地点</div>
            <div field="raw_contacttype" align="center"  headerAlign="center">记录类型</div>
            <div field="ccmemo" width="260"  headerAlign="center">记录内容</div>
            <div field="creatorname"  headerAlign="center" align="center">登记人</div>
            <div field="createdate"  headerAlign="center" dateFormat="yyyy-MM-dd" align="center">登记时间</div>
            <div field="modificatorname"  headerAlign="center" align="center">修改人</div>
            <div field="modifydate"  headerAlign="center" dateFormat="yyyy-MM-dd" align="center">修改时间</div>
        </div>
    </div> 
</div>
<script>
	function addVisirecordRow() {
		var visirecordgrid = $mini("visirecordgrid");
		mini.open({
    		ShowModal:false,
    		showCloseButton:true,
    		showMaxButton:true,
            url: getRootPath()+"/leasing/cust_info/cust_contact/add_cust_contact_list.bi?opertype=add",
            title: "增加", width: 800, height: 300,
            onload: function () {
            	
            },
            ondestroy: function (action) {
            	visirecordgrid.reload();
                
            }
        });
	}
	function removeVisirecordRow() {
		var visirecordgrid = $mini("visirecordgrid");
		var row = visirecordgrid.getSelected();
	    //alert(row.id);
	   			 
	    if (null != row) {
	        if (confirm("确定删除选中记录？")) {
	        	visirecordgrid.loading("操作中，请稍后......");
	            $.ajax({
	                url: "<%=request.getContextPath() %>/acl/removeFundOverduerent.acl?id="+row.id,
	                data: {}  ,
	                success: function (text) {
	                	visirecordgrid.reload();
	                },
	                error: function () {
	                }
	            });
	        }
	    } else {
	        alert("请选中一条记录");
	    }
	}
	function saveVisirecordRow() {
		var visirecordgrid = $mini("visirecordgrid");
	    var add_data = visirecordgrid.getData();
        var add_json = mini.encode(add_data);
        
       
        //alert(add_json);
        visirecordgrid.loading("保存中，请稍后......");
        var url="<%=request.getContextPath() %>/acl/addFundOverduerent.acl";
        $.ajax({
            url: url,
            data: { add_json: add_json},
            type: "post",
            success: function (text) {
            	visirecordgrid.reload();
            },
            error: function () {
                
            }
        });
	    
	}
	function $mini(id){
    	return mini.get(id);
    } 
</script>