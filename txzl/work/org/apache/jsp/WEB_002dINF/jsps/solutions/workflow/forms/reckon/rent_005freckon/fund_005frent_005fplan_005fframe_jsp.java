/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:28:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon.rent_005freckon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005frent_005fplan_005fframe_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function importMiniTableCallBack(message,tableid){\r\n");
      out.write("\t var info=eval(\"(\"+message+\")\");\r\n");
      out.write("\t var tabledate=info.tabledate;\r\n");
      out.write("\t mini.alert(info.message);\r\n");
      out.write("\t if(\"\"!=tabledate){\r\n");
      out.write("\t if(tableid==\"proj_equip\"){\r\n");
      out.write("\t\tvar grid=mini.get(tableid);\r\n");
      out.write("\t\tvar oldData = grid.getData();\r\n");
      out.write("\t\tvar tabledate = tabledate.concat(oldData);\r\n");
      out.write("\t\tgrid.set({data:mini.decode(tabledate)});\r\n");
      out.write("\t }else{\r\n");
      out.write("\t \tvar grid=mini.get(tableid);\r\n");
      out.write("\t\tgrid.set({data:mini.decode(tabledate)});\r\n");
      out.write("\t }\r\n");
      out.write("\r\n");
      out.write("\t }\r\n");
      out.write("    mini.get(\"id_minitableimport\").hide();\r\n");
      out.write("    mini.unmask(document.body);\r\n");
      out.write("}\r\n");
      out.write("function loadIrrExcelParams(){\r\n");
      out.write("\t\r\n");
      out.write("\tvar equipamt =  $minigetvalue(\"equipamt\");\r\n");
      out.write("\tif(equipamt){\r\n");
      out.write("\t\tif(Number(equipamt) <= 0 ){\r\n");
      out.write("\t\t\tmini.alert('设备款需大于0！！！');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tmini.alert('设备款不能为空！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar yearrate = $minigetvalue(\"yearrate\");\r\n");
      out.write("\tvar rateRegex = /^[-\\+]?\\d+(\\.[0-9]{1,6})?$/;\r\n");
      out.write("\tif(!rateRegex.test(yearrate)){\r\n");
      out.write("\t\tmini.alert('请你输入正确格式的测算利率！')\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar firstplandate = $minigetvalue(\"firstplandate\");\r\n");
      out.write("\tif(!firstplandate){\r\n");
      out.write("\t\tmini.alert('请先输入第一期租金支付日！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar startdate = $minigetvalue(\"startdate\");\r\n");
      out.write("\tif(!startdate){\r\n");
      out.write("\t\tmini.alert('请先输入起租日！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar o = businessForm.getData(true,true);\r\n");
      out.write("\tvar fields = businessForm.getFields();\r\n");
      out.write("\tvar resultData = {};\r\n");
      out.write("\tfor(var index =0;index<fields.length;index++){\r\n");
      out.write("\t\t //判断是否是下拉框控件，是则同时把text属性设置combobox\r\n");
      out.write("\t\t var fieldValue = o[fields[index].name];\r\n");
      out.write("\t\tif(fields[index].uiCls == \"mini-textbox\"){\r\n");
      out.write("\t\t\tif(fields[index].getInputText().indexOf(',')!=-1){\r\n");
      out.write("\t\t\t\t//所有textbox去掉逗号,\r\n");
      out.write("\t\t\t\tfieldValue = fields[index].getInputText().replace(/,/g,\"\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tresultData[fields[index].name] = fieldValue;\r\n");
      out.write("\t}\r\n");
      out.write("\tresultData.json_fund_fund_charge_str = $('#id_json_fund_fund_charge_str').val();\r\n");
      out.write("\tresultData.json_fund_put_config_str = mini.encode(mini.get('fund_put_config').getData());\r\n");
      out.write("\t//resultData.json_knowing_rent_plan_str = $('#id_json_knowing_rent_plan_str').val();\r\n");
      out.write("\tvar resultDataStr = mini.encode(resultData);\r\n");
      out.write("\tloadIrrExcel(resultDataStr);\r\n");
      out.write("}\r\n");
      out.write("//下载租金计划导入模板\r\n");
      out.write("function loadIrrExcel(params){\r\n");
      out.write("\tupdateIrrExcel(\r\n");
      out.write("\t   \t {\r\n");
      out.write("\t\t\turl:'/leasing/IrrRentCalculate.action',\r\n");
      out.write("\t\t\tmodelname:'模板管理',\r\n");
      out.write("\t\t\ttitle:'不规则租金计划Excel导入',\r\n");
      out.write("\t       \tid : 'irrExcelImportDiv_irr_cal',\r\n");
      out.write("\t        parames:{\r\n");
      out.write("\t        \tconditionitem:params\r\n");
      out.write("\t       \t}\r\n");
      out.write("\t     }\r\n");
      out.write("\t);\r\n");
      out.write("}\r\n");
      out.write("var tools = [];\r\n");
      out.write("var columns = [];\r\n");
      out.write("var toolsIr =  ['add', '-', 'edit', '-','remove','-','exportExcel','-','importExcel','-',{\r\n");
      out.write("\thtml : '租金测算',//自定义按钮的名字\r\n");
      out.write("\tplain : true,//按钮是否有立体感\r\n");
      out.write("\ticonCls : 'icon-save',//按钮的图标\r\n");
      out.write("\thandler : function(miniTable, buttonText) {//按钮响应函数\r\n");
      out.write("\t\tvar fundPlans = miniTable.getData();\r\n");
      out.write("\t\tif(fundPlans.length <= 0 ){\r\n");
      out.write("\t\t\tmini.alert('请先进行租金测算！！！！');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//将租金计划重新赋给隐藏域\r\n");
      out.write("\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\tsave('plan');\r\n");
      out.write("\t }\r\n");
      out.write("\t}\r\n");
      out.write("\t];\r\n");
      out.write("var impExcelTool=[{\r\n");
      out.write("\thtml : '导入租金计划',\r\n");
      out.write("\tplain : true,\r\n");
      out.write("\ticonCls : 'icon-importExcel',\r\n");
      out.write("\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\t//上传模板\r\n");
      out.write("\t\tloadIrrExcelParams();\r\n");
      out.write("\t}\r\n");
      out.write("},'-',\r\n");
      out.write("{\r\n");
      out.write("\thtml : '模板下载',\r\n");
      out.write("\tplain : true,\r\n");
      out.write("\ticonCls : 'icon-importExcel',\r\n");
      out.write("\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\tvar fileTeplate=new fileTemplateConfig({\r\n");
      out.write("       \t templateno:'F-201605001',\r\n");
      out.write("       \t tableid:miniTable.config.id,\r\n");
      out.write("            modelname:miniTable.config.title,\r\n");
      out.write("            returntype:'file',\r\n");
      out.write("            parames:{}\r\n");
      out.write("            });\r\n");
      out.write("      fileTeplate.createFile();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("];    \t\t\t\r\n");
      out.write("var nomalTool = [{\r\n");
      out.write("\thtml : '租金调整',\r\n");
      out.write("\tplain : true,\r\n");
      out.write("\ticonCls : 'icon-redo',\r\n");
      out.write("\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\tvar fundPlans = mini.get('fund_cash_plan_frame').getData();\r\n");
      out.write("\t\tif(fundPlans.length <= 0 ){\r\n");
      out.write("\t\t\tmini.alert('请先进行租金测算！！！！');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//将租金计划重新赋给隐藏域\r\n");
      out.write("\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\tsave('adjust');\r\n");
      out.write("\t}\r\n");
      out.write("}];\r\n");
      out.write("\r\n");
      out.write("var columnsIrr = [\r\n");
      out.write("\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'rentlist', header: '期项',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'plandate', header: '计划日期',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\ttype:'date',\r\n");
      out.write("\t\t\t\t\t\tformat:'yyyy-MM-dd',\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'rent', header: '租金',dataType : \"currency\",summary : true,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'corpus', header: '业务本金',dataType : \"currency\",summary : true,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200,\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onblur=\"caiwubenjin1()\"'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'interest', header: '业务利息',dataType : \"currency\",summary : true,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200,\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onblur=\"caiwubenjin2()\"'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'corpusbusiness', header: '业务本金',dataType : \"currency\",summary : true,visible:false,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("\t\t\t\t\t\twidth: 200,\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive1()\"'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'interestbusiness', header: '业务利息',dataType : \"currency\",summary : true,visible:false,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("\t\t\t\t\t\twidth: 200,\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive2()\"'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'corpusoverage', header: '剩余本金',dataType : \"currency\",summary : true,visible:true,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\tnewLine:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive2()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'rentadjust', header: '租金调整',\r\n");
      out.write("        \t\t\tformEditorConfig:\r\n");
      out.write("        \t\t\t{\r\n");
      out.write("        \t\t\t\trequired: false,\r\n");
      out.write("        \t\t\t\tlabelWidth:100,\r\n");
      out.write("        \t\t\t\treadOnly:true,\r\n");
      out.write("        \t\t\t\twidth: 200\r\n");
      out.write("        \t\t\t},\r\n");
      out.write("        \t\t\teditor:{width:'100%',type:'textbox'}\r\n");
      out.write("        \t\t}\r\n");
      out.write("\t\t\t];\r\n");
      out.write("var columnsNomal = [\r\n");
      out.write("  \t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("  \t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("  \t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("  \t\t\t\t{field: 'rentlist', header: '期项',\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\treadOnly:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\twidth: 200\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'plandate', header: '计划日期',\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\ttype:'date',\r\n");
      out.write("  \t\t\t\t\t\tformat:'yyyy-MM-dd',\r\n");
      out.write("  \t\t\t\t\t\twidth: 200\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'rent', header: '租金',dataType : \"currency\",summary : true,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\tnewLine:true,\r\n");
      out.write("  \t\t\t\t\t\treadOnly:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\t\r\n");
      out.write("  \t\t\t\t\t\twidth: 200\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'corpus', header: '财务本金',dataType : \"currency\",summary : true,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\t\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"caiwubenjin1()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'interest', header: '财务利息',dataType : \"currency\",summary : true,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\tnewLine:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"caiwubenjin2()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'corpusbusiness', header: '业务本金',dataType : \"currency\",summary : true,visible:false,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive1()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'interestbusiness', header: '业务利息',dataType : \"currency\",summary : true,visible:false,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\tnewLine:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive2()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'corpusoverage', header: '剩余本金',dataType : \"currency\",summary : true,visible:true,\r\n");
      out.write("  \t\t\t\t\tformEditorConfig:\r\n");
      out.write("  \t\t\t\t\t{\r\n");
      out.write("  \t\t\t\t\t\tnewLine:true,\r\n");
      out.write("  \t\t\t\t\t\trequired: true,\r\n");
      out.write("  \t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("  \t\t\t\t\t\tvtype:\"float\",\r\n");
      out.write("  \t\t\t\t\t\twidth: 200,\r\n");
      out.write("  \t\t\t\t\t\totherAttributes:'onblur=\"adjustPlanPrive2()\"'\r\n");
      out.write("  \t\t\t\t\t}\r\n");
      out.write("  \t\t\t\t},\r\n");
      out.write("  \t\t\t\t{field: 'rentadjust', header: '租金调整',\r\n");
      out.write("          \t\t\tformEditorConfig:\r\n");
      out.write("          \t\t\t{\r\n");
      out.write("          \t\t\t\trequired: false,\r\n");
      out.write("          \t\t\t\tlabelWidth:100,\r\n");
      out.write("          \t\t\t\treadOnly:true,\r\n");
      out.write("          \t\t\t\twidth: 200\r\n");
      out.write("          \t\t\t}\r\n");
      out.write("          \t\t}\r\n");
      out.write("  \t\t\t];\t\t\r\n");
      out.write("if(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty requestScope['settlemethod'] ? 'even_rent' : requestScope['settlemethod'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" == 'irregular_rent'){\r\n");
      out.write("\ttools = nomalTool;\r\n");
      out.write("\tcolumns =  columnsIrr;\r\n");
      out.write("}else if('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['settlemethod']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'=='excel_import'){\r\n");
      out.write("\ttools=impExcelTool;\r\n");
      out.write("\tcolumns =  columnsNomal;\r\n");
      out.write("}else{\r\n");
      out.write("\tcolumns = columnsNomal;\r\n");
      out.write("}\t\t\r\n");
      out.write("var showTools = true;\r\n");
      out.write("if('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask == true){showTools = false};\r\n");
      out.write("var config =  {\r\n");
      out.write("\tid: \"fund_rent_plan_frame\",\r\n");
      out.write("\trenderTo: \"id_fund_rent_plan_frame\",\r\n");
      out.write("\twidth: globalClientWidth - 30,\r\n");
      out.write("\theight: 700,\r\n");
      out.write("\tlazyLoad: false,\r\n");
      out.write("\tisClickLoad:false,\r\n");
      out.write("\tremoteOper : false,\r\n");
      out.write("\tshowPager: false,\r\n");
      out.write("\tmultiSelect: true,\r\n");
      out.write("\tshowToolbar: showTools,\r\n");
      out.write("\teditFormPopupWindowWidth : 700,\r\n");
      out.write("\timportTargetClass:'',//导入EXCEL对应的类\r\n");
      out.write("\timportOrExPortCallBack:'',//导入回调方法\r\n");
      out.write("\timportHeaderIndex:'3',//标题行\r\n");
      out.write("\timportDataIndex:'4',//数据行\r\n");
      out.write("\tonenter:function(){\r\n");
      out.write("\t\tmini.get(\"fund_rent_plan_frame\").commitEdit();//提交编辑\r\n");
      out.write("\t},\r\n");
      out.write("\tallowCellEdit: true,\r\n");
      out.write("\tallowCellSelect: true,\r\n");
      out.write("\toncelldblclick: function(e){\r\n");
      out.write("\t\tvar miniTable = e.sender;\r\n");
      out.write("\t\tvar rowData   = e.record;\r\n");
      out.write("\t\t//miniTable.commitEdit();\r\n");
      out.write("\t\tminiTable.beginEditCell();\r\n");
      out.write("\t},\r\n");
      out.write("\ttools: tools, \r\n");
      out.write("\tdata: mini.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_fund_rent_plan_str ? \"[]\" : json_fund_rent_plan_str	 }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\tcompleteCallBack:function(miniTable){\r\n");
      out.write("\t\tif(mini.get(\"cleancreditmoney\").getValue()=='0'||mini.get(\"cleancreditmoney\").getValue()==''){\r\n");
      out.write("\t\t\tcleancreditmoney();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t},\r\n");
      out.write("\tcolumns:columns,\r\n");
      out.write("\tafterShowWindowCallBack:function(miniTable,miniForm,operFlag){\r\n");
      out.write("\t\tvar datas = miniTable.getData();\r\n");
      out.write("\t\tif(\"add\" == operFlag){\r\n");
      out.write("\t\t\tvar qixiang=datas[datas.length-1].rentlist;\r\n");
      out.write("\t\t\tmini.getbyName(\"rentlist\").setValue(parseInt(qixiang)+1);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t},\r\n");
      out.write("\tconfirmRemoveCallBack:function(miniTable){\r\n");
      out.write("\t\tvar datas = miniTable.getData();\r\n");
      out.write("\t\tvar selectedRowDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\tvar qixiang=datas[datas.length-1].rentlist;\r\n");
      out.write("\t\tvar strmax=0;\r\n");
      out.write("\t\tvar strmin=selectedRowDatas[0].rentlist;\r\n");
      out.write("\t\tfor(var i=0;i<selectedRowDatas.length;i++){\r\n");
      out.write("\t\t\tif(selectedRowDatas[i].rentlist>=strmax){\r\n");
      out.write("\t\t\t\tstrmax=selectedRowDatas[i].rentlist;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(selectedRowDatas[i].rentlist<=strmin){\r\n");
      out.write("\t\t\t\tstrmin=selectedRowDatas[i].rentlist;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(strmax!=qixiang){\r\n");
      out.write("\t\t\talert(\"租金计划应该从最后一项开始删除，请重新选择！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}if(parseInt(strmax)-parseInt(strmin)+1!=selectedRowDatas.length){\r\n");
      out.write("\t\t\talert(\"租金计划应该从最后一项开始删除，期项必须是连续的，请重新选择！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t},\r\n");
      out.write("\tvalidateForm: function(miniTable, miniForm){\r\n");
      out.write("\t\tvar cwbj=Number(mini.getbyName(\"corpus\").getValue());\r\n");
      out.write("\t\tvar cwlx=parseFloat(mini.getbyName(\"interest\").getValue());\r\n");
      out.write("\t\tvar ywbj=parseFloat(mini.getbyName(\"corpusbusiness\").getValue());\r\n");
      out.write("\t\tvar ywlx=parseFloat(mini.getbyName(\"interestbusiness\").getValue());\r\n");
      out.write("\t\tif(ywbj+ywlx>cwbj+cwlx){\r\n");
      out.write("\t\t\talert(\"业务本金与业务利息之和不能大于租金!\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tmini.getbyName(\"rent\").setValue(decimal(Number(cwbj+cwlx),2));\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t},\r\n");
      out.write("};\r\n");
      out.write("var tempColume = mini.clone(config);\r\n");
      out.write("var tempColume2 =  mini.clone(config);\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable(config);\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function adjustPlanPrive1(){\r\n");
      out.write("\tvar cwbj=parseFloat(mini.getbyName(\"corpus\").getValue());\r\n");
      out.write("\tvar cwlx=parseFloat(mini.getbyName(\"interest\").getValue());\r\n");
      out.write("\tvar v3=mini.getbyName(\"corpusbusiness\").getValue();\r\n");
      out.write("\tif(v3==''||! $.isNumeric(v3)){\r\n");
      out.write("\t\tv3=0;\r\n");
      out.write("\t\tmini.getbyName(\"corpusbusiness\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar ywbj=parseFloat(v3);\r\n");
      out.write("\tvar v4=mini.getbyName(\"interestbusiness\").getValue();\r\n");
      out.write("\tif(v4==''||! $.isNumeric(v4)){\r\n");
      out.write("\t\tv4=0;\r\n");
      out.write("\t\t//mini.getbyName(\"interestbusiness\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar ywlx=parseFloat(v4);\r\n");
      out.write("\tif(ywbj+ywlx>cwbj+cwlx){\r\n");
      out.write("\t\talert(\"本次业务本金与业务利息之和不能大于租金!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function adjustPlanPrive2(){\r\n");
      out.write("\tvar cwbj=parseFloat(mini.getbyName(\"corpus\").getValue());\r\n");
      out.write("\tvar cwlx=parseFloat(mini.getbyName(\"interest\").getValue());\r\n");
      out.write("\tvar v3=mini.getbyName(\"corpusbusiness\").getValue();\r\n");
      out.write("\tif(v3==''||! $.isNumeric(v3)){\r\n");
      out.write("\t\tv3=0;\r\n");
      out.write("\t\t//mini.getbyName(\"corpusbusiness\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar ywbj=parseFloat(v3);\r\n");
      out.write("\tvar v4=mini.getbyName(\"interestbusiness\").getValue();\r\n");
      out.write("\tif(v4==''||! $.isNumeric(v4)){\r\n");
      out.write("\t\tv4=0;\r\n");
      out.write("\t\tmini.getbyName(\"interestbusiness\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar ywlx=parseFloat(v4);\r\n");
      out.write("\tif(ywbj+ywlx>cwbj+cwlx){\r\n");
      out.write("\t\talert(\"本次业务本金与业务利息之和不能大于租金!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function caiwubenjin1(){\r\n");
      out.write("\tvar v1=mini.getbyName(\"corpus\").getValue();\r\n");
      out.write("\tif(v1==''||! $.isNumeric(v1)){\r\n");
      out.write("\t\tv1=0;\r\n");
      out.write("\t\tmini.getbyName(\"corpus\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar cwbj=parseFloat(v1);\r\n");
      out.write("\tvar v2=mini.getbyName(\"interest\").getValue();\r\n");
      out.write("\tif(v2==''||! $.isNumeric(v2)){\r\n");
      out.write("\t\tv2=0;\r\n");
      out.write("\t\t//mini.getbyName(\"interest\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar cwlx=parseFloat(v2);\r\n");
      out.write("\tmini.getbyName(\"rent\").setValue(cwbj+cwlx);\r\n");
      out.write("}\r\n");
      out.write("function caiwubenjin2(){\r\n");
      out.write("\tvar v1=mini.getbyName(\"corpus\").getValue();\r\n");
      out.write("\tif(v1==''||! $.isNumeric(v1)){\r\n");
      out.write("\t\tv1=0;\r\n");
      out.write("\t//\tmini.getbyName(\"corpus\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar cwbj=parseFloat(v1);\r\n");
      out.write("\tvar v2=mini.getbyName(\"interest\").getValue();\r\n");
      out.write("\tif(v2==''||! $.isNumeric(v2)){\r\n");
      out.write("\t\tv2=0;\r\n");
      out.write("\t\tmini.getbyName(\"interest\").setValue(0);\r\n");
      out.write("\t}\r\n");
      out.write("\tvar cwlx=parseFloat(v2);\r\n");
      out.write("\tmini.getbyName(\"rent\").setValue(cwbj+cwlx);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_fund_rent_plan_frame\"></div>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
