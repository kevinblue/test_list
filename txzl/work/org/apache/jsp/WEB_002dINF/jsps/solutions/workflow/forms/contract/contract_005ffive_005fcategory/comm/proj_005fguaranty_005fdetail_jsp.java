/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-20 08:08:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005ffive_005fcategory.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005fguaranty_005fdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"proj_guaranty_detail\",\r\n");
      out.write("\t\t\trenderTo: \"id_table_proj_guaranty_detail\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 10,\r\n");
      out.write("\t\t\theight: 365,\r\n");
      out.write("\t\t\tlazyLoad: true,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\ttitle: \"\",\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\ttools: ['add', '-', 'edit', '-', 'remove'],\r\n");
      out.write("\t\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_proj_guaranty_detail_str ? \"[]\" : json_proj_guaranty_detail_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field:'id',header:'id',visible:false},\r\n");
      out.write("\t\t\t\t{field:'equipname',header:'抵押物名称',\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t       newLine:true,\r\n");
      out.write("\t\t\t\t\t\t      required:true,\r\n");
      out.write("\t\t\t\t\t\t     maxLength:120,\r\n");
      out.write("\t\t\t\t\t\t       colspan:3,\r\n");
      out.write("\t\t\t\t\t\t    labelWidth:100,\r\n");
      out.write("\t\t\t\t\t\t         width:'100%'}},\r\n");
      out.write("\t\t\t\t{field:'guarantorname', header:'抵押人',\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t      fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t fillFromFieldName:'guarantor',\r\n");
      out.write("\t\t\t\t\t      fillProperty:'name'},\r\n");
      out.write("\t\t\t\t\t  \trenderer:function(e){\r\n");
      out.write("\t\t\t\t\t\t\tvar row=e.record;\r\n");
      out.write("\t\t\t\t\t\t\treturn \"<a style='text-decoration:underline;color:blue;' href='javascript:showcustinfo(\\\"\"+row.guarantor+\"\\\")'>\"+row.guarantorname+\"</a>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t}\t    \r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'guarantor',header:'抵押人',visible:false,\r\n");
      out.write("\t\t\t\t\t   formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t            newLine:true,\r\n");
      out.write("\t\t\t\t\t               type:'queryinput',\r\n");
      out.write("\t\t\t\t\t           required:true,\r\n");
      out.write("\t\t\t\t\t          textField:'name',\r\n");
      out.write("\t\t\t\t\t         valueField:'value',\r\n");
      out.write("\t\t\t\t\t         allowInput : false,\r\n");
      out.write("\t\t\t\t\t       fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t            colspan:3,\r\n");
      out.write("\t\t\t\t\t              width:'100%',\r\n");
      out.write("\t\t\t\t\t             params:{cust_type:'cust_type.guarantor',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},\r\n");
      out.write("\t\t\t\t{field:'equipinvoice',header:'发票号',\r\n");
      out.write("\t\t\t\t\t   formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t    newLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t  maxLength:120}},\r\n");
      out.write("\t\t\t\t{field:'guarantyvalue', header:'抵质押物价值',dataType :\"currency\",align:'right',\r\n");
      out.write("\t\t\t\t\t   formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t         labelWidth:100,\r\n");
      out.write("\t\t\t\t\t              vtype:'float',\r\n");
      out.write("\t\t\t\t\t          maxLength:20}},\t\r\n");
      out.write("\t\t\t\t{field:'equipguaranteetypename',header:'抵质押方式', \r\n");
      out.write("\t\t\t\t\t   formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t       fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t  fillFromFieldName:'equipguaranteetype',\r\n");
      out.write("\t\t\t\t\t       fillProperty:'name'}},\r\n");
      out.write("\t\t\t\t{field:'equipguaranteetype', header:'抵质押方式', visible:false,\r\n");
      out.write("\t\t\t\t\t   formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t            newLine:true,\r\n");
      out.write("\t\t\t\t\t               type:'combobox',\r\n");
      out.write("\t\t\t\t\t           required:true,\r\n");
      out.write("\t\t\t\t\t          textField:'name',\r\n");
      out.write("\t\t\t\t\t          valueField:'value',\r\n");
      out.write("\t\t\t\t\t        fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t             colspan:3,\r\n");
      out.write("\t\t\t\t\t               width:'100%',\r\n");
      out.write("\t\t\t\t\t              params:{dictid:'guarantee_type',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_dict_data.xml'}}},\r\n");
      out.write("\t\t\t\t{field:'presentvalue',header:'现值',dataType :\"currency\",align:'right',\r\n");
      out.write("\t\t\t\t\t  \tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t             newLine:true,\r\n");
      out.write("\t\t\t\t\t               vtype:'float',\r\n");
      out.write("\t\t\t\t\t           maxLength:20}},\r\n");
      out.write("\t\t\t\t{field:'notaryflagname',header:'是否抵质押公证',visible:false,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t        fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t   fillFromFieldName:'notaryflag',\r\n");
      out.write("\t\t\t\t\t        fillProperty:'name'}},\r\n");
      out.write("\t\t\t\t{field:'notaryflag',header:'是否抵质押公证',\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t                type:'combobox',\r\n");
      out.write("\t\t\t\t\t           textField:'name',\r\n");
      out.write("\t\t\t\t\t          valueField:'value',\r\n");
      out.write("\t\t\t\t\t        fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                data:[{name :'是',value:'是'},{name :'否',value:'否'}]}},\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t{field:'recordmech',header:'登记机关',\r\n");
      out.write("\t\t\t\t\t    formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t             newLine:true,\r\n");
      out.write("\t\t\t\t\t           maxLength:120}},\r\n");
      out.write("\t\t\t\t{field:'purchaselife',header:'购买年限',\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t              \r\n");
      out.write("\t\t\t\t               }},\r\n");
      out.write("\t\t\t\t{field:'cgenote',header:'备注',\r\n");
      out.write("\t\t\t\t   \t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t             newLine:true,\r\n");
      out.write("\t\t\t\t\t                type:'textarea',\r\n");
      out.write("\t\t\t\t\t             colspan:3,\r\n");
      out.write("\t\t\t\t\t               width:'100%',\r\n");
      out.write("\t\t\t\t\t              height:70,\r\n");
      out.write("\t\t\t\t\t           maxLength:120\r\n");
      out.write("\t\t\t\t\t}}\r\n");
      out.write("\t\t\t\t]\r\n");
      out.write("\t});});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_proj_guaranty_detail\"></div>\r\n");
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
