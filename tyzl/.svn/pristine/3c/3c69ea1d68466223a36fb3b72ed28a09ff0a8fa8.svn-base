<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	function setapcolumnformatvalue(m){
		var obj = m.sender;
		var pMoneyOri = obj.getInputText().trim();
		var pMoney = pMoneyOri.replace(/,/gi,"");
		if(pMoney==""){pMoney = 0};
		if(isNaN(pMoney)){
			  pMoney="0";
	    }
	    if(pMoney.indexOf(".")>0){
	    	var clen=pMoney.length-pMoney.indexOf(".");
	    	if(clen>=4){clen=3;}
	    	if(clen>1){
	    		pMoney=parseFloat(pMoney).toFixed(clen-1);
	    	}
	    }
		var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,2})?$/;
		if(obj.vtype == 'rate'){
			var pMoneyReg = /^[-\+]?\d+[\.]?([0-9]{1,6})?$/;
		}
		if((pMoneyOri && pMoneyOri.length>=0) && !pMoneyReg.test(pMoney)){
			pMoney = 0;
			obj.setValue();
			obj.setValue('0');
		}else{
			obj.setValue(formatNumberThousand(pMoney));
		}
	}
	//æ ¼å¼åæ°å­ä¸ºååä½
	function formatNumberThousand(s) {  
		s += "";
		s = s.replace(/,/g,"");
		if(isNaN(s)){
		 return s;
		}
		s += "";
		var re=/(\d{1,3})(?=(\d{3})+(?:$|\D))/g; //
		var n1=s.replace(re,"$1,");
		return n1;
	}
	function thousandvalidation(e){
			 var temp= e.value.replace(/,/g,"");
			 if(temp==""){return false;}
             if (temp.match(/^(:?(:?\d+.\d+)|(:?\d+))$/)) {
            	 e.errorText = "";
                 e.isValid = true;
             }else{ 
                 e.errorText = "必须为数值";
                 e.isValid = false;
                 e.value=0;
             }            
	}
</script>