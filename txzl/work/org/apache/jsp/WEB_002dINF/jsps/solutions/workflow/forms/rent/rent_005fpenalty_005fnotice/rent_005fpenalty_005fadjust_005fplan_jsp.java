/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-23 16:04:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.rent_005fpenalty_005fnotice;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rent_005fpenalty_005fadjust_005fplan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tif(isViewHistoryTask){showTools = false;}\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"rent_income_plan\",\r\n");
      out.write("\t\trenderTo: \"id_table_rent_income_plan\",\r\n");
      out.write("\t\twidth : globalClientWidth-30,\r\n");
      out.write("\t\theight : 400,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: [{\r\n");
      out.write("\t\t\thtml: '选择期数',\r\n");
      out.write("\t\t\tplain: true,\r\n");
      out.write("\t\t\ticonCls: 'icon-ok',\r\n");
      out.write("\t\t\thandler: function(miniTable, buttonText){\r\n");
      out.write("\t\t\t\t//var currTable = mini.get(\"rent_penalty_adjust_detail\");\r\n");
      out.write("\t\t\t\t//var currRowDatas = mini.clone(currTable.getData());\r\n");
      out.write("\t\t\t\tselectedDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\tif(selectedDatas.length == 0){\r\n");
      out.write("\t\t\t\t\tmini.alert(\"请先选择要生成罚息通知书的租金计划！\");\r\n");
      out.write("\t\t\t\t\treturn false;\t\t\t\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tconsole.info(selectedDatas);\r\n");
      out.write("\t\t\t\t\treturn selectedDatas;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t  }}],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_rent_income_plan_str ? \"[]\" : json_rent_income_plan_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'checkcolumn',visible: showTools},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'rentlist', header: '期次'},\r\n");
      out.write("\t\t\t{field: 'status', header: '回笼状态', renderer: showRentStatus},\r\n");
      out.write("\t\t\t{field: 'plandate', header: '计划日期'},\r\n");
      out.write("\t\t\t{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curpenaltyincome', header: '罚息实收',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curpenaltyadjustincome', header: '罚息已减免',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : \"currency\"},\t\r\n");
      out.write("\t\t\t{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : \"currency\"}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function showRentStatus(e){\r\n");
      out.write("    var curRowData=e.record;\r\n");
      out.write("\tif(curRowData.currentoverage == 0){//&& curRowData.penaltyoverage==0\r\n");
      out.write("\t\treturn \"已回笼\";\r\n");
      out.write("\t}else if(curRowData.currentoverage == curRowData.rent){\r\n");
      out.write("\t\treturn \"未回笼\";\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\treturn \"部分回笼\";\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("/* function generateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,ebankmoney){\r\n");
      out.write("\tvar rowObj = {};\r\n");
      out.write("\tvar message = \"\";//已添加的期次\r\n");
      out.write("\tvar cmessage=\"\";//已核销的期次\r\n");
      out.write("\tvar dmessage=\"\";//没有网银核销的期次\r\n");
      out.write("\tvar rent = 0.00;\r\n");
      out.write("\tvar corpus = 0.00;\r\n");
      out.write("\tvar interest = 0.00;\r\n");
      out.write("\tvar penalty = 0.00;\r\n");
      out.write("\tallMoney=ebankmoney;\r\n");
      out.write("\tif(parseFloat(ebankmoney)>0){\r\n");
      out.write("\t\tfor(var i = 0; i < selectedDatas.length; i ++){\r\n");
      out.write("\t\t\tif(parseFloat(allMoney)<=0){\r\n");
      out.write("\t\t\t\tdmessage+= '【' + selectedDatas[i].rentlist + '】';\r\n");
      out.write("\t\t\t\tcontinue;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(isExisted(currTable,selectedDatas[i].rentlist)){\r\n");
      out.write("\t\t\t\tmessage += '【' + selectedDatas[i].rentlist + '】';\r\n");
      out.write("\t\t\t\tcontinue;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(!checkRentMayIncome(selectedDatas[i])){\r\n");
      out.write("\t\t\t\tcmessage+= '【' + selectedDatas[i].rentlist + '】';\r\n");
      out.write("\t\t\t\tcontinue;\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t\trowObj.planid=selectedDatas[i].id;\r\n");
      out.write("\t\t\trowObj.planlist = selectedDatas[i].rentlist;\r\n");
      out.write("\t\t\trowObj.hirelist = getHireList(selectedDatas[i].rentlist);\r\n");
      out.write("\t\t\trowObj.balancemode = \"payfund6\";\r\n");
      out.write("\t\t\trowObj.balancemodename = \"电汇\";\r\n");
      out.write("\t\t\trowObj.hiredate = (new Date()).format('yyyy-MM-dd');\r\n");
      out.write("\t\t\trowObj.accountingdate = (new Date()).format('yyyy-MM-dd');\r\n");
      out.write("\t\t\trowObj.rent =0;\r\n");
      out.write("\t\t\trowObj.corpus = 0;\r\n");
      out.write("\t\t\trowObj.interest = 0;\r\n");
      out.write("\t\t\trowObj.penalty=0;\r\n");
      out.write("\t\t\trowObj.planpenalty = selectedDatas[i].penalty;\r\n");
      out.write("\t\t\trowObj.curpenaltyincome = selectedDatas[i].curpenaltyincome;\r\n");
      out.write("\t\t\trowObj.curpenaltyadjustincome = selectedDatas[i].curpenaltyadjustincome;\r\n");
      out.write("\t\t\trowObj.curcorpusoverage = 0;\r\n");
      out.write("\t\t\trowObj.curinterestoverage = 0;\r\n");
      out.write("\t\t\trowObj.penaltyoverage = selectedDatas[i].penaltyoverage;\t\t\t\t\r\n");
      out.write("\t\t\trowObj.rentadjust = 0;\r\n");
      out.write("\t\t\trowObj.corpusadjust = 0;\r\n");
      out.write("\t\t\trowObj.interestadjust = 0;\r\n");
      out.write("\t\t\trowObj.penaltyadjust =  selectedDatas[i].penaltyoverage;\r\n");
      out.write("\t\t\tcurrRowDatas.push(mini.clone(rowObj));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tcurrRowDatas.sort(function(a,b){return a.planlist - b.planlist;});\r\n");
      out.write("\t    currTable.setData(currRowDatas);\r\n");
      out.write("\t   \r\n");
      out.write("\t\tif(message != \"\"||cmessage!=\"\"||dmessage){\r\n");
      out.write("\t\t\tvar tempMessage=\"\";\r\n");
      out.write("\t\t\tif(message != \"\"){tempMessage+=\"操作成功！其中计划期项为<br/>\" + message + \"<br/>的数据已添加到实收计划中！\"};\r\n");
      out.write("\t\t\tif(cmessage != \"\"){tempMessage+=\"计划期项为<br/>\" + cmessage + \"<br/>的罚息余额为零或租金没有收完,不允许生成！\"};\r\n");
      out.write("\t\t\tmini.alert(tempMessage);\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tmini.alert(\"生成罚息减免回笼明细成功！\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tminiTable.deselectAll(false);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tmini.alert(\"可核销的金额为0不能再核销了\");\r\n");
      out.write("   }\r\n");
      out.write("}\r\n");
      out.write("function checkRentMayIncome(curRowData){\r\n");
      out.write("\tif(parseFloat(curRowData.penaltyoverage)>0){\r\n");
      out.write("        return true;\r\n");
      out.write("    }else{\r\n");
      out.write("       return false;\r\n");
      out.write("    }\r\n");
      out.write("} */\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_rent_income_plan\" style=\"width: 100%;height: 100%;\"></div>");
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
