/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-09 06:05:05 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.law_005fmng.pledge;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class not_005fcancled_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/* 字段备注  ：   计划金额   = 本次收款金额 + 调整金额            */\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"not_cancled\",\r\n");
      out.write("\t\trenderTo: \"id_table_not_cancled\",\r\n");
      out.write("\t\twidth : globalClientWidth-30,\r\n");
      out.write("\t\theight : 400,\r\n");
      out.write("\t\teditFormPopupWindowWidth : 400,\r\n");
      out.write("\t\tlazyLoad:false,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tmultiSelect : true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: toolsArray,\r\n");
      out.write("\t\tdata:JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_not_cancled_str ? \"[]\" : json_not_cancled_str}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t//debugger;\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type : 'indexcolumn',header : '序号'},\r\n");
      out.write("\t\t\t{type : 'checkcolumn'},\r\n");
      out.write("            {field : 'id',header : '编号',headerAlign : 'center',width : 100,allowSort : true,visible : false,\r\n");
      out.write("\t\t\t\t  formEditorConfig : {\r\n");
      out.write("\t\t\t\t\t  readOnly : true,\r\n");
      out.write("\t\t\t\t\t  fieldVisible : false\r\n");
      out.write("\t\t\t\t }\r\n");
      out.write("\t\t\t},\t\t\r\n");
      out.write("\t\t\t{field : 'registypeid',header : '登记类型',visible : false\t},\t\t\t\t\t\t\r\n");
      out.write("\t\t\t{field : 'registypeidname',header : '登记类型',visible : true\t},\r\n");
      out.write("\t\t\t{field : 'contractid',header : '抵质押登记合同编号',visible : true,width : 150},\r\n");
      out.write("\t\t\t{field : 'regisnum',header : '登记证明编号',headerAlign : 'center',visible : true,width : 150,\r\n");
      out.write("\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tfieldVisible : true\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{field : 'regisunit',header : '登记单位',headerAlign : 'center',visible : true,width : 150},\r\n");
      out.write("\t\t\t{field : 'assuror',header : '担保单位',visible : false},\r\n");
      out.write("\t\t\t{field : 'assurorname',header : '担保单位',visible : true},\r\n");
      out.write("\t\t\t{field : 'pledgeownner',header : '抵质押物所在企业/自然人',headerAlign : 'center',width : 200,visible : true},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfield : 'pledgevalue',\r\n");
      out.write("\t\t\t\theader : '抵质押物数额/原值',\r\n");
      out.write("\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\twidth : 150\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'pledgevaluenow',\r\n");
      out.write("\t\t\t\t\theader : '抵质押物现值',\r\n");
      out.write("\t\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'assurortotalval',\r\n");
      out.write("\t\t\t\t\theader : '被担保债权总额/主合同金额',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 200\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t {\r\n");
      out.write("\t\t\t\t\tfield : 'descriptionone',\r\n");
      out.write("\t\t\t\t\theader : '抵质押物描述',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150\r\n");
      out.write("\t\t\t\t}, \r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'debtstart',\r\n");
      out.write("\t\t\t\t\theader : '债务履行期起始日',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\t\tdateFormat : \"yyyy-MM-dd\"\r\n");
      out.write("\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\tfield : 'debtend',\r\n");
      out.write("\t\t\t\t\theader : '债务履行期终止日',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\t\tdateFormat : \"yyyy-MM-dd\"\r\n");
      out.write("\t\t\t\t}, \r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'pledgestart',\r\n");
      out.write("\t\t\t\t\theader : '抵质押登记起始日',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\t\tdateFormat : \"yyyy-MM-dd\"\r\n");
      out.write("\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\tfield : 'pledgeend',\r\n");
      out.write("\t\t\t\t\theader : '抵质押登记到期日',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 150,\r\n");
      out.write("\t\t\t\t\tdateFormat : \"yyyy-MM-dd\"\r\n");
      out.write("\t\t\t\t}, \r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfield : 'registime',\r\n");
      out.write("\t\t\t\t\theader : '登记时间',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 100,\r\n");
      out.write("\t\t\t\t\tdateFormat : \"yyyy-MM-dd\"\r\n");
      out.write("\t\t\t\t}, {\r\n");
      out.write("\t\t\t\t\tfield : 'preparer',\r\n");
      out.write("\t\t\t\t\theader : '填表人',\r\n");
      out.write("\t\t\t\t\theaderAlign : 'center',\r\n");
      out.write("\t\t\t\t\tvisible : true,\r\n");
      out.write("\t\t\t\t\twidth : 100\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("var toolsArray=\r\n");
      out.write("\t[\r\n");
      out.write("\t{\r\n");
      out.write("\t\thtml : '注销',\r\n");
      out.write("\t\tplain : true,\r\n");
      out.write("\t\ticonCls : 'icon-ok',\r\n");
      out.write("\t\thandler : function(miniTable, buttonText) \r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\t   // var row = miniTable.getSelected();\r\n");
      out.write("\t\t\t    var rows = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\t\tif (rows.length == 0){\r\n");
      out.write("\t\t\t\t\t\t\tmini.alert(\"请勾选数据再操作!\");\r\n");
      out.write("\t\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t var curGrid = mini.get(\"present_cancled\");//获取本次注销对象\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t var curGrids=curGrid.getData();\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t//\t curGrid.addRows(rows,0 );//添加要拷贝的对象\r\n");
      out.write("\t\t   // curGrid.saveDataToInput();//保存操作！ \r\n");
      out.write("\t\t\t\t\tvar gobalFlag=false;\r\n");
      out.write("\t\t\t\t\tfor(var i = 0 ; i< rows.length;i++){\r\n");
      out.write("\t\t\t\t\t\tvar row = rows[i];\r\n");
      out.write("\t\t\t\t\t\tvar flag=false;\r\n");
      out.write("\t\t\t\t\t\tfor(var j = 0 ; j < curGrids.length;j++){\t\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif(row.id == curGrids[j].id){\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tgobalFlag=true;\t\t\t\t\t\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t} \r\n");
      out.write("\t\t\t\t\tif(gobalFlag){\r\n");
      out.write("\t\t\t\t\t\tmini.alert(\"您选中行的数据已在本次注销明细中了,请不要重复注销！\");\r\n");
      out.write("\t\t\t\t\t\tminiTable.deselectAll();return false;\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tgetPledge(miniTable)\r\n");
      out.write("\t\t\t\t\t\tmini.alert(\"操作成功,请到本次付款明细查看!\");\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}];\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tfunction  getPledge(miniTable)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar newRow = {};\r\n");
      out.write("\t\tvar planGrid = miniTable.getSelecteds();//获取注销选中grid\r\n");
      out.write("\t\tvar currentGrid = mini.get(\"present_cancled\");//获取本次注销grid\r\n");
      out.write("\t\tvar rowDatas = [];\r\n");
      out.write("\t\tfor (var i = 0 ;i <planGrid.length; i++ )\r\n");
      out.write("\t\t{   \r\n");
      out.write("\t\t\tnewRow.id=planGrid[i].id;\r\n");
      out.write("\t\t\tnewRow.registypeidname=planGrid[i].registypeidname;\r\n");
      out.write("\t\t\tnewRow.contractid=planGrid[i].contractid;\t\t\t\r\n");
      out.write("\t\t\tnewRow.pledgevaluenow=planGrid[i].pledgevaluenow;\t\t\t\r\n");
      out.write("\t\t\tnewRow.regisnum=planGrid[i].regisnum;\r\n");
      out.write("\t\t\tnewRow.regisunit=planGrid[i].regisunit;\r\n");
      out.write("\t\t\tnewRow.assurorname=planGrid[i].assurorname;\r\n");
      out.write("\t\t\tnewRow.pledgeownner=planGrid[i].pledgeownner;\r\n");
      out.write("\t\t\tnewRow.pledgevalue=planGrid[i].pledgevalue;\r\n");
      out.write("\t\t\tnewRow.assurortotalval=planGrid[i].assurortotalval;\r\n");
      out.write("\t\t\tnewRow.descriptionone= planGrid[i].descriptionone;\r\n");
      out.write("\t\t\tnewRow.debtstart= planGrid[i].debtstart;\r\n");
      out.write("\t\t\tnewRow.debtend=planGrid[i].debtend;\r\n");
      out.write("\t\t\tnewRow.pledgestart=planGrid[i].pledgestart;\r\n");
      out.write("\t\t\tnewRow.pledgeend=planGrid[i].pledgeend;\r\n");
      out.write("\t\t\tnewRow.registime=planGrid[i].registime;\r\n");
      out.write("\t\t\tnewRow.preparer=planGrid[i].preparer;\r\n");
      out.write("\t\t\trowDatas.push(mini.clone(newRow));\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcurrentGrid.addRows(rowDatas, 0);\r\n");
      out.write("\t\tcurrentGrid.saveDataToInput();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tminiTable.deselectAll(false);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_table_not_cancled\" style=\"width:100%;height:100%;\"></div>\r\n");
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
