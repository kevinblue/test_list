/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-14 09:22:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005frent_005finvoice_005ftype_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<input name=\"proj_invoice_type.id\" id=\"proj_invoice_type.id\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("<input id=\"rawValue_proj_invoice_type.receivedinvoicetype\" name=\"rawValue_proj_invoice_type.receivedinvoicetype\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.receivedinvoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\"  style=\"display:none\">\r\n");
      out.write("<input id=\"rawValue_proj_invoice_type.rentinvoicetype\" name=\"rawValue_proj_invoice_type.rentinvoicetype\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.rentinvoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\"  style=\"display:none\">\r\n");
      out.write("<input id=\"rawValue_proj_invoice_type.invoicetype\" name=\"rawValue_proj_invoice_type.invoicetype\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.invoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\"  style=\"display:none\"/>\r\n");
      out.write("<input id=\"rawValue_proj_invoice_type.taxregtype\" name=\"rawValue_proj_invoice_type.taxregtype\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.taxregtype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" class=\"mini-textbox\" style=\"display:none\"/>\r\n");
      out.write("\r\n");
      out.write(" <div id=\"proj_proj_invoice_type_form\" title=\"租金发票类型\">\r\n");
      out.write("\t<table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%;border: 1px solid #99CCFF;\" class=\"fillTable\">\r\n");
      out.write("\t\t\t");
      out.write("\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">租金发票类型：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t<input id=\"proj_invoice_type.rentinvoicetype\" name=\"proj_invoice_type.rentinvoicetype\" class=\"mini-combobox\" label=\"租金发票类型\"\r\n");
      out.write("\t\t\t\t\ttextField=\"name\"\r\n");
      out.write("\t\t\t\t\tvalueField=\"value\"\r\n");
      out.write("\t\t\t\t\tdataField=\"datas\"\r\n");
      out.write("\t\t\t\t\tallowInput=\"false\"\r\n");
      out.write("\t\t\t\t\tdata-options=\"{_params:{pid:'rent_invoice_type'}}\" \r\n");
      out.write("\t\t\t\t    onbeforeshowpopup=\"onbeforeshowpopup\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.rentinvoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\ttext=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.rentinvoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\tonvaluechanged=\"comboboxChanged\" />\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">发票种类：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t<input id=\"proj_invoice_type.invoicetype\" name=\"proj_invoice_type.invoicetype\" class=\"mini-combobox\" style=\"width: 200px;\" label=\"发票种类\"\r\n");
      out.write("\t\t\t\t\ttextField=\"name\"\r\n");
      out.write("\t\t\t\t\tvalueField=\"value\"\r\n");
      out.write("\t\t\t\t\tdataField=\"datas\"\r\n");
      out.write("\t\t\t\t\tallowInput=\"false\" \r\n");
      out.write("\t\t\t\t\tdata-options=\"{_params:{pid:'invoicetype'}}\" \r\n");
      out.write("\t\t\t\t    onbeforeshowpopup=\"onbeforeshowpopup\"\r\n");
      out.write("\t\t\t\t\tvalue=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.invoicetype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\ttext=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.invoicetype']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"\r\n");
      out.write("\t\t\t\t\tonvaluechanged=\"comboboxChanged\"  />\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tbody id=\"invoice_tbody\">\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">纳税人类别：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t<input id=\"proj_invoice_type.taxregtype\" name=\"proj_invoice_type.taxregtype\" class=\"mini-combobox\" textField=\"name\"  label=\"纳税人类别\" \r\n");
      out.write("\t\t                  \t   valueField=\"value\"  \r\n");
      out.write("\t\t\t\t\t\t\t   dataField=\"datas\"\r\n");
      out.write("\t\t\t\t\t\t\t   allowInput=\"false\" \r\n");
      out.write("\t\t\t\t\t\t\t   data-options=\"{_params:{pid:'tax_level_name'}}\" \r\n");
      out.write("\t\t\t\t\t\t\t   onbeforeshowpopup=\"onbeforeshowpopup\"\r\n");
      out.write("\t\t\t\t\t\t\t   value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.taxregtype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t\t   text=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['rawValue_proj_invoice_type.taxregtype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" \r\n");
      out.write("\t\t\t\t\t\t\t   onvaluechanged=\"comboboxChanged2\" \r\n");
      out.write("\t\t\t\t />\t \r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">纳税人识别号：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"proj_invoice_type.taxregcode\" name=\"proj_invoice_type.taxregcode\" class=\"mini-textbox\" label=\"纳税人识别号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.taxregcode'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"true\" /></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\" width=\"12%\">开户行：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\" width=\"38%\"><input id=\"proj_invoice_type.taxbank\" name=\"proj_invoice_type.taxbank\" class=\"mini-textbox\" label=\"开户行\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.taxbank'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"true\" /></td>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\" width=\"12%\">开户账号：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\" width=\"38%\"><input id=\"proj_invoice_type.taxacc\" name=\"proj_invoice_type.taxacc\" class=\"mini-textbox\" label=\"开户账号\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.taxacc'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"true\" /></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">开票地址：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"proj_invoice_type.invoiceadd\" name=\"proj_invoice_type.invoiceadd\" class=\"mini-textbox\" label=\"开票地址\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.invoiceadd'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"true\" /></td>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">开票电话：</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"proj_invoice_type.invoicephone\" name=\"proj_invoice_type.invoicephone\" class=\"mini-textbox\" label=\"开票电话\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.invoicephone'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" allowInput=\"true\" /></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("</tbody>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var showTools = true;\r\n");
      out.write("if('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;};\r\n");
      out.write("if(showTools==false){\r\n");
      out.write("\tminiui_ext.disableFormFields(\"proj_proj_invoice_type_form\");\r\n");
      out.write("}\r\n");
      out.write("//控制必填 一般纳税人必填\r\n");
      out.write("if(\"tax_level_name.1\"==\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_invoice_type.taxregtype'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"){\r\n");
      out.write("\tminiui_ext.setFieldsRequired(\"proj_proj_invoice_type_form\",true);\r\n");
      out.write("}else{\r\n");
      out.write("\tminiui_ext.setFieldsRequired(\"proj_proj_invoice_type_form\",false);\r\n");
      out.write("\t//miniui_ext.setFieldsRequired(\"proj_invoice_type.receivedinvoicetype\",true);\r\n");
      out.write("}  \r\n");
      out.write("/* \r\n");
      out.write("*  received_invoice_type.01\t增值税专用发票（17%，有税务监制章）\r\n");
      out.write("*  received_invoice_type.02\t正规收据（有财政监制章）\r\n");
      out.write("*  received_invoice_type.03\t增值税专用发票（6%，有税务监制章）\r\n");
      out.write("*  received_invoice_type.04\t零税率发票（有税务监制章）\r\n");
      out.write("*  received_invoice_type.05\t白条收据\r\n");
      out.write("*  received_invoice_type.06\t未取得合规扣额凭证\r\n");
      out.write("*  \r\n");
      out.write("*   invoicetype1\t增值税专用发票\r\n");
      out.write("*   invoicetype2\t增值税普通发票\r\n");
      out.write("*   invoicetype4\t增专、增普混合开票\r\n");
      out.write("*  \r\n");
      out.write("*   \r\n");
      out.write("*  本金开收据、利息开发票   invoice_type06\r\n");
      out.write("*  本金开普票,利息开专票     invoice_type07\r\n");
      out.write("*  每期租金开票                     invoice_type03\r\n");
      out.write("*  \r\n");
      out.write("*/\r\n");
      out.write("var invoiceobj = {'invoicetype1':'增值税专用发票','invoicetype2':'增值税普通发票','invoicetype4':'增专、增普混合开票'};\r\n");
      out.write("var projinvoiceobj = {'invoice_type06':'每期开具利息发票和本金收据（不开具本金发票）','invoice_type07':'每期本金开普票,利息开专票','invoice_type03':'发票分期开具，按每期租金开票'};\r\n");
      out.write("function receivedInvoies(e){\r\n");
      out.write("\tvar isYBNSR = mini.get(\"proj_invoice_type.taxregtype\").getValue()==\"tax_level_name.1\"; //是否一般纳税人\r\n");
      out.write("\t//var receivedinvoicetype= mini.get(\"proj_invoice_type.receivedinvoicetype\").getValue();\r\n");
      out.write("\tif(isYBNSR){\r\n");
      out.write("\t\t//一般纳税人\r\n");
      out.write("\t\tswitch(receivedinvoicetype){\r\n");
      out.write("\t\tcase 'received_invoice_type.01':\r\n");
      out.write("\t\t\t//增值税专用发票  每期租金开票\r\n");
      out.write("\t\t\tassignment(\"invoicetype1\",\"invoice_type03\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.02':\r\n");
      out.write("\t\t\t//增专增普混合开票  本金开普票,利息开专票\r\n");
      out.write("\t\t\tassignment(\"invoicetype4\",\"invoice_type07\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.03':\r\n");
      out.write("\t\t\t//增专增普混合开票\t本金开普票,利息开专票\r\n");
      out.write("\t\t\tassignment(\"invoicetype4\",\"invoice_type07\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.04':\r\n");
      out.write("\t\t\t//增专增普混合开票\t本金开普票,利息开专票\r\n");
      out.write("\t\t\tassignment(\"invoicetype4\",\"invoice_type07\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.05':\r\n");
      out.write("\t\t\t//增值税专用发票\t本金开收据、利息开发票\r\n");
      out.write("\t\t\tassignment(\"invoicetype1\",\"invoice_type06\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.06':\r\n");
      out.write("\t\t\t//增值税专用发票\t本金开收据、利息开发票\r\n");
      out.write("\t\t\tassignment(\"invoicetype1\",\"invoice_type06\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tswitch(receivedinvoicetype){\r\n");
      out.write("\t\t//非一般纳税人\r\n");
      out.write("\t\tcase 'received_invoice_type.01':\r\n");
      out.write("\t\t\t//增值税普通发票  每期租金开票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type03\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.02':\r\n");
      out.write("\t\t\t//增值税普通发票\t每期租金开票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type03\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.03':\r\n");
      out.write("\t\t\t//增值税普通发票\t每期租金开票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type03\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.04':\r\n");
      out.write("\t\t\t//增值税普通发票\t每期租金开票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type03\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.05':\r\n");
      out.write("\t\t\t//增值税专用发票\t本金开收据、利息开发票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type06\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\tcase 'received_invoice_type.06':\r\n");
      out.write("\t\t\t//增值税专用发票\t本金开收据、利息开发票\r\n");
      out.write("\t\t\tassignment(\"invoicetype2\",\"invoice_type06\");\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(typeof(e)!='undefined'){\r\n");
      out.write("\t\tcomboboxChanged(e);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function\tcomboboxChanged2(e){\r\n");
      out.write("\tchecktaxregtype(e);\r\n");
      out.write("\tcomboboxChanged(e);\r\n");
      out.write("}\r\n");
      out.write("function assignment(invoicetype,rentinvoicetype){\r\n");
      out.write("\tmini.get(\"proj_invoice_type.invoicetype\").setValue(invoicetype);\r\n");
      out.write("\tmini.get(\"proj_invoice_type.invoicetype\").setText(invoiceobj[invoicetype]);\r\n");
      out.write("\tmini.get(\"rawValue_proj_invoice_type.invoicetype\").setValue(invoiceobj[invoicetype]);\r\n");
      out.write("\t\r\n");
      out.write("\tmini.get(\"proj_invoice_type.rentinvoicetype\").setValue(rentinvoicetype);\r\n");
      out.write("\tmini.get(\"proj_invoice_type.rentinvoicetype\").setText(projinvoiceobj[rentinvoicetype]);\r\n");
      out.write("\tmini.get(\"rawValue_proj_invoice_type.rentinvoicetype\").setValue(projinvoiceobj[rentinvoicetype]);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* function checktaxregtype(e){\r\n");
      out.write("\tvar sender = mini.get(\"proj_invoice_type.taxregtype\");\r\n");
      out.write("\tif(\"一般纳税人\" == sender.text){\r\n");
      out.write("\t\t$('input').attr('required', true);\t\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t$('input').attr('required', false);\t\r\n");
      out.write("\t}\r\n");
      out.write("} */\r\n");
      out.write("function checktaxregtype(e){\r\n");
      out.write("\tvar sender = mini.get(\"proj_invoice_type.taxregtype\");\r\n");
      out.write("\tvar str1 =\"proj_invoice_type.taxregcode\";\r\n");
      out.write("\tvar str2 =\"proj_invoice_type.taxbank\";\r\n");
      out.write("\tvar str3=\"proj_invoice_type.taxacc\";\r\n");
      out.write("\tvar str4=\"proj_invoice_type.invoiceadd\";\r\n");
      out.write("\tvar str5=\"proj_invoice_type.invoicephone\";\r\n");
      out.write("\tvar str6=\"proj_invoice_type.corpusinvoiceratio\";\r\n");
      out.write("\tvar str7=\"proj_invoice_type.interestinvoiceratio\";\r\n");
      out.write("\tvar str8=\"proj_invoice_type.handlingchargeinvoiceratio\";\r\n");
      out.write("\tvar str9=\"proj_invoice_type.managementinvoiceratio\";\r\n");
      out.write("\tif(\"一般纳税人\" == sender.text){\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str1,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str2,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str3,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str4,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str5,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str6,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str7,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str8,true);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str9,true); \r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str1,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str2,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str3,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str4,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str5,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str6,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str7,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str8,false);\r\n");
      out.write("\t\tminiui_ext.setFieldsRequired(str9,false);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
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
