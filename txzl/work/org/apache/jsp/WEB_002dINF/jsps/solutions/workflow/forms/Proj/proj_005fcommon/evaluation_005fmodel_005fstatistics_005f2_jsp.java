/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-14 09:30:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class evaluation_005fmodel_005fstatistics_005f2_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"evaluation_model_statistics2\",\r\n");
      out.write("\t\t\trenderTo: \"id_evaluation_model_statistics2\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 10,\r\n");
      out.write("\t\t\theight: 500,\r\n");
      out.write("\t\t\tlazyLoad: false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\ttitle: \"\",\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tparams :{\r\n");
      out.write("\t\t\t\tflowUnid:flowUnid\r\n");
      out.write("\t\t            },\r\n");
      out.write("\t\t\txmlFileName : '/eleasing/workflow/proj/proj_common/proj_evaluation_model2.xml', \r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t{field:'listid',header:'年',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file1',header:'文件一',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file2',header:'文件二',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file3',header:'文件三',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file4',header:'文件四',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file5',header:'文件五',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file6',header:'文件六',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file7',header:'文件七',align:'center'},\r\n");
      out.write("\t\t\t\t{field:'file8',header:'文件八',align:'center'}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t]\r\n");
      out.write("\t});});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_evaluation_model_statistics2\"></div>\r\n");
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
