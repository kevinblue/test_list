/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 08:19:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.rent_005fcomm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rent_005fincome_005fdetail_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif(isViewHistoryTask){showTools = false;}\r\n");
      out.write("\ttenwa.createTable({\r\n");
      out.write("\t\tid: \"rent_income_detail\",\r\n");
      out.write("\t\trenderTo: \"id_table_rent_income_detail\",\r\n");
      out.write("\t\twidth : globalClientWidth-30,\r\n");
      out.write("\t\theight : 400,\r\n");
      out.write("\t\ttitle: \"\",\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: false,\r\n");
      out.write("\t\tlazyLoad: true,\r\n");
      out.write("\t\tmultiSelect: true,\r\n");
      out.write("\t\tshowToolbar: showTools,\r\n");
      out.write("\t\ttools: ['edit', '-', 'remove'],\r\n");
      out.write("\t\tdata: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_rent_income_detail_str ? \"[]\" : json_rent_income_detail_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'checkcolumn',visible: showTools},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field:'planid',header:'planid',visible: false},\r\n");
      out.write("\t\t\t{field: 'planlist', header: '计划期项',type:'number',formEditorConfig:{labelWidth:100,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'ebdataid', header: '网银编号',formEditorConfig:{labelWidth:100,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'ebanknumber', header: '网银编号',visible: false},\r\n");
      out.write("\t\t\t{field: 'hirelist', header: '回笼期项',formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'balancemodename', header: '结算方式',visible:false,formEditorConfig: {fillFromFieldName : 'balancemode',fillProperty : 'name'}},\r\n");
      out.write("\t\t\t{field: 'balancemode', header: '结算方式',\r\n");
      out.write("\t\t\t\trenderer: function(e){\r\n");
      out.write("\t\t\t\t\treturn e.record.balancemodename;\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t\t\ttype:'combobox',\r\n");
      out.write("\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\t\tvalueField: 'value',\r\n");
      out.write("\t\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\tpid: 'PayFund',\r\n");
      out.write("\t\t\t\t\txmlFileName: 'combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'hiredate', header: '回笼日期',type:\"date\",format:\"yyyy-MM-dd\",formEditorConfig:{\r\n");
      out.write("\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\ttype: 'date',\r\n");
      out.write("\t\t\t\tvtype: 'date',\r\n");
      out.write("\t\t\t\tformat: 'yyyy-MM-dd'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'accountingdate', header: '会计处理日',type:\"date\",format:\"yyyy-MM-dd\",formEditorConfig:{\r\n");
      out.write("\t\t\t\ttype: 'date',\r\n");
      out.write("\t\t\t\tvtype: 'date',\r\n");
      out.write("\t\t\t\tformat: 'yyyy-MM-dd'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'rent', header: '回笼租金',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,vtype:'float',readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'corpus', header: '回笼本金',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{vtype:'float',onkeyup: 'checkHire(e,\"corpus\")'}},\r\n");
      out.write("\t\t\t{field: 'interest', header: '回笼租息',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,vtype:'float',onkeyup: 'checkHire(e,\"interest\")'}},\r\n");
      out.write("\t\t\t{field: 'penalty', header: '回笼罚息',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{vtype:'float',onkeyup: 'checkHire(e,\"penalty\")'}},\r\n");
      out.write("\t\t\t{field: 'rentadjust', header: '租金调整',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{readOnly:true,newLine:true,vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'corpusadjust', header: '本金调整',type:'double',summary : true,dataType : \"currency\",visible: false,formEditorConfig:{vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'interestadjust', header: '租息调整',type:'double',summary : true,dataType : \"currency\",formEditorConfig:{vtype:'float',onkeyup:'checkHire(e,\"interestadjust\")'}},\r\n");
      out.write("\t\t\t{field: 'penaltyadjust', header: '罚息调整',type:'double',summary : true,dataType : \"currency\",visible: false,formEditorConfig:{vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'curcorpusoverage', header: '本金余额',visible:false,formEditorConfig:{vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'curinterestoverage', header: '租息余额',visible:false,formEditorConfig:{vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'penaltyoverage', header: '罚息余额',visible:false,formEditorConfig:{vtype:'float'}},\r\n");
      out.write("\t\t\t{field: 'hireobject', header: '付款人',formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'invoiceno', header: '单据号',formEditorConfig:{}},\r\n");
      out.write("\t\t\t{field: 'ownbank', header: '本方银行',formEditorConfig:{colspan:3,newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'ownaccount', header: '本方银行账户',formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'ownnumber', header: '本方银行账号',formEditorConfig:{readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'hirebank', header: '对方银行',formEditorConfig:{colspan:3,newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'hireaccount', header: '对方银行账户',formEditorConfig:{newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'hirenumber', header: '对银行账号',formEditorConfig:{readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'memo', header: '备注',formEditorConfig:{colspan : 3,\r\n");
      out.write("\t\t\t\twidth: 400,\r\n");
      out.write("\t\t\t\theight:70, type: 'textarea',newLine:true}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("//检查核销值\r\n");
      out.write("function checkHire(e,type){\r\n");
      out.write("    var corpus = getMiniEditFormField(\"rent_income_detail.corpus\");\r\n");
      out.write("\tvar interest =getMiniEditFormField(\"rent_income_detail.interest\");\r\n");
      out.write("\tvar penalty =getMiniEditFormField(\"rent_income_detail.penalty\"); \r\n");
      out.write("\tvar curcorpusoverage =getMiniEditFormField(\"rent_income_detail.curcorpusoverage\"); \r\n");
      out.write("\tvar curinterestoverage = getMiniEditFormField(\"rent_income_detail.curinterestoverage\");  \r\n");
      out.write("\tvar penaltyoverage =getMiniEditFormField(\"rent_income_detail.penaltyoverage\");  \r\n");
      out.write("\tvar interestadjust = getMiniEditFormField(\"rent_income_detail.interestadjust\"); \r\n");
      out.write("\tvar rent= getMiniEditFormField(\"rent_income_detail.rent\"); \r\n");
      out.write("\tif(type == \"corpus\"){\r\n");
      out.write("\t\tif(compareTwoField( $(\"input[name=corpus]\").val(),curcorpusoverage.getValue())){\r\n");
      out.write("            mini.alert(\"本次回笼本金金额大于本金余额\"+curcorpusoverage.getValue());\r\n");
      out.write("            corpus.setValue(curcorpusoverage.getValue());\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(type == \"interest\"||type==\"interestadjust\"){\r\n");
      out.write("\t\t var sum=parseFloat( $(\"input[name=interest]\").val())+parseFloat( $(\"input[name=interestadjust]\").val());\r\n");
      out.write("\t\t sum=parseFloat(sum).toFixed(2);\r\n");
      out.write("\t\t if(compareTwoField(sum,curinterestoverage.getValue())){\r\n");
      out.write("\t            mini.alert(\"本次回笼保理租息加上保理租息调整的金额大于保理租息余额\"+curinterestoverage.getValue());\r\n");
      out.write("\t            if(type == \"interest\"){interest.setValue(curinterestoverage.getValue());}\r\n");
      out.write("\t            if(type==\"interestadjust\"){interestadjust.setValue(0);}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(type = \"penalty\"){\r\n");
      out.write("\t\tif(compareTwoField( $(\"input[name=penalty]\").val(),penaltyoverage.getValue())){\r\n");
      out.write("            mini.alert(\"本次回笼罚息金额大于罚息余额\"+penaltyoverage.getValue());\r\n");
      out.write("            penalty.setValue(penaltyoverage.getValue());\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tsum=parseFloat($(\"input[name=corpus]\").val()||0)+parseFloat($(\"input[name=interest]\").val()||0)+parseFloat($(\"input[name=interestadjust]\").val()||0);\r\n");
      out.write("\tsum=parseFloat(sum).toFixed(2);\r\n");
      out.write("\trent.setValue(sum);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_rent_income_detail\" style=\"width: 100%;height: 100%;\"></div>");
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
