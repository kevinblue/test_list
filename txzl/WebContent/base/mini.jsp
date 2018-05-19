<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="base.jsp"%>
<script src="${pageContext.request.contextPath}/js/miniui/miniui.js?${currentTimestamp}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/miniui/locale/${currentLocale}.js?${currentTimestamp}" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniui_ext.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/miniuiExtButtons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comm/DataUtil.js"></script>

<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/default/miniui.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/icons.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/${currentSkin}/skin.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/comm/miniui_ext.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/tracywindy/tracywindyJsonUtil.js"></script>
<script type="text/javascript">
	var miniuiExtObject = {};
	function getMiniuiExtObject(id){
		return miniuiExtObject[id];
	}
	function getRootPath() {
		var strFullPath = window.document.location.href;
		var strPath = window.document.location.pathname;
		var pos = strFullPath.indexOf(strPath);
		var prePath = strFullPath.substring(0, pos);
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		return (prePath + postPath);
	}
	function miniextonbeforeshowpopup(e) {
		miniui_ext.onbeforeshowpopup(e);
	}
	function miniextonDateRenderer(e){
		return miniui_ext.onDateRenderer(e);
	}
	function toggleFieldSet(ck, id) {
        var dom = document.getElementById(id);
        dom.className = !ck.checked ? "hideFieldset" : "";
        var _fit = mini.get("_fit");
        _fit.setHeight(window.document.body.clientHeight);
    }
	function CloseWindow(action) {            
		if (action == "close" && grid.isChanged()) {
	        if (confirm("æ°æ®è¢«ä¿®æ¹äºï¼æ¯å¦åä¿å­ï¼")) {
	            return false;
	        }
	    }
	    if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
	    else window.close();           
	}
	function onCancel(e) {
	    CloseWindow("cancel");
	}
	function onCloseClick(e) {
	    var obj = e.sender;
	    obj.setText("");
	    obj.setValue("");
	}
	function isEnglish(){
		 if("${sessionScope['login_userlanguage'].language}" == 'en'){
			 	return true;
         }
		return false;
	}
	
	//设置千分位之后的值
	function setformatvalue(m){
		var pMoneyOri = m.getInputText().trim();
		var pMoney = pMoneyOri.replace(/,/gi,"");
		var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,2})?$/;
		var flag = true;
		if(m.vtype == 'rate'){
			 pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,6})?$/;
			 flag = false;
		}else if(m.vtype == 'int'){
			 pMoneyReg = /^[-\+]?\d+$/;
			 flag = false;
		}
		
		if(pMoneyOri && !pMoneyReg.test(pMoney)){
			pMoney = 0;
			m.setValue();
			m.setValue('0');
		}else{
			//把非数字的字符格式化成空格、并千分位
			if(flag){
				m.setValue(formatNumberThousand(pMoney));
			}
		}
	}
	//设置千分位
	function setapcolumnformatvalue(m){
		var obj = m.sender;
		var pMoneyOri = obj.getInputText().trim();
		var pMoney = pMoneyOri.replace(/,/gi,"");
		if(!pMoney){pMoney = 0};
		var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,2})?$/;
		if(obj.vtype == 'rate'){
			var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,6})?$/;
		}
		if((pMoney && pMoney.length>=0) && !pMoneyReg.test(pMoney)){
			pMoney = 0;
			obj.setValue();
			obj.setValue('0');
		}else{
			obj.setValue();
			obj.setValue(formatNumberThousand(pMoney));
		}
	}
	//格式化数字为千分位
	function formatNumberThousand(s) {  
		   s += "";
		   s = s.replace(/,/g,"");
		  
		   if(isNaN(s)){
			   return s;
		   }
		   //if(s==0){s="0.00";}
		   s += "";
		   //s = s.replace(/,/g,"");
		   var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g; //
		   var n1=s.replace(re,"$1,");
		   return n1;
	}
	
	$(function(){//2017/03/27 所有页面增加一个alt+3获取页面jsp路径的功能  by zhulh
		document.onkeydown = function(e){
			var keyCode = e.keyCode || e.which;
				if(e.altKey && keyCode == 51){
					var jpath = "${requestScope.requestFormPath}";
					if(jpath){
						mini.alert(jpath.substring(0,jpath.length-2)+"jsp","JSP路径");
					}else{
						mini.alert("<%=request.getServletPath()%>","JSP路径");
					}
				}
			}
		})
</script>