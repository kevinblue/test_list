/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-19 05:22:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.jbpm_002dcore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class commonWorkflow_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("/WEB-INF/tlds/spring.tld", Long.valueOf(1486341662563L));
    _jspx_dependants.put("/WEB-INF/tlds/fn.tld", Long.valueOf(1486341662568L));
    _jspx_dependants.put("/WEB-INF/tlds/fmt.tld", Long.valueOf(1486341662517L));
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
    _jspx_dependants.put("/WEB-INF/tlds/c.tld", Long.valueOf(1486341662557L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody != null) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<!-- 通用变量和函数 -->\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write(" <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<title>");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("</title>\r\n");
      out.write("<!--样式和引用以及通用函数-->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowCommonCssAndJs.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t/* \r\n");
      out.write("\tmini.mask({\r\n");
      out.write("\t    el: document.body,\r\n");
      out.write("\t    cls: 'mini-mask-loading',\r\n");
      out.write("\t    html: '数据加载中，请稍后...'\r\n");
      out.write("\t});\r\n");
      out.write("\tsetTimeout(function(){\r\n");
      out.write("\t\tmini.unmask(document.body);\r\n");
      out.write("\t}, 3000);\r\n");
      out.write("\t*/\r\n");
      out.write("\tvar windowHeightV = jQuery(window).height() - 100;\r\n");
      out.write("\tvar windowWidthV = jQuery(window).width() - 20;\r\n");
      out.write("\tjQuery('#id_workflowFormContainer').css({height:windowHeightV});//,width:windowWidthV\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\t<body style=\"overflow:hidden; padding-bottom:2px;\">\r\n");
      out.write("\t\t<!-- 流程信息容器-->\r\n");
      out.write("\t\t<div style=\"overflow: hidden;margin: 0 auto;width: 99.5%;padding-top: 2px;\" id=\"__processPageAllContainer\">\r\n");
      out.write("\t\t\t<!--提交按钮-->\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowButtons.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t<!-- 流程信息明细 -->\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowMainContainer.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t<!-- 弹出意见框开始 -->\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowChoseAdvise.jsp", out, false);
      out.write("  \t \r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\t<!-- 流程页面加载完成处理 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowLoadFinishProcess.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<!-- 流程提交相关函数 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowSubmitAndCallBack.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<!-- 流程页面通用函数 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowCommonFunc.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t<!-- 流程页面操作函数 -->\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "getWorkflowButtonsFunc.jsp", out, false);
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

  private boolean _jspx_meth_mini_005fparam_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f0 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/commonWorkflow.jsp(13,7) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("currentTaskFormTitle");
    int _jspx_eval_mini_005fparam_005f0 = _jspx_th_mini_005fparam_005f0.doStartTag();
    if (_jspx_th_mini_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
    return false;
  }
}
