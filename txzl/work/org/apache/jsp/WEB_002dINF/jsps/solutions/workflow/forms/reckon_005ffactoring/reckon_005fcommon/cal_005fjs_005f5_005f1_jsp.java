/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-09 07:25:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon_005ffactoring.reckon_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class cal_005fjs_005f5_005f1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    \r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../reckon/rent_common/cal_js_5_1_common.jsp", out, false);
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar fields = form.getFields();\r\n");
      out.write("\tfor(var  i = 0  ; i < fields.length ; i++){\r\n");
      out.write("\t\tvar field = fields[i];\r\n");
      out.write("\t\tif(field.id == 'equipamt' ){\r\n");
      out.write("\t\t\tmini.get(field.id).set({'onkeyup':changeInputToThoud});\r\n");
      out.write("\t\t\tmini.get(field.id).set({'onvaluechanged':changeInputToThoud});\r\n");
      out.write("\t\t}else if(field.id == 'leasetermday' || field.id == 'ratefloat'){\r\n");
      out.write("\t\t\tmini.get(field.id).set({'onvaluechanged':leasetermdayChange});\r\n");
      out.write("\t\t}else if(field.vtype==\"double\"){\r\n");
      out.write("\t\t\tmini.get(field.id).set({'onkeyup':fieldValueChange}) ;\r\n");
      out.write("\t\t\tmini.get(field.id).set({'onvaluechanged':fieldValueChange}) ;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tconfirmConditionRate();\r\n");
      out.write("});\r\n");
      out.write("function equipamtChanged(e){\r\n");
      out.write("\tchangeInputToThoud(e);\r\n");
      out.write("\tcalSalesvolume();\r\n");
      out.write("\tcalActualfund();\r\n");
      out.write("}\r\n");
      out.write("function leasetermdayChange(e){\r\n");
      out.write("\tchangeInputToThoud(e);\r\n");
      out.write("\tconfirmConditionRate();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function fieldValueChange(e){\r\n");
      out.write("\tvar obj = e.sender;\r\n");
      out.write("\tvar radioId =  obj.id+\"ratio\";\r\n");
      out.write("\tif(obj.id == 'counselingmoney'){\r\n");
      out.write("\t\tradioId = \"counselingratio\";\r\n");
      out.write("\t}else if(obj.id == 'cautionmoney'){\r\n");
      out.write("\t\tradioId = \"cautiondeductionratio\";\r\n");
      out.write("\t}\r\n");
      out.write("\tsetformatvalue(e.sender);\r\n");
      out.write("\tvar feeValue = obj.getValue().replace(/,/g,\"\");\r\n");
      out.write("\tvar ratiovalue = getRadio(feeValue);\r\n");
      out.write("\tif(mini.get(radioId)){\r\n");
      out.write("\t\tmini.get(radioId).setValue(ratiovalue);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t//console.info(field.id);\t\r\n");
      out.write("\t}\r\n");
      out.write("\tcalSalesvolume();\r\n");
      out.write("\tcalActualfund();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getRadio(money){\r\n");
      out.write("\tvar equipAmt = getNumber('equipamt');\r\n");
      out.write("\treturn !equipAmt || Number(equipAmt)<=0 ?  0 : decimal(money/equipAmt*100, 2);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//销售额=应收账款受让款-保理费收入-保险费收入-担保费收入-登记费收入-咨询服务费收入-手续费收入-其他收入+其他成本\r\n");
      out.write("function calSalesvolume(){\r\n");
      out.write("\t//应收账款受让款\r\n");
      out.write("\tvar factoringpayout = getNumber('factoringpayout');\r\n");
      out.write("\t//保理费收入\r\n");
      out.write("\tvar factoringincome = getNumber('factoringincome');\r\n");
      out.write("\t//保险费收入\r\n");
      out.write("\tvar insuremoney = getNumber('insuremoney');\r\n");
      out.write("\t//担保费收入\r\n");
      out.write("\tvar factoringguaranteefee = getNumber('factoringguaranteefee');\r\n");
      out.write("\t//登记费收入\r\n");
      out.write("\tvar factoringregisterfee = getNumber('factoringregisterfee');\r\n");
      out.write("\t//咨询服务费收入\r\n");
      out.write("\tvar counselingmoney = getNumber('counselingmoney');\r\n");
      out.write("\t//手续费\r\n");
      out.write("\tvar handlingchargemoney = getNumber('handlingchargemoney');\r\n");
      out.write("\t//其他收入\r\n");
      out.write("\tvar otherfeerec  = getNumber('otherfeerec');\r\n");
      out.write("\t//其他成本\r\n");
      out.write("\tvar otherfee = getNumber('otherfee');\r\n");
      out.write("\tvar salesvolume = factoringpayout - factoringincome - insuremoney- \r\n");
      out.write("\t\t\t\t\t  factoringguaranteefee - factoringregisterfee -counselingmoney \r\n");
      out.write("\t\t\t\t\t  -handlingchargemoney - otherfeerec + otherfee;\r\n");
      out.write("\tmini.get('salesvolume').setValue(formatNumberThousand(salesvolume));\r\n");
      out.write("\tmini.get('salesvolumeratio').setValue(getRadio(salesvolume));\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//净融资额=销售额-保证金\r\n");
      out.write("function calActualfund(){\r\n");
      out.write("\t//销售额\r\n");
      out.write("\tvar salesvolume = getNumber('salesvolume');\r\n");
      out.write("\tvar cautionmoney = getNumber('cautionmoney');\r\n");
      out.write("\tvar actualfund = salesvolume - cautionmoney;\r\n");
      out.write("\tmini.get('actualfund').setValue(formatNumberThousand(actualfund));\r\n");
      out.write("\tmini.get('actualfundradio').setValue(getRadio(actualfund));\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getLastPlanDate(){\r\n");
      out.write("\tvar leasetermday = getNumber(\"leasetermday\")? getNumber(\"leasetermday\") : 0;\r\n");
      out.write("\tvar leaseamtdate = mini.get('leaseamtdate').getValue();\r\n");
      out.write("\treturn dateAdd('d',leasetermday,leaseamtdate);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//确定资金成本\r\n");
      out.write("function confirmConditionRate(){\r\n");
      out.write("\tvar leasetermday = getNumber(\"leasetermday\");\r\n");
      out.write("\t//应收账款转让日\r\n");
      out.write("\tvar leaseamtdate = mini.get('leaseamtdate').getFormValue();\r\n");
      out.write("\tif(leasetermday && leaseamtdate){\r\n");
      out.write("\t\tvar data = {};\r\n");
      out.write("\t\tdata.leaseamtdate = leaseamtdate;\r\n");
      out.write("\t\tdata.leasetermday = leasetermday;\r\n");
      out.write("\t\tvar flag = true;\r\n");
      out.write("\t\tvar baseRate = 0;\r\n");
      out.write("\t\tvar floatingRadio = 0;\r\n");
      out.write("\t\t//求基准利率\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t        url: \"");
      out.print(request.getContextPath() );
      out.write("/leasing/getBaseRate.action\",\r\n");
      out.write("\t        type: \"post\",\r\n");
      out.write("\t        data:  data ,\r\n");
      out.write("\t        async : false,\r\n");
      out.write("\t        success: function (json) {\r\n");
      out.write("\t        \tvar data = mini.decode(json);\r\n");
      out.write("\t        \tbaseRate = Number(data.baseRate);\r\n");
      out.write("\t        \tfloatingRadio = Number(data.floatingRadio);\r\n");
      out.write("\t        },\r\n");
      out.write("            error: function (jqXHR, textStatus, errorThrown) {\r\n");
      out.write("                flag = false;\r\n");
      out.write("                alert(jqXHR.responseText);\r\n");
      out.write("            }\r\n");
      out.write("\t    });\r\n");
      out.write("\t\tif(!flag){\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//基准利率\r\n");
      out.write("\t\t//mini.get('baserate').setValue(baseRate);\r\n");
      out.write("\t    //资金成本\r\n");
      out.write("\t    var fundcostYear =  decimal(baseRate * (1+floatingRadio/100),2);//数据字典维护的上浮利率\r\n");
      out.write("\t\tvar fundcostMonth = decimal(fundcostYear /12,2) ;\r\n");
      out.write("\t\tmini.get('factoringfundcostmonth').setValue(fundcostMonth);\r\n");
      out.write("\t\tmini.get('factoringfundcostyear').setValue(fundcostYear);\r\n");
      out.write("\t\t//平面利率\r\n");
      out.write("\t\tvar ratefloat = getNumber(\"ratefloat\");\r\n");
      out.write("\t\tvar factoringflatrateyear = decimal(baseRate * (1+ratefloat/100),2);\r\n");
      out.write("\t\tvar factoringflatratemonth = decimal(factoringflatrateyear/12,2);\r\n");
      out.write("\t\tmini.get('factoringflatrateyear').setValue(factoringflatrateyear);\r\n");
      out.write("\t\tmini.get('factoringflatrateyear1').setValue(factoringflatrateyear);\r\n");
      out.write("\t\tmini.get('factoringflatratemonth').setValue(factoringflatratemonth);\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function save(e){\r\n");
      out.write("\tvar startdate = $minigetvalue(\"leaseamtdate\");\r\n");
      out.write("\tif(!startdate){\r\n");
      out.write("\t\tmini.alert('请先输入应收账款转让日！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar equipamt =  $minigetvalue(\"equipamt\");\r\n");
      out.write("\tif(equipamt){\r\n");
      out.write("\t\tif(Number(equipamt) <= 0 ){\r\n");
      out.write("\t\t\tmini.alert('应收账款金额需大于0！！！');\r\n");
      out.write("\t\t\treturn;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tmini.alert('应收账款金额不能为空！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tvar leasetermday = $minigetvalue(\"leasetermday\");\r\n");
      out.write("\tvar numRegex  = /^[-\\+]?\\d+$/;\r\n");
      out.write("\tif(!numRegex.test(leasetermday)){\r\n");
      out.write("\t\tmini.alert('请你输入正确格式的期限！')\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tif(Number(leasetermday) <= 0 ){\r\n");
      out.write("\t\t\tmini.alert('期限需大于0！');\r\n");
      out.write("\t\t\treturn ;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t/*\r\n");
      out.write("\t检查保理明细是否为空\r\n");
      out.write("\t*/\r\n");
      out.write("\tvar renPlans = mini.get('fund_rent_plan').getData();\r\n");
      out.write("\tif(renPlans.length <= 0){\r\n");
      out.write("\t\tmini.alert('请你先填写保理明细！！！');\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\to = form.getData(true,true);\r\n");
      out.write("\tfor(var p in o){\r\n");
      out.write("\t\to[p] = o[p].replace(/,/gim,'');\r\n");
      out.write("\t} \r\n");
      out.write("\to.json_fund_rent_plan_str  = $('#id_json_fund_rent_plan_str').val();\r\n");
      out.write("\tmini.mask({\r\n");
      out.write("\t\tel: document.body,\r\n");
      out.write("\t\tcls: 'mini-mask-loading',\r\n");
      out.write("\t\thtml: '正在测算中，请稍后...'\r\n");
      out.write("\t});\r\n");
      out.write("\tvar url=\"");
      out.print(request.getContextPath() );
      out.write("/leasing/facRentCalculate.action\";\r\n");
      out.write("\t$.ajax({\r\n");
      out.write("        url: url,\r\n");
      out.write("        type: \"post\",\r\n");
      out.write("        data:  o ,\r\n");
      out.write("        success: function (text) {\r\n");
      out.write("        \tvar result = mini.decode(text);\r\n");
      out.write("        \tif(result.rentcalculaters == 'yes'){\r\n");
      out.write("        \t\tmini.get('factoringirryear').setValue(decimal(result.irr,2));\r\n");
      out.write("        \t\tmini.get('factoringirrmonth').setValue(decimal(result.factoringirrmonth,2));\r\n");
      out.write("        \t\tmini.get('factoringyearrateyear').setValue(decimal(result.factoringyearrateyear,2));\r\n");
      out.write("        \t\tmini.get('factoringyearratemonth').setValue(decimal(result.factoringyearratemonth,2));\r\n");
      out.write("        \t\t \r\n");
      out.write("        \t\tvar costmonth = mini.get('factoringfundcostmonth').getValue();\r\n");
      out.write("        \t\tmini.get('factoringspreadmonth').setValue(decimal(result.factoringspreadmonth,2));\r\n");
      out.write("        \t\tmini.get('factoringspreadyear').setValue(decimal(result.factoringspreadyear,2));\r\n");
      out.write("        \t\tmini.get('factoringflatrateyear').setValue(decimal(result.factoringflatrateyear,2));\r\n");
      out.write("        \t\tmini.get('factoringflatratemonth').setValue(decimal(result.factoringflatratemonth,2));\r\n");
      out.write("        \t\t\r\n");
      out.write("        \t\tmini.get('gp').setValue(result.gp);\r\n");
      out.write("            \tvar fundrentplan =result.rentplanlist ;\r\n");
      out.write("            \tvar finacashdetail =result.cashdetaillist ;\r\n");
      out.write("            \tvar fundchargeplan =  result.fundchargeplan;\r\n");
      out.write("            \t\r\n");
      out.write("            \tmini.get(\"fund_rent_plan\").setData(fundrentplan);\r\n");
      out.write("            \tmini.get(\"fund_cash_plan_frame\").setData(finacashdetail);\r\n");
      out.write("            \tmini.get(\"fund_fund_plan\").setData(fundchargeplan);\r\n");
      out.write("            \t//分别把租金计划  、现金流 、资金收付计划保存至隐藏域\r\n");
      out.write("            \t$(\"#id_json_fund_rent_plan_str\").val(mini.encode(fundrentplan));\r\n");
      out.write("            \t$(\"#id_json_fund_cash_flow_str\").val(mini.encode(finacashdetail));\r\n");
      out.write("            \t$(\"#id_json_fund_fund_charge_str\").val(mini.encode(fundchargeplan));\r\n");
      out.write("            \tmini.unmask(document.body);\r\n");
      out.write("            \tmini.alert('测算成功！');\r\n");
      out.write("        \t}\r\n");
      out.write("        \telse {\r\n");
      out.write("            \tmini.unmask(document.body);\r\n");
      out.write("        \t\tmini.alert('测算失败，请与管理员联系！');\r\n");
      out.write("            \tupdateInputThousand();\r\n");
      out.write("        \t}\r\n");
      out.write("        \t\r\n");
      out.write("        },\r\n");
      out.write("        error: function (jqXHR, textStatus, errorThrown) {\r\n");
      out.write("        \tmini.unmask(document.body);\r\n");
      out.write("            alert(jqXHR.responseText);\r\n");
      out.write("        }\r\n");
      out.write("    });\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>");
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
