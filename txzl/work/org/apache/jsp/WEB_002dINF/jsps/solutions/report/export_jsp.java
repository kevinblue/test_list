/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-08-12 03:10:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.report;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class export_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/c.tld", Long.valueOf(1486185689570L));
  }

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

      out.write("\n");
      out.write("\n");
      out.write("<div id=\"div_excelExport\" style=\"display:none;height:200px\">\t\n");
      out.write("\t<input type=\"hidden\" name=\"export_reportName\" id=\"export_reportName\" vlaue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${(empty reportName)?'':reportName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\n");
      out.write("\t<center>\n");
      out.write("\t<table id=\"table_excelExport\" style=\"width:90%;margin-top:5px\">\n");
      out.write("\t\t\n");
      out.write("\t</table>\n");
      out.write("\t<div id=\"table_export_btn\" style=\"float:right;position:absolute; bottom:1px;right:10px;height:20px;margin-bottom:10px;margin-right:10px;\">\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"btn btn-primary\" onclick=\"doExport()\"><span>导出</span></a>\n");
      out.write("\t\t<a href=\"javascript:void(0);\" class=\"btn btn-primary\" onclick=\"javascript:$('#div_excelExport').window('close');\"><span>关闭</span></a>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t<div style=\"display:none\">\n");
      out.write("\t\t<iframe id=\"iframe_export\" src=\"about:blank\" style=\"display:none\">\n");
      out.write("\t\t\n");
      out.write("\t\t</iframe>\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("\t</center>\n");
      out.write("</div>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\tfunction doExport (){\n");
      out.write("\t\tvar d = new Date();\n");
      out.write("\t\t//var d = new Date ('2014/7/17 23:44:19');\n");
      out.write("\t\tvar day = d.getHours();\n");
      out.write("\t\t//得到当前报表的数据量\n");
      out.write("\t\tvar infos = jQuery('.mini-pager-right').html();\n");
      out.write("\t\tvar pageReg = /\\d+/gi;\n");
      out.write("\t\tvar nums = infos.match(pageReg);\n");
      out.write("\t\tvar num = nums[1];\n");
      out.write("\t\t//如果数据量小于10000,随时可以下载\n");
      out.write("\t\tif(Number(num) <= 50000){\n");
      out.write("\t\t\texportExcel('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/report/exportExcel.action');\n");
      out.write("\t\t}else if(Number(num) > 50000 && Number(num) <=100000){\n");
      out.write("\t\t\t//在上班期间不能下载\n");
      out.write("\t\t\tif((day>=8 && day<12)||(day>=14&&day<18)){\n");
      out.write("\t\t\t\talert('抱歉，数据量过大，请在非工作期间下载！');\n");
      out.write("\t\t\t\treturn;\n");
      out.write("\t\t\t}else{\n");
      out.write("\t\t\t\texportExcel('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/report/exportExcel.action');\n");
      out.write("\t\t\t}\n");
      out.write("\t\t}else{\n");
      out.write("\t\t\talert('数据量过大，请过滤后下下载！');\n");
      out.write("\t\t\treturn;\n");
      out.write("\t\t}\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("</script>");
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
