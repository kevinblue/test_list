/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-03-13 05:51:53 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fdevelop_005flist.proj_005fwind_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005faccessories_005freport_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("var filekey = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isShow}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tvar tools= [{\r\n");
      out.write("\t\thtml : '上传',\r\n");
      out.write("\t\tplain : true,\r\n");
      out.write("\t\ticonCls : 'icon-add',\r\n");
      out.write("\t\thandler : function(miniTable, buttonText) {uploadFile();}\r\n");
      out.write("\t},'-','remove'];\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"proj_accessories_report_list\",\r\n");
      out.write("\t\trenderTo: \"id_table_proj_accessories_report_list\",\r\n");
      out.write("\t\twidth: '100%',\r\n");
      out.write("\t\theight: 200,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: true,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: tools,\r\n");
      out.write("\t\tremoveOperCallBack: function(miniTable,rowDatas){\r\n");
      out.write("\t\t\tdropCreateFile(rowDatas);\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}, \r\n");
      out.write("\t\txmlFileName : '/eleasing/workflow/proj/proj_wind/proj_accessories_report_list.xml',\r\n");
      out.write("\t\tparams : {\r\n");
      out.write("\t\t filekey:filekey,\r\n");
      out.write("\t\t modelname : '项目建设相关附件'\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn',width:9},\r\n");
      out.write("\t\t\t{type:'checkcolumn',width:12},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'filename', header: '文件名称'},\r\n");
      out.write("\t\t\t{field: 'createdate', header: '上传时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},\r\n");
      out.write("\t\t\t{field: 'realname', header: '上传人'},\r\n");
      out.write("\t\t\t{field: 'create', header: '操作',renderer: showOperator}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("  function downloadFile(Id) {\r\n");
      out.write("\t  \r\n");
      out.write("\t\r\n");
      out.write("\tattachmentDown({returnType:'file',url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/template/downLoadAttachById.action?id=\"+Id});\r\n");
      out.write("   \r\n");
      out.write("  }   \r\n");
      out.write("function showOperator(e){\r\n");
      out.write("\tvar id = e.record.id;\r\n");
      out.write("\tvar base = \"<a href='javascript:void(0);' onclick='showRecorder(\\\"\" + id + \"\\\")'>记录</a>\";\r\n");
      out.write("\tvar base2 = \"<a href='javascript:void(0);' onclick='downloadFile(\\\"\" + id + \"\\\")'>下载</a>\";\r\n");
      out.write("\tvar base3 = \"<a href='javascript:void(0);' onclick='editoverdue(\\\"\" + id + \"\\\")'>编辑</a>\";\r\n");
      out.write("\tvar base4 = \"<a href='javascript:void(0);' onclick='readCreateWord(\\\"\" + id + \"\\\")'>查看</a>\";\r\n");
      out.write("\tvar base5 = \"<a href='javascript:void(0);' onclick='printCreateWord(\\\"\" + id + \"\\\")'>打印</a>\";\r\n");
      out.write("\tvar tempright=\"2\" \r\n");
      out.write("\tvar resStr =base2;\r\n");
      out.write("\treturn resStr;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function  readCreateWord(id){\r\n");
      out.write("\tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write(" \tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write(" \topenFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:\"1\"});\r\n");
      out.write(" \t\r\n");
      out.write("}\r\n");
      out.write("function  printCreateWord(id){\r\n");
      out.write("\tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write(" \tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write(" \topenFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:\"3\"});\r\n");
      out.write(" \t\r\n");
      out.write("}\r\n");
      out.write("function editoverdue(id){\r\n");
      out.write("\tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write("\tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write("\topenFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:\"2\"});\r\n");
      out.write("\t//window.open (getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi?id='+id,'newwindow',\"resizable=true,top=0,left=0\") ;\r\n");
      out.write("}\r\n");
      out.write("function showContractFile(value,tableObj,columnName,columnIndex,rowData){\r\n");
      out.write("\tvar base = \"<a href='#' onclick='downloadContractWordUploadFile(\\\"\"+$(\"#projid\").val()+\"/\"+rowData.wordaddress+\"\\\",\\\"\"+rowData.wordtemplatename+\"\\\")'>{1}</a>{2}\";\r\n");
      out.write("\tvar separator = \"&nbsp;&nbsp;\";\r\n");
      out.write("\tvar updateFlag=value;\r\n");
      out.write("\tvar updateClickFunc=\"other\";\r\n");
      out.write("\tvar field=base.replace(\"{1}\",updateFlag).replace(\"{2}\",separator);\r\n");
      out.write("\treturn field;\r\n");
      out.write("}\r\n");
      out.write("function downloadContractWordUploadFile(fileAddress,wordName) {\r\n");
      out.write("\t  mini.mask({el: document.body,cls: 'mini-mask-loading',html: '操作进行中 请稍等...'});\r\n");
      out.write("\tvar newAction = getRootPath()+\"/leasing/word/downLoadContractAttach.action?browserType=\"+SysBrowser.getBrowser().toLowerCase();\r\n");
      out.write("\tnewAction+=\"&fileAddress=\"+fileAddress+\"&wordName=\"+wordName;\r\n");
      out.write("\tuploadAttachmentFileFormSubmit(newAction);\r\n");
      out.write("\tmini.unmask(document.body);\r\n");
      out.write("}\r\n");
      out.write("//删除生成的文件。把文件注为无效    \r\n");
      out.write(" function dropCreateFile(rowDatas){\r\n");
      out.write("\tvar plandata = rowDatas;\r\n");
      out.write("\tvar ids=[];\r\n");
      out.write("\tfor(var i=0;i<plandata.length;i++){\r\n");
      out.write("\t\tids.push(plandata[i].id);\r\n");
      out.write("\t}\r\n");
      out.write("    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除尽调报告 请稍等...'});\r\n");
      out.write("\tvar url=\"/leasing/template/dropCreateFile.action\";\r\n");
      out.write("/* \tvar param=[];\r\n");
      out.write("\tparam[\"ids\"]=ids+\"\";\r\n");
      out.write("\tparam[\"isAttachment\"]=false;//连带级联操作删除自定义方法 */\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("\t\turl:getRootPath()+url,\r\n");
      out.write("\t      type: \"post\",\r\n");
      out.write("\t      cache: false, \r\n");
      out.write("\t      data :{\"ids\":ids+\"\",\"isAttachment\":false},\r\n");
      out.write("\t      async : false,\r\n");
      out.write("\t\tsuccess:function(rs){\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t/* var message= rs.responseText;\r\n");
      out.write("\t\t\tmessage=message.replace(/(^\\s+)|(\\s+$)/g, \"\"); \r\n");
      out.write("\t\t\tmini.unmask(document.body); */\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tasync:false,\r\n");
      out.write("\t\tfailure:function(res){\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t},\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("/*处理上传的模板*/\r\n");
      out.write("function uploadFile() {\r\n");
      out.write("\tvar uploadutil = new uploadUtil({\r\n");
      out.write("\t\t\turl : '/leasing/file/uploadFile.action',\r\n");
      out.write("\t\t\ttableid : \"contract_word\",\r\n");
      out.write("\t\t\tmodelname : '项目建设相关附件',\r\n");
      out.write("\t\t\tparames : {\r\n");
      out.write("\t\t\t\t/* flowUnid : flowUnid, */\r\n");
      out.write("\t\t\t\tfilekey : filekey　　　//项目ＩＤ\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tjscallback : 'reloadcustcontactfile'\r\n");
      out.write("\t\t});\r\n");
      out.write("\tuploadutil.createFileAndShow();\r\n");
      out.write("}\r\n");
      out.write("function reloadcustcontactfile(message){\r\n");
      out.write("\tmini.alert(message);\r\n");
      out.write("\tmini.get(\"id_uploadfile\").hide();\r\n");
      out.write("\tmini.unmask(document.body);\r\n");
      out.write("\tmini.get(\"proj_accessories_report_list\").reload();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_proj_accessories_report_list\"></div>");
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
