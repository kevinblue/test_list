/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-14 01:51:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.rent_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rent_005fincome_005fplan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("' == 'true' || isViewHistoryTask==true){showTools = false;}\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"rent_income_plan\",\r\n");
      out.write("\t\trenderTo: \"id_table_rent_income_plan\",\r\n");
      out.write("\t\twidth : globalClientWidth - 30,\r\n");
      out.write("\t\theight :400,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: [{\r\n");
      out.write("\t\t\thtml: '生成租金核销',\r\n");
      out.write("\t\t\tplain: true,\r\n");
      out.write("\t\t\ticonCls: 'icon-ok',\r\n");
      out.write("\t\t\thandler: function(miniTable, buttonText){\r\n");
      out.write("\t\t\t\tvar currTable = mini.get(\"rent_income_detail\");\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tvar currRowDatas = mini.clone(currTable.getData());\r\n");
      out.write("\t\t\t\tvar selectedDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\tif(selectedDatas.length == 0){\r\n");
      out.write("\t\t\t\t\tmini.alert(\"请先选择要核销的租金数据！\");\r\n");
      out.write("\t\t\t\t\treturn false;\t\t\t\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tvar maymoney=getfundEbankOverMoney();//获得网银余额（网银余额-已增加到每次核销的租金）\r\n");
      out.write("\t\t\t\t\tgenerateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,maymoney);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t\t}, '-' ,{\r\n");
      out.write("\t\t\thtml: '<div readOnly=\"' + !showTools + '\" checked=\"");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("\" onclick=\"saveCheckboxValue(this);\" class=\"mini-checkbox\" style=\"vertical-align: middle;\" text=\"允许隔期回笼\"></div>',\r\n");
      out.write("\t\t\tisHtml: true\r\n");
      out.write("\t\t}],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      if (_jspx_meth_mini_005fparam_005f2(_jspx_page_context))
        return;
      out.write("'),\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'rentlist', header: '期次'},\r\n");
      out.write("\t\t\t{field: 'status', header: '回笼状态', renderer: showRentStatus},\r\n");
      out.write("\t\t\t{field: 'plandate', header: '计划日期'},\r\n");
      out.write("\t\t\t{field: 'rent', header: '计划租金',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'corpus', header: '计划本金',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'interest', header: '计划租息',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'penalty', header: '应收罚息',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'currentoverage', header: '租金余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curcorpusoverage', header: '本金余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'curinterestoverage', header: '利息余额',type:'double',summary : true,dataType : \"currency\"},\r\n");
      out.write("\t\t\t{field: 'penaltyoverage', header: '罚息余额',type:'double',summary : true,dataType : \"currency\"}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("//保存 允许隔期回笼 的value\r\n");
      out.write("function saveCheckboxValue(obj){\r\n");
      out.write("\tvar flag = obj.value;\r\n");
      out.write("\t$(\"#isseparaterentlist\").val(flag);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checkRentMayIncome(curRowData){\r\n");
      out.write("\tif(parseFloat(curRowData.currentoverage)>0||parseFloat(curRowData.penaltyoverage)>0){\r\n");
      out.write("        return true;\r\n");
      out.write("    }else{\r\n");
      out.write("       return false;\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("function getfundEbankOverMoney(){\r\n");
      out.write("\tvar mayMoney=mini.get(\"fund_ebank_data.mayopemoney\").getValue();\r\n");
      out.write("\tmayMoney=parseFloat(mayMoney).toFixed(2);\r\n");
      out.write("\t//var rentData = mini.get(\"rent_income_detail\").getData();\r\n");
      out.write("\tvar rentData =mini.get(\"rent_income_detail\").getData();\r\n");
      out.write("\tif(rentData.length>0){\r\n");
      out.write("          for(var i=0;i<rentData.length;i++){\r\n");
      out.write("        \t  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].corpus);\r\n");
      out.write("        \t  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].interest);\r\n");
      out.write("        \t  mayMoney=parseFloat(mayMoney)-parseFloat(rentData[i].penalty);\r\n");
      out.write("        \t  mayMoney=parseFloat(mayMoney).toFixed(2);\t  \r\n");
      out.write("          }\r\n");
      out.write("          return mayMoney;      \r\n");
      out.write("\t}else{\r\n");
      out.write("\t\treturn mayMoney;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("//生成回笼明细\r\n");
      out.write("function generateRentDetail(miniTable,currTable,currRowDatas,selectedDatas,ebankmoney){\r\n");
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
      out.write("\t\t\trowObj.ebanknumber =\"");
      if (_jspx_meth_mini_005fparam_005f3(_jspx_page_context))
        return;
      out.write("\"; \r\n");
      out.write("\t\t\trowObj.ebdataid =\"");
      if (_jspx_meth_mini_005fparam_005f4(_jspx_page_context))
        return;
      out.write("\"; \r\n");
      out.write("\t\t\trowObj.hirelist = getHireList(selectedDatas[i].rentlist);\r\n");
      out.write("\t\t\trowObj.balancemode = \"payfund6\";\r\n");
      out.write("\t\t\trowObj.balancemodename = \"电汇\";\r\n");
      out.write("\t\t\trowObj.hiredate = \"");
      if (_jspx_meth_mini_005fparam_005f5(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\tcorpus = parseFloat(selectedDatas[i].curcorpusoverage);\r\n");
      out.write("\t\t\tinterest = parseFloat(selectedDatas[i].curinterestoverage);\r\n");
      out.write("\t\t\tpenalty = parseFloat(selectedDatas[i].penaltyoverage);\r\n");
      out.write("\t\t\tvar calculateresult = calculatePlanRent(allMoney, corpus, interest, penalty);\r\n");
      out.write("\t\t\tallMoney = parseFloat(calculateresult.lastMoney).toFixed(2);\r\n");
      out.write("\t\t\trowObj.rent = calculateresult.rent;;\r\n");
      out.write("\t\t\trowObj.corpus = calculateresult.corpus;\r\n");
      out.write("\t\t\trowObj.interest = calculateresult.interest;\r\n");
      out.write("\t\t\trowObj.penalty = calculateresult.penalty;\r\n");
      out.write("\t\t\trowObj.curcorpusoverage = selectedDatas[i].curcorpusoverage;\r\n");
      out.write("\t\t\trowObj.curinterestoverage = selectedDatas[i].curinterestoverage;\r\n");
      out.write("\t\t\trowObj.penaltyoverage = selectedDatas[i].penaltyoverage;\t\t\t\t\r\n");
      out.write("\t\t\trowObj.rentadjust = 0;\r\n");
      out.write("\t\t\trowObj.corpusadjust = 0;\r\n");
      out.write("\t\t\trowObj.interestadjust = 0;\r\n");
      out.write("\t\t\trowObj.penaltyadjust = 0;\r\n");
      out.write("\t\t\trowObj.accountingdate =\"");
      if (_jspx_meth_mini_005fparam_005f6(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\trowObj.ownbank =\"");
      if (_jspx_meth_mini_005fparam_005f7(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\trowObj.ownaccount = \"");
      if (_jspx_meth_mini_005fparam_005f8(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\trowObj.ownnumber =\"");
      if (_jspx_meth_mini_005fparam_005f9(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\trowObj.hireobject = \"");
      if (_jspx_meth_mini_005fparam_005f10(_jspx_page_context))
        return;
      out.write("\"; \r\n");
      out.write("\t\t\trowObj.hirebank =\"");
      if (_jspx_meth_mini_005fparam_005f11(_jspx_page_context))
        return;
      out.write("\";  \r\n");
      out.write("\t\t\trowObj.hireaccount =\"");
      if (_jspx_meth_mini_005fparam_005f12(_jspx_page_context))
        return;
      out.write("\";    \r\n");
      out.write("\t\t\trowObj.hirenumber = \"");
      if (_jspx_meth_mini_005fparam_005f13(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t\t\tcurrRowDatas.push(mini.clone(rowObj));\r\n");
      out.write("\t\t}  \r\n");
      out.write("\t\tcurrRowDatas.sort(function(a,b){return a.planlist - b.planlist;});\r\n");
      out.write("\t    currTable.setData(currRowDatas);\r\n");
      out.write("\t\tif(message != \"\"||cmessage!=\"\"||dmessage){\r\n");
      out.write("\t\t\tvar tempMessage=\"操作成功，\";\r\n");
      out.write("\t\t\tif(message != \"\"){tempMessage+=\"其中计划期项为<br/>\" + message + \"<br/>的数据已添加到实收计划中！\"};\r\n");
      out.write("\t\t\tif(cmessage != \"\"){tempMessage+=\"其中计划期项为<br/>\" + cmessage + \"<br/>的数据已回笼不能再核销！\"};\r\n");
      out.write("\t\t\tif(dmessage != \"\"){tempMessage+=\"其中计划期项为<br/>\" + dmessage + \"<br/>的数据已没有网银余额进行核销！\"};\r\n");
      out.write("\t\t\tmini.alert(tempMessage);\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tmini.alert(\"生成租金回笼明细成功！\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tminiTable.deselectAll(false);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tmini.alert(\"可核销的金额为0不能再核销了\");\r\n");
      out.write("   }\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<input type=\"hidden\" id=\"isseparaterentlist\" name=\"isseparaterentlist\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty isseparaterentlist ? false : isseparaterentlist}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
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

  private boolean _jspx_meth_mini_005fparam_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f0 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f0.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(6,5) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(36,54) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("isseparaterentlist");
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(36,54) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setDefaultValue("false");
    int _jspx_eval_mini_005fparam_005f1 = _jspx_th_mini_005fparam_005f1.doStartTag();
    if (_jspx_th_mini_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f2 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f2.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(39,25) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setName("json_rent_income_plan_str");
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(39,25) name = defaultValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setDefaultValue("[]");
    int _jspx_eval_mini_005fparam_005f2 = _jspx_th_mini_005fparam_005f2.doStartTag();
    if (_jspx_th_mini_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fdefaultValue_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f3 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f3.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f3.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(115,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f3.setName("fund_ebank_data.id");
    int _jspx_eval_mini_005fparam_005f3 = _jspx_th_mini_005fparam_005f3.doStartTag();
    if (_jspx_th_mini_005fparam_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f3);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f4 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f4.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f4.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(116,21) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f4.setName("fund_ebank_data.ebdataid");
    int _jspx_eval_mini_005fparam_005f4 = _jspx_th_mini_005fparam_005f4.doStartTag();
    if (_jspx_th_mini_005fparam_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f4);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f5(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f5 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f5.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f5.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(120,22) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f5.setName("fund_ebank_data.factdate");
    int _jspx_eval_mini_005fparam_005f5 = _jspx_th_mini_005fparam_005f5.doStartTag();
    if (_jspx_th_mini_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f6(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f6 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f6.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f6.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(137,27) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f6.setName("fund_ebank_data.factdate");
    int _jspx_eval_mini_005fparam_005f6 = _jspx_th_mini_005fparam_005f6.doStartTag();
    if (_jspx_th_mini_005fparam_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f6);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f7(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f7 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f7.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f7.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(138,20) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f7.setName("fund_ebank_data.ownbank");
    int _jspx_eval_mini_005fparam_005f7 = _jspx_th_mini_005fparam_005f7.doStartTag();
    if (_jspx_th_mini_005fparam_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f7);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f8(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f8 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f8.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f8.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(139,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f8.setName("fund_ebank_data.ownaccount");
    int _jspx_eval_mini_005fparam_005f8 = _jspx_th_mini_005fparam_005f8.doStartTag();
    if (_jspx_th_mini_005fparam_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f8);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f9(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f9 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f9.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f9.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(140,22) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f9.setName("fund_ebank_data.ownaccnumber");
    int _jspx_eval_mini_005fparam_005f9 = _jspx_th_mini_005fparam_005f9.doStartTag();
    if (_jspx_th_mini_005fparam_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f9);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f10(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f10 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f10.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f10.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(141,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f10.setName("fund_ebank_data.clientname");
    int _jspx_eval_mini_005fparam_005f10 = _jspx_th_mini_005fparam_005f10.doStartTag();
    if (_jspx_th_mini_005fparam_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f10);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f11(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f11 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f11.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f11.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(142,21) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f11.setName("fund_ebank_data.clientbank");
    int _jspx_eval_mini_005fparam_005f11 = _jspx_th_mini_005fparam_005f11.doStartTag();
    if (_jspx_th_mini_005fparam_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f11);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f12(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f12 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f12.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f12.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(143,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f12.setName("fund_ebank_data.clientaccount");
    int _jspx_eval_mini_005fparam_005f12 = _jspx_th_mini_005fparam_005f12.doStartTag();
    if (_jspx_th_mini_005fparam_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f12);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f13(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f13 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f13.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f13.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/forms/rent/rent_comm/rent_income_plan.jsp(144,24) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f13.setName("fund_ebank_data.clientaccnumber");
    int _jspx_eval_mini_005fparam_005f13 = _jspx_th_mini_005fparam_005f13.doStartTag();
    if (_jspx_th_mini_005fparam_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f13);
    return false;
  }
}
