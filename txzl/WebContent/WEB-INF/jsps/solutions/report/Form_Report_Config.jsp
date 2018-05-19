<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="/WEB-INF/tlds/spring.tld"%>
<div id="id_form_report" class="mini-window" modal="true" title="<spring:message code='report.config.manageReport' text='报表管理' />" style="width: 450px">
	<center>
		<form id="form_report">
        	<table style="width:90%">
        		<tr>
        			<td style="display: none;">
        				<input name="id" id="id"  class="mini-textbox"/>
        				<input name="position" id="position" class="mini-textbox" />
						<input name="parentReport" id="parentReport" class="mini-textbox" />
						<input name="reportMenuRoot" id="reportMenuRoot" class="mini-textbox" value="${param.reportMenuRoot}"/>
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