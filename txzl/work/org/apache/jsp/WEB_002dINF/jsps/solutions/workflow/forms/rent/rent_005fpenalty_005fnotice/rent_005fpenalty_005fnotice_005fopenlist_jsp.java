/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-23 03:36:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.rent_005fpenalty_005fnotice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rent_005fpenalty_005fnotice_005fopenlist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("/WEB-INF/tlds/spring.tld", Long.valueOf(1486185689584L));
    _jspx_dependants.put("/base/base.jsp", Long.valueOf(1486185899351L));
    _jspx_dependants.put("/base/var.jsp", Long.valueOf(1490779521893L));
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486185689762L));
    _jspx_dependants.put("/base/mini.jsp", Long.valueOf(1490779821933L));
    _jspx_dependants.put("/WEB-INF/tlds/c.tld", Long.valueOf(1486185689570L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fc_005fif_0026_005ftest != null) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    if (_005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody != null) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    if (_005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody != null) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.release();
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("<title>罚息通知书</title>\r\n");
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_c_005fset_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f3(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      if (_jspx_meth_c_005fset_005f4(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar globalTimestamp = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar globalLocale = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentLocale}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar globalSkin = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentSkin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar globalClientWidth = document.documentElement.clientWidth;\r\n");
      out.write("\tvar globalClientHeight = document.documentElement.clientHeight;\r\n");
      out.write("\tvar globalWebRoot = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\tvar urlPrefix = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${urlPrefix}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/seajs/sea.js?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery/jquery-1.11.0.min.js?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">seajs.config({vars: { currentLocale: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentLocale}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("',currentSkin:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentSkin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'},base: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/',map: [[ /^(.*\\.(?:css|js))(.*)$/i, '$1?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' ]],charset: 'UTF-8'});</script>\r\n");
      out.write("<link rel=stylesheet href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/base/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentSkin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/base.css?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write('"');
      out.write('/');
      out.write('>');
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/miniui/miniui.js?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/miniui/locale/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentLocale}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(".js?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/comm/miniui_ext.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/comm/miniuiExtButtons.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/comm/DataUtil.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<link rel=stylesheet href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/miniui/default/miniui.css?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<link rel=stylesheet href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/miniui/icons.css?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<link rel=stylesheet href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/miniui/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentSkin}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/skin.css?");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentTimestamp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("<link rel=stylesheet href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/comm/miniui_ext.css\"/>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyJsonUtil.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar miniuiExtObject = {};\r\n");
      out.write("\tfunction getMiniuiExtObject(id){\r\n");
      out.write("\t\treturn miniuiExtObject[id];\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction getRootPath() {\r\n");
      out.write("\t\tvar strFullPath = window.document.location.href;\r\n");
      out.write("\t\tvar strPath = window.document.location.pathname;\r\n");
      out.write("\t\tvar pos = strFullPath.indexOf(strPath);\r\n");
      out.write("\t\tvar prePath = strFullPath.substring(0, pos);\r\n");
      out.write("\t\tvar postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);\r\n");
      out.write("\t\treturn (prePath + postPath);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction miniextonbeforeshowpopup(e) {\r\n");
      out.write("\t\tminiui_ext.onbeforeshowpopup(e);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction miniextonDateRenderer(e){\r\n");
      out.write("\t\treturn miniui_ext.onDateRenderer(e);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction toggleFieldSet(ck, id) {\r\n");
      out.write("        var dom = document.getElementById(id);\r\n");
      out.write("        dom.className = !ck.checked ? \"hideFieldset\" : \"\";\r\n");
      out.write("        var _fit = mini.get(\"_fit\");\r\n");
      out.write("        _fit.setHeight(window.document.body.clientHeight);\r\n");
      out.write("    }\r\n");
      out.write("\tfunction CloseWindow(action) {            \r\n");
      out.write("\t\tif (action == \"close\" && grid.isChanged()) {\r\n");
      out.write("\t        if (confirm(\"æ°æ®è¢«ä¿®æ¹äºï¼æ¯å¦åä¿å­ï¼\")) {\r\n");
      out.write("\t            return false;\r\n");
      out.write("\t        }\r\n");
      out.write("\t    }\r\n");
      out.write("\t    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);\r\n");
      out.write("\t    else window.close();           \r\n");
      out.write("\t}\r\n");
      out.write("\tfunction onCancel(e) {\r\n");
      out.write("\t    CloseWindow(\"cancel\");\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction onCloseClick(e) {\r\n");
      out.write("\t    var obj = e.sender;\r\n");
      out.write("\t    obj.setText(\"\");\r\n");
      out.write("\t    obj.setValue(\"\");\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction isEnglish(){\r\n");
      out.write("\t\t if(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope['login_userlanguage'].language}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" == 'en'){\r\n");
      out.write("\t\t\t \treturn true;\r\n");
      out.write("         }\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//设置千分位之后的值\r\n");
      out.write("\tfunction setformatvalue(m){\r\n");
      out.write("\t\tvar pMoneyOri = m.getInputText().trim();\r\n");
      out.write("\t\tvar pMoney = pMoneyOri.replace(/,/gi,\"\");\r\n");
      out.write("\t\tvar pMoneyReg = /^[-\\+]?\\d+[\\.]?([0-9]{1,2})?$/;\r\n");
      out.write("\t\tvar flag = true;\r\n");
      out.write("\t\tif(m.vtype == 'rate'){\r\n");
      out.write("\t\t\t pMoneyReg = /^[-\\+]?\\d+[\\.]?([0-9]{1,6})?$/;\r\n");
      out.write("\t\t\t flag = false;\r\n");
      out.write("\t\t}else if(m.vtype == 'int'){\r\n");
      out.write("\t\t\t pMoneyReg = /^[-\\+]?\\d+$/;\r\n");
      out.write("\t\t\t flag = false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(pMoneyOri && !pMoneyReg.test(pMoney)){\r\n");
      out.write("\t\t\tpMoney = 0;\r\n");
      out.write("\t\t\tm.setValue();\r\n");
      out.write("\t\t\tm.setValue('0');\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t//把非数字的字符格式化成空格、并千分位\r\n");
      out.write("\t\t\tif(flag){\r\n");
      out.write("\t\t\t\tm.setValue(formatNumberThousand(pMoney));\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t//设置千分位\r\n");
      out.write("\tfunction setapcolumnformatvalue(m){\r\n");
      out.write("\t\tvar obj = m.sender;\r\n");
      out.write("\t\tvar pMoneyOri = obj.getInputText().trim();\r\n");
      out.write("\t\tvar pMoney = pMoneyOri.replace(/,/gi,\"\");\r\n");
      out.write("\t\tif(!pMoney){pMoney = 0};\r\n");
      out.write("\t\tvar pMoneyReg = /^[-\\+]?\\d+[\\.]?([0-9]{1,2})?$/;\r\n");
      out.write("\t\tif(obj.vtype == 'rate'){\r\n");
      out.write("\t\t\tvar pMoneyReg = /^[-\\+]?\\d+[\\.]?([0-9]{1,6})?$/;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif((pMoney && pMoney.length>=0) && !pMoneyReg.test(pMoney)){\r\n");
      out.write("\t\t\tpMoney = 0;\r\n");
      out.write("\t\t\tobj.setValue();\r\n");
      out.write("\t\t\tobj.setValue('0');\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tobj.setValue();\r\n");
      out.write("\t\t\tobj.setValue(formatNumberThousand(pMoney));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t//格式化数字为千分位\r\n");
      out.write("\tfunction formatNumberThousand(s) {  \r\n");
      out.write("\t\t   s += \"\";\r\n");
      out.write("\t\t   s = s.replace(/,/g,\"\");\r\n");
      out.write("\t\t  \r\n");
      out.write("\t\t   if(isNaN(s)){\r\n");
      out.write("\t\t\t   return s;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t   //if(s==0){s=\"0.00\";}\r\n");
      out.write("\t\t   s += \"\";\r\n");
      out.write("\t\t   //s = s.replace(/,/g,\"\");\r\n");
      out.write("\t\t   var re=/(\\d{1,3})(?=(\\d{3})+(?:$|\\D))/g; //\r\n");
      out.write("\t\t   var n1=s.replace(re,\"$1,\");\r\n");
      out.write("\t\t   return n1;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){//2017/03/27 所有页面增加一个alt+3获取页面jsp路径的功能  by zhulh\r\n");
      out.write("\t\tdocument.onkeydown = function(e){\r\n");
      out.write("\t\t\tvar keyCode = e.keyCode || e.which;\r\n");
      out.write("\t\t\t\tif(e.altKey && keyCode == 51){\r\n");
      out.write("\t\t\t\t\tvar jpath = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.requestFormPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t\t\t\t\tif(jpath){\r\n");
      out.write("\t\t\t\t\t\tmini.alert(jpath.substring(0,jpath.length-2)+\"jsp\",\"JSP路径\");\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tmini.alert(\"");
      out.print(request.getServletPath());
      out.write("\",\"JSP路径\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t})\r\n");
      out.write("</script>");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/tenwa.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/workFlowUtil.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyUtils.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var currentClientWidth = document.documentElement.clientWidth;\r\n");
      out.write("var currentClientHeight = document.documentElement.clientHeight;\r\n");
      out.write("var tools = [];\r\n");
      out.write("tools.push({\r\n");
      out.write("\thtml: '罚息通知书',\r\n");
      out.write("\tplain: false,\r\n");
      out.write("\ticonCls: 'icon-addfolder',\r\n");
      out.write("\thandler: function(miniTable, buttonText){\r\n");
      out.write("\t\tvar rentRowData = miniTable.getSelected();\r\n");
      out.write("\t\tif(rentRowData){\r\n");
      out.write("\t\t\tvar attachmentParams = \"contract_id=\" + rentRowData.id;\r\n");
      out.write("\t\t\tstartProcess(\"罚息通知书-1\",attachmentParams);\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tmini.alert(\"请选中要操作的数据！\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t//流程列表\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"rent_income\",\r\n");
      out.write("\t\trenderTo: \"id_table_container_rent_income\",\r\n");
      out.write("\t\twidth: currentClientWidth,\r\n");
      out.write("\t\theight: currentClientHeight,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\ttitle: \"罚息支付通知书\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: true,\r\n");
      out.write("\t\tpageSize: 20,\r\n");
      out.write("\t\ttools: tools,\r\n");
      out.write("\t\tqueryButtonColSpan: 10,\r\n");
      out.write("\t\tqueryButtonNewLine:true,\r\n");
      out.write("\t\txmlFileName: '/eleasing/workflow/rent/rent_income/rent_income_list.xml',\r\n");
      out.write("\t\tparams:{ismaymoney:'and PENALTYOVERAGE<>0'},\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t      \t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'contract_id', header: '合同编号'},\r\n");
      out.write("\t\t\t\t{field: 'contract_number', header: '业务合同编号',queryConfig:{width: 165}},\r\n");
      out.write("\t\t\t\t{field: 'project_name', header: '项目名称',queryConfig:{width: 165}},\r\n");
      out.write("\t\t\t\t{field: 'currentoverage', header: '租金余额',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t\t{field: 'penaltyoverage', header: '罚息余额',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t\t{field: 'cust_name', header: '客户名称',queryConfig:{width: 165,newLine: false}},\r\n");
      out.write("\t\t\t\t{field: 'industry_type', header: '内部行业'},\r\n");
      out.write("\t\t\t\t{field: 'projmanagename', header: '项目经理'},\r\n");
      out.write("\t\t\t\t{field: 'department', header: '出单部门'},\r\n");
      out.write("\t\t\t\t{field: 'contractstatus', header: '项目状态'}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"id_table_container_rent_income\"></div>\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /base/var.jsp(3,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope['login_userlanguage'].language == 'zh'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_c_005fset_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /base/var.jsp(4,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setVar("currentLocale");
    // /base/var.jsp(4,1) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f0.setValue("zh_CN");
    int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
    if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /base/var.jsp(6,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope['login_userlanguage'].language == 'en'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_c_005fset_005f1(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /base/var.jsp(7,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setVar("currentLocale");
    // /base/var.jsp(7,1) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f1.setValue("en_US");
    int _jspx_eval_c_005fset_005f1 = _jspx_th_c_005fset_005f1.doStartTag();
    if (_jspx_th_c_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f2.setParent(null);
    // /base/var.jsp(10,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setVar("currentTimestamp");
    // /base/var.jsp(10,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f2.setValue("20140109000");
    int _jspx_eval_c_005fset_005f2 = _jspx_th_c_005fset_005f2.doStartTag();
    if (_jspx_th_c_005fset_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f3.setParent(null);
    // /base/var.jsp(11,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setVar("currentSkin");
    // /base/var.jsp(11,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f3.setValue("blue3");
    int _jspx_eval_c_005fset_005f3 = _jspx_th_c_005fset_005f3.doStartTag();
    if (_jspx_th_c_005fset_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fset_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f4 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_005fset_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fset_005f4.setParent(null);
    // /base/var.jsp(12,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setVar("urlPrefix");
    // /base/var.jsp(12,0) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}/table/getTableData.action?tracywindyRandom=1&decorate=none&xmlFileName=", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /base/var.jsp(12,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fset_005f4.setScope("request");
    int _jspx_eval_c_005fset_005f4 = _jspx_th_c_005fset_005f4.doStartTag();
    if (_jspx_th_c_005fset_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_005fset_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fscope_005fnobody.reuse(_jspx_th_c_005fset_005f4);
    return false;
  }
}
