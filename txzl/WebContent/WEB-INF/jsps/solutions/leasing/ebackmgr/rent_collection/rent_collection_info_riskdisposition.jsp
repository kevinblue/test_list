<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="mini-panel" title="风险处置" showCollapseButton="true" style="width: 100%;">
    <!-- 按钮操作 -->
    <div class="mini-toolbar miniext-toolbar-top">
        <table class="miniext-form-fit">
            <tr>
	            <td>
	                <a class="mini-button" iconCls="icon-addfolder" plain="true" onclick="addDispositionRow">增加</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-remove" plain="true" onclick="removeDispositionRow">删除</a>
	                <span class="separator"></span>
	                <a class="mini-button" iconCls="icon-save" plain="true" onclick="saveDispositionRow">保存</a>
	            </td>
            </tr>
        </table>
    </div>
    <!--grid展示--撑满页面-->
    <div id="riskdispositiongrid" class="mini-datagrid" style="width:100%;height:300px;" dataField="datas" contextMenu="#gridMenu"
        url=""  
        sizeList="[5,10,20,50]" pageSize="10"  idField="id"
     allowResize="true" showColumnsMenu="true"
     allowCellEdit="true" allowCellSelect="true" multiSelect="true"
     allowCellValid="true"
    >
        <div property="columns">
            <div type="checkcolumn"></div>
            <div type="indexcolumn" headerAlign="center">序号</div>
            <div field=""  headerAlign="center" >合同编号
            	<input property="editor" class="mini-textbox"  />
            </div>                            
            <div field=""  align="center" headerAlign="center">
            	被告人
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""  headerAlign="center">
            	处理方式
            	<input property="editor" class="mini-textbox"  />
            </div>                                    
            <div field=""  headerAlign="center">处理时间
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""  headerAlign="center">登记人
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field="" headerAlign="center">登记时间
            	<input property="editor" class="mini-textbox"  />
            </div>
            <div field=""   headerAlign="center" >修改人
            	<input property="editor" class="mini-textbox" />
            </div>
            <div field=""   headerAlign="center" >修改时间
            	<input property="editor" class="mini-textarea"  />
            </div>
        </div>
    </div> 
</div>
<script>
	function addDispositionRow() {
		var riskdispositiongrid = $mini("riskdispositiongrid");
	    var newRow = {};
	    riskdispositiongrid.addRow(newRow, 0);
	    riskdispositiongrid.beginEditCell(newRow);
	}
	function removeDispositionRow() {
		var riskdispositiongrid = $mini("riskdispositiongrid");
		var row = riskdispositiongrid.getSelected();
	    //alert(row.id);
	   			 
	    if (null != row) {
	        if (confirm("确定删除选中记录？")) {
	        	riskdispositiongrid.loading("操作中，请稍后......");
	            $.ajax({
	                url: "<%=request.getContextPath() %>/acl/removeFundOverduerent.acl?id="+row.id,
	                data: {}  ,
	                success: function (text) {
	                	riskdispositiongrid.reload();
	                },
	                error: function () {
	                }
	            });
	        }
	    } else {
	        alert("请选中一条记录");
	    }
	}
	function saveDispositionRow() {
		var riskdispositiongrid = $mini("riskdispositiongrid");
	    var add_data = riskdispositiongrid.getData();
        var add_json = mini.encode(add_data);
        
       
        //alert(add_json);
        riskdispositiongrid.loading("保存中，请稍后......");
        var url="<%=request.getContextPath() %>/acl/addFundOverduerent.acl";
        $.ajax({
            url: url,
            data: { add_json: add_json},
            type: "post",
            success: function (text) {
            	riskdispositiongrid.reload();
            },
            error: function () {
                
            }
        });
	    
	}
	function $mini(id){
    	return mini.get(id);
    } 
</script>