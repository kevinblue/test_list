/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-08-12 06:35:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005finspection.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class curpatrol_005finfo_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div id=\"patrol_result_info\" title=\"十、本次检查评估结论\">\r\n");
      out.write("\t<fieldset class=\"fieldset-title\">\r\n");
      out.write("\t\t<table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%\" class=\"fillTable\">\r\n");
      out.write("\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\" width=\"12%\">本次结论：</td>\r\n");
      out.write("\t\t\t\t<td style=\"padding-top: 4px;padding-bottom: 4px;\" colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t<input style=\"width:75%;height:100px\" id=\"contract_patrol_info.resadvice\" \r\n");
      out.write("\t\t\t\t\tname=\"contract_patrol_info.resadvice\" class=\"mini-textarea\" \r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_patrol_info.resadvice']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\" >关注点：</td>\r\n");
      out.write("\t\t\t\t<td style=\"padding-top: 4px;padding-bottom: 4px;\" colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t<input style=\"width:75%;height:100px\" id=\"contract_patrol_info.curassettype\" \r\n");
      out.write("\t\t\t\t\tname=\"contract_patrol_info.curassettype\" class=\"mini-textarea\" \r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_patrol_info.curassettype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\" width=\"12%\">管理措施建议：</td>\r\n");
      out.write("\t\t\t\t<td style=\"padding-top: 4px;padding-bottom: 4px;\" colspan=\"3\">\r\n");
      out.write("\t\t\t\t\t<input style=\"width:75%;height:100px\" id=\"contract_patrol_info.qassettype\" \r\n");
      out.write("\t\t\t\t\tname=\"contract_patrol_info.qassettype\" class=\"mini-textarea\" \r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_patrol_info.qassettype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t</fieldset>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields(\"patrol_result_info\");};\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
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