/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-02 01:34:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fput.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fput_005fprocess_005fnote_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" id=\"div_limit_info\" >\r\n");
      out.write("\t\t\t<tr class=\"tr-project-info tr-even\">\r\n");
      out.write("\t\t\t\t <td class=\"td-content-title\" width=\"12%\">流程备注：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"88%\">\r\n");
      out.write("\t             \t<input id=\"progressnote\" \r\n");
      out.write("\t\t\t\t\t name=\"progressnote\" \r\n");
      out.write("\t\t\t\t\t value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['progressnote']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t class=\"mini-textbox\" style=\"width:30%\" maxlength=\"20\"/>\r\n");
      out.write("\t\t          </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr class=\"tr-project-info tr-odd\">  \r\n");
      out.write("\t             <td class=\"td-content-title\">是否抵扣：</td>\r\n");
      out.write("\t             <td class=\"td-content\">\r\n");
      out.write("\t\t             <input name=\"proj_info.fund_delivery\" id=\"proj_info.fund_delivery\" class=\"mini-combobox\" label=\"内部行业\"\r\n");
      out.write("\t\t             \t\t\t\ttextField=\"name\" \r\n");
      out.write("\t\t             \t\t\t\trequired=\"true\"\r\n");
      out.write("\t\t             \t\t\t\tvalueField=\"value\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   allowInput=\"false\" \r\n");
      out.write("\t\t\t\t\t\t\t\t   data=\"[{value:'是',name:'是'},{value:'否',name:'否'}]\"\r\n");
      out.write("\t\t\t\t\t\t\t\t   text=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty requestScope['rawValue_proj_info.fund_delivery'] ? '否' : requestScope['rawValue_proj_info.fund_delivery']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty requestScope['proj_info.fund_delivery'] ? '否' : requestScope['proj_info.fund_delivery']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t\t\t   \t\t   onvaluechanged=\"comboboxChanged\" \r\n");
      out.write("\t\t\t\t\t\t\t\t/>\t\t \r\n");
      out.write("\t\t\t\t\t <input id=\"rawValue_proj_info.fund_delivery\" name=\"rawValue_proj_info.fund_delivery\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_info.fund_delivery']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none;\"/>\r\n");
      out.write("\t\t           </td>\r\n");
      out.write("\t\t    </tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("if('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'||isViewHistoryTask==true){ \r\n");
      out.write("    miniui_ext.disableFormFields(\"div_limit_info\");\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
