/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:28:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon.rent_005freckon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class rent_005fcustom_005finfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\tfunction updateIrrExcel(config){\r\n");
      out.write("\t\t\tconfig.id=config.id ||\"id_uploadfile\";\r\n");
      out.write("\t\t\tconfig.title=config.title||\"上传文件\"; //标题 \r\n");
      out.write("\t\t\tconfig.url=config.url||\"\";//url\r\n");
      out.write("\t\t\tconfig.jscallback=config.jscallback||\"\";//回调\r\n");
      out.write("\t\t\tconfig.parames=config.parames||{};\r\n");
      out.write("\t\t\tconfig.modelname=config.modelname||\"财务报表\";\r\n");
      out.write("\t\t\tconfig.browserType=SysBrowser.getBrowser().toLowerCase();\r\n");
      out.write("\t\t\tcreateFileAndShow(config);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tfunction createFileAndShow(config){\r\n");
      out.write("\t\t\tvar $form = jQuery(\"#\" + config.id);\r\n");
      out.write("\t\t\tif (0 < $form.length) {\r\n");
      out.write("\t\t\t\tdocument.body.removeChild($form[0]);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar importExcelUrl = getRootPath()+config.url;\r\n");
      out.write("\t\t\tvar uploadAttachmentFileWindow_html = \"\";\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '<div id=\"'+config.id+'\" class=\"mini-window\"  closed=\"true\" modal=\"true\" title=\"'+config.title+'\" style=\"display:none;width:400px;text-align:center;background-color:#FDF9F8;\">';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t<center>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t<div style=\"width:95%;\">';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t<iframe style=\"display:none;\" name=\"import_excel_real_submit_frame\"></iframe>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t<form id=\"'+config.id+'Form\" action=\"'+importExcelUrl+'\" enctype=\"multipart/form-data\" target=\"import_excel_real_submit_frame\"  method=\"post\" >';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t<table align=\"center\" style=\"width:90%\"><tr><td colspan=2>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html +=getParametetoStr(config);\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += \"</td></tr>\";\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t<tr><td colspan=2><input type=\"file\" id=\"id_tableImportTemplate_irr_calculate\" name=\"tableImportExcel\" style=\"width:350px;border:1px solid #DDD;cursor:pointer;\"></td></tr>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t<tr class=\"content-separator\">';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t\t<td colspan=\"2\" align=\"center\">';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t\t\t<a href=\"javascript:void(0);\" id=\"'+config.id+'_save\" class=\"mini-button\" iconCls=\"icon-add\"  onclick=\\'javascript:document.getElementById(\"'+config.id+'Form\").submit();\\'><span class=\"mini-button-text\">确定</span></a>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t\t\t<a style=\"margin-left:20px;\" id=\"'+config.id+'_cancle\" href=\"javascript:void(0);\" class=\"mini-button\" iconCls=\"icon-close\"  onclick=\\'javascript:mini.get(\"'+config.id+'\").hide();\\'><span class=\"mini-button-text\" >取消</span></a>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t\t</td>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t\t</tr>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t\t</table>';    \r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t\t</form>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t\t</div>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '\t</center>';\r\n");
      out.write("\t\t\tuploadAttachmentFileWindow_html += '</div>';\r\n");
      out.write("\t\t\t$(document.body).append(uploadAttachmentFileWindow_html);\r\n");
      out.write("\t\t\tmini.parse();\r\n");
      out.write("\t\t\tvar formid=config.id+\"Form\";\r\n");
      out.write("\t\t\tvar id=config.id;\r\n");
      out.write("\t\t\t$(\"#\"+config.id+\"_save\").bind('click',function(){\r\n");
      out.write("\t\t\t\tvar filename=$(\"#id_tableImportTemplate_irr_calculate\").val();\r\n");
      out.write("\t\t\t\tif(filename==\"\"){alert(\"请选择上传的文件\");return false;}\r\n");
      out.write("\t\t\t\tmini.mask({ el: document.body,cls: 'mini-mask-loading',html:'上传中...'});\r\n");
      out.write("\t\t\t\t$(\"#\"+formid).submit();});\r\n");
      out.write("\t\t\t$(\"#\"+config.id+\"_cancle\").bind('click',function(){mini.get(id).hide()});\r\n");
      out.write("\t\t\tmini.get(config.id).show();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction getParametetoStr(config){\r\n");
      out.write("\t\t\tvar tempInnerHtml=\"\"; \r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='browserType' value='\" + config.browserType+ \"'/>\";\r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='currentTableId' value='\" + config.id+ \"'/>\";\r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='jscallback' value='\" + config.jscallback+ \"'/>\";\r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='modelname' value='\" + config.modelname+ \"'/>\";\r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='_success' value='window.parent.irrCalculateSuccess'/>\";\r\n");
      out.write("\t\t\ttempInnerHtml+= \"<input type='hidden' name='_failture' value='window.parent.irrCalculateFailture'/>\";\r\n");
      out.write("\t\t    for(var param in config.parames){\r\n");
      out.write("\t\t      if (typeof (config.parames[param]) == 'object'){\r\n");
      out.write("\t\t    \ttempInnerHtml+= \"<input type='hidden' name='\"+param+\"' value='\" + mini.decode(config.parames[param])+ \"'/>\";\r\n");
      out.write("\t\t    \t}else{\r\n");
      out.write("\t\t    \ttempInnerHtml+= \"<input type='hidden' name='\"+param+\"' value='\" + config.parames[param]+ \"'/>\";\t\r\n");
      out.write("\t\t      }\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    return tempInnerHtml;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction irrCalculateSuccess( message){\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\tvar result = mini.decode(message);\r\n");
      out.write("\t\t\tif(result.issucess == 'true'){\r\n");
      out.write("\t\t\t\t$mini(\"irrshow\").setValue(result.irr);//内部收益率\r\n");
      out.write("\t\t\t\t$(\"#irr\").val(result.irr);\r\n");
      out.write("\t\t\t\tmini.get(\"xirr\").setValue(result.xirr);\r\n");
      out.write("\t\t\t\t//$mini(\"leaseterm\").setValue(result.leaseterm);//租赁期限\r\n");
      out.write("\t\t\t\tvar sender = mini.get(\"incomenumberyear\");//租金支付类型\r\n");
      out.write("\t\t\t\tvar incomenumber = $minigetinputtext(\"incomenumber\");//还租次数\r\n");
      out.write("\t\t\t\tvar grace = $minigetinputtext(\"grace\")\r\n");
      out.write("\t\t\t\tgrace = isNaN(grace) ? 0 : grace;\r\n");
      out.write("\t\t\t\tincomenumber = isNaN(incomenumber) ? 0 : incomenumber;\r\n");
      out.write("\t\t\t\tvar resultnumber = incomenumber + grace;\r\n");
      out.write("\t\t\t\tif(\"income_1\" == sender.value){\r\n");
      out.write("\t\t\t\t\t$mini(\"leaseterm\").setValue(resultnumber);\r\n");
      out.write("\t\t\t\t}else if(\"income_3\" == sender.value){\r\n");
      out.write("\t\t\t\t\t$mini(\"leaseterm\").setValue(resultnumber *3);\r\n");
      out.write("\t\t\t\t}else if(\"income_6\" == sender.value){\r\n");
      out.write("\t\t\t\t\t$mini(\"leaseterm\").setValue(resultnumber *6);\r\n");
      out.write("\t\t\t\t}else if(\"income_12\" == sender.value){\r\n");
      out.write("\t\t\t\t\t$mini(\"leaseterm\").setValue(resultnumber *12);\r\n");
      out.write("\t\t\t\t}else if(\"income_2\" == sender.value){\r\n");
      out.write("\t\t\t\t\t$mini(\"leaseterm\").setValue(resultnumber *2);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t$mini(\"incomenumber\").setValue(result.incomenumber);//还租次数\r\n");
      out.write("        \t\t$mini(\"yearrate\").setValue(result.yearrate);//年利率\r\n");
      out.write("            \t$mini(\"enddate\").setValue(result.enddate);//结束日期\r\n");
      out.write("            \t$mini(\"firstplandate\").setValue(result.firstplandate);//一期\r\n");
      out.write("            \t$mini(\"secondplandate\").setValue(result.secondplandate);//二期\r\n");
      out.write("            \t$mini(\"grossprofit\").setValue(result.grossprofit);//项目粗利\r\n");
      out.write("            \t$mini(\"cleancreditmoney\").setValue(result.cleancreditmoney);//授信额度\r\n");
      out.write("            \t$mini(\"firstpaymenttotal\").setValue(result.firstpaymenttotal);//计算期初付款总计\r\n");
      out.write("            \tif(mini.get('rentorrate').getValue() != 'rate'){\r\n");
      out.write("            \t\t$mini(\"yearrate\").setValue(decimal(result.yearrate, 6));//年租息率\r\n");
      out.write("            \t}\r\n");
      out.write("            \t$mini(\"cleancreditratio\").setValue(decimal(result.cleancreditratio, 6));//授信比例\r\n");
      out.write("            \t\r\n");
      out.write("            \t//result.fundrentplan 是对象、 不是字符串\r\n");
      out.write("            \t//如果需要查看字符串、则用 mini.encode(fundrentplan)\r\n");
      out.write("            \t//租金计划  \r\n");
      out.write("            \t//现金流 \r\n");
      out.write("            \t//资金收付计划\r\n");
      out.write("            \tmini.get(\"fund_rent_plan_frame\").isinitData=1;\r\n");
      out.write("            \tmini.get(\"fund_cash_plan_frame\").isinitData=1;\r\n");
      out.write("            \tmini.get(\"fund_fund_plan\").isinitData=1;\r\n");
      out.write("            \t\r\n");
      out.write("            \tvar fundrentplan =mini.decode(result.rentplanlist) ;\r\n");
      out.write("            \tvar finacashdetail =mini.decode(result.cashdetaillist) ;\r\n");
      out.write("            \tvar fundchargeplan = mini.decode( result.fundchargeplan);\r\n");
      out.write("            \t\r\n");
      out.write("            \tmini.get(\"fund_rent_plan_frame\").setData(fundrentplan);\r\n");
      out.write("            \tmini.get(\"fund_cash_plan_frame\").setData(finacashdetail);\r\n");
      out.write("            \tmini.get(\"fund_fund_plan\").setData(fundchargeplan);\r\n");
      out.write("            \t//分别把租金计划  、现金流 、资金收付计划保存至隐藏域\r\n");
      out.write("            \tjQuery(\"#id_json_fund_rent_plan_str\").val(mini.encode(fundrentplan));\r\n");
      out.write("            \tjQuery(\"#id_json_fund_cash_flow_str\").val(mini.encode(finacashdetail));\r\n");
      out.write("            \tjQuery(\"#id_json_fund_fund_charge_str\").val(mini.encode(fundchargeplan));\r\n");
      out.write("            \tupdateInputThousand();\r\n");
      out.write("            \tmini.alert(\"测算成功！\", \"提示\", function(){\r\n");
      out.write("            \t\t//$miniSetCombValue('incomenumberyear','income_0','不等期');\r\n");
      out.write("\t\t\t\t\tmini.get('irrExcelImportDiv_irr_cal').hide();\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tmini.alert(result.message);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tfunction irrCalculateFailture( message){\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\tmini.alert(message);\r\n");
      out.write("\t\t}\r\n");
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
