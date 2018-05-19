<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<style type="text/css">
.mini-textarea textarea {
	font-size:13px;
	line-height:1.5;
	font-family:Monaco, 'Lucida Console', monospace, Consolas, Courier, 'Microsoft YaHei';
}
</style>
<form id="table_form" class="mini-form" method="post">
	<div style="display:none">
	 	<input type="text" class="mini-textbox" name="table_id"  id="table_id"/>
        <input type="text" class="mini-textbox" name="table_layoutId" id="table_layoutId" />        
    </div>
    <div id="table_tabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%" plain="false" borderStyle="border:1px solid #ddd" bodyStyle="border:1px solid #ddd;height:100%" headerStyle="border:1px soldi #ddd">
    	<div title="<s:message code='report.config.form.table.basic' text='基本' />" class="form_tab">
    		<table style="width:100%">
				<tr>
					<td class="input_label_desc"><span><s:message code='report.config.chineseName' text='名称' />:</span></td>
					<td class="input_value">
						<input type="text" class="mini-textbox" required="true" name="table_name" id="table_name" value=""/><span class="content-required">*</span>
					</td>
					<td class="input_label_desc"><s:message code='report.config.englishName' text='英文名称' />:</td>									
					<td class="td-content">						
    	  				<input type="text" class="mini-textbox" required="true" name="table_enname" id="table_enname" value=""/><span class="content-required">*</span>
    	  			</td>         
				</tr>
				<tr>
					<td class="input_label_desc"><s:message code='report.config.form.table.datasource' text='数据源' />:</td>									
					<td class="td-content" colspan="3">						
    	  				<input id="table_datasource" name="table_datasource" style="display:inline-block" class="mini-combobox" />
    	  			</td>         
				</tr>
				
				<tr id="tr_table_layout_width" style="display:none">
					<td class="input_label_desc"><s:message code='report.config.form.table.pageWidth' text='页面宽度' />:</td>
					<td class="input_value">
						<input type="text" class="mini-textbox" name="table_layout_width" id="table_layout_width" value="600"/><span class="content-required">px</span>
					</td>
				</tr>
				<tr id="tr_table_layout_height" style="display:none">
					<td class="input_label_desc"><s:message code='report.config.form.table.pageHeight' text='页面高度' />:</td>
					<td class="input_value">
						<input type="text" class="mini-textbox"  name="table_layout_height" id="table_layout_height" value="300"/><span class="content-required">px</span>
					</td>
				</tr>
				
				<tr>
					<td class="input_label_desc"><s:message code='report.config.form.table.pageSize' text='每页显示记录数' />:</td>
					<td class="input_value">
						<input type="text" class="mini-textbox" name="table_pageSize" id="table_pageSize" value="20"/><span class="content-required">条</span>
					</td>
					<td class="input_label_desc"><s:message code='report.config.form.table.isExpand' text='按比例扩展列' />:</td>
					<td class="td-content">						
    	  				<input id="table_isScale" name="table_isScale" style="display:inline-block" class="mini-combobox" />
    	  			</td>  
				</tr>
				
										
				
				<tr>
					<td class="input_label_desc"><s:message code='report.config.form.table.sqlType' text='SQL类型' />:</td>									
					<td class="td-content">
    	  				<input id="table_sqlType" name="table_sqlType" style="display:inline-block" class="mini-combobox"/><span class="content-required">*</span>
    	  			</td>         
				 	<td class="input_label_desc"><s:message code='report.config.form.table.sqlParams' text='SQL参数值' />:</td>
				 	<td class="input_value">
				 		<input id="table_sqlParamValue" name="table_sqlParamValue" class="mini-textbox"  />
						<span class="content-required"><s:message code='report.config.form.table.paramsDes' text='使用"|"分隔参数' /></span>
					</td>
				</tr>	
				<tr>
				 	<td class="input_label_desc">SQL:</td>
				 	<td class="input_value" colspan="3">
				 		<textarea id="table_sql" name="table_sql" class="mini-textarea" style="width:100%;height:200px;"></textarea> 
				 	</td>
				</tr>	
				<tr>
					<td colspan="4">
						SQL说明：如果需要在SQL中使用参数，其格式应类似select * from table where id={id},<br/>
						对于日期区间或日期的参数，其格式为 select * from table where d {date}，并在预查询中设置该参数类型为日期。 <br/>
						权限控制参数： _TENWA_USERID, 用于替换当前用户ID
					</td>
				</tr>
				<tr>
					<td class="input_label_desc"><s:message code='report.config.form.table.isShowRowNumber' text='显示行序列' />:</td>
					<td class="td-content">
						<input id="table_showRowNumber" name="table_showRowNumber" style="display:inline-block" class="mini-combobox" />
    	  			</td>    
					<td class="input_label_desc"><s:message code='report.config.form.table.isShowTotalTitle' text='显示统计标题列' />:</td>
					<td class="td-content">						
    	  				<input id="table_showTotalTitle" name="table_showTotalTitle" style="display:inline-block" class="mini-combobox"  />    	  				
    	  			</td>    
				</tr>
				<tr>
					<td class="input_label_desc"><s:message code='report.config.form.table.isCache' text='是否缓存' />:</td>
					<td class="td-content">
						<input id="table_isCache" name="table_isCache" style="display:inline-block" class="mini-combobox" />
    	  			</td> 
    	  				
    	  			<td class="input_label_desc"><s:message code='report.config.form.table.isExcel' text='是否控制EXCEL导出' />:</td>
					<td class="td-content">
						<input id="table_isExcel" name="table_isExcel" style="display:inline-block" class="mini-combobox" />
    	  			</td>    
					
				</tr>
				<tr id="tr_table_controlPerson">
						<td class="input_label_desc"><s:message code='report.config.form.table.exportPersons' text='指定报表导出人员' />：</td>
						<td class="td-content" colspan="2">
							<input id="table_controlPersonName" style="width: 500px;height: 150px" name="table_controlPersonName" allowInput="false" emptyText="请选择具有导出权限的人员"  class="mini-textarea" />
							<input id="table_controlPerson"  name="table_controlPerson"  class="mini-hidden" />
						</td>
						<td>
							<a id="controlPersonButton" class="mini-button" iconCls="icon-search">选择人员</a>
						</td>
				</tr>
				<tr>
					<td colspan="4" style="padding-top:20px">
						<a class="mini-button" id="table_validateSQL"><s:message code='report.config.form.table.validateSql' text='验证SQL' /></a>
					</td>
				</tr>								
			</table>
    	</div>
    	<div title="<s:message code='report.config.form.column.columnConfig' text='列配置' />" class="form_tab">
    		<div style="display:none">
    			<input type="text" class="mini-TextArea" id="table_selectedColumns" name="table_selectedColumns" value="">
    		</div>
    		<div id="table_column_layout" class="mini-layout" style="width:100%;height:100%">
    			<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="table_column_tree" class="mini-tree"></div>
    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				<div id="table_column_form">
    					<div style="display:none">
    						<input hidden="true" class="mini-textbox" id="table_columnNodeId" name="table_columnNodeId" value="">
    						<input type="text" class="mini-hidden" id="table_columnId" name="table_columnId" value="" />
    					</div>
    					<table>
                            <tr> 
                                <td class="input_label_desc"><s:message code='report.config.form.column.columnName' text='列名' />:</td>
                                <td class="input_value">
                                	<input type="text" class="mini-textbox"  name="table_columnName" id="table_columnName" readonly="true"/>
                                </td>
                            </tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.Header' text='标题' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox" name="table_columnLabel" id="table_columnLabel" value=""/>
								</td>
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.englishHeader' text='英文标题' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox" name="table_columnEnLabel" id="table_columnEnLabel" value=""/>
								</td>
							</tr>			
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.styleType' text='格式类型' />:</td>									
								<td class="td-content">									
	            	  				<input id="table_columnType" name="table_columnType" class="mini-combobox"  />
	            	  			</td>         
							</tr>
							
							<tr id="tr_table_columnformater" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.style' text='格式' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox" name="table_columnFormater" id="table_columnFormater" value=""/>							
								</td>
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.columnWidth' text='列宽' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox" name="table_columnWidth" id="table_columnWidth" value="100"/>
									<span class="content-required">px</span>
								</td>
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.visible' text='可见' />:</td>
								<td class="td-content">
									<input id="table_columnVisible" name="table_columnVisible" class="mini-combobox"  />	     
	            	  			</td>         
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.align' text='对齐' />:</td>
								<td class="td-content">
									<input id="table_columnAlign" name="table_columnAlign" class="mini-combobox"  />	     
	            	  			</td>         
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.columnMerge' text='同值合并' />:</td>  
								<td class="td-content">
									<input id="table_columnMerge" name="table_columnMerge" class="mini-combobox"  />
	            	  			</td>         
							</tr>
							<tr>
								<td class="input_label_desc" ><s:message code='report.config.form.column.columnGroupby' text='分组依据' />:</td>									
								<td class="td-content">									
									<input id="table_columnGroupby" name="table_columnGroupby" class="mini-combobox"  />	            	  				
	            	  			</td>         
							</tr>
						
							<tr id="tr_table_subTotal" style="display:none">
								<td class="input_label_desc" ><s:message code='report.config.form.column.SubTotal' text='小计' />:</td>									
								<td class="td-content">
									<input id="table_columnSubTotal" name="table_columnSubTotal" class="mini-combobox"  />
	            	  			</td>         
							</tr>
							<tr id="tr_table_total" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.Total' text='总计' />:</td>									
								<td class="td-content">
									<input id="table_columnTotal" name="table_columnTotal"	class="mini-combobox"  />
	            	  			</td>         
							</tr>

							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.colour' text='着色' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox" name="table_columnColor" id="table_columnColor" value=""/>
								</td>
							</tr>

							<tr>
								<td class="input_label_desc">&nbsp;</td>
								<td class="input_value">
									说明：JSON格式,"&lt;颜色值&gt;":"&lt;条件表达式&gt;","&lt;颜色值&gt;":"&lt;条件表达式&gt;"，如果要统一着色，使用"&lt;颜色值&gt;":""
									<br/>示例:"#ddd":"{a}+{b}","#eee":"ccc"
								</td>
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.actionType' text='附加链接类型' />:</td>									
								<td class="td-content">
									<input id="table_columnActionType" name="table_columnActionType" class="mini-combobox" showNullItem="true" />
	            	  			</td>         
							</tr>

							<tr id="tr_table_actionurl" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.actionUrl' text='链接目标' />:</td>
								<td class="input_value">
									<input type="text" class="mini-combobox"  name="table_columnActionUrl" id="table_columnActionUrl"/>
								</td>
							</tr>
							
							<tr id="tr_table_actionurl2" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.actionUrl' text='链接目标' />:</td>
								<td class="input_value">
									<input type="text" class="mini-combobox"  name="table_columnActionUrl2" id="table_columnActionUrl2" value=""/>
								</td>
							</tr>
							
							<tr id="tr_table_actionparameter" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.ActionParams' text='链接参数:(多列以|隔开)' /></td>
								<td class="input_value">
									<input type="text" class="mini-textbox"  name="table_columnActionParameter" id="table_columnActionParameter" value=""/>
								</td>
							</tr>
							<tr id="tr_table_actioncondition" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.column.ActionCondition' text='链接条件' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_columnActionCondition" id="table_columnActionCondition" value=""/>
								</td>
							</tr>
							<tr id="tr_table_actionmemo" style="display:none">
								<td class="input_label_desc">&nbsp;</td>
								<td class="input_value">
									说明：若取其它域名，使用{columnName1} + {columnName2} > {columnName3}类似格式
								</td>
							</tr>
						</table>
    				</div>
    			</div>
    		</div>    			
    	</div>
    	<div title="<s:message code='report.config.form.filter'  text='过滤器' />" class="form_tab">
    		<div id="table_filter_layout" class="mini-layout" style="width:100%;height:100%">    			
    			<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="table_filter_tree" class="mini-tree"></div>
    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				<div style="display:none">
    					<input hidden="true" class="mini-textbox" id="table_filterNodeId" name="table_filterNodeId" value="">
	    				<input type="text" class="mini-TextArea" name="table_selectedFilters" id="table_selectedFilters" />
						<input type="text" class="mini-TextArea" name="table_combine_express_filter" id="table_combine_express_filter" />
						<input type="text" class="mini-TextArea" name="table_combine_express_search" id="table_combine_express_search" />
	    			</div>
	    			<div id="table_filter_form">
	    				<div style="display:none">
	    					<input type="text" class="mini-textbox" name="table_filterId" id="table_filterId" />
							<input type="text" class="mini-textbox" name="table_filterFilterType" id="table_filterFilterType" />
						</div>
						<table>
                            <tr> 
                                <td class="input_label_desc"><s:message code='report.config.form.filter.fieldId' text='字段标识' />:</td>
                                <td class="input_value">
                                	<input type="text" class="mini-textbox" name="table_filterName" id="table_filterName" value=""/>	                                	
                                	<span class="content-required">*</span>
                                </td>
                            </tr>
                            <tr> 
                                <td class="input_label_desc"><s:message code='report.config.form.filter.fieldEnglishName' text='英文字段标识' />:</td>
                                <td class="input_value">
                                	<input type="text" class="mini-textbox" name="table_filterEnname" id="table_filterEnname" value=""/>	                                	
                                	<span class="content-required">*</span>
                                </td>
                            </tr>
                            <tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                	与列标识或SQL参数相同，若是SQL参数，形式{param}，此处填写param,<br />
                                	如果是多表查询，此处参数应附加表名。
                                	</span>
                                </td>
                            </tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.filter.fieldShow' text='字段显示' />:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="table_filterLabel" id="table_filterLabel" value=""/>
									<span class="content-required">*</span>
								</td>
							</tr>
							 <tr>
								<td class="input_label_desc"><s:message code='report.config.form.filter.DbType' text='数据类型' />:</td>
								<td class="td-content">	
									<input id="table_filterDbType" name="table_filterDbType" style="display:inline-block" class="mini-combobox"  />		            	  				
	            	  			</td>   
								
							</tr>
							<tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                		数据库中的类型，用于当数据库类型与格式类型不一致时，调用转换函数时使用，如VARCHAR，选择“文本”。
                                	</span>
                                </td>
                            </tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.column.styleType' text='格式类型' />:</td>
								<td class="td-content">	
									<input id="table_filterHtmlType" name="table_filterHtmlType" style="display:inline-block" class="mini-combobox"  />		            	  				
	            	  			</td>   
							</tr>
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.filter.expression' text='表达式' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_filterExpress" id="table_filterExpress" />
								</td>
							</tr>

							<tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                		应用于Where条件时，默认对于文本和下拉列表使用like '%&lt;value&gt;%'，<br/>对于日期和日期区间使用between...and...<br/>
                                		如有特殊需要，在此处输入相应的表达式，如='{字段标识}'
                                	</span>
                                </td>
                            </tr>

                            <tr>
								<td class="input_label_desc"><s:message code='report.config.form.filter.defaultValue' text='默认值' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_filterDefaultValue" id="table_filterDefaultValue" />
								</td>
							</tr>
							<tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">                                		
                                		多值按顺序使用"|"分隔
                                	</span>
                                </td>
                            </tr>

							<tr id="tr_table_combobox_datasource1" style="display:none;width:196px;" >
								<td class="input_label_desc"><s:message code='report.config.form.filter.requestType' text='数据请求类型' />:</td>
								<td class="td-content">	
									<input id="table_filterFilterDataRequestType" name="table_filterFilterDataRequestType" class="mini-combobox"  />
	            	  			</td>   
								
							</tr>		
							<tr id="tr_table_combobox_datasource2" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.filter.requestSource' text='数据请求来源' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_filterComboboxDataSource" id="table_filterComboboxDataSource" />
								</td>
							</tr>	
							<tr id="tr_table_combobox_datasource3" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.filter.dataShowField' text='数据显示字段' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_filterComboboxNameField" id="table_filterComboboxNameField" value=""/>
																
								</td>
							</tr>			
							
							<tr id="tr_table_combobox_datasource4" style="display:none">
								<td class="input_label_desc"><s:message code='report.config.form.filter.dataValueField' text='数据值字段' />:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="table_filterComboboxValueField" id="table_filterComboboxValueField" value=""/>
								</td>
							</tr>								
						</table>
					</div>    				
    			</div>
    		</div>
  		</div>
  		<div title="<s:message code='report.config.form.control' text='权限控制' />" class="form_tab">
    		<div id="table_control_layout" class="mini-layout" style="width:100%;height:100%">    			
    			<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="table_control_tree" class="mini-tree"></div>
    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				 <div style="display:none">
    					<input hidden="true" class="mini-textbox" id="table_controlNodeId" name="table_controlNodeId" value="">
	    				<input type="text" class="mini-TextArea" name="table_selectedControls" id="table_selectedControls" />
						<%--<input type="text" class="mini-TextArea" name="table_combine_express_filter" id="table_combine_express_filter" />
						<input type="text" class="mini-TextArea" name="table_combine_express_search" id="table_combine_express_search" />--%>
	    			</div>
	    			<div id="table_control_form">
	    				<div style="display:none">
	    					<input type="text" class="mini-textbox" name="table_controlId" id="table_controlId" />
						</div>
						<table>
                            
                            
							<tr>
								<td class="input_label_desc"><s:message code='report.config.form.control.field' text='权限字段' />:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="table_controlLabel" id="table_controlLabel" value=""/>
									<span class="content-required">*</span>
								</td>
							</tr>
							<tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                	权限字段：用于报表自动在SQL末尾外层添加where后的条件判断。例：当填写为column_a时，并且该用于属于该群组，
                                	则报表会自动在SQL外转添加 where 1=1 and (column= ?) 注：该字段必须为报表字段列表中存在的字段。
                                	</span>
                                </td>
                            </tr>
                            
                            <tr>
								<td class="input_label_desc"><s:message code='report.config.form.control.controlName' text='权限名称' />:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="table_controlName" id="table_controlName" value=""/>
									<span class="content-required">*</span>
								</td>
							</tr>
                            
                             <tr> 
                                <td class="input_label_desc"><s:message code='report.config.form.control.groupSelected' text='群组选择' />:</td>
                                <td class="input_value">
                                	<input id="table_controlGroup" name="table_controlGroup" style="display:inline-block" class="mini-combobox"/>                	
                                	<span class="content-required">*</span>
                                </td>
                            </tr>
                            <tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                	群组选择用于控制查看该报表的人员可查看的数据范围。例：如果配置为经销商，
                                	当登陆用户属于经销商群组时，则数据自动过滤为经销商层次数据，该经销商只能看到自己数据，否则，能查看到该报表全部数据。
                                	</span>
                                </td>
                            </tr>
                            
                            <tr>
								<td class="input_label_desc"><s:message code='report.config.form.control.matchRelation' text='匹配关系' />:</td>
								<td class="input_value">
								 	<input id="table_controlMatch" name="table_controlMatch" style="display:inline-block" class="mini-combobox"/>    
									<span class="content-required">*</span>
								</td>
							</tr>
							<tr> 
                                <td class="input_label_desc">&nbsp;</td>
                                <td class="input_value">                                	
                                	<span class="content-required" style="color:black">
                                	用于选择“权限字段”所对应条件中的SQL中的？所代表SQL语句。<br />
                                	</span>
                                </td>
                            </tr>
                            			
						</table>
					</div>    				
    			</div>
    		</div>
  		</div>
  	</div>
