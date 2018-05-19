<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<div id="id_freemaker_entity_setting" class="mini-window" title="field" style="display:none;width:700px;height:342px;">
			<table width="100%;" style="table-layout:fixed;">
				<tr>
					<td style="text-align:left;padding-left:20px;height:30px;line-height:30px;border-bottom:1px solid silver;" colspan=4>
						包名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;
						<label style="color:red;font-weight:BOLD;" id="id_activityType"></label>&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			<!-- 标签栏 -->
			<div class="mini-tabs" id="freemaker_entity_column_container" style="height:220px;" >
				<div title="Clomn" style="overflow:hidden;">
					<table  class="fillTable" cellspacing="0" cellpadding="0">
					
						<tr class="tr-project-info tr-even">
							<td class="td-content-title" style="width: 30%">属性名：</td>
							<td class="td-content" style="width: 70%">
								<input name="id" id= "entity_id" class="mini-hidden">
								<input name="entity" id= "entity" class="mini-hidden">
								<input name="fieldname" id= "fieldname" class="mini-textbox" requiredErrorText="属性名不能为空！！！" required="true" style="width: 90%">
							</td>
						</tr>
						<tr class="tr-project-info tr-odd">
							<td class="td-content-title" style="width: 30%">列注释：</td>
							<td class="td-content" style="width: 70%">
								<input name="tablecolumndesc" id="tablecolumndesc" class="mini-textbox" style="width: 90%"   >
							</td>
						</tr>
						<tr class="tr-project-info tr-odd">
							<td class="td-content-title" style="width: 30%">排序：</td>
							<td class="td-content" style="width: 70%">
								<input name="position" id="tablecolumnposition" class="mini-combobox" style="width: 90%"   >
							</td>
						</tr>
						
					</table>
					
				</div>
				<div title="Key" id="freemaker_entity_column_key" style="overflow:hidden;">
					<table  class="fillTable" cellspacing="0" cellpadding="0">
						 <tr class="tr-project-info tr-odd">	
							<td class="td-content-title">约束类型：</td>
							<td class="td-content">
								<input name="tablecolumntype" class="mini-combobox"   id ="tablecolumntype" >
							</td>
							<td class="td-content-title">长度：</td>
							<td class="td-content">
								<input name="tablecolumnlength" class="mini-textbox" vtype="int" intErrorText="长度必须为整数！！！" id ="tablecolumnlength" >
							</td>
						</tr>
						<tr class="tr-project-info tr-even">
							<td class="td-content-title">fetch：</td>
							<td class="td-content" >
								<input name="fetchtype" class="mini-combobox"   id ="fetchtype" >
							</td>
							<td class="td-content" colspan="2">
								<font color="red">外键或者一对多，一对一配置生效！！！</font>
							</td>
						</tr>
						</tr>
						<tr class="tr-project-info tr-odd">
							<td class="td-content-title">列名：</td>
							<td class="td-content">
								<input name="tablecolumnname" class="mini-textbox" required="true" requiredErrorText="列名不能为空！！！"  id ="tablecolumnname" >
							</td>
							<td class="td-content-title">属性类型：</td>
							<td class="td-content">
								<input name="fieldtype" id= "fieldtype" class="mini-hidden">
								<input name="fieldtypefullname" class="mini-combobox"  required="true" requiredErrorText="属性类型不能为空！！！" id ="fieldtypefullname" >
							</td>
						 <tr>
						 
						<tr class="tr-project-info tr-even"  id="bigdecimal_config_tr">
							<td class="td-content-title">精度：</td>
							<td class="td-content">
								<input name="tablecolumnprecision" class="mini-textbox" vtype="int" intErrorText="长度必须为整数！！！"   id ="tablecolumnprecision" >
							</td>
							<td class="td-content-title">位数：</td>
							<td class="td-content">
								<input name="tablecolumnscale" class="mini-textbox" vtype="int" intErrorText="长度必须为整数！！！"    id ="tablecolumnscale" >
							</td>
						 <tr>
						 
						
						<tr  class="tr-project-info tr-odd">
							<td class="td-content-title">是否索引：</td>
							<td class="td-content">
								<input name="tableisindex" class="mini-combobox"   id ="tableisindex" >
							</td>
							<td class="td-content-title">索引名：</td>
							<td class="td-content">
								<input name="tableindexname" class="mini-textbox"   id ="tableindexname" >
							</td>
						</tr>
						<tr  class="tr-project-info tr-even">
							<td class="td-content-title">非空约束：</td>
							<td class="td-content">
								<input name="tableisnotnull" class="mini-combobox"   id ="tableisnotnull" >
							</td>
							<td class="td-content-title">唯一性约束：</td>
							<td class="td-content">
								<input name="tableisunique" class="mini-combobox"   id ="tableisunique" >
							</td>
						</tr>
						<tr class="tr-project-info tr-odd">
							<td class="td-content-title">排序：</td>
							<td class="td-content" >
								<input name="columnorderby" class="mini-textbox"   id ="columnorderby" >
							</td>
							<td class="td-content" colspan="2">
								<font color="red">只有one-to-many的时候用的上，语法一定要准确！！！</font>
							</td>
						</tr>
						<tr class="tr-project-info tr-even">
							<td class="td-content-title">其他属性：</td>
							<td class="td-content" >
								<input name="tablecolumndefinition" class="mini-textbox"   id ="tablecolumndefinition" >
							</td>
							<td class="td-content" colspan="2">
								<font color="red">其他属性，例如默认值，直接写在生成标的ddl中，因此语法必须正确</font>
							</td>
						</tr>
					</table>
				</div>
				
			</div>
		<table width="100%;">
			<tr style="text-align:center;">
				<td style="padding: 10px;">
					<a class="mini-button" id="config_column_save_button">保存</a>
					<a class="mini-button" onclick="__saveChangeToActivityClose">取消</a>
				</td>
			</tr>
		</table>
	</div>