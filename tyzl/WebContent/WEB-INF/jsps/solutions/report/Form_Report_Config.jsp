<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<%@ taglib uri="/minidict" prefix="mini"%>
<div id="id_form_report" class="mini-window" modal="true" title="<spring:message code='report.config.manageReport' text='报表管理' />" style="width: 450px">
	<center>
		<form id="form_report">
        	<table style="width:90%">
        		<tr>
        			<td style="display: none;">
        				<input name="id" id="id"  class="mini-textbox"/>
        				<input name="position" id="position" class="mini-textbox" />
						<input name="parentReport" id="parentReport" class="mini-textbox" />
						<input name="reportMenuRoot" id="reportMenuRoot" class="mini-textbox" value="<mini:param name='reportMenuRoot'/>"/>
        			</td>
        		</tr>
        		<tr>
					<td class="input_label_desc" id="reportortype1" style="display: none;" ><spring:message code='report.config.repType' text='报表类型' />:</td>									
					<td class="td-content"  id="reportortype2" style="display: none;">
    	  				<input name="reportortype" id="reportortype" class="mini-combobox miniext-form-fit" textField="text"  style="width:200px;"
	                  	   valueField="value"   required="true"
						   data="[{text:'普通报表',value:'0'},{text:'1104报表',value:'1'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText=""
						   onvaluechanged="oncredittype"/><span class="content-required">*</span>
    	  			</td>         
				</tr>
	             <tr>
					<td class="input_label_desc" style="display: none;" id="classify1"><spring:message code='report.config.repClassify' text='报表分类' />:</td>									
					<td class="td-content" style="display: none;" id="classify2">
    	  				<input name="reportclassify" class="mini-combobox miniext-form-fit" textField="text"  style="width:200px;"
	                  	   valueField="value"  
						   data="[{text:'月报',value:'0'},{text:'季报',value:'1'},{text:'半年报',value:'2'}]"
						   allowInput="false" 
						   showNullItem="true" nullItemText=""
						   emptyText="" />
						   <!-- onvaluechanged="comboboxChanged" -->
						   <span class="content-required">*</span>
    	  			</td>         
				</tr>	
	            <tr>
	                    <td class="input_label_desc" style="text-align: center;"><spring:message code='report.config.chineseName' text='名称' />:</td>
	                <td>
	                    <input name="name" id="name" class="mini-textbox" required="true"  style="width:200px;"  /><span class="content-required">*</span>
	                </td>
	            </tr>
	             <tr>
	                    <td class="input_label_desc" style="text-align: center;"><spring:message code='report.config.englishName' text='英文名称' />:</td>
	                <td>
	                    <input name="enname" id="enname" class="mini-textbox" required="true"  style="width:200px;"  /><span class="content-required">*</span>
	                </td>
	            </tr>
	            <tr>
	                    <td class="input_label_desc" style="text-align: center;"><spring:message code='report.config.code' text='编号' />:</td>
	                <td>
	                    <input name="code" id="code" class="mini-textbox" style="width:200px;"/><span class="content-required">*</span>
	                </td>
	            </tr>
	            <tr>
	                   <td class="input_label_desc" style="text-align: center;"><spring:message code='report.config.type' text='类型' />:</td>
	                <td>
	                    <input name="reportType" id="reportType" readonly="true" class="mini-textbox" style="width:200px;"/><span class="content-required">*</span>
	                </td>
	            </tr>
           
            	<tr>
					<td class="input_label_desc">&nbsp;</td>
					<td class="input_value">
						<table style="width:98%;padding-top: 15px;">
							<tr>	
								<td style="width:40%">&nbsp;</td>								
								<td><a id="report_saveButton" class='mini-button'><span><spring:message code='Confirm' text='确定' /></span></a></td>
								<td><a id="report_closeButton" class='mini-button'><span><spring:message code='Cancel' text='取消' /></span></a></td>									
							</tr>
						</table>
					</td>
				</tr>
			</table>
    	</form>
	</center>
</div>
<script>
var flag;
var falseflag;
function initReportType(reportType,isNew){
	
	flag=reportType;
	falseflag=isNew;
	var reportTypeone=mini.get("reportType").getValue();
	var reportortype=mini.get("reportortype").getValue();
	switch (flag) {
    case "FOLDER":
    	initreportypehide();
        break;
    case "REPORT":
    	 $("#reportortype1").show();
		 $("#reportortype2").show();
        break;
        initreportypehide();
    default:
      }  
	if(flag==''||flag==null){
		falseflag=true;
		switch (reportTypeone) {
	    case "FOLDER":
	    	initreportypehide();
	        break;
	    case "REPORT":
	    	loadreportor(reportortype);
	        break;
	        initreportypehide();
	    default:
	      } 
	}
	if(!falseflag){
		loadreportor(reportortype);
	}
}
function loadreportor(reportortype){
	  if(reportortype=='0'){
			 $("#classify1").hide();
			 $("#classify2").hide();
		  }else{
			 	 $("#reportortype1").show();
				 $("#reportortype2").show();
				 $("#classify1").show();
				 $("#classify2").show();
		  }
}
function initreportypehide(){
	 $("#reportortype1").hide();
	 $("#reportortype2").hide();
	 $("#classify1").hide();
	 $("#classify2").hide();
	
}
function oncredittype(e){
	
 if(e.value=='0'){
	 $("#classify1").hide();
	 $("#classify2").hide();
 }else if(e.value=='1'){
	 $("#classify1").show();
	 $("#classify2").show();
 }else{
	 $("#classify1").hide();
	 $("#classify2").hide();
 }
}
</script>