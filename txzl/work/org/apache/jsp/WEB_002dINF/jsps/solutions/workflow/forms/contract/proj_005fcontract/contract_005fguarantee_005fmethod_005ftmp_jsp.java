/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:28:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.proj_005fcontract;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fguarantee_005fmethod_005ftmp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tvar conid = mini.getbyName(\"contract_info.contractid\").getValue();\r\n");
      out.write("\tvar projid = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.projid']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("\t//alert(projid);\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask == true){showTools = false;};\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"contract_guarantee_method\",\r\n");
      out.write("\t\trenderTo: \"id_table_guarantee_method\",\r\n");
      out.write("\t\twidth: globalClientWidth - 30,\r\n");
      out.write("\t\theight: 365,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : true,\r\n");
      out.write("\t\tentityClassName : \"com.tenwa.leasing.entity.proj.ProjGuaranteeMethodTmp\",\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['add', '-', 'edit', '-', 'remove'],\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tparams:{\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tprojid:projid,\r\n");
      out.write("\t\t\tdocid:flowUnid\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\t//data: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_contract_guarantee_method_str ? \"[]\" : json_contract_guarantee_method_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\txmlFileName : '/eleasing/workflow/proj/proj_contract/proj_guarantee_method_tmp.xml',\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t{field:'id', header:'id', visible:false},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t{field:'contractid',\r\n");
      out.write("\t\t\t\theader:'合同编号',\r\n");
      out.write("\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\tvalue:conid\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t{field:'projinfo',\r\n");
      out.write("\t\t\t\theader:'项目id',\r\n");
      out.write("\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\tvalue:projid\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t{field:'docid',\r\n");
      out.write("\t\t\t\theader:'流程id',\r\n");
      out.write("\t\t\t\tvisible: false,\r\n");
      out.write("\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\tvalue:flowUnid\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t{field:'assurorname', header:'担保单位', visible:true,\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t     fieldVisible:false},\r\n");
      out.write("\t\t\t\t\t     renderer : function(e){\r\n");
      out.write("\t\t\t\t\t    \t var assurorname = e.record.assurorname;\r\n");
      out.write("\t\t\t\t\t    \t var assurorId = e.record.assuror;\r\n");
      out.write("\t\t\t\t\t    \t var assurorcustclass=e.record.assurorcustclass;\r\n");
      out.write("\t\t\t\t\t    \t return \"<a style='text-decoration:underline;color:blue;' onclick='opencustdetail(\\\"\" + assurorId +\"\\\",\\\"\"+assurorcustclass+ \"\\\")'>\" + assurorname + \"</a>\";}\t     \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{field:'assuror', header:'担保单位', visible:false,\r\n");
      out.write("\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t          newLine:true,\r\n");
      out.write("\t\t\t\t\t            width:\"100%\",\r\n");
      out.write("\t\t\t\t\t             type:'queryinput',\r\n");
      out.write("\t\t\t\t\t         required:true,\r\n");
      out.write("\t\t\t\t\t        textField:'name',\r\n");
      out.write("\t\t\t\t\t       valueField:'value',\r\n");
      out.write("\t\t\t\t\t       allowInput:false,\r\n");
      out.write("\t\t\t\t\t     fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t            width:'100%',\r\n");
      out.write("\t\t\t\t\t          colspan:3,\r\n");
      out.write("\t\t\t\t\t           params:{cust_type:'cust_type.assuror',xmlFileName:'/eleasing/workflow/contract/contract_approval/contract_custinfo.xml'}}},\r\n");
      out.write("\t\t\t{field:'assurorcustclass', header:'担保单位客户类型',visible:false,\r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t    fillFromFieldName:'assuror',\r\n");
      out.write("\t\t\t\t         fillProperty:'custclass'}},\r\n");
      out.write("\t\t\t{field:'assuremethodname', header:'担保类型',\r\n");
      out.write("\t\t\t\t \t formEditorConfig:{\r\n");
      out.write("\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t    fillFromFieldName:'assuremethod',\r\n");
      out.write("\t\t\t\t         fillProperty:'name'}},\r\n");
      out.write("\t\t\t{field:'assuremethod', header:'担保类型', visible:false,\r\n");
      out.write("\t\t\t\t \t formEditorConfig:{\r\n");
      out.write("\t\t\t\t                width:\"100%\",\r\n");
      out.write("\t\t\t\t              newLine:true,\r\n");
      out.write("\t\t\t\t                 type:'combobox',\r\n");
      out.write("\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t            textField:'name',\r\n");
      out.write("\t\t\t\t           valueField:'value',\r\n");
      out.write("\t\t\t\t           allowInput:false,\r\n");
      out.write("\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t               params:{pid:'assure_method',xmlFileName:'combos/comboDict.xml'}}},\r\n");
      out.write("\t\t\t{field:'assurerelationname', header:'关系', \r\n");
      out.write("\t\t\t\t     formEditorConfig:{\r\n");
      out.write("\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t    fillFromFieldName:'assurerelation',\r\n");
      out.write("\t\t\t\t         fillProperty:'name'}},\r\n");
      out.write("\t\t\t{field:'assurerelation', header:'关系', visible:false,\r\n");
      out.write("\t\t\t\t \t formEditorConfig:{\r\n");
      out.write("\t\t\t\t              newLine:false,\r\n");
      out.write("\t\t\t\t                width:\"100%\",\r\n");
      out.write("\t\t\t\t                 type:'combobox',\r\n");
      out.write("\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t            textField:'name',\r\n");
      out.write("\t\t\t\t           valueField:'value',\r\n");
      out.write("\t\t\t\t           allowInput:false,\r\n");
      out.write("\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t            labelWidth:100,\r\n");
      out.write("\t\t\t\t                params:{pid:'relation',xmlFileName:'combos/comboDict.xml'}}},\r\n");
      out.write("\t\t\t{field:'cgmnote', header:'备注',formEditorConfig:{newLine:true,width:'100%',colspan:3,type:'textarea'}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_guarantee_method\" style=\"width:100%;height:100%;\"></div>");
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
