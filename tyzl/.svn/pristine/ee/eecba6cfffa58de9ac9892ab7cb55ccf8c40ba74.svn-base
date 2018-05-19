<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<form id="page_form" class="mini-form" method="post">
	<div style="display:none">
	 	<input type="text" class="mini-textbox" name="page_id"  id="page_id"/>
        <input type="text" class="mini-textbox" name="page_layoutId" id="page_layoutId" />        
    </div>
    <div id="page_tabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%" plain="false" borderStyle="border:1px solid #ddd" bodyStyle="border:1px solid #ddd;height:100%" headerStyle="border:1px soldi #ddd">
    	<div title="<s:message code='report.config.form.page.basic' text='基本' />" class="form_tab">
    		<table style="width:90%">
				<tr>
					<td class="input_label_desc">名称:</td>
					<td class="input_value">
						<input id="page_name" name="page_name" require="true"  type="text" class="mini-textbox" /><span class="content-required">*</span>
					</td>
				</tr>
				<tr>
					<td class="input_label_desc">英文名称:</td>
					<td class="input_value">
						<input id="page_enname" name="page_enname" require="true"  type="text" class="mini-textbox" /><span class="content-required">*</span>
					</td>
				</tr>
				<tr>
					<td class="input_label_desc">链接:</td>
					<td class="input_value"><input id="page_url" name="page_url" require="true"  type="text" class="mini-textbox"/><span class="content-required">*</span></td>
				</tr>	
				<tr id="tr_layout_width" style="display:none">
					<td class="input_label_desc">页面宽度:</td>
					<td class="input_value"><input type="text"  name="page_layout_width" id="page_layout_width" class="mini-textbox" value="600"/><span class="content-required">px</span></td>
				</tr>
				<tr id="tr_layout_height" style="display:none">
					<td class="input_label_desc">页面高度:</td>
					<td class="input_value"><input type="text"  name="page_layout_height" id="page_layout_height" class="mini-textbox" value="300"/><span class="content-required">px</span></td>
				</tr>			
			</table>
    	</div>
    </div>
</form>
