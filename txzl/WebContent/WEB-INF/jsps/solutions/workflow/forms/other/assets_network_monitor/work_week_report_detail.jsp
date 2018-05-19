<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<input class="mini-textbox" type="text" name="workid" id="workid" value="${requestScope['registerid'] }" style="display:none"/>

<div id="workweek"  style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true" tooltip="增加...">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>          
                    </td>
                </tr>
            </table>           
        </div>
    </div>
       <div id="datagrid1" class="mini-datagrid" style="width:100%;height:200px;" idField="id"
            url="${urlPrefix}/eleasing/jsp/cust_info/cust_company/work_week_list.xml"  
            sizeList="[5,10,20,50]"  allowAlternating="true" pageSize="5"
            dataField="datas" showColumnsMenu="true"  allowResize="true" allowCellEdit="${opertype == 'view' ? false : true}" allowCellSelect="true"
        	editNextOnEnterKey="true"  editNextRowCell="true" allowCellWrap="true">
 			<div property="columns">
 			<div type="indexcolumn"></div>
            <div type="checkcolumn" width="15"></div>
            <div field="weekone" id="weekone" width="120" headerAlign="center">周一(${requestScope['weekone']})
                <input property="editor" class="mini-textarea" />
            </div>
            <div field="weektwo" width="120" headerAlign="center">周二(${requestScope['weektwo']})
                <input property="editor" class="mini-textarea" />
            </div>  
            <div field="weekthree" width="120" headerAlign="center">周三(${requestScope['weekthree']})
                <input property="editor" class="mini-textarea" />
            </div>  
            <div field="weekfour" width="120" headerAlign="center">周四(${requestScope['weekfour']})
                <input property="editor" class="mini-textarea" />
            </div>  
            <div field="weekfive" width="120" headerAlign="center">周五(${requestScope['weekfive']})
                <input property="editor" class="mini-textarea" />
            </div>
            <div field="weeksix" width="120" headerAlign="center">周六(${requestScope['weeksix']})
                <input property="editor" class="mini-textarea" />
            </div> 
            <div field="weekseven" width="120" headerAlign="center">周日(${requestScope['weekseven']})
                <input property="editor" class="mini-textarea" />
            </div>    
            <div field="twid" headerAlign="center" visible="false"></div>
         </div>
    </div>
<script type="text/javascript">

var optype=mini.getbyName("opertype").getValue();
if(optype=="view"){
	 $("#workweek").hide()
	
}
mini.parse();
var grid = mini.get("datagrid1");
var workid=mini.getbyName("workid").getValue();
grid.load({workid:workid});

function addRow(index) {          
    var newRow = { name: "New Row",twid:" ",weekone:" ",weektwo:" ",weekthree:" ",weekfour:" ",weekfive:" ",weeksix:" ",weekseven:" " };
    grid.addRow(newRow, index);
    grid.beginEditCell(newRow, "weekone");
}
function removeRow() {
	var params={};
    var rows = grid.getSelecteds();
    if (rows.length > 0) {
       var id=rows[0].twid;
       if(id==" "){
    	   grid.removeRows(rows, true);
       }else{
	       params["twid"]=id;
	       $.ajax({
				url:getRootPath()+"/acl/delWorkWeekReportDetaillication.acl",
				data:params,
				type:'post',
				async:false,
				success:function(e){
					var result=mini.decode(e);
					if(result.flag=='true'){
						 mini.alert(result.msg);
						 grid.load({workid:workid});
					}else{
						mini.alert('发生异常：'+result.msg);
					}
				}
			});    	   
       }
    }else{
    	mini.alert("请选择要删除的信息！")
    }
}

grid.on("celleditenter", function (e) {
    var index = grid.indexOf(e.record);
    if (index == grid.getData().length - 1) {
        var row = {};
        grid.addRow(row);
    }
}); 
 
</script>