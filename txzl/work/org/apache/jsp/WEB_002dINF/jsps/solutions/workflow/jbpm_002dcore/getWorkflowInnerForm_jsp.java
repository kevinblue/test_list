/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-08-12 06:35:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.jbpm_002dcore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class getWorkflowInnerForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(4);
    _jspx_dependants.put("/WEB-INF/tlds/spring.tld", Long.valueOf(1486185689584L));
    _jspx_dependants.put("/WEB-INF/tlds/fn.tld", Long.valueOf(1486185689597L));
    _jspx_dependants.put("/WEB-INF/tlds/fmt.tld", Long.valueOf(1486185689642L));
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

      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--表单页签1开始-->\r\n");
      out.write("<!--表单处理-->\r\n");
      out.write("<div id=\"id_workflowFormContainer\" style=\"width:100%;overflow:auto;position:relative;\">\r\n");
      out.write("\t<iframe name=\"real_submit_frame\" style=\"display:none;\" id=\"id_real_submit_frame\"></iframe>\r\n");
      out.write("\t<form id=\"id_submitProcessedForm\" enctype=\"multipart/form-data\" target=\"real_submit_frame\" action=\"{pageContext.request.contextPath}/submitProcessedForm/jbpm/submitProcessedForm.action\" method=\"post\">\r\n");
      out.write("\t\t<!-- 流程提交隐藏域 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowFormHiddenField.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<!-- 当前任务节点表单路径相对于jbpm-core/forms下的jsp页面 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, (java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/${empty requestFormPath ? 'errorPages/error404.bi' : requestFormPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false), out, true);
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t</form>\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t$(\".mini-textbox-readOnly .mini-textbox-border\").css(\"background\", \"#F0F0F0\");\r\n");
      out.write("\t$(\".mini-buttonedit-readOnly .mini-buttonedit-border\").css(\"background\", \"#F0F0F0\");\r\n");
      out.write("\t$(\".mini-buttonedit-readOnly .mini-buttonedit-button\").css(\"display\", \"none\");\r\n");
      out.write("\t$(\"<font class='required-tag'>*</font>\").insertAfter(\".mini-required\");\r\n");
      out.write("});\r\n");
      out.write("function $miniEnable(id){\r\n");
      out.write("\tvar miniObj = mini.get(id);\r\n");
      out.write("\tminiObj.enable();\r\n");
      out.write("\tvar uiCl = miniObj.uiCls;\r\n");
      out.write("\tvar jQueryObj = $('#'+id);\r\n");
      out.write("\tif(uiCl == 'mini-textbox'){\r\n");
      out.write("\t\tjQueryObj.find('.mini-textbox-border').attr(\"style\", \"\");\r\n");
      out.write("\t}else if(uiCl == 'mini-combobox'){\r\n");
      out.write("\t\tjQueryObj.find('.mini-textbox-border').attr(\"style\", \"\");\r\n");
      out.write("\t\tjQueryObj.find('.mini-buttonedit-border').attr(\"style\", \"\");\r\n");
      out.write("\t\tjQueryObj.find(\".mini-buttonedit-button\").css(\"display\", \"block\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function $miniDisable(id){\r\n");
      out.write("\tvar miniObj = mini.get(id);\r\n");
      out.write("\tminiObj.disable();\r\n");
      out.write("\tvar uiCl = miniObj.uiCls;\r\n");
      out.write("\tvar jQueryObj = $('#'+id);\r\n");
      out.write("\tif(uiCl == 'mini-combobox'){\r\n");
      out.write("\t\tjQueryObj.find(\".mini-buttonedit-button\").css(\"display\", \"none\");\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function $miniSetCombValue(id,value,text){\r\n");
      out.write("\tvar miniObj = mini.get(id);\r\n");
      out.write("\tminiObj.setValue(value);\r\n");
      out.write("\tminiObj.setText(text);\r\n");
      out.write("\tvar miniHiddenObj = mini.get(\"rawValue_\"+id).setValue(text);\r\n");
      out.write("}\r\n");
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
