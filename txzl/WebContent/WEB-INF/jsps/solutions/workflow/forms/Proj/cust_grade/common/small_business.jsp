<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0">
<table id="id_credit_grade_table" class="fillTable" cellspacing="0" cellpadding="0">
	<tr class="tr-person_review-report tr-even">
		<td class="td-even-line-separator" colspan="4" style="text-indent: 0px;">
<!-- 		 <a class="mini-button" iconCls="icon-upload" id="id_import_finacne" onclick="importFinanceReport()">导入财务报表</a>	<span class="separator"></span>
		    <a class="mini-button" iconCls="icon-download" id="id_import_template" onclick="tempdownload()">财务报表模板下载</a>	<span class="separator"></span>   -->
			<a class="mini-button" iconCls="icon-user" id="id_button_import" plain="true" onclick="initCreditTablea('small_business_data')" style="color: red;">导入评分自动计算值</a><span class="separator"></span>    
		</td>
	</tr>
	<tr class="tr-person_review-report">
		<td class="td-even-line-separator">
			<div id="div_id_small_business">
				<input id="json_small_business_str" style="display: none;"
					class="mini-textarea" name="json_small_business_str"
					value='${empty json_small_business_str ? "[]" : json_small_business_str }'>
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
	 	   renderTo:'div_id_small_business',
	 	   id:'small_business_data',
	 	   rootDictId:'SMALL_BUSINESS',
	 	   readOnly:showTools,
	 	   savedDataKey:'small_business_data'+flowUnid,
	 	   savedDataKey1:"${requestScope['cust_info.custnumber']}",
		   savedDataKey3:"${currentProcessInstanceDBID}",  //当前流程的ID
		   savedDataKey4:"${requestScope['currentWorkFlowName']}",//流程名称
	 	   height:document.documentElement.clientHeight-50,
	 	   totalScoreGrantValueTo:"all_Score",
	 	   treeTableJson:'${empty json_small_business_str ? "[]" : json_small_business_str }'
	    }); 
	});
	$(function(tableid){
	    var credittype={};
		var params = {};
		var tid = tableid;
		params.custid = "${param.custid}";
		params.credittype = "财务比率";
		params.xmlFileName = "\\eleasing\\jsp\\finance\\financeGreadsmall.xml";
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
var flag = true;
function getFinancevalue(code,lastvalue){
	var financevalue=0;//这个分值就是页面动态默认被选中的值。
    var newcode = code.replace("_","");
	//有形净资产
	if("SG01"== newcode){
		//转换为百分比
		if(""==lastvalue){
			financevalue=99;	
		}else{
		 if(lastvalue >= 100000000){
			financevalue= 5;
		}else if(lastvalue >= 50000000 && lastvalue < 100000000 ){
			financevalue= 3;
		}else if(lastvalue >= 20000000 && lastvalue < 50000000){
			financevalue= 1;
		}else if(lastvalue < 20000000){
			financevalue= 0;
		}
		}
	}
	//资产负债率
	else if("SG02"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue <= 50){
			financevalue= 6 ;
		}else if(llvalue > 50 && lastvalue <= 60){
			financevalue= 4 ;
		}else if(llvalue > 60 && lastvalue <= 70){
			financevalue= 2 ;
		}else if(llvalue > 70 && lastvalue <= 80){
			financevalue= 0 ;
		}else if(llvalue > 80){
			financevalue= -3 ;
		}
		}
	}
	//流动比率
	else if("SG03"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else if(lastvalue >= 1.4){
			financevalue= 3 ;
		}else if(lastvalue >= 1.2 && lastvalue < 1.4){
			financevalue= 2 ;
		}else if(lastvalue >= 1.0 && lastvalue < 1.2){
			financevalue= 1 ;
		}else if(lastvalue < 1.0){
			financevalue= 0 ;
		}
	}
	//应收账款周转率
	else if("SG04"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 400){
				financevalue= 2 ;
			}else if(llvalue < 400 && llvalue >= 200){
				financevalue= 1 ;
			}else if(llvalue < 200){
				financevalue= 0 ;
			}
		} 		
	}
	//营业收入增长率
	else if("SG05"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 20){
				financevalue= 3 ;
			}else if(llvalue >=10 && llvalue < 20){
				financevalue= 2 ;
			}else if(llvalue >=0 && llvalue < 10){
				financevalue= 1 ;
			}else if(llvalue < 0){
				financevalue= 0 ;
			}
		} 		
	}
	//主营业务收入
	else if("SG06"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			if(lastvalue >= 30000000){
				financevalue= 3 ;
			}else if(lastvalue >=20000000 && lastvalue < 30000000){
				financevalue= 2 ;
			}else if(lastvalue >=10000000 && lastvalue < 20000000){
				financevalue= 1 ;
			}else if(lastvalue < 10000000){
				financevalue= 0 ;
			}
		} 		
	}
	//净利润增长率
	else if("SG07"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 20){
				financevalue= 3 ;
			}else if(llvalue >=10 && llvalue < 20){
				financevalue= 2 ;
			}else if(llvalue >=0 && llvalue < 10){
				financevalue= 1 ;
			}else if(llvalue < 0){
				financevalue= 0 ;
			}
		} 		
	}
	//净资产收益率
	else if("SG08"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 20){
				financevalue= 3 ;
			}else if(llvalue >=10 && llvalue < 20){
				financevalue= 2 ;
			}else if(llvalue >=0 && llvalue < 10){
				financevalue= 1 ;
			}else if(llvalue < 0){
				financevalue= 0 ;
			}
		} 		
	}
	return financevalue;
}

function initCreditTablea(tableid) {
	var credittype={}
	var params = {};
	var tid = tableid;
	params.custid = "${param.custid}";
	params.credittype = "财务比率";
	params.xmlFileName = "\\eleasing\\jsp\\finance\\financeGreadsmall.xml";
	ajaxRequest({
		url : getRootPath() + '/table/getTableData.action',
		async : false,
		params : params,
		timeout : 60 * 60 * 1000,
		success : function (res) {
			if(flag==true){
	        var currentData = mini.decode(res.responseText);
	          var credit=currentData.datas;
	          var treetable=getTracywindyObject(tid);
	          var treetableConfig=treetable.tableConfig["treeDataMapping"];
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
    var cinputs=$("input[dictid='" + code + "'][type='radio']");
    var flagtmp=false;
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
	var templateno='F-201612003';
    downLoadFileTemplate(templateno);
} */
</script>
