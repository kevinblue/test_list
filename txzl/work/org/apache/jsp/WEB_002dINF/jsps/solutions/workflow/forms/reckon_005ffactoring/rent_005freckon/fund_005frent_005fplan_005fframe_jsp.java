/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-09 07:25:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon_005ffactoring.rent_005freckon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005frent_005fplan_005fframe_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t//if('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false};\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask == true){showTools = false};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid:\"fund_rent_plan\",\r\n");
      out.write("\t\t\trenderTo:\"id_fund_rent_plan_frame\",\r\n");
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
      out.write("\t\t\ttools : ['add', '-', 'edit', '-','remove'],\r\n");
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
      out.write("\t\t\t\tcalFacIncome(miniTable);\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tremoveOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\tif(!miniTable.getData()){\r\n");
      out.write("\t\t\t\t\tmini.get('factoringincome').setValue(0);\r\n");
      out.write("\t\t\t\t\tmini.get('factoringpayout').setValue(0);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tcalFacIncome(miniTable);\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcopyOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\tcalFacIncome(miniTable);\r\n");
      out.write("\t\t\t\t$('#id_json_fund_rent_plan_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tupdateOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\tcalFacIncome(miniTable);\r\n");
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
      out.write("\t\t\t\t\t\tfieldVisible : false\r\n");
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
      out.write("\t\t\t\t\t\tvtype: 'float'\r\n");
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
      out.write("function calFacIncome(miniTable){\r\n");
      out.write("\tvar datas = miniTable.getData();\r\n");
      out.write("\tvar interestbusiness = 0; \r\n");
      out.write("\tvar corpusbusiness =0;\r\n");
      out.write("\tfor(var i=0;i<datas.length;i++){\r\n");
      out.write("\t\tinterestbusiness += Number(datas[i].interestbusiness);\r\n");
      out.write("\t\tcorpusbusiness += Number(datas[i].corpusbusiness);\r\n");
      out.write("\t}\r\n");
      out.write("\tif(interestbusiness > 0 ){\r\n");
      out.write("\t\tmini.get('factoringincome').setValue(formatNumberThousand(interestbusiness));\r\n");
      out.write("\t}\r\n");
      out.write("\tif(corpusbusiness > 0){\r\n");
      out.write("\t\tmini.get('factoringpayout').setValue(formatNumberThousand(corpusbusiness));\r\n");
      out.write("\t}\r\n");
      out.write("\tvar ratiovalueIncome = getRadio(interestbusiness);\r\n");
      out.write("\tvar ratiovaluepayout = getRadio(corpusbusiness);\r\n");
      out.write("\tmini.get('factoringpayoutratio').setValue(ratiovaluepayout);\r\n");
      out.write("\tmini.get('factoringincomeratio').setValue(ratiovalueIncome);\r\n");
      out.write("\tcalSalesvolume();\r\n");
      out.write("\tcalActualfund();\r\n");
      out.write("}\r\n");
      out.write("/* function startPro (e){\r\n");
      out.write("\tif (miniui_ext.submitFormValidation([\"multiform\"]) == false){\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tmini.get('editWindow_cal').hide();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function cancel (e){\r\n");
      out.write("\tmini.get('editWindow_cal').hide();\r\n");
      out.write("} */\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_fund_rent_plan_frame\"></div>\r\n");
      out.write("<!-- <div id=\"editWindow_cal\" class=\"mini-window\" title=\"保理费用\" style=\"width:700px;height:400px;\" \r\n");
      out.write("\t    showModal=\"true\" allowResize=\"true\" allowDrag=\"true\"\r\n");
      out.write("\t    >\r\n");
      out.write("\t    <div id=\"multiform\" title=\"保理费用\">\r\n");
      out.write("\t\t\t<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"10px\" style=\"width:100%;\" >\r\n");
      out.write("\t\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content-title\" style=\"width:80px;\">开始期次：</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t\t\t<input name=\"startlist\" id=\"startlist\" label=\"开始期次\" required readonly  vtype=\"int\" class=\"mini-textbox\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content-title\" style=\"width:80px;\">结束期次：</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t\t\t<input name=\"endlist\" id=\"endlist\" required label=\"结束期次\"  vtype=\"int\" class=\"mini-textbox\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content-title\" style=\"width:80px;\">计划日期：</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t\t\t<input name=\"plandate\" required id =\"plandate\"   class=\"mini-datepicker\" allowInput=\"false\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content-title\" style=\"width:80px;\">结束期次：</td>\r\n");
      out.write("\t\t\t\t\t<td class=\"td-content\">\r\n");
      out.write("\t\t\t\t\t\t<input name=\"endlist\" id=\"endlist\" required label=\"结束期次\"  vtype=\"int\" class=\"mini-textbox\">\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\" colspan=\"4\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"mini-button \" iconCls=\"icon-save\"  onclick=\"startPro\">&nbsp;&nbsp;确定&nbsp;&nbsp;</a>\r\n");
      out.write("\t\t\t\t\t\t<span class=\"separator\"></span>\r\n");
      out.write("\t\t\t\t\t\t<a class=\"mini-button \" iconCls=\"icon-close\"  onclick='cancel'>&nbsp;&nbsp;取消&nbsp;&nbsp;</a>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>   \r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div> -->\r\n");
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
