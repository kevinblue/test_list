/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-25 06:37:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.risk_005freport;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class risk_005freport_005fpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486185689762L));
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
      out.write("\r\n");
      out.write("    <input name=\"proj_info.custInfo\" id=\"proj_info.custInfo\" type=\"hidden\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_info.custInfo'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("    <input name=\"proj_info.projstatus\" id=\"proj_info.projstatus\" type=\"hidden\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_info.projstatus'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("    <input name=\"proj_info.custclass\" id=\"proj_info.custclass\" type=\"hidden\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_info.custclass'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("    <div id=\"riskreport_change_info_form\" title=\"风险报送\">\r\n");
      out.write("\r\n");
      out.write("\t    <table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" id=\"proj_base_info\" >\r\n");
      out.write("\t          <tr class=\"tr-project-info tr-even\">\r\n");
      out.write("\t             <td class=\"td-content-title\" width=\"12%\">风险名称：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\" risk_info.riskname\" id =\"risk_name\"  class=\"mini-textbox\" label=\"项目编号\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.riskname']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t             <td class=\"td-content-title\" width=\"12%\">风险类型：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\" risk_info.risktype\" class=\"mini-combobox asLabel\" required=true textField=\"text\" \r\n");
      out.write("\t                  \t   valueField=\"text\"  \r\n");
      out.write("\t\t\t\t\t\t   data=\"[{text:'运营风险'},{text:'战略风险'},{text:'操作风险'},{text:'财务风险'},{text:'外部风险'}]\"\r\n");
      out.write("\t\t\t\t\t\t   allowInput=\"false\" \r\n");
      out.write("\t\t\t\t\t\t   showNullItem=\"true\" nullItemText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   emptyText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.risktype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   text=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.risktype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   onvaluechanged=\"oncreditcardyesorno\"/>\r\n");
      out.write("\t\t\t\t\t\t   <input id=\"rawValue_risk_info.risktype\" name=\"rawValue_risk_info.risktype\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.risktype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none\"/>\r\n");
      out.write("               </td>\r\n");
      out.write("\t         \r\n");
      out.write("\t          </tr>\r\n");
      out.write("\t           <tr class=\"tr-project-info tr-even\">\r\n");
      out.write("\t             <td class=\"td-content-title\" width=\"12%\">风险等级：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"risk_info.risklevel\" id =\"risk_level\"  class=\"mini-textbox\" label=\"项目编号\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.risklevel']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t             <td class=\"td-content-title\" width=\"12%\">风险类别：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"risk_info.riskcategories\" class=\"mini-combobox asLabel\" required=true textField=\"text\" \r\n");
      out.write("\t                  \t   valueField=\"value\"  \r\n");
      out.write("\t\t\t\t\t\t   data=\"[{text:'绿色',value:'绿色'},{text:'黄色',value:'黄色'},{text:'红色',value:'红色'}]\"\r\n");
      out.write("\t\t\t\t\t\t   allowInput=\"false\" \r\n");
      out.write("\t\t\t\t\t\t   showNullItem=\"true\" nullItemText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   emptyText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.riskcategories'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   text=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.riskcategories'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   onvaluechanged=\"oncreditcardyesorno\"/>\r\n");
      out.write("\t\t\t\t\t\t   <input id=\"rawValue_risk_info.riskcategories\" name=\"rawValue_risk_info.riskcategories\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.riskcategories']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none\"/>\r\n");
      out.write("                 </td>\r\n");
      out.write("\t          </tr>\r\n");
      out.write("\t        \r\n");
      out.write("\t           <tr class=\"tr-odd\">\r\n");
      out.write("\t             <td class=\"td-content-title\">风险描述：</td>\r\n");
      out.write("\t             <td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\"><input style=\"width:72%;height:50px\" label=\"风险描述：\" name=\"risk_info.riskdescription\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.riskdescription'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textarea\"  type=\"text\" >  </td>\r\n");
      out.write("\t           </tr> \r\n");
      out.write("\t          <tr class=\"tr-project-info tr-odd\">\r\n");
      out.write("\t             <td class=\"td-content-title\">风险控制策略：</td>\r\n");
      out.write("\t             <td class=\"td-content\" colspan=3>\r\n");
      out.write("\t             <input name=\" risk_info.riskcontrolstrategy\" class=\"mini-combobox asLabel\" required=true textField=\"text\" \r\n");
      out.write("\t                  \t   valueField=\"text\"  \r\n");
      out.write("\t\t\t\t\t\t   data=\"[{text:'减轻风险'},{text:'风险转移'},{text:'风险承担'},{text:'拒绝风险'}]\"\r\n");
      out.write("\t\t\t\t\t\t   allowInput=\"false\" \r\n");
      out.write("\t\t\t\t\t\t   showNullItem=\"true\" nullItemText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   emptyText=\"\"\r\n");
      out.write("\t\t\t\t\t\t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.riskcontrolstrategy'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   text=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.riskcontrolstrategy'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t   onvaluechanged=\"oncreditcardyesorno\"/>\r\n");
      out.write("\t\t\t\t\t\t   <input id=\"rawValue_risk_info.riskcontrolstrategy\" name=\"rawValue_risk_info.riskcontrolstrategy\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_risk_info.riskcontrolstrategy']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none\"/>\r\n");
      out.write("\t          </tr>\r\n");
      out.write("\t         \r\n");
      out.write("\t           <tr class=\"tr-odd\">\r\n");
      out.write("\t             <td class=\"td-content-title\">风险控制措施：</td>\r\n");
      out.write("\t             <td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\"><input style=\"width:72%;height:50px\" label=\"风险控制措施\" required=true name=\"risk_info.riskcontrolmeasures\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['risk_info.riskcontrolmeasures'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textarea\"  type=\"text\" >  </td>\r\n");
      out.write("\t           </tr>    \r\n");
      out.write("\t</table>\r\n");
      out.write("\t\r\n");
      out.write("</div>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
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