/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:13:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.regulating_005fbreathing_005fbefore.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class todo_005fregulating_005fof_005fbreathing_005fnew_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t\tid:\"todo_regulating_of_breathing\",\r\n");
      out.write("\t\t\trenderTo:\"id_todo_regulating_of_breathing\",\r\n");
      out.write("\t\t\twidth:globalClientWidth - 50,\r\n");
      out.write("\t\t\theight:600,\r\n");
      out.write("\t\t\tlazyLoad:false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper :false,\r\n");
      out.write("\t\t\tshowPager:true,\r\n");
      out.write("\t\t\tpageSize : 500,//每页显示几条数据\r\n");
      out.write("\t\t\tshowToolbar:showTools,\r\n");
      out.write("\t\t\tmultiSelect:true,\r\n");
      out.write("\t\t\txmlFileName:'/eleasing/workflow/rent/regulating_breathing_before/todo_regulating_breathing_list_new.xml',\r\n");
      out.write("\t\t\ttools :[\r\n");
      out.write("\t\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\t\thtml:'调息',\r\n");
      out.write("\t\t\t\t\t\t\tplain:true,\r\n");
      out.write("\t\t\t\t\t\t\ticonCls:'icon-addfolder',\r\n");
      out.write("\t\t\t\t\t\t\thandler:function(miniTable, buttonText) {\r\n");
      out.write("\t\t\t\t\t\t\t\tvar rowDatas = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\t        \t\t if(rowDatas.length == 0){\r\n");
      out.write("\t\t\t\t        \t\t\t jQuery.messager.alert('错误提示',\"<div style='font-size:18px;line-height:30px;'>请选择需要调息的合同</div>\",'error');\r\n");
      out.write("\t\t\t\t        \t\t }else {\r\n");
      out.write("\t\t\t\t        \t\t\t todoTX(rowDatas);\r\n");
      out.write("\t\t\t\t        \t\t }\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}],\r\n");
      out.write("\t\t\tparams:{adjustid:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['fund_standard_interest.id'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",docid:flowUnid},\r\n");
      out.write("\t\t    columns:[\r\n");
      out.write("\t\t\t\t\t{type:'indexcolumn'}, \r\n");
      out.write("\t\t\t\t\t{type:'checkcolumn'},\r\n");
      out.write("\t\t\t\t\t{header:'id',field:'id', visible:false},\r\n");
      out.write("\t\t\t\t\t{header:'proj_id',field:'projid', visible:false},\r\n");
      out.write("\t\t\t\t\t{header:'custid',field:'custid', visible:false},\r\n");
      out.write("\t\t\t\t\t{header:'docid',field:'docid', visible:false},\r\n");
      out.write("\t\t\t\t\t{header:'合同号',field:'contractid',width:100,queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t{header:'业务合同号',field:'contractnumber',width:150,queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t{header:'客户名',field:'custname',width:200,queryConfig:{isNewLine:true}},\r\n");
      out.write("\t\t\t\t\t{header:'设备款',field:'equipamt',queryConfig:{}},\r\n");
      out.write("\t\t\t\t\t{header:'首付款',field:'firstpayment'},\r\n");
      out.write("\t\t\t\t\t{header:'还租次数',field:'incomenumber'},\r\n");
      out.write("\t\t\t\t\t{header:'付租类型',field:'incomenumberyear'},\r\n");
      out.write("\t\t\t\t\t{header:'付租方式',field:'periodtype'},\r\n");
      out.write("\t\t\t\t\t{header:'起租日期',field:'startdate'},\r\n");
      out.write("\t\t\t\t\t{header:'利率',field:'yearrate',visible : false},\r\n");
      out.write("\t\t\t\t\t{header:'内部收益率',field:'irr'},\r\n");
      out.write("\t\t\t\t\t{header:'利率浮动类型',field:'ratefloattypename'},\r\n");
      out.write("\t\t\t\t\t{header:'调息生效节点',field:'adjuststylename'},\r\n");
      out.write("\t\t            {header:'操作',align:'center',width:120,\r\n");
      out.write("\t\t\t\t\t\t     renderer:function(e){\r\n");
      out.write("\t\t\t\t\t\t             var id = e.record.id;\r\n");
      out.write("\t\t                             return \"<a href='javascript:void(0);' onclick='viewShowCondition(\\\"\"+id+\"\\\")'>当前商务条件查看</a>\";}}\r\n");
      out.write("\t        ]\r\n");
      out.write("\t\t});});});\t\r\n");
      out.write("//查看商务条件\r\n");
      out.write("function viewShowCondition(contractId){\r\n");
      out.write("\tvar adjusttype = $(\"#adjusttype\").val();//根据adjusttype确定租金计划从哪张表获取\r\n");
      out.write("\tvar URL = getRootPath()+\"/leasing/selectContractCondition.action?contractid=\"+contractId+\"&adjusttype=\"+adjusttype;\r\n");
      out.write("    openFullScreenWindow(URL);\r\n");
      out.write("}\r\n");
      out.write("var isContinue=true;//控制循环是否继续\r\n");
      out.write("var c;\r\n");
      out.write("//总循环控制调息\r\n");
      out.write("function todoTX(rowDatas){\r\n");
      out.write("\tif(confirm(\"确认对选中合同进行调息?\")){\r\n");
      out.write("\t\tisContinue=true;\r\n");
      out.write("\t\tc=rowDatas.length;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tjQuery(\"#msgButtonText\").html(\"停止请求\");//设置控制按钮\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tshwoMessageWindwos();//显示操作信息层\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfor(var i=0;i<rowDatas.length&&isContinue;i++){\r\n");
      out.write("\t\t\tshowMsg(rowDatas[i][\"contractnumber\"]+\"开始请求调息!\");\r\n");
      out.write("\t\t\tdoSendTX(rowDatas[i][\"id\"],rowDatas[i][\"contractnumber\"]);//调用调息ajax\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//显示操作信息层\r\n");
      out.write("function shwoMessageWindwos(){\r\n");
      out.write("\tmini.get(\"id_station_message\").show();\r\n");
      out.write("    mini.unmask(document.body);\r\n");
      out.write("    \r\n");
      out.write("}\r\n");
      out.write("function msgButton(){\r\n");
      out.write("\tif(isContinue){\r\n");
      out.write("\t\tif(confirm(\"确认中断调息吗?\")){\r\n");
      out.write("\t\t\tisContinue=false;\r\n");
      out.write("\t\t\tshowMsg(\"调息被人为中断!\");\r\n");
      out.write("\t\t\tjQuery(\"#msgButtonText\").html(\"关闭\");//设置控制按钮\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t//把信息写入富文本域\r\n");
      out.write("\t\tvar msg=jQuery(\"#msg_memo\").html();\r\n");
      out.write("\t\twhile(msg.indexOf(\"<br>\")>=0){\r\n");
      out.write("\t\t\tmsg=msg.replace(\"<br>\",\"\\n\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tjQuery(\"#tx_text_memo\").val(jQuery(\"#tx_text_memo\").val()+msg);\r\n");
      out.write("\t\tjQuery(\"#msg_memo\").html(\"\");\r\n");
      out.write("\t\t//重新加载列表\r\n");
      out.write("\t\tmini.get(\"todo_regulating_of_breathing\").load();\r\n");
      out.write("\t\tmini.get(\"current_regulating_of_breathing\").load();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tmini.get(\"id_station_message\").hide();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("//往控制台显示消息\r\n");
      out.write("function showMsg(msg){\r\n");
      out.write("\tmsg=\"-------------------------------------------------------------------------<br>\"+msg+\"<br>\";\r\n");
      out.write("\tjQuery(\"#msg_memo\").html(jQuery(\"#msg_memo\").html()+msg);\r\n");
      out.write("}\r\n");
      out.write("//发contract——id 到后台调用调息函数\r\n");
      out.write("function doSendTX(cid,contractNumber){\r\n");
      out.write("\tvar adjusttype = $(\"#adjusttype\").val();\r\n");
      out.write("\tajaxRequest({\r\n");
      out.write("\t     url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/acl/addAdjustinterest.acl\",\r\n");
      out.write("\t     params:{adjustid:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['fund_standard_interest.id'] }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",docid:flowUnid,contractid:cid,adjusttype:adjusttype},\r\n");
      out.write("\t     timeout:30*1000,\r\n");
      out.write("\t     async:true,//改为同步请求\r\n");
      out.write("\t     success:function(res){\r\n");
      out.write("\t \t\tres=res.responseText;\r\n");
      out.write("\t \t\tres=res.replace(/(^\\s+)|(\\s+$)/g, \"\"); \r\n");
      out.write("\t \t\tshowMsg(contractNumber+\"调息请求成功!反馈如下[\"+res+\"]\");\r\n");
      out.write("\t \t\ttxBackFun(cid);\r\n");
      out.write("\t     },\r\n");
      out.write("\t     failure:function(res){\r\n");
      out.write("\t    \tres=res.responseText;\r\n");
      out.write("\t \t\tres=res.replace(/(^\\s+)|(\\s+$)/g, \"\");\r\n");
      out.write("\t \t\tshowMsg(\"<strong id='red'>\"+contractNumber+\"调息请求失败!反馈如下[\"+res+\"]</strong>\");\r\n");
      out.write("\t \t\ttxBackFun();\r\n");
      out.write("\t     }\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function txBackFun(id){\r\n");
      out.write("\tc--;\r\n");
      out.write("\tif(c<=0){\r\n");
      out.write("\t\tisContinue=false;\r\n");
      out.write("\t\tjQuery(\"#msgButtonText\").html(\"关闭\");//设置控制按钮\r\n");
      out.write("\t}\r\n");
      out.write("\tif(id){\r\n");
      out.write("\t\tjQuery(\"#id_adjust_contractids\").val(jQuery(\"#id_adjust_contractids\").val()+(jQuery(\"#id_adjust_contractids\").val()==\"\"?\"\":\",\")+id);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_todo_regulating_of_breathing\"></div>");
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
