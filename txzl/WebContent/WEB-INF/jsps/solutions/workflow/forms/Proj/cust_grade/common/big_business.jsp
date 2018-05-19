<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0">
<table id="id_credit_grade_table" class="fillTable" cellspacing="0" cellpadding="0">
	<tr class="tr-person_review-report tr-even">
		<td class="td-even-line-separator" colspan="4" style="text-indent: 0px;">
<!-- 		     <a class="mini-button" iconCls="icon-upload" id="id_import_finacne" onclick="importFinanceReport()">导入财务报表</a>	<span class="separator"></span>
		    <a class="mini-button" iconCls="icon-download" id="id_import_template" onclick="tempdownload()">财务报表模板下载</a> <span class="separator"></span>	 -->  
			<a class="mini-button" iconCls="icon-user" id="id_button_import" plain="true" onclick="initCreditTablea('big_business_data')" style="color: red;">导入评分自动计算值</a><span class="separator"></span>    
		</td>
	</tr>
	<tr class="tr-person_review-report">
		<td class="td-even-line-separator">
			<div id="div_id_big_business">
				<input id="json_big_business_str" style="display: none;"
					class="mini-textarea" name="json_big_business_str"
					value='${empty json_big_business_str ? "[]" : json_big_business_str }'>
			</div>
		</td>
	</tr>
</table>
<script language="javascript">
jQuery(function(){
	var showTools = false;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){ showTools = true;}
	jQuery(function(){
		new tracywindyTree2Table({
		   width:formWidth-1,
		   isAutoHeight:true,
		   refreshStatisticCallBack:window.setCreditLevel,
	 	   renderTo:'div_id_big_business',
	 	   id:'big_business_data',
	 	   rootDictId:'BIG_BUSINESS',
	 	   readOnly:showTools,
	 	   savedDataKey:'big_business_data'+flowUnid,
	 	   savedDataKey1:"${requestScope['cust_info.custnumber']}",
		   savedDataKey3:"${currentProcessInstanceDBID}",  //当前流程的ID
		   savedDataKey4:"${requestScope['currentWorkFlowName']}",//流程名称
	 	   height:document.documentElement.clientHeight-50,
	 	   totalScoreGrantValueTo:"all_Score",
	 	   treeTableJson:'${empty json_big_business_str ? "[]" : json_big_business_str }'
	    }); 
	});
 $(function(tableid){
    var credittype={};
	var params = {};
	var tid = tableid;
	params.custid = "${param.custid}";
	params.credittype = "财务比率";
	params.xmlFileName = "\\eleasing\\jsp\\finance\\financeGread.xml";
	ajaxRequest({
		url : getRootPath() + '/table/getTableData.action',
		async : false,
		params : params,
		timeout : 60 * 60 * 1000,
		success : function (res) {
			//res SQL里面查询出来的结果集
	          var currentData = mini.decode(res.responseText);
	          var credit=currentData.datas;
	          var treetable=getTracywindyObject(tid);
	          if(credit.length>0){
	             for(var i=0;i<credit.length;i++){
	            		var cinputs=$("input[dictid='" + credit[i].code + "'][type='radio']");
	            		$(cinputs).attr("disabled","disabled");     		 
	             }          
	          }
		}
	});
	});   
});

