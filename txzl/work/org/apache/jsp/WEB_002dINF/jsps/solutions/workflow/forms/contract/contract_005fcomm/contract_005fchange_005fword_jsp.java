/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-09 04:12:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fchange_005fword_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/init.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/tenwa.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("seajs.use([\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/init.js\"]);\r\n");
      out.write("var leaseForm = [{value: '1',name: '直租'}, {value: '2',name: '回租'}, {value: '0',name: '其它'}];\r\n");
      out.write("//获取合同模板\r\n");
      out.write("function chargeTemplate() {\r\n");
      out.write("\t\tvar param = {};\r\n");
      out.write("\t\tparam[\"oneClassify\"] = 'wordtempletypefirst8';\r\n");
      out.write("\t\tparam[\"twoClassify\"] = 'wordtempletypetwo.97';\r\n");
      out.write("\t\t//window.currentDeleteFileLoadMask = new tracywindyLoadMask(document.body, \"正在加载合同模板 请稍等...\");\r\n");
      out.write("\t\t//currentDeleteFileLoadMask.show();\r\n");
      out.write("\t    param[\"changeRowNum\"]=8;\r\n");
      out.write("\t\tajaxRequest({\r\n");
      out.write("\t\t\turl : '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/template/loadTemplateByClassify.action',\r\n");
      out.write("\t\t\tmethod : 'POST',\r\n");
      out.write("\t\t\tsuccess : ajaxChargeTemplateCallBack,  \r\n");
      out.write("\t\t\tasync : false,\r\n");
      out.write("\t\t\tfailure : function(res) {\r\n");
      out.write("\t\t\t\t//currentDeleteFileLoadMask.hide();\r\n");
      out.write("\t\t\t\tmini.alert(\"抓合同模板失败!\");\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tparams : param\r\n");
      out.write("\t\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function ajaxChargeTemplateCallBack(rs){\r\n");
      out.write("\tvar svote=rs.responseText;\r\n");
      out.write("    svote=svote.replace(/(^\\s+)|(\\s+$)/g, \"\"); \r\n");
      out.write("    document.getElementById(\"contract_word_check_list\").innerHTML=svote;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getTempateIdInfo() {\r\n");
      out.write("\tvar tempid = \"\";\r\n");
      out.write("\t$('input[name=\"contract_word_list_check_box\"]:checked').each(\r\n");
      out.write("\t\tfunction() {\r\n");
      out.write("\t\t\t//chk_value.push($(this).val());\r\n");
      out.write("\t\t\tif (tempid.length > 0) {\r\n");
      out.write("\t\t\t\ttempid = tempid + \",\";\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\ttempid = tempid + $(this).val();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t);\r\n");
      out.write("\t$('input[name=\"contract_word_list_check_box\"]:checked').each(\r\n");
      out.write("\t\tfunction() {\r\n");
      out.write("\t\t\t//chk_value.push($(this).attr(\"checked\", false));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t);\r\n");
      out.write("\treturn tempid;\r\n");
      out.write("}\r\n");
      out.write("function createContractWord() {\r\n");
      out.write("\tvar tempids = getTempateIdInfo();\r\n");
      out.write("\tif (\"\" == tempids){\r\n");
      out.write("\t\tmini.alert(\"请选择合同模板!!!\");\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar changeTime=mini.getbyName('contract_change_info.changedate').getValue();\r\n");
      out.write("\tif(!changeTime){\r\n");
      out.write("\t\tmini.alert('请填写变更说明!!!');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar changeInfo=mini.getbyName('fund_rent_adjust.paydayadjust').getValue();\r\n");
      out.write("\tif(!changeInfo){\r\n");
      out.write("\t\tmini.alert('请填写变更信息!!!');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar params = {};\r\n");
      out.write("\tparams[\"flowunid\"] = flowUnid;\r\n");
      out.write("\tparams[\"templateid\"] = tempids;\r\n");
      out.write("\tparams[\"custcreatetemplateno\"]=\"\";\r\n");
      out.write("\tparams[\"custcreatetemplatemethod\"]=\"\";\r\n");
      out.write("\tinitCreateWordData(params);\r\n");
      out.write("\tvar fileTeplate = new fileTemplateConfig({\r\n");
      out.write("\t\ttableid : \"contract_word\",\r\n");
      out.write("\t\tmodelname : \"合同管理\",\r\n");
      out.write("\t\turl : '/leasing/template/CreateFileByTemplate.action',\r\n");
      out.write("\t\tparames : params\r\n");
      out.write("\t});\r\n");
      out.write("\tfileTeplate.createFile();\r\n");
      out.write("}\r\n");
      out.write("function initCreateWordData(params) {\r\n");
      out.write("\t//合同基本信息\r\n");
      out.write("\tvar contract_base_info_form=new mini.Form(\"contract_base_info_form\");\r\n");
      out.write("\tvar contract_base_info_data=contract_base_info_form.getData().contract_info;\r\n");
      out.write("\tfor(var cbid in contract_base_info_data){\r\n");
      out.write("\t\tparams[\"contract_info.\"+cbid]=contract_base_info_data[cbid];\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['contract_info.leasformname']=mini.getbyName(\"contract_info.leasform\").getValue();\r\n");
      out.write("\tparams['contractnumbre.maincontract']=generateContractNo('L',0);\r\n");
      out.write("\tparams['contractnumbre.contractno']=generateContractNo('C',0);\r\n");
      out.write("\t\r\n");
      out.write("\t//原租金计划\r\n");
      out.write("\tvar fund_rent_plan=mini.get('fund_rent_plan').getData();\r\n");
      out.write("\t\r\n");
      out.write("\t//现租金计划\r\n");
      out.write("\tvar fund_rent_plan_new=mini.get('fund_rent_plan_new').getData();\r\n");
      out.write("\t\r\n");
      out.write("    //变更信息:年,月,日,合同编号,开始期次\r\n");
      out.write("    //change_year,change_month,change_day,contractid,startlist2\r\n");
      out.write("\t//变更年,月,日\r\n");
      out.write("    var change_year=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'yyyy');\r\n");
      out.write("    var change_month=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'MM');\r\n");
      out.write("    var change_day=mini.formatDate(mini.getbyName('contract_change_info.changedate').getValue(),'dd');\r\n");
      out.write("    params['change_info.change_year']=change_year;\r\n");
      out.write("    params['change_info.change_month']=change_month;\r\n");
      out.write("    params['change_info.change_day']=change_day;\r\n");
      out.write("    //合同编号\r\n");
      out.write("    params['change_info.contractid']=mini.getbyName('contract_info.contractid').getValue();\r\n");
      out.write("    //开始期次\r\n");
      out.write("\tparams['change_info.startlist']='第'+mini.getbyName('fund_rent_adjust.startlist').getValue()+'期';\r\n");
      out.write("    params['change_info.startlist2']=mini.getbyName('fund_rent_adjust.startlist').getValue();\r\n");
      out.write("    //支付时间\r\n");
      out.write("    params['change_info.paydayadjust']=mini.getbyName('fund_rent_adjust.paydayadjust').getValue();\r\n");
      out.write("    var adjust_year=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'yyyy');\r\n");
      out.write("    var adjust_month=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'MM');\r\n");
      out.write("    var adjust_day=mini.formatDate(mini.getbyName('fund_rent_adjust.paydayadjust').getValue(),'dd');\r\n");
      out.write("    params['change_info.paydayadjust']=adjust_year+'年'+adjust_month+'月'+adjust_day+'日';\r\n");
      out.write("\r\n");
      out.write("\t//期数,计划日期,调整前的租金,租赁本金,利息,调整后的租金额,租赁本金,利息\r\n");
      out.write("\t//rentlist,plandate,rent_old,corpus_old,interest_old,rent_new,corpus_new,interest_new\r\n");
      out.write("    var list=0;\r\n");
      out.write("\tif(fund_rent_plan.length>fund_rent_plan_new.length){\r\n");
      out.write("\t\tlist=fund_rent_plan.length;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tlist=fund_rent_plan_new.length;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar rent_plans=[];\r\n");
      out.write("\tfor(var i=0;i<list;i++){\r\n");
      out.write("\t\tvar rent_plan={};\r\n");
      out.write("\t\tif(mini.getbyName('fund_rent_adjust.startlist').getValue()==fund_rent_plan[i].rentlist){\r\n");
      out.write("\t\t\tparams['change_info.firstrent']=fund_rent_plan[i].rent;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(fund_rent_plan.length>i){\r\n");
      out.write("\t\t\t//期次\r\n");
      out.write("\t\t\trent_plan['rentlist']=fund_rent_plan[i].rentlist;\r\n");
      out.write("\t\t\t//支付时间\r\n");
      out.write("\t\t\tvar date=tenwa.toDate(fund_rent_plan[i].plandate,'yyyy年MM月dd日');\r\n");
      out.write("\t\t\trent_plan['plandate']=tenwa.toChar(date,'yyyy年MM月dd日');\r\n");
      out.write("\t\t\t//调整前的租金\r\n");
      out.write("\t\t\tvar rent_old=fund_rent_plan[i].rent;\r\n");
      out.write("\t\t\trent_plan['rent_old']=tenwa.money2Thousand(parseFloat(rent_old).toFixed(2));\r\n");
      out.write("\t\t    //调整前的租赁本金\r\n");
      out.write("\t\t    var corpus_old=fund_rent_plan[i].corpus;\r\n");
      out.write("\t\t    if(corpus_old){\r\n");
      out.write("\t\t    \trent_plan['corpus_old']=tenwa.money2Thousand(parseFloat(corpus_old).toFixed(2));\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t\t    rent_plan['corpus_old']='0.00';\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    //调整前的利息\r\n");
      out.write("\t\t    var interest_old=fund_rent_plan[i].interest;\r\n");
      out.write("\t\t    if(interest_old){\r\n");
      out.write("\t\t    \trent_plan['interest_old']=tenwa.money2Thousand(parseFloat(interest_old).toFixed(2));\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t    \trent_plan['interest_old']='0.00';\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(fund_rent_plan_new.length>i){\r\n");
      out.write("\t\t\t//期次\r\n");
      out.write("\t\t\trent_plan['rentlist']=fund_rent_plan_new[i].rentlist;\r\n");
      out.write("\t\t\t//支付时间\r\n");
      out.write("\t\t\tvar date=tenwa.toDate(fund_rent_plan_new[i].plandate,'yyyy年MM月dd日');\r\n");
      out.write("\t\t\trent_plan['plandate']=tenwa.toChar(date,'yyyy年MM月dd日');\r\n");
      out.write("\t\t\t//调整后的租金\r\n");
      out.write("\t\t\tvar rent_new=fund_rent_plan_new[i].rent;\r\n");
      out.write("\t\t\trent_plan['rent_new']=tenwa.money2Thousand(parseFloat(rent_new).toFixed(2));\r\n");
      out.write("\t\t    //调整后的租赁本金\r\n");
      out.write("\t\t    var corpus_new=fund_rent_plan_new[i].corpus;\r\n");
      out.write("\t\t    if(corpus_new){\r\n");
      out.write("\t\t    \trent_plan['corpus_new']=tenwa.money2Thousand(parseFloat(corpus_new).toFixed(2));\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t\t    rent_plan['corpus_new']='0.00';\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    //调整后的利息\r\n");
      out.write("\t\t    var interest_new=fund_rent_plan_new[i].interest;\r\n");
      out.write("\t\t    if(interest_new){\r\n");
      out.write("\t\t    \trent_plan['interest_new']=tenwa.money2Thousand(parseFloat(interest_new).toFixed(2));\r\n");
      out.write("\t\t    }else{\r\n");
      out.write("\t\t    \trent_plan['interest_new']='0.00';\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\trent_plans.push(rent_plan);\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['rent_plans']=mini.encode(rent_plans);\r\n");
      out.write("\t\r\n");
      out.write("    //当前系统日期\r\n");
      out.write("    params['system_time.nowTime']= new Date().format(\"yyyy 年 MM 月 dd 日\");\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function generateContractNo(mark,count){\r\n");
      out.write("\tvar level1=\"\";\r\n");
      out.write("\tcount++;\r\n");
      out.write("\tvar level2=mark;\r\n");
      out.write("\tvar contract_number=mini.getbyName('contract_info.contractid').getValue().replace('P','');\r\n");
      out.write("\tvar level3=contract_number.split('-')[0];\r\n");
      out.write("\tif(mini.getbyName('contract_info.businesstype').getValue()=='business_type.factoring'){\r\n");
      out.write("\t\tlevel1='F';\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tvar industrytype=mini.get(\"contract_info.industrytype\").getValue();\r\n");
      out.write("\t\tif(industrytype=='medical'){\r\n");
      out.write("\t\t\tlevel1='H';\r\n");
      out.write("\t\t}else if(industrytype=='Pharma'){\r\n");
      out.write("\t\t\tlevel1='P';\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tlevel1='S';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn level1+level2+level3+\"-\"+count;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" id=\"id_table_generate_contract\">\r\n");
      out.write("\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t<td class=\"td-content-title\"  style=\"text-indent: 0px;\">\r\n");
      out.write("\t\t\t<a id=\"create_contract_word\" class=\"mini-button\" iconCls=\"icon-new\" onclick=\"createContractWord()\" style=\"color: red;font-weight: bold;\">生成文件</a>\r\n");
      out.write("\t\t</td>\r\n");
      out.write("</table>\r\n");
      out.write("<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" >\r\n");
      out.write("\t<tr class=\"tr-even\">\r\n");
      out.write("\t\t<td class=\"td-content-title\">协议模板：</td>\r\n");
      out.write("\t\t<td class=\"td-content\" colspan=\"3\"><div style=\"overflow:hidden;width:100%;\" id=\"contract_word_check_list\">请先选择!</div></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr class=\"tr-contractect-word-list\">\r\n");
      out.write("\t\t<td class=\"td-content\" colspan=\"4\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../contract_comm/contract_list_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\tjQuery(function(){chargeTemplate()});\r\n");
      out.write("</script>");
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
