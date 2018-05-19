<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% String workid=request.getParameter("workid"); %>
<input class="mini-textbox" type="text" name="workid" id="workid" value="<%=workid %>" style="display:none"/>
<div id="workweekone" style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="jiaRow()" plain="true" tooltip="增加...">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="shanRow()" plain="true">删除</a>          
                    </td>
                </tr>
            </table>           
        </div>
    </div>
       <div id="datagrid2" class="mini-datagrid" style="width:100%;height:200px;" idField="id"
            url="${urlPrefix}/eleasing/jsp/cust_info/cust_company/next_work_week_list.xml"  
            sizeList="[5,10,20,50]"  allowAlternating="true" pageSize="5"
            dataField="datas" showColumnsMenu="true"   allowCellEdit="${opertype == 'view' ? false : true}" allowCellSelect="true"
        	editNextOnEnterKey="true"  editNextRowCell="true" allowCellWrap="true">
 			<div property="columns">
        	<div type="indexcolumn"></div>
            <div type="checkcolumn" width="15"></div> 
            <div field="nextweekone" width="120" headerAlign="center">周一(${requestScope['nextweekone']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>  
            <div field="nextweektwo" width="120" headerAlign="center">周二(${requestScope['nextweektwo']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>  
            <div field="nextweekthree" width="120" headerAlign="center">周三(${requestScope['nextweekthree']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>  
            <div field="nextweekfour" width="120" headerAlign="center">周四(${requestScope['nextweekfour']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>  
            <div field="tnid" headerAlign="center" visible="false"></div>
            <div field="nextweekfive" width="120" headerAlign="center">周五(${requestScope['nextweekfive']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>
             <div field="nextweeksix" width="120" headerAlign="center">周六(${requestScope['nextweeksix']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>
             <div field="nextweekseven" width="120" headerAlign="center">周日(${requestScope['nextweekseven']})
                <input property="editor" class="mini-textarea" style="width:200px;" minWidth="200" minHeight="75"/>
            </div>   
         </div>
    </div>
<script type="text/javascript">
var optype=mini.getbyName("opertype").getValue();
if(optype=="view"){
	 $("#workweekone").hide()
}

mini.parse();
var grid2 = mini.get("datagrid2");
var workid=mini.getbyName("workid").getValue();
grid2.load({workid:workid});

function jiaRow(index) {          
    var newRow = { name: "New Row",tnid:" ",nextweekone:" ",nextweektwo:" ",nextweekthree:" ",nextweekfour:" ",nextweekfive:" ",nextweeksix:" ",nextweekseven:" " };
    grid2.addRow(newRow, index);

    grid2.beginEditCell(newRow, "nextweekone");
}
function shanRow() {
	var params={};
    var rows = grid2.getSelecteds();
    if (rows.length > 0) {
       var id=rows[0].tnid;
       if(id==" "){
    	   grid2.removeRows(rows, true);
       }else{
	       params["tnid"]=id;
	       $.ajax({
				url:getRootPath()+"/acl/delNextWorkWeekReportDetaillication.acl",
				data:params,
				type:'post',
				async:false,
				success:function(e){
					var result=mini.decode(e);
					if(result.flag=='true'){
						 mini.alert(result.msg);
						 grid2.load({workid:workid});
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

grid2.on("celleditenter", function (e) {
    var index = grid2.indexOf(e.record);
    if (index == grid2.getData().length - 1) {
        var row = {};
        grid2.addRow(row);
    }
}); 

grid2.on("beforeload", function (e) {
    if (grid2.getChanges().length > 0) {
        if (confirm("有增删改的数据未保存，是否取消本次操作？")) {
            e.cancel = true;
        }
    }
}); 
</script>