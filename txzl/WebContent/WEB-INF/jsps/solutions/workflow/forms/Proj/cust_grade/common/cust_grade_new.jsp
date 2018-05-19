<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table cellspacing="0" cellpadding="0">
<table id="id_credit_grade_table" class="fillTable" cellspacing="0" cellpadding="0">
<!--  	<tr class="tr-person_review-report tr-even"> 
		<td class="td-even-line-separator" colspan="4" style="text-indent: 0px;">
			<a class="mini-button" iconCls="icon-user" id="id_button_import" plain="true" onclick="initCreditTablea('cust_grade_data')" style="color: red;">导入最近一次报表</a><span class="separator"></span>     
			<a class="mini-button" iconCls="icon-user" id="id_button_import2" plain="true" onclick="initCreditTablea('cust_grade_data')" style="color: red;">选择年份导入报表</a>
		</td>
	</tr> -->
	<div id="id_main_year">
             	<tr>
                    <td style="width:120px;">选择年份导入报表：
                    <input style="width:200px;" id="id_year" name="id_year" class="mini-buttonedit mini-queryinput" allowInput="false" 
							text="${empty requestScope['rawValue_id_year'] ? '' : requestScope['rawValue_id_year']}"
							value="${empty requestScope['id_year'] ? '': requestScope['id_year']}"
							/>
                    <a class="mini-button" iconCls="icon-edit" onclick="initCreditTablea('cust_grade_data')">确定</a>
                    
                   <a class="mini-button" iconCls="icon-exportExcel" onclick="AllAreaExcel()">打印</a> 
                      <input id="rawValue_id_year" name="rawValue_id_year" value="${empty requestScope['rawValue_id_year'] ? '': requestScope['rawValue_id_year']}" class="mini-textbox" style="display:none"/>
                    </td>
                </tr>
	</div>
		<input id="json_cust_grade_str" style="display: none;"
					class="mini-textarea" name="json_cust_grade_str"
					value='${empty json_cust_grade_str ? "[]" : json_cust_grade_str }'>
 	<tr class="tr-person_review-report">
		<td class="td-even-line-separator">
			<div id="div_id_cust_grade">
			</div>
		</td>
	</tr> 
</table>

