/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-23 01:16:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.seal_005fregistration;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class seal_005fregistration01_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/comm/arrayUtils.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    //流程保存之前回调\r\n");
      out.write("\tfunction workflowSaveCallBack()\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar obj = mini.get(\"cbl11\").getValue();\r\n");
      out.write("\t\tif(obj==''){\r\n");
      out.write("\t\t\tmini.alert(\"用章种类至少选择一种！！！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//保存成功提交后，后台返回回调\r\n");
      out.write("\tfunction saveCallBack() {\r\n");
      out.write("\t\t\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//流程提交之前回调\r\n");
      out.write("\tfunction workflowSubmitCallBack(buttonText)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar obj = mini.get(\"cbl11\").getValue();\r\n");
      out.write("\t\tif(obj==''){\r\n");
      out.write("\t\t\tmini.alert(\"用章种类至少选择一种！！！\");\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//提交路由，选择下一步的节点\r\n");
      out.write("\tfunction workflowNextRouteCallBack(buttonText,requestNextRoute){\r\n");
      out.write("\t\tif(buttonText == \"Submit\"){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\trequestNextRoute.value = \"TO 流程审批人_02\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<!--多行控件  -->\r\n");
      out.write("<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_route_str_many\" name=\"route_str_many\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${route_str_many}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></input>\r\n");
      out.write("<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_approvallevellast\" name=\"approvallevellast\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${approvallevellast}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></input>\r\n");
      out.write("\r\n");
      out.write("<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_proj_equip_str\" name=\"json_proj_equip_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_proj_equip_str ? \"[]\" : json_proj_equip_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_proj_guarantee_detail_str\" \tname=\"json_proj_guarantee_detail_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_proj_guarantee_detail_str ? \"[]\" : json_proj_guarantee_detail_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_proj_guaranty_detail_str\" \tname=\"json_proj_guaranty_detail_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_proj_guaranty_detail_str ? \"[]\" : json_proj_guaranty_detail_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<div class=\"fillTableContainer\">\r\n");
      out.write("   <table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t     <td colspan=4>\r\n");
      out.write("\t\t\t <!-- 公章使用登记信息 --> \r\n");
      out.write("\t\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comm/seal_registration_base_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t </td>\r\n");
      out.write("\t   </tr>\r\n");
      out.write("\t   \r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"tabDeatils\" class=\"mini-tabs\" activeIndex=\"0\"\r\n");
      out.write("\t\t\tstyle=\"width: 100%;\" bodyStyle=\"padding:0;border:0;\">\r\n");
      out.write("\t\t<div title=\"公章使用相关附件\" name=\"proj_accessories_report_list\"\r\n");
      out.write("\t\t\t\ticonCls=\"icon-cut\">\r\n");
      out.write("\t\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comm/seal_accessories_report_list.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("</script>\t\r\n");
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
