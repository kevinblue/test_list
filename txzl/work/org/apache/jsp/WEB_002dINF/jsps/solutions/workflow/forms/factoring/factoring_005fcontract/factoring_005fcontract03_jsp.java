/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-10 02:30:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.factoring.factoring_005fcontract;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class factoring_005fcontract03_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t//流程保存之前回调\r\n");
      out.write("\tfunction workflowSaveCallBack() {\r\n");
      out.write("\t\t//miniui_ext.saveIncludeData(\"tabApprovalDeatils\");\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"conditionDeatils\");\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//保存成功提交后，后台返回回调\r\n");
      out.write("\tfunction saveCallBack() {\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//流程提交之前回调\r\n");
      out.write("\tfunction workflowSubmitCallBack(buttonText) {\r\n");
      out.write("\t\tif (miniui_ext.submitFormValidation([\"contract_base_info_form\"]) == false) return false;\r\n");
      out.write("\t\t/* //if(!checkCalIsSame()){return ;}\r\n");
      out.write("\t\tif(checkEquipIsNull()==false) return;\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"tabDetails\");\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"conditionDeatils\");\r\n");
      out.write("\t\tif (miniui_ext.submitFormValidation([\"contract_base_info_form\",\"contract_rent_invoice_type_form\",\"contract_signatory_form\"]) == false) return false;\r\n");
      out.write("\t/* \tif(\"\" ==mini.get(\"contract_word\").getData()){\r\n");
      out.write("\t\t\tmini.alert(\"请生成合同文件再提交！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\tif(mini.getbyName(\"rawValue_contract_info.leasform\").getValue()==\"直租\"){\r\n");
      out.write("\t\t\tvar data=mini.get('contract_equip').getData();\r\n");
      out.write("\t    \tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t    \t\tif(data[i].vndrtype==\"\"){\r\n");
      out.write("\t    \t\t\tmini.alert(\"直租时，请在租赁物明细中，填写贸易类型！\");\r\n");
      out.write("\t    \t\t\treturn false;\r\n");
      out.write("\t    \t\t}\r\n");
      out.write("\t    \t\treturn true;\r\n");
      out.write("\t    \t}\r\n");
      out.write("\t\t};*/\r\n");
      out.write("\t\treturn true; \r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!--多行控件  -->\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_invoice_str\" name=\"json_contract_invoice_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_invoice_str ? \"[]\" : json_contract_invoice_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_equip_str\" name=\"json_contract_equip_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_equip_str ? \"[]\" : json_contract_equip_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_guarantee_method_str\" name=\"json_contract_guarantee_method_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_guarantee_method_str ? \"[]\" : json_contract_guarantee_method_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_guarantee_equip_str\" name=\"json_contract_guarantee_equip_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_guarantee_equip_str ? \"[]\" : json_contract_guarantee_equip_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_supplier_str\" name=\"json_contract_supplier_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_supplier_str ? \"[]\" : json_contract_supplier_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_word_str\" name=\"json_contract_word_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_word_str ? \"[]\" : json_contract_word_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<input style=\"display: none;\" class=\"mini-textarea\" id=\"id_json_contract_premise_str\" name=\"json_contract_premise_str\" value='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_premise_str ? \"[]\" : json_contract_premise_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<div class=\"fillTableContainer\">\r\n");
      out.write("\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=4>\r\n");
      out.write("\t\t\t\t<!-- 插入保理业务基本信息 --> ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contract_factoring_base_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"tabApprovalDeatils\" class=\"mini-tabs\" activeIndex=\"0\" onactivechanged=\"changTab\" style=\"width: 100%;\" bodyStyle=\"border:0px;\">\r\n");
      out.write("\t<div title=\"发票信息\" name=\"proj_invoice\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contract_invoice.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"保理报价方案\" name=\"business_condition\" iconCls=\"icon-cut\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../reckon/rent_reckon/factoring_main_5_1.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"供应商信息\" name=\"supplier_message\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contract_supplier_message.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"贸易基础交易情况\" name=\"trade_based\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contrade_transaction.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write(" \t<div title=\"签约方\" name=\"contract_supplier\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contract_signatory.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div> \r\n");
      out.write(" \t<div title=\"生成合同\" name=\"contract_word\" iconCls=\"icon-node\">\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../factoring_comm/contract_word.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("\t</div>  \r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("<script>\r\n");
      out.write("jQuery(function(){\t\r\n");
      out.write("\tminiui_ext.disableFormFields(\"contract_base_info_form\");\r\n");
      out.write("});\r\n");
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
