/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-08-12 06:35:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005finspection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fpatrol_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<input name=\"contract_info.custInfo\" id=\"contract_info.custInfo\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.custInfo'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input name=\"contract_info.businessmanager\" id=\"contract_info.businessmanager\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.businessmanager'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input name=\"contract_info.contractstatus\" id=\"contract_info.contractstatus\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.contractstatus'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input name=\"contract_info.projid\" id=\"contract_info.projid\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.projid']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input name=\"contract_info.id\" id=\"contract_info.id\" class=\"mini-textbox\" style=\"display: none;\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input name=\"contract_info.contract_id\" id=\"contract_info.contract_id\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.contract_id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<div id=\"contract_base_info_form\" title=\"承租人基本信息\">\r\n");
      out.write("\t<div class=\"mini-panel\" title=\"承租人基本信息\" showCollapseButton=\"true\" style=\"width:100%;\">\r\n");
      out.write("\t\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%\" id=\"contract_base_info\">\r\n");
      out.write("\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">承租人名称：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">\r\n");
      out.write("\t\t\t\t\t<input name=\"contract_info.projectname\" id=\"cust_name\"  label=\"项目名称\" class=\"mini-textbox\" readOnly type=\"text\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['cust_name']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t<input style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_custid_str\" name=\"json_industryType_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${custid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">项目名称：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"project_name\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['project_name'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content-title\">租赁合同编号：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"contract_number\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contractnumber'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">租赁种类：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"lease_type\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['leasetype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">融资金额（万元）：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"lease_money\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['leasemoney'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">租赁期限（月）：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"leaseterm\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['lease_term'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">租金总期数：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"rent_num\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rentnum'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t        <td class=\"td-content-title\">剩余期数：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"surplus\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['surplus'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">每期平均租金：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"avgrent\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['avgrent'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t        <td class=\"td-content-title\">租金支付周期：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"income_time\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['incometime'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">支付方式：</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\"><input id=\"periodtype\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['periodtype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content-title\">逾期期数：</td>\r\n");
      out.write("\t\t\t    <td class=\"td-content\"><input id=\"ouot_list\"   name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['ouotlist'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t          <td class=\"td-content-title\">逾期金额（万元）：</td>\r\n");
      out.write("\t\t\t\t   <td class=\"td-content\"><input id=\"out_money\" name=\"contract_info.contractid\" class=\"mini-textbox\" label=\"合同编号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['outmoney'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t        <td class=\"td-content-title\">应收租赁款余额（万元）：</td>\r\n");
      out.write("\t\t\t\t   <td class=\"td-content\"><input id=\"current_overage\"  name=\"contract_info.contractnumber\" class=\"mini-textbox\" label=\"业务合同编号\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['currentoverage'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"false\" /></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\r\n");
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
