/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.56
 * Generated at: 2017-06-16 05:28:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsps.solutions.workflow.forms.reckon.rent_005fcommon;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class special_005fregular_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tif('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.isView}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("' == 'true' || isViewHistoryTask ==true){showTools = false};\r\n");
      out.write("\r\n");
      out.write("\tseajs.use([ \"js/apcomponent/aptable/aptable.js\" ], function(ApTable) {\r\n");
      out.write("\t\tnew ApTable({\r\n");
      out.write("\t\t\tid: \"special_regular\",\r\n");
      out.write("\t\t\trenderTo: \"id_special_regular\",\r\n");
      out.write("\t\t\twidth: globalClientWidth - 30,\r\n");
      out.write("\t\t\theight: 220,\r\n");
      out.write("\t\t\tlazyLoad: false,\r\n");
      out.write("\t\t\tisClickLoad:false,\r\n");
      out.write("\t\t\tremoteOper : false,\r\n");
      out.write("\t\t\tshowPager: false,\r\n");
      out.write("\t\t\tmultiSelect: true,\r\n");
      out.write("\t\t\tshowToolbar: showTools,\r\n");
      out.write("\t\t\teditFormPopupWindowWidth : 700,\r\n");
      out.write("\t\t\tdata: mini.decode('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty json_special_regular_str ? \"[]\" : json_special_regular_str }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("'),\r\n");
      out.write("\t\t\ttools : [ 'add','-','edit','-','remove','-','copy'],\r\n");
      out.write("\t\t\taddOperCallBack :function(miniTable,rowData){\r\n");
      out.write("\t\t\t\tvar rowDatas = miniTable.getData();\r\n");
      out.write("\t\t\t\trowDatas.sort(function(a,b){return a.startlist - b.endlist;});\r\n");
      out.write("\t\t\t\tminiTable.setData(rowDatas); \r\n");
      out.write("\t\t\t\t//changeGrace();//调整宽限期的方法\t\r\n");
      out.write("\t\t\t\tchangeDate();//调整起租日和第一期租金支付日\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tupdateOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\tvar rowDatas = miniTable.getData();\r\n");
      out.write("\t\t\t\trowDatas.sort(function(a,b){return a.startlist - b.endlist;});\r\n");
      out.write("\t\t\t\tminiTable.setData(rowDatas); \r\n");
      out.write("\t\t\t\t//changeGrace();//调整宽限期的方法\r\n");
      out.write("\t\t\t\tchangeDate();//调整起租日和第一期租金支付日\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tremoveOperCallBack:function(miniTable){\r\n");
      out.write("\t\t\t\t//changeGrace();//调整宽限期的方法\r\n");
      out.write("\t\t\t\tchangeDate();//调整起租日和第一期租金支付日\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tafterShowWindowCallBack: function(miniTable,miniForm,operFlag){\r\n");
      out.write("\t\t\t\tif(\"add\" == operFlag){\t\r\n");
      out.write("\t\t\t\t\tadjustregularincomenumberyear();//新增时支付频率会默认显示还租频率\r\n");
      out.write("\t\t\t\t\tvar startrentlist = mini.getbyName(\"startlist\");\r\n");
      out.write("\t\t\t\t\tvar endlist = mini.getbyName(\"endlist\");\r\n");
      out.write("\t\t\t\t\tvar row = miniTable.getRow(miniTable.getData().length-1);\r\n");
      out.write("\t\t\t\t\tif(null != row){\r\n");
      out.write("\t\t\t\t\t\tendrentlist = row.endlist;\r\n");
      out.write("\t\t\t\t\t\tstartrentlist.setValue(parseInt(endrentlist)+1);\r\n");
      out.write("\t\t\t\t\t\tendlist.setValue(parseInt(endrentlist)+2);\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tstartrentlist.setValue(1);\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tstartrentlist.setEnabled(false);\r\n");
      out.write("\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\tvar ratefloattype=mini.get(\"ratefloattype\");//利率计算方式\r\n");
      out.write("\t\t\t\t\tvar baserate=getMiniEditFormField(\"special_regular.baserate\");//基准利率\r\n");
      out.write("\t\t\t\t\tvar ratefloatamt=getMiniEditFormField(\"special_regular.ratefloatamt\");//利率调整值\r\n");
      out.write("\t\t\t\t\tvar yearrate=getMiniEditFormField(\"special_regular.yearrate\");//租赁年利率\r\n");
      out.write("\t\t\t\t\tvar regular_settlemethod = getMiniEditFormField('special_regular.regularsettlemethod');//计算方式\r\n");
      out.write("\t\t\t\t\tvar regular_settlemethod_value=regular_settlemethod.getValue();\r\n");
      out.write("\t\t\t\t\tvar regular_planmoney = getMiniEditFormField('special_regular.regularplanmoney');//金额\r\n");
      out.write("\t\t\t\t\tvar regular_months = getMiniEditFormField('special_regular.regularmonths');//间隔月数\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\tif(ratefloattype.getValue()==\"fixed\"){\r\n");
      out.write("\t\t\t\t\t\tbaserate.disable();\t//基准利率设为只读\t\t\t\r\n");
      out.write("\t\t\t\t\t\tratefloatamt.disable();\t//利率调整值设为只读\r\n");
      out.write("\t\t\t\t\t\tyearrate.enable();//租赁年利率设为可编辑\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\t//baserate.enable();\t//基准利率设为编辑\t\t\t\r\n");
      out.write("\t\t\t\t\t\tratefloatamt.enable();\t//利率调整值设为编辑\r\n");
      out.write("\t\t\t\t\t\tyearrate.disable();//租赁年利率设为可只读\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tif(regular_settlemethod_value == 'special_regular.knowingrent' ||regular_settlemethod_value == 'special_regular.knowingcorpus'|| regular_settlemethod_value == 'special_regular.nointerest'){\r\n");
      out.write("\t\t\t\t\t\tregular_planmoney.enable();\r\n");
      out.write("\t\t\t\t\t}else{\r\n");
      out.write("\t\t\t\t\t\tregular_planmoney.disable();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tvalidateForm:function (miniTable, miniForm, editFormItemOperFlag, addIndex){\r\n");
      out.write("\t\t\t\t//校验融资额的计划收款日期不能大于第一期的租金支付日\r\n");
      out.write("\t\t\t\tvar startlist = mini.getbyName('startlist').getValue();\r\n");
      out.write("\t\t\t\tvar endlist = mini.getbyName('endlist').getValue();\r\n");
      out.write("\t\t\t\tif(Number(endlist) < startlist){\r\n");
      out.write("\t\t\t\t\tmini.alert('结束期次不能小于开始期次！！！');\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t},\r\n");
      out.write("\t\t\tcolumns: [\r\n");
      out.write("\t\t\t\t{type: 'indexcolumn'},\r\n");
      out.write("\t\t\t\t{type: 'checkcolumn'},\r\n");
      out.write("\t\t\t\t{field: 'id', header: 'id', visible: false},\r\n");
      out.write("\t\t\t\t{field: 'docid', header: 'docid', visible: false},\t\r\n");
      out.write("\t\t\t\t{field: 'startlist', header: '起始期次',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\treadOnly:false,\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'endlist', header: '结束期次',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'int',\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field:'regularsettlemethodname', header:'计算方式', \r\n");
      out.write("\t\t\t\t      formEditorConfig:{\r\n");
      out.write("\t\t\t\t          fieldVisible:false,\r\n");
      out.write("\t\t\t\t     fillFromFieldName:'regularsettlemethod',\r\n");
      out.write("\t\t\t\t          fillProperty:'name',\r\n");
      out.write("\t\t\t           entityClassName:'com.tenwa.business.entity.DictionaryData',\t\r\n");
      out.write("\t\t\t\t entityHeaderFieldName:'name'}},\r\n");
      out.write("\t\t\t\t{field: 'regularsettlemethod', header: '计算方式',\r\n");
      out.write("\t\t\t\t\tvisible:false,\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t                  type:'combobox',\r\n");
      out.write("\t\t\t\t                  required:true,\r\n");
      out.write("\t\t\t\t             textField:'name',\r\n");
      out.write("\t\t\t\t            valueField:'value',\r\n");
      out.write("\t\t\t\t            otherAttributes:' onitemclick=checkregular_planmoneyIsReadonly ',\r\n");
      out.write("\t\t\t\t          fieldVisible:true,\r\n");
      out.write("\t\t\t\t    params:{pid: 'special_regular', xmlFileName:'combos/comboDict.xml'}}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'regularplanmoney', header: '金额',dataType : \"currency\",summary : true,align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'float',\r\n");
      out.write("\t\t\t\t\t\trequired: true,\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t/* {field:'regularincomenumberyearname', header:'支付频率',  visible:false,\r\n");
      out.write("\t\t\t\t      formEditorConfig:{\r\n");
      out.write("\t\t\t\t          fieldVisible:false,\r\n");
      out.write("\t\t\t\t     fillFromFieldName:'regularincomenumberyear',\r\n");
      out.write("\t\t\t\t          fillProperty:'name',\r\n");
      out.write("\t\t\t           entityClassName:'com.tenwa.business.entity.DictionaryData',\t\r\n");
      out.write("\t\t\t\t entityHeaderFieldName:'name'}},\r\n");
      out.write("\t\t\t\t{field: 'regularincomenumberyear', header: '支付频率', visible:false,\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t                  type:'combobox',\r\n");
      out.write("\t\t\t\t              required:true,\r\n");
      out.write("\t\t\t\t             textField:'name',\r\n");
      out.write("\t\t\t\t            valueField:'value',\r\n");
      out.write("\t\t\t\t            otherAttributes:' onitemclick=checkregular_monthsIsReadonly ',\r\n");
      out.write("\t\t\t\t          fieldVisible:false,\r\n");
      out.write("\t\t\t\t    params:{pid: 'sp_numberyear', xmlFileName:'combos/comboDict.xml'}}\r\n");
      out.write("\t\t\t\t}, */\r\n");
      out.write("\t\t\t\t{field: 'regularmonths', header: '间隔月数', visible:false,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'int',\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t/* {field:'ratefloattypename', header:'利率计算方式',  visible:false,\r\n");
      out.write("\t\t\t\t      formEditorConfig:{\r\n");
      out.write("\t\t\t\t          fieldVisible:false,\r\n");
      out.write("\t\t\t\t     fillFromFieldName:'ratefloattype',\r\n");
      out.write("\t\t\t\t          fillProperty:'name',\r\n");
      out.write("\t\t\t           entityClassName:'com.tenwa.business.entity.DictionaryData',\t\r\n");
      out.write("\t\t\t\t entityHeaderFieldName:'name'}},\r\n");
      out.write("\t\t\t\t{field: 'ratefloattype', header: '利率计算方式', visible:false,\r\n");
      out.write("\t\t\t\t\t  formEditorConfig:{\r\n");
      out.write("\t\t\t\t               newLine:true,\r\n");
      out.write("\t\t\t\t                  type:'combobox',\r\n");
      out.write("\t\t\t\t             textField:'name',\r\n");
      out.write("\t\t\t\t            valueField:'value',\r\n");
      out.write("\t\t\t\t            otherAttributes:'onitemclick=checkyearrateIsReadonly',\r\n");
      out.write("\t\t\t\t          fieldVisible:false,\r\n");
      out.write("\t\t\t\t    params:{pid: 'rate_float_type', xmlFileName:'combos/comboDict.xml'}}\r\n");
      out.write("\t\t\t\t}, */\r\n");
      out.write("\t\t\t\t/* {field: 'baserate', header: '基准利率（%）',align:'right', visible:false,\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'float',\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onvaluechanged=calyearrate',\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}, */\r\n");
      out.write("\t\t\t\t{field: 'ratefloatamt', header: '利率调整值',align:'right',\r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'float',\r\n");
      out.write("\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\tnewLine:true,\r\n");
      out.write("\t\t\t\t\t\totherAttributes:'onvaluechanged=calyearrate',\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:100,\r\n");
      out.write("\t\t\t\t\t\tdefaultValue : 0,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t},\r\n");
      out.write("\t\t\t\t{field: 'yearrate', header: '租赁年利率（%）', \r\n");
      out.write("\t\t\t\t\tformEditorConfig:\r\n");
      out.write("\t\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t\ttype:'float',\r\n");
      out.write("\t\t\t\t\t\tlabelWidth:120,\r\n");
      out.write("\t\t\t\t\t\trequired:true,\r\n");
      out.write("\t\t\t\t\t\twidth: 200\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t]\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("});\r\n");
      out.write("function checkregular_planmoneyIsReadonly(e){\r\n");
      out.write("\tvar regular_settlemethod =  e.sender.value;\r\n");
      out.write("\tvar miniObj = mini.getbyName('regularplanmoney');\r\n");
      out.write("\tif(regular_settlemethod == 'special_regular.knowingrent' || regular_settlemethod == 'special_regular.knowingcorpus'){\r\n");
      out.write("\t\tminiObj.enable();\r\n");
      out.write("\t\tminiObj.setValue(0);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tminiObj.setValue(0);\r\n");
      out.write("\t\tminiObj.disable();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("/* function changeGrace(){\r\n");
      out.write("\tvar data=mini.get(\"special_regular\").getData();\r\n");
      out.write("\tvar totalTerms=0;//租前息总期次\r\n");
      out.write("\tvar str=\"\";\r\n");
      out.write("\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\tstr+=data[i].regularsettlemethod+\",\";\r\n");
      out.write("\t\t//如果是租前息，则改变租前期数据\r\n");
      out.write("\t\tif(\"special_regular.beforeinterest\"==data[i].regularsettlemethod){\r\n");
      out.write("\t\t\tvar regularmonths=Number(data[i].regularmonths);//间隔月数\r\n");
      out.write("\t\t\tvar terms=(Number(data[i].endlist)-Number(data[i].startlist)+1)*regularmonths;\r\n");
      out.write("\t\t\ttotalTerms+=terms;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\tif(str.indexOf(\"special_regular.beforeinterest\")>-1){\r\n");
      out.write("\t\tmini.get(\"grace\").setValue(totalTerms);\r\n");
      out.write("\t}\r\n");
      out.write("} */\r\n");
      out.write("function checkyearrateIsReadonly(e){\r\n");
      out.write("\tvar ratefloat =  e.sender.value;\r\n");
      out.write("\tvar baserateObj = getMiniEditFormField('special_regular.baserate');//基准利率\r\n");
      out.write("\tvar ratefloatamtObj = getMiniEditFormField('special_regular.ratefloatamt');//利率调整值\r\n");
      out.write("\tvar yearrateObj = getMiniEditFormField('special_regular.yearrate');//年利率 \r\n");
      out.write("\tif(ratefloat == 'fixed'){\r\n");
      out.write("\t\tbaserateObj.disable();\r\n");
      out.write("\t\tbaserateObj.setValue(0);\r\n");
      out.write("\t\tratefloatamtObj.disable();\r\n");
      out.write("\t\tratefloatamtObj.setValue(0);\r\n");
      out.write("\t\tyearrateObj.enable();\r\n");
      out.write("\t\tyearrateObj.setValue(0);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tbaserateObj.enable();\r\n");
      out.write("\t\tratefloatamtObj.enable();\r\n");
      out.write("\t\tyearrateObj.disable();//年利率只读\r\n");
      out.write("\t\tvar a=baserateObj.getValue();\r\n");
      out.write("\t\tvar b=ratefloatamtObj.getValue();\r\n");
      out.write("\t\tyearrateObj.setValue(parseFloat(a)+parseFloat(b));//年利率初始化为：基准利率+利率调整值\r\n");
      out.write("\t\tbaserateObj.setRequired(true);\r\n");
      out.write("\t\tratefloatamtObj.setRequired(true);\r\n");
      out.write("\t\tif(ratefloat=='add'){\r\n");
      out.write("\t\t\tyearrateObj.setValue(decimal((parseFloat(ratefloatamtObj.getValue()) + parseFloat(baserateObj.getValue())),6));\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tyearrateObj.setValue(decimal((parseFloat(ratefloatamtObj.getValue())+1) * parseFloat(baserateObj.getValue()),6));\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checkregular_monthsIsReadonly(e){\r\n");
      out.write("\tvar regular_incomenumberyear =  e.sender.value;\r\n");
      out.write("\tvar miniObj = mini.getbyName('regularmonths');\r\n");
      out.write("\tvar regular_months = parseInt(regular_incomenumberyear.replace(\"sp_numberyear.income_\",\"\"));\r\n");
      out.write("\tif(regular_incomenumberyear == 'sp_numberyear.income_n'){\r\n");
      out.write("\t\tminiObj.enable();\r\n");
      out.write("\t\tminiObj.setValue(1);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tminiObj.setValue(regular_months);\r\n");
      out.write("\t\tminiObj.disable();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function calyearrate(){\r\n");
      out.write("\t try{\r\n");
      out.write("\t\t\tvar baserateObj = getMiniEditFormField('special_regular.baserate').getValue();\r\n");
      out.write("\t\t\tvar ratefloatamtObj = getMiniEditFormField('special_regular.ratefloatamt').getValue();\r\n");
      out.write("\t\t\tvar yearrateObj = getMiniEditFormField('special_regular.yearrate');\r\n");
      out.write("\t\t\tvar ratefloattypeObj = getMiniEditFormField('special_regular.ratefloattype').getValue();\r\n");
      out.write("\t\t\tif(ratefloattypeObj=='add'){\r\n");
      out.write("\t\t\t\tyearrateObj.setValue(decimal((parseFloat(ratefloatamtObj) + parseFloat(baserateObj)),6));\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tyearrateObj.setValue(decimal((parseFloat(ratefloatamtObj)+1) * parseFloat(baserateObj),6));\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t }catch(e){\r\n");
      out.write("\t }\r\n");
      out.write("}\r\n");
      out.write(" function adjustregularincomenumberyear(){\r\n");
      out.write("\t /*方法描述：商务条件分段配置中，点击【新增-只有新增有这个按钮】按钮，会自动获取页面中还租频率，并给弹出框中支付频率默认值；\r\n");
      out.write("\t 通过ajax获取最新的银行基准利率，并添加默认值*/\r\n");
      out.write("\t var incomenumberyear=$minigetvalue(\"incomenumberyear\");\r\n");
      out.write("\t var incomenumber=Number(incomenumberyear.substr(incomenumberyear.indexOf(\"_\")+1));\r\n");
      out.write("\t var name;\r\n");
      out.write("\t switch(incomenumber){\r\n");
      out.write("\t case 1: {name=\"月付\";break;}\r\n");
      out.write("\t case 3: {name=\"季付\";break;}\r\n");
      out.write("\t case 6: {name=\"半年付\";break;}\r\n");
      out.write("\t case 12: {name=\"年付\";break;}\r\n");
      out.write("\t default :{name=\"月付\";break;}\r\n");
      out.write("\t }\r\n");
      out.write("\t var id=\"regularincomenumberyear\";//和$miniSetCombValue(id,value,text)一样，不过formedit里面的数据用此方法获取不到，会报错。\r\n");
      out.write("\t var regularincomenumberyear=getMiniEditFormField(\"special_regular.\"+id)//支付频率\r\n");
      out.write("\t try{\r\n");
      out.write("\t\t regularincomenumberyear.setValue(\"sp_numberyear.\"+incomenumberyear);\r\n");
      out.write("\t\t regularincomenumberyear.setText(name);\r\n");
      out.write("\t\t getMiniEditFormField(\"special_regular.\"+id+\"name\").setValue(name);\r\n");
      out.write("\t\t getMiniEditFormField(\"special_regular.regularmonths\").disable();\r\n");
      out.write("\t\t getMiniEditFormField(\"special_regular.regularmonths\").setValue(incomenumber);\r\n");
      out.write("\t\t //获得银行基本利率\r\n");
      out.write("\t\t var baserate=getBaseRate();\r\n");
      out.write("\t\t getMiniEditFormField(\"special_regular.baserate\").setValue(Number(baserate));\r\n");
      out.write("\t }catch(e){\r\n");
      out.write("\t }\r\n");
      out.write(" }\r\n");
      out.write(" function getBaseRate(){\r\n");
      out.write("\t /*方法描述：获取银行最近一期(5年)*/\r\n");
      out.write("\t var baserate=0;\r\n");
      out.write("\t $.ajax({\r\n");
      out.write("\t        url: urlPrefix+\"/eleasing/jsp/base_rate/get_base_rate.xml\",\r\n");
      out.write("\t        type: \"post\",\r\n");
      out.write("\t        cache: false, \r\n");
      out.write("\t        async:false,\r\n");
      out.write("\t        success: function (text) {\t\t\t\t\t\t\t            \r\n");
      out.write("\t            var result = mini.decode(text);\r\n");
      out.write("\t           \tvar data=result.datas[0];\r\n");
      out.write("\t           \tbaserate= data.base_rate_abovefive;\r\n");
      out.write("\t        }\r\n");
      out.write("\t    });\r\n");
      out.write("\t return baserate;\r\n");
      out.write(" }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<div id=\"id_special_regular\"></div>");
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