/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-24 04:34:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.rent.fund_005foffset.comm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005fguarance_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){showTools = false;}\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\tid: \"fund_guarance_plan\",\r\n");
      out.write("\t\trenderTo: \"id_table_fund_guarance_plan\",\r\n");
      out.write("\t\twidth : '100%',\r\n");
      out.write("\t\theight :'100%',\r\n");
      out.write("\t\teditFormPopupWindowWidth : 400,\r\n");
      out.write("\t\tlazyLoad: false,\r\n");
      out.write("\t\tisClickLoad:false,\r\n");
      out.write("\t\tremoteOper : false,\r\n");
      out.write("\t\tshowPager: true,\r\n");
      out.write("\t\tpageSize: 20,\r\n");
      out.write("\t\ttools:[{\r\n");
      out.write("\t\t\thtml: '抵扣',\r\n");
      out.write("\t\t\tplain: false,\r\n");
      out.write("\t\t\ticonCls: 'icon-folder',\r\n");
      out.write("\t\t\thandler: function(miniTable, buttonText){\r\n");
      out.write("\t\t\t\tvar row = miniTable.getSelected();\r\n");
      out.write("\t\t\t\tif(row){\r\n");
      out.write("\t\t\t\t\tif(parseFloat(row.overmoney)>0){\r\n");
      out.write("\t\t\t\t\t   var selectedDatas=mini.get(\"rent_income_plan\").getSelecteds();\t\r\n");
      out.write("\t\t\t\t\t   var currTable=mini.get(\"rent_income_detail\");\r\n");
      out.write("\t\t\t\t\t   var currRowDatas=currTable.getData();\t\r\n");
      out.write("\t\t\t\t\t   var renttable=mini.get(\"rent_income_plan\");\r\n");
      out.write("\t\t\t\t\t   var info=\"使用编号为\"+row.paymentid+\"的保证金抵扣\";\r\n");
      out.write("\t\t\t\t\t   mini.mask({el: document.body,cls: 'mini-mask-loading',html: '生成抵扣信息中..'});\r\n");
      out.write("\t\t\t\t\t   var rinfo= generateRentDetail(renttable,currTable,currRowDatas,selectedDatas,row.overmoney,info);\r\n");
      out.write("                       var fundtable=mini.get(\"caution_money_refund_detail\");\r\n");
      out.write("                       var fundRowDatas=fundtable.getData();\r\n");
      out.write("                       if(rinfo.length>0){\r\n");
      out.write("\t\t\t\t\t     getFundPutPlan(fundtable,fundRowDatas,row,rinfo);\r\n");
      out.write("                       }\r\n");
      out.write("\t\t\t\t\t   mini.unmask(document.body);\r\n");
      out.write("\t\t\t\t\t   miniTable.deselectAll(false);\r\n");
      out.write("\t\t\t\t\t   mini.get(\"id_window_fund_offset\").hide();\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("                         mini.alert(\"这一笔保证金没有余额不能进行保证金抵扣\");\r\n");
      out.write("                         return false;\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tmini.alert(\"请选中要抵扣的保证金计划！\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}],\r\n");
      out.write("\t\tqueryButtonColSpan: 4,\r\n");
      out.write("\t\txmlFileName: 'eleasing/workflow/fund/fundcomm/fund_fund_plan_list.xml',\r\n");
      out.write("\t\tparams : {\r\n");
      out.write("\t\t    paytype:'pay_type_out',\r\n");
      out.write("\t\t    feetypes:\"'feetype2','feetype17','feetype16'\",\r\n");
      out.write("\t\t    contractid:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope['contract_info.id']}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"  \r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tcolumns: [\r\n");
      out.write("\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t{field: 'feetypename', header: '费用类型',visible: true, formEditorConfig:{\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\tfieldVisible: false,\r\n");
      out.write("\t\t\t\tfillFromFieldName : 'feetype',\r\n");
      out.write("\t\t\t\tfillProperty : 'name'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'feetype', header: '费用类型', visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\ttype : 'combobox',\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'value',\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t    pid: 'FeeType',\r\n");
      out.write("\t\t\t\t\txmlFileName: 'combos/comboDict.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'paymentid', header: '收/付款编号',formEditorConfig:{labelWidth:100,required: true}},\r\n");
      out.write("\t\t\t{field: 'paycustname', header: '支付对象',formEditorConfig:{fieldVisible: false}},\r\n");
      out.write("\t\t\t{field: 'paycust', header: '支付对象',visible: false,formEditorConfig:{\r\n");
      out.write("\t\t\t\tnewLine: true,\r\n");
      out.write("\t\t\t\ttype : 'queryinput',\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttextField: 'name',\r\n");
      out.write("\t\t\t\tvalueField: 'name',\r\n");
      out.write("\t\t\t\tallowInput: false,\r\n");
      out.write("\t\t\t\tfieldVisible: true,\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\tcolspan:3,\r\n");
      out.write("\t\t\t\tparams: {\r\n");
      out.write("\t\t\t\t\txmlFileName: 'common/custInfo.xml'\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'planmoney', header: '计划付款金额',summary : true,dataType : \"currency\",formEditorConfig:{newLine:true,required: true}},\r\n");
      out.write("\t\t\t{field: 'factmoney', header: '实付款金额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'overmoney', header: '计划余额',summary : true,dataType : \"currency\",formEditorConfig:{defaultValue:'0.00',newLine:true,readOnly:true}},\r\n");
      out.write("\t\t\t{field: 'plandate', header: '付款日期',formEditorConfig:{\r\n");
      out.write("\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\ttype:'date'\r\n");
      out.write("\t\t\t}},\r\n");
      out.write("\t\t\t{field: 'fpnote', header: '备注',formEditorConfig:{newLine:true,colspan:3, type: 'textarea',\r\n");
      out.write("\t\t\t\twidth: 300,\r\n");
      out.write("\t\t\t\theight:70}}\r\n");
      out.write("\t\t]\r\n");
      out.write("\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t \r\n");
      out.write("});\r\n");
      out.write("function  getFundPutPlan(currTable,currRowDatas,row,rinfo)\r\n");
      out.write("{\r\n");
      out.write("\tvar newRow = {};\r\n");
      out.write("\tvar rowDatas = [];\r\n");
      out.write("    newRow.fundfundchargeplan=row.id;\r\n");
      out.write("\tnewRow.paymentid=row.paymentid;\r\n");
      out.write("\tnewRow.feetype=row.feetype;\r\n");
      out.write("\tnewRow.feetypename=row.feetypename;\r\n");
      out.write("\tnewRow.settlemethod='payfund7';\r\n");
      out.write("\tnewRow.settlemethodname='保证金抵扣';\r\n");
      out.write("\tnewRow.paytype='pay_type_out';\r\n");
      out.write("\tnewRow.paytypename='付款';\r\n");
      out.write("\tnewRow.factdate=(new Date()).format('yyyy-MM-dd');\r\n");
      out.write("\tnewRow.accountingdate=(new Date()).format('yyyy-MM-dd');\r\n");
      out.write("\tnewRow.factadjust=0;\r\n");
      out.write("\tnewRow.factobject=row.paycust;\r\n");
      out.write("\tnewRow.factobjectname=row.paycustname;\r\n");
      out.write("\r\n");
      out.write("\tnewRow.feeadjust='0';\r\n");
      out.write("\tfor(var i=0;i<rinfo.length;i++){\r\n");
      out.write("\t\tvar temp=mini.clone(newRow);\r\n");
      out.write("\t\ttemp.pid=rinfo[i].pid;\r\n");
      out.write("\t\tvar tempmoney=parseFloat(rinfo[i].rent)+parseFloat(rinfo[i].penalty);\r\n");
      out.write("\t\ttempmoney=parseFloat(tempmoney).toFixed(2);\r\n");
      out.write("\t\ttemp.factmoney=tempmoney;\r\n");
      out.write("\t\ttemp.comparemoney=tempmoney;\r\n");
      out.write("\t\tvar info=\"抵扣第\"+rinfo[i].planlist+\"期\";\r\n");
      out.write("\t\tif(parseFloat(rinfo[i].rent)>0){info+=\"租金(\"+rinfo[i].rent+\")\";}\r\n");
      out.write("\t\tif(parseFloat(rinfo[i].penalty)>0){info+=\"罚息(\"+rinfo[i].penalty+\")\";}\r\n");
      out.write("\t\ttemp.ffcmemo=info;\r\n");
      out.write("\t\tcurrRowDatas.push(temp);\r\n");
      out.write("\t}\r\n");
      out.write("\tcurrTable.setData(currRowDatas);\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"id_table_fund_guarance_plan\" style=\"width:100%;height:400px\"></div>\r\n");
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
