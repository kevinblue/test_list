/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-24 04:35:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fplancharg;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fplancharg_005fcollect_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("jQuery(function(){\r\n");
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"plancharg_collect\",\r\n");
      out.write("\t\trenderTo: \"id_table_plancharg_collect\",\r\n");
      out.write("\t\twidth : '100%',\r\n");
      out.write("\t\theight :'100%',\r\n");
      out.write("\t\teditFormPopupWindowWidth : 400,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['add','-','edit','-','remove'],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_plancharg_collect_str ? \"[]\" : json_plancharg_collect_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\toperValidate:function(miniTable, rowDatas, operFlag){\r\n");
      out.write("              if(operFlag==\"edit\" || operFlag==\"remove\"){\r\n");
      out.write("            \t  if(!rowDatas || rowDatas.length==0){\r\n");
      out.write("            \t\t  mini.alert(\"请选择要\"+(operFlag == \"edit\"?\"修改\":\"删除\")+\"的数据!\");\r\n");
      out.write("            \t\t  return false;\r\n");
      out.write("            \t  }\r\n");
      out.write("                  var  factmoney=0.00;\r\n");
      out.write("                  if(operFlag==\"edit\"){factmoney=rowDatas.factmoney;}else{factmoney=rowDatas[0].factmoney;}\r\n");
      out.write("                  if(parseFloat(factmoney)>0){\r\n");
      out.write("                        mini.alert(\"已经收款过的资金计划不能修改或删除\");\r\n");
      out.write("                        return false;\r\n");
      out.write("                      }\r\n");
      out.write("               }\r\n");
      out.write("\t\t\t   return true;\r\n");
      out.write("\t   },\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'paytype', header: '收付方向', visible: false, formEditorConfig:{defaultValue:'pay_type_in',fieldVisible: false}},\r\n");
      out.write("\t\t\t{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{\t\t\t\r\n");
      out.write("\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\tfillFromFieldName : 'feetype',\r\n");
      out.write("\t\t\t\tfillProperty : 'name'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'value',\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t    pid: 'FeeType',\r\n");
      out.write("\t\t\t\t\txmlFileName: 'combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'paymentid', header: '收款编号',formEditorConfig:{labelWidth:100,required: true}},\r\n");
      out.write("\t\t\t{field: 'paycustname', header: '支付对象',formEditorConfig:{fieldVisible: false}},\r\n");
      out.write("\t\t\t{field: 'paycust', header: '支付对象',visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\tnewLine: true,\r\n");
      out.write("\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'name',\r\n");
      out.write("\t\t\t\tallowInput: false,\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\tcolspan:3,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\txmlFileName: 'common/custInfo.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'planmoney', header: '计划收款金额',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,required: true,onkeyup: 'setFundCollectOver(e)'}},\r\n");
      out.write("\t\t\t{field: 'factmoney', header: '实收款金额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'overmoney', header: '计划收款余额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'plandate', header: '收款日期',formEditorConfig:{\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttype:'date'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'planmoneystatus', header: '付款状态',formEditorConfig:{fieldVisible: false},renderer : function(e){\r\n");
      out.write("\t\t\t\t  return setfundIncomeState(e.record);\r\n");
      out.write("\t     \t}},\r\n");
      out.write("\t\t\t{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\theight:70}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\t \r\n");
      out.write("}); \r\n");
      out.write("function setFundCollectOver(e){\r\n");
      out.write("   var planmoney= e.sender.getInputText();\r\n");
      out.write("   var overmoney=getMiniEditFormField(\"plancharg_collect.overmoney\");\r\n");
      out.write("   overmoney.setValue(planmoney);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_table_plancharg_collect\" style=\"width:100%;height:400px\"></div>\r\n");
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