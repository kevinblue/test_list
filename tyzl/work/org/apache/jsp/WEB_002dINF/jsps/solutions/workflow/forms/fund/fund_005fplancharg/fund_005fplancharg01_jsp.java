/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-02-22 10:14:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fplancharg;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fplancharg01_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../fund/fund_comm/fund_comm_js_function.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar isFirstPage=true;\r\n");
      out.write("\t//是否保存   \r\n");
      out.write("\tfunction workflowSaveCallBack()//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证\r\n");
      out.write("\t{\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"tabApprovalDeatils\");\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//保存成功提交后，后台返回\r\n");
      out.write("\tfunction saveCallBack() {\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("\t//是否提交    \r\n");
      out.write("\tfunction workflowSubmitCallBack(buttonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证\r\n");
      out.write("\t{\r\n");
      out.write("\t\t if (miniui_ext.submitFormValidation([\"contract_change_info_form\"]) == false) return;\r\n");
      out.write("        //检查资金付款是否有重复\r\n");
      out.write("        if(!checkfundFeetypeListSame(\"plancharg_collect\")){return false;}\r\n");
      out.write("        if(!checkfundFeetypeListSame(\"plancharg_pay\")){return false;}\r\n");
      out.write("\t\t//json域 保存\r\n");
      out.write("\t\tminiui_ext.saveIncludeData(\"tabApprovalDeatils\");\r\n");
      out.write("\t\t\r\n");
      out.write("\t    return true;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<!--多行控件  -->\r\n");
      out.write("<input  style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_plancharg_collect_str\" name=\"json_plancharg_collect_str\" value='");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("'></input>\r\n");
      out.write("<input  style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_plancharg_pay_str\" \tname=\"json_plancharg_pay_str\" value='");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("'></input>\r\n");
      out.write("<input  style=\"display:none;\"\tclass=\"mini-textarea\" id=\"id_json_fund_plan_old_str\" \tname=\"json_fund_plan_old_str\" value='");
      if (_jspx_meth_mini_005fparam_005f2(_jspx_page_context))
        return;
      out.write("'></input>\r\n");
      out.write("<div class=\"fillTableContainer\">\r\n");
      out.write("   <table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t     <td colspan=4>\r\n");
      out.write("\t\t\t <!-- 合同基本信息 --> \r\n");
      out.write("\t\t\t ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../contract/contract_comm/contract_base_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t </td>\r\n");
      out.write("\t   </tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\t\t\r\n");
      out.write("<div id=\"tabApprovalDeatils\" class=\"mini-tabs\" activeIndex=\"0\" onactivechanged=\"changTab\" style=\"width:100%;\" bodyStyle=\"padding:0;border:0;\">\r\n");
      out.write("    <div title=\"变更说明\" name=\"contract_fund_plan_change\" iconCls=\"icon-cut\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../contract/contract_comm/contract_change_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"投放明细\" name=\"fund_put\" iconCls=\"icon-expand\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../fund/fund_comm/fund_put_cur_money.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"资金收款计划明细\" name=\"plancharg_collect\" iconCls=\"icon-cut\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "fund_plancharg_collect_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"资金付款计划明细\" name=\"plancharg_pay\" iconCls=\"icon-cut\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "fund_plancharg_pay_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div title=\"资金收付款计划历史\" name=\"fund_plan_old\" iconCls=\"icon-cut\">\r\n");
      out.write("\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../fund_comm/fund_fund_plan_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\t<div title=\"联合承租人\" name=\"union_cust\" iconCls=\"icon-cut\" >\r\n");
      out.write("\t\t     ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../fund_comm/fund_union_cust.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isView", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("true", request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("    </div> \r\n");
      out.write("\t \r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("//检查费期次从重复\r\n");
      out.write("function checkfundFeetypeListSame(tableid){\r\n");
      out.write("\tvar idsp=new Array();\r\n");
      out.write("\tvar idsr=new Array();\r\n");
      out.write("\tvar cmessage=new Array();\r\n");
      out.write("\tvar currentTable1 = mini.get(tableid);\r\n");
      out.write("    var tablesdata=currentTable1.getData();\r\n");
      out.write("    var jsond=tablesdata;\r\n");
      out.write("    if(jsond.length>0){\r\n");
      out.write("      for(var i=0;i<jsond.length;i++){\r\n");
      out.write("    \t  idsr.push(jsond[i].feetypename+\"第\"+jsond[i].paymentid+\"期\");\r\n");
      out.write("      } \r\n");
      out.write("    }\r\n");
      out.write("    idsr.sort();\r\n");
      out.write("    for(var i=0;i<idsr.length-1;i++){\r\n");
      out.write("        for(var j=i+1;j<idsr.length;j++){\r\n");
      out.write("       if(idsr[i]==idsr[j]){cmessage.push(idsr[i]);}\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("    if(cmessage.length>0){\r\n");
      out.write("    \t mini.alert(\"费用类型为\"+cmessage+\"的计划重复\"); \r\n");
      out.write("         return false;\r\n");
      out.write("    } \r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t//单个table 只读\r\n");
      out.write("\tminiui_ext.initformenabled(\"contract_base_info_form\");\r\n");
      out.write("\t//mini.get(\"contract_number\").enable();//单个字段不只读\r\n");
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

  private boolean _jspx_meth_mini_005fparam_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f0 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(30,128) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("json_plancharg_collect_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(30,128) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setDefaultValue("[]");
    int _jspx_eval_mini_005fparam_005f0 = _jspx_th_mini_005fparam_005f0.doStartTag();
    if (_jspx_th_mini_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(31,121) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("json_plancharg_pay_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(31,121) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setDefaultValue("[]");
    int _jspx_eval_mini_005fparam_005f1 = _jspx_th_mini_005fparam_005f1.doStartTag();
    if (_jspx_th_mini_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f2 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f2.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(32,121) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setName("json_fund_plan_old_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg01.jsp(32,121) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setDefaultValue("[]");
    int _jspx_eval_mini_005fparam_005f2 = _jspx_th_mini_005fparam_005f2.doStartTag();
    if (_jspx_th_mini_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
    return false;
  }
}