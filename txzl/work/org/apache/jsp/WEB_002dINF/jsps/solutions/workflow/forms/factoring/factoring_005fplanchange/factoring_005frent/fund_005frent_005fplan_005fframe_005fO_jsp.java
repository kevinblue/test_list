/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-30 03:36:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.factoring.factoring_005fplanchange.factoring_005frent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005frent_005fplan_005fframe_005fO_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("' == 'true'){showTools = false};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid:\"fund_rent_plan_1\",\r\n");
      out.write("\t\t\trenderTo:\"id_fund_rent_plan_frame_1\",\r\n");
      out.write("\t\t\twidth:globalClientWidth - 30,\r\n");
      out.write("\t\t\theight:400,\r\n");
      out.write("\t\t\tlazyLoad:false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper :false,\r\n");
      out.write("\t\t\tshowPager:false,\r\n");
      out.write("\t\t\tmultiSelect:true,\r\n");
      out.write("\t\t\tshowToolbar:showTools,\r\n");
      out.write("\t\t\tdata:mini.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_fund_rent_plan_str ? \"[]\" :json_fund_rent_plan_str}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\tafterShowWindowCallBack: function(miniTable,miniForm,operFlag){\r\n");
      out.write("\t\t\t\tif(\"add\" == operFlag){\r\n");
      out.write("\t\t\t\t\tvar leaseamtdate = mini.get('leaseamtdate').getValue();\r\n");
      out.write("\t\t\t\t\tvar startrentlist = mini.getbyName(\"rentlist\");\r\n");
      out.write("\t\t\t\t\tvar row = miniTable.getRow(miniTable.getData().length-1);\r\n");
      out.write("\t\t\t\t\tif(null != row){\r\n");
      out.write("\t\t\t\t\t\tstartrentlist.setValue(parseInt(row.rentlist)+1);\r\n");
      out.write("\t\t\t\t\t\tif(leaseamtdate){\r\n");
      out.write("\t\t\t\t\t\t\tvar plandate = mini.getbyName(\"plandate\");\r\n");
      out.write("\t\t\t\t\t\t\tplandate.setValue(getLastPlanDate());\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tstartrentlist.setValue(1);\r\n");
      out.write("\t\t\t\t\t\tif(leaseamtdate){\r\n");
      out.write("\t\t\t\t\t\t\tvar plandate = mini.getbyName(\"plandate\");\r\n");
      out.write("\t\t\t\t\t\t\tplandate.setValue(leaseamtdate);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tstartrentlist.setEnabled(false);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tvalidateForm:function(miniTable, miniForm){\r\n");
      out.write("\t\t\t\tvar datas = miniTable.getData();\r\n");
      out.write("\t\t\t\tvar plandate = mini.getbyName(\"plandate\").getValue();\r\n");
      out.write("\t\t\t\tvar leaseamtdate = mini.get('leaseamtdate').getValue()\r\n");
      out.write("\t\t\t\tif(datas.length == 0){\r\n");
      out.write("\t\t\t\t\tif(!leaseamtdate){\r\n");
      out.write("\t\t\t\t\t\tmini.get('leaseamtdate').setValue(plandate);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tif(leaseamtdate && (plandate < leaseamtdate || plandate > getLastPlanDate())){\r\n");
      out.write("\t\t\t\t\t\tmini.alert('日期不能小于应收账款转让日，且不能大于，规定期限日！');\r\n");
      out.write("\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar currentcorpus =  Number(!(mini.getbyName(\"corpusbusiness\").getValue()) ? 0 : mini.getbyName(\"corpusbusiness\").getValue());\r\n");
      out.write("\t\t\t\tvar currentInterest = Number(!(mini.getbyName(\"interestbusiness\").getValue()) ? 0 : mini.getbyName(\"interestbusiness\").getValue());\r\n");
      out.write("\t\t\t\t//商务条件中保理费收入与保理明细中保理费收入之和相等 商务条件中应收账款受让款等于保理明细中的应收账款受让款相等\r\n");
      out.write("\t\t\t\tfor(var i = 0 ; i < datas.length ;i++){\r\n");
      out.write("\t\t\t\t\tcurrentInterest += Number(!datas[i].currentInterest ? 0 : datas[i].currentInterest);\r\n");
      out.write("\t\t\t\t\tcurrentcorpus += Number(!datas[i].currentcorpus ? 0 : datas[i].currentcorpus);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tmini.get('factoringincome').setValue(currentInterest);\r\n");
      out.write("\t\t\t\tmini.get('factoringpayout').setValue(currentcorpus);\r\n");
      out.write("\t\t\t\t$setThouValue(\"factoringincome\");\r\n");
      out.write("\t\t\t\t$setThouValue(\"factoringpayout\");\r\n");
      out.write("\t\t\t\tvar ratiovalueIncome = getRadio(currentInterest);\r\n");
      out.write("\t\t\t\tvar ratiovaluepayout = getRadio(currentcorpus);\r\n");
      out.write("\t\t\t\tmini.get('factoringpayoutratio').setValue(ratiovaluepayout);\r\n");
      out.write("\t\t\t\tmini.get('factoringincomeratio').setValue(ratiovalueIncome);\r\n");
      out.write("\t\t\t\tmini.getbyName('rent').setValue( Number(!(mini.getbyName(\"corpusbusiness\").getValue()) ? 0 : mini.getbyName(\"corpusbusiness\").getValue()) + Number(!(mini.getbyName(\"interestbusiness\").getValue()) ? 0 : mini.getbyName(\"interestbusiness\").getValue()));\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tconfirmRemoveCallBack:function(miniTable){\t\r\n");
      out.write("\t\t\t\tvar datas = miniTable.getData();\r\n");
      out.write("\t\t\t\tvar selectedRowDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\tvar qixiang=datas[datas.length-1].rentlist;\r\n");
      out.write("\t\t\t\tvar strmax=0;\r\n");
      out.write("\t\t\t\tvar strmin=selectedRowDatas[0].rentlist;\r\n");
      out.write("\t\t\t\tfor(var i=0;i<selectedRowDatas.length;i++){\r\n");
      out.write("\t\t\t\t\tif(selectedRowDatas[i].rentlist>=strmax){\r\n");
      out.write("\t\t\t\t\t\tstrmax=selectedRowDatas[i].rentlist;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tif(selectedRowDatas[i].rentlist<=strmin){\r\n");
      out.write("\t\t\t\t\t\tstrmin=selectedRowDatas[i].rentlist;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(strmax!=qixiang){\r\n");
      out.write("\t\t\t\t\tmini.alert(\"租金计划应该从最后一项开始删除，请重新选择！\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}if(parseInt(strmax)-parseInt(strmin)+1!=selectedRowDatas.length){\r\n");
      out.write("\t\t\t\t\tmini.alert(\"租金计划应该从最后一项开始删除，期项必须是连续的，请重新选择！\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\taddOperCallBack : function (miniTable,rowData){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tremoveOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcopyOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tupdateOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcolumns:[\r\n");
      out.write("\t\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field:'rentlist', header:'期项',formEditorConfig : \r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly : true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,width:180,\r\n");
      out.write("\t\t\t\t\t\ttype: 'int',\r\n");
      out.write("\t\t\t\t\t\tvtype: 'int'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'plandate', header:'日期',\r\n");
      out.write("\t\t\t\t\tformEditorConfig : \r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\trequired: true,width:180,\r\n");
      out.write("\t\t\t\t\t\ttype: 'date',\r\n");
      out.write("\t\t\t\t\t\tvtype: 'date',\r\n");
      out.write("\t\t\t\t\t\tformat: 'yyyy-MM-dd'\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'rent', header: '租金',visible: false,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfieldVisible : false,\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t{field:'corpusbusiness', header:'收回应收账款',dataType :\"currency\",summary :true,align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig : \r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\twidth:180,\r\n");
      out.write("\t\t\t\t\t\ttype: 'float',\r\n");
      out.write("\t\t\t\t\t\tvtype: 'float',\r\n");
      out.write("\t\t\t\t\t\tformat: 'yyyy-MM-dd'\r\n");
      out.write("\t\t\t\t\t}\t\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'interestbusiness', header:'保理费收入',dataType :\"currency\",summary :true,align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig : \r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\twidth:180,\r\n");
      out.write("\t\t\t\t\t\ttype: 'float',\r\n");
      out.write("\t\t\t\t\t\tvtype: 'float',\r\n");
      out.write("\t\t\t\t\t}\t\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'cautionmoneyremain', header:'保证金退还',dataType :\"currency\",summary :true,align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig : \r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\twidth:180,\r\n");
      out.write("\t\t\t\t\t\ttype: 'float',\r\n");
      out.write("\t\t\t\t\t\tvtype: 'float'\r\n");
      out.write("\t\t\t\t\t}\t\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_fund_rent_plan_frame_1\"></div>");
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
