<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mini-panel" title="租金催收" showCollapseButton="true" style="width: 100%;">
    <!-- 按钮操作 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="addRentRow">增加</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeRentRow">删除</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-save" plain="true" onclick="saveRentRow">保存</a>
	            </td>
            </tr>
        </table>
    </div>
    <!--grid展示--撑满页面-->
    <div id="rentgrid" class="mini-datagrid" style="width:100%;height:300px;" dataField="datas" contextMenu="#gridMenu"
        url=""  
        sizeList="[5,10,20,50]" pageSize="10"  idField="id"
     allowResize="true" showColumnsMenu="true"
     allowCellEdit="true" allowCellSelect="true" multiSelect="true"
     allowCellValid="true"
    >
        <div property="columns">
            <div type="checkcolumn"></div>
            <div type="indexcolumn" headerAlign="center">序号</div>
            <div field=""  headerAlign="center" >客户名称
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""  headerAlign="center" >合同名
            	<input property="editor" class="mini-textbox"  />
            </div>                            
            <div field=""  align="center" headerAlign="center">
            	客户名称
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""  headerAlign="center">
            	催款员
            	<input property="editor" class="mini-textbox"  />
            </div>                                    
            <div field=""  headerAlign="center">联系方式
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""  headerAlign="center">联系日期
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field="" headerAlign="center">承诺还款日
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""   headerAlign="center" >承诺还款金额
            	<input property="editor" class="mini-textbox" />
            </div>
            <div field=""   headerAlign="center" >下次联系日期
            	<input property="editor" class="mini-textarea"  />
            </div>
            <div field=""   headerAlign="center" >联系情况
            	<input property="editor" class="mini-textarea"  />
            </div>
            <div field=""   headerAlign="center" >登记时间
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""   headerAlign="center" >最后修改时间
            	<input property="editor" class="mini-textarea"  />
            </div>
        </div>
    </div> 
</div>
<script>
	function addRentRow() {
		var rentgrid = $mini("rentgrid");
	    var newRow = {};
	    rentgrid.addRow(newRow, 0);
	    rentgrid.beginEditCell(newRow);
	}
	function removeRentRow() {
		var rentgrid = $mini("rentgrid");
		var row = rentgrid.getSelected();
	    //alert(row.id);
	   			 
	    if (null != row) {
	        if (confirm("确定删除选中记录？")) {
	        	rentgrid.loading("操作中，请稍后......");
	            $.ajax({
	                url: "<%=request.getContextPath() %>/acl/removeDunningRecord.acl?id="+row.id,
	                data: {}  ,
	                success: function (text) {
	                	rentgrid.reload();
	                },
	                error: function () {
	                }
	            });
	        }
	    } else {
	        alert("请选中一条记录");
	    }
	}
	function saveRentRow() {
		var rentgrid = $mini("rentgrid");
	    var add_data = rentgrid.getData();
        var add_json = mini.encode(add_data);
        
       
        //alert(add_json);
        rentgrid.loading("保存中，请稍后......");
        var url="<%=request.getContextPath() %>/acl/addDunningRecord.acl";
        $.ajax({
            url: url,
            data: { add_json: add_json},
            type: "post",
            success: function (text) {
            	rentgrid.reload();
            },
            error: function () {
                
            }
        });
	    
	}
	function $mini(id){
    	return mini.get(id);
    }
	function $mini(id){
    	return mini.get(id);
    } 
</script>