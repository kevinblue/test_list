<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>
<%@ taglib uri="/minidict" prefix="mini"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var custid ="<mini:param  name='id'/>";
jQuery(function(){
	showTable();
});
function showTable(){
	var param = {};
	param.custid = custid;
	param['xmlFileName'] = "/eleasing/jsp/cust_info/cust_financial/cust_financial_table_show.xml";
	ajaxRequest({
		params:param,
		url:'${pageContext.request.contextPath}/table/getTableData.action',
		method : 'POST',
		async : false,
		success:function(response){
			var jsondata = eval('(' + response.responseText + ')').datas;
			var trs = "";
			var flag = "";
			for(var i = 0;i<jsondata.length;i++){
				//表格标题行
				var tablename = jsondata[i].title_name;
				var reprotunit = i > 0? "" :"&nbsp;&nbsp;&nbsp;&nbsp;单位：RMB元";
				if(flag != tablename){
				trs += "<tr><th colspan=3>"+tablename +reprotunit+"</th></tr>";
					flag = tablename;
				}
				//内容行
				var subjectname = jsondata[i].subject_name;
				var values = jsondata[i].obj.split(",");
				var othervalues = jsondata[i].otherobj.split(",");
				trs += "<tr><th>"+subjectname+"</th>";
				for(var j = 0;j < values.length;j++){
					if(j>3) break;
					var percent = isNaN(parseFloat(othervalues[j])) == true ?othervalues[j]: parseFloat(othervalues[j]).toFixed(2);
					var number = values[j].indexOf('.')>0 ? values[j].substr(0,values[j].indexOf('.')+3): values[j];
					trs += "<td>" +number + "</td><td>"+percent+"</td>";
				}
				trs += "</tr>";
			}
			$("#table-finance-reprot").html(trs);
			$("#table-finance-reprot td").css({
				'width':'200px',
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'center',
				'word-break':'break-all'
				
			});

			$("#table-finance-reprot th").css({
				'width':'200px',
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'left'				
			});
			$("#table-finance-reprot tr:eq(1)").css({'background': '#468CC8'});
		},
		failure:function(response){
			mini.alert("加载财务报表出错!");
		}
	});
}

function importFinanceReportOMY(){
	var uputil=new uploadUtil({
		url:'/leasing/finance/importFinance.action',
    	title:'财务报表',
    	jscallback:'importFinanceCall',
    	parames:{custid:custid}
	});
    uputil.createFileAndShow();
}

function importFinanceCall(message){
    mini.alert(message);
	mini.unmask(document.body);
	mini.get("id_uploadfile").hide();
	window.location.reload(); 
}

function edit(){
	mini.open({
        url: getRootPath()+"/leasing/editFinancialData.acl?custid=" + custid,
        title: "编辑", width: 600, height: 500,
        onload: function (){},
        ondestroy: function (action) {
            if(action=="close"){
            	showTable();
			}
       	}
	})
}
function tempdownload(){
	var templateno='F-201412013';
    downLoadFileTemplate(templateno);
}
function remove(){
	mini.confirm("确认要清空吗？", "清空", function (action) {
		 if(action=='ok'){
			var param = {"custid":custid};
			ajaxRequest({
				params:param,
				url:getRootPath()+"/leasing/removeFinancialData.acl",
				method : 'POST',
				async : false,
				success:function(response){
					mini.alert("删除数据成功!");
					showTable();
				}
			})
		}
    })
}
</script>
<div class="mini-toolbar" id="id_finance_tool">
		<a class="mini-button" iconCls="icon-upload" onclick="importFinanceReportOMY()">导入财务报表</a><span class="separator"></span>
		<a text-aligh="right"class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a><span class="separator"></span>
		<a text-aligh="right"class="mini-button" iconCls="icon-remove" onclick="remove()">清空</a><span class="separator"></span>
		<a text-aligh="right"class="mini-button" iconCls="icon-download" onclick="tempdownload()">模板下载</a>
</div>
<div class="mini-table"style="width: 100%; height: 100%; padding: 5px auto auto 5px;">
	<table id="table-finance-reprot" style="border-collapse: collapse; width: 100%" cellspacing="0">
</table>
</div>