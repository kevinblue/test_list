<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='/WEB-INF/tlds/c.tld'%>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyAjax.js"></script>
<script type="text/javascript">
alert(222);
var custid ='';
function showTable(title,financeid){
	var param = {};
	param.custid = custid;
	param.financeid=financeid;
	param['xmlFileName'] = "/eleasing/jsp/cust_info/cust_financial/cust_financial_table_show.xml";
	param["splitflag"]="";	
	var tempfinanceid=financeid;
	var temptitle=title;
	ajaxRequest({
		params:param,
		url:'${pageContext.request.contextPath}/table/getTableData.action',
		method : 'POST',
		async : false,
		success:function(response){
			var jsondata = eval('(' + response.responseText + ')').datas;
		    var trs="";
		    if(temptitle=="财务比率"){ trs=initGread(jsondata);}
		    else {trs=inintDebt(jsondata);}
			$("#"+tempfinanceid).html(trs);
			$("#"+tempfinanceid+" td").css({
				'width':'100px',
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'right',
				
			});
			$("#"+tempfinanceid+" th").css({
				'border': '1px dotted #cccccc',
				'padding-left': '5px',
				'text-align':'left'				
			});
			if(temptitle=="财务比率"){
				$("#"+tempfinanceid+" tr:eq(1)").css({'background': '#468CC8'});
				$("#"+tempfinanceid+" tr:eq(7)").css({'background': '#468CC8'});
				$("#"+tempfinanceid+" tr:eq(20)").css({'background': '#468CC8'});
				$("#"+tempfinanceid+" tr:eq(28)").css({'background': '#468CC8'});
			}else{
				$("#"+tempfinanceid+" tr:eq(0)").css({'background': '#468CC8'});
			}
		},
		failure:function(response){
			mini.alert("加载财务报表出错!");
		}
	});
}

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
					window.location.reload(); 
				}
			})
		}
    })
}
</script>
<div id="toolbar" class="mini-toolbar">
		<a class="mini-button" iconCls="icon-upload" onclick="importFinanceReport()">导入财务报表</a><span class="separator"></span>
	<!-- 	<a text-aligh="right"class="mini-button" iconCls="icon-edit" onclick="edit()">编辑</a><span class="separator"></span> -->
	 <a text-aligh="right"class="mini-button" iconCls="icon-remove" onclick="remove()">清空</a><span class="separator"></span>
		<a text-aligh="right"class="mini-button" iconCls="icon-download" onclick="tempdownload()">模板下载</a>
</div>
<div id="id_DateContainers"   title="财务日期" style="height:400;display:none" >
	        <center>
			        <table style="width:100%" >
			                <tr >
                              <td >开始日期：</td><td><input id="id_plan_date_start" name="plan_date_start" class="Wdate td-content-input td-content-readonly" type="text"   onClick="WdatePicker(this,{readOnly:true})" /></td>
                              <td >结束日期：</td><td ><input id="id_plan_date_end" name="plan_date_end" class="Wdate td-content-input td-content-readonly" type="text"   onClick="WdatePicker(this,{readOnly:true})" /></td>
                          </tr>
			              <tr class="content-separator">
				            <td colspan='4'>
				                 <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary"onclick='loadFinacneByDate();'><span>确定</span></a>
						         <a  style="margin-left:20px;" href="javascript:void(0);" class="btn btn-primary" onclick='jQuery("#id_DateContainers").window("close");'><span>取消</span></a>
				            </td>
			            </tr>
			        </table>
		         
	        </center>
</div>
<div id="tabDetails" class="mini-tabs"  activeIndex="0"  onactivechanged="onFinanceactivechanged" style="width: 100%;" bodyStyle="border:0px;">
	    <div title="项目进度台帐" name="4028801556e9dd5a0156e9f6b51b000b" iconCls="icon-node">
			 <table id="4028801556e9dd5a0156e9f6b51b000b" style="border-collapse: collapse; width: 100%" cellspacing="0"></table>
		</div>


</div>
<script language="javascript">
 function inintDebt(jsondata){
	 alert(444);
	 var trs="";
	 var dateLenth=0;
	 var flagdata="";
	 if(jsondata.length>0){
		 var values = jsondata[0].obj.split(",");
		 dateLenth=values.length+2;
		 flagdata=values;
	 }
	 var tablename = jsondata[0].title_name;
	 var showname="";
	 
	 for(var i = 0;i<jsondata.length;i++){
		var subjectname = jsondata[i].subject_name;
		var values = jsondata[i].obj.split(",");
		trs += "<tr><th width='100'>"+subjectname+"</th>";
		for(var j = 0;j < values.length;j++){
			if(i<1){
				   trs += "<td>" +values[j].split(".")[0] + "</td>";
		    }else{	
			    
                  trs += "<td>" +formateDebtData(flagdata,j,values[j]) + "</td>";
		    }  
		}
		trs += "</tr>";
	 }
		 showname="填报提示：单位元";
		 trs += "<tr><th colspan="+dateLenth+">"+showname+"</th></tr>";
	 return trs;
 }
 function initGread(jsondata){
	 var trs="";
	 var dateLenth=0;
	 var flagdata="";
	 if(jsondata.length>0){
		 var values = jsondata[0].obj.split(",");
		 dateLenth=values.length+2;
		 flagdata=values;
	 }
	 var tablename = jsondata[0].title_name;
	 var showname="";
	 for(var i = 0;i<jsondata.length;i++){
		var subjectname = jsondata[i].subject_name;
		var values = jsondata[i].obj.split(",");
		var subjectnames=subjectname.split("@")
		//<th  width='30'>"+(i+1)+"</th>
		trs += "<tr>";
		for(var j=0;j<subjectnames.length;j++){
			trs += "<th width='50'>"+subjectnames[j]+"</th>";
	    }
		for(var j = 0;j < values.length;j++){
			if(i==1||i==7||i==20||i==28){
				trs += "<td>" +values[j].split(".")[0] + "</td>";
			}else{
			   var temp=values[j]
		        temp=isNaN(temp)==true?temp:parseFloat(parseFloat(temp)*100).toFixed(2);
		         temp=isNaN(temp)==true?(temp=="NaN"?"":temp):temp+"%";
		        trs += "<td>" +temp + "</td>";
			}
		}
		trs += "</tr>";
	 }
	 showname="说明：最新一期部分损益类指标采用年化后数据。";
	 trs += "<tr><th colspan="+dateLenth+">"+showname+"</th></tr>";
	 return trs;
 }
 function formateDebtData(flagdata,index,values){
   if(values==""||values=="-"){return "";}
   var temp=flagdata[index];
   if(temp=="占比"||temp=="余额变动百分比"){
	   values=isNaN(values)==true?0:parseFloat(parseFloat(values)*100).toFixed(0);
	   values=isNaN(values)==true?"":values+"%";
   }else{
	   values=isNaN(values)==true?0:parseFloat(values).toFixed(2);
	   values=isNaN(values)==true?"":formatNumberThousand(values);
   }
   return values;
}
 function onFinanceactivechanged(e){
	  var tab =e.tab;  
	  var id = tab.name;
	  var title=tab.title;
	  var thtml= $("#"+id).html()
	  if(thtml.length>10){}else{
	  showTable(title,id);}
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
 jQuery(function(){
	/*  if('${param.isView}' == 'true'){
		 document.getElementById("toolbar").style.display="none";
		 } */
	 var cur_date=new Date();
		var year=cur_date.getFullYear();
	 var plan_date_start=(parseFloat(year)-4)+"-01-01";
	 var plan_date_end=year+"-12-31";
	});
 
</script>