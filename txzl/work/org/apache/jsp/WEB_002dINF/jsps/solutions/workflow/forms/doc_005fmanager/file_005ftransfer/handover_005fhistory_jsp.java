/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-30 06:55:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.doc_005fmanager.file_005ftransfer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class handover_005fhistory_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tvar contractid = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"handover_history\",\r\n");
      out.write("\t\trenderTo: \"id_table_handover_history\",\r\n");
      out.write("\t\twidth: globalClientWidth ,\r\n");
      out.write("\t\theight: 365,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\tparams:{\"contract_id\":contractid},\r\n");
      out.write("\t\txmlFileName : '/eleasing/jsp/doc/handover_history.xml',\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type : 'indexcolumn'}, \r\n");
      out.write("\t\t\t{field:'contractid', header:'contractid',visible:false,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'soncontract', header:'子合同号',visible:true,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'filenumber',header:'档案编号',visible:true,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'filename',header:'档案名称',visible:true,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'handoverman',header:'交接人',visible:true,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'handoverdate',header:'交接时间',visible:true,headerAlign:'center',align:'center'},\r\n");
      out.write("\t\t\t{field:'remark',header:'备注',visible:true,headerAlign:'center',align:'center'}\r\n");
      out.write("\t\t         ]\r\n");
      out.write("\t   });\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_handover_history\"></div>\r\n");
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
