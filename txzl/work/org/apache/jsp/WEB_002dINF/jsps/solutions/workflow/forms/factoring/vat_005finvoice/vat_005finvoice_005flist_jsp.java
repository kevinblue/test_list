/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-13 06:39:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.factoring.vat_005finvoice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class vat_005finvoice_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<input style=\"display: none;\" class=\"mini-textarea\"\r\n");
      out.write("\tid=\"id_json_csut_apply_list_str\" name=\"json_csut_apply_list_str\"\r\n");
      out.write("\tvalue='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_csut_apply_list_str ? \"[]\" : json_csut_apply_list_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'></input>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/workFlowUtil.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyUtils.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyAjax.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/tenwa.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/seajs/sea.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tjQuery(function() {\r\n");
      out.write("\t\tvalueContractId = mini.getbyName(\"contract_info.contractid\").getValue();\r\n");
      out.write("\t});\r\n");
      out.write("\tjQuery(function() {\r\n");
      out.write("\t\tseajs\r\n");
      out.write("\t\t\t\t.use(\r\n");
      out.write("\t\t\t\t\t\t[ \"js/apcomponent/aptable/aptable.js\" ],\r\n");
      out.write("\t\t\t\t\t\tfunction(ApTable) {\r\n");
      out.write("\t\t\t\t\t\t\tnew ApTable(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tid : \"proj_invoice\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trenderTo : \"id_table_proj_invoice_detail\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\twidth : globalClientWidth - 30,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttitle : '进项发票信息',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\theight : 360,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tlazyLoad : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tisClickLoad : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowPager : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmultiSelect : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thiddenQueryArea : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonColSpan : 6,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonNewLine : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowToolbar : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvirtualScroll : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tcontractid : contractid\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t beforeShowWindowCallBack : function(miniTable, miniForm, operFlag) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tif (\"add\" == operFlag|| \"edit\" == operFlag) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tmini.getbyName(\"contractid\").setValue(ciid);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tmini.getbyName(\"invoicestatus\").setValue(1);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\treturn true;\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t}, \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tremoteOper : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tentityClassName : \"com.tenwa.leasing.entity.invoice.VatInvoiceInfo\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_detailinfo.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttools : [ 'add', '-', 'edit', '-',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t'remove', '-', 'exportExcel' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcolumns : [\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'indexcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'checkcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : 'id',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'contractid',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '合同ID',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'contractnumber',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '合同编号',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 120,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalue : valueContractId\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'invoiceno',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '发票号码',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tqueryConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'invoicemoney',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '发票金额',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tqueryConfig : {},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'float',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvtype : 'float',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'purchasenits',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '购货单位',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t//显示供应商名字，保存供应商ID\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'suppliername',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '供应商',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'supplier',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '供应商',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth:230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttextField : 'suppliername',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalueField : 'supplier',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_supplier.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tallowinput:false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'recorddate',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '开票日期',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth:120,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tqueryConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tallowInput : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tformat : 'yyyy-MM-dd'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tallowInput : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tdefaultValue : mini.formatDate(new Date(),\"yyyy-MM-dd\"),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'goodsname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '货物名称',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tqueryConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t//显示的是款项，存的是设备的ID\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'contractfundfundplanidname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '计划款明细',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 130,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'text',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'contractfundfundplanid',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '计划款明细 ',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttextField : 'contractfundfundplanidname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalueField : 'contractfundfundplanid',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 230,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcontractid : ciid,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/jsp/invoice_manage/vat_invoice/vat_invoice_record_equipsubentry.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tallowinput:false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'memo',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '备注',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'textarea',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : \"100%\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\theight : 70,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tcolspan : 4,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield:'invoicestatusname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader:'状态',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible:false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield:'invoicestatus',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader:'状态',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttextField:'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvalueField:'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tdata:[{name:'未提交',value:0},{name:'已提交',value:1},{name:'已确认',value:2},{name:'已退回',value:3}],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible:false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}]\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_proj_invoice_detail\" style=\"height: 100%;\"></div>\r\n");
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
