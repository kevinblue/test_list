/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-08-12 06:44:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.leasing.financial.loan_005ffee_005fincome;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loan_005ffee_005fincome_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tracywindy/tracywindyAjax.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/tenwa.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function() {\r\n");
      out.write("\t var loanid ='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.loanid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\r\n");
      out.write("\t\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\t\tnew ApTable({\r\n");
      out.write("\t\t\t\tid:'loan_fee_income',\r\n");
      out.write("\t\t\t\trenderTo:'id_table_loan_fee_income',\r\n");
      out.write("\t\t\t\twidth:globalClientWidth,\r\n");
      out.write("\t\t\t\theight:globalClientHeight,\r\n");
      out.write("\t\t\t\ttitle:'',\r\n");
      out.write("\t\t\t\ticonCls:'icon-node',\r\n");
      out.write("\t\t\t\tmultiSelect:false,\r\n");
      out.write("\t\t\t\thiddenQueryArea:false,//是否将查询区域折叠起来\r\n");
      out.write("\t\t\t\tqueryButtonColSpan:2,\r\n");
      out.write("\t\t\t\tqueryButtonNewLine:false,\r\n");
      out.write("\t\t\t\tshowPager:true,\r\n");
      out.write("\t\t\t\timportTargetClass : 'com.tenwa.leasing.entity.finacial.LoanFeeIncome',\r\n");
      out.write("\t\t\t\timportDataIndex : '2',\r\n");
      out.write("\t\t\t\timportHeaderIndex : '1',\r\n");
      out.write("\t\t\t\timportLoanaccountid :loanid,\r\n");
      out.write("\t\t\t\timportOrExPortCallBack:'SetloanIdIncome',\r\n");
      out.write("  \t\t\t\tremoteOper:true,\r\n");
      out.write("  \t\t\t\tparams:{loanid:loanid},\r\n");
      out.write("\t\t\t\tentityClassName:'com.tenwa.leasing.entity.finacial.LoanFeeIncome',\r\n");
      out.write("\t\t\t\txmlFileName:'/eleasing/jsp/finacial/loan_fee_income/loan_fee_income_list.xml',\r\n");
      out.write("\t\t\t\ttools:['add','-','edit','-','remove','-','exportExcel','-','importExcel'],\r\n");
      out.write("\t\t\t\tafterShowWindowCallBack : function(miniTable,miniForm, operFlag){\r\n");
      out.write("\t\t\t\t\t//alert(mini.getbyName(\"feename\").getValue());\r\n");
      out.write("\t\t\t\t\tmini.getbyName(\"planid\").setText(mini.getbyName(\"feename\").getValue());\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tcolumns:[ \r\n");
      out.write("\t\t\t\t    {type:'indexcolumn'},\r\n");
      out.write("\t\t\t\t   \t{type:'checkcolumn'},  \r\n");
      out.write("\t\t\t\t   \t{field:'id',header:'id',visible:false},\r\n");
      out.write("\t\t\t\t   \t{field: 'feename', header: '支付计划', \r\n");
      out.write("\t\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t\t          fieldVisible: false,\r\n");
      out.write("\t\t\t\t\t\t      fillProperty:'name',\r\n");
      out.write("\t\t\t\t\t\t      entityClassName:'com.tenwa.leasing.entity.finacial.LoanFeePlan',\r\n");
      out.write("\t\t\t\t\t\t\t  fillFromFieldName : 'planid',//关联的列\r\n");
      out.write("\t\t\t\t\t\t\t  entityHeaderFieldName:'feeName'//显示值是对应实体类的哪个字段\r\n");
      out.write("\t\t\t\t\t\t      }\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\tfield : 'planid',\r\n");
      out.write("\t\t\t\t\t\theader : '支付计划',\r\n");
      out.write("\t\t\t\t\t\tvisible : false,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig : {\r\n");
      out.write("\t\t\t\t\t\t\twidth : 200,\r\n");
      out.write("\t\t\t\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\t\t\t\trequired : true,\r\n");
      out.write("\t\t\t\t\t\t\ttextField : 'feename',\r\n");
      out.write("\t\t\t\t\t\t\tvalueField : 'id',\r\n");
      out.write("\t\t\t\t            allowInput:false,\r\n");
      out.write("\t\t\t\t\t         fieldVisible:true,\r\n");
      out.write("\t\t\t\t             onSelect:function($me, queryInput, rowData){\r\n");
      out.write("\t\t\t\t                   mini.getbyName(\"currencyname\").setValue(rowData.currencyname);\r\n");
      out.write("\t\t\t\t                   mini.getbyName(\"currency\").setValue(rowData.currency);},\r\n");
      out.write("\t\t\t\t\t\t\tparams : {\r\n");
      out.write("\t\t\t\t\t\t\t\txmlFileName : '/eleasing/jsp/finacial/loan_fee_plan/loan_fee_plan_income.xml',\r\n");
      out.write("\t\t\t\t\t\t\t\tloanid:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.loanid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t},\r\n");
      out.write("              \t\t{field:'loanid',header:'借款合同号',visible:false,allowSort:true,formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\trequired:false,\r\n");
      out.write("\t\t\t\t   \t\tfieldVisible:false,\r\n");
      out.write("\t\t\t\t   \t\tdefaultValue:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.loanid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("',\r\n");
      out.write("              \t\t\ttype:'text'\r\n");
      out.write("              \t\t}}, \r\n");
      out.write("\t\t\t\t   \t{field:'currencyname',header:'币种',visible:true,formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\tfieldVisible:false,\r\n");
      out.write("\t\t\t\t\t     fillFromFieldName:'currency',\r\n");
      out.write("\t\t\t\t         fillProperty:'name',\r\n");
      out.write("\t\t\t\t         defaultValue:'人民币',\r\n");
      out.write("\t\t\t\t         entityClassName:'com.tenwa.business.entity.DictionaryData',\r\n");
      out.write("\t\t\t\t\t     fillFromFieldName : 'currency',//关联的列\r\n");
      out.write("\t\t\t\t\t     entityHeaderFieldName:'name'//显示值是对应实体类的哪个字段\r\n");
      out.write("\t\t\t\t   \t}},\r\n");
      out.write("\t\t\t\t   \t{field:'currency',header:'币种',visible:false,formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\ttype:'combobox',\r\n");
      out.write("\t\t\t\t   \t\tfieldVisible:true,\r\n");
      out.write("\t\t\t\t   \t\trequired:true,\r\n");
      out.write("\t\t\t\t   \t\treadOnly:false,\r\n");
      out.write("\t\t\t\t   \t\tdefaultValue:'currency_type1',\r\n");
      out.write("\t\t\t\t   \t\ttextField: 'name',\r\n");
      out.write("\t\t\t\t\t\tvalueField: 'value',\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\tparams:{pid: 'currency_type',xmlFileName: '/combos/comboDict.xml'}\r\n");
      out.write("\t\t\t\t   \t}},\r\n");
      out.write("\t\t\t\t   \t{field:'factmoney',header:'实付金额',visible:true,dataType:\"currency\",formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\trequired:true,\r\n");
      out.write("              \t\t\ttype:'text',vtype:'float',\r\n");
      out.write("\t\t\t\t   \t\tnewLine:true,\r\n");
      out.write("\t\t\t\t   \t\tfieldVisible:true\r\n");
      out.write("\t\t\t\t   \t}},\r\n");
      out.write("\t\t\t\t   \t{field:'factdate',header:'实付日期',visible:true,formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\trequired:true,\r\n");
      out.write("\t\t\t\t   \t\ttype:'date'\r\n");
      out.write("\t\t\t\t   \t}},\r\n");
      out.write("\t\t\t\t   \t{field:'adjustmoney',header:'调整金额',visible:true,align:'center',\r\n");
      out.write("\t\t\t\t\t\theaderAlign : 'center',dataType:\"currency\",formEditorConfig:{\r\n");
      out.write("\t\t\t\t   \t\trequired:true,\r\n");
      out.write("              \t\t\ttype:'text',vtype:'float',\r\n");
      out.write("\t\t\t\t   \t\tnewLine:true,\r\n");
      out.write("\t\t\t\t   \t\tfieldVisible:true\r\n");
      out.write("\t\t\t\t   \t}},\r\n");
      out.write("\t\t\t\t]\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"id_table_loan_fee_income\"></div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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