/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-01 06:22:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.fund.fund_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fcollection_005fhis_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid:\"collection_his\",\r\n");
      out.write("\t\trenderTo:\"id_table_collection_his\",\r\n");
      out.write("\t\twidth :globalClientWidth-30,\r\n");
      out.write("\t\theight :400,\r\n");
      out.write("\t\tlazyLoad:false,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\tmultiSelect :true,\r\n");
      out.write("\t\ttitle:\"\",\r\n");
      out.write("\t\tremoteOper :false,\r\n");
      out.write("\t\tshowPager:false,\r\n");
      out.write("\t\tshowToolbar:showTools,\r\n");
      out.write("\t\ttools:toolsArr,\r\n");
      out.write("\t\tdata:JsonUtil.decode('");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("'),\r\n");
      out.write("\t\tcolumns:[\r\n");
      out.write("\t\t\t{type:'indexcolumn'},\r\n");
      out.write("\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t{field:'roll_back',header:'是否已被红冲',renderer :function(e){var rollback = e.record.roll_back;\r\n");
      out.write("\t\t\t\tif(rollback==\"0\"){return \"否\";}else{return \"是\";}}\r\n");
      out.write("\t\t\t},\t\r\n");
      out.write("\t\t\t{field:'fundfundchargeplan', header:'收款编号',visible:false},//计划表主键id\r\n");
      out.write("\t\t\t{field:'ebanknumber', header:'网银编号'},\r\n");
      out.write("\t\t\t{field:'feetype', header:'费用类型',formEditorConfig:{}, visible:false},\r\n");
      out.write("\t\t\t{field:'paymentid', header:'收款编号'},//计划表主键id\r\n");
      out.write("\t\t\t{field:'feetypename', header:'费用类型',formEditorConfig:{}},\r\n");
      out.write("\t\t\t{field:'settlemethod', header:'结算方式', visible:false,formEditorConfig:{newLine:true}},\r\n");
      out.write("\t\t\t{field:'settlemethodname', header:'结算方式',formEditorConfig:{readOnly :true}},\r\n");
      out.write("\t\t\t{field:'factmoney', header:'实收金额',summary :true,dataType :\"currency\"},\r\n");
      out.write("\t\t\t{field:'factdate', header:'实收日期'},\r\n");
      out.write("\t\t\t{field:'feeadjust', header:'调整金额',summary :true,dataType :\"currency\"},\r\n");
      out.write("\t\t\t{field:'accountbank', header:'本方银行'},     \r\n");
      out.write("\t\t\t{field:'account', header:'本方账户'},\r\n");
      out.write("\t\t\t{field:'accnumber', header:'本方账号'},\r\n");
      out.write("\t\t\t{field:'factobject', header:'付款对象'},\r\n");
      out.write("\t\t\t{field:'clientbank', header:'对方银行'},\r\n");
      out.write("\t\t\t{field:'clientaccount', header:'对方账户'},\r\n");
      out.write("\t\t\t{field:'clientaccnumber', header:'对方账号'},\r\n");
      out.write("\t\t\t{field:'accountingdate', header:'会计处理日'},\r\n");
      out.write("\t\t\t{field:'ffcmemo', header:'备注'}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write(" var toolsArr=[{\r\n");
      out.write("\t\thtml :'生成实收红冲明细',\r\n");
      out.write("\t\tplain :true,\r\n");
      out.write("\t\ticonCls :'icon-ok',\r\n");
      out.write("\t\thandler :function(miniTable, buttonText)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tvar curGrid = mini.get(\"redout_current\");//本次资金收款grid\r\n");
      out.write("\t\t\tvar curGrids =curGrid.getData();\r\n");
      out.write("\t\t\tvar rows = miniTable.getSelecteds();   \r\n");
      out.write("\t\t\tif (rows.length==0){mini.alert(\"请勾选数据在操作!\");return false;}\r\n");
      out.write("\t\t\telse if (curGrids.length > 0)\r\n");
      out.write("\t\t\t{ // 重复生成判断\r\n");
      out.write("\t\t\t\tfor ( var n = 0; n < curGrids.length; n++)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tfor(var i=0;i<rows.length;i++){\r\n");
      out.write("                      if(rows[i].id==curGrids[n].pid){\r\n");
      out.write("                    \t mini.alert(rows[i].feetypename+rows[i].factmoney+\"已在本次红冲明细中请不要重复生成!\");return false; }\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tFundCollectRedOutInfo(miniTable);\r\n");
      out.write("\t\t\t\tmini.alert(\"红冲操作成功,请到本次红冲明细中复核!\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tfor(var i=0;i<rows.length;i++){\r\n");
      out.write("\t\t\t\t\t  if(parseFloat(rows[i].roll_back)!=0){ mini.alert(rows[i].feetypename+rows[i].factmoney+\"已红冲 过不能再红冲!\");return false;}\r\n");
      out.write("\t\t\t\t}  \r\n");
      out.write("\t\t\t\tFundCollectRedOutInfo(miniTable);\r\n");
      out.write("\t\t\t\tmini.alert(\"红冲操作成功,请到本次红冲明细中复核!\");\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}];\r\n");
      out.write("\r\n");
      out.write(" function FundCollectRedOutInfo(miniTable)\r\n");
      out.write(" {\r\n");
      out.write(" \t\r\n");
      out.write(" \tvar hisGrid = miniTable.getSelecteds();//获取资金计划grid\r\n");
      out.write(" \tvar currentGrid = mini.get(\"redout_current\");//获取本次红冲grid\r\n");
      out.write(" \tvar curData=currentGrid.getData();\r\n");
      out.write(" \tvar rowDatas = [];\r\n");
      out.write(" \tfor (var i = 0 ;i <hisGrid.length; i++ )\r\n");
      out.write(" \t{  \r\n");
      out.write(" \t\tvar newRow = {}; \r\n");
      out.write(" \t\tnewRow=mini.clone(mini.clone(hisGrid[i]));\r\n");
      out.write(" \t\tnewRow.id=\"\";\r\n");
      out.write(" \t\tnewRow.pid=hisGrid[i].id;\r\n");
      out.write(" \t\tnewRow.settlemethod='payfund9';\r\n");
      out.write(" \t\tnewRow.settlemethodname='红冲';\r\n");
      out.write(" \t\tnewRow.factmoney=parseFloat(hisGrid[i].factmoney)*(-1);\r\n");
      out.write(" \t\tnewRow.feeadjust=parseFloat(hisGrid[i].feeadjust)*(-1);\r\n");
      out.write(" \t\tnewRow.roll_back='-1';\r\n");
      out.write(" \t\tcurData.push(newRow); \r\n");
      out.write(" \t}\r\n");
      out.write(" \tcurrentGrid.setData(curData);\r\n");
      out.write(" \tminiTable.deselectAll(false);\r\n");
      out.write(" }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_table_collection_his\" style=\"width:100%;height:100%;\"></div>\r\n");
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/fund_collection_his_info.jsp(7,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/fund_collection_his_info.jsp(22,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("json_collection_his_str");
    // /WEB-INF/jsps/solutions/workflow/forms/fund/fund_comm/fund_collection_his_info.jsp(22,24) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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