<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<input class="mini-textbox" type="text" name="workid" id="workid" value="${requestScope['registerid'] }" style="display:none"/>

       <div id="datagrid3" class="mini-datagrid" style="width:100%;height:200px;" idField="id"
            url="${urlPrefix}/eleasing/jsp/cust_info/cust_company/last_work_week_list.xml" 
            dataField="datas"  allowCellWrap="true">
 			<div property="columns">
 			<div type="indexcolumn"></div>
            <div field="weekone" width="120" headerAlign="center">周一(${requestScope['weekone']})
            </div>  
            <div field="weektwo" width="120" headerAlign="center">周二(${requestScope['weektwo']})
            </div>  
            <div field="weekthree" width="120" headerAlign="center">周三(${requestScope['weekthree']})
            </div>  
            <div field="weekfour" width="120" headerAlign="center">周四(${requestScope['weekfour']})
            </div>  
            <div field="weekfive" width="120" headerAlign="center">周五(${requestScope['weekfive']})
            </div> 
            <div field="weeksix" width="120" headerAlign="center">周六(${requestScope['weeksix']})
            </div>
            <div field="weekseven" width="120" headerAlign="center">周日(${requestScope['weekseven']})
            </div>  
            <div field="twid" headerAlign="center" visible="false"></div>
         </div>
    </div>
<script type="text/javascript">
function date(date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? '0' + m : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
}
mini.parse();
var registerid=mini.getbyName("registerid").getValue();
var time="${requestScope['startdate']}";
var day=new Date(time);
var lastday=new Date(day.getTime() - 24*1*60*60*1000); 
var last=date(lastday);
var grid3 = mini.get("datagrid3");
grid3.load({time:last,registerid:registerid});



 
</script>