function getFinancevalue(code,lastvalue){
	var financevalue=0;//这个分值就是页面动态默认被选中的值。
	//资产负债表
    var newcode = code.replace("_","");
	if("CG01"== newcode){
		//转换为百分比
		if(""==lastvalue){
			financevalue=99;	
		}else{
			var llvalue = parseFloat(lastvalue * 100);		
		 if(llvalue <= 70){
			financevalue= 4;
		}else if(llvalue > 70 && llvalue <= 80 ){
			financevalue= 3;
		}else if(llvalue > 80 && llvalue <= 90 ){
			financevalue= 2;
		}else if(llvalue > 90 && llvalue <= 100){
			financevalue= 1;
		}else if(llvalue > 100){
			financevalue= 0;
		}
		}
	}
	//流动比率
	else if("CG02"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else if(lastvalue >= 1){
			financevalue= 4 ;
		}else if(lastvalue < 1 && lastvalue >= 0.5){
			financevalue= 2 ;
		}else if(lastvalue < 0.5 && lastvalue >= 0.2){
			financevalue= 1 ;
		}else if(lastvalue < 0.2){
			financevalue= 0 ;
		}
	}
	//利息保证倍数
	else if("CG03"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else if(lastvalue >= 4){
			financevalue= 3 ;
		}else if(lastvalue < 4 && lastvalue >= 3){
			financevalue= 2 ;
		}else if(lastvalue < 3 && lastvalue >= 2){
			financevalue= 1 ;
		}else if(lastvalue < 2){
			financevalue= 0 ;
		}
	}
	//营业利润率
	else if("CG04"== newcode){
		//转换为百分比
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 10){
			financevalue= 3 ;
		}else if(llvalue < 10 && lastvalue >= 7){
			financevalue= 2 ;
		}else if(llvalue < 7 && lastvalue >= 4){
			financevalue= 1 ;
		}else if(llvalue < 4){
			financevalue= 0 ;
		}
		}
	}
	//成本费用利润率
	else if("CG05"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 6){
			financevalue= 3 ;
		}else if(llvalue < 6 && lastvalue >= 3){
			financevalue= 2 ;
		}else if(llvalue < 3 && lastvalue > 0){
			financevalue= 1 ;
		}else if(llvalue <= 0){
			financevalue= 0 ;
		}
		}
	}
	//应收账款周转率
	else if("CG06"== newcode){
		if(""==lastvalue){
			financevalue==99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		 if(llvalue >= 1000){
			financevalue= 3 ;
		}else if(llvalue < 1000 && lastvalue >= 700){
			financevalue= 2 ;
		}else if(llvalue < 700 && lastvalue >= 300){
			financevalue= 1 ;
		}else if(llvalue < 300){
			financevalue= 0 ;
		}
		}
	}
	//流动资产周转率
	else if("CG07"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 250){
			financevalue= 3 ;
		}else if(llvalue < 250 && lastvalue >= 200){
			financevalue= 2 ;
		}else if(llvalue < 200 && lastvalue >= 150){
			financevalue= 1 ;
		}else if(llvalue < 150){
			financevalue= 0 ;
		}
		}
	}
	//总资产周转率
	else if("CG08"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 50){
			financevalue= 4 ;
		}else if(llvalue < 50 && lastvalue >= 40){
			financevalue= 3 ;
		}else if(llvalue < 40 && lastvalue >= 30){
			financevalue= 2 ;
		}else if(llvalue < 30 && lastvalue >= 20){
			financevalue= 1 ;
		}else if(llvalue < 20){
			financevalue= 0 ;
		}
		}
	}
	//有形净资产
	else if("CG09"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else if(lastvalue >= 50000000){
			financevalue= 3 ;
		}else if(lastvalue < 50000000 && lastvalue >= 30000000){
			financevalue= 2 ;
		}else if(lastvalue < 30000000 && lastvalue >= 10000000){
			financevalue= 1 ;
		}else if(lastvalue < 10000000){
			financevalue= 0 ;
		}
	}
	//净资产增长率
	else if("CG10"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 10){
			financevalue= 2 ;
		}else if(llvalue < 10 && lastvalue >= 0){
			financevalue= 1 ;
		}else if(llvalue < 0){
			financevalue= 0 ;
		}
		}
	}
	//净利润增长率
	else if("CG11"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 10){
			financevalue= 2 ;
		}else if(llvalue < 10 && lastvalue >= 0){
			financevalue= 1 ;
		}else if(llvalue < 0){
			financevalue= 0 ;
		}
		}
	}
	//留存收益率
	else if("CG12"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 50){
			financevalue= 2 ;
		}else if(llvalue < 50 && lastvalue >= 30){
			financevalue= 1 ;
		}else if(llvalue < 30){
			financevalue= 0 ;
		}
		}
	}
	//现金流量债务比
	else if("CG13"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 15){
			financevalue= 4 ;
		}else if(llvalue >= 10 && lastvalue < 15){
			financevalue= 3 ;
		}else if(llvalue >= 5 && lastvalue < 10){
			financevalue= 2 ;
		}else if(llvalue >= 0 && lastvalue < 5){
			financevalue= 1 ;
		}else if(llvalue < 0){
			financevalue= 0 ;
		}
	}
	}
	//总资产净利率
	else if("CG14"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 6){
			financevalue= 3 ;
		}else if(llvalue >= 3 && lastvalue < 6){
			financevalue= 2 ;
		}else if(llvalue > 0 && lastvalue < 3){
			financevalue= 1 ;
		}else if(llvalue <= 0){
			financevalue= 0 ;
		}
		}
	}
	//营业收入增长率
	else if("CG15"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 10){
			financevalue= 2 ;
		}else if(llvalue >= 0 && lastvalue < 10){
			financevalue= 1 ;
		}else if(llvalue <= 0){
			financevalue= 0 ;
		}
		}
	}
	//主营业务收入
	else if("CG16"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		if(lastvalue >= 1500000000){
			financevalue= 4 ;
		}else if(lastvalue >= 1000000000 && lastvalue < 1500000000){
			financevalue= 3 ;
		}else if(lastvalue >= 500000000 && lastvalue < 1000000000){
			financevalue= 2 ;
		}else if(lastvalue >= 300000000 && lastvalue < 500000000){
			financevalue= 1 ;
		}else if(lastvalue < 300000000){
			financevalue= 0 ;
		}
		}
	}
	//总资产
	else if("CG17"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		if(lastvalue >= 2000000000){
			financevalue= 4 ;
		}else if(lastvalue >= 1500000000 && lastvalue < 2000000000){
			financevalue= 3 ;
		}else if(lastvalue >= 1000000000 && lastvalue < 1500000000){
			financevalue= 2 ;
		}else if(lastvalue >= 500000000 && lastvalue < 1000000000){
			financevalue= 1 ;
		}else if(lastvalue < 500000000){
			financevalue= 0 ;
		}
		}
	}
	//净资产
	else if("CG18"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		if(lastvalue >= 500000000){
			financevalue= 2 ;
		}else if(lastvalue >= 300000000 && lastvalue < 500000000){
			financevalue= 1.5 ;
		}else if(lastvalue >= 100000000 && lastvalue < 300000000){
			financevalue= 1 ;
		}else if(lastvalue >= 50000000 && lastvalue < 100000000){
			financevalue= 0.5 ;
		}else if(lastvalue <  50000000){
			financevalue= 0 ;
		}
		}
	}
	return financevalue;
}
//让点击事件只触发一次
var flag = true;
function initCreditTablea(tableid) {
	var credittype={};
	var params = {};
	var tid = tableid;
	params.custid = "${param.custid}";
	params.credittype = "财务比率";
	params.xmlFileName = "\\eleasing\\jsp\\finance\\financeGread.xml";
	ajaxRequest({
		url : getRootPath() + '/table/getTableData.action',
		async : false,
		params : params,
		timeout : 60 * 60 * 1000,
		success : function (res) {
			//res SQL里面查询出来的结果集
			if(flag==true){
	          var currentData = mini.decode(res.responseText);
	          var credit=currentData.datas;
	          var treetable=getTracywindyObject(tid);
	          var treetableConfig=treetable.tableConfig["treeDataMapping"];
	          var newvalue=0;//分值
	          if(credit.length>0){
	             for(var i=0;i<credit.length;i++){
	            	 newvalue=getFinancevalue(credit[i].code,credit[i].value);
	            	 setTree2TableOneData(treetableConfig,tid,credit[i].code,newvalue)
	             }
	             //mini.alert("成功导入评分");
	          }else{	
	        	  mini.alert("没有财报数据数据,请导入财务报表");
	        	  return false;
	          }
			}else{
				mini.alert("自动评分已完成，请勿重复点击");
			}
	          flag = false;
		}
	});
}

