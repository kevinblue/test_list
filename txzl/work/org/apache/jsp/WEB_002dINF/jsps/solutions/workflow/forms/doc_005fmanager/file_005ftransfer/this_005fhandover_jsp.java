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

public final class this_005fhandover_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"this_handover\",\r\n");
      out.write("\t\t\trenderTo: \"id_table_this_handover\",\r\n");
      out.write("\t\t\twidth: globalClientWidth,\r\n");
      out.write("\t\t\theight: 360,\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\ttitle: \"\",\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tmultiSelect: true,\r\n");
      out.write("\t\t\ttitle:'',\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\tvirtualScroll:true,\r\n");
      out.write("\t\t\ttools: ['add', '-', 'edit', '-','remove'],\r\n");
      out.write("\t\t\tparams:{\"contract_id\":contractid},\r\n");
      out.write("\t\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_this_handover_str ? \"[]\" : json_this_handover_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field:'id',header:'id',visible: false},\r\n");
      out.write("\t\t\t\t{field:'docsubname',header:'子合同号',\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t    labelWidth:105,\r\n");
      out.write("\t\t\t\t\t\t     maxLength:120,\r\n");
      out.write("\t\t\t\t\t\t       colspan:3,\r\n");
      out.write("\t\t\t\t\t\t         width:'100%'}},\r\n");
      out.write("\t\t\t\t{field:'docnumber', header:'档案编号',\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t    labelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t     maxLength:120,\r\n");
      out.write("\t\t\t\t\t\t       colspan: 3,\r\n");
      out.write("\t\t\t\t\t\t         width: '100%'}},\r\n");
      out.write("\t\t\t\t{field:'docname', header:'档案名称',\r\n");
      out.write("\t\t\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\tnewLine: true,\r\n");
      out.write("\t\t\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t\t\t maxLength:120,\r\n");
      out.write("\t\t\t\t\t\t\t\t colspan: 3,\r\n");
      out.write("\t\t\t\t\t\t\t\t width: '100%'}},\r\n");
      out.write("\t\t\t\t{field: 'handoverdate', header:'交接日期',\r\n");
      out.write("\t\t\t\t\t\t\tdateFormat:\"yyyy-MM-dd\",\r\n");
      out.write("\t\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\t  type:'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t  vtype:'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t format:'yyyy-MM-dd',\r\n");
      out.write("\t\t\t\t\t\t\t\tallowInput:false}},\r\n");
      out.write("\t\t\t\t/* {field:'handoverman', header:'交接人',\r\n");
      out.write("\t\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\tnewLine: true,\r\n");
      out.write("\t\t\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t\t\tmaxLength:120,\r\n");
      out.write("\t\t\t\t\t\t\t\tcolspan: 3,\r\n");
      out.write("\t\t\t\t\t\t\t\twidth: '100%'}}, */\r\n");
      out.write(" \t\t\t\t{field: 'handovermanname', header:'交接人', \r\n");
      out.write("\t\t\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\t  colspan:6,\r\n");
      out.write("\t\t\t\t\t\t\t\tfieldVisible:false,\r\n");
      out.write("\t\t\t\t\t\t\tfillFromFieldName:'handoverman',\r\n");
      out.write("\t\t\t\t\t\t\t\tfillProperty:'name',\r\n");
      out.write("\t\t\t\t\t\t\tentityClassName:'com.tenwa.leasing.entity.cust.User',\r\n");
      out.write("\t\t\t\t\t\t\tentityHeaderFieldName:'custName'\r\n");
      out.write("\t\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t\t{field: 'handoverman', header:'交接人', visible:false,\r\n");
      out.write("\t\t\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t    newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t    colspan:3,\r\n");
      out.write("\t\t\t\t\t\t\t    width:'100%',\r\n");
      out.write("\t\t\t\t\t\t\t\ttype:'queryinput',\r\n");
      out.write("\t\t\t\t\t\t\t    textField:'name',\r\n");
      out.write("\t\t\t\t\t\t\t\tvalueField:'value',\r\n");
      out.write("\t\t\t\t\t\t\t\tallowInput:false,\r\n");
      out.write("\t\t\t\t\t\t\t\tfieldVisible:true,\r\n");
      out.write("params:{xmlFileName:'/eleasing/workflow/proj/proj_common/creator_combox.xml'}}}, \r\n");
      out.write("\t\t\t\t{field:'docmemo',header:'备注',\r\n");
      out.write("\t\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\t maxLength:1000,\r\n");
      out.write("\t\t\t\t\t\t\t\t newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t type:'textarea',\r\n");
      out.write("\t\t\t\t\t\t\t\t  colspan:6,\r\n");
      out.write("\t\t\t\t\t\t\t\t  height:70,\r\n");
      out.write("\t\t\t\t\t\t\t\t  width:'100%'\r\n");
      out.write("\t\t\t\t\t\t\t}}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_this_handover\"></div>\r\n");
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
