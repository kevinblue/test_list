/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-04-01 06:57:19 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.regulating_005fbreathing.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class current_005fregulating_005fof_005fbreathing_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script>\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"current_regulating_of_breathing\",\r\n");
      out.write("\t\t\trenderTo: \"id_current_regulating_of_breathing\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 50,\r\n");
      out.write("\t\t\theight: 360,\r\n");
      out.write("\t\t\tlazyLoad: false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: true,\r\n");
      out.write("\t\t\tpageSize : 500,//每页显示几条数据\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\tmultiSelect: true,\r\n");
      out.write("\t\t\ttitle:'本次调息',\r\n");
      out.write("\t\t\txmlFileName:'/eleasing/workflow/rent/regulating_breathing/current_regulating_breathing_list_new.xml',\r\n");
      out.write("\t\t\tparams:{ adjustid:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['fund_standard_interest.id'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",docid:flowUnid,Central_Bank_id:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty Central_Bank_id ? \"[]\" : Central_Bank_id }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'},\r\n");
      out.write("\t\t\ttools : [\r\n");
      out.write("\t\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\t\thtml : '撤销',\r\n");
      out.write("\t\t\t\t\t\t\t\tplain : true,\r\n");
      out.write("\t\t\t\t\t\t\t\ticonCls : 'icon-addfolder',\r\n");
      out.write("\t\t\t\t\t\t\t\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar rowDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\t\t        \t\t if(rowDatas.length == 0){\r\n");
      out.write("\t\t\t\t\t        \t\t\t jQuery.messager.alert('错误提示',\"<div style='font-size:18px;line-height:30px;'>请选择需要调息的合同</div>\",'error');\r\n");
      out.write("\t\t\t\t\t        \t\t }else {\r\n");
      out.write("\t\t\t\t\t        \t\t\t revocationTX(rowDatas);\r\n");
      out.write("\t\t\t\t\t        \t\t }\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t}         \r\n");
      out.write("\t\t\t\t\t\t],\r\n");
      out.write("\t\t     columns:[\r\n");
      out.write("\t\t\t\t\t\t{type:'indexcolumn'}, \r\n");
      out.write("\t\t\t\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t\t\t\t{header:'id',field:'id', visible:false},\r\n");
      out.write("\t\t\t\t\t\t{header:'faictid',field:'faictid', visible:false},\r\n");
      out.write("\t\t\t\t\t\t{header:'proj_id',field:'proj_id', visible:false},\r\n");
      out.write("\t\t\t\t\t\t{header:'custid',field:'custid', visible:false},\r\n");
      out.write("\t\t\t\t\t\t{header:'docid',field:'docid', visible:false},\r\n");
      out.write("\t\t\t\t\t\t{header:'合同号',field:'contractid',queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t\t{header:'业务合同号',field:'contractnumber',queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t\t{header:'客户名',field:'custname',queryConfig:{}},\t\t\r\n");
      out.write("\t\t\t\t\t\t{header:'设备款',field:'equipamt',queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t\t{header:'首付款',field:'firstpayment'},\r\n");
      out.write("\t\t\t\t\t\t{header:'利率浮动类型',field:'ratefloattypename'},\r\n");
      out.write("\t\t\t\t\t\t{header:'调息生效节点',field:'adjuststylename'},\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t{header:'还租次数',field:'incomenumber'},\r\n");
      out.write("\t\t\t\t\t\t{header:'付租类型',field:'incomenumberyear'},\r\n");
      out.write("\t\t\t\t\t\t{header:'付租方式',field:'periodtype'},\r\n");
      out.write("\t\t\t\t\t\t{header:'起租日期',field:'startdate'},\r\n");
      out.write("\t\t\t\t\t\t/* {header:'利率',field:'yearrate'}, */\r\n");
      out.write("\t\t\t\t\t\t{header:'调息前利率',field:'rateoriginal'},\r\n");
      out.write("\t\t\t\t\t\t{header:'调息后利率',field:'rateadjust'},\r\n");
      out.write("\t\t\t\t\t\t{header:'调息前内部收益率',field:'oldirr'},\r\n");
      out.write("\t\t\t\t\t\t{header:'调息后内部收益率',field:'newirr'},\r\n");
      out.write("\t\t\t\t\t    {header:'操作',align:'center',width:120,\r\n");
      out.write("\t\t\t\t\t\t\t\t\trenderer:function(e){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tvar  table = e.record;\r\n");
      out.write("\t\t\t\t\t                return \"<a href='#' onclick='previewhis(\"+JsonUtil.encode(table)+\")'>对比</a>\";}}\r\n");
      out.write("\r\n");
      out.write("\t        ]\r\n");
      out.write("\t\t});});\t});\r\n");
      out.write("function previewhis(data){\r\n");
      out.write(" \r\n");
      out.write("   var URL = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/query/condition_temp/interestTempCompare.bi\";\r\n");
      out.write("   \r\n");
      out.write("   var params = {cid:data.id,contractid:data.contractid,docid:data.docid,read_only :false};\r\n");
      out.write("   openFullScreenWindow(URL,params);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//发Ajax到后台调用调息撤销acl\r\n");
      out.write("function revocationTX(rowDatas){\r\n");
      out.write("\tif(confirm(\"确认对选中合同进行撤销?\")){\r\n");
      out.write("\t\tisContinue=true;\r\n");
      out.write("\t\tc=rowDatas.length;\r\n");
      out.write("\t\tjQuery(\"#msgButtonText\").html(\"停止请求\");//设置控制按钮\r\n");
      out.write("\t\tshwoMessageWindwos();//显示操作信息层\r\n");
      out.write("\t\tfor(var i=0;i<rowDatas.length&&isContinue;i++){\r\n");
      out.write("\t\t\tshowMsg(rowDatas[i][\"contractnumber\"]+\"开始请求撤销调息!\");\r\n");
      out.write("\t\t\tdoSendTXCX(rowDatas[i][\"id\"],rowDatas[i][\"contractnumber\"]);//调用调息ajax\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function doSendTXCX(cid,contractNumber){\r\n");
      out.write("\tajaxRequest({\r\n");
      out.write("\t     url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/acl/removeAdjustinterest.acl\",\r\n");
      out.write("\t     params:{adjustid:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['fund_standard_interest.id'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",docid:flowUnid,contractid:cid},\r\n");
      out.write("\t     timeout:30*1000,\r\n");
      out.write("\t     async:true,//改为同步请求\r\n");
      out.write("\t     success:function(res){\r\n");
      out.write("\t \t\tres=res.responseText;\r\n");
      out.write("\t \t\tres=res.replace(/(^\\s+)|(\\s+$)/g, \"\"); \r\n");
      out.write("\t \t\tshowMsg(contractNumber+\"撤销调息请求成功!反馈如下[\"+res+\"]\");\r\n");
      out.write("\t \t\ttxcxBackFun(cid);\r\n");
      out.write("\t     },\r\n");
      out.write("\t     failure:function(res){\r\n");
      out.write("\t    \tres=res.responseText;\r\n");
      out.write("\t \t\tres=res.replace(/(^\\s+)|(\\s+$)/g, \"\");\r\n");
      out.write("\t \t\tshowMsg(\"<strong id='red'>\"+contractNumber+\"撤销调息请求失败!反馈如下[\"+res+\"]</strong>\");\r\n");
      out.write("\t \t\ttxcxBackFun(cid);\r\n");
      out.write("\t  \t }\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function txcxBackFun(id){\r\n");
      out.write("\tc--;\r\n");
      out.write("\tif(c<=0){\r\n");
      out.write("\t\tisContinue=false;\r\n");
      out.write("\t\tjQuery(\"#msgButtonText\").html(\"关闭\");//设置控制按钮\r\n");
      out.write("\t}\r\n");
      out.write("\tif(id){\r\n");
      out.write("\t\tvar ids=jQuery(\"#id_adjust_contractids\").val();\r\n");
      out.write("\t\tids=ids.replace(id,\"\").replace(\",,\",\",\");\r\n");
      out.write("\t\tif(ids.indexOf(\",\")==0){\r\n");
      out.write("\t\t\tids=ids.substring(1);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(ids.indexOf(\",\")==ids.length-1){\r\n");
      out.write("\t\t\tids=ids.substring(0,ids.length-1);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tjQuery(\"#id_adjust_contractids\").val(ids);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_current_regulating_of_breathing\"></div>");
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
