/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:28:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.contract.contract_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class contract_005fword_005fsign_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyUtils.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyAjax.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyLoadMask.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("seajs.use([\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/init.js\"]);\r\n");
      out.write("var conid = \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\";\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\t\t    seajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\t\tnew ApTable({\r\n");
      out.write("\t\t\t\tid: \"id_tasks_table1\",\r\n");
      out.write("\t\t\t\trenderTo: \"id_tasksContainer2\",\r\n");
      out.write("\t\t\t\twidth: '100%',\r\n");
      out.write("\t\t\t\theight: '100%',\r\n");
      out.write("\t\t\t\theight: 360,\r\n");
      out.write("\t\t\t\tshowPager : true,\r\n");
      out.write("\t\t\t\tlazyLoad : false,\r\n");
      out.write("\t\t\t\ttitle:'合同编号设置',\r\n");
      out.write("\t\t\t\tremoteOper : true,\r\n");
      out.write("\t\t\t\tentityClassName : \"com.tenwa.leasing.entity.proj.ContractNumberSetting\",\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tparams:{\r\n");
      out.write("\t\t\t\t\tflowunid:flowUnid,\r\n");
      out.write("\t\t\t\t\tconid:conid\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tshowToolbar: true,\r\n");
      out.write("\t\t\t\ttools: ['edit'],\r\n");
      out.write("\t\t\t\tmultiSelect: false,\r\n");
      out.write("\t\t\t\txmlFileName : '/eleasing/workflow/contract/contract_sign/contract_sign_number_setting.xml',\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tqueryButtonColSpan : 6,\r\n");
      out.write("\t\t\t\tqueryButtonNewLine : true,\r\n");
      out.write("\t\t\t\tcolumns: [\r\n");
      out.write("                 { type : 'checkcolumn'},\r\n");
      out.write("\t\t\t\t\t{ header:'序号',type : 'indexcolumn'},\r\n");
      out.write("\t\t\t\t\t{field:'id',\r\n");
      out.write("\t\t\t\t\t\theader:'主键',\r\n");
      out.write("\t\t\t\t\t\tvisible: false,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{field:'contractname',\r\n");
      out.write("\t\t\t\t\t\theader:'合同名称',\r\n");
      out.write("\t\t\t\t\t\tvisible: true,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t required:true,\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{field:'contractnumber',\r\n");
      out.write("\t\t\t\t\t\theader:'合同编号',\r\n");
      out.write("\t\t\t\t\t\tvisible: true,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\t required:true,\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{field:'oldcontnum',\r\n");
      out.write("\t\t\t\t\t\theader:'修改前合同编号',\r\n");
      out.write("\t\t\t\t\t\tvisible: false,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{field:'docid',\r\n");
      out.write("\t\t\t\t\t\theader:'流程ID',\r\n");
      out.write("\t\t\t\t\t\tvisible: false,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\tvalue:flowUnid,\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{field:'contractsubject',\r\n");
      out.write("\t\t\t\t\t\theader:'合同主体',\r\n");
      out.write("\t\t\t\t\t\tvisible: true,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t\tvalueField:'value'\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'contracttypename',\r\n");
      out.write("\t\t\t\t\t\tvisible:true,\r\n");
      out.write("\t\t\t\t\t\theader:'合同类型',\r\n");
      out.write("\t\t   \t\t         formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t             }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'fileaddress',\r\n");
      out.write("\t\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t\theader:'文件地址',\r\n");
      out.write("\t\t   \t\t         formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t             },\r\n");
      out.write("\t\t\t\t\t    renderer:showUploadFilesList\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'contractperson',\r\n");
      out.write("\t\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t\theader:'签约人',\r\n");
      out.write("\t\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t          newLine:true,\r\n");
      out.write("\t\t\t\t\t            width:\"100%\",\r\n");
      out.write("\t\t\t\t\t             type:'queryinput',\r\n");
      out.write("\t\t\t\t\t         required:true,\r\n");
      out.write("\t\t\t\t\t        textField:'realname',\r\n");
      out.write("\t\t\t\t\t       valueField:'id',\r\n");
      out.write("\t\t\t\t\t       allowInput:false,\r\n");
      out.write("\t\t\t\t\t     fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t            width:'100%',\r\n");
      out.write("\t\t\t\t\t          colspan:3,\r\n");
      out.write("\t\t\t\t\t           params:{xmlFileName:'/test/user.xml'}}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'contractpersonname',\r\n");
      out.write("\t\t\t\t\t\tvisible:true,\r\n");
      out.write("\t\t\t\t\t\theader:'签约人',\r\n");
      out.write("\t\t\t\t\t\t formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t     fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t        }},\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'signingtime',\r\n");
      out.write("\t\t\t\t\t\tvisible:true,\r\n");
      out.write("\t\t\t\t\t\theader:'签约时间',\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t                  type:'date',\r\n");
      out.write("\t\t\t                 vtype:'date',\r\n");
      out.write("\t\t\t                format:'yyyy-MM-dd',\r\n");
      out.write("\t\t\t                newLine:true,\r\n");
      out.write("\t\t\t            allowInput:false}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'contractingplace',\r\n");
      out.write("\t\t\t\t\t\tvisible:true,\r\n");
      out.write("\t\t\t\t\t\theader:'签约地点',\r\n");
      out.write("\t\t   \t\t         formEditorConfig:{\r\n");
      out.write("\t\t   \t\t        \tnewLine:true,\r\n");
      out.write("\t\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t\t         type:'textarea'\r\n");
      out.write("\t\t\t\t\t             }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'fileid',\r\n");
      out.write("\t\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t\theader:'文件id',\r\n");
      out.write("\t\t   \t\t         formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t         fieldVisible:false,\r\n");
      out.write("\t\t\t\t\t             }\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield:'contracttype',\r\n");
      out.write("\t\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t\theader:'合同类型',\r\n");
      out.write("\t\t   \t\t         formEditorConfig:{\r\n");
      out.write("\t\t   \t\t\t              newLine:true,\r\n");
      out.write("\t\t\t\t\t             required:true,\r\n");
      out.write("\t\t\t\t\t          multiSelect:false, \r\n");
      out.write("\t\t\t\t\t            textField:'name',\r\n");
      out.write("\t\t\t\t\t           valueField:'value',\r\n");
      out.write("\t\t\t\t\t           showNullItem:\"true\",\r\n");
      out.write("\t\t\t\t\t           nullItemText:\"\",\r\n");
      out.write("\t\t\t\t\t         fieldVisible:false}},\r\n");
      out.write("\t\t\t\t\t         {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfield : 'mark',\r\n");
      out.write("\t\t\t\t\t\t\t\t\theader : '备注',\r\n");
      out.write("\t\t\t\t\t\t\t\t\theaderAlign : 'center',width: 150,\r\n");
      out.write("\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\twidth : 495,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tcolspan : 6,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\theight : 55,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\ttype : 'textarea',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfieldVisible:false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t} ,\r\n");
      out.write("\t\t         {field: 'filename', header: '上传文件信息',width: '30%',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tfieldVisible:false\r\n");
      out.write("\t\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t     \trenderer: function(e){\r\n");
      out.write("\t\t\t\t\t\t\t\treturn getPaydownload(e);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t            },\r\n");
      out.write("\t\t\t     {\r\n");
      out.write("\t\t\t\t\tfield: 'create', \r\n");
      out.write("\t\t\t\t\theader: '操作',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:{ fieldVisible:false},\r\n");
      out.write("\t\t\t\t    renderer:function(e){\r\n");
      out.write("\t\t\t\t    \tvar id = e.record.id;\r\n");
      out.write("\t\t\t\t\t\treturn \"<a href='javascript:void(0);' onclick='upFile(\\\"\" + id + \"\\\")'>上传附件</a>\";    \t\r\n");
      out.write("\t\t\t\t}}\r\n");
      out.write("\t\t\t\t] \r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("function getPaydownload(e){\r\n");
      out.write("\t//debugger;\r\n");
      out.write("\t  var fileHtmlTd = '';\r\n");
      out.write("\t  //文件id\r\n");
      out.write("\t var idStr = e.record.fileid;\r\n");
      out.write("\tif(idStr == null || idStr == undefined || idStr == ''){\r\n");
      out.write("\t\treturn \"还未上传附件！\";\r\n");
      out.write("\t}\r\n");
      out.write("\t var idArray = idStr.split(\",\");\r\n");
      out.write("\t//文件名\r\n");
      out.write("     var filenameStr = e.record.filename;\r\n");
      out.write("      var filenameArray = filenameStr.split(\",\");\r\n");
      out.write("    //上传时间\r\n");
      out.write("    //debugger;\r\n");
      out.write("    var createdateStr = e.record.createdate;\r\n");
      out.write("    var createdateArray;\r\n");
      out.write("    if(typeof(createdateStr)== 'object'){\r\n");
      out.write("    \tvar date=tenwa.toDate(createdateStr+\"\",\"yyyy-MM-dd hh:mm:ss\");\r\n");
      out.write("    \tvar ss=tenwa.toChar(date,\"yyyy-MM-dd hh:mm:ss\");\r\n");
      out.write("    \tcreatedateArray = ss.split(\",\");\r\n");
      out.write("    }else{\r\n");
      out.write("\t    createdateArray = createdateStr.split(\",\");\r\n");
      out.write("    }\r\n");
      out.write("    //上传人\r\n");
      out.write("    var realnameStr = e.record.realname;\r\n");
      out.write("    var realnameArray = realnameStr.split(\",\");\r\n");
      out.write("     //拼table\r\n");
      out.write("     var renderHtml1 = \"<table style='border:0px solid #FFF;'>\";\r\n");
      out.write("     var renderHtml2 = \"</table>\";\r\n");
      out.write("     for(var i=0;i<filenameArray.length;i++){\r\n");
      out.write("   \t var fnStr = filenameArray[i];\r\n");
      out.write("   \tfileHtmlTd +=\"<tr style='border:0px solid #FFF;'>\"+\r\n");
      out.write("   \t                     \"<td style='border:0px solid #FFF;'>\"+\r\n");
      out.write("   \t                             \"<a href='javascript:void(0);' onclick='removeUploadFilebyId(\\\"\"+ idArray[i] + \"\\\");'style='color:red;'>\"+\"[删除]\"+\"</a>\"+\r\n");
      out.write("   \t                     \"</td>\"+\r\n");
      out.write("   \t                     \"<td style='border:0px solid #FFF;'>\"+\r\n");
      out.write("                                  \"<a href='javascript:void(0);' onclick='downloadFile(\\\"\"+ idArray[i] + \"\\\")'>\"+\"[\"+fnStr+\"]\"+\"</a>\"+\r\n");
      out.write("                         \"</td>\"+\r\n");
      out.write("                         \"<td style='border:0px solid #FFF;'>\"+\r\n");
      out.write("                                  \"【\"+   realnameArray[i]+\"】\"+\r\n");
      out.write("                         \"</td>\"+\r\n");
      out.write("                         \"<td style='border:0px solid #FFF;'>\"+\r\n");
      out.write("                                  \"【\"+ createdateArray[i]+\"】\"+\r\n");
      out.write("                         \"</td>\"+\r\n");
      out.write("   \t                \"</tr>\";\r\n");
      out.write("             } \r\n");
      out.write("     return renderHtml1+fileHtmlTd+renderHtml2;  \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//删除附件\r\n");
      out.write("function removeUploadFilebyId(uploadAttachmentFileDetailId) {\r\n");
      out.write("\tmini.confirm(\"确认删除合同 附件？\", \"删除？\", function(action){\r\n");
      out.write("\t\tif(action==\"ok\"){\r\n");
      out.write("\t\t\t$.ajax({\r\n");
      out.write("\t\t\t\tcache : false,\r\n");
      out.write("\t\t\t\tasync : false,\r\n");
      out.write("\t\t        type: \"post\",\r\n");
      out.write("\t\t        dataType: \"JSON\",\r\n");
      out.write("\t\t        url : getRootPath()+ \"/acl/ContractEnclosureMarkDelect.acl\",\r\n");
      out.write("\t\t        data: {uploadAttachmentFileDetailId:uploadAttachmentFileDetailId},\r\n");
      out.write("\t\t        success: function (data) {\r\n");
      out.write("\t\t        \tif(data){\r\n");
      out.write("\t\t        \t\tmini.alert(\"删除成功！\");\r\n");
      out.write("\t\t        \t\tmini.get(\"id_tasks_table1\").reload();\r\n");
      out.write("\t\t        \t\treturn;\r\n");
      out.write("\t\t        \t}else{\r\n");
      out.write("\t\t        \t\tmini.alert(\"删除失败！\");\r\n");
      out.write("\t\t        \t\treturn;\r\n");
      out.write("\t\t        \t}\r\n");
      out.write("\t\t        \t\r\n");
      out.write("\t\t        }\r\n");
      out.write("\t\t    })\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//上传附件\r\n");
      out.write("function upFile(id) {\r\n");
      out.write("\tvar filekey = id;\r\n");
      out.write("\tvar uploadutil = new uploadUtil({\r\n");
      out.write("\t\t\turl : '/leasing/file/uploadFile.action',\r\n");
      out.write("\t\t\ttableid : \"contract_word\",\r\n");
      out.write("\t\t    modelname : '合同附件',\r\n");
      out.write("\t\t    parames : {\r\n");
      out.write("\t\t\t\t\tflowUnid : flowUnid,\r\n");
      out.write("\t\t\t\t\tfilekey : filekey　　　\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tjscallback : 'reloadcustcontactfile2'\r\n");
      out.write("\t\t});\r\n");
      out.write("\tuploadutil.createFileAndShow();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\tfunction downloadFile(Id) {\r\n");
      out.write("\t\tattachmentDown({returnType:'file',url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/template/downLoadAttachById.action?id=\"+Id});\r\n");
      out.write("\t} \r\n");
      out.write("\t\r\n");
      out.write("\tfunction reloadcustcontactfile2(message){\r\n");
      out.write("\t\tmini.alert(message);\r\n");
      out.write("\t\tmini.get(\"id_uploadfile\").hide();\r\n");
      out.write("\t\tmini.unmask(document.body);\r\n");
      out.write("\t\tmini.get(\"id_tasks_table1\").reload();\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_tasksContainer2\" style=\"width: 100%;height: 100%;\"></div>\r\n");
      out.write("<table class=\"fillTable\" cellspacing=\"0\" cellpadding=\"0\" id=\"id_table_generate_contract\">\r\n");
      out.write("\t<tr class=\"tr-contractect-word-list\">\r\n");
      out.write("\t\t<td class=\"td-content\" colspan=\"2\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "contract_list_info.jsp", out, false);
      out.write("\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>");
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
