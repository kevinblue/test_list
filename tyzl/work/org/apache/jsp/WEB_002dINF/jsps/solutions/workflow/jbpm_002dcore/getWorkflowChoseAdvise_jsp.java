/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-19 05:22:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.jbpm_002dcore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class getWorkflowChoseAdvise_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tlds/spring.tld", Long.valueOf(1486341662563L));
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody != null) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.release();
    if (_005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody != null) _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody.release();
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

      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\r\n");
      out.write("<!-- 弹出意见框开始 -->\r\n");
      out.write("<div id=\"id_adviseContainerWindow\" class=\"mini-window\" title=\"");
      if (_jspx_meth_spring_005fmessage_005f0(_jspx_page_context))
        return;
      out.write("\" style=\"display:none;width:350px;\">\r\n");
      out.write("\t<div style=\"padding: 5px 0px;\">\r\n");
      out.write("\t\t<table style=\"width:330px\" style=\"padding:1px;\">\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t<td style=\"width:80px;text-align:right;\">");
      if (_jspx_meth_spring_005fmessage_005f1(_jspx_page_context))
        return;
      out.write("：</td>\r\n");
      out.write("\t\t\t\t<td style=\"text-align:left;\"><div id=\"id_currentSystemTime\"></div></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-even\" style=\"width:100px;\">\r\n");
      out.write("\t\t\t\t<td style=\"width:80px;text-align:right;\">");
      if (_jspx_meth_spring_005fmessage_005f2(_jspx_page_context))
        return;
      out.write("：</td>\r\n");
      out.write("\t\t\t\t<td style=\"text-align:left;\">\r\n");
      out.write("\t\t\t\t   ");
      if (_jspx_meth_mini_005fdict_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr class=\"tr-odd\">\r\n");
      out.write("\t\t\t\t<td style=\"width:80px;\">&nbsp;</td>\r\n");
      out.write("\t\t\t\t<td style=\"text-align:left;\">\r\n");
      out.write("\t\t\t\t\t<textarea id=\"text_selectedAdvise\" style=\"width:200px;height:100px;border:1px solid #DDD;font-size: 12px;\"></textarea>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div style=\"padding:5px 0px;text-align:center;border-top: 1px solid #AAA; \"> \r\n");
      out.write("\t\t<a class=\"mini-button\" onclick='__adviseContainerWindowOk'>");
      if (_jspx_meth_spring_005fmessage_005f3(_jspx_page_context))
        return;
      out.write("</a>\r\n");
      out.write("\t\t<a class=\"mini-button\" onclick='__adviseContainerWindowNo'>");
      if (_jspx_meth_spring_005fmessage_005f4(_jspx_page_context))
        return;
      out.write("</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction __adviseContainerWindowOk(){\r\n");
      out.write("\t\tfillCurrentCommonAdvise();\r\n");
      out.write("\t\tmini.get(\"id_adviseContainerWindow\").hide();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction __adviseContainerWindowNo(){\r\n");
      out.write("\t\tmini.get(\"id_adviseContainerWindow\").hide();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tmini.parse(document.getElementById('id_adviseContainerWindow'));\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<textarea id='id_processedAdviseTxt' style=\"display:none;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentJBPMWorkflowHistoryInfoUser.processedAdvise}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\r\n");
      out.write("<textarea id='id_assignedAdviseTxt' style=\"display:none;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentJBPMWorkflowHistoryInfoUser.assignedAdvise}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tvar currentSavedAdvise = (jQuery(\"#id_processedAdviseTxt\").val()||jQuery(\"#id_assignedAdviseTxt\").val());\r\n");
      out.write("\t\r\n");
      out.write("\t// 加载意见列表意见\r\n");
      out.write("\tfunction addCommonAdivse(hidden_text_choseAdviseValue) {\r\n");
      out.write("\t\tvar hidden_text_choseAdvise = document.getElementById('id_hidden_text_choseAdvise');\r\n");
      out.write("\t\thidden_text_choseAdvise.value = hidden_text_choseAdviseValue;\r\n");
      out.write("\t\tvar workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar insertHtml ='<div id=\"id_currentTaskFilledAdvise_1\" style=\"border:0px solid silver;padding-left:5px;height:25px;line-height:25px;background-color:#EFEFEF;\">';\r\n");
      out.write("\t\tinsertHtml+='\t<span style=\"width:200px;height:100%;padding-top:5px;font-size:12px;\">【'+workFlowlocale['step']+'】<font color=\"red\" style=\"font-weight:BOLD;\">'+workFlowlocale['currentHandle']+'</font></span>';\r\n");
      out.write("\t\tinsertHtml+='\t<span style=\"width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;\">【'+workFlowlocale['WriteDate']+'】'+ getCurDateTime()+'</span>';\r\n");
      out.write("\t\tinsertHtml+='\t<span style=\"width:150px;margin-left:20%;height:100%;padding-top:4px;font-size:12px;\">';\r\n");
      out.write("\t\tif(!isCompletedTask){\r\n");
      out.write("\t\t\tinsertHtml+='\t\t<a href=\"javascript:void(0);\" style=\"text-decoration:none;\" onclick=\"showModalCommonAdivseWindow();\"><img align=\"absmiddle\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/menuIcons/pencil.png\" border=\"0\"/>&nbsp;'+workFlowlocale['Edit']+'</a>';\r\n");
      out.write("\t\t\tinsertHtml+='\t\t<a href=\"javascript:void(0);\" style=\"text-decoration:none;margin-left:20px;\" onclick=\"delCommonAdivse();\"><img align=\"absmiddle\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/menuIcons/no.png\" border=\"0\"/>&nbsp;'+workFlowlocale['Remove']+'</a></span>';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tinsertHtml+='</div>';\r\n");
      out.write("\t\tinsertHtml+='<div id=\"id_currentTaskFilledAdvise_2\" style=\"border:0px solid silver;border-top:0px solid #FFFFFF;border-bottom:0px;padding-left:20px;height:25px;line-height:25px;background-color:#FFFFFF;\">';\r\n");
      out.write("\t\tinsertHtml+='\t<span style=\"height:100%;padding-top:5px;font-size:12px;\">'+hidden_text_choseAdvise.value+'</span>';\r\n");
      out.write("\t\tinsertHtml+='</div>';\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tworkflowAdviseContainer.innerHTML = (insertHtml+workflowAdviseContainer.innerHTML);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//通用意见弹出窗口\r\n");
      out.write("\tfunction showModalCommonAdivseWindow() {\r\n");
      out.write("\t\tvar hidden_text_choseAdvise=$(\"#id_hidden_text_choseAdvise\").val();\r\n");
      out.write("\t\t$(\"#text_selectedAdvise\").val(hidden_text_choseAdvise);\r\n");
      out.write("\t\t$(\"#id_currentSystemTime\").html(getCurDateTime());\r\n");
      out.write("\t\tvar win = mini.get(\"id_adviseContainerWindow\").show('center','middle');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//添加或者修改当前流程意见\r\n");
      out.write("\tfunction fillCurrentCommonAdvise(){\r\n");
      out.write("\t\tvar hidden_text_choseAdvise = document.getElementById('id_hidden_text_choseAdvise');\r\n");
      out.write("\t\thidden_text_choseAdvise.value = document.getElementById('text_selectedAdvise').value;\r\n");
      out.write("\t\tvar workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');\r\n");
      out.write("\t\tvar currentTaskFilledAdvise_1 = document.getElementById('id_currentTaskFilledAdvise_1');\r\n");
      out.write("\t\tif(currentTaskFilledAdvise_1) {\r\n");
      out.write("\t\t\tworkflowAdviseContainer.removeChild(currentTaskFilledAdvise_1);\r\n");
      out.write("\t\t\tvar currentTaskFilledAdvise_2 = document.getElementById('id_currentTaskFilledAdvise_2');\r\n");
      out.write("\t\t\tworkflowAdviseContainer.removeChild(currentTaskFilledAdvise_2);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tvar insertHtml = '';\r\n");
      out.write("\t\tinsertHtml +='<div id=\"id_currentTaskFilledAdvise_1\" style=\"border:0px solid silver;padding:5px;height:25px;line-height:25px;background-color:#EFEFEF;\">';\r\n");
      out.write("\t\tinsertHtml +='\t<span style=\"width:200px;height:100%;padding-top:5px;font-size:12px;\">【'+workFlowlocale['step']+'】<font color=\"red\" style=\"font-weight:BOLD;\">'+workFlowlocale['currentHandle']+'</font></span>';\r\n");
      out.write("\t\tinsertHtml +='\t<span style=\"width:300px;margin-left:5%;height:100%;padding-top:5px;font-size:12px;\">【'+workFlowlocale['WriteDate']+'】'+ getCurDateTime()+'</span>';\r\n");
      out.write("\t\tinsertHtml +='\t<span style=\"width:150px;margin-left:20%;height:100%;padding-top:4px;font-size:12px;\">';\r\n");
      out.write("\t\tinsertHtml +='\t\t<a href=\"javascript:void(0);\" style=\"text-decoration:none;\" onclick=\"showModalCommonAdivseWindow();\"><img align=\"absmiddle\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/menuIcons/pencil.png\" border=\"0\"/>&nbsp;'+workFlowlocale['Edit']+'</a>';\r\n");
      out.write("\t\tinsertHtml +='\t\t<a href=\"javascript:void(0);\" style=\"text-decoration:none;margin-left:20px;\" onclick=\"delCommonAdivse();\"><img align=\"absmiddle\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/menuIcons/no.png\" border=\"0\"/>&nbsp;'+workFlowlocale['Remove']+'</a>';\r\n");
      out.write("\t\tinsertHtml +='   </span>';\r\n");
      out.write("\t\tinsertHtml +='</div>';\r\n");
      out.write("\t\tinsertHtml +='<div id=\"id_currentTaskFilledAdvise_2\" style=\"border:0px solid silver;border-top:0px solid #FFFFFF;border-bottom:0px;padding-left:20px;height:25px;line-height:25px;background-color:#FFFFFF;\">';\r\n");
      out.write("\t\tinsertHtml +='\t<span style=\"height:100%;padding-top:5px;font-size:12px;\">'+hidden_text_choseAdvise.value+'</span>';\r\n");
      out.write("\t\tinsertHtml +='</div>';\r\n");
      out.write("\t\tinsertHtml +='</div>';\r\n");
      out.write("\t\tworkflowAdviseContainer.innerHTML = (insertHtml+workflowAdviseContainer.innerHTML);\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//删除当前流程意见\r\n");
      out.write("\tfunction delCommonAdivse() {\r\n");
      out.write("\t\tvar workflowAdviseContainer = document.getElementById('id_workflowAdviseContainer');\r\n");
      out.write("\t\tvar currentTaskFilledAdvise_1 = document.getElementById('id_currentTaskFilledAdvise_1');\r\n");
      out.write("\t\tif(currentTaskFilledAdvise_1) {\r\n");
      out.write("\t\t\tworkflowAdviseContainer.removeChild(currentTaskFilledAdvise_1);\r\n");
      out.write("\t\t\tvar currentTaskFilledAdvise_2 = document.getElementById('id_currentTaskFilledAdvise_2');\r\n");
      out.write("\t\t\tworkflowAdviseContainer.removeChild(currentTaskFilledAdvise_2);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$(\"#id_hidden_text_choseAdvise\").val(\"\");\r\n");
      out.write("\t}\r\n");
      out.write("\t//判断已经保存过了意见\r\n");
      out.write("\tif(currentSavedAdvise && !isCompletedTask) {\r\n");
      out.write("\t\t$(\"#id_hidden_text_choseAdvise\").val(currentSavedAdvise);\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction setWorkflowChoseAdvise(e){\r\n");
      out.write("\t\t var combox=e.sender;\r\n");
      out.write("\t\t selectedAdviseValue=combox.text\r\n");
      out.write("\t\t $('#text_selectedAdvise').val(selectedAdviseValue);\r\n");
      out.write("\t\t $('#id_hidden_text_choseAdvise').val(selectedAdviseValue);\r\n");
      out.write("    }\r\n");
      out.write("</script>\t\t\r\n");
      out.write("<!-- 弹出意见框结束-->");
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

  private boolean _jspx_meth_spring_005fmessage_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f0 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(5,62) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f0.setCode("WriteOpinion");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(5,62) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f0.setText("填写意见");
    int[] _jspx_push_body_count_spring_005fmessage_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f0 = _jspx_th_spring_005fmessage_005f0.doStartTag();
      if (_jspx_th_spring_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f0.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f1 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f1.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f1.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(9,45) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f1.setCode("CurrentTime");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(9,45) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f1.setText("当前时间");
    int[] _jspx_push_body_count_spring_005fmessage_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f1 = _jspx_th_spring_005fmessage_005f1.doStartTag();
      if (_jspx_th_spring_005fmessage_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f1.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f2 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f2.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f2.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(13,45) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f2.setCode("CommonOpinion");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(13,45) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f2.setText("常用意见");
    int[] _jspx_push_body_count_spring_005fmessage_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f2 = _jspx_th_spring_005fmessage_005f2.doStartTag();
      if (_jspx_th_spring_005fmessage_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f2.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_mini_005fdict_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:dict
    com.tenwa.leasing.tag.MiniDictTag _jspx_th_mini_005fdict_005f0 = (com.tenwa.leasing.tag.MiniDictTag) _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody.get(com.tenwa.leasing.tag.MiniDictTag.class);
    _jspx_th_mini_005fdict_005f0.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fdict_005f0.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(15,7) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fdict_005f0.setName("selectAdvise_combo");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(15,7) name = otherproperties type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fdict_005f0.setOtherproperties("width=\"200px\" onvaluechanged=\"setWorkflowChoseAdvise\" ");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(15,7) name = parentid type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fdict_005f0.setParentid("JbpmCommonAdvise");
    int _jspx_eval_mini_005fdict_005f0 = _jspx_th_mini_005fdict_005f0.doStartTag();
    if (_jspx_th_mini_005fdict_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody.reuse(_jspx_th_mini_005fdict_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fdict_0026_005fparentid_005fotherproperties_005fname_005fnobody.reuse(_jspx_th_mini_005fdict_005f0);
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f3 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f3.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f3.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(27,61) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f3.setCode("Confirm");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(27,61) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f3.setText("确定");
    int[] _jspx_push_body_count_spring_005fmessage_005f3 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f3 = _jspx_th_spring_005fmessage_005f3.doStartTag();
      if (_jspx_th_spring_005fmessage_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f3[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f3.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f3.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f3);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f4 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f4.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f4.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(28,61) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f4.setCode("Cancel");
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowChoseAdvise.jsp(28,61) name = text type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f4.setText("取消");
    int[] _jspx_push_body_count_spring_005fmessage_005f4 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f4 = _jspx_th_spring_005fmessage_005f4.doStartTag();
      if (_jspx_th_spring_005fmessage_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f4[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f4.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f4.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005ftext_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f4);
    }
    return false;
  }
}