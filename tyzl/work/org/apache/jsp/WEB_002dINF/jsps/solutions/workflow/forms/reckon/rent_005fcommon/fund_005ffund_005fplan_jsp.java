/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-05-19 05:22:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon.rent_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class fund_005ffund_005fplan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tvar tools=[ 'edit','-','copy','-','remove','-'];\r\n");
      out.write("\tvar toolsnew={\r\n");
      out.write("\t\thtml : '生成费用表',\r\n");
      out.write("\t\tplain : true,\r\n");
      out.write("\t\ticonCls : 'icon-redo',\r\n");
      out.write("\t\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\t\tvar rentPlan=mini.get(\"fund_rent_plan_frame\").getData();\r\n");
      out.write("\t\t\tif(rentPlan.length <= 0){\r\n");
      out.write("\t\t\t\tmini.alert('请先进行租金测算，生成资金计划！！！！');\r\n");
      out.write("\t\t\t\treturn;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar lastRent=rentPlan[rentPlan.length-1].rent;\r\n");
      out.write("\t\t\tmini.mask({\r\n");
      out.write("\t\t\t\tel: document.body,\r\n");
      out.write("\t\t\t\tcls: 'mini-mask-loading',\r\n");
      out.write("\t\t\t\thtml: '正在更新资金计划中，请稍后...'\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar o = businessForm.getData(true,true);\r\n");
      out.write("\t\t\tvar fields = businessForm.getFields();\r\n");
      out.write("\t\t\tvar resultData = [];\r\n");
      out.write("\t\t\t$.extend(resultData,fields);\r\n");
      out.write("\t\t\tfor(var index =0;index<fields.length;index++){\r\n");
      out.write("\t\t\t\t //判断是否是下拉框控件，是则同时把text属性设置combobox\r\n");
      out.write("\t\t\t\tif(fields[index].uiCls == \"mini-textbox\"){\r\n");
      out.write("\t\t\t\t\tif(fields[index].getInputText().indexOf(',')!=-1){\r\n");
      out.write("\t\t\t\t\t\t//所有textbox去掉逗号,\r\n");
      out.write("\t\t\t\t\t\tresultData[index].setValue(fields[index].getInputText().replace(/,/g,\"\"));\r\n");
      out.write("\t\t\t\t\t}else if(!fields[index].getInputText() && (fields[index].vtype == 'double' || fields[index].vtype == 'int')){\r\n");
      out.write("\t\t\t\t\t\tresultData[index].setValue('0');\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t    o = businessForm.getData(true,true);//把textbox去掉,逗号之后，再次获取form\r\n");
      out.write("\t\t\to.docId = flowUnid;\r\n");
      out.write("\t\t\to.lastRent = lastRent;\r\n");
      out.write("\t\t\to.enddate = mini.get('enddate').getValue();\r\n");
      out.write("\t\t\to.startdate = mini.get('startdate').getFormValue();\r\n");
      out.write("\t\t\to.projId = mini.get('conditionProjId').getValue();\r\n");
      out.write("\t\t\to.contractId = mini.get('conditionContractId').getValue();\r\n");
      out.write("\t\t\to.process = mini.get('reckonProcess').getValue();\r\n");
      out.write("\t\t\to.json_fund_fund_charge_str = mini.encode(miniTable.getData());\r\n");
      out.write("\t\t\to.json_fund_rent_plan_str = mini.encode(mini.get('fund_rent_plan_frame').getData());\r\n");
      out.write("\t\t\t var url=\"");
      out.print(request.getContextPath() );
      out.write("/leasing/updateFundFundPlan.action\";\r\n");
      out.write("\t\t\t $.ajax({\r\n");
      out.write("\t\t        url: url,\r\n");
      out.write("\t\t        type: \"post\",\r\n");
      out.write("\t\t        data:  o ,\r\n");
      out.write("\t\t        success: function (text) {\r\n");
      out.write("\t\t        \tvar result = mini.decode(text);\r\n");
      out.write("\t\t        \tif(result.updateinfo == '成功'){\r\n");
      out.write("\t\t        \t\tmini.alert('资金计划更新成功！！！');\r\n");
      out.write("\t\t        \t\tvar fundchargeplan =  result.fundchargeplan;\r\n");
      out.write("\t\t        \t\tmini.get(\"cautionmoneyremain\").setValue(result.cautionmoneyremain);\r\n");
      out.write("\t\t        \t\tmini.get(\"cautiondeductionmoney\").setValue(result.cautiondeductionmoney);\r\n");
      out.write("\t\t        \t\tmini.get(\"cleancreditmoney\").setValue(result.cleancreditmoney);//风险敞口\r\n");
      out.write("\t\t        \t\tmini.get(\"cleancreditratio\").setValue(decimal(result.cleancreditmoney/mini.get(\"equipamt\").getValue()*100,6));//净授信比例\r\n");
      out.write("\t\t        \t\tmini.get(\"fund_fund_plan\").setData(fundchargeplan);\r\n");
      out.write("\t\t        \t\t$(\"#id_json_fund_fund_charge_str\").val(mini.encode(fundchargeplan));\r\n");
      out.write("\t\t\t\t\t\t//更新租金计划表中最后一期的实际还款金额  没有涉及到数据库级别的操作\r\n");
      out.write("\t\t        \t\tvar fundrentplan=mini.get(\"fund_rent_plan_frame\").getData();\r\n");
      out.write("\t\t        \t\tfundrentplan[fundrentplan.length-1].actualrent=decimal(fundrentplan[fundrentplan.length-1].rent-result.cautiondeductionmoney,2);\r\n");
      out.write("\t\t        \t\tmini.get(\"fund_rent_plan_frame\").setData(fundrentplan);\r\n");
      out.write("\t\t        \t\t$(\"#id_json_fund_rent_plan_str\").val(mini.encode(fundrentplan));\r\n");
      out.write("\t\t        \t\tmini.unmask(document.body);\r\n");
      out.write("\t\t        \t}else{\r\n");
      out.write("\t\t        \t\tmini.alert('资金计划更新失败！！！');\r\n");
      out.write("\t\t        \t\tmini.unmask(document.body);\r\n");
      out.write("\t\t        \t}\r\n");
      out.write("\t\t        },\r\n");
      out.write("\t\t        error: function (jqXHR, textStatus, errorThrown) {\r\n");
      out.write("\t\t        \tmini.unmask(document.body);\r\n");
      out.write("\t\t            alert(jqXHR.responseText);\r\n");
      out.write("\t\t        }\r\n");
      out.write("\t\t\t  });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t};\r\n");
      out.write("\ttools.push(toolsnew);\r\n");
      out.write("\tvar put={\r\n");
      out.write("\t\t\thtml : '抵扣',\r\n");
      out.write("\t\t\tplain : true,\r\n");
      out.write("\t\t\ticonCls : 'icon-redo',\r\n");
      out.write("\t\t\thandler : function(miniTable, buttonText) {\r\n");
      out.write("\t\t\t\tdebugger;\r\n");
      out.write("\t\t\t\t   var pays = miniTable.getSelecteds();\r\n");
      out.write("\t\t\t\t   for (var j = 0 ;j <pays.length; j++ ){\r\n");
      out.write("\t\t\t\t\t   //选择非设备款的收款进行抵扣\r\n");
      out.write("\t\t\t\t\t\tif(pays[j].paytype=='pay_type_out'){\r\n");
      out.write("\t\t\t\t\t\t\tmini.alert(\"请正确选择货扣的资金！！！\");\r\n");
      out.write("\t\t\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t   setDeduceFundPutPlanPut(pays);\r\n");
      out.write("\t\t\t\t   setDeduceFundPutPlanFund(pays);\r\n");
      out.write("\t\t\t\t   mini.alert(\"抵扣成功，请到本次投放明细查看！！！\");\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t};\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isput}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'){\r\n");
      out.write("\t\ttools.push(put);\r\n");
      out.write("\t};\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask ==true){showTools = false};\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isOnhire}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' && isViewHistoryTask != true){showTools = true;};\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isContractApp}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' && isViewHistoryTask != true){showTools = true;};\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isRentChange}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask ==true){\r\n");
      out.write("\t\t//租金计划变更里面只有 生成费用表按钮\r\n");
      out.write("\t\tvar toolschange=[];\r\n");
      out.write("\t\ttoolschange.push(tools[6]);\r\n");
      out.write("\t\ttools=toolschange;\r\n");
      out.write("\t};\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"fund_fund_plan\",\r\n");
      out.write("\t\t\trenderTo: \"id_fund_fund_plan\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 30,\r\n");
      out.write("\t\t\theight: 360,\r\n");
      out.write("\t\t\tlazyLoad: false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tmultiSelect: true,\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\teditFormPopupWindowWidth : 700,\r\n");
      out.write("\t\t\tdata: mini.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_fund_fund_charge_str ? \"[]\" : json_fund_fund_charge_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\ttools : tools,\r\n");
      out.write("\t\t\tconfirmCopyCallBack:function(miniTable,rows){\r\n");
      out.write("\t\t\t\tvar names = [];\r\n");
      out.write("\t\t\t\tfor(var i = 0 ; i<rows.length;i++){\r\n");
      out.write("\t\t\t\t\tnames.push(rows[i].feetypename);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tnames = unique(names);\r\n");
      out.write("\t\t\t\tvar newRows = [];\r\n");
      out.write("\t\t\t\tvar datas = miniTable.getData();\r\n");
      out.write("\t\t\t\tfor(var i = 0 ; i< names.length;i++){\r\n");
      out.write("\t\t\t\t\tvar temp = 0;\r\n");
      out.write("\t\t\t\t\tvar feetypename = names[i];\r\n");
      out.write("\t\t\t\t\tfor(var j = 0 ; j< datas.length;j++){\r\n");
      out.write("\t\t\t\t\t\tvar data = datas[j];\r\n");
      out.write("\t\t\t\t\t\tif(data.feetypename == feetypename){\r\n");
      out.write("\t\t\t\t\t\t\tif(Number(data.paymentid) > temp){\r\n");
      out.write("\t\t\t\t\t\t\t\ttemp = Number(data.paymentid);\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tfor(var k = 0 ;k<rows.length;k++){\r\n");
      out.write("\t\t\t\t\t\tvar row = mini.clone(rows[k]);\r\n");
      out.write("\t\t\t\t\t\tif(row.feetypename == feetypename){\r\n");
      out.write("\t\t\t\t\t\t\trow.paymentid = ++temp;\r\n");
      out.write("\t\t\t\t\t\t\tnewRows.push(row);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t} \r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tminiTable.addRows(newRows);\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tremoveOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tcopyOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tupdateOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t$('#id_json_fund_fund_charge_str').val(mini.encode(miniTable.getData()));\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'docid', header: 'docid', visible: false},\t\r\n");
      out.write("\t\t\t\t{field: 'contractid', header: '合同号',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'paymentid', header: '编号',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'plandate', header: '计划收付日期',type:\"date\",format:\"yyyy-MM-dd\",\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:\"date\",format:\"yyyy-MM-dd\",\r\n");
      out.write("\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'planmoney', header: '金额',dataType : \"currency\",summary : true,align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'feetypename', header: '款项名称',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\tnewLine : true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'paytype', header: '收付方向id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'paytypename', header: '收付方向',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:true,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'paycust', header: '收付对象',\r\n");
      out.write("\t\t\t\t\tvisible: false,\r\n");
      out.write("\t\t\t       formEditorConfig:{\r\n");
      out.write("\t\t\t\t    \tnewLine:true,\r\n");
      out.write("\t\t                width: 200,\r\n");
      out.write("\t\t                type:'queryinput',\r\n");
      out.write("\t\t             \trequired: true,\r\n");
      out.write("\t\t            \ttextField: 'paycustname',\r\n");
      out.write("\t\t           \t\tvalueField: 'paycust',\r\n");
      out.write("\t\t           \t\tallowInput: false,\r\n");
      out.write("\t\t         \t\tfieldVisible: true,\r\n");
      out.write("\t\t             \t/* onSelect:function($me, queryInput, rowData){\r\n");
      out.write("\t\t\t                   mini.getbyName(\"paycustname\").setValue(rowData.paycustname);\r\n");
      out.write("\t\t\t                   mini.getbyName(\"paycust\").setValue(rowData.paycust);\r\n");
      out.write("\t\t\t            }, */\r\n");
      out.write("\t\t               \tparams: {xmlFileName: '/eleasing/workflow/rent/rent_cal/fund_fund_pay_obj.xml'}\r\n");
      out.write("\t\t\t\t  }\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'paycustname', header: '收付对象',visible: true,\r\n");
      out.write("\t\t\t\t\t\tformEditorConfig:{\r\n");
      out.write("\t\t\t  \t\t\tfieldVisible:false \r\n");
      out.write("\t\t             }\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'fpnote', header: '备注',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'textarea',\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\theight:70,\r\n");
      out.write("\t\t\t\t\t\tcolspan:3,\r\n");
      out.write("\t\t\t\t\t\twidth: 478\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function unique(arr){\r\n");
      out.write("\tvar ret = [];\r\n");
      out.write("\tvar have = {};\r\n");
      out.write("\tfor(var i = 0 ; i< arr.length; i++){\r\n");
      out.write("\t\tvar item = arr[i];\r\n");
      out.write("\t\tvar key = typeof(item)+item;\r\n");
      out.write("\t\tif(have[key] != 1){\r\n");
      out.write("\t\t\tret.push(item);\r\n");
      out.write("\t\t\thave[key] = 1;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn ret;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function  setDeduceFundPutPlanPut(pays)\r\n");
      out.write("{\r\n");
      out.write("\tdebugger;\r\n");
      out.write("\tvar miniTable=mini.get(\"fund_guarance_plan\");\r\n");
      out.write("\tvar newRow = {};\r\n");
      out.write("\tvar planGrid = pays;//获取资金计划grid\r\n");
      out.write("\tvar currentGrid = mini.get(\"put_current\");//获取本次计划grid\r\n");
      out.write("\tvar rowDatas =currentGrid.getData();\r\n");
      out.write("\tvar alldemoney=0;\r\n");
      out.write("\tfor (var i = 0 ;i <planGrid.length; i++ )\r\n");
      out.write("\t{\r\n");
      out.write("\t\tif(parseFloat(planGrid[i].planmoney)>0){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tnewRow.paymentid=planGrid[i].paymentid;\r\n");
      out.write("// \t\t\tnewRow.feetype=planGrid[i].feetype;\r\n");
      out.write("// \t\t\tnewRow.feetypename=planGrid[i].feetypename;\r\n");
      out.write("// \t\t\tnewRow.paytype='pay_type_in';\r\n");
      out.write("// \t\t\tnewRow.paytypename='收款';\r\n");
      out.write("\t\t\tnewRow.feetype=\"feetype10\";\r\n");
      out.write("\t\t\tnewRow.feetypename=\"设备款\";\r\n");
      out.write("\t\t\tnewRow.settlemethod='payfund11';\r\n");
      out.write("\t\t\tnewRow.settlemethodname='货扣';\r\n");
      out.write("\t\t\tnewRow.paytype='pay_type_out';\r\n");
      out.write("\t\t\tnewRow.paytypename='付款';\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tnewRow.factdate=mini.formatDate(new Date(),\"yyyy-MM-dd\");\r\n");
      out.write("\t\t\tnewRow.feeadjust='0';\r\n");
      out.write("\t\t\tnewRow.ffcmemo=planGrid[i].feetypename+\"抵扣\";\r\n");
      out.write("\t\t\tnewRow.fundfundchargeplan=planGrid[i].feetype;//保存抵扣费用类型\r\n");
      out.write("\t\t\tnewRow.factmoney=planGrid[i].planmoney;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\trowDatas.push(mini.clone(newRow));\r\n");
      out.write("\t\t\talldemoney=parseFloat(alldemoney)+parseFloat(planGrid[i].planmoney);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tfor (var j = 0 ;j <rowDatas.length; j++ ){\r\n");
      out.write("\t\tif(rowDatas[j].settlemethod==\"payfund6\"){\r\n");
      out.write("\t\t\trowDatas[j].factmoney=parseFloat(rowDatas[j].factmoney)-parseFloat(alldemoney);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tcurrentGrid.setData(rowDatas);\r\n");
      out.write("\t//currentGrid.addRows ( rowDatas, 0 );\r\n");
      out.write("\tcurrentGrid.saveDataToInput();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function  setDeduceFundPutPlanFund(pays)\r\n");
      out.write("{\r\n");
      out.write("\tdebugger;\r\n");
      out.write("\tvar newRow = {};\r\n");
      out.write("\tvar planGrid = pays;//获取资金计划grid\r\n");
      out.write("\tvar currentGrid = mini.get(\"caution_money_refund_detail\");//获取本资金抵扣的grid\r\n");
      out.write("\tvar rowDatas =[];\r\n");
      out.write("\tfor (var i = 0 ;i <planGrid.length; i++ )\r\n");
      out.write("\t{\r\n");
      out.write("\t\tif(parseFloat(planGrid[i].planmoney)>0){\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tnewRow.paymentid=planGrid[i].paymentid;\r\n");
      out.write("\t\t\tnewRow.feetype=planGrid[i].feetype;\r\n");
      out.write("\t\t\tnewRow.feetypename=planGrid[i].feetypename;\r\n");
      out.write("\t\t\tnewRow.paytype='pay_type_in';\r\n");
      out.write("\t\t\tnewRow.paytypename='收款';\r\n");
      out.write("\t\t\tnewRow.settlemethod='payfund11';\r\n");
      out.write("\t\t\tnewRow.settlemethodname='货扣';\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tnewRow.factdate=mini.formatDate(new Date(),\"yyyy-MM-dd\");\r\n");
      out.write("\t\t\tnewRow.feeadjust='0';\r\n");
      out.write("\t\t\tnewRow.ffcmemo=planGrid[i].feetypename+\"抵扣\";\r\n");
      out.write("\t\t\tnewRow.fundfundchargeplan=planGrid[i].feetype;//保存抵扣费用类型\r\n");
      out.write("\t\t\tnewRow.factmoney=planGrid[i].planmoney;\r\n");
      out.write("\t\t\trowDatas.push(mini.clone(newRow));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tcurrentGrid.setData(rowDatas);\r\n");
      out.write("\t//currentGrid.addRows ( rowDatas, 0 );\r\n");
      out.write("\tcurrentGrid.saveDataToInput();\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_fund_fund_plan\"></div>");
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
