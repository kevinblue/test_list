/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-02-08 07:43:08 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fput.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class caution_005fmoney_005frefund_005fdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody != null) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.release();
    if (_005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody != null) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.release();
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
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"caution_money_refund_detail\",\r\n");
      out.write("\t\trenderTo: \"id_table_caution_money_refund_detail\",\r\n");
      out.write("\t\twidth : globalClientWidth-30,\r\n");
      out.write("\t\theight : 400,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("'),\r\n");
      out.write("\t\ttools: ['edit', '-', 'remove'],\r\n");
      out.write("// \t\tremoveOperCallBack:function(miniTable, rowDatas){\r\n");
      out.write("// \t\t      for(j=0;j<rowDatas.length;j++){\r\n");
      out.write("//                 var pid=rowDatas[j].fundfundchargeplan;\r\n");
      out.write("//                 var guarantable=mini.get(\"put_current\");\r\n");
      out.write("//                 var guarantableData=guarantable.getData();\r\n");
      out.write("//                 for(var i=0;i<guarantableData.length;i++){\r\n");
      out.write("//                      if(guarantableData[i].pid==pid){\r\n");
      out.write("//                      \tguarantable.removeRow (guarantableData[i]);\r\n");
      out.write("//                      }\r\n");
      out.write("//                 } \r\n");
      out.write("// \t\t      } \r\n");
      out.write("// \t\t   return true;\r\n");
      out.write("//         },\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'pid', header: 'pid',visible: false},\r\n");
      out.write("\t\t\t\t\t{field:'fundfundchargeplan',header:'付款计划',visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'paymentid', header: '付款编号',formEditorConfig:{readOnly : true}}, \r\n");
      out.write("\t\t\t\t\t{field: 'comparemoney', header: '比较金额', visible: false,formEditorConfig:{readOnly : true}}, \r\n");
      out.write("\t\t\t\t\t{field: 'feetype', header: '费用类型',formEditorConfig:{}, visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'feetypename', header: '费用类型',formEditorConfig:{readOnly : true}},\r\n");
      out.write("\t\t\t\t\t{field: 'paytype', header: '支付类型',formEditorConfig:{newLine:true}, visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'paytypename', header: '支付类型', visible: false,formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t\t\t{field: 'factmoney', header: '付款金额',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,required: true}},\r\n");
      out.write("\t\t\t\t\t{field: 'comparemoney', header: '比较付款金额', visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'factadjust', header: '调整付款金额', visible: false},\r\n");
      out.write("\t\t\t\t\t{field: 'settlemethod', header: '结算方式', visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\t\t\tvalueField: 'value',\r\n");
      out.write("\t\t\t\t\t\tallowInput: true,\r\n");
      out.write("\t\t\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\t\t\tcolspan : 3,\t\r\n");
      out.write("\t\t\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\t\t\t\tpid: 'PayFund',\r\n");
      out.write("\t\t\t\t\t\t\t\txmlFileName: 'combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t}}},\r\n");
      out.write("\t\t\t\t\t{field: 'settlemethodname', header: '结算方式',formEditorConfig:{fieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t  fillFromFieldName : 'settlemethod',\r\n");
      out.write("\t\t\t\t\t\tfillProperty : 'name'}},\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{field: 'factdate', header: '付款日期',formEditorConfig:{newLine:true,required: true,type:'date'}},\r\n");
      out.write("\t\t\t\t\t{field: 'accountingdate', header: '会计处理日',formEditorConfig:{type:'date'}},\r\n");
      out.write("\t\t\t\t\t{field: 'ffcmemo', header: '备注',formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\ttype: 'textarea',\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\tcolspan : 3,\r\n");
      out.write("\t\t\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\t\t\theight:70\r\n");
      out.write("\t\t\t\t\t}}\r\n");
      out.write("\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function getCautionMoney(){\r\n");
      out.write("\tvar tableData=mini.get(\"caution_money_refund_detail\").getData();\r\n");
      out.write("\tvar summoney=0;\r\n");
      out.write("\tfor(var i=0;i<tableData.length;i++){\r\n");
      out.write("\t\tsummoney=parseFloat(summoney)+parseFloat(tableData[i].factmoney);\r\n");
      out.write("\t}\r\n");
      out.write("\tsummoney=parseFloat(summoney).toFixed(2);\r\n");
      out.write("\treturn summoney;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_caution_money_refund_detail\" style=\"width: 100%;height: 100%;\"></div>\r\n");
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_put/comm/caution_money_refund_detail.jsp(6,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("isView");
    int _jspx_eval_mini_005fparam_005f0 = _jspx_th_mini_005fparam_005f0.doStartTag();
    if (_jspx_th_mini_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f1 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f1.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_put/comm/caution_money_refund_detail.jsp(18,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("json_caution_money_refund_detail_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_put/comm/caution_money_refund_detail.jsp(18,25) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setDefaultValue("[]");
    int _jspx_eval_mini_005fparam_005f1 = _jspx_th_mini_005fparam_005f1.doStartTag();
    if (_jspx_th_mini_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
    return false;
  }
}
