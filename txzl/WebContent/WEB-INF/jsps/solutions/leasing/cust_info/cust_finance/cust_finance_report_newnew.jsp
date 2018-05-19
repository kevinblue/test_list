<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>
<%String id=request.getParameter("id");%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
var custid ="<%=id %>";
jQuery(function(){
	var showTools = true;
    if('${param.isView}' == 'true'){showTools = false;}
    if(!showTools){
    	$("#id_finance_tool").hide();
    }
	if('${param.isHide}' == 'true'||'${param.isHide}'==''){
		$("#unkown_div").hide();
	}
});
function importFinanceReport(){
	var uputil=new uploadUtil({
		url:'/leasing/finance/importFinance.action',
    	title:'财务报表',
    	jscallback:'importFinanceCall',
    	parames:{custid:custid}
	});
    uputil.createFileAndShow();
}

function importFinanceCall(message){
	mini.unmask(document.body);
	mini.get("id_uploadfile").hide();
	mini.confirm(message, "是否刷此頁面", function () {
		window.location.reload(); 
   })
	
	
}

function edit(){
	mini.open({
        url: getRootPath()+"/leasing/editFinancialData.acl?custid=" + custid,
        title: "编辑", width: 800, height: 500,
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
	mini.get("id_date_remove").show();
}
function removeyeardata(){
	mini.confirm("确认要清空吗？", "清空", function (action) {
		 if(action=='ok'){
			var param = {};
			param.custid = custid;
			param.financialdate=mini.formatDate(mini.get("financialdate").getValue(),"yyyy-MM-dd");
			ajaxRequest({
				params:param,
				url:getRootPath()+"/leasing/removeFinancialData.acl",
				method : 'POST',
				async : false,
				success:function(response){
				
			/* 		mini.confirm("<spring:message code='837F2820-4D5B-48DC-9486-7893C1CCC6F6' text='删除数据成功!'/>", "是否刷新当前页面", function () {
						window.location.reload(); 
				   }) */
				   window.location.reload(); 
					showTable();
				}
			})
		}
   })
}
function onFinanceactivechanged(e){
	  var tab =e.tab;  
	  var id = tab.name;
	  var title=tab.title;
	  var thtml= $("#"+id).html()
	  showTable(title,id);
}
function showTable(title,financeid){
	var param = {};
	param.custid = custid;
	param.financeid=financeid;
	param['xmlFileName'] = "/eleasing/jsp/cust_info/cust_financial/cust_financial_table_show.xml";
	if(title=="资负表"||title=="Balance sheet"){
		   param["splitflag"]=",";
		}else{
		   param["splitflag"]="";	
		}
	var tempfinanceid=financeid;
	var temptitle=title;
	ajaxRequest({
		params:param,
		url:'${pageContext.request.contextPath}/table/getTableData.action',
		method : 'POST',
		async : false,
		success:function(response){
			var jsondata = eval('(' + response.responseText + ')').datas;
		    var trs=inintDebt(jsondata);
			$("#"+tempfinanceid).html(trs);
			$("#"+tempfinanceid+" td").css({
				'width':'100px',
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'right'
				
			});
			$("#"+tempfinanceid+" th").css({
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'left'
			});
			$("#"+tempfinanceid+" tr:eq(0)").css({
				'background': '#468CC8',
				'color': '#fff',
				'font-weight':'bold',
				'border': '1px dotted #cccccc'
			});
		},
		failure:function(response){
			mini.alert("加载财务报表出错!");
		}
	});
}
function inintDebt(jsondata){
	 var trs="";
	 var dateLenth=0;
	 var flagdata="";
	 if(jsondata.length>0){
		 var values = jsondata[0].obj.split(",");
		 dateLenth=values.length+2;
		 flagdata=values;
	 }
	 var tablename="";
	 if(jsondata.length!=0){
	    tablename = jsondata[0].title_name;
	 }
	 var showname="";
	 var count=0;
	 for(var i = 0;i<jsondata.length;i++){
		var subjectname = jsondata[i].subject_name;
		var values = jsondata[i].obj.split(",");
		var percent=jsondata[i].percent;
		trs += "<tr id='szctr'> <th width='30'>"+(i)+"</th> <th width='100'>"+subjectname+" </th>";
		for(var j = 0;j < values.length;j++){
			if(i<1){
			trs += "<td> " +values[j] + " </td>"; 
			count=values.length;
		    }else{	
		    	if(values[j]==''){
					   for(var y=0;y<count;y++){
						   trs +='<td></td>'
					   }
					}else{
		    trs += "<td> " +formateDebtData(percent,flagdata,j,values[j],tablename) + " </td>";
					}
		    } 
			
		}
		trs += " </tr>";
	 }
	
	 //if(tablename=="资产负债表"||tablename=="Balance sheet"){
	//	 showname="逻辑检查:当资产负债表检查结果不为0时提示.<br>填报提示：单位为万元";
	//	 trs += "<tr><th colspan="+dateLenth+">"+showname+"</th></tr>";
	// }
	 return trs;
}
function formateDebtData(percent,flagdata,index,values,tablename){
	if(values==""||values=="-"){return "";}
	   var temp=flagdata[index];
	   if(percent=="1"){
		   values=isNaN(values)==true?0:parseFloat(parseFloat(values)*100).toFixed(2);
		   values=isNaN(values)==true?"":values+"%";
	   }else{
		   if(tablename=="财务比率"){
			   values=isNaN(values)==true?0:parseFloat(values).toFixed(4);
			   if(!isNaN(values)){
			     if(values>1000 || values<-1000){
				   values=isNaN(values)==true?"":formatNumberThousand(values);
			      }
			   }else{
				   values="";
			   }
			   
		   }
		   else{
		   values=isNaN(values)==true?0:parseFloat(values).toFixed(2);
		   values=isNaN(values)==true?"":formatNumberThousand(values);
		   }
	   }
	   return values;
}
function formatNumberThousand(s) {  
	   if(isNaN(s)){
		   return s;
	   }
	   if(s==0){s="0.00";}
	   s += "";
	   s = s.replace(/,/g,"");
	   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g; //
	   var n1=s.replace(re,"$1,");
	   return n1;
}
</script>
<div class="mini-toolbar" id="id_finance_tool">
	<a class="mini-button" iconCls="icon-download"
		onclick="importFinanceReport()"><spring:message
			code="ImportFinancialReports" text="导入财务报表" /></a><span class="separator"></span>
	<a class="mini-button" iconCls="icon-edit" onclick="edit()"><spring:message
			code="edit" text="编辑" /></a><span class="separator"></span> <a
		class="mini-button" iconCls="icon-remove" onclick="remove()"><spring:message
			code="Clear" text="清空" /></a><span class="separator"></span> <a
		class="mini-button" iconCls="icon-download" onclick="tempdownload()"><spring:message
			code="TemplatesDownload" text="模板下载" /></a>
</div>
<div id="tabDetails" class="mini-tabs" activeIndex="0"
	onactivechanged="onFinanceactivechanged" style="width: 99.2%;"
	bodyStyle="border:0px;">
	<div title="资负表" name="297e072a57d574e40157d5a096680013"
		iconCls="icon-node">
		<table id="297e072a57d574e40157d5a096680013"
			style="border-collapse: collapse; width: 100%" cellspacing="0"></table>
	</div>
	<div title="利润表" name="297e072a57d574e40157d59d6bc60008"
		iconCls="icon-node">
		<table id="297e072a57d574e40157d59d6bc60008"
			style="border-collapse: collapse; width: 100%" cellspacing="0"></table>
	</div>

	<div title="现流表" name="297e072a57d574e40157d5a3d9820014"
		iconCls="icon-node">
		<table id="297e072a57d574e40157d5a3d9820014"
			style="border-collapse: collapse; width: 100%" cellspacing="0"></table>
	</div>

	<div title="财务比率" name="8ac180845827f43a0158295c7bc1015c"
		iconCls="icon-node">
		<table id="8ac180845827f43a0158295c7bc1015c"
			style="border-collapse: collapse; width: 100%" cellspacing="0"></table>
	</div>
</div>
<div class="mini-table"
	style="width: 100%; height: 100%; padding: 5px auto auto 5px;">
	<table id="table-finance-reprot"
		style="border-collapse: collapse; width: 100%" cellspacing="0">
	</table>
</div>
<%-- <div id="unkown_div" class="mini-panel" showCollapseButton="true"
	style="width: 100%;">
	<table id="table-add_unkown" class="fillTable"
		style="border-collapse: collapse; width: 100%" cellspacing="0">
		<tr class="tr-project-info tr-even">
			<td class="td-content-title" width="12%">Financial Analysis:</td>
			<td class="td-content"><textarea name="proj_other.fAnalysis"
					id="proj_other.fAnalysis" style="width: 73.5%; height: 150px"
					class="mini-textarea" label="Financial Analysis"
					emptyText="Please input Financial Analysis">${requestScope['proj_other.fAnalysis'] }</textarea>
			</td>
		</tr>
		<tr class="tr-project-info tr-odd">
			<td class="td-content-title" width="12%">Main Risks and
				Mitigants:</td>
			<td class="td-content"><textarea name="proj_other.mrMitigants"
					id="proj_other.mrMitigants" style="width: 73.5%; height: 150px"
					class="mini-textarea" label="Main Risks and Mitigants"
					emptyText="Please input Main Risks and Mitigants">${requestScope['proj_other.mrMitigants'] }</textarea>
			</td>
		</tr>
		<tr class="tr-project-info tr-even" width="12%">
			<td class="td-content-title">Summary &Recommendation:</td>
			<td class="td-content"><textarea
					name="proj_other.sRecommendation" id="proj_other.sRecommendation"
					style="width: 73.5%; height: 150px" class="mini-textarea"
					label="Summary &Recommendation"
					emptyText="Please input Summary &Recommendation">${requestScope['proj_other.sRecommendation'] }</textarea>
			</td>
		</tr>
	</table>
</div> --%>
 <div id="id_date_remove" class="mini-window" title="选择财报年份" style="width:400px;height:100px;" showModal="true" allowResize="true" allowDrag="true">
	<div id="id_date_financial">
		<table>
             	<tr>
		      
                    <td style="width:100px;">财报年份：</td>
                    <td ><input id="financialdate" name="financialdate" class="mini-datepicker miniext-form-fit" vtype="maxLength:50" /> </td>
                    <td>
                     <a class="mini-button" iconCls="icon-edit" onclick="removeyeardata">确定</a>
                     </td>
                </tr>
          </table>
	</div>
	</div>