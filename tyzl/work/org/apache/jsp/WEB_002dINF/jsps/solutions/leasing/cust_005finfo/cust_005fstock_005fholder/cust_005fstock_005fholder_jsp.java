/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-02 09:58:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.leasing.cust_005finfo.cust_005fstock_005fholder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cust_005fstock_005fholder_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function() {\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid:'table_id5',\r\n");
      out.write("\t\t\twidth:globalClientWidth-20,\r\n");
      out.write("\t\t\theight:420,\r\n");
      out.write("\t\t\ticonCls:'icon-node',\r\n");
      out.write("\t\t\tfrozenStartColumn:0,\r\n");
      out.write("\t\t\tfrozenEndColumn:1,\r\n");
      out.write("\t\t\trenderTo:'content_table_id5',\r\n");
      out.write("\t\t\thiddenQueryArea:true,\r\n");
      out.write("\t\t\tfrozenStartColumn:1,\r\n");
      out.write("\t\t\tfrozenEndColumn:3,\r\n");
      out.write("\t\t\teditFormPopupWindowWidth:700,\r\n");
      out.write("\t\t\tremoteOper:true,\r\n");
      out.write("\t\t\tvalidateForm:function(miniTable, miniForm, editFormItemOperFlag){\r\n");
      out.write("\t\t\t\tif(checkCardNoCustStockHolder()==\"repeat\") return;\r\n");
      out.write("\t\t\t\treturn true;},\r\n");
      out.write("\t\t\tentityClassName:'com.tenwa.leasing.entity.cust.CustStockHolder',\r\n");
      out.write("\t\t\tpageSize:15,\r\n");
      out.write("\t\t\tshowPager:true,\r\n");
      out.write("\t\t\tlazyLoad:false,\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\txmlFileName:'/eleasing/jsp/cust_info/cust_stockholder/cust_stockholder.xml',\r\n");
      out.write("\t\t\tparams:{custid:custid},\r\n");
      out.write("\t\t\ttools:[ 'add', '-', 'edit', '-', 'remove'],\r\n");
      out.write("\t\t\tcolumns:[ \r\n");
      out.write("\t\t\t\t    {type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t    {type:'checkcolumn'}, \r\n");
      out.write("\t\t\t\t    {field:'id',header:'记录编号',headerAlign:'center',width:100,allowSort:true,visible:false,\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               readOnly:true,\r\n");
      out.write("\t\t\t\t\t           fieldVisible:false }}, \r\n");
      out.write("\t\t\t\t\t{field:'custid',header:'客户ID',headerAlign:'center',width:100,allowSort:true,visible:false,\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               readOnly:true,\r\n");
      out.write("\t\t\t\t\t           fieldVisible:false, \r\n");
      out.write("\t\t\t\t\t                  value:custid}},\r\n");
      out.write("\t\t\t\t\t{field:'stockholdername',header:'股东名称',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               required:true,\r\n");
      out.write("\t\t\t\t\t                   type:'text',\r\n");
      out.write("\t\t\t\t\t           fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                  width:200,\r\n");
      out.write("\t\t\t\t\t             labelWidth:100}},\r\n");
      out.write("\t\t\t\t    {field: 'cust_info_typename', header: '法人/个人', \r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t           fieldVisible: false,\r\n");
      out.write("\t\t\t\t\t      fillFromFieldName:'stockholdertype',\r\n");
      out.write("\t\t\t\t\t           fillProperty:'name'}},\r\n");
      out.write("\t\t\t\t\t{field:'stockholdertype',header:'法人/个人',visible:false,headerAlign:'center',width:100,allowSort:true,\t\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t             labelWidth:100,\r\n");
      out.write("\t\t\t\t\t                  width:200,\r\n");
      out.write("\t\t\t\t\t           fieldVisible: true,\r\n");
      out.write("\t\t\t\t\t           showNullItem:\"true\", \r\n");
      out.write("\t\t\t\t\t           nullItemText:\"\",\r\n");
      out.write("\t\t\t\t\t              emptyText:\"\",\r\n");
      out.write("\t\t\t\t\t                   type:'combobox',\r\n");
      out.write("\t\t\t\t\t                 params:{pid: 'cust_info_type',xmlFileName: '/combos/comboDict.xml'},\r\n");
      out.write("\t\t\t\t\t              textField:'name',\r\n");
      out.write("\t\t\t\t\t             valueField:'value'}},\r\n");
      out.write("\t\t\t\t\t{field:'orgcode',header:'身份证/组织机构代码',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t                newLine:true,\r\n");
      out.write("\t\t\t\t\t                   type:'text',\r\n");
      out.write("\t\t\t\t\t           fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                  width:200,\r\n");
      out.write("\t\t\t\t\t             labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t{field:'regcapital',header:'注册资本',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t           formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t                   type:'text',\r\n");
      out.write("\t\t\t\t\t           fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                  width:200,\r\n");
      out.write("\t\t\t\t\t             labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t{field:'capitaltype',header:'出资方式',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t                  type:'text',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                 width:200,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100}},\r\n");
      out.write("\t\t\t\t    {field:'capitalmoney',header:'出资金额',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t                  type:'text',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                 width:200,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t{field:'shareholding',header:'持股比例(%)',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t                  type:'text',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                 vtype:\"float\",\r\n");
      out.write("\t\t\t\t\t                 width:200,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t {field:'legalrepresentative',header:'法人代表',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t                  type:'text',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t                 width:200,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t {field:'addr',header:'地址',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t                  type:'text',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t               colspan: 3,\r\n");
      out.write("\t\t\t\t\t                 width:\"100%\",\r\n");
      out.write("\t\t\t\t\t            labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t  {field:'bizscopeprimary',header:'主营业务',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t               colspan: 3,\r\n");
      out.write("\t\t\t\t\t                  type:'textarea',\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100,\r\n");
      out.write("\t\t\t\t\t                 width:\"100%\"}},\r\n");
      out.write("\t\t\t\t\t  {field:'cshmemo',header:'备注',headerAlign:'center',width:100,allowSort:true,\r\n");
      out.write("\t\t\t\t          formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t\t               colspan: 3,\r\n");
      out.write("\t\t\t\t\t                  type:'textarea',\r\n");
      out.write("\t\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t            labelWidth:100,\r\n");
      out.write("\t\t\t\t\t                 width:\"100%\"\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function checkCardNoCustStockHolder(){\r\n");
      out.write("\tvar cardNo = getMiniEditFormField(\"table_id5.orgcode\").getValue();//mini.getbyName(\"mainpersonflag\").getValue();\r\n");
      out.write("\tif(\"\" == cardNo)return \"\";\r\n");
      out.write("\tvar idvalue = getMiniEditFormField(\"table_id5.id\").getValue();\r\n");
      out.write("\tvar str = \"\";\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("        url: getRootPath()+\"/acl/checkCardNoCustStockHolder.acl\",\r\n");
      out.write("        type: \"post\",\r\n");
      out.write("        cache: false, \r\n");
      out.write("        data :{\"id\":idvalue,\"custId\":custid,\"cardNo\":cardNo},\r\n");
      out.write("        async:false,\r\n");
      out.write("        success: function (text) {\r\n");
      out.write("        \tvar obj=mini.decode(text);\r\n");
      out.write("        \tif(obj.message.length>1){\r\n");
      out.write("        \t\tmini.alert(text+\"身份证/组织机构代码不能重复！\");\r\n");
      out.write("        \t\tstr=\"repeat\"\r\n");
      out.write("        \t}else{\r\n");
      out.write("        \t\tstr = \"\";\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\treturn str;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id='content_table_id5'></div>");
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
    // /WEB-INF/jsps/solutions/leasing/cust_info/cust_stock_holder/cust_stock_holder.jsp(6,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("isView");
    int _jspx_eval_mini_005fparam_005f0 = _jspx_th_mini_005fparam_005f0.doStartTag();
    if (_jspx_th_mini_005fparam_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f0);
    return false;
  }
}