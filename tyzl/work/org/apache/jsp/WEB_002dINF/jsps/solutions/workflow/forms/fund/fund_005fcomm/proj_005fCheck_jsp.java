/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-02-08 07:39:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005fCheck_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
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
      out.write("\r\n");
      out.write("<div id=\"projectCheckTable\" title=\"项目考核\">\r\n");
      out.write("\t<table id=\"proj_Check\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"\r\n");
      out.write("\t\tstyle=\"width: 70%; border: 2px solid #99CCFF;\" class=\"fillTable\">\r\n");
      out.write("\t\t<th bgcolor=\"#468cc8\"></th>\r\n");
      out.write("\t\t<th bgcolor=\"#468cc8\">&nbsp&nbsp&nbsp姓&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp名</th>\r\n");
      out.write("\t\t<th bgcolor=\"#468cc8\">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp分配比例</th>\r\n");
      out.write("\t\t<tr>\t<td class=\"td-content-title\">推荐人:</td>\r\n");
      out.write("\t\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t\t<input id=\"referee\" name=\"referee\" label=\"推荐人\" class=\"mini-combobox\" allowInput=\"true\"\r\n");
      out.write("\t             \t\trequired=\"false\"\r\n");
      out.write("\t\t\t\t\t\ttext=\"");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\t/>\r\n");
      out.write("\t\t\t\t\t<input id=\"rawValue_referee\" name=\"rawValue_referee\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f2(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"mini-textbox\" style=\"display: none\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"referee_Proportion_id\"\r\n");
      out.write("\t\t\t\tname=\"referee_Proportion\" class=\"mini-textbox\" vtype=\"float\"\r\n");
      out.write("\t\t\t\tallowInput=\"true\" Value=0 /> <font class=\"required-tag\">%</font></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t <td class=\"td-content-title\">主&nbsp&nbsp办:</td>\r\n");
      out.write("\t\t\t   <td class=\"td-content\">\r\n");
      out.write("\t\t\t   <input id=\"hoster\" name=\"hoster\" label=\"主办\"\r\n");
      out.write("\t\t\t\tclass=\"mini-combobox\"  required=\"true\"  allowInput=\"true\"  value=\"");
      if (_jspx_meth_mini_005fparam_005f3(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\ttext=\"");
      if (_jspx_meth_mini_005fparam_005f4(_jspx_page_context))
        return;
      out.write("\" \r\n");
      out.write("\t\t\t\t/>\r\n");
      out.write("\t\t\t\t<input id=\"rawValue_hoster\" name=\"rawValue_hoster\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f5(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"mini-textbox\" style=\"display: none\" />\r\n");
      out.write("\t\t\t\t</td> \r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"hoster_Proportion_id\"\r\n");
      out.write("\t\t\t\tname=\"hoster_Proportion\" class=\"mini-textbox\" vtype=\"float\"\r\n");
      out.write("\t\t\t\tallowInput=\"true\" Value=0 /> <font class=\"required-tag\">%</font></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">协&nbsp&nbsp办:</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"assistant\" name=\"assistant\"\r\n");
      out.write("\t\t\t\tclass=\"mini-combobox\" textField=\"name\" label=\"协办\" allowInput=\"true\"\r\n");
      out.write("\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f6(_jspx_page_context))
        return;
      out.write("\"  \r\n");
      out.write("\t\t\t\ttext=\"");
      if (_jspx_meth_mini_005fparam_005f7(_jspx_page_context))
        return;
      out.write("\" />\r\n");
      out.write("\t\t\t\t<input id=\"rawValue_assistant\" name=\"rawValue_assistant\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f8(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"mini-textbox\" style=\"display: none\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"assistant_Proportion_id\"\r\n");
      out.write("\t\t\t\tname=\"assistant_Proportion\" class=\"mini-textbox\" vtype=\"float\"\r\n");
      out.write("\t\t\t\tallowInput=\"true\" Value=0 /> <font class=\"required-tag\">%</font></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"td-content-title\">第三方:</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"thirdParty\"\r\n");
      out.write("\t\t\t\tname=\"thirdParty\" class=\"mini-combobox\" textField=\"name\" label=\"第三方\" allowInput=\"true\"\r\n");
      out.write("\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f9(_jspx_page_context))
        return;
      out.write("\"  \r\n");
      out.write("\t\t\t\ttext=\"");
      if (_jspx_meth_mini_005fparam_005f10(_jspx_page_context))
        return;
      out.write("\" />\r\n");
      out.write("\t\t\t\t<input id=\"rawValue_thirdParty\" name=\"rawValue_thirdParty\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      if (_jspx_meth_mini_005fparam_005f11(_jspx_page_context))
        return;
      out.write("\"\r\n");
      out.write("\t\t\t\t\t\tclass=\"mini-textbox\" style=\"display: none\" />\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t<td class=\"td-content\"><input id=\"third_Party_Proportion_id\"\r\n");
      out.write("\t\t\t\tname=\"third_Party_Proportion\" class=\"mini-textbox\" vtype=\"float\"\r\n");
      out.write("\t\t\t\tallowInput=\"true\" Value=0 request=\"ture\" /> <font\r\n");
      out.write("\t\t\t\tclass=\"required-tag\">%</font></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("      jQuery(function(){\r\n");
      out.write("\t\tvar strs = [{key:\"referee\",value:\"推荐人\"},{key:\"hoster\",value:\"主办\"},{key:\"assistant\",value:\"协办\"},{key:\"thirdParty\",value:\"第三方\"}];\r\n");
      out.write("\t\tfor(var i = 0 ;i<strs.length;i++){\r\n");
      out.write("\t\t\ttenwa.createQueryInput({ \r\n");
      out.write("\t\t\t\tid:strs[i].key,\r\n");
      out.write("\t\t\t\tlabel:strs[i].value,\r\n");
      out.write("\t\t\t\twindowWidth: 600,\r\n");
      out.write("\t\t\t\ttextField:\"name\",\r\n");
      out.write("\t\t      \tvalueField:\"id\",\r\n");
      out.write("\t\t\t\tonSelect: function($me, inputObj, rowData){\r\n");
      out.write("\t\t\t\t\tmini.get(\"rawValue_\"+inputObj.id).setValue(rowData.name);\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\txmlFileName: '/eleasing/workflow/proj/proj_common/creator_combox.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("      });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!--  <script type=\"text/javascript\">\r\n");
      out.write(" $(function(){\r\n");
      out.write("\t// 设置属性值\r\n");
      out.write("\t$(\"#submit\").click(function() {\r\n");
      out.write("\t\t var allocationProportion1 = parseFloat(mini.get(\"allocationProportion_id1\").getValue());\r\n");
      out.write("\t\t var allocationProportion2 = parseFloat(mini.get(\"allocationProportion_id2\").getValue());\r\n");
      out.write("\t\t var allocationProportion3 = parseFloat(mini.get(\"allocationProportion_id3\").getValue());\r\n");
      out.write("\t\t var allocationProportion4 = parseFloat(mini.get(\"allocationProportion_id4\").getValue());\r\n");
      out.write("\t\t var sum=allocationProportion1+allocationProportion2+allocationProportion3+allocationProportion4;\r\n");
      out.write("\t\t  mini.alert(sum);\r\n");
      out.write("\t\t if(sum!=100){\r\n");
      out.write("\t\t\t mini.alert(\"分配比例之和必须为100%，请修改\");\r\n");
      out.write("\t\t\t return false;\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t return true;\r\n");
      out.write("\t});\r\n");
      out.write("}); -->\r\n");
      out.write("\r\n");
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(15,12) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("rawValue_referee");
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
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f1 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f1.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(16,13) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("referee");
    int _jspx_eval_mini_005fparam_005f1 = _jspx_th_mini_005fparam_005f1.doStartTag();
    if (_jspx_th_mini_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f2 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f2.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(19,13) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setName("rawValue_referee");
    int _jspx_eval_mini_005fparam_005f2 = _jspx_th_mini_005fparam_005f2.doStartTag();
    if (_jspx_th_mini_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f3 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f3.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(30,70) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f3.setName("contract_info.projmanage");
    int _jspx_eval_mini_005fparam_005f3 = _jspx_th_mini_005fparam_005f3.doStartTag();
    if (_jspx_th_mini_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f4 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f4.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(31,10) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f4.setName("rawValue_contract_info.projmanage");
    int _jspx_eval_mini_005fparam_005f4 = _jspx_th_mini_005fparam_005f4.doStartTag();
    if (_jspx_th_mini_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f5 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f5.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f5.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(34,13) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f5.setName("rawValue_hoster");
    int _jspx_eval_mini_005fparam_005f5 = _jspx_th_mini_005fparam_005f5.doStartTag();
    if (_jspx_th_mini_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f6(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f6 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f6.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f6.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(45,11) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f6.setName("rawValue_assistant");
    int _jspx_eval_mini_005fparam_005f6 = _jspx_th_mini_005fparam_005f6.doStartTag();
    if (_jspx_th_mini_005fparam_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f6);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f7(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f7 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f7.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f7.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(46,10) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f7.setName("assistant");
    int _jspx_eval_mini_005fparam_005f7 = _jspx_th_mini_005fparam_005f7.doStartTag();
    if (_jspx_th_mini_005fparam_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f7);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f8(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f8 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f8.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f8.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(48,13) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f8.setName("rawValue_assistant");
    int _jspx_eval_mini_005fparam_005f8 = _jspx_th_mini_005fparam_005f8.doStartTag();
    if (_jspx_th_mini_005fparam_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f8);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f9(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f9 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f9.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f9.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(59,11) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f9.setName("rawValue_thirdParty");
    int _jspx_eval_mini_005fparam_005f9 = _jspx_th_mini_005fparam_005f9.doStartTag();
    if (_jspx_th_mini_005fparam_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f9);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f10(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f10 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f10.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f10.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(60,10) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f10.setName("thirdParty");
    int _jspx_eval_mini_005fparam_005f10 = _jspx_th_mini_005fparam_005f10.doStartTag();
    if (_jspx_th_mini_005fparam_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f10);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f11(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f11 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f11.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f11.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/proj_Check.jsp(62,13) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f11.setName("rawValue_thirdParty");
    int _jspx_eval_mini_005fparam_005f11 = _jspx_th_mini_005fparam_005f11.doStartTag();
    if (_jspx_th_mini_005fparam_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f11);
    return false;
  }
}