</form>
<div id="idPersonWindowContainer" class="mini-window" width="800px" heigth="800px" showCloseButton="true" showModal="true" allowDrag="true" title="组织架构" style="display:none;">
	 <table>
	 	<tr>
	 		<td>
		 		<div class="mini-panel" title='选择列表' showCollapseButton="false" style="width: 340px;height: 620px;overflow:hidden;">
		 			 <div id="org_search_div" style="position: fixed;margin-top: -27px;text-align: right;width:330px">
		 			 	<input type="text"   id="org_search_input" style="width: 100px;" />
		 			 	<a id="org_search_button" class="mini-button" iconCls="icon-search" >搜索</a>
		 			 </div>
	        		 <div id="orgTree" style="height: 200px;" bodyStyle="height:100px;"></div>
		 		</div>
	 		</td>
	 		<td>
	 			<div style="width: 80px;height: 620px;overflow:hidden;padding:10px;">
		 			<div style="text-align:center;">
		        		 <a id="personSelect" class="mini-button"  style="text-indent:0px;text-align:center;top:240px;position:relative;cursor:pointer;">>>></a><br>
		 			</div>
	 				<div style="text-align:center;">
	        		 	<a id="personUnSelect" class="mini-button"  style="text-indent:0px;text-align:center;top:300px;position:relative;cursor:pointer;"><<<</a>
		 			</div>
		 		</div>
	 		</td>
	 		<td>
	 			<div class="mini-panel" title="已选列表" showCollapseButton="false" style="width: 340px;height: 620px;overflow:hidden;">
	        		 <div id="orgTreeSelected" style="height: 200px;" bodyStyle="height:100px;"></div>
		 		</div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td colspan="3" align="center">
	 			 <a id="controlConfirm" class="mini-button" iconCls="icon-ok" >确定</a>
	        	 <a id="controlCancel" class="mini-button" iconCls="icon-cancel" >取消</a>
	 		</td>
	 	</tr>
	 </table>
	 
</div>

<!--过滤器右键菜单-->
<div id="table_filter_contextmenu" class="mini-contextmenu">
	<div id="table_filter_contextmenu_add" iconCls="icon-plus-w">新增</div>
	<div id="table_filter_contextmenu_delete" iconCls="icon-plus-w">删除</div>
	<div id="table_filter_contextmenu_express" iconCls="icon-plus-w">组合表达式</div>
</div>
<!--权限控制右键菜单-->
<div id="table_control_contextmenu" class="mini-contextmenu">
	<div id="table_control_contextmenu_add" iconCls="icon-plus-w">新增</div>
	<div id="table_control_contextmenu_delete" iconCls="icon-plus-w">删除</div>
</div>

