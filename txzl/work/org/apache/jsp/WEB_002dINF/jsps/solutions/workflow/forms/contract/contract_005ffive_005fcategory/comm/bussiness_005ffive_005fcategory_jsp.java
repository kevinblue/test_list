/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-11 11:09:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005ffive_005fcategory.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bussiness_005ffive_005fcategory_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div id=\"bussiness_five_category_form\" title=\"五级分类\">\r\n");
      out.write("\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" style=\"border: 1px solid #99CCFF;\">\r\n");
      out.write("\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\" width='12%'>合同五级分类：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\" width='38%'>\r\n");
      out.write("\t\t\t\t<input id=\"five_category.contractfive_business\" \r\n");
      out.write("\t\t\t\tlabel=\"合同五级分类：\"\r\n");
      out.write("\t\t\t\t\tname=\"five_category.contractfive_business\" \r\n");
      out.write("\t\t\t\t\tclass=\"mini-combobox\" \r\n");
      out.write("\t\t\t\t\trequired=\"true\"\r\n");
      out.write("\t\t\t\t\ttextField=\"name\"\r\n");
      out.write("\t\t\t\t\tvalueField=\"value\"  \r\n");
      out.write("\t\t\t\t    dataField=\"datas\"\r\n");
      out.write("\t\t\t\t    allowInput=\"false\" \r\n");
      out.write("\t\t\t\t    data-options=\"{_params:{pid:'five_class'}}\" \r\n");
      out.write("\t\t\t\t    onbeforeshowpopup=\"onbeforeshowpopup\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.contractfive_business'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\ttext=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_five_category.contractfive_business']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\tonvaluechanged=\"comboboxChanged\"\r\n");
      out.write("\t\t\t\t/>\r\n");
      out.write("\t\t\t\t<input label=\"合同五级分类：\"  id=\"rawValue_five_category.contractfive_business\" name=\"rawValue_five_category.contractfive_business\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_five_category.contractfive_business']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none\"/>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\" width='12%'>五级分类日期：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\" width='38%'>\r\n");
      out.write("\t\t\t\t<input label=\"五级分类日期：\" id=\"five_category.contractfivedate_business\" name=\"five_category.contractfivedate_business\" class=\"mini-datepicker\" required=\"true\" allowInput=\"false\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.contractfivedate_business'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<!-- 质押股权总价，质押股权比例，股权质押评估值，电费质押评估值，设备抵押评估值，其他抵质押物评估值\r\n");
      out.write("\t\tfive_category.pledgesumprice,\r\n");
      out.write("\t\tfive_category.pledgeratio,\r\n");
      out.write("\t\tfive_category.pledgeratingvalue,\r\n");
      out.write("\t\tfive_category.electricratingvalue,\r\n");
      out.write("\t\tfive_category.equipratingvalue,\r\n");
      out.write("\t\tfive_category.otherpledgeratingvalue\r\n");
      out.write("\t\t -->\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">质押股权总价：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.pledgesumprice\" id =\"five_category.pledgesumprice\" vtype=\"float\"  class=\"mini-textbox\" label=\"质押股权总价\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.pledgesumprice']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">质押股权比例(%)：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.pledgeratio\" id =\"risk_name\"  vtype=\"float\" class=\"mini-textbox\" label=\"质押股权比例\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.pledgeratio']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t</tr >\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">股权质押评估值：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.pledgeratingvalue\" id =\"five_category.pledgeratingvalue\" vtype=\"float\" class=\"mini-textbox\" label=\"股权质押评估值\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.pledgeratingvalue']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">电费质押评估值：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.electricratingvalue\" id =\"five_category.electricratingvalue\" vtype=\"float\" class=\"mini-textbox\" label=\"股权质押评估值\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.electricratingvalue']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t</tr >\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">设备抵押评估值：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.equipratingvalue\" id =\"five_category.equipratingvalue\" vtype=\"float\"  class=\"mini-textbox\" label=\"设备抵押评估值\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.equipratingvalue']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t<td class=\"td-content-title\" width=\"12%\">其他抵质押物评估值：</td>\r\n");
      out.write("\t             <td class=\"td-content\" width=\"38%\"><input name=\"five_category.otherpledgeratingvalue\" id =\"five_category.otherpledgeratingvalue\" vtype=\"float\"  class=\"mini-textbox\" label=\"其他抵质押物评估值\" required=true type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.otherpledgeratingvalue']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t</tr >\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">五级分类说明：</td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input id=\"five_category.explain_business\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.explain_business\" required=\"true\" class=\"mini-textarea\" label=\"五级分类说明：\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.explain_business'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t   <td class=\"td-content\" width=\"38%\"><input name=\"five_category.docid\" id =\"five_category.docid\" style=\"display:none\" class=\"mini-textbox\" label=\"流程编号\"  type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.docid']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<!-- badinfluenceofasset,treatmentofsameasset,classfyreason,expectreason,assetmeasurement -->\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">对资产有不利影响的因素 </td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input label=\"对资产有不利影响的因素\" id=\"five_category.badinfluenceofasset\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.badinfluenceofasset\" required=\"true\" class=\"mini-textarea\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.badinfluenceofasset'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t  \r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">同类资产处置情况 ：</td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input label=\"同类资产处置情况 ：\" id=\"five_category.treatmentofsameasset\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.treatmentofsameasset\" required=\"true\" class=\"mini-textarea\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.treatmentofsameasset'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">租赁资产分类理由  ：</td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input label=\"租赁资产分类理由  ：\" id=\"five_category.classfyreason\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.classfyreason\" required=\"true\" class=\"mini-textarea\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.classfyreason'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">租赁资产分类展望及理由 </td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input label=\"租赁资产分类展望及理由\" id=\"five_category.expectreason\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.expectreason\" required=\"true\" class=\"mini-textarea\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.expectreason'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">建议租赁资产处置措施 </td>\r\n");
      out.write("\t\t\t<td colspan=\"3\" style=\"padding-top: 5px;padding-bottom: 5px;\">\r\n");
      out.write("\t\t\t\t<input label=\"建议租赁资产处置措施 \" id=\"five_category.assetmeasurement\" style=\"width: 73.5%;height: 100px;\" name=\"five_category.assetmeasurement\" required=\"true\" class=\"mini-textarea\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['five_category.assetmeasurement'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\t\t\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields(\"bussiness_five_category_form\");};\r\n");
      out.write("});\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar flowid=flowUnid;\r\n");
      out.write("\tmini.get(\"five_category.docid\").setValue(flowid);\r\n");
      out.write("});\r\n");
      out.write("\r\n");
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
