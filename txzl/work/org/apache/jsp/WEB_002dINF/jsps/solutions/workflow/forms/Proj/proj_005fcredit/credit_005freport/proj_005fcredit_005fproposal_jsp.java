/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-14 09:30:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.Proj.proj_005fcredit.credit_005freport;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class proj_005fcredit_005fproposal_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("seajs.use([\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/tenwa/init.js\"]);\r\n");
      out.write("var modelnameParmas=null;\r\n");
      out.write("jQuery(function(){\r\n");
      out.write("\tvar showTools = true;\r\n");
      out.write("\r\n");
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true'||isViewHistoryTask==true){showTools = false;}\r\n");
      out.write("\t//获取父页面中保存在hidden域的值\r\n");
      out.write("\t\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"quotation_scheme\",\r\n");
      out.write("\t\t\trenderTo: \"id_table_quotation_scheme\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 20,\r\n");
      out.write("\t\t\theight: 360,\r\n");
      out.write("\t\t\tlazyLoad: false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tmultiSelect: true,\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\ttools: ['remove','-',\r\n");
      out.write("\t\t\t        {\r\n");
      out.write("\t\t\t\t\t\thtml : '生成项目建议书',\r\n");
      out.write("\t\t\t\t\t\tplain : true,\r\n");
      out.write("\t\t\t\t\t\ticonCls : 'icon-save',\r\n");
      out.write("\t\t\t\t\t\thandler : function(miniTable, buttonText) {\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t//获得01界面上租赁形式，来判断生成模板类型：直接租赁和售后回租\r\n");
      out.write("\t\t\t\t\t\t\tvar lease_form=mini.getbyName(\"proj_info.leasform\").getText();\r\n");
      out.write("\t\t\t\t\t\t     var cdates=miniTable.getData();\r\n");
      out.write("\t\t\t\t\t       for(var i=0;i<cdates.length;i++){ \r\n");
      out.write("\t\t\t\t                   \t\tmini.alert(\"您已生成一份，无需重复生成\");            \t \r\n");
      out.write("\t\t\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\t\tif(lease_form==\"直接租赁\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tmodelnameParmas=\"项目建议书-直租\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t}else if(lease_form==\"售后回租\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\tmodelnameParmas=\"项目建议书-回租\";\r\n");
      out.write("\t\t\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t\t\tmini.alert(\"非直接租赁和售后回租项目无须生成项目建议书！\");\r\n");
      out.write("\t\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\tvar params1=getParams1();\r\n");
      out.write("\t\t\t\t\t\t\tvar params2=getParams2();\r\n");
      out.write("\t\t\t\t\t\t\tvar fileTeplate1=new fileTemplateConfig({\r\n");
      out.write("\t\t\t\t\t\t\t\tisAttachment : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tattachmentParams: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmodule:'WorkflowAttchmentFiles',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tjbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tattachmentFileDictId : 'root.FileType.Proposal01',//项目建议书\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierOne:window.assignAttachmentKeyOne||\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentProcessInstanceDBID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierTwo:window.assignAttachmentKeyTwo||jQuery(\"#id_currentHistoryTaskInfo_keyOne\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierThree:window.assignAttachmentKeyThree||jQuery(\"#id_currentHistoryTaskInfo_keyTwo\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierFour:window.assignAttachmentKeyFour||jQuery(\"#id_currentHistoryTaskInfo_keyThree\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierFive:window.assignAttachmentKeyFive||jQuery(\"#id_currentHistoryTaskInfo_keyFour\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierSix:window.assignAttachmentKeySix||jQuery(\"#id_currentHistoryTaskInfo_keyFive\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierSeven:window.assignAttachmentKeySeven||jQuery(\"#id_currentHistoryTaskInfo_keySix\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierEight:window.assignAttachmentKeyEight||jQuery(\"#id_currentHistoryTaskInfo_keySeven\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierNine:window.assignAttachmentKeyNine||jQuery(\"#id_currentHistoryTaskInfo_keyEight\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierTen:window.assignAttachmentKeyTen||jQuery(\"#id_currentHistoryTaskInfo_keyNine\").val()\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\ttemplateno : 'F-201612012',\r\n");
      out.write("\t\t\t\t\t\t\t\ttableid : 'quotation_scheme',\r\n");
      out.write("\t\t\t\t\t\t\t\tmodelname : modelnameParmas,\r\n");
      out.write("\t\t\t\t\t\t\t\treturntype : 'listtocurpage',\r\n");
      out.write("\t\t\t\t\t\t\t\tparames : params1\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\tvar fileTeplate2=new fileTemplateConfig({\r\n");
      out.write("\t\t\t\t\t\t\t\tisAttachment : true,\r\n");
      out.write("\t\t\t\t\t\t\t\tattachmentParams: {\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmodule:'WorkflowAttchmentFiles',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tjbpmWorkflowHistoryInfoId : window.currentJbpmWorkflowHistoryInfoId ? window.currentJbpmWorkflowHistoryInfoId : '',\r\n");
      out.write("\t\t\t\t\t\t\t\t\tattachmentFileDictId : 'root.FileType.Proposal01',//项目建议书\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierOne:window.assignAttachmentKeyOne||\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${currentProcessInstanceDBID}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\",\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierTwo:window.assignAttachmentKeyTwo||jQuery(\"#id_currentHistoryTaskInfo_keyOne\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierThree:window.assignAttachmentKeyThree||jQuery(\"#id_currentHistoryTaskInfo_keyTwo\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierFour:window.assignAttachmentKeyFour||jQuery(\"#id_currentHistoryTaskInfo_keyThree\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierFive:window.assignAttachmentKeyFive||jQuery(\"#id_currentHistoryTaskInfo_keyFour\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierSix:window.assignAttachmentKeySix||jQuery(\"#id_currentHistoryTaskInfo_keyFive\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierSeven:window.assignAttachmentKeySeven||jQuery(\"#id_currentHistoryTaskInfo_keySix\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierEight:window.assignAttachmentKeyEight||jQuery(\"#id_currentHistoryTaskInfo_keySeven\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierNine:window.assignAttachmentKeyNine||jQuery(\"#id_currentHistoryTaskInfo_keyEight\").val(),\r\n");
      out.write("\t\t\t\t\t\t\t\t\tidentifierTen:window.assignAttachmentKeyTen||jQuery(\"#id_currentHistoryTaskInfo_keyNine\").val()\r\n");
      out.write("\t\t\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t\t\t\t\t\ttemplateno : 'F-201612005',\r\n");
      out.write("\t\t\t\t\t\t\t\ttableid : 'quotation_scheme',\r\n");
      out.write("\t\t\t\t\t\t\t\tmodelname : modelnameParmas,\r\n");
      out.write("\t\t\t\t\t\t\t\treturntype : 'listtocurpage',\r\n");
      out.write("\t\t\t\t\t\t\t\tparames : params2\r\n");
      out.write("\t\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif(cdates.length==0){\r\n");
      out.write("\t\t\t\t\t\t\t\tif(lease_form==\"直接租赁\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmodelnameParmas=\"项目建议书-直租\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfileTeplate1.createFile();\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmini.alert(\"您已成功生成直接租赁项目建议书\");\r\n");
      out.write("\t\t\t\t\t\t\t\t}else if(lease_form==\"售后回租\"){\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmodelnameParmas=\"项目建议书-回租\";\r\n");
      out.write("\t\t\t\t\t\t\t\t\tfileTeplate2.createFile();\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmini.alert(\"您已成功生成售后回租项目建议书\");\r\n");
      out.write("\t\t\t\t\t\t\t\t}else{\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\t\t\tmini.alert(\"请确认租赁形式是否填写！\");\r\n");
      out.write("\t\t\t\t\t\t\t\t\treturn;\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\tif (mini.get(\"id_customworkflowattachment\")) {\r\n");
      out.write("\t\t\t\t\t\t\t\tmini.get(\"id_customworkflowattachment\").reload();\r\n");
      out.write("\t\t\t\t\t\t\t\tmini.get(\"id_workflowhisAttachment\").reload();\r\n");
      out.write("\t\t\t\t\t\t\t  }\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t\tsaveCallBack();\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t        },\r\n");
      out.write("\t\t\t        ],\r\n");
      out.write("\t\t\t//data: JsonUtil.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_quotation_scheme_str ? \"[]\" : json_quotation_scheme_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\txmlFileName : '/eleasing/workflow/proj/proj_credit/credit_report_list.xml',\r\n");
      out.write("\t\t\tparams : {\r\n");
      out.write("\t\t\t\tflowUnid:flowUnid,\r\n");
      out.write("\t\t\t\tmodelname : modelnameParmas,\r\n");
      out.write("\t\t\t\tfilekey:flowUnid+mini.get(\"project_id\").getValue()\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tupdateOperCallBack: function(miniTable,formData){\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tremoveOperCallBack: function(miniTable,rowDatas){\r\n");
      out.write("\t\t\t\tdropCreateFile(rowDatas);\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'filename', header: '文件名称'},\r\n");
      out.write("\t\t\t\t{field: 'createdate', header: '文件生成时间',dateFormat:'yyyy-MM-dd hh:mm:ss'},\r\n");
      out.write("\t\t\t\t{field: 'create', header: '操作',renderer:function(e){ var cfalg=showTools;\r\n");
      out.write("\t\t\t\tvar temp=e;\r\n");
      out.write("\t\t\t\treturn showOperator(temp,cfalg);}}\r\n");
      out.write("\t\t\t]\t\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function getParams1(){\r\n");
      out.write("\tvar params={};\r\n");
      out.write("\tparams[\"flowunid\"]=flowUnid;\r\n");
      out.write("\tparams[\"filekey\"]=flowUnid+mini.get(\"project_id\").getValue();\r\n");
      out.write("\r\n");
      out.write("\t//项目名称label11\r\n");
      out.write("\tparams['label.projname'] = mini.get(\"project_name\").getValue();\r\n");
      out.write("\t//建议书生成日期\r\n");
      out.write(" \t//params['label.projdate'] = mini.get(\"cust_name\").getValue();\r\n");
      out.write("\tparams['label.projdate'] =new Date().format(\"yyyy年MM月dd日\");\t\r\n");
      out.write("\t//项目所在地\r\n");
      out.write("\tvar proj_id=mini.get(\"project_id\").getValue();\r\n");
      out.write("\t//mini.alert(proj_id);\r\n");
      out.write("    params['projaddress'] =proj_id;\r\n");
      out.write("    \r\n");
      out.write("\t//项目规模projscale\r\n");
      out.write("\tparams['label.projscale'] = mini.get(\"projscale\").getText();\t\r\n");
      out.write("\t//融资租赁金额\r\n");
      out.write("\tparams['label.cleanleasemoney']=getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000;\r\n");
      out.write("\t//客户融资净额\r\n");
      out.write("\tparams['label.cleancreditmoney']=getNumberNew(mini.get(\"cleancreditmoney\").getValue())/10000;\r\n");
      out.write("\t//静态投资额和融资金额一样    cleanleasemoney\r\n");
      out.write("\t//动态投资额和客户融资净额一样\r\n");
      out.write("\t\r\n");
      out.write("\t//期限    leaseterm\r\n");
      out.write("\t//params['label.leaseterm']= Math.ceil((mini.get(\"leaseterm\").getValue())/12);\r\n");
      out.write("\tvar leasetermmonth=mini.get(\"leaseterm\").getValue();\r\n");
      out.write("\tvar leasetermyear=Math.ceil(leasetermmonth/12);\r\n");
      out.write("\tvar leasetermother=mini.get(\"grace\").getValue();\r\n");
      out.write("\tvar leaseterm=parseFloat(leasetermyear)+parseFloat(leasetermother);\r\n");
      out.write("\tparams['year.leaseterm']=leaseterm;\r\n");
      out.write("\tparams['year.leasetermother']=leasetermother;\t\r\n");
      out.write("\t//利率  \t\r\n");
      out.write("\tvar ratebase=mini.get(\"baserate\").getValue();\r\n");
      out.write("\tvar ratebefore=parseFloat(ratebase)+1.1;\r\n");
      out.write("\tvar rateafter=parseFloat(ratebase)+0.6;\r\n");
      out.write("\tparams['rate.ratebase']=ratebase;\r\n");
      out.write("\tparams['rate.ratebefore']=ratebefore;\r\n");
      out.write("\tparams['rate.rateafter']=rateafter;\t\r\n");
      out.write("\t//保证金\r\n");
      out.write("\tvar money=getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000\r\n");
      out.write(" \tvar margin=(money*5/100).toFixed(2);\r\n");
      out.write(" \tparams['rate.margin']=margin; \r\n");
      out.write("\t//手续费\r\n");
      out.write("\tvar yearpoundage=money*2/100;\r\n");
      out.write("\tparams['rate.poundage']=yearpoundage.toFixed(2)+\"万元\";\t\r\n");
      out.write("\t//租赁期\r\n");
      out.write("\tparams['rate.leaseterm']=leaseterm;\t\r\n");
      out.write("\t//还款方式\r\n");
      out.write("\t//获取租金\r\n");
      out.write("\tvar jsonrent=mini.get('fund_rent_plan_frame').getData(); \r\n");
      out.write("\t//var jsonrent=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());\r\n");
      out.write("\tif(jsonrent.length!=0){\r\n");
      out.write("\t\tvar  returnmoney=jsonrent[0][\"rent\"];\r\n");
      out.write("\t\tparams['rate.returnmoney']=(returnmoney/10000).toFixed(2);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tparams['rate.returnmoney']=0;\r\n");
      out.write("\t}\t\r\n");
      out.write("\t//承租人背景\r\n");
      out.write("\t//客户名称\r\n");
      out.write("\tvar custname=mini.get(\"cust_name\").getValue();\r\n");
      out.write("\t//mini.alert(custname);\r\n");
      out.write("\tparams['lessee_frame_list']=custname;\t\r\n");
      out.write("\t//客户编号\r\n");
      out.write("\tvar custnumber = mini.getbyName(\"proj_info.custnumber\").getValue();\r\n");
      out.write("\tparams['custbase.custname']=custname;\r\n");
      out.write("\tparams['custbase.custnumber']=custnumber;\t\r\n");
      out.write("\t//租赁方案\r\n");
      out.write("\t//期限\r\n");
      out.write("\tparams['lease_scheme.leaseterm']=leaseterm;\r\n");
      out.write("\t//宽限期\r\n");
      out.write("\tparams['lease_scheme.leasetermother']=leasetermother;\r\n");
      out.write("\t//还款租金\r\n");
      out.write("\tparams['lease_scheme.returnmoney']=(returnmoney/10000).toFixed(2);\r\n");
      out.write("\t//融资金额\r\n");
      out.write("\tparams['lease_scheme.cleanleasemoney']=getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000;\r\n");
      out.write("\t//租赁款发放计划表\r\n");
      out.write("\tvar lease_schemearr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsonrent.length;i++){\r\n");
      out.write("\t\tvar equipment={};\r\n");
      out.write("\t\t    equipment[\"number\"] = i+1;\r\n");
      out.write("\t\t    equipment[\"plandate\"] = jsonrent[i][\"plandate\"];\r\n");
      out.write("\t\t    equipment[\"rent\"] = (jsonrent[i][\"rent\"]/10000).toFixed(2);\r\n");
      out.write("\t\t\tlease_schemearr.push(equipment);\t\t\r\n");
      out.write("\t} \r\n");
      out.write("\t//params['lease_plan_list']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());\r\n");
      out.write("\tparams['lease_plan_list']=JsonUtil.encode(lease_schemearr);\r\n");
      out.write("\t\r\n");
      out.write("\t//保证金收取计划表\r\n");
      out.write("\tvar jsonpromise = mini.get('fund_fund_plan').getData();\r\n");
      out.write(" \tvar marginarr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsonpromise.length;i++){\r\n");
      out.write("\t\tvar count=0;\r\n");
      out.write("\t\tvar margin={};\r\n");
      out.write("\t\tvar moneyname=jsonpromise[i][\"feetypename\"];\r\n");
      out.write("\t\tif(moneyname=='保证金'){\r\n");
      out.write("\t\t\tcount+=1;\r\n");
      out.write("\t\t\tmargin[\"number\"] = count;\r\n");
      out.write("\t\t\tmargin[\"plandate\"] =jsonpromise[i][\"plandate\"]; \r\n");
      out.write("\t\t\tmargin[\"planmoney\"] =(jsonpromise[i][\"planmoney\"]/10000).toFixed(2);\r\n");
      out.write("\t\t\tmarginarr.push(margin);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['lease_margin_list']=JsonUtil.encode(marginarr);\r\n");
      out.write("\t//console.info(params['lease_margin_list']);\r\n");
      out.write("\t//console.info(jsonpromise);\r\n");
      out.write("\t//手续费\r\n");
      out.write("\tvar jsonpoundage= mini.get('fund_fund_plan').getData();\r\n");
      out.write(" \tvar poundagearr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsonpoundage.length;i++){\r\n");
      out.write("\t\tvar count=0;\r\n");
      out.write("\t\tvar poundage={};\r\n");
      out.write("\t\tvar moneyname=jsonpoundage[i][\"feetypename\"];\r\n");
      out.write("\t\tif(moneyname=='手续费'){\r\n");
      out.write("\t\t\tcount+=1;\r\n");
      out.write("\t\t\tpoundage[\"number\"] = count;\r\n");
      out.write("\t\t\tpoundage[\"plandate\"] =jsonpoundage[i][\"plandate\"]; \r\n");
      out.write("\t\t\tpoundage[\"planmoney\"] =(jsonpoundage[i][\"planmoney\"]/10000).toFixed(2);\r\n");
      out.write("\t\t\tpoundagearr.push(poundage);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['lease_poundage_list']=JsonUtil.encode(poundagearr);\r\n");
      out.write("\t//租金偿还表\r\n");
      out.write("\tparams['rent_repay.cleanleasemoney']=(getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000).toFixed(2);//租赁金额\r\n");
      out.write("\tparams['rent_repay.leaseterm']=leaseterm;//期限\r\n");
      out.write("\tparams['rent_repay.firstpayment']=(getNumberNew(mini.get(\"firstpayment\").getValue())/10000).toFixed(2);//预付金\r\n");
      out.write("\tparams['rent_repay.incomenumber']=mini.get(\"incomenumber\").getValue();//年还款数\r\n");
      out.write("\tparams['rent_repay.count']=yearpoundage.toFixed(2);//手续费\r\n");
      out.write("\t//mini.alert(yearpoundage);\r\n");
      out.write("\tvar startdate=mini.get(\"startdate\").getValue();\r\n");
      out.write("\tparams['rent_repay.repay_date']=mini.formatDate(startdate, \"yyyy 年  MM 月 dd 日 \");//放款日期\r\n");
      out.write("\t//租金计划安排\r\n");
      out.write("\tparams['rent_repay_list']=JsonUtil.encode(lease_schemearr);\r\n");
      out.write("\t//console.info(params['rent_repay_list']);  \r\n");
      out.write("\treturn params;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function getParams2(){\r\n");
      out.write("\tvar params={};\r\n");
      out.write("\tparams[\"flowunid\"]=flowUnid;\r\n");
      out.write("\tparams[\"filekey\"]=flowUnid+mini.get(\"project_id\").getValue();\r\n");
      out.write("\r\n");
      out.write("\t//客户名称\r\n");
      out.write("\tparams['label.custname']=mini.get(\"custname\").getValue();\r\n");
      out.write("\t//项目名称label11\r\n");
      out.write("\tparams['label.projname'] = mini.get(\"project_name\").getValue();\r\n");
      out.write("\t//建议书生成日期\r\n");
      out.write(" \t//params['label.projdate'] = mini.get(\"cust_name\").getValue();\r\n");
      out.write("\tparams['label.projdate'] =new Date().format(\"yyyy年MM月dd日\");\t\r\n");
      out.write("\t//客户名称+项目名称\r\n");
      out.write("\tparams['label.custprojname']=mini.get(\"custname\").getValue()+mini.get(\"project_name\").getValue();\r\n");
      out.write("    \r\n");
      out.write("\t//固定资产净值\r\n");
      out.write("\tparams['label.equipamt']=(getNumberNew(mini.get(\"equipamt\").getValue())/10000).toFixed(2);\r\n");
      out.write("\t//融资租赁金额\r\n");
      out.write("\tparams['label.cleanleasemoney']=getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000;\r\n");
      out.write("\t//客户融资净额\r\n");
      out.write("\tparams['label.cleancreditmoney']=getNumberNew(mini.get(\"cleancreditmoney\").getValue())/10000;\r\n");
      out.write("\t\r\n");
      out.write("\t//融资租赁方案\r\n");
      out.write("\t//融资金额\r\n");
      out.write("\tparams['lease_plan.cleanleasemoney']=params['label.cleanleasemoney'];\r\n");
      out.write("\t\r\n");
      out.write("\t//期限\r\n");
      out.write("\tvar leasetermmonth=mini.get(\"leaseterm\").getValue();\t\r\n");
      out.write("\tvar leasetermyear=Math.ceil(leasetermmonth/12);\r\n");
      out.write("\tvar leasetermother=mini.get(\"grace\").getValue();\r\n");
      out.write("\tvar leaseterm=parseFloat(leasetermyear)+parseFloat(leasetermother);\r\n");
      out.write("\t//alert(\"总期限\"+leaseterm+\"宽限期\"+leasetermother);\r\n");
      out.write("\tparams['lease_plan.leaseterm']=leaseterm;\r\n");
      out.write("\t//利率  \t\r\n");
      out.write("\tvar ratebase=mini.get(\"baserate\").getValue();\r\n");
      out.write("\tparams['lease_plan.baserate']=ratebase;\r\n");
      out.write("\t//保证金\r\n");
      out.write("\tvar money=getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000\r\n");
      out.write(" \tvar margin=(money*10/100).toFixed(2);\r\n");
      out.write(" \tparams['lease_plan.margin']=margin; \r\n");
      out.write(" \t//首笔放款日\r\n");
      out.write(" \tvar jsonlend=mini.get(\"fund_put_config\").getData();\r\n");
      out.write(" \tif(jsonlend.length!=0){\r\n");
      out.write(" \t\tparams['lease_plan.plandate']=jsonlend[0][\"plandate\"];//放款日期\r\n");
      out.write(" \t}else{\r\n");
      out.write(" \t\tparams['lease_plan.plandate']=\"\";//放款日期\r\n");
      out.write(" \t}\r\n");
      out.write("\t//手续费\r\n");
      out.write("\tvar yearpoundage=(money*(1.8/100));\r\n");
      out.write("\tparams['lease_plan.poundage']=yearpoundage.toFixed(2)+\"万元\";\t\r\n");
      out.write("\t\r\n");
      out.write("\t//获取租金\r\n");
      out.write("\tvar jsonrent=mini.get('fund_rent_plan_frame').getData(); \r\n");
      out.write("\tif(jsonrent.length!=0){\r\n");
      out.write("\t\tvar  returnmoney=jsonrent[0][\"rent\"];\r\n");
      out.write("\t\tparams['lease_plan.returnmoney']=(returnmoney/10000).toFixed(2);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tparams['lease_plan.returnmoney']=0;\r\n");
      out.write("\t}\t\r\n");
      out.write("/* \t//租赁期\r\n");
      out.write("\tparams['lease_plan.leaseterm']=leaseterm; */\r\n");
      out.write("\t\r\n");
      out.write("\t//承租人背景\r\n");
      out.write("\t//客户名称\r\n");
      out.write("\tvar custname=mini.get(\"cust_name\").getValue();\r\n");
      out.write("\t//客户编号\r\n");
      out.write("\tvar custnumber = mini.getbyName(\"proj_info.custnumber\").getValue();\r\n");
      out.write("\tparams['lessee_base.custname']=custname;\r\n");
      out.write("\tparams['lessee_base.custnumber']=custnumber;\t\r\n");
      out.write("\t//实缴出资\r\n");
      out.write("\tparams['lessee_base_money']=custname;\r\n");
      out.write("\t//客户股本结构历史\r\n");
      out.write("\tparams['lessee_history_list']=custname;\r\n");
      out.write("\t//合计\r\n");
      out.write("\tparams['lessee_base_sum']=custname;\r\n");
      out.write("\t\r\n");
      out.write("\t//租赁方案\r\n");
      out.write("\t//期限\r\n");
      out.write("\tparams['lease_scheme.leaseterm']=leaseterm;\r\n");
      out.write("\t//租赁金额\r\n");
      out.write("\tparams['lease_scheme.cleanleasemoney']=params['label.cleanleasemoney'];\r\n");
      out.write("\t//起租日\r\n");
      out.write("\tparams['lease_scheme.startdate']=mini.formatDate(mini.get(\"startdate\").getValue(),\"yyyy 年  MM 月 dd 日 \");\r\n");
      out.write("\t//基准利率\r\n");
      out.write("\tparams['lease_scheme.ratebase']=ratebase;\r\n");
      out.write("\t//保证金\r\n");
      out.write("\tparams['lease_scheme.margin']=margin;\r\n");
      out.write("\t//手续费\r\n");
      out.write("\tparams['lease_scheme.poundage']=yearpoundage.toFixed(2);\r\n");
      out.write("\t//手续费收取计划表\r\n");
      out.write("\tvar jsondate= mini.get('fund_fund_plan').getData();\r\n");
      out.write(" \tvar poundagearr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsondate.length;i++){\r\n");
      out.write("\t\tvar count=0;\r\n");
      out.write("\t\tvar poundage={};\r\n");
      out.write("\t\tvar moneyname=jsondate[i][\"feetypename\"];\r\n");
      out.write("\t\tif(moneyname=='手续费'){\r\n");
      out.write("\t\t\tcount+=1;\r\n");
      out.write("\t\t\tpoundage[\"number\"] = count;\r\n");
      out.write("\t\t\tpoundage[\"plandate\"] =jsondate[i][\"plandate\"];\r\n");
      out.write("\t\t\tpoundage[\"planmoney\"] =(jsondate[i][\"planmoney\"]/10000).toFixed(2);\r\n");
      out.write("\t\t\tpoundagearr.push(poundage);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['lease_poundage_list']=JsonUtil.encode(poundagearr);\t\r\n");
      out.write("\t\r\n");
      out.write("\t//租金偿还表\r\n");
      out.write("\t//租赁金额\r\n");
      out.write("\tparams['rent_pay.cleanleasemoney']=params['label.cleanleasemoney']*10000;\r\n");
      out.write("\t//期限\r\n");
      out.write("\tparams['rent_pay.leaseterm']=leaseterm;\r\n");
      out.write("\t//预付租金\r\n");
      out.write("\tparams['rent_pay.firstpayment']=getNumberNew(mini.get(\"firstpayment\").getValue());\r\n");
      out.write("\t//还款次数\r\n");
      out.write("\tparams['rent_pay.incomenumber']=mini.get(\"incomenumber\").getValue();//年还款数\r\n");
      out.write("\t//手续费\r\n");
      out.write("\tparams['rent_pay.poundage']=params['lease_scheme.poundage']*10000;\r\n");
      out.write("\t//租赁金额放款日\r\n");
      out.write("\tvar startdate=mini.get(\"startdate\").getValue();\r\n");
      out.write("\tparams['rent_pay.startdate']=mini.formatDate(startdate, \"yyyy 年  MM 月 dd 日 \");//放款日期\r\n");
      out.write("\t//租赁款发放计划表\r\n");
      out.write("\tvar lease_schemearr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsonrent.length;i++){\r\n");
      out.write("\t\tvar equipment={};\r\n");
      out.write("\t\t    equipment[\"number\"] = jsonrent[i][\"rentlist\"];\r\n");
      out.write("\t\t    equipment[\"plandate\"] = jsonrent[i][\"plandate\"];\r\n");
      out.write("\t\t    equipment[\"rent\"] = jsonrent[i][\"rent\"];\r\n");
      out.write("\t\t    equipment[\"corpus\"] = jsonrent[i][\"corpus\"];\r\n");
      out.write("\t\t    equipment[\"interest\"] = jsonrent[i][\"interest\"];\t    \t\t    \r\n");
      out.write("\t\t\tlease_schemearr.push(equipment);\t\t\r\n");
      out.write("\t} \r\n");
      out.write("\t//params['lease_plan_list']=JsonUtil.encode(mini.get('fund_rent_plan_frame').getData());\r\n");
      out.write("\tparams['rent_pay_list']=JsonUtil.encode(lease_schemearr);\r\n");
      out.write("\t\r\n");
      out.write("\t//console.info(jsonrent);\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t/* \r\n");
      out.write("\t//保证金收取计划表\r\n");
      out.write("\tvar jsonpromise = mini.get('fund_fund_plan').getData();\r\n");
      out.write(" \tvar marginarr = new Array();\r\n");
      out.write("\tfor(var i=0;i<jsonpromise.length;i++){\r\n");
      out.write("\t\tvar count=0;\r\n");
      out.write("\t\tvar margin={};\r\n");
      out.write("\t\tvar moneyname=jsonpromise[i][\"feetypename\"];\r\n");
      out.write("\t\tif(moneyname=='保证金'){\r\n");
      out.write("\t\t\tcount+=1;\r\n");
      out.write("\t\t\tmargin[\"number\"] = count;\r\n");
      out.write("\t\t\tmargin[\"plandate\"] =jsonpromise[i][\"plandate\"]; \r\n");
      out.write("\t\t\tmargin[\"planmoney\"] =(jsonpromise[i][\"planmoney\"]/10000).toFixed(2);\r\n");
      out.write("\t\t\tmarginarr.push(margin);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tparams['lease_margin_list']=JsonUtil.encode(marginarr);\r\n");
      out.write("\r\n");
      out.write("\t//租金偿还表\r\n");
      out.write("\tparams['rent_repay.cleanleasemoney']=(getNumberNew(mini.get(\"cleanleasemoney\").getValue())/10000).toFixed(2);//租赁金额\r\n");
      out.write("\tparams['rent_repay.leaseterm']=leaseterm;//期限\r\n");
      out.write("\tparams['rent_repay.firstpayment']=(getNumberNew(mini.get(\"firstpayment\").getValue())/10000).toFixed(2);//预付金\r\n");
      out.write("\tparams['rent_repay.incomenumber']=mini.get(\"incomenumber\").getValue();//年还款数\r\n");
      out.write("\tparams['rent_repay.count']=yearpoundage.toFixed(2);//手续费\r\n");
      out.write("\t//mini.alert(yearpoundage);\r\n");
      out.write("\t\r\n");
      out.write("\t//租金计划安排\r\n");
      out.write("\tparams['rent_repay_list']=JsonUtil.encode(lease_schemearr);\r\n");
      out.write("\tconsole.info(params['rent_repay_list']); */\r\n");
      out.write("\treturn params;\r\n");
      out.write("}\r\n");
      out.write("function getNumberNew(money){\r\n");
      out.write("\tvar num = money.split(\",\");\r\n");
      out.write("\tvar b = \"\";\r\n");
      out.write("\tfor(var a=0;a<num.length;a++ ){\r\n");
      out.write("\t\tb=b+num[a];\r\n");
      out.write("\t}\r\n");
      out.write("\treturn b;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function dateSformat(date){\r\n");
      out.write("\treturn date.replace('-','年').replace('-','月')+'日';\r\n");
      out.write("} \r\n");
      out.write("function showOperator(temp,cfalg){\r\n");
      out.write("\tvar id = temp.record.id;\r\n");
      out.write("\tvar base = \"<a href='javascript:void(0);' onclick='showRecorder(\\\"\" + id + \"\\\")'>记录</a>\";\r\n");
      out.write("\tvar base2 = \"<a href='javascript:void(0);' onclick='downloadFile(\\\"\" + id + \"\\\")'>下载</a>\";\r\n");
      out.write("\tvar base3 = \"<a href='javascript:void(0);' onclick='editoverdue(\\\"\" + id + \"\\\")'>编辑</a>\";\r\n");
      out.write("\tvar base4 = \"<a href='javascript:void(0);' onclick='readCreateWord(\\\"\" + id + \"\\\")'>查看</a>\";\r\n");
      out.write("\tvar base5 = \"<a href='javascript:void(0);' onclick='printCreateWord(\\\"\" + id + \"\\\")'>打印</a>\";\r\n");
      out.write("\tvar tempright=\"2\"\r\n");
      out.write("\tvar filename=temp.record.filename;\r\n");
      out.write("\tif(filename==\"项目建议书-直租.docx\"&&cfalg){\r\n");
      out.write("\t    return base2+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base3+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base4;\r\n");
      out.write("\t}else if(filename==\"项目建议书-回租.docx\"&&cfalg){\r\n");
      out.write("\t\treturn base2+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base3+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base4;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\treturn base2+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base4+\"&nbsp;&nbsp;|&nbsp;&nbsp;\"+base3;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function editoverdue(id){\r\n");
      out.write("\tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write("\tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write("\topenFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:\"2\"});\r\n");
      out.write("}\r\n");
      out.write("function downloadFile(Id){\r\n");
      out.write("\tattachmentDown({returnType:'file',url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/leasing/template/downLoadAttachById.action?id=\"+Id});\r\n");
      out.write("} \r\n");
      out.write("function  readCreateWord(id){\r\n");
      out.write("\tvar currentPageClientWidth  =  document.documentElement.clientWidth;\r\n");
      out.write(" \tvar currentPageClientHeight =  document.documentElement.clientHeight;\r\n");
      out.write(" \topenFullScreenWindow(getRootPath()+'/leasing/common/templateManager/editWordTemplate.bi',{id:id,otype:\"1\"});\r\n");
      out.write("}\r\n");
      out.write("//删除生成的文件。把文件注为无效\r\n");
      out.write("function dropCreateFile(rowDatas){\r\n");
      out.write("\tvar plandata = rowDatas;\r\n");
      out.write("\tvar ids=[];\r\n");
      out.write("\tfor(var i=0;i<plandata.length;i++){\r\n");
      out.write("\t\tids.push(plandata[i].id);\r\n");
      out.write("\t}\r\n");
      out.write("    mini.mask({el: document.body,cls: 'mini-mask-loading',html: '正在删除合同 请稍等...'});\r\n");
      out.write("\tvar url=\"/leasing/template/dropCreateFile.action\";\r\n");
      out.write("\tvar param=[];\r\n");
      out.write("\tparam[\"ids\"]=ids+\"\";\r\n");
      out.write("\tparam[\"isAttachment\"]=true;//连带级联操作删除自定义方法\r\n");
      out.write("\tajaxRequest({\r\n");
      out.write("\t\turl:getRootPath()+url,\r\n");
      out.write("\t\tmethod:'POST',\r\n");
      out.write("\t\tsuccess:function(rs){\r\n");
      out.write("\t\tvar message= rs.responseText;\r\n");
      out.write("\t\t\tmessage=message.replace(/(^\\s+)|(\\s+$)/g, \"\"); \r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t\tif (mini.get(\"id_customworkflowattachment\")) {\r\n");
      out.write("\t\t\t\tmini.get(\"id_customworkflowattachment\").reload();\r\n");
      out.write("\t\t\t\tmini.get(\"id_workflowhisAttachment\").reload();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tmini.alert(message);\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tasync:false,\r\n");
      out.write("\t\tfailure:function(res){\r\n");
      out.write("\t\t\tmini.unmask(document.body);\r\n");
      out.write("\t\t},\r\n");
      out.write("\t\tparams:param\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_table_quotation_scheme\"></div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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