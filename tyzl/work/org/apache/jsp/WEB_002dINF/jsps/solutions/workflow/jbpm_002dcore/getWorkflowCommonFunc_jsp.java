/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-19 05:23:00 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.jbpm_002dcore;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class getWorkflowCommonFunc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/tlds/minidict.tld", Long.valueOf(1486341662546L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    if (_005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody != null) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.release();
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
      out.write("    <!-- function -->\r\n");
      out.write("   <script type=\"text/javascript\">\r\n");
      out.write("\t   window.onresize = function(){\r\n");
      out.write("\t\t   \tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write("\t\t   \tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write("\t\t   \tcurrentPageClientWidth  = currentPageClientWidth  > 0 ? currentPageClientWidth  : document.body.clientWidth;\r\n");
      out.write("\t\t   \tcurrentPageClientHeight = currentPageClientHeight > 0 ? currentPageClientHeight : document.body.clientHeight;\r\n");
      out.write("\t\t       if(\r\n");
      out.write("\t\t                (Math.abs(currentPageClientWidth-oldCurrentPageClientWidth)>10)\r\n");
      out.write("\t\t              &&(Math.abs(currentPageClientHeight-oldCurrentPageClientHeight)>10)\r\n");
      out.write("\t\t         ){\r\n");
      out.write("\t\t             if(confirm(\"您改变了页面的大小，确定录入内容已保存,重新布局页面么？\")){\r\n");
      out.write("\t\t           \t  window.location.href = window.location.href;\r\n");
      out.write("\t\t\t          }\r\n");
      out.write("\t\t         }\r\n");
      out.write("\t\t   \toldCurrentPageClientWidth  =  currentPageClientWidth;\r\n");
      out.write("\t\t   \toldCurrentPageClientHeight =  currentPageClientHeight;\r\n");
      out.write("\t\t};\r\n");
      out.write("\t\t//点击加载容器内容\r\n");
      out.write("\t\tfunction getLazyLoadedObj(infoFlag,$parent,callback){\r\n");
      out.write("\t\t\t/*if(lazyLoadedObj[infoFlag]){\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}*/\r\n");
      out.write("\t\t\tif(((\"advise\" == infoFlag)||(\"history\" == infoFlag)) && lazyLoadedObj[infoFlag]){\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}else if(lazyLoadedObj[infoFlag]){\r\n");
      out.write("\t\t\t\ttry{$parent.remove(jQuery(\"#id_nextRouteChoseWindow\"));}catch(e){};\r\n");
      out.write("\t\t\t\ttry{$parent.remove(jQuery(\"#id_backRouteChoseWindow\"));}catch(e){};\r\n");
      out.write("\t\t\t\ttry{$parent.remove(jQuery(\"#id_allRouteChoseWindow\"));}catch(e){};\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tmini.mask({\r\n");
      out.write("\t            el: document.body,\r\n");
      out.write("\t            cls: 'mini-mask-loading',\r\n");
      out.write("\t            html: '数据加载中，请稍后...'\r\n");
      out.write("\t        });\r\n");
      out.write("\t\t\tvar params = {\r\n");
      out.write("\t\t\t\tjhiCompletedTaskImplId:'");
      if (_jspx_meth_mini_005fparam_005f0(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("\t\t\t\thistoryProcessInstanceImplDbid:'");
      if (_jspx_meth_mini_005fparam_005f1(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("\t\t\t\tjbpmWorkflowHistoryInfoUserId:'");
      if (_jspx_meth_mini_005fparam_005f2(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("\t\t\t\tinfoFlag:infoFlag\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t        var submitForm = document.getElementById(\"id_submitProcessedForm\");\r\n");
      out.write("\t        var formElements = submitForm.elements;\r\n");
      out.write("\t        for (var i = 0; i < formElements.length; i++) {\r\n");
      out.write("\t        \tvar formElement = formElements[i];\r\n");
      out.write("\t        \tvar inputName   = formElement.name;\r\n");
      out.write("\t        \tvar inputValue  = formElement.value;\r\n");
      out.write("\t        \t//add by zhangc 2015/9/25\r\n");
      out.write("\t        \tinputName = inputName.replace(/'/gi, '&#39;');\r\n");
      out.write("\t        \tinputValue = inputValue.replace(/'/gi, '&#39;');\r\n");
      out.write("\t\t        if(inputName){\r\n");
      out.write("\t\t        \tparams[inputName] = inputValue;\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t\tajaxRequest({\r\n");
      out.write("\t\t\t\turl:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/jbpm/getWorkflowInfo.action\",\r\n");
      out.write("\t\t\t\tasync:false, \r\n");
      out.write("\t\t\t\tsuccess:function(res){\r\n");
      out.write("\t\t\t\t\tvar html = (res.responseText||\"\").replace(/(\\r|\\n)/gi,\"\");\r\n");
      out.write("\t\t\t\t\tif(0 == (html||\"\").indexOf(\"javascript:\")){\r\n");
      out.write("\t\t\t\t\t\t \r\n");
      out.write("\t\t\t\t        var oHead = document.getElementsByTagName(\"HEAD\").item(0); \r\n");
      out.write("\t\t\t\t        var oScript = document.createElement( \"script\" ); \r\n");
      out.write("\t\t\t\t        oScript.language = \"javascript\"; \r\n");
      out.write("\t\t\t\t        oScript.type = \"text/javascript\"; \r\n");
      out.write("\t\t\t\t        oScript.defer = true; \r\n");
      out.write("\t\t\t\t        oScript.text = html.substring(\"javascript:\".length,html.length); \r\n");
      out.write("\t\t\t\t        oHead.appendChild(oScript);\r\n");
      out.write("\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tvar $html = jQuery(html);\r\n");
      out.write("\t\t\t\t\t$parent.append($html);\r\n");
      out.write("\t\t\t\t\tif(callback){\r\n");
      out.write("\t\t\t\t\t\ttry{\r\n");
      out.write("\t\t\t\t\t\t\tcallback();\r\n");
      out.write("\t\t\t\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tlazyLoadedObj[infoFlag] = false;\r\n");
      out.write("\t\t\t\t\t\t\ttry{$parent.remove(lazyLoadedObjRemain[infoFlag]);}catch(e){};\r\n");
      out.write("\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\tlazyLoadedObj[infoFlag] = true;\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tfailure:function(res){\r\n");
      out.write("\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\ttimeout:30*60*1000,\r\n");
      out.write("\t\t\t\tparams:params\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction backRejectChecked(checkName){\r\n");
      out.write("\t\t     jQuery(\"#id_backRouteChoseWindow input[parent_name^='back_chose_'][parent_name!='\"+checkName+\"']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     \r\n");
      out.write("\t\t     jQuery(\"#id_backRouteChoseWindow input[id^='back_chose_'][id!='\"+checkName+\"_checkedAll_actor']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_read\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_backRouteChoseWindow input[id^='back_chose_'][id!='\"+checkName+\"_checkedAll_read']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_backRouteChoseWindow input[id^='back_chose_'][id!='\"+checkName+\"_checkedAll_signature']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_read\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     //jQuery(\"#id_backRouteChoseWindow input[name^='back_chose_'][parent_name!='\"+checkName+\"']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     //jQuery(\"#id_backRouteChoseWindow input[id^='back_chose_'][id!='\"+checkName+\"_checkedAll']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction nextRejectChecked(checkName,flag){\r\n");
      out.write("\t\t     jQuery(\"#id_nextRouteChoseWindow input[parent_name^='next_chose_'][parent_name!='\"+checkName+\"']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     \r\n");
      out.write("\t\t     jQuery(\"#id_nextRouteChoseWindow input[id^='next_chose_'][id!='\"+checkName+\"_checkedAll_actor']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_read\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_nextRouteChoseWindow input[id^='next_chose_'][id!='\"+checkName+\"_checkedAll_read']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_nextRouteChoseWindow input[id^='next_chose_'][id!='\"+checkName+\"_checkedAll_signature']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_read\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction allRejectChecked(checkName){\r\n");
      out.write("\t\t     /* \r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[parent_name^='all_chose_'][parent_name!='\"+checkName+\"']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[id^='all_chose_'][id!='\"+checkName+\"_checkedAll']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     */\r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[parent_name^='all_chose_'][parent_name!='\"+checkName+\"']:checked\").attr(\"checked\",false);\r\n");
      out.write("\t\t     \r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[id^='all_chose_'][id!='\"+checkName+\"_checkedAll_actor']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_read\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[id^='all_chose_'][id!='\"+checkName+\"_checkedAll_read']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_signature\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t     jQuery(\"#id_allRouteChoseWindow input[id^='all_chose_'][id!='\"+checkName+\"_checkedAll_signature']:checked\").each(function(){\r\n");
      out.write("\t\t\t       if(((checkName+\"_checkedAll_actor\") != this.id) && ((checkName+\"_checkedAll_read\") != this.id)){\r\n");
      out.write("\t\t\t\t       this.checked = false;\r\n");
      out.write("\t\t\t\t   }\r\n");
      out.write("\t\t\t });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction doCheckedAll(checked,checkName,parentCheckName,flag) {\r\n");
      out.write("\t\t\tvar checkboxsArr =  document.getElementsByName(checkName);\r\n");
      out.write("            var checkboxs = [];\r\n");
      out.write("            for(var i=0;i<checkboxsArr.length;i++){\r\n");
      out.write("                var checkbox = checkboxsArr[i];\r\n");
      out.write("                if(flag == checkbox.getAttribute(\"flag\")){\r\n");
      out.write("                \tcheckboxs.push(checkbox);\r\n");
      out.write("                } \r\n");
      out.write("            }\r\n");
      out.write("\t\t\tfor(var i=0;i<checkboxs.length;i++)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tcheckboxs[i].checked = checked;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(checkName.indexOf(\"back_chose_\")>-1){\r\n");
      out.write("\t\t\t\tbackRejectChecked(parentCheckName);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(checkName.indexOf(\"next_chose_\")>-1){\r\n");
      out.write("\t\t\t\tnextRejectChecked(parentCheckName,flag);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(checkName.indexOf(\"all_chose_\")>-1){\r\n");
      out.write("\t\t\t\tallRejectChecked(parentCheckName);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction toggleFormDisplay() {\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction toggleAdviseDisplay() {\r\n");
      out.write("\t\t\tgetLazyLoadedObj(\"advise\", jQuery(\"#id_workflowAdviseContainer\"), function(){\r\n");
      out.write("\t\t\t\tif(currentSavedAdvise /*&& !isCompletedTask*/)  {\r\n");
      out.write("\t\t\t    \taddCommonAdivse(currentSavedAdvise);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tvar height = jQuery(window).height() - 145;\r\n");
      out.write("\t\t\t\tjQuery('#id_workflowAdviseContainer').css({height: height < 100 ? 100 : height});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction closeWindow() {\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\t\twindow.opener=null;\r\n");
      out.write("\t\t\t\twindow.open(\"\",\"_self\");\r\n");
      out.write("\t\t\t\twindow.close();\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\twindow.close();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction toggleDisplay() {\r\n");
      out.write("\t\t   var currentOperationImgElement = window.event.srcElement;\r\n");
      out.write("\t\t   var isDisplay = currentOperationImgElement.src.indexOf(\"_b\")==-1;\r\n");
      out.write("\t\t   currentOperationImgElement.src = isDisplay?currentOperationImgElement.src.replace(\"_a\",\"_b\"):currentOperationImgElement.src.replace(\"_b\",\"_a\");\r\n");
      out.write("\t\t   var toggleDisplayElement = currentOperationImgElement.parentNode.parentNode.nextSibling;\r\n");
      out.write("\t\t   toggleDisplayElement.style.display = (isDisplay?\"block\":\"none\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction checkIsSameUser(){\r\n");
      out.write("\t\t\tvar params = {\r\n");
      out.write("\t\t\t\tjhiCompletedTaskImplId:'");
      if (_jspx_meth_mini_005fparam_005f3(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("\t\t\t\thistoryProcessInstanceImplDbid:'");
      if (_jspx_meth_mini_005fparam_005f4(_jspx_page_context))
        return;
      out.write("',\r\n");
      out.write("\t\t\t\tjbpmWorkflowHistoryInfoUserId:'");
      if (_jspx_meth_mini_005fparam_005f5(_jspx_page_context))
        return;
      out.write("'\r\n");
      out.write("\t\t\t};\r\n");
      out.write("\t\t\t var submitForm = document.getElementById(\"id_submitProcessedForm\");\r\n");
      out.write("\t        var formElements = submitForm.elements;\r\n");
      out.write("\t        for (var i = 0; i < formElements.length; i++) {\r\n");
      out.write("\t        \tvar formElement = formElements[i];\r\n");
      out.write("\t        \tvar inputName   = formElement.name;\r\n");
      out.write("\t        \tvar inputValue  = formElement.value;\r\n");
      out.write("\t\t        if(inputName){\r\n");
      out.write("\t\t        \tparams[inputName] = inputValue;\r\n");
      out.write("\t\t\t    }\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t        var flag = false;\r\n");
      out.write("\t\t\tajaxRequest({\r\n");
      out.write("\t\t\t\turl:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/jbpm/getNextWorkflowInfo.action\",\r\n");
      out.write("\t\t\t\tasync:false, \r\n");
      out.write("\t\t\t\tsuccess:function(res){\r\n");
      out.write("\t\t\t\t\tvar html = (res.responseText||\"\").replace(/(\\r|\\n)/gi,\"\");\r\n");
      out.write("\t\t\t\t\tif(html == \"true\"){\r\n");
      out.write("\t\t\t\t\t\tflag =  true;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tfailure:function(res){\r\n");
      out.write("\t\t\t\t\tflag =  false;\r\n");
      out.write("\t\t\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\ttimeout:30*60*1000,\r\n");
      out.write("\t\t\t\tparams:params\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t    });\r\n");
      out.write("\t\t\treturn flag;\r\n");
      out.write("\t}\r\n");
      out.write("   </script>");
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
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(39,28) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f0.setName("currentJbpmWorkflowHistoryInfoId");
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
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f1 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f1.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f1.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(40,36) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f1.setName("currentProcessInstanceDBID");
    int _jspx_eval_mini_005fparam_005f1 = _jspx_th_mini_005fparam_005f1.doStartTag();
    if (_jspx_th_mini_005fparam_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f1);
    return false;
  }

  private boolean _jspx_meth_mini_005fparam_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  mini:param
    com.tenwa.leasing.tag.GetParameter _jspx_th_mini_005fparam_005f2 = (com.tenwa.leasing.tag.GetParameter) _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.get(com.tenwa.leasing.tag.GetParameter.class);
    _jspx_th_mini_005fparam_005f2.setPageContext(_jspx_page_context);
    _jspx_th_mini_005fparam_005f2.setParent(null);
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(41,35) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f2.setName("jbpmWorkflowHistoryInfoUserId");
    int _jspx_eval_mini_005fparam_005f2 = _jspx_th_mini_005fparam_005f2.doStartTag();
    if (_jspx_th_mini_005fparam_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f2);
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
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(220,28) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f3.setName("currentJbpmWorkflowHistoryInfoId");
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
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(221,36) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f4.setName("currentProcessInstanceDBID");
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
    // /WEB-INF/jsps/solutions/workflow/jbpm-core/getWorkflowCommonFunc.jsp(222,35) name = name type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_mini_005fparam_005f5.setName("jbpmWorkflowHistoryInfoUserId");
    int _jspx_eval_mini_005fparam_005f5 = _jspx_th_mini_005fparam_005f5.doStartTag();
    if (_jspx_th_mini_005fparam_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fmini_005fparam_0026_005fname_005fnobody.reuse(_jspx_th_mini_005fparam_005f5);
    return false;
  }
}