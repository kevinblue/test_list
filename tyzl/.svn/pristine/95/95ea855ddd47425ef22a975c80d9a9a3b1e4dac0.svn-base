<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/jquery-easyui/easyui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/jquery-easyui/icon.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/mainstyle.css" rel="stylesheet" type="text/css">
  	
<!-- 	<script language="javascript" type="text/javascript" src="/tenwa/js/public.js"></script>
	<script language="javascript" type="text/javascript" src="/tenwa/js/calendar.js"></script>
	<script language="javascript" type="text/javascript" src="/tenwa/js/validator.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.easyui.min.js"></script>
	
<script type="text/javascript" src="/dict/js/js_dictionary.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tsc/compadJs.js"></script>
</head>		
<body onload="">
<div> 
	<!--隐藏字段-->
	<input type='hidden' name="currentLoginName" value="${sessionScope.login_username}"/>
	<input type='hidden' name="cust_id" type="hidden" value="${cust_id}"/>
	<input name="json_items_list_str" type="text" style="width:90%" value='[{"t1":"1221","t2":"1222","t3":"1223","t4":"1224"},{"t1":"1122","t2":"2122","t3":"3122","t4":"4122"}]'/>
	<input name="json_items_list_str1" type="text" style="width:90%" value=''/>

	
    <!--多行控件-->
        
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

</div>

  
  <script type="text/javascript" language="javascript"> 		  	    	 
  	//初始化多行控件
  	compadCollection.add("id_project_info_tabs_content1");
  	function showh(){
  		 var aa=window.open('');aa.document.body.innerText=document.body.innerHTML
  	}  
  </script>
 </body> 
</html>