/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-21 08:20:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fguarantee_005fequip_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("' == 'true' || isViewHistoryTask == true){showTools = false;};\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"contract_guarantee_equip\",\r\n");
      out.write("\t\trenderTo: \"id_table_contract_guarantee_equip\",\r\n");
      out.write("\t\twidth: globalClientWidth - 30,\r\n");
      out.write("\t\theight: 365,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['add', '-', 'edit', '-', 'remove'],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("'),\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'equipname', header: '抵押物名称',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t              colspan:3,\r\n");
      out.write("\t\t\t\t           labelWidth:100,\r\n");
      out.write("\t\t\t\t                width:'100%',\r\n");
      out.write("\t\t\t\t            maxLength:120}},\r\n");
      out.write("\t\t\t{field:'guarantorname', header:'抵押人',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t    fillFromFieldName:'guarantor',\r\n");
      out.write("\t\t\t\t         fillProperty:'name'}},\r\n");
      out.write("\t\t\t{field:'guarantor', header:'抵押人',visible:false,\r\n");
      out.write("\t\t\t\t \t formEditorConfig:{\r\n");
      out.write("\t\t\t\t              newLine:true,\r\n");
      out.write("\t\t\t\t                 type:'queryinput',\r\n");
      out.write("\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t            textField:'name',\r\n");
      out.write("\t\t\t\t           valueField:'value',\r\n");
      out.write("\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t              colspan:3,\r\n");
      out.write("\t\t\t\t                width:\"100%\",\r\n");
      out.write("\t\t\t\t               params:{cust_type:'cust_type.guarantor',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},\r\n");
      out.write("\t\t\t{field:'equipinvoice', header:'发票号',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t  newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\tmaxLength:50}},\r\n");
      out.write("\t\t\t{field:'guarantyvalue', header:'抵质押物价值',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t required:true,\r\n");
      out.write("\t\t\t\t\t\t\t    vtype:'float',\r\n");
      out.write("\t\t\t\t\t\t   labelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t    maxLength:20}},\r\n");
      out.write("\t\t\t{field:'equipguaranteetypename', header:'抵质押方式', \r\n");
      out.write("\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t    fillFromFieldName:'equipguaranteetype',\r\n");
      out.write("\t\t\t\t         fillProperty:'name'}},\r\n");
      out.write("\t\t\t{field:'equipguaranteetype', header:'抵质押方式', visible:false,\r\n");
      out.write("\t\t\t\t \t formEditorConfig:{\r\n");
      out.write("\t\t\t\t              newLine:true,\r\n");
      out.write("\t\t\t\t                 type:'combobox',\r\n");
      out.write("\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t            textField:'name',\r\n");
      out.write("\t\t\t\t           valueField:'value',\r\n");
      out.write("\t\t\t\t           allowInput:false,\r\n");
      out.write("\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t              colspan:3,\r\n");
      out.write("\t\t\t\t                width:\"100%\",\r\n");
      out.write("\t\t\t\t               params:{pid:'guarantee_type',xmlFileName:'combos/comboDict.xml'}}},\r\n");
      out.write("\t\t\t{field:'presentvalue', header:'现值',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t  newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t    vtype:'float',\r\n");
      out.write("\t\t\t\t\t\t\tmaxLength:20}},\r\n");
      out.write("\t\t\t{field:'notaryflag', header:'是否抵质押公证', visible:true,\r\n");
      out.write("\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t         showNullItem:\"true\", \r\n");
      out.write("\t\t\t\t         nullItemText:\"\",\r\n");
      out.write("\t\t\t\t            emptyText:\"\",\r\n");
      out.write("\t\t\t\t                 type:'combobox',\r\n");
      out.write("\t\t\t\t           valueField:'text',\r\n");
      out.write("\t\t\t\t            textField:'text',\r\n");
      out.write("\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t                 data:[{text:'是'},{text:'否'}]}},\r\n");
      out.write("\t\t\t{field:'recordmech', header:'登记机关',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t  newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\tmaxLength:120}},\r\n");
      out.write("\t\t\t{field:'purchaselife', header:'购买年限',\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t}},\r\n");
      out.write("\t\t\t{field:'cgenote', header:'备注',\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t                  type:'textarea',\r\n");
      out.write("\t\t\t \t               colspan:3,\r\n");
      out.write("\t\t                        height:70,\r\n");
      out.write("\t\t\t\t                 width:\"100%\",\r\n");
      out.write("\t\t\t\t             maxLength:300}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_contract_guarantee_equip\" style=\"width:100%;height:100%;\"></div>");
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
    // /WEB-INF/jsps/solutions/workflow/forms/contract/contract_comm/contract_guarantee_equip.jsp(6,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /WEB-INF/jsps/solutions/workflow/forms/contract/contract_comm/contract_guarantee_equip.jsp(18,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("json_contract_guarantee_equip_str");
    // /WEB-INF/jsps/solutions/workflow/forms/contract/contract_comm/contract_guarantee_equip.jsp(18,25) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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