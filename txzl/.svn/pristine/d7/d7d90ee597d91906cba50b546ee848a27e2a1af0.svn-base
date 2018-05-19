<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<form id="chart_form" class="mini-form" method="post">
	<div style="display: none">		
		<input type="text" class="mini-textbox" name="chart_id"  id="chart_id"/>
        <input type="text" class="mini-textbox" name="chart_layoutId" id="chart_layoutId" />
    </div>
    <div id="chart_tabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%" plain="false" borderStyle="border:1px solid #ddd" bodyStyle="border:1px solid #ddd;height:100%" headerStyle="border:1px soldi #ddd">
    	<div title="<s:message code='report.config.form.chart.basic' text='基本'/>" class="form_table">
    		<table style="width:100%">
				<tr>
					<td class="input_label_desc"><span>名称:</span></td>
					<td><input  class="mini-textbox" required="true" name="chart_name" id="chart_name"/><span class="content-required">*</span></td>
				</tr>
				
				<tr>
					<td class="input_label_desc">图表类型:</td>									
					<td class="td-content">
    	  				<input id="chart_chartType" name="chart_chartType" class="mini-combobox"/>
    	  			</td>         
				</tr>		

				<tr>
					<td class="input_label_desc">图表:</td>									
					<td class="td-content">
    	  				<input id="chart_chartType2" name="chart_chartType2" class="mini-combobox"/>
    	  			</td>         
				</tr>	

				<tr>
					<td class="input_label_desc"><span>标题:</span></td>
					<td><input   class="mini-textbox" name="chart_caption" id="chart_caption"/></td>
				</tr>

				<tr>
					<td class="input_label_desc"><span>子标题:</span></td>
					<td><input  class="mini-textbox" name="chart_subcaption" id="chart_subcaption"/></td>
				</tr>

				<tr>
					<td class="input_label_desc"><span>X轴标签:</span></td>
					<td><input  class="mini-textbox" name="chart_xAxisName" id="chart_xAxisName"/></td>
				</tr>
				<tr>
					<td class="input_label_desc"><span>Y轴标签:</span></td>
					<td><input  class="mini-textbox" name="chart_yAxisName" id="chart_yAxisName"/></td>
				</tr>

				<tr id="tr_chart_dualy2" style="display:none">
					<td class="input_label_desc"><span>次Y轴标签:</span></td>
					<td><input  class="mini-textbox" name="chart_sAxisName" id="chart_sAxisName"/></td>
				</tr>

				<tr id="tr_layout_width" style="display:none">
					<td class="input_label_desc">页面宽度:</td>
					<td><input name="chart_layout_width" id="chart_layout_width" class="mini-textbox" value="600"/></td>
				</tr>
				<tr id="tr_layout_height" style="display:none">
					<td class="input_label_desc">页面高度:</td>
					<td><input name="chart_layout_height" id="chart_layout_height" class="mini-textbox" value="300"/></td>
				</tr>

				<tr>
					<td class="input_label_desc">数据源:</td>									
					<td class="td-content">
    	  				<input id="chart_datasource" name="chart_datasource" class="mini-combobox"/>
    	  			</td>         
				</tr>						
				
				<tr>
					<td class="input_label_desc">SQL类型:</td>									
					<td class="td-content">
    	  				<input id="chart_sqlType" name="chart_sqlType" class="mini-combobox"/>
    	  			</td>         
				</tr>									
				<tr>
				 	<td class="input_label_desc">SQL:</td>
				 	<td class="input_value"><textarea id="chart_sql" name="chart_sql" class="mini-textarea" style="width:400px;height:200px;"></textarea></td>
				</tr>	
				<tr>
				 	<td class="input_label_desc">SQL参数值:</td>
				 	<td><input class="mini-textbox" id="chart_sqlParamValue" name="chart_sqlParamValue" /><span class="content-required">使用"|"分隔参数</span></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top:20px"><a class="mini-button" id="chart_validateSQL">验证SQL</a></td>
				</tr>								
			</table>
    	</div>
    	<div title="<s:message code='report.config.form.chart.columns' text='列配置' />" class="form_tab">
    		<div style="display:none">
				<input hidden="true" class="mini-textbox" id="chart_selectedColumns" name="chart_selectedColumns" value="">
				<input hidden="true" class="mini-textbox" id="chart_columnNodeId" name="chart_columnNodeId" value="">
			</div>
			<div id="chart_column_layout" class="mini-layout" style="width:100%;height:100%">
				<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="chart_column_tree" class="mini-tree"></div>
    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				<div id="chart_column_form">
    					<div style="display:none">
    						<input type="text" class="mini-hidden" id="chart_columnId" name="chart_columnId" value="" />
    					</div>
    					<table>
	                        <tr> 
	                            <td class="input_label_desc">列名:</td>
	                            <td>
	                            	<input class="mini-textbox" name="chart_columnName" id="chart_columnName" readonly="true"/>	                            	
	                            </td>
	                        </tr>
							<tr>
								<td class="input_label_desc">标题:</td>
								<td>
									<input class="mini-textbox" name="chart_columnLabel" id="chart_columnLabel" value=""/>									
								</td>
							</tr>		
							<tr>
								<td class="input_label_desc">格式类型:</td>									
								<td class="td-content">									
	            	  				<input id="chart_columnType" name="chart_columnType" class="mini-combobox"/>
	            	  			</td>         
							</tr>
							
							<tr id="tr_columnformater" style="display:none">
								<td class="input_label_desc">格式:</td>
								<td>
									<input class="mini-textbox" name="chart_columnFormater" id="chart_columnFormater"/>									
								</td>
							</tr>

							<tr>
								<td class="input_label_desc">数据用途:</td>									
								<td class="td-content">									
	            	  				<input id="chart_columnUsageType" name="chart_columnUsageType" class="mini-combobox"/>
	            	  			</td>         
							</tr>

							<tr>
								<td class="input_label_desc">显示数值:</td>									
								<td class="td-content">
									
	            	  				<input id="chart_columnShowValue" name="chart_columnShowValue" class="mini-combobox"/>
	            	  			</td>         
							</tr>

							<tr id="tr_chart_dualy" style="display:none">
								<td class="input_label_desc">次Y坐标轴:</td>									
								<td class="td-content">									
	            	  				<input id="chart_columnRightY" name="chart_columnRightY" class="mini-combobox"/>
	            	  			</td>         
							</tr>

							<tr id="tr_chart_combichart" style="display:none">
								<td class="input_label_desc">组合图形类型:</td>									
								<td class="td-content">									
	            	  				<input id="chart_columnCombi" name="chart_columnCombi" class="mini-combobox"/>
	            	  			</td>         
							</tr>
							
							<tr>
								<td class="input_label_desc">颜色</td>
								<td>
									<input class="mini-textbox" id="chart_columnColor" name="chart_columnColor" value="" />
								</td>
							</tr>
							
							<tr>
								<td class="input_label_desc">附加链接类型:</td>									
								<td class="td-content">
									<input id="chart_columnActionType" name="chart_columnActionType" class="mini-combobox" showNullItem="true" />
	            	  			</td>         
							</tr>
							<tr id="tr_chart_actionurl" style="display:none">
								<td class="input_label_desc">链接目标:</td>
								<td class="input_value">
									<input type="text" class="mini-combobox"  name="chart_columnActionUrl" id="chart_columnActionUrl"/>
								</td>
							</tr>

							<tr id="tr_chart_actionurl2" style="display:none">
								<td class="input_label_desc">链接目标:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"  name="chart_columnActionUrl2" id="chart_columnActionUrl2" value=""/>
								</td>
							</tr>
							
							<tr id="tr_chart_actionparameter" style="display:none">
								<td class="input_label_desc">链接参数:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"  name="chart_columnActionParameter" id="chart_columnActionParameter" value=""/>
								</td>
							</tr>
							<tr id="tr_chart_actioncondition" style="display:none">
								<td class="input_label_desc">链接条件:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_columnActionCondition" id="chart_columnActionCondition" value=""/>
								</td>
							</tr>
						</table>
    				</div>
    			</div>
			</div>
    	</div>
    	<div title="<s:message code='report.config.form.chart_.filter' text='过滤器' />" class="form_tab">
    		<div id="chart_filter_layout" class="mini-layout" style="width:100%;height:100%">    			
    			<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="chart_filter_tree" class="mini-tree"></div>

    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				<div style="display:none">
    					<input hidden="true" class="mini-textbox" id="chart_filterNodeId" name="chart_filterNodeId" value="">
	    				<input type="text" class="mini-textbox" name="chart_selectedFilters" id="chart_selectedFilters" />
						<input type="text" class="mini-textbox" name="chart_combine_express_filter" id="chart_combine_express_filter" />
						<input type="text" class="mini-textbox" name="chart_combine_express_search" id="chart_combine_express_search" />
	    			</div>
	    			<div id="chart_filter_form">
	    				<div style="display:none">
	    					<input type="text" class="mini-textbox" name="chart_filterId" id="chart_filterId" />
							<input type="text" class="mini-textbox" name="chart_filterFilterType" id="chart_filterFilterType" />
						</div>
						<table>
                            <tr> 
                                <td class="input_label_desc">字段标识:</td>
                                <td class="input_value">
                                	<input type="text" class="mini-textbox" name="chart_filterName" id="chart_filterName" value=""/>	                                	
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
								<td class="input_label_desc">字段显示:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="chart_filterLabel" id="chart_filterLabel" value=""/>
									<span class="content-required">*</span>
								</td>
							</tr>
							 <tr>
								<td class="input_label_desc">数据类型:</td>
								<td class="td-content">	
									<input id="chart_filterDbType" name="chart_filterDbType" style="display:inline-block" class="mini-combobox"  />		            	  				
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
								<td class="input_label_desc">格式类型:</td>
								<td class="td-content">	
									<input id="chart_filterHtmlType" name="chart_filterHtmlType" style="display:inline-block" class="mini-combobox"  />		            	  				
	            	  			</td>   
							</tr>
							<tr>
								<td class="input_label_desc">表达式:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_filterExpress" id="chart_filterExpress" />
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
								<td class="input_label_desc">默认值:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_filterDefaultValue" id="chart_filterDefaultValue" />
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

							<tr id="tr_chart_combobox_datasource1" style="display:none;width:196px;" >
								<td class="input_label_desc">数据请求类型:</td>
								<td class="td-content">	
									<input id="chart_filterFilterDataRequestType" name="chart_filterFilterDataRequestType" class="mini-combobox"  />
	            	  			</td>   
								
							</tr>		
							<tr id="tr_chart_combobox_datasource2" style="display:none">
								<td class="input_label_desc">数据请求来源:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_filterComboboxDataSource" id="chart_filterComboboxDataSource" />
								</td>
							</tr>	
							<tr id="tr_chart_combobox_datasource3" style="display:none">
								<td class="input_label_desc">数据显示字段:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_filterComboboxNameField" id="chart_filterComboboxNameField" value=""/>
																
								</td>
							</tr>			

							<tr id="tr_chart_combobox_datasource4" style="display:none">
								<td class="input_label_desc">数据值字段:</td>
								<td class="input_value">
									<input type="text" class="mini-textbox"   name="chart_filterComboboxValueField" id="chart_filterComboboxValueField" value=""/>
								</td>
							</tr>								
						</table>
					</div>								
    			</div>
    		</div>
    	</div>
    	
    	<div title="权限控制" class="form_tab">
    		<div id="chart_control_layout" class="mini-layout" style="width:100%;height:100%">    			
    			<div region="west" showHeader="false" width="280" style="margin-top:-1px;margin-bottom:-1px;border:0;border-right:1px solid #ddd">
    				<div id="chart_control_tree" class="mini-tree"></div>
    			</div>
    			<div region="center" showHeader="false" style="border:0">
    				 <div style="display:none">
    					<input hidden="true" class="mini-textbox" id="chart_controlNodeId" name="chart_controlNodeId" value="">
	    				<input type="text" class="mini-TextArea" name="chart_selectedControls" id="chart_selectedControls" />
						<%--<input type="text" class="mini-TextArea" name="chart_combine_express_filter" id="chart_combine_express_filter" />
						<input type="text" class="mini-TextArea" name="chart_combine_express_search" id="chart_combine_express_search" />--%>
	    			</div>
	    			<div id="chart_control_form">
	    				<div style="display:none">
	    					<input type="text" class="mini-textbox" name="chart_controlId" id="chart_controlId" />
						</div>
						<table>
                            
                            
							<tr>
								<td class="input_label_desc">权限字段:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="chart_controlLabel" id="chart_controlLabel" value=""/>
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
								<td class="input_label_desc">权限名称:</td>
								<td class="input_value">
								 	<input type="text" class="mini-textbox" name="chart_controlName" id="chart_controlName" value=""/>
									<span class="content-required">*</span>
								</td>
							</tr>
                            
                             <tr> 
                                <td class="input_label_desc">群组选择:</td>
                                <td class="input_value">
                                	<input id="chart_controlGroup" name="chart_controlGroup" style="display:inline-block" class="mini-combobox"/>                	
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
								<td class="input_label_desc">匹配关系:</td>
								<td class="input_value">
								 	<input id="chart_controlMatch" name="chart_controlMatch" style="display:inline-block" class="mini-combobox"/>    
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

