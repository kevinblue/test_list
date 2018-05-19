<!-- 最小化mini配置 -->
<%@include  file="var.jsp"%>
<!--mini js-->
<script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.0.min.js?${currentTimestamp}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/miniui/miniui.js?${currentTimestamp}" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/miniui/locale/${currentLocale}.js?${currentTimestamp}" type="text/javascript"></script>
<!--mini css  -->
<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/default/miniui.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/icons.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/miniui/${currentSkin}/skin.css?${currentTimestamp}"/>
<link rel=stylesheet href="${pageContext.request.contextPath}/css/base/${currentSkin}/base.css?${currentTimestamp}"/>