/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-14 09:22:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fcredit.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005fcredit_005fcommittee2_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\tjQuery(function(){\r\n");
      out.write("\t\t\tvar creditStatus = [{text:'同意',value:'同意'},{text:'不同意',value:'不同意'}];\r\n");
      out.write("\t\t\tmini.get('isagree').set({\r\n");
      out.write("\t\t\t\ttextField : \"text\",\r\n");
      out.write("\t\t\t\tvalueField : \"value\",\r\n");
      out.write("\t\t\t\tdata:creditStatus\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\tfunction saveVote(){   \r\n");
      out.write("\t\t\t   \r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t//var isagree = mini.get('isagree').getValue();//\r\n");
      out.write("\t\t\t\t//var  voteview = mini.get('voteview').getValue();\r\n");
      out.write("\t\t\t\tvar isagree = $(\"#id_processedResultShowTxt\").val();//审批结论\r\n");
      out.write("\t\t\t\tvar  voteview = $(\"#text_selectedAdvise\").val();//审批意见\r\n");
      out.write("\t\t\t\tif(!voteview || !isagree){\r\n");
      out.write("\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\t//mini.alert(\"请先填写是否同意和投票意见！\", '提示');\r\n");
      out.write("\t\t\t\t\t//return false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar param = {};\r\n");
      out.write("\t\t\t\tvar projId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope[\"proj_id\"]}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tparam.projId=  projId;\r\n");
      out.write("\t\t\t\tparam.voteview = voteview;\r\n");
      out.write("\t\t\t\tparam.isagree = isagree;\r\n");
      out.write("\t\t\t\tparam.docid = flowUnid;\r\n");
      out.write("\t\t\t\tparam.flowname=flowName;\r\n");
      out.write("\t\t\t\tparam.stepname=stepName;\r\n");
      out.write("\t\t\t\tvar flag = true;\r\n");
      out.write("\t\t\t\tajaxRequest({\r\n");
      out.write("\t\t\t\t\tparams:param,\r\n");
      out.write("\t\t\t\t\tasync : false,\r\n");
      out.write("\t\t\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/acl/addVote2.acl',\r\n");
      out.write("\t\t\t\t\ttimeout:30*60*1000,\r\n");
      out.write("\t\t\t\t\tsuccess:function(response){\r\n");
      out.write("\t\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\t\tvar msg = eval(\"(\"+response.responseText+\")\");\r\n");
      out.write("\t\t\t\t\t\tif(\"投票失败\"==msg.msg){\r\n");
      out.write("\t\t\t\t\t\t\tmini.alert(\"投票失败!\");\r\n");
      out.write("\t\t\t\t\t\t\tflag=false;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\tfailure:function(response){\r\n");
      out.write("\t\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\t\tmini.alert(\"投票失败！\");\r\n");
      out.write("\t\t\t\t\t\tflag =  false;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\treturn flag;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction getVote(){\r\n");
      out.write("\t\t\tvar param = {};\r\n");
      out.write("\t\t\tvar projId = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope[\"proj_id\"]}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\t\tparam.projId=  projId;\r\n");
      out.write("\t\t\tparam.docid = flowUnid;\r\n");
      out.write("\t\t\tparam.flowname=flowName;\r\n");
      out.write("\t\t\tvar flag = true;\r\n");
      out.write("\t\t\tajaxRequest({\r\n");
      out.write("\t\t\t\tparams:param,\r\n");
      out.write("\t\t\t\turl:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/acl/getVotesByUser.acl',\r\n");
      out.write("\t\t\t\ttimeout:30*60*1000,\r\n");
      out.write("\t\t\t\tsuccess:function(res){\r\n");
      out.write("\t\t\t\t\t//alert(res.responseText);\r\n");
      out.write("\t\t\t\t\tvar votes= eval('('+res.responseText+')');\r\n");
      out.write("\t\t\t\t\tif(votes.results.length>0){\r\n");
      out.write("\t\t\t\t\t\tif(\"null\"==votes.results[0].view){\r\n");
      out.write("\t\t\t\t\t\tmini.getbyName(\"proj_info.voteview\").setValue(\"\");\t\r\n");
      out.write("\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tmini.getbyName(\"proj_info.voteview\").setValue(votes.results[0].view);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\tmini.get(\"isagree\").setValue(votes.results[0].isview);   \r\n");
      out.write("\t\t\t\t    }else{\r\n");
      out.write("\t\t\t\t\t\tmini.getbyName(\"proj_info.voteview\").setValue(\"\");\r\n");
      out.write("\t\t\t\t\t\tmini.get(\"isagree\").setValue(\"\"); \r\n");
      out.write("\t\t\t\t    }\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tfailure:function(response){\r\n");
      out.write("\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\tflag =  false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\treturn flag;\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div style=\"display: none\" id=\"id_project_vote\" title=\"上会投票信息\" > \r\n");
      out.write("    <div class=\"mini-panel\" title=\"上会投票信息\" showCollapseButton=\"true\" style=\"width: 100%;\">\r\n");
      out.write("\t    <table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\"  >\r\n");
      out.write("\t          <tr class=\"tr-project-info tr-odd\">  \r\n");
      out.write("\t             <td class=\"td-content-title\" style=\"width:12%;\">投票意见：</td>\r\n");
      out.write("\t             <td class=\"td-content\" style=\"width:476px;\">\r\n");
      out.write("\t\t             <input name=\"proj_info.isagree\" id=\"isagree\" class=\"mini-combobox\"   label=\"投票意见\" showNullItem=\"false\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_info.isagree']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t </td>\r\n");
      out.write("\t\t\t\t </tr>\r\n");
      out.write("\t\t\t\t <tr class=\"tr-project-info tr-even\">\r\n");
      out.write("\t\t\t\t <td class=\"td-content-title\" style=\"width:12%\">意见：</td>\r\n");
      out.write("\t             <td class=\"td-content\" colspan=\"3\">\r\n");
      out.write("\t\t             <input name=\"proj_info.voteview\" id=\"voteview\" class=\"mini-textarea\"  label=\"意见\" style=\"width:80%;height:100px\"  value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['proj_info.voteview']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t\t\t\t </td>\r\n");
      out.write("\t          </tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</div>    \r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("/* \tmini.getbyName(\"proj_info.voteview\").setValue(\"\");\r\n");
      out.write("\tmini.get(\"isagree\").setValue(\"\"); */\r\n");
      out.write("\tgetVote();\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask==true){miniui_ext.disableFormFields(\"id_project_vote\");};\r\n");
      out.write("\t\r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
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