<!--过滤器组合表达式配置-->
<div id="chart_filterExpress_container" style="display:none;">
	<table>
		<tr> 
            <td class="input_label_desc">组合表达式:</td>
            <td class="input_value">
            	<input type="text"  name="combineexpress" id="combineexpress" style="width:250px"/>
            	<span class="content-required">*</span>
            </td>
        </tr>
        <tr>
        	<td colspan="2">
        		组合表达式：用于对同一组的查询条件进行组合，默认时，所有条件使用"AND"连接<br />
        		如果需要特定组合，使用格式: {查询条件ID1} AND {查询条件2} OR {查询条件3}
        	</td>
        </tr>
        <tr><td colspan="2"><span style="width:40px">&nbsp;</span></td>
        <tr>			
			<td colspan="2">
				<span style="width:40px">&nbsp;</span>
				<a class="btn btn-primary" onclick="saveCombineExpress('#chart_filterExpress_container','#form_chart');"><span>确定</span></a>
				<span style="width:40px">&nbsp;</span>
				<a class="btn btn-primary" onclick="closeWindow('#chart_filterExpress_container');"><span>取消</span></a>				
			</td>
		</tr>
		 <tr><td colspan="2"><span style="width:40px">&nbsp;</span></td></tr>
	</table>

</div>

<!--过滤器右键菜单-->
<div id="chart_filter_contextmenu" class="mini-contextmenu">
	<div id="chart_filter_contextmenu_add" iconCls="icon-plus-w">新增</div>
	<div id="chart_filter_contextmenu_delete" iconCls="icon-plus-w">删除</div>
	<div id="chart_filter_contextmenu_express" iconCls="icon-plus-w">组合表达式</div>
</div>