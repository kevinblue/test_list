/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-02-22 10:14:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fplancharg;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fplancharg_005fpay_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"plancharg_pay\",\r\n");
      out.write("\t\trenderTo: \"id_table_plancharg_pay\",\r\n");
      out.write("\t\twidth : '100%',\r\n");
      out.write("\t\theight :'100%',\r\n");
      out.write("\t\teditFormPopupWindowWidth : 400,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['add','-','edit','-','remove'],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("'),\r\n");
      out.write("\t\toperValidate:function(miniTable, rowDatas, operFlag){\r\n");
      out.write("            if(operFlag==\"edit\" || operFlag==\"remove\"){\r\n");
      out.write("                var  factmoney=0.00;\r\n");
      out.write("                if(operFlag==\"edit\"){factmoney=rowDatas.factmoney;}else{factmoney=rowDatas[0].factmoney;}\r\n");
      out.write("                if(parseFloat(factmoney)>0){\r\n");
      out.write("                      mini.alert(\"已经付款过的资金计划不能修改或删除\");\r\n");
      out.write("                      return false;\r\n");
      out.write("                    }\r\n");
      out.write("             }\r\n");
      out.write("\t\t\t   return true;\r\n");
      out.write("\t   },\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'paytype', header: '收付方向', visible: false, formEditorConfig:{defaultValue:'pay_type_out',fieldVisible: false}},\r\n");
      out.write("\t\t\t{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\tfillFromFieldName : 'feetype',\r\n");
      out.write("\t\t\t\tfillProperty : 'name'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'value',\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t    pid: 'FeeType',\r\n");
      out.write("\t\t\t\t\txmlFileName: 'combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'paymentid', header: '付款编号',formEditorConfig:{labelWidth:100,required: true}},\r\n");
      out.write("\t\t\t{field: 'payobjname', header: '支付对象',formEditorConfig:{fieldVisible: false}},\r\n");
      out.write("\t\t\t{field: 'payobj', header: '支付对象',visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\tnewLine: true,\r\n");
      out.write("\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'name',\r\n");
      out.write("\t\t\t\tallowInput: false,\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\tcolspan:3,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\txmlFileName: 'common/custInfo.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'planmoney', header: '计划付款金额',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,required: true,onkeyup: 'setFundPayOver(e)'}},\r\n");
      out.write("\t\t\t{field: 'factmoney', header: '实付款金额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'overmoney', header: '计划付款余额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'plandate', header: '付款日期',formEditorConfig:{\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttype:'date'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'planmoneystatus', header: '付款状态',formEditorConfig:{fieldVisible: false},renderer : function(e){\r\n");
      out.write("\t\t\t\t  return setfundPayState(e.record);\r\n");
      out.write("\t     \t}},\r\n");
      out.write("\t\t\t{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\theight:70}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t \r\n");
      out.write("});\r\n");
      out.write("function setFundPayOver(e){\r\n");
      out.write("\t   var planmoney= e.sender.getInputText();\r\n");
      out.write("\t   var overmoney=getMiniEditFormField(\"plancharg_pay.overmoney\");\r\n");
      out.write("\t   overmoney.setValue(planmoney);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_plancharg_pay\" style=\"width:100%;height:400px\"></div>\r\n");
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg_pay_info.jsp(7,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg_pay_info.jsp(22,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("json_plancharg_pay_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_plancharg/fund_plancharg_pay_info.jsp(22,25) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
