/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-22 01:53:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.factoring.factoring_005fred;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class factoring_005fredout_005fcurrent_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true')\r\n");
      out.write("\t{\r\n");
      out.write("\t\tshowTools = false;\r\n");
      out.write("\t}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"redout_current\",\r\n");
      out.write("\t\trenderTo: \"id_table_redout_current\",\r\n");
      out.write("\t\twidth : globalClientWidth-30,\r\n");
      out.write("\t\theight : 400,\r\n");
      out.write("\t\teditFormPopupWindowWidth : 600,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tmultiSelect : true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['edit', '-', 'remove'],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_redout_current_str ? \"[]\" : json_redout_current_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'contractid', header: 'contractid', visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'ebid', header: 'ebid', visible: false},\r\n");
      out.write("\t\t\t\t\t{field:'fundfundchargeplan',header:'收款计划',visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'ebanknumber', header: '网银编号',formEditorConfig:{readOnly : true,required: true}},\r\n");
      out.write("\t\t\t\t\t{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{}},\r\n");
      out.write("\t\t\t\t\t{field: 'settlemethodname', header: '结算方式',formEditorConfig:{readOnly : true,required: true}},\r\n");
      out.write("\t\t\t\t\t{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'feetypename', header: '费用类型',formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t\t\t{field: 'paymentid', header: '期次',formEditorConfig:{readOnly:true}},\r\n");
      out.write("\t\t\t\t\t{field: 'factmoney', header: '收/付款金额',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,required: true,readOnly:true}},\r\n");
      out.write("\t\t\t\t\t{field: 'feeadjust', header: '调整金额',summary : true,dataType : \"currency\",formEditorConfig:{readOnly:true}},\t\t\t\r\n");
      out.write("\t\t\t\t\t{field: 'paytype', header: '支付类型',formEditorConfig:{}, visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'paytypename', header: '支付类型', visible: false,formEditorConfig:{readOnly:true}},\r\n");
      out.write("\t\t\t\t\t{field: 'factdate', header: '到账时间',dateFormat : \"yyyy-MM-dd\",formEditorConfig:{newLine:true,type: 'date',required: true,readOnly : true,format: 'yyyy-MM-dd'}},\r\n");
      out.write("\t\t\t\t\t{field: 'accountingdate', header: '会计处理日',dateFormat : \"yyyy-MM-dd\",formEditorConfig:{type: 'date',required: true,format: 'yyyy-MM-dd'}},\r\n");
      out.write("\t\t\t\t\t{field: 'accountbank', header: '本方银行',formEditorConfig:{readOnly : true,newLine:true,required: true}},   \r\n");
      out.write("\t\t\t\t\t{field: 'account', header: '本方账户',formEditorConfig:{readOnly : true,required: true}},\r\n");
      out.write("\t\t\t\t\t{field: 'accnumber', header: '本方账号',formEditorConfig:{readOnly : true,newLine:true,required: true}},\r\n");
      out.write("\t\t\t\t\t{field: 'factobject', header: '付款客户',formEditorConfig:{ readOnly : true}},\r\n");
      out.write("\t\t\t\t\t{field: 'clientbank', header: '客户银行',formEditorConfig:{ newLine:true,readOnly : true}},\r\n");
      out.write("\t\t\t\t\t{field: 'clientaccount', header: '客户账户',formEditorConfig:{ readOnly : true}},\r\n");
      out.write("\t\t\t\t\t{field: 'clientaccnumber', header: '客户账号',formEditorConfig:{colspan:3,newLine:true, readOnly : true}},\r\n");
      out.write("\t\t\t\t\t{field: 'ffcmemo', header: '备注',width:280,formEditorConfig:{colspan : 3,\r\n");
      out.write("\t\t\t\t\t\twidth: 400,\r\n");
      out.write("\t\t\t\t\t\theight:70, type: 'textarea',newLine:true}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_table_redout_current\"style=\"width:100%;height:100%;\"></div>\r\n");
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
