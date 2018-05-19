<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %> 
<html>
<head>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="/tenwa/css/mainstyle.css" rel="stylesheet" type="text/css">
  <!--javascript libray-->	
	<script language="javascript" type="text/javascript" src="/tenwa/js/public.js"></script>
	<script language="javascript" type="text/javascript" src="/tenwa/js/calendar.js"></script>
	<script language="javascript" type="text/javascript" src="/tenwa/js/validator.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	
	<script language="javascript" type="text/javascript" src="/dict/js/js_dictionary.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tsc/compadJs.js"></script>
</head>		
<body onload="">
<div> 
	<!--隐藏字段-->
	<input type='hidden' name="currentLoginName" value="${sessionScope.login_username}"/>
	<input type='hidden' name="cust_id" type="hidden" value="${cust_id}"/>
	<input name="json_items_list_str" type="text" style="width:90%" value='[{"t1":"1221","t2":"1222","t3":"1223","t4":"1224"},{"t1":"1122","t2":"2122","t3":"3122","t4":"4122"}]'/>
	<input name="json_items_list_str1" type="text" style="width:90%" value=''/>
<%

String itemsName="订水明细";
ResultSet rs = null;
String sqlstr ="";
String wherestr = " where 1=1 ";
String filterStr = "";

String room_code = "暂未实现该业务表的读取！";
String area = "暂未实现该业务表的读取！";
%>

	
	<!--正文开始 -->
    <!--表单 -->
	<div id="kis_msg" style="color:#f00;text-align:center;font-size:24px;">订水流程表单内容</div>
    <table class="build-project-info-table">
          <tr class="tr-project-info tr-odd">
             <td class="td-content-title">申请日期：</td><td class="td-content">
			 <input type="text" id="id_applyDate" name="id_applyDate" class="td-content-input td-content-readonly" readOnly value="" />
			 </td>
             <td class="td-content-title">流 水 号：</td><td class="td-content"> <input name="SerialNo" class="td-content-input td-content-readonly" readOnly type="text" value="FIX_ME"/></td>
          </tr>
          <tr class="tr-project-info tr-even">
             <td class="td-content-title">申 请 人：</td><td class="td-content"><input name="applyUserName" class="td-content-input td-content-readonly" readOnly type="text" value="${sessionScope.login_username}" ></td>
             <td class="td-content-title">所在部门：</td><td class="td-content"><input name="applyUserDept" class="td-content-input td-content-readonly" readOnly type="text" value="FIX_ME"></td>
          </tr>
          <tr class="tr-project-info tr-odd">
             <td class="td-content-title">客户名称：</td><td colspan=3 class="td-content"><input name="selCustomerName" class="td-content-input td-content-readonly" readOnly type="text" value="${cust_name}"></td>
          </tr>
          <tr class="tr-project-info tr-even">
             <td class="td-content-title">所在楼层：</td><td class="td-content"><input name="customFloor" class="td-content-input " type="text" value="${area}" ></td>
             <td class="td-content-title">所在单元：</td><td class="td-content"><input name="customRoom" class="td-content-input " type="text" value="${room_code}"></td>
          </tr>
          <tr class="tr-project-info tr-odd">
             <td class="td-content-title">公司电话：</td><td class="td-content"><input name="CustomTel" class="td-content-input " type="text" value="测试表中无此信息"></td>
             <td class="td-content-title">预定日期：</td><td class="td-content">
			 <input type="text" id="id_reserveDate" name="reserveDate" class="Wdate td-content-input td-content-readonly" style="border:1px solid silver;"  onClick="WdatePicker(this,{readOnly:true})" readOnly/>
			 </td>
      </tr>
	</table>
	
    <!--多行控件-->
          <div id='id_project_info_tabs_content'>                          	             
          	<!--
             	  <comPad title="订水明细" mode="locale" state="edit" hasindex="1" hasstat="1" height="">
             	  	<table></table>             	  	
             	  	<xmlconfigpath></xmlconfigpath>
             	  	<jsonfield>json_items_list_str</jsonfield>
             	  	<action>new,edit,del,excel,copy,move</action>             	  	
             	  	<items>
             	  			<item name="t1" column="" title="t1" width="30%" type="" data="" property="stat,readonly" option="" default=""/>
             	  			<item name="t2" column="" title="t2" width="30%" type="dict" data=""  option="'KJKM'" default=""/>
             	  			<item name="t3" column="" title="t3" width="20%" type="" data="" property="hidden"  option="" default="222"/>
             	  			<item name="t4" column="" title="t4" width="20%" check="emptynumber" type="text" data="c" property="stat" option="" default=""/>
             	  	</items>
             	  </comPad>
             	 -->
         </div>
         
         <div id='id_project_info_tabs_content1'>                          	             
          	<!--
             	  <comPad title="订水明细1" mode="locale" state="edit" hasindex="1" hasstat="1">
             	  	<table></table>             	  	
             	  	<xmlconfigpath></xmlconfigpath>
             	  	<jsonfield>json_items_list_str1</jsonfield>
             	  	<action>new,edit,del,excel,copy,move</action>             	  	
             	  	<items>
             	  			<item name="at1" column="" title="at1" width="200" check="empty" type="" data="" property="stat,readonly" option="" default=""/>
             	  			<item name="at2" column="" title="at2" width="120" type="dict" data=""  option="'KJKM'" default=""/>
             	  			<item name="at3" column="" title="at3" width="100" type="list" data="" property=""  option="1,2,33,4,55,555" default=""/>
             	  			<item name="at4" column="" title="at4" width="100" type="" data="" property="" option="" default=""/>
             	  			<item name="at5" column="" title="at5" width="100" check="empty" type="" data="" property="stat,readonly" option="" default=""/>
             	  			<item name="at6" column="" title="at6" width="100" type="dict" data=""  option="'KJKM'" default=""/>
             	  			<item name="at7" column="" title="at7" width="100" type="list" data="" property=""  option="1,2,33,4,55,555" default=""/>
             	  			<item name="at8" column="" title="at8" width="100" type="datetime" data="" property="" option="" default=""/>
             	  			<item name="at9" column="" title="at9" width="100" check="empty" type="" data="" property="stat,readonly" option="" default=""/>
             	  			<item name="at10" column="" title="at10" width="100" type="dict" data=""  option="'KJKM'" default=""/>
             	  			<item name="at11" column="" title="at11" width="100" type="list" data="" property=""  option="1,2,33,4,55,555" default=""/>
             	  			<item name="at12" column="" title="at12" width="100" type="datetime" data="" property="" option="" default=""/>
             	  	</items>
             	  </comPad>
             	 -->
         </div>
		
    <table class="build-project-info-table">
             <tr class="tr-project-info tr-odd">
             	<td class="td-content-title">总金额：</td><td class="td-content"><input name="SumMoney" class="td-content-input " type="text" value=""></td>	  
             	<td class="td-content-title">结算方式：</td><td class="td-content"><div id="id_productType2" style="float:left;"></div></td>	  
             </tr>
    </table>
	<!-- 打印按钮 -->
		<button onclick="window.location.href='/leasing/WEB-INF/jsps/solutions/workflow/forms/jmcs/01_water/print_water.html'">打印订水单</button>
</div>

<script type="text/javascript" defer>
         
    /***流程信息回调函数开始***/
   function workflowSaveCallBack()//点击保存按钮时候调用,返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
   {	 
	   var isSubmitForm = true;	  
	   return isSubmitForm;
   }
   
   function saveCallBack(returnResult)//保存成功时候的回调函数
   {
     
   }
   function workflowSubmitCallBack(sumitButtonText)//返回true提交，返回false不提交 ，该回调函数可与用表单字段验证
   {
	    var isSubmitForm = true;
	    return isSubmitForm; 
   }
   function workflowNextRouteCallBack(sumitButtonText,requestNextRoute)//该回调函数可用于设置下一步提交的路由线路通过(requestNextRouteValue=路由线路值)进行设置;
   {	
   }
  </script>
  
  <script type="text/javascript" language="javascript"> 		  	    	 
  	//初始化多行控件
  	compadCollection.add("id_project_info_tabs_content");       
  	compadCollection.add("id_project_info_tabs_content1");
  	function showh(){
  		 var aa=window.open('');aa.document.body.innerText=document.body.innerHTML
  	}  
  </script>
 </body> 
</html>