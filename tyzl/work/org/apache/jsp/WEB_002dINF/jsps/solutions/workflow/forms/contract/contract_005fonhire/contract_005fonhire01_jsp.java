/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-19 05:22:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005fonhire;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fonhire01_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("//是否保存   \r\n");
      out.write("function workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证\r\n");
      out.write("{\r\n");
      out.write("\tminiui_ext.saveIncludeData(\"tabDetails\");\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("//保存成功提交后，后台返回\r\n");
      out.write("function saveCallBack() {\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("//是否提交    \r\n");
      out.write("function workflowSubmitCallBack(buttonText)\r\n");
      out.write("{   \r\n");
      out.write("    if(checkEquipIsNull()==false) return;\r\n");
      out.write("    miniui_ext.saveIncludeData(\"tabDetails\");\r\n");
      out.write(" \treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\t//本次投放明细 设备序列号不能为空\r\n");
      out.write("function checkEquipID(){\r\n");
      out.write("\tvar t1 = mini.get(\"contract_equip\").getData();\r\n");
      out.write("\tfor(var i=0;i<t1.length;i++){\r\n");
      out.write("\t\tif((!t1[i].equipid)){\r\n");
      out.write("\t\t\tmini.alert(\"请填写设备序列号！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\tfunction isRepeat() {\r\n");
      out.write("\t\tvar t1 = mini.get(\"contract_equip\").getData();\r\n");
      out.write("\t\tvar hash = [];\r\n");
      out.write("\t\tvar idArrays = [];\r\n");
      out.write("\t\tfor (var i = 0; i < t1.length; i++) {\r\n");
      out.write("\t\t\thash.push(\"'\" + t1[i].equipid + \"'\");\r\n");
      out.write("\t\t\tidArrays.push(\"'\" + t1[i].id + \"'\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar equipids = hash.join(\",\")\r\n");
      out.write("\t\tvar s = hash.join(\",\") + \",\";\r\n");
      out.write("\t\tfor (var i = 0; i < hash.length; i++) {\r\n");
      out.write("\t\t\tif (s.replace(hash[i] + \",\", \"\").indexOf(hash[i] + \",\") > -1) { //匹配字符串S中当前i的数组值，并替换为空；在当前循环里查看S中是否有重复\r\n");
      out.write("\t\t\t\tmini.alert(\"设备序列号重复，请重新填写！\");\r\n");
      out.write("\t\t\t\treturn \"repeat\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar ids = idArrays.join(\",\");\r\n");
      out.write("\t\tvar str = \"\";\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t\turl : getRootPath() + \"/acl/checkEquipSerial.acl\",\r\n");
      out.write("\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\tcache : false,\r\n");
      out.write("\t\t\tdata : {\r\n");
      out.write("\t\t\t\t\"ids\" : ids, \"equipids\" : equipids\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\tsuccess : function(text) {\r\n");
      out.write("\t\t\t\tif (\"repeat\" == text) {\r\n");
      out.write("\t\t\t\t\tmini.alert(\"设备序列号重复，请重新填写！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tstr = text;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\treturn str;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<!--start 多行控件  -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "comm/contract_onhire_mutli_info.jsp", out, false);
      out.write("\r\n");
      out.write("<!--end 多行控件  -->\r\n");
      out.write(" \r\n");
      out.write("<div class=\"contract_onhire_form\">\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../contract_comm/contract_base_info.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t<div id=\"tabDetails\" class=\"mini-tabs\" activeIndex=\"0\" onactivechanged=\"changTab\" style=\"width: 100%;\" bodyStyle=\"border:0px;\">\r\n");
      out.write("\t\t<div title=\"投放明细\" name=\"fund_put\" iconCls=\"icon-expand\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../fund/fund_comm/fund_put_cur_money.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div title=\"租赁物明细\" name=\"contract_equip\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../contract_comm/contract_equip_detail.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div title=\"商务条件\" name=\"business_condition\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../reckon/rent_reckon/main_5_1.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isOnhire", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div title=\"资金收付款情况\" name=\"fund_plan_old\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../fund/fund_comm/fund_fund_plan_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t<div title=\"联合承租人\" name=\"union_cust\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../contract_comm/contract_union_cust.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"起租通知书\" name=\"fund_plan_old\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "start_rent_notice.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("</div>\r\n");
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