<script language="javascript">
jQuery(function(){
	tenwa.createQueryInput({
		id:'id_year',
		textField:"id",
      	valueField:"id",
		windowWidth: 600,
		onSelect: function($me, inputObj, rowData){
			mini.get("rawValue_id_year").setValue(rowData.id);
		}, 
		params: {
			custid:'${param.custid}',
			xmlFileName: '/eleasing/workflow/other/finance_date.xml'
		}
	}); 
}); 
jQuery(function(){
	
	var showTools = false;
	if('${param.isView}' == 'true'||isViewHistoryTask==true){ showTools = true;}
	jQuery(function(){
		new tracywindyTree2Table({
		   width:formWidth-1,
		   isAutoHeight:true,
		   refreshStatisticCallBack:window.setCreditLevel,
	 	   renderTo:'div_id_cust_grade',
	 	   id:'cust_grade_data',
	 	   rootDictId:'CUST_GRADE',
	 	   readOnly:showTools,
	 	   savedDataKey:'cust_grade_data'+flowUnid,
	 	   savedDataKey1:"${requestScope['cust_info.custnumber']}",
		   savedDataKey3:"${currentProcessInstanceDBID}",  //当前流程的ID
		   savedDataKey4:"${requestScope['currentWorkFlowName']}",//流程名称
	 	   height:document.documentElement.clientHeight-50,
	 	   totalScoreGrantValueTo:"all_Score",
	 	   treeTableJson:'${empty json_cust_grade_str ? "[]" : json_cust_grade_str }'
	    }); 
	});
	$(function(tableid){
	    var credittype={};
		var params = {};
		var tid = tableid;
		params.custid = "${param.custid}";
		params.credittype = "财务比率";
		params.xmlFileName = "\\eleasing\\jsp\\finance\\financeGreadty.xml";
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
	//1资产负债率
	if("G002"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue <= 50){
			financevalue= 4 ;
		}else if(llvalue > 50 && llvalue <= 70){
			financevalue= 3 ;
		}else if(llvalue > 70 && llvalue <= 80){
			financevalue= 2 ;
		}else if(llvalue > 80 && llvalue <= 90){
			financevalue= 1 ;
		}else if(llvalue > 90){
			financevalue= 0 ;
		}
		}
	}
	//2流动比率
	else if("G003"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		  if(llvalue >= 150){
			financevalue= 5 ;
		}else if(llvalue >= 130 && llvalue < 150){
			financevalue= 4 ;
		}else if(llvalue >= 110 && llvalue < 130){
			financevalue= 3 ;
		}else if(llvalue >= 90 && llvalue < 110){
			financevalue= 2 ;
		}else if(llvalue < 90){
			financevalue= 1 ;
		}
		}
	}
	//3速动比率
	else if("G004"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 100){
			financevalue= 3 ;
		}else if(llvalue >=70 && lastvalue <100){
			financevalue= 2 ;
		}else if(llvalue >=50 && lastvalue <70){
			financevalue= 1 ;
		}else if(llvalue < 50){
			financevalue= 0 ;
		}
		}
	}
	//4利息保证倍数
	else if("G006"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else if(lastvalue >= 10){
			financevalue= 10 ;
		}else if(lastvalue < 10 && lastvalue >= 9){
			financevalue= 9 ;
		}else if(lastvalue < 9 && lastvalue >= 8){
			financevalue= 8 ;
		}else if(lastvalue < 8 && lastvalue >= 7){
			financevalue= 7 ;
		}else if(lastvalue < 7 && lastvalue >= 6){
			financevalue= 6 ;
		}else if(lastvalue < 6 && lastvalue >= 5){
			financevalue= 5 ;
		}else if(lastvalue < 5 && lastvalue >= 4){
			financevalue= 4 ;
		}else if(lastvalue < 4 && lastvalue >= 3){
			financevalue= 3 ;
		}else if(lastvalue < 3 && lastvalue >= 2){
			financevalue= 2 ;
		}else if(lastvalue < 2 && lastvalue >= 1){
			financevalue= 1 ;
		}else if(lastvalue < 1 ){
			financevalue= -2 ;
		}
	}
	//5营业利润率
	else if("G007"== newcode){
		//转换为百分比
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 12){
			financevalue= 4 ;
		}else if(llvalue < 12 && llvalue >= 8){
			financevalue= 3 ;
		}else if(llvalue < 8 && llvalue >= 4){
			financevalue= 2 ;
		}else if(llvalue >=0 ){
			financevalue= 1 ;
		}
		}
	}
	//6成本费用利润率
	else if("G008"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 30){
			financevalue= 4 ;
		}else if(llvalue < 30 && llvalue >= 20){
			financevalue= 3 ;
		}else if(llvalue < 20 && llvalue >= 10){
			financevalue= 2 ;
		}else if(llvalue >= 0){
			financevalue= 1 ;
		}else if(llvalue < 0){
			financevalue= 0 ;
		}
		}
	}
	//7应收账款周转率
	else if("G010"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 500){
				financevalue= 3 ;
			}else if(llvalue < 500 && llvalue >= 300){
				financevalue= 2 ;
			}else if(llvalue < 300 && llvalue >= 200){
				financevalue= 1 ;
			}else if(llvalue < 200 && llvalue >= 100){
				financevalue= 0 ;
			}else if(llvalue < 100){
				financevalue= -2 ;
			}
		} 		
	}
	//8总资产周转率
	else if("G011"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 30){
			financevalue= 3 ;
		}else if(llvalue < 30 && llvalue >= 20){
			financevalue= 2 ;
		}else if(llvalue < 20 && llvalue >= 10){
			financevalue= 1 ;
		}else if(llvalue < 10){
			financevalue= 0 ;
		}
		}
	}
	//9固定资产周转率
	else if("G012"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 30){
			financevalue= 2 ;
		}else if(llvalue < 30 && llvalue >= 15){
			financevalue= 1 ;
		}else if(llvalue < 15){
			financevalue= 0 ;
		}
		}
	}
	//10有形净资产
	if("G013"== newcode){
		//转换为百分比
		if(""==lastvalue){
			financevalue=99;	
		}else{
		 if(lastvalue >= 50000000){
			financevalue= 5;
		}else if(lastvalue >= 40000000 && lastvalue < 50000000 ){
			financevalue= 4;
		}else if(lastvalue >= 30000000 && lastvalue < 40000000){
			financevalue= 3;
		}else if(lastvalue >= 20000000 && lastvalue < 30000000){
			financevalue= 2;
		}else if(lastvalue >= 10000000 && lastvalue < 20000000){
			financevalue= 1;
		}else if(lastvalue < 10000000){
			financevalue= 0;
		}
		}
	}
	//11营业收入增长率
	else if("G015"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 20){
				financevalue= 3 ;
			}else if(llvalue >=10 && llvalue < 20){
				financevalue= 2 ;
			}else if(llvalue >=0){
				financevalue= 1 ;
			}else if(llvalue < 0){
				financevalue= 0 ;
			}
		} 		
	}
	//12利润总额增长率
	else if("G016"== newcode){
		if("" == lastvalue){
			financevalue= 99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
			if(llvalue >= 20){
				financevalue= 4 ;
			}else if(llvalue >=10 && llvalue < 20){
				financevalue= 3 ;
			}else if(llvalue >=5 && llvalue < 10){
				financevalue= 2 ;
			}else if(llvalue >=0){
				financevalue= 1 ;
			}else if(llvalue < 0){
				financevalue= 0 ;
			}
		} 		
	}
	//13留存收益率
	else if("G017"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
			var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 50){
			financevalue= 3 ;
		}else if(llvalue < 50 && llvalue >= 40){
			financevalue= 2 ;
		}else if(llvalue < 40 && llvalue >= 30){
			financevalue= 1 ;
		}else if(llvalue < 30){
			financevalue= 0 ;
		}
		}
	}
	//14现金流量债务比
	else if("G005"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 14){
			financevalue= 10 ;
		}else if(llvalue < 14 && llvalue >= 12){
			financevalue= 9 ;
		}else if(llvalue < 12 && llvalue >= 10){
			financevalue= 8 ;
		}else if(llvalue < 10 && llvalue >= 8){
			financevalue= 7 ;
		}else if(llvalue < 8 && llvalue >= 6){
			financevalue= 6 ;
		}else if(llvalue < 6 && llvalue >= 4){
			financevalue= 5 ;
		}else if(llvalue < 4 && llvalue >= 3){
			financevalue= 4 ;
		}else if(llvalue < 3 && llvalue >= 2){
			financevalue= 3 ;
		}else if(llvalue < 2 && llvalue >= 1){
			financevalue= 2 ;
		}else if(llvalue < 1 && llvalue >= 0){
			financevalue= 1 ;
		}else if(llvalue < 0){
			financevalue= 0 ;
		}
		}
	}
	//15总资产报酬率
	else if("G009"== newcode){
		if(""==lastvalue){
			financevalue=99;
		}else{
		var llvalue = parseFloat(lastvalue * 100);
		if(llvalue >= 10){
			financevalue= 5 ;
		}else if(llvalue < 10 && llvalue >= 8){
			financevalue= 4 ;
		}else if(llvalue < 8 && llvalue >= 6){
			financevalue= 3 ;
		}else if(llvalue < 6 && llvalue >= 4){
			financevalue= 2 ;
		}else if(llvalue < 4 && llvalue >= 2){
			financevalue= 1 ;
		}else if(llvalue >= 0){
			financevalue= 0 ;
		}else if(llvalue < 0){
			financevalue= -1 ;
		}
		}
	} 
	return financevalue;
}
function AllAreaExcel() {
	try{
		/* var oXL = new ActiveXObject("Excel.Application"); //导出office
		var oWB = oXL.Workbooks.Add(); 
		var oSheet = oWB.ActiveSheet;         
		var sel=document.body.createTextRange();
		sel.moveToElementText(document.getElementById("div_id_cust_grade"));
		sel.select();
		sel.execCommand("Copy");
		oSheet.Paste();
		oXL.Visible = true; */
		/* document.body.innerHTML=document.getElementById('div_id_cust_grade').innerHTML; 
		window.print();  */
		var str = document.getElementById('div_id_cust_grade').innerHTML;
		var oPop = window.open('','oPop');  
        oPop.document.write(str);  
        oPop.print();  
        oPop.close();
	 }catch(e) {
	 	alert("要输出该表，您必须安装Windows Office Excel电子表格软件，同时要正确设置安全站点！");
	 	return "";    
	 }
}	

function initCreditTablea(tableid) {
	var credittype={}
	var params = {};
	var tid = tableid;
	var custid = "${param.custid}";
	var credittype = "财务比率";
	var year = mini.get("rawValue_id_year").getValue();
	$.ajax({
		url : urlPrefix + "/eleasing/jsp/finance/financeGreadty.xml",
		type: "post",
	    cache: false,
		async : false,
		data :{custid:custid,credittype:credittype,year:year},
		success : function (res) {
	        var currentData = mini.decode(res);
	          var credit=currentData.datas;
	          var treetable=getTracywindyObject(tid);
	          var treetableConfig=treetable.tableConfig["treeDataMapping"];
	          if(credit.length>0){
	             for(var i=0;i<credit.length;i++){
	            	 newvalue=getFinancevalue(credit[i].code,credit[i].value);
	            	 setTree2TableOneData(treetableConfig,tid,credit[i].code,newvalue)
	             }
	          }else{	
	        	  mini.alert("没有财报数据数据,请导入财务报表");
	        	  return false;
	          }
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
                   }
                   }
        	}
      } 
} 
}
</script>
