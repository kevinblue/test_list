/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-13 05:51:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fdevelop_005flist.proj_005fwind_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class supplier_005finformation_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/permission.tld", Long.valueOf(1486185689668L));
  }

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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tjQuery(function() {\r\n");
      out.write("\t\tvar showTools = true;\r\n");
      out.write("\t\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isShow}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\t\tseajs.use(\r\n");
      out.write("\t\t\t\t\t\t[ \"js/apcomponent/aptable/aptable.js\" ],\r\n");
      out.write("\t\t\t\t\t\tfunction(ApTable) {\r\n");
      out.write("\t\t\t\t\t\t\tnew ApTable(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tid : 'table_supplier_information',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trenderTo : \"id_table_supplier_information\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\twidth : globalClientWidth,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\theight : globalClientHeight,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ticonCls : 'icon-node',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thiddenQueryArea : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tmultiSelect : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonColSpan : 4,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonNewLine : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\teditFormPopupWindowWidth : 800,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\teditFormPopupWindowHeight : 450,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tremoteOper : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpageSize : 15,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowPager : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tlazyLoad : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tloadMode : 'ajax',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonColSpan : 6,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tqueryButtonNewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformation',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenStartColumn : 0,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenEndColumn : 2,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//新增弹出窗口调用该方法返回AJAX\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tafterShowWindowCallBack : function(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tminiTable, miniForm, operType) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tif (operType == 'add') {//如果是新增就获取流水号\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\turl : getRootPath()+ \"/acl/getRunningWater.acl\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : \"post\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tdata : {},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tsuccess : function(date) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tmini.getbyName(\"supplierid\").setValue(date);\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_information.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparams:{projid:projid},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttools : [ 'add', '-', 'edit', '-','remove', '-', 'exportExcel' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcolumns : [\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'indexcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'checkcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '标识符',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t   {field : 'projid',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\theader : '项目id号',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvalue : projid\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'supplierid',header:'供应商编号',formEditorConfig : {newLine:true,width : 200,labelWidth:100,readOnly:true}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'suppliername',header:'供应商名称',formEditorConfig : {width : 200,labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'icregistcode',header:'企业工商注册编码',formEditorConfig : {width : 200,labelWidth:120,newLine:true}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t//{field:'supplierId',header:'供应商编号',formEditorConfig:{width : 200,labelWidth:100}}, \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'supplierbcategoriesname',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '供应商大类',\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tlabelWidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillFromFieldName : 'supplierbcategories',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillProperty : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'supplierbcategories',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '供应商大类',\r\n");
      out.write("\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tnewLine : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttextField : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalueField : 'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpid1 : 'A',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpid2 : 'B',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_big.xml'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcascade : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tchildrenFieldNames : [ 'supplierscategories' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    clearFieldNames : [ 'supplierscategories','nameofgoodsorservices' ] \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'supplierscategoriesname',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '供应商小类',\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillFromFieldName : 'supplierscategories',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillProperty : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'supplierscategories',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '供应商小类',\r\n");
      out.write("\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tnewLine :true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tshowNullItem : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tnullItemText : \"\",    //显示空值\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttextField : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalueField : 'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\totherAttributes:'onClick=\"AreYouOk\"',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_small.xml'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcascade : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparentFieldNames : [ 'supplierbcategories' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tchildrenFieldNames : [ 'nameofgoodsorservices' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    clearFieldNames : [ 'nameofgoodsorservices' ] \r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'nameofgoodsorservicesname',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '货物或劳务名称',\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillFromFieldName : 'nameofgoodsorservices',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfillProperty : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\tfield : 'nameofgoodsorservices',\r\n");
      out.write("\t\t\t\t\t\t\t\theader : '货物或劳务名称',\r\n");
      out.write("\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tnewLine :false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tshowNullItem : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tnullItemText : \"\",    //显示空值\r\n");
      out.write("\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.SupplierInformationType',\r\n");
      out.write("\t\t\t\t\t\t\t\t\ttextField : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvalueField : 'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\totherAttributes:'onClick=\"AreYouOk1\"',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/supplier_type.xml'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\tcascade : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparentFieldNames : [ 'supplierscategories' ]\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  /*  clearFieldNames : [ 'nameofgoodsorservices' ]  */\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'isqualifiedsupplier',header:'是否合格供应商',formEditorConfig:{newLine:true,width : 200,labelWidth:100,type : 'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttextField : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvalueField : 'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowInput : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tshowNullItem : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tdefaultValue : '是',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tdata : [ {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tname : \"是\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalue : \"是\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tname : \"否\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalue : \"否\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t} ]}}, \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'relevantqualification',header:'相关资质',formEditorConfig : {newLine:false,width : 200,labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'projectexperience',header:'项目经验',formEditorConfig : {colspan : 3,\twidth : \"100%\",newLine:true}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'contacts',header:'联系人',formEditorConfig :{newLine:true,width : 200,labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'contactway',header:'联系方式',formEditorConfig : {newLine:false,width : 200,labelWidth:100}},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{field:'remarks',header:'备注',formEditorConfig : {type : 'textarea',colspan : 3,width : \"100%\",newLine:true}}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t]\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction AreYouOk(e){\t\t\r\n");
      out.write("\tvar big=mini.getbyName(\"supplierbcategories\").getValue();\r\n");
      out.write("\t   if(big==\"\"){\r\n");
      out.write("\t\t   setTimeout(function(){mini.alert(\"请先选择供应商大类！\")},100);\r\n");
      out.write("\t   }\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction AreYouOk1(e){\t\r\n");
      out.write("\tvar small=mini.getbyName(\"supplierscategories\").getValue();\r\n");
      out.write("\tvar big=mini.getbyName(\"supplierbcategories\").getValue();\r\n");
      out.write("\t   if(small==\"\" || big==\"\"){\r\n");
      out.write("\t\t   setTimeout(function(){mini.alert(\"请先选择供应商小类！\")},100);\r\n");
      out.write("\t\t   \r\n");
      out.write("\t   }\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_supplier_information\"></div>\r\n");
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