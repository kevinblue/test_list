/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-13 05:51:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fdevelop_005flist.proj_005fwind_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tariff_005frecord_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("var projname = mini.getbyName(\"projname\");\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"proj_tariff_record\",\r\n");
      out.write("\t\trenderTo: \"id_table_tariff_record\",\r\n");
      out.write("\t\twidth: '100%',\r\n");
      out.write("\t\theight: 200,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: true,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\tremoveOperCallBack: function(miniTable,rowDatas){\r\n");
      out.write("\t\t\tdropCreateFile(rowDatas);\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}, \r\n");
      out.write("\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/proj_tariff_record.xml',\r\n");
      out.write("\t\tparams : {\r\n");
      out.write("\t\t\tprojname:projname,\r\n");
      out.write("\t\t\tprojid:projid,\r\n");
      out.write("\t\t filekey:filekey,\r\n");
      out.write("\t\t modelname : '项目建设相关附件'\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'filename', header: '项目类别'},\r\n");
      out.write("\t\t\t{field: 'filename', header: '区域类型'},\r\n");
      out.write("\t\t\t{field: 'projkindname', header: '项目类型'},\r\n");
      out.write("\t\t\t{field: 'filename', header: '资源区'},\r\n");
      out.write("\t\t\t{field: 'filename', header: '省市' ,width: 150,\r\n");
      out.write("\t\t\t\trenderer: function(e){\r\n");
      out.write("\t\t\t   \t\t var resStr = e.record.provincename+\"-\"+e.record.cityname;\r\n");
      out.write("\t\t\t   \t\treturn resStr;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{field: 'createdate', header: '核准时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},\r\n");
      out.write("\t\t\t{field: 'benchmarkprice', header: '标杆电价（元/度）'},\r\n");
      out.write("\t\t\t{field: 'internetprice', header: '上网电价（元/度）'}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_tariff_record\"></div>");
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