function setTree2TableOneData(treetableConfig,tid,code,value){
    var lastValue=value;
    if(value===""){return}
    lastValue=parseFloat(lastValue).toFixed(2);
    var flagtmp=false;
    var cinputs=$("input[dictid='" + code + "'][type='radio']");
    if(cinputs.length>0){
    	for(var j=0;j<cinputs.length;j++){
    		if(parseFloat(cinputs[j].value)==parseFloat(lastValue)){
    			$(cinputs).removeAttr("disabled"); 
    			cinputs[j].checked = true;
    			cinputs[j].click();
    			flagtmp=true;
    			$(cinputs).attr("disabled","disabled");  
    		}
    	 	if(!flagtmp){
    			try{
    				var tdTotal = $(cinputs[0]).parent().parent().parent().children("td[identifer='columnsStatistic']");
    	    		tdTotal.html("NaN");
    			}catch(e){
    			}
        	}
    	}
    }else{
        var lables=$("#div_id_"+tid+" label[dictid='"+code+"']");
        if(lables.length>0){
        var tempvalue=0;
        var xlables=null;
        var autocompluter=false;
        var index=-1;
        for(var j=0;j<lables.length;j++){
        	xlables=lables[j];
        	var span= xlables.childNodes[0];
            if(span){
            	if(span.nodeType!=3){
            	span.setAttribute("class", "tree2table-radio");   }
               }
        } 	
        xlables=null;   
        for(var j=0;j<lables.length;j++){
        	tempvalue=lables[j].getAttribute("value");
        	var svalue=treetableConfig[code][j].defaultValue||"";
        	if(svalue.indexOf("x")<0){
        	   tempvalue=parseFloat(tempvalue).toFixed(2);
        	   if(parseFloat(lastValue)==parseFloat(tempvalue)){xlables=lables[j];}
        	}else{
        		if(xlables==null){
        			if(svalue.indexOf("+")>0){        	        	
        				var tvalue=svalue.substr(svalue.indexOf("+")+1);
        				if(parseFloat(lastValue)>parseFloat(tvalue)){
        					 xlables=lables[j];autocompluter=true;index=j;
        				}
        			}else{
        		       xlables=lables[j];autocompluter=true;index=j;
        			 }
        		  } 
               }
           }
           if(lastValue!=""){
	         var span= xlables.childNodes[0];
	         span.setAttribute("class", "tree2table-radio-checked"); 
	         lastValue=lastValue+"";  
	         if(lastValue.indexOf(".00")>0){
	            lastValue=lastValue.replace(".00","");
	         }
	         scoreClick(tid,xlables.getAttribute("name"),code,lastValue,false,"radio",xlables.getAttribute("columnid"));
	         if(autocompluter==true){  
	        	 treetableConfig[code][index].value=lastValue;
	         } 
           }
        }else{
        	 //初始化输入项
        	 var  cinputs=$("input[columnid='" + code + "']");
        	 if(cinputs.length>0){
        		 var cinput=cinputs[0];
        		 var ctext=cinput.parentNode.innerText;
        		 if(ctext.indexOf("%")>=0){
        			  cinputs[0].value=parseFloat(value*100).toFixed(2);
        		 }else{
        		   cinputs[0].value=value;
        		 }
        	 }else{
        	   //初始化结果项	
               if(code=="id_creditgrade"||code=="id_syscreditlevel"||code=="id_changecreditgrade"||code=="id_changecreditlevel"){
            	   if(code=="id_creditgrade"){
            		   mini.get(code).setValue(parseFloat(value).toFixed(2));
            	   }else{
            		   mini.get(code).setValue(value);
            	   }
               }else{ 
            	   var lables=$("label[columnid='"+code+"']");
                   if(lables.length>0){
                	   lables[0].innerHTML=value;
                   }/* else{
        	   if(null!=code){
        	      alert(code+"没有对应的选项");
               }} */}
        	}
      } 
} 
}
/* function importFinanceCall(message){
	mini.unmask(document.body);
	mini.get("id_uploadfile").hide();
	mini.confirm(message, "是否刷此頁面", function () {
		window.location.reload(); 
   })
	
	
} */
/* function importFinanceReport(){
	var uputil=new uploadUtil({
		url:'/leasing/finance/importFinance.action',
    	title:'财务报表',
    	jscallback:'importFinanceCall',
    	parames:{custid:"${param.custid}"}
	});
    uputil.createFileAndShow();
}
function tempdownload(){
	var templateno='F-201612002';
    downLoadFileTemplate(templateno);
} */
</script>
