/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-13 05:51:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fdevelop_005flist.proj_005fwind_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005fprogress_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t/* var tools = [];\r\n");
      out.write("\t<permission:action code=\"cust_saleInfo_add\">\r\n");
      out.write("\ttools.push('add');\r\n");
      out.write("\t</permission:action>\r\n");
      out.write("\t<permission:action code=\"cust_saleInfo_edit\">\r\n");
      out.write("\ttools.length > 0 ? tools.push('-', 'edit') : tools.push('edit');\r\n");
      out.write("\t</permission:action>\r\n");
      out.write("\t<permission:action code=\"cust_saleInfo_remove\">\r\n");
      out.write("\ttools.length > 0 ? tools.push('-', \"remove\") : tool.push('remove');\r\n");
      out.write("\t</permission:action> */\r\n");
      out.write("\r\n");
      out.write("\tfunction importMiniTableCallBack(message, tableid) {\t\r\n");
      out.write("\t\tvar info = eval(\"(\" + message + \")\");\r\n");
      out.write("\t\tmini.alert(info.message);\r\n");
      out.write("\t\tmini.get(\"id_minitableimport\").hide();\r\n");
      out.write("\t\tmini.unmask(document.body);\r\n");
      out.write("\t\tmini.get(tableid).reload();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar tabledate = info.tabledate;\r\n");
      out.write("\t\t//console.info(info);\r\n");
      out.write("\t\tif (\"\" != tabledate) {\r\n");
      out.write("\t\t\tvar grid = mini.get(tableid);\r\n");
      out.write("\t\t\tgrid.set({\r\n");
      out.write("\t\t\t\tdata : mini.decode(tabledate)\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tjQuery(function() {\r\n");
      out.write("\t\tvar showTools = true;\r\n");
      out.write("\t\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\t\tseajs.use(\r\n");
      out.write("\t\t\t\t\t\t[ \"js/apcomponent/aptable/aptable.js\" ],\r\n");
      out.write("\t\t\t\t\t\tfunction(ApTable) {\r\n");
      out.write("\t\t\t\t\t\t\tnew ApTable(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tid : 'table_id8',\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\twidth : globalClientWidth - 20,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\theight : 420,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ticonCls : 'icon-node',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenStartColumn : 0,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenEndColumn : 1,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\timportTargetClass:'com.tenwa.leasing.entity.proj.ProjProgress',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\timportDataIndex : '2',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\timportHeaderIndex : '1',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\texportTitle:'项目建设进度',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\timportprojDevelop :projid,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\timportOrExPortCallBack:'SetprojDevelopProgress',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\trenderTo : 'content_table_id8',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\thiddenQueryArea : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenStartColumn : 1,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfrozenEndColumn : 3,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\teditFormPopupWindowWidth : 700,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t//showToolbar : showTools,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tremoteOper : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tentityClassName : 'com.tenwa.leasing.entity.proj.ProjProgress',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tpageSize : 15,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowPager : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tlazyLoad : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tloadMode : 'ajax',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\txmlFileName : '/eleasing/workflow/proj/proj_search/proj_progress.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\tprojid : projid\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttools : ['add','-','edit','-','remove','-','exportExcel','-','importExcel' ],\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcolumns : [\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'indexcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'checkcolumn'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'id',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '记录编号',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'projid',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '项目ID',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalue : projid\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t \t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'task',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '任务名称',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'text',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tlabelWidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfillFromFieldName:'taskname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t          fillProperty:'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t       entityClassName:'com.tenwa.business.entity.DictionaryData',\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t entityHeaderFieldName:'name'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}, \r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'taskname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '任务名称',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttextField : 'name',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tvalueField : 'value',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tpid : 'taskname',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\txmlFileName : '/combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'begintime',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '开始时间',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tdateFormat : \"yyyy-MM-dd \",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tdefaultValue : mini\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.formatDate(\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tnew Date(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\"yyyy-MM-dd\"),\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tlabelWidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\totherAttributes:'onvaluechanged=\"judgedate\"'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'endtime',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '结束时间',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tdateFormat : \"yyyy-MM-dd \",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'date',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tlabelWidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\totherAttributes:'onvaluechanged=\"durationtime\"'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'durationtime',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '持续时间（天）',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'text',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tlabelWidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'schedule',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '完工进度',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tcolspan : 3,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : \"100%\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfield : 'completedprojectcase',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theader : '已完工项目情况说明',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tallowSort : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttype : 'textarea',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\treadOnly : false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tcolspan : 3,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\twidth : \"100%\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t]\r\n");
      out.write("\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\tfunction judgedate(e){ \t\t\r\n");
      out.write("\t\tvar start=mini.getbyName(\"begintime\").getValue();\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tmini.getbyName(\"endtime\").setMinDate(start);  \t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction durationtime(e){ \t\t\r\n");
      out.write("\t\tvar start=mini.getbyName(\"begintime\").getValue();\r\n");
      out.write("\t\tvar end=mini.getbyName(\"endtime\").getValue();\t\t\r\n");
      out.write("\t\tvar time = end.getTime() - start.getTime() ; //日期的long型值之差\t\t\t\t\r\n");
      out.write("\t\tvar days=Math.floor(time/(24*60*60*1000)+1); \t\t\t\r\n");
      out.write("\t\tmini.getbyName(\"durationtime\").setValue(days);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<div id='content_table_id8'></div>");
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
