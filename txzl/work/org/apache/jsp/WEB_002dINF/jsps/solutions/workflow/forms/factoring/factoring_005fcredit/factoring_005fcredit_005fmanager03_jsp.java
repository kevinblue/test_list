/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-10 02:27:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.factoring.factoring_005fcredit;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class factoring_005fcredit_005fmanager03_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    //流程保存之前回调\r\n");
      out.write("\tfunction workflowSaveCallBack()\r\n");
      out.write("\t{\r\n");
      out.write("    /* \tvar id1=document.getElementById(\"proj_info.projstatus\");\r\n");
      out.write("        var id2=document.getElementById(\"proj_info.approvedate\");\r\n");
      out.write("        var id3=document.getElementById(\"proj_info.approveconclusion\");\r\n");
      out.write("    \t */\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"tabApprovalDeatils\");\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("    \t\r\n");
      out.write("    \t\r\n");
      out.write("\t}\r\n");
      out.write("\t//保存成功提交后，后台返回回调\r\n");
      out.write("\tfunction saveCallBack() {\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//流程提交之前回调\r\n");
      out.write("\tfunction workflowSubmitCallBack(buttonText)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//条件路由判断是否同意立项\r\n");
      out.write("\t    mini.get(\"isagree\").setValue(mini.get(\"id_selectAdvise_combo\").getValue());\r\n");
      out.write("\t   if (miniui_ext.submitFormValidation([\"proj_factoring_base_info_form\"]) == false||miniui_ext.submitFormValidation([\"credit_result_form\"])==false) return;\r\n");
      out.write("\t   miniui_ext.saveIncludeData(\"tabApprovalDeatils\");\r\n");
      out.write("\t //保存投票信息\r\n");
      out.write("       var cflag=miniui_ext.submitFormValidation([\"id_project_vote\"]);\r\n");
      out.write("\t\tif (cflag== false) return false;\t \r\n");
      out.write("       //获取审批结论下拉框是否填写信息，如果填写，则进行保存。\r\n");
      out.write("       if(mini.get('id_selectAdvise_combo').getValue()!=\"\"){\r\n");
      out.write("\t        var flag = saveVote();\r\n");
      out.write("\t    \treturn flag;\r\n");
      out.write("       }\r\n");
      out.write("      return true;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!--多行控件  -->\r\n");
      out.write("<input name=\"isagree\" id=\"isagree\" style=\"display:none;\" class=\"mini-textbox\"   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['isagree']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<input \tstyle=\"display: none;\" class=\"mini-textarea\" id=\"id_json_proj_invoice_str\" name=\"json_proj_invoice_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_proj_invoice_str ? \"[]\" : json_proj_invoice_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<div class=\"fillTableContainer\">\r\n");
      out.write("   <table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t     <td colspan=4>\r\n");
      out.write("\t\t\t <!-- 插入保理业务基本信息 --> \r\n");
      out.write("\t\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/factoring_base_info.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t </td>\r\n");
      out.write("\t   </tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\t\t\r\n");
      out.write("<div id=\"tabApprovalDeatils\" class=\"mini-tabs\" activeIndex=\"0\" onactivechanged=\"changTab\" style=\"width: 100%;\" bodyStyle=\"border:0px;\">\r\n");
      out.write("\t<div title=\"投票信息汇总\" name=\"proj_vote\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t\t ");
      out.write("\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<!-- 插入项目信审信息 -->\r\n");
      out.write("\t\t\t\t<td colspan=4> ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../Proj/proj_credit/common/proj_credit_committee2.jsp", out, false);
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<!-- 插入项目信审信息 -->\r\n");
      out.write("\t\t\t\t<td colspan=4> ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../Proj/proj_credit/common/proj_credit_committee_final2.jsp", out, false);
      out.write("</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"发票信息\" name=\"proj_invoice\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../Proj/proj_common/proj_invoice.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"保理报价方案\" name=\"business_condition\" iconCls=\"icon-cut\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../reckon/rent_reckon/main_5_1.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"供应商信息\" name=\"supplier_message\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../Proj/proj_common/proj_supplier_message.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"贸易基础交易情况\" name=\"trade_based\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../Proj/proj_common/trade_based_transactions.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"信审信息\" name=\"credit_message\" iconCls=\"icon-cut\">\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/credit_result.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("</div>